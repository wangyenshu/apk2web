package android.widget;

import android.content.Context;
import android.util.AttributeSet;

/** A check box: Widget.CompoundButton.CheckBox's indicator (btn_check) beside the text. */
public class CheckBox extends CompoundButton {
  public CheckBox(Context c) { this(c, null); }
  public CheckBox(Context c, AttributeSet a) { this(c, a, 0); }
  public CheckBox(Context c, AttributeSet a, int d) { super(c, a, d, "Widget.CompoundButton.CheckBox"); }
  public CheckBox(Context c, AttributeSet a, int d, int r) { this(c, a, d); }
  @Override public CharSequence getAccessibilityClassName() { return CheckBox.class.getName(); }
}
