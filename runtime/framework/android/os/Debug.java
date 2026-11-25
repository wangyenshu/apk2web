package android.os;
public final class Debug {
  public static class MemoryInfo {
    public int dalvikPss, dalvikPrivateDirty, dalvikSharedDirty, nativePss, nativePrivateDirty, nativeSharedDirty, otherPss, otherPrivateDirty, otherSharedDirty;
    public int getTotalPss() { return dalvikPss + nativePss + otherPss; }
    public int getTotalPrivateDirty() { return dalvikPrivateDirty + nativePrivateDirty + otherPrivateDirty; }
    public int getTotalSharedDirty() { return dalvikSharedDirty + nativeSharedDirty + otherSharedDirty; }
    public int getTotalPrivateClean() { return 0; }
    public int getTotalSharedClean() { return 0; }
    public int getTotalSwappablePss() { return 0; }
    public String getMemoryStat(String name) { return "0"; }
  }
  /** plausible figures in kB (the page cannot measure its own memory per heap) */
  public static void getMemoryInfo(MemoryInfo m) {
    m.dalvikPss = 16384; m.dalvikPrivateDirty = 16384; m.dalvikSharedDirty = 2048;
    m.nativePss = 16384; m.nativePrivateDirty = 16384; m.nativeSharedDirty = 1024;
    m.otherPss = 4096; m.otherPrivateDirty = 2048; m.otherSharedDirty = 1024;
  }
  public static long getPss() { return 36864; }
  public static long getNativeHeapSize() { return 64L << 20; }
  public static long getNativeHeapAllocatedSize() { return 32L << 20; }
  public static long getNativeHeapFreeSize() { return 32L << 20; }
  public static boolean isDebuggerConnected() { return false; }
  public static boolean waitingForDebugger() { return false; }
  public static void startMethodTracing() {}
  public static void stopMethodTracing() {}
}
