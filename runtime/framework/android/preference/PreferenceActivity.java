package android.preference;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import java.util.List;

/** Shows a PreferenceScreen in a list (the single-pane form; headers are listed and open their intents). */
public abstract class PreferenceActivity extends ListActivity implements PreferenceManager.OnPreferenceTreeClickListener {
  public static final String EXTRA_SHOW_FRAGMENT = ":android:show_fragment", EXTRA_SHOW_FRAGMENT_ARGUMENTS = ":android:show_fragment_args",
      EXTRA_SHOW_FRAGMENT_TITLE = ":android:show_fragment_title", EXTRA_SHOW_FRAGMENT_SHORT_TITLE = ":android:show_fragment_short_title", EXTRA_NO_HEADERS = ":android:no_headers";
  public static final long HEADER_ID_UNDEFINED = -1;
  private PreferenceManager mPreferenceManager;
  private boolean mBindPosted;
  private final java.util.ArrayList<Header> mHeaders = new java.util.ArrayList<>();

  public static final class Header {
    public long id = HEADER_ID_UNDEFINED;
    public int titleRes, summaryRes, breadCrumbTitleRes, breadCrumbShortTitleRes, iconRes;
    public CharSequence title, summary, breadCrumbTitle, breadCrumbShortTitle;
    public String fragment;
    public Bundle fragmentArguments, extras;
    public Intent intent;
    public Header() {}
    public CharSequence getTitle(android.content.res.Resources res) { return titleRes != 0 ? res.getText(titleRes) : title; }
    public CharSequence getSummary(android.content.res.Resources res) { return summaryRes != 0 ? res.getText(summaryRes) : summary; }
    public CharSequence getBreadCrumbTitle(android.content.res.Resources res) { return breadCrumbTitleRes != 0 ? res.getText(breadCrumbTitleRes) : breadCrumbTitle; }
    public CharSequence getBreadCrumbShortTitle(android.content.res.Resources res) { return breadCrumbShortTitleRes != 0 ? res.getText(breadCrumbShortTitleRes) : breadCrumbShortTitle; }
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(android.R.layout.list_content);
    mPreferenceManager = new PreferenceManager(this, 100);
    mPreferenceManager.setOnPreferenceTreeClickListener(this);
    onBuildHeaders(mHeaders);
    if (!mHeaders.isEmpty() && getIntent().getStringExtra(EXTRA_SHOW_FRAGMENT) == null) showHeaders();
  }
  private void showHeaders() {
    final java.util.ArrayList<Header> hs = mHeaders;
    setListAdapter(new android.widget.BaseAdapter() {
      public int getCount() { return hs.size(); }
      public Object getItem(int p) { return hs.get(p); }
      public long getItemId(int p) { return p; }
      public View getView(int p, View convert, android.view.ViewGroup parent) {
        Header h = hs.get(p);
        Preference row = new Preference(PreferenceActivity.this);
        row.setTitle(h.getTitle(getResources()));
        row.setSummary(h.getSummary(getResources()));
        if (h.iconRes != 0) row.setIcon(h.iconRes);
        return row.getView(null, parent);
      }
    });
  }
  public void onBuildHeaders(List<Header> target) {}
  public boolean hasHeaders() { return !mHeaders.isEmpty(); }
  public List<Header> getHeaders() { return mHeaders; }
  public boolean isMultiPane() { return false; }
  public boolean onIsMultiPane() { return false; }
  public boolean onIsHidingHeaders() { return getIntent().getBooleanExtra(EXTRA_NO_HEADERS, false); }
  public void invalidateHeaders() { mHeaders.clear(); onBuildHeaders(mHeaders); showHeaders(); }
  protected boolean isValidFragment(String fragmentName) { return false; }
  public void loadHeadersFromResource(int resid, List<Header> target) {
    a2w.XmlNode root = a2w.XmlNode.of(a2w.Res.xml(resid));
    if (root == null) return;
    for (a2w.XmlNode n : root.children()) {
      if (!n.tag.equals("header")) continue;
      Header h = new Header();
      String v;
      if ((v = n.a("id")) != null) h.id = a2w.Values.refId(v);
      if ((v = n.a("title")) != null) { if (a2w.Values.isRef(v)) h.titleRes = a2w.Values.refId(v); else h.title = v; }
      if ((v = n.a("summary")) != null) { if (a2w.Values.isRef(v)) h.summaryRes = a2w.Values.refId(v); else h.summary = v; }
      if ((v = n.a("icon")) != null) h.iconRes = a2w.Values.refId(v);
      h.fragment = n.a("fragment");
      for (a2w.XmlNode c : n.children()) if (c.tag.equals("intent")) h.intent = a2w.PrefInflater.intent(this, c);
      target.add(h);
    }
  }
  public void onHeaderClick(Header header, int position) {
    if (header.fragment != null) startWithFragment(header.fragment, header.fragmentArguments, null, 0, header.titleRes, header.breadCrumbShortTitleRes);
    else if (header.intent != null) startActivity(header.intent);
  }
  public Intent onBuildStartFragmentIntent(String fragmentName, Bundle args, int titleRes, int shortTitleRes) {
    Intent intent = new Intent(Intent.ACTION_MAIN);
    intent.setClass(this, getClass());
    intent.putExtra(EXTRA_SHOW_FRAGMENT, fragmentName);
    intent.putExtra(EXTRA_SHOW_FRAGMENT_ARGUMENTS, args);
    intent.putExtra(EXTRA_NO_HEADERS, true);
    return intent;
  }
  public void startWithFragment(String fragmentName, Bundle args, android.app.Fragment resultTo, int resultRequestCode) { startWithFragment(fragmentName, args, resultTo, resultRequestCode, 0, 0); }
  public void startWithFragment(String fragmentName, Bundle args, android.app.Fragment resultTo, int resultRequestCode, int titleRes, int shortTitleRes) {
    startActivity(onBuildStartFragmentIntent(fragmentName, args, titleRes, shortTitleRes));
  }
  public void switchToHeader(String fragmentName, Bundle args) {}
  public void switchToHeader(Header header) { onHeaderClick(header, 0); }
  public void showBreadCrumbs(CharSequence title, CharSequence shortTitle) {}
  public void setParentTitle(CharSequence title, CharSequence shortTitle, View.OnClickListener listener) {}
  public void finishPreferencePanel(android.app.Fragment caller, int resultCode, Intent resultData) { finish(); }
  public void startPreferencePanel(String fragmentClass, Bundle args, int titleRes, CharSequence titleText, android.app.Fragment resultTo, int resultRequestCode) { startWithFragment(fragmentClass, args, resultTo, resultRequestCode); }
  public void startPreferenceFragment(android.app.Fragment fragment, boolean push) {}
  public void setListFooter(View view) {}

