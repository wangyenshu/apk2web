package android.os;

/** android.os.Trace: systrace sections (no tracer in the page). */
public final class Trace {
  private Trace() {}
  public static final long TRACE_TAG_NEVER = 0, TRACE_TAG_ALWAYS = 1, TRACE_TAG_VIEW = 1 << 3, TRACE_TAG_APP = 1 << 12;
  public static boolean isEnabled() { return false; }
  public static boolean isTagEnabled(long tag) { return false; }
  public static void beginSection(String sectionName) { if (sectionName != null && sectionName.length() > 127) throw new IllegalArgumentException("sectionName is too long"); }
  public static void endSection() {}
  public static void beginAsyncSection(String methodName, int cookie) {}
  public static void endAsyncSection(String methodName, int cookie) {}
  public static void setCounter(String counterName, long counterValue) {}
  public static void traceBegin(long tag, String name) {}
  public static void traceEnd(long tag) {}
  public static void asyncTraceBegin(long tag, String name, int cookie) {}
  public static void asyncTraceEnd(long tag, String name, int cookie) {}
  public static void traceCounter(long tag, String name, int value) {}
}
