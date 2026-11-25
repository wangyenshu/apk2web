package android.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.BitSet;

/** A ListView of groups that expand to show their children; the flat list is rebuilt from the expanded set. */
public class ExpandableListView extends ListView {
  public static final int PACKED_POSITION_TYPE_GROUP = 0, PACKED_POSITION_TYPE_CHILD = 1, PACKED_POSITION_TYPE_NULL = 2;
  public static final long PACKED_POSITION_VALUE_NULL = 0x00000000FFFFFFFFL;
  public static final int CHILD_INDICATOR_INHERIT = -1;
  public interface OnGroupCollapseListener { void onGroupCollapse(int groupPosition); }
  public interface OnGroupExpandListener { void onGroupExpand(int groupPosition); }
  public interface OnGroupClickListener { boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id); }
  public interface OnChildClickListener { boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id); }
  public static class ExpandableListContextMenuInfo implements ContextMenu.ContextMenuInfo {
    public View targetView; public long packedPosition, id;
    public ExpandableListContextMenuInfo(View targetView, long packedPosition, long id) { this.targetView = targetView; this.packedPosition = packedPosition; this.id = id; }
  }

  private ExpandableListAdapter mExpAdapter;
  private final BitSet mExpanded = new BitSet();
  /** flat position -> packed position */
  private final ArrayList<Long> mFlat = new ArrayList<>();
  private OnGroupCollapseListener mOnGroupCollapseListener;
  private OnGroupExpandListener mOnGroupExpandListener;
  private OnGroupClickListener mOnGroupClickListener;
  private OnChildClickListener mOnChildClickListener;
  private Drawable mGroupIndicator;
  private boolean mDefaultIndicator = true;
  private int mIndicatorLeft;
  private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

  public ExpandableListView(Context c) { this(c, null); }
  public ExpandableListView(Context c, AttributeSet a) { this(c, a, 0); }
  public ExpandableListView(Context c, AttributeSet a, int d) {
    super(c, a, d);
    String ns = "http://schemas.android.com/apk/res/android";
    if (a != null && a.getAttributeValue(ns, "groupIndicator") != null) {
      int id = a.getAttributeResourceValue(ns, "groupIndicator", 0);
      mDefaultIndicator = false;
      if (id != 0) mGroupIndicator = c.getResources().getDrawable(id);
    }
  }

  private final class Connector extends BaseAdapter {
    final DataSetObserver obs = new DataSetObserver() {
      @Override public void onChanged() { rebuild(); notifyDataSetChanged(); }
      @Override public void onInvalidated() { rebuild(); notifyDataSetInvalidated(); }
    };
    public int getCount() { return mFlat.size(); }
    public Object getItem(int p) { long pk = mFlat.get(p); int g = getPackedPositionGroup(pk); return getPackedPositionType(pk) == PACKED_POSITION_TYPE_CHILD ? mExpAdapter.getChild(g, getPackedPositionChild(pk)) : mExpAdapter.getGroup(g); }
    public long getItemId(int p) {
      long pk = mFlat.get(p); int g = getPackedPositionGroup(pk);
      if (getPackedPositionType(pk) == PACKED_POSITION_TYPE_CHILD) return mExpAdapter.getCombinedChildId(mExpAdapter.getGroupId(g), mExpAdapter.getChildId(g, getPackedPositionChild(pk)));
      return mExpAdapter.getCombinedGroupId(mExpAdapter.getGroupId(g));
    }
    @Override public boolean hasStableIds() { return mExpAdapter.hasStableIds(); }
    @Override public int getViewTypeCount() {
      if (mExpAdapter instanceof HeterogeneousExpandableList) { HeterogeneousExpandableList h = (HeterogeneousExpandableList) mExpAdapter; return h.getGroupTypeCount() + h.getChildTypeCount(); }
      return 2;
    }
    @Override public int getItemViewType(int p) {
      long pk = mFlat.get(p); int g = getPackedPositionGroup(pk);
      boolean child = getPackedPositionType(pk) == PACKED_POSITION_TYPE_CHILD;
      if (mExpAdapter instanceof HeterogeneousExpandableList) {
        HeterogeneousExpandableList h = (HeterogeneousExpandableList) mExpAdapter;
        return child ? h.getGroupTypeCount() + h.getChildType(g, getPackedPositionChild(pk)) : h.getGroupType(g);
      }
      return child ? 1 : 0;
    }
    @Override public boolean isEnabled(int p) {
      long pk = mFlat.get(p);
      return getPackedPositionType(pk) != PACKED_POSITION_TYPE_CHILD || mExpAdapter.isChildSelectable(getPackedPositionGroup(pk), getPackedPositionChild(pk));
    }
    @Override public boolean areAllItemsEnabled() { return mExpAdapter.areAllItemsEnabled(); }
    public View getView(int p, View convert, ViewGroup parent) {
      long pk = mFlat.get(p); int g = getPackedPositionGroup(pk);
      if (getPackedPositionType(pk) == PACKED_POSITION_TYPE_CHILD) {
        int c = getPackedPositionChild(pk);
        return mExpAdapter.getChildView(g, c, c == mExpAdapter.getChildrenCount(g) - 1, convert, parent);
      }
      return mExpAdapter.getGroupView(g, mExpanded.get(g), convert, parent);
    }
  }
  private Connector mConnector;

  void rebuild() {
    mFlat.clear();
    if (mExpAdapter == null) return;
    int n = mExpAdapter.getGroupCount();
    for (int g = 0; g < n; g++) {
      mFlat.add(getPackedPositionForGroup(g));
      if (mExpanded.get(g)) for (int c = 0, cn = mExpAdapter.getChildrenCount(g); c < cn; c++) mFlat.add(getPackedPositionForChild(g, c));
    }
  }
  @Override public void setAdapter(ListAdapter adapter) { throw new RuntimeException("For ExpandableListView, use setAdapter(ExpandableListAdapter) instead of setAdapter(ListAdapter)"); }
  public void setAdapter(ExpandableListAdapter adapter) {
    if (mExpAdapter != null && mConnector != null) mExpAdapter.unregisterDataSetObserver(mConnector.obs);
    mExpAdapter = adapter;
    mExpanded.clear();
    rebuild();
    mConnector = adapter == null ? null : new Connector();
    if (adapter != null) adapter.registerDataSetObserver(mConnector.obs);
    super.setAdapter(mConnector);
  }
  public ExpandableListAdapter getExpandableListAdapter() { return mExpAdapter; }
  public boolean expandGroup(int g) { return expandGroup(g, false); }
  public boolean expandGroup(int g, boolean animate) {
    if (mExpAdapter == null || g < 0 || g >= mExpAdapter.getGroupCount() || mExpanded.get(g)) return false;
    mExpanded.set(g);
    mExpAdapter.onGroupExpanded(g);
    refresh();
    if (mOnGroupExpandListener != null) mOnGroupExpandListener.onGroupExpand(g);
    return true;
  }
  public boolean collapseGroup(int g) {
    if (mExpAdapter == null || !mExpanded.get(g)) return false;
    mExpanded.clear(g);
    mExpAdapter.onGroupCollapsed(g);
    refresh();
    if (mOnGroupCollapseListener != null) mOnGroupCollapseListener.onGroupCollapse(g);
    return true;
  }
  private void refresh() { rebuild(); if (mConnector != null) mConnector.notifyDataSetChanged(); }
  public boolean isGroupExpanded(int g) { return mExpanded.get(g); }
  @Override public boolean performItemClick(View v, int position, long id) {
    if (position < 0 || position >= mFlat.size()) return super.performItemClick(v, position, id);
    long pk = mFlat.get(position);
    int g = getPackedPositionGroup(pk);
    if (getPackedPositionType(pk) == PACKED_POSITION_TYPE_GROUP) {
      if (mOnGroupClickListener != null && mOnGroupClickListener.onGroupClick(this, v, g, id)) return true;
      if (mExpanded.get(g)) collapseGroup(g); else expandGroup(g);
      playSoundEffect(0);
      return true;
    }
    if (mOnChildClickListener != null) { playSoundEffect(0); return mOnChildClickListener.onChildClick(this, v, g, getPackedPositionChild(pk), id); }
    return false;
  }
  public void setOnGroupCollapseListener(OnGroupCollapseListener l) { mOnGroupCollapseListener = l; }
  public void setOnGroupExpandListener(OnGroupExpandListener l) { mOnGroupExpandListener = l; }
  public void setOnGroupClickListener(OnGroupClickListener l) { mOnGroupClickListener = l; }
  public void setOnChildClickListener(OnChildClickListener l) { mOnChildClickListener = l; }
  public long getExpandableListPosition(int flatListPosition) { return flatListPosition < 0 || flatListPosition >= mFlat.size() ? PACKED_POSITION_VALUE_NULL : mFlat.get(flatListPosition); }
  public int getFlatListPosition(long packedPosition) { return mFlat.indexOf(packedPosition); }
  public long getSelectedPosition() { return getExpandableListPosition(getSelectedItemPosition()); }
  public long getSelectedId() { return getSelectedItemId(); }
  public void setSelectedGroup(int g) { int p = mFlat.indexOf(getPackedPositionForGroup(g)); if (p >= 0) setSelection(p); }
  public boolean setSelectedChild(int g, int c, boolean expand) {
    if (expand) expandGroup(g);
    int p = mFlat.indexOf(getPackedPositionForChild(g, c));
    if (p < 0) return false;
    setSelection(p);
    return true;
  }
  public void setGroupIndicator(Drawable d) { mGroupIndicator = d; mDefaultIndicator = false; invalidate(); }
  public void setChildIndicator(Drawable d) {}
  public void setChildDivider(Drawable d) {}
  public void setIndicatorBounds(int left, int right) { mIndicatorLeft = left; }
  public void setIndicatorBoundsRelative(int start, int end) { mIndicatorLeft = start; }
  public void setChildIndicatorBounds(int left, int right) {}

  @Override protected void dispatchDraw(Canvas canvas) {
    super.dispatchDraw(canvas);
    if (mExpAdapter == null || !mDefaultIndicator && mGroupIndicator == null) return;
    float d = getResources().getDisplayMetrics().density;
    int first = getFirstVisiblePosition();
    for (int i = 0; i < getChildCount(); i++) {
      int p = first + i;
      if (p >= mFlat.size()) break;
      long pk = mFlat.get(p);
      if (getPackedPositionType(pk) != PACKED_POSITION_TYPE_GROUP) continue;
      int g = getPackedPositionGroup(pk);
      if (mExpAdapter.getChildrenCount(g) == 0) continue;
      View v = getChildAt(i);
      boolean open = mExpanded.get(g);
      if (mGroupIndicator != null) {
        mGroupIndicator.setState(open ? new int[] {android.R.attr.state_expanded} : new int[0]);
        int w = mGroupIndicator.getIntrinsicWidth(), h = mGroupIndicator.getIntrinsicHeight();
        if (w <= 0) w = (int) (24 * d); if (h <= 0) h = (int) (24 * d);
        int top = v.getTop() + (v.getHeight() - h) / 2, left = mIndicatorLeft + (int) (8 * d);
        mGroupIndicator.setBounds(left, top, left + w, top + h);
        mGroupIndicator.draw(canvas);
        continue;
      }
      // default indicator: a small chevron at the start of the row
      float cx = mIndicatorLeft + 18 * d, cy = v.getTop() + v.getHeight() / 2f, s = 5 * d;
      mPaint.setColor(0xff888888);
      mPaint.setStrokeWidth(2 * d);
      if (open) { canvas.drawLine(cx - s, cy - s / 2, cx, cy + s / 2, mPaint); canvas.drawLine(cx, cy + s / 2, cx + s, cy - s / 2, mPaint); }
      else { canvas.drawLine(cx - s / 2, cy - s, cx + s / 2, cy, mPaint); canvas.drawLine(cx + s / 2, cy, cx - s / 2, cy + s, mPaint); }
    }
  }

  public static int getPackedPositionType(long p) { return p == PACKED_POSITION_VALUE_NULL ? PACKED_POSITION_TYPE_NULL : (p & 0x8000000000000000L) != 0 ? PACKED_POSITION_TYPE_CHILD : PACKED_POSITION_TYPE_GROUP; }
  public static int getPackedPositionGroup(long p) { return p == PACKED_POSITION_VALUE_NULL ? -1 : (int) ((p & 0x7FFFFFFF00000000L) >> 32); }
  public static int getPackedPositionChild(long p) { return p == PACKED_POSITION_VALUE_NULL || (p & 0x8000000000000000L) == 0 ? -1 : (int) (p & 0x00000000FFFFFFFFL); }
  public static long getPackedPositionForChild(int g, int c) { return 0x8000000000000000L | (((long) g & 0x7FFFFFFF) << 32) | ((long) c & 0xFFFFFFFFL); }
  public static long getPackedPositionForGroup(int g) { return ((long) g & 0x7FFFFFFF) << 32; }
}
