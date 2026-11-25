package android.graphics;

public final class Rect implements android.os.Parcelable {
  public int left, top, right, bottom;
  public Rect() {}
  public Rect(int l, int t, int r, int b) { left = l; top = t; right = r; bottom = b; }
  public Rect(Rect r) { if (r != null) set(r); }
  public boolean equals(Object o) { if (!(o instanceof Rect)) return false; Rect r = (Rect) o; return left == r.left && top == r.top && right == r.right && bottom == r.bottom; }
  public int hashCode() { return ((left * 31 + top) * 31 + right) * 31 + bottom; }
  public String toString() { return "Rect(" + left + ", " + top + " - " + right + ", " + bottom + ")"; }
  public String toShortString() { return "[" + left + "," + top + "][" + right + "," + bottom + "]"; }
  public final boolean isEmpty() { return left >= right || top >= bottom; }
  public final int width() { return right - left; }
  public final int height() { return bottom - top; }
  public final int centerX() { return (left + right) >> 1; }
  public final int centerY() { return (top + bottom) >> 1; }
  public final float exactCenterX() { return (left + right) * 0.5f; }
  public final float exactCenterY() { return (top + bottom) * 0.5f; }
  public void setEmpty() { left = top = right = bottom = 0; }
  public void set(int l, int t, int r, int b) { left = l; top = t; right = r; bottom = b; }
  public void set(Rect s) { left = s.left; top = s.top; right = s.right; bottom = s.bottom; }
  public void offset(int dx, int dy) { left += dx; top += dy; right += dx; bottom += dy; }
  public void offsetTo(int x, int y) { right += x - left; bottom += y - top; left = x; top = y; }
  public void inset(int dx, int dy) { left += dx; top += dy; right -= dx; bottom -= dy; }
  public boolean contains(int x, int y) { return left < right && top < bottom && x >= left && x < right && y >= top && y < bottom; }
  public boolean contains(Rect r) { return left < right && top < bottom && left <= r.left && top <= r.top && right >= r.right && bottom >= r.bottom; }
  public boolean intersect(int l, int t, int r, int b) {
    if (left < r && l < right && top < b && t < bottom) { if (left < l) left = l; if (top < t) top = t; if (right > r) right = r; if (bottom > b) bottom = b; return true; }
    return false;
  }
  public boolean intersect(Rect r) { return intersect(r.left, r.top, r.right, r.bottom); }
  public boolean intersects(int l, int t, int r, int b) { return left < r && l < right && top < b && t < bottom; }
  public static boolean intersects(Rect a, Rect b) { return a.left < b.right && b.left < a.right && a.top < b.bottom && b.top < a.bottom; }
  public boolean setIntersect(Rect a, Rect b) { if (intersects(a, b)) { left = Math.max(a.left, b.left); top = Math.max(a.top, b.top); right = Math.min(a.right, b.right); bottom = Math.min(a.bottom, b.bottom); return true; } return false; }
  public void union(int l, int t, int r, int b) {
    if (l < r && t < b) {
      if (left < right && top < bottom) { if (left > l) left = l; if (top > t) top = t; if (right < r) right = r; if (bottom < b) bottom = b; }
      else { left = l; top = t; right = r; bottom = b; }
    }
  }
  public void union(Rect r) { union(r.left, r.top, r.right, r.bottom); }
  public void union(int x, int y) { if (x < left) left = x; else if (x > right) right = x; if (y < top) top = y; else if (y > bottom) bottom = y; }
  public void sort() { if (left > right) { int t = left; left = right; right = t; } if (top > bottom) { int t = top; top = bottom; bottom = t; } }
  /** "left top right bottom", for unflattenFromString */
  public String flattenToString() { return left + " " + top + " " + right + " " + bottom; }
  public static Rect unflattenFromString(String str) {
    if (str == null || str.isEmpty()) return null;
    String[] p = str.trim().split(" ");
    if (p.length != 4) return null;
    try { return new Rect(Integer.parseInt(p[0]), Integer.parseInt(p[1]), Integer.parseInt(p[2]), Integer.parseInt(p[3])); }
    catch (NumberFormatException e) { return null; }
  }
  public void readFromParcel(android.os.Parcel in) { left = in.readInt(); top = in.readInt(); right = in.readInt(); bottom = in.readInt(); }
  public static final android.os.Parcelable.Creator<Rect> CREATOR = new android.os.Parcelable.Creator<Rect>() {
    public Rect createFromParcel(android.os.Parcel in) { Rect r = new Rect(); r.readFromParcel(in); return r; }
    public Rect[] newArray(int size) { return new Rect[size]; }
  };
  public int describeContents() { return 0; }
  public void writeToParcel(android.os.Parcel p, int f) { p.writeInt(left); p.writeInt(top); p.writeInt(right); p.writeInt(bottom); }
  public boolean contains(int l, int t, int r, int b) { return left < right && top < bottom && left <= l && top <= t && right >= r && bottom >= b; }
  public void inset(Insets insets) { left += insets.left; top += insets.top; right -= insets.right; bottom -= insets.bottom; }
  public void inset(int l, int t, int r, int b) { left += l; top += t; right -= r; bottom -= b; }
}
