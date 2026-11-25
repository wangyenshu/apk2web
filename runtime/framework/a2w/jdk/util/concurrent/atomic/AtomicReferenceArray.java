package a2w.jdk.util.concurrent.atomic;

/** java.util.concurrent.atomic.AtomicReferenceArray. Framework code is not preempted (a2w.Preempt), so each operation runs alone; the
 *  memory-order variants are the plain ones (one memory, no reordering). */
@SuppressWarnings("unchecked")
public class AtomicReferenceArray<E> implements java.io.Serializable {
  private final Object[] a;
  public AtomicReferenceArray(int length) { a = new Object[length]; }
  public AtomicReferenceArray(E[] array) { a = java.util.Arrays.copyOf(array, array.length, Object[].class); }
  public final int length() { return a.length; }
  public final E get(int i) { return (E) a[i]; }
  public final E getPlain(int i) { return get(i); }
  public final E getOpaque(int i) { return get(i); }
  public final E getAcquire(int i) { return get(i); }
  public final void set(int i, E v) { a[i] = v; }
  public final void setPlain(int i, E v) { a[i] = v; }
  public final void setOpaque(int i, E v) { a[i] = v; }
  public final void setRelease(int i, E v) { a[i] = v; }
  public final void lazySet(int i, E v) { a[i] = v; }
  public final E getAndSet(int i, E v) { E o = get(i); a[i] = v; return o; }
  public final boolean compareAndSet(int i, E expect, E update) { if (a[i] != expect) return false; a[i] = update; return true; }
  public final boolean weakCompareAndSet(int i, E expect, E update) { return compareAndSet(i, expect, update); }
  public final boolean weakCompareAndSetPlain(int i, E expect, E update) { return compareAndSet(i, expect, update); }
  public final boolean weakCompareAndSetVolatile(int i, E expect, E update) { return compareAndSet(i, expect, update); }
  public final boolean weakCompareAndSetAcquire(int i, E expect, E update) { return compareAndSet(i, expect, update); }
  public final boolean weakCompareAndSetRelease(int i, E expect, E update) { return compareAndSet(i, expect, update); }
  public final E compareAndExchange(int i, E expect, E update) { E o = get(i); if (o == expect) a[i] = update; return o; }
  public final E compareAndExchangeAcquire(int i, E expect, E update) { return compareAndExchange(i, expect, update); }
  public final E compareAndExchangeRelease(int i, E expect, E update) { return compareAndExchange(i, expect, update); }
  public final E getAndUpdate(int i, java.util.function.UnaryOperator<E> f) { E prev, next; do { prev = get(i); next = f.apply(prev); } while (!compareAndSet(i, prev, next)); return prev; }
  public final E updateAndGet(int i, java.util.function.UnaryOperator<E> f) { E prev, next; do { prev = get(i); next = f.apply(prev); } while (!compareAndSet(i, prev, next)); return next; }
  public final E getAndAccumulate(int i, E x, java.util.function.BinaryOperator<E> f) { E prev, next; do { prev = get(i); next = f.apply(prev, x); } while (!compareAndSet(i, prev, next)); return prev; }
  public final E accumulateAndGet(int i, E x, java.util.function.BinaryOperator<E> f) { E prev, next; do { prev = get(i); next = f.apply(prev, x); } while (!compareAndSet(i, prev, next)); return next; }
  public String toString() {
    if (a.length == 0) return "[]";
    StringBuilder b = new StringBuilder("[");
    for (int i = 0; i < a.length; i++) { if (i > 0) b.append(", "); b.append(get(i)); }
    return b.append(']').toString();
  }
}
