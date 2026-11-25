package android.view.animation;
public class CycleInterpolator implements Interpolator {
  final float cycles;
  public CycleInterpolator(float c) { cycles = c; }
  public float getInterpolation(float t) { return (float) Math.sin(2 * cycles * Math.PI * t); }
}
