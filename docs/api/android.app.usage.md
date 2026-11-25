# android.app.usage

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ConfigurationStats — declaration; API 21+ (0 +, 0 *, 0 ~, 9 -)

```
- CREATOR [field]  [API 21+]
- ConfigurationStats(ConfigurationStats)  [API 21+]
- describeContents(): int  [API 21+]
- getActivationCount(): int  [API 21+]
- getConfiguration(): Configuration  [API 21+]
- getFirstTimeStamp(): long  [API 21+]
- getLastTimeActive(): long  [API 21+]
- getLastTimeStamp(): long  [API 21+]
- getTotalTimeActive(): long  [API 21+]
- writeToParcel(Parcel, int): void  [API 21+]
```

## EventStats — declaration; API 28+ (0 +, 0 *, 0 ~, 10 -)

```
- CREATOR [field]  [API 28+]
- EventStats(EventStats)  [API 28+]
- add(EventStats): void  [API 28+]
- describeContents(): int  [API 28+]
- getCount(): int  [API 28+]
- getEventType(): int  [API 28+]
- getFirstTimeStamp(): long  [API 28+]
- getLastEventTime(): long  [API 28+]
- getLastTimeStamp(): long  [API 28+]
- getTotalTime(): long  [API 28+]
- writeToParcel(Parcel, int): void  [API 28+]
```

## ExternalStorageStats — declaration; API 26+ (0 +, 0 *, 0 ~, 7 -)

```
- CREATOR [field]  [API 26+]
- describeContents(): int  [API 26+]
- getAppBytes(): long  [API 26+]
- getAudioBytes(): long  [API 26+]
- getImageBytes(): long  [API 26+]
- getTotalBytes(): long  [API 26+]
- getVideoBytes(): long  [API 26+]
- writeToParcel(Parcel, int): void  [API 26+]
```

## NetworkStats — declaration; API 23+ (0 +, 0 *, 0 ~, 4 -)

```
- close(): void  [API 23+]
- finalize(): void  [API 23+]
- getNextBucket(NetworkStats.Bucket): boolean  [API 23+]
- hasNextBucket(): boolean  [API 23+]
```

## NetworkStats.Bucket — declaration; API 23+ (0 +, 0 *, 0 ~, 13 -)

```
- Bucket()  [API 23+]
- getDefaultNetworkStatus(): int  [API 28+]
- getEndTimeStamp(): long  [API 23+]
- getMetered(): int  [API 26+]
- getRoaming(): int  [API 24+]
- getRxBytes(): long  [API 23+]
- getRxPackets(): long  [API 23+]
- getStartTimeStamp(): long  [API 23+]
- getState(): int  [API 23+]
- getTag(): int  [API 24+]
- getTxBytes(): long  [API 23+]
- getTxPackets(): long  [API 23+]
- getUid(): int  [API 23+]
```

## NetworkStatsManager — framework; API 23+ (0 +, 0 *, 0 ~, 10 -)

```
- queryDetails(int, String, long, long): NetworkStats  [API 23+]
- queryDetailsForUid(int, String, long, long, int): NetworkStats  [API 23+]
- queryDetailsForUidTag(int, String, long, long, int, int): NetworkStats  [API 24+]
- queryDetailsForUidTagState(int, String, long, long, int, int, int): NetworkStats  [API 28+]
- querySummary(int, String, long, long): NetworkStats  [API 23+]
- querySummaryForDevice(int, String, long, long): NetworkStats.Bucket  [API 23+]
- querySummaryForUser(int, String, long, long): NetworkStats.Bucket  [API 23+]
- registerUsageCallback(int, String, long, NetworkStatsManager.UsageCallback): void  [API 24+]
- registerUsageCallback(int, String, long, NetworkStatsManager.UsageCallback, Handler): void  [API 24+]
- unregisterUsageCallback(NetworkStatsManager.UsageCallback): void  [API 24+]
```

