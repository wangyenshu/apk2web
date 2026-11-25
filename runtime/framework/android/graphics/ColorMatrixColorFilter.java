package android.graphics;
public class ColorMatrixColorFilter extends ColorFilter {
  public ColorMatrixColorFilter(ColorMatrix m) { matrix = m.getArray().clone(); }
  public ColorMatrixColorFilter(float[] a) { matrix = new float[20]; System.arraycopy(a, 0, matrix, 0, 20); }
}
