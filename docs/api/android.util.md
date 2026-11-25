# android.util

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AndroidException — framework; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ AndroidException()  [API 1+]
+ AndroidException(Exception)  [API 1+]
+ AndroidException(String)  [API 1+]
+ AndroidException(String, Throwable)  [API 11+]
```

## AndroidRuntimeException — framework; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ AndroidRuntimeException()  [API 1+]
+ AndroidRuntimeException(Exception)  [API 1+]
+ AndroidRuntimeException(String)  [API 1+]
+ AndroidRuntimeException(String, Throwable)  [API 11+]
```

## ArrayMap — framework; API 19+ (31 +, 0 *, 0 ~, 0 -)

```
+ ArrayMap()  [API 19+]
+ ArrayMap(ArrayMap)  [API 19+]
+ ArrayMap(int)  [API 19+]
+ clear(): void  [API 19+]
+ containsAll(Collection): boolean  [API 19+]
+ containsKey(Object): boolean  [API 19+]
+ containsValue(Object): boolean  [API 19+]
+ ensureCapacity(int): void  [API 19+]
+ entrySet(): Set  [API 19+]
+ equals(Object): boolean  [API 19+]
+ forEach(BiConsumer): void  [API 19+]
+ get(Object): Object  [API 19+]
+ hashCode(): int  [API 19+]
+ indexOfKey(Object): int  [API 21+]
+ indexOfValue(Object): int  [API 29+]
+ isEmpty(): boolean  [API 19+]
+ keyAt(int): Object  [API 19+]
+ keySet(): Set  [API 19+]
+ put(Object, Object): Object  [API 19+]
+ putAll(ArrayMap): void  [API 19+]
+ putAll(Map): void  [API 19+]
+ remove(Object): Object  [API 19+]
+ removeAll(Collection): boolean  [API 19+]
+ removeAt(int): Object  [API 19+]
+ replaceAll(BiFunction): void  [API 19+]
+ retainAll(Collection): boolean  [API 19+]
+ setValueAt(int, Object): Object  [API 19+]
+ size(): int  [API 19+]
+ toString(): String  [API 19+]
+ valueAt(int): Object  [API 19+]
+ values(): Collection  [API 19+]
```

## ArraySet — framework; API 23+ (29 +, 0 *, 0 ~, 0 -)

```
+ ArraySet()  [API 23+]
+ ArraySet(ArraySet)  [API 23+]
+ ArraySet(Collection)  [API 29+]
+ ArraySet(Object[])  [API 30+]
+ ArraySet(int)  [API 23+]
+ add(Object): boolean  [API 23+]
+ addAll(ArraySet): void  [API 23+]
+ addAll(Collection): boolean  [API 23+]
+ clear(): void  [API 23+]
+ contains(Object): boolean  [API 23+]
+ containsAll(Collection): boolean  [API 23+]
+ ensureCapacity(int): void  [API 23+]
+ equals(Object): boolean  [API 23+]
+ forEach(Consumer): void  [API 23+]
+ hashCode(): int  [API 23+]
+ indexOf(Object): int  [API 23+]
+ isEmpty(): boolean  [API 23+]
+ iterator(): Iterator  [API 23+]
+ remove(Object): boolean  [API 23+]
+ removeAll(ArraySet): boolean  [API 23+]
+ removeAll(Collection): boolean  [API 23+]
+ removeAt(int): Object  [API 23+]
+ removeIf(Predicate): boolean  [API 23+]
+ retainAll(Collection): boolean  [API 23+]
+ size(): int  [API 23+]
+ toArray(): Object[]  [API 23+]
+ toArray(Object[]): Object[]  [API 23+]
+ toString(): String  [API 23+]
+ valueAt(int): Object  [API 23+]
```

## AtomicFile — framework; API 17+ (10 +, 0 *, 0 ~, 0 -)

```
+ AtomicFile(File)  [API 17+]
+ delete(): void  [API 17+]
+ failWrite(FileOutputStream): void  [API 17+]
+ finishWrite(FileOutputStream): void  [API 17+]
+ getBaseFile(): File  [API 17+]
+ getLastModifiedTime(): long  [API 31+]
+ openRead(): FileInputStream  [API 17+]
+ readFully(): byte[]  [API 17+]
+ startWrite(): FileOutputStream  [API 17+]
+ toString(): String  [API 17+]
```

## AttributeSet — framework; API 1+ (22 +, 0 *, 1 ~, 0 -)

