package android.animation;

import java.util.ArrayList;

public abstract class Animator implements Cloneable {
  public static final long DURATION_INFINITE = -1;
  public interface AnimatorListener {
    default void onAnimationStart(Animator animation, boolean isReverse) { onAnimationStart(animation); }
    default void onAnimationEnd(Animator animation, boolean isReverse) { onAnimationEnd(animation); }
    void onAnimationStart(Animator animation);
    void onAnimationEnd(Animator animation);
    void onAnimationCancel(Animator animation);
    void onAnimationRepeat(Animator animation);
  }
  public interface AnimatorPauseListener { void onAnimationPause(Animator animation); void onAnimationResume(Animator animation); }
  ArrayList<AnimatorListener> mListeners;
  ArrayList<AnimatorPauseListener> mPauseListeners;
  boolean mPaused;
  public void start() {}
  public void cancel() {}
  public void end() {}
  public void pause() {
    if (isStarted() && !mPaused) { mPaused = true; if (mPauseListeners != null) for (AnimatorPauseListener l : new ArrayList<>(mPauseListeners)) l.onAnimationPause(this); }
  }
  public void resume() {
    if (mPaused) { mPaused = false; if (mPauseListeners != null) for (AnimatorPauseListener l : new ArrayList<>(mPauseListeners)) l.onAnimationResume(this); }
  }
  public boolean isPaused() { return mPaused; }
  public abstract long getStartDelay();
  public abstract void setStartDelay(long startDelay);
  public abstract Animator setDuration(long duration);
  public abstract long getDuration();
  public abstract void setInterpolator(TimeInterpolator value);
  public TimeInterpolator getInterpolator() { return null; }
  public abstract boolean isRunning();
  public boolean isStarted() { return isRunning(); }
  public long getTotalDuration() { long d = getDuration(); return d == DURATION_INFINITE ? DURATION_INFINITE : getStartDelay() + d; }
  public void addListener(AnimatorListener listener) { if (mListeners == null) mListeners = new ArrayList<>(); mListeners.add(listener); }
  public void removeListener(AnimatorListener listener) { if (mListeners != null) { mListeners.remove(listener); if (mListeners.isEmpty()) mListeners = null; } }
  public ArrayList<AnimatorListener> getListeners() { return mListeners; }
  public void addPauseListener(AnimatorPauseListener listener) { if (mPauseListeners == null) mPauseListeners = new ArrayList<>(); mPauseListeners.add(listener); }
  public void removePauseListener(AnimatorPauseListener listener) { if (mPauseListeners != null) mPauseListeners.remove(listener); }
  public void removeAllListeners() { mListeners = null; mPauseListeners = null; }
  @Override public Animator clone() {
    try {
      final Animator anim = (Animator) super.clone();
      if (mListeners != null) anim.mListeners = new ArrayList<>(mListeners);
      if (mPauseListeners != null) anim.mPauseListeners = new ArrayList<>(mPauseListeners);
      return anim;
    } catch (CloneNotSupportedException e) { throw new AssertionError(); }
  }
  public void setupStartValues() {}
  public void setupEndValues() {}
  public void setTarget(Object target) {}
  public boolean canReverse() { return false; }
  public void reverse() { throw new IllegalStateException("Reverse is not supported"); }
  void notifyStart() { if (mListeners != null) for (AnimatorListener l : new ArrayList<>(mListeners)) l.onAnimationStart(this); }
  void notifyEnd() { if (mListeners != null) for (AnimatorListener l : new ArrayList<>(mListeners)) l.onAnimationEnd(this); }
  void notifyCancel() { if (mListeners != null) for (AnimatorListener l : new ArrayList<>(mListeners)) l.onAnimationCancel(this); }
  void notifyRepeat() { if (mListeners != null) for (AnimatorListener l : new ArrayList<>(mListeners)) l.onAnimationRepeat(this); }
}
