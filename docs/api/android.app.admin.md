# android.app.admin

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ConnectEvent — declaration; API 26+ (0 +, 0 *, 0 ~, 5 -)

```
- CREATOR [field]  [API 26+]
- describeContents(): int  [API 26+]
- getInetAddress(): InetAddress  [API 26+]
- getPort(): int  [API 26+]
- toString(): String  [API 26+]
- writeToParcel(Parcel, int): void  [API 26+]
```

## DelegatedAdminReceiver — declaration; API 29+ (0 +, 0 *, 0 ~, 5 -)

```
- DelegatedAdminReceiver()  [API 29+]
- onChoosePrivateKeyAlias(Context, Intent, int, Uri, String): String  [API 29+]
- onNetworkLogsAvailable(Context, Intent, long, int): void  [API 29+]
- onReceive(Context, Intent): void  [API 29+]
- onSecurityLogsAvailable(Context, Intent): void  [API 31+]
```

## DeviceAdminInfo — declaration; API 8+ (0 +, 0 *, 0 ~, 17 -)

```
- CREATOR [field]  [API 8+]
- DeviceAdminInfo(Context, ResolveInfo)  [API 8+]
- describeContents(): int  [API 8+]
- dump(Printer, String): void  [API 8+]
- getActivityInfo(): ActivityInfo  [API 8+]
- getComponent(): ComponentName  [API 8+]
- getHeadlessDeviceOwnerMode(): int  [API 34+]
- getPackageName(): String  [API 8+]
- getReceiverName(): String  [API 8+]
- getTagForPolicy(int): String  [API 8+]
- isVisible(): boolean  [API 8+]
- loadDescription(PackageManager): CharSequence  [API 8+]
- loadIcon(PackageManager): Drawable  [API 8+]
- loadLabel(PackageManager): CharSequence  [API 8+]
- supportsTransferOwnership(): boolean  [API 28+]
- toString(): String  [API 8+]
- usesPolicy(int): boolean  [API 8+]
- writeToParcel(Parcel, int): void  [API 8+]
```

## DeviceAdminReceiver — declaration; API 8+ (0 +, 0 *, 0 ~, 35 -)

```
- DeviceAdminReceiver()  [API 8+]
- getManager(Context): DevicePolicyManager  [API 8+]
- getWho(Context): ComponentName  [API 8+]
- onBugreportFailed(Context, Intent, int): void  [API 24+]
- onBugreportShared(Context, Intent, String): void  [API 24+]
- onBugreportSharingDeclined(Context, Intent): void  [API 24+]
- onChoosePrivateKeyAlias(Context, Intent, int, Uri, String): String  [API 23+]
- onComplianceAcknowledgementRequired(Context, Intent): void  [API 31+]
- onDisableRequested(Context, Intent): CharSequence  [API 8+]
- onDisabled(Context, Intent): void  [API 8+]
- onEnabled(Context, Intent): void  [API 8+]
- onLockTaskModeEntering(Context, Intent, String): void  [API 21+]
- onLockTaskModeExiting(Context, Intent): void  [API 21+]
- onNetworkLogsAvailable(Context, Intent, long, int): void  [API 26+]
- onOperationSafetyStateChanged(Context, int, boolean): void  [API 31+]
- onPasswordChanged(Context, Intent): void  [API 8+, deprecated in 26]
- onPasswordChanged(Context, Intent, UserHandle): void  [API 26+]
- onPasswordExpiring(Context, Intent): void  [API 11+, deprecated in 26]
- onPasswordExpiring(Context, Intent, UserHandle): void  [API 26+]
- onPasswordFailed(Context, Intent): void  [API 8+, deprecated in 26]
- onPasswordFailed(Context, Intent, UserHandle): void  [API 26+]
- onPasswordSucceeded(Context, Intent): void  [API 8+, deprecated in 26]
- onPasswordSucceeded(Context, Intent, UserHandle): void  [API 26+]
- onProfileProvisioningComplete(Context, Intent): void  [API 21+]
- onReadyForUserInitialization(Context, Intent): void  [API 23+, deprecated in 24]
- onReceive(Context, Intent): void  [API 8+]
- onSecurityLogsAvailable(Context, Intent): void  [API 24+]
- onSystemUpdatePending(Context, Intent, long): void  [API 23+]
- onTransferAffiliatedProfileOwnershipComplete(Context, UserHandle): void  [API 28+]
- onTransferOwnershipComplete(Context, PersistableBundle): void  [API 28+]
- onUserAdded(Context, Intent, UserHandle): void  [API 26+]
- onUserRemoved(Context, Intent, UserHandle): void  [API 26+]
- onUserStarted(Context, Intent, UserHandle): void  [API 28+]
- onUserStopped(Context, Intent, UserHandle): void  [API 28+]
- onUserSwitched(Context, Intent, UserHandle): void  [API 28+]
```

