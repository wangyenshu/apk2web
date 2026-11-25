package android.preference;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;

public class SwitchPreference extends TwoStatePreference {
  public SwitchPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) { super(context, attrs, defStyleAttr, defStyleRes); }
  public SwitchPreference(Context context, AttributeSet attrs, int defStyleAttr) { this(context, attrs, defStyleAttr, 0); }
  public SwitchPreference(Context context, AttributeSet attrs) { this(context, attrs, 0); }
  public SwitchPreference(Context context) { this(context, null); }
  @Override View onCreateWidget(ViewGroup frame) {
    android.widget.Switch w = new android.widget.Switch(getContext());
    w.setId(android.R.id.checkbox);
    w.setFocusable(false);
    w.setClickable(false);
    return w;
  }
  @Override protected void onBindView(View view) {
    super.onBindView(view);
    View checkboxView = view.findViewById(android.R.id.checkbox);
    if (checkboxView == null) checkboxView = view.findViewById(android.R.id.switch_widget);
    if (checkboxView instanceof Checkable) ((Checkable) checkboxView).setChecked(mChecked);
    syncSummaryView(view);
  }
  private CharSequence mOn, mOff;
  public void setSwitchTextOn(CharSequence onText) { mOn = onText; notifyChanged(); }
  public void setSwitchTextOff(CharSequence offText) { mOff = offText; notifyChanged(); }
  public void setSwitchTextOn(int resId) { setSwitchTextOn(getContext().getString(resId)); }
  public void setSwitchTextOff(int resId) { setSwitchTextOff(getContext().getString(resId)); }
  public CharSequence getSwitchTextOn() { return mOn; }
  public CharSequence getSwitchTextOff() { return mOff; }
}
