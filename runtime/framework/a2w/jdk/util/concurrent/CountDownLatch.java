package a2w.jdk.util.concurrent;

import java.util.concurrent.TimeUnit;

/** await() waits (TeaVM's threads switch meanwhile) until countDown() has been called count times. */
public class CountDownLatch {
  private long count;
  public CountDownLatch(int count) { if (count < 0) throw new IllegalArgumentException("count < 0"); this.count = count; }
  public synchronized void await() throws InterruptedException { while (count > 0) wait(); }
  public synchronized boolean await(long timeout, TimeUnit unit) throws InterruptedException {
    long end = System.currentTimeMillis() + Math.max(0, unit.toMillis(timeout));
    while (count > 0) {
      long left = end - System.currentTimeMillis();
      if (left <= 0) return false;
      wait(left);
    }
    return true;
  }
  public synchronized void countDown() { if (count > 0 && --count == 0) notifyAll(); }
  public synchronized long getCount() { return count; }
  @Override public String toString() { return super.toString() + "[Count = " + getCount() + "]"; }
}