## DeviceAdminService — declaration; API 26+ (0 +, 0 *, 0 ~, 2 -)

```
- DeviceAdminService()  [API 26+]
- onBind(Intent): IBinder  [API 26+]
```

## DevicePolicyIdentifiers — declaration; API 34+ (0 +, 0 *, 0 ~, 1 -)

```
- getIdentifierForUserRestriction(String): String [static]  [API 34+]
```

## DevicePolicyManager — framework; API 8+ (0 +, 0 *, 0 ~, 292 -)

```
- acknowledgeDeviceCompliant(): void  [API 31+]
- addCrossProfileIntentFilter(ComponentName, IntentFilter, int): void  [API 21+]
- addCrossProfileWidgetProvider(ComponentName, String): boolean  [API 21+]
- addOverrideApn(ComponentName, ApnSetting): int  [API 28+]
- addPersistentPreferredActivity(ComponentName, IntentFilter, ComponentName): void  [API 21+]
- addUserRestriction(ComponentName, String): void  [API 21+]
- addUserRestrictionGlobally(String): void  [API 34+]
- bindDeviceAdminServiceAsUser(ComponentName, Intent, ServiceConnection, Context.BindServiceFlags, UserHandle): boolean  [API 34+]
- bindDeviceAdminServiceAsUser(ComponentName, Intent, ServiceConnection, int, UserHandle): boolean  [API 26+]
- canAdminGrantSensorsPermissions(): boolean  [API 31+]
- canUsbDataSignalingBeDisabled(): boolean  [API 31+]
- clearApplicationUserData(ComponentName, String, Executor, DevicePolicyManager.OnClearApplicationUserDataListener): void  [API 28+]
- clearCrossProfileIntentFilters(ComponentName): void  [API 21+]
- clearDeviceOwnerApp(String): void  [API 21+, deprecated in 26]
- clearPackagePersistentPreferredActivities(ComponentName, String): void  [API 21+]
- clearProfileOwner(ComponentName): void  [API 24+, deprecated in 26]
- clearResetPasswordToken(ComponentName): boolean  [API 26+]
- clearUserRestriction(ComponentName, String): void  [API 21+]
- createAdminSupportIntent(String): Intent  [API 26+]
- createAndInitializeUser(ComponentName, String, String, ComponentName, Bundle): UserHandle  [API 21-23, removed in 24, deprecated in 23]
- createAndManageUser(ComponentName, String, ComponentName, PersistableBundle, int): UserHandle  [API 24+]
- createUser(ComponentName, String): UserHandle  [API 21-23, removed in 24, deprecated in 23]
- enableSystemApp(ComponentName, Intent): int  [API 21+]
- enableSystemApp(ComponentName, String): void  [API 21+]
- generateKeyPair(ComponentName, String, KeyGenParameterSpec, int): AttestedKeyPair  [API 28+]
- getAccountTypesWithManagementDisabled(): String[]  [API 21+]
- getActiveAdmins(): List  [API 8+]
- getAffiliationIds(ComponentName): Set  [API 26+]
- getAlwaysOnVpnLockdownWhitelist(ComponentName): Set  [API 29+]
- getAlwaysOnVpnPackage(ComponentName): String  [API 24+]
- getApplicationRestrictions(ComponentName, String): Bundle  [API 21+]
- getApplicationRestrictionsManagingPackage(ComponentName): String  [API 24+, deprecated in 26]
- getAutoTimeEnabled(ComponentName): boolean  [API 30+]
- getAutoTimeRequired(): boolean  [API 21+, deprecated in 30]
- getAutoTimeZoneEnabled(ComponentName): boolean  [API 30+]
- getBindDeviceAdminTargetUsers(ComponentName): List  [API 26+]
- getBluetoothContactSharingDisabled(ComponentName): boolean  [API 23+]
- getCameraDisabled(ComponentName): boolean  [API 14+]
- getCertInstallerPackage(ComponentName): String  [API 23+, deprecated in 26]
- getContentProtectionPolicy(ComponentName): int  [API 35+]
- getCredentialManagerPolicy(): PackagePolicy  [API 34+]
- getCrossProfileCalendarPackages(ComponentName): Set  [API 29+, deprecated in 34]
- getCrossProfileCallerIdDisabled(ComponentName): boolean  [API 21+, deprecated in 34]
- getCrossProfileContactsSearchDisabled(ComponentName): boolean  [API 24+, deprecated in 34]
- getCrossProfilePackages(ComponentName): Set  [API 30+]
- getCrossProfileWidgetProviders(ComponentName): List  [API 21+]
- getCurrentFailedPasswordAttempts(): int  [API 8+]
- getDelegatePackages(ComponentName, String): List  [API 26+]
- getDelegatedScopes(ComponentName, String): List  [API 26+]
- getDeviceOwnerLockScreenInfo(): CharSequence  [API 24+]
- getDevicePolicyManagementRoleHolderPackage(): String  [API 33+]
- getEndUserSessionMessage(ComponentName): CharSequence  [API 28+]
- getEnrollmentSpecificId(): String  [API 31+]
- getFactoryResetProtectionPolicy(ComponentName): FactoryResetProtectionPolicy  [API 30+]
- getGlobalPrivateDnsHost(ComponentName): String  [API 29+]
- getGlobalPrivateDnsMode(ComponentName): int  [API 29+]
- getInstalledCaCerts(ComponentName): List  [API 21+]
- getKeepUninstalledPackages(ComponentName): List  [API 28+]
- getKeyPairGrants(String): Map  [API 31+]
- getKeyguardDisabledFeatures(ComponentName): int  [API 17+]
- getLockTaskFeatures(ComponentName): int  [API 28+]
- getLockTaskPackages(ComponentName): String[]  [API 26+]
- getLongSupportMessage(ComponentName): CharSequence  [API 24+]
- getManagedProfileCallerIdAccessPolicy(): PackagePolicy  [API 34+]
- getManagedProfileContactsAccessPolicy(): PackagePolicy  [API 34+]
- getManagedProfileMaximumTimeOff(ComponentName): long  [API 30+]
- getManagedSubscriptionsPolicy(): ManagedSubscriptionsPolicy  [API 34+]
- getMaximumFailedPasswordsForWipe(ComponentName): int  [API 8+]
- getMaximumTimeToLock(ComponentName): long  [API 8+]
- getMeteredDataDisabledPackages(ComponentName): List  [API 28+]
- getMinimumRequiredWifiSecurityLevel(): int  [API 33+]
- getMtePolicy(): int  [API 34+]
- getNearbyAppStreamingPolicy(): int  [API 31+]
- getNearbyNotificationStreamingPolicy(): int  [API 31+]
- getOrganizationColor(ComponentName): int  [API 24+, deprecated in 31]
- getOrganizationName(ComponentName): CharSequence  [API 24+]
- getOverrideApns(ComponentName): List  [API 28+]
- getParentProfileInstance(ComponentName): DevicePolicyManager  [API 24+]
- getPasswordComplexity(): int  [API 29+]
- getPasswordExpiration(ComponentName): long  [API 11+]
- getPasswordExpirationTimeout(ComponentName): long  [API 11+]
- getPasswordHistoryLength(ComponentName): int  [API 11+]
- getPasswordMaximumLength(int): int  [API 8+]
- getPasswordMinimumLength(ComponentName): int  [API 8+, deprecated in 31]
- getPasswordMinimumLetters(ComponentName): int  [API 11+, deprecated in 31]
- getPasswordMinimumLowerCase(ComponentName): int  [API 11+, deprecated in 31]
- getPasswordMinimumNonLetter(ComponentName): int  [API 11+, deprecated in 31]
- getPasswordMinimumNumeric(ComponentName): int  [API 11+, deprecated in 31]
- getPasswordMinimumSymbols(ComponentName): int  [API 11+, deprecated in 31]
- getPasswordMinimumUpperCase(ComponentName): int  [API 11+, deprecated in 31]
- getPasswordQuality(ComponentName): int  [API 8+, deprecated in 31]
- getPendingSystemUpdate(ComponentName): SystemUpdateInfo  [API 26+]
- getPermissionGrantState(ComponentName, String, String): int  [API 23+]
- getPermissionPolicy(ComponentName): int  [API 23+]
- getPermittedAccessibilityServices(ComponentName): List  [API 21+]
- getPermittedCrossProfileNotificationListeners(ComponentName): List  [API 26+]
- getPermittedInputMethods(ComponentName): List  [API 21+]
- getPersonalAppsSuspendedReasons(ComponentName): int  [API 30+]
- getPreferentialNetworkServiceConfigs(): List  [API 33+]
- getRequiredPasswordComplexity(): int  [API 31+]
- getRequiredStrongAuthTimeout(ComponentName): long  [API 26+]
- getResources(): DevicePolicyResourcesManager  [API 33+]
- getScreenCaptureDisabled(ComponentName): boolean  [API 21+]
- getSecondaryUsers(ComponentName): List  [API 28+]
- getShortSupportMessage(ComponentName): CharSequence  [API 24+]
- getStartUserSessionMessage(ComponentName): CharSequence  [API 28+]
- getStorageEncryption(ComponentName): boolean  [API 11+, deprecated in 30]
- getStorageEncryptionStatus(): int  [API 11+]
- getSubscriptionIds(): Set  [API 35+]
- getSystemUpdatePolicy(): SystemUpdatePolicy  [API 23+]
- getTransferOwnershipBundle(): PersistableBundle  [API 28+]
- getTrustAgentConfiguration(ComponentName, ComponentName): List  [API 23+]
- getUserControlDisabledPackages(ComponentName): List  [API 30+]
- getUserRestrictions(ComponentName): Bundle  [API 24+]
- getUserRestrictionsGlobally(): Bundle  [API 34+]
- getWifiMacAddress(ComponentName): String  [API 24+]
- getWifiSsidPolicy(): WifiSsidPolicy  [API 33+]
- grantKeyPairToApp(ComponentName, String, String): boolean  [API 30+]
- grantKeyPairToWifiAuth(String): boolean  [API 31+]
- hasCaCertInstalled(ComponentName, byte[]): boolean  [API 21+]
- hasGrantedPolicy(ComponentName, int): boolean  [API 11+]
- hasKeyPair(String): boolean  [API 31+]
- hasLockdownAdminConfiguredNetworks(ComponentName): boolean  [API 30+]
- installCaCert(ComponentName, byte[]): boolean  [API 21+]
- installExistingPackage(ComponentName, String): boolean  [API 28+]
- installKeyPair(ComponentName, PrivateKey, Certificate, String): boolean  [API 21+]
- installKeyPair(ComponentName, PrivateKey, Certificate[], String, boolean): boolean  [API 24+]
- installKeyPair(ComponentName, PrivateKey, Certificate[], String, int): boolean  [API 28+]
- installSystemUpdate(ComponentName, Uri, Executor, DevicePolicyManager.InstallSystemUpdateCallback): void  [API 29+]
- isActivePasswordSufficient(): boolean  [API 8+]
- isActivePasswordSufficientForDeviceRequirement(): boolean  [API 31+]
- isAdminActive(ComponentName): boolean  [API 8+]
- isAffiliatedUser(): boolean  [API 28+]
- isAlwaysOnVpnLockdownEnabled(ComponentName): boolean  [API 29+]
- isApplicationHidden(ComponentName, String): boolean  [API 21+]
- isBackupServiceEnabled(ComponentName): boolean  [API 26+]
- isCallerApplicationRestrictionsManagingPackage(): boolean  [API 24+, deprecated in 26]
- isCommonCriteriaModeEnabled(ComponentName): boolean  [API 30+]
- isComplianceAcknowledgementRequired(): boolean  [API 31+]
- isDeviceFinanced(): boolean  [API 34+]
- isDeviceIdAttestationSupported(): boolean  [API 28+]
- isDeviceOwnerApp(String): boolean  [API 18+]
- isEphemeralUser(ComponentName): boolean  [API 28+]
- isKeyPairGrantedToWifiAuth(String): boolean  [API 31+]
- isLockTaskPermitted(String): boolean  [API 21+]
- isLogoutEnabled(): boolean  [API 28+]
- isManagedProfile(ComponentName): boolean  [API 24+]
- isMasterVolumeMuted(ComponentName): boolean  [API 21+]
- isMtePolicyEnforced(): boolean [static]  [API 35+]
- isNetworkLoggingEnabled(ComponentName): boolean  [API 26+]
- isOrganizationOwnedDeviceWithManagedProfile(): boolean  [API 30+]
- isOverrideApnEnabled(ComponentName): boolean  [API 28+]
- isPackageSuspended(ComponentName, String): boolean  [API 24+]
- isPreferentialNetworkServiceEnabled(): boolean  [API 31+]
- isProfileOwnerApp(String): boolean  [API 21+]
- isProvisioningAllowed(String): boolean  [API 24+]
- isResetPasswordTokenActive(ComponentName): boolean  [API 26+]
- isSafeOperation(int): boolean  [API 31+]
- isSecurityLoggingEnabled(ComponentName): boolean  [API 24+]
- isStatusBarDisabled(): boolean  [API 34+]
- isUninstallBlocked(ComponentName, String): boolean  [API 21+]
- isUniqueDeviceAttestationSupported(): boolean  [API 30+]
- isUsbDataSignalingEnabled(): boolean  [API 31+]
- isUsingUnifiedPassword(ComponentName): boolean  [API 28+]
- listForegroundAffiliatedUsers(): List  [API 31+]
- lockNow(): void  [API 8+]
- lockNow(int): void  [API 26+]
- logoutUser(ComponentName): int  [API 28+]
- reboot(ComponentName): void  [API 24+]
- removeActiveAdmin(ComponentName): void  [API 8+]
- removeCrossProfileWidgetProvider(ComponentName, String): boolean  [API 21+]
- removeKeyPair(ComponentName, String): boolean  [API 24+]
- removeOverrideApn(ComponentName, int): boolean  [API 28+]
- removeUser(ComponentName, UserHandle): boolean  [API 21+]
- requestBugreport(ComponentName): boolean  [API 24+]
- resetPassword(String, int): boolean  [API 8+, deprecated in 30]
- resetPasswordWithToken(ComponentName, String, byte[], int): boolean  [API 26+]
- retrieveNetworkLogs(ComponentName, long): List  [API 26+]
- retrievePreRebootSecurityLogs(ComponentName): List  [API 24+]
- retrieveSecurityLogs(ComponentName): List  [API 24+]
- revokeKeyPairFromApp(ComponentName, String, String): boolean  [API 30+]
- revokeKeyPairFromWifiAuth(String): boolean  [API 31+]
- setAccountManagementDisabled(ComponentName, String, boolean): void  [API 21+]
- setAffiliationIds(ComponentName, Set): void  [API 26+]
- setAlwaysOnVpnPackage(ComponentName, String, boolean): void  [API 24+]
- setAlwaysOnVpnPackage(ComponentName, String, boolean, Set): void  [API 29+]
- setApplicationHidden(ComponentName, String, boolean): boolean  [API 21+]
- setApplicationRestrictions(ComponentName, String, Bundle): void  [API 21+]
- setApplicationRestrictionsManagingPackage(ComponentName, String): void  [API 24+, deprecated in 26]
- setAutoTimeEnabled(ComponentName, boolean): void  [API 30+]
- setAutoTimeRequired(ComponentName, boolean): void  [API 21+, deprecated in 30]
- setAutoTimeZoneEnabled(ComponentName, boolean): void  [API 30+]
- setBackupServiceEnabled(ComponentName, boolean): void  [API 26+]
- setBluetoothContactSharingDisabled(ComponentName, boolean): void  [API 23+]
- setCameraDisabled(ComponentName, boolean): void  [API 14+]
- setCertInstallerPackage(ComponentName, String): void  [API 23+, deprecated in 26]
- setCommonCriteriaModeEnabled(ComponentName, boolean): void  [API 30+]
- setConfiguredNetworksLockdownState(ComponentName, boolean): void  [API 30+]
- setContentProtectionPolicy(ComponentName, int): void  [API 35+]
- setCredentialManagerPolicy(PackagePolicy): void  [API 34+]
- setCrossProfileCalendarPackages(ComponentName, Set): void  [API 29+, deprecated in 34]
- setCrossProfileCallerIdDisabled(ComponentName, boolean): void  [API 21+, deprecated in 34]
- setCrossProfileContactsSearchDisabled(ComponentName, boolean): void  [API 24+, deprecated in 34]
- setCrossProfilePackages(ComponentName, Set): void  [API 30+]
- setDefaultDialerApplication(String): void  [API 34+]
- setDefaultSmsApplication(ComponentName, String): void  [API 29+]
- setDelegatedScopes(ComponentName, String, List): void  [API 26+]
- setDeviceOwnerLockScreenInfo(ComponentName, CharSequence): void  [API 24+]
- setEndUserSessionMessage(ComponentName, CharSequence): void  [API 28+]
- setFactoryResetProtectionPolicy(ComponentName, FactoryResetProtectionPolicy): void  [API 30+]
- setGlobalPrivateDnsModeOpportunistic(ComponentName): int  [API 29+]
- setGlobalPrivateDnsModeSpecifiedHost(ComponentName, String): int  [API 29+]
- setGlobalSetting(ComponentName, String, String): void  [API 21+]
- setKeepUninstalledPackages(ComponentName, List): void  [API 28+]
- setKeyPairCertificate(ComponentName, String, List, boolean): boolean  [API 28+]
- setKeyguardDisabled(ComponentName, boolean): boolean  [API 23+]
- setKeyguardDisabledFeatures(ComponentName, int): void  [API 17+]
- setLocationEnabled(ComponentName, boolean): void  [API 30+]
- setLockTaskFeatures(ComponentName, int): void  [API 28+]
- setLockTaskPackages(ComponentName, String[]): void  [API 21+]
- setLogoutEnabled(ComponentName, boolean): void  [API 28+]
- setLongSupportMessage(ComponentName, CharSequence): void  [API 24+]
- setManagedProfileCallerIdAccessPolicy(PackagePolicy): void  [API 34+]
- setManagedProfileContactsAccessPolicy(PackagePolicy): void  [API 34+]
- setManagedProfileMaximumTimeOff(ComponentName, long): void  [API 30+]
- setManagedSubscriptionsPolicy(ManagedSubscriptionsPolicy): void  [API 34+]
- setMasterVolumeMuted(ComponentName, boolean): void  [API 21+]
- setMaximumFailedPasswordsForWipe(ComponentName, int): void  [API 8+]
- setMaximumTimeToLock(ComponentName, long): void  [API 8+]
- setMeteredDataDisabledPackages(ComponentName, List): List  [API 28+]
- setMinimumRequiredWifiSecurityLevel(int): void  [API 33+]
- setMtePolicy(int): void  [API 34+]
- setNearbyAppStreamingPolicy(int): void  [API 31+]
- setNearbyNotificationStreamingPolicy(int): void  [API 31+]
- setNetworkLoggingEnabled(ComponentName, boolean): void  [API 26+]
- setOrganizationColor(ComponentName, int): void  [API 24+, deprecated in 31]
- setOrganizationId(String): void  [API 31+]
- setOrganizationName(ComponentName, CharSequence): void  [API 24+]
- setOverrideApnsEnabled(ComponentName, boolean): void  [API 28+]
- setPackagesSuspended(ComponentName, String[], boolean): String[]  [API 24+]
- setPasswordExpirationTimeout(ComponentName, long): void  [API 11+]
- setPasswordHistoryLength(ComponentName, int): void  [API 11+]
- setPasswordMinimumLength(ComponentName, int): void  [API 8+, deprecated in 31]
- setPasswordMinimumLetters(ComponentName, int): void  [API 11+, deprecated in 31]
- setPasswordMinimumLowerCase(ComponentName, int): void  [API 11+, deprecated in 31]
- setPasswordMinimumNonLetter(ComponentName, int): void  [API 11+, deprecated in 31]
- setPasswordMinimumNumeric(ComponentName, int): void  [API 11+, deprecated in 31]
- setPasswordMinimumSymbols(ComponentName, int): void  [API 11+, deprecated in 31]
- setPasswordMinimumUpperCase(ComponentName, int): void  [API 11+, deprecated in 31]
- setPasswordQuality(ComponentName, int): void  [API 8+, deprecated in 31]
- setPermissionGrantState(ComponentName, String, String, int): boolean  [API 23+]
- setPermissionPolicy(ComponentName, int): void  [API 23+]
- setPermittedAccessibilityServices(ComponentName, List): boolean  [API 21+]
- setPermittedCrossProfileNotificationListeners(ComponentName, List): boolean  [API 26+]
- setPermittedInputMethods(ComponentName, List): boolean  [API 21+]
- setPersonalAppsSuspended(ComponentName, boolean): void  [API 30+]
- setPreferentialNetworkServiceConfigs(List): void  [API 33+]
- setPreferentialNetworkServiceEnabled(boolean): void  [API 31+]
- setProfileEnabled(ComponentName): void  [API 21+]
- setProfileName(ComponentName, String): void  [API 21+]
- setRecommendedGlobalProxy(ComponentName, ProxyInfo): void  [API 21+]
- setRequiredPasswordComplexity(int): void  [API 31+]
- setRequiredStrongAuthTimeout(ComponentName, long): void  [API 26+]
- setResetPasswordToken(ComponentName, byte[]): boolean  [API 26+]
- setRestrictionsProvider(ComponentName, ComponentName): void  [API 21+]
- setScreenCaptureDisabled(ComponentName, boolean): void  [API 21+]
- setSecureSetting(ComponentName, String, String): void  [API 21+]
- setSecurityLoggingEnabled(ComponentName, boolean): void  [API 24+]
- setShortSupportMessage(ComponentName, CharSequence): void  [API 24+]
- setStartUserSessionMessage(ComponentName, CharSequence): void  [API 28+]
- setStatusBarDisabled(ComponentName, boolean): boolean  [API 23+]
- setStorageEncryption(ComponentName, boolean): int  [API 11+, deprecated in 30]
- setSystemSetting(ComponentName, String, String): void  [API 28+]
- setSystemUpdatePolicy(ComponentName, SystemUpdatePolicy): void  [API 23+]
- setTime(ComponentName, long): boolean  [API 28+]
- setTimeZone(ComponentName, String): boolean  [API 28+]
- setTrustAgentConfiguration(ComponentName, ComponentName, PersistableBundle): void  [API 23+]
- setUninstallBlocked(ComponentName, String, boolean): void  [API 21+]
- setUsbDataSignalingEnabled(boolean): void  [API 31+]
- setUserControlDisabledPackages(ComponentName, List): void  [API 30+]
- setUserIcon(ComponentName, Bitmap): void  [API 23+]
- setWifiSsidPolicy(WifiSsidPolicy): void  [API 33+]
- startUserInBackground(ComponentName, UserHandle): int  [API 28+]
- stopUser(ComponentName, UserHandle): int  [API 28+]
- switchUser(ComponentName, UserHandle): boolean  [API 21+]
- transferOwnership(ComponentName, ComponentName, PersistableBundle): void  [API 28+]
- uninstallAllUserCaCerts(ComponentName): void  [API 21+]
- uninstallCaCert(ComponentName, byte[]): void  [API 21+]
- updateOverrideApn(ComponentName, int, ApnSetting): boolean  [API 28+]
- wipeData(int): void  [API 8+]
- wipeData(int, CharSequence): void  [API 28+]
- wipeDevice(int): void  [API 34+]
```

