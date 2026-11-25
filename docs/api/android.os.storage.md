# android.os.storage

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## OnObbStateChangeListener — declaration; API 9+ (2 +, 0 *, 0 ~, 0 -)

```
+ OnObbStateChangeListener()  [API 9+]
+ onObbStateChange(String, int): void  [API 9+]
```

## StorageManager — framework; API 9+ (0 +, 0 *, 0 ~, 27 -)

```
- UUID_DEFAULT [field]  [API 26+]
- allocateBytes(FileDescriptor, long): void  [API 26+]
- allocateBytes(UUID, long): void  [API 26+]
- getAllocatableBytes(UUID): long  [API 26+]
- getCacheQuotaBytes(UUID): long  [API 26+]
- getCacheSizeBytes(UUID): long  [API 26+]
- getManageSpaceActivityIntent(String, int): PendingIntent  [API 31+]
- getMountedObbPath(String): String  [API 9+]
- getPrimaryStorageVolume(): StorageVolume  [API 24+]
- getRecentStorageVolumes(): List  [API 30+]
- getStorageVolume(File): StorageVolume  [API 24+]
- getStorageVolume(Uri): StorageVolume  [API 29+]
- getStorageVolumes(): List  [API 24+]
- getStorageVolumesIncludingSharedProfiles(): List  [API 33+]
- getUuidForPath(File): UUID  [API 26+]
- isAllocationSupported(FileDescriptor): boolean  [API 27+]
- isCacheBehaviorGroup(File): boolean  [API 26+]
- isCacheBehaviorTombstone(File): boolean  [API 26+]
- isCheckpointSupported(): boolean  [API 30+]
- isEncrypted(File): boolean  [API 24+]
- isObbMounted(String): boolean  [API 9+]
- mountObb(String, String, OnObbStateChangeListener): boolean  [API 9+]
- openProxyFileDescriptor(int, ProxyFileDescriptorCallback, Handler): ParcelFileDescriptor  [API 26+]
- registerStorageVolumeCallback(Executor, StorageManager.StorageVolumeCallback): void  [API 30+]
- setCacheBehaviorGroup(File, boolean): void  [API 26+]
- setCacheBehaviorTombstone(File, boolean): void  [API 26+]
- unmountObb(String, boolean, OnObbStateChangeListener): boolean  [API 9+]
- unregisterStorageVolumeCallback(StorageManager.StorageVolumeCallback): void  [API 30+]
```

## StorageManager.StorageVolumeCallback — framework; API 30+ (2 +, 0 *, 0 ~, 0 -)

```
+ StorageVolumeCallback()  [API 30+]
+ onStateChanged(StorageVolume): void  [API 30+]
```

## StorageVolume — declaration; API 24+ (0 +, 0 *, 0 ~, 17 -)

```
- CREATOR [field]  [API 24+]
- createAccessIntent(String): Intent  [API 24+, deprecated in 29]
- createOpenDocumentTreeIntent(): Intent  [API 29+]
- describeContents(): int  [API 24+]
- equals(Object): boolean  [API 24+]
- getDescription(Context): String  [API 24+]
- getDirectory(): File  [API 30+]
- getMediaStoreVolumeName(): String  [API 30+]
- getOwner(): UserHandle  [API 33+]
- getState(): String  [API 24+]
- getStorageUuid(): UUID  [API 31+]
- getUuid(): String  [API 24+]
- hashCode(): int  [API 24+]
- isEmulated(): boolean  [API 24+]
- isPrimary(): boolean  [API 24+]
- isRemovable(): boolean  [API 24+]
- toString(): String  [API 24+]
- writeToParcel(Parcel, int): void  [API 24+]
```

