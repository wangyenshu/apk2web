# java.util

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AbstractCollection — TeaVM class library; API 1+ (15 +, 0 *, 0 ~, 0 -)

```
+ AbstractCollection()  [API 1+]
+ add(Object): boolean  [API 1+]
+ addAll(Collection): boolean  [API 1+]
+ clear(): void  [API 1+]
+ contains(Object): boolean  [API 1+]
+ containsAll(Collection): boolean  [API 1+]
+ isEmpty(): boolean  [API 1+]
+ iterator(): Iterator  [API 1+]
+ remove(Object): boolean  [API 1+]
+ removeAll(Collection): boolean  [API 1+]
+ retainAll(Collection): boolean  [API 1+]
+ size(): int  [API 1+]
+ toArray(): Object[]  [API 1+]
+ toArray(Object[]): Object[]  [API 1+]
+ toString(): String  [API 1+]
```

## AbstractList — TeaVM class library; API 1+ (17 +, 0 *, 0 ~, 0 -)

```
+ AbstractList()  [API 1+]
+ add(Object): boolean  [API 1+]
+ add(int, Object): void  [API 1+]
+ addAll(int, Collection): boolean  [API 1+]
+ clear(): void  [API 1+]
+ equals(Object): boolean  [API 1+]
+ get(int): Object  [API 1+]
+ hashCode(): int  [API 1+]
+ indexOf(Object): int  [API 1+]
+ iterator(): Iterator  [API 1+]
+ lastIndexOf(Object): int  [API 1+]
+ listIterator(): ListIterator  [API 1+]
+ listIterator(int): ListIterator  [API 1+]
+ modCount [field]  [API 1+]
+ remove(int): Object  [API 1+]
+ removeRange(int, int): void  [API 1+]
+ set(int, Object): Object  [API 1+]
+ subList(int, int): List  [API 1+]
```

## AbstractMap — TeaVM class library; API 1+ (17 +, 0 *, 0 ~, 0 -)

```
+ AbstractMap()  [API 1+]
+ clear(): void  [API 1+]
+ clone(): Object  [API 1+]
+ containsKey(Object): boolean  [API 1+]
+ containsValue(Object): boolean  [API 1+]
+ entrySet(): Set  [API 1+]
+ equals(Object): boolean  [API 1+]
+ get(Object): Object  [API 1+]
+ hashCode(): int  [API 1+]
+ isEmpty(): boolean  [API 1+]
+ keySet(): Set  [API 1+]
+ put(Object, Object): Object  [API 1+]
+ putAll(Map): void  [API 1+]
+ remove(Object): Object  [API 1+]
+ size(): int  [API 1+]
+ toString(): String  [API 1+]
+ values(): Collection  [API 1+]
```

## AbstractMap.SimpleEntry — TeaVM class library; API 9+ (8 +, 0 *, 0 ~, 0 -)

```
+ SimpleEntry(Map.Entry)  [API 9+]
+ SimpleEntry(Object, Object)  [API 9+]
+ equals(Object): boolean  [API 9+]
+ getKey(): Object  [API 9+]
+ getValue(): Object  [API 9+]
+ hashCode(): int  [API 9+]
+ setValue(Object): Object  [API 9+]
+ toString(): String  [API 9+]
```

## AbstractMap.SimpleImmutableEntry — TeaVM class library; API 9+ (8 +, 0 *, 0 ~, 0 -)

```
+ SimpleImmutableEntry(Map.Entry)  [API 9+]
+ SimpleImmutableEntry(Object, Object)  [API 9+]
+ equals(Object): boolean  [API 9+]
+ getKey(): Object  [API 9+]
+ getValue(): Object  [API 9+]
+ hashCode(): int  [API 9+]
+ setValue(Object): Object  [API 9+]
+ toString(): String  [API 9+]
```

## AbstractQueue — TeaVM class library; API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ AbstractQueue()  [API 1+]
+ add(Object): boolean  [API 1+]
+ addAll(Collection): boolean  [API 1+]
+ clear(): void  [API 1+]
+ element(): Object  [API 1+]
+ remove(): Object  [API 1+]
```

## AbstractSequentialList — TeaVM class library; API 1+ (8 +, 0 *, 0 ~, 0 -)

```
+ AbstractSequentialList()  [API 1+]
+ add(int, Object): void  [API 1+]
+ addAll(int, Collection): boolean  [API 1+]
+ get(int): Object  [API 1+]
+ iterator(): Iterator  [API 1+]
+ listIterator(int): ListIterator  [API 1+]
+ remove(int): Object  [API 1+]
+ set(int, Object): Object  [API 1+]
```

## AbstractSet — TeaVM class library; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ AbstractSet()  [API 1+]
+ equals(Object): boolean  [API 1+]
+ hashCode(): int  [API 1+]
+ removeAll(Collection): boolean  [API 1+]
```

## ArrayDeque — TeaVM class library; API 9+ (41 +, 0 *, 0 ~, 0 -)

```
+ ArrayDeque()  [API 9+]
+ ArrayDeque(Collection)  [API 9+]
+ ArrayDeque(int)  [API 9+]
+ add(Object): boolean  [API 9+]
+ addAll(Collection): boolean  [API 9+]
+ addFirst(Object): void  [API 9+]
+ addLast(Object): void  [API 9+]
+ clear(): void  [API 9+]
+ clone(): ArrayDeque  [API 9+]
+ contains(Object): boolean  [API 9+]
+ descendingIterator(): Iterator  [API 9+]
+ element(): Object  [API 9+]
+ forEach(Consumer): void  [API 9+]
+ getFirst(): Object  [API 9+]
+ getLast(): Object  [API 9+]
+ isEmpty(): boolean  [API 9+]
+ iterator(): Iterator  [API 9+]
+ offer(Object): boolean  [API 9+]
+ offerFirst(Object): boolean  [API 9+]
+ offerLast(Object): boolean  [API 9+]
+ peek(): Object  [API 9+]
+ peekFirst(): Object  [API 9+]
+ peekLast(): Object  [API 9+]
+ poll(): Object  [API 9+]
+ pollFirst(): Object  [API 9+]
+ pollLast(): Object  [API 9+]
+ pop(): Object  [API 9+]
+ push(Object): void  [API 9+]
+ remove(): Object  [API 9+]
+ remove(Object): boolean  [API 9+]
+ removeAll(Collection): boolean  [API 9+]
+ removeFirst(): Object  [API 9+]
+ removeFirstOccurrence(Object): boolean  [API 9+]
+ removeIf(Predicate): boolean  [API 9+]
+ removeLast(): Object  [API 9+]
+ removeLastOccurrence(Object): boolean  [API 9+]
+ retainAll(Collection): boolean  [API 9+]
+ size(): int  [API 9+]
+ spliterator(): Spliterator  [API 9+]
+ toArray(): Object[]  [API 9+]
+ toArray(Object[]): Object[]  [API 9+]
```

## ArrayList — TeaVM class library; API 1+ (42 +, 0 *, 0 ~, 0 -)

```
+ ArrayList()  [API 1+]
+ ArrayList(Collection)  [API 1+]
+ ArrayList(int)  [API 1+]
+ add(Object): boolean  [API 1+]
+ add(int, Object): void  [API 1+]
+ addAll(Collection): boolean  [API 1+]
+ addAll(int, Collection): boolean  [API 1+]
+ addFirst(Object): void  [API 1+]
+ addLast(Object): void  [API 1+]
+ clear(): void  [API 1+]
+ clone(): Object  [API 1+]
+ contains(Object): boolean  [API 1+]
+ ensureCapacity(int): void  [API 1+]
+ equals(Object): boolean  [API 1+]
+ forEach(Consumer): void  [API 1+]
+ get(int): Object  [API 1+]
+ getFirst(): Object  [API 1+]
+ getLast(): Object  [API 1+]
+ hashCode(): int  [API 1+]
+ indexOf(Object): int  [API 1+]
+ isEmpty(): boolean  [API 1+]
+ iterator(): Iterator  [API 1+]
+ lastIndexOf(Object): int  [API 1+]
+ listIterator(): ListIterator  [API 1+]
+ listIterator(int): ListIterator  [API 1+]
+ remove(Object): boolean  [API 1+]
+ remove(int): Object  [API 1+]
+ removeAll(Collection): boolean  [API 1+]
+ removeFirst(): Object  [API 1+]
+ removeIf(Predicate): boolean  [API 1+]
+ removeLast(): Object  [API 1+]
+ removeRange(int, int): void  [API 1+]
+ replaceAll(UnaryOperator): void  [API 1+]
+ retainAll(Collection): boolean  [API 1+]
+ set(int, Object): Object  [API 1+]
+ size(): int  [API 1+]
+ sort(Comparator): void  [API 1+]
+ spliterator(): Spliterator  [API 1+]
+ subList(int, int): List  [API 1+]
+ toArray(): Object[]  [API 1+]
+ toArray(Object[]): Object[]  [API 1+]
+ trimToSize(): void  [API 1+]
```

## Arrays — TeaVM class library; API 1+ (214 +, 0 *, 0 ~, 0 -)

```
+ asList(Object[]): List [static]  [API 1+]
+ binarySearch(Object[], Object): int [static]  [API 1+]
+ binarySearch(Object[], Object, Comparator): int [static]  [API 1+]
+ binarySearch(Object[], int, int, Object): int [static]  [API 9+]
+ binarySearch(Object[], int, int, Object, Comparator): int [static]  [API 9+]
+ binarySearch(byte[], byte): int [static]  [API 1+]
+ binarySearch(byte[], int, int, byte): int [static]  [API 9+]
+ binarySearch(char[], char): int [static]  [API 1+]
+ binarySearch(char[], int, int, char): int [static]  [API 9+]
+ binarySearch(double[], double): int [static]  [API 1+]
+ binarySearch(double[], int, int, double): int [static]  [API 9+]
+ binarySearch(float[], float): int [static]  [API 1+]
+ binarySearch(float[], int, int, float): int [static]  [API 9+]
+ binarySearch(int[], int): int [static]  [API 1+]
+ binarySearch(int[], int, int, int): int [static]  [API 9+]
+ binarySearch(long[], int, int, long): int [static]  [API 9+]
+ binarySearch(long[], long): int [static]  [API 1+]
+ binarySearch(short[], int, int, short): int [static]  [API 9+]
+ binarySearch(short[], short): int [static]  [API 1+]
+ compare(Comparable[], Comparable[]): int [static]  [API 33+]
+ compare(Comparable[], int, int, Comparable[], int, int): int [static]  [API 33+]
+ compare(Object[], Object[], Comparator): int [static]  [API 33+]
+ compare(Object[], int, int, Object[], int, int, Comparator): int [static]  [API 33+]
+ compare(boolean[], boolean[]): int [static]  [API 33+]
+ compare(boolean[], int, int, boolean[], int, int): int [static]  [API 33+]
+ compare(byte[], byte[]): int [static]  [API 33+]
+ compare(byte[], int, int, byte[], int, int): int [static]  [API 33+]
+ compare(char[], char[]): int [static]  [API 33+]
+ compare(char[], int, int, char[], int, int): int [static]  [API 33+]
+ compare(double[], double[]): int [static]  [API 33+]
+ compare(double[], int, int, double[], int, int): int [static]  [API 33+]
+ compare(float[], float[]): int [static]  [API 33+]
+ compare(float[], int, int, float[], int, int): int [static]  [API 33+]
+ compare(int[], int, int, int[], int, int): int [static]  [API 33+]
+ compare(int[], int[]): int [static]  [API 33+]
+ compare(long[], int, int, long[], int, int): int [static]  [API 33+]
+ compare(long[], long[]): int [static]  [API 33+]
+ compare(short[], int, int, short[], int, int): int [static]  [API 33+]
+ compare(short[], short[]): int [static]  [API 33+]
+ compareUnsigned(byte[], byte[]): int [static]  [API 33+]
+ compareUnsigned(byte[], int, int, byte[], int, int): int [static]  [API 33+]
+ compareUnsigned(int[], int, int, int[], int, int): int [static]  [API 33+]
+ compareUnsigned(int[], int[]): int [static]  [API 33+]
+ compareUnsigned(long[], int, int, long[], int, int): int [static]  [API 33+]
+ compareUnsigned(long[], long[]): int [static]  [API 33+]
+ compareUnsigned(short[], int, int, short[], int, int): int [static]  [API 33+]
+ compareUnsigned(short[], short[]): int [static]  [API 33+]
+ copyOf(Object[], int): Object[] [static]  [API 9+]
+ copyOf(Object[], int, Class): Object[] [static]  [API 9+]
+ copyOf(boolean[], int): boolean[] [static]  [API 9+]
+ copyOf(byte[], int): byte[] [static]  [API 9+]
+ copyOf(char[], int): char[] [static]  [API 9+]
+ copyOf(double[], int): double[] [static]  [API 9+]
+ copyOf(float[], int): float[] [static]  [API 9+]
+ copyOf(int[], int): int[] [static]  [API 9+]
+ copyOf(long[], int): long[] [static]  [API 9+]
+ copyOf(short[], int): short[] [static]  [API 9+]
+ copyOfRange(Object[], int, int): Object[] [static]  [API 9+]
+ copyOfRange(Object[], int, int, Class): Object[] [static]  [API 9+]
+ copyOfRange(boolean[], int, int): boolean[] [static]  [API 9+]
+ copyOfRange(byte[], int, int): byte[] [static]  [API 9+]
+ copyOfRange(char[], int, int): char[] [static]  [API 9+]
+ copyOfRange(double[], int, int): double[] [static]  [API 9+]
+ copyOfRange(float[], int, int): float[] [static]  [API 9+]
+ copyOfRange(int[], int, int): int[] [static]  [API 9+]
+ copyOfRange(long[], int, int): long[] [static]  [API 9+]
+ copyOfRange(short[], int, int): short[] [static]  [API 9+]
+ deepEquals(Object[], Object[]): boolean [static]  [API 1+]
+ deepHashCode(Object[]): int [static]  [API 1+]
+ deepToString(Object[]): String [static]  [API 1+]
+ equals(Object[], Object[]): boolean [static]  [API 1+]
+ equals(Object[], Object[], Comparator): boolean [static]  [API 33+]
+ equals(Object[], int, int, Object[], int, int): boolean [static]  [API 33+]
+ equals(Object[], int, int, Object[], int, int, Comparator): boolean [static]  [API 33+]
+ equals(boolean[], boolean[]): boolean [static]  [API 1+]
+ equals(boolean[], int, int, boolean[], int, int): boolean [static]  [API 33+]
+ equals(byte[], byte[]): boolean [static]  [API 1+]
+ equals(byte[], int, int, byte[], int, int): boolean [static]  [API 33+]
+ equals(char[], char[]): boolean [static]  [API 1+]
+ equals(char[], int, int, char[], int, int): boolean [static]  [API 33+]
+ equals(double[], double[]): boolean [static]  [API 1+]
+ equals(double[], int, int, double[], int, int): boolean [static]  [API 33+]
+ equals(float[], float[]): boolean [static]  [API 1+]
+ equals(float[], int, int, float[], int, int): boolean [static]  [API 33+]
+ equals(int[], int, int, int[], int, int): boolean [static]  [API 33+]
+ equals(int[], int[]): boolean [static]  [API 1+]
+ equals(long[], int, int, long[], int, int): boolean [static]  [API 33+]
+ equals(long[], long[]): boolean [static]  [API 1+]
+ equals(short[], int, int, short[], int, int): boolean [static]  [API 33+]
+ equals(short[], short[]): boolean [static]  [API 1+]
+ fill(Object[], Object): void [static]  [API 1+]
+ fill(Object[], int, int, Object): void [static]  [API 1+]
+ fill(boolean[], boolean): void [static]  [API 1+]
+ fill(boolean[], int, int, boolean): void [static]  [API 1+]
+ fill(byte[], byte): void [static]  [API 1+]
+ fill(byte[], int, int, byte): void [static]  [API 1+]
+ fill(char[], char): void [static]  [API 1+]
+ fill(char[], int, int, char): void [static]  [API 1+]
+ fill(double[], double): void [static]  [API 1+]
+ fill(double[], int, int, double): void [static]  [API 1+]
+ fill(float[], float): void [static]  [API 1+]
+ fill(float[], int, int, float): void [static]  [API 1+]
+ fill(int[], int): void [static]  [API 1+]
+ fill(int[], int, int, int): void [static]  [API 1+]
+ fill(long[], int, int, long): void [static]  [API 1+]
+ fill(long[], long): void [static]  [API 1+]
+ fill(short[], int, int, short): void [static]  [API 1+]
+ fill(short[], short): void [static]  [API 1+]
+ hashCode(Object[]): int [static]  [API 1+]
+ hashCode(boolean[]): int [static]  [API 1+]
+ hashCode(byte[]): int [static]  [API 1+]
+ hashCode(char[]): int [static]  [API 1+]
+ hashCode(double[]): int [static]  [API 1+]
+ hashCode(float[]): int [static]  [API 1+]
+ hashCode(int[]): int [static]  [API 1+]
+ hashCode(long[]): int [static]  [API 1+]
+ hashCode(short[]): int [static]  [API 1+]
+ mismatch(Object[], Object[]): int [static]  [API 33+]
+ mismatch(Object[], Object[], Comparator): int [static]  [API 33+]
+ mismatch(Object[], int, int, Object[], int, int): int [static]  [API 33+]
+ mismatch(Object[], int, int, Object[], int, int, Comparator): int [static]  [API 33+]
+ mismatch(boolean[], boolean[]): int [static]  [API 33+]
+ mismatch(boolean[], int, int, boolean[], int, int): int [static]  [API 33+]
+ mismatch(byte[], byte[]): int [static]  [API 33+]
+ mismatch(byte[], int, int, byte[], int, int): int [static]  [API 33+]
+ mismatch(char[], char[]): int [static]  [API 33+]
+ mismatch(char[], int, int, char[], int, int): int [static]  [API 33+]
+ mismatch(double[], double[]): int [static]  [API 33+]
+ mismatch(double[], int, int, double[], int, int): int [static]  [API 33+]
+ mismatch(float[], float[]): int [static]  [API 33+]
+ mismatch(float[], int, int, float[], int, int): int [static]  [API 33+]
+ mismatch(int[], int, int, int[], int, int): int [static]  [API 33+]
+ mismatch(int[], int[]): int [static]  [API 33+]
+ mismatch(long[], int, int, long[], int, int): int [static]  [API 33+]
+ mismatch(long[], long[]): int [static]  [API 33+]
+ mismatch(short[], int, int, short[], int, int): int [static]  [API 33+]
+ mismatch(short[], short[]): int [static]  [API 33+]
+ parallelPrefix(Object[], BinaryOperator): void [static]  [API 24+]
+ parallelPrefix(Object[], int, int, BinaryOperator): void [static]  [API 24+]
+ parallelPrefix(double[], DoubleBinaryOperator): void [static]  [API 24+]
+ parallelPrefix(double[], int, int, DoubleBinaryOperator): void [static]  [API 24+]
+ parallelPrefix(int[], IntBinaryOperator): void [static]  [API 24+]
+ parallelPrefix(int[], int, int, IntBinaryOperator): void [static]  [API 24+]
+ parallelPrefix(long[], LongBinaryOperator): void [static]  [API 24+]
+ parallelPrefix(long[], int, int, LongBinaryOperator): void [static]  [API 24+]
+ parallelSetAll(Object[], IntFunction): void [static]  [API 24+]
+ parallelSetAll(double[], IntToDoubleFunction): void [static]  [API 24+]
+ parallelSetAll(int[], IntUnaryOperator): void [static]  [API 24+]
+ parallelSetAll(long[], IntToLongFunction): void [static]  [API 24+]
+ parallelSort(Comparable[]): void [static]  [API 24+]
+ parallelSort(Comparable[], int, int): void [static]  [API 24+]
+ parallelSort(Object[], Comparator): void [static]  [API 24+]
+ parallelSort(Object[], int, int, Comparator): void [static]  [API 24+]
+ parallelSort(byte[]): void [static]  [API 24+]
+ parallelSort(byte[], int, int): void [static]  [API 24+]
+ parallelSort(char[]): void [static]  [API 24+]
+ parallelSort(char[], int, int): void [static]  [API 24+]
+ parallelSort(double[]): void [static]  [API 24+]
+ parallelSort(double[], int, int): void [static]  [API 24+]
+ parallelSort(float[]): void [static]  [API 24+]
+ parallelSort(float[], int, int): void [static]  [API 24+]
+ parallelSort(int[]): void [static]  [API 24+]
+ parallelSort(int[], int, int): void [static]  [API 24+]
+ parallelSort(long[]): void [static]  [API 24+]
+ parallelSort(long[], int, int): void [static]  [API 24+]
+ parallelSort(short[]): void [static]  [API 24+]
+ parallelSort(short[], int, int): void [static]  [API 24+]
+ setAll(Object[], IntFunction): void [static]  [API 24+]
+ setAll(double[], IntToDoubleFunction): void [static]  [API 24+]
+ setAll(int[], IntUnaryOperator): void [static]  [API 24+]
+ setAll(long[], IntToLongFunction): void [static]  [API 24+]
+ sort(Object[]): void [static]  [API 1+]
+ sort(Object[], Comparator): void [static]  [API 1+]
+ sort(Object[], int, int): void [static]  [API 1+]
+ sort(Object[], int, int, Comparator): void [static]  [API 1+]
+ sort(byte[]): void [static]  [API 1+]
+ sort(byte[], int, int): void [static]  [API 1+]
+ sort(char[]): void [static]  [API 1+]
+ sort(char[], int, int): void [static]  [API 1+]
+ sort(double[]): void [static]  [API 1+]
+ sort(double[], int, int): void [static]  [API 1+]
+ sort(float[]): void [static]  [API 1+]
+ sort(float[], int, int): void [static]  [API 1+]
+ sort(int[]): void [static]  [API 1+]
+ sort(int[], int, int): void [static]  [API 1+]
+ sort(long[]): void [static]  [API 1+]
+ sort(long[], int, int): void [static]  [API 1+]
+ sort(short[]): void [static]  [API 1+]
+ sort(short[], int, int): void [static]  [API 1+]
+ spliterator(Object[]): Spliterator [static]  [API 24+]
+ spliterator(Object[], int, int): Spliterator [static]  [API 24+]
+ spliterator(double[]): Spliterator.OfDouble [static]  [API 24+]
+ spliterator(double[], int, int): Spliterator.OfDouble [static]  [API 24+]
+ spliterator(int[]): Spliterator.OfInt [static]  [API 24+]
+ spliterator(int[], int, int): Spliterator.OfInt [static]  [API 24+]
+ spliterator(long[]): Spliterator.OfLong [static]  [API 24+]
+ spliterator(long[], int, int): Spliterator.OfLong [static]  [API 24+]
+ stream(Object[]): Stream [static]  [API 24+]
+ stream(Object[], int, int): Stream [static]  [API 24+]
+ stream(double[]): DoubleStream [static]  [API 24+]
+ stream(double[], int, int): DoubleStream [static]  [API 24+]
+ stream(int[]): IntStream [static]  [API 24+]
+ stream(int[], int, int): IntStream [static]  [API 24+]
+ stream(long[]): LongStream [static]  [API 24+]
+ stream(long[], int, int): LongStream [static]  [API 24+]
+ toString(Object[]): String [static]  [API 1+]
+ toString(boolean[]): String [static]  [API 1+]
+ toString(byte[]): String [static]  [API 1+]
+ toString(char[]): String [static]  [API 1+]
+ toString(double[]): String [static]  [API 1+]
+ toString(float[]): String [static]  [API 1+]
+ toString(int[]): String [static]  [API 1+]
+ toString(long[]): String [static]  [API 1+]
+ toString(short[]): String [static]  [API 1+]
```

