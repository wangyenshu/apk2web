# android.text.style

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AbsoluteSizeSpan — framework; API 1+ (9 +, 0 *, 2 ~, 0 -)

```
+ AbsoluteSizeSpan(Parcel)  [API 3+]
+ AbsoluteSizeSpan(int)  [API 1+]
+ AbsoluteSizeSpan(int, boolean)  [API 5+]
~ describeContents(): int  [API 1+]
+ getDip(): boolean  [API 5+]
+ getSize(): int  [API 1+]
~ getSpanTypeId(): int  [API 1+]
+ toString(): String  [API 1+]
+ updateDrawState(TextPaint): void  [API 1+]
+ updateMeasureState(TextPaint): void  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## AlignmentSpan — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ getAlignment(): Layout.Alignment  [API 1+]
```

## AlignmentSpan.Standard — framework; API 1+ (5 +, 0 *, 2 ~, 0 -)

```
+ Standard(Layout.Alignment)  [API 1+]
+ Standard(Parcel)  [API 3+]
~ describeContents(): int  [API 1+]
+ getAlignment(): Layout.Alignment  [API 1+]
~ getSpanTypeId(): int  [API 1+]
+ toString(): String  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## BackgroundColorSpan — framework; API 1+ (6 +, 0 *, 2 ~, 0 -)

```
+ BackgroundColorSpan(Parcel)  [API 3+]
+ BackgroundColorSpan(int)  [API 1+]
~ describeContents(): int  [API 1+]
+ getBackgroundColor(): int  [API 1+]
~ getSpanTypeId(): int  [API 1+]
+ toString(): String  [API 1+]
+ updateDrawState(TextPaint): void  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## BulletSpan — framework; API 1+ (12 +, 0 *, 2 ~, 0 -)

```
+ BulletSpan()  [API 1+]
+ BulletSpan(Parcel)  [API 3+]
+ BulletSpan(int)  [API 1+]
+ BulletSpan(int, int)  [API 1+]
+ BulletSpan(int, int, int)  [API 28+]
~ describeContents(): int  [API 1+]
+ drawLeadingMargin(Canvas, Paint, int, int, int, int, int, CharSequence, int, int, boolean, Layout): void  [API 1+]
+ getBulletRadius(): int  [API 28+]
+ getColor(): int  [API 28+]
+ getGapWidth(): int  [API 28+]
+ getLeadingMargin(boolean): int  [API 1+]
~ getSpanTypeId(): int  [API 1+]
+ toString(): String  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## CharacterStyle — framework; API 1+ (3 +, 0 *, 1 ~, 0 -)

```
+ CharacterStyle()  [API 1+]
~ getUnderlying(): CharacterStyle  [API 1+]
+ updateDrawState(TextPaint): void  [API 1+]
+ wrap(CharacterStyle): CharacterStyle [static]  [API 1+]
```

## ClickableSpan — framework; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ ClickableSpan()  [API 1+]
+ onClick(View): void  [API 1+]
+ toString(): String  [API 1+]
+ updateDrawState(TextPaint): void  [API 1+]
```

## DrawableMarginSpan — framework; API 1+ (6 +, 0 *, 0 ~, 2 -)

```
+ DrawableMarginSpan(Drawable)  [API 1+]
+ DrawableMarginSpan(Drawable, int)  [API 1+]
+ chooseHeight(CharSequence, int, int, int, int, Paint.FontMetricsInt): void  [API 1+]
+ drawLeadingMargin(Canvas, Paint, int, int, int, int, int, CharSequence, int, int, boolean, Layout): void  [API 1+]
- getDrawable(): Drawable  [API 34+]
+ getLeadingMargin(boolean): int  [API 1+]
- getPadding(): int  [API 34+]
+ toString(): String  [API 1+]
```

## DynamicDrawableSpan — framework; API 1+ (7 +, 0 *, 0 ~, 0 -)

```
+ DynamicDrawableSpan()  [API 1+]
+ DynamicDrawableSpan(int)  [API 3+]
+ draw(Canvas, CharSequence, int, int, float, int, int, int, Paint): void  [API 1+]
+ getDrawable(): Drawable  [API 1+]
+ getSize(Paint, CharSequence, int, int, Paint.FontMetricsInt): int  [API 1+]
+ getVerticalAlignment(): int  [API 3+]
+ mVerticalAlignment [field]  [API 3+]
+ toString(): String  [API 1+]
```

