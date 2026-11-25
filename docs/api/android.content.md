# android.content

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AbstractThreadedSyncAdapter — declaration; API 5+ (4 +, 0 *, 0 ~, 5 -)

```
- AbstractThreadedSyncAdapter(Context, boolean)  [API 5+]
- AbstractThreadedSyncAdapter(Context, boolean, boolean)  [API 11+]
- getContext(): Context  [API 5+]
- getSyncAdapterBinder(): IBinder  [API 5+]
+ onPerformSync(Account, Bundle, String, ContentProviderClient, SyncResult): void  [API 5+]
+ onSecurityException(Account, Bundle, String, SyncResult): void  [API 23+]
+ onSyncCanceled(): void  [API 8+]
+ onSyncCanceled(Thread): void  [API 11+]
- onUnsyncableAccount(): boolean  [API 28+]
```

## ActivityNotFoundException — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ ActivityNotFoundException()  [API 1+]
+ ActivityNotFoundException(String)  [API 1+]
```

## AsyncQueryHandler — framework; API 1+ (12 +, 0 *, 0 ~, 0 -)

```
+ AsyncQueryHandler(ContentResolver)  [API 1+]
+ cancelOperation(int): void  [API 1+]
+ createHandler(Looper): Handler  [API 1+]
+ handleMessage(Message): void  [API 1+]
+ onDeleteComplete(int, Object, int): void  [API 1+]
+ onInsertComplete(int, Object, Uri): void  [API 1+]
+ onQueryComplete(int, Object, Cursor): void  [API 1+]
+ onUpdateComplete(int, Object, int): void  [API 1+]
+ startDelete(int, Object, Uri, String, String[]): void  [API 1+]
+ startInsert(int, Object, Uri, ContentValues): void  [API 1+]
+ startQuery(int, Object, Uri, String[], String, String[], String): void  [API 1+]
+ startUpdate(int, Object, Uri, ContentValues, String, String[]): void  [API 1+]
```

## AsyncQueryHandler.WorkerArgs — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ WorkerArgs()  [API 1+]
+ cookie [field]  [API 1+]
+ handler [field]  [API 1+]
+ orderBy [field]  [API 1+]
+ projection [field]  [API 1+]
+ result [field]  [API 1+]
+ selection [field]  [API 1+]
+ selectionArgs [field]  [API 1+]
+ uri [field]  [API 1+]
+ values [field]  [API 1+]
```

## AsyncQueryHandler.WorkerHandler — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ WorkerHandler(AsyncQueryHandler, Looper)  [API 1+]
+ handleMessage(Message): void  [API 1+]
```

## AsyncTaskLoader — framework; API 11+, deprecated in 28 (9 +, 0 *, 1 ~, 1 -)

```
+ AsyncTaskLoader(Context)  [API 11+]
+ cancelLoad(): boolean  [API 11-15, removed in 16]
~ cancelLoadInBackground(): void  [API 16+]
- dump(String, FileDescriptor, PrintWriter, String[]): void  [API 11+]
+ isLoadInBackgroundCanceled(): boolean  [API 16+]
+ loadInBackground(): Object  [API 11+]
+ onCancelLoad(): boolean  [API 11+]
+ onCanceled(Object): void  [API 11+]
+ onForceLoad(): void  [API 11+]
+ onLoadInBackground(): Object  [API 11+]
+ setUpdateThrottle(long): void  [API 11+]
```

## AttributionSource — declaration; API 31+ (0 +, 11 *, 0 ~, 4 -)

```
+ CREATOR [field]  [API 31+]
- checkCallingUid(): boolean  [API 31+]
* describeContents(): int  [API 31+]
- enforceCallingUid(): void  [API 31+]
* equals(Object): boolean  [API 31+]
* getAttributionTag(): String  [API 31+]
* getDeviceId(): int  [API 35+]
* getNext(): AttributionSource  [API 31+]
* getPackageName(): String  [API 31+]
* getPid(): int  [API 34+]
* getUid(): int  [API 31+]
* hashCode(): int  [API 31+]
- isTrusted(Context): boolean  [API 31+]
- myAttributionSource(): AttributionSource [static]  [API 33+]
* toString(): String  [API 31+]
* writeToParcel(Parcel, int): void  [API 31+]
```

## AttributionSource.Builder — declaration; API 31+ (0 +, 8 *, 0 ~, 0 -)

```
* Builder(AttributionSource)  [API 33+]
* Builder(int)  [API 31+]
* build(): AttributionSource  [API 31+]
* setAttributionTag(String): AttributionSource.Builder  [API 31+]
* setDeviceId(int): AttributionSource.Builder  [API 35+]
* setNext(AttributionSource): AttributionSource.Builder  [API 31+]
* setPackageName(String): AttributionSource.Builder  [API 31+]
* setPid(int): AttributionSource.Builder  [API 34+]
```

## BroadcastReceiver — framework; API 1+ (20 +, 0 *, 1 ~, 0 -)

```
+ BroadcastReceiver()  [API 1+]
+ abortBroadcast(): void  [API 1+]
+ clearAbortBroadcast(): void  [API 1+]
+ getAbortBroadcast(): boolean  [API 1+]
+ getDebugUnregister(): boolean  [API 1+]
+ getResultCode(): int  [API 1+]
+ getResultData(): String  [API 1+]
+ getResultExtras(boolean): Bundle  [API 1+]
+ getSentFromPackage(): String  [API 34+]
+ getSentFromUid(): int  [API 34+]
+ goAsync(): BroadcastReceiver.PendingResult  [API 11+]
+ isInitialStickyBroadcast(): boolean  [API 5+]
+ isOrderedBroadcast(): boolean  [API 5+]
+ onReceive(Context, Intent): void  [API 1+]
+ peekService(Context, Intent): IBinder  [API 3+]
+ setDebugUnregister(boolean): void  [API 1+]
~ setOrderedHint(boolean): void  [API 1+]
+ setResult(int, String, Bundle): void  [API 1+]
+ setResultCode(int): void  [API 1+]
+ setResultData(String): void  [API 1+]
+ setResultExtras(Bundle): void  [API 1+]
```

## BroadcastReceiver.PendingResult — framework; API 11+ (11 +, 0 *, 0 ~, 0 -)

```
+ abortBroadcast(): void  [API 11+]
+ clearAbortBroadcast(): void  [API 11+]
+ finish(): void  [API 11+]
+ getAbortBroadcast(): boolean  [API 11+]
+ getResultCode(): int  [API 11+]
+ getResultData(): String  [API 11+]
+ getResultExtras(boolean): Bundle  [API 11+]
+ setResult(int, String, Bundle): void  [API 11+]
+ setResultCode(int): void  [API 11+]
+ setResultData(String): void  [API 11+]
+ setResultExtras(Bundle): void  [API 11+]
```

## ClipData — framework; API 11+ (15 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 11+]
+ ClipData(CharSequence, String[], ClipData.Item)  [API 11+]
+ ClipData(ClipData)  [API 16+]
+ ClipData(ClipDescription, ClipData.Item)  [API 11+]
+ addItem(ClipData.Item): void  [API 11+]
+ addItem(ContentResolver, ClipData.Item): void  [API 26+]
~ describeContents(): int  [API 11+]
+ getDescription(): ClipDescription  [API 11+]
+ getItemAt(int): ClipData.Item  [API 11+]
+ getItemCount(): int  [API 11+]
+ newHtmlText(CharSequence, CharSequence, String): ClipData [static]  [API 16+]
+ newIntent(CharSequence, Intent): ClipData [static]  [API 11+]
+ newPlainText(CharSequence, CharSequence): ClipData [static]  [API 11+]
+ newRawUri(CharSequence, Uri): ClipData [static]  [API 11+]
+ newUri(ContentResolver, CharSequence, Uri): ClipData [static]  [API 11+]
+ toString(): String  [API 11+]
+ writeToParcel(Parcel, int): void  [API 11+]
```

## ClipData.Item — framework; API 11+ (16 +, 0 *, 0 ~, 0 -)

```
+ Item(CharSequence)  [API 11+]
+ Item(CharSequence, Intent, Uri)  [API 11+]
+ Item(CharSequence, String)  [API 16+]
+ Item(CharSequence, String, Intent, Uri)  [API 16+]
+ Item(Intent)  [API 11+]
+ Item(Uri)  [API 11+]
+ coerceToHtmlText(Context): String  [API 16+]
+ coerceToStyledText(Context): CharSequence  [API 16+]
+ coerceToText(Context): CharSequence  [API 11+]
+ getHtmlText(): String  [API 16+]
+ getIntent(): Intent  [API 11+]
+ getIntentSender(): IntentSender  [API 35+]
+ getText(): CharSequence  [API 11+]
+ getTextLinks(): TextLinks  [API 31+]
+ getUri(): Uri  [API 11+]
+ toString(): String  [API 11+]
```

## ClipData.Item.Builder — framework; API 35+ (7 +, 0 *, 0 ~, 0 -)

```
+ Builder()  [API 35+]
+ build(): ClipData.Item  [API 35+]
+ setHtmlText(String): ClipData.Item.Builder  [API 35+]
+ setIntent(Intent): ClipData.Item.Builder  [API 35+]
+ setIntentSender(IntentSender): ClipData.Item.Builder  [API 35+]
+ setText(CharSequence): ClipData.Item.Builder  [API 35+]
+ setUri(Uri): ClipData.Item.Builder  [API 35+]
```

## ClipDescription — framework; API 11+ (16 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 11+]
+ ClipDescription(CharSequence, String[])  [API 11+]
+ ClipDescription(ClipDescription)  [API 11+]
+ compareMimeTypes(String, String): boolean [static]  [API 11+]
~ describeContents(): int  [API 11+]
+ filterMimeTypes(String): String[]  [API 11+]
+ getClassificationStatus(): int  [API 31+]
+ getConfidenceScore(String): float  [API 31+]
+ getExtras(): PersistableBundle  [API 24+]
+ getLabel(): CharSequence  [API 11+]
+ getMimeType(int): String  [API 11+]
+ getMimeTypeCount(): int  [API 11+]
+ getTimestamp(): long  [API 26+]
+ hasMimeType(String): boolean  [API 11+]
+ isStyledText(): boolean  [API 31+]
+ setExtras(PersistableBundle): void  [API 24+]
+ toString(): String  [API 11+]
+ writeToParcel(Parcel, int): void  [API 11+]
```

## ClipboardManager — framework; API 11+ (10 +, 0 *, 0 ~, 0 -)

```
+ addPrimaryClipChangedListener(ClipboardManager.OnPrimaryClipChangedListener): void  [API 11+]
+ clearPrimaryClip(): void  [API 28+]
+ getPrimaryClip(): ClipData  [API 11+]
+ getPrimaryClipDescription(): ClipDescription  [API 11+]
+ getText(): CharSequence  [API 11+]
+ hasPrimaryClip(): boolean  [API 11+]
+ hasText(): boolean  [API 11+]
+ removePrimaryClipChangedListener(ClipboardManager.OnPrimaryClipChangedListener): void  [API 11+]
+ setPrimaryClip(ClipData): void  [API 11+]
+ setText(CharSequence): void  [API 11+]
```

## ClipboardManager.OnPrimaryClipChangedListener — framework; API 11+ (1 +, 0 *, 0 ~, 0 -)

```
+ onPrimaryClipChanged(): void  [API 11+]
```

## ComponentCallbacks — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ onConfigurationChanged(Configuration): void  [API 1+]
+ onLowMemory(): void  [API 1+, deprecated in 35]
```

