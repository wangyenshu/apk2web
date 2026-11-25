# android.health.connect.changelog

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ChangeLogTokenRequest — declaration; API 34+ (0 +, 0 *, 0 ~, 4 -)

```
- CREATOR [field]  [API 34+]
- describeContents(): int  [API 34+]
- getDataOriginFilters(): Set  [API 34+]
- getRecordTypes(): Set  [API 34+]
- writeToParcel(Parcel, int): void  [API 34+]
```

## ChangeLogTokenRequest.Builder — declaration; API 34+ (0 +, 0 *, 0 ~, 4 -)

```
- Builder()  [API 34+]
- addDataOriginFilter(DataOrigin): ChangeLogTokenRequest.Builder  [API 34+]
- addRecordType(Class): ChangeLogTokenRequest.Builder  [API 34+]
- build(): ChangeLogTokenRequest  [API 34+]
```

## ChangeLogTokenResponse — declaration; API 34+ (0 +, 0 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 34+]
- describeContents(): int  [API 34+]
- getToken(): String  [API 34+]
- writeToParcel(Parcel, int): void  [API 34+]
```

## ChangeLogsRequest — declaration; API 34+ (0 +, 4 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* describeContents(): int  [API 34+]
* getPageSize(): int  [API 34+]
* getToken(): String  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## ChangeLogsRequest.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
* Builder(String)  [API 34+]
* build(): ChangeLogsRequest  [API 34+]
* setPageSize(int): ChangeLogsRequest.Builder  [API 34+]
```

## ChangeLogsResponse — declaration; API 34+ (0 +, 0 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 34+]
- describeContents(): int  [API 34+]
- getDeletedLogs(): List  [API 34+]
- getNextChangesToken(): String  [API 34+]
- getUpsertedRecords(): List  [API 34+]
- hasMorePages(): boolean  [API 34+]
- writeToParcel(Parcel, int): void  [API 34+]
```

## ChangeLogsResponse.DeletedLog — declaration; API 34+ (0 +, 0 *, 0 ~, 3 -)

```
- DeletedLog(String, long)  [API 34+]
- getDeletedRecordId(): String  [API 34+]
- getDeletedTime(): Instant  [API 34+]
```

