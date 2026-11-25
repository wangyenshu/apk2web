# java.text

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## Annotation — TeaVM class library; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ Annotation(Object)  [API 1+]
+ getValue(): Object  [API 1+]
+ toString(): String  [API 1+]
```

## AttributedCharacterIterator — TeaVM class library; API 1+ (9 +, 0 *, 0 ~, 0 -)

```
+ getAllAttributeKeys(): Set  [API 1+]
+ getAttribute(AttributedCharacterIterator.Attribute): Object  [API 1+]
+ getAttributes(): Map  [API 1+]
+ getRunLimit(): int  [API 1+]
+ getRunLimit(AttributedCharacterIterator.Attribute): int  [API 1+]
+ getRunLimit(Set): int  [API 1+]
+ getRunStart(): int  [API 1+]
+ getRunStart(AttributedCharacterIterator.Attribute): int  [API 1+]
+ getRunStart(Set): int  [API 1+]
```

## AttributedCharacterIterator.Attribute — TeaVM class library; API 1+ (5 +, 0 *, 0 ~, 1 -)

```
+ Attribute(String)  [API 1+]
+ INPUT_METHOD_SEGMENT [field]  [API 1+]
+ LANGUAGE [field]  [API 1+]
+ READING [field]  [API 1+]
+ equals(Object): boolean  [API 1+]
+ getName(): String  [API 1+]
+ hashCode(): int  [API 1+]
- readResolve(): Object  [API 1+]
+ toString(): String  [API 1+]
```

## AttributedString — TeaVM class library; API 1+ (11 +, 0 *, 0 ~, 0 -)

```
+ AttributedString(AttributedCharacterIterator)  [API 1+]
+ AttributedString(AttributedCharacterIterator, int, int)  [API 1+]
+ AttributedString(AttributedCharacterIterator, int, int, AttributedCharacterIterator.Attribute[])  [API 1+]
+ AttributedString(String)  [API 1+]
+ AttributedString(String, Map)  [API 1+]
+ addAttribute(AttributedCharacterIterator.Attribute, Object): void  [API 1+]
+ addAttribute(AttributedCharacterIterator.Attribute, Object, int, int): void  [API 1+]
+ addAttributes(Map, int, int): void  [API 1+]
+ getIterator(): AttributedCharacterIterator  [API 1+]
+ getIterator(AttributedCharacterIterator.Attribute[]): AttributedCharacterIterator  [API 1+]
+ getIterator(AttributedCharacterIterator.Attribute[], int, int): AttributedCharacterIterator  [API 1+]
```

## Bidi — declaration; API 1+ (0 +, 0 *, 0 ~, 18 -)

```
- Bidi(AttributedCharacterIterator)  [API 1+]
- Bidi(String, int)  [API 1+]
- Bidi(char[], int, byte[], int, int, int)  [API 1+]
- baseIsLeftToRight(): boolean  [API 1+]
- createLineBidi(int, int): Bidi  [API 1+]
- getBaseLevel(): int  [API 1+]
- getLength(): int  [API 1+]
- getLevelAt(int): int  [API 1+]
- getRunCount(): int  [API 1+]
- getRunLevel(int): int  [API 1+]
- getRunLimit(int): int  [API 1+]
- getRunStart(int): int  [API 1+]
- isLeftToRight(): boolean  [API 1+]
- isMixed(): boolean  [API 1+]
- isRightToLeft(): boolean  [API 1+]
- reorderVisually(byte[], int, Object[], int, int): void [static]  [API 1+]
- requiresBidi(char[], int, int): boolean [static]  [API 1+]
- toString(): String  [API 1+]
```

## BreakIterator — declaration; API 1+ (9 +, 0 *, 0 ~, 17 -)

```
- BreakIterator()  [API 1+]
- clone(): Object  [API 1+]
+ current(): int  [API 1+]
+ first(): int  [API 1+]
+ following(int): int  [API 1+]
- getAvailableLocales(): Locale[] [static]  [API 1+]
- getCharacterInstance(): BreakIterator [static]  [API 1+]
- getCharacterInstance(Locale): BreakIterator [static]  [API 1+]
- getInt(byte[], int): int  [API 1-8, removed in 9]
- getLineInstance(): BreakIterator [static]  [API 1+]
- getLineInstance(Locale): BreakIterator [static]  [API 1+]
- getLong(byte[], int): long  [API 1-8, removed in 9]
- getSentenceInstance(): BreakIterator [static]  [API 1+]
- getSentenceInstance(Locale): BreakIterator [static]  [API 1+]
- getShort(byte[], int): short  [API 1-8, removed in 9]
+ getText(): CharacterIterator  [API 1+]
- getWordInstance(): BreakIterator [static]  [API 1+]
- getWordInstance(Locale): BreakIterator [static]  [API 1+]
- isBoundary(int): boolean  [API 1+]
+ last(): int  [API 1+]
+ next(): int  [API 1+]
+ next(int): int  [API 1+]
- preceding(int): int  [API 1+]
+ previous(): int  [API 1+]
+ setText(CharacterIterator): void  [API 1+]
- setText(String): void  [API 1+]
```

## CharacterIterator — TeaVM class library; API 1+ (10 +, 0 *, 0 ~, 0 -)

```
+ clone(): Object  [API 1+]
+ current(): char  [API 1+]
+ first(): char  [API 1+]
+ getBeginIndex(): int  [API 1+]
+ getEndIndex(): int  [API 1+]
+ getIndex(): int  [API 1+]
+ last(): char  [API 1+]
+ next(): char  [API 1+]
+ previous(): char  [API 1+]
+ setIndex(int): char  [API 1+]
```

## ChoiceFormat — TeaVM class library; API 1+ (16 +, 0 *, 0 ~, 0 -)

```
+ ChoiceFormat(String)  [API 1+]
+ ChoiceFormat(double[], String[])  [API 1+]
+ applyPattern(String): void  [API 1+]
+ clone(): Object  [API 1+]
+ equals(Object): boolean  [API 1+]
+ format(double, StringBuffer, FieldPosition): StringBuffer  [API 1+]
+ format(long, StringBuffer, FieldPosition): StringBuffer  [API 1+]
+ getFormats(): Object[]  [API 1+]
+ getLimits(): double[]  [API 1+]
+ hashCode(): int  [API 1+]
+ nextDouble(double): double [static]  [API 1+]
+ nextDouble(double, boolean): double [static]  [API 1+]
+ parse(String, ParsePosition): Number  [API 1+]
+ previousDouble(double): double [static]  [API 1+]
+ setChoices(double[], String[]): void  [API 1+]
+ toPattern(): String  [API 1+]
```

## CollationElementIterator — framework (a2w.jdk.text.CollationElementIterator); API 1+ (10 +, 0 *, 1 ~, 0 -)

```
~ getMaxExpansion(int): int  [API 1+]
+ getOffset(): int  [API 1+]
+ next(): int  [API 1+]
+ previous(): int  [API 1+]
+ primaryOrder(int): int [static]  [API 1+]
+ reset(): void  [API 1+]
+ secondaryOrder(int): short [static]  [API 1+]
+ setOffset(int): void  [API 1+]
+ setText(CharacterIterator): void  [API 1+]
+ setText(String): void  [API 1+]
+ tertiaryOrder(int): short [static]  [API 1+]
```

## CollationKey — framework (a2w.jdk.text.CollationKey); API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ CollationKey(String)  [API 9+]
+ compareTo(CollationKey): int  [API 1+]
+ getSourceString(): String  [API 1+]
+ toByteArray(): byte[]  [API 1+]
```