## ComponentCallbacks2 — framework; API 14+ (1 +, 0 *, 0 ~, 0 -)

```
+ onTrimMemory(int): void  [API 14+]
```

## ComponentName — framework; API 1+ (21 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 1+]
+ ComponentName(Context, Class)  [API 1+]
+ ComponentName(Context, String)  [API 1+]
+ ComponentName(Parcel)  [API 1+]
+ ComponentName(String, String)  [API 1+]
+ clone(): ComponentName  [API 8+]
+ compareTo(ComponentName): int  [API 4+]
+ createRelative(Context, String): ComponentName [static]  [API 23+]
+ createRelative(String, String): ComponentName [static]  [API 23+]
~ describeContents(): int  [API 1+]
+ equals(Object): boolean  [API 1+]
+ flattenToShortString(): String  [API 1+]
+ flattenToString(): String  [API 1+]
+ getClassName(): String  [API 1+]
+ getPackageName(): String  [API 1+]
+ getShortClassName(): String  [API 1+]
+ hashCode(): int  [API 1+]
+ readFromParcel(Parcel): ComponentName [static]  [API 1+]
+ toShortString(): String  [API 1+]
+ toString(): String  [API 1+]
+ unflattenFromString(String): ComponentName [static]  [API 1+]
+ writeToParcel(ComponentName, Parcel): void [static]  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## ContentProvider — framework; API 1+ (34 +, 0 *, 8 ~, 10 -)

```
+ ContentProvider()  [API 1+]
+ applyBatch(ArrayList): ContentProviderResult[]  [API 5+]
+ applyBatch(String, ArrayList): ContentProviderResult[]  [API 29+]
+ attachInfo(Context, ProviderInfo): void  [API 1+]
+ bulkInsert(Uri, ContentValues[]): int  [API 1+]
~ call(String, String, Bundle): Bundle  [API 11+]
+ call(String, String, String, Bundle): Bundle  [API 29+]
~ canonicalize(Uri): Uri  [API 19+]
- clearCallingIdentity(): ContentProvider.CallingIdentity  [API 29+]
+ delete(Uri, Bundle): int  [API 30+]
+ delete(Uri, String, String[]): int  [API 1+]
- dump(FileDescriptor, PrintWriter, String[]): void  [API 18+]
- getCallingAttributionSource(): AttributionSource  [API 31+]
~ getCallingAttributionTag(): String  [API 30+]
+ getCallingPackage(): String  [API 19+]
- getCallingPackageUnchecked(): String  [API 30+]
+ getContext(): Context  [API 1+]
- getPathPermissions(): PathPermission[]  [API 4+]
+ getReadPermission(): String  [API 1+]
~ getStreamTypes(Uri, String): String[]  [API 11+]
+ getType(Uri): String  [API 1+]
- getTypeAnonymous(Uri): String  [API 34+]
+ getWritePermission(): String  [API 1+]
+ insert(Uri, ContentValues): Uri  [API 1+]
+ insert(Uri, ContentValues, Bundle): Uri  [API 30+]
~ isTemporary(): boolean  [API 1+]
- onCallingPackageChanged(): void  [API 30+]
+ onConfigurationChanged(Configuration): void  [API 1+]
+ onCreate(): boolean  [API 1+]
+ onLowMemory(): void  [API 1+]
+ onTrimMemory(int): void  [API 1+]
+ openAssetFile(Uri, String): AssetFileDescriptor  [API 3+]
+ openAssetFile(Uri, String, CancellationSignal): AssetFileDescriptor  [API 19+]
+ openFile(Uri, String): ParcelFileDescriptor  [API 1+]
+ openFile(Uri, String, CancellationSignal): ParcelFileDescriptor  [API 19+]
+ openFileHelper(Uri, String): ParcelFileDescriptor  [API 1+]
- openPipeHelper(Uri, String, Bundle, Object, ContentProvider.PipeDataWriter): ParcelFileDescriptor  [API 11+]
+ openTypedAssetFile(Uri, String, Bundle): AssetFileDescriptor  [API 11+]
- openTypedAssetFile(Uri, String, Bundle, CancellationSignal): AssetFileDescriptor  [API 19+]
+ query(Uri, String[], Bundle, CancellationSignal): Cursor  [API 26+]
+ query(Uri, String[], String, String[], String): Cursor  [API 1+]
+ query(Uri, String[], String, String[], String, CancellationSignal): Cursor  [API 16+]
~ refresh(Uri, Bundle, CancellationSignal): boolean  [API 26+]
+ requireContext(): Context  [API 30+]
- restoreCallingIdentity(ContentProvider.CallingIdentity): void  [API 29+]
~ setPathPermissions(PathPermission[]): void  [API 4+]
+ setReadPermission(String): void  [API 1+]
+ setWritePermission(String): void  [API 1+]
~ shutdown(): void  [API 11+]
+ uncanonicalize(Uri): Uri  [API 19+]
+ update(Uri, ContentValues, Bundle): int  [API 30+]
+ update(Uri, ContentValues, String, String[]): int  [API 1+]
```

## ContentProvider.CallingIdentity — declaration; API 29+ (0 +, 0 *, 0 ~, 0 -)

## ContentProvider.PipeDataWriter — declaration; API 11+ (1 +, 0 *, 0 ~, 0 -)

```
+ writeDataToPipe(ParcelFileDescriptor, Uri, String, Bundle, Object): void  [API 11+]
```

## ContentProviderClient — framework; API 5+ (13 +, 0 *, 2 ~, 15 -)

```
+ applyBatch(ArrayList): ContentProviderResult[]  [API 5+]
- applyBatch(String, ArrayList): ContentProviderResult[]  [API 29+]
+ bulkInsert(Uri, ContentValues[]): int  [API 5+]
+ call(String, String, Bundle): Bundle  [API 17+]
- call(String, String, String, Bundle): Bundle  [API 29+]
- canonicalize(Uri): Uri  [API 19+]
~ close(): void  [API 5+]
- delete(Uri, Bundle): int  [API 30+]
+ delete(Uri, String, String[]): int  [API 5+]
+ finalize(): void  [API 5+]
+ getLocalContentProvider(): ContentProvider  [API 5+]
- getStreamTypes(Uri, String): String[]  [API 11+]
+ getType(Uri): String  [API 5+]
+ insert(Uri, ContentValues): Uri  [API 5+]
- insert(Uri, ContentValues, Bundle): Uri  [API 30+]
+ openAssetFile(Uri, String): AssetFileDescriptor  [API 5+]
- openAssetFile(Uri, String, CancellationSignal): AssetFileDescriptor  [API 19+]
+ openFile(Uri, String): ParcelFileDescriptor  [API 5+]
- openFile(Uri, String, CancellationSignal): ParcelFileDescriptor  [API 19+]
- openTypedAssetFile(Uri, String, Bundle, CancellationSignal): AssetFileDescriptor  [API 29+]
- openTypedAssetFileDescriptor(Uri, String, Bundle): AssetFileDescriptor  [API 11+]
- openTypedAssetFileDescriptor(Uri, String, Bundle, CancellationSignal): AssetFileDescriptor  [API 19+]
- query(Uri, String[], Bundle, CancellationSignal): Cursor  [API 26+]
+ query(Uri, String[], String, String[], String): Cursor  [API 5+]
+ query(Uri, String[], String, String[], String, CancellationSignal): Cursor  [API 16+]
- refresh(Uri, Bundle, CancellationSignal): boolean  [API 26+]
~ release(): boolean  [API 5+, deprecated in 24]
- uncanonicalize(Uri): Uri  [API 19+]
- update(Uri, ContentValues, Bundle): int  [API 30+]
+ update(Uri, ContentValues, String, String[]): int  [API 5+]
```

## ContentProviderOperation — framework; API 5+ (16 +, 0 *, 2 ~, 4 -)

```
- CREATOR [field]  [API 5+]
+ apply(ContentProvider, ContentProviderResult[], int): ContentProviderResult  [API 5+]
~ describeContents(): int  [API 5+]
+ getUri(): Uri  [API 5+]
+ isAssertQuery(): boolean  [API 23+]
- isCall(): boolean  [API 30+]
+ isDelete(): boolean  [API 23+]
- isExceptionAllowed(): boolean  [API 30+]
+ isInsert(): boolean  [API 23+]
+ isReadOperation(): boolean  [API 5+]
+ isUpdate(): boolean  [API 23+]
+ isWriteOperation(): boolean  [API 5+]
+ isYieldAllowed(): boolean  [API 5+]
+ newAssertQuery(Uri): ContentProviderOperation.Builder [static]  [API 5+]
- newCall(Uri, String, String): ContentProviderOperation.Builder [static]  [API 30+]
+ newDelete(Uri): ContentProviderOperation.Builder [static]  [API 5+]
+ newInsert(Uri): ContentProviderOperation.Builder [static]  [API 5+]
+ newUpdate(Uri): ContentProviderOperation.Builder [static]  [API 5+]
- resolveExtrasBackReferences(ContentProviderResult[], int): Bundle  [API 30+]
+ resolveSelectionArgsBackReferences(ContentProviderResult[], int): String[]  [API 5+]
+ resolveValueBackReferences(ContentProviderResult[], int): ContentValues  [API 5+]
+ toString(): String  [API 5+]
~ writeToParcel(Parcel, int): void  [API 5+]
```

## ContentProviderOperation.Builder — framework; API 5+ (9 +, 0 *, 0 ~, 7 -)

```
+ build(): ContentProviderOperation  [API 5+]
- withExceptionAllowed(boolean): ContentProviderOperation.Builder  [API 30+]
+ withExpectedCount(int): ContentProviderOperation.Builder  [API 5+]
- withExtra(String, Object): ContentProviderOperation.Builder  [API 30+]
- withExtraBackReference(String, int): ContentProviderOperation.Builder  [API 30+]
- withExtraBackReference(String, int, String): ContentProviderOperation.Builder  [API 30+]
- withExtras(Bundle): ContentProviderOperation.Builder  [API 30+]
+ withSelection(String, String[]): ContentProviderOperation.Builder  [API 5+]
+ withSelectionBackReference(int, int): ContentProviderOperation.Builder  [API 5+]
- withSelectionBackReference(int, int, String): ContentProviderOperation.Builder  [API 30+]
+ withValue(String, Object): ContentProviderOperation.Builder  [API 5+]
+ withValueBackReference(String, int): ContentProviderOperation.Builder  [API 5+]
- withValueBackReference(String, int, String): ContentProviderOperation.Builder  [API 30+]
+ withValueBackReferences(ContentValues): ContentProviderOperation.Builder  [API 5+]
+ withValues(ContentValues): ContentProviderOperation.Builder  [API 5+]
+ withYieldAllowed(boolean): ContentProviderOperation.Builder  [API 5+]
```

