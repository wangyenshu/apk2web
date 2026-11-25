package a2w.jdk.util.concurrent.locks;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/** java.util.concurrent.locks.ReentrantLock on AbstractQueuedSynchronizer (the hold count is the state), as the JDK's. */
public class ReentrantLock implements Lock, java.io.Serializable {
  private final Sync sync;

  abstract static class Sync extends AbstractQueuedSynchronizer {
    final boolean tryLockNow() {
      Thread cur = Thread.currentThread();
      int c = getState();
      if (c == 0) { if (compareAndSetState(0, 1)) { setExclusiveOwnerThread(cur); return true; } }
      else if (getExclusiveOwnerThread() == cur) { if (++c < 0) throw new Error("Maximum lock count exceeded"); setState(c); return true; }
      return false;
    }
    protected final boolean tryRelease(int releases) {
      if (getExclusiveOwnerThread() != Thread.currentThread()) throw new IllegalMonitorStateException();
      int c = getState() - releases;
      boolean free = c == 0;
      if (free) setExclusiveOwnerThread(null);
      setState(c);
      return free;
    }
    protected final boolean isHeldExclusively() { return getExclusiveOwnerThread() == Thread.currentThread(); }
    final ConditionObject newCondition() { return new ConditionObject(); }
    final Thread getOwner() { return getState() == 0 ? null : getExclusiveOwnerThread(); }
    final int getHoldCount() { return isHeldExclusively() ? getState() : 0; }
    final boolean isLocked() { return getState() != 0; }
  }
  static final class NonfairSync extends Sync {
    protected final boolean tryAcquire(int acquires) { return tryLockNow(); }
  }
  static final class FairSync extends Sync {
    protected final boolean tryAcquire(int acquires) {
      if (getState() == 0 && hasQueuedPredecessors()) return false;
      return tryLockNow();
    }
  }

  public ReentrantLock() { sync = new NonfairSync(); }
  public ReentrantLock(boolean fair) { sync = fair ? new FairSync() : new NonfairSync(); }
  public void lock() { sync.acquire(1); }
  public void lockInterruptibly() throws InterruptedException { sync.acquireInterruptibly(1); }
  public boolean tryLock() { return sync.tryLockNow(); }
  public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException { return sync.tryAcquireNanos(1, unit.toNanos(timeout)); }
  public void unlock() { sync.release(1); }
  public Condition newCondition() { return sync.newCondition(); }
  public int getHoldCount() { return sync.getHoldCount(); }
  public boolean isHeldByCurrentThread() { return sync.isHeldExclusively(); }
  public boolean isLocked() { return sync.isLocked(); }
  public final boolean isFair() { return sync instanceof FairSync; }
  protected Thread getOwner() { return sync.getOwner(); }
  public final boolean hasQueuedThreads() { return sync.hasQueuedThreads(); }
  public final boolean hasQueuedThread(Thread thread) { return sync.isQueued(thread); }
  public final int getQueueLength() { return sync.getQueueLength(); }
  protected Collection<Thread> getQueuedThreads() { return sync.getQueuedThreads(); }
  private AbstractQueuedSynchronizer.ConditionObject cond(Condition c) {
    if (c == null) throw new NullPointerException();
    if (!(c instanceof AbstractQueuedSynchronizer.ConditionObject)) throw new IllegalArgumentException("not owner");
    return (AbstractQueuedSynchronizer.ConditionObject) c;
  }
  public boolean hasWaiters(Condition condition) { return sync.hasWaiters(cond(condition)); }
  public int getWaitQueueLength(Condition condition) { return sync.getWaitQueueLength(cond(condition)); }
  protected Collection<Thread> getWaitingThreads(Condition condition) { return sync.getWaitingThreads(cond(condition)); }
  public String toString() {
    Thread o = sync.getOwner();
    return super.toString() + (o == null ? "[Unlocked]" : "[Locked by thread " + o.getName() + "]");
  }
}
