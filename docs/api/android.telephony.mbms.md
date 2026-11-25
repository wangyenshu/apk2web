# android.telephony.mbms

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## DownloadProgressListener — declaration; API 28+ (2 +, 0 *, 0 ~, 0 -)

```
+ DownloadProgressListener()  [API 28+]
+ onProgressUpdated(DownloadRequest, FileInfo, int, int, int, int): void  [API 28+]
```

## DownloadRequest — declaration; API 28+ (8 +, 3 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 28+]
+ describeContents(): int  [API 28+]
+ equals(Object): boolean  [API 28+]
* getDestinationUri(): Uri  [API 28+]
+ getFileServiceId(): String  [API 28+]
+ getMaxAppIntentSize(): int [static]  [API 28+]
+ getMaxDestinationUriSize(): int [static]  [API 28+]
* getSourceUri(): Uri  [API 28+]
* getSubscriptionId(): int  [API 28+]
+ hashCode(): int  [API 28+]
+ toByteArray(): byte[]  [API 28+]
+ writeToParcel(Parcel, int): void  [API 28+]
```

## DownloadRequest.Builder — declaration; API 28+ (4 +, 3 *, 0 ~, 0 -)

```
* Builder(Uri, Uri)  [API 28+]
* build(): DownloadRequest  [API 28+]
+ fromDownloadRequest(DownloadRequest): DownloadRequest.Builder [static]  [API 28+]
+ fromSerializedRequest(byte[]): DownloadRequest.Builder [static]  [API 28+]
+ setAppIntent(Intent): DownloadRequest.Builder  [API 28+]
+ setServiceInfo(FileServiceInfo): DownloadRequest.Builder  [API 28+]
* setSubscriptionId(int): DownloadRequest.Builder  [API 28+]
```

## DownloadStatusListener — declaration; API 28+ (2 +, 0 *, 0 ~, 0 -)

```
+ DownloadStatusListener()  [API 28+]
+ onStatusUpdated(DownloadRequest, FileInfo, int): void  [API 28+]
```

## FileInfo — declaration; API 28+ (6 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 28+]
+ describeContents(): int  [API 28+]
+ equals(Object): boolean  [API 28+]
+ getMimeType(): String  [API 28+]
+ getUri(): Uri  [API 28+]
+ hashCode(): int  [API 28+]
+ writeToParcel(Parcel, int): void  [API 28+]
```

## FileServiceInfo — declaration; API 28+ (3 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 28+]
+ describeContents(): int  [API 28+]
+ getFiles(): List  [API 28+]
+ writeToParcel(Parcel, int): void  [API 28+]
```

## GroupCall — declaration; API 29+ (3 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 29+]
+ getTmgi(): long  [API 29+]
+ updateGroupCall(List, List): void  [API 29+]
```

## GroupCallCallback — declaration; API 29+ (3 +, 0 *, 0 ~, 0 -)

```
+ onBroadcastSignalStrengthUpdated(int): void  [API 29+]
+ onError(int, String): void  [API 29+]
+ onGroupCallStateChanged(int, int): void  [API 29+]
```

## MbmsDownloadReceiver — declaration; API 28+ (1 +, 0 *, 0 ~, 1 -)

```
- MbmsDownloadReceiver()  [API 28+]
+ onReceive(Context, Intent): void  [API 28+]
```

## MbmsDownloadSessionCallback — declaration; API 28+ (4 +, 0 *, 0 ~, 0 -)

```
+ MbmsDownloadSessionCallback()  [API 28+]
+ onError(int, String): void  [API 28+]
+ onFileServicesUpdated(List): void  [API 28+]
+ onMiddlewareReady(): void  [API 28+]
```

## MbmsErrors — declaration; API 28+ (0 +, 0 *, 0 ~, 0 -)

## MbmsErrors.DownloadErrors — declaration; API 28+ (0 +, 0 *, 0 ~, 0 -)

## MbmsErrors.GeneralErrors — declaration; API 28+ (0 +, 0 *, 0 ~, 0 -)

## MbmsErrors.GroupCallErrors — declaration; API 29+ (0 +, 0 *, 0 ~, 0 -)

## MbmsErrors.InitializationErrors — declaration; API 28+ (0 +, 0 *, 0 ~, 0 -)

## MbmsErrors.StreamingErrors — declaration; API 28+ (0 +, 0 *, 0 ~, 0 -)

## MbmsGroupCallSessionCallback — declaration; API 29+ (4 +, 0 *, 0 ~, 0 -)

```
+ onAvailableSaisUpdated(List, List): void  [API 29+]
+ onError(int, String): void  [API 29+]
+ onMiddlewareReady(): void  [API 29+]
+ onServiceInterfaceAvailable(String, int): void  [API 29+]
```

## MbmsStreamingSessionCallback — declaration; API 28+ (4 +, 0 *, 0 ~, 0 -)

```
+ MbmsStreamingSessionCallback()  [API 28+]
+ onError(int, String): void  [API 28+]
+ onMiddlewareReady(): void  [API 28+]
+ onStreamingServicesUpdated(List): void  [API 28+]
```

## ServiceInfo — declaration; API 28+ (9 +, 0 *, 0 ~, 0 -)

```
+ equals(Object): boolean  [API 28+]
+ getLocales(): List  [API 28+]
+ getNameForLocale(Locale): CharSequence  [API 28+]
+ getNamedContentLocales(): Set  [API 28+]
+ getServiceClassName(): String  [API 28+]
+ getServiceId(): String  [API 28+]
+ getSessionEndTime(): Date  [API 28+]
+ getSessionStartTime(): Date  [API 28+]
+ hashCode(): int  [API 28+]
```

## StreamingService — declaration; API 28+ (3 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 28+]
+ getInfo(): StreamingServiceInfo  [API 28+]
+ getPlaybackUri(): Uri  [API 28+]
```

## StreamingServiceCallback — declaration; API 28+ (6 +, 0 *, 0 ~, 0 -)

```
+ StreamingServiceCallback()  [API 28+]
+ onBroadcastSignalStrengthUpdated(int): void  [API 28+]
+ onError(int, String): void  [API 28+]
+ onMediaDescriptionUpdated(): void  [API 28+]
+ onStreamMethodUpdated(int): void  [API 28+]
+ onStreamStateUpdated(int, int): void  [API 28+]
```

## StreamingServiceInfo — declaration; API 28+ (2 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 28+]
+ describeContents(): int  [API 28+]
+ writeToParcel(Parcel, int): void  [API 28+]
```

