# android.health.connect.datatypes

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ActiveCaloriesBurnedRecord — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
- ACTIVE_CALORIES_TOTAL [field]  [API 34+]
* equals(Object): boolean  [API 34+]
* getEnergy(): Energy  [API 34+]
* hashCode(): int  [API 34+]
```

## ActiveCaloriesBurnedRecord.Builder — declaration; API 34+ (0 +, 4 *, 0 ~, 2 -)

```
* Builder(Metadata, Instant, Instant, Energy)  [API 34+]
* build(): ActiveCaloriesBurnedRecord  [API 34+]
- clearEndZoneOffset(): ActiveCaloriesBurnedRecord.Builder  [API 34+]
- clearStartZoneOffset(): ActiveCaloriesBurnedRecord.Builder  [API 34+]
* setEndZoneOffset(ZoneOffset): ActiveCaloriesBurnedRecord.Builder  [API 34+]
* setStartZoneOffset(ZoneOffset): ActiveCaloriesBurnedRecord.Builder  [API 34+]
```

## AggregationType — declaration; API 34+ (0 +, 0 *, 0 ~, 0 -)

## AppInfo — declaration; API 34+ (0 +, 0 *, 0 ~, 3 -)

```
- getIcon(): Bitmap  [API 34+]
- getName(): String  [API 34+]
- getPackageName(): String  [API 34+]
```

## BasalBodyTemperatureRecord — declaration; API 34+ (0 +, 4 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 34+]
* getMeasurementLocation(): int  [API 34+]
* getTemperature(): Temperature  [API 34+]
* hashCode(): int  [API 34+]
```

## BasalBodyTemperatureRecord.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 1 -)

```
* Builder(Metadata, Instant, int, Temperature)  [API 34+]
* build(): BasalBodyTemperatureRecord  [API 34+]
- clearZoneOffset(): BasalBodyTemperatureRecord.Builder  [API 34+]
* setZoneOffset(ZoneOffset): BasalBodyTemperatureRecord.Builder  [API 34+]
```

## BasalMetabolicRateRecord — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
- BASAL_CALORIES_TOTAL [field]  [API 34+]
* equals(Object): boolean  [API 34+]
* getBasalMetabolicRate(): Power  [API 34+]
* hashCode(): int  [API 34+]
```

## BasalMetabolicRateRecord.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 1 -)

```
* Builder(Metadata, Instant, Power)  [API 34+]
* build(): BasalMetabolicRateRecord  [API 34+]
- clearZoneOffset(): BasalMetabolicRateRecord.Builder  [API 34+]
* setZoneOffset(ZoneOffset): BasalMetabolicRateRecord.Builder  [API 34+]
```

## BloodGlucoseRecord — declaration; API 34+ (0 +, 6 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 34+]
* getLevel(): BloodGlucose  [API 34+]
* getMealType(): int  [API 34+]
* getRelationToMeal(): int  [API 34+]
* getSpecimenSource(): int  [API 34+]
* hashCode(): int  [API 34+]
```

## BloodGlucoseRecord.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 1 -)

```
* Builder(Metadata, Instant, int, BloodGlucose, int, int)  [API 34+]
* build(): BloodGlucoseRecord  [API 34+]
- clearZoneOffset(): BloodGlucoseRecord.Builder  [API 34+]
* setZoneOffset(ZoneOffset): BloodGlucoseRecord.Builder  [API 34+]
```

## BloodGlucoseRecord.RelationToMealType — declaration; API 34+ (0 +, 0 *, 0 ~, 0 -)

## BloodGlucoseRecord.SpecimenSource — declaration; API 34+ (0 +, 0 *, 0 ~, 0 -)

## BloodPressureRecord — declaration; API 34+ (0 +, 6 *, 0 ~, 0 -)

```
- DIASTOLIC_AVG [field]  [API 35+]
- DIASTOLIC_MAX [field]  [API 35+]
- DIASTOLIC_MIN [field]  [API 35+]
- SYSTOLIC_AVG [field]  [API 35+]
- SYSTOLIC_MAX [field]  [API 35+]
- SYSTOLIC_MIN [field]  [API 35+]
* equals(Object): boolean  [API 34+]
* getBodyPosition(): int  [API 34+]
* getDiastolic(): Pressure  [API 34+]
* getMeasurementLocation(): int  [API 34+]
* getSystolic(): Pressure  [API 34+]
* hashCode(): int  [API 34+]
```

## BloodPressureRecord.BloodPressureMeasurementLocation — declaration; API 34+ (0 +, 0 *, 0 ~, 0 -)

## BloodPressureRecord.BodyPosition — declaration; API 34+ (0 +, 0 *, 0 ~, 0 -)

## BloodPressureRecord.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 1 -)

```
* Builder(Metadata, Instant, int, Pressure, Pressure, int)  [API 34+]
* build(): BloodPressureRecord  [API 34+]
- clearZoneOffset(): BloodPressureRecord.Builder  [API 34+]
* setZoneOffset(ZoneOffset): BloodPressureRecord.Builder  [API 34+]
```

## BodyFatRecord — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 34+]
* getPercentage(): Percentage  [API 34+]
* hashCode(): int  [API 34+]
```

## BodyFatRecord.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 1 -)

```
* Builder(Metadata, Instant, Percentage)  [API 34+]
* build(): BodyFatRecord  [API 34+]
- clearZoneOffset(): BodyFatRecord.Builder  [API 34+]
* setZoneOffset(ZoneOffset): BodyFatRecord.Builder  [API 34+]
```

## BodyTemperatureMeasurementLocation — declaration; API 34+ (0 +, 0 *, 0 ~, 0 -)

## BodyTemperatureRecord — declaration; API 34+ (0 +, 4 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 34+]
* getMeasurementLocation(): int  [API 34+]
* getTemperature(): Temperature  [API 34+]
* hashCode(): int  [API 34+]
```

## BodyTemperatureRecord.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 1 -)

```
* Builder(Metadata, Instant, int, Temperature)  [API 34+]
* build(): BodyTemperatureRecord  [API 34+]
- clearZoneOffset(): BodyTemperatureRecord.Builder  [API 34+]
* setZoneOffset(ZoneOffset): BodyTemperatureRecord.Builder  [API 34+]
```

## BodyWaterMassRecord — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 34+]
* getBodyWaterMass(): Mass  [API 34+]
* hashCode(): int  [API 34+]
```

## BodyWaterMassRecord.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 1 -)

```
* Builder(Metadata, Instant, Mass)  [API 34+]
* build(): BodyWaterMassRecord  [API 34+]
- clearZoneOffset(): BodyWaterMassRecord.Builder  [API 34+]
* setZoneOffset(ZoneOffset): BodyWaterMassRecord.Builder  [API 34+]
```

## BoneMassRecord — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 34+]
* getMass(): Mass  [API 34+]
* hashCode(): int  [API 34+]
```

## BoneMassRecord.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 1 -)

```
* Builder(Metadata, Instant, Mass)  [API 34+]
* build(): BoneMassRecord  [API 34+]
- clearZoneOffset(): BoneMassRecord.Builder  [API 34+]
* setZoneOffset(ZoneOffset): BoneMassRecord.Builder  [API 34+]
```

