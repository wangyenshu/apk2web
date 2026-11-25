# android.icu.text

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AlphabeticIndex — declaration; API 24+ (0 +, 10 *, 0 ~, 13 -)

```
- AlphabeticIndex(Locale)  [API 24+]
* AlphabeticIndex(RuleBasedCollator)  [API 24+]
- AlphabeticIndex(ULocale)  [API 24+]
- addLabels(Locale[]): AlphabeticIndex  [API 24+]
- addLabels(ULocale[]): AlphabeticIndex  [API 24+]
- addLabels(UnicodeSet): AlphabeticIndex  [API 24+]
- addRecord(CharSequence, Object): AlphabeticIndex  [API 24+]
- buildImmutableIndex(): AlphabeticIndex.ImmutableIndex  [API 24+]
- clearRecords(): AlphabeticIndex  [API 24+]
- getBucketCount(): int  [API 24+]
- getBucketIndex(CharSequence): int  [API 24+]
- getBucketLabels(): List  [API 24+]
* getCollator(): RuleBasedCollator  [API 24+]
* getInflowLabel(): String  [API 24+]
* getMaxLabelCount(): int  [API 24+]
* getOverflowLabel(): String  [API 24+]
- getRecordCount(): int  [API 24+]
* getUnderflowLabel(): String  [API 24+]
- iterator(): Iterator  [API 24+]
* setInflowLabel(String): AlphabeticIndex  [API 24+]
* setMaxLabelCount(int): AlphabeticIndex  [API 24+]
* setOverflowLabel(String): AlphabeticIndex  [API 24+]
* setUnderflowLabel(String): AlphabeticIndex  [API 24+]
```

## AlphabeticIndex.Bucket — declaration; API 24+ (0 +, 0 *, 0 ~, 5 -)

```
- getLabel(): String  [API 24+]
- getLabelType(): AlphabeticIndex.Bucket.LabelType  [API 24+]
- iterator(): Iterator  [API 24+]
- size(): int  [API 24+]
- toString(): String  [API 24+]
```

## AlphabeticIndex.Bucket.LabelType — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ INFLOW [field]  [API 24+]
+ NORMAL [field]  [API 24+]
+ OVERFLOW [field]  [API 24+]
+ UNDERFLOW [field]  [API 24+]
+ valueOf(String): AlphabeticIndex.Bucket.LabelType [static]  [API 24+]
+ values(): AlphabeticIndex.Bucket.LabelType[] [static]  [API 24+]
```

## AlphabeticIndex.ImmutableIndex — declaration; API 24+ (0 +, 0 *, 0 ~, 4 -)

```
- getBucket(int): AlphabeticIndex.Bucket  [API 24+]
- getBucketCount(): int  [API 24+]
- getBucketIndex(CharSequence): int  [API 24+]
- iterator(): Iterator  [API 24+]
```

## AlphabeticIndex.Record — declaration; API 24+ (0 +, 0 *, 0 ~, 3 -)

```
- getData(): Object  [API 24+]
- getName(): CharSequence  [API 24+]
- toString(): String  [API 24+]
```

## Bidi — declaration; API 29+ (0 +, 9 *, 0 ~, 50 -)

```
* Bidi()  [API 29+]
- Bidi(AttributedCharacterIterator)  [API 29+]
- Bidi(String, int)  [API 29+]
- Bidi(char[], int, byte[], int, int, int)  [API 29+]
- Bidi(int, int)  [API 29+]
- baseIsLeftToRight(): boolean  [API 29+]
- countParagraphs(): int  [API 29+]
- countRuns(): int  [API 29+]
- createLineBidi(int, int): Bidi  [API 29+]
- getBaseDirection(CharSequence): byte [static]  [API 29+]
- getBaseLevel(): int  [API 29+]
* getCustomClassifier(): BidiClassifier  [API 29+]
- getCustomizedClass(int): int  [API 29+]
- getDirection(): byte  [API 29+]
- getLength(): int  [API 29+]
- getLevelAt(int): byte  [API 29+]
- getLevels(): byte[]  [API 29+]
- getLogicalIndex(int): int  [API 29+]
- getLogicalMap(): int[]  [API 29+]
- getLogicalRun(int): BidiRun  [API 29+]
- getParaLevel(): byte  [API 29+]
- getParagraph(int): BidiRun  [API 29+]
- getParagraphByIndex(int): BidiRun  [API 29+]
- getParagraphIndex(int): int  [API 29+]
- getProcessedLength(): int  [API 29+]
* getReorderingMode(): int  [API 29+]
* getReorderingOptions(): int  [API 29+]
- getResultLength(): int  [API 29+]
- getRunCount(): int  [API 29+]
- getRunLevel(int): int  [API 29+]
- getRunLimit(int): int  [API 29+]
- getRunStart(int): int  [API 29+]
- getText(): char[]  [API 29+]
- getTextAsString(): String  [API 29+]
- getVisualIndex(int): int  [API 29+]
- getVisualMap(): int[]  [API 29+]
- getVisualRun(int): BidiRun  [API 29+]
- invertMap(int[]): int[] [static]  [API 29+]
* isInverse(): boolean  [API 29+]
- isLeftToRight(): boolean  [API 29+]
- isMixed(): boolean  [API 29+]
- isOrderParagraphsLTR(): boolean  [API 29+]
- isRightToLeft(): boolean  [API 29+]
- orderParagraphsLTR(boolean): void  [API 29+]
- reorderLogical(byte[]): int[] [static]  [API 29+]
- reorderVisual(byte[]): int[] [static]  [API 29+]
- reorderVisually(byte[], int, Object[], int, int): void [static]  [API 29+]
- requiresBidi(char[], int, int): boolean [static]  [API 29+]
- setContext(String, String): void  [API 29+]
* setCustomClassifier(BidiClassifier): void  [API 29+]
* setInverse(boolean): void  [API 29+]
- setLine(int, int): Bidi  [API 29+]
- setPara(AttributedCharacterIterator): void  [API 29+]
- setPara(String, byte, byte[]): void  [API 29+]
- setPara(char[], byte, byte[]): void  [API 29+]
* setReorderingMode(int): void  [API 29+]
* setReorderingOptions(int): void  [API 29+]
- writeReordered(int): String  [API 29+]
- writeReverse(String, int): String [static]  [API 29+]
```

## BidiClassifier — declaration; API 29+ (0 +, 3 *, 0 ~, 1 -)

```
* BidiClassifier(Object)  [API 29+]
- classify(int): int  [API 29+]
* getContext(): Object  [API 29+]
* setContext(Object): void  [API 29+]
```

## BidiRun — declaration; API 29+ (0 +, 0 *, 0 ~, 8 -)

```
- getDirection(): byte  [API 29+]
- getEmbeddingLevel(): byte  [API 29+]
- getLength(): int  [API 29+]
- getLimit(): int  [API 29+]
- getStart(): int  [API 29+]
- isEvenRun(): boolean  [API 29+]
- isOddRun(): boolean  [API 29+]
- toString(): String  [API 29+]
```

## BreakIterator — declaration; API 24+ (9 +, 0 *, 0 ~, 24 -)

```
- BreakIterator()  [API 24+]
- clone(): Object  [API 24+]
+ current(): int  [API 24+]
+ first(): int  [API 24+]
+ following(int): int  [API 24+]
- getAvailableLocales(): Locale[] [static]  [API 24+]
- getCharacterInstance(): BreakIterator [static]  [API 24+]
- getCharacterInstance(Locale): BreakIterator [static]  [API 24+]
- getCharacterInstance(ULocale): BreakIterator [static]  [API 24+]
- getLineInstance(): BreakIterator [static]  [API 24+]
- getLineInstance(Locale): BreakIterator [static]  [API 24+]
- getLineInstance(ULocale): BreakIterator [static]  [API 24+]
- getRuleStatus(): int  [API 24+]
- getRuleStatusVec(int[]): int  [API 24+]
- getSentenceInstance(): BreakIterator [static]  [API 24+]
- getSentenceInstance(Locale): BreakIterator [static]  [API 24+]
- getSentenceInstance(ULocale): BreakIterator [static]  [API 24+]
+ getText(): CharacterIterator  [API 24+]
- getTitleInstance(): BreakIterator [static]  [API 24+, deprecated in 29]
- getTitleInstance(Locale): BreakIterator [static]  [API 24+, deprecated in 29]
- getTitleInstance(ULocale): BreakIterator [static]  [API 24+, deprecated in 29]
- getWordInstance(): BreakIterator [static]  [API 24+]
- getWordInstance(Locale): BreakIterator [static]  [API 24+]
- getWordInstance(ULocale): BreakIterator [static]  [API 24+]
- isBoundary(int): boolean  [API 24+]
+ last(): int  [API 24+]
+ next(): int  [API 24+]
+ next(int): int  [API 24+]
- preceding(int): int  [API 24+]
+ previous(): int  [API 24+]
- setText(CharSequence): void  [API 29+]
+ setText(CharacterIterator): void  [API 24+]
- setText(String): void  [API 24+]
```

## CaseMap — declaration; API 29+ (1 +, 0 *, 0 ~, 4 -)

```
- fold(): CaseMap.Fold [static]  [API 29+]
+ omitUnchangedText(): CaseMap  [API 29+]
- toLower(): CaseMap.Lower [static]  [API 29+]
- toTitle(): CaseMap.Title [static]  [API 29+]
- toUpper(): CaseMap.Upper [static]  [API 29+]
```

## CaseMap.Fold — declaration; API 29+ (0 +, 0 *, 0 ~, 4 -)

```
- apply(CharSequence): String  [API 29+]
- apply(CharSequence, Appendable, Edits): Appendable  [API 29+]
- omitUnchangedText(): CaseMap.Fold  [API 29+]
- turkic(): CaseMap.Fold  [API 29+]
```

## CaseMap.Lower — declaration; API 29+ (0 +, 0 *, 0 ~, 3 -)

```
- apply(Locale, CharSequence): String  [API 29+]
- apply(Locale, CharSequence, Appendable, Edits): Appendable  [API 29+]
- omitUnchangedText(): CaseMap.Lower  [API 29+]
```

## CaseMap.Title — declaration; API 29+ (0 +, 0 *, 0 ~, 8 -)

```
- adjustToCased(): CaseMap.Title  [API 29+]
- apply(Locale, BreakIterator, CharSequence): String  [API 29+]
- apply(Locale, BreakIterator, CharSequence, Appendable, Edits): Appendable  [API 29+]
- noBreakAdjustment(): CaseMap.Title  [API 29+]
- noLowercase(): CaseMap.Title  [API 29+]
- omitUnchangedText(): CaseMap.Title  [API 29+]
- sentences(): CaseMap.Title  [API 29+]
- wholeString(): CaseMap.Title  [API 29+]
```

## CaseMap.Upper — declaration; API 29+ (0 +, 0 *, 0 ~, 3 -)

```
- apply(Locale, CharSequence): String  [API 29+]
- apply(Locale, CharSequence, Appendable, Edits): Appendable  [API 29+]
- omitUnchangedText(): CaseMap.Upper  [API 29+]
```

## CollationElementIterator — declaration; API 24+ (0 +, 4 *, 0 ~, 10 -)

```
* equals(Object): boolean  [API 24+]
- getMaxExpansion(int): int  [API 24+]
* getOffset(): int  [API 24+]
* hashCode(): int  [API 24+]
- next(): int  [API 24+]
- previous(): int  [API 24+]
- primaryOrder(int): int [static]  [API 24+]
- reset(): void  [API 24+]
- secondaryOrder(int): int [static]  [API 24+]
* setOffset(int): void  [API 24+]
- setText(CharacterIterator): void  [API 24+]
- setText(String): void  [API 24+]
- setText(UCharacterIterator): void  [API 24+]
- tertiaryOrder(int): int [static]  [API 24+]
```

## CollationKey — declaration; API 24+ (0 +, 0 *, 0 ~, 9 -)

```
- CollationKey(String, byte[])  [API 24+]
- compareTo(CollationKey): int  [API 24+]
- equals(CollationKey): boolean  [API 24+]
- equals(Object): boolean  [API 24+]
- getBound(int, int): CollationKey  [API 24+]
- getSourceString(): String  [API 24+]
- hashCode(): int  [API 24+]
- merge(CollationKey): CollationKey  [API 24+]
- toByteArray(): byte[]  [API 24+]
```

## CollationKey.BoundMode — declaration; API 24+ (0 +, 0 *, 0 ~, 0 -)

## Collator — declaration; API 24+ (5 +, 9 *, 0 ~, 24 -)

```
* Collator()  [API 24+]
- clone(): Object  [API 24+]
- cloneAsThawed(): Collator  [API 24+]
- compare(Object, Object): int  [API 24+]
+ compare(String, String): int  [API 24+]
- equals(Object): boolean  [API 24+]
- equals(String, String): boolean  [API 24+]
- freeze(): Collator  [API 24+]
- getAvailableLocales(): Locale[] [static]  [API 24+]
- getAvailableULocales(): ULocale[] [static]  [API 24+]
+ getCollationKey(String): CollationKey  [API 24+]
* getDecomposition(): int  [API 24+]
- getDisplayName(Locale): String [static]  [API 24+]
- getDisplayName(Locale, Locale): String [static]  [API 24+]
- getDisplayName(ULocale): String [static]  [API 24+]
- getDisplayName(ULocale, ULocale): String [static]  [API 24+]
- getEquivalentReorderCodes(int): int[] [static]  [API 24+]
- getFunctionalEquivalent(String, ULocale): ULocale [static]  [API 24+]
- getFunctionalEquivalent(String, ULocale, boolean[]): ULocale [static]  [API 24+]
- getInstance(): Collator [static]  [API 24+]
- getInstance(Locale): Collator [static]  [API 24+]
- getInstance(ULocale): Collator [static]  [API 24+]
- getKeywordValues(String): String[] [static]  [API 24+]
- getKeywordValuesForLocale(String, ULocale, boolean): String[] [static]  [API 24+]
- getKeywords(): String[] [static]  [API 24+]
* getMaxVariable(): int  [API 24+]
* getReorderCodes(): int[]  [API 24+]
* getStrength(): int  [API 24+]
- getTailoredSet(): UnicodeSet  [API 24+]
+ getUCAVersion(): VersionInfo  [API 24+]
+ getVariableTop(): int  [API 24+]
+ getVersion(): VersionInfo  [API 24+]
- hashCode(): int  [API 24+]
- isFrozen(): boolean  [API 24+]
* setDecomposition(int): void  [API 24+]
* setMaxVariable(int): Collator  [API 24+]
* setReorderCodes(int[]): void  [API 24+]
* setStrength(int): void  [API 24+]
```

## Collator.ReorderCodes — declaration; API 24+ (0 +, 0 *, 0 ~, 0 -)

## CompactDecimalFormat — declaration; API 24+ (0 +, 0 *, 0 ~, 4 -)

```
- getInstance(Locale, CompactDecimalFormat.CompactStyle): CompactDecimalFormat [static]  [API 24+]
- getInstance(ULocale, CompactDecimalFormat.CompactStyle): CompactDecimalFormat [static]  [API 24+]
- parse(String, ParsePosition): Number  [API 24+]
- parseCurrency(CharSequence, ParsePosition): CurrencyAmount  [API 24+]
```

## CompactDecimalFormat.CompactStyle — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ LONG [field]  [API 24+]
+ SHORT [field]  [API 24+]
+ valueOf(String): CompactDecimalFormat.CompactStyle [static]  [API 24+]
+ values(): CompactDecimalFormat.CompactStyle[] [static]  [API 24+]
```