## EasyEditSpan — framework; API 14+ (2 +, 0 *, 3 ~, 1 -)

```
+ EasyEditSpan()  [API 14+]
- EasyEditSpan(Parcel)  [API 18+]
+ EasyEditSpan(PendingIntent)  [API 18+]
~ describeContents(): int  [API 14+]
~ getSpanTypeId(): int  [API 14+]
~ writeToParcel(Parcel, int): void  [API 14+]
```

## ForegroundColorSpan — framework; API 1+ (6 +, 0 *, 2 ~, 0 -)

```
+ ForegroundColorSpan(Parcel)  [API 3+]
+ ForegroundColorSpan(int)  [API 1+]
~ describeContents(): int  [API 1+]
+ getForegroundColor(): int  [API 1+]
~ getSpanTypeId(): int  [API 1+]
+ toString(): String  [API 1+]
+ updateDrawState(TextPaint): void  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## IconMarginSpan — framework; API 1+ (6 +, 0 *, 0 ~, 2 -)

```
+ IconMarginSpan(Bitmap)  [API 1+]
+ IconMarginSpan(Bitmap, int)  [API 1+]
+ chooseHeight(CharSequence, int, int, int, int, Paint.FontMetricsInt): void  [API 1+]
+ drawLeadingMargin(Canvas, Paint, int, int, int, int, int, CharSequence, int, int, boolean, Layout): void  [API 1+]
- getBitmap(): Bitmap  [API 34+]
+ getLeadingMargin(boolean): int  [API 1+]
- getPadding(): int  [API 34+]
+ toString(): String  [API 1+]
```

## ImageSpan — framework; API 1+ (15 +, 0 *, 0 ~, 0 -)

```
+ ImageSpan(Bitmap)  [API 1+, deprecated in 15]
+ ImageSpan(Bitmap, int)  [API 3+, deprecated in 15]
+ ImageSpan(Context, Bitmap)  [API 4+]
+ ImageSpan(Context, Bitmap, int)  [API 4+]
+ ImageSpan(Context, Uri)  [API 1+]
+ ImageSpan(Context, Uri, int)  [API 3+]
+ ImageSpan(Context, int)  [API 1+]
+ ImageSpan(Context, int, int)  [API 3+]
+ ImageSpan(Drawable)  [API 1+]
+ ImageSpan(Drawable, String)  [API 1+]
+ ImageSpan(Drawable, String, int)  [API 3+]
+ ImageSpan(Drawable, int)  [API 3+]
+ getDrawable(): Drawable  [API 1+]
+ getSource(): String  [API 1+]
+ toString(): String  [API 1+]
```

## LeadingMarginSpan — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ drawLeadingMargin(Canvas, Paint, int, int, int, int, int, CharSequence, int, int, boolean, Layout): void  [API 1+]
+ getLeadingMargin(boolean): int  [API 1+]
```

## LeadingMarginSpan.LeadingMarginSpan2 — framework; API 8+ (1 +, 0 *, 0 ~, 0 -)

```
+ getLeadingMarginLineCount(): int  [API 8+]
```

## LeadingMarginSpan.Standard — framework; API 1+ (5 +, 0 *, 3 ~, 0 -)

```
+ Standard(Parcel)  [API 3+]
+ Standard(int)  [API 1+]
+ Standard(int, int)  [API 1+]
~ describeContents(): int  [API 1+]
~ drawLeadingMargin(Canvas, Paint, int, int, int, int, int, CharSequence, int, int, boolean, Layout): void  [API 1+]
+ getLeadingMargin(boolean): int  [API 1+]
~ getSpanTypeId(): int  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## LineBackgroundSpan — declaration; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ drawBackground(Canvas, Paint, int, int, int, int, int, CharSequence, int, int, int): void  [API 1+]
```

## LineBackgroundSpan.Standard — declaration; API 29+ (0 +, 2 *, 0 ~, 5 -)

```
- Standard(Parcel)  [API 29+]
* Standard(int)  [API 29+]
- describeContents(): int  [API 29+]
- drawBackground(Canvas, Paint, int, int, int, int, int, CharSequence, int, int, int): void  [API 29+]
* getColor(): int  [API 29+]
- getSpanTypeId(): int  [API 29+]
- writeToParcel(Parcel, int): void  [API 29+]
```

