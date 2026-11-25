package android.preference;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import java.util.HashSet;
import java.util.Set;

public class MultiSelectListPreference extends DialogPreference {
  private CharSequence[] mEntries, mEntryValues;
  private final Set<String> mValues = new HashSet<>(), mNewValues = new HashSet<>();
  private boolean mPreferenceChanged;
  public MultiSelectListPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    if (attrs != null) {
      TypedArray a = context.obtainStyledAttributes(attrs, new int[] {android.R.attr.entries, android.R.attr.entryValues});
      mEntries = a.getTextArray(0);
      mEntryValues = a.getTextArray(1);
      a.recycle();
    }
  }
  public MultiSelectListPreference(Context context, AttributeSet attrs, int defStyleAttr) { this(context, attrs, defStyleAttr, 0); }
  public MultiSelectListPreference(Context context, AttributeSet attrs) { this(context, attrs, 0); }
  public MultiSelectListPreference(Context context) { this(context, null); }
  public void setEntries(CharSequence[] entries) { mEntries = entries; }
  public void setEntries(int entriesResId) { setEntries(getContext().getResources().getTextArray(entriesResId)); }
  public CharSequence[] getEntries() { return mEntries; }
  public void setEntryValues(CharSequence[] entryValues) { mEntryValues = entryValues; }
  public void setEntryValues(int entryValuesResId) { setEntryValues(getContext().getResources().getTextArray(entryValuesResId)); }
  public CharSequence[] getEntryValues() { return mEntryValues; }
  public void setValues(Set<String> values) { mValues.clear(); mValues.addAll(values); persistStringSet(values); }
  public Set<String> getValues() { return mValues; }
  public int findIndexOfValue(String value) {
    if (value != null && mEntryValues != null) for (int i = mEntryValues.length - 1; i >= 0; i--) if (mEntryValues[i].equals(value)) return i;
    return -1;
  }
  @Override protected void onPrepareDialogBuilder(AlertDialog.Builder builder) {
    super.onPrepareDialogBuilder(builder);
    if (mEntries == null || mEntryValues == null) throw new IllegalStateException("MultiSelectListPreference requires an entries array and an entryValues array.");
    boolean[] checkedItems = new boolean[mEntryValues.length];
    for (int i = 0; i < mEntryValues.length; i++) checkedItems[i] = mValues.contains(mEntryValues[i].toString());
    builder.setMultiChoiceItems(mEntries, checkedItems, (dialog, which, isChecked) -> {
      if (isChecked) mPreferenceChanged |= mNewValues.add(mEntryValues[which].toString());
      else mPreferenceChanged |= mNewValues.remove(mEntryValues[which].toString());
    });
    mNewValues.clear();
    mNewValues.addAll(mValues);
  }
  @Override protected void onDialogClosed(boolean positiveResult) {
    super.onDialogClosed(positiveResult);
    if (positiveResult && mPreferenceChanged) { final Set<String> values = new HashSet<>(mNewValues); if (callChangeListener(values)) setValues(values); }
    mPreferenceChanged = false;
  }
  @Override protected Object onGetDefaultValue(TypedArray a, int index) {
    final CharSequence[] defaultValues = a.getTextArray(index);
    final Set<String> result = new HashSet<>();
    if (defaultValues != null) for (CharSequence v : defaultValues) result.add(v.toString());
    return result;
  }
  @SuppressWarnings("unchecked") @Override protected void onSetInitialValue(boolean restoreValue, Object defaultValue) { setValues(restoreValue ? getPersistedStringSet(mValues) : (Set<String>) defaultValue); }
}
