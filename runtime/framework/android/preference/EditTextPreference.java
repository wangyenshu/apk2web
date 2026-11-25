package android.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.LinearLayout;

public class EditTextPreference extends DialogPreference {
  private final EditText mEditText;
  private String mText;
  private boolean mTextSet;
  public EditTextPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    mEditText = new EditText(context, attrs);
    mEditText.setId(android.R.id.edit);
    mEditText.setEnabled(true);
  }
  public EditTextPreference(Context context, AttributeSet attrs, int defStyleAttr) { this(context, attrs, defStyleAttr, 0); }
  public EditTextPreference(Context context, AttributeSet attrs) { this(context, attrs, 0); }
  public EditTextPreference(Context context) { this(context, null); }
  public void setText(String text) {
    final boolean changed = !TextUtils.equals(mText, text);
    if (changed || !mTextSet) {
      mText = text;
      mTextSet = true;
      persistString(text);
      if (changed) { notifyDependencyChange(shouldDisableDependents()); notifyChanged(); }
    }
  }
  public String getText() { return mText; }
  @Override protected View onCreateDialogView() {
    View v = super.onCreateDialogView();
    if (v != null) return v;
    LinearLayout l = new LinearLayout(getContext());
    l.setOrientation(LinearLayout.VERTICAL);
    int pad = (int) dp(getContext(), 16);
    l.setPadding(pad, pad / 2, pad, 0);
    android.widget.TextView msg = new android.widget.TextView(getContext());
    msg.setId(android.R.id.message);
    l.addView(msg);
    return l;
  }
  @Override protected void onBindDialogView(View view) {
    super.onBindDialogView(view);
    EditText editText = mEditText;
    editText.setText(getText());
    ViewParent oldParent = editText.getParent();
    if (oldParent != view) {
      if (oldParent != null) ((ViewGroup) oldParent).removeView(editText);
      onAddEditTextToDialogView(view, editText);
    }
  }
  protected void onAddEditTextToDialogView(View dialogView, EditText editText) {
    ViewGroup container = (ViewGroup) dialogView.findViewById(android.R.id.edit);
    if (container == null && dialogView instanceof ViewGroup) container = (ViewGroup) dialogView;
    if (container != null) container.addView(editText, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
  }
  @Override protected void onDialogClosed(boolean positiveResult) {
    super.onDialogClosed(positiveResult);
    if (positiveResult) {
      String value = mEditText.getText().toString();
      if (callChangeListener(value)) setText(value);
    }
  }
  @Override protected Object onGetDefaultValue(TypedArray a, int index) { return a.getString(index); }
  @Override protected void onSetInitialValue(boolean restoreValue, Object defaultValue) { setText(restoreValue ? getPersistedString(mText) : (String) defaultValue); }
  @Override public boolean shouldDisableDependents() { return TextUtils.isEmpty(mText) || super.shouldDisableDependents(); }
  public EditText getEditText() { return mEditText; }
}