## CervicalMucusRecord — declaration; API 34+ (0 +, 4 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 34+]
* getAppearance(): int  [API 34+]
* getSensation(): int  [API 34+]
* hashCode(): int  [API 34+]
```

## CervicalMucusRecord.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 1 -)

```
* Builder(Metadata, Instant, int, int)  [API 34+]
* build(): CervicalMucusRecord  [API 34+]
- clearZoneOffset(): CervicalMucusRecord.Builder  [API 34+]
* setZoneOffset(ZoneOffset): CervicalMucusRecord.Builder  [API 34+]
```

## CervicalMucusRecord.CervicalMucusAppearance — declaration; API 34+ (0 +, 0 *, 0 ~, 0 -)

## CervicalMucusRecord.CervicalMucusSensation — declaration; API 34+ (0 +, 0 *, 0 ~, 0 -)

## CyclingPedalingCadenceRecord — declaration; API 34+ (0 +, 0 *, 0 ~, 3 -)

```
- RPM_AVG [field]  [API 35+]
- RPM_MAX [field]  [API 35+]
- RPM_MIN [field]  [API 35+]
- equals(Object): boolean  [API 34+]
- getSamples(): List  [API 34+]
- hashCode(): int  [API 34+]
```

## CyclingPedalingCadenceRecord.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 3 -)

```
- Builder(Metadata, Instant, Instant, List)  [API 34+]
* build(): CyclingPedalingCadenceRecord  [API 34+]
- clearEndZoneOffset(): CyclingPedalingCadenceRecord.Builder  [API 34+]
- clearStartZoneOffset(): CyclingPedalingCadenceRecord.Builder  [API 34+]
* setEndZoneOffset(ZoneOffset): CyclingPedalingCadenceRecord.Builder  [API 34+]
* setStartZoneOffset(ZoneOffset): CyclingPedalingCadenceRecord.Builder  [API 34+]
```

## CyclingPedalingCadenceRecord.CyclingPedalingCadenceRecordSample — declaration; API 34+ (0 +, 5 *, 0 ~, 0 -)

```
* CyclingPedalingCadenceRecordSample(double, Instant)  [API 34+]
* equals(Object): boolean  [API 34+]
* getRevolutionsPerMinute(): double  [API 34+]
* getTime(): Instant  [API 34+]
* hashCode(): int  [API 34+]
```

## DataOrigin — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 34+]
* getPackageName(): String  [API 34+]
* hashCode(): int  [API 34+]
```

## DataOrigin.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* build(): DataOrigin  [API 34+]
* setPackageName(String): DataOrigin.Builder  [API 34+]
```

## Device — declaration; API 34+ (0 +, 5 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 34+]
* getManufacturer(): String  [API 34+]
* getModel(): String  [API 34+]
* getType(): int  [API 34+]
* hashCode(): int  [API 34+]
```

## Device.Builder — declaration; API 34+ (0 +, 5 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* build(): Device  [API 34+]
* setManufacturer(String): Device.Builder  [API 34+]
* setModel(String): Device.Builder  [API 34+]
* setType(int): Device.Builder  [API 34+]
```

## DistanceRecord — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
- DISTANCE_TOTAL [field]  [API 34+]
* equals(Object): boolean  [API 34+]
* getDistance(): Length  [API 34+]
* hashCode(): int  [API 34+]
```

## DistanceRecord.Builder — declaration; API 34+ (0 +, 4 *, 0 ~, 2 -)

```
* Builder(Metadata, Instant, Instant, Length)  [API 34+]
* build(): DistanceRecord  [API 34+]
- clearEndZoneOffset(): DistanceRecord.Builder  [API 34+]
- clearStartZoneOffset(): DistanceRecord.Builder  [API 34+]
* setEndZoneOffset(ZoneOffset): DistanceRecord.Builder  [API 34+]
* setStartZoneOffset(ZoneOffset): DistanceRecord.Builder  [API 34+]
```

## ElevationGainedRecord — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
- ELEVATION_GAINED_TOTAL [field]  [API 34+]
* equals(Object): boolean  [API 34+]
* getElevation(): Length  [API 34+]
* hashCode(): int  [API 34+]
```

## ElevationGainedRecord.Builder — declaration; API 34+ (0 +, 4 *, 0 ~, 2 -)

```
* Builder(Metadata, Instant, Instant, Length)  [API 34+]
* build(): ElevationGainedRecord  [API 34+]
- clearEndZoneOffset(): ElevationGainedRecord.Builder  [API 34+]
- clearStartZoneOffset(): ElevationGainedRecord.Builder  [API 34+]
* setEndZoneOffset(ZoneOffset): ElevationGainedRecord.Builder  [API 34+]
* setStartZoneOffset(ZoneOffset): ElevationGainedRecord.Builder  [API 34+]
```

## ExerciseCompletionGoal — declaration; API 35+ (0 +, 0 *, 0 ~, 0 -)

## ExerciseCompletionGoal.ActiveCaloriesBurnedGoal — declaration; API 35+ (0 +, 4 *, 0 ~, 0 -)

```
* ActiveCaloriesBurnedGoal(Energy)  [API 35+]
* equals(Object): boolean  [API 35+]
* getActiveCalories(): Energy  [API 35+]
* hashCode(): int  [API 35+]
```

## ExerciseCompletionGoal.DistanceGoal — declaration; API 35+ (0 +, 4 *, 0 ~, 0 -)

```
* DistanceGoal(Length)  [API 35+]
* equals(Object): boolean  [API 35+]
* getDistance(): Length  [API 35+]
* hashCode(): int  [API 35+]
```

## ExerciseCompletionGoal.DistanceWithVariableRestGoal — declaration; API 35+ (0 +, 5 *, 0 ~, 0 -)

```
* DistanceWithVariableRestGoal(Length, Duration)  [API 35+]
* equals(Object): boolean  [API 35+]
* getDistance(): Length  [API 35+]
* getDuration(): Duration  [API 35+]
* hashCode(): int  [API 35+]
```

## ExerciseCompletionGoal.DurationGoal — declaration; API 35+ (0 +, 4 *, 0 ~, 0 -)

```
* DurationGoal(Duration)  [API 35+]
* equals(Object): boolean  [API 35+]
* getDuration(): Duration  [API 35+]
* hashCode(): int  [API 35+]
```

## ExerciseCompletionGoal.RepetitionsGoal — declaration; API 35+ (0 +, 4 *, 0 ~, 0 -)

```
* RepetitionsGoal(int)  [API 35+]
* equals(Object): boolean  [API 35+]
* getRepetitions(): int  [API 35+]
* hashCode(): int  [API 35+]
```

## ExerciseCompletionGoal.StepsGoal — declaration; API 35+ (0 +, 4 *, 0 ~, 0 -)

```
* StepsGoal(int)  [API 35+]
* equals(Object): boolean  [API 35+]
* getSteps(): int  [API 35+]
* hashCode(): int  [API 35+]
```

## ExerciseCompletionGoal.TotalCaloriesBurnedGoal — declaration; API 35+ (0 +, 4 *, 0 ~, 0 -)

```
* TotalCaloriesBurnedGoal(Energy)  [API 35+]
* equals(Object): boolean  [API 35+]
* getTotalCalories(): Energy  [API 35+]
* hashCode(): int  [API 35+]
```

## ExerciseCompletionGoal.UnknownGoal — declaration; API 35+ (0 +, 0 *, 0 ~, 0 -)

```
- INSTANCE [field]  [API 35+]
```

## ExerciseCompletionGoal.UnspecifiedGoal — declaration; API 35+ (0 +, 0 *, 0 ~, 0 -)

```
- INSTANCE [field]  [API 35+]
```

## ExerciseLap — declaration; API 34+ (0 +, 5 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 34+]
* getEndTime(): Instant  [API 34+]
* getLength(): Length  [API 34+]
* getStartTime(): Instant  [API 34+]
* hashCode(): int  [API 34+]
```

