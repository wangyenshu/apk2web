package android.app;

import android.view.ContextMenu;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

public class ExpandableListActivity extends Activity implements View.OnCreateContextMenuListener, ExpandableListView.OnChildClickListener,
    ExpandableListView.OnGroupCollapseListener, ExpandableListView.OnGroupExpandListener {
  ExpandableListAdapter mAdapter;
  ExpandableListView mList;
  boolean mFinishedStart;
  @Override public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {}
  public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) { return false; }
  public void onGroupCollapse(int groupPosition) {}
  public void onGroupExpand(int groupPosition) {}
  @Override public void onContentChanged() {
    super.onContentChanged();
    View empty = findViewById(android.R.id.empty);
    mList = (ExpandableListView) findViewById(android.R.id.list);
    if (mList == null) throw new RuntimeException("Your content must have a ExpandableListView whose id attribute is 'android.R.id.list'");
    if (empty != null) mList.setEmptyView(empty);
    mList.setOnChildClickListener(this);
    mList.setOnGroupExpandListener(this);
    mList.setOnGroupCollapseListener(this);
    if (mFinishedStart) setListAdapter(mAdapter);
    mFinishedStart = true;
  }
  public void setListAdapter(ExpandableListAdapter adapter) { ensureList(); mAdapter = adapter; mList.setAdapter(adapter); }
  public ExpandableListView getExpandableListView() { ensureList(); return mList; }
  public ExpandableListAdapter getExpandableListAdapter() { return mAdapter; }
  private void ensureList() { if (mList == null) setContentView(android.R.layout.expandable_list_content); }
  public long getSelectedId() { return mList.getSelectedId(); }
  public long getSelectedPosition() { return mList.getSelectedPosition(); }
  public boolean setSelectedChild(int g, int c, boolean expand) { return mList.setSelectedChild(g, c, expand); }
  public void setSelectedGroup(int g) { mList.setSelectedGroup(g); }
}
