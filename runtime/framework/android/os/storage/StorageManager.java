// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.os.storage;

public class StorageManager {
  public static final java.lang.String ACTION_CLEAR_APP_CACHE = "android.os.storage.action.CLEAR_APP_CACHE";
  public static final java.lang.String ACTION_MANAGE_STORAGE = "android.os.storage.action.MANAGE_STORAGE";
  public static final java.lang.String EXTRA_REQUESTED_BYTES = "android.os.storage.extra.REQUESTED_BYTES";
  public static final java.lang.String EXTRA_UUID = "android.os.storage.extra.UUID";
  @a2w.NotImplemented public static final a2w.jdk.util.UUID UUID_DEFAULT = null;
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public StorageManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public void allocateBytes(a2w.jdk.FileDescriptor fd, long bytes) throws java.io.IOException { a2w.Stubs.hit("android.os.storage.StorageManager.allocateBytes"); }
  @a2w.NotImplemented public void allocateBytes(a2w.jdk.util.UUID storageUuid, long bytes) throws java.io.IOException { a2w.Stubs.hit("android.os.storage.StorageManager.allocateBytes"); }
  @a2w.NotImplemented public long getAllocatableBytes(a2w.jdk.util.UUID storageUuid) throws java.io.IOException { a2w.Stubs.hit("android.os.storage.StorageManager.getAllocatableBytes"); return 0L; }
  @a2w.NotImplemented public long getCacheQuotaBytes(a2w.jdk.util.UUID storageUuid) throws java.io.IOException { a2w.Stubs.hit("android.os.storage.StorageManager.getCacheQuotaBytes"); return 0L; }
  @a2w.NotImplemented public long getCacheSizeBytes(a2w.jdk.util.UUID storageUuid) throws java.io.IOException { a2w.Stubs.hit("android.os.storage.StorageManager.getCacheSizeBytes"); return 0L; }
  @a2w.NotImplemented public android.app.PendingIntent getManageSpaceActivityIntent(java.lang.String packageName, int requestCode) { a2w.Stubs.hit("android.os.storage.StorageManager.getManageSpaceActivityIntent"); return null; }
  @a2w.NotImplemented public java.lang.String getMountedObbPath(java.lang.String rawPath) { a2w.Stubs.hit("android.os.storage.StorageManager.getMountedObbPath"); return null; }
  @a2w.NotImplemented public android.os.storage.StorageVolume getPrimaryStorageVolume() { a2w.Stubs.hit("android.os.storage.StorageManager.getPrimaryStorageVolume"); return null; }
  @a2w.NotImplemented public java.util.List<android.os.storage.StorageVolume> getRecentStorageVolumes() { a2w.Stubs.hit("android.os.storage.StorageManager.getRecentStorageVolumes"); return null; }
  @a2w.NotImplemented public android.os.storage.StorageVolume getStorageVolume(android.net.Uri uri) { a2w.Stubs.hit("android.os.storage.StorageManager.getStorageVolume"); return null; }
  @a2w.NotImplemented public android.os.storage.StorageVolume getStorageVolume(java.io.File file) { a2w.Stubs.hit("android.os.storage.StorageManager.getStorageVolume"); return null; }
  @a2w.NotImplemented public java.util.List<android.os.storage.StorageVolume> getStorageVolumes() { a2w.Stubs.hit("android.os.storage.StorageManager.getStorageVolumes"); return null; }
  @a2w.NotImplemented public java.util.List<android.os.storage.StorageVolume> getStorageVolumesIncludingSharedProfiles() { a2w.Stubs.hit("android.os.storage.StorageManager.getStorageVolumesIncludingSharedProfiles"); return null; }
  @a2w.NotImplemented public a2w.jdk.util.UUID getUuidForPath(java.io.File path) throws java.io.IOException { a2w.Stubs.hit("android.os.storage.StorageManager.getUuidForPath"); return null; }
  @a2w.NotImplemented public boolean isAllocationSupported(a2w.jdk.FileDescriptor fd) { a2w.Stubs.hit("android.os.storage.StorageManager.isAllocationSupported"); return false; }
  @a2w.NotImplemented public boolean isCacheBehaviorGroup(java.io.File path) throws java.io.IOException { a2w.Stubs.hit("android.os.storage.StorageManager.isCacheBehaviorGroup"); return false; }
  @a2w.NotImplemented public boolean isCacheBehaviorTombstone(java.io.File path) throws java.io.IOException { a2w.Stubs.hit("android.os.storage.StorageManager.isCacheBehaviorTombstone"); return false; }
  @a2w.NotImplemented public boolean isCheckpointSupported() { a2w.Stubs.hit("android.os.storage.StorageManager.isCheckpointSupported"); return false; }
  @a2w.NotImplemented public boolean isEncrypted(java.io.File file) { a2w.Stubs.hit("android.os.storage.StorageManager.isEncrypted"); return false; }
  @a2w.NotImplemented public boolean isObbMounted(java.lang.String rawPath) { a2w.Stubs.hit("android.os.storage.StorageManager.isObbMounted"); return false; }
  @a2w.NotImplemented public boolean mountObb(java.lang.String rawPath, java.lang.String key, android.os.storage.OnObbStateChangeListener listener) { a2w.Stubs.hit("android.os.storage.StorageManager.mountObb"); return false; }
  @a2w.NotImplemented public android.os.ParcelFileDescriptor openProxyFileDescriptor(int mode, android.os.ProxyFileDescriptorCallback callback, android.os.Handler handler) throws java.io.IOException { a2w.Stubs.hit("android.os.storage.StorageManager.openProxyFileDescriptor"); return null; }
  @a2w.NotImplemented public void registerStorageVolumeCallback(java.util.concurrent.Executor executor, android.os.storage.StorageManager.StorageVolumeCallback callback) { a2w.Stubs.hit("android.os.storage.StorageManager.registerStorageVolumeCallback"); }
  @a2w.NotImplemented public void setCacheBehaviorGroup(java.io.File path, boolean group) throws java.io.IOException { a2w.Stubs.hit("android.os.storage.StorageManager.setCacheBehaviorGroup"); }
  @a2w.NotImplemented public void setCacheBehaviorTombstone(java.io.File path, boolean tombstone) throws java.io.IOException { a2w.Stubs.hit("android.os.storage.StorageManager.setCacheBehaviorTombstone"); }
  @a2w.NotImplemented public boolean unmountObb(java.lang.String rawPath, boolean force, android.os.storage.OnObbStateChangeListener listener) { a2w.Stubs.hit("android.os.storage.StorageManager.unmountObb"); return false; }
  @a2w.NotImplemented public void unregisterStorageVolumeCallback(android.os.storage.StorageManager.StorageVolumeCallback callback) { a2w.Stubs.hit("android.os.storage.StorageManager.unregisterStorageVolumeCallback"); }

    public static class StorageVolumeCallback {
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public StorageVolumeCallback(a2w.Hidden hidden) { }
    public StorageVolumeCallback() { this((a2w.Hidden) null); }
    public void onStateChanged(android.os.storage.StorageVolume volume) { }
  }
}
