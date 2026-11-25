# android.bluetooth

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## BluetoothA2dp — declaration; API 11+ (6 +, 0 *, 0 ~, 0 -)

```
+ finalize(): void  [API 11+]
+ getConnectedDevices(): List  [API 11+]
+ getConnectionState(BluetoothDevice): int  [API 11+]
+ getDevicesMatchingConnectionStates(int[]): List  [API 11+]
+ getSupportedCodecTypes(): Collection  [API 35+]
+ isA2dpPlaying(BluetoothDevice): boolean  [API 11+]
```

## BluetoothAdapter — declaration; API 5+ (43 +, 0 *, 0 ~, 0 -)

```
+ cancelDiscovery(): boolean  [API 5+]
+ checkBluetoothAddress(String): boolean [static]  [API 5+]
+ closeProfileProxy(int, BluetoothProfile): void  [API 11+]
+ disable(): boolean  [API 5+, deprecated in 33]
+ enable(): boolean  [API 5+, deprecated in 33]
+ finalize(): void  [API 5+]
+ getAddress(): String  [API 5+]
+ getBluetoothLeAdvertiser(): BluetoothLeAdvertiser  [API 21+]
+ getBluetoothLeScanner(): BluetoothLeScanner  [API 21+]
+ getBondedDevices(): Set  [API 5+]
+ getDefaultAdapter(): BluetoothAdapter [static]  [API 5+, deprecated in 31]
+ getDiscoverableTimeout(): Duration  [API 33+]
+ getLeMaximumAdvertisingDataLength(): int  [API 26+]
+ getMaxConnectedAudioDevices(): int  [API 33+]
+ getName(): String  [API 5+]
+ getProfileConnectionState(int): int  [API 14+]
+ getProfileProxy(Context, BluetoothProfile.ServiceListener, int): boolean  [API 11+]
+ getRemoteDevice(String): BluetoothDevice  [API 5+]
+ getRemoteDevice(byte[]): BluetoothDevice  [API 16+]
+ getRemoteLeDevice(String, int): BluetoothDevice  [API 33+]
+ getScanMode(): int  [API 5+]
+ getState(): int  [API 5+]
+ isDiscovering(): boolean  [API 5+]
+ isEnabled(): boolean  [API 5+]
+ isLe2MPhySupported(): boolean  [API 26+]
+ isLeAudioBroadcastAssistantSupported(): int  [API 33+]
+ isLeAudioBroadcastSourceSupported(): int  [API 33+]
+ isLeAudioSupported(): int  [API 33+]
+ isLeCodedPhySupported(): boolean  [API 26+]
+ isLeExtendedAdvertisingSupported(): boolean  [API 26+]
+ isLePeriodicAdvertisingSupported(): boolean  [API 26+]
+ isMultipleAdvertisementSupported(): boolean  [API 21+]
+ isOffloadedFilteringSupported(): boolean  [API 21+]
+ isOffloadedScanBatchingSupported(): boolean  [API 21+]
+ listenUsingInsecureL2capChannel(): BluetoothServerSocket  [API 29+]
+ listenUsingInsecureRfcommWithServiceRecord(String, UUID): BluetoothServerSocket  [API 10+]
+ listenUsingL2capChannel(): BluetoothServerSocket  [API 29+]
+ listenUsingRfcommWithServiceRecord(String, UUID): BluetoothServerSocket  [API 5+]
+ setName(String): boolean  [API 5+]
+ startDiscovery(): boolean  [API 5+]
+ startLeScan(BluetoothAdapter.LeScanCallback): boolean  [API 18+, deprecated in 21]
+ startLeScan(UUID[], BluetoothAdapter.LeScanCallback): boolean  [API 18+, deprecated in 21]
+ stopLeScan(BluetoothAdapter.LeScanCallback): void  [API 18+, deprecated in 21]
```

## BluetoothAdapter.LeScanCallback — declaration; API 18+ (1 +, 0 *, 0 ~, 0 -)

```
+ onLeScan(BluetoothDevice, int, byte[]): void  [API 18+]
```

