// Placeholder for compiling the framework: the linker (tools/jarproc) generates the real class per app.
package a2w.gen;
public final class JniTable {
  public static void register() {}
  public static long invoke(int id, Object t, int[] a) { return 0; }
  public static long getField(int id, Object t) { return 0; }
  public static void setField(int id, Object t, long v) {}
  public static Object[] newArray(int id, int n) { return null; }
  public static Throwable newThrowable(int id, String m) { return null; }
}
