package android.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import java.util.Map;

public class SimpleExpandableListAdapter extends BaseExpandableListAdapter {
  private final List<? extends Map<String, ?>> mGroupData;
  private final int mExpandedGroupLayout, mCollapsedGroupLayout, mChildLayout, mLastChildLayout;
  private final String[] mGroupFrom, mChildFrom;
  private final int[] mGroupTo, mChildTo;
  private final List<? extends List<? extends Map<String, ?>>> mChildData;
  private final LayoutInflater mInflater;
  public SimpleExpandableListAdapter(Context c, List<? extends Map<String, ?>> groupData, int groupLayout, String[] groupFrom, int[] groupTo,
      List<? extends List<? extends Map<String, ?>>> childData, int childLayout, String[] childFrom, int[] childTo) {
    this(c, groupData, groupLayout, groupLayout, groupFrom, groupTo, childData, childLayout, childLayout, childFrom, childTo);
  }
  public SimpleExpandableListAdapter(Context c, List<? extends Map<String, ?>> groupData, int expandedGroupLayout, int collapsedGroupLayout, String[] groupFrom, int[] groupTo,
      List<? extends List<? extends Map<String, ?>>> childData, int childLayout, String[] childFrom, int[] childTo) {
    this(c, groupData, expandedGroupLayout, collapsedGroupLayout, groupFrom, groupTo, childData, childLayout, childLayout, childFrom, childTo);
  }
  public SimpleExpandableListAdapter(Context c, List<? extends Map<String, ?>> groupData, int expandedGroupLayout, int collapsedGroupLayout, String[] groupFrom, int[] groupTo,
      List<? extends List<? extends Map<String, ?>>> childData, int childLayout, int lastChildLayout, String[] childFrom, int[] childTo) {
    mGroupData = groupData; mExpandedGroupLayout = expandedGroupLayout; mCollapsedGroupLayout = collapsedGroupLayout; mGroupFrom = groupFrom; mGroupTo = groupTo;
    mChildData = childData; mChildLayout = childLayout; mLastChildLayout = lastChildLayout; mChildFrom = childFrom; mChildTo = childTo;
    mInflater = LayoutInflater.from(c);
  }
  public Object getChild(int g, int c) { return mChildData.get(g).get(c); }
  public long getChildId(int g, int c) { return c; }
  public View getChildView(int g, int c, boolean isLast, View convert, ViewGroup parent) {
    View v = convert != null ? convert : mInflater.inflate(isLast ? mLastChildLayout : mChildLayout, parent, false);
    bind(v, mChildData.get(g).get(c), mChildFrom, mChildTo);
    return v;
  }
  public int getChildrenCount(int g) { return mChildData.get(g).size(); }
  public Object getGroup(int g) { return mGroupData.get(g); }
  public int getGroupCount() { return mGroupData.size(); }
  public long getGroupId(int g) { return g; }
  public View getGroupView(int g, boolean expanded, View convert, ViewGroup parent) {
    View v = convert != null ? convert : mInflater.inflate(expanded ? mExpandedGroupLayout : mCollapsedGroupLayout, parent, false);
    bind(v, mGroupData.get(g), mGroupFrom, mGroupTo);
    return v;
  }
  private static void bind(View v, Map<String, ?> data, String[] from, int[] to) {
    for (int i = 0; i < to.length && i < from.length; i++) {
      View t = v.findViewById(to[i]);
      if (t instanceof TextView) { Object o = data.get(from[i]); ((TextView) t).setText(o == null ? "" : o.toString()); }
    }
  }
  public boolean isChildSelectable(int g, int c) { return true; }
  public boolean hasStableIds() { return true; }
}
