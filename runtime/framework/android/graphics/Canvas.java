package android.graphics;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import org.teavm.jso.typedarrays.Float32Array;

/** Draws through a CanvasRenderingContext2D (the screen or a bitmap's canvas). */
public class Canvas {
  public static final int MATRIX_SAVE_FLAG = 1, CLIP_SAVE_FLAG = 2, HAS_ALPHA_LAYER_SAVE_FLAG = 4, FULL_COLOR_LAYER_SAVE_FLAG = 8,
      CLIP_TO_LAYER_SAVE_FLAG = 16, ALL_SAVE_FLAG = 31;
  public enum EdgeType { BW, AA }
  protected JSObject ctx;
  Bitmap bitmap;
  int width, height, density = a2w.Screen.DPI;
  int saveCount = 1;
  /** the clip's bounds in the canvas's untransformed coordinates, per save level (l, t, r, b) */
  private float[] clipStack = new float[64];
  private float[] clip;
  /** web: extra alpha applied to everything drawn (view alpha/animations), as a stack */
  private float[] alphaStack = new float[16];
  float alpha = 1;

  public Canvas() {}
  public Canvas(Bitmap b) { setBitmap(b); }
  /** web: the screen canvas */
  public Canvas(JSObject ctx, int w, int h) { this.ctx = ctx; width = w; height = h; }
  private float[] clip() { if (clip == null) clip = new float[] {0, 0, width, height}; return clip; }
  public JSObject ctx() { return ctx; }

  public void setBitmap(Bitmap b) {
    bitmap = b;
    if (b == null) { ctx = null; return; }
    ctx = b.ctx();
    b.mutable = true;
    width = b.width; height = b.height; density = b.density;
    saveCount = 1;
    clip = null;
  }
  public int getWidth() { return width; }
  public int getHeight() { return height; }
  public int getDensity() { return density; }
  public void setDensity(int d) { density = d; }
  public boolean isOpaque() { return false; }
  public boolean isHardwareAccelerated() { return false; }
  public int getMaximumBitmapWidth() { return 4096; }
  public int getMaximumBitmapHeight() { return 4096; }

  // ---------------------------------------------------------------- state
  @JSBody(params = {"c"}, script = "c.save();")
  static native void jsSave(JSObject c);
  @JSBody(params = {"c"}, script = "c.restore();")
  static native void jsRestore(JSObject c);
  @JSBody(params = {"c", "a"}, script = "c.globalAlpha = a;")
  static native void jsAlpha(JSObject c, float a);
  public int save() {
    jsSave(ctx);
    if (saveCount >= alphaStack.length) alphaStack = java.util.Arrays.copyOf(alphaStack, alphaStack.length * 2);
    alphaStack[saveCount] = alpha;
    if (4 * saveCount + 4 > clipStack.length) clipStack = java.util.Arrays.copyOf(clipStack, clipStack.length * 2);
    System.arraycopy(clip(), 0, clipStack, 4 * saveCount, 4);
    return saveCount++;
  }
  public int save(int flags) { return save(); }
  public int saveLayer(RectF b, Paint p, int f) { return saveLayerAlpha(b, p == null ? 255 : p.getAlpha(), f); }
  public int saveLayer(float l, float t, float r, float b, Paint p, int f) { return saveLayerAlpha(null, p == null ? 255 : p.getAlpha(), f); }
  public int saveLayerAlpha(RectF b, int a, int f) { int c = save(); multiplyAlpha(a / 255f); return c; }
  public int saveLayerAlpha(float l, float t, float r, float b, int a, int f) { return saveLayerAlpha(null, a, f); }
  public int saveLayer(RectF bounds, Paint paint) { return saveLayer(bounds, paint, ALL_SAVE_FLAG); }
  public int saveLayer(float left, float top, float right, float bottom, Paint paint) { return saveLayer(left, top, right, bottom, paint, ALL_SAVE_FLAG); }
  public int saveLayerAlpha(RectF bounds, int alpha) { return saveLayerAlpha(bounds, alpha, ALL_SAVE_FLAG); }
  public int saveLayerAlpha(float left, float top, float right, float bottom, int alpha) { return saveLayerAlpha(left, top, right, bottom, alpha, ALL_SAVE_FLAG); }
  /** (Z ordering of render nodes; the page draws in order) */
  public void enableZ() {}
  public void disableZ() {}
  public void restore() {
    if (saveCount <= 1) throw new IllegalStateException("Underflow in restore");
    jsRestore(ctx);
    saveCount--;
    alpha = alphaStack[saveCount];
    System.arraycopy(clipStack, 4 * saveCount, clip(), 0, 4);
  }
  public int getSaveCount() { return saveCount; }
  public void restoreToCount(int c) {
    if (c < 1) c = 1;
    while (saveCount > c) restore();
  }
  /** web: multiplies the alpha of everything drawn until the matching restore */
  public void multiplyAlpha(float a) { alpha *= a; jsAlpha(ctx, alpha); }
  public float alpha() { return alpha; }

