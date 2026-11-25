# java.util.random

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## RandomGenerator — TeaVM class library; API 35+ (29 +, 0 *, 0 ~, 3 -)

```
+ doubles(): DoubleStream  [API 35+]
+ doubles(double, double): DoubleStream  [API 35+]
+ doubles(long): DoubleStream  [API 35+]
+ doubles(long, double, double): DoubleStream  [API 35+]
- getDefault(): RandomGenerator [static]  [API 35+]
+ ints(): IntStream  [API 35+]
+ ints(int, int): IntStream  [API 35+]
+ ints(long): IntStream  [API 35+]
+ ints(long, int, int): IntStream  [API 35+]
+ isDeprecated(): boolean  [API 35+]
+ longs(): LongStream  [API 35+]
+ longs(long): LongStream  [API 35+]
+ longs(long, long): LongStream  [API 35+]
+ longs(long, long, long): LongStream  [API 35+]
+ nextBoolean(): boolean  [API 35+]
+ nextBytes(byte[]): void  [API 35+]
+ nextDouble(): double  [API 35+]
+ nextDouble(double): double  [API 35+]
+ nextDouble(double, double): double  [API 35+]
- nextExponential(): double  [API 35+]
+ nextFloat(): float  [API 35+]
+ nextFloat(float): float  [API 35+]
+ nextFloat(float, float): float  [API 35+]
+ nextGaussian(): double  [API 35+]
+ nextGaussian(double, double): double  [API 35+]
+ nextInt(): int  [API 35+]
+ nextInt(int): int  [API 35+]
+ nextInt(int, int): int  [API 35+]
+ nextLong(): long  [API 35+]
+ nextLong(long): long  [API 35+]
+ nextLong(long, long): long  [API 35+]
- of(String): RandomGenerator [static]  [API 35+]
```

## RandomGenerator.ArbitrarilyJumpableGenerator — declaration; API 35+ (3 +, 0 *, 0 ~, 6 -)

```
+ copy(): RandomGenerator.ArbitrarilyJumpableGenerator  [API 35+]
- copyAndJump(double): RandomGenerator.ArbitrarilyJumpableGenerator  [API 35+]
- jump(): void  [API 35+]
+ jump(double): void  [API 35+]
+ jumpPowerOfTwo(int): void  [API 35+]
- jumps(double): Stream  [API 35+]
- jumps(long, double): Stream  [API 35+]
- leap(): void  [API 35+]
- of(String): RandomGenerator.ArbitrarilyJumpableGenerator [static]  [API 35+]
```

## RandomGenerator.JumpableGenerator — declaration; API 35+ (3 +, 0 *, 0 ~, 6 -)

```
+ copy(): RandomGenerator.JumpableGenerator  [API 35+]
- copyAndJump(): RandomGenerator  [API 35+]
+ jump(): void  [API 35+]
+ jumpDistance(): double  [API 35+]
- jumps(): Stream  [API 35+]
- jumps(long): Stream  [API 35+]
- of(String): RandomGenerator.JumpableGenerator [static]  [API 35+]
- rngs(): Stream  [API 35+]
- rngs(long): Stream  [API 35+]
```

## RandomGenerator.LeapableGenerator — declaration; API 35+ (3 +, 0 *, 0 ~, 4 -)

```
+ copy(): RandomGenerator.LeapableGenerator  [API 35+]
- copyAndLeap(): RandomGenerator.JumpableGenerator  [API 35+]
+ leap(): void  [API 35+]
+ leapDistance(): double  [API 35+]
- leaps(): Stream  [API 35+]
- leaps(long): Stream  [API 35+]
- of(String): RandomGenerator.LeapableGenerator [static]  [API 35+]
```

## RandomGenerator.SplittableGenerator — declaration; API 35+ (5 +, 0 *, 0 ~, 4 -)

```
- of(String): RandomGenerator.SplittableGenerator [static]  [API 35+]
- rngs(): Stream  [API 35+]
- rngs(long): Stream  [API 35+]
+ split(): RandomGenerator.SplittableGenerator  [API 35+]
+ split(RandomGenerator.SplittableGenerator): RandomGenerator.SplittableGenerator  [API 35+]
- splits(): Stream  [API 35+]
+ splits(RandomGenerator.SplittableGenerator): Stream  [API 35+]
+ splits(long): Stream  [API 35+]
+ splits(long, RandomGenerator.SplittableGenerator): Stream  [API 35+]
```

## RandomGenerator.StreamableGenerator — declaration; API 35+ (1 +, 0 *, 0 ~, 2 -)

```
- of(String): RandomGenerator.StreamableGenerator [static]  [API 35+]
+ rngs(): Stream  [API 35+]
- rngs(long): Stream  [API 35+]
```

## RandomGeneratorFactory — declaration; API 35+ (0 +, 0 *, 0 ~, 20 -)

```
- all(): Stream [static]  [API 35+]
- create(): RandomGenerator  [API 35+]
- create(byte[]): RandomGenerator  [API 35+]
- create(long): RandomGenerator  [API 35+]
- equidistribution(): int  [API 35+]
- getDefault(): RandomGeneratorFactory [static]  [API 35+]
- group(): String  [API 35+]
- isArbitrarilyJumpable(): boolean  [API 35+]
- isDeprecated(): boolean  [API 35+]
- isHardware(): boolean  [API 35+]
- isJumpable(): boolean  [API 35+]
- isLeapable(): boolean  [API 35+]
- isSplittable(): boolean  [API 35+]
- isStatistical(): boolean  [API 35+]
- isStochastic(): boolean  [API 35+]
- isStreamable(): boolean  [API 35+]
- name(): String  [API 35+]
- of(String): RandomGeneratorFactory [static]  [API 35+]
- period(): BigInteger  [API 35+]
- stateBits(): int  [API 35+]
```