## ContentProviderResult — framework; API 5+ (5 +, 0 *, 2 ~, 1 -)

```
- CREATOR [field]  [API 5+]
+ ContentProviderResult(Bundle)  [API 30+]
- ContentProviderResult(Parcel)  [API 5+]
+ ContentProviderResult(Throwable)  [API 30+]
+ ContentProviderResult(Uri)  [API 5+]
+ ContentProviderResult(int)  [API 5+]
+ count [field]  [API 5+]
~ describeContents(): int  [API 5+]
+ exception [field]  [API 30+]
+ extras [field]  [API 30+]
+ toString(): String  [API 5+]
+ uri [field]  [API 5+]
~ writeToParcel(Parcel, int): void  [API 5+]
```

## ContentQueryMap — framework; API 1+ (7 +, 0 *, 0 ~, 0 -)

```
+ ContentQueryMap(Cursor, String, boolean, Handler)  [API 1+]
+ close(): void  [API 1+]
+ finalize(): void  [API 1+]
+ getRows(): Map  [API 1+]
+ getValues(String): ContentValues  [API 1+]
+ requery(): void  [API 1+]
+ setKeepUpdated(boolean): void  [API 1+]
```

## ContentResolver — framework; API 1+ (39 +, 0 *, 17 ~, 16 -)

```
+ ContentResolver(Context)  [API 1+]
+ acquireContentProviderClient(String): ContentProviderClient  [API 5+]
+ acquireContentProviderClient(Uri): ContentProviderClient  [API 5+]
+ acquireUnstableContentProviderClient(String): ContentProviderClient  [API 16+]
+ acquireUnstableContentProviderClient(Uri): ContentProviderClient  [API 16+]
~ addPeriodicSync(Account, String, Bundle, long): void [static]  [API 8+]
+ addStatusChangeListener(int, SyncStatusObserver): Object [static]  [API 5+]
+ applyBatch(String, ArrayList): ContentProviderResult[]  [API 5+]
+ bulkInsert(Uri, ContentValues[]): int  [API 1+]
+ call(String, String, String, Bundle): Bundle  [API 29+]
+ call(Uri, String, String, Bundle): Bundle  [API 11+]
~ cancelSync(Account, String): void [static]  [API 5+]
- cancelSync(SyncRequest): void [static]  [API 21+]
~ cancelSync(Uri): void  [API 1+, deprecated in 15]
+ canonicalize(Uri): Uri  [API 19+]
+ delete(Uri, Bundle): int  [API 30+]
+ delete(Uri, String, String[]): int  [API 1+]
- getCurrentSync(): SyncInfo [static]  [API 8+, deprecated in 15]
- getCurrentSyncs(): List [static]  [API 11+]
~ getIsSyncable(Account, String): int [static]  [API 5+]
~ getMasterSyncAutomatically(): boolean [static]  [API 5+]
- getOutgoingPersistedUriPermissions(): List  [API 19+]
- getPeriodicSyncs(Account, String): List [static]  [API 8+]
+ getPersistedUriPermissions(): List  [API 19+]
+ getStreamTypes(Uri, String): String[]  [API 11+]
- getSyncAdapterTypes(): SyncAdapterType[] [static]  [API 5+]
~ getSyncAutomatically(Account, String): boolean [static]  [API 5+]
+ getType(Uri): String  [API 1+]
- getTypeInfo(String): ContentResolver.MimeTypeInfo  [API 29+]
+ insert(Uri, ContentValues): Uri  [API 1+]
+ insert(Uri, ContentValues, Bundle): Uri  [API 30+]
~ isSyncActive(Account, String): boolean [static]  [API 5+]
~ isSyncPending(Account, String): boolean [static]  [API 5+]
- loadThumbnail(Uri, Size, CancellationSignal): Bitmap  [API 29+]
+ notifyChange(Collection, ContentObserver, int): void  [API 30+]
+ notifyChange(Uri, ContentObserver): void  [API 1+]
+ notifyChange(Uri, ContentObserver, boolean): void  [API 1+, deprecated in 30]
+ notifyChange(Uri, ContentObserver, int): void  [API 24+]
- openAssetFile(Uri, String, CancellationSignal): AssetFileDescriptor  [API 29+]
+ openAssetFileDescriptor(Uri, String): AssetFileDescriptor  [API 3+]
+ openAssetFileDescriptor(Uri, String, CancellationSignal): AssetFileDescriptor  [API 19+]
- openFile(Uri, String, CancellationSignal): ParcelFileDescriptor  [API 29+]
+ openFileDescriptor(Uri, String): ParcelFileDescriptor  [API 1+]
+ openFileDescriptor(Uri, String, CancellationSignal): ParcelFileDescriptor  [API 19+]
+ openInputStream(Uri): InputStream  [API 1+]
+ openOutputStream(Uri): OutputStream  [API 1+]
+ openOutputStream(Uri, String): OutputStream  [API 3+]
- openTypedAssetFile(Uri, String, Bundle, CancellationSignal): AssetFileDescriptor  [API 29+]
+ openTypedAssetFileDescriptor(Uri, String, Bundle): AssetFileDescriptor  [API 11+]
- openTypedAssetFileDescriptor(Uri, String, Bundle, CancellationSignal): AssetFileDescriptor  [API 19+]
+ query(Uri, String[], Bundle, CancellationSignal): Cursor  [API 26+]
+ query(Uri, String[], String, String[], String): Cursor  [API 1+]
+ query(Uri, String[], String, String[], String, CancellationSignal): Cursor  [API 16+]
+ refresh(Uri, Bundle, CancellationSignal): boolean  [API 26+]
+ registerContentObserver(Uri, boolean, ContentObserver): void  [API 1+]
~ releasePersistableUriPermission(Uri, int): void  [API 19+]
~ removePeriodicSync(Account, String, Bundle): void [static]  [API 8+]
~ removeStatusChangeListener(Object): void [static]  [API 5+]
~ requestSync(Account, String, Bundle): void [static]  [API 5+]
- requestSync(SyncRequest): void [static]  [API 19+]
~ setIsSyncable(Account, String, int): void [static]  [API 5+]
~ setMasterSyncAutomatically(boolean): void [static]  [API 5+]
~ setSyncAutomatically(Account, String, boolean): void [static]  [API 5+]
~ startSync(Uri, Bundle): void  [API 1+, deprecated in 15]
~ takePersistableUriPermission(Uri, int): void  [API 19+]
+ uncanonicalize(Uri): Uri  [API 19+]
+ unregisterContentObserver(ContentObserver): void  [API 1+]
+ update(Uri, ContentValues, Bundle): int  [API 30+]
+ update(Uri, ContentValues, String, String[]): int  [API 1+]
- validateSyncExtrasBundle(Bundle): void [static]  [API 1+]
- wrap(ContentProvider): ContentResolver [static]  [API 29+]
- wrap(ContentProviderClient): ContentResolver [static]  [API 29+]
```

## ContentResolver.MimeTypeInfo — declaration; API 29+ (0 +, 0 *, 0 ~, 3 -)

```
- getContentDescription(): CharSequence  [API 29+]
- getIcon(): Icon  [API 29+]
- getLabel(): CharSequence  [API 29+]
```

## ContentUris — framework; API 1+ (5 +, 0 *, 0 ~, 0 -)

```
+ ContentUris()  [API 1+]
+ appendId(Uri.Builder, long): Uri.Builder [static]  [API 1+]
+ parseId(Uri): long [static]  [API 1+]
+ removeId(Uri): Uri [static]  [API 29+]
+ withAppendedId(Uri, long): Uri [static]  [API 1+]
```

## ContentValues — framework; API 1+ (34 +, 0 *, 2 ~, 0 -)

```
- CREATOR [field]  [API 1+]
+ ContentValues()  [API 1+]
+ ContentValues(ContentValues)  [API 1+]
+ ContentValues(int)  [API 1+]
+ clear(): void  [API 1+]
+ containsKey(String): boolean  [API 1+]
~ describeContents(): int  [API 1+]
+ equals(Object): boolean  [API 1+]
+ get(String): Object  [API 1+]
+ getAsBoolean(String): Boolean  [API 1+]
+ getAsByte(String): Byte  [API 1+]
+ getAsByteArray(String): byte[]  [API 1+]
+ getAsDouble(String): Double  [API 1+]
+ getAsFloat(String): Float  [API 1+]
+ getAsInteger(String): Integer  [API 1+]
+ getAsLong(String): Long  [API 1+]
+ getAsShort(String): Short  [API 1+]
+ getAsString(String): String  [API 1+]
+ hashCode(): int  [API 1+]
+ isEmpty(): boolean  [API 30+]
+ keySet(): Set  [API 11+]
+ put(String, Boolean): void  [API 1+]
+ put(String, Byte): void  [API 1+]
+ put(String, Double): void  [API 1+]
+ put(String, Float): void  [API 1+]
+ put(String, Integer): void  [API 1+]
+ put(String, Long): void  [API 1+]
+ put(String, Short): void  [API 1+]
+ put(String, String): void  [API 1+]
+ put(String, byte[]): void  [API 1+]
+ putAll(ContentValues): void  [API 1+]
+ putNull(String): void  [API 1+]
+ remove(String): void  [API 1+]
+ size(): int  [API 1+]
+ toString(): String  [API 1+]
+ valueSet(): Set  [API 1+]
~ writeToParcel(Parcel, int): void  [API 1+]
```

## Context — framework; API 1+ (131 +, 0 *, 22 ~, 0 -)

