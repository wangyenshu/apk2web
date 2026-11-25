package android.graphics.text;

import android.graphics.Paint;
import android.graphics.Rect;

/** A paragraph's characters measured by style run: each character's advance (from the run's paint), and the runs' metrics. */
public class MeasuredText {
  final char[] mText;
  final float[] mWidths;
  final int[] mRunEnds;
  final Paint[] mRunPaints;
  final boolean[] mRunReplacement;
  MeasuredText(char[] text, float[] widths, java.util.List<int[]> runs, java.util.List<Paint> paints) {
    mText = text; mWidths = widths;
    mRunEnds = new int[runs.size()]; mRunPaints = new Paint[runs.size()]; mRunReplacement = new boolean[runs.size()];
    for (int i = 0; i < mRunEnds.length; i++) { mRunEnds[i] = runs.get(i)[0]; mRunReplacement[i] = runs.get(i)[1] != 0; mRunPaints[i] = paints.get(i); }
  }
  void check(int start, int end) {
    if (start < 0 || end > mText.length || start > end) throw new IllegalArgumentException("start(" + start + ") end(" + end + ") length(" + mText.length + ")");
  }
  public float getWidth(int start, int end) {
    check(start, end);
    float w = 0;
    for (int i = start; i < end; i++) w += mWidths[i];
    return w;
  }
  public float getCharWidthAt(int offset) {
    if (offset < 0 || offset >= mText.length) throw new IllegalArgumentException("offset(" + offset + ") is out of range");
    return mWidths[offset];
  }
  /** the union of the glyph boxes of [start, end) */
  public void getBounds(int start, int end, Rect rect) {
    check(start, end);
    rect.setEmpty();
    int runStart = 0;
    for (int r = 0; r < mRunEnds.length; r++) {
      int rs = Math.max(start, runStart), re = Math.min(end, mRunEnds[r]);
      if (rs < re && !mRunReplacement[r]) {
        Rect b = new Rect();
        mRunPaints[r].getTextBounds(mText, rs, re - rs, b);
        b.offset((int) getWidth(start, rs), 0);
        if (rect.isEmpty()) rect.set(b); else rect.union(b);
      }
      runStart = mRunEnds[r];
    }
  }
  /** the extremes of the metrics of the runs [start, end) covers */
  public void getFontMetricsInt(int start, int end, Paint.FontMetricsInt outMetrics) {
    check(start, end);
    boolean first = true;
    int runStart = 0;
    for (int r = 0; r < mRunEnds.length; r++) {
      if (Math.max(start, runStart) < Math.min(end, mRunEnds[r]) || (start == end && start >= runStart && start <= mRunEnds[r])) {
        Paint.FontMetricsInt m = mRunPaints[r].getFontMetricsInt();
        if (first) { outMetrics.top = m.top; outMetrics.ascent = m.ascent; outMetrics.descent = m.descent; outMetrics.bottom = m.bottom; outMetrics.leading = m.leading; first = false; }
        else {
          outMetrics.top = Math.min(outMetrics.top, m.top); outMetrics.ascent = Math.min(outMetrics.ascent, m.ascent);
          outMetrics.descent = Math.max(outMetrics.descent, m.descent); outMetrics.bottom = Math.max(outMetrics.bottom, m.bottom);
          outMetrics.leading = Math.max(outMetrics.leading, m.leading);
        }
        if (start == end) break;
      }
      runStart = mRunEnds[r];
    }
  }

  public static final class Builder {
    public static final int HYPHENATION_MODE_NONE = 0, HYPHENATION_MODE_NORMAL = 1, HYPHENATION_MODE_FAST = 2;
    private final char[] mText;
    private final float[] mWidths;
    private final java.util.ArrayList<int[]> mRuns = new java.util.ArrayList<>();
    private final java.util.ArrayList<Paint> mPaints = new java.util.ArrayList<>();
    private int mCurrentOffset, mHyphenationMode;
    private boolean mComputeLayout = true, mBuilt;
    public Builder(char[] text) {
      mText = java.util.Objects.requireNonNull(text, "text");
      mWidths = new float[text.length];
    }
    /** re-measures the text of an existing measurement (new runs follow) */
    public Builder(MeasuredText text) {
      this(text.mText.clone());
    }
    public Builder appendStyleRun(Paint paint, int length, boolean isRtl) { return appendStyleRun(paint, null, length, isRtl); }
    public Builder appendStyleRun(Paint paint, LineBreakConfig lineBreakConfig, int length, boolean isRtl) {
      checkRun(paint, length);
      float[] w = new float[length];
      paint.getTextWidths(mText, mCurrentOffset, length, w);
      System.arraycopy(w, 0, mWidths, mCurrentOffset, length);
      return addRun(paint, length, false);
    }
    public Builder appendReplacementRun(Paint paint, int length, float width) {
      checkRun(paint, length);
      if (width < 0) throw new IllegalArgumentException("width can not be negative");
      if (length > 0) mWidths[mCurrentOffset] = width;
      return addRun(paint, length, true);
    }
    private void checkRun(Paint paint, int length) {
      java.util.Objects.requireNonNull(paint, "paint");
      if (length <= 0) throw new IllegalArgumentException("length can not be negative");
      if (mCurrentOffset + length > mText.length) throw new IllegalArgumentException("Style exceeds the text length");
    }
    private Builder addRun(Paint paint, int length, boolean replacement) {
      mCurrentOffset += length;
      mRuns.add(new int[] {mCurrentOffset, replacement ? 1 : 0});
      mPaints.add(new Paint(paint));
      return this;
    }
    public Builder setComputeHyphenation(boolean computeHyphenation) { mHyphenationMode = computeHyphenation ? HYPHENATION_MODE_NORMAL : HYPHENATION_MODE_NONE; return this; }
    public Builder setComputeHyphenation(int mode) {
      if (mode < HYPHENATION_MODE_NONE || mode > HYPHENATION_MODE_FAST) throw new IllegalArgumentException("Unknown hyphenation mode: " + mode);
      mHyphenationMode = mode; return this;
    }
    public Builder setComputeLayout(boolean computeLayout) { mComputeLayout = computeLayout; return this; }
    public MeasuredText build() {
      if (mBuilt) throw new IllegalStateException("Builder can not be reused.");
      if (mCurrentOffset != mText.length) throw new IllegalStateException("Style info has not been provided for all text.");
      mBuilt = true;
      return new MeasuredText(mText, mWidths, mRuns, mPaints);
    }
  }
}
