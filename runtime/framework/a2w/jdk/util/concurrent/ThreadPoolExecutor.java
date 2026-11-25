package a2w.jdk.util.concurrent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * java.util.concurrent.ThreadPoolExecutor on real (TeaVM) threads, with the JDK's policy: start a core thread per task
 * until there are corePoolSize of them, then queue, and when the queue refuses start more threads up to maximumPoolSize,
 * then hand the task to the RejectedExecutionHandler. Threads above the core size (or all, with allowCoreThreadTimeOut)
 * stop after keepAliveTime idle. Fairness and the exact JDK lock-free bookkeeping are not reproduced.
 */
public class ThreadPoolExecutor extends AbstractExecutorService {
  private final Object m = new Object();
  private final BlockingQueue<Runnable> queue;
  private final HashSet<Worker> workers = new HashSet<>();
  private volatile int core, max;
  private volatile long keepAliveMs;
  private volatile boolean coreTimeout;
  private volatile ThreadFactory factory;
  private volatile RejectedExecutionHandler handler;
  private volatile int runState;   // 0 running, 1 shutdown, 2 stop, 3 terminated
  private int active, largest;
  private long completed;

  public ThreadPoolExecutor(int core, int max, long keepAlive, TimeUnit unit, BlockingQueue<Runnable> q) { this(core, max, keepAlive, unit, q, null, null); }
  public ThreadPoolExecutor(int core, int max, long keepAlive, TimeUnit unit, BlockingQueue<Runnable> q, ThreadFactory f) { this(core, max, keepAlive, unit, q, f, null); }
  public ThreadPoolExecutor(int core, int max, long keepAlive, TimeUnit unit, BlockingQueue<Runnable> q, RejectedExecutionHandler h) { this(core, max, keepAlive, unit, q, null, h); }
  public ThreadPoolExecutor(int core, int max, long keepAlive, TimeUnit unit, BlockingQueue<Runnable> q, ThreadFactory f, RejectedExecutionHandler h) {
    if (core < 0 || max <= 0 || max < core || keepAlive < 0) throw new IllegalArgumentException();
    if (q == null) throw new NullPointerException();
    this.core = core; this.max = max; keepAliveMs = unit.toMillis(keepAlive); queue = q;
    factory = f != null ? f : Executors.defaultThreadFactory();
    handler = h != null ? h : new AbortPolicy();
  }

  final class Worker implements Runnable {
    Runnable first;
    Thread thread;
    Worker(Runnable first) { this.first = first; }
    @Override public void run() { runWorker(this); }
  }

  private boolean addWorker(Runnable first, boolean asCore) {
    Worker w;
    synchronized (m) {
      if (runState >= 2 || (runState == 1 && (first != null || queue.isEmpty()))) return false;
      if (workers.size() >= (asCore ? core : max)) return false;
      w = new Worker(first);
      Thread t = factory.newThread(w);
      if (t == null) return false;
      w.thread = t;
      workers.add(w);
      if (workers.size() > largest) largest = workers.size();
    }
    try { w.thread.start(); }
    catch (Throwable e) { synchronized (m) { workers.remove(w); } return false; }
    return true;
  }

  @Override public void execute(Runnable r) {
    if (r == null) throw new NullPointerException();
    int n;
    synchronized (m) { n = workers.size(); }
    if (runState == 0 && n < core && addWorker(r, true)) return;
    if (runState == 0 && queue.offer(r)) {
      if (runState != 0 && remove(r)) { reject(r); return; }
      synchronized (m) { n = workers.size(); }
      if (n == 0) addWorker(null, false);
      return;
    }
    if (!addWorker(r, false)) reject(r);
  }
  final void reject(Runnable r) { handler.rejectedExecution(r, this); }
  /** makes sure a queued task has a thread to run it (ScheduledThreadPoolExecutor queues directly) */
  final void ensureWorker() {
    int n;
    synchronized (m) { n = workers.size(); }
    if (n < core) addWorker(null, true);
    else if (n == 0) addWorker(null, false);
  }
  final BlockingQueue<Runnable> queue() { return queue; }

  private Runnable getTask() {
    boolean timedOut = false;
    while (true) {
      if (runState >= 2 || (runState == 1 && queue.isEmpty())) return null;
      boolean timed;
      synchronized (m) {
        timed = coreTimeout || workers.size() > core;
        if ((workers.size() > max || (timed && timedOut)) && (workers.size() > 1 || queue.isEmpty())) return null;
      }
      try {
        // an untimed worker still wakes up now and then, so that shutdown() is noticed whether or not interrupts reach it
        long wait = timed ? Math.max(1, keepAliveMs) : 1000;
        Runnable r = queue.poll(wait, TimeUnit.MILLISECONDS);
        if (r != null) return r;
        timedOut = timed;
      } catch (InterruptedException e) {
        timedOut = false;
      }
    }
  }

  final void runWorker(Worker w) {
    Thread t = Thread.currentThread();
    Runnable task = w.first;
    w.first = null;
    try {
      while (task != null || (task = getTask()) != null) {
        synchronized (m) { active++; }
        Throwable thrown = null;
        try {
          Thread.interrupted();
          beforeExecute(t, task);
          try { task.run(); }
          catch (Throwable e) { thrown = e; android.util.Log.e("apk2web", "uncaught exception in an executor task on " + t.getName(), e); }
          afterExecute(task, thrown);
        } catch (Throwable e) {
          android.util.Log.e("apk2web", "uncaught exception in an executor hook", e);
        } finally {
          task = null;
          synchronized (m) { active--; completed++; }
        }
      }
    } finally {
      boolean last;
      synchronized (m) { workers.remove(w); last = workers.isEmpty(); }
      if (runState == 0 && !queue.isEmpty()) ensureWorker();
      if (last) tryTerminate();
    }
  }

