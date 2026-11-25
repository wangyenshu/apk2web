# android.nfc.tech

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## IsoDep — declaration; API 10+ (10 +, 2 *, 0 ~, 0 -)

```
+ close(): void  [API 10+]
+ connect(): void  [API 10+]
+ get(Tag): IsoDep [static]  [API 10+]
+ getHiLayerResponse(): byte[]  [API 10+]
+ getHistoricalBytes(): byte[]  [API 10+]
+ getMaxTransceiveLength(): int  [API 14+]
+ getTag(): Tag  [API 10+]
* getTimeout(): int  [API 14+]
+ isConnected(): boolean  [API 10+]
+ isExtendedLengthApduSupported(): boolean  [API 16+]
* setTimeout(int): void  [API 10+]
+ transceive(byte[]): byte[]  [API 10+]
```

## MifareClassic — declaration; API 10+ (22 +, 2 *, 0 ~, 0 -)

```
- KEY_DEFAULT [field]  [API 10+]
- KEY_MIFARE_APPLICATION_DIRECTORY [field]  [API 10+]
- KEY_NFC_FORUM [field]  [API 10+]
+ authenticateSectorWithKeyA(int, byte[]): boolean  [API 10+]
+ authenticateSectorWithKeyB(int, byte[]): boolean  [API 10+]
+ blockToSector(int): int  [API 10+]
+ close(): void  [API 10+]
+ connect(): void  [API 10+]
+ decrement(int, int): void  [API 10+]
+ get(Tag): MifareClassic [static]  [API 10+]
+ getBlockCount(): int  [API 10+]
+ getBlockCountInSector(int): int  [API 10+]
+ getMaxTransceiveLength(): int  [API 14+]
+ getSectorCount(): int  [API 10+]
+ getSize(): int  [API 10+]
+ getTag(): Tag  [API 10+]
* getTimeout(): int  [API 14+]
+ getType(): int  [API 10+]
+ increment(int, int): void  [API 10+]
+ isConnected(): boolean  [API 10+]
+ readBlock(int): byte[]  [API 10+]
+ restore(int): void  [API 10+]
+ sectorToBlock(int): int  [API 10+]
* setTimeout(int): void  [API 14+]
+ transceive(byte[]): byte[]  [API 10+]
+ transfer(int): void  [API 10+]
+ writeBlock(int, byte[]): void  [API 10+]
```

## MifareUltralight — declaration; API 10+ (10 +, 2 *, 0 ~, 0 -)

```
+ close(): void  [API 10+]
+ connect(): void  [API 10+]
+ get(Tag): MifareUltralight [static]  [API 10+]
+ getMaxTransceiveLength(): int  [API 14+]
+ getTag(): Tag  [API 10+]
* getTimeout(): int  [API 14+]
+ getType(): int  [API 10+]
+ isConnected(): boolean  [API 10+]
+ readPages(int): byte[]  [API 10+]
* setTimeout(int): void  [API 14+]
+ transceive(byte[]): byte[]  [API 10+]
+ writePage(int, byte[]): void  [API 10+]
```

## Ndef — declaration; API 10+ (13 +, 0 *, 0 ~, 0 -)

```
+ canMakeReadOnly(): boolean  [API 10+]
+ close(): void  [API 10+]
+ connect(): void  [API 10+]
+ get(Tag): Ndef [static]  [API 10+]
+ getCachedNdefMessage(): NdefMessage  [API 10+]
+ getMaxSize(): int  [API 10+]
+ getNdefMessage(): NdefMessage  [API 10+]
+ getTag(): Tag  [API 10+]
+ getType(): String  [API 10+]
+ isConnected(): boolean  [API 10+]
+ isWritable(): boolean  [API 10+]
+ makeReadOnly(): boolean  [API 10+]
+ writeNdefMessage(NdefMessage): void  [API 10+]
```

## NdefFormatable — declaration; API 10+ (7 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 10+]
+ connect(): void  [API 10+]
+ format(NdefMessage): void  [API 10+]
+ formatReadOnly(NdefMessage): void  [API 10+]
+ get(Tag): NdefFormatable [static]  [API 10+]
+ getTag(): Tag  [API 10+]
+ isConnected(): boolean  [API 10+]
```

## NfcA — declaration; API 10+ (9 +, 2 *, 0 ~, 0 -)

```
+ close(): void  [API 10+]
+ connect(): void  [API 10+]
+ get(Tag): NfcA [static]  [API 10+]
+ getAtqa(): byte[]  [API 10+]
+ getMaxTransceiveLength(): int  [API 14+]
+ getSak(): short  [API 10+]
+ getTag(): Tag  [API 10+]
* getTimeout(): int  [API 14+]
+ isConnected(): boolean  [API 10+]
* setTimeout(int): void  [API 14+]
+ transceive(byte[]): byte[]  [API 10+]
```

## NfcB — declaration; API 10+ (9 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 10+]
+ connect(): void  [API 10+]
+ get(Tag): NfcB [static]  [API 10+]
+ getApplicationData(): byte[]  [API 10+]
+ getMaxTransceiveLength(): int  [API 14+]
+ getProtocolInfo(): byte[]  [API 10+]
+ getTag(): Tag  [API 10+]
+ isConnected(): boolean  [API 10+]
+ transceive(byte[]): byte[]  [API 10+]
```

## NfcBarcode — declaration; API 17+ (7 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 17+]
+ connect(): void  [API 17+]
+ get(Tag): NfcBarcode [static]  [API 17+]
+ getBarcode(): byte[]  [API 17+]
+ getTag(): Tag  [API 17+]
+ getType(): int  [API 17+]
+ isConnected(): boolean  [API 17+]
```

## NfcF — declaration; API 10+ (9 +, 2 *, 0 ~, 0 -)

```
+ close(): void  [API 10+]
+ connect(): void  [API 10+]
+ get(Tag): NfcF [static]  [API 10+]
+ getManufacturer(): byte[]  [API 10+]
+ getMaxTransceiveLength(): int  [API 14+]
+ getSystemCode(): byte[]  [API 10+]
+ getTag(): Tag  [API 10+]
* getTimeout(): int  [API 14+]
+ isConnected(): boolean  [API 10+]
* setTimeout(int): void  [API 14+]
+ transceive(byte[]): byte[]  [API 10+]
```

## NfcV — declaration; API 10+ (9 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 10+]
+ connect(): void  [API 10+]
+ get(Tag): NfcV [static]  [API 10+]
+ getDsfId(): byte  [API 10+]
+ getMaxTransceiveLength(): int  [API 14+]
+ getResponseFlags(): byte  [API 10+]
+ getTag(): Tag  [API 10+]
+ isConnected(): boolean  [API 10+]
+ transceive(byte[]): byte[]  [API 10+]
```

## TagTechnology — declaration; API 10+ (4 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 10+]
+ connect(): void  [API 10+]
+ getTag(): Tag  [API 10+]
+ isConnected(): boolean  [API 10+]
```

