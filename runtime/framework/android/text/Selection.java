package android.text;

/** The cursor and selection of a Spannable, kept as two spans. */
public class Selection {
  private Selection() {}
  private static final class START implements NoCopySpan {}
  private static final class END implements NoCopySpan {}
  public static final Object SELECTION_START = new START();
  public static final Object SELECTION_END = new END();
  public static final int getSelectionStart(CharSequence text) { return text instanceof Spanned ? ((Spanned) text).getSpanStart(SELECTION_START) : -1; }
  public static final int getSelectionEnd(CharSequence text) { return text instanceof Spanned ? ((Spanned) text).getSpanStart(SELECTION_END) : -1; }
  public static void setSelection(Spannable text, int start, int stop) {
    int ostart = getSelectionStart(text), oend = getSelectionEnd(text);
    if (ostart != start || oend != stop) {
      text.setSpan(SELECTION_START, start, start, Spanned.SPAN_POINT_POINT | Spanned.SPAN_INTERMEDIATE);
      text.setSpan(SELECTION_END, stop, stop, Spanned.SPAN_POINT_POINT);
    }
  }
  public static final void setSelection(Spannable text, int index) { setSelection(text, index, index); }
  public static final void selectAll(Spannable text) { setSelection(text, 0, text.length()); }
  public static final void extendSelection(Spannable text, int index) { if (text.getSpanStart(SELECTION_END) != index) text.setSpan(SELECTION_END, index, index, Spanned.SPAN_POINT_POINT); }
  public static final void removeSelection(Spannable text) { text.removeSpan(SELECTION_START); text.removeSpan(SELECTION_END); }
  public static boolean moveLeft(Spannable text, Layout layout) { int s = getSelectionEnd(text); if (s > 0) { setSelection(text, s - 1); return true; } return false; }
  public static boolean moveRight(Spannable text, Layout layout) { int s = getSelectionEnd(text); if (s < text.length()) { setSelection(text, s + 1); return true; } return false; }
  public static boolean moveUp(Spannable text, Layout layout) { return false; }
  public static boolean moveDown(Spannable text, Layout layout) { return false; }
  public static boolean moveToLeftEdge(Spannable text, Layout layout) { setSelection(text, 0); return true; }
  public static boolean moveToRightEdge(Spannable text, Layout layout) { setSelection(text, text.length()); return true; }
  public static boolean extendLeft(Spannable text, Layout layout) { int e = getSelectionEnd(text); if (e > 0) { extendSelection(text, e - 1); return true; } return false; }
  public static boolean extendRight(Spannable text, Layout layout) { int e = getSelectionEnd(text); if (e < text.length()) { extendSelection(text, e + 1); return true; } return false; }
  public static boolean extendToLeftEdge(Spannable text, Layout layout) { extendSelection(text, 0); return true; }
  public static boolean extendToRightEdge(Spannable text, Layout layout) { extendSelection(text, text.length()); return true; }
}
