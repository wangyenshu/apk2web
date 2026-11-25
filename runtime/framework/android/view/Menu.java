package android.view;

import android.content.ComponentName;
import android.content.Intent;

public interface Menu {
  int USER_MASK = 0x0000ffff, USER_SHIFT = 0, CATEGORY_MASK = 0xffff0000, CATEGORY_SHIFT = 16, SUPPORTED_MODIFIERS_MASK = 0x1100f;
  int NONE = 0, FIRST = 1, CATEGORY_CONTAINER = 0x00010000, CATEGORY_SYSTEM = 0x00020000, CATEGORY_SECONDARY = 0x00030000, CATEGORY_ALTERNATIVE = 0x00040000;
  int FLAG_APPEND_TO_GROUP = 0x0001, FLAG_PERFORM_NO_CLOSE = 0x0001, FLAG_ALWAYS_PERFORM_CLOSE = 0x0002;
  MenuItem add(CharSequence title);
  MenuItem add(int titleRes);
  MenuItem add(int groupId, int itemId, int order, CharSequence title);
  MenuItem add(int groupId, int itemId, int order, int titleRes);
  SubMenu addSubMenu(CharSequence title);
  SubMenu addSubMenu(int titleRes);
  SubMenu addSubMenu(int groupId, int itemId, int order, CharSequence title);
  SubMenu addSubMenu(int groupId, int itemId, int order, int titleRes);
  int addIntentOptions(int groupId, int itemId, int order, ComponentName caller, Intent[] specifics, Intent intent, int flags, MenuItem[] outSpecificItems);
  void removeItem(int id);
  void removeGroup(int groupId);
  void clear();
  void setGroupCheckable(int group, boolean checkable, boolean exclusive);
  void setGroupVisible(int group, boolean visible);
  void setGroupEnabled(int group, boolean enabled);
  boolean hasVisibleItems();
  MenuItem findItem(int id);
  int size();
  MenuItem getItem(int index);
  void close();
  boolean performShortcut(int keyCode, KeyEvent event, int flags);
  boolean isShortcutKey(int keyCode, KeyEvent event);
  boolean performIdentifierAction(int id, int flags);
  void setQwertyMode(boolean isQwerty);
  default void setGroupDividerEnabled(boolean groupDividerEnabled) {}
}
