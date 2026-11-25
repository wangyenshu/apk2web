package android.graphics.drawable;

import android.graphics.*;
import android.graphics.drawable.shapes.Shape;

/** android.graphics.drawable.ShapeDrawable: a Shape (or the bounds' rectangle) drawn with its paint at the bounds. */
public class ShapeDrawable extends Drawable {
  public static abstract class ShaderFactory {
    public ShaderFactory() {}
    public abstract Shader resize(int width, int height);
  }
  final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
  private Shape mShape;
  private ShaderFactory mShaderFactory;
  private Rect mPadding;
  private int mAlpha = 255;
  int iw = -1, ih = -1;

  public ShapeDrawable() {}
  public ShapeDrawable(Shape s) { mShape = s; }
  public Shape getShape() { return mShape; }
  public void setShape(Shape shape) { mShape = shape; updateShape(); }
  public void setShaderFactory(ShaderFactory fact) { mShaderFactory = fact; }
  public ShaderFactory getShaderFactory() { return mShaderFactory; }
  public Paint getPaint() { return paint; }
  public void setPadding(int left, int top, int right, int bottom) {
    if ((left | top | right | bottom) == 0) mPadding = null;
    else { if (mPadding == null) mPadding = new Rect(); mPadding.set(left, top, right, bottom); }
    invalidateSelf();
  }
  public void setPadding(Rect padding) {
    if (padding == null) mPadding = null;
    else { if (mPadding == null) mPadding = new Rect(); mPadding.set(padding); }
    invalidateSelf();
  }
  @Override public boolean getPadding(Rect padding) {
    if (mPadding != null) { padding.set(mPadding); return true; }
    return super.getPadding(padding);
  }
  public void setIntrinsicWidth(int width) { iw = width; invalidateSelf(); }
  public void setIntrinsicHeight(int height) { ih = height; invalidateSelf(); }
  @Override public int getIntrinsicWidth() { return iw; }
  @Override public int getIntrinsicHeight() { return ih; }
  protected void onDraw(Shape shape, Canvas canvas, Paint paint) { shape.draw(canvas, paint); }
  private static int modulateAlpha(int paintAlpha, int alpha) { int scale = alpha + (alpha >>> 7); return paintAlpha * scale >>> 8; }
  @Override public void draw(Canvas canvas) {
    Rect r = getBounds();
    int prev = paint.getAlpha();
    paint.setAlpha(modulateAlpha(prev, mAlpha));
    if (paint.getAlpha() != 0 || paint.getXfermode() != null) {
      if (mShape != null) {
        int count = canvas.save();
        canvas.translate(r.left, r.top);
        onDraw(mShape, canvas, paint);
        canvas.restoreToCount(count);
      } else canvas.drawRect(r, paint);
    }
    paint.setAlpha(prev);
  }
  @Override public void setAlpha(int alpha) { mAlpha = alpha; invalidateSelf(); }
  @Override public int getAlpha() { return mAlpha; }
  @Override public void setColorFilter(ColorFilter cf) { paint.setColorFilter(cf); invalidateSelf(); }
  @Override public ColorFilter getColorFilter() { return paint.getColorFilter(); }
  @Override public void setDither(boolean dither) { paint.setDither(dither); invalidateSelf(); }
  @Override public int getOpacity() {
    if (mShape == null && paint.getXfermode() == null) {
      int a = modulateAlpha(paint.getAlpha(), mAlpha);
      if (a == 0) return PixelFormat.TRANSPARENT;
      if (a == 255) return PixelFormat.OPAQUE;
    }
    return PixelFormat.TRANSLUCENT;
  }
  @Override protected void onBoundsChange(Rect bounds) { updateShape(); }
  private void updateShape() {
    if (mShape != null) {
      Rect r = getBounds();
      mShape.resize(r.width(), r.height());
      if (mShaderFactory != null) paint.setShader(mShaderFactory.resize(r.width(), r.height()));
    }
    invalidateSelf();
  }
  @Override public void getOutline(Outline outline) {
    if (mShape != null) { mShape.getOutline(outline); outline.setAlpha(getAlpha() / 255.0f); }
  }
  /** "padding" children (left, top, right, bottom attributes); subclasses handle their own tags */
  protected boolean inflateTag(String name, android.content.res.Resources r, org.xmlpull.v1.XmlPullParser parser, android.util.AttributeSet attrs) {
    if ("padding".equals(name)) {
      setPadding(dim(attrs, "left"), dim(attrs, "top"), dim(attrs, "right"), dim(attrs, "bottom"));
      return true;
    }
    return false;
  }
  /** an android: dimension attribute in pixels (0 when absent) */
  static int dim(android.util.AttributeSet attrs, String name) {
    String v = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", name);
    if (v == null) v = attrs.getAttributeValue(null, name);
    return v == null ? 0 : (int) a2w.Values.dimension(v);
  }
  @Override public ConstantState getConstantState() {
    final Shape s = mShape; final Paint p = new Paint(paint); final ShaderFactory f = mShaderFactory; final Rect pad = mPadding == null ? null : new Rect(mPadding);
    final int a = mAlpha, w = iw, h = ih;
    return new ConstantState() {
      public Drawable newDrawable() {
        ShapeDrawable d = new ShapeDrawable();
        try { d.mShape = s == null ? null : s.clone(); } catch (CloneNotSupportedException e) { d.mShape = s; }
        d.paint.set(p); d.mShaderFactory = f; d.mPadding = pad; d.mAlpha = a; d.iw = w; d.ih = h;
        return d;
      }
      public int getChangingConfigurations() { return 0; }
    };
  }
  @Override public Drawable mutate() { return this; }
}
