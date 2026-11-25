package android.view.accessibility;

/** Supplies the accessibility tree of a view's virtual children (the page has no accessibility service asking). */
public abstract class AccessibilityNodeProvider {
  public static final int HOST_VIEW_ID = -1;
  public AccessibilityNodeInfo createAccessibilityNodeInfo(int virtualViewId) { return null; }
  public boolean performAction(int virtualViewId, int action, android.os.Bundle arguments) { return false; }
  public java.util.List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String text, int virtualViewId) { return null; }
  public AccessibilityNodeInfo findFocus(int focus) { return null; }
  public void addExtraDataToAccessibilityNodeInfo(int virtualViewId, AccessibilityNodeInfo info, String extraDataKey, android.os.Bundle arguments) {}
}
