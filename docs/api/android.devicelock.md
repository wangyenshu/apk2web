# android.devicelock

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## DeviceId — declaration; API 34+ (0 +, 0 *, 0 ~, 2 -)

```
- getId(): String  [API 34+]
- getType(): int  [API 34+]
```

## DeviceLockManager — framework; API 34+ (0 +, 0 *, 0 ~, 5 -)

```
- getDeviceId(Executor, OutcomeReceiver): void  [API 34+]
- getKioskApps(Executor, OutcomeReceiver): void  [API 34+]
- isDeviceLocked(Executor, OutcomeReceiver): void  [API 34+]
- lockDevice(Executor, OutcomeReceiver): void  [API 34+]
- unlockDevice(Executor, OutcomeReceiver): void  [API 34+]
```

