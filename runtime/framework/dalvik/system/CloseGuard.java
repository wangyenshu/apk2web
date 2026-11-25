package dalvik.system;

/** dalvik.system.CloseGuard (hidden): resource-leak reporting, disabled. */
public final class CloseGuard {
  private static final CloseGuard NOOP = new CloseGuard();
  private static volatile boolean enabled;
  public interface Reporter { void report(String message, Throwable allocationSite); }
  private Throwable site;
  public static CloseGuard get() { return enabled ? new CloseGuard() : NOOP; }
  public static void setEnabled(boolean e) { enabled = e; }
  public static boolean isEnabled() { return enabled; }
  public static void setReporter(Reporter r) {}
  public static Reporter getReporter() { return null; }
  public void open(String closer) { if (this != NOOP) site = new Throwable("Explicit termination method '" + closer + "' not called"); }
  public void openWithCallSite(String closer, String callsite) { open(closer); }
  public void close() { site = null; }
  public void warnIfOpen() { if (site != null) android.util.Log.w("StrictMode", "A resource was acquired and never released.", site); }
}
