package android.view.accessibility;

/** android.view.accessibility.AccessibilityEvent (no accessibility services in the page: events go nowhere) */
public final class AccessibilityEvent {
  public static final int TYPE_VIEW_CLICKED = 1, TYPE_VIEW_LONG_CLICKED = 2, TYPE_VIEW_SELECTED = 4, TYPE_VIEW_FOCUSED = 8, TYPE_VIEW_TEXT_CHANGED = 16,
      TYPE_WINDOW_STATE_CHANGED = 32, TYPE_NOTIFICATION_STATE_CHANGED = 64, TYPE_VIEW_HOVER_ENTER = 128, TYPE_VIEW_HOVER_EXIT = 256,
      TYPE_TOUCH_EXPLORATION_GESTURE_START = 512, TYPE_TOUCH_EXPLORATION_GESTURE_END = 1024, TYPE_WINDOW_CONTENT_CHANGED = 2048,
      TYPE_VIEW_SCROLLED = 4096, TYPE_VIEW_TEXT_SELECTION_CHANGED = 8192, TYPE_ANNOUNCEMENT = 16384, TYPE_VIEW_ACCESSIBILITY_FOCUSED = 32768,
      TYPES_ALL_MASK = -1;
  private int type;
  private CharSequence className, packageName, contentDescription;
  private final java.util.ArrayList<CharSequence> text = new java.util.ArrayList<>();
  private AccessibilityEvent() {}
  public static AccessibilityEvent obtain() { return new AccessibilityEvent(); }
  public static AccessibilityEvent obtain(int eventType) { AccessibilityEvent e = new AccessibilityEvent(); e.type = eventType; return e; }
  public static AccessibilityEvent obtain(AccessibilityEvent event) { AccessibilityEvent e = obtain(event.type); e.className = event.className; e.packageName = event.packageName; e.text.addAll(event.text); return e; }
  public void recycle() {}
  public int getEventType() { return type; }
  public void setEventType(int t) { type = t; }
  public CharSequence getClassName() { return className; }
  public void setClassName(CharSequence c) { className = c; }
  public CharSequence getPackageName() { return packageName; }
  public void setPackageName(CharSequence p) { packageName = p; }
  public java.util.List<CharSequence> getText() { return text; }
  public CharSequence getContentDescription() { return contentDescription; }
  public void setContentDescription(CharSequence d) { contentDescription = d; }
  public long getEventTime() { return android.os.SystemClock.uptimeMillis(); }
  public void setEventTime(long t) {}
  public void setEnabled(boolean b) {}
  public void setChecked(boolean b) {}
  public void setScrollable(boolean b) {}
  public void setItemCount(int c) {}
  public void setFromIndex(int i) {}
  public void setToIndex(int i) {}
  public void setScrollX(int x) {}
  public void setScrollY(int y) {}
  public void setMaxScrollX(int x) {}
  public void setMaxScrollY(int y) {}
  public void setSource(android.view.View v) {}
  public int getRecordCount() { return 0; }
  public static String eventTypeToString(int eventType) { return "TYPE_" + eventType; }
}