## Collator — framework (a2w.jdk.text.Collator); API 1+ (15 +, 0 *, 0 ~, 0 -)

```
+ Collator()  [API 1+]
+ clone(): Object  [API 1+]
+ compare(Object, Object): int  [API 1+]
+ compare(String, String): int  [API 1+]
+ equals(Object): boolean  [API 1+]
+ equals(String, String): boolean  [API 1+]
+ getAvailableLocales(): Locale[] [static]  [API 1+]
+ getCollationKey(String): CollationKey  [API 1+]
+ getDecomposition(): int  [API 1+]
+ getInstance(): Collator [static]  [API 1+]
+ getInstance(Locale): Collator [static]  [API 1+]
+ getStrength(): int  [API 1+]
+ hashCode(): int  [API 1+]
+ setDecomposition(int): void  [API 1+]
+ setStrength(int): void  [API 1+]
```

## DateFormat — TeaVM class library; API 1+ (27 +, 0 *, 0 ~, 2 -)

```
+ DateFormat()  [API 1+]
+ calendar [field]  [API 1+]
+ clone(): Object  [API 1+]
+ equals(Object): boolean  [API 1+]
+ format(Date): String  [API 1+]
+ format(Date, StringBuffer, FieldPosition): StringBuffer  [API 1+]
+ format(Object, StringBuffer, FieldPosition): StringBuffer  [API 1+]
+ getAvailableLocales(): Locale[] [static]  [API 1+]
+ getCalendar(): Calendar  [API 1+]
+ getDateInstance(): DateFormat [static]  [API 1+]
+ getDateInstance(int): DateFormat [static]  [API 1+]
+ getDateInstance(int, Locale): DateFormat [static]  [API 1+]
+ getDateTimeInstance(): DateFormat [static]  [API 1+]
+ getDateTimeInstance(int, int): DateFormat [static]  [API 1+]
+ getDateTimeInstance(int, int, Locale): DateFormat [static]  [API 1+]
+ getInstance(): DateFormat [static]  [API 1+]
- getNumberFormat(): NumberFormat  [API 1+]
+ getTimeInstance(): DateFormat [static]  [API 1+]
+ getTimeInstance(int): DateFormat [static]  [API 1+]
+ getTimeInstance(int, Locale): DateFormat [static]  [API 1+]
+ getTimeZone(): TimeZone  [API 1+]
+ hashCode(): int  [API 1+]
+ isLenient(): boolean  [API 1+]
+ numberFormat [field]  [API 1+]
+ parse(String): Date  [API 1+]
+ parse(String, ParsePosition): Date  [API 1+]
+ parseObject(String, ParsePosition): Object  [API 1+]
+ setCalendar(Calendar): void  [API 1+]
+ setLenient(boolean): void  [API 1+]
- setNumberFormat(NumberFormat): void  [API 1+]
+ setTimeZone(TimeZone): void  [API 1+]
```

