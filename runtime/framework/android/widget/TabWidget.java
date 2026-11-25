package android.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/** The row of tab indicators of a TabHost; clicking one selects its tab. */
public class TabWidget extends LinearLayout implements View.OnFocusChangeListener {
  interface OnTabSelectionChanged { void onTabSelectionChanged(int tabIndex, boolean clicked); }
  OnTabSelectionChanged mSelectionChangedListener;
  private int mSelectedTab = -1;
  private boolean mStripEnabled = true;
  private Drawable mDivider;
  public TabWidget(Context c) { this(c, null); }
  public TabWidget(Context c, AttributeSet a) { this(c, a, 0); }
  public TabWidget(Context c, AttributeSet a, int d) {
    super(c, a);
    setOrientation(HORIZONTAL);
    setBaselineAligned(false);
    // focus comes to the current tab, not to the first one
    setFocusable(true);
    setOnFocusChangeListener(this);
  }
  /** an indicator taking focus (from the keyboard) selects its tab; focus on the widget itself goes to the current tab */
  @Override public void onFocusChange(View v, boolean hasFocus) {
    if (v == this && hasFocus && getTabCount() > 0) { if (mSelectedTab >= 0 && mSelectedTab < getTabCount()) getChildTabViewAt(mSelectedTab).requestFocus(); return; }
    if (hasFocus) {
      for (int i = 0; i < getTabCount(); i++) if (getChildTabViewAt(i) == v) {
        setCurrentTab(i);
        if (mSelectionChangedListener != null) mSelectionChangedListener.onTabSelectionChanged(i, false);
        break;
      }
    }
  }
  public View getChildTabViewAt(int index) { return getChildAt(index); }
  public int getTabCount() { return getChildCount(); }
  public void setDividerDrawable(Drawable d) { mDivider = d; }
  public void setDividerDrawable(int id) { mDivider = id == 0 ? null : getResources().getDrawable(id); }
  public void setLeftStripDrawable(Drawable d) {}
  public void setLeftStripDrawable(int id) {}
  public void setRightStripDrawable(Drawable d) {}
  public void setRightStripDrawable(int id) {}
  public void setStripEnabled(boolean e) { mStripEnabled = e; invalidate(); }
  public boolean isStripEnabled() { return mStripEnabled; }
  public void setCurrentTab(int index) {
    if (index < 0 || index >= getTabCount() || index == mSelectedTab) return;
    if (mSelectedTab != -1 && mSelectedTab < getTabCount()) getChildTabViewAt(mSelectedTab).setSelected(false);
    mSelectedTab = index;
    getChildTabViewAt(mSelectedTab).setSelected(true);
  }
  public void focusCurrentTab(int index) {
    final int oldTab = mSelectedTab;
    setCurrentTab(index);
    if (oldTab != index && index >= 0 && index < getTabCount()) getChildTabViewAt(index).requestFocus();
  }
  @Override public void setEnabled(boolean e) { super.setEnabled(e); for (int i = 0; i < getTabCount(); i++) getChildTabViewAt(i).setEnabled(e); }
  @Override public void addView(View child) {
    if (child.getLayoutParams() == null) {
      LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f);
      lp.setMargins(0, 0, 0, 0);
      child.setLayoutParams(lp);
    }
    child.setFocusable(true);
    child.setClickable(true);
    super.addView(child);
    final int index = getTabCount() - 1;
    child.setOnClickListener(v -> { if (mSelectionChangedListener != null) mSelectionChangedListener.onTabSelectionChanged(index, true); });
    child.setOnFocusChangeListener(this);
  }
  @Override public void removeAllViews() { super.removeAllViews(); mSelectedTab = -1; }
}
