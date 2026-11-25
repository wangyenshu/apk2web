package android.text;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.text.style.AlignmentSpan;

/**
 * A laid out block of text: lines with their offsets and vertical metrics (from the subclass), the paint, width,
 * alignment and spacing. Drawing, hit testing, cursor and selection paths are computed here from those lines.
 * Text is left to right per paragraph unless its first strong character is right to left (the paragraph is then
 * aligned to the right; runs are not reordered).
 */
public abstract class Layout {
  public enum Alignment { ALIGN_NORMAL, ALIGN_OPPOSITE, ALIGN_CENTER }
  public static final int DIR_LEFT_TO_RIGHT = 1, DIR_RIGHT_TO_LEFT = -1;
  public static final int BREAK_STRATEGY_SIMPLE = 0, BREAK_STRATEGY_HIGH_QUALITY = 1, BREAK_STRATEGY_BALANCED = 2;
  public static final int HYPHENATION_FREQUENCY_NONE = 0, HYPHENATION_FREQUENCY_NORMAL = 1, HYPHENATION_FREQUENCY_FULL = 2,
      HYPHENATION_FREQUENCY_NORMAL_FAST = 3, HYPHENATION_FREQUENCY_FULL_FAST = 4;
  public static final int JUSTIFICATION_MODE_NONE = 0, JUSTIFICATION_MODE_INTER_WORD = 1, JUSTIFICATION_MODE_INTER_CHARACTER = 2;
  public static final float DEFAULT_LINESPACING_MULTIPLIER = 1.0f, DEFAULT_LINESPACING_ADDITION = 0.0f;
  public static final int TEXT_SELECTION_LAYOUT_LEFT_TO_RIGHT = 1, TEXT_SELECTION_LAYOUT_RIGHT_TO_LEFT = 0;

  /** whether a text segment's bounds count as inside an area (getRangeForRect) */
  public interface TextInclusionStrategy { boolean isSegmentInside(android.graphics.RectF segmentBounds, android.graphics.RectF area); }
  public static final TextInclusionStrategy INCLUSION_STRATEGY_ANY_OVERLAP = android.graphics.RectF::intersects;
  public static final TextInclusionStrategy INCLUSION_STRATEGY_CONTAINS_CENTER = (s, area) -> area.contains(s.centerX(), s.centerY());
  public static final TextInclusionStrategy INCLUSION_STRATEGY_CONTAINS_ALL = (s, area) -> area.contains(s);

  /** Runs of a line in visual order (left to right here: one run). */
  public static class Directions {
    final int[] mDirections;
    Directions(int[] dirs) { mDirections = dirs; }
  }
  static final Directions DIRS_ALL_LEFT_TO_RIGHT = new Directions(new int[] {0, 0x03ffffff});
  static final Directions DIRS_ALL_RIGHT_TO_LEFT = new Directions(new int[] {0, 0x03ffffff | 0x04000000});