## ExerciseLap.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
* Builder(Instant, Instant)  [API 34+]
* build(): ExerciseLap  [API 34+]
* setLength(Length): ExerciseLap.Builder  [API 34+]
```

## ExercisePerformanceGoal — declaration; API 35+ (0 +, 0 *, 0 ~, 0 -)

## ExercisePerformanceGoal.AmrapGoal — declaration; API 35+ (0 +, 0 *, 0 ~, 0 -)

```
- INSTANCE [field]  [API 35+]
```

## ExercisePerformanceGoal.CadenceGoal — declaration; API 35+ (0 +, 5 *, 0 ~, 0 -)

```
* CadenceGoal(double, double)  [API 35+]
* equals(Object): boolean  [API 35+]
* getMaxRpm(): double  [API 35+]
* getMinRpm(): double  [API 35+]
* hashCode(): int  [API 35+]
```

## ExercisePerformanceGoal.HeartRateGoal — declaration; API 35+ (0 +, 5 *, 0 ~, 0 -)

```
* HeartRateGoal(int, int)  [API 35+]
* equals(Object): boolean  [API 35+]
* getMaxBpm(): int  [API 35+]
* getMinBpm(): int  [API 35+]
* hashCode(): int  [API 35+]
```

## ExercisePerformanceGoal.PowerGoal — declaration; API 35+ (0 +, 5 *, 0 ~, 0 -)

```
* PowerGoal(Power, Power)  [API 35+]
* equals(Object): boolean  [API 35+]
* getMaxPower(): Power  [API 35+]
* getMinPower(): Power  [API 35+]
* hashCode(): int  [API 35+]
```

## ExercisePerformanceGoal.RateOfPerceivedExertionGoal — declaration; API 35+ (0 +, 4 *, 0 ~, 0 -)

```
* RateOfPerceivedExertionGoal(int)  [API 35+]
* equals(Object): boolean  [API 35+]
* getRpe(): int  [API 35+]
* hashCode(): int  [API 35+]
```

## ExercisePerformanceGoal.SpeedGoal — declaration; API 35+ (0 +, 5 *, 0 ~, 0 -)

```
* SpeedGoal(Velocity, Velocity)  [API 35+]
* equals(Object): boolean  [API 35+]
* getMaxSpeed(): Velocity  [API 35+]
* getMinSpeed(): Velocity  [API 35+]
* hashCode(): int  [API 35+]
```

## ExercisePerformanceGoal.UnknownGoal — declaration; API 35+ (0 +, 0 *, 0 ~, 0 -)

```
- INSTANCE [field]  [API 35+]
```

## ExercisePerformanceGoal.WeightGoal — declaration; API 35+ (0 +, 4 *, 0 ~, 0 -)

```
* WeightGoal(Mass)  [API 35+]
* equals(Object): boolean  [API 35+]
* getMass(): Mass  [API 35+]
* hashCode(): int  [API 35+]
```

## ExerciseRoute — declaration; API 34+ (0 +, 6 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* ExerciseRoute(List)  [API 34+]
* describeContents(): int  [API 34+]
* equals(Object): boolean  [API 34+]
* getRouteLocations(): List  [API 34+]
* hashCode(): int  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## ExerciseRoute.Location — declaration; API 34+ (0 +, 10 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* describeContents(): int  [API 34+]
* equals(Object): boolean  [API 34+]
* getAltitude(): Length  [API 34+]
* getHorizontalAccuracy(): Length  [API 34+]
* getLatitude(): double  [API 34+]
* getLongitude(): double  [API 34+]
* getTime(): Instant  [API 34+]
* getVerticalAccuracy(): Length  [API 34+]
* hashCode(): int  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## ExerciseRoute.Location.Builder — declaration; API 34+ (0 +, 5 *, 0 ~, 0 -)

```
* Builder(Instant, double, double)  [API 34+]
* build(): ExerciseRoute.Location  [API 34+]
* setAltitude(Length): ExerciseRoute.Location.Builder  [API 34+]
* setHorizontalAccuracy(Length): ExerciseRoute.Location.Builder  [API 34+]
* setVerticalAccuracy(Length): ExerciseRoute.Location.Builder  [API 34+]
```

## ExerciseSegment — declaration; API 34+ (0 +, 6 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 34+]
* getEndTime(): Instant  [API 34+]
* getRepetitionsCount(): int  [API 34+]
* getSegmentType(): int  [API 34+]
* getStartTime(): Instant  [API 34+]
* hashCode(): int  [API 34+]
```

## ExerciseSegment.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
* Builder(Instant, Instant, int)  [API 34+]
* build(): ExerciseSegment  [API 34+]
* setRepetitionsCount(int): ExerciseSegment.Builder  [API 34+]
```

## ExerciseSegmentType — declaration; API 34+ (0 +, 0 *, 0 ~, 0 -)

## ExerciseSessionRecord — declaration; API 34+ (0 +, 9 *, 0 ~, 1 -)

```
- EXERCISE_DURATION_TOTAL [field]  [API 34+]
* equals(Object): boolean  [API 34+]
* getExerciseType(): int  [API 34+]
* getLaps(): List  [API 34+]
* getNotes(): CharSequence  [API 34+]
* getPlannedExerciseSessionId(): String  [API 35+]
* getRoute(): ExerciseRoute  [API 34+]
* getSegments(): List  [API 34+]
* getTitle(): CharSequence  [API 34+]
- hasRoute(): boolean  [API 34+]
* hashCode(): int  [API 34+]
```

## ExerciseSessionRecord.Builder — declaration; API 34+ (0 +, 10 *, 0 ~, 2 -)

```
* Builder(Metadata, Instant, Instant, int)  [API 34+]
* build(): ExerciseSessionRecord  [API 34+]
- clearEndZoneOffset(): ExerciseSessionRecord.Builder  [API 34+]
- clearStartZoneOffset(): ExerciseSessionRecord.Builder  [API 34+]
* setEndZoneOffset(ZoneOffset): ExerciseSessionRecord.Builder  [API 34+]
* setLaps(List): ExerciseSessionRecord.Builder  [API 34+]
* setNotes(CharSequence): ExerciseSessionRecord.Builder  [API 34+]
* setPlannedExerciseSessionId(String): ExerciseSessionRecord.Builder  [API 35+]
* setRoute(ExerciseRoute): ExerciseSessionRecord.Builder  [API 34+]
* setSegments(List): ExerciseSessionRecord.Builder  [API 34+]
* setStartZoneOffset(ZoneOffset): ExerciseSessionRecord.Builder  [API 34+]
* setTitle(CharSequence): ExerciseSessionRecord.Builder  [API 34+]
```

## ExerciseSessionType — declaration; API 34+ (0 +, 0 *, 0 ~, 0 -)

## FhirResource — declaration; API 36+ (0 +, 3 *, 0 ~, 0 -)

```
* getData(): String  [API 36+]
* getId(): String  [API 36+]
* getType(): int  [API 36+]
```

## FhirResource.Builder — declaration; API 36+ (0 +, 6 *, 0 ~, 1 -)

```
* Builder(FhirResource)  [API 36+]
- Builder(FhirResource.Builder)  [API 36+]
* Builder(int, String, String)  [API 36+]
* build(): FhirResource  [API 36+]
* setData(String): FhirResource.Builder  [API 36+]
* setId(String): FhirResource.Builder  [API 36+]
* setType(int): FhirResource.Builder  [API 36+]
```

## FhirVersion — declaration; API 36+ (0 +, 0 *, 0 ~, 4 -)

```
- getMajor(): int  [API 36+]
- getMinor(): int  [API 36+]
- getPatch(): int  [API 36+]
- parseFhirVersion(String): FhirVersion  [API 36+]
```

## FloorsClimbedRecord — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
- FLOORS_CLIMBED_TOTAL [field]  [API 34+]
* equals(Object): boolean  [API 34+]
* getFloors(): double  [API 34+]
* hashCode(): int  [API 34+]
```

## FloorsClimbedRecord.Builder — declaration; API 34+ (0 +, 4 *, 0 ~, 2 -)

```
* Builder(Metadata, Instant, Instant, double)  [API 34+]
* build(): FloorsClimbedRecord  [API 34+]
- clearEndZoneOffset(): FloorsClimbedRecord.Builder  [API 34+]
- clearStartZoneOffset(): FloorsClimbedRecord.Builder  [API 34+]
* setEndZoneOffset(ZoneOffset): FloorsClimbedRecord.Builder  [API 34+]
* setStartZoneOffset(ZoneOffset): FloorsClimbedRecord.Builder  [API 34+]
```

