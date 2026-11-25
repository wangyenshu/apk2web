# java.util.concurrent.atomic

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## AtomicBoolean — TeaVM class library; API 1+ (22 +, 0 *, 0 ~, 0 -)

```
+ AtomicBoolean()  [API 1+]
+ AtomicBoolean(boolean)  [API 1+]
+ compareAndExchange(boolean, boolean): boolean  [API 33+]
+ compareAndExchangeAcquire(boolean, boolean): boolean  [API 33+]
+ compareAndExchangeRelease(boolean, boolean): boolean  [API 33+]
+ compareAndSet(boolean, boolean): boolean  [API 1+]
+ get(): boolean  [API 1+]
+ getAcquire(): boolean  [API 33+]
+ getAndSet(boolean): boolean  [API 1+]
+ getOpaque(): boolean  [API 33+]
+ getPlain(): boolean  [API 33+]
+ lazySet(boolean): void  [API 9+]
+ set(boolean): void  [API 1+]
+ setOpaque(boolean): void  [API 33+]
+ setPlain(boolean): void  [API 33+]
+ setRelease(boolean): void  [API 33+]
+ toString(): String  [API 1+]
+ weakCompareAndSet(boolean, boolean): boolean  [API 1+, deprecated in 33]
+ weakCompareAndSetAcquire(boolean, boolean): boolean  [API 33+]
+ weakCompareAndSetPlain(boolean, boolean): boolean  [API 33+]
+ weakCompareAndSetRelease(boolean, boolean): boolean  [API 33+]
+ weakCompareAndSetVolatile(boolean, boolean): boolean  [API 33+]
```

## AtomicInteger — TeaVM class library; API 1+ (36 +, 0 *, 0 ~, 0 -)

```
+ AtomicInteger()  [API 1+]
+ AtomicInteger(int)  [API 1+]
+ accumulateAndGet(int, IntBinaryOperator): int  [API 24+]
+ addAndGet(int): int  [API 1+]
+ compareAndExchange(int, int): int  [API 33+]
+ compareAndExchangeAcquire(int, int): int  [API 33+]
+ compareAndExchangeRelease(int, int): int  [API 33+]
+ compareAndSet(int, int): boolean  [API 1+]
+ decrementAndGet(): int  [API 1+]
+ doubleValue(): double  [API 1+]
+ floatValue(): float  [API 1+]
+ get(): int  [API 1+]
+ getAcquire(): int  [API 33+]
+ getAndAccumulate(int, IntBinaryOperator): int  [API 24+]
+ getAndAdd(int): int  [API 1+]
+ getAndDecrement(): int  [API 1+]
+ getAndIncrement(): int  [API 1+]
+ getAndSet(int): int  [API 1+]
+ getAndUpdate(IntUnaryOperator): int  [API 24+]
+ getOpaque(): int  [API 33+]
+ getPlain(): int  [API 33+]
+ incrementAndGet(): int  [API 1+]
+ intValue(): int  [API 1+]
+ lazySet(int): void  [API 9+]
+ longValue(): long  [API 1+]
+ set(int): void  [API 1+]
+ setOpaque(int): void  [API 33+]
+ setPlain(int): void  [API 33+]
+ setRelease(int): void  [API 33+]
+ toString(): String  [API 1+]
+ updateAndGet(IntUnaryOperator): int  [API 24+]
+ weakCompareAndSet(int, int): boolean  [API 1+, deprecated in 33]
+ weakCompareAndSetAcquire(int, int): boolean  [API 33+]
+ weakCompareAndSetPlain(int, int): boolean  [API 33+]
+ weakCompareAndSetRelease(int, int): boolean  [API 33+]
+ weakCompareAndSetVolatile(int, int): boolean  [API 33+]
```

## AtomicIntegerArray — framework (a2w.jdk.util.concurrent.atomic.AtomicIntegerArray); API 1+ (33 +, 0 *, 0 ~, 0 -)

