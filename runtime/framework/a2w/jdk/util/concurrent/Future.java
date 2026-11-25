package a2w.jdk.util.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public interface Future<V> {
  boolean cancel(boolean mayInterruptIfRunning);
  boolean isCancelled();
  boolean isDone();
  V get() throws InterruptedException, ExecutionException;
  V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException;
  /** the result of a task that completed with one (Java 19) */
  default V resultNow() {
    if (!isDone()) throw new IllegalStateException("Task has not completed");
    boolean interrupted = false;
    try {
      while (true) {
        try { return get(); }
        catch (InterruptedException e) { interrupted = true; }
        catch (ExecutionException e) { throw new IllegalStateException("Task completed with exception"); }
        catch (java.util.concurrent.CancellationException e) { throw new IllegalStateException("Task was cancelled"); }
      }
    } finally { if (interrupted) Thread.currentThread().interrupt(); }
  }
  /** the exception of a task that failed (Java 19) */
  default Throwable exceptionNow() {
    if (!isDone()) throw new IllegalStateException("Task has not completed");
    if (isCancelled()) throw new IllegalStateException("Task was cancelled");
    boolean interrupted = false;
    try {
      while (true) {
        try { get(); throw new IllegalStateException("Task completed with a result"); }
        catch (InterruptedException e) { interrupted = true; }
        catch (ExecutionException e) { return e.getCause(); }
      }
    } finally { if (interrupted) Thread.currentThread().interrupt(); }
  }
  /** RUNNING, SUCCESS, FAILED or CANCELLED (Java 19) */
  default Future$State state() {
    if (!isDone()) return Future$State.RUNNING;
    if (isCancelled()) return Future$State.CANCELLED;
    boolean interrupted = false;
    try {
      while (true) {
        try { get(); return Future$State.SUCCESS; }
        catch (InterruptedException e) { interrupted = true; }
        catch (ExecutionException e) { return Future$State.FAILED; }
      }
    } finally { if (interrupted) Thread.currentThread().interrupt(); }
  }
}