## DevicePolicyManager.InstallSystemUpdateCallback — framework; API 29+ (2 +, 0 *, 0 ~, 0 -)

```
+ InstallSystemUpdateCallback()  [API 29+]
+ onInstallUpdateError(int, String): void  [API 29+]
```

## DevicePolicyManager.OnClearApplicationUserDataListener — framework; API 28+ (1 +, 0 *, 0 ~, 0 -)

```
+ onApplicationUserDataCleared(String, boolean): void  [API 28+]
```

## DevicePolicyResources — declaration; API 33+ (0 +, 0 *, 0 ~, 0 -)

## DevicePolicyResourcesManager — declaration; API 33+ (0 +, 0 *, 0 ~, 8 -)

```
- getDrawable(String, String, String, Supplier): Drawable  [API 33+]
- getDrawable(String, String, Supplier): Drawable  [API 33+]
- getDrawableAsIcon(String, String, Icon): Icon  [API 33+]
- getDrawableAsIcon(String, String, String, Icon): Icon  [API 33+]
- getDrawableForDensity(String, String, String, int, Supplier): Drawable  [API 33+]
- getDrawableForDensity(String, String, int, Supplier): Drawable  [API 33+]
- getString(String, Supplier): String  [API 33+]
- getString(String, Supplier, Object[]): String  [API 33+]
```

