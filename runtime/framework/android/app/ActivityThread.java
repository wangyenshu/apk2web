package android.app;

/**
 * android.app.ActivityThread (hidden): what SDKs reach through reflection for the process's Application and its
 * name. The page's real activity thread is a2w.ActivityThread.
 */
public final class ActivityThread {
  static final ActivityThread CURRENT = new ActivityThread();
  private ActivityThread() {}
  public static ActivityThread currentActivityThread() { return CURRENT; }
  public static Application currentApplication() { return a2w.ActivityThread.application(); }
  public static String currentPackageName() { return a2w.Res.pkg(); }
  public static String currentProcessName() { return a2w.Res.pkg(); }
  public static String currentOpPackageName() { return a2w.Res.pkg(); }
  public Application getApplication() { return currentApplication(); }
  public String getProcessName() { return currentProcessName(); }
  public android.os.Looper getLooper() { return android.os.Looper.getMainLooper(); }
  public static boolean isSystem() { return false; }
  public android.content.Context getSystemContext() { return a2w.ActivityThread.baseContext(); }
  public Instrumentation getInstrumentation() { return new Instrumentation(); }
}
