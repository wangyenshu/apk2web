# android.telephony

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AccessNetworkConstants — declaration; API 28+ (0 +, 0 *, 0 ~, 0 -)

## AccessNetworkConstants.AccessNetworkType — declaration; API 28+ (0 +, 0 *, 0 ~, 0 -)

## AccessNetworkConstants.EutranBand — declaration; API 28+ (0 +, 0 *, 0 ~, 0 -)

## AccessNetworkConstants.GeranBand — declaration; API 28+ (0 +, 0 *, 0 ~, 0 -)

## AccessNetworkConstants.NgranBands — declaration; API 30+ (0 +, 0 *, 0 ~, 0 -)

## AccessNetworkConstants.UtranBand — declaration; API 28+ (0 +, 0 *, 0 ~, 0 -)

## AvailableNetworkInfo — declaration; API 29+ (0 +, 11 *, 0 ~, 0 -)

```
* AvailableNetworkInfo(int, int, List, List)  [API 29+]
+ CREATOR [field]  [API 29+]
* describeContents(): int  [API 29+]
* equals(Object): boolean  [API 29+]
* getBands(): List  [API 29+]
* getMccMncs(): List  [API 29+]
* getPriority(): int  [API 29+]
* getRadioAccessSpecifiers(): List  [API 33+]
* getSubId(): int  [API 29+]
* hashCode(): int  [API 29+]
* toString(): String  [API 29+]
* writeToParcel(Parcel, int): void  [API 29+]
```

## AvailableNetworkInfo.Builder — declaration; API 33+ (0 +, 5 *, 0 ~, 0 -)

```
* Builder(int)  [API 33+]
* build(): AvailableNetworkInfo  [API 33+]
* setMccMncs(List): AvailableNetworkInfo.Builder  [API 33+]
* setPriority(int): AvailableNetworkInfo.Builder  [API 33+]
* setRadioAccessSpecifiers(List): AvailableNetworkInfo.Builder  [API 33+]
```

## BarringInfo — declaration; API 30+ (6 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 30+]
+ describeContents(): int  [API 30+]
+ equals(Object): boolean  [API 30+]
+ getBarringServiceInfo(int): BarringInfo.BarringServiceInfo  [API 30+]
+ hashCode(): int  [API 30+]
+ toString(): String  [API 30+]
+ writeToParcel(Parcel, int): void  [API 30+]
```

## BarringInfo.BarringServiceInfo — declaration; API 30+ (10 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 30+]
+ describeContents(): int  [API 30+]
+ equals(Object): boolean  [API 30+]
+ getBarringType(): int  [API 30+]
+ getConditionalBarringFactor(): int  [API 30+]
+ getConditionalBarringTimeSeconds(): int  [API 30+]
+ hashCode(): int  [API 30+]
+ isBarred(): boolean  [API 30+]
+ isConditionallyBarred(): boolean  [API 30+]
+ toString(): String  [API 30+]
+ writeToParcel(Parcel, int): void  [API 30+]
```

## CarrierConfigManager — framework; API 23+ (9 +, 0 *, 0 ~, 0 -)

```
+ getConfig(): PersistableBundle  [API 23+, deprecated in 34]
+ getConfig(String[]): PersistableBundle  [API 34+]
+ getConfigByComponentForSubId(String, int): PersistableBundle  [API 30+]
+ getConfigForSubId(int): PersistableBundle  [API 23+, deprecated in 34]
+ getConfigForSubId(int, String[]): PersistableBundle  [API 34+]
+ isConfigForIdentifiedCarrier(PersistableBundle): boolean [static]  [API 28+]
+ notifyConfigChangedForSubId(int): void  [API 23+]
+ registerCarrierConfigChangeListener(Executor, CarrierConfigManager.CarrierConfigChangeListener): void  [API 34+]
+ unregisterCarrierConfigChangeListener(CarrierConfigManager.CarrierConfigChangeListener): void  [API 34+]
```

## CarrierConfigManager.Apn — framework; API 30+ (0 +, 0 *, 0 ~, 0 -)

## CarrierConfigManager.Bsf — framework; API 33+ (0 +, 0 *, 0 ~, 0 -)

## CarrierConfigManager.CarrierConfigChangeListener — framework; API 34+ (1 +, 0 *, 0 ~, 0 -)

```
+ onCarrierConfigChanged(int, int, int, int): void  [API 34+]
```

## CarrierConfigManager.Gps — framework; API 30+ (0 +, 0 *, 0 ~, 0 -)

## CarrierConfigManager.Ims — framework; API 30+ (0 +, 0 *, 0 ~, 0 -)

## CarrierConfigManager.ImsEmergency — framework; API 33+ (0 +, 0 *, 0 ~, 0 -)

## CarrierConfigManager.ImsRtt — framework; API 33+ (0 +, 0 *, 0 ~, 0 -)

## CarrierConfigManager.ImsServiceEntitlement — framework; API 31+ (0 +, 0 *, 0 ~, 0 -)

## CarrierConfigManager.ImsSms — framework; API 33+ (0 +, 0 *, 0 ~, 0 -)

## CarrierConfigManager.ImsSs — framework; API 33+ (0 +, 0 *, 0 ~, 0 -)

## CarrierConfigManager.ImsVoice — framework; API 33+ (0 +, 0 *, 0 ~, 0 -)

## CarrierConfigManager.ImsVt — framework; API 33+ (0 +, 0 *, 0 ~, 0 -)

## CarrierConfigManager.ImsWfc — framework; API 33+ (0 +, 0 *, 0 ~, 0 -)

## CarrierConfigManager.Iwlan — framework; API 31+ (0 +, 0 *, 0 ~, 0 -)

## CellIdentity — declaration; API 28+ (6 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 28+]
+ describeContents(): int  [API 28+]
+ equals(Object): boolean  [API 28+]
+ getOperatorAlphaLong(): CharSequence  [API 28+]
+ getOperatorAlphaShort(): CharSequence  [API 28+]
+ hashCode(): int  [API 28+]
+ writeToParcel(Parcel, int): void  [API 28+]
```

## CellIdentityCdma — declaration; API 17+ (9 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 17+]
+ equals(Object): boolean  [API 17+]
+ getBasestationId(): int  [API 17+]
+ getLatitude(): int  [API 17+]
+ getLongitude(): int  [API 17+]
+ getNetworkId(): int  [API 17+]
+ getSystemId(): int  [API 17+]
+ hashCode(): int  [API 17+]
+ toString(): String  [API 17+]
+ writeToParcel(Parcel, int): void  [API 17+]
```

## CellIdentityGsm — declaration; API 17+ (15 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 17+]
+ equals(Object): boolean  [API 17+]
+ getAdditionalPlmns(): Set  [API 30+]
+ getArfcn(): int  [API 24+]
+ getBsic(): int  [API 24+]
+ getCid(): int  [API 17+]
+ getLac(): int  [API 17+]
+ getMcc(): int  [API 17+, deprecated in 28]
+ getMccString(): String  [API 28+]
+ getMnc(): int  [API 17+, deprecated in 28]
+ getMncString(): String  [API 28+]
+ getMobileNetworkOperator(): String  [API 28+]
+ getPsc(): int  [API 17+, deprecated in 18]
+ hashCode(): int  [API 17+]
+ toString(): String  [API 17+]
+ writeToParcel(Parcel, int): void  [API 17+]
```

## CellIdentityLte — declaration; API 17+ (17 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 17+]
+ equals(Object): boolean  [API 17+]
+ getAdditionalPlmns(): Set  [API 30+]
+ getBands(): int[]  [API 30+]
+ getBandwidth(): int  [API 28+]
+ getCi(): int  [API 17+]
+ getClosedSubscriberGroupInfo(): ClosedSubscriberGroupInfo  [API 30+]
+ getEarfcn(): int  [API 24+]
+ getMcc(): int  [API 17+, deprecated in 28]
+ getMccString(): String  [API 28+]
+ getMnc(): int  [API 17+, deprecated in 28]
+ getMncString(): String  [API 28+]
+ getMobileNetworkOperator(): String  [API 28+]
+ getPci(): int  [API 17+]
+ getTac(): int  [API 17+]
+ hashCode(): int  [API 17+]
+ toString(): String  [API 17+]
+ writeToParcel(Parcel, int): void  [API 17+]
```

## CellIdentityNr — declaration; API 29+ (12 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 29+]
+ equals(Object): boolean  [API 29+]
+ getAdditionalPlmns(): Set  [API 30+]
+ getBands(): int[]  [API 30+]
+ getMccString(): String  [API 29+]
+ getMncString(): String  [API 29+]
+ getNci(): long  [API 29+]
+ getNrarfcn(): int  [API 29+]
+ getPci(): int  [API 29+]
+ getTac(): int  [API 29+]
+ hashCode(): int  [API 29+]
+ toString(): String  [API 29+]
+ writeToParcel(Parcel, int): void  [API 29+]
```

## CellIdentityTdscdma — declaration; API 28+ (14 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 28+]
+ describeContents(): int  [API 28+]
+ equals(Object): boolean  [API 28+]
+ getAdditionalPlmns(): Set  [API 30+]
+ getCid(): int  [API 28+]
+ getClosedSubscriberGroupInfo(): ClosedSubscriberGroupInfo  [API 30+]
+ getCpid(): int  [API 28+]
+ getLac(): int  [API 28+]
+ getMccString(): String  [API 28+]
+ getMncString(): String  [API 28+]
+ getMobileNetworkOperator(): String  [API 29+]
+ getUarfcn(): int  [API 29+]
+ hashCode(): int  [API 28+]
+ toString(): String  [API 28+]
+ writeToParcel(Parcel, int): void  [API 28+]
```

