package android.text;

import android.graphics.Paint;
import android.text.style.LeadingMarginSpan;
import android.text.style.LineHeightSpan;
import android.text.style.MetricAffectingSpan;

/**
 * Text laid out once: lines broken at spaces, after hyphens and slashes and between CJK characters, wider words split
 * where they overflow; vertical metrics from the paint and the spans (LineHeightSpan included), line spacing as
 * Android computes it (no extra spacing after the last line), padding, maxLines and ellipsizing.
 */
public class StaticLayout extends Layout {
  private int mLineCount;
  private int[] mStarts = new int[2], mTops = new int[2], mDescents = new int[1], mEllipsisStart = new int[1], mEllipsisCount = new int[1];
  private int mTopPadding, mBottomPadding, mEllipsizedWidth, mMaxLines = Integer.MAX_VALUE;
  private boolean mIncludePad;
  private TextUtils.TruncateAt mEllipsize;
  private int mJustificationMode, mBreakStrategy, mHyphenationFrequency;
  private int[] mLeftIndents, mRightIndents;

  /** Builds a StaticLayout from its parameters. */
  public static final class Builder {
    CharSequence mText;
    int mStart, mEnd, mWidth, mEllipsizedWidth, mMaxLines = Integer.MAX_VALUE, mBreakStrategy, mHyphenationFrequency, mJustificationMode;
    TextPaint mPaint;
    Alignment mAlignment = Alignment.ALIGN_NORMAL;
    TextDirectionHeuristic mTextDir = TextDirectionHeuristics.FIRSTSTRONG_LTR;
    float mSpacingMult = 1, mSpacingAdd;
    boolean mIncludePad = true, mFallbackLineSpacing, mUseBoundsForWidth, mShiftDrawingOffsetForStartOverhang;
    TextUtils.TruncateAt mEllipsize;
    int[] mLeftIndents, mRightIndents;
    android.graphics.text.LineBreakConfig mLineBreakConfig;
    Paint.FontMetrics mMinimumFontMetrics;
    private Builder() {}
    public static Builder obtain(CharSequence source, int start, int end, TextPaint paint, int width) {
      Builder b = new Builder();
      b.mText = source; b.mStart = start; b.mEnd = end; b.mPaint = paint; b.mWidth = width; b.mEllipsizedWidth = width;
      return b;
    }
    public Builder setText(CharSequence source) { return setText(source, 0, source.length()); }
    Builder setText(CharSequence source, int start, int end) { mText = source; mStart = start; mEnd = end; return this; }
    public Builder setAlignment(Alignment alignment) { mAlignment = alignment; return this; }
    public Builder setTextDirection(TextDirectionHeuristic textDir) { mTextDir = textDir; return this; }
    public Builder setLineSpacing(float spacingAdd, float spacingMult) { mSpacingAdd = spacingAdd; mSpacingMult = spacingMult; return this; }
    public Builder setIncludePad(boolean includePad) { mIncludePad = includePad; return this; }
    public Builder setUseLineSpacingFromFallbacks(boolean useLineSpacingFromFallbacks) { mFallbackLineSpacing = useLineSpacingFromFallbacks; return this; }
    public Builder setEllipsizedWidth(int ellipsizedWidth) { mEllipsizedWidth = ellipsizedWidth; return this; }
    public Builder setEllipsize(TextUtils.TruncateAt ellipsize) { mEllipsize = ellipsize; return this; }
    public Builder setMaxLines(int maxLines) { mMaxLines = maxLines; return this; }
    public Builder setBreakStrategy(int breakStrategy) { mBreakStrategy = breakStrategy; return this; }
    public Builder setHyphenationFrequency(int hyphenationFrequency) { mHyphenationFrequency = hyphenationFrequency; return this; }
    public Builder setIndents(int[] leftIndents, int[] rightIndents) { mLeftIndents = leftIndents; mRightIndents = rightIndents; return this; }
    public Builder setJustificationMode(int justificationMode) { mJustificationMode = justificationMode; return this; }
    public Builder setLineBreakConfig(android.graphics.text.LineBreakConfig lineBreakConfig) { mLineBreakConfig = lineBreakConfig; return this; }
    public Builder setUseBoundsForWidth(boolean useBoundsForWidth) { mUseBoundsForWidth = useBoundsForWidth; return this; }
    public Builder setShiftDrawingOffsetForStartOverhang(boolean shift) { mShiftDrawingOffsetForStartOverhang = shift; return this; }
    public Builder setMinimumFontMetrics(Paint.FontMetrics minimumFontMetrics) { mMinimumFontMetrics = minimumFontMetrics; return this; }
    public StaticLayout build() { return new StaticLayout(this); }
  }

