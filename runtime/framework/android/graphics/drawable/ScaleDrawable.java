package android.graphics.drawable;

import android.graphics.*;
import android.view.Gravity;

/** the wrapped drawable shrunk by level (10000: full size) along the scaled axes, placed by gravity */
public class ScaleDrawable extends DrawableWrapper {
  final int gravity; final float sw, sh;
  private final Rect tmp = new Rect();
  public ScaleDrawable(Drawable drawable, int gravity, float scaleWidth, float scaleHeight) {
    super(drawable);
    this.gravity = gravity; sw = scaleWidth; sh = scaleHeight;
  }
  @Override public void draw(Canvas c) { if (mDrawable != null && mDrawable.getLevel() != 0) mDrawable.draw(c); }
  @Override protected void onBoundsChange(Rect b) {
    int lvl = getLevel();
    int w = b.width(), h = b.height();
    if (sw > 0) { int iw = mDrawable == null ? 0 : mDrawable.getIntrinsicWidth(); if (iw > 0) w = iw; w -= (int) ((w * (10000 - lvl) * sw) / 10000); }
    if (sh > 0) { int ih = mDrawable == null ? 0 : mDrawable.getIntrinsicHeight(); if (ih > 0) h = ih; h -= (int) ((h * (10000 - lvl) * sh) / 10000); }
    Gravity.apply(gravity, w, h, b, tmp, getLayoutDirection());
    if (mDrawable != null) mDrawable.setBounds(tmp);
  }
  @Override protected boolean onLevelChange(int l) { super.onLevelChange(l); onBoundsChange(getBounds()); invalidateSelf(); return true; }
  @Override public int getOpacity() { return mDrawable != null && mDrawable.getLevel() == 0 ? PixelFormat.TRANSPARENT : super.getOpacity(); }
  @Override Drawable copyWith(Drawable d) { return new ScaleDrawable(d, gravity, sw, sh); }
}
