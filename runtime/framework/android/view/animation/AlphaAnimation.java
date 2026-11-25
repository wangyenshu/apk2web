package android.view.animation;
public class AlphaAnimation extends Animation {
  final float from, to;
  public AlphaAnimation(float from, float to) { this.from = from; this.to = to; }
  @Override protected void applyTransformation(float t, Transformation out) { out.setAlpha(from + ((to - from) * t)); }
  @Override public boolean willChangeTransformationMatrix() { return false; }
  @Override public boolean willChangeBounds() { return false; }
  @Override public boolean hasAlpha() { return true; }
}
