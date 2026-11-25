# java.time.zone

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ZoneOffsetTransition — TeaVM class library; API 26+ (15 +, 0 *, 0 ~, 0 -)

```
+ compareTo(ZoneOffsetTransition): int  [API 26+]
+ equals(Object): boolean  [API 26+]
+ getDateTimeAfter(): LocalDateTime  [API 26+]
+ getDateTimeBefore(): LocalDateTime  [API 26+]
+ getDuration(): Duration  [API 26+]
+ getInstant(): Instant  [API 26+]
+ getOffsetAfter(): ZoneOffset  [API 26+]
+ getOffsetBefore(): ZoneOffset  [API 26+]
+ hashCode(): int  [API 26+]
+ isGap(): boolean  [API 26+]
+ isOverlap(): boolean  [API 26+]
+ isValidOffset(ZoneOffset): boolean  [API 26+]
+ of(LocalDateTime, ZoneOffset, ZoneOffset): ZoneOffsetTransition [static]  [API 26+]
+ toEpochSecond(): long  [API 26+]
+ toString(): String  [API 26+]
```

## ZoneOffsetTransitionRule — TeaVM class library; API 26+ (14 +, 0 *, 0 ~, 0 -)

```
+ createTransition(int): ZoneOffsetTransition  [API 26+]
+ equals(Object): boolean  [API 26+]
+ getDayOfMonthIndicator(): int  [API 26+]
+ getDayOfWeek(): DayOfWeek  [API 26+]
+ getLocalTime(): LocalTime  [API 26+]
+ getMonth(): Month  [API 26+]
+ getOffsetAfter(): ZoneOffset  [API 26+]
+ getOffsetBefore(): ZoneOffset  [API 26+]
+ getStandardOffset(): ZoneOffset  [API 26+]
+ getTimeDefinition(): ZoneOffsetTransitionRule.TimeDefinition  [API 26+]
+ hashCode(): int  [API 26+]
+ isMidnightEndOfDay(): boolean  [API 26+]
+ of(Month, int, DayOfWeek, LocalTime, boolean, ZoneOffsetTransitionRule.TimeDefinition, ZoneOffset, ZoneOffset, ZoneOffset): ZoneOffsetTransitionRule [static]  [API 26+]
+ toString(): String  [API 26+]
```

## ZoneOffsetTransitionRule.TimeDefinition — TeaVM class library; API 26+ (3 +, 0 *, 0 ~, 0 -)

```
+ STANDARD [field]  [API 26+]
+ UTC [field]  [API 26+]
+ WALL [field]  [API 26+]
+ createDateTime(LocalDateTime, ZoneOffset, ZoneOffset): LocalDateTime  [API 26+]
+ valueOf(String): ZoneOffsetTransitionRule.TimeDefinition [static]  [API 26+]
+ values(): ZoneOffsetTransitionRule.TimeDefinition[] [static]  [API 26+]
```

## ZoneRules — TeaVM class library; API 26+ (18 +, 0 *, 0 ~, 0 -)

```
+ equals(Object): boolean  [API 26+]
+ getDaylightSavings(Instant): Duration  [API 26+]
+ getOffset(Instant): ZoneOffset  [API 26+]
+ getOffset(LocalDateTime): ZoneOffset  [API 26+]
+ getStandardOffset(Instant): ZoneOffset  [API 26+]
+ getTransition(LocalDateTime): ZoneOffsetTransition  [API 26+]
+ getTransitionRules(): List  [API 26+]
+ getTransitions(): List  [API 26+]
+ getValidOffsets(LocalDateTime): List  [API 26+]
+ hashCode(): int  [API 26+]
+ isDaylightSavings(Instant): boolean  [API 26+]
+ isFixedOffset(): boolean  [API 26+]
+ isValidOffset(LocalDateTime, ZoneOffset): boolean  [API 26+]
+ nextTransition(Instant): ZoneOffsetTransition  [API 26+]
+ of(ZoneOffset): ZoneRules [static]  [API 26+]
+ of(ZoneOffset, ZoneOffset, List, List, List): ZoneRules [static]  [API 26+]
+ previousTransition(Instant): ZoneOffsetTransition  [API 26+]
+ toString(): String  [API 26+]
```

## ZoneRulesException — TeaVM class library; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ ZoneRulesException(String)  [API 26+]
+ ZoneRulesException(String, Throwable)  [API 26+]
```

