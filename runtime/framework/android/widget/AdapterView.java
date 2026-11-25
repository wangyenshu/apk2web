package android.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.View;
import android.view.ViewGroup;

public abstract class AdapterView<T extends Adapter> extends ViewGroup {
  public static final int ITEM_VIEW_TYPE_IGNORE = -1, ITEM_VIEW_TYPE_HEADER_OR_FOOTER = -2, INVALID_POSITION = -1;
  public static final long INVALID_ROW_ID = Long.MIN_VALUE;
  public interface OnItemClickListener { void onItemClick(AdapterView<?> parent, View view, int position, long id); }
  public interface OnItemLongClickListener { boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id); }
  public interface OnItemSelectedListener { void onItemSelected(AdapterView<?> parent, View view, int position, long id); void onNothingSelected(AdapterView<?> parent); }
  public static class AdapterContextMenuInfo implements ContextMenu.ContextMenuInfo {
    public View targetView; public int position; public long id;
    public AdapterContextMenuInfo(View v, int p, long i) { targetView = v; position = p; id = i; }
  }
  int mFirstPosition;
  int mItemCount, mOldItemCount;
  boolean mDataChanged;
  int mSelectedPosition = INVALID_POSITION, mNextSelectedPosition = INVALID_POSITION;
  OnItemClickListener mOnItemClickListener;
  OnItemLongClickListener mOnItemLongClickListener;
  OnItemSelectedListener mOnItemSelectedListener;
  View mEmptyView;

  public AdapterView(Context c) { super(c); }
  public AdapterView(Context c, AttributeSet a) { super(c, a); }
  public AdapterView(Context c, AttributeSet a, int d) { super(c, a, d); }
  public void setOnItemClickListener(OnItemClickListener l) { mOnItemClickListener = l; }
  public final OnItemClickListener getOnItemClickListener() { return mOnItemClickListener; }
  public boolean performItemClick(View view, int position, long id) {
    if (mOnItemClickListener != null) { playSoundEffect(0); mOnItemClickListener.onItemClick(this, view, position, id); return true; }
    return false;
  }
  public void setOnItemLongClickListener(OnItemLongClickListener l) { if (!isLongClickable()) setLongClickable(true); mOnItemLongClickListener = l; }
  public final OnItemLongClickListener getOnItemLongClickListener() { return mOnItemLongClickListener; }
  public void setOnItemSelectedListener(OnItemSelectedListener l) { mOnItemSelectedListener = l; }
  public final OnItemSelectedListener getOnItemSelectedListener() { return mOnItemSelectedListener; }
  public abstract T getAdapter();
  public abstract void setAdapter(T adapter);
  public abstract View getSelectedView();
  public abstract void setSelection(int position);
  @Override public void addView(View child) { throw new UnsupportedOperationException("addView(View) is not supported in AdapterView"); }
  @Override public void addView(View child, int index) { throw new UnsupportedOperationException("addView(View, int) is not supported in AdapterView"); }
  @Override public void addView(View child, LayoutParams params) { throw new UnsupportedOperationException("addView(View, LayoutParams) is not supported in AdapterView"); }
  @Override public void addView(View child, int index, LayoutParams params) { throw new UnsupportedOperationException("addView(View, int, LayoutParams) is not supported in AdapterView"); }
  @Override public void removeView(View child) { throw new UnsupportedOperationException("removeView(View) is not supported in AdapterView"); }
  @Override public void removeViewAt(int index) { throw new UnsupportedOperationException("removeViewAt(int) is not supported in AdapterView"); }
  @Override public void removeAllViews() { throw new UnsupportedOperationException("removeAllViews() is not supported in AdapterView"); }
  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {}
  public int getSelectedItemPosition() { return mNextSelectedPosition; }
  public long getSelectedItemId() { return mNextSelectedPosition < 0 || getAdapter() == null ? INVALID_ROW_ID : getAdapter().getItemId(mNextSelectedPosition); }
  public Object getSelectedItem() { T a = getAdapter(); int s = getSelectedItemPosition(); return a != null && a.getCount() > 0 && s >= 0 ? a.getItem(s) : null; }
  public int getCount() { return mItemCount; }
  public int getPositionForView(View view) {
    View listItem = view;
    try {
      View v;
      while (!(v = (View) listItem.getParent()).equals(this)) listItem = v;
    } catch (ClassCastException e) { return INVALID_POSITION; } catch (NullPointerException e) { return INVALID_POSITION; }
    final int childCount = getChildCount();
    for (int i = 0; i < childCount; i++) if (getChildAt(i).equals(listItem)) return mFirstPosition + i;
    return INVALID_POSITION;
  }
  public int getFirstVisiblePosition() { return mFirstPosition; }
  public int getLastVisiblePosition() { return mFirstPosition + getChildCount() - 1; }
  public void setEmptyView(View v) { mEmptyView = v; updateEmptyStatus(); }
  public View getEmptyView() { return mEmptyView; }
  void updateEmptyStatus() {
    T a = getAdapter();
    boolean empty = a == null || a.isEmpty();
    if (mEmptyView != null) { mEmptyView.setVisibility(empty ? VISIBLE : GONE); setVisibility(empty ? GONE : VISIBLE); }
  }
  public Object getItemAtPosition(int p) { T a = getAdapter(); return a == null || p < 0 ? null : a.getItem(p); }
  public long getItemIdAtPosition(int p) { T a = getAdapter(); return a == null || p < 0 ? INVALID_ROW_ID : a.getItemId(p); }
  @Override public void setOnClickListener(OnClickListener l) { throw new RuntimeException("Don't call setOnClickListener for an AdapterView. You probably want setOnItemClickListener instead"); }
  class AdapterDataSetObserver extends DataSetObserver {
    @Override public void onChanged() {
      mDataChanged = true;
      mOldItemCount = mItemCount;
      mItemCount = getAdapter().getCount();
      requestLayout();
      invalidate();
      updateEmptyStatus();
    }
    @Override public void onInvalidated() {
      mDataChanged = true;
      mOldItemCount = mItemCount;
      mItemCount = 0;
      requestLayout();
      updateEmptyStatus();
    }
  }
}
