package android.view.inputmethod;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.text.SegmentFinder;

/** The bounds, flags and bidi levels of the characters [start, end) of an editor, with its grapheme, word and line segments. */
public final class TextBoundsInfo implements android.os.Parcelable {
  public static final int FLAG_CHARACTER_WHITESPACE = 1, FLAG_CHARACTER_LINEFEED = 2, FLAG_CHARACTER_PUNCTUATION = 4, FLAG_LINE_IS_RTL = 8;
  private final int mStart, mEnd;
  private final float[] mMatrixValues, mCharacterBounds;
  private final int[] mFlags, mBidiLevels;
  private final SegmentFinder mGraphemes, mWords, mLines;

  private TextBoundsInfo(int start, int end, float[] matrix, float[] bounds, int[] flags, int[] bidi, SegmentFinder g, SegmentFinder w, SegmentFinder l) {
    mStart = start; mEnd = end; mMatrixValues = matrix; mCharacterBounds = bounds; mFlags = flags; mBidiLevels = bidi; mGraphemes = g; mWords = w; mLines = l;
  }
  public void getMatrix(Matrix matrix) { java.util.Objects.requireNonNull(matrix); matrix.setValues(mMatrixValues); }
  public int getStartIndex() { return mStart; }
  public int getEndIndex() { return mEnd; }
  private int check(int index) {
    if (index < mStart || index >= mEnd) throw new IndexOutOfBoundsException("Index is out of the bounds of [" + mStart + ", " + mEnd + ").");
    return index - mStart;
  }
  public void getCharacterBounds(int index, RectF bounds) {
    int i = 4 * check(index);
    bounds.set(mCharacterBounds[i], mCharacterBounds[i + 1], mCharacterBounds[i + 2], mCharacterBounds[i + 3]);
  }
  public int getCharacterFlags(int index) { return mFlags[check(index)]; }
  public int getCharacterBidiLevel(int index) { return mBidiLevels[check(index)]; }
  public SegmentFinder getWordSegmentFinder() { return mWords; }
  public SegmentFinder getGraphemeSegmentFinder() { return mGraphemes; }
  public SegmentFinder getLineSegmentFinder() { return mLines; }

  private RectF segmentBounds(int s, int e) {
    RectF r = null, c = new RectF();
    for (int i = Math.max(s, mStart); i < Math.min(e, mEnd); i++) {
      getCharacterBounds(i, c);
      if (r == null) r = new RectF(c); else r.union(c);
    }
    return r;
  }
  /** the offset of the grapheme boundary nearest (x, y) on the line that contains y; -1 when no line does */
  public int getOffsetForPosition(float x, float y) {
    for (int ls = mLines.nextStartBoundary(mStart - 1); ls != SegmentFinder.DONE && ls < mEnd; ls = mLines.nextStartBoundary(ls)) {
      int le = mLines.nextEndBoundary(ls);
      if (le == SegmentFinder.DONE) break;
      RectF line = segmentBounds(ls, le);
      if (line == null || y < line.top || y >= line.bottom) continue;
      int last = ls;
      for (int g = mGraphemes.nextStartBoundary(ls - 1); g != SegmentFinder.DONE && g < le; g = mGraphemes.nextStartBoundary(g)) {
        int ge = Math.min(mGraphemes.nextEndBoundary(g), le);
        if (g < mEnd && (mFlags[g - mStart] & FLAG_CHARACTER_LINEFEED) != 0) break;
        RectF b = segmentBounds(g, ge);
        if (b == null) continue;
        boolean rtl = g < mEnd && (mBidiLevels[g - mStart] & 1) != 0;
        if (rtl ? x > b.centerX() : x < b.centerX()) return g;
        last = ge;
      }
      return last;
    }
    return -1;
  }
  public int[] getRangeForRect(RectF area, SegmentFinder segmentFinder, android.text.Layout.TextInclusionStrategy inclusionStrategy) {
    int start = -1, end = -1;
    for (int ls = mLines.nextStartBoundary(mStart - 1); ls != SegmentFinder.DONE && ls < mEnd; ls = mLines.nextStartBoundary(ls)) {
      int le = mLines.nextEndBoundary(ls);
      if (le == SegmentFinder.DONE) break;
      RectF line = segmentBounds(ls, le);
      if (line == null || line.bottom <= area.top || line.top >= area.bottom) continue;
      for (int s = segmentFinder.nextStartBoundary(ls - 1); s != SegmentFinder.DONE && s < le; s = segmentFinder.nextStartBoundary(s)) {
        int e = segmentFinder.nextEndBoundary(s);
        if (e == SegmentFinder.DONE) break;
        RectF b = segmentBounds(s, Math.min(e, le));
        if (b != null && inclusionStrategy.isSegmentInside(b, area)) { if (start < 0) start = s; end = Math.max(end, e); }
      }
    }
    return start < 0 ? null : new int[] {start, end};
  }
  public int describeContents() { return 0; }
  static int[] segments(SegmentFinder f, int start, int end) {
    java.util.ArrayList<Integer> l = new java.util.ArrayList<>();
    for (int s = f.nextStartBoundary(start - 1); s != SegmentFinder.DONE && s < end; s = f.nextStartBoundary(s)) {
      int e = f.nextEndBoundary(s);
      if (e == SegmentFinder.DONE) break;
      l.add(s); l.add(e);
    }
    int[] a = new int[l.size()];
    for (int i = 0; i < a.length; i++) a[i] = l.get(i);
    return a;
  }
  public void writeToParcel(android.os.Parcel dest, int flags) {
    dest.writeInt(mStart); dest.writeInt(mEnd); dest.writeFloatArray(mMatrixValues); dest.writeFloatArray(mCharacterBounds);
    dest.writeIntArray(mFlags); dest.writeIntArray(mBidiLevels);
    dest.writeIntArray(segments(mGraphemes, mStart, mEnd)); dest.writeIntArray(segments(mWords, mStart, mEnd)); dest.writeIntArray(segments(mLines, mStart, mEnd));
  }
  public static final android.os.Parcelable.Creator<TextBoundsInfo> CREATOR = new Creator();
  static final class Creator implements android.os.Parcelable.Creator<TextBoundsInfo> {
    public TextBoundsInfo createFromParcel(android.os.Parcel in) {
      int s = in.readInt(), e = in.readInt();
      float[] m = in.createFloatArray(), b = in.createFloatArray();
      int[] f = in.createIntArray(), bidi = in.createIntArray();
      return new TextBoundsInfo(s, e, m, b, f, bidi, new SegmentFinder.PrescribedSegmentFinder(in.createIntArray()),
          new SegmentFinder.PrescribedSegmentFinder(in.createIntArray()), new SegmentFinder.PrescribedSegmentFinder(in.createIntArray()));
    }
    public TextBoundsInfo[] newArray(int size) { return new TextBoundsInfo[size]; }
  }