```
+ getAttributeBooleanValue(String, String, boolean): boolean  [API 1+]
+ getAttributeBooleanValue(int, boolean): boolean  [API 1+]
+ getAttributeCount(): int  [API 1+]
+ getAttributeFloatValue(String, String, float): float  [API 1+]
+ getAttributeFloatValue(int, float): float  [API 1+]
+ getAttributeIntValue(String, String, int): int  [API 1+]
+ getAttributeIntValue(int, int): int  [API 1+]
+ getAttributeListValue(String, String, String[], int): int  [API 1+]
+ getAttributeListValue(int, String[], int): int  [API 1+]
+ getAttributeName(int): String  [API 1+]
+ getAttributeNameResource(int): int  [API 1+]
~ getAttributeNamespace(int): String  [API 28+]
+ getAttributeResourceValue(String, String, int): int  [API 1+]
+ getAttributeResourceValue(int, int): int  [API 1+]
+ getAttributeUnsignedIntValue(String, String, int): int  [API 1+]
+ getAttributeUnsignedIntValue(int, int): int  [API 1+]
+ getAttributeValue(String, String): String  [API 1+]
+ getAttributeValue(int): String  [API 1+]
+ getClassAttribute(): String  [API 1+]
+ getIdAttribute(): String  [API 1+]
+ getIdAttributeResourceValue(int): int  [API 1+]
+ getPositionDescription(): String  [API 1+]
+ getStyleAttribute(): int  [API 1+]
```

## Base64 — framework; API 8+ (7 +, 0 *, 0 ~, 0 -)

```
+ decode(String, int): byte[] [static]  [API 8+]
+ decode(byte[], int): byte[] [static]  [API 8+]
+ decode(byte[], int, int, int): byte[] [static]  [API 8+]
+ encode(byte[], int): byte[] [static]  [API 8+]
+ encode(byte[], int, int, int): byte[] [static]  [API 8+]
+ encodeToString(byte[], int): String [static]  [API 8+]
+ encodeToString(byte[], int, int, int): String [static]  [API 8+]
```

## Base64DataException — framework; API 11+ (1 +, 0 *, 0 ~, 0 -)

```
+ Base64DataException(String)  [API 11+]
```

## Base64InputStream — framework; API 8+ (6 +, 0 *, 3 ~, 0 -)

```
+ Base64InputStream(InputStream, int)  [API 8+]
+ available(): int  [API 8+]
+ close(): void  [API 8+]
~ mark(int): void  [API 8+]
~ markSupported(): boolean  [API 8+]
+ read(): int  [API 8+]
+ read(byte[], int, int): int  [API 8+]
~ reset(): void  [API 8+]
+ skip(long): long  [API 8+]
```

## Base64OutputStream — framework; API 8+ (4 +, 0 *, 0 ~, 0 -)

```
+ Base64OutputStream(OutputStream, int)  [API 8+]
+ close(): void  [API 8+]
+ write(byte[], int, int): void  [API 8+]
+ write(int): void  [API 8+]
```

## CloseGuard — declaration; API 30+ (0 +, 0 *, 0 ~, 4 -)

```
- CloseGuard()  [API 30+]
- close(): void  [API 30+]
- open(String): void  [API 30+]
- warnIfOpen(): void  [API 30+]
```

## Config — declaration; API 1+, deprecated in 14 (1 +, 0 *, 0 ~, 0 -)

```
+ Config()  [API 1-11, removed in 12]
```

