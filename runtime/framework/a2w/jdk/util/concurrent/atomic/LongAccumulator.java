package a2w.jdk.util.concurrent.atomic;

/** java.util.concurrent.atomic.LongAccumulator: one cell (threads do not run in parallel). */
public class LongAccumulator extends Number implements java.io.Serializable {
  private final java.util.function.LongBinaryOperator f;
  private final long identity;
  private long v;
  public LongAccumulator(java.util.function.LongBinaryOperator accumulatorFunction, long identity) { f = accumulatorFunction; this.identity = v = identity; }
  public void accumulate(long x) { long prev, next; do { prev = v; next = f.applyAsLong(prev, x); } while (v != prev); v = next; }
  public long get() { return v; }
  public void reset() { v = identity; }
  public long getThenReset() { long s = v; v = identity; return s; }
  public String toString() { return Long.toString(v); }
  public long longValue() { return v; }
  public int intValue() { return (int) v; }
  public float floatValue() { return (float) v; }
  public double doubleValue() { return (double) v; }
}