## ConstrainedFieldPosition — declaration; API 31+ (0 +, 3 *, 0 ~, 10 -)

```
* ConstrainedFieldPosition()  [API 31+]
- constrainClass(Class): void  [API 31+]
- constrainField(Format.Field): void  [API 31+]
- getField(): Format.Field  [API 31+]
- getFieldValue(): Object  [API 31+]
* getInt64IterationContext(): long  [API 31+]
- getLimit(): int  [API 31+]
- getStart(): int  [API 31+]
- matchesField(Format.Field, Object): boolean  [API 31+]
- reset(): void  [API 31+]
* setInt64IterationContext(long): void  [API 31+]
- setState(Format.Field, Object, int, int): void  [API 31+]
- toString(): String  [API 31+]
```

## CurrencyPluralInfo — declaration; API 24+ (0 +, 4 *, 0 ~, 11 -)

```
* CurrencyPluralInfo()  [API 24+]
- CurrencyPluralInfo(Locale)  [API 24+]
* CurrencyPluralInfo(ULocale)  [API 24+]
- clone(): Object  [API 24+]
- equals(Object): boolean  [API 24+]
- getCurrencyPluralPattern(String): String  [API 24+]
- getInstance(): CurrencyPluralInfo [static]  [API 24+]
- getInstance(Locale): CurrencyPluralInfo [static]  [API 24+]
- getInstance(ULocale): CurrencyPluralInfo [static]  [API 24+]
* getLocale(): ULocale  [API 24+]
- getPluralRules(): PluralRules  [API 24+]
- hashCode(): int  [API 24+]
- setCurrencyPluralPattern(String, String): void  [API 24+]
* setLocale(ULocale): void  [API 24+]
- setPluralRules(String): void  [API 24+]
```

## DateFormat — declaration; API 24+ (2 +, 0 *, 0 ~, 60 -)

```
- DateFormat()  [API 24+]
+ calendar [field]  [API 24+]
- clone(): Object  [API 24+]
- equals(Object): boolean  [API 24+]
+ format(Calendar, StringBuffer, FieldPosition): StringBuffer  [API 24+]
- format(Date): String  [API 24+]
- format(Date, StringBuffer, FieldPosition): StringBuffer  [API 24+]
- format(Object, StringBuffer, FieldPosition): StringBuffer  [API 24+]
- getAvailableLocales(): Locale[] [static]  [API 24+]
- getBooleanAttribute(DateFormat.BooleanAttribute): boolean  [API 24+]
- getCalendar(): Calendar  [API 24+]
- getContext(DisplayContext.Type): DisplayContext  [API 24+]
- getDateInstance(): DateFormat [static]  [API 24+]
- getDateInstance(Calendar, int): DateFormat [static]  [API 24+]
- getDateInstance(Calendar, int, Locale): DateFormat [static]  [API 24+]
- getDateInstance(Calendar, int, ULocale): DateFormat [static]  [API 24+]
- getDateInstance(int): DateFormat [static]  [API 24+]
- getDateInstance(int, Locale): DateFormat [static]  [API 24+]
- getDateInstance(int, ULocale): DateFormat [static]  [API 24+]
- getDateTimeInstance(): DateFormat [static]  [API 24+]
- getDateTimeInstance(Calendar, int, int): DateFormat [static]  [API 24+]
- getDateTimeInstance(Calendar, int, int, Locale): DateFormat [static]  [API 24+]
- getDateTimeInstance(Calendar, int, int, ULocale): DateFormat [static]  [API 24+]
- getDateTimeInstance(int, int): DateFormat [static]  [API 24+]
- getDateTimeInstance(int, int, Locale): DateFormat [static]  [API 24+]
- getDateTimeInstance(int, int, ULocale): DateFormat [static]  [API 24+]
- getInstance(): DateFormat [static]  [API 24+]
- getInstance(Calendar): DateFormat [static]  [API 24+]
- getInstance(Calendar, Locale): DateFormat [static]  [API 24+]
- getInstance(Calendar, ULocale): DateFormat [static]  [API 33+]
- getInstanceForSkeleton(Calendar, String, Locale): DateFormat [static]  [API 24+]
- getInstanceForSkeleton(Calendar, String, ULocale): DateFormat [static]  [API 24+]
- getInstanceForSkeleton(String): DateFormat [static]  [API 24+]
- getInstanceForSkeleton(String, Locale): DateFormat [static]  [API 24+]
- getInstanceForSkeleton(String, ULocale): DateFormat [static]  [API 24+]
- getNumberFormat(): NumberFormat  [API 24+]
- getPatternInstance(Calendar, String, Locale): DateFormat [static]  [API 24+]
- getPatternInstance(Calendar, String, ULocale): DateFormat [static]  [API 24+]
- getPatternInstance(String): DateFormat [static]  [API 24+]
- getPatternInstance(String, Locale): DateFormat [static]  [API 24+]
- getPatternInstance(String, ULocale): DateFormat [static]  [API 24+]
- getTimeInstance(): DateFormat [static]  [API 24+]
- getTimeInstance(Calendar, int): DateFormat [static]  [API 24+]
- getTimeInstance(Calendar, int, Locale): DateFormat [static]  [API 24+]
- getTimeInstance(Calendar, int, ULocale): DateFormat [static]  [API 24+]
- getTimeInstance(int): DateFormat [static]  [API 24+]
- getTimeInstance(int, Locale): DateFormat [static]  [API 24+]
- getTimeInstance(int, ULocale): DateFormat [static]  [API 24+]
- getTimeZone(): TimeZone  [API 24+]
- hashCode(): int  [API 24+]
- isCalendarLenient(): boolean  [API 24+]
- isLenient(): boolean  [API 24+]
+ numberFormat [field]  [API 24+]
- parse(String): Date  [API 24+]
+ parse(String, Calendar, ParsePosition): void  [API 24+]
- parse(String, ParsePosition): Date  [API 24+]
- parseObject(String, ParsePosition): Object  [API 24+]
- setBooleanAttribute(DateFormat.BooleanAttribute, boolean): DateFormat  [API 24+]
- setCalendar(Calendar): void  [API 24+]
- setCalendarLenient(boolean): void  [API 24+]
- setContext(DisplayContext): void  [API 24+]
- setLenient(boolean): void  [API 24+]
- setNumberFormat(NumberFormat): void  [API 24+]
- setTimeZone(TimeZone): void  [API 24+]
```

## DateFormat.BooleanAttribute — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ PARSE_ALLOW_NUMERIC [field]  [API 24+]
+ PARSE_ALLOW_WHITESPACE [field]  [API 24+]
+ PARSE_MULTIPLE_PATTERNS_FOR_MATCH [field]  [API 26+]
+ PARSE_PARTIAL_LITERAL_MATCH [field]  [API 26+]
+ valueOf(String): DateFormat.BooleanAttribute [static]  [API 24+]
+ values(): DateFormat.BooleanAttribute[] [static]  [API 24+]
```

## DateFormat.Field — declaration; API 24+ (0 +, 0 *, 0 ~, 4 -)

```
- AM_PM [field]  [API 24+]
- AM_PM_MIDNIGHT_NOON [field]  [API 28+]
- DAY_OF_MONTH [field]  [API 24+]
- DAY_OF_WEEK [field]  [API 24+]
- DAY_OF_WEEK_IN_MONTH [field]  [API 24+]
- DAY_OF_YEAR [field]  [API 24+]
- DOW_LOCAL [field]  [API 24+]
- ERA [field]  [API 24+]
- EXTENDED_YEAR [field]  [API 24+]
- FLEXIBLE_DAY_PERIOD [field]  [API 28+]
- Field(String, int)  [API 24+]
- HOUR0 [field]  [API 24+]
- HOUR1 [field]  [API 24+]
- HOUR_OF_DAY0 [field]  [API 24+]
- HOUR_OF_DAY1 [field]  [API 24+]
- JULIAN_DAY [field]  [API 24+]
- MILLISECOND [field]  [API 24+]
- MILLISECONDS_IN_DAY [field]  [API 24+]
- MINUTE [field]  [API 24+]
- MONTH [field]  [API 24+]
- QUARTER [field]  [API 24+]
- SECOND [field]  [API 24+]
- TIME_ZONE [field]  [API 24+]
- WEEK_OF_MONTH [field]  [API 24+]
- WEEK_OF_YEAR [field]  [API 24+]
- YEAR [field]  [API 24+]
- YEAR_WOY [field]  [API 24+]
- getCalendarField(): int  [API 24+]
- ofCalendarField(int): DateFormat.Field [static]  [API 24+]
- readResolve(): Object  [API 24+]
```

## DateFormat.HourCycle — declaration; API 33+ (2 +, 0 *, 0 ~, 0 -)

```
+ HOUR_CYCLE_11 [field]  [API 33+]
+ HOUR_CYCLE_12 [field]  [API 33+]
+ HOUR_CYCLE_23 [field]  [API 33+]
+ HOUR_CYCLE_24 [field]  [API 33+]
+ valueOf(String): DateFormat.HourCycle [static]  [API 33+]
+ values(): DateFormat.HourCycle[] [static]  [API 33+]
```

## DateFormatSymbols — declaration; API 24+ (0 +, 23 *, 0 ~, 24 -)

```
* DateFormatSymbols()  [API 24+]
- DateFormatSymbols(Calendar, Locale)  [API 24+]
- DateFormatSymbols(Calendar, ULocale)  [API 24+]
- DateFormatSymbols(Class, Locale)  [API 24+]
- DateFormatSymbols(Class, ULocale)  [API 24+]
- DateFormatSymbols(Locale)  [API 24+]
- DateFormatSymbols(ResourceBundle, Locale)  [API 24+]
- DateFormatSymbols(ResourceBundle, ULocale)  [API 24+]
- DateFormatSymbols(ULocale)  [API 24+]
- clone(): Object  [API 24+]
* equals(Object): boolean  [API 24+]
* getAmPmStrings(): String[]  [API 24+]
- getAvailableLocales(): Locale[] [static]  [API 24+]
* getEraNames(): String[]  [API 24+]
* getEras(): String[]  [API 24+]
- getInstance(): DateFormatSymbols [static]  [API 24+]
- getInstance(Locale): DateFormatSymbols [static]  [API 24+]
- getInstance(ULocale): DateFormatSymbols [static]  [API 24+]
* getLocalPatternChars(): String  [API 24+]
* getMonths(): String[]  [API 24+]
- getMonths(int, int): String[]  [API 24+]
* getNarrowEras(): String[]  [API 31+]
- getQuarters(int, int): String[]  [API 24+]
* getShortMonths(): String[]  [API 24+]
* getShortWeekdays(): String[]  [API 24+]
* getWeekdays(): String[]  [API 24+]
- getWeekdays(int, int): String[]  [API 24+]
- getYearNames(int, int): String[]  [API 24+]
- getZodiacNames(int, int): String[]  [API 24+]
* getZoneStrings(): String[][]  [API 24+]
* hashCode(): int  [API 24+]
- initializeData(ULocale, String): void  [API 24+]
* setAmPmStrings(String[]): void  [API 24+]
* setEraNames(String[]): void  [API 24+]
* setEras(String[]): void  [API 24+]
* setLocalPatternChars(String): void  [API 24+]
* setMonths(String[]): void  [API 24+]
- setMonths(String[], int, int): void  [API 24+]
* setNarrowEras(String[]): void  [API 31+]
- setQuarters(String[], int, int): void  [API 24+]
* setShortMonths(String[]): void  [API 24+]
* setShortWeekdays(String[]): void  [API 24+]
* setWeekdays(String[]): void  [API 24+]
- setWeekdays(String[], int, int): void  [API 24+]
- setYearNames(String[], int, int): void  [API 24+]
- setZodiacNames(String[], int, int): void  [API 24+]
* setZoneStrings(String[][]): void  [API 24+]
```

## DateIntervalFormat — declaration; API 24+ (0 +, 0 *, 0 ~, 20 -)

```
- clone(): Object  [API 24+]
- format(Calendar, Calendar, StringBuffer, FieldPosition): StringBuffer  [API 24+]
- format(DateInterval, StringBuffer, FieldPosition): StringBuffer  [API 24+]
- format(Object, StringBuffer, FieldPosition): StringBuffer  [API 24+]
- formatToValue(Calendar, Calendar): DateIntervalFormat.FormattedDateInterval  [API 31+]
- formatToValue(DateInterval): DateIntervalFormat.FormattedDateInterval  [API 31+]
- getContext(DisplayContext.Type): DisplayContext  [API 33+]
- getDateFormat(): DateFormat  [API 24+]
- getDateIntervalInfo(): DateIntervalInfo  [API 24+]
- getInstance(String): DateIntervalFormat [static]  [API 24+]
- getInstance(String, DateIntervalInfo): DateIntervalFormat [static]  [API 24+]
- getInstance(String, Locale): DateIntervalFormat [static]  [API 24+]
- getInstance(String, Locale, DateIntervalInfo): DateIntervalFormat [static]  [API 24+]
- getInstance(String, ULocale): DateIntervalFormat [static]  [API 24+]
- getInstance(String, ULocale, DateIntervalInfo): DateIntervalFormat [static]  [API 24+]
- getTimeZone(): TimeZone  [API 24+]
- parseObject(String, ParsePosition): Object  [API 24+]
- setContext(DisplayContext): void  [API 33+]
- setDateIntervalInfo(DateIntervalInfo): void  [API 24+]
- setTimeZone(TimeZone): void  [API 24+]
```

## DateIntervalFormat.FormattedDateInterval — declaration; API 31+ (0 +, 0 *, 0 ~, 7 -)

```
- appendTo(Appendable): Appendable  [API 31+]
- charAt(int): char  [API 31+]
- length(): int  [API 31+]
- nextPosition(ConstrainedFieldPosition): boolean  [API 31+]
- subSequence(int, int): CharSequence  [API 31+]
- toCharacterIterator(): AttributedCharacterIterator  [API 31+]
- toString(): String  [API 31+]
```

## DateIntervalInfo — declaration; API 24+ (0 +, 2 *, 0 ~, 11 -)

```
- DateIntervalInfo(Locale)  [API 24+]
- DateIntervalInfo(ULocale)  [API 24+]
- clone(): Object  [API 24+]
- cloneAsThawed(): DateIntervalInfo  [API 24+]
- equals(Object): boolean  [API 24+]
- freeze(): DateIntervalInfo  [API 24+]
- getDefaultOrder(): boolean  [API 24+]
* getFallbackIntervalPattern(): String  [API 24+]
- getIntervalPattern(String, int): DateIntervalInfo.PatternInfo  [API 24+]
- hashCode(): int  [API 24+]
- isFrozen(): boolean  [API 24+]
* setFallbackIntervalPattern(String): void  [API 24+]
- setIntervalPattern(String, int, String): void  [API 24+]
```

## DateIntervalInfo.PatternInfo — declaration; API 24+ (0 +, 0 *, 0 ~, 7 -)

```
- PatternInfo(String, String, boolean)  [API 24+]
- equals(Object): boolean  [API 24+]
- firstDateInPtnIsLaterDate(): boolean  [API 24+]
- getFirstPart(): String  [API 24+]
- getSecondPart(): String  [API 24+]
- hashCode(): int  [API 24+]
- toString(): String  [API 24+]
```

## DateTimePatternGenerator — declaration; API 24+ (0 +, 5 *, 0 ~, 25 -)

```
* DateTimePatternGenerator()  [API 24+]
- addPattern(String, boolean, DateTimePatternGenerator.PatternInfo): DateTimePatternGenerator  [API 24+]
- clone(): Object  [API 24+]
- cloneAsThawed(): DateTimePatternGenerator  [API 24+]
- freeze(): DateTimePatternGenerator  [API 24+]
- getAppendItemFormat(int): String  [API 24+]
- getAppendItemName(int): String  [API 24+]
- getBaseSkeleton(String): String  [API 24+]
- getBaseSkeletons(Set): Set  [API 24+]
- getBestPattern(String): String  [API 24+]
- getBestPattern(String, int): String  [API 24+]
* getDateTimeFormat(): String  [API 24+]
- getDateTimeFormat(int): String  [API 35+]
* getDecimal(): String  [API 24+]
- getDefaultHourCycle(): DateFormat.HourCycle  [API 33+]
- getEmptyInstance(): DateTimePatternGenerator [static]  [API 24+]
- getFieldDisplayName(int, DateTimePatternGenerator.DisplayWidth): String  [API 30+]
- getInstance(): DateTimePatternGenerator [static]  [API 24+]
- getInstance(Locale): DateTimePatternGenerator [static]  [API 24+]
- getInstance(ULocale): DateTimePatternGenerator [static]  [API 24+]
- getSkeleton(String): String  [API 24+]
- getSkeletons(Map): Map  [API 24+]
- isFrozen(): boolean  [API 24+]
- replaceFieldTypes(String, String): String  [API 24+]
- replaceFieldTypes(String, String, int): String  [API 24+]
- setAppendItemFormat(int, String): void  [API 24+]
- setAppendItemName(int, String): void  [API 24+]
* setDateTimeFormat(String): void  [API 24+]
- setDateTimeFormat(int, String): void  [API 35+]
* setDecimal(String): void  [API 24+]
```

## DateTimePatternGenerator.DisplayWidth — declaration; API 30+ (2 +, 0 *, 0 ~, 0 -)

```
+ ABBREVIATED [field]  [API 30+]
+ NARROW [field]  [API 30+]
+ WIDE [field]  [API 30+]
+ valueOf(String): DateTimePatternGenerator.DisplayWidth [static]  [API 30+]
+ values(): DateTimePatternGenerator.DisplayWidth[] [static]  [API 30+]
```

## DateTimePatternGenerator.PatternInfo — declaration; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ PatternInfo()  [API 24+]
+ conflictingPattern [field]  [API 24+]
+ status [field]  [API 24+]
```