## Base64 — TeaVM class library; API 26+ (4 +, 0 *, 0 ~, 3 -)

```
+ getDecoder(): Base64.Decoder [static]  [API 26+]
+ getEncoder(): Base64.Encoder [static]  [API 26+]
- getMimeDecoder(): Base64.Decoder [static]  [API 26+]
- getMimeEncoder(): Base64.Encoder [static]  [API 26+]
- getMimeEncoder(int, byte[]): Base64.Encoder [static]  [API 26+]
+ getUrlDecoder(): Base64.Decoder [static]  [API 26+]
+ getUrlEncoder(): Base64.Encoder [static]  [API 26+]
```

## Base64.Decoder — TeaVM class library; API 26+ (5 +, 0 *, 0 ~, 0 -)

```
+ decode(ByteBuffer): ByteBuffer  [API 26+]
+ decode(String): byte[]  [API 26+]
+ decode(byte[]): byte[]  [API 26+]
+ decode(byte[], byte[]): int  [API 26+]
+ wrap(InputStream): InputStream  [API 26+]
```

## Base64.Encoder — TeaVM class library; API 26+ (6 +, 0 *, 0 ~, 0 -)

```
+ encode(ByteBuffer): ByteBuffer  [API 26+]
+ encode(byte[]): byte[]  [API 26+]
+ encode(byte[], byte[]): int  [API 26+]
+ encodeToString(byte[]): String  [API 26+]
+ withoutPadding(): Base64.Encoder  [API 26+]
+ wrap(OutputStream): OutputStream  [API 26+]
```

## BitSet — TeaVM class library; API 1+ (37 +, 0 *, 0 ~, 0 -)

```
+ BitSet()  [API 1+]
+ BitSet(int)  [API 1+]
+ and(BitSet): void  [API 1+]
+ andNot(BitSet): void  [API 1+]
+ cardinality(): int  [API 1+]
+ clear(): void  [API 1+]
+ clear(int): void  [API 1+]
+ clear(int, int): void  [API 1+]
+ clone(): Object  [API 1+]
+ equals(Object): boolean  [API 1+]
+ flip(int): void  [API 1+]
+ flip(int, int): void  [API 1+]
+ get(int): boolean  [API 1+]
+ get(int, int): BitSet  [API 1+]
+ hashCode(): int  [API 1+]
+ intersects(BitSet): boolean  [API 1+]
+ isEmpty(): boolean  [API 1+]
+ length(): int  [API 1+]
+ nextClearBit(int): int  [API 1+]
+ nextSetBit(int): int  [API 1+]
+ or(BitSet): void  [API 1+]
+ previousClearBit(int): int  [API 19+]
+ previousSetBit(int): int  [API 19+]
+ set(int): void  [API 1+]
+ set(int, boolean): void  [API 1+]
+ set(int, int): void  [API 1+]
+ set(int, int, boolean): void  [API 1+]
+ size(): int  [API 1+]
+ stream(): IntStream  [API 24+]
+ toByteArray(): byte[]  [API 19+]
+ toLongArray(): long[]  [API 19+]
+ toString(): String  [API 1+]
+ valueOf(ByteBuffer): BitSet [static]  [API 19+]
+ valueOf(LongBuffer): BitSet [static]  [API 19+]
+ valueOf(byte[]): BitSet [static]  [API 19+]
+ valueOf(long[]): BitSet [static]  [API 19+]
+ xor(BitSet): void  [API 1+]
```

## Calendar — TeaVM class library; API 1+ (56 +, 0 *, 0 ~, 0 -)

```
+ Calendar()  [API 1+]
+ Calendar(TimeZone, Locale)  [API 1+]
+ add(int, int): void  [API 1+]
+ after(Object): boolean  [API 1+]
+ areFieldsSet [field]  [API 1+]
+ before(Object): boolean  [API 1+]
+ clear(): void  [API 1+]
+ clear(int): void  [API 1+]
+ clone(): Object  [API 1+]
+ compareTo(Calendar): int  [API 1+]
+ complete(): void  [API 1+]
+ computeFields(): void  [API 1+]
+ computeTime(): void  [API 1+]
+ equals(Object): boolean  [API 1+]
+ fields [field]  [API 1+]
+ get(int): int  [API 1+]
+ getActualMaximum(int): int  [API 1+]
+ getActualMinimum(int): int  [API 1+]
+ getAvailableCalendarTypes(): Set [static]  [API 26+]
+ getAvailableLocales(): Locale[] [static]  [API 1+]
+ getCalendarType(): String  [API 26+]
+ getDisplayName(int, int, Locale): String  [API 9+]
+ getDisplayNames(int, int, Locale): Map  [API 9+]
+ getFirstDayOfWeek(): int  [API 1+]
+ getGreatestMinimum(int): int  [API 1+]
+ getInstance(): Calendar [static]  [API 1+]
+ getInstance(Locale): Calendar [static]  [API 1+]
+ getInstance(TimeZone): Calendar [static]  [API 1+]
+ getInstance(TimeZone, Locale): Calendar [static]  [API 1+]
+ getLeastMaximum(int): int  [API 1+]
+ getMaximum(int): int  [API 1+]
+ getMinimalDaysInFirstWeek(): int  [API 1+]
+ getMinimum(int): int  [API 1+]
+ getTime(): Date  [API 1+]
+ getTimeInMillis(): long  [API 1+]
+ getTimeZone(): TimeZone  [API 1+]
+ getWeekYear(): int  [API 24+]
+ getWeeksInWeekYear(): int  [API 24+]
+ hashCode(): int  [API 1+]
+ internalGet(int): int  [API 1+]
+ isLenient(): boolean  [API 1+]
+ isSet [field]  [API 1+]
+ isSet(int): boolean  [API 1+]
+ isTimeSet [field]  [API 1+]
+ isWeekDateSupported(): boolean  [API 24+]
+ roll(int, boolean): void  [API 1+]
+ roll(int, int): void  [API 1+]
+ set(int, int): void  [API 1+]
+ set(int, int, int): void  [API 1+]
+ set(int, int, int, int, int): void  [API 1+]
+ set(int, int, int, int, int, int): void  [API 1+]
+ setFirstDayOfWeek(int): void  [API 1+]
+ setLenient(boolean): void  [API 1+]
+ setMinimalDaysInFirstWeek(int): void  [API 1+]
+ setTime(Date): void  [API 1+]
+ setTimeInMillis(long): void  [API 1+]
+ setTimeZone(TimeZone): void  [API 1+]
+ setWeekDate(int, int, int): void  [API 24+]
+ time [field]  [API 1+]
+ toInstant(): Instant  [API 26+]
+ toString(): String  [API 1+]
```

## Calendar.Builder — declaration; API 26+ (0 +, 0 *, 0 ~, 15 -)

```
- Builder()  [API 26+]
- build(): Calendar  [API 26+]
- set(int, int): Calendar.Builder  [API 26+]
- setCalendarType(String): Calendar.Builder  [API 26+]
- setDate(int, int, int): Calendar.Builder  [API 26+]
- setFields(int[]): Calendar.Builder  [API 26+]
- setInstant(Date): Calendar.Builder  [API 26+]
- setInstant(long): Calendar.Builder  [API 26+]
- setLenient(boolean): Calendar.Builder  [API 26+]
- setLocale(Locale): Calendar.Builder  [API 26+]
- setTimeOfDay(int, int, int): Calendar.Builder  [API 26+]
- setTimeOfDay(int, int, int, int): Calendar.Builder  [API 26+]
- setTimeZone(TimeZone): Calendar.Builder  [API 26+]
- setWeekDate(int, int, int): Calendar.Builder  [API 26+]
- setWeekDefinition(int, int): Calendar.Builder  [API 26+]
```

## Collection — TeaVM class library; API 1+ (19 +, 0 *, 0 ~, 1 -)

```
+ add(Object): boolean  [API 1+]
+ addAll(Collection): boolean  [API 1+]
+ clear(): void  [API 1+]
+ contains(Object): boolean  [API 1+]
+ containsAll(Collection): boolean  [API 1+]
+ equals(Object): boolean  [API 1+]
+ hashCode(): int  [API 1+]
+ isEmpty(): boolean  [API 1+]
+ iterator(): Iterator  [API 1+]
- parallelStream(): Stream  [API 24+]
+ remove(Object): boolean  [API 1+]
+ removeAll(Collection): boolean  [API 1+]
+ removeIf(Predicate): boolean  [API 24+]
+ retainAll(Collection): boolean  [API 1+]
+ size(): int  [API 1+]
+ spliterator(): Spliterator  [API 1+]
+ stream(): Stream  [API 24+]
+ toArray(): Object[]  [API 1+]
+ toArray(IntFunction): Object[]  [API 33+]
+ toArray(Object[]): Object[]  [API 1+]
```

## Collections — TeaVM class library; API 1+ (68 +, 0 *, 0 ~, 3 -)

