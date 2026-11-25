package android.graphics;
public class LightingColorFilter extends ColorFilter {
  public LightingColorFilter(int mul, int add) {
    matrix = new float[] {Color.red(mul) / 255f, 0, 0, 0, Color.red(add), 0, Color.green(mul) / 255f, 0, 0, Color.green(add), 0, 0, Color.blue(mul) / 255f, 0, Color.blue(add), 0, 0, 0, 1, 0};
  }
}
