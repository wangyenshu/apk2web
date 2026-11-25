package android.graphics;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import org.teavm.jso.typedarrays.Float32Array;
import org.teavm.jso.typedarrays.Int32Array;

/** android.graphics.Shader: described to the page as a canvas gradient or pattern (see hw.js shaderStyle). */
public class Shader {
  public enum TileMode { CLAMP, REPEAT, MIRROR, DECAL }
  Matrix local;
  int gen;

  @Deprecated public Shader() {}
  public void setLocalMatrix(Matrix m) { local = m == null || m.isIdentity() ? null : new Matrix(m); gen++; }
  public boolean getLocalMatrix(Matrix m) { if (local == null) { m.reset(); return false; } m.set(local); return true; }

  /** changes whenever the page-side description would (Paint re-sends it then) */
  int version() { return gen; }
  /** the page-side description, or null to draw with the paint's color */
  JSObject js() { return null; }
  /** the local matrix as a, b, c, d, e, f (canvas order), or null */
  Float32Array localJs() {
    if (local == null) return null;
    float[] v = local.m;
    return Float32Array.copyFromJavaArray(new float[] {v[0], v[3], v[1], v[4], v[2], v[5]});
  }
  @JSBody(params = {"k", "m", "d", "c", "img", "a", "b", "tx", "ty", "mode"},
      script = "return {k: k, m: m, d: d, c: c, img: img, a: a, b: b, tx: tx, ty: ty, mode: mode};")
  static native JSObject make(int k, Float32Array m, Float32Array d, Int32Array c, JSObject img, JSObject a, JSObject b, int tx, int ty, String mode);
  /** a gradient's description: its geometry, then a stop position per color */
  JSObject gradient(int kind, float[] geometry, int[] colors, float[] positions, TileMode tile) {
    float[] d = java.util.Arrays.copyOf(geometry, geometry.length + colors.length);
    for (int i = 0; i < colors.length; i++) d[geometry.length + i] = positions == null ? i / (float) (colors.length - 1) : positions[i];
    int t = tile == null ? 0 : tile.ordinal();
    return make(kind, localJs(), Float32Array.copyFromJavaArray(d), Int32Array.copyFromJavaArray(colors), null, null, null, t, t, null);
  }
  static void checkColors(int count, float[] positions) {
    if (count < 2) throw new IllegalArgumentException("needs >= 2 number of colors");
    if (positions != null && count != positions.length) throw new IllegalArgumentException("color and position arrays must be of equal length");
  }
  /** color longs as sRGB color ints */
  static int[] argb(long[] colors) {
    int[] r = new int[colors.length];
    for (int i = 0; i < r.length; i++) r[i] = Color.toArgb(colors[i]);
    return r;
  }
}
