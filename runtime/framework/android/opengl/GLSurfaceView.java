package android.opengl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import a2w.Windows;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * GLSurfaceView over the page's WebGL canvas. The renderer runs on the main thread from the
 * frame loop (the emulated native code owns the GL context, so there is no GL thread).
 */
public class GLSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Windows.FrameClient {
  public static final int RENDERMODE_WHEN_DIRTY = 0, RENDERMODE_CONTINUOUSLY = 1, DEBUG_CHECK_GL_ERROR = 1, DEBUG_LOG_GL_CALLS = 2;
  public interface Renderer {
    void onSurfaceCreated(GL10 gl, EGLConfig config);
    void onSurfaceChanged(GL10 gl, int width, int height);
    void onDrawFrame(GL10 gl);
  }
  public interface EGLConfigChooser { EGLConfig chooseConfig(javax.microedition.khronos.egl.EGL10 egl, javax.microedition.khronos.egl.EGLDisplay display); }
  public interface EGLContextFactory {}
  public interface EGLWindowSurfaceFactory {}
  public interface GLWrapper { javax.microedition.khronos.opengles.GL wrap(javax.microedition.khronos.opengles.GL gl); }
  /** what renderers get: every GL interface, calling the static bindings (GLImpl, generated) */
  static final GL10 GL = new GLImpl();
  /** (for EGLContext.getGL) */
  public static javax.microedition.khronos.opengles.GL gl() { return GL; }
  static final EGLConfig CONFIG = new EGLConfig() {};
  Renderer mRenderer;
  int mRenderMode = RENDERMODE_CONTINUOUSLY;
  boolean mPaused, mCreated, mRequestRender = true, mAttached;
  int mSurfaceW = -1, mSurfaceH = -1, mPlaceX = -1, mPlaceY = -1;
  final int[] mLoc = new int[2];

  public GLSurfaceView(Context c) { super(c); init(); }
  public GLSurfaceView(Context c, AttributeSet a) { super(c, a); init(); }
  private void init() { getHolder().addCallback(this); }
  public void setRenderer(Renderer r) {
    if (mRenderer != null) throw new IllegalStateException("setRenderer has already been called for this instance.");
    mRenderer = r;
    if (mAttached) Windows.addFrameClient(this);
  }
  /** a version 3 context reports OpenGL ES 3.0 (its calls are WebGL 2's) */
  public void setEGLContextClientVersion(int v) { mClientVersion = v; GLBridge.setClientVersion(v); }
  private int mClientVersion = 1;
  public void setEGLConfigChooser(boolean depth) {}
  public void setEGLConfigChooser(int r, int g, int b, int a, int d, int s) {}
  public void setEGLConfigChooser(EGLConfigChooser c) {}
  public void setEGLContextFactory(EGLContextFactory f) {}
  public void setEGLWindowSurfaceFactory(EGLWindowSurfaceFactory f) {}
  public void setGLWrapper(GLWrapper w) {}
  public void setDebugFlags(int f) {}
  public int getDebugFlags() { return 0; }
  public void setPreserveEGLContextOnPause(boolean p) {}
  public boolean getPreserveEGLContextOnPause() { return true; }
  public void setRenderMode(int m) { mRenderMode = m; if (m == RENDERMODE_CONTINUOUSLY) Windows.scheduleFrame(false); }
  public int getRenderMode() { return mRenderMode; }
  public void requestRender() { mRequestRender = true; Windows.scheduleFrame(false); }
  public void queueEvent(Runnable r) { a2w.ViewRoot.mainHandler().post(r); }
  public void onPause() { mPaused = true; }
  public void onResume() { mPaused = false; mRequestRender = true; Windows.scheduleFrame(false); }
  public void surfaceCreated(SurfaceHolder h) {}
  public void surfaceChanged(SurfaceHolder h, int f, int w, int hh) {}
  public void surfaceDestroyed(SurfaceHolder h) {}
  @Override protected void onAttachedToWindow() { super.onAttachedToWindow(); mAttached = true; if (mRenderer != null) Windows.addFrameClient(this); }
  @Override protected void onDetachedFromWindow() { mAttached = false; Windows.removeFrameClient(this); super.onDetachedFromWindow(); }

  /** Windows.FrameClient: render one frame; true while continuous rendering wants more frames */
  public boolean onFrame(long now) {
    if (mRenderer == null || mPaused) return false;
    int w = getWidth(), h = getHeight();
    if (w <= 0 || h <= 0) return true;
    getLocationOnScreen(mLoc);
    if (mLoc[0] != mPlaceX || mLoc[1] != mPlaceY || w != mSurfaceW || h != mSurfaceH) {
      mPlaceX = mLoc[0]; mPlaceY = mLoc[1];
      a2w.Host.glPlace(mPlaceX, mPlaceY, w, h);
    }
    if (!mCreated) {
      mCreated = true;
      mRenderer.onSurfaceCreated(GL, CONFIG);
    }
    if (w != mSurfaceW || h != mSurfaceH) {
      mSurfaceW = w; mSurfaceH = h;
      mRenderer.onSurfaceChanged(GL, w, h);
      mRequestRender = true;
    }
    if (mRenderMode == RENDERMODE_CONTINUOUSLY && !a2w.Host.frameDue(now)) return true;
    if (mRenderMode == RENDERMODE_CONTINUOUSLY || mRequestRender) {
      mRequestRender = false;
      mRenderer.onDrawFrame(GL);
    }
    return mRenderMode == RENDERMODE_CONTINUOUSLY;
  }
}