```
+ AtomicIntegerArray(int)  [API 1+]
+ AtomicIntegerArray(int[])  [API 1+]
+ accumulateAndGet(int, int, IntBinaryOperator): int  [API 24+]
+ addAndGet(int, int): int  [API 1+]
+ compareAndExchange(int, int, int): int  [API 33+]
+ compareAndExchangeAcquire(int, int, int): int  [API 33+]
+ compareAndExchangeRelease(int, int, int): int  [API 33+]
+ compareAndSet(int, int, int): boolean  [API 1+]
+ decrementAndGet(int): int  [API 1+]
+ get(int): int  [API 1+]
+ getAcquire(int): int  [API 33+]
+ getAndAccumulate(int, int, IntBinaryOperator): int  [API 24+]
+ getAndAdd(int, int): int  [API 1+]
+ getAndDecrement(int): int  [API 1+]
+ getAndIncrement(int): int  [API 1+]
+ getAndSet(int, int): int  [API 1+]
+ getAndUpdate(int, IntUnaryOperator): int  [API 24+]
+ getOpaque(int): int  [API 33+]
+ getPlain(int): int  [API 33+]
+ incrementAndGet(int): int  [API 1+]
+ lazySet(int, int): void  [API 9+]
+ length(): int  [API 1+]
+ set(int, int): void  [API 1+]
+ setOpaque(int, int): void  [API 33+]
+ setPlain(int, int): void  [API 33+]
+ setRelease(int, int): void  [API 33+]
+ toString(): String  [API 1+]
+ updateAndGet(int, IntUnaryOperator): int  [API 24+]
+ weakCompareAndSet(int, int, int): boolean  [API 1+, deprecated in 33]
+ weakCompareAndSetAcquire(int, int, int): boolean  [API 33+]
+ weakCompareAndSetPlain(int, int, int): boolean  [API 33+]
+ weakCompareAndSetRelease(int, int, int): boolean  [API 33+]
+ weakCompareAndSetVolatile(int, int, int): boolean  [API 33+]
```

## AtomicIntegerFieldUpdater — TeaVM class library; API 1+ (18 +, 0 *, 0 ~, 0 -)

```
+ AtomicIntegerFieldUpdater()  [API 1+]
+ accumulateAndGet(Object, int, IntBinaryOperator): int  [API 24+]
+ addAndGet(Object, int): int  [API 1+]
+ compareAndSet(Object, int, int): boolean  [API 1+]
+ decrementAndGet(Object): int  [API 1+]
+ get(Object): int  [API 1+]
+ getAndAccumulate(Object, int, IntBinaryOperator): int  [API 24+]
+ getAndAdd(Object, int): int  [API 1+]
+ getAndDecrement(Object): int  [API 1+]
+ getAndIncrement(Object): int  [API 1+]
+ getAndSet(Object, int): int  [API 1+]
+ getAndUpdate(Object, IntUnaryOperator): int  [API 24+]
+ incrementAndGet(Object): int  [API 1+]
+ lazySet(Object, int): void  [API 9+]
+ newUpdater(Class, String): AtomicIntegerFieldUpdater [static]  [API 1+]
+ set(Object, int): void  [API 1+]
+ updateAndGet(Object, IntUnaryOperator): int  [API 24+]
+ weakCompareAndSet(Object, int, int): boolean  [API 1+]
```

## AtomicLong — TeaVM class library; API 1+ (36 +, 0 *, 0 ~, 0 -)

```
+ AtomicLong()  [API 1+]
+ AtomicLong(long)  [API 1+]
+ accumulateAndGet(long, LongBinaryOperator): long  [API 24+]
+ addAndGet(long): long  [API 1+]
+ compareAndExchange(long, long): long  [API 33+]
+ compareAndExchangeAcquire(long, long): long  [API 33+]
+ compareAndExchangeRelease(long, long): long  [API 33+]
+ compareAndSet(long, long): boolean  [API 1+]
+ decrementAndGet(): long  [API 1+]
+ doubleValue(): double  [API 1+]
+ floatValue(): float  [API 1+]
+ get(): long  [API 1+]
+ getAcquire(): long  [API 33+]
+ getAndAccumulate(long, LongBinaryOperator): long  [API 24+]
+ getAndAdd(long): long  [API 1+]
+ getAndDecrement(): long  [API 1+]
+ getAndIncrement(): long  [API 1+]
+ getAndSet(long): long  [API 1+]
+ getAndUpdate(LongUnaryOperator): long  [API 24+]
+ getOpaque(): long  [API 33+]
+ getPlain(): long  [API 33+]
+ incrementAndGet(): long  [API 1+]
+ intValue(): int  [API 1+]
+ lazySet(long): void  [API 9+]
+ longValue(): long  [API 1+]
+ set(long): void  [API 1+]
+ setOpaque(long): void  [API 33+]
+ setPlain(long): void  [API 33+]
+ setRelease(long): void  [API 33+]
+ toString(): String  [API 1+]
+ updateAndGet(LongUnaryOperator): long  [API 24+]
+ weakCompareAndSet(long, long): boolean  [API 1+, deprecated in 33]
+ weakCompareAndSetAcquire(long, long): boolean  [API 33+]
+ weakCompareAndSetPlain(long, long): boolean  [API 33+]
+ weakCompareAndSetRelease(long, long): boolean  [API 33+]
+ weakCompareAndSetVolatile(long, long): boolean  [API 33+]
```

