# android.graphics.fonts

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## Font — declaration; API 29+ (0 +, 10 *, 0 ~, 2 -)

```
* equals(Object): boolean  [API 29+]
* getAxes(): FontVariationAxis[]  [API 29+]
* getBuffer(): ByteBuffer  [API 29+]
* getFile(): File  [API 29+]
- getGlyphBounds(int, Paint, RectF): float  [API 31+]
* getLocaleList(): LocaleList  [API 29+]
- getMetrics(Paint, Paint.FontMetrics): void  [API 31+]
* getSourceIdentifier(): int  [API 31+]
* getStyle(): FontStyle  [API 29+]
* getTtcIndex(): int  [API 29+]
* hashCode(): int  [API 29+]
* toString(): String  [API 29+]
```

## Font.Builder — declaration; API 29+ (0 +, 4 *, 0 ~, 9 -)

```
- Builder(AssetManager, String)  [API 29+]
* Builder(ByteBuffer)  [API 29+]
- Builder(File)  [API 29+]
* Builder(Font)  [API 31+]
- Builder(ParcelFileDescriptor)  [API 29+]
- Builder(ParcelFileDescriptor, long, long)  [API 29+]
- Builder(Resources, int)  [API 29+]
* build(): Font  [API 29+]
- setFontVariationSettings(FontVariationAxis[]): Font.Builder  [API 29+]
- setFontVariationSettings(String): Font.Builder  [API 29+]
- setSlant(int): Font.Builder  [API 29+]
* setTtcIndex(int): Font.Builder  [API 29+]
- setWeight(int): Font.Builder  [API 29+]
```

## FontFamily — declaration; API 29+ (0 +, 0 *, 0 ~, 2 -)

```
- getFont(int): Font  [API 29+]
- getSize(): int  [API 29+]
```

## FontFamily.Builder — declaration; API 29+ (0 +, 0 *, 0 ~, 4 -)

```
- Builder(Font)  [API 29+]
- addFont(Font): FontFamily.Builder  [API 29+]
- build(): FontFamily  [API 29+]
- buildVariableFamily(): FontFamily  [API 35+]
```

## FontStyle — declaration; API 29+ (0 +, 6 *, 0 ~, 1 -)

```
- FontStyle()  [API 29+]
* FontStyle(int, int)  [API 29+]
* equals(Object): boolean  [API 29+]
* getSlant(): int  [API 29+]
* getWeight(): int  [API 29+]
* hashCode(): int  [API 29+]
* toString(): String  [API 29+]
```

## FontVariationAxis — declaration; API 26+ (0 +, 0 *, 0 ~, 8 -)

```
- FontVariationAxis(String, float)  [API 26+]
- equals(Object): boolean  [API 26+]
- fromFontVariationSettings(String): FontVariationAxis[] [static]  [API 26+]
- getStyleValue(): float  [API 26+]
- getTag(): String  [API 26+]
- hashCode(): int  [API 26+]
- toFontVariationSettings(FontVariationAxis[]): String [static]  [API 26+]
- toString(): String  [API 26+]
```

## SystemFonts — declaration; API 29+ (0 +, 0 *, 0 ~, 1 -)

```
- getAvailableFonts(): Set [static]  [API 29+]
```