## DebugUtils — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ DebugUtils()  [API 1-11, removed in 12]
+ isObjectSelected(Object): boolean [static]  [API 1+]
```

## DisplayMetrics — framework; API 1+ (6 +, 0 *, 0 ~, 1 -)

```
- DENSITY_DEVICE_STABLE [field]  [API 24+]
+ DisplayMetrics()  [API 1+]
+ density [field]  [API 1+]
+ densityDpi [field]  [API 4+]
- equals(DisplayMetrics): boolean  [API 17+]
+ equals(Object): boolean  [API 1+]
+ hashCode(): int  [API 1+]
+ heightPixels [field]  [API 1+]
+ scaledDensity [field]  [API 1+, deprecated in 34]
+ setTo(DisplayMetrics): void  [API 1+]
+ setToDefaults(): void  [API 1+]
+ toString(): String  [API 1+]
+ widthPixels [field]  [API 1+]
+ xdpi [field]  [API 1+]
+ ydpi [field]  [API 1+]
```

## Dumpable — declaration; API 33+ (1 +, 0 *, 0 ~, 1 -)

```
+ dump(PrintWriter, String[]): void  [API 33+]
- getDumpableName(): String  [API 33+]
```

## DumpableContainer — declaration; API 33+ (2 +, 0 *, 0 ~, 0 -)

```
+ addDumpable(Dumpable): boolean  [API 33+]
+ removeDumpable(Dumpable): boolean  [API 33+]
```

## EventLog — declaration; API 8+ (0 +, 0 *, 0 ~, 9 -)

```
- EventLog()  [API 8-11, removed in 12]
- getTagCode(String): int [static]  [API 8+]
- getTagName(int): String [static]  [API 8+]
- readEvents(int[], Collection): void [static]  [API 8+]
- writeEvent(int, Object[]): int [static]  [API 8+]
- writeEvent(int, String): int [static]  [API 8+]
- writeEvent(int, float): int [static]  [API 23+]
- writeEvent(int, int): int [static]  [API 8+]
- writeEvent(int, long): int [static]  [API 8+]
```

## EventLog.Event — declaration; API 8+ (0 +, 0 *, 0 ~, 7 -)

```
- equals(Object): boolean  [API 8+]
- getData(): Object  [API 8+]
- getProcessId(): int  [API 8+]
- getTag(): int  [API 8+]
- getThreadId(): int  [API 8+]
- getTimeNanos(): long  [API 8+]
- hashCode(): int  [API 8+]
```

## EventLogTags — declaration; API 1+, deprecated in 9 (0 +, 0 *, 0 ~, 4 -)

```
- EventLogTags()  [API 1+]
- EventLogTags(BufferedReader)  [API 1+]
- get(String): EventLogTags.Description  [API 1+]
- get(int): EventLogTags.Description  [API 1+]
```

## EventLogTags.Description — declaration; API 1+, deprecated in 29 (0 +, 0 *, 0 ~, 0 -)

```
- mName [field]  [API 1+]
- mTag [field]  [API 1+]
```

## FloatMath — framework; API 1+, deprecated in 22 (8 +, 0 *, 0 ~, 0 -)

```
+ ceil(float): float  [API 1-22, removed in 23]
+ cos(float): float  [API 1-22, removed in 23]
+ exp(float): float  [API 17-22, removed in 23]
+ floor(float): float  [API 1-22, removed in 23]
+ hypot(float, float): float  [API 17-22, removed in 23]
+ pow(float, float): float  [API 17-22, removed in 23]
+ sin(float): float  [API 1-22, removed in 23]
+ sqrt(float): float  [API 1-22, removed in 23]
```

## FloatProperty — framework; API 24+ (3 +, 0 *, 0 ~, 0 -)

```
+ FloatProperty(String)  [API 24+]
+ set(Object, Float): void  [API 24+]
+ setValue(Object, float): void  [API 24+]
```

## Half — framework; API 26+ (49 +, 0 *, 0 ~, 0 -)

```
+ Half(String)  [API 26+]
+ Half(double)  [API 26+]
+ Half(float)  [API 26+]
+ Half(short)  [API 26+]
+ abs(short): short [static]  [API 26+]
+ byteValue(): byte  [API 26+]
+ ceil(short): short [static]  [API 26+]
+ compare(short, short): int [static]  [API 26+]
+ compareTo(Half): int  [API 26+]
+ copySign(short, short): short [static]  [API 26+]
+ doubleValue(): double  [API 26+]
+ equals(Object): boolean  [API 26+]
+ equals(short, short): boolean [static]  [API 26+]
+ floatValue(): float  [API 26+]
+ floor(short): short [static]  [API 26+]
+ getExponent(short): int [static]  [API 26+]
+ getSign(short): int [static]  [API 26+]
+ getSignificand(short): int [static]  [API 26+]
+ greater(short, short): boolean [static]  [API 26+]
+ greaterEquals(short, short): boolean [static]  [API 26+]
+ halfToIntBits(short): int [static]  [API 26+]
+ halfToRawIntBits(short): int [static]  [API 26+]
+ halfToShortBits(short): short [static]  [API 26+]
+ halfValue(): short  [API 26+]
+ hashCode(): int  [API 26+]
+ hashCode(short): int [static]  [API 26+]
+ intBitsToHalf(int): short [static]  [API 26+]
+ intValue(): int  [API 26+]
+ isInfinite(short): boolean [static]  [API 26+]
+ isNaN(): boolean  [API 26+]
+ isNaN(short): boolean [static]  [API 26+]
+ isNormalized(short): boolean [static]  [API 26+]
+ less(short, short): boolean [static]  [API 26+]
+ lessEquals(short, short): boolean [static]  [API 26+]
+ longValue(): long  [API 26+]
+ max(short, short): short [static]  [API 26+]
+ min(short, short): short [static]  [API 26+]
+ parseHalf(String): short [static]  [API 26+]
+ round(short): short [static]  [API 26+]
+ shortValue(): short  [API 26+]
+ toFloat(short): float [static]  [API 26+]
+ toHalf(float): short [static]  [API 26+]
+ toHexString(short): String [static]  [API 26+]
+ toString(): String  [API 26+]
+ toString(short): String [static]  [API 26+]
+ trunc(short): short [static]  [API 26+]
+ valueOf(String): Half [static]  [API 26+]
+ valueOf(float): Half [static]  [API 26+]
+ valueOf(short): Half [static]  [API 26+]
```

## IntProperty — framework; API 24+ (3 +, 0 *, 0 ~, 0 -)

```
+ IntProperty(String)  [API 24+]
+ set(Object, Integer): void  [API 24+]
+ setValue(Object, int): void  [API 24+]
```

## JsonReader — framework; API 11+ (19 +, 0 *, 0 ~, 0 -)

```
+ JsonReader(Reader)  [API 11+]
+ beginArray(): void  [API 11+]
+ beginObject(): void  [API 11+]
+ close(): void  [API 11+]
+ endArray(): void  [API 11+]
+ endObject(): void  [API 11+]
+ hasNext(): boolean  [API 11+]
+ isLenient(): boolean  [API 11+]
+ nextBoolean(): boolean  [API 11+]
+ nextDouble(): double  [API 11+]
+ nextInt(): int  [API 11+]
+ nextLong(): long  [API 11+]
+ nextName(): String  [API 11+]
+ nextNull(): void  [API 11+]
+ nextString(): String  [API 11+]
+ peek(): JsonToken  [API 11+]
+ setLenient(boolean): void  [API 11+]
+ skipValue(): void  [API 11+]
+ toString(): String  [API 11+]
```

## JsonToken — framework; API 11+ (2 +, 0 *, 0 ~, 0 -)

```
+ BEGIN_ARRAY [field]  [API 11+]
+ BEGIN_OBJECT [field]  [API 11+]
+ BOOLEAN [field]  [API 11+]
+ END_ARRAY [field]  [API 11+]
+ END_DOCUMENT [field]  [API 11+]
+ END_OBJECT [field]  [API 11+]
+ NAME [field]  [API 11+]
+ NULL [field]  [API 11+]
+ NUMBER [field]  [API 11+]
+ STRING [field]  [API 11+]
+ valueOf(String): JsonToken [static]  [API 11+]
+ values(): JsonToken[] [static]  [API 11+]
```

## JsonWriter — framework; API 11+ (17 +, 0 *, 0 ~, 0 -)

```
+ JsonWriter(Writer)  [API 11+]
+ beginArray(): JsonWriter  [API 11+]
+ beginObject(): JsonWriter  [API 11+]
+ close(): void  [API 11+]
+ endArray(): JsonWriter  [API 11+]
+ endObject(): JsonWriter  [API 11+]
+ flush(): void  [API 11+]
+ isLenient(): boolean  [API 11+]
+ name(String): JsonWriter  [API 11+]
+ nullValue(): JsonWriter  [API 11+]
+ setIndent(String): void  [API 11+]
+ setLenient(boolean): void  [API 11+]
+ value(Number): JsonWriter  [API 11+]
+ value(String): JsonWriter  [API 11+]
+ value(boolean): JsonWriter  [API 11+]
+ value(double): JsonWriter  [API 11+]
+ value(long): JsonWriter  [API 11+]
```

## LayoutDirection — framework; API 19+ (0 +, 0 *, 0 ~, 0 -)

## Log — framework; API 1+ (17 +, 0 *, 0 ~, 0 -)

```
+ d(String, String): int [static]  [API 1+]
+ d(String, String, Throwable): int [static]  [API 1+]
+ e(String, String): int [static]  [API 1+]
+ e(String, String, Throwable): int [static]  [API 1+]
+ getStackTraceString(Throwable): String [static]  [API 1+]
+ i(String, String): int [static]  [API 1+]
+ i(String, String, Throwable): int [static]  [API 1+]
+ isLoggable(String, int): boolean [static]  [API 1+]
+ println(int, String, String): int [static]  [API 1+]
+ v(String, String): int [static]  [API 1+]
+ v(String, String, Throwable): int [static]  [API 1+]
+ w(String, String): int [static]  [API 1+]
+ w(String, String, Throwable): int [static]  [API 1+]
+ w(String, Throwable): int [static]  [API 1+]
+ wtf(String, String): int [static]  [API 8+]
+ wtf(String, String, Throwable): int [static]  [API 8+]
+ wtf(String, Throwable): int [static]  [API 8+]
```

## LogPrinter — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ LogPrinter(int, String)  [API 1+]
+ println(String): void  [API 1+]
```