## HeartRateRecord — declaration; API 34+ (0 +, 0 *, 0 ~, 3 -)

```
- BPM_AVG [field]  [API 34+]
- BPM_MAX [field]  [API 34+]
- BPM_MIN [field]  [API 34+]
- HEART_MEASUREMENTS_COUNT [field]  [API 34+]
- equals(Object): boolean  [API 34+]
- getSamples(): List  [API 34+]
- hashCode(): int  [API 34+]
```

## HeartRateRecord.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 3 -)

```
- Builder(Metadata, Instant, Instant, List)  [API 34+]
* build(): HeartRateRecord  [API 34+]
- clearEndZoneOffset(): HeartRateRecord.Builder  [API 34+]
- clearStartZoneOffset(): HeartRateRecord.Builder  [API 34+]
* setEndZoneOffset(ZoneOffset): HeartRateRecord.Builder  [API 34+]
* setStartZoneOffset(ZoneOffset): HeartRateRecord.Builder  [API 34+]
```

## HeartRateRecord.HeartRateSample — declaration; API 34+ (0 +, 5 *, 0 ~, 0 -)

```
* HeartRateSample(long, Instant)  [API 34+]
* equals(Object): boolean  [API 34+]
* getBeatsPerMinute(): long  [API 34+]
* getTime(): Instant  [API 34+]
* hashCode(): int  [API 34+]
```

## HeartRateVariabilityRmssdRecord — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 34+]
* getHeartRateVariabilityMillis(): double  [API 34+]
* hashCode(): int  [API 34+]
```

## HeartRateVariabilityRmssdRecord.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 1 -)

```
* Builder(Metadata, Instant, double)  [API 34+]
* build(): HeartRateVariabilityRmssdRecord  [API 34+]
- clearZoneOffset(): HeartRateVariabilityRmssdRecord.Builder  [API 34+]
* setZoneOffset(ZoneOffset): HeartRateVariabilityRmssdRecord.Builder  [API 34+]
```

## HeightRecord — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
- HEIGHT_AVG [field]  [API 34+]
- HEIGHT_MAX [field]  [API 34+]
- HEIGHT_MIN [field]  [API 34+]
* equals(Object): boolean  [API 34+]
* getHeight(): Length  [API 34+]
* hashCode(): int  [API 34+]
```

## HeightRecord.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 1 -)

```
* Builder(Metadata, Instant, Length)  [API 34+]
* build(): HeightRecord  [API 34+]
- clearZoneOffset(): HeightRecord.Builder  [API 34+]
* setZoneOffset(ZoneOffset): HeightRecord.Builder  [API 34+]
```

## HydrationRecord — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
- VOLUME_TOTAL [field]  [API 34+]
* equals(Object): boolean  [API 34+]
* getVolume(): Volume  [API 34+]
* hashCode(): int  [API 34+]
```

## HydrationRecord.Builder — declaration; API 34+ (0 +, 4 *, 0 ~, 2 -)

```
* Builder(Metadata, Instant, Instant, Volume)  [API 34+]
* build(): HydrationRecord  [API 34+]
- clearEndZoneOffset(): HydrationRecord.Builder  [API 34+]
- clearStartZoneOffset(): HydrationRecord.Builder  [API 34+]
* setEndZoneOffset(ZoneOffset): HydrationRecord.Builder  [API 34+]
* setStartZoneOffset(ZoneOffset): HydrationRecord.Builder  [API 34+]
```

## InstantRecord — declaration; API 34+ (0 +, 4 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 34+]
* getTime(): Instant  [API 34+]
* getZoneOffset(): ZoneOffset  [API 34+]
* hashCode(): int  [API 34+]
```

## IntermenstrualBleedingRecord — declaration; API 34+ (0 +, 2 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 34+]
* hashCode(): int  [API 34+]
```

## IntermenstrualBleedingRecord.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 1 -)

```
* Builder(Metadata, Instant)  [API 34+]
* build(): IntermenstrualBleedingRecord  [API 34+]
- clearZoneOffset(): IntermenstrualBleedingRecord.Builder  [API 34+]
* setZoneOffset(ZoneOffset): IntermenstrualBleedingRecord.Builder  [API 34+]
```

## IntervalRecord — declaration; API 34+ (0 +, 6 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 34+]
* getEndTime(): Instant  [API 34+]
* getEndZoneOffset(): ZoneOffset  [API 34+]
* getStartTime(): Instant  [API 34+]
* getStartZoneOffset(): ZoneOffset  [API 34+]
* hashCode(): int  [API 34+]
```

## LeanBodyMassRecord — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 34+]
* getMass(): Mass  [API 34+]
* hashCode(): int  [API 34+]
```

## LeanBodyMassRecord.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 1 -)

```
* Builder(Metadata, Instant, Mass)  [API 34+]
* build(): LeanBodyMassRecord  [API 34+]
- clearZoneOffset(): LeanBodyMassRecord.Builder  [API 34+]
* setZoneOffset(ZoneOffset): LeanBodyMassRecord.Builder  [API 34+]
```

## MealType — declaration; API 34+ (0 +, 0 *, 0 ~, 0 -)

## MedicalDataSource — declaration; API 36+ (0 +, 5 *, 0 ~, 0 -)

```
* getDisplayName(): String  [API 36+]
* getFhirBaseUri(): Uri  [API 36+]
* getId(): String  [API 36+]
* getLastDataUpdateTime(): Instant  [API 36+]
* getPackageName(): String  [API 36+]
```

## MedicalDataSource.Builder — declaration; API 36+ (0 +, 8 *, 0 ~, 1 -)

```
* Builder(MedicalDataSource)  [API 36+]
- Builder(MedicalDataSource.Builder)  [API 36+]
* Builder(String, String, Uri, String)  [API 36+]
* build(): MedicalDataSource  [API 36+]
* setDisplayName(String): MedicalDataSource.Builder  [API 36+]
* setFhirBaseUri(Uri): MedicalDataSource.Builder  [API 36+]
* setId(String): MedicalDataSource.Builder  [API 36+]
* setLastDataUpdateTime(Instant): MedicalDataSource.Builder  [API 36+]
* setPackageName(String): MedicalDataSource.Builder  [API 36+]
```

## MedicalResource — declaration; API 36+ (0 +, 5 *, 0 ~, 0 -)

```
* getDataSourceId(): String  [API 36+]
* getFhirResource(): FhirResource  [API 36+]
* getFhirVersion(): FhirVersion  [API 36+]
* getId(): MedicalResourceId  [API 36+]
* getType(): int  [API 36+]
```

## MedicalResource.Builder — declaration; API 36+ (0 +, 7 *, 0 ~, 1 -)

```
* Builder(MedicalResource)  [API 36+]
- Builder(MedicalResource.Builder)  [API 36+]
* Builder(int, String, FhirVersion, FhirResource)  [API 36+]
* build(): MedicalResource  [API 36+]
* setDataSourceId(String): MedicalResource.Builder  [API 36+]
* setFhirResource(FhirResource): MedicalResource.Builder  [API 36+]
* setFhirVersion(FhirVersion): MedicalResource.Builder  [API 36+]
* setType(int): MedicalResource.Builder  [API 36+]
```

## MenstruationFlowRecord — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 34+]
* getFlow(): int  [API 34+]
* hashCode(): int  [API 34+]
```

## MenstruationFlowRecord.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 1 -)

```
* Builder(Metadata, Instant, int)  [API 34+]
* build(): MenstruationFlowRecord  [API 34+]
- clearZoneOffset(): MenstruationFlowRecord.Builder  [API 34+]
* setZoneOffset(ZoneOffset): MenstruationFlowRecord.Builder  [API 34+]
```

