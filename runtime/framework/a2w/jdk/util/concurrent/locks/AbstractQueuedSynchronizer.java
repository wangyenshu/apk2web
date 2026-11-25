package a2w.jdk.util.concurrent.locks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * java.util.concurrent.locks.AbstractQueuedSynchronizer. Waiting threads queue in arrival order and wait on one monitor; the first
 * queued thread retries the acquire after each release (new arrivals may barge, as with the JDK's non-fair
 * acquires). Conditions wait on the same monitor.
 */
public abstract class AbstractQueuedSynchronizer extends AbstractOwnableSynchronizer implements java.io.Serializable {
  private volatile int state;
  private final transient ArrayList<Node> queue = new ArrayList<>();
  private transient long signals;
  private transient boolean contended;

  static final class Node {
    final Thread thread; final boolean shared;
    Node(Thread thread, boolean shared) { this.thread = thread; this.shared = shared; }
  }

  protected AbstractQueuedSynchronizer() {}

  protected final int getState() { return state; }
  protected final void setState(int newState) { state = newState; }
  /** (framework code is not preempted, so the test and the store happen together) */
  protected final boolean compareAndSetState(int expect, int update) { if (state != expect) return false; state = update; return true; }

  protected boolean tryAcquire(int arg) { throw new UnsupportedOperationException(); }
  protected boolean tryRelease(int arg) { throw new UnsupportedOperationException(); }
  protected int tryAcquireShared(int arg) { throw new UnsupportedOperationException(); }
  protected boolean tryReleaseShared(int arg) { throw new UnsupportedOperationException(); }
  protected boolean isHeldExclusively() { throw new UnsupportedOperationException(); }

  private void wakeAll() { synchronized (queue) { signals++; queue.notifyAll(); } }
  private boolean tryOnce(int arg, boolean shared) { return shared ? tryAcquireShared(arg) >= 0 : tryAcquire(arg); }

  /** queues the current thread until it acquires; false on timeout (deadline in ms, 0: none) */
  final boolean acquireQueued(int arg, boolean shared, boolean interruptible, long deadline) throws InterruptedException {
    Node node = new Node(Thread.currentThread(), shared);
    synchronized (queue) { queue.add(node); contended = true; }
    boolean interrupted = false;
    try {
      for (;;) {
        long seen; boolean first;
        synchronized (queue) { seen = signals; first = queue.get(0) == node; }
        if (first && tryOnce(arg, shared)) {
          synchronized (queue) { queue.remove(node); signals++; queue.notifyAll(); }
          node = null;
          return true;
        }
        synchronized (queue) {
          if (signals == seen) {
            long left = deadline == 0 ? 0 : deadline - System.currentTimeMillis();
            if (deadline != 0 && left <= 0) return false;
            try { queue.wait(left); }
            catch (InterruptedException e) { if (interruptible) throw e; interrupted = true; }
          }
        }
      }
    } finally {
      if (node != null) synchronized (queue) { queue.remove(node); signals++; queue.notifyAll(); }
      if (interrupted) Thread.currentThread().interrupt();
    }
  }
  private static long deadline(long nanos) { return System.currentTimeMillis() + Math.max(1, nanos / 1_000_000); }

  public final void acquire(int arg) {
    if (tryAcquire(arg)) return;
    try { acquireQueued(arg, false, false, 0); } catch (InterruptedException e) { /* not interruptible */ }
  }
  public final void acquireInterruptibly(int arg) throws InterruptedException {
    if (Thread.interrupted()) throw new InterruptedException();
    if (!tryAcquire(arg)) acquireQueued(arg, false, true, 0);
  }
  public final boolean tryAcquireNanos(int arg, long nanosTimeout) throws InterruptedException {
    if (Thread.interrupted()) throw new InterruptedException();
    if (tryAcquire(arg)) return true;
    return nanosTimeout > 0 && acquireQueued(arg, false, true, deadline(nanosTimeout));
  }
  public final boolean release(int arg) { if (!tryRelease(arg)) return false; wakeAll(); return true; }
  public final void acquireShared(int arg) {
    if (tryAcquireShared(arg) >= 0) return;
    try { acquireQueued(arg, true, false, 0); } catch (InterruptedException e) { /* not interruptible */ }
  }
  public final void acquireSharedInterruptibly(int arg) throws InterruptedException {
    if (Thread.interrupted()) throw new InterruptedException();
    if (tryAcquireShared(arg) < 0) acquireQueued(arg, true, true, 0);
  }
  public final boolean tryAcquireSharedNanos(int arg, long nanosTimeout) throws InterruptedException {
    if (Thread.interrupted()) throw new InterruptedException();
    if (tryAcquireShared(arg) >= 0) return true;
    return nanosTimeout > 0 && acquireQueued(arg, true, true, deadline(nanosTimeout));
  }
  public final boolean releaseShared(int arg) { if (!tryReleaseShared(arg)) return false; wakeAll(); return true; }

