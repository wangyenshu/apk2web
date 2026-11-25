package a2w.jdk.util.concurrent;

/** java.util.concurrent.RecursiveAction: a ForkJoinTask without a result. */
public abstract class RecursiveAction extends ForkJoinTask<Void> {
  public RecursiveAction() {}
  protected abstract void compute();
  public final Void getRawResult() { return null; }
  protected final void setRawResult(Void mustBeNull) {}
  protected final boolean exec() { compute(); return true; }
}
