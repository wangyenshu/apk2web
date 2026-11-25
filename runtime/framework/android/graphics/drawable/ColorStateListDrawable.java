package android.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.*;

/** android.graphics.drawable.ColorStateListDrawable (API 29): the bounds filled with the state's color of a list. */
public class ColorStateListDrawable extends Drawable implements Drawable.Callback {
  private ColorStateList mList;
  private final ColorDrawable mColor = new ColorDrawable();
  private int mAlpha = -1;
  public ColorStateListDrawable() { this(ColorStateList.valueOf(0)); }
  public ColorStateListDrawable(ColorStateList colorStateList) { mColor.setCallback(this); setColorStateList(colorStateList); }
  public ColorStateList getColorStateList() { return mList; }
  public void setColorStateList(ColorStateList colorStateList) { mList = colorStateList == null ? ColorStateList.valueOf(0) : colorStateList; onStateChange(getState()); }
  @Override public void draw(Canvas canvas) { mColor.draw(canvas); }
  @Override protected void onBoundsChange(Rect bounds) { mColor.setBounds(bounds); }
  @Override protected boolean onStateChange(int[] state) {
    int c = mList.getColorForState(state, mList.getDefaultColor());
    if (mAlpha != -1) c = (c & 0xffffff) | (((c >>> 24) * mAlpha / 255) << 24);
    if (c != mColor.getColor()) { mColor.setColor(c); invalidateSelf(); return true; }
    return false;
  }
  @Override public boolean isStateful() { return mList.isStateful(); }
  @Override public boolean hasFocusStateSpecified() { return false; }
  @Override public Drawable getCurrent() { return mColor; }
  @Override public void setAlpha(int alpha) { mAlpha = alpha; onStateChange(getState()); }
  @Override public int getAlpha() { return mColor.getAlpha(); }
  public void clearAlpha() { mAlpha = -1; onStateChange(getState()); }
  @Override public void setColorFilter(ColorFilter colorFilter) { mColor.setColorFilter(colorFilter); }
  @Override public ColorFilter getColorFilter() { return mColor.getColorFilter(); }
  @Override public void setTintList(ColorStateList tint) { mColor.setTintList(tint); }
  @Override public void setTintBlendMode(BlendMode blendMode) { mColor.setTintBlendMode(blendMode); }
  @Override public int getOpacity() { return mColor.getOpacity(); }
  @Override public boolean canApplyTheme() { return false; }
  @Override public void applyTheme(android.content.res.Resources.Theme t) {}
  @Override public int getChangingConfigurations() { return super.getChangingConfigurations(); }
  @Override public ConstantState getConstantState() {
    final ColorStateList l = mList; final int a = mAlpha;
    return new ConstantState() {
      public Drawable newDrawable() { ColorStateListDrawable d = new ColorStateListDrawable(l); d.mAlpha = a; d.onStateChange(d.getState()); return d; }
      public int getChangingConfigurations() { return 0; }
    };
  }
  @Override public Drawable mutate() { return this; }
  public void invalidateDrawable(Drawable who) { if (who == mColor) invalidateSelf(); }
  public void scheduleDrawable(Drawable who, Runnable what, long when) { scheduleSelf(what, when); }
  public void unscheduleDrawable(Drawable who, Runnable what) { unscheduleSelf(what); }
}
