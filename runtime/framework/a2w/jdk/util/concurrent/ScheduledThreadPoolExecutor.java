package a2w.jdk.util.concurrent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Delayed and periodic tasks on a pool of corePoolSize threads (as in the JDK, the pool never grows past its core size).
 * The work queue hands a task out only once it is due.
 */
public class ScheduledThreadPoolExecutor extends ThreadPoolExecutor implements ScheduledExecutorService {
  private volatile boolean continuePeriodic, delayedAfterShutdown = true, removeOnCancel;
  private static long sequencer;

  public ScheduledThreadPoolExecutor(int core) { super(core, Integer.MAX_VALUE, 10, TimeUnit.MILLISECONDS, new DelayedWorkQueue()); }
  public ScheduledThreadPoolExecutor(int core, ThreadFactory f) { super(core, Integer.MAX_VALUE, 10, TimeUnit.MILLISECONDS, new DelayedWorkQueue(), f); }
  public ScheduledThreadPoolExecutor(int core, RejectedExecutionHandler h) { super(core, Integer.MAX_VALUE, 10, TimeUnit.MILLISECONDS, new DelayedWorkQueue(), h); }
  public ScheduledThreadPoolExecutor(int core, ThreadFactory f, RejectedExecutionHandler h) { super(core, Integer.MAX_VALUE, 10, TimeUnit.MILLISECONDS, new DelayedWorkQueue(), f, h); }

  static long now() { return System.nanoTime() / 1000000; }

  final class Task<V> extends FutureTask<V> implements RunnableScheduledFuture<V> {
    volatile long at;
    final long period;   // > 0 fixed rate, < 0 fixed delay, 0 once
    final long seq;
    Task(Callable<V> c, long delayMs, long periodMs) { super(c); at = now() + Math.max(0, delayMs); period = periodMs; synchronized (ScheduledThreadPoolExecutor.class) { seq = sequencer++; } }
    Task(Runnable r, V v, long delayMs, long periodMs) { this(Executors.callable(r, v), delayMs, periodMs); }
    @Override public long getDelay(TimeUnit u) { return u.convert(at - now(), TimeUnit.MILLISECONDS); }
    @Override public int compareTo(Object o) {
      if (o == this) return 0;
      if (o instanceof Task) { Task<?> t = (Task<?>) o; int c = Long.compare(at, t.at); return c != 0 ? c : Long.compare(seq, t.seq); }
      if (o instanceof Delayed) return Long.compare(getDelay(TimeUnit.MILLISECONDS), ((Delayed) o).getDelay(TimeUnit.MILLISECONDS));
      return 0;
    }
    @Override public boolean isPeriodic() { return period != 0; }
    @Override public void run() {
      if (isShutdown() && !(isPeriodic() ? continuePeriodic : delayedAfterShutdown)) { cancel(false); return; }
      if (!isPeriodic()) { super.run(); return; }
      if (runAndReset()) {
        at = period > 0 ? at + period : now() - period;
        if (!isShutdown() || continuePeriodic) { queue().add(this); ensureWorker(); }
      }
    }
    @Override public boolean cancel(boolean interrupt) {
      boolean c = super.cancel(interrupt);
      if (c && removeOnCancel) queue().remove(this);
      return c;
    }
  }

  protected <V> RunnableScheduledFuture<V> decorateTask(Runnable r, RunnableScheduledFuture<V> task) { return task; }
  protected <V> RunnableScheduledFuture<V> decorateTask(Callable<V> c, RunnableScheduledFuture<V> task) { return task; }

