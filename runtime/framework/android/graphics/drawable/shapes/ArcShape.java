package android.graphics.drawable.shapes;

import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;

public class ArcShape extends RectShape {
  private final float mStartAngle, mSweepAngle;
  public ArcShape(float startAngle, float sweepAngle) { mStartAngle = startAngle; mSweepAngle = sweepAngle; }
  public final float getStartAngle() { return mStartAngle; }
  public final float getSweepAngle() { return mSweepAngle; }
  @Override public void draw(Canvas canvas, Paint paint) { canvas.drawArc(rect(), mStartAngle, mSweepAngle, true, paint); }
  /** an arc's outline is not a convex shape the outline can hold */
  @Override public void getOutline(Outline outline) {}
  @Override public ArcShape clone() throws CloneNotSupportedException { return (ArcShape) super.clone(); }
  @Override public boolean equals(Object o) { return super.equals(o) && ((ArcShape) o).mStartAngle == mStartAngle && ((ArcShape) o).mSweepAngle == mSweepAngle; }
  @Override public int hashCode() { return 31 * (31 * super.hashCode() + Float.floatToIntBits(mStartAngle)) + Float.floatToIntBits(mSweepAngle); }
}
