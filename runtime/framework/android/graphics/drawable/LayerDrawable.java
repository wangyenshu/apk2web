package android.graphics.drawable;

import android.graphics.*;
import android.util.LayoutDirection;
import android.view.Gravity;
import java.util.ArrayList;

/**
 * android.graphics.drawable.LayerDrawable: layers drawn in order, each placed in the bounds by its insets (relative
 * start/end ones win), gravity and size, and (PADDING_MODE_NEST, the default) inside the padding of the layers below.
 */
public class LayerDrawable extends Drawable implements Drawable.Callback {
  public static final int PADDING_MODE_NEST = 0, PADDING_MODE_STACK = 1;
  public static final int INSET_UNDEFINED = Integer.MIN_VALUE;
  static final class Layer {
    Drawable d;
    int id = -1;
    int l, t, r, b, s = INSET_UNDEFINED, e = INSET_UNDEFINED;
    int width = -1, height = -1, gravity = Gravity.NO_GRAVITY;
  }
  final ArrayList<Layer> layers = new ArrayList<>();
  private int paddingMode = PADDING_MODE_NEST;
  private int padL = -1, padT = -1, padR = -1, padB = -1, padS = -1, padE = -1;
  private int opacityOverride = PixelFormat.UNKNOWN;
  private int alpha = 255;
  private final Rect tmp = new Rect(), container = new Rect(), out = new Rect();

  public LayerDrawable(Drawable[] ds) {
    if (ds == null) throw new IllegalArgumentException("layers must be non-null");
    for (Drawable d : ds) add(d, -1, 0, 0, 0, 0);
  }
  LayerDrawable() {}
  void add(Drawable d, int id, int l, int t, int r, int b) {
    Layer y = new Layer(); y.d = d; y.id = id; y.l = l; y.t = t; y.r = r; y.b = b;
    layers.add(y);
    if (d != null) d.setCallback(this);
  }
  /** a new layer on top; its index */
  public int addLayer(Drawable dr) {
    add(dr, -1, 0, 0, 0, 0);
    if (dr != null) { dr.setLayoutDirection(getLayoutDirection()); dr.setState(getState()); dr.setLevel(getLevel()); dr.setVisible(isVisible(), true); }
    updateBounds(getBounds());
    invalidateSelf();
    return layers.size() - 1;
  }
  private Layer at(int index) {
    if (index < 0 || index >= layers.size()) throw new IndexOutOfBoundsException("index " + index + " out of bounds");
    return layers.get(index);
  }
  public Drawable findDrawableByLayerId(int id) { for (int i = layers.size() - 1; i >= 0; i--) if (layers.get(i).id == id) return layers.get(i).d; return null; }
  public int findIndexByLayerId(int id) { for (int i = 0; i < layers.size(); i++) if (layers.get(i).id == id) return i; return -1; }
  public void setId(int index, int id) { at(index).id = id; }
  public int getId(int index) { return at(index).id; }
  public int getNumberOfLayers() { return layers.size(); }
  public Drawable getDrawable(int index) { return at(index).d; }
  public void setDrawable(int index, Drawable drawable) {
    Layer y = at(index);
    if (y.d != null) y.d.setCallback(null);
    y.d = drawable;
    if (drawable != null) { drawable.setCallback(this); drawable.setState(getState()); drawable.setLevel(getLevel()); drawable.setVisible(isVisible(), true); }
    updateBounds(getBounds());
    invalidateSelf();
  }
  public boolean setDrawableByLayerId(int id, Drawable d) {
    int i = findIndexByLayerId(id);
    if (i < 0) return false;
    setDrawable(i, d);
    return true;
  }

  // ---- layer geometry
  public void setLayerSize(int index, int w, int h) { Layer y = at(index); y.width = w; y.height = h; updateBounds(getBounds()); }
  public void setLayerWidth(int index, int w) { at(index).width = w; updateBounds(getBounds()); }
  public int getLayerWidth(int index) { return at(index).width; }
  public void setLayerHeight(int index, int h) { at(index).height = h; updateBounds(getBounds()); }
  public int getLayerHeight(int index) { return at(index).height; }
  public void setLayerGravity(int index, int gravity) { at(index).gravity = gravity; updateBounds(getBounds()); }
  public int getLayerGravity(int index) { return at(index).gravity; }
  public void setLayerInset(int index, int l, int t, int r, int b) { Layer y = at(index); y.l = l; y.t = t; y.r = r; y.b = b; y.s = y.e = INSET_UNDEFINED; updateBounds(getBounds()); }
  public void setLayerInsetRelative(int index, int s, int t, int e, int b) { Layer y = at(index); y.s = s; y.t = t; y.e = e; y.b = b; updateBounds(getBounds()); }
  public void setLayerInsetLeft(int index, int v) { at(index).l = v; updateBounds(getBounds()); }
  public int getLayerInsetLeft(int index) { return at(index).l; }
  public void setLayerInsetRight(int index, int v) { at(index).r = v; updateBounds(getBounds()); }
  public int getLayerInsetRight(int index) { return at(index).r; }
  public void setLayerInsetTop(int index, int v) { at(index).t = v; updateBounds(getBounds()); }
  public int getLayerInsetTop(int index) { return at(index).t; }
  public void setLayerInsetBottom(int index, int v) { at(index).b = v; updateBounds(getBounds()); }
  public int getLayerInsetBottom(int index) { return at(index).b; }
  public void setLayerInsetStart(int index, int v) { at(index).s = v; updateBounds(getBounds()); }
  public int getLayerInsetStart(int index) { return at(index).s; }
  public void setLayerInsetEnd(int index, int v) { at(index).e = v; updateBounds(getBounds()); }
  public int getLayerInsetEnd(int index) { return at(index).e; }

