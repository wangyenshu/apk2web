// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.app.usage;

public final class UsageStatsManager {
  public static final java.lang.String EXTRA_EVENT_ACTION = "android.app.usage.extra.EVENT_ACTION";
  public static final java.lang.String EXTRA_EVENT_CATEGORY = "android.app.usage.extra.EVENT_CATEGORY";
  public static final int INTERVAL_BEST = 4;
  public static final int INTERVAL_DAILY = 0;
  public static final int INTERVAL_MONTHLY = 2;
  public static final int INTERVAL_WEEKLY = 1;
  public static final int INTERVAL_YEARLY = 3;
  public static final int STANDBY_BUCKET_ACTIVE = 10;
  public static final int STANDBY_BUCKET_FREQUENT = 30;
  public static final int STANDBY_BUCKET_RARE = 40;
  public static final int STANDBY_BUCKET_RESTRICTED = 45;
  public static final int STANDBY_BUCKET_WORKING_SET = 20;
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public UsageStatsManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public int getAppStandbyBucket() { a2w.Stubs.hit("android.app.usage.UsageStatsManager.getAppStandbyBucket"); return 0; }
  @a2w.NotImplemented public boolean isAppInactive(java.lang.String packageName) { a2w.Stubs.hit("android.app.usage.UsageStatsManager.isAppInactive"); return false; }
  @a2w.NotImplemented public java.util.Map<java.lang.String, android.app.usage.UsageStats> queryAndAggregateUsageStats(long beginTime, long endTime) { a2w.Stubs.hit("android.app.usage.UsageStatsManager.queryAndAggregateUsageStats"); return null; }
  @a2w.NotImplemented public java.util.List<android.app.usage.ConfigurationStats> queryConfigurations(int intervalType, long beginTime, long endTime) { a2w.Stubs.hit("android.app.usage.UsageStatsManager.queryConfigurations"); return null; }
  @a2w.NotImplemented public java.util.List<android.app.usage.EventStats> queryEventStats(int intervalType, long beginTime, long endTime) { a2w.Stubs.hit("android.app.usage.UsageStatsManager.queryEventStats"); return null; }
  @a2w.NotImplemented public android.app.usage.UsageEvents queryEvents(long beginTime, long endTime) { a2w.Stubs.hit("android.app.usage.UsageStatsManager.queryEvents"); return null; }
  @a2w.NotImplemented public android.app.usage.UsageEvents queryEvents(android.app.usage.UsageEventsQuery query) { a2w.Stubs.hit("android.app.usage.UsageStatsManager.queryEvents"); return null; }
  @a2w.NotImplemented public android.app.usage.UsageEvents queryEventsForSelf(long beginTime, long endTime) { a2w.Stubs.hit("android.app.usage.UsageStatsManager.queryEventsForSelf"); return null; }
  @a2w.NotImplemented public java.util.List<android.app.usage.UsageStats> queryUsageStats(int intervalType, long beginTime, long endTime) { a2w.Stubs.hit("android.app.usage.UsageStatsManager.queryUsageStats"); return null; }
}
