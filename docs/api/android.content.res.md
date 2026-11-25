# android.content.res

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AssetFileDescriptor — framework; API 1+ (9 +, 0 *, 3 ~, 2 -)

```
+ AssetFileDescriptor(ParcelFileDescriptor, long, long)  [API 1+]
+ AssetFileDescriptor(ParcelFileDescriptor, long, long, Bundle)  [API 19+]
- CREATOR [field]  [API 3+]
~ close(): void  [API 1+]
+ createInputStream(): FileInputStream  [API 3+]
+ createOutputStream(): FileOutputStream  [API 3+]
- describeContents(): int  [API 1+]
+ getDeclaredLength(): long  [API 3+]
~ getExtras(): Bundle  [API 19+]
+ getFileDescriptor(): FileDescriptor  [API 1+]
+ getLength(): long  [API 1+]
+ getParcelFileDescriptor(): ParcelFileDescriptor  [API 1+]
~ getStartOffset(): long  [API 1+]
+ toString(): String  [API 1+]
- writeToParcel(Parcel, int): void  [API 1+]
```

## AssetFileDescriptor.AutoCloseInputStream — missing; API 3+ (0 +, 0 *, 0 ~, 11 -)

```
- AutoCloseInputStream(AssetFileDescriptor)  [API 3+]
- available(): int  [API 3+]
- close(): void  [API 3+]
- getChannel(): FileChannel  [API 3+]
- mark(int): void  [API 3+]
- markSupported(): boolean  [API 3+]
- read(): int  [API 3+]
- read(byte[]): int  [API 3+]
- read(byte[], int, int): int  [API 3+]
- reset(): void  [API 3+]
- skip(long): long  [API 3+]
```

## AssetFileDescriptor.AutoCloseOutputStream — declaration; API 3+ (0 +, 0 *, 0 ~, 4 -)

```
- AutoCloseOutputStream(AssetFileDescriptor)  [API 3+]
- write(byte[]): void  [API 3+]
- write(byte[], int, int): void  [API 3+]
- write(int): void  [API 3+]
```

## AssetManager — framework; API 1+ (8 +, 0 *, 1 ~, 2 -)

```
~ close(): void  [API 1+]
+ finalize(): void  [API 1+]
+ getLocales(): String[]  [API 1+]
+ list(String): String[]  [API 1+]
+ open(String): InputStream  [API 1+]
+ open(String, int): InputStream  [API 1+]
+ openFd(String): AssetFileDescriptor  [API 1+]
+ openNonAssetFd(String): AssetFileDescriptor  [API 1+]
+ openNonAssetFd(int, String): AssetFileDescriptor  [API 1+]
- openXmlResourceParser(String): XmlResourceParser  [API 1+]
- openXmlResourceParser(int, String): XmlResourceParser  [API 1+]
```

## AssetManager.AssetInputStream — declaration; API 1+ (0 +, 0 *, 0 ~, 11 -)

```
- available(): int  [API 1+]
- close(): void  [API 1+]
- finalize(): void  [API 1+]
- getAssetInt(): int  [API 1-20, removed in 21]
- mark(int): void  [API 1+]
- markSupported(): boolean  [API 1+]
- read(): int  [API 1+]
- read(byte[]): int  [API 1+]
- read(byte[], int, int): int  [API 1+]
- reset(): void  [API 1+]
- skip(long): long  [API 1+]
```