## DecimalFormat — declaration; API 24+ (0 +, 0 *, 0 ~, 101 -)

```
- DecimalFormat()  [API 24+]
- DecimalFormat(String)  [API 24+]
- DecimalFormat(String, DecimalFormatSymbols)  [API 24+]
- DecimalFormat(String, DecimalFormatSymbols, CurrencyPluralInfo, int)  [API 24+]
- applyLocalizedPattern(String): void  [API 24+]
- applyPattern(String): void  [API 24+]
- areSignificantDigitsUsed(): boolean  [API 24+]
- clone(): Object  [API 24+]
- equals(Object): boolean  [API 24+]
- format(BigDecimal, StringBuffer, FieldPosition): StringBuffer  [API 24+]
- format(BigDecimal, StringBuffer, FieldPosition): StringBuffer  [API 24+]
- format(BigInteger, StringBuffer, FieldPosition): StringBuffer  [API 24+]
- format(CurrencyAmount, StringBuffer, FieldPosition): StringBuffer  [API 24+]
- format(double, StringBuffer, FieldPosition): StringBuffer  [API 24+]
- format(long, StringBuffer, FieldPosition): StringBuffer  [API 24+]
- formatToCharacterIterator(Object): AttributedCharacterIterator  [API 24+]
- getCurrency(): Currency  [API 24+]
- getCurrencyPluralInfo(): CurrencyPluralInfo  [API 24+]
- getCurrencyUsage(): Currency.CurrencyUsage  [API 24+]
- getDecimalFormatSymbols(): DecimalFormatSymbols  [API 24+]
- getFormatWidth(): int  [API 24+]
- getGroupingSize(): int  [API 24+]
- getMathContext(): MathContext  [API 24+]
- getMathContextICU(): MathContext  [API 24+]
- getMaximumFractionDigits(): int  [API 24+]
- getMaximumIntegerDigits(): int  [API 24+]
- getMaximumSignificantDigits(): int  [API 24+]
- getMinimumExponentDigits(): byte  [API 24+]
- getMinimumFractionDigits(): int  [API 24+]
- getMinimumGroupingDigits(): int  [API 31+]
- getMinimumIntegerDigits(): int  [API 24+]
- getMinimumSignificantDigits(): int  [API 24+]
- getMultiplier(): int  [API 24+]
- getNegativePrefix(): String  [API 24+]
- getNegativeSuffix(): String  [API 24+]
- getPadCharacter(): char  [API 24+]
- getPadPosition(): int  [API 24+]
- getParseMaxDigits(): int  [API 24+, deprecated in 28]
- getPositivePrefix(): String  [API 24+]
- getPositiveSuffix(): String  [API 24+]
- getRoundingIncrement(): BigDecimal  [API 24+]
- getRoundingMode(): int  [API 24+]
- getSecondaryGroupingSize(): int  [API 24+]
- hashCode(): int  [API 24+]
- isDecimalPatternMatchRequired(): boolean  [API 24+]
- isDecimalSeparatorAlwaysShown(): boolean  [API 24+]
- isExponentSignAlwaysShown(): boolean  [API 24+]
- isGroupingUsed(): boolean  [API 24+]
- isParseBigDecimal(): boolean  [API 24+]
- isParseCaseSensitive(): boolean  [API 31+]
- isParseIntegerOnly(): boolean  [API 24+]
- isParseNoExponent(): boolean  [API 31+]
- isParseStrict(): boolean  [API 24+]
- isScientificNotation(): boolean  [API 24+]
- isSignAlwaysShown(): boolean  [API 31+]
- parse(String, ParsePosition): Number  [API 24+]
- parseCurrency(CharSequence, ParsePosition): CurrencyAmount  [API 24+]
- setCurrency(Currency): void  [API 24+]
- setCurrencyPluralInfo(CurrencyPluralInfo): void  [API 24+]
- setCurrencyUsage(Currency.CurrencyUsage): void  [API 24+]
- setDecimalFormatSymbols(DecimalFormatSymbols): void  [API 24+]
- setDecimalPatternMatchRequired(boolean): void  [API 24+]
- setDecimalSeparatorAlwaysShown(boolean): void  [API 24+]
- setExponentSignAlwaysShown(boolean): void  [API 24+]
- setFormatWidth(int): void  [API 24+]
- setGroupingSize(int): void  [API 24+]
- setGroupingUsed(boolean): void  [API 24+]
- setMathContext(MathContext): void  [API 24+]
- setMathContextICU(MathContext): void  [API 24+]
- setMaximumFractionDigits(int): void  [API 24+]
- setMaximumIntegerDigits(int): void  [API 24+]
- setMaximumSignificantDigits(int): void  [API 24+]
- setMinimumExponentDigits(byte): void  [API 24+]
- setMinimumFractionDigits(int): void  [API 24+]
- setMinimumGroupingDigits(int): void  [API 31+]
- setMinimumIntegerDigits(int): void  [API 24+]
- setMinimumSignificantDigits(int): void  [API 24+]
- setMultiplier(int): void  [API 24+]
- setNegativePrefix(String): void  [API 24+]
- setNegativeSuffix(String): void  [API 24+]
- setPadCharacter(char): void  [API 24+]
- setPadPosition(int): void  [API 24+]
- setParseBigDecimal(boolean): void  [API 24+]
- setParseCaseSensitive(boolean): void  [API 31+]
- setParseIntegerOnly(boolean): void  [API 24+]
- setParseMaxDigits(int): void  [API 24+, deprecated in 28]
- setParseNoExponent(boolean): void  [API 31+]
- setParseStrict(boolean): void  [API 24+]
- setPositivePrefix(String): void  [API 24+]
- setPositiveSuffix(String): void  [API 24+]
- setRoundingIncrement(BigDecimal): void  [API 24+]
- setRoundingIncrement(BigDecimal): void  [API 24+]
- setRoundingIncrement(double): void  [API 24+]
- setRoundingMode(int): void  [API 24+]
- setScientificNotation(boolean): void  [API 24+]
- setSecondaryGroupingSize(int): void  [API 24+]
- setSignAlwaysShown(boolean): void  [API 31+]
- setSignificantDigitsUsed(boolean): void  [API 24+]
- toLocalizedPattern(): String  [API 24+]
- toPattern(): String  [API 24+]
- toString(): String  [API 24+]
```

## DecimalFormatSymbols — declaration; API 24+ (0 +, 61 *, 0 ~, 15 -)

```
* DecimalFormatSymbols()  [API 24+]
* DecimalFormatSymbols(Locale)  [API 24+]
- DecimalFormatSymbols(ULocale)  [API 24+]
- clone(): Object  [API 24+]
- equals(Object): boolean  [API 24+]
- forNumberingSystem(Locale, NumberingSystem): DecimalFormatSymbols [static]  [API 29+]
- forNumberingSystem(ULocale, NumberingSystem): DecimalFormatSymbols [static]  [API 29+]
- getAvailableLocales(): Locale[] [static]  [API 24+]
- getAvailableULocales(): ULocale[] [static]  [API 33+]
* getCurrency(): Currency  [API 24+]
* getCurrencySymbol(): String  [API 24+]
* getDecimalSeparator(): char  [API 24+]
* getDecimalSeparatorString(): String  [API 28+]
* getDigit(): char  [API 24+]
* getDigitStrings(): String[]  [API 28+]
- getDigits(): char[]  [API 24+]
* getExponentMultiplicationSign(): String  [API 24+]
* getExponentSeparator(): String  [API 24+]
* getGroupingSeparator(): char  [API 24+]
* getGroupingSeparatorString(): String  [API 28+]
* getInfinity(): String  [API 24+]
- getInstance(): DecimalFormatSymbols [static]  [API 24+]
- getInstance(Locale): DecimalFormatSymbols [static]  [API 24+]
- getInstance(ULocale): DecimalFormatSymbols [static]  [API 24+]
* getInternationalCurrencySymbol(): String  [API 24+]
* getLocale(): Locale  [API 24+]
* getMinusSign(): char  [API 24+]
* getMinusSignString(): String  [API 28+]
* getMonetaryDecimalSeparator(): char  [API 24+]
* getMonetaryDecimalSeparatorString(): String  [API 28+]
* getMonetaryGroupingSeparator(): char  [API 24+]
* getMonetaryGroupingSeparatorString(): String  [API 28+]
* getNaN(): String  [API 24+]
* getPadEscape(): char  [API 24+]
- getPatternForCurrencySpacing(int, boolean): String  [API 24+]
* getPatternSeparator(): char  [API 24+]
* getPerMill(): char  [API 24+]
* getPerMillString(): String  [API 28+]
* getPercent(): char  [API 24+]
* getPercentString(): String  [API 28+]
* getPlusSign(): char  [API 24+]
* getPlusSignString(): String  [API 28+]
* getSignificantDigit(): char  [API 24+]
- getULocale(): ULocale  [API 24+]
* getZeroDigit(): char  [API 24+]
- hashCode(): int  [API 24+]
* setCurrency(Currency): void  [API 24+]
* setCurrencySymbol(String): void  [API 24+]
* setDecimalSeparator(char): void  [API 24+]
* setDecimalSeparatorString(String): void  [API 28+]
* setDigit(char): void  [API 24+]
* setDigitStrings(String[]): void  [API 28+]
* setExponentMultiplicationSign(String): void  [API 24+]
* setExponentSeparator(String): void  [API 24+]
* setGroupingSeparator(char): void  [API 24+]
* setGroupingSeparatorString(String): void  [API 28+]
* setInfinity(String): void  [API 24+]
* setInternationalCurrencySymbol(String): void  [API 24+]
* setMinusSign(char): void  [API 24+]
* setMinusSignString(String): void  [API 28+]
* setMonetaryDecimalSeparator(char): void  [API 24+]
* setMonetaryDecimalSeparatorString(String): void  [API 28+]
* setMonetaryGroupingSeparator(char): void  [API 24+]
* setMonetaryGroupingSeparatorString(String): void  [API 28+]
* setNaN(String): void  [API 24+]
* setPadEscape(char): void  [API 24+]
- setPatternForCurrencySpacing(int, boolean, String): void  [API 24+]
* setPatternSeparator(char): void  [API 24+]
* setPerMill(char): void  [API 24+]
* setPerMillString(String): void  [API 28+]
* setPercent(char): void  [API 24+]
* setPercentString(String): void  [API 28+]
* setPlusSign(char): void  [API 24+]
* setPlusSignString(String): void  [API 28+]
* setSignificantDigit(char): void  [API 24+]
* setZeroDigit(char): void  [API 24+]
```

## DisplayContext — declaration; API 24+ (2 +, 0 *, 0 ~, 2 -)

