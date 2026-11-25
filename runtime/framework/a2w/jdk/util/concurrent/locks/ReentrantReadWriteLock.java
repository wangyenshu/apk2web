package a2w.jdk.util.concurrent.locks;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/** java.util.concurrent.locks.ReentrantReadWriteLock on AbstractQueuedSynchronizer: the state holds the shared count
 *  (high 16 bits) and the exclusive hold count (low 16 bits); each thread's read holds are kept in a thread local. */
public class ReentrantReadWriteLock implements ReadWriteLock, java.io.Serializable {
  private final ReadLock readerLock;
  private final WriteLock writerLock;
  final Sync sync;

  abstract static class Sync extends AbstractQueuedSynchronizer {
    static final int SHARED_SHIFT = 16, SHARED_UNIT = 1 << SHARED_SHIFT, MAX_COUNT = (1 << SHARED_SHIFT) - 1, EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;
    static int sharedCount(int c) { return c >>> SHARED_SHIFT; }
    static int exclusiveCount(int c) { return c & EXCLUSIVE_MASK; }
    private final transient ThreadLocal<int[]> readHolds = ThreadLocal.withInitial(() -> new int[1]);

    abstract boolean readerShouldBlock();
    abstract boolean writerShouldBlock();

    protected final boolean tryRelease(int releases) {
      if (!isHeldExclusively()) throw new IllegalMonitorStateException();
      int nextc = getState() - releases;
      boolean free = exclusiveCount(nextc) == 0;
      if (free) setExclusiveOwnerThread(null);
      setState(nextc);
      return free;
    }
    protected final boolean tryAcquire(int acquires) {
      Thread current = Thread.currentThread();
      int c = getState(), w = exclusiveCount(c);
      if (c != 0) {
        if (w == 0 || current != getExclusiveOwnerThread()) return false;
        if (w + exclusiveCount(acquires) > MAX_COUNT) throw new Error("Maximum lock count exceeded");
        setState(c + acquires);
        return true;
      }
      if (writerShouldBlock() || !compareAndSetState(c, c + acquires)) return false;
      setExclusiveOwnerThread(current);
      return true;
    }
    protected final boolean tryReleaseShared(int unused) {
      int[] h = readHolds.get();
      if (h[0] <= 0) throw new IllegalMonitorStateException("attempt to unlock read lock, not locked by current thread");
      h[0]--;
      for (;;) { int c = getState(), nextc = c - SHARED_UNIT; if (compareAndSetState(c, nextc)) return nextc == 0; }
    }
    protected final int tryAcquireShared(int unused) {
      Thread current = Thread.currentThread();
      int[] h = readHolds.get();
      for (;;) {
        int c = getState();
        if (exclusiveCount(c) != 0 && getExclusiveOwnerThread() != current) return -1;
        if (readerShouldBlock() && h[0] == 0 && getExclusiveOwnerThread() != current) return -1;
        if (sharedCount(c) == MAX_COUNT) throw new Error("Maximum lock count exceeded");
        if (compareAndSetState(c, c + SHARED_UNIT)) { h[0]++; return 1; }
      }
    }
    final boolean tryWriteLock() {
      Thread current = Thread.currentThread();
      int c = getState();
      if (c != 0) {
        int w = exclusiveCount(c);
        if (w == 0 || current != getExclusiveOwnerThread()) return false;
        if (w == MAX_COUNT) throw new Error("Maximum lock count exceeded");
      }
      if (!compareAndSetState(c, c + 1)) return false;
      setExclusiveOwnerThread(current);
      return true;
    }
    final boolean tryReadLock() {
      Thread current = Thread.currentThread();
      for (;;) {
        int c = getState();
        if (exclusiveCount(c) != 0 && getExclusiveOwnerThread() != current) return false;
        if (sharedCount(c) == MAX_COUNT) throw new Error("Maximum lock count exceeded");
        if (compareAndSetState(c, c + SHARED_UNIT)) { readHolds.get()[0]++; return true; }
      }
    }
    protected final boolean isHeldExclusively() { return getExclusiveOwnerThread() == Thread.currentThread(); }
    final ConditionObject newCondition() { return new ConditionObject(); }
    final Thread getOwner() { return exclusiveCount(getState()) == 0 ? null : getExclusiveOwnerThread(); }
    final int getReadLockCount() { return sharedCount(getState()); }
    final boolean isWriteLocked() { return exclusiveCount(getState()) != 0; }
    final int getWriteHoldCount() { return isHeldExclusively() ? exclusiveCount(getState()) : 0; }
    final int getReadHoldCount() { return getReadLockCount() == 0 ? 0 : readHolds.get()[0]; }
    final int getCount() { return getState(); }
  }
  static final class NonfairSync extends Sync {
    boolean writerShouldBlock() { return false; }
    boolean readerShouldBlock() { return apparentlyFirstQueuedIsExclusive(); }
  }
  static final class FairSync extends Sync {
    boolean writerShouldBlock() { return hasQueuedPredecessors(); }
    boolean readerShouldBlock() { return hasQueuedPredecessors(); }
  }

