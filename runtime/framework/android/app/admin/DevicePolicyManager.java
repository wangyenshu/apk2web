// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.app.admin;

public class DevicePolicyManager {
  public static final java.lang.String ACTION_ADD_DEVICE_ADMIN = "android.app.action.ADD_DEVICE_ADMIN";
  public static final java.lang.String ACTION_ADMIN_POLICY_COMPLIANCE = "android.app.action.ADMIN_POLICY_COMPLIANCE";
  public static final java.lang.String ACTION_APPLICATION_DELEGATION_SCOPES_CHANGED = "android.app.action.APPLICATION_DELEGATION_SCOPES_CHANGED";
  public static final java.lang.String ACTION_CHECK_POLICY_COMPLIANCE = "android.app.action.CHECK_POLICY_COMPLIANCE";
  public static final java.lang.String ACTION_DEVICE_ADMIN_SERVICE = "android.app.action.DEVICE_ADMIN_SERVICE";
  public static final java.lang.String ACTION_DEVICE_FINANCING_STATE_CHANGED = "android.app.admin.action.DEVICE_FINANCING_STATE_CHANGED";
  public static final java.lang.String ACTION_DEVICE_OWNER_CHANGED = "android.app.action.DEVICE_OWNER_CHANGED";
  public static final java.lang.String ACTION_DEVICE_POLICY_RESOURCE_UPDATED = "android.app.action.DEVICE_POLICY_RESOURCE_UPDATED";
  public static final java.lang.String ACTION_GET_PROVISIONING_MODE = "android.app.action.GET_PROVISIONING_MODE";
  public static final java.lang.String ACTION_MANAGED_PROFILE_PROVISIONED = "android.app.action.MANAGED_PROFILE_PROVISIONED";
  public static final java.lang.String ACTION_PROFILE_OWNER_CHANGED = "android.app.action.PROFILE_OWNER_CHANGED";
  public static final java.lang.String ACTION_PROVISIONING_SUCCESSFUL = "android.app.action.PROVISIONING_SUCCESSFUL";
  public static final java.lang.String ACTION_PROVISION_MANAGED_DEVICE = "android.app.action.PROVISION_MANAGED_DEVICE";
  public static final java.lang.String ACTION_PROVISION_MANAGED_PROFILE = "android.app.action.PROVISION_MANAGED_PROFILE";
  public static final java.lang.String ACTION_SET_NEW_PARENT_PROFILE_PASSWORD = "android.app.action.SET_NEW_PARENT_PROFILE_PASSWORD";
  public static final java.lang.String ACTION_SET_NEW_PASSWORD = "android.app.action.SET_NEW_PASSWORD";
  public static final java.lang.String ACTION_START_ENCRYPTION = "android.app.action.START_ENCRYPTION";
  public static final java.lang.String ACTION_SYSTEM_UPDATE_POLICY_CHANGED = "android.app.action.SYSTEM_UPDATE_POLICY_CHANGED";
  public static final int CONTENT_PROTECTION_DISABLED = 1;
  public static final int CONTENT_PROTECTION_ENABLED = 2;
  public static final int CONTENT_PROTECTION_NOT_CONTROLLED_BY_POLICY = 0;
  public static final java.lang.String DELEGATION_APP_RESTRICTIONS = "delegation-app-restrictions";
  public static final java.lang.String DELEGATION_BLOCK_UNINSTALL = "delegation-block-uninstall";
  public static final java.lang.String DELEGATION_CERT_INSTALL = "delegation-cert-install";
  public static final java.lang.String DELEGATION_CERT_SELECTION = "delegation-cert-selection";
  public static final java.lang.String DELEGATION_ENABLE_SYSTEM_APP = "delegation-enable-system-app";
  public static final java.lang.String DELEGATION_INSTALL_EXISTING_PACKAGE = "delegation-install-existing-package";
  public static final java.lang.String DELEGATION_KEEP_UNINSTALLED_PACKAGES = "delegation-keep-uninstalled-packages";
  public static final java.lang.String DELEGATION_NETWORK_LOGGING = "delegation-network-logging";
  public static final java.lang.String DELEGATION_PACKAGE_ACCESS = "delegation-package-access";
  public static final java.lang.String DELEGATION_PERMISSION_GRANT = "delegation-permission-grant";
  public static final java.lang.String DELEGATION_SECURITY_LOGGING = "delegation-security-logging";
  public static final int ENCRYPTION_STATUS_ACTIVATING = 2;
  public static final int ENCRYPTION_STATUS_ACTIVE = 3;
  public static final int ENCRYPTION_STATUS_ACTIVE_DEFAULT_KEY = 4;
  public static final int ENCRYPTION_STATUS_ACTIVE_PER_USER = 5;
  public static final int ENCRYPTION_STATUS_INACTIVE = 1;
  public static final int ENCRYPTION_STATUS_UNSUPPORTED = 0;
  public static final java.lang.String EXTRA_ADD_EXPLANATION = "android.app.extra.ADD_EXPLANATION";
  public static final java.lang.String EXTRA_DELEGATION_SCOPES = "android.app.extra.DELEGATION_SCOPES";
  public static final java.lang.String EXTRA_DEVICE_ADMIN = "android.app.extra.DEVICE_ADMIN";
  public static final java.lang.String EXTRA_DEVICE_PASSWORD_REQUIREMENT_ONLY = "android.app.extra.DEVICE_PASSWORD_REQUIREMENT_ONLY";
  public static final java.lang.String EXTRA_PASSWORD_COMPLEXITY = "android.app.extra.PASSWORD_COMPLEXITY";
  public static final java.lang.String EXTRA_PROVISIONING_ACCOUNT_TO_MIGRATE = "android.app.extra.PROVISIONING_ACCOUNT_TO_MIGRATE";
  public static final java.lang.String EXTRA_PROVISIONING_ADMIN_EXTRAS_BUNDLE = "android.app.extra.PROVISIONING_ADMIN_EXTRAS_BUNDLE";
  public static final java.lang.String EXTRA_PROVISIONING_ALLOWED_PROVISIONING_MODES = "android.app.extra.PROVISIONING_ALLOWED_PROVISIONING_MODES";
  public static final java.lang.String EXTRA_PROVISIONING_ALLOW_OFFLINE = "android.app.extra.PROVISIONING_ALLOW_OFFLINE";
  public static final java.lang.String EXTRA_PROVISIONING_DEVICE_ADMIN_COMPONENT_NAME = "android.app.extra.PROVISIONING_DEVICE_ADMIN_COMPONENT_NAME";
  public static final java.lang.String EXTRA_PROVISIONING_DEVICE_ADMIN_MINIMUM_VERSION_CODE = "android.app.extra.PROVISIONING_DEVICE_ADMIN_MINIMUM_VERSION_CODE";
  public static final java.lang.String EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_CHECKSUM = "android.app.extra.PROVISIONING_DEVICE_ADMIN_PACKAGE_CHECKSUM";
  public static final java.lang.String EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_DOWNLOAD_COOKIE_HEADER = "android.app.extra.PROVISIONING_DEVICE_ADMIN_PACKAGE_DOWNLOAD_COOKIE_HEADER";
  public static final java.lang.String EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_DOWNLOAD_LOCATION = "android.app.extra.PROVISIONING_DEVICE_ADMIN_PACKAGE_DOWNLOAD_LOCATION";
  public static final java.lang.String EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_NAME = "android.app.extra.PROVISIONING_DEVICE_ADMIN_PACKAGE_NAME";
  public static final java.lang.String EXTRA_PROVISIONING_DEVICE_ADMIN_SIGNATURE_CHECKSUM = "android.app.extra.PROVISIONING_DEVICE_ADMIN_SIGNATURE_CHECKSUM";
  public static final java.lang.String EXTRA_PROVISIONING_DISCLAIMERS = "android.app.extra.PROVISIONING_DISCLAIMERS";
  public static final java.lang.String EXTRA_PROVISIONING_DISCLAIMER_CONTENT = "android.app.extra.PROVISIONING_DISCLAIMER_CONTENT";
  public static final java.lang.String EXTRA_PROVISIONING_DISCLAIMER_HEADER = "android.app.extra.PROVISIONING_DISCLAIMER_HEADER";
  public static final java.lang.String EXTRA_PROVISIONING_EMAIL_ADDRESS = "android.app.extra.PROVISIONING_EMAIL_ADDRESS";
  public static final java.lang.String EXTRA_PROVISIONING_IMEI = "android.app.extra.PROVISIONING_IMEI";
  public static final java.lang.String EXTRA_PROVISIONING_KEEP_ACCOUNT_ON_MIGRATION = "android.app.extra.PROVISIONING_KEEP_ACCOUNT_ON_MIGRATION";
  public static final java.lang.String EXTRA_PROVISIONING_KEEP_SCREEN_ON = "android.app.extra.PROVISIONING_KEEP_SCREEN_ON";
  public static final java.lang.String EXTRA_PROVISIONING_LEAVE_ALL_SYSTEM_APPS_ENABLED = "android.app.extra.PROVISIONING_LEAVE_ALL_SYSTEM_APPS_ENABLED";
  public static final java.lang.String EXTRA_PROVISIONING_LOCALE = "android.app.extra.PROVISIONING_LOCALE";
  public static final java.lang.String EXTRA_PROVISIONING_LOCAL_TIME = "android.app.extra.PROVISIONING_LOCAL_TIME";
  public static final java.lang.String EXTRA_PROVISIONING_LOGO_URI = "android.app.extra.PROVISIONING_LOGO_URI";
  public static final java.lang.String EXTRA_PROVISIONING_MAIN_COLOR = "android.app.extra.PROVISIONING_MAIN_COLOR";
  public static final java.lang.String EXTRA_PROVISIONING_MODE = "android.app.extra.PROVISIONING_MODE";
  public static final java.lang.String EXTRA_PROVISIONING_SENSORS_PERMISSION_GRANT_OPT_OUT = "android.app.extra.PROVISIONING_SENSORS_PERMISSION_GRANT_OPT_OUT";
  public static final java.lang.String EXTRA_PROVISIONING_SERIAL_NUMBER = "android.app.extra.PROVISIONING_SERIAL_NUMBER";
  public static final java.lang.String EXTRA_PROVISIONING_SHOULD_LAUNCH_RESULT_INTENT = "android.app.extra.PROVISIONING_SHOULD_LAUNCH_RESULT_INTENT";
  public static final java.lang.String EXTRA_PROVISIONING_SKIP_EDUCATION_SCREENS = "android.app.extra.PROVISIONING_SKIP_EDUCATION_SCREENS";
  public static final java.lang.String EXTRA_PROVISIONING_SKIP_ENCRYPTION = "android.app.extra.PROVISIONING_SKIP_ENCRYPTION";
  public static final java.lang.String EXTRA_PROVISIONING_SKIP_USER_CONSENT = "android.app.extra.PROVISIONING_SKIP_USER_CONSENT";
  public static final java.lang.String EXTRA_PROVISIONING_TIME_ZONE = "android.app.extra.PROVISIONING_TIME_ZONE";
  public static final java.lang.String EXTRA_PROVISIONING_USE_MOBILE_DATA = "android.app.extra.PROVISIONING_USE_MOBILE_DATA";
  public static final java.lang.String EXTRA_PROVISIONING_WIFI_ANONYMOUS_IDENTITY = "android.app.extra.PROVISIONING_WIFI_ANONYMOUS_IDENTITY";
  public static final java.lang.String EXTRA_PROVISIONING_WIFI_CA_CERTIFICATE = "android.app.extra.PROVISIONING_WIFI_CA_CERTIFICATE";
  public static final java.lang.String EXTRA_PROVISIONING_WIFI_DOMAIN = "android.app.extra.PROVISIONING_WIFI_DOMAIN";
  public static final java.lang.String EXTRA_PROVISIONING_WIFI_EAP_METHOD = "android.app.extra.PROVISIONING_WIFI_EAP_METHOD";
  public static final java.lang.String EXTRA_PROVISIONING_WIFI_HIDDEN = "android.app.extra.PROVISIONING_WIFI_HIDDEN";
  public static final java.lang.String EXTRA_PROVISIONING_WIFI_IDENTITY = "android.app.extra.PROVISIONING_WIFI_IDENTITY";
  public static final java.lang.String EXTRA_PROVISIONING_WIFI_PAC_URL = "android.app.extra.PROVISIONING_WIFI_PAC_URL";
  public static final java.lang.String EXTRA_PROVISIONING_WIFI_PASSWORD = "android.app.extra.PROVISIONING_WIFI_PASSWORD";
  public static final java.lang.String EXTRA_PROVISIONING_WIFI_PHASE2_AUTH = "android.app.extra.PROVISIONING_WIFI_PHASE2_AUTH";
  public static final java.lang.String EXTRA_PROVISIONING_WIFI_PROXY_BYPASS = "android.app.extra.PROVISIONING_WIFI_PROXY_BYPASS";
  public static final java.lang.String EXTRA_PROVISIONING_WIFI_PROXY_HOST = "android.app.extra.PROVISIONING_WIFI_PROXY_HOST";
  public static final java.lang.String EXTRA_PROVISIONING_WIFI_PROXY_PORT = "android.app.extra.PROVISIONING_WIFI_PROXY_PORT";
  public static final java.lang.String EXTRA_PROVISIONING_WIFI_SECURITY_TYPE = "android.app.extra.PROVISIONING_WIFI_SECURITY_TYPE";
  public static final java.lang.String EXTRA_PROVISIONING_WIFI_SSID = "android.app.extra.PROVISIONING_WIFI_SSID";
  public static final java.lang.String EXTRA_PROVISIONING_WIFI_USER_CERTIFICATE = "android.app.extra.PROVISIONING_WIFI_USER_CERTIFICATE";
  public static final java.lang.String EXTRA_RESOURCE_IDS = "android.app.extra.RESOURCE_IDS";
  public static final java.lang.String EXTRA_RESOURCE_TYPE = "android.app.extra.RESOURCE_TYPE";
  public static final int EXTRA_RESOURCE_TYPE_DRAWABLE = 1;
  public static final int EXTRA_RESOURCE_TYPE_STRING = 2;
  public static final java.lang.String EXTRA_RESULT_LAUNCH_INTENT = "android.app.extra.RESULT_LAUNCH_INTENT";
  public static final int FLAG_EVICT_CREDENTIAL_ENCRYPTION_KEY = 1;
  public static final int FLAG_MANAGED_CAN_ACCESS_PARENT = 2;
  public static final int FLAG_PARENT_CAN_ACCESS_MANAGED = 1;
  public static final int ID_TYPE_BASE_INFO = 1;
  public static final int ID_TYPE_IMEI = 4;
  public static final int ID_TYPE_INDIVIDUAL_ATTESTATION = 16;
  public static final int ID_TYPE_MEID = 8;
  public static final int ID_TYPE_SERIAL = 2;
  public static final int INSTALLKEY_REQUEST_CREDENTIALS_ACCESS = 1;
  public static final int INSTALLKEY_SET_USER_SELECTABLE = 2;
  public static final int KEYGUARD_DISABLE_BIOMETRICS = 416;
  public static final int KEYGUARD_DISABLE_FACE = 128;
  public static final int KEYGUARD_DISABLE_FEATURES_ALL = 2147483647;
  public static final int KEYGUARD_DISABLE_FEATURES_NONE = 0;
  public static final int KEYGUARD_DISABLE_FINGERPRINT = 32;
  public static final int KEYGUARD_DISABLE_IRIS = 256;
  public static final int KEYGUARD_DISABLE_REMOTE_INPUT = 64;
  public static final int KEYGUARD_DISABLE_SECURE_CAMERA = 2;
  public static final int KEYGUARD_DISABLE_SECURE_NOTIFICATIONS = 4;
  public static final int KEYGUARD_DISABLE_SHORTCUTS_ALL = 512;
  public static final int KEYGUARD_DISABLE_TRUST_AGENTS = 16;
  public static final int KEYGUARD_DISABLE_UNREDACTED_NOTIFICATIONS = 8;
  public static final int KEYGUARD_DISABLE_WIDGETS_ALL = 1;
  public static final int LEAVE_ALL_SYSTEM_APPS_ENABLED = 16;
  public static final int LOCK_TASK_FEATURE_BLOCK_ACTIVITY_START_IN_TASK = 64;
  public static final int LOCK_TASK_FEATURE_GLOBAL_ACTIONS = 16;
  public static final int LOCK_TASK_FEATURE_HOME = 4;
  public static final int LOCK_TASK_FEATURE_KEYGUARD = 32;
  public static final int LOCK_TASK_FEATURE_NONE = 0;
  public static final int LOCK_TASK_FEATURE_NOTIFICATIONS = 2;
  public static final int LOCK_TASK_FEATURE_OVERVIEW = 8;
  public static final int LOCK_TASK_FEATURE_SYSTEM_INFO = 1;
  public static final int MAKE_USER_EPHEMERAL = 2;
  public static final java.lang.String MIME_TYPE_PROVISIONING_NFC = "application/com.android.managedprovisioning";
  public static final int MTE_DISABLED = 2;
  public static final int MTE_ENABLED = 1;
  public static final int MTE_NOT_CONTROLLED_BY_POLICY = 0;
  public static final int NEARBY_STREAMING_DISABLED = 1;
  public static final int NEARBY_STREAMING_ENABLED = 2;
  public static final int NEARBY_STREAMING_NOT_CONTROLLED_BY_POLICY = 0;
  public static final int NEARBY_STREAMING_SAME_MANAGED_ACCOUNT_ONLY = 3;
  public static final int OPERATION_SAFETY_REASON_DRIVING_DISTRACTION = 1;
  public static final int PASSWORD_COMPLEXITY_HIGH = 327680;
  public static final int PASSWORD_COMPLEXITY_LOW = 65536;
  public static final int PASSWORD_COMPLEXITY_MEDIUM = 196608;
  public static final int PASSWORD_COMPLEXITY_NONE = 0;
  public static final int PASSWORD_QUALITY_ALPHABETIC = 262144;
  public static final int PASSWORD_QUALITY_ALPHANUMERIC = 327680;
  public static final int PASSWORD_QUALITY_BIOMETRIC_WEAK = 32768;
  public static final int PASSWORD_QUALITY_COMPLEX = 393216;
  public static final int PASSWORD_QUALITY_NUMERIC = 131072;
  public static final int PASSWORD_QUALITY_NUMERIC_COMPLEX = 196608;
  public static final int PASSWORD_QUALITY_SOMETHING = 65536;
  public static final int PASSWORD_QUALITY_UNSPECIFIED = 0;
  public static final int PERMISSION_GRANT_STATE_DEFAULT = 0;
  public static final int PERMISSION_GRANT_STATE_DENIED = 2;
  public static final int PERMISSION_GRANT_STATE_GRANTED = 1;
  public static final int PERMISSION_POLICY_AUTO_DENY = 2;
  public static final int PERMISSION_POLICY_AUTO_GRANT = 1;
  public static final int PERMISSION_POLICY_PROMPT = 0;
  public static final int PERSONAL_APPS_NOT_SUSPENDED = 0;
  public static final int PERSONAL_APPS_SUSPENDED_EXPLICITLY = 1;
  public static final int PERSONAL_APPS_SUSPENDED_PROFILE_TIMEOUT = 2;
  public static final java.lang.String POLICY_DISABLE_CAMERA = "policy_disable_camera";
  public static final java.lang.String POLICY_DISABLE_SCREEN_CAPTURE = "policy_disable_screen_capture";
  public static final int PRIVATE_DNS_MODE_OFF = 1;
  public static final int PRIVATE_DNS_MODE_OPPORTUNISTIC = 2;
  public static final int PRIVATE_DNS_MODE_PROVIDER_HOSTNAME = 3;
  public static final int PRIVATE_DNS_MODE_UNKNOWN = 0;
  public static final int PRIVATE_DNS_SET_ERROR_FAILURE_SETTING = 2;
  public static final int PRIVATE_DNS_SET_ERROR_HOST_NOT_SERVING = 1;
  public static final int PRIVATE_DNS_SET_NO_ERROR = 0;
  public static final int PROVISIONING_MODE_FULLY_MANAGED_DEVICE = 1;
  public static final int PROVISIONING_MODE_MANAGED_PROFILE = 2;
  public static final int PROVISIONING_MODE_MANAGED_PROFILE_ON_PERSONAL_DEVICE = 3;
  public static final int RESET_PASSWORD_DO_NOT_ASK_CREDENTIALS_ON_BOOT = 2;
  public static final int RESET_PASSWORD_REQUIRE_ENTRY = 1;
  public static final int SKIP_SETUP_WIZARD = 1;
  public static final int WIFI_SECURITY_ENTERPRISE_192 = 3;
  public static final int WIFI_SECURITY_ENTERPRISE_EAP = 2;
  public static final int WIFI_SECURITY_OPEN = 0;
  public static final int WIFI_SECURITY_PERSONAL = 1;
  public static final int WIPE_EUICC = 4;
  public static final int WIPE_EXTERNAL_STORAGE = 1;
  public static final int WIPE_RESET_PROTECTION_DATA = 2;
  public static final int WIPE_SILENTLY = 8;
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public DevicePolicyManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public void acknowledgeDeviceCompliant() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.acknowledgeDeviceCompliant"); }
  @a2w.NotImplemented public void addCrossProfileIntentFilter(android.content.ComponentName admin, android.content.IntentFilter filter, int flags) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.addCrossProfileIntentFilter"); }
  @a2w.NotImplemented public boolean addCrossProfileWidgetProvider(android.content.ComponentName admin, java.lang.String packageName) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.addCrossProfileWidgetProvider"); return false; }
  @a2w.NotImplemented public int addOverrideApn(android.content.ComponentName admin, android.telephony.data.ApnSetting apnSetting) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.addOverrideApn"); return 0; }
  @a2w.NotImplemented public void addPersistentPreferredActivity(android.content.ComponentName admin, android.content.IntentFilter filter, android.content.ComponentName activity) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.addPersistentPreferredActivity"); }
  @a2w.NotImplemented public void addUserRestriction(android.content.ComponentName admin, java.lang.String key) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.addUserRestriction"); }
  @a2w.NotImplemented public void addUserRestrictionGlobally(java.lang.String key) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.addUserRestrictionGlobally"); }
  @a2w.NotImplemented public boolean bindDeviceAdminServiceAsUser(android.content.ComponentName admin, android.content.Intent serviceIntent, android.content.ServiceConnection conn, int flags, android.os.UserHandle targetUser) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.bindDeviceAdminServiceAsUser"); return false; }
  @a2w.NotImplemented public boolean bindDeviceAdminServiceAsUser(android.content.ComponentName admin, android.content.Intent serviceIntent, android.content.ServiceConnection conn, android.content.Context.BindServiceFlags flags, android.os.UserHandle targetUser) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.bindDeviceAdminServiceAsUser"); return false; }
  @a2w.NotImplemented public boolean canAdminGrantSensorsPermissions() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.canAdminGrantSensorsPermissions"); return false; }
  @a2w.NotImplemented public boolean canUsbDataSignalingBeDisabled() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.canUsbDataSignalingBeDisabled"); return false; }
  @a2w.NotImplemented public void clearApplicationUserData(android.content.ComponentName admin, java.lang.String packageName, java.util.concurrent.Executor executor, android.app.admin.DevicePolicyManager.OnClearApplicationUserDataListener listener) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.clearApplicationUserData"); }
  @a2w.NotImplemented public void clearCrossProfileIntentFilters(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.clearCrossProfileIntentFilters"); }
  @a2w.NotImplemented public void clearDeviceOwnerApp(java.lang.String packageName) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.clearDeviceOwnerApp"); }
  @a2w.NotImplemented public void clearPackagePersistentPreferredActivities(android.content.ComponentName admin, java.lang.String packageName) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.clearPackagePersistentPreferredActivities"); }
  @a2w.NotImplemented public void clearProfileOwner(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.clearProfileOwner"); }
  @a2w.NotImplemented public boolean clearResetPasswordToken(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.clearResetPasswordToken"); return false; }
  @a2w.NotImplemented public void clearUserRestriction(android.content.ComponentName admin, java.lang.String key) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.clearUserRestriction"); }
  @a2w.NotImplemented public android.content.Intent createAdminSupportIntent(java.lang.String restriction) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.createAdminSupportIntent"); return null; }
  @a2w.NotImplemented public android.os.UserHandle createAndInitializeUser(android.content.ComponentName p0, java.lang.String p1, java.lang.String p2, android.content.ComponentName p3, android.os.Bundle p4) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.createAndInitializeUser"); return null; }
  @a2w.NotImplemented public android.os.UserHandle createAndManageUser(android.content.ComponentName admin, java.lang.String name, android.content.ComponentName profileOwner, android.os.PersistableBundle adminExtras, int flags) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.createAndManageUser"); return null; }
  @a2w.NotImplemented public android.os.UserHandle createUser(android.content.ComponentName p0, java.lang.String p1) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.createUser"); return null; }
  @a2w.NotImplemented public int enableSystemApp(android.content.ComponentName admin, android.content.Intent intent) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.enableSystemApp"); return 0; }
  @a2w.NotImplemented public void enableSystemApp(android.content.ComponentName admin, java.lang.String packageName) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.enableSystemApp"); }
  @a2w.NotImplemented public android.security.AttestedKeyPair generateKeyPair(android.content.ComponentName admin, java.lang.String algorithm, android.security.keystore.KeyGenParameterSpec keySpec, int idAttestationFlags) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.generateKeyPair"); return null; }
  @a2w.NotImplemented public java.lang.String[] getAccountTypesWithManagementDisabled() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getAccountTypesWithManagementDisabled"); return null; }
  @a2w.NotImplemented public java.util.List<android.content.ComponentName> getActiveAdmins() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getActiveAdmins"); return null; }
  @a2w.NotImplemented public java.util.Set<java.lang.String> getAffiliationIds(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getAffiliationIds"); return null; }
  @a2w.NotImplemented public java.util.Set<java.lang.String> getAlwaysOnVpnLockdownWhitelist(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getAlwaysOnVpnLockdownWhitelist"); return null; }
  @a2w.NotImplemented public java.lang.String getAlwaysOnVpnPackage(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getAlwaysOnVpnPackage"); return null; }
  @a2w.NotImplemented public android.os.Bundle getApplicationRestrictions(android.content.ComponentName admin, java.lang.String packageName) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getApplicationRestrictions"); return null; }
  @a2w.NotImplemented public java.lang.String getApplicationRestrictionsManagingPackage(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getApplicationRestrictionsManagingPackage"); return null; }
  @a2w.NotImplemented public boolean getAutoTimeEnabled(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getAutoTimeEnabled"); return false; }
  @a2w.NotImplemented public boolean getAutoTimeRequired() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getAutoTimeRequired"); return false; }
  @a2w.NotImplemented public boolean getAutoTimeZoneEnabled(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getAutoTimeZoneEnabled"); return false; }
  @a2w.NotImplemented public java.util.List<android.os.UserHandle> getBindDeviceAdminTargetUsers(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getBindDeviceAdminTargetUsers"); return null; }
  @a2w.NotImplemented public boolean getBluetoothContactSharingDisabled(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getBluetoothContactSharingDisabled"); return false; }
  @a2w.NotImplemented public boolean getCameraDisabled(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getCameraDisabled"); return false; }
  @a2w.NotImplemented public java.lang.String getCertInstallerPackage(android.content.ComponentName admin) throws java.lang.SecurityException { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getCertInstallerPackage"); return null; }
  @a2w.NotImplemented public int getContentProtectionPolicy(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getContentProtectionPolicy"); return 0; }
  @a2w.NotImplemented public android.app.admin.PackagePolicy getCredentialManagerPolicy() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getCredentialManagerPolicy"); return null; }
  @a2w.NotImplemented public java.util.Set<java.lang.String> getCrossProfileCalendarPackages(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getCrossProfileCalendarPackages"); return null; }
  @a2w.NotImplemented public boolean getCrossProfileCallerIdDisabled(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getCrossProfileCallerIdDisabled"); return false; }
  @a2w.NotImplemented public boolean getCrossProfileContactsSearchDisabled(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getCrossProfileContactsSearchDisabled"); return false; }
  @a2w.NotImplemented public java.util.Set<java.lang.String> getCrossProfilePackages(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getCrossProfilePackages"); return null; }
  @a2w.NotImplemented public java.util.List<java.lang.String> getCrossProfileWidgetProviders(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getCrossProfileWidgetProviders"); return null; }
  @a2w.NotImplemented public int getCurrentFailedPasswordAttempts() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getCurrentFailedPasswordAttempts"); return 0; }
  @a2w.NotImplemented public java.util.List<java.lang.String> getDelegatePackages(android.content.ComponentName admin, java.lang.String delegationScope) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getDelegatePackages"); return null; }
  @a2w.NotImplemented public java.util.List<java.lang.String> getDelegatedScopes(android.content.ComponentName admin, java.lang.String delegatedPackage) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getDelegatedScopes"); return null; }
  @a2w.NotImplemented public java.lang.CharSequence getDeviceOwnerLockScreenInfo() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getDeviceOwnerLockScreenInfo"); return null; }
  @a2w.NotImplemented public java.lang.String getDevicePolicyManagementRoleHolderPackage() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getDevicePolicyManagementRoleHolderPackage"); return null; }
  @a2w.NotImplemented public java.lang.CharSequence getEndUserSessionMessage(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getEndUserSessionMessage"); return null; }
  @a2w.NotImplemented public java.lang.String getEnrollmentSpecificId() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getEnrollmentSpecificId"); return null; }
  @a2w.NotImplemented public android.app.admin.FactoryResetProtectionPolicy getFactoryResetProtectionPolicy(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getFactoryResetProtectionPolicy"); return null; }
  @a2w.NotImplemented public java.lang.String getGlobalPrivateDnsHost(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getGlobalPrivateDnsHost"); return null; }
  @a2w.NotImplemented public int getGlobalPrivateDnsMode(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getGlobalPrivateDnsMode"); return 0; }
  @a2w.NotImplemented public java.util.List<byte[]> getInstalledCaCerts(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getInstalledCaCerts"); return null; }
  @a2w.NotImplemented public java.util.List<java.lang.String> getKeepUninstalledPackages(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getKeepUninstalledPackages"); return null; }
  @a2w.NotImplemented public java.util.Map<java.lang.Integer, java.util.Set<java.lang.String>> getKeyPairGrants(java.lang.String alias) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getKeyPairGrants"); return null; }
  @a2w.NotImplemented public int getKeyguardDisabledFeatures(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getKeyguardDisabledFeatures"); return 0; }
  @a2w.NotImplemented public int getLockTaskFeatures(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getLockTaskFeatures"); return 0; }
  @a2w.NotImplemented public java.lang.String[] getLockTaskPackages(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getLockTaskPackages"); return null; }
  @a2w.NotImplemented public java.lang.CharSequence getLongSupportMessage(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getLongSupportMessage"); return null; }
  @a2w.NotImplemented public android.app.admin.PackagePolicy getManagedProfileCallerIdAccessPolicy() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getManagedProfileCallerIdAccessPolicy"); return null; }
  @a2w.NotImplemented public android.app.admin.PackagePolicy getManagedProfileContactsAccessPolicy() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getManagedProfileContactsAccessPolicy"); return null; }
  @a2w.NotImplemented public long getManagedProfileMaximumTimeOff(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getManagedProfileMaximumTimeOff"); return 0L; }
  @a2w.NotImplemented public android.app.admin.ManagedSubscriptionsPolicy getManagedSubscriptionsPolicy() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getManagedSubscriptionsPolicy"); return null; }
  @a2w.NotImplemented public int getMaximumFailedPasswordsForWipe(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getMaximumFailedPasswordsForWipe"); return 0; }
  @a2w.NotImplemented public long getMaximumTimeToLock(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getMaximumTimeToLock"); return 0L; }
  @a2w.NotImplemented public java.util.List<java.lang.String> getMeteredDataDisabledPackages(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getMeteredDataDisabledPackages"); return null; }
  @a2w.NotImplemented public int getMinimumRequiredWifiSecurityLevel() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getMinimumRequiredWifiSecurityLevel"); return 0; }
  @a2w.NotImplemented public int getMtePolicy() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getMtePolicy"); return 0; }
  @a2w.NotImplemented public int getNearbyAppStreamingPolicy() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getNearbyAppStreamingPolicy"); return 0; }
  @a2w.NotImplemented public int getNearbyNotificationStreamingPolicy() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getNearbyNotificationStreamingPolicy"); return 0; }
  @a2w.NotImplemented public int getOrganizationColor(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getOrganizationColor"); return 0; }
  @a2w.NotImplemented public java.lang.CharSequence getOrganizationName(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getOrganizationName"); return null; }
  @a2w.NotImplemented public java.util.List<android.telephony.data.ApnSetting> getOverrideApns(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getOverrideApns"); return null; }
  @a2w.NotImplemented public android.app.admin.DevicePolicyManager getParentProfileInstance(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getParentProfileInstance"); return null; }
  @a2w.NotImplemented public int getPasswordComplexity() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getPasswordComplexity"); return 0; }
  @a2w.NotImplemented public long getPasswordExpiration(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getPasswordExpiration"); return 0L; }
  @a2w.NotImplemented public long getPasswordExpirationTimeout(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getPasswordExpirationTimeout"); return 0L; }
  @a2w.NotImplemented public int getPasswordHistoryLength(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getPasswordHistoryLength"); return 0; }
  @a2w.NotImplemented public int getPasswordMaximumLength(int quality) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getPasswordMaximumLength"); return 0; }
  @a2w.NotImplemented public int getPasswordMinimumLength(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getPasswordMinimumLength"); return 0; }
  @a2w.NotImplemented public int getPasswordMinimumLetters(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getPasswordMinimumLetters"); return 0; }
  @a2w.NotImplemented public int getPasswordMinimumLowerCase(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getPasswordMinimumLowerCase"); return 0; }
  @a2w.NotImplemented public int getPasswordMinimumNonLetter(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getPasswordMinimumNonLetter"); return 0; }
  @a2w.NotImplemented public int getPasswordMinimumNumeric(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getPasswordMinimumNumeric"); return 0; }
  @a2w.NotImplemented public int getPasswordMinimumSymbols(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getPasswordMinimumSymbols"); return 0; }
  @a2w.NotImplemented public int getPasswordMinimumUpperCase(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getPasswordMinimumUpperCase"); return 0; }
  @a2w.NotImplemented public int getPasswordQuality(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getPasswordQuality"); return 0; }
  @a2w.NotImplemented public android.app.admin.SystemUpdateInfo getPendingSystemUpdate(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getPendingSystemUpdate"); return null; }
  @a2w.NotImplemented public int getPermissionGrantState(android.content.ComponentName admin, java.lang.String packageName, java.lang.String permission) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getPermissionGrantState"); return 0; }
  @a2w.NotImplemented public int getPermissionPolicy(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getPermissionPolicy"); return 0; }
  @a2w.NotImplemented public java.util.List<java.lang.String> getPermittedAccessibilityServices(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getPermittedAccessibilityServices"); return null; }
  @a2w.NotImplemented public java.util.List<java.lang.String> getPermittedCrossProfileNotificationListeners(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getPermittedCrossProfileNotificationListeners"); return null; }
  @a2w.NotImplemented public java.util.List<java.lang.String> getPermittedInputMethods(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getPermittedInputMethods"); return null; }
  @a2w.NotImplemented public int getPersonalAppsSuspendedReasons(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getPersonalAppsSuspendedReasons"); return 0; }
  @a2w.NotImplemented public java.util.List<android.app.admin.PreferentialNetworkServiceConfig> getPreferentialNetworkServiceConfigs() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getPreferentialNetworkServiceConfigs"); return null; }
  @a2w.NotImplemented public int getRequiredPasswordComplexity() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getRequiredPasswordComplexity"); return 0; }
  @a2w.NotImplemented public long getRequiredStrongAuthTimeout(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getRequiredStrongAuthTimeout"); return 0L; }
  @a2w.NotImplemented public android.app.admin.DevicePolicyResourcesManager getResources() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getResources"); return null; }
  @a2w.NotImplemented public boolean getScreenCaptureDisabled(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getScreenCaptureDisabled"); return false; }
  @a2w.NotImplemented public java.util.List<android.os.UserHandle> getSecondaryUsers(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getSecondaryUsers"); return null; }
  @a2w.NotImplemented public java.lang.CharSequence getShortSupportMessage(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getShortSupportMessage"); return null; }
  @a2w.NotImplemented public java.lang.CharSequence getStartUserSessionMessage(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getStartUserSessionMessage"); return null; }
  @a2w.NotImplemented public boolean getStorageEncryption(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getStorageEncryption"); return false; }
  @a2w.NotImplemented public int getStorageEncryptionStatus() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getStorageEncryptionStatus"); return 0; }
  @a2w.NotImplemented public java.util.Set<java.lang.Integer> getSubscriptionIds() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getSubscriptionIds"); return null; }
  @a2w.NotImplemented public android.app.admin.SystemUpdatePolicy getSystemUpdatePolicy() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getSystemUpdatePolicy"); return null; }
  @a2w.NotImplemented public android.os.PersistableBundle getTransferOwnershipBundle() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getTransferOwnershipBundle"); return null; }
  @a2w.NotImplemented public java.util.List<android.os.PersistableBundle> getTrustAgentConfiguration(android.content.ComponentName admin, android.content.ComponentName agent) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getTrustAgentConfiguration"); return null; }
  @a2w.NotImplemented public java.util.List<java.lang.String> getUserControlDisabledPackages(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getUserControlDisabledPackages"); return null; }
  @a2w.NotImplemented public android.os.Bundle getUserRestrictions(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getUserRestrictions"); return null; }
  @a2w.NotImplemented public android.os.Bundle getUserRestrictionsGlobally() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getUserRestrictionsGlobally"); return null; }
  @a2w.NotImplemented public java.lang.String getWifiMacAddress(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getWifiMacAddress"); return null; }
  @a2w.NotImplemented public android.app.admin.WifiSsidPolicy getWifiSsidPolicy() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.getWifiSsidPolicy"); return null; }
  @a2w.NotImplemented public boolean grantKeyPairToApp(android.content.ComponentName admin, java.lang.String alias, java.lang.String packageName) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.grantKeyPairToApp"); return false; }
  @a2w.NotImplemented public boolean grantKeyPairToWifiAuth(java.lang.String alias) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.grantKeyPairToWifiAuth"); return false; }
  @a2w.NotImplemented public boolean hasCaCertInstalled(android.content.ComponentName admin, byte[] certBuffer) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.hasCaCertInstalled"); return false; }
  @a2w.NotImplemented public boolean hasGrantedPolicy(android.content.ComponentName admin, int usesPolicy) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.hasGrantedPolicy"); return false; }
  @a2w.NotImplemented public boolean hasKeyPair(java.lang.String alias) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.hasKeyPair"); return false; }
  @a2w.NotImplemented public boolean hasLockdownAdminConfiguredNetworks(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.hasLockdownAdminConfiguredNetworks"); return false; }
  @a2w.NotImplemented public boolean installCaCert(android.content.ComponentName admin, byte[] certBuffer) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.installCaCert"); return false; }
  @a2w.NotImplemented public boolean installExistingPackage(android.content.ComponentName admin, java.lang.String packageName) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.installExistingPackage"); return false; }
  @a2w.NotImplemented public boolean installKeyPair(android.content.ComponentName admin, a2w.jdk.security.PrivateKey privKey, a2w.jdk.security.cert.Certificate cert, java.lang.String alias) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.installKeyPair"); return false; }
  @a2w.NotImplemented public boolean installKeyPair(android.content.ComponentName admin, a2w.jdk.security.PrivateKey privKey, a2w.jdk.security.cert.Certificate[] certs, java.lang.String alias, int flags) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.installKeyPair"); return false; }
  @a2w.NotImplemented public boolean installKeyPair(android.content.ComponentName admin, a2w.jdk.security.PrivateKey privKey, a2w.jdk.security.cert.Certificate[] certs, java.lang.String alias, boolean requestAccess) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.installKeyPair"); return false; }
  @a2w.NotImplemented public void installSystemUpdate(android.content.ComponentName admin, android.net.Uri updateFilePath, java.util.concurrent.Executor executor, android.app.admin.DevicePolicyManager.InstallSystemUpdateCallback callback) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.installSystemUpdate"); }
  @a2w.NotImplemented public boolean isActivePasswordSufficient() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isActivePasswordSufficient"); return false; }
  @a2w.NotImplemented public boolean isActivePasswordSufficientForDeviceRequirement() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isActivePasswordSufficientForDeviceRequirement"); return false; }
  @a2w.NotImplemented public boolean isAdminActive(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isAdminActive"); return false; }
  @a2w.NotImplemented public boolean isAffiliatedUser() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isAffiliatedUser"); return false; }
  @a2w.NotImplemented public boolean isAlwaysOnVpnLockdownEnabled(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isAlwaysOnVpnLockdownEnabled"); return false; }
  @a2w.NotImplemented public boolean isApplicationHidden(android.content.ComponentName admin, java.lang.String packageName) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isApplicationHidden"); return false; }
  @a2w.NotImplemented public boolean isBackupServiceEnabled(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isBackupServiceEnabled"); return false; }
  @a2w.NotImplemented public boolean isCallerApplicationRestrictionsManagingPackage() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isCallerApplicationRestrictionsManagingPackage"); return false; }
  @a2w.NotImplemented public boolean isCommonCriteriaModeEnabled(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isCommonCriteriaModeEnabled"); return false; }
  @a2w.NotImplemented public boolean isComplianceAcknowledgementRequired() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isComplianceAcknowledgementRequired"); return false; }
  @a2w.NotImplemented public boolean isDeviceFinanced() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isDeviceFinanced"); return false; }
  @a2w.NotImplemented public boolean isDeviceIdAttestationSupported() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isDeviceIdAttestationSupported"); return false; }
  @a2w.NotImplemented public boolean isDeviceOwnerApp(java.lang.String packageName) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isDeviceOwnerApp"); return false; }
  @a2w.NotImplemented public boolean isEphemeralUser(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isEphemeralUser"); return false; }
  @a2w.NotImplemented public boolean isKeyPairGrantedToWifiAuth(java.lang.String alias) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isKeyPairGrantedToWifiAuth"); return false; }
  @a2w.NotImplemented public boolean isLockTaskPermitted(java.lang.String pkg) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isLockTaskPermitted"); return false; }
  @a2w.NotImplemented public boolean isLogoutEnabled() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isLogoutEnabled"); return false; }
  @a2w.NotImplemented public boolean isManagedProfile(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isManagedProfile"); return false; }
  @a2w.NotImplemented public boolean isMasterVolumeMuted(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isMasterVolumeMuted"); return false; }
  @a2w.NotImplemented public static boolean isMtePolicyEnforced() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isMtePolicyEnforced"); return false; }
  @a2w.NotImplemented public boolean isNetworkLoggingEnabled(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isNetworkLoggingEnabled"); return false; }
  @a2w.NotImplemented public boolean isOrganizationOwnedDeviceWithManagedProfile() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isOrganizationOwnedDeviceWithManagedProfile"); return false; }
  @a2w.NotImplemented public boolean isOverrideApnEnabled(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isOverrideApnEnabled"); return false; }
  @a2w.NotImplemented public boolean isPackageSuspended(android.content.ComponentName admin, java.lang.String packageName) throws android.content.pm.PackageManager.NameNotFoundException { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isPackageSuspended"); return false; }
  @a2w.NotImplemented public boolean isPreferentialNetworkServiceEnabled() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isPreferentialNetworkServiceEnabled"); return false; }
  @a2w.NotImplemented public boolean isProfileOwnerApp(java.lang.String packageName) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isProfileOwnerApp"); return false; }
  @a2w.NotImplemented public boolean isProvisioningAllowed(java.lang.String action) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isProvisioningAllowed"); return false; }
  @a2w.NotImplemented public boolean isResetPasswordTokenActive(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isResetPasswordTokenActive"); return false; }
  @a2w.NotImplemented public boolean isSafeOperation(int reason) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isSafeOperation"); return false; }
  @a2w.NotImplemented public boolean isSecurityLoggingEnabled(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isSecurityLoggingEnabled"); return false; }
  @a2w.NotImplemented public boolean isStatusBarDisabled() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isStatusBarDisabled"); return false; }
  @a2w.NotImplemented public boolean isUninstallBlocked(android.content.ComponentName admin, java.lang.String packageName) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isUninstallBlocked"); return false; }
  @a2w.NotImplemented public boolean isUniqueDeviceAttestationSupported() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isUniqueDeviceAttestationSupported"); return false; }
  @a2w.NotImplemented public boolean isUsbDataSignalingEnabled() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isUsbDataSignalingEnabled"); return false; }
  @a2w.NotImplemented public boolean isUsingUnifiedPassword(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.isUsingUnifiedPassword"); return false; }
  @a2w.NotImplemented public java.util.List<android.os.UserHandle> listForegroundAffiliatedUsers() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.listForegroundAffiliatedUsers"); return null; }
  @a2w.NotImplemented public void lockNow() { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.lockNow"); }
  @a2w.NotImplemented public void lockNow(int flags) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.lockNow"); }
  @a2w.NotImplemented public int logoutUser(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.logoutUser"); return 0; }
  @a2w.NotImplemented public void reboot(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.reboot"); }
  @a2w.NotImplemented public void removeActiveAdmin(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.removeActiveAdmin"); }
  @a2w.NotImplemented public boolean removeCrossProfileWidgetProvider(android.content.ComponentName admin, java.lang.String packageName) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.removeCrossProfileWidgetProvider"); return false; }
  @a2w.NotImplemented public boolean removeKeyPair(android.content.ComponentName admin, java.lang.String alias) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.removeKeyPair"); return false; }
  @a2w.NotImplemented public boolean removeOverrideApn(android.content.ComponentName admin, int apnId) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.removeOverrideApn"); return false; }
  @a2w.NotImplemented public boolean removeUser(android.content.ComponentName admin, android.os.UserHandle userHandle) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.removeUser"); return false; }
  @a2w.NotImplemented public boolean requestBugreport(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.requestBugreport"); return false; }
  @a2w.NotImplemented public boolean resetPassword(java.lang.String password, int flags) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.resetPassword"); return false; }
  @a2w.NotImplemented public boolean resetPasswordWithToken(android.content.ComponentName admin, java.lang.String password, byte[] token, int flags) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.resetPasswordWithToken"); return false; }
  @a2w.NotImplemented public java.util.List<android.app.admin.NetworkEvent> retrieveNetworkLogs(android.content.ComponentName admin, long batchToken) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.retrieveNetworkLogs"); return null; }
  @a2w.NotImplemented public java.util.List<android.app.admin.SecurityLog.SecurityEvent> retrievePreRebootSecurityLogs(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.retrievePreRebootSecurityLogs"); return null; }
  @a2w.NotImplemented public java.util.List<android.app.admin.SecurityLog.SecurityEvent> retrieveSecurityLogs(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.retrieveSecurityLogs"); return null; }
  @a2w.NotImplemented public boolean revokeKeyPairFromApp(android.content.ComponentName admin, java.lang.String alias, java.lang.String packageName) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.revokeKeyPairFromApp"); return false; }
  @a2w.NotImplemented public boolean revokeKeyPairFromWifiAuth(java.lang.String alias) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.revokeKeyPairFromWifiAuth"); return false; }
  @a2w.NotImplemented public void setAccountManagementDisabled(android.content.ComponentName admin, java.lang.String accountType, boolean disabled) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setAccountManagementDisabled"); }
  @a2w.NotImplemented public void setAffiliationIds(android.content.ComponentName admin, java.util.Set<java.lang.String> ids) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setAffiliationIds"); }
  @a2w.NotImplemented public void setAlwaysOnVpnPackage(android.content.ComponentName admin, java.lang.String vpnPackage, boolean lockdownEnabled) throws android.content.pm.PackageManager.NameNotFoundException { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setAlwaysOnVpnPackage"); }
  @a2w.NotImplemented public void setAlwaysOnVpnPackage(android.content.ComponentName admin, java.lang.String vpnPackage, boolean lockdownEnabled, java.util.Set<java.lang.String> lockdownAllowlist) throws android.content.pm.PackageManager.NameNotFoundException { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setAlwaysOnVpnPackage"); }
  @a2w.NotImplemented public boolean setApplicationHidden(android.content.ComponentName admin, java.lang.String packageName, boolean p2) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setApplicationHidden"); return false; }
  @a2w.NotImplemented public void setApplicationRestrictions(android.content.ComponentName admin, java.lang.String packageName, android.os.Bundle settings) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setApplicationRestrictions"); }
  @a2w.NotImplemented public void setApplicationRestrictionsManagingPackage(android.content.ComponentName admin, java.lang.String packageName) throws android.content.pm.PackageManager.NameNotFoundException { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setApplicationRestrictionsManagingPackage"); }
  @a2w.NotImplemented public void setAutoTimeEnabled(android.content.ComponentName admin, boolean enabled) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setAutoTimeEnabled"); }
  @a2w.NotImplemented public void setAutoTimeRequired(android.content.ComponentName admin, boolean required) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setAutoTimeRequired"); }
  @a2w.NotImplemented public void setAutoTimeZoneEnabled(android.content.ComponentName admin, boolean enabled) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setAutoTimeZoneEnabled"); }
  @a2w.NotImplemented public void setBackupServiceEnabled(android.content.ComponentName admin, boolean enabled) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setBackupServiceEnabled"); }
  @a2w.NotImplemented public void setBluetoothContactSharingDisabled(android.content.ComponentName admin, boolean disabled) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setBluetoothContactSharingDisabled"); }
  @a2w.NotImplemented public void setCameraDisabled(android.content.ComponentName admin, boolean disabled) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setCameraDisabled"); }
  @a2w.NotImplemented public void setCertInstallerPackage(android.content.ComponentName admin, java.lang.String installerPackage) throws java.lang.SecurityException { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setCertInstallerPackage"); }
  @a2w.NotImplemented public void setCommonCriteriaModeEnabled(android.content.ComponentName admin, boolean enabled) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setCommonCriteriaModeEnabled"); }
  @a2w.NotImplemented public void setConfiguredNetworksLockdownState(android.content.ComponentName admin, boolean lockdown) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setConfiguredNetworksLockdownState"); }
  @a2w.NotImplemented public void setContentProtectionPolicy(android.content.ComponentName admin, int policy) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setContentProtectionPolicy"); }
  @a2w.NotImplemented public void setCredentialManagerPolicy(android.app.admin.PackagePolicy policy) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setCredentialManagerPolicy"); }
  @a2w.NotImplemented public void setCrossProfileCalendarPackages(android.content.ComponentName admin, java.util.Set<java.lang.String> packageNames) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setCrossProfileCalendarPackages"); }
  @a2w.NotImplemented public void setCrossProfileCallerIdDisabled(android.content.ComponentName admin, boolean disabled) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setCrossProfileCallerIdDisabled"); }
  @a2w.NotImplemented public void setCrossProfileContactsSearchDisabled(android.content.ComponentName admin, boolean disabled) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setCrossProfileContactsSearchDisabled"); }
  @a2w.NotImplemented public void setCrossProfilePackages(android.content.ComponentName admin, java.util.Set<java.lang.String> packageNames) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setCrossProfilePackages"); }
  @a2w.NotImplemented public void setDefaultDialerApplication(java.lang.String packageName) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setDefaultDialerApplication"); }
  @a2w.NotImplemented public void setDefaultSmsApplication(android.content.ComponentName admin, java.lang.String packageName) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setDefaultSmsApplication"); }
  @a2w.NotImplemented public void setDelegatedScopes(android.content.ComponentName admin, java.lang.String delegatePackage, java.util.List<java.lang.String> scopes) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setDelegatedScopes"); }
  @a2w.NotImplemented public void setDeviceOwnerLockScreenInfo(android.content.ComponentName admin, java.lang.CharSequence info) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setDeviceOwnerLockScreenInfo"); }
  @a2w.NotImplemented public void setEndUserSessionMessage(android.content.ComponentName admin, java.lang.CharSequence endUserSessionMessage) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setEndUserSessionMessage"); }
  @a2w.NotImplemented public void setFactoryResetProtectionPolicy(android.content.ComponentName admin, android.app.admin.FactoryResetProtectionPolicy policy) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setFactoryResetProtectionPolicy"); }
  @a2w.NotImplemented public int setGlobalPrivateDnsModeOpportunistic(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setGlobalPrivateDnsModeOpportunistic"); return 0; }
  @a2w.NotImplemented public int setGlobalPrivateDnsModeSpecifiedHost(android.content.ComponentName admin, java.lang.String privateDnsHost) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setGlobalPrivateDnsModeSpecifiedHost"); return 0; }
  @a2w.NotImplemented public void setGlobalSetting(android.content.ComponentName admin, java.lang.String setting, java.lang.String value) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setGlobalSetting"); }
  @a2w.NotImplemented public void setKeepUninstalledPackages(android.content.ComponentName admin, java.util.List<java.lang.String> packageNames) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setKeepUninstalledPackages"); }
  @a2w.NotImplemented public boolean setKeyPairCertificate(android.content.ComponentName admin, java.lang.String alias, java.util.List<a2w.jdk.security.cert.Certificate> certs, boolean isUserSelectable) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setKeyPairCertificate"); return false; }
  @a2w.NotImplemented public boolean setKeyguardDisabled(android.content.ComponentName admin, boolean disabled) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setKeyguardDisabled"); return false; }
  @a2w.NotImplemented public void setKeyguardDisabledFeatures(android.content.ComponentName admin, int which) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setKeyguardDisabledFeatures"); }
  @a2w.NotImplemented public void setLocationEnabled(android.content.ComponentName admin, boolean locationEnabled) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setLocationEnabled"); }
  @a2w.NotImplemented public void setLockTaskFeatures(android.content.ComponentName admin, int flags) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setLockTaskFeatures"); }
  @a2w.NotImplemented public void setLockTaskPackages(android.content.ComponentName admin, java.lang.String[] packages) throws java.lang.SecurityException { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setLockTaskPackages"); }
  @a2w.NotImplemented public void setLogoutEnabled(android.content.ComponentName admin, boolean enabled) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setLogoutEnabled"); }
  @a2w.NotImplemented public void setLongSupportMessage(android.content.ComponentName admin, java.lang.CharSequence message) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setLongSupportMessage"); }
  @a2w.NotImplemented public void setManagedProfileCallerIdAccessPolicy(android.app.admin.PackagePolicy policy) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setManagedProfileCallerIdAccessPolicy"); }
  @a2w.NotImplemented public void setManagedProfileContactsAccessPolicy(android.app.admin.PackagePolicy policy) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setManagedProfileContactsAccessPolicy"); }
  @a2w.NotImplemented public void setManagedProfileMaximumTimeOff(android.content.ComponentName admin, long timeoutMillis) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setManagedProfileMaximumTimeOff"); }
  @a2w.NotImplemented public void setManagedSubscriptionsPolicy(android.app.admin.ManagedSubscriptionsPolicy policy) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setManagedSubscriptionsPolicy"); }
  @a2w.NotImplemented public void setMasterVolumeMuted(android.content.ComponentName admin, boolean on) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setMasterVolumeMuted"); }
  @a2w.NotImplemented public void setMaximumFailedPasswordsForWipe(android.content.ComponentName admin, int num) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setMaximumFailedPasswordsForWipe"); }
  @a2w.NotImplemented public void setMaximumTimeToLock(android.content.ComponentName admin, long timeMs) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setMaximumTimeToLock"); }
  @a2w.NotImplemented public java.util.List<java.lang.String> setMeteredDataDisabledPackages(android.content.ComponentName admin, java.util.List<java.lang.String> packageNames) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setMeteredDataDisabledPackages"); return null; }
  @a2w.NotImplemented public void setMinimumRequiredWifiSecurityLevel(int level) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setMinimumRequiredWifiSecurityLevel"); }
  @a2w.NotImplemented public void setMtePolicy(int policy) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setMtePolicy"); }
  @a2w.NotImplemented public void setNearbyAppStreamingPolicy(int policy) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setNearbyAppStreamingPolicy"); }
  @a2w.NotImplemented public void setNearbyNotificationStreamingPolicy(int policy) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setNearbyNotificationStreamingPolicy"); }
  @a2w.NotImplemented public void setNetworkLoggingEnabled(android.content.ComponentName admin, boolean enabled) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setNetworkLoggingEnabled"); }
  @a2w.NotImplemented public void setOrganizationColor(android.content.ComponentName admin, int color) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setOrganizationColor"); }
  @a2w.NotImplemented public void setOrganizationId(java.lang.String enterpriseId) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setOrganizationId"); }
  @a2w.NotImplemented public void setOrganizationName(android.content.ComponentName admin, java.lang.CharSequence title) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setOrganizationName"); }
  @a2w.NotImplemented public void setOverrideApnsEnabled(android.content.ComponentName admin, boolean enabled) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setOverrideApnsEnabled"); }
  @a2w.NotImplemented public java.lang.String[] setPackagesSuspended(android.content.ComponentName admin, java.lang.String[] packageNames, boolean suspended) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setPackagesSuspended"); return null; }
  @a2w.NotImplemented public void setPasswordExpirationTimeout(android.content.ComponentName admin, long timeout) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setPasswordExpirationTimeout"); }
  @a2w.NotImplemented public void setPasswordHistoryLength(android.content.ComponentName admin, int length) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setPasswordHistoryLength"); }
  @a2w.NotImplemented public void setPasswordMinimumLength(android.content.ComponentName admin, int length) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setPasswordMinimumLength"); }
  @a2w.NotImplemented public void setPasswordMinimumLetters(android.content.ComponentName admin, int length) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setPasswordMinimumLetters"); }
  @a2w.NotImplemented public void setPasswordMinimumLowerCase(android.content.ComponentName admin, int length) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setPasswordMinimumLowerCase"); }
  @a2w.NotImplemented public void setPasswordMinimumNonLetter(android.content.ComponentName admin, int length) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setPasswordMinimumNonLetter"); }
  @a2w.NotImplemented public void setPasswordMinimumNumeric(android.content.ComponentName admin, int length) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setPasswordMinimumNumeric"); }
  @a2w.NotImplemented public void setPasswordMinimumSymbols(android.content.ComponentName admin, int length) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setPasswordMinimumSymbols"); }
  @a2w.NotImplemented public void setPasswordMinimumUpperCase(android.content.ComponentName admin, int length) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setPasswordMinimumUpperCase"); }
  @a2w.NotImplemented public void setPasswordQuality(android.content.ComponentName admin, int quality) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setPasswordQuality"); }
  @a2w.NotImplemented public boolean setPermissionGrantState(android.content.ComponentName admin, java.lang.String packageName, java.lang.String permission, int grantState) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setPermissionGrantState"); return false; }
  @a2w.NotImplemented public void setPermissionPolicy(android.content.ComponentName admin, int policy) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setPermissionPolicy"); }
  @a2w.NotImplemented public boolean setPermittedAccessibilityServices(android.content.ComponentName admin, java.util.List<java.lang.String> packageNames) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setPermittedAccessibilityServices"); return false; }
  @a2w.NotImplemented public boolean setPermittedCrossProfileNotificationListeners(android.content.ComponentName admin, java.util.List<java.lang.String> packageList) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setPermittedCrossProfileNotificationListeners"); return false; }
  @a2w.NotImplemented public boolean setPermittedInputMethods(android.content.ComponentName admin, java.util.List<java.lang.String> packageNames) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setPermittedInputMethods"); return false; }
  @a2w.NotImplemented public void setPersonalAppsSuspended(android.content.ComponentName admin, boolean suspended) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setPersonalAppsSuspended"); }
  @a2w.NotImplemented public void setPreferentialNetworkServiceConfigs(java.util.List<android.app.admin.PreferentialNetworkServiceConfig> preferentialNetworkServiceConfigs) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setPreferentialNetworkServiceConfigs"); }
  @a2w.NotImplemented public void setPreferentialNetworkServiceEnabled(boolean enabled) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setPreferentialNetworkServiceEnabled"); }
  @a2w.NotImplemented public void setProfileEnabled(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setProfileEnabled"); }
  @a2w.NotImplemented public void setProfileName(android.content.ComponentName admin, java.lang.String profileName) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setProfileName"); }
  @a2w.NotImplemented public void setRecommendedGlobalProxy(android.content.ComponentName admin, android.net.ProxyInfo proxyInfo) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setRecommendedGlobalProxy"); }
  @a2w.NotImplemented public void setRequiredPasswordComplexity(int passwordComplexity) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setRequiredPasswordComplexity"); }
  @a2w.NotImplemented public void setRequiredStrongAuthTimeout(android.content.ComponentName admin, long timeoutMs) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setRequiredStrongAuthTimeout"); }
  @a2w.NotImplemented public boolean setResetPasswordToken(android.content.ComponentName admin, byte[] token) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setResetPasswordToken"); return false; }
  @a2w.NotImplemented public void setRestrictionsProvider(android.content.ComponentName admin, android.content.ComponentName provider) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setRestrictionsProvider"); }
  @a2w.NotImplemented public void setScreenCaptureDisabled(android.content.ComponentName admin, boolean disabled) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setScreenCaptureDisabled"); }
  @a2w.NotImplemented public void setSecureSetting(android.content.ComponentName admin, java.lang.String setting, java.lang.String value) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setSecureSetting"); }
  @a2w.NotImplemented public void setSecurityLoggingEnabled(android.content.ComponentName admin, boolean enabled) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setSecurityLoggingEnabled"); }
  @a2w.NotImplemented public void setShortSupportMessage(android.content.ComponentName admin, java.lang.CharSequence message) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setShortSupportMessage"); }
  @a2w.NotImplemented public void setStartUserSessionMessage(android.content.ComponentName admin, java.lang.CharSequence startUserSessionMessage) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setStartUserSessionMessage"); }
  @a2w.NotImplemented public boolean setStatusBarDisabled(android.content.ComponentName admin, boolean disabled) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setStatusBarDisabled"); return false; }
  @a2w.NotImplemented public int setStorageEncryption(android.content.ComponentName admin, boolean encrypt) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setStorageEncryption"); return 0; }
  @a2w.NotImplemented public void setSystemSetting(android.content.ComponentName admin, java.lang.String setting, java.lang.String value) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setSystemSetting"); }
  @a2w.NotImplemented public void setSystemUpdatePolicy(android.content.ComponentName admin, android.app.admin.SystemUpdatePolicy policy) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setSystemUpdatePolicy"); }
  @a2w.NotImplemented public boolean setTime(android.content.ComponentName admin, long millis) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setTime"); return false; }
  @a2w.NotImplemented public boolean setTimeZone(android.content.ComponentName admin, java.lang.String timeZone) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setTimeZone"); return false; }
  @a2w.NotImplemented public void setTrustAgentConfiguration(android.content.ComponentName admin, android.content.ComponentName target, android.os.PersistableBundle configuration) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setTrustAgentConfiguration"); }
  @a2w.NotImplemented public void setUninstallBlocked(android.content.ComponentName admin, java.lang.String packageName, boolean uninstallBlocked) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setUninstallBlocked"); }
  @a2w.NotImplemented public void setUsbDataSignalingEnabled(boolean enabled) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setUsbDataSignalingEnabled"); }
  @a2w.NotImplemented public void setUserControlDisabledPackages(android.content.ComponentName admin, java.util.List<java.lang.String> packages) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setUserControlDisabledPackages"); }
  @a2w.NotImplemented public void setUserIcon(android.content.ComponentName admin, android.graphics.Bitmap icon) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setUserIcon"); }
  @a2w.NotImplemented public void setWifiSsidPolicy(android.app.admin.WifiSsidPolicy policy) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.setWifiSsidPolicy"); }
  @a2w.NotImplemented public int startUserInBackground(android.content.ComponentName admin, android.os.UserHandle userHandle) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.startUserInBackground"); return 0; }
  @a2w.NotImplemented public int stopUser(android.content.ComponentName admin, android.os.UserHandle userHandle) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.stopUser"); return 0; }
  @a2w.NotImplemented public boolean switchUser(android.content.ComponentName admin, android.os.UserHandle userHandle) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.switchUser"); return false; }
  @a2w.NotImplemented public void transferOwnership(android.content.ComponentName admin, android.content.ComponentName target, android.os.PersistableBundle bundle) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.transferOwnership"); }
  @a2w.NotImplemented public void uninstallAllUserCaCerts(android.content.ComponentName admin) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.uninstallAllUserCaCerts"); }
  @a2w.NotImplemented public void uninstallCaCert(android.content.ComponentName admin, byte[] certBuffer) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.uninstallCaCert"); }
  @a2w.NotImplemented public boolean updateOverrideApn(android.content.ComponentName admin, int apnId, android.telephony.data.ApnSetting apnSetting) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.updateOverrideApn"); return false; }
  @a2w.NotImplemented public void wipeData(int flags) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.wipeData"); }
  @a2w.NotImplemented public void wipeData(int flags, java.lang.CharSequence reason) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.wipeData"); }
  @a2w.NotImplemented public void wipeDevice(int flags) { a2w.Stubs.hit("android.app.admin.DevicePolicyManager.wipeDevice"); }

    public static abstract class InstallSystemUpdateCallback {
    public static final int UPDATE_ERROR_BATTERY_LOW = 5;
    public static final int UPDATE_ERROR_FILE_NOT_FOUND = 4;
    public static final int UPDATE_ERROR_INCORRECT_OS_VERSION = 2;
    public static final int UPDATE_ERROR_UNKNOWN = 1;
    public static final int UPDATE_ERROR_UPDATE_FILE_INVALID = 3;
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public InstallSystemUpdateCallback(a2w.Hidden hidden) { }
    public InstallSystemUpdateCallback() { this((a2w.Hidden) null); }
    public void onInstallUpdateError(int errorCode, java.lang.String errorMessage) { }
  }

    public interface OnClearApplicationUserDataListener {
    void onApplicationUserDataCleared(java.lang.String p0, boolean p1);
  }
}
