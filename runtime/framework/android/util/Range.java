package android.util;

public final class Range<T extends Comparable<? super T>> {
  private final T mLower, mUpper;
  public Range(T lower, T upper) {
    if (lower == null || upper == null) throw new NullPointerException();
    if (lower.compareTo(upper) > 0) throw new IllegalArgumentException("lower must be less than or equal to upper");
    mLower = lower; mUpper = upper;
  }
  public static <T extends Comparable<? super T>> Range<T> create(T lower, T upper) { return new Range<>(lower, upper); }
  public T getLower() { return mLower; }
  public T getUpper() { return mUpper; }
  public boolean contains(T value) { return value.compareTo(mLower) >= 0 && value.compareTo(mUpper) <= 0; }
  public boolean contains(Range<T> r) { return r.mLower.compareTo(mLower) >= 0 && r.mUpper.compareTo(mUpper) <= 0; }
  public T clamp(T value) { return value.compareTo(mLower) < 0 ? mLower : value.compareTo(mUpper) > 0 ? mUpper : value; }
  public Range<T> intersect(Range<T> r) {
    T lo = mLower.compareTo(r.mLower) >= 0 ? mLower : r.mLower, hi = mUpper.compareTo(r.mUpper) <= 0 ? mUpper : r.mUpper;
    return new Range<>(lo, hi);
  }
  public Range<T> intersect(T lower, T upper) { return intersect(new Range<>(lower, upper)); }
  public Range<T> extend(Range<T> r) { return new Range<>(mLower.compareTo(r.mLower) <= 0 ? mLower : r.mLower, mUpper.compareTo(r.mUpper) >= 0 ? mUpper : r.mUpper); }
  public Range<T> extend(T lower, T upper) { return extend(new Range<>(lower, upper)); }
  public Range<T> extend(T value) { return extend(value, value); }
  @Override public boolean equals(Object o) { return o instanceof Range && mLower.equals(((Range<?>) o).mLower) && mUpper.equals(((Range<?>) o).mUpper); }
  @Override public int hashCode() { return mLower.hashCode() * 31 + mUpper.hashCode(); }
  @Override public String toString() { return "[" + mLower + ", " + mUpper + "]"; }
}
