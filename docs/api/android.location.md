# android.location

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## Address — declaration; API 1+ (0 +, 34 *, 0 ~, 10 -)

```
* Address(Locale)  [API 1+]
- CREATOR [field]  [API 1+]
- clearLatitude(): void  [API 1+]
- clearLongitude(): void  [API 1+]
- describeContents(): int  [API 1+]
- getAddressLine(int): String  [API 1+]
* getAdminArea(): String  [API 1+]
* getCountryCode(): String  [API 1+]
* getCountryName(): String  [API 1+]
* getExtras(): Bundle  [API 1+]
* getFeatureName(): String  [API 1+]
* getLatitude(): double  [API 1+]
* getLocale(): Locale  [API 1+]
* getLocality(): String  [API 1+]
* getLongitude(): double  [API 1+]
- getMaxAddressLineIndex(): int  [API 1+]
* getPhone(): String  [API 1+]
* getPostalCode(): String  [API 1+]
* getPremises(): String  [API 4+]
* getSubAdminArea(): String  [API 1+]
* getSubLocality(): String  [API 4+]
* getSubThoroughfare(): String  [API 4+]
* getThoroughfare(): String  [API 1+]
* getUrl(): String  [API 1+]
- hasLatitude(): boolean  [API 1+]
- hasLongitude(): boolean  [API 1+]
- setAddressLine(int, String): void  [API 1+]
* setAdminArea(String): void  [API 1+]
* setCountryCode(String): void  [API 1+]
* setCountryName(String): void  [API 1+]
* setExtras(Bundle): void  [API 1+]
* setFeatureName(String): void  [API 1+]
* setLatitude(double): void  [API 1+]
* setLocality(String): void  [API 1+]
* setLongitude(double): void  [API 1+]
* setPhone(String): void  [API 1+]
* setPostalCode(String): void  [API 1+]
* setPremises(String): void  [API 4+]
* setSubAdminArea(String): void  [API 1+]
* setSubLocality(String): void  [API 4+]
* setSubThoroughfare(String): void  [API 4+]
* setThoroughfare(String): void  [API 1+]
* setUrl(String): void  [API 1+]
- toString(): String  [API 1+]
- writeToParcel(Parcel, int): void  [API 1+]
```

## Criteria — framework; API 1+, deprecated in 34 (14 +, 0 *, 2 ~, 9 -)

```
- CREATOR [field]  [API 1+]
+ Criteria()  [API 1+]
- Criteria(Criteria)  [API 1+]
- describeContents(): int  [API 1+]
+ getAccuracy(): int  [API 1+]
- getBearingAccuracy(): int  [API 9+]
- getHorizontalAccuracy(): int  [API 9+]
+ getPowerRequirement(): int  [API 1+]
- getSpeedAccuracy(): int  [API 9+]
- getVerticalAccuracy(): int  [API 9+]
+ isAltitudeRequired(): boolean  [API 1+]
+ isBearingRequired(): boolean  [API 1+]
+ isCostAllowed(): boolean  [API 1+]
+ isSpeedRequired(): boolean  [API 1+]
+ setAccuracy(int): void  [API 1+]
+ setAltitudeRequired(boolean): void  [API 1+]
- setBearingAccuracy(int): void  [API 9+]
+ setBearingRequired(boolean): void  [API 1+]
+ setCostAllowed(boolean): void  [API 1+]
~ setHorizontalAccuracy(int): void  [API 9+]
+ setPowerRequirement(int): void  [API 1+]
- setSpeedAccuracy(int): void  [API 9+]
+ setSpeedRequired(boolean): void  [API 1+]
~ setVerticalAccuracy(int): void  [API 9+]
+ toString(): String  [API 1+]
- writeToParcel(Parcel, int): void  [API 1+]
```

## Geocoder — declaration; API 1+ (0 +, 0 *, 0 ~, 9 -)

```
- Geocoder(Context)  [API 1+]
- Geocoder(Context, Locale)  [API 1+]
- getFromLocation(double, double, int): List  [API 1+, deprecated in 33]
- getFromLocation(double, double, int, Geocoder.GeocodeListener): void  [API 33+]
- getFromLocationName(String, int): List  [API 1+, deprecated in 33]
- getFromLocationName(String, int, Geocoder.GeocodeListener): void  [API 33+]
- getFromLocationName(String, int, double, double, double, double): List  [API 1+, deprecated in 33]
- getFromLocationName(String, int, double, double, double, double, Geocoder.GeocodeListener): void  [API 33+]
- isPresent(): boolean [static]  [API 9+]
```

## Geocoder.GeocodeListener — declaration; API 33+ (2 +, 0 *, 0 ~, 0 -)

```
+ onError(String): void  [API 33+]
+ onGeocode(List): void  [API 33+]
```