  // ---------------------------------------------------------------- transform
  @JSBody(params = {"c", "x", "y"}, script = "c.translate(x, y);")
  static native void jsTranslate(JSObject c, float x, float y);
  @JSBody(params = {"c", "x", "y"}, script = "c.scale(x, y);")
  static native void jsScale(JSObject c, float x, float y);
  @JSBody(params = {"c", "r"}, script = "c.rotate(r);")
  static native void jsRotate(JSObject c, float r);
  @JSBody(params = {"c", "a", "b", "cc", "d", "e", "f"}, script = "c.transform(a, b, cc, d, e, f);")
  static native void jsTransform(JSObject c, float a, float b, float cc, float d, float e, float f);
  @JSBody(params = {"c", "a", "b", "cc", "d", "e", "f"}, script = "HW.cv.setMatrix(c, a, b, cc, d, e, f);")
  static native void jsSetMatrix(JSObject c, float a, float b, float cc, float d, float e, float f);
  @JSBody(params = {"c"}, script = "return HW.cv.getMatrix(c);")
  static native Float32Array jsGetMatrix(JSObject c);
  public void translate(float dx, float dy) { if (dx != 0 || dy != 0) jsTranslate(ctx, dx, dy); }
  public void scale(float sx, float sy) { jsScale(ctx, sx, sy); }
  public final void scale(float sx, float sy, float px, float py) { translate(px, py); scale(sx, sy); translate(-px, -py); }
  public void rotate(float deg) { jsRotate(ctx, (float) Math.toRadians(deg)); }
  public final void rotate(float deg, float px, float py) { translate(px, py); rotate(deg); translate(-px, -py); }
  public void skew(float sx, float sy) { jsTransform(ctx, 1, sy, sx, 1, 0, 0); }
  /** a perspective matrix becomes the affine map that agrees with it at the clip's center and two edge midpoints */
  public void concat(Matrix m) {
    if (m == null || m.isIdentity()) return;
    if (!m.isAffine()) {
      Rect cb = new Rect();
      if (!getClipBounds(cb)) return;
      Matrix inv = new Matrix();
      float[] c = {cb.exactCenterX(), cb.exactCenterY()};
      if (m.invert(inv)) inv.mapPoints(c);
      m = affineNear(m, c[0] - cb.width() / 2f, c[1] - cb.height() / 2f, c[0] + cb.width() / 2f, c[1] + cb.height() / 2f);
      if (m == null) return;
    }
    jsTransform(ctx, m.m[0], m.m[3], m.m[1], m.m[4], m.m[2], m.m[5]);
  }
  /**
   * (not part of the API) the affine map agreeing with a perspective matrix at the center and right and bottom
   * edge midpoints of the local rectangle l, t, r, b; null when they collapse
   */
  public static Matrix affineNear(Matrix m, float l, float t, float r, float b) {
    float cx = (l + r) / 2, cy = (t + b) / 2, hx = Math.max(1, (r - l) / 2), hy = Math.max(1, (b - t) / 2);
    float[] src = {cx, cy, cx + hx, cy, cx, cy + hy}, dst = new float[6];
    m.mapPoints(dst, src);
    Matrix a = new Matrix();
    return a.setPolyToPoly(src, 0, dst, 0, 3) ? a : null;
  }
  public void setMatrix(Matrix m) {
    if (m != null && !m.isAffine()) m = affineNear(m, 0, 0, width, height);
    if (m == null) jsSetMatrix(ctx, 1, 0, 0, 1, 0, 0); else jsSetMatrix(ctx, m.m[0], m.m[3], m.m[1], m.m[4], m.m[2], m.m[5]);
  }
  public void getMatrix(Matrix m) { float[] v = jsGetMatrix(ctx).copyToJavaArray(); m.setValues(new float[] {v[0], v[2], v[4], v[1], v[3], v[5], 0, 0, 1}); }
  public final Matrix getMatrix() { Matrix m = new Matrix(); getMatrix(m); return m; }