## DnsEvent — declaration; API 26+ (0 +, 0 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 26+]
- describeContents(): int  [API 26+]
- getHostname(): String  [API 26+]
- getInetAddresses(): List  [API 26+]
- getTotalResolvedAddressCount(): int  [API 26+]
- toString(): String  [API 26+]
- writeToParcel(Parcel, int): void  [API 26+]
```

## FactoryResetProtectionPolicy — declaration; API 30+ (0 +, 5 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 30+]
* describeContents(): int  [API 30+]
* getFactoryResetProtectionAccounts(): List  [API 30+]
* isFactoryResetProtectionEnabled(): boolean  [API 30+]
* toString(): String  [API 30+]
* writeToParcel(Parcel, int): void  [API 30+]
```

## FactoryResetProtectionPolicy.Builder — declaration; API 30+ (0 +, 4 *, 0 ~, 0 -)

```
* Builder()  [API 30+]
* build(): FactoryResetProtectionPolicy  [API 30+]
* setFactoryResetProtectionAccounts(List): FactoryResetProtectionPolicy.Builder  [API 30+]
* setFactoryResetProtectionEnabled(boolean): FactoryResetProtectionPolicy.Builder  [API 30+]
```

## FreezePeriod — declaration; API 28+ (0 +, 4 *, 0 ~, 0 -)