## BluetoothAssignedNumbers — declaration; API 11+ (0 +, 0 *, 0 ~, 0 -)

## BluetoothClass — declaration; API 5+ (9 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 5+]
+ describeContents(): int  [API 5+]
+ doesClassMatch(int): boolean  [API 33+]
+ equals(Object): boolean  [API 5+]
+ getDeviceClass(): int  [API 5+]
+ getMajorDeviceClass(): int  [API 5+]
+ hasService(int): boolean  [API 5+]
+ hashCode(): int  [API 5+]
+ toString(): String  [API 5+]
+ writeToParcel(Parcel, int): void  [API 5+]
```

## BluetoothClass.Device — declaration; API 5+ (1 +, 0 *, 0 ~, 0 -)

```
+ Device()  [API 5+]
```

## BluetoothClass.Device.Major — declaration; API 5+ (1 +, 0 *, 0 ~, 0 -)

```
+ Major()  [API 5+]
```

## BluetoothClass.Service — declaration; API 5+ (1 +, 0 *, 0 ~, 0 -)

```
+ Service()  [API 5+]
```

## BluetoothCodecConfig — declaration; API 33+ (6 +, 10 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 33+]
+ describeContents(): int  [API 33+]
+ equals(Object): boolean  [API 33+]
* getBitsPerSample(): int  [API 33+]
* getChannelMode(): int  [API 33+]
* getCodecPriority(): int  [API 33+]
* getCodecSpecific1(): long  [API 33+]
* getCodecSpecific2(): long  [API 33+]
* getCodecSpecific3(): long  [API 33+]
* getCodecSpecific4(): long  [API 33+]
* getCodecType(): int  [API 33+, deprecated in 35]
* getExtendedCodecType(): BluetoothCodecType  [API 35+]
* getSampleRate(): int  [API 33+]
+ hashCode(): int  [API 33+]
+ isMandatoryCodec(): boolean  [API 33+]
+ toString(): String  [API 33+]
+ writeToParcel(Parcel, int): void  [API 33+]
```

## BluetoothCodecConfig.Builder — declaration; API 33+ (0 +, 12 *, 0 ~, 0 -)

```
* Builder()  [API 33+]
* build(): BluetoothCodecConfig  [API 33+]
* setBitsPerSample(int): BluetoothCodecConfig.Builder  [API 33+]
* setChannelMode(int): BluetoothCodecConfig.Builder  [API 33+]
* setCodecPriority(int): BluetoothCodecConfig.Builder  [API 33+]
* setCodecSpecific1(long): BluetoothCodecConfig.Builder  [API 33+]
* setCodecSpecific2(long): BluetoothCodecConfig.Builder  [API 33+]
* setCodecSpecific3(long): BluetoothCodecConfig.Builder  [API 33+]
* setCodecSpecific4(long): BluetoothCodecConfig.Builder  [API 33+]
* setCodecType(int): BluetoothCodecConfig.Builder  [API 33+, deprecated in 35]
* setExtendedCodecType(BluetoothCodecType): BluetoothCodecConfig.Builder  [API 35+]
* setSampleRate(int): BluetoothCodecConfig.Builder  [API 33+]
```

## BluetoothCodecStatus — declaration; API 33+ (1 +, 8 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 33+]
* describeContents(): int  [API 33+]
* equals(Object): boolean  [API 33+]
* getCodecConfig(): BluetoothCodecConfig  [API 33+]
* getCodecsLocalCapabilities(): List  [API 33+]
* getCodecsSelectableCapabilities(): List  [API 33+]
* hashCode(): int  [API 33+]
+ isCodecConfigSelectable(BluetoothCodecConfig): boolean  [API 33+]
* toString(): String  [API 33+]
* writeToParcel(Parcel, int): void  [API 33+]
```

## BluetoothCodecStatus.Builder — declaration; API 33+ (0 +, 5 *, 0 ~, 0 -)

```
* Builder()  [API 33+]
* build(): BluetoothCodecStatus  [API 33+]
* setCodecConfig(BluetoothCodecConfig): BluetoothCodecStatus.Builder  [API 33+]
* setCodecsLocalCapabilities(List): BluetoothCodecStatus.Builder  [API 33+]
* setCodecsSelectableCapabilities(List): BluetoothCodecStatus.Builder  [API 33+]
```

## BluetoothCodecType — declaration; API 35+ (8 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 35+]
+ describeContents(): int  [API 35+]
+ equals(Object): boolean  [API 35+]
+ getCodecId(): long  [API 35+]
+ getCodecName(): String  [API 35+]
+ hashCode(): int  [API 35+]
+ isMandatoryCodec(): boolean  [API 35+]
+ toString(): String  [API 35+]
+ writeToParcel(Parcel, int): void  [API 35+]
```

