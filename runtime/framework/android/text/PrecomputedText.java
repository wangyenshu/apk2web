package android.text;

import android.graphics.Paint;
import android.graphics.Rect;

/** Text measured ahead of display: a spannable copy of the text, split into paragraphs, with the paint it was measured with. */
public class PrecomputedText implements Spannable {
  private final SpannableString mText;
  private final Params mParams;
  private final int[] mParagraphEnds;
  private PrecomputedText(CharSequence text, Params params) {
    mText = new SpannableString(text);
    mParams = params;
    java.util.ArrayList<Integer> ends = new java.util.ArrayList<>();
    for (int i = 0; i < text.length(); i++) if (text.charAt(i) == '\n') ends.add(i + 1);
    if (ends.isEmpty() || ends.get(ends.size() - 1) != text.length()) ends.add(text.length());
    mParagraphEnds = new int[ends.size()];
    for (int i = 0; i < mParagraphEnds.length; i++) mParagraphEnds[i] = ends.get(i);
  }
  public static PrecomputedText create(CharSequence text, Params params) {
    java.util.Objects.requireNonNull(text); java.util.Objects.requireNonNull(params);
    if (text instanceof PrecomputedText && ((PrecomputedText) text).mParams.equals(params)) return (PrecomputedText) text;
    return new PrecomputedText(text, params);
  }
  public Params getParams() { return mParams; }
  public int getParagraphCount() { return mParagraphEnds.length; }
  public int getParagraphStart(int paraIndex) { check(paraIndex); return paraIndex == 0 ? 0 : mParagraphEnds[paraIndex - 1]; }
  public int getParagraphEnd(int paraIndex) { check(paraIndex); return mParagraphEnds[paraIndex]; }
  private void check(int i) { if (i < 0 || i >= mParagraphEnds.length) throw new IndexOutOfBoundsException("paraIndex " + i); }
  private void checkRange(int start, int end) {
    if (start < 0 || end > length() || start > end) throw new IllegalArgumentException("invalid range " + start + ", " + end);
    for (int i = 0; i < mParagraphEnds.length; i++)
      if (start < mParagraphEnds[i] && end > mParagraphEnds[i]) throw new IllegalArgumentException("Cannot measured across the paragraph:" + start + ", " + end);
  }
  public float getWidth(int start, int end) { checkRange(start, end); return start == end ? 0 : mParams.mPaint.measureText(mText, start, end); }
  public void getBounds(int start, int end, Rect bounds) {
    checkRange(start, end);
    if (start == end) { bounds.set(0, 0, 0, 0); return; }
    mParams.mPaint.getTextBounds(mText, start, end, bounds);
  }
  public void getFontMetricsInt(int start, int end, Paint.FontMetricsInt outMetrics) {
    checkRange(start, end);
    Paint.FontMetricsInt m = mParams.mPaint.getFontMetricsInt();
    outMetrics.top = m.top; outMetrics.ascent = m.ascent; outMetrics.descent = m.descent; outMetrics.bottom = m.bottom; outMetrics.leading = m.leading;
  }
  /** a MetricAffectingSpan would invalidate the measurement */
  public void setSpan(Object what, int start, int end, int flags) {
    if (what instanceof android.text.style.MetricAffectingSpan) throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
    mText.setSpan(what, start, end, flags);
  }
  public void removeSpan(Object what) {
    if (what instanceof android.text.style.MetricAffectingSpan) throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
    mText.removeSpan(what);
  }
  public <T> T[] getSpans(int start, int end, Class<T> type) { return mText.getSpans(start, end, type); }
  public int getSpanStart(Object tag) { return mText.getSpanStart(tag); }
  public int getSpanEnd(Object tag) { return mText.getSpanEnd(tag); }
  public int getSpanFlags(Object tag) { return mText.getSpanFlags(tag); }
  public int nextSpanTransition(int start, int limit, Class type) { return mText.nextSpanTransition(start, limit, type); }
  public int length() { return mText.length(); }
  public char charAt(int index) { return mText.charAt(index); }
  public CharSequence subSequence(int start, int end) { return PrecomputedText.create(mText.subSequence(start, end), mParams); }
  @Override public String toString() { return mText.toString(); }

