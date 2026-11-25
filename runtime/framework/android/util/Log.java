package android.util;

public final class Log {
  public static final int VERBOSE = 2, DEBUG = 3, INFO = 4, WARN = 5, ERROR = 6, ASSERT = 7;
  public static int level = INFO;
  private Log() {}
  static int out(int p, String tag, String msg, Throwable t) {
    if (p < level) return 0;
    String s = "IVDIWEA".charAt(Math.min(p, 7) - 1 < 0 ? 0 : p - 1) + "/" + tag + ": " + msg;
    if (t != null) s += "\n" + getStackTraceString(t);
    a2w.Host.log(p, s);
    return 1;
  }
  public static int v(String t, String m) { return out(VERBOSE, t, m, null); }
  public static int v(String t, String m, Throwable e) { return out(VERBOSE, t, m, e); }
  public static int d(String t, String m) { return out(DEBUG, t, m, null); }
  public static int d(String t, String m, Throwable e) { return out(DEBUG, t, m, e); }
  public static int i(String t, String m) { return out(INFO, t, m, null); }
  public static int i(String t, String m, Throwable e) { return out(INFO, t, m, e); }
  public static int w(String t, String m) { return out(WARN, t, m, null); }
  public static int w(String t, String m, Throwable e) { return out(WARN, t, m, e); }
  public static int w(String t, Throwable e) { return out(WARN, t, "", e); }
  public static int e(String t, String m) { return out(ERROR, t, m, null); }
  public static int e(String t, String m, Throwable e) { return out(ERROR, t, m, e); }
  public static int wtf(String t, String m) { return out(ASSERT, t, m, null); }
  public static int wtf(String t, String m, Throwable e) { return out(ASSERT, t, m, e); }
  public static int wtf(String t, Throwable e) { return out(ASSERT, t, e.getMessage(), e); }
  public static int println(int p, String t, String m) { return out(p, t, m, null); }
  public static boolean isLoggable(String tag, int p) { return p >= level; }
  public static String getStackTraceString(Throwable t) {
    if (t == null) return "";
    StringBuilder sb = new StringBuilder(t.toString());
    for (StackTraceElement e : t.getStackTrace()) sb.append("\n\tat ").append(e);
    Throwable c = t.getCause();
    if (c != null && c != t) sb.append("\nCaused by: ").append(getStackTraceString(c));
    return sb.toString();
  }
}
