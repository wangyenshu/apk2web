# android.health.connect

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AggregateRecordsGroupedByDurationResponse — declaration; API 34+ (0 +, 0 *, 0 ~, 5 -)

```
- get(AggregationType): Object  [API 34+]
- getDataOrigins(AggregationType): Set  [API 35+]
- getEndTime(): Instant  [API 34+]
- getStartTime(): Instant  [API 34+]
- getZoneOffset(AggregationType): ZoneOffset  [API 34+]
```

## AggregateRecordsGroupedByPeriodResponse — declaration; API 34+ (0 +, 0 *, 0 ~, 5 -)

```
- get(AggregationType): Object  [API 34+]
- getDataOrigins(AggregationType): Set  [API 35+]
- getEndTime(): LocalDateTime  [API 34+]
- getStartTime(): LocalDateTime  [API 34+]
- getZoneOffset(AggregationType): ZoneOffset  [API 34+]
```

## AggregateRecordsRequest — declaration; API 34+ (0 +, 1 *, 0 ~, 2 -)

```
- getAggregationTypes(): Set  [API 34+]
- getDataOriginsFilters(): Set  [API 34+]
* getTimeRangeFilter(): TimeRangeFilter  [API 34+]
```

## AggregateRecordsRequest.Builder — declaration; API 34+ (0 +, 2 *, 0 ~, 2 -)

```
* Builder(TimeRangeFilter)  [API 34+]
- addAggregationType(AggregationType): AggregateRecordsRequest.Builder  [API 34+]
- addDataOriginsFilter(DataOrigin): AggregateRecordsRequest.Builder  [API 34+]
* build(): AggregateRecordsRequest  [API 34+]
```

## AggregateRecordsResponse — declaration; API 34+ (0 +, 0 *, 0 ~, 3 -)

```
- get(AggregationType): Object  [API 34+]
- getDataOrigins(AggregationType): Set  [API 34+]
- getZoneOffset(AggregationType): ZoneOffset  [API 34+]
```

## CreateMedicalDataSourceRequest — declaration; API 36+ (0 +, 2 *, 0 ~, 0 -)

```
* getDisplayName(): String  [API 36+]
* getFhirBaseUri(): Uri  [API 36+]
```

## CreateMedicalDataSourceRequest.Builder — declaration; API 36+ (0 +, 5 *, 0 ~, 1 -)

```
* Builder(CreateMedicalDataSourceRequest)  [API 36+]
- Builder(CreateMedicalDataSourceRequest.Builder)  [API 36+]
* Builder(Uri, String)  [API 36+]
* build(): CreateMedicalDataSourceRequest  [API 36+]
* setDisplayName(String): CreateMedicalDataSourceRequest.Builder  [API 36+]
* setFhirBaseUri(Uri): CreateMedicalDataSourceRequest.Builder  [API 36+]
```

## DeleteMedicalResourcesRequest — declaration; API 36+ (0 +, 0 *, 0 ~, 2 -)

```
- getDataSourceIds(): Set  [API 36+]
- getMedicalResourceTypes(): Set  [API 36+]
```

## DeleteMedicalResourcesRequest.Builder — declaration; API 36+ (0 +, 0 *, 0 ~, 7 -)

```
- Builder()  [API 36+]
- Builder(DeleteMedicalResourcesRequest.Builder)  [API 36+]
- addDataSourceId(String): DeleteMedicalResourcesRequest.Builder  [API 36+]
- addMedicalResourceType(int): DeleteMedicalResourcesRequest.Builder  [API 36+]
- build(): DeleteMedicalResourcesRequest  [API 36+]
- clearDataSourceIds(): DeleteMedicalResourcesRequest.Builder  [API 36+]
- clearMedicalResourceTypes(): DeleteMedicalResourcesRequest.Builder  [API 36+]
```

## GetMedicalDataSourcesRequest — declaration; API 36+ (0 +, 0 *, 0 ~, 1 -)

```
- getPackageNames(): Set  [API 36+]
```

## GetMedicalDataSourcesRequest.Builder — declaration; API 36+ (0 +, 0 *, 0 ~, 3 -)

```
- Builder()  [API 36+]
- addPackageName(String): GetMedicalDataSourcesRequest.Builder  [API 36+]
- build(): GetMedicalDataSourcesRequest  [API 36+]
```

## HealthConnectException — declaration; API 34+ (0 +, 0 *, 0 ~, 1 -)

```
- getErrorCode(): int  [API 34+]
```

## HealthConnectManager — framework; API 34+ (0 +, 0 *, 0 ~, 19 -)

