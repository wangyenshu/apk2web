package android.view.animation;
public class DecelerateInterpolator implements Interpolator {
  final float f;
  public DecelerateInterpolator() { f = 1; }
  public DecelerateInterpolator(float factor) { f = factor; }
  public DecelerateInterpolator(android.content.Context c, android.util.AttributeSet a) { this(); }
  public float getInterpolation(float t) { return f == 1 ? 1 - (1 - t) * (1 - t) : (float) (1 - Math.pow(1 - t, 2 * f)); }
}
