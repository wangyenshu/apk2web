package android.graphics;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import org.teavm.jso.typedarrays.Float32Array;

/** Records commands; the page turns them into a Path2D (cached until the path changes). */
public class Path {
  public enum FillType { WINDING, EVEN_ODD, INVERSE_WINDING, INVERSE_EVEN_ODD }
  public enum Direction { CW, CCW }
  public enum Op { DIFFERENCE, INTERSECT, UNION, XOR, REVERSE_DIFFERENCE }
  static final int MOVE = 0, LINE = 1, QUAD = 2, CUBIC = 3, CLOSE = 4, RECT = 5, ELLIPSE = 6, ARC = 7, RRECT = 8;
  float[] cmd = new float[32];
  int n;
  FillType fill = FillType.WINDING;
  private JSObject cached;
  float lastX, lastY;
  boolean empty = true;

  public Path() {}
  public Path(Path src) { set(src); }
  private void add(float... v) {
    if (n + v.length > cmd.length) cmd = java.util.Arrays.copyOf(cmd, Math.max(cmd.length * 2, n + v.length));
    System.arraycopy(v, 0, cmd, n, v.length);
    n += v.length;
    cached = null;
    empty = false;
    generation++;
  }
  private int generation;
  public void reset() { n = 0; cached = null; empty = true; generation++; }
  public void rewind() { reset(); }
  public void set(Path s) { cmd = s.cmd.clone(); n = s.n; fill = s.fill; cached = null; empty = s.empty; lastX = s.lastX; lastY = s.lastY; generation++; }
  public boolean isEmpty() { return empty; }
  /** a single rectangle: one addRect, or one closed axis-aligned four-sided contour */
  public boolean isRect(RectF r) {
    if (n == 5 && cmd[0] == RECT) {
      if (r != null) r.set(Math.min(cmd[1], cmd[3]), Math.min(cmd[2], cmd[4]), Math.max(cmd[1], cmd[3]), Math.max(cmd[2], cmd[4]));
      return true;
    }
    float[] e = expanded();
    // MOVE + 3 or 4 LINEs (+ CLOSE)
    int i = 0;
    if (e.length < 3 || e[0] != MOVE) return false;
    float[] xs = new float[5], ys = new float[5];
    int k = 0;
    xs[k] = e[1]; ys[k++] = e[2]; i = 3;
    while (i < e.length && e[i] == LINE && k < 5) { xs[k] = e[i + 1]; ys[k++] = e[i + 2]; i += 3; }
    boolean closed = i < e.length && e[i] == CLOSE;
    if (closed) i++;
    if (i != e.length) return false;
    if (k == 5 && xs[4] == xs[0] && ys[4] == ys[0]) k = 4;
    if (k != 4 || (!closed && !(xs[0] == xs[3] || ys[0] == ys[3]))) return false;
    for (int j = 0; j < 4; j++) {
      int q = (j + 1) % 4;
      boolean h = ys[j] == ys[q] && xs[j] != xs[q], v = xs[j] == xs[q] && ys[j] != ys[q];
      if (!h && !v) return false;
      int q2 = (j + 2) % 4;
      boolean h2 = ys[q] == ys[q2];
      if (h == h2) return false;   // sides must alternate
    }
    if (r != null) r.set(Math.min(Math.min(xs[0], xs[1]), xs[2]), Math.min(Math.min(ys[0], ys[1]), ys[2]), Math.max(Math.max(xs[0], xs[1]), xs[2]), Math.max(Math.max(ys[0], ys[1]), ys[2]));
    return true;
  }
  public FillType getFillType() { return fill; }
  public void setFillType(FillType f) { fill = f; }
  public boolean isInverseFillType() { return fill == FillType.INVERSE_WINDING || fill == FillType.INVERSE_EVEN_ODD; }
  public void toggleInverseFillType() { fill = FillType.values()[fill.ordinal() ^ 2]; }
  public void incReserve(int extra) {}
  public void moveTo(float x, float y) { add(MOVE, x, y); lastX = x; lastY = y; }
  public void rMoveTo(float dx, float dy) { moveTo(lastX + dx, lastY + dy); }
  public void lineTo(float x, float y) { add(LINE, x, y); lastX = x; lastY = y; }
  public void rLineTo(float dx, float dy) { lineTo(lastX + dx, lastY + dy); }
  public void quadTo(float x1, float y1, float x2, float y2) { add(QUAD, x1, y1, x2, y2); lastX = x2; lastY = y2; }
  public void rQuadTo(float x1, float y1, float x2, float y2) { quadTo(lastX + x1, lastY + y1, lastX + x2, lastY + y2); }
  public void cubicTo(float x1, float y1, float x2, float y2, float x3, float y3) { add(CUBIC, x1, y1, x2, y2, x3, y3); lastX = x3; lastY = y3; }
  public void rCubicTo(float x1, float y1, float x2, float y2, float x3, float y3) { cubicTo(lastX + x1, lastY + y1, lastX + x2, lastY + y2, lastX + x3, lastY + y3); }
  public void arcTo(RectF o, float start, float sweep, boolean forceMoveTo) {
    add(ARC, o.left, o.top, o.right, o.bottom, start, sweep, forceMoveTo ? 1 : 0);
    double e = Math.toRadians(start + sweep);
    lastX = (float) ((o.left + o.right) / 2 + (o.right - o.left) / 2 * Math.cos(e));
    lastY = (float) ((o.top + o.bottom) / 2 + (o.bottom - o.top) / 2 * Math.sin(e));
  }
  public void arcTo(RectF o, float start, float sweep) { arcTo(o, start, sweep, false); }
  public void close() { add(CLOSE); }
  public void addRect(RectF r, Direction d) { addRect(r.left, r.top, r.right, r.bottom, d); }
  public void addRect(float l, float t, float r, float b, Direction d) {
    if (d == Direction.CCW) { moveTo(l, t); lineTo(l, b); lineTo(r, b); lineTo(r, t); close(); }
    else add(RECT, l, t, r, b);
  }
  public void addOval(RectF o, Direction d) { add(ELLIPSE, o.left, o.top, o.right, o.bottom, 0, 360, d == Direction.CCW ? 1 : 0); }
  public void addCircle(float x, float y, float r, Direction d) { add(ELLIPSE, x - r, y - r, x + r, y + r, 0, 360, d == Direction.CCW ? 1 : 0); }
  public void addArc(RectF o, float start, float sweep) { add(ARC, o.left, o.top, o.right, o.bottom, start, sweep, 1); }
  public void addRoundRect(RectF r, float rx, float ry, Direction d) {
    if (d == Direction.CCW) addRoundRect(r, new float[] {rx, ry, rx, ry, rx, ry, rx, ry}, d);
    else add(RRECT, r.left, r.top, r.right, r.bottom, rx, ry);
  }
  /** corners (x, y radius pairs: top-left, top-right, bottom-right, bottom-left), scaled down to fit like SkRRect */
  public void addRoundRect(RectF r, float[] radii, Direction d) {
    if (radii.length < 8) throw new ArrayIndexOutOfBoundsException("radii[] needs 8 values");
    float[] q = new float[8];
    for (int i = 0; i < 8; i++) q[i] = Math.max(0, radii[i]);
    boolean same = true;
    for (int i = 2; i < 8; i++) same &= q[i] == q[i & 1];
    if (same && d != Direction.CCW) { add(RRECT, r.left, r.top, r.right, r.bottom, q[0], q[1]); return; }
    float w = r.width(), h = r.height(), sc = 1;
    if (q[0] + q[2] > w) sc = Math.min(sc, w / (q[0] + q[2]));
    if (q[4] + q[6] > w) sc = Math.min(sc, w / (q[4] + q[6]));
    if (q[1] + q[7] > h) sc = Math.min(sc, h / (q[1] + q[7]));
    if (q[3] + q[5] > h) sc = Math.min(sc, h / (q[3] + q[5]));
    for (int i = 0; i < 8; i++) q[i] *= sc;
    float l = r.left, t = r.top, rt = r.right, b = r.bottom;
    if (d != Direction.CCW) {
      moveTo(l + q[0], t);
      lineTo(rt - q[2], t); corner(rt, t, rt, t + q[3]);
      lineTo(rt, b - q[5]); corner(rt, b, rt - q[4], b);
      lineTo(l + q[6], b); corner(l, b, l, b - q[7]);
      lineTo(l, t + q[1]); corner(l, t, l + q[0], t);
    } else {
      moveTo(l + q[0], t);
      corner(l, t, l, t + q[1]); lineTo(l, b - q[7]);
      corner(l, b, l + q[6], b); lineTo(rt - q[4], b);
      corner(rt, b, rt, b - q[5]); lineTo(rt, t + q[3]);
      corner(rt, t, rt - q[2], t);
    }
    close();
  }
  private static final float KAPPA = 0.5522847498f;
  /** a quarter ellipse from the current point to (x, y) around the corner (cx, cy) */
  private void corner(float cx, float cy, float x, float y) {
    float x0 = lastX, y0 = lastY;
    if (x0 == x && y0 == y) return;
    cubicTo(x0 + KAPPA * (cx - x0), y0 + KAPPA * (cy - y0), x + KAPPA * (cx - x), y + KAPPA * (cy - y), x, y);
  }
  public void addPath(Path p) { for (int i = 0; i < p.n; i++) add(p.cmd[i]); }
  public void addPath(Path p, float dx, float dy) { Path c = new Path(p); c.offset(dx, dy); addPath(c); }
  public void addPath(Path p, Matrix m) { Path c = new Path(p); c.transform(m); addPath(c); }
  public void offset(float dx, float dy) { Matrix m = new Matrix(); m.setTranslate(dx, dy); transform(m); }
  public void offset(float dx, float dy, Path dst) { if (dst != null) { dst.set(this); dst.offset(dx, dy); } else offset(dx, dy); }
  /** moves the path's last point (a moveTo when the path is empty) */
  public void setLastPoint(float x, float y) {
    if (n == 0) { moveTo(x, y); return; }
    int i = 0, last = -1;
    while (i < n) { last = i; i += 1 + args((int) cmd[i]); }
    int c = (int) cmd[last];
    if (c == MOVE || c == LINE || c == QUAD || c == CUBIC) {
      int a = args(c);
      cmd[last + a - 1] = x; cmd[last + a] = y;
      lastX = x; lastY = y; cached = null; generation++;
    } else lineTo(x, y);
  }
  public void transform(Matrix m, Path dst) { if (dst != null) { dst.set(this); dst.transform(m); } else transform(m); }
  /** points mapped by the matrix: rectangles, ovals and arcs become lines and curves unless it only scales up and moves */
  public void transform(Matrix m) {
    float[] mm = m.m;
    if (!(mm[1] == 0 && mm[3] == 0 && mm[6] == 0 && mm[7] == 0 && mm[8] == 1 && mm[0] > 0 && mm[4] > 0)) {
      cmd = expanded(); n = cmd.length;
    } else {
      int i = 0;
      while (i < n) {
        int c = (int) cmd[i];
        if (c == RRECT) { cmd[i + 5] *= mm[0]; cmd[i + 6] *= mm[4]; }
        i += 1 + args(c);
      }
    }
    float[] p = new float[2];
    int i = 0;
    while (i < n) {
      int c = (int) cmd[i];
      int args = c == MOVE || c == LINE ? 2 : c == QUAD ? 4 : c == CUBIC ? 6 : c == CLOSE ? 0 : c == RECT ? 4 : c == RRECT ? 6 : 7;
      int pts = c == RECT || c == ELLIPSE || c == ARC || c == RRECT ? 2 : args / 2;
      for (int k = 0; k < pts; k++) { p[0] = cmd[i + 1 + 2 * k]; p[1] = cmd[i + 2 + 2 * k]; m.mapPoints(p); cmd[i + 1 + 2 * k] = p[0]; cmd[i + 2 + 2 * k] = p[1]; }
      i += 1 + args;
    }
    p[0] = lastX; p[1] = lastY; m.mapPoints(p); lastX = p[0]; lastY = p[1];
    cached = null;
    generation++;
  }

