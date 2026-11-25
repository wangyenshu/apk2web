package android.os;

public final class SystemClock {
  private static final long start = System.currentTimeMillis();
  private SystemClock() {}
  public static long uptimeMillis() { return (long) a2w.Host.now(); }
  public static long elapsedRealtime() { return (long) a2w.Host.now(); }
  public static long currentThreadTimeMillis() { return (long) a2w.Host.now(); }
  public static void sleep(long ms) { try { Thread.sleep(ms); } catch (InterruptedException e) {} }
  public static boolean setCurrentTimeMillis(long m) { return false; }
}
