package a2w.jdk.util;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import a2w.jdk.util.random.RandomGenerator$SplittableGenerator;

/** java.util.SplittableRandom: the SplitMix64 generator (a seed advanced by an odd gamma, mixed on output), with the
 *  JDK's seeding, splitting and bounded-value algorithms, so a seeded instance gives the JDK's sequence. */
public final class SplittableRandom implements java.util.random.RandomGenerator, RandomGenerator$SplittableGenerator {
  private static final long GOLDEN_GAMMA = 0x9e3779b97f4a7c15L;
  private static final double DOUBLE_UNIT = 0x1.0p-53;
  private static long defaultGen = mix64(System.currentTimeMillis()) ^ mix64(System.nanoTime());
  private long seed;
  private final long gamma;

  private SplittableRandom(long seed, long gamma) { this.seed = seed; this.gamma = gamma; }
  public SplittableRandom(long seed) { this(seed, GOLDEN_GAMMA); }
  public SplittableRandom() {
    long s = defaultGen;
    defaultGen = s + 2 * GOLDEN_GAMMA;
    this.seed = mix64(s);
    this.gamma = mixGamma(s + GOLDEN_GAMMA);
  }

  private static long mix64(long z) {
    z = (z ^ (z >>> 30)) * 0xbf58476d1ce4e5b9L;
    z = (z ^ (z >>> 27)) * 0x94d049bb133111ebL;
    return z ^ (z >>> 31);
  }
  private static int mix32(long z) {
    z = (z ^ (z >>> 33)) * 0x62a9d9ed799705f5L;
    return (int) (((z ^ (z >>> 28)) * 0xcb24d0a5c88c35b3L) >>> 32);
  }
  private static long mixGamma(long z) {
    z = (z ^ (z >>> 33)) * 0xff51afd7ed558ccdL;
    z = (z ^ (z >>> 33)) * 0xc4ceb9fe1a85ec53L;
    z = (z ^ (z >>> 33)) | 1L;
    int n = Long.bitCount(z ^ (z >>> 1));
    return (n < 24) ? z ^ 0xaaaaaaaaaaaaaaaaL : z;
  }
  private long nextSeed() { return seed += gamma; }

  public SplittableRandom split() { return new SplittableRandom(nextLong(), mixGamma(nextSeed())); }
  public SplittableRandom split(RandomGenerator$SplittableGenerator source) {
    java.util.Objects.requireNonNull(source);
    return new SplittableRandom(source.nextLong(), mixGamma(source.nextLong()));
  }
  public Stream<RandomGenerator$SplittableGenerator> splits() { return splits(Long.MAX_VALUE, this); }
  public Stream<RandomGenerator$SplittableGenerator> splits(long streamSize) { return splits(streamSize, this); }
  public Stream<RandomGenerator$SplittableGenerator> splits(RandomGenerator$SplittableGenerator source) { return splits(Long.MAX_VALUE, source); }
  public Stream<RandomGenerator$SplittableGenerator> splits(long streamSize, RandomGenerator$SplittableGenerator source) {
    if (streamSize < 0) throw new IllegalArgumentException("size must be non-negative");
    java.util.Objects.requireNonNull(source);
    return Stream.<RandomGenerator$SplittableGenerator>generate(() -> split(source)).limit(streamSize);
  }
  public Stream<java.util.random.RandomGenerator> rngs() { return Stream.<java.util.random.RandomGenerator>generate(this::split); }
  public Stream<java.util.random.RandomGenerator> rngs(long streamSize) { if (streamSize < 0) throw new IllegalArgumentException("size must be non-negative"); return rngs().limit(streamSize); }

  public int nextInt() { return mix32(nextSeed()); }
  public long nextLong() { return mix64(nextSeed()); }
  public boolean nextBoolean() { return mix32(nextSeed()) < 0; }
  public double nextDouble() { return (nextLong() >>> 11) * DOUBLE_UNIT; }
  public float nextFloat() { return (nextInt() >>> 8) * 0x1.0p-24f; }
  public void nextBytes(byte[] bytes) {
    int i = 0, len = bytes.length;
    for (int words = len >> 3; words-- > 0; ) {
      long rnd = nextLong();
      for (int n = 8; n-- > 0; rnd >>>= Byte.SIZE) bytes[i++] = (byte) rnd;
    }
    if (i < len) for (long rnd = nextLong(); i < len; rnd >>>= Byte.SIZE) bytes[i++] = (byte) rnd;
  }

