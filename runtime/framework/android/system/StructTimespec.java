package android.system;

public final class StructTimespec implements Comparable<StructTimespec> {
  public final long tv_sec, tv_nsec;
  public StructTimespec(long sec, long nsec) { tv_sec = sec; tv_nsec = nsec; }
  @Override public int compareTo(StructTimespec o) { return tv_sec != o.tv_sec ? Long.compare(tv_sec, o.tv_sec) : Long.compare(tv_nsec, o.tv_nsec); }
  @Override public boolean equals(Object o) { return o instanceof StructTimespec && ((StructTimespec) o).tv_sec == tv_sec && ((StructTimespec) o).tv_nsec == tv_nsec; }
  @Override public int hashCode() { return (int) (tv_sec ^ tv_nsec); }
}
