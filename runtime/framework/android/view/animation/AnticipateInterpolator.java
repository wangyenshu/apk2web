package android.view.animation;
public class AnticipateInterpolator implements Interpolator {
  final float tension;
  public AnticipateInterpolator() { tension = 2; }
  public AnticipateInterpolator(float t) { tension = t; }
  public float getInterpolation(float t) { return t * t * ((tension + 1) * t - tension); }
}
