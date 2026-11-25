package android.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.*;
import java.util.ArrayList;

/**
 * A vector drawable: groups (transforms), paths (SVG path data, fill and stroke, trimming) and clip paths in a
 * viewport, drawn scaled to the bounds. Built from {@code <vector>} XML by a2w.Drawables.
 */
public class VectorDrawable extends Drawable {
  /** a fill or stroke: a color list or a gradient shader (in viewport coordinates) */
  public static final class Fill {
    public ColorStateList colors;
    public Shader shader;
    public Fill(ColorStateList c) { colors = c; }
    public Fill(Shader s) { shader = s; }
  }
  public static class Group {
    public String name;
    public float rotate, pivotX, pivotY, scaleX = 1, scaleY = 1, translateX, translateY;
    public final ArrayList<Object> children = new ArrayList<>();
    final Matrix matrix() {
      Matrix m = new Matrix();
      m.postTranslate(-pivotX, -pivotY);
      m.postScale(scaleX, scaleY);
      m.postRotate(rotate, 0, 0);
      m.postTranslate(translateX + pivotX, translateY + pivotY);
      return m;
    }
  }
  public static class VPath {
    public String name;
    public Path path = new Path();
    public boolean clip;
    public Fill fill, stroke;
    public float strokeWidth, fillAlpha = 1, strokeAlpha = 1, trimStart = 0, trimEnd = 1, trimOffset, miter = 4;
    public Paint.Cap cap = Paint.Cap.BUTT;
    public Paint.Join join = Paint.Join.MITER;
    public boolean evenOdd;
  }

  public Group root = new Group();
  public float viewportWidth, viewportHeight;
  public int width, height;   // intrinsic size, px
  public float rootAlpha = 1;
  int mAlpha = 255;
  ColorFilter mFilter;
  private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

  public VectorDrawable() {}

  @Override public void draw(Canvas c) {
    Rect b = getBounds();
    if (b.isEmpty() || viewportWidth <= 0 || viewportHeight <= 0) return;
    int sc = c.save();
    c.translate(b.left, b.top);
    if (mAutoMirrored && getLayoutDirection() == 1) { c.translate(b.width(), 0); c.scale(-1, 1); }
    c.scale(b.width() / viewportWidth, b.height() / viewportHeight);
    drawGroup(c, root, rootAlpha * mAlpha / 255f);
    c.restoreToCount(sc);
  }
  private void drawGroup(Canvas c, Group g, float alpha) {
    int sc = c.save();
    c.concat(g.matrix());
    for (Object o : g.children) {
      if (o instanceof Group) drawGroup(c, (Group) o, alpha);
      else {
        VPath p = (VPath) o;
        if (p.clip) { c.clipPath(p.path); continue; }
        Path path = p.path;
        if (p.trimStart != 0 || p.trimEnd != 1) continue;   // (trimmed paths are animation states; drawn when whole)
        if (p.fill != null) paint(c, path, p, p.fill, p.fillAlpha * alpha, false);
        if (p.stroke != null && p.strokeWidth > 0) paint(c, path, p, p.stroke, p.strokeAlpha * alpha, true);
      }
    }
    c.restoreToCount(sc);
  }
  private void paint(Canvas c, Path path, VPath p, Fill f, float alpha, boolean stroke) {
    mPaint.reset();
    mPaint.setAntiAlias(true);
    mPaint.setStyle(stroke ? Paint.Style.STROKE : Paint.Style.FILL);
    if (stroke) { mPaint.setStrokeWidth(p.strokeWidth); mPaint.setStrokeCap(p.cap); mPaint.setStrokeJoin(p.join); mPaint.setStrokeMiter(p.miter); }
    int a;
    if (f.shader != null) { mPaint.setShader(f.shader); mPaint.setColor(0xff000000); a = 255; }
    else {
      int col = f.colors.getColorForState(getState(), f.colors.getDefaultColor());
      mPaint.setColor(col);
      a = Color.alpha(col);
      if (a == 0) return;
    }
    mPaint.setAlpha(Math.round(a * Math.max(0, Math.min(1, alpha))));
    if (mFilter != null) mPaint.setColorFilter(mFilter);
    path.setFillType(p.evenOdd ? Path.FillType.EVEN_ODD : Path.FillType.WINDING);
    c.drawPath(path, mPaint);
  }

  @Override public void setAlpha(int a) { if (mAlpha != a) { mAlpha = a; invalidateSelf(); } }
  @Override public int getAlpha() { return mAlpha; }
  @Override public void setColorFilter(ColorFilter cf) { mFilter = cf; invalidateSelf(); }
  @Override public ColorFilter getColorFilter() { return mFilter; }
  @Override public int getOpacity() { return PixelFormat.TRANSLUCENT; }
  @Override public int getIntrinsicWidth() { return width; }
  @Override public int getIntrinsicHeight() { return height; }
  @Override public boolean isStateful() { return super.isStateful() || stateful(root); }
  private static boolean stateful(Group g) {
    for (Object o : g.children) {
      if (o instanceof Group) { if (stateful((Group) o)) return true; }
      else {
        VPath p = (VPath) o;
        if (p.fill != null && p.fill.colors != null && p.fill.colors.isStateful()) return true;
        if (p.stroke != null && p.stroke.colors != null && p.stroke.colors.isStateful()) return true;
      }
    }
    return false;
  }
  @Override protected boolean onStateChange(int[] s) { if (stateful(root)) { invalidateSelf(); return true; } return false; }
  @Override public boolean canApplyTheme() { return false; }
  @Override public Drawable mutate() { return this; }
  /** the named group or path (for animations) */
  public Object getTargetByName(String name) { return find(root, name); }
  private static Object find(Group g, String name) {
    if (name.equals(g.name)) return g;
    for (Object o : g.children) {
      if (o instanceof Group) { Object r = find((Group) o, name); if (r != null) return r; }
      else if (name.equals(((VPath) o).name)) return o;
    }
    return null;
  }

  @Override public ConstantState getConstantState() {
    final VectorDrawable src = this;
    return new ConstantState() {
      @Override public Drawable newDrawable() {
        VectorDrawable d = new VectorDrawable();
        d.root = src.root; d.viewportWidth = src.viewportWidth; d.viewportHeight = src.viewportHeight;
        d.width = src.width; d.height = src.height; d.rootAlpha = src.rootAlpha; d.mAutoMirrored = src.mAutoMirrored;
        if (src.mTintList != null) { d.mTintMode = src.mTintMode; d.setTintList(src.mTintList); }
        return d;
      }
      @Override public int getChangingConfigurations() { return 0; }
    };
  }
}
