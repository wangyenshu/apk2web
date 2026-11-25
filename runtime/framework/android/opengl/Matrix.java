package android.opengl;

/** 4x4 matrix math on float arrays, column-major as OpenGL uses them. */
public class Matrix {
  public Matrix() {}
  public static void multiplyMM(float[] r, int ro, float[] a, int ao, float[] b, int bo) {
    float[] t = new float[16];
    for (int c = 0; c < 4; c++)
      for (int row = 0; row < 4; row++) {
        float s = 0;
        for (int k = 0; k < 4; k++) s += a[ao + k * 4 + row] * b[bo + c * 4 + k];
        t[c * 4 + row] = s;
      }
    System.arraycopy(t, 0, r, ro, 16);
  }
  public static void multiplyMV(float[] r, int ro, float[] m, int mo, float[] v, int vo) {
    float x = v[vo], y = v[vo + 1], z = v[vo + 2], w = v[vo + 3];
    float[] t = new float[4];
    for (int row = 0; row < 4; row++) t[row] = m[mo + row] * x + m[mo + 4 + row] * y + m[mo + 8 + row] * z + m[mo + 12 + row] * w;
    System.arraycopy(t, 0, r, ro, 4);
  }
  public static void transposeM(float[] r, int ro, float[] m, int mo) {
    for (int i = 0; i < 4; i++) for (int j = 0; j < 4; j++) r[ro + i * 4 + j] = m[mo + j * 4 + i];
  }
  public static boolean invertM(float[] inv, int io, float[] m, int mo) {
    float[] a = new float[16];
    System.arraycopy(m, mo, a, 0, 16);
    float[] r = new float[16];
    r[0] = a[5] * a[10] * a[15] - a[5] * a[11] * a[14] - a[9] * a[6] * a[15] + a[9] * a[7] * a[14] + a[13] * a[6] * a[11] - a[13] * a[7] * a[10];
    r[4] = -a[4] * a[10] * a[15] + a[4] * a[11] * a[14] + a[8] * a[6] * a[15] - a[8] * a[7] * a[14] - a[12] * a[6] * a[11] + a[12] * a[7] * a[10];
    r[8] = a[4] * a[9] * a[15] - a[4] * a[11] * a[13] - a[8] * a[5] * a[15] + a[8] * a[7] * a[13] + a[12] * a[5] * a[11] - a[12] * a[7] * a[9];
    r[12] = -a[4] * a[9] * a[14] + a[4] * a[10] * a[13] + a[8] * a[5] * a[14] - a[8] * a[6] * a[13] - a[12] * a[5] * a[10] + a[12] * a[6] * a[9];
    r[1] = -a[1] * a[10] * a[15] + a[1] * a[11] * a[14] + a[9] * a[2] * a[15] - a[9] * a[3] * a[14] - a[13] * a[2] * a[11] + a[13] * a[3] * a[10];
    r[5] = a[0] * a[10] * a[15] - a[0] * a[11] * a[14] - a[8] * a[2] * a[15] + a[8] * a[3] * a[14] + a[12] * a[2] * a[11] - a[12] * a[3] * a[10];
    r[9] = -a[0] * a[9] * a[15] + a[0] * a[11] * a[13] + a[8] * a[1] * a[15] - a[8] * a[3] * a[13] - a[12] * a[1] * a[11] + a[12] * a[3] * a[9];
    r[13] = a[0] * a[9] * a[14] - a[0] * a[10] * a[13] - a[8] * a[1] * a[14] + a[8] * a[2] * a[13] + a[12] * a[1] * a[10] - a[12] * a[2] * a[9];
    r[2] = a[1] * a[6] * a[15] - a[1] * a[7] * a[14] - a[5] * a[2] * a[15] + a[5] * a[3] * a[14] + a[13] * a[2] * a[7] - a[13] * a[3] * a[6];
    r[6] = -a[0] * a[6] * a[15] + a[0] * a[7] * a[14] + a[4] * a[2] * a[15] - a[4] * a[3] * a[14] - a[12] * a[2] * a[7] + a[12] * a[3] * a[6];
    r[10] = a[0] * a[5] * a[15] - a[0] * a[7] * a[13] - a[4] * a[1] * a[15] + a[4] * a[3] * a[13] + a[12] * a[1] * a[7] - a[12] * a[3] * a[5];
    r[14] = -a[0] * a[5] * a[14] + a[0] * a[6] * a[13] + a[4] * a[1] * a[14] - a[4] * a[2] * a[13] - a[12] * a[1] * a[6] + a[12] * a[2] * a[5];
    r[3] = -a[1] * a[6] * a[11] + a[1] * a[7] * a[10] + a[5] * a[2] * a[11] - a[5] * a[3] * a[10] - a[9] * a[2] * a[7] + a[9] * a[3] * a[6];
    r[7] = a[0] * a[6] * a[11] - a[0] * a[7] * a[10] - a[4] * a[2] * a[11] + a[4] * a[3] * a[10] + a[8] * a[2] * a[7] - a[8] * a[3] * a[6];
    r[11] = -a[0] * a[5] * a[11] + a[0] * a[7] * a[9] + a[4] * a[1] * a[11] - a[4] * a[3] * a[9] - a[8] * a[1] * a[7] + a[8] * a[3] * a[5];
    r[15] = a[0] * a[5] * a[10] - a[0] * a[6] * a[9] - a[4] * a[1] * a[10] + a[4] * a[2] * a[9] + a[8] * a[1] * a[6] - a[8] * a[2] * a[5];
    float det = a[0] * r[0] + a[1] * r[4] + a[2] * r[8] + a[3] * r[12];
    if (det == 0) return false;
    for (int i = 0; i < 16; i++) inv[io + i] = r[i] / det;
    return true;
  }
  public static void orthoM(float[] m, int o, float l, float r, float b, float t, float n, float f) {
    if (l == r || b == t || n == f) throw new IllegalArgumentException();
    setIdentityM(m, o);
    m[o] = 2 / (r - l); m[o + 5] = 2 / (t - b); m[o + 10] = -2 / (f - n);
    m[o + 12] = -(r + l) / (r - l); m[o + 13] = -(t + b) / (t - b); m[o + 14] = -(f + n) / (f - n);
  }
  public static void frustumM(float[] m, int o, float l, float r, float b, float t, float n, float f) {
    if (l == r || t == b || n == f || n <= 0 || f <= 0) throw new IllegalArgumentException();
    java.util.Arrays.fill(m, o, o + 16, 0);
    m[o] = 2 * n / (r - l); m[o + 5] = 2 * n / (t - b); m[o + 8] = (r + l) / (r - l); m[o + 9] = (t + b) / (t - b);
    m[o + 10] = -(f + n) / (f - n); m[o + 11] = -1; m[o + 14] = -2 * f * n / (f - n);
  }
  public static void perspectiveM(float[] m, int o, float fovy, float aspect, float n, float f) {
    float fy = 1 / (float) Math.tan(fovy * (Math.PI / 360.0));
    java.util.Arrays.fill(m, o, o + 16, 0);
    m[o] = fy / aspect; m[o + 5] = fy; m[o + 10] = (f + n) / (n - f); m[o + 11] = -1; m[o + 14] = 2 * f * n / (n - f);
  }
  public static float length(float x, float y, float z) { return (float) Math.sqrt(x * x + y * y + z * z); }
  public static void setIdentityM(float[] m, int o) { for (int i = 0; i < 16; i++) m[o + i] = i % 5 == 0 ? 1 : 0; }
  public static void scaleM(float[] r, int ro, float[] m, int mo, float x, float y, float z) {
    for (int i = 0; i < 4; i++) { r[ro + i] = m[mo + i] * x; r[ro + 4 + i] = m[mo + 4 + i] * y; r[ro + 8 + i] = m[mo + 8 + i] * z; r[ro + 12 + i] = m[mo + 12 + i]; }
  }
  public static void scaleM(float[] m, int o, float x, float y, float z) { scaleM(m, o, m, o, x, y, z); }
  public static void translateM(float[] r, int ro, float[] m, int mo, float x, float y, float z) {
    if (r != m || ro != mo) System.arraycopy(m, mo, r, ro, 12);
    for (int i = 0; i < 4; i++) r[ro + 12 + i] = m[mo + i] * x + m[mo + 4 + i] * y + m[mo + 8 + i] * z + m[mo + 12 + i];
  }
  public static void translateM(float[] m, int o, float x, float y, float z) { translateM(m, o, m, o, x, y, z); }
  public static void rotateM(float[] r, int ro, float[] m, int mo, float a, float x, float y, float z) {
    float[] t = new float[16];
    setRotateM(t, 0, a, x, y, z);
    multiplyMM(r, ro, m, mo, t, 0);
  }
  public static void rotateM(float[] m, int o, float a, float x, float y, float z) { rotateM(m, o, m.clone(), o, a, x, y, z); }
  public static void setRotateM(float[] r, int o, float a, float x, float y, float z) {
    setIdentityM(r, o);
    float rad = (float) Math.toRadians(a), s = (float) Math.sin(rad), c = (float) Math.cos(rad);
    float len = length(x, y, z);
    if (len != 1 && len != 0) { x /= len; y /= len; z /= len; }
    float nc = 1 - c;
    r[o] = x * x * nc + c; r[o + 4] = x * y * nc - z * s; r[o + 8] = x * z * nc + y * s;
    r[o + 1] = y * x * nc + z * s; r[o + 5] = y * y * nc + c; r[o + 9] = y * z * nc - x * s;
    r[o + 2] = x * z * nc - y * s; r[o + 6] = y * z * nc + x * s; r[o + 10] = z * z * nc + c;
  }
  public static void setRotateEulerM(float[] r, int o, float x, float y, float z) {
    x = (float) Math.toRadians(x); y = (float) Math.toRadians(y); z = (float) Math.toRadians(z);
    float cx = (float) Math.cos(x), sx = (float) Math.sin(x), cy = (float) Math.cos(y), sy = (float) Math.sin(y), cz = (float) Math.cos(z), sz = (float) Math.sin(z);
    float cxsy = cx * sy, sxsy = sx * sy;
    r[o] = cy * cz; r[o + 1] = -cy * sz; r[o + 2] = sy; r[o + 3] = 0;
    r[o + 4] = cxsy * cz + cx * sz; r[o + 5] = -cxsy * sz + cx * cz; r[o + 6] = -sx * cy; r[o + 7] = 0;
    r[o + 8] = -sxsy * cz + sx * sz; r[o + 9] = sxsy * sz + sx * cz; r[o + 10] = cx * cy; r[o + 11] = 0;
    r[o + 12] = 0; r[o + 13] = 0; r[o + 14] = 0; r[o + 15] = 1;
  }
  public static void setLookAtM(float[] rm, int o, float ex, float ey, float ez, float cx, float cy, float cz, float ux, float uy, float uz) {
    float fx = cx - ex, fy = cy - ey, fz = cz - ez;
    float rl = 1 / length(fx, fy, fz);
    fx *= rl; fy *= rl; fz *= rl;
    float sx = fy * uz - fz * uy, sy = fz * ux - fx * uz, sz = fx * uy - fy * ux;
    float rls = 1 / length(sx, sy, sz);
    sx *= rls; sy *= rls; sz *= rls;
    float vx = sy * fz - sz * fy, vy = sz * fx - sx * fz, vz = sx * fy - sy * fx;
    rm[o] = sx; rm[o + 1] = vx; rm[o + 2] = -fx; rm[o + 3] = 0;
    rm[o + 4] = sy; rm[o + 5] = vy; rm[o + 6] = -fy; rm[o + 7] = 0;
    rm[o + 8] = sz; rm[o + 9] = vz; rm[o + 10] = -fz; rm[o + 11] = 0;
    rm[o + 12] = 0; rm[o + 13] = 0; rm[o + 14] = 0; rm[o + 15] = 1;
    translateM(rm, o, -ex, -ey, -ez);
  }
}
