package android.graphics.text;

/**
 * Breaks a measured paragraph into lines: greedy, at spaces and after hyphens (or anywhere in a word that does not fit
 * alone); tab stops; per-line indents. The break strategy, hyphenation and justification settings are kept; the
 * greedy breaker is used for every strategy and words are not hyphenated.
 */
public class LineBreaker {
  public static final int BREAK_STRATEGY_SIMPLE = 0, BREAK_STRATEGY_HIGH_QUALITY = 1, BREAK_STRATEGY_BALANCED = 2;
  public static final int HYPHENATION_FREQUENCY_NONE = 0, HYPHENATION_FREQUENCY_NORMAL = 1, HYPHENATION_FREQUENCY_FULL = 2;
  public static final int JUSTIFICATION_MODE_NONE = 0, JUSTIFICATION_MODE_INTER_WORD = 1, JUSTIFICATION_MODE_INTER_CHARACTER = 2;
  final int mBreakStrategy, mHyphenationFrequency, mJustificationMode;
  final int[] mIndents;
  final boolean mUseBoundsForWidth;
  LineBreaker(Builder b) {
    mBreakStrategy = b.mBreakStrategy; mHyphenationFrequency = b.mHyphenationFrequency; mJustificationMode = b.mJustificationMode;
    mIndents = b.mIndents; mUseBoundsForWidth = b.mUseBoundsForWidth;
  }

  public Result computeLineBreaks(MeasuredText measuredPara, ParagraphConstraints constraints, int lineNumber) {
    char[] t = measuredPara.mText;
    int n = t.length;
    java.util.ArrayList<float[]> lines = new java.util.ArrayList<>();   // {breakOffset, width, ascent, descent, hasTab}
    int start = 0;
    while (start < n || lines.isEmpty()) {
      int line = lineNumber + lines.size();
      float avail = (lines.size() < constraints.mFirstWidthLineCount ? constraints.mFirstWidth : constraints.mWidth)
          - (mIndents == null || mIndents.length == 0 ? 0 : mIndents[Math.min(line, mIndents.length - 1)]);
      float w = 0, wAtBreak = 0;
      int lastBreak = -1, i = start;
      boolean tab = false;
      for (; i < n; i++) {
        char c = t[i];
        if (c == '\n') { i++; lastBreak = -1; break; }
        float adv = measuredPara.mWidths[i];
        if (c == '\t') { tab = true; adv = constraints.nextTab(w) - w; }
        if (w + adv > avail && !Character.isWhitespace(c) && i > start) {
          if (lastBreak > start) { i = lastBreak; w = wAtBreak; } // the line ends after the last break opportunity
          break;
        }
        w += adv;
        if (c == ' ' || c == '\t' || c == '-' || c == 0x2010 || c == 0x200B || Character.isIdeographic(c)) { lastBreak = i + 1; wAtBreak = w; }
      }
      int end = Math.max(i, Math.min(n, start + 1));
      if (n == 0) end = 0;
      // the width does not include trailing spaces
      float lw = 0;
      int visible = end;
      while (visible > start && (t[visible - 1] == ' ' || t[visible - 1] == '\n')) visible--;
      for (int k = start; k < visible; k++) lw += t[k] == '\t' ? constraints.nextTab(lw) - lw : measuredPara.mWidths[k];
      android.graphics.Paint.FontMetricsInt fm = new android.graphics.Paint.FontMetricsInt();
      measuredPara.getFontMetricsInt(start, end, fm);
      lines.add(new float[] {end, lw, fm.ascent, fm.descent, tab ? 1 : 0});
      if (end >= n) break;
      start = end;
    }
    return new Result(lines);
  }

  public static final class Builder {
    int mBreakStrategy, mHyphenationFrequency, mJustificationMode;
    int[] mIndents;
    boolean mUseBoundsForWidth;
    public Builder() {}
    public Builder setBreakStrategy(int breakStrategy) { mBreakStrategy = breakStrategy; return this; }
    public Builder setHyphenationFrequency(int hyphenationFrequency) { mHyphenationFrequency = hyphenationFrequency; return this; }
    public Builder setJustificationMode(int justificationMode) { mJustificationMode = justificationMode; return this; }
    public Builder setIndents(int[] indents) { mIndents = indents; return this; }
    public Builder setUseBoundsForWidth(boolean useBoundsForWidth) { mUseBoundsForWidth = useBoundsForWidth; return this; }
    public LineBreaker build() { return new LineBreaker(this); }
  }

  public static class ParagraphConstraints {
    float mWidth, mFirstWidth, mDefaultTabStop;
    int mFirstWidthLineCount;
    float[] mTabStops;
    public ParagraphConstraints() {}
    public void setWidth(float width) { mWidth = width; }
    public void setIndent(float firstWidth, int firstWidthLineCount) { mFirstWidth = firstWidth; mFirstWidthLineCount = firstWidthLineCount; }
    public void setTabStops(float[] tabStops, float defaultTabStop) { mTabStops = tabStops; mDefaultTabStop = defaultTabStop; }
    public float getWidth() { return mWidth; }
    public float getFirstWidth() { return mFirstWidth; }
    public int getFirstWidthLineCount() { return mFirstWidthLineCount; }
    public float[] getTabStops() { return mTabStops; }
    public float getDefaultTabStop() { return mDefaultTabStop; }
    float nextTab(float x) {
      if (mTabStops != null) for (float s : mTabStops) if (s > x) return s;
      float d = mDefaultTabStop > 0 ? mDefaultTabStop : 20 * a2w.Values.METRICS.density;
      return (float) (Math.floor(x / d) + 1) * d;
    }
  }

  public static class Result {
    private final java.util.List<float[]> mLines;
    Result(java.util.List<float[]> lines) { mLines = lines; }
    public int getLineCount() { return mLines.size(); }
    public int getLineBreakOffset(int lineIndex) { return (int) mLines.get(lineIndex)[0]; }
    public float getLineWidth(int lineIndex) { return mLines.get(lineIndex)[1]; }
    public float getLineAscent(int lineIndex) { return mLines.get(lineIndex)[2]; }
    public float getLineDescent(int lineIndex) { return mLines.get(lineIndex)[3]; }
    public boolean hasLineTab(int lineIndex) { return mLines.get(lineIndex)[4] != 0; }
    public int getStartLineHyphenEdit(int lineIndex) { return 0; }
    public int getEndLineHyphenEdit(int lineIndex) { return 0; }
  }
}
