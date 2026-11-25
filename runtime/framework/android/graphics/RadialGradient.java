package android.graphics;

import org.teavm.jso.JSObject;

/** circular gradients; the API 31 form is a two-point conical gradient (canvas createRadialGradient) */
public class RadialGradient extends Shader {
  final float sx, sy, sr, x, y, r;
  final boolean twoPoint;
  final int[] cols;
  final float[] pos;
  final TileMode tile;
  public RadialGradient(float centerX, float centerY, float radius, int[] colors, float[] stops, TileMode tileMode) {
    if (radius <= 0) throw new IllegalArgumentException("radius must be > 0");
    checkColors(colors.length, stops);
    sx = x = centerX; sy = y = centerY; sr = 0; r = radius; twoPoint = false; cols = colors.clone(); pos = stops == null ? null : stops.clone(); tile = tileMode;
  }
  public RadialGradient(float centerX, float centerY, float radius, long[] colors, float[] stops, TileMode tileMode) { this(centerX, centerY, radius, argb(colors), stops, tileMode); }
  public RadialGradient(float centerX, float centerY, float radius, int centerColor, int edgeColor, TileMode tileMode) { this(centerX, centerY, radius, new int[] {centerColor, edgeColor}, null, tileMode); }
  public RadialGradient(float centerX, float centerY, float radius, long centerColor, long edgeColor, TileMode tileMode) { this(centerX, centerY, radius, new long[] {centerColor, edgeColor}, null, tileMode); }
  public RadialGradient(float startX, float startY, float startRadius, float endX, float endY, float endRadius, long[] colors, float[] stops, TileMode tileMode) {
    if (startRadius < 0 || endRadius < 0) throw new IllegalArgumentException("radius must be >= 0");
    int[] c = argb(colors);
    checkColors(c.length, stops);
    sx = startX; sy = startY; sr = startRadius; x = endX; y = endY; r = endRadius; twoPoint = true; cols = c; pos = stops == null ? null : stops.clone(); tile = tileMode;
  }
  @Override JSObject js() {
    return twoPoint ? gradient(6, new float[] {sx, sy, sr, x, y, r}, cols, pos, tile) : gradient(2, new float[] {x, y, r}, cols, pos, tile);
  }
}
