// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.os;

public final class PerformanceHintManager {
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public PerformanceHintManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public android.os.PerformanceHintManager.Session createHintSession(int[] tids, long initialTargetWorkDurationNanos) { a2w.Stubs.hit("android.os.PerformanceHintManager.createHintSession"); return null; }
  @a2w.NotImplemented public long getPreferredUpdateRateNanos() { a2w.Stubs.hit("android.os.PerformanceHintManager.getPreferredUpdateRateNanos"); return 0L; }

    public static class Session implements java.io.Closeable {
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public Session(a2w.Hidden hidden) { }
    @a2w.NotImplemented public void close() { a2w.Stubs.hit("android.os.PerformanceHintManager.Session.close"); }
    @a2w.NotImplemented protected void finalize() throws java.lang.Throwable { a2w.Stubs.hit("android.os.PerformanceHintManager.Session.finalize"); }
    @a2w.NotImplemented public void reportActualWorkDuration(long actualDurationNanos) { a2w.Stubs.hit("android.os.PerformanceHintManager.Session.reportActualWorkDuration"); }
    @a2w.NotImplemented public void reportActualWorkDuration(android.os.WorkDuration workDuration) { a2w.Stubs.hit("android.os.PerformanceHintManager.Session.reportActualWorkDuration"); }
    @a2w.NotImplemented public void setPreferPowerEfficiency(boolean enabled) { a2w.Stubs.hit("android.os.PerformanceHintManager.Session.setPreferPowerEfficiency"); }
    @a2w.NotImplemented public void setThreads(int[] tids) { a2w.Stubs.hit("android.os.PerformanceHintManager.Session.setThreads"); }
    @a2w.NotImplemented public void updateTargetWorkDuration(long targetDurationNanos) { a2w.Stubs.hit("android.os.PerformanceHintManager.Session.updateTargetWorkDuration"); }
  }
}
