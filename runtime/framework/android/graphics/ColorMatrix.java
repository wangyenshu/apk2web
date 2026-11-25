package android.graphics;

public class ColorMatrix {
  final float[] a = new float[20];
  public ColorMatrix() { reset(); }
  public ColorMatrix(float[] src) { System.arraycopy(src, 0, a, 0, 20); }
  public ColorMatrix(ColorMatrix src) { System.arraycopy(src.a, 0, a, 0, 20); }
  public final float[] getArray() { return a; }
  public void reset() { java.util.Arrays.fill(a, 0); a[0] = a[6] = a[12] = a[18] = 1; }
  public void set(ColorMatrix s) { System.arraycopy(s.a, 0, a, 0, 20); }
  public void set(float[] s) { System.arraycopy(s, 0, a, 0, 20); }
  public void setScale(float r, float g, float b, float al) { java.util.Arrays.fill(a, 0); a[0] = r; a[6] = g; a[12] = b; a[18] = al; }
  public void setSaturation(float sat) {
    reset();
    float inv = 1 - sat, R = 0.213f * inv, G = 0.715f * inv, B = 0.072f * inv;
    a[0] = R + sat; a[1] = G; a[2] = B;
    a[5] = R; a[6] = G + sat; a[7] = B;
    a[10] = R; a[11] = G; a[12] = B + sat;
  }
  public void setRotate(int axis, float deg) {
    reset();
    double r = Math.toRadians(deg); float c = (float) Math.cos(r), s = (float) Math.sin(r);
    switch (axis) {
      case 0: a[6] = a[12] = c; a[7] = s; a[11] = -s; break;
      case 1: a[0] = a[12] = c; a[2] = -s; a[10] = s; break;
      case 2: a[0] = a[6] = c; a[1] = s; a[5] = -s; break;
    }
  }
  public void setConcat(ColorMatrix m1, ColorMatrix m2) {
    float[] t = new float[20];
    for (int j = 0; j < 20; j += 5)
      for (int i = 0; i < 4; i++) t[j + i] = m1.a[j] * m2.a[i] + m1.a[j + 1] * m2.a[5 + i] + m1.a[j + 2] * m2.a[10 + i] + m1.a[j + 3] * m2.a[15 + i];
    for (int j = 0; j < 20; j += 5) t[j + 4] = m1.a[j] * m2.a[4] + m1.a[j + 1] * m2.a[9] + m1.a[j + 2] * m2.a[14] + m1.a[j + 3] * m2.a[19] + m1.a[j + 4];
    System.arraycopy(t, 0, a, 0, 20);
  }
  public void preConcat(ColorMatrix pre) { setConcat(this, pre); }
  public void postConcat(ColorMatrix post) { setConcat(post, this); }
  public void setRGB2YUV() { set(new float[] {0.299f, 0.587f, 0.114f, 0, 0, -0.16874f, -0.33126f, 0.5f, 0, 0, 0.5f, -0.41869f, -0.08131f, 0, 0, 0, 0, 0, 1, 0}); }
  public void setYUV2RGB() { set(new float[] {1, 0, 1.402f, 0, 0, 1, -0.34414f, -0.71414f, 0, 0, 1, 1.772f, 0, 0, 0, 0, 0, 0, 1, 0}); }
}