```
+ EMPTY_LIST [field]  [API 1+]
+ EMPTY_MAP [field]  [API 1+]
+ EMPTY_SET [field]  [API 1+]
+ addAll(Collection, Object[]): boolean [static]  [API 1+]
+ asLifoQueue(Deque): Queue [static]  [API 9+]
+ binarySearch(List, Object): int [static]  [API 1+]
+ binarySearch(List, Object, Comparator): int [static]  [API 1+]
+ checkedCollection(Collection, Class): Collection [static]  [API 1+]
+ checkedList(List, Class): List [static]  [API 1+]
+ checkedMap(Map, Class, Class): Map [static]  [API 1+]
+ checkedNavigableMap(NavigableMap, Class, Class): NavigableMap [static]  [API 26+]
+ checkedNavigableSet(NavigableSet, Class): NavigableSet [static]  [API 26+]
+ checkedQueue(Queue, Class): Queue [static]  [API 26+]
+ checkedSet(Set, Class): Set [static]  [API 1+]
+ checkedSortedMap(SortedMap, Class, Class): SortedMap [static]  [API 1+]
+ checkedSortedSet(SortedSet, Class): SortedSet [static]  [API 1+]
+ copy(List, List): void [static]  [API 1+]
+ disjoint(Collection, Collection): boolean [static]  [API 1+]
+ emptyEnumeration(): Enumeration [static]  [API 19+]
+ emptyIterator(): Iterator [static]  [API 19+]
+ emptyList(): List [static]  [API 1+]
+ emptyListIterator(): ListIterator [static]  [API 19+]
+ emptyMap(): Map [static]  [API 1+]
+ emptyNavigableMap(): NavigableMap [static]  [API 26+]
+ emptyNavigableSet(): NavigableSet [static]  [API 26+]
+ emptySet(): Set [static]  [API 1+]
+ emptySortedMap(): SortedMap [static]  [API 26+]
+ emptySortedSet(): SortedSet [static]  [API 26+]
+ enumeration(Collection): Enumeration [static]  [API 1+]
+ fill(List, Object): void [static]  [API 1+]
+ frequency(Collection, Object): int [static]  [API 1+]
+ indexOfSubList(List, List): int [static]  [API 1+]
+ lastIndexOfSubList(List, List): int [static]  [API 1+]
+ list(Enumeration): ArrayList [static]  [API 1+]
+ max(Collection): Object [static]  [API 1+]
+ max(Collection, Comparator): Object [static]  [API 1+]
+ min(Collection): Object [static]  [API 1+]
+ min(Collection, Comparator): Object [static]  [API 1+]
+ nCopies(int, Object): List [static]  [API 1+]
+ newSequencedSetFromMap(SequencedMap): SequencedSet [static]  [API 35+]
+ newSetFromMap(Map): Set [static]  [API 9+]
+ replaceAll(List, Object, Object): boolean [static]  [API 1+]
+ reverse(List): void [static]  [API 1+]
+ reverseOrder(): Comparator [static]  [API 1+]
+ reverseOrder(Comparator): Comparator [static]  [API 1+]
+ rotate(List, int): void [static]  [API 1+]
+ shuffle(List): void [static]  [API 1+]
+ shuffle(List, Random): void [static]  [API 1+]
+ shuffle(List, RandomGenerator): void [static]  [API 35+]
+ singleton(Object): Set [static]  [API 1+]
+ singletonList(Object): List [static]  [API 1+]
+ singletonMap(Object, Object): Map [static]  [API 1+]
+ sort(List): void [static]  [API 1+]
+ sort(List, Comparator): void [static]  [API 1+]
+ swap(List, int, int): void [static]  [API 1+]
+ synchronizedCollection(Collection): Collection [static]  [API 1+]
+ synchronizedList(List): List [static]  [API 1+]
+ synchronizedMap(Map): Map [static]  [API 1+]
+ synchronizedNavigableMap(NavigableMap): NavigableMap [static]  [API 26+]
+ synchronizedNavigableSet(NavigableSet): NavigableSet [static]  [API 26+]
+ synchronizedSet(Set): Set [static]  [API 1+]
+ synchronizedSortedMap(SortedMap): SortedMap [static]  [API 1+]
+ synchronizedSortedSet(SortedSet): SortedSet [static]  [API 1+]
+ unmodifiableCollection(Collection): Collection [static]  [API 1+]
+ unmodifiableList(List): List [static]  [API 1+]
+ unmodifiableMap(Map): Map [static]  [API 1+]
+ unmodifiableNavigableMap(NavigableMap): NavigableMap [static]  [API 26+]
+ unmodifiableNavigableSet(NavigableSet): NavigableSet [static]  [API 26+]
- unmodifiableSequencedCollection(SequencedCollection): SequencedCollection [static]  [API 35+]
- unmodifiableSequencedMap(SequencedMap): SequencedMap [static]  [API 35+]
- unmodifiableSequencedSet(SequencedSet): SequencedSet [static]  [API 35+]
+ unmodifiableSet(Set): Set [static]  [API 1+]
+ unmodifiableSortedMap(SortedMap): SortedMap [static]  [API 1+]
+ unmodifiableSortedSet(SortedSet): SortedSet [static]  [API 1+]
```

## Comparator — TeaVM class library; API 1+ (18 +, 0 *, 0 ~, 0 -)

```
+ compare(Object, Object): int  [API 1+]
+ comparing(Function): Comparator [static]  [API 24+]
+ comparing(Function, Comparator): Comparator [static]  [API 24+]
+ comparingDouble(ToDoubleFunction): Comparator [static]  [API 24+]
+ comparingInt(ToIntFunction): Comparator [static]  [API 24+]
+ comparingLong(ToLongFunction): Comparator [static]  [API 24+]
+ equals(Object): boolean  [API 1+]
+ naturalOrder(): Comparator [static]  [API 24+]
+ nullsFirst(Comparator): Comparator [static]  [API 24+]
+ nullsLast(Comparator): Comparator [static]  [API 24+]
+ reverseOrder(): Comparator [static]  [API 24+]
+ reversed(): Comparator  [API 24+]
+ thenComparing(Comparator): Comparator  [API 24+]
+ thenComparing(Function): Comparator  [API 24+]
+ thenComparing(Function, Comparator): Comparator  [API 24+]
+ thenComparingDouble(ToDoubleFunction): Comparator  [API 24+]
+ thenComparingInt(ToIntFunction): Comparator  [API 24+]
+ thenComparingLong(ToLongFunction): Comparator  [API 24+]
```

## ConcurrentModificationException — TeaVM class library; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ ConcurrentModificationException()  [API 1+]
+ ConcurrentModificationException(String)  [API 1+]
+ ConcurrentModificationException(String, Throwable)  [API 19+]
+ ConcurrentModificationException(Throwable)  [API 19+]
```

## Currency — TeaVM class library; API 1+ (11 +, 0 *, 0 ~, 1 -)

```
+ getAvailableCurrencies(): Set [static]  [API 19+]
+ getCurrencyCode(): String  [API 1+]
+ getDefaultFractionDigits(): int  [API 1+]
+ getDisplayName(): String  [API 19+]
+ getDisplayName(Locale): String  [API 19+]
+ getInstance(Locale): Currency [static]  [API 1+]
+ getInstance(String): Currency [static]  [API 1+]
+ getNumericCode(): int  [API 24+]
- getNumericCodeAsString(): String  [API 34+]
+ getSymbol(): String  [API 1+]
+ getSymbol(Locale): String  [API 1+]
+ toString(): String  [API 1+]
```

## Date — TeaVM class library; API 1+ (35 +, 0 *, 0 ~, 0 -)

```
+ Date()  [API 1+]
+ Date(String)  [API 1+, deprecated in 15]
+ Date(int, int, int)  [API 1+, deprecated in 15]
+ Date(int, int, int, int, int)  [API 1+, deprecated in 15]
+ Date(int, int, int, int, int, int)  [API 1+, deprecated in 15]
+ Date(long)  [API 1+]
+ UTC(int, int, int, int, int, int): long [static]  [API 1+, deprecated in 15]
+ after(Date): boolean  [API 1+]
+ before(Date): boolean  [API 1+]
+ clone(): Object  [API 1+]
+ compareTo(Date): int  [API 1+]
+ equals(Object): boolean  [API 1+]
+ from(Instant): Date [static]  [API 26+]
+ getDate(): int  [API 1+, deprecated in 15]
+ getDay(): int  [API 1+, deprecated in 15]
+ getHours(): int  [API 1+, deprecated in 15]
+ getMinutes(): int  [API 1+, deprecated in 15]
+ getMonth(): int  [API 1+, deprecated in 15]
+ getSeconds(): int  [API 1+, deprecated in 15]
+ getTime(): long  [API 1+]
+ getTimezoneOffset(): int  [API 1+, deprecated in 15]
+ getYear(): int  [API 1+, deprecated in 15]
+ hashCode(): int  [API 1+]
+ parse(String): long [static]  [API 1+, deprecated in 15]
+ setDate(int): void  [API 1+, deprecated in 15]
+ setHours(int): void  [API 1+, deprecated in 15]
+ setMinutes(int): void  [API 1+, deprecated in 15]
+ setMonth(int): void  [API 1+, deprecated in 15]
+ setSeconds(int): void  [API 1+, deprecated in 15]
+ setTime(long): void  [API 1+]
+ setYear(int): void  [API 1+, deprecated in 15]
+ toGMTString(): String  [API 1+, deprecated in 15]
+ toInstant(): Instant  [API 26+]
+ toLocaleString(): String  [API 1+, deprecated in 15]
+ toString(): String  [API 1+]
```

## Deque — TeaVM class library; API 9+ (29 +, 0 *, 0 ~, 0 -)

```
+ add(Object): boolean  [API 9+]
+ addAll(Collection): boolean  [API 9+]
+ addFirst(Object): void  [API 9+]
+ addLast(Object): void  [API 9+]
+ contains(Object): boolean  [API 9+]
+ descendingIterator(): Iterator  [API 9+]
+ element(): Object  [API 9+]
+ getFirst(): Object  [API 9+]
+ getLast(): Object  [API 9+]
+ iterator(): Iterator  [API 9+]
+ offer(Object): boolean  [API 9+]
+ offerFirst(Object): boolean  [API 9+]
+ offerLast(Object): boolean  [API 9+]
+ peek(): Object  [API 9+]
+ peekFirst(): Object  [API 9+]
+ peekLast(): Object  [API 9+]
+ poll(): Object  [API 9+]
+ pollFirst(): Object  [API 9+]
+ pollLast(): Object  [API 9+]
+ pop(): Object  [API 9+]
+ push(Object): void  [API 9+]
+ remove(): Object  [API 9+]
+ remove(Object): boolean  [API 9+]
+ removeFirst(): Object  [API 9+]
+ removeFirstOccurrence(Object): boolean  [API 9+]
+ removeLast(): Object  [API 9+]
+ removeLastOccurrence(Object): boolean  [API 9+]
+ reversed(): Deque  [API 35+]
+ size(): int  [API 9+]
```

## Dictionary — TeaVM class library; API 1+ (8 +, 0 *, 0 ~, 0 -)

```
+ Dictionary()  [API 1+]
+ elements(): Enumeration  [API 1+]
+ get(Object): Object  [API 1+]
+ isEmpty(): boolean  [API 1+]
+ keys(): Enumeration  [API 1+]
+ put(Object, Object): Object  [API 1+]
+ remove(Object): Object  [API 1+]
+ size(): int  [API 1+]
```

## DoubleSummaryStatistics — TeaVM class library; API 24+ (10 +, 0 *, 0 ~, 0 -)

```
+ DoubleSummaryStatistics()  [API 24+]
+ DoubleSummaryStatistics(long, double, double, double)  [API 34+]
+ accept(double): void  [API 24+]
+ combine(DoubleSummaryStatistics): void  [API 24+]
+ getAverage(): double  [API 24+]
+ getCount(): long  [API 24+]
+ getMax(): double  [API 24+]
+ getMin(): double  [API 24+]
+ getSum(): double  [API 24+]
+ toString(): String  [API 24+]
```

## DuplicateFormatFlagsException — TeaVM class library; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ DuplicateFormatFlagsException(String)  [API 1+]
+ getFlags(): String  [API 1+]
+ getMessage(): String  [API 1+]
```

## EmptyStackException — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ EmptyStackException()  [API 1+]
```

## EnumMap — TeaVM class library; API 1+ (17 +, 0 *, 0 ~, 0 -)

```
+ EnumMap(Class)  [API 1+]
+ EnumMap(EnumMap)  [API 1+]
+ EnumMap(Map)  [API 1+]
+ clear(): void  [API 1+]
+ clone(): EnumMap  [API 1+]
+ containsKey(Object): boolean  [API 1+]
+ containsValue(Object): boolean  [API 1+]
+ entrySet(): Set  [API 1+]
+ equals(Object): boolean  [API 1+]
+ get(Object): Object  [API 1+]
+ hashCode(): int  [API 1+]
+ keySet(): Set  [API 1+]
+ put(Enum, Object): Object  [API 1+]
+ putAll(Map): void  [API 1+]
+ remove(Object): Object  [API 1+]
+ size(): int  [API 1+]
+ values(): Collection  [API 1+]
```

## EnumSet — TeaVM class library; API 1+ (13 +, 0 *, 0 ~, 0 -)

```
+ allOf(Class): EnumSet [static]  [API 1+]
+ clone(): EnumSet  [API 1+]
+ complementOf(EnumSet): EnumSet [static]  [API 1+]
+ copyOf(Collection): EnumSet [static]  [API 1+]
+ copyOf(EnumSet): EnumSet [static]  [API 1+]
+ noneOf(Class): EnumSet [static]  [API 1+]
+ of(Enum): EnumSet [static]  [API 1+]
+ of(Enum, Enum): EnumSet [static]  [API 1+]
+ of(Enum, Enum, Enum): EnumSet [static]  [API 1+]
+ of(Enum, Enum, Enum, Enum): EnumSet [static]  [API 1+]
+ of(Enum, Enum, Enum, Enum, Enum): EnumSet [static]  [API 1+]
+ of(Enum, Enum[]): EnumSet [static]  [API 1+]
+ range(Enum, Enum): EnumSet [static]  [API 1+]
```

## Enumeration — TeaVM class library; API 1+ (2 +, 0 *, 0 ~, 1 -)

```
- asIterator(): Iterator  [API 33+]
+ hasMoreElements(): boolean  [API 1+]
+ nextElement(): Object  [API 1+]
```

## EventListener — TeaVM class library; API 1+ (0 +, 0 *, 0 ~, 0 -)

## EventListenerProxy — declaration; API 1+ (0 +, 2 *, 0 ~, 0 -)

```
* EventListenerProxy(EventListener)  [API 1+]
* getListener(): EventListener  [API 1+]
```

## EventObject — TeaVM class library; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ EventObject(Object)  [API 1+]
+ getSource(): Object  [API 1+]
+ source [field]  [API 1+]
+ toString(): String  [API 1+]
```

## FormatFlagsConversionMismatchException — TeaVM class library; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ FormatFlagsConversionMismatchException(String, char)  [API 1+]
+ getConversion(): char  [API 1+]
+ getFlags(): String  [API 1+]
+ getMessage(): String  [API 1+]
```

## Formattable — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ formatTo(Formatter, int, int, int): void  [API 1+]
```

## FormattableFlags — TeaVM class library; API 1+ (0 +, 0 *, 0 ~, 0 -)

## Formatter — TeaVM class library; API 1+ (16 +, 0 *, 0 ~, 9 -)

```
+ Formatter()  [API 1+]
+ Formatter(Appendable)  [API 1+]
+ Formatter(Appendable, Locale)  [API 1+]
- Formatter(File)  [API 1+]
- Formatter(File, Charset, Locale)  [API 34+]
- Formatter(File, String)  [API 1+]
- Formatter(File, String, Locale)  [API 1+]
+ Formatter(Locale)  [API 1+]
+ Formatter(OutputStream)  [API 1+]
- Formatter(OutputStream, Charset, Locale)  [API 34+]
+ Formatter(OutputStream, String)  [API 1+]
+ Formatter(OutputStream, String, Locale)  [API 1+]
+ Formatter(PrintStream)  [API 1+]
- Formatter(String)  [API 1+]
- Formatter(String, Charset, Locale)  [API 34+]
- Formatter(String, String)  [API 1+]
- Formatter(String, String, Locale)  [API 1+]
+ close(): void  [API 1+]
+ flush(): void  [API 1+]
+ format(Locale, String, Object[]): Formatter  [API 1+]
+ format(String, Object[]): Formatter  [API 1+]
+ ioException(): IOException  [API 1+]
+ locale(): Locale  [API 1+]
+ out(): Appendable  [API 1+]
+ toString(): String  [API 1+]
```

