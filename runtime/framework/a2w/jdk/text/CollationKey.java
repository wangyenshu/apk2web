package a2w.jdk.text;

/** java.text.CollationKey: a string's collation, comparable bitwise. */
public abstract class CollationKey implements Comparable<CollationKey> {
  private final String source;
  protected CollationKey(String source) { this.source = source; }
  public abstract int compareTo(CollationKey target);
  public String getSourceString() { return source; }
  public abstract byte[] toByteArray();
}
