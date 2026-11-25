# android.app.job

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## JobInfo — framework; API 21+ (37 +, 0 *, 3 ~, 0 -)

```
+ CREATOR [field]  [API 21+]
~ describeContents(): int  [API 21+]
+ equals(Object): boolean  [API 21+]
+ getBackoffPolicy(): int  [API 21+]
+ getClipData(): ClipData  [API 26+]
+ getClipGrantFlags(): int  [API 26+]
+ getDebugTags(): Set  [API 35+]
+ getEstimatedNetworkDownloadBytes(): long  [API 28+]
+ getEstimatedNetworkUploadBytes(): long  [API 28+]
+ getExtras(): PersistableBundle  [API 21+]
+ getFlexMillis(): long  [API 24+]
+ getId(): int  [API 21+]
+ getInitialBackoffMillis(): long  [API 21+]
+ getIntervalMillis(): long  [API 21+]
+ getMaxExecutionDelayMillis(): long  [API 21+]
~ getMinFlexMillis(): long [static]  [API 24+]
+ getMinLatencyMillis(): long  [API 21+]
~ getMinPeriodMillis(): long [static]  [API 24+]
+ getMinimumNetworkChunkBytes(): long  [API 33+]
+ getNetworkType(): int  [API 21+, deprecated in 28]
+ getPriority(): int  [API 33+]
+ getRequiredNetwork(): NetworkRequest  [API 28+]
+ getService(): ComponentName  [API 21+]
+ getTraceTag(): String  [API 35+]
+ getTransientExtras(): Bundle  [API 26+]
+ getTriggerContentMaxDelay(): long  [API 24+]
+ getTriggerContentUpdateDelay(): long  [API 24+]
+ getTriggerContentUris(): JobInfo.TriggerContentUri[]  [API 24+]
+ hashCode(): int  [API 21+]
+ isExpedited(): boolean  [API 31+]
+ isImportantWhileForeground(): boolean  [API 28+]
+ isPeriodic(): boolean  [API 21+]
+ isPersisted(): boolean  [API 21+]
+ isPrefetch(): boolean  [API 28+]
+ isRequireBatteryNotLow(): boolean  [API 26+]
+ isRequireCharging(): boolean  [API 21+]
+ isRequireDeviceIdle(): boolean  [API 21+]
+ isRequireStorageNotLow(): boolean  [API 26+]
+ isUserInitiated(): boolean  [API 34+]
+ toString(): String  [API 21+]
+ writeToParcel(Parcel, int): void  [API 21+]
```

## JobInfo.Builder — framework; API 21+ (30 +, 0 *, 0 ~, 0 -)

```
+ Builder(int, ComponentName)  [API 21+]
+ addDebugTag(String): JobInfo.Builder  [API 35+]
+ addTriggerContentUri(JobInfo.TriggerContentUri): JobInfo.Builder  [API 24+]
+ build(): JobInfo  [API 21+]
+ removeDebugTag(String): JobInfo.Builder  [API 35+]
+ setBackoffCriteria(long, int): JobInfo.Builder  [API 21+]
+ setClipData(ClipData, int): JobInfo.Builder  [API 26+]
+ setEstimatedNetworkBytes(long, long): JobInfo.Builder  [API 28+]
+ setExpedited(boolean): JobInfo.Builder  [API 31+]
+ setExtras(PersistableBundle): JobInfo.Builder  [API 21+]
+ setImportantWhileForeground(boolean): JobInfo.Builder  [API 28+, deprecated in 31]
+ setMinimumLatency(long): JobInfo.Builder  [API 21+]
+ setMinimumNetworkChunkBytes(long): JobInfo.Builder  [API 33+]
+ setOverrideDeadline(long): JobInfo.Builder  [API 21+]
+ setPeriodic(long): JobInfo.Builder  [API 21+]
+ setPeriodic(long, long): JobInfo.Builder  [API 24+]
+ setPersisted(boolean): JobInfo.Builder  [API 21+]
+ setPrefetch(boolean): JobInfo.Builder  [API 28+]
+ setPriority(int): JobInfo.Builder  [API 33+]
+ setRequiredNetwork(NetworkRequest): JobInfo.Builder  [API 28+]
+ setRequiredNetworkType(int): JobInfo.Builder  [API 21+]
+ setRequiresBatteryNotLow(boolean): JobInfo.Builder  [API 26+]
+ setRequiresCharging(boolean): JobInfo.Builder  [API 21+]
+ setRequiresDeviceIdle(boolean): JobInfo.Builder  [API 21+]
+ setRequiresStorageNotLow(boolean): JobInfo.Builder  [API 26+]
+ setTraceTag(String): JobInfo.Builder  [API 35+]
+ setTransientExtras(Bundle): JobInfo.Builder  [API 26+]
+ setTriggerContentMaxDelay(long): JobInfo.Builder  [API 24+]
+ setTriggerContentUpdateDelay(long): JobInfo.Builder  [API 24+]
+ setUserInitiated(boolean): JobInfo.Builder  [API 34+]
```

