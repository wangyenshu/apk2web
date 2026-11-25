package android.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * A row of action items for a menu (a Toolbar's): items shown as actions (showAsAction always, or ifRoom while there
 * is room), then an overflow button opening the rest in a popup. The items follow the menu's changes.
 */
public class ActionMenuView extends LinearLayout {
  public interface OnMenuItemClickListener { boolean onMenuItemClick(MenuItem item); }

  private a2w.MenuBuilder mMenu;
  private OnMenuItemClickListener mOnMenuItemClickListener;
  private Drawable mOverflowIcon;
  private int mPopupTheme;
  private android.app.Dialog mPopup;
  private final ImageView mOverflowButton;
  private boolean mDirty;
  /** the menu changed: the items are made again at the next measure */
  private final Runnable mRebuild = () -> { mDirty = true; requestLayout(); invalidate(); };
  private int mItemColor;
  /** set by a Toolbar: the color of text items and of the overflow glyph */
  void setItemColor(int color) { mItemColor = color; if (mOverflowIcon == null) mOverflowButton.setImageDrawable(a2w.ActionBarImpl.glyph(true, color)); mRebuild.run(); }

  public ActionMenuView(Context context) { this(context, null); }
  public ActionMenuView(Context context, AttributeSet attrs) {
    super(context, attrs);
    setBaselineAligned(false);
    setGravity(Gravity.CENTER_VERTICAL);
    mItemColor = defaultColor(context);
    mOverflowButton = new ImageView(context);
    mOverflowButton.setScaleType(ImageView.ScaleType.CENTER);
    mOverflowButton.setImageDrawable(a2w.ActionBarImpl.glyph(true, mItemColor));
    mOverflowButton.setContentDescription("More options");
    mOverflowButton.setFocusable(true);
    mOverflowButton.setOnClickListener(v -> showOverflowMenu());
  }
  static int defaultColor(Context c) {
    try {
      android.content.res.TypedArray ta = c.obtainStyledAttributes(new int[] {android.R.attr.textColorPrimary});
      int v = ta.getColor(0, 0xffffffff);
      ta.recycle();
      return v;
    } catch (RuntimeException e) { return 0xffffffff; }
  }

