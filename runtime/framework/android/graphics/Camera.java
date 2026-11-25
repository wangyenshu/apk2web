package android.graphics;

/**
 * android.graphics.Camera: Skia's Sk3DView. A stack of 3D transforms (y up) seen by a camera 8 inches (576 points)
 * in front of the view; getMatrix projects the unit patch into a perspective 3x3 matrix like SkCamera3D.
 */
public class Camera {
  private double[] m = identity();                   // 4x4, row-major
  private final java.util.ArrayDeque<double[]> stack = new java.util.ArrayDeque<>();
  private double locX, locY, locZ = -576, obsZ = -576;   // points (inches * 72)

  public Camera() {}
  private static double[] identity() { return new double[] {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1}; }
  private static double[] mul(double[] a, double[] b) {
    double[] r = new double[16];
    for (int i = 0; i < 4; i++) for (int j = 0; j < 4; j++) {
      double s = 0;
      for (int k = 0; k < 4; k++) s += a[4 * i + k] * b[4 * k + j];
      r[4 * i + j] = s;
    }
    return r;
  }

  public void save() { stack.push(m.clone()); }
  public void restore() { if (!stack.isEmpty()) m = stack.pop(); }
  public void translate(float x, float y, float z) { m = mul(m, new double[] {1, 0, 0, x, 0, 1, 0, y, 0, 0, 1, z, 0, 0, 0, 1}); }
  private void rotate(double ax, double ay, double az, float deg) {
    double r = Math.toRadians(deg), c = Math.cos(r), s = Math.sin(r), t = 1 - c;
    m = mul(m, new double[] {
        t * ax * ax + c, t * ax * ay - s * az, t * ax * az + s * ay, 0,
        t * ax * ay + s * az, t * ay * ay + c, t * ay * az - s * ax, 0,
        t * ax * az - s * ay, t * ay * az + s * ax, t * az * az + c, 0,
        0, 0, 0, 1});
  }
  public void rotateX(float deg) { rotate(1, 0, 0, deg); }
  public void rotateY(float deg) { rotate(0, -1, 0, deg); }
  public void rotateZ(float deg) { rotate(0, 0, 1, deg); }
  public void rotate(float x, float y, float z) { rotateX(x); rotateY(y); rotateZ(z); }
  /** in inches, like Android (72 points each) */
  public void setLocation(float x, float y, float z) { locX = x * 72.0; locY = y * 72.0; locZ = z * 72.0; obsZ = locZ; }
  public float getLocationX() { return (float) (locX / 72.0); }
  public float getLocationY() { return (float) (locY / 72.0); }
  public float getLocationZ() { return (float) (locZ / 72.0); }

  /** the patch: u = M (1, 0, 0), v = M (0, -1, 0) as vectors, origin = M (0, 0, 0, 1) */
  private double[] patch() {
    return new double[] {m[0], m[4], m[8], -m[1], -m[5], -m[9], m[3], m[7], m[11]};
  }
  /** the normal of the transformed patch, dotted with (x, y, z) */
  public float dotWithNormal(float x, float y, float z) {
    double[] p = patch();
    double cx = p[1] * p[5] - p[2] * p[4], cy = p[2] * p[3] - p[0] * p[5], cz = p[0] * p[4] - p[1] * p[3];
    return (float) (cx * x + cy * y + cz * z);
  }
  public void getMatrix(Matrix matrix) {
    // orientation: axis (0, 0, 1), zenith (0, -1, 0), cross (1, 0, 0), sheared by the observer (0, 0, obsZ)
    double z = obsZ;
    double[] row0 = {-z, 0, 0}, row1 = {0, z, 0}, row2 = {0, 0, 1};
    double[] p = patch();
    double dx = p[6] - locX, dy = p[7] - locY, dz = p[8] - locZ;
    double dot = dx * row2[0] + dy * row2[1] + dz * row2[2];
    if (dot == 0) dot = 1e-6;
    float[] v = new float[9];
    v[Matrix.MSCALE_X] = (float) ((p[0] * row0[0] + p[1] * row0[1] + p[2] * row0[2]) / dot);
    v[Matrix.MSKEW_Y] = (float) ((p[0] * row1[0] + p[1] * row1[1] + p[2] * row1[2]) / dot);
    v[Matrix.MPERSP_0] = (float) ((p[0] * row2[0] + p[1] * row2[1] + p[2] * row2[2]) / dot);
    v[Matrix.MSKEW_X] = (float) ((p[3] * row0[0] + p[4] * row0[1] + p[5] * row0[2]) / dot);
    v[Matrix.MSCALE_Y] = (float) ((p[3] * row1[0] + p[4] * row1[1] + p[5] * row1[2]) / dot);
    v[Matrix.MPERSP_1] = (float) ((p[3] * row2[0] + p[4] * row2[1] + p[5] * row2[2]) / dot);
    v[Matrix.MTRANS_X] = (float) ((dx * row0[0] + dy * row0[1] + dz * row0[2]) / dot);
    v[Matrix.MTRANS_Y] = (float) ((dx * row1[0] + dy * row1[1] + dz * row1[2]) / dot);
    v[Matrix.MPERSP_2] = 1;
    matrix.setValues(v);
  }
  public void applyToCanvas(Canvas canvas) { Matrix mm = new Matrix(); getMatrix(mm); canvas.concat(mm); }
  protected void finalize() throws Throwable {}
}
