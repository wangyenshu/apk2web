# android.drm

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## DrmConvertedStatus — declaration; API 11+, deprecated in 30 (0 +, 0 *, 0 ~, 1 -)

```
- DrmConvertedStatus(int, byte[], int)  [API 11+]
- convertedData [field]  [API 11+]
- offset [field]  [API 11+]
- statusCode [field]  [API 11+]
```

## DrmErrorEvent — declaration; API 11+, deprecated in 30 (0 +, 1 *, 0 ~, 1 -)

```
* DrmErrorEvent(int, int, String)  [API 11+]
- DrmErrorEvent(int, int, String, HashMap)  [API 12+]
```

## DrmEvent — declaration; API 11+, deprecated in 30 (0 +, 4 *, 0 ~, 2 -)

```
* DrmEvent(int, int, String)  [API 11+]
- DrmEvent(int, int, String, HashMap)  [API 12+]
- getAttribute(String): Object  [API 12+]
* getMessage(): String  [API 11+]
* getType(): int  [API 11+]
* getUniqueId(): int  [API 11+]
```

## DrmInfo — declaration; API 11+, deprecated in 30 (0 +, 4 *, 0 ~, 5 -)

```
- DrmInfo(int, String, String)  [API 11+]
* DrmInfo(int, byte[], String)  [API 11+]
- get(String): Object  [API 11+]
* getData(): byte[]  [API 11+]
* getInfoType(): int  [API 11+]
* getMimeType(): String  [API 11+]
- iterator(): Iterator  [API 11+]
- keyIterator(): Iterator  [API 11+]
- put(String, Object): void  [API 11+]
```

## DrmInfoEvent — declaration; API 11+, deprecated in 30 (0 +, 1 *, 0 ~, 1 -)

```
* DrmInfoEvent(int, int, String)  [API 11+]
- DrmInfoEvent(int, int, String, HashMap)  [API 12+]
```

## DrmInfoRequest — declaration; API 11+, deprecated in 30 (0 +, 3 *, 0 ~, 4 -)

```
* DrmInfoRequest(int, String)  [API 11+]
- get(String): Object  [API 11+]
* getInfoType(): int  [API 11+]
* getMimeType(): String  [API 11+]
- iterator(): Iterator  [API 11+]
- keyIterator(): Iterator  [API 11+]
- put(String, Object): void  [API 11+]
```

## DrmInfoStatus — declaration; API 11+, deprecated in 30 (0 +, 0 *, 0 ~, 1 -)

```
- DrmInfoStatus(int, int, ProcessedData, String)  [API 11+]
- data [field]  [API 11+]
- infoType [field]  [API 11+]
- mimeType [field]  [API 11+]
- statusCode [field]  [API 11+]
```

## DrmManagerClient — declaration; API 11+, deprecated in 30 (0 +, 0 *, 0 ~, 33 -)

```
- DrmManagerClient(Context)  [API 11+]
- acquireDrmInfo(DrmInfoRequest): DrmInfo  [API 11+]
- acquireRights(DrmInfoRequest): int  [API 11+]
- canHandle(String, String): boolean  [API 11+]
- canHandle(Uri, String): boolean  [API 11+]
- checkRightsStatus(String): int  [API 11+]
- checkRightsStatus(String, int): int  [API 11+]
- checkRightsStatus(Uri): int  [API 11+]
- checkRightsStatus(Uri, int): int  [API 11+]
- close(): void  [API 11+]
- closeConvertSession(int): DrmConvertedStatus  [API 11+]
- convertData(int, byte[]): DrmConvertedStatus  [API 11+]
- finalize(): void  [API 11+]
- getAvailableDrmEngines(): String[]  [API 11+]
- getAvailableDrmSupportInfo(): Collection  [API 30+]
- getConstraints(String, int): ContentValues  [API 11+]
- getConstraints(Uri, int): ContentValues  [API 11+]
- getDrmObjectType(String, String): int  [API 11+]
- getDrmObjectType(Uri, String): int  [API 11+]
- getMetadata(String): ContentValues  [API 11+]
- getMetadata(Uri): ContentValues  [API 11+]
- getOriginalMimeType(String): String  [API 11+]
- getOriginalMimeType(Uri): String  [API 11+]
- openConvertSession(String): int  [API 11+]
- processDrmInfo(DrmInfo): int  [API 11+]
- release(): void  [API 16+, deprecated in 24]
- removeAllRights(): int  [API 11+]
- removeRights(String): int  [API 11+]
- removeRights(Uri): int  [API 11+]
- saveRights(DrmRights, String, String): int  [API 11+]
- setOnErrorListener(DrmManagerClient.OnErrorListener): void  [API 11+]
- setOnEventListener(DrmManagerClient.OnEventListener): void  [API 11+]
- setOnInfoListener(DrmManagerClient.OnInfoListener): void  [API 11+]
```