```
* FreezePeriod(MonthDay, MonthDay)  [API 28+]
* getEnd(): MonthDay  [API 28+]
* getStart(): MonthDay  [API 28+]
* toString(): String  [API 28+]
```

## ManagedSubscriptionsPolicy — declaration; API 34+ (0 +, 7 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* ManagedSubscriptionsPolicy(int)  [API 34+]
* describeContents(): int  [API 34+]
* equals(Object): boolean  [API 34+]
* getPolicyType(): int  [API 34+]
* hashCode(): int  [API 34+]
* toString(): String  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## NetworkEvent — declaration; API 26+ (1 +, 0 *, 0 ~, 4 -)

```
- CREATOR [field]  [API 26+]
- describeContents(): int  [API 26+]
- getId(): long  [API 28+]
- getPackageName(): String  [API 26+]
- getTimestamp(): long  [API 26+]
+ writeToParcel(Parcel, int): void  [API 26+]
```

## PackagePolicy — declaration; API 34+ (0 +, 8 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* PackagePolicy(int)  [API 34+]
* PackagePolicy(int, Set)  [API 34+]
* describeContents(): int  [API 34+]
* equals(Object): boolean  [API 34+]
* getPackageNames(): Set  [API 34+]
* getPolicyType(): int  [API 34+]
* hashCode(): int  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## PolicyUpdateReceiver — declaration; API 34+ (4 +, 0 *, 0 ~, 0 -)

