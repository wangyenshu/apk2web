package android.view.animation;
public class TranslateAnimation extends Animation {
  int fxt = ABSOLUTE, txt = ABSOLUTE, fyt = ABSOLUTE, tyt = ABSOLUTE;
  float fxv, txv, fyv, tyv, fx, tx, fy, ty;
  public TranslateAnimation(float fromX, float toX, float fromY, float toY) { fxv = fromX; txv = toX; fyv = fromY; tyv = toY; }
  public TranslateAnimation(int fxt, float fx, int txt, float tx, int fyt, float fy, int tyt, float ty) {
    this.fxt = fxt; fxv = fx; this.txt = txt; txv = tx; this.fyt = fyt; fyv = fy; this.tyt = tyt; tyv = ty;
  }
  @Override protected void applyTransformation(float t, Transformation out) {
    float dx = fx, dy = fy;
    if (fx != tx) dx = fx + ((tx - fx) * t);
    if (fy != ty) dy = fy + ((ty - fy) * t);
    out.getMatrix().setTranslate(dx, dy);
  }
  @Override public void initialize(int w, int h, int pw, int ph) {
    super.initialize(w, h, pw, ph);
    fx = resolveSize(fxt, fxv, w, pw); tx = resolveSize(txt, txv, w, pw);
    fy = resolveSize(fyt, fyv, h, ph); ty = resolveSize(tyt, tyv, h, ph);
  }
}
