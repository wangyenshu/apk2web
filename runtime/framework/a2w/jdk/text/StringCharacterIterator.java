package a2w.jdk.text;

/** java.text.StringCharacterIterator: a CharacterIterator over a range of a string. */
public final class StringCharacterIterator implements java.text.CharacterIterator {
  private String text;
  private int begin, end, pos;
  public StringCharacterIterator(String text) { this(text, 0); }
  public StringCharacterIterator(String text, int pos) { this(text, 0, text.length(), pos); }
  public StringCharacterIterator(String text, int begin, int end, int pos) {
    if (text == null) throw new NullPointerException();
    if (begin < 0 || begin > end || end > text.length()) throw new IllegalArgumentException("Invalid substring range");
    if (pos < begin || pos > end) throw new IllegalArgumentException("Invalid position");
    this.text = text; this.begin = begin; this.end = end; this.pos = pos;
  }
  public void setText(String text) { if (text == null) throw new NullPointerException(); this.text = text; begin = 0; end = text.length(); pos = 0; }
  @Override public char first() { pos = begin; return current(); }
  @Override public char last() { pos = end > begin ? end - 1 : end; return current(); }
  @Override public char setIndex(int p) { if (p < begin || p > end) throw new IllegalArgumentException("Invalid index"); pos = p; return current(); }
  @Override public char current() { return pos >= begin && pos < end ? text.charAt(pos) : DONE; }
  @Override public char next() { if (pos < end - 1) { pos++; return text.charAt(pos); } pos = end; return DONE; }
  @Override public char previous() { if (pos > begin) { pos--; return text.charAt(pos); } return DONE; }
  @Override public int getBeginIndex() { return begin; }
  @Override public int getEndIndex() { return end; }
  @Override public int getIndex() { return pos; }
  @Override public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof StringCharacterIterator)) return false;
    StringCharacterIterator o = (StringCharacterIterator) obj;
    return hashCode() == o.hashCode() && text.equals(o.text) && pos == o.pos && begin == o.begin && end == o.end;
  }
  @Override public int hashCode() { return text.hashCode() ^ pos ^ begin ^ end; }
  @Override public Object clone() { try { return super.clone(); } catch (CloneNotSupportedException e) { throw new InternalError(e); } }
}
