package android.view.inputmethod;

import android.graphics.Matrix;
import android.graphics.RectF;

/** Where the editor's selection, composing text, insertion marker and characters are on screen, for an input method. */
public final class CursorAnchorInfo implements android.os.Parcelable {
  public static final int FLAG_HAS_VISIBLE_REGION = 1, FLAG_HAS_INVISIBLE_REGION = 2, FLAG_IS_RTL = 4;
  private final int mSelectionStart, mSelectionEnd, mComposingTextStart, mInsertionMarkerFlags;
  private final CharSequence mComposingText;
  private final float mInsertionMarkerHorizontal, mInsertionMarkerTop, mInsertionMarkerBaseline, mInsertionMarkerBottom;
  private final android.util.SparseArray<float[]> mCharacterBounds;   // index → {left, top, right, bottom, flags}
  private final java.util.List<RectF> mVisibleLineBounds;
  private final EditorBoundsInfo mEditorBoundsInfo;
  private final TextAppearanceInfo mTextAppearanceInfo;
  private final float[] mMatrixValues;

  private CursorAnchorInfo(Builder b) {
    mSelectionStart = b.mSelectionStart; mSelectionEnd = b.mSelectionEnd; mComposingTextStart = b.mComposingTextStart; mComposingText = b.mComposingText;
    mInsertionMarkerFlags = b.mInsertionMarkerFlags; mInsertionMarkerHorizontal = b.mInsertionMarkerHorizontal; mInsertionMarkerTop = b.mInsertionMarkerTop;
    mInsertionMarkerBaseline = b.mInsertionMarkerBaseline; mInsertionMarkerBottom = b.mInsertionMarkerBottom;
    mCharacterBounds = b.mCharacterBounds.clone(); mVisibleLineBounds = new java.util.ArrayList<>(b.mVisibleLineBounds);
    mEditorBoundsInfo = b.mEditorBoundsInfo; mTextAppearanceInfo = b.mTextAppearanceInfo;
    mMatrixValues = new float[9];
    (b.mMatrix != null ? b.mMatrix : new Matrix()).getValues(mMatrixValues);
  }
  public CursorAnchorInfo(android.os.Parcel source) {
    mSelectionStart = source.readInt(); mSelectionEnd = source.readInt(); mComposingTextStart = source.readInt();
    mComposingText = android.text.TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(source);
    mInsertionMarkerFlags = source.readInt(); mInsertionMarkerHorizontal = source.readFloat(); mInsertionMarkerTop = source.readFloat();
    mInsertionMarkerBaseline = source.readFloat(); mInsertionMarkerBottom = source.readFloat();
    mCharacterBounds = new android.util.SparseArray<>();
    int n = source.readInt();
    for (int i = 0; i < n; i++) { int k = source.readInt(); mCharacterBounds.put(k, source.createFloatArray()); }
    mVisibleLineBounds = new java.util.ArrayList<>();
    float[] v = source.createFloatArray();
    for (int i = 0; v != null && i + 3 < v.length; i += 4) mVisibleLineBounds.add(new RectF(v[i], v[i + 1], v[i + 2], v[i + 3]));
    mEditorBoundsInfo = source.readTypedObject(EditorBoundsInfo.CREATOR);
    mTextAppearanceInfo = source.readTypedObject(TextAppearanceInfo.CREATOR);
    mMatrixValues = source.createFloatArray();
  }
  public void writeToParcel(android.os.Parcel dest, int flags) {
    dest.writeInt(mSelectionStart); dest.writeInt(mSelectionEnd); dest.writeInt(mComposingTextStart);
    android.text.TextUtils.writeToParcel(mComposingText, dest, flags);
    dest.writeInt(mInsertionMarkerFlags); dest.writeFloat(mInsertionMarkerHorizontal); dest.writeFloat(mInsertionMarkerTop);
    dest.writeFloat(mInsertionMarkerBaseline); dest.writeFloat(mInsertionMarkerBottom);
    dest.writeInt(mCharacterBounds.size());
    for (int i = 0; i < mCharacterBounds.size(); i++) { dest.writeInt(mCharacterBounds.keyAt(i)); dest.writeFloatArray(mCharacterBounds.valueAt(i)); }
    float[] v = new float[mVisibleLineBounds.size() * 4];
    for (int i = 0; i < mVisibleLineBounds.size(); i++) { RectF r = mVisibleLineBounds.get(i); v[4 * i] = r.left; v[4 * i + 1] = r.top; v[4 * i + 2] = r.right; v[4 * i + 3] = r.bottom; }
    dest.writeFloatArray(v);
    dest.writeTypedObject(mEditorBoundsInfo, flags); dest.writeTypedObject(mTextAppearanceInfo, flags);
    dest.writeFloatArray(mMatrixValues);
  }
  public int describeContents() { return 0; }
  public static final android.os.Parcelable.Creator<CursorAnchorInfo> CREATOR = new Creator();
  static final class Creator implements android.os.Parcelable.Creator<CursorAnchorInfo> {
    public CursorAnchorInfo createFromParcel(android.os.Parcel in) { return new CursorAnchorInfo(in); }
    public CursorAnchorInfo[] newArray(int size) { return new CursorAnchorInfo[size]; }
  }
  @Override public int hashCode() {
    return java.util.Objects.hash(mSelectionStart, mSelectionEnd, mComposingTextStart, mComposingText == null ? null : mComposingText.toString(), mInsertionMarkerFlags,
        mInsertionMarkerHorizontal, mInsertionMarkerTop, mInsertionMarkerBaseline, mInsertionMarkerBottom, java.util.Arrays.hashCode(mMatrixValues));
  }
  @Override public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof CursorAnchorInfo)) return false;
    CursorAnchorInfo o = (CursorAnchorInfo) obj;
    if (mSelectionStart != o.mSelectionStart || mSelectionEnd != o.mSelectionEnd || mComposingTextStart != o.mComposingTextStart
        || !android.text.TextUtils.equals(mComposingText, o.mComposingText) || mInsertionMarkerFlags != o.mInsertionMarkerFlags
        || !same(mInsertionMarkerHorizontal, o.mInsertionMarkerHorizontal) || !same(mInsertionMarkerTop, o.mInsertionMarkerTop)
        || !same(mInsertionMarkerBaseline, o.mInsertionMarkerBaseline) || !same(mInsertionMarkerBottom, o.mInsertionMarkerBottom)
        || !java.util.Arrays.equals(mMatrixValues, o.mMatrixValues) || !mVisibleLineBounds.equals(o.mVisibleLineBounds)
        || !java.util.Objects.equals(mEditorBoundsInfo, o.mEditorBoundsInfo) || !java.util.Objects.equals(mTextAppearanceInfo, o.mTextAppearanceInfo)
        || mCharacterBounds.size() != o.mCharacterBounds.size()) return false;
    for (int i = 0; i < mCharacterBounds.size(); i++)
      if (mCharacterBounds.keyAt(i) != o.mCharacterBounds.keyAt(i) || !java.util.Arrays.equals(mCharacterBounds.valueAt(i), o.mCharacterBounds.valueAt(i))) return false;
    return true;
  }
  private static boolean same(float a, float b) { return Float.isNaN(a) && Float.isNaN(b) || a == b; }
  @Override public String toString() {
    return "CursorAnchorInfo{mHashCode=" + hashCode() + " mSelection=" + mSelectionStart + "," + mSelectionEnd + " mComposingTextStart=" + mComposingTextStart
        + " mComposingText=" + mComposingText + " mInsertionMarkerFlags=" + mInsertionMarkerFlags + " mInsertionMarkerHorizontal=" + mInsertionMarkerHorizontal
        + " mInsertionMarkerTop=" + mInsertionMarkerTop + " mInsertionMarkerBaseline=" + mInsertionMarkerBaseline + " mInsertionMarkerBottom=" + mInsertionMarkerBottom
        + " mCharacterBoundsCount=" + mCharacterBounds.size() + " mVisibleLineBounds=" + mVisibleLineBounds + " mMatrix=" + java.util.Arrays.toString(mMatrixValues) + "}";
  }
  public int getSelectionStart() { return mSelectionStart; }
  public int getSelectionEnd() { return mSelectionEnd; }
  public int getComposingTextStart() { return mComposingTextStart; }
  public CharSequence getComposingText() { return mComposingText; }
  public int getInsertionMarkerFlags() { return mInsertionMarkerFlags; }
  public float getInsertionMarkerHorizontal() { return mInsertionMarkerHorizontal; }
  public float getInsertionMarkerTop() { return mInsertionMarkerTop; }
  public float getInsertionMarkerBaseline() { return mInsertionMarkerBaseline; }
  public float getInsertionMarkerBottom() { return mInsertionMarkerBottom; }
  public RectF getCharacterBounds(int index) {
    float[] b = mCharacterBounds.get(index);
    return b == null ? null : new RectF(b[0], b[1], b[2], b[3]);
  }
  public int getCharacterBoundsFlags(int index) { float[] b = mCharacterBounds.get(index); return b == null ? 0 : (int) b[4]; }
  public java.util.List<RectF> getVisibleLineBounds() { return new java.util.ArrayList<>(mVisibleLineBounds); }
  public EditorBoundsInfo getEditorBoundsInfo() { return mEditorBoundsInfo; }
  public TextAppearanceInfo getTextAppearanceInfo() { return mTextAppearanceInfo; }
  public Matrix getMatrix() { Matrix m = new Matrix(); m.setValues(mMatrixValues); return m; }

  public static final class Builder {
    int mSelectionStart = -1, mSelectionEnd = -1, mComposingTextStart = -1, mInsertionMarkerFlags;
    CharSequence mComposingText;
    float mInsertionMarkerHorizontal = Float.NaN, mInsertionMarkerTop = Float.NaN, mInsertionMarkerBaseline = Float.NaN, mInsertionMarkerBottom = Float.NaN;
    android.util.SparseArray<float[]> mCharacterBounds = new android.util.SparseArray<>();
    java.util.ArrayList<RectF> mVisibleLineBounds = new java.util.ArrayList<>();
    EditorBoundsInfo mEditorBoundsInfo;
    TextAppearanceInfo mTextAppearanceInfo;
    Matrix mMatrix;
    public Builder() {}
    public Builder setSelectionRange(int newStart, int newEnd) { mSelectionStart = newStart; mSelectionEnd = newEnd; return this; }
    public Builder setComposingText(int composingTextStart, CharSequence composingText) {
      mComposingTextStart = composingTextStart;
      mComposingText = composingText == null ? null : new android.text.SpannedString(composingText);
      return this;
    }
    public Builder setInsertionMarkerLocation(float horizontalPosition, float lineTop, float lineBaseline, float lineBottom, int flags) {
      mInsertionMarkerHorizontal = horizontalPosition; mInsertionMarkerTop = lineTop; mInsertionMarkerBaseline = lineBaseline; mInsertionMarkerBottom = lineBottom;
      mInsertionMarkerFlags = flags; return this;
    }
    public Builder addCharacterBounds(int index, float left, float top, float right, float bottom, int flags) {
      if (index < 0) throw new IllegalArgumentException("index must not be a negative integer.");
      mCharacterBounds.put(index, new float[] {left, top, right, bottom, flags});
      return this;
    }
    public Builder setEditorBoundsInfo(EditorBoundsInfo bounds) { mEditorBoundsInfo = bounds; return this; }
    public Builder setMatrix(Matrix matrix) { mMatrix = matrix == null ? null : new Matrix(matrix); return this; }
    public Builder setTextAppearanceInfo(TextAppearanceInfo textAppearanceInfo) { mTextAppearanceInfo = textAppearanceInfo; return this; }
    public Builder addVisibleLineBounds(float left, float top, float right, float bottom) { mVisibleLineBounds.add(new RectF(left, top, right, bottom)); return this; }
    public Builder clearVisibleLineBounds() { mVisibleLineBounds.clear(); return this; }
    public CursorAnchorInfo build() { return new CursorAnchorInfo(this); }
    public void reset() {
      mSelectionStart = mSelectionEnd = mComposingTextStart = -1; mComposingText = null; mInsertionMarkerFlags = 0;
      mInsertionMarkerHorizontal = mInsertionMarkerTop = mInsertionMarkerBaseline = mInsertionMarkerBottom = Float.NaN;
      mCharacterBounds.clear(); mVisibleLineBounds.clear(); mEditorBoundsInfo = null; mTextAppearanceInfo = null; mMatrix = null;
    }
  }
}
