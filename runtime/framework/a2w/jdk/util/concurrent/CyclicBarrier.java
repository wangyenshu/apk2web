package a2w.jdk.util.concurrent;

import java.util.concurrent.TimeUnit;

public class CyclicBarrier {
  private final int parties;
  private final Runnable action;
  private int waiting;
  private long generation;
  private boolean broken;
  public CyclicBarrier(int parties, Runnable action) { if (parties <= 0) throw new IllegalArgumentException(); this.parties = parties; this.action = action; }
  public CyclicBarrier(int parties) { this(parties, null); }
  public int getParties() { return parties; }
  public synchronized int getNumberWaiting() { return waiting; }
  public synchronized boolean isBroken() { return broken; }
  public synchronized void reset() { if (waiting > 0) broken = true; waiting = 0; generation++; notifyAll(); broken = false; }
  public int await() throws InterruptedException, BrokenBarrierException {
    try { return awaitMillis(-1); } catch (TimeoutException e) { throw new IllegalStateException(e); }
  }
  public int await(long t, TimeUnit u) throws InterruptedException, BrokenBarrierException, TimeoutException { return awaitMillis(Math.max(0, u.toMillis(t))); }
  private synchronized int awaitMillis(long ms) throws InterruptedException, BrokenBarrierException, TimeoutException {
    if (broken) throw new BrokenBarrierException();
    int index = parties - 1 - waiting;
    if (index == 0) {
      try { if (action != null) action.run(); }
      catch (RuntimeException | Error e) { broken = true; notifyAll(); throw e; }
      waiting = 0; generation++; notifyAll();
      return 0;
    }
    waiting++;
    long g = generation;
    long end = ms < 0 ? Long.MAX_VALUE : System.currentTimeMillis() + ms;
    try {
      while (g == generation && !broken) {
        long left = end == Long.MAX_VALUE ? 0 : end - System.currentTimeMillis();
        if (end != Long.MAX_VALUE && left <= 0) { broken = true; notifyAll(); throw new TimeoutException(); }
        wait(left);
      }
    } catch (InterruptedException e) { if (g == generation) { broken = true; notifyAll(); } throw e; }
    if (g == generation && broken) throw new BrokenBarrierException();
    return index;
  }
}
