# android.app.slice

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## Slice — declaration; API 28+, deprecated in 35 (0 +, 3 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 28+]
- Slice(Parcel)  [API 28+]
- describeContents(): int  [API 28+]
- getHints(): List  [API 28+]
- getItems(): List  [API 28+]
* getSpec(): SliceSpec  [API 28+]
* getUri(): Uri  [API 28+]
* isCallerNeeded(): boolean  [API 28+]
- toString(): String  [API 28+]
- writeToParcel(Parcel, int): void  [API 28+]
```

## Slice.Builder — declaration; API 28+, deprecated in 35 (0 +, 3 *, 0 ~, 10 -)

```
- Builder(Slice.Builder)  [API 28+]
* Builder(Uri, SliceSpec)  [API 28+]
- addAction(PendingIntent, Slice, String): Slice.Builder  [API 28+]
- addBundle(Bundle, String, List): Slice.Builder  [API 28+]
- addHints(List): Slice.Builder  [API 28+]
- addIcon(Icon, String, List): Slice.Builder  [API 28+]
- addInt(int, String, List): Slice.Builder  [API 28+]
- addLong(long, String, List): Slice.Builder  [API 28+]
- addRemoteInput(RemoteInput, String, List): Slice.Builder  [API 28+]
- addSubSlice(Slice, String): Slice.Builder  [API 28+]
- addText(CharSequence, String, List): Slice.Builder  [API 28+]
* build(): Slice  [API 28+]
* setCallerNeeded(boolean): Slice.Builder  [API 28+]
```

## SliceItem — declaration; API 28+, deprecated in 35 (0 +, 0 *, 0 ~, 14 -)

```
- CREATOR [field]  [API 28+]
- describeContents(): int  [API 28+]
- getAction(): PendingIntent  [API 28+]
- getBundle(): Bundle  [API 28+]
- getFormat(): String  [API 28+]
- getHints(): List  [API 28+]
- getIcon(): Icon  [API 28+]
- getInt(): int  [API 28+]
- getLong(): long  [API 28+]
- getRemoteInput(): RemoteInput  [API 28+]
- getSlice(): Slice  [API 28+]
- getSubType(): String  [API 28+]
- getText(): CharSequence  [API 28+]
- hasHint(String): boolean  [API 28+]
- writeToParcel(Parcel, int): void  [API 28+]
```

## SliceManager — declaration; API 28+, deprecated in 35 (0 +, 0 *, 0 ~, 11 -)

```
- bindSlice(Intent, Set): Slice  [API 28+]
- bindSlice(Uri, Set): Slice  [API 28+]
- checkSlicePermission(Uri, int, int): int  [API 28+]
- getPinnedSlices(): List  [API 28+]
- getPinnedSpecs(Uri): Set  [API 28+]
- getSliceDescendants(Uri): Collection  [API 28+]
- grantSlicePermission(String, Uri): void  [API 28+]
- mapIntentToUri(Intent): Uri  [API 28+]
- pinSlice(Uri, Set): void  [API 28+]
- revokeSlicePermission(String, Uri): void  [API 28+]
- unpinSlice(Uri): void  [API 28+]
```

## SliceMetrics — declaration; API 28+, deprecated in 35 (0 +, 0 *, 0 ~, 4 -)

```
- SliceMetrics(Context, Uri)  [API 28+]
- logHidden(): void  [API 28+]
- logTouch(int, Uri): void  [API 28+]
- logVisible(): void  [API 28+]
```

## SliceProvider — declaration; API 28+, deprecated in 35 (2 +, 0 *, 0 ~, 15 -)

```
- SliceProvider()  [API 28+]
- SliceProvider(String[])  [API 28+]
- attachInfo(Context, ProviderInfo): void  [API 28+]
- call(String, String, Bundle): Bundle  [API 28+]
- delete(Uri, String, String[]): int  [API 28+]
- getType(Uri): String  [API 28+]
- insert(Uri, ContentValues): Uri  [API 28+]
- onBindSlice(Uri, Set): Slice  [API 28+]
- onCreatePermissionRequest(Uri): PendingIntent  [API 28+]
- onGetSliceDescendants(Uri): Collection  [API 28+]
- onMapIntentToUri(Intent): Uri  [API 28+]
+ onSlicePinned(Uri): void  [API 28+]
+ onSliceUnpinned(Uri): void  [API 28+]
- query(Uri, String[], Bundle, CancellationSignal): Cursor  [API 28+]
- query(Uri, String[], String, String[], String): Cursor  [API 28+]
- query(Uri, String[], String, String[], String, CancellationSignal): Cursor  [API 28+]
- update(Uri, ContentValues, String, String[]): int  [API 28+]
```

## SliceSpec — declaration; API 28+, deprecated in 35 (0 +, 7 *, 0 ~, 1 -)

```
+ CREATOR [field]  [API 28+]
* SliceSpec(String, int)  [API 28+]
- canRender(SliceSpec): boolean  [API 28+]
* describeContents(): int  [API 28+]
* equals(Object): boolean  [API 28+]
* getRevision(): int  [API 28+]
* getType(): String  [API 28+]
* toString(): String  [API 28+]
* writeToParcel(Parcel, int): void  [API 28+]
```

