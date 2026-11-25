# android.icu.number

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## CompactNotation — declaration; API 30+ (0 +, 0 *, 0 ~, 0 -)

## CurrencyPrecision — declaration; API 30+ (0 +, 0 *, 0 ~, 1 -)

```
- withCurrency(Currency): Precision  [API 30+]
```

## FormattedNumber — declaration; API 30+ (0 +, 0 *, 0 ~, 10 -)

```
- appendTo(Appendable): Appendable  [API 30+]
- charAt(int): char  [API 30+]
- getNounClass(): DisplayOptions.NounClass  [API 35+]
- getOutputUnit(): MeasureUnit  [API 33+]
- length(): int  [API 30+]
- nextPosition(ConstrainedFieldPosition): boolean  [API 30+]
- subSequence(int, int): CharSequence  [API 30+]
- toBigDecimal(): BigDecimal  [API 30+]
- toCharacterIterator(): AttributedCharacterIterator  [API 30+]
- toString(): String  [API 30+]
```

## FormattedNumberRange — declaration; API 30+ (0 +, 0 *, 0 ~, 12 -)

```
- appendTo(Appendable): Appendable  [API 30+]
- charAt(int): char  [API 30+]
- equals(Object): boolean  [API 30+]
- getFirstBigDecimal(): BigDecimal  [API 30+]
- getIdentityResult(): NumberRangeFormatter.RangeIdentityResult  [API 30+]
- getSecondBigDecimal(): BigDecimal  [API 30+]
- hashCode(): int  [API 30+]
- length(): int  [API 30+]
- nextPosition(ConstrainedFieldPosition): boolean  [API 30+]
- subSequence(int, int): CharSequence  [API 30+]
- toCharacterIterator(): AttributedCharacterIterator  [API 30+]
- toString(): String  [API 30+]
```

## FractionPrecision — declaration; API 30+ (0 +, 0 *, 0 ~, 3 -)

```
- withMaxDigits(int): Precision  [API 30+]
- withMinDigits(int): Precision  [API 30+]
- withSignificantDigits(int, int, NumberFormatter.RoundingPriority): Precision  [API 34+]
```

## IntegerWidth — declaration; API 30+ (0 +, 0 *, 0 ~, 2 -)

```
- truncateAt(int): IntegerWidth  [API 30+]
- zeroFillTo(int): IntegerWidth [static]  [API 30+]
```

## LocalizedNumberFormatter — declaration; API 30+ (0 +, 0 *, 0 ~, 5 -)

```
- format(Measure): FormattedNumber  [API 30+]
- format(Number): FormattedNumber  [API 30+]
- format(double): FormattedNumber  [API 30+]
- format(long): FormattedNumber  [API 30+]
- toFormat(): Format  [API 30+]
```

## LocalizedNumberRangeFormatter — declaration; API 30+ (0 +, 0 *, 0 ~, 3 -)

```
- formatRange(Number, Number): FormattedNumberRange  [API 30+]
- formatRange(double, double): FormattedNumberRange  [API 30+]
- formatRange(int, int): FormattedNumberRange  [API 30+]
```

## Notation — declaration; API 30+ (0 +, 0 *, 0 ~, 5 -)

```
- compactLong(): CompactNotation [static]  [API 30+]
- compactShort(): CompactNotation [static]  [API 30+]
- engineering(): ScientificNotation [static]  [API 30+]
- scientific(): ScientificNotation [static]  [API 30+]
- simple(): SimpleNotation [static]  [API 30+]
```

## NumberFormatter — declaration; API 30+ (0 +, 0 *, 0 ~, 3 -)

```
- with(): UnlocalizedNumberFormatter [static]  [API 30+]
- withLocale(Locale): LocalizedNumberFormatter [static]  [API 30+]
- withLocale(ULocale): LocalizedNumberFormatter [static]  [API 30+]
```

## NumberFormatter.DecimalSeparatorDisplay — declaration; API 30+ (2 +, 0 *, 0 ~, 0 -)

```
+ ALWAYS [field]  [API 30+]
+ AUTO [field]  [API 30+]
+ valueOf(String): NumberFormatter.DecimalSeparatorDisplay [static]  [API 30+]
+ values(): NumberFormatter.DecimalSeparatorDisplay[] [static]  [API 30+]
```

## NumberFormatter.GroupingStrategy — declaration; API 30+ (2 +, 0 *, 0 ~, 0 -)

