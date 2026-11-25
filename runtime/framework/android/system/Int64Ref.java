package android.system;

/** A long passed by reference (Os.sendfile's offset). */
public class Int64Ref {
  public long value;
  public Int64Ref(long value) { this.value = value; }
  @Override public String toString() { return "Int64Ref{value=" + value + "}"; }
}