## GnssAntennaInfo — declaration; API 30+ (0 +, 9 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 30+]
* describeContents(): int  [API 30+]
* equals(Object): boolean  [API 30+]
* getCarrierFrequencyMHz(): double  [API 30+]
* getPhaseCenterOffset(): GnssAntennaInfo.PhaseCenterOffset  [API 30+]
* getPhaseCenterVariationCorrections(): GnssAntennaInfo.SphericalCorrections  [API 30+]
* getSignalGainCorrections(): GnssAntennaInfo.SphericalCorrections  [API 30+]
* hashCode(): int  [API 30+]
* toString(): String  [API 30+]
* writeToParcel(Parcel, int): void  [API 30+]
```

## GnssAntennaInfo.Builder — declaration; API 30+ (0 +, 8 *, 0 ~, 0 -)

```
* Builder()  [API 30+, deprecated in 31]
* Builder(GnssAntennaInfo)  [API 31+]
* Builder(double, GnssAntennaInfo.PhaseCenterOffset)  [API 31+]
* build(): GnssAntennaInfo  [API 30+]
* setCarrierFrequencyMHz(double): GnssAntennaInfo.Builder  [API 30+]
* setPhaseCenterOffset(GnssAntennaInfo.PhaseCenterOffset): GnssAntennaInfo.Builder  [API 30+]
* setPhaseCenterVariationCorrections(GnssAntennaInfo.SphericalCorrections): GnssAntennaInfo.Builder  [API 30+]
* setSignalGainCorrections(GnssAntennaInfo.SphericalCorrections): GnssAntennaInfo.Builder  [API 30+]
```

## GnssAntennaInfo.Listener — declaration; API 30+ (1 +, 0 *, 0 ~, 0 -)

```
+ onGnssAntennaInfoReceived(List): void  [API 30+]
```

## GnssAntennaInfo.PhaseCenterOffset — declaration; API 30+ (0 +, 0 *, 0 ~, 12 -)

```
- CREATOR [field]  [API 30+]
- PhaseCenterOffset(double, double, double, double, double, double)  [API 30+]
- describeContents(): int  [API 30+]
- equals(Object): boolean  [API 30+]
- getXOffsetMm(): double  [API 30+]
- getXOffsetUncertaintyMm(): double  [API 30+]
- getYOffsetMm(): double  [API 30+]
- getYOffsetUncertaintyMm(): double  [API 30+]
- getZOffsetMm(): double  [API 30+]
- getZOffsetUncertaintyMm(): double  [API 30+]
- hashCode(): int  [API 30+]
- toString(): String  [API 30+]
- writeToParcel(Parcel, int): void  [API 30+]
```

## GnssAntennaInfo.SphericalCorrections — declaration; API 30+ (0 +, 0 *, 0 ~, 10 -)

```
- CREATOR [field]  [API 30+]
- SphericalCorrections(double[][], double[][])  [API 30+]
- describeContents(): int  [API 30+]
- equals(Object): boolean  [API 30+]
- getCorrectionUncertaintiesArray(): double[][]  [API 30+]
- getCorrectionsArray(): double[][]  [API 30+]
- getDeltaPhi(): double  [API 30+]
- getDeltaTheta(): double  [API 30+]
- hashCode(): int  [API 30+]
- toString(): String  [API 30+]
- writeToParcel(Parcel, int): void  [API 30+]
```

## GnssAutomaticGainControl — declaration; API 33+ (0 +, 8 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 33+]
* describeContents(): int  [API 33+]
* equals(Object): boolean  [API 33+]
* getCarrierFrequencyHz(): long  [API 33+]
* getConstellationType(): int  [API 33+]
* getLevelDb(): double  [API 33+]
* hashCode(): int  [API 33+]
* toString(): String  [API 33+]
* writeToParcel(Parcel, int): void  [API 33+]
```

## GnssAutomaticGainControl.Builder — declaration; API 33+ (0 +, 6 *, 0 ~, 0 -)

```
* Builder()  [API 33+]
* Builder(GnssAutomaticGainControl)  [API 33+]
* build(): GnssAutomaticGainControl  [API 33+]
* setCarrierFrequencyHz(long): GnssAutomaticGainControl.Builder  [API 33+]
* setConstellationType(int): GnssAutomaticGainControl.Builder  [API 33+]
* setLevelDb(double): GnssAutomaticGainControl.Builder  [API 33+]
```

## GnssCapabilities — declaration; API 30+ (0 +, 6 *, 0 ~, 26 -)

