package a2w.jdk.util.concurrent.locks;

import java.util.concurrent.TimeUnit;

/**
 * java.util.concurrent.locks.StampedLock with the JDK's stamp layout: bits 0-6 count readers (with an overflow
 * count beyond 126), bit 7 is the write lock, the rest a version that each write unlock advances. Blocking waits use
 * the lock's monitor; optimistic reads only compare versions.
 */
public class StampedLock implements java.io.Serializable {
  private static final int LG_READERS = 7;
  private static final long RUNIT = 1L, WBIT = 1L << LG_READERS, RBITS = WBIT - 1L, RFULL = RBITS - 1L,
      ABITS = RBITS | WBIT, SBITS = ~RBITS, ORIGIN = WBIT << 1;
  private long state = ORIGIN;
  private int readerOverflow;

  public StampedLock() {}

  private static long unlockWriteState(long s) { return ((s += WBIT) == 0L) ? ORIGIN : s; }
  private static long deadline(long time, TimeUnit unit) { return System.currentTimeMillis() + Math.max(1, unit.toMillis(time)); }

  // ---- write
  public synchronized long tryWriteLock() {
    long s = state;
    if ((s & ABITS) != 0L) return 0L;
    state = s + WBIT;
    return state;
  }
  private long writeWait(boolean interruptible, long deadline) throws InterruptedException {
    synchronized (this) {
      boolean interrupted = false;
      try {
        for (;;) {
          long st = tryWriteLock();
          if (st != 0L) return st;
          long left = deadline == 0 ? 0 : deadline - System.currentTimeMillis();
          if (deadline != 0 && left <= 0) return 0L;
          try { wait(left); } catch (InterruptedException e) { if (interruptible) throw e; interrupted = true; }
        }
      } finally { if (interrupted) Thread.currentThread().interrupt(); }
    }
  }
  public long writeLock() { try { return writeWait(false, 0); } catch (InterruptedException e) { return 0L; } }
  public long tryWriteLock(long time, TimeUnit unit) throws InterruptedException {
    if (Thread.interrupted()) throw new InterruptedException();
    long st = tryWriteLock();
    if (st != 0L || time <= 0) return st;
    return writeWait(true, deadline(time, unit));
  }
  public long writeLockInterruptibly() throws InterruptedException {
    if (Thread.interrupted()) throw new InterruptedException();
    return writeWait(true, 0);
  }

  // ---- read
  public synchronized long tryReadLock() {
    long s = state;
    if ((s & WBIT) != 0L) return 0L;
    if ((s & RBITS) < RFULL) return state = s + RUNIT;
    readerOverflow++;
    return s;
  }
  private long readWait(boolean interruptible, long deadline) throws InterruptedException {
    synchronized (this) {
      boolean interrupted = false;
      try {
        for (;;) {
          long st = tryReadLock();
          if (st != 0L) return st;
          long left = deadline == 0 ? 0 : deadline - System.currentTimeMillis();
          if (deadline != 0 && left <= 0) return 0L;
          try { wait(left); } catch (InterruptedException e) { if (interruptible) throw e; interrupted = true; }
        }
      } finally { if (interrupted) Thread.currentThread().interrupt(); }
    }
  }
  public long readLock() { try { return readWait(false, 0); } catch (InterruptedException e) { return 0L; } }
  public long tryReadLock(long time, TimeUnit unit) throws InterruptedException {
    if (Thread.interrupted()) throw new InterruptedException();
    long st = tryReadLock();
    if (st != 0L || time <= 0) return st;
    return readWait(true, deadline(time, unit));
  }
  public long readLockInterruptibly() throws InterruptedException {
    if (Thread.interrupted()) throw new InterruptedException();
    return readWait(true, 0);
  }

  // ---- optimistic
  public synchronized long tryOptimisticRead() { long s = state; return (s & WBIT) == 0L ? (s & SBITS) : 0L; }
  public synchronized boolean validate(long stamp) { return (stamp & SBITS) == (state & SBITS); }