```
+ CAPITALIZATION_FOR_BEGINNING_OF_SENTENCE [field]  [API 24+]
+ CAPITALIZATION_FOR_MIDDLE_OF_SENTENCE [field]  [API 24+]
+ CAPITALIZATION_FOR_STANDALONE [field]  [API 24+]
+ CAPITALIZATION_FOR_UI_LIST_OR_MENU [field]  [API 24+]
+ CAPITALIZATION_NONE [field]  [API 24+]
+ DIALECT_NAMES [field]  [API 24+]
+ LENGTH_FULL [field]  [API 24+]
+ LENGTH_SHORT [field]  [API 24+]
+ NO_SUBSTITUTE [field]  [API 28+]
+ STANDARD_NAMES [field]  [API 24+]
+ SUBSTITUTE [field]  [API 28+]
- type(): DisplayContext.Type  [API 24+]
- value(): int  [API 24+]
+ valueOf(String): DisplayContext [static]  [API 24+]
+ values(): DisplayContext[] [static]  [API 24+]
```

## DisplayContext.Type — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ CAPITALIZATION [field]  [API 24+]
+ DIALECT_HANDLING [field]  [API 24+]
+ DISPLAY_LENGTH [field]  [API 24+]
+ SUBSTITUTE_HANDLING [field]  [API 28+]
+ valueOf(String): DisplayContext.Type [static]  [API 24+]
+ values(): DisplayContext.Type[] [static]  [API 24+]
```

## DisplayOptions — declaration; API 35+ (0 +, 7 *, 0 ~, 2 -)

```
- builder(): DisplayOptions.Builder [static]  [API 35+]
- copyToBuilder(): DisplayOptions.Builder  [API 35+]
* getCapitalization(): DisplayOptions.Capitalization  [API 35+]
* getDisplayLength(): DisplayOptions.DisplayLength  [API 35+]
* getGrammaticalCase(): DisplayOptions.GrammaticalCase  [API 35+]
* getNameStyle(): DisplayOptions.NameStyle  [API 35+]
* getNounClass(): DisplayOptions.NounClass  [API 35+]
* getPluralCategory(): DisplayOptions.PluralCategory  [API 35+]
* getSubstituteHandling(): DisplayOptions.SubstituteHandling  [API 35+]
```

## DisplayOptions.Builder — declaration; API 35+ (0 +, 8 *, 0 ~, 0 -)

```
* build(): DisplayOptions  [API 35+]
* setCapitalization(DisplayOptions.Capitalization): DisplayOptions.Builder  [API 35+]
* setDisplayLength(DisplayOptions.DisplayLength): DisplayOptions.Builder  [API 35+]
* setGrammaticalCase(DisplayOptions.GrammaticalCase): DisplayOptions.Builder  [API 35+]
* setNameStyle(DisplayOptions.NameStyle): DisplayOptions.Builder  [API 35+]
* setNounClass(DisplayOptions.NounClass): DisplayOptions.Builder  [API 35+]
* setPluralCategory(DisplayOptions.PluralCategory): DisplayOptions.Builder  [API 35+]
* setSubstituteHandling(DisplayOptions.SubstituteHandling): DisplayOptions.Builder  [API 35+]
```

## DisplayOptions.Capitalization — declaration; API 35+ (2 +, 0 *, 0 ~, 0 -)

```
+ BEGINNING_OF_SENTENCE [field]  [API 35+]
+ MIDDLE_OF_SENTENCE [field]  [API 35+]
+ STANDALONE [field]  [API 35+]
+ UI_LIST_OR_MENU [field]  [API 35+]
+ UNDEFINED [field]  [API 35+]
- VALUES [field]  [API 35+]
+ valueOf(String): DisplayOptions.Capitalization [static]  [API 35+]
+ values(): DisplayOptions.Capitalization[] [static]  [API 35+]
```

## DisplayOptions.DisplayLength — declaration; API 35+ (2 +, 0 *, 0 ~, 0 -)

```
+ LENGTH_FULL [field]  [API 35+]
+ LENGTH_SHORT [field]  [API 35+]
+ UNDEFINED [field]  [API 35+]
- VALUES [field]  [API 35+]
+ valueOf(String): DisplayOptions.DisplayLength [static]  [API 35+]
+ values(): DisplayOptions.DisplayLength[] [static]  [API 35+]
```

## DisplayOptions.GrammaticalCase — declaration; API 35+ (2 +, 0 *, 0 ~, 2 -)

```
+ ABLATIVE [field]  [API 35+]
+ ACCUSATIVE [field]  [API 35+]
+ COMITATIVE [field]  [API 35+]
+ DATIVE [field]  [API 35+]
+ ERGATIVE [field]  [API 35+]
+ GENITIVE [field]  [API 35+]
+ INSTRUMENTAL [field]  [API 35+]
+ LOCATIVE [field]  [API 35+]
+ LOCATIVE_COPULATIVE [field]  [API 35+]
+ NOMINATIVE [field]  [API 35+]
+ OBLIQUE [field]  [API 35+]
+ PREPOSITIONAL [field]  [API 35+]
+ SOCIATIVE [field]  [API 35+]
+ UNDEFINED [field]  [API 35+]
- VALUES [field]  [API 35+]
+ VOCATIVE [field]  [API 35+]
- fromIdentifier(String): DisplayOptions.GrammaticalCase [static]  [API 35+]
- getIdentifier(): String  [API 35+]
+ valueOf(String): DisplayOptions.GrammaticalCase [static]  [API 35+]
+ values(): DisplayOptions.GrammaticalCase[] [static]  [API 35+]
```

## DisplayOptions.NameStyle — declaration; API 35+ (2 +, 0 *, 0 ~, 0 -)

```
+ DIALECT_NAMES [field]  [API 35+]
+ STANDARD_NAMES [field]  [API 35+]
+ UNDEFINED [field]  [API 35+]
- VALUES [field]  [API 35+]
+ valueOf(String): DisplayOptions.NameStyle [static]  [API 35+]
+ values(): DisplayOptions.NameStyle[] [static]  [API 35+]
```

## DisplayOptions.NounClass — declaration; API 35+ (2 +, 0 *, 0 ~, 2 -)

```
+ ANIMATE [field]  [API 35+]
+ COMMON [field]  [API 35+]
+ FEMININE [field]  [API 35+]
+ INANIMATE [field]  [API 35+]
+ MASCULINE [field]  [API 35+]
+ NEUTER [field]  [API 35+]
+ OTHER [field]  [API 35+]
+ PERSONAL [field]  [API 35+]
+ UNDEFINED [field]  [API 35+]
- VALUES [field]  [API 35+]
- fromIdentifier(String): DisplayOptions.NounClass [static]  [API 35+]
- getIdentifier(): String  [API 35+]
+ valueOf(String): DisplayOptions.NounClass [static]  [API 35+]
+ values(): DisplayOptions.NounClass[] [static]  [API 35+]
```

## DisplayOptions.PluralCategory — declaration; API 35+ (2 +, 0 *, 0 ~, 2 -)

```
+ FEW [field]  [API 35+]
+ MANY [field]  [API 35+]
+ ONE [field]  [API 35+]
+ OTHER [field]  [API 35+]
+ TWO [field]  [API 35+]
+ UNDEFINED [field]  [API 35+]
- VALUES [field]  [API 35+]
+ ZERO [field]  [API 35+]
- fromIdentifier(String): DisplayOptions.PluralCategory [static]  [API 35+]
- getIdentifier(): String  [API 35+]
+ valueOf(String): DisplayOptions.PluralCategory [static]  [API 35+]
+ values(): DisplayOptions.PluralCategory[] [static]  [API 35+]
```

## DisplayOptions.SubstituteHandling — declaration; API 35+ (2 +, 0 *, 0 ~, 0 -)

```
+ NO_SUBSTITUTE [field]  [API 35+]
+ SUBSTITUTE [field]  [API 35+]
+ UNDEFINED [field]  [API 35+]
- VALUES [field]  [API 35+]
+ valueOf(String): DisplayOptions.SubstituteHandling [static]  [API 35+]
+ values(): DisplayOptions.SubstituteHandling[] [static]  [API 35+]
```

## Edits — declaration; API 29+ (0 +, 0 *, 0 ~, 12 -)

```
- Edits()  [API 29+]
- addReplace(int, int): void  [API 29+]
- addUnchanged(int): void  [API 29+]
- getCoarseChangesIterator(): Edits.Iterator  [API 29+]
- getCoarseIterator(): Edits.Iterator  [API 29+]
- getFineChangesIterator(): Edits.Iterator  [API 29+]
- getFineIterator(): Edits.Iterator  [API 29+]
- hasChanges(): boolean  [API 29+]
- lengthDelta(): int  [API 29+]
- mergeAndAppend(Edits, Edits): Edits  [API 29+]
- numberOfChanges(): int  [API 29+]
- reset(): void  [API 29+]
```

## Edits.Iterator — declaration; API 29+ (0 +, 0 *, 0 ~, 12 -)

```
- destinationIndex(): int  [API 29+]
- destinationIndexFromSourceIndex(int): int  [API 29+]
- findDestinationIndex(int): boolean  [API 29+]
- findSourceIndex(int): boolean  [API 29+]
- hasChange(): boolean  [API 29+]
- newLength(): int  [API 29+]
- next(): boolean  [API 29+]
- oldLength(): int  [API 29+]
- replacementIndex(): int  [API 29+]
- sourceIndex(): int  [API 29+]
- sourceIndexFromDestinationIndex(int): int  [API 29+]
- toString(): String  [API 29+]
```

## FormattedValue — declaration; API 31+ (4 +, 0 *, 0 ~, 0 -)

```
+ appendTo(Appendable): Appendable  [API 31+]
+ nextPosition(ConstrainedFieldPosition): boolean  [API 31+]
+ toCharacterIterator(): AttributedCharacterIterator  [API 31+]
+ toString(): String  [API 31+]
```

## IDNA — declaration; API 24+ (4 +, 0 *, 0 ~, 1 -)

```
- getUTS46Instance(int): IDNA [static]  [API 24+]
+ labelToASCII(CharSequence, StringBuilder, IDNA.Info): StringBuilder  [API 24+]
+ labelToUnicode(CharSequence, StringBuilder, IDNA.Info): StringBuilder  [API 24+]
+ nameToASCII(CharSequence, StringBuilder, IDNA.Info): StringBuilder  [API 24+]
+ nameToUnicode(CharSequence, StringBuilder, IDNA.Info): StringBuilder  [API 24+]
```

## IDNA.Error — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ BIDI [field]  [API 24+]
+ CONTEXTJ [field]  [API 24+]
+ CONTEXTO_DIGITS [field]  [API 24+]
+ CONTEXTO_PUNCTUATION [field]  [API 24+]
+ DISALLOWED [field]  [API 24+]
+ DOMAIN_NAME_TOO_LONG [field]  [API 24+]
+ EMPTY_LABEL [field]  [API 24+]
+ HYPHEN_3_4 [field]  [API 24+]
+ INVALID_ACE_LABEL [field]  [API 24+]
+ LABEL_HAS_DOT [field]  [API 24+]
+ LABEL_TOO_LONG [field]  [API 24+]
+ LEADING_COMBINING_MARK [field]  [API 24+]
+ LEADING_HYPHEN [field]  [API 24+]
+ PUNYCODE [field]  [API 24+]
+ TRAILING_HYPHEN [field]  [API 24+]
+ valueOf(String): IDNA.Error [static]  [API 24+]
+ values(): IDNA.Error[] [static]  [API 24+]
```

## IDNA.Info — declaration; API 24+ (0 +, 0 *, 0 ~, 4 -)

```
- Info()  [API 24+]
- getErrors(): Set  [API 24+]
- hasErrors(): boolean  [API 24+]
- isTransitionalDifferent(): boolean  [API 24+]
```

## ListFormatter — declaration; API 26+ (0 +, 0 *, 0 ~, 10 -)

```
- format(Collection): String  [API 26+]
- format(Object[]): String  [API 26+]
- formatToValue(Collection): ListFormatter.FormattedList  [API 33+]
- formatToValue(Object[]): ListFormatter.FormattedList  [API 33+]
- getInstance(): ListFormatter [static]  [API 26+]
- getInstance(Locale): ListFormatter [static]  [API 26+]
- getInstance(Locale, ListFormatter.Type, ListFormatter.Width): ListFormatter [static]  [API 33+]
- getInstance(ULocale): ListFormatter [static]  [API 26+]
- getInstance(ULocale, ListFormatter.Type, ListFormatter.Width): ListFormatter [static]  [API 33+]
- getPatternForNumItems(int): String  [API 26+]
```

## ListFormatter.FormattedList — declaration; API 33+ (0 +, 0 *, 0 ~, 7 -)

```
- appendTo(Appendable): Appendable  [API 33+]
- charAt(int): char  [API 33+]
- length(): int  [API 33+]
- nextPosition(ConstrainedFieldPosition): boolean  [API 33+]
- subSequence(int, int): CharSequence  [API 33+]
- toCharacterIterator(): AttributedCharacterIterator  [API 33+]
- toString(): String  [API 33+]
```

## ListFormatter.Type — declaration; API 33+ (2 +, 0 *, 0 ~, 0 -)

```
+ AND [field]  [API 33+]
+ OR [field]  [API 33+]
+ UNITS [field]  [API 33+]
+ valueOf(String): ListFormatter.Type [static]  [API 33+]
+ values(): ListFormatter.Type[] [static]  [API 33+]
```

## ListFormatter.Width — declaration; API 33+ (2 +, 0 *, 0 ~, 0 -)

```
+ NARROW [field]  [API 33+]
+ SHORT [field]  [API 33+]
+ WIDE [field]  [API 33+]
+ valueOf(String): ListFormatter.Width [static]  [API 33+]
+ values(): ListFormatter.Width[] [static]  [API 33+]
```

## LocaleDisplayNames — declaration; API 24+ (14 +, 0 *, 0 ~, 6 -)

```
+ getContext(DisplayContext.Type): DisplayContext  [API 24+]
+ getDialectHandling(): LocaleDisplayNames.DialectHandling  [API 24+]
- getInstance(Locale): LocaleDisplayNames [static]  [API 24+]
- getInstance(Locale, DisplayContext[]): LocaleDisplayNames [static]  [API 24+]
- getInstance(ULocale): LocaleDisplayNames [static]  [API 24+]
- getInstance(ULocale, DisplayContext[]): LocaleDisplayNames [static]  [API 24+]
- getInstance(ULocale, LocaleDisplayNames.DialectHandling): LocaleDisplayNames [static]  [API 24+]
+ getLocale(): ULocale  [API 24+]
- getUiList(Set, boolean, Comparator): List  [API 26+]
+ getUiListCompareWholeItems(Set, Comparator): List  [API 26+]
+ keyDisplayName(String): String  [API 24+]
+ keyValueDisplayName(String, String): String  [API 24+]
+ languageDisplayName(String): String  [API 24+]
+ localeDisplayName(Locale): String  [API 24+]
+ localeDisplayName(String): String  [API 24+]
+ localeDisplayName(ULocale): String  [API 24+]
+ regionDisplayName(String): String  [API 24+]
+ scriptDisplayName(String): String  [API 24+]
+ scriptDisplayName(int): String  [API 24+]
+ variantDisplayName(String): String  [API 24+]
```