## NetworkStatsManager.UsageCallback — framework; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ UsageCallback()  [API 24+]
+ onThresholdReached(int, String): void  [API 24+]
```

## StorageStats — declaration; API 26+ (0 +, 0 *, 0 ~, 7 -)

```
- CREATOR [field]  [API 26+]
- describeContents(): int  [API 26+]
- getAppBytes(): long  [API 26+]
- getAppBytesByDataType(int): long  [API 35+]
- getCacheBytes(): long  [API 26+]
- getDataBytes(): long  [API 26+]
- getExternalCacheBytes(): long  [API 31+]
- writeToParcel(Parcel, int): void  [API 26+]
```

## StorageStatsManager — framework; API 26+ (0 +, 0 *, 0 ~, 6 -)

```
- getFreeBytes(UUID): long  [API 26+]
- getTotalBytes(UUID): long  [API 26+]
- queryExternalStatsForUser(UUID, UserHandle): ExternalStorageStats  [API 26+]
- queryStatsForPackage(UUID, String, UserHandle): StorageStats  [API 26+]
- queryStatsForUid(UUID, int): StorageStats  [API 26+]
- queryStatsForUser(UUID, UserHandle): StorageStats  [API 26+]
```

## UsageEvents — declaration; API 21+ (0 +, 0 *, 0 ~, 4 -)

```
- CREATOR [field]  [API 21+]
- describeContents(): int  [API 21+]
- getNextEvent(UsageEvents.Event): boolean  [API 21+]
- hasNextEvent(): boolean  [API 21+]
- writeToParcel(Parcel, int): void  [API 21+]
```

## UsageEvents.Event — declaration; API 21+ (0 +, 0 *, 0 ~, 9 -)

```
- Event()  [API 21+]
- getAppStandbyBucket(): int  [API 28+]
- getClassName(): String  [API 21+]
- getConfiguration(): Configuration  [API 21+]
- getEventType(): int  [API 21+]
- getExtras(): PersistableBundle  [API 35+]
- getPackageName(): String  [API 21+]
- getShortcutId(): String  [API 25+]
- getTimeStamp(): long  [API 21+]
```

## UsageEventsQuery — declaration; API 35+ (0 +, 3 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 35+]
- describeContents(): int  [API 35+]
* getBeginTimeMillis(): long  [API 35+]
* getEndTimeMillis(): long  [API 35+]
* getEventTypes(): int[]  [API 35+]
- getPackageNames(): Set  [API 35+]
- writeToParcel(Parcel, int): void  [API 35+]
```

## UsageEventsQuery.Builder — declaration; API 35+ (0 +, 3 *, 0 ~, 1 -)

```
* Builder(long, long)  [API 35+]
* build(): UsageEventsQuery  [API 35+]
* setEventTypes(int[]): UsageEventsQuery.Builder  [API 35+]
- setPackageNames(String[]): UsageEventsQuery.Builder  [API 35+]
```

## UsageStats — declaration; API 21+ (0 +, 0 *, 0 ~, 13 -)

```
- CREATOR [field]  [API 21+]
- UsageStats(UsageStats)  [API 21+]
- add(UsageStats): void  [API 21+]
- describeContents(): int  [API 21+]
- getFirstTimeStamp(): long  [API 21+]
- getLastTimeForegroundServiceUsed(): long  [API 29+]
- getLastTimeStamp(): long  [API 21+]
- getLastTimeUsed(): long  [API 21+]
- getLastTimeVisible(): long  [API 29+]
- getPackageName(): String  [API 21+]
- getTotalTimeForegroundServiceUsed(): long  [API 29+]
- getTotalTimeInForeground(): long  [API 21+]
- getTotalTimeVisible(): long  [API 29+]
- writeToParcel(Parcel, int): void  [API 21+]
```

## UsageStatsManager — framework; API 21+ (0 +, 0 *, 0 ~, 9 -)

```
- getAppStandbyBucket(): int  [API 28+]
- isAppInactive(String): boolean  [API 23+]
- queryAndAggregateUsageStats(long, long): Map  [API 21+]
- queryConfigurations(int, long, long): List  [API 21+]
- queryEventStats(int, long, long): List  [API 28+]
- queryEvents(UsageEventsQuery): UsageEvents  [API 35+]
- queryEvents(long, long): UsageEvents  [API 21+]
- queryEventsForSelf(long, long): UsageEvents  [API 28+]
- queryUsageStats(int, long, long): List  [API 21+]
```