## LongSparseArray — framework; API 16+ (18 +, 0 *, 0 ~, 0 -)

```
+ LongSparseArray()  [API 16+]
+ LongSparseArray(int)  [API 16+]
+ append(long, Object): void  [API 16+]
+ clear(): void  [API 16+]
+ clone(): LongSparseArray  [API 16+]
+ delete(long): void  [API 16+]
+ get(long): Object  [API 16+]
+ get(long, Object): Object  [API 16+]
+ indexOfKey(long): int  [API 16+]
+ indexOfValue(Object): int  [API 16+]
+ keyAt(int): long  [API 16+]
+ put(long, Object): void  [API 16+]
+ remove(long): void  [API 16+]
+ removeAt(int): void  [API 16+]
+ setValueAt(int, Object): void  [API 16+]
+ size(): int  [API 16+]
+ toString(): String  [API 16+]
+ valueAt(int): Object  [API 16+]
```

## LruCache — framework; API 12+ (16 +, 0 *, 3 ~, 0 -)

```
+ LruCache(int)  [API 12+]
~ create(Object): Object  [API 12+]
+ createCount(): int  [API 12+]
~ entryRemoved(boolean, Object, Object, Object): void  [API 12+]
+ evictAll(): void  [API 12+]
+ evictionCount(): int  [API 12+]
+ get(Object): Object  [API 12+]
+ hitCount(): int  [API 12+]
+ maxSize(): int  [API 12+]
+ missCount(): int  [API 12+]
+ put(Object, Object): Object  [API 12+]
+ putCount(): int  [API 12+]
+ remove(Object): Object  [API 12+]
+ resize(int): void  [API 21+]
+ size(): int  [API 12+]
~ sizeOf(Object, Object): int  [API 12+]
+ snapshot(): Map  [API 12+]
+ toString(): String  [API 12+]
+ trimToSize(int): void  [API 17+]
```

