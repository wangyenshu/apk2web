package a2w.jdk.util.concurrent;

import java.util.Collection;
import java.util.concurrent.TimeUnit;
import a2w.jdk.util.concurrent.locks.AbstractQueuedSynchronizer;

/** java.util.concurrent.Semaphore on AbstractQueuedSynchronizer (the permits are the state), as the JDK's. */
public class Semaphore implements java.io.Serializable {
  private final Sync sync;

  abstract static class Sync extends AbstractQueuedSynchronizer {
    Sync(int permits) { setState(permits); }
    final int getPermits() { return getState(); }
    final int nonfairTryAcquireShared(int acquires) {
      for (;;) { int available = getState(), remaining = available - acquires; if (remaining < 0 || compareAndSetState(available, remaining)) return remaining; }
    }
    protected final boolean tryReleaseShared(int releases) {
      for (;;) {
        int current = getState(), next = current + releases;
        if (next < current) throw new Error("Maximum permit count exceeded");
        if (compareAndSetState(current, next)) return true;
      }
    }
    final void reducePermits(int reductions) {
      for (;;) {
        int current = getState(), next = current - reductions;
        if (next > current) throw new Error("Permit count underflow");
        if (compareAndSetState(current, next)) return;
      }
    }
    final int drainPermits() { for (;;) { int current = getState(); if (current == 0 || compareAndSetState(current, 0)) return current; } }
  }
  static final class NonfairSync extends Sync {
    NonfairSync(int permits) { super(permits); }
    protected int tryAcquireShared(int acquires) { return nonfairTryAcquireShared(acquires); }
  }
  static final class FairSync extends Sync {
    FairSync(int permits) { super(permits); }
    protected int tryAcquireShared(int acquires) { if (hasQueuedPredecessors()) return -1; return nonfairTryAcquireShared(acquires); }
  }

  public Semaphore(int permits) { sync = new NonfairSync(permits); }
  public Semaphore(int permits, boolean fair) { sync = fair ? new FairSync(permits) : new NonfairSync(permits); }
  public void acquire() throws InterruptedException { sync.acquireSharedInterruptibly(1); }
  public void acquireUninterruptibly() { sync.acquireShared(1); }
  public boolean tryAcquire() { return sync.nonfairTryAcquireShared(1) >= 0; }
  public boolean tryAcquire(long timeout, TimeUnit unit) throws InterruptedException { return sync.tryAcquireSharedNanos(1, unit.toNanos(timeout)); }
  public void release() { sync.releaseShared(1); }
  private static int check(int permits) { if (permits < 0) throw new IllegalArgumentException(); return permits; }
  public void acquire(int permits) throws InterruptedException { sync.acquireSharedInterruptibly(check(permits)); }
  public void acquireUninterruptibly(int permits) { sync.acquireShared(check(permits)); }
  public boolean tryAcquire(int permits) { return sync.nonfairTryAcquireShared(check(permits)) >= 0; }
  public boolean tryAcquire(int permits, long timeout, TimeUnit unit) throws InterruptedException { return sync.tryAcquireSharedNanos(check(permits), unit.toNanos(timeout)); }
  public void release(int permits) { sync.releaseShared(check(permits)); }
  public int availablePermits() { return sync.getPermits(); }
  public int drainPermits() { return sync.drainPermits(); }
  protected void reducePermits(int reduction) { sync.reducePermits(check(reduction)); }
  public boolean isFair() { return sync instanceof FairSync; }
  public final boolean hasQueuedThreads() { return sync.hasQueuedThreads(); }
  public final int getQueueLength() { return sync.getQueueLength(); }
  protected Collection<Thread> getQueuedThreads() { return sync.getQueuedThreads(); }
  public String toString() { return super.toString() + "[Permits = " + sync.getPermits() + "]"; }
}
