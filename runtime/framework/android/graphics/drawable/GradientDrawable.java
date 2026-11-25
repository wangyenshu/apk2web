package android.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.*;

/**
 * android.graphics.drawable.GradientDrawable: a rectangle (with corner radii), oval, line or ring, filled with a
 * color (state list) or a linear, radial or sweep gradient, and an optionally dashed stroke.
 */
public class GradientDrawable extends Drawable {
  public static final int RECTANGLE = 0, OVAL = 1, LINE = 2, RING = 3;
  public static final int LINEAR_GRADIENT = 0, RADIAL_GRADIENT = 1, SWEEP_GRADIENT = 2;
  public enum Orientation { TOP_BOTTOM, TR_BL, RIGHT_LEFT, BR_TL, BOTTOM_TOP, BL_TR, LEFT_RIGHT, TL_BR }

  int shape = RECTANGLE, gradientType = LINEAR_GRADIENT;
  Orientation orientation = Orientation.TOP_BOTTOM;
  int[] colors; float[] positions;
  ColorStateList solidList;
  Integer solid;
  int strokeWidth = -1, strokeColor; ColorStateList strokeList; float dashWidth, dashGap;
  float radius; float[] radii;
  float gradientRadius = 0.5f, centerX = 0.5f, centerY = 0.5f;
  int innerRadius = -1, thickness = -1;
  float innerRadiusRatio = 3, thicknessRatio = 9;
  Rect padding; int width = -1, height = -1;
  int alpha = 255; ColorFilter filter;
  boolean useLevel, useLevelForShape = true;
  private final Paint fill = new Paint(Paint.ANTI_ALIAS_FLAG), stroke = new Paint(Paint.ANTI_ALIAS_FLAG);
  private final RectF rect = new RectF();

  public GradientDrawable() {}
  public GradientDrawable(Orientation o, int[] colors) { orientation = o; this.colors = colors; }

