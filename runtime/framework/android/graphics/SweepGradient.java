package android.graphics;

import org.teavm.jso.JSObject;

/** a conic gradient around (cx, cy), starting at 3 o'clock and going clockwise */
public class SweepGradient extends Shader {
  final float cx, cy;
  final int[] cols;
  final float[] pos;
  public SweepGradient(float cx, float cy, int[] colors, float[] positions) {
    checkColors(colors.length, positions);
    this.cx = cx; this.cy = cy; cols = colors.clone(); pos = positions == null ? null : positions.clone();
  }
  public SweepGradient(float cx, float cy, long[] colors, float[] positions) { this(cx, cy, argb(colors), positions); }
  public SweepGradient(float cx, float cy, int color0, int color1) { this(cx, cy, new int[] {color0, color1}, null); }
  public SweepGradient(float cx, float cy, long color0, long color1) { this(cx, cy, new long[] {color0, color1}, null); }
  @Override JSObject js() { return gradient(3, new float[] {cx, cy}, cols, pos, TileMode.CLAMP); }
}