  private CharSequence mText;
  private TextPaint mPaint;
  private int mWidth;
  private Alignment mAlignment = Alignment.ALIGN_NORMAL;
  private float mSpacingMult = 1, mSpacingAdd;
  private boolean mSpannedText;
  /** for the framework's own layouts (TextView's lines) */
  Layout() {}
  protected Layout(CharSequence text, TextPaint paint, int width, Alignment align, float spacingMult, float spacingAdd) {
    if (width < 0) throw new IllegalArgumentException("Layout: " + width + " < 0");
    if (paint != null) { paint.bgColor = 0; paint.baselineShift = 0; }
    mText = text;
    mPaint = paint;
    mWidth = width;
    mAlignment = align;
    mSpacingMult = spacingMult;
    mSpacingAdd = spacingAdd;
    mSpannedText = text instanceof Spanned;
  }
  // ---------------------------------------------------------------- the attributes the builders set (API 35 getters)
  private int mBreakStrategy, mHyphenationFrequency, mJustificationMode, mMaxLinesAttr = Integer.MAX_VALUE;
  private int[] mLeftIndentsAttr, mRightIndentsAttr;
  private TextUtils.TruncateAt mEllipsizeAttr;
  private boolean mIncludePadAttr = true, mUseBoundsForWidth, mShiftDrawingOffsetForStartOverhang;
  private android.graphics.text.LineBreakConfig mLineBreakConfig;
  private Paint.FontMetrics mMinimumFontMetrics;
  void setAttributes(int breakStrategy, int hyphenation, int justification, int maxLines, int[] left, int[] right, TextUtils.TruncateAt ellipsize,
      boolean includePad, boolean useBounds, boolean shift, android.graphics.text.LineBreakConfig config, Paint.FontMetrics minimum) {
    mBreakStrategy = breakStrategy; mHyphenationFrequency = hyphenation; mJustificationMode = justification; mMaxLinesAttr = maxLines;
    mLeftIndentsAttr = left; mRightIndentsAttr = right; mEllipsizeAttr = ellipsize; mIncludePadAttr = includePad; mUseBoundsForWidth = useBounds;
    mShiftDrawingOffsetForStartOverhang = shift; mLineBreakConfig = config; mMinimumFontMetrics = minimum;
  }
  public final int getBreakStrategy() { return mBreakStrategy; }
  public final int getHyphenationFrequency() { return mHyphenationFrequency; }
  public final int getJustificationMode() { return mJustificationMode; }
  public final int getMaxLines() { return mMaxLinesAttr; }
  public final int[] getLeftIndents() { return mLeftIndentsAttr == null ? null : mLeftIndentsAttr.clone(); }
  public final int[] getRightIndents() { return mRightIndentsAttr == null ? null : mRightIndentsAttr.clone(); }
  public final TextUtils.TruncateAt getEllipsize() { return mEllipsizeAttr; }
  public final boolean isFontPaddingIncluded() { return mIncludePadAttr; }
  public boolean getUseBoundsForWidth() { return mUseBoundsForWidth; }
  public boolean getShiftDrawingOffsetForStartOverhang() { return mShiftDrawingOffsetForStartOverhang; }
  public Paint.FontMetrics getMinimumFontMetrics() { return mMinimumFontMetrics; }
  public android.graphics.text.LineBreakConfig getLineBreakConfig() {
    if (mLineBreakConfig == null) mLineBreakConfig = new android.graphics.text.LineBreakConfig.Builder().build();
    return mLineBreakConfig;
  }
  protected final boolean isSpanned() { return mSpannedText; }
  void replaceWith(CharSequence text, TextPaint paint, int width, Alignment align, float spacingmult, float spacingadd) {
    mText = text; mPaint = paint; mWidth = width; mAlignment = align; mSpacingMult = spacingmult; mSpacingAdd = spacingadd; mSpannedText = text instanceof Spanned;
  }

  // ---------------------------------------------------------------- what subclasses give
  public abstract int getLineCount();
  public abstract int getLineTop(int line);
  public abstract int getLineDescent(int line);
  public abstract int getLineStart(int line);
  public int getParagraphDirection(int line) { return firstStrongRtl(line) ? DIR_RIGHT_TO_LEFT : DIR_LEFT_TO_RIGHT; }
  public boolean getLineContainsTab(int line) { CharSequence t = getText(); if (t == null) return false; for (int i = getLineStart(line), e = getLineEnd(line); i < e; i++) if (t.charAt(i) == '\t') return true; return false; }
  public Directions getLineDirections(int line) { return getParagraphDirection(line) == DIR_RIGHT_TO_LEFT ? DIRS_ALL_RIGHT_TO_LEFT : DIRS_ALL_LEFT_TO_RIGHT; }
  public int getTopPadding() { return 0; }
  public int getBottomPadding() { return 0; }
  public int getEllipsisStart(int line) { return 0; }
  public int getEllipsisCount(int line) { return 0; }

  // ---------------------------------------------------------------- properties
  public final CharSequence getText() { return mText; }
  public final TextPaint getPaint() { return mPaint; }
  public int getWidth() { return mWidth; }
  public int getEllipsizedWidth() { return getWidth(); }
  public final void increaseWidthTo(int wid) {
    if (wid < mWidth) throw new RuntimeException("attempted to reduce Layout width");
    mWidth = wid;
  }
  public int getHeight() { return getLineTop(getLineCount()); }
  public int getHeight(boolean cap) { return getHeight(); }
  public final Alignment getAlignment() { return mAlignment; }
  public final float getSpacingMultiplier() { return mSpacingMult; }
  public final float getSpacingAdd() { return mSpacingAdd; }
  public float getLineSpacingMultiplier() { return mSpacingMult; }
  public float getLineSpacingAmount() { return mSpacingAdd; }
  boolean mFallbackLineSpacingAttr;
  TextDirectionHeuristic mTextDirAttr = TextDirectionHeuristics.FIRSTSTRONG_LTR;
  public boolean isFallbackLineSpacingEnabled() { return mFallbackLineSpacingAttr; }
  public final TextDirectionHeuristic getTextDirectionHeuristic() { return mTextDirAttr; }

