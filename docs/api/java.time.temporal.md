# java.time.temporal

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ChronoField — TeaVM class library; API 26+ (15 +, 0 *, 0 ~, 0 -)

```
+ ALIGNED_DAY_OF_WEEK_IN_MONTH [field]  [API 26+]
+ ALIGNED_DAY_OF_WEEK_IN_YEAR [field]  [API 26+]
+ ALIGNED_WEEK_OF_MONTH [field]  [API 26+]
+ ALIGNED_WEEK_OF_YEAR [field]  [API 26+]
+ AMPM_OF_DAY [field]  [API 26+]
+ CLOCK_HOUR_OF_AMPM [field]  [API 26+]
+ CLOCK_HOUR_OF_DAY [field]  [API 26+]
+ DAY_OF_MONTH [field]  [API 26+]
+ DAY_OF_WEEK [field]  [API 26+]
+ DAY_OF_YEAR [field]  [API 26+]
+ EPOCH_DAY [field]  [API 26+]
+ ERA [field]  [API 26+]
+ HOUR_OF_AMPM [field]  [API 26+]
+ HOUR_OF_DAY [field]  [API 26+]
+ INSTANT_SECONDS [field]  [API 26+]
+ MICRO_OF_DAY [field]  [API 26+]
+ MICRO_OF_SECOND [field]  [API 26+]
+ MILLI_OF_DAY [field]  [API 26+]
+ MILLI_OF_SECOND [field]  [API 26+]
+ MINUTE_OF_DAY [field]  [API 26+]
+ MINUTE_OF_HOUR [field]  [API 26+]
+ MONTH_OF_YEAR [field]  [API 26+]
+ NANO_OF_DAY [field]  [API 26+]
+ NANO_OF_SECOND [field]  [API 26+]
+ OFFSET_SECONDS [field]  [API 26+]
+ PROLEPTIC_MONTH [field]  [API 26+]
+ SECOND_OF_DAY [field]  [API 26+]
+ SECOND_OF_MINUTE [field]  [API 26+]
+ YEAR [field]  [API 26+]
+ YEAR_OF_ERA [field]  [API 26+]
+ adjustInto(Temporal, long): Temporal  [API 26+]
+ checkValidIntValue(long): int  [API 26+]
+ checkValidValue(long): long  [API 26+]
+ getBaseUnit(): TemporalUnit  [API 26+]
+ getDisplayName(Locale): String  [API 26+]
+ getFrom(TemporalAccessor): long  [API 26+]
+ getRangeUnit(): TemporalUnit  [API 26+]
+ isDateBased(): boolean  [API 26+]
+ isSupportedBy(TemporalAccessor): boolean  [API 26+]
+ isTimeBased(): boolean  [API 26+]
+ range(): ValueRange  [API 26+]
+ rangeRefinedBy(TemporalAccessor): ValueRange  [API 26+]
+ toString(): String  [API 26+]
+ valueOf(String): ChronoField [static]  [API 26+]
+ values(): ChronoField[] [static]  [API 26+]
```

## ChronoUnit — TeaVM class library; API 26+ (10 +, 0 *, 0 ~, 0 -)

```
+ CENTURIES [field]  [API 26+]
+ DAYS [field]  [API 26+]
+ DECADES [field]  [API 26+]
+ ERAS [field]  [API 26+]
+ FOREVER [field]  [API 26+]
+ HALF_DAYS [field]  [API 26+]
+ HOURS [field]  [API 26+]
+ MICROS [field]  [API 26+]
+ MILLENNIA [field]  [API 26+]
+ MILLIS [field]  [API 26+]
+ MINUTES [field]  [API 26+]
+ MONTHS [field]  [API 26+]
+ NANOS [field]  [API 26+]
+ SECONDS [field]  [API 26+]
+ WEEKS [field]  [API 26+]
+ YEARS [field]  [API 26+]
+ addTo(Temporal, long): Temporal  [API 26+]
+ between(Temporal, Temporal): long  [API 26+]
+ getDuration(): Duration  [API 26+]
+ isDateBased(): boolean  [API 26+]
+ isDurationEstimated(): boolean  [API 26+]
+ isSupportedBy(Temporal): boolean  [API 26+]
+ isTimeBased(): boolean  [API 26+]
+ toString(): String  [API 26+]
+ valueOf(String): ChronoUnit [static]  [API 26+]
+ values(): ChronoUnit[] [static]  [API 26+]
```

