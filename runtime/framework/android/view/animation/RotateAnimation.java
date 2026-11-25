package android.view.animation;
public class RotateAnimation extends Animation {
  final float from, to; int pxt = ABSOLUTE, pyt = ABSOLUTE; float pxv, pyv, px, py;
  public RotateAnimation(float from, float to) { this.from = from; this.to = to; }
  public RotateAnimation(float from, float to, float pivotX, float pivotY) { this(from, to); pxv = px = pivotX; pyv = py = pivotY; }
  public RotateAnimation(float from, float to, int pxt, float pxv, int pyt, float pyv) {
    this(from, to); this.pxt = pxt; this.pxv = pxv; this.pyt = pyt; this.pyv = pyv;
    if (pxt == ABSOLUTE) px = pxv;
    if (pyt == ABSOLUTE) py = pyv;
  }
  @Override protected void applyTransformation(float t, Transformation out) {
    float deg = from + ((to - from) * t), scale = getScaleFactor();
    if (px == 0.0f && py == 0.0f) out.getMatrix().setRotate(deg);
    else out.getMatrix().setRotate(deg, px * scale, py * scale);
  }
  @Override public void initialize(int w, int h, int pw, int ph) {
    super.initialize(w, h, pw, ph);
    px = resolveSize(pxt, pxv, w, pw); py = resolveSize(pyt, pyv, h, ph);
  }
}