## BluetoothCsipSetCoordinator — declaration; API 33+ (5 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 33+]
+ finalize(): void  [API 33+]
+ getConnectedDevices(): List  [API 33+]
+ getConnectionState(BluetoothDevice): int  [API 33+]
+ getDevicesMatchingConnectionStates(int[]): List  [API 33+]
```

## BluetoothDevice — declaration; API 5+ (26 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 5+]
+ connectGatt(Context, boolean, BluetoothGattCallback): BluetoothGatt  [API 18+]
+ connectGatt(Context, boolean, BluetoothGattCallback, int): BluetoothGatt  [API 23+]
+ connectGatt(Context, boolean, BluetoothGattCallback, int, int): BluetoothGatt  [API 26+]
+ connectGatt(Context, boolean, BluetoothGattCallback, int, int, Handler): BluetoothGatt  [API 26+]
+ createBond(): boolean  [API 19+]
+ createInsecureL2capChannel(int): BluetoothSocket  [API 29+]
+ createInsecureRfcommSocketToServiceRecord(UUID): BluetoothSocket  [API 10+]
+ createL2capChannel(int): BluetoothSocket  [API 29+]
+ createRfcommSocketToServiceRecord(UUID): BluetoothSocket  [API 5+]
+ describeContents(): int  [API 5+]
+ equals(Object): boolean  [API 5+]
+ fetchUuidsWithSdp(): boolean  [API 15+]
+ getAddress(): String  [API 5+]
+ getAddressType(): int  [API 35+]
+ getAlias(): String  [API 30+]
+ getBluetoothClass(): BluetoothClass  [API 5+]
+ getBondState(): int  [API 5+]
+ getName(): String  [API 5+]
+ getType(): int  [API 18+]
+ getUuids(): ParcelUuid[]  [API 15+]
+ hashCode(): int  [API 5+]
+ setAlias(String): int  [API 31+]
+ setPairingConfirmation(boolean): boolean  [API 19+]
+ setPin(byte[]): boolean  [API 19+]
+ toString(): String  [API 5+]
+ writeToParcel(Parcel, int): void  [API 5+]
```

## BluetoothGatt — declaration; API 18+ (26 +, 0 *, 0 ~, 0 -)

```
+ abortReliableWrite(): void  [API 19+]
+ abortReliableWrite(BluetoothDevice): void  [API 18+, deprecated in 19]
+ beginReliableWrite(): boolean  [API 18+]
+ close(): void  [API 18+]
+ connect(): boolean  [API 18+]
+ disconnect(): void  [API 18+]
+ discoverServices(): boolean  [API 18+]
+ executeReliableWrite(): boolean  [API 18+]
+ getConnectedDevices(): List  [API 18+]
+ getConnectionState(BluetoothDevice): int  [API 18+]
+ getDevice(): BluetoothDevice  [API 18+]
+ getDevicesMatchingConnectionStates(int[]): List  [API 18+]
+ getService(UUID): BluetoothGattService  [API 18+]
+ getServices(): List  [API 18+]
+ readCharacteristic(BluetoothGattCharacteristic): boolean  [API 18+]
+ readDescriptor(BluetoothGattDescriptor): boolean  [API 18+]
+ readPhy(): void  [API 26+]
+ readRemoteRssi(): boolean  [API 18+]
+ requestConnectionPriority(int): boolean  [API 21+]
+ requestMtu(int): boolean  [API 21+]
+ setCharacteristicNotification(BluetoothGattCharacteristic, boolean): boolean  [API 18+]
+ setPreferredPhy(int, int, int): void  [API 26+]
+ writeCharacteristic(BluetoothGattCharacteristic): boolean  [API 18+, deprecated in 33]
+ writeCharacteristic(BluetoothGattCharacteristic, byte[], int): int  [API 33+]
+ writeDescriptor(BluetoothGattDescriptor): boolean  [API 18+, deprecated in 33]
+ writeDescriptor(BluetoothGattDescriptor, byte[]): int  [API 33+]
```

