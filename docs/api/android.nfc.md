# android.nfc

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AvailableNfcAntenna — declaration; API 34+ (0 +, 8 *, 0 ~, 0 -)

```
* AvailableNfcAntenna(int, int)  [API 34+]
+ CREATOR [field]  [API 34+]
* describeContents(): int  [API 34+]
* equals(Object): boolean  [API 34+]
* getLocationX(): int  [API 34+]
* getLocationY(): int  [API 34+]
* hashCode(): int  [API 34+]
* toString(): String  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## FormatException — declaration; API 9+ (3 +, 0 *, 0 ~, 0 -)

```
+ FormatException()  [API 9+]
+ FormatException(String)  [API 9+]
+ FormatException(String, Throwable)  [API 16+]
```

## NdefMessage — declaration; API 9+ (7 +, 2 *, 0 ~, 2 -)

```
- CREATOR [field]  [API 9+]
- NdefMessage(NdefRecord, NdefRecord[])  [API 16+]
* NdefMessage(NdefRecord[])  [API 9+]
- NdefMessage(byte[])  [API 9+]
+ describeContents(): int  [API 9+]
+ equals(Object): boolean  [API 9+]
+ getByteArrayLength(): int  [API 16+]
* getRecords(): NdefRecord[]  [API 9+]
+ hashCode(): int  [API 9+]
+ toByteArray(): byte[]  [API 9+]
+ toString(): String  [API 9+]
+ writeToParcel(Parcel, int): void  [API 9+]
```

## NdefRecord — declaration; API 9+ (9 +, 10 *, 0 ~, 1 -)

```
+ CREATOR [field]  [API 9+]
- NdefRecord(byte[])  [API 9+, deprecated in 16]
* NdefRecord(short, byte[], byte[], byte[])  [API 9+]
- RTD_ALTERNATIVE_CARRIER [field]  [API 9+]
- RTD_HANDOVER_CARRIER [field]  [API 9+]
- RTD_HANDOVER_REQUEST [field]  [API 9+]
- RTD_HANDOVER_SELECT [field]  [API 9+]
- RTD_SMART_POSTER [field]  [API 9+]
- RTD_TEXT [field]  [API 9+]
- RTD_URI [field]  [API 9+]
+ createApplicationRecord(String): NdefRecord [static]  [API 14+]
+ createExternal(String, String, byte[]): NdefRecord [static]  [API 16+]
+ createMime(String, byte[]): NdefRecord [static]  [API 16+]
+ createTextRecord(String, String): NdefRecord [static]  [API 21+]
+ createUri(String): NdefRecord [static]  [API 14+]
+ createUri(Uri): NdefRecord [static]  [API 14+]
* describeContents(): int  [API 9+]
* equals(Object): boolean  [API 9+]
* getId(): byte[]  [API 9+]
* getPayload(): byte[]  [API 9+]
* getTnf(): short  [API 9+]
* getType(): byte[]  [API 9+]
* hashCode(): int  [API 9+]
+ toByteArray(): byte[]  [API 9+, deprecated in 16]
+ toMimeType(): String  [API 16+]
* toString(): String  [API 9+]
+ toUri(): Uri  [API 16+]
* writeToParcel(Parcel, int): void  [API 9+]
```

## NfcAdapter — declaration; API 9+ (27 +, 0 *, 0 ~, 0 -)

```
+ disableForegroundDispatch(Activity): void  [API 10+]
+ disableForegroundNdefPush(Activity): void  [API 10-33, removed in 34, deprecated in 15]
+ disableReaderMode(Activity): void  [API 19+]
+ enableForegroundDispatch(Activity, PendingIntent, IntentFilter[], String[][]): void  [API 10+]
+ enableForegroundNdefPush(Activity, NdefMessage): void  [API 10-33, removed in 34, deprecated in 15]
+ enableReaderMode(Activity, NfcAdapter.ReaderCallback, int, Bundle): void  [API 19+]
+ getDefaultAdapter(): NfcAdapter  [API 9-15, removed in 16, deprecated in 15]
+ getDefaultAdapter(Context): NfcAdapter [static]  [API 10+]
+ getNfcAntennaInfo(): NfcAntennaInfo  [API 34+]
+ ignore(Tag, int, NfcAdapter.OnTagRemovedListener, Handler): boolean  [API 24+]
+ invokeBeam(Activity): boolean  [API 21-33, removed in 34, deprecated in 29]
+ isEnabled(): boolean  [API 9+]
+ isNdefPushEnabled(): boolean  [API 16-33, removed in 34, deprecated in 29]
+ isObserveModeEnabled(): boolean  [API 35+]
+ isObserveModeSupported(): boolean  [API 35+]
+ isReaderOptionEnabled(): boolean  [API 35+]
+ isReaderOptionSupported(): boolean  [API 35+]
+ isSecureNfcEnabled(): boolean  [API 29+]
+ isSecureNfcSupported(): boolean  [API 29+]
+ resetDiscoveryTechnology(Activity): void  [API 35+]
+ setBeamPushUris(Uri[], Activity): void  [API 16-33, removed in 34, deprecated in 29]
+ setBeamPushUrisCallback(NfcAdapter.CreateBeamUrisCallback, Activity): void  [API 16-33, removed in 34, deprecated in 29]
+ setDiscoveryTechnology(Activity, int, int): void  [API 35+]
+ setNdefPushMessage(NdefMessage, Activity, Activity[]): void  [API 14-33, removed in 34, deprecated in 29]
+ setNdefPushMessageCallback(NfcAdapter.CreateNdefMessageCallback, Activity, Activity[]): void  [API 14-33, removed in 34, deprecated in 29]
+ setObserveModeEnabled(boolean): boolean  [API 35+]
+ setOnNdefPushCompleteCallback(NfcAdapter.OnNdefPushCompleteCallback, Activity, Activity[]): void  [API 14-33, removed in 34, deprecated in 29]
```

## NfcAdapter.CreateBeamUrisCallback — declaration; API 16+, deprecated in 29 (1 +, 0 *, 0 ~, 0 -)

```
+ createBeamUris(NfcEvent): Uri[]  [API 16+]
```

## NfcAdapter.CreateNdefMessageCallback — declaration; API 14+, deprecated in 29 (1 +, 0 *, 0 ~, 0 -)

```
+ createNdefMessage(NfcEvent): NdefMessage  [API 14+]
```

## NfcAdapter.OnNdefPushCompleteCallback — declaration; API 14+, deprecated in 29 (1 +, 0 *, 0 ~, 0 -)

```
+ onNdefPushComplete(NfcEvent): void  [API 14+]
```

## NfcAdapter.OnTagRemovedListener — declaration; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ onTagRemoved(): void  [API 24+]
```

