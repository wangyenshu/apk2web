package android.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.AttributeSet;
import java.util.ArrayList;

/** A transparent hole in the 2D layer; what is behind it (the WebGL canvas) shows through. */
public class SurfaceView extends View {
  final ArrayList<SurfaceHolder.Callback> mCallbacks = new ArrayList<>();
  final Surface mSurface = new Surface();
  final SurfaceHolder mHolder = new SurfaceHolder() {
    public void addCallback(Callback c) { if (!mCallbacks.contains(c)) mCallbacks.add(c); }
    public void removeCallback(Callback c) { mCallbacks.remove(c); }
    public boolean isCreating() { return false; }
    public void setType(int t) {}
    public void setFixedSize(int w, int h) {}
    public void setSizeFromLayout() {}
    public void setFormat(int f) {}
    public void setKeepScreenOn(boolean k) {}
    public Canvas lockCanvas() { return lock(); }
    public Canvas lockCanvas(Rect d) { return lock(); }
    public void unlockCanvasAndPost(Canvas c) { post(c); }
    public Rect getSurfaceFrame() { return new Rect(0, 0, getWidth(), getHeight()); }
    public Surface getSurface() { return mSurface; }
  };
  public SurfaceView(Context c) { super(c); setWillNotDraw(false); mSurface.owner = this; }
  public SurfaceView(Context c, AttributeSet a) { super(c, a); setWillNotDraw(false); mSurface.owner = this; }
  public SurfaceView(Context c, AttributeSet a, int d) { super(c, a, d); setWillNotDraw(false); mSurface.owner = this; }
  public SurfaceHolder getHolder() { return mHolder; }
  /** the view an EGL window (a SurfaceHolder, Surface or SurfaceView) draws into, or null */
  public static View viewOf(Object window) {
    if (window instanceof SurfaceView) return (View) window;
    if (window instanceof SurfaceHolder) { Surface s = ((SurfaceHolder) window).getSurface(); return s == null ? null : s.owner; }
    if (window instanceof Surface) return ((Surface) window).owner;
    if (window instanceof View) return (View) window;
    return null;
  }
  public void setZOrderOnTop(boolean t) {}
  public void setZOrderMediaOverlay(boolean t) {}
  @Override public void draw(Canvas c) {
    if (mBuffer != null && mPosted) c.drawBitmap(mBuffer, 0, 0, null);   // software rendering (lockCanvas)
    else c.drawColor(0, PorterDuff.Mode.CLEAR);   // the hole the GL canvas shows through
    super.draw(c);
  }

  // software rendering: lockCanvas draws into a bitmap the size of the view, which unlockCanvasAndPost shows. A
  // drawing loop on its own thread is paced to the display (its thread waits for the next frame), as on a device.
  android.graphics.Bitmap mBuffer;
  boolean mPosted;
  long mLastPost;
  Canvas lock() {
    int w = getWidth(), h = getHeight();
    if (!mSurfaceExists || w <= 0 || h <= 0) { pace(16); return null; }
    if (mBuffer == null || mBuffer.getWidth() != w || mBuffer.getHeight() != h) {
      android.graphics.Bitmap old = mBuffer;
      mBuffer = android.graphics.Bitmap.createBitmap(w, h, android.graphics.Bitmap.Config.ARGB_8888);
      if (old != null) new Canvas(mBuffer).drawBitmap(old, 0, 0, null);
    }
    return new Canvas(mBuffer);
  }
  void post(Canvas c) {
    mPosted = true;
    postInvalidate();
    long now = android.os.SystemClock.uptimeMillis();
    long wait = mLastPost + 16 - now;
    mLastPost = Math.max(now, mLastPost + 16);
    pace(wait > 0 ? wait : 1);
  }
  /** a thread other than the main one waits (the page draws and handles input meanwhile) */
  static void pace(long ms) {
    if (android.os.Looper.myLooper() == android.os.Looper.getMainLooper()) return;
    try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
  }
  @Override protected void dispatchDraw(Canvas c) {}
  @Override protected void onMeasure(int ws, int hs) { setMeasuredDimension(getDefaultSize(0, ws), getDefaultSize(0, hs)); }

  // the surface exists while the view is attached and has a size: its holder's callbacks hear of it, as on Android
  boolean mSurfaceExists;
  @Override protected void onAttachedToWindow() { super.onAttachedToWindow(); updateSurface(); }
  @Override protected void onDetachedFromWindow() {
    if (mSurfaceExists) {
      mSurfaceExists = false;
      for (SurfaceHolder.Callback c : mCallbacks.toArray(new SurfaceHolder.Callback[0])) c.surfaceDestroyed(mHolder);
    }
    super.onDetachedFromWindow();
  }
  @Override protected void onSizeChanged(int w, int h, int ow, int oh) { super.onSizeChanged(w, h, ow, oh); updateSurface(); }
  private void updateSurface() {
    if (!isAttachedToWindow() || getWidth() <= 0 || getHeight() <= 0) return;
    SurfaceHolder.Callback[] cbs = mCallbacks.toArray(new SurfaceHolder.Callback[0]);
    if (!mSurfaceExists) { mSurfaceExists = true; for (SurfaceHolder.Callback c : cbs) c.surfaceCreated(mHolder); }
    for (SurfaceHolder.Callback c : cbs) c.surfaceChanged(mHolder, android.graphics.PixelFormat.RGBA_8888, getWidth(), getHeight());
  }
}
