package android.animation;
public class StateListAnimator implements Cloneable {
  public StateListAnimator() {}
  public void addState(int[] specs, Animator animator) {}
  public void jumpToCurrentState() {}
  @Override public StateListAnimator clone() { return new StateListAnimator(); }
}
