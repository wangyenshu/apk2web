package android.animation;

import android.util.Property;
import android.view.View;

/** Animates a property of a target: View properties directly, others through their setX/getX methods. */
public final class ObjectAnimator extends ValueAnimator {
  private Object mTarget;
  private String mPropertyName;
  private Property mProperty;
  private boolean mAutoCancel;
  public ObjectAnimator() {}
  private ObjectAnimator(Object target, String propertyName) { setTarget(target); setPropertyName(propertyName); }
  private <T> ObjectAnimator(T target, Property<T, ?> property) { setTarget(target); setProperty(property); }
  public void setPropertyName(String propertyName) {
    if (mValues != null && mValues.length > 0) { mValues[0].setPropertyName(propertyName); mValuesMap.clear(); mValuesMap.put(propertyName, mValues[0]); }
    mPropertyName = propertyName;
  }
  public void setProperty(Property property) {
    if (mValues != null && mValues.length > 0) { mValues[0].setProperty(property); mValues[0].setPropertyName(property.getName()); }
    if (mProperty != null) mPropertyName = property.getName();
    mProperty = property;
  }
  public String getPropertyName() { return mProperty != null ? mProperty.getName() : mPropertyName; }
  public static ObjectAnimator ofInt(Object target, String propertyName, int... values) { ObjectAnimator a = new ObjectAnimator(target, propertyName); a.setIntValues(values); return a; }
  public static ObjectAnimator ofInt(Object target, String xPropertyName, String yPropertyName, android.graphics.Path path) { return ofInt(target, xPropertyName, 0); }
  public static <T> ObjectAnimator ofInt(T target, Property<T, Integer> property, int... values) { ObjectAnimator a = new ObjectAnimator(target, property); a.setIntValues(values); return a; }
  public static ObjectAnimator ofArgb(Object target, String propertyName, int... values) { ObjectAnimator a = ofInt(target, propertyName, values); a.setEvaluator(ArgbEvaluator.getInstance()); return a; }
  public static <T> ObjectAnimator ofArgb(T target, Property<T, Integer> property, int... values) { ObjectAnimator a = ofInt(target, property, values); a.setEvaluator(ArgbEvaluator.getInstance()); return a; }
  public static ObjectAnimator ofFloat(Object target, String propertyName, float... values) { ObjectAnimator a = new ObjectAnimator(target, propertyName); a.setFloatValues(values); return a; }
  public static <T> ObjectAnimator ofFloat(T target, Property<T, Float> property, float... values) { ObjectAnimator a = new ObjectAnimator(target, property); a.setFloatValues(values); return a; }
  public static ObjectAnimator ofObject(Object target, String propertyName, TypeEvaluator evaluator, Object... values) { ObjectAnimator a = new ObjectAnimator(target, propertyName); a.setObjectValues(values); a.setEvaluator(evaluator); return a; }
  @SafeVarargs public static <T, V> ObjectAnimator ofObject(T target, Property<T, V> property, TypeEvaluator<V> evaluator, V... values) { ObjectAnimator a = new ObjectAnimator(target, property); a.setObjectValues(values); a.setEvaluator(evaluator); return a; }
  public static ObjectAnimator ofPropertyValuesHolder(Object target, PropertyValuesHolder... values) { ObjectAnimator a = new ObjectAnimator(); a.setTarget(target); a.setValues(values); return a; }
  @Override public void setIntValues(int... values) {
    if (mValues == null || mValues.length == 0) {
      if (mProperty != null) setValues(PropertyValuesHolder.ofInt(mProperty, values)); else setValues(PropertyValuesHolder.ofInt(mPropertyName, values));
    } else super.setIntValues(values);
  }
  @Override public void setFloatValues(float... values) {
    if (mValues == null || mValues.length == 0) {
      if (mProperty != null) setValues(PropertyValuesHolder.ofFloat(mProperty, values)); else setValues(PropertyValuesHolder.ofFloat(mPropertyName, values));
    } else super.setFloatValues(values);
  }
  @Override public void setObjectValues(Object... values) {
    if (mValues == null || mValues.length == 0) {
      if (mProperty != null) setValues(PropertyValuesHolder.ofObject(mProperty, (TypeEvaluator) null, values)); else setValues(PropertyValuesHolder.ofObject(mPropertyName, (TypeEvaluator) null, values));
    } else super.setObjectValues(values);
  }
  public void setAutoCancel(boolean cancel) { mAutoCancel = cancel; }
  @Override public void start() { super.start(); }
  @Override void initAnimation() { if (mValues != null) for (PropertyValuesHolder v : mValues) v.setupStart(mTarget); }
  @Override public ObjectAnimator setDuration(long duration) { super.setDuration(duration); return this; }
  public Object getTarget() { return mTarget; }
  @Override public void setTarget(Object target) { if (mTarget != target) { if (isStarted()) cancel(); mTarget = target; } }
  @Override public void setupStartValues() { initAnimation(); }
  @Override public void setupEndValues() {}
  @Override void animateValue(float fraction) {
    super.animateValue(fraction);
    if (mValues != null) for (PropertyValuesHolder v : mValues) v.setAnimatedValue(mTarget);
  }
  @Override public ObjectAnimator clone() { return (ObjectAnimator) super.clone(); }
  @Override public String toString() { return "ObjectAnimator@" + Integer.toHexString(hashCode()) + ", target " + mTarget; }

