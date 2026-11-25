package android.util;

public final class SizeF implements android.os.Parcelable {
  private final float mWidth, mHeight;
  public SizeF(float width, float height) { mWidth = width; mHeight = height; }
  public float getWidth() { return mWidth; }
  public float getHeight() { return mHeight; }
  @Override public boolean equals(Object o) { return o instanceof SizeF && ((SizeF) o).mWidth == mWidth && ((SizeF) o).mHeight == mHeight; }
  @Override public int hashCode() { return Float.floatToIntBits(mWidth) ^ Float.floatToIntBits(mHeight); }
  @Override public String toString() { return mWidth + "x" + mHeight; }
  public int describeContents() { return 0; }
  public void writeToParcel(android.os.Parcel out, int flags) { out.writeFloat(mWidth); out.writeFloat(mHeight); }
  public static final android.os.Parcelable.Creator<SizeF> CREATOR = new android.os.Parcelable.Creator<SizeF>() {
    public SizeF createFromParcel(android.os.Parcel in) { float w = in.readFloat(); return new SizeF(w, in.readFloat()); }
    public SizeF[] newArray(int size) { return new SizeF[size]; }
  };
  public static SizeF parseSizeF(String s) {
    int sep = s.indexOf('*');
    if (sep < 0) sep = s.indexOf('x');
    if (sep < 0) throw new NumberFormatException("Invalid SizeF: \"" + s + "\"");
    return new SizeF(Float.parseFloat(s.substring(0, sep)), Float.parseFloat(s.substring(sep + 1)));
  }
}
