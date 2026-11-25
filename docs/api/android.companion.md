# android.companion

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AssociatedDevice — declaration; API 34+ (8 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 34+]
+ describeContents(): int  [API 34+]
+ equals(Object): boolean  [API 34+]
+ getBleDevice(): ScanResult  [API 34+]
+ getBluetoothDevice(): BluetoothDevice  [API 34+]
+ getWifiDevice(): ScanResult  [API 34+]
+ hashCode(): int  [API 34+]
+ toString(): String  [API 34+]
+ writeToParcel(Parcel, int): void  [API 34+]
```

## AssociationInfo — declaration; API 33+ (12 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 33+]
+ describeContents(): int  [API 33+]
+ equals(Object): boolean  [API 33+]
+ getAssociatedDevice(): AssociatedDevice  [API 34+]
+ getDeviceMacAddress(): MacAddress  [API 33+]
+ getDeviceProfile(): String  [API 33+]
+ getDisplayName(): CharSequence  [API 33+]
+ getId(): int  [API 33+]
+ getSystemDataSyncFlags(): int  [API 34+]
+ hashCode(): int  [API 33+]
+ isSelfManaged(): boolean  [API 35+]
+ toString(): String  [API 33+]
+ writeToParcel(Parcel, int): void  [API 33+]
```

## AssociationRequest — declaration; API 26+ (0 +, 10 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 26+]
* describeContents(): int  [API 26+]
* equals(Object): boolean  [API 26+]
* getDeviceProfile(): String  [API 33+]
* getDisplayName(): CharSequence  [API 33+]
* hashCode(): int  [API 26+]
* isForceConfirmation(): boolean  [API 33+]
* isSelfManaged(): boolean  [API 33+]
* isSingleDevice(): boolean  [API 33+]
* toString(): String  [API 26+]
* writeToParcel(Parcel, int): void  [API 26+]
```

## AssociationRequest.Builder — declaration; API 26+ (3 +, 7 *, 0 ~, 0 -)

```
* Builder()  [API 26+]
+ addDeviceFilter(DeviceFilter): AssociationRequest.Builder  [API 26+]
* build(): AssociationRequest  [API 26+]
+ checkNotUsed(): void  [API 28-28, removed in 29]
+ markUsed(): void  [API 28-28, removed in 29]
* setDeviceProfile(String): AssociationRequest.Builder  [API 31+]
* setDisplayName(CharSequence): AssociationRequest.Builder  [API 33+]
* setForceConfirmation(boolean): AssociationRequest.Builder  [API 33+]
* setSelfManaged(boolean): AssociationRequest.Builder  [API 33+]
* setSingleDevice(boolean): AssociationRequest.Builder  [API 26+]
```

## BluetoothDeviceFilter — declaration; API 26+ (5 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 26+]
+ describeContents(): int  [API 26+]
+ equals(Object): boolean  [API 26+]
+ hashCode(): int  [API 26+]
+ toString(): String  [API 26+]
+ writeToParcel(Parcel, int): void  [API 26+]
```

## BluetoothDeviceFilter.Builder — declaration; API 26+ (6 +, 0 *, 0 ~, 1 -)

```
- Builder()  [API 26+]
+ addServiceUuid(ParcelUuid, ParcelUuid): BluetoothDeviceFilter.Builder  [API 26+]
+ build(): BluetoothDeviceFilter  [API 26+]
+ checkNotUsed(): void  [API 28-28, removed in 29]
+ markUsed(): void  [API 28-28, removed in 29]
+ setAddress(String): BluetoothDeviceFilter.Builder  [API 26+]
+ setNamePattern(Pattern): BluetoothDeviceFilter.Builder  [API 26+]
```

