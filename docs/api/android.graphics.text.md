# android.graphics.text

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## LineBreakConfig — framework; API 33+ (8 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 35+]
~ describeContents(): int  [API 33+]
+ equals(Object): boolean  [API 33+]
+ getHyphenation(): int  [API 35+]
+ getLineBreakStyle(): int  [API 33+]
+ getLineBreakWordStyle(): int  [API 33+]
+ hashCode(): int  [API 33+]
+ merge(LineBreakConfig): LineBreakConfig  [API 35+]
+ toString(): String  [API 33+]
+ writeToParcel(Parcel, int): void  [API 33+]
```

## LineBreakConfig.Builder — framework; API 33+ (6 +, 0 *, 0 ~, 0 -)

```
+ Builder()  [API 33+]
+ build(): LineBreakConfig  [API 33+]
+ merge(LineBreakConfig): LineBreakConfig.Builder  [API 35+]
+ setHyphenation(int): LineBreakConfig.Builder  [API 35+]
+ setLineBreakStyle(int): LineBreakConfig.Builder  [API 33+]
+ setLineBreakWordStyle(int): LineBreakConfig.Builder  [API 33+]
```

## LineBreaker — framework; API 29+ (1 +, 0 *, 0 ~, 0 -)

```
+ computeLineBreaks(MeasuredText, LineBreaker.ParagraphConstraints, int): LineBreaker.Result  [API 29+]
```

## LineBreaker.Builder — framework; API 29+ (7 +, 0 *, 0 ~, 0 -)

```
+ Builder()  [API 29+]
+ build(): LineBreaker  [API 29+]
+ setBreakStrategy(int): LineBreaker.Builder  [API 29+]
+ setHyphenationFrequency(int): LineBreaker.Builder  [API 29+]
+ setIndents(int[]): LineBreaker.Builder  [API 29+]
+ setJustificationMode(int): LineBreaker.Builder  [API 29+]
+ setUseBoundsForWidth(boolean): LineBreaker.Builder  [API 35+]
```

## LineBreaker.ParagraphConstraints — framework; API 29+ (9 +, 0 *, 0 ~, 0 -)

```
+ ParagraphConstraints()  [API 29+]
+ getDefaultTabStop(): float  [API 29+]
+ getFirstWidth(): float  [API 29+]
+ getFirstWidthLineCount(): int  [API 29+]
+ getTabStops(): float[]  [API 29+]
+ getWidth(): float  [API 29+]
+ setIndent(float, int): void  [API 29+]
+ setTabStops(float[], float): void  [API 29+]
+ setWidth(float): void  [API 29+]
```

## LineBreaker.Result — framework; API 29+ (6 +, 0 *, 2 ~, 0 -)

```
~ getEndLineHyphenEdit(int): int  [API 29+]
+ getLineAscent(int): float  [API 29+]
+ getLineBreakOffset(int): int  [API 29+]
+ getLineCount(): int  [API 29+]
+ getLineDescent(int): float  [API 29+]
+ getLineWidth(int): float  [API 29+]
~ getStartLineHyphenEdit(int): int  [API 29+]
+ hasLineTab(int): boolean  [API 29+]
```

## MeasuredText — framework; API 29+ (4 +, 0 *, 0 ~, 0 -)

```
+ getBounds(int, int, Rect): void  [API 29+]
+ getCharWidthAt(int): float  [API 29+]
+ getFontMetricsInt(int, int, Paint.FontMetricsInt): void  [API 33+]
+ getWidth(int, int): float  [API 29+]
```

## MeasuredText.Builder — framework; API 29+ (9 +, 0 *, 0 ~, 0 -)

```
+ Builder(MeasuredText)  [API 29+]
+ Builder(char[])  [API 29+]
+ appendReplacementRun(Paint, int, float): MeasuredText.Builder  [API 29+]
+ appendStyleRun(Paint, LineBreakConfig, int, boolean): MeasuredText.Builder  [API 33+]
+ appendStyleRun(Paint, int, boolean): MeasuredText.Builder  [API 29+]
+ build(): MeasuredText  [API 29+]
+ setComputeHyphenation(boolean): MeasuredText.Builder  [API 29+, deprecated in 33]
+ setComputeHyphenation(int): MeasuredText.Builder  [API 33+]
+ setComputeLayout(boolean): MeasuredText.Builder  [API 29+]
```

## PositionedGlyphs — declaration; API 31+ (0 +, 0 *, 0 ~, 17 -)

```
- equals(Object): boolean  [API 31+]
- getAdvance(): float  [API 31+]
- getAscent(): float  [API 31+]
- getDescent(): float  [API 31+]
- getFakeBold(int): boolean  [API 35+]
- getFakeItalic(int): boolean  [API 35+]
- getFont(int): Font  [API 31+]
- getGlyphId(int): int  [API 31+]
- getGlyphX(int): float  [API 31+]
- getGlyphY(int): float  [API 31+]
- getItalicOverride(int): float  [API 35+]
- getOffsetX(): float  [API 31+]
- getOffsetY(): float  [API 31+]
- getWeightOverride(int): float  [API 35+]
- glyphCount(): int  [API 31+]
- hashCode(): int  [API 31+]
- toString(): String  [API 31+]
```

## TextRunShaper — declaration; API 31+ (0 +, 0 *, 0 ~, 2 -)

```
- shapeTextRun(CharSequence, int, int, int, int, float, float, boolean, Paint): PositionedGlyphs [static]  [API 31+]
- shapeTextRun(char[], int, int, int, int, float, float, boolean, Paint): PositionedGlyphs [static]  [API 31+]
```

