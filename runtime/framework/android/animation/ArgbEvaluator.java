package android.animation;
public class ArgbEvaluator implements TypeEvaluator {
  private static final ArgbEvaluator sInstance = new ArgbEvaluator();
  public static ArgbEvaluator getInstance() { return sInstance; }
  public Object evaluate(float fraction, Object startValue, Object endValue) {
    int startInt = (Integer) startValue, endInt = (Integer) endValue;
    float startA = ((startInt >> 24) & 0xff) / 255.0f, startR = ((startInt >> 16) & 0xff) / 255.0f, startG = ((startInt >> 8) & 0xff) / 255.0f, startB = (startInt & 0xff) / 255.0f;
    float endA = ((endInt >> 24) & 0xff) / 255.0f, endR = ((endInt >> 16) & 0xff) / 255.0f, endG = ((endInt >> 8) & 0xff) / 255.0f, endB = (endInt & 0xff) / 255.0f;
    startR = (float) Math.pow(startR, 2.2); startG = (float) Math.pow(startG, 2.2); startB = (float) Math.pow(startB, 2.2);
    endR = (float) Math.pow(endR, 2.2); endG = (float) Math.pow(endG, 2.2); endB = (float) Math.pow(endB, 2.2);
    float a = startA + fraction * (endA - startA), r = startR + fraction * (endR - startR), g = startG + fraction * (endG - startG), b = startB + fraction * (endB - startB);
    a = a * 255.0f; r = (float) Math.pow(r, 1.0 / 2.2) * 255.0f; g = (float) Math.pow(g, 1.0 / 2.2) * 255.0f; b = (float) Math.pow(b, 1.0 / 2.2) * 255.0f;
    return Math.round(a) << 24 | Math.round(r) << 16 | Math.round(g) << 8 | Math.round(b);
  }
}
