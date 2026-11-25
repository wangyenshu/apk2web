// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.os;

public final class ProfilingManager {
  public static final int PROFILING_TYPE_HEAP_PROFILE = 2;
  public static final int PROFILING_TYPE_JAVA_HEAP_DUMP = 1;
  public static final int PROFILING_TYPE_STACK_SAMPLING = 3;
  public static final int PROFILING_TYPE_SYSTEM_TRACE = 4;
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public ProfilingManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public void registerForAllProfilingResults(java.util.concurrent.Executor executor, java.util.function.Consumer<android.os.ProfilingResult> listener) { a2w.Stubs.hit("android.os.ProfilingManager.registerForAllProfilingResults"); }
  @a2w.NotImplemented public void requestProfiling(int profilingType, android.os.Bundle parameters, java.lang.String tag, android.os.CancellationSignal cancellationSignal, java.util.concurrent.Executor executor, java.util.function.Consumer<android.os.ProfilingResult> listener) { a2w.Stubs.hit("android.os.ProfilingManager.requestProfiling"); }
  @a2w.NotImplemented public void unregisterForAllProfilingResults(java.util.function.Consumer<android.os.ProfilingResult> listener) { a2w.Stubs.hit("android.os.ProfilingManager.unregisterForAllProfilingResults"); }
}