  // ---- padding
  public void setPaddingMode(int mode) {
    if (mode != PADDING_MODE_NEST && mode != PADDING_MODE_STACK) throw new IllegalArgumentException("Padding mode must be PADDING_MODE_NEST or PADDING_MODE_STACK");
    if (paddingMode != mode) { paddingMode = mode; updateBounds(getBounds()); }
  }
  public int getPaddingMode() { return paddingMode; }
  public void setPadding(int left, int top, int right, int bottom) { padL = left; padT = top; padR = right; padB = bottom; padS = padE = -1; }
  public void setPaddingRelative(int start, int top, int end, int bottom) { padS = start; padT = top; padE = end; padB = bottom; padL = padR = -1; }
  public int getLeftPadding() { return padL; }
  public int getRightPadding() { return padR; }
  public int getStartPadding() { return padS; }
  public int getEndPadding() { return padE; }
  public int getTopPadding() { return padT; }
  public int getBottomPadding() { return padB; }
  private boolean rtl() { return getLayoutDirection() == LayoutDirection.RTL; }
  @Override public boolean getPadding(Rect p) {
    p.set(0, 0, 0, 0);
    for (Layer y : layers) {
      if (y.d == null || !y.d.getPadding(tmp)) continue;
      if (paddingMode == PADDING_MODE_NEST) { p.left += tmp.left; p.top += tmp.top; p.right += tmp.right; p.bottom += tmp.bottom; }
      else { p.left = Math.max(p.left, tmp.left); p.top = Math.max(p.top, tmp.top); p.right = Math.max(p.right, tmp.right); p.bottom = Math.max(p.bottom, tmp.bottom); }
    }
    int l = rtl() ? padE : padS, r = rtl() ? padS : padE;
    if (l < 0) l = padL;
    if (r < 0) r = padR;
    if (l >= 0) p.left = l;
    if (r >= 0) p.right = r;
    if (padT >= 0) p.top = padT;
    if (padB >= 0) p.bottom = padB;
    return p.left != 0 || p.top != 0 || p.right != 0 || p.bottom != 0;
  }

  private int insetL(Layer y) { int v = rtl() ? y.e : y.s; return v == INSET_UNDEFINED ? y.l : v; }
  private int insetR(Layer y) { int v = rtl() ? y.s : y.e; return v == INSET_UNDEFINED ? y.r : v; }
  private static int resolveGravity(int gravity, int width, int height, int iw, int ih) {
    if ((gravity & Gravity.HORIZONTAL_GRAVITY_MASK) == 0) gravity |= width < 0 ? Gravity.FILL_HORIZONTAL : Gravity.START;
    if ((gravity & Gravity.VERTICAL_GRAVITY_MASK) == 0) gravity |= height < 0 ? Gravity.FILL_VERTICAL : Gravity.TOP;
    if (width < 0 && iw < 0) gravity |= Gravity.FILL_HORIZONTAL;
    if (height < 0 && ih < 0) gravity |= Gravity.FILL_VERTICAL;
    return gravity;
  }
  void updateBounds(Rect bounds) {
    int pl = 0, pt = 0, pr = 0, pb = 0;
    int dir = getLayoutDirection();
    for (Layer y : layers) {
      Drawable d = y.d;
      if (d == null) continue;
      container.set(bounds.left + insetL(y) + pl, bounds.top + y.t + pt, bounds.right - insetR(y) - pr, bounds.bottom - y.b - pb);
      int iw = d.getIntrinsicWidth(), ih = d.getIntrinsicHeight();
      int g = resolveGravity(y.gravity, y.width, y.height, iw, ih);
      Gravity.apply(g, y.width < 0 ? iw : y.width, y.height < 0 ? ih : y.height, container, out, dir);
      d.setBounds(out);
      if (paddingMode == PADDING_MODE_NEST && d.getPadding(tmp)) { pl += tmp.left; pt += tmp.top; pr += tmp.right; pb += tmp.bottom; }
    }
  }
  @Override protected void onBoundsChange(Rect b) { updateBounds(b); }
  @Override public boolean onLayoutDirectionChanged(int dir) {
    for (Layer y : layers) if (y.d != null) y.d.setLayoutDirection(dir);
    updateBounds(getBounds());
    return true;
  }
  @Override public int getIntrinsicWidth() {
    int w = -1, pl = 0, pr = 0;
    for (Layer y : layers) {
      if (y.d == null) continue;
      int min = y.width < 0 ? y.d.getIntrinsicWidth() : y.width;
      int lw = min < 0 ? -1 : min + insetL(y) + insetR(y) + pl + pr;
      w = Math.max(w, lw);
      if (paddingMode == PADDING_MODE_NEST && y.d.getPadding(tmp)) { pl += tmp.left; pr += tmp.right; }
    }
    return w;
  }
  @Override public int getIntrinsicHeight() {
    int h = -1, pt = 0, pb = 0;
    for (Layer y : layers) {
      if (y.d == null) continue;
      int min = y.height < 0 ? y.d.getIntrinsicHeight() : y.height;
      int lh = min < 0 ? -1 : min + y.t + y.b + pt + pb;
      h = Math.max(h, lh);
      if (paddingMode == PADDING_MODE_NEST && y.d.getPadding(tmp)) { pt += tmp.top; pb += tmp.bottom; }
    }
    return h;
  }