## NfcAdapter.ReaderCallback — declaration; API 19+ (1 +, 0 *, 0 ~, 0 -)

```
+ onTagDiscovered(Tag): void  [API 19+]
```

## NfcAntennaInfo — declaration; API 34+ (0 +, 7 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* NfcAntennaInfo(int, int, boolean, List)  [API 34+]
* describeContents(): int  [API 34+]
* getAvailableNfcAntennas(): List  [API 34+]
* getDeviceHeight(): int  [API 34+]
* getDeviceWidth(): int  [API 34+]
* isDeviceFoldable(): boolean  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## NfcEvent — declaration; API 14+ (0 +, 0 *, 0 ~, 0 -)

```
- nfcAdapter [field]  [API 14+]
- peerLlcpMajorVersion [field]  [API 23+]
- peerLlcpMinorVersion [field]  [API 23+]
```

## NfcManager — framework; API 10+ (1 +, 0 *, 0 ~, 0 -)

```
+ getDefaultAdapter(): NfcAdapter  [API 10+]
```

## Tag — declaration; API 10+ (5 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 10+]
+ describeContents(): int  [API 10+]
+ getId(): byte[]  [API 10+]
+ getTechList(): String[]  [API 10+]
+ toString(): String  [API 10+]
+ writeToParcel(Parcel, int): void  [API 10+]
```

## TagLostException — declaration; API 10+ (2 +, 0 *, 0 ~, 0 -)

```
+ TagLostException()  [API 10+]
+ TagLostException(String)  [API 10+]
```

