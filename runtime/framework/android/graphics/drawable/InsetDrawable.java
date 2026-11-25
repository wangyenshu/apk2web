package android.graphics.drawable;

import android.graphics.*;

/** the wrapped drawable inset by pixels or by fractions of the bounds (API 26) */
public class InsetDrawable extends DrawableWrapper {
  int l, t, r, b;
  float fl, ft, fr, fb;   // fractions, used when the pixel inset is 0
  private final Rect tmp = new Rect();
  public InsetDrawable(Drawable drawable, int inset) { this(drawable, inset, inset, inset, inset); }
  public InsetDrawable(Drawable drawable, float inset) { this(drawable, inset, inset, inset, inset); }
  public InsetDrawable(Drawable drawable, int insetLeft, int insetTop, int insetRight, int insetBottom) {
    super(drawable);
    l = insetLeft; t = insetTop; r = insetRight; b = insetBottom;
  }
  public InsetDrawable(Drawable drawable, float insetLeftFraction, float insetTopFraction, float insetRightFraction, float insetBottomFraction) {
    super(drawable);
    fl = insetLeftFraction; ft = insetTopFraction; fr = insetRightFraction; fb = insetBottomFraction;
  }
  private int il(int w) { return l != 0 ? l : (int) (fl * w); }
  private int it(int h) { return t != 0 ? t : (int) (ft * h); }
  private int ir(int w) { return r != 0 ? r : (int) (fr * w); }
  private int ib(int h) { return b != 0 ? b : (int) (fb * h); }
  @Override protected void onBoundsChange(Rect bb) {
    int w = bb.width(), h = bb.height();
    if (mDrawable != null) mDrawable.setBounds(bb.left + il(w), bb.top + it(h), bb.right - ir(w), bb.bottom - ib(h));
  }
  @Override public boolean getPadding(Rect p) {
    boolean pad = super.getPadding(p);
    Rect bb = getBounds();
    int w = bb.width(), h = bb.height();
    p.left += il(w); p.right += ir(w); p.top += it(h); p.bottom += ib(h);
    return pad || (p.left | p.right | p.top | p.bottom) != 0;
  }
  @Override public Insets getOpticalInsets() {
    Insets in = super.getOpticalInsets();
    Rect bb = getBounds();
    int w = bb.width(), h = bb.height();
    return Insets.of(in.left + il(w), in.top + it(h), in.right + ir(w), in.bottom + ib(h));
  }
  @Override public int getOpacity() {
    int op = super.getOpacity();
    return op == PixelFormat.OPAQUE && (l > 0 || t > 0 || r > 0 || b > 0 || fl > 0 || ft > 0 || fr > 0 || fb > 0) ? PixelFormat.TRANSLUCENT : op;
  }
  @Override public int getIntrinsicWidth() {
    int w = mDrawable == null ? -1 : mDrawable.getIntrinsicWidth();
    if (w < 0) return -1;
    float f = fl + fr;
    return (l == 0 && r == 0 && f > 0 && f < 1) ? (int) (w / (1 - f)) : w + l + r;
  }
  @Override public int getIntrinsicHeight() {
    int h = mDrawable == null ? -1 : mDrawable.getIntrinsicHeight();
    if (h < 0) return -1;
    float f = ft + fb;
    return (t == 0 && b == 0 && f > 0 && f < 1) ? (int) (h / (1 - f)) : h + t + b;
  }
  @Override Drawable copyWith(Drawable d) { InsetDrawable c = new InsetDrawable(d, l, t, r, b); c.fl = fl; c.ft = ft; c.fr = fr; c.fb = fb; return c; }
}