  public static class ReadLock implements Lock, java.io.Serializable {
    private final Sync sync;
    protected ReadLock(ReentrantReadWriteLock lock) { sync = lock.sync; }
    public void lock() { sync.acquireShared(1); }
    public void lockInterruptibly() throws InterruptedException { sync.acquireSharedInterruptibly(1); }
    public boolean tryLock() { return sync.tryReadLock(); }
    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException { return sync.tryAcquireSharedNanos(1, unit.toNanos(timeout)); }
    public void unlock() { sync.releaseShared(1); }
    public Condition newCondition() { throw new UnsupportedOperationException(); }
    public String toString() { return super.toString() + "[Read locks = " + sync.getReadLockCount() + "]"; }
  }
  public static class WriteLock implements Lock, java.io.Serializable {
    private final Sync sync;
    protected WriteLock(ReentrantReadWriteLock lock) { sync = lock.sync; }
    public void lock() { sync.acquire(1); }
    public void lockInterruptibly() throws InterruptedException { sync.acquireInterruptibly(1); }
    public boolean tryLock() { return sync.tryWriteLock(); }
    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException { return sync.tryAcquireNanos(1, unit.toNanos(timeout)); }
    public void unlock() { sync.release(1); }
    public Condition newCondition() { return sync.newCondition(); }
    public boolean isHeldByCurrentThread() { return sync.isHeldExclusively(); }
    public int getHoldCount() { return sync.getWriteHoldCount(); }
    public String toString() { Thread o = sync.getOwner(); return super.toString() + (o == null ? "[Unlocked]" : "[Locked by thread " + o.getName() + "]"); }
  }

  public ReentrantReadWriteLock() { this(false); }
  public ReentrantReadWriteLock(boolean fair) {
    sync = fair ? new FairSync() : new NonfairSync();
    readerLock = new ReadLock(this);
    writerLock = new WriteLock(this);
  }
  public ReentrantReadWriteLock.WriteLock writeLock() { return writerLock; }
  public ReentrantReadWriteLock.ReadLock readLock() { return readerLock; }
  public final boolean isFair() { return sync instanceof FairSync; }
  protected Thread getOwner() { return sync.getOwner(); }
  public int getReadLockCount() { return sync.getReadLockCount(); }
  public boolean isWriteLocked() { return sync.isWriteLocked(); }
  public boolean isWriteLockedByCurrentThread() { return sync.isHeldExclusively(); }
  public int getWriteHoldCount() { return sync.getWriteHoldCount(); }
  public int getReadHoldCount() { return sync.getReadHoldCount(); }
  protected Collection<Thread> getQueuedWriterThreads() { return sync.getExclusiveQueuedThreads(); }
  protected Collection<Thread> getQueuedReaderThreads() { return sync.getSharedQueuedThreads(); }
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
    int c = sync.getCount();
    return super.toString() + "[Write locks = " + Sync.exclusiveCount(c) + ", Read locks = " + Sync.sharedCount(c) + "]";
  }
}
