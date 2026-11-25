package android.view.animation;

import android.graphics.Matrix;

public class Transformation {
  public static final int TYPE_IDENTITY = 0, TYPE_ALPHA = 1, TYPE_MATRIX = 2, TYPE_BOTH = 3;
  protected Matrix mMatrix = new Matrix();
  protected float mAlpha = 1;
  protected int mTransformationType = TYPE_BOTH;
  public Transformation() {}
  public void clear() { mMatrix.reset(); mAlpha = 1; mTransformationType = TYPE_BOTH; }
  public int getTransformationType() { return mTransformationType; }
  public void setTransformationType(int t) { mTransformationType = t; }
  public void set(Transformation t) { mAlpha = t.mAlpha; mMatrix.set(t.mMatrix); mTransformationType = t.mTransformationType; }
  public void compose(Transformation t) { mAlpha *= t.mAlpha; mMatrix.preConcat(t.mMatrix); }
  public void postCompose(Transformation t) { mAlpha *= t.mAlpha; mMatrix.postConcat(t.mMatrix); }
  public Matrix getMatrix() { return mMatrix; }
  public void setAlpha(float a) { mAlpha = a; }
  public float getAlpha() { return mAlpha; }
  public String toString() { return "Transformation{alpha=" + mAlpha + " matrix=" + mMatrix.toShortString() + "}"; }
}