## LocaleDisplayNames.DialectHandling — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ DIALECT_NAMES [field]  [API 24+]
+ STANDARD_NAMES [field]  [API 24+]
+ valueOf(String): LocaleDisplayNames.DialectHandling [static]  [API 24+]
+ values(): LocaleDisplayNames.DialectHandling[] [static]  [API 24+]
```

## LocaleDisplayNames.UiListItem — declaration; API 26+ (0 +, 0 *, 0 ~, 5 -)

```
- UiListItem(ULocale, ULocale, String, String)  [API 26+]
- equals(Object): boolean  [API 26+]
- getComparator(Comparator, boolean): Comparator [static]  [API 26+]
- hashCode(): int  [API 26+]
- minimized [field]  [API 26+]
- modified [field]  [API 26+]
- nameInDisplayLocale [field]  [API 26+]
- nameInSelf [field]  [API 26+]
- toString(): String  [API 26+]
```

## MeasureFormat — declaration; API 24+ (0 +, 0 *, 0 ~, 18 -)

```
- equals(Object): boolean  [API 24+]
- format(Object, StringBuffer, FieldPosition): StringBuffer  [API 24+]
- formatMeasurePerUnit(Measure, MeasureUnit, StringBuilder, FieldPosition): StringBuilder  [API 26+]
- formatMeasures(Measure[]): String  [API 24+]
- formatMeasures(StringBuilder, FieldPosition, Measure[]): StringBuilder  [API 24+]
- getCurrencyFormat(): MeasureFormat [static]  [API 24+]
- getCurrencyFormat(Locale): MeasureFormat [static]  [API 24+]
- getCurrencyFormat(ULocale): MeasureFormat [static]  [API 24+]
- getInstance(Locale, MeasureFormat.FormatWidth): MeasureFormat [static]  [API 24+]
- getInstance(Locale, MeasureFormat.FormatWidth, NumberFormat): MeasureFormat [static]  [API 24+]
- getInstance(ULocale, MeasureFormat.FormatWidth): MeasureFormat [static]  [API 24+]
- getInstance(ULocale, MeasureFormat.FormatWidth, NumberFormat): MeasureFormat [static]  [API 24+]
- getLocale(): ULocale  [API 24+]
- getNumberFormat(): NumberFormat  [API 24+]
- getUnitDisplayName(MeasureUnit): String  [API 28+]
- getWidth(): MeasureFormat.FormatWidth  [API 24+]
- hashCode(): int  [API 24+]
- parseObject(String, ParsePosition): Measure  [API 24+]
```

## MeasureFormat.FormatWidth — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ NARROW [field]  [API 24+]
+ NUMERIC [field]  [API 24+]
+ SHORT [field]  [API 24+]
+ WIDE [field]  [API 24+]
+ valueOf(String): MeasureFormat.FormatWidth [static]  [API 24+]
+ values(): MeasureFormat.FormatWidth[] [static]  [API 24+]
```

## MessageFormat — declaration; API 24+ (0 +, 0 *, 0 ~, 37 -)

```
- MessageFormat(String)  [API 24+]
- MessageFormat(String, Locale)  [API 24+]
- MessageFormat(String, ULocale)  [API 24+]
- applyPattern(String): void  [API 24+]
- applyPattern(String, MessagePattern.ApostropheMode): void  [API 24+]
- autoQuoteApostrophe(String): String [static]  [API 24+]
- clone(): Object  [API 24+]
- equals(Object): boolean  [API 24+]
- format(Map, StringBuffer, FieldPosition): StringBuffer  [API 24+]
- format(Object, StringBuffer, FieldPosition): StringBuffer  [API 24+]
- format(Object[], StringBuffer, FieldPosition): StringBuffer  [API 24+]
- format(String, Map): String [static]  [API 24+]
- format(String, Object[]): String [static]  [API 24+]
- formatToCharacterIterator(Object): AttributedCharacterIterator  [API 24+]
- getApostropheMode(): MessagePattern.ApostropheMode  [API 24+]
- getArgumentNames(): Set  [API 24+]
- getFormatByArgumentName(String): Format  [API 24+]
- getFormats(): Format[]  [API 24+]
- getFormatsByArgumentIndex(): Format[]  [API 24+]
- getLocale(): Locale  [API 24+]
- getULocale(): ULocale  [API 24+]
- hashCode(): int  [API 24+]
- parse(String): Object[]  [API 24+]
- parse(String, ParsePosition): Object[]  [API 24+]
- parseObject(String, ParsePosition): Object  [API 24+]
- parseToMap(String): Map  [API 24+]
- parseToMap(String, ParsePosition): Map  [API 24+]
- setFormat(int, Format): void  [API 24+]
- setFormatByArgumentIndex(int, Format): void  [API 24+]
- setFormatByArgumentName(String, Format): void  [API 24+]
- setFormats(Format[]): void  [API 24+]
- setFormatsByArgumentIndex(Format[]): void  [API 24+]
- setFormatsByArgumentName(Map): void  [API 24+]
- setLocale(Locale): void  [API 24+]
- setLocale(ULocale): void  [API 24+]
- toPattern(): String  [API 24+]
- usesNamedArguments(): boolean  [API 24+]
```

## MessageFormat.Field — declaration; API 24+ (0 +, 0 *, 0 ~, 2 -)

```
- ARGUMENT [field]  [API 24+]
- Field(String)  [API 24+]
- readResolve(): Object  [API 24+]
```

## MessagePattern — declaration; API 24+ (0 +, 0 *, 0 ~, 31 -)

```
- MessagePattern()  [API 24+]
- MessagePattern(MessagePattern.ApostropheMode)  [API 24+]
- MessagePattern(String)  [API 24+]
- autoQuoteApostropheDeep(): String  [API 24+]
- clear(): void  [API 24+]
- clearPatternAndSetApostropheMode(MessagePattern.ApostropheMode): void  [API 24+]
- clone(): Object  [API 24+]
- cloneAsThawed(): MessagePattern  [API 24+]
- countParts(): int  [API 24+]
- equals(Object): boolean  [API 24+]
- freeze(): MessagePattern  [API 24+]
- getApostropheMode(): MessagePattern.ApostropheMode  [API 24+]
- getLimitPartIndex(int): int  [API 24+]
- getNumericValue(MessagePattern.Part): double  [API 24+]
- getPart(int): MessagePattern.Part  [API 24+]
- getPartType(int): MessagePattern.Part.Type  [API 24+]
- getPatternIndex(int): int  [API 24+]
- getPatternString(): String  [API 24+]
- getPluralOffset(int): double  [API 24+]
- getSubstring(MessagePattern.Part): String  [API 24+]
- hasNamedArguments(): boolean  [API 24+]
- hasNumberedArguments(): boolean  [API 24+]
- hashCode(): int  [API 24+]
- isFrozen(): boolean  [API 24+]
- parse(String): MessagePattern  [API 24+]
- parseChoiceStyle(String): MessagePattern  [API 24+]
- parsePluralStyle(String): MessagePattern  [API 24+]
- parseSelectStyle(String): MessagePattern  [API 24+]
- partSubstringMatches(MessagePattern.Part, String): boolean  [API 24+]
- toString(): String  [API 24+]
- validateArgumentName(String): int [static]  [API 24+]
```

## MessagePattern.ApostropheMode — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ DOUBLE_OPTIONAL [field]  [API 24+]
+ DOUBLE_REQUIRED [field]  [API 24+]
+ valueOf(String): MessagePattern.ApostropheMode [static]  [API 24+]
+ values(): MessagePattern.ApostropheMode[] [static]  [API 24+]
```

## MessagePattern.ArgType — declaration; API 24+ (2 +, 0 *, 0 ~, 1 -)

```
+ CHOICE [field]  [API 24+]
+ NONE [field]  [API 24+]
+ PLURAL [field]  [API 24+]
+ SELECT [field]  [API 24+]
+ SELECTORDINAL [field]  [API 24+]
+ SIMPLE [field]  [API 24+]
- hasPluralStyle(): boolean  [API 24+]
+ valueOf(String): MessagePattern.ArgType [static]  [API 24+]
+ values(): MessagePattern.ArgType[] [static]  [API 24+]
```

## MessagePattern.Part — declaration; API 24+ (0 +, 0 *, 0 ~, 9 -)

```
- equals(Object): boolean  [API 24+]
- getArgType(): MessagePattern.ArgType  [API 24+]
- getIndex(): int  [API 24+]
- getLength(): int  [API 24+]
- getLimit(): int  [API 24+]
- getType(): MessagePattern.Part.Type  [API 24+]
- getValue(): int  [API 24+]
- hashCode(): int  [API 24+]
- toString(): String  [API 24+]
```

## MessagePattern.Part.Type — declaration; API 24+ (2 +, 0 *, 0 ~, 1 -)

```
+ ARG_DOUBLE [field]  [API 24+]
+ ARG_INT [field]  [API 24+]
+ ARG_LIMIT [field]  [API 24+]
+ ARG_NAME [field]  [API 24+]
+ ARG_NUMBER [field]  [API 24+]
+ ARG_SELECTOR [field]  [API 24+]
+ ARG_START [field]  [API 24+]
+ ARG_STYLE [field]  [API 24+]
+ ARG_TYPE [field]  [API 24+]
+ INSERT_CHAR [field]  [API 24+]
+ MSG_LIMIT [field]  [API 24+]
+ MSG_START [field]  [API 24+]
+ REPLACE_NUMBER [field]  [API 24+]
+ SKIP_SYNTAX [field]  [API 24+]
- hasNumericValue(): boolean  [API 24+]
+ valueOf(String): MessagePattern.Part.Type [static]  [API 24+]
+ values(): MessagePattern.Part.Type[] [static]  [API 24+]
```

## Normalizer — declaration; API 24+ (0 +, 0 *, 0 ~, 6 -)

```
- MAYBE [field]  [API 24+]
- NO [field]  [API 24+]
- YES [field]  [API 24+]
- clone(): Object  [API 24+]
- compare(String, String, int): int [static]  [API 24+]
- compare(char[], char[], int): int [static]  [API 24+]
- compare(char[], int, int, char[], int, int, int): int [static]  [API 24+]
- compare(int, String, int): int [static]  [API 24+]
- compare(int, int, int): int [static]  [API 24+]
```

## Normalizer.QuickCheckResult — declaration; API 24+ (0 +, 0 *, 0 ~, 0 -)

## Normalizer2 — declaration; API 24+ (11 +, 0 *, 0 ~, 10 -)

```
+ append(StringBuilder, CharSequence): StringBuilder  [API 24+]
- composePair(int, int): int  [API 24+]
- getCombiningClass(int): int  [API 24+]
+ getDecomposition(int): String  [API 24+]
- getInstance(InputStream, String, Normalizer2.Mode): Normalizer2 [static]  [API 24+, deprecated in 31]
- getNFCInstance(): Normalizer2 [static]  [API 24+]
- getNFDInstance(): Normalizer2 [static]  [API 24+]
- getNFKCCasefoldInstance(): Normalizer2 [static]  [API 24+]
- getNFKCInstance(): Normalizer2 [static]  [API 24+]
- getNFKDInstance(): Normalizer2 [static]  [API 24+]
- getRawDecomposition(int): String  [API 24+]
+ hasBoundaryAfter(int): boolean  [API 24+]
+ hasBoundaryBefore(int): boolean  [API 24+]
+ isInert(int): boolean  [API 24+]
+ isNormalized(CharSequence): boolean  [API 24+]
- normalize(CharSequence): String  [API 24+]
+ normalize(CharSequence, Appendable): Appendable  [API 24+]
+ normalize(CharSequence, StringBuilder): StringBuilder  [API 24+]
+ normalizeSecondAndAppend(StringBuilder, CharSequence): StringBuilder  [API 24+]
+ quickCheck(CharSequence): Normalizer.QuickCheckResult  [API 24+]
+ spanQuickCheckYes(CharSequence): int  [API 24+]
```

## Normalizer2.Mode — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ COMPOSE [field]  [API 24+]
+ COMPOSE_CONTIGUOUS [field]  [API 24+]
+ DECOMPOSE [field]  [API 24+]
+ FCD [field]  [API 24+]
+ valueOf(String): Normalizer2.Mode [static]  [API 24+]
+ values(): Normalizer2.Mode[] [static]  [API 24+]
```

## NumberFormat — declaration; API 24+ (6 +, 0 *, 0 ~, 58 -)

