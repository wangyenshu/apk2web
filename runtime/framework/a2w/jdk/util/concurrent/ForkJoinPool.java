package a2w.jdk.util.concurrent;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * java.util.concurrent.ForkJoinPool: one queue of tasks served by up to {@code parallelism} worker threads (made on
 * demand by the factory). Submissions queue at the end; forks by a worker at the front (at the end in async mode).
 * Threads are green threads on one JavaScript thread, so the workers interleave rather than run in parallel; the
 * common pool cannot be shut down.
 */
public class ForkJoinPool extends AbstractExecutorService {
  public interface ForkJoinWorkerThreadFactory { ForkJoinWorkerThread newThread(ForkJoinPool pool); }
  public interface ManagedBlocker { boolean block() throws InterruptedException; boolean isReleasable(); }

  public static final ForkJoinWorkerThreadFactory defaultForkJoinWorkerThreadFactory = pool -> new ForkJoinWorkerThread(pool);
  private static final int MAX_CAP = 0x7fff;
  private static ForkJoinPool common;
  private static int poolIds;

  private final int id = ++poolIds;
  private int parallelism;
  private final ForkJoinWorkerThreadFactory factory;
  private final Thread.UncaughtExceptionHandler handler;
  private final boolean asyncMode, isCommon;
  private final ArrayDeque<ForkJoinTask<?>> queue = new ArrayDeque<>();
  private final ArrayList<ForkJoinWorkerThread> workers = new ArrayList<>();
  private int idle, active, nextIndex;
  private boolean shutdown, terminated;

  public ForkJoinPool() { this(Math.min(MAX_CAP, Runtime.getRuntime().availableProcessors()), defaultForkJoinWorkerThreadFactory, null, false); }
  public ForkJoinPool(int parallelism) { this(parallelism, defaultForkJoinWorkerThreadFactory, null, false); }
  public ForkJoinPool(int parallelism, ForkJoinWorkerThreadFactory factory, Thread.UncaughtExceptionHandler handler, boolean asyncMode) {
    this(parallelism, factory, handler, asyncMode, false);
  }
  public ForkJoinPool(int parallelism, ForkJoinWorkerThreadFactory factory, Thread.UncaughtExceptionHandler handler, boolean asyncMode,
      int corePoolSize, int maximumPoolSize, int minimumRunnable, java.util.function.Predicate<? super ForkJoinPool> saturate, long keepAliveTime, TimeUnit unit) {
    this(parallelism, factory, handler, asyncMode, false);
    if (maximumPoolSize < parallelism || keepAliveTime <= 0L) throw new IllegalArgumentException();
  }
  private ForkJoinPool(int parallelism, ForkJoinWorkerThreadFactory factory, Thread.UncaughtExceptionHandler handler, boolean asyncMode, boolean isCommon) {
    if (parallelism <= 0 || parallelism > MAX_CAP) throw new IllegalArgumentException();
    this.parallelism = parallelism;
    this.factory = java.util.Objects.requireNonNull(factory);
    this.handler = handler;
    this.asyncMode = asyncMode;
    this.isCommon = isCommon;
  }

  public static ForkJoinPool commonPool() {
    if (common == null) common = new ForkJoinPool(Math.max(1, Runtime.getRuntime().availableProcessors() - 1), defaultForkJoinWorkerThreadFactory, null, false, true);
    return common;
  }
  public static int getCommonPoolParallelism() { return commonPool().parallelism; }

  // ---- the queue
  synchronized void push(ForkJoinTask<?> t, boolean local) {
    if (shutdown && !isCommon) throw new RejectedExecutionException();
    t.queuedIn = this;
    if (local && !asyncMode) queue.addFirst(t); else queue.addLast(t);
    if (idle > 0) notifyAll();
    else if (workers.size() < parallelism) addWorker();
  }
  synchronized boolean remove(ForkJoinTask<?> t) { return queue.removeFirstOccurrence(t); }
  synchronized ForkJoinTask<?> poll() { return queue.pollFirst(); }
  synchronized ForkJoinTask<?> peek() { return queue.peekFirst(); }
  synchronized int queued() { return queue.size(); }
  synchronized int idleCount() { return idle; }