## AtomicLongArray — framework (a2w.jdk.util.concurrent.atomic.AtomicLongArray); API 1+ (33 +, 0 *, 0 ~, 0 -)

```
+ AtomicLongArray(int)  [API 1+]
+ AtomicLongArray(long[])  [API 1+]
+ accumulateAndGet(int, long, LongBinaryOperator): long  [API 24+]
+ addAndGet(int, long): long  [API 1+]
+ compareAndExchange(int, long, long): long  [API 33+]
+ compareAndExchangeAcquire(int, long, long): long  [API 33+]
+ compareAndExchangeRelease(int, long, long): long  [API 33+]
+ compareAndSet(int, long, long): boolean  [API 1+]
+ decrementAndGet(int): long  [API 1+]
+ get(int): long  [API 1+]
+ getAcquire(int): long  [API 33+]
+ getAndAccumulate(int, long, LongBinaryOperator): long  [API 24+]
+ getAndAdd(int, long): long  [API 1+]
+ getAndDecrement(int): long  [API 1+]
+ getAndIncrement(int): long  [API 1+]
+ getAndSet(int, long): long  [API 1+]
+ getAndUpdate(int, LongUnaryOperator): long  [API 24+]
+ getOpaque(int): long  [API 33+]
+ getPlain(int): long  [API 33+]
+ incrementAndGet(int): long  [API 1+]
+ lazySet(int, long): void  [API 9+]
+ length(): int  [API 1+]
+ set(int, long): void  [API 1+]
+ setOpaque(int, long): void  [API 33+]
+ setPlain(int, long): void  [API 33+]
+ setRelease(int, long): void  [API 33+]
+ toString(): String  [API 1+]
+ updateAndGet(int, LongUnaryOperator): long  [API 24+]
+ weakCompareAndSet(int, long, long): boolean  [API 1+, deprecated in 33]
+ weakCompareAndSetAcquire(int, long, long): boolean  [API 33+]
+ weakCompareAndSetPlain(int, long, long): boolean  [API 33+]
+ weakCompareAndSetRelease(int, long, long): boolean  [API 33+]
+ weakCompareAndSetVolatile(int, long, long): boolean  [API 33+]
```

## AtomicLongFieldUpdater — TeaVM class library; API 1+ (18 +, 0 *, 0 ~, 0 -)

```
+ AtomicLongFieldUpdater()  [API 1+]
+ accumulateAndGet(Object, long, LongBinaryOperator): long  [API 24+]
+ addAndGet(Object, long): long  [API 1+]
+ compareAndSet(Object, long, long): boolean  [API 1+]
+ decrementAndGet(Object): long  [API 1+]
+ get(Object): long  [API 1+]
+ getAndAccumulate(Object, long, LongBinaryOperator): long  [API 24+]
+ getAndAdd(Object, long): long  [API 1+]
+ getAndDecrement(Object): long  [API 1+]
+ getAndIncrement(Object): long  [API 1+]
+ getAndSet(Object, long): long  [API 1+]
+ getAndUpdate(Object, LongUnaryOperator): long  [API 24+]
+ incrementAndGet(Object): long  [API 1+]
+ lazySet(Object, long): void  [API 9+]
+ newUpdater(Class, String): AtomicLongFieldUpdater [static]  [API 1+]
+ set(Object, long): void  [API 1+]
+ updateAndGet(Object, LongUnaryOperator): long  [API 24+]
+ weakCompareAndSet(Object, long, long): boolean  [API 1+]
```

