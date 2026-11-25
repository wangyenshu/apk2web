package android.view.animation;

import android.content.Context;
import android.graphics.Path;
import android.util.AttributeSet;

/** An interpolator following a quadratic or cubic Bézier curve from (0, 0) to (1, 1) (or a path). */
public class PathInterpolator extends BaseInterpolator {
  private float[] mX, mY;   // sampled curve, x increasing
  public PathInterpolator(Path path) { this(0.42f, 0f, 0.58f, 1f); }
  public PathInterpolator(float controlX, float controlY) { initQuad(controlX, controlY); }
  public PathInterpolator(float controlX1, float controlY1, float controlX2, float controlY2) { initCubic(controlX1, controlY1, controlX2, controlY2); }
  public PathInterpolator(Context context, AttributeSet attrs) {
    float x1 = 0, y1 = 0, x2 = 1, y2 = 1;
    boolean cubic = false;
    if (attrs instanceof a2w.Attrs) {
      a2w.Attrs a = (a2w.Attrs) attrs;
      x1 = a2w.Values.parseFloat(a.a("controlX1"), 0); y1 = a2w.Values.parseFloat(a.a("controlY1"), 0);
      cubic = a.a("controlX2") != null;
      x2 = a2w.Values.parseFloat(a.a("controlX2"), 1); y2 = a2w.Values.parseFloat(a.a("controlY2"), 1);
    }
    if (cubic) initCubic(x1, y1, x2, y2); else initQuad(x1, y1);
  }
  private static final int N = 200;
  private void initQuad(float cx, float cy) {
    mX = new float[N + 1]; mY = new float[N + 1];
    for (int i = 0; i <= N; i++) { float t = i / (float) N, u = 1 - t; mX[i] = 2 * u * t * cx + t * t; mY[i] = 2 * u * t * cy + t * t; }
  }
  private void initCubic(float x1, float y1, float x2, float y2) {
    mX = new float[N + 1]; mY = new float[N + 1];
    for (int i = 0; i <= N; i++) {
      float t = i / (float) N, u = 1 - t;
      mX[i] = 3 * u * u * t * x1 + 3 * u * t * t * x2 + t * t * t;
      mY[i] = 3 * u * u * t * y1 + 3 * u * t * t * y2 + t * t * t;
    }
  }
  @Override public float getInterpolation(float t) {
    if (t <= 0) return 0;
    if (t >= 1) return 1;
    int lo = 0, hi = mX.length - 1;
    while (hi - lo > 1) { int mid = (lo + hi) >>> 1; if (t < mX[mid]) hi = mid; else lo = mid; }
    float range = mX[hi] - mX[lo];
    if (range == 0) return mY[lo];
    float f = (t - mX[lo]) / range;
    return mY[lo] + f * (mY[hi] - mY[lo]);
  }
}
