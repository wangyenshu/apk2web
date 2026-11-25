package android.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.*;

/**
 * A ripple: its layers, and while pressed (or focused, hovered) the ripple color over them, inside the mask (or the
 * layers' area). The ripple is not animated.
 */
public class RippleDrawable extends LayerDrawable {
  public static final int RADIUS_AUTO = -1;
  ColorStateList mColor;
  Drawable mMask;
  int mRadius = RADIUS_AUTO;
  private boolean mActive;
  private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

  public RippleDrawable(ColorStateList color, Drawable content, Drawable mask) {
    this(color, content == null ? new Drawable[0] : new Drawable[] {content}, mask);
  }
  /** web: a ripple over several layers (as <ripple> XML has them) */
  public RippleDrawable(ColorStateList color, Drawable[] layers, Drawable mask) {
    super(layers);
    mColor = color != null ? color : ColorStateList.valueOf(0x1f000000);
    mMask = mask;
    if (mask != null) mask.setCallback(this);
  }
  public void setColor(ColorStateList c) { if (c != null) { mColor = c; invalidateSelf(); } }
  public void setRadius(int r) { mRadius = r; invalidateSelf(); }
  public int getRadius() { return mRadius; }
  public ColorStateList getEffectColor() { return mColor; }
  public void setEffectColor(ColorStateList c) { setColor(c); }

  @Override public boolean isStateful() { return true; }
  @Override public boolean hasFocusStateSpecified() { return true; }
  @Override protected boolean onStateChange(int[] s) {
    boolean ch = super.onStateChange(s);
    boolean active = false, enabled = false;
    for (int x : s) {
      if (x == android.R.attr.state_pressed || x == android.R.attr.state_focused || x == android.R.attr.state_hovered) active = true;
      if (x == android.R.attr.state_enabled) enabled = true;
    }
    active &= enabled;
    if (active != mActive) { mActive = active; invalidateSelf(); return true; }
    return ch;
  }
  @Override protected void onBoundsChange(Rect b) { super.onBoundsChange(b); if (mMask != null) mMask.setBounds(b); }
  @Override public void draw(Canvas c) {
    super.draw(c);
    if (!mActive) return;
    int col = mColor.getColorForState(getState(), mColor.getDefaultColor());
    if (Color.alpha(col) == 0) return;
    Rect b = getBounds();
    mPaint.setColor(col);
    if (mRadius > 0) { c.drawCircle(b.exactCenterX(), b.exactCenterY(), mRadius, mPaint); return; }
    if (mMask == null && getNumberOfLayers() == 0) {
      // unbounded: a circle around the center
      c.drawCircle(b.exactCenterX(), b.exactCenterY(), Math.max(b.width(), b.height()) / 2f, mPaint);
      return;
    }
    c.drawRect(b, mPaint);
  }
  @Override public void getOutline(Outline o) {
    if (mMask != null) mMask.getOutline(o);
    else if (getNumberOfLayers() > 0) getDrawable(0).getOutline(o);
    else super.getOutline(o);
  }
}
