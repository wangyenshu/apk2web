package a2w;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import java.util.ArrayList;

/** Options, context and sub menus: items kept sorted by order (category, then order in category, then insertion). */
public class MenuBuilder implements Menu {
  public interface Callback { boolean onItemSelected(MenuBuilder menu, MenuItem item); }
  final Context mContext;
  final ArrayList<MenuItemImpl> mItems = new ArrayList<>();
  CharSequence mHeaderTitle;
  Drawable mHeaderIcon;
  View mHeaderView;
  Callback mCallback;
  ContextMenu.ContextMenuInfo mMenuInfo;
  MenuBuilder mParent;
  public MenuBuilder(Context c) { mContext = c; }
  // ---- views showing this menu (a toolbar's ActionMenuView) are told when items change
  private java.util.ArrayList<Runnable> mWatchers;
  public void addWatcher(Runnable r) { if (mWatchers == null) mWatchers = new java.util.ArrayList<>(); if (!mWatchers.contains(r)) mWatchers.add(r); }
  public void removeWatcher(Runnable r) { if (mWatchers != null) mWatchers.remove(r); }
  public void changed() {
    MenuBuilder root = this;
    while (root.mParent != null) root = root.mParent;
    if (root.mWatchers == null || root.mWatchers.isEmpty()) return;
    for (Runnable w : new java.util.ArrayList<>(root.mWatchers)) w.run();
  }
  public Context getContext() { return mContext; }
  public void setCallback(Callback cb) { mCallback = cb; }
  public void setMenuInfo(ContextMenu.ContextMenuInfo info) { mMenuInfo = info; }
  public CharSequence getHeaderTitle() { return mHeaderTitle; }
  public ArrayList<MenuItemImpl> visibleItems() {
    ArrayList<MenuItemImpl> out = new ArrayList<>();
    for (MenuItemImpl i : mItems) if (i.isVisible()) out.add(i);
    return out;
  }
  private MenuItemImpl addInternal(int group, int id, int categoryOrder, CharSequence title) {
    MenuItemImpl item = new MenuItemImpl(this, group, id, categoryOrder, title);
    item.mMenuInfo = mMenuInfo;
    int i = mItems.size();
    while (i > 0 && mItems.get(i - 1).getOrder() > categoryOrder) i--;
    mItems.add(i, item);
    changed();
    return item;
  }
  public MenuItem add(CharSequence title) { return addInternal(0, 0, 0, title); }
  public MenuItem add(int titleRes) { return addInternal(0, 0, 0, mContext.getText(titleRes)); }
  public MenuItem add(int groupId, int itemId, int order, CharSequence title) { return addInternal(groupId, itemId, order, title); }
  public MenuItem add(int groupId, int itemId, int order, int titleRes) { return addInternal(groupId, itemId, order, mContext.getText(titleRes)); }
  public SubMenu addSubMenu(CharSequence title) { return addSubMenu(0, 0, 0, title); }
  public SubMenu addSubMenu(int titleRes) { return addSubMenu(0, 0, 0, mContext.getText(titleRes)); }
  public SubMenu addSubMenu(int groupId, int itemId, int order, int titleRes) { return addSubMenu(groupId, itemId, order, mContext.getText(titleRes)); }
  public SubMenu addSubMenu(int groupId, int itemId, int order, CharSequence title) {
    MenuItemImpl item = addInternal(groupId, itemId, order, title);
    SubMenuBuilder sub = new SubMenuBuilder(mContext, this, item);
    item.mSubMenu = sub;
    return sub;
  }
  public int addIntentOptions(int groupId, int itemId, int order, ComponentName caller, Intent[] specifics, Intent intent, int flags, MenuItem[] outSpecificItems) {
    java.util.List<android.content.pm.ResolveInfo> lri = mContext.getPackageManager().queryIntentActivities(intent, 0);
    if ((flags & FLAG_APPEND_TO_GROUP) == 0) removeGroup(groupId);
    for (android.content.pm.ResolveInfo ri : lri) {
      Intent i = new Intent(intent);
      i.setComponent(new ComponentName(ri.activityInfo.packageName, ri.activityInfo.name));
      add(groupId, itemId, order, ri.loadLabel(mContext.getPackageManager())).setIntent(i);
    }
    return lri.size();
  }
  public void removeItem(int id) { for (int i = 0; i < mItems.size(); i++) if (mItems.get(i).getItemId() == id) { mItems.remove(i); changed(); return; } }
  public void removeGroup(int groupId) { mItems.removeIf(i -> i.getGroupId() == groupId); changed(); }
  public void clear() { mItems.clear(); changed(); }
  public void setGroupCheckable(int group, boolean checkable, boolean exclusive) {
    for (MenuItemImpl i : mItems) if (i.getGroupId() == group) { i.setCheckable(checkable); i.mExclusive = exclusive; }
  }
  public void setGroupVisible(int group, boolean visible) { for (MenuItemImpl i : mItems) if (i.getGroupId() == group) i.setVisible(visible); }
  public void setGroupEnabled(int group, boolean enabled) { for (MenuItemImpl i : mItems) if (i.getGroupId() == group) i.setEnabled(enabled); }
  public boolean hasVisibleItems() { for (MenuItemImpl i : mItems) if (i.isVisible()) return true; return false; }
  public MenuItem findItem(int id) {
    for (MenuItemImpl i : mItems) {
      if (i.getItemId() == id) return i;
      if (i.mSubMenu != null) { MenuItem f = i.mSubMenu.findItem(id); if (f != null) return f; }
    }
    return null;
  }
  public int size() { return mItems.size(); }
  public MenuItem getItem(int index) { return mItems.get(index); }
  public void close() { MenuPopup.closeAll(); }
  public boolean performShortcut(int keyCode, KeyEvent event, int flags) {
    char c = (char) event.getUnicodeChar();
    for (MenuItemImpl i : mItems) if (i.isEnabled() && i.isVisible() && (Character.toLowerCase(i.getAlphabeticShortcut()) == Character.toLowerCase(c) && c != 0 || i.getNumericShortcut() == c && c != 0)) return performItemAction(i, flags);
    return false;
  }
  public boolean isShortcutKey(int keyCode, KeyEvent event) {
    char c = (char) event.getUnicodeChar();
    for (MenuItemImpl i : mItems) if (c != 0 && (Character.toLowerCase(i.getAlphabeticShortcut()) == Character.toLowerCase(c) || i.getNumericShortcut() == c)) return true;
    return false;
  }
  public boolean performIdentifierAction(int id, int flags) { MenuItem i = findItem(id); return i instanceof MenuItemImpl && performItemAction((MenuItemImpl) i, flags); }
  public void setQwertyMode(boolean isQwerty) {}
  void headerTitle(CharSequence title) { mHeaderTitle = title; }
  void headerIcon(Drawable icon) { mHeaderIcon = icon; }
  void headerView(View view) { mHeaderView = view; }
  public void clearHeader() { mHeaderTitle = null; mHeaderIcon = null; mHeaderView = null; }
  /** an item was chosen: its listener, then the menu's owner (onOptionsItemSelected...), then its intent; submenus open */
  public boolean performItemAction(MenuItemImpl item, int flags) {
    if (!item.isEnabled()) return false;
    if (item.isCheckable()) {
      if (item.mExclusive) { for (MenuItemImpl o : mItems) if (o.getGroupId() == item.getGroupId() && o.isCheckable()) o.mChecked = o == item; }
    }
    if (item.invoke()) return true;
    MenuBuilder root = this;
    while (root.mParent != null && root.mCallback == null) root = root.mParent;
    if (root.mCallback != null && root.mCallback.onItemSelected(this, item)) return true;
    if (item.mSubMenu != null && item.mSubMenu.hasVisibleItems()) { MenuPopup.show(item.mSubMenu); return true; }
    if (item.getIntent() != null) { try { mContext.startActivity(item.getIntent()); return true; } catch (RuntimeException e) { android.util.Log.e("MenuItemImpl", "Can't find activity to handle intent; ignoring", e); } }
    return false;
  }

