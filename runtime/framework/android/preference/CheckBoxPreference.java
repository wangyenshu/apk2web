package android.preference;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;

public class CheckBoxPreference extends TwoStatePreference {
  public CheckBoxPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) { super(context, attrs, defStyleAttr, defStyleRes); }
  public CheckBoxPreference(Context context, AttributeSet attrs, int defStyleAttr) { this(context, attrs, defStyleAttr, 0); }
  public CheckBoxPreference(Context context, AttributeSet attrs) { this(context, attrs, 0); }
  public CheckBoxPreference(Context context) { this(context, null); }
  @Override View onCreateWidget(ViewGroup frame) {
    android.widget.CheckBox w = new android.widget.CheckBox(getContext());
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
}
