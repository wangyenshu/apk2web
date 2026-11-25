package android.animation;

import android.util.Property;

/** One animated property: its values over the animation and how to set it on the target. */
public class PropertyValuesHolder implements Cloneable {
  String mPropertyName;
  Property mProperty;
  Object[] mValues;
  Class<?> mValueType;
  TypeEvaluator mEvaluator;
  Object mAnimatedValue;
  private PropertyValuesHolder(String propertyName) { mPropertyName = propertyName; }
  private PropertyValuesHolder(Property property) { mProperty = property; if (property != null) mPropertyName = property.getName(); }
  public static PropertyValuesHolder ofInt(String propertyName, int... values) { PropertyValuesHolder p = new PropertyValuesHolder(propertyName); p.setIntValues(values); return p; }
  public static PropertyValuesHolder ofInt(Property<?, Integer> property, int... values) { PropertyValuesHolder p = new PropertyValuesHolder(property); p.setIntValues(values); return p; }
  public static PropertyValuesHolder ofFloat(String propertyName, float... values) { PropertyValuesHolder p = new PropertyValuesHolder(propertyName); p.setFloatValues(values); return p; }
  public static PropertyValuesHolder ofFloat(Property<?, Float> property, float... values) { PropertyValuesHolder p = new PropertyValuesHolder(property); p.setFloatValues(values); return p; }
  public static PropertyValuesHolder ofObject(String propertyName, TypeEvaluator evaluator, Object... values) { PropertyValuesHolder p = new PropertyValuesHolder(propertyName); p.setObjectValues(values); p.setEvaluator(evaluator); return p; }
  @SafeVarargs public static <V> PropertyValuesHolder ofObject(Property property, TypeEvaluator<V> evaluator, V... values) { PropertyValuesHolder p = new PropertyValuesHolder(property); p.setObjectValues(values); p.setEvaluator(evaluator); return p; }
  public static PropertyValuesHolder ofMultiInt(String propertyName, int[][] values) { return ofObject(propertyName, new IntArrayEvaluator(), (Object[]) values); }
  public static PropertyValuesHolder ofMultiFloat(String propertyName, float[][] values) { return ofObject(propertyName, new FloatArrayEvaluator(), (Object[]) values); }
  public static PropertyValuesHolder ofKeyframe(String propertyName, Keyframe... values) {
    Object[] v = new Object[values.length];
    for (int i = 0; i < v.length; i++) v[i] = values[i].getValue();
    PropertyValuesHolder p = new PropertyValuesHolder(propertyName);
    p.mValues = v;
    p.mValueType = values.length > 0 ? values[0].getType() : Float.class;
    return p;
  }
  public void setIntValues(int... values) { mValueType = int.class; mValues = new Object[values.length]; for (int i = 0; i < values.length; i++) mValues[i] = values[i]; }
  public void setFloatValues(float... values) { mValueType = float.class; mValues = new Object[values.length]; for (int i = 0; i < values.length; i++) mValues[i] = values[i]; }
  public void setObjectValues(Object... values) { mValueType = values.length > 0 && values[0] != null ? values[0].getClass() : Object.class; mValues = values.clone(); }
  public void setEvaluator(TypeEvaluator evaluator) { mEvaluator = evaluator; }
  public void setPropertyName(String propertyName) { mPropertyName = propertyName; }
  public String getPropertyName() { return mPropertyName; }
  public void setProperty(Property property) { mProperty = property; }
  @Override public PropertyValuesHolder clone() {
    try { PropertyValuesHolder p = (PropertyValuesHolder) super.clone(); p.mValues = mValues == null ? null : mValues.clone(); return p; }
    catch (CloneNotSupportedException e) { return null; }
  }
  /** the value at an (interpolated) fraction of the whole animation */
  @SuppressWarnings("unchecked")
  void calculateValue(float fraction) {
    if (mValues == null || mValues.length == 0) return;
    if (mValues.length == 1) { mAnimatedValue = mValues[0]; return; }
    int n = mValues.length - 1;
    float pos = fraction * n;
    int i = (int) Math.floor(pos);
    if (i < 0) i = 0;
    if (i >= n) i = n - 1;
    float f = pos - i;
    Object a = mValues[i], b = mValues[i + 1];
    if (mEvaluator != null) mAnimatedValue = mEvaluator.evaluate(f, a, b);
    else if (a instanceof Integer) mAnimatedValue = (int) ((Integer) a + f * ((Integer) b - (Integer) a));
    else if (a instanceof Number) mAnimatedValue = ((Number) a).floatValue() + f * (((Number) b).floatValue() - ((Number) a).floatValue());
    else mAnimatedValue = f < 1 ? a : b;
  }
  Object getAnimatedValue() { return mAnimatedValue; }
  /** before starting: a single value animates from the target's current one */
  void setupStart(Object target) {
    if (mValues != null && mValues.length == 1 && target != null) {
      Object cur = ObjectAnimator.getProperty(target, mPropertyName, mProperty);
      if (cur != null) mValues = new Object[] {coerce(cur), mValues[0]};
    }
  }
  private Object coerce(Object v) {
    if (mValueType == int.class && v instanceof Number) return ((Number) v).intValue();
    if (mValueType == float.class && v instanceof Number) return ((Number) v).floatValue();
    return v;
  }
  void setAnimatedValue(Object target) { if (target != null && mAnimatedValue != null) ObjectAnimator.setProperty(target, mPropertyName, mProperty, mAnimatedValue); }
  @Override public String toString() { return mPropertyName + ": " + java.util.Arrays.toString(mValues); }
}
