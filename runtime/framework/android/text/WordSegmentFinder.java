package android.text;

/** Words as segments: runs of letters and digits (with apostrophes inside), each CJK ideograph alone. */
public class WordSegmentFinder extends SegmentFinder {
  private final SegmentFinder.PrescribedSegmentFinder mSegments;
  public WordSegmentFinder(CharSequence text, android.icu.util.ULocale locale) { this(text); }
  public WordSegmentFinder(CharSequence text, java.util.Locale locale) { this(text); }
  private WordSegmentFinder(CharSequence text) {
    java.util.ArrayList<Integer> starts = new java.util.ArrayList<>(), ends = new java.util.ArrayList<>();
    int n = text.length(), i = 0;
    while (i < n) {
      int cp = Character.codePointAt(text, i);
      if (!Character.isLetterOrDigit(cp)) { i += Character.charCount(cp); continue; }
      int s = i;
      if (Character.isIdeographic(cp)) { i += Character.charCount(cp); }
      else {
        while (i < n) {
          int c = Character.codePointAt(text, i);
          if (Character.isLetterOrDigit(c) && !Character.isIdeographic(c) || GraphemeClusterSegmentFinder.isExtend(c)) { i += Character.charCount(c); continue; }
          if ((c == '\'' || c == 0x2019) && i + 1 < n && Character.isLetter(Character.codePointAt(text, i + 1))) { i++; continue; }
          break;
        }
      }
      starts.add(s); ends.add(i);
    }
    int[] a = new int[starts.size()], b = new int[ends.size()];
    for (int k = 0; k < a.length; k++) { a[k] = starts.get(k); b[k] = ends.get(k); }
    mSegments = new SegmentFinder.PrescribedSegmentFinder(a, b);
  }
  public int previousStartBoundary(int offset) { return mSegments.previousStartBoundary(offset); }
  public int previousEndBoundary(int offset) { return mSegments.previousEndBoundary(offset); }
  public int nextStartBoundary(int offset) { return mSegments.nextStartBoundary(offset); }
  public int nextEndBoundary(int offset) { return mSegments.nextEndBoundary(offset); }
}