## CellIdentityWcdma — declaration; API 18+ (15 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 18+]
+ equals(Object): boolean  [API 18+]
+ getAdditionalPlmns(): Set  [API 30+]
+ getCid(): int  [API 18+]
+ getClosedSubscriberGroupInfo(): ClosedSubscriberGroupInfo  [API 30+]
+ getLac(): int  [API 18+]
+ getMcc(): int  [API 18+, deprecated in 28]
+ getMccString(): String  [API 28+]
+ getMnc(): int  [API 18+, deprecated in 28]
+ getMncString(): String  [API 28+]
+ getMobileNetworkOperator(): String  [API 28+]
+ getPsc(): int  [API 18+]
+ getUarfcn(): int  [API 24+]
+ hashCode(): int  [API 18+]
+ toString(): String  [API 18+]
+ writeToParcel(Parcel, int): void  [API 18+]
```

## CellInfo — declaration; API 17+ (11 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 17+]
+ describeContents(): int  [API 17+]
+ equals(Object): boolean  [API 17+]
+ getCellConnectionStatus(): int  [API 28+]
+ getCellIdentity(): CellIdentity  [API 30+]
+ getCellSignalStrength(): CellSignalStrength  [API 30+]
+ getTimeStamp(): long  [API 17+, deprecated in 30]
+ getTimestampMillis(): long  [API 30+]
+ hashCode(): int  [API 17+]
+ isRegistered(): boolean  [API 17+]
+ toString(): String  [API 17+]
+ writeToParcel(Parcel, int): void  [API 17+]
```

## CellInfoCdma — declaration; API 17+ (7 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 17+]
+ describeContents(): int  [API 17+]
+ equals(Object): boolean  [API 17+]
+ getCellIdentity(): CellIdentityCdma  [API 17+]
+ getCellSignalStrength(): CellSignalStrengthCdma  [API 17+]
+ hashCode(): int  [API 17+]
+ toString(): String  [API 17+]
+ writeToParcel(Parcel, int): void  [API 17+]
```

## CellInfoGsm — declaration; API 17+ (7 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 17+]
+ describeContents(): int  [API 17+]
+ equals(Object): boolean  [API 17+]
+ getCellIdentity(): CellIdentityGsm  [API 17+]
+ getCellSignalStrength(): CellSignalStrengthGsm  [API 17+]
+ hashCode(): int  [API 17+]
+ toString(): String  [API 17+]
+ writeToParcel(Parcel, int): void  [API 17+]
```

## CellInfoLte — declaration; API 17+ (7 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 17+]
+ describeContents(): int  [API 17+]
+ equals(Object): boolean  [API 17+]
+ getCellIdentity(): CellIdentityLte  [API 17+]
+ getCellSignalStrength(): CellSignalStrengthLte  [API 17+]
+ hashCode(): int  [API 17+]
+ toString(): String  [API 17+]
+ writeToParcel(Parcel, int): void  [API 17+]
```

## CellInfoNr — declaration; API 29+ (6 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 29+]
+ equals(Object): boolean  [API 29+]
+ getCellIdentity(): CellIdentity  [API 29+]
+ getCellSignalStrength(): CellSignalStrength  [API 29+]
+ hashCode(): int  [API 29+]
+ toString(): String  [API 29+]
+ writeToParcel(Parcel, int): void  [API 29+]
```

## CellInfoTdscdma — declaration; API 29+ (7 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 29+]
+ describeContents(): int  [API 29+]
+ equals(Object): boolean  [API 29+]
+ getCellIdentity(): CellIdentityTdscdma  [API 29+]
+ getCellSignalStrength(): CellSignalStrengthTdscdma  [API 29+]
+ hashCode(): int  [API 29+]
+ toString(): String  [API 29+]
+ writeToParcel(Parcel, int): void  [API 29+]
```

## CellInfoWcdma — declaration; API 18+ (7 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 18+]
+ describeContents(): int  [API 18+]
+ equals(Object): boolean  [API 18+]
+ getCellIdentity(): CellIdentityWcdma  [API 18+]
+ getCellSignalStrength(): CellSignalStrengthWcdma  [API 18+]
+ hashCode(): int  [API 18+]
+ toString(): String  [API 18+]
+ writeToParcel(Parcel, int): void  [API 18+]
```

## CellLocation — declaration; API 1+, deprecated in 31 (2 +, 0 *, 0 ~, 1 -)

```
- CellLocation()  [API 1+]
+ getEmpty(): CellLocation [static]  [API 1+]
+ requestLocationUpdate(): void [static]  [API 1+]
```

## CellSignalStrength — declaration; API 17+ (5 +, 0 *, 0 ~, 0 -)

```
+ equals(Object): boolean  [API 17+]
+ getAsuLevel(): int  [API 17+]
+ getDbm(): int  [API 17+]
+ getLevel(): int  [API 17+]
+ hashCode(): int  [API 17+]
```

## CellSignalStrengthCdma — declaration; API 17+ (15 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 17+]
+ describeContents(): int  [API 17+]
+ equals(Object): boolean  [API 17+]
+ getAsuLevel(): int  [API 17+]
+ getCdmaDbm(): int  [API 17+]
+ getCdmaEcio(): int  [API 17+]
+ getCdmaLevel(): int  [API 17+]
+ getDbm(): int  [API 17+]
+ getEvdoDbm(): int  [API 17+]
+ getEvdoEcio(): int  [API 17+]
+ getEvdoLevel(): int  [API 17+]
+ getEvdoSnr(): int  [API 17+]
+ getLevel(): int  [API 17+]
+ hashCode(): int  [API 17+]
+ toString(): String  [API 17+]
+ writeToParcel(Parcel, int): void  [API 17+]
```

## CellSignalStrengthGsm — declaration; API 17+ (11 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 17+]
+ describeContents(): int  [API 17+]
+ equals(Object): boolean  [API 17+]
+ getAsuLevel(): int  [API 17+]
+ getBitErrorRate(): int  [API 29+]
+ getDbm(): int  [API 17+]
+ getLevel(): int  [API 17+]
+ getRssi(): int  [API 30+]
+ getTimingAdvance(): int  [API 26+]
+ hashCode(): int  [API 17+]
+ toString(): String  [API 17+]
+ writeToParcel(Parcel, int): void  [API 17+]
```

## CellSignalStrengthLte — declaration; API 17+ (15 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 17+]
+ describeContents(): int  [API 17+]
+ equals(Object): boolean  [API 17+]
+ getAsuLevel(): int  [API 17+]
+ getCqi(): int  [API 26+]
+ getCqiTableIndex(): int  [API 31+]
+ getDbm(): int  [API 17+]
+ getLevel(): int  [API 17+]
+ getRsrp(): int  [API 26+]
+ getRsrq(): int  [API 26+]
+ getRssi(): int  [API 29+]
+ getRssnr(): int  [API 26+]
+ getTimingAdvance(): int  [API 17+]
+ hashCode(): int  [API 17+]
+ toString(): String  [API 17+]
+ writeToParcel(Parcel, int): void  [API 17+]
```

## CellSignalStrengthNr — declaration; API 29+ (17 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 29+]
+ describeContents(): int  [API 29+]
+ equals(Object): boolean  [API 29+]
+ getAsuLevel(): int  [API 29+]
+ getCsiCqiReport(): List  [API 31+]
+ getCsiCqiTableIndex(): int  [API 31+]
+ getCsiRsrp(): int  [API 29+]
+ getCsiRsrq(): int  [API 29+]
+ getCsiSinr(): int  [API 29+]
+ getDbm(): int  [API 29+]
+ getLevel(): int  [API 29+]
+ getSsRsrp(): int  [API 29+]
+ getSsRsrq(): int  [API 29+]
+ getSsSinr(): int  [API 29+]
+ getTimingAdvanceMicros(): int  [API 34+]
+ hashCode(): int  [API 29+]
+ toString(): String  [API 29+]
+ writeToParcel(Parcel, int): void  [API 29+]
```