## Formatter.BigDecimalLayoutForm — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ DECIMAL_FLOAT [field]  [API 1+]
+ SCIENTIFIC [field]  [API 1+]
+ valueOf(String): Formatter.BigDecimalLayoutForm [static]  [API 1+]
+ values(): Formatter.BigDecimalLayoutForm[] [static]  [API 1+]
```

## FormatterClosedException — TeaVM class library; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ FormatterClosedException()  [API 1+]
```

## GregorianCalendar — TeaVM class library; API 1+ (33 +, 0 *, 0 ~, 0 -)

```
+ GregorianCalendar()  [API 1+]
+ GregorianCalendar(Locale)  [API 1+]
+ GregorianCalendar(TimeZone)  [API 1+]
+ GregorianCalendar(TimeZone, Locale)  [API 1+]
+ GregorianCalendar(int, int, int)  [API 1+]
+ GregorianCalendar(int, int, int, int, int)  [API 1+]
+ GregorianCalendar(int, int, int, int, int, int)  [API 1+]
+ add(int, int): void  [API 1+]
+ clone(): Object  [API 1+]
+ computeFields(): void  [API 1+]
+ computeTime(): void  [API 1+]
+ equals(Object): boolean  [API 1+]
+ from(ZonedDateTime): GregorianCalendar [static]  [API 26+]
+ getActualMaximum(int): int  [API 1+]
+ getActualMinimum(int): int  [API 1+]
+ getCalendarType(): String  [API 1+]
+ getGreatestMinimum(int): int  [API 1+]
+ getGregorianChange(): Date  [API 1+]
+ getLeastMaximum(int): int  [API 1+]
+ getMaximum(int): int  [API 1+]
+ getMinimum(int): int  [API 1+]
+ getTimeZone(): TimeZone  [API 1+]
+ getWeekYear(): int  [API 1+]
+ getWeeksInWeekYear(): int  [API 1+]
+ hashCode(): int  [API 1+]
+ isLeapYear(int): boolean  [API 1+]
+ isWeekDateSupported(): boolean  [API 1+]
+ roll(int, boolean): void  [API 1+]
+ roll(int, int): void  [API 1+]
+ setGregorianChange(Date): void  [API 1+]
+ setTimeZone(TimeZone): void  [API 1+]
+ setWeekDate(int, int, int): void  [API 1+]
+ toZonedDateTime(): ZonedDateTime  [API 26+]
```

## HashMap — TeaVM class library; API 1+ (29 +, 0 *, 0 ~, 0 -)

```
+ HashMap()  [API 1+]
+ HashMap(Map)  [API 1+]
+ HashMap(int)  [API 1+]
+ HashMap(int, float)  [API 1+]
+ clear(): void  [API 1+]
+ clone(): Object  [API 1+]
+ compute(Object, BiFunction): Object  [API 1+]
+ computeIfAbsent(Object, Function): Object  [API 1+]
+ computeIfPresent(Object, BiFunction): Object  [API 1+]
+ containsKey(Object): boolean  [API 1+]
+ containsValue(Object): boolean  [API 1+]
+ entrySet(): Set  [API 1+]
+ forEach(BiConsumer): void  [API 1+]
+ get(Object): Object  [API 1+]
+ getOrDefault(Object, Object): Object  [API 1+]
+ isEmpty(): boolean  [API 1+]
+ keySet(): Set  [API 1+]
+ merge(Object, Object, BiFunction): Object  [API 1+]
+ newHashMap(int): HashMap [static]  [API 35+]
+ put(Object, Object): Object  [API 1+]
+ putAll(Map): void  [API 1+]
+ putIfAbsent(Object, Object): Object  [API 1+]
+ remove(Object): Object  [API 1+]
+ remove(Object, Object): boolean  [API 1+]
+ replace(Object, Object): Object  [API 1+]
+ replace(Object, Object, Object): boolean  [API 1+]
+ replaceAll(BiFunction): void  [API 1+]
+ size(): int  [API 1+]
+ values(): Collection  [API 1+]
```

## HashSet — TeaVM class library; API 1+ (16 +, 0 *, 0 ~, 0 -)

```
+ HashSet()  [API 1+]
+ HashSet(Collection)  [API 1+]
+ HashSet(int)  [API 1+]
+ HashSet(int, float)  [API 1+]
+ add(Object): boolean  [API 1+]
+ clear(): void  [API 1+]
+ clone(): Object  [API 1+]
+ contains(Object): boolean  [API 1+]
+ isEmpty(): boolean  [API 1+]
+ iterator(): Iterator  [API 1+]
+ newHashSet(int): HashSet [static]  [API 35+]
+ remove(Object): boolean  [API 1+]
+ size(): int  [API 1+]
+ spliterator(): Spliterator  [API 1+]
+ toArray(): Object[]  [API 1+]
+ toArray(Object[]): Object[]  [API 1+]
```

## Hashtable — TeaVM class library; API 1+ (35 +, 0 *, 0 ~, 0 -)

```
+ Hashtable()  [API 1+]
+ Hashtable(Map)  [API 1+]
+ Hashtable(int)  [API 1+]
+ Hashtable(int, float)  [API 1+]
+ clear(): void  [API 1+]
+ clone(): Object  [API 1+]
+ compute(Object, BiFunction): Object  [API 1+]
+ computeIfAbsent(Object, Function): Object  [API 1+]
+ computeIfPresent(Object, BiFunction): Object  [API 1+]
+ contains(Object): boolean  [API 1+]
+ containsKey(Object): boolean  [API 1+]
+ containsValue(Object): boolean  [API 1+]
+ elements(): Enumeration  [API 1+]
+ entrySet(): Set  [API 1+]
+ equals(Object): boolean  [API 1+]
+ forEach(BiConsumer): void  [API 1+]
+ get(Object): Object  [API 1+]
+ getOrDefault(Object, Object): Object  [API 1+]
+ hashCode(): int  [API 1+]
+ isEmpty(): boolean  [API 1+]
+ keySet(): Set  [API 1+]
+ keys(): Enumeration  [API 1+]
+ merge(Object, Object, BiFunction): Object  [API 1+]
+ put(Object, Object): Object  [API 1+]
+ putAll(Map): void  [API 1+]
+ putIfAbsent(Object, Object): Object  [API 1+]
+ rehash(): void  [API 1+]
+ remove(Object): Object  [API 1+]
+ remove(Object, Object): boolean  [API 1+]
+ replace(Object, Object): Object  [API 1+]
+ replace(Object, Object, Object): boolean  [API 1+]
+ replaceAll(BiFunction): void  [API 1+]
+ size(): int  [API 1+]
+ toString(): String  [API 1+]
+ values(): Collection  [API 1+]
```

## HexFormat — framework (a2w.jdk.util.HexFormat); API 34+ (36 +, 0 *, 0 ~, 0 -)

```
+ delimiter(): String  [API 34+]
+ equals(Object): boolean  [API 34+]
+ formatHex(Appendable, byte[]): Appendable  [API 34+]
+ formatHex(Appendable, byte[], int, int): Appendable  [API 34+]
+ formatHex(byte[]): String  [API 34+]
+ formatHex(byte[], int, int): String  [API 34+]
+ fromHexDigit(int): int [static]  [API 34+]
+ fromHexDigits(CharSequence): int [static]  [API 34+]
+ fromHexDigits(CharSequence, int, int): int [static]  [API 34+]
+ fromHexDigitsToLong(CharSequence): long [static]  [API 34+]
+ fromHexDigitsToLong(CharSequence, int, int): long [static]  [API 34+]
+ hashCode(): int  [API 34+]
+ isHexDigit(int): boolean [static]  [API 34+]
+ isUpperCase(): boolean  [API 34+]
+ of(): HexFormat [static]  [API 34+]
+ ofDelimiter(String): HexFormat [static]  [API 34+]
+ parseHex(CharSequence): byte[]  [API 34+]
+ parseHex(CharSequence, int, int): byte[]  [API 34+]
+ parseHex(char[], int, int): byte[]  [API 34+]
+ prefix(): String  [API 34+]
+ suffix(): String  [API 34+]
+ toHexDigits(Appendable, byte): Appendable  [API 34+]
+ toHexDigits(byte): String  [API 34+]
+ toHexDigits(char): String  [API 34+]
+ toHexDigits(int): String  [API 34+]
+ toHexDigits(long): String  [API 34+]
+ toHexDigits(long, int): String  [API 34+]
+ toHexDigits(short): String  [API 34+]
+ toHighHexDigit(int): char  [API 34+]
+ toLowHexDigit(int): char  [API 34+]
+ toString(): String  [API 34+]
+ withDelimiter(String): HexFormat  [API 34+]
+ withLowerCase(): HexFormat  [API 34+]
+ withPrefix(String): HexFormat  [API 34+]
+ withSuffix(String): HexFormat  [API 34+]
+ withUpperCase(): HexFormat  [API 34+]
```

## IdentityHashMap — TeaVM class library; API 1+ (20 +, 0 *, 0 ~, 0 -)

```
+ IdentityHashMap()  [API 1+]
+ IdentityHashMap(Map)  [API 1+]
+ IdentityHashMap(int)  [API 1+]
+ clear(): void  [API 1+]
+ clone(): Object  [API 1+]
+ containsKey(Object): boolean  [API 1+]
+ containsValue(Object): boolean  [API 1+]
+ entrySet(): Set  [API 1+]
+ equals(Object): boolean  [API 1+]
+ forEach(BiConsumer): void  [API 1+]
+ get(Object): Object  [API 1+]
+ hashCode(): int  [API 1+]
+ isEmpty(): boolean  [API 1+]
+ keySet(): Set  [API 1+]
+ put(Object, Object): Object  [API 1+]
+ putAll(Map): void  [API 1+]
+ remove(Object): Object  [API 1+]
+ replaceAll(BiFunction): void  [API 1+]
+ size(): int  [API 1+]
+ values(): Collection  [API 1+]
```

## IllegalFormatCodePointException — TeaVM class library; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ IllegalFormatCodePointException(int)  [API 1+]
+ getCodePoint(): int  [API 1+]
+ getMessage(): String  [API 1+]
```

## IllegalFormatConversionException — TeaVM class library; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ IllegalFormatConversionException(char, Class)  [API 1+]
+ getArgumentClass(): Class  [API 1+]
+ getConversion(): char  [API 1+]
+ getMessage(): String  [API 1+]
```

## IllegalFormatException — TeaVM class library; API 1+ (0 +, 0 *, 0 ~, 0 -)

## IllegalFormatFlagsException — TeaVM class library; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ IllegalFormatFlagsException(String)  [API 1+]
+ getFlags(): String  [API 1+]
+ getMessage(): String  [API 1+]
```

## IllegalFormatPrecisionException — TeaVM class library; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ IllegalFormatPrecisionException(int)  [API 1+]
+ getMessage(): String  [API 1+]
+ getPrecision(): int  [API 1+]
```

## IllegalFormatWidthException — missing; API 1+ (0 +, 0 *, 0 ~, 3 -)

```
- IllegalFormatWidthException(int)  [API 1+]
- getMessage(): String  [API 1+]
- getWidth(): int  [API 1+]
```

## IllformedLocaleException — declaration; API 21+ (2 +, 0 *, 0 ~, 2 -)

```
+ IllformedLocaleException()  [API 21+]
+ IllformedLocaleException(String)  [API 21+]
- IllformedLocaleException(String, int)  [API 21+]
- getErrorIndex(): int  [API 21+]
```

## InputMismatchException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ InputMismatchException()  [API 1+]
+ InputMismatchException(String)  [API 1+]
```

## IntSummaryStatistics — TeaVM class library; API 24+ (10 +, 0 *, 0 ~, 0 -)

```
+ IntSummaryStatistics()  [API 24+]
+ IntSummaryStatistics(long, int, int, long)  [API 33+]
+ accept(int): void  [API 24+]
+ combine(IntSummaryStatistics): void  [API 24+]
+ getAverage(): double  [API 24+]
+ getCount(): long  [API 24+]
+ getMax(): int  [API 24+]
+ getMin(): int  [API 24+]
+ getSum(): long  [API 24+]
+ toString(): String  [API 24+]
```

## InvalidPropertiesFormatException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ InvalidPropertiesFormatException(String)  [API 1+]
+ InvalidPropertiesFormatException(Throwable)  [API 1+]
```

