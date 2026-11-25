package a2w.jdk.util.concurrent.atomic;

/** java.util.concurrent.atomic.DoubleAdder: one cell (threads do not run in parallel). */
public class DoubleAdder extends Number implements java.io.Serializable {
  private double v;
  public DoubleAdder() {}
  public void add(double x) { v += x; }
  public double sum() { return v; }
  public void reset() { v = 0; }
  public double sumThenReset() { double s = v; v = 0; return s; }
  public String toString() { return Double.toString(v); }
  public long longValue() { return (long) v; }
  public int intValue() { return (int) v; }
  public float floatValue() { return (float) v; }
  public double doubleValue() { return v; }
}
