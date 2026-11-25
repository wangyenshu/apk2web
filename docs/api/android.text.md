# android.text

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AlteredCharSequence — framework; API 1+, deprecated in 30 (6 +, 0 *, 0 ~, 0 -)

```
+ charAt(int): char  [API 1+]
+ getChars(int, int, char[], int): void  [API 1+]
+ length(): int  [API 1+]
+ make(CharSequence, char[], int, int): AlteredCharSequence [static]  [API 1+]
+ subSequence(int, int): CharSequence  [API 1+]
+ toString(): String  [API 1+]
```

## AndroidCharacter — framework; API 1+, deprecated in 26 (6 +, 0 *, 0 ~, 0 -)

```
+ AndroidCharacter()  [API 1+]
+ getDirectionalities(char[], byte[], int): void [static]  [API 1+]
+ getEastAsianWidth(char): int [static]  [API 8+]
+ getEastAsianWidths(char[], int, int, byte[]): void [static]  [API 8+]
+ getMirror(char): char [static]  [API 1+]
+ mirror(char[], int, int): boolean [static]  [API 1+]
```

## Annotation — framework; API 1+ (6 +, 0 *, 1 ~, 0 -)

```
+ Annotation(Parcel)  [API 3+]
+ Annotation(String, String)  [API 1+]
~ describeContents(): int  [API 1+]
+ getKey(): String  [API 1+]
+ getSpanTypeId(): int  [API 1+]
+ getValue(): String  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## AutoText — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ get(CharSequence, int, int, View): String [static]  [API 1+]
+ getSize(View): int [static]  [API 3+]
```

## BidiFormatter — framework; API 18+ (15 +, 0 *, 0 ~, 0 -)

```
+ getInstance(): BidiFormatter [static]  [API 18+]
+ getInstance(Locale): BidiFormatter [static]  [API 18+]
+ getInstance(boolean): BidiFormatter [static]  [API 18+]
+ getStereoReset(): boolean  [API 18+]
+ isRtl(CharSequence): boolean  [API 26+]
+ isRtl(String): boolean  [API 18+]
+ isRtlContext(): boolean  [API 18+]
+ unicodeWrap(CharSequence): CharSequence  [API 26+]
+ unicodeWrap(CharSequence, TextDirectionHeuristic): CharSequence  [API 26+]
+ unicodeWrap(CharSequence, TextDirectionHeuristic, boolean): CharSequence  [API 26+]
+ unicodeWrap(CharSequence, boolean): CharSequence  [API 26+]
+ unicodeWrap(String): String  [API 18+]
+ unicodeWrap(String, TextDirectionHeuristic): String  [API 18+]
+ unicodeWrap(String, TextDirectionHeuristic, boolean): String  [API 18+]
+ unicodeWrap(String, boolean): String  [API 18+]
```

## BidiFormatter.Builder — framework; API 18+ (6 +, 0 *, 0 ~, 0 -)

```
+ Builder()  [API 18+]
+ Builder(Locale)  [API 18+]
+ Builder(boolean)  [API 18+]
+ build(): BidiFormatter  [API 18+]
+ setTextDirectionHeuristic(TextDirectionHeuristic): BidiFormatter.Builder  [API 18+]
+ stereoReset(boolean): BidiFormatter.Builder  [API 18+]
```

## BoringLayout — framework; API 1+ (28 +, 0 *, 3 ~, 0 -)

```
+ BoringLayout(CharSequence, TextPaint, int, Layout.Alignment, float, float, BoringLayout.Metrics, boolean)  [API 1+]
+ BoringLayout(CharSequence, TextPaint, int, Layout.Alignment, float, float, BoringLayout.Metrics, boolean, TextUtils.TruncateAt, int)  [API 1+]
+ BoringLayout(CharSequence, TextPaint, int, Layout.Alignment, float, float, BoringLayout.Metrics, boolean, TextUtils.TruncateAt, int, boolean)  [API 33+]
+ computeDrawingBoundingBox(): RectF  [API 1+]
+ draw(Canvas, Path, Paint, int): void  [API 1+]
+ ellipsized(int, int): void  [API 1+]
+ getBottomPadding(): int  [API 1+]
+ getEllipsisCount(int): int  [API 1+]
+ getEllipsisStart(int): int  [API 1+]
+ getEllipsizedWidth(): int  [API 1+]
+ getHeight(): int  [API 1+]
~ getLineContainsTab(int): boolean  [API 1+]
~ getLineCount(): int  [API 1+]
+ getLineDescent(int): int  [API 1+]
+ getLineDirections(int): Layout.Directions  [API 1+]
+ getLineMax(int): float  [API 1+]
+ getLineStart(int): int  [API 1+]
+ getLineTop(int): int  [API 1+]
+ getLineWidth(int): float  [API 1+]
~ getParagraphDirection(int): int  [API 1+]
+ getTopPadding(): int  [API 1+]
+ isBoring(CharSequence, TextPaint): BoringLayout.Metrics [static]  [API 1+]
+ isBoring(CharSequence, TextPaint, BoringLayout.Metrics): BoringLayout.Metrics [static]  [API 1+]
+ isBoring(CharSequence, TextPaint, TextDirectionHeuristic, boolean, BoringLayout.Metrics): BoringLayout.Metrics [static]  [API 33+]
+ isFallbackLineSpacingEnabled(): boolean  [API 1+]
+ make(CharSequence, TextPaint, int, Layout.Alignment, BoringLayout.Metrics, boolean, TextUtils.TruncateAt, int, boolean): BoringLayout [static]  [API 33+]
+ make(CharSequence, TextPaint, int, Layout.Alignment, float, float, BoringLayout.Metrics, boolean): BoringLayout [static]  [API 1+]
+ make(CharSequence, TextPaint, int, Layout.Alignment, float, float, BoringLayout.Metrics, boolean, TextUtils.TruncateAt, int): BoringLayout [static]  [API 1+]
+ replaceOrMake(CharSequence, TextPaint, int, Layout.Alignment, BoringLayout.Metrics, boolean, TextUtils.TruncateAt, int, boolean): BoringLayout  [API 33+]
+ replaceOrMake(CharSequence, TextPaint, int, Layout.Alignment, float, float, BoringLayout.Metrics, boolean): BoringLayout  [API 1+]
+ replaceOrMake(CharSequence, TextPaint, int, Layout.Alignment, float, float, BoringLayout.Metrics, boolean, TextUtils.TruncateAt, int): BoringLayout  [API 1+]
```