## DateFormat.Field — TeaVM class library; API 1+ (3 +, 0 *, 0 ~, 1 -)

```
+ AM_PM [field]  [API 1+]
+ DAY_OF_MONTH [field]  [API 1+]
+ DAY_OF_WEEK [field]  [API 1+]
+ DAY_OF_WEEK_IN_MONTH [field]  [API 1+]
+ DAY_OF_YEAR [field]  [API 1+]
+ ERA [field]  [API 1+]
+ Field(String, int)  [API 1+]
+ HOUR0 [field]  [API 1+]
+ HOUR1 [field]  [API 1+]
+ HOUR_OF_DAY0 [field]  [API 1+]
+ HOUR_OF_DAY1 [field]  [API 1+]
+ MILLISECOND [field]  [API 1+]
+ MINUTE [field]  [API 1+]
+ MONTH [field]  [API 1+]
+ SECOND [field]  [API 1+]
+ TIME_ZONE [field]  [API 1+]
+ WEEK_OF_MONTH [field]  [API 1+]
+ WEEK_OF_YEAR [field]  [API 1+]
+ YEAR [field]  [API 1+]
+ getCalendarField(): int  [API 1+]
+ ofCalendarField(int): DateFormat.Field [static]  [API 1+]
- readResolve(): Object  [API 1+]
```

## DateFormatSymbols — TeaVM class library; API 1+ (22 +, 0 *, 0 ~, 2 -)