  // ---------------------------------------------------------------- lines
  public int getLineEnd(int line) { return getLineStart(line + 1); }
  public int getLineVisibleEnd(int line) {
    CharSequence t = getText();
    int start = getLineStart(line), end = getLineEnd(line);
    if (line == getLineCount() - 1 && t != null) end = Math.min(end, t.length());
    while (end > start && t != null) {
      char c = t.charAt(end - 1);
      if (c == '\n') { end--; continue; }
      if (c != ' ' && c != '\t') break;
      end--;
    }
    return end;
  }
  public int getLineBottom(int line) { return getLineTop(line + 1); }
  public int getLineBottom(int line, boolean includeLineSpacing) { return getLineBottom(line); }
  public int getLineBaseline(int line) { return getLineTop(line + 1) - getLineDescent(line); }
  public int getLineAscent(int line) { return getLineTop(line) - (getLineTop(line + 1) - getLineDescent(line)); }
  public int getLineBounds(int line, Rect bounds) {
    if (bounds != null) { bounds.left = 0; bounds.top = getLineTop(line); bounds.right = mWidth; bounds.bottom = getLineTop(line + 1); }
    return getLineBaseline(line);
  }
  public int getLineForVertical(int vertical) {
    int high = getLineCount(), low = -1;
    while (high - low > 1) { int guess = (high + low) >>> 1; if (getLineTop(guess) > vertical) high = guess; else low = guess; }
    return low < 0 ? 0 : low;
  }
  public int getLineForOffset(int offset) {
    int high = getLineCount(), low = -1;
    while (high - low > 1) { int guess = (high + low) >>> 1; if (getLineStart(guess) > offset) high = guess; else low = guess; }
    return low < 0 ? 0 : low;
  }
  public final int getParagraphLeft(int line) { return 0; }
  public final int getParagraphRight(int line) { return mWidth; }
  public final Alignment getParagraphAlignment(int line) {
    Alignment align = mAlignment;
    if (mSpannedText && getText() instanceof Spanned) {
      Spanned sp = (Spanned) getText();
      AlignmentSpan[] spans = sp.getSpans(getLineStart(line), getLineEnd(line), AlignmentSpan.class);
      if (spans.length > 0) align = spans[spans.length - 1].getAlignment();
    }
    return align;
  }
  /** the width of the line's text (without trailing whitespace) */
  public float getLineWidth(int line) { return measure(getLineStart(line), getLineVisibleEnd(line), line); }
  public float getLineMax(int line) { return getLineWidth(line); }
  public float getLineLeft(int line) {
    boolean rtl = getParagraphDirection(line) == DIR_RIGHT_TO_LEFT;
    Alignment a = getParagraphAlignment(line);
    float w = getLineMax(line);
    if (a == Alignment.ALIGN_CENTER) return (float) Math.floor((mWidth - w) / 2);
    boolean right = rtl ? a == Alignment.ALIGN_NORMAL : a == Alignment.ALIGN_OPPOSITE;
    return right ? mWidth - w : 0;
  }
  public float getLineRight(int line) { return getLineLeft(line) + getLineMax(line); }
  public boolean isRtlCharAt(int offset) {
    CharSequence t = getText();
    if (t == null || offset < 0 || offset >= t.length()) return false;
    byte d = a2w.jdk.Compat.Character_getDirectionality(Character.codePointAt(t, offset));
    return d == Character.DIRECTIONALITY_RIGHT_TO_LEFT || d == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC;
  }
  private boolean firstStrongRtl(int line) {
    CharSequence t = getText();
    if (t == null) return false;
    int p = getLineStart(line);
    while (p > 0 && t.charAt(p - 1) != '\n') p--;
    for (int i = p; i < t.length() && t.charAt(i) != '\n'; i++) {
      byte d = a2w.jdk.Compat.Character_getDirectionality(t.charAt(i));
      if (d == Character.DIRECTIONALITY_LEFT_TO_RIGHT) return false;
      if (d == Character.DIRECTIONALITY_RIGHT_TO_LEFT || d == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC) return true;
    }
    return false;
  }