  public int nextInt(int bound) {
    if (bound <= 0) throw new IllegalArgumentException("bound must be positive");
    int r = nextInt(), m = bound - 1;
    if ((bound & m) == 0) return r & m;
    for (int u = r >>> 1; u + m - (r = u % bound) < 0; u = nextInt() >>> 1) ;
    return r;
  }
  public int nextInt(int origin, int bound) {
    if (origin >= bound) throw new IllegalArgumentException("bound must be greater than origin");
    int r = nextInt();
    int n = bound - origin, m = n - 1;
    if ((n & m) == 0) return (r & m) + origin;
    if (n > 0) {
      for (int u = r >>> 1; u + m - (r = u % n) < 0; u = nextInt() >>> 1) ;
      return r + origin;
    }
    while (r < origin || r >= bound) r = nextInt();
    return r;
  }
  public long nextLong(long bound) {
    if (bound <= 0) throw new IllegalArgumentException("bound must be positive");
    long r = nextLong(), m = bound - 1;
    if ((bound & m) == 0L) return r & m;
    for (long u = r >>> 1; u + m - (r = u % bound) < 0L; u = nextLong() >>> 1) ;
    return r;
  }
  public long nextLong(long origin, long bound) {
    if (origin >= bound) throw new IllegalArgumentException("bound must be greater than origin");
    long r = nextLong();
    long n = bound - origin, m = n - 1;
    if ((n & m) == 0L) return (r & m) + origin;
    if (n > 0L) {
      for (long u = r >>> 1; u + m - (r = u % n) < 0L; u = nextLong() >>> 1) ;
      return r + origin;
    }
    while (r < origin || r >= bound) r = nextLong();
    return r;
  }
  public double nextDouble(double bound) {
    if (!(bound > 0.0 && bound < Double.POSITIVE_INFINITY)) throw new IllegalArgumentException("bound must be finite and positive");
    double r = nextDouble() * bound;
    return r < bound ? r : Math.nextDown(bound);
  }
  public double nextDouble(double origin, double bound) {
    if (!(origin < bound && bound - origin < Double.POSITIVE_INFINITY)) throw new IllegalArgumentException("bound must be greater than origin");
    double r = nextDouble() * (bound - origin) + origin;
    return r < bound ? r : Math.nextDown(bound);
  }

  private static void size(long n) { if (n < 0) throw new IllegalArgumentException("size must be non-negative"); }
  public IntStream ints() { return IntStream.generate(this::nextInt); }
  public IntStream ints(long streamSize) { size(streamSize); return ints().limit(streamSize); }
  public IntStream ints(int origin, int bound) {
    if (origin >= bound) throw new IllegalArgumentException("bound must be greater than origin");
    return IntStream.generate(() -> nextInt(origin, bound));
  }
  public IntStream ints(long streamSize, int origin, int bound) { size(streamSize); return ints(origin, bound).limit(streamSize); }
  public LongStream longs() { return LongStream.generate(this::nextLong); }
  public LongStream longs(long streamSize) { size(streamSize); return longs().limit(streamSize); }
  public LongStream longs(long origin, long bound) {
    if (origin >= bound) throw new IllegalArgumentException("bound must be greater than origin");
    return LongStream.generate(() -> nextLong(origin, bound));
  }
  public LongStream longs(long streamSize, long origin, long bound) { size(streamSize); return longs(origin, bound).limit(streamSize); }
  public DoubleStream doubles() { return DoubleStream.generate(this::nextDouble); }
  public DoubleStream doubles(long streamSize) { size(streamSize); return doubles().limit(streamSize); }
  public DoubleStream doubles(double origin, double bound) {
    if (!(origin < bound && bound - origin < Double.POSITIVE_INFINITY)) throw new IllegalArgumentException("bound must be greater than origin");
    return DoubleStream.generate(() -> nextDouble(origin, bound));
  }
  public DoubleStream doubles(long streamSize, double origin, double bound) { size(streamSize); return doubles(origin, bound).limit(streamSize); }
}
