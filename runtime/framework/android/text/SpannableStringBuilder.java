package android.text;

/** Editable text with spans (the spans move with edits). */
public class SpannableStringBuilder implements Editable, GetChars {
  final StringBuilder sb;
  final a2w.Spans spans;
  InputFilter[] filters = new InputFilter[0];
  public SpannableStringBuilder() { sb = new StringBuilder(); spans = new a2w.Spans(); }
  public SpannableStringBuilder(CharSequence s) { this(s == null ? "" : s, 0, s == null ? 0 : s.length()); }
  public SpannableStringBuilder(CharSequence s, int start, int end) { sb = new StringBuilder(s.toString().substring(start, end)); spans = a2w.Spans.of(s, start, end); }
  public static SpannableStringBuilder valueOf(CharSequence source) { return source instanceof SpannableStringBuilder ? (SpannableStringBuilder) source : new SpannableStringBuilder(source); }
  public int length() { return sb.length(); }
  public char charAt(int i) { return sb.charAt(i); }
  public CharSequence subSequence(int s, int e) { return new SpannableStringBuilder(this, s, e); }
  public String toString() { return sb.toString(); }
  public void getChars(int s, int e, char[] d, int o) { sb.getChars(s, e, d, o); }
  public SpannableStringBuilder replace(int st, int en, CharSequence src, int start, int end) {
    CharSequence s = src.subSequence(start, end);
    for (InputFilter f : filters) {
      CharSequence r = f.filter(s, 0, s.length(), this, st, en);
      if (r != null) s = r;
    }
    a2w.Spans incoming = a2w.Spans.of(s, 0, s.length());
    sb.replace(st, en, s.toString());
    spans.replaced(st, en, s.length());
    for (Object w : incoming.get(0, s.length(), Object.class)) spans.set(w, incoming.start(w) + st, incoming.end(w) + st, incoming.flags(w), sb.length());
    return this;
  }
  public SpannableStringBuilder replace(int st, int en, CharSequence t) { return replace(st, en, t, 0, t.length()); }
  public SpannableStringBuilder insert(int w, CharSequence t, int s, int e) { return replace(w, w, t, s, e); }
  public SpannableStringBuilder insert(int w, CharSequence t) { return replace(w, w, t, 0, t.length()); }
  public SpannableStringBuilder delete(int st, int en) { sb.delete(st, en); spans.replaced(st, en, 0); return this; }
  public SpannableStringBuilder append(CharSequence t) { return replace(sb.length(), sb.length(), t, 0, t.length()); }
  public SpannableStringBuilder append(CharSequence t, int s, int e) { return replace(sb.length(), sb.length(), t, s, e); }
  public SpannableStringBuilder append(char c) { return append(String.valueOf(c)); }
  public void clear() { replace(0, sb.length(), "", 0, 0); }
  public void clearSpans() { spans.clear(); }
  public void setFilters(InputFilter[] f) { filters = f == null ? new InputFilter[0] : f; }
  public InputFilter[] getFilters() { return filters; }
  public void setSpan(Object what, int s, int e, int f) { spans.set(what, s, e, f, sb.length()); }
  public void removeSpan(Object what) { spans.remove(what); }
  public <T> T[] getSpans(int s, int e, Class<T> t) { return spans.get(s, e, t); }
  public int getSpanStart(Object t) { return spans.start(t); }
  public int getSpanEnd(Object t) { return spans.end(t); }
  public int getSpanFlags(Object t) { return spans.flags(t); }
  public int nextSpanTransition(int s, int l, Class t) { return spans.nextTransition(s, l, t); }
  public SpannableStringBuilder append(CharSequence text, Object what, int flags) { int st = sb.length(); append(text); setSpan(what, st, sb.length(), flags); return this; }
  public int getTextRunCursor(int contextStart, int contextEnd, int dir, int offset, int cursorOpt, android.graphics.Paint p) { return offset; }
  public boolean equals(Object o) { return o instanceof CharSequence && o.toString().equals(toString()); }
  public int hashCode() { return toString().hashCode(); }
}
