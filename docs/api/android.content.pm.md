# android.content.pm

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## ActivityInfo — framework; API 1+ (2 +, 0 *, 0 ~, 6 -)

```
+ ActivityInfo()  [API 1+]
- ActivityInfo(ActivityInfo)  [API 1+]
- CREATOR [field]  [API 1+]
- colorMode [field]  [API 26+]
+ configChanges [field]  [API 1+]
- describeContents(): int  [API 1+]
- documentLaunchMode [field]  [API 21+]
- dump(Printer, String): void  [API 1+]
+ flags [field]  [API 1+]
- getKnownActivityEmbeddingCerts(): Set  [API 33+]
- getThemeResource(): int  [API 1+]
+ launchMode [field]  [API 1+]
- maxRecents [field]  [API 21+]
- parentActivityName [field]  [API 16+]
+ permission [field]  [API 1+]
- persistableMode [field]  [API 21+]
- requiredDisplayCategory [field]  [API 34+]
+ screenOrientation [field]  [API 1+]
- softInputMode [field]  [API 3+]
+ targetActivity [field]  [API 1+]
+ taskAffinity [field]  [API 1+]
+ theme [field]  [API 1+]
+ toString(): String  [API 1+]
- uiOptions [field]  [API 14+]
- windowLayout [field]  [API 24+]
- writeToParcel(Parcel, int): void  [API 1+]
```

## ActivityInfo.WindowLayout — declaration; API 24+ (0 +, 0 *, 0 ~, 1 -)

```
- WindowLayout(int, float, int, float, int, int, int)  [API 24+]
- gravity [field]  [API 24+]
- height [field]  [API 24+]
- heightFraction [field]  [API 24+]
- minHeight [field]  [API 24+]
- minWidth [field]  [API 24+]
- width [field]  [API 24+]
- widthFraction [field]  [API 24+]
```

## ApkChecksum — declaration; API 31+ (0 +, 0 *, 0 ~, 8 -)

```
- CREATOR [field]  [API 31+]
- describeContents(): int  [API 31+]
- getInstallerCertificate(): Certificate  [API 31+]
- getInstallerPackageName(): String  [API 31+]
- getSplitName(): String  [API 31+]
- getType(): int  [API 31+]
- getValue(): byte[]  [API 31+]
- toString(): String  [API 31+]
- writeToParcel(Parcel, int): void  [API 31+]
```

## ApplicationInfo — framework; API 1+ (2 +, 0 *, 2 ~, 14 -)

```
+ ApplicationInfo()  [API 1+]
- ApplicationInfo(ApplicationInfo)  [API 1+]
- CREATOR [field]  [API 1+]
- appComponentFactory [field]  [API 28+]
- areAttributionsUserVisible(): boolean  [API 31+]
- backupAgentName [field]  [API 8+]
- category [field]  [API 26+]
+ className [field]  [API 1+]
- compatibleWidthLimitDp [field]  [API 13+]
+ compileSdkVersion [field]  [API 31+]
- compileSdkVersionCodename [field]  [API 31+]
+ dataDir [field]  [API 1+]
~ describeContents(): int  [API 1+]
+ descriptionRes [field]  [API 1+]
- deviceProtectedDataDir [field]  [API 24+]
- dump(Printer, String): void  [API 1+]
+ enabled [field]  [API 1+]
+ flags [field]  [API 1+]
- getCategoryTitle(Context, int): CharSequence [static]  [API 26+]
- getGwpAsanMode(): int  [API 30+]
- getKnownActivityEmbeddingCerts(): Set  [API 33+]
- getMemtagMode(): int  [API 31+]
- getNativeHeapZeroInitialized(): int  [API 31+]
- getRequestRawExternalStorageAccess(): int  [API 31+]
- isProfileable(): boolean  [API 31+]
- isProfileableByShell(): boolean  [API 29+]
- isResourceOverlay(): boolean  [API 29+]
- isVirtualPreload(): boolean  [API 27+]
- largestWidthLimitDp [field]  [API 13+]
- loadDescription(PackageManager): CharSequence  [API 1+]
- manageSpaceActivityName [field]  [API 1+]
+ minSdkVersion [field]  [API 24+]
+ nativeLibraryDir [field]  [API 9+]
+ permission [field]  [API 1+]
+ processName [field]  [API 1+]
+ publicSourceDir [field]  [API 1+]
- requiresSmallestWidthDp [field]  [API 13+]
+ sharedLibraryFiles [field]  [API 1+]
+ sourceDir [field]  [API 1+]
- splitNames [field]  [API 26+]
- splitPublicSourceDirs [field]  [API 21+]
- splitSourceDirs [field]  [API 21+]
- storageUuid [field]  [API 26+]
+ targetSdkVersion [field]  [API 4+]
+ taskAffinity [field]  [API 1+]
+ theme [field]  [API 1+]
+ toString(): String  [API 1+]
- uiOptions [field]  [API 14+]
+ uid [field]  [API 1+]
~ writeToParcel(Parcel, int): void  [API 1+]
```

## ApplicationInfo.DisplayNameComparator — declaration; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- DisplayNameComparator(PackageManager)  [API 1+]
- compare(ApplicationInfo, ApplicationInfo): int  [API 1+]
```

## ArchivedActivityInfo — declaration; API 35+ (0 +, 9 *, 0 ~, 0 -)

```
* ArchivedActivityInfo(CharSequence, ComponentName)  [API 35+]
* getComponentName(): ComponentName  [API 35+]
* getIcon(): Drawable  [API 35+]
* getLabel(): CharSequence  [API 35+]
* getMonochromeIcon(): Drawable  [API 35+]
* setComponentName(ComponentName): ArchivedActivityInfo  [API 35+]
* setIcon(Drawable): ArchivedActivityInfo  [API 35+]
* setLabel(CharSequence): ArchivedActivityInfo  [API 35+]
* setMonochromeIcon(Drawable): ArchivedActivityInfo  [API 35+]
```

## ArchivedPackageInfo — declaration; API 35+ (0 +, 19 *, 0 ~, 0 -)

```
* ArchivedPackageInfo(String, SigningInfo, List)  [API 35+]
* getDefaultToDeviceProtectedStorage(): String  [API 35+]
* getLauncherActivities(): List  [API 35+]
* getPackageName(): String  [API 35+]
* getRequestLegacyExternalStorage(): String  [API 35+]
* getSigningInfo(): SigningInfo  [API 35+]
* getTargetSdkVersion(): int  [API 35+]
* getUserDataFragile(): String  [API 35+]
* getVersionCode(): int  [API 35+]
* getVersionCodeMajor(): int  [API 35+]
* setDefaultToDeviceProtectedStorage(String): ArchivedPackageInfo  [API 35+]
* setLauncherActivities(List): ArchivedPackageInfo  [API 35+]
* setPackageName(String): ArchivedPackageInfo  [API 35+]
* setRequestLegacyExternalStorage(String): ArchivedPackageInfo  [API 35+]
* setSigningInfo(SigningInfo): ArchivedPackageInfo  [API 35+]
* setTargetSdkVersion(int): ArchivedPackageInfo  [API 35+]
* setUserDataFragile(String): ArchivedPackageInfo  [API 35+]
* setVersionCode(int): ArchivedPackageInfo  [API 35+]
* setVersionCodeMajor(int): ArchivedPackageInfo  [API 35+]
```

## Attribution — declaration; API 31+ (0 +, 0 *, 0 ~, 4 -)

```
- CREATOR [field]  [API 31+]
- describeContents(): int  [API 31+]
- getLabel(): int  [API 31+]
- getTag(): String  [API 31+]
- writeToParcel(Parcel, int): void  [API 31+]
```

## Capability — declaration; API 33+ (0 +, 5 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 33+]
* describeContents(): int  [API 33+]
* equals(Object): boolean  [API 33+]
* getName(): String  [API 33+]
* hashCode(): int  [API 33+]
* writeToParcel(Parcel, int): void  [API 33+]
```

## Capability.Builder — declaration; API 33+ (0 +, 2 *, 0 ~, 0 -)

```
* Builder(String)  [API 33+]
* build(): Capability  [API 33+]
```

