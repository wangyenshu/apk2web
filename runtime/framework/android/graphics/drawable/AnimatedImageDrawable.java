package android.graphics.drawable;

import android.graphics.*;
import android.os.SystemClock;

/** android.graphics.drawable.AnimatedImageDrawable (API 28): an animated GIF (decoded like Movie) played over its bounds. */
public class AnimatedImageDrawable extends Drawable implements Animatable2 {
  public static final int REPEAT_INFINITE = -1;
  private Movie mMovie;
  private int mRepeatCount = REPEAT_INFINITE, mAlpha = 255;
  private long mStart;
  private boolean mRunning, mAutoMirrored;
  private final Paint mPaint = new Paint(Paint.FILTER_BITMAP_FLAG);
  private final java.util.ArrayList<AnimationCallback> mCallbacks = new java.util.ArrayList<>();
  private int mDensity = android.util.DisplayMetrics.DENSITY_DEFAULT, mTargetDensity = a2w.Screen.DPI;

  public AnimatedImageDrawable() {}
  /** (not part of the API) the movie to play, decoded from a GIF */
  public void a2wSetMovie(Movie movie, int density) { mMovie = movie; mDensity = density; invalidateSelf(); }
  public void setRepeatCount(int repeatCount) {
    if (repeatCount < REPEAT_INFINITE) throw new IllegalArgumentException("invalid value passed to setRepeatCount" + repeatCount);
    mRepeatCount = repeatCount;
  }
  public int getRepeatCount() { return mRepeatCount; }
  private int scaled(int v) { return mDensity == 0 || mDensity == mTargetDensity ? v : (v * mTargetDensity + mDensity / 2) / mDensity; }
  @Override public int getIntrinsicWidth() { return mMovie == null ? -1 : scaled(mMovie.width()); }
  @Override public int getIntrinsicHeight() { return mMovie == null ? -1 : scaled(mMovie.height()); }
  @Override public void draw(Canvas canvas) {
    if (mMovie == null) return;
    int dur = mMovie.duration();
    int t = 0;
    if (mRunning && dur > 0) {
      long el = SystemClock.uptimeMillis() - mStart;
      long loops = el / dur;
      if (mRepeatCount != REPEAT_INFINITE && loops > mRepeatCount) { t = dur; finish(); }
      else t = (int) (el % dur);
    }
    mMovie.setTime(t);
    Rect b = getBounds();
    int s = canvas.save();
    canvas.translate(b.left, b.top);
    if (mAutoMirrored && getLayoutDirection() == android.util.LayoutDirection.RTL) { canvas.translate(b.width(), 0); canvas.scale(-1, 1); }
    if (mMovie.width() > 0 && mMovie.height() > 0) canvas.scale(b.width() / (float) mMovie.width(), b.height() / (float) mMovie.height());
    mPaint.setAlpha(mAlpha);
    mMovie.draw(canvas, 0, 0, mPaint);
    canvas.restoreToCount(s);
    if (mRunning) invalidateSelf();
  }
  private void finish() {
    mRunning = false;
    for (AnimationCallback c : new java.util.ArrayList<>(mCallbacks)) c.onAnimationEnd(this);
  }
  @Override public void start() {
    if (mRunning) return;
    mRunning = true;
    mStart = SystemClock.uptimeMillis();
    for (AnimationCallback c : new java.util.ArrayList<>(mCallbacks)) c.onAnimationStart(this);
    invalidateSelf();
  }
  @Override public void stop() { if (mRunning) finish(); }
  @Override public boolean isRunning() { return mRunning; }
  @Override public void registerAnimationCallback(AnimationCallback callback) { if (callback == null) throw new IllegalArgumentException("callback must not be null"); if (!mCallbacks.contains(callback)) mCallbacks.add(callback); }
  @Override public boolean unregisterAnimationCallback(AnimationCallback callback) { return mCallbacks.remove(callback); }
  @Override public void clearAnimationCallbacks() { mCallbacks.clear(); }
  @Override public void setAlpha(int alpha) { if (alpha < 0 || alpha > 255) throw new IllegalArgumentException("Alpha must be between 0 and 255! provided " + alpha); mAlpha = alpha; invalidateSelf(); }
  @Override public int getAlpha() { return mAlpha; }
  @Override public void setColorFilter(ColorFilter colorFilter) { mPaint.setColorFilter(colorFilter); invalidateSelf(); }
  @Override public ColorFilter getColorFilter() { return mPaint.getColorFilter(); }
  @Override public int getOpacity() { return PixelFormat.TRANSLUCENT; }
  @Override public void setAutoMirrored(boolean mirrored) { mAutoMirrored = mirrored; }
  @Override public boolean isAutoMirrored() { return mAutoMirrored; }
  @Override public boolean onLayoutDirectionChanged(int layoutDirection) { if (mAutoMirrored) { invalidateSelf(); return true; } return false; }
  @Override protected void onBoundsChange(Rect bounds) {}
  @Override public void inflate(android.content.res.Resources r, org.xmlpull.v1.XmlPullParser parser, android.util.AttributeSet attrs, android.content.res.Resources.Theme theme) {}
}
