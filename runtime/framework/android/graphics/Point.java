package android.graphics;
public class Point implements android.os.Parcelable {
  public int x, y;
  public Point() {}
  public Point(int x, int y) { this.x = x; this.y = y; }
  public Point(Point p) { x = p.x; y = p.y; }
  public void set(int x, int y) { this.x = x; this.y = y; }
  public final void offset(int dx, int dy) { x += dx; y += dy; }
  public final void negate() { x = -x; y = -y; }
  public final boolean equals(int x, int y) { return this.x == x && this.y == y; }
  public boolean equals(Object o) { return o instanceof Point && ((Point) o).x == x && ((Point) o).y == y; }
  public int hashCode() { return x * 31 + y; }
  public String toString() { return "Point(" + x + ", " + y + ")"; }
  public int describeContents() { return 0; }
  public void writeToParcel(android.os.Parcel out, int flags) { out.writeInt(x); out.writeInt(y); }
  public void readFromParcel(android.os.Parcel in) { x = in.readInt(); y = in.readInt(); }
  public static final android.os.Parcelable.Creator<Point> CREATOR = new android.os.Parcelable.Creator<Point>() {
    public Point createFromParcel(android.os.Parcel in) { Point p = new Point(); p.readFromParcel(in); return p; }
    public Point[] newArray(int size) { return new Point[size]; }
  };
}