## MalformedJsonException — framework; API 11+ (1 +, 0 *, 0 ~, 0 -)

```
+ MalformedJsonException(String)  [API 11+]
```

## MonthDisplayHelper — framework; API 1+ (15 +, 0 *, 0 ~, 0 -)

```
+ MonthDisplayHelper(int, int)  [API 1+]
+ MonthDisplayHelper(int, int, int)  [API 1+]
+ getColumnOf(int): int  [API 1+]
+ getDayAt(int, int): int  [API 1+]
+ getDigitsForRow(int): int[]  [API 1+]
+ getFirstDayOfMonth(): int  [API 1+]
+ getMonth(): int  [API 1+]
+ getNumberOfDaysInMonth(): int  [API 1+]
+ getOffset(): int  [API 1+]
+ getRowOf(int): int  [API 1+]
+ getWeekStartDay(): int  [API 1+]
+ getYear(): int  [API 1+]
+ isWithinCurrentMonth(int, int): boolean  [API 1+]
+ nextMonth(): void  [API 1+]
+ previousMonth(): void  [API 1+]
```

## MutableBoolean — declaration; API 21+, deprecated in 28 (0 +, 1 *, 0 ~, 0 -)

```
* MutableBoolean(boolean)  [API 21+]
+ value [field]  [API 21+]
```

## MutableByte — declaration; API 21+, deprecated in 28 (0 +, 1 *, 0 ~, 0 -)

```
* MutableByte(byte)  [API 21+]
+ value [field]  [API 21+]
```

## MutableChar — declaration; API 21+, deprecated in 28 (0 +, 1 *, 0 ~, 0 -)

```
* MutableChar(char)  [API 21+]
+ value [field]  [API 21+]
```

## MutableDouble — declaration; API 21+, deprecated in 28 (0 +, 1 *, 0 ~, 0 -)

```
* MutableDouble(double)  [API 21+]
+ value [field]  [API 21+]
```

## MutableFloat — declaration; API 21+, deprecated in 28 (0 +, 1 *, 0 ~, 0 -)

```
* MutableFloat(float)  [API 21+]
+ value [field]  [API 21+]
```

## MutableInt — declaration; API 21+, deprecated in 28 (0 +, 1 *, 0 ~, 0 -)

```
* MutableInt(int)  [API 21+]
+ value [field]  [API 21+]
```

## MutableLong — declaration; API 21+, deprecated in 28 (0 +, 1 *, 0 ~, 0 -)

```
* MutableLong(long)  [API 21+]
+ value [field]  [API 21+]
```

## MutableShort — declaration; API 21+, deprecated in 28 (0 +, 1 *, 0 ~, 0 -)