## CapabilityParams — declaration; API 33+ (0 +, 1 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 33+]
- describeContents(): int  [API 33+]
- equals(Object): boolean  [API 33+]
* getAliases(): List  [API 33+]
- getName(): String  [API 33+]
- getValue(): String  [API 33+]
- hashCode(): int  [API 33+]
- writeToParcel(Parcel, int): void  [API 33+]
```

## CapabilityParams.Builder — declaration; API 33+ (0 +, 2 *, 0 ~, 1 -)

```
- Builder(String, String)  [API 33+]
* addAlias(String): CapabilityParams.Builder  [API 33+]
* build(): CapabilityParams  [API 33+]
```

## ChangedPackages — declaration; API 26+ (0 +, 5 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 26+]
* ChangedPackages(int, List)  [API 26+]
* describeContents(): int  [API 26+]
* getPackageNames(): List  [API 26+]
* getSequenceNumber(): int  [API 26+]
* writeToParcel(Parcel, int): void  [API 26+]
```

## Checksum — declaration; API 31+ (0 +, 5 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 31+]
* Checksum(int, byte[])  [API 31+]
* describeContents(): int  [API 31+]
* getType(): int  [API 31+]
* getValue(): byte[]  [API 31+]
* writeToParcel(Parcel, int): void  [API 31+]
```

## ComponentInfo — declaration; API 1+ (0 +, 0 *, 0 ~, 10 -)

```
- ComponentInfo()  [API 1+]
- ComponentInfo(ComponentInfo)  [API 1+]
- ComponentInfo(Parcel)  [API 1+]
+ applicationInfo [field]  [API 1+]
+ attributionTags [field]  [API 31+]
+ descriptionRes [field]  [API 8+]
+ directBootAware [field]  [API 24+]
- dumpBack(Printer, String): void  [API 1+]
- dumpFront(Printer, String): void  [API 1+]
+ enabled [field]  [API 1+]
+ exported [field]  [API 1+]
- getBannerResource(): int  [API 20+]
- getIconResource(): int  [API 1+]
- getLogoResource(): int  [API 19+]
- isEnabled(): boolean  [API 11+]
+ processName [field]  [API 1+]
+ splitName [field]  [API 26+]
- writeToParcel(Parcel, int): void  [API 1+]
```

## ConfigurationInfo — framework; API 3+ (2 +, 0 *, 1 ~, 3 -)

```
- CREATOR [field]  [API 3+]
+ ConfigurationInfo()  [API 3+]
- ConfigurationInfo(ConfigurationInfo)  [API 3+]
- describeContents(): int  [API 3+]
~ getGlEsVersion(): String  [API 4+]
+ reqGlEsVersion [field]  [API 4+]
+ reqInputFeatures [field]  [API 3+]
+ reqKeyboardType [field]  [API 3+]
+ reqNavigation [field]  [API 3+]
+ reqTouchScreen [field]  [API 3+]
+ toString(): String  [API 3+]
- writeToParcel(Parcel, int): void  [API 3+]
```

## CrossProfileApps — framework; API 28+ (0 +, 0 *, 0 ~, 12 -)

```
- canInteractAcrossProfiles(): boolean  [API 30+]
- canRequestInteractAcrossProfiles(): boolean  [API 30+]
- createRequestInteractAcrossProfilesIntent(): Intent  [API 30+]
- getProfileSwitchingIconDrawable(UserHandle): Drawable  [API 28+]
- getProfileSwitchingLabel(UserHandle): CharSequence  [API 28+]
- getTargetUserProfiles(): List  [API 28+]
- isManagedProfile(UserHandle): boolean  [API 35+]
- isProfile(UserHandle): boolean  [API 35+]
- startActivity(Intent, UserHandle, Activity): void  [API 30+]
- startActivity(Intent, UserHandle, Activity, Bundle): void  [API 30+]
- startMainActivity(ComponentName, UserHandle): void  [API 28+]
- startMainActivity(ComponentName, UserHandle, Activity, Bundle): void  [API 33+]
```

## FeatureGroupInfo — declaration; API 21+ (0 +, 0 *, 0 ~, 4 -)

```
- CREATOR [field]  [API 21+]
- FeatureGroupInfo()  [API 21+]
- FeatureGroupInfo(FeatureGroupInfo)  [API 21+]
- describeContents(): int  [API 21+]
+ features [field]  [API 21+]
- writeToParcel(Parcel, int): void  [API 21+]
```

## FeatureInfo — declaration; API 5+ (0 +, 0 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 5+]
- FeatureInfo()  [API 5+]
- FeatureInfo(FeatureInfo)  [API 5+]
- describeContents(): int  [API 5+]
+ flags [field]  [API 5+]
- getGlEsVersion(): String  [API 5+]
+ name [field]  [API 5+]
+ reqGlEsVersion [field]  [API 5+]
- toString(): String  [API 5+]
+ version [field]  [API 24+]
- writeToParcel(Parcel, int): void  [API 5+]
```

## IPackageInstallObserver — declaration; API 1-3, removed in 4 (1 +, 0 *, 0 ~, 0 -)

```
+ packageInstalled(String, int): void  [API 1-3, removed in 4]
```

## IPackageInstallObserver.Stub — declaration; API 1-3, removed in 4 (0 +, 0 *, 0 ~, 2 -)

```
- Stub()  [API 1-3, removed in 4]
- asInterface(IBinder): IPackageInstallObserver  [API 1-3, removed in 4]
```

## InstallSourceInfo — declaration; API 30+ (0 +, 0 *, 0 ~, 8 -)

```
- CREATOR [field]  [API 30+]
- describeContents(): int  [API 30+]
- getInitiatingPackageName(): String  [API 30+]
- getInitiatingPackageSigningInfo(): SigningInfo  [API 30+]
- getInstallingPackageName(): String  [API 30+]
- getOriginatingPackageName(): String  [API 30+]
- getPackageSource(): int  [API 33+]
- getUpdateOwnerPackageName(): String  [API 34+]
- writeToParcel(Parcel, int): void  [API 30+]
```

## InstrumentationInfo — declaration; API 1+ (0 +, 0 *, 0 ~, 5 -)

```
- CREATOR [field]  [API 1+]
- InstrumentationInfo()  [API 1+]
- InstrumentationInfo(InstrumentationInfo)  [API 1+]
+ dataDir [field]  [API 1+]
- describeContents(): int  [API 1+]
+ functionalTest [field]  [API 1+]
+ handleProfiling [field]  [API 1+]
+ publicSourceDir [field]  [API 1+]
+ sourceDir [field]  [API 1+]
+ splitNames [field]  [API 26+]
+ splitPublicSourceDirs [field]  [API 21+]
+ splitSourceDirs [field]  [API 21+]
+ targetPackage [field]  [API 1+]
+ targetProcesses [field]  [API 26+]
- toString(): String  [API 1+]
- writeToParcel(Parcel, int): void  [API 1+]
```

## LabeledIntent — declaration; API 5+ (0 +, 0 *, 0 ~, 12 -)

```
- CREATOR [field]  [API 5+]
- LabeledIntent(Intent, String, CharSequence, int)  [API 5+]
- LabeledIntent(Intent, String, int, int)  [API 5+]
- LabeledIntent(String, CharSequence, int)  [API 5+]
- LabeledIntent(String, int, int)  [API 5+]
- getIconResource(): int  [API 5+]
- getLabelResource(): int  [API 5+]
- getNonLocalizedLabel(): CharSequence  [API 5+]
- getSourcePackage(): String  [API 5+]
- loadIcon(PackageManager): Drawable  [API 5+]
- loadLabel(PackageManager): CharSequence  [API 5+]
- readFromParcel(Parcel): void  [API 5+]
- writeToParcel(Parcel, int): void  [API 5+]
```

## LauncherActivityInfo — declaration; API 21+ (0 +, 0 *, 0 ~, 10 -)

```
- getActivityInfo(): ActivityInfo  [API 31+]
- getApplicationInfo(): ApplicationInfo  [API 21+]
- getBadgedIcon(int): Drawable  [API 21+]
- getComponentName(): ComponentName  [API 21+]
- getFirstInstallTime(): long  [API 21+]
- getIcon(int): Drawable  [API 21+]
- getLabel(): CharSequence  [API 21+]
- getLoadingProgress(): float  [API 31+]
- getName(): String  [API 21+]
- getUser(): UserHandle  [API 21+]
```