```
+ CREATOR [field]  [API 31+]
* describeContents(): int  [API 30+]
* equals(Object): boolean  [API 30+]
* getGnssSignalTypes(): List  [API 34+]
- hasAccumulatedDeltaRange(): int  [API 34+]
- hasAntennaInfo(): boolean  [API 31+]
- hasGeofencing(): boolean  [API 34+]
- hasGnssAntennaInfo(): boolean  [API 30+, deprecated in 31]
- hasLowPowerMode(): boolean  [API 34+]
- hasMeasurementCorrections(): boolean  [API 34+]
- hasMeasurementCorrectionsExcessPathLength(): boolean  [API 34+]
- hasMeasurementCorrectionsForDriving(): boolean  [API 34+]
- hasMeasurementCorrectionsLosSats(): boolean  [API 34+]
- hasMeasurementCorrectionsReflectingPlane(): boolean  [API 34+]
- hasMeasurementCorrelationVectors(): boolean  [API 34+]
- hasMeasurements(): boolean  [API 31+]
- hasMsa(): boolean  [API 34+]
- hasMsb(): boolean  [API 34+]
- hasNavigationMessages(): boolean  [API 31+]
- hasOnDemandTime(): boolean  [API 34+]
- hasPowerMultibandAcquisition(): boolean  [API 34+]
- hasPowerMultibandTracking(): boolean  [API 34+]
- hasPowerOtherModes(): boolean  [API 34+]
- hasPowerSinglebandAcquisition(): boolean  [API 34+]
- hasPowerSinglebandTracking(): boolean  [API 34+]
- hasPowerTotal(): boolean  [API 34+]
- hasSatelliteBlocklist(): boolean  [API 34+]
- hasSatellitePvt(): boolean  [API 34+]
- hasScheduling(): boolean  [API 34+]
- hasSingleShotFix(): boolean  [API 34+]
* hashCode(): int  [API 30+]
* toString(): String  [API 30+]
* writeToParcel(Parcel, int): void  [API 30+]
```

## GnssCapabilities.Builder — declaration; API 31+ (0 +, 4 *, 0 ~, 25 -)

```
* Builder()  [API 31+]
* Builder(GnssCapabilities)  [API 31+]
* build(): GnssCapabilities  [API 31+]
* setGnssSignalTypes(List): GnssCapabilities.Builder  [API 34+]
- setHasAccumulatedDeltaRange(int): GnssCapabilities.Builder  [API 34+]
- setHasAntennaInfo(boolean): GnssCapabilities.Builder  [API 31+]
- setHasGeofencing(boolean): GnssCapabilities.Builder  [API 34+]
- setHasLowPowerMode(boolean): GnssCapabilities.Builder  [API 34+]
- setHasMeasurementCorrections(boolean): GnssCapabilities.Builder  [API 34+]
- setHasMeasurementCorrectionsExcessPathLength(boolean): GnssCapabilities.Builder  [API 34+]
- setHasMeasurementCorrectionsForDriving(boolean): GnssCapabilities.Builder  [API 34+]
- setHasMeasurementCorrectionsLosSats(boolean): GnssCapabilities.Builder  [API 34+]
- setHasMeasurementCorrectionsReflectingPlane(boolean): GnssCapabilities.Builder  [API 34+]
- setHasMeasurementCorrelationVectors(boolean): GnssCapabilities.Builder  [API 34+]
- setHasMeasurements(boolean): GnssCapabilities.Builder  [API 31+]
- setHasMsa(boolean): GnssCapabilities.Builder  [API 34+]
- setHasMsb(boolean): GnssCapabilities.Builder  [API 34+]
- setHasNavigationMessages(boolean): GnssCapabilities.Builder  [API 31+]
- setHasOnDemandTime(boolean): GnssCapabilities.Builder  [API 34+]
- setHasPowerMultibandAcquisition(boolean): GnssCapabilities.Builder  [API 34+]
- setHasPowerMultibandTracking(boolean): GnssCapabilities.Builder  [API 34+]
- setHasPowerOtherModes(boolean): GnssCapabilities.Builder  [API 34+]
- setHasPowerSinglebandAcquisition(boolean): GnssCapabilities.Builder  [API 34+]
- setHasPowerSinglebandTracking(boolean): GnssCapabilities.Builder  [API 34+]
- setHasPowerTotal(boolean): GnssCapabilities.Builder  [API 34+]
- setHasSatelliteBlocklist(boolean): GnssCapabilities.Builder  [API 34+]
- setHasSatellitePvt(boolean): GnssCapabilities.Builder  [API 34+]
- setHasScheduling(boolean): GnssCapabilities.Builder  [API 34+]
- setHasSingleShotFix(boolean): GnssCapabilities.Builder  [API 34+]
```

## GnssClock — declaration; API 24+ (0 +, 0 *, 0 ~, 29 -)

