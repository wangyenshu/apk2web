package dalvik.system;

/** dalvik.system.VMRuntime (hidden): the VM's heap and ABI facts as a 32-bit ARM device reports them. */
public final class VMRuntime {
  static final VMRuntime THE_ONE = new VMRuntime();
  public static final int SDK_VERSION_CUR_DEVELOPMENT = 10000;
  private int targetSdk = android.content.pm.ApplicationInfo.app().targetSdkVersion;
  private float utilization = 0.75f;
  private VMRuntime() {}
  public static VMRuntime getRuntime() { return THE_ONE; }
  public String[] properties() { return new String[0]; }
  public String bootClassPath() { return ""; }
  public String classPath() { return ""; }
  public String vmVersion() { return "2.1.0"; }
  public String vmLibrary() { return "libart.so"; }
  public String vmInstructionSet() { return "arm"; }
  public boolean is64Bit() { return false; }
  public boolean isCheckJniEnabled() { return false; }
  public boolean isNativeDebuggable() { return false; }
  public static String getCurrentInstructionSet() { return "arm"; }
  public static String getInstructionSet(String abi) { return abi != null && abi.startsWith("arm64") ? "arm64" : abi != null && abi.startsWith("x86_64") ? "x86_64" : abi != null && abi.startsWith("x86") ? "x86" : "arm"; }
  public static boolean is64BitAbi(String abi) { return abi != null && abi.contains("64"); }
  public static boolean is64BitInstructionSet(String isa) { return isa != null && isa.contains("64"); }
  public float getTargetHeapUtilization() { return utilization; }
  public float setTargetHeapUtilization(float u) { float old = utilization; utilization = u; return old; }
  public void setTargetSdkVersion(int v) { targetSdk = v; }
  public int getTargetSdkVersion() { return targetSdk; }
  public long getMinimumHeapSize() { return 0; }
  public long setMinimumHeapSize(long s) { return 0; }
  public void gcSoftReferences() { System.gc(); }
  public void runFinalizationSync() {}
  public void registerNativeAllocation(int bytes) {}
  public void registerNativeAllocation(long bytes) {}
  public void registerNativeFree(int bytes) {}
  public void registerNativeFree(long bytes) {}
  public void clearGrowthLimit() {}
  public void clampGrowthLimit() {}
  public void trimHeap() {}
  public void concurrentGC() {}
  public void requestConcurrentGC() {}
  public void requestHeapTrim() {}
  public long getExternalBytesAllocated() { return 0; }
  public boolean trackExternalAllocation(long size) { return true; }
  public void trackExternalFree(long size) {}
  public Object newNonMovableArray(Class<?> componentType, int length) { return java.lang.reflect.Array.newInstance(componentType, length); }
  public Object newUnpaddedArray(Class<?> componentType, int minLength) { return java.lang.reflect.Array.newInstance(componentType, minLength); }
  public long addressOf(Object array) { return 0; }
  public static void registerAppInfo(String packageName, String[] codePaths) {}
  public static void setHiddenApiExemptions(String[] signaturePrefixes) {}
  public static void setHiddenApiAccessLogSamplingRate(int rate) {}
  public void setDedupeHiddenApiWarnings(boolean dedupe) {}
  public void disableJitCompilation() {}
  public void startJitCompilation() {}
  public void notifyStartupCompleted() {}
}