## ColorStateList — framework; API 1+ (13 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 1+]
+ ColorStateList(int[][], int[])  [API 1+]
+ createFromXml(Resources, XmlPullParser): ColorStateList [static]  [API 1+, deprecated in 23]
+ createFromXml(Resources, XmlPullParser, Resources.Theme): ColorStateList [static]  [API 23+]
~ describeContents(): int  [API 1+]
+ getChangingConfigurations(): int  [API 23+]
+ getColorForState(int[], int): int  [API 1+]
+ getDefaultColor(): int  [API 1+]
+ isOpaque(): boolean  [API 21+]
+ isStateful(): boolean  [API 1+]
+ toString(): String  [API 1+]
+ valueOf(int): ColorStateList [static]  [API 1+]
+ withAlpha(int): ColorStateList  [API 1+]
+ withLStar(float): ColorStateList  [API 31+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## Configuration — framework; API 1+ (26 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 1+]
+ Configuration()  [API 1+]
+ Configuration(Configuration)  [API 1+]
+ colorMode [field]  [API 26+]
+ compareTo(Configuration): int  [API 1+]
+ densityDpi [field]  [API 17+]
~ describeContents(): int  [API 1+]
+ diff(Configuration): int  [API 1+]
+ equals(Configuration): boolean  [API 1+]
+ equals(Object): boolean  [API 1+]
+ fontScale [field]  [API 1+]
+ fontWeightAdjustment [field]  [API 31+]
+ generateDelta(Configuration, Configuration): Configuration [static]  [API 33+]
+ getGrammaticalGender(): int  [API 34+]
+ getLayoutDirection(): int  [API 17+]
+ getLocales(): LocaleList  [API 24+]
+ hardKeyboardHidden [field]  [API 3+]
+ hashCode(): int  [API 1+]
+ isLayoutSizeAtLeast(int): boolean  [API 11+]
+ isNightModeActive(): boolean  [API 30+]
+ isScreenHdr(): boolean  [API 26+]
+ isScreenRound(): boolean  [API 23+]
+ isScreenWideColorGamut(): boolean  [API 26+]
+ keyboard [field]  [API 1+]
+ keyboardHidden [field]  [API 1+]
+ locale [field]  [API 1+, deprecated in 24]
+ mcc [field]  [API 1+]
+ mnc [field]  [API 1+]
+ navigation [field]  [API 1+]
+ navigationHidden [field]  [API 5+]
+ needNewResources(int, int): boolean [static]  [API 1+]
+ orientation [field]  [API 1+]
+ readFromParcel(Parcel): void  [API 8+]
+ screenHeightDp [field]  [API 13+]
+ screenLayout [field]  [API 4+]
+ screenWidthDp [field]  [API 13+]
+ setLayoutDirection(Locale): void  [API 17+]
+ setLocale(Locale): void  [API 17+]
+ setLocales(LocaleList): void  [API 24+]
+ setTo(Configuration): void  [API 8+]
+ setToDefaults(): void  [API 1+]
+ smallestScreenWidthDp [field]  [API 13+]
+ toString(): String  [API 1+]
+ touchscreen [field]  [API 1+]
+ uiMode [field]  [API 8+]
+ updateFrom(Configuration): int  [API 1+]
+ writeToParcel(Parcel, int): void  [API 1+]
```

## FontScaleConverter — declaration; API 35+ (2 +, 0 *, 0 ~, 2 -)

```
+ convertDpToSp(float): float  [API 35+]
+ convertSpToDp(float): float  [API 35+]
- forScale(float): FontScaleConverter [static]  [API 35+]
- isNonLinearFontScalingActive(float): boolean [static]  [API 35+]
```

## ObbInfo — declaration; API 9+ (0 +, 0 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 9+]
- describeContents(): int  [API 9+]
+ filename [field]  [API 9+]
+ flags [field]  [API 9+]
+ packageName [field]  [API 9+]
- toString(): String  [API 9+]
+ version [field]  [API 9+]
- writeToParcel(Parcel, int): void  [API 9+]
```

## ObbScanner — declaration; API 9+ (0 +, 0 *, 0 ~, 1 -)

```
- getObbInfo(String): ObbInfo [static]  [API 9+]
```

## Resources — framework; API 1+ (49 +, 0 *, 4 ~, 5 -)

```
+ Resources(AssetManager, DisplayMetrics, Configuration)  [API 1+, deprecated in 25]
- addLoaders(ResourcesLoader[]): void  [API 30+]
~ finishPreloading(): void  [API 1+]
~ flushLayoutCache(): void  [API 1+]
+ getAnimation(int): XmlResourceParser  [API 1+]
+ getAssets(): AssetManager  [API 1+]
- getAttributeSetSourceResId(AttributeSet): int [static]  [API 29+]
+ getBoolean(int): boolean  [API 3+]
+ getColor(int): int  [API 1+, deprecated in 23]
+ getColor(int, Resources.Theme): int  [API 23+]
+ getColorStateList(int): ColorStateList  [API 1+, deprecated in 23]
+ getColorStateList(int, Resources.Theme): ColorStateList  [API 23+]
+ getConfiguration(): Configuration  [API 1+]
+ getDimension(int): float  [API 1+]
+ getDimensionPixelOffset(int): int  [API 1+]
+ getDimensionPixelSize(int): int  [API 1+]
+ getDisplayMetrics(): DisplayMetrics  [API 1+]
+ getDrawable(int): Drawable  [API 1+, deprecated in 22]
+ getDrawable(int, Resources.Theme): Drawable  [API 21+]
+ getDrawableForDensity(int, int): Drawable  [API 15+, deprecated in 22]
+ getDrawableForDensity(int, int, Resources.Theme): Drawable  [API 21+]
+ getFloat(int): float  [API 29+]
+ getFont(int): Typeface  [API 26+]
~ getFraction(int, int, int): float  [API 3+]
+ getIdentifier(String, String, String): int  [API 1+]
+ getIntArray(int): int[]  [API 1+]
+ getInteger(int): int  [API 1+]
+ getLayout(int): XmlResourceParser  [API 1+]
~ getMovie(int): Movie  [API 1+, deprecated in 29]
+ getQuantityString(int, int): String  [API 1+]
+ getQuantityString(int, int, Object[]): String  [API 1+]
+ getQuantityText(int, int): CharSequence  [API 1+]
+ getResourceEntryName(int): String  [API 1+]
+ getResourceName(int): String  [API 1+]
+ getResourcePackageName(int): String  [API 1+]
+ getResourceTypeName(int): String  [API 1+]
+ getString(int): String  [API 1+]
+ getString(int, Object[]): String  [API 1+]
+ getStringArray(int): String[]  [API 1+]
+ getSystem(): Resources [static]  [API 1+]
+ getText(int): CharSequence  [API 1+]
+ getText(int, CharSequence): CharSequence  [API 1+]
+ getTextArray(int): CharSequence[]  [API 1+]
+ getValue(String, TypedValue, boolean): void  [API 1+]
+ getValue(int, TypedValue, boolean): void  [API 1+]
- getValueForDensity(int, int, TypedValue, boolean): void  [API 15+]
+ getXml(int): XmlResourceParser  [API 1+]
+ newTheme(): Resources.Theme  [API 1+]
+ obtainAttributes(AttributeSet, int[]): TypedArray  [API 1+]
+ obtainTypedArray(int): TypedArray  [API 1+]
+ openRawResource(int): InputStream  [API 1+]
+ openRawResource(int, TypedValue): InputStream  [API 3+]
+ openRawResourceFd(int): AssetFileDescriptor  [API 1+]
+ parseBundleExtra(String, AttributeSet, Bundle): void  [API 3+]
+ parseBundleExtras(XmlResourceParser, Bundle): void  [API 3+]
- registerResourcePaths(String, ApplicationInfo): void [static]  [API 35+]
- removeLoaders(ResourcesLoader[]): void  [API 30+]
+ updateConfiguration(Configuration, DisplayMetrics): void  [API 1+, deprecated in 25]
```

## Resources.NotFoundException — framework; API 1+ (2 +, 0 *, 0 ~, 1 -)

```
+ NotFoundException()  [API 1+]
+ NotFoundException(String)  [API 1+]
- NotFoundException(String, Exception)  [API 24+]
```

## Resources.Theme — framework; API 1+ (13 +, 0 *, 3 ~, 0 -)

```
+ applyStyle(int, boolean): void  [API 1+]
~ dump(int, String, String): void  [API 1+]
+ equals(Object): boolean  [API 1+]
+ getAttributeResolutionStack(int, int, int): int[]  [API 29+]
~ getChangingConfigurations(): int  [API 23+]
+ getDrawable(int): Drawable  [API 21+]
+ getExplicitStyle(AttributeSet): int  [API 29+]
+ getResources(): Resources  [API 21+]
+ hashCode(): int  [API 1+]
+ obtainStyledAttributes(AttributeSet, int[], int, int): TypedArray  [API 1+]
+ obtainStyledAttributes(int, int[]): TypedArray  [API 1+]
+ obtainStyledAttributes(int[]): TypedArray  [API 1+]
~ rebase(): void  [API 29+]
+ resolveAttribute(int, TypedValue, boolean): boolean  [API 1+]
+ setTo(Resources.Theme): void  [API 1+]
+ toString(): String  [API 1+]
```

## TypedArray — framework; API 1+ (31 +, 0 *, 3 ~, 0 -)

```
+ close(): void  [API 1+]
+ getBoolean(int, boolean): boolean  [API 1+]
~ getChangingConfigurations(): int  [API 21+]
+ getColor(int, int): int  [API 1+]
+ getColorStateList(int): ColorStateList  [API 1+]
+ getDimension(int, float): float  [API 1+]
+ getDimensionPixelOffset(int, int): int  [API 1+]
+ getDimensionPixelSize(int, int): int  [API 1+]
+ getDrawable(int): Drawable  [API 1+]
+ getFloat(int, float): float  [API 1+]
+ getFont(int): Typeface  [API 26+]
+ getFraction(int, int, int, float): float  [API 1+]
+ getIndex(int): int  [API 1+]
+ getIndexCount(): int  [API 1+]
+ getInt(int, int): int  [API 1+]
+ getInteger(int, int): int  [API 1+]
+ getLayoutDimension(int, String): int  [API 1+]
+ getLayoutDimension(int, int): int  [API 3+]
+ getNonResourceString(int): String  [API 1+]
~ getPositionDescription(): String  [API 1+]
+ getResourceId(int, int): int  [API 1+]
+ getResources(): Resources  [API 1+]
+ getSourceResourceId(int, int): int  [API 29+]
+ getString(int): String  [API 1+]
+ getText(int): CharSequence  [API 1+]
+ getTextArray(int): CharSequence[]  [API 1+]
+ getType(int): int  [API 21+]
+ getValue(int, TypedValue): boolean  [API 1+]
+ hasValue(int): boolean  [API 1+]
+ hasValueOrEmpty(int): boolean  [API 22+]
+ length(): int  [API 1+]
+ peekValue(int): TypedValue  [API 1+]
~ recycle(): void  [API 1+]
+ toString(): String  [API 1+]
```

## XmlResourceParser — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 1+]
+ getAttributeNamespace(int): String  [API 1+]
```

