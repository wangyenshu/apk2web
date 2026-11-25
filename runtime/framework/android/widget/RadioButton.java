package android.widget;

import android.content.Context;
import android.util.AttributeSet;

/** A radio button: checked by a click, unchecked only by its RadioGroup (or setChecked). */
public class RadioButton extends CompoundButton {
  public RadioButton(Context c) { this(c, null); }
  public RadioButton(Context c, AttributeSet a) { this(c, a, 0); }
  public RadioButton(Context c, AttributeSet a, int d) { super(c, a, d, "Widget.CompoundButton.RadioButton"); }
  public RadioButton(Context c, AttributeSet a, int d, int r) { this(c, a, d); }
  @Override public void toggle() { if (!isChecked()) super.toggle(); }
  @Override public CharSequence getAccessibilityClassName() { return RadioButton.class.getName(); }
}