```
+ DateFormatSymbols()  [API 1+]
+ DateFormatSymbols(Locale)  [API 1+]
+ clone(): Object  [API 1+]
+ equals(Object): boolean  [API 1+]
+ getAmPmStrings(): String[]  [API 1+]
- getAvailableLocales(): Locale[] [static]  [API 9+]
+ getEras(): String[]  [API 1+]
- getInstance(): DateFormatSymbols [static]  [API 9+]
+ getInstance(Locale): DateFormatSymbols [static]  [API 9+]
+ getLocalPatternChars(): String  [API 1+]
+ getMonths(): String[]  [API 1+]
+ getShortMonths(): String[]  [API 1+]
+ getShortWeekdays(): String[]  [API 1+]
+ getWeekdays(): String[]  [API 1+]
+ getZoneStrings(): String[][]  [API 1+]
+ hashCode(): int  [API 1+]
+ setAmPmStrings(String[]): void  [API 1+]
+ setEras(String[]): void  [API 1+]
+ setLocalPatternChars(String): void  [API 1+]
+ setMonths(String[]): void  [API 1+]
+ setShortMonths(String[]): void  [API 1+]
+ setShortWeekdays(String[]): void  [API 1+]
+ setWeekdays(String[]): void  [API 1+]
+ setZoneStrings(String[][]): void  [API 1+]
```

## DecimalFormat — TeaVM class library; API 1+ (46 +, 0 *, 0 ~, 3 -)

```
+ DecimalFormat()  [API 1+]
+ DecimalFormat(String)  [API 1+]
+ DecimalFormat(String, DecimalFormatSymbols)  [API 1+]
- applyLocalizedPattern(String): void  [API 1+]
+ applyPattern(String): void  [API 1+]
+ clone(): Object  [API 1+]
+ equals(Object): boolean  [API 1+]
+ format(Object, StringBuffer, FieldPosition): StringBuffer  [API 1+]
+ format(double, StringBuffer, FieldPosition): StringBuffer  [API 1+]
+ format(long, StringBuffer, FieldPosition): StringBuffer  [API 1+]
+ formatToCharacterIterator(Object): AttributedCharacterIterator  [API 1+]
+ getCurrency(): Currency  [API 1+]
+ getDecimalFormatSymbols(): DecimalFormatSymbols  [API 1+]
+ getGroupingSize(): int  [API 1+]
+ getMaximumFractionDigits(): int  [API 1+]
+ getMaximumIntegerDigits(): int  [API 1+]
+ getMinimumFractionDigits(): int  [API 1+]
+ getMinimumIntegerDigits(): int  [API 1+]
+ getMultiplier(): int  [API 1+]
+ getNegativePrefix(): String  [API 1+]
+ getNegativeSuffix(): String  [API 1+]
+ getPositivePrefix(): String  [API 1+]
+ getPositiveSuffix(): String  [API 1+]
+ getRoundingMode(): RoundingMode  [API 1+]
+ hashCode(): int  [API 1+]
+ isDecimalSeparatorAlwaysShown(): boolean  [API 1+]
+ isGroupingUsed(): boolean  [API 1+]
+ isParseBigDecimal(): boolean  [API 1+]
+ isParseIntegerOnly(): boolean  [API 1+]
+ parse(String, ParsePosition): Number  [API 1+]
+ setCurrency(Currency): void  [API 1+]
+ setDecimalFormatSymbols(DecimalFormatSymbols): void  [API 1+]
+ setDecimalSeparatorAlwaysShown(boolean): void  [API 1+]
+ setGroupingSize(int): void  [API 1+]
+ setGroupingUsed(boolean): void  [API 1+]
+ setMaximumFractionDigits(int): void  [API 1+]
+ setMaximumIntegerDigits(int): void  [API 1+]
+ setMinimumFractionDigits(int): void  [API 1+]
+ setMinimumIntegerDigits(int): void  [API 1+]
+ setMultiplier(int): void  [API 1+]
+ setNegativePrefix(String): void  [API 1+]
+ setNegativeSuffix(String): void  [API 1+]
+ setParseBigDecimal(boolean): void  [API 1+]
+ setParseIntegerOnly(boolean): void  [API 1+]
+ setPositivePrefix(String): void  [API 1+]
+ setPositiveSuffix(String): void  [API 1+]
+ setRoundingMode(RoundingMode): void  [API 1+]
- toLocalizedPattern(): String  [API 1+]
- toPattern(): String  [API 1+]
```

## DecimalFormatSymbols — TeaVM class library; API 1+ (32 +, 0 *, 0 ~, 8 -)

