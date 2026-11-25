package android.text;

/**
 * A layout of editable text: laid out again (as a StaticLayout of the display text) when the text has changed since
 * the last query.
 */
public class DynamicLayout extends Layout {
  private final CharSequence mBase, mDisplay;
  private final boolean mIncludePad;
  private final TextUtils.TruncateAt mEllipsize;
  private final int mEllipsizedWidth;
  private StaticLayout mLayout;
  private String mLaidOut;
  private int mLaidOutSpans = -1;

  /** Builds a DynamicLayout. */
  public static final class Builder {
    CharSequence mBase, mDisplay;
    TextPaint mPaint;
    int mWidth, mEllipsizedWidth;
    Alignment mAlignment = Alignment.ALIGN_NORMAL;
    float mSpacingMult = 1, mSpacingAdd;
    boolean mIncludePad = true;
    TextUtils.TruncateAt mEllipsize;
    int mBreakStrategy, mHyphenationFrequency, mJustificationMode;
    boolean mUseBoundsForWidth, mShift;
    android.graphics.text.LineBreakConfig mLineBreakConfig;
    android.graphics.Paint.FontMetrics mMinimumFontMetrics;
    private Builder() {}
    public static Builder obtain(CharSequence base, TextPaint paint, int width) { Builder b = new Builder(); b.mBase = b.mDisplay = base; b.mPaint = paint; b.mWidth = b.mEllipsizedWidth = width; return b; }
    public Builder setDisplayText(CharSequence display) { mDisplay = display; return this; }
    public Builder setAlignment(Alignment alignment) { mAlignment = alignment; return this; }
    TextDirectionHeuristic mTextDir;
    boolean mFallback;
    public Builder setTextDirection(TextDirectionHeuristic textDir) { mTextDir = textDir; return this; }
    public Builder setLineSpacing(float spacingAdd, float spacingMult) { mSpacingAdd = spacingAdd; mSpacingMult = spacingMult; return this; }
    public Builder setIncludePad(boolean includePad) { mIncludePad = includePad; return this; }
    public Builder setUseLineSpacingFromFallbacks(boolean b) { mFallback = b; return this; }
    public Builder setBreakStrategy(int breakStrategy) { mBreakStrategy = breakStrategy; return this; }
    public Builder setHyphenationFrequency(int hyphenationFrequency) { mHyphenationFrequency = hyphenationFrequency; return this; }
    public Builder setJustificationMode(int justificationMode) { mJustificationMode = justificationMode; return this; }
    public Builder setLineBreakConfig(android.graphics.text.LineBreakConfig config) { mLineBreakConfig = config; return this; }
    public Builder setUseBoundsForWidth(boolean b) { mUseBoundsForWidth = b; return this; }
    public Builder setShiftDrawingOffsetForStartOverhang(boolean b) { mShift = b; return this; }
    public Builder setMinimumFontMetrics(android.graphics.Paint.FontMetrics m) { mMinimumFontMetrics = m; return this; }
    public Builder setEllipsize(TextUtils.TruncateAt ellipsize) { mEllipsize = ellipsize; return this; }
    public Builder setEllipsizedWidth(int ellipsizedWidth) { mEllipsizedWidth = ellipsizedWidth; return this; }
    public DynamicLayout build() {
      DynamicLayout d = new DynamicLayout(mBase, mDisplay, mPaint, mWidth, mAlignment, mSpacingMult, mSpacingAdd, mIncludePad, mEllipsize, mEllipsizedWidth);
      d.mFallbackLineSpacingAttr = mFallback;
      if (mTextDir != null) d.mTextDirAttr = mTextDir;
      d.setAttributes(mBreakStrategy, mHyphenationFrequency, mJustificationMode, Integer.MAX_VALUE, null, null, mEllipsize, mIncludePad, mUseBoundsForWidth, mShift, mLineBreakConfig, mMinimumFontMetrics);
      return d;
    }
  }

  public DynamicLayout(CharSequence base, TextPaint paint, int width, Alignment align, float spacingmult, float spacingadd, boolean includepad) {
    this(base, base, paint, width, align, spacingmult, spacingadd, includepad);
  }
  public DynamicLayout(CharSequence base, CharSequence display, TextPaint paint, int width, Alignment align, float spacingmult, float spacingadd, boolean includepad) {
    this(base, display, paint, width, align, spacingmult, spacingadd, includepad, null, 0);
  }
  public DynamicLayout(CharSequence base, CharSequence display, TextPaint paint, int width, Alignment align, float spacingmult, float spacingadd, boolean includepad,
      TextUtils.TruncateAt ellipsize, int ellipsizedWidth) {
    super(display, paint, width, align, spacingmult, spacingadd);
    mBase = base;
    mDisplay = display;
    mIncludePad = includepad;
    mEllipsize = ellipsize;
    mEllipsizedWidth = ellipsize == null ? width : ellipsizedWidth;
  }
  private StaticLayout current() {
    String s = mDisplay.toString();
    int spans = mDisplay instanceof Spanned ? ((Spanned) mDisplay).getSpans(0, mDisplay.length(), Object.class).length : 0;
    if (mLayout == null || !s.equals(mLaidOut) || spans != mLaidOutSpans) {
      mLayout = StaticLayout.Builder.obtain(mDisplay, 0, mDisplay.length(), getPaint(), getWidth()).setAlignment(getAlignment())
          .setLineSpacing(getSpacingAdd(), getSpacingMultiplier()).setIncludePad(mIncludePad).setEllipsize(mEllipsize).setEllipsizedWidth(mEllipsizedWidth).build();
      mLaidOut = s;
      mLaidOutSpans = spans;
    }
    return mLayout;
  }
  @Override public int getLineCount() { return current().getLineCount(); }
  @Override public int getLineTop(int line) { return current().getLineTop(line); }
  @Override public int getLineDescent(int line) { return current().getLineDescent(line); }
  @Override public int getLineStart(int line) { return current().getLineStart(line); }
  @Override public boolean getLineContainsTab(int line) { return current().getLineContainsTab(line); }
  @Override public int getParagraphDirection(int line) { return current().getParagraphDirection(line); }
  @Override public Directions getLineDirections(int line) { return current().getLineDirections(line); }
  @Override public int getTopPadding() { return current().getTopPadding(); }
  @Override public int getBottomPadding() { return current().getBottomPadding(); }
  @Override public int getEllipsizedWidth() { return mEllipsizedWidth; }
  @Override public int getEllipsisStart(int line) { return current().getEllipsisStart(line); }
  @Override public int getEllipsisCount(int line) { return current().getEllipsisCount(line); }
}