## Iterator — TeaVM class library; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ forEachRemaining(Consumer): void  [API 24+]
+ hasNext(): boolean  [API 1+]
+ next(): Object  [API 1+]
+ remove(): void  [API 1+]
```

## LinkedHashMap — TeaVM class library; API 1+ (22 +, 0 *, 0 ~, 0 -)

```
+ LinkedHashMap()  [API 1+]
+ LinkedHashMap(Map)  [API 1+]
+ LinkedHashMap(int)  [API 1+]
+ LinkedHashMap(int, float)  [API 1+]
+ LinkedHashMap(int, float, boolean)  [API 1+]
+ clear(): void  [API 1+]
+ containsValue(Object): boolean  [API 1+]
+ entrySet(): Set  [API 1+]
+ forEach(BiConsumer): void  [API 1+]
+ get(Object): Object  [API 1+]
+ getOrDefault(Object, Object): Object  [API 1+]
+ keySet(): Set  [API 1+]
+ newLinkedHashMap(int): LinkedHashMap [static]  [API 35+]
+ putFirst(Object, Object): Object  [API 1+]
+ putLast(Object, Object): Object  [API 1+]
+ removeEldestEntry(Map.Entry): boolean  [API 1+]
+ replaceAll(BiFunction): void  [API 1+]
+ reversed(): SequencedMap  [API 1+]
+ sequencedEntrySet(): SequencedSet  [API 1+]
+ sequencedKeySet(): SequencedSet  [API 1+]
+ sequencedValues(): SequencedCollection  [API 1+]
+ values(): Collection  [API 1+]
```

## LinkedHashSet — TeaVM class library; API 1+ (13 +, 0 *, 0 ~, 0 -)

```
+ LinkedHashSet()  [API 1+]
+ LinkedHashSet(Collection)  [API 1+]
+ LinkedHashSet(int)  [API 1+]
+ LinkedHashSet(int, float)  [API 1+]
+ addFirst(Object): void  [API 1+]
+ addLast(Object): void  [API 1+]
+ getFirst(): Object  [API 1+]
+ getLast(): Object  [API 1+]
+ newLinkedHashSet(int): LinkedHashSet [static]  [API 35+]
+ removeFirst(): Object  [API 1+]
+ removeLast(): Object  [API 1+]
+ reversed(): SequencedSet  [API 1+]
+ spliterator(): Spliterator  [API 1+]
```

## LinkedList — TeaVM class library; API 1+ (43 +, 0 *, 0 ~, 0 -)

```
+ LinkedList()  [API 1+]
+ LinkedList(Collection)  [API 1+]
+ add(Object): boolean  [API 1+]
+ add(int, Object): void  [API 1+]
+ addAll(Collection): boolean  [API 1+]
+ addAll(int, Collection): boolean  [API 1+]
+ addFirst(Object): void  [API 1+]
+ addLast(Object): void  [API 1+]
+ clear(): void  [API 1+]
+ clone(): Object  [API 1+]
+ contains(Object): boolean  [API 1+]
+ descendingIterator(): Iterator  [API 1+]
+ element(): Object  [API 1+]
+ get(int): Object  [API 1+]
+ getFirst(): Object  [API 1+]
+ getLast(): Object  [API 1+]
+ indexOf(Object): int  [API 1+]
+ lastIndexOf(Object): int  [API 1+]
+ listIterator(int): ListIterator  [API 1+]
+ offer(Object): boolean  [API 1+]
+ offerFirst(Object): boolean  [API 1+]
+ offerLast(Object): boolean  [API 1+]
+ peek(): Object  [API 1+]
+ peekFirst(): Object  [API 1+]
+ peekLast(): Object  [API 1+]
+ poll(): Object  [API 1+]
+ pollFirst(): Object  [API 1+]
+ pollLast(): Object  [API 1+]
+ pop(): Object  [API 1+]
+ push(Object): void  [API 1+]
+ remove(): Object  [API 1+]
+ remove(Object): boolean  [API 1+]
+ remove(int): Object  [API 1+]
+ removeFirst(): Object  [API 1+]
+ removeFirstOccurrence(Object): boolean  [API 1+]
+ removeLast(): Object  [API 1+]
+ removeLastOccurrence(Object): boolean  [API 1+]
+ reversed(): LinkedList  [API 35+]
+ set(int, Object): Object  [API 1+]
+ size(): int  [API 1+]
+ spliterator(): Spliterator  [API 1+]
+ toArray(): Object[]  [API 1+]
+ toArray(Object[]): Object[]  [API 1+]
```

## List — TeaVM class library; API 1+ (48 +, 0 *, 0 ~, 0 -)

```
+ add(Object): boolean  [API 1+]
+ add(int, Object): void  [API 1+]
+ addAll(Collection): boolean  [API 1+]
+ addAll(int, Collection): boolean  [API 1+]
+ addFirst(Object): void  [API 1+]
+ addLast(Object): void  [API 1+]
+ clear(): void  [API 1+]
+ contains(Object): boolean  [API 1+]
+ containsAll(Collection): boolean  [API 1+]
+ copyOf(Collection): List [static]  [API 31+]
+ equals(Object): boolean  [API 1+]
+ get(int): Object  [API 1+]
+ getFirst(): Object  [API 1+]
+ getLast(): Object  [API 1+]
+ hashCode(): int  [API 1+]
+ indexOf(Object): int  [API 1+]
+ isEmpty(): boolean  [API 1+]
+ iterator(): Iterator  [API 1+]
+ lastIndexOf(Object): int  [API 1+]
+ listIterator(): ListIterator  [API 1+]
+ listIterator(int): ListIterator  [API 1+]
+ of(): List [static]  [API 30+]
+ of(Object): List [static]  [API 30+]
+ of(Object, Object): List [static]  [API 30+]
+ of(Object, Object, Object): List [static]  [API 30+]
+ of(Object, Object, Object, Object): List [static]  [API 30+]
+ of(Object, Object, Object, Object, Object): List [static]  [API 30+]
+ of(Object, Object, Object, Object, Object, Object): List [static]  [API 30+]
+ of(Object, Object, Object, Object, Object, Object, Object): List [static]  [API 30+]
+ of(Object, Object, Object, Object, Object, Object, Object, Object): List [static]  [API 30+]
+ of(Object, Object, Object, Object, Object, Object, Object, Object, Object): List [static]  [API 30+]
+ of(Object, Object, Object, Object, Object, Object, Object, Object, Object, Object): List [static]  [API 30+]
+ of(Object[]): List [static]  [API 30+]
+ remove(Object): boolean  [API 1+]
+ remove(int): Object  [API 1+]
+ removeAll(Collection): boolean  [API 1+]
+ removeFirst(): Object  [API 1+]
+ removeLast(): Object  [API 1+]
+ replaceAll(UnaryOperator): void  [API 24+]
+ retainAll(Collection): boolean  [API 1+]
+ reversed(): List  [API 35+]
+ set(int, Object): Object  [API 1+]
+ size(): int  [API 1+]
+ sort(Comparator): void  [API 24+]
+ spliterator(): Spliterator  [API 1+]
+ subList(int, int): List  [API 1+]
+ toArray(): Object[]  [API 1+]
+ toArray(Object[]): Object[]  [API 1+]
```

## ListIterator — TeaVM class library; API 1+ (9 +, 0 *, 0 ~, 0 -)

```
+ add(Object): void  [API 1+]
+ hasNext(): boolean  [API 1+]
+ hasPrevious(): boolean  [API 1+]
+ next(): Object  [API 1+]
+ nextIndex(): int  [API 1+]
+ previous(): Object  [API 1+]
+ previousIndex(): int  [API 1+]
+ remove(): void  [API 1+]
+ set(Object): void  [API 1+]
```

## ListResourceBundle — TeaVM class library; API 1+ (4 +, 0 *, 0 ~, 1 -)

```
+ ListResourceBundle()  [API 1+]
+ getContents(): Object[][]  [API 1+]
+ getKeys(): Enumeration  [API 1+]
+ handleGetObject(String): Object  [API 1+]
- handleKeySet(): Set  [API 1+]
```

## Locale — TeaVM class library; API 1+ (44 +, 0 *, 0 ~, 6 -)

```
+ CANADA [field]  [API 1+]
+ CANADA_FRENCH [field]  [API 1+]
+ CHINA [field]  [API 1+]
+ CHINESE [field]  [API 1+]
+ ENGLISH [field]  [API 1+]
+ FRANCE [field]  [API 1+]
+ FRENCH [field]  [API 1+]
+ GERMAN [field]  [API 1+]
+ GERMANY [field]  [API 1+]
+ ITALIAN [field]  [API 1+]
+ ITALY [field]  [API 1+]
+ JAPAN [field]  [API 1+]
+ JAPANESE [field]  [API 1+]
+ KOREA [field]  [API 1+]
+ KOREAN [field]  [API 1+]
+ Locale(String)  [API 1+, deprecated in 36]
+ Locale(String, String)  [API 1+, deprecated in 36]
+ Locale(String, String, String)  [API 1+, deprecated in 36]
+ PRC [field]  [API 1+]
+ ROOT [field]  [API 9+]
+ SIMPLIFIED_CHINESE [field]  [API 1+]
+ TAIWAN [field]  [API 1+]
+ TRADITIONAL_CHINESE [field]  [API 1+]
+ UK [field]  [API 1+]
+ US [field]  [API 1+]
+ availableLocales(): Stream  [API 36+]
+ clone(): Object  [API 1+]
+ equals(Object): boolean  [API 1+]
- filter(List, Collection): List [static]  [API 26+]
- filter(List, Collection, Locale.FilteringMode): List [static]  [API 26+]
- filterTags(List, Collection): List [static]  [API 26+]
- filterTags(List, Collection, Locale.FilteringMode): List [static]  [API 26+]
+ forLanguageTag(String): Locale [static]  [API 21+]
+ getAvailableLocales(): Locale[] [static]  [API 1+]
+ getCountry(): String  [API 1+]
+ getDefault(): Locale [static]  [API 1+]
+ getDefault(Locale.Category): Locale [static]  [API 24+]
+ getDisplayCountry(): String  [API 1+]
+ getDisplayCountry(Locale): String  [API 1+]
+ getDisplayLanguage(): String  [API 1+]
+ getDisplayLanguage(Locale): String  [API 1+]
+ getDisplayName(): String  [API 1+]
+ getDisplayName(Locale): String  [API 1+]
+ getDisplayScript(): String  [API 21+]
+ getDisplayScript(Locale): String  [API 21+]
+ getDisplayVariant(): String  [API 1+]
+ getDisplayVariant(Locale): String  [API 1+]
+ getExtension(char): String  [API 21+]
+ getExtensionKeys(): Set  [API 21+]
+ getISO3Country(): String  [API 1+]
+ getISO3Language(): String  [API 1+]
+ getISOCountries(): String[] [static]  [API 1+]
+ getISOCountries(Locale.IsoCountryCode): Set [static]  [API 34+]
+ getISOLanguages(): String[] [static]  [API 1+]
+ getLanguage(): String  [API 1+]
+ getScript(): String  [API 21+]
+ getUnicodeLocaleAttributes(): Set  [API 21+]
+ getUnicodeLocaleKeys(): Set  [API 21+]
+ getUnicodeLocaleType(String): String  [API 21+]
+ getVariant(): String  [API 1+]
+ hasExtensions(): boolean  [API 26+]
+ hashCode(): int  [API 1+]
- lookup(List, Collection): Locale [static]  [API 26+]
- lookupTag(List, Collection): String [static]  [API 26+]
+ of(String): Locale  [API 36+]
+ of(String, String): Locale  [API 36+]
+ of(String, String, String): Locale  [API 36+]
+ setDefault(Locale): void [static]  [API 1+]
+ setDefault(Locale.Category, Locale): void [static]  [API 24+]
+ stripExtensions(): Locale  [API 26+]
+ toLanguageTag(): String  [API 21+]
+ toString(): String  [API 1+]
```

## Locale.Builder — declaration; API 21+ (0 +, 0 *, 0 ~, 14 -)

```
- Builder()  [API 21+]
- addUnicodeLocaleAttribute(String): Locale.Builder  [API 21+]
- build(): Locale  [API 21+]
- clear(): Locale.Builder  [API 21+]
- clearExtensions(): Locale.Builder  [API 21+]
- removeUnicodeLocaleAttribute(String): Locale.Builder  [API 21+]
- setExtension(char, String): Locale.Builder  [API 21+]
- setLanguage(String): Locale.Builder  [API 21+]
- setLanguageTag(String): Locale.Builder  [API 21+]
- setLocale(Locale): Locale.Builder  [API 21+]
- setRegion(String): Locale.Builder  [API 21+]
- setScript(String): Locale.Builder  [API 21+]
- setUnicodeLocaleKeyword(String, String): Locale.Builder  [API 21+]
- setVariant(String): Locale.Builder  [API 21+]
```

## Locale.Category — declaration; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ DISPLAY [field]  [API 24+]
+ FORMAT [field]  [API 24+]
+ valueOf(String): Locale.Category [static]  [API 24+]
+ values(): Locale.Category[] [static]  [API 24+]
```

## Locale.FilteringMode — declaration; API 26+ (2 +, 0 *, 0 ~, 0 -)

```
+ AUTOSELECT_FILTERING [field]  [API 26+]
+ EXTENDED_FILTERING [field]  [API 26+]
+ IGNORE_EXTENDED_RANGES [field]  [API 26+]
+ MAP_EXTENDED_RANGES [field]  [API 26+]
+ REJECT_EXTENDED_RANGES [field]  [API 26+]
+ valueOf(String): Locale.FilteringMode [static]  [API 26+]
+ values(): Locale.FilteringMode[] [static]  [API 26+]
```

## Locale.IsoCountryCode — declaration; API 34+ (2 +, 0 *, 0 ~, 0 -)

```
+ PART1_ALPHA2 [field]  [API 34+]
+ PART1_ALPHA3 [field]  [API 34+]
+ PART3 [field]  [API 34+]
+ valueOf(String): Locale.IsoCountryCode [static]  [API 34+]
+ values(): Locale.IsoCountryCode[] [static]  [API 34+]
```

## Locale.LanguageRange — declaration; API 26+ (0 +, 7 *, 0 ~, 3 -)

```
* LanguageRange(String)  [API 26+]
* LanguageRange(String, double)  [API 26+]
* equals(Object): boolean  [API 26+]
* getRange(): String  [API 26+]
* getWeight(): double  [API 26+]
* hashCode(): int  [API 26+]
- mapEquivalents(List, Map): List [static]  [API 26+]
- parse(String): List [static]  [API 26+]
- parse(String, Map): List [static]  [API 26+]
* toString(): String  [API 26+]
```

## LongSummaryStatistics — TeaVM class library; API 24+ (11 +, 0 *, 0 ~, 0 -)

```
+ LongSummaryStatistics()  [API 24+]
+ LongSummaryStatistics(long, long, long, long)  [API 33+]
+ accept(int): void  [API 24+]
+ accept(long): void  [API 24+]
+ combine(LongSummaryStatistics): void  [API 24+]
+ getAverage(): double  [API 24+]
+ getCount(): long  [API 24+]
+ getMax(): long  [API 24+]
+ getMin(): long  [API 24+]
+ getSum(): long  [API 24+]
+ toString(): String  [API 24+]
```

## Map — TeaVM class library; API 1+ (39 +, 0 *, 0 ~, 0 -)

```
+ clear(): void  [API 1+]
+ compute(Object, BiFunction): Object  [API 24+]
+ computeIfAbsent(Object, Function): Object  [API 24+]
+ computeIfPresent(Object, BiFunction): Object  [API 24+]
+ containsKey(Object): boolean  [API 1+]
+ containsValue(Object): boolean  [API 1+]
+ copyOf(Map): Map [static]  [API 31+]
+ entry(Object, Object): Map.Entry [static]  [API 30+]
+ entrySet(): Set  [API 1+]
+ equals(Object): boolean  [API 1+]
+ forEach(BiConsumer): void  [API 24+]
+ get(Object): Object  [API 1+]
+ getOrDefault(Object, Object): Object  [API 24+]
+ hashCode(): int  [API 1+]
+ isEmpty(): boolean  [API 1+]
+ keySet(): Set  [API 1+]
+ merge(Object, Object, BiFunction): Object  [API 24+]
+ of(): Map [static]  [API 30+]
+ of(Object, Object): Map [static]  [API 30+]
+ of(Object, Object, Object, Object): Map [static]  [API 30+]
+ of(Object, Object, Object, Object, Object, Object): Map [static]  [API 30+]
+ of(Object, Object, Object, Object, Object, Object, Object, Object): Map [static]  [API 30+]
+ of(Object, Object, Object, Object, Object, Object, Object, Object, Object, Object): Map [static]  [API 30+]
+ of(Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object): Map [static]  [API 30+]
+ of(Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object): Map [static]  [API 30+]
+ of(Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object): Map [static]  [API 30+]
+ of(Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object): Map [static]  [API 30+]
+ of(Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object): Map [static]  [API 30+]
+ ofEntries(Map.Entry[]): Map [static]  [API 30+]
+ put(Object, Object): Object  [API 1+]
+ putAll(Map): void  [API 1+]
+ putIfAbsent(Object, Object): Object  [API 24+]
+ remove(Object): Object  [API 1+]
+ remove(Object, Object): boolean  [API 24+]
+ replace(Object, Object): Object  [API 24+]
+ replace(Object, Object, Object): boolean  [API 24+]
+ replaceAll(BiFunction): void  [API 24+]
+ size(): int  [API 1+]
+ values(): Collection  [API 1+]
```

## Map.Entry — TeaVM class library; API 1+ (10 +, 0 *, 0 ~, 0 -)

```
+ comparingByKey(): Comparator [static]  [API 24+]
+ comparingByKey(Comparator): Comparator [static]  [API 24+]
+ comparingByValue(): Comparator [static]  [API 24+]
+ comparingByValue(Comparator): Comparator [static]  [API 24+]
+ copyOf(Map.Entry): Map.Entry [static]  [API 35+]
+ equals(Object): boolean  [API 1+]
+ getKey(): Object  [API 1+]
+ getValue(): Object  [API 1+]
+ hashCode(): int  [API 1+]
+ setValue(Object): Object  [API 1+]
```

## MissingFormatArgumentException — missing; API 1+ (0 +, 0 *, 0 ~, 3 -)

```
- MissingFormatArgumentException(String)  [API 1+]
- getFormatSpecifier(): String  [API 1+]
- getMessage(): String  [API 1+]
```

## MissingFormatWidthException — TeaVM class library; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ MissingFormatWidthException(String)  [API 1+]
+ getFormatSpecifier(): String  [API 1+]
+ getMessage(): String  [API 1+]
```

## MissingResourceException — TeaVM class library; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ MissingResourceException(String, String, String)  [API 1+]
+ getClassName(): String  [API 1+]
+ getKey(): String  [API 1+]
```

## NavigableMap — TeaVM class library; API 9+ (22 +, 0 *, 0 ~, 0 -)

```
+ ceilingEntry(Object): Map.Entry  [API 9+]
+ ceilingKey(Object): Object  [API 9+]
+ descendingKeySet(): NavigableSet  [API 9+]
+ descendingMap(): NavigableMap  [API 9+]
+ firstEntry(): Map.Entry  [API 9+]
+ floorEntry(Object): Map.Entry  [API 9+]
+ floorKey(Object): Object  [API 9+]
+ headMap(Object): SortedMap  [API 9+]
+ headMap(Object, boolean): NavigableMap  [API 9+]
+ higherEntry(Object): Map.Entry  [API 9+]
+ higherKey(Object): Object  [API 9+]
+ lastEntry(): Map.Entry  [API 9+]
+ lowerEntry(Object): Map.Entry  [API 9+]
+ lowerKey(Object): Object  [API 9+]
+ navigableKeySet(): NavigableSet  [API 9+]
+ pollFirstEntry(): Map.Entry  [API 9+]
+ pollLastEntry(): Map.Entry  [API 9+]
+ reversed(): NavigableMap  [API 35+]
+ subMap(Object, Object): SortedMap  [API 9+]
+ subMap(Object, boolean, Object, boolean): NavigableMap  [API 9+]
+ tailMap(Object): SortedMap  [API 9+]
+ tailMap(Object, boolean): NavigableMap  [API 9+]
```

## NavigableSet — TeaVM class library; API 9+ (18 +, 0 *, 0 ~, 0 -)