  /** the commands with rectangles, ovals, round rectangles and arcs as moves, lines and cubic curves */
  float[] expanded() {
    Path o = new Path();
    int i = 0;
    while (i < n) {
      int c = (int) cmd[i];
      float[] a = java.util.Arrays.copyOfRange(cmd, i + 1, i + 1 + args(c));
      i += 1 + args(c);
      switch (c) {
        case MOVE: o.moveTo(a[0], a[1]); break;
        case LINE: o.lineTo(a[0], a[1]); break;
        case QUAD: o.quadTo(a[0], a[1], a[2], a[3]); break;
        case CUBIC: o.cubicTo(a[0], a[1], a[2], a[3], a[4], a[5]); break;
        case CLOSE: o.close(); break;
        case RECT: o.moveTo(a[0], a[1]); o.lineTo(a[2], a[1]); o.lineTo(a[2], a[3]); o.lineTo(a[0], a[3]); o.close(); break;
        case RRECT: o.addRoundRect(new RectF(a[0], a[1], a[2], a[3]), new float[] {a[4], a[5], a[4], a[5], a[4], a[5], a[4], a[5]}, Direction.CW, true); break;
        case ELLIPSE: o.arcCubics(a[0], a[1], a[2], a[3], 0, a[6] != 0 ? -360 : 360, true); o.close(); break;
        default: o.arcCubics(a[0], a[1], a[2], a[3], a[4], a[5], a[6] != 0 || o.n == 0); break;
      }
    }
    return java.util.Arrays.copyOf(o.cmd, o.n);
  }
  /** addRoundRect with explicit corners always (for expanded()) */
  private void addRoundRect(RectF r, float[] radii, Direction d, boolean explicit) {
    float rx = Math.min(radii[0], r.width() / 2), ry = Math.min(radii[1], r.height() / 2);
    float l = r.left, t = r.top, rt = r.right, b = r.bottom;
    moveTo(l + rx, t);
    lineTo(rt - rx, t); corner(rt, t, rt, t + ry);
    lineTo(rt, b - ry); corner(rt, b, rt - rx, b);
    lineTo(l + rx, b); corner(l, b, l, b - ry);
    lineTo(l, t + ry); corner(l, t, l + rx, t);
    close();
  }
  /** an elliptical arc as cubic curves of at most 90 degrees, started with a move or a line */
  private void arcCubics(float l, float t, float r, float b, float start, float sweep, boolean move) {
    double cx = (l + r) / 2.0, cy = (t + b) / 2.0, rx = (r - l) / 2.0, ry = (b - t) / 2.0;
    double a0 = Math.toRadians(start);
    float sx = (float) (cx + rx * Math.cos(a0)), sy = (float) (cy + ry * Math.sin(a0));
    if (move) moveTo(sx, sy); else lineTo(sx, sy);
    int segs = (int) Math.ceil(Math.abs(sweep) / 90 - 1e-6);
    if (segs == 0) return;
    double step = Math.toRadians(sweep) / segs, k = 4.0 / 3 * Math.tan(step / 4);
    for (int i = 0; i < segs; i++) {
      double p = a0 + i * step, q = p + step;
      double c0 = Math.cos(p), s0 = Math.sin(p), c1 = Math.cos(q), s1 = Math.sin(q);
      cubicTo((float) (cx + rx * (c0 - k * s0)), (float) (cy + ry * (s0 + k * c0)), (float) (cx + rx * (c1 + k * s1)), (float) (cy + ry * (s1 - k * c1)),
          (float) (cx + rx * c1), (float) (cy + ry * s1));
    }
  }