## LauncherApps — framework; API 21+ (0 +, 0 *, 0 ~, 32 -)

```
- getActivityList(String, UserHandle): List  [API 21+]
- getAllPackageInstallerSessions(): List  [API 29+]
- getAppMarketActivityIntent(String, UserHandle): IntentSender  [API 35+]
- getApplicationInfo(String, int, UserHandle): ApplicationInfo  [API 26+]
- getLauncherUserInfo(UserHandle): LauncherUserInfo  [API 35+]
- getPinItemRequest(Intent): LauncherApps.PinItemRequest  [API 26+]
- getPreInstalledSystemPackages(UserHandle): List  [API 35+]
- getProfiles(): List  [API 26+]
- getShortcutBadgedIconDrawable(ShortcutInfo, int): Drawable  [API 25+]
- getShortcutConfigActivityIntent(LauncherActivityInfo): IntentSender  [API 26+]
- getShortcutConfigActivityList(String, UserHandle): List  [API 26+]
- getShortcutIconDrawable(ShortcutInfo, int): Drawable  [API 25+]
- getShortcutIntent(String, String, Bundle, UserHandle): PendingIntent  [API 31+]
- getShortcuts(LauncherApps.ShortcutQuery, UserHandle): List  [API 25+]
- getSuspendedPackageLauncherExtras(String, UserHandle): Bundle  [API 28+]
- hasShortcutHostPermission(): boolean  [API 25+]
- isActivityEnabled(ComponentName, UserHandle): boolean  [API 21+]
- isPackageEnabled(String, UserHandle): boolean  [API 21+]
- pinShortcuts(String, List, UserHandle): void  [API 25+]
- registerCallback(LauncherApps.Callback): void  [API 21+]
- registerCallback(LauncherApps.Callback, Handler): void  [API 21+]
- registerPackageInstallerSessionCallback(Executor, PackageInstaller.SessionCallback): void  [API 29+]
- resolveActivity(Intent, UserHandle): LauncherActivityInfo  [API 21+]
- setArchiveCompatibility(LauncherApps.ArchiveCompatibilityParams): void  [API 35+]
- shouldHideFromSuggestions(String, UserHandle): boolean  [API 29+]
- startAppDetailsActivity(ComponentName, UserHandle, Rect, Bundle): void  [API 21+]
- startMainActivity(ComponentName, UserHandle, Rect, Bundle): void  [API 21+]
- startPackageInstallerSessionDetailsActivity(PackageInstaller.SessionInfo, Rect, Bundle): void  [API 29+]
- startShortcut(ShortcutInfo, Rect, Bundle): void  [API 25+]
- startShortcut(String, String, Rect, Bundle, UserHandle): void  [API 25+]
- unregisterCallback(LauncherApps.Callback): void  [API 21+]
- unregisterPackageInstallerSessionCallback(PackageInstaller.SessionCallback): void  [API 29+]
```

## LauncherApps.ArchiveCompatibilityParams — framework; API 35+ (0 +, 0 *, 0 ~, 3 -)

```
- ArchiveCompatibilityParams()  [API 35+]
- setEnableIconOverlay(boolean): void  [API 35+]
- setEnableUnarchivalConfirmation(boolean): void  [API 35+]
```

## LauncherApps.Callback — framework; API 21+ (11 +, 0 *, 0 ~, 0 -)

```
+ Callback()  [API 21+]
+ onPackageAdded(String, UserHandle): void  [API 21+]
+ onPackageChanged(String, UserHandle): void  [API 21+]
+ onPackageLoadingProgressChanged(String, UserHandle, float): void  [API 31+]
+ onPackageRemoved(String, UserHandle): void  [API 21+]
+ onPackagesAvailable(String[], UserHandle, boolean): void  [API 21+]
+ onPackagesSuspended(String[], UserHandle): void  [API 24+]
+ onPackagesSuspended(String[], UserHandle, Bundle): void  [API 28+, deprecated in 30]
+ onPackagesUnavailable(String[], UserHandle, boolean): void  [API 21+]
+ onPackagesUnsuspended(String[], UserHandle): void  [API 24+]
+ onShortcutsChanged(String, List, UserHandle): void  [API 25+]
```

## LauncherApps.PinItemRequest — framework; API 26+ (0 +, 0 *, 0 ~, 9 -)

```
- CREATOR [field]  [API 26+]
- accept(): boolean  [API 26+]
- accept(Bundle): boolean  [API 26+]
- describeContents(): int  [API 26+]
- getAppWidgetProviderInfo(Context): AppWidgetProviderInfo  [API 26+]
- getExtras(): Bundle  [API 26+]
- getRequestType(): int  [API 26+]
- getShortcutInfo(): ShortcutInfo  [API 26+]
- isValid(): boolean  [API 26+]
- writeToParcel(Parcel, int): void  [API 26+]
```

## LauncherApps.ShortcutQuery — framework; API 25+ (0 +, 0 *, 0 ~, 7 -)

```
- ShortcutQuery()  [API 25+]
- setActivity(ComponentName): LauncherApps.ShortcutQuery  [API 25+]
- setChangedSince(long): LauncherApps.ShortcutQuery  [API 25+]
- setLocusIds(List): LauncherApps.ShortcutQuery  [API 30+]
- setPackage(String): LauncherApps.ShortcutQuery  [API 25+]
- setQueryFlags(int): LauncherApps.ShortcutQuery  [API 25+]
- setShortcutIds(List): LauncherApps.ShortcutQuery  [API 25+]
```

## LauncherUserInfo — declaration; API 35+ (0 +, 0 *, 0 ~, 4 -)

```
- CREATOR [field]  [API 35+]
- describeContents(): int  [API 35+]
- getUserSerialNumber(): int  [API 35+]
- getUserType(): String  [API 35+]
- writeToParcel(Parcel, int): void  [API 35+]
```

## ModuleInfo — declaration; API 29+ (0 +, 0 *, 0 ~, 8 -)

```
- CREATOR [field]  [API 29+]
- describeContents(): int  [API 29+]
- equals(Object): boolean  [API 29+]
- getName(): CharSequence  [API 29+]
- getPackageName(): String  [API 29+]
- hashCode(): int  [API 29+]
- isHidden(): boolean  [API 29+]
- toString(): String  [API 29+]
- writeToParcel(Parcel, int): void  [API 29+]
```

## PackageInfo — framework; API 1+ (2 +, 0 *, 2 ~, 4 -)

```
- CREATOR [field]  [API 1+]
+ PackageInfo()  [API 1+]
+ activities [field]  [API 1+]
+ applicationInfo [field]  [API 1+]
- attributions [field]  [API 31+]
- baseRevisionCode [field]  [API 22+]
- configPreferences [field]  [API 3+]
~ describeContents(): int  [API 1+]
- featureGroups [field]  [API 21+]
+ firstInstallTime [field]  [API 9+]
- getApexPackageName(): String  [API 35+]
- getArchiveTimeMillis(): long  [API 35+]
- getLongVersionCode(): long  [API 28+]
- gids [field]  [API 1+]
- installLocation [field]  [API 21+]
- instrumentation [field]  [API 1+]
- isApex [field]  [API 29+]
+ lastUpdateTime [field]  [API 9+]
+ packageName [field]  [API 1+]
- permissions [field]  [API 1+]
- providers [field]  [API 1+]
- receivers [field]  [API 1+]
- reqFeatures [field]  [API 5+]
+ requestedPermissions [field]  [API 1+]
- requestedPermissionsFlags [field]  [API 16+]
- services [field]  [API 1+]
- setLongVersionCode(long): void  [API 28+]
+ sharedUserId [field]  [API 3+]
- sharedUserLabel [field]  [API 3+]
+ signatures [field]  [API 1+, deprecated in 28]
- signingInfo [field]  [API 28+]
- splitNames [field]  [API 21+]
- splitRevisionCodes [field]  [API 22+]
+ toString(): String  [API 1+]
+ versionCode [field]  [API 1+, deprecated in 28]
+ versionName [field]  [API 1+]
~ writeToParcel(Parcel, int): void  [API 1+]
```

## PackageInstaller — declaration; API 21+ (0 +, 0 *, 0 ~, 27 -)

