# android.app.sdksandbox

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AppOwnedSdkSandboxInterface — declaration; API 35+ (0 +, 0 *, 0 ~, 6 -)

```
- AppOwnedSdkSandboxInterface(String, long, IBinder)  [API 35+]
- CREATOR [field]  [API 35+]
- describeContents(): int  [API 35+]
- getInterface(): IBinder  [API 35+]
- getName(): String  [API 35+]
- getVersion(): long  [API 35+]
- writeToParcel(Parcel, int): void  [API 35+]
```

## LoadSdkException — declaration; API 34+ (0 +, 0 *, 0 ~, 5 -)

```
- CREATOR [field]  [API 34+]
- LoadSdkException(Throwable, Bundle)  [API 34+]
- describeContents(): int  [API 34+]
- getExtraInformation(): Bundle  [API 34+]
- getLoadSdkErrorCode(): int  [API 34+]
- writeToParcel(Parcel, int): void  [API 34+]
```

## RequestSurfacePackageException — declaration; API 34+, deprecated in 36 (0 +, 0 *, 0 ~, 5 -)

```
- RequestSurfacePackageException(int, String)  [API 34+]
- RequestSurfacePackageException(int, String, Throwable)  [API 34+]
- RequestSurfacePackageException(int, String, Throwable, Bundle)  [API 34+]
- getExtraErrorInformation(): Bundle  [API 34+]
- getRequestSurfacePackageErrorCode(): int  [API 34+]
```

## SandboxedSdk — declaration; API 34+ (0 +, 0 *, 0 ~, 5 -)

```
- CREATOR [field]  [API 34+]
- SandboxedSdk(IBinder)  [API 34+]
- describeContents(): int  [API 34+]
- getInterface(): IBinder  [API 34+]
- getSharedLibraryInfo(): SharedLibraryInfo  [API 34+]
- writeToParcel(Parcel, int): void  [API 34+]
```

## SandboxedSdkProvider — declaration; API 34+ (2 +, 0 *, 0 ~, 4 -)

```
- SandboxedSdkProvider()  [API 34+]
- attachContext(Context): void  [API 34+]
- beforeUnloadSdk(): void  [API 34+]
- getContext(): Context  [API 34+]
+ getView(Context, Bundle, int, int): View  [API 34+, deprecated in 36]
+ onLoadSdk(Bundle): SandboxedSdk  [API 34+]
```

## SdkSandboxManager — declaration; API 33+ (0 +, 0 *, 0 ~, 14 -)

```
- addSdkSandboxProcessDeathCallback(Executor, SdkSandboxManager.SdkSandboxProcessDeathCallback): void  [API 34+]
- addSyncedSharedPreferencesKeys(Set): void  [API 34+]
- getAppOwnedSdkSandboxInterfaces(): List  [API 35+]
- getSandboxedSdks(): List  [API 34+]
- getSdkSandboxState(): int [static]  [API 33+]
- getSyncedSharedPreferencesKeys(): Set  [API 34+]
- loadSdk(String, Bundle, Executor, OutcomeReceiver): void  [API 34+]
- registerAppOwnedSdkSandboxInterface(AppOwnedSdkSandboxInterface): void  [API 35+]
- removeSdkSandboxProcessDeathCallback(SdkSandboxManager.SdkSandboxProcessDeathCallback): void  [API 34+]
- removeSyncedSharedPreferencesKeys(Set): void  [API 34+]
- requestSurfacePackage(String, Bundle, Executor, OutcomeReceiver): void  [API 34+, deprecated in 36]
- startSdkSandboxActivity(Activity, IBinder): void  [API 34+]
- unloadSdk(String): void  [API 34+]
- unregisterAppOwnedSdkSandboxInterface(String): void  [API 35+]
```

## SdkSandboxManager.SdkSandboxProcessDeathCallback — declaration; API 34+ (1 +, 0 *, 0 ~, 0 -)

```
+ onSdkSandboxDied(): void  [API 34+]
```