```
+ Context()  [API 1+]
+ bindIsolatedService(Intent, Context.BindServiceFlags, String, Executor, ServiceConnection): boolean  [API 34+]
+ bindIsolatedService(Intent, int, String, Executor, ServiceConnection): boolean  [API 29+]
+ bindService(Intent, Context.BindServiceFlags, Executor, ServiceConnection): boolean  [API 34+]
+ bindService(Intent, ServiceConnection, Context.BindServiceFlags): boolean  [API 34+]
+ bindService(Intent, ServiceConnection, int): boolean  [API 1+]
+ bindService(Intent, int, Executor, ServiceConnection): boolean  [API 29+]
+ bindServiceAsUser(Intent, ServiceConnection, Context.BindServiceFlags, UserHandle): boolean  [API 34+]
+ bindServiceAsUser(Intent, ServiceConnection, int, UserHandle): boolean  [API 30+]
+ checkCallingOrSelfPermission(String): int  [API 1+]
+ checkCallingOrSelfUriPermission(Uri, int): int  [API 1+]
+ checkCallingOrSelfUriPermissions(List, int): int[]  [API 31+]
+ checkCallingPermission(String): int  [API 1+]
+ checkCallingUriPermission(Uri, int): int  [API 1+]
+ checkCallingUriPermissions(List, int): int[]  [API 31+]
+ checkContentUriPermissionFull(Uri, int, int, int): int  [API 35+]
+ checkPermission(String, int, int): int  [API 1+]
+ checkSelfPermission(String): int  [API 23+]
+ checkUriPermission(Uri, String, String, int, int, int): int  [API 1+]
~ checkUriPermission(Uri, int, int, int): int  [API 1+]
+ checkUriPermissions(List, int, int, int): int[]  [API 31+]
+ clearWallpaper(): void  [API 1+, deprecated in 15]
~ createAttributionContext(String): Context  [API 30+]
~ createConfigurationContext(Configuration): Context  [API 17+]
~ createContext(ContextParams): Context  [API 31+]
~ createContextForSplit(String): Context  [API 26+]
~ createDeviceContext(int): Context  [API 34+]
~ createDeviceProtectedStorageContext(): Context  [API 24+]
~ createDisplayContext(Display): Context  [API 17+]
+ createPackageContext(String, int): Context  [API 1+]
~ createWindowContext(Display, int, Bundle): Context  [API 31+]
~ createWindowContext(int, Bundle): Context  [API 30+]
+ databaseList(): String[]  [API 1+]
+ deleteDatabase(String): boolean  [API 1+]
+ deleteFile(String): boolean  [API 1+]
+ deleteSharedPreferences(String): boolean  [API 24+]
+ enforceCallingOrSelfPermission(String, String): void  [API 1+]
+ enforceCallingOrSelfUriPermission(Uri, int, String): void  [API 1+]
+ enforceCallingPermission(String, String): void  [API 1+]
+ enforceCallingUriPermission(Uri, int, String): void  [API 1+]
+ enforcePermission(String, int, int, String): void  [API 1+]
+ enforceUriPermission(Uri, String, String, int, int, int, String): void  [API 1+]
+ enforceUriPermission(Uri, int, int, int, String): void  [API 1+]
+ fileList(): String[]  [API 1+]
+ getApplicationContext(): Context  [API 1+]
+ getApplicationInfo(): ApplicationInfo  [API 4+]
+ getAssets(): AssetManager  [API 1+]
~ getAttributionSource(): AttributionSource  [API 31+]
~ getAttributionTag(): String  [API 30+]
+ getCacheDir(): File  [API 1+]
+ getClassLoader(): ClassLoader  [API 1+]
+ getCodeCacheDir(): File  [API 21+]
+ getColor(int): int  [API 23+]
+ getColorStateList(int): ColorStateList  [API 23+]
+ getContentResolver(): ContentResolver  [API 1+]
+ getDataDir(): File  [API 24+]
+ getDatabasePath(String): File  [API 1+]
~ getDeviceId(): int  [API 34+]
+ getDir(String, int): File  [API 1+]
+ getDisplay(): Display  [API 30+]
+ getDrawable(int): Drawable  [API 21+]
+ getExternalCacheDir(): File  [API 8+]
+ getExternalCacheDirs(): File[]  [API 19+]
+ getExternalFilesDir(String): File  [API 8+]
+ getExternalFilesDirs(String): File[]  [API 19+]
+ getExternalMediaDirs(): File[]  [API 21+, deprecated in 30]
+ getFileStreamPath(String): File  [API 1+]
+ getFilesDir(): File  [API 1+]
+ getMainExecutor(): Executor  [API 28+]
+ getMainLooper(): Looper  [API 1+]
+ getNoBackupFilesDir(): File  [API 21+]
~ getObbDir(): File  [API 11+]
+ getObbDirs(): File[]  [API 19+]
+ getOpPackageName(): String  [API 29+]
+ getPackageCodePath(): String  [API 8+]
+ getPackageManager(): PackageManager  [API 1+]
+ getPackageName(): String  [API 1+]
+ getPackageResourcePath(): String  [API 8+]
~ getParams(): ContextParams  [API 31+]
+ getResources(): Resources  [API 1+]
+ getSharedPreferences(String, int): SharedPreferences  [API 1+]
+ getString(int): String  [API 1+]
+ getString(int, Object[]): String  [API 1+]
+ getSystemService(Class): Object  [API 23+]
+ getSystemService(String): Object  [API 1+]
+ getSystemServiceName(Class): String  [API 23+]
+ getText(int): CharSequence  [API 1+]
+ getTheme(): Resources.Theme  [API 1+]
+ getWallpaper(): Drawable  [API 1+, deprecated in 15]
+ getWallpaperDesiredMinimumHeight(): int  [API 1+, deprecated in 15]
+ getWallpaperDesiredMinimumWidth(): int  [API 1+, deprecated in 15]
+ grantUriPermission(String, Uri, int): void  [API 1+]
~ isDeviceProtectedStorage(): boolean  [API 24+]
~ isRestricted(): boolean  [API 4+]
+ isUiContext(): boolean  [API 31+]
~ moveDatabaseFrom(Context, String): boolean  [API 24+]
~ moveSharedPreferencesFrom(Context, String): boolean  [API 24+]
+ obtainStyledAttributes(AttributeSet, int[]): TypedArray  [API 1+]
+ obtainStyledAttributes(AttributeSet, int[], int, int): TypedArray  [API 1+]
+ obtainStyledAttributes(int, int[]): TypedArray  [API 1+]
+ obtainStyledAttributes(int[]): TypedArray  [API 1+]
+ openFileInput(String): FileInputStream  [API 1+]
+ openFileOutput(String, int): FileOutputStream  [API 1+]
+ openOrCreateDatabase(String, int, SQLiteDatabase.CursorFactory): SQLiteDatabase  [API 1+]
+ openOrCreateDatabase(String, int, SQLiteDatabase.CursorFactory, DatabaseErrorHandler): SQLiteDatabase  [API 11+]
+ peekWallpaper(): Drawable  [API 1+, deprecated in 15]
~ registerComponentCallbacks(ComponentCallbacks): void  [API 14+]
+ registerDeviceIdChangeListener(Executor, IntConsumer): void  [API 34+]
+ registerReceiver(BroadcastReceiver, IntentFilter): Intent  [API 1+]
+ registerReceiver(BroadcastReceiver, IntentFilter, String, Handler): Intent  [API 1+]
+ registerReceiver(BroadcastReceiver, IntentFilter, String, Handler, int): Intent  [API 26+]
+ registerReceiver(BroadcastReceiver, IntentFilter, int): Intent  [API 26+]
+ removeStickyBroadcast(Intent): void  [API 1+, deprecated in 21]
+ removeStickyBroadcastAsUser(Intent, UserHandle): void  [API 17+, deprecated in 21]
+ revokeSelfPermissionOnKill(String): void  [API 33+]
+ revokeSelfPermissionsOnKill(Collection): void  [API 33+]
+ revokeUriPermission(String, Uri, int): void  [API 26+]
+ revokeUriPermission(Uri, int): void  [API 1+]
+ sendBroadcast(Intent): void  [API 1+]
+ sendBroadcast(Intent, String): void  [API 1+]
+ sendBroadcast(Intent, String, Bundle): void  [API 34+]
+ sendBroadcastAsUser(Intent, UserHandle): void  [API 17+]
+ sendBroadcastAsUser(Intent, UserHandle, String): void  [API 17+]
+ sendBroadcastWithMultiplePermissions(Intent, String[]): void  [API 30+]
+ sendOrderedBroadcast(Intent, String): void  [API 1+]
+ sendOrderedBroadcast(Intent, String, BroadcastReceiver, Handler, int, String, Bundle): void  [API 1+]
+ sendOrderedBroadcast(Intent, String, Bundle): void  [API 34+]
+ sendOrderedBroadcast(Intent, String, Bundle, BroadcastReceiver, Handler, int, String, Bundle): void  [API 34+]
+ sendOrderedBroadcast(Intent, String, String, BroadcastReceiver, Handler, int, String, Bundle): void  [API 30+]
+ sendOrderedBroadcastAsUser(Intent, UserHandle, String, BroadcastReceiver, Handler, int, String, Bundle): void  [API 17+]
+ sendStickyBroadcast(Intent): void  [API 1+, deprecated in 21]
+ sendStickyBroadcast(Intent, Bundle): void  [API 31+, deprecated in 31]
+ sendStickyBroadcastAsUser(Intent, UserHandle): void  [API 17+, deprecated in 21]
+ sendStickyOrderedBroadcast(Intent, BroadcastReceiver, Handler, int, String, Bundle): void  [API 5+, deprecated in 21]
+ sendStickyOrderedBroadcastAsUser(Intent, UserHandle, BroadcastReceiver, Handler, int, String, Bundle): void  [API 17+, deprecated in 21]
+ setTheme(int): void  [API 1+]
+ setWallpaper(Bitmap): void  [API 1+, deprecated in 15]
+ setWallpaper(InputStream): void  [API 1+, deprecated in 15]
+ startActivities(Intent[]): void  [API 11+]
+ startActivities(Intent[], Bundle): void  [API 16+]
+ startActivity(Intent): void  [API 1+]
+ startActivity(Intent, Bundle): void  [API 16+]
+ startForegroundService(Intent): ComponentName  [API 26+]
~ startInstrumentation(ComponentName, String, Bundle): boolean  [API 1+]
+ startIntentSender(IntentSender, Intent, int, int, int): void  [API 5+]
+ startIntentSender(IntentSender, Intent, int, int, int, Bundle): void  [API 16+]
+ startService(Intent): ComponentName  [API 1+]
+ stopService(Intent): boolean  [API 1+]
+ unbindService(ServiceConnection): void  [API 1+]
~ unregisterComponentCallbacks(ComponentCallbacks): void  [API 14+]
+ unregisterDeviceIdChangeListener(IntConsumer): void  [API 34+]
+ unregisterReceiver(BroadcastReceiver): void  [API 1+]
+ updateServiceGroup(ServiceConnection, int, int): void  [API 29+]
```

## Context.BindServiceFlags — framework; API 34+ (1 +, 0 *, 0 ~, 0 -)

```
+ of(long): Context.BindServiceFlags [static]  [API 34+]
```

## ContextParams — declaration; API 31+ (0 +, 2 *, 0 ~, 1 -)

```
* getAttributionTag(): String  [API 31+]
* getNextAttributionSource(): AttributionSource  [API 31+]
- shouldRegisterAttributionSource(): boolean  [API 35+]
```

