# android.nfc.cardemulation

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## CardEmulation — declaration; API 19+ (21 +, 0 *, 0 ~, 0 -)

```
+ categoryAllowsForegroundPreference(String): boolean  [API 21+]
+ getAidsForPreferredPaymentService(): List  [API 30+]
+ getAidsForService(ComponentName, String): List  [API 21+]
+ getDescriptionForPreferredPaymentService(): CharSequence  [API 30+]
+ getInstance(NfcAdapter): CardEmulation [static]  [API 19+]
+ getRouteDestinationForPreferredPaymentService(): String  [API 30+]
+ getSelectionModeForCategory(String): int  [API 19+]
+ isDefaultServiceForAid(ComponentName, String): boolean  [API 19+]
+ isDefaultServiceForCategory(ComponentName, String): boolean  [API 19+]
+ registerAidsForService(ComponentName, String, List): boolean  [API 21+]
+ registerPollingLoopFilterForService(ComponentName, String, boolean): boolean  [API 35+]
+ registerPollingLoopPatternFilterForService(ComponentName, String, boolean): boolean  [API 35+]
+ removeAidsForService(ComponentName, String): boolean  [API 21+]
+ removePollingLoopFilterForService(ComponentName, String): boolean  [API 35+]
+ removePollingLoopPatternFilterForService(ComponentName, String): boolean  [API 35+]
+ setOffHostForService(ComponentName, String): boolean  [API 29+]
+ setPreferredService(Activity, ComponentName): boolean  [API 21+]
+ setShouldDefaultToObserveModeForService(ComponentName, boolean): boolean  [API 35+]
+ supportsAidPrefixRegistration(): boolean  [API 21+]
+ unsetOffHostForService(ComponentName): boolean  [API 29+]
+ unsetPreferredService(Activity): boolean  [API 21+]
```

## HostApduService — declaration; API 19+ (6 +, 0 *, 0 ~, 1 -)

```
- HostApduService()  [API 19+]
+ notifyUnhandled(): void  [API 19+]
+ onBind(Intent): IBinder  [API 19+]
+ onDeactivated(int): void  [API 19+]
+ processCommandApdu(byte[], Bundle): byte[]  [API 19+]
+ processPollingFrames(List): void  [API 35+]
+ sendResponseApdu(byte[]): void  [API 19+]
```

## HostNfcFService — declaration; API 24+ (4 +, 0 *, 0 ~, 1 -)

```
- HostNfcFService()  [API 24+]
+ onBind(Intent): IBinder  [API 24+]
+ onDeactivated(int): void  [API 24+]
+ processNfcFPacket(byte[], Bundle): byte[]  [API 24+]
+ sendResponsePacket(byte[]): void  [API 24+]
```

## NfcFCardEmulation — declaration; API 24+ (8 +, 0 *, 0 ~, 0 -)

```
+ disableService(Activity): boolean  [API 24+]
+ enableService(Activity, ComponentName): boolean  [API 24+]
+ getInstance(NfcAdapter): NfcFCardEmulation [static]  [API 24+]
+ getNfcid2ForService(ComponentName): String  [API 24+]
+ getSystemCodeForService(ComponentName): String  [API 24+]
+ registerSystemCodeForService(ComponentName, String): boolean  [API 24+]
+ setNfcid2ForService(ComponentName, String): boolean  [API 24+]
+ unregisterSystemCodeForService(ComponentName): boolean  [API 24+]
```

## OffHostApduService — declaration; API 19+ (2 +, 0 *, 0 ~, 0 -)

```
+ OffHostApduService()  [API 19+]
+ onBind(Intent): IBinder  [API 19+]
```

## PollingFrame — declaration; API 35+ (8 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 35+]
+ describeContents(): int  [API 35+]
+ getData(): byte[]  [API 35+]
+ getTimestamp(): long  [API 35+]
+ getTriggeredAutoTransact(): boolean  [API 35+]
+ getType(): int  [API 35+]
+ getVendorSpecificGain(): int  [API 35+]
+ toString(): String  [API 35+]
+ writeToParcel(Parcel, int): void  [API 35+]
```

