# android.bluetooth.le

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AdvertiseCallback — declaration; API 21+ (3 +, 0 *, 0 ~, 0 -)

```
+ AdvertiseCallback()  [API 21+]
+ onStartFailure(int): void  [API 21+]
+ onStartSuccess(AdvertiseSettings): void  [API 21+]
```

## AdvertiseData — declaration; API 21+ (7 +, 5 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 21+]
+ describeContents(): int  [API 21+]
+ equals(Object): boolean  [API 21+]
* getIncludeDeviceName(): boolean  [API 21+]
* getIncludeTxPowerLevel(): boolean  [API 21+]
+ getManufacturerSpecificData(): SparseArray  [API 21+]
+ getServiceData(): Map  [API 21+]
* getServiceSolicitationUuids(): List  [API 31+]
* getServiceUuids(): List  [API 21+]
* getTransportDiscoveryData(): List  [API 33+]
+ hashCode(): int  [API 21+]
+ toString(): String  [API 21+]
+ writeToParcel(Parcel, int): void  [API 21+]
```

## AdvertiseData.Builder — declaration; API 21+ (2 +, 7 *, 0 ~, 0 -)

```
* Builder()  [API 21+]
+ addManufacturerData(int, byte[]): AdvertiseData.Builder  [API 21+]
+ addServiceData(ParcelUuid, byte[]): AdvertiseData.Builder  [API 21+]
* addServiceSolicitationUuid(ParcelUuid): AdvertiseData.Builder  [API 31+]
* addServiceUuid(ParcelUuid): AdvertiseData.Builder  [API 21+]
* addTransportDiscoveryData(TransportDiscoveryData): AdvertiseData.Builder  [API 33+]
* build(): AdvertiseData  [API 21+]
* setIncludeDeviceName(boolean): AdvertiseData.Builder  [API 21+]
* setIncludeTxPowerLevel(boolean): AdvertiseData.Builder  [API 21+]
```

## AdvertiseSettings — declaration; API 21+ (4 +, 4 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 21+]
+ describeContents(): int  [API 21+]
+ getMode(): int  [API 21+]
* getTimeout(): int  [API 21+]
* getTxPowerLevel(): int  [API 21+]
* isConnectable(): boolean  [API 21+]
* isDiscoverable(): boolean  [API 34+]
+ toString(): String  [API 21+]
+ writeToParcel(Parcel, int): void  [API 21+]
```

## AdvertiseSettings.Builder — declaration; API 21+ (1 +, 6 *, 0 ~, 0 -)

```
* Builder()  [API 21+]
* build(): AdvertiseSettings  [API 21+]
+ setAdvertiseMode(int): AdvertiseSettings.Builder  [API 21+]
* setConnectable(boolean): AdvertiseSettings.Builder  [API 21+]
* setDiscoverable(boolean): AdvertiseSettings.Builder  [API 34+]
* setTimeout(int): AdvertiseSettings.Builder  [API 21+]
* setTxPowerLevel(int): AdvertiseSettings.Builder  [API 21+]
```

## AdvertisingSet — declaration; API 26+ (7 +, 0 *, 0 ~, 0 -)

```
+ enableAdvertising(boolean, int, int): void  [API 26+]
+ setAdvertisingData(AdvertiseData): void  [API 26+]
+ setAdvertisingParameters(AdvertisingSetParameters): void  [API 26+]
+ setPeriodicAdvertisingData(AdvertiseData): void  [API 26+]
+ setPeriodicAdvertisingEnabled(boolean): void  [API 26+]
+ setPeriodicAdvertisingParameters(PeriodicAdvertisingParameters): void  [API 26+]
+ setScanResponseData(AdvertiseData): void  [API 26+]
```

## AdvertisingSetCallback — declaration; API 26+ (10 +, 0 *, 0 ~, 0 -)

