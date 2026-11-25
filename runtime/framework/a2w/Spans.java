package a2w;

import java.lang.reflect.Array;
import java.util.ArrayList;

/** Span storage for the Spanned/Spannable text classes: (what, start, end, flags), kept in insertion order. */
public final class Spans {
  final ArrayList<Object> what = new ArrayList<>();
  final ArrayList<int[]> range = new ArrayList<>();   // start, end, flags
  public Spans() {}
  public Spans copy(int from, int to) {
    Spans s = new Spans();
    for (int i = 0; i < what.size(); i++) {
      int[] r = range.get(i);
      if (r[1] < from || r[0] > to) continue;
      if (r[0] == r[1] ? r[0] < from || r[0] > to : r[1] <= from || r[0] >= to) { if (!(r[0] == r[1])) continue; }
      int st = Math.max(r[0], from) - from, en = Math.min(r[1], to) - from;
      s.what.add(what.get(i));
      s.range.add(new int[] {st, en, r[2]});
    }
    return s;
  }
  /** spans of a CharSequence that has them (any Spanned), over [start, end) */
  public static Spans of(CharSequence cs, int start, int end) {
    Spans s = new Spans();
    if (!(cs instanceof android.text.Spanned)) return s;
    android.text.Spanned sp = (android.text.Spanned) cs;
    for (Object o : sp.getSpans(start, end, Object.class)) {
      int st = sp.getSpanStart(o), en = sp.getSpanEnd(o);
      if (st < start) st = start;
      if (en > end) en = end;
      s.what.add(o);
      s.range.add(new int[] {st - start, en - start, sp.getSpanFlags(o)});
    }
    return s;
  }
  public void set(Object w, int start, int end, int flags, int length) {
    if (start > end) throw new IndexOutOfBoundsException("setSpan (" + start + " ... " + end + ") has end before start");
    if (start < 0 || end > length) throw new IndexOutOfBoundsException("setSpan (" + start + " ... " + end + ") ends beyond length " + length);
    int i = what.indexOf(w);
    if (i >= 0) { range.set(i, new int[] {start, end, flags}); return; }
    what.add(w);
    range.add(new int[] {start, end, flags});
  }
  public void remove(Object w) { int i = what.indexOf(w); if (i >= 0) { what.remove(i); range.remove(i); } }
  public void clear() { what.clear(); range.clear(); }
  public int start(Object w) { int i = what.indexOf(w); return i < 0 ? -1 : range.get(i)[0]; }
  public int end(Object w) { int i = what.indexOf(w); return i < 0 ? -1 : range.get(i)[1]; }
  public int flags(Object w) { int i = what.indexOf(w); return i < 0 ? 0 : range.get(i)[2]; }
  /** the span array types the framework asks for (TeaVM generates an array type only when code names it) */
  static final Object[] ARRAY_TYPES = {
    new android.text.style.CharacterStyle[0], new android.text.style.MetricAffectingSpan[0], new android.text.style.ReplacementSpan[0],
    new android.text.style.LeadingMarginSpan[0], new android.text.style.ClickableSpan[0], new android.text.style.URLSpan[0],
    new android.text.style.ParagraphStyle[0], new android.text.style.UpdateAppearance[0], new android.text.style.ImageSpan[0],
    new android.text.style.StyleSpan[0], new android.text.style.ForegroundColorSpan[0], new android.text.style.AlignmentSpan[0],
    new android.text.NoCopySpan[0], new android.text.TextWatcher[0], new android.text.SpanWatcher[0],
  };
  @SuppressWarnings("unchecked")
  public <T> T[] get(int start, int end, Class<T> kind) {
    if (ARRAY_TYPES.length == 0) return null;
    ArrayList<Object> out = new ArrayList<>();
    for (int i = 0; i < what.size(); i++) {
      Object w = what.get(i);
      if (kind != null && kind != Object.class && !kind.isInstance(w)) continue;
      int[] r = range.get(i);
      // as SpannableStringBuilder: empty spans and touching boundaries count when the query is empty or the span is
      if (r[0] > end || r[1] < start) continue;
      if (r[0] != r[1] && start != end) { if (r[0] == end || r[1] == start) continue; }
      out.add(w);
    }
    // by priority (SPAN_PRIORITY bits), stable otherwise
    out.sort((a, b) -> Integer.compare((flags(b) & 0x00ff0000), (flags(a) & 0x00ff0000)));
    T[] arr = (T[]) Array.newInstance(kind == null ? Object.class : kind, out.size());
    for (int i = 0; i < arr.length; i++) arr[i] = (T) out.get(i);
    return arr;
  }
  public int nextTransition(int start, int limit, Class<?> kind) {
    for (int i = 0; i < what.size(); i++) {
      if (kind != null && !kind.isInstance(what.get(i))) continue;
      int[] r = range.get(i);
      if (r[0] > start && r[0] < limit) limit = r[0];
      if (r[1] > start && r[1] < limit) limit = r[1];
    }
    return limit;
  }
  /** text [st, en) was replaced by n chars: move the spans as SpannableStringBuilder does (by their point flags) */
  public void replaced(int st, int en, int n) {
    int delta = n - (en - st);
    for (int i = what.size() - 1; i >= 0; i--) {
      int[] r = range.get(i);
      int flags = r[2];
      int startFlag = (flags & 0xf0) >> 4, endFlag = flags & 0x0f;   // 1 MARK, 2 POINT, 3 PARAGRAPH
      r[0] = move(r[0], st, en, n, delta, startFlag == 2);
      r[1] = move(r[1], st, en, n, delta, endFlag != 1);
      if (r[1] < r[0]) r[1] = r[0];
      if (r[0] == r[1] && (flags & 0x21) == 0x21 && en > st && n == 0 && r[0] == st) { what.remove(i); range.remove(i); }   // SPAN_EXCLUSIVE_EXCLUSIVE emptied
    }
  }
  private static int move(int p, int st, int en, int n, int delta, boolean point) {
    if (p < st) return p;
    if (p > en) return p + delta;
    if (p == st && p == en) return point ? p + n : p;   // insertion at the boundary: POINT moves, MARK stays
    if (p == st) return p;
    if (p == en) return st + n;
    return point ? st + n : st;   // inside the replaced text
  }
  public int size() { return what.size(); }
}