## BluetoothGattCallback — declaration; API 18+ (17 +, 0 *, 0 ~, 0 -)

```
+ BluetoothGattCallback()  [API 18+]
+ onCharacteristicChanged(BluetoothGatt, BluetoothGattCharacteristic): void  [API 18+, deprecated in 33]
+ onCharacteristicChanged(BluetoothGatt, BluetoothGattCharacteristic, byte[]): void  [API 33+]
+ onCharacteristicRead(BluetoothGatt, BluetoothGattCharacteristic, byte[], int): void  [API 33+]
+ onCharacteristicRead(BluetoothGatt, BluetoothGattCharacteristic, int): void  [API 18+, deprecated in 33]
+ onCharacteristicWrite(BluetoothGatt, BluetoothGattCharacteristic, int): void  [API 18+]
+ onConnectionStateChange(BluetoothGatt, int, int): void  [API 18+]
+ onDescriptorRead(BluetoothGatt, BluetoothGattDescriptor, int): void  [API 18+, deprecated in 33]
+ onDescriptorRead(BluetoothGatt, BluetoothGattDescriptor, int, byte[]): void  [API 33+]
+ onDescriptorWrite(BluetoothGatt, BluetoothGattDescriptor, int): void  [API 18+]
+ onMtuChanged(BluetoothGatt, int, int): void  [API 21+]
+ onPhyRead(BluetoothGatt, int, int, int): void  [API 26+]
+ onPhyUpdate(BluetoothGatt, int, int, int): void  [API 26+]
+ onReadRemoteRssi(BluetoothGatt, int, int): void  [API 18+]
+ onReliableWriteCompleted(BluetoothGatt, int): void  [API 18+]
+ onServiceChanged(BluetoothGatt): void  [API 31+]
+ onServicesDiscovered(BluetoothGatt, int): void  [API 18+]
```

## BluetoothGattCharacteristic — declaration; API 18+ (15 +, 6 *, 0 ~, 0 -)

```
* BluetoothGattCharacteristic(UUID, int, int)  [API 18+]
- CREATOR [field]  [API 24+]
+ addDescriptor(BluetoothGattDescriptor): boolean  [API 18+]
+ describeContents(): int  [API 18+]
+ getDescriptor(UUID): BluetoothGattDescriptor  [API 18+]
+ getDescriptors(): List  [API 18+]
+ getFloatValue(int, int): Float  [API 18+, deprecated in 33]
+ getInstanceId(): int  [API 18+]
+ getIntValue(int, int): Integer  [API 18+, deprecated in 33]
* getPermissions(): int  [API 18+]
* getProperties(): int  [API 18+]
+ getService(): BluetoothGattService  [API 18+]
+ getStringValue(int): String  [API 18+, deprecated in 33]
* getUuid(): UUID  [API 18+]
+ getValue(): byte[]  [API 18+, deprecated in 33]
* getWriteType(): int  [API 18+]
+ mDescriptors [field]  [API 18+]
+ setValue(String): boolean  [API 18+, deprecated in 33]
+ setValue(byte[]): boolean  [API 18+, deprecated in 33]
+ setValue(int, int, int): boolean  [API 18+, deprecated in 33]
+ setValue(int, int, int, int): boolean  [API 18+, deprecated in 33]
* setWriteType(int): void  [API 18+]
+ writeToParcel(Parcel, int): void  [API 18+]
```

## BluetoothGattDescriptor — declaration; API 18+ (5 +, 3 *, 0 ~, 0 -)

