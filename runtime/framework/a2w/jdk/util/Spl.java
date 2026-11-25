package a2w.jdk.util;

import java.util.*;
import java.util.function.*;

/** Array spliterators (Arrays.spliterator, Spliterators.spliterator over arrays), empty spliterators and the
 *  iterator adapters of Spliterators, which TeaVM's class library lacks. */
public final class Spl {
  private Spl() {}

  static void range(int length, int from, int to) {
    if (from > to) throw new ArrayIndexOutOfBoundsException("origin(" + from + ") > fence(" + to + ")");
    if (from < 0) throw new ArrayIndexOutOfBoundsException(from);
    if (to > length) throw new ArrayIndexOutOfBoundsException(to);
  }
  static final int SIZED = Spliterator.SIZED | Spliterator.SUBSIZED;

  public static <T> Spliterator<T> of(Object[] a, int from, int to, int ch) { range(a.length, from, to); return new Obj<>(a, from, to, ch | SIZED); }
  public static Spliterator.OfInt of(int[] a, int from, int to, int ch) { range(a.length, from, to); return new Int(a, from, to, ch | SIZED); }
  public static Spliterator.OfLong of(long[] a, int from, int to, int ch) { range(a.length, from, to); return new Lng(a, from, to, ch | SIZED); }
  public static Spliterator.OfDouble of(double[] a, int from, int to, int ch) { range(a.length, from, to); return new Dbl(a, from, to, ch | SIZED); }

  abstract static class Base {
    int i; final int end, ch;
    Base(int i, int end, int ch) { this.i = i; this.end = end; this.ch = ch; }
    public long estimateSize() { return end - i; }
    public int characteristics() { return ch; }
    int splitAt() { int mid = (i + end) >>> 1; return mid > i ? mid : -1; }
    Comparator<Object> cmp() { if ((ch & Spliterator.SORTED) != 0) return null; throw new IllegalStateException(); }
  }
  static final class Obj<T> extends Base implements Spliterator<T> {
    final Object[] a;
    Obj(Object[] a, int i, int end, int ch) { super(i, end, ch); this.a = a; }
    @SuppressWarnings("unchecked")
    public boolean tryAdvance(Consumer<? super T> c) { Objects.requireNonNull(c); if (i >= end) return false; c.accept((T) a[i++]); return true; }
    @SuppressWarnings("unchecked")
    public void forEachRemaining(Consumer<? super T> c) { Objects.requireNonNull(c); while (i < end) c.accept((T) a[i++]); }
    public Spliterator<T> trySplit() { int m = splitAt(); if (m < 0) return null; Spliterator<T> s = new Obj<>(a, i, m, ch); i = m; return s; }
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Comparator<? super T> getComparator() { return (Comparator) cmp(); }
  }
  static final class Int extends Base implements Spliterator.OfInt {
    final int[] a;
    Int(int[] a, int i, int end, int ch) { super(i, end, ch); this.a = a; }
    public boolean tryAdvance(IntConsumer c) { Objects.requireNonNull(c); if (i >= end) return false; c.accept(a[i++]); return true; }
    public void forEachRemaining(IntConsumer c) { Objects.requireNonNull(c); while (i < end) c.accept(a[i++]); }
    public Spliterator.OfInt trySplit() { int m = splitAt(); if (m < 0) return null; Spliterator.OfInt s = new Int(a, i, m, ch); i = m; return s; }
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Comparator<? super Integer> getComparator() { return (Comparator) cmp(); }
  }
  static final class Lng extends Base implements Spliterator.OfLong {
    final long[] a;
    Lng(long[] a, int i, int end, int ch) { super(i, end, ch); this.a = a; }
    public boolean tryAdvance(LongConsumer c) { Objects.requireNonNull(c); if (i >= end) return false; c.accept(a[i++]); return true; }
    public void forEachRemaining(LongConsumer c) { Objects.requireNonNull(c); while (i < end) c.accept(a[i++]); }
    public Spliterator.OfLong trySplit() { int m = splitAt(); if (m < 0) return null; Spliterator.OfLong s = new Lng(a, i, m, ch); i = m; return s; }
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Comparator<? super Long> getComparator() { return (Comparator) cmp(); }
  }
  static final class Dbl extends Base implements Spliterator.OfDouble {
    final double[] a;
    Dbl(double[] a, int i, int end, int ch) { super(i, end, ch); this.a = a; }
    public boolean tryAdvance(DoubleConsumer c) { Objects.requireNonNull(c); if (i >= end) return false; c.accept(a[i++]); return true; }
    public void forEachRemaining(DoubleConsumer c) { Objects.requireNonNull(c); while (i < end) c.accept(a[i++]); }
    public Spliterator.OfDouble trySplit() { int m = splitAt(); if (m < 0) return null; Spliterator.OfDouble s = new Dbl(a, i, m, ch); i = m; return s; }
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Comparator<? super Double> getComparator() { return (Comparator) cmp(); }
  }

  public static <T> Spliterator<T> empty() { return new Obj<>(new Object[0], 0, 0, SIZED); }
  public static Spliterator.OfInt emptyInt() { return new Int(new int[0], 0, 0, SIZED); }
  public static Spliterator.OfLong emptyLong() { return new Lng(new long[0], 0, 0, SIZED); }
  public static Spliterator.OfDouble emptyDouble() { return new Dbl(new double[0], 0, 0, SIZED); }

  /** Spliterators.iterator(Spliterator) */
  public static <T> Iterator<T> iterator(Spliterator<? extends T> s) {
    Objects.requireNonNull(s);
    return new Iterator<T>() {
      boolean ready; T next;
      public boolean hasNext() { if (!ready) s.tryAdvance(t -> { ready = true; next = t; }); return ready; }
      public T next() { if (!ready && !hasNext()) throw new NoSuchElementException(); ready = false; T t = next; next = null; return t; }
      public void forEachRemaining(Consumer<? super T> c) { Objects.requireNonNull(c); if (ready) { ready = false; T t = next; next = null; c.accept(t); } s.forEachRemaining(c); }
    };
  }
  public static PrimitiveIterator.OfInt iterator(Spliterator.OfInt s) {
    Objects.requireNonNull(s);
    return new PrimitiveIterator.OfInt() {
      boolean ready; int next;
      public boolean hasNext() { if (!ready) s.tryAdvance((IntConsumer) t -> { ready = true; next = t; }); return ready; }
      public int nextInt() { if (!ready && !hasNext()) throw new NoSuchElementException(); ready = false; return next; }
    };
  }
  public static PrimitiveIterator.OfLong iterator(Spliterator.OfLong s) {
    Objects.requireNonNull(s);
    return new PrimitiveIterator.OfLong() {
      boolean ready; long next;
      public boolean hasNext() { if (!ready) s.tryAdvance((LongConsumer) t -> { ready = true; next = t; }); return ready; }
      public long nextLong() { if (!ready && !hasNext()) throw new NoSuchElementException(); ready = false; return next; }
    };
  }
  public static PrimitiveIterator.OfDouble iterator(Spliterator.OfDouble s) {
    Objects.requireNonNull(s);
    return new PrimitiveIterator.OfDouble() {
      boolean ready; double next;
      public boolean hasNext() { if (!ready) s.tryAdvance((DoubleConsumer) t -> { ready = true; next = t; }); return ready; }
      public double nextDouble() { if (!ready && !hasNext()) throw new NoSuchElementException(); ready = false; return next; }
    };
  }
}
