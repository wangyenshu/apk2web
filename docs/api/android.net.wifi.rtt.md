# android.net.wifi.rtt

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## CivicLocationKeys — declaration; API 29+ (0 +, 0 *, 0 ~, 0 -)

## RangingRequest — declaration; API 28+ (4 +, 6 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 28+]
* describeContents(): int  [API 28+]
* equals(Object): boolean  [API 28+]
+ getDefaultRttBurstSize(): int [static]  [API 31+]
+ getMaxPeers(): int [static]  [API 28+]
+ getMaxRttBurstSize(): int [static]  [API 31+]
+ getMinRttBurstSize(): int [static]  [API 31+]
* getRttBurstSize(): int  [API 31+]
* hashCode(): int  [API 28+]
* toString(): String  [API 28+]
* writeToParcel(Parcel, int): void  [API 28+]
```

## RangingRequest.Builder — declaration; API 28+ (8 +, 3 *, 0 ~, 0 -)

```
* Builder()  [API 28+]
+ addAccessPoint(ScanResult): RangingRequest.Builder  [API 28+]
+ addAccessPoints(List): RangingRequest.Builder  [API 28+]
+ addNon80211mcCapableAccessPoint(ScanResult): RangingRequest.Builder  [API 31+]
+ addNon80211mcCapableAccessPoints(List): RangingRequest.Builder  [API 31+]
+ addResponder(ResponderConfig): RangingRequest.Builder  [API 33+]
+ addResponders(List): RangingRequest.Builder  [API 33+]
+ addWifiAwarePeer(MacAddress): RangingRequest.Builder  [API 28+]
+ addWifiAwarePeer(PeerHandle): RangingRequest.Builder  [API 28+]
* build(): RangingRequest  [API 28+]
* setRttBurstSize(int): RangingRequest.Builder  [API 31+]
```

## RangingResult — declaration; API 28+ (6 +, 21 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 28+]
* describeContents(): int  [API 28+]
* equals(Object): boolean  [API 28+]
+ get80211azInitiatorTxLtfRepetitionsCount(): int  [API 35+]
+ get80211azNumberOfRxSpatialStreams(): int  [API 35+]
+ get80211azNumberOfTxSpatialStreams(): int  [API 35+]
+ get80211azResponderTxLtfRepetitionsCount(): int  [API 35+]
* getDistanceMm(): int  [API 28+]
* getDistanceStdDevMm(): int  [API 28+]
* getLci(): byte[]  [API 35+]
* getLcr(): byte[]  [API 35+]
* getMacAddress(): MacAddress  [API 28+]
* getMaxTimeBetweenNtbMeasurementsMicros(): long  [API 35+]
* getMeasurementBandwidth(): int  [API 34+]
* getMeasurementChannelFrequencyMHz(): int  [API 34+]
* getMinTimeBetweenNtbMeasurementsMicros(): long  [API 35+]
* getNumAttemptedMeasurements(): int  [API 28+]
* getNumSuccessfulMeasurements(): int  [API 28+]
* getPeerHandle(): PeerHandle  [API 28+]
* getRangingTimestampMillis(): long  [API 28+]
* getRssi(): int  [API 28+]
* getStatus(): int  [API 28+]
* getUnverifiedResponderLocation(): ResponderLocation  [API 29+]
* hashCode(): int  [API 28+]
+ is80211azNtbMeasurement(): boolean  [API 35+]
+ is80211mcMeasurement(): boolean  [API 31+]
* toString(): String  [API 28+]
* writeToParcel(Parcel, int): void  [API 28+]
```

## RangingResult.Builder — declaration; API 35+ (6 +, 18 *, 0 ~, 0 -)

```
* Builder()  [API 35+]
* build(): RangingResult  [API 35+]
+ set80211azInitiatorTxLtfRepetitionsCount(int): RangingResult.Builder  [API 35+]
+ set80211azNtbMeasurement(boolean): RangingResult.Builder  [API 35+]
+ set80211azNumberOfRxSpatialStreams(int): RangingResult.Builder  [API 35+]
+ set80211azNumberOfTxSpatialStreams(int): RangingResult.Builder  [API 35+]
+ set80211azResponderTxLtfRepetitionsCount(int): RangingResult.Builder  [API 35+]
+ set80211mcMeasurement(boolean): RangingResult.Builder  [API 35+]
* setDistanceMm(int): RangingResult.Builder  [API 35+]
* setDistanceStdDevMm(int): RangingResult.Builder  [API 35+]
* setLci(byte[]): RangingResult.Builder  [API 35+]
* setLcr(byte[]): RangingResult.Builder  [API 35+]
* setMacAddress(MacAddress): RangingResult.Builder  [API 35+]
* setMaxTimeBetweenNtbMeasurementsMicros(long): RangingResult.Builder  [API 35+]
* setMeasurementBandwidth(int): RangingResult.Builder  [API 35+]
* setMeasurementChannelFrequencyMHz(int): RangingResult.Builder  [API 35+]
* setMinTimeBetweenNtbMeasurementsMicros(long): RangingResult.Builder  [API 35+]
* setNumAttemptedMeasurements(int): RangingResult.Builder  [API 35+]
* setNumSuccessfulMeasurements(int): RangingResult.Builder  [API 35+]
* setPeerHandle(PeerHandle): RangingResult.Builder  [API 35+]
* setRangingTimestampMillis(long): RangingResult.Builder  [API 35+]
* setRssi(int): RangingResult.Builder  [API 35+]
* setStatus(int): RangingResult.Builder  [API 35+]
* setUnverifiedResponderLocation(ResponderLocation): RangingResult.Builder  [API 35+]
```

