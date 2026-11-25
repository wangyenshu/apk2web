// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.content.pm;

public class LauncherApps {
  public static final java.lang.String ACTION_CONFIRM_PIN_APPWIDGET = "android.content.pm.action.CONFIRM_PIN_APPWIDGET";
  public static final java.lang.String ACTION_CONFIRM_PIN_SHORTCUT = "android.content.pm.action.CONFIRM_PIN_SHORTCUT";
  public static final java.lang.String EXTRA_PIN_ITEM_REQUEST = "android.content.pm.extra.PIN_ITEM_REQUEST";
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public LauncherApps(a2w.Hidden hidden) { }
  @a2w.NotImplemented public java.util.List<android.content.pm.LauncherActivityInfo> getActivityList(java.lang.String packageName, android.os.UserHandle user) { a2w.Stubs.hit("android.content.pm.LauncherApps.getActivityList"); return null; }
  @a2w.NotImplemented public java.util.List<android.content.pm.PackageInstaller.SessionInfo> getAllPackageInstallerSessions() { a2w.Stubs.hit("android.content.pm.LauncherApps.getAllPackageInstallerSessions"); return null; }
  @a2w.NotImplemented public android.content.IntentSender getAppMarketActivityIntent(java.lang.String packageName, android.os.UserHandle user) { a2w.Stubs.hit("android.content.pm.LauncherApps.getAppMarketActivityIntent"); return null; }
  @a2w.NotImplemented public android.content.pm.ApplicationInfo getApplicationInfo(java.lang.String packageName, int flags, android.os.UserHandle user) throws android.content.pm.PackageManager.NameNotFoundException { a2w.Stubs.hit("android.content.pm.LauncherApps.getApplicationInfo"); return null; }
  @a2w.NotImplemented public final android.content.pm.LauncherUserInfo getLauncherUserInfo(android.os.UserHandle userHandle) { a2w.Stubs.hit("android.content.pm.LauncherApps.getLauncherUserInfo"); return null; }
  @a2w.NotImplemented public android.content.pm.LauncherApps.PinItemRequest getPinItemRequest(android.content.Intent intent) { a2w.Stubs.hit("android.content.pm.LauncherApps.getPinItemRequest"); return null; }
  @a2w.NotImplemented public java.util.List<java.lang.String> getPreInstalledSystemPackages(android.os.UserHandle userHandle) { a2w.Stubs.hit("android.content.pm.LauncherApps.getPreInstalledSystemPackages"); return null; }
  @a2w.NotImplemented public java.util.List<android.os.UserHandle> getProfiles() { a2w.Stubs.hit("android.content.pm.LauncherApps.getProfiles"); return null; }
  @a2w.NotImplemented public android.graphics.drawable.Drawable getShortcutBadgedIconDrawable(android.content.pm.ShortcutInfo shortcut, int density) { a2w.Stubs.hit("android.content.pm.LauncherApps.getShortcutBadgedIconDrawable"); return null; }
  @a2w.NotImplemented public android.content.IntentSender getShortcutConfigActivityIntent(android.content.pm.LauncherActivityInfo info) { a2w.Stubs.hit("android.content.pm.LauncherApps.getShortcutConfigActivityIntent"); return null; }
  @a2w.NotImplemented public java.util.List<android.content.pm.LauncherActivityInfo> getShortcutConfigActivityList(java.lang.String packageName, android.os.UserHandle user) { a2w.Stubs.hit("android.content.pm.LauncherApps.getShortcutConfigActivityList"); return null; }
  @a2w.NotImplemented public android.graphics.drawable.Drawable getShortcutIconDrawable(android.content.pm.ShortcutInfo shortcut, int density) { a2w.Stubs.hit("android.content.pm.LauncherApps.getShortcutIconDrawable"); return null; }
  @a2w.NotImplemented public android.app.PendingIntent getShortcutIntent(java.lang.String packageName, java.lang.String shortcutId, android.os.Bundle opts, android.os.UserHandle user) { a2w.Stubs.hit("android.content.pm.LauncherApps.getShortcutIntent"); return null; }
  @a2w.NotImplemented public java.util.List<android.content.pm.ShortcutInfo> getShortcuts(android.content.pm.LauncherApps.ShortcutQuery query, android.os.UserHandle user) { a2w.Stubs.hit("android.content.pm.LauncherApps.getShortcuts"); return null; }
  @a2w.NotImplemented public android.os.Bundle getSuspendedPackageLauncherExtras(java.lang.String packageName, android.os.UserHandle user) { a2w.Stubs.hit("android.content.pm.LauncherApps.getSuspendedPackageLauncherExtras"); return null; }
  @a2w.NotImplemented public boolean hasShortcutHostPermission() { a2w.Stubs.hit("android.content.pm.LauncherApps.hasShortcutHostPermission"); return false; }
  @a2w.NotImplemented public boolean isActivityEnabled(android.content.ComponentName component, android.os.UserHandle user) { a2w.Stubs.hit("android.content.pm.LauncherApps.isActivityEnabled"); return false; }
  @a2w.NotImplemented public boolean isPackageEnabled(java.lang.String packageName, android.os.UserHandle user) { a2w.Stubs.hit("android.content.pm.LauncherApps.isPackageEnabled"); return false; }
  @a2w.NotImplemented public void pinShortcuts(java.lang.String packageName, java.util.List<java.lang.String> shortcutIds, android.os.UserHandle user) { a2w.Stubs.hit("android.content.pm.LauncherApps.pinShortcuts"); }
  @a2w.NotImplemented public void registerCallback(android.content.pm.LauncherApps.Callback callback) { a2w.Stubs.hit("android.content.pm.LauncherApps.registerCallback"); }
  @a2w.NotImplemented public void registerCallback(android.content.pm.LauncherApps.Callback callback, android.os.Handler handler) { a2w.Stubs.hit("android.content.pm.LauncherApps.registerCallback"); }
  @a2w.NotImplemented public void registerPackageInstallerSessionCallback(java.util.concurrent.Executor executor, android.content.pm.PackageInstaller.SessionCallback callback) { a2w.Stubs.hit("android.content.pm.LauncherApps.registerPackageInstallerSessionCallback"); }
  @a2w.NotImplemented public android.content.pm.LauncherActivityInfo resolveActivity(android.content.Intent intent, android.os.UserHandle user) { a2w.Stubs.hit("android.content.pm.LauncherApps.resolveActivity"); return null; }
  @a2w.NotImplemented public void setArchiveCompatibility(android.content.pm.LauncherApps.ArchiveCompatibilityParams params) { a2w.Stubs.hit("android.content.pm.LauncherApps.setArchiveCompatibility"); }
  @a2w.NotImplemented public boolean shouldHideFromSuggestions(java.lang.String packageName, android.os.UserHandle user) { a2w.Stubs.hit("android.content.pm.LauncherApps.shouldHideFromSuggestions"); return false; }
  @a2w.NotImplemented public void startAppDetailsActivity(android.content.ComponentName component, android.os.UserHandle user, android.graphics.Rect sourceBounds, android.os.Bundle opts) { a2w.Stubs.hit("android.content.pm.LauncherApps.startAppDetailsActivity"); }
  @a2w.NotImplemented public void startMainActivity(android.content.ComponentName component, android.os.UserHandle user, android.graphics.Rect sourceBounds, android.os.Bundle opts) { a2w.Stubs.hit("android.content.pm.LauncherApps.startMainActivity"); }
  @a2w.NotImplemented public void startPackageInstallerSessionDetailsActivity(android.content.pm.PackageInstaller.SessionInfo sessionInfo, android.graphics.Rect sourceBounds, android.os.Bundle opts) { a2w.Stubs.hit("android.content.pm.LauncherApps.startPackageInstallerSessionDetailsActivity"); }
  @a2w.NotImplemented public void startShortcut(android.content.pm.ShortcutInfo shortcut, android.graphics.Rect sourceBounds, android.os.Bundle startActivityOptions) { a2w.Stubs.hit("android.content.pm.LauncherApps.startShortcut"); }
  @a2w.NotImplemented public void startShortcut(java.lang.String packageName, java.lang.String shortcutId, android.graphics.Rect sourceBounds, android.os.Bundle startActivityOptions, android.os.UserHandle user) { a2w.Stubs.hit("android.content.pm.LauncherApps.startShortcut"); }
  @a2w.NotImplemented public void unregisterCallback(android.content.pm.LauncherApps.Callback callback) { a2w.Stubs.hit("android.content.pm.LauncherApps.unregisterCallback"); }
  @a2w.NotImplemented public void unregisterPackageInstallerSessionCallback(android.content.pm.PackageInstaller.SessionCallback callback) { a2w.Stubs.hit("android.content.pm.LauncherApps.unregisterPackageInstallerSessionCallback"); }