```
* MutableShort(short)  [API 21+]
+ value [field]  [API 21+]
```

## NoSuchPropertyException — framework; API 14+ (1 +, 0 *, 0 ~, 0 -)

```
+ NoSuchPropertyException(String)  [API 14+]
```

## Pair — framework; API 5+ (5 +, 0 *, 0 ~, 0 -)

```
+ Pair(Object, Object)  [API 5+]
+ create(Object, Object): Pair [static]  [API 5+]
+ equals(Object): boolean  [API 5+]
+ first [field]  [API 5+]
+ hashCode(): int  [API 5+]
+ second [field]  [API 5+]
+ toString(): String  [API 5+]
```

## Patterns — framework; API 8+ (2 +, 0 *, 0 ~, 0 -)

```
+ DOMAIN_NAME [field]  [API 8+]
+ EMAIL_ADDRESS [field]  [API 8+]
+ IP_ADDRESS [field]  [API 8+, deprecated in 31]
+ PHONE [field]  [API 8+]
+ TOP_LEVEL_DOMAIN [field]  [API 8+, deprecated in 21]
+ WEB_URL [field]  [API 8+]
+ concatGroups(Matcher): String [static]  [API 8+]
+ digitsAndPlusOnly(Matcher): String [static]  [API 8+]
```

## PrintStreamPrinter — framework; API 3+ (2 +, 0 *, 0 ~, 0 -)

```
+ PrintStreamPrinter(PrintStream)  [API 3+]
+ println(String): void  [API 3+]
```

## PrintWriterPrinter — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ PrintWriterPrinter(PrintWriter)  [API 1+]
+ println(String): void  [API 1+]
```

## Printer — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ println(String): void  [API 1+]
```

## Property — framework; API 14+ (6 +, 0 *, 1 ~, 0 -)

```
+ Property(Class, String)  [API 14+]
+ get(Object): Object  [API 14+]
+ getName(): String  [API 14+]
+ getType(): Class  [API 14+]
~ isReadOnly(): boolean  [API 14+]
+ of(Class, Class, String): Property [static]  [API 14+]
+ set(Object, Object): void  [API 14+]
```

## Range — framework; API 21+ (15 +, 0 *, 0 ~, 0 -)

```
+ Range(Comparable, Comparable)  [API 21+]
+ clamp(Comparable): Comparable  [API 21+]
+ contains(Comparable): boolean  [API 21+]
+ contains(Range): boolean  [API 21+]
+ create(Comparable, Comparable): Range [static]  [API 21+]
+ equals(Object): boolean  [API 21+]
+ extend(Comparable): Range  [API 21+]
+ extend(Comparable, Comparable): Range  [API 21+]
+ extend(Range): Range  [API 21+]
+ getLower(): Comparable  [API 21+]
+ getUpper(): Comparable  [API 21+]
+ hashCode(): int  [API 21+]
+ intersect(Comparable, Comparable): Range  [API 21+]
+ intersect(Range): Range  [API 21+]
+ toString(): String  [API 21+]
```

## Rational — framework; API 21+ (17 +, 0 *, 0 ~, 0 -)

```
+ NEGATIVE_INFINITY [field]  [API 21+]
+ NaN [field]  [API 21+]
+ POSITIVE_INFINITY [field]  [API 21+]
+ Rational(int, int)  [API 21+]
+ ZERO [field]  [API 21+]
+ compareTo(Rational): int  [API 21+]
+ doubleValue(): double  [API 21+]
+ equals(Object): boolean  [API 21+]
+ floatValue(): float  [API 21+]
+ getDenominator(): int  [API 21+]
+ getNumerator(): int  [API 21+]
+ hashCode(): int  [API 21+]
+ intValue(): int  [API 21+]
+ isFinite(): boolean  [API 21+]
+ isInfinite(): boolean  [API 21+]
+ isNaN(): boolean  [API 21+]
+ isZero(): boolean  [API 21+]
+ longValue(): long  [API 21+]
+ parseRational(String): Rational [static]  [API 21+]
+ shortValue(): short  [API 21+]
+ toString(): String  [API 21+]
```

## Size — framework; API 21+ (7 +, 0 *, 0 ~, 0 -)

```
+ Size(int, int)  [API 21+]
+ equals(Object): boolean  [API 21+]
+ getHeight(): int  [API 21+]
+ getWidth(): int  [API 21+]
+ hashCode(): int  [API 21+]
+ parseSize(String): Size [static]  [API 21+]
+ toString(): String  [API 21+]
```

## SizeF — framework; API 21+ (8 +, 0 *, 1 ~, 0 -)

