// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.os;

public class UserManager {
  public static final java.lang.String ALLOW_PARENT_PROFILE_APP_LINKING = "allow_parent_profile_app_linking";
  public static final java.lang.String DISALLOW_ADD_MANAGED_PROFILE = "no_add_managed_profile";
  public static final java.lang.String DISALLOW_ADD_PRIVATE_PROFILE = "no_add_private_profile";
  public static final java.lang.String DISALLOW_ADD_USER = "no_add_user";
  public static final java.lang.String DISALLOW_ADD_WIFI_CONFIG = "no_add_wifi_config";
  public static final java.lang.String DISALLOW_ADJUST_VOLUME = "no_adjust_volume";
  public static final java.lang.String DISALLOW_AIRPLANE_MODE = "no_airplane_mode";
  public static final java.lang.String DISALLOW_AMBIENT_DISPLAY = "no_ambient_display";
  public static final java.lang.String DISALLOW_APPS_CONTROL = "no_control_apps";
  public static final java.lang.String DISALLOW_ASSIST_CONTENT = "no_assist_content";
  public static final java.lang.String DISALLOW_AUTOFILL = "no_autofill";
  public static final java.lang.String DISALLOW_BLUETOOTH = "no_bluetooth";
  public static final java.lang.String DISALLOW_BLUETOOTH_SHARING = "no_bluetooth_sharing";
  public static final java.lang.String DISALLOW_CAMERA_TOGGLE = "disallow_camera_toggle";
  public static final java.lang.String DISALLOW_CELLULAR_2G = "no_cellular_2g";
  public static final java.lang.String DISALLOW_CHANGE_WIFI_STATE = "no_change_wifi_state";
  public static final java.lang.String DISALLOW_CONFIG_BLUETOOTH = "no_config_bluetooth";
  public static final java.lang.String DISALLOW_CONFIG_BRIGHTNESS = "no_config_brightness";
  public static final java.lang.String DISALLOW_CONFIG_CELL_BROADCASTS = "no_config_cell_broadcasts";
  public static final java.lang.String DISALLOW_CONFIG_CREDENTIALS = "no_config_credentials";
  public static final java.lang.String DISALLOW_CONFIG_DATE_TIME = "no_config_date_time";
  public static final java.lang.String DISALLOW_CONFIG_DEFAULT_APPS = "disallow_config_default_apps";
  public static final java.lang.String DISALLOW_CONFIG_LOCALE = "no_config_locale";
  public static final java.lang.String DISALLOW_CONFIG_LOCATION = "no_config_location";
  public static final java.lang.String DISALLOW_CONFIG_MOBILE_NETWORKS = "no_config_mobile_networks";
  public static final java.lang.String DISALLOW_CONFIG_PRIVATE_DNS = "disallow_config_private_dns";
  public static final java.lang.String DISALLOW_CONFIG_SCREEN_TIMEOUT = "no_config_screen_timeout";
  public static final java.lang.String DISALLOW_CONFIG_TETHERING = "no_config_tethering";
  public static final java.lang.String DISALLOW_CONFIG_VPN = "no_config_vpn";
  public static final java.lang.String DISALLOW_CONFIG_WIFI = "no_config_wifi";
  public static final java.lang.String DISALLOW_CONTENT_CAPTURE = "no_content_capture";
  public static final java.lang.String DISALLOW_CONTENT_SUGGESTIONS = "no_content_suggestions";
  public static final java.lang.String DISALLOW_CREATE_WINDOWS = "no_create_windows";
  public static final java.lang.String DISALLOW_CROSS_PROFILE_COPY_PASTE = "no_cross_profile_copy_paste";
  public static final java.lang.String DISALLOW_DATA_ROAMING = "no_data_roaming";
  public static final java.lang.String DISALLOW_DEBUGGING_FEATURES = "no_debugging_features";
  public static final java.lang.String DISALLOW_FACTORY_RESET = "no_factory_reset";
  public static final java.lang.String DISALLOW_FUN = "no_fun";
  public static final java.lang.String DISALLOW_GRANT_ADMIN = "no_grant_admin";
  public static final java.lang.String DISALLOW_INSTALL_APPS = "no_install_apps";
  public static final java.lang.String DISALLOW_INSTALL_UNKNOWN_SOURCES = "no_install_unknown_sources";
  public static final java.lang.String DISALLOW_INSTALL_UNKNOWN_SOURCES_GLOBALLY = "no_install_unknown_sources_globally";
  public static final java.lang.String DISALLOW_MICROPHONE_TOGGLE = "disallow_microphone_toggle";
  public static final java.lang.String DISALLOW_MODIFY_ACCOUNTS = "no_modify_accounts";
  public static final java.lang.String DISALLOW_MOUNT_PHYSICAL_MEDIA = "no_physical_media";
  public static final java.lang.String DISALLOW_NEAR_FIELD_COMMUNICATION_RADIO = "no_near_field_communication_radio";
  public static final java.lang.String DISALLOW_NETWORK_RESET = "no_network_reset";
  public static final java.lang.String DISALLOW_OUTGOING_BEAM = "no_outgoing_beam";
  public static final java.lang.String DISALLOW_OUTGOING_CALLS = "no_outgoing_calls";
  public static final java.lang.String DISALLOW_PRINTING = "no_printing";
  public static final java.lang.String DISALLOW_REMOVE_MANAGED_PROFILE = "no_remove_managed_profile";
  public static final java.lang.String DISALLOW_REMOVE_USER = "no_remove_user";
  public static final java.lang.String DISALLOW_SAFE_BOOT = "no_safe_boot";
  public static final java.lang.String DISALLOW_SET_USER_ICON = "no_set_user_icon";
  public static final java.lang.String DISALLOW_SET_WALLPAPER = "no_set_wallpaper";
  public static final java.lang.String DISALLOW_SHARE_INTO_MANAGED_PROFILE = "no_sharing_into_profile";
  public static final java.lang.String DISALLOW_SHARE_LOCATION = "no_share_location";
  public static final java.lang.String DISALLOW_SHARING_ADMIN_CONFIGURED_WIFI = "no_sharing_admin_configured_wifi";
  public static final java.lang.String DISALLOW_SIM_GLOBALLY = "no_sim_globally";
  public static final java.lang.String DISALLOW_SMS = "no_sms";
  public static final java.lang.String DISALLOW_SYSTEM_ERROR_DIALOGS = "no_system_error_dialogs";
  public static final java.lang.String DISALLOW_ULTRA_WIDEBAND_RADIO = "no_ultra_wideband_radio";
  public static final java.lang.String DISALLOW_UNIFIED_PASSWORD = "no_unified_password";
  public static final java.lang.String DISALLOW_UNINSTALL_APPS = "no_uninstall_apps";
  public static final java.lang.String DISALLOW_UNMUTE_MICROPHONE = "no_unmute_microphone";
  public static final java.lang.String DISALLOW_USB_FILE_TRANSFER = "no_usb_file_transfer";
  public static final java.lang.String DISALLOW_USER_SWITCH = "no_user_switch";
  public static final java.lang.String DISALLOW_WIFI_DIRECT = "no_wifi_direct";
  public static final java.lang.String DISALLOW_WIFI_TETHERING = "no_wifi_tethering";
  public static final java.lang.String ENSURE_VERIFY_APPS = "ensure_verify_apps";
  public static final java.lang.String KEY_RESTRICTIONS_PENDING = "restrictions_pending";
  public static final int QUIET_MODE_DISABLE_ONLY_IF_CREDENTIAL_NOT_REQUIRED = 1;
  public static final int USER_CREATION_FAILED_NOT_PERMITTED = 1;
  public static final int USER_CREATION_FAILED_NO_MORE_USERS = 2;
  public static final int USER_OPERATION_ERROR_CURRENT_USER = 4;
  public static final int USER_OPERATION_ERROR_LOW_STORAGE = 5;
  public static final int USER_OPERATION_ERROR_MANAGED_PROFILE = 2;
  public static final int USER_OPERATION_ERROR_MAX_RUNNING_USERS = 3;
  public static final int USER_OPERATION_ERROR_MAX_USERS = 6;
  public static final int USER_OPERATION_ERROR_UNKNOWN = 1;
  public static final int USER_OPERATION_SUCCESS = 0;
  public static final java.lang.String USER_TYPE_PROFILE_CLONE = "android.os.usertype.profile.CLONE";
  public static final java.lang.String USER_TYPE_PROFILE_MANAGED = "android.os.usertype.profile.MANAGED";
  public static final java.lang.String USER_TYPE_PROFILE_PRIVATE = "android.os.usertype.profile.PRIVATE";
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public UserManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public static android.content.Intent createUserCreationIntent(java.lang.String userName, java.lang.String accountName, java.lang.String accountType, android.os.PersistableBundle accountOptions) { a2w.Stubs.hit("android.os.UserManager.createUserCreationIntent"); return null; }
  @a2w.NotImplemented public android.os.Bundle getApplicationRestrictions(java.lang.String packageName) { a2w.Stubs.hit("android.os.UserManager.getApplicationRestrictions"); return null; }
  @a2w.NotImplemented public long getSerialNumberForUser(android.os.UserHandle user) { a2w.Stubs.hit("android.os.UserManager.getSerialNumberForUser"); return 0L; }
  @a2w.NotImplemented public int getUserCount() { a2w.Stubs.hit("android.os.UserManager.getUserCount"); return 0; }
  @a2w.NotImplemented public long getUserCreationTime(android.os.UserHandle userHandle) { a2w.Stubs.hit("android.os.UserManager.getUserCreationTime"); return 0L; }
  @a2w.NotImplemented public android.os.UserHandle getUserForSerialNumber(long serialNumber) { a2w.Stubs.hit("android.os.UserManager.getUserForSerialNumber"); return null; }
  @a2w.NotImplemented public java.lang.String getUserName() { a2w.Stubs.hit("android.os.UserManager.getUserName"); return null; }
  @a2w.NotImplemented public java.util.List<android.os.UserHandle> getUserProfiles() { a2w.Stubs.hit("android.os.UserManager.getUserProfiles"); return null; }
  @a2w.NotImplemented public android.os.Bundle getUserRestrictions() { a2w.Stubs.hit("android.os.UserManager.getUserRestrictions"); return null; }
  @a2w.NotImplemented public android.os.Bundle getUserRestrictions(android.os.UserHandle userHandle) { a2w.Stubs.hit("android.os.UserManager.getUserRestrictions"); return null; }
  @a2w.NotImplemented public boolean hasUserRestriction(java.lang.String restrictionKey) { a2w.Stubs.hit("android.os.UserManager.hasUserRestriction"); return false; }
  @a2w.NotImplemented public boolean isAdminUser() { a2w.Stubs.hit("android.os.UserManager.isAdminUser"); return false; }
  @a2w.NotImplemented public boolean isDemoUser() { a2w.Stubs.hit("android.os.UserManager.isDemoUser"); return false; }
  @a2w.NotImplemented public static boolean isHeadlessSystemUserMode() { a2w.Stubs.hit("android.os.UserManager.isHeadlessSystemUserMode"); return false; }
  @a2w.NotImplemented public boolean isManagedProfile() { a2w.Stubs.hit("android.os.UserManager.isManagedProfile"); return false; }
  @a2w.NotImplemented public boolean isProfile() { a2w.Stubs.hit("android.os.UserManager.isProfile"); return false; }
  @a2w.NotImplemented public boolean isQuietModeEnabled(android.os.UserHandle userHandle) { a2w.Stubs.hit("android.os.UserManager.isQuietModeEnabled"); return false; }
  @a2w.NotImplemented public boolean isSystemUser() { a2w.Stubs.hit("android.os.UserManager.isSystemUser"); return false; }
  @a2w.NotImplemented public boolean isUserAGoat() { a2w.Stubs.hit("android.os.UserManager.isUserAGoat"); return false; }
  @a2w.NotImplemented public boolean isUserForeground() { a2w.Stubs.hit("android.os.UserManager.isUserForeground"); return false; }
  @a2w.NotImplemented public boolean isUserRunning(android.os.UserHandle user) { a2w.Stubs.hit("android.os.UserManager.isUserRunning"); return false; }
  @a2w.NotImplemented public boolean isUserRunningOrStopping(android.os.UserHandle user) { a2w.Stubs.hit("android.os.UserManager.isUserRunningOrStopping"); return false; }
  @a2w.NotImplemented public boolean isUserUnlocked() { a2w.Stubs.hit("android.os.UserManager.isUserUnlocked"); return false; }
  @a2w.NotImplemented public boolean isUserUnlocked(android.os.UserHandle user) { a2w.Stubs.hit("android.os.UserManager.isUserUnlocked"); return false; }
  @a2w.NotImplemented public boolean requestQuietModeEnabled(boolean enableQuietMode, android.os.UserHandle userHandle) { a2w.Stubs.hit("android.os.UserManager.requestQuietModeEnabled"); return false; }
  @a2w.NotImplemented public boolean requestQuietModeEnabled(boolean enableQuietMode, android.os.UserHandle userHandle, int flags) { a2w.Stubs.hit("android.os.UserManager.requestQuietModeEnabled"); return false; }
  @a2w.NotImplemented public boolean setRestrictionsChallenge(java.lang.String newPin) { a2w.Stubs.hit("android.os.UserManager.setRestrictionsChallenge"); return false; }
  @a2w.NotImplemented public void setUserRestriction(java.lang.String key, boolean value) { a2w.Stubs.hit("android.os.UserManager.setUserRestriction"); }
  @a2w.NotImplemented public void setUserRestrictions(android.os.Bundle restrictions) { a2w.Stubs.hit("android.os.UserManager.setUserRestrictions"); }
  @a2w.NotImplemented public void setUserRestrictions(android.os.Bundle restrictions, android.os.UserHandle userHandle) { a2w.Stubs.hit("android.os.UserManager.setUserRestrictions"); }
  @a2w.NotImplemented public static boolean supportsMultipleUsers() { a2w.Stubs.hit("android.os.UserManager.supportsMultipleUsers"); return false; }

    public static class UserOperationException extends java.lang.RuntimeException {
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public UserOperationException(a2w.Hidden hidden) { super(); }
    @a2w.NotImplemented public int getUserOperationResult() { a2w.Stubs.hit("android.os.UserManager.UserOperationException.getUserOperationResult"); return 0; }
  }
}