    public static class ArchiveCompatibilityParams {
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public ArchiveCompatibilityParams(a2w.Hidden hidden) { }
    @a2w.NotImplemented public ArchiveCompatibilityParams() { this((a2w.Hidden) null); a2w.Stubs.hit("android.content.pm.LauncherApps.ArchiveCompatibilityParams.<init>"); }
    @a2w.NotImplemented public void setEnableIconOverlay(boolean enableIconOverlay) { a2w.Stubs.hit("android.content.pm.LauncherApps.ArchiveCompatibilityParams.setEnableIconOverlay"); }
    @a2w.NotImplemented public void setEnableUnarchivalConfirmation(boolean enableUnarchivalConfirmation) { a2w.Stubs.hit("android.content.pm.LauncherApps.ArchiveCompatibilityParams.setEnableUnarchivalConfirmation"); }
  }

    public static abstract class Callback {
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public Callback(a2w.Hidden hidden) { }
    public Callback() { this((a2w.Hidden) null); }
    public abstract void onPackageAdded(java.lang.String p0, android.os.UserHandle p1);
    public abstract void onPackageChanged(java.lang.String p0, android.os.UserHandle p1);
    public void onPackageLoadingProgressChanged(java.lang.String packageName, android.os.UserHandle user, float progress) { }
    public abstract void onPackageRemoved(java.lang.String p0, android.os.UserHandle p1);
    public abstract void onPackagesAvailable(java.lang.String[] p0, android.os.UserHandle p1, boolean p2);
    public void onPackagesSuspended(java.lang.String[] packageNames, android.os.UserHandle user) { }
    public void onPackagesSuspended(java.lang.String[] packageNames, android.os.UserHandle user, android.os.Bundle launcherExtras) { }
    public abstract void onPackagesUnavailable(java.lang.String[] p0, android.os.UserHandle p1, boolean p2);
    public void onPackagesUnsuspended(java.lang.String[] packageNames, android.os.UserHandle user) { }
    public void onShortcutsChanged(java.lang.String packageName, java.util.List<android.content.pm.ShortcutInfo> shortcuts, android.os.UserHandle user) { }
  }