## BoringLayout.Metrics — framework; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ Metrics()  [API 1+]
+ getDrawingBoundingBox(): RectF  [API 35+]
+ toString(): String  [API 1+]
+ width [field]  [API 1+]
```

## ClipboardManager — framework; API 1+, deprecated in 11 (4 +, 0 *, 0 ~, 0 -)

```
+ ClipboardManager()  [API 11+]
+ getText(): CharSequence  [API 1+]
+ hasText(): boolean  [API 1+]
+ setText(CharSequence): void  [API 1+]
```

## DynamicLayout — framework; API 1+ (16 +, 0 *, 0 ~, 0 -)

```
+ DynamicLayout(CharSequence, CharSequence, TextPaint, int, Layout.Alignment, float, float, boolean)  [API 1+, deprecated in 28]
+ DynamicLayout(CharSequence, CharSequence, TextPaint, int, Layout.Alignment, float, float, boolean, TextUtils.TruncateAt, int)  [API 1+, deprecated in 28]
+ DynamicLayout(CharSequence, TextPaint, int, Layout.Alignment, float, float, boolean)  [API 1+, deprecated in 28]
+ getBottomPadding(): int  [API 1+]
+ getEllipsisCount(int): int  [API 1+]
+ getEllipsisStart(int): int  [API 1+]
+ getEllipsizedWidth(): int  [API 1+]
+ getLineBreakConfig(): LineBreakConfig  [API 1+]
+ getLineContainsTab(int): boolean  [API 1+]
+ getLineCount(): int  [API 1+]
+ getLineDescent(int): int  [API 1+]
+ getLineDirections(int): Layout.Directions  [API 1+]
+ getLineStart(int): int  [API 1+]
+ getLineTop(int): int  [API 1+]
+ getParagraphDirection(int): int  [API 1+]
+ getTopPadding(): int  [API 1+]
```

## DynamicLayout.Builder — framework; API 28+ (17 +, 0 *, 0 ~, 0 -)

```
+ build(): DynamicLayout  [API 28+]
+ obtain(CharSequence, TextPaint, int): DynamicLayout.Builder [static]  [API 28+]
+ setAlignment(Layout.Alignment): DynamicLayout.Builder  [API 28+]
+ setBreakStrategy(int): DynamicLayout.Builder  [API 28+]
+ setDisplayText(CharSequence): DynamicLayout.Builder  [API 28+]
+ setEllipsize(TextUtils.TruncateAt): DynamicLayout.Builder  [API 28+]
+ setEllipsizedWidth(int): DynamicLayout.Builder  [API 28+]
+ setHyphenationFrequency(int): DynamicLayout.Builder  [API 28+]
+ setIncludePad(boolean): DynamicLayout.Builder  [API 28+]
+ setJustificationMode(int): DynamicLayout.Builder  [API 28+]
+ setLineBreakConfig(LineBreakConfig): DynamicLayout.Builder  [API 35+]
+ setLineSpacing(float, float): DynamicLayout.Builder  [API 28+]
+ setMinimumFontMetrics(Paint.FontMetrics): DynamicLayout.Builder  [API 35+]
+ setShiftDrawingOffsetForStartOverhang(boolean): DynamicLayout.Builder  [API 35+]
+ setTextDirection(TextDirectionHeuristic): DynamicLayout.Builder  [API 28+]
+ setUseBoundsForWidth(boolean): DynamicLayout.Builder  [API 35+]
+ setUseLineSpacingFromFallbacks(boolean): DynamicLayout.Builder  [API 28+]
```

## Editable — framework; API 1+ (12 +, 0 *, 0 ~, 0 -)

```
+ append(CharSequence): Editable  [API 1+]
+ append(CharSequence, int, int): Editable  [API 1+]
+ append(char): Editable  [API 1+]
+ clear(): void  [API 1+]
+ clearSpans(): void  [API 1+]
+ delete(int, int): Editable  [API 1+]
+ getFilters(): InputFilter[]  [API 1+]
+ insert(int, CharSequence): Editable  [API 1+]
+ insert(int, CharSequence, int, int): Editable  [API 1+]
+ replace(int, int, CharSequence): Editable  [API 1+]
+ replace(int, int, CharSequence, int, int): Editable  [API 1+]
+ setFilters(InputFilter[]): void  [API 1+]
```

## Editable.Factory — framework; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ Factory()  [API 1+]
+ getInstance(): Editable.Factory [static]  [API 1+]
+ newEditable(CharSequence): Editable  [API 1+]
```