## AtomicMarkableReference — framework (a2w.jdk.util.concurrent.atomic.AtomicMarkableReference); API 1+ (8 +, 0 *, 0 ~, 0 -)

```
+ AtomicMarkableReference(Object, boolean)  [API 1+]
+ attemptMark(Object, boolean): boolean  [API 1+]
+ compareAndSet(Object, Object, boolean, boolean): boolean  [API 1+]
+ get(boolean[]): Object  [API 1+]
+ getReference(): Object  [API 1+]
+ isMarked(): boolean  [API 1+]
+ set(Object, boolean): void  [API 1+]
+ weakCompareAndSet(Object, Object, boolean, boolean): boolean  [API 1+]
```

## AtomicReference — TeaVM class library; API 1+ (26 +, 0 *, 0 ~, 0 -)

```
+ AtomicReference()  [API 1+]
+ AtomicReference(Object)  [API 1+]
+ accumulateAndGet(Object, BinaryOperator): Object  [API 24+]
+ compareAndExchange(Object, Object): Object  [API 33+]
+ compareAndExchangeAcquire(Object, Object): Object  [API 33+]
+ compareAndExchangeRelease(Object, Object): Object  [API 33+]
+ compareAndSet(Object, Object): boolean  [API 1+]
+ get(): Object  [API 1+]
+ getAcquire(): Object  [API 33+]
+ getAndAccumulate(Object, BinaryOperator): Object  [API 24+]
+ getAndSet(Object): Object  [API 1+]
+ getAndUpdate(UnaryOperator): Object  [API 24+]
+ getOpaque(): Object  [API 33+]
+ getPlain(): Object  [API 33+]
+ lazySet(Object): void  [API 9+]
+ set(Object): void  [API 1+]
+ setOpaque(Object): void  [API 33+]
+ setPlain(Object): void  [API 33+]
+ setRelease(Object): void  [API 33+]
+ toString(): String  [API 1+]
+ updateAndGet(UnaryOperator): Object  [API 24+]
+ weakCompareAndSet(Object, Object): boolean  [API 1+, deprecated in 33]
+ weakCompareAndSetAcquire(Object, Object): boolean  [API 33+]
+ weakCompareAndSetPlain(Object, Object): boolean  [API 33+]
+ weakCompareAndSetRelease(Object, Object): boolean  [API 33+]
+ weakCompareAndSetVolatile(Object, Object): boolean  [API 33+]
```

## AtomicReferenceArray — framework (a2w.jdk.util.concurrent.atomic.AtomicReferenceArray); API 1+ (27 +, 0 *, 0 ~, 0 -)

```
+ AtomicReferenceArray(Object[])  [API 1+]
+ AtomicReferenceArray(int)  [API 1+]
+ accumulateAndGet(int, Object, BinaryOperator): Object  [API 24+]
+ compareAndExchange(int, Object, Object): Object  [API 33+]
+ compareAndExchangeAcquire(int, Object, Object): Object  [API 33+]
+ compareAndExchangeRelease(int, Object, Object): Object  [API 33+]
+ compareAndSet(int, Object, Object): boolean  [API 1+]
+ get(int): Object  [API 1+]
+ getAcquire(int): Object  [API 33+]
+ getAndAccumulate(int, Object, BinaryOperator): Object  [API 24+]
+ getAndSet(int, Object): Object  [API 1+]
+ getAndUpdate(int, UnaryOperator): Object  [API 24+]
+ getOpaque(int): Object  [API 33+]
+ getPlain(int): Object  [API 33+]
+ lazySet(int, Object): void  [API 9+]
+ length(): int  [API 1+]
+ set(int, Object): void  [API 1+]
+ setOpaque(int, Object): void  [API 33+]
+ setPlain(int, Object): void  [API 33+]
+ setRelease(int, Object): void  [API 33+]
+ toString(): String  [API 1+]
+ updateAndGet(int, UnaryOperator): Object  [API 24+]
+ weakCompareAndSet(int, Object, Object): boolean  [API 1+, deprecated in 33]
+ weakCompareAndSetAcquire(int, Object, Object): boolean  [API 33+]
+ weakCompareAndSetPlain(int, Object, Object): boolean  [API 33+]
+ weakCompareAndSetRelease(int, Object, Object): boolean  [API 33+]
+ weakCompareAndSetVolatile(int, Object, Object): boolean  [API 33+]
```

