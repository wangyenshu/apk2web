package android.preference;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class PreferenceCategory extends PreferenceGroup {
  public PreferenceCategory(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) { super(context, attrs, defStyleAttr, defStyleRes); }
  public PreferenceCategory(Context context, AttributeSet attrs, int defStyleAttr) { this(context, attrs, defStyleAttr, 0); }
  public PreferenceCategory(Context context, AttributeSet attrs) { this(context, attrs, 0); }
  public PreferenceCategory(Context context) { this(context, null); }
  @Override protected boolean onPrepareAddPreference(Preference preference) {
    if (preference instanceof PreferenceCategory) throw new IllegalArgumentException("Cannot add a " + PreferenceCategory.class.getSimpleName() + " directly to a " + PreferenceCategory.class.getSimpleName());
    return super.onPrepareAddPreference(preference);
  }
  @Override public boolean isEnabled() { return false; }
  @Override public boolean shouldDisableDependents() { return !super.isEnabled(); }
  @Override protected View onCreateView(ViewGroup parent) {
    if (getLayoutResource() != 0) return super.onCreateView(parent);
    return android.view.LayoutInflater.from(getContext()).inflate(android.R.layout.preference_category, parent, false);
  }
  @Override protected void onBindView(View view) {
    android.widget.TextView t = (android.widget.TextView) view.findViewById(android.R.id.title);
    if (t != null) t.setText(getTitle());
    else if (view instanceof android.widget.TextView) ((android.widget.TextView) view).setText(getTitle());
  }
}
