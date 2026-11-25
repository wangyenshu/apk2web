package android.graphics.drawable.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

/** a path drawn in a standard width x height box, scaled to the shape's size */
public class PathShape extends Shape {
  private final float mStdWidth, mStdHeight;
  private Path mPath;
  private float mScaleX = 1, mScaleY = 1;
  public PathShape(Path path, float stdWidth, float stdHeight) { mPath = path; mStdWidth = stdWidth; mStdHeight = stdHeight; }
  @Override public void draw(Canvas canvas, Paint paint) {
    canvas.save();
    canvas.scale(mScaleX, mScaleY);
    canvas.drawPath(mPath, paint);
    canvas.restore();
  }
  @Override protected void onResize(float width, float height) { mScaleX = width / mStdWidth; mScaleY = height / mStdHeight; }
  @Override public PathShape clone() throws CloneNotSupportedException { PathShape s = (PathShape) super.clone(); s.mPath = new Path(mPath); return s; }
  @Override public boolean equals(Object o) {
    if (!super.equals(o)) return false;
    PathShape x = (PathShape) o;
    return x.mStdWidth == mStdWidth && x.mStdHeight == mStdHeight && x.mPath == mPath;
  }
  @Override public int hashCode() { return 31 * (31 * super.hashCode() + Float.floatToIntBits(mStdWidth)) + Float.floatToIntBits(mStdHeight); }
}
