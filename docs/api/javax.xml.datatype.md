# javax.xml.datatype

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## DatatypeConfigurationException — declaration; API 8+ (4 +, 0 *, 0 ~, 3 -)

```
+ DatatypeConfigurationException()  [API 8+]
+ DatatypeConfigurationException(String)  [API 8+]
+ DatatypeConfigurationException(String, Throwable)  [API 8+]
+ DatatypeConfigurationException(Throwable)  [API 8+]
- printStackTrace(): void  [API 8+]
- printStackTrace(PrintStream): void  [API 8+]
- printStackTrace(PrintWriter): void  [API 8+]
```

## DatatypeConstants — declaration; API 8+ (0 +, 0 *, 0 ~, 0 -)

```
- DATE [field]  [API 8+]
- DATETIME [field]  [API 8+]
- DAYS [field]  [API 8+]
- DURATION [field]  [API 8+]
- DURATION_DAYTIME [field]  [API 8+]
- DURATION_YEARMONTH [field]  [API 8+]
- GDAY [field]  [API 8+]
- GMONTH [field]  [API 8+]
- GMONTHDAY [field]  [API 8+]
- GYEAR [field]  [API 8+]
- GYEARMONTH [field]  [API 8+]
- HOURS [field]  [API 8+]
- MINUTES [field]  [API 8+]
- MONTHS [field]  [API 8+]
- SECONDS [field]  [API 8+]
- TIME [field]  [API 8+]
- YEARS [field]  [API 8+]
```

## DatatypeConstants.Field — declaration; API 8+ (0 +, 0 *, 0 ~, 2 -)

```
- getId(): int  [API 8+]
- toString(): String  [API 8+]
```

## DatatypeFactory — declaration; API 8+ (7 +, 0 *, 0 ~, 17 -)

```
- DATATYPEFACTORY_IMPLEMENTATION_CLASS [field]  [API 8+]
- DatatypeFactory()  [API 8+]
+ newDuration(String): Duration  [API 8+]
+ newDuration(boolean, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigDecimal): Duration  [API 8+]
- newDuration(boolean, int, int, int, int, int, int): Duration  [API 8+]
+ newDuration(long): Duration  [API 8+]
- newDurationDayTime(String): Duration  [API 8+]
- newDurationDayTime(boolean, BigInteger, BigInteger, BigInteger, BigInteger): Duration  [API 8+]
- newDurationDayTime(boolean, int, int, int, int): Duration  [API 8+]
- newDurationDayTime(long): Duration  [API 8+]
- newDurationYearMonth(String): Duration  [API 8+]
- newDurationYearMonth(boolean, BigInteger, BigInteger): Duration  [API 8+]
- newDurationYearMonth(boolean, int, int): Duration  [API 8+]
- newDurationYearMonth(long): Duration  [API 8+]
- newInstance(): DatatypeFactory [static]  [API 8+]
- newInstance(String, ClassLoader): DatatypeFactory [static]  [API 9+]
+ newXMLGregorianCalendar(): XMLGregorianCalendar  [API 8+]
+ newXMLGregorianCalendar(BigInteger, int, int, int, int, int, BigDecimal, int): XMLGregorianCalendar  [API 8+]
+ newXMLGregorianCalendar(GregorianCalendar): XMLGregorianCalendar  [API 8+]
+ newXMLGregorianCalendar(String): XMLGregorianCalendar  [API 8+]
- newXMLGregorianCalendar(int, int, int, int, int, int, int, int): XMLGregorianCalendar  [API 8+]
- newXMLGregorianCalendarDate(int, int, int, int): XMLGregorianCalendar  [API 8+]
- newXMLGregorianCalendarTime(int, int, int, BigDecimal, int): XMLGregorianCalendar  [API 8+]
- newXMLGregorianCalendarTime(int, int, int, int): XMLGregorianCalendar  [API 8+]
- newXMLGregorianCalendarTime(int, int, int, int, int): XMLGregorianCalendar  [API 8+]
```

## Duration — declaration; API 8+ (10 +, 0 *, 0 ~, 17 -)

```
- Duration()  [API 8+]
+ add(Duration): Duration  [API 8+]
+ addTo(Calendar): void  [API 8+]
- addTo(Date): void  [API 8+]
+ compare(Duration): int  [API 8+]
- equals(Object): boolean  [API 8+]
- getDays(): int  [API 8+]
+ getField(DatatypeConstants.Field): Number  [API 8+]
- getHours(): int  [API 8+]
- getMinutes(): int  [API 8+]
- getMonths(): int  [API 8+]
- getSeconds(): int  [API 8+]
+ getSign(): int  [API 8+]
- getTimeInMillis(Calendar): long  [API 8+]
- getTimeInMillis(Date): long  [API 8+]
- getXMLSchemaType(): QName  [API 8+]
- getYears(): int  [API 8+]
+ hashCode(): int  [API 8+]
- isLongerThan(Duration): boolean  [API 8+]
+ isSet(DatatypeConstants.Field): boolean  [API 8+]
- isShorterThan(Duration): boolean  [API 8+]
+ multiply(BigDecimal): Duration  [API 8+]
- multiply(int): Duration  [API 8+]
+ negate(): Duration  [API 8+]
+ normalizeWith(Calendar): Duration  [API 8+]
- subtract(Duration): Duration  [API 8+]
- toString(): String  [API 8+]
```

## XMLGregorianCalendar — declaration; API 8+ (31 +, 6 *, 0 ~, 3 -)

```
* XMLGregorianCalendar()  [API 8+]
+ add(Duration): void  [API 8+]
+ clear(): void  [API 8+]
+ clone(): Object  [API 8+]
+ compare(XMLGregorianCalendar): int  [API 8+]
* equals(Object): boolean  [API 8+]
+ getDay(): int  [API 8+]
+ getEon(): BigInteger  [API 8+]
+ getEonAndYear(): BigInteger  [API 8+]
+ getFractionalSecond(): BigDecimal  [API 8+]
+ getHour(): int  [API 8+]
* getMillisecond(): int  [API 8+]
+ getMinute(): int  [API 8+]
+ getMonth(): int  [API 8+]
+ getSecond(): int  [API 8+]
+ getTimeZone(int): TimeZone  [API 8+]
+ getTimezone(): int  [API 8+]
+ getXMLSchemaType(): QName  [API 8+]
+ getYear(): int  [API 8+]
* hashCode(): int  [API 8+]
+ isValid(): boolean  [API 8+]
+ normalize(): XMLGregorianCalendar  [API 8+]
+ reset(): void  [API 8+]
+ setDay(int): void  [API 8+]
+ setFractionalSecond(BigDecimal): void  [API 8+]
+ setHour(int): void  [API 8+]
* setMillisecond(int): void  [API 8+]
+ setMinute(int): void  [API 8+]
+ setMonth(int): void  [API 8+]
+ setSecond(int): void  [API 8+]
- setTime(int, int, int): void  [API 8+]
- setTime(int, int, int, BigDecimal): void  [API 8+]
- setTime(int, int, int, int): void  [API 8+]
+ setTimezone(int): void  [API 8+]
+ setYear(BigInteger): void  [API 8+]
+ setYear(int): void  [API 8+]
+ toGregorianCalendar(): GregorianCalendar  [API 8+]
+ toGregorianCalendar(TimeZone, Locale, XMLGregorianCalendar): GregorianCalendar  [API 8+]
* toString(): String  [API 8+]
+ toXMLFormat(): String  [API 8+]
```

