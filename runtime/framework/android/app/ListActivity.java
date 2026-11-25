package android.app;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListActivity extends Activity {
  protected ListAdapter mAdapter;
  protected ListView mList;
  private boolean mFinishedStart;
  private final AdapterView.OnItemClickListener mOnClickListener = (parent, v, position, id) -> onListItemClick((ListView) parent, v, position, id);
  protected void onListItemClick(ListView l, View v, int position, long id) {}
  @Override protected void onRestoreInstanceState(Bundle state) { ensureList(); super.onRestoreInstanceState(state); }
  @Override protected void onDestroy() { super.onDestroy(); }
  @Override public void onContentChanged() {
    super.onContentChanged();
    View empty = findViewById(android.R.id.empty);
    mList = (ListView) findViewById(android.R.id.list);
    if (mList == null) throw new RuntimeException("Your content must have a ListView whose id attribute is 'android.R.id.list'");
    if (empty != null) mList.setEmptyView(empty);
    mList.setOnItemClickListener(mOnClickListener);
    if (mFinishedStart) setListAdapter(mAdapter);
    mFinishedStart = true;
  }
  public void setListAdapter(ListAdapter adapter) { ensureList(); mAdapter = adapter; mList.setAdapter(adapter); }
  public void setSelection(int position) { ensureList(); mList.setSelection(position); }
  public int getSelectedItemPosition() { ensureList(); return mList.getSelectedItemPosition(); }
  public long getSelectedItemId() { ensureList(); return mList.getSelectedItemId(); }
  public ListView getListView() { ensureList(); return mList; }
  public ListAdapter getListAdapter() { return mAdapter; }
  private void ensureList() {
    if (mList != null) return;
    ListView lv = new ListView(this);
    lv.setId(android.R.id.list);
    setContentView(lv);
  }
}