## LineBreakConfigSpan — declaration; API 35+ (0 +, 2 *, 0 ~, 8 -)

```
- CREATOR [field]  [API 35+]
* LineBreakConfigSpan(LineBreakConfig)  [API 35+]
- createNoBreakSpan(): LineBreakConfigSpan [static]  [API 35+]
- createNoHyphenationSpan(): LineBreakConfigSpan [static]  [API 35+]
- describeContents(): int  [API 35+]
- equals(Object): boolean  [API 35+]
* getLineBreakConfig(): LineBreakConfig  [API 35+]
- getSpanTypeId(): int  [API 35+]
- hashCode(): int  [API 35+]
- toString(): String  [API 35+]
- writeToParcel(Parcel, int): void  [API 35+]
```

## LineHeightSpan — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ chooseHeight(CharSequence, int, int, int, int, Paint.FontMetricsInt): void  [API 1+]
```

## LineHeightSpan.Standard — framework; API 29+ (3 +, 0 *, 0 ~, 4 -)

```
- Standard(Parcel)  [API 29+]
+ Standard(int)  [API 29+]
+ chooseHeight(CharSequence, int, int, int, int, Paint.FontMetricsInt): void  [API 29+]
- describeContents(): int  [API 29+]
+ getHeight(): int  [API 29+]
- getSpanTypeId(): int  [API 29+]
- writeToParcel(Parcel, int): void  [API 29+]
```

## LineHeightSpan.WithDensity — framework; API 5+ (1 +, 0 *, 0 ~, 0 -)

```
+ chooseHeight(CharSequence, int, int, int, int, Paint.FontMetricsInt, TextPaint): void  [API 5+]
```

## LocaleSpan — framework; API 17+ (5 +, 0 *, 4 ~, 2 -)

```
+ LocaleSpan(Locale)  [API 17+]
- LocaleSpan(LocaleList)  [API 24+]
+ LocaleSpan(Parcel)  [API 17+]
~ describeContents(): int  [API 17+]
+ getLocale(): Locale  [API 17+]
- getLocales(): LocaleList  [API 24+]
~ getSpanTypeId(): int  [API 17+]
+ toString(): String  [API 17+]
~ updateDrawState(TextPaint): void  [API 17+]
~ updateMeasureState(TextPaint): void  [API 17+]
+ writeToParcel(Parcel, int): void  [API 17+]
```

## MaskFilterSpan — framework; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ MaskFilterSpan(MaskFilter)  [API 1+]
+ getMaskFilter(): MaskFilter  [API 1+]
+ toString(): String  [API 1+]
+ updateDrawState(TextPaint): void  [API 1+]
```

## MetricAffectingSpan — framework; API 1+ (2 +, 0 *, 1 ~, 0 -)

```
+ MetricAffectingSpan()  [API 1+]
~ getUnderlying(): MetricAffectingSpan  [API 1+]
+ updateMeasureState(TextPaint): void  [API 1+]
```

## ParagraphStyle — framework; API 1+ (0 +, 0 *, 0 ~, 0 -)

## QuoteSpan — framework; API 1+ (11 +, 0 *, 2 ~, 0 -)

```
+ QuoteSpan()  [API 1+]
+ QuoteSpan(Parcel)  [API 3+]
+ QuoteSpan(int)  [API 1+]
+ QuoteSpan(int, int, int)  [API 28+]
~ describeContents(): int  [API 1+]
+ drawLeadingMargin(Canvas, Paint, int, int, int, int, int, CharSequence, int, int, boolean, Layout): void  [API 1+]
+ getColor(): int  [API 1+]
+ getGapWidth(): int  [API 28+]
+ getLeadingMargin(boolean): int  [API 1+]
~ getSpanTypeId(): int  [API 1+]
+ getStripeWidth(): int  [API 28+]
+ toString(): String  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## RasterizerSpan — declaration; API 1-25, removed in 26 (0 +, 0 *, 0 ~, 2 -)

```
- RasterizerSpan(Rasterizer)  [API 1-25, removed in 26]
- getRasterizer(): Rasterizer  [API 1-25, removed in 26]
```

## RelativeSizeSpan — framework; API 1+ (7 +, 0 *, 2 ~, 0 -)

```
+ RelativeSizeSpan(Parcel)  [API 3+]
+ RelativeSizeSpan(float)  [API 1+]
~ describeContents(): int  [API 1+]
+ getSizeChange(): float  [API 1+]
~ getSpanTypeId(): int  [API 1+]
+ toString(): String  [API 1+]
+ updateDrawState(TextPaint): void  [API 1+]
+ updateMeasureState(TextPaint): void  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## ReplacementSpan — framework; API 1+ (5 +, 0 *, 2 ~, 0 -)

