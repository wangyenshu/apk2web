package android.text;

/**
 * Grapheme clusters as segments: a base character with its combining marks, variation selectors, emoji modifiers and
 * zero-width-joiner sequences; regional indicators in pairs.
 */
public class GraphemeClusterSegmentFinder extends SegmentFinder {
  private final int[] mBoundaries;
  private final int mLength;
  public GraphemeClusterSegmentFinder(CharSequence text, TextPaint textPaint) {
    mLength = text.length();
    java.util.ArrayList<Integer> b = new java.util.ArrayList<>();
    b.add(0);
    int regional = 0;
    for (int i = 0; i < mLength; ) {
      int cp = Character.codePointAt(text, i), next = i + Character.charCount(cp);
      boolean ri = cp >= 0x1F1E6 && cp <= 0x1F1FF;
      regional = ri ? regional + 1 : 0;
      if (next < mLength) {
        int n = Character.codePointAt(text, next);
        boolean extend = isExtend(n) || cp == 0x200D || (ri && regional % 2 == 1 && n >= 0x1F1E6 && n <= 0x1F1FF);
        if (!extend) b.add(next);
      } else b.add(mLength);
      i = next;
    }
    if (mLength == 0) b.clear();
    mBoundaries = new int[b.size()];
    for (int i = 0; i < mBoundaries.length; i++) mBoundaries[i] = b.get(i);
  }
  static boolean isExtend(int cp) {
    int t = Character.getType(cp);
    return t == Character.NON_SPACING_MARK || t == Character.ENCLOSING_MARK || t == Character.COMBINING_SPACING_MARK
        || cp == 0x200D || (cp >= 0xFE00 && cp <= 0xFE0F) || (cp >= 0xE0100 && cp <= 0xE01EF) || (cp >= 0x1F3FB && cp <= 0x1F3FF) || (cp >= 0xE0020 && cp <= 0xE007F);
  }
  public int previousStartBoundary(int offset) { for (int i = mBoundaries.length - 1; i >= 0; i--) { int v = mBoundaries[i]; if (v < offset && v < mLength) return v; } return DONE; }
  public int previousEndBoundary(int offset) { for (int i = mBoundaries.length - 1; i >= 0; i--) { int v = mBoundaries[i]; if (v < offset && v > 0) return v; } return DONE; }
  public int nextStartBoundary(int offset) { for (int v : mBoundaries) if (v > offset && v < mLength) return v; return DONE; }
  public int nextEndBoundary(int offset) { for (int v : mBoundaries) if (v > offset && v > 0) return v; return DONE; }
}