```
- abandonSession(int): void  [API 21+]
- checkInstallConstraints(List, PackageInstaller.InstallConstraints, Executor, Consumer): void  [API 34+]
- commitSessionAfterInstallConstraintsAreMet(int, IntentSender, PackageInstaller.InstallConstraints, long): void  [API 34+]
- createSession(PackageInstaller.SessionParams): int  [API 21+]
- getActiveStagedSession(): PackageInstaller.SessionInfo  [API 29+, deprecated in 30]
- getActiveStagedSessions(): List  [API 30+]
- getAllSessions(): List  [API 21+]
- getMySessions(): List  [API 21+]
- getSessionInfo(int): PackageInstaller.SessionInfo  [API 21+]
- getStagedSessions(): List  [API 29+]
- installExistingPackage(String, int, IntentSender): void  [API 29+]
- installPackageArchived(ArchivedPackageInfo, PackageInstaller.SessionParams, IntentSender): void  [API 35+]
- openSession(int): PackageInstaller.Session  [API 21+]
- registerSessionCallback(PackageInstaller.SessionCallback): void  [API 21+]
- registerSessionCallback(PackageInstaller.SessionCallback, Handler): void  [API 21+]
- reportUnarchivalState(PackageInstaller.UnarchivalState): void  [API 35+]
- reportUnarchivalStatus(int, int, long, PendingIntent): void  [API 35+]
- requestArchive(String, IntentSender): void  [API 35+]
- requestUnarchive(String, IntentSender): void  [API 35+]
- uninstall(String, IntentSender): void  [API 21+]
- uninstall(VersionedPackage, IntentSender): void  [API 26+]
- uninstall(VersionedPackage, int, IntentSender): void  [API 34+]
- uninstallExistingPackage(String, IntentSender): void  [API 31+]
- unregisterSessionCallback(PackageInstaller.SessionCallback): void  [API 21+]
- updateSessionAppIcon(int, Bitmap): void  [API 21+]
- updateSessionAppLabel(int, CharSequence): void  [API 21+]
- waitForInstallConstraints(List, PackageInstaller.InstallConstraints, IntentSender, long): void  [API 34+]
```

## PackageInstaller.InstallConstraints — declaration; API 34+ (0 +, 0 *, 0 ~, 9 -)

```
- CREATOR [field]  [API 34+]
- GENTLE_UPDATE [field]  [API 34+]
- describeContents(): int  [API 34+]
- equals(Object): boolean  [API 34+]
- hashCode(): int  [API 34+]
- isAppNotForegroundRequired(): boolean  [API 34+]
- isAppNotInteractingRequired(): boolean  [API 34+]
- isAppNotTopVisibleRequired(): boolean  [API 34+]
- isDeviceIdleRequired(): boolean  [API 34+]
- isNotInCallRequired(): boolean  [API 34+]
- writeToParcel(Parcel, int): void  [API 34+]
```

## PackageInstaller.InstallConstraints.Builder — declaration; API 34+ (0 +, 0 *, 0 ~, 7 -)

```
- Builder()  [API 34+]
- build(): PackageInstaller.InstallConstraints  [API 34+]
- setAppNotForegroundRequired(): PackageInstaller.InstallConstraints.Builder  [API 34+]
- setAppNotInteractingRequired(): PackageInstaller.InstallConstraints.Builder  [API 34+]
- setAppNotTopVisibleRequired(): PackageInstaller.InstallConstraints.Builder  [API 34+]
- setDeviceIdleRequired(): PackageInstaller.InstallConstraints.Builder  [API 34+]
- setNotInCallRequired(): PackageInstaller.InstallConstraints.Builder  [API 34+]
```

## PackageInstaller.InstallConstraintsResult — declaration; API 34+ (0 +, 0 *, 0 ~, 3 -)

```
- CREATOR [field]  [API 34+]
- areAllConstraintsSatisfied(): boolean  [API 34+]
- describeContents(): int  [API 34+]
- writeToParcel(Parcel, int): void  [API 34+]
```

## PackageInstaller.PreapprovalDetails — declaration; API 34+ (0 +, 7 *, 0 ~, 0 -)

```
+ CREATOR [field]  [API 34+]
* describeContents(): int  [API 34+]
* getIcon(): Bitmap  [API 34+]
* getLabel(): CharSequence  [API 34+]
* getLocale(): ULocale  [API 34+]
* getPackageName(): String  [API 34+]
* toString(): String  [API 34+]
* writeToParcel(Parcel, int): void  [API 34+]
```

## PackageInstaller.PreapprovalDetails.Builder — declaration; API 34+ (0 +, 6 *, 0 ~, 0 -)

```
* Builder()  [API 34+]
* build(): PackageInstaller.PreapprovalDetails  [API 34+]
* setIcon(Bitmap): PackageInstaller.PreapprovalDetails.Builder  [API 34+]
* setLabel(CharSequence): PackageInstaller.PreapprovalDetails.Builder  [API 34+]
* setLocale(ULocale): PackageInstaller.PreapprovalDetails.Builder  [API 34+]
* setPackageName(String): PackageInstaller.PreapprovalDetails.Builder  [API 34+]
```

## PackageInstaller.Session — declaration; API 21+ (0 +, 0 *, 0 ~, 23 -)

```
- abandon(): void  [API 21+]
- addChildSessionId(int): void  [API 29+]
- close(): void  [API 21+]
- commit(IntentSender): void  [API 21+]
- fsync(OutputStream): void  [API 21+]
- getAppMetadata(): PersistableBundle  [API 34+]
- getChildSessionIds(): int[]  [API 29+]
- getNames(): String[]  [API 21+]
- getParentSessionId(): int  [API 29+]
- isApplicationEnabledSettingPersistent(): boolean  [API 34+]
- isMultiPackage(): boolean  [API 29+]
- isRequestUpdateOwnership(): boolean  [API 34+]
- isStaged(): boolean  [API 29+]
- openRead(String): InputStream  [API 21+]
- openWrite(String, long, long): OutputStream  [API 21+]
- removeChildSessionId(int): void  [API 29+]
- removeSplit(String): void  [API 24+]
- requestChecksums(String, int, List, Executor, PackageManager.OnChecksumsReadyListener): void  [API 33+]
- requestUserPreapproval(PackageInstaller.PreapprovalDetails, IntentSender): void  [API 34+]
- setAppMetadata(PersistableBundle): void  [API 34+]
- setChecksums(String, List, byte[]): void  [API 31+, deprecated in 31]
- setStagingProgress(float): void  [API 21+]
- transfer(String): void  [API 27+]
```

## PackageInstaller.SessionCallback — declaration; API 21+ (6 +, 0 *, 0 ~, 0 -)

```
+ SessionCallback()  [API 21+]
+ onActiveChanged(int, boolean): void  [API 21+]
+ onBadgingChanged(int): void  [API 21+]
+ onCreated(int): void  [API 21+]
+ onFinished(int, boolean): void  [API 21+]
+ onProgressChanged(int, float): void  [API 21+]
```

## PackageInstaller.SessionInfo — declaration; API 21+ (0 +, 0 *, 0 ~, 42 -)

```
- CREATOR [field]  [API 21+]
- createDetailsIntent(): Intent  [API 21+]
- describeContents(): int  [API 21+]
- getAppIcon(): Bitmap  [API 21+]
- getAppLabel(): CharSequence  [API 21+]
- getAppPackageName(): String  [API 21+]
- getChildSessionIds(): int[]  [API 29+]
- getCreatedMillis(): long  [API 30+]
- getDontKillApp(): boolean  [API 34+]
- getInstallLocation(): int  [API 27+]
- getInstallReason(): int  [API 26+]
- getInstallerAttributionTag(): String  [API 31+]
- getInstallerPackageName(): String  [API 21+]
- getInstallerUid(): int  [API 34+]
- getMode(): int  [API 27+]
- getOriginatingUid(): int  [API 27+]
- getOriginatingUri(): Uri  [API 27+]
- getPackageSource(): int  [API 33+]
- getParentSessionId(): int  [API 29+]
- getProgress(): float  [API 21+]
- getReferrerUri(): Uri  [API 27+]
- getRequireUserAction(): int  [API 31+]
- getSessionId(): int  [API 21+]
- getSize(): long  [API 27+]
- getStagedSessionErrorCode(): int  [API 29+]
- getStagedSessionErrorMessage(): String  [API 29+]
- getUpdatedMillis(): long  [API 29+]
- getUser(): UserHandle  [API 29+]
- hasParentSessionId(): boolean  [API 30+]
- isActive(): boolean  [API 21+]
- isApplicationEnabledSettingPersistent(): boolean  [API 34+]
- isCommitted(): boolean  [API 29+]
- isMultiPackage(): boolean  [API 29+]
- isPreApprovalRequested(): boolean  [API 34+]
- isRequestUpdateOwnership(): boolean  [API 34+]
- isSealed(): boolean  [API 26+]
- isStaged(): boolean  [API 29+]
- isStagedSessionActive(): boolean  [API 30+]
- isStagedSessionApplied(): boolean  [API 29+]
- isStagedSessionFailed(): boolean  [API 29+]
- isStagedSessionReady(): boolean  [API 29+]
- isUnarchival(): boolean  [API 35+]
- writeToParcel(Parcel, int): void  [API 21+]
```