    public static final class PinItemRequest implements android.os.Parcelable {
    @a2w.NotImplemented public static final android.os.Parcelable.Creator<android.content.pm.LauncherApps.PinItemRequest> CREATOR = null;
    public static final int REQUEST_TYPE_APPWIDGET = 2;
    public static final int REQUEST_TYPE_SHORTCUT = 1;
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public PinItemRequest(a2w.Hidden hidden) { }
    @a2w.NotImplemented public boolean accept() { a2w.Stubs.hit("android.content.pm.LauncherApps.PinItemRequest.accept"); return false; }
    @a2w.NotImplemented public boolean accept(android.os.Bundle options) { a2w.Stubs.hit("android.content.pm.LauncherApps.PinItemRequest.accept"); return false; }
    @a2w.NotImplemented public int describeContents() { a2w.Stubs.hit("android.content.pm.LauncherApps.PinItemRequest.describeContents"); return 0; }
    @a2w.NotImplemented public android.appwidget.AppWidgetProviderInfo getAppWidgetProviderInfo(android.content.Context context) { a2w.Stubs.hit("android.content.pm.LauncherApps.PinItemRequest.getAppWidgetProviderInfo"); return null; }
    @a2w.NotImplemented public android.os.Bundle getExtras() { a2w.Stubs.hit("android.content.pm.LauncherApps.PinItemRequest.getExtras"); return null; }
    @a2w.NotImplemented public int getRequestType() { a2w.Stubs.hit("android.content.pm.LauncherApps.PinItemRequest.getRequestType"); return 0; }
    @a2w.NotImplemented public android.content.pm.ShortcutInfo getShortcutInfo() { a2w.Stubs.hit("android.content.pm.LauncherApps.PinItemRequest.getShortcutInfo"); return null; }
    @a2w.NotImplemented public boolean isValid() { a2w.Stubs.hit("android.content.pm.LauncherApps.PinItemRequest.isValid"); return false; }
    @a2w.NotImplemented public void writeToParcel(android.os.Parcel dest, int flags) { a2w.Stubs.hit("android.content.pm.LauncherApps.PinItemRequest.writeToParcel"); }
  }

