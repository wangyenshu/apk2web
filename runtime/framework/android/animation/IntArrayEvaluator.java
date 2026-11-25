package android.animation;
public class IntArrayEvaluator implements TypeEvaluator<int[]> {
  private int[] mArray;
  public IntArrayEvaluator() {}
  public IntArrayEvaluator(int[] reuseArray) { mArray = reuseArray; }
  public int[] evaluate(float fraction, int[] startValue, int[] endValue) {
    int[] array = mArray == null ? new int[startValue.length] : mArray;
    for (int i = 0; i < array.length; i++) array[i] = (int) (startValue[i] + (fraction * (endValue[i] - startValue[i])));
    return array;
  }
}
