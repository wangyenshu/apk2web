package android.graphics;
public final class Outline {
  public final Rect mRect = new Rect();
  public float mRadius;
  public float mAlpha = 1;
  public Outline() {}
  public Outline(Outline src) { set(src); }
  public void setEmpty() { mRect.setEmpty(); mRadius = 0; }
  public boolean isEmpty() { return mRect.isEmpty(); }
  public boolean canClip() { return true; }
  public void setAlpha(float alpha) { mAlpha = alpha; }
  public float getAlpha() { return mAlpha; }
  public void set(Outline src) { mRect.set(src.mRect); mRadius = src.mRadius; mAlpha = src.mAlpha; }
  public void setRect(int left, int top, int right, int bottom) { mRect.set(left, top, right, bottom); mRadius = 0; }
  public void setRect(Rect rect) { setRect(rect.left, rect.top, rect.right, rect.bottom); }
  public void setRoundRect(int left, int top, int right, int bottom, float radius) { mRect.set(left, top, right, bottom); mRadius = radius; }
  public void setRoundRect(Rect rect, float radius) { setRoundRect(rect.left, rect.top, rect.right, rect.bottom, radius); }
  public boolean getRect(Rect outRect) { outRect.set(mRect); return true; }
  public float getRadius() { return mRadius; }
  public void setOval(int left, int top, int right, int bottom) { setRoundRect(left, top, right, bottom, Math.min(right - left, bottom - top) / 2f); }
  public void setOval(Rect rect) { setOval(rect.left, rect.top, rect.right, rect.bottom); }
  public void setConvexPath(Path convexPath) {}
  public void setPath(Path path) {}
  public void offset(int dx, int dy) { mRect.offset(dx, dy); }
}