```
* BluetoothGattDescriptor(UUID, int)  [API 18+]
- CREATOR [field]  [API 24+]
- DISABLE_NOTIFICATION_VALUE [field]  [API 18+]
- ENABLE_INDICATION_VALUE [field]  [API 18+]
- ENABLE_NOTIFICATION_VALUE [field]  [API 18+]
+ describeContents(): int  [API 18+]
+ getCharacteristic(): BluetoothGattCharacteristic  [API 18+]
* getPermissions(): int  [API 18+]
* getUuid(): UUID  [API 18+]
+ getValue(): byte[]  [API 18+, deprecated in 33]
+ setValue(byte[]): boolean  [API 18+, deprecated in 33]
+ writeToParcel(Parcel, int): void  [API 18+]
```

## BluetoothGattServer — declaration; API 18+ (16 +, 0 *, 0 ~, 0 -)

```
+ addService(BluetoothGattService): boolean  [API 18+]
+ cancelConnection(BluetoothDevice): void  [API 18+]
+ clearServices(): void  [API 18+]
+ close(): void  [API 18+]
+ connect(BluetoothDevice, boolean): boolean  [API 18+]
+ getConnectedDevices(): List  [API 18+]
+ getConnectionState(BluetoothDevice): int  [API 18+]
+ getDevicesMatchingConnectionStates(int[]): List  [API 18+]
+ getService(UUID): BluetoothGattService  [API 18+]
+ getServices(): List  [API 18+]
+ notifyCharacteristicChanged(BluetoothDevice, BluetoothGattCharacteristic, boolean): boolean  [API 18+, deprecated in 33]
+ notifyCharacteristicChanged(BluetoothDevice, BluetoothGattCharacteristic, boolean, byte[]): int  [API 33+]
+ readPhy(BluetoothDevice): void  [API 26+]
+ removeService(BluetoothGattService): boolean  [API 18+]
+ sendResponse(BluetoothDevice, int, int, int, byte[]): boolean  [API 18+]
+ setPreferredPhy(BluetoothDevice, int, int, int): void  [API 26+]
```

## BluetoothGattServerCallback — declaration; API 18+ (12 +, 0 *, 0 ~, 0 -)

```
+ BluetoothGattServerCallback()  [API 18+]
+ onCharacteristicReadRequest(BluetoothDevice, int, int, BluetoothGattCharacteristic): void  [API 18+]
+ onCharacteristicWriteRequest(BluetoothDevice, int, BluetoothGattCharacteristic, boolean, boolean, int, byte[]): void  [API 18+]
+ onConnectionStateChange(BluetoothDevice, int, int): void  [API 18+]
+ onDescriptorReadRequest(BluetoothDevice, int, int, BluetoothGattDescriptor): void  [API 18+]
+ onDescriptorWriteRequest(BluetoothDevice, int, BluetoothGattDescriptor, boolean, boolean, int, byte[]): void  [API 18+]
+ onExecuteWrite(BluetoothDevice, int, boolean): void  [API 18+]
+ onMtuChanged(BluetoothDevice, int): void  [API 22+]
+ onNotificationSent(BluetoothDevice, int): void  [API 21+]
+ onPhyRead(BluetoothDevice, int, int, int): void  [API 26+]
+ onPhyUpdate(BluetoothDevice, int, int, int): void  [API 26+]
+ onServiceAdded(int, BluetoothGattService): void  [API 18+]
```

## BluetoothGattService — declaration; API 18+ (10 +, 0 *, 0 ~, 1 -)

```
- BluetoothGattService(UUID, int)  [API 18+]
- CREATOR [field]  [API 24+]
+ addCharacteristic(BluetoothGattCharacteristic): boolean  [API 18+]
+ addService(BluetoothGattService): boolean  [API 18+]
+ describeContents(): int  [API 18+]
+ getCharacteristic(UUID): BluetoothGattCharacteristic  [API 18+]
+ getCharacteristics(): List  [API 18+]
+ getIncludedServices(): List  [API 18+]
+ getInstanceId(): int  [API 18+]
+ getType(): int  [API 18+]
+ getUuid(): UUID  [API 18+]
+ mCharacteristics [field]  [API 18+]
+ mIncludedServices [field]  [API 18+]
+ writeToParcel(Parcel, int): void  [API 18+]
```

