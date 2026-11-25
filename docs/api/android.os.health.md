# android.os.health

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## HealthStats — declaration; API 24+ (0 +, 0 *, 0 ~, 23 -)

```
- getDataType(): String  [API 24+]
- getMeasurement(int): long  [API 24+]
- getMeasurementKeyAt(int): int  [API 24+]
- getMeasurementKeyCount(): int  [API 24+]
- getMeasurements(int): Map  [API 24+]
- getMeasurementsKeyAt(int): int  [API 24+]
- getMeasurementsKeyCount(): int  [API 24+]
- getStats(int): Map  [API 24+]
- getStatsKeyAt(int): int  [API 24+]
- getStatsKeyCount(): int  [API 24+]
- getTimer(int): TimerStat  [API 24+]
- getTimerCount(int): int  [API 24+]
- getTimerKeyAt(int): int  [API 24+]
- getTimerKeyCount(): int  [API 24+]
- getTimerTime(int): long  [API 24+]
- getTimers(int): Map  [API 24+]
- getTimersKeyAt(int): int  [API 24+]
- getTimersKeyCount(): int  [API 24+]
- hasMeasurement(int): boolean  [API 24+]
- hasMeasurements(int): boolean  [API 24+]
- hasStats(int): boolean  [API 24+]
- hasTimer(int): boolean  [API 24+]
- hasTimers(int): boolean  [API 24+]
```

## PackageHealthStats — declaration; API 24+ (0 +, 0 *, 0 ~, 0 -)

## PidHealthStats — declaration; API 24+ (0 +, 0 *, 0 ~, 0 -)

## ProcessHealthStats — declaration; API 24+ (0 +, 0 *, 0 ~, 0 -)

## ServiceHealthStats — declaration; API 24+ (0 +, 0 *, 0 ~, 0 -)

## SystemHealthManager — framework; API 24+ (0 +, 0 *, 0 ~, 5 -)

```
- getPowerMonitorReadings(List, Executor, OutcomeReceiver): void  [API 35+]
- getSupportedPowerMonitors(Executor, Consumer): void  [API 35+]
- takeMyUidSnapshot(): HealthStats  [API 24+]
- takeUidSnapshot(int): HealthStats  [API 24+]
- takeUidSnapshots(int[]): HealthStats[]  [API 24+]
```

## TimerStat — declaration; API 24+ (0 +, 8 *, 0 ~, 1 -)

```
+ CREATOR [field]  [API 24+]
* TimerStat()  [API 24+]
- TimerStat(Parcel)  [API 24+]
* TimerStat(int, long)  [API 24+]
* describeContents(): int  [API 24+]
* getCount(): int  [API 24+]
* getTime(): long  [API 24+]
* setCount(int): void  [API 24+]
* setTime(long): void  [API 24+]
* writeToParcel(Parcel, int): void  [API 24+]
```

## UidHealthStats — declaration; API 24+ (0 +, 0 *, 0 ~, 0 -)

