package android.view;

import android.content.Context;

public abstract class ActionProvider {
  public interface VisibilityListener { void onActionProviderVisibilityChanged(boolean isVisible); }
  public ActionProvider(Context context) {}
  @Deprecated public abstract View onCreateActionView();
  public View onCreateActionView(MenuItem forItem) { return onCreateActionView(); }
  public boolean overridesItemVisibility() { return false; }
  public boolean isVisible() { return true; }
  public void refreshVisibility() {}
  public boolean onPerformDefaultAction() { return false; }
  public boolean hasSubMenu() { return false; }
  public void onPrepareSubMenu(SubMenu subMenu) {}
  public void setVisibilityListener(VisibilityListener listener) {}
}
