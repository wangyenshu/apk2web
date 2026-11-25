package android.preference;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

public final class PreferenceScreen extends PreferenceGroup implements AdapterView.OnItemClickListener, DialogInterface.OnDismissListener {
  private ListAdapter mRootAdapter;
  private Dialog mDialog;
  private ListView mListView;
  public PreferenceScreen(Context context, AttributeSet attrs) { super(context, attrs, 0); }
  public ListAdapter getRootAdapter() { if (mRootAdapter == null) mRootAdapter = onCreateRootAdapter(); return mRootAdapter; }
  protected ListAdapter onCreateRootAdapter() { return new PreferenceGroupAdapter(this); }
  public void bind(ListView listView) {
    listView.setOnItemClickListener(this);
    listView.setAdapter(getRootAdapter());
    onAttachedToActivity();
  }
  @Override protected void onClick() {
    if (getIntent() != null || getFragment() != null || getPreferenceCount() == 0) return;
    showDialog();
  }
  private void showDialog() {
    Context context = getContext();
    if (mListView != null) mListView.setAdapter(null);
    mListView = new ListView(context);
    bind(mListView);
    final CharSequence title = getTitle();
    Dialog dialog = mDialog = new Dialog(context);
    if (title == null || title.length() == 0) dialog.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
    else dialog.setTitle(title);
    dialog.setContentView(mListView);
    dialog.setOnDismissListener(this);
    getPreferenceManager().addPreferencesScreen(dialog);
    dialog.show();
  }
  public void onDismiss(DialogInterface dialog) { mDialog = null; getPreferenceManager().removePreferencesScreen(dialog); }
  public Dialog getDialog() { return mDialog; }
  public void onItemClick(AdapterView parent, View view, int position, long id) {
    if (parent instanceof ListView) position -= ((ListView) parent).getHeaderViewsCount();
    Object item = getRootAdapter().getItem(position);
    if (!(item instanceof Preference)) return;
    ((Preference) item).performClick(this);
  }
  @Override protected boolean isOnSameScreenAsChildren() { return false; }
}
