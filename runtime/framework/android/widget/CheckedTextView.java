package android.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import a2w.Attrs;
import a2w.Values;

/** A TextView that is Checkable (list items in choice mode), with its check mark drawable at the end. */
public class CheckedTextView extends TextView implements Checkable {
  private static final int[] CHECKED_STATE_SET = {android.R.attr.state_checked};
  private static final String NS = "http://schemas.android.com/apk/res/android";
  private boolean mChecked;
  private Drawable mCheckMarkDrawable;
  private int mCheckMarkWidth;
  private ColorStateList mCheckMarkTintList;
  private PorterDuff.Mode mCheckMarkTintMode;
  private BlendMode mCheckMarkBlendMode;
  public CheckedTextView(Context c) { this(c, null); }
  public CheckedTextView(Context c, AttributeSet a) { this(c, a, 0); }
  public CheckedTextView(Context c, AttributeSet a, int d) {
    super(c, a, d);
    Attrs at = mCtorAttrs instanceof Attrs ? (Attrs) mCtorAttrs : null;
    String v;
    if (at != null && (v = at.a("checkMarkTint")) != null) mCheckMarkTintList = tintList(v);
    if (at != null && (v = at.a("checkMarkTintMode")) != null) mCheckMarkTintMode = CompoundButton.tintMode(v);
    v = a != null ? a.getAttributeValue(NS, "checkMark") : null;
    if (v == null && at != null) v = at.a("checkMark");
    if (v != null) setCheckMarkDrawable(markFor(v));
    v = a != null ? a.getAttributeValue(NS, "checked") : null;
    if (v == null && at != null) v = at.a("checked");
    if (v != null) setChecked(Values.parseBool(v, false));
  }
  public CheckedTextView(Context c, AttributeSet a, int d, int r) { this(c, a, d); }
  /** a check mark reference: the theme's choice indicators (listChoiceIndicatorSingle/Multiple) become the framework's radio and check box */
  private Drawable markFor(String v) {
    if (v.startsWith("?") || v.startsWith("@android:")) {
      if (v.contains("Single") || v.contains("radio")) return new a2w.WidgetDrawables.Indicator(a2w.WidgetDrawables.Indicator.RADIO);
      if (v.contains("Multiple") || v.contains("CheckMark") || v.contains("check")) return new a2w.WidgetDrawables.Indicator(a2w.WidgetDrawables.Indicator.CHECK);
      if (v.startsWith("?")) return null;
    }
    if (!Values.isRef(v)) return null;
    try { return getResources().getDrawable(Values.refId(v)); } catch (RuntimeException e) { return null; }
  }
  private ColorStateList tintList(String v) {
    try {
      if (Values.isRef(v)) return getResources().getColorStateList(Values.refId(v));
      if (v.startsWith("#")) return ColorStateList.valueOf(Values.parseColor(v));
    } catch (RuntimeException e) { /* none */ }
    return null;
  }
  @Override public boolean isChecked() { return mChecked; }
  @Override public void setChecked(boolean checked) { if (mChecked != checked) { mChecked = checked; refreshDrawableState(); invalidate(); } }
  @Override public void toggle() { setChecked(!mChecked); }
  public void setCheckMarkDrawable(int resId) { setCheckMarkDrawable(resId == 0 ? null : getResources().getDrawable(resId)); }
  public void setCheckMarkDrawable(Drawable d) {
    if (mCheckMarkDrawable != null) { mCheckMarkDrawable.setCallback(null); unscheduleDrawable(mCheckMarkDrawable); }
    mCheckMarkDrawable = d;
    if (d != null) {
      d.setCallback(this);
      d.setVisible(getVisibility() == VISIBLE, false);
      d.setState(getDrawableState());
      setMinHeight(d.getIntrinsicHeight());
      mCheckMarkWidth = Math.max(0, d.getIntrinsicWidth());
      applyCheckMarkTint();
    } else mCheckMarkWidth = 0;
    requestLayout();
  }
  public Drawable getCheckMarkDrawable() { return mCheckMarkDrawable; }
  public void setCheckMarkTintList(ColorStateList tint) { mCheckMarkTintList = tint; applyCheckMarkTint(); }
  public ColorStateList getCheckMarkTintList() { return mCheckMarkTintList; }
  public void setCheckMarkTintMode(PorterDuff.Mode mode) { mCheckMarkTintMode = mode; mCheckMarkBlendMode = null; applyCheckMarkTint(); }
  public PorterDuff.Mode getCheckMarkTintMode() { return mCheckMarkTintMode; }
  public void setCheckMarkTintBlendMode(BlendMode mode) { mCheckMarkBlendMode = mode; applyCheckMarkTint(); }
  public BlendMode getCheckMarkTintBlendMode() { return mCheckMarkBlendMode; }
  private void applyCheckMarkTint() {
    if (mCheckMarkDrawable == null || (mCheckMarkTintList == null && mCheckMarkTintMode == null && mCheckMarkBlendMode == null)) return;
    mCheckMarkDrawable = mCheckMarkDrawable.mutate();
    if (mCheckMarkTintList != null) mCheckMarkDrawable.setTintList(mCheckMarkTintList);
    if (mCheckMarkBlendMode != null) mCheckMarkDrawable.setTintBlendMode(mCheckMarkBlendMode); else if (mCheckMarkTintMode != null) mCheckMarkDrawable.setTintMode(mCheckMarkTintMode);
    if (mCheckMarkDrawable.isStateful()) mCheckMarkDrawable.setState(getDrawableState());
    invalidate();
  }
  @Override public int getCompoundPaddingRight() { return super.getCompoundPaddingRight() + (isLayoutRtl() ? 0 : mCheckMarkWidth); }
  @Override public int getCompoundPaddingLeft() { return super.getCompoundPaddingLeft() + (isLayoutRtl() ? mCheckMarkWidth : 0); }
  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    final Drawable d = mCheckMarkDrawable;
    if (d == null) return;
    final int height = Math.max(0, d.getIntrinsicHeight()), width = mCheckMarkWidth;
    int y;
    switch (getGravity() & Gravity.VERTICAL_GRAVITY_MASK) {
      case Gravity.BOTTOM: y = getHeight() - height; break;
      case Gravity.CENTER_VERTICAL: y = (getHeight() - height) / 2; break;
      default: y = 0;
    }
    final int right = isLayoutRtl() ? getPaddingLeft() + width : getWidth() - getPaddingRight();
    d.setBounds(getScrollX() + right - width, y, getScrollX() + right, y + height);
    d.draw(canvas);
  }
  @Override protected int[] onCreateDrawableState(int extraSpace) {
    final int[] s = super.onCreateDrawableState(extraSpace + 1);
    if (isChecked()) mergeDrawableStates(s, CHECKED_STATE_SET);
    return s;
  }
  @Override protected void drawableStateChanged() {
    super.drawableStateChanged();
    if (mCheckMarkDrawable != null && mCheckMarkDrawable.isStateful() && mCheckMarkDrawable.setState(getDrawableState())) invalidate();
  }
  @Override protected boolean verifyDrawable(Drawable who) { return who == mCheckMarkDrawable || super.verifyDrawable(who); }
  @Override public void jumpDrawablesToCurrentState() { super.jumpDrawablesToCurrentState(); if (mCheckMarkDrawable != null) mCheckMarkDrawable.jumpToCurrentState(); }
  @Override public CharSequence getAccessibilityClassName() { return CheckedTextView.class.getName(); }
}