```
+ ReplacementSpan()  [API 1+]
+ draw(Canvas, CharSequence, int, int, float, int, int, int, Paint): void  [API 1+]
+ getContentDescription(): CharSequence  [API 30+]
+ getSize(Paint, CharSequence, int, int, Paint.FontMetricsInt): int  [API 1+]
+ setContentDescription(CharSequence): void  [API 30+]
~ updateDrawState(TextPaint): void  [API 1+]
~ updateMeasureState(TextPaint): void  [API 1+]
```

## ScaleXSpan — framework; API 1+ (7 +, 0 *, 2 ~, 0 -)

```
+ ScaleXSpan(Parcel)  [API 3+]
+ ScaleXSpan(float)  [API 1+]
~ describeContents(): int  [API 1+]
+ getScaleX(): float  [API 1+]
~ getSpanTypeId(): int  [API 1+]
+ toString(): String  [API 1+]
+ updateDrawState(TextPaint): void  [API 1+]
+ updateMeasureState(TextPaint): void  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## StrikethroughSpan — framework; API 1+ (4 +, 0 *, 3 ~, 0 -)

```
+ StrikethroughSpan()  [API 1+]
+ StrikethroughSpan(Parcel)  [API 3+]
~ describeContents(): int  [API 1+]
~ getSpanTypeId(): int  [API 1+]
+ toString(): String  [API 1+]
+ updateDrawState(TextPaint): void  [API 1+]
~ writeToParcel(Parcel, int): void  [API 1+]
```

## StyleSpan — framework; API 1+ (8 +, 0 *, 3 ~, 0 -)

```
+ StyleSpan(Parcel)  [API 3+]
+ StyleSpan(int)  [API 1+]
+ StyleSpan(int, int)  [API 33+]
~ describeContents(): int  [API 1+]
~ getFontWeightAdjustment(): int  [API 33+]
~ getSpanTypeId(): int  [API 1+]
+ getStyle(): int  [API 1+]
+ toString(): String  [API 1+]
+ updateDrawState(TextPaint): void  [API 1+]
+ updateMeasureState(TextPaint): void  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## SubscriptSpan — framework; API 1+ (5 +, 0 *, 3 ~, 0 -)

```
+ SubscriptSpan()  [API 1+]
+ SubscriptSpan(Parcel)  [API 3+]
~ describeContents(): int  [API 1+]
~ getSpanTypeId(): int  [API 1+]
+ toString(): String  [API 1+]
+ updateDrawState(TextPaint): void  [API 1+]
+ updateMeasureState(TextPaint): void  [API 1+]
~ writeToParcel(Parcel, int): void  [API 1+]
```

## SuggestionRangeSpan — framework; API 33+ (6 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 33+]
+ SuggestionRangeSpan()  [API 33+]
~ describeContents(): int  [API 33+]
+ getBackgroundColor(): int  [API 33+]
+ getSpanTypeId(): int  [API 33+]
+ setBackgroundColor(int): void  [API 33+]
+ updateDrawState(TextPaint): void  [API 33+]
+ writeToParcel(Parcel, int): void  [API 33+]
```

## SuggestionSpan — framework; API 14+ (8 +, 0 *, 4 ~, 4 -)

```
- CREATOR [field]  [API 14+]
- SuggestionSpan(Context, Locale, String[], int, Class)  [API 14+]
+ SuggestionSpan(Context, String[], int)  [API 14+]
+ SuggestionSpan(Locale, String[], int)  [API 14+]
- SuggestionSpan(Parcel)  [API 14+]
~ describeContents(): int  [API 14+]
+ equals(Object): boolean  [API 14+]
+ getFlags(): int  [API 14+]
- getLocale(): String  [API 14+, deprecated in 24]
~ getLocaleObject(): Locale  [API 24+]
~ getSpanTypeId(): int  [API 14+]
+ getSuggestions(): String[]  [API 14+]
- getUnderlineColor(): int  [API 29+]
+ hashCode(): int  [API 14+]
+ setFlags(int): void  [API 14+]
~ updateDrawState(TextPaint): void  [API 14+]
+ writeToParcel(Parcel, int): void  [API 14+]
```

## SuperscriptSpan — framework; API 1+ (5 +, 0 *, 3 ~, 0 -)

```
+ SuperscriptSpan()  [API 1+]
+ SuperscriptSpan(Parcel)  [API 3+]
~ describeContents(): int  [API 1+]
~ getSpanTypeId(): int  [API 1+]
+ toString(): String  [API 1+]
+ updateDrawState(TextPaint): void  [API 1+]
+ updateMeasureState(TextPaint): void  [API 1+]
~ writeToParcel(Parcel, int): void  [API 1+]
```

## TabStopSpan — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ getTabStop(): int  [API 1+]
```