```
- aggregate(AggregateRecordsRequest, Executor, OutcomeReceiver): void  [API 34+]
- aggregateGroupByDuration(AggregateRecordsRequest, Duration, Executor, OutcomeReceiver): void  [API 34+]
- aggregateGroupByPeriod(AggregateRecordsRequest, Period, Executor, OutcomeReceiver): void  [API 34+]
- createMedicalDataSource(CreateMedicalDataSourceRequest, Executor, OutcomeReceiver): void  [API 36+]
- deleteMedicalDataSourceWithData(String, Executor, OutcomeReceiver): void  [API 36+]
- deleteMedicalResources(DeleteMedicalResourcesRequest, Executor, OutcomeReceiver): void  [API 36+]
- deleteMedicalResources(List, Executor, OutcomeReceiver): void  [API 36+]
- deleteRecords(Class, TimeRangeFilter, Executor, OutcomeReceiver): void  [API 34+]
- deleteRecords(List, Executor, OutcomeReceiver): void  [API 34+]
- getChangeLogToken(ChangeLogTokenRequest, Executor, OutcomeReceiver): void  [API 34+]
- getChangeLogs(ChangeLogsRequest, Executor, OutcomeReceiver): void  [API 34+]
- getMedicalDataSources(GetMedicalDataSourcesRequest, Executor, OutcomeReceiver): void  [API 36+]
- getMedicalDataSources(List, Executor, OutcomeReceiver): void  [API 36+]
- insertRecords(List, Executor, OutcomeReceiver): void  [API 34+]
- readMedicalResources(List, Executor, OutcomeReceiver): void  [API 36+]
- readMedicalResources(ReadMedicalResourcesRequest, Executor, OutcomeReceiver): void  [API 36+]
- readRecords(ReadRecordsRequest, Executor, OutcomeReceiver): void  [API 34+]
- updateRecords(List, Executor, OutcomeReceiver): void  [API 34+]
- upsertMedicalResources(List, Executor, OutcomeReceiver): void  [API 36+]
```

## HealthPermissions — declaration; API 34+ (0 +, 0 *, 0 ~, 0 -)

## InsertRecordsResponse — declaration; API 34+ (0 +, 0 *, 0 ~, 1 -)

```
- getRecords(): List  [API 34+]
```

## LocalTimeRangeFilter — declaration; API 34+ (0 +, 2 *, 0 ~, 3 -)

```
- equals(Object): boolean  [API 34+]
* getEndTime(): LocalDateTime  [API 34+]
* getStartTime(): LocalDateTime  [API 34+]
- hashCode(): int  [API 34+]
- isBounded(): boolean  [API 34+]
```