## BluetoothHeadset — declaration; API 11+ (10 +, 0 *, 0 ~, 0 -)

```
+ finalize(): void  [API 11+]
+ getConnectedDevices(): List  [API 11+]
+ getConnectionState(BluetoothDevice): int  [API 11+]
+ getDevicesMatchingConnectionStates(int[]): List  [API 11+]
+ isAudioConnected(BluetoothDevice): boolean  [API 11+]
+ isNoiseReductionSupported(BluetoothDevice): boolean  [API 31+]
+ isVoiceRecognitionSupported(BluetoothDevice): boolean  [API 31+]
+ sendVendorSpecificResultCode(BluetoothDevice, String, String): boolean  [API 19+]
+ startVoiceRecognition(BluetoothDevice): boolean  [API 11+]
+ stopVoiceRecognition(BluetoothDevice): boolean  [API 11+]
```

## BluetoothHealth — declaration; API 14+, deprecated in 29 (8 +, 0 *, 0 ~, 0 -)

```
+ connectChannelToSource(BluetoothDevice, BluetoothHealthAppConfiguration): boolean  [API 14+]
+ disconnectChannel(BluetoothDevice, BluetoothHealthAppConfiguration, int): boolean  [API 14+]
+ getConnectedDevices(): List  [API 14+]
+ getConnectionState(BluetoothDevice): int  [API 14+]
+ getDevicesMatchingConnectionStates(int[]): List  [API 14+]
+ getMainChannelFd(BluetoothDevice, BluetoothHealthAppConfiguration): ParcelFileDescriptor  [API 14+]
+ registerSinkAppConfiguration(String, int, BluetoothHealthCallback): boolean  [API 14+]
+ unregisterAppConfiguration(BluetoothHealthAppConfiguration): boolean  [API 14+]
```

