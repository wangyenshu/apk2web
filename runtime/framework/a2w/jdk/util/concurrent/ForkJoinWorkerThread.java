package a2w.jdk.util.concurrent;

/** java.util.concurrent.ForkJoinWorkerThread: a thread that runs a ForkJoinPool's tasks. */
public class ForkJoinWorkerThread extends Thread {
  ForkJoinPool pool;
  int index;
  protected ForkJoinWorkerThread(ForkJoinPool pool) { super("ForkJoinPool-worker"); this.pool = pool; }
  protected ForkJoinWorkerThread(a2w.jdk.lang.ThreadGroup group, ForkJoinPool pool, boolean preserveThreadLocals) { this(pool); }
  public ForkJoinPool getPool() { return pool; }
  public int getPoolIndex() { return index; }
  public int getQueuedTaskCount() { return pool.queued(); }
  protected void onStart() {}
  protected void onTermination(Throwable exception) {}
  public void run() {
    Throwable ex = null;
    try { onStart(); pool.runWorker(this); }
    catch (Throwable t) { ex = t; }
    finally {
      try { onTermination(ex); } catch (Throwable t) { if (ex == null) ex = t; }
      pool.workerExited(this);
    }
    if (ex instanceof RuntimeException) throw (RuntimeException) ex;
    if (ex instanceof Error) throw (Error) ex;
  }
}