```
+ DecimalFormatSymbols()  [API 1+]
+ DecimalFormatSymbols(Locale)  [API 1+]
+ clone(): Object  [API 1+]
+ equals(Object): boolean  [API 1+]
+ getAvailableLocales(): Locale[] [static]  [API 9+]
- getCurrency(): Currency  [API 1+]
- getCurrencySymbol(): String  [API 1+]
+ getDecimalSeparator(): char  [API 1+]
+ getDigit(): char  [API 1+]
+ getExponentSeparator(): String  [API 9+]
+ getGroupingSeparator(): char  [API 1+]
+ getInfinity(): String  [API 1+]
+ getInstance(): DecimalFormatSymbols [static]  [API 9+]
+ getInstance(Locale): DecimalFormatSymbols [static]  [API 9+]
- getInternationalCurrencySymbol(): String  [API 1+]
+ getMinusSign(): char  [API 1+]
+ getMonetaryDecimalSeparator(): char  [API 1+]
- getMonetaryGroupingSeparator(): char  [API 34+]
+ getNaN(): String  [API 1+]
+ getPatternSeparator(): char  [API 1+]
+ getPerMill(): char  [API 1+]
+ getPercent(): char  [API 1+]
+ getZeroDigit(): char  [API 1+]
+ hashCode(): int  [API 1+]
- setCurrency(Currency): void  [API 1+]
- setCurrencySymbol(String): void  [API 1+]
+ setDecimalSeparator(char): void  [API 1+]
+ setDigit(char): void  [API 1+]
+ setExponentSeparator(String): void  [API 9+]
+ setGroupingSeparator(char): void  [API 1+]
+ setInfinity(String): void  [API 1+]
- setInternationalCurrencySymbol(String): void  [API 1+]
+ setMinusSign(char): void  [API 1+]
+ setMonetaryDecimalSeparator(char): void  [API 1+]
- setMonetaryGroupingSeparator(char): void  [API 34+]
+ setNaN(String): void  [API 1+]
+ setPatternSeparator(char): void  [API 1+]
+ setPerMill(char): void  [API 1+]
+ setPercent(char): void  [API 1+]
+ setZeroDigit(char): void  [API 1+]
```

## FieldPosition — TeaVM class library; API 1+ (12 +, 0 *, 0 ~, 0 -)

```
+ FieldPosition(Format.Field)  [API 1+]
+ FieldPosition(Format.Field, int)  [API 1+]
+ FieldPosition(int)  [API 1+]
+ equals(Object): boolean  [API 1+]
+ getBeginIndex(): int  [API 1+]
+ getEndIndex(): int  [API 1+]
+ getField(): int  [API 1+]
+ getFieldAttribute(): Format.Field  [API 1+]
+ hashCode(): int  [API 1+]
+ setBeginIndex(int): void  [API 1+]
+ setEndIndex(int): void  [API 1+]
+ toString(): String  [API 1+]
```

## Format — TeaVM class library; API 1+ (7 +, 0 *, 0 ~, 0 -)

```
+ Format()  [API 1+]
+ clone(): Object  [API 1+]
+ format(Object): String  [API 1+]
+ format(Object, StringBuffer, FieldPosition): StringBuffer  [API 1+]
+ formatToCharacterIterator(Object): AttributedCharacterIterator  [API 1+]
+ parseObject(String): Object  [API 1+]
+ parseObject(String, ParsePosition): Object  [API 1+]
```