## ContextParams.Builder — declaration; API 31+ (0 +, 5 *, 0 ~, 1 -)

```
* Builder()  [API 31+]
* Builder(ContextParams)  [API 31+]
* build(): ContextParams  [API 31+]
* setAttributionTag(String): ContextParams.Builder  [API 31+]
* setNextAttributionSource(AttributionSource): ContextParams.Builder  [API 31+]
- setShouldRegisterAttributionSource(boolean): ContextParams.Builder  [API 35+]
```

## ContextWrapper — framework; API 1+ (124 +, 0 *, 18 ~, 0 -)

```
+ ContextWrapper(Context)  [API 1+]
+ attachBaseContext(Context): void  [API 1+]
+ bindIsolatedService(Intent, int, String, Executor, ServiceConnection): boolean  [API 1+]
+ bindService(Intent, Context.BindServiceFlags, Executor, ServiceConnection): boolean  [API 1+]
+ bindService(Intent, ServiceConnection, Context.BindServiceFlags): boolean  [API 1+]
+ bindService(Intent, ServiceConnection, int): boolean  [API 1+]
+ bindService(Intent, int, Executor, ServiceConnection): boolean  [API 1+]
+ bindServiceAsUser(Intent, ServiceConnection, Context.BindServiceFlags, UserHandle): boolean  [API 1+]
+ bindServiceAsUser(Intent, ServiceConnection, int, UserHandle): boolean  [API 1+]
+ checkCallingOrSelfPermission(String): int  [API 1+]
+ checkCallingOrSelfUriPermission(Uri, int): int  [API 1+]
+ checkCallingOrSelfUriPermissions(List, int): int[]  [API 1+]
+ checkCallingPermission(String): int  [API 1+]
+ checkCallingUriPermission(Uri, int): int  [API 1+]
+ checkCallingUriPermissions(List, int): int[]  [API 1+]
+ checkContentUriPermissionFull(Uri, int, int, int): int  [API 1+]
+ checkPermission(String, int, int): int  [API 1+]
+ checkSelfPermission(String): int  [API 1+]
+ checkUriPermission(Uri, String, String, int, int, int): int  [API 1+]
~ checkUriPermission(Uri, int, int, int): int  [API 1+]
+ checkUriPermissions(List, int, int, int): int[]  [API 1+]
+ clearWallpaper(): void  [API 1+]
~ createAttributionContext(String): Context  [API 1+]
+ createConfigurationContext(Configuration): Context  [API 1+]
~ createContext(ContextParams): Context  [API 1+]
~ createContextForSplit(String): Context  [API 1+]
~ createDeviceContext(int): Context  [API 1+]
+ createDeviceProtectedStorageContext(): Context  [API 1+]
+ createDisplayContext(Display): Context  [API 1+]
+ createPackageContext(String, int): Context  [API 1+]
~ createWindowContext(Display, int, Bundle): Context  [API 1+]
~ createWindowContext(int, Bundle): Context  [API 1+]
+ databaseList(): String[]  [API 1+]
+ deleteDatabase(String): boolean  [API 1+]
+ deleteFile(String): boolean  [API 1+]
+ deleteSharedPreferences(String): boolean  [API 1+]
+ enforceCallingOrSelfPermission(String, String): void  [API 1+]
+ enforceCallingOrSelfUriPermission(Uri, int, String): void  [API 1+]
+ enforceCallingPermission(String, String): void  [API 1+]
+ enforceCallingUriPermission(Uri, int, String): void  [API 1+]
+ enforcePermission(String, int, int, String): void  [API 1+]
+ enforceUriPermission(Uri, String, String, int, int, int, String): void  [API 1+]
+ enforceUriPermission(Uri, int, int, int, String): void  [API 1+]
+ fileList(): String[]  [API 1+]
+ getApplicationContext(): Context  [API 1+]
+ getApplicationInfo(): ApplicationInfo  [API 1+]
+ getAssets(): AssetManager  [API 1+]
~ getAttributionSource(): AttributionSource  [API 1+]
~ getAttributionTag(): String  [API 1+]
+ getBaseContext(): Context  [API 1+]
+ getCacheDir(): File  [API 1+]
+ getClassLoader(): ClassLoader  [API 1+]
+ getCodeCacheDir(): File  [API 1+]
+ getContentResolver(): ContentResolver  [API 1+]
+ getDataDir(): File  [API 1+]
+ getDatabasePath(String): File  [API 1+]
~ getDeviceId(): int  [API 1+]
+ getDir(String, int): File  [API 1+]
+ getDisplay(): Display  [API 1+]
+ getExternalCacheDir(): File  [API 1+]
+ getExternalCacheDirs(): File[]  [API 1+]
+ getExternalFilesDir(String): File  [API 1+]
+ getExternalFilesDirs(String): File[]  [API 1+]
+ getExternalMediaDirs(): File[]  [API 1+]
+ getFileStreamPath(String): File  [API 1+]
+ getFilesDir(): File  [API 1+]
+ getMainExecutor(): Executor  [API 1+]
+ getMainLooper(): Looper  [API 1+]
+ getNoBackupFilesDir(): File  [API 1+]
+ getObbDir(): File  [API 1+]
+ getObbDirs(): File[]  [API 1+]
+ getOpPackageName(): String  [API 1+]
+ getPackageCodePath(): String  [API 1+]
+ getPackageManager(): PackageManager  [API 1+]
+ getPackageName(): String  [API 1+]
+ getPackageResourcePath(): String  [API 1+]
~ getParams(): ContextParams  [API 1+]
+ getResources(): Resources  [API 1+]
+ getSharedPreferences(String, int): SharedPreferences  [API 1+]
+ getSystemService(String): Object  [API 1+]
+ getSystemServiceName(Class): String  [API 1+]
+ getTheme(): Resources.Theme  [API 1+]
+ getWallpaper(): Drawable  [API 1+]
+ getWallpaperDesiredMinimumHeight(): int  [API 1+]
+ getWallpaperDesiredMinimumWidth(): int  [API 1+]
+ grantUriPermission(String, Uri, int): void  [API 1+]
~ isDeviceProtectedStorage(): boolean  [API 1+]
~ isRestricted(): boolean  [API 1+]
+ isUiContext(): boolean  [API 1+]
~ moveDatabaseFrom(Context, String): boolean  [API 1+]
~ moveSharedPreferencesFrom(Context, String): boolean  [API 1+]
+ openFileInput(String): FileInputStream  [API 1+]
+ openFileOutput(String, int): FileOutputStream  [API 1+]
+ openOrCreateDatabase(String, int, SQLiteDatabase.CursorFactory): SQLiteDatabase  [API 1+]
+ openOrCreateDatabase(String, int, SQLiteDatabase.CursorFactory, DatabaseErrorHandler): SQLiteDatabase  [API 1+]
+ peekWallpaper(): Drawable  [API 1+]
~ registerComponentCallbacks(ComponentCallbacks): void  [API 1+]
+ registerDeviceIdChangeListener(Executor, IntConsumer): void  [API 1+]
+ registerReceiver(BroadcastReceiver, IntentFilter): Intent  [API 1+]
+ registerReceiver(BroadcastReceiver, IntentFilter, String, Handler): Intent  [API 1+]
+ registerReceiver(BroadcastReceiver, IntentFilter, String, Handler, int): Intent  [API 1+]
+ registerReceiver(BroadcastReceiver, IntentFilter, int): Intent  [API 1+]
+ removeStickyBroadcast(Intent): void  [API 1+]
+ removeStickyBroadcastAsUser(Intent, UserHandle): void  [API 1+]
+ revokeSelfPermissionsOnKill(Collection): void  [API 1+]
+ revokeUriPermission(String, Uri, int): void  [API 1+]
+ revokeUriPermission(Uri, int): void  [API 1+]
+ sendBroadcast(Intent): void  [API 1+]
+ sendBroadcast(Intent, String): void  [API 1+]
+ sendBroadcast(Intent, String, Bundle): void  [API 1+]
+ sendBroadcastAsUser(Intent, UserHandle): void  [API 1+]
+ sendBroadcastAsUser(Intent, UserHandle, String): void  [API 1+]
+ sendOrderedBroadcast(Intent, String): void  [API 1+]
+ sendOrderedBroadcast(Intent, String, BroadcastReceiver, Handler, int, String, Bundle): void  [API 1+]
+ sendOrderedBroadcast(Intent, String, Bundle): void  [API 1+]
+ sendOrderedBroadcast(Intent, String, Bundle, BroadcastReceiver, Handler, int, String, Bundle): void  [API 1+]
+ sendOrderedBroadcast(Intent, String, String, BroadcastReceiver, Handler, int, String, Bundle): void  [API 1+]
+ sendOrderedBroadcast(Intent, int, String, String, BroadcastReceiver, Handler, String, Bundle, Bundle): void  [API 30+]
+ sendOrderedBroadcastAsUser(Intent, UserHandle, String, BroadcastReceiver, Handler, int, String, Bundle): void  [API 1+]
+ sendStickyBroadcast(Intent): void  [API 1+]
+ sendStickyBroadcast(Intent, Bundle): void  [API 1+]
+ sendStickyBroadcastAsUser(Intent, UserHandle): void  [API 1+]
+ sendStickyOrderedBroadcast(Intent, BroadcastReceiver, Handler, int, String, Bundle): void  [API 1+]
+ sendStickyOrderedBroadcastAsUser(Intent, UserHandle, BroadcastReceiver, Handler, int, String, Bundle): void  [API 1+]
+ setTheme(int): void  [API 1+]
+ setWallpaper(Bitmap): void  [API 1+]
+ setWallpaper(InputStream): void  [API 1+]
+ startActivities(Intent[]): void  [API 1+]
+ startActivities(Intent[], Bundle): void  [API 1+]
+ startActivity(Intent): void  [API 1+]
+ startActivity(Intent, Bundle): void  [API 1+]
+ startForegroundService(Intent): ComponentName  [API 1+]
~ startInstrumentation(ComponentName, String, Bundle): boolean  [API 1+]
+ startIntentSender(IntentSender, Intent, int, int, int): void  [API 1+]
+ startIntentSender(IntentSender, Intent, int, int, int, Bundle): void  [API 1+]
+ startService(Intent): ComponentName  [API 1+]
+ stopService(Intent): boolean  [API 1+]
+ unbindService(ServiceConnection): void  [API 1+]
~ unregisterComponentCallbacks(ComponentCallbacks): void  [API 1+]
+ unregisterDeviceIdChangeListener(IntConsumer): void  [API 1+]
+ unregisterReceiver(BroadcastReceiver): void  [API 1+]
+ updateServiceGroup(ServiceConnection, int, int): void  [API 1+]
```

## CursorLoader — framework; API 11+, deprecated in 28 (18 +, 0 *, 1 ~, 1 -)

