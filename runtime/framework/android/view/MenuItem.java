package android.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;

public interface MenuItem {
  int SHOW_AS_ACTION_NEVER = 0, SHOW_AS_ACTION_IF_ROOM = 1, SHOW_AS_ACTION_ALWAYS = 2, SHOW_AS_ACTION_WITH_TEXT = 4, SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW = 8;
  interface OnMenuItemClickListener { boolean onMenuItemClick(MenuItem item); }
  interface OnActionExpandListener { boolean onMenuItemActionExpand(MenuItem item); boolean onMenuItemActionCollapse(MenuItem item); }
  int getItemId();
  int getGroupId();
  int getOrder();
  MenuItem setTitle(CharSequence title);
  MenuItem setTitle(int title);
  CharSequence getTitle();
  MenuItem setTitleCondensed(CharSequence title);
  CharSequence getTitleCondensed();
  MenuItem setIcon(Drawable icon);
  MenuItem setIcon(int iconRes);
  Drawable getIcon();
  MenuItem setIntent(Intent intent);
  Intent getIntent();
  MenuItem setShortcut(char numericChar, char alphaChar);
  MenuItem setNumericShortcut(char numericChar);
  char getNumericShortcut();
  MenuItem setAlphabeticShortcut(char alphaChar);
  char getAlphabeticShortcut();
  MenuItem setCheckable(boolean checkable);
  boolean isCheckable();
  MenuItem setChecked(boolean checked);
  boolean isChecked();
  MenuItem setVisible(boolean visible);
  boolean isVisible();
  MenuItem setEnabled(boolean enabled);
  boolean isEnabled();
  boolean hasSubMenu();
  SubMenu getSubMenu();
  MenuItem setOnMenuItemClickListener(OnMenuItemClickListener menuItemClickListener);
  ContextMenu.ContextMenuInfo getMenuInfo();
  void setShowAsAction(int actionEnum);
  MenuItem setShowAsActionFlags(int actionEnum);
  MenuItem setActionView(View view);
  MenuItem setActionView(int resId);
  View getActionView();
  MenuItem setActionProvider(ActionProvider actionProvider);
  ActionProvider getActionProvider();
  boolean expandActionView();
  boolean collapseActionView();
  boolean isActionViewExpanded();
  MenuItem setOnActionExpandListener(OnActionExpandListener listener);
  default MenuItem setContentDescription(CharSequence contentDescription) { return this; }
  default CharSequence getContentDescription() { return null; }
  default MenuItem setTooltipText(CharSequence tooltipText) { return this; }
  default CharSequence getTooltipText() { return null; }
  default MenuItem setIconTintList(android.content.res.ColorStateList tint) { return this; }
  default MenuItem setIconTintMode(android.graphics.PorterDuff.Mode tintMode) { return this; }
}
