package android.system;

/** struct timeval: seconds and microseconds. */
public final class StructTimeval {
  public final long tv_sec, tv_usec;
  private StructTimeval(long sec, long usec) { tv_sec = sec; tv_usec = usec; }
  public static StructTimeval fromMillis(long millis) {
    long sec = Math.floorDiv(millis, 1000), usec = Math.floorMod(millis, 1000) * 1000;
    return new StructTimeval(sec, usec);
  }
  public long toMillis() { return tv_sec * 1000 + tv_usec / 1000; }
  @Override public boolean equals(Object o) { return o instanceof StructTimeval && ((StructTimeval) o).tv_sec == tv_sec && ((StructTimeval) o).tv_usec == tv_usec; }
  @Override public int hashCode() { return java.util.Objects.hash(tv_sec, tv_usec); }
  @Override public String toString() { return "StructTimeval{tv_sec=" + tv_sec + ", tv_usec=" + tv_usec + "}"; }
}
