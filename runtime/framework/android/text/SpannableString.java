package android.text;

public class SpannableString extends SpannedString implements Spannable {
  public SpannableString(CharSequence source) { super(source); }
  public SpannableString(CharSequence source, boolean ignoreNoCopySpan) { super(source); }
  private SpannableString(CharSequence source, int start, int end) { super(source, start, end); }
  public static SpannableString valueOf(CharSequence source) { return source instanceof SpannableString ? (SpannableString) source : new SpannableString(source); }
  public void setSpan(Object what, int start, int end, int flags) { spans.set(what, start, end, flags, length()); }
  public void removeSpan(Object what) { spans.remove(what); }
  @Override public CharSequence subSequence(int start, int end) { return new SpannableString(this, start, end); }
}