  // ---------------------------------------------------------------- clip
  @JSBody(params = {"c", "l", "t", "r", "b", "op"}, script = "HW.cv.clipRect(c, l, t, r, b, op);")
  static native void jsClipRect(JSObject c, float l, float t, float r, float b, int op);
  @JSBody(params = {"c", "p", "eo", "op"}, script = "HW.cv.clipPath(c, p, eo, op);")
  static native void jsClipPath(JSObject c, JSObject p, boolean evenOdd, int op);
  /** the device bounds of a local rectangle */
  private float[] mapped(float l, float t, float r, float b) {
    Matrix m = getMatrix();
    float[] p = {l, t, r, t, r, b, l, b};
    m.mapPoints(p);
    return new float[] {Math.min(Math.min(p[0], p[2]), Math.min(p[4], p[6])), Math.min(Math.min(p[1], p[3]), Math.min(p[5], p[7])),
        Math.max(Math.max(p[0], p[2]), Math.max(p[4], p[6])), Math.max(Math.max(p[1], p[3]), Math.max(p[5], p[7]))};
  }
  private boolean trackClip(float l, float t, float r, float b, Region.Op op) {
    float[] c = clip(), d = mapped(l, t, r, b);
    Region.Op o = op == null ? Region.Op.INTERSECT : op;
    // the page's canvas clip can only shrink: INTERSECT and REPLACE intersect, the rest keep the bounds
    if (o == Region.Op.INTERSECT || o == Region.Op.REPLACE) { c[0] = Math.max(c[0], d[0]); c[1] = Math.max(c[1], d[1]); c[2] = Math.min(c[2], d[2]); c[3] = Math.min(c[3], d[3]); }
    return c[2] > c[0] && c[3] > c[1];
  }
  public boolean clipRect(float l, float t, float r, float b, Region.Op op) { jsClipRect(ctx, l, t, r, b, op == null ? 1 : op.ordinal()); return trackClip(l, t, r, b, op); }
  public boolean clipRect(float l, float t, float r, float b) { jsClipRect(ctx, l, t, r, b, 1); return trackClip(l, t, r, b, Region.Op.INTERSECT); }
  public boolean clipRect(int l, int t, int r, int b) { return clipRect((float) l, (float) t, (float) r, (float) b); }
  public boolean clipRect(RectF r) { return clipRect(r.left, r.top, r.right, r.bottom); }
  public boolean clipRect(Rect r) { return clipRect(r.left, r.top, r.right, r.bottom); }
  public boolean clipRect(RectF r, Region.Op op) { return clipRect(r.left, r.top, r.right, r.bottom, op); }
  public boolean clipRect(Rect r, Region.Op op) { return clipRect(r.left, r.top, r.right, r.bottom, op); }
  public boolean clipPath(Path p, Region.Op op) {
    jsClipPath(ctx, p.js(), p.evenOdd(), op == null ? 1 : op.ordinal());
    RectF b = new RectF(); p.computeBounds(b, true);
    return trackClip(b.left, b.top, b.right, b.bottom, op);
  }
  public boolean clipOutPath(Path path) { return clipPath(path, Region.Op.DIFFERENCE); }
  public boolean clipOutRect(RectF rect) { return clipRect(rect, Region.Op.DIFFERENCE); }
  public boolean clipOutRect(Rect rect) { return clipRect(rect, Region.Op.DIFFERENCE); }
  public boolean clipOutRect(float left, float top, float right, float bottom) { return clipRect(left, top, right, bottom, Region.Op.DIFFERENCE); }
  public boolean clipOutRect(int left, int top, int right, int bottom) { return clipRect((float) left, (float) top, (float) right, (float) bottom, Region.Op.DIFFERENCE); }
  public boolean clipPath(Path p) { return clipPath(p, Region.Op.INTERSECT); }
  public boolean clipRegion(Region r, Region.Op op) { return clipPath(r.getBoundaryPath(), op); }
  public boolean clipRegion(Region r) { return clipRegion(r, Region.Op.INTERSECT); }
  /** the clip's bounds in local coordinates (rounded out) */
  public boolean getClipBounds(Rect b) {
    float[] c = clip();
    Matrix inv = new Matrix();
    if (!getMatrix().invert(inv)) { if (b != null) b.setEmpty(); return false; }
    float[] p = {c[0], c[1], c[2], c[1], c[2], c[3], c[0], c[3]};
    inv.mapPoints(p);
    float l = Math.min(Math.min(p[0], p[2]), Math.min(p[4], p[6])), t = Math.min(Math.min(p[1], p[3]), Math.min(p[5], p[7]));
    float r = Math.max(Math.max(p[0], p[2]), Math.max(p[4], p[6])), bt = Math.max(Math.max(p[1], p[3]), Math.max(p[5], p[7]));
    boolean nonEmpty = c[2] > c[0] && c[3] > c[1];
    if (b != null) { if (nonEmpty) b.set((int) Math.floor(l), (int) Math.floor(t), (int) Math.ceil(r), (int) Math.ceil(bt)); else b.setEmpty(); }
    return nonEmpty;
  }
  public final Rect getClipBounds() { Rect r = new Rect(); getClipBounds(r); return r; }
  /** whether the rectangle lies outside the clip (so drawing it draws nothing) */
  public boolean quickReject(float l, float t, float r, float b) {
    if (!(r > l && b > t)) return true;
    float[] c = clip(), d = mapped(l, t, r, b);
    return d[2] <= c[0] || d[0] >= c[2] || d[3] <= c[1] || d[1] >= c[3] || c[2] <= c[0] || c[3] <= c[1];
  }
  public boolean quickReject(RectF rect) { return quickReject(rect.left, rect.top, rect.right, rect.bottom); }
  public boolean quickReject(Path path) { RectF b = new RectF(); path.computeBounds(b, true); return path.isEmpty() || quickReject(b); }
  public boolean quickReject(RectF r, EdgeType t) { return quickReject(r); }
  public boolean quickReject(Path p, EdgeType t) { return quickReject(p); }
  public boolean quickReject(float l, float t, float r, float b, EdgeType e) { return quickReject(l, t, r, b); }
  public void setDrawFilter(DrawFilter f) {}
  public DrawFilter getDrawFilter() { return null; }

