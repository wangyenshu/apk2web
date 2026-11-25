package a2w.jdk.util.concurrent.atomic;

/** java.util.concurrent.atomic.LongAdder: one cell (threads do not run in parallel). */
public class LongAdder extends Number implements java.io.Serializable {
  private long v;
  public LongAdder() {}
  public void add(long x) { v += x; }
  public void increment() { v++; }
  public void decrement() { v--; }
  public long sum() { return v; }
  public void reset() { v = 0; }
  public long sumThenReset() { long s = v; v = 0; return s; }
  public String toString() { return Long.toString(v); }
  public long longValue() { return v; }
  public int intValue() { return (int) v; }
  public float floatValue() { return (float) v; }
  public double doubleValue() { return (double) v; }
}