## CellSignalStrengthTdscdma — declaration; API 29+ (9 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 29+]
+ describeContents(): int  [API 29+]
+ equals(Object): boolean  [API 29+]
+ getAsuLevel(): int  [API 29+]
+ getDbm(): int  [API 29+]
+ getLevel(): int  [API 29+]
+ getRscp(): int  [API 29+]
+ hashCode(): int  [API 29+]
+ toString(): String  [API 29+]
+ writeToParcel(Parcel, int): void  [API 29+]
```

## CellSignalStrengthWcdma — declaration; API 18+ (9 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 18+]
+ describeContents(): int  [API 18+]
+ equals(Object): boolean  [API 18+]
+ getAsuLevel(): int  [API 18+]
+ getDbm(): int  [API 18+]
+ getEcNo(): int  [API 30+]
+ getLevel(): int  [API 18+]
+ hashCode(): int  [API 18+]
+ toString(): String  [API 18+]
+ writeToParcel(Parcel, int): void  [API 18+]
```

## ClosedSubscriberGroupInfo — declaration; API 30+ (8 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 30+]
+ describeContents(): int  [API 30+]
+ equals(Object): boolean  [API 30+]
+ getCsgIdentity(): int  [API 30+]
+ getCsgIndicator(): boolean  [API 30+]
+ getHomeNodebName(): String  [API 30+]
+ hashCode(): int  [API 30+]
+ toString(): String  [API 30+]
+ writeToParcel(Parcel, int): void  [API 30+]
```

## DataFailCause — declaration; API 30+ (0 +, 0 *, 0 ~, 0 -)

## DisconnectCause — declaration; API 31+ (0 +, 0 *, 0 ~, 0 -)

## IccOpenLogicalChannelResponse — declaration; API 21+ (6 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 21+]
+ describeContents(): int  [API 21+]
+ getChannel(): int  [API 21+]
+ getSelectResponse(): byte[]  [API 21+]
+ getStatus(): int  [API 21+]
+ toString(): String  [API 21+]
+ writeToParcel(Parcel, int): void  [API 21+]
```

## MbmsDownloadSession — declaration; API 28+ (17 +, 0 *, 0 ~, 0 -)

```
+ addProgressListener(DownloadRequest, Executor, DownloadProgressListener): void  [API 28+]
+ addServiceAnnouncement(byte[]): void  [API 31+]
+ addStatusListener(DownloadRequest, Executor, DownloadStatusListener): void  [API 28+]
+ cancelDownload(DownloadRequest): void  [API 28+]
+ close(): void  [API 28+]
+ create(Context, Executor, MbmsDownloadSessionCallback): MbmsDownloadSession [static]  [API 28+]
+ create(Context, Executor, int, MbmsDownloadSessionCallback): MbmsDownloadSession [static]  [API 28+]
+ download(DownloadRequest): void  [API 28+]
+ getMaximumServiceAnnouncementSize(): int [static]  [API 31+]
+ getTempFileRootDirectory(): File  [API 28+]
+ listPendingDownloads(): List  [API 28+]
+ removeProgressListener(DownloadRequest, DownloadProgressListener): void  [API 28+]
+ removeStatusListener(DownloadRequest, DownloadStatusListener): void  [API 28+]
+ requestDownloadState(DownloadRequest, FileInfo): void  [API 28+]
+ requestUpdateFileServices(List): void  [API 28+]
+ resetDownloadKnowledge(DownloadRequest): void  [API 28+]
+ setTempFileRootDirectory(File): void  [API 28+]
```

## MbmsGroupCallSession — declaration; API 29+ (4 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 29+]
+ create(Context, Executor, MbmsGroupCallSessionCallback): MbmsGroupCallSession [static]  [API 29+]
+ create(Context, int, Executor, MbmsGroupCallSessionCallback): MbmsGroupCallSession [static]  [API 29+]
+ startGroupCall(long, List, List, Executor, GroupCallCallback): GroupCall  [API 29+]
```

## MbmsStreamingSession — declaration; API 28+ (5 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 28+]
+ create(Context, Executor, MbmsStreamingSessionCallback): MbmsStreamingSession [static]  [API 28+]
+ create(Context, Executor, int, MbmsStreamingSessionCallback): MbmsStreamingSession [static]  [API 28+]
+ requestUpdateStreamingServices(List): void  [API 28+]
+ startStreaming(StreamingServiceInfo, Executor, StreamingServiceCallback): StreamingService  [API 28+]
```

## NeighboringCellInfo — declaration; API 3+, deprecated in 29 (6 +, 6 *, 0 ~, 2 -)

```
- CREATOR [field]  [API 3+]
* NeighboringCellInfo()  [API 3+, deprecated in 15]
- NeighboringCellInfo(Parcel)  [API 3+]
- NeighboringCellInfo(int, String, int)  [API 7+]
* NeighboringCellInfo(int, int)  [API 3+, deprecated in 15]
+ describeContents(): int  [API 3+]
* getCid(): int  [API 3+]
+ getLac(): int  [API 5+]
+ getNetworkType(): int  [API 5+]
+ getPsc(): int  [API 5+]
* getRssi(): int  [API 3+]
* setCid(int): void  [API 3+, deprecated in 15]
* setRssi(int): void  [API 3+, deprecated in 15]
+ toString(): String  [API 3+]
+ writeToParcel(Parcel, int): void  [API 3+]
```