```
- NumberFormat()  [API 24+]
- clone(): Object  [API 24+]
- equals(Object): boolean  [API 24+]
- format(BigDecimal): String  [API 24+]
- format(BigDecimal): String  [API 24+]
+ format(BigDecimal, StringBuffer, FieldPosition): StringBuffer  [API 24+]
+ format(BigDecimal, StringBuffer, FieldPosition): StringBuffer  [API 24+]
- format(BigInteger): String  [API 24+]
+ format(BigInteger, StringBuffer, FieldPosition): StringBuffer  [API 24+]
- format(CurrencyAmount): String  [API 24+]
- format(CurrencyAmount, StringBuffer, FieldPosition): StringBuffer  [API 24+]
- format(Object, StringBuffer, FieldPosition): StringBuffer  [API 24+]
- format(double): String  [API 24+]
+ format(double, StringBuffer, FieldPosition): StringBuffer  [API 24+]
- format(long): String  [API 24+]
+ format(long, StringBuffer, FieldPosition): StringBuffer  [API 24+]
- getAvailableLocales(): Locale[] [static]  [API 24+]
- getContext(DisplayContext.Type): DisplayContext  [API 24+]
- getCurrency(): Currency  [API 24+]
- getCurrencyInstance(): NumberFormat [static]  [API 24+]
- getCurrencyInstance(Locale): NumberFormat [static]  [API 24+]
- getCurrencyInstance(ULocale): NumberFormat [static]  [API 24+]
- getInstance(): NumberFormat [static]  [API 24+]
- getInstance(Locale): NumberFormat [static]  [API 24+]
- getInstance(Locale, int): NumberFormat [static]  [API 24+]
- getInstance(ULocale): NumberFormat [static]  [API 24+]
- getInstance(ULocale, int): NumberFormat [static]  [API 24+]
- getInstance(int): NumberFormat [static]  [API 24+]
- getIntegerInstance(): NumberFormat [static]  [API 24+]
- getIntegerInstance(Locale): NumberFormat [static]  [API 24+]
- getIntegerInstance(ULocale): NumberFormat [static]  [API 24+]
- getMaximumFractionDigits(): int  [API 24+]
- getMaximumIntegerDigits(): int  [API 24+]
- getMinimumFractionDigits(): int  [API 24+]
- getMinimumIntegerDigits(): int  [API 24+]
- getNumberInstance(): NumberFormat [static]  [API 24+]
- getNumberInstance(Locale): NumberFormat [static]  [API 24+]
- getNumberInstance(ULocale): NumberFormat [static]  [API 24+]
- getPattern(ULocale, int): String [static]  [API 24+]
- getPercentInstance(): NumberFormat [static]  [API 24+]
- getPercentInstance(Locale): NumberFormat [static]  [API 24+]
- getPercentInstance(ULocale): NumberFormat [static]  [API 24+]
- getRoundingMode(): int  [API 24+]
- getScientificInstance(): NumberFormat [static]  [API 24+]
- getScientificInstance(Locale): NumberFormat [static]  [API 24+]
- getScientificInstance(ULocale): NumberFormat [static]  [API 24+]
- hashCode(): int  [API 24+]
- isGroupingUsed(): boolean  [API 24+]
- isParseIntegerOnly(): boolean  [API 24+]
- isParseStrict(): boolean  [API 24+]
- parse(String): Number  [API 24+]
+ parse(String, ParsePosition): Number  [API 24+]
- parseCurrency(CharSequence, ParsePosition): CurrencyAmount  [API 24+]
- parseObject(String, ParsePosition): Object  [API 24+]
- setContext(DisplayContext): void  [API 24+]
- setCurrency(Currency): void  [API 24+]
- setGroupingUsed(boolean): void  [API 24+]
- setMaximumFractionDigits(int): void  [API 24+]
- setMaximumIntegerDigits(int): void  [API 24+]
- setMinimumFractionDigits(int): void  [API 24+]
- setMinimumIntegerDigits(int): void  [API 24+]
- setParseIntegerOnly(boolean): void  [API 24+]
- setParseStrict(boolean): void  [API 24+]
- setRoundingMode(int): void  [API 24+]
```

## NumberFormat.Field — declaration; API 24+ (0 +, 0 *, 0 ~, 2 -)

```
- APPROXIMATELY_SIGN [field]  [API 35+]
- COMPACT [field]  [API 31+]
- CURRENCY [field]  [API 24+]
- DECIMAL_SEPARATOR [field]  [API 24+]
- EXPONENT [field]  [API 24+]
- EXPONENT_SIGN [field]  [API 24+]
- EXPONENT_SYMBOL [field]  [API 24+]
- FRACTION [field]  [API 24+]
- Field(String)  [API 24+]
- GROUPING_SEPARATOR [field]  [API 24+]
- INTEGER [field]  [API 24+]
- MEASURE_UNIT [field]  [API 31+]
- PERCENT [field]  [API 24+]
- PERMILLE [field]  [API 24+]
- SIGN [field]  [API 24+]
- readResolve(): Object  [API 24+]
```

## NumberingSystem — declaration; API 24+ (0 +, 0 *, 0 ~, 12 -)

```
- LATIN [field]  [API 29+]
- NumberingSystem()  [API 24+]
- getAvailableNames(): String[] [static]  [API 24+]
- getDescription(): String  [API 24+]
- getInstance(): NumberingSystem [static]  [API 24+]
- getInstance(Locale): NumberingSystem [static]  [API 24+]
- getInstance(ULocale): NumberingSystem [static]  [API 24+]
- getInstance(int, boolean, String): NumberingSystem [static]  [API 24+]
- getInstanceByName(String): NumberingSystem [static]  [API 24+]
- getName(): String  [API 24+]
- getRadix(): int  [API 24+]
- isAlgorithmic(): boolean  [API 24+]
- isValidDigitString(String): boolean [static]  [API 24+]
```

## PluralFormat — declaration; API 24+ (0 +, 0 *, 0 ~, 24 -)

```
- PluralFormat()  [API 24+]
- PluralFormat(Locale)  [API 24+]
- PluralFormat(Locale, PluralRules)  [API 24+]
- PluralFormat(Locale, PluralRules.PluralType)  [API 24+]
- PluralFormat(PluralRules)  [API 24+]
- PluralFormat(PluralRules, String)  [API 24+]
- PluralFormat(String)  [API 24+]
- PluralFormat(ULocale)  [API 24+]
- PluralFormat(ULocale, PluralRules)  [API 24+]
- PluralFormat(ULocale, PluralRules, String)  [API 24+]
- PluralFormat(ULocale, PluralRules.PluralType)  [API 24+]
- PluralFormat(ULocale, PluralRules.PluralType, String)  [API 24+]
- PluralFormat(ULocale, String)  [API 24+]
- applyPattern(String): void  [API 24+]
- equals(Object): boolean  [API 24+]
- equals(PluralFormat): boolean  [API 24+]
- format(Object, StringBuffer, FieldPosition): StringBuffer  [API 24+]
- format(double): String  [API 24+]
- hashCode(): int  [API 24+]
- parse(String, ParsePosition): Number  [API 24+]
- parseObject(String, ParsePosition): Object  [API 24+]
- setNumberFormat(NumberFormat): void  [API 24+]
- toPattern(): String  [API 24+]
- toString(): String  [API 24+]
```

## PluralRules — declaration; API 24+ (0 +, 0 *, 0 ~, 17 -)

```
- DEFAULT [field]  [API 24+]
- createRules(String): PluralRules [static]  [API 24+]
- equals(Object): boolean  [API 24+]
- equals(PluralRules): boolean  [API 24+]
- forLocale(Locale): PluralRules [static]  [API 24+]
- forLocale(Locale, PluralRules.PluralType): PluralRules [static]  [API 24+]
- forLocale(ULocale): PluralRules [static]  [API 24+]
- forLocale(ULocale, PluralRules.PluralType): PluralRules [static]  [API 24+]
- getAllKeywordValues(String): Collection  [API 24+]
- getKeywords(): Set  [API 24+]
- getSamples(String): Collection  [API 24+]
- getUniqueKeywordValue(String): double  [API 24+]
- hashCode(): int  [API 24+]
- parseDescription(String): PluralRules [static]  [API 24+]
- select(FormattedNumber): String  [API 31+]
- select(FormattedNumberRange): String  [API 33+]
- select(double): String  [API 24+]
- toString(): String  [API 24+]
```

## PluralRules.PluralType — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ CARDINAL [field]  [API 24+]
+ ORDINAL [field]  [API 24+]
+ valueOf(String): PluralRules.PluralType [static]  [API 24+]
+ values(): PluralRules.PluralType[] [static]  [API 24+]
```

## RelativeDateTimeFormatter — declaration; API 24+ (0 +, 0 *, 0 ~, 18 -)

```
- combineDateAndTime(String, String): String  [API 24+]
- format(RelativeDateTimeFormatter.Direction, RelativeDateTimeFormatter.AbsoluteUnit): String  [API 24+]
- format(double, RelativeDateTimeFormatter.Direction, RelativeDateTimeFormatter.RelativeUnit): String  [API 24+]
- format(double, RelativeDateTimeFormatter.RelativeDateTimeUnit): String  [API 28+]
- formatNumeric(double, RelativeDateTimeFormatter.RelativeDateTimeUnit): String  [API 28+]
- formatNumericToValue(double, RelativeDateTimeFormatter.RelativeDateTimeUnit): RelativeDateTimeFormatter.FormattedRelativeDateTime  [API 31+]
- formatToValue(RelativeDateTimeFormatter.Direction, RelativeDateTimeFormatter.AbsoluteUnit): RelativeDateTimeFormatter.FormattedRelativeDateTime  [API 31+]
- formatToValue(double, RelativeDateTimeFormatter.Direction, RelativeDateTimeFormatter.RelativeUnit): RelativeDateTimeFormatter.FormattedRelativeDateTime  [API 31+]
- formatToValue(double, RelativeDateTimeFormatter.RelativeDateTimeUnit): RelativeDateTimeFormatter.FormattedRelativeDateTime  [API 31+]
- getCapitalizationContext(): DisplayContext  [API 24+]
- getFormatStyle(): RelativeDateTimeFormatter.Style  [API 24+]
- getInstance(): RelativeDateTimeFormatter [static]  [API 24+]
- getInstance(Locale): RelativeDateTimeFormatter [static]  [API 24+]
- getInstance(Locale, NumberFormat): RelativeDateTimeFormatter [static]  [API 24+]
- getInstance(ULocale): RelativeDateTimeFormatter [static]  [API 24+]
- getInstance(ULocale, NumberFormat): RelativeDateTimeFormatter [static]  [API 24+]
- getInstance(ULocale, NumberFormat, RelativeDateTimeFormatter.Style, DisplayContext): RelativeDateTimeFormatter [static]  [API 24+]
- getNumberFormat(): NumberFormat  [API 24+]
```

## RelativeDateTimeFormatter.AbsoluteUnit — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ DAY [field]  [API 24+]
+ FRIDAY [field]  [API 24+]
+ HOUR [field]  [API 31+]
+ MINUTE [field]  [API 31+]
+ MONDAY [field]  [API 24+]
+ MONTH [field]  [API 24+]
+ NOW [field]  [API 24+]
+ QUARTER [field]  [API 28+]
+ SATURDAY [field]  [API 24+]
+ SUNDAY [field]  [API 24+]
+ THURSDAY [field]  [API 24+]
+ TUESDAY [field]  [API 24+]
+ WEDNESDAY [field]  [API 24+]
+ WEEK [field]  [API 24+]
+ YEAR [field]  [API 24+]
+ valueOf(String): RelativeDateTimeFormatter.AbsoluteUnit [static]  [API 24+]
+ values(): RelativeDateTimeFormatter.AbsoluteUnit[] [static]  [API 24+]
```

## RelativeDateTimeFormatter.Direction — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ LAST [field]  [API 24+]
+ LAST_2 [field]  [API 24+]
+ NEXT [field]  [API 24+]
+ NEXT_2 [field]  [API 24+]
+ PLAIN [field]  [API 24+]
+ THIS [field]  [API 24+]
+ valueOf(String): RelativeDateTimeFormatter.Direction [static]  [API 24+]
+ values(): RelativeDateTimeFormatter.Direction[] [static]  [API 24+]
```

## RelativeDateTimeFormatter.FormattedRelativeDateTime — declaration; API 31+ (0 +, 0 *, 0 ~, 7 -)

```
- appendTo(Appendable): Appendable  [API 31+]
- charAt(int): char  [API 31+]
- length(): int  [API 31+]
- nextPosition(ConstrainedFieldPosition): boolean  [API 31+]
- subSequence(int, int): CharSequence  [API 31+]
- toCharacterIterator(): AttributedCharacterIterator  [API 31+]
- toString(): String  [API 31+]
```

## RelativeDateTimeFormatter.RelativeDateTimeUnit — declaration; API 28+ (2 +, 0 *, 0 ~, 0 -)

```
+ DAY [field]  [API 28+]
+ FRIDAY [field]  [API 28+]
+ HOUR [field]  [API 28+]
+ MINUTE [field]  [API 28+]
+ MONDAY [field]  [API 28+]
+ MONTH [field]  [API 28+]
+ QUARTER [field]  [API 28+]
+ SATURDAY [field]  [API 28+]
+ SECOND [field]  [API 28+]
+ SUNDAY [field]  [API 28+]
+ THURSDAY [field]  [API 28+]
+ TUESDAY [field]  [API 28+]
+ WEDNESDAY [field]  [API 28+]
+ WEEK [field]  [API 28+]
+ YEAR [field]  [API 28+]
+ valueOf(String): RelativeDateTimeFormatter.RelativeDateTimeUnit [static]  [API 28+]
+ values(): RelativeDateTimeFormatter.RelativeDateTimeUnit[] [static]  [API 28+]
```

## RelativeDateTimeFormatter.RelativeUnit — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ DAYS [field]  [API 24+]
+ HOURS [field]  [API 24+]
+ MINUTES [field]  [API 24+]
+ MONTHS [field]  [API 24+]
+ SECONDS [field]  [API 24+]
+ WEEKS [field]  [API 24+]
+ YEARS [field]  [API 24+]
+ valueOf(String): RelativeDateTimeFormatter.RelativeUnit [static]  [API 24+]
+ values(): RelativeDateTimeFormatter.RelativeUnit[] [static]  [API 24+]
```

