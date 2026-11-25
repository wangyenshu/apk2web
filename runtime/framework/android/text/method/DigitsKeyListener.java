package android.text.method;

import android.text.InputType;
import android.text.SpannableStringBuilder;
import android.text.Spanned;

public class DigitsKeyListener extends NumberKeyListener {
  private char[] mAccepted;
  private final boolean mSign, mDecimal;
  private static final char[][] CHARACTERS = {
    {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'},
    {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '+'},
    {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.'},
    {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '+', '.'},
  };
  public DigitsKeyListener() { this(false, false); }
  public DigitsKeyListener(boolean sign, boolean decimal) { this((String) null, sign, decimal); }
  public DigitsKeyListener(java.util.Locale locale) { this(false, false); }
  public DigitsKeyListener(java.util.Locale locale, boolean sign, boolean decimal) { this(sign, decimal); }
  private DigitsKeyListener(String accepted, boolean sign, boolean decimal) {
    mSign = sign; mDecimal = decimal;
    mAccepted = accepted != null ? accepted.toCharArray() : CHARACTERS[(sign ? 1 : 0) | (decimal ? 2 : 0)];
  }
  @Override protected char[] getAcceptedChars() { return mAccepted; }
  public static DigitsKeyListener getInstance() { return new DigitsKeyListener(false, false); }
  public static DigitsKeyListener getInstance(boolean sign, boolean decimal) { return new DigitsKeyListener(sign, decimal); }
  public static DigitsKeyListener getInstance(java.util.Locale locale) { return new DigitsKeyListener(false, false); }
  public static DigitsKeyListener getInstance(java.util.Locale locale, boolean sign, boolean decimal) { return new DigitsKeyListener(sign, decimal); }
  public static DigitsKeyListener getInstance(String accepted) { return new DigitsKeyListener(accepted, false, false); }
  public static DigitsKeyListener getInstance(java.util.Locale locale, DigitsKeyListener listener) { return listener; }
  public int getInputType() {
    int contentType = InputType.TYPE_CLASS_NUMBER;
    if (mSign) contentType |= InputType.TYPE_NUMBER_FLAG_SIGNED;
    if (mDecimal) contentType |= InputType.TYPE_NUMBER_FLAG_DECIMAL;
    return contentType;
  }
  @Override public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
    CharSequence out = super.filter(source, start, end, dest, dstart, dend);
    if (!mSign && !mDecimal) return out;
    if (out != null) { source = out; start = 0; end = out.length(); }
    int sign = -1, decimal = -1, dlen = dest.length();
    for (int i = 0; i < dstart; i++) { char c = dest.charAt(i); if (c == '-' || c == '+') sign = i; else if (c == '.') decimal = i; }
    for (int i = dend; i < dlen; i++) { char c = dest.charAt(i); if (c == '-' || c == '+') return ""; else if (c == '.') decimal = i; }
    SpannableStringBuilder stripped = null;
    for (int i = end - 1; i >= start; i--) {
      char c = source.charAt(i);
      boolean strip = false;
      if (c == '-' || c == '+') { if (i != start || dstart != 0) strip = true; else if (sign >= 0) strip = true; else sign = i; }
      else if (c == '.') { if (decimal >= 0) strip = true; else decimal = i; }
      if (strip) {
        if (end == start + 1) return "";
        if (stripped == null) stripped = new SpannableStringBuilder(source, start, end);
        stripped.delete(i - start, i + 1 - start);
      }
    }
    if (stripped != null) return stripped;
    return out;
  }
}
