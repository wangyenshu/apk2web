package android.view;

import android.graphics.Path;
import android.os.Parcel;

/** The display's shape: a rectangle of the screen's size. */
public final class DisplayShape implements android.os.Parcelable {
  private final int mWidth, mHeight;
  DisplayShape(int width, int height) { mWidth = width; mHeight = height; }
  public Path getPath() { Path p = new Path(); p.addRect(0, 0, mWidth, mHeight, Path.Direction.CW); return p; }
  @Override public boolean equals(Object o) { return o instanceof DisplayShape && ((DisplayShape) o).mWidth == mWidth && ((DisplayShape) o).mHeight == mHeight; }
  @Override public int hashCode() { return mWidth * 31 + mHeight; }
  @Override public String toString() { return "DisplayShape{ spec=rect " + mWidth + "x" + mHeight + "}"; }
  public int describeContents() { return 0; }
  public void writeToParcel(Parcel out, int flags) { out.writeInt(mWidth); out.writeInt(mHeight); }
  public static final android.os.Parcelable.Creator<DisplayShape> CREATOR = new android.os.Parcelable.Creator<DisplayShape>() {
    public DisplayShape createFromParcel(Parcel in) { return new DisplayShape(in.readInt(), in.readInt()); }
    public DisplayShape[] newArray(int size) { return new DisplayShape[size]; }
  };
}
