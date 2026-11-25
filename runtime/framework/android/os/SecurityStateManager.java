// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.os;

public class SecurityStateManager {
  public static final java.lang.String KEY_KERNEL_VERSION = "kernel_version";
  public static final java.lang.String KEY_SYSTEM_SPL = "system_spl";
  public static final java.lang.String KEY_VENDOR_SPL = "vendor_spl";
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public SecurityStateManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public android.os.Bundle getGlobalSecurityState() { a2w.Stubs.hit("android.os.SecurityStateManager.getGlobalSecurityState"); return null; }
}