## JobInfo.TriggerContentUri — framework; API 24+ (6 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 24+]
+ TriggerContentUri(Uri, int)  [API 24+]
~ describeContents(): int  [API 24+]
+ equals(Object): boolean  [API 24+]
+ getFlags(): int  [API 24+]
+ getUri(): Uri  [API 24+]
+ hashCode(): int  [API 24+]
+ writeToParcel(Parcel, int): void  [API 24+]
```

## JobParameters — framework; API 21+ (13 +, 0 *, 4 ~, 0 -)

```
+ CREATOR [field]  [API 21+]
+ completeWork(JobWorkItem): void  [API 26+]
+ dequeueWork(): JobWorkItem  [API 26+]
~ describeContents(): int  [API 21+]
+ getClipData(): ClipData  [API 26+]
+ getClipGrantFlags(): int  [API 26+]
+ getExtras(): PersistableBundle  [API 21+]
+ getJobId(): int  [API 21+]
+ getJobNamespace(): String  [API 34+]
~ getNetwork(): Network  [API 28+]
+ getStopReason(): int  [API 31+]
+ getTransientExtras(): Bundle  [API 26+]
~ getTriggeredContentAuthorities(): String[]  [API 24+]
~ getTriggeredContentUris(): Uri[]  [API 24+]
+ isExpeditedJob(): boolean  [API 31+]
+ isOverrideDeadlineExpired(): boolean  [API 21+]
+ isUserInitiatedJob(): boolean  [API 34+]
+ writeToParcel(Parcel, int): void  [API 21+]
```

## JobScheduler — framework; API 21+ (7 +, 0 *, 6 ~, 0 -)

```
+ JobScheduler()  [API 21+]
~ canRunUserInitiatedJobs(): boolean  [API 34+]
+ cancel(int): void  [API 21+]
+ cancelAll(): void  [API 21+]
~ cancelInAllNamespaces(): void  [API 34+]
+ enqueue(JobInfo, JobWorkItem): int  [API 26+]
~ forNamespace(String): JobScheduler  [API 34+]
+ getAllPendingJobs(): List  [API 21+]
~ getNamespace(): String  [API 34+]
+ getPendingJob(int): JobInfo  [API 24+]
~ getPendingJobReason(int): int  [API 34+]
~ getPendingJobsInAllNamespaces(): Map  [API 34+]
+ schedule(JobInfo): int  [API 21+]
```

## JobService — framework; API 21+ (7 +, 0 *, 4 ~, 0 -)

```
+ JobService()  [API 21+]
+ jobFinished(JobParameters, boolean): void  [API 21+]
+ onBind(Intent): IBinder  [API 21+]
+ onNetworkChanged(JobParameters): void  [API 34+]
+ onStartJob(JobParameters): boolean  [API 21+]
+ onStopJob(JobParameters): boolean  [API 21+]
+ setNotification(JobParameters, int, Notification, int): void  [API 34+]
~ updateEstimatedNetworkBytes(JobParameters, JobWorkItem, long, long): void  [API 34+]
~ updateEstimatedNetworkBytes(JobParameters, long, long): void  [API 34+]
~ updateTransferredNetworkBytes(JobParameters, JobWorkItem, long, long): void  [API 34+]
~ updateTransferredNetworkBytes(JobParameters, long, long): void  [API 34+]
```

## JobServiceEngine — framework; API 26+ (7 +, 0 *, 2 ~, 0 -)

```
+ JobServiceEngine(Service)  [API 26+]
+ getBinder(): IBinder  [API 26+]
+ jobFinished(JobParameters, boolean): void  [API 26+]
+ onNetworkChanged(JobParameters): void  [API 34+]
+ onStartJob(JobParameters): boolean  [API 26+]
+ onStopJob(JobParameters): boolean  [API 26+]
+ setNotification(JobParameters, int, Notification, int): void  [API 34+]
~ updateEstimatedNetworkBytes(JobParameters, JobWorkItem, long, long): void  [API 34+]
~ updateTransferredNetworkBytes(JobParameters, JobWorkItem, long, long): void  [API 34+]
```

## JobWorkItem — framework; API 26+ (11 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 26+]
+ JobWorkItem(Intent)  [API 26+]
+ JobWorkItem(Intent, long, long)  [API 28+]
+ JobWorkItem(Intent, long, long, long)  [API 33+]
~ describeContents(): int  [API 26+]
+ getDeliveryCount(): int  [API 26+]
+ getEstimatedNetworkDownloadBytes(): long  [API 28+]
+ getEstimatedNetworkUploadBytes(): long  [API 28+]
+ getExtras(): PersistableBundle  [API 34+]
+ getIntent(): Intent  [API 26+]
+ getMinimumNetworkChunkBytes(): long  [API 33+]
+ toString(): String  [API 26+]
+ writeToParcel(Parcel, int): void  [API 26+]
```

## JobWorkItem.Builder — framework; API 34+ (6 +, 0 *, 0 ~, 0 -)

```
+ Builder()  [API 34+]
+ build(): JobWorkItem  [API 34+]
+ setEstimatedNetworkBytes(long, long): JobWorkItem.Builder  [API 34+]
+ setExtras(PersistableBundle): JobWorkItem.Builder  [API 34+]
+ setIntent(Intent): JobWorkItem.Builder  [API 34+]
+ setMinimumNetworkChunkBytes(long): JobWorkItem.Builder  [API 34+]
```

