package a2w.jdk.util;

/** java.util.Spliterators.AbstractLongSpliterator: subclasses supply tryAdvance; trySplit hands out growing batches
 *  copied into an array, as the JDK's does. */
public abstract class Spliterators$AbstractLongSpliterator implements java.util.Spliterator.OfLong {
  static final int BATCH_UNIT = 1 << 10, MAX_BATCH = 1 << 25;
  private final int ch;
  private long est;
  private int batch;
  protected Spliterators$AbstractLongSpliterator(long est, int additionalCharacteristics) {
    this.est = est;
    this.ch = (additionalCharacteristics & java.util.Spliterator.SIZED) != 0 ? additionalCharacteristics | java.util.Spliterator.SUBSIZED : additionalCharacteristics;
  }
  public java.util.Spliterator.OfLong trySplit() {
    long[] box = new long[1];
    java.util.function.LongConsumer hold = v -> box[0] = v;
    long s = est;
    if (s > 1 && tryAdvance(hold)) {
      int n = batch + BATCH_UNIT;
      if (n > s) n = (int) s;
      if (n > MAX_BATCH) n = MAX_BATCH;
      long[] a = new long[n];
      int j = 0;
      do { a[j] = box[0]; } while (++j < n && tryAdvance(hold));
      batch = j;
      if (est != Long.MAX_VALUE) est -= j;
      return Spl.of(a, 0, j, characteristics());
    }
    return null;
  }
  public long estimateSize() { return est; }
  public int characteristics() { return ch; }
}