  public void setPopupTheme(int resId) { mPopupTheme = resId; }
  public int getPopupTheme() { return mPopupTheme; }
  public void setOnMenuItemClickListener(OnMenuItemClickListener listener) { mOnMenuItemClickListener = listener; }
  public Menu getMenu() {
    if (mMenu == null) setMenuInternal(new a2w.MenuBuilder(getContext()), true);
    return mMenu;
  }
  /** shows menu (own: item clicks go to the OnMenuItemClickListener; else the menu's own callback handles them) */
  void setMenuInternal(a2w.MenuBuilder menu, boolean own) {
    if (mMenu == menu) { mRebuild.run(); return; }
    if (mMenu != null) mMenu.removeWatcher(mRebuild);
    mMenu = menu;
    if (menu != null) {
      if (own) menu.setCallback((m, item) -> mOnMenuItemClickListener != null && mOnMenuItemClickListener.onMenuItemClick(item));
      menu.addWatcher(mRebuild);
    }
    mRebuild.run();
  }
  boolean hasItems() { return mMenu != null && mMenu.hasVisibleItems(); }
  public void setOverflowIcon(Drawable icon) { mOverflowIcon = icon; mOverflowButton.setImageDrawable(icon != null ? icon : a2w.ActionBarImpl.glyph(true, mItemColor)); }
  public Drawable getOverflowIcon() { return mOverflowButton.getDrawable(); }
  public boolean showOverflowMenu() {
    if (mMenu == null || mOverflowButton.getParent() != this) return false;
    dismissPopupMenus();
    mPopup = a2w.MenuPopup.show(overflowMenu(), () -> mPopup = null);
    return mPopup != null;
  }
  public boolean hideOverflowMenu() { boolean showing = isOverflowMenuShowing(); dismissPopupMenus(); return showing; }
  public boolean isOverflowMenuShowing() { return mPopup != null && mPopup.isShowing(); }
  public void dismissPopupMenus() { if (mPopup != null) { android.app.Dialog p = mPopup; mPopup = null; p.dismiss(); } }
  @Override public void onConfigurationChanged(Configuration newConfig) { super.onConfigurationChanged(newConfig); mRebuild.run(); }
  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    if (mDirty) { mDirty = false; rebuild(); }
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }
  @Override protected void onDetachedFromWindow() { super.onDetachedFromWindow(); dismissPopupMenus(); }

  private static boolean wantsBar(a2w.MenuItemImpl it) { return (it.showAsAction() & (MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_IF_ROOM)) != 0; }
  private final java.util.ArrayList<a2w.MenuItemImpl> mOnBar = new java.util.ArrayList<>();
  /** the items that are not on the bar, in a menu whose clicks act on the originals */
  private a2w.MenuBuilder overflowMenu() {
    a2w.MenuBuilder m = new a2w.MenuBuilder(getContext());
    for (a2w.MenuItemImpl it : mMenu.visibleItems()) {
      if (mOnBar.contains(it)) continue;
      MenuItem copy = m.add(it.getGroupId(), it.getItemId(), it.getOrder(), it.getTitle());
      copy.setEnabled(it.isEnabled()); copy.setCheckable(it.isCheckable()); copy.setChecked(it.isChecked());
      final a2w.MenuItemImpl orig = it;
      copy.setOnMenuItemClickListener(x -> { mMenu.performItemAction(orig, 0); return true; });
    }
    return m;
  }
  /** the action items: those that ask for the bar, as many as fit in half the screen (always-items all), then the overflow */
  private void rebuild() {
    removeAllViews();
    mOnBar.clear();
    if (mMenu == null) return;
    float density = getResources().getDisplayMetrics().density;
    int itemWidth = Math.round(48 * density);
    int room = Math.max(1, (getResources().getDisplayMetrics().widthPixels / 2) / itemWidth);
    boolean overflow = false;
    for (a2w.MenuItemImpl it : mMenu.visibleItems()) {
      int s = it.showAsAction();
      boolean always = (s & MenuItem.SHOW_AS_ACTION_ALWAYS) != 0, ifRoom = (s & MenuItem.SHOW_AS_ACTION_IF_ROOM) != 0;
      if ((always || ifRoom && room > 0)) {
        View v = itemView(it, itemWidth);
        addView(v, generateDefaultLayoutParams());
        mOnBar.add(it);
        room--;
      } else overflow = true;
    }
    if (overflow) addView(mOverflowButton, new LayoutParams(Math.round(40 * density), ViewGroup.LayoutParams.MATCH_PARENT));
  }
  private View itemView(final a2w.MenuItemImpl it, int minWidth) {
    View av = it.getActionView();
    if (av != null) { if (av.getParent() instanceof ViewGroup) ((ViewGroup) av.getParent()).removeView(av); return av; }
    Drawable icon = it.getIcon();
    View v;
    if (icon != null && (it.showAsAction() & MenuItem.SHOW_AS_ACTION_WITH_TEXT) == 0) {
      ImageView iv = new ImageView(getContext());
      iv.setImageDrawable(icon);
      iv.setScaleType(ImageView.ScaleType.CENTER);
      iv.setMinimumWidth(minWidth);
      iv.setContentDescription(it.getTitle());
      v = iv;
    } else {
      TextView t = new TextView(getContext());
      CharSequence title = it.getTitleCondensed();
      t.setText(title == null ? "" : title.toString().toUpperCase());
      t.setTextColor(mItemColor);
      t.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
      t.setGravity(Gravity.CENTER);
      t.setMinWidth(minWidth);
      int p = Math.round(12 * getResources().getDisplayMetrics().density);
      t.setPadding(p, 0, p, 0);
      if (icon != null) t.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
      v = t;
    }
    v.setFocusable(true);
    v.setEnabled(it.isEnabled());
    v.setOnClickListener(x -> mMenu.performItemAction(it, 0));
    v.setOnLongClickListener(x -> { Toast.makeText(getContext(), it.getTitle(), Toast.LENGTH_SHORT).show(); return true; });
    return v;
  }

  @Override protected LayoutParams generateDefaultLayoutParams() {
    LayoutParams p = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
    p.gravity = Gravity.CENTER_VERTICAL;
    return p;
  }
  @Override public LayoutParams generateLayoutParams(AttributeSet attrs) { return new LayoutParams(getContext(), attrs); }
  @Override protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
    if (p == null) return generateDefaultLayoutParams();
    LayoutParams r = p instanceof LayoutParams ? new LayoutParams((LayoutParams) p) : new LayoutParams(p);
    if (r.gravity <= Gravity.NO_GRAVITY) r.gravity = Gravity.CENTER_VERTICAL;
    return r;
  }
  @Override protected boolean checkLayoutParams(ViewGroup.LayoutParams p) { return p instanceof LayoutParams; }

  public static class LayoutParams extends LinearLayout.LayoutParams {
    public LayoutParams(Context c, AttributeSet attrs) { super(c, attrs); }
    public LayoutParams(ViewGroup.LayoutParams other) { super(other); }
    public LayoutParams(LayoutParams other) { super((LinearLayout.LayoutParams) other); }
    public LayoutParams(int width, int height) { super(width, height); }
  }
}