```
+ AUTO [field]  [API 30+]
+ MIN2 [field]  [API 30+]
+ OFF [field]  [API 30+]
+ ON_ALIGNED [field]  [API 30+]
+ THOUSANDS [field]  [API 30+]
+ valueOf(String): NumberFormatter.GroupingStrategy [static]  [API 30+]
+ values(): NumberFormatter.GroupingStrategy[] [static]  [API 30+]
```

## NumberFormatter.RoundingPriority — declaration; API 34+ (2 +, 0 *, 0 ~, 0 -)

```
+ RELAXED [field]  [API 34+]
+ STRICT [field]  [API 34+]
+ valueOf(String): NumberFormatter.RoundingPriority [static]  [API 34+]
+ values(): NumberFormatter.RoundingPriority[] [static]  [API 34+]
```

## NumberFormatter.SignDisplay — declaration; API 30+ (2 +, 0 *, 0 ~, 0 -)

```
+ ACCOUNTING [field]  [API 30+]
+ ACCOUNTING_ALWAYS [field]  [API 30+]
+ ACCOUNTING_EXCEPT_ZERO [field]  [API 30+]
+ ACCOUNTING_NEGATIVE [field]  [API 34+]
+ ALWAYS [field]  [API 30+]
+ AUTO [field]  [API 30+]
+ EXCEPT_ZERO [field]  [API 30+]
+ NEGATIVE [field]  [API 34+]
+ NEVER [field]  [API 30+]
+ valueOf(String): NumberFormatter.SignDisplay [static]  [API 30+]
+ values(): NumberFormatter.SignDisplay[] [static]  [API 30+]
```

## NumberFormatter.TrailingZeroDisplay — declaration; API 34+ (2 +, 0 *, 0 ~, 0 -)

```
+ AUTO [field]  [API 34+]
+ HIDE_IF_WHOLE [field]  [API 34+]
+ valueOf(String): NumberFormatter.TrailingZeroDisplay [static]  [API 34+]
+ values(): NumberFormatter.TrailingZeroDisplay[] [static]  [API 34+]
```

## NumberFormatter.UnitWidth — declaration; API 30+ (2 +, 0 *, 0 ~, 0 -)

```
+ FORMAL [field]  [API 33+]
+ FULL_NAME [field]  [API 30+]
+ HIDDEN [field]  [API 30+]
+ ISO_CODE [field]  [API 30+]
+ NARROW [field]  [API 30+]
+ SHORT [field]  [API 30+]
+ VARIANT [field]  [API 33+]
+ valueOf(String): NumberFormatter.UnitWidth [static]  [API 30+]
+ values(): NumberFormatter.UnitWidth[] [static]  [API 30+]
```

## NumberFormatterSettings — declaration; API 30+ (0 +, 0 *, 0 ~, 17 -)

```
- decimal(NumberFormatter.DecimalSeparatorDisplay): NumberFormatterSettings  [API 30+]
- displayOptions(DisplayOptions): NumberFormatterSettings  [API 35+]
- equals(Object): boolean  [API 30+]
- grouping(NumberFormatter.GroupingStrategy): NumberFormatterSettings  [API 30+]
- hashCode(): int  [API 30+]
- integerWidth(IntegerWidth): NumberFormatterSettings  [API 30+]
- notation(Notation): NumberFormatterSettings  [API 30+]
- perUnit(MeasureUnit): NumberFormatterSettings  [API 30+]
- precision(Precision): NumberFormatterSettings  [API 30+]
- roundingMode(RoundingMode): NumberFormatterSettings  [API 30+]
- scale(Scale): NumberFormatterSettings  [API 30+]
- sign(NumberFormatter.SignDisplay): NumberFormatterSettings  [API 30+]
- symbols(DecimalFormatSymbols): NumberFormatterSettings  [API 30+]
- symbols(NumberingSystem): NumberFormatterSettings  [API 30+]
- unit(MeasureUnit): NumberFormatterSettings  [API 30+]
- unitWidth(NumberFormatter.UnitWidth): NumberFormatterSettings  [API 30+]
- usage(String): NumberFormatterSettings  [API 33+]
```

## NumberRangeFormatter — declaration; API 30+ (0 +, 0 *, 0 ~, 3 -)

```
- with(): UnlocalizedNumberRangeFormatter [static]  [API 30+]
- withLocale(Locale): LocalizedNumberRangeFormatter [static]  [API 30+]
- withLocale(ULocale): LocalizedNumberRangeFormatter [static]  [API 30+]
```

## NumberRangeFormatter.RangeCollapse — declaration; API 30+ (2 +, 0 *, 0 ~, 0 -)