  // ---- boolean operations
  public boolean op(Path path, Op op) { return op(this, path, op); }
  /**
   * path1 op path2 by scan beams: both are flattened to polygons and cut at every vertex and crossing height; each
   * beam's inside spans become trapezoids, joined down the beams while they follow the same pair of edges
   */
  public boolean op(Path path1, Path path2, Op op) {
    java.util.ArrayList<double[]> e = new java.util.ArrayList<>();   // x0, y0, x1, y1 (y0 < y1), direction, path
    edges(e, path1, 0);
    edges(e, path2, 1);
    boolean eo1 = path1.evenOdd(), eo2 = path2.evenOdd(), inv1 = path1.isInverseFillType(), inv2 = path2.isInverseFillType();
    int m = e.size();
    double[] ys = new double[2 * m + 16];
    int ny = 0;
    for (double[] g : e) { ys = grow(ys, ny + 2); ys[ny++] = g[1]; ys[ny++] = g[3]; }
    for (int i = 0; i < m; i++) {
      double[] a = e.get(i);
      for (int j = i + 1; j < m; j++) {
        double[] b = e.get(j);
        if (a[3] <= b[1] || b[3] <= a[1]) continue;
        if (Math.max(a[0], a[2]) < Math.min(b[0], b[2]) || Math.max(b[0], b[2]) < Math.min(a[0], a[2])) continue;
        double y = crossY(a, b);
        if (!Double.isNaN(y)) { ys = grow(ys, ny + 1); ys[ny++] = y; }
      }
    }
    java.util.Arrays.sort(ys, 0, ny);
    Path out = new Path();
    java.util.HashMap<Long, Chain> open = new java.util.HashMap<>();   // (left edge, right edge) -> the polygon growing down them
    double prevB = Double.NaN;
    for (int k = 0; k + 1 < ny; k++) {
      double ya = ys[k], yb = ys[k + 1];
      if (yb - ya < 1e-7) continue;
      double ym = (ya + yb) / 2;
      java.util.ArrayList<Integer> act = new java.util.ArrayList<>();
      for (int i = 0; i < m; i++) { double[] g = e.get(i); if (g[1] < ym && g[3] > ym) act.add(i); }
      act.sort((p, q) -> Double.compare(xAt(e.get(p), ym), xAt(e.get(q), ym)));
      java.util.HashMap<Long, Chain> next = new java.util.HashMap<>();
      int w1 = 0, w2 = 0, left = -1;
      boolean in = keep(inv1, inv2, op);
      if (in) { for (Chain ch : open.values()) emit(out, ch); open.clear(); prevB = yb; continue; }   // unbounded results are not kept
      for (int idx : act) {
        double[] g = e.get(idx);
        if (g[5] == 0) w1 += (int) g[4]; else w2 += (int) g[4];
        boolean a = (eo1 ? (w1 & 1) != 0 : w1 != 0) != inv1, b = (eo2 ? (w2 & 1) != 0 : w2 != 0) != inv2;
        boolean now = combine(a, b, op);
        if (now && !in) left = idx;
        else if (!now && in) {
          long key = (long) left << 32 | idx;
          double[] L = e.get(left), R = g;
          Chain ch = ya == prevB ? open.remove(key) : null;
          if (ch == null) { ch = new Chain(); ch.add(xAt(L, ya), xAt(R, ya), ya); }
          ch.add(xAt(L, yb), xAt(R, yb), yb);
          next.put(key, ch);
        }
        in = now;
      }
      for (Chain ch : open.values()) emit(out, ch);
      open = next;
      prevB = yb;
    }
    for (Chain ch : open.values()) emit(out, ch);
    set(out);
    fill = FillType.WINDING;
    return true;
  }
  private static boolean keep(boolean a, boolean b, Op op) { return combine(a, b, op); }
  private static boolean combine(boolean a, boolean b, Op op) {
    switch (op) {
      case DIFFERENCE: return a && !b;
      case INTERSECT: return a && b;
      case UNION: return a || b;
      case XOR: return a != b;
      default: return b && !a;
    }
  }
  private static double[] grow(double[] a, int need) { return need <= a.length ? a : java.util.Arrays.copyOf(a, Math.max(need, a.length * 2)); }
  /** a polygon between two edges: left and right x per height, top down */
  private static final class Chain {
    double[] l = new double[8], r = new double[8], y = new double[8];
    int n;
    void add(double xl, double xr, double yy) {
      if (n == y.length) { l = java.util.Arrays.copyOf(l, n * 2); r = java.util.Arrays.copyOf(r, n * 2); y = java.util.Arrays.copyOf(y, n * 2); }
      l[n] = xl; r[n] = xr; y[n++] = yy;
    }
  }
  private static double xAt(double[] g, double y) { return g[0] + (y - g[1]) * (g[2] - g[0]) / (g[3] - g[1]); }
  /** the height where two edges cross inside both, or NaN */
  private static double crossY(double[] a, double[] b) {
    double ax = a[2] - a[0], ay = a[3] - a[1], bx = b[2] - b[0], by = b[3] - b[1];
    double den = ax * by - ay * bx;
    if (Math.abs(den) < 1e-12) return Double.NaN;
    double t = ((b[0] - a[0]) * by - (b[1] - a[1]) * bx) / den, u = ((b[0] - a[0]) * ay - (b[1] - a[1]) * ax) / den;
    if (t <= 1e-9 || t >= 1 - 1e-9 || u <= 1e-9 || u >= 1 - 1e-9) return Double.NaN;
    return a[1] + t * ay;
  }
  private static void edges(java.util.ArrayList<double[]> out, Path p, int which) {
    for (Contour c : p.contours(0.1f)) {
      int k = c.n / 2;
      if (k < 2) continue;
      for (int i = 0; i < k; i++) {
        int j = (i + 1) % k;
        double x0 = c.p[2 * i], y0 = c.p[2 * i + 1], x1 = c.p[2 * j], y1 = c.p[2 * j + 1];
        if (y0 == y1) continue;
        out.add(y0 < y1 ? new double[] {x0, y0, x1, y1, 1, which} : new double[] {x1, y1, x0, y0, -1, which});
      }
    }
  }
  /** a chain: down its left points, back up its right points */
  private static void emit(Path out, Chain c) {
    out.moveTo((float) c.l[0], (float) c.y[0]);
    for (int i = 1; i < c.n; i++) out.lineTo((float) c.l[i], (float) c.y[i]);
    for (int i = c.n - 1; i >= 0; i--) out.lineTo((float) c.r[i], (float) c.y[i]);
    out.close();
  }