  private void tryTerminate() {
    synchronized (m) {
      if (runState == 0 || runState == 3) return;
      if (runState == 1 && !queue.isEmpty()) return;
      if (!workers.isEmpty()) return;
      runState = 3;
    }
    try { terminated(); } catch (Throwable e) { android.util.Log.e("apk2web", "terminated() threw", e); }
    synchronized (m) { m.notifyAll(); }
  }

  protected void beforeExecute(Thread t, Runnable r) {}
  protected void afterExecute(Runnable r, Throwable t) {}
  protected void terminated() {}
  void onShutdown() {}

  @Override public void shutdown() {
    synchronized (m) { if (runState < 1) runState = 1; }
    onShutdown();
    interruptIdle();
    tryTerminate();
  }
  @Override public List<Runnable> shutdownNow() {
    List<Runnable> left = new ArrayList<>();
    List<Thread> ts = new ArrayList<>();
    synchronized (m) {
      if (runState < 2) runState = 2;
      for (Worker w : workers) ts.add(w.thread);
    }
    queue.drainTo(left);
    for (Thread t : ts) { try { t.interrupt(); } catch (Throwable e) { /* ignore */ } }
    tryTerminate();
    return left;
  }
  private void interruptIdle() {
    // idle workers poll with a timeout and see the new state; interrupting a busy task would be wrong
  }
  @Override public boolean isShutdown() { return runState >= 1; }
  public boolean isTerminating() { return runState >= 1 && runState < 3; }
  @Override public boolean isTerminated() { return runState == 3; }
  @Override public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
    long end = System.currentTimeMillis() + Math.max(0, unit.toMillis(timeout));
    synchronized (m) {
      while (runState != 3) {
        long left = end - System.currentTimeMillis();
        if (left <= 0) return false;
        m.wait(Math.min(left, 100));
        if (runState >= 1 && workers.isEmpty() && (runState == 2 || queue.isEmpty())) { runState = 3; m.notifyAll(); }
      }
      return true;
    }
  }
  @Override protected void finalize() { shutdown(); }

  public int getCorePoolSize() { return core; }
  public void setCorePoolSize(int n) {
    if (n < 0) throw new IllegalArgumentException();
    core = n;
    if (max < n) max = n;
    int k = Math.min(n, queue.size());
    while (k-- > 0 && addWorker(null, true)) { /* start threads for queued work */ }
  }
  public int getMaximumPoolSize() { return max; }
  public void setMaximumPoolSize(int n) { if (n <= 0 || n < core) throw new IllegalArgumentException(); max = n; }
  public boolean allowsCoreThreadTimeOut() { return coreTimeout; }
  public void allowCoreThreadTimeOut(boolean b) { if (b && keepAliveMs <= 0) throw new IllegalArgumentException("Core threads must have nonzero keep alive times"); coreTimeout = b; }
  public void setKeepAliveTime(long t, TimeUnit u) { if (t < 0) throw new IllegalArgumentException(); keepAliveMs = u.toMillis(t); }
  public long getKeepAliveTime(TimeUnit u) { return u.convert(keepAliveMs, TimeUnit.MILLISECONDS); }
  public int getActiveCount() { synchronized (m) { return active; } }
  public long getTaskCount() { synchronized (m) { return completed + active + queue.size(); } }
  public long getCompletedTaskCount() { synchronized (m) { return completed; } }
  public int getPoolSize() { synchronized (m) { return workers.size(); } }
  public int getLargestPoolSize() { synchronized (m) { return largest; } }
  public boolean prestartCoreThread() { return addWorker(null, true); }
  public int prestartAllCoreThreads() { int n = 0; while (addWorker(null, true)) n++; return n; }
  public void setThreadFactory(ThreadFactory f) { if (f == null) throw new NullPointerException(); factory = f; }
  public ThreadFactory getThreadFactory() { return factory; }
  public void setRejectedExecutionHandler(RejectedExecutionHandler h) { if (h == null) throw new NullPointerException(); handler = h; }
  public RejectedExecutionHandler getRejectedExecutionHandler() { return handler; }
  public BlockingQueue<Runnable> getQueue() { return queue; }
  public boolean remove(Runnable r) { boolean b = queue.remove(r); tryTerminate(); return b; }
  public void purge() {
    for (Object o : queue.toArray()) if (o instanceof Future && ((Future<?>) o).isCancelled()) queue.remove(o);
  }
  @Override public String toString() {
    String s = runState == 0 ? "Running" : runState == 3 ? "Terminated" : "Shutting down";
    return super.toString() + "[" + s + ", pool size = " + getPoolSize() + ", active threads = " + getActiveCount()
        + ", queued tasks = " + queue.size() + ", completed tasks = " + getCompletedTaskCount() + "]";
  }

  public static class AbortPolicy implements RejectedExecutionHandler {
    public AbortPolicy() {}
    @Override public void rejectedExecution(Runnable r, ThreadPoolExecutor e) { throw new RejectedExecutionException("Task " + r + " rejected from " + e); }
  }
  public static class CallerRunsPolicy implements RejectedExecutionHandler {
    public CallerRunsPolicy() {}
    @Override public void rejectedExecution(Runnable r, ThreadPoolExecutor e) { if (!e.isShutdown()) r.run(); }
  }
  public static class DiscardPolicy implements RejectedExecutionHandler {
    public DiscardPolicy() {}
    @Override public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {}
  }
  public static class DiscardOldestPolicy implements RejectedExecutionHandler {
    public DiscardOldestPolicy() {}
    @Override public void rejectedExecution(Runnable r, ThreadPoolExecutor e) { if (!e.isShutdown()) { e.getQueue().poll(); e.execute(r); } }
  }
}