```
- CREATOR [field]  [API 24+]
- describeContents(): int  [API 24+]
- getBiasNanos(): double  [API 24+]
- getBiasUncertaintyNanos(): double  [API 24+]
- getDriftNanosPerSecond(): double  [API 24+]
- getDriftUncertaintyNanosPerSecond(): double  [API 24+]
- getElapsedRealtimeNanos(): long  [API 29+]
- getElapsedRealtimeUncertaintyNanos(): double  [API 29+]
- getFullBiasNanos(): long  [API 24+]
- getHardwareClockDiscontinuityCount(): int  [API 24+]
- getLeapSecond(): int  [API 24+]
- getReferenceCarrierFrequencyHzForIsb(): double  [API 30+]
- getReferenceCodeTypeForIsb(): String  [API 30+]
- getReferenceConstellationTypeForIsb(): int  [API 30+]
- getTimeNanos(): long  [API 24+]
- getTimeUncertaintyNanos(): double  [API 24+]
- hasBiasNanos(): boolean  [API 24+]
- hasBiasUncertaintyNanos(): boolean  [API 24+]
- hasDriftNanosPerSecond(): boolean  [API 24+]
- hasDriftUncertaintyNanosPerSecond(): boolean  [API 24+]
- hasElapsedRealtimeNanos(): boolean  [API 29+]
- hasElapsedRealtimeUncertaintyNanos(): boolean  [API 29+]
- hasFullBiasNanos(): boolean  [API 24+]
- hasLeapSecond(): boolean  [API 24+]
- hasReferenceCarrierFrequencyHzForIsb(): boolean  [API 30+]
- hasReferenceCodeTypeForIsb(): boolean  [API 30+]
- hasReferenceConstellationTypeForIsb(): boolean  [API 30+]
- hasTimeUncertaintyNanos(): boolean  [API 24+]
- toString(): String  [API 24+]
- writeToParcel(Parcel, int): void  [API 24+]
```

## GnssMeasurement — declaration; API 24+ (0 +, 0 *, 0 ~, 40 -)

```
- CREATOR [field]  [API 24+]
- describeContents(): int  [API 24+]
- getAccumulatedDeltaRangeMeters(): double  [API 24+]
- getAccumulatedDeltaRangeState(): int  [API 24+]
- getAccumulatedDeltaRangeUncertaintyMeters(): double  [API 24+]
- getAutomaticGainControlLevelDb(): double  [API 26+, deprecated in 33]
- getBasebandCn0DbHz(): double  [API 30+]
- getCarrierCycles(): long  [API 24+, deprecated in 28]
- getCarrierFrequencyHz(): float  [API 24+]
- getCarrierPhase(): double  [API 24+, deprecated in 28]
- getCarrierPhaseUncertainty(): double  [API 24+, deprecated in 28]
- getCn0DbHz(): double  [API 24+]
- getCodeType(): String  [API 29+]
- getConstellationType(): int  [API 24+]
- getFullInterSignalBiasNanos(): double  [API 30+]
- getFullInterSignalBiasUncertaintyNanos(): double  [API 30+]
- getMultipathIndicator(): int  [API 24+]
- getPseudorangeRateMetersPerSecond(): double  [API 24+]
- getPseudorangeRateUncertaintyMetersPerSecond(): double  [API 24+]
- getReceivedSvTimeNanos(): long  [API 24+]
- getReceivedSvTimeUncertaintyNanos(): long  [API 24+]
- getSatelliteInterSignalBiasNanos(): double  [API 30+]
- getSatelliteInterSignalBiasUncertaintyNanos(): double  [API 30+]
- getSnrInDb(): double  [API 24+]
- getState(): int  [API 24+]
- getSvid(): int  [API 24+]
- getTimeOffsetNanos(): double  [API 24+]
- hasAutomaticGainControlLevelDb(): boolean  [API 26+, deprecated in 33]
- hasBasebandCn0DbHz(): boolean  [API 30+]
- hasCarrierCycles(): boolean  [API 24+, deprecated in 28]
- hasCarrierFrequencyHz(): boolean  [API 24+]
- hasCarrierPhase(): boolean  [API 24+, deprecated in 28]
- hasCarrierPhaseUncertainty(): boolean  [API 24+, deprecated in 28]
- hasCodeType(): boolean  [API 29+]
- hasFullInterSignalBiasNanos(): boolean  [API 30+]
- hasFullInterSignalBiasUncertaintyNanos(): boolean  [API 30+]
- hasSatelliteInterSignalBiasNanos(): boolean  [API 30+]
- hasSatelliteInterSignalBiasUncertaintyNanos(): boolean  [API 30+]
- hasSnrInDb(): boolean  [API 24+]
- toString(): String  [API 24+]
- writeToParcel(Parcel, int): void  [API 24+]
```

