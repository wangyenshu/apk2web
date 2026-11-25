// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.os.health;

public class SystemHealthManager {
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public SystemHealthManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public void getPowerMonitorReadings(java.util.List<android.os.PowerMonitor> powerMonitors, java.util.concurrent.Executor executor, android.os.OutcomeReceiver<android.os.PowerMonitorReadings, java.lang.RuntimeException> onResult) { a2w.Stubs.hit("android.os.health.SystemHealthManager.getPowerMonitorReadings"); }
  @a2w.NotImplemented public void getSupportedPowerMonitors(java.util.concurrent.Executor executor, java.util.function.Consumer<java.util.List<android.os.PowerMonitor>> onResult) { a2w.Stubs.hit("android.os.health.SystemHealthManager.getSupportedPowerMonitors"); }
  @a2w.NotImplemented public android.os.health.HealthStats takeMyUidSnapshot() { a2w.Stubs.hit("android.os.health.SystemHealthManager.takeMyUidSnapshot"); return null; }
  @a2w.NotImplemented public android.os.health.HealthStats takeUidSnapshot(int uid) { a2w.Stubs.hit("android.os.health.SystemHealthManager.takeUidSnapshot"); return null; }
  @a2w.NotImplemented public android.os.health.HealthStats[] takeUidSnapshots(int[] uids) { a2w.Stubs.hit("android.os.health.SystemHealthManager.takeUidSnapshots"); return null; }
}