## MenstruationFlowRecord.MenstruationFlowType — declaration; API 34+ (0 +, 0 *, 0 ~, 0 -)

## MenstruationPeriodRecord — declaration; API 34+ (0 +, 2 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 34+]
* hashCode(): int  [API 34+]
```

## MenstruationPeriodRecord.Builder — declaration; API 34+ (0 +, 4 *, 0 ~, 2 -)

```
* Builder(Metadata, Instant, Instant)  [API 34+]
* build(): MenstruationPeriodRecord  [API 34+]
- clearEndZoneOffset(): MenstruationPeriodRecord.Builder  [API 34+]
- clearStartZoneOffset(): MenstruationPeriodRecord.Builder  [API 34+]
* setEndZoneOffset(ZoneOffset): MenstruationPeriodRecord.Builder  [API 34+]
* setStartZoneOffset(ZoneOffset): MenstruationPeriodRecord.Builder  [API 34+]
```

## Metadata — declaration; API 34+ (0 +, 9 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 34+]
* getClientRecordId(): String  [API 34+]
* getClientRecordVersion(): long  [API 34+]
* getDataOrigin(): DataOrigin  [API 34+]
* getDevice(): Device  [API 34+]
* getId(): String  [API 34+]
* getLastModifiedTime(): Instant  [API 34+]
* getRecordingMethod(): int  [API 34+]
* hashCode(): int  [API 34+]
```

## Metadata.Builder — declaration; API 34+ (0 +, 9 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* build(): Metadata  [API 34+]
* setClientRecordId(String): Metadata.Builder  [API 34+]
* setClientRecordVersion(long): Metadata.Builder  [API 34+]
* setDataOrigin(DataOrigin): Metadata.Builder  [API 34+]
* setDevice(Device): Metadata.Builder  [API 34+]
* setId(String): Metadata.Builder  [API 34+]
* setLastModifiedTime(Instant): Metadata.Builder  [API 34+]
* setRecordingMethod(int): Metadata.Builder  [API 34+]
```

## MindfulnessSessionRecord — declaration; API 36+ (0 +, 3 *, 0 ~, 0 -)

```
* getMindfulnessSessionType(): int  [API 36+]
* getNotes(): CharSequence  [API 36+]
* getTitle(): CharSequence  [API 36+]
```

## MindfulnessSessionRecord.Builder — declaration; API 36+ (0 +, 6 *, 0 ~, 0 -)

```
* Builder(Metadata, Instant, Instant, int)  [API 36+]
* build(): MindfulnessSessionRecord  [API 36+]
* setEndZoneOffset(ZoneOffset): MindfulnessSessionRecord.Builder  [API 36+]
* setNotes(CharSequence): MindfulnessSessionRecord.Builder  [API 36+]
* setStartZoneOffset(ZoneOffset): MindfulnessSessionRecord.Builder  [API 36+]
* setTitle(CharSequence): MindfulnessSessionRecord.Builder  [API 36+]
```

## NutritionRecord — declaration; API 34+ (0 +, 46 *, 0 ~, 0 -)

```
- BIOTIN_TOTAL [field]  [API 34+]
- CAFFEINE_TOTAL [field]  [API 34+]
- CALCIUM_TOTAL [field]  [API 34+]
- CHLORIDE_TOTAL [field]  [API 34+]
- CHOLESTEROL_TOTAL [field]  [API 34+]
- CHROMIUM_TOTAL [field]  [API 34+]
- COPPER_TOTAL [field]  [API 34+]
- DIETARY_FIBER_TOTAL [field]  [API 34+]
- ENERGY_FROM_FAT_TOTAL [field]  [API 34+]
- ENERGY_TOTAL [field]  [API 34+]
- FOLATE_TOTAL [field]  [API 34+]
- FOLIC_ACID_TOTAL [field]  [API 34+]
- IODINE_TOTAL [field]  [API 34+]
- IRON_TOTAL [field]  [API 34+]
- MAGNESIUM_TOTAL [field]  [API 34+]
- MANGANESE_TOTAL [field]  [API 34+]
- MOLYBDENUM_TOTAL [field]  [API 34+]
- MONOUNSATURATED_FAT_TOTAL [field]  [API 34+]
- NIACIN_TOTAL [field]  [API 34+]
- PANTOTHENIC_ACID_TOTAL [field]  [API 34+]
- PHOSPHORUS_TOTAL [field]  [API 34+]
- POLYUNSATURATED_FAT_TOTAL [field]  [API 34+]
- POTASSIUM_TOTAL [field]  [API 34+]
- PROTEIN_TOTAL [field]  [API 34+]
- RIBOFLAVIN_TOTAL [field]  [API 34+]
- SATURATED_FAT_TOTAL [field]  [API 34+]
- SELENIUM_TOTAL [field]  [API 34+]
- SODIUM_TOTAL [field]  [API 34+]
- SUGAR_TOTAL [field]  [API 34+]
- THIAMIN_TOTAL [field]  [API 34+]
- TOTAL_CARBOHYDRATE_TOTAL [field]  [API 34+]
- TOTAL_FAT_TOTAL [field]  [API 34+]
- TRANS_FAT_TOTAL [field]  [API 35+]
- UNSATURATED_FAT_TOTAL [field]  [API 34+]
- VITAMIN_A_TOTAL [field]  [API 34+]
- VITAMIN_B12_TOTAL [field]  [API 34+]
- VITAMIN_B6_TOTAL [field]  [API 34+]
- VITAMIN_C_TOTAL [field]  [API 34+]
- VITAMIN_D_TOTAL [field]  [API 34+]
- VITAMIN_E_TOTAL [field]  [API 34+]
- VITAMIN_K_TOTAL [field]  [API 34+]
- ZINC_TOTAL [field]  [API 34+]
* equals(Object): boolean  [API 34+]
* getBiotin(): Mass  [API 34+]
* getCaffeine(): Mass  [API 34+]
* getCalcium(): Mass  [API 34+]
* getChloride(): Mass  [API 34+]
* getCholesterol(): Mass  [API 34+]
* getChromium(): Mass  [API 34+]
* getCopper(): Mass  [API 34+]
* getDietaryFiber(): Mass  [API 34+]
* getEnergy(): Energy  [API 34+]
* getEnergyFromFat(): Energy  [API 34+]
* getFolate(): Mass  [API 34+]
* getFolicAcid(): Mass  [API 34+]
* getIodine(): Mass  [API 34+]
* getIron(): Mass  [API 34+]
* getMagnesium(): Mass  [API 34+]
* getManganese(): Mass  [API 34+]
* getMealName(): String  [API 34+]
* getMealType(): int  [API 34+]
* getMolybdenum(): Mass  [API 34+]
* getMonounsaturatedFat(): Mass  [API 34+]
* getNiacin(): Mass  [API 34+]
* getPantothenicAcid(): Mass  [API 34+]
* getPhosphorus(): Mass  [API 34+]
* getPolyunsaturatedFat(): Mass  [API 34+]
* getPotassium(): Mass  [API 34+]
* getProtein(): Mass  [API 34+]
* getRiboflavin(): Mass  [API 34+]
* getSaturatedFat(): Mass  [API 34+]
* getSelenium(): Mass  [API 34+]
* getSodium(): Mass  [API 34+]
* getSugar(): Mass  [API 34+]
* getThiamin(): Mass  [API 34+]
* getTotalCarbohydrate(): Mass  [API 34+]
* getTotalFat(): Mass  [API 34+]
* getTransFat(): Mass  [API 34+]
* getUnsaturatedFat(): Mass  [API 34+]
* getVitaminA(): Mass  [API 34+]
* getVitaminB12(): Mass  [API 34+]
* getVitaminB6(): Mass  [API 34+]
* getVitaminC(): Mass  [API 34+]
* getVitaminD(): Mass  [API 34+]
* getVitaminE(): Mass  [API 34+]
* getVitaminK(): Mass  [API 34+]
* getZinc(): Mass  [API 34+]
* hashCode(): int  [API 34+]
```

## NutritionRecord.Builder — declaration; API 34+ (0 +, 48 *, 0 ~, 2 -)

```
* Builder(Metadata, Instant, Instant)  [API 34+]
* build(): NutritionRecord  [API 34+]
- clearEndZoneOffset(): NutritionRecord.Builder  [API 34+]
- clearStartZoneOffset(): NutritionRecord.Builder  [API 34+]
* setBiotin(Mass): NutritionRecord.Builder  [API 34+]
* setCaffeine(Mass): NutritionRecord.Builder  [API 34+]
* setCalcium(Mass): NutritionRecord.Builder  [API 34+]
* setChloride(Mass): NutritionRecord.Builder  [API 34+]
* setCholesterol(Mass): NutritionRecord.Builder  [API 34+]
* setChromium(Mass): NutritionRecord.Builder  [API 34+]
* setCopper(Mass): NutritionRecord.Builder  [API 34+]
* setDietaryFiber(Mass): NutritionRecord.Builder  [API 34+]
* setEndZoneOffset(ZoneOffset): NutritionRecord.Builder  [API 34+]
* setEnergy(Energy): NutritionRecord.Builder  [API 34+]
* setEnergyFromFat(Energy): NutritionRecord.Builder  [API 34+]
* setFolate(Mass): NutritionRecord.Builder  [API 34+]
* setFolicAcid(Mass): NutritionRecord.Builder  [API 34+]
* setIodine(Mass): NutritionRecord.Builder  [API 34+]
* setIron(Mass): NutritionRecord.Builder  [API 34+]
* setMagnesium(Mass): NutritionRecord.Builder  [API 34+]
* setManganese(Mass): NutritionRecord.Builder  [API 34+]
* setMealName(String): NutritionRecord.Builder  [API 34+]
* setMealType(int): NutritionRecord.Builder  [API 34+]
* setMolybdenum(Mass): NutritionRecord.Builder  [API 34+]
* setMonounsaturatedFat(Mass): NutritionRecord.Builder  [API 34+]
* setNiacin(Mass): NutritionRecord.Builder  [API 34+]
* setPantothenicAcid(Mass): NutritionRecord.Builder  [API 34+]
* setPhosphorus(Mass): NutritionRecord.Builder  [API 34+]
* setPolyunsaturatedFat(Mass): NutritionRecord.Builder  [API 34+]
* setPotassium(Mass): NutritionRecord.Builder  [API 34+]
* setProtein(Mass): NutritionRecord.Builder  [API 34+]
* setRiboflavin(Mass): NutritionRecord.Builder  [API 34+]
* setSaturatedFat(Mass): NutritionRecord.Builder  [API 34+]
* setSelenium(Mass): NutritionRecord.Builder  [API 34+]
* setSodium(Mass): NutritionRecord.Builder  [API 34+]
* setStartZoneOffset(ZoneOffset): NutritionRecord.Builder  [API 34+]
* setSugar(Mass): NutritionRecord.Builder  [API 34+]
* setThiamin(Mass): NutritionRecord.Builder  [API 34+]
* setTotalCarbohydrate(Mass): NutritionRecord.Builder  [API 34+]
* setTotalFat(Mass): NutritionRecord.Builder  [API 34+]
* setTransFat(Mass): NutritionRecord.Builder  [API 34+]
* setUnsaturatedFat(Mass): NutritionRecord.Builder  [API 34+]
* setVitaminA(Mass): NutritionRecord.Builder  [API 34+]
* setVitaminB12(Mass): NutritionRecord.Builder  [API 34+]
* setVitaminB6(Mass): NutritionRecord.Builder  [API 34+]
* setVitaminC(Mass): NutritionRecord.Builder  [API 34+]
* setVitaminD(Mass): NutritionRecord.Builder  [API 34+]
* setVitaminE(Mass): NutritionRecord.Builder  [API 34+]
* setVitaminK(Mass): NutritionRecord.Builder  [API 34+]
* setZinc(Mass): NutritionRecord.Builder  [API 34+]
```

## OvulationTestRecord — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 34+]
* getResult(): int  [API 34+]
* hashCode(): int  [API 34+]
```

