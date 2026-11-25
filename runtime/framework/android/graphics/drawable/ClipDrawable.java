package android.graphics.drawable;

import android.graphics.*;
import android.view.Gravity;

/** the wrapped drawable clipped to a level-sized part (10000: all of it), placed by gravity */
public class ClipDrawable extends DrawableWrapper {
  public static final int HORIZONTAL = 1, VERTICAL = 2;
  final int gravity, orientation;
  private final Rect tmp = new Rect();
  public ClipDrawable(Drawable drawable, int gravity, int orientation) {
    super(drawable);
    this.gravity = gravity; this.orientation = orientation;
  }
  @Override public void draw(Canvas c) {
    if (mDrawable == null || mDrawable.getLevel() == 0) return;
    Rect b = getBounds();
    int lvl = getLevel(), w = b.width(), h = b.height();
    if ((orientation & HORIZONTAL) != 0) w -= (w - 0) * (10000 - lvl) / 10000;
    if ((orientation & VERTICAL) != 0) h -= (h - 0) * (10000 - lvl) / 10000;
    Gravity.apply(gravity, w, h, b, tmp, getLayoutDirection());
    if (w > 0 && h > 0) { int s = c.save(); c.clipRect(tmp); mDrawable.draw(c); c.restoreToCount(s); }
  }
  @Override protected boolean onLevelChange(int l) { super.onLevelChange(l); invalidateSelf(); return true; }
  @Override public int getOpacity() {
    if (mDrawable == null || mDrawable.getLevel() == 0) return PixelFormat.TRANSPARENT;
    return getLevel() >= 10000 ? mDrawable.getOpacity() : PixelFormat.TRANSLUCENT;
  }
  @Override Drawable copyWith(Drawable d) { return new ClipDrawable(d, gravity, orientation); }
}
