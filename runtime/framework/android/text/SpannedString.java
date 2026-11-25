package android.text;

import a2w.Spans;

public class SpannedString implements Spanned, GetChars {
  final String s;
  final Spans spans;
  public SpannedString(CharSequence source) { s = source.toString(); spans = Spans.of(source, 0, s.length()); }
  SpannedString(CharSequence source, int start, int end) { s = source.toString().substring(start, end); spans = Spans.of(source, start, end); }
  public static SpannedString valueOf(CharSequence source) { return source instanceof SpannedString ? (SpannedString) source : new SpannedString(source); }
  public int length() { return s.length(); }
  public char charAt(int i) { return s.charAt(i); }
  public CharSequence subSequence(int a, int b) { return new SpannedString(this, a, b); }
  public void getChars(int start, int end, char[] dest, int off) { s.getChars(start, end, dest, off); }
  public String toString() { return s; }
  public <T> T[] getSpans(int a, int b, Class<T> t) { return spans.get(a, b, t); }
  public int getSpanStart(Object t) { return spans.start(t); }
  public int getSpanEnd(Object t) { return spans.end(t); }
  public int getSpanFlags(Object t) { return spans.flags(t); }
  public int nextSpanTransition(int a, int l, Class t) { return spans.nextTransition(a, l, t); }
  @Override public boolean equals(Object o) { return o instanceof Spanned && o.toString().equals(s); }
  @Override public int hashCode() { return s.hashCode(); }
}
