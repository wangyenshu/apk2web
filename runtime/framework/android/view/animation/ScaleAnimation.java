package android.view.animation;
public class ScaleAnimation extends Animation {
  final float fx, tx, fy, ty; int pxt = ABSOLUTE, pyt = ABSOLUTE; float pxv, pyv, px, py;
  public ScaleAnimation(float fromX, float toX, float fromY, float toY) { fx = fromX; tx = toX; fy = fromY; ty = toY; }
  public ScaleAnimation(float fromX, float toX, float fromY, float toY, float pivotX, float pivotY) { this(fromX, toX, fromY, toY); pxv = pivotX; pyv = pivotY; px = pivotX; py = pivotY; }
  public ScaleAnimation(float fromX, float toX, float fromY, float toY, int pxt, float pxv, int pyt, float pyv) {
    this(fromX, toX, fromY, toY); this.pxt = pxt; this.pxv = pxv; this.pyt = pyt; this.pyv = pyv; initializePivot();
  }
  private void initializePivot() { if (pxt == ABSOLUTE) px = pxv; if (pyt == ABSOLUTE) py = pyv; }
  @Override protected void applyTransformation(float t, Transformation out) {
    float sx = 1.0f, sy = 1.0f, scale = getScaleFactor();
    if (fx != 1.0f || tx != 1.0f) sx = fx + ((tx - fx) * t);
    if (fy != 1.0f || ty != 1.0f) sy = fy + ((ty - fy) * t);
    if (px == 0 && py == 0) out.getMatrix().setScale(sx, sy);
    else out.getMatrix().setScale(sx, sy, scale * px, scale * py);
  }
  @Override public void initialize(int w, int h, int pw, int ph) {
    super.initialize(w, h, pw, ph);
    px = resolveSize(pxt, pxv, w, pw); py = resolveSize(pyt, pyv, h, ph);
  }
}