  private <V> RunnableScheduledFuture<V> delayed(RunnableScheduledFuture<V> t) {
    if (isShutdown()) { reject(t); return t; }
    queue().add(t);
    if (isShutdown() && !(t.isPeriodic() ? continuePeriodic : delayedAfterShutdown) && remove(t)) t.cancel(false);
    else ensureWorker();
    return t;
  }
  @Override public void execute(Runnable r) { schedule(r, 0, TimeUnit.MILLISECONDS); }
  @Override public Future<?> submit(Runnable r) { return schedule(r, 0, TimeUnit.MILLISECONDS); }
  @Override public <T> Future<T> submit(Runnable r, T v) { if (r == null) throw new NullPointerException(); return delayed(decorateTask(r, new Task<T>(r, v, 0, 0))); }
  @Override public <T> Future<T> submit(Callable<T> c) { return schedule(c, 0, TimeUnit.MILLISECONDS); }
  @Override public ScheduledFuture<?> schedule(Runnable r, long d, TimeUnit u) { if (r == null || u == null) throw new NullPointerException(); return delayed(decorateTask(r, new Task<Object>(r, null, u.toMillis(d), 0))); }
  @Override public <V> ScheduledFuture<V> schedule(Callable<V> c, long d, TimeUnit u) { if (c == null || u == null) throw new NullPointerException(); return delayed(decorateTask(c, new Task<>(c, u.toMillis(d), 0))); }
  @Override public ScheduledFuture<?> scheduleAtFixedRate(Runnable r, long i, long p, TimeUnit u) {
    if (r == null || u == null) throw new NullPointerException();
    if (p <= 0) throw new IllegalArgumentException();
    return delayed(decorateTask(r, new Task<Object>(r, null, u.toMillis(i), Math.max(1, u.toMillis(p)))));
  }
  @Override public ScheduledFuture<?> scheduleWithFixedDelay(Runnable r, long i, long d, TimeUnit u) {
    if (r == null || u == null) throw new NullPointerException();
    if (d <= 0) throw new IllegalArgumentException();
    return delayed(decorateTask(r, new Task<Object>(r, null, u.toMillis(i), -Math.max(1, u.toMillis(d)))));
  }
  @Override void onShutdown() {
    for (Object o : queue().toArray()) {
      if (!(o instanceof RunnableScheduledFuture)) continue;
      RunnableScheduledFuture<?> t = (RunnableScheduledFuture<?>) o;
      if ((t.isPeriodic() ? !continuePeriodic : !delayedAfterShutdown) || t.isCancelled()) { if (queue().remove(t)) t.cancel(false); }
    }
  }
  public void setContinueExistingPeriodicTasksAfterShutdownPolicy(boolean b) { continuePeriodic = b; }
  public boolean getContinueExistingPeriodicTasksAfterShutdownPolicy() { return continuePeriodic; }
  public void setExecuteExistingDelayedTasksAfterShutdownPolicy(boolean b) { delayedAfterShutdown = b; }
  public boolean getExecuteExistingDelayedTasksAfterShutdownPolicy() { return delayedAfterShutdown; }
  public void setRemoveOnCancelPolicy(boolean b) { removeOnCancel = b; }
  public boolean getRemoveOnCancelPolicy() { return removeOnCancel; }

  /** due tasks first; take/poll wait until the head is due (or a sooner task arrives) */
  static final class DelayedWorkQueue extends java.util.AbstractQueue<Runnable> implements java.util.concurrent.BlockingQueue<Runnable> {
    private final PriorityQueue<Runnable> q = new PriorityQueue<>(16, (a, b) -> ((Comparable<Object>) a).compareTo(b));
    private static long delayOf(Runnable r) { return r instanceof Delayed ? ((Delayed) r).getDelay(TimeUnit.MILLISECONDS) : 0; }
    @Override public synchronized boolean offer(Runnable r) {
      if (r == null) throw new NullPointerException();
      if (!(r instanceof Comparable)) { Runnable plain = r; r = new FutureTask<Object>(Executors.callable(plain)) { }; }
      q.add(r); notifyAll(); return true;
    }
    @Override public void put(Runnable r) { offer(r); }
    @Override public boolean offer(Runnable r, long t, TimeUnit u) { return offer(r); }
    @Override public synchronized Runnable poll() { Runnable h = q.peek(); return h == null || delayOf(h) > 0 ? null : q.poll(); }
    @Override public synchronized Runnable peek() { return q.peek(); }
    @Override public synchronized Runnable take() throws InterruptedException {
      while (true) {
        Runnable h = q.peek();
        if (h == null) { wait(); continue; }
        long d = delayOf(h);
        if (d <= 0) return q.poll();
        wait(d);
      }
    }
    @Override public synchronized Runnable poll(long t, TimeUnit u) throws InterruptedException {
      long end = System.currentTimeMillis() + Math.max(0, u.toMillis(t));
      while (true) {
        Runnable h = q.peek();
        long left = end - System.currentTimeMillis();
        if (h != null) {
          long d = delayOf(h);
          if (d <= 0) return q.poll();
          if (left <= 0) return null;
          wait(Math.min(d, left));
        } else {
          if (left <= 0) return null;
          wait(left);
        }
      }
    }
    @Override public synchronized boolean remove(Object o) { boolean r = q.remove(o); if (r) notifyAll(); return r; }
    @Override public synchronized int size() { return q.size(); }
    @Override public synchronized boolean isEmpty() { return q.isEmpty(); }
    @Override public synchronized Object[] toArray() { return q.toArray(); }
    @Override public synchronized void clear() { q.clear(); }
    @Override public synchronized Iterator<Runnable> iterator() { return new ArrayList<>(q).iterator(); }
    @Override public int remainingCapacity() { return Integer.MAX_VALUE; }
    @Override public int drainTo(Collection<? super Runnable> c) { return drainTo(c, Integer.MAX_VALUE); }
    @Override public synchronized int drainTo(Collection<? super Runnable> c, int max) {
      int n = 0;
      for (Runnable r; n < max && (r = poll()) != null; n++) c.add(r);
      return n;
    }
  }
}
