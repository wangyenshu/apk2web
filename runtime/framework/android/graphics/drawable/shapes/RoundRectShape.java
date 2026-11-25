package android.graphics.drawable.shapes;

import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

/** a rectangle with rounded corners (8 radii: x, y per corner from top-left), optionally with an inset inner hole */
public class RoundRectShape extends RectShape {
  private float[] mOuterRadii, mInnerRadii;
  private RectF mInset, mInnerRect;
  private Path mPath;
  public RoundRectShape(float[] outerRadii, RectF inset, float[] innerRadii) {
    if (outerRadii != null && outerRadii.length < 8) throw new ArrayIndexOutOfBoundsException("outer radii must have >= 8 values");
    if (innerRadii != null && innerRadii.length < 8) throw new ArrayIndexOutOfBoundsException("inner radii must have >= 8 values");
    mOuterRadii = outerRadii;
    mInset = inset;
    mInnerRadii = innerRadii;
    if (inset != null) mInnerRect = new RectF();
    mPath = new Path();
  }
  @Override public void draw(Canvas canvas, Paint paint) { canvas.drawPath(mPath, paint); }
  @Override public void getOutline(Outline outline) {
    if (mInnerRect != null) return;   // not convex
    float radius = 0;
    if (mOuterRadii != null) {
      radius = mOuterRadii[0];
      for (int i = 1; i < 8; i++) if (mOuterRadii[i] != radius) return;   // per-corner radii: no simple outline
    }
    RectF r = rect();
    outline.setRoundRect((int) Math.ceil(r.left), (int) Math.ceil(r.top), (int) Math.floor(r.right), (int) Math.floor(r.bottom), radius);
  }
  @Override protected void onResize(float w, float h) {
    super.onResize(w, h);
    RectF r = rect();
    mPath.reset();
    if (mOuterRadii != null) mPath.addRoundRect(r, mOuterRadii, Path.Direction.CW);
    else mPath.addRect(r, Path.Direction.CW);
    if (mInnerRect != null) {
      mInnerRect.set(r.left + mInset.left, r.top + mInset.top, r.right - mInset.right, r.bottom - mInset.bottom);
      if (mInnerRect.width() < w && mInnerRect.height() < h) {
        if (mInnerRadii != null) mPath.addRoundRect(mInnerRect, mInnerRadii, Path.Direction.CCW);
        else mPath.addRect(mInnerRect, Path.Direction.CCW);
      }
    }
  }
  @Override public RoundRectShape clone() throws CloneNotSupportedException {
    RoundRectShape s = (RoundRectShape) super.clone();
    s.mOuterRadii = mOuterRadii == null ? null : mOuterRadii.clone();
    s.mInnerRadii = mInnerRadii == null ? null : mInnerRadii.clone();
    s.mInset = mInset == null ? null : new RectF(mInset);
    s.mInnerRect = mInnerRect == null ? null : new RectF(mInnerRect);
    s.mPath = new Path(mPath);
    return s;
  }
  @Override public boolean equals(Object o) {
    if (!super.equals(o)) return false;
    RoundRectShape x = (RoundRectShape) o;
    return java.util.Arrays.equals(mOuterRadii, x.mOuterRadii) && java.util.Arrays.equals(mInnerRadii, x.mInnerRadii) && java.util.Objects.equals(mInset, x.mInset);
  }
  @Override public int hashCode() { return 31 * (31 * super.hashCode() + java.util.Arrays.hashCode(mOuterRadii)) + java.util.Arrays.hashCode(mInnerRadii); }
}