  /** (API 34) the path's segments as moves, lines, curves and closes */
  public PathIterator getPathIterator() { return new PathIterator(expanded()); }
  public void computeBounds(RectF b, boolean exact) {
    float l = Float.MAX_VALUE, t = Float.MAX_VALUE, r = -Float.MAX_VALUE, bt = -Float.MAX_VALUE;
    int i = 0;
    while (i < n) {
      int c = (int) cmd[i];
      int args = c == MOVE || c == LINE ? 2 : c == QUAD ? 4 : c == CUBIC ? 6 : c == CLOSE ? 0 : c == RECT ? 4 : c == RRECT ? 6 : 7;
      int pts = c == RECT || c == ELLIPSE || c == ARC || c == RRECT ? 2 : args / 2;
      for (int k = 0; k < pts; k++) { float x = cmd[i + 1 + 2 * k], y = cmd[i + 2 + 2 * k]; l = Math.min(l, x); r = Math.max(r, x); t = Math.min(t, y); bt = Math.max(bt, y); }
      i += 1 + args;
    }
    if (l > r) b.set(0, 0, 0, 0); else b.set(l, t, r, bt);
  }

  @JSBody(params = {"a"}, script = "return HW.cv.path(a);")
  private static native JSObject build(Float32Array a);
  /** Path2D for the page */
  public JSObject js() {
    if (cached == null) {
      float[] c = new float[n];
      System.arraycopy(cmd, 0, c, 0, n);
      cached = build(Float32Array.copyFromJavaArray(c));
    }
    return cached;
  }
  public boolean evenOdd() { return fill == FillType.EVEN_ODD || fill == FillType.INVERSE_EVEN_ODD; }

