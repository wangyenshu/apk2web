package android.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListFragment extends Fragment {
  ListAdapter mAdapter;
  ListView mList;
  View mEmptyView;
  TextView mStandardEmptyView;
  CharSequence mEmptyText;
  boolean mListShown = true;
  private final AdapterView.OnItemClickListener mOnClickListener = (parent, v, position, id) -> onListItemClick((ListView) parent, v, position, id);
  public ListFragment() {}
  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(android.R.layout.list_content, container, false);
  }
  @Override public void onViewCreated(View view, Bundle savedInstanceState) { super.onViewCreated(view, savedInstanceState); ensureList(); }
  @Override public void onDestroyView() { mList = null; mListShown = false; mEmptyView = mStandardEmptyView = null; super.onDestroyView(); }
  public void onListItemClick(ListView l, View v, int position, long id) {}
  public void setListAdapter(ListAdapter adapter) {
    mAdapter = adapter;
    if (mList != null) mList.setAdapter(adapter);
  }
  public void setSelection(int position) { ensureList(); mList.setSelection(position); }
  public int getSelectedItemPosition() { ensureList(); return mList.getSelectedItemPosition(); }
  public long getSelectedItemId() { ensureList(); return mList.getSelectedItemId(); }
  public ListView getListView() { ensureList(); return mList; }
  public void setEmptyText(CharSequence text) {
    ensureList();
    mEmptyText = text;
    if (mStandardEmptyView != null) mStandardEmptyView.setText(text);
  }
  public void setListShown(boolean shown) { mListShown = shown; if (mList != null) mList.setVisibility(shown ? View.VISIBLE : View.INVISIBLE); }
  public void setListShownNoAnimation(boolean shown) { setListShown(shown); }
  public ListAdapter getListAdapter() { return mAdapter; }
  private void ensureList() {
    if (mList != null) return;
    View root = getView();
    if (root == null) throw new IllegalStateException("Content view not yet created");
    if (root instanceof ListView) mList = (ListView) root;
    else {
      View e = root.findViewById(android.R.id.empty);
      if (e instanceof TextView) mStandardEmptyView = (TextView) e; else mEmptyView = e;
      View rawListView = root.findViewById(android.R.id.list);
      if (!(rawListView instanceof ListView)) throw new RuntimeException("Your content must have a ListView whose id attribute is 'android.R.id.list'");
      mList = (ListView) rawListView;
      if (mEmptyView != null) mList.setEmptyView(mEmptyView);
      else if (mStandardEmptyView != null) { if (mEmptyText != null) mStandardEmptyView.setText(mEmptyText); mList.setEmptyView(mStandardEmptyView); }
    }
    mList.setOnItemClickListener(mOnClickListener);
    if (mAdapter != null) { ListAdapter adapter = mAdapter; mAdapter = null; setListAdapter(adapter); }
  }
}