## RangingResultCallback — declaration; API 28+ (3 +, 0 *, 0 ~, 0 -)

```
+ RangingResultCallback()  [API 28+]
+ onRangingFailure(int): void  [API 28+]
+ onRangingResults(List): void  [API 28+]
```

## ResponderConfig — declaration; API 33+ (3 +, 12 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 33+]
* describeContents(): int  [API 33+]
* equals(Object): boolean  [API 33+]
+ fromScanResult(ScanResult): ResponderConfig [static]  [API 33+]
* getCenterFreq0Mhz(): int  [API 33+]
* getCenterFreq1Mhz(): int  [API 33+]
* getChannelWidth(): int  [API 33+]
* getFrequencyMhz(): int  [API 33+]
* getMacAddress(): MacAddress  [API 33+]
* getPreamble(): int  [API 33+]
* getResponderType(): int  [API 34+]
* hashCode(): int  [API 33+]
+ is80211azNtbSupported(): boolean  [API 35+]
+ is80211mcSupported(): boolean  [API 33+]
* toString(): String  [API 33+]
* writeToParcel(Parcel, int): void  [API 33+]
```

## ResponderConfig.Builder — declaration; API 33+ (2 +, 9 *, 0 ~, 0 -)

```
* Builder()  [API 33+]
* build(): ResponderConfig  [API 33+]
+ set80211azNtbSupported(boolean): ResponderConfig.Builder  [API 35+]
+ set80211mcSupported(boolean): ResponderConfig.Builder  [API 33+]
* setCenterFreq0Mhz(int): ResponderConfig.Builder  [API 33+]
* setCenterFreq1Mhz(int): ResponderConfig.Builder  [API 33+]
* setChannelWidth(int): ResponderConfig.Builder  [API 33+]
* setFrequencyMhz(int): ResponderConfig.Builder  [API 33+]
* setMacAddress(MacAddress): ResponderConfig.Builder  [API 33+]
* setPreamble(int): ResponderConfig.Builder  [API 33+]
* setResponderType(int): ResponderConfig.Builder  [API 34+]
```

## ResponderLocation — declaration; API 29+ (26 +, 0 *, 0 ~, 0 -)

```
- CREATOR [field]  [API 29+]
+ describeContents(): int  [API 29+]
+ equals(Object): boolean  [API 29+]
+ getAltitude(): double  [API 29+]
+ getAltitudeType(): int  [API 29+]
+ getAltitudeUncertainty(): double  [API 29+]
+ getColocatedBssids(): List  [API 29+]
+ getDatum(): int  [API 29+]
+ getExpectedToMove(): int  [API 29+]
+ getFloorNumber(): double  [API 29+]
+ getHeightAboveFloorMeters(): double  [API 29+]
+ getHeightAboveFloorUncertaintyMeters(): double  [API 29+]
+ getLatitude(): double  [API 29+]
+ getLatitudeUncertainty(): double  [API 29+]
+ getLciVersion(): int  [API 29+]
+ getLongitude(): double  [API 29+]
+ getLongitudeUncertainty(): double  [API 29+]
+ getMapImageMimeType(): String  [API 29+]
+ getMapImageUri(): Uri  [API 29+]
+ getRegisteredLocationAgreementIndication(): boolean  [API 29+]
+ hashCode(): int  [API 29+]
+ isLciSubelementValid(): boolean  [API 29+]
+ isZaxisSubelementValid(): boolean  [API 29+]
+ toCivicLocationAddress(): Address  [API 29+]
+ toCivicLocationSparseArray(): SparseArray  [API 29+]
+ toLocation(): Location  [API 29+]
+ writeToParcel(Parcel, int): void  [API 29+]
```

## WifiRttManager — declaration; API 28+ (3 +, 0 *, 0 ~, 0 -)

```
+ getRttCharacteristics(): Bundle  [API 34+]
+ isAvailable(): boolean  [API 28+]
+ startRanging(RangingRequest, Executor, RangingResultCallback): void  [API 28+]
```

