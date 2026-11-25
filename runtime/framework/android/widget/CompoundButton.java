package android.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import a2w.Attrs;
import a2w.Values;

public abstract class CompoundButton extends Button implements Checkable {
  public interface OnCheckedChangeListener { void onCheckedChanged(CompoundButton b, boolean checked); }
  static final int[] CHECKED_STATE_SET = {android.R.attr.state_checked};
  boolean mChecked, mBroadcasting;
  Drawable mButtonDrawable;
  OnCheckedChangeListener mOnCheckedChangeListener, mInternalListener;
  public CompoundButton(Context c) { this(c, null); }
  public CompoundButton(Context c, AttributeSet a) { this(c, a, 0); }
  public CompoundButton(Context c, AttributeSet a, int d) { this(c, a, d, "Widget.CompoundButton"); }
  public CompoundButton(Context c, AttributeSet a, int d, int r) { this(c, a, d); }
  /** fw: the framework style with the widget's defaults (the indicator: button) */
  CompoundButton(Context c, AttributeSet a, int d, String fw) {
    super(c, a, d, fw);
    Attrs at = mCtorAttrs instanceof Attrs ? (Attrs) mCtorAttrs : Attrs.defaultsOnly(a2w.Styles.framework(fw));
    String v = at.a("button");
    if (v != null && Values.isRef(v)) setButtonDrawable(getResources().getDrawable(Values.refId(v)));
    if ((v = at.a("buttonTint")) != null) { android.content.res.ColorStateList l = tintList(v); if (l != null) setButtonTintList(l); }
    if ((v = at.a("buttonTintMode")) != null) setButtonTintMode(tintMode(v));
    v = at.a("checked");
    if (v != null) setChecked(v.equals("true"));
  }
  android.content.res.ColorStateList tintList(String v) {
    try {
      if (Values.isRef(v)) return getResources().getColorStateList(Values.refId(v));
      if (v.startsWith("#")) return android.content.res.ColorStateList.valueOf(Values.parseColor(v));
    } catch (RuntimeException e) { /* none */ }
    return null;
  }
  static android.graphics.PorterDuff.Mode tintMode(String v) {
    switch (v) {
      case "src_over": case "3": return android.graphics.PorterDuff.Mode.SRC_OVER;
      case "src_atop": case "9": return android.graphics.PorterDuff.Mode.SRC_ATOP;
      case "multiply": case "14": return android.graphics.PorterDuff.Mode.MULTIPLY;
      case "screen": case "15": return android.graphics.PorterDuff.Mode.SCREEN;
      case "add": case "16": return android.graphics.PorterDuff.Mode.ADD;
      default: return android.graphics.PorterDuff.Mode.SRC_IN;
    }
  }
  public void toggle() { setChecked(!mChecked); }
  @Override public boolean performClick() { toggle(); return super.performClick(); }
  public boolean isChecked() { return mChecked; }
  public void setChecked(boolean c) {
    if (mChecked != c) {
      mChecked = c;
      refreshDrawableState();
      if (mBroadcasting) return;
      mBroadcasting = true;
      if (mOnCheckedChangeListener != null) mOnCheckedChangeListener.onCheckedChanged(this, mChecked);
      if (mInternalListener != null) mInternalListener.onCheckedChanged(this, mChecked);
      mBroadcasting = false;
    }
  }
  public void setOnCheckedChangeListener(OnCheckedChangeListener l) { mOnCheckedChangeListener = l; }
  void setOnCheckedChangeListenerInternal(OnCheckedChangeListener l) { mInternalListener = l; }
  public void setButtonDrawable(int id) { setButtonDrawable(id == 0 ? null : getResources().getDrawable(id)); }
  public void setButtonDrawable(Drawable d) {
    if (mButtonDrawable != null) mButtonDrawable.setCallback(null);
    mButtonDrawable = d;
    if (d != null) { d.setCallback(this); d.setState(getDrawableState()); setMinHeight(d.getIntrinsicHeight()); }
    applyButtonTint();
    refreshDrawableState();
  }
  public Drawable getButtonDrawable() { return mButtonDrawable; }
  public void setButtonIcon(android.graphics.drawable.Icon icon) { setButtonDrawable(icon == null ? null : icon.loadDrawable(getContext())); }
  android.content.res.ColorStateList mButtonTintList;
  android.graphics.PorterDuff.Mode mButtonTintMode;
  android.graphics.BlendMode mButtonBlendMode;
  public void setButtonTintList(android.content.res.ColorStateList tint) { mButtonTintList = tint; applyButtonTint(); }
  public android.content.res.ColorStateList getButtonTintList() { return mButtonTintList; }
  public void setButtonTintMode(android.graphics.PorterDuff.Mode mode) { mButtonTintMode = mode; mButtonBlendMode = null; applyButtonTint(); }
  public android.graphics.PorterDuff.Mode getButtonTintMode() { return mButtonTintMode; }
  public void setButtonTintBlendMode(android.graphics.BlendMode mode) { mButtonBlendMode = mode; applyButtonTint(); }
  public android.graphics.BlendMode getButtonTintBlendMode() { return mButtonBlendMode; }
  private void applyButtonTint() {
    if (mButtonDrawable == null || (mButtonTintList == null && mButtonTintMode == null && mButtonBlendMode == null)) return;
    mButtonDrawable = mButtonDrawable.mutate();
    if (mButtonTintList != null) mButtonDrawable.setTintList(mButtonTintList);
    if (mButtonBlendMode != null) mButtonDrawable.setTintBlendMode(mButtonBlendMode);
    else if (mButtonTintMode != null) mButtonDrawable.setTintMode(mButtonTintMode);
    if (mButtonDrawable.isStateful()) mButtonDrawable.setState(getDrawableState());
    invalidate();
  }
  @Override public void jumpDrawablesToCurrentState() { super.jumpDrawablesToCurrentState(); if (mButtonDrawable != null) mButtonDrawable.jumpToCurrentState(); }
  @Override public int getAutofillType() { return isEnabled() ? AUTOFILL_TYPE_TOGGLE : AUTOFILL_TYPE_NONE; }
  @Override public android.view.autofill.AutofillValue getAutofillValue() { return isEnabled() ? android.view.autofill.AutofillValue.forToggle(isChecked()) : null; }
  @Override public void autofill(android.view.autofill.AutofillValue value) { if (isEnabled() && value != null && value.isToggle()) setChecked(value.getToggleValue()); }
  @Override public int getCompoundPaddingLeft() { int p = super.getCompoundPaddingLeft(); if (mButtonDrawable != null) p += mButtonDrawable.getIntrinsicWidth(); return p; }
  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    if (mButtonDrawable != null) {
      int h = mButtonDrawable.getIntrinsicHeight(), w = mButtonDrawable.getIntrinsicWidth();
      int vg = getGravity() & Gravity.VERTICAL_GRAVITY_MASK;
      int y = vg == Gravity.BOTTOM ? getHeight() - h : vg == Gravity.CENTER_VERTICAL ? (getHeight() - h) / 2 : 0;
      mButtonDrawable.setBounds(0, y, w, y + h);
      mButtonDrawable.draw(canvas);
    }
  }
  @Override protected int[] onCreateDrawableState(int extra) {
    int[] s = super.onCreateDrawableState(extra + 1);
    if (isChecked()) mergeDrawableStates(s, CHECKED_STATE_SET);
    return s;
  }
  @Override protected void drawableStateChanged() { super.drawableStateChanged(); if (mButtonDrawable != null) { mButtonDrawable.setState(getDrawableState()); invalidate(); } }
  @Override protected boolean verifyDrawable(Drawable who) { return super.verifyDrawable(who) || who == mButtonDrawable; }
  // ---- saved state: the checked state (as Android's)
  static class SavedState extends BaseSavedState {
    boolean checked;
    SavedState(android.os.Parcelable superState) { super(superState); }
    private SavedState(android.os.Parcel in) { super(in); checked = in.readInt() != 0; }
    @Override public void writeToParcel(android.os.Parcel out, int flags) { super.writeToParcel(out, flags); out.writeInt(checked ? 1 : 0); }
    @Override public String toString() { return "CompoundButton.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " checked=" + checked + "}"; }
    public static final android.os.Parcelable.Creator<SavedState> CREATOR = new android.os.Parcelable.Creator<SavedState>() {
      public SavedState createFromParcel(android.os.Parcel in) { return new SavedState(in); }
      public SavedState[] newArray(int size) { return new SavedState[size]; }
    };
  }
  @Override public android.os.Parcelable onSaveInstanceState() {
    SavedState ss = new SavedState(super.onSaveInstanceState());
    ss.checked = isChecked();
    return ss;
  }
  @Override public void onRestoreInstanceState(android.os.Parcelable state) {
    if (!(state instanceof SavedState)) { super.onRestoreInstanceState(state); return; }
    SavedState ss = (SavedState) state;
    super.onRestoreInstanceState(ss.getSuperState());
    setChecked(ss.checked);
    requestLayout();
  }
}
