# android.companion.virtual

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## VirtualDevice — declaration; API 34+ (9 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 34+]
+ describeContents(): int  [API 34+]
+ getDeviceId(): int  [API 34+]
+ getDisplayIds(): int[]  [API 35+]
+ getDisplayName(): CharSequence  [API 35+]
+ getName(): String  [API 34+]
+ getPersistentDeviceId(): String  [API 35+]
+ hasCustomSensorSupport(): boolean  [API 35+]
+ toString(): String  [API 34+]
+ writeToParcel(Parcel, int): void  [API 34+]
```

## VirtualDeviceManager — framework; API 34+ (4 +, 0 *, 0 ~, 0 -)

```
+ getVirtualDevice(int): VirtualDevice  [API 35+]
+ getVirtualDevices(): List  [API 34+]
+ registerVirtualDeviceListener(Executor, VirtualDeviceManager.VirtualDeviceListener): void  [API 35+]
+ unregisterVirtualDeviceListener(VirtualDeviceManager.VirtualDeviceListener): void  [API 35+]
```

## VirtualDeviceManager.VirtualDeviceListener — framework; API 35+ (2 +, 0 *, 0 ~, 0 -)

```
+ onVirtualDeviceClosed(int): void  [API 35+]
+ onVirtualDeviceCreated(int): void  [API 35+]
```

