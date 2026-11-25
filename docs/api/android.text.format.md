# android.text.format

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## DateFormat — framework; API 3+ (11 +, 0 *, 0 ~, 0 -)

```
+ DateFormat()  [API 3+]
+ format(CharSequence, Calendar): CharSequence [static]  [API 3+]
+ format(CharSequence, Date): CharSequence [static]  [API 3+]
+ format(CharSequence, long): CharSequence [static]  [API 3+]
+ getBestDateTimePattern(Locale, String): String [static]  [API 18+]
+ getDateFormat(Context): DateFormat [static]  [API 3+]
+ getDateFormatOrder(Context): char[] [static]  [API 3+]
+ getLongDateFormat(Context): DateFormat [static]  [API 3+]
+ getMediumDateFormat(Context): DateFormat [static]  [API 3+]
+ getTimeFormat(Context): DateFormat [static]  [API 3+]
+ is24HourFormat(Context): boolean [static]  [API 3+]
```

## DateUtils — framework; API 3+ (17 +, 0 *, 0 ~, 1 -)

```
+ DateUtils()  [API 3+]
+ formatDateRange(Context, Formatter, long, long, int): Formatter [static]  [API 5+]
+ formatDateRange(Context, Formatter, long, long, int, String): Formatter [static]  [API 9+]
+ formatDateRange(Context, long, long, int): String [static]  [API 3+]
+ formatDateTime(Context, long, int): String [static]  [API 3+]
+ formatElapsedTime(StringBuilder, long): String [static]  [API 3+]
+ formatElapsedTime(long): String [static]  [API 3+]
- formatSameDayTime(long, long, int, int): CharSequence [static]  [API 3+]
+ getAMPMString(int): String [static]  [API 3+, deprecated in 17]
+ getDayOfWeekString(int, int): String [static]  [API 3+, deprecated in 17]
+ getMonthString(int, int): String [static]  [API 3+, deprecated in 17]
+ getRelativeDateTimeString(Context, long, long, long, int): CharSequence [static]  [API 3+]
+ getRelativeTimeSpanString(Context, long): CharSequence [static]  [API 3+]
+ getRelativeTimeSpanString(Context, long, boolean): CharSequence [static]  [API 3+]
+ getRelativeTimeSpanString(long): CharSequence [static]  [API 3+]
+ getRelativeTimeSpanString(long, long, long): CharSequence [static]  [API 3+]
+ getRelativeTimeSpanString(long, long, long, int): CharSequence [static]  [API 3+]
+ isToday(long): boolean [static]  [API 3+]
- sameMonthTable [field]  [API 3+, deprecated in 17]
- sameYearTable [field]  [API 3+, deprecated in 17]
```

## Formatter — framework; API 3+ (4 +, 0 *, 0 ~, 0 -)

```
+ Formatter()  [API 3+]
+ formatFileSize(Context, long): String [static]  [API 3+]
+ formatIpAddress(int): String [static]  [API 3+, deprecated in 15]
+ formatShortFileSize(Context, long): String [static]  [API 5+]
```

## Time — framework; API 3+, deprecated in 22 (29 +, 0 *, 0 ~, 0 -)

```
+ Time()  [API 3+]
+ Time(String)  [API 3+]
+ Time(Time)  [API 3+]
+ after(Time): boolean  [API 3+]
+ allDay [field]  [API 3+]
+ before(Time): boolean  [API 3+]
+ clear(String): void  [API 3+]
+ compare(Time, Time): int [static]  [API 3+]
+ format(String): String  [API 3+]
+ format2445(): String  [API 3+]
+ format3339(boolean): String  [API 3+]
+ getActualMaximum(int): int  [API 3+]
+ getCurrentTimezone(): String [static]  [API 3+]
+ getJulianDay(long, long): int [static]  [API 3+]
+ getJulianMondayFromWeeksSinceEpoch(int): int [static]  [API 11+]
+ getWeekNumber(): int  [API 3+]
+ getWeeksSinceEpochFromJulianDay(int, int): int [static]  [API 11+]
+ gmtoff [field]  [API 3+]
+ hour [field]  [API 3+]
+ isDst [field]  [API 3+]
+ isEpoch(Time): boolean [static]  [API 3+]
+ minute [field]  [API 3+]
+ month [field]  [API 3+]
+ monthDay [field]  [API 3+]
+ normalize(boolean): long  [API 3+]
+ parse(String): boolean  [API 3+]
+ parse3339(String): boolean  [API 3+]
+ second [field]  [API 3+]
+ set(Time): void  [API 3+]
+ set(int, int, int): void  [API 3+]
+ set(int, int, int, int, int, int): void  [API 3+]
+ set(long): void  [API 3+]
+ setJulianDay(int): long  [API 3+]
+ setToNow(): void  [API 3+]
+ switchTimezone(String): void  [API 3+]
+ timezone [field]  [API 3+]
+ toMillis(boolean): long  [API 3+]
+ toString(): String  [API 3+]
+ weekDay [field]  [API 3+]
+ year [field]  [API 3+]
+ yearDay [field]  [API 3+]
```

