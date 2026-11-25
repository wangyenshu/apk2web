package a2w.jdk.text;

/** java.text.CollationElementIterator: the collation elements of a2w.jdk.text.Collation, as primary << 16 | secondary << 8 | tertiary. */
public final class CollationElementIterator {
  public static final int NULLORDER = -1;
  private String text;
  private int[] orders, offsets;
  private int index;
  CollationElementIterator(String text) { setText(text); }
  public void setText(String source) {
    text = source;
    int[][] e = Collation.elements(source);
    orders = new int[e[0].length];
    for (int i = 0; i < orders.length; i++) orders[i] = (e[0][i] == 0 ? 0 : ((e[0][i] >>> 21) << 13 | (e[0][i] & 0x1fff))) << 16 | (e[1][i] & 0xff) << 8 | (e[2][i] & 0xff);
    // the source offset after each element (elements follow the text's code points in order)
    offsets = new int[orders.length];
    int k = 0;
    for (int i = 0; i < source.length() && k < offsets.length; ) {
      int cp = source.codePointAt(i);
      i += Character.charCount(cp);
      int n = Collation.elements(new String(Character.toChars(cp)))[0].length;
      for (int j = 0; j < n && k < offsets.length; j++) offsets[k++] = i;
    }
    while (k < offsets.length) offsets[k++] = source.length();
    index = 0;
  }
  public void setText(java.text.CharacterIterator source) {
    StringBuilder b = new StringBuilder();
    for (char c = source.first(); c != java.text.CharacterIterator.DONE; c = source.next()) b.append(c);
    setText(b.toString());
  }
  public void reset() { index = 0; }
  public int next() { return index < orders.length ? orders[index++] : NULLORDER; }
  public int previous() { return index > 0 ? orders[--index] : NULLORDER; }
  public int getOffset() { return index == 0 ? 0 : offsets[index - 1]; }
  public void setOffset(int newOffset) {
    if (newOffset < 0 || newOffset > text.length()) throw new IllegalArgumentException("Invalid offset: " + newOffset);
    index = 0;
    while (index < orders.length && offsets[index] <= newOffset) index++;
  }
  public int getMaxExpansion(int order) { return 2; }
  public static int primaryOrder(int order) { return order >>> 16; }
  public static short secondaryOrder(int order) { return (short) ((order >> 8) & 0xff); }
  public static short tertiaryOrder(int order) { return (short) (order & 0xff); }
}