## AtomicReferenceFieldUpdater — TeaVM class library; API 1+ (12 +, 0 *, 0 ~, 0 -)

```
+ AtomicReferenceFieldUpdater()  [API 1+]
+ accumulateAndGet(Object, Object, BinaryOperator): Object  [API 24+]
+ compareAndSet(Object, Object, Object): boolean  [API 1+]
+ get(Object): Object  [API 1+]
+ getAndAccumulate(Object, Object, BinaryOperator): Object  [API 24+]
+ getAndSet(Object, Object): Object  [API 1+]
+ getAndUpdate(Object, UnaryOperator): Object  [API 24+]
+ lazySet(Object, Object): void  [API 9+]
+ newUpdater(Class, Class, String): AtomicReferenceFieldUpdater [static]  [API 1+]
+ set(Object, Object): void  [API 1+]
+ updateAndGet(Object, UnaryOperator): Object  [API 24+]
+ weakCompareAndSet(Object, Object, Object): boolean  [API 1+]
```

## AtomicStampedReference — framework (a2w.jdk.util.concurrent.atomic.AtomicStampedReference); API 1+ (8 +, 0 *, 0 ~, 0 -)

```
+ AtomicStampedReference(Object, int)  [API 1+]
+ attemptStamp(Object, int): boolean  [API 1+]
+ compareAndSet(Object, Object, int, int): boolean  [API 1+]
+ get(int[]): Object  [API 1+]
+ getReference(): Object  [API 1+]
+ getStamp(): int  [API 1+]
+ set(Object, int): void  [API 1+]
+ weakCompareAndSet(Object, Object, int, int): boolean  [API 1+]
```

## DoubleAccumulator — framework (a2w.jdk.util.concurrent.atomic.DoubleAccumulator); API 24+ (10 +, 0 *, 0 ~, 0 -)

```
+ DoubleAccumulator(DoubleBinaryOperator, double)  [API 24+]
+ accumulate(double): void  [API 24+]
+ doubleValue(): double  [API 24+]
+ floatValue(): float  [API 24+]
+ get(): double  [API 24+]
+ getThenReset(): double  [API 24+]
+ intValue(): int  [API 24+]
+ longValue(): long  [API 24+]
+ reset(): void  [API 24+]
+ toString(): String  [API 24+]
```

## DoubleAdder — framework (a2w.jdk.util.concurrent.atomic.DoubleAdder); API 24+ (10 +, 0 *, 0 ~, 0 -)

```
+ DoubleAdder()  [API 24+]
+ add(double): void  [API 24+]
+ doubleValue(): double  [API 24+]
+ floatValue(): float  [API 24+]
+ intValue(): int  [API 24+]
+ longValue(): long  [API 24+]
+ reset(): void  [API 24+]
+ sum(): double  [API 24+]
+ sumThenReset(): double  [API 24+]
+ toString(): String  [API 24+]
```

## LongAccumulator — framework (a2w.jdk.util.concurrent.atomic.LongAccumulator); API 24+ (10 +, 0 *, 0 ~, 0 -)

```
+ LongAccumulator(LongBinaryOperator, long)  [API 24+]
+ accumulate(long): void  [API 24+]
+ doubleValue(): double  [API 24+]
+ floatValue(): float  [API 24+]
+ get(): long  [API 24+]
+ getThenReset(): long  [API 24+]
+ intValue(): int  [API 24+]
+ longValue(): long  [API 24+]
+ reset(): void  [API 24+]
+ toString(): String  [API 24+]
```

## LongAdder — framework (a2w.jdk.util.concurrent.atomic.LongAdder); API 24+ (12 +, 0 *, 0 ~, 0 -)

```
+ LongAdder()  [API 24+]
+ add(long): void  [API 24+]
+ decrement(): void  [API 24+]
+ doubleValue(): double  [API 24+]
+ floatValue(): float  [API 24+]
+ increment(): void  [API 24+]
+ intValue(): int  [API 24+]
+ longValue(): long  [API 24+]
+ reset(): void  [API 24+]
+ sum(): long  [API 24+]
+ sumThenReset(): long  [API 24+]
+ toString(): String  [API 24+]
```

