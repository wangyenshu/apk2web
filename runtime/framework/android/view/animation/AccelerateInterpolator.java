package android.view.animation;
public class AccelerateInterpolator implements Interpolator {
  final float f; final double d;
  public AccelerateInterpolator() { f = 1; d = 2; }
  public AccelerateInterpolator(float factor) { f = factor; d = 2 * factor; }
  public AccelerateInterpolator(android.content.Context c, android.util.AttributeSet a) { this(); }
  public float getInterpolation(float t) { return f == 1 ? t * t : (float) Math.pow(t, d); }
}
