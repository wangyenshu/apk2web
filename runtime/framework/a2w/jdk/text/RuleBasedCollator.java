package a2w.jdk.text;

/** java.text.RuleBasedCollator: the ordering of a2w.jdk.text.Collation (tailoring rules are kept, not applied). */
public class RuleBasedCollator extends Collator {
  private final String rules;
  RuleBasedCollator() { rules = ""; }
  public RuleBasedCollator(String rules) throws java.text.ParseException {
    if (rules == null) throw new NullPointerException("rules == null");
    this.rules = rules;
  }
  public String getRules() { return rules; }
  @Override public int compare(String source, String target) {
    if (source == null || target == null) throw new NullPointerException();
    return Collation.compare(source, target, getStrength());
  }
  @Override public CollationKey getCollationKey(String source) {
    if (source == null) return null;
    return new Key(source, Collation.key(source, getStrength()));
  }
  public CollationElementIterator getCollationElementIterator(String source) { return new CollationElementIterator(source); }
  public CollationElementIterator getCollationElementIterator(java.text.CharacterIterator source) {
    StringBuilder b = new StringBuilder();
    for (char c = source.first(); c != java.text.CharacterIterator.DONE; c = source.next()) b.append(c);
    return new CollationElementIterator(b.toString());
  }
  @Override public Object clone() { return super.clone(); }
  @Override public boolean equals(Object obj) { return super.equals(obj) && rules.equals(((RuleBasedCollator) obj).rules); }
  @Override public int hashCode() { return rules.hashCode() ^ getStrength() * 31 ^ getDecomposition(); }

  static final class Key extends CollationKey {
    private final byte[] bytes;
    Key(String source, byte[] bytes) { super(source); this.bytes = bytes; }
    @Override public int compareTo(CollationKey target) {
      byte[] o = target.toByteArray();
      int n = Math.min(bytes.length, o.length);
      for (int i = 0; i < n; i++) if (bytes[i] != o[i]) return (bytes[i] & 0xff) < (o[i] & 0xff) ? -1 : 1;
      return Integer.compare(bytes.length, o.length);
    }
    @Override public byte[] toByteArray() { return bytes.clone(); }
    @Override public boolean equals(Object other) { return other instanceof CollationKey && compareTo((CollationKey) other) == 0; }
    @Override public int hashCode() { return java.util.Arrays.hashCode(bytes); }
  }
}