  public StaticLayout(CharSequence source, TextPaint paint, int width, Alignment align, float spacingmult, float spacingadd, boolean includepad) {
    this(source, 0, source.length(), paint, width, align, spacingmult, spacingadd, includepad);
  }
  public StaticLayout(CharSequence source, int bufstart, int bufend, TextPaint paint, int outerwidth, Alignment align, float spacingmult, float spacingadd, boolean includepad) {
    this(source, bufstart, bufend, paint, outerwidth, align, spacingmult, spacingadd, includepad, null, 0);
  }
  public StaticLayout(CharSequence source, int bufstart, int bufend, TextPaint paint, int outerwidth, Alignment align, float spacingmult, float spacingadd,
      boolean includepad, TextUtils.TruncateAt ellipsize, int ellipsizedWidth) {
    this(Builder.obtain(source, bufstart, bufend, paint, outerwidth).setAlignment(align).setLineSpacing(spacingadd, spacingmult).setIncludePad(includepad)
        .setEllipsize(ellipsize).setEllipsizedWidth(ellipsizedWidth));
  }
  private StaticLayout(Builder b) {
    super(b.mEllipsize == null ? b.mText : b.mText, b.mPaint, b.mWidth, b.mAlignment, b.mSpacingMult, b.mSpacingAdd);
    mIncludePad = b.mIncludePad;
    mEllipsize = b.mEllipsize;
    mEllipsizedWidth = b.mEllipsize != null ? b.mEllipsizedWidth : b.mWidth;
    mMaxLines = b.mMaxLines;
    mJustificationMode = b.mJustificationMode;
    mBreakStrategy = b.mBreakStrategy;
    mHyphenationFrequency = b.mHyphenationFrequency;
    mLeftIndents = b.mLeftIndents;
    mRightIndents = b.mRightIndents;
    setAttributes(b.mBreakStrategy, b.mHyphenationFrequency, b.mJustificationMode, b.mMaxLines, b.mLeftIndents, b.mRightIndents, b.mEllipsize, b.mIncludePad,
        b.mUseBoundsForWidth, b.mShiftDrawingOffsetForStartOverhang, b.mLineBreakConfig, b.mMinimumFontMetrics);
    mFallbackLineSpacingAttr = b.mFallbackLineSpacing;
    if (b.mTextDir != null) mTextDirAttr = b.mTextDir;
    generate(b.mText, b.mStart, b.mEnd, b.mPaint, b.mWidth);
  }