  // ---- API 21+ forms with coordinates
  public void addArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle) { addArc(new RectF(left, top, right, bottom), startAngle, sweepAngle); }
  public void addOval(float left, float top, float right, float bottom, Direction dir) { addOval(new RectF(left, top, right, bottom), dir); }
  public void addRoundRect(float left, float top, float right, float bottom, float rx, float ry, Direction dir) { addRoundRect(new RectF(left, top, right, bottom), rx, ry, dir); }
  public void addRoundRect(float left, float top, float right, float bottom, float[] radii, Direction dir) { addRoundRect(new RectF(left, top, right, bottom), radii, dir); }
  public void arcTo(float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean forceMoveTo) { arcTo(new RectF(left, top, right, bottom), startAngle, sweepAngle, forceMoveTo); }
  /** a conic section (weight w; 1 is a quadratic curve), as line segments */
  public void conicTo(float x1, float y1, float x2, float y2, float weight) {
    if (weight == 1) { quadTo(x1, y1, x2, y2); return; }
    float x0 = lastX, y0 = lastY;
    int segs = 16;
    for (int i = 1; i <= segs; i++) {
      float t = (float) i / segs, u = 1 - t;
      float d = u * u + 2 * weight * t * u + t * t;
      lineTo((u * u * x0 + 2 * weight * t * u * x1 + t * t * x2) / d, (u * u * y0 + 2 * weight * t * u * y1 + t * t * y2) / d);
    }
  }
  public void rConicTo(float dx1, float dy1, float dx2, float dy2, float weight) { conicTo(lastX + dx1, lastY + dy1, lastX + dx2, lastY + dy2, weight); }
  public int getGenerationId() { return generation; }
  /** whether the path is one contour whose turns all go the same way */
  public boolean isConvex() {
    java.util.List<Contour> cs = contours(0.25f);
    if (cs.size() != 1) return cs.isEmpty();
    Contour c = cs.get(0);
    int k = c.n / 2;
    if (k < 3) return true;
    int sign = 0;
    for (int i = 0; i < k; i++) {
      float ax = c.p[2 * i], ay = c.p[2 * i + 1], bx = c.p[2 * ((i + 1) % k)], by = c.p[2 * ((i + 1) % k) + 1], cx = c.p[2 * ((i + 2) % k)], cy = c.p[2 * ((i + 2) % k) + 1];
      float cross = (bx - ax) * (cy - by) - (by - ay) * (cx - bx);
      if (Math.abs(cross) < 1e-6f) continue;
      int sg = cross > 0 ? 1 : -1;
      if (sign == 0) sign = sg; else if (sg != sign) return false;
    }
    return true;
  }
  /** same commands and argument counts, so the coordinates can be interpolated */
  public boolean isInterpolatable(Path other) {
    if (other.n != n) return false;
    int i = 0;
    while (i < n) { if (cmd[i] != other.cmd[i]) return false; i += 1 + args((int) cmd[i]); }
    return true;
  }
  public boolean interpolate(Path otherPath, float t, Path interpolatedPath) {
    if (!isInterpolatable(otherPath)) return false;
    float[] out = new float[n];
    int i = 0;
    while (i < n) {
      int c = (int) cmd[i], a = args(c);
      out[i] = cmd[i];
      for (int k = 1; k <= a; k++) {
        boolean flag = (c == ELLIPSE || c == ARC) && k == 7;
        out[i + k] = flag ? cmd[i + k] : cmd[i + k] * t + otherPath.cmd[i + k] * (1 - t);
      }
      i += 1 + a;
    }
    interpolatedPath.cmd = out; interpolatedPath.n = n; interpolatedPath.fill = fill; interpolatedPath.empty = empty;
    interpolatedPath.cached = null; interpolatedPath.generation++;
    return true;
  }
  /** points along the path, each as (fraction of the total length, x, y); contours meet at equal fractions */
  public float[] approximate(float acceptableError) {
    if (acceptableError < 0) throw new IllegalArgumentException("AcceptableError cannot be negative");
    java.util.List<Contour> cs = contours(Math.max(acceptableError, 0.01f));
    float total = 0;
    for (Contour c : cs) total += c.length();
    java.util.ArrayList<float[]> pts = new java.util.ArrayList<>();
    float run = 0;
    for (Contour c : cs) {
      int k = c.n / 2;
      for (int i = 0; i <= k; i++) {
        if (i == k && !c.closed) break;
        int j = i % k;
        if (i > 0) { int q = i - 1; run += (float) Math.hypot(c.p[2 * j] - c.p[2 * q], c.p[2 * j + 1] - c.p[2 * q + 1]); }
        pts.add(new float[] {total == 0 ? 0 : run / total, c.p[2 * j], c.p[2 * j + 1]});
      }
    }
    if (pts.isEmpty()) return new float[] {0, 0, 0, 1, 0, 0};
    if (pts.size() == 1) pts.add(new float[] {1, pts.get(0)[1], pts.get(0)[2]});
    float[] r = new float[pts.size() * 3];
    for (int i = 0; i < pts.size(); i++) System.arraycopy(pts.get(i), 0, r, 3 * i, 3);
    r[r.length - 3] = 1;
    return r;
  }

  // ---- flattening (PathMeasure, approximate, isConvex)
  static int args(int c) { return c == MOVE || c == LINE ? 2 : c == QUAD ? 4 : c == CUBIC ? 6 : c == CLOSE ? 0 : c == RECT ? 4 : c == RRECT ? 6 : 7; }
  /** a polyline: x,y pairs; closed contours return to their first point */
  static final class Contour {
    float[] p = new float[16]; int n; boolean closed;
    void add(float x, float y) {
      if (n >= 2 && p[n - 2] == x && p[n - 1] == y) return;
      if (n + 2 > p.length) p = java.util.Arrays.copyOf(p, p.length * 2);
      p[n++] = x; p[n++] = y;
    }
    float length() {
      float l = 0;
      int k = n / 2;
      for (int i = 1; i < k; i++) l += (float) Math.hypot(p[2 * i] - p[2 * i - 2], p[2 * i + 1] - p[2 * i - 1]);
      if (closed && k > 1) l += (float) Math.hypot(p[0] - p[n - 2], p[1] - p[n - 1]);
      return l;
    }
  }
  private static int steps(float len, float tol) { return Math.max(1, Math.min(256, (int) Math.ceil(Math.sqrt(len / Math.max(tol, 0.01f)) * 2))); }
  private static void arcPoints(Contour c, float l, float t, float r, float b, float start, float sweep, float tol, boolean first) {
    float cx = (l + r) / 2, cy = (t + b) / 2, rx = (r - l) / 2, ry = (b - t) / 2;
    int seg = steps((float) (Math.abs(sweep) / 180 * Math.PI * Math.max(rx, ry)), tol);
    for (int i = first ? 0 : 1; i <= seg; i++) {
      double a = Math.toRadians(start + sweep * i / seg);
      c.add((float) (cx + rx * Math.cos(a)), (float) (cy + ry * Math.sin(a)));
    }
  }
  java.util.List<Contour> contours(float tol) {
    java.util.ArrayList<Contour> out = new java.util.ArrayList<>();
    Contour cur = null;
    float x = 0, y = 0, sx = 0, sy = 0;
    int i = 0;
    while (i < n) {
      int c = (int) cmd[i];
      float[] a = java.util.Arrays.copyOfRange(cmd, i + 1, i + 1 + args(c));
      i += 1 + args(c);
      if (c == MOVE) { cur = null; x = sx = a[0]; y = sy = a[1]; continue; }
      if (c == CLOSE) { if (cur != null) { cur.closed = true; cur = null; } x = sx; y = sy; continue; }
      if (c == RECT || c == ELLIPSE || c == RRECT) {
        Contour k = new Contour();
        k.closed = true;
        if (c == RECT) { k.add(a[0], a[1]); k.add(a[2], a[1]); k.add(a[2], a[3]); k.add(a[0], a[3]); }
        else if (c == ELLIPSE) arcPoints(k, a[0], a[1], a[2], a[3], 0, a[6] != 0 ? -360 : 360, tol, true);
        else {
          float rx = Math.min(a[4], (a[2] - a[0]) / 2), ry = Math.min(a[5], (a[3] - a[1]) / 2);
          arcPoints(k, a[2] - 2 * rx, a[1], a[2], a[1] + 2 * ry, 270, 90, tol, true);
          arcPoints(k, a[2] - 2 * rx, a[3] - 2 * ry, a[2], a[3], 0, 90, tol, true);
          arcPoints(k, a[0], a[3] - 2 * ry, a[0] + 2 * rx, a[3], 90, 90, tol, true);
          arcPoints(k, a[0], a[1], a[0] + 2 * rx, a[1] + 2 * ry, 180, 90, tol, true);
        }
        if (k.n > 0) { out.add(k); x = sx = k.p[0]; y = sy = k.p[1]; }
        cur = null;
        continue;
      }
      if (c == ARC) {
        double st = Math.toRadians(a[4]);
        float ax = (float) ((a[0] + a[2]) / 2 + (a[2] - a[0]) / 2 * Math.cos(st)), ay = (float) ((a[1] + a[3]) / 2 + (a[3] - a[1]) / 2 * Math.sin(st));
        if (a[6] != 0) { cur = new Contour(); out.add(cur); sx = ax; sy = ay; }
        else if (cur == null) { cur = new Contour(); out.add(cur); cur.add(x, y); sx = x; sy = y; }
        arcPoints(cur, a[0], a[1], a[2], a[3], a[4], a[5], tol, true);
        x = cur.p[cur.n - 2]; y = cur.p[cur.n - 1];
        continue;
      }
      if (cur == null) { cur = new Contour(); out.add(cur); cur.add(x, y); sx = x; sy = y; }
      if (c == LINE) { cur.add(a[0], a[1]); x = a[0]; y = a[1]; }
      else if (c == QUAD) {
        int seg = steps((float) (Math.hypot(a[0] - x, a[1] - y) + Math.hypot(a[2] - a[0], a[3] - a[1])), tol);
        for (int k = 1; k <= seg; k++) { float t = (float) k / seg, u = 1 - t; cur.add(u * u * x + 2 * u * t * a[0] + t * t * a[2], u * u * y + 2 * u * t * a[1] + t * t * a[3]); }
        x = a[2]; y = a[3];
      } else if (c == CUBIC) {
        int seg = steps((float) (Math.hypot(a[0] - x, a[1] - y) + Math.hypot(a[2] - a[0], a[3] - a[1]) + Math.hypot(a[4] - a[2], a[5] - a[3])), tol);
        for (int k = 1; k <= seg; k++) {
          float t = (float) k / seg, u = 1 - t;
          cur.add(u * u * u * x + 3 * u * u * t * a[0] + 3 * u * t * t * a[2] + t * t * t * a[4], u * u * u * y + 3 * u * u * t * a[1] + 3 * u * t * t * a[3] + t * t * t * a[5]);
        }
        x = a[4]; y = a[5];
      }
    }
    return out;
  }
}