```
+ PolicyUpdateReceiver()  [API 34+]
+ onPolicyChanged(Context, String, Bundle, TargetUser, PolicyUpdateResult): void  [API 34+]
+ onPolicySetResult(Context, String, Bundle, TargetUser, PolicyUpdateResult): void  [API 34+]
+ onReceive(Context, Intent): void  [API 34+]
```

## PolicyUpdateResult — declaration; API 34+ (0 +, 2 *, 0 ~, 0 -)

```
* PolicyUpdateResult(int)  [API 34+]
* getResultCode(): int  [API 34+]
```

## PreferentialNetworkServiceConfig — declaration; API 33+ (0 +, 10 *, 0 ~, 1 -)

```
+ CREATOR [field]  [API 33+]
* describeContents(): int  [API 33+]
* equals(Object): boolean  [API 33+]
* getExcludedUids(): int[]  [API 33+]
* getIncludedUids(): int[]  [API 33+]
* getNetworkId(): int  [API 33+]
* hashCode(): int  [API 33+]
* isEnabled(): boolean  [API 33+]
* isFallbackToDefaultConnectionAllowed(): boolean  [API 33+]
- shouldBlockNonMatchingNetworks(): boolean  [API 34+]
* toString(): String  [API 33+]
* writeToParcel(Parcel, int): void  [API 33+]
```

