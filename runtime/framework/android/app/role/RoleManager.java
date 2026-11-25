// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.app.role;

public final class RoleManager {
  public static final java.lang.String ROLE_ASSISTANT = "android.app.role.ASSISTANT";
  public static final java.lang.String ROLE_BROWSER = "android.app.role.BROWSER";
  public static final java.lang.String ROLE_CALL_REDIRECTION = "android.app.role.CALL_REDIRECTION";
  public static final java.lang.String ROLE_CALL_SCREENING = "android.app.role.CALL_SCREENING";
  public static final java.lang.String ROLE_DIALER = "android.app.role.DIALER";
  public static final java.lang.String ROLE_EMERGENCY = "android.app.role.EMERGENCY";
  public static final java.lang.String ROLE_HOME = "android.app.role.HOME";
  public static final java.lang.String ROLE_NOTES = "android.app.role.NOTES";
  public static final java.lang.String ROLE_SMS = "android.app.role.SMS";
  public static final java.lang.String ROLE_WALLET = "android.app.role.WALLET";
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public RoleManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public android.content.Intent createRequestRoleIntent(java.lang.String roleName) { a2w.Stubs.hit("android.app.role.RoleManager.createRequestRoleIntent"); return null; }
  @a2w.NotImplemented public boolean isRoleAvailable(java.lang.String roleName) { a2w.Stubs.hit("android.app.role.RoleManager.isRoleAvailable"); return false; }
  @a2w.NotImplemented public boolean isRoleHeld(java.lang.String roleName) { a2w.Stubs.hit("android.app.role.RoleManager.isRoleHeld"); return false; }
}