  // ---- properties
  public void setShape(int s) { shape = s; invalidateSelf(); }
  public int getShape() { return shape; }
  public void setGradientType(int t) { gradientType = t; invalidateSelf(); }
  public int getGradientType() { return gradientType; }
  public void setGradientRadius(float r) { gradientRadius = r; invalidateSelf(); }
  public float getGradientRadius() { return gradientType == RADIAL_GRADIENT ? gradientRadius : 0; }
  public void setGradientCenter(float x, float y) { centerX = x; centerY = y; invalidateSelf(); }
  public float getGradientCenterX() { return centerX; }
  public float getGradientCenterY() { return centerY; }
  public void setUseLevel(boolean u) { useLevel = u; invalidateSelf(); }
  public boolean getUseLevel() { return useLevel; }
  public void setOrientation(Orientation o) { orientation = o; invalidateSelf(); }
  public Orientation getOrientation() { return orientation; }
  public void setColors(int[] c) { setColors(c, null); }
  public void setColors(int[] c, float[] offsets) { colors = c; positions = offsets; solid = null; solidList = null; invalidateSelf(); }
  public int[] getColors() { return colors == null ? null : colors.clone(); }
  public void setColor(int c) { solid = c; solidList = null; colors = null; invalidateSelf(); }
  public void setColor(ColorStateList c) {
    solidList = c; colors = null;
    solid = c == null ? null : c.getColorForState(getState(), c.getDefaultColor());
    invalidateSelf();
  }
  public ColorStateList getColor() { return solidList != null ? solidList : solid != null ? ColorStateList.valueOf(solid) : null; }
  public void setStroke(int w, int c) { setStroke(w, c, 0, 0); }
  public void setStroke(int w, int c, float dw, float dg) { strokeWidth = w; strokeColor = c; strokeList = null; dashWidth = dw; dashGap = dg; invalidateSelf(); }
  public void setStroke(int w, ColorStateList c) { setStroke(w, c, 0, 0); }
  public void setStroke(int w, ColorStateList c, float dw, float dg) {
    strokeWidth = w; strokeList = c; dashWidth = dw; dashGap = dg;
    strokeColor = c == null ? 0 : c.getColorForState(getState(), c.getDefaultColor());
    invalidateSelf();
  }
  public void setCornerRadius(float r) { radius = r; radii = null; invalidateSelf(); }
  public float getCornerRadius() { return radius; }
  public void setCornerRadii(float[] r) { radii = r == null ? null : r.clone(); invalidateSelf(); }
  public float[] getCornerRadii() { return radii == null ? null : radii.clone(); }
  public void setSize(int w, int h) { width = w; height = h; invalidateSelf(); }
  public void setPadding(int l, int t, int r, int b) { padding = new Rect(l, t, r, b); invalidateSelf(); }
  public void setInnerRadius(int r) { innerRadius = r; invalidateSelf(); }
  public int getInnerRadius() { return innerRadius; }
  public void setInnerRadiusRatio(float r) {
    if (r <= 0) throw new IllegalArgumentException("Ratio must be greater than zero");
    innerRadiusRatio = r; invalidateSelf();
  }
  public float getInnerRadiusRatio() { return innerRadiusRatio; }
  public void setThickness(int t) { thickness = t; invalidateSelf(); }
  public int getThickness() { return thickness; }
  public void setThicknessRatio(float r) {
    if (r <= 0) throw new IllegalArgumentException("Ratio must be greater than zero");
    thicknessRatio = r; invalidateSelf();
  }
  public float getThicknessRatio() { return thicknessRatio; }
  /** (not part of the API; rings) whether the level sweeps the ring, as android:useLevel on <shape> */
  public void a2wSetUseLevelForShape(boolean u) { useLevelForShape = u; }
  @Override public int getIntrinsicWidth() { return width; }
  @Override public int getIntrinsicHeight() { return height; }
  @Override public boolean getPadding(Rect p) { if (padding != null) { p.set(padding); return true; } return super.getPadding(p); }
  @Override public void setAlpha(int a) { if (a != alpha) { alpha = a; invalidateSelf(); } }
  @Override public int getAlpha() { return alpha; }
  @Override public void setColorFilter(ColorFilter cf) { filter = cf; invalidateSelf(); }
  @Override public ColorFilter getColorFilter() { return filter; }
  @Override public void setDither(boolean dither) { fill.setDither(dither); stroke.setDither(dither); }
  @Override public int getOpacity() {
    if (alpha != 255 || shape != RECTANGLE || radius > 0 || radii != null) return PixelFormat.TRANSLUCENT;
    if (strokeWidth > 0 && (strokeColor >>> 24) != 255) return PixelFormat.TRANSLUCENT;
    if (colors != null) { for (int c : colors) if ((c >>> 24) != 255) return PixelFormat.TRANSLUCENT; return PixelFormat.OPAQUE; }
    if (solid != null) return (solid >>> 24) == 255 ? PixelFormat.OPAQUE : PixelFormat.TRANSLUCENT;
    return PixelFormat.TRANSLUCENT;
  }
  @Override public boolean isStateful() { return (solidList != null && solidList.isStateful()) || (strokeList != null && strokeList.isStateful()) || super.isStateful(); }
  @Override protected boolean onStateChange(int[] s) {
    boolean ch = false;
    if (solidList != null) { int c = solidList.getColorForState(s, solidList.getDefaultColor()); if (solid == null || c != solid) { solid = c; ch = true; } }
    if (strokeList != null) { int c = strokeList.getColorForState(s, strokeList.getDefaultColor()); if (c != strokeColor) { strokeColor = c; ch = true; } }
    if (ch) invalidateSelf();
    return ch;
  }
  @Override protected boolean onLevelChange(int l) { if (useLevel || (shape == RING && useLevelForShape)) { invalidateSelf(); return true; } return false; }
  @Override public void getOutline(Outline outline) {
    Rect b = getBounds();
    float a = alpha / 255f;
    switch (shape) {
      case RECTANGLE: {
        float r = radii != null ? radii[0] : radius;
        if (radii != null) for (float x : radii) if (x != r) { outline.setRect(b); outline.setAlpha(a); return; }
        outline.setRoundRect(b, Math.min(r, Math.min(b.width(), b.height()) * 0.5f));
        break;
      }
      case OVAL: outline.setOval(b); break;
      case LINE: { int h = Math.max(1, strokeWidth); int cy = b.centerY(); outline.setRect(b.left, cy - h / 2, b.right, cy - h / 2 + h); break; }
      default: return;
    }
    outline.setAlpha(a);
  }

