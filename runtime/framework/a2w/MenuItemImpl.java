package a2w;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

public final class MenuItemImpl implements MenuItem {
  final MenuBuilder mMenu;
  final int mGroup, mId, mCategoryOrder;
  CharSequence mTitle, mTitleCondensed;
  Drawable mIcon;
  int mIconRes;
  Intent mIntent;
  char mShortcutNumericChar, mShortcutAlphabeticChar;
  boolean mCheckable, mChecked, mExclusive, mVisible = true, mEnabled = true, mExpanded;
  MenuBuilder.SubMenuBuilder mSubMenu;
  OnMenuItemClickListener mClickListener;
  OnActionExpandListener mExpandListener;
  ContextMenu.ContextMenuInfo mMenuInfo;
  View mActionView;
  ActionProvider mActionProvider;
  int mShowAsAction;
  Object mClickTarget;
  String mClickMethod;
  MenuItemImpl(MenuBuilder menu, int group, int id, int categoryOrder, CharSequence title) {
    mMenu = menu; mGroup = group; mId = id; mCategoryOrder = categoryOrder; mTitle = title;
  }
  /** android:onClick="method" on a menu item: the activity's method(MenuItem) */
  public void setOnClickMethod(Object target, String method) { mClickTarget = target; mClickMethod = method; }
  boolean invoke() {
    if (mClickListener != null && mClickListener.onMenuItemClick(this)) return true;
    if (mClickMethod != null && mClickTarget != null && Reflection.call(mClickTarget, mClickMethod, this)) return true;
    if (mActionProvider != null && mActionProvider.onPerformDefaultAction()) return true;
    return false;
  }
  public int getItemId() { return mId; }
  public int getGroupId() { return mGroup; }
  public int getOrder() { return mCategoryOrder; }
  public MenuItem setTitle(CharSequence title) { mTitle = title; if (mSubMenu != null) mSubMenu.headerTitle(title); mMenu.changed(); return this; }
  public MenuItem setTitle(int title) { return setTitle(mMenu.getContext().getText(title)); }
  public CharSequence getTitle() { return mTitle; }
  public MenuItem setTitleCondensed(CharSequence title) { mTitleCondensed = title; mMenu.changed(); return this; }
  public CharSequence getTitleCondensed() { return mTitleCondensed != null ? mTitleCondensed : mTitle; }
  public MenuItem setIcon(Drawable icon) { mIcon = icon; mIconRes = 0; mMenu.changed(); return this; }
  public MenuItem setIcon(int iconRes) { mIcon = null; mIconRes = iconRes; mMenu.changed(); return this; }
  public Drawable getIcon() { if (mIcon == null && mIconRes != 0) { try { mIcon = mMenu.getContext().getResources().getDrawable(mIconRes); } catch (RuntimeException e) {} } return mIcon; }
  public MenuItem setIntent(Intent intent) { mIntent = intent; return this; }
  public Intent getIntent() { return mIntent; }
  public MenuItem setShortcut(char numericChar, char alphaChar) { mShortcutNumericChar = numericChar; mShortcutAlphabeticChar = Character.toLowerCase(alphaChar); return this; }
  public MenuItem setNumericShortcut(char numericChar) { mShortcutNumericChar = numericChar; return this; }
  public char getNumericShortcut() { return mShortcutNumericChar; }
  public MenuItem setAlphabeticShortcut(char alphaChar) { mShortcutAlphabeticChar = Character.toLowerCase(alphaChar); return this; }
  public char getAlphabeticShortcut() { return mShortcutAlphabeticChar; }
  public MenuItem setCheckable(boolean checkable) { mCheckable = checkable; return this; }
  public boolean isCheckable() { return mCheckable; }
  public MenuItem setChecked(boolean checked) {
    if (mExclusive && checked) { for (MenuItemImpl o : mMenu.mItems) if (o.getGroupId() == mGroup && o.mExclusive) o.mChecked = false; }
    mChecked = checked;
    mMenu.changed();
    return this;
  }
  public boolean isChecked() { return mChecked; }
  public MenuItem setVisible(boolean visible) { mVisible = visible; mMenu.changed(); return this; }
  public boolean isVisible() { return mVisible && (mActionProvider == null || !mActionProvider.overridesItemVisibility() || mActionProvider.isVisible()); }
  public MenuItem setEnabled(boolean enabled) { mEnabled = enabled; mMenu.changed(); return this; }
  public boolean isEnabled() { return mEnabled; }
  public boolean hasSubMenu() { return mSubMenu != null; }
  public SubMenu getSubMenu() { return mSubMenu; }
  public MenuItem setOnMenuItemClickListener(OnMenuItemClickListener l) { mClickListener = l; return this; }
  public ContextMenu.ContextMenuInfo getMenuInfo() { return mMenuInfo; }
  public void setShowAsAction(int actionEnum) { mShowAsAction = actionEnum; mMenu.changed(); }
  public int showAsAction() { return mShowAsAction; }
  public MenuItem setShowAsActionFlags(int actionEnum) { setShowAsAction(actionEnum); return this; }
  public MenuItem setActionView(View view) { mActionView = view; mMenu.changed(); return this; }
  public MenuItem setActionView(int resId) { mActionView = LayoutInflater.from(mMenu.getContext()).inflate(resId, null); mMenu.changed(); return this; }
  public View getActionView() {
    if (mActionView == null && mActionProvider != null) mActionView = mActionProvider.onCreateActionView(this);
    return mActionView;
  }
  public MenuItem setActionProvider(ActionProvider actionProvider) { mActionProvider = actionProvider; return this; }
  public ActionProvider getActionProvider() { return mActionProvider; }
  public boolean expandActionView() { if (mExpandListener != null && !mExpandListener.onMenuItemActionExpand(this)) return false; mExpanded = true; return true; }
  public boolean collapseActionView() { if (mExpandListener != null && !mExpandListener.onMenuItemActionCollapse(this)) return false; mExpanded = false; return true; }
  public boolean isActionViewExpanded() { return mExpanded; }
  public MenuItem setOnActionExpandListener(OnActionExpandListener listener) { mExpandListener = listener; return this; }
  @Override public String toString() { return mTitle == null ? "" : mTitle.toString(); }
}
