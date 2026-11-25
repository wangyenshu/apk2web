package android.graphics;

import org.teavm.jso.JSObject;

public class LinearGradient extends Shader {
  final float x0, y0, x1, y1;
  final int[] cols;
  final float[] pos;
  final TileMode tile;
  public LinearGradient(float x0, float y0, float x1, float y1, int[] colors, float[] positions, TileMode tile) {
    checkColors(colors.length, positions);
    this.x0 = x0; this.y0 = y0; this.x1 = x1; this.y1 = y1; cols = colors.clone(); pos = positions == null ? null : positions.clone(); this.tile = tile;
  }
  public LinearGradient(float x0, float y0, float x1, float y1, long[] colors, float[] positions, TileMode tile) { this(x0, y0, x1, y1, argb(colors), positions, tile); }
  public LinearGradient(float x0, float y0, float x1, float y1, int c0, int c1, TileMode tile) { this(x0, y0, x1, y1, new int[] {c0, c1}, null, tile); }
  public LinearGradient(float x0, float y0, float x1, float y1, long c0, long c1, TileMode tile) { this(x0, y0, x1, y1, new long[] {c0, c1}, null, tile); }
  @Override JSObject js() { return gradient(1, new float[] {x0, y0, x1, y1}, cols, pos, tile); }
}
