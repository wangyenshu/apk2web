# android.widget.photopicker

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## EmbeddedPhotoPickerClient — declaration; API 36+ (5 +, 0 *, 0 ~, 0 -)

```
+ onSelectionComplete(): void  [API 36+]
+ onSessionError(Throwable): void  [API 36+]
+ onSessionOpened(EmbeddedPhotoPickerSession): void  [API 36+]
+ onUriPermissionGranted(List): void  [API 36+]
+ onUriPermissionRevoked(List): void  [API 36+]
```

## EmbeddedPhotoPickerFeatureInfo — declaration; API 36+ (0 +, 6 *, 0 ~, 0 -)

```
* getAccentColor(): long  [API 36+]
* getMaxSelectionLimit(): int  [API 36+]
* getMimeTypes(): List  [API 36+]
* getPreSelectedUris(): List  [API 36+]
* getThemeNightMode(): int  [API 36+]
* isOrderedSelection(): boolean  [API 36+]
```

## EmbeddedPhotoPickerFeatureInfo.Builder — declaration; API 36+ (0 +, 8 *, 0 ~, 0 -)

```
* Builder()  [API 36+]
* build(): EmbeddedPhotoPickerFeatureInfo  [API 36+]
* setAccentColor(long): EmbeddedPhotoPickerFeatureInfo.Builder  [API 36+]
* setMaxSelectionLimit(int): EmbeddedPhotoPickerFeatureInfo.Builder  [API 36+]
* setMimeTypes(List): EmbeddedPhotoPickerFeatureInfo.Builder  [API 36+]
* setOrderedSelection(boolean): EmbeddedPhotoPickerFeatureInfo.Builder  [API 36+]
* setPreSelectedUris(List): EmbeddedPhotoPickerFeatureInfo.Builder  [API 36+]
* setThemeNightMode(int): EmbeddedPhotoPickerFeatureInfo.Builder  [API 36+]
```

## EmbeddedPhotoPickerProvider — declaration; API 36+ (1 +, 0 *, 0 ~, 0 -)

```
+ openSession(IBinder, int, int, int, EmbeddedPhotoPickerFeatureInfo, Executor, EmbeddedPhotoPickerClient): void  [API 36+]
```

## EmbeddedPhotoPickerProviderFactory — declaration; API 36+ (0 +, 0 *, 0 ~, 1 -)

```
- create(Context): EmbeddedPhotoPickerProvider  [API 36+]
```

## EmbeddedPhotoPickerSession — declaration; API 36+ (7 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 36+]
+ getSurfacePackage(): SurfaceControlViewHost.SurfacePackage  [API 36+]
+ notifyConfigurationChanged(Configuration): void  [API 36+]
+ notifyPhotoPickerExpanded(boolean): void  [API 36+]
+ notifyResized(int, int): void  [API 36+]
+ notifyVisibilityChanged(boolean): void  [API 36+]
+ requestRevokeUriPermission(List): void  [API 36+]
```