  // ---------------------------------------------------------------- measuring
  /** the width of text[start, end) on line (ellipsized text counts as drawn) */
  float measure(int start, int end, int line) {
    CharSequence t = getText();
    if (t == null || end <= start) return 0;
    String s = visible(line, start, end);
    if (t instanceof Spanned && a2w.SpanText.styled(t)) {
      if (s.equals(t.subSequence(start, end).toString())) return a2w.SpanText.width(mPaint, (Spanned) t, start, end);
    }
    return mPaint.measureText(s);
  }
  /** the text of [start, end) of a line as drawn: the ellipsized range replaced by "…" */
  String visible(int line, int start, int end) {
    CharSequence t = getText();
    int ls = getLineStart(line), ec = getEllipsisCount(line);
    StringBuilder b = new StringBuilder();
    for (int i = start; i < end; i++) {
      char c = t.charAt(i);
      if (c == '\n') continue;
      if (ec > 0) {
        int es = ls + getEllipsisStart(line);
        if (i >= es && i < es + ec) { if (i == es) b.append('…'); continue; }
      }
      b.append(c);
    }
    return b.toString();
  }
  public float getPrimaryHorizontal(int offset) {
    int line = getLineForOffset(offset);
    int start = getLineStart(line);
    return getLineLeft(line) + measure(start, Math.min(Math.max(offset, start), getLineEnd(line)), line);
  }
  public float getSecondaryHorizontal(int offset) { return getPrimaryHorizontal(offset); }
  public int getOffsetForHorizontal(int line, float horiz) {
    int start = getLineStart(line), end = getLineVisibleEnd(line);
    float left = getLineLeft(line);
    int best = start;
    float bestDist = Math.abs(horiz - left);
    for (int i = start + 1; i <= end; i++) {
      float x = left + measure(start, i, line);
      float d = Math.abs(horiz - x);
      if (d < bestDist) { bestDist = d; best = i; }
    }
    return best;
  }
  public int getOffsetToLeftOf(int offset) { return Math.max(0, offset - 1); }
  public int getOffsetToRightOf(int offset) { CharSequence t = getText(); return Math.min(t == null ? 0 : t.length(), offset + 1); }
  public void getCursorPath(int point, Path dest, CharSequence editingBuffer) {
    dest.reset();
    int line = getLineForOffset(point);
    int top = getLineTop(line), bottom = getLineBottom(line);
    float h = getPrimaryHorizontal(point) - 0.5f;
    dest.moveTo(h, top);
    dest.lineTo(h, bottom);
  }
  public void getSelectionPath(int start, int end, Path dest) {
    dest.reset();
    if (start == end) return;
    if (end < start) { int t = end; end = start; start = t; }
    int startLine = getLineForOffset(start), endLine = getLineForOffset(end);
    for (int line = startLine; line <= endLine; line++) {
      int ls = getLineStart(line), le = getLineEnd(line);
      float left = line == startLine ? getPrimaryHorizontal(start) : getLineLeft(line);
      float right = line == endLine ? getPrimaryHorizontal(Math.min(end, le)) : (line < endLine ? mWidth : getLineRight(line));
      dest.addRect(left, getLineTop(line), right, getLineBottom(line), Path.Direction.CW);
    }
  }
  public static float getDesiredWidth(CharSequence source, TextPaint paint) { return getDesiredWidth(source, 0, source.length(), paint); }
  public static float getDesiredWidth(CharSequence source, int start, int end, TextPaint paint) {
    float need = 0;
    for (int i = start; i <= end; i++) {
      int next = TextUtils.indexOf(source, '\n', i, end);
      if (next < 0) next = end;
      float w;
      if (source instanceof Spanned && a2w.SpanText.styled(source)) w = a2w.SpanText.width(paint, (Spanned) source, i, next);
      else w = paint.measureText(source, i, next);
      if (w > need) need = w;
      i = next;
    }
    return need;
  }
  public static float getDesiredWidth(CharSequence source, int start, int end, TextPaint paint, TextDirectionHeuristic textDir) { return getDesiredWidth(source, start, end, paint); }

