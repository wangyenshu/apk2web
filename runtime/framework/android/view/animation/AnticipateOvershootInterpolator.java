package android.view.animation;
public class AnticipateOvershootInterpolator extends BaseInterpolator {
  final float tension;
  public AnticipateOvershootInterpolator() { tension = 2 * 1.5f; }
  public AnticipateOvershootInterpolator(float t) { tension = t * 1.5f; }
  public AnticipateOvershootInterpolator(float t, float extraTension) { tension = t * extraTension; }
  public AnticipateOvershootInterpolator(android.content.Context c, android.util.AttributeSet a) { this(2f); }
  private static float a(float t, float s) { return t * t * ((s + 1) * t - s); }
  private static float o(float t, float s) { return t * t * ((s + 1) * t + s); }
  public float getInterpolation(float t) { if (t < 0.5f) return 0.5f * a(t * 2.0f, tension); else return 0.5f * (o(t * 2.0f - 2.0f, tension) + 2.0f); }
}
