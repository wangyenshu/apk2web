package a2w;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * The platform action bar of an activity whose theme has one (windowActionBar, no windowNoTitle): a bar at the top of
 * the decor with the home/up button, the title, a custom view, the action items of the options menu and the overflow.
 */
public final class ActionBarImpl extends ActionBar {
  final Activity mActivity;
  final Context mContext;
  final BarView mBar;
  final LinearLayout mContainer;   // the bar and (in tab mode) the tab row
  final LinearLayout mTabRow;
  private CharSequence mTitle, mSubtitle;
  private int mOptions = DISPLAY_SHOW_HOME | DISPLAY_SHOW_TITLE;
  private Drawable mIcon, mLogo, mUpIndicator;
  private View mCustom;
  private int mNavMode = NAVIGATION_MODE_STANDARD;
  private boolean mShowing = true, mHomeEnabled;
  private final ArrayList<TabImpl> mTabs = new ArrayList<>();
  private TabImpl mSelected;
  private SpinnerAdapter mListAdapter;
  private OnNavigationListener mListCallback;
  private int mListSelected;
  private final ArrayList<OnMenuVisibilityListener> mMenuListeners = new ArrayList<>();
  final int mHeight, mTextColor;

  public ActionBarImpl(Activity a) {
    mActivity = a;
    mContext = a;
    Resources.Theme t = a.getTheme();
    TypedValue tv = new TypedValue();
    mHeight = t.resolveAttribute(android.R.attr.actionBarSize, tv, true)
        ? TypedValue.complexToDimensionPixelSize(tv.data, a.getResources().getDisplayMetrics()) : Math.round(48 * Screen.DENSITY);
    // the bar's style: background, title appearance (the platform themes name one with actionBarStyle)
    Drawable bg = null;
    int textColor = 0xffffffff;
    String style = t.get("a", "actionBarStyle");
    if (style != null && Values.isRef(style)) {
      String[] items = Styles.items(Values.refId(style));
      Attrs at = new Attrs(new String[0]).withDefaults(items);
      at.theme = t;
      String b = at.a("background");
      if (b != null && b.startsWith("#")) bg = new ColorDrawable(Values.parseColor(b));
      else if (b != null && Values.isRef(b)) { try { bg = a.getResources().getDrawable(Values.refId(b), t); } catch (RuntimeException e) { bg = null; } }
      String ts = at.a("titleTextStyle");
      if (ts != null && Values.isRef(ts)) {
        Attrs tsa = new Attrs(new String[0]).withDefaults(Styles.items(Values.refId(ts)));
        tsa.theme = t;
        String c = tsa.a("textColor");
        if (c != null) {
          try { textColor = Values.isRef(c) ? a.getResources().getColorStateList(Values.refId(c), t).getDefaultColor() : Values.parseColor(c); } catch (RuntimeException e) { /* keep */ }
        }
      }
    }
    if (bg == null) bg = new ColorDrawable(0xff222222);
    mTextColor = textColor;
    mContainer = new LinearLayout(a);
    mContainer.setOrientation(LinearLayout.VERTICAL);
    mContainer.setBackgroundDrawable(bg);
    // as the platform's action_bar_container: arrows don't enter it on a touchscreen device; it is a keyboard navigation cluster (Meta+Tab)
    mContainer.setTouchscreenBlocksFocus(true);
    mContainer.setKeyboardNavigationCluster(true);
    mBar = new BarView(a);
    mContainer.addView(mBar, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeight));
    mTabRow = new LinearLayout(a);
    mTabRow.setOrientation(LinearLayout.HORIZONTAL);
    mTabRow.setVisibility(View.GONE);
    mContainer.addView(mTabRow, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeight));
    mTitle = a.getTitle();
    if (mTitle == null) mTitle = a.getApplicationInfo().loadLabel(a.getPackageManager());
    mBar.refresh();
    // the action items come from the options menu, made once the activity is created
    new android.os.Handler(android.os.Looper.getMainLooper()).post(this::menuChanged);
  }
  /** the view the window puts above its content */
  public View view() { return mContainer; }
  /** the options menu changed */
  public void menuChanged() { mBar.refreshActions(); }
  public void titleChanged(CharSequence t) { mTitle = t; mBar.refresh(); }

  // ---------------------------------------------------------------- ActionBar
  @Override public void setCustomView(View view) { setCustomView(view, new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT)); }
  @Override public void setCustomView(View view, LayoutParams lp) { mCustom = view; if (view != null) view.setLayoutParams(lp); mBar.refresh(); }
  @Override public void setCustomView(int resId) { setCustomView(android.view.LayoutInflater.from(getThemedContext()).inflate(resId, mBar, false)); }
  @Override public void setIcon(int resId) { setIcon(resId == 0 ? null : mContext.getResources().getDrawable(resId)); }
  @Override public void setIcon(Drawable icon) { mIcon = icon; mBar.refresh(); }
  @Override public void setLogo(int resId) { setLogo(resId == 0 ? null : mContext.getResources().getDrawable(resId)); }
  @Override public void setLogo(Drawable logo) { mLogo = logo; mBar.refresh(); }
  @Override public void setListNavigationCallbacks(SpinnerAdapter adapter, OnNavigationListener callback) { mListAdapter = adapter; mListCallback = callback; mBar.refresh(); }
  @Override public void setSelectedNavigationItem(int position) {
    if (mNavMode == NAVIGATION_MODE_TABS) { if (position >= 0 && position < mTabs.size()) selectTab(mTabs.get(position)); return; }
    mListSelected = position;
    if (mListCallback != null && mListAdapter != null && position >= 0 && position < mListAdapter.getCount())
      mListCallback.onNavigationItemSelected(position, mListAdapter.getItemId(position));
    mBar.refresh();
  }
  @Override public int getSelectedNavigationIndex() {
    if (mNavMode == NAVIGATION_MODE_TABS) return mSelected != null ? mSelected.mPosition : -1;
    return mNavMode == NAVIGATION_MODE_LIST ? mListSelected : -1;
  }
  @Override public int getNavigationItemCount() {
    if (mNavMode == NAVIGATION_MODE_TABS) return mTabs.size();
    return mNavMode == NAVIGATION_MODE_LIST && mListAdapter != null ? mListAdapter.getCount() : 0;
  }
  @Override public void setTitle(CharSequence title) { mTitle = title; mBar.refresh(); }
  @Override public void setTitle(int resId) { setTitle(mContext.getText(resId)); }
  @Override public void setSubtitle(CharSequence subtitle) { mSubtitle = subtitle; mBar.refresh(); }
  @Override public void setSubtitle(int resId) { setSubtitle(mContext.getText(resId)); }
  @Override public void setDisplayOptions(int options) { mOptions = options; mBar.refresh(); }
  @Override public void setDisplayOptions(int options, int mask) { mOptions = (mOptions & ~mask) | (options & mask); mBar.refresh(); }
  @Override public void setDisplayUseLogoEnabled(boolean b) { setDisplayOptions(b ? DISPLAY_USE_LOGO : 0, DISPLAY_USE_LOGO); }
  @Override public void setDisplayShowHomeEnabled(boolean b) { setDisplayOptions(b ? DISPLAY_SHOW_HOME : 0, DISPLAY_SHOW_HOME); }
  @Override public void setDisplayHomeAsUpEnabled(boolean b) { setDisplayOptions(b ? DISPLAY_HOME_AS_UP : 0, DISPLAY_HOME_AS_UP); }
  @Override public void setDisplayShowTitleEnabled(boolean b) { setDisplayOptions(b ? DISPLAY_SHOW_TITLE : 0, DISPLAY_SHOW_TITLE); }
  @Override public void setDisplayShowCustomEnabled(boolean b) { setDisplayOptions(b ? DISPLAY_SHOW_CUSTOM : 0, DISPLAY_SHOW_CUSTOM); }
  @Override public void setBackgroundDrawable(Drawable d) { mContainer.setBackgroundDrawable(d); }
  @Override public void setStackedBackgroundDrawable(Drawable d) { mTabRow.setBackgroundDrawable(d); }
  @Override public View getCustomView() { return mCustom; }
  @Override public CharSequence getTitle() { return mTitle; }
  @Override public CharSequence getSubtitle() { return mSubtitle; }
  @Override public int getNavigationMode() { return mNavMode; }
  @Override public void setNavigationMode(int mode) { mNavMode = mode; mTabRow.setVisibility(mode == NAVIGATION_MODE_TABS && !mTabs.isEmpty() ? View.VISIBLE : View.GONE); mBar.refresh(); }
  @Override public int getDisplayOptions() { return mOptions; }
  @Override public Tab newTab() { return new TabImpl(); }
  @Override public void addTab(Tab tab) { addTab(tab, mTabs.isEmpty()); }
  @Override public void addTab(Tab tab, boolean setSelected) { addTab(tab, mTabs.size(), setSelected); }
  @Override public void addTab(Tab tab, int position) { addTab(tab, position, mTabs.isEmpty()); }
  @Override public void addTab(Tab tab, int position, boolean setSelected) {
    TabImpl t = (TabImpl) tab;
    mTabs.add(position, t);
    renumber();
    rebuildTabs();
    if (setSelected) selectTab(t);
  }
  @Override public void removeTab(Tab tab) { removeTabAt(tab.getPosition()); }
  @Override public void removeTabAt(int position) {
    if (position < 0 || position >= mTabs.size()) return;
    TabImpl t = mTabs.remove(position);
    renumber();
    if (t == mSelected) { mSelected = null; if (!mTabs.isEmpty()) selectTab(mTabs.get(Math.max(0, position - 1))); }
    rebuildTabs();
  }
  @Override public void removeAllTabs() { mTabs.clear(); mSelected = null; rebuildTabs(); }
  @Override public void selectTab(Tab tab) {
    TabImpl t = (TabImpl) tab;
    FragmentTransaction ft = null;
    try { ft = mActivity.getFragmentManager().beginTransaction(); } catch (RuntimeException e) { ft = null; }
    if (mSelected == t) { if (t != null && t.mListener != null) t.mListener.onTabReselected(t, ft); }
    else {
      if (mSelected != null && mSelected.mListener != null) mSelected.mListener.onTabUnselected(mSelected, ft);
      mSelected = t;
      if (t != null && t.mListener != null) t.mListener.onTabSelected(t, ft);
    }
    if (ft != null) { try { if (!ft.isEmpty()) ft.commit(); } catch (RuntimeException e) { Host.uncaught(e); } }
    rebuildTabs();
  }
  @Override public Tab getSelectedTab() { return mSelected; }
  @Override public Tab getTabAt(int index) { return mTabs.get(index); }
  @Override public int getTabCount() { return mTabs.size(); }
  @Override public int getHeight() { return mShowing ? mHeight : 0; }
  @Override public void show() { mShowing = true; mContainer.setVisibility(View.VISIBLE); }
  @Override public void hide() { mShowing = false; mContainer.setVisibility(View.GONE); }
  @Override public boolean isShowing() { return mShowing; }
  @Override public void addOnMenuVisibilityListener(OnMenuVisibilityListener l) { mMenuListeners.add(l); }
  @Override public void removeOnMenuVisibilityListener(OnMenuVisibilityListener l) { mMenuListeners.remove(l); }
  @Override public void setHomeButtonEnabled(boolean enabled) { mHomeEnabled = enabled; mBar.refresh(); }
  @Override public Context getThemedContext() { return mContext; }
  @Override public void setHomeAsUpIndicator(Drawable d) { mUpIndicator = d; mBar.refresh(); }
  @Override public void setHomeAsUpIndicator(int resId) { setHomeAsUpIndicator(resId == 0 ? null : mContext.getResources().getDrawable(resId)); }

  private void renumber() { for (int i = 0; i < mTabs.size(); i++) mTabs.get(i).mPosition = i; }
  private void rebuildTabs() {
    mTabRow.removeAllViews();
    mTabRow.setVisibility(mNavMode == NAVIGATION_MODE_TABS && !mTabs.isEmpty() ? View.VISIBLE : View.GONE);
    for (final TabImpl t : mTabs) {
      View v;
      if (t.mCustom != null) { v = t.mCustom; if (v.getParent() instanceof ViewGroup) ((ViewGroup) v.getParent()).removeView(v); }
      else {
        TextView tv = new TextView(mContext);
        tv.setText(t.mText != null ? t.mText.toString().toUpperCase() : "");
        tv.setTextColor(t == mSelected ? mTextColor : (mTextColor & 0x00ffffff) | 0x99000000);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        tv.setGravity(Gravity.CENTER);
        if (t.mIcon != null) tv.setCompoundDrawablesWithIntrinsicBounds(t.mIcon, null, null, null);
        v = tv;
      }
      FrameLayout cell = new FrameLayout(mContext) {
        @Override protected void dispatchDraw(Canvas c) {
          super.dispatchDraw(c);
          if (t == mSelected) { Paint p = new Paint(); p.setColor(0xff33b5e5); c.drawRect(0, getHeight() - Math.round(3 * Screen.DENSITY), getWidth(), getHeight(), p); }
        }
      };
      cell.addView(v, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
      cell.setOnClickListener(x -> selectTab(t));
      mTabRow.addView(cell, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
    }
  }

  final class TabImpl extends Tab {
    int mPosition = INVALID_POSITION;
    Drawable mIcon; CharSequence mText, mDesc; View mCustom; Object mTag; TabListener mListener;
    @Override public int getPosition() { return mPosition; }
    @Override public Drawable getIcon() { return mIcon; }
    @Override public CharSequence getText() { return mText; }
    @Override public Tab setIcon(Drawable icon) { mIcon = icon; rebuildTabs(); return this; }
    @Override public Tab setIcon(int resId) { return setIcon(mContext.getResources().getDrawable(resId)); }
    @Override public Tab setText(CharSequence text) { mText = text; rebuildTabs(); return this; }
    @Override public Tab setText(int resId) { return setText(mContext.getText(resId)); }
    @Override public Tab setCustomView(View view) { mCustom = view; rebuildTabs(); return this; }
    @Override public Tab setCustomView(int layoutResId) { return setCustomView(android.view.LayoutInflater.from(mContext).inflate(layoutResId, null)); }
    @Override public View getCustomView() { return mCustom; }
    @Override public Tab setTag(Object obj) { mTag = obj; return this; }
    @Override public Object getTag() { return mTag; }
    @Override public Tab setTabListener(TabListener l) { mListener = l; return this; }
    @Override public void select() { selectTab(this); }
    @Override public Tab setContentDescription(int resId) { mDesc = mContext.getText(resId); return this; }
    @Override public Tab setContentDescription(CharSequence d) { mDesc = d; return this; }
    @Override public CharSequence getContentDescription() { return mDesc; }
  }

  /** an arrow or three dots drawn in the bar's text color */
  /** the overflow (three dots) or up (arrow) glyph, drawn in color */
  public static Drawable glyph(boolean dots, int color) { return new Glyph(dots, color); }
  static final class Glyph extends Drawable {
    final boolean dots; final int color; final int size;
    Glyph(boolean dots, int color) { this.dots = dots; this.color = color; size = Math.round(24 * Screen.DENSITY); }
    @Override public void draw(Canvas c) {
      android.graphics.Rect b = getBounds();
      Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
      p.setColor(color);
      float cx = b.exactCenterX(), cy = b.exactCenterY(), u = size / 24f;
      if (dots) { for (int i = -1; i <= 1; i++) c.drawCircle(cx, cy + i * 6 * u, 2 * u, p); return; }
      p.setStrokeWidth(2 * u); p.setStyle(Paint.Style.STROKE); p.setStrokeCap(Paint.Cap.SQUARE);
      c.drawLine(cx - 7 * u, cy, cx + 7 * u, cy, p);
      c.drawLine(cx - 7 * u, cy, cx - 1 * u, cy - 6 * u, p);
      c.drawLine(cx - 7 * u, cy, cx - 1 * u, cy + 6 * u, p);
    }
    @Override public void setAlpha(int a) {}
    @Override public void setColorFilter(ColorFilter cf) {}
    @Override public int getOpacity() { return PixelFormat.TRANSLUCENT; }
    @Override public int getIntrinsicWidth() { return size; }
    @Override public int getIntrinsicHeight() { return size; }
  }

  /** the bar: [up][icon][title | custom][actions...][overflow] */
  final class BarView extends LinearLayout {
    final ImageView mUp, mHome, mOverflow;
    final LinearLayout mTitles, mActions;
    final TextView mTitleView, mSubtitleView;
    final FrameLayout mCustomFrame;
    BarView(Context c) {
      super(c);
      setOrientation(HORIZONTAL);
      setGravity(Gravity.CENTER_VERTICAL);
      int pad = Math.round(8 * Screen.DENSITY);
      mUp = new ImageView(c);
      mUp.setScaleType(ImageView.ScaleType.CENTER);
      addView(mUp, new LinearLayout.LayoutParams(Math.round(32 * Screen.DENSITY), ViewGroup.LayoutParams.MATCH_PARENT));
      mHome = new ImageView(c);
      mHome.setScaleType(ImageView.ScaleType.FIT_CENTER);
      mHome.setPadding(pad, pad, pad, pad);
      addView(mHome, new LinearLayout.LayoutParams(mHeight, ViewGroup.LayoutParams.MATCH_PARENT));
      View.OnClickListener home = v -> homeClicked();
      mUp.setOnClickListener(home);
      mHome.setOnClickListener(home);
      mTitles = new LinearLayout(c);
      mTitles.setOrientation(VERTICAL);
      mTitles.setGravity(Gravity.CENTER_VERTICAL);
      mTitles.setPadding(pad, 0, pad, 0);
      mTitleView = new TextView(c);
      mTitleView.setSingleLine(true);
      mTitleView.setEllipsize(TextUtils.TruncateAt.END);
      mTitleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
      mTitleView.setTextColor(mTextColor);
      mSubtitleView = new TextView(c);
      mSubtitleView.setSingleLine(true);
      mSubtitleView.setEllipsize(TextUtils.TruncateAt.END);
      mSubtitleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
      mSubtitleView.setTextColor((mTextColor & 0x00ffffff) | 0xb3000000);
      mTitles.addView(mTitleView);
      mTitles.addView(mSubtitleView);
      addView(mTitles, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
      mCustomFrame = new FrameLayout(c);
      addView(mCustomFrame, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
      mActions = new LinearLayout(c);
      mActions.setOrientation(HORIZONTAL);
      addView(mActions, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
      mOverflow = new ImageView(c);
      mOverflow.setScaleType(ImageView.ScaleType.CENTER);
      mOverflow.setImageDrawable(new Glyph(true, mTextColor));
      mOverflow.setOnClickListener(v -> mActivity.openOptionsMenu());
      addView(mOverflow, new LinearLayout.LayoutParams(Math.round(40 * Screen.DENSITY), ViewGroup.LayoutParams.MATCH_PARENT));
    }
    void homeClicked() {
      if ((mOptions & DISPLAY_HOME_AS_UP) == 0 && !mHomeEnabled) return;
      MenuBuilder m = new MenuBuilder(mActivity);
      MenuItem home = m.add(0, android.R.id.home, 0, mTitle);
      mActivity.onMenuItemSelected(android.view.Window.FEATURE_OPTIONS_PANEL, home);
    }
    private boolean mActionsReady;
    void refresh() {
      boolean up = (mOptions & DISPLAY_HOME_AS_UP) != 0;
      mUp.setVisibility(up ? VISIBLE : GONE);
      mUp.setImageDrawable(mUpIndicator != null ? mUpIndicator : new Glyph(false, mTextColor));
      Drawable icon = (mOptions & DISPLAY_USE_LOGO) != 0 && mLogo != null ? mLogo : mIcon;
      if (icon == null) { try { icon = mActivity.getPackageManager().getApplicationIcon(mActivity.getApplicationInfo()); } catch (RuntimeException e) { icon = null; } }
      boolean showHome = (mOptions & DISPLAY_SHOW_HOME) != 0 && icon != null;
      mHome.setVisibility(showHome ? VISIBLE : GONE);
      mHome.setImageDrawable(icon);
      boolean title = (mOptions & DISPLAY_SHOW_TITLE) != 0 && mNavMode != NAVIGATION_MODE_LIST;
      CharSequence t = mTitle;
      if (mNavMode == NAVIGATION_MODE_LIST && mListAdapter != null && mListSelected >= 0 && mListSelected < mListAdapter.getCount()) {
        Object o = mListAdapter.getItem(mListSelected);
        t = o == null ? t : o.toString();
        title = true;
      }
      mTitleView.setText(t);
      mSubtitleView.setText(mSubtitle);
      mSubtitleView.setVisibility(TextUtils.isEmpty(mSubtitle) ? GONE : VISIBLE);
      boolean custom = (mOptions & DISPLAY_SHOW_CUSTOM) != 0 && mCustom != null;
      mCustomFrame.removeAllViews();
      if (custom) {
        if (mCustom.getParent() instanceof ViewGroup) ((ViewGroup) mCustom.getParent()).removeView(mCustom);
        ViewGroup.LayoutParams lp = mCustom.getLayoutParams();
        int g = lp instanceof ActionBar.LayoutParams ? ((ActionBar.LayoutParams) lp).gravity : Gravity.CENTER_VERTICAL | Gravity.START;
        FrameLayout.LayoutParams flp = new FrameLayout.LayoutParams(lp != null ? lp.width : ViewGroup.LayoutParams.WRAP_CONTENT,
            lp != null ? lp.height : ViewGroup.LayoutParams.MATCH_PARENT, g == 0 ? Gravity.CENTER_VERTICAL | Gravity.START : g);
        mCustomFrame.addView(mCustom, flp);
      }
      mTitles.setVisibility(title ? VISIBLE : GONE);
      mCustomFrame.setVisibility(custom ? VISIBLE : GONE);
      ((LinearLayout.LayoutParams) mTitles.getLayoutParams()).weight = custom ? 0 : 1;
      ((LinearLayout.LayoutParams) mTitles.getLayoutParams()).width = custom ? ViewGroup.LayoutParams.WRAP_CONTENT : 0;
      if (!title && !custom) { ((LinearLayout.LayoutParams) mCustomFrame.getLayoutParams()).weight = 1; mCustomFrame.setVisibility(VISIBLE); }
      if (mActionsReady) refreshActions();
    }
    void refreshActions() {
      mActionsReady = true;
      mActions.removeAllViews();
      MenuBuilder m = mActivity.optionsMenuForBar();
      boolean overflow = false;
      if (m != null) {
        int room = Math.max(1, (Screen.W / 2) / mHeight);
        for (MenuItemImpl it : m.visibleItems()) {
          int s = it.showAsAction();
          boolean always = (s & MenuItem.SHOW_AS_ACTION_ALWAYS) != 0, ifRoom = (s & MenuItem.SHOW_AS_ACTION_IF_ROOM) != 0;
          if ((always || ifRoom && room > 0) && mActions.getChildCount() < 6) {
            mActions.addView(actionView(it), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            room--;
          } else overflow = true;
        }
      }
      mOverflow.setVisibility(overflow ? VISIBLE : GONE);
    }
    private View actionView(final MenuItemImpl it) {
      View av = it.getActionView();
      if (av != null) { if (av.getParent() instanceof ViewGroup) ((ViewGroup) av.getParent()).removeView(av); return av; }
      Drawable icon = it.getIcon();
      View v;
      if (icon != null && (it.showAsAction() & MenuItem.SHOW_AS_ACTION_WITH_TEXT) == 0) {
        ImageView iv = new ImageView(mContext);
        iv.setImageDrawable(icon);
        iv.setScaleType(ImageView.ScaleType.CENTER);
        iv.setMinimumWidth(mHeight);
        v = iv;
      } else {
        TextView tv = new TextView(mContext);
        tv.setText(it.getTitleCondensed() != null ? it.getTitleCondensed().toString().toUpperCase() : "");
        tv.setTextColor(mTextColor);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        tv.setGravity(Gravity.CENTER);
        int p = Math.round(12 * Screen.DENSITY);
        tv.setPadding(p, 0, p, 0);
        if (icon != null) tv.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
        v = tv;
      }
      v.setEnabled(it.isEnabled());
      v.setOnClickListener(x -> mActivity.onMenuItemSelected(android.view.Window.FEATURE_OPTIONS_PANEL, it));
      v.setOnLongClickListener(x -> { android.widget.Toast.makeText(mContext, it.getTitle(), android.widget.Toast.LENGTH_SHORT).show(); return true; });
      return v;
    }
  }
}
