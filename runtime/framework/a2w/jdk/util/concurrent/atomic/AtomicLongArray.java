package a2w.jdk.util.concurrent.atomic;

/** java.util.concurrent.atomic.AtomicLongArray. Framework code is not preempted (a2w.Preempt), so each operation runs alone; the
 *  memory-order variants are the plain ones (one memory, no reordering). */
public class AtomicLongArray implements java.io.Serializable {
  private final long[] a;
  public AtomicLongArray(int length) { a = new long[length]; }
  public AtomicLongArray(long[] array) { a = array.clone(); }
  public final int length() { return a.length; }
  public final long get(int i) { return a[i]; }
  public final long getPlain(int i) { return get(i); }
  public final long getOpaque(int i) { return get(i); }
  public final long getAcquire(int i) { return get(i); }
  public final void set(int i, long v) { a[i] = v; }
  public final void setPlain(int i, long v) { a[i] = v; }
  public final void setOpaque(int i, long v) { a[i] = v; }
  public final void setRelease(int i, long v) { a[i] = v; }
  public final void lazySet(int i, long v) { a[i] = v; }
  public final long getAndSet(int i, long v) { long o = get(i); a[i] = v; return o; }
  public final boolean compareAndSet(int i, long expect, long update) { if (a[i] != expect) return false; a[i] = update; return true; }
  public final boolean weakCompareAndSet(int i, long expect, long update) { return compareAndSet(i, expect, update); }
  public final boolean weakCompareAndSetPlain(int i, long expect, long update) { return compareAndSet(i, expect, update); }
  public final boolean weakCompareAndSetVolatile(int i, long expect, long update) { return compareAndSet(i, expect, update); }
  public final boolean weakCompareAndSetAcquire(int i, long expect, long update) { return compareAndSet(i, expect, update); }
  public final boolean weakCompareAndSetRelease(int i, long expect, long update) { return compareAndSet(i, expect, update); }
  public final long compareAndExchange(int i, long expect, long update) { long o = get(i); if (o == expect) a[i] = update; return o; }
  public final long compareAndExchangeAcquire(int i, long expect, long update) { return compareAndExchange(i, expect, update); }
  public final long compareAndExchangeRelease(int i, long expect, long update) { return compareAndExchange(i, expect, update); }
  public final long getAndIncrement(int i) { return a[i]++; }
  public final long getAndDecrement(int i) { return a[i]--; }
  public final long getAndAdd(int i, long d) { long o = a[i]; a[i] = o + d; return o; }
  public final long incrementAndGet(int i) { return ++a[i]; }
  public final long decrementAndGet(int i) { return --a[i]; }
  public final long addAndGet(int i, long d) { return a[i] += d; }
  public final long getAndUpdate(int i, java.util.function.LongUnaryOperator f) { long prev, next; do { prev = get(i); next = f.applyAsLong(prev); } while (!compareAndSet(i, prev, next)); return prev; }
  public final long updateAndGet(int i, java.util.function.LongUnaryOperator f) { long prev, next; do { prev = get(i); next = f.applyAsLong(prev); } while (!compareAndSet(i, prev, next)); return next; }
  public final long getAndAccumulate(int i, long x, java.util.function.LongBinaryOperator f) { long prev, next; do { prev = get(i); next = f.applyAsLong(prev, x); } while (!compareAndSet(i, prev, next)); return prev; }
  public final long accumulateAndGet(int i, long x, java.util.function.LongBinaryOperator f) { long prev, next; do { prev = get(i); next = f.applyAsLong(prev, x); } while (!compareAndSet(i, prev, next)); return next; }
  public String toString() {
    if (a.length == 0) return "[]";
    StringBuilder b = new StringBuilder("[");
    for (int i = 0; i < a.length; i++) { if (i > 0) b.append(", "); b.append(get(i)); }
    return b.append(']').toString();
  }
}
