package android.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.util.AttributeSet;

/** A drop-down: clicking opens the adapter's items in a dialog; choosing one selects it. */
public class Spinner extends AbsSpinner {
  public static final int MODE_DIALOG = 0, MODE_DROPDOWN = 1;
  private CharSequence prompt;
  public Spinner(Context c) { this(c, null); }
  public Spinner(Context c, int mode) { this(c, null); }
  public Spinner(Context c, AttributeSet a) { this(c, a, 0); }
  public Spinner(Context c, AttributeSet a, int d) {
    super(c, a, d);
    if (a != null) prompt = a.getAttributeValue("http://schemas.android.com/apk/res/android", "prompt");
    if (getBackground() == null) setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(0x22888888));
  }
  public Spinner(Context c, AttributeSet a, int d, int mode) { this(c, a, d); }
  @Override public boolean performClick() {
    super.performClick();
    if (mAdapter == null || mAdapter.getCount() == 0) return true;
    CharSequence[] items = new CharSequence[mAdapter.getCount()];
    for (int i = 0; i < items.length; i++) { Object o = mAdapter.getItem(i); items[i] = o == null ? "" : String.valueOf(o); }
    AlertDialog.Builder b = new AlertDialog.Builder(getContext());
    if (prompt != null) b.setTitle(prompt);
    b.setSingleChoiceItems(items, getSelectedItemPosition(), (dlg, which) -> { setSelection(which); dlg.dismiss(); });
    b.show();
    return true;
  }
  public void setPrompt(CharSequence p) { prompt = p; }
  public void setPromptId(int id) { prompt = getContext().getText(id); }
  public CharSequence getPrompt() { return prompt; }
  public void setDropDownWidth(int w) {}
  public void setDropDownVerticalOffset(int o) {}
  public void setDropDownHorizontalOffset(int o) {}
  public void setPopupBackgroundResource(int id) {}
  public void setGravity(int g) {}
}