  public static final class SubMenuBuilder extends MenuBuilder implements SubMenu {
    final MenuItemImpl mItem;
    SubMenuBuilder(Context c, MenuBuilder parent, MenuItemImpl item) { super(c); mParent = parent; mItem = item; mHeaderTitle = item.getTitle(); }
    public MenuItem getItem() { return mItem; }
    public SubMenu setHeaderTitle(int titleRes) { headerTitle(mContext.getText(titleRes)); return this; }
    public SubMenu setHeaderTitle(CharSequence title) { headerTitle(title); return this; }
    public SubMenu setHeaderIcon(int iconRes) { headerIcon(mContext.getResources().getDrawable(iconRes)); return this; }
    public SubMenu setHeaderIcon(Drawable icon) { headerIcon(icon); return this; }
    public SubMenu setHeaderView(View view) { headerView(view); return this; }
    public SubMenu setIcon(int iconRes) { mItem.setIcon(iconRes); return this; }
    public SubMenu setIcon(Drawable icon) { mItem.setIcon(icon); return this; }
  }

  /** the menu a long-pressed view (and its parents) build: items report to the activity's onContextItemSelected */
  public static final class ContextMenuBuilder extends MenuBuilder implements ContextMenu {
    public ContextMenuBuilder(Context c) { super(c); }
    public ContextMenu setHeaderTitle(int titleRes) { headerTitle(mContext.getText(titleRes)); return this; }
    public ContextMenu setHeaderTitle(CharSequence title) { headerTitle(title); return this; }
    public ContextMenu setHeaderIcon(int iconRes) { headerIcon(mContext.getResources().getDrawable(iconRes)); return this; }
    public ContextMenu setHeaderIcon(Drawable icon) { headerIcon(icon); return this; }
    public ContextMenu setHeaderView(View view) { headerView(view); return this; }
  }
}
