package a2w.jdk.util.concurrent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * java.util.concurrent.ForkJoinTask. fork() queues the task in the current worker's pool (the common pool outside one);
 * join() runs a queued task in the joining thread, and a worker waiting for a task another thread runs executes other
 * queued tasks meanwhile, so recursive decompositions finish with any number of workers.
 */
public abstract class ForkJoinTask<V> implements Future<V>, java.io.Serializable {
  static final int NORMAL = 1, CANCELLED = 2, EXCEPTIONAL = 3;
  volatile int status;          // 0 while not done
  boolean started;
  Throwable exception;
  ForkJoinPool queuedIn;
  private short tag;

  public ForkJoinTask() {}

  protected abstract boolean exec();
  public abstract V getRawResult();
  protected abstract void setRawResult(V value);

  /** runs the task here unless it ran or is done; completes it when exec says so */
  final void doExec() {
    synchronized (this) { if (started || status != 0) return; started = true; }
    boolean done;
    try { done = exec(); } catch (Throwable ex) { setExceptional(ex); return; }
    if (done) setDone(NORMAL);
  }
  final boolean setDone(int s) {
    synchronized (this) {
      if (status != 0) return false;
      status = s;
      notifyAll();
    }
    return true;
  }
  final void setExceptional(Throwable ex) {
    synchronized (this) { if (status != 0) return; exception = ex; }
    setDone(EXCEPTIONAL);
    propagateException(ex);
  }
  /** (CountedCompleter passes an exception on to its completers) */
  void propagateException(Throwable ex) {}

  static ForkJoinWorkerThread worker() { Thread t = Thread.currentThread(); return t instanceof ForkJoinWorkerThread ? (ForkJoinWorkerThread) t : null; }