## GnssMeasurementRequest — declaration; API 31+ (0 +, 7 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 31+]
* describeContents(): int  [API 31+]
* equals(Object): boolean  [API 31+]
* getIntervalMillis(): int  [API 33+]
* hashCode(): int  [API 31+]
* isFullTracking(): boolean  [API 31+]
* toString(): String  [API 31+]
* writeToParcel(Parcel, int): void  [API 31+]
```

## GnssMeasurementRequest.Builder — declaration; API 31+ (0 +, 5 *, 0 ~, 0 -)

```
* Builder()  [API 31+]
* Builder(GnssMeasurementRequest)  [API 31+]
* build(): GnssMeasurementRequest  [API 31+]
* setFullTracking(boolean): GnssMeasurementRequest.Builder  [API 31+]
* setIntervalMillis(int): GnssMeasurementRequest.Builder  [API 33+]
```

## GnssMeasurementsEvent — declaration; API 24+ (0 +, 7 *, 0 ~, 1 -)

```
+ CREATOR [field]  [API 24+]
* describeContents(): int  [API 24+]
* getClock(): GnssClock  [API 24+]
* getGnssAutomaticGainControls(): Collection  [API 33+]
* getMeasurements(): Collection  [API 24+]
- hasIsFullTracking(): boolean  [API 34+]
* isFullTracking(): boolean  [API 34+]
* toString(): String  [API 24+]
* writeToParcel(Parcel, int): void  [API 24+]
```

## GnssMeasurementsEvent.Builder — declaration; API 33+ (0 +, 6 *, 0 ~, 2 -)

```
* Builder()  [API 33+]
* Builder(GnssMeasurementsEvent)  [API 33+]
* build(): GnssMeasurementsEvent  [API 33+]
- clearIsFullTracking(): GnssMeasurementsEvent.Builder  [API 34+]
* setClock(GnssClock): GnssMeasurementsEvent.Builder  [API 33+]
* setGnssAutomaticGainControls(Collection): GnssMeasurementsEvent.Builder  [API 33+]
- setIsFullTracking(boolean): GnssMeasurementsEvent.Builder  [API 34+]
* setMeasurements(Collection): GnssMeasurementsEvent.Builder  [API 33+]
```

## GnssMeasurementsEvent.Callback — declaration; API 24+ (3 +, 0 *, 0 ~, 0 -)

```
+ Callback()  [API 24+]
+ onGnssMeasurementsReceived(GnssMeasurementsEvent): void  [API 24+]
+ onStatusChanged(int): void  [API 24+, deprecated in 31]
```

## GnssNavigationMessage — declaration; API 24+ (0 +, 0 *, 0 ~, 9 -)

```
- CREATOR [field]  [API 24+]
- describeContents(): int  [API 24+]
- getData(): byte[]  [API 24+]
- getMessageId(): int  [API 24+]
- getStatus(): int  [API 24+]
- getSubmessageId(): int  [API 24+]
- getSvid(): int  [API 24+]
- getType(): int  [API 24+]
- toString(): String  [API 24+]
- writeToParcel(Parcel, int): void  [API 24+]
```

## GnssNavigationMessage.Callback — declaration; API 24+ (3 +, 0 *, 0 ~, 0 -)

```
+ Callback()  [API 24+]
+ onGnssNavigationMessageReceived(GnssNavigationMessage): void  [API 24+]
+ onStatusChanged(int): void  [API 24+, deprecated in 31]
```

## GnssSignalType — declaration; API 34+ (0 +, 0 *, 0 ~, 9 -)

```
- CREATOR [field]  [API 34+]
- create(int, double, String): GnssSignalType [static]  [API 34+]
- describeContents(): int  [API 34+]
- equals(Object): boolean  [API 34+]
- getCarrierFrequencyHz(): double  [API 34+]
- getCodeType(): String  [API 34+]
- getConstellationType(): int  [API 34+]
- hashCode(): int  [API 34+]
- toString(): String  [API 34+]
- writeToParcel(Parcel, int): void  [API 34+]
```

## GnssStatus — declaration; API 24+ (0 +, 0 *, 0 ~, 17 -)

```
- CREATOR [field]  [API 31+]
- describeContents(): int  [API 24+]
- equals(Object): boolean  [API 24+]
- getAzimuthDegrees(int): float  [API 24+]
- getBasebandCn0DbHz(int): float  [API 30+]
- getCarrierFrequencyHz(int): float  [API 26+]
- getCn0DbHz(int): float  [API 24+]
- getConstellationType(int): int  [API 24+]
- getElevationDegrees(int): float  [API 24+]
- getSatelliteCount(): int  [API 24+]
- getSvid(int): int  [API 24+]
- hasAlmanacData(int): boolean  [API 24+]
- hasBasebandCn0DbHz(int): boolean  [API 30+]
- hasCarrierFrequencyHz(int): boolean  [API 26+]
- hasEphemerisData(int): boolean  [API 24+]
- hashCode(): int  [API 24+]
- usedInFix(int): boolean  [API 24+]
- writeToParcel(Parcel, int): void  [API 24+]
```

## GnssStatus.Builder — declaration; API 30+ (0 +, 0 *, 0 ~, 4 -)

```
- Builder()  [API 30+]
- addSatellite(int, int, float, float, float, boolean, boolean, boolean, boolean, float, boolean, float): GnssStatus.Builder  [API 30+]
- build(): GnssStatus  [API 30+]
- clearSatellites(): GnssStatus.Builder  [API 30+]
```

## GnssStatus.Callback — declaration; API 24+ (5 +, 0 *, 0 ~, 0 -)

```
+ Callback()  [API 24+]
+ onFirstFix(int): void  [API 24+]
+ onSatelliteStatusChanged(GnssStatus): void  [API 24+]
+ onStarted(): void  [API 24+]
+ onStopped(): void  [API 24+]
```

## GpsSatellite — declaration; API 3+, deprecated in 24 (0 +, 0 *, 0 ~, 7 -)

```
- getAzimuth(): float  [API 3+]
- getElevation(): float  [API 3+]
- getPrn(): int  [API 3+]
- getSnr(): float  [API 3+]
- hasAlmanac(): boolean  [API 3+]
- hasEphemeris(): boolean  [API 3+]
- usedInFix(): boolean  [API 3+]
```

## GpsStatus — declaration; API 3+, deprecated in 24 (0 +, 0 *, 0 ~, 4 -)

```
- create(GnssStatus, int): GpsStatus [static]  [API 30+, deprecated in 30]
- getMaxSatellites(): int  [API 3+]
- getSatellites(): Iterable  [API 3+]
- getTimeToFirstFix(): int  [API 3+]
```

## GpsStatus.Listener — declaration; API 3+, deprecated in 24 (1 +, 0 *, 0 ~, 0 -)

```
+ onGpsStatusChanged(int): void  [API 3+]
```

## GpsStatus.NmeaListener — declaration; API 5+, deprecated in 24 (1 +, 0 *, 0 ~, 0 -)

```
+ onNmeaReceived(long, String): void  [API 5+]
```

## Location — framework; API 1+ (33 +, 0 *, 2 ~, 39 -)

```
- CREATOR [field]  [API 1+]
+ Location(Location)  [API 1+]
+ Location(String)  [API 1+]
+ bearingTo(Location): float  [API 1+]
- convert(String): double [static]  [API 1+]
- convert(double, int): String [static]  [API 1+]
~ describeContents(): int  [API 1+]
+ distanceBetween(double, double, double, double, float[]): void [static]  [API 1+]
+ distanceTo(Location): float  [API 1+]
- dump(Printer, String): void  [API 3+, deprecated in 33]
+ equals(Object): boolean  [API 1+]
+ getAccuracy(): float  [API 1+]
+ getAltitude(): double  [API 1+]
+ getBearing(): float  [API 1+]
- getBearingAccuracyDegrees(): float  [API 26+]
- getElapsedRealtimeAgeMillis(): long  [API 33+]
- getElapsedRealtimeAgeMillis(long): long  [API 33+]
- getElapsedRealtimeMillis(): long  [API 33+]
+ getElapsedRealtimeNanos(): long  [API 17+]
- getElapsedRealtimeUncertaintyNanos(): double  [API 29+]
+ getExtras(): Bundle  [API 1+]
+ getLatitude(): double  [API 1+]
+ getLongitude(): double  [API 1+]
- getMslAltitudeAccuracyMeters(): float  [API 34+]
- getMslAltitudeMeters(): double  [API 34+]
+ getProvider(): String  [API 1+]
+ getSpeed(): float  [API 1+]
- getSpeedAccuracyMetersPerSecond(): float  [API 26+]
+ getTime(): long  [API 1+]
- getVerticalAccuracyMeters(): float  [API 26+]
+ hasAccuracy(): boolean  [API 1+]
+ hasAltitude(): boolean  [API 1+]
+ hasBearing(): boolean  [API 1+]
- hasBearingAccuracy(): boolean  [API 26+]
- hasElapsedRealtimeUncertaintyNanos(): boolean  [API 29+]
- hasMslAltitude(): boolean  [API 34+]
- hasMslAltitudeAccuracy(): boolean  [API 34+]
+ hasSpeed(): boolean  [API 1+]
- hasSpeedAccuracy(): boolean  [API 26+]
- hasVerticalAccuracy(): boolean  [API 26+]
+ hashCode(): int  [API 1+]
- isComplete(): boolean  [API 33+]
- isFromMockProvider(): boolean  [API 18+, deprecated in 31]
- isMock(): boolean  [API 31+]
- removeAccuracy(): void  [API 1+]
- removeAltitude(): void  [API 1+]
- removeBearing(): void  [API 1+]
- removeBearingAccuracy(): void  [API 33+]
- removeElapsedRealtimeUncertaintyNanos(): void  [API 33+]
- removeMslAltitude(): void  [API 34+]
- removeMslAltitudeAccuracy(): void  [API 34+]
- removeSpeed(): void  [API 1+]
- removeSpeedAccuracy(): void  [API 33+]
- removeVerticalAccuracy(): void  [API 33+]
+ reset(): void  [API 1+]
+ set(Location): void  [API 1+]
+ setAccuracy(float): void  [API 1+]
+ setAltitude(double): void  [API 1+]
+ setBearing(float): void  [API 1+]
- setBearingAccuracyDegrees(float): void  [API 26+]
- setElapsedRealtimeNanos(long): void  [API 17+]
- setElapsedRealtimeUncertaintyNanos(double): void  [API 29+]
+ setExtras(Bundle): void  [API 1+]
+ setLatitude(double): void  [API 1+]
+ setLongitude(double): void  [API 1+]
- setMock(boolean): void  [API 31+]
- setMslAltitudeAccuracyMeters(float): void  [API 34+]
- setMslAltitudeMeters(double): void  [API 34+]
+ setProvider(String): void  [API 1+]
+ setSpeed(float): void  [API 1+]
- setSpeedAccuracyMetersPerSecond(float): void  [API 26+]
+ setTime(long): void  [API 1+]
- setVerticalAccuracyMeters(float): void  [API 26+]
+ toString(): String  [API 1+]
~ writeToParcel(Parcel, int): void  [API 1+]
```

## LocationListener — framework; API 1+ (4 +, 0 *, 0 ~, 2 -)

```
- onFlushComplete(int): void  [API 31+]
- onLocationChanged(List): void  [API 31+]
+ onLocationChanged(Location): void  [API 1+]
+ onProviderDisabled(String): void  [API 1+]
+ onProviderEnabled(String): void  [API 1+]
+ onStatusChanged(String, int, Bundle): void  [API 1+, deprecated in 29]
```

## LocationManager — framework; API 1+ (3 +, 0 *, 14 ~, 53 -)

```
- addGpsStatusListener(GpsStatus.Listener): boolean  [API 3+, deprecated in 24]
- addNmeaListener(Executor, OnNmeaMessageListener): boolean  [API 30+]
- addNmeaListener(GpsStatus.NmeaListener): boolean  [API 5+, deprecated in 24]
- addNmeaListener(OnNmeaMessageListener): boolean  [API 24+, deprecated in 30]
- addNmeaListener(OnNmeaMessageListener, Handler): boolean  [API 24+]
~ addProximityAlert(double, double, float, long, PendingIntent): void  [API 1+]
- addTestProvider(String, ProviderProperties): void  [API 31+]
- addTestProvider(String, ProviderProperties, Set): void  [API 31+]
- addTestProvider(String, boolean, boolean, boolean, boolean, boolean, boolean, boolean, int, int): void  [API 3+]
- clearTestProviderEnabled(String): void  [API 3+, deprecated in 29]
- clearTestProviderLocation(String): void  [API 3+, deprecated in 29]
- clearTestProviderStatus(String): void  [API 3+, deprecated in 29]
+ getAllProviders(): List  [API 1+]
~ getBestProvider(Criteria, boolean): String  [API 1+, deprecated in 34]
- getCurrentLocation(String, CancellationSignal, Executor, Consumer): void  [API 30+]
- getCurrentLocation(String, LocationRequest, CancellationSignal, Executor, Consumer): void  [API 31+]
- getGnssAntennaInfos(): List  [API 31+]
- getGnssCapabilities(): GnssCapabilities  [API 30+]
- getGnssHardwareModelName(): String  [API 28+]
- getGnssYearOfHardware(): int  [API 28+]
- getGpsStatus(GpsStatus): GpsStatus  [API 3+, deprecated in 24]
~ getLastKnownLocation(String): Location  [API 1+]
- getProvider(String): LocationProvider  [API 1+, deprecated in 31]
- getProviderProperties(String): ProviderProperties  [API 31+]
+ getProviders(Criteria, boolean): List  [API 1+, deprecated in 34]
+ getProviders(boolean): List  [API 1+]
- hasProvider(String): boolean  [API 31+]
~ isLocationEnabled(): boolean  [API 28+]
~ isProviderEnabled(String): boolean  [API 1+]
- registerAntennaInfoListener(Executor, GnssAntennaInfo.Listener): boolean  [API 30+]
- registerGnssMeasurementsCallback(Executor, GnssMeasurementsEvent.Callback): boolean  [API 30+]
- registerGnssMeasurementsCallback(GnssMeasurementRequest, Executor, GnssMeasurementsEvent.Callback): boolean  [API 31+]
- registerGnssMeasurementsCallback(GnssMeasurementsEvent.Callback): boolean  [API 24+, deprecated in 30]
- registerGnssMeasurementsCallback(GnssMeasurementsEvent.Callback, Handler): boolean  [API 24+]
- registerGnssNavigationMessageCallback(Executor, GnssNavigationMessage.Callback): boolean  [API 30+]
- registerGnssNavigationMessageCallback(GnssNavigationMessage.Callback): boolean  [API 24+, deprecated in 30]
- registerGnssNavigationMessageCallback(GnssNavigationMessage.Callback, Handler): boolean  [API 24+]
- registerGnssStatusCallback(Executor, GnssStatus.Callback): boolean  [API 30+]
- registerGnssStatusCallback(GnssStatus.Callback): boolean  [API 24+, deprecated in 30]
- registerGnssStatusCallback(GnssStatus.Callback, Handler): boolean  [API 24+]
- removeGpsStatusListener(GpsStatus.Listener): void  [API 3+, deprecated in 24]
- removeNmeaListener(GpsStatus.NmeaListener): void  [API 5+, deprecated in 24]
- removeNmeaListener(OnNmeaMessageListener): void  [API 24+]
~ removeProximityAlert(PendingIntent): void  [API 1+]
- removeTestProvider(String): void  [API 3+]
~ removeUpdates(LocationListener): void  [API 1+]
~ removeUpdates(PendingIntent): void  [API 3+]
- requestFlush(String, LocationListener, int): void  [API 31+]
- requestFlush(String, PendingIntent, int): void  [API 31+]
- requestLocationUpdates(String, LocationRequest, Executor, LocationListener): void  [API 31+]
- requestLocationUpdates(String, LocationRequest, PendingIntent): void  [API 31+]
- requestLocationUpdates(String, long, float, Executor, LocationListener): void  [API 30+]
~ requestLocationUpdates(String, long, float, LocationListener): void  [API 1+]
~ requestLocationUpdates(String, long, float, LocationListener, Looper): void  [API 1+]
~ requestLocationUpdates(String, long, float, PendingIntent): void  [API 3+]
- requestLocationUpdates(long, float, Criteria, Executor, LocationListener): void  [API 30+, deprecated in 31]
~ requestLocationUpdates(long, float, Criteria, LocationListener, Looper): void  [API 9+, deprecated in 31]
- requestLocationUpdates(long, float, Criteria, PendingIntent): void  [API 9+, deprecated in 31]
~ requestSingleUpdate(Criteria, LocationListener, Looper): void  [API 9+, deprecated in 30]
- requestSingleUpdate(Criteria, PendingIntent): void  [API 9+, deprecated in 30]
~ requestSingleUpdate(String, LocationListener, Looper): void  [API 9+, deprecated in 30]
- requestSingleUpdate(String, PendingIntent): void  [API 9+, deprecated in 30]
- sendExtraCommand(String, String, Bundle): boolean  [API 3+]
- setTestProviderEnabled(String, boolean): void  [API 3+]
- setTestProviderLocation(String, Location): void  [API 3+]
- setTestProviderStatus(String, int, Bundle, long): void  [API 3+, deprecated in 29]
- unregisterAntennaInfoListener(GnssAntennaInfo.Listener): void  [API 30+]
- unregisterGnssMeasurementsCallback(GnssMeasurementsEvent.Callback): void  [API 24+]
- unregisterGnssNavigationMessageCallback(GnssNavigationMessage.Callback): void  [API 24+]
- unregisterGnssStatusCallback(GnssStatus.Callback): void  [API 24+]
```

## LocationProvider — declaration; API 1+, deprecated in 31 (0 +, 0 *, 0 ~, 11 -)

```
- getAccuracy(): int  [API 1+]
- getName(): String  [API 1+]
- getPowerRequirement(): int  [API 1+]
- hasMonetaryCost(): boolean  [API 1+]
- meetsCriteria(Criteria): boolean  [API 1+]
- requiresCell(): boolean  [API 1+]
- requiresNetwork(): boolean  [API 1+]
- requiresSatellite(): boolean  [API 1+]
- supportsAltitude(): boolean  [API 1+]
- supportsBearing(): boolean  [API 1+]
- supportsSpeed(): boolean  [API 1+]
```

## LocationRequest — declaration; API 31+ (0 +, 12 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 31+]
* describeContents(): int  [API 31+]
* equals(Object): boolean  [API 31+]
* getDurationMillis(): long  [API 31+]
* getIntervalMillis(): long  [API 31+]
* getMaxUpdateDelayMillis(): long  [API 31+]
* getMaxUpdates(): int  [API 31+]
* getMinUpdateDistanceMeters(): float  [API 31+]
* getMinUpdateIntervalMillis(): long  [API 31+]
* getQuality(): int  [API 31+]
* hashCode(): int  [API 31+]
* toString(): String  [API 31+]
* writeToParcel(Parcel, int): void  [API 31+]
```