## OvulationTestRecord.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 1 -)

```
* Builder(Metadata, Instant, int)  [API 34+]
* build(): OvulationTestRecord  [API 34+]
- clearZoneOffset(): OvulationTestRecord.Builder  [API 34+]
* setZoneOffset(ZoneOffset): OvulationTestRecord.Builder  [API 34+]
```

## OvulationTestRecord.OvulationTestResult — declaration; API 34+ (0 +, 0 *, 0 ~, 0 -)

## OxygenSaturationRecord — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 34+]
* getPercentage(): Percentage  [API 34+]
* hashCode(): int  [API 34+]
```

## OxygenSaturationRecord.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 1 -)

```
* Builder(Metadata, Instant, Percentage)  [API 34+]
* build(): OxygenSaturationRecord  [API 34+]
- clearZoneOffset(): OxygenSaturationRecord.Builder  [API 34+]
* setZoneOffset(ZoneOffset): OxygenSaturationRecord.Builder  [API 34+]
```

## PlannedExerciseBlock — declaration; API 35+ (0 +, 5 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 35+]
* getDescription(): CharSequence  [API 35+]
* getRepetitions(): int  [API 35+]
* getSteps(): List  [API 35+]
* hashCode(): int  [API 35+]
```

## PlannedExerciseBlock.Builder — declaration; API 35+ (0 +, 7 *, 0 ~, 0 -)

```
* Builder(int)  [API 35+]
* addStep(PlannedExerciseStep): PlannedExerciseBlock.Builder  [API 35+]
* build(): PlannedExerciseBlock  [API 35+]
* clearSteps(): PlannedExerciseBlock.Builder  [API 35+]
* setDescription(CharSequence): PlannedExerciseBlock.Builder  [API 35+]
* setRepetitions(int): PlannedExerciseBlock.Builder  [API 35+]
* setSteps(List): PlannedExerciseBlock.Builder  [API 35+]
```

## PlannedExerciseSessionRecord — declaration; API 35+ (0 +, 6 *, 0 ~, 4 -)

```
- equals(Object): boolean  [API 35+]
* getBlocks(): List  [API 35+]
- getCompletedExerciseSessionId(): String  [API 35+]
* getDuration(): Duration  [API 35+]
* getExerciseType(): int  [API 35+]
* getNotes(): CharSequence  [API 35+]
* getStartDate(): LocalDate  [API 35+]
* getTitle(): CharSequence  [API 35+]
- hasExplicitTime(): boolean  [API 35+]
- hashCode(): int  [API 35+]
```

## PlannedExerciseSessionRecord.Builder — declaration; API 35+ (0 +, 14 *, 0 ~, 2 -)

```
* Builder(Metadata, int, Instant, Instant)  [API 35+]
* Builder(Metadata, int, LocalDate, Duration)  [API 35+]
* addBlock(PlannedExerciseBlock): PlannedExerciseSessionRecord.Builder  [API 35+]
* build(): PlannedExerciseSessionRecord  [API 35+]
* clearBlocks(): PlannedExerciseSessionRecord.Builder  [API 35+]
- clearEndZoneOffset(): PlannedExerciseSessionRecord.Builder  [API 35+]
- clearStartZoneOffset(): PlannedExerciseSessionRecord.Builder  [API 35+]
* setBlocks(List): PlannedExerciseSessionRecord.Builder  [API 35+]
* setEndTime(Instant): PlannedExerciseSessionRecord.Builder  [API 35+]
* setEndZoneOffset(ZoneOffset): PlannedExerciseSessionRecord.Builder  [API 35+]
* setExerciseType(int): PlannedExerciseSessionRecord.Builder  [API 35+]
* setMetadata(Metadata): PlannedExerciseSessionRecord.Builder  [API 35+]
* setNotes(CharSequence): PlannedExerciseSessionRecord.Builder  [API 35+]
* setStartTime(Instant): PlannedExerciseSessionRecord.Builder  [API 35+]
* setStartZoneOffset(ZoneOffset): PlannedExerciseSessionRecord.Builder  [API 35+]
* setTitle(CharSequence): PlannedExerciseSessionRecord.Builder  [API 35+]
```

