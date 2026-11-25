package a2w.jdk.util.concurrent.atomic;

/** java.util.concurrent.atomic.AtomicIntegerArray. Framework code is not preempted (a2w.Preempt), so each operation runs alone; the
 *  memory-order variants are the plain ones (one memory, no reordering). */
public class AtomicIntegerArray implements java.io.Serializable {
  private final int[] a;
  public AtomicIntegerArray(int length) { a = new int[length]; }
  public AtomicIntegerArray(int[] array) { a = array.clone(); }
  public final int length() { return a.length; }
  public final int get(int i) { return a[i]; }
  public final int getPlain(int i) { return get(i); }
  public final int getOpaque(int i) { return get(i); }
  public final int getAcquire(int i) { return get(i); }
  public final void set(int i, int v) { a[i] = v; }
  public final void setPlain(int i, int v) { a[i] = v; }
  public final void setOpaque(int i, int v) { a[i] = v; }
  public final void setRelease(int i, int v) { a[i] = v; }
  public final void lazySet(int i, int v) { a[i] = v; }
  public final int getAndSet(int i, int v) { int o = get(i); a[i] = v; return o; }
  public final boolean compareAndSet(int i, int expect, int update) { if (a[i] != expect) return false; a[i] = update; return true; }
  public final boolean weakCompareAndSet(int i, int expect, int update) { return compareAndSet(i, expect, update); }
  public final boolean weakCompareAndSetPlain(int i, int expect, int update) { return compareAndSet(i, expect, update); }
  public final boolean weakCompareAndSetVolatile(int i, int expect, int update) { return compareAndSet(i, expect, update); }
  public final boolean weakCompareAndSetAcquire(int i, int expect, int update) { return compareAndSet(i, expect, update); }
  public final boolean weakCompareAndSetRelease(int i, int expect, int update) { return compareAndSet(i, expect, update); }
  public final int compareAndExchange(int i, int expect, int update) { int o = get(i); if (o == expect) a[i] = update; return o; }
  public final int compareAndExchangeAcquire(int i, int expect, int update) { return compareAndExchange(i, expect, update); }
  public final int compareAndExchangeRelease(int i, int expect, int update) { return compareAndExchange(i, expect, update); }
  public final int getAndIncrement(int i) { return a[i]++; }
  public final int getAndDecrement(int i) { return a[i]--; }
  public final int getAndAdd(int i, int d) { int o = a[i]; a[i] = o + d; return o; }
  public final int incrementAndGet(int i) { return ++a[i]; }
  public final int decrementAndGet(int i) { return --a[i]; }
  public final int addAndGet(int i, int d) { return a[i] += d; }
  public final int getAndUpdate(int i, java.util.function.IntUnaryOperator f) { int prev, next; do { prev = get(i); next = f.applyAsInt(prev); } while (!compareAndSet(i, prev, next)); return prev; }
  public final int updateAndGet(int i, java.util.function.IntUnaryOperator f) { int prev, next; do { prev = get(i); next = f.applyAsInt(prev); } while (!compareAndSet(i, prev, next)); return next; }
  public final int getAndAccumulate(int i, int x, java.util.function.IntBinaryOperator f) { int prev, next; do { prev = get(i); next = f.applyAsInt(prev, x); } while (!compareAndSet(i, prev, next)); return prev; }
  public final int accumulateAndGet(int i, int x, java.util.function.IntBinaryOperator f) { int prev, next; do { prev = get(i); next = f.applyAsInt(prev, x); } while (!compareAndSet(i, prev, next)); return next; }
  public String toString() {
    if (a.length == 0) return "[]";
    StringBuilder b = new StringBuilder("[");
    for (int i = 0; i < a.length; i++) { if (i > 0) b.append(", "); b.append(get(i)); }
    return b.append(']').toString();
  }
}