## DrmManagerClient.OnErrorListener — declaration; API 11+, deprecated in 30 (1 +, 0 *, 0 ~, 0 -)

```
+ onError(DrmManagerClient, DrmErrorEvent): void  [API 11+]
```

## DrmManagerClient.OnEventListener — declaration; API 11+, deprecated in 30 (2 +, 0 *, 0 ~, 0 -)

```
+ onEvent(DrmManagerClient, DrmEvent): void  [API 12+]
+ onEvent(DrmManagerClient, DrmEvent, HashMap): void  [API 11-11, removed in 12]
```

## DrmManagerClient.OnInfoListener — declaration; API 11+, deprecated in 30 (1 +, 0 *, 0 ~, 0 -)

```
+ onInfo(DrmManagerClient, DrmInfoEvent): void  [API 11+]
```

## DrmRights — declaration; API 11+, deprecated in 30 (0 +, 0 *, 0 ~, 9 -)

```
- DrmRights(File, String)  [API 11+]
- DrmRights(ProcessedData, String)  [API 11+]
- DrmRights(String, String)  [API 11+]
- DrmRights(String, String, String)  [API 11+]
- DrmRights(String, String, String, String)  [API 11+]
- getAccountId(): String  [API 11+]
- getData(): byte[]  [API 11+]
- getMimeType(): String  [API 11+]
- getSubscriptionId(): String  [API 11+]
```

## DrmStore — declaration; API 11+, deprecated in 30 (1 +, 0 *, 0 ~, 0 -)

```
+ DrmStore()  [API 11+, deprecated in 16]
```

## DrmStore.Action — declaration; API 11+, deprecated in 30 (1 +, 0 *, 0 ~, 0 -)

```
+ Action()  [API 11+, deprecated in 16]
```

## DrmStore.ConstraintsColumns — declaration; API 11+, deprecated in 30 (0 +, 0 *, 0 ~, 0 -)

## DrmStore.DrmObjectType — declaration; API 11+, deprecated in 30 (1 +, 0 *, 0 ~, 0 -)

```
+ DrmObjectType()  [API 11+, deprecated in 16]
```

## DrmStore.Playback — declaration; API 11+, deprecated in 30 (1 +, 0 *, 0 ~, 0 -)

```
+ Playback()  [API 11+, deprecated in 16]
```

## DrmStore.RightsStatus — declaration; API 11+, deprecated in 30 (1 +, 0 *, 0 ~, 0 -)

```
+ RightsStatus()  [API 11+, deprecated in 16]
```

## DrmSupportInfo — declaration; API 11+, deprecated in 30 (0 +, 3 *, 0 ~, 7 -)

```
* DrmSupportInfo()  [API 11+]
- addFileSuffix(String): void  [API 11+]
- addMimeType(String): void  [API 11+]
- equals(Object): boolean  [API 11+]
- getDescriprition(): String  [API 11+, deprecated in 16]
* getDescription(): String  [API 16+]
- getFileSuffixIterator(): Iterator  [API 11+]
- getMimeTypeIterator(): Iterator  [API 11+]
- hashCode(): int  [API 11+]
* setDescription(String): void  [API 11+]
```

## DrmUtils — declaration; API 11+, deprecated in 30 (0 +, 0 *, 0 ~, 2 -)

```
- DrmUtils()  [API 11+]
- getExtendedMetadataParser(byte[]): DrmUtils.ExtendedMetadataParser [static]  [API 11+]
```

## DrmUtils.ExtendedMetadataParser — declaration; API 11+, deprecated in 30 (0 +, 0 *, 0 ~, 3 -)

```
- get(String): String  [API 11+]
- iterator(): Iterator  [API 11+]
- keyIterator(): Iterator  [API 11+]
```

## ProcessedData — declaration; API 11+, deprecated in 30 (0 +, 0 *, 0 ~, 3 -)

```
- getAccountId(): String  [API 11+]
- getData(): byte[]  [API 11+]
- getSubscriptionId(): String  [API 11+]
```