```
+ CursorLoader(Context)  [API 11+]
+ CursorLoader(Context, Uri, String[], String, String[], String)  [API 11+]
~ cancelLoadInBackground(): void  [API 11+]
+ deliverResult(Cursor): void  [API 11+]
- dump(String, FileDescriptor, PrintWriter, String[]): void  [API 11+]
+ getProjection(): String[]  [API 11+]
+ getSelection(): String  [API 11+]
+ getSelectionArgs(): String[]  [API 11+]
+ getSortOrder(): String  [API 11+]
+ getUri(): Uri  [API 11+]
+ loadInBackground(): Cursor  [API 11+]
+ onCanceled(Cursor): void  [API 11+]
+ onReset(): void  [API 11+]
+ onStartLoading(): void  [API 11+]
+ onStopLoading(): void  [API 11+]
+ setProjection(String[]): void  [API 11+]
+ setSelection(String): void  [API 11+]
+ setSelectionArgs(String[]): void  [API 11+]
+ setSortOrder(String): void  [API 11+]
+ setUri(Uri): void  [API 11+]
```

## DialogInterface — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ cancel(): void  [API 1+]
+ dismiss(): void  [API 1+]
```

## DialogInterface.OnCancelListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onCancel(DialogInterface): void  [API 1+]
```

## DialogInterface.OnClickListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onClick(DialogInterface, int): void  [API 1+]
```

## DialogInterface.OnDismissListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onDismiss(DialogInterface): void  [API 1+]
```

## DialogInterface.OnKeyListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onKey(DialogInterface, int, KeyEvent): boolean  [API 1+]
```

## DialogInterface.OnMultiChoiceClickListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onClick(DialogInterface, int, boolean): void  [API 1+]
```

## DialogInterface.OnShowListener — framework; API 8+ (1 +, 0 *, 0 ~, 0 -)

```
+ onShow(DialogInterface): void  [API 8+]
```

## Entity — framework; API 8+ (5 +, 0 *, 0 ~, 0 -)

```
+ Entity(ContentValues)  [API 8+]
+ addSubValue(Uri, ContentValues): void  [API 8+]
+ getEntityValues(): ContentValues  [API 8+]
+ getSubValues(): ArrayList  [API 8+]
+ toString(): String  [API 8+]
```

## Entity.NamedContentValues — framework; API 8+ (1 +, 0 *, 0 ~, 0 -)

```
+ NamedContentValues(Uri, ContentValues)  [API 8+]
+ uri [field]  [API 8+]
+ values [field]  [API 8+]
```

## EntityIterator — declaration; API 8+ (2 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 8+]
+ reset(): void  [API 8+]
```

## Intent — framework; API 1+ (136 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 1+]
+ Intent()  [API 1+]
+ Intent(Context, Class)  [API 1+]
+ Intent(Intent)  [API 1+]
+ Intent(String)  [API 1+]
+ Intent(String, Uri)  [API 1+]
+ Intent(String, Uri, Context, Class)  [API 1+]
+ addCategory(String): Intent  [API 1+]
+ addFlags(int): Intent  [API 1+]
+ clone(): Object  [API 1+]
+ cloneFilter(): Intent  [API 1+]
+ createChooser(Intent, CharSequence): Intent [static]  [API 1+]
+ createChooser(Intent, CharSequence, IntentSender): Intent [static]  [API 22+]
+ describeContents(): int  [API 1+]
+ fillIn(Intent, int): int  [API 1+]
+ filterEquals(Intent): boolean  [API 1+]
+ filterHashCode(): int  [API 1+]
+ getAction(): String  [API 1+]
+ getBooleanArrayExtra(String): boolean[]  [API 1+]
+ getBooleanExtra(String, boolean): boolean  [API 1+]
+ getBundleExtra(String): Bundle  [API 1+]
+ getByteArrayExtra(String): byte[]  [API 1+]
+ getByteExtra(String, byte): byte  [API 1+]
+ getCategories(): Set  [API 1+]
+ getCharArrayExtra(String): char[]  [API 1+]
+ getCharExtra(String, char): char  [API 1+]
+ getCharSequenceArrayExtra(String): CharSequence[]  [API 8+]
+ getCharSequenceArrayListExtra(String): ArrayList  [API 8+]
+ getCharSequenceExtra(String): CharSequence  [API 1+]
+ getClipData(): ClipData  [API 16+]
+ getComponent(): ComponentName  [API 1+]
+ getData(): Uri  [API 1+]
+ getDataString(): String  [API 1+]
+ getDoubleArrayExtra(String): double[]  [API 1+]
+ getDoubleExtra(String, double): double  [API 1+]
+ getExtras(): Bundle  [API 1+]
+ getFlags(): int  [API 1+]
+ getFloatArrayExtra(String): float[]  [API 1+]
+ getFloatExtra(String, float): float  [API 1+]
+ getIdentifier(): String  [API 29+]
+ getIntArrayExtra(String): int[]  [API 1+]
+ getIntExtra(String, int): int  [API 1+]
+ getIntegerArrayListExtra(String): ArrayList  [API 1+]
+ getIntent(String): Intent [static]  [API 1+, deprecated in 15]
+ getIntentOld(String): Intent [static]  [API 1+]
+ getLongArrayExtra(String): long[]  [API 1+]
+ getLongExtra(String, long): long  [API 1+]
+ getPackage(): String  [API 4+]
+ getParcelableArrayExtra(String): Parcelable[]  [API 1+, deprecated in 33]
+ getParcelableArrayExtra(String, Class): Object[]  [API 33+]
+ getParcelableArrayListExtra(String): ArrayList  [API 1+, deprecated in 33]
+ getParcelableArrayListExtra(String, Class): ArrayList  [API 33+]
+ getParcelableExtra(String): Parcelable  [API 1+, deprecated in 33]
+ getParcelableExtra(String, Class): Object  [API 33+]
+ getScheme(): String  [API 1+]
+ getSelector(): Intent  [API 15+]
+ getSerializableExtra(String): Serializable  [API 1+, deprecated in 33]
+ getSerializableExtra(String, Class): Serializable  [API 33+]
+ getShortArrayExtra(String): short[]  [API 1+]
+ getShortExtra(String, short): short  [API 1+]
+ getSourceBounds(): Rect  [API 7+]
+ getStringArrayExtra(String): String[]  [API 1+]
+ getStringArrayListExtra(String): ArrayList  [API 1+]
+ getStringExtra(String): String  [API 1+]
+ getType(): String  [API 1+]
+ hasCategory(String): boolean  [API 1+]
+ hasExtra(String): boolean  [API 1+]
+ hasFileDescriptors(): boolean  [API 1+]
~ isMismatchingFilter(): boolean  [API 35+]
+ makeMainActivity(ComponentName): Intent [static]  [API 11+]
+ makeMainSelectorActivity(String, String): Intent [static]  [API 15+]
+ makeRestartActivityTask(ComponentName): Intent [static]  [API 11+]
+ normalizeMimeType(String): String [static]  [API 16+]
+ parseIntent(Resources, XmlPullParser, AttributeSet): Intent [static]  [API 1+]
+ parseUri(String, int): Intent [static]  [API 4+]
+ putCharSequenceArrayListExtra(String, ArrayList): Intent  [API 8+]
+ putExtra(String, Bundle): Intent  [API 1+]
+ putExtra(String, CharSequence): Intent  [API 1+]
+ putExtra(String, CharSequence[]): Intent  [API 8+]
+ putExtra(String, Parcelable): Intent  [API 1+]
+ putExtra(String, Parcelable[]): Intent  [API 1+]
+ putExtra(String, Serializable): Intent  [API 1+]
+ putExtra(String, String): Intent  [API 1+]
+ putExtra(String, String[]): Intent  [API 1+]
+ putExtra(String, boolean): Intent  [API 1+]
+ putExtra(String, boolean[]): Intent  [API 1+]
+ putExtra(String, byte): Intent  [API 1+]
+ putExtra(String, byte[]): Intent  [API 1+]
+ putExtra(String, char): Intent  [API 1+]
+ putExtra(String, char[]): Intent  [API 1+]
+ putExtra(String, double): Intent  [API 1+]
+ putExtra(String, double[]): Intent  [API 1+]
+ putExtra(String, float): Intent  [API 1+]
+ putExtra(String, float[]): Intent  [API 1+]
+ putExtra(String, int): Intent  [API 1+]
+ putExtra(String, int[]): Intent  [API 1+]
+ putExtra(String, long): Intent  [API 1+]
+ putExtra(String, long[]): Intent  [API 1+]
+ putExtra(String, short): Intent  [API 1+]
+ putExtra(String, short[]): Intent  [API 1+]
+ putExtras(Bundle): Intent  [API 1+]
+ putExtras(Intent): Intent  [API 1+]
+ putIntegerArrayListExtra(String, ArrayList): Intent  [API 1+]
+ putParcelableArrayListExtra(String, ArrayList): Intent  [API 1+]
+ putStringArrayListExtra(String, ArrayList): Intent  [API 1+]
+ readFromParcel(Parcel): void  [API 1+]
+ removeCategory(String): void  [API 1+]
+ removeExtra(String): void  [API 1+]
+ removeFlags(int): void  [API 26+]
+ replaceExtras(Bundle): Intent  [API 3+]
+ replaceExtras(Intent): Intent  [API 3+]
+ resolveActivity(PackageManager): ComponentName  [API 1+]
+ resolveActivityInfo(PackageManager, int): ActivityInfo  [API 1+]
+ resolveType(ContentResolver): String  [API 1+]
+ resolveType(Context): String  [API 1+]
+ resolveTypeIfNeeded(ContentResolver): String  [API 1+]
+ setAction(String): Intent  [API 1+]
+ setClass(Context, Class): Intent  [API 1+]
+ setClassName(Context, String): Intent  [API 1+]
+ setClassName(String, String): Intent  [API 1+]
+ setClipData(ClipData): void  [API 16+]
+ setComponent(ComponentName): Intent  [API 1+]
+ setData(Uri): Intent  [API 1+]
+ setDataAndNormalize(Uri): Intent  [API 16+]
+ setDataAndType(Uri, String): Intent  [API 1+]
+ setDataAndTypeAndNormalize(Uri, String): Intent  [API 16+]
+ setExtrasClassLoader(ClassLoader): void  [API 1+]
+ setFlags(int): Intent  [API 1+]
+ setIdentifier(String): Intent  [API 29+]
+ setPackage(String): Intent  [API 4+]
+ setSelector(Intent): void  [API 15+]
+ setSourceBounds(Rect): void  [API 7+]
+ setType(String): Intent  [API 1+]
+ setTypeAndNormalize(String): Intent  [API 16+]
+ toString(): String  [API 1+]
+ toURI(): String  [API 1+, deprecated in 15]
+ toUri(int): String  [API 4+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## Intent.FilterComparison — framework; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ FilterComparison(Intent)  [API 1+]
+ equals(Object): boolean  [API 1+]
+ getIntent(): Intent  [API 1+]
+ hashCode(): int  [API 1+]
```

