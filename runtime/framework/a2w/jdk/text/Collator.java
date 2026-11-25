package a2w.jdk.text;

/**
 * java.text.Collator: locale-sensitive string comparison. Every locale gets the same ordering, a simplified Unicode
 * Collation Algorithm (a2w.jdk.text.Collation): whitespace, punctuation, symbols, digits, then letters by script; accents
 * are a secondary difference and case (lowercase first) a tertiary one. Locale tailorings are not applied.
 */
public abstract class Collator implements java.util.Comparator<Object>, Cloneable {
  public static final int PRIMARY = 0, SECONDARY = 1, TERTIARY = 2, IDENTICAL = 3;
  public static final int NO_DECOMPOSITION = 0, CANONICAL_DECOMPOSITION = 1, FULL_DECOMPOSITION = 2;
  private int strength = TERTIARY, decomposition = CANONICAL_DECOMPOSITION;
  protected Collator() {}
  public static synchronized Collator getInstance() { return getInstance(java.util.Locale.getDefault()); }
  public static Collator getInstance(java.util.Locale desiredLocale) {
    if (desiredLocale == null) throw new NullPointerException("locale == null");
    return new RuleBasedCollator();
  }
  public static synchronized java.util.Locale[] getAvailableLocales() { return java.util.Locale.getAvailableLocales(); }
  public abstract int compare(String source, String target);
  @Override public int compare(Object o1, Object o2) { return compare((String) o1, (String) o2); }
  public abstract CollationKey getCollationKey(String source);
  public boolean equals(String source, String target) { return compare(source, target) == 0; }
  public synchronized int getStrength() { return strength; }
  public synchronized void setStrength(int newStrength) {
    if (newStrength != PRIMARY && newStrength != SECONDARY && newStrength != TERTIARY && newStrength != IDENTICAL) throw new IllegalArgumentException("Incorrect comparison level.");
    strength = newStrength;
  }
  public synchronized int getDecomposition() { return decomposition; }
  public synchronized void setDecomposition(int decompositionMode) {
    if (decompositionMode != NO_DECOMPOSITION && decompositionMode != CANONICAL_DECOMPOSITION && decompositionMode != FULL_DECOMPOSITION) throw new IllegalArgumentException("Wrong decomposition mode.");
    decomposition = decompositionMode;
  }
  @Override public Object clone() { try { return super.clone(); } catch (CloneNotSupportedException e) { throw new InternalError(e); } }
  @Override public boolean equals(Object that) {
    if (this == that) return true;
    if (that == null || getClass() != that.getClass()) return false;
    Collator o = (Collator) that;
    return strength == o.strength && decomposition == o.decomposition;
  }
  @Override public abstract int hashCode();
}