## EmojiConsistency — framework; API 34+ (1 +, 0 *, 0 ~, 0 -)

```
+ getEmojiConsistencySet(): Set [static]  [API 34+]
```

## GetChars — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ getChars(int, int, char[], int): void  [API 1+]
```

## GraphemeClusterSegmentFinder — framework; API 34+ (5 +, 0 *, 0 ~, 0 -)

```
+ GraphemeClusterSegmentFinder(CharSequence, TextPaint)  [API 34+]
+ nextEndBoundary(int): int  [API 34+]
+ nextStartBoundary(int): int  [API 34+]
+ previousEndBoundary(int): int  [API 34+]
+ previousStartBoundary(int): int  [API 34+]
```

## Highlights — framework; API 34+ (3 +, 0 *, 0 ~, 0 -)

```
+ getPaint(int): Paint  [API 34+]
+ getRanges(int): int[]  [API 34+]
+ getSize(): int  [API 34+]
```

## Highlights.Builder — framework; API 34+ (4 +, 0 *, 0 ~, 0 -)

```
+ Builder()  [API 34+]
+ addRange(Paint, int, int): Highlights.Builder  [API 34+]
+ addRanges(Paint, int[]): Highlights.Builder  [API 34+]
+ build(): Highlights  [API 34+]
```

## Html — framework; API 1+ (7 +, 0 *, 0 ~, 0 -)

```
+ escapeHtml(CharSequence): String [static]  [API 16+]
+ fromHtml(String): Spanned [static]  [API 1+, deprecated in 24]
+ fromHtml(String, Html.ImageGetter, Html.TagHandler): Spanned [static]  [API 1+, deprecated in 24]
+ fromHtml(String, int): Spanned [static]  [API 24+]
+ fromHtml(String, int, Html.ImageGetter, Html.TagHandler): Spanned [static]  [API 24+]
+ toHtml(Spanned): String [static]  [API 1+, deprecated in 24]
+ toHtml(Spanned, int): String [static]  [API 24+]
```

## Html.ImageGetter — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ getDrawable(String): Drawable  [API 1+]
```

## Html.TagHandler — framework; API 1+ (0 +, 0 *, 0 ~, 1 -)

```
- handleTag(boolean, String, Editable, XMLReader): void  [API 1+]
```

## InputFilter — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ filter(CharSequence, int, int, Spanned, int, int): CharSequence  [API 1+]
```

## InputFilter.AllCaps — framework; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ AllCaps()  [API 1+]
+ AllCaps(Locale)  [API 27+]
+ filter(CharSequence, int, int, Spanned, int, int): CharSequence  [API 1+]
```

