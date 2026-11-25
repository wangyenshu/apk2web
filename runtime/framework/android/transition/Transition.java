package android.transition;
public abstract class Transition implements Cloneable {
  public Transition() {}
  @Override public Transition clone() {
    try { return (Transition) super.clone(); } catch (CloneNotSupportedException e) { throw new RuntimeException(e); }
  }
}