```
+ AdvertisingSetCallback()  [API 26+]
+ onAdvertisingDataSet(AdvertisingSet, int): void  [API 26+]
+ onAdvertisingEnabled(AdvertisingSet, boolean, int): void  [API 26+]
+ onAdvertisingParametersUpdated(AdvertisingSet, int, int): void  [API 26+]
+ onAdvertisingSetStarted(AdvertisingSet, int, int): void  [API 26+]
+ onAdvertisingSetStopped(AdvertisingSet): void  [API 26+]
+ onPeriodicAdvertisingDataSet(AdvertisingSet, int): void  [API 26+]
+ onPeriodicAdvertisingEnabled(AdvertisingSet, boolean, int): void  [API 26+]
+ onPeriodicAdvertisingParametersUpdated(AdvertisingSet, int): void  [API 26+]
+ onScanResponseDataSet(AdvertisingSet, int): void  [API 26+]
```

## AdvertisingSetParameters — declaration; API 26+ (5 +, 8 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 26+]
+ describeContents(): int  [API 26+]
* getInterval(): int  [API 26+]
* getPrimaryPhy(): int  [API 26+]
* getSecondaryPhy(): int  [API 26+]
* getTxPowerLevel(): int  [API 26+]
+ includeTxPower(): boolean  [API 26+]
* isAnonymous(): boolean  [API 26+]
* isConnectable(): boolean  [API 26+]
* isDiscoverable(): boolean  [API 34+]
+ isLegacy(): boolean  [API 26+]
* isScannable(): boolean  [API 26+]
+ toString(): String  [API 26+]
+ writeToParcel(Parcel, int): void  [API 26+]
```

## AdvertisingSetParameters.Builder — declaration; API 26+ (2 +, 10 *, 0 ~, 0 -)

```
* Builder()  [API 26+]
* build(): AdvertisingSetParameters  [API 26+]
* setAnonymous(boolean): AdvertisingSetParameters.Builder  [API 26+]
* setConnectable(boolean): AdvertisingSetParameters.Builder  [API 26+]
* setDiscoverable(boolean): AdvertisingSetParameters.Builder  [API 34+]
+ setIncludeTxPower(boolean): AdvertisingSetParameters.Builder  [API 26+]
* setInterval(int): AdvertisingSetParameters.Builder  [API 26+]
+ setLegacyMode(boolean): AdvertisingSetParameters.Builder  [API 26+]
* setPrimaryPhy(int): AdvertisingSetParameters.Builder  [API 26+]
* setScannable(boolean): AdvertisingSetParameters.Builder  [API 26+]
* setSecondaryPhy(int): AdvertisingSetParameters.Builder  [API 26+]
* setTxPowerLevel(int): AdvertisingSetParameters.Builder  [API 26+]
```

## BluetoothLeAdvertiser — declaration; API 21+ (8 +, 0 *, 0 ~, 0 -)

```
+ startAdvertising(AdvertiseSettings, AdvertiseData, AdvertiseCallback): void  [API 21+]
+ startAdvertising(AdvertiseSettings, AdvertiseData, AdvertiseData, AdvertiseCallback): void  [API 21+]
+ startAdvertisingSet(AdvertisingSetParameters, AdvertiseData, AdvertiseData, PeriodicAdvertisingParameters, AdvertiseData, AdvertisingSetCallback): void  [API 26+]
+ startAdvertisingSet(AdvertisingSetParameters, AdvertiseData, AdvertiseData, PeriodicAdvertisingParameters, AdvertiseData, AdvertisingSetCallback, Handler): void  [API 26+]
+ startAdvertisingSet(AdvertisingSetParameters, AdvertiseData, AdvertiseData, PeriodicAdvertisingParameters, AdvertiseData, int, int, AdvertisingSetCallback): void  [API 26+]
+ startAdvertisingSet(AdvertisingSetParameters, AdvertiseData, AdvertiseData, PeriodicAdvertisingParameters, AdvertiseData, int, int, AdvertisingSetCallback, Handler): void  [API 26+]
+ stopAdvertising(AdvertiseCallback): void  [API 21+]
+ stopAdvertisingSet(AdvertisingSetCallback): void  [API 26+]
```

## BluetoothLeScanner — declaration; API 21+ (6 +, 0 *, 0 ~, 0 -)

```
+ flushPendingScanResults(ScanCallback): void  [API 21+]
+ startScan(List, ScanSettings, PendingIntent): int  [API 26+]
+ startScan(List, ScanSettings, ScanCallback): void  [API 21+]
+ startScan(ScanCallback): void  [API 21+]
+ stopScan(PendingIntent): void  [API 26+]
+ stopScan(ScanCallback): void  [API 21+]
```

## PeriodicAdvertisingParameters — declaration; API 26+ (0 +, 4 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 26+]
* describeContents(): int  [API 26+]
* getIncludeTxPower(): boolean  [API 26+]
* getInterval(): int  [API 26+]
* writeToParcel(Parcel, int): void  [API 26+]
```

