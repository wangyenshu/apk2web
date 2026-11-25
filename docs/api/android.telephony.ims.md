# android.telephony.ims

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ImsException — declaration; API 30+ (1 +, 0 *, 0 ~, 0 -)

```
+ getCode(): int  [API 30+]
```

## ImsManager — declaration; API 30+ (3 +, 0 *, 0 ~, 0 -)

```
+ getImsMmTelManager(int): ImsMmTelManager  [API 30+]
+ getImsRcsManager(int): ImsRcsManager  [API 30+]
+ getProvisioningManager(int): ProvisioningManager  [API 33+]
```

## ImsMmTelManager — declaration; API 30+ (15 +, 0 *, 0 ~, 0 -)

```
+ getRegistrationState(Executor, Consumer): void  [API 30+]
+ getRegistrationTransportType(Executor, Consumer): void  [API 30+]
+ getVoWiFiModeSetting(): int  [API 30+]
+ isAdvancedCallingSettingEnabled(): boolean  [API 30+]
+ isCrossSimCallingEnabled(): boolean  [API 31+]
+ isTtyOverVolteEnabled(): boolean  [API 30+]
+ isVoWiFiRoamingSettingEnabled(): boolean  [API 30+]
+ isVoWiFiSettingEnabled(): boolean  [API 30+]
+ isVtSettingEnabled(): boolean  [API 30+]
+ registerImsRegistrationCallback(Executor, RegistrationManager.RegistrationCallback): void  [API 30+]
+ registerImsStateCallback(Executor, ImsStateCallback): void  [API 33+]
+ registerMmTelCapabilityCallback(Executor, ImsMmTelManager.CapabilityCallback): void  [API 30+]
+ unregisterImsRegistrationCallback(RegistrationManager.RegistrationCallback): void  [API 30+]
+ unregisterImsStateCallback(ImsStateCallback): void  [API 33+]
+ unregisterMmTelCapabilityCallback(ImsMmTelManager.CapabilityCallback): void  [API 30+]
```

## ImsMmTelManager.CapabilityCallback — declaration; API 30+ (2 +, 0 *, 0 ~, 0 -)

```
+ CapabilityCallback()  [API 30+]
+ onCapabilitiesStatusChanged(MmTelFeature.MmTelCapabilities): void  [API 30+]
```

## ImsRcsManager — declaration; API 30+ (7 +, 0 *, 0 ~, 0 -)

```
+ getRegistrationState(Executor, Consumer): void  [API 31+]
+ getRegistrationTransportType(Executor, Consumer): void  [API 31+]
+ getUceAdapter(): RcsUceAdapter  [API 30+]
+ registerImsRegistrationCallback(Executor, RegistrationManager.RegistrationCallback): void  [API 31+]
+ registerImsStateCallback(Executor, ImsStateCallback): void  [API 33+]
+ unregisterImsRegistrationCallback(RegistrationManager.RegistrationCallback): void  [API 31+]
+ unregisterImsStateCallback(ImsStateCallback): void  [API 33+]
```

## ImsReasonInfo — declaration; API 30+ (0 +, 7 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 30+]
* ImsReasonInfo(int, int, String)  [API 30+]
* describeContents(): int  [API 30+]
* getCode(): int  [API 30+]
* getExtraCode(): int  [API 30+]
* getExtraMessage(): String  [API 30+]
* toString(): String  [API 30+]
* writeToParcel(Parcel, int): void  [API 30+]
```

## ImsRegistrationAttributes — declaration; API 31+ (9 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 31+]
+ describeContents(): int  [API 31+]
+ equals(Object): boolean  [API 31+]
+ getAttributeFlags(): int  [API 31+]
+ getFeatureTags(): Set  [API 31+]
+ getSipDetails(): SipDetails  [API 34+]
+ getTransportType(): int  [API 31+]
+ hashCode(): int  [API 31+]
+ toString(): String  [API 31+]
+ writeToParcel(Parcel, int): void  [API 31+]
```

## ImsStateCallback — declaration; API 33+ (4 +, 0 *, 0 ~, 0 -)

```
+ ImsStateCallback()  [API 33+]
+ onAvailable(): void  [API 33+]
+ onError(): void  [API 33+]
+ onUnavailable(int): void  [API 33+]
```

## ProvisioningManager — declaration; API 33+ (8 +, 0 *, 0 ~, 0 -)

```
+ getProvisioningStatusForCapability(int, int): boolean  [API 33+]
+ getRcsProvisioningStatusForCapability(int, int): boolean  [API 33+]
+ isProvisioningRequiredForCapability(int, int): boolean  [API 33+]
+ isRcsProvisioningRequiredForCapability(int, int): boolean  [API 33+]
+ registerFeatureProvisioningChangedCallback(Executor, ProvisioningManager.FeatureProvisioningCallback): void  [API 33+]
+ setProvisioningStatusForCapability(int, int, boolean): void  [API 33+]
+ setRcsProvisioningStatusForCapability(int, int, boolean): void  [API 33+]
+ unregisterFeatureProvisioningChangedCallback(ProvisioningManager.FeatureProvisioningCallback): void  [API 33+]
```

## ProvisioningManager.FeatureProvisioningCallback — declaration; API 33+ (3 +, 0 *, 0 ~, 0 -)

```
+ FeatureProvisioningCallback()  [API 33+]
+ onFeatureProvisioningChanged(int, int, boolean): void  [API 33+]
+ onRcsFeatureProvisioningChanged(int, int, boolean): void  [API 33+]
```

## RcsUceAdapter — declaration; API 30+ (1 +, 0 *, 0 ~, 0 -)

```
+ isUceSettingEnabled(): boolean  [API 30+]
```

## RegistrationManager — declaration; API 30+ (4 +, 0 *, 0 ~, 0 -)

```
+ getRegistrationState(Executor, Consumer): void  [API 30+]
+ getRegistrationTransportType(Executor, Consumer): void  [API 30+]
+ registerImsRegistrationCallback(Executor, RegistrationManager.RegistrationCallback): void  [API 30+]
+ unregisterImsRegistrationCallback(RegistrationManager.RegistrationCallback): void  [API 30+]
```

## RegistrationManager.RegistrationCallback — declaration; API 30+ (7 +, 0 *, 0 ~, 0 -)

```
+ RegistrationCallback()  [API 30+]
+ onRegistered(ImsRegistrationAttributes): void  [API 31+]
+ onRegistered(int): void  [API 30+, deprecated in 31]
+ onRegistering(ImsRegistrationAttributes): void  [API 31+]
+ onRegistering(int): void  [API 30+, deprecated in 31]
+ onTechnologyChangeFailed(int, ImsReasonInfo): void  [API 30+]
+ onUnregistered(ImsReasonInfo): void  [API 30+]
```

## SipDetails — declaration; API 34+ (12 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 34+]
+ describeContents(): int  [API 34+]
+ equals(Object): boolean  [API 34+]
+ getCSeq(): int  [API 34+]
+ getCallId(): String  [API 34+]
+ getMethod(): int  [API 34+]
+ getReasonHeaderCause(): int  [API 34+]
+ getReasonHeaderText(): String  [API 34+]
+ getResponseCode(): int  [API 34+]
+ getResponsePhrase(): String  [API 34+]
+ hashCode(): int  [API 34+]
+ toString(): String  [API 34+]
+ writeToParcel(Parcel, int): void  [API 34+]
```

