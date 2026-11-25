package a2w;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.Toolbar;
import java.util.ArrayList;

/**
 * An activity's action bar shown by a Toolbar (Activity.setActionBar), as the platform's ToolbarActionBar: the
 * activity's title and options menu go to the toolbar, its navigation button selects android.R.id.home, display
 * options show or hide the up button, title, logo and custom view. Tabs are not supported (as on the platform).
 */
public final class ToolbarActionBar extends ActionBar {
  private final Activity mActivity;
  private final Toolbar mToolbar;
  private int mOptions;
  private CharSequence mTitle, mSubtitle;
  private boolean mTitleSet, mShowing = true;
  private Drawable mIcon, mLogo, mNavIcon, mDefaultNavIcon;
  private View mCustom;
  private int mNavMode = NAVIGATION_MODE_STANDARD;
  private android.widget.Spinner mSpinner;
  private OnNavigationListener mListCallback;
  private final ArrayList<OnMenuVisibilityListener> mMenuListeners = new ArrayList<>();
  private boolean mMenuPosted;

  public ToolbarActionBar(Activity activity, Toolbar toolbar) {
    mActivity = activity;
    mToolbar = toolbar;
    mTitle = toolbar.getTitle();
    mSubtitle = toolbar.getSubtitle();
    mTitleSet = mTitle != null;
    mOptions = DISPLAY_SHOW_TITLE | DISPLAY_SHOW_HOME | DISPLAY_USE_LOGO;
    if (toolbar.getNavigationIcon() != null) { mOptions |= DISPLAY_HOME_AS_UP; mDefaultNavIcon = toolbar.getNavigationIcon(); }
    else mDefaultNavIcon = ActionBarImpl.glyph(false, titleColor());
    toolbar.setNavigationOnClickListener(v -> {
      a2w.MenuBuilder m = new a2w.MenuBuilder(mActivity);
      MenuItem home = m.add(0, android.R.id.home, 0, mTitle);
      mActivity.onMenuItemSelected(android.view.Window.FEATURE_OPTIONS_PANEL, home);
    });
    setWindowTitle(activity.getTitle());
    menuChanged();
  }
  private int titleColor() {
    try {
      android.content.res.TypedArray ta = mToolbar.getContext().obtainStyledAttributes(new int[] {android.R.attr.textColorPrimary});
      int v = ta.getColor(0, 0xffffffff);
      ta.recycle();
      return v;
    } catch (RuntimeException e) { return 0xffffffff; }
  }
  public Toolbar toolbar() { return mToolbar; }
  /** the activity's title, unless the toolbar was given its own */
  public void setWindowTitle(CharSequence title) { if (!mTitleSet) { mTitle = title; if ((mOptions & DISPLAY_SHOW_TITLE) != 0) mToolbar.setTitle(title); } }
  /** the options menu changed (invalidateOptionsMenu): the toolbar shows the new one */
  public void menuChanged() {
    if (mMenuPosted) return;
    mMenuPosted = true;
    new android.os.Handler(android.os.Looper.getMainLooper()).post(() -> {
      mMenuPosted = false;
      a2w.MenuBuilder m = mActivity.optionsMenuForBar();
      mToolbar.a2wSetMenu(m != null ? m : new a2w.MenuBuilder(mActivity));
    });
  }
  public void detach() { mToolbar.a2wSetMenu(new a2w.MenuBuilder(mActivity)); }