## PackageInstaller.SessionParams — declaration; API 21+ (0 +, 0 *, 0 ~, 24 -)

```
- CREATOR [field]  [API 21+]
- RESTRICTED_PERMISSIONS_ALL [field]  [API 29+]
- SessionParams(int)  [API 21+]
- describeContents(): int  [API 21+]
- setAppIcon(Bitmap): void  [API 21+]
- setAppLabel(CharSequence): void  [API 21+]
- setAppPackageName(String): void  [API 21+]
- setApplicationEnabledSettingPersistent(): void  [API 34+]
- setAutoRevokePermissionsMode(boolean): void  [API 30+, deprecated in 31]
- setDontKillApp(boolean): void  [API 34+]
- setInstallLocation(int): void  [API 21+]
- setInstallReason(int): void  [API 26+]
- setInstallScenario(int): void  [API 31+]
- setInstallerPackageName(String): void  [API 34+]
- setMultiPackage(): void  [API 29+]
- setOriginatingUid(int): void  [API 24+]
- setOriginatingUri(Uri): void  [API 21+]
- setPackageSource(int): void  [API 33+]
- setPermissionState(String, int): PackageInstaller.SessionParams  [API 34+]
- setReferrerUri(Uri): void  [API 21+]
- setRequestUpdateOwnership(boolean): void  [API 34+]
- setRequireUserAction(int): void  [API 31+]
- setSize(long): void  [API 21+]
- setUnarchiveId(int): void  [API 35+]
- setWhitelistedRestrictedPermissions(Set): void  [API 29+]
- writeToParcel(Parcel, int): void  [API 21+]
```

## PackageInstaller.UnarchivalState — declaration; API 35+ (0 +, 0 *, 0 ~, 5 -)

```
- createGenericErrorState(int): PackageInstaller.UnarchivalState [static]  [API 35+]
- createInsufficientStorageState(int, long, PendingIntent): PackageInstaller.UnarchivalState [static]  [API 35+]
- createNoConnectivityState(int): PackageInstaller.UnarchivalState [static]  [API 35+]
- createOkState(int): PackageInstaller.UnarchivalState [static]  [API 35+]
- createUserActionRequiredState(int, PendingIntent): PackageInstaller.UnarchivalState [static]  [API 35+]
```

## PackageItemInfo — framework; API 1+ (6 +, 0 *, 0 ~, 6 -)

```
+ PackageItemInfo()  [API 1+]
+ PackageItemInfo(PackageItemInfo)  [API 1+]
- PackageItemInfo(Parcel)  [API 1+]
+ banner [field]  [API 20+]
- dumpBack(Printer, String): void  [API 1+]
- dumpFront(Printer, String): void  [API 1+]
+ icon [field]  [API 1+]
- isArchived [field]  [API 35+]
+ labelRes [field]  [API 1+]
- loadBanner(PackageManager): Drawable  [API 20+]
+ loadIcon(PackageManager): Drawable  [API 1+]
+ loadLabel(PackageManager): CharSequence  [API 1+]
+ loadLogo(PackageManager): Drawable  [API 9+]
- loadUnbadgedIcon(PackageManager): Drawable  [API 22+]
+ loadXmlMetaData(PackageManager, String): XmlResourceParser  [API 1+]
+ logo [field]  [API 9+]
+ metaData [field]  [API 1+]
+ name [field]  [API 1+]
+ nonLocalizedLabel [field]  [API 1+]
+ packageName [field]  [API 1+]
- writeToParcel(Parcel, int): void  [API 1+]
```

