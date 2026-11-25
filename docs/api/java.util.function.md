# java.util.function

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## BiConsumer — TeaVM class library; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ accept(Object, Object): void  [API 24+]
+ andThen(BiConsumer): BiConsumer  [API 24+]
```

## BiFunction — TeaVM class library; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ andThen(Function): BiFunction  [API 24+]
+ apply(Object, Object): Object  [API 24+]
```

## BiPredicate — TeaVM class library; API 24+ (4 +, 0 *, 0 ~, 0 -)

```
+ and(BiPredicate): BiPredicate  [API 24+]
+ negate(): BiPredicate  [API 24+]
+ or(BiPredicate): BiPredicate  [API 24+]
+ test(Object, Object): boolean  [API 24+]
```

## BinaryOperator — TeaVM class library; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ maxBy(Comparator): BinaryOperator [static]  [API 24+]
+ minBy(Comparator): BinaryOperator [static]  [API 24+]
```

## BooleanSupplier — TeaVM class library; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ getAsBoolean(): boolean  [API 24+]
```

## Consumer — TeaVM class library; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ accept(Object): void  [API 24+]
+ andThen(Consumer): Consumer  [API 24+]
```

## DoubleBinaryOperator — TeaVM class library; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ applyAsDouble(double, double): double  [API 24+]
```

## DoubleConsumer — TeaVM class library; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ accept(double): void  [API 24+]
+ andThen(DoubleConsumer): DoubleConsumer  [API 24+]
```

## DoubleFunction — TeaVM class library; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ apply(double): Object  [API 24+]
```

## DoublePredicate — TeaVM class library; API 24+ (4 +, 0 *, 0 ~, 0 -)

```
+ and(DoublePredicate): DoublePredicate  [API 24+]
+ negate(): DoublePredicate  [API 24+]
+ or(DoublePredicate): DoublePredicate  [API 24+]
+ test(double): boolean  [API 24+]
```

## DoubleSupplier — TeaVM class library; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ getAsDouble(): double  [API 24+]
```

## DoubleToIntFunction — TeaVM class library; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ applyAsInt(double): int  [API 24+]
```

## DoubleToLongFunction — TeaVM class library; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ applyAsLong(double): long  [API 24+]
```

## DoubleUnaryOperator — TeaVM class library; API 24+ (4 +, 0 *, 0 ~, 0 -)

```
+ andThen(DoubleUnaryOperator): DoubleUnaryOperator  [API 24+]
+ applyAsDouble(double): double  [API 24+]
+ compose(DoubleUnaryOperator): DoubleUnaryOperator  [API 24+]
+ identity(): DoubleUnaryOperator [static]  [API 24+]
```

## Function — TeaVM class library; API 24+ (4 +, 0 *, 0 ~, 0 -)

```
+ andThen(Function): Function  [API 24+]
+ apply(Object): Object  [API 24+]
+ compose(Function): Function  [API 24+]
+ identity(): Function [static]  [API 24+]
```

## IntBinaryOperator — TeaVM class library; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ applyAsInt(int, int): int  [API 24+]
```

## IntConsumer — TeaVM class library; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ accept(int): void  [API 24+]
+ andThen(IntConsumer): IntConsumer  [API 24+]
```

## IntFunction — TeaVM class library; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ apply(int): Object  [API 24+]
```

## IntPredicate — TeaVM class library; API 24+ (4 +, 0 *, 0 ~, 0 -)

```
+ and(IntPredicate): IntPredicate  [API 24+]
+ negate(): IntPredicate  [API 24+]
+ or(IntPredicate): IntPredicate  [API 24+]
+ test(int): boolean  [API 24+]
```

## IntSupplier — TeaVM class library; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ getAsInt(): int  [API 24+]
```

## IntToDoubleFunction — TeaVM class library; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ applyAsDouble(int): double  [API 24+]
```

## IntToLongFunction — TeaVM class library; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ applyAsLong(int): long  [API 24+]
```

## IntUnaryOperator — TeaVM class library; API 24+ (4 +, 0 *, 0 ~, 0 -)

```
+ andThen(IntUnaryOperator): IntUnaryOperator  [API 24+]
+ applyAsInt(int): int  [API 24+]
+ compose(IntUnaryOperator): IntUnaryOperator  [API 24+]
+ identity(): IntUnaryOperator [static]  [API 24+]
```

## LongBinaryOperator — TeaVM class library; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ applyAsLong(long, long): long  [API 24+]
```

## LongConsumer — TeaVM class library; API 24+ (2 +, 0 *, 0 ~, 0 -)

```
+ accept(long): void  [API 24+]
+ andThen(LongConsumer): LongConsumer  [API 24+]
```

## LongFunction — TeaVM class library; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ apply(long): Object  [API 24+]
```

## LongPredicate — TeaVM class library; API 24+ (4 +, 0 *, 0 ~, 0 -)

```
+ and(LongPredicate): LongPredicate  [API 24+]
+ negate(): LongPredicate  [API 24+]
+ or(LongPredicate): LongPredicate  [API 24+]
+ test(long): boolean  [API 24+]
```

## LongSupplier — TeaVM class library; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ getAsLong(): long  [API 24+]
```

## LongToDoubleFunction — TeaVM class library; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ applyAsDouble(long): double  [API 24+]
```

## LongToIntFunction — TeaVM class library; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ applyAsInt(long): int  [API 24+]
```

## LongUnaryOperator — TeaVM class library; API 24+ (4 +, 0 *, 0 ~, 0 -)

```
+ andThen(LongUnaryOperator): LongUnaryOperator  [API 24+]
+ applyAsLong(long): long  [API 24+]
+ compose(LongUnaryOperator): LongUnaryOperator  [API 24+]
+ identity(): LongUnaryOperator [static]  [API 24+]
```

## ObjDoubleConsumer — TeaVM class library; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ accept(Object, double): void  [API 24+]
```

## ObjIntConsumer — TeaVM class library; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ accept(Object, int): void  [API 24+]
```

## ObjLongConsumer — TeaVM class library; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ accept(Object, long): void  [API 24+]
```

## Predicate — TeaVM class library; API 24+ (6 +, 0 *, 0 ~, 0 -)

```
+ and(Predicate): Predicate  [API 24+]
+ isEqual(Object): Predicate [static]  [API 24+]
+ negate(): Predicate  [API 24+]
+ not(Predicate): Predicate [static]  [API 33+]
+ or(Predicate): Predicate  [API 24+]
+ test(Object): boolean  [API 24+]
```

## Supplier — TeaVM class library; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ get(): Object  [API 24+]
```

## ToDoubleBiFunction — TeaVM class library; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ applyAsDouble(Object, Object): double  [API 24+]
```

## ToDoubleFunction — TeaVM class library; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ applyAsDouble(Object): double  [API 24+]
```

## ToIntBiFunction — TeaVM class library; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ applyAsInt(Object, Object): int  [API 24+]
```

## ToIntFunction — TeaVM class library; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ applyAsInt(Object): int  [API 24+]
```

## ToLongBiFunction — TeaVM class library; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ applyAsLong(Object, Object): long  [API 24+]
```

## ToLongFunction — TeaVM class library; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ applyAsLong(Object): long  [API 24+]
```

## UnaryOperator — TeaVM class library; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ identity(): UnaryOperator [static]  [API 24+]
```

