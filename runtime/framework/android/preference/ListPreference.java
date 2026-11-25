package android.preference;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;

public class ListPreference extends DialogPreference {
  private CharSequence[] mEntries, mEntryValues;
  private String mValue, mSummary;
  private int mClickedDialogEntryIndex;
  private boolean mValueSet;
  public ListPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    if (attrs != null) {
      TypedArray a = context.obtainStyledAttributes(attrs, new int[] {android.R.attr.entries, android.R.attr.entryValues, android.R.attr.summary});
      mEntries = a.getTextArray(0);
      mEntryValues = a.getTextArray(1);
      CharSequence s = a.getText(2);
      mSummary = s == null ? null : s.toString();
      a.recycle();
    }
  }
  public ListPreference(Context context, AttributeSet attrs, int defStyleAttr) { this(context, attrs, defStyleAttr, 0); }
  public ListPreference(Context context, AttributeSet attrs) { this(context, attrs, 0); }
  public ListPreference(Context context) { this(context, null); }
  public void setEntries(CharSequence[] entries) { mEntries = entries; }
  public void setEntries(int entriesResId) { setEntries(getContext().getResources().getTextArray(entriesResId)); }
  public CharSequence[] getEntries() { return mEntries; }
  public void setEntryValues(CharSequence[] entryValues) { mEntryValues = entryValues; }
  public void setEntryValues(int entryValuesResId) { setEntryValues(getContext().getResources().getTextArray(entryValuesResId)); }
  public CharSequence[] getEntryValues() { return mEntryValues; }
  public void setValue(String value) {
    final boolean changed = !TextUtils.equals(mValue, value);
    if (changed || !mValueSet) {
      mValue = value;
      mValueSet = true;
      persistString(value);
      if (changed) notifyChanged();
    }
  }
  @Override public CharSequence getSummary() {
    final CharSequence entry = getEntry();
    if (mSummary == null) return super.getSummary();
    return String.format(mSummary, entry == null ? "" : entry);
  }
  @Override public void setSummary(CharSequence summary) {
    super.setSummary(summary);
    if (summary == null && mSummary != null) mSummary = null;
    else if (summary != null && !summary.equals(mSummary)) mSummary = summary.toString();
  }
  public void setValueIndex(int index) { if (mEntryValues != null) setValue(mEntryValues[index].toString()); }
  public String getValue() { return mValue; }
  public CharSequence getEntry() { int index = getValueIndex(); return index >= 0 && mEntries != null ? mEntries[index] : null; }
  public int findIndexOfValue(String value) {
    if (value != null && mEntryValues != null) for (int i = mEntryValues.length - 1; i >= 0; i--) if (mEntryValues[i].equals(value)) return i;
    return -1;
  }
  private int getValueIndex() { return findIndexOfValue(mValue); }
  @Override protected void onPrepareDialogBuilder(AlertDialog.Builder builder) {
    super.onPrepareDialogBuilder(builder);
    if (mEntries == null || mEntryValues == null) throw new IllegalStateException("ListPreference requires an entries array and an entryValues array.");
    mClickedDialogEntryIndex = getValueIndex();
    builder.setSingleChoiceItems(mEntries, mClickedDialogEntryIndex, (dialog, which) -> {
      mClickedDialogEntryIndex = which;
      ListPreference.this.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
      dialog.dismiss();
    });
    builder.setPositiveButton(null, null);
  }
  @Override protected void onDialogClosed(boolean positiveResult) {
    super.onDialogClosed(positiveResult);
    if (positiveResult && mClickedDialogEntryIndex >= 0 && mEntryValues != null) {
      String value = mEntryValues[mClickedDialogEntryIndex].toString();
      if (callChangeListener(value)) setValue(value);
    }
  }
  @Override protected Object onGetDefaultValue(TypedArray a, int index) { return a.getString(index); }
  @Override protected void onSetInitialValue(boolean restoreValue, Object defaultValue) { setValue(restoreValue ? getPersistedString(mValue) : (String) defaultValue); }
}