```
+ ceiling(Object): Object  [API 9+]
+ descendingIterator(): Iterator  [API 9+]
+ descendingSet(): NavigableSet  [API 9+]
+ floor(Object): Object  [API 9+]
+ headSet(Object): SortedSet  [API 9+]
+ headSet(Object, boolean): NavigableSet  [API 9+]
+ higher(Object): Object  [API 9+]
+ iterator(): Iterator  [API 9+]
+ lower(Object): Object  [API 9+]
+ pollFirst(): Object  [API 9+]
+ pollLast(): Object  [API 9+]
+ removeFirst(): Object  [API 9+]
+ removeLast(): Object  [API 9+]
+ reversed(): NavigableSet  [API 35+]
+ subSet(Object, Object): SortedSet  [API 9+]
+ subSet(Object, boolean, Object, boolean): NavigableSet  [API 9+]
+ tailSet(Object): SortedSet  [API 9+]
+ tailSet(Object, boolean): NavigableSet  [API 9+]
```

## NoSuchElementException — TeaVM class library; API 1+ (4 +, 0 *, 0 ~, 0 -)

```
+ NoSuchElementException()  [API 1+]
+ NoSuchElementException(String)  [API 1+]
+ NoSuchElementException(String, Throwable)  [API 34+]
+ NoSuchElementException(Throwable)  [API 34+]
```

## Objects — TeaVM class library; API 19+ (21 +, 0 *, 0 ~, 0 -)

```
+ checkFromIndexSize(int, int, int): int [static]  [API 30+]
+ checkFromIndexSize(long, long, long): long [static]  [API 34+]
+ checkFromToIndex(int, int, int): int [static]  [API 30+]
+ checkFromToIndex(long, long, long): long [static]  [API 34+]
+ checkIndex(int, int): int [static]  [API 30+]
+ checkIndex(long, long): long [static]  [API 34+]
+ compare(Object, Object, Comparator): int [static]  [API 19+]
+ deepEquals(Object, Object): boolean [static]  [API 19+]
+ equals(Object, Object): boolean [static]  [API 19+]
+ hash(Object[]): int [static]  [API 19+]
+ hashCode(Object): int [static]  [API 19+]
+ isNull(Object): boolean [static]  [API 24+]
+ nonNull(Object): boolean [static]  [API 24+]
+ requireNonNull(Object): Object [static]  [API 19+]
+ requireNonNull(Object, String): Object [static]  [API 19+]
+ requireNonNull(Object, Supplier): Object [static]  [API 24+]
+ requireNonNullElse(Object, Object): Object [static]  [API 30+]
+ requireNonNullElseGet(Object, Supplier): Object [static]  [API 30+]
+ toIdentityString(Object): String  [API 36+]
+ toString(Object): String [static]  [API 19+]
+ toString(Object, String): String [static]  [API 19+]
```

## Observable — TeaVM class library; API 1+, deprecated in 33 (10 +, 0 *, 0 ~, 0 -)

```
+ Observable()  [API 1+]
+ addObserver(Observer): void  [API 1+]
+ clearChanged(): void  [API 1+]
+ countObservers(): int  [API 1+]
+ deleteObserver(Observer): void  [API 1+]
+ deleteObservers(): void  [API 1+]
+ hasChanged(): boolean  [API 1+]
+ notifyObservers(): void  [API 1+]
+ notifyObservers(Object): void  [API 1+]
+ setChanged(): void  [API 1+]
```

## Observer — TeaVM class library; API 1+, deprecated in 33 (1 +, 0 *, 0 ~, 0 -)

```
+ update(Observable, Object): void  [API 1+]
```

## Optional — TeaVM class library; API 24+ (20 +, 0 *, 0 ~, 0 -)

```
+ empty(): Optional [static]  [API 24+]
+ equals(Object): boolean  [API 24+]
+ filter(Predicate): Optional  [API 24+]
+ flatMap(Function): Optional  [API 24+]
+ get(): Object  [API 24+]
+ hashCode(): int  [API 24+]
+ ifPresent(Consumer): void  [API 24+]
+ ifPresentOrElse(Consumer, Runnable): void  [API 33+]
+ isEmpty(): boolean  [API 33+]
+ isPresent(): boolean  [API 24+]
+ map(Function): Optional  [API 24+]
+ of(Object): Optional [static]  [API 24+]
+ ofNullable(Object): Optional [static]  [API 24+]
+ or(Supplier): Optional  [API 33+]
+ orElse(Object): Object  [API 24+]
+ orElseGet(Supplier): Object  [API 24+]
+ orElseThrow(): Object  [API 33+]
+ orElseThrow(Supplier): Object  [API 24+]
+ stream(): Stream  [API 33+]
+ toString(): String  [API 24+]
```

## OptionalDouble — TeaVM class library; API 24+ (15 +, 0 *, 0 ~, 0 -)

```
+ empty(): OptionalDouble [static]  [API 24+]
+ equals(Object): boolean  [API 24+]
+ getAsDouble(): double  [API 24+]
+ hashCode(): int  [API 24+]
+ ifPresent(DoubleConsumer): void  [API 24+]
+ ifPresentOrElse(DoubleConsumer, Runnable): void  [API 33+]
+ isEmpty(): boolean  [API 33+]
+ isPresent(): boolean  [API 24+]
+ of(double): OptionalDouble [static]  [API 24+]
+ orElse(double): double  [API 24+]
+ orElseGet(DoubleSupplier): double  [API 24+]
+ orElseThrow(): double  [API 33+]
+ orElseThrow(Supplier): double  [API 24+]
+ stream(): DoubleStream  [API 33+]
+ toString(): String  [API 24+]
```

## OptionalInt — TeaVM class library; API 24+ (15 +, 0 *, 0 ~, 0 -)

```
+ empty(): OptionalInt [static]  [API 24+]
+ equals(Object): boolean  [API 24+]
+ getAsInt(): int  [API 24+]
+ hashCode(): int  [API 24+]
+ ifPresent(IntConsumer): void  [API 24+]
+ ifPresentOrElse(IntConsumer, Runnable): void  [API 33+]
+ isEmpty(): boolean  [API 33+]
+ isPresent(): boolean  [API 24+]
+ of(int): OptionalInt [static]  [API 24+]
+ orElse(int): int  [API 24+]
+ orElseGet(IntSupplier): int  [API 24+]
+ orElseThrow(): int  [API 33+]
+ orElseThrow(Supplier): int  [API 24+]
+ stream(): IntStream  [API 33+]
+ toString(): String  [API 24+]
```

## OptionalLong — TeaVM class library; API 24+ (15 +, 0 *, 0 ~, 0 -)

```
+ empty(): OptionalLong [static]  [API 24+]
+ equals(Object): boolean  [API 24+]
+ getAsLong(): long  [API 24+]
+ hashCode(): int  [API 24+]
+ ifPresent(LongConsumer): void  [API 24+]
+ ifPresentOrElse(LongConsumer, Runnable): void  [API 33+]
+ isEmpty(): boolean  [API 33+]
+ isPresent(): boolean  [API 24+]
+ of(long): OptionalLong [static]  [API 24+]
+ orElse(long): long  [API 24+]
+ orElseGet(LongSupplier): long  [API 24+]
+ orElseThrow(): long  [API 33+]
+ orElseThrow(Supplier): long  [API 24+]
+ stream(): LongStream  [API 33+]
+ toString(): String  [API 24+]
```

## PrimitiveIterator — TeaVM class library; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ forEachRemaining(Object): void  [API 24+]
```

## PrimitiveIterator.OfDouble — TeaVM class library; API 24+ (4 +, 0 *, 0 ~, 0 -)

```
+ forEachRemaining(Consumer): void  [API 24+]
+ forEachRemaining(DoubleConsumer): void  [API 24+]
+ next(): Double  [API 24+]
+ nextDouble(): double  [API 24+]
```

## PrimitiveIterator.OfInt — TeaVM class library; API 24+ (4 +, 0 *, 0 ~, 0 -)

```
+ forEachRemaining(Consumer): void  [API 24+]
+ forEachRemaining(IntConsumer): void  [API 24+]
+ next(): Integer  [API 24+]
+ nextInt(): int  [API 24+]
```

## PrimitiveIterator.OfLong — TeaVM class library; API 24+ (4 +, 0 *, 0 ~, 0 -)

```
+ forEachRemaining(Consumer): void  [API 24+]
+ forEachRemaining(LongConsumer): void  [API 24+]
+ next(): Long  [API 24+]
+ nextLong(): long  [API 24+]
```

## PriorityQueue — TeaVM class library; API 1+ (24 +, 0 *, 0 ~, 0 -)

```
+ PriorityQueue()  [API 1+]
+ PriorityQueue(Collection)  [API 1+]
+ PriorityQueue(Comparator)  [API 24+]
+ PriorityQueue(PriorityQueue)  [API 1+]
+ PriorityQueue(SortedSet)  [API 1+]
+ PriorityQueue(int)  [API 1+]
+ PriorityQueue(int, Comparator)  [API 1+]
+ add(Object): boolean  [API 1+]
+ clear(): void  [API 1+]
+ comparator(): Comparator  [API 1+]
+ contains(Object): boolean  [API 1+]
+ forEach(Consumer): void  [API 1+]
+ iterator(): Iterator  [API 1+]
+ offer(Object): boolean  [API 1+]
+ peek(): Object  [API 1+]
+ poll(): Object  [API 1+]
+ remove(Object): boolean  [API 1+]
+ removeAll(Collection): boolean  [API 1+]
+ removeIf(Predicate): boolean  [API 1+]
+ retainAll(Collection): boolean  [API 1+]
+ size(): int  [API 1+]
+ spliterator(): Spliterator  [API 1+]
+ toArray(): Object[]  [API 1+]
+ toArray(Object[]): Object[]  [API 1+]
```

## Properties — TeaVM class library; API 1+ (49 +, 0 *, 0 ~, 1 -)

```
+ Properties()  [API 1+]
+ Properties(Properties)  [API 1+]
- Properties(int)  [API 35+]
+ clear(): void  [API 1+]
+ clone(): Object  [API 1+]
+ compute(Object, BiFunction): Object  [API 1+]
+ computeIfAbsent(Object, Function): Object  [API 1+]
+ computeIfPresent(Object, BiFunction): Object  [API 1+]
+ contains(Object): boolean  [API 1+]
+ containsKey(Object): boolean  [API 1+]
+ containsValue(Object): boolean  [API 1+]
+ defaults [field]  [API 1+]
+ elements(): Enumeration  [API 1+]
+ entrySet(): Set  [API 1+]
+ equals(Object): boolean  [API 1+]
+ forEach(BiConsumer): void  [API 1+]
+ get(Object): Object  [API 1+]
+ getOrDefault(Object, Object): Object  [API 1+]
+ getProperty(String): String  [API 1+]
+ getProperty(String, String): String  [API 1+]
+ hashCode(): int  [API 1+]
+ isEmpty(): boolean  [API 1+]
+ keySet(): Set  [API 1+]
+ keys(): Enumeration  [API 1+]
+ list(PrintStream): void  [API 1+]
+ list(PrintWriter): void  [API 1+]
+ load(InputStream): void  [API 1+]
+ load(Reader): void  [API 9+]
+ loadFromXML(InputStream): void  [API 1+]
+ merge(Object, Object, BiFunction): Object  [API 1+]
+ propertyNames(): Enumeration  [API 1+]
+ put(Object, Object): Object  [API 1+]
+ putAll(Map): void  [API 1+]
+ putIfAbsent(Object, Object): Object  [API 1+]
+ rehash(): void  [API 1+]
+ remove(Object): Object  [API 1+]
+ remove(Object, Object): boolean  [API 1+]
+ replace(Object, Object): Object  [API 1+]
+ replace(Object, Object, Object): boolean  [API 1+]
+ replaceAll(BiFunction): void  [API 1+]
+ save(OutputStream, String): void  [API 1+, deprecated in 15]
+ setProperty(String, String): Object  [API 1+]
+ size(): int  [API 1+]
+ store(OutputStream, String): void  [API 1+]
+ store(Writer, String): void  [API 9+]
+ storeToXML(OutputStream, String): void  [API 1+]
+ storeToXML(OutputStream, String, Charset): void  [API 35+]
+ storeToXML(OutputStream, String, String): void  [API 1+]
+ stringPropertyNames(): Set  [API 9+]
+ toString(): String  [API 1+]
+ values(): Collection  [API 1+]
```

## PropertyPermission — declaration; API 1+ (0 +, 0 *, 0 ~, 1 -)

```
- PropertyPermission(String, String)  [API 1+]
```

## PropertyResourceBundle — TeaVM class library; API 1+ (4 +, 0 *, 0 ~, 1 -)

```
+ PropertyResourceBundle(InputStream)  [API 1+]
+ PropertyResourceBundle(Reader)  [API 9+]
+ getKeys(): Enumeration  [API 1+]
+ handleGetObject(String): Object  [API 1+]
- handleKeySet(): Set  [API 1+]
```

## Queue — TeaVM class library; API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ add(Object): boolean  [API 1+]
+ element(): Object  [API 1+]
+ offer(Object): boolean  [API 1+]
+ peek(): Object  [API 1+]
+ poll(): Object  [API 1+]
+ remove(): Object  [API 1+]
```

## Random — TeaVM class library; API 1+ (24 +, 0 *, 0 ~, 1 -)

```
+ Random()  [API 1+]
+ Random(long)  [API 1+]
+ doubles(): DoubleStream  [API 24+]
+ doubles(double, double): DoubleStream  [API 24+]
+ doubles(long): DoubleStream  [API 24+]
+ doubles(long, double, double): DoubleStream  [API 24+]
- from(RandomGenerator): Random  [API 36+]
+ ints(): IntStream  [API 24+]
+ ints(int, int): IntStream  [API 24+]
+ ints(long): IntStream  [API 24+]
+ ints(long, int, int): IntStream  [API 24+]
+ longs(): LongStream  [API 24+]
+ longs(long): LongStream  [API 24+]
+ longs(long, long): LongStream  [API 24+]
+ longs(long, long, long): LongStream  [API 24+]
+ next(int): int  [API 1+]
+ nextBoolean(): boolean  [API 1+]
+ nextBytes(byte[]): void  [API 1+]
+ nextDouble(): double  [API 1+]
+ nextFloat(): float  [API 1+]
+ nextGaussian(): double  [API 1+]
+ nextInt(): int  [API 1+]
+ nextInt(int): int  [API 1+]
+ nextLong(): long  [API 1+]
+ setSeed(long): void  [API 1+]
```

## RandomAccess — TeaVM class library; API 1+ (0 +, 0 *, 0 ~, 0 -)

## ResourceBundle — TeaVM class library; API 1+ (13 +, 0 *, 0 ~, 7 -)

```
+ ResourceBundle()  [API 1+]
- clearCache(): void [static]  [API 9+]
- clearCache(ClassLoader): void [static]  [API 9+]
+ containsKey(String): boolean  [API 9+]
+ getBaseBundleName(): String  [API 26+]
+ getBundle(String): ResourceBundle [static]  [API 1+]
+ getBundle(String, Locale): ResourceBundle [static]  [API 1+]
+ getBundle(String, Locale, ClassLoader): ResourceBundle [static]  [API 1+]
- getBundle(String, Locale, ClassLoader, ResourceBundle.Control): ResourceBundle [static]  [API 9+]
- getBundle(String, Locale, ResourceBundle.Control): ResourceBundle [static]  [API 9+]
- getBundle(String, ResourceBundle.Control): ResourceBundle [static]  [API 9+]
+ getKeys(): Enumeration  [API 1+]
+ getLocale(): Locale  [API 1+]
+ getObject(String): Object  [API 1+]
+ getString(String): String  [API 1+]
+ getStringArray(String): String[]  [API 1+]
+ handleGetObject(String): Object  [API 1+]
- handleKeySet(): Set  [API 9+]
- keySet(): Set  [API 9+]
+ parent [field]  [API 1+]
+ setParent(ResourceBundle): void  [API 1+]
```

## ResourceBundle.Control — declaration; API 9+ (0 +, 0 *, 0 ~, 11 -)

```
- Control()  [API 9+]
- FORMAT_CLASS [field]  [API 9+]
- FORMAT_DEFAULT [field]  [API 9+]
- FORMAT_PROPERTIES [field]  [API 9+]
- getCandidateLocales(String, Locale): List  [API 9+]
- getControl(List): ResourceBundle.Control [static]  [API 9+]
- getFallbackLocale(String, Locale): Locale  [API 9+]
- getFormats(String): List  [API 9+]
- getNoFallbackControl(List): ResourceBundle.Control [static]  [API 9+]
- getTimeToLive(String, Locale): long  [API 9+]
- needsReload(String, Locale, String, ClassLoader, ResourceBundle, long): boolean  [API 9+]
- newBundle(String, Locale, String, ClassLoader, boolean): ResourceBundle  [API 9+]
- toBundleName(String, Locale): String  [API 9+]
- toResourceName(String, String): String  [API 9+]
```

