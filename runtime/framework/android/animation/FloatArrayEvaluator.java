package android.animation;
public class FloatArrayEvaluator implements TypeEvaluator<float[]> {
  private float[] mArray;
  public FloatArrayEvaluator() {}
  public FloatArrayEvaluator(float[] reuseArray) { mArray = reuseArray; }
  public float[] evaluate(float fraction, float[] startValue, float[] endValue) {
    float[] array = mArray == null ? new float[startValue.length] : mArray;
    for (int i = 0; i < array.length; i++) array[i] = startValue[i] + (fraction * (endValue[i] - startValue[i]));
    return array;
  }
}
