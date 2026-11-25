package android.graphics.drawable.shapes;

import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.RectF;

public class OvalShape extends RectShape {
  public OvalShape() {}
  @Override public void draw(Canvas canvas, Paint paint) { canvas.drawOval(rect(), paint); }
  @Override public void getOutline(Outline outline) {
    RectF r = rect();
    outline.setOval((int) Math.ceil(r.left), (int) Math.ceil(r.top), (int) Math.floor(r.right), (int) Math.floor(r.bottom));
  }
  @Override public OvalShape clone() throws CloneNotSupportedException { return (OvalShape) super.clone(); }
}
