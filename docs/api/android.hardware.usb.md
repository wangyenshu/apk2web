# android.hardware.usb

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## UsbAccessory — declaration; API 12+ (11 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 12+]
+ describeContents(): int  [API 12+]
+ equals(Object): boolean  [API 12+]
+ getDescription(): String  [API 12+]
+ getManufacturer(): String  [API 12+]
+ getModel(): String  [API 12+]
+ getSerial(): String  [API 12+]
+ getUri(): String  [API 12+]
+ getVersion(): String  [API 12+]
+ hashCode(): int  [API 12+]
+ toString(): String  [API 12+]
+ writeToParcel(Parcel, int): void  [API 12+]
```

## UsbConfiguration — declaration; API 21+ (10 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 21+]
+ describeContents(): int  [API 21+]
+ getId(): int  [API 21+]
+ getInterface(int): UsbInterface  [API 21+]
+ getInterfaceCount(): int  [API 21+]
+ getMaxPower(): int  [API 21+]
+ getName(): String  [API 21+]
+ isRemoteWakeup(): boolean  [API 21+]
+ isSelfPowered(): boolean  [API 21+]
+ toString(): String  [API 21+]
+ writeToParcel(Parcel, int): void  [API 21+]
```

## UsbConstants — declaration; API 12+ (1 +, 0 *, 0 ~, 0 -)

```
+ UsbConstants()  [API 12+]
```

## UsbDevice — declaration; API 12+ (22 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 12+]
+ describeContents(): int  [API 12+]
+ equals(Object): boolean  [API 12+]
+ getConfiguration(int): UsbConfiguration  [API 21+]
+ getConfigurationCount(): int  [API 21+]
+ getDeviceClass(): int  [API 12+]
+ getDeviceId(): int  [API 12+]
+ getDeviceId(String): int [static]  [API 12+]
+ getDeviceName(): String  [API 12+]
+ getDeviceName(int): String [static]  [API 12+]
+ getDeviceProtocol(): int  [API 12+]
+ getDeviceSubclass(): int  [API 12+]
+ getInterface(int): UsbInterface  [API 12+]
+ getInterfaceCount(): int  [API 12+]
+ getManufacturerName(): String  [API 21+]
+ getProductId(): int  [API 12+]
+ getProductName(): String  [API 21+]
+ getSerialNumber(): String  [API 21+]
+ getVendorId(): int  [API 12+]
+ getVersion(): String  [API 23+]
+ hashCode(): int  [API 12+]
+ toString(): String  [API 12+]
+ writeToParcel(Parcel, int): void  [API 12+]
```

## UsbDeviceConnection — declaration; API 12+ (15 +, 0 *, 0 ~, 0 -)

```
+ bulkTransfer(UsbEndpoint, byte[], int, int): int  [API 12+]
+ bulkTransfer(UsbEndpoint, byte[], int, int, int): int  [API 18+]
+ claimInterface(UsbInterface, boolean): boolean  [API 12+]
+ close(): void  [API 12+]
+ controlTransfer(int, int, int, int, byte[], int, int): int  [API 12+]
+ controlTransfer(int, int, int, int, byte[], int, int, int): int  [API 18+]
+ finalize(): void  [API 12+]
+ getFileDescriptor(): int  [API 12+]
+ getRawDescriptors(): byte[]  [API 13+]
+ getSerial(): String  [API 12+]
+ releaseInterface(UsbInterface): boolean  [API 12+]
+ requestWait(): UsbRequest  [API 12+]
+ requestWait(long): UsbRequest  [API 26+]
+ setConfiguration(UsbConfiguration): boolean  [API 21+]
+ setInterface(UsbInterface): boolean  [API 21+]
```

## UsbEndpoint — declaration; API 12+ (10 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 12+]
+ describeContents(): int  [API 12+]
+ getAddress(): int  [API 12+]
+ getAttributes(): int  [API 12+]
+ getDirection(): int  [API 12+]
+ getEndpointNumber(): int  [API 12+]
+ getInterval(): int  [API 12+]
+ getMaxPacketSize(): int  [API 12+]
+ getType(): int  [API 12+]
+ toString(): String  [API 12+]
+ writeToParcel(Parcel, int): void  [API 12+]
```

## UsbInterface — declaration; API 12+ (11 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 12+]
+ describeContents(): int  [API 12+]
+ getAlternateSetting(): int  [API 21+]
+ getEndpoint(int): UsbEndpoint  [API 12+]
+ getEndpointCount(): int  [API 12+]
+ getId(): int  [API 12+]
+ getInterfaceClass(): int  [API 12+]
+ getInterfaceProtocol(): int  [API 12+]
+ getInterfaceSubclass(): int  [API 12+]
+ getName(): String  [API 21+]
+ toString(): String  [API 12+]
+ writeToParcel(Parcel, int): void  [API 12+]
```

## UsbManager — framework; API 12+ (8 +, 0 *, 0 ~, 0 -)

```
+ getAccessoryList(): UsbAccessory[]  [API 12+]
+ getDeviceList(): HashMap  [API 12+]
+ hasPermission(UsbAccessory): boolean  [API 12+]
+ hasPermission(UsbDevice): boolean  [API 12+]
+ openAccessory(UsbAccessory): ParcelFileDescriptor  [API 12+]
+ openDevice(UsbDevice): UsbDeviceConnection  [API 12+]
+ requestPermission(UsbAccessory, PendingIntent): void  [API 12+]
+ requestPermission(UsbDevice, PendingIntent): void  [API 12+]
```

## UsbRequest — declaration; API 12+ (7 +, 3 *, 0 ~, 0 -)

```
* UsbRequest()  [API 12+]
+ cancel(): boolean  [API 12+]
+ close(): void  [API 12+]
+ finalize(): void  [API 12+]
* getClientData(): Object  [API 12+]
+ getEndpoint(): UsbEndpoint  [API 12+]
+ initialize(UsbDeviceConnection, UsbEndpoint): boolean  [API 12+]
+ queue(ByteBuffer): boolean  [API 26+]
+ queue(ByteBuffer, int): boolean  [API 12+, deprecated in 26]
* setClientData(Object): void  [API 12+]
```

