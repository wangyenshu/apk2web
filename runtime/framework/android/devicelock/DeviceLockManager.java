// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.devicelock;

public final class DeviceLockManager {
  public static final int DEVICE_LOCK_ROLE_FINANCING = 0;
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public DeviceLockManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public void getDeviceId(java.util.concurrent.Executor executor, android.os.OutcomeReceiver<android.devicelock.DeviceId, java.lang.Exception> callback) { a2w.Stubs.hit("android.devicelock.DeviceLockManager.getDeviceId"); }
  @a2w.NotImplemented public void getKioskApps(java.util.concurrent.Executor executor, android.os.OutcomeReceiver<java.util.Map<java.lang.Integer, java.lang.String>, java.lang.Exception> callback) { a2w.Stubs.hit("android.devicelock.DeviceLockManager.getKioskApps"); }
  @a2w.NotImplemented public void isDeviceLocked(java.util.concurrent.Executor executor, android.os.OutcomeReceiver<java.lang.Boolean, java.lang.Exception> callback) { a2w.Stubs.hit("android.devicelock.DeviceLockManager.isDeviceLocked"); }
  @a2w.NotImplemented public void lockDevice(java.util.concurrent.Executor executor, android.os.OutcomeReceiver<java.lang.Void, java.lang.Exception> callback) { a2w.Stubs.hit("android.devicelock.DeviceLockManager.lockDevice"); }
  @a2w.NotImplemented public void unlockDevice(java.util.concurrent.Executor executor, android.os.OutcomeReceiver<java.lang.Void, java.lang.Exception> callback) { a2w.Stubs.hit("android.devicelock.DeviceLockManager.unlockDevice"); }
}