  private int a(int c) { return (c & 0xffffff) | (((c >>> 24) * alpha / 255) << 24); }

  @Override public void draw(Canvas c) {
    Rect b = getBounds();
    float inset = strokeWidth > 0 ? strokeWidth * 0.5f : 0;
    rect.set(b.left + inset, b.top + inset, b.right - inset, b.bottom - inset);
    fill.setColorFilter(filter); stroke.setColorFilter(filter);
    fill.setShader(null);
    boolean hasFill = false;
    float level = useLevel ? getLevel() / 10000f : 1;
    if (colors != null && colors.length > 0) {
      hasFill = true;
      int[] cs = new int[colors.length];
      for (int i = 0; i < cs.length; i++) cs[i] = a(colors[i]);
      if (cs.length == 1) cs = new int[] {cs[0], cs[0]};
      float[] pos = positions != null && positions.length == cs.length ? positions : null;
      fill.setColor(0xff000000);
      RectF r = rect;
      if (gradientType == LINEAR_GRADIENT) {
        float x0, y0, x1, y1;
        switch (orientation) {
          case TOP_BOTTOM: x0 = r.left; y0 = r.top; x1 = x0; y1 = r.bottom; break;
          case TR_BL: x0 = r.right; y0 = r.top; x1 = r.left; y1 = r.bottom; break;
          case RIGHT_LEFT: x0 = r.right; y0 = r.top; x1 = r.left; y1 = y0; break;
          case BR_TL: x0 = r.right; y0 = r.bottom; x1 = r.left; y1 = r.top; break;
          case BOTTOM_TOP: x0 = r.left; y0 = r.bottom; x1 = x0; y1 = r.top; break;
          case BL_TR: x0 = r.left; y0 = r.bottom; x1 = r.right; y1 = r.top; break;
          case LEFT_RIGHT: x0 = r.left; y0 = r.top; x1 = r.right; y1 = y0; break;
          default: x0 = r.left; y0 = r.top; x1 = r.right; y1 = r.bottom; break;
        }
        fill.setShader(new LinearGradient(x0, y0, x0 + (x1 - x0) * level, y0 + (y1 - y0) * level, cs, pos, Shader.TileMode.CLAMP));
      } else if (gradientType == RADIAL_GRADIENT) {
        float rad = gradientRadius <= 1 ? gradientRadius * Math.min(r.width(), r.height()) : gradientRadius;
        fill.setShader(new RadialGradient(r.left + r.width() * centerX, r.top + r.height() * centerY, Math.max(1, rad * level), cs, pos, Shader.TileMode.CLAMP));
      } else {
        float cx = r.left + r.width() * centerX, cy = r.top + r.height() * centerY;
        if (useLevel && pos == null) {
          // the colors spread over the level's share of the circle
          pos = new float[cs.length + 1];
          for (int i = 0; i < cs.length; i++) pos[i] = level * i / (cs.length - 1);
          pos[cs.length] = 1;
          int[] cs2 = java.util.Arrays.copyOf(cs, cs.length + 1);
          cs2[cs.length] = cs[cs.length - 1];
          cs = cs2;
        }
        fill.setShader(new SweepGradient(cx, cy, cs, pos));
      }
    } else if (solid != null) {
      hasFill = (solid >>> 24) != 0;
      fill.setColor(a(solid));
    }
    if (strokeWidth > 0) {
      stroke.setStyle(Paint.Style.STROKE);
      stroke.setStrokeWidth(strokeWidth);
      stroke.setColor(a(strokeColor));
      stroke.setPathEffect(dashWidth > 0 ? new DashPathEffect(new float[] {dashWidth, dashGap}, 0) : null);
    }
    switch (shape) {
      case OVAL:
        if (hasFill) c.drawOval(rect, fill);
        if (strokeWidth > 0) c.drawOval(rect, stroke);
        break;
      case LINE: {
        float y = rect.centerY();
        if (strokeWidth > 0) c.drawLine(rect.left, y, rect.right, y, stroke);
        break;
      }
      case RING: {
        Path ring = ringPath(new RectF(b));
        if (hasFill) c.drawPath(ring, fill);
        if (strokeWidth > 0) c.drawPath(ring, stroke);
        break;
      }
      default: {
        if (radii != null) {
          Path p = new Path();
          p.addRoundRect(rect, radii, Path.Direction.CW);
          if (hasFill) c.drawPath(p, fill);
          if (strokeWidth > 0) c.drawPath(p, stroke);
          break;
        }
        float rad = radius;
        if (rad > 0) {
          rad = Math.min(rad, Math.min(rect.width(), rect.height()) / 2);
          if (hasFill) c.drawRoundRect(rect, rad, rad, fill);
          if (strokeWidth > 0) c.drawRoundRect(rect, rad, rad, stroke);
        } else {
          if (hasFill) c.drawRect(rect, fill);
          if (strokeWidth > 0) c.drawRect(rect, stroke);
        }
      }
    }
  }
  /** GradientDrawable.buildRing: an annulus (or, with the level, a sweep of it) centered in the bounds */
  private Path ringPath(RectF bounds) {
    float x = bounds.width() / 2f, y = bounds.height() / 2f;
    float thick = thickness != -1 ? thickness : bounds.width() / thicknessRatio;
    float rad = innerRadius != -1 ? innerRadius : bounds.width() / innerRadiusRatio;
    float sweep = useLevelForShape && useLevel ? 360f * getLevel() / 10000f : 360f;
    RectF inner = new RectF(bounds.left + x - rad, bounds.top + y - rad, bounds.left + x + rad, bounds.top + y + rad);
    RectF outer = new RectF(inner);
    outer.inset(-thick, -thick);
    Path p = new Path();
    if (sweep < 360 && sweep > -360) {
      p.setFillType(Path.FillType.EVEN_ODD);
      p.moveTo(bounds.left + x + rad, bounds.top + y);
      p.lineTo(bounds.left + x + rad + thick, bounds.top + y);
      p.arcTo(outer, 0.0f, sweep, false);
      p.arcTo(inner, sweep, -sweep, false);
      p.close();
    } else {
      p.addOval(outer, Path.Direction.CW);
      p.addOval(inner, Path.Direction.CCW);
    }
    return p;
  }
  @Override public ConstantState getConstantState() {
    final GradientDrawable src = this;
    return new ConstantState() {
      public Drawable newDrawable() {
        GradientDrawable g = new GradientDrawable();
        g.shape = src.shape; g.gradientType = src.gradientType; g.orientation = src.orientation;
        g.colors = src.colors == null ? null : src.colors.clone(); g.positions = src.positions; g.solidList = src.solidList; g.solid = src.solid;
        g.strokeWidth = src.strokeWidth; g.strokeColor = src.strokeColor; g.strokeList = src.strokeList; g.dashWidth = src.dashWidth; g.dashGap = src.dashGap;
        g.radius = src.radius; g.radii = src.radii == null ? null : src.radii.clone(); g.gradientRadius = src.gradientRadius; g.centerX = src.centerX; g.centerY = src.centerY;
        g.innerRadius = src.innerRadius; g.thickness = src.thickness; g.innerRadiusRatio = src.innerRadiusRatio; g.thicknessRatio = src.thicknessRatio;
        g.padding = src.padding == null ? null : new Rect(src.padding); g.width = src.width; g.height = src.height; g.alpha = src.alpha; g.useLevel = src.useLevel;
        g.useLevelForShape = src.useLevelForShape;
        return g;
      }
      public int getChangingConfigurations() { return 0; }
    };
  }
  private boolean mutated;
  @Override public Drawable mutate() {
    if (!mutated) { mutated = true; if (colors != null) colors = colors.clone(); if (radii != null) radii = radii.clone(); if (padding != null) padding = new Rect(padding); }
    return this;
  }
}