## NetworkRegistrationInfo — declaration; API 30+ (19 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 30+]
+ describeContents(): int  [API 30+]
+ equals(Object): boolean  [API 30+]
+ getAccessNetworkTechnology(): int  [API 30+]
+ getAvailableServices(): List  [API 30+]
+ getCellIdentity(): CellIdentity  [API 30+]
+ getDomain(): int  [API 30+]
+ getRegisteredPlmn(): String  [API 30+]
+ getRejectCause(): int  [API 35+]
+ getTransportType(): int  [API 30+]
+ hashCode(): int  [API 30+]
+ isNetworkRegistered(): boolean  [API 34+]
+ isNetworkRoaming(): boolean  [API 34+]
+ isNetworkSearching(): boolean  [API 34+]
+ isNonTerrestrialNetwork(): boolean  [API 35+]
+ isRegistered(): boolean  [API 30+, deprecated in 34]
+ isRoaming(): boolean  [API 30+, deprecated in 34]
+ isSearching(): boolean  [API 30+, deprecated in 34]
+ toString(): String  [API 30+]
+ writeToParcel(Parcel, int): void  [API 30+]
```

## NetworkScan — declaration; API 28+ (1 +, 0 *, 0 ~, 0 -)

```
+ stopScan(): void  [API 28+]
```

## NetworkScanRequest — declaration; API 28+ (11 +, 0 *, 0 ~, 1 -)

```
- CREATOR [field]  [API 28+]
- NetworkScanRequest(int, RadioAccessSpecifier[], int, int, boolean, int, ArrayList)  [API 28+]
+ describeContents(): int  [API 28+]
+ equals(Object): boolean  [API 28+]
+ getIncrementalResults(): boolean  [API 28+]
+ getIncrementalResultsPeriodicity(): int  [API 28+]
+ getMaxSearchTime(): int  [API 28+]
+ getPlmns(): ArrayList  [API 28+]
+ getScanType(): int  [API 28+]
+ getSearchPeriodicity(): int  [API 28+]
+ getSpecifiers(): RadioAccessSpecifier[]  [API 28+]
+ hashCode(): int  [API 28+]
+ writeToParcel(Parcel, int): void  [API 28+]
```

## PhoneNumberFormattingTextWatcher — declaration; API 1+, deprecated in 35 (3 +, 0 *, 0 ~, 2 -)

```
- PhoneNumberFormattingTextWatcher()  [API 1+]
- PhoneNumberFormattingTextWatcher(String)  [API 21+]
+ afterTextChanged(Editable): void  [API 1+]
+ beforeTextChanged(CharSequence, int, int, int): void  [API 1+]
+ onTextChanged(CharSequence, int, int, int): void  [API 1+]
```

## PhoneNumberUtils — declaration; API 1+ (46 +, 0 *, 0 ~, 1 -)

```
- PhoneNumberUtils()  [API 1+]
+ addTtsSpan(Spannable, int, int): void [static]  [API 23+]
+ areSamePhoneNumber(String, String, String): boolean [static]  [API 31+]
+ calledPartyBCDFragmentToString(byte[], int, int): String [static]  [API 1+, deprecated in 28]
+ calledPartyBCDFragmentToString(byte[], int, int, int): String [static]  [API 28+]
+ calledPartyBCDToString(byte[], int, int): String [static]  [API 1+, deprecated in 28]
+ calledPartyBCDToString(byte[], int, int, int): String [static]  [API 28+]
+ compare(Context, String, String): boolean [static]  [API 5+, deprecated in 31]
+ compare(String, String): boolean [static]  [API 1+, deprecated in 31]
+ convertKeypadLettersToDigits(String): String [static]  [API 1+]
+ createTtsSpan(String): TtsSpan [static]  [API 23+]
+ createTtsSpannable(CharSequence): CharSequence [static]  [API 23+]
+ extractNetworkPortion(String): String [static]  [API 1+]
+ extractPostDialPortion(String): String [static]  [API 1+]
+ formatJapaneseNumber(Editable): void [static]  [API 3+, deprecated in 21]
+ formatNanpNumber(Editable): void [static]  [API 1+, deprecated in 21]
+ formatNumber(Editable, int): void [static]  [API 1+, deprecated in 21]
+ formatNumber(String): String [static]  [API 1+, deprecated in 21]
+ formatNumber(String, String): String [static]  [API 21+]
+ formatNumber(String, String, String): String [static]  [API 21+]
+ formatNumberToE164(String, String): String [static]  [API 21+]
+ formatNumberToRFC3966(String, String): String [static]  [API 23+]
+ getFormatTypeForLocale(Locale): int [static]  [API 1+, deprecated in 21]
+ getNumberFromIntent(Intent, Context): String [static]  [API 1+]
+ getStrippedReversed(String): String [static]  [API 1+]
+ is12Key(char): boolean [static]  [API 1+]
+ isDialable(char): boolean [static]  [API 1+]
+ isEmergencyNumber(String): boolean [static]  [API 1+, deprecated in 29]
+ isGlobalPhoneNumber(String): boolean [static]  [API 1+]
+ isISODigit(char): boolean [static]  [API 1+]
+ isLocalEmergencyNumber(Context, String): boolean [static]  [API 21+, deprecated in 29]
+ isNonSeparator(char): boolean [static]  [API 1+]
+ isReallyDialable(char): boolean [static]  [API 1+]
+ isStartsPostDial(char): boolean [static]  [API 1+]
+ isVoiceMailNumber(String): boolean [static]  [API 21+]
+ isWellFormedSmsAddress(String): boolean [static]  [API 1+]
+ isWpsCallNumber(String): boolean [static]  [API 35+]
+ networkPortionToCalledPartyBCD(String): byte[] [static]  [API 1+]
+ networkPortionToCalledPartyBCDWithLength(String): byte[] [static]  [API 1+]
+ normalizeNumber(String): String [static]  [API 21+]
+ numberToCalledPartyBCD(String): byte[] [static]  [API 1+, deprecated in 28]
+ numberToCalledPartyBCD(String, int): byte[] [static]  [API 28+]
+ replaceUnicodeDigits(String): String [static]  [API 21+]
+ stringFromStringAndTOA(String, int): String [static]  [API 1+]
+ stripSeparators(String): String [static]  [API 1+]
+ toCallerIDMinMatch(String): String [static]  [API 1+]
+ toaFromString(String): int [static]  [API 1+]
```

## PhoneStateListener — declaration; API 1+, deprecated in 31 (21 +, 0 *, 0 ~, 1 -)

```
+ PhoneStateListener()  [API 1+]
- PhoneStateListener(Executor)  [API 29+]
+ onActiveDataSubscriptionIdChanged(int): void  [API 29+]
+ onBarringInfoChanged(BarringInfo): void  [API 30+]
+ onCallDisconnectCauseChanged(int, int): void  [API 30+]
+ onCallForwardingIndicatorChanged(boolean): void  [API 1+]
+ onCallStateChanged(int, String): void  [API 1+]
+ onCellInfoChanged(List): void  [API 17+]
+ onCellLocationChanged(CellLocation): void  [API 1+]
+ onDataActivity(int): void  [API 1+]
+ onDataConnectionStateChanged(int): void  [API 1+]
+ onDataConnectionStateChanged(int, int): void  [API 7+]
+ onDisplayInfoChanged(TelephonyDisplayInfo): void  [API 30+]
+ onEmergencyNumberListChanged(Map): void  [API 31+]
+ onImsCallDisconnectCauseChanged(ImsReasonInfo): void  [API 30+]
+ onMessageWaitingIndicatorChanged(boolean): void  [API 1+]
+ onPreciseDataConnectionStateChanged(PreciseDataConnectionState): void  [API 30+]
+ onRegistrationFailed(CellIdentity, String, int, int, int): void  [API 30+]
+ onServiceStateChanged(ServiceState): void  [API 1+]
+ onSignalStrengthChanged(int): void  [API 1+, deprecated in 15]
+ onSignalStrengthsChanged(SignalStrength): void  [API 7+]
+ onUserMobileDataStateChanged(boolean): void  [API 28+]
```

## PhysicalChannelConfig — declaration; API 31+ (15 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 31+]
+ describeContents(): int  [API 31+]
+ equals(Object): boolean  [API 31+]
+ getBand(): int  [API 31+]
+ getCellBandwidthDownlinkKhz(): int  [API 31+]
+ getCellBandwidthUplinkKhz(): int  [API 31+]
+ getConnectionStatus(): int  [API 31+]
+ getDownlinkChannelNumber(): int  [API 31+]
+ getDownlinkFrequencyKhz(): int  [API 31+]
+ getNetworkType(): int  [API 31+]
+ getPhysicalCellId(): int  [API 31+]
+ getUplinkChannelNumber(): int  [API 31+]
+ getUplinkFrequencyKhz(): int  [API 31+]
+ hashCode(): int  [API 31+]
+ toString(): String  [API 31+]
+ writeToParcel(Parcel, int): void  [API 31+]
```

## PreciseDataConnectionState — declaration; API 30+ (13 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 30+]
+ describeContents(): int  [API 30+]
+ equals(Object): boolean  [API 30+]
+ getApnSetting(): ApnSetting  [API 30+]
+ getId(): int  [API 31+]
+ getLastCauseCode(): int  [API 30+]
+ getLinkProperties(): LinkProperties  [API 30+]
+ getNetworkType(): int  [API 30+]
+ getNetworkValidationStatus(): int  [API 35+]
+ getState(): int  [API 30+]
+ getTransportType(): int  [API 31+]
+ hashCode(): int  [API 30+]
+ toString(): String  [API 30+]
+ writeToParcel(Parcel, int): void  [API 30+]
```

## RadioAccessSpecifier — declaration; API 28+ (8 +, 0 *, 0 ~, 1 -)

```
- CREATOR [field]  [API 28+]
- RadioAccessSpecifier(int, int[], int[])  [API 28+]
+ describeContents(): int  [API 28+]
+ equals(Object): boolean  [API 28+]
+ getBands(): int[]  [API 28+]
+ getChannels(): int[]  [API 28+]
+ getRadioAccessNetwork(): int  [API 28+]
+ hashCode(): int  [API 28+]
+ toString(): String  [API 28+]
+ writeToParcel(Parcel, int): void  [API 28+]
```

## ServiceState — declaration; API 1+ (20 +, 7 *, 0 ~, 2 -)

```
- CREATOR [field]  [API 1+]
* ServiceState()  [API 1+]
- ServiceState(Parcel)  [API 1+, deprecated in 29]
- ServiceState(ServiceState)  [API 1+]
+ copyFrom(ServiceState): void  [API 1+]
+ describeContents(): int  [API 1+]
+ equals(Object): boolean  [API 1+]
+ getCdmaNetworkId(): int  [API 28+]
+ getCdmaSystemId(): int  [API 28+]
+ getCellBandwidths(): int[]  [API 28+]
+ getChannelNumber(): int  [API 28+]
+ getDuplexMode(): int  [API 28+]
* getIsManualSelection(): boolean  [API 1+]
+ getNetworkRegistrationInfoList(): List  [API 30+]
+ getOperatorAlphaLong(): String  [API 1+]
+ getOperatorAlphaShort(): String  [API 1+]
+ getOperatorNumeric(): String  [API 1+]
* getRoaming(): boolean  [API 1+]
* getState(): int  [API 1+]
+ hashCode(): int  [API 1+]
+ isSearching(): boolean  [API 30+]
+ isUsingNonTerrestrialNetwork(): boolean  [API 35+]
* setIsManualSelection(boolean): void  [API 1+]
+ setOperatorName(String, String, String): void  [API 1+]
* setRoaming(boolean): void  [API 1+]
* setState(int): void  [API 1+]
+ setStateOff(): void  [API 1+]
+ setStateOutOfService(): void  [API 1+]
+ toString(): String  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## SignalStrength — declaration; API 7+ (17 +, 0 *, 0 ~, 1 -)

```
- CREATOR [field]  [API 30+]
- SignalStrength(SignalStrength)  [API 31+]
+ describeContents(): int  [API 7+]
+ equals(Object): boolean  [API 7+]
+ getCdmaDbm(): int  [API 7+, deprecated in 29]
+ getCdmaEcio(): int  [API 7+, deprecated in 29]
+ getCellSignalStrengths(): List  [API 29+]
+ getCellSignalStrengths(Class): List  [API 29+]
+ getEvdoDbm(): int  [API 7+, deprecated in 29]
+ getEvdoEcio(): int  [API 7+, deprecated in 29]
+ getEvdoSnr(): int  [API 7+, deprecated in 29]
+ getGsmBitErrorRate(): int  [API 7+, deprecated in 29]
+ getGsmSignalStrength(): int  [API 7+, deprecated in 29]
+ getLevel(): int  [API 23+]
+ getTimestampMillis(): long  [API 30+]
+ hashCode(): int  [API 7+]
+ isGsm(): boolean  [API 7+, deprecated in 29]
+ toString(): String  [API 7+]
+ writeToParcel(Parcel, int): void  [API 7+]
```

