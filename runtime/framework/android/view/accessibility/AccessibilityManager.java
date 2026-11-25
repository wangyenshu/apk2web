package android.view.accessibility;

import java.util.ArrayList;
import java.util.List;

/** no accessibility services run on the page */
public final class AccessibilityManager {
  public interface AccessibilityStateChangeListener { void onAccessibilityStateChanged(boolean enabled); }
  public interface TouchExplorationStateChangeListener { void onTouchExplorationStateChanged(boolean enabled); }
  public boolean isEnabled() { return false; }
  public boolean isTouchExplorationEnabled() { return false; }
  public List<Object> getInstalledAccessibilityServiceList() { return new ArrayList<>(); }
  public List<Object> getEnabledAccessibilityServiceList(int feedbackTypes) { return new ArrayList<>(); }
  public boolean addAccessibilityStateChangeListener(AccessibilityStateChangeListener l) { return true; }
  public boolean removeAccessibilityStateChangeListener(AccessibilityStateChangeListener l) { return true; }
  public boolean addTouchExplorationStateChangeListener(TouchExplorationStateChangeListener l) { return true; }
  public boolean removeTouchExplorationStateChangeListener(TouchExplorationStateChangeListener l) { return true; }
  public void interrupt() {}
}