## RelativeDateTimeFormatter.Style — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ LONG [field]  [API 24+]
+ NARROW [field]  [API 24+]
+ SHORT [field]  [API 24+]
+ valueOf(String): RelativeDateTimeFormatter.Style [static]  [API 24+]
+ values(): RelativeDateTimeFormatter.Style[] [static]  [API 24+]
```

## Replaceable — declaration; API 24+ (8 +, 0 *, 0 ~, 0 -)

```
+ char32At(int): int  [API 24+]
+ charAt(int): char  [API 24+]
+ copy(int, int, int): void  [API 24+]
+ getChars(int, int, char[], int): void  [API 24+]
+ hasMetaData(): boolean  [API 24+]
+ length(): int  [API 24+]
+ replace(int, int, String): void  [API 24+]
+ replace(int, int, char[], int, int): void  [API 24+]
```

## RuleBasedCollator — declaration; API 24+ (0 +, 22 *, 0 ~, 24 -)

```
* RuleBasedCollator(String)  [API 24+]
- clone(): Object  [API 24+]
- cloneAsThawed(): RuleBasedCollator  [API 24+]
- compare(String, String): int  [API 24+]
- equals(Object): boolean  [API 24+]
- freeze(): Collator  [API 24+]
- getCollationElementIterator(CharacterIterator): CollationElementIterator  [API 24+]
- getCollationElementIterator(String): CollationElementIterator  [API 24+]
- getCollationElementIterator(UCharacterIterator): CollationElementIterator  [API 24+]
- getCollationKey(String): CollationKey  [API 24+]
- getContractionsAndExpansions(UnicodeSet, UnicodeSet, boolean): void  [API 24+]
* getDecomposition(): int  [API 24+]
* getMaxVariable(): int  [API 24+]
* getNumericCollation(): boolean  [API 24+]
* getReorderCodes(): int[]  [API 24+]
* getRules(): String  [API 24+]
- getRules(boolean): String  [API 24+]
* getStrength(): int  [API 24+]
- getTailoredSet(): UnicodeSet  [API 24+]
- getUCAVersion(): VersionInfo  [API 24+]
- getVariableTop(): int  [API 24+]
- getVersion(): VersionInfo  [API 24+]
- hashCode(): int  [API 24+]
* isAlternateHandlingShifted(): boolean  [API 24+]
* isCaseLevel(): boolean  [API 24+]
* isFrenchCollation(): boolean  [API 24+]
- isFrozen(): boolean  [API 24+]
* isLowerCaseFirst(): boolean  [API 24+]
* isUpperCaseFirst(): boolean  [API 24+]
- setAlternateHandlingDefault(): void  [API 24+]
* setAlternateHandlingShifted(boolean): void  [API 24+]
- setCaseFirstDefault(): void  [API 24+]
* setCaseLevel(boolean): void  [API 24+]
- setCaseLevelDefault(): void  [API 24+]
* setDecomposition(int): void  [API 24+]
- setDecompositionDefault(): void  [API 24+]
* setFrenchCollation(boolean): void  [API 24+]
- setFrenchCollationDefault(): void  [API 24+]
* setLowerCaseFirst(boolean): void  [API 24+]
* setMaxVariable(int): RuleBasedCollator  [API 24+]
* setNumericCollation(boolean): void  [API 24+]
- setNumericCollationDefault(): void  [API 24+]
* setReorderCodes(int[]): void  [API 24+]
* setStrength(int): void  [API 24+]
- setStrengthDefault(): void  [API 24+]
* setUpperCaseFirst(boolean): void  [API 24+]
```

## ScientificNumberFormatter — declaration; API 26+ (0 +, 0 *, 0 ~, 5 -)

```
- format(Object): String  [API 26+]
- getMarkupInstance(DecimalFormat, String, String): ScientificNumberFormatter [static]  [API 26+]
- getMarkupInstance(ULocale, String, String): ScientificNumberFormatter [static]  [API 26+]
- getSuperscriptInstance(DecimalFormat): ScientificNumberFormatter [static]  [API 26+]
- getSuperscriptInstance(ULocale): ScientificNumberFormatter [static]  [API 26+]
```

## SearchIterator — declaration; API 24+ (3 +, 10 *, 0 ~, 11 -)

```
- SearchIterator(CharacterIterator, BreakIterator)  [API 24+]
+ breakIterator [field]  [API 24+]
- first(): int  [API 24+]
- following(int): int  [API 24+]
* getBreakIterator(): BreakIterator  [API 24+]
* getElementComparisonType(): SearchIterator.ElementComparisonType  [API 24+]
+ getIndex(): int  [API 24+]
* getMatchLength(): int  [API 24+]
- getMatchStart(): int  [API 24+]
- getMatchedText(): String  [API 24+]
* getTarget(): CharacterIterator  [API 24+]
+ handleNext(int): int  [API 24+]
+ handlePrevious(int): int  [API 24+]
* isOverlapping(): boolean  [API 24+]
- last(): int  [API 24+]
+ matchLength [field]  [API 24+]
- next(): int  [API 24+]
- preceding(int): int  [API 24+]
- previous(): int  [API 24+]
- reset(): void  [API 24+]
* setBreakIterator(BreakIterator): void  [API 24+]
* setElementComparisonType(SearchIterator.ElementComparisonType): void  [API 24+]
- setIndex(int): void  [API 24+]
* setMatchLength(int): void  [API 24+]
* setOverlapping(boolean): void  [API 24+]
* setTarget(CharacterIterator): void  [API 24+]
+ targetText [field]  [API 24+]
```

## SearchIterator.ElementComparisonType — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ ANY_BASE_WEIGHT_IS_WILDCARD [field]  [API 24+]
+ PATTERN_BASE_WEIGHT_IS_WILDCARD [field]  [API 24+]
+ STANDARD_ELEMENT_COMPARISON [field]  [API 24+]
+ valueOf(String): SearchIterator.ElementComparisonType [static]  [API 24+]
+ values(): SearchIterator.ElementComparisonType[] [static]  [API 24+]
```

## SelectFormat — declaration; API 24+ (0 +, 0 *, 0 ~, 9 -)

```
- SelectFormat(String)  [API 24+]
- applyPattern(String): void  [API 24+]
- equals(Object): boolean  [API 24+]
- format(Object, StringBuffer, FieldPosition): StringBuffer  [API 24+]
- format(String): String  [API 24+]
- hashCode(): int  [API 24+]
- parseObject(String, ParsePosition): Object  [API 24+]
- toPattern(): String  [API 24+]
- toString(): String  [API 24+]
```

## SimpleDateFormat — declaration; API 24+ (0 +, 0 *, 0 ~, 33 -)

```
- SimpleDateFormat()  [API 24+]
- SimpleDateFormat(String)  [API 24+]
- SimpleDateFormat(String, DateFormatSymbols)  [API 24+]
- SimpleDateFormat(String, Locale)  [API 24+]
- SimpleDateFormat(String, String, ULocale)  [API 24+]
- SimpleDateFormat(String, ULocale)  [API 24+]
- applyLocalizedPattern(String): void  [API 24+]
- applyPattern(String): void  [API 24+]
- clone(): Object  [API 24+]
- equals(Object): boolean  [API 24+]
- format(Calendar, StringBuffer, FieldPosition): StringBuffer  [API 24+]
- formatToCharacterIterator(Object): AttributedCharacterIterator  [API 24+]
- get2DigitYearStart(): Date  [API 24+]
- getDateFormatSymbols(): DateFormatSymbols  [API 24+]
- getNumberFormat(char): NumberFormat  [API 24+]
- getSymbols(): DateFormatSymbols  [API 24+]
- getTimeZoneFormat(): TimeZoneFormat  [API 24+]
- hashCode(): int  [API 24+]
- matchQuarterString(String, int, int, String[], Calendar): int  [API 24+]
- matchString(String, int, int, String[], Calendar): int  [API 24+]
- parse(String, Calendar, ParsePosition): void  [API 24+]
- patternCharToDateFormatField(char): DateFormat.Field  [API 24+]
- set2DigitYearStart(Date): void  [API 24+]
- setContext(DisplayContext): void  [API 24+]
- setDateFormatSymbols(DateFormatSymbols): void  [API 24+]
- setNumberFormat(NumberFormat): void  [API 24+]
- setNumberFormat(String, NumberFormat): void  [API 24+]
- setTimeZoneFormat(TimeZoneFormat): void  [API 24+]
- subFormat(char, int, int, FieldPosition, DateFormatSymbols, Calendar): String  [API 24+]
- subParse(String, int, char, int, boolean, boolean, boolean[], Calendar): int  [API 24+]
- toLocalizedPattern(): String  [API 24+]
- toPattern(): String  [API 24+]
- zeroPaddingNumber(long, int, int): String  [API 24+]
```

## StringPrepParseException — declaration; API 24+ (1 +, 0 *, 0 ~, 6 -)

```
+ StringPrepParseException(String, int)  [API 24+]
- StringPrepParseException(String, int, String, int)  [API 24+]
- StringPrepParseException(String, int, String, int, int)  [API 24+]
- equals(Object): boolean  [API 24+]
- getError(): int  [API 24+]
- hashCode(): int  [API 24+]
- toString(): String  [API 24+]
```

## StringSearch — declaration; API 24+ (0 +, 10 *, 0 ~, 7 -)

```
- StringSearch(String, CharacterIterator, Locale)  [API 24+]
* StringSearch(String, CharacterIterator, RuleBasedCollator)  [API 24+]
- StringSearch(String, CharacterIterator, RuleBasedCollator, BreakIterator)  [API 24+]
- StringSearch(String, CharacterIterator, ULocale)  [API 24+]
- StringSearch(String, String)  [API 24+]
* getCollator(): RuleBasedCollator  [API 24+]
* getIndex(): int  [API 24+]
* getPattern(): String  [API 24+]
- handleNext(int): int  [API 24+]
- handlePrevious(int): int  [API 24+]
* isCanonical(): boolean  [API 24+]
- reset(): void  [API 24+]
* setCanonical(boolean): void  [API 24+]
* setCollator(RuleBasedCollator): void  [API 24+]
* setIndex(int): void  [API 24+]
* setPattern(String): void  [API 24+]
* setTarget(CharacterIterator): void  [API 24+]
```

## SymbolTable — declaration; API 24+ (3 +, 0 *, 0 ~, 0 -)

```
+ lookup(String): char[]  [API 24+]
+ lookupMatcher(int): UnicodeMatcher  [API 24+]
+ parseReference(String, ParsePosition, int): String  [API 24+]
```

## TimeZoneFormat — declaration; API 24+ (0 +, 0 *, 0 ~, 34 -)

```
- TimeZoneFormat(ULocale)  [API 24+]
- cloneAsThawed(): TimeZoneFormat  [API 24+]
- format(Object, StringBuffer, FieldPosition): StringBuffer  [API 24+]
- format(TimeZoneFormat.Style, TimeZone, long): String  [API 24+]
- format(TimeZoneFormat.Style, TimeZone, long, Output): String  [API 24+]
- formatOffsetISO8601Basic(int, boolean, boolean, boolean): String  [API 24+]
- formatOffsetISO8601Extended(int, boolean, boolean, boolean): String  [API 24+]
- formatOffsetLocalizedGMT(int): String  [API 24+]
- formatOffsetShortLocalizedGMT(int): String  [API 24+]
- formatToCharacterIterator(Object): AttributedCharacterIterator  [API 24+]
- freeze(): TimeZoneFormat  [API 24+]
- getDefaultParseOptions(): EnumSet  [API 24+]
- getGMTOffsetDigits(): String  [API 24+]
- getGMTOffsetPattern(TimeZoneFormat.GMTOffsetPatternType): String  [API 24+]
- getGMTPattern(): String  [API 24+]
- getGMTZeroFormat(): String  [API 24+]
- getInstance(Locale): TimeZoneFormat [static]  [API 24+]
- getInstance(ULocale): TimeZoneFormat [static]  [API 24+]
- getTimeZoneNames(): TimeZoneNames  [API 24+]
- isFrozen(): boolean  [API 24+]
- parse(String): TimeZone  [API 24+]
- parse(String, ParsePosition): TimeZone  [API 24+]
- parse(TimeZoneFormat.Style, String, ParsePosition, EnumSet, Output): TimeZone  [API 24+]
- parse(TimeZoneFormat.Style, String, ParsePosition, Output): TimeZone  [API 24+]
- parseObject(String, ParsePosition): Object  [API 24+]
- parseOffsetISO8601(String, ParsePosition): int  [API 24+]
- parseOffsetLocalizedGMT(String, ParsePosition): int  [API 24+]
- parseOffsetShortLocalizedGMT(String, ParsePosition): int  [API 24+]
- setDefaultParseOptions(EnumSet): TimeZoneFormat  [API 24+]
- setGMTOffsetDigits(String): TimeZoneFormat  [API 24+]
- setGMTOffsetPattern(TimeZoneFormat.GMTOffsetPatternType, String): TimeZoneFormat  [API 24+]
- setGMTPattern(String): TimeZoneFormat  [API 24+]
- setGMTZeroFormat(String): TimeZoneFormat  [API 24+]
- setTimeZoneNames(TimeZoneNames): TimeZoneFormat  [API 24+]
```

## TimeZoneFormat.GMTOffsetPatternType — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ NEGATIVE_H [field]  [API 24+]
+ NEGATIVE_HM [field]  [API 24+]
+ NEGATIVE_HMS [field]  [API 24+]
+ POSITIVE_H [field]  [API 24+]
+ POSITIVE_HM [field]  [API 24+]
+ POSITIVE_HMS [field]  [API 24+]
+ valueOf(String): TimeZoneFormat.GMTOffsetPatternType [static]  [API 24+]
+ values(): TimeZoneFormat.GMTOffsetPatternType[] [static]  [API 24+]
```

## TimeZoneFormat.ParseOption — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ ALL_STYLES [field]  [API 24+]
+ TZ_DATABASE_ABBREVIATIONS [field]  [API 24+]
+ valueOf(String): TimeZoneFormat.ParseOption [static]  [API 24+]
+ values(): TimeZoneFormat.ParseOption[] [static]  [API 24+]
```

## TimeZoneFormat.Style — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ EXEMPLAR_LOCATION [field]  [API 24+]
+ GENERIC_LOCATION [field]  [API 24+]
+ GENERIC_LONG [field]  [API 24+]
+ GENERIC_SHORT [field]  [API 24+]
+ ISO_BASIC_FIXED [field]  [API 24+]
+ ISO_BASIC_FULL [field]  [API 24+]
+ ISO_BASIC_LOCAL_FIXED [field]  [API 24+]
+ ISO_BASIC_LOCAL_FULL [field]  [API 24+]
+ ISO_BASIC_LOCAL_SHORT [field]  [API 24+]
+ ISO_BASIC_SHORT [field]  [API 24+]
+ ISO_EXTENDED_FIXED [field]  [API 24+]
+ ISO_EXTENDED_FULL [field]  [API 24+]
+ ISO_EXTENDED_LOCAL_FIXED [field]  [API 24+]
+ ISO_EXTENDED_LOCAL_FULL [field]  [API 24+]
+ LOCALIZED_GMT [field]  [API 24+]
+ LOCALIZED_GMT_SHORT [field]  [API 24+]
+ SPECIFIC_LONG [field]  [API 24+]
+ SPECIFIC_SHORT [field]  [API 24+]
+ ZONE_ID [field]  [API 24+]
+ ZONE_ID_SHORT [field]  [API 24+]
+ valueOf(String): TimeZoneFormat.Style [static]  [API 24+]
+ values(): TimeZoneFormat.Style[] [static]  [API 24+]
```

## TimeZoneFormat.TimeType — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ DAYLIGHT [field]  [API 24+]
+ STANDARD [field]  [API 24+]
+ UNKNOWN [field]  [API 24+]
+ valueOf(String): TimeZoneFormat.TimeType [static]  [API 24+]
+ values(): TimeZoneFormat.TimeType[] [static]  [API 24+]
```

## TimeZoneNames — declaration; API 24+ (6 +, 0 *, 0 ~, 5 -)

```
+ getAvailableMetaZoneIDs(): Set  [API 24+]
+ getAvailableMetaZoneIDs(String): Set  [API 24+]
- getDisplayName(String, TimeZoneNames.NameType, long): String  [API 24+]
- getExemplarLocationName(String): String  [API 24+]
- getInstance(Locale): TimeZoneNames [static]  [API 24+]
- getInstance(ULocale): TimeZoneNames [static]  [API 24+]
+ getMetaZoneDisplayName(String, TimeZoneNames.NameType): String  [API 24+]
+ getMetaZoneID(String, long): String  [API 24+]
+ getReferenceZoneID(String, String): String  [API 24+]
- getTZDBInstance(ULocale): TimeZoneNames [static]  [API 24+]
+ getTimeZoneDisplayName(String, TimeZoneNames.NameType): String  [API 24+]
```