## SignalStrengthUpdateRequest — declaration; API 31+ (0 +, 7 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 31+]
* describeContents(): int  [API 31+]
* equals(Object): boolean  [API 31+]
* getSignalThresholdInfos(): Collection  [API 31+]
* hashCode(): int  [API 31+]
* isReportingRequestedWhileIdle(): boolean  [API 31+]
* toString(): String  [API 31+]
* writeToParcel(Parcel, int): void  [API 31+]
```

## SignalStrengthUpdateRequest.Builder — declaration; API 31+ (0 +, 4 *, 0 ~, 0 -)

```
* Builder()  [API 31+]
* build(): SignalStrengthUpdateRequest  [API 31+]
* setReportingRequestedWhileIdle(boolean): SignalStrengthUpdateRequest.Builder  [API 31+]
* setSignalThresholdInfos(Collection): SignalStrengthUpdateRequest.Builder  [API 31+]
```

## SignalThresholdInfo — declaration; API 31+ (2 +, 9 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 31+]
* describeContents(): int  [API 31+]
* equals(Object): boolean  [API 31+]
* getHysteresisDb(): int  [API 34+]
+ getMaximumNumberOfThresholdsAllowed(): int [static]  [API 31+]
+ getMinimumNumberOfThresholdsAllowed(): int [static]  [API 31+]
* getRadioAccessNetworkType(): int  [API 31+]
* getSignalMeasurementType(): int  [API 31+]
* getThresholds(): int[]  [API 31+]
* hashCode(): int  [API 31+]
* toString(): String  [API 31+]
* writeToParcel(Parcel, int): void  [API 31+]
```

## SignalThresholdInfo.Builder — declaration; API 31+ (0 +, 6 *, 0 ~, 0 -)

```
* Builder()  [API 31+]
* build(): SignalThresholdInfo  [API 31+]
* setHysteresisDb(int): SignalThresholdInfo.Builder  [API 34+]
* setRadioAccessNetworkType(int): SignalThresholdInfo.Builder  [API 31+]
* setSignalMeasurementType(int): SignalThresholdInfo.Builder  [API 31+]
* setThresholds(int[]): SignalThresholdInfo.Builder  [API 31+]
```

## SmsManager — declaration; API 4+ (25 +, 0 *, 0 ~, 0 -)

```
+ createAppSpecificSmsToken(PendingIntent): String  [API 26+]
+ createAppSpecificSmsTokenWithPackageInfo(String, PendingIntent): String  [API 29+]
+ createForSubscriptionId(int): SmsManager  [API 31+]
+ divideMessage(String): ArrayList  [API 4+]
+ downloadMultimediaMessage(Context, String, Uri, Bundle, PendingIntent): void  [API 21+]
+ downloadMultimediaMessage(Context, String, Uri, Bundle, PendingIntent, long): void  [API 31+]
+ getCarrierConfigValues(): Bundle  [API 21+]
+ getDefault(): SmsManager [static]  [API 4+, deprecated in 31]
+ getDefaultSmsSubscriptionId(): int [static]  [API 22+]
+ getSmsCapacityOnIcc(): int  [API 31+]
+ getSmsManagerForSubscriptionId(int): SmsManager [static]  [API 22+, deprecated in 31]
+ getSmsMessagesForFinancialApp(Bundle, Executor, SmsManager.FinancialSmsCallback): void  [API 29+]
+ getSmscAddress(): String  [API 30+]
+ getSubscriptionId(): int  [API 22+]
+ injectSmsPdu(byte[], String, PendingIntent): void  [API 22+]
+ sendDataMessage(String, String, short, byte[], PendingIntent, PendingIntent): void  [API 4+]
+ sendMultimediaMessage(Context, Uri, String, Bundle, PendingIntent): void  [API 21+]
+ sendMultimediaMessage(Context, Uri, String, Bundle, PendingIntent, long): void  [API 31+]
+ sendMultipartTextMessage(String, String, ArrayList, ArrayList, ArrayList): void  [API 4+]
+ sendMultipartTextMessage(String, String, List, List, List, String, String): void  [API 30+]
+ sendMultipartTextMessage(String, String, List, List, List, long): void  [API 30+]
+ sendTextMessage(String, String, String, PendingIntent, PendingIntent): void  [API 4+]
+ sendTextMessage(String, String, String, PendingIntent, PendingIntent, long): void  [API 30+]
+ sendTextMessageWithoutPersisting(String, String, String, PendingIntent, PendingIntent): void  [API 28+]
+ setSmscAddress(String): boolean  [API 30+]
```

## SmsManager.FinancialSmsCallback — declaration; API 29+ (2 +, 0 *, 0 ~, 0 -)

```
+ FinancialSmsCallback()  [API 29+]
+ onFinancialSmsMessages(CursorWindow): void  [API 29+]
```

## SmsMessage — declaration; API 4+ (33 +, 0 *, 0 ~, 0 -)

```
+ calculateLength(CharSequence, boolean): int[] [static]  [API 4+]
+ calculateLength(String, boolean): int[] [static]  [API 4+]
+ createFromPdu(byte[]): SmsMessage [static]  [API 4+, deprecated in 23]
+ createFromPdu(byte[], String): SmsMessage [static]  [API 23+]
+ getDisplayMessageBody(): String  [API 4+]
+ getDisplayOriginatingAddress(): String  [API 4+]
+ getEmailBody(): String  [API 4+]
+ getEmailFrom(): String  [API 4+]
+ getIndexOnIcc(): int  [API 4+]
+ getIndexOnSim(): int  [API 4+, deprecated in 15]
+ getMessageBody(): String  [API 4+]
+ getMessageClass(): SmsMessage.MessageClass  [API 4+]
+ getOriginatingAddress(): String  [API 4+]
+ getPdu(): byte[]  [API 4+]
+ getProtocolIdentifier(): int  [API 4+]
+ getPseudoSubject(): String  [API 4+]
+ getServiceCenterAddress(): String  [API 4+]
+ getStatus(): int  [API 4+]
+ getStatusOnIcc(): int  [API 4+]
+ getStatusOnSim(): int  [API 4+, deprecated in 15]
+ getSubmitPdu(String, String, String, boolean): SmsMessage.SubmitPdu [static]  [API 4+]
+ getSubmitPdu(String, String, short, byte[], boolean): SmsMessage.SubmitPdu [static]  [API 4+]
+ getTPLayerLengthForPDU(String): int [static]  [API 4+]
+ getTimestampMillis(): long  [API 4+]
+ getUserData(): byte[]  [API 4+]
+ isCphsMwiMessage(): boolean  [API 4+]
+ isEmail(): boolean  [API 4+]
+ isMWIClearMessage(): boolean  [API 4+]
+ isMWISetMessage(): boolean  [API 4+]
+ isMwiDontStore(): boolean  [API 4+]
+ isReplace(): boolean  [API 4+]
+ isReplyPathPresent(): boolean  [API 4+]
+ isStatusReportMessage(): boolean  [API 4+]
```

## SmsMessage.MessageClass — declaration; API 4+ (2 +, 0 *, 0 ~, 0 -)

```
+ CLASS_0 [field]  [API 4+]
+ CLASS_1 [field]  [API 4+]
+ CLASS_2 [field]  [API 4+]
+ CLASS_3 [field]  [API 4+]
+ UNKNOWN [field]  [API 4+]
+ valueOf(String): SmsMessage.MessageClass [static]  [API 4+]
+ values(): SmsMessage.MessageClass[] [static]  [API 4+]
```

## SmsMessage.SubmitPdu — declaration; API 4+ (1 +, 0 *, 0 ~, 0 -)

```
+ encodedMessage [field]  [API 4+]
+ encodedScAddress [field]  [API 4+]
+ toString(): String  [API 4+]
```