## InputFilter.LengthFilter — framework; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ LengthFilter(int)  [API 1+]
+ filter(CharSequence, int, int, Spanned, int, int): CharSequence  [API 1+]
+ getMax(): int  [API 21+]
```

## InputType — framework; API 3+ (0 +, 0 *, 0 ~, 0 -)

## Layout — framework; API 1+ (67 +, 0 *, 6 ~, 0 -)

```
+ INCLUSION_STRATEGY_ANY_OVERLAP [field]  [API 34+]
+ INCLUSION_STRATEGY_CONTAINS_ALL [field]  [API 34+]
+ INCLUSION_STRATEGY_CONTAINS_CENTER [field]  [API 34+]
+ Layout(CharSequence, TextPaint, int, Layout.Alignment, float, float)  [API 1+]
+ computeDrawingBoundingBox(): RectF  [API 35+]
+ draw(Canvas): void  [API 1+]
+ draw(Canvas, List, List, Path, Paint, int): void  [API 34+]
+ draw(Canvas, Path, Paint, int): void  [API 1+]
~ drawBackground(Canvas): void  [API 34+]
+ drawText(Canvas): void  [API 34+]
+ fillCharacterBounds(int, int, float[], int): void  [API 34+]
+ getAlignment(): Layout.Alignment  [API 1+]
~ getBottomPadding(): int  [API 1+]
+ getBreakStrategy(): int  [API 35+]
+ getCursorPath(int, Path, CharSequence): void  [API 1+]
+ getDesiredWidth(CharSequence, TextPaint): float [static]  [API 1+]
+ getDesiredWidth(CharSequence, int, int, TextPaint): float [static]  [API 1+]
~ getEllipsisCount(int): int  [API 1+]
~ getEllipsisStart(int): int  [API 1+]
+ getEllipsize(): TextUtils.TruncateAt  [API 35+]
+ getEllipsizedWidth(): int  [API 1+]
+ getHeight(): int  [API 1+]
+ getHyphenationFrequency(): int  [API 35+]
+ getJustificationMode(): int  [API 35+]
+ getLeftIndents(): int[]  [API 35+]
+ getLineAscent(int): int  [API 1+]
+ getLineBaseline(int): int  [API 1+]
+ getLineBottom(int): int  [API 1+]
+ getLineBottom(int, boolean): int  [API 34+]
+ getLineBounds(int, Rect): int  [API 1+]
+ getLineBreakConfig(): LineBreakConfig  [API 35+]
+ getLineContainsTab(int): boolean  [API 1+]
+ getLineCount(): int  [API 1+]
+ getLineDescent(int): int  [API 1+]
+ getLineDirections(int): Layout.Directions  [API 1+]
+ getLineEnd(int): int  [API 1+]
+ getLineForOffset(int): int  [API 1+]
+ getLineForVertical(int): int  [API 1+]
+ getLineLeft(int): float  [API 1+]
+ getLineLetterSpacingUnitCount(int, boolean): int  [API 35+]
+ getLineMax(int): float  [API 1+]
+ getLineRight(int): float  [API 1+]
+ getLineSpacingAmount(): float  [API 35+]
+ getLineSpacingMultiplier(): float  [API 35+]
+ getLineStart(int): int  [API 1+]
+ getLineTop(int): int  [API 1+]
+ getLineVisibleEnd(int): int  [API 1+]
+ getLineWidth(int): float  [API 1+]
+ getMaxLines(): int  [API 35+]
+ getMinimumFontMetrics(): Paint.FontMetrics  [API 35+]
+ getOffsetForHorizontal(int, float): int  [API 1+]
+ getOffsetToLeftOf(int): int  [API 1+]
+ getOffsetToRightOf(int): int  [API 1+]
+ getPaint(): TextPaint  [API 1+]
+ getParagraphAlignment(int): Layout.Alignment  [API 1+]
+ getParagraphDirection(int): int  [API 1+]
~ getParagraphLeft(int): int  [API 1+]
+ getParagraphRight(int): int  [API 1+]
+ getPrimaryHorizontal(int): float  [API 1+]
+ getRangeForRect(RectF, SegmentFinder, Layout.TextInclusionStrategy): int[]  [API 34+]
+ getRightIndents(): int[]  [API 35+]
+ getSecondaryHorizontal(int): float  [API 1+]
+ getSelectionPath(int, int, Path): void  [API 1+]
+ getShiftDrawingOffsetForStartOverhang(): boolean  [API 35+]
+ getSpacingAdd(): float  [API 1+]
+ getSpacingMultiplier(): float  [API 1+]
+ getText(): CharSequence  [API 1+]
+ getTextDirectionHeuristic(): TextDirectionHeuristic  [API 35+]
~ getTopPadding(): int  [API 1+]
+ getUseBoundsForWidth(): boolean  [API 35+]
+ getWidth(): int  [API 1+]
+ increaseWidthTo(int): void  [API 1+]
+ isFallbackLineSpacingEnabled(): boolean  [API 33+]
+ isFontPaddingIncluded(): boolean  [API 35+]
+ isRtlCharAt(int): boolean  [API 14+]
+ isSpanned(): boolean  [API 1+]
```

## Layout.Alignment — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ ALIGN_CENTER [field]  [API 1+]
+ ALIGN_NORMAL [field]  [API 1+]
+ ALIGN_OPPOSITE [field]  [API 1+]
+ valueOf(String): Layout.Alignment [static]  [API 1+]
+ values(): Layout.Alignment[] [static]  [API 1+]
```

## Layout.Builder — framework; API 35+ (20 +, 0 *, 0 ~, 0 -)

```
+ Builder(CharSequence, int, int, TextPaint, int)  [API 35+]
+ build(): Layout  [API 35+]
+ setAlignment(Layout.Alignment): Layout.Builder  [API 35+]
+ setBreakStrategy(int): Layout.Builder  [API 35+]
+ setEllipsize(TextUtils.TruncateAt): Layout.Builder  [API 35+]
+ setEllipsizedWidth(int): Layout.Builder  [API 35+]
+ setFallbackLineSpacingEnabled(boolean): Layout.Builder  [API 35+]
+ setFontPaddingIncluded(boolean): Layout.Builder  [API 35+]
+ setHyphenationFrequency(int): Layout.Builder  [API 35+]
+ setJustificationMode(int): Layout.Builder  [API 35+]
+ setLeftIndents(int[]): Layout.Builder  [API 35+]
+ setLineBreakConfig(LineBreakConfig): Layout.Builder  [API 35+]
+ setLineSpacingAmount(float): Layout.Builder  [API 35+]
+ setLineSpacingMultiplier(float): Layout.Builder  [API 35+]
+ setMaxLines(int): Layout.Builder  [API 35+]
+ setMinimumFontMetrics(Paint.FontMetrics): Layout.Builder  [API 35+]
+ setRightIndents(int[]): Layout.Builder  [API 35+]
+ setShiftDrawingOffsetForStartOverhang(boolean): Layout.Builder  [API 35+]
+ setTextDirectionHeuristic(TextDirectionHeuristic): Layout.Builder  [API 35+]
+ setUseBoundsForWidth(boolean): Layout.Builder  [API 35+]
```

## Layout.Directions — framework; API 1+ (0 +, 0 *, 0 ~, 0 -)

## Layout.TextInclusionStrategy — framework; API 34+ (1 +, 0 *, 0 ~, 0 -)

```
+ isSegmentInside(RectF, RectF): boolean  [API 34+]
```

## LoginFilter — framework; API 1+, deprecated in 30 (5 +, 0 *, 0 ~, 0 -)

```
+ filter(CharSequence, int, int, Spanned, int, int): CharSequence  [API 1+]
+ isAllowed(char): boolean  [API 1+]
+ onInvalidCharacter(char): void  [API 1+]
+ onStart(): void  [API 1+]
+ onStop(): void  [API 1+]
```

## LoginFilter.PasswordFilterGMail — framework; API 1+, deprecated in 30 (3 +, 0 *, 0 ~, 0 -)

