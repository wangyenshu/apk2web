package android.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import a2w.Attrs;
import a2w.Values;

/** A two-state button showing textOn or textOff, with the light bar of its background (layer android:id/toggle). */
public class ToggleButton extends CompoundButton {
  private CharSequence mTextOn, mTextOff;
  private Drawable mIndicatorDrawable;
  private float mDisabledAlpha = 0.5f;
  public ToggleButton(Context c) { this(c, null); }
  public ToggleButton(Context c, AttributeSet a) { this(c, a, 0); }
  public ToggleButton(Context c, AttributeSet a, int d) {
    super(c, a, d, "Widget.Button.Toggle");
    Attrs at = mCtorAttrs instanceof Attrs ? (Attrs) mCtorAttrs : Attrs.defaultsOnly(a2w.Styles.framework("Widget.Button.Toggle"));
    String v;
    mTextOn = (v = at.a("textOn")) != null ? text(v) : "ON";
    mTextOff = (v = at.a("textOff")) != null ? text(v) : "OFF";
    if ((v = at.a("disabledAlpha")) != null) mDisabledAlpha = Values.parseFloat(v, 0.5f);
    syncTextState();
    updateReferenceToIndicatorDrawable(getBackground());
  }
  public ToggleButton(Context c, AttributeSet a, int d, int r) { this(c, a, d); }
  @Override public void setChecked(boolean checked) { super.setChecked(checked); syncTextState(); }
  private void syncTextState() {
    boolean checked = isChecked();
    if (checked && mTextOn != null) setText(mTextOn);
    else if (!checked && mTextOff != null) setText(mTextOff);
  }
  public CharSequence getTextOn() { return mTextOn; }
  public void setTextOn(CharSequence textOn) { mTextOn = textOn; }
  public CharSequence getTextOff() { return mTextOff; }
  public void setTextOff(CharSequence textOff) { mTextOff = textOff; }
  public float getDisabledAlpha() { return mDisabledAlpha; }
  @Override public void setBackgroundDrawable(Drawable d) { super.setBackgroundDrawable(d); updateReferenceToIndicatorDrawable(d); }
  private void updateReferenceToIndicatorDrawable(Drawable d) {
    mIndicatorDrawable = d instanceof LayerDrawable ? ((LayerDrawable) d).findDrawableByLayerId(android.R.id.toggle) : null;
  }
  @Override protected void drawableStateChanged() {
    super.drawableStateChanged();
    if (mIndicatorDrawable != null) mIndicatorDrawable.setAlpha(isEnabled() ? 255 : (int) (255 * mDisabledAlpha));
  }
  @Override public CharSequence getAccessibilityClassName() { return ToggleButton.class.getName(); }
}