## Scanner — framework (a2w.jdk.util.Scanner); API 1+ (71 +, 0 *, 1 ~, 0 -)

```
+ Scanner(File)  [API 1+]
+ Scanner(File, Charset)  [API 34+]
+ Scanner(File, String)  [API 1+]
+ Scanner(InputStream)  [API 1+]
+ Scanner(InputStream, Charset)  [API 34+]
+ Scanner(InputStream, String)  [API 1+]
+ Scanner(Path)  [API 26+]
+ Scanner(Path, Charset)  [API 34+]
+ Scanner(Path, String)  [API 26+]
+ Scanner(Readable)  [API 1+]
+ Scanner(ReadableByteChannel)  [API 1+]
+ Scanner(ReadableByteChannel, Charset)  [API 34+]
+ Scanner(ReadableByteChannel, String)  [API 1+]
+ Scanner(String)  [API 1+]
+ close(): void  [API 1+]
+ delimiter(): Pattern  [API 1+]
+ findAll(Pattern): Stream  [API 34+]
+ findAll(String): Stream  [API 34+]
+ findInLine(Pattern): String  [API 1+]
+ findInLine(String): String  [API 1+]
+ findWithinHorizon(Pattern, int): String  [API 1+]
+ findWithinHorizon(String, int): String  [API 1+]
+ hasNext(): boolean  [API 1+]
+ hasNext(Pattern): boolean  [API 1+]
+ hasNext(String): boolean  [API 1+]
+ hasNextBigDecimal(): boolean  [API 1+]
+ hasNextBigInteger(): boolean  [API 1+]
+ hasNextBigInteger(int): boolean  [API 1+]
+ hasNextBoolean(): boolean  [API 1+]
+ hasNextByte(): boolean  [API 1+]
+ hasNextByte(int): boolean  [API 1+]
+ hasNextDouble(): boolean  [API 1+]
+ hasNextFloat(): boolean  [API 1+]
+ hasNextInt(): boolean  [API 1+]
+ hasNextInt(int): boolean  [API 1+]
+ hasNextLine(): boolean  [API 1+]
+ hasNextLong(): boolean  [API 1+]
+ hasNextLong(int): boolean  [API 1+]
+ hasNextShort(): boolean  [API 1+]
+ hasNextShort(int): boolean  [API 1+]
+ ioException(): IOException  [API 1+]
+ locale(): Locale  [API 1+]
+ match(): MatchResult  [API 1+]
+ next(): String  [API 1+]
+ next(Pattern): String  [API 1+]
+ next(String): String  [API 1+]
+ nextBigDecimal(): BigDecimal  [API 1+]
+ nextBigInteger(): BigInteger  [API 1+]
+ nextBigInteger(int): BigInteger  [API 1+]
+ nextBoolean(): boolean  [API 1+]
+ nextByte(): byte  [API 1+]
+ nextByte(int): byte  [API 1+]
+ nextDouble(): double  [API 1+]
+ nextFloat(): float  [API 1+]
+ nextInt(): int  [API 1+]
+ nextInt(int): int  [API 1+]
+ nextLine(): String  [API 1+]
+ nextLong(): long  [API 1+]
+ nextLong(int): long  [API 1+]
+ nextShort(): short  [API 1+]
+ nextShort(int): short  [API 1+]
+ radix(): int  [API 1+]
~ remove(): void  [API 1+]
+ reset(): Scanner  [API 9+]
+ skip(Pattern): Scanner  [API 1+]
+ skip(String): Scanner  [API 1+]
+ toString(): String  [API 1+]
+ tokens(): Stream  [API 34+]
+ useDelimiter(Pattern): Scanner  [API 1+]
+ useDelimiter(String): Scanner  [API 1+]
+ useLocale(Locale): Scanner  [API 1+]
+ useRadix(int): Scanner  [API 1+]
```

## SequencedCollection — TeaVM class library; API 35+ (7 +, 0 *, 0 ~, 0 -)

```
+ addFirst(Object): void  [API 35+]
+ addLast(Object): void  [API 35+]
+ getFirst(): Object  [API 35+]
+ getLast(): Object  [API 35+]
+ removeFirst(): Object  [API 35+]
+ removeLast(): Object  [API 35+]
+ reversed(): SequencedCollection  [API 35+]
```

## SequencedMap — TeaVM class library; API 35+ (10 +, 0 *, 0 ~, 0 -)

```
+ firstEntry(): Map.Entry  [API 35+]
+ lastEntry(): Map.Entry  [API 35+]
+ pollFirstEntry(): Map.Entry  [API 35+]
+ pollLastEntry(): Map.Entry  [API 35+]
+ putFirst(Object, Object): Object  [API 35+]
+ putLast(Object, Object): Object  [API 35+]
+ reversed(): SequencedMap  [API 35+]
+ sequencedEntrySet(): SequencedSet  [API 35+]
+ sequencedKeySet(): SequencedSet  [API 35+]
+ sequencedValues(): SequencedCollection  [API 35+]
```

## SequencedSet — TeaVM class library; API 35+ (1 +, 0 *, 0 ~, 0 -)

```
+ reversed(): SequencedSet  [API 35+]
```

## ServiceConfigurationError — TeaVM class library; API 9+ (2 +, 0 *, 0 ~, 0 -)

```
+ ServiceConfigurationError(String)  [API 9+]
+ ServiceConfigurationError(String, Throwable)  [API 9+]
```

## ServiceLoader — TeaVM class library; API 9+ (6 +, 0 *, 0 ~, 2 -)

```
- findFirst(): Optional  [API 34+]
+ iterator(): Iterator  [API 9+]
+ load(Class): ServiceLoader [static]  [API 9+]
+ load(Class, ClassLoader): ServiceLoader [static]  [API 9+]
+ loadInstalled(Class): ServiceLoader [static]  [API 9+]
+ reload(): void  [API 9+]
- stream(): Stream  [API 35+]
+ toString(): String  [API 9+]
```

## ServiceLoader.Provider — declaration; API 35+ (2 +, 0 *, 0 ~, 0 -)

```
+ get(): Object  [API 35+]
+ type(): Class  [API 35+]
```

## Set — TeaVM class library; API 1+ (29 +, 0 *, 0 ~, 0 -)

```
+ add(Object): boolean  [API 1+]
+ addAll(Collection): boolean  [API 1+]
+ clear(): void  [API 1+]
+ contains(Object): boolean  [API 1+]
+ containsAll(Collection): boolean  [API 1+]
+ copyOf(Collection): Set [static]  [API 31+]
+ equals(Object): boolean  [API 1+]
+ hashCode(): int  [API 1+]
+ isEmpty(): boolean  [API 1+]
+ iterator(): Iterator  [API 1+]
+ of(): Set [static]  [API 30+]
+ of(Object): Set [static]  [API 30+]
+ of(Object, Object): Set [static]  [API 30+]
+ of(Object, Object, Object): Set [static]  [API 30+]
+ of(Object, Object, Object, Object): Set [static]  [API 30+]
+ of(Object, Object, Object, Object, Object): Set [static]  [API 30+]
+ of(Object, Object, Object, Object, Object, Object): Set [static]  [API 30+]
+ of(Object, Object, Object, Object, Object, Object, Object): Set [static]  [API 30+]
+ of(Object, Object, Object, Object, Object, Object, Object, Object): Set [static]  [API 30+]
+ of(Object, Object, Object, Object, Object, Object, Object, Object, Object): Set [static]  [API 30+]
+ of(Object, Object, Object, Object, Object, Object, Object, Object, Object, Object): Set [static]  [API 30+]
+ of(Object[]): Set [static]  [API 30+]
+ remove(Object): boolean  [API 1+]
+ removeAll(Collection): boolean  [API 1+]
+ retainAll(Collection): boolean  [API 1+]
+ size(): int  [API 1+]
+ spliterator(): Spliterator  [API 1+]
+ toArray(): Object[]  [API 1+]
+ toArray(Object[]): Object[]  [API 1+]
```

## SimpleTimeZone — declaration; API 1+ (0 +, 0 *, 0 ~, 25 -)

```
- SimpleTimeZone(int, String)  [API 1+]
- SimpleTimeZone(int, String, int, int, int, int, int, int, int, int)  [API 1+]
- SimpleTimeZone(int, String, int, int, int, int, int, int, int, int, int)  [API 1+]
- SimpleTimeZone(int, String, int, int, int, int, int, int, int, int, int, int, int)  [API 1+]
- clone(): Object  [API 1+]
- equals(Object): boolean  [API 1+]
- getDSTSavings(): int  [API 1+]
- getOffset(int, int, int, int, int, int): int  [API 1+]
- getOffset(long): int  [API 1+]
- getRawOffset(): int  [API 1+]
- hasSameRules(TimeZone): boolean  [API 1+]
- hashCode(): int  [API 1+]
- inDaylightTime(Date): boolean  [API 1+]
- observesDaylightTime(): boolean  [API 1+]
- setDSTSavings(int): void  [API 1+]
- setEndRule(int, int, int): void  [API 1+]
- setEndRule(int, int, int, int): void  [API 1+]
- setEndRule(int, int, int, int, boolean): void  [API 1+]
- setRawOffset(int): void  [API 1+]
- setStartRule(int, int, int): void  [API 1+]
- setStartRule(int, int, int, int): void  [API 1+]
- setStartRule(int, int, int, int, boolean): void  [API 1+]
- setStartYear(int): void  [API 1+]
- toString(): String  [API 1+]
- useDaylightTime(): boolean  [API 1+]
```

## SortedMap — TeaVM class library; API 1+ (11 +, 0 *, 0 ~, 1 -)

```
+ comparator(): Comparator  [API 1+]
+ entrySet(): Set  [API 1+]
+ firstKey(): Object  [API 1+]
+ headMap(Object): SortedMap  [API 1+]
+ keySet(): Set  [API 1+]
+ lastKey(): Object  [API 1+]
+ putFirst(Object, Object): Object  [API 1+]
+ putLast(Object, Object): Object  [API 1+]
- reversed(): SortedMap  [API 35+]
+ subMap(Object, Object): SortedMap  [API 1+]
+ tailMap(Object): SortedMap  [API 1+]
+ values(): Collection  [API 1+]
```

## SortedSet — TeaVM class library; API 1+ (14 +, 0 *, 0 ~, 0 -)

```
+ addFirst(Object): void  [API 1+]
+ addLast(Object): void  [API 1+]
+ comparator(): Comparator  [API 1+]
+ first(): Object  [API 1+]
+ getFirst(): Object  [API 1+]
+ getLast(): Object  [API 1+]
+ headSet(Object): SortedSet  [API 1+]
+ last(): Object  [API 1+]
+ removeFirst(): Object  [API 1+]
+ removeLast(): Object  [API 1+]
+ reversed(): SortedSet  [API 35+]
+ spliterator(): Spliterator  [API 1+]
+ subSet(Object, Object): SortedSet  [API 1+]
+ tailSet(Object): SortedSet  [API 1+]
```

## Spliterator — TeaVM class library; API 24+ (8 +, 0 *, 0 ~, 0 -)

```
+ characteristics(): int  [API 24+]
+ estimateSize(): long  [API 24+]
+ forEachRemaining(Consumer): void  [API 24+]
+ getComparator(): Comparator  [API 24+]
+ getExactSizeIfKnown(): long  [API 24+]
+ hasCharacteristics(int): boolean  [API 24+]
+ tryAdvance(Consumer): boolean  [API 24+]
+ trySplit(): Spliterator  [API 24+]
```

## Spliterator.OfDouble — TeaVM class library; API 24+ (4 +, 0 *, 0 ~, 1 -)

```
+ forEachRemaining(Consumer): void  [API 24+]
+ forEachRemaining(DoubleConsumer): void  [API 24+]
+ tryAdvance(Consumer): boolean  [API 24+]
+ tryAdvance(DoubleConsumer): boolean  [API 24+]
- trySplit(): Spliterator.OfDouble  [API 24+]
```

## Spliterator.OfInt — TeaVM class library; API 24+ (4 +, 0 *, 0 ~, 1 -)

```
+ forEachRemaining(Consumer): void  [API 24+]
+ forEachRemaining(IntConsumer): void  [API 24+]
+ tryAdvance(Consumer): boolean  [API 24+]
+ tryAdvance(IntConsumer): boolean  [API 24+]
- trySplit(): Spliterator.OfInt  [API 24+]
```

## Spliterator.OfLong — TeaVM class library; API 24+ (4 +, 0 *, 0 ~, 1 -)

```
+ forEachRemaining(Consumer): void  [API 24+]
+ forEachRemaining(LongConsumer): void  [API 24+]
+ tryAdvance(Consumer): boolean  [API 24+]
+ tryAdvance(LongConsumer): boolean  [API 24+]
- trySplit(): Spliterator.OfLong  [API 24+]
```

## Spliterator.OfPrimitive — TeaVM class library; API 24+ (3 +, 0 *, 0 ~, 0 -)

```
+ forEachRemaining(Object): void  [API 24+]
+ tryAdvance(Object): boolean  [API 24+]
+ trySplit(): Spliterator.OfPrimitive  [API 24+]
```

## Spliterators — TeaVM class library; API 24+ (25 +, 0 *, 0 ~, 0 -)

```
+ emptyDoubleSpliterator(): Spliterator.OfDouble [static]  [API 24+]
+ emptyIntSpliterator(): Spliterator.OfInt [static]  [API 24+]
+ emptyLongSpliterator(): Spliterator.OfLong [static]  [API 24+]
+ emptySpliterator(): Spliterator [static]  [API 24+]
+ iterator(Spliterator): Iterator [static]  [API 24+]
+ iterator(Spliterator.OfDouble): PrimitiveIterator.OfDouble [static]  [API 24+]
+ iterator(Spliterator.OfInt): PrimitiveIterator.OfInt [static]  [API 24+]
+ iterator(Spliterator.OfLong): PrimitiveIterator.OfLong [static]  [API 24+]
+ spliterator(Collection, int): Spliterator [static]  [API 24+]
+ spliterator(Iterator, long, int): Spliterator [static]  [API 24+]
+ spliterator(Object[], int): Spliterator [static]  [API 24+]
+ spliterator(Object[], int, int, int): Spliterator [static]  [API 24+]
+ spliterator(PrimitiveIterator.OfDouble, long, int): Spliterator.OfDouble [static]  [API 24+]
+ spliterator(PrimitiveIterator.OfInt, long, int): Spliterator.OfInt [static]  [API 24+]
+ spliterator(PrimitiveIterator.OfLong, long, int): Spliterator.OfLong [static]  [API 24+]
+ spliterator(double[], int): Spliterator.OfDouble [static]  [API 24+]
+ spliterator(double[], int, int, int): Spliterator.OfDouble [static]  [API 24+]
+ spliterator(int[], int): Spliterator.OfInt [static]  [API 24+]
+ spliterator(int[], int, int, int): Spliterator.OfInt [static]  [API 24+]
+ spliterator(long[], int): Spliterator.OfLong [static]  [API 24+]
+ spliterator(long[], int, int, int): Spliterator.OfLong [static]  [API 24+]
+ spliteratorUnknownSize(Iterator, int): Spliterator [static]  [API 24+]
+ spliteratorUnknownSize(PrimitiveIterator.OfDouble, int): Spliterator.OfDouble [static]  [API 24+]
+ spliteratorUnknownSize(PrimitiveIterator.OfInt, int): Spliterator.OfInt [static]  [API 24+]
+ spliteratorUnknownSize(PrimitiveIterator.OfLong, int): Spliterator.OfLong [static]  [API 24+]
```

## Spliterators.AbstractDoubleSpliterator — framework (a2w.jdk.util.Spliterators$AbstractDoubleSpliterator); API 24+ (4 +, 0 *, 0 ~, 0 -)

```
+ AbstractDoubleSpliterator(long, int)  [API 24+]
+ characteristics(): int  [API 24+]
+ estimateSize(): long  [API 24+]
+ trySplit(): Spliterator.OfDouble  [API 24+]
```

## Spliterators.AbstractIntSpliterator — framework (a2w.jdk.util.Spliterators$AbstractIntSpliterator); API 24+ (4 +, 0 *, 0 ~, 0 -)

```
+ AbstractIntSpliterator(long, int)  [API 24+]
+ characteristics(): int  [API 24+]
+ estimateSize(): long  [API 24+]
+ trySplit(): Spliterator.OfInt  [API 24+]
```