```
+ PasswordFilterGMail()  [API 1+]
+ PasswordFilterGMail(boolean)  [API 1+]
+ isAllowed(char): boolean  [API 1+]
```

## LoginFilter.UsernameFilterGMail — framework; API 1+, deprecated in 30 (3 +, 0 *, 0 ~, 0 -)

```
+ UsernameFilterGMail()  [API 1+]
+ UsernameFilterGMail(boolean)  [API 1+]
+ isAllowed(char): boolean  [API 1+]
```

## LoginFilter.UsernameFilterGeneric — framework; API 1+, deprecated in 30 (3 +, 0 *, 0 ~, 0 -)

```
+ UsernameFilterGeneric()  [API 1+]
+ UsernameFilterGeneric(boolean)  [API 1+]
+ isAllowed(char): boolean  [API 1+]
```

## NoCopySpan — framework; API 3+ (0 +, 0 *, 0 ~, 0 -)

## NoCopySpan.Concrete — framework; API 3+ (1 +, 0 *, 0 ~, 0 -)

```
+ Concrete()  [API 3+]
```

## ParcelableSpan — framework; API 3+ (1 +, 0 *, 0 ~, 0 -)

```
+ getSpanTypeId(): int  [API 3+]
```

## PrecomputedText — framework; API 28+ (19 +, 0 *, 0 ~, 0 -)

```
+ charAt(int): char  [API 28+]
+ create(CharSequence, PrecomputedText.Params): PrecomputedText [static]  [API 28+]
+ getBounds(int, int, Rect): void  [API 28+]
+ getFontMetricsInt(int, int, Paint.FontMetricsInt): void  [API 33+]
+ getParagraphCount(): int  [API 28+]
+ getParagraphEnd(int): int  [API 28+]
+ getParagraphStart(int): int  [API 28+]
+ getParams(): PrecomputedText.Params  [API 28+]
+ getSpanEnd(Object): int  [API 28+]
+ getSpanFlags(Object): int  [API 28+]
+ getSpanStart(Object): int  [API 28+]
+ getSpans(int, int, Class): Object[]  [API 28+]
+ getWidth(int, int): float  [API 28+]
+ length(): int  [API 28+]
+ nextSpanTransition(int, int, Class): int  [API 28+]
+ removeSpan(Object): void  [API 28+]
+ setSpan(Object, int, int, int): void  [API 28+]
+ subSequence(int, int): CharSequence  [API 28+]
+ toString(): String  [API 28+]
```

## PrecomputedText.Params — framework; API 28+ (8 +, 0 *, 0 ~, 0 -)

```
+ equals(Object): boolean  [API 28+]
+ getBreakStrategy(): int  [API 28+]
+ getHyphenationFrequency(): int  [API 28+]
+ getLineBreakConfig(): LineBreakConfig  [API 33+]
+ getTextDirection(): TextDirectionHeuristic  [API 28+]
+ getTextPaint(): TextPaint  [API 28+]
+ hashCode(): int  [API 28+]
+ toString(): String  [API 28+]
```

## PrecomputedText.Params.Builder — framework; API 28+ (7 +, 0 *, 0 ~, 0 -)

```
+ Builder(PrecomputedText.Params)  [API 29+]
+ Builder(TextPaint)  [API 28+]
+ build(): PrecomputedText.Params  [API 28+]
+ setBreakStrategy(int): PrecomputedText.Params.Builder  [API 28+]
+ setHyphenationFrequency(int): PrecomputedText.Params.Builder  [API 28+]
+ setLineBreakConfig(LineBreakConfig): PrecomputedText.Params.Builder  [API 33+]
+ setTextDirection(TextDirectionHeuristic): PrecomputedText.Params.Builder  [API 28+]
```

## SegmentFinder — framework; API 34+ (5 +, 0 *, 0 ~, 0 -)

```
+ SegmentFinder()  [API 34+]
+ nextEndBoundary(int): int  [API 34+]
+ nextStartBoundary(int): int  [API 34+]
+ previousEndBoundary(int): int  [API 34+]
+ previousStartBoundary(int): int  [API 34+]
```

## SegmentFinder.PrescribedSegmentFinder — framework; API 34+ (5 +, 0 *, 0 ~, 0 -)

```
+ PrescribedSegmentFinder(int[])  [API 34+]
+ nextEndBoundary(int): int  [API 34+]
+ nextStartBoundary(int): int  [API 34+]
+ previousEndBoundary(int): int  [API 34+]
+ previousStartBoundary(int): int  [API 34+]
```

## Selection — framework; API 1+ (15 +, 0 *, 2 ~, 6 -)

