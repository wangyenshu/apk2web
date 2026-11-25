package android.graphics.drawable;

import android.graphics.*;

/** the wrapped drawable rotated by level: from fromDegrees (level 0) to toDegrees (10000) around the pivot */
public class RotateDrawable extends DrawableWrapper {
  float from = 0, to = 360, px = 0.5f, py = 0.5f;
  boolean pxRel = true, pyRel = true;
  public RotateDrawable() { super(null); }
  public RotateDrawable(Drawable d) { super(d); }
  @Override public void draw(Canvas c) {
    if (mDrawable == null) return;
    Rect b = getBounds();
    float deg = from + (to - from) * getLevel() / 10000f;
    float x = pxRel ? b.width() * px : px, y = pyRel ? b.height() * py : py;
    int s = c.save();
    c.rotate(deg, b.left + x, b.top + y);
    mDrawable.draw(c);
    c.restoreToCount(s);
  }
  @Override protected boolean onLevelChange(int l) { super.onLevelChange(l); invalidateSelf(); return true; }
  public void setFromDegrees(float fromDegrees) { if (from != fromDegrees) { from = fromDegrees; invalidateSelf(); } }
  public float getFromDegrees() { return from; }
  public void setToDegrees(float toDegrees) { if (to != toDegrees) { to = toDegrees; invalidateSelf(); } }
  public float getToDegrees() { return to; }
  public void setPivotX(float pivotX) { if (px != pivotX) { px = pivotX; invalidateSelf(); } }
  public float getPivotX() { return px; }
  public void setPivotXRelative(boolean relative) { if (pxRel != relative) { pxRel = relative; invalidateSelf(); } }
  public boolean isPivotXRelative() { return pxRel; }
  public void setPivotY(float pivotY) { if (py != pivotY) { py = pivotY; invalidateSelf(); } }
  public float getPivotY() { return py; }
  public void setPivotYRelative(boolean relative) { if (pyRel != relative) { pyRel = relative; invalidateSelf(); } }
  public boolean isPivotYRelative() { return pyRel; }
  @Override Drawable copyWith(Drawable d) { RotateDrawable c = new RotateDrawable(d); c.from = from; c.to = to; c.px = px; c.py = py; c.pxRel = pxRel; c.pyRel = pyRel; return c; }
}