  private void apply(int changed) {
    if ((changed & (DISPLAY_HOME_AS_UP)) != 0) mToolbar.setNavigationIcon((mOptions & DISPLAY_HOME_AS_UP) != 0 ? (mNavIcon != null ? mNavIcon : mDefaultNavIcon) : null);
    if ((changed & (DISPLAY_SHOW_HOME | DISPLAY_USE_LOGO)) != 0) {
      Drawable logo = null;
      if ((mOptions & DISPLAY_SHOW_HOME) != 0) logo = (mOptions & DISPLAY_USE_LOGO) != 0 ? (mLogo != null ? mLogo : mIcon) : mIcon;
      mToolbar.setLogo(logo);
    }
    if ((changed & DISPLAY_SHOW_TITLE) != 0) {
      boolean show = (mOptions & DISPLAY_SHOW_TITLE) != 0;
      mToolbar.setTitle(show ? mTitle : null);
      mToolbar.setSubtitle(show ? mSubtitle : null);
    }
    if ((changed & DISPLAY_SHOW_CUSTOM) != 0 && mCustom != null) {
      if ((mOptions & DISPLAY_SHOW_CUSTOM) != 0) { if (mCustom.getParent() != mToolbar) { if (mCustom.getParent() instanceof ViewGroup) ((ViewGroup) mCustom.getParent()).removeView(mCustom); mToolbar.addView(mCustom); } }
      else mToolbar.removeView(mCustom);
    }
  }
  @Override public void setCustomView(View view) { setCustomView(view, new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)); }
  @Override public void setCustomView(View view, LayoutParams layoutParams) {
    if (mCustom != null && (mOptions & DISPLAY_SHOW_CUSTOM) != 0) mToolbar.removeView(mCustom);
    mCustom = view;
    if (view != null) { view.setLayoutParams(layoutParams); apply(DISPLAY_SHOW_CUSTOM); }
  }
  @Override public void setCustomView(int resId) { setCustomView(android.view.LayoutInflater.from(mToolbar.getContext()).inflate(resId, mToolbar, false)); }
  @Override public View getCustomView() { return mCustom; }
  @Override public void setIcon(int resId) { setIcon(resId == 0 ? null : mActivity.getDrawable(resId)); }
  @Override public void setIcon(Drawable icon) { mIcon = icon; apply(DISPLAY_SHOW_HOME); }
  @Override public void setLogo(int resId) { setLogo(resId == 0 ? null : mActivity.getDrawable(resId)); }
  @Override public void setLogo(Drawable logo) { mLogo = logo; apply(DISPLAY_SHOW_HOME); }
  @Override public void setListNavigationCallbacks(SpinnerAdapter adapter, OnNavigationListener callback) {
    if (mSpinner == null) {
      mSpinner = new android.widget.Spinner(mToolbar.getContext());
      mSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
        public void onItemSelected(android.widget.AdapterView<?> p, View v, int pos, long id) { if (mListCallback != null) mListCallback.onNavigationItemSelected(pos, id); }
        public void onNothingSelected(android.widget.AdapterView<?> p) {}
      });
    }
    mSpinner.setAdapter(adapter);
    mListCallback = callback;
  }
  @Override public void setSelectedNavigationItem(int position) {
    if (mNavMode != NAVIGATION_MODE_LIST || mSpinner == null) throw new IllegalStateException("setSelectedNavigationIndex not valid for current navigation mode");
    mSpinner.setSelection(position);
  }
  @Override public int getSelectedNavigationIndex() { return mNavMode == NAVIGATION_MODE_LIST && mSpinner != null ? mSpinner.getSelectedItemPosition() : -1; }
  @Override public int getNavigationItemCount() { return mNavMode == NAVIGATION_MODE_LIST && mSpinner != null && mSpinner.getAdapter() != null ? mSpinner.getAdapter().getCount() : 0; }
  @Override public void setTitle(CharSequence title) { mTitleSet = true; mTitle = title; if ((mOptions & DISPLAY_SHOW_TITLE) != 0) mToolbar.setTitle(title); }
  @Override public void setTitle(int resId) { setTitle(resId != 0 ? mActivity.getText(resId) : null); }
  @Override public void setSubtitle(CharSequence subtitle) { mSubtitle = subtitle; if ((mOptions & DISPLAY_SHOW_TITLE) != 0) mToolbar.setSubtitle(subtitle); }
  @Override public void setSubtitle(int resId) { setSubtitle(resId != 0 ? mActivity.getText(resId) : null); }
  @Override public void setDisplayOptions(int options) { setDisplayOptions(options, 0xffffffff); }
  @Override public void setDisplayOptions(int options, int mask) {
    int old = mOptions;
    mOptions = (options & mask) | (old & ~mask);
    apply(old ^ mOptions);
  }
  @Override public void setDisplayUseLogoEnabled(boolean useLogo) { setDisplayOptions(useLogo ? DISPLAY_USE_LOGO : 0, DISPLAY_USE_LOGO); }
  @Override public void setDisplayShowHomeEnabled(boolean showHome) { setDisplayOptions(showHome ? DISPLAY_SHOW_HOME : 0, DISPLAY_SHOW_HOME); }
  @Override public void setDisplayHomeAsUpEnabled(boolean showHomeAsUp) { setDisplayOptions(showHomeAsUp ? DISPLAY_HOME_AS_UP : 0, DISPLAY_HOME_AS_UP); }
  @Override public void setDisplayShowTitleEnabled(boolean showTitle) { setDisplayOptions(showTitle ? DISPLAY_SHOW_TITLE : 0, DISPLAY_SHOW_TITLE); }
  @Override public void setDisplayShowCustomEnabled(boolean showCustom) { setDisplayOptions(showCustom ? DISPLAY_SHOW_CUSTOM : 0, DISPLAY_SHOW_CUSTOM); }
  @Override public void setBackgroundDrawable(Drawable d) { mToolbar.setBackground(d); }
  @Override public CharSequence getTitle() { return mToolbar.getTitle(); }
  @Override public CharSequence getSubtitle() { return mToolbar.getSubtitle(); }
  @Override public int getNavigationMode() { return mNavMode; }
  @Override public void setNavigationMode(int mode) {
    if (mode == NAVIGATION_MODE_TABS) throw new IllegalArgumentException("Tabs not supported in this configuration");
    int old = mNavMode;
    if (old == mode) return;
    if (old == NAVIGATION_MODE_LIST && mSpinner != null && mSpinner.getParent() == mToolbar) mToolbar.removeView(mSpinner);
    mNavMode = mode;
    if (mode == NAVIGATION_MODE_LIST && mSpinner != null) {
      if (mSpinner.getParent() instanceof ViewGroup) ((ViewGroup) mSpinner.getParent()).removeView(mSpinner);
      mToolbar.addView(mSpinner, 0, new Toolbar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, android.view.Gravity.START | android.view.Gravity.CENTER_VERTICAL));
    }
  }
  @Override public int getDisplayOptions() { return mOptions; }
  @Override public Tab newTab() { throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars"); }
  @Override public void addTab(Tab tab) { throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars"); }
  @Override public void addTab(Tab tab, boolean setSelected) { throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars"); }
  @Override public void addTab(Tab tab, int position) { throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars"); }
  @Override public void addTab(Tab tab, int position, boolean setSelected) { throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars"); }
  @Override public void removeTab(Tab tab) { throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars"); }
  @Override public void removeTabAt(int position) { throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars"); }
  @Override public void removeAllTabs() { throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars"); }
  @Override public void selectTab(Tab tab) { throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars"); }
  @Override public Tab getSelectedTab() { throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars"); }
  @Override public Tab getTabAt(int index) { throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars"); }
  @Override public int getTabCount() { return 0; }
  @Override public int getHeight() { return mToolbar.getHeight(); }
  @Override public void show() { mShowing = true; mToolbar.setVisibility(View.VISIBLE); }
  @Override public void hide() { mShowing = false; mToolbar.setVisibility(View.GONE); }
  @Override public boolean isShowing() { return mToolbar.getVisibility() == View.VISIBLE; }
  @Override public void addOnMenuVisibilityListener(OnMenuVisibilityListener listener) { mMenuListeners.add(listener); }
  @Override public void removeOnMenuVisibilityListener(OnMenuVisibilityListener listener) { mMenuListeners.remove(listener); }
  @Override public void setHomeButtonEnabled(boolean enabled) {}
  @Override public Context getThemedContext() { return mToolbar.getContext(); }
  @Override public void setHomeAsUpIndicator(Drawable indicator) { mNavIcon = indicator; if ((mOptions & DISPLAY_HOME_AS_UP) != 0) mToolbar.setNavigationIcon(indicator != null ? indicator : mDefaultNavIcon); }
  @Override public void setHomeAsUpIndicator(int resId) { setHomeAsUpIndicator(resId != 0 ? mActivity.getDrawable(resId) : null); }
  @Override public void setHomeActionContentDescription(CharSequence description) { mToolbar.setNavigationContentDescription(description); }
  @Override public void setHomeActionContentDescription(int resId) { mToolbar.setNavigationContentDescription(resId); }
  @Override public void setElevation(float elevation) { mToolbar.setElevation(elevation); }
  @Override public float getElevation() { return mToolbar.getElevation(); }
  /** the overflow of the toolbar's menu (Activity.openOptionsMenu) */
  public boolean openOptionsMenu() { return mToolbar.showOverflowMenu(); }
}