## PeriodicAdvertisingParameters.Builder — declaration; API 26+ (0 +, 4 *, 0 ~, 0 -)

```
* Builder()  [API 26+]
* build(): PeriodicAdvertisingParameters  [API 26+]
* setIncludeTxPower(boolean): PeriodicAdvertisingParameters.Builder  [API 26+]
* setInterval(int): PeriodicAdvertisingParameters.Builder  [API 26+]
```

## ScanCallback — declaration; API 21+ (4 +, 0 *, 0 ~, 0 -)

```
+ ScanCallback()  [API 21+]
+ onBatchScanResults(List): void  [API 21+]
+ onScanFailed(int): void  [API 21+]
+ onScanResult(int, ScanResult): void  [API 21+]
```

## ScanFilter — declaration; API 21+ (16 +, 5 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 21+]
+ describeContents(): int  [API 21+]
+ equals(Object): boolean  [API 21+]
+ getAdvertisingData(): byte[]  [API 33+]
+ getAdvertisingDataMask(): byte[]  [API 33+]
* getAdvertisingDataType(): int  [API 33+]
* getDeviceAddress(): String  [API 21+]
* getDeviceName(): String  [API 21+]
+ getManufacturerData(): byte[]  [API 21+]
+ getManufacturerDataMask(): byte[]  [API 21+]
+ getManufacturerId(): int  [API 21+]
+ getServiceData(): byte[]  [API 21+]
+ getServiceDataMask(): byte[]  [API 21+]
+ getServiceDataUuid(): ParcelUuid  [API 21+]
* getServiceSolicitationUuid(): ParcelUuid  [API 29+]
+ getServiceSolicitationUuidMask(): ParcelUuid  [API 29+]
* getServiceUuid(): ParcelUuid  [API 21+]
+ getServiceUuidMask(): ParcelUuid  [API 21+]
+ hashCode(): int  [API 21+]
+ matches(ScanResult): boolean  [API 21+]
+ toString(): String  [API 21+]
+ writeToParcel(Parcel, int): void  [API 21+]
```

## ScanFilter.Builder — declaration; API 21+ (7 +, 7 *, 0 ~, 0 -)

```
* Builder()  [API 21+]
* build(): ScanFilter  [API 21+]
* setAdvertisingDataType(int): ScanFilter.Builder  [API 33+]
+ setAdvertisingDataTypeWithData(int, byte[], byte[]): ScanFilter.Builder  [API 33+]
* setDeviceAddress(String): ScanFilter.Builder  [API 21+]
* setDeviceName(String): ScanFilter.Builder  [API 21+]
+ setManufacturerData(int, byte[]): ScanFilter.Builder  [API 21+]
+ setManufacturerData(int, byte[], byte[]): ScanFilter.Builder  [API 21+]
+ setServiceData(ParcelUuid, byte[]): ScanFilter.Builder  [API 21+]
+ setServiceData(ParcelUuid, byte[], byte[]): ScanFilter.Builder  [API 21+]
* setServiceSolicitationUuid(ParcelUuid): ScanFilter.Builder  [API 29+]
+ setServiceSolicitationUuid(ParcelUuid, ParcelUuid): ScanFilter.Builder  [API 29+]
* setServiceUuid(ParcelUuid): ScanFilter.Builder  [API 21+]
+ setServiceUuid(ParcelUuid, ParcelUuid): ScanFilter.Builder  [API 21+]
```

## ScanRecord — declaration; API 21+ (12 +, 0 *, 0 ~, 0 -)

```
+ getAdvertiseFlags(): int  [API 21+]
+ getAdvertisingDataMap(): Map  [API 33+]
+ getBytes(): byte[]  [API 21+]
+ getDeviceName(): String  [API 21+]
+ getManufacturerSpecificData(): SparseArray  [API 21+]
+ getManufacturerSpecificData(int): byte[]  [API 21+]
+ getServiceData(): Map  [API 21+]
+ getServiceData(ParcelUuid): byte[]  [API 21+]
+ getServiceSolicitationUuids(): List  [API 29+]
+ getServiceUuids(): List  [API 21+]
+ getTxPowerLevel(): int  [API 21+]
+ toString(): String  [API 21+]
```

## ScanResult — declaration; API 21+ (13 +, 5 *, 0 ~, 1 -)

```
- CREATOR [field]  [API 21+]
* ScanResult(BluetoothDevice, ScanRecord, int, long)  [API 21+, deprecated in 26]
- ScanResult(BluetoothDevice, int, int, int, int, int, int, int, ScanRecord, long)  [API 26+]
+ describeContents(): int  [API 21+]
+ equals(Object): boolean  [API 21+]
+ getAdvertisingSid(): int  [API 26+]
+ getDataStatus(): int  [API 26+]
* getDevice(): BluetoothDevice  [API 21+]
+ getPeriodicAdvertisingInterval(): int  [API 26+]
+ getPrimaryPhy(): int  [API 26+]
* getRssi(): int  [API 21+]
* getScanRecord(): ScanRecord  [API 21+]
+ getSecondaryPhy(): int  [API 26+]
* getTimestampNanos(): long  [API 21+]
+ getTxPower(): int  [API 26+]
+ hashCode(): int  [API 21+]
+ isConnectable(): boolean  [API 26+]
+ isLegacy(): boolean  [API 26+]
+ toString(): String  [API 21+]
+ writeToParcel(Parcel, int): void  [API 21+]
```

## ScanSettings — declaration; API 21+ (4 +, 4 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 21+]
+ describeContents(): int  [API 21+]
* getCallbackType(): int  [API 21+]
* getLegacy(): boolean  [API 26+]
* getPhy(): int  [API 26+]
+ getReportDelayMillis(): long  [API 21+]
* getScanMode(): int  [API 21+]
+ getScanResultType(): int  [API 21+]
+ writeToParcel(Parcel, int): void  [API 21+]
```