## Intent.ShortcutIconResource — framework; API 1+ (4 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 1+]
+ ShortcutIconResource()  [API 1+]
~ describeContents(): int  [API 1+]
+ fromContext(Context, int): Intent.ShortcutIconResource [static]  [API 1+]
+ packageName [field]  [API 1+]
+ resourceName [field]  [API 1+]
+ toString(): String  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## IntentFilter — framework; API 1+ (58 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 1+]
+ IntentFilter()  [API 1+]
+ IntentFilter(IntentFilter)  [API 1+]
+ IntentFilter(String)  [API 1+]
+ IntentFilter(String, String)  [API 1+]
+ actionsIterator(): Iterator  [API 1+]
+ addAction(String): void  [API 1+]
+ addCategory(String): void  [API 1+]
+ addDataAuthority(String, String): void  [API 1+]
+ addDataPath(String, int): void  [API 1+]
+ addDataScheme(String): void  [API 1+]
+ addDataSchemeSpecificPart(String, int): void  [API 19+]
+ addDataType(String): void  [API 1+]
+ addUriRelativeFilterGroup(UriRelativeFilterGroup): void  [API 35+]
+ asPredicate(): Predicate  [API 33+]
+ asPredicateWithTypeResolution(ContentResolver): Predicate  [API 33+]
+ authoritiesIterator(): Iterator  [API 1+]
+ categoriesIterator(): Iterator  [API 1+]
+ clearUriRelativeFilterGroups(): void  [API 35+]
+ countActions(): int  [API 1+]
+ countCategories(): int  [API 1+]
+ countDataAuthorities(): int  [API 1+]
+ countDataPaths(): int  [API 1+]
+ countDataSchemeSpecificParts(): int  [API 19+]
+ countDataSchemes(): int  [API 1+]
+ countDataTypes(): int  [API 1+]
+ countUriRelativeFilterGroups(): int  [API 35+]
+ create(String, String): IntentFilter [static]  [API 1+]
~ describeContents(): int  [API 1+]
+ dump(Printer, String): void  [API 1+]
+ getAction(int): String  [API 1+]
+ getCategory(int): String  [API 1+]
+ getDataAuthority(int): IntentFilter.AuthorityEntry  [API 1+]
+ getDataPath(int): PatternMatcher  [API 1+]
+ getDataScheme(int): String  [API 1+]
+ getDataSchemeSpecificPart(int): PatternMatcher  [API 19+]
+ getDataType(int): String  [API 1+]
+ getPriority(): int  [API 1+]
+ getUriRelativeFilterGroup(int): UriRelativeFilterGroup  [API 35+]
+ hasAction(String): boolean  [API 1+]
+ hasCategory(String): boolean  [API 1+]
+ hasDataAuthority(Uri): boolean  [API 1+]
+ hasDataPath(String): boolean  [API 1+]
+ hasDataScheme(String): boolean  [API 1+]
+ hasDataSchemeSpecificPart(String): boolean  [API 19+]
+ hasDataType(String): boolean  [API 1+]
+ match(ContentResolver, Intent, boolean, String): int  [API 1+]
+ match(String, String, String, Uri, Set, String): int  [API 1+]
+ matchAction(String): boolean  [API 1+]
+ matchCategories(Set): String  [API 1+]
+ matchData(String, String, Uri): int  [API 1+]
+ matchDataAuthority(Uri): int  [API 1+]
+ pathsIterator(): Iterator  [API 1+]
+ readFromXml(XmlPullParser): void  [API 1+]
+ schemeSpecificPartsIterator(): Iterator  [API 19+]
+ schemesIterator(): Iterator  [API 1+]
+ setPriority(int): void  [API 1+]
+ typesIterator(): Iterator  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
+ writeToXml(XmlSerializer): void  [API 1+]
```

## IntentFilter.AuthorityEntry — framework; API 1+ (5 +, 0 *, 0 ~, 0 -)

```
+ AuthorityEntry(String, String)  [API 1+]
+ equals(Object): boolean  [API 1+]
+ getHost(): String  [API 1+]
+ getPort(): int  [API 1+]
+ match(Uri): int  [API 1+]
```

## IntentFilter.MalformedMimeTypeException — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ MalformedMimeTypeException()  [API 1+]
+ MalformedMimeTypeException(String)  [API 1+]
```

## IntentSender — framework; API 4+ (12 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 4+]
~ describeContents(): int  [API 4+]
+ equals(Object): boolean  [API 4+]
+ getCreatorPackage(): String  [API 17+]
+ getCreatorUid(): int  [API 17+]
+ getCreatorUserHandle(): UserHandle  [API 17+]
+ getTargetPackage(): String  [API 9+, deprecated in 17]
+ hashCode(): int  [API 4+]
+ readIntentSenderOrNullFromParcel(Parcel): IntentSender [static]  [API 4+]
+ sendIntent(Context, int, Intent, IntentSender.OnFinished, Handler): void  [API 4+]
+ sendIntent(Context, int, Intent, IntentSender.OnFinished, Handler, String): void  [API 14+]
+ toString(): String  [API 4+]
+ writeIntentSenderOrNullToParcel(IntentSender, Parcel): void [static]  [API 4+]
+ writeToParcel(Parcel, int): void  [API 4+]
```

## IntentSender.OnFinished — framework; API 4+ (1 +, 0 *, 0 ~, 0 -)

```
+ onSendFinished(IntentSender, Intent, int, String, Bundle): void  [API 4+]
```

## IntentSender.SendIntentException — framework; API 4+ (3 +, 0 *, 0 ~, 0 -)

```
+ SendIntentException()  [API 4+]
+ SendIntentException(Exception)  [API 4+]
+ SendIntentException(String)  [API 4+]
```

## Loader — framework; API 11+, deprecated in 28 (30 +, 0 *, 0 ~, 1 -)

```
+ Loader(Context)  [API 11+]
+ abandon(): void  [API 11+]
+ cancelLoad(): boolean  [API 16+]
+ commitContentChanged(): void  [API 18+]
+ dataToString(Object): String  [API 11+]
+ deliverCancellation(): void  [API 16+]
+ deliverResult(Object): void  [API 11+]
- dump(String, FileDescriptor, PrintWriter, String[]): void  [API 11+]
+ forceLoad(): void  [API 11+]
+ getContext(): Context  [API 11+]
+ getId(): int  [API 11+]
+ isAbandoned(): boolean  [API 11+]
+ isReset(): boolean  [API 11+]
+ isStarted(): boolean  [API 11+]
+ onAbandon(): void  [API 11+]
+ onCancelLoad(): boolean  [API 16+]
+ onContentChanged(): void  [API 11+]
+ onForceLoad(): void  [API 11+]
+ onReset(): void  [API 11+]
+ onStartLoading(): void  [API 11+]
+ onStopLoading(): void  [API 11+]
+ registerListener(int, Loader.OnLoadCompleteListener): void  [API 11+]
+ registerOnLoadCanceledListener(Loader.OnLoadCanceledListener): void  [API 16+]
+ reset(): void  [API 11+]
+ rollbackContentChanged(): void  [API 18+]
+ startLoading(): void  [API 11+]
+ stopLoading(): void  [API 11+]
+ takeContentChanged(): boolean  [API 11+]
+ toString(): String  [API 11+]
+ unregisterListener(Loader.OnLoadCompleteListener): void  [API 11+]
+ unregisterOnLoadCanceledListener(Loader.OnLoadCanceledListener): void  [API 16+]
```

## Loader.ForceLoadContentObserver — framework; API 11+, deprecated in 28 (2 +, 0 *, 1 ~, 0 -)

```
+ ForceLoadContentObserver(Loader)  [API 11+]
~ deliverSelfNotifications(): boolean  [API 11+]
+ onChange(boolean): void  [API 11+]
```

## Loader.OnLoadCanceledListener — framework; API 16+, deprecated in 28 (1 +, 0 *, 0 ~, 0 -)

```
+ onLoadCanceled(Loader): void  [API 16+]
```

## Loader.OnLoadCompleteListener — framework; API 11+, deprecated in 28 (1 +, 0 *, 0 ~, 0 -)

```
+ onLoadComplete(Loader, Object): void  [API 11+]
```

## LocusId — framework; API 29+ (6 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 29+]
+ LocusId(String)  [API 29+]
~ describeContents(): int  [API 29+]
+ equals(Object): boolean  [API 29+]
+ getId(): String  [API 29+]
+ hashCode(): int  [API 29+]
+ toString(): String  [API 29+]
+ writeToParcel(Parcel, int): void  [API 29+]
```

## MutableContextWrapper — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ MutableContextWrapper(Context)  [API 1+]
+ setBaseContext(Context): void  [API 1+]
```

## OperationApplicationException — framework; API 5+ (7 +, 0 *, 0 ~, 0 -)

```
+ OperationApplicationException()  [API 5+]
+ OperationApplicationException(String)  [API 5+]
+ OperationApplicationException(String, Throwable)  [API 5+]
+ OperationApplicationException(String, int)  [API 5+]
+ OperationApplicationException(Throwable)  [API 5+]
+ OperationApplicationException(int)  [API 5+]
+ getNumSuccessfulYieldPoints(): int  [API 5+]
```

## PeriodicSync — framework; API 8+ (4 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 8+]
+ PeriodicSync(Account, String, Bundle, long)  [API 8+]
+ account [field]  [API 8+]
+ authority [field]  [API 8+]
~ describeContents(): int  [API 8+]
+ equals(Object): boolean  [API 8+]
+ extras [field]  [API 8+]
+ period [field]  [API 8+]
+ toString(): String  [API 8+]
+ writeToParcel(Parcel, int): void  [API 8+]
```

## QuickViewConstants — declaration; API 26+ (0 +, 0 *, 0 ~, 0 -)