## TabStopSpan.Standard — framework; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ Standard(int)  [API 1+]
+ getTabStop(): int  [API 1+]
+ toString(): String  [API 1+]
```

## TextAppearanceSpan — framework; API 1+ (13 +, 0 *, 2 ~, 11 -)

```
+ TextAppearanceSpan(Context, int)  [API 1+]
+ TextAppearanceSpan(Context, int, int)  [API 1+]
+ TextAppearanceSpan(Parcel)  [API 3+]
+ TextAppearanceSpan(String, int, int, ColorStateList, ColorStateList)  [API 1+]
~ describeContents(): int  [API 1+]
+ getFamily(): String  [API 1+]
- getFontFeatureSettings(): String  [API 29+]
- getFontVariationSettings(): String  [API 29+]
- getLetterSpacing(): float  [API 34+]
+ getLinkTextColor(): ColorStateList  [API 1+]
- getShadowColor(): int  [API 29+]
- getShadowDx(): float  [API 29+]
- getShadowDy(): float  [API 29+]
- getShadowRadius(): float  [API 29+]
~ getSpanTypeId(): int  [API 1+]
+ getTextColor(): ColorStateList  [API 1+]
- getTextFontWeight(): int  [API 29+]
- getTextLocales(): LocaleList  [API 29+]
+ getTextSize(): int  [API 1+]
+ getTextStyle(): int  [API 1+]
- getTypeface(): Typeface  [API 29+]
- isElegantTextHeight(): boolean  [API 29+]
+ toString(): String  [API 1+]
+ updateDrawState(TextPaint): void  [API 1+]
+ updateMeasureState(TextPaint): void  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## TtsSpan — framework; API 21+ (6 +, 0 *, 1 ~, 0 -)

```
+ TtsSpan(Parcel)  [API 21+]
+ TtsSpan(String, PersistableBundle)  [API 21+]
~ describeContents(): int  [API 21+]
+ getArgs(): PersistableBundle  [API 21+]
+ getSpanTypeId(): int  [API 21+]
+ getType(): String  [API 21+]
+ writeToParcel(Parcel, int): void  [API 21+]
```

## TtsSpan.Builder — framework; API 21+ (5 +, 0 *, 0 ~, 0 -)

```
+ Builder(String)  [API 21+]
+ build(): TtsSpan  [API 21+]
+ setIntArgument(String, int): TtsSpan.Builder  [API 21+]
+ setLongArgument(String, long): TtsSpan.Builder  [API 21+]
+ setStringArgument(String, String): TtsSpan.Builder  [API 21+]
```

## TtsSpan.CardinalBuilder — framework; API 21+ (5 +, 0 *, 0 ~, 0 -)

```
+ CardinalBuilder()  [API 21+]
+ CardinalBuilder(String)  [API 21+]
+ CardinalBuilder(long)  [API 21+]
+ setNumber(String): TtsSpan.CardinalBuilder  [API 21+]
+ setNumber(long): TtsSpan.CardinalBuilder  [API 21+]
```

## TtsSpan.DateBuilder — framework; API 21+ (6 +, 0 *, 0 ~, 0 -)

```
+ DateBuilder()  [API 21+]
+ DateBuilder(Integer, Integer, Integer, Integer)  [API 21+]
+ setDay(int): TtsSpan.DateBuilder  [API 21+]
+ setMonth(int): TtsSpan.DateBuilder  [API 21+]
+ setWeekday(int): TtsSpan.DateBuilder  [API 21+]
+ setYear(int): TtsSpan.DateBuilder  [API 21+]
```

