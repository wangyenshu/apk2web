package android.opengl;

import javax.microedition.khronos.opengles.GL10;

/** The GLU helpers Android offers, on top of the GL10 calls and android.opengl.Matrix. */
public class GLU {
  public GLU() {}
  public static String gluErrorString(int e) {
    switch (e) {
      case 0: return "no error";
      case 0x0500: return "invalid enum";
      case 0x0501: return "invalid value";
      case 0x0502: return "invalid operation";
      case 0x0503: return "stack overflow";
      case 0x0504: return "stack underflow";
      case 0x0505: return "out of memory";
      default: return null;
    }
  }
  public static void gluLookAt(GL10 gl, float ex, float ey, float ez, float cx, float cy, float cz, float ux, float uy, float uz) {
    float[] m = new float[16];
    Matrix.setLookAtM(m, 0, ex, ey, ez, cx, cy, cz, ux, uy, uz);
    gl.glMultMatrixf(m, 0);
  }
  public static void gluOrtho2D(GL10 gl, float l, float r, float b, float t) { gl.glOrthof(l, r, b, t, -1, 1); }
  public static void gluPerspective(GL10 gl, float fovy, float aspect, float n, float f) {
    float top = n * (float) Math.tan(fovy * (Math.PI / 360.0)), bottom = -top;
    gl.glFrustumf(bottom * aspect, top * aspect, bottom, top, n, f);
  }
  public static int gluProject(float ox, float oy, float oz, float[] model, int mo, float[] proj, int po, int[] view, int vo, float[] win, int wo) {
    float[] mvp = new float[16], v = {ox, oy, oz, 1}, r = new float[4];
    Matrix.multiplyMM(mvp, 0, proj, po, model, mo);
    Matrix.multiplyMV(r, 0, mvp, 0, v, 0);
    if (r[3] == 0) return 0;
    win[wo] = view[vo] + view[vo + 2] * (r[0] / r[3] + 1) / 2;
    win[wo + 1] = view[vo + 1] + view[vo + 3] * (r[1] / r[3] + 1) / 2;
    win[wo + 2] = (r[2] / r[3] + 1) / 2;
    return 1;
  }
  public static int gluUnProject(float wx, float wy, float wz, float[] model, int mo, float[] proj, int po, int[] view, int vo, float[] obj, int oo) {
    float[] mvp = new float[16], inv = new float[16];
    Matrix.multiplyMM(mvp, 0, proj, po, model, mo);
    if (!Matrix.invertM(inv, 0, mvp, 0)) return 0;
    float[] v = {(wx - view[vo]) * 2 / view[vo + 2] - 1, (wy - view[vo + 1]) * 2 / view[vo + 3] - 1, 2 * wz - 1, 1}, r = new float[4];
    Matrix.multiplyMV(r, 0, inv, 0, v, 0);
    if (r[3] == 0) return 0;
    obj[oo] = r[0] / r[3]; obj[oo + 1] = r[1] / r[3]; obj[oo + 2] = r[2] / r[3];
    if (obj.length > oo + 3) obj[oo + 3] = r[3];
    return 1;
  }
}
