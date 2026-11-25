# android.mtp

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## MtpConstants — declaration; API 12+ (0 +, 0 *, 0 ~, 2 -)

```
- MtpConstants()  [API 12+]
- isAbstractObject(int): boolean [static]  [API 12+]
```

## MtpDevice — declaration; API 12+ (0 +, 0 *, 0 ~, 24 -)

```
- MtpDevice(UsbDevice)  [API 12+]
- close(): void  [API 12+]
- deleteObject(int): boolean  [API 12+]
- finalize(): void  [API 12+]
- getDeviceId(): int  [API 12+]
- getDeviceInfo(): MtpDeviceInfo  [API 12+]
- getDeviceName(): String  [API 12+]
- getObject(int, int): byte[]  [API 12+]
- getObjectHandles(int, int, int): int[]  [API 12+]
- getObjectInfo(int): MtpObjectInfo  [API 12+]
- getParent(int): long  [API 12+]
- getPartialObject(int, long, long, byte[]): long  [API 24+]
- getPartialObject64(int, long, long, byte[]): long  [API 24+]
- getStorageId(int): long  [API 12+]
- getStorageIds(): int[]  [API 12+]
- getStorageInfo(int): MtpStorageInfo  [API 12+]
- getThumbnail(int): byte[]  [API 12+]
- importFile(int, ParcelFileDescriptor): boolean  [API 24+]
- importFile(int, String): boolean  [API 12+]
- open(UsbDeviceConnection): boolean  [API 12+]
- readEvent(CancellationSignal): MtpEvent  [API 24+]
- sendObject(int, long, ParcelFileDescriptor): boolean  [API 24+]
- sendObjectInfo(MtpObjectInfo): MtpObjectInfo  [API 24+]
- toString(): String  [API 12+]
```

## MtpDeviceInfo — declaration; API 12+ (0 +, 0 *, 0 ~, 8 -)

```
- getEventsSupported(): int[]  [API 24+]
- getManufacturer(): String  [API 12+]
- getModel(): String  [API 12+]
- getOperationsSupported(): int[]  [API 24+]
- getSerialNumber(): String  [API 12+]
- getVersion(): String  [API 12+]
- isEventSupported(int): boolean  [API 24+]
- isOperationSupported(int): boolean  [API 24+]
```

## MtpEvent — declaration; API 24+ (0 +, 0 *, 0 ~, 10 -)

```
- getDevicePropCode(): int  [API 24+]
- getEventCode(): int  [API 24+]
- getObjectFormatCode(): int  [API 24+]
- getObjectHandle(): int  [API 24+]
- getObjectPropCode(): int  [API 24+]
- getParameter1(): int  [API 24+]
- getParameter2(): int  [API 24+]
- getParameter3(): int  [API 24+]
- getStorageId(): int  [API 24+]
- getTransactionId(): int  [API 24+]
```

## MtpObjectInfo — declaration; API 12+ (0 +, 28 *, 0 ~, 0 -)

```
* getAssociationDesc(): int  [API 12+]
* getAssociationType(): int  [API 12+]
* getCompressedSize(): int  [API 12+]
* getCompressedSizeLong(): long  [API 24+]
* getDateCreated(): long  [API 12+]
* getDateModified(): long  [API 12+]
* getFormat(): int  [API 12+]
* getImagePixDepth(): int  [API 12+]
* getImagePixDepthLong(): long  [API 24+]
* getImagePixHeight(): int  [API 12+]
* getImagePixHeightLong(): long  [API 24+]
* getImagePixWidth(): int  [API 12+]
* getImagePixWidthLong(): long  [API 24+]
* getKeywords(): String  [API 12+]
* getName(): String  [API 12+]
* getObjectHandle(): int  [API 12+]
* getParent(): int  [API 12+]
* getProtectionStatus(): int  [API 12+]
* getSequenceNumber(): int  [API 12+]
* getSequenceNumberLong(): long  [API 24+]
* getStorageId(): int  [API 12+]
* getThumbCompressedSize(): int  [API 12+]
* getThumbCompressedSizeLong(): long  [API 24+]
* getThumbFormat(): int  [API 12+]
* getThumbPixHeight(): int  [API 12+]
* getThumbPixHeightLong(): long  [API 24+]
* getThumbPixWidth(): int  [API 12+]
* getThumbPixWidthLong(): long  [API 24+]
```

## MtpObjectInfo.Builder — declaration; API 24+ (0 +, 15 *, 0 ~, 8 -)

```
* Builder()  [API 24+]
* Builder(MtpObjectInfo)  [API 24+]
* build(): MtpObjectInfo  [API 24+]
* setAssociationDesc(int): MtpObjectInfo.Builder  [API 24+]
* setAssociationType(int): MtpObjectInfo.Builder  [API 24+]
- setCompressedSize(long): MtpObjectInfo.Builder  [API 24+]
* setDateCreated(long): MtpObjectInfo.Builder  [API 24+]
* setDateModified(long): MtpObjectInfo.Builder  [API 24+]
* setFormat(int): MtpObjectInfo.Builder  [API 24+]
- setImagePixDepth(long): MtpObjectInfo.Builder  [API 24+]
- setImagePixHeight(long): MtpObjectInfo.Builder  [API 24+]
- setImagePixWidth(long): MtpObjectInfo.Builder  [API 24+]
* setKeywords(String): MtpObjectInfo.Builder  [API 24+]
* setName(String): MtpObjectInfo.Builder  [API 24+]
* setObjectHandle(int): MtpObjectInfo.Builder  [API 24+]
* setParent(int): MtpObjectInfo.Builder  [API 24+]
* setProtectionStatus(int): MtpObjectInfo.Builder  [API 24+]
- setSequenceNumber(long): MtpObjectInfo.Builder  [API 24+]
* setStorageId(int): MtpObjectInfo.Builder  [API 24+]
- setThumbCompressedSize(long): MtpObjectInfo.Builder  [API 24+]
* setThumbFormat(int): MtpObjectInfo.Builder  [API 24+]
- setThumbPixHeight(long): MtpObjectInfo.Builder  [API 24+]
- setThumbPixWidth(long): MtpObjectInfo.Builder  [API 24+]
```

## MtpStorageInfo — declaration; API 12+ (0 +, 0 *, 0 ~, 5 -)

```
- getDescription(): String  [API 12+]
- getFreeSpace(): long  [API 12+]
- getMaxCapacity(): long  [API 12+]
- getStorageId(): int  [API 12+]
- getVolumeIdentifier(): String  [API 12+]
```

