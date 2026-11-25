package android.view.animation;
public class OvershootInterpolator implements Interpolator {
  final float tension;
  public OvershootInterpolator() { tension = 2; }
  public OvershootInterpolator(float t) { tension = t; }
  public OvershootInterpolator(android.content.Context c, android.util.AttributeSet a) { this(); }
  public float getInterpolation(float t) { t -= 1.0f; return t * t * ((tension + 1) * t + tension) + 1.0f; }
}