## SubscriptionInfo — declaration; API 22+ (29 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 22+]
+ createIconBitmap(Context): Bitmap  [API 22+]
+ describeContents(): int  [API 22+]
+ equals(Object): boolean  [API 22+]
+ getCardId(): int  [API 29+]
+ getCarrierId(): int  [API 29+]
+ getCarrierName(): CharSequence  [API 22+]
+ getCountryIso(): String  [API 22+]
+ getDataRoaming(): int  [API 22+]
+ getDisplayName(): CharSequence  [API 22+]
+ getGroupUuid(): ParcelUuid  [API 29+]
+ getIccId(): String  [API 22+]
+ getIconTint(): int  [API 22+]
+ getMcc(): int  [API 22+, deprecated in 29]
+ getMccString(): String  [API 29+]
+ getMnc(): int  [API 22+, deprecated in 29]
+ getMncString(): String  [API 29+]
+ getNumber(): String  [API 22+, deprecated in 33]
+ getPortIndex(): int  [API 33+]
+ getServiceCapabilities(): Set  [API 35+]
+ getSimSlotIndex(): int  [API 22+]
+ getSubscriptionId(): int  [API 22+]
+ getSubscriptionType(): int  [API 29+]
+ getUsageSetting(): int  [API 33+]
+ hashCode(): int  [API 22+]
+ isEmbedded(): boolean  [API 28+]
+ isOnlyNonTerrestrialNetwork(): boolean  [API 35+]
+ isOpportunistic(): boolean  [API 29+]
+ toString(): String  [API 22+]
+ writeToParcel(Parcel, int): void  [API 22+]
```

## SubscriptionManager — framework; API 22+ (48 +, 0 *, 0 ~, 0 -)

```
+ addOnOpportunisticSubscriptionsChangedListener(Executor, SubscriptionManager.OnOpportunisticSubscriptionsChangedListener): void  [API 29+]
+ addOnSubscriptionsChangedListener(Executor, SubscriptionManager.OnSubscriptionsChangedListener): void  [API 30+]
+ addOnSubscriptionsChangedListener(SubscriptionManager.OnSubscriptionsChangedListener): void  [API 22+, deprecated in 31]
+ addSubscriptionsIntoGroup(List, ParcelUuid): void  [API 29+]
+ canManageSubscription(SubscriptionInfo): boolean  [API 28+]
+ createSubscriptionGroup(List): ParcelUuid  [API 29+]
+ from(Context): SubscriptionManager [static]  [API 22+, deprecated in 28]
+ getAccessibleSubscriptionInfoList(): List  [API 28+]
+ getActiveDataSubscriptionId(): int [static]  [API 30+]
+ getActiveSubscriptionInfo(int): SubscriptionInfo  [API 22+]
+ getActiveSubscriptionInfoCount(): int  [API 22+]
+ getActiveSubscriptionInfoCountMax(): int  [API 22+]
+ getActiveSubscriptionInfoForSimSlotIndex(int): SubscriptionInfo  [API 22+]
+ getActiveSubscriptionInfoList(): List  [API 22+]
+ getAllSubscriptionInfoList(): List  [API 34+]
+ getCompleteActiveSubscriptionInfoList(): List  [API 30+]
+ getDefaultDataSubscriptionId(): int [static]  [API 24+]
+ getDefaultSmsSubscriptionId(): int [static]  [API 24+]
+ getDefaultSubscriptionId(): int [static]  [API 24+]
+ getDefaultVoiceSubscriptionId(): int [static]  [API 24+]
+ getDeviceToDeviceStatusSharingContacts(int): List  [API 31+]
+ getDeviceToDeviceStatusSharingPreference(int): int  [API 31+]
+ getOpportunisticSubscriptions(): List  [API 29+]
+ getPhoneNumber(int): String  [API 33+]
+ getPhoneNumber(int, int): String  [API 33+]
+ getSlotIndex(int): int [static]  [API 29+]
+ getSubscriptionId(int): int [static]  [API 34+]
+ getSubscriptionIds(int): int[]  [API 29+, deprecated in 34]
+ getSubscriptionPlans(int): List  [API 28+]
+ getSubscriptionsInGroup(ParcelUuid): List  [API 29+]
+ isActiveSubscriptionId(int): boolean  [API 29+]
+ isNetworkRoaming(int): boolean  [API 22+]
+ isUsableSubscriptionId(int): boolean [static]  [API 29+]
+ isValidSubscriptionId(int): boolean [static]  [API 29+]
+ removeOnOpportunisticSubscriptionsChangedListener(SubscriptionManager.OnOpportunisticSubscriptionsChangedListener): void  [API 29+]
+ removeOnSubscriptionsChangedListener(SubscriptionManager.OnSubscriptionsChangedListener): void  [API 22+]
+ removeSubscriptionsFromGroup(List, ParcelUuid): void  [API 29+]
+ setCarrierPhoneNumber(int, String): void  [API 33+]
+ setDeviceToDeviceStatusSharingContacts(int, List): void  [API 31+]
+ setDeviceToDeviceStatusSharingPreference(int, int): void  [API 31+]
+ setOpportunistic(boolean, int): boolean  [API 29+]
+ setSubscriptionOverrideCongested(int, boolean, int[], long): void  [API 31+]
+ setSubscriptionOverrideCongested(int, boolean, long): void  [API 28+]
+ setSubscriptionOverrideUnmetered(int, boolean, int[], long): void  [API 31+]
+ setSubscriptionOverrideUnmetered(int, boolean, long): void  [API 28+]
+ setSubscriptionPlans(int, List): void  [API 28+, deprecated in 33]
+ setSubscriptionPlans(int, List, long): void  [API 33+]
+ switchToSubscription(int, PendingIntent): void  [API 29+, deprecated in 34]
```

## SubscriptionManager.OnOpportunisticSubscriptionsChangedListener — framework; API 29+ (2 +, 0 *, 0 ~, 0 -)

```
+ OnOpportunisticSubscriptionsChangedListener()  [API 29+]
+ onOpportunisticSubscriptionsChanged(): void  [API 29+]
```

## SubscriptionManager.OnSubscriptionsChangedListener — framework; API 22+ (2 +, 0 *, 0 ~, 0 -)

```
+ OnSubscriptionsChangedListener()  [API 22+]
+ onSubscriptionsChanged(): void  [API 22+]
```

## SubscriptionPlan — declaration; API 28+ (10 +, 3 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 28+]
+ cycleIterator(): Iterator  [API 28+]
+ describeContents(): int  [API 28+]
+ equals(Object): boolean  [API 28+]
+ getDataLimitBehavior(): int  [API 28+]
+ getDataLimitBytes(): long  [API 28+]
+ getDataUsageBytes(): long  [API 28+]
+ getDataUsageTime(): long  [API 28+]
* getNetworkTypes(): int[]  [API 30+]
* getSummary(): CharSequence  [API 28+]
* getTitle(): CharSequence  [API 28+]
+ hashCode(): int  [API 28+]
+ toString(): String  [API 28+]
+ writeToParcel(Parcel, int): void  [API 28+]
```

## SubscriptionPlan.Builder — declaration; API 28+ (5 +, 4 *, 0 ~, 0 -)

```
* build(): SubscriptionPlan  [API 28+]
+ createNonrecurring(ZonedDateTime, ZonedDateTime): SubscriptionPlan.Builder [static]  [API 28+]
+ createRecurring(ZonedDateTime, Period): SubscriptionPlan.Builder [static]  [API 28+]
+ resetNetworkTypes(): SubscriptionPlan.Builder  [API 30+]
+ setDataLimit(long, int): SubscriptionPlan.Builder  [API 28+]
+ setDataUsage(long, long): SubscriptionPlan.Builder  [API 28+]
* setNetworkTypes(int[]): SubscriptionPlan.Builder  [API 30+]
* setSummary(CharSequence): SubscriptionPlan.Builder  [API 28+]
* setTitle(CharSequence): SubscriptionPlan.Builder  [API 28+]
```

## TelephonyCallback — declaration; API 31+ (1 +, 0 *, 0 ~, 0 -)

```
+ TelephonyCallback()  [API 31+]
```

## TelephonyCallback.ActiveDataSubscriptionIdListener — declaration; API 31+ (1 +, 0 *, 0 ~, 0 -)

```
+ onActiveDataSubscriptionIdChanged(int): void  [API 31+]
```

## TelephonyCallback.BarringInfoListener — declaration; API 31+ (1 +, 0 *, 0 ~, 0 -)

```
+ onBarringInfoChanged(BarringInfo): void  [API 31+]
```

## TelephonyCallback.CallDisconnectCauseListener — declaration; API 31+ (1 +, 0 *, 0 ~, 0 -)

```
+ onCallDisconnectCauseChanged(int, int): void  [API 31+]
```

## TelephonyCallback.CallForwardingIndicatorListener — declaration; API 31+ (1 +, 0 *, 0 ~, 0 -)

```
+ onCallForwardingIndicatorChanged(boolean): void  [API 31+]
```

## TelephonyCallback.CallStateListener — declaration; API 31+ (1 +, 0 *, 0 ~, 0 -)

```
+ onCallStateChanged(int): void  [API 31+]
```

## TelephonyCallback.CarrierNetworkListener — declaration; API 31+ (1 +, 0 *, 0 ~, 0 -)

```
+ onCarrierNetworkChange(boolean): void  [API 31+]
```

## TelephonyCallback.CellInfoListener — declaration; API 31+ (1 +, 0 *, 0 ~, 0 -)

```
+ onCellInfoChanged(List): void  [API 31+]
```

## TelephonyCallback.CellLocationListener — declaration; API 31+ (1 +, 0 *, 0 ~, 0 -)

```
+ onCellLocationChanged(CellLocation): void  [API 31+]
```

## TelephonyCallback.DataActivationStateListener — declaration; API 31+ (1 +, 0 *, 0 ~, 0 -)

```
+ onDataActivationStateChanged(int): void  [API 31+]
```

## TelephonyCallback.DataActivityListener — declaration; API 31+ (1 +, 0 *, 0 ~, 0 -)

```
+ onDataActivity(int): void  [API 31+]
```

## TelephonyCallback.DataConnectionStateListener — declaration; API 31+ (1 +, 0 *, 0 ~, 0 -)

```
+ onDataConnectionStateChanged(int, int): void  [API 31+]
```

## TelephonyCallback.DisplayInfoListener — declaration; API 31+ (1 +, 0 *, 0 ~, 0 -)

```
+ onDisplayInfoChanged(TelephonyDisplayInfo): void  [API 31+]
```

