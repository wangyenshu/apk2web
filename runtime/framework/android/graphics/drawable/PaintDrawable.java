package android.graphics.drawable;

import android.graphics.drawable.shapes.RoundRectShape;

/** a ShapeDrawable of the paint's color, a round rectangle once corner radii are set */
public class PaintDrawable extends ShapeDrawable {
  public PaintDrawable() {}
  public PaintDrawable(int color) { getPaint().setColor(color); }
  public void setCornerRadius(float radius) {
    float[] radii = null;
    if (radius > 0) { radii = new float[8]; java.util.Arrays.fill(radii, radius); }
    setCornerRadii(radii);
  }
  public void setCornerRadii(float[] radii) {
    if (radii == null) { if (getShape() != null) setShape(null); }
    else setShape(new RoundRectShape(radii, null, null));
    invalidateSelf();
  }
  private static int or(int v, int def) { return v != 0 ? v : def; }
  @Override protected boolean inflateTag(String name, android.content.res.Resources r, org.xmlpull.v1.XmlPullParser parser, android.util.AttributeSet attrs) {
    if ("corners".equals(name)) {
      int radius = dim(attrs, "radius");
      float[] radii = new float[8];
      int[] corner = {or(dim(attrs, "topLeftRadius"), radius), or(dim(attrs, "topRightRadius"), radius), or(dim(attrs, "bottomRightRadius"), radius), or(dim(attrs, "bottomLeftRadius"), radius)};
      for (int i = 0; i < 4; i++) radii[2 * i] = radii[2 * i + 1] = corner[i];
      setCornerRadii(radii);
      return true;
    }
    return super.inflateTag(name, r, parser, attrs);
  }
}