  @Override public void draw(Canvas c) { for (Layer y : layers) if (y.d != null) y.d.draw(c); }
  @Override public void setAlpha(int a) { alpha = a; for (Layer y : layers) if (y.d != null) y.d.setAlpha(a); }
  @Override public int getAlpha() { return alpha; }
  @Override public void setColorFilter(ColorFilter cf) { for (Layer y : layers) if (y.d != null) y.d.setColorFilter(cf); }
  @Override public void setTintList(android.content.res.ColorStateList tint) { for (Layer y : layers) if (y.d != null) y.d.setTintList(tint); }
  @Override public void setTintMode(PorterDuff.Mode mode) { for (Layer y : layers) if (y.d != null) y.d.setTintMode(mode); }
  @Override public void setDither(boolean dither) { for (Layer y : layers) if (y.d != null) y.d.setDither(dither); }
  public void setOpacity(int opacity) { opacityOverride = opacity; }
  @Override public int getOpacity() {
    if (opacityOverride != PixelFormat.UNKNOWN) return opacityOverride;
    int op = PixelFormat.TRANSPARENT;
    boolean first = true;
    for (Layer y : layers) {
      if (y.d == null) continue;
      op = first ? y.d.getOpacity() : Drawable.resolveOpacity(op, y.d.getOpacity());
      first = false;
    }
    return op;
  }
  @Override public boolean isStateful() { for (Layer y : layers) if (y.d != null && y.d.isStateful()) return true; return false; }
  @Override public boolean hasFocusStateSpecified() { for (Layer y : layers) if (y.d != null && y.d.hasFocusStateSpecified()) return true; return false; }
  @Override protected boolean onStateChange(int[] s) {
    boolean ch = false;
    for (Layer y : layers) if (y.d != null && y.d.isStateful() && y.d.setState(s)) ch = true;
    if (ch) updateBounds(getBounds());
    return ch;
  }
  @Override protected boolean onLevelChange(int l) {
    boolean ch = false;
    for (Layer y : layers) if (y.d != null && y.d.setLevel(l)) ch = true;
    if (ch) updateBounds(getBounds());
    return ch;
  }
  @Override public boolean setVisible(boolean v, boolean r) { boolean c = super.setVisible(v, r); for (Layer y : layers) if (y.d != null) y.d.setVisible(v, r); return c; }
  @Override public void jumpToCurrentState() { for (Layer y : layers) if (y.d != null) y.d.jumpToCurrentState(); }
  @Override public void setHotspot(float x, float y) { for (Layer l : layers) if (l.d != null) l.d.setHotspot(x, y); }
  @Override public void getOutline(Outline o) {
    for (Layer y : layers) if (y.d != null) { y.d.getOutline(o); if (!o.isEmpty()) return; }
  }
  public void invalidateDrawable(Drawable who) { invalidateSelf(); }
  public void scheduleDrawable(Drawable who, Runnable what, long when) { scheduleSelf(what, when); }
  public void unscheduleDrawable(Drawable who, Runnable what) { unscheduleSelf(what); }
  @Override public Drawable mutate() { for (Layer y : layers) if (y.d != null) y.d.mutate(); return this; }
}