## LocalTimeRangeFilter.Builder — declaration; API 34+ (0 +, 4 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* build(): LocalTimeRangeFilter  [API 34+]
* setEndTime(LocalDateTime): LocalTimeRangeFilter.Builder  [API 34+]
* setStartTime(LocalDateTime): LocalTimeRangeFilter.Builder  [API 34+]
```

## MedicalResourceId — declaration; API 36+ (0 +, 4 *, 0 ~, 1 -)

```
* MedicalResourceId(String, int, String)  [API 36+]
- fromFhirReference(String, String): MedicalResourceId  [API 36+]
* getDataSourceId(): String  [API 36+]
* getFhirResourceId(): String  [API 36+]
* getFhirResourceType(): int  [API 36+]
```

## ReadMedicalResourcesInitialRequest — declaration; API 36+ (0 +, 2 *, 0 ~, 0 -)

```
* getDataSourceIds(): Set  [API 36+]
* getMedicalResourceType(): int  [API 36+]
```

## ReadMedicalResourcesInitialRequest.Builder — declaration; API 36+ (0 +, 5 *, 0 ~, 4 -)

```
* Builder(ReadMedicalResourcesInitialRequest)  [API 36+]
- Builder(ReadMedicalResourcesInitialRequest.Builder)  [API 36+]
* Builder(int)  [API 36+]
- addDataSourceId(String): ReadMedicalResourcesInitialRequest.Builder  [API 36+]
- addDataSourceIds(Set): ReadMedicalResourcesInitialRequest.Builder  [API 36+]
* build(): ReadMedicalResourcesInitialRequest  [API 36+]
- clearDataSourceIds(): ReadMedicalResourcesInitialRequest.Builder  [API 36+]
* setMedicalResourceType(int): ReadMedicalResourcesInitialRequest.Builder  [API 36+]
* setPageSize(int): ReadMedicalResourcesInitialRequest.Builder  [API 36+]
```

## ReadMedicalResourcesPageRequest — declaration; API 36+ (0 +, 1 *, 0 ~, 0 -)

```
* getPageToken(): String  [API 36+]
```

## ReadMedicalResourcesPageRequest.Builder — declaration; API 36+ (0 +, 5 *, 0 ~, 1 -)

```
* Builder(ReadMedicalResourcesPageRequest)  [API 36+]
- Builder(ReadMedicalResourcesPageRequest.Builder)  [API 36+]
* Builder(String)  [API 36+]
* build(): ReadMedicalResourcesPageRequest  [API 36+]
* setPageSize(int): ReadMedicalResourcesPageRequest.Builder  [API 36+]
* setPageToken(String): ReadMedicalResourcesPageRequest.Builder  [API 36+]
```

## ReadMedicalResourcesRequest — declaration; API 36+ (0 +, 1 *, 0 ~, 0 -)

```
* getPageSize(): int  [API 36+]
```

## ReadMedicalResourcesResponse — declaration; API 36+ (0 +, 3 *, 0 ~, 0 -)

```
* ReadMedicalResourcesResponse(List, String)  [API 36+]
* getMedicalResources(): List  [API 36+]
* getNextPageToken(): String  [API 36+]
```

## ReadRecordsRequest — declaration; API 34+ (0 +, 1 *, 0 ~, 0 -)

```
* getRecordType(): Class  [API 34+]
```

## ReadRecordsRequestUsingFilters — declaration; API 34+ (0 +, 4 *, 0 ~, 1 -)

```
- getDataOrigins(): Set  [API 34+]
* getPageSize(): int  [API 34+]
* getPageToken(): long  [API 34+]
* getTimeRangeFilter(): TimeRangeFilter  [API 34+]
* isAscending(): boolean  [API 34+]
```

## ReadRecordsRequestUsingFilters.Builder — declaration; API 34+ (0 +, 6 *, 0 ~, 1 -)

```
* Builder(Class)  [API 34+]
- addDataOrigins(DataOrigin): ReadRecordsRequestUsingFilters.Builder  [API 34+]
* build(): ReadRecordsRequestUsingFilters  [API 34+]
* setAscending(boolean): ReadRecordsRequestUsingFilters.Builder  [API 34+]
* setPageSize(int): ReadRecordsRequestUsingFilters.Builder  [API 34+]
* setPageToken(long): ReadRecordsRequestUsingFilters.Builder  [API 34+]
* setTimeRangeFilter(TimeRangeFilter): ReadRecordsRequestUsingFilters.Builder  [API 34+]
```

## ReadRecordsRequestUsingIds — declaration; API 34+ (0 +, 0 *, 0 ~, 1 -)

```
- getRecordIdFilters(): List  [API 34+]
```

## ReadRecordsRequestUsingIds.Builder — declaration; API 34+ (0 +, 2 *, 0 ~, 2 -)

```
* Builder(Class)  [API 34+]
- addClientRecordId(String): ReadRecordsRequestUsingIds.Builder  [API 34+]
- addId(String): ReadRecordsRequestUsingIds.Builder  [API 34+]
* build(): ReadRecordsRequestUsingIds  [API 34+]
```

## ReadRecordsResponse — declaration; API 34+ (0 +, 0 *, 0 ~, 2 -)

```
- getNextPageToken(): long  [API 34+]
- getRecords(): List  [API 34+]
```

## RecordIdFilter — declaration; API 34+ (0 +, 0 *, 0 ~, 5 -)

```
- fromClientRecordId(Class, String): RecordIdFilter [static]  [API 34+]
- fromId(Class, String): RecordIdFilter [static]  [API 34+]
- getClientRecordId(): String  [API 34+]
- getId(): String  [API 34+]
- getRecordType(): Class  [API 34+]
```

## TimeInstantRangeFilter — declaration; API 34+ (0 +, 2 *, 0 ~, 3 -)

```
- equals(Object): boolean  [API 34+]
* getEndTime(): Instant  [API 34+]
* getStartTime(): Instant  [API 34+]
- hashCode(): int  [API 34+]
- isBounded(): boolean  [API 34+]
```

## TimeInstantRangeFilter.Builder — declaration; API 34+ (0 +, 4 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* build(): TimeInstantRangeFilter  [API 34+]
* setEndTime(Instant): TimeInstantRangeFilter.Builder  [API 34+]
* setStartTime(Instant): TimeInstantRangeFilter.Builder  [API 34+]
```

## TimeRangeFilter — declaration; API 34+ (0 +, 0 *, 0 ~, 0 -)

## UpsertMedicalResourceRequest — declaration; API 36+ (0 +, 3 *, 0 ~, 0 -)

```
* getData(): String  [API 36+]
* getDataSourceId(): String  [API 36+]
* getFhirVersion(): FhirVersion  [API 36+]
```

## UpsertMedicalResourceRequest.Builder — declaration; API 36+ (0 +, 6 *, 0 ~, 1 -)

```
* Builder(String, FhirVersion, String)  [API 36+]
* Builder(UpsertMedicalResourceRequest)  [API 36+]
- Builder(UpsertMedicalResourceRequest.Builder)  [API 36+]
* build(): UpsertMedicalResourceRequest  [API 36+]
* setData(String): UpsertMedicalResourceRequest.Builder  [API 36+]
* setDataSourceId(String): UpsertMedicalResourceRequest.Builder  [API 36+]
* setFhirVersion(FhirVersion): UpsertMedicalResourceRequest.Builder  [API 36+]
```