  // ---------------------------------------------------------------- property access
  @SuppressWarnings("unchecked")
  static void setProperty(Object target, String name, Property property, Object value) {
    if (property != null) { property.set(target, value); return; }
    if (name == null) return;
    if (target instanceof View && setViewProperty((View) target, name, value)) return;
    String setter = "set" + Character.toUpperCase(name.charAt(0)) + name.substring(1);
    if (!a2w.Reflection.call(target, setter, value)) android.util.Log.w("PropertyValuesHolder", "Method " + setter + "() with type " + (value == null ? null : value.getClass()) + " not found on target class " + target.getClass());
  }
  static Object getProperty(Object target, String name, Property property) {
    if (property != null) return property.get(target);
    if (name == null) return null;
    if (target instanceof View) { Object v = getViewProperty((View) target, name); if (v != null) return v; }
    return null;
  }
  private static float f(Object v) { return ((Number) v).floatValue(); }
  static boolean setViewProperty(View v, String name, Object value) {
    switch (name) {
      case "alpha": v.setAlpha(f(value)); return true;
      case "translationX": v.setTranslationX(f(value)); return true;
      case "translationY": v.setTranslationY(f(value)); return true;
      case "translationZ": v.setTranslationZ(f(value)); return true;
      case "x": v.setX(f(value)); return true;
      case "y": v.setY(f(value)); return true;
      case "z": v.setZ(f(value)); return true;
      case "scaleX": v.setScaleX(f(value)); return true;
      case "scaleY": v.setScaleY(f(value)); return true;
      case "rotation": v.setRotation(f(value)); return true;
      case "rotationX": v.setRotationX(f(value)); return true;
      case "rotationY": v.setRotationY(f(value)); return true;
      case "pivotX": v.setPivotX(f(value)); return true;
      case "pivotY": v.setPivotY(f(value)); return true;
      case "elevation": v.setElevation(f(value)); return true;
      case "scrollX": v.setScrollX(((Number) value).intValue()); return true;
      case "scrollY": v.setScrollY(((Number) value).intValue()); return true;
      case "left": v.setLeft(((Number) value).intValue()); return true;
      case "top": v.setTop(((Number) value).intValue()); return true;
      case "right": v.setRight(((Number) value).intValue()); return true;
      case "bottom": v.setBottom(((Number) value).intValue()); return true;
      case "backgroundColor": v.setBackgroundColor(((Number) value).intValue()); return true;
      case "textColor": if (v instanceof android.widget.TextView) { ((android.widget.TextView) v).setTextColor(((Number) value).intValue()); return true; } return false;
      case "textSize": if (v instanceof android.widget.TextView) { ((android.widget.TextView) v).setTextSize(f(value)); return true; } return false;
      case "progress": if (v instanceof android.widget.ProgressBar) { ((android.widget.ProgressBar) v).setProgress(((Number) value).intValue()); return true; } return false;
    }
    return false;
  }
  static Object getViewProperty(View v, String name) {
    switch (name) {
      case "alpha": return v.getAlpha();
      case "translationX": return v.getTranslationX();
      case "translationY": return v.getTranslationY();
      case "translationZ": return v.getTranslationZ();
      case "x": return v.getX();
      case "y": return v.getY();
      case "z": return v.getZ();
      case "scaleX": return v.getScaleX();
      case "scaleY": return v.getScaleY();
      case "rotation": return v.getRotation();
      case "rotationX": return v.getRotationX();
      case "rotationY": return v.getRotationY();
      case "pivotX": return v.getPivotX();
      case "pivotY": return v.getPivotY();
      case "elevation": return v.getElevation();
      case "scrollX": return v.getScrollX();
      case "scrollY": return v.getScrollY();
      case "left": return v.getLeft();
      case "top": return v.getTop();
      case "right": return v.getRight();
      case "bottom": return v.getBottom();
      case "progress": return v instanceof android.widget.ProgressBar ? ((android.widget.ProgressBar) v).getProgress() : null;
    }
    return null;
  }
}
