package android.graphics.drawable;

import android.graphics.*;

/**
 * android.graphics.drawable.AdaptiveIconDrawable (API 26): background, foreground (and API 33 monochrome) layers at
 * 1.5x the bounds, centered, clipped to the device mask (the AOSP default, a circle).
 */
public class AdaptiveIconDrawable extends Drawable implements Drawable.Callback {
  private static final float EXTRA_INSET_PERCENTAGE = 1 / 4f;
  private static final float DEFAULT_VIEW_PORT_SCALE = 1f / (1 + 2 * EXTRA_INSET_PERCENTAGE);
  private static final float MASK_SIZE = 100f;
  private static Path sMask;
  private final Drawable mBackground, mForeground, mMonochrome;
  private final Path mMask = new Path();
  private int mAlpha = 255, mOpacity = PixelFormat.UNKNOWN;
  private boolean mAutoMirrored;

  public AdaptiveIconDrawable(Drawable backgroundDrawable, Drawable foregroundDrawable) { this(backgroundDrawable, foregroundDrawable, null); }
  public AdaptiveIconDrawable(Drawable backgroundDrawable, Drawable foregroundDrawable, Drawable monochromeDrawable) {
    mBackground = backgroundDrawable; mForeground = foregroundDrawable; mMonochrome = monochromeDrawable;
    for (Drawable d : new Drawable[] {mBackground, mForeground, mMonochrome}) if (d != null) d.setCallback(this);
  }
  static Path mask() {
    if (sMask == null) { sMask = new Path(); sMask.addCircle(MASK_SIZE / 2, MASK_SIZE / 2, MASK_SIZE / 2, Path.Direction.CW); }
    return sMask;
  }
  public static float getExtraInsetFraction() { return EXTRA_INSET_PERCENTAGE; }
  public Drawable getBackground() { return mBackground; }
  public Drawable getForeground() { return mForeground; }
  public Drawable getMonochrome() { return mMonochrome; }
  /** the mask scaled to the bounds */
  public Path getIconMask() { return mMask; }
  @Override protected void onBoundsChange(Rect b) {
    int cx = b.width() / 2, cy = b.height() / 2;
    int iw = (int) (b.width() / (DEFAULT_VIEW_PORT_SCALE * 2)), ih = (int) (b.height() / (DEFAULT_VIEW_PORT_SCALE * 2));
    Rect r = new Rect(b.left + cx - iw, b.top + cy - ih, b.left + cx + iw, b.top + cy + ih);
    for (Drawable d : new Drawable[] {mBackground, mForeground, mMonochrome}) if (d != null) d.setBounds(r);
    Matrix m = new Matrix();
    m.setScale(b.width() / MASK_SIZE, b.height() / MASK_SIZE);
    m.postTranslate(b.left, b.top);
    mMask.reset();
    mask().transform(m, mMask);
    invalidateSelf();
  }
  @Override public void draw(Canvas canvas) {
    if (getBounds().isEmpty()) return;
    int s = canvas.save();
    canvas.clipPath(mMask);
    if (mAlpha < 255) canvas.saveLayerAlpha(null, mAlpha);
    if (mBackground != null) mBackground.draw(canvas);
    if (mForeground != null) mForeground.draw(canvas);
    canvas.restoreToCount(s);
  }
  @Override public void getOutline(Outline outline) {
    Rect b = getBounds();
    outline.setOval(b);
    outline.setAlpha(mAlpha / 255f);
  }
  @Override public Region getTransparentRegion() {
    Region r = new Region();
    r.setPath(mMask, new Region(getBounds()));
    Region t = new Region(getBounds());
    t.op(r, Region.Op.DIFFERENCE);
    return t;
  }
  private int maxIntrinsic(boolean w) {
    int a = mBackground == null ? -1 : (w ? mBackground.getIntrinsicWidth() : mBackground.getIntrinsicHeight());
    int b = mForeground == null ? -1 : (w ? mForeground.getIntrinsicWidth() : mForeground.getIntrinsicHeight());
    return Math.max(a, b);
  }
  @Override public int getIntrinsicWidth() { int m = maxIntrinsic(true); return m < 0 ? -1 : (int) (m * DEFAULT_VIEW_PORT_SCALE); }
  @Override public int getIntrinsicHeight() { int m = maxIntrinsic(false); return m < 0 ? -1 : (int) (m * DEFAULT_VIEW_PORT_SCALE); }
  @Override public void setAlpha(int alpha) { mAlpha = alpha; invalidateSelf(); }
  @Override public int getAlpha() { return mAlpha; }
  @Override public void setColorFilter(ColorFilter cf) { for (Drawable d : new Drawable[] {mBackground, mForeground}) if (d != null) d.setColorFilter(cf); }
  @Override public void setTintList(android.content.res.ColorStateList tint) { for (Drawable d : new Drawable[] {mBackground, mForeground}) if (d != null) d.setTintList(tint); }
  @Override public void setTintBlendMode(BlendMode mode) { for (Drawable d : new Drawable[] {mBackground, mForeground}) if (d != null) d.setTintBlendMode(mode); }
  @Override public void setDither(boolean dither) { for (Drawable d : new Drawable[] {mBackground, mForeground}) if (d != null) d.setDither(dither); }
  public void setOpacity(int opacity) { mOpacity = opacity; }
  @Override public int getOpacity() { return mOpacity != PixelFormat.UNKNOWN ? mOpacity : PixelFormat.TRANSLUCENT; }
  @Override public void setAutoMirrored(boolean mirrored) { mAutoMirrored = mirrored; for (Drawable d : new Drawable[] {mBackground, mForeground}) if (d != null) d.setAutoMirrored(mirrored); }
  @Override public boolean isAutoMirrored() { return mAutoMirrored; }
  @Override public boolean isProjected() { return false; }
  @Override public boolean isStateful() { return (mBackground != null && mBackground.isStateful()) || (mForeground != null && mForeground.isStateful()); }
  @Override public boolean hasFocusStateSpecified() { return (mBackground != null && mBackground.hasFocusStateSpecified()) || (mForeground != null && mForeground.hasFocusStateSpecified()); }
  @Override protected boolean onStateChange(int[] state) {
    boolean ch = false;
    for (Drawable d : new Drawable[] {mBackground, mForeground}) if (d != null && d.isStateful()) ch |= d.setState(state);
    return ch;
  }
  @Override protected boolean onLevelChange(int level) {
    boolean ch = false;
    for (Drawable d : new Drawable[] {mBackground, mForeground}) if (d != null) ch |= d.setLevel(level);
    return ch;
  }
  @Override public void jumpToCurrentState() { for (Drawable d : new Drawable[] {mBackground, mForeground}) if (d != null) d.jumpToCurrentState(); }
  @Override public boolean setVisible(boolean visible, boolean restart) {
    boolean c = super.setVisible(visible, restart);
    for (Drawable d : new Drawable[] {mBackground, mForeground}) if (d != null) d.setVisible(visible, restart);
    return c;
  }
  @Override public void setHotspot(float x, float y) { for (Drawable d : new Drawable[] {mBackground, mForeground}) if (d != null) d.setHotspot(x, y); }
  @Override public void setHotspotBounds(int l, int t, int r, int b) { for (Drawable d : new Drawable[] {mBackground, mForeground}) if (d != null) d.setHotspotBounds(l, t, r, b); }
  @Override public void getHotspotBounds(Rect out) { if (mBackground != null) mBackground.getHotspotBounds(out); else out.set(getBounds()); }
  @Override public int getChangingConfigurations() {
    return super.getChangingConfigurations() | (mBackground != null ? mBackground.getChangingConfigurations() : 0) | (mForeground != null ? mForeground.getChangingConfigurations() : 0);
  }
  @Override public boolean canApplyTheme() { return (mBackground != null && mBackground.canApplyTheme()) || (mForeground != null && mForeground.canApplyTheme()); }
  @Override public void applyTheme(android.content.res.Resources.Theme t) { for (Drawable d : new Drawable[] {mBackground, mForeground}) if (d != null && d.canApplyTheme()) d.applyTheme(t); }
  @Override public void inflate(android.content.res.Resources r, org.xmlpull.v1.XmlPullParser parser, android.util.AttributeSet attrs, android.content.res.Resources.Theme theme) {}
  @Override public ConstantState getConstantState() {
    final ConstantState b = mBackground == null ? null : mBackground.getConstantState(), f = mForeground == null ? null : mForeground.getConstantState();
    if ((mBackground != null && b == null) || (mForeground != null && f == null)) return null;
    return new ConstantState() {
      public Drawable newDrawable() { return new AdaptiveIconDrawable(b == null ? null : b.newDrawable(), f == null ? null : f.newDrawable()); }
      public int getChangingConfigurations() { return 0; }
    };
  }
  @Override public Drawable mutate() { for (Drawable d : new Drawable[] {mBackground, mForeground, mMonochrome}) if (d != null) d.mutate(); return this; }
  @Override public void invalidateSelf() { super.invalidateSelf(); }
  public void invalidateDrawable(Drawable who) { invalidateSelf(); }
  public void scheduleDrawable(Drawable who, Runnable what, long when) { scheduleSelf(what, when); }
  public void unscheduleDrawable(Drawable who, Runnable what) { unscheduleSelf(what); }
}