  public final ForkJoinTask<V> fork() {
    ForkJoinWorkerThread w = worker();
    (w != null ? w.pool : ForkJoinPool.commonPool()).push(this, w != null);
    return this;
  }
  /** waits until done: runs this task if it is still queued, else helps with other tasks of the pool */
  final void awaitDone(long deadline) throws InterruptedException {
    if (status != 0) return;
    ForkJoinWorkerThread w = worker();
    if (w != null) {
      if (w.pool.remove(this)) doExec();
      while (status == 0) {
        ForkJoinTask<?> other = w.pool.poll();
        if (other != null) { other.doExec(); continue; }
        if (!waitHere(deadline, 20)) return;
      }
    } else {
      ForkJoinPool q = queuedIn;
      if (q != null && q.remove(this)) doExec();
      while (status == 0) if (!waitHere(deadline, 0)) return;
    }
  }
  private boolean waitHere(long deadline, long slice) throws InterruptedException {
    synchronized (this) {
      if (status != 0) return true;
      long left = deadline == 0 ? 0 : deadline - System.currentTimeMillis();
      if (deadline != 0 && left <= 0) return false;
      long t = slice > 0 ? (left > 0 ? Math.min(left, slice) : slice) : left;
      wait(t);
    }
    return true;
  }
  final void awaitUninterruptibly(long deadline) {
    boolean interrupted = false;
    for (;;) {
      try { awaitDone(deadline); break; } catch (InterruptedException e) { interrupted = true; }
    }
    if (interrupted) Thread.currentThread().interrupt();
  }
  private V report() {
    switch (status) {
      case CANCELLED: throw new CancellationException();
      case EXCEPTIONAL:
        if (exception instanceof RuntimeException) throw (RuntimeException) exception;
        if (exception instanceof Error) throw (Error) exception;
        throw new RuntimeException(exception);
      default: return getRawResult();
    }
  }
  public final V join() { awaitUninterruptibly(0); return report(); }
  public final V invoke() { doExec(); awaitUninterruptibly(0); return report(); }
  public final void quietlyJoin() { awaitUninterruptibly(0); }
  public final void quietlyInvoke() { doExec(); awaitUninterruptibly(0); }
  public final boolean quietlyJoin(long timeout, TimeUnit unit) throws InterruptedException {
    if (Thread.interrupted()) throw new InterruptedException();
    awaitDone(System.currentTimeMillis() + Math.max(1, unit.toMillis(timeout)));
    return status != 0;
  }
  public final boolean quietlyJoinUninterruptibly(long timeout, TimeUnit unit) {
    awaitUninterruptibly(System.currentTimeMillis() + Math.max(1, unit.toMillis(timeout)));
    return status != 0;
  }
  public final V get() throws InterruptedException, ExecutionException {
    if (Thread.interrupted()) throw new InterruptedException();
    awaitDone(0);
    return reportGet();
  }
  public final V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
    if (Thread.interrupted()) throw new InterruptedException();
    awaitDone(System.currentTimeMillis() + Math.max(1, unit.toMillis(timeout)));
    if (status == 0) throw new TimeoutException();
    return reportGet();
  }
  private V reportGet() throws ExecutionException {
    if (status == CANCELLED) throw new CancellationException();
    if (status == EXCEPTIONAL) throw new ExecutionException(exception);
    return getRawResult();
  }

  public boolean cancel(boolean mayInterruptIfRunning) {
    boolean r = setDone(CANCELLED);
    ForkJoinPool q = queuedIn;
    if (r && q != null) q.remove(this);
    return r || status == CANCELLED;
  }
  public final boolean isDone() { return status != 0; }
  public final boolean isCancelled() { return status == CANCELLED; }
  public final boolean isCompletedAbnormally() { return status >= CANCELLED; }
  public final boolean isCompletedNormally() { return status == NORMAL; }
  public final Throwable getException() { return status == CANCELLED ? new CancellationException() : status == EXCEPTIONAL ? exception : null; }
  public void completeExceptionally(Throwable ex) {
    setExceptional((ex instanceof RuntimeException) || (ex instanceof Error) ? ex : new RuntimeException(ex));
  }
  public void complete(V value) {
    try { setRawResult(value); } catch (Throwable ex) { setExceptional(ex); return; }
    setDone(NORMAL);
  }
  public final void quietlyComplete() { setDone(NORMAL); }
  public void reinitialize() { synchronized (this) { status = 0; started = false; exception = null; } }
  public boolean tryUnfork() { ForkJoinWorkerThread w = worker(); return w != null && w.pool.remove(this); }

  public V resultNow() {
    if (status == 0) throw new IllegalStateException("Task has not completed");
    if (status == CANCELLED) throw new IllegalStateException("Task was cancelled");
    if (status == EXCEPTIONAL) throw new IllegalStateException("Task completed with exception");
    return getRawResult();
  }
  public Throwable exceptionNow() {
    if (status == 0) throw new IllegalStateException("Task has not completed");
    if (status == CANCELLED) throw new IllegalStateException("Task was cancelled");
    if (status != EXCEPTIONAL) throw new IllegalStateException("Task completed with a result");
    return exception;
  }
  public Future$State state() {
    switch (status) {
      case 0: return Future$State.RUNNING;
      case NORMAL: return Future$State.SUCCESS;
      case CANCELLED: return Future$State.CANCELLED;
      default: return Future$State.FAILED;
    }
  }

  public final short getForkJoinTaskTag() { return tag; }
  public final synchronized short setForkJoinTaskTag(short newValue) { short o = tag; tag = newValue; return o; }
  public final synchronized boolean compareAndSetForkJoinTaskTag(short expect, short update) { if (tag != expect) return false; tag = update; return true; }

  public static ForkJoinPool getPool() { ForkJoinWorkerThread w = worker(); return w == null ? null : w.pool; }
  public static boolean inForkJoinPool() { return worker() != null; }
  public static int getQueuedTaskCount() { ForkJoinWorkerThread w = worker(); return w == null ? 0 : w.pool.queued(); }
  public static int getSurplusQueuedTaskCount() { ForkJoinWorkerThread w = worker(); return w == null ? 0 : Math.max(0, w.pool.queued() - w.pool.idleCount()); }
  public static void helpQuiesce() { ForkJoinWorkerThread w = worker(); if (w != null) for (ForkJoinTask<?> t; (t = w.pool.poll()) != null; ) t.doExec(); }
  protected static ForkJoinTask<?> peekNextLocalTask() { ForkJoinWorkerThread w = worker(); return w == null ? null : w.pool.peek(); }
  protected static ForkJoinTask<?> pollNextLocalTask() { ForkJoinWorkerThread w = worker(); return w == null ? null : w.pool.poll(); }
  protected static ForkJoinTask<?> pollTask() { return pollNextLocalTask(); }

  public static void invokeAll(ForkJoinTask<?> t1, ForkJoinTask<?> t2) {
    t2.fork();
    t1.quietlyInvoke();
    t2.quietlyJoin();
    t1.join(); t2.join();
  }
  public static void invokeAll(ForkJoinTask<?>... tasks) {
    for (int i = tasks.length - 1; i > 0; i--) tasks[i].fork();
    if (tasks.length > 0) tasks[0].quietlyInvoke();
    for (int i = 1; i < tasks.length; i++) tasks[i].quietlyJoin();
    for (ForkJoinTask<?> t : tasks) t.join();
  }
  public static <T extends ForkJoinTask<?>> Collection<T> invokeAll(Collection<T> tasks) {
    invokeAll(tasks.toArray(new ForkJoinTask<?>[0]));
    return tasks;
  }

  public static ForkJoinTask<?> adapt(Runnable runnable) { return new Adapted<Object>(() -> { runnable.run(); return null; }, false); }
  public static <T> ForkJoinTask<T> adapt(Runnable runnable, T result) { return new Adapted<T>(() -> { runnable.run(); return result; }, false); }
  public static <T> ForkJoinTask<T> adapt(Callable<? extends T> callable) { return new Adapted<T>(callable, true); }
  public static <T> ForkJoinTask<T> adaptInterruptible(Callable<? extends T> callable) { return new Adapted<T>(callable, true); }

  static final class Adapted<T> extends ForkJoinTask<T> implements RunnableFuture<T> {
    final Callable<? extends T> c; final boolean wrapChecked; T result;
    Adapted(Callable<? extends T> c, boolean wrapChecked) { this.c = java.util.Objects.requireNonNull(c); this.wrapChecked = wrapChecked; }
    public T getRawResult() { return result; }
    protected void setRawResult(T v) { result = v; }
    protected boolean exec() {
      try { result = c.call(); return true; }
      catch (RuntimeException | Error e) { throw e; }
      catch (Exception e) { throw new RuntimeException(e); }
    }
    public void run() { invoke(); }
  }
}
