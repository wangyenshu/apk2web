package android.view;

import android.graphics.Rect;

public interface ViewParent {
  void requestLayout();
  boolean isLayoutRequested();
  void requestTransparentRegion(View child);
  void invalidateChild(View child, Rect r);
  ViewParent invalidateChildInParent(int[] location, Rect r);
  ViewParent getParent();
  void requestChildFocus(View child, View focused);
  void recomputeViewAttributes(View child);
  void clearChildFocus(View child);
  boolean getChildVisibleRect(View child, Rect r, android.graphics.Point offset);
  View focusSearch(View v, int direction);
  void bringChildToFront(View child);
  void focusableViewAvailable(View v);
  boolean showContextMenuForChild(View originalView);
  default boolean showContextMenuForChild(View originalView, float x, float y) { return showContextMenuForChild(originalView); }
  default ActionMode startActionModeForChild(View originalView, ActionMode.Callback callback) { return startActionModeForChild(originalView, callback, ActionMode.TYPE_PRIMARY); }
  default ActionMode startActionModeForChild(View originalView, ActionMode.Callback callback, int type) { return null; }
  default void createContextMenu(ContextMenu menu) {}
  default ViewParent getParentForAccessibility() { return null; }
  default View keyboardNavigationClusterSearch(View currentCluster, int direction) { return null; }
  default void requestFitSystemWindows() {}
  default boolean requestSendAccessibilityEvent(View child, android.view.accessibility.AccessibilityEvent event) { return false; }
  default android.window.OnBackInvokedDispatcher findOnBackInvokedDispatcherForChild(View child, View requester) { return null; }
  void childDrawableStateChanged(View child);
  void requestDisallowInterceptTouchEvent(boolean disallow);
  boolean requestChildRectangleOnScreen(View child, Rect rectangle, boolean immediate);
  // nested scrolling (API 21)
  default boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) { return false; }
  default void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {}
  default void onStopNestedScroll(View target) {}
  default void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {}
  default void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {}
  default boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) { return false; }
  default boolean onNestedPreFling(View target, float velocityX, float velocityY) { return false; }
  default boolean onNestedPrePerformAccessibilityAction(View target, int action, android.os.Bundle arguments) { return false; }
  default void notifySubtreeAccessibilityStateChanged(View child, View source, int changeType) {}
  default boolean canResolveLayoutDirection() { return true; }
  default boolean isLayoutDirectionResolved() { return true; }
  default int getLayoutDirection() { return View.LAYOUT_DIRECTION_LTR; }
  default boolean canResolveTextDirection() { return true; }
  default boolean isTextDirectionResolved() { return true; }
  default int getTextDirection() { return 0; }
  default boolean canResolveTextAlignment() { return true; }
  default boolean isTextAlignmentResolved() { return true; }
  default int getTextAlignment() { return 0; }
  default void onDescendantInvalidated(View child, View target) {}
  default void childHasTransientStateChanged(View child, boolean hasTransientState) {}
  default void subtractObscuredTouchableRegion(android.graphics.Region touchableRegion, View view) {}
}