  // ---------------------------------------------------------------- drawing
  @JSBody(params = {"c", "col", "mode"}, script = "HW.cv.color(c, col, mode);")
  static native void jsColor(JSObject c, int col, int mode);
  @JSBody(params = {"c", "l", "t", "r", "b", "p"}, script = "HW.cv.rect(c, l, t, r, b, p);")
  static native void jsRect(JSObject c, float l, float t, float r, float b, JSObject p);
  @JSBody(params = {"c", "l", "t", "r", "b", "rx", "ry", "p"}, script = "HW.cv.rrect(c, l, t, r, b, rx, ry, p);")
  static native void jsRRect(JSObject c, float l, float t, float r, float b, float rx, float ry, JSObject p);
  @JSBody(params = {"c", "l", "t", "r", "b", "s", "w", "u", "p"}, script = "HW.cv.arc(c, l, t, r, b, s, w, u, p);")
  static native void jsArc(JSObject c, float l, float t, float r, float b, float start, float sweep, boolean useCenter, JSObject p);
  @JSBody(params = {"c", "pts", "p"}, script = "HW.cv.lines(c, pts, p);")
  static native void jsLines(JSObject c, Float32Array pts, JSObject p);
  @JSBody(params = {"c", "pts", "p"}, script = "HW.cv.points(c, pts, p);")
  static native void jsPoints(JSObject c, Float32Array pts, JSObject p);
  @JSBody(params = {"c", "path", "eo", "p"}, script = "HW.cv.path2d(c, path, eo, p);")
  static native void jsPath(JSObject c, JSObject path, boolean evenOdd, JSObject p);
  @JSBody(params = {"c", "img", "sx", "sy", "sw", "sh", "dx", "dy", "dw", "dh", "p"}, script = "HW.cv.image(c, img, sx, sy, sw, sh, dx, dy, dw, dh, p);")
  static native void jsImage(JSObject c, JSObject img, float sx, float sy, float sw, float sh, float dx, float dy, float dw, float dh, JSObject p);
  @JSBody(params = {"c", "s", "x", "y", "p"}, script = "HW.cv.text(c, s, x, y, p);")
  static native void jsText(JSObject c, String s, float x, float y, JSObject p);

