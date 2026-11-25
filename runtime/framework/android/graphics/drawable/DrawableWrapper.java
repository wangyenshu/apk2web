package android.graphics.drawable;

import android.graphics.*;

/** android.graphics.drawable.DrawableWrapper (API 23): forwards to the wrapped drawable, which fills the bounds. */
public abstract class DrawableWrapper extends Drawable implements Drawable.Callback {
  Drawable mDrawable;
  public DrawableWrapper(Drawable dr) { setDrawable(dr); }
  public void setDrawable(Drawable dr) {
    if (mDrawable != null) mDrawable.setCallback(null);
    mDrawable = dr;
    if (dr != null) {
      dr.setCallback(this);
      dr.setVisible(isVisible(), true);
      dr.setState(getState());
      dr.setLevel(getLevel());
      dr.setBounds(getBounds());
      dr.setLayoutDirection(getLayoutDirection());
    }
    invalidateSelf();
  }
  public Drawable getDrawable() { return mDrawable; }
  @Override public void draw(Canvas canvas) { if (mDrawable != null) mDrawable.draw(canvas); }
  @Override protected void onBoundsChange(Rect bounds) { if (mDrawable != null) mDrawable.setBounds(bounds); }
  @Override public boolean getPadding(Rect padding) { return mDrawable != null && mDrawable.getPadding(padding); }
  @Override public Insets getOpticalInsets() { return mDrawable != null ? mDrawable.getOpticalInsets() : Insets.NONE; }
  @Override public void setHotspot(float x, float y) { if (mDrawable != null) mDrawable.setHotspot(x, y); }
  @Override public void setHotspotBounds(int left, int top, int right, int bottom) { if (mDrawable != null) mDrawable.setHotspotBounds(left, top, right, bottom); }
  @Override public void getHotspotBounds(Rect outRect) { if (mDrawable != null) mDrawable.getHotspotBounds(outRect); else outRect.set(getBounds()); }
  @Override public boolean setVisible(boolean visible, boolean restart) {
    boolean superChanged = super.setVisible(visible, restart);
    boolean changed = mDrawable != null && mDrawable.setVisible(visible, restart);
    return superChanged | changed;
  }
  @Override public void setAlpha(int alpha) { if (mDrawable != null) mDrawable.setAlpha(alpha); }
  @Override public int getAlpha() { return mDrawable != null ? mDrawable.getAlpha() : 255; }
  @Override public void setColorFilter(ColorFilter colorFilter) { if (mDrawable != null) mDrawable.setColorFilter(colorFilter); }
  @Override public ColorFilter getColorFilter() { return mDrawable != null ? mDrawable.getColorFilter() : null; }
  @Override public void setTintList(android.content.res.ColorStateList tint) { if (mDrawable != null) mDrawable.setTintList(tint); }
  @Override public void setTintMode(PorterDuff.Mode mode) { if (mDrawable != null) mDrawable.setTintMode(mode); }
  @Override public void setTintBlendMode(BlendMode blendMode) { if (mDrawable != null) mDrawable.setTintBlendMode(blendMode); }
  @Override public void setDither(boolean dither) { if (mDrawable != null) mDrawable.setDither(dither); }
  @Override public void setFilterBitmap(boolean filter) { if (mDrawable != null) mDrawable.setFilterBitmap(filter); }
  @Override public boolean isFilterBitmap() { return mDrawable != null && mDrawable.isFilterBitmap(); }
  @Override public int getOpacity() { return mDrawable != null ? mDrawable.getOpacity() : PixelFormat.TRANSPARENT; }
  @Override public boolean isStateful() { return mDrawable != null && mDrawable.isStateful(); }
  @Override public boolean hasFocusStateSpecified() { return mDrawable != null && mDrawable.hasFocusStateSpecified(); }
  @Override protected boolean onStateChange(int[] state) {
    if (mDrawable != null && mDrawable.isStateful()) {
      boolean changed = mDrawable.setState(state);
      if (changed) onBoundsChange(getBounds());
      return changed;
    }
    return false;
  }
  @Override public void jumpToCurrentState() { if (mDrawable != null) mDrawable.jumpToCurrentState(); }
  @Override protected boolean onLevelChange(int level) { return mDrawable != null && mDrawable.setLevel(level); }
  @Override public boolean onLayoutDirectionChanged(int layoutDirection) { return mDrawable != null && mDrawable.setLayoutDirection(layoutDirection); }
  @Override public int getIntrinsicWidth() { return mDrawable != null ? mDrawable.getIntrinsicWidth() : -1; }
  @Override public int getIntrinsicHeight() { return mDrawable != null ? mDrawable.getIntrinsicHeight() : -1; }
  @Override public void getOutline(Outline outline) { if (mDrawable != null) mDrawable.getOutline(outline); else super.getOutline(outline); }
  @Override public int getChangingConfigurations() { return super.getChangingConfigurations() | (mDrawable != null ? mDrawable.getChangingConfigurations() : 0); }
  @Override public boolean canApplyTheme() { return mDrawable != null && mDrawable.canApplyTheme(); }
  @Override public void applyTheme(android.content.res.Resources.Theme t) { if (mDrawable != null && mDrawable.canApplyTheme()) mDrawable.applyTheme(t); }
  @Override public void inflate(android.content.res.Resources r, org.xmlpull.v1.XmlPullParser parser, android.util.AttributeSet attrs, android.content.res.Resources.Theme theme)
      throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {}
  @Override public ConstantState getConstantState() {
    final ConstantState inner = mDrawable == null ? null : mDrawable.getConstantState();
    if (inner == null) return null;
    final DrawableWrapper self = this;
    return new ConstantState() {
      public Drawable newDrawable() { return self.copyWith(inner.newDrawable()); }
      public Drawable newDrawable(android.content.res.Resources r) { return self.copyWith(inner.newDrawable(r)); }
      public int getChangingConfigurations() { return inner.getChangingConfigurations(); }
    };
  }
  /** a wrapper like this one around d (subclasses copy their own settings) */
  Drawable copyWith(Drawable d) { return null; }
  @Override public Drawable mutate() { if (mDrawable != null) mDrawable.mutate(); return this; }
  public void invalidateDrawable(Drawable who) { if (callback != null) callback.invalidateDrawable(this); }
  public void scheduleDrawable(Drawable who, Runnable what, long when) { if (callback != null) callback.scheduleDrawable(this, what, when); }
  public void unscheduleDrawable(Drawable who, Runnable what) { if (callback != null) callback.unscheduleDrawable(this, what); }
}
