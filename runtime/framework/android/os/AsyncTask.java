package android.os;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;

public abstract class AsyncTask<Params, Progress, Result> {
  public enum Status { PENDING, RUNNING, FINISHED }

  /** Every task gets its own (cooperative) thread. */
  public static final Executor THREAD_POOL_EXECUTOR = r -> new Thread(r, "AsyncTask").start();

  /** One task at a time, in order. */
  public static final Executor SERIAL_EXECUTOR = new Executor() {
    final ArrayDeque<Runnable> tasks = new ArrayDeque<>();
    Runnable active;
    public synchronized void execute(final Runnable r) {
      tasks.offer(() -> { try { r.run(); } finally { scheduleNext(); } });
      if (active == null) scheduleNext();
    }
    synchronized void scheduleNext() {
      if ((active = tasks.poll()) != null) THREAD_POOL_EXECUTOR.execute(active);
    }
  };

  private static volatile Executor defaultExecutor = THREAD_POOL_EXECUTOR;
  private static final Handler handler = new Handler(Looper.getMainLooper());

  private volatile Status status = Status.PENDING;
  private volatile boolean cancelled;
  private Result result;
  private boolean done;

  public static void setDefaultExecutor(Executor e) { defaultExecutor = e; }
  public static void execute(Runnable r) { defaultExecutor.execute(r); }

  public AsyncTask() {}
  protected abstract Result doInBackground(Params... params);
  protected void onPreExecute() {}
  protected void onPostExecute(Result r) {}
  protected void onProgressUpdate(Progress... values) {}
  protected void onCancelled(Result r) { onCancelled(); }
  protected void onCancelled() {}

  public final Status getStatus() { return status; }
  public final boolean isCancelled() { return cancelled; }
  public final boolean cancel(boolean interrupt) { cancelled = true; return true; }

  @SafeVarargs public final AsyncTask<Params, Progress, Result> execute(Params... params) { return executeOnExecutor(defaultExecutor, params); }

  @SafeVarargs
  public final AsyncTask<Params, Progress, Result> executeOnExecutor(Executor exec, final Params... params) {
    if (status != Status.PENDING) throw new IllegalStateException("Cannot execute task: already " + status);
    status = Status.RUNNING;
    onPreExecute();
    exec.execute(() -> {
      Result r = null;
      try {
        r = doInBackground(params);
      } catch (Throwable t) {
        a2w.Host.uncaught(t);
      }
      final Result fr = r;
      handler.post(() -> finish(fr));
    });
    return this;
  }

  private void finish(Result r) {
    synchronized (this) { result = r; done = true; notifyAll(); }
    if (cancelled) onCancelled(r); else onPostExecute(r);
    status = Status.FINISHED;
  }

  @SafeVarargs protected final void publishProgress(final Progress... values) {
    if (!cancelled) handler.post(() -> onProgressUpdate(values));
  }

  public final Result get() throws InterruptedException {
    synchronized (this) { while (!done) wait(); return result; }
  }
  public final Result get(long timeout, java.util.concurrent.TimeUnit unit) throws InterruptedException {
    synchronized (this) { if (!done) wait(unit.toMillis(timeout)); return result; }
  }
}