  public void drawColor(int c) { jsColor(ctx, c, 3); }
  public void drawColor(int c, PorterDuff.Mode m) { jsColor(ctx, c, m.ordinal()); }
  public void drawColor(int color, BlendMode mode) {
    String n = mode.name();
    if (n.equals("PLUS")) n = "ADD"; else if (n.equals("MODULATE")) n = "MULTIPLY";
    for (PorterDuff.Mode m : PorterDuff.Mode.values()) if (m.name().equals(n)) { drawColor(color, m); return; }
    drawColor(color);
  }
  public void drawColor(long color) { drawColor(Paint.toArgb(color)); }
  public void drawColor(long color, BlendMode mode) { drawColor(Paint.toArgb(color), mode); }
  public void drawOval(float left, float top, float right, float bottom, Paint paint) { jsArc(ctx, left, top, right, bottom, 0, 360, false, paint.js()); }
  public void drawRoundRect(float left, float top, float right, float bottom, float rx, float ry, Paint paint) { jsRRect(ctx, left, top, right, bottom, rx, ry, paint.js()); }
  public void drawArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean useCenter, Paint paint) { jsArc(ctx, left, top, right, bottom, startAngle, sweepAngle, useCenter, paint.js()); }
  /** the area between two round rectangles (even-odd fill of both) */
  public void drawDoubleRoundRect(RectF outer, float outerRx, float outerRy, RectF inner, float innerRx, float innerRy, Paint paint) {
    Path p = new Path();
    p.setFillType(Path.FillType.EVEN_ODD);
    p.addRoundRect(outer, outerRx, outerRy, Path.Direction.CW);
    p.addRoundRect(inner, innerRx, innerRy, Path.Direction.CW);
    drawPath(p, paint);
  }
  public void drawDoubleRoundRect(RectF outer, float[] outerRadii, RectF inner, float[] innerRadii, Paint paint) {
    if (outerRadii.length != 8 || innerRadii.length != 8) throw new IllegalArgumentException("Outer and inner radii arrays must contain exactly 8 values");
    drawDoubleRoundRect(outer, outerRadii[0], outerRadii[1], inner, innerRadii[0], innerRadii[1], paint);
  }
  public void drawPatch(NinePatch patch, Rect dst, Paint paint) { patch.draw(this, dst, paint); }
  public void drawPatch(NinePatch patch, RectF dst, Paint paint) { Rect r = new Rect(); dst.roundOut(r); patch.draw(this, r, paint); }
  public void drawARGB(int a, int r, int g, int b) { drawColor(Color.argb(a, r, g, b)); }
  public void drawRGB(int r, int g, int b) { drawColor(Color.rgb(r, g, b)); }
  public void drawPaint(Paint p) { jsRect(ctx, -1e5f, -1e5f, 1e5f, 1e5f, p.js()); }
  public void drawRect(float l, float t, float r, float b, Paint p) { jsRect(ctx, l, t, r, b, p.js()); }
  public void drawRect(RectF r, Paint p) { drawRect(r.left, r.top, r.right, r.bottom, p); }
  public void drawRect(Rect r, Paint p) { drawRect(r.left, r.top, r.right, r.bottom, p); }
  public void drawRoundRect(RectF r, float rx, float ry, Paint p) { jsRRect(ctx, r.left, r.top, r.right, r.bottom, rx, ry, p.js()); }
  public void drawOval(RectF r, Paint p) { jsArc(ctx, r.left, r.top, r.right, r.bottom, 0, 360, false, p.js()); }
  public void drawCircle(float cx, float cy, float rad, Paint p) { jsArc(ctx, cx - rad, cy - rad, cx + rad, cy + rad, 0, 360, false, p.js()); }
  public void drawArc(RectF o, float start, float sweep, boolean useCenter, Paint p) { jsArc(ctx, o.left, o.top, o.right, o.bottom, start, sweep, useCenter, p.js()); }
  public void drawLine(float x0, float y0, float x1, float y1, Paint p) { jsLines(ctx, Float32Array.copyFromJavaArray(new float[] {x0, y0, x1, y1}), p.js()); }
  public void drawLines(float[] pts, int off, int count, Paint p) { float[] a = new float[count]; System.arraycopy(pts, off, a, 0, count); jsLines(ctx, Float32Array.copyFromJavaArray(a), p.js()); }
  public void drawLines(float[] pts, Paint p) { drawLines(pts, 0, pts.length, p); }
  public void drawPoint(float x, float y, Paint p) { jsPoints(ctx, Float32Array.copyFromJavaArray(new float[] {x, y}), p.js()); }
  public void drawPoints(float[] pts, int off, int count, Paint p) { float[] a = new float[count]; System.arraycopy(pts, off, a, 0, count); jsPoints(ctx, Float32Array.copyFromJavaArray(a), p.js()); }
  public void drawPoints(float[] pts, Paint p) { drawPoints(pts, 0, pts.length, p); }
  public void drawPath(Path path, Paint p) { jsPath(ctx, path.js(), path.evenOdd(), p.js()); }

  private static Paint defaultBitmapPaint;
  private static JSObject bp(Paint p) {
    if (p != null) return p.js();
    if (defaultBitmapPaint == null) defaultBitmapPaint = new Paint(Paint.FILTER_BITMAP_FLAG);
    return defaultBitmapPaint.js();
  }
  /** scale of the bitmap when drawn here (bitmap density vs canvas density) */
  private float bscale(Bitmap b) {
    if (b.density == 0 || density == 0 || b.density == density) return 1;
    return density / (float) b.density;
  }
  public void drawBitmap(Bitmap b, float left, float top, Paint p) {
    float s = bscale(b);
    jsImage(ctx, b.drawSource(p), 0, 0, b.width, b.height, left, top, b.width * s, b.height * s, bp(p));
  }
  public void drawBitmap(Bitmap b, Rect src, RectF dst, Paint p) {
    if (src == null) jsImage(ctx, b.drawSource(p), 0, 0, b.width, b.height, dst.left, dst.top, dst.width(), dst.height(), bp(p));
    else jsImage(ctx, b.drawSource(p), src.left, src.top, src.width(), src.height(), dst.left, dst.top, dst.width(), dst.height(), bp(p));
  }
  public void drawBitmap(Bitmap b, Rect src, Rect dst, Paint p) {
    if (src == null) jsImage(ctx, b.drawSource(p), 0, 0, b.width, b.height, dst.left, dst.top, dst.width(), dst.height(), bp(p));
    else jsImage(ctx, b.drawSource(p), src.left, src.top, src.width(), src.height(), dst.left, dst.top, dst.width(), dst.height(), bp(p));
  }
  public void drawBitmap(Bitmap b, Matrix m, Paint p) {
    save();
    concat(m);
    jsImage(ctx, b.drawSource(p), 0, 0, b.width, b.height, 0, 0, b.width, b.height, bp(p));
    restore();
  }
  public void drawBitmap(int[] colors, int off, int stride, float x, float y, int w, int h, boolean alpha, Paint p) {
    drawBitmap(Bitmap.createBitmap(colors, off, stride, w, h, Bitmap.Config.ARGB_8888), x, y, p);
  }
  public void drawBitmap(int[] colors, int off, int stride, int x, int y, int w, int h, boolean alpha, Paint p) { drawBitmap(colors, off, stride, (float) x, (float) y, w, h, alpha, p); }
  /** each mesh cell as two affine-mapped triangles of the bitmap (per-vertex colors are not applied) */
  public void drawBitmapMesh(Bitmap b, int mw, int mh, float[] verts, int vo, int[] colors, int co, Paint p) {
    if ((mw | mh | vo | co) < 0) throw new ArrayIndexOutOfBoundsException();
    if (mw == 0 || mh == 0) return;
    int count = (mw + 1) * (mh + 1);
    if (vo + count * 2 > verts.length) throw new ArrayIndexOutOfBoundsException();
    if (colors != null && co + count > colors.length) throw new ArrayIndexOutOfBoundsException();
    float W = b.width, H = b.height;
    for (int j = 0; j < mh; j++) for (int i = 0; i < mw; i++) {
      int k0 = j * (mw + 1) + i, k1 = k0 + 1, k2 = k0 + mw + 1, k3 = k2 + 1;
      float u0 = i * W / mw, u1 = (i + 1) * W / mw, v0 = j * H / mh, v1 = (j + 1) * H / mh;
      texTriangle(b, u0, v0, u1, v0, u0, v1, verts, vo + 2 * k0, vo + 2 * k1, vo + 2 * k2, p);
      texTriangle(b, u1, v0, u1, v1, u0, v1, verts, vo + 2 * k1, vo + 2 * k3, vo + 2 * k2, p);
    }
  }
  /** the bitmap triangle (u, v) drawn onto the vertices at v[a], v[b], v[c] */
  private void texTriangle(Bitmap bmp, float u0, float v0, float u1, float v1, float u2, float v2, float[] vs, int a, int b, int c, Paint p) {
    float x0 = vs[a], y0 = vs[a + 1], x1 = vs[b], y1 = vs[b + 1], x2 = vs[c], y2 = vs[c + 1];
    float du1 = u1 - u0, dv1 = v1 - v0, du2 = u2 - u0, dv2 = v2 - v0, det = du1 * dv2 - du2 * dv1;
    if (det == 0 || ((x1 - x0) * (y2 - y0) - (x2 - x0) * (y1 - y0)) == 0) return;
    float dx1 = x1 - x0, dy1 = y1 - y0, dx2 = x2 - x0, dy2 = y2 - y0;
    float ma = (dx1 * dv2 - dx2 * dv1) / det, mc = (dx2 * du1 - dx1 * du2) / det;
    float mb = (dy1 * dv2 - dy2 * dv1) / det, md = (dy2 * du1 - dy1 * du2) / det;
    Matrix m = new Matrix();
    m.setValues(new float[] {ma, mc, x0 - ma * u0 - mc * v0, mb, md, y0 - mb * u0 - md * v0, 0, 0, 1});
    // the clip grows half a pixel around the centroid so neighbouring triangles meet without seams
    float cx = (x0 + x1 + x2) / 3, cy = (y0 + y1 + y2) / 3;
    Path t = new Path();
    float[] q = {x0, y0, x1, y1, x2, y2};
    for (int i = 0; i < 6; i += 2) {
      float dx = q[i] - cx, dy = q[i + 1] - cy, l = (float) Math.hypot(dx, dy);
      if (l > 0) { q[i] += dx / l * 0.5f; q[i + 1] += dy / l * 0.5f; }
    }
    t.moveTo(q[0], q[1]); t.lineTo(q[2], q[3]); t.lineTo(q[4], q[5]); t.close();
    save();
    clipPath(t);
    concat(m);
    drawBitmap(bmp, 0, 0, p);
    restore();
  }
  /** web: draws any page image source (used by drawables wrapping decoded images) */
  public void drawImage(JSObject img, float sx, float sy, float sw, float sh, float dx, float dy, float dw, float dh, Paint p) {
    jsImage(ctx, img, sx, sy, sw, sh, dx, dy, dw, dh, bp(p));
  }

  public void drawText(String s, float x, float y, Paint p) { if (s != null && !s.isEmpty()) jsText(ctx, s, x, y, p.js()); }
  public void drawText(String s, int start, int end, float x, float y, Paint p) { drawText(s.substring(start, end), x, y, p); }
  public void drawText(CharSequence s, int start, int end, float x, float y, Paint p) { drawText(s.subSequence(start, end).toString(), x, y, p); }
  public void drawText(char[] t, int i, int c, float x, float y, Paint p) { drawText(new String(t, i, c), x, y, p); }
  public void drawPosText(String s, float[] pos, Paint p) { for (int i = 0; i < s.length(); i++) drawText(s.substring(i, i + 1), pos[2 * i], pos[2 * i + 1], p); }
  public void drawPosText(char[] t, int i, int c, float[] pos, Paint p) { drawPosText(new String(t, i, c), pos, p); }
  /** each character along the path, turned to its tangent (v moves it off the path) */
  public void drawTextOnPath(String s, Path path, float h, float v, Paint p) {
    if (s == null || s.isEmpty()) return;
    PathMeasure pm = new PathMeasure(path, false);
    float len = pm.getLength();
    float[] w = new float[s.length()];
    p.getTextWidths(s, w);
    float total = 0;
    for (float x : w) total += x;
    float start = h + (p.getTextAlign() == Paint.Align.CENTER ? (len - total) / 2 : p.getTextAlign() == Paint.Align.RIGHT ? len - total : 0);
    Paint.Align align = p.getTextAlign();
    p.setTextAlign(Paint.Align.LEFT);
    try {
      float[] pos = new float[2], tan = new float[2];
      float d = start;
      for (int i = 0; i < s.length(); i++) {
        if (w[i] == 0) continue;
        float mid = d + w[i] / 2;
        if (mid >= 0 && mid <= len) {
          pm.getPosTan(mid, pos, tan);
          save();
          translate(pos[0], pos[1]);
          rotate((float) Math.toDegrees(Math.atan2(tan[1], tan[0])));
          drawText(s.substring(i, i + 1), -w[i] / 2, v, p);
          restore();
        }
        d += w[i];
      }
    } finally { p.setTextAlign(align); }
  }
  public void drawTextOnPath(char[] text, int index, int count, Path path, float hOffset, float vOffset, Paint paint) { drawTextOnPath(new String(text, index, count), path, hOffset, vOffset, paint); }
  public void drawTextRun(CharSequence text, int start, int end, int contextStart, int contextEnd, float x, float y, boolean isRtl, Paint paint) { drawText(text, start, end, x, y, paint); }
  public void drawTextRun(char[] text, int index, int count, int contextIndex, int contextCount, float x, float y, boolean isRtl, Paint paint) { drawText(text, index, count, x, y, paint); }
  public void drawPicture(Picture p) { p.draw(this); }
  public void drawPicture(Picture p, RectF d) {
    save();
    translate(d.left, d.top);
    if (p.getWidth() > 0 && p.getHeight() > 0) scale(d.width() / p.getWidth(), d.height() / p.getHeight());
    drawPicture(p);
    restore();
  }
  public void drawPicture(Picture p, Rect d) { drawPicture(p, new RectF(d)); }
  /**
   * the triangles of the vertex list: with texture coordinates and a BitmapShader the bitmap is mapped onto
   * them, otherwise each is filled with the paint (vertex colors: their average, over the paint's alpha)
   */
  public void drawVertices(VertexMode mode, int vertexCount, float[] verts, int vertOffset, float[] texs, int texOffset, int[] colors, int colorOffset,
      short[] indices, int indexOffset, int indexCount, Paint paint) {
    if ((vertexCount | vertOffset | texOffset | colorOffset | indexOffset | indexCount) < 0) throw new ArrayIndexOutOfBoundsException();
    if (vertOffset + vertexCount > verts.length || (texs != null && texOffset + vertexCount > texs.length)
        || (colors != null && colorOffset + vertexCount / 2 > colors.length) || (indices != null && indexOffset + indexCount > indices.length))
      throw new ArrayIndexOutOfBoundsException();
    int n = indices != null ? indexCount : vertexCount / 2;
    java.util.function.IntUnaryOperator at = indices != null ? k -> indices[indexOffset + k] & 0xffff : k -> k;
    Bitmap tex = texs != null && paint.getShader() instanceof BitmapShader ? ((BitmapShader) paint.getShader()).bitmap : null;
    Paint fill = colors != null && tex == null ? new Paint(paint) : null;
    int tris = mode == VertexMode.TRIANGLES ? n / 3 : Math.max(0, n - 2);
    for (int k = 0; k < tris; k++) {
      int a, b, c;
      if (mode == VertexMode.TRIANGLES) { a = at.applyAsInt(3 * k); b = at.applyAsInt(3 * k + 1); c = at.applyAsInt(3 * k + 2); }
      else if (mode == VertexMode.TRIANGLE_STRIP) { a = at.applyAsInt(k); b = at.applyAsInt(k + 1); c = at.applyAsInt(k + 2); }
      else { a = at.applyAsInt(0); b = at.applyAsInt(k + 1); c = at.applyAsInt(k + 2); }
      int va = vertOffset + 2 * a, vb = vertOffset + 2 * b, vcc = vertOffset + 2 * c;
      if (tex != null) {
        texTriangle(tex, texs[texOffset + 2 * a], texs[texOffset + 2 * a + 1], texs[texOffset + 2 * b], texs[texOffset + 2 * b + 1],
            texs[texOffset + 2 * c], texs[texOffset + 2 * c + 1], verts, va, vb, vcc, null);
        continue;
      }
      Path t = new Path();
      t.moveTo(verts[va], verts[va + 1]); t.lineTo(verts[vb], verts[vb + 1]); t.lineTo(verts[vcc], verts[vcc + 1]); t.close();
      if (fill != null) {
        int c0 = colors[colorOffset + a], c1 = colors[colorOffset + b], c2 = colors[colorOffset + c];
        int al = ((c0 >>> 24) + (c1 >>> 24) + (c2 >>> 24)) / 3 * paint.getAlpha() / 255;
        int r = (((c0 >> 16) & 255) + ((c1 >> 16) & 255) + ((c2 >> 16) & 255)) / 3;
        int g = (((c0 >> 8) & 255) + ((c1 >> 8) & 255) + ((c2 >> 8) & 255)) / 3;
        int bl = ((c0 & 255) + (c1 & 255) + (c2 & 255)) / 3;
        fill.setColor(al << 24 | r << 16 | g << 8 | bl);
        drawPath(t, fill);
      } else drawPath(t, paint);
    }
  }
  public enum VertexMode { TRIANGLES, TRIANGLE_STRIP, TRIANGLE_FAN }
}
