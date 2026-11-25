# java.util.stream

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## BaseStream — TeaVM class library; API 24+ (8 +, 0 *, 0 ~, 0 -)

```
+ close(): void  [API 24+]
+ isParallel(): boolean  [API 24+]
+ iterator(): Iterator  [API 24+]
+ onClose(Runnable): BaseStream  [API 24+]
+ parallel(): BaseStream  [API 24+]
+ sequential(): BaseStream  [API 24+]
+ spliterator(): Spliterator  [API 24+]
+ unordered(): BaseStream  [API 24+]
```

## Collector — TeaVM class library; API 24+ (7 +, 0 *, 0 ~, 0 -)

```
+ accumulator(): BiConsumer  [API 24+]
+ characteristics(): Set  [API 24+]
+ combiner(): BinaryOperator  [API 24+]
+ finisher(): Function  [API 24+]
+ of(Supplier, BiConsumer, BinaryOperator, Collector.Characteristics[]): Collector [static]  [API 24+]
+ of(Supplier, BiConsumer, BinaryOperator, Function, Collector.Characteristics[]): Collector [static]  [API 24+]
+ supplier(): Supplier  [API 24+]
```

## Collector.Characteristics — TeaVM class library; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ CONCURRENT [field]  [API 24+]
+ IDENTITY_FINISH [field]  [API 24+]
+ UNORDERED [field]  [API 24+]
+ valueOf(String): Collector.Characteristics [static]  [API 24+]
+ values(): Collector.Characteristics[] [static]  [API 24+]
```

## Collectors — TeaVM class library; API 24+ (44 +, 0 *, 0 ~, 0 -)

```
+ averagingDouble(ToDoubleFunction): Collector [static]  [API 24+]
+ averagingInt(ToIntFunction): Collector [static]  [API 24+]
+ averagingLong(ToLongFunction): Collector [static]  [API 24+]
+ collectingAndThen(Collector, Function): Collector [static]  [API 24+]
+ counting(): Collector [static]  [API 24+]
+ filtering(Predicate, Collector): Collector [static]  [API 33+]
+ flatMapping(Function, Collector): Collector [static]  [API 33+]
+ groupingBy(Function): Collector [static]  [API 24+]
+ groupingBy(Function, Collector): Collector [static]  [API 24+]
+ groupingBy(Function, Supplier, Collector): Collector [static]  [API 24+]
+ groupingByConcurrent(Function): Collector [static]  [API 24+]
+ groupingByConcurrent(Function, Collector): Collector [static]  [API 24+]
+ groupingByConcurrent(Function, Supplier, Collector): Collector [static]  [API 24+]
+ joining(): Collector [static]  [API 24+]
+ joining(CharSequence): Collector [static]  [API 24+]
+ joining(CharSequence, CharSequence, CharSequence): Collector [static]  [API 24+]
+ mapping(Function, Collector): Collector [static]  [API 24+]
+ maxBy(Comparator): Collector [static]  [API 24+]
+ minBy(Comparator): Collector [static]  [API 24+]
+ partitioningBy(Predicate): Collector [static]  [API 24+]
+ partitioningBy(Predicate, Collector): Collector [static]  [API 24+]
+ reducing(BinaryOperator): Collector [static]  [API 24+]
+ reducing(Object, BinaryOperator): Collector [static]  [API 24+]
+ reducing(Object, Function, BinaryOperator): Collector [static]  [API 24+]
+ summarizingDouble(ToDoubleFunction): Collector [static]  [API 24+]
+ summarizingInt(ToIntFunction): Collector [static]  [API 24+]
+ summarizingLong(ToLongFunction): Collector [static]  [API 24+]
+ summingDouble(ToDoubleFunction): Collector [static]  [API 24+]
+ summingInt(ToIntFunction): Collector [static]  [API 24+]
+ summingLong(ToLongFunction): Collector [static]  [API 24+]
+ teeing(Collector, Collector, BiFunction): Collector [static]  [API 35+]
+ toCollection(Supplier): Collector [static]  [API 24+]
+ toConcurrentMap(Function, Function): Collector [static]  [API 24+]
+ toConcurrentMap(Function, Function, BinaryOperator): Collector [static]  [API 24+]
+ toConcurrentMap(Function, Function, BinaryOperator, Supplier): Collector [static]  [API 24+]
+ toList(): Collector [static]  [API 24+]
+ toMap(Function, Function): Collector [static]  [API 24+]
+ toMap(Function, Function, BinaryOperator): Collector [static]  [API 24+]
+ toMap(Function, Function, BinaryOperator, Supplier): Collector [static]  [API 24+]
+ toSet(): Collector [static]  [API 24+]
+ toUnmodifiableList(): Collector [static]  [API 33+]
+ toUnmodifiableMap(Function, Function): Collector [static]  [API 33+]
+ toUnmodifiableMap(Function, Function, BinaryOperator): Collector [static]  [API 33+]
+ toUnmodifiableSet(): Collector [static]  [API 33+]
```

## DoubleStream — TeaVM class library; API 24+ (44 +, 0 *, 0 ~, 0 -)

```
+ allMatch(DoublePredicate): boolean  [API 24+]
+ anyMatch(DoublePredicate): boolean  [API 24+]
+ average(): OptionalDouble  [API 24+]
+ boxed(): Stream  [API 24+]
+ builder(): DoubleStream.Builder [static]  [API 24+]
+ collect(Supplier, ObjDoubleConsumer, BiConsumer): Object  [API 24+]
+ concat(DoubleStream, DoubleStream): DoubleStream [static]  [API 24+]
+ count(): long  [API 24+]
+ distinct(): DoubleStream  [API 24+]
+ dropWhile(DoublePredicate): DoubleStream  [API 35+]
+ empty(): DoubleStream [static]  [API 24+]
+ filter(DoublePredicate): DoubleStream  [API 24+]
+ findAny(): OptionalDouble  [API 24+]
+ findFirst(): OptionalDouble  [API 24+]
+ flatMap(DoubleFunction): DoubleStream  [API 24+]
+ forEach(DoubleConsumer): void  [API 24+]
+ forEachOrdered(DoubleConsumer): void  [API 24+]
+ generate(DoubleSupplier): DoubleStream [static]  [API 24+]
+ iterate(double, DoublePredicate, DoubleUnaryOperator): DoubleStream [static]  [API 35+]
+ iterate(double, DoubleUnaryOperator): DoubleStream [static]  [API 24+]
+ iterator(): PrimitiveIterator.OfDouble  [API 24+]
+ limit(long): DoubleStream  [API 24+]
+ map(DoubleUnaryOperator): DoubleStream  [API 24+]
+ mapMulti(DoubleStream.DoubleMapMultiConsumer): DoubleStream  [API 35+]
+ mapToInt(DoubleToIntFunction): IntStream  [API 24+]
+ mapToLong(DoubleToLongFunction): LongStream  [API 24+]
+ mapToObj(DoubleFunction): Stream  [API 24+]
+ max(): OptionalDouble  [API 24+]
+ min(): OptionalDouble  [API 24+]
+ noneMatch(DoublePredicate): boolean  [API 24+]
+ of(double): DoubleStream [static]  [API 24+]
+ of(double[]): DoubleStream [static]  [API 24+]
+ parallel(): DoubleStream  [API 24+]
+ peek(DoubleConsumer): DoubleStream  [API 24+]
+ reduce(DoubleBinaryOperator): OptionalDouble  [API 24+]
+ reduce(double, DoubleBinaryOperator): double  [API 24+]
+ sequential(): DoubleStream  [API 24+]
+ skip(long): DoubleStream  [API 24+]
+ sorted(): DoubleStream  [API 24+]
+ spliterator(): Spliterator.OfDouble  [API 24+]
+ sum(): double  [API 24+]
+ summaryStatistics(): DoubleSummaryStatistics  [API 24+]
+ takeWhile(DoublePredicate): DoubleStream  [API 35+]
+ toArray(): double[]  [API 24+]
```

## DoubleStream.Builder — TeaVM class library; API 24+ (3 +, 0 *, 0 ~, 0 -)

```
+ accept(double): void  [API 24+]
+ add(double): DoubleStream.Builder  [API 24+]
+ build(): DoubleStream  [API 24+]
```

## DoubleStream.DoubleMapMultiConsumer — TeaVM class library; API 35+ (1 +, 0 *, 0 ~, 0 -)

```
+ accept(double, DoubleConsumer): void  [API 35+]
```

## IntStream — TeaVM class library; API 24+ (48 +, 0 *, 0 ~, 0 -)

```
+ allMatch(IntPredicate): boolean  [API 24+]
+ anyMatch(IntPredicate): boolean  [API 24+]
+ asDoubleStream(): DoubleStream  [API 24+]
+ asLongStream(): LongStream  [API 24+]
+ average(): OptionalDouble  [API 24+]
+ boxed(): Stream  [API 24+]
+ builder(): IntStream.Builder [static]  [API 24+]
+ collect(Supplier, ObjIntConsumer, BiConsumer): Object  [API 24+]
+ concat(IntStream, IntStream): IntStream [static]  [API 24+]
+ count(): long  [API 24+]
+ distinct(): IntStream  [API 24+]
+ dropWhile(IntPredicate): IntStream  [API 35+]
+ empty(): IntStream [static]  [API 24+]
+ filter(IntPredicate): IntStream  [API 24+]
+ findAny(): OptionalInt  [API 24+]
+ findFirst(): OptionalInt  [API 24+]
+ flatMap(IntFunction): IntStream  [API 24+]
+ forEach(IntConsumer): void  [API 24+]
+ forEachOrdered(IntConsumer): void  [API 24+]
+ generate(IntSupplier): IntStream [static]  [API 24+]
+ iterate(int, IntPredicate, IntUnaryOperator): IntStream [static]  [API 35+]
+ iterate(int, IntUnaryOperator): IntStream [static]  [API 24+]
+ iterator(): PrimitiveIterator.OfInt  [API 24+]
+ limit(long): IntStream  [API 24+]
+ map(IntUnaryOperator): IntStream  [API 24+]
+ mapMulti(IntStream.IntMapMultiConsumer): IntStream  [API 35+]
+ mapToDouble(IntToDoubleFunction): DoubleStream  [API 24+]
+ mapToLong(IntToLongFunction): LongStream  [API 24+]
+ mapToObj(IntFunction): Stream  [API 24+]
+ max(): OptionalInt  [API 24+]
+ min(): OptionalInt  [API 24+]
+ noneMatch(IntPredicate): boolean  [API 24+]
+ of(int): IntStream [static]  [API 24+]
+ of(int[]): IntStream [static]  [API 24+]
+ parallel(): IntStream  [API 24+]
+ peek(IntConsumer): IntStream  [API 24+]
+ range(int, int): IntStream [static]  [API 24+]
+ rangeClosed(int, int): IntStream [static]  [API 24+]
+ reduce(IntBinaryOperator): OptionalInt  [API 24+]
+ reduce(int, IntBinaryOperator): int  [API 24+]
+ sequential(): IntStream  [API 24+]
+ skip(long): IntStream  [API 24+]
+ sorted(): IntStream  [API 24+]
+ spliterator(): Spliterator.OfInt  [API 24+]
+ sum(): int  [API 24+]
+ summaryStatistics(): IntSummaryStatistics  [API 24+]
+ takeWhile(IntPredicate): IntStream  [API 35+]
+ toArray(): int[]  [API 24+]
```

## IntStream.Builder — TeaVM class library; API 24+ (3 +, 0 *, 0 ~, 0 -)

```
+ accept(int): void  [API 24+]
+ add(int): IntStream.Builder  [API 24+]
+ build(): IntStream  [API 24+]
```

## IntStream.IntMapMultiConsumer — TeaVM class library; API 35+ (1 +, 0 *, 0 ~, 0 -)

```
+ accept(int, IntConsumer): void  [API 35+]
```

## LongStream — TeaVM class library; API 24+ (47 +, 0 *, 0 ~, 0 -)

```
+ allMatch(LongPredicate): boolean  [API 24+]
+ anyMatch(LongPredicate): boolean  [API 24+]
+ asDoubleStream(): DoubleStream  [API 24+]
+ average(): OptionalDouble  [API 24+]
+ boxed(): Stream  [API 24+]
+ builder(): LongStream.Builder [static]  [API 24+]
+ collect(Supplier, ObjLongConsumer, BiConsumer): Object  [API 24+]
+ concat(LongStream, LongStream): LongStream [static]  [API 24+]
+ count(): long  [API 24+]
+ distinct(): LongStream  [API 24+]
+ dropWhile(LongPredicate): LongStream  [API 35+]
+ empty(): LongStream [static]  [API 24+]
+ filter(LongPredicate): LongStream  [API 24+]
+ findAny(): OptionalLong  [API 24+]
+ findFirst(): OptionalLong  [API 24+]
+ flatMap(LongFunction): LongStream  [API 24+]
+ forEach(LongConsumer): void  [API 24+]
+ forEachOrdered(LongConsumer): void  [API 24+]
+ generate(LongSupplier): LongStream [static]  [API 24+]
+ iterate(long, LongPredicate, LongUnaryOperator): LongStream [static]  [API 35+]
+ iterate(long, LongUnaryOperator): LongStream [static]  [API 24+]
+ iterator(): PrimitiveIterator.OfLong  [API 24+]
+ limit(long): LongStream  [API 24+]
+ map(LongUnaryOperator): LongStream  [API 24+]
+ mapMulti(LongStream.LongMapMultiConsumer): LongStream  [API 35+]
+ mapToDouble(LongToDoubleFunction): DoubleStream  [API 24+]
+ mapToInt(LongToIntFunction): IntStream  [API 24+]
+ mapToObj(LongFunction): Stream  [API 24+]
+ max(): OptionalLong  [API 24+]
+ min(): OptionalLong  [API 24+]
+ noneMatch(LongPredicate): boolean  [API 24+]
+ of(long): LongStream [static]  [API 24+]
+ of(long[]): LongStream [static]  [API 24+]
+ parallel(): LongStream  [API 24+]
+ peek(LongConsumer): LongStream  [API 24+]
+ range(long, long): LongStream [static]  [API 24+]
+ rangeClosed(long, long): LongStream [static]  [API 24+]
+ reduce(LongBinaryOperator): OptionalLong  [API 24+]
+ reduce(long, LongBinaryOperator): long  [API 24+]
+ sequential(): LongStream  [API 24+]
+ skip(long): LongStream  [API 24+]
+ sorted(): LongStream  [API 24+]
+ spliterator(): Spliterator.OfLong  [API 24+]
+ sum(): long  [API 24+]
+ summaryStatistics(): LongSummaryStatistics  [API 24+]
+ takeWhile(LongPredicate): LongStream  [API 35+]
+ toArray(): long[]  [API 24+]
```

## LongStream.Builder — TeaVM class library; API 24+ (3 +, 0 *, 0 ~, 0 -)

```
+ accept(long): void  [API 24+]
+ add(long): LongStream.Builder  [API 24+]
+ build(): LongStream  [API 24+]
```

## LongStream.LongMapMultiConsumer — TeaVM class library; API 35+ (1 +, 0 *, 0 ~, 0 -)

```
+ accept(long, LongConsumer): void  [API 35+]
```

## Stream — TeaVM class library; API 24+ (48 +, 0 *, 0 ~, 0 -)

```
+ allMatch(Predicate): boolean  [API 24+]
+ anyMatch(Predicate): boolean  [API 24+]
+ builder(): Stream.Builder [static]  [API 24+]
+ collect(Collector): Object  [API 24+]
+ collect(Supplier, BiConsumer, BiConsumer): Object  [API 24+]
+ concat(Stream, Stream): Stream [static]  [API 24+]
+ count(): long  [API 24+]
+ distinct(): Stream  [API 24+]
+ dropWhile(Predicate): Stream  [API 34+]
+ empty(): Stream [static]  [API 24+]
+ filter(Predicate): Stream  [API 24+]
+ findAny(): Optional  [API 24+]
+ findFirst(): Optional  [API 24+]
+ flatMap(Function): Stream  [API 24+]
+ flatMapToDouble(Function): DoubleStream  [API 24+]
+ flatMapToInt(Function): IntStream  [API 24+]
+ flatMapToLong(Function): LongStream  [API 24+]
+ forEach(Consumer): void  [API 24+]
+ forEachOrdered(Consumer): void  [API 24+]
+ generate(Supplier): Stream [static]  [API 24+]
+ iterate(Object, Predicate, UnaryOperator): Stream [static]  [API 34+]
+ iterate(Object, UnaryOperator): Stream [static]  [API 24+]
+ limit(long): Stream  [API 24+]
+ map(Function): Stream  [API 24+]
+ mapMulti(BiConsumer): Stream  [API 34+]
+ mapMultiToDouble(BiConsumer): DoubleStream  [API 34+]
+ mapMultiToInt(BiConsumer): IntStream  [API 34+]
+ mapMultiToLong(BiConsumer): LongStream  [API 34+]
+ mapToDouble(ToDoubleFunction): DoubleStream  [API 24+]
+ mapToInt(ToIntFunction): IntStream  [API 24+]
+ mapToLong(ToLongFunction): LongStream  [API 24+]
+ max(Comparator): Optional  [API 24+]
+ min(Comparator): Optional  [API 24+]
+ noneMatch(Predicate): boolean  [API 24+]
+ of(Object): Stream [static]  [API 24+]
+ of(Object[]): Stream [static]  [API 24+]
+ ofNullable(Object): Stream [static]  [API 34+]
+ peek(Consumer): Stream  [API 24+]
+ reduce(BinaryOperator): Optional  [API 24+]
+ reduce(Object, BiFunction, BinaryOperator): Object  [API 24+]
+ reduce(Object, BinaryOperator): Object  [API 24+]
+ skip(long): Stream  [API 24+]
+ sorted(): Stream  [API 24+]
+ sorted(Comparator): Stream  [API 24+]
+ takeWhile(Predicate): Stream  [API 34+]
+ toArray(): Object[]  [API 24+]
+ toArray(IntFunction): Object[]  [API 24+]
+ toList(): List  [API 34+]
```

## Stream.Builder — TeaVM class library; API 24+ (3 +, 0 *, 0 ~, 0 -)

```
+ accept(Object): void  [API 24+]
+ add(Object): Stream.Builder  [API 24+]
+ build(): Stream  [API 24+]
```

## StreamSupport — TeaVM class library; API 24+ (8 +, 0 *, 0 ~, 0 -)

```
+ doubleStream(Spliterator.OfDouble, boolean): DoubleStream [static]  [API 24+]
+ doubleStream(Supplier, int, boolean): DoubleStream [static]  [API 24+]
+ intStream(Spliterator.OfInt, boolean): IntStream [static]  [API 24+]
+ intStream(Supplier, int, boolean): IntStream [static]  [API 24+]
+ longStream(Spliterator.OfLong, boolean): LongStream [static]  [API 24+]
+ longStream(Supplier, int, boolean): LongStream [static]  [API 24+]
+ stream(Spliterator, boolean): Stream [static]  [API 24+]
+ stream(Supplier, int, boolean): Stream [static]  [API 24+]
```

