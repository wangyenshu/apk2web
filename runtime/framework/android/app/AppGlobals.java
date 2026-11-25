package android.app;

/** android.app.AppGlobals (hidden) */
public class AppGlobals {
  public static Application getInitialApplication() { return ActivityThread.currentApplication(); }
  public static String getInitialPackage() { return ActivityThread.currentPackageName(); }
  public static int getIntCoreSetting(String key, int def) { return def; }
}
