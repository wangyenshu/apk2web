package a2w.jdk.util.concurrent.atomic;

/** java.util.concurrent.atomic.DoubleAccumulator: one cell (threads do not run in parallel). */
public class DoubleAccumulator extends Number implements java.io.Serializable {
  private final java.util.function.DoubleBinaryOperator f;
  private final double identity;
  private double v;
  public DoubleAccumulator(java.util.function.DoubleBinaryOperator accumulatorFunction, double identity) { f = accumulatorFunction; this.identity = v = identity; }
  public void accumulate(double x) {
    double prev, next;
    do { prev = v; next = f.applyAsDouble(prev, x); } while (Double.doubleToRawLongBits(v) != Double.doubleToRawLongBits(prev));
    v = next;
  }
  public double get() { return v; }
  public void reset() { v = identity; }
  public double getThenReset() { double s = v; v = identity; return s; }
  public String toString() { return Double.toString(v); }
  public long longValue() { return (long) v; }
  public int intValue() { return (int) v; }
  public float floatValue() { return (float) v; }
  public double doubleValue() { return v; }
}
