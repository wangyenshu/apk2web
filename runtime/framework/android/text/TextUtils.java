package android.text;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Pattern;

/** android.text.TextUtils */
public class TextUtils {
  private TextUtils() {}
  public enum TruncateAt { START, MIDDLE, END, MARQUEE, END_SMALL }
  public interface EllipsizeCallback { void ellipsized(int start, int end); }
  public interface StringSplitter extends Iterable<String> { void setString(String string); }
  public static final int CAP_MODE_CHARACTERS = 0x1000, CAP_MODE_WORDS = 0x2000, CAP_MODE_SENTENCES = 0x4000;
  public static final int SAFE_STRING_FLAG_TRIM = 1, SAFE_STRING_FLAG_SINGLE_LINE = 2, SAFE_STRING_FLAG_FIRST_LINE = 4;
  public static final Parcelable.Creator<CharSequence> CHAR_SEQUENCE_CREATOR = new Parcelable.Creator<CharSequence>() {
    public CharSequence createFromParcel(Parcel p) { return p.readString(); }
    public CharSequence[] newArray(int size) { return new CharSequence[size]; }
  };

  public static class SimpleStringSplitter implements StringSplitter, Iterator<String> {
    private String mString;
    private final char mDelimiter;
    private int mPosition, mLength;
    public SimpleStringSplitter(char delimiter) { mDelimiter = delimiter; }
    @Override public void setString(String string) { mString = string; mPosition = 0; mLength = string.length(); }
    @Override public Iterator<String> iterator() { return this; }
    @Override public boolean hasNext() { return mPosition < mLength; }
    @Override public String next() {
      int end = mString.indexOf(mDelimiter, mPosition);
      if (end == -1) end = mLength;
      String next = mString.substring(mPosition, end);
      mPosition = end + 1;
      return next;
    }
    @Override public void remove() { throw new UnsupportedOperationException(); }
  }