  private void addWorker() {
    ForkJoinWorkerThread w;
    try { w = factory.newThread(this); } catch (Throwable e) { return; }
    if (w == null) return;
    w.pool = this;
    w.index = nextIndex++;
    if (handler != null) w.setUncaughtExceptionHandler(handler);
    w.setDaemon(true);
    workers.add(w);
    w.start();
  }
  /** a worker's loop: runs queued tasks until the pool shuts down */
  void runWorker(ForkJoinWorkerThread w) {
    for (;;) {
      ForkJoinTask<?> t;
      synchronized (this) {
        while ((t = queue.pollFirst()) == null) {
          if (shutdown && !isCommon) { workers.remove(w); if (workers.isEmpty()) terminated = true; notifyAll(); return; }
          idle++;
          try { wait(); } catch (InterruptedException e) { /* keep serving */ } finally { idle--; }
        }
        active++;
      }
      try { t.doExec(); }
      finally { synchronized (this) { active--; if (active == 0 && queue.isEmpty()) notifyAll(); } }
    }
  }
  synchronized void workerExited(ForkJoinWorkerThread w) { workers.remove(w); if (shutdown && workers.isEmpty()) terminated = true; notifyAll(); }

  // ---- submission
  public <T> T invoke(ForkJoinTask<T> task) {
    java.util.Objects.requireNonNull(task);
    ForkJoinWorkerThread w = ForkJoinTask.worker();
    if (w != null && w.pool == this) return task.invoke();
    push(task, false);
    return task.join();
  }
  public void execute(ForkJoinTask<?> task) { push(java.util.Objects.requireNonNull(task), false); }
  public void execute(Runnable task) {
    java.util.Objects.requireNonNull(task);
    push(task instanceof ForkJoinTask ? (ForkJoinTask<?>) task : new ForkJoinTask.Adapted<Object>(() -> { task.run(); return null; }, false), false);
  }
  public <T> ForkJoinTask<T> submit(ForkJoinTask<T> task) { push(java.util.Objects.requireNonNull(task), false); return task; }
  public <T> ForkJoinTask<T> submit(Callable<T> task) { ForkJoinTask<T> t = ForkJoinTask.adapt(task); push(t, false); return t; }
  public <T> ForkJoinTask<T> submit(Runnable task, T result) { ForkJoinTask<T> t = ForkJoinTask.adapt(task, result); push(t, false); return t; }
  public ForkJoinTask<?> submit(Runnable task) {
    java.util.Objects.requireNonNull(task);
    ForkJoinTask<?> t = task instanceof ForkJoinTask ? (ForkJoinTask<?>) task : ForkJoinTask.adapt(task);
    push(t, false);
    return t;
  }
  public <T> ForkJoinTask<T> externalSubmit(ForkJoinTask<T> task) { return submit(task); }
  public <T> ForkJoinTask<T> lazySubmit(ForkJoinTask<T> task) { return submit(task); }
  public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) {
    ArrayList<Future<T>> fs = new ArrayList<>(tasks.size());
    for (Callable<T> c : tasks) { ForkJoinTask<T> t = ForkJoinTask.adapt(c); fs.add(t); push(t, false); }
    for (Future<T> f : fs) ((ForkJoinTask<?>) f).quietlyJoin();
    return fs;
  }
  public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
    long deadline = System.currentTimeMillis() + unit.toMillis(timeout);
    ArrayList<Future<T>> fs = new ArrayList<>(tasks.size());
    for (Callable<T> c : tasks) { ForkJoinTask<T> t = ForkJoinTask.adapt(c); fs.add(t); push(t, false); }
    for (Future<T> f : fs) {
      long left = deadline - System.currentTimeMillis();
      if (left <= 0 || !((ForkJoinTask<?>) f).quietlyJoin(left, TimeUnit.MILLISECONDS)) { for (Future<T> g : fs) g.cancel(true); break; }
    }
    return fs;
  }
  protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) { return new ForkJoinTask.Adapted<T>(() -> { runnable.run(); return value; }, false); }
  protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) { return new ForkJoinTask.Adapted<T>(callable, true); }
  protected synchronized ForkJoinTask<?> pollSubmission() { return queue.pollLast(); }
  protected synchronized int drainTasksTo(Collection<? super ForkJoinTask<?>> c) {
    int n = 0;
    for (ForkJoinTask<?> t; (t = queue.pollFirst()) != null; n++) c.add(t);
    return n;
  }

  // ---- state
  public int getParallelism() { return parallelism; }
  public synchronized int setParallelism(int size) {
    if (size < 1 || size > MAX_CAP) throw new IllegalArgumentException();
    if (isCommon) throw new UnsupportedOperationException();
    int old = parallelism; parallelism = size; return old;
  }
  public int getPoolSize() { synchronized (this) { return workers.size(); } }
  public boolean getAsyncMode() { return asyncMode; }
  public ForkJoinWorkerThreadFactory getFactory() { return factory; }
  public Thread.UncaughtExceptionHandler getUncaughtExceptionHandler() { return handler; }
  public synchronized int getRunningThreadCount() { return active; }
  public synchronized int getActiveThreadCount() { return active; }
  public synchronized boolean isQuiescent() { return active == 0 && queue.isEmpty(); }
  public long getStealCount() { return 0; }
  public synchronized long getQueuedTaskCount() { return queue.size(); }
  public synchronized int getQueuedSubmissionCount() { return queue.size(); }
  public synchronized boolean hasQueuedSubmissions() { return !queue.isEmpty(); }
  public boolean awaitQuiescence(long timeout, TimeUnit unit) {
    ForkJoinWorkerThread w = ForkJoinTask.worker();
    if (w != null && w.pool == this) { ForkJoinTask.helpQuiesce(); return true; }
    long deadline = System.currentTimeMillis() + unit.toMillis(timeout);
    synchronized (this) {
      while (active != 0 || !queue.isEmpty()) {
        long left = deadline - System.currentTimeMillis();
        if (left <= 0) return false;
        try { wait(left); } catch (InterruptedException e) { Thread.currentThread().interrupt(); return false; }
      }
    }
    return true;
  }
  public static void managedBlock(ManagedBlocker blocker) throws InterruptedException {
    java.util.Objects.requireNonNull(blocker);
    while (!blocker.isReleasable()) if (blocker.block()) break;
  }

  // ---- shutdown
  public synchronized void shutdown() {
    if (isCommon) return;
    shutdown = true;
    if (workers.isEmpty() && queue.isEmpty()) terminated = true;
    notifyAll();
  }
  public List<Runnable> shutdownNow() {
    if (isCommon) return Collections.emptyList();
    List<ForkJoinTask<?>> dropped = new ArrayList<>();
    synchronized (this) { drainTasksTo(dropped); }
    for (ForkJoinTask<?> t : dropped) t.cancel(false);
    shutdown();
    return Collections.emptyList();
  }
  public synchronized boolean isShutdown() { return shutdown; }
  public synchronized boolean isTerminated() { return terminated; }
  public synchronized boolean isTerminating() { return shutdown && !terminated; }
  public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
    if (isCommon) { awaitQuiescence(timeout, unit); return false; }
    long deadline = System.currentTimeMillis() + unit.toMillis(timeout);
    synchronized (this) {
      while (!terminated) {
        long left = deadline - System.currentTimeMillis();
        if (left <= 0) return false;
        wait(left);
      }
    }
    return true;
  }
  public void close() {
    if (isCommon) return;
    shutdown();
    boolean interrupted = false;
    while (!isTerminated()) { try { awaitTermination(1, TimeUnit.DAYS); } catch (InterruptedException e) { interrupted = true; } }
    if (interrupted) Thread.currentThread().interrupt();
  }
  public synchronized String toString() {
    String state = terminated ? "Terminated" : shutdown ? "Shutting down" : "Running";
    return "java.util.concurrent.ForkJoinPool@" + Integer.toHexString(hashCode()) + "[" + state + ", parallelism = " + parallelism + ", size = " + workers.size()
        + ", active = " + active + ", running = " + active + ", steals = 0, tasks = " + queue.size() + ", submissions = 0]";
  }
}