  public static final class Builder {
    private int mStart, mEnd;
    private float[] mMatrixValues, mCharacterBounds;
    private int[] mFlags, mBidiLevels;
    private SegmentFinder mGraphemes, mWords, mLines;
    public Builder(int start, int end) { setStartAndEnd(start, end); }
    public Builder clear() {
      mMatrixValues = null; mCharacterBounds = null; mFlags = null; mBidiLevels = null; mGraphemes = mWords = mLines = null; mStart = mEnd = -1;
      return this;
    }
    public Builder setMatrix(Matrix matrix) {
      mMatrixValues = new float[9];
      java.util.Objects.requireNonNull(matrix, "matrix").getValues(mMatrixValues);
      return this;
    }
    public Builder setStartAndEnd(int start, int end) {
      if (start < 0 || end < start) throw new IllegalArgumentException("start must be non-negative and not larger than end");
      mStart = start; mEnd = end; return this;
    }
    public Builder setCharacterBounds(float[] characterBounds) { mCharacterBounds = java.util.Objects.requireNonNull(characterBounds); return this; }
    public Builder setCharacterFlags(int[] characterFlags) { mFlags = java.util.Objects.requireNonNull(characterFlags); return this; }
    public Builder setCharacterBidiLevel(int[] characterBidiLevels) { mBidiLevels = java.util.Objects.requireNonNull(characterBidiLevels); return this; }
    public Builder setGraphemeSegmentFinder(SegmentFinder graphemeSegmentFinder) { mGraphemes = java.util.Objects.requireNonNull(graphemeSegmentFinder); return this; }
    public Builder setWordSegmentFinder(SegmentFinder wordSegmentFinder) { mWords = java.util.Objects.requireNonNull(wordSegmentFinder); return this; }
    public Builder setLineSegmentFinder(SegmentFinder lineSegmentFinder) { mLines = java.util.Objects.requireNonNull(lineSegmentFinder); return this; }
    public TextBoundsInfo build() {
      if (mStart < 0 || mEnd < 0) throw new IllegalStateException("Start and end must be set.");
      if (mMatrixValues == null) throw new IllegalStateException("Matrix must be set.");
      if (mCharacterBounds == null) throw new IllegalStateException("CharacterBounds must be set.");
      if (mFlags == null) throw new IllegalStateException("CharacterFlags must be set.");
      if (mBidiLevels == null) throw new IllegalStateException("CharacterBidiLevel must be set.");
      if (mCharacterBounds.length != 4 * (mEnd - mStart)) throw new IllegalStateException("The length of characterBounds doesn't match the length of the given start and end.");
      if (mFlags.length != mEnd - mStart) throw new IllegalStateException("The length of characterFlags doesn't match the length of the given start and end.");
      if (mBidiLevels.length != mEnd - mStart) throw new IllegalStateException("The length of characterBidiLevels doesn't match the length of the given start and end.");
      if (mGraphemes == null) throw new IllegalStateException("GraphemeSegmentFinder must be set.");
      if (mWords == null) throw new IllegalStateException("WordSegmentFinder must be set.");
      if (mLines == null) throw new IllegalStateException("LineSegmentFinder must be set.");
      return new TextBoundsInfo(mStart, mEnd, mMatrixValues, mCharacterBounds, mFlags, mBidiLevels, mGraphemes, mWords, mLines);
    }
  }
}
