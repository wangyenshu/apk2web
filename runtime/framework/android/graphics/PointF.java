package android.graphics;
public class PointF implements android.os.Parcelable {
  public float x, y;
  public PointF() {}
  public PointF(float x, float y) { this.x = x; this.y = y; }
  public PointF(Point p) { x = p.x; y = p.y; }
  public PointF(PointF p) { x = p.x; y = p.y; }
  public final void set(float x, float y) { this.x = x; this.y = y; }
  public final void set(PointF p) { x = p.x; y = p.y; }
  public final void offset(float dx, float dy) { x += dx; y += dy; }
  public final void negate() { x = -x; y = -y; }
  public final boolean equals(float x, float y) { return this.x == x && this.y == y; }
  public final float length() { return (float) Math.hypot(x, y); }
  public static float length(float x, float y) { return (float) Math.hypot(x, y); }
  public int describeContents() { return 0; }
  public void writeToParcel(android.os.Parcel out, int flags) { out.writeFloat(x); out.writeFloat(y); }
  public void readFromParcel(android.os.Parcel in) { x = in.readFloat(); y = in.readFloat(); }
  public static final android.os.Parcelable.Creator<PointF> CREATOR = new android.os.Parcelable.Creator<PointF>() {
    public PointF createFromParcel(android.os.Parcel in) { PointF p = new PointF(); p.readFromParcel(in); return p; }
    public PointF[] newArray(int size) { return new PointF[size]; }
  };
}
