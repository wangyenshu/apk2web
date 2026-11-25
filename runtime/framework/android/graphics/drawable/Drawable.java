package android.graphics.drawable;

import android.graphics.*;
import android.util.StateSet;

public abstract class Drawable {
  public interface Callback {
    void invalidateDrawable(Drawable who);
    void scheduleDrawable(Drawable who, Runnable what, long when);
    void unscheduleDrawable(Drawable who, Runnable what);
  }
  public static abstract class ConstantState {
    public abstract Drawable newDrawable();
    public Drawable newDrawable(android.content.res.Resources r) { return newDrawable(); }
    public Drawable newDrawable(android.content.res.Resources r, android.content.res.Resources.Theme theme) { return newDrawable(r); }
    public boolean canApplyTheme() { return false; }
    public abstract int getChangingConfigurations();
  }

  private static final Rect ZERO = new Rect();
  int[] state = StateSet.WILD_CARD;
  int level;
  int changingConfigurations;
  Rect bounds = ZERO;
  Callback callback;
  boolean visible = true;

  public abstract void draw(Canvas c);
  public abstract void setAlpha(int a);
  public abstract void setColorFilter(ColorFilter cf);
  public abstract int getOpacity();

  public void setBounds(int l, int t, int r, int b) {
    Rect old = bounds;
    if (old == ZERO) old = bounds = new Rect();
    if (old.left != l || old.top != t || old.right != r || old.bottom != b) {
      old.set(l, t, r, b);
      onBoundsChange(old);
    }
  }
  public void setBounds(Rect r) { setBounds(r.left, r.top, r.right, r.bottom); }
  public final void copyBounds(Rect b) { b.set(bounds); }
  public final Rect copyBounds() { return new Rect(bounds); }
  public final Rect getBounds() { if (bounds == ZERO) bounds = new Rect(); return bounds; }
  public void setChangingConfigurations(int c) { changingConfigurations = c; }
  public int getChangingConfigurations() { return changingConfigurations; }
  public void setDither(boolean d) {}
  public void setFilterBitmap(boolean f) {}
  public final void setCallback(Callback cb) { callback = cb; }
  public Callback getCallback() { return callback; }
  public void invalidateSelf() { if (callback != null) callback.invalidateDrawable(this); }
  public void scheduleSelf(Runnable what, long when) { if (callback != null) callback.scheduleDrawable(this, what, when); }
  public void unscheduleSelf(Runnable what) { if (callback != null) callback.unscheduleDrawable(this, what); }
  public int getAlpha() { return 255; }
  public void setColorFilter(int color, PorterDuff.Mode mode) { setColorFilter(new PorterDuffColorFilter(color, mode)); }
  public void clearColorFilter() { setColorFilter(null); }
  public boolean isStateful() { return mTintList != null && mTintList.isStateful(); }
  public boolean setState(int[] s) {
    if (!java.util.Arrays.equals(state, s)) {
      state = s;
      boolean r = onStateChange(s);
      if (mTintList != null && mTintList.isStateful()) r |= applyTint();
      return r;
    }
    return false;
  }

  // ---- tint: a color filter over the drawable (SRC_IN unless set), the color picked by state
  android.content.res.ColorStateList mTintList;
  PorterDuff.Mode mTintMode = PorterDuff.Mode.SRC_IN;
  boolean mTinted;
  public void setTint(int color) { setTintList(android.content.res.ColorStateList.valueOf(color)); }
  public void setTintList(android.content.res.ColorStateList tint) { mTintList = tint; applyTint(); }
  public void setTintMode(PorterDuff.Mode mode) { mTintMode = mode == null ? PorterDuff.Mode.SRC_IN : mode; applyTint(); }
  boolean applyTint() {
    if (mTintList == null) {
      if (mTinted) { mTinted = false; setColorFilter(null); invalidateSelf(); return true; }
      return false;
    }
    setColorFilter(new PorterDuffColorFilter(mTintList.getColorForState(state, mTintList.getDefaultColor()), mTintMode));
    mTinted = true;
    invalidateSelf();
    return true;
  }
  public ColorFilter getColorFilter() { return null; }
  public boolean hasFocusStateSpecified() { return false; }

  // ---- layout direction, mirroring, hotspots, outline
  int mLayoutDirection;
  boolean mAutoMirrored;
  public int getLayoutDirection() { return mLayoutDirection; }
  public final boolean setLayoutDirection(int d) { if (mLayoutDirection != d) { mLayoutDirection = d; return onLayoutDirectionChanged(d); } return false; }
  public boolean onLayoutDirectionChanged(int d) { return false; }
  public void setAutoMirrored(boolean m) { mAutoMirrored = m; }
  public boolean isAutoMirrored() { return mAutoMirrored; }
  public void setHotspot(float x, float y) {}
  public void setHotspotBounds(int l, int t, int r, int b) {}
  public void getHotspotBounds(Rect out) { out.set(getBounds()); }
  public boolean isProjected() { return false; }
  public void getOutline(Outline outline) { outline.setRect(getBounds()); outline.setAlpha(0); }
  public Rect getDirtyBounds() { return getBounds(); }
  public boolean isFilterBitmap() { return false; }
  public void setXfermode(Xfermode mode) {}