## LocationRequest.Builder — declaration; API 31+ (0 +, 10 *, 0 ~, 1 -)

```
* Builder(LocationRequest)  [API 31+]
* Builder(long)  [API 31+]
* build(): LocationRequest  [API 31+]
- clearMinUpdateIntervalMillis(): LocationRequest.Builder  [API 31+]
* setDurationMillis(long): LocationRequest.Builder  [API 31+]
* setIntervalMillis(long): LocationRequest.Builder  [API 31+]
* setMaxUpdateDelayMillis(long): LocationRequest.Builder  [API 31+]
* setMaxUpdates(int): LocationRequest.Builder  [API 31+]
* setMinUpdateDistanceMeters(float): LocationRequest.Builder  [API 31+]
* setMinUpdateIntervalMillis(long): LocationRequest.Builder  [API 31+]
* setQuality(int): LocationRequest.Builder  [API 31+]
```

## OnNmeaMessageListener — declaration; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ onNmeaMessage(String, long): void  [API 24+]
```

## SettingInjectorService — declaration; API 19+ (3 +, 0 *, 0 ~, 4 -)

```
- SettingInjectorService(String)  [API 19+]
- onBind(Intent): IBinder  [API 19+]
+ onGetEnabled(): boolean  [API 19+]
+ onGetSummary(): String  [API 19+]
+ onStart(Intent, int): void  [API 19+]
- onStartCommand(Intent, int, int): int  [API 19+]
- refreshSettings(Context): void [static]  [API 29+]
```

