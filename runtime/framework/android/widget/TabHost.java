package android.widget;

import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import java.util.ArrayList;

/** Tabbed container: a TabWidget of indicators and a FrameLayout (android:id/tabcontent) showing the current tab. */
public class TabHost extends FrameLayout {
  public interface OnTabChangeListener { void onTabChanged(String tabId); }
  public interface TabContentFactory { View createTabContent(String tag); }
  private TabWidget mTabWidget;
  private FrameLayout mTabContent;
  private final ArrayList<TabSpec> mTabSpecs = new ArrayList<>();
  protected int mCurrentTab = -1;
  private View mCurrentView;
  protected LocalActivityManager mLocalActivityManager;
  private OnTabChangeListener mOnTabChangeListener;

  public TabHost(Context c) { super(c); }
  public TabHost(Context c, AttributeSet a) { super(c, a); }
  public TabHost(Context c, AttributeSet a, int d) { super(c, a); }

  public TabSpec newTabSpec(String tag) { return new TabSpec(tag); }
  public void setup() {
    mTabWidget = (TabWidget) findViewById(android.R.id.tabs);
    if (mTabWidget == null) throw new RuntimeException("Your TabHost must have a TabWidget whose id attribute is 'android.R.id.tabs'");
    mTabWidget.mSelectionChangedListener = (index, clicked) -> { setCurrentTab(index); if (clicked) mTabContent.requestFocus(View.FOCUS_FORWARD); };
    mTabContent = (FrameLayout) findViewById(android.R.id.tabcontent);
    if (mTabContent == null) throw new RuntimeException("Your TabHost must have a FrameLayout whose id attribute is 'android.R.id.tabcontent'");
  }
  public void setup(LocalActivityManager m) { setup(); mLocalActivityManager = m; }
  public void addTab(TabSpec spec) {
    if (spec.mIndicatorStrategy == null) throw new IllegalArgumentException("you must specify a way to create the tab indicator.");
    if (spec.mContentStrategy == null) throw new IllegalArgumentException("you must specify a way to create the tab content");
    View indicator = spec.mIndicatorStrategy.createIndicatorView();
    mTabWidget.addView(indicator);
    mTabSpecs.add(spec);
    if (mCurrentTab == -1) setCurrentTab(0);
  }
  public void clearAllTabs() {
    mTabWidget.removeAllViews();
    mTabContent.removeAllViews();
    mTabSpecs.clear();
    mCurrentTab = -1;
    mCurrentView = null;
    requestLayout();
    invalidate();
  }
  public TabWidget getTabWidget() { return mTabWidget; }
  public int getCurrentTab() { return mCurrentTab; }
  public String getCurrentTabTag() { return mCurrentTab >= 0 && mCurrentTab < mTabSpecs.size() ? mTabSpecs.get(mCurrentTab).getTag() : null; }
  public View getCurrentTabView() { return mCurrentTab >= 0 && mCurrentTab < mTabSpecs.size() ? mTabWidget.getChildTabViewAt(mCurrentTab) : null; }
  public View getCurrentView() { return mCurrentView; }
  public FrameLayout getTabContentView() { return mTabContent; }
  public void setCurrentTabByTag(String tag) {
    for (int i = 0; i < mTabSpecs.size(); i++) if (mTabSpecs.get(i).getTag().equals(tag)) { setCurrentTab(i); break; }
  }
  public void setCurrentTab(int index) {
    if (index < 0 || index >= mTabSpecs.size() || index == mCurrentTab) return;
    if (mCurrentTab != -1) mTabSpecs.get(mCurrentTab).mContentStrategy.tabClosed();
    mCurrentTab = index;
    TabSpec spec = mTabSpecs.get(index);
    mTabWidget.focusCurrentTab(mCurrentTab);
    mCurrentView = spec.mContentStrategy.getContentView();
    if (mCurrentView.getParent() == null)
      mTabContent.addView(mCurrentView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    if (!mTabWidget.hasFocus()) mCurrentView.requestFocus();
    if (mOnTabChangeListener != null) mOnTabChangeListener.onTabChanged(getCurrentTabTag());
  }
  public void setOnTabChangedListener(OnTabChangeListener l) { mOnTabChangeListener = l; }

  public class TabSpec {
    private final String mTag;
    IndicatorStrategy mIndicatorStrategy;
    ContentStrategy mContentStrategy;
    TabSpec(String tag) { mTag = tag; }
    public TabSpec setIndicator(CharSequence label) { mIndicatorStrategy = () -> a2w.Tabs.indicator(getContext(), mTabWidget, label, null); return this; }
    public TabSpec setIndicator(CharSequence label, Drawable icon) { mIndicatorStrategy = () -> a2w.Tabs.indicator(getContext(), mTabWidget, label, icon); return this; }
    public TabSpec setIndicator(View view) { mIndicatorStrategy = () -> view; return this; }
    public TabSpec setContent(int viewId) {
      View v = mTabContent.findViewById(viewId);
      if (v == null) throw new RuntimeException("Could not create tab content because could not find view with id " + viewId);
      v.setVisibility(View.GONE);
      mContentStrategy = new ContentStrategy() {
        public View getContentView() { v.setVisibility(View.VISIBLE); return v; }
        public void tabClosed() { v.setVisibility(View.GONE); }
      };
      return this;
    }
    public TabSpec setContent(TabContentFactory f) {
      mContentStrategy = new ContentStrategy() {
        View v;
        public View getContentView() { if (v == null) v = f.createTabContent(mTag); v.setVisibility(View.VISIBLE); return v; }
        public void tabClosed() { if (v != null) v.setVisibility(View.GONE); }
      };
      return this;
    }
    public TabSpec setContent(Intent intent) {
      mContentStrategy = new ContentStrategy() {
        View launched;
        public View getContentView() {
          if (mLocalActivityManager == null) throw new IllegalStateException("Did you forget to call 'public void setup(LocalActivityManager activityGroup)'?");
          Window w = mLocalActivityManager.startActivity(mTag, intent);
          View wd = w != null ? w.getDecorView() : null;
          if (launched != wd && launched != null && launched.getParent() != null) mTabContent.removeView(launched);
          launched = wd != null ? wd : new View(getContext());
          if (launched.getParent() != null && launched.getParent() != mTabContent) ((ViewGroup) launched.getParent()).removeView(launched);
          launched.setVisibility(View.VISIBLE);
          launched.setFocusableInTouchMode(true);
          return launched;
        }
        public void tabClosed() { if (launched != null) launched.setVisibility(View.GONE); }
      };
      return this;
    }
    public String getTag() { return mTag; }
  }
  interface IndicatorStrategy { View createIndicatorView(); }
  interface ContentStrategy { View getContentView(); void tabClosed(); }
}
