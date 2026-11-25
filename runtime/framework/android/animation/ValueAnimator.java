package android.animation;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import java.util.ArrayList;
import java.util.HashMap;

/** Animates values over time on the main thread, at the display's frame pace. */
public class ValueAnimator extends Animator {
  public static final int RESTART = 1, REVERSE = 2, INFINITE = -1;
  public interface AnimatorUpdateListener { void onAnimationUpdate(ValueAnimator animation); }
  private static final TimeInterpolator sDefaultInterpolator = new AccelerateDecelerateInterpolator();
  private static float sDurationScale = 1.0f;
  private static long sFrameDelay = 16;

  PropertyValuesHolder[] mValues;
  HashMap<String, PropertyValuesHolder> mValuesMap;
  private long mDuration = 300, mStartDelay, mStartTime = -1, mSeekPlayTime = -1, mPauseTime;
  private TimeInterpolator mInterpolator = sDefaultInterpolator;
  private int mRepeatCount = 0, mRepeatMode = RESTART, mIteration;
  private boolean mRunning, mStarted, mReversing, mStartListenersCalled;
  private float mFraction;
  private ArrayList<AnimatorUpdateListener> mUpdateListeners;

  public ValueAnimator() {}
  public static void setDurationScale(float durationScale) { sDurationScale = durationScale; }
  public static float getDurationScale() { return sDurationScale; }
  public static boolean areAnimatorsEnabled() { return sDurationScale != 0; }
  public static ValueAnimator ofInt(int... values) { ValueAnimator a = new ValueAnimator(); a.setIntValues(values); return a; }
  public static ValueAnimator ofArgb(int... values) { ValueAnimator a = new ValueAnimator(); a.setIntValues(values); a.setEvaluator(ArgbEvaluator.getInstance()); return a; }
  public static ValueAnimator ofFloat(float... values) { ValueAnimator a = new ValueAnimator(); a.setFloatValues(values); return a; }
  public static ValueAnimator ofPropertyValuesHolder(PropertyValuesHolder... values) { ValueAnimator a = new ValueAnimator(); a.setValues(values); return a; }
  public static ValueAnimator ofObject(TypeEvaluator evaluator, Object... values) { ValueAnimator a = new ValueAnimator(); a.setObjectValues(values); a.setEvaluator(evaluator); return a; }
  public void setIntValues(int... values) {
    if (values == null || values.length == 0) return;
    if (mValues == null || mValues.length == 0) setValues(PropertyValuesHolder.ofInt("", values)); else mValues[0].setIntValues(values);
  }
  public void setFloatValues(float... values) {
    if (values == null || values.length == 0) return;
    if (mValues == null || mValues.length == 0) setValues(PropertyValuesHolder.ofFloat("", values)); else mValues[0].setFloatValues(values);
  }
  public void setObjectValues(Object... values) {
    if (values == null || values.length == 0) return;
    if (mValues == null || mValues.length == 0) setValues(PropertyValuesHolder.ofObject("", (TypeEvaluator) null, values)); else mValues[0].setObjectValues(values);
  }
  public void setValues(PropertyValuesHolder... values) {
    mValues = values;
    mValuesMap = new HashMap<>();
    for (PropertyValuesHolder v : values) mValuesMap.put(v.getPropertyName(), v);
  }
  public PropertyValuesHolder[] getValues() { return mValues; }
  public void setEvaluator(TypeEvaluator value) { if (value != null && mValues != null && mValues.length > 0) mValues[0].setEvaluator(value); }
  @Override public ValueAnimator setDuration(long duration) {
    if (duration < 0) throw new IllegalArgumentException("Animators cannot have negative duration: " + duration);
    mDuration = duration;
    return this;
  }
  @Override public long getDuration() { return mDuration; }
  @Override public long getTotalDuration() { return mRepeatCount == INFINITE ? DURATION_INFINITE : mStartDelay + mDuration * (mRepeatCount + 1); }
  public void setCurrentPlayTime(long playTime) { setCurrentFraction(mDuration > 0 ? (float) playTime / mDuration : 1); }
  public void setCurrentFraction(float fraction) {
    if (fraction < 0) fraction = 0;
    long scaled = (long) (fraction * mDuration * sDurationScale);
    if (mRunning) mStartTime = now() - scaled;
    else mSeekPlayTime = scaled;
    animateValue(fractionInIteration(fraction));
  }
  public long getCurrentPlayTime() { return !mStarted ? 0 : mRunning ? now() - mStartTime : (mSeekPlayTime >= 0 ? mSeekPlayTime : 0); }
  @Override public long getStartDelay() { return mStartDelay; }
  @Override public void setStartDelay(long startDelay) { mStartDelay = Math.max(0, startDelay); }
  public static long getFrameDelay() { return sFrameDelay; }
  public static void setFrameDelay(long frameDelay) { sFrameDelay = frameDelay; }
  public Object getAnimatedValue() { return mValues != null && mValues.length > 0 ? mValues[0].getAnimatedValue() : null; }
  public Object getAnimatedValue(String propertyName) { PropertyValuesHolder v = mValuesMap == null ? null : mValuesMap.get(propertyName); return v == null ? null : v.getAnimatedValue(); }
  public void setRepeatCount(int value) { mRepeatCount = value; }
  public int getRepeatCount() { return mRepeatCount; }
  public void setRepeatMode(int value) { mRepeatMode = value; }
  public int getRepeatMode() { return mRepeatMode; }
  public void addUpdateListener(AnimatorUpdateListener listener) { if (mUpdateListeners == null) mUpdateListeners = new ArrayList<>(); mUpdateListeners.add(listener); }
  public void removeAllUpdateListeners() { mUpdateListeners = null; }
  public void removeUpdateListener(AnimatorUpdateListener listener) { if (mUpdateListeners != null) mUpdateListeners.remove(listener); }
  @Override public void setInterpolator(TimeInterpolator value) { mInterpolator = value != null ? value : new LinearInterpolator(); }
  @Override public TimeInterpolator getInterpolator() { return mInterpolator; }
  public float getAnimatedFraction() { return mFraction; }
  @Override public boolean isRunning() { return mRunning; }
  @Override public boolean isStarted() { return mStarted; }
  @Override public boolean canReverse() { return true; }