```
+ SELECTION_END [field]  [API 1+]
+ SELECTION_START [field]  [API 1+]
- extendDown(Spannable, Layout): boolean [static]  [API 1+]
+ extendLeft(Spannable, Layout): boolean [static]  [API 1+]
+ extendRight(Spannable, Layout): boolean [static]  [API 1+]
+ extendSelection(Spannable, int): void [static]  [API 1+]
+ extendToLeftEdge(Spannable, Layout): boolean [static]  [API 1+]
- extendToParagraphEnd(Spannable): boolean [static]  [API 34+]
- extendToParagraphStart(Spannable): boolean [static]  [API 34+]
+ extendToRightEdge(Spannable, Layout): boolean [static]  [API 1+]
- extendUp(Spannable, Layout): boolean [static]  [API 1+]
+ getSelectionEnd(CharSequence): int [static]  [API 1+]
+ getSelectionStart(CharSequence): int [static]  [API 1+]
~ moveDown(Spannable, Layout): boolean [static]  [API 1+]
+ moveLeft(Spannable, Layout): boolean [static]  [API 1+]
+ moveRight(Spannable, Layout): boolean [static]  [API 1+]
+ moveToLeftEdge(Spannable, Layout): boolean [static]  [API 1+]
- moveToParagraphEnd(Spannable, Layout): boolean [static]  [API 34+]
- moveToParagraphStart(Spannable, Layout): boolean [static]  [API 34+]
+ moveToRightEdge(Spannable, Layout): boolean [static]  [API 1+]
~ moveUp(Spannable, Layout): boolean [static]  [API 1+]
+ removeSelection(Spannable): void [static]  [API 1+]
+ selectAll(Spannable): void [static]  [API 1+]
+ setSelection(Spannable, int): void [static]  [API 1+]
+ setSelection(Spannable, int, int): void [static]  [API 1+]
```

## SpanWatcher — framework; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ onSpanAdded(Spannable, Object, int, int): void  [API 1+]
+ onSpanChanged(Spannable, Object, int, int, int, int): void  [API 1+]
+ onSpanRemoved(Spannable, Object, int, int): void  [API 1+]
```

## Spannable — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ removeSpan(Object): void  [API 1+]
+ setSpan(Object, int, int, int): void  [API 1+]
```

