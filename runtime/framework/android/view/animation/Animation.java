package android.view.animation;

import android.os.Handler;
import android.util.TypedValue;

/** android.view.animation.Animation, following the 4.x implementation. */
public abstract class Animation implements Cloneable {
  public static final int INFINITE = -1, RESTART = 1, REVERSE = 2, START_ON_FIRST_FRAME = -1, ABSOLUTE = 0, RELATIVE_TO_SELF = 1,
      RELATIVE_TO_PARENT = 2, ZORDER_NORMAL = 0, ZORDER_TOP = 1, ZORDER_BOTTOM = -1;
  public interface AnimationListener {
    void onAnimationStart(Animation a);
    void onAnimationEnd(Animation a);
    void onAnimationRepeat(Animation a);
  }
  protected static class Description {
    public int type; public float value;
    static Description parse(String v) {
      Description d = new Description();
      if (v == null) { d.type = ABSOLUTE; d.value = 0; return d; }
      v = v.trim();
      if (v.endsWith("%p")) { d.type = RELATIVE_TO_PARENT; d.value = Float.parseFloat(v.substring(0, v.length() - 2)) / 100f; }
      else if (v.endsWith("%")) { d.type = RELATIVE_TO_SELF; d.value = Float.parseFloat(v.substring(0, v.length() - 1)) / 100f; }
      else { d.type = ABSOLUTE; d.value = a2w.Values.isDimension(v) && !v.matches("-?[0-9.]+") ? a2w.Values.dimension(v) : Float.parseFloat(v); }
      return d;
    }
  }

  boolean mEnded, mStarted, mCycleFlip, mInitialized, mFillBefore = true, mFillAfter, mFillEnabled;
  long mStartTime = -1, mStartOffset, mDuration;
  int mRepeatCount, mRepeated, mRepeatMode = RESTART;
  Interpolator mInterpolator;
  AnimationListener mListener;
  private int mZAdjustment;
  private float mScaleFactor = 1;
  private boolean mMore = true, mOneMoreTime = true;
  Handler mListenerHandler;
  private Runnable mOnStart, mOnRepeat, mOnEnd;

  public Animation() { ensureInterpolator(); }
  public Animation(android.content.Context c, android.util.AttributeSet a) { this(); }
  @Override protected Animation clone() throws CloneNotSupportedException { return (Animation) super.clone(); }

  public void reset() { mInitialized = false; mCycleFlip = false; mRepeated = 0; mMore = true; mOneMoreTime = true; mListenerHandler = null; }
  public void cancel() {
    if (mStarted && !mEnded) { fireAnimationEnd(); mEnded = true; }
    mStartTime = Long.MIN_VALUE;
    mMore = mOneMoreTime = false;
  }
  public void detach() { if (mStarted && !mEnded) { mEnded = true; fireAnimationEnd(); } }
  public boolean isInitialized() { return mInitialized; }
  public void initialize(int width, int height, int parentWidth, int parentHeight) { reset(); mInitialized = true; }
  public void setListenerHandler(Handler h) {
    if (mListenerHandler == null) {
      mOnStart = () -> { if (mListener != null) mListener.onAnimationStart(Animation.this); };
      mOnRepeat = () -> { if (mListener != null) mListener.onAnimationRepeat(Animation.this); };
      mOnEnd = () -> { if (mListener != null) mListener.onAnimationEnd(Animation.this); };
    }
    mListenerHandler = h;
  }
  public void setInterpolator(android.content.Context c, int resID) { setInterpolator(AnimationUtils.loadInterpolator(c, resID)); }
  public void setInterpolator(Interpolator i) { mInterpolator = i; }
  public void setStartOffset(long o) { mStartOffset = o; }
  public void setDuration(long d) { if (d < 0) throw new IllegalArgumentException("Animation duration cannot be negative"); mDuration = d; }
  public void restrictDuration(long max) { if (mStartOffset > max) { mStartOffset = max; mDuration = 0; mRepeatCount = 0; return; } long dur = mDuration + mStartOffset; if (dur > max) { mDuration = max - mStartOffset; dur = max; } if (mDuration <= 0) { mDuration = 0; mRepeatCount = 0; return; } if (mRepeatCount < 0 || mRepeatCount > max || (dur * mRepeatCount) > max) { mRepeatCount = (int) (max / dur) - 1; if (mRepeatCount < 0) mRepeatCount = 0; } }
  public void scaleCurrentDuration(float scale) { mDuration = (long) (mDuration * scale); mStartOffset = (long) (mStartOffset * scale); }
  public void setStartTime(long t) { mStartTime = t; mStarted = mEnded = false; mCycleFlip = false; mRepeated = 0; mMore = true; }
  public void start() { setStartTime(-1); }
  public void startNow() { setStartTime(AnimationUtils.currentAnimationTimeMillis()); }
  public void setRepeatMode(int m) { mRepeatMode = m; }
  public void setRepeatCount(int c) { if (c < 0) c = INFINITE; mRepeatCount = c; }
  public boolean isFillEnabled() { return mFillEnabled; }
  public void setFillEnabled(boolean f) { mFillEnabled = f; }
  public void setFillBefore(boolean f) { mFillBefore = f; }
  public void setFillAfter(boolean f) { mFillAfter = f; }
  public void setZAdjustment(int z) { mZAdjustment = z; }
  public void setBackgroundColor(int c) {}
  protected float getScaleFactor() { return mScaleFactor; }
  public void setDetachWallpaper(boolean d) {}
  public Interpolator getInterpolator() { return mInterpolator; }
  public long getStartTime() { return mStartTime; }
  public long getDuration() { return mDuration; }
  public long getStartOffset() { return mStartOffset; }
  public int getRepeatMode() { return mRepeatMode; }
  public int getRepeatCount() { return mRepeatCount; }
  public boolean getFillBefore() { return mFillBefore; }
  public boolean getFillAfter() { return mFillAfter; }
  public int getZAdjustment() { return mZAdjustment; }
  public int getBackgroundColor() { return 0; }
  public boolean getDetachWallpaper() { return false; }
  public boolean willChangeTransformationMatrix() { return true; }
  public boolean willChangeBounds() { return true; }
  public void setAnimationListener(AnimationListener l) { mListener = l; }
  protected void ensureInterpolator() { if (mInterpolator == null) mInterpolator = new AccelerateDecelerateInterpolator(); }
  public long computeDurationHint() { return (getStartOffset() + getDuration()) * (getRepeatCount() + 1); }