## ReceiverCallNotAllowedException — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ ReceiverCallNotAllowedException(String)  [API 1+]
```

## RestrictionEntry — framework; API 18+ (35 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 18+]
+ RestrictionEntry(Parcel)  [API 18+]
+ RestrictionEntry(String, String)  [API 18+]
+ RestrictionEntry(String, String[])  [API 18+]
+ RestrictionEntry(String, boolean)  [API 18+]
+ RestrictionEntry(String, int)  [API 21+]
+ RestrictionEntry(int, String)  [API 21+]
+ createBundleArrayEntry(String, RestrictionEntry[]): RestrictionEntry [static]  [API 23+]
+ createBundleEntry(String, RestrictionEntry[]): RestrictionEntry [static]  [API 23+]
~ describeContents(): int  [API 18+]
+ equals(Object): boolean  [API 18+]
+ getAllSelectedStrings(): String[]  [API 18+]
+ getChoiceEntries(): String[]  [API 18+]
+ getChoiceValues(): String[]  [API 18+]
+ getDescription(): String  [API 18+]
+ getIntValue(): int  [API 21+]
+ getKey(): String  [API 18+]
+ getRestrictions(): RestrictionEntry[]  [API 23+]
+ getSelectedState(): boolean  [API 18+]
+ getSelectedString(): String  [API 18+]
+ getTitle(): String  [API 18+]
+ getType(): int  [API 18+]
+ hashCode(): int  [API 18+]
+ setAllSelectedStrings(String[]): void  [API 18+]
+ setChoiceEntries(Context, int): void  [API 18+]
+ setChoiceEntries(String[]): void  [API 18+]
+ setChoiceValues(Context, int): void  [API 18+]
+ setChoiceValues(String[]): void  [API 18+]
+ setDescription(String): void  [API 18+]
+ setIntValue(int): void  [API 21+]
+ setRestrictions(RestrictionEntry[]): void  [API 23+]
+ setSelectedState(boolean): void  [API 18+]
+ setSelectedString(String): void  [API 18+]
+ setTitle(String): void  [API 18+]
+ setType(int): void  [API 18+]
+ toString(): String  [API 18+]
+ writeToParcel(Parcel, int): void  [API 18+]
```

## RestrictionsManager — framework; API 21+ (0 +, 0 *, 0 ~, 8 -)

```
- convertRestrictionsToBundle(List): Bundle [static]  [API 23+]
- createLocalApprovalIntent(): Intent  [API 21+]
- getApplicationRestrictions(): Bundle  [API 21+]
- getApplicationRestrictionsPerAdmin(): List  [API 34+]
- getManifestRestrictions(String): List  [API 21+]
- hasRestrictionsProvider(): boolean  [API 21+]
- notifyPermissionResponse(String, PersistableBundle): void  [API 21+]
- requestPermission(String, String, PersistableBundle): void  [API 21+]
```

## SearchRecentSuggestionsProvider — framework; API 1+ (7 +, 0 *, 1 ~, 0 -)

```
+ SearchRecentSuggestionsProvider()  [API 1+]
+ delete(Uri, String, String[]): int  [API 1+]
+ getType(Uri): String  [API 1+]
+ insert(Uri, ContentValues): Uri  [API 1+]
+ onCreate(): boolean  [API 1+]
+ query(Uri, String[], String, String[], String): Cursor  [API 1+]
+ setupSuggestions(String, int): void  [API 1+]
~ update(Uri, ContentValues, String, String[]): int  [API 1+]
```

## ServiceConnection — framework; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ onBindingDied(ComponentName): void  [API 26+]
+ onNullBinding(ComponentName): void  [API 28+]
+ onServiceConnected(ComponentName, IBinder): void  [API 1+]
+ onServiceDisconnected(ComponentName): void  [API 1+]
```

## SharedPreferences — framework; API 1+ (11 +, 0 *, 0 ~, 0 -)

```
+ contains(String): boolean  [API 1+]
+ edit(): SharedPreferences.Editor  [API 1+]
+ getAll(): Map  [API 1+]
+ getBoolean(String, boolean): boolean  [API 1+]
+ getFloat(String, float): float  [API 1+]
+ getInt(String, int): int  [API 1+]
+ getLong(String, long): long  [API 1+]
+ getString(String, String): String  [API 1+]
+ getStringSet(String, Set): Set  [API 11+]
+ registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener): void  [API 1+]
+ unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener): void  [API 1+]
```

## SharedPreferences.Editor — framework; API 1+ (10 +, 0 *, 0 ~, 0 -)

```
+ apply(): void  [API 9+]
+ clear(): SharedPreferences.Editor  [API 1+]
+ commit(): boolean  [API 1+]
+ putBoolean(String, boolean): SharedPreferences.Editor  [API 1+]
+ putFloat(String, float): SharedPreferences.Editor  [API 1+]
+ putInt(String, int): SharedPreferences.Editor  [API 1+]
+ putLong(String, long): SharedPreferences.Editor  [API 1+]
+ putString(String, String): SharedPreferences.Editor  [API 1+]
+ putStringSet(String, Set): SharedPreferences.Editor  [API 11+]
+ remove(String): SharedPreferences.Editor  [API 1+]
```

## SharedPreferences.OnSharedPreferenceChangeListener — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ onSharedPreferenceChanged(SharedPreferences, String): void  [API 1+]
```

## SyncAdapterType — framework; API 5+ (12 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 5+]
+ SyncAdapterType(Parcel)  [API 5+]
+ SyncAdapterType(String, String, boolean, boolean)  [API 5+]
+ accountType [field]  [API 5+]
+ allowParallelSyncs(): boolean  [API 11+]
+ authority [field]  [API 5+]
~ describeContents(): int  [API 5+]
+ equals(Object): boolean  [API 5+]
+ getSettingsActivity(): String  [API 14+]
+ hashCode(): int  [API 5+]
+ isAlwaysSyncable(): boolean  [API 11+]
+ isKey [field]  [API 5+]
+ isUserVisible(): boolean  [API 5+]
+ newKey(String, String): SyncAdapterType [static]  [API 5+]
+ supportsUploading(): boolean  [API 5+]
+ toString(): String  [API 5+]
+ writeToParcel(Parcel, int): void  [API 5+]
```

## SyncContext — declaration; API 5+ (0 +, 0 *, 0 ~, 2 -)

```
- getSyncContextBinder(): IBinder  [API 5+]
- onFinished(SyncResult): void  [API 5+]
```

## SyncInfo — framework; API 8+ (1 +, 0 *, 1 ~, 0 -)

```
+ account [field]  [API 8+]
+ authority [field]  [API 8+]
~ describeContents(): int  [API 8+]
+ startTime [field]  [API 8+]
+ writeToParcel(Parcel, int): void  [API 8+]
```

## SyncRequest — declaration; API 19+ (0 +, 0 *, 0 ~, 2 -)

```
- CREATOR [field]  [API 19+]
- describeContents(): int  [API 19+]
- writeToParcel(Parcel, int): void  [API 19+]
```

## SyncRequest.Builder — declaration; API 19+ (0 +, 0 *, 0 ~, 14 -)

```
- Builder()  [API 19+]
- build(): SyncRequest  [API 19+]
- setDisallowMetered(boolean): SyncRequest.Builder  [API 19+]
- setExpedited(boolean): SyncRequest.Builder  [API 19+]
- setExtras(Bundle): SyncRequest.Builder  [API 19+]
- setIgnoreBackoff(boolean): SyncRequest.Builder  [API 19+]
- setIgnoreSettings(boolean): SyncRequest.Builder  [API 19+]
- setManual(boolean): SyncRequest.Builder  [API 19+]
- setNoRetry(boolean): SyncRequest.Builder  [API 19+]
- setRequiresCharging(boolean): SyncRequest.Builder  [API 24+]
- setScheduleAsExpeditedJob(boolean): SyncRequest.Builder  [API 31+]
- setSyncAdapter(Account, String): SyncRequest.Builder  [API 19+]
- syncOnce(): SyncRequest.Builder  [API 19+]
- syncPeriodic(long, long): SyncRequest.Builder  [API 19+]
```

## SyncResult — framework; API 5+ (9 +, 0 *, 1 ~, 0 -)

```
+ ALREADY_IN_PROGRESS [field]  [API 5+]
+ CREATOR [field]  [API 5+]
+ SyncResult()  [API 5+]
+ clear(): void  [API 5+]
+ databaseError [field]  [API 5+]
+ delayUntil [field]  [API 8+]
~ describeContents(): int  [API 5+]
+ fullSyncRequested [field]  [API 5+]
+ hasError(): boolean  [API 5+]
+ hasHardError(): boolean  [API 5+]
+ hasSoftError(): boolean  [API 5+]
+ madeSomeProgress(): boolean  [API 5+]
+ moreRecordsToGet [field]  [API 5+]
+ partialSyncUnavailable [field]  [API 5+]
+ stats [field]  [API 5+]
+ syncAlreadyInProgress [field]  [API 5+]
+ toDebugString(): String  [API 5+]
+ toString(): String  [API 5+]
+ tooManyDeletions [field]  [API 5+]
+ tooManyRetries [field]  [API 5+]
+ writeToParcel(Parcel, int): void  [API 5+]
```

## SyncStats — framework; API 5+ (5 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 5+]
+ SyncStats()  [API 5+]
+ SyncStats(Parcel)  [API 5+]
+ clear(): void  [API 5+]
~ describeContents(): int  [API 5+]
+ numAuthExceptions [field]  [API 5+]
+ numConflictDetectedExceptions [field]  [API 5+]
+ numDeletes [field]  [API 5+]
+ numEntries [field]  [API 5+]
+ numInserts [field]  [API 5+]
+ numIoExceptions [field]  [API 5+]
+ numParseExceptions [field]  [API 5+]
+ numSkippedEntries [field]  [API 5+]
+ numUpdates [field]  [API 5+]
+ toString(): String  [API 5+]
+ writeToParcel(Parcel, int): void  [API 5+]
```

## SyncStatusObserver — framework; API 5+ (1 +, 0 *, 0 ~, 0 -)

```
+ onStatusChanged(int): void  [API 5+]
```

## UriMatcher — framework; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ UriMatcher(int)  [API 1+]
+ addURI(String, String, int): void  [API 1+]
+ match(Uri): int  [API 1+]
```

## UriPermission — framework; API 19+ (1 +, 0 *, 4 ~, 2 -)

```
- CREATOR [field]  [API 19+]
- describeContents(): int  [API 19+]
~ getPersistedTime(): long  [API 19+]
~ getUri(): Uri  [API 19+]
~ isReadPermission(): boolean  [API 19+]
~ isWritePermission(): boolean  [API 19+]
+ toString(): String  [API 19+]
- writeToParcel(Parcel, int): void  [API 19+]
```

## UriRelativeFilter — declaration; API 35+ (0 +, 7 *, 0 ~, 1 -)

```
* UriRelativeFilter(int, int, String)  [API 35+]
* equals(Object): boolean  [API 35+]
* getFilter(): String  [API 35+]
* getPatternType(): int  [API 35+]
* getUriPart(): int  [API 35+]
* hashCode(): int  [API 35+]
- matchData(Uri): boolean  [API 35+]
* toString(): String  [API 35+]
```

## UriRelativeFilterGroup — declaration; API 35+ (0 +, 2 *, 0 ~, 6 -)

```
* UriRelativeFilterGroup(int)  [API 35+]
- addUriRelativeFilter(UriRelativeFilter): void  [API 35+]
- equals(Object): boolean  [API 35+]
* getAction(): int  [API 35+]
- getUriRelativeFilters(): Collection  [API 35+]
- hashCode(): int  [API 35+]
- matchData(Uri): boolean  [API 35+]
- toString(): String  [API 35+]
```

