# android.view.contentcapture

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ContentCaptureCondition — declaration; API 29+ (0 +, 8 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 29+]
* ContentCaptureCondition(LocusId, int)  [API 29+]
* describeContents(): int  [API 29+]
* equals(Object): boolean  [API 29+]
* getFlags(): int  [API 29+]
* getLocusId(): LocusId  [API 29+]
* hashCode(): int  [API 29+]
* toString(): String  [API 29+]
* writeToParcel(Parcel, int): void  [API 29+]
```

## ContentCaptureContext — declaration; API 29+ (0 +, 1 *, 0 ~, 5 -)

```
- CREATOR [field]  [API 29+]
- describeContents(): int  [API 29+]
- forLocusId(String): ContentCaptureContext [static]  [API 29+]
* getExtras(): Bundle  [API 29+]
- getLocusId(): LocusId  [API 29+]
- toString(): String  [API 29+]
- writeToParcel(Parcel, int): void  [API 29+]
```

## ContentCaptureContext.Builder — declaration; API 29+ (0 +, 2 *, 0 ~, 1 -)

```
- Builder(LocusId)  [API 29+]
* build(): ContentCaptureContext  [API 29+]
* setExtras(Bundle): ContentCaptureContext.Builder  [API 29+]
```

## ContentCaptureManager — declaration; API 29+ (0 +, 0 *, 0 ~, 6 -)

```
- getContentCaptureConditions(): Set  [API 29+]
- getServiceComponentName(): ComponentName  [API 29+]
- isContentCaptureEnabled(): boolean  [API 29+]
- removeData(DataRemovalRequest): void  [API 29+]
- setContentCaptureEnabled(boolean): void  [API 29+]
- shareData(DataShareRequest, Executor, DataShareWriteAdapter): void  [API 30+]
```

## ContentCaptureSession — declaration; API 29+ (0 +, 0 *, 0 ~, 18 -)

```
- close(): void  [API 29+]
- createContentCaptureSession(ContentCaptureContext): ContentCaptureSession  [API 29+]
- destroy(): void  [API 29+]
- getContentCaptureContext(): ContentCaptureContext  [API 29+]
- getContentCaptureSessionId(): ContentCaptureSessionId  [API 29+]
- newAutofillId(AutofillId, long): AutofillId  [API 29+]
- newViewStructure(View): ViewStructure  [API 29+]
- newVirtualViewStructure(AutofillId, long): ViewStructure  [API 29+]
- notifySessionPaused(): void  [API 30+]
- notifySessionResumed(): void  [API 30+]
- notifyViewAppeared(ViewStructure): void  [API 29+]
- notifyViewDisappeared(AutofillId): void  [API 29+]
- notifyViewInsetsChanged(Insets): void  [API 30+]
- notifyViewTextChanged(AutofillId, CharSequence): void  [API 29+]
- notifyViewsAppeared(List): void  [API 34+]
- notifyViewsDisappeared(AutofillId, long[]): void  [API 29+]
- setContentCaptureContext(ContentCaptureContext): void  [API 29+]
- toString(): String  [API 29+]
```

## ContentCaptureSessionId — declaration; API 29+ (0 +, 0 *, 0 ~, 5 -)

```
- CREATOR [field]  [API 29+]
- describeContents(): int  [API 29+]
- equals(Object): boolean  [API 29+]
- hashCode(): int  [API 29+]
- toString(): String  [API 29+]
- writeToParcel(Parcel, int): void  [API 29+]
```

## DataRemovalRequest — declaration; API 29+ (0 +, 0 *, 0 ~, 5 -)

```
- CREATOR [field]  [API 29+]
- describeContents(): int  [API 29+]
- getLocusIdRequests(): List  [API 29+]
- getPackageName(): String  [API 29+]
- isForEverything(): boolean  [API 29+]
- writeToParcel(Parcel, int): void  [API 29+]
```

## DataRemovalRequest.Builder — declaration; API 29+ (0 +, 0 *, 0 ~, 4 -)

```
- Builder()  [API 29+]
- addLocusId(LocusId, int): DataRemovalRequest.Builder  [API 29+]
- build(): DataRemovalRequest  [API 29+]
- forEverything(): DataRemovalRequest.Builder  [API 29+]
```

## DataRemovalRequest.LocusIdRequest — declaration; API 29+ (0 +, 0 *, 0 ~, 2 -)

```
- getFlags(): int  [API 29+]
- getLocusId(): LocusId  [API 29+]
```

## DataShareRequest — declaration; API 30+ (0 +, 3 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 30+]
* DataShareRequest(LocusId, String)  [API 30+]
- describeContents(): int  [API 30+]
- equals(Object): boolean  [API 30+]
* getLocusId(): LocusId  [API 30+]
* getMimeType(): String  [API 30+]
- getPackageName(): String  [API 30+]
- hashCode(): int  [API 30+]
- toString(): String  [API 30+]
- writeToParcel(Parcel, int): void  [API 30+]
```

## DataShareWriteAdapter — declaration; API 30+ (3 +, 0 *, 0 ~, 0 -)

```
+ onError(int): void  [API 30+]
+ onRejected(): void  [API 30+]
+ onWrite(ParcelFileDescriptor): void  [API 30+]
```

