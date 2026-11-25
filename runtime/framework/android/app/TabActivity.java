package android.app;

import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabWidget;

public class TabActivity extends ActivityGroup {
  private TabHost mTabHost;
  private String mDefaultTab;
  private int mDefaultTabIndex = -1;
  public TabActivity() {}
  public void setDefaultTab(String tag) { mDefaultTab = tag; mDefaultTabIndex = -1; }
  public void setDefaultTab(int index) { mDefaultTab = null; mDefaultTabIndex = index; }
  @Override protected void onPostCreate(Bundle b) {
    super.onPostCreate(b);
    ensureTabHost();
    if (mTabHost.getCurrentTab() == -1) mTabHost.setCurrentTab(0);
  }
  @Override protected void onStart() {
    super.onStart();
    ensureTabHost();
    if (mDefaultTab != null) mTabHost.setCurrentTabByTag(mDefaultTab);
    else if (mDefaultTabIndex >= 0) mTabHost.setCurrentTab(mDefaultTabIndex);
  }
  @Override public void onContentChanged() {
    super.onContentChanged();
    mTabHost = (TabHost) findViewById(android.R.id.tabhost);
    if (mTabHost == null) throw new RuntimeException("Your content must have a TabHost whose id attribute is 'android.R.id.tabhost'");
    mTabHost.setup(getLocalActivityManager());
  }
  private void ensureTabHost() { if (mTabHost == null) setContentView(a2w.Tabs.defaultLayout(this)); }
  public TabHost getTabHost() { ensureTabHost(); return mTabHost; }
  public TabWidget getTabWidget() { return getTabHost().getTabWidget(); }
  @Override protected void onChildTitleChanged(Activity child, CharSequence title) {}
}