## PreferentialNetworkServiceConfig.Builder — declaration; API 33+ (0 +, 7 *, 0 ~, 1 -)

```
* Builder()  [API 33+]
* build(): PreferentialNetworkServiceConfig  [API 33+]
* setEnabled(boolean): PreferentialNetworkServiceConfig.Builder  [API 33+]
* setExcludedUids(int[]): PreferentialNetworkServiceConfig.Builder  [API 33+]
* setFallbackToDefaultConnectionAllowed(boolean): PreferentialNetworkServiceConfig.Builder  [API 33+]
* setIncludedUids(int[]): PreferentialNetworkServiceConfig.Builder  [API 33+]
* setNetworkId(int): PreferentialNetworkServiceConfig.Builder  [API 33+]
- setShouldBlockNonMatchingNetworks(boolean): PreferentialNetworkServiceConfig.Builder  [API 34+]
```

## SecurityLog — declaration; API 24+ (1 +, 0 *, 0 ~, 0 -)

```
+ SecurityLog()  [API 24+]
```

## SecurityLog.SecurityEvent — declaration; API 24+ (0 +, 0 *, 0 ~, 9 -)

```
- CREATOR [field]  [API 24+]
- describeContents(): int  [API 24+]
- equals(Object): boolean  [API 24+]
- getData(): Object  [API 24+]
- getId(): long  [API 28+]
- getLogLevel(): int  [API 28+]
- getTag(): int  [API 24+]
- getTimeNanos(): long  [API 24+]
- hashCode(): int  [API 24+]
- writeToParcel(Parcel, int): void  [API 24+]
```