## BluetoothHealthAppConfiguration — declaration; API 14+, deprecated in 29 (5 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 14+]
+ describeContents(): int  [API 14+]
+ getDataType(): int  [API 14+]
+ getName(): String  [API 14+]
+ getRole(): int  [API 14+]
+ writeToParcel(Parcel, int): void  [API 14+]
```

## BluetoothHealthCallback — declaration; API 14+, deprecated in 29 (3 +, 0 *, 0 ~, 0 -)

```
+ BluetoothHealthCallback()  [API 14+]
+ onHealthAppConfigurationStatusChange(BluetoothHealthAppConfiguration, int): void  [API 14+]
+ onHealthChannelStateChange(BluetoothHealthAppConfiguration, BluetoothDevice, int, int, ParcelFileDescriptor, int): void  [API 14+]
```

## BluetoothHearingAid — declaration; API 29+ (3 +, 0 *, 0 ~, 0 -)

```
+ getConnectedDevices(): List  [API 29+]
+ getConnectionState(BluetoothDevice): int  [API 29+]
+ getDevicesMatchingConnectionStates(int[]): List  [API 29+]
```

## BluetoothHidDevice — declaration; API 28+ (10 +, 0 *, 0 ~, 0 -)

```
+ connect(BluetoothDevice): boolean  [API 28+]
+ disconnect(BluetoothDevice): boolean  [API 28+]
+ getConnectedDevices(): List  [API 28+]
+ getConnectionState(BluetoothDevice): int  [API 28+]
+ getDevicesMatchingConnectionStates(int[]): List  [API 28+]
+ registerApp(BluetoothHidDeviceAppSdpSettings, BluetoothHidDeviceAppQosSettings, BluetoothHidDeviceAppQosSettings, Executor, BluetoothHidDevice.Callback): boolean  [API 28+]
+ replyReport(BluetoothDevice, byte, byte, byte[]): boolean  [API 28+]
+ reportError(BluetoothDevice, byte): boolean  [API 28+]
+ sendReport(BluetoothDevice, int, byte[]): boolean  [API 28+]
+ unregisterApp(): boolean  [API 28+]
```

## BluetoothHidDevice.Callback — declaration; API 28+ (8 +, 0 *, 0 ~, 0 -)

```
+ Callback()  [API 28+]
+ onAppStatusChanged(BluetoothDevice, boolean): void  [API 28+]
+ onConnectionStateChanged(BluetoothDevice, int): void  [API 28+]
+ onGetReport(BluetoothDevice, byte, byte, int): void  [API 28+]
+ onInterruptData(BluetoothDevice, byte, byte[]): void  [API 28+]
+ onSetProtocol(BluetoothDevice, byte): void  [API 28+]
+ onSetReport(BluetoothDevice, byte, byte, byte[]): void  [API 28+]
+ onVirtualCableUnplug(BluetoothDevice): void  [API 28+]
```

## BluetoothHidDeviceAppQosSettings — declaration; API 28+ (0 +, 9 *, 0 ~, 0 -)

```
* BluetoothHidDeviceAppQosSettings(int, int, int, int, int, int)  [API 28+]
+ CREATOR [field]  [API 28+]
* describeContents(): int  [API 28+]
* getDelayVariation(): int  [API 28+]
* getLatency(): int  [API 28+]
* getPeakBandwidth(): int  [API 28+]
* getServiceType(): int  [API 28+]
* getTokenBucketSize(): int  [API 28+]
* getTokenRate(): int  [API 28+]
* writeToParcel(Parcel, int): void  [API 28+]
```

## BluetoothHidDeviceAppSdpSettings — declaration; API 28+ (0 +, 8 *, 0 ~, 0 -)

```
* BluetoothHidDeviceAppSdpSettings(String, String, String, byte, byte[])  [API 28+]
+ CREATOR [field]  [API 28+]
* describeContents(): int  [API 28+]
* getDescription(): String  [API 28+]
* getDescriptors(): byte[]  [API 28+]
* getName(): String  [API 28+]
* getProvider(): String  [API 28+]
* getSubclass(): byte  [API 28+]
* writeToParcel(Parcel, int): void  [API 28+]
```

## BluetoothLeAudio — declaration; API 31+ (7 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 31+]
+ finalize(): void  [API 31+]
+ getConnectedDevices(): List  [API 31+]
+ getConnectedGroupLeadDevice(int): BluetoothDevice  [API 33+]
+ getConnectionState(BluetoothDevice): int  [API 31+]
+ getDevicesMatchingConnectionStates(int[]): List  [API 31+]
+ getGroupId(BluetoothDevice): int  [API 33+]
```

