// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.net;

public class VpnManager {
  public static final java.lang.String ACTION_VPN_MANAGER_EVENT = "android.net.action.VPN_MANAGER_EVENT";
  public static final java.lang.String CATEGORY_EVENT_ALWAYS_ON_STATE_CHANGED = "android.net.category.EVENT_ALWAYS_ON_STATE_CHANGED";
  public static final java.lang.String CATEGORY_EVENT_DEACTIVATED_BY_USER = "android.net.category.EVENT_DEACTIVATED_BY_USER";
  public static final java.lang.String CATEGORY_EVENT_IKE_ERROR = "android.net.category.EVENT_IKE_ERROR";
  public static final java.lang.String CATEGORY_EVENT_NETWORK_ERROR = "android.net.category.EVENT_NETWORK_ERROR";
  public static final int ERROR_CLASS_NOT_RECOVERABLE = 1;
  public static final int ERROR_CLASS_RECOVERABLE = 2;
  public static final int ERROR_CODE_NETWORK_IO = 3;
  public static final int ERROR_CODE_NETWORK_LOST = 2;
  public static final int ERROR_CODE_NETWORK_PROTOCOL_TIMEOUT = 1;
  public static final int ERROR_CODE_NETWORK_UNKNOWN_HOST = 0;
  public static final java.lang.String EXTRA_ERROR_CLASS = "android.net.extra.ERROR_CLASS";
  public static final java.lang.String EXTRA_ERROR_CODE = "android.net.extra.ERROR_CODE";
  public static final java.lang.String EXTRA_SESSION_KEY = "android.net.extra.SESSION_KEY";
  public static final java.lang.String EXTRA_TIMESTAMP_MILLIS = "android.net.extra.TIMESTAMP_MILLIS";
  public static final java.lang.String EXTRA_UNDERLYING_LINK_PROPERTIES = "android.net.extra.UNDERLYING_LINK_PROPERTIES";
  public static final java.lang.String EXTRA_UNDERLYING_NETWORK = "android.net.extra.UNDERLYING_NETWORK";
  public static final java.lang.String EXTRA_UNDERLYING_NETWORK_CAPABILITIES = "android.net.extra.UNDERLYING_NETWORK_CAPABILITIES";
  public static final java.lang.String EXTRA_VPN_PROFILE_STATE = "android.net.extra.VPN_PROFILE_STATE";
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public VpnManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public void deleteProvisionedVpnProfile() { a2w.Stubs.hit("android.net.VpnManager.deleteProvisionedVpnProfile"); }
  @a2w.NotImplemented public android.net.VpnProfileState getProvisionedVpnProfileState() { a2w.Stubs.hit("android.net.VpnManager.getProvisionedVpnProfileState"); return null; }
  @a2w.NotImplemented public android.content.Intent provisionVpnProfile(android.net.PlatformVpnProfile profile) { a2w.Stubs.hit("android.net.VpnManager.provisionVpnProfile"); return null; }
  @a2w.NotImplemented public void startProvisionedVpnProfile() { a2w.Stubs.hit("android.net.VpnManager.startProvisionedVpnProfile"); }
  @a2w.NotImplemented public java.lang.String startProvisionedVpnProfileSession() { a2w.Stubs.hit("android.net.VpnManager.startProvisionedVpnProfileSession"); return null; }
  @a2w.NotImplemented public void stopProvisionedVpnProfile() { a2w.Stubs.hit("android.net.VpnManager.stopProvisionedVpnProfile"); }
}
