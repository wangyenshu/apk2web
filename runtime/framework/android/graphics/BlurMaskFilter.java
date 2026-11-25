package android.graphics;
public class BlurMaskFilter extends MaskFilter {
  public enum Blur { NORMAL, SOLID, OUTER, INNER }
  final float radius;
  final Blur style;
  public BlurMaskFilter(float r, Blur s) {
    if (r <= 0) throw new IllegalArgumentException("radius must be > 0");
    radius = r; style = s;
  }
}