## BluetoothLeAudioCodecConfig — declaration; API 33+ (0 +, 15 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 33+]
* describeContents(): int  [API 33+]
* equals(Object): boolean  [API 33+]
* getBitsPerSample(): int  [API 33+]
* getChannelCount(): int  [API 33+]
* getCodecName(): String  [API 33+]
* getCodecPriority(): int  [API 33+]
* getCodecType(): int  [API 33+]
* getFrameDuration(): int  [API 33+]
* getMaxOctetsPerFrame(): int  [API 33+]
* getMinOctetsPerFrame(): int  [API 33+]
* getOctetsPerFrame(): int  [API 33+]
* getSampleRate(): int  [API 33+]
* hashCode(): int  [API 33+]
* toString(): String  [API 33+]
* writeToParcel(Parcel, int): void  [API 33+]
```

## BluetoothLeAudioCodecConfig.Builder — declaration; API 33+ (0 +, 12 *, 0 ~, 0 -)

```
* Builder()  [API 33+]
* Builder(BluetoothLeAudioCodecConfig)  [API 33+]
* build(): BluetoothLeAudioCodecConfig  [API 33+]
* setBitsPerSample(int): BluetoothLeAudioCodecConfig.Builder  [API 33+]
* setChannelCount(int): BluetoothLeAudioCodecConfig.Builder  [API 33+]
* setCodecPriority(int): BluetoothLeAudioCodecConfig.Builder  [API 33+]
* setCodecType(int): BluetoothLeAudioCodecConfig.Builder  [API 33+]
* setFrameDuration(int): BluetoothLeAudioCodecConfig.Builder  [API 33+]
* setMaxOctetsPerFrame(int): BluetoothLeAudioCodecConfig.Builder  [API 33+]
* setMinOctetsPerFrame(int): BluetoothLeAudioCodecConfig.Builder  [API 33+]
* setOctetsPerFrame(int): BluetoothLeAudioCodecConfig.Builder  [API 33+]
* setSampleRate(int): BluetoothLeAudioCodecConfig.Builder  [API 33+]
```

## BluetoothLeAudioCodecStatus — declaration; API 33+ (13 +, 0 *, 0 ~, 1 -)

```
- BluetoothLeAudioCodecStatus(BluetoothLeAudioCodecConfig, BluetoothLeAudioCodecConfig, List, List, List, List)  [API 33+]
- CREATOR [field]  [API 33+]
+ describeContents(): int  [API 33+]
+ equals(Object): boolean  [API 33+]
+ getInputCodecConfig(): BluetoothLeAudioCodecConfig  [API 33+]
+ getInputCodecLocalCapabilities(): List  [API 33+]
+ getInputCodecSelectableCapabilities(): List  [API 33+]
+ getOutputCodecConfig(): BluetoothLeAudioCodecConfig  [API 33+]
+ getOutputCodecLocalCapabilities(): List  [API 33+]
+ getOutputCodecSelectableCapabilities(): List  [API 33+]
+ hashCode(): int  [API 33+]
+ isInputCodecConfigSelectable(BluetoothLeAudioCodecConfig): boolean  [API 33+]
+ isOutputCodecConfigSelectable(BluetoothLeAudioCodecConfig): boolean  [API 33+]
+ toString(): String  [API 33+]
+ writeToParcel(Parcel, int): void  [API 33+]
```

## BluetoothManager — framework; API 18+ (5 +, 0 *, 0 ~, 0 -)

```
+ getAdapter(): BluetoothAdapter  [API 18+]
+ getConnectedDevices(int): List  [API 18+]
+ getConnectionState(BluetoothDevice, int): int  [API 18+]
+ getDevicesMatchingConnectionStates(int, int[]): List  [API 18+]
+ openGattServer(Context, BluetoothGattServerCallback): BluetoothGattServer  [API 18+]
```

## BluetoothProfile — declaration; API 11+ (3 +, 0 *, 0 ~, 0 -)

```
+ getConnectedDevices(): List  [API 11+]
+ getConnectionState(BluetoothDevice): int  [API 11+]
+ getDevicesMatchingConnectionStates(int[]): List  [API 11+]
```

## BluetoothProfile.ServiceListener — declaration; API 11+ (2 +, 0 *, 0 ~, 0 -)

```
+ onServiceConnected(int, BluetoothProfile): void  [API 11+]
+ onServiceDisconnected(int): void  [API 11+]
```

## BluetoothServerSocket — declaration; API 5+ (5 +, 0 *, 0 ~, 0 -)

```
+ accept(): BluetoothSocket  [API 5+]
+ accept(int): BluetoothSocket  [API 5+]
+ close(): void  [API 5+]
+ getPsm(): int  [API 29+]
+ toString(): String  [API 5+]
```

## BluetoothSocket — declaration; API 5+ (11 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 5+]
+ connect(): void  [API 5+]
+ finalize(): void  [API 5+]
+ getConnectionType(): int  [API 23+]
+ getInputStream(): InputStream  [API 5+]
+ getMaxReceivePacketSize(): int  [API 23+]
+ getMaxTransmitPacketSize(): int  [API 23+]
+ getOutputStream(): OutputStream  [API 5+]
+ getRemoteDevice(): BluetoothDevice  [API 5+]
+ isConnected(): boolean  [API 14+]
+ toString(): String  [API 5+]
```

## BluetoothSocketException — declaration; API 34+ (1 +, 0 *, 0 ~, 2 -)

```
- BluetoothSocketException(int)  [API 34+]
- BluetoothSocketException(int, String)  [API 34+]
+ getErrorCode(): int  [API 34+]
```

## BluetoothStatusCodes — declaration; API 31+ (0 +, 0 *, 0 ~, 0 -)