  // ---------------------------------------------------------------- line breaking
  private void addLine(int start, int top, int descent, int ellStart, int ellCount) {
    int n = mLineCount;
    if (n + 2 > mStarts.length) {
      int cap = Math.max(8, mStarts.length * 2);
      mStarts = java.util.Arrays.copyOf(mStarts, cap); mTops = java.util.Arrays.copyOf(mTops, cap);
      mDescents = java.util.Arrays.copyOf(mDescents, cap); mEllipsisStart = java.util.Arrays.copyOf(mEllipsisStart, cap); mEllipsisCount = java.util.Arrays.copyOf(mEllipsisCount, cap);
    }
    mStarts[n] = start; mTops[n] = top; mDescents[n] = descent; mEllipsisStart[n] = ellStart; mEllipsisCount[n] = ellCount;
    mLineCount = n + 1;
  }
  private static boolean isCjk(char c) { return c >= 0x2E80 && c <= 0x9FFF || c >= 0xAC00 && c <= 0xD7AF || c >= 0xF900 && c <= 0xFAFF || c >= 0xFF00 && c <= 0xFFEF || c >= 0x3000 && c <= 0x30FF; }
  private float width(CharSequence text, int start, int end, TextPaint paint) {
    if (end <= start) return 0;
    if (text instanceof Spanned && a2w.SpanText.styled(text)) return a2w.SpanText.width(paint, (Spanned) text, start, end);
    return paint.measureText(text, start, end);
  }
  private int indent(int[] indents, int line) { return indents == null || indents.length == 0 ? 0 : indents[Math.min(line, indents.length - 1)]; }
  private int lineWidth(CharSequence text, int start, int width, int line) {
    int w = width - indent(mLeftIndents, line) - indent(mRightIndents, line);
    if (text instanceof Spanned) {
      Spanned sp = (Spanned) text;
      for (LeadingMarginSpan m : sp.getSpans(start, start, LeadingMarginSpan.class)) {
        int ps = start;
        while (ps > 0 && text.charAt(ps - 1) != '\n') ps--;
        w -= m.getLeadingMargin(ps == start);
      }
    }
    return Math.max(1, w);
  }
  private void generate(CharSequence text, int start, int end, TextPaint paint, int width) {
    mLineCount = 0;
    java.util.ArrayList<int[]> lines = new java.util.ArrayList<>();   // {start, end}
    int p = start;
    while (p <= end) {
      int nl = TextUtils.indexOf(text, '\n', p, end);
      int paraEnd = nl < 0 ? end : nl;
      int paraNext = nl < 0 ? end : nl + 1;
      breakParagraph(text, p, paraEnd, paraNext, paint, width, lines);
      if (nl < 0) break;
      p = paraNext;
      if (p == end) { lines.add(new int[] {end, end}); break; }   // a final empty line after a trailing newline
    }
    if (lines.isEmpty()) lines.add(new int[] {start, end});
    // maxLines and ellipsizing
    boolean ellipsizeEnd = mEllipsize == TextUtils.TruncateAt.END || mEllipsize == TextUtils.TruncateAt.END_SMALL || mEllipsize == TextUtils.TruncateAt.MARQUEE && mMaxLines == 1;
    int[] ellStart = new int[lines.size()], ellCount = new int[lines.size()];
    if (lines.size() > mMaxLines) {
      int[] last = lines.get(mMaxLines - 1);
      last[1] = lines.get(lines.size() - 1)[1];
      while (lines.size() > mMaxLines) lines.remove(lines.size() - 1);
      if (mEllipsize != null && ellipsizeEnd) ellipsize(text, lines.size() - 1, lines, paint, ellStart, ellCount, TextUtils.TruncateAt.END);
    } else if (mEllipsize != null && mEllipsize != TextUtils.TruncateAt.MARQUEE && (mMaxLines == 1 || lines.size() == 1)) {
      int[] l = lines.get(0);
      if (width(text, l[0], l[1], paint) > mEllipsizedWidth) ellipsize(text, 0, lines, paint, ellStart, ellCount, mEllipsize);
    }
    // vertical metrics
    Paint.FontMetricsInt fm0 = paint.getFontMetricsInt();
    boolean needMultiply = getSpacingMultiplier() != 1 || getSpacingAdd() != 0;
    int v = 0;
    int n = lines.size();
    for (int i = 0; i < n; i++) {
      int ls = lines.get(i)[0], le = lines.get(i)[1];
      Paint.FontMetricsInt fm = new Paint.FontMetricsInt();
      fm.top = fm0.top; fm.ascent = fm0.ascent; fm.descent = fm0.descent; fm.bottom = fm0.bottom; fm.leading = fm0.leading;
      if (text instanceof Spanned && a2w.SpanText.styled(text) && le > ls) a2w.SpanText.metrics(paint, (Spanned) text, ls, le, fm);
      if (text instanceof Spanned) {
        Spanned sp = (Spanned) text;
        for (LineHeightSpan lh : sp.getSpans(ls, Math.max(ls, le), LineHeightSpan.class)) lh.chooseHeight(text, ls, le, sp.getSpanStart(lh), v, fm);
      }
      int above = fm.ascent, below = fm.descent;
      if (i == 0) { if (mIncludePad) { mTopPadding = fm.top - fm.ascent; above = fm.top; } }
      boolean lastLine = i == n - 1;
      if (lastLine && mIncludePad) { mBottomPadding = fm.bottom - fm.descent; below = fm.bottom; }
      int extra = 0;
      if (needMultiply && !lastLine) {
        double ex = (below - above) * (getSpacingMultiplier() - 1) + getSpacingAdd();
        extra = (int) (ex >= 0 ? ex + 0.5 : -(-ex + 0.5));
      }
      addLine(ls, v, below + extra, ellStart[i], ellCount[i]);
      v += below - above + extra;
    }
    if (mLineCount + 1 > mStarts.length) { mStarts = java.util.Arrays.copyOf(mStarts, mLineCount + 2); mTops = java.util.Arrays.copyOf(mTops, mLineCount + 2); }
    mStarts[mLineCount] = lines.get(n - 1)[1];
    mTops[mLineCount] = v;
  }
  /** breaks [start, paraEnd) into lines; the last ends at paraNext (after the newline) */
  private void breakParagraph(CharSequence text, int start, int paraEnd, int paraNext, TextPaint paint, int width, java.util.ArrayList<int[]> out) {
    if (paraEnd == start) { out.add(new int[] {start, paraNext}); return; }
    int lineStart = start;
    while (lineStart < paraEnd) {
      int avail = lineWidth(text, lineStart, width, out.size());
      int lastBreak = -1, i = lineStart;
      float sum = 0;
      for (; i < paraEnd; i++) {
        char c = text.charAt(i);
        float cw = width(text, i, i + 1, paint);
        if (c == ' ' || c == '\t') { lastBreak = i + 1; sum += cw; continue; }
        if (sum + cw > avail && i > lineStart) break;
        sum += cw;
        if (isCjk(c) || c == '-' || c == '/') lastBreak = i + 1;
        else if (i + 1 < paraEnd && isCjk(text.charAt(i + 1))) lastBreak = i + 1;
      }
      int lineEnd;
      if (i >= paraEnd) lineEnd = paraEnd;
      else if (lastBreak > lineStart) lineEnd = lastBreak;
      else lineEnd = Math.max(lineStart + 1, i);
      // trailing spaces stay on the line
      while (lineEnd < paraEnd && text.charAt(lineEnd) == ' ') lineEnd++;
      out.add(new int[] {lineStart, lineEnd >= paraEnd ? paraNext : lineEnd});
      lineStart = lineEnd;
      if (lineStart >= paraEnd) break;
    }
  }
  private void ellipsize(CharSequence text, int line, java.util.ArrayList<int[]> lines, TextPaint paint, int[] ellStart, int[] ellCount, TextUtils.TruncateAt where) {
    int ls = lines.get(line)[0], le = lines.get(line)[1];
    while (le > ls && (text.charAt(le - 1) == '\n')) le--;
    float avail = mEllipsizedWidth > 0 ? mEllipsizedWidth : getWidth();
    float ew = paint.measureText("…");
    int len = le - ls;
    if (width(text, ls, le, paint) <= avail && where != TextUtils.TruncateAt.END) return;
    if (where == TextUtils.TruncateAt.START) {
      int k = 0;
      while (k < len && width(text, ls + k, le, paint) + ew > avail) k++;
      ellStart[line] = 0; ellCount[line] = k;
    } else if (where == TextUtils.TruncateAt.MIDDLE) {
      int left = 0, right = 0;   // characters kept at each end
      while (left + right < len) {
        boolean leftFirst = left <= right;
        if (leftFirst && width(text, ls, ls + left + 1, paint) + ew + width(text, le - right, le, paint) <= avail) { left++; continue; }
        if (width(text, ls, ls + left, paint) + ew + width(text, le - right - 1, le, paint) <= avail) { right++; continue; }
        if (!leftFirst && width(text, ls, ls + left + 1, paint) + ew + width(text, le - right, le, paint) <= avail) { left++; continue; }
        break;
      }
      ellStart[line] = left; ellCount[line] = len - left - right;
    } else {
      int keep = len;
      while (keep > 0 && width(text, ls, ls + keep, paint) + ew > avail) keep--;
      if (keep == len && lines.size() <= mMaxLines && width(text, ls, le, paint) <= avail) return;
      ellStart[line] = keep; ellCount[line] = len - keep;
    }
  }

  // ---------------------------------------------------------------- Layout
  @Override public int getLineCount() { return mLineCount; }
  @Override public int getLineTop(int line) { return mTops[Math.max(0, Math.min(line, mLineCount))]; }
  @Override public int getLineDescent(int line) { return mDescents[Math.max(0, Math.min(line, mLineCount - 1))]; }
  @Override public int getLineStart(int line) { return mStarts[Math.max(0, Math.min(line, mLineCount))]; }
  @Override public int getTopPadding() { return mTopPadding; }
  @Override public int getBottomPadding() { return mBottomPadding; }
  @Override public int getEllipsisCount(int line) { return line < 0 || line >= mLineCount ? 0 : mEllipsisCount[line]; }
  @Override public int getEllipsisStart(int line) { return line < 0 || line >= mLineCount ? 0 : mEllipsisStart[line]; }
  @Override public int getEllipsizedWidth() { return mEllipsizedWidth; }
  /** the paint's line height for a line, as in TextView (hidden API) */
  public int getHeight(boolean cap) { return getHeight(); }
}
