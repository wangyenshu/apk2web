package android.graphics;

/** 3x3 matrix, row-major: [a b c; d e f; g h i] maps (x,y) -> (a x + b y + c, d x + e y + f). */
public class Matrix {
  public static final int MSCALE_X = 0, MSKEW_X = 1, MTRANS_X = 2, MSKEW_Y = 3, MSCALE_Y = 4, MTRANS_Y = 5, MPERSP_0 = 6, MPERSP_1 = 7, MPERSP_2 = 8;
  public static final Matrix IDENTITY_MATRIX = new Matrix();
  public enum ScaleToFit { FILL, START, CENTER, END }
  final float[] m = {1, 0, 0, 0, 1, 0, 0, 0, 1};

  public Matrix() {}
  public Matrix(Matrix s) { if (s != null) set(s); }
  public boolean isIdentity() { return m[0] == 1 && m[1] == 0 && m[2] == 0 && m[3] == 0 && m[4] == 1 && m[5] == 0 && m[6] == 0 && m[7] == 0 && m[8] == 1; }
  public boolean rectStaysRect() { return (m[1] == 0 && m[3] == 0) || (m[0] == 0 && m[4] == 0); }
  public void set(Matrix s) { if (s == null) reset(); else System.arraycopy(s.m, 0, m, 0, 9); }
  public boolean equals(Object o) { return o instanceof Matrix && java.util.Arrays.equals(m, ((Matrix) o).m); }
  public int hashCode() { return java.util.Arrays.hashCode(m); }
  public void reset() { m[0] = 1; m[1] = 0; m[2] = 0; m[3] = 0; m[4] = 1; m[5] = 0; m[6] = 0; m[7] = 0; m[8] = 1; }
  public void getValues(float[] v) { System.arraycopy(m, 0, v, 0, 9); }
  public void setValues(float[] v) { System.arraycopy(v, 0, m, 0, 9); }
  public float get(int i) { return m[i]; }

