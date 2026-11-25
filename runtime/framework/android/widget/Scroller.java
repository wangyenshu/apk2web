package android.widget;

import android.content.Context;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

/** android.widget.Scroller (2.x physics: viscous-fluid scrolls, constant-deceleration flings). */
public class Scroller {
  private static final int SCROLL_MODE = 0, FLING_MODE = 1, DEFAULT_DURATION = 250;
  private int mMode, mStartX, mStartY, mFinalX, mFinalY, mMinX, mMaxX, mMinY, mMaxY, mCurrX, mCurrY;
  private long mStartTime;
  private int mDuration;
  private float mDurationReciprocal, mDeltaX, mDeltaY, mVelocity, mCoeffX = 0, mCoeffY = 1;
  private boolean mFinished = true;
  private final Interpolator mInterpolator;
  private final float mDeceleration;
  private static final float VISCOUS_FLUID_SCALE = 8.0f;
  private static final float VISCOUS_FLUID_NORMALIZE;
  static { VISCOUS_FLUID_NORMALIZE = 1.0f / viscousFluidRaw(1.0f); }

  public Scroller(Context c) { this(c, null); }
  public Scroller(Context c, Interpolator i) { this(c, i, true); }
  public Scroller(Context c, Interpolator i, boolean flywheel) {
    mInterpolator = i;
    float ppi = a2w.Values.METRICS.density * 160.0f;
    mDeceleration = 9.80665f * 39.37f * ppi * 0.015f;
  }
  public final void setFriction(float f) {}
  public final boolean isFinished() { return mFinished; }
  public final void forceFinished(boolean f) { mFinished = f; }
  public final int getDuration() { return mDuration; }
  public final int getCurrX() { return mCurrX; }
  public final int getCurrY() { return mCurrY; }
  public float getCurrVelocity() { return mMode == FLING_MODE ? mVelocity - mDeceleration * timePassed() / 2000.0f : 0; }
  public final int getStartX() { return mStartX; }
  public final int getStartY() { return mStartY; }
  public final int getFinalX() { return mFinalX; }
  public final int getFinalY() { return mFinalY; }
  public boolean computeScrollOffset() {
    if (mFinished) return false;
    int timePassed = (int) (AnimationUtils.currentAnimationTimeMillis() - mStartTime);
    if (timePassed < mDuration) {
      switch (mMode) {
        case SCROLL_MODE: {
          float x = timePassed * mDurationReciprocal;
          if (mInterpolator == null) x = viscousFluid(x); else x = mInterpolator.getInterpolation(x);
          mCurrX = mStartX + Math.round(x * mDeltaX);
          mCurrY = mStartY + Math.round(x * mDeltaY);
          break;
        }
        case FLING_MODE: {
          float t = timePassed / 1000.0f;
          float distance = (mVelocity * t) - (mDeceleration * t * t / 2.0f);
          mCurrX = mStartX + Math.round(distance * mCoeffX);
          mCurrX = Math.min(mCurrX, mMaxX); mCurrX = Math.max(mCurrX, mMinX);
          mCurrY = mStartY + Math.round(distance * mCoeffY);
          mCurrY = Math.min(mCurrY, mMaxY); mCurrY = Math.max(mCurrY, mMinY);
          if (mCurrX == mFinalX && mCurrY == mFinalY) mFinished = true;
          break;
        }
      }
    } else {
      mCurrX = mFinalX;
      mCurrY = mFinalY;
      mFinished = true;
    }
    return true;
  }
  public void startScroll(int sx, int sy, int dx, int dy) { startScroll(sx, sy, dx, dy, DEFAULT_DURATION); }
  public void startScroll(int sx, int sy, int dx, int dy, int duration) {
    mMode = SCROLL_MODE;
    mFinished = false;
    mDuration = duration;
    mStartTime = AnimationUtils.currentAnimationTimeMillis();
    mStartX = sx; mStartY = sy;
    mFinalX = sx + dx; mFinalY = sy + dy;
    mDeltaX = dx; mDeltaY = dy;
    mDurationReciprocal = 1.0f / (float) Math.max(1, mDuration);
  }
  public void fling(int startX, int startY, int velocityX, int velocityY, int minX, int maxX, int minY, int maxY) {
    mMode = FLING_MODE;
    mFinished = false;
    float velocity = (float) Math.hypot(velocityX, velocityY);
    mVelocity = velocity;
    mDuration = (int) (1000 * velocity / mDeceleration);
    mStartTime = AnimationUtils.currentAnimationTimeMillis();
    mStartX = startX; mStartY = startY;
    mCoeffX = velocity == 0 ? 1.0f : velocityX / velocity;
    mCoeffY = velocity == 0 ? 1.0f : velocityY / velocity;
    int totalDistance = (int) ((velocity * velocity) / (2 * mDeceleration));
    mMinX = minX; mMaxX = maxX; mMinY = minY; mMaxY = maxY;
    mFinalX = startX + Math.round(totalDistance * mCoeffX);
    mFinalX = Math.min(mFinalX, mMaxX); mFinalX = Math.max(mFinalX, mMinX);
    mFinalY = startY + Math.round(totalDistance * mCoeffY);
    mFinalY = Math.min(mFinalY, mMaxY); mFinalY = Math.max(mFinalY, mMinY);
  }
  static float viscousFluidRaw(float x) {
    x *= VISCOUS_FLUID_SCALE;
    if (x < 1.0f) x -= (1.0f - (float) Math.exp(-x));
    else { float start = 0.36787944117f; x = 1.0f - (float) Math.exp(1.0f - x); x = start + x * (1.0f - start); }
    return x;
  }
  static float viscousFluid(float x) { return viscousFluidRaw(x) * VISCOUS_FLUID_NORMALIZE; }
  public void abortAnimation() { mCurrX = mFinalX; mCurrY = mFinalY; mFinished = true; }
  public void extendDuration(int extend) { int passed = timePassed(); mDuration = passed + extend; mDurationReciprocal = 1.0f / mDuration; mFinished = false; }
  public int timePassed() { return (int) (AnimationUtils.currentAnimationTimeMillis() - mStartTime); }
  public void setFinalX(int x) { mFinalX = x; mDeltaX = mFinalX - mStartX; mFinished = false; }
  public void setFinalY(int y) { mFinalY = y; mDeltaY = mFinalY - mStartY; mFinished = false; }
  public boolean isScrollingInDirection(float xvel, float yvel) { return !mFinished && Math.signum(xvel) == Math.signum(mFinalX - mStartX) && Math.signum(yvel) == Math.signum(mFinalY - mStartY); }
}
