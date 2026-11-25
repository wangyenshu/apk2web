package android.text;

public interface Spannable extends Spanned {
  void setSpan(Object what, int start, int end, int flags);
  void removeSpan(Object what);
  /** makes the Spannable a TextView holds (SpannableString by default) */
  public static class Factory {
    private static final Factory sInstance = new Factory();
    public Factory() {}
    public static Factory getInstance() { return sInstance; }
    public Spannable newSpannable(CharSequence source) { return new SpannableString(source); }
  }
}
