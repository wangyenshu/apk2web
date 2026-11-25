package android.view;

import android.graphics.Point;
import android.os.Parcel;

/** A rounded corner of the display: position, radius and center (the page's display has none). */
public final class RoundedCorner implements android.os.Parcelable {
  public static final int POSITION_TOP_LEFT = 0, POSITION_TOP_RIGHT = 1, POSITION_BOTTOM_RIGHT = 2, POSITION_BOTTOM_LEFT = 3;
  private final int mPosition, mRadius;
  private final Point mCenter;
  public RoundedCorner(int position, int radius, int centerX, int centerY) {
    mPosition = position;
    mRadius = radius > 0 ? radius : 0;
    mCenter = new Point(radius > 0 ? centerX : 0, radius > 0 ? centerY : 0);
  }
  public int getPosition() { return mPosition; }
  public int getRadius() { return mRadius; }
  public Point getCenter() { return new Point(mCenter); }
  @Override public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof RoundedCorner)) return false;
    RoundedCorner r = (RoundedCorner) o;
    return mPosition == r.mPosition && mRadius == r.mRadius && mCenter.equals(r.mCenter);
  }
  @Override public int hashCode() { return (mPosition * 31 + mRadius) * 31 + mCenter.hashCode(); }
  @Override public String toString() {
    String p = mPosition == POSITION_TOP_LEFT ? "TopLeft" : mPosition == POSITION_TOP_RIGHT ? "TopRight" : mPosition == POSITION_BOTTOM_RIGHT ? "BottomRight" : mPosition == POSITION_BOTTOM_LEFT ? "BottomLeft" : "Invalid";
    return "RoundedCorner{position=" + p + ", radius=" + mRadius + ", center=" + mCenter + '}';
  }
  public int describeContents() { return 0; }
  public void writeToParcel(Parcel out, int flags) { out.writeInt(mPosition); out.writeInt(mRadius); out.writeInt(mCenter.x); out.writeInt(mCenter.y); }
  public static final android.os.Parcelable.Creator<RoundedCorner> CREATOR = new android.os.Parcelable.Creator<RoundedCorner>() {
    public RoundedCorner createFromParcel(Parcel in) { return new RoundedCorner(in.readInt(), in.readInt(), in.readInt(), in.readInt()); }
    public RoundedCorner[] newArray(int size) { return new RoundedCorner[size]; }
  };
}