  public boolean getTransformation(long currentTime, Transformation out) {
    if (mStartTime == -1) mStartTime = currentTime;
    final long startOffset = getStartOffset();
    final long duration = mDuration;
    float normalizedTime;
    if (duration != 0) normalizedTime = ((float) (currentTime - (mStartTime + startOffset))) / (float) duration;
    else normalizedTime = currentTime < mStartTime ? 0.0f : 1.0f;
    final boolean expired = normalizedTime >= 1.0f;
    mMore = !expired;
    if (!mFillEnabled) normalizedTime = Math.max(Math.min(normalizedTime, 1.0f), 0.0f);
    if ((normalizedTime >= 0.0f || mFillBefore) && (normalizedTime <= 1.0f || mFillAfter)) {
      if (!mStarted) { fireAnimationStart(); mStarted = true; }
      if (mFillEnabled) normalizedTime = Math.max(Math.min(normalizedTime, 1.0f), 0.0f);
      if (mCycleFlip) normalizedTime = 1.0f - normalizedTime;
      final float interpolatedTime = mInterpolator.getInterpolation(normalizedTime);
      applyTransformation(interpolatedTime, out);
    }
    if (expired) {
      if (mRepeatCount == mRepeated) {
        if (!mEnded) { mEnded = true; fireAnimationEnd(); }
      } else {
        if (mRepeatCount > 0) mRepeated++;
        if (mRepeatMode == REVERSE) mCycleFlip = !mCycleFlip;
        mStartTime = -1;
        mMore = true;
        fireAnimationRepeat();
      }
    }
    if (!mMore && mOneMoreTime) { mOneMoreTime = false; return true; }
    return mMore;
  }
  public boolean getTransformation(long currentTime, Transformation out, float scale) { mScaleFactor = scale; return getTransformation(currentTime, out); }
  private void fireAnimationStart() { if (mListener != null) { if (mListenerHandler == null) mListener.onAnimationStart(this); else mListenerHandler.postAtFrontOfQueue(mOnStart); } }
  private void fireAnimationRepeat() { if (mListener != null) { if (mListenerHandler == null) mListener.onAnimationRepeat(this); else mListenerHandler.postAtFrontOfQueue(mOnRepeat); } }
  private void fireAnimationEnd() { if (mListener != null) { if (mListenerHandler == null) mListener.onAnimationEnd(this); else mListenerHandler.postAtFrontOfQueue(mOnEnd); } }
  public boolean hasStarted() { return mStarted; }
  public boolean hasEnded() { return mEnded; }
  protected void applyTransformation(float interpolatedTime, Transformation t) {}
  protected float resolveSize(int type, float value, int size, int parentSize) {
    switch (type) {
      case ABSOLUTE: return value;
      case RELATIVE_TO_SELF: return size * value;
      case RELATIVE_TO_PARENT: return parentSize * value;
      default: return value;
    }
  }
  public void getInvalidateRegion(int l, int t, int r, int b, android.graphics.RectF inv, Transformation prev) { inv.set(l, t, r, b); }
  public void initializeInvalidateArea(int l, int t, int r, int b) {}
  public boolean hasAlpha() { return false; }
}