## Spannable.Factory — framework; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ Factory()  [API 1+]
+ getInstance(): Spannable.Factory [static]  [API 1+]
+ newSpannable(CharSequence): Spannable  [API 1+]
```

## SpannableString — framework; API 1+ (16 +, 0 *, 0 ~, 0 -)

```
+ SpannableString(CharSequence)  [API 1+]
+ charAt(int): char  [API 1+]
+ equals(Object): boolean  [API 1+]
+ getChars(int, int, char[], int): void  [API 1+]
+ getSpanEnd(Object): int  [API 1+]
+ getSpanFlags(Object): int  [API 1+]
+ getSpanStart(Object): int  [API 1+]
+ getSpans(int, int, Class): Object[]  [API 1+]
+ hashCode(): int  [API 1+]
+ length(): int  [API 1+]
+ nextSpanTransition(int, int, Class): int  [API 1+]
+ removeSpan(Object): void  [API 1+]
+ setSpan(Object, int, int, int): void  [API 1+]
+ subSequence(int, int): CharSequence  [API 1+]
+ toString(): String  [API 1+]
+ valueOf(CharSequence): SpannableString [static]  [API 1+]
```

## SpannableStringBuilder — framework; API 1+ (32 +, 0 *, 0 ~, 1 -)

```
+ SpannableStringBuilder()  [API 1+]
+ SpannableStringBuilder(CharSequence)  [API 1+]
+ SpannableStringBuilder(CharSequence, int, int)  [API 1+]
+ append(CharSequence): SpannableStringBuilder  [API 1+]
+ append(CharSequence, Object, int): SpannableStringBuilder  [API 21+]
+ append(CharSequence, int, int): SpannableStringBuilder  [API 1+]
+ append(char): SpannableStringBuilder  [API 1+]
+ charAt(int): char  [API 1+]
+ clear(): void  [API 1+]
+ clearSpans(): void  [API 1+]
+ delete(int, int): SpannableStringBuilder  [API 1+]
+ equals(Object): boolean  [API 1+]
+ getChars(int, int, char[], int): void  [API 1+]
+ getFilters(): InputFilter[]  [API 1+]
+ getSpanEnd(Object): int  [API 1+]
+ getSpanFlags(Object): int  [API 1+]
+ getSpanStart(Object): int  [API 1+]
+ getSpans(int, int, Class): Object[]  [API 1+]
+ getTextRunCursor(int, int, int, int, int, Paint): int  [API 11+, deprecated in 15]
- getTextWatcherDepth(): int  [API 23+]
+ hashCode(): int  [API 1+]
+ insert(int, CharSequence): SpannableStringBuilder  [API 1+]
+ insert(int, CharSequence, int, int): SpannableStringBuilder  [API 1+]
+ length(): int  [API 1+]
+ nextSpanTransition(int, int, Class): int  [API 1+]
+ removeSpan(Object): void  [API 1+]
+ replace(int, int, CharSequence): SpannableStringBuilder  [API 1+]
+ replace(int, int, CharSequence, int, int): SpannableStringBuilder  [API 1+]
+ setFilters(InputFilter[]): void  [API 1+]
+ setSpan(Object, int, int, int): void  [API 1+]
+ subSequence(int, int): CharSequence  [API 1+]
+ toString(): String  [API 1+]
+ valueOf(CharSequence): SpannableStringBuilder [static]  [API 1+]
```

## Spanned — framework; API 1+ (5 +, 0 *, 0 ~, 0 -)

```
+ getSpanEnd(Object): int  [API 1+]
+ getSpanFlags(Object): int  [API 1+]
+ getSpanStart(Object): int  [API 1+]
+ getSpans(int, int, Class): Object[]  [API 1+]
+ nextSpanTransition(int, int, Class): int  [API 1+]
```

## SpannedString — framework; API 1+ (14 +, 0 *, 0 ~, 0 -)

```
+ SpannedString(CharSequence)  [API 1+]
+ charAt(int): char  [API 1+]
+ equals(Object): boolean  [API 1+]
+ getChars(int, int, char[], int): void  [API 1+]
+ getSpanEnd(Object): int  [API 1+]
+ getSpanFlags(Object): int  [API 1+]
+ getSpanStart(Object): int  [API 1+]
+ getSpans(int, int, Class): Object[]  [API 1+]
+ hashCode(): int  [API 1+]
+ length(): int  [API 1+]
+ nextSpanTransition(int, int, Class): int  [API 1+]
+ subSequence(int, int): CharSequence  [API 1+]
+ toString(): String  [API 1+]
+ valueOf(CharSequence): SpannedString [static]  [API 1+]
```

## StaticLayout — framework; API 1+ (16 +, 0 *, 0 ~, 0 -)

```
+ StaticLayout(CharSequence, TextPaint, int, Layout.Alignment, float, float, boolean)  [API 1+, deprecated in 28]
+ StaticLayout(CharSequence, int, int, TextPaint, int, Layout.Alignment, float, float, boolean)  [API 1+, deprecated in 28]
+ StaticLayout(CharSequence, int, int, TextPaint, int, Layout.Alignment, float, float, boolean, TextUtils.TruncateAt, int)  [API 1+, deprecated in 28]
+ computeDrawingBoundingBox(): RectF  [API 1+]
+ getBottomPadding(): int  [API 1+]
+ getEllipsisCount(int): int  [API 1+]
+ getEllipsisStart(int): int  [API 1+]
+ getLineContainsTab(int): boolean  [API 1+]
+ getLineCount(): int  [API 1+]
+ getLineDescent(int): int  [API 1+]
+ getLineDirections(int): Layout.Directions  [API 1+]
+ getLineForVertical(int): int  [API 1+]
+ getLineStart(int): int  [API 1+]
+ getLineTop(int): int  [API 1+]
+ getParagraphDirection(int): int  [API 1+]
+ getTopPadding(): int  [API 1+]
```

## StaticLayout.Builder — framework; API 23+ (19 +, 0 *, 0 ~, 0 -)

```
+ build(): StaticLayout  [API 23+]
+ obtain(CharSequence, int, int, TextPaint, int): StaticLayout.Builder [static]  [API 23+]
+ setAlignment(Layout.Alignment): StaticLayout.Builder  [API 23+]
+ setBreakStrategy(int): StaticLayout.Builder  [API 23+]
+ setEllipsize(TextUtils.TruncateAt): StaticLayout.Builder  [API 23+]
+ setEllipsizedWidth(int): StaticLayout.Builder  [API 23+]
+ setHyphenationFrequency(int): StaticLayout.Builder  [API 23+]
+ setIncludePad(boolean): StaticLayout.Builder  [API 23+]
+ setIndents(int[], int[]): StaticLayout.Builder  [API 23+]
+ setJustificationMode(int): StaticLayout.Builder  [API 26+]
+ setLineBreakConfig(LineBreakConfig): StaticLayout.Builder  [API 33+]
+ setLineSpacing(float, float): StaticLayout.Builder  [API 23+]
+ setMaxLines(int): StaticLayout.Builder  [API 23+]
+ setMinimumFontMetrics(Paint.FontMetrics): StaticLayout.Builder  [API 35+]
+ setShiftDrawingOffsetForStartOverhang(boolean): StaticLayout.Builder  [API 35+]
+ setText(CharSequence): StaticLayout.Builder  [API 23+]
+ setTextDirection(TextDirectionHeuristic): StaticLayout.Builder  [API 23+]
+ setUseBoundsForWidth(boolean): StaticLayout.Builder  [API 35+]
+ setUseLineSpacingFromFallbacks(boolean): StaticLayout.Builder  [API 28+]
```

## TextDirectionHeuristic — declaration; API 18+ (2 +, 0 *, 0 ~, 0 -)

```
+ isRtl(CharSequence, int, int): boolean  [API 18+]
+ isRtl(char[], int, int): boolean  [API 18+]
```

## TextDirectionHeuristics — framework; API 18+ (1 +, 0 *, 0 ~, 0 -)

```
+ ANYRTL_LTR [field]  [API 18+]
+ FIRSTSTRONG_LTR [field]  [API 18+]
+ FIRSTSTRONG_RTL [field]  [API 18+]
+ LOCALE [field]  [API 18+]
+ LTR [field]  [API 18+]
+ RTL [field]  [API 18+]
+ TextDirectionHeuristics()  [API 18+]
```

## TextPaint — framework; API 1+ (5 +, 0 *, 0 ~, 0 -)

```
+ TextPaint()  [API 1+]
+ TextPaint(Paint)  [API 1+]
+ TextPaint(int)  [API 1+]
+ baselineShift [field]  [API 1+]
+ bgColor [field]  [API 1+]
+ density [field]  [API 5+]
+ drawableState [field]  [API 1+]
+ getUnderlineThickness(): float  [API 1+]
+ linkColor [field]  [API 1+]
+ set(TextPaint): void  [API 1+]
+ underlineColor [field]  [API 29+]
+ underlineThickness [field]  [API 29+]
```

## TextShaper — declaration; API 31+ (0 +, 0 *, 0 ~, 1 -)

```
- shapeText(CharSequence, int, int, TextDirectionHeuristic, TextPaint, TextShaper.GlyphsConsumer): void [static]  [API 31+]
```

## TextShaper.GlyphsConsumer — declaration; API 31+ (1 +, 0 *, 0 ~, 0 -)

```
+ accept(int, int, PositionedGlyphs, TextPaint): void  [API 31+]
```

## TextUtils — framework; API 1+ (38 +, 0 *, 0 ~, 2 -)

```
+ CHAR_SEQUENCE_CREATOR [field]  [API 1+]
+ commaEllipsize(CharSequence, TextPaint, float, String, String): CharSequence [static]  [API 1+, deprecated in 26]
+ concat(CharSequence[]): CharSequence [static]  [API 1+]
+ copySpansFrom(Spanned, int, int, Class, Spannable, int): void [static]  [API 1+]
+ dumpSpans(CharSequence, Printer, String): void [static]  [API 3+]
+ ellipsize(CharSequence, TextPaint, float, TextUtils.TruncateAt): CharSequence [static]  [API 1+]
+ ellipsize(CharSequence, TextPaint, float, TextUtils.TruncateAt, boolean, TextUtils.EllipsizeCallback): CharSequence [static]  [API 1+]
+ equals(CharSequence, CharSequence): boolean [static]  [API 1+]
+ expandTemplate(CharSequence, CharSequence[]): CharSequence [static]  [API 1+]
+ getCapsMode(CharSequence, int, int): int [static]  [API 3+]
+ getChars(CharSequence, int, int, char[], int): void [static]  [API 1+]
+ getLayoutDirectionFromLocale(Locale): int [static]  [API 17+]
+ getOffsetAfter(CharSequence, int): int [static]  [API 1+]
+ getOffsetBefore(CharSequence, int): int [static]  [API 1+]
+ getReverse(CharSequence, int, int): CharSequence [static]  [API 1+, deprecated in 24]
+ getTrimmedLength(CharSequence): int [static]  [API 1+]
+ htmlEncode(String): String [static]  [API 1+]
+ indexOf(CharSequence, CharSequence): int [static]  [API 1+]
+ indexOf(CharSequence, CharSequence, int): int [static]  [API 1+]
- indexOf(CharSequence, CharSequence, int, int): int [static]  [API 1+]
+ indexOf(CharSequence, char): int [static]  [API 1+]
+ indexOf(CharSequence, char, int): int [static]  [API 1+]
+ indexOf(CharSequence, char, int, int): int [static]  [API 1+]
+ isDigitsOnly(CharSequence): boolean [static]  [API 1+]
+ isEmpty(CharSequence): boolean [static]  [API 1+]
+ isGraphic(CharSequence): boolean [static]  [API 1+]
+ isGraphic(char): boolean [static]  [API 1+, deprecated in 24]
+ join(CharSequence, Iterable): String [static]  [API 1+]
+ join(CharSequence, Object[]): String [static]  [API 1+]
+ lastIndexOf(CharSequence, char): int [static]  [API 1+]
+ lastIndexOf(CharSequence, char, int): int [static]  [API 1+]
- lastIndexOf(CharSequence, char, int, int): int [static]  [API 1+]
+ listEllipsize(Context, List, String, TextPaint, float, int): CharSequence [static]  [API 26+]
+ makeSafeForPresentation(String, int, float, int): CharSequence [static]  [API 29+]
+ regionMatches(CharSequence, int, CharSequence, int, int): boolean [static]  [API 1+]
+ replace(CharSequence, String[], CharSequence[]): CharSequence [static]  [API 1+]
+ split(String, Pattern): String[] [static]  [API 1+]
+ split(String, String): String[] [static]  [API 1+]
+ stringOrSpannedString(CharSequence): CharSequence [static]  [API 1+]
+ substring(CharSequence, int, int): String [static]  [API 1+]
+ writeToParcel(CharSequence, Parcel, int): void [static]  [API 1+]
```

## TextUtils.EllipsizeCallback — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ ellipsized(int, int): void  [API 1+]
```

