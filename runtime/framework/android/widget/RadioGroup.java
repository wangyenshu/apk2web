package android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/** A LinearLayout where checking one RadioButton (or any CompoundButton) unchecks the others. */
public class RadioGroup extends LinearLayout {
  public interface OnCheckedChangeListener { void onCheckedChanged(RadioGroup group, int checkedId); }
  private int checked = -1;
  private boolean protect;
  private OnCheckedChangeListener listener;
  private final CompoundButton.OnCheckedChangeListener childListener = (b, isChecked) -> {
    if (protect) return;
    if (isChecked) setCheckedId(b.getId());
  };
  public RadioGroup(Context c) { this(c, null); }
  public RadioGroup(Context c, AttributeSet a) {
    super(c, a);
    if (a == null || a.getAttributeValue("http://schemas.android.com/apk/res/android", "orientation") == null) setOrientation(VERTICAL);
    if (a != null) { int id = a.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "checkedButton", -1); if (id != -1) checked = id; }
  }
  @Override public void addView(View child, int index, android.view.ViewGroup.LayoutParams p) {
    if (child instanceof CompoundButton) {
      CompoundButton b = (CompoundButton) child;
      if (b.getId() == View.NO_ID) b.setId(View.generateViewId());
      if (b.isChecked()) { if (checked != -1 && checked != b.getId()) setStateOf(checked, false); checked = b.getId(); }
      else if (b.getId() == checked) { protect = true; b.setChecked(true); protect = false; }
      b.setOnCheckedChangeListenerInternal(childListener);
    }
    super.addView(child, index, p);
  }
  private void setStateOf(int id, boolean on) {
    View v = findViewById(id);
    if (v instanceof CompoundButton) { protect = true; ((CompoundButton) v).setChecked(on); protect = false; }
  }
  private void setCheckedId(int id) {
    if (id == checked) return;
    if (checked != -1) setStateOf(checked, false);
    checked = id;
    if (id != -1) setStateOf(id, true);
    if (listener != null) listener.onCheckedChanged(this, id);
  }
  public void check(int id) { if (id != -1 && id == checked) return; setCheckedId(id); }
  public void clearCheck() { setCheckedId(-1); }
  public int getCheckedRadioButtonId() { return checked; }
  public void setOnCheckedChangeListener(OnCheckedChangeListener l) { listener = l; }
  @Override public LayoutParams generateLayoutParams(AttributeSet a) { return new LayoutParams(getContext(), a); }
  public static class LayoutParams extends LinearLayout.LayoutParams {
    public LayoutParams(int w, int h) { super(w, h); }
    public LayoutParams(int w, int h, float weight) { super(w, h, weight); }
    public LayoutParams(Context c, AttributeSet a) { super(c, a); }
    public LayoutParams(android.view.ViewGroup.LayoutParams p) { super(p); }
  }
}