  public static boolean isEmpty(CharSequence s) { return s == null || s.length() == 0; }
  public static boolean equals(CharSequence a, CharSequence b) {
    if (a == b) return true;
    if (a == null || b == null) return false;
    int n = a.length();
    if (n != b.length()) return false;
    if (a instanceof String && b instanceof String) return a.equals(b);
    for (int i = 0; i < n; i++) if (a.charAt(i) != b.charAt(i)) return false;
    return true;
  }
  public static String join(CharSequence d, Object[] t) { StringBuilder sb = new StringBuilder(); for (int i = 0; i < t.length; i++) { if (i > 0) sb.append(d); sb.append(t[i]); } return sb.toString(); }
  public static String join(CharSequence d, Iterable t) { StringBuilder sb = new StringBuilder(); boolean f = true; for (Object o : t) { if (!f) sb.append(d); f = false; sb.append(o); } return sb.toString(); }
  public static String[] split(String text, String expr) { return text.length() == 0 ? new String[0] : text.split(expr, -1); }
  public static String[] split(String text, Pattern pattern) { return text.length() == 0 ? new String[0] : pattern.split(text, -1); }
  public static boolean isDigitsOnly(CharSequence s) { for (int i = 0; i < s.length(); i++) if (!Character.isDigit(s.charAt(i))) return false; return true; }
  public static boolean isGraphic(CharSequence s) { for (int i = 0; i < s.length(); i++) if (!Character.isWhitespace(s.charAt(i)) && !Character.isISOControl(s.charAt(i))) return true; return false; }
  public static boolean isGraphic(char c) { return !Character.isWhitespace(c) && !Character.isISOControl(c); }
  public static boolean isPrintableAsciiOnly(CharSequence s) { for (int i = 0; i < s.length(); i++) { char c = s.charAt(i); if (!(c >= 0x20 && c <= 0x7E) && c != '\r' && c != '\n') return false; } return true; }
  public static int getTrimmedLength(CharSequence s) {
    int len = s.length(), start = 0;
    while (start < len && s.charAt(start) <= ' ') start++;
    int end = len;
    while (end > start && s.charAt(end - 1) <= ' ') end--;
    return end - start;
  }
  public static String htmlEncode(String s) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      switch (c) {
        case '<': sb.append("&lt;"); break;
        case '>': sb.append("&gt;"); break;
        case '&': sb.append("&amp;"); break;
        case '\'': sb.append("&#39;"); break;
        case '"': sb.append("&quot;"); break;
        default: sb.append(c);
      }
    }
    return sb.toString();
  }
  /** concatenation keeping the parts' spans (a Spanned result when any part has spans) */
  public static CharSequence concat(CharSequence... t) {
    if (t.length == 0) return "";
    if (t.length == 1) return t[0];
    boolean spanned = false;
    for (CharSequence c : t) if (c instanceof Spanned) { spanned = true; break; }
    if (!spanned) { StringBuilder sb = new StringBuilder(); for (CharSequence c : t) sb.append(c); return sb.toString(); }
    SpannableStringBuilder ssb = new SpannableStringBuilder();
    for (CharSequence c : t) ssb.append(c == null ? "null" : c);
    return new SpannedString(ssb);
  }
  public static CharSequence expandTemplate(CharSequence template, CharSequence... values) {
    if (values.length > 9) throw new IllegalArgumentException("max of 9 values are supported");
    SpannableStringBuilder ssb = new SpannableStringBuilder(template);
    int i = 0;
    while (i < ssb.length()) {
      if (ssb.charAt(i) == '^') {
        char next = i + 1 < ssb.length() ? ssb.charAt(i + 1) : 0;
        if (next == '^') { ssb.delete(i + 1, i + 2); i++; continue; }
        if (Character.isDigit(next)) {
          int which = Character.getNumericValue(next) - 1;
          if (which < 0 || which >= values.length) throw new IllegalArgumentException("template requests value ^" + (which + 1) + "; only " + values.length + " provided");
          ssb.replace(i, i + 2, values[which]);
          i += values[which].length();
          continue;
        }
      }
      i++;
    }
    return ssb;
  }
  public static int indexOf(CharSequence s, char c) { return indexOf(s, c, 0); }
  public static int indexOf(CharSequence s, char c, int from) { return s.toString().indexOf(c, from); }
  public static int indexOf(CharSequence s, char c, int start, int end) { int i = s.toString().indexOf(c, start); return i >= end ? -1 : i; }
  public static int indexOf(CharSequence s, CharSequence n) { return s.toString().indexOf(n.toString()); }
  public static int indexOf(CharSequence s, CharSequence n, int start) { return s.toString().indexOf(n.toString(), start); }
  public static int lastIndexOf(CharSequence s, char c) { return s.toString().lastIndexOf(c); }
  public static int lastIndexOf(CharSequence s, char c, int last) { return s.toString().lastIndexOf(c, last); }
  public static String substring(CharSequence s, int a, int b) { return s.toString().substring(a, b); }
  public static void getChars(CharSequence s, int a, int b, char[] d, int o) { if (s instanceof GetChars) ((GetChars) s).getChars(a, b, d, o); else for (int i = a; i < b; i++) d[o++] = s.charAt(i); }
  public static boolean regionMatches(CharSequence one, int toffset, CharSequence two, int ooffset, int len) { return one.toString().regionMatches(toffset, two.toString(), ooffset, len); }
  public static CharSequence getReverse(CharSequence source, int start, int end) { return new StringBuilder(source.subSequence(start, end)).reverse().toString(); }
  public static int getOffsetBefore(CharSequence text, int offset) { if (offset <= 0) return 0; if (offset > 1 && Character.isLowSurrogate(text.charAt(offset - 1)) && Character.isHighSurrogate(text.charAt(offset - 2))) return offset - 2; return offset - 1; }
  public static int getOffsetAfter(CharSequence text, int offset) { int len = text.length(); if (offset >= len) return len; if (offset < len - 1 && Character.isHighSurrogate(text.charAt(offset)) && Character.isLowSurrogate(text.charAt(offset + 1))) return offset + 2; return offset + 1; }
  public static CharSequence replace(CharSequence template, String[] sources, CharSequence[] destinations) {
    SpannableStringBuilder tb = new SpannableStringBuilder(template);
    for (int i = 0; i < sources.length; i++) {
      String s = sources[i];
      int where = tb.toString().indexOf(s);
      if (where >= 0) tb.replace(where, where + s.length(), destinations[i]);
    }
    return tb;
  }
  public static CharSequence stringOrSpannedString(CharSequence s) {
    if (s == null) return null;
    if (s instanceof SpannedString) return s;
    if (s instanceof Spanned) return new SpannedString(s);
    return s.toString();
  }
  public static void copySpansFrom(Spanned source, int start, int end, Class kind, Spannable dest, int destoff) {
    if (kind == null) kind = Object.class;
    for (Object o : source.getSpans(start, end, kind)) {
      int st = Math.max(source.getSpanStart(o), start), en = Math.min(source.getSpanEnd(o), end);
      dest.setSpan(o, st - start + destoff, en - start + destoff, source.getSpanFlags(o));
    }
  }
  public static void writeToParcel(CharSequence cs, Parcel p, int flags) { p.writeString(cs == null ? null : cs.toString()); }
  public static CharSequence commaEllipsize(CharSequence text, TextPaint p, float avail, String oneMore, String more) { return ellipsize(text, p, avail, TruncateAt.END); }
  public static CharSequence ellipsize(CharSequence text, TextPaint p, float avail, TruncateAt where) { return ellipsize(text, p, avail, where, false, null); }
  public static CharSequence ellipsize(CharSequence text, TextPaint p, float avail, TruncateAt where, boolean preserveLength, EllipsizeCallback cb) {
    String s = text.toString();
    if (p.measureText(s) <= avail) { if (cb != null) cb.ellipsized(0, 0); return text; }
    String e = "…";
    float ew = p.measureText(e);
    int n = s.length();
    String out;
    int cutStart, cutEnd;
    if (where == TruncateAt.START) {
      int lo = 0, hi = n;
      while (lo < hi) { int mid = (lo + hi + 1) / 2; if (p.measureText(s.substring(n - mid)) + ew <= avail) lo = mid; else hi = mid - 1; }
      cutStart = 0; cutEnd = n - lo; out = e + s.substring(n - lo);
    } else if (where == TruncateAt.MIDDLE) {
      int lo = 0, hi = n;
      while (lo < hi) { int mid = (lo + hi + 1) / 2; int l = (mid + 1) / 2, r = mid / 2; if (p.measureText(s.substring(0, l)) + p.measureText(s.substring(n - r)) + ew <= avail) lo = mid; else hi = mid - 1; }
      int l = (lo + 1) / 2, r = lo / 2;
      cutStart = l; cutEnd = n - r; out = s.substring(0, l) + e + s.substring(n - r);
    } else {
      int lo = 0, hi = n;
      while (lo < hi) { int mid = (lo + hi + 1) / 2; if (p.measureText(s.substring(0, mid)) + ew <= avail) lo = mid; else hi = mid - 1; }
      cutStart = lo; cutEnd = n; out = s.substring(0, lo) + e;
    }
    if (cb != null) cb.ellipsized(cutStart, cutEnd);
    if (preserveLength) {
      StringBuilder b = new StringBuilder(s);
      for (int i = cutStart; i < cutEnd; i++) b.setCharAt(i, '﻿');
      if (cutEnd > cutStart) b.setCharAt(cutStart, '…');
      return b.toString();
    }
    return out;
  }
  public static CharSequence listEllipsize(android.content.Context context, java.util.List<CharSequence> elements, String separator, TextPaint paint, float avail, int moreId) {
    if (elements == null || elements.isEmpty()) return "";
    StringBuilder b = new StringBuilder();
    for (int i = 0; i < elements.size(); i++) { if (i > 0) b.append(separator); b.append(elements.get(i)); }
    return ellipsize(b, paint, avail, TruncateAt.END);
  }
  public static int getCapsMode(CharSequence cs, int off, int reqModes) {
    if (off < 0) return 0;
    int mode = 0;
    if ((reqModes & CAP_MODE_CHARACTERS) != 0) mode |= CAP_MODE_CHARACTERS;
    if ((reqModes & (CAP_MODE_WORDS | CAP_MODE_SENTENCES)) == 0) return mode;
    int i = off;
    while (i > 0 && Character.isWhitespace(cs.charAt(i - 1))) i--;
    if (i == 0 || i != off) mode |= reqModes & CAP_MODE_WORDS;
    if (i == 0) return mode | (reqModes & CAP_MODE_SENTENCES);
    char c = cs.charAt(i - 1);
    if ((c == '.' || c == '!' || c == '?') && i != off) mode |= reqModes & CAP_MODE_SENTENCES;
    return mode;
  }
  public static int getLayoutDirectionFromLocale(Locale locale) {
    if (locale == null) return 0;
    String l = locale.getLanguage();
    return l.equals("ar") || l.equals("fa") || l.equals("he") || l.equals("iw") || l.equals("ur") || l.equals("yi") ? 1 : 0;
  }
  public static CharSequence makeSafeForPresentation(String unclean, int maxCharactersToConsider, float ellipsizeDip, int flags) {
    String s = unclean == null ? "" : unclean;
    if (maxCharactersToConsider > 0 && s.length() > maxCharactersToConsider) s = s.substring(0, maxCharactersToConsider);
    if ((flags & SAFE_STRING_FLAG_FIRST_LINE) != 0) { int nl = s.indexOf('\n'); if (nl >= 0) s = s.substring(0, nl); }
    if ((flags & SAFE_STRING_FLAG_SINGLE_LINE) != 0) s = s.replace('\n', ' ');
    if ((flags & SAFE_STRING_FLAG_TRIM) != 0) s = s.trim();
    return s;
  }
  public static void dumpSpans(CharSequence cs, android.util.Printer printer, String prefix) {
    if (cs instanceof Spanned) {
      Spanned sp = (Spanned) cs;
      for (Object o : sp.getSpans(0, cs.length(), Object.class))
        printer.println(prefix + cs.subSequence(sp.getSpanStart(o), sp.getSpanEnd(o)) + ": " + Integer.toHexString(System.identityHashCode(o)) + " " + o.getClass().getCanonicalName() + " (" + sp.getSpanStart(o) + "-" + sp.getSpanEnd(o) + ") fl=#" + sp.getSpanFlags(o));
    } else printer.println(prefix + cs + ": (no spans)");
  }
  public static CharSequence trimToSize(CharSequence text, int size) { return text == null || text.length() <= size ? text : text.subSequence(0, size); }
  public static CharSequence trimNoCopySpans(CharSequence charSequence) { return charSequence; }
  public static String nullIfEmpty(String str) { return isEmpty(str) ? null : str; }
  public static String emptyIfNull(String str) { return str == null ? "" : str; }
  public static int length(String s) { return s == null ? 0 : s.length(); }
  public static String safeIntern(String s) { return s == null ? null : s.intern(); }
  public static String truncateStringForUtf8Storage(String str, int maxbytes) { return str; }
}