## TtsSpan.DecimalBuilder — framework; API 21+ (7 +, 0 *, 0 ~, 0 -)

```
+ DecimalBuilder()  [API 21+]
+ DecimalBuilder(String, String)  [API 21+]
+ DecimalBuilder(double, int, int)  [API 21+]
+ setArgumentsFromDouble(double, int, int): TtsSpan.DecimalBuilder  [API 21+]
+ setFractionalPart(String): TtsSpan.DecimalBuilder  [API 21+]
+ setIntegerPart(String): TtsSpan.DecimalBuilder  [API 21+]
+ setIntegerPart(long): TtsSpan.DecimalBuilder  [API 21+]
```

## TtsSpan.DigitsBuilder — framework; API 21+ (3 +, 0 *, 0 ~, 0 -)

```
+ DigitsBuilder()  [API 21+]
+ DigitsBuilder(String)  [API 21+]
+ setDigits(String): TtsSpan.DigitsBuilder  [API 21+]
```

## TtsSpan.ElectronicBuilder — framework; API 21+ (10 +, 0 *, 0 ~, 0 -)

```
+ ElectronicBuilder()  [API 21+]
+ setDomain(String): TtsSpan.ElectronicBuilder  [API 21+]
+ setEmailArguments(String, String): TtsSpan.ElectronicBuilder  [API 21+]
+ setFragmentId(String): TtsSpan.ElectronicBuilder  [API 21+]
+ setPassword(String): TtsSpan.ElectronicBuilder  [API 21+]
+ setPath(String): TtsSpan.ElectronicBuilder  [API 21+]
+ setPort(int): TtsSpan.ElectronicBuilder  [API 21+]
+ setProtocol(String): TtsSpan.ElectronicBuilder  [API 21+]
+ setQueryString(String): TtsSpan.ElectronicBuilder  [API 21+]
+ setUsername(String): TtsSpan.ElectronicBuilder  [API 21+]
```

## TtsSpan.FractionBuilder — framework; API 21+ (8 +, 0 *, 0 ~, 0 -)

```
+ FractionBuilder()  [API 21+]
+ FractionBuilder(long, long, long)  [API 21+]
+ setDenominator(String): TtsSpan.FractionBuilder  [API 21+]
+ setDenominator(long): TtsSpan.FractionBuilder  [API 21+]
+ setIntegerPart(String): TtsSpan.FractionBuilder  [API 21+]
+ setIntegerPart(long): TtsSpan.FractionBuilder  [API 21+]
+ setNumerator(String): TtsSpan.FractionBuilder  [API 21+]
+ setNumerator(long): TtsSpan.FractionBuilder  [API 21+]
```

## TtsSpan.MeasureBuilder — framework; API 21+ (11 +, 0 *, 0 ~, 0 -)

```
+ MeasureBuilder()  [API 21+]
+ setDenominator(String): TtsSpan.MeasureBuilder  [API 21+]
+ setDenominator(long): TtsSpan.MeasureBuilder  [API 21+]
+ setFractionalPart(String): TtsSpan.MeasureBuilder  [API 21+]
+ setIntegerPart(String): TtsSpan.MeasureBuilder  [API 21+]
+ setIntegerPart(long): TtsSpan.MeasureBuilder  [API 21+]
+ setNumber(String): TtsSpan.MeasureBuilder  [API 21+]
+ setNumber(long): TtsSpan.MeasureBuilder  [API 21+]
+ setNumerator(String): TtsSpan.MeasureBuilder  [API 21+]
+ setNumerator(long): TtsSpan.MeasureBuilder  [API 21+]
+ setUnit(String): TtsSpan.MeasureBuilder  [API 21+]
```

## TtsSpan.MoneyBuilder — framework; API 21+ (6 +, 0 *, 0 ~, 0 -)

```
+ MoneyBuilder()  [API 21+]
+ setCurrency(String): TtsSpan.MoneyBuilder  [API 21+]
+ setFractionalPart(String): TtsSpan.MoneyBuilder  [API 21+]
+ setIntegerPart(String): TtsSpan.MoneyBuilder  [API 21+]
+ setIntegerPart(long): TtsSpan.MoneyBuilder  [API 21+]
+ setQuantity(String): TtsSpan.MoneyBuilder  [API 21+]
```