## BluetoothLeDeviceFilter — declaration; API 26+ (6 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 26+]
+ describeContents(): int  [API 26+]
+ equals(Object): boolean  [API 26+]
+ getRenamePrefixLengthLimit(): int [static]  [API 26+]
+ hashCode(): int  [API 26+]
+ toString(): String  [API 26+]
+ writeToParcel(Parcel, int): void  [API 26+]
```

## BluetoothLeDeviceFilter.Builder — declaration; API 26+ (8 +, 0 *, 0 ~, 1 -)

```
- Builder()  [API 26+]
+ build(): BluetoothLeDeviceFilter  [API 26+]
+ checkNotUsed(): void  [API 28-28, removed in 29]
+ markUsed(): void  [API 28-28, removed in 29]
+ setNamePattern(Pattern): BluetoothLeDeviceFilter.Builder  [API 26+]
+ setRawDataFilter(byte[], byte[]): BluetoothLeDeviceFilter.Builder  [API 26+]
+ setRenameFromBytes(String, String, int, int, ByteOrder): BluetoothLeDeviceFilter.Builder  [API 26+]
+ setRenameFromName(String, String, int, int): BluetoothLeDeviceFilter.Builder  [API 26+]
+ setScanFilter(ScanFilter): BluetoothLeDeviceFilter.Builder  [API 26+]
```

## CompanionDeviceManager — framework; API 26+ (18 +, 0 *, 0 ~, 0 -)

```
+ associate(AssociationRequest, CompanionDeviceManager.Callback, Handler): void  [API 26+]
+ associate(AssociationRequest, Executor, CompanionDeviceManager.Callback): void  [API 33+]
+ attachSystemDataTransport(int, InputStream, OutputStream): void  [API 34+]
+ buildAssociationCancellationIntent(): IntentSender  [API 34+]
+ buildPermissionTransferUserConsentIntent(int): IntentSender  [API 34+]
+ detachSystemDataTransport(int): void  [API 34+]
+ disableSystemDataSyncForTypes(int, int): void  [API 34+]
+ disassociate(String): void  [API 26+, deprecated in 33]
+ disassociate(int): void  [API 33+]
+ enableSystemDataSyncForTypes(int, int): void  [API 34+]
+ getAssociations(): List  [API 26+, deprecated in 33]
+ getMyAssociations(): List  [API 33+]
+ hasNotificationAccess(ComponentName): boolean  [API 26+, deprecated in 33]
+ isPermissionTransferUserConsented(int): boolean  [API 35+]
+ requestNotificationAccess(ComponentName): void  [API 26+]
+ startObservingDevicePresence(String): void  [API 31+]
+ startSystemDataTransfer(int, Executor, OutcomeReceiver): void  [API 34+]
+ stopObservingDevicePresence(String): void  [API 31+]
```

## CompanionDeviceManager.Callback — framework; API 26+ (5 +, 0 *, 0 ~, 0 -)

```
+ Callback()  [API 26+]
+ onAssociationCreated(AssociationInfo): void  [API 33+]
+ onAssociationPending(IntentSender): void  [API 33+]
+ onDeviceFound(IntentSender): void  [API 26+, deprecated in 33]
+ onFailure(CharSequence): void  [API 26+]
```

## CompanionDeviceService — declaration; API 31+ (7 +, 0 *, 0 ~, 1 -)

```
- CompanionDeviceService()  [API 31+]
+ attachSystemDataTransport(int, InputStream, OutputStream): void  [API 34+]
+ detachSystemDataTransport(int): void  [API 34+]
+ onBind(Intent): IBinder  [API 31+]
+ onDeviceAppeared(AssociationInfo): void  [API 33+]
+ onDeviceAppeared(String): void  [API 31+, deprecated in 33]
+ onDeviceDisappeared(AssociationInfo): void  [API 33+]
+ onDeviceDisappeared(String): void  [API 31+, deprecated in 33]
```

## CompanionException — declaration; API 34+ (0 +, 0 *, 0 ~, 0 -)

## DeviceFilter — declaration; API 26+ (0 +, 0 *, 0 ~, 0 -)

## DeviceNotAssociatedException — declaration; API 31+ (0 +, 0 *, 0 ~, 0 -)

## WifiDeviceFilter — declaration; API 26+ (4 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 26+]
+ describeContents(): int  [API 26+]
+ equals(Object): boolean  [API 26+]
+ hashCode(): int  [API 26+]
+ writeToParcel(Parcel, int): void  [API 26+]
```

## WifiDeviceFilter.Builder — declaration; API 26+ (6 +, 0 *, 0 ~, 1 -)

```
- Builder()  [API 26+]
+ build(): WifiDeviceFilter  [API 26+]
+ checkNotUsed(): void  [API 28-28, removed in 29]
+ markUsed(): void  [API 28-28, removed in 29]
+ setBssid(MacAddress): WifiDeviceFilter.Builder  [API 30+]
+ setBssidMask(MacAddress): WifiDeviceFilter.Builder  [API 30+]
+ setNamePattern(Pattern): WifiDeviceFilter.Builder  [API 26+]
```