## PackageItemInfo.DisplayNameComparator — declaration; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- DisplayNameComparator(PackageManager)  [API 1+]
- compare(PackageItemInfo, PackageItemInfo): int  [API 1+]
```

## PackageManager — framework; API 1+ (23 +, 0 *, 4 ~, 136 -)

```
- PackageManager()  [API 1+, deprecated in 31]
- TRUST_ALL [field]  [API 31+]
- TRUST_NONE [field]  [API 31+]
- addPackageToPreferred(String): void  [API 1+, deprecated in 15]
- addPermission(PermissionInfo): boolean  [API 1+]
- addPermissionAsync(PermissionInfo): boolean  [API 8+]
- addPreferredActivity(IntentFilter, int, ComponentName[], ComponentName): void  [API 1+, deprecated in 15]
- addWhitelistedRestrictedPermission(String, String, int): boolean  [API 29+]
- canPackageQuery(String, String): boolean  [API 33+]
- canPackageQuery(String, String[]): boolean[]  [API 34+]
- canRequestPackageInstalls(): boolean  [API 26+]
- canonicalToCurrentPackageNames(String[]): String[]  [API 8+]
~ checkPermission(String, String): int  [API 1+]
- checkSignatures(String, String): int  [API 1+]
- checkSignatures(int, int): int  [API 5+]
- clearInstantAppCookie(): void  [API 26+]
- clearPackagePreferredActivities(String): void  [API 1+, deprecated in 29]
- currentToCanonicalPackageNames(String[]): String[]  [API 8+]
- extendVerificationTimeout(int, int, long): void  [API 17+]
- getActivityBanner(ComponentName): Drawable  [API 20+]
- getActivityBanner(Intent): Drawable  [API 20+]
- getActivityIcon(ComponentName): Drawable  [API 1+]
- getActivityIcon(Intent): Drawable  [API 1+]
- getActivityInfo(ComponentName, PackageManager.ComponentInfoFlags): ActivityInfo  [API 33+]
+ getActivityInfo(ComponentName, int): ActivityInfo  [API 1+]
- getActivityLogo(ComponentName): Drawable  [API 9+]
- getActivityLogo(Intent): Drawable  [API 9+]
- getAllPermissionGroups(int): List  [API 1+]
- getApplicationBanner(ApplicationInfo): Drawable  [API 20+]
- getApplicationBanner(String): Drawable  [API 20+]
- getApplicationEnabledSetting(String): int  [API 1+]
+ getApplicationIcon(ApplicationInfo): Drawable  [API 1+]
+ getApplicationIcon(String): Drawable  [API 1+]
- getApplicationInfo(String, PackageManager.ApplicationInfoFlags): ApplicationInfo  [API 33+]
+ getApplicationInfo(String, int): ApplicationInfo  [API 1+]
+ getApplicationLabel(ApplicationInfo): CharSequence  [API 1+]
- getApplicationLogo(ApplicationInfo): Drawable  [API 9+]
- getApplicationLogo(String): Drawable  [API 9+]
- getArchivedPackage(String): ArchivedPackageInfo  [API 35+]
- getBackgroundPermissionOptionLabel(): CharSequence  [API 30+]
- getChangedPackages(int): ChangedPackages  [API 26+]
~ getComponentEnabledSetting(ComponentName): int  [API 1+]
+ getDefaultActivityIcon(): Drawable  [API 1+]
- getDrawable(String, int, ApplicationInfo): Drawable  [API 1+]
- getGroupOfPlatformPermission(String, Executor, Consumer): void  [API 31+]
- getInstallSourceInfo(String): InstallSourceInfo  [API 30+]
- getInstalledApplications(PackageManager.ApplicationInfoFlags): List  [API 33+]
+ getInstalledApplications(int): List  [API 1+]
- getInstalledModules(int): List  [API 29+]
- getInstalledPackages(PackageManager.PackageInfoFlags): List  [API 33+]
+ getInstalledPackages(int): List  [API 1+]
~ getInstallerPackageName(String): String  [API 5+, deprecated in 30]
- getInstantAppCookie(): byte[]  [API 26+]
- getInstantAppCookieMaxBytes(): int  [API 26+]
- getInstrumentationInfo(ComponentName, int): InstrumentationInfo  [API 1+]
+ getLaunchIntentForPackage(String): Intent  [API 3+]
- getLaunchIntentSenderForPackage(String): IntentSender  [API 33+]
- getLeanbackLaunchIntentForPackage(String): Intent  [API 21+]
- getMimeGroup(String): Set  [API 30+]
- getModuleInfo(String, int): ModuleInfo  [API 29+]
- getNameForUid(int): String  [API 1+]
- getPackageArchiveInfo(String, PackageManager.PackageInfoFlags): PackageInfo  [API 33+]
- getPackageArchiveInfo(String, int): PackageInfo  [API 1+]
- getPackageGids(String): int[]  [API 1+]
- getPackageGids(String, PackageManager.PackageInfoFlags): int[]  [API 33+]
- getPackageGids(String, int): int[]  [API 24+]
- getPackageInfo(String, PackageManager.PackageInfoFlags): PackageInfo  [API 33+]
+ getPackageInfo(String, int): PackageInfo  [API 1+]
- getPackageInfo(VersionedPackage, PackageManager.PackageInfoFlags): PackageInfo  [API 33+]
- getPackageInfo(VersionedPackage, int): PackageInfo  [API 26+]
- getPackageInstaller(): PackageInstaller  [API 21+]
- getPackageUid(String, PackageManager.PackageInfoFlags): int  [API 33+]
- getPackageUid(String, int): int  [API 24+]
- getPackagesForUid(int): String[]  [API 1+]
- getPackagesHoldingPermissions(String[], PackageManager.PackageInfoFlags): List  [API 33+]
- getPackagesHoldingPermissions(String[], int): List  [API 18+]
- getPermissionGroupInfo(String, int): PermissionGroupInfo  [API 1+]
- getPermissionInfo(String, int): PermissionInfo  [API 1+]
- getPlatformPermissionsForGroup(String, Executor, Consumer): void  [API 31+]
- getPreferredActivities(List, List, String): int  [API 1+, deprecated in 29]
- getPreferredPackages(int): List  [API 1+, deprecated in 29]
- getProperty(String, ComponentName): PackageManager.Property  [API 31+]
- getProperty(String, String): PackageManager.Property  [API 31+]
- getProviderInfo(ComponentName, PackageManager.ComponentInfoFlags): ProviderInfo  [API 33+]
+ getProviderInfo(ComponentName, int): ProviderInfo  [API 9+]
- getReceiverInfo(ComponentName, PackageManager.ComponentInfoFlags): ActivityInfo  [API 33+]
+ getReceiverInfo(ComponentName, int): ActivityInfo  [API 1+]
+ getResourcesForActivity(ComponentName): Resources  [API 1+]
+ getResourcesForApplication(ApplicationInfo): Resources  [API 1+]
- getResourcesForApplication(ApplicationInfo, Configuration): Resources  [API 31+]
+ getResourcesForApplication(String): Resources  [API 1+]
- getServiceInfo(ComponentName, PackageManager.ComponentInfoFlags): ServiceInfo  [API 33+]
+ getServiceInfo(ComponentName, int): ServiceInfo  [API 1+]
- getSharedLibraries(PackageManager.PackageInfoFlags): List  [API 33+]
- getSharedLibraries(int): List  [API 26+]
- getSuspendedPackageAppExtras(): Bundle  [API 28+]
- getSyntheticAppDetailsActivityEnabled(String): boolean  [API 29+]
- getSystemAvailableFeatures(): FeatureInfo[]  [API 5+]
- getSystemSharedLibraryNames(): String[]  [API 3+]
- getTargetSdkVersion(String): int  [API 31+]
- getText(String, int, ApplicationInfo): CharSequence  [API 1+]
- getUserBadgedDrawableForDensity(Drawable, UserHandle, Rect, int): Drawable  [API 21+]
- getUserBadgedIcon(Drawable, UserHandle): Drawable  [API 21+]
- getUserBadgedLabel(CharSequence, UserHandle): CharSequence  [API 21+]
- getVerifiedSigningInfo(String, int): SigningInfo  [API 36+]
- getWhitelistedRestrictedPermissions(String, int): Set  [API 29+]
- getXml(String, int, ApplicationInfo): XmlResourceParser  [API 1+]
- hasSigningCertificate(String, byte[], int): boolean  [API 28+]
- hasSigningCertificate(int, byte[], int): boolean  [API 28+]
+ hasSystemFeature(String): boolean  [API 5+]
- hasSystemFeature(String, int): boolean  [API 24+]
- installPackage(Uri): void  [API 1-3, removed in 4]
- installPackage(Uri, IPackageInstallObserver, int): void  [API 1-3, removed in 4]
- isAppArchivable(String): boolean  [API 35+]
- isAutoRevokeWhitelisted(): boolean  [API 30+]
- isAutoRevokeWhitelisted(String): boolean  [API 30+]
- isDefaultApplicationIcon(Drawable): boolean  [API 30+]
- isDeviceUpgrading(): boolean  [API 29+]
- isInstantApp(): boolean  [API 26+]
- isInstantApp(String): boolean  [API 26+]
- isPackageStopped(String): boolean  [API 35+]
- isPackageSuspended(): boolean  [API 28+]
- isPackageSuspended(String): boolean  [API 29+]
- isPermissionRevokedByPolicy(String, String): boolean  [API 23+]
- isSafeMode(): boolean  [API 3+]
- parseAndroidManifest(File, Function): Object  [API 35+]
- queryActivityProperty(String): List  [API 31+]
- queryApplicationProperty(String): List  [API 31+]
- queryBroadcastReceivers(Intent, PackageManager.ResolveInfoFlags): List  [API 33+]
+ queryBroadcastReceivers(Intent, int): List  [API 1+]
- queryContentProviders(String, int, PackageManager.ComponentInfoFlags): List  [API 33+]
- queryContentProviders(String, int, int): List  [API 1+]
- queryInstrumentation(String, int): List  [API 1+]
- queryIntentActivities(Intent, PackageManager.ResolveInfoFlags): List  [API 33+]
+ queryIntentActivities(Intent, int): List  [API 1+]
- queryIntentActivityOptions(ComponentName, Intent[], Intent, int): List  [API 1+]
- queryIntentActivityOptions(ComponentName, List, Intent, PackageManager.ResolveInfoFlags): List  [API 33+]
- queryIntentContentProviders(Intent, PackageManager.ResolveInfoFlags): List  [API 33+]
+ queryIntentContentProviders(Intent, int): List  [API 19+]
- queryIntentServices(Intent, PackageManager.ResolveInfoFlags): List  [API 33+]
+ queryIntentServices(Intent, int): List  [API 1+]
- queryPermissionsByGroup(String, int): List  [API 1+]
- queryProviderProperty(String): List  [API 31+]
- queryReceiverProperty(String): List  [API 31+]
- queryServiceProperty(String): List  [API 31+]
- relinquishUpdateOwnership(String): void  [API 34+]
- removePackageFromPreferred(String): void  [API 1+, deprecated in 15]
- removePermission(String): void  [API 1+]
- removeWhitelistedRestrictedPermission(String, String, int): boolean  [API 29+]
- requestChecksums(String, boolean, int, List, PackageManager.OnChecksumsReadyListener): void  [API 31+]
- resolveActivity(Intent, PackageManager.ResolveInfoFlags): ResolveInfo  [API 33+]
+ resolveActivity(Intent, int): ResolveInfo  [API 1+]
- resolveContentProvider(String, PackageManager.ComponentInfoFlags): ProviderInfo  [API 33+]
- resolveContentProvider(String, int): ProviderInfo  [API 1+]
- resolveService(Intent, PackageManager.ResolveInfoFlags): ResolveInfo  [API 33+]
+ resolveService(Intent, int): ResolveInfo  [API 1+]
- setApplicationCategoryHint(String, int): void  [API 26+]
- setApplicationEnabledSetting(String, int, int): void  [API 1+]
- setAutoRevokeWhitelisted(String, boolean): boolean  [API 30+]
~ setComponentEnabledSetting(ComponentName, int, int): void  [API 1+]
- setComponentEnabledSettings(List): void  [API 33+]
- setInstallerPackageName(String, String): void  [API 11+]
- setMimeGroup(String, Set): void  [API 30+]
- updateInstantAppCookie(byte[]): void  [API 26+]
- verifyPendingInstall(int, int): void  [API 14+]
```

## PackageManager.ApplicationInfoFlags — declaration; API 33+ (0 +, 0 *, 0 ~, 2 -)

```
- getValue(): long  [API 33+]
- of(long): PackageManager.ApplicationInfoFlags [static]  [API 33+]
```

## PackageManager.ComponentEnabledSetting — declaration; API 33+ (0 +, 0 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 33+]
- ComponentEnabledSetting(ComponentName, int, int)  [API 33+]
- describeContents(): int  [API 33+]
- getComponentName(): ComponentName  [API 33+]
- getEnabledFlags(): int  [API 33+]
- getEnabledState(): int  [API 33+]
- writeToParcel(Parcel, int): void  [API 33+]
```