## IsoFields — TeaVM class library; API 26+ (0 +, 0 *, 0 ~, 0 -)

```
+ DAY_OF_QUARTER [field]  [API 26+]
+ QUARTER_OF_YEAR [field]  [API 26+]
+ QUARTER_YEARS [field]  [API 26+]
+ WEEK_BASED_YEAR [field]  [API 26+]
+ WEEK_BASED_YEARS [field]  [API 26+]
+ WEEK_OF_WEEK_BASED_YEAR [field]  [API 26+]
```

## JulianFields — TeaVM class library; API 26+ (0 +, 0 *, 0 ~, 0 -)

```
+ JULIAN_DAY [field]  [API 26+]
+ MODIFIED_JULIAN_DAY [field]  [API 26+]
+ RATA_DIE [field]  [API 26+]
```

## Temporal — TeaVM class library; API 26+ (8 +, 0 *, 0 ~, 0 -)

```
+ isSupported(TemporalUnit): boolean  [API 26+]
+ minus(TemporalAmount): Temporal  [API 26+]
+ minus(long, TemporalUnit): Temporal  [API 26+]
+ plus(TemporalAmount): Temporal  [API 26+]
+ plus(long, TemporalUnit): Temporal  [API 26+]
+ until(Temporal, TemporalUnit): long  [API 26+]
+ with(TemporalAdjuster): Temporal  [API 26+]
+ with(TemporalField, long): Temporal  [API 26+]
```

## TemporalAccessor — TeaVM class library; API 26+ (5 +, 0 *, 0 ~, 0 -)

```
+ get(TemporalField): int  [API 26+]
+ getLong(TemporalField): long  [API 26+]
+ isSupported(TemporalField): boolean  [API 26+]
+ query(TemporalQuery): Object  [API 26+]
+ range(TemporalField): ValueRange  [API 26+]
```

## TemporalAdjuster — TeaVM class library; API 26+ (1 +, 0 *, 0 ~, 0 -)

```
+ adjustInto(Temporal): Temporal  [API 26+]
```

## TemporalAdjusters — TeaVM class library; API 26+ (13 +, 0 *, 0 ~, 1 -)

```
+ dayOfWeekInMonth(int, DayOfWeek): TemporalAdjuster [static]  [API 26+]
+ firstDayOfMonth(): TemporalAdjuster [static]  [API 26+]
+ firstDayOfNextMonth(): TemporalAdjuster [static]  [API 26+]
+ firstDayOfNextYear(): TemporalAdjuster [static]  [API 26+]
+ firstDayOfYear(): TemporalAdjuster [static]  [API 26+]
+ firstInMonth(DayOfWeek): TemporalAdjuster [static]  [API 26+]
+ lastDayOfMonth(): TemporalAdjuster [static]  [API 26+]
+ lastDayOfYear(): TemporalAdjuster [static]  [API 26+]
+ lastInMonth(DayOfWeek): TemporalAdjuster [static]  [API 26+]
+ next(DayOfWeek): TemporalAdjuster [static]  [API 26+]
+ nextOrSame(DayOfWeek): TemporalAdjuster [static]  [API 26+]
- ofDateAdjuster(UnaryOperator): TemporalAdjuster [static]  [API 26+]
+ previous(DayOfWeek): TemporalAdjuster [static]  [API 26+]
+ previousOrSame(DayOfWeek): TemporalAdjuster [static]  [API 26+]
```

## TemporalAmount — TeaVM class library; API 26+ (4 +, 0 *, 0 ~, 0 -)

```
+ addTo(Temporal): Temporal  [API 26+]
+ get(TemporalUnit): long  [API 26+]
+ getUnits(): List  [API 26+]
+ subtractFrom(Temporal): Temporal  [API 26+]
```

## TemporalField — TeaVM class library; API 26+ (12 +, 0 *, 0 ~, 0 -)