```
+ ALL [field]  [API 30+]
+ AUTO [field]  [API 30+]
+ NONE [field]  [API 30+]
+ UNIT [field]  [API 30+]
+ valueOf(String): NumberRangeFormatter.RangeCollapse [static]  [API 30+]
+ values(): NumberRangeFormatter.RangeCollapse[] [static]  [API 30+]
```

## NumberRangeFormatter.RangeIdentityFallback — declaration; API 30+ (2 +, 0 *, 0 ~, 0 -)

```
+ APPROXIMATELY [field]  [API 30+]
+ APPROXIMATELY_OR_SINGLE_VALUE [field]  [API 30+]
+ RANGE [field]  [API 30+]
+ SINGLE_VALUE [field]  [API 30+]
+ valueOf(String): NumberRangeFormatter.RangeIdentityFallback [static]  [API 30+]
+ values(): NumberRangeFormatter.RangeIdentityFallback[] [static]  [API 30+]
```

## NumberRangeFormatter.RangeIdentityResult — declaration; API 30+ (2 +, 0 *, 0 ~, 0 -)

```
+ EQUAL_AFTER_ROUNDING [field]  [API 30+]
+ EQUAL_BEFORE_ROUNDING [field]  [API 30+]
+ NOT_EQUAL [field]  [API 30+]
+ valueOf(String): NumberRangeFormatter.RangeIdentityResult [static]  [API 30+]
+ values(): NumberRangeFormatter.RangeIdentityResult[] [static]  [API 30+]
```

## NumberRangeFormatterSettings — declaration; API 30+ (0 +, 0 *, 0 ~, 7 -)

```
- collapse(NumberRangeFormatter.RangeCollapse): NumberRangeFormatterSettings  [API 30+]
- equals(Object): boolean  [API 30+]
- hashCode(): int  [API 30+]
- identityFallback(NumberRangeFormatter.RangeIdentityFallback): NumberRangeFormatterSettings  [API 30+]
- numberFormatterBoth(UnlocalizedNumberFormatter): NumberRangeFormatterSettings  [API 30+]
- numberFormatterFirst(UnlocalizedNumberFormatter): NumberRangeFormatterSettings  [API 30+]
- numberFormatterSecond(UnlocalizedNumberFormatter): NumberRangeFormatterSettings  [API 30+]
```

## Precision — declaration; API 30+ (0 +, 0 *, 0 ~, 13 -)

```
- currency(Currency.CurrencyUsage): CurrencyPrecision [static]  [API 30+]
- fixedFraction(int): FractionPrecision [static]  [API 30+]
- fixedSignificantDigits(int): Precision [static]  [API 30+]
- increment(BigDecimal): Precision [static]  [API 30+]
- integer(): FractionPrecision [static]  [API 30+]
- maxFraction(int): FractionPrecision [static]  [API 30+]
- maxSignificantDigits(int): Precision [static]  [API 30+]
- minFraction(int): FractionPrecision [static]  [API 30+]
- minMaxFraction(int, int): FractionPrecision [static]  [API 30+]
- minMaxSignificantDigits(int, int): Precision [static]  [API 30+]
- minSignificantDigits(int): Precision [static]  [API 30+]
- trailingZeroDisplay(NumberFormatter.TrailingZeroDisplay): Precision  [API 34+]
- unlimited(): Precision [static]  [API 30+]
```

## Scale — declaration; API 30+ (0 +, 0 *, 0 ~, 5 -)

```
- byBigDecimal(BigDecimal): Scale [static]  [API 30+]
- byDouble(double): Scale [static]  [API 30+]
- byDoubleAndPowerOfTen(double, int): Scale [static]  [API 30+]
- none(): Scale [static]  [API 30+]
- powerOfTen(int): Scale [static]  [API 30+]
```

## ScientificNotation — declaration; API 30+ (0 +, 0 *, 0 ~, 2 -)

```
- withExponentSignDisplay(NumberFormatter.SignDisplay): ScientificNotation  [API 30+]
- withMinExponentDigits(int): ScientificNotation  [API 30+]
```

## SimpleNotation — declaration; API 30+ (0 +, 0 *, 0 ~, 0 -)

## UnlocalizedNumberFormatter — declaration; API 30+ (0 +, 0 *, 0 ~, 2 -)

```
- locale(Locale): LocalizedNumberFormatter  [API 30+]
- locale(ULocale): LocalizedNumberFormatter  [API 30+]
```

## UnlocalizedNumberRangeFormatter — declaration; API 30+ (0 +, 0 *, 0 ~, 2 -)

```
- locale(Locale): LocalizedNumberRangeFormatter  [API 30+]
- locale(ULocale): LocalizedNumberRangeFormatter  [API 30+]
```