  public final boolean hasQueuedThreads() { synchronized (queue) { return !queue.isEmpty(); } }
  public final boolean hasContended() { synchronized (queue) { return contended; } }
  public final Thread getFirstQueuedThread() { synchronized (queue) { return queue.isEmpty() ? null : queue.get(0).thread; } }
  public final boolean isQueued(Thread thread) {
    if (thread == null) throw new NullPointerException();
    synchronized (queue) { for (Node n : queue) if (n.thread == thread) return true; }
    return false;
  }
  public final boolean hasQueuedPredecessors() { synchronized (queue) { return !queue.isEmpty() && queue.get(0).thread != Thread.currentThread(); } }
  final boolean apparentlyFirstQueuedIsExclusive() { synchronized (queue) { return !queue.isEmpty() && !queue.get(0).shared; } }
  public final int getQueueLength() { synchronized (queue) { return queue.size(); } }
  private Collection<Thread> threads(int which) {
    ArrayList<Thread> l = new ArrayList<>();
    synchronized (queue) { for (Node n : queue) if (which == 0 || (which == 1) == !n.shared) l.add(n.thread); }
    return l;
  }
  public final Collection<Thread> getQueuedThreads() { return threads(0); }
  public final Collection<Thread> getExclusiveQueuedThreads() { return threads(1); }
  public final Collection<Thread> getSharedQueuedThreads() { return threads(2); }
  public String toString() { return super.toString() + "[State = " + getState() + ", " + (hasQueuedThreads() ? "non" : "") + "empty queue]"; }

  public final boolean owns(ConditionObject condition) { return condition.owner() == this; }
  private ConditionObject check(ConditionObject c) { if (!owns(c)) throw new IllegalArgumentException("Not owner"); return c; }
  public final boolean hasWaiters(ConditionObject condition) { return check(condition).hasWaiters(); }
  public final int getWaitQueueLength(ConditionObject condition) { return check(condition).getWaitQueueLength(); }
  public final Collection<Thread> getWaitingThreads(ConditionObject condition) { return check(condition).getWaitingThreads(); }

  /** java.util.concurrent.locks.AbstractQueuedSynchronizer.ConditionObject: waits release the synchronizer fully and reacquire it */
  public class ConditionObject implements Condition, java.io.Serializable {
    private final transient ArrayList<Waiter> waiters = new ArrayList<>();
    final class Waiter { final Thread thread = Thread.currentThread(); boolean signalled; }
    public ConditionObject() {}
    final AbstractQueuedSynchronizer owner() { return AbstractQueuedSynchronizer.this; }
    private void held() { if (!isHeldExclusively()) throw new IllegalMonitorStateException(); }
    public final void signal() {
      held();
      synchronized (queue) {
        if (!waiters.isEmpty()) { waiters.remove(0).signalled = true; signals++; queue.notifyAll(); }
      }
    }
    public final void signalAll() {
      held();
      synchronized (queue) {
        for (Waiter w : waiters) w.signalled = true;
        waiters.clear(); signals++; queue.notifyAll();
      }
    }
    /** waits until signalled, interrupted or the deadline (ms, 0: none); true when signalled */
    private boolean awaitImpl(boolean interruptible, long deadline) throws InterruptedException {
      if (interruptible && Thread.interrupted()) throw new InterruptedException();
      held();
      Waiter w = new Waiter();
      synchronized (queue) { waiters.add(w); }
      int saved = getState();
      if (!release(saved)) { synchronized (queue) { waiters.remove(w); } throw new IllegalMonitorStateException(); }
      boolean interrupted = false;
      synchronized (queue) {
        while (!w.signalled) {
          long left = deadline == 0 ? 0 : deadline - System.currentTimeMillis();
          if (deadline != 0 && left <= 0) break;
          try { queue.wait(left); } catch (InterruptedException e) { interrupted = true; if (interruptible) break; }
        }
        if (!w.signalled) waiters.remove(w);
      }
      if (!tryAcquire(saved)) acquireQueued(saved, false, false, 0);
      if (interrupted) { if (interruptible) throw new InterruptedException(); Thread.currentThread().interrupt(); }
      return w.signalled;
    }
    public final void await() throws InterruptedException { awaitImpl(true, 0); }
    public final void awaitUninterruptibly() { try { awaitImpl(false, 0); } catch (InterruptedException e) { /* not thrown */ } }
    public final long awaitNanos(long nanosTimeout) throws InterruptedException {
      long end = System.nanoTime() + nanosTimeout;
      if (nanosTimeout <= 0) { if (Thread.interrupted()) throw new InterruptedException(); return nanosTimeout; }
      awaitImpl(true, deadline(nanosTimeout));
      return end - System.nanoTime();
    }
    public final boolean await(long time, TimeUnit unit) throws InterruptedException {
      long nanos = unit.toNanos(time);
      if (nanos <= 0) { if (Thread.interrupted()) throw new InterruptedException(); return false; }
      return awaitImpl(true, deadline(nanos));
    }
    public final boolean awaitUntil(Date deadline) throws InterruptedException {
      long d = deadline.getTime();
      if (d <= System.currentTimeMillis()) { if (Thread.interrupted()) throw new InterruptedException(); return false; }
      return awaitImpl(true, d);
    }
    protected final boolean hasWaiters() { held(); synchronized (queue) { return !waiters.isEmpty(); } }
    protected final int getWaitQueueLength() { held(); synchronized (queue) { return waiters.size(); } }
    protected final Collection<Thread> getWaitingThreads() {
      held();
      ArrayList<Thread> l = new ArrayList<>();
      synchronized (queue) { for (Waiter w : waiters) l.add(w.thread); }
      return l;
    }
  }
}
