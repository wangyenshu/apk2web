// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.content.pm;

public class CrossProfileApps {
  public static final java.lang.String ACTION_CAN_INTERACT_ACROSS_PROFILES_CHANGED = "android.content.pm.action.CAN_INTERACT_ACROSS_PROFILES_CHANGED";
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public CrossProfileApps(a2w.Hidden hidden) { }
  @a2w.NotImplemented public boolean canInteractAcrossProfiles() { a2w.Stubs.hit("android.content.pm.CrossProfileApps.canInteractAcrossProfiles"); return false; }
  @a2w.NotImplemented public boolean canRequestInteractAcrossProfiles() { a2w.Stubs.hit("android.content.pm.CrossProfileApps.canRequestInteractAcrossProfiles"); return false; }
  @a2w.NotImplemented public android.content.Intent createRequestInteractAcrossProfilesIntent() { a2w.Stubs.hit("android.content.pm.CrossProfileApps.createRequestInteractAcrossProfilesIntent"); return null; }
  @a2w.NotImplemented public android.graphics.drawable.Drawable getProfileSwitchingIconDrawable(android.os.UserHandle userHandle) { a2w.Stubs.hit("android.content.pm.CrossProfileApps.getProfileSwitchingIconDrawable"); return null; }
  @a2w.NotImplemented public java.lang.CharSequence getProfileSwitchingLabel(android.os.UserHandle userHandle) { a2w.Stubs.hit("android.content.pm.CrossProfileApps.getProfileSwitchingLabel"); return null; }
  @a2w.NotImplemented public java.util.List<android.os.UserHandle> getTargetUserProfiles() { a2w.Stubs.hit("android.content.pm.CrossProfileApps.getTargetUserProfiles"); return null; }
  @a2w.NotImplemented public boolean isManagedProfile(android.os.UserHandle userHandle) { a2w.Stubs.hit("android.content.pm.CrossProfileApps.isManagedProfile"); return false; }
  @a2w.NotImplemented public boolean isProfile(android.os.UserHandle userHandle) { a2w.Stubs.hit("android.content.pm.CrossProfileApps.isProfile"); return false; }
  @a2w.NotImplemented public void startActivity(android.content.Intent intent, android.os.UserHandle targetUser, android.app.Activity callingActivity) { a2w.Stubs.hit("android.content.pm.CrossProfileApps.startActivity"); }
  @a2w.NotImplemented public void startActivity(android.content.Intent intent, android.os.UserHandle targetUser, android.app.Activity callingActivity, android.os.Bundle options) { a2w.Stubs.hit("android.content.pm.CrossProfileApps.startActivity"); }
  @a2w.NotImplemented public void startMainActivity(android.content.ComponentName component, android.os.UserHandle targetUser) { a2w.Stubs.hit("android.content.pm.CrossProfileApps.startMainActivity"); }
  @a2w.NotImplemented public void startMainActivity(android.content.ComponentName component, android.os.UserHandle targetUser, android.app.Activity callingActivity, android.os.Bundle options) { a2w.Stubs.hit("android.content.pm.CrossProfileApps.startMainActivity"); }
}