## PackageManager.ComponentInfoFlags — declaration; API 33+ (0 +, 0 *, 0 ~, 2 -)

```
- getValue(): long  [API 33+]
- of(long): PackageManager.ComponentInfoFlags [static]  [API 33+]
```

## PackageManager.NameNotFoundException — framework; API 1+ (2 +, 0 *, 0 ~, 0 -)

```
+ NameNotFoundException()  [API 1+]
+ NameNotFoundException(String)  [API 1+]
```

## PackageManager.OnChecksumsReadyListener — declaration; API 31+ (1 +, 0 *, 0 ~, 0 -)

```
+ onChecksumsReady(List): void  [API 31+]
```

## PackageManager.PackageInfoFlags — declaration; API 33+ (0 +, 0 *, 0 ~, 2 -)

```
- getValue(): long  [API 33+]
- of(long): PackageManager.PackageInfoFlags [static]  [API 33+]
```

## PackageManager.Property — declaration; API 31+ (0 +, 0 *, 0 ~, 15 -)

```
- CREATOR [field]  [API 31+]
- describeContents(): int  [API 31+]
- getBoolean(): boolean  [API 31+]
- getClassName(): String  [API 31+]
- getFloat(): float  [API 31+]
- getInteger(): int  [API 31+]
- getName(): String  [API 31+]
- getPackageName(): String  [API 31+]
- getResourceId(): int  [API 31+]
- getString(): String  [API 31+]
- isBoolean(): boolean  [API 31+]
- isFloat(): boolean  [API 31+]
- isInteger(): boolean  [API 31+]
- isResourceId(): boolean  [API 31+]
- isString(): boolean  [API 31+]
- writeToParcel(Parcel, int): void  [API 31+]
```

## PackageManager.ResolveInfoFlags — declaration; API 33+ (0 +, 0 *, 0 ~, 2 -)

```
- getValue(): long  [API 33+]
- of(long): PackageManager.ResolveInfoFlags [static]  [API 33+]
```

## PackageStats — declaration; API 1+, deprecated in 26 (0 +, 0 *, 0 ~, 8 -)

```
- CREATOR [field]  [API 1+]
- PackageStats(PackageStats)  [API 1+]
- PackageStats(Parcel)  [API 1+]
- PackageStats(String)  [API 1+]
+ cacheSize [field]  [API 1+]
+ codeSize [field]  [API 1+]
+ dataSize [field]  [API 1+]
- describeContents(): int  [API 1+]
- equals(Object): boolean  [API 1+]
+ externalCacheSize [field]  [API 11+]
+ externalCodeSize [field]  [API 14+]
+ externalDataSize [field]  [API 11+]
+ externalMediaSize [field]  [API 11+]
+ externalObbSize [field]  [API 11+]
- hashCode(): int  [API 1+]
+ packageName [field]  [API 1+]
- toString(): String  [API 1+]
- writeToParcel(Parcel, int): void  [API 1+]
```

## PathPermission — framework; API 4+ (4 +, 0 *, 0 ~, 1 -)

```
+ CREATOR [field]  [API 4+]
- PathPermission(Parcel)  [API 4+]
+ PathPermission(String, int, String, String)  [API 4+]
+ getReadPermission(): String  [API 4+]
+ getWritePermission(): String  [API 4+]
+ writeToParcel(Parcel, int): void  [API 4+]
```

## PermissionGroupInfo — declaration; API 1+ (0 +, 0 *, 0 ~, 6 -)

```
- CREATOR [field]  [API 1+]
- PermissionGroupInfo()  [API 1+, deprecated in 29]
- PermissionGroupInfo(PermissionGroupInfo)  [API 1+, deprecated in 29]
- describeContents(): int  [API 1+]
+ descriptionRes [field]  [API 1+]
+ flags [field]  [API 17+]
- loadDescription(PackageManager): CharSequence  [API 1+]
+ nonLocalizedDescription [field]  [API 1+]
+ priority [field]  [API 17+]
- toString(): String  [API 1+]
- writeToParcel(Parcel, int): void  [API 1+]
```

## PermissionInfo — declaration; API 1+ (0 +, 0 *, 0 ~, 8 -)

```
- CREATOR [field]  [API 1+]
- PermissionInfo()  [API 1+, deprecated in 29]
- PermissionInfo(PermissionInfo)  [API 1+, deprecated in 29]
- describeContents(): int  [API 1+]
+ descriptionRes [field]  [API 1+]
+ flags [field]  [API 17+]
- getProtection(): int  [API 28+]
- getProtectionFlags(): int  [API 28+]
+ group [field]  [API 1+]
- loadDescription(PackageManager): CharSequence  [API 1+]
+ nonLocalizedDescription [field]  [API 1+]
+ protectionLevel [field]  [API 1+, deprecated in 28]
- toString(): String  [API 1+]
- writeToParcel(Parcel, int): void  [API 1+]
```

## ProviderInfo — framework; API 1+ (2 +, 0 *, 0 ~, 4 -)

```
- CREATOR [field]  [API 1+]
+ ProviderInfo()  [API 1+]
- ProviderInfo(ProviderInfo)  [API 1+]
+ authority [field]  [API 1+]
- describeContents(): int  [API 1+]
- dump(Printer, String): void  [API 19+]
- flags [field]  [API 17+]
- forceUriPermissions [field]  [API 29+]
+ grantUriPermissions [field]  [API 1+]
+ initOrder [field]  [API 1+]
- isSyncable [field]  [API 1+, deprecated in 15]
+ multiprocess [field]  [API 1+]
- pathPermissions [field]  [API 4+]
+ readPermission [field]  [API 1+]
+ toString(): String  [API 1+]
- uriPermissionPatterns [field]  [API 1+]
+ writePermission [field]  [API 1+]
- writeToParcel(Parcel, int): void  [API 1+]
```

## ResolveInfo — framework; API 1+ (6 +, 0 *, 0 ~, 4 -)

```
- CREATOR [field]  [API 1+]
+ ResolveInfo()  [API 1+]
+ ResolveInfo(ResolveInfo)  [API 17+]
+ activityInfo [field]  [API 1+]
- describeContents(): int  [API 1+]
- dump(Printer, String): void  [API 1+]
+ filter [field]  [API 1+]
+ getIconResource(): int  [API 1+]
+ icon [field]  [API 1+]
- isCrossProfileIntentForwarderActivity(): boolean  [API 30+]
+ isDefault [field]  [API 1+]
- isInstantAppAvailable [field]  [API 26+]
+ labelRes [field]  [API 1+]
+ loadIcon(PackageManager): Drawable  [API 1+]
+ loadLabel(PackageManager): CharSequence  [API 1+]
+ match [field]  [API 1+]
+ nonLocalizedLabel [field]  [API 1+]
+ preferredOrder [field]  [API 1+]
+ priority [field]  [API 1+]
+ providerInfo [field]  [API 19+]
+ resolvePackageName [field]  [API 5+]
+ serviceInfo [field]  [API 1+]
+ specificIndex [field]  [API 1+]
+ toString(): String  [API 1+]
- writeToParcel(Parcel, int): void  [API 1+]
```

## ResolveInfo.DisplayNameComparator — declaration; API 1+ (0 +, 0 *, 0 ~, 2 -)