  /** The paint and line-breaking settings text is measured with. */
  public static final class Params {
    final TextPaint mPaint;
    final TextDirectionHeuristic mTextDir;
    final int mBreakStrategy, mHyphenationFrequency;
    final android.graphics.text.LineBreakConfig mLineBreakConfig;
    Params(TextPaint paint, TextDirectionHeuristic dir, int strategy, int frequency, android.graphics.text.LineBreakConfig config) {
      mPaint = paint; mTextDir = dir; mBreakStrategy = strategy; mHyphenationFrequency = frequency; mLineBreakConfig = config;
    }
    public TextPaint getTextPaint() { return mPaint; }
    public TextDirectionHeuristic getTextDirection() { return mTextDir; }
    public int getBreakStrategy() { return mBreakStrategy; }
    public int getHyphenationFrequency() { return mHyphenationFrequency; }
    public android.graphics.text.LineBreakConfig getLineBreakConfig() { return mLineBreakConfig; }
    /** the paint attributes that change measurements, and the settings */
    @Override public boolean equals(Object o) {
      if (o == this) return true;
      if (!(o instanceof Params)) return false;
      Params p = (Params) o;
      return sameMetrics(mPaint, p.mPaint) && mTextDir == p.mTextDir && mBreakStrategy == p.mBreakStrategy
          && mHyphenationFrequency == p.mHyphenationFrequency && java.util.Objects.equals(mLineBreakConfig, p.mLineBreakConfig);
    }
    static boolean sameMetrics(TextPaint a, TextPaint b) {
      return a.getTextSize() == b.getTextSize() && a.getTextScaleX() == b.getTextScaleX() && a.getTextSkewX() == b.getTextSkewX()
          && a.getLetterSpacing() == b.getLetterSpacing() && a.getWordSpacing() == b.getWordSpacing() && a.getFlags() == b.getFlags()
          && java.util.Objects.equals(a.getTextLocales(), b.getTextLocales()) && java.util.Objects.equals(a.getFontFeatureSettings(), b.getFontFeatureSettings())
          && java.util.Objects.equals(a.getFontVariationSettings(), b.getFontVariationSettings()) && java.util.Objects.equals(a.getTypeface(), b.getTypeface());
    }
    @Override public int hashCode() {
      return java.util.Objects.hash(mPaint.getTextSize(), mPaint.getTextScaleX(), mPaint.getTextSkewX(), mPaint.getLetterSpacing(), mPaint.getWordSpacing(),
          mPaint.getFlags(), mPaint.getTextLocales(), mPaint.getTypeface(), mPaint.getFontVariationSettings(), mTextDir, mBreakStrategy, mHyphenationFrequency, mLineBreakConfig);
    }
    @Override public String toString() {
      return "{textSize=" + mPaint.getTextSize() + ", textScaleX=" + mPaint.getTextScaleX() + ", textSkewX=" + mPaint.getTextSkewX() + ", letterSpacing=" + mPaint.getLetterSpacing()
          + ", textLocale=" + mPaint.getTextLocales() + ", typeface=" + mPaint.getTypeface() + ", textDir=" + mTextDir + ", breakStrategy=" + mBreakStrategy
          + ", hyphenationFrequency=" + mHyphenationFrequency + ", lineBreakConfig=" + mLineBreakConfig + "}";
    }

    public static class Builder {
      private final TextPaint mPaint;
      private TextDirectionHeuristic mTextDir = TextDirectionHeuristics.FIRSTSTRONG_LTR;
      private int mBreakStrategy = android.graphics.text.LineBreaker.BREAK_STRATEGY_HIGH_QUALITY, mHyphenationFrequency = Layout.HYPHENATION_FREQUENCY_NORMAL;
      private android.graphics.text.LineBreakConfig mLineBreakConfig = new android.graphics.text.LineBreakConfig.Builder().build();
      public Builder(TextPaint paint) { mPaint = paint; }
      public Builder(Params params) {
        mPaint = params.mPaint; mTextDir = params.mTextDir; mBreakStrategy = params.mBreakStrategy;
        mHyphenationFrequency = params.mHyphenationFrequency; mLineBreakConfig = params.mLineBreakConfig;
      }
      public Builder setBreakStrategy(int strategy) { mBreakStrategy = strategy; return this; }
      public Builder setHyphenationFrequency(int frequency) { mHyphenationFrequency = frequency; return this; }
      public Builder setTextDirection(TextDirectionHeuristic textDir) { mTextDir = textDir; return this; }
      public Builder setLineBreakConfig(android.graphics.text.LineBreakConfig lineBreakConfig) { mLineBreakConfig = lineBreakConfig; return this; }
      public Params build() { return new Params(mPaint, mTextDir, mBreakStrategy, mHyphenationFrequency, mLineBreakConfig); }
    }
  }
}
