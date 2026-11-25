package android.text;
public interface InputFilter {
  CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend);
  class LengthFilter implements InputFilter {
    final int max;
    public LengthFilter(int max) { this.max = max; }
    public int getMax() { return max; }
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
      int keep = max - (dest.length() - (dend - dstart));
      if (keep <= 0) return "";
      if (keep >= end - start) return null;
      return source.subSequence(start, start + keep);
    }
  }
  class AllCaps implements InputFilter {
    private final java.util.Locale mLocale;
    public AllCaps() { mLocale = null; }
    public AllCaps(java.util.Locale locale) { mLocale = java.util.Objects.requireNonNull(locale); }
    /** null when the source has no lower-case letter (kept as is), else its upper-case form (spans kept where the length does not change) */
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
      boolean lower = false;
      for (int i = start; i < end; ) { int cp = Character.codePointAt(source, i); if (Character.isLowerCase(cp) || Character.isTitleCase(cp)) { lower = true; break; } i += Character.charCount(cp); }
      if (!lower) return null;
      java.util.Locale l = mLocale != null ? mLocale : java.util.Locale.getDefault();
      String up = source.subSequence(start, end).toString().toUpperCase(l);
      if (source instanceof Spanned && up.length() == end - start) {
        SpannableString s = new SpannableString(up);
        TextUtils.copySpansFrom((Spanned) source, start, end, null, s, 0);
        return s;
      }
      return up;
    }
  }
}
