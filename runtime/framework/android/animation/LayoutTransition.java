package android.animation;

import android.view.View;
import android.view.ViewGroup;

/** Animations for layout changes (here: changes apply at once; the API and listeners work). */
public class LayoutTransition {
  public static final int CHANGE_APPEARING = 0, CHANGE_DISAPPEARING = 1, APPEARING = 2, DISAPPEARING = 3, CHANGING = 4;
  public interface TransitionListener {
    void startTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType);
    void endTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType);
  }
  private final long[] mDurations = {300, 300, 300, 300, 300};
  private final Animator[] mAnimators = new Animator[5];
  private final java.util.ArrayList<TransitionListener> mListeners = new java.util.ArrayList<>();
  public LayoutTransition() {}
  public void setDuration(long duration) { java.util.Arrays.fill(mDurations, duration); }
  public void setDuration(int transitionType, long duration) { mDurations[transitionType] = duration; }
  public long getDuration(int transitionType) { return mDurations[transitionType]; }
  public void setStagger(int transitionType, long duration) {}
  public long getStagger(int transitionType) { return 0; }
  public void setStartDelay(int transitionType, long delay) {}
  public long getStartDelay(int transitionType) { return 0; }
  public void setInterpolator(int transitionType, TimeInterpolator interpolator) {}
  public TimeInterpolator getInterpolator(int transitionType) { return null; }
  public void setAnimator(int transitionType, Animator animator) { mAnimators[transitionType] = animator; }
  public Animator getAnimator(int transitionType) { return mAnimators[transitionType]; }
  public void enableTransitionType(int transitionType) {}
  public void disableTransitionType(int transitionType) {}
  public boolean isTransitionTypeEnabled(int transitionType) { return false; }
  public void setAnimateParentHierarchy(boolean animateParentHierarchy) {}
  public boolean isChangingLayout() { return false; }
  public boolean isRunning() { return false; }
  public void addChild(ViewGroup parent, View child) {}
  public void removeChild(ViewGroup parent, View child) {}
  public void showChild(ViewGroup parent, View child) {}
  public void hideChild(ViewGroup parent, View child) {}
  public void addTransitionListener(TransitionListener listener) { mListeners.add(listener); }
  public void removeTransitionListener(TransitionListener listener) { mListeners.remove(listener); }
  public java.util.List<TransitionListener> getTransitionListeners() { return mListeners; }
}
