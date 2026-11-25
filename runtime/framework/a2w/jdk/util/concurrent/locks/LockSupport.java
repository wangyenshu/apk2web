package a2w.jdk.util.concurrent.locks;

import java.util.WeakHashMap;

/** park/unpark with a permit per thread (one waiting monitor for all of them). */
public class LockSupport {
  private LockSupport() {}
  private static final Object M = new Object();
  private static final WeakHashMap<Thread, Boolean> PERMITS = new WeakHashMap<>();
  private static final WeakHashMap<Thread, Object> BLOCKERS = new WeakHashMap<>();
  private static void parkMillis(Object blocker, long ms) {
    Thread t = Thread.currentThread();
    long end = ms < 0 ? Long.MAX_VALUE : System.currentTimeMillis() + ms;
    synchronized (M) {
      if (blocker != null) BLOCKERS.put(t, blocker);
      try {
        while (PERMITS.remove(t) == null) {
          long left = end == Long.MAX_VALUE ? 0 : end - System.currentTimeMillis();
          if (end != Long.MAX_VALUE && left <= 0) return;
          try { M.wait(left); } catch (InterruptedException e) { t.interrupt(); return; }
        }
      } finally { BLOCKERS.remove(t); }
    }
  }
  public static void park() { parkMillis(null, -1); }
  public static void park(Object blocker) { parkMillis(blocker, -1); }
  public static void parkNanos(long n) { if (n > 0) parkMillis(null, Math.max(1, n / 1000000)); }
  public static void parkNanos(Object blocker, long n) { if (n > 0) parkMillis(blocker, Math.max(1, n / 1000000)); }
  public static void parkUntil(long deadline) { parkMillis(null, Math.max(0, deadline - System.currentTimeMillis())); }
  public static void parkUntil(Object blocker, long deadline) { parkMillis(blocker, Math.max(0, deadline - System.currentTimeMillis())); }
  public static void unpark(Thread t) { if (t == null) return; synchronized (M) { PERMITS.put(t, Boolean.TRUE); M.notifyAll(); } }
  public static Object getBlocker(Thread t) { synchronized (M) { return BLOCKERS.get(t); } }
}
