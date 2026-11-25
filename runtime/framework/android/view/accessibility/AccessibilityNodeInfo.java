package android.view.accessibility;

/** android.view.accessibility.AccessibilityNodeInfo (no accessibility services in the page) */
public class AccessibilityNodeInfo {
  public static final int ACTION_FOCUS = 1, ACTION_CLEAR_FOCUS = 2, ACTION_SELECT = 4, ACTION_CLEAR_SELECTION = 8, ACTION_CLICK = 16,
      ACTION_LONG_CLICK = 32, ACTION_ACCESSIBILITY_FOCUS = 64, ACTION_CLEAR_ACCESSIBILITY_FOCUS = 128, ACTION_SCROLL_FORWARD = 4096,
      ACTION_SCROLL_BACKWARD = 8192, ACTION_COPY = 16384, ACTION_PASTE = 32768, ACTION_CUT = 65536, ACTION_SET_SELECTION = 131072,
      ACTION_EXPAND = 262144, ACTION_COLLAPSE = 524288, ACTION_DISMISS = 1048576, ACTION_SET_TEXT = 2097152;
  private CharSequence className, text, contentDescription;
  private boolean clickable, checkable, checked, enabled = true, focusable, focused, scrollable, selected, longClickable;
  private int actions;
  public static AccessibilityNodeInfo obtain() { return new AccessibilityNodeInfo(); }
  public static AccessibilityNodeInfo obtain(android.view.View source) { return new AccessibilityNodeInfo(); }
  public static AccessibilityNodeInfo obtain(AccessibilityNodeInfo info) { AccessibilityNodeInfo n = new AccessibilityNodeInfo(); n.className = info.className; n.text = info.text; return n; }
  public void recycle() {}
  public void addAction(int action) { actions |= action; }
  public int getActions() { return actions; }
  public void setClassName(CharSequence c) { className = c; }
  public CharSequence getClassName() { return className; }
  public void setText(CharSequence t) { text = t; }
  public CharSequence getText() { return text; }
  public void setContentDescription(CharSequence d) { contentDescription = d; }
  public CharSequence getContentDescription() { return contentDescription; }
  public void setClickable(boolean b) { clickable = b; }
  public boolean isClickable() { return clickable; }
  public void setLongClickable(boolean b) { longClickable = b; }
  public boolean isLongClickable() { return longClickable; }
  public void setCheckable(boolean b) { checkable = b; }
  public boolean isCheckable() { return checkable; }
  public void setChecked(boolean b) { checked = b; }
  public boolean isChecked() { return checked; }
  public void setEnabled(boolean b) { enabled = b; }
  public boolean isEnabled() { return enabled; }
  public void setFocusable(boolean b) { focusable = b; }
  public boolean isFocusable() { return focusable; }
  public void setFocused(boolean b) { focused = b; }
  public boolean isFocused() { return focused; }
  public void setScrollable(boolean b) { scrollable = b; }
  public boolean isScrollable() { return scrollable; }
  public void setSelected(boolean b) { selected = b; }
  public boolean isSelected() { return selected; }
  public void setSource(android.view.View v) {}
  public void setParent(android.view.View v) {}
  public void addChild(android.view.View v) {}
  public void setBoundsInParent(android.graphics.Rect r) {}
  public void setBoundsInScreen(android.graphics.Rect r) {}
  public void getBoundsInParent(android.graphics.Rect r) {}
  public void getBoundsInScreen(android.graphics.Rect r) {}
  public void setVisibleToUser(boolean b) {}
  public void setPassword(boolean b) {}
  public void setPackageName(CharSequence p) {}
}
