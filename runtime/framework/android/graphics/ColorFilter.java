package android.graphics;

/** Filters are applied as a 4x5 color matrix (premultiplication handled by the page). */
public class ColorFilter {
  /** 20 entries, or null when the filter can't be expressed as a matrix */
  float[] matrix;
  int pdColor; PorterDuff.Mode pdMode;
  public String key() {
    if (matrix != null) return java.util.Arrays.toString(matrix);
    return "pd" + pdColor + pdMode;
  }
  /** apply to a plain color (used for shapes/text) */
  public int filter(int c) {
    if (matrix != null) {
      float r = Color.red(c), g = Color.green(c), b = Color.blue(c), a = Color.alpha(c);
      float[] m = matrix;
      int R = clamp(m[0] * r + m[1] * g + m[2] * b + m[3] * a + m[4]);
      int G = clamp(m[5] * r + m[6] * g + m[7] * b + m[8] * a + m[9]);
      int B = clamp(m[10] * r + m[11] * g + m[12] * b + m[13] * a + m[14]);
      int A = clamp(m[15] * r + m[16] * g + m[17] * b + m[18] * a + m[19]);
      return Color.argb(A, R, G, B);
    }
    if (pdMode != null) {
      switch (pdMode) {
        case SRC: return pdColor;
        case SRC_ATOP: case SRC_IN: { int a = Color.alpha(c) * Color.alpha(pdColor) / 255; return (a << 24) | (pdColor & 0xffffff); }
        case MULTIPLY: return Color.argb(Color.alpha(c) * Color.alpha(pdColor) / 255, Color.red(c) * Color.red(pdColor) / 255, Color.green(c) * Color.green(pdColor) / 255, Color.blue(c) * Color.blue(pdColor) / 255);
        case CLEAR: return 0;
        default: return c;
      }
    }
    return c;
  }
  static int clamp(float v) { return v < 0 ? 0 : v > 255 ? 255 : (int) v; }
  public float[] matrix() { return matrix; }
  public int pdColor() { return pdColor; }
  public String pdModeName() { return pdMode == null ? null : pdMode.name(); }
}