## Format.Field — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ Field(String)  [API 1+]
```

## MessageFormat — TeaVM class library; API 1+ (22 +, 0 *, 0 ~, 0 -)

```
+ MessageFormat(String)  [API 1+]
+ MessageFormat(String, Locale)  [API 1+]
+ applyPattern(String): void  [API 1+]
+ clone(): Object  [API 1+]
+ equals(Object): boolean  [API 1+]
+ format(Object, StringBuffer, FieldPosition): StringBuffer  [API 1+]
+ format(Object[], StringBuffer, FieldPosition): StringBuffer  [API 1+]
+ format(String, Object[]): String [static]  [API 1+]
+ formatToCharacterIterator(Object): AttributedCharacterIterator  [API 1+]
+ getFormats(): Format[]  [API 1+]
+ getFormatsByArgumentIndex(): Format[]  [API 1+]
+ getLocale(): Locale  [API 1+]
+ hashCode(): int  [API 1+]
+ parse(String): Object[]  [API 1+]
+ parse(String, ParsePosition): Object[]  [API 1+]
+ parseObject(String, ParsePosition): Object  [API 1+]
+ setFormat(int, Format): void  [API 1+]
+ setFormatByArgumentIndex(int, Format): void  [API 1+]
+ setFormats(Format[]): void  [API 1+]
+ setFormatsByArgumentIndex(Format[]): void  [API 1+]
+ setLocale(Locale): void  [API 1+]
+ toPattern(): String  [API 1+]
```

## MessageFormat.Field — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 1 -)

```
+ ARGUMENT [field]  [API 1+]
+ Field(String)  [API 1+]
- readResolve(): Object  [API 1+]
```

## Normalizer — framework (a2w.jdk.text.Normalizer); API 9+ (2 +, 0 *, 0 ~, 0 -)

```
+ isNormalized(CharSequence, Normalizer.Form): boolean [static]  [API 9+]
+ normalize(CharSequence, Normalizer.Form): String [static]  [API 9+]
```

## Normalizer.Form — framework (a2w.jdk.text.Normalizer$Form); API 9+ (2 +, 0 *, 0 ~, 0 -)

```
+ NFC [field]  [API 9+]
+ NFD [field]  [API 9+]
+ NFKC [field]  [API 9+]
+ NFKD [field]  [API 9+]
+ valueOf(String): Normalizer.Form [static]  [API 9+]
+ values(): Normalizer.Form[] [static]  [API 9+]
```

## NumberFormat — TeaVM class library; API 1+ (39 +, 0 *, 0 ~, 0 -)

```
+ NumberFormat()  [API 1+]
+ clone(): Object  [API 1+]
+ equals(Object): boolean  [API 1+]
+ format(Object, StringBuffer, FieldPosition): StringBuffer  [API 1+]
+ format(double): String  [API 1+]
+ format(double, StringBuffer, FieldPosition): StringBuffer  [API 1+]
+ format(long): String  [API 1+]
+ format(long, StringBuffer, FieldPosition): StringBuffer  [API 1+]
+ getAvailableLocales(): Locale[] [static]  [API 1+]
+ getCurrency(): Currency  [API 1+]
+ getCurrencyInstance(): NumberFormat [static]  [API 1+]
+ getCurrencyInstance(Locale): NumberFormat [static]  [API 1+]
+ getInstance(): NumberFormat [static]  [API 1+]
+ getInstance(Locale): NumberFormat [static]  [API 1+]
+ getIntegerInstance(): NumberFormat [static]  [API 1+]
+ getIntegerInstance(Locale): NumberFormat [static]  [API 1+]
+ getMaximumFractionDigits(): int  [API 1+]
+ getMaximumIntegerDigits(): int  [API 1+]
+ getMinimumFractionDigits(): int  [API 1+]
+ getMinimumIntegerDigits(): int  [API 1+]
+ getNumberInstance(): NumberFormat [static]  [API 1+]
+ getNumberInstance(Locale): NumberFormat [static]  [API 1+]
+ getPercentInstance(): NumberFormat [static]  [API 1+]
+ getPercentInstance(Locale): NumberFormat [static]  [API 1+]
+ getRoundingMode(): RoundingMode  [API 9+]
+ hashCode(): int  [API 1+]
+ isGroupingUsed(): boolean  [API 1+]
+ isParseIntegerOnly(): boolean  [API 1+]
+ parse(String): Number  [API 1+]
+ parse(String, ParsePosition): Number  [API 1+]
+ parseObject(String, ParsePosition): Object  [API 1+]
+ setCurrency(Currency): void  [API 1+]
+ setGroupingUsed(boolean): void  [API 1+]
+ setMaximumFractionDigits(int): void  [API 1+]
+ setMaximumIntegerDigits(int): void  [API 1+]
+ setMinimumFractionDigits(int): void  [API 1+]
+ setMinimumIntegerDigits(int): void  [API 1+]
+ setParseIntegerOnly(boolean): void  [API 1+]
+ setRoundingMode(RoundingMode): void  [API 9+]
```

## NumberFormat.Field — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 1 -)

```
+ CURRENCY [field]  [API 1+]
+ DECIMAL_SEPARATOR [field]  [API 1+]
+ EXPONENT [field]  [API 1+]
+ EXPONENT_SIGN [field]  [API 1+]
+ EXPONENT_SYMBOL [field]  [API 1+]
+ FRACTION [field]  [API 1+]
+ Field(String)  [API 1+]
+ GROUPING_SEPARATOR [field]  [API 1+]
+ INTEGER [field]  [API 1+]
+ PERCENT [field]  [API 1+]
+ PERMILLE [field]  [API 1+]
+ SIGN [field]  [API 1+]
- readResolve(): Object  [API 1+]
```

## ParseException — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ ParseException(String, int)  [API 1+]
+ getErrorOffset(): int  [API 1+]
```

