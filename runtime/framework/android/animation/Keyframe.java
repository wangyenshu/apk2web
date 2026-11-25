package android.animation;

public abstract class Keyframe implements Cloneable {
  float mFraction;
  Class mValueType;
  TimeInterpolator mInterpolator;
  boolean mHasValue;
  public static Keyframe ofInt(float fraction, int value) { IntKeyframe k = new IntKeyframe(); k.mFraction = fraction; k.mValue = value; k.mHasValue = true; k.mValueType = int.class; return k; }
  public static Keyframe ofInt(float fraction) { IntKeyframe k = new IntKeyframe(); k.mFraction = fraction; k.mValueType = int.class; return k; }
  public static Keyframe ofFloat(float fraction, float value) { FloatKeyframe k = new FloatKeyframe(); k.mFraction = fraction; k.mValue = value; k.mHasValue = true; k.mValueType = float.class; return k; }
  public static Keyframe ofFloat(float fraction) { FloatKeyframe k = new FloatKeyframe(); k.mFraction = fraction; k.mValueType = float.class; return k; }
  public static Keyframe ofObject(float fraction, Object value) { ObjectKeyframe k = new ObjectKeyframe(); k.mFraction = fraction; k.mValue = value; k.mHasValue = value != null; k.mValueType = value == null ? Object.class : value.getClass(); return k; }
  public static Keyframe ofObject(float fraction) { return ofObject(fraction, null); }
  public boolean hasValue() { return mHasValue; }
  public abstract Object getValue();
  public abstract void setValue(Object value);
  public float getFraction() { return mFraction; }
  public void setFraction(float fraction) { mFraction = fraction; }
  public TimeInterpolator getInterpolator() { return mInterpolator; }
  public void setInterpolator(TimeInterpolator interpolator) { mInterpolator = interpolator; }
  public Class getType() { return mValueType; }
  @Override public abstract Keyframe clone();
  static class IntKeyframe extends Keyframe { int mValue; public Object getValue() { return mValue; } public void setValue(Object v) { mValue = (Integer) v; mHasValue = true; } public IntKeyframe clone() { IntKeyframe k = new IntKeyframe(); k.mFraction = mFraction; k.mValue = mValue; k.mHasValue = mHasValue; k.mValueType = mValueType; return k; } }
  static class FloatKeyframe extends Keyframe { float mValue; public Object getValue() { return mValue; } public void setValue(Object v) { mValue = (Float) v; mHasValue = true; } public FloatKeyframe clone() { FloatKeyframe k = new FloatKeyframe(); k.mFraction = mFraction; k.mValue = mValue; k.mHasValue = mHasValue; k.mValueType = mValueType; return k; } }
  static class ObjectKeyframe extends Keyframe { Object mValue; public Object getValue() { return mValue; } public void setValue(Object v) { mValue = v; mHasValue = true; } public ObjectKeyframe clone() { ObjectKeyframe k = new ObjectKeyframe(); k.mFraction = mFraction; k.mValue = mValue; k.mHasValue = mHasValue; k.mValueType = mValueType; return k; } }
}