## PlannedExerciseStep — declaration; API 35+ (0 +, 7 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 35+]
* getCompletionGoal(): ExerciseCompletionGoal  [API 35+]
* getDescription(): CharSequence  [API 35+]
* getExerciseCategory(): int  [API 35+]
* getExerciseType(): int  [API 35+]
* getPerformanceGoals(): List  [API 35+]
* hashCode(): int  [API 35+]
```

## PlannedExerciseStep.Builder — declaration; API 35+ (0 +, 9 *, 0 ~, 0 -)

```
* Builder(int, int, ExerciseCompletionGoal)  [API 35+]
* addPerformanceGoal(ExercisePerformanceGoal): PlannedExerciseStep.Builder  [API 35+]
* build(): PlannedExerciseStep  [API 35+]
* clearPerformanceGoals(): PlannedExerciseStep.Builder  [API 35+]
* setCompletionGoal(ExerciseCompletionGoal): PlannedExerciseStep.Builder  [API 35+]
* setDescription(CharSequence): PlannedExerciseStep.Builder  [API 35+]
* setExerciseCategory(int): PlannedExerciseStep.Builder  [API 35+]
* setExerciseType(int): PlannedExerciseStep.Builder  [API 35+]
* setPerformanceGoals(List): PlannedExerciseStep.Builder  [API 35+]
```

## PowerRecord — declaration; API 34+ (0 +, 0 *, 0 ~, 3 -)

```
- POWER_AVG [field]  [API 34+]
- POWER_MAX [field]  [API 34+]
- POWER_MIN [field]  [API 34+]
- equals(Object): boolean  [API 34+]
- getSamples(): List  [API 34+]
- hashCode(): int  [API 34+]
```

## PowerRecord.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 3 -)

```
- Builder(Metadata, Instant, Instant, List)  [API 34+]
* build(): PowerRecord  [API 34+]
- clearEndZoneOffset(): PowerRecord.Builder  [API 34+]
- clearStartZoneOffset(): PowerRecord.Builder  [API 34+]
* setEndZoneOffset(ZoneOffset): PowerRecord.Builder  [API 34+]
* setStartZoneOffset(ZoneOffset): PowerRecord.Builder  [API 34+]
```

## PowerRecord.PowerRecordSample — declaration; API 34+ (0 +, 5 *, 0 ~, 0 -)

```
* PowerRecordSample(Power, Instant)  [API 34+]
* equals(Object): boolean  [API 34+]
* getPower(): Power  [API 34+]
* getTime(): Instant  [API 34+]
* hashCode(): int  [API 34+]
```

## Record — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 34+]
* getMetadata(): Metadata  [API 34+]
* hashCode(): int  [API 34+]
```

## RespiratoryRateRecord — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 34+]
* getRate(): double  [API 34+]
* hashCode(): int  [API 34+]
```

## RespiratoryRateRecord.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 1 -)

```
* Builder(Metadata, Instant, double)  [API 34+]
* build(): RespiratoryRateRecord  [API 34+]
- clearZoneOffset(): RespiratoryRateRecord.Builder  [API 34+]
* setZoneOffset(ZoneOffset): RespiratoryRateRecord.Builder  [API 34+]
```

## RestingHeartRateRecord — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
- BPM_AVG [field]  [API 34+]
- BPM_MAX [field]  [API 34+]
- BPM_MIN [field]  [API 34+]
* equals(Object): boolean  [API 34+]
* getBeatsPerMinute(): long  [API 34+]
* hashCode(): int  [API 34+]
```

## RestingHeartRateRecord.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 1 -)

```
* Builder(Metadata, Instant, long)  [API 34+]
* build(): RestingHeartRateRecord  [API 34+]
- clearZoneOffset(): RestingHeartRateRecord.Builder  [API 34+]
* setZoneOffset(ZoneOffset): RestingHeartRateRecord.Builder  [API 34+]
```