```
+ CREATOR [field]  [API 31+]
+ SizeF(float, float)  [API 21+]
~ describeContents(): int  [API 21+]
+ equals(Object): boolean  [API 21+]
+ getHeight(): float  [API 21+]
+ getWidth(): float  [API 21+]
+ hashCode(): int  [API 21+]
+ parseSizeF(String): SizeF [static]  [API 21+]
+ toString(): String  [API 21+]
+ writeToParcel(Parcel, int): void  [API 21+]
```

## SparseArray — framework; API 1+ (23 +, 0 *, 0 ~, 0 -)

```
+ SparseArray()  [API 1+]
+ SparseArray(int)  [API 1+]
+ append(int, Object): void  [API 1+]
+ clear(): void  [API 1+]
+ clone(): SparseArray  [API 14+]
+ contains(int): boolean  [API 30+]
+ contentEquals(SparseArray): boolean  [API 31+]
+ contentHashCode(): int  [API 31+]
+ delete(int): void  [API 1+]
+ get(int): Object  [API 1+]
+ get(int, Object): Object  [API 1+]
+ indexOfKey(int): int  [API 1+]
+ indexOfValue(Object): int  [API 1+]
+ keyAt(int): int  [API 1+]
+ put(int, Object): void  [API 1+]
+ remove(int): void  [API 1+]
+ removeAt(int): void  [API 11+]
+ removeAtRange(int, int): void  [API 19+]
+ set(int, Object): void  [API 31+]
+ setValueAt(int, Object): void  [API 1+]
+ size(): int  [API 1+]
+ toString(): String  [API 1+]
+ valueAt(int): Object  [API 1+]
```

## SparseBooleanArray — framework; API 1+ (19 +, 0 *, 0 ~, 0 -)

```
+ SparseBooleanArray()  [API 1+]
+ SparseBooleanArray(int)  [API 1+]
+ append(int, boolean): void  [API 1+]
+ clear(): void  [API 1+]
+ clone(): SparseBooleanArray  [API 14+]
+ delete(int): void  [API 1+]
+ equals(Object): boolean  [API 1+]
+ get(int): boolean  [API 1+]
+ get(int, boolean): boolean  [API 1+]
+ hashCode(): int  [API 1+]
+ indexOfKey(int): int  [API 1+]
+ indexOfValue(boolean): int  [API 1+]
+ keyAt(int): int  [API 1+]
+ put(int, boolean): void  [API 1+]
+ removeAt(int): void  [API 28+]
+ setValueAt(int, boolean): void  [API 29+]
+ size(): int  [API 1+]
+ toString(): String  [API 1+]
+ valueAt(int): boolean  [API 1+]
```

## SparseIntArray — framework; API 1+ (17 +, 0 *, 0 ~, 0 -)

```
+ SparseIntArray()  [API 1+]
+ SparseIntArray(int)  [API 1+]
+ append(int, int): void  [API 1+]
+ clear(): void  [API 1+]
+ clone(): SparseIntArray  [API 14+]
+ delete(int): void  [API 1+]
+ get(int): int  [API 1+]
+ get(int, int): int  [API 1+]
+ indexOfKey(int): int  [API 1+]
+ indexOfValue(int): int  [API 1+]
+ keyAt(int): int  [API 1+]
+ put(int, int): void  [API 1+]
+ removeAt(int): void  [API 3+]
+ setValueAt(int, int): void  [API 29+]
+ size(): int  [API 1+]
+ toString(): String  [API 1+]
+ valueAt(int): int  [API 1+]
```

## SparseLongArray — framework; API 18+ (16 +, 0 *, 0 ~, 0 -)

```
+ SparseLongArray()  [API 18+]
+ SparseLongArray(int)  [API 18+]
+ append(int, long): void  [API 18+]
+ clear(): void  [API 18+]
+ clone(): SparseLongArray  [API 18+]
+ delete(int): void  [API 18+]
+ get(int): long  [API 18+]
+ get(int, long): long  [API 18+]
+ indexOfKey(int): int  [API 18+]
+ indexOfValue(long): int  [API 18+]
+ keyAt(int): int  [API 18+]
+ put(int, long): void  [API 18+]
+ removeAt(int): void  [API 18+]
+ size(): int  [API 18+]
+ toString(): String  [API 18+]
+ valueAt(int): long  [API 18+]
```