## TextUtils.SimpleStringSplitter — framework; API 1+ (4 +, 0 *, 2 ~, 0 -)

```
+ SimpleStringSplitter(char)  [API 1+]
+ hasNext(): boolean  [API 1+]
~ iterator(): Iterator  [API 1+]
+ next(): String  [API 1+]
~ remove(): void  [API 1+]
+ setString(String): void  [API 1+]
```

## TextUtils.StringSplitter — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ setString(String): void  [API 1+]
```

## TextUtils.TruncateAt — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ END [field]  [API 1+]
+ MARQUEE [field]  [API 2+]
+ MIDDLE [field]  [API 1+]
+ START [field]  [API 1+]
+ valueOf(String): TextUtils.TruncateAt [static]  [API 1+]
+ values(): TextUtils.TruncateAt[] [static]  [API 1+]
```

## TextWatcher — framework; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ afterTextChanged(Editable): void  [API 1+]
+ beforeTextChanged(CharSequence, int, int, int): void  [API 1+]
+ onTextChanged(CharSequence, int, int, int): void  [API 1+]
```

## WordSegmentFinder — framework; API 34+ (5 +, 0 *, 0 ~, 0 -)

```
+ WordSegmentFinder(CharSequence, ULocale)  [API 34+]
+ nextEndBoundary(int): int  [API 34+]
+ nextStartBoundary(int): int  [API 34+]
+ previousEndBoundary(int): int  [API 34+]
+ previousStartBoundary(int): int  [API 34+]
```