## TelephonyCallback.EmergencyNumberListListener — declaration; API 31+ (1 +, 0 *, 0 ~, 0 -)

```
+ onEmergencyNumberListChanged(Map): void  [API 31+]
```

## TelephonyCallback.ImsCallDisconnectCauseListener — declaration; API 31+ (1 +, 0 *, 0 ~, 0 -)

```
+ onImsCallDisconnectCauseChanged(ImsReasonInfo): void  [API 31+]
```

## TelephonyCallback.MessageWaitingIndicatorListener — declaration; API 31+ (1 +, 0 *, 0 ~, 0 -)

```
+ onMessageWaitingIndicatorChanged(boolean): void  [API 31+]
```

## TelephonyCallback.PhysicalChannelConfigListener — declaration; API 31+ (1 +, 0 *, 0 ~, 0 -)

```
+ onPhysicalChannelConfigChanged(List): void  [API 31+]
```

## TelephonyCallback.PreciseDataConnectionStateListener — declaration; API 31+ (1 +, 0 *, 0 ~, 0 -)

```
+ onPreciseDataConnectionStateChanged(PreciseDataConnectionState): void  [API 31+]
```

## TelephonyCallback.RegistrationFailedListener — declaration; API 31+ (1 +, 0 *, 0 ~, 0 -)

```
+ onRegistrationFailed(CellIdentity, String, int, int, int): void  [API 31+]
```

## TelephonyCallback.ServiceStateListener — declaration; API 31+ (1 +, 0 *, 0 ~, 0 -)

```
+ onServiceStateChanged(ServiceState): void  [API 31+]
```

## TelephonyCallback.SignalStrengthsListener — declaration; API 31+ (1 +, 0 *, 0 ~, 0 -)

```
+ onSignalStrengthsChanged(SignalStrength): void  [API 31+]
```

## TelephonyCallback.UserMobileDataStateListener — declaration; API 31+ (1 +, 0 *, 0 ~, 0 -)

```
+ onUserMobileDataStateChanged(boolean): void  [API 31+]
```

## TelephonyDisplayInfo — declaration; API 30+ (8 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 30+]
+ describeContents(): int  [API 30+]
+ equals(Object): boolean  [API 30+]
+ getNetworkType(): int  [API 30+]
+ getOverrideNetworkType(): int  [API 30+]
+ hashCode(): int  [API 30+]
+ isRoaming(): boolean  [API 34+]
+ toString(): String  [API 30+]
+ writeToParcel(Parcel, int): void  [API 30+]
```

## TelephonyManager — framework; API 1+ (1 +, 0 *, 16 ~, 127 -)

```
- EXTRA_STATE_IDLE [field]  [API 3+]
- EXTRA_STATE_OFFHOOK [field]  [API 3+]
- EXTRA_STATE_RINGING [field]  [API 3+]
- canChangeDtmfToneLength(): boolean  [API 23+]
- clearSignalStrengthUpdateRequest(SignalStrengthUpdateRequest): void  [API 31+]
- createForPhoneAccountHandle(PhoneAccountHandle): TelephonyManager  [API 26+]
- createForSubscriptionId(int): TelephonyManager  [API 24+]
- doesSwitchMultiSimConfigTriggerReboot(): boolean  [API 29+]
- getActiveModemCount(): int  [API 30+]
- getAllCellInfo(): List  [API 17+]
- getAllowedNetworkTypesForReason(int): long  [API 33+]
- getCallComposerStatus(): int  [API 31+]
~ getCallState(): int  [API 1+, deprecated in 31]
- getCallStateForSubscription(): int  [API 31+]
- getCardIdForDefaultEuicc(): int  [API 29+]
- getCarrierConfig(): PersistableBundle  [API 26+]
- getCarrierIdFromSimMccMnc(): int  [API 29+]
- getCarrierRestrictionStatus(Executor, Consumer): void  [API 34+]
- getCellLocation(): CellLocation  [API 1+, deprecated in 26]
- getDataActivity(): int  [API 1+]
- getDataNetworkType(): int  [API 24+]
~ getDataState(): int  [API 1+]
+ getDeviceId(): String  [API 1+, deprecated in 26]
- getDeviceId(int): String  [API 23+, deprecated in 26]
~ getDeviceSoftwareVersion(): String  [API 1+]
- getEmergencyNumberList(): Map  [API 29+]
- getEmergencyNumberList(int): Map  [API 29+]
- getEquivalentHomePlmns(): List  [API 31+]
- getForbiddenPlmns(): String[]  [API 26+]
- getGroupIdLevel1(): String  [API 18+]
- getIccAuthentication(int, int, String): String  [API 24+]
- getImei(): String  [API 26+]
- getImei(int): String  [API 26+]
~ getLine1Number(): String  [API 1+, deprecated in 33]
- getManualNetworkSelectionPlmn(): String  [API 30+]
- getManufacturerCode(): String  [API 29+]
- getManufacturerCode(int): String  [API 29+]
- getMaximumCallComposerPictureSize(): long [static]  [API 31+]
- getMeid(): String  [API 26+]
- getMeid(int): String  [API 26+]
- getMmsUAProfUrl(): String  [API 19+]
- getMmsUserAgent(): String  [API 19+]
- getNai(): String  [API 28+]
- getNeighboringCellInfo(): List  [API 3-28, removed in 29, deprecated in 23]
~ getNetworkCountryIso(): String  [API 1+]
- getNetworkCountryIso(int): String  [API 30+]
~ getNetworkOperator(): String  [API 1+]
~ getNetworkOperatorName(): String  [API 1+]
- getNetworkSelectionMode(): int  [API 30+]
- getNetworkSlicingConfiguration(Executor, OutcomeReceiver): void  [API 31+]
- getNetworkSpecifier(): String  [API 26+]
~ getNetworkType(): int  [API 1+, deprecated in 30]
- getPhoneAccountHandle(): PhoneAccountHandle  [API 31+]
- getPhoneCount(): int  [API 23+, deprecated in 30]
~ getPhoneType(): int  [API 1+]
- getPreferredOpportunisticDataSubscription(): int  [API 29+]
- getPrimaryImei(): String  [API 34+]
- getServiceState(): ServiceState  [API 26+]
- getServiceState(int): ServiceState  [API 33+]
- getSignalStrength(): SignalStrength  [API 28+]
- getSimCarrierId(): int  [API 28+]
- getSimCarrierIdName(): CharSequence  [API 28+]
~ getSimCountryIso(): String  [API 1+]
~ getSimOperator(): String  [API 1+]
~ getSimOperatorName(): String  [API 1+]
~ getSimSerialNumber(): String  [API 1+]
- getSimSpecificCarrierId(): int  [API 29+]
- getSimSpecificCarrierIdName(): CharSequence  [API 29+]
~ getSimState(): int  [API 1+]
- getSimState(int): int  [API 26+]
~ getSubscriberId(): String  [API 1+]
- getSubscriptionId(): int  [API 30+]
- getSubscriptionId(PhoneAccountHandle): int  [API 30+]
- getSupportedModemCount(): int  [API 30+]
- getSupportedRadioAccessFamily(): long  [API 33+]
- getTypeAllocationCode(): String  [API 29+]
- getTypeAllocationCode(int): String  [API 29+]
- getUiccCardsInfo(): List  [API 29+]
- getVisualVoicemailPackageName(): String  [API 26+]
- getVoiceMailAlphaTag(): String  [API 1+]
- getVoiceMailNumber(): String  [API 1+]
- getVoiceNetworkType(): int  [API 24+]
- getVoicemailRingtoneUri(PhoneAccountHandle): Uri  [API 24+]
- hasCarrierPrivileges(): boolean  [API 22+]
- hasIccCard(): boolean  [API 5+]
- iccCloseLogicalChannel(int): boolean  [API 21+]
- iccExchangeSimIO(int, int, int, int, int, String): byte[]  [API 21+]
- iccOpenLogicalChannel(String): IccOpenLogicalChannelResponse  [API 21+, deprecated in 26]
- iccOpenLogicalChannel(String, int): IccOpenLogicalChannelResponse  [API 26+]
- iccTransmitApduBasicChannel(int, int, int, int, int, String): String  [API 21+]
- iccTransmitApduLogicalChannel(int, int, int, int, int, int, String): String  [API 21+]
- isConcurrentVoiceAndDataSupported(): boolean  [API 26+]
- isDataCapable(): boolean  [API 31+]
- isDataConnectionAllowed(): boolean  [API 31+]
- isDataEnabled(): boolean  [API 26+]
- isDataEnabledForReason(int): boolean  [API 31+]
- isDataRoamingEnabled(): boolean  [API 29+]
- isDeviceSmsCapable(): boolean  [API 35+]
- isDeviceVoiceCapable(): boolean  [API 35+]
- isEmergencyNumber(String): boolean  [API 29+]
- isHearingAidCompatibilitySupported(): boolean  [API 23+]
- isManualNetworkSelectionAllowed(): boolean  [API 30+]
- isModemEnabledForSlot(int): boolean  [API 30+]
- isMultiSimSupported(): int  [API 29+]
~ isNetworkRoaming(): boolean  [API 1+]
- isPremiumCapabilityAvailableForPurchase(int): boolean  [API 34+]
- isRadioInterfaceCapabilitySupported(String): boolean  [API 31+]
- isRttSupported(): boolean  [API 29+]
- isSmsCapable(): boolean  [API 21+, deprecated in 35]
- isTtyModeSupported(): boolean  [API 23+, deprecated in 28]
- isVoiceCapable(): boolean  [API 22+, deprecated in 35]
- isVoicemailVibrationEnabled(PhoneAccountHandle): boolean  [API 24+]
- isWorldPhone(): boolean  [API 23+]
- listen(PhoneStateListener, int): void  [API 1+, deprecated in 31]
- purchasePremiumCapability(int, Executor, Consumer): void  [API 34+]
- rebootModem(): void  [API 33+]
- registerTelephonyCallback(Executor, TelephonyCallback): void  [API 31+]
- registerTelephonyCallback(int, Executor, TelephonyCallback): void  [API 33+]
- requestCellInfoUpdate(Executor, TelephonyManager.CellInfoCallback): void  [API 29+]
- requestNetworkScan(NetworkScanRequest, Executor, TelephonyScanManager.NetworkScanCallback): NetworkScan  [API 28+]
- requestNetworkScan(int, NetworkScanRequest, Executor, TelephonyScanManager.NetworkScanCallback): NetworkScan  [API 33+]
- sendDialerSpecialCode(String): void  [API 26+]
- sendEnvelopeWithStatus(String): String  [API 21+]
- sendUssdRequest(String, TelephonyManager.UssdResponseCallback, Handler): void  [API 26+]
- sendVisualVoicemailSms(String, int, String, PendingIntent): void  [API 26+]
- setAllowedNetworkTypesForReason(int, long): void  [API 33+]
- setCallComposerStatus(int): void  [API 31+]
- setDataEnabled(boolean): void  [API 26+, deprecated in 31]
- setDataEnabledForReason(int, boolean): void  [API 31+]
- setForbiddenPlmns(List): int  [API 30+]
- setLine1NumberForDisplay(String, String): boolean  [API 22+, deprecated in 33]
- setNetworkSelectionModeAutomatic(): void  [API 28+]
- setNetworkSelectionModeManual(String, boolean): boolean  [API 28+]
- setNetworkSelectionModeManual(String, boolean, int): boolean  [API 30+]
- setOperatorBrandOverride(String): boolean  [API 22+]
- setPreferredNetworkTypeToGlobal(): boolean  [API 22+]
- setPreferredOpportunisticDataSubscription(int, boolean, Executor, Consumer): void  [API 29+]
- setSignalStrengthUpdateRequest(SignalStrengthUpdateRequest): void  [API 31+]
- setVisualVoicemailSmsFilterSettings(VisualVoicemailSmsFilterSettings): void  [API 26+]
- setVoiceMailNumber(String, String): boolean  [API 22+]
- setVoicemailRingtoneUri(PhoneAccountHandle, Uri): void  [API 26+, deprecated in 28]
- setVoicemailVibrationEnabled(PhoneAccountHandle, boolean): void  [API 26+, deprecated in 28]
- switchMultiSimConfig(int): void  [API 29+]
- unregisterTelephonyCallback(TelephonyCallback): void  [API 31+]
- updateAvailableNetworks(List, Executor, Consumer): void  [API 29+]
- uploadCallComposerPicture(InputStream, String, Executor, OutcomeReceiver): void  [API 31+]
- uploadCallComposerPicture(Path, String, Executor, OutcomeReceiver): void  [API 31+]
```

## TelephonyManager.CallComposerException — declaration; API 31+ (2 +, 0 *, 0 ~, 1 -)

```
- CallComposerException(int, IOException)  [API 31+]
+ getErrorCode(): int  [API 31+]
+ getIOException(): IOException  [API 31+]
```

## TelephonyManager.CellInfoCallback — declaration; API 29+ (3 +, 0 *, 0 ~, 0 -)

```
+ CellInfoCallback()  [API 29+]
+ onCellInfo(List): void  [API 29+]
+ onError(int, Throwable): void  [API 29+]
```

## TelephonyManager.ModemErrorException — declaration; API 31+ (0 +, 0 *, 0 ~, 0 -)

## TelephonyManager.NetworkSlicingException — declaration; API 31+ (1 +, 0 *, 0 ~, 0 -)

```
+ toString(): String  [API 31+]
```

## TelephonyManager.TimeoutException — declaration; API 31+ (0 +, 0 *, 0 ~, 0 -)

## TelephonyManager.UssdResponseCallback — declaration; API 26+ (3 +, 0 *, 0 ~, 0 -)

```
+ UssdResponseCallback()  [API 26+]
+ onReceiveUssdResponse(TelephonyManager, String, CharSequence): void  [API 26+]
+ onReceiveUssdResponseFailed(TelephonyManager, String, int): void  [API 26+]
```

## TelephonyScanManager — declaration; API 28+ (1 +, 0 *, 0 ~, 0 -)

```
+ TelephonyScanManager()  [API 28+]
```

## TelephonyScanManager.NetworkScanCallback — declaration; API 28+ (4 +, 0 *, 0 ~, 0 -)

```
+ NetworkScanCallback()  [API 28+]
+ onComplete(): void  [API 28+]
+ onError(int): void  [API 28+]
+ onResults(List): void  [API 28+]
```

## UiccCardInfo — declaration; API 29+ (14 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 29+]
+ describeContents(): int  [API 29+]
+ equals(Object): boolean  [API 29+]
+ getCardId(): int  [API 29+]
+ getEid(): String  [API 29+]
+ getIccId(): String  [API 29+, deprecated in 33]
+ getPhysicalSlotIndex(): int  [API 33+]
+ getPorts(): Collection  [API 33+]
+ getSlotIndex(): int  [API 29+, deprecated in 33]
+ hashCode(): int  [API 29+]
+ isEuicc(): boolean  [API 29+]
+ isMultipleEnabledProfilesSupported(): boolean  [API 33+]
+ isRemovable(): boolean  [API 29+]
+ toString(): String  [API 29+]
+ writeToParcel(Parcel, int): void  [API 29+]
```