## SexualActivityRecord — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 34+]
* getProtectionUsed(): int  [API 34+]
* hashCode(): int  [API 34+]
```

## SexualActivityRecord.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 1 -)

```
* Builder(Metadata, Instant, int)  [API 34+]
* build(): SexualActivityRecord  [API 34+]
- clearZoneOffset(): SexualActivityRecord.Builder  [API 34+]
* setZoneOffset(ZoneOffset): SexualActivityRecord.Builder  [API 34+]
```

## SexualActivityRecord.SexualActivityProtectionUsed — declaration; API 34+ (0 +, 0 *, 0 ~, 0 -)

## SkinTemperatureRecord — declaration; API 35+ (0 +, 5 *, 0 ~, 0 -)

```
- SKIN_TEMPERATURE_DELTA_AVG [field]  [API 35+]
- SKIN_TEMPERATURE_DELTA_MAX [field]  [API 35+]
- SKIN_TEMPERATURE_DELTA_MIN [field]  [API 35+]
* equals(Object): boolean  [API 35+]
* getBaseline(): Temperature  [API 35+]
* getDeltas(): List  [API 35+]
* getMeasurementLocation(): int  [API 35+]
* hashCode(): int  [API 35+]
```

## SkinTemperatureRecord.Builder — declaration; API 35+ (0 +, 7 *, 0 ~, 2 -)

```
* Builder(Metadata, Instant, Instant)  [API 35+]
* build(): SkinTemperatureRecord  [API 35+]
- clearEndZoneOffset(): SkinTemperatureRecord.Builder  [API 35+]
- clearStartZoneOffset(): SkinTemperatureRecord.Builder  [API 35+]
* setBaseline(Temperature): SkinTemperatureRecord.Builder  [API 35+]
* setDeltas(List): SkinTemperatureRecord.Builder  [API 35+]
* setEndZoneOffset(ZoneOffset): SkinTemperatureRecord.Builder  [API 35+]
* setMeasurementLocation(int): SkinTemperatureRecord.Builder  [API 35+]
* setStartZoneOffset(ZoneOffset): SkinTemperatureRecord.Builder  [API 35+]
```

## SkinTemperatureRecord.Delta — declaration; API 35+ (0 +, 5 *, 0 ~, 0 -)

```
* Delta(TemperatureDelta, Instant)  [API 35+]
* equals(Object): boolean  [API 35+]
* getDelta(): TemperatureDelta  [API 35+]
* getTime(): Instant  [API 35+]
* hashCode(): int  [API 35+]
```

## SleepSessionRecord — declaration; API 34+ (0 +, 5 *, 0 ~, 0 -)

```
- SLEEP_DURATION_TOTAL [field]  [API 34+]
* equals(Object): boolean  [API 34+]
* getNotes(): CharSequence  [API 34+]
* getStages(): List  [API 34+]
* getTitle(): CharSequence  [API 34+]
* hashCode(): int  [API 34+]
```

## SleepSessionRecord.Builder — declaration; API 34+ (0 +, 7 *, 0 ~, 2 -)

```
* Builder(Metadata, Instant, Instant)  [API 34+]
* build(): SleepSessionRecord  [API 34+]
- clearEndZoneOffset(): SleepSessionRecord.Builder  [API 34+]
- clearStartZoneOffset(): SleepSessionRecord.Builder  [API 34+]
* setEndZoneOffset(ZoneOffset): SleepSessionRecord.Builder  [API 34+]
* setNotes(CharSequence): SleepSessionRecord.Builder  [API 34+]
* setStages(List): SleepSessionRecord.Builder  [API 34+]
* setStartZoneOffset(ZoneOffset): SleepSessionRecord.Builder  [API 34+]
* setTitle(CharSequence): SleepSessionRecord.Builder  [API 34+]
```

## SleepSessionRecord.Stage — declaration; API 34+ (0 +, 0 *, 0 ~, 6 -)

```
- Stage(Instant, Instant, int)  [API 34+]
- equals(Object): boolean  [API 34+]
- getEndTime(): Instant  [API 34+]
- getStartTime(): Instant  [API 34+]
- getType(): int  [API 34+]
- hashCode(): int  [API 34+]
```

## SleepSessionRecord.StageType — declaration; API 34+ (0 +, 0 *, 0 ~, 0 -)

## SpeedRecord — declaration; API 34+ (0 +, 0 *, 0 ~, 3 -)

```
- SPEED_AVG [field]  [API 35+]
- SPEED_MAX [field]  [API 35+]
- SPEED_MIN [field]  [API 35+]
- equals(Object): boolean  [API 34+]
- getSamples(): List  [API 34+]
- hashCode(): int  [API 34+]
```

## SpeedRecord.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 3 -)

```
- Builder(Metadata, Instant, Instant, List)  [API 34+]
* build(): SpeedRecord  [API 34+]
- clearEndZoneOffset(): SpeedRecord.Builder  [API 34+]
- clearStartZoneOffset(): SpeedRecord.Builder  [API 34+]
* setEndZoneOffset(ZoneOffset): SpeedRecord.Builder  [API 34+]
* setStartZoneOffset(ZoneOffset): SpeedRecord.Builder  [API 34+]
```

## SpeedRecord.SpeedRecordSample — declaration; API 34+ (0 +, 5 *, 0 ~, 0 -)

```
* SpeedRecordSample(Velocity, Instant)  [API 34+]
* equals(Object): boolean  [API 34+]
* getSpeed(): Velocity  [API 34+]
* getTime(): Instant  [API 34+]
* hashCode(): int  [API 34+]
```

## StepsCadenceRecord — declaration; API 34+ (0 +, 0 *, 0 ~, 3 -)

```
- STEPS_CADENCE_RATE_AVG [field]  [API 35+]
- STEPS_CADENCE_RATE_MAX [field]  [API 35+]
- STEPS_CADENCE_RATE_MIN [field]  [API 35+]
- equals(Object): boolean  [API 34+]
- getSamples(): List  [API 34+]
- hashCode(): int  [API 34+]
```

## StepsCadenceRecord.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 3 -)

```
- Builder(Metadata, Instant, Instant, List)  [API 34+]
* build(): StepsCadenceRecord  [API 34+]
- clearEndZoneOffset(): StepsCadenceRecord.Builder  [API 34+]
- clearStartZoneOffset(): StepsCadenceRecord.Builder  [API 34+]
* setEndZoneOffset(ZoneOffset): StepsCadenceRecord.Builder  [API 34+]
* setStartZoneOffset(ZoneOffset): StepsCadenceRecord.Builder  [API 34+]
```

## StepsCadenceRecord.StepsCadenceRecordSample — declaration; API 34+ (0 +, 5 *, 0 ~, 0 -)

```
* StepsCadenceRecordSample(double, Instant)  [API 34+]
* equals(Object): boolean  [API 34+]
* getRate(): double  [API 34+]
* getTime(): Instant  [API 34+]
* hashCode(): int  [API 34+]
```

## StepsRecord — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
- STEPS_COUNT_TOTAL [field]  [API 34+]
* equals(Object): boolean  [API 34+]
* getCount(): long  [API 34+]
* hashCode(): int  [API 34+]
```

## StepsRecord.Builder — declaration; API 34+ (0 +, 4 *, 0 ~, 2 -)

```
* Builder(Metadata, Instant, Instant, long)  [API 34+]
* build(): StepsRecord  [API 34+]
- clearEndZoneOffset(): StepsRecord.Builder  [API 34+]
- clearStartZoneOffset(): StepsRecord.Builder  [API 34+]
* setEndZoneOffset(ZoneOffset): StepsRecord.Builder  [API 34+]
* setStartZoneOffset(ZoneOffset): StepsRecord.Builder  [API 34+]
```

## TotalCaloriesBurnedRecord — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
- ENERGY_TOTAL [field]  [API 34+]
* equals(Object): boolean  [API 34+]
* getEnergy(): Energy  [API 34+]
* hashCode(): int  [API 34+]
```

## TotalCaloriesBurnedRecord.Builder — declaration; API 34+ (0 +, 4 *, 0 ~, 2 -)

```
* Builder(Metadata, Instant, Instant, Energy)  [API 34+]
* build(): TotalCaloriesBurnedRecord  [API 34+]
- clearEndZoneOffset(): TotalCaloriesBurnedRecord.Builder  [API 34+]
- clearStartZoneOffset(): TotalCaloriesBurnedRecord.Builder  [API 34+]
* setEndZoneOffset(ZoneOffset): TotalCaloriesBurnedRecord.Builder  [API 34+]
* setStartZoneOffset(ZoneOffset): TotalCaloriesBurnedRecord.Builder  [API 34+]
```

## Vo2MaxRecord — declaration; API 34+ (0 +, 4 *, 0 ~, 0 -)

```
* equals(Object): boolean  [API 34+]
* getMeasurementMethod(): int  [API 34+]
* getVo2MillilitersPerMinuteKilogram(): double  [API 34+]
* hashCode(): int  [API 34+]
```

## Vo2MaxRecord.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 1 -)

```
* Builder(Metadata, Instant, int, double)  [API 34+]
* build(): Vo2MaxRecord  [API 34+]
- clearZoneOffset(): Vo2MaxRecord.Builder  [API 34+]
* setZoneOffset(ZoneOffset): Vo2MaxRecord.Builder  [API 34+]
```

## Vo2MaxRecord.Vo2MaxMeasurementMethod — declaration; API 34+ (0 +, 0 *, 0 ~, 0 -)

## WeightRecord — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
- WEIGHT_AVG [field]  [API 34+]
- WEIGHT_MAX [field]  [API 34+]
- WEIGHT_MIN [field]  [API 34+]
* equals(Object): boolean  [API 34+]
* getWeight(): Mass  [API 34+]
* hashCode(): int  [API 34+]
```

## WeightRecord.Builder — declaration; API 34+ (0 +, 3 *, 0 ~, 1 -)

```
* Builder(Metadata, Instant, Mass)  [API 34+]
* build(): WeightRecord  [API 34+]
- clearZoneOffset(): WeightRecord.Builder  [API 34+]
* setZoneOffset(ZoneOffset): WeightRecord.Builder  [API 34+]
```

## WheelchairPushesRecord — declaration; API 34+ (0 +, 3 *, 0 ~, 0 -)

```
- WHEEL_CHAIR_PUSHES_COUNT_TOTAL [field]  [API 34+]
* equals(Object): boolean  [API 34+]
* getCount(): long  [API 34+]
* hashCode(): int  [API 34+]
```

## WheelchairPushesRecord.Builder — declaration; API 34+ (0 +, 4 *, 0 ~, 2 -)

```
* Builder(Metadata, Instant, Instant, long)  [API 34+]
* build(): WheelchairPushesRecord  [API 34+]
- clearEndZoneOffset(): WheelchairPushesRecord.Builder  [API 34+]
- clearStartZoneOffset(): WheelchairPushesRecord.Builder  [API 34+]
* setEndZoneOffset(ZoneOffset): WheelchairPushesRecord.Builder  [API 34+]
* setStartZoneOffset(ZoneOffset): WheelchairPushesRecord.Builder  [API 34+]
```