## TtsSpan.OrdinalBuilder — framework; API 21+ (5 +, 0 *, 0 ~, 0 -)

```
+ OrdinalBuilder()  [API 21+]
+ OrdinalBuilder(String)  [API 21+]
+ OrdinalBuilder(long)  [API 21+]
+ setNumber(String): TtsSpan.OrdinalBuilder  [API 21+]
+ setNumber(long): TtsSpan.OrdinalBuilder  [API 21+]
```

## TtsSpan.SemioticClassBuilder — framework; API 21+ (5 +, 0 *, 0 ~, 0 -)

```
+ SemioticClassBuilder(String)  [API 21+]
+ setAnimacy(String): TtsSpan.SemioticClassBuilder  [API 21+]
+ setCase(String): TtsSpan.SemioticClassBuilder  [API 21+]
+ setGender(String): TtsSpan.SemioticClassBuilder  [API 21+]
+ setMultiplicity(String): TtsSpan.SemioticClassBuilder  [API 21+]
```

## TtsSpan.TelephoneBuilder — framework; API 21+ (5 +, 0 *, 0 ~, 0 -)

```
+ TelephoneBuilder()  [API 21+]
+ TelephoneBuilder(String)  [API 21+]
+ setCountryCode(String): TtsSpan.TelephoneBuilder  [API 21+]
+ setExtension(String): TtsSpan.TelephoneBuilder  [API 21+]
+ setNumberParts(String): TtsSpan.TelephoneBuilder  [API 21+]
```

## TtsSpan.TextBuilder — framework; API 21+ (3 +, 0 *, 0 ~, 0 -)

```
+ TextBuilder()  [API 21+]
+ TextBuilder(String)  [API 21+]
+ setText(String): TtsSpan.TextBuilder  [API 21+]
```

## TtsSpan.TimeBuilder — framework; API 21+ (4 +, 0 *, 0 ~, 0 -)

```
+ TimeBuilder()  [API 21+]
+ TimeBuilder(int, int)  [API 21+]
+ setHours(int): TtsSpan.TimeBuilder  [API 21+]
+ setMinutes(int): TtsSpan.TimeBuilder  [API 21+]
```

## TtsSpan.VerbatimBuilder — framework; API 21+ (3 +, 0 *, 0 ~, 0 -)

```
+ VerbatimBuilder()  [API 21+]
+ VerbatimBuilder(String)  [API 21+]
+ setVerbatim(String): TtsSpan.VerbatimBuilder  [API 21+]
```

## TypefaceSpan — framework; API 1+ (9 +, 0 *, 2 ~, 0 -)

```
+ TypefaceSpan(Parcel)  [API 3+]
+ TypefaceSpan(String)  [API 1+]
+ TypefaceSpan(Typeface)  [API 28+]
~ describeContents(): int  [API 1+]
+ getFamily(): String  [API 1+]
~ getSpanTypeId(): int  [API 1+]
+ getTypeface(): Typeface  [API 28+]
+ toString(): String  [API 1+]
+ updateDrawState(TextPaint): void  [API 1+]
+ updateMeasureState(TextPaint): void  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## URLSpan — framework; API 1+ (6 +, 0 *, 2 ~, 0 -)

```
+ URLSpan(Parcel)  [API 3+]
+ URLSpan(String)  [API 1+]
~ describeContents(): int  [API 1+]
~ getSpanTypeId(): int  [API 1+]
+ getURL(): String  [API 1+]
+ onClick(View): void  [API 1+]
+ toString(): String  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## UnderlineSpan — framework; API 1+ (4 +, 0 *, 3 ~, 0 -)

```
+ UnderlineSpan()  [API 1+]
+ UnderlineSpan(Parcel)  [API 3+]
~ describeContents(): int  [API 1+]
~ getSpanTypeId(): int  [API 1+]
+ toString(): String  [API 1+]
+ updateDrawState(TextPaint): void  [API 1+]
~ writeToParcel(Parcel, int): void  [API 1+]
```

## UpdateAppearance — framework; API 3+ (0 +, 0 *, 0 ~, 0 -)

## UpdateLayout — framework; API 1+ (0 +, 0 *, 0 ~, 0 -)

## WrapTogetherSpan — framework; API 1+ (0 +, 0 *, 0 ~, 0 -)