  // ---------------------------------------------------------------- drawing
  public void draw(Canvas c) { draw(c, null, null, 0); }
  public void draw(Canvas canvas, Path highlight, Paint highlightPaint, int cursorOffsetVertical) {
    int n = getLineCount();
    if (n == 0) return;
    Rect clip = new Rect();
    int first = 0, last = n - 1;
    if (canvas.getClipBounds(clip)) {
      first = getLineForVertical(Math.max(0, clip.top));
      last = getLineForVertical(clip.bottom);
    }
    if (highlight != null) {
      if (cursorOffsetVertical != 0) canvas.translate(0, cursorOffsetVertical);
      canvas.drawPath(highlight, highlightPaint);
      if (cursorOffsetVertical != 0) canvas.translate(0, -cursorOffsetVertical);
    }
    drawText(canvas, first, last);
  }
  public void drawText(Canvas canvas) { drawText(canvas, 0, getLineCount() - 1); }
  void drawText(Canvas canvas, int first, int last) {
    CharSequence t = getText();
    if (t == null) return;
    boolean styled = t instanceof Spanned && a2w.SpanText.styled(t);
    for (int line = first; line <= last && line < getLineCount(); line++) {
      int start = getLineStart(line), end = getLineVisibleEnd(line);
      int top = getLineTop(line), bottom = getLineBottom(line), baseline = getLineBaseline(line);
      float x = getLineLeft(line);
      if (styled && getEllipsisCount(line) == 0) {
        int margin = a2w.SpanText.drawMargins(canvas, mPaint, (Spanned) t, start, end, top, baseline, bottom, this);
        a2w.SpanText.draw(canvas, mPaint, (Spanned) t, start, end, x + margin, top, baseline, bottom);
      } else {
        String s = visible(line, start, end);
        if (!s.isEmpty()) canvas.drawText(s, x, baseline, mPaint);
      }
    }
  }
  public void drawBackground(Canvas canvas) {}
  /** highlights (each path with its paint), the selection, then the text */
  public void draw(Canvas canvas, java.util.List<Path> highlightPaths, java.util.List<Paint> highlightPaints, Path selectionPath, Paint selectionPaint, int cursorOffsetVertical) {
    if (cursorOffsetVertical != 0) canvas.translate(0, cursorOffsetVertical);
    if (highlightPaths != null && highlightPaints != null) {
      if (highlightPaths.size() != highlightPaints.size()) throw new IllegalArgumentException("The highlight path size is different from highlight paints");
      for (int i = 0; i < highlightPaths.size(); i++) if (highlightPaths.get(i) != null && highlightPaints.get(i) != null) canvas.drawPath(highlightPaths.get(i), highlightPaints.get(i));
    }
    if (selectionPath != null && selectionPaint != null) canvas.drawPath(selectionPath, selectionPaint);
    if (cursorOffsetVertical != 0) canvas.translate(0, -cursorOffsetVertical);
    draw(canvas, null, null, 0);
  }
  /** the box the text draws in: the lines' extents */
  public android.graphics.RectF computeDrawingBoundingBox() {
    int n = getLineCount();
    if (n == 0) return new android.graphics.RectF();
    float l = Float.MAX_VALUE, r = -Float.MAX_VALUE;
    for (int i = 0; i < n; i++) { l = Math.min(l, getLineLeft(i)); r = Math.max(r, getLineRight(i)); }
    return new android.graphics.RectF(Math.min(0, l), 0, Math.max(getWidth(), r), getHeight());
  }
  /** each character's box (left, top, right, bottom) from start to end, written to bounds from boundsStart */
  public void fillCharacterBounds(int start, int end, float[] bounds, int boundsStart) {
    if (start < 0 || end < start || end > getText().length()) throw new IndexOutOfBoundsException("start " + start + " end " + end);
    if (bounds.length - boundsStart < 4 * (end - start)) throw new IndexOutOfBoundsException("bounds too small");
    CharSequence t = getText();
    for (int i = start; i < end; i++) {
      int line = getLineForOffset(i), lineEnd = getLineEnd(line);
      float left = getPrimaryHorizontal(i);
      char c = t.charAt(i);
      float right = c == '\n' ? left : i + 1 < lineEnd ? getPrimaryHorizontal(i + 1) : left + getPaint().measureText(t, i, i + 1);
      int k = boundsStart + 4 * (i - start);
      bounds[k] = Math.min(left, right); bounds[k + 1] = getLineTop(line); bounds[k + 2] = Math.max(left, right); bounds[k + 3] = getLineBottom(line);
    }
  }
  /** the text range of the segments (words, graphemes...) that lie in area by the strategy; null when none does */
  public int[] getRangeForRect(android.graphics.RectF area, SegmentFinder segmentFinder, TextInclusionStrategy inclusionStrategy) {
    int first = getLineForVertical((int) area.top), last = getLineForVertical((int) area.bottom);
    int start = -1, end = -1;
    for (int line = first; line <= last && line < getLineCount(); line++) {
      int lineStart = getLineStart(line), lineEnd = getLineVisibleEnd(line);
      float top = getLineTop(line), bottom = getLineBottom(line);
      for (int s = segmentFinder.nextStartBoundary(lineStart - 1); s != SegmentFinder.DONE && s < lineEnd; s = segmentFinder.nextStartBoundary(s)) {
        int e = segmentFinder.nextEndBoundary(s);
        if (e == SegmentFinder.DONE) break;
        e = Math.min(e, lineEnd);
        float a = getPrimaryHorizontal(s), b = e < getLineEnd(line) ? getPrimaryHorizontal(e) : getLineRight(line);
        android.graphics.RectF seg = new android.graphics.RectF(Math.min(a, b), top, Math.max(a, b), bottom);
        if (inclusionStrategy.isSegmentInside(seg, area)) { if (start < 0) start = s; end = Math.max(end, e); }
      }
    }
    return start < 0 ? null : new int[] {start, end};
  }
  /** the characters letter spacing applies to on the line (trailing white space only when asked) */
  public int getLineLetterSpacingUnitCount(int line, boolean includeTrailingWhitespace) {
    int s = getLineStart(line), e = includeTrailingWhitespace ? getLineEnd(line) : getLineVisibleEnd(line);
    CharSequence t = getText();
    int n = 0;
    for (int i = s; i < e; ) { int cp = Character.codePointAt(t, i); if (cp != '\n') n++; i += Character.charCount(cp); }
    return n;
  }