    public static class ShortcutQuery {
    public static final int FLAG_GET_KEY_FIELDS_ONLY = 4;
    public static final int FLAG_MATCH_CACHED = 16;
    public static final int FLAG_MATCH_DYNAMIC = 1;
    public static final int FLAG_MATCH_MANIFEST = 8;
    public static final int FLAG_MATCH_PINNED = 2;
    public static final int FLAG_MATCH_PINNED_BY_ANY_LAUNCHER = 1024;
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public ShortcutQuery(a2w.Hidden hidden) { }
    @a2w.NotImplemented public ShortcutQuery() { this((a2w.Hidden) null); a2w.Stubs.hit("android.content.pm.LauncherApps.ShortcutQuery.<init>"); }
    @a2w.NotImplemented public android.content.pm.LauncherApps.ShortcutQuery setActivity(android.content.ComponentName activity) { a2w.Stubs.hit("android.content.pm.LauncherApps.ShortcutQuery.setActivity"); return null; }
    @a2w.NotImplemented public android.content.pm.LauncherApps.ShortcutQuery setChangedSince(long changedSince) { a2w.Stubs.hit("android.content.pm.LauncherApps.ShortcutQuery.setChangedSince"); return null; }
    @a2w.NotImplemented public android.content.pm.LauncherApps.ShortcutQuery setLocusIds(java.util.List<android.content.LocusId> locusIds) { a2w.Stubs.hit("android.content.pm.LauncherApps.ShortcutQuery.setLocusIds"); return null; }
    @a2w.NotImplemented public android.content.pm.LauncherApps.ShortcutQuery setPackage(java.lang.String packageName) { a2w.Stubs.hit("android.content.pm.LauncherApps.ShortcutQuery.setPackage"); return null; }
    @a2w.NotImplemented public android.content.pm.LauncherApps.ShortcutQuery setQueryFlags(int queryFlags) { a2w.Stubs.hit("android.content.pm.LauncherApps.ShortcutQuery.setQueryFlags"); return null; }
    @a2w.NotImplemented public android.content.pm.LauncherApps.ShortcutQuery setShortcutIds(java.util.List<java.lang.String> shortcutIds) { a2w.Stubs.hit("android.content.pm.LauncherApps.ShortcutQuery.setShortcutIds"); return null; }
  }
}