  @Override protected void onListItemClick(ListView l, View v, int position, long id) {
    if (!mHeaders.isEmpty() && getPreferenceScreen() == null) { onHeaderClick(mHeaders.get(position), position); return; }
    PreferenceScreen screen = getPreferenceScreen();
    if (screen != null) screen.onItemClick(l, v, position, id);
  }
  @Override protected void onStop() { super.onStop(); }
  @Override protected void onDestroy() { super.onDestroy(); if (mPreferenceManager != null) mPreferenceManager.dispatchActivityDestroy(); }
  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) { super.onActivityResult(requestCode, resultCode, data); mPreferenceManager.dispatchActivityResult(requestCode, resultCode, data); }
  @Override public void onContentChanged() { super.onContentChanged(); if (mPreferenceManager != null && getPreferenceScreen() != null) postBindPreferences(); }
  private void postBindPreferences() {
    if (mBindPosted) return;
    mBindPosted = true;
    getListView().post(() -> { mBindPosted = false; bindPreferences(); });
  }
  private void bindPreferences() {
    final PreferenceScreen preferenceScreen = getPreferenceScreen();
    if (preferenceScreen != null) {
      preferenceScreen.bind(getListView());
      mAdapter = preferenceScreen.getRootAdapter();
    }
  }
  @Deprecated public PreferenceManager getPreferenceManager() { return mPreferenceManager; }
  @Deprecated public void setPreferenceScreen(PreferenceScreen preferenceScreen) {
    if (mPreferenceManager.setPreferences(preferenceScreen) && preferenceScreen != null) {
      postBindPreferences();
      CharSequence title = getPreferenceScreen().getTitle();
      if (title != null) setTitle(title);
    }
  }
  @Deprecated public PreferenceScreen getPreferenceScreen() { return mPreferenceManager == null ? null : mPreferenceManager.getPreferenceScreen(); }
  @Deprecated public void addPreferencesFromIntent(Intent intent) { setPreferenceScreen(mPreferenceManager.inflateFromIntent(intent, getPreferenceScreen())); }
  @Deprecated public void addPreferencesFromResource(int preferencesResId) { setPreferenceScreen(mPreferenceManager.inflateFromResource(this, preferencesResId, getPreferenceScreen())); }
  @Deprecated public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) { return false; }
  @Deprecated public Preference findPreference(CharSequence key) { return mPreferenceManager == null ? null : mPreferenceManager.findPreference(key); }
  @Override protected void onNewIntent(Intent intent) { super.onNewIntent(intent); }
}