## ScanSettings.Builder — declaration; API 21+ (3 +, 6 *, 0 ~, 0 -)

```
* Builder()  [API 21+]
* build(): ScanSettings  [API 21+]
* setCallbackType(int): ScanSettings.Builder  [API 23+]
* setLegacy(boolean): ScanSettings.Builder  [API 26+]
+ setMatchMode(int): ScanSettings.Builder  [API 23+]
+ setNumOfMatches(int): ScanSettings.Builder  [API 23+]
* setPhy(int): ScanSettings.Builder  [API 26+]
+ setReportDelay(long): ScanSettings.Builder  [API 21+]
* setScanMode(int): ScanSettings.Builder  [API 21+]
```

## TransportBlock — declaration; API 33+ (2 +, 9 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 33+]
* TransportBlock(int, int, int, byte[])  [API 33+]
* describeContents(): int  [API 33+]
* equals(Object): boolean  [API 33+]
* getOrgId(): int  [API 33+]
* getTdsFlags(): int  [API 33+]
* getTransportData(): byte[]  [API 33+]
* getTransportDataLength(): int  [API 33+]
* hashCode(): int  [API 33+]
+ toByteArray(): byte[]  [API 33+]
+ totalBytes(): int  [API 33+]
* writeToParcel(Parcel, int): void  [API 33+]
```

## TransportDiscoveryData — declaration; API 33+ (2 +, 8 *, 0 ~, 1 -)

```
+ CREATOR [field]  [API 33+]
- TransportDiscoveryData(byte[])  [API 33+]
* TransportDiscoveryData(int, List)  [API 33+]
* describeContents(): int  [API 33+]
* equals(Object): boolean  [API 33+]
* getTransportBlocks(): List  [API 33+]
* getTransportDataType(): int  [API 33+]
* hashCode(): int  [API 33+]
+ toByteArray(): byte[]  [API 33+]
* toString(): String  [API 33+]
+ totalBytes(): int  [API 33+]
* writeToParcel(Parcel, int): void  [API 33+]
```