## TimeZoneNames.NameType — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ EXEMPLAR_LOCATION [field]  [API 24+]
+ LONG_DAYLIGHT [field]  [API 24+]
+ LONG_GENERIC [field]  [API 24+]
+ LONG_STANDARD [field]  [API 24+]
+ SHORT_DAYLIGHT [field]  [API 24+]
+ SHORT_GENERIC [field]  [API 24+]
+ SHORT_STANDARD [field]  [API 24+]
+ valueOf(String): TimeZoneNames.NameType [static]  [API 24+]
+ values(): TimeZoneNames.NameType[] [static]  [API 24+]
```

## Transliterator — declaration; API 29+ (0 +, 2 *, 0 ~, 25 -)

```
- createFromRules(String, String, int): Transliterator [static]  [API 29+]
- filteredTransliterate(Replaceable, Transliterator.Position, boolean): void  [API 29+]
- finishTransliteration(Replaceable, Transliterator.Position): void  [API 29+]
- getAvailableIDs(): Enumeration [static]  [API 29+]
- getAvailableSources(): Enumeration [static]  [API 29+]
- getAvailableTargets(String): Enumeration [static]  [API 29+]
- getAvailableVariants(String, String): Enumeration [static]  [API 29+]
- getDisplayName(String): String [static]  [API 29+]
- getDisplayName(String, Locale): String [static]  [API 29+]
- getDisplayName(String, ULocale): String [static]  [API 29+]
- getElements(): Transliterator[]  [API 29+]
* getFilter(): UnicodeFilter  [API 29+]
- getID(): String  [API 29+]
- getInstance(String): Transliterator [static]  [API 29+]
- getInstance(String, int): Transliterator [static]  [API 29+]
- getInverse(): Transliterator  [API 29+]
- getMaximumContextLength(): int  [API 29+]
- getSourceSet(): UnicodeSet  [API 29+]
- getTargetSet(): UnicodeSet  [API 29+]
* setFilter(UnicodeFilter): void  [API 29+]
- toRules(boolean): String  [API 29+]
- transliterate(Replaceable): void  [API 29+]
- transliterate(Replaceable, Transliterator.Position): void  [API 29+]
- transliterate(Replaceable, Transliterator.Position, String): void  [API 29+]
- transliterate(Replaceable, Transliterator.Position, int): void  [API 29+]
- transliterate(Replaceable, int, int): int  [API 29+]
- transliterate(String): String  [API 29+]
```

## Transliterator.Position — declaration; API 29+ (0 +, 2 *, 0 ~, 7 -)

```
- Position()  [API 29+]
- Position(Transliterator.Position)  [API 29+]
* Position(int, int, int)  [API 29+]
* Position(int, int, int, int)  [API 29+]
+ contextLimit [field]  [API 29+]
+ contextStart [field]  [API 29+]
- equals(Object): boolean  [API 29+]
- hashCode(): int  [API 29+]
+ limit [field]  [API 29+]
- set(Transliterator.Position): void  [API 29+]
+ start [field]  [API 29+]
- toString(): String  [API 29+]
- validate(int): void  [API 29+]
```

## UCharacterIterator — declaration; API 24+ (7 +, 0 *, 0 ~, 18 -)

```
- UCharacterIterator()  [API 24+]
- clone(): Object  [API 24+]
+ current(): int  [API 24+]
- currentCodePoint(): int  [API 24+]
- getCharacterIterator(): CharacterIterator  [API 24+]
+ getIndex(): int  [API 24+]
- getInstance(CharacterIterator): UCharacterIterator [static]  [API 24+]
- getInstance(Replaceable): UCharacterIterator [static]  [API 24+]
- getInstance(String): UCharacterIterator [static]  [API 24+]
- getInstance(StringBuffer): UCharacterIterator [static]  [API 24+]
- getInstance(char[]): UCharacterIterator [static]  [API 24+]
- getInstance(char[], int, int): UCharacterIterator [static]  [API 24+]
+ getLength(): int  [API 24+]
- getText(): String  [API 24+]
- getText(char[]): int  [API 24+]
+ getText(char[], int): int  [API 24+]
- moveCodePointIndex(int): int  [API 24+]
- moveIndex(int): int  [API 24+]
+ next(): int  [API 24+]
- nextCodePoint(): int  [API 24+]
+ previous(): int  [API 24+]
- previousCodePoint(): int  [API 24+]
+ setIndex(int): void  [API 24+]
- setToLimit(): void  [API 24+]
- setToStart(): void  [API 24+]
```

## UFormat — declaration; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ UFormat()  [API 24+]
```

## UnicodeFilter — declaration; API 24+ (1 +, 0 *, 0 ~, 1 -)

```
+ contains(int): boolean  [API 24+]
- matches(Replaceable, int[], int, boolean): int  [API 24+]
```

## UnicodeMatcher — declaration; API 24+ (4 +, 0 *, 0 ~, 0 -)

```
+ addMatchSetTo(UnicodeSet): void  [API 24+]
+ matches(Replaceable, int[], int, boolean): int  [API 24+]
+ matchesIndexValue(int): boolean  [API 24+]
+ toPattern(boolean): String  [API 24+]
```

## UnicodeSet — declaration; API 24+ (0 +, 0 *, 0 ~, 96 -)

```
- ALL_CODE_POINTS [field]  [API 24+]
- EMPTY [field]  [API 24+]
- UnicodeSet()  [API 24+]
- UnicodeSet(String)  [API 24+]
- UnicodeSet(String, ParsePosition, SymbolTable)  [API 24+]
- UnicodeSet(String, ParsePosition, SymbolTable, int)  [API 24+]
- UnicodeSet(String, boolean)  [API 24+]
- UnicodeSet(String, int)  [API 24+]
- UnicodeSet(UnicodeSet)  [API 24+]
- UnicodeSet(int, int)  [API 24+]
- UnicodeSet(int[])  [API 24+]
- _generatePattern(StringBuffer, boolean): StringBuffer  [API 24+]
- _generatePattern(StringBuffer, boolean, boolean): StringBuffer  [API 24+]
- add(CharSequence): UnicodeSet  [API 24+]
- add(Iterable): UnicodeSet  [API 24+]
- add(int): UnicodeSet  [API 24+]
- add(int, int): UnicodeSet  [API 24+]
- addAll(CharSequence): UnicodeSet  [API 24+]
- addAll(CharSequence[]): UnicodeSet  [API 24+]
- addAll(Iterable): UnicodeSet  [API 24+]
- addAll(UnicodeSet): UnicodeSet  [API 24+]
- addAll(int, int): UnicodeSet  [API 24+]
- addAllTo(Collection): Collection  [API 24+]
- addMatchSetTo(UnicodeSet): void  [API 24+]
- applyIntPropertyValue(int, int): UnicodeSet  [API 24+]
- applyPattern(String): UnicodeSet  [API 24+]
- applyPattern(String, boolean): UnicodeSet  [API 24+]
- applyPattern(String, int): UnicodeSet  [API 24+]
- applyPropertyAlias(String, String): UnicodeSet  [API 24+]
- applyPropertyAlias(String, String, SymbolTable): UnicodeSet  [API 24+]
- charAt(int): int  [API 24+]
- clear(): UnicodeSet  [API 24+]
- clone(): Object  [API 24+]
- cloneAsThawed(): UnicodeSet  [API 24+]
- closeOver(int): UnicodeSet  [API 24+]
- compact(): UnicodeSet  [API 24+]
- compareTo(Iterable): int  [API 24+]
- compareTo(UnicodeSet): int  [API 24+]
- compareTo(UnicodeSet, UnicodeSet.ComparisonStyle): int  [API 24+]
- complement(): UnicodeSet  [API 24+]
- complement(CharSequence): UnicodeSet  [API 24+]
- complement(int): UnicodeSet  [API 24+]
- complement(int, int): UnicodeSet  [API 24+]
- complementAll(CharSequence): UnicodeSet  [API 24+]
- complementAll(UnicodeSet): UnicodeSet  [API 24+]
- contains(CharSequence): boolean  [API 24+]
- contains(int): boolean  [API 24+]
- contains(int, int): boolean  [API 24+]
- containsAll(Iterable): boolean  [API 24+]
- containsAll(String): boolean  [API 24+]
- containsAll(UnicodeSet): boolean  [API 24+]
- containsNone(CharSequence): boolean  [API 24+]
- containsNone(Iterable): boolean  [API 24+]
- containsNone(UnicodeSet): boolean  [API 24+]
- containsNone(int, int): boolean  [API 24+]
- containsSome(CharSequence): boolean  [API 24+]
- containsSome(Iterable): boolean  [API 24+]
- containsSome(UnicodeSet): boolean  [API 24+]
- containsSome(int, int): boolean  [API 24+]
- equals(Object): boolean  [API 24+]
- freeze(): UnicodeSet  [API 24+]
- from(CharSequence): UnicodeSet [static]  [API 24+]
- fromAll(CharSequence): UnicodeSet [static]  [API 24+]
- getRangeCount(): int  [API 24+]
- getRangeEnd(int): int  [API 24+]
- getRangeStart(int): int  [API 24+]
- hasStrings(): boolean  [API 34+]
- hashCode(): int  [API 24+]
- indexOf(int): int  [API 24+]
- isEmpty(): boolean  [API 24+]
- isFrozen(): boolean  [API 24+]
- iterator(): Iterator  [API 24+]
- matches(Replaceable, int[], int, boolean): int  [API 24+]
- matchesIndexValue(int): boolean  [API 24+]
- ranges(): Iterable  [API 24+]
- remove(CharSequence): UnicodeSet  [API 24+]
- remove(int): UnicodeSet  [API 24+]
- remove(int, int): UnicodeSet  [API 24+]
- removeAll(CharSequence): UnicodeSet  [API 24+]
- removeAll(Iterable): UnicodeSet  [API 24+]
- removeAll(UnicodeSet): UnicodeSet  [API 24+]
- removeAllStrings(): UnicodeSet  [API 24+]
- retain(CharSequence): UnicodeSet  [API 24+]
- retain(int): UnicodeSet  [API 24+]
- retain(int, int): UnicodeSet  [API 24+]
- retainAll(CharSequence): UnicodeSet  [API 24+]
- retainAll(Iterable): UnicodeSet  [API 24+]
- retainAll(UnicodeSet): UnicodeSet  [API 24+]
- set(UnicodeSet): UnicodeSet  [API 24+]
- set(int, int): UnicodeSet  [API 24+]
- size(): int  [API 24+]
- span(CharSequence, UnicodeSet.SpanCondition): int  [API 24+]
- span(CharSequence, int, UnicodeSet.SpanCondition): int  [API 24+]
- spanBack(CharSequence, UnicodeSet.SpanCondition): int  [API 24+]
- spanBack(CharSequence, int, UnicodeSet.SpanCondition): int  [API 24+]
- strings(): Collection  [API 24+]
- toPattern(boolean): String  [API 24+]
- toString(): String  [API 24+]
```

## UnicodeSet.ComparisonStyle — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ LEXICOGRAPHIC [field]  [API 24+]
+ LONGER_FIRST [field]  [API 24+]
+ SHORTER_FIRST [field]  [API 24+]
+ valueOf(String): UnicodeSet.ComparisonStyle [static]  [API 24+]
+ values(): UnicodeSet.ComparisonStyle[] [static]  [API 24+]
```

## UnicodeSet.EntryRange — declaration; API 24+ (0 +, 0 *, 0 ~, 1 -)

```
+ codepoint [field]  [API 24+]
+ codepointEnd [field]  [API 24+]
- toString(): String  [API 24+]
```

## UnicodeSet.SpanCondition — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ CONDITION_COUNT [field]  [API 24+]
+ CONTAINED [field]  [API 24+]
+ NOT_CONTAINED [field]  [API 24+]
+ SIMPLE [field]  [API 24+]
+ valueOf(String): UnicodeSet.SpanCondition [static]  [API 24+]
+ values(): UnicodeSet.SpanCondition[] [static]  [API 24+]
```

## UnicodeSetIterator — declaration; API 24+ (0 +, 0 *, 0 ~, 8 -)

```
+ IS_STRING [field]  [API 24+]
- UnicodeSetIterator()  [API 24+]
- UnicodeSetIterator(UnicodeSet)  [API 24+]
+ codepoint [field]  [API 24+]
+ codepointEnd [field]  [API 24+]
- getString(): String  [API 24+]
- next(): boolean  [API 24+]
- nextRange(): boolean  [API 24+]
- reset(): void  [API 24+]
- reset(UnicodeSet): void  [API 24+]
- skipToStrings(): UnicodeSetIterator  [API 34+]
+ string [field]  [API 24+]
```

## UnicodeSetSpanner — declaration; API 24+ (0 +, 0 *, 0 ~, 15 -)

```
- UnicodeSetSpanner(UnicodeSet)  [API 24+]
- countIn(CharSequence): int  [API 24+]
- countIn(CharSequence, UnicodeSetSpanner.CountMethod): int  [API 24+]
- countIn(CharSequence, UnicodeSetSpanner.CountMethod, UnicodeSet.SpanCondition): int  [API 24+]
- deleteFrom(CharSequence): String  [API 24+]
- deleteFrom(CharSequence, UnicodeSet.SpanCondition): String  [API 24+]
- equals(Object): boolean  [API 24+]
- getUnicodeSet(): UnicodeSet  [API 24+]
- hashCode(): int  [API 24+]
- replaceFrom(CharSequence, CharSequence): String  [API 24+]
- replaceFrom(CharSequence, CharSequence, UnicodeSetSpanner.CountMethod): String  [API 24+]
- replaceFrom(CharSequence, CharSequence, UnicodeSetSpanner.CountMethod, UnicodeSet.SpanCondition): String  [API 24+]
- trim(CharSequence): CharSequence  [API 24+]
- trim(CharSequence, UnicodeSetSpanner.TrimOption): CharSequence  [API 24+]
- trim(CharSequence, UnicodeSetSpanner.TrimOption, UnicodeSet.SpanCondition): CharSequence  [API 24+]
```

## UnicodeSetSpanner.CountMethod — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ MIN_ELEMENTS [field]  [API 24+]
+ WHOLE_SPAN [field]  [API 24+]
+ valueOf(String): UnicodeSetSpanner.CountMethod [static]  [API 24+]
+ values(): UnicodeSetSpanner.CountMethod[] [static]  [API 24+]
```

## UnicodeSetSpanner.TrimOption — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ BOTH [field]  [API 24+]
+ LEADING [field]  [API 24+]
+ TRAILING [field]  [API 24+]
+ valueOf(String): UnicodeSetSpanner.TrimOption [static]  [API 24+]
+ values(): UnicodeSetSpanner.TrimOption[] [static]  [API 24+]
```

