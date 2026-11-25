package android.graphics;

public class RectF implements android.os.Parcelable {
  public float left, top, right, bottom;
  public RectF() {}
  public RectF(float l, float t, float r, float b) { left = l; top = t; right = r; bottom = b; }
  public RectF(RectF r) { set(r); }
  public RectF(Rect r) { set(r); }
  public String toString() { return "RectF(" + left + ", " + top + ", " + right + ", " + bottom + ")"; }
  public final boolean isEmpty() { return left >= right || top >= bottom; }
  public final float width() { return right - left; }
  public final float height() { return bottom - top; }
  public final float centerX() { return (left + right) * 0.5f; }
  public final float centerY() { return (top + bottom) * 0.5f; }
  public void setEmpty() { left = top = right = bottom = 0; }
  public void set(float l, float t, float r, float b) { left = l; top = t; right = r; bottom = b; }
  public void set(RectF s) { left = s.left; top = s.top; right = s.right; bottom = s.bottom; }
  public void set(Rect s) { left = s.left; top = s.top; right = s.right; bottom = s.bottom; }
  public void offset(float dx, float dy) { left += dx; top += dy; right += dx; bottom += dy; }
  public void offsetTo(float x, float y) { right += x - left; bottom += y - top; left = x; top = y; }
  public void inset(float dx, float dy) { left += dx; top += dy; right -= dx; bottom -= dy; }
  public boolean contains(float x, float y) { return left < right && top < bottom && x >= left && x < right && y >= top && y < bottom; }
  public boolean intersect(float l, float t, float r, float b) {
    if (left < r && l < right && top < b && t < bottom) { if (left < l) left = l; if (top < t) top = t; if (right > r) right = r; if (bottom > b) bottom = b; return true; }
    return false;
  }
  public boolean intersect(RectF r) { return intersect(r.left, r.top, r.right, r.bottom); }
  public boolean intersects(float l, float t, float r, float b) { return left < r && l < right && top < b && t < bottom; }
  public void union(float l, float t, float r, float b) {
    if (l < r && t < b) {
      if (left < right && top < bottom) { if (left > l) left = l; if (top > t) top = t; if (right < r) right = r; if (bottom < b) bottom = b; }
      else { left = l; top = t; right = r; bottom = b; }
    }
  }
  public void union(RectF r) { union(r.left, r.top, r.right, r.bottom); }
  /** grows the rectangle to include the point (x, y) */
  public void union(float x, float y) {
    if (x < left) left = x; else if (x > right) right = x;
    if (y < top) top = y; else if (y > bottom) bottom = y;
  }
  public void round(Rect d) { d.set(Math.round(left), Math.round(top), Math.round(right), Math.round(bottom)); }
  public void roundOut(Rect d) { d.set((int) Math.floor(left), (int) Math.floor(top), (int) Math.ceil(right), (int) Math.ceil(bottom)); }
  public void sort() { if (left > right) { float t = left; left = right; right = t; } if (top > bottom) { float t = top; top = bottom; bottom = t; } }
  public boolean contains(float l, float t, float r, float b) { return left < right && top < bottom && left <= l && top <= t && right >= r && bottom >= b; }
  public boolean contains(RectF r) { return contains(r.left, r.top, r.right, r.bottom); }
  public static boolean intersects(RectF a, RectF b) { return a.left < b.right && b.left < a.right && a.top < b.bottom && b.top < a.bottom; }
  public boolean setIntersect(RectF a, RectF b) {
    if (!intersects(a, b)) return false;
    left = Math.max(a.left, b.left); top = Math.max(a.top, b.top); right = Math.min(a.right, b.right); bottom = Math.min(a.bottom, b.bottom);
    return true;
  }
  public String toShortString() { return "[" + left + "," + top + "][" + right + "," + bottom + "]"; }
  public int describeContents() { return 0; }
  public void writeToParcel(android.os.Parcel out, int flags) { out.writeFloat(left); out.writeFloat(top); out.writeFloat(right); out.writeFloat(bottom); }
  public void readFromParcel(android.os.Parcel in) { left = in.readFloat(); top = in.readFloat(); right = in.readFloat(); bottom = in.readFloat(); }
  public static final android.os.Parcelable.Creator<RectF> CREATOR = new android.os.Parcelable.Creator<RectF>() {
    public RectF createFromParcel(android.os.Parcel in) { RectF r = new RectF(); r.readFromParcel(in); return r; }
    public RectF[] newArray(int size) { return new RectF[size]; }
  };
  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RectF r = (RectF) o;
    return left == r.left && top == r.top && right == r.right && bottom == r.bottom;
  }
  @Override public int hashCode() {
    int result = left != +0.0f ? Float.floatToIntBits(left) : 0;
    result = 31 * result + (top != +0.0f ? Float.floatToIntBits(top) : 0);
    result = 31 * result + (right != +0.0f ? Float.floatToIntBits(right) : 0);
    return 31 * result + (bottom != +0.0f ? Float.floatToIntBits(bottom) : 0);
  }
}
