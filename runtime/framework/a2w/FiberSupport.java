package a2w;

/** used by TeaVM's org.teavm.runtime.Fiber in place of Arrays.copyOf (see jp.teavm.FiberFix): plain code that cannot suspend */
public final class FiberSupport {
  private FiberSupport() {}
  /**
   * Makes t TeaVM's current thread. TeaVM switches its current thread when it resumes a thread; code the page calls
   * (a2w.Main's event entry points) runs outside any thread and would otherwise run as whichever thread ran last:
   * wrong monitor owners, ThreadLocals and Loopers.
   */
  public static void resume(Thread t) { if (t != null) setCurrent(t); }
  /** jp.teavm.FiberFix makes this call java.lang.Thread.setCurrentThread (TeaVM's, package private) */
  private static void setCurrent(Thread t) {}
  public static Object[] grow(Object[] a, int n) {
    Object[] r = new Object[n];
    int m = a.length < n ? a.length : n;
    for (int i = 0; i < m; i++) r[i] = a[i];
    return r;
  }
}