  // ---- unlock
  public synchronized void unlockWrite(long stamp) {
    if (state != stamp || (stamp & WBIT) == 0L) throw new IllegalMonitorStateException();
    state = unlockWriteState(stamp);
    notifyAll();
  }
  public synchronized void unlockRead(long stamp) {
    long s = state;
    if ((stamp & RBITS) == 0L || (s & SBITS) != (stamp & SBITS) || (s & RBITS) == 0L) throw new IllegalMonitorStateException();
    releaseRead(s);
  }
  private void releaseRead(long s) {
    if ((s & RBITS) == RFULL && readerOverflow > 0) readerOverflow--;
    else state = s - RUNIT;
    notifyAll();
  }
  public synchronized void unlock(long stamp) {
    if ((stamp & WBIT) != 0L) unlockWrite(stamp);
    else unlockRead(stamp);
  }
  public synchronized boolean tryUnlockWrite() {
    long s = state;
    if ((s & WBIT) == 0L) return false;
    state = unlockWriteState(s);
    notifyAll();
    return true;
  }
  public synchronized boolean tryUnlockRead() {
    long s = state;
    if ((s & ABITS) == 0L || (s & ABITS) >= WBIT) return false;
    releaseRead(s);
    return true;
  }

  // ---- conversion
  public synchronized long tryConvertToWriteLock(long stamp) {
    long a = stamp & ABITS, s = state;
    if ((s & SBITS) != (stamp & SBITS)) return 0L;
    long m = s & ABITS;
    if (m == 0L) { if (a != 0L) return 0L; return state = s + WBIT; }
    if (m == WBIT) { if (a != m) return 0L; return stamp; }
    if (m == RUNIT && a != 0L) { state = s - RUNIT + WBIT; return state; }
    return 0L;
  }
  public synchronized long tryConvertToReadLock(long stamp) {
    long a = stamp & ABITS, s = state;
    if ((s & SBITS) != (stamp & SBITS)) return 0L;
    long m = s & ABITS;
    if (m == WBIT) { if (a != m) return 0L; state = unlockWriteState(s) + RUNIT; notifyAll(); return state; }
    if (a != 0L && a < WBIT) return stamp;
    if (a == 0L) return tryReadLock();
    return 0L;
  }
  public synchronized long tryConvertToOptimisticRead(long stamp) {
    long a = stamp & ABITS, s = state;
    if ((stamp & SBITS) != (s & SBITS)) return 0L;
    long m = s & ABITS;
    if (a == 0L) return (m & WBIT) == 0L ? s & SBITS : 0L;
    if (m == WBIT) { if (a != m) return 0L; state = unlockWriteState(s); notifyAll(); return state & SBITS; }
    if (m == 0L || a == WBIT) return 0L;
    releaseRead(s);
    return (s - RUNIT) & SBITS;
  }

  // ---- state
  public synchronized boolean isWriteLocked() { return (state & WBIT) != 0L; }
  public synchronized boolean isReadLocked() { return (state & RBITS) != 0L; }
  public synchronized int getReadLockCount() { long r = state & RBITS; return (int) (r < RFULL ? r : RFULL + readerOverflow); }
  public static boolean isWriteLockStamp(long stamp) { return (stamp & ABITS) == WBIT; }
  public static boolean isReadLockStamp(long stamp) { return (stamp & RBITS) != 0L; }
  public static boolean isLockStamp(long stamp) { return (stamp & ABITS) != 0L; }
  public static boolean isOptimisticReadStamp(long stamp) { return (stamp & ABITS) == 0L && stamp != 0L; }
  public String toString() {
    long s;
    synchronized (this) { s = state; }
    return super.toString() + ((s & ABITS) == 0L ? "[Unlocked]" : (s & WBIT) != 0L ? "[Write-locked]" : "[Read-locks:" + getReadLockCount() + "]");
  }

  // ---- views
  public Lock asReadLock() {
    return new Lock() {
      public void lock() { readLock(); }
      public void lockInterruptibly() throws InterruptedException { readLockInterruptibly(); }
      public boolean tryLock() { return tryReadLock() != 0L; }
      public boolean tryLock(long time, TimeUnit unit) throws InterruptedException { return tryReadLock(time, unit) != 0L; }
      public void unlock() { if (!tryUnlockRead()) throw new IllegalMonitorStateException(); }
      public Condition newCondition() { throw new UnsupportedOperationException(); }
    };
  }
  public Lock asWriteLock() {
    return new Lock() {
      public void lock() { writeLock(); }
      public void lockInterruptibly() throws InterruptedException { writeLockInterruptibly(); }
      public boolean tryLock() { return tryWriteLock() != 0L; }
      public boolean tryLock(long time, TimeUnit unit) throws InterruptedException { return tryWriteLock(time, unit) != 0L; }
      public void unlock() { if (!tryUnlockWrite()) throw new IllegalMonitorStateException(); }
      public Condition newCondition() { throw new UnsupportedOperationException(); }
    };
  }
  public ReadWriteLock asReadWriteLock() {
    Lock r = asReadLock(), w = asWriteLock();
    return new ReadWriteLock() { public Lock readLock() { return r; } public Lock writeLock() { return w; } };
  }
}
