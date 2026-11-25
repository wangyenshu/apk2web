package android.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;

/** Shows the selected item of its adapter (its single child). */
public abstract class AbsSpinner extends AdapterView<SpinnerAdapter> {
  SpinnerAdapter mAdapter;
  private final DataSetObserver observer = new DataSetObserver() {
    @Override public void onChanged() { mItemCount = mAdapter == null ? 0 : mAdapter.getCount(); if (mNextSelectedPosition >= mItemCount) mNextSelectedPosition = mItemCount - 1; if (mNextSelectedPosition < 0 && mItemCount > 0) mNextSelectedPosition = 0; refresh(); }
    @Override public void onInvalidated() { onChanged(); }
  };
  public AbsSpinner(Context c) { this(c, null); }
  public AbsSpinner(Context c, AttributeSet a) { this(c, a, 0); }
  public AbsSpinner(Context c, AttributeSet a, int d) {
    super(c, a, d);
    setClickable(true);
    if (a != null) {
      int id = a.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "entries", 0);
      if (id != 0) {
        ArrayAdapter<CharSequence> ad = new ArrayAdapter<>(c, android.R.layout.simple_spinner_item, c.getResources().getTextArray(id));
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        setAdapter(ad);
      }
    }
  }
  @Override public SpinnerAdapter getAdapter() { return mAdapter; }
  @Override public void setAdapter(SpinnerAdapter a) {
    if (mAdapter != null) mAdapter.unregisterDataSetObserver(observer);
    mAdapter = a;
    mItemCount = a == null ? 0 : a.getCount();
    if (a != null) a.registerDataSetObserver(observer);
    mNextSelectedPosition = mItemCount > 0 ? 0 : -1;
    refresh();
    fireSelected();
  }
  @Override public View getSelectedView() { return getChildCount() > 0 ? getChildAt(0) : null; }
  @Override public void setSelection(int position) { setSelection(position, false); }
  public void setSelection(int position, boolean animate) {
    if (position < 0 || position >= mItemCount || position == mNextSelectedPosition) return;
    mNextSelectedPosition = position;
    refresh();
    fireSelected();
  }
  void fireSelected() {
    OnItemSelectedListener l = getOnItemSelectedListener();
    if (l == null) return;
    if (mNextSelectedPosition >= 0) l.onItemSelected(this, getSelectedView(), mNextSelectedPosition, mAdapter.getItemId(mNextSelectedPosition));
    else l.onNothingSelected(this);
  }
  void refresh() {
    View old = getSelectedView();
    removeAllViewsInLayout();
    if (mAdapter != null && mNextSelectedPosition >= 0 && mNextSelectedPosition < mAdapter.getCount()) {
      View v = mAdapter.getView(mNextSelectedPosition, old, this);
      if (v.getLayoutParams() == null) v.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
      addViewInLayout(v, 0, v.getLayoutParams(), true);
    }
    requestLayout();
    invalidate();
  }
  @Override protected void onMeasure(int ws, int hs) {
    View v = getSelectedView();
    int w = 0, h = 0;
    if (v != null) { measureChild(v, ws, hs); w = v.getMeasuredWidth(); h = v.getMeasuredHeight(); }
    w += getPaddingLeft() + getPaddingRight();
    h += getPaddingTop() + getPaddingBottom();
    setMeasuredDimension(resolveSize(Math.max(w, getSuggestedMinimumWidth()), ws), resolveSize(Math.max(h, getSuggestedMinimumHeight()), hs));
  }
  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
    View v = getSelectedView();
    if (v != null) v.layout(getPaddingLeft(), getPaddingTop(), r - l - getPaddingRight(), b - t - getPaddingBottom());
  }
  public int pointToPosition(int x, int y) { return mNextSelectedPosition; }
}