  // ---------------------------------------------------------------- running
  static long now() { return SystemClock.uptimeMillis(); }
  private static Handler sHandler;
  private static final ArrayList<ValueAnimator> sAnimations = new ArrayList<>();
  private static boolean sScheduled;
  private static final Runnable sFrame = new Runnable() {
    public void run() {
      sScheduled = false;
      long t = now();
      for (ValueAnimator a : new ArrayList<>(sAnimations)) {
        try { a.doAnimationFrame(t); } catch (RuntimeException e) { a2w.Host.uncaught(e); sAnimations.remove(a); }
      }
      schedule();
    }
  };
  private static void schedule() {
    if (sScheduled || sAnimations.isEmpty()) return;
    if (sHandler == null) sHandler = new Handler(Looper.getMainLooper());
    sScheduled = true;
    sHandler.postDelayed(sFrame, sFrameDelay);
  }
  @Override public void start() { start(false); }
  private void start(boolean playBackwards) {
    if (Looper.myLooper() == null) throw new android.util.AndroidRuntimeException("Animators may only be run on Looper threads");
    mReversing = playBackwards;
    mStarted = true;
    mPaused = false;
    mRunning = false;
    mIteration = 0;
    mStartListenersCalled = false;
    long seek = mSeekPlayTime;
    mStartTime = now() + (long) (mStartDelay * sDurationScale) - (seek >= 0 ? seek : 0);
    mSeekPlayTime = -1;
    initAnimation();
    if (mStartDelay == 0 || seek >= 0) {
      startAnimationNow();
      animateValue(fractionInIteration(seek >= 0 && mDuration > 0 ? (float) seek / mDuration : 0));
    }
    if (!sAnimations.contains(this)) sAnimations.add(this);
    schedule();
  }
  void initAnimation() {}
  private void startAnimationNow() {
    mRunning = true;
    if (!mStartListenersCalled) { mStartListenersCalled = true; notifyStart(); }
  }
  /** fraction of the current iteration (0..1) from overall fraction, honoring REVERSE repeats and reverse() */
  private float fractionInIteration(float overall) {
    int iteration = (int) overall;
    float f = overall - iteration;
    if (mRepeatCount != INFINITE && iteration > mRepeatCount) { iteration = mRepeatCount; f = 1; }
    else if (overall > 0 && f == 0) { iteration--; f = 1; }
    boolean backwards = mRepeatMode == REVERSE && (iteration & 1) == 1;
    if (backwards) f = 1 - f;
    if (mReversing) f = 1 - f;
    return f;
  }
  void doAnimationFrame(long frameTime) {
    if (mPaused) return;
    if (!mRunning) {
      if (frameTime < mStartTime) return;
      startAnimationNow();
    }
    long scaled = (long) (mDuration * sDurationScale);
    float overall = scaled > 0 ? (float) (frameTime - mStartTime) / scaled : 1f;
    if (overall < 0) overall = 0;
    int iteration = (int) overall;
    boolean done = mRepeatCount != INFINITE && overall >= mRepeatCount + 1;
    if (!done && iteration > mIteration) { mIteration = iteration; notifyRepeat(); }
    animateValue(fractionInIteration(done ? mRepeatCount + 1 : overall));
    if (done) endAnimation();
  }
  void animateValue(float fraction) {
    fraction = mInterpolator.getInterpolation(fraction);
    mFraction = fraction;
    if (mValues != null) for (PropertyValuesHolder v : mValues) v.calculateValue(fraction);
    if (mUpdateListeners != null) for (AnimatorUpdateListener l : new ArrayList<>(mUpdateListeners)) l.onAnimationUpdate(this);
  }
  private void endAnimation() {
    sAnimations.remove(this);
    boolean notify = mStarted && mStartListenersCalled;
    mRunning = false;
    mStarted = false;
    mPaused = false;
    mReversing = false;
    if (notify || mStartListenersCalled) notifyEnd();
    mStartListenersCalled = false;
  }
  @Override public void cancel() {
    if (!mStarted && !mRunning) return;
    if (mStartListenersCalled) notifyCancel();
    else { mStartListenersCalled = true; notifyStart(); notifyCancel(); }
    endAnimation();
  }
  @Override public void end() {
    if (!mStarted) { initAnimation(); mStarted = true; mStartListenersCalled = false; }
    if (!mStartListenersCalled) { mStartListenersCalled = true; notifyStart(); }
    animateValue(mReversing ? 0f : fractionInIteration(mRepeatCount == INFINITE ? 1 : mRepeatCount + 1) < 0.5f && mRepeatMode == REVERSE && (mRepeatCount & 1) == 1 ? 0f : 1f);
    endAnimation();
  }
  @Override public void resume() { if (mPaused) { mStartTime += now() - mPauseTime; } super.resume(); }
  @Override public void pause() { boolean was = mPaused; super.pause(); if (!was && mPaused) mPauseTime = now(); }
  @Override public void reverse() {
    if (mRunning) {
      long played = now() - mStartTime;
      mStartTime = now() - ((long) (mDuration * sDurationScale) - played);
      mReversing = !mReversing;
    } else start(true);
  }
  @Override public ValueAnimator clone() {
    final ValueAnimator anim = (ValueAnimator) super.clone();
    if (mUpdateListeners != null) anim.mUpdateListeners = new ArrayList<>(mUpdateListeners);
    anim.mStartTime = -1; anim.mSeekPlayTime = -1; anim.mRunning = false; anim.mStarted = false; anim.mPaused = false; anim.mReversing = false; anim.mStartListenersCalled = false;
    if (mValues != null) {
      PropertyValuesHolder[] newValues = new PropertyValuesHolder[mValues.length];
      anim.mValuesMap = new HashMap<>();
      for (int i = 0; i < mValues.length; i++) { newValues[i] = mValues[i].clone(); anim.mValuesMap.put(newValues[i].getPropertyName(), newValues[i]); }
      anim.mValues = newValues;
    }
    return anim;
  }
  @Override public String toString() { return "ValueAnimator@" + Integer.toHexString(hashCode()); }
}
