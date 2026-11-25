package a2w.jdk.util.concurrent;

/** java.util.concurrent.RecursiveTask: a ForkJoinTask whose compute() returns the result. */
public abstract class RecursiveTask<V> extends ForkJoinTask<V> {
  V result;
  public RecursiveTask() {}
  protected abstract V compute();
  public final V getRawResult() { return result; }
  protected final void setRawResult(V value) { result = value; }
  protected final boolean exec() { result = compute(); return true; }
}