## StateSet — framework; API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ NOTHING [field]  [API 11+]
+ StateSet()  [API 1-11, removed in 12]
+ WILD_CARD [field]  [API 1+]
+ dump(int[]): String [static]  [API 1+]
+ isWildCard(int[]): boolean [static]  [API 1+]
+ stateSetMatches(int[], int): boolean [static]  [API 1+]
+ stateSetMatches(int[], int[]): boolean [static]  [API 1+]
+ trimStateSet(int[], int): int[] [static]  [API 1+]
```

## StatsLog — declaration; API 28+ (0 +, 0 *, 0 ~, 4 -)

```
- logBinaryPushStateChanged(String, long, int, int, long[]): boolean [static]  [API 29+]
- logEvent(int): boolean [static]  [API 28+]
- logStart(int): boolean [static]  [API 28+]
- logStop(int): boolean [static]  [API 28+]
```

## StringBuilderPrinter — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ StringBuilderPrinter(StringBuilder)  [API 1+]
+ println(String): void  [API 1+]
```

## TimeFormatException — framework; API 1+ (0 +, 0 *, 0 ~, 0 -)

## TimeUtils — declaration; API 1+ (0 +, 0 *, 0 ~, 5 -)

```
- TimeUtils()  [API 1-11, removed in 12]
- getTimeZone(int, boolean, long, String): TimeZone [static]  [API 1+]
- getTimeZoneDatabaseVersion(): String [static]  [API 3+]
- getTimeZoneIdsForCountryCode(String): List [static]  [API 29+]
- isTimeBetween(LocalTime, LocalTime, LocalTime): boolean [static]  [API 30+]
```

## TimingLogger — framework; API 1+, deprecated in 30 (5 +, 0 *, 0 ~, 0 -)

```
+ TimingLogger(String, String)  [API 1+]
+ addSplit(String): void  [API 1+]
+ dumpToLog(): void  [API 1+]
+ reset(): void  [API 1+]
+ reset(String, String): void  [API 1+]
```

## TypedValue — framework; API 1+ (20 +, 0 *, 0 ~, 0 -)

```
+ TypedValue()  [API 1+]
+ applyDimension(int, float, DisplayMetrics): float [static]  [API 1+]
+ assetCookie [field]  [API 1+]
+ changingConfigurations [field]  [API 1+]
+ coerceToString(): CharSequence  [API 1+]
+ coerceToString(int, int): String [static]  [API 1+]
+ complexToDimension(int, DisplayMetrics): float [static]  [API 1+]
+ complexToDimensionNoisy(int, DisplayMetrics): float  [API 1-19, removed in 20]
+ complexToDimensionPixelOffset(int, DisplayMetrics): int [static]  [API 1+]
+ complexToDimensionPixelSize(int, DisplayMetrics): int [static]  [API 1+]
+ complexToFloat(int): float [static]  [API 1+]
+ complexToFraction(int, float, float): float [static]  [API 1+]
+ convertDimensionToPixels(int, float, DisplayMetrics): float [static]  [API 34+]
+ convertPixelsToDimension(int, float, DisplayMetrics): float [static]  [API 34+]
+ data [field]  [API 1+]
+ density [field]  [API 4+]
+ deriveDimension(int, float, DisplayMetrics): float [static]  [API 34+]
+ getComplexUnit(): int  [API 22+]
+ getDimension(DisplayMetrics): float  [API 1+]
+ getFloat(): float  [API 1+]
+ getFraction(float, float): float  [API 1+]
+ isColorType(): boolean  [API 29+]
+ resourceId [field]  [API 1+]
+ setTo(TypedValue): void  [API 1+]
+ sourceResourceId [field]  [API 29+]
+ string [field]  [API 1+]
+ toString(): String  [API 1+]
+ type [field]  [API 1+]
```

## Xml — framework; API 1+ (8 +, 0 *, 0 ~, 0 -)

```
+ FEATURE_RELAXED [field]  [API 1+]
+ Xml()  [API 1-11, removed in 12]
+ asAttributeSet(XmlPullParser): AttributeSet [static]  [API 1+]
+ findEncodingByName(String): Xml.Encoding [static]  [API 1+]
+ newPullParser(): XmlPullParser [static]  [API 1+]
+ newSerializer(): XmlSerializer [static]  [API 1+]
+ parse(InputStream, Xml.Encoding, ContentHandler): void [static]  [API 1+]
+ parse(Reader, ContentHandler): void [static]  [API 1+]
+ parse(String, ContentHandler): void [static]  [API 1+]
```

## Xml.Encoding — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ ISO_8859_1 [field]  [API 1+]
+ US_ASCII [field]  [API 1+]
+ UTF_16 [field]  [API 1+]
+ UTF_8 [field]  [API 1+]
+ valueOf(String): Xml.Encoding [static]  [API 1+]
+ values(): Xml.Encoding[] [static]  [API 1+]
```

