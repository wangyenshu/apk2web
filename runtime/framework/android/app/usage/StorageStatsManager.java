// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.app.usage;

public class StorageStatsManager {
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public StorageStatsManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public long getFreeBytes(a2w.jdk.util.UUID storageUuid) throws java.io.IOException { a2w.Stubs.hit("android.app.usage.StorageStatsManager.getFreeBytes"); return 0L; }
  @a2w.NotImplemented public long getTotalBytes(a2w.jdk.util.UUID storageUuid) throws java.io.IOException { a2w.Stubs.hit("android.app.usage.StorageStatsManager.getTotalBytes"); return 0L; }
  @a2w.NotImplemented public android.app.usage.ExternalStorageStats queryExternalStatsForUser(a2w.jdk.util.UUID storageUuid, android.os.UserHandle user) throws java.io.IOException { a2w.Stubs.hit("android.app.usage.StorageStatsManager.queryExternalStatsForUser"); return null; }
  @a2w.NotImplemented public android.app.usage.StorageStats queryStatsForPackage(a2w.jdk.util.UUID storageUuid, java.lang.String packageName, android.os.UserHandle user) throws java.io.IOException, android.content.pm.PackageManager.NameNotFoundException { a2w.Stubs.hit("android.app.usage.StorageStatsManager.queryStatsForPackage"); return null; }
  @a2w.NotImplemented public android.app.usage.StorageStats queryStatsForUid(a2w.jdk.util.UUID storageUuid, int uid) throws java.io.IOException { a2w.Stubs.hit("android.app.usage.StorageStatsManager.queryStatsForUid"); return null; }
  @a2w.NotImplemented public android.app.usage.StorageStats queryStatsForUser(a2w.jdk.util.UUID storageUuid, android.os.UserHandle user) throws java.io.IOException { a2w.Stubs.hit("android.app.usage.StorageStatsManager.queryStatsForUser"); return null; }
}
