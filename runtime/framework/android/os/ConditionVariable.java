package android.os;

public class ConditionVariable {
  private volatile boolean mCondition;
  public ConditionVariable() {}
  public ConditionVariable(boolean state) { mCondition = state; }
  public synchronized void open() { boolean old = mCondition; mCondition = true; if (!old) notifyAll(); }
  public synchronized void close() { mCondition = false; }
  public synchronized void block() { while (!mCondition) { try { wait(); } catch (InterruptedException e) { /* keep waiting */ } } }
  public synchronized boolean block(long timeout) {
    if (timeout == 0) { block(); return true; }
    long end = SystemClock.elapsedRealtime() + timeout;
    while (!mCondition) {
      long left = end - SystemClock.elapsedRealtime();
      if (left <= 0) break;
      try { wait(left); } catch (InterruptedException e) { /* keep waiting */ }
    }
    return mCondition;
  }
}