  // ---- themes and inflation
  public boolean canApplyTheme() { return false; }
  public void applyTheme(android.content.res.Resources.Theme t) {}
  public void inflate(android.content.res.Resources r, org.xmlpull.v1.XmlPullParser parser, android.util.AttributeSet attrs)
      throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {}
  public void inflate(android.content.res.Resources r, org.xmlpull.v1.XmlPullParser parser, android.util.AttributeSet attrs,
      android.content.res.Resources.Theme theme) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException { inflate(r, parser, attrs); }
  public static Drawable createFromXml(android.content.res.Resources r, org.xmlpull.v1.XmlPullParser parser)
      throws org.xmlpull.v1.XmlPullParserException, java.io.IOException { return createFromXml(r, parser, null); }
  public static Drawable createFromXml(android.content.res.Resources r, org.xmlpull.v1.XmlPullParser parser, android.content.res.Resources.Theme theme)
      throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
    if (!(parser instanceof a2w.XmlResParser)) throw new org.xmlpull.v1.XmlPullParserException("not a compiled resource parser");
    a2w.XmlNode n = ((a2w.XmlResParser) parser).currentNode();
    if (n == null) throw new org.xmlpull.v1.XmlPullParserException("No start tag found");
    android.content.res.Resources.Theme was = a2w.Drawables.theme;
    if (theme != null) a2w.Drawables.theme = theme;
    try { return a2w.Drawables.fromXml(r, n); } finally { a2w.Drawables.theme = was; }
  }
  public static Drawable createFromXmlInner(android.content.res.Resources r, org.xmlpull.v1.XmlPullParser parser, android.util.AttributeSet attrs)
      throws org.xmlpull.v1.XmlPullParserException, java.io.IOException { return createFromXml(r, parser, null); }
  public static Drawable createFromXmlInner(android.content.res.Resources r, org.xmlpull.v1.XmlPullParser parser, android.util.AttributeSet attrs,
      android.content.res.Resources.Theme theme) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException { return createFromXml(r, parser, theme); }
  public static Drawable createFromResourceStream(android.content.res.Resources r, android.util.TypedValue value, java.io.InputStream is, String srcName) {
    return createFromResourceStream(r, value, is, srcName, null);
  }
  public static Drawable createFromResourceStream(android.content.res.Resources r, android.util.TypedValue value, java.io.InputStream is, String srcName,
      BitmapFactory.Options opts) {
    if (is == null) return null;
    if (opts == null) opts = new BitmapFactory.Options();
    opts.inScreenDensity = r != null ? r.getDisplayMetrics().densityDpi : android.util.DisplayMetrics.DENSITY_DEVICE;
    return fromBitmap(r, BitmapFactory.decodeResourceStream(r, value, is, null, opts), srcName);
  }
  public void clearMutated() {}
  public int[] getState() { return state; }
  public void jumpToCurrentState() {}
  public Drawable getCurrent() { return this; }
  public final boolean setLevel(int l) { if (level != l) { level = l; return onLevelChange(l); } return false; }
  public final int getLevel() { return level; }
  public boolean setVisible(boolean v, boolean restart) { boolean changed = visible != v; visible = v; if (changed) invalidateSelf(); return changed; }
  public final boolean isVisible() { return visible; }
  public static int resolveOpacity(int a, int b) {
    if (a == b) return a;
    if (a == PixelFormat.UNKNOWN || b == PixelFormat.UNKNOWN) return PixelFormat.UNKNOWN;
    if (a == PixelFormat.TRANSLUCENT || b == PixelFormat.TRANSLUCENT) return PixelFormat.TRANSLUCENT;
    if (a == PixelFormat.TRANSPARENT || b == PixelFormat.TRANSPARENT) return PixelFormat.TRANSPARENT;
    return PixelFormat.OPAQUE;
  }
  public Region getTransparentRegion() { return null; }
  /** layout (optical) insets: none unless the drawable defines them (nine-patches, wrappers) */
  public Insets getOpticalInsets() { return Insets.NONE; }
  /** the blend modes with a Porter-Duff equivalent tint like it; the rest tint with SRC_IN */
  public void setTintBlendMode(BlendMode blendMode) { setTintMode(porterDuff(blendMode)); }
  static PorterDuff.Mode porterDuff(BlendMode b) {
    if (b == null) return PorterDuff.Mode.SRC_IN;
    String n = b.name();
    if (n.equals("PLUS")) n = "ADD"; else if (n.equals("MODULATE")) n = "MULTIPLY";
    for (PorterDuff.Mode m : PorterDuff.Mode.values()) if (m.name().equals(n)) return m;
    return PorterDuff.Mode.SRC_IN;
  }
  /** a BitmapDrawable, or a NinePatchDrawable for a compiled nine-patch */
  static Drawable fromBitmap(android.content.res.Resources r, Bitmap b, String srcName) {
    if (b == null) return null;
    byte[] np = b.getNinePatchChunk();
    if (np != null && NinePatch.isNinePatchChunk(np)) return new NinePatchDrawable(r, b, np, null, srcName);
    return new BitmapDrawable(r, b);
  }
  protected boolean onStateChange(int[] s) { return false; }
  protected boolean onLevelChange(int l) { return false; }
  protected void onBoundsChange(Rect b) {}
  public int getIntrinsicWidth() { return -1; }
  public int getIntrinsicHeight() { return -1; }
  public int getMinimumWidth() { int w = getIntrinsicWidth(); return w > 0 ? w : 0; }
  public int getMinimumHeight() { int h = getIntrinsicHeight(); return h > 0 ? h : 0; }
  public boolean getPadding(Rect p) { p.set(0, 0, 0, 0); return false; }
  public Drawable mutate() { return this; }
  public ConstantState getConstantState() { return null; }
  public static Drawable createFromStream(java.io.InputStream is, String srcName) { return is == null ? null : fromBitmap(null, BitmapFactory.decodeStream(is), srcName); }
  public static Drawable createFromPath(String path) { return path == null ? null : fromBitmap(null, BitmapFactory.decodeFile(path), path); }
}
