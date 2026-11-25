# android.app.sdksandbox.sdkprovider

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## SdkSandboxActivityHandler — declaration; API 34+ (1 +, 0 *, 0 ~, 0 -)

```
+ onActivityCreated(Activity): void  [API 34+]
```

## SdkSandboxClientImportanceListener — declaration; API 36+ (1 +, 0 *, 0 ~, 0 -)

```
+ onForegroundImportanceChanged(boolean): void  [API 36+]
```

## SdkSandboxController — declaration; API 34+ (0 +, 0 *, 0 ~, 9 -)

```
- getAppOwnedSdkSandboxInterfaces(): List  [API 35+]
- getClientPackageName(): String  [API 35+]
- getClientSharedPreferences(): SharedPreferences  [API 34+]
- getSandboxedSdks(): List  [API 34+]
- loadSdk(String, Bundle, Executor, OutcomeReceiver): void  [API 35+]
- registerSdkSandboxActivityHandler(SdkSandboxActivityHandler): IBinder  [API 34+]
- registerSdkSandboxClientImportanceListener(Executor, SdkSandboxClientImportanceListener): void  [API 36+]
- unregisterSdkSandboxActivityHandler(SdkSandboxActivityHandler): void  [API 34+]
- unregisterSdkSandboxClientImportanceListener(SdkSandboxClientImportanceListener): void  [API 36+]
```