## UiccPortInfo — declaration; API 33+ (9 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 33+]
+ describeContents(): int  [API 33+]
+ equals(Object): boolean  [API 33+]
+ getIccId(): String  [API 33+]
+ getLogicalSlotIndex(): int  [API 33+]
+ getPortIndex(): int  [API 33+]
+ hashCode(): int  [API 33+]
+ isActive(): boolean  [API 33+]
+ toString(): String  [API 33+]
+ writeToParcel(Parcel, int): void  [API 33+]
```

## VisualVoicemailService — declaration; API 26+ (5 +, 0 *, 0 ~, 1 -)

```
- VisualVoicemailService()  [API 26+]
+ onBind(Intent): IBinder  [API 26+]
+ onCellServiceConnected(VisualVoicemailService.VisualVoicemailTask, PhoneAccountHandle): void  [API 26+]
+ onSimRemoved(VisualVoicemailService.VisualVoicemailTask, PhoneAccountHandle): void  [API 26+]
+ onSmsReceived(VisualVoicemailService.VisualVoicemailTask, VisualVoicemailSms): void  [API 26+]
+ onStopped(VisualVoicemailService.VisualVoicemailTask): void  [API 26+]
```

## VisualVoicemailService.VisualVoicemailTask — declaration; API 26+ (3 +, 0 *, 0 ~, 0 -)

```
+ equals(Object): boolean  [API 26+]
+ finish(): void  [API 26+]
+ hashCode(): int  [API 26+]
```

## VisualVoicemailSms — declaration; API 26+ (6 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 26+]
+ describeContents(): int  [API 26+]
+ getFields(): Bundle  [API 26+]
+ getMessageBody(): String  [API 26+]
+ getPhoneAccountHandle(): PhoneAccountHandle  [API 26+]
+ getPrefix(): String  [API 26+]
+ writeToParcel(Parcel, int): void  [API 26+]
```

## VisualVoicemailSmsFilterSettings — declaration; API 26+ (3 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 26+]
- clientPrefix [field]  [API 26+]
+ describeContents(): int  [API 26+]
- destinationPort [field]  [API 26+]
- originatingNumbers [field]  [API 26+]
+ toString(): String  [API 26+]
+ writeToParcel(Parcel, int): void  [API 26+]
```

## VisualVoicemailSmsFilterSettings.Builder — declaration; API 26+ (4 +, 0 *, 0 ~, 1 -)

```
- Builder()  [API 26+]
+ build(): VisualVoicemailSmsFilterSettings  [API 26+]
+ setClientPrefix(String): VisualVoicemailSmsFilterSettings.Builder  [API 26+]
+ setDestinationPort(int): VisualVoicemailSmsFilterSettings.Builder  [API 26+]
+ setOriginatingNumbers(List): VisualVoicemailSmsFilterSettings.Builder  [API 26+]
```

