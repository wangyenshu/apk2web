package android.view;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import java.util.ArrayList;

/** view.animate(): animates several view properties together (one ValueAnimator per start()). */
public class ViewPropertyAnimator {
  static final int ALPHA = 0, TX = 1, TY = 2, TZ = 3, SX = 4, SY = 5, ROT = 6, RX = 7, RY = 8, X = 9, Y = 10, Z = 11;
  final View v;
  private long mDuration = -1, mStartDelay;
  private TimeInterpolator mInterpolator;
  private Animator.AnimatorListener mListener;
  private ValueAnimator.AnimatorUpdateListener mUpdateListener;
  private Runnable mWithStart, mWithEnd;
  private final ArrayList<float[]> mPending = new ArrayList<>();   // {property, to, by?}
  private ValueAnimator mRunning;
  private boolean mPosted;
  ViewPropertyAnimator(View v) { this.v = v; }
  public ViewPropertyAnimator setDuration(long d) { if (d < 0) throw new IllegalArgumentException("Animators cannot have negative duration: " + d); mDuration = d; return this; }
  public long getDuration() { return mDuration >= 0 ? mDuration : 300; }
  public long getStartDelay() { return mStartDelay; }
  public ViewPropertyAnimator setStartDelay(long d) { mStartDelay = d; return this; }
  public ViewPropertyAnimator setInterpolator(TimeInterpolator i) { mInterpolator = i; return this; }
  public TimeInterpolator getInterpolator() { return mInterpolator; }
  public ViewPropertyAnimator setListener(Animator.AnimatorListener l) { mListener = l; return this; }
  public ViewPropertyAnimator setUpdateListener(ValueAnimator.AnimatorUpdateListener l) { mUpdateListener = l; return this; }
  public ViewPropertyAnimator withStartAction(Runnable r) { mWithStart = r; return this; }
  public ViewPropertyAnimator withEndAction(Runnable r) { mWithEnd = r; return this; }
  public ViewPropertyAnimator withLayer() { return this; }
  private ViewPropertyAnimator add(int prop, float value, boolean by) {
    mPending.add(new float[] {prop, value, by ? 1 : 0});
    if (!mPosted) { mPosted = true; v.post(this::startPending); }
    return this;
  }
  public ViewPropertyAnimator alpha(float a) { return add(ALPHA, a, false); }
  public ViewPropertyAnimator alphaBy(float a) { return add(ALPHA, a, true); }
  public ViewPropertyAnimator translationX(float x) { return add(TX, x, false); }
  public ViewPropertyAnimator translationXBy(float x) { return add(TX, x, true); }
  public ViewPropertyAnimator translationY(float y) { return add(TY, y, false); }
  public ViewPropertyAnimator translationYBy(float y) { return add(TY, y, true); }
  public ViewPropertyAnimator translationZ(float z) { return add(TZ, z, false); }
  public ViewPropertyAnimator translationZBy(float z) { return add(TZ, z, true); }
  public ViewPropertyAnimator scaleX(float s) { return add(SX, s, false); }
  public ViewPropertyAnimator scaleXBy(float s) { return add(SX, s, true); }
  public ViewPropertyAnimator scaleY(float s) { return add(SY, s, false); }
  public ViewPropertyAnimator scaleYBy(float s) { return add(SY, s, true); }
  public ViewPropertyAnimator rotation(float r) { return add(ROT, r, false); }
  public ViewPropertyAnimator rotationBy(float r) { return add(ROT, r, true); }
  public ViewPropertyAnimator rotationX(float r) { return add(RX, r, false); }
  public ViewPropertyAnimator rotationXBy(float r) { return add(RX, r, true); }
  public ViewPropertyAnimator rotationY(float r) { return add(RY, r, false); }
  public ViewPropertyAnimator rotationYBy(float r) { return add(RY, r, true); }
  public ViewPropertyAnimator x(float x) { return add(X, x, false); }
  public ViewPropertyAnimator xBy(float x) { return add(X, x, true); }
  public ViewPropertyAnimator y(float y) { return add(Y, y, false); }
  public ViewPropertyAnimator yBy(float y) { return add(Y, y, true); }
  public ViewPropertyAnimator z(float z) { return add(Z, z, false); }
  public ViewPropertyAnimator zBy(float z) { return add(Z, z, true); }
  public void start() { startPending(); }
  public void cancel() { mPending.clear(); if (mRunning != null) mRunning.cancel(); mRunning = null; }
  private float get(int p) {
    switch (p) {
      case ALPHA: return v.getAlpha(); case TX: return v.getTranslationX(); case TY: return v.getTranslationY(); case TZ: return v.getTranslationZ();
      case SX: return v.getScaleX(); case SY: return v.getScaleY(); case ROT: return v.getRotation(); case RX: return v.getRotationX(); case RY: return v.getRotationY();
      case X: return v.getX(); case Y: return v.getY(); default: return v.getZ();
    }
  }
  private void set(int p, float val) {
    switch (p) {
      case ALPHA: v.setAlpha(val); break; case TX: v.setTranslationX(val); break; case TY: v.setTranslationY(val); break; case TZ: v.setTranslationZ(val); break;
      case SX: v.setScaleX(val); break; case SY: v.setScaleY(val); break; case ROT: v.setRotation(val); break; case RX: v.setRotationX(val); break; case RY: v.setRotationY(val); break;
      case X: v.setX(val); break; case Y: v.setY(val); break; default: v.setZ(val);
    }
  }
  private void startPending() {
    mPosted = false;
    if (mPending.isEmpty()) return;
    final int n = mPending.size();
    final int[] props = new int[n];
    final float[] from = new float[n], to = new float[n];
    for (int i = 0; i < n; i++) {
      float[] e = mPending.get(i);
      props[i] = (int) e[0];
      from[i] = get(props[i]);
      to[i] = e[2] != 0 ? from[i] + e[1] : e[1];
    }
    mPending.clear();
    if (mRunning != null) mRunning.cancel();
    ValueAnimator a = mRunning = ValueAnimator.ofFloat(0f, 1f);
    if (mDuration >= 0) a.setDuration(mDuration);
    a.setStartDelay(mStartDelay);
    if (mInterpolator != null) a.setInterpolator(mInterpolator);
    a.addUpdateListener(an -> {
      float f = an.getAnimatedFraction();
      for (int i = 0; i < n; i++) set(props[i], from[i] + (to[i] - from[i]) * f);
      if (mUpdateListener != null) mUpdateListener.onAnimationUpdate(an);
    });
    final Animator.AnimatorListener listener = mListener;
    final Runnable start = mWithStart, end = mWithEnd;
    mWithStart = null; mWithEnd = null;
    a.addListener(new android.animation.AnimatorListenerAdapter() {
      @Override public void onAnimationStart(Animator an) { if (start != null) start.run(); if (listener != null) listener.onAnimationStart(an); }
      @Override public void onAnimationCancel(Animator an) { if (listener != null) listener.onAnimationCancel(an); }
      @Override public void onAnimationRepeat(Animator an) { if (listener != null) listener.onAnimationRepeat(an); }
      @Override public void onAnimationEnd(Animator an) {
        if (listener != null) listener.onAnimationEnd(an);
        if (end != null) end.run();
        if (mRunning == an) mRunning = null;
      }
    });
    a.start();
  }
}
