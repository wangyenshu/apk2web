package android.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public abstract class TwoStatePreference extends Preference {
  private CharSequence mSummaryOn, mSummaryOff;
  boolean mChecked;
  private boolean mCheckedSet, mDisableDependentsState;
  public TwoStatePreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    if (attrs != null) {
      TypedArray a = context.obtainStyledAttributes(attrs, new int[] {android.R.attr.summaryOn, android.R.attr.summaryOff, android.R.attr.disableDependentsState});
      mSummaryOn = a.getText(0);
      mSummaryOff = a.getText(1);
      mDisableDependentsState = a.getBoolean(2, false);
      a.recycle();
    }
  }
  public TwoStatePreference(Context context, AttributeSet attrs, int defStyleAttr) { this(context, attrs, defStyleAttr, 0); }
  public TwoStatePreference(Context context, AttributeSet attrs) { this(context, attrs, 0); }
  public TwoStatePreference(Context context) { this(context, null); }
  @Override protected void onClick() {
    super.onClick();
    final boolean newValue = !isChecked();
    if (callChangeListener(newValue)) setChecked(newValue);
  }
  public void setChecked(boolean checked) {
    final boolean changed = mChecked != checked;
    if (changed || !mCheckedSet) {
      mChecked = checked;
      mCheckedSet = true;
      persistBoolean(checked);
      if (changed) { notifyDependencyChange(shouldDisableDependents()); notifyChanged(); }
    }
  }
  public boolean isChecked() { return mChecked; }
  @Override public boolean shouldDisableDependents() {
    boolean shouldDisable = mDisableDependentsState ? mChecked : !mChecked;
    return shouldDisable || super.shouldDisableDependents();
  }
  public void setSummaryOn(CharSequence summary) { mSummaryOn = summary; if (isChecked()) notifyChanged(); }
  public void setSummaryOn(int summaryResId) { setSummaryOn(getContext().getString(summaryResId)); }
  public CharSequence getSummaryOn() { return mSummaryOn; }
  public void setSummaryOff(CharSequence summary) { mSummaryOff = summary; if (!isChecked()) notifyChanged(); }
  public void setSummaryOff(int summaryResId) { setSummaryOff(getContext().getString(summaryResId)); }
  public CharSequence getSummaryOff() { return mSummaryOff; }
  public boolean getDisableDependentsState() { return mDisableDependentsState; }
  public void setDisableDependentsState(boolean disableDependentsState) { mDisableDependentsState = disableDependentsState; }
  @Override protected Object onGetDefaultValue(TypedArray a, int index) { return a.getBoolean(index, false); }
  @Override protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
    setChecked(restoreValue ? getPersistedBoolean(mChecked) : (Boolean) defaultValue);
  }
  void syncSummaryView(View view) {
    TextView summaryView = (TextView) view.findViewById(android.R.id.summary);
    if (summaryView != null) {
      boolean useDefaultSummary = true;
      if (mChecked && !TextUtils.isEmpty(mSummaryOn)) { summaryView.setText(mSummaryOn); useDefaultSummary = false; }
      else if (!mChecked && !TextUtils.isEmpty(mSummaryOff)) { summaryView.setText(mSummaryOff); useDefaultSummary = false; }
      if (useDefaultSummary) { final CharSequence summary = getSummary(); if (!TextUtils.isEmpty(summary)) { summaryView.setText(summary); useDefaultSummary = false; } }
      summaryView.setVisibility(useDefaultSummary ? View.GONE : View.VISIBLE);
    }
  }
}