## Spliterators.AbstractLongSpliterator — framework (a2w.jdk.util.Spliterators$AbstractLongSpliterator); API 24+ (4 +, 0 *, 0 ~, 0 -)

```
+ AbstractLongSpliterator(long, int)  [API 24+]
+ characteristics(): int  [API 24+]
+ estimateSize(): long  [API 24+]
+ trySplit(): Spliterator.OfLong  [API 24+]
```

## Spliterators.AbstractSpliterator — framework (a2w.jdk.util.Spliterators$AbstractSpliterator); API 24+ (4 +, 0 *, 0 ~, 0 -)

```
+ AbstractSpliterator(long, int)  [API 24+]
+ characteristics(): int  [API 24+]
+ estimateSize(): long  [API 24+]
+ trySplit(): Spliterator  [API 24+]
```

## SplittableRandom — framework (a2w.jdk.util.SplittableRandom); API 24+ (31 +, 0 *, 0 ~, 0 -)

```
+ SplittableRandom()  [API 24+]
+ SplittableRandom(long)  [API 24+]
+ doubles(): DoubleStream  [API 24+]
+ doubles(double, double): DoubleStream  [API 24+]
+ doubles(long): DoubleStream  [API 24+]
+ doubles(long, double, double): DoubleStream  [API 24+]
+ ints(): IntStream  [API 24+]
+ ints(int, int): IntStream  [API 24+]
+ ints(long): IntStream  [API 24+]
+ ints(long, int, int): IntStream  [API 24+]
+ longs(): LongStream  [API 24+]
+ longs(long): LongStream  [API 24+]
+ longs(long, long): LongStream  [API 24+]
+ longs(long, long, long): LongStream  [API 24+]
+ nextBoolean(): boolean  [API 24-34, removed in 35]
+ nextBytes(byte[]): void  [API 33+]
+ nextDouble(): double  [API 24-34, removed in 35]
+ nextDouble(double): double  [API 24-34, removed in 35]
+ nextDouble(double, double): double  [API 24-34, removed in 35]
+ nextInt(): int  [API 24+]
+ nextInt(int): int  [API 24-34, removed in 35]
+ nextInt(int, int): int  [API 24-34, removed in 35]
+ nextLong(): long  [API 24+]
+ nextLong(long): long  [API 24-34, removed in 35]
+ nextLong(long, long): long  [API 24-34, removed in 35]
+ split(): SplittableRandom  [API 24+]
+ split(RandomGenerator.SplittableGenerator): SplittableRandom  [API 35+]
+ splits(): Stream  [API 24+]
+ splits(RandomGenerator.SplittableGenerator): Stream  [API 24+]
+ splits(long): Stream  [API 24+]
+ splits(long, RandomGenerator.SplittableGenerator): Stream  [API 24+]
```

## Stack — TeaVM class library; API 1+ (6 +, 0 *, 0 ~, 0 -)

```
+ Stack()  [API 1+]
+ empty(): boolean  [API 1+]
+ peek(): Object  [API 1+]
+ pop(): Object  [API 1+]
+ push(Object): Object  [API 1+]
+ search(Object): int  [API 1+]
```

## StringJoiner — framework (a2w.jdk.util.StringJoiner); API 24+ (7 +, 0 *, 0 ~, 0 -)

```
+ StringJoiner(CharSequence)  [API 24+]
+ StringJoiner(CharSequence, CharSequence, CharSequence)  [API 24+]
+ add(CharSequence): StringJoiner  [API 24+]
+ length(): int  [API 24+]
+ merge(StringJoiner): StringJoiner  [API 24+]
+ setEmptyValue(CharSequence): StringJoiner  [API 24+]
+ toString(): String  [API 24+]
```

## StringTokenizer — TeaVM class library; API 1+ (9 +, 0 *, 0 ~, 0 -)

```
+ StringTokenizer(String)  [API 1+]
+ StringTokenizer(String, String)  [API 1+]
+ StringTokenizer(String, String, boolean)  [API 1+]
+ countTokens(): int  [API 1+]
+ hasMoreElements(): boolean  [API 1+]
+ hasMoreTokens(): boolean  [API 1+]
+ nextElement(): Object  [API 1+]
+ nextToken(): String  [API 1+]
+ nextToken(String): String  [API 1+]
```

## TimeZone — TeaVM class library; API 1+ (24 +, 0 *, 0 ~, 0 -)

```
+ TimeZone()  [API 1+]
+ clone(): Object  [API 1+]
+ getAvailableIDs(): String[] [static]  [API 1+]
+ getAvailableIDs(int): String[] [static]  [API 1+]
+ getDSTSavings(): int  [API 1+]
+ getDefault(): TimeZone [static]  [API 1+]
+ getDisplayName(): String  [API 1+]
+ getDisplayName(Locale): String  [API 1+]
+ getDisplayName(boolean, int): String  [API 1+]
+ getDisplayName(boolean, int, Locale): String  [API 1+]
+ getID(): String  [API 1+]
+ getOffset(int, int, int, int, int, int): int  [API 1+]
+ getOffset(long): int  [API 1+]
+ getRawOffset(): int  [API 1+]
+ getTimeZone(String): TimeZone [static]  [API 1+]
+ getTimeZone(ZoneId): TimeZone [static]  [API 26+]
+ hasSameRules(TimeZone): boolean  [API 1+]
+ inDaylightTime(Date): boolean  [API 1+]
+ observesDaylightTime(): boolean  [API 24+]
+ setDefault(TimeZone): void [static]  [API 1+]
+ setID(String): void  [API 1+]
+ setRawOffset(int): void  [API 1+]
+ toZoneId(): ZoneId  [API 26+]
+ useDaylightTime(): boolean  [API 1+]
```

## Timer — TeaVM class library; API 1+ (12 +, 0 *, 0 ~, 0 -)

```
+ Timer()  [API 1+]
+ Timer(String)  [API 1+]
+ Timer(String, boolean)  [API 1+]
+ Timer(boolean)  [API 1+]
+ cancel(): void  [API 1+]
+ purge(): int  [API 1+]
+ schedule(TimerTask, Date): void  [API 1+]
+ schedule(TimerTask, Date, long): void  [API 1+]
+ schedule(TimerTask, long): void  [API 1+]
+ schedule(TimerTask, long, long): void  [API 1+]
+ scheduleAtFixedRate(TimerTask, Date, long): void  [API 1+]
+ scheduleAtFixedRate(TimerTask, long, long): void  [API 1+]
```

## TimerTask — TeaVM class library; API 1+ (3 +, 0 *, 0 ~, 1 -)

```
+ TimerTask()  [API 1+]
+ cancel(): boolean  [API 1+]
+ run(): void  [API 1+]
- scheduledExecutionTime(): long  [API 1+]
```

## TooManyListenersException — declaration; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ TooManyListenersException()  [API 1+]
+ TooManyListenersException(String)  [API 1+]
```

## TreeMap — TeaVM class library; API 1+ (51 +, 0 *, 0 ~, 0 -)

```
+ TreeMap()  [API 1+]
+ TreeMap(Comparator)  [API 1+]
+ TreeMap(Map)  [API 1+]
+ TreeMap(SortedMap)  [API 1+]
+ ceilingEntry(Object): Map.Entry  [API 1+]
+ ceilingKey(Object): Object  [API 1+]
+ clear(): void  [API 1+]
+ clone(): Object  [API 1+]
+ comparator(): Comparator  [API 1+]
+ compute(Object, BiFunction): Object  [API 1+]
+ computeIfAbsent(Object, Function): Object  [API 1+]
+ computeIfPresent(Object, BiFunction): Object  [API 1+]
+ containsKey(Object): boolean  [API 1+]
+ containsValue(Object): boolean  [API 1+]
+ descendingKeySet(): NavigableSet  [API 1+]
+ descendingMap(): NavigableMap  [API 1+]
+ entrySet(): Set  [API 1+]
+ firstEntry(): Map.Entry  [API 1+]
+ firstKey(): Object  [API 1+]
+ floorEntry(Object): Map.Entry  [API 1+]
+ floorKey(Object): Object  [API 1+]
+ forEach(BiConsumer): void  [API 1+]
+ get(Object): Object  [API 1+]
+ headMap(Object): SortedMap  [API 1+]
+ headMap(Object, boolean): NavigableMap  [API 1+]
+ higherEntry(Object): Map.Entry  [API 1+]
+ higherKey(Object): Object  [API 1+]
+ keySet(): Set  [API 1+]
+ lastEntry(): Map.Entry  [API 1+]
+ lastKey(): Object  [API 1+]
+ lowerEntry(Object): Map.Entry  [API 1+]
+ lowerKey(Object): Object  [API 1+]
+ merge(Object, Object, BiFunction): Object  [API 1+]
+ navigableKeySet(): NavigableSet  [API 1+]
+ pollFirstEntry(): Map.Entry  [API 1+]
+ pollLastEntry(): Map.Entry  [API 1+]
+ put(Object, Object): Object  [API 1+]
+ putAll(Map): void  [API 1+]
+ putFirst(Object, Object): Object  [API 1+]
+ putIfAbsent(Object, Object): Object  [API 1+]
+ putLast(Object, Object): Object  [API 1+]
+ remove(Object): Object  [API 1+]
+ replace(Object, Object): Object  [API 1+]
+ replace(Object, Object, Object): boolean  [API 1+]
+ replaceAll(BiFunction): void  [API 1+]
+ size(): int  [API 1+]
+ subMap(Object, Object): SortedMap  [API 1+]
+ subMap(Object, boolean, Object, boolean): NavigableMap  [API 1+]
+ tailMap(Object): SortedMap  [API 1+]
+ tailMap(Object, boolean): NavigableMap  [API 1+]
+ values(): Collection  [API 1+]
```

## TreeSet — TeaVM class library; API 1+ (33 +, 0 *, 0 ~, 0 -)

```
+ TreeSet()  [API 1+]
+ TreeSet(Collection)  [API 1+]
+ TreeSet(Comparator)  [API 1+]
+ TreeSet(SortedSet)  [API 1+]
+ add(Object): boolean  [API 1+]
+ addAll(Collection): boolean  [API 1+]
+ addFirst(Object): void  [API 1+]
+ addLast(Object): void  [API 1+]
+ ceiling(Object): Object  [API 1+]
+ clear(): void  [API 1+]
+ clone(): Object  [API 1+]
+ comparator(): Comparator  [API 1+]
+ contains(Object): boolean  [API 1+]
+ descendingIterator(): Iterator  [API 1+]
+ descendingSet(): NavigableSet  [API 1+]
+ first(): Object  [API 1+]
+ floor(Object): Object  [API 1+]
+ headSet(Object): SortedSet  [API 1+]
+ headSet(Object, boolean): NavigableSet  [API 1+]
+ higher(Object): Object  [API 1+]
+ isEmpty(): boolean  [API 1+]
+ iterator(): Iterator  [API 1+]
+ last(): Object  [API 1+]
+ lower(Object): Object  [API 1+]
+ pollFirst(): Object  [API 1+]
+ pollLast(): Object  [API 1+]
+ remove(Object): boolean  [API 1+]
+ size(): int  [API 1+]
+ spliterator(): Spliterator  [API 1+]
+ subSet(Object, Object): SortedSet  [API 1+]
+ subSet(Object, boolean, Object, boolean): NavigableSet  [API 1+]
+ tailSet(Object): SortedSet  [API 1+]
+ tailSet(Object, boolean): NavigableSet  [API 1+]
```

## UUID — framework (a2w.jdk.util.UUID); API 1+ (12 +, 0 *, 0 ~, 3 -)

```
+ UUID(long, long)  [API 1+]
- clockSequence(): int  [API 1+]
+ compareTo(UUID): int  [API 1+]
+ equals(Object): boolean  [API 1+]
+ fromString(String): UUID [static]  [API 1+]
+ getLeastSignificantBits(): long  [API 1+]
+ getMostSignificantBits(): long  [API 1+]
+ hashCode(): int  [API 1+]
+ nameUUIDFromBytes(byte[]): UUID [static]  [API 1+]
- node(): long  [API 1+]
+ randomUUID(): UUID [static]  [API 1+]
- timestamp(): long  [API 1+]
+ toString(): String  [API 1+]
+ variant(): int  [API 1+]
+ version(): int  [API 1+]
```

## UnknownFormatConversionException — TeaVM class library; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ UnknownFormatConversionException(String)  [API 1+]
+ getConversion(): String  [API 1+]
+ getMessage(): String  [API 1+]
```

## UnknownFormatFlagsException — missing; API 1+ (0 +, 0 *, 0 ~, 3 -)

```
- UnknownFormatFlagsException(String)  [API 1+]
- getFlags(): String  [API 1+]
- getMessage(): String  [API 1+]
```

## Vector — TeaVM class library; API 1+ (54 +, 0 *, 0 ~, 0 -)

```
+ Vector()  [API 1+]
+ Vector(Collection)  [API 1+]
+ Vector(int)  [API 1+]
+ Vector(int, int)  [API 1+]
+ add(Object): boolean  [API 1+]
+ add(int, Object): void  [API 1+]
+ addAll(Collection): boolean  [API 1+]
+ addAll(int, Collection): boolean  [API 1+]
+ addElement(Object): void  [API 1+]
+ capacity(): int  [API 1+]
+ capacityIncrement [field]  [API 1+]
+ clear(): void  [API 1+]
+ clone(): Object  [API 1+]
+ contains(Object): boolean  [API 1+]
+ containsAll(Collection): boolean  [API 1+]
+ copyInto(Object[]): void  [API 1+]
+ elementAt(int): Object  [API 1+]
+ elementCount [field]  [API 1+]
+ elementData [field]  [API 1+]
+ elements(): Enumeration  [API 1+]
+ ensureCapacity(int): void  [API 1+]
+ equals(Object): boolean  [API 1+]
+ firstElement(): Object  [API 1+]
+ forEach(Consumer): void  [API 1+]
+ get(int): Object  [API 1+]
+ hashCode(): int  [API 1+]
+ indexOf(Object): int  [API 1+]
+ indexOf(Object, int): int  [API 1+]
+ insertElementAt(Object, int): void  [API 1+]
+ isEmpty(): boolean  [API 1+]
+ iterator(): Iterator  [API 1+]
+ lastElement(): Object  [API 1+]
+ lastIndexOf(Object): int  [API 1+]
+ lastIndexOf(Object, int): int  [API 1+]
+ listIterator(): ListIterator  [API 1+]
+ listIterator(int): ListIterator  [API 1+]
+ remove(Object): boolean  [API 1+]
+ remove(int): Object  [API 1+]
+ removeAll(Collection): boolean  [API 1+]
+ removeAllElements(): void  [API 1+]
+ removeElement(Object): boolean  [API 1+]
+ removeElementAt(int): void  [API 1+]
+ removeIf(Predicate): boolean  [API 1+]
+ removeRange(int, int): void  [API 1+]
+ replaceAll(UnaryOperator): void  [API 1+]
+ retainAll(Collection): boolean  [API 1+]
+ set(int, Object): Object  [API 1+]
+ setElementAt(Object, int): void  [API 1+]
+ setSize(int): void  [API 1+]
+ size(): int  [API 1+]
+ sort(Comparator): void  [API 1+]
+ spliterator(): Spliterator  [API 1+]
+ subList(int, int): List  [API 1+]
+ toArray(): Object[]  [API 1+]
+ toArray(Object[]): Object[]  [API 1+]
+ toString(): String  [API 1+]
+ trimToSize(): void  [API 1+]
```

## WeakHashMap — TeaVM class library; API 1+ (18 +, 0 *, 0 ~, 1 -)

```
+ WeakHashMap()  [API 1+]
+ WeakHashMap(Map)  [API 1+]
+ WeakHashMap(int)  [API 1+]
+ WeakHashMap(int, float)  [API 1+]
+ clear(): void  [API 1+]
+ containsKey(Object): boolean  [API 1+]
+ containsValue(Object): boolean  [API 1+]
+ entrySet(): Set  [API 1+]
+ forEach(BiConsumer): void  [API 1+]
+ get(Object): Object  [API 1+]
+ isEmpty(): boolean  [API 1+]
+ keySet(): Set  [API 1+]
- newWeakHashMap(int): WeakHashMap  [API 36+]
+ put(Object, Object): Object  [API 1+]
+ putAll(Map): void  [API 1+]
+ remove(Object): Object  [API 1+]
+ replaceAll(BiFunction): void  [API 1+]
+ size(): int  [API 1+]
+ values(): Collection  [API 1+]
```