  static void mul(float[] r, float[] a, float[] b) {   // r = a * b
    float[] t = new float[9];
    for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) t[i * 3 + j] = a[i * 3] * b[j] + a[i * 3 + 1] * b[3 + j] + a[i * 3 + 2] * b[6 + j];
    System.arraycopy(t, 0, r, 0, 9);
  }
  private static float[] tr(float dx, float dy) { return new float[] {1, 0, dx, 0, 1, dy, 0, 0, 1}; }
  private static float[] sc(float sx, float sy, float px, float py) { return new float[] {sx, 0, px - sx * px, 0, sy, py - sy * py, 0, 0, 1}; }
  private static float[] rot(float deg, float px, float py) {
    double r = Math.toRadians(deg);
    float c = (float) Math.cos(r), s = (float) Math.sin(r);
    if (deg % 90 == 0) { c = Math.round(c); s = Math.round(s); }
    return new float[] {c, -s, s * py + (1 - c) * px, s, c, -s * px + (1 - c) * py, 0, 0, 1};
  }
  private static float[] sk(float kx, float ky, float px, float py) { return new float[] {1, kx, -kx * py, ky, 1, -ky * px, 0, 0, 1}; }

  public void setTranslate(float dx, float dy) { System.arraycopy(tr(dx, dy), 0, m, 0, 9); }
  public void setScale(float sx, float sy, float px, float py) { System.arraycopy(sc(sx, sy, px, py), 0, m, 0, 9); }
  public void setScale(float sx, float sy) { setScale(sx, sy, 0, 0); }
  public void setRotate(float d, float px, float py) { System.arraycopy(rot(d, px, py), 0, m, 0, 9); }
  public void setRotate(float d) { setRotate(d, 0, 0); }
  public void setSinCos(float s, float c, float px, float py) { System.arraycopy(new float[] {c, -s, s * py + (1 - c) * px, s, c, -s * px + (1 - c) * py, 0, 0, 1}, 0, m, 0, 9); }
  public void setSinCos(float s, float c) { setSinCos(s, c, 0, 0); }
  public void setSkew(float kx, float ky, float px, float py) { System.arraycopy(sk(kx, ky, px, py), 0, m, 0, 9); }
  public void setSkew(float kx, float ky) { setSkew(kx, ky, 0, 0); }
  public boolean setConcat(Matrix a, Matrix b) { mul(m, a.m, b.m); return true; }

  public boolean preTranslate(float dx, float dy) { mul(m, m, tr(dx, dy)); return true; }
  public boolean preScale(float sx, float sy, float px, float py) { mul(m, m, sc(sx, sy, px, py)); return true; }
  public boolean preScale(float sx, float sy) { return preScale(sx, sy, 0, 0); }
  public boolean preRotate(float d, float px, float py) { mul(m, m, rot(d, px, py)); return true; }
  public boolean preRotate(float d) { return preRotate(d, 0, 0); }
  public boolean preSkew(float kx, float ky, float px, float py) { mul(m, m, sk(kx, ky, px, py)); return true; }
  public boolean preSkew(float kx, float ky) { return preSkew(kx, ky, 0, 0); }
  public boolean preConcat(Matrix o) { mul(m, m, o.m); return true; }
  public boolean postTranslate(float dx, float dy) { mul(m, tr(dx, dy), m); return true; }
  public boolean postScale(float sx, float sy, float px, float py) { mul(m, sc(sx, sy, px, py), m); return true; }
  public boolean postScale(float sx, float sy) { return postScale(sx, sy, 0, 0); }
  public boolean postRotate(float d, float px, float py) { mul(m, rot(d, px, py), m); return true; }
  public boolean postRotate(float d) { return postRotate(d, 0, 0); }
  public boolean postSkew(float kx, float ky, float px, float py) { mul(m, sk(kx, ky, px, py), m); return true; }
  public boolean postSkew(float kx, float ky) { return postSkew(kx, ky, 0, 0); }
  public boolean postConcat(Matrix o) { mul(m, o.m, m); return true; }

  public boolean invert(Matrix inv) {
    float a = m[0], b = m[1], c = m[2], d = m[3], e = m[4], f = m[5], g = m[6], h = m[7], i = m[8];
    float A = e * i - f * h, B = -(d * i - f * g), C = d * h - e * g;
    float det = a * A + b * B + c * C;
    if (det == 0) return false;
    float id = 1 / det;
    if (inv != null) {
      float[] r = {A * id, -(b * i - c * h) * id, (b * f - c * e) * id, B * id, (a * i - c * g) * id, -(a * f - c * d) * id, C * id, -(a * h - b * g) * id, (a * e - b * d) * id};
      System.arraycopy(r, 0, inv.m, 0, 9);
    }
    return true;
  }
  public void mapPoints(float[] dst, int di, float[] src, int si, int count) {
    for (int k = 0; k < count; k++) {
      float x = src[si + 2 * k], y = src[si + 2 * k + 1];
      float w = m[6] * x + m[7] * y + m[8];
      if (w == 0) w = 1;
      dst[di + 2 * k] = (m[0] * x + m[1] * y + m[2]) / w;
      dst[di + 2 * k + 1] = (m[3] * x + m[4] * y + m[5]) / w;
    }
  }
  public void mapPoints(float[] dst, float[] src) { mapPoints(dst, 0, src, 0, src.length / 2); }
  public void mapPoints(float[] pts) { mapPoints(pts, 0, pts, 0, pts.length / 2); }
  public void mapVectors(float[] v) { mapVectors(v, 0, v, 0, v.length >> 1); }

  public boolean mapRect(RectF dst, RectF src) {
    float[] p = {src.left, src.top, src.right, src.top, src.right, src.bottom, src.left, src.bottom};
    mapPoints(p);
    dst.set(Math.min(Math.min(p[0], p[2]), Math.min(p[4], p[6])), Math.min(Math.min(p[1], p[3]), Math.min(p[5], p[7])),
        Math.max(Math.max(p[0], p[2]), Math.max(p[4], p[6])), Math.max(Math.max(p[1], p[3]), Math.max(p[5], p[7])));
    return rectStaysRect();
  }
  public boolean mapRect(RectF r) { return mapRect(r, r); }
  public float mapRadius(float r) { return (float) (r * Math.sqrt(Math.abs(m[0] * m[4] - m[1] * m[3]))); }
  public boolean setRectToRect(RectF src, RectF dst, ScaleToFit stf) {
    if (src.isEmpty()) { reset(); return false; }
    float sx = dst.width() / src.width(), sy = dst.height() / src.height();
    boolean xLarger = false;
    if (stf != ScaleToFit.FILL) { if (sx > sy) { xLarger = true; sx = sy; } else sy = sx; }
    float tx = dst.left - src.left * sx, ty = dst.top - src.top * sy;
    if (stf == ScaleToFit.CENTER || stf == ScaleToFit.END) {
      float diff = xLarger ? dst.width() - src.width() * sy : dst.height() - src.height() * sy;
      if (stf == ScaleToFit.CENTER) diff /= 2;
      if (xLarger) tx += diff; else ty += diff;
    }
    m[0] = sx; m[1] = 0; m[2] = tx; m[3] = 0; m[4] = sy; m[5] = ty; m[6] = 0; m[7] = 0; m[8] = 1;
    return true;
  }
  public String toString() { return "Matrix" + java.util.Arrays.toString(m); }
  public String toShortString() { return java.util.Arrays.toString(m); }
  public boolean isAffine() { return m[6] == 0 && m[7] == 0 && m[8] == 1; }
  /** vectors: the mapped point minus the mapped origin (translation drops out) */
  public void mapVectors(float[] dst, int dstIndex, float[] src, int srcIndex, int vectorCount) {
    float[] o = {0, 0};
    mapPoints(o);
    for (int k = 0; k < vectorCount; k++) {
      float[] p = {src[srcIndex + 2 * k], src[srcIndex + 2 * k + 1]};
      mapPoints(p);
      dst[dstIndex + 2 * k] = p[0] - o[0];
      dst[dstIndex + 2 * k + 1] = p[1] - o[1];
    }
  }
  public void mapVectors(float[] dst, float[] src) { mapVectors(dst, 0, src, 0, src.length >> 1); }
  /** maps 0 to 4 points onto others: translation, similarity, affine or perspective */
  public boolean setPolyToPoly(float[] src, int srcIndex, float[] dst, int dstIndex, int pointCount) {
    if (pointCount > 4) throw new IllegalArgumentException();
    if (srcIndex < 0 || dstIndex < 0 || srcIndex + 2 * pointCount > src.length || dstIndex + 2 * pointCount > dst.length) throw new ArrayIndexOutOfBoundsException();
    if (pointCount == 0) { reset(); return true; }
    if (pointCount == 1) { setTranslate(dst[dstIndex] - src[srcIndex], dst[dstIndex + 1] - src[srcIndex + 1]); return true; }
    if (pointCount == 2) {
      float sx = src[srcIndex + 2] - src[srcIndex], sy = src[srcIndex + 3] - src[srcIndex + 1];
      float dx = dst[dstIndex + 2] - dst[dstIndex], dy = dst[dstIndex + 3] - dst[dstIndex + 1];
      float l = sx * sx + sy * sy;
      if (l == 0) return false;
      float a = (dx * sx + dy * sy) / l, b = (dy * sx - dx * sy) / l;
      float tx = dst[dstIndex] - (a * src[srcIndex] - b * src[srcIndex + 1]), ty = dst[dstIndex + 1] - (b * src[srcIndex] + a * src[srcIndex + 1]);
      setValues(new float[] {a, -b, tx, b, a, ty, 0, 0, 1});
      return true;
    }
    float[] s = quadMap(src, srcIndex, pointCount), d = quadMap(dst, dstIndex, pointCount);
    if (s == null || d == null) return false;
    Matrix ms = new Matrix(), md = new Matrix(), inv = new Matrix();
    ms.setValues(s); md.setValues(d);
    if (!ms.invert(inv)) return false;
    setConcat(md, inv);
    return true;
  }
  /** the matrix taking the unit square's corners (0,0) (1,0) (1,1) (0,1) to the points (3 points: affine on the first three of (0,0) (1,0) (0,1)) */
  private static float[] quadMap(float[] p, int o, int n) {
    if (n == 3) {
      float x0 = p[o], y0 = p[o + 1], x1 = p[o + 2], y1 = p[o + 3], x2 = p[o + 4], y2 = p[o + 5];
      float[] r = {x1 - x0, x2 - x0, x0, y1 - y0, y2 - y0, y0, 0, 0, 1};
      return r[0] * r[4] - r[1] * r[3] == 0 ? null : r;
    }
    float x0 = p[o], y0 = p[o + 1], x1 = p[o + 2], y1 = p[o + 3], x2 = p[o + 4], y2 = p[o + 5], x3 = p[o + 6], y3 = p[o + 7];
    float dx1 = x1 - x2, dx2 = x3 - x2, dx3 = x0 - x1 + x2 - x3, dy1 = y1 - y2, dy2 = y3 - y2, dy3 = y0 - y1 + y2 - y3;
    if (dx3 == 0 && dy3 == 0) return new float[] {x1 - x0, x3 - x0, x0, y1 - y0, y3 - y0, y0, 0, 0, 1};
    float det = dx1 * dy2 - dx2 * dy1;
    if (det == 0) return null;
    float g = (dx3 * dy2 - dx2 * dy3) / det, h = (dx1 * dy3 - dx3 * dy1) / det;
    return new float[] {x1 - x0 + g * x1, x3 - x0 + h * x3, x0, y1 - y0 + g * y1, y3 - y0 + h * y3, y0, g, h, 1};
  }
  public void dump(java.io.PrintWriter pw) {
    pw.print("[[" + m[0] + ", " + m[1] + ", " + m[2] + "], [" + m[3] + ", " + m[4] + ", " + m[5] + "], [" + m[6] + ", " + m[7] + ", " + m[8] + "]]");
  }
}