```
+ adjustInto(Temporal, long): Temporal  [API 26+]
+ getBaseUnit(): TemporalUnit  [API 26+]
+ getDisplayName(Locale): String  [API 26+]
+ getFrom(TemporalAccessor): long  [API 26+]
+ getRangeUnit(): TemporalUnit  [API 26+]
+ isDateBased(): boolean  [API 26+]
+ isSupportedBy(TemporalAccessor): boolean  [API 26+]
+ isTimeBased(): boolean  [API 26+]
+ range(): ValueRange  [API 26+]
+ rangeRefinedBy(TemporalAccessor): ValueRange  [API 26+]
+ resolve(Map, TemporalAccessor, ResolverStyle): TemporalAccessor  [API 26+]
+ toString(): String  [API 26+]
```

## TemporalQueries — TeaVM class library; API 26+ (7 +, 0 *, 0 ~, 0 -)

```
+ chronology(): TemporalQuery [static]  [API 26+]
+ localDate(): TemporalQuery [static]  [API 26+]
+ localTime(): TemporalQuery [static]  [API 26+]
+ offset(): TemporalQuery [static]  [API 26+]
+ precision(): TemporalQuery [static]  [API 26+]
+ zone(): TemporalQuery [static]  [API 26+]
+ zoneId(): TemporalQuery [static]  [API 26+]
```

## TemporalQuery — TeaVM class library; API 26+ (1 +, 0 *, 0 ~, 0 -)

```
+ queryFrom(TemporalAccessor): Object  [API 26+]
```

## TemporalUnit — TeaVM class library; API 26+ (8 +, 0 *, 0 ~, 0 -)

```
+ addTo(Temporal, long): Temporal  [API 26+]
+ between(Temporal, Temporal): long  [API 26+]
+ getDuration(): Duration  [API 26+]
+ isDateBased(): boolean  [API 26+]
+ isDurationEstimated(): boolean  [API 26+]
+ isSupportedBy(Temporal): boolean  [API 26+]
+ isTimeBased(): boolean  [API 26+]
+ toString(): String  [API 26+]
```

## UnsupportedTemporalTypeException — TeaVM class library; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ UnsupportedTemporalTypeException(String)  [API 26+]
+ UnsupportedTemporalTypeException(String, Throwable)  [API 26+]
```

## ValueRange — TeaVM class library; API 26+ (16 +, 0 *, 0 ~, 0 -)

```
+ checkValidIntValue(long, TemporalField): int  [API 26+]
+ checkValidValue(long, TemporalField): long  [API 26+]
+ equals(Object): boolean  [API 26+]
+ getLargestMinimum(): long  [API 26+]
+ getMaximum(): long  [API 26+]
+ getMinimum(): long  [API 26+]
+ getSmallestMaximum(): long  [API 26+]
+ hashCode(): int  [API 26+]
+ isFixed(): boolean  [API 26+]
+ isIntValue(): boolean  [API 26+]
+ isValidIntValue(long): boolean  [API 26+]
+ isValidValue(long): boolean  [API 26+]
+ of(long, long): ValueRange [static]  [API 26+]
+ of(long, long, long): ValueRange [static]  [API 26+]
+ of(long, long, long, long): ValueRange [static]  [API 26+]
+ toString(): String  [API 26+]
```

## WeekFields — TeaVM class library; API 26+ (12 +, 0 *, 0 ~, 0 -)

```
+ ISO [field]  [API 26+]
+ SUNDAY_START [field]  [API 26+]
+ WEEK_BASED_YEARS [field]  [API 26+]
+ dayOfWeek(): TemporalField  [API 26+]
+ equals(Object): boolean  [API 26+]
+ getFirstDayOfWeek(): DayOfWeek  [API 26+]
+ getMinimalDaysInFirstWeek(): int  [API 26+]
+ hashCode(): int  [API 26+]
+ of(DayOfWeek, int): WeekFields [static]  [API 26+]
+ of(Locale): WeekFields [static]  [API 26+]
+ toString(): String  [API 26+]
+ weekBasedYear(): TemporalField  [API 26+]
+ weekOfMonth(): TemporalField  [API 26+]
+ weekOfWeekBasedYear(): TemporalField  [API 26+]
+ weekOfYear(): TemporalField  [API 26+]
```

