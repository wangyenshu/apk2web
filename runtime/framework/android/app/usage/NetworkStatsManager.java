// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.app.usage;

public class NetworkStatsManager {
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public NetworkStatsManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public android.app.usage.NetworkStats queryDetails(int networkType, java.lang.String subscriberId, long startTime, long endTime) throws android.os.RemoteException, java.lang.SecurityException { a2w.Stubs.hit("android.app.usage.NetworkStatsManager.queryDetails"); return null; }
  @a2w.NotImplemented public android.app.usage.NetworkStats queryDetailsForUid(int networkType, java.lang.String subscriberId, long startTime, long endTime, int uid) throws java.lang.SecurityException { a2w.Stubs.hit("android.app.usage.NetworkStatsManager.queryDetailsForUid"); return null; }
  @a2w.NotImplemented public android.app.usage.NetworkStats queryDetailsForUidTag(int networkType, java.lang.String subscriberId, long startTime, long endTime, int uid, int tag) throws java.lang.SecurityException { a2w.Stubs.hit("android.app.usage.NetworkStatsManager.queryDetailsForUidTag"); return null; }
  @a2w.NotImplemented public android.app.usage.NetworkStats queryDetailsForUidTagState(int networkType, java.lang.String subscriberId, long startTime, long endTime, int uid, int tag, int state) throws java.lang.SecurityException { a2w.Stubs.hit("android.app.usage.NetworkStatsManager.queryDetailsForUidTagState"); return null; }
  @a2w.NotImplemented public android.app.usage.NetworkStats querySummary(int networkType, java.lang.String subscriberId, long startTime, long endTime) throws android.os.RemoteException, java.lang.SecurityException { a2w.Stubs.hit("android.app.usage.NetworkStatsManager.querySummary"); return null; }
  @a2w.NotImplemented public android.app.usage.NetworkStats.Bucket querySummaryForDevice(int networkType, java.lang.String subscriberId, long startTime, long endTime) throws android.os.RemoteException, java.lang.SecurityException { a2w.Stubs.hit("android.app.usage.NetworkStatsManager.querySummaryForDevice"); return null; }
  @a2w.NotImplemented public android.app.usage.NetworkStats.Bucket querySummaryForUser(int networkType, java.lang.String subscriberId, long startTime, long endTime) throws android.os.RemoteException, java.lang.SecurityException { a2w.Stubs.hit("android.app.usage.NetworkStatsManager.querySummaryForUser"); return null; }
  @a2w.NotImplemented public void registerUsageCallback(int networkType, java.lang.String subscriberId, long thresholdBytes, android.app.usage.NetworkStatsManager.UsageCallback callback) { a2w.Stubs.hit("android.app.usage.NetworkStatsManager.registerUsageCallback"); }
  @a2w.NotImplemented public void registerUsageCallback(int networkType, java.lang.String subscriberId, long thresholdBytes, android.app.usage.NetworkStatsManager.UsageCallback callback, android.os.Handler handler) { a2w.Stubs.hit("android.app.usage.NetworkStatsManager.registerUsageCallback"); }
  @a2w.NotImplemented public void unregisterUsageCallback(android.app.usage.NetworkStatsManager.UsageCallback callback) { a2w.Stubs.hit("android.app.usage.NetworkStatsManager.unregisterUsageCallback"); }

    public static abstract class UsageCallback {
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public UsageCallback(a2w.Hidden hidden) { }
    public UsageCallback() { this((a2w.Hidden) null); }
    public abstract void onThresholdReached(int p0, java.lang.String p1);
  }
}