```
- DisplayNameComparator(PackageManager)  [API 1+]
- compare(ResolveInfo, ResolveInfo): int  [API 1+]
```

## ServiceInfo — framework; API 1+ (2 +, 0 *, 0 ~, 5 -)

```
- CREATOR [field]  [API 1+]
+ ServiceInfo()  [API 1+]
- ServiceInfo(ServiceInfo)  [API 1+]
- describeContents(): int  [API 1+]
- dump(Printer, String): void  [API 5+]
- flags [field]  [API 14+]
- getForegroundServiceType(): int  [API 29+]
+ permission [field]  [API 1+]
+ toString(): String  [API 1+]
- writeToParcel(Parcel, int): void  [API 1+]
```

## SharedLibraryInfo — declaration; API 26+ (0 +, 0 *, 0 ~, 10 -)

```
- CREATOR [field]  [API 26+]
- describeContents(): int  [API 26+]
- getDeclaringPackage(): VersionedPackage  [API 26+]
- getDependentPackages(): List  [API 26+]
- getLongVersion(): long  [API 28+]
- getName(): String  [API 26+]
- getOptionalDependentPackages(): List  [API 36+]
- getType(): int  [API 26+]
- getVersion(): int  [API 26+, deprecated in 28]
- toString(): String  [API 26+]
- writeToParcel(Parcel, int): void  [API 26+]
```

## ShortcutInfo — declaration; API 25+ (0 +, 11 *, 0 ~, 19 -)

```
- CREATOR [field]  [API 25+]
- createFromGenericDocument(Context, GenericDocument): ShortcutInfo [static]  [API 33+]
- describeContents(): int  [API 25+]
* getActivity(): ComponentName  [API 25+]
- getCapabilities(): List  [API 33+]
- getCapabilityParams(Capability): List  [API 33+]
* getCategories(): Set  [API 25+]
* getDisabledMessage(): CharSequence  [API 25+]
- getDisabledReason(): int  [API 28+]
* getExcludedFromSurfaces(): int  [API 33+]
* getExtras(): PersistableBundle  [API 25+]
- getId(): String  [API 25+]
* getIntent(): Intent  [API 25+]
* getIntents(): Intent[]  [API 25+]
- getLastChangedTimestamp(): long  [API 25+]
* getLocusId(): LocusId  [API 29+]
* getLongLabel(): CharSequence  [API 25+]
- getPackage(): String  [API 25+]
* getRank(): int  [API 25+]
* getShortLabel(): CharSequence  [API 25+]
- getUserHandle(): UserHandle  [API 25+]
- hasKeyFieldsOnly(): boolean  [API 25+]
- isCached(): boolean  [API 30+]
- isDeclaredInManifest(): boolean  [API 25+]
- isDynamic(): boolean  [API 25+]
- isEnabled(): boolean  [API 25+]
- isExcludedFromSurfaces(int): boolean  [API 33+]
- isImmutable(): boolean  [API 25+]
- isPinned(): boolean  [API 25+]
- toString(): String  [API 25+]
- writeToParcel(Parcel, int): void  [API 25+]
```

## ShortcutInfo.Builder — declaration; API 25+ (0 +, 12 *, 0 ~, 7 -)

```
- Builder(Context, String)  [API 25+]
- addCapabilityBinding(Capability, CapabilityParams): ShortcutInfo.Builder  [API 33+]
* build(): ShortcutInfo  [API 25+]
* setActivity(ComponentName): ShortcutInfo.Builder  [API 25+]
* setCategories(Set): ShortcutInfo.Builder  [API 25+]
* setDisabledMessage(CharSequence): ShortcutInfo.Builder  [API 25+]
* setExcludedFromSurfaces(int): ShortcutInfo.Builder  [API 33+]
* setExtras(PersistableBundle): ShortcutInfo.Builder  [API 25+]
- setIcon(Icon): ShortcutInfo.Builder  [API 25+]
* setIntent(Intent): ShortcutInfo.Builder  [API 25+]
* setIntents(Intent[]): ShortcutInfo.Builder  [API 25+]
* setLocusId(LocusId): ShortcutInfo.Builder  [API 29+]
* setLongLabel(CharSequence): ShortcutInfo.Builder  [API 25+]
- setLongLived(boolean): ShortcutInfo.Builder  [API 29+]
- setPerson(Person): ShortcutInfo.Builder  [API 29+]
- setPersons(Person[]): ShortcutInfo.Builder  [API 29+]
* setRank(int): ShortcutInfo.Builder  [API 25+]
* setShortLabel(CharSequence): ShortcutInfo.Builder  [API 25+]
- setStartingTheme(int): ShortcutInfo.Builder  [API 31+]
```

## ShortcutManager — framework; API 25+ (0 +, 0 *, 0 ~, 22 -)

```
- addDynamicShortcuts(List): boolean  [API 25+]
- createShortcutResultIntent(ShortcutInfo): Intent  [API 26+]
- disableShortcuts(List): void  [API 25+]
- disableShortcuts(List, CharSequence): void  [API 25+]
- enableShortcuts(List): void  [API 25+]
- getDynamicShortcuts(): List  [API 25+]
- getIconMaxHeight(): int  [API 25+]
- getIconMaxWidth(): int  [API 25+]
- getManifestShortcuts(): List  [API 25+]
- getMaxShortcutCountPerActivity(): int  [API 25+]
- getPinnedShortcuts(): List  [API 25+]
- getShortcuts(int): List  [API 30+]
- isRateLimitingActive(): boolean  [API 25+]
- isRequestPinShortcutSupported(): boolean  [API 26+]
- pushDynamicShortcut(ShortcutInfo): void  [API 30+]
- removeAllDynamicShortcuts(): void  [API 25+]
- removeDynamicShortcuts(List): void  [API 25+]
- removeLongLivedShortcuts(List): void  [API 30+]
- reportShortcutUsed(String): void  [API 25+]
- requestPinShortcut(ShortcutInfo, IntentSender): boolean  [API 26+]
- setDynamicShortcuts(List): boolean  [API 25+]
- updateShortcuts(List): boolean  [API 25+]
```

## Signature — framework; API 1+ (6 +, 0 *, 2 ~, 2 -)

```
- CREATOR [field]  [API 1+]
+ Signature(String)  [API 1+]
+ Signature(byte[])  [API 1+]
~ describeContents(): int  [API 1+]
+ equals(Object): boolean  [API 1+]
+ hashCode(): int  [API 1+]
+ toByteArray(): byte[]  [API 1+]
- toChars(): char[]  [API 1+]
- toChars(char[], int[]): char[]  [API 1+]
+ toCharsString(): String  [API 1+]
~ writeToParcel(Parcel, int): void  [API 1+]
```

## SigningInfo — declaration; API 28+ (0 +, 0 *, 0 ~, 12 -)

```
- CREATOR [field]  [API 28+]
- SigningInfo()  [API 28+]
- SigningInfo(SigningInfo)  [API 28+]
- SigningInfo(int, Collection, Collection, Collection)  [API 35+]
- describeContents(): int  [API 28+]
- getApkContentsSigners(): Signature[]  [API 28+]
- getPublicKeys(): Collection  [API 35+]
- getSchemeVersion(): int  [API 35+]
- getSigningCertificateHistory(): Signature[]  [API 28+]
- hasMultipleSigners(): boolean  [API 28+]
- hasPastSigningCertificates(): boolean  [API 28+]
- signersMatchExactly(SigningInfo): boolean  [API 36+]
- writeToParcel(Parcel, int): void  [API 28+]
```

## SigningInfoException — declaration; API 36+ (0 +, 0 *, 0 ~, 1 -)

```
- getCode(): int  [API 36+]
```

## VersionedPackage — declaration; API 26+ (0 +, 3 *, 0 ~, 7 -)

```
- CREATOR [field]  [API 26+]
* VersionedPackage(String, int)  [API 26+]
- VersionedPackage(String, long)  [API 28+]
- describeContents(): int  [API 26+]
- equals(Object): boolean  [API 26+]
- getLongVersionCode(): long  [API 28+]
* getPackageName(): String  [API 26+]
* getVersionCode(): int  [API 26+, deprecated in 28]
- hashCode(): int  [API 26+]
- toString(): String  [API 26+]
- writeToParcel(Parcel, int): void  [API 26+]
```

