package android.text;

import android.graphics.Paint;

/**
 * A single line of text without newlines or right-to-left characters ("boring"), laid out with its measured width.
 */
public class BoringLayout extends Layout implements TextUtils.EllipsizeCallback {
  /** Font metrics of boring text, and its width. */
  public static class Metrics extends Paint.FontMetricsInt {
    public int width;
    android.graphics.RectF mDrawingBoundingBox;
    public Metrics() {}
    /** the box the text's glyphs draw in: its width and the font's extent (top to bottom) */
    public android.graphics.RectF getDrawingBoundingBox() { return mDrawingBoundingBox != null ? new android.graphics.RectF(mDrawingBoundingBox) : new android.graphics.RectF(0, top, width, bottom); }
    @Override public String toString() { return super.toString() + " width=" + width; }
  }
  private int mBottom, mDesc, mTopPadding, mBottomPadding, mEllipsizedWidth, mEllipsizedStart, mEllipsizedCount;
  private float mMax;

  public BoringLayout(CharSequence source, TextPaint paint, int outerwidth, Alignment align, float spacingMult, float spacingAdd, Metrics metrics, boolean includePad) {
    super(source, paint, outerwidth, align, spacingMult, spacingAdd);
    mEllipsizedWidth = outerwidth;
    init(source, paint, align, metrics, includePad, true);
  }
  public BoringLayout(CharSequence source, TextPaint paint, int outerwidth, Alignment align, float spacingMult, float spacingAdd, Metrics metrics, boolean includePad,
      TextUtils.TruncateAt ellipsize, int ellipsizedWidth) {
    super(source, paint, outerwidth, align, spacingMult, spacingAdd);
    boolean trust;
    if (ellipsize == null || ellipsize == TextUtils.TruncateAt.MARQUEE) { mEllipsizedWidth = outerwidth; trust = true; }
    else {
      replaceWith(TextUtils.ellipsize(source, paint, ellipsizedWidth, ellipsize, true, this), paint, outerwidth, align, spacingMult, spacingAdd);
      mEllipsizedWidth = ellipsizedWidth;
      trust = false;
    }
    init(getText(), paint, align, metrics, includePad, trust);
  }
  public BoringLayout(CharSequence source, TextPaint paint, int outerWidth, Alignment align, float spacingMult, float spacingAdd, boolean includePad,
      TextUtils.TruncateAt ellipsize, int ellipsizedWidth, boolean useFallbackLineSpacing) {
    this(source, paint, outerWidth, align, spacingMult, spacingAdd, isBoring(source, paint), includePad, ellipsize, ellipsizedWidth);
  }
  public BoringLayout(CharSequence source, TextPaint paint, int outerWidth, Alignment align, float spacingMult, float spacingAdd, Metrics metrics, boolean includePad,
      TextUtils.TruncateAt ellipsize, int ellipsizedWidth, boolean useFallbackLineSpacing) {
    this(source, paint, outerWidth, align, spacingMult, spacingAdd, metrics, includePad, ellipsize, ellipsizedWidth);
    mFallbackLineSpacingAttr = useFallbackLineSpacing;
  }
  public static BoringLayout make(CharSequence source, TextPaint paint, int outerwidth, Alignment align, float spacingMult, float spacingAdd, Metrics metrics, boolean includePad) {
    return new BoringLayout(source, paint, outerwidth, align, spacingMult, spacingAdd, metrics, includePad);
  }
  public static BoringLayout make(CharSequence source, TextPaint paint, int outerwidth, Alignment align, float spacingmult, float spacingadd, Metrics metrics, boolean includePad,
      TextUtils.TruncateAt ellipsize, int ellipsizedWidth) {
    return new BoringLayout(source, paint, outerwidth, align, spacingmult, spacingadd, metrics, includePad, ellipsize, ellipsizedWidth);
  }
  public static BoringLayout make(CharSequence source, TextPaint paint, int outerWidth, Alignment align, Metrics metrics, boolean includePad,
      TextUtils.TruncateAt ellipsize, int ellipsizedWidth, boolean useFallbackLineSpacing) {
    return new BoringLayout(source, paint, outerWidth, align, 1f, 0f, metrics, includePad, ellipsize, ellipsizedWidth);
  }
  public BoringLayout replaceOrMake(CharSequence source, TextPaint paint, int outerwidth, Alignment align, float spacingMult, float spacingAdd, Metrics metrics, boolean includePad) {
    replaceWith(source, paint, outerwidth, align, spacingMult, spacingAdd);
    mEllipsizedWidth = outerwidth;
    mEllipsizedStart = mEllipsizedCount = 0;
    init(source, paint, align, metrics, includePad, true);
    return this;
  }
  public BoringLayout replaceOrMake(CharSequence source, TextPaint paint, int outerwidth, Alignment align, float spacingMult, float spacingAdd, Metrics metrics, boolean includePad,
      TextUtils.TruncateAt ellipsize, int ellipsizedWidth) {
    if (ellipsize == null || ellipsize == TextUtils.TruncateAt.MARQUEE) return replaceOrMake(source, paint, outerwidth, align, spacingMult, spacingAdd, metrics, includePad);
    replaceWith(TextUtils.ellipsize(source, paint, ellipsizedWidth, ellipsize, true, this), paint, outerwidth, align, spacingMult, spacingAdd);
    mEllipsizedWidth = ellipsizedWidth;
    init(getText(), paint, align, metrics, includePad, false);
    return this;
  }
  public BoringLayout replaceOrMake(CharSequence source, TextPaint paint, int outerWidth, Alignment align, Metrics metrics, boolean includePad,
      TextUtils.TruncateAt ellipsize, int ellipsizedWidth, boolean useFallbackLineSpacing) {
    return replaceOrMake(source, paint, outerWidth, align, 1f, 0f, metrics, includePad, ellipsize, ellipsizedWidth);
  }
  private void init(CharSequence source, TextPaint paint, Alignment align, Metrics metrics, boolean includePad, boolean trustWidth) {
    if (metrics == null) metrics = isBoring(source, paint, new Metrics());
    if (metrics == null) { metrics = new Metrics(); paint.getFontMetricsInt(metrics); metrics.width = (int) Math.ceil(paint.measureText(source, 0, source.length())); }
    int spacing;
    if (includePad) { spacing = metrics.bottom - metrics.top; mDesc = metrics.bottom; }
    else { spacing = metrics.descent - metrics.ascent; mDesc = metrics.descent; }
    mBottom = spacing;
    mMax = trustWidth ? metrics.width : Layout.getDesiredWidth(source, 0, source.length(), paint);
    if (includePad) { mTopPadding = metrics.top - metrics.ascent; mBottomPadding = metrics.bottom - metrics.descent; }
  }
  public static Metrics isBoring(CharSequence text, TextPaint paint) { return isBoring(text, paint, null); }
  public static Metrics isBoring(CharSequence text, TextPaint paint, Metrics metrics) {
    for (int i = 0; i < text.length(); i++) {
      char c = text.charAt(i);
      if (c == '\n' || c == '\t' || c >= 0x0590 && c <= 0x08FF || c >= 0xFB1D && c <= 0xFEFC || c >= 0xD800 && c <= 0xDFFF || c >= 0x202A && c <= 0x202E || c >= 0x2066 && c <= 0x2069) return null;
    }
    if (text instanceof Spanned && ((Spanned) text).getSpans(0, text.length(), android.text.style.ParagraphStyle.class).length > 0) return null;
    Metrics fm = metrics != null ? metrics : new Metrics();
    paint.getFontMetricsInt(fm);
    if (text instanceof Spanned && a2w.SpanText.styled(text)) {
      a2w.SpanText.metrics(paint, (Spanned) text, 0, text.length(), fm);
      fm.width = (int) Math.ceil(a2w.SpanText.width(paint, (Spanned) text, 0, text.length()));
    } else fm.width = (int) Math.ceil(paint.measureText(text, 0, text.length()));
    if (text.length() > 0 && !(text instanceof Spanned)) {
      android.graphics.Rect b = new android.graphics.Rect();
      paint.getTextBounds(text, 0, text.length(), b);
      fm.mDrawingBoundingBox = new android.graphics.RectF(b);
    } else fm.mDrawingBoundingBox = null;
    return fm;
  }
  public static Metrics isBoring(CharSequence text, TextPaint paint, TextDirectionHeuristic textDir, boolean useFallbackLineSpacing, Metrics metrics) { return isBoring(text, paint, metrics); }
  public static Metrics isBoring(CharSequence text, TextPaint paint, TextDirectionHeuristic textDir, boolean useFallbackLineSpacing,
      Paint.FontMetrics minimumFontMetrics, Metrics metrics) { return isBoring(text, paint, metrics); }
  @Override public int getHeight() { return mBottom; }
  @Override public int getLineCount() { return 1; }
  @Override public int getLineTop(int line) { return line == 0 ? 0 : mBottom; }
  @Override public int getLineDescent(int line) { return mDesc; }
  @Override public int getLineStart(int line) { return line == 0 ? 0 : getText().length(); }
  @Override public int getParagraphDirection(int line) { return DIR_LEFT_TO_RIGHT; }
  @Override public boolean getLineContainsTab(int line) { return false; }
  @Override public float getLineMax(int line) { return mMax; }
  @Override public float getLineWidth(int line) { return line == 0 ? mMax : 0; }
  @Override public final Directions getLineDirections(int line) { return DIRS_ALL_LEFT_TO_RIGHT; }
  @Override public int getTopPadding() { return mTopPadding; }
  @Override public int getBottomPadding() { return mBottomPadding; }
  @Override public int getEllipsisCount(int line) { return mEllipsizedCount; }
  @Override public int getEllipsisStart(int line) { return mEllipsizedStart; }
  @Override public int getEllipsizedWidth() { return mEllipsizedWidth; }
  @Override public void ellipsized(int start, int end) { mEllipsizedStart = start; mEllipsizedCount = end - start; }
}