## SystemUpdateInfo — declaration; API 26+ (0 +, 0 *, 0 ~, 7 -)

```
- CREATOR [field]  [API 26+]
- describeContents(): int  [API 26+]
- equals(Object): boolean  [API 26+]
- getReceivedTime(): long  [API 26+]
- getSecurityPatchState(): int  [API 26+]
- hashCode(): int  [API 26+]
- toString(): String  [API 26+]
- writeToParcel(Parcel, int): void  [API 26+]
```

## SystemUpdatePolicy — declaration; API 23+ (0 +, 2 *, 0 ~, 9 -)

```
- CREATOR [field]  [API 23+]
- createAutomaticInstallPolicy(): SystemUpdatePolicy [static]  [API 23+]
- createPostponeInstallPolicy(): SystemUpdatePolicy [static]  [API 23+]
- createWindowedInstallPolicy(int, int): SystemUpdatePolicy [static]  [API 23+]
- describeContents(): int  [API 23+]
* getFreezePeriods(): List  [API 28+]
- getInstallWindowEnd(): int  [API 23+]
- getInstallWindowStart(): int  [API 23+]
- getPolicyType(): int  [API 23+]
* setFreezePeriods(List): SystemUpdatePolicy  [API 28+]
- toString(): String  [API 23+]
- writeToParcel(Parcel, int): void  [API 23+]
```

## SystemUpdatePolicy.ValidationFailedException — declaration; API 28+ (0 +, 0 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 28+]
- describeContents(): int  [API 28+]
- getErrorCode(): int  [API 28+]
- writeToParcel(Parcel, int): void  [API 28+]
```

## TargetUser — declaration; API 34+ (0 +, 0 *, 0 ~, 2 -)

```
- GLOBAL [field]  [API 34+]
- LOCAL_USER [field]  [API 34+]
- PARENT_USER [field]  [API 34+]
- UNKNOWN_USER [field]  [API 34+]
- equals(Object): boolean  [API 34+]
- hashCode(): int  [API 34+]
```

## UnsafeStateException — declaration; API 31+ (0 +, 0 *, 0 ~, 4 -)

```
- CREATOR [field]  [API 31+]
- describeContents(): int  [API 31+]
- getMessage(): String  [API 31+]
- getReasons(): List  [API 31+]
- writeToParcel(Parcel, int): void  [API 31+]
```

## WifiSsidPolicy — declaration; API 33+ (0 +, 7 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 33+]
* WifiSsidPolicy(int, Set)  [API 33+]
* describeContents(): int  [API 33+]
* equals(Object): boolean  [API 33+]
* getPolicyType(): int  [API 33+]
* getSsids(): Set  [API 33+]
* hashCode(): int  [API 33+]
* writeToParcel(Parcel, int): void  [API 33+]
```

