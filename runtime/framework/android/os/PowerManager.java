package android.os;
public final class PowerManager {
  public static final int PARTIAL_WAKE_LOCK = 1, SCREEN_DIM_WAKE_LOCK = 6, SCREEN_BRIGHT_WAKE_LOCK = 10, FULL_WAKE_LOCK = 26, ACQUIRE_CAUSES_WAKEUP = 0x10000000, ON_AFTER_RELEASE = 0x20000000;
  public final class WakeLock {
    boolean held;
    public void acquire() { held = true; }
    public void acquire(long t) { held = true; }
    public void release() { held = false; }
    public boolean isHeld() { return held; }
    public void setReferenceCounted(boolean b) {}
  }
  public WakeLock newWakeLock(int flags, String tag) { return new WakeLock(); }
  public boolean isScreenOn() { return true; }
}