  /** Builds a Layout (a StaticLayout) of text[start, end) at width (API 35). */
  public static final class Builder {
    private final StaticLayout.Builder mB;
    public Builder(CharSequence text, int start, int end, TextPaint paint, int width) { mB = StaticLayout.Builder.obtain(text, start, end, paint, width); }
    public Builder setAlignment(Alignment alignment) { mB.setAlignment(alignment); return this; }
    public Builder setTextDirectionHeuristic(TextDirectionHeuristic textDirection) { mB.setTextDirection(textDirection); return this; }
    public Builder setLineSpacingAmount(float amount) { mB.mSpacingAdd = amount; return this; }
    public Builder setLineSpacingMultiplier(float multiplier) { mB.mSpacingMult = multiplier; return this; }
    public Builder setFontPaddingIncluded(boolean includeFontPadding) { mB.setIncludePad(includeFontPadding); return this; }
    public Builder setFallbackLineSpacingEnabled(boolean fallbackLineSpacing) { mB.setUseLineSpacingFromFallbacks(fallbackLineSpacing); return this; }
    public Builder setEllipsizedWidth(int ellipsizeWidth) { mB.setEllipsizedWidth(ellipsizeWidth); return this; }
    public Builder setEllipsize(TextUtils.TruncateAt ellipsize) { mB.setEllipsize(ellipsize); return this; }
    public Builder setMaxLines(int maxLines) { mB.setMaxLines(maxLines); return this; }
    public Builder setBreakStrategy(int breakStrategy) { mB.setBreakStrategy(breakStrategy); return this; }
    public Builder setHyphenationFrequency(int hyphenationFrequency) { mB.setHyphenationFrequency(hyphenationFrequency); return this; }
    public Builder setLeftIndents(int[] leftIndents) { mB.mLeftIndents = leftIndents; return this; }
    public Builder setRightIndents(int[] rightIndents) { mB.mRightIndents = rightIndents; return this; }
    public Builder setJustificationMode(int justificationMode) { mB.setJustificationMode(justificationMode); return this; }
    public Builder setLineBreakConfig(android.graphics.text.LineBreakConfig lineBreakConfig) { mB.setLineBreakConfig(lineBreakConfig); return this; }
    public Builder setUseBoundsForWidth(boolean useBoundsForWidth) { mB.setUseBoundsForWidth(useBoundsForWidth); return this; }
    public Builder setShiftDrawingOffsetForStartOverhang(boolean shift) { mB.setShiftDrawingOffsetForStartOverhang(shift); return this; }
    public Builder setMinimumFontMetrics(Paint.FontMetrics minimumFontMetrics) { mB.setMinimumFontMetrics(minimumFontMetrics); return this; }
    public Layout build() { return mB.build(); }
  }
}