## ParsePosition — TeaVM class library; API 1+ (8 +, 0 *, 0 ~, 0 -)

```
+ ParsePosition(int)  [API 1+]
+ equals(Object): boolean  [API 1+]
+ getErrorIndex(): int  [API 1+]
+ getIndex(): int  [API 1+]
+ hashCode(): int  [API 1+]
+ setErrorIndex(int): void  [API 1+]
+ setIndex(int): void  [API 1+]
+ toString(): String  [API 1+]
```

## RuleBasedCollator — framework (a2w.jdk.text.RuleBasedCollator); API 1+ (9 +, 0 *, 0 ~, 0 -)

```
+ RuleBasedCollator(String)  [API 1+]
+ clone(): Object  [API 1+]
+ compare(String, String): int  [API 1+]
+ equals(Object): boolean  [API 1+]
+ getCollationElementIterator(CharacterIterator): CollationElementIterator  [API 1+]
+ getCollationElementIterator(String): CollationElementIterator  [API 1+]
+ getCollationKey(String): CollationKey  [API 1+]
+ getRules(): String  [API 1+]
+ hashCode(): int  [API 1+]
```

## SimpleDateFormat — TeaVM class library; API 1+ (16 +, 0 *, 0 ~, 2 -)

```
+ SimpleDateFormat()  [API 1+]
+ SimpleDateFormat(String)  [API 1+]
+ SimpleDateFormat(String, DateFormatSymbols)  [API 1+]
+ SimpleDateFormat(String, Locale)  [API 1+]
+ applyLocalizedPattern(String): void  [API 1+]
+ applyPattern(String): void  [API 1+]
+ clone(): Object  [API 1+]
+ equals(Object): boolean  [API 1+]
+ format(Date, StringBuffer, FieldPosition): StringBuffer  [API 1+]
+ formatToCharacterIterator(Object): AttributedCharacterIterator  [API 1+]
- get2DigitYearStart(): Date  [API 1+]
+ getDateFormatSymbols(): DateFormatSymbols  [API 1+]
+ hashCode(): int  [API 1+]
+ parse(String, ParsePosition): Date  [API 1+]
- set2DigitYearStart(Date): void  [API 1+]
+ setDateFormatSymbols(DateFormatSymbols): void  [API 1+]
+ toLocalizedPattern(): String  [API 1+]
+ toPattern(): String  [API 1+]
```

## StringCharacterIterator — framework (a2w.jdk.text.StringCharacterIterator); API 1+ (16 +, 0 *, 0 ~, 0 -)

```
+ StringCharacterIterator(String)  [API 1+]
+ StringCharacterIterator(String, int)  [API 1+]
+ StringCharacterIterator(String, int, int, int)  [API 1+]
+ clone(): Object  [API 1+]
+ current(): char  [API 1+]
+ equals(Object): boolean  [API 1+]
+ first(): char  [API 1+]
+ getBeginIndex(): int  [API 1+]
+ getEndIndex(): int  [API 1+]
+ getIndex(): int  [API 1+]
+ hashCode(): int  [API 1+]
+ last(): char  [API 1+]
+ next(): char  [API 1+]
+ previous(): char  [API 1+]
+ setIndex(int): char  [API 1+]
+ setText(String): void  [API 1+]
```

