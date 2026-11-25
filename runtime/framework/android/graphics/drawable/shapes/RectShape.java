package android.graphics.drawable.shapes;

import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.RectF;

public class RectShape extends Shape {
  private RectF mRect = new RectF();
  public RectShape() {}
  @Override public void draw(Canvas canvas, Paint paint) { canvas.drawRect(mRect, paint); }
  @Override public void getOutline(Outline outline) {
    RectF r = rect();
    outline.setRect((int) Math.ceil(r.left), (int) Math.ceil(r.top), (int) Math.floor(r.right), (int) Math.floor(r.bottom));
  }
  @Override protected void onResize(float width, float height) { mRect.set(0, 0, width, height); }
  protected final RectF rect() { return mRect; }
  @Override public RectShape clone() throws CloneNotSupportedException { RectShape s = (RectShape) super.clone(); s.mRect = new RectF(mRect); return s; }
  @Override public boolean equals(Object o) { return super.equals(o) && mRect.equals(((RectShape) o).mRect); }
  @Override public int hashCode() { return 31 * super.hashCode() + mRect.hashCode(); }
}
