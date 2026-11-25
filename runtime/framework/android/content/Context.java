package android.content;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import java.io.File;
import a2w.jdk.FileInputStream;
import java.io.FileOutputStream;

public abstract class Context {
  public static final int MODE_PRIVATE = 0, MODE_WORLD_READABLE = 1, MODE_WORLD_WRITEABLE = 2, MODE_APPEND = 0x8000, MODE_MULTI_PROCESS = 4;
  public static final int BIND_AUTO_CREATE = 1, BIND_DEBUG_UNBIND = 2, BIND_NOT_FOREGROUND = 4, CONTEXT_INCLUDE_CODE = 1, CONTEXT_IGNORE_SECURITY = 2, CONTEXT_RESTRICTED = 4;
  public static final String ACTIVITY_SERVICE = "activity", ALARM_SERVICE = "alarm", AUDIO_SERVICE = "audio", CLIPBOARD_SERVICE = "clipboard",
      CONNECTIVITY_SERVICE = "connectivity", DOWNLOAD_SERVICE = "download", INPUT_METHOD_SERVICE = "input_method", KEYGUARD_SERVICE = "keyguard",
      LAYOUT_INFLATER_SERVICE = "layout_inflater", LOCATION_SERVICE = "location", NOTIFICATION_SERVICE = "notification", POWER_SERVICE = "power",
      SEARCH_SERVICE = "search", SENSOR_SERVICE = "sensor", TELEPHONY_SERVICE = "phone", VIBRATOR_SERVICE = "vibrator", WALLPAPER_SERVICE = "wallpaper",
      WIFI_SERVICE = "wifi", WINDOW_SERVICE = "window", ACCOUNT_SERVICE = "account", STORAGE_SERVICE = "storage", UI_MODE_SERVICE = "uimode";

  public abstract AssetManager getAssets();
  public abstract Resources getResources();
  public abstract PackageManager getPackageManager();
  public abstract ContentResolver getContentResolver();
  public abstract Looper getMainLooper();
  public abstract Context getApplicationContext();
  public abstract void setTheme(int resid);
  public abstract Resources.Theme getTheme();
  public abstract ClassLoader getClassLoader();
  public abstract String getPackageName();
  public abstract ApplicationInfo getApplicationInfo();
  public abstract String getPackageResourcePath();
  public abstract String getPackageCodePath();
  public abstract SharedPreferences getSharedPreferences(String name, int mode);
  public abstract FileInputStream openFileInput(String name) throws java.io.FileNotFoundException;
  public abstract FileOutputStream openFileOutput(String name, int mode) throws java.io.FileNotFoundException;
  public abstract boolean deleteFile(String name);
  public abstract File getFileStreamPath(String name);
  public abstract File getFilesDir();
  public abstract File getExternalFilesDir(String type);
  public abstract File getCacheDir();
  public abstract File getExternalCacheDir();
  public File getObbDir() { return null; }
  public File[] getObbDirs() { File f = getObbDir(); return f == null ? new File[0] : new File[] {f}; }
  public File[] getExternalFilesDirs(String type) { File f = getExternalFilesDir(type); return f == null ? new File[0] : new File[] {f}; }
  public File[] getExternalCacheDirs() { File f = getExternalCacheDir(); return f == null ? new File[0] : new File[] {f}; }
  public abstract String[] fileList();
  public abstract File getDir(String name, int mode);
  public abstract File getDatabasePath(String name);
  public abstract android.database.sqlite.SQLiteDatabase openOrCreateDatabase(String name, int mode, android.database.sqlite.SQLiteDatabase.CursorFactory f);
  public abstract boolean deleteDatabase(String name);
  public android.database.sqlite.SQLiteDatabase openOrCreateDatabase(String name, int mode, android.database.sqlite.SQLiteDatabase.CursorFactory f, android.database.DatabaseErrorHandler eh) { return openOrCreateDatabase(name, mode, f); }
  public String[] databaseList() { return new String[0]; }
  public boolean moveDatabaseFrom(Context source, String name) { return false; }
  public abstract void startActivity(Intent intent);
  public void startActivity(Intent intent, Bundle options) { startActivity(intent); }
  public void startActivities(Intent[] intents) { for (Intent i : intents) startActivity(i); }
  public abstract void sendBroadcast(Intent intent);
  // ---- broadcasts (a2w.Broadcasts; permissions are always held, users are the one user)
  public void sendBroadcast(Intent intent, String perm) { sendBroadcast(intent); }
  public void sendBroadcast(Intent intent, String receiverPermission, Bundle options) { sendBroadcast(intent); }
  public void sendBroadcastWithMultiplePermissions(Intent intent, String[] receiverPermissions) { sendBroadcast(intent); }
  public void sendBroadcastAsUser(Intent intent, android.os.UserHandle user) { sendBroadcast(intent); }
  public void sendBroadcastAsUser(Intent intent, android.os.UserHandle user, String receiverPermission) { sendBroadcast(intent); }
  public void sendOrderedBroadcast(Intent intent, String perm) { a2w.Broadcasts.sendOrdered(this, intent, null, null, 0, null, null, false); }
  public void sendOrderedBroadcast(Intent intent, String receiverPermission, Bundle options) { sendOrderedBroadcast(intent, receiverPermission); }
  public void sendOrderedBroadcast(Intent intent, String receiverPermission, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras) {
    a2w.Broadcasts.sendOrdered(this, intent, resultReceiver, scheduler, initialCode, initialData, initialExtras, false);
  }
  public void sendOrderedBroadcast(Intent intent, String receiverPermission, Bundle options, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras) {
    sendOrderedBroadcast(intent, receiverPermission, resultReceiver, scheduler, initialCode, initialData, initialExtras);
  }
  public void sendOrderedBroadcast(Intent intent, String receiverPermission, String receiverAppOp, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras) {
    sendOrderedBroadcast(intent, receiverPermission, resultReceiver, scheduler, initialCode, initialData, initialExtras);
  }
  public void sendOrderedBroadcast(Intent intent, int initialCode, String receiverPermission, String receiverAppOp, BroadcastReceiver resultReceiver, Handler scheduler, String initialData, Bundle initialExtras, Bundle options) {
    sendOrderedBroadcast(intent, receiverPermission, resultReceiver, scheduler, initialCode, initialData, initialExtras);
  }
  public void sendOrderedBroadcastAsUser(Intent intent, android.os.UserHandle user, String receiverPermission, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras) {
    sendOrderedBroadcast(intent, receiverPermission, resultReceiver, scheduler, initialCode, initialData, initialExtras);
  }
  public void sendStickyBroadcast(Intent intent) { a2w.Broadcasts.sendSticky(this, intent); }
  public void sendStickyBroadcast(Intent intent, Bundle options) { sendStickyBroadcast(intent); }
  public void sendStickyBroadcastAsUser(Intent intent, android.os.UserHandle user) { sendStickyBroadcast(intent); }
  public void sendStickyOrderedBroadcast(Intent intent, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras) {
    a2w.Broadcasts.sendOrdered(this, intent, resultReceiver, scheduler, initialCode, initialData, initialExtras, true);
  }
  public void sendStickyOrderedBroadcastAsUser(Intent intent, android.os.UserHandle user, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras) {
    sendStickyOrderedBroadcast(intent, resultReceiver, scheduler, initialCode, initialData, initialExtras);
  }
  public void removeStickyBroadcast(Intent intent) { a2w.Broadcasts.removeSticky(intent); }
  public void removeStickyBroadcastAsUser(Intent intent, android.os.UserHandle user) { removeStickyBroadcast(intent); }
  public abstract Intent registerReceiver(BroadcastReceiver r, IntentFilter f);
  public Intent registerReceiver(BroadcastReceiver r, IntentFilter f, String perm, Handler h) { return a2w.Broadcasts.register(this, r, f, h); }
  public abstract void unregisterReceiver(BroadcastReceiver r);
  public abstract ComponentName startService(Intent service);
  public abstract boolean stopService(Intent service);
  public abstract boolean bindService(Intent service, ServiceConnection conn, int flags);
  public abstract void unbindService(ServiceConnection conn);
  public abstract Object getSystemService(String name);
  // ---- API 21+ conveniences, from the methods every Context has
  public final int getColor(int id) { return getResources().getColor(id, getTheme()); }
  public final android.graphics.drawable.Drawable getDrawable(int id) { return getResources().getDrawable(id, getTheme()); }
  public final android.content.res.ColorStateList getColorStateList(int id) { return getResources().getColorStateList(id, getTheme()); }
  public int checkSelfPermission(String permission) { return checkPermission(permission, android.os.Process.myPid(), android.os.Process.myUid()); }
  public File getDataDir() { File f = getFilesDir().getParentFile(); return f != null ? f : getFilesDir(); }
  public File getCodeCacheDir() { File f = new File(getDataDir(), "code_cache"); f.mkdirs(); return f; }
  public File getNoBackupFilesDir() { File f = new File(getDataDir(), "no_backup"); f.mkdirs(); return f; }
  public File[] getExternalMediaDirs() { return new File[] {new File("/sdcard/Android/media/" + getPackageName())}; }
  public boolean deleteSharedPreferences(String name) {
    SharedPreferences p = getSharedPreferences(name, 0);
    p.edit().clear().commit();
    return true;
  }
  public boolean moveSharedPreferencesFrom(Context source, String name) { return true; }
  public boolean isDeviceProtectedStorage() { return false; }
  public Context createAttributionContext(String tag) { return this; }
  public Context createContext(ContextParams contextParams) { return this; }
  public ContextParams getParams() { return null; }
  public Context createContextForSplit(String splitName) throws PackageManager.NameNotFoundException { return this; }
  public Context createDeviceContext(int deviceId) { return this; }
  public Context createWindowContext(android.view.Display display, int type, Bundle options) { return this; }
  public AttributionSource getAttributionSource() { return null; }
  public void registerDeviceIdChangeListener(java.util.concurrent.Executor executor, java.util.function.IntConsumer listener) {
    java.util.Objects.requireNonNull(executor); java.util.Objects.requireNonNull(listener);   // (the device never changes)
  }
  public void unregisterDeviceIdChangeListener(java.util.function.IntConsumer listener) { java.util.Objects.requireNonNull(listener); }
  public Context createWindowContext(int type, android.os.Bundle options) { return this; }
  public String getOpPackageName() { return getPackageName(); }
  public String getAttributionTag() { return null; }
  public int getDeviceId() { return 0; }
  public boolean isUiContext() { return this instanceof android.app.Activity; }
  public android.view.Display getDisplay() { return ((android.view.WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay(); }
  public java.util.concurrent.Executor getMainExecutor() { android.os.Handler h = new android.os.Handler(getMainLooper()); return h::post; }
  public Intent registerReceiver(BroadcastReceiver r, IntentFilter f, int flags) { return registerReceiver(r, f); }
  public Intent registerReceiver(BroadcastReceiver r, IntentFilter f, String perm, Handler h, int flags) { return registerReceiver(r, f, perm, h); }
  public ComponentName startForegroundService(Intent service) { return startService(service); }
  public final <T> T getSystemService(Class<T> serviceClass) {
    String n = getSystemServiceName(serviceClass);
    return n == null ? null : serviceClass.cast(getSystemService(n));
  }
  public String getSystemServiceName(Class<?> c) {
    if (c == null) return null;
    String n = c.getName();
    switch (n) {
      case "android.view.WindowManager": return WINDOW_SERVICE;
      case "android.view.LayoutInflater": return LAYOUT_INFLATER_SERVICE;
      case "android.app.ActivityManager": return ACTIVITY_SERVICE;
      case "android.app.AlarmManager": return ALARM_SERVICE;
      case "android.app.NotificationManager": return NOTIFICATION_SERVICE;
      case "android.media.AudioManager": return AUDIO_SERVICE;
      case "android.net.ConnectivityManager": return CONNECTIVITY_SERVICE;
      case "android.os.Vibrator": return VIBRATOR_SERVICE;
      case "android.os.PowerManager": return POWER_SERVICE;
      case "android.view.inputmethod.InputMethodManager": return INPUT_METHOD_SERVICE;
      case "android.location.LocationManager": return LOCATION_SERVICE;
      case "android.telephony.TelephonyManager": return TELEPHONY_SERVICE;
      case "android.net.wifi.WifiManager": return WIFI_SERVICE;
      case "android.text.ClipboardManager": case "android.content.ClipboardManager": return CLIPBOARD_SERVICE;
      case "android.hardware.SensorManager": return SENSOR_SERVICE;
      case "android.app.KeyguardManager": return KEYGUARD_SERVICE;
      case "android.app.DownloadManager": return DOWNLOAD_SERVICE;
      case "android.app.UiModeManager": return UI_MODE_SERVICE;
      case "android.view.accessibility.AccessibilityManager": return "accessibility";
      case "android.os.storage.StorageManager": return "storage";
      case "android.os.BatteryManager": return "batterymanager";
      case "android.app.job.JobScheduler": return "jobscheduler";
      case "android.hardware.display.DisplayManager": return "display";
      case "android.accounts.AccountManager": return "account";
      case "android.os.UserManager": return "user";
      case "android.content.pm.ShortcutManager": return "shortcut";
      case "android.app.AppOpsManager": return "appops";
      case "android.hardware.camera2.CameraManager": return "camera";
      case "android.bluetooth.BluetoothManager": return "bluetooth";
      case "android.hardware.usb.UsbManager": return "usb";
      case "android.media.session.MediaSessionManager": return "media_session";
      case "android.media.MediaRouter": return "media_router";
      case "android.view.textservice.TextServicesManager": return "textservices";
      case "android.view.accessibility.CaptioningManager": return "captioning";
      case "android.hardware.ConsumerIrManager": return "consumer_ir";
      case "android.hardware.biometrics.BiometricManager": return "biometric";
      case "android.app.WallpaperManager": return "wallpaper";
      case "android.app.admin.DevicePolicyManager": return "device_policy";
      case "android.app.usage.UsageStatsManager": return "usagestats";
      case "android.print.PrintManager": return "print";
      case "android.net.nsd.NsdManager": return "servicediscovery";
      case "android.nfc.NfcManager": return "nfc";
      case "android.app.role.RoleManager": return "role";
      case "android.app.LocaleManager": return "locale";
      case "android.content.RestrictionsManager": return "restrictions";
      case "android.content.pm.LauncherApps": return "launcherapps";
      case "android.telecom.TelecomManager": return "telecom";
      case "android.telephony.SubscriptionManager": return "telephony_subscription_service";
      case "android.app.StatusBarManager": return "statusbar";
      case "android.media.midi.MidiManager": return "midi";
      case "android.os.HardwarePropertiesManager": return "hardware_properties";
      case "android.app.GameManager": return "game";
      case "android.os.health.SystemHealthManager": return "systemhealth";
      case "android.app.usage.StorageStatsManager": return "storagestats";
      case "android.os.DropBoxManager": return "dropbox";
      case "android.media.projection.MediaProjectionManager": return "media_projection";
      case "android.net.ConnectivityDiagnosticsManager": return "connectivity_diagnostics";
      case "android.credentials.CredentialManager": return "credential";
      case "android.view.autofill.AutofillManager": return "autofill";
      case "android.view.translation.TranslationManager": return "translation";
      case "android.appwidget.AppWidgetManager": return "appwidget";
      case "android.app.usage.NetworkStatsManager": return "netstats";
      case "android.view.textclassifier.TextClassificationManager": return "textclassification";
      case "android.app.people.PeopleManager": return "people";
      case "android.companion.CompanionDeviceManager": return "companiondevice";
      case "android.app.blob.BlobStoreManager": return "blob_store";
      case "android.content.pm.CrossProfileApps": return "crossprofileapps";
      case "android.telephony.CarrierConfigManager": return "carrier_config";
      case "android.telephony.euicc.EuiccManager": return "euicc";
      case "android.media.tv.TvInputManager": return "tv_input";
      case "android.content.om.OverlayManager": return "overlay";
      case "android.os.PerformanceHintManager": return "performance_hint";
      case "android.content.pm.verify.domain.DomainVerificationManager": return "domain_verification";
      case "android.security.FileIntegrityManager": return "file_integrity";
      case "android.net.IpSecManager": return "ipsec";
      case "android.net.VpnManager": return "vpn_management";
      case "android.app.appsearch.AppSearchManager": return "app_search";
      case "android.media.MediaCommunicationManager": return "media_communication";
      case "android.media.metrics.MediaMetricsManager": return "media_metrics";
      case "android.app.GrammaticalInflectionManager": return "grammatical_inflection";
      case "android.view.displayhash.DisplayHashManager": return "display_hash";
      case "android.os.BugreportManager": return "bugreport";
      case "android.os.SecurityStateManager": return "security_state";
      case "android.os.ProfilingManager": return "profiling";
      case "android.service.persistentdata.PersistentDataBlockManager": return "persistent_data_block";
      case "android.provider.E2eeContactKeysManager": return "contact_keys";
      case "android.devicelock.DeviceLockManager": return "device_lock";
      case "android.media.tv.interactive.TvInteractiveAppManager": return "tv_interactive_app";
      case "android.companion.virtual.VirtualDeviceManager": return "virtualdevice";
      case "android.health.connect.HealthConnectManager": return "healthconnect";
      case "android.app.SearchManager": return "search";
      case "android.os.VibratorManager": return "vibrator_manager";
      case "android.hardware.fingerprint.FingerprintManager": return "fingerprint";
      case "android.net.wifi.p2p.WifiP2pManager": return "wifip2p";
      case "android.hardware.lights.LightsManager": return "lights";
      default: return null;
    }
  }
  public abstract int checkPermission(String permission, int pid, int uid);
  public abstract int checkCallingOrSelfPermission(String permission);
  public int checkCallingPermission(String p) { return checkCallingOrSelfPermission(p); }
  public void enforcePermission(String permission, int pid, int uid, String message) {
    if (checkPermission(permission, pid, uid) != PackageManager.PERMISSION_GRANTED) throw new SecurityException((message != null ? message + ": " : "") + "Neither user " + uid + " nor current process has " + permission + ".");
  }
  public void enforceCallingPermission(String permission, String message) { enforcePermission(permission, android.os.Binder.getCallingPid(), android.os.Binder.getCallingUid(), message); }
  public void enforceCallingOrSelfPermission(String permission, String message) { enforcePermission(permission, android.os.Process.myPid(), android.os.Process.myUid(), message); }
  // ---- URI permissions: one app, which holds every grant
  public int checkUriPermission(android.net.Uri uri, int pid, int uid, int modeFlags) { return PackageManager.PERMISSION_GRANTED; }
  public int checkUriPermission(android.net.Uri uri, String readPermission, String writePermission, int pid, int uid, int modeFlags) {
    if ((modeFlags & Intent.FLAG_GRANT_READ_URI_PERMISSION) != 0 && readPermission != null && checkPermission(readPermission, pid, uid) != PackageManager.PERMISSION_GRANTED) return PackageManager.PERMISSION_DENIED;
    if ((modeFlags & Intent.FLAG_GRANT_WRITE_URI_PERMISSION) != 0 && writePermission != null && checkPermission(writePermission, pid, uid) != PackageManager.PERMISSION_GRANTED) return PackageManager.PERMISSION_DENIED;
    return uri == null ? PackageManager.PERMISSION_DENIED : checkUriPermission(uri, pid, uid, modeFlags);
  }
  public int checkCallingUriPermission(android.net.Uri uri, int modeFlags) { return checkUriPermission(uri, android.os.Binder.getCallingPid(), android.os.Binder.getCallingUid(), modeFlags); }
  public int checkCallingOrSelfUriPermission(android.net.Uri uri, int modeFlags) { return checkUriPermission(uri, android.os.Process.myPid(), android.os.Process.myUid(), modeFlags); }
  public int checkContentUriPermissionFull(android.net.Uri uri, int pid, int uid, int modeFlags) { return checkUriPermission(uri, pid, uid, modeFlags); }
  public int[] checkUriPermissions(java.util.List<android.net.Uri> uris, int pid, int uid, int modeFlags) {
    int[] r = new int[uris.size()];
    for (int i = 0; i < r.length; i++) r[i] = checkUriPermission(uris.get(i), pid, uid, modeFlags);
    return r;
  }
  public int[] checkCallingUriPermissions(java.util.List<android.net.Uri> uris, int modeFlags) { return checkUriPermissions(uris, android.os.Binder.getCallingPid(), android.os.Binder.getCallingUid(), modeFlags); }
  public int[] checkCallingOrSelfUriPermissions(java.util.List<android.net.Uri> uris, int modeFlags) { return checkUriPermissions(uris, android.os.Process.myPid(), android.os.Process.myUid(), modeFlags); }
  public void enforceUriPermission(android.net.Uri uri, int pid, int uid, int modeFlags, String message) {
    if (checkUriPermission(uri, pid, uid, modeFlags) != PackageManager.PERMISSION_GRANTED) throw new SecurityException((message != null ? message + ": " : "") + "No permission to " + uri);
  }
  public void enforceUriPermission(android.net.Uri uri, String readPermission, String writePermission, int pid, int uid, int modeFlags, String message) {
    if (checkUriPermission(uri, readPermission, writePermission, pid, uid, modeFlags) != PackageManager.PERMISSION_GRANTED) throw new SecurityException((message != null ? message + ": " : "") + "No permission to " + uri);
  }
  public void enforceCallingUriPermission(android.net.Uri uri, int modeFlags, String message) { enforceUriPermission(uri, android.os.Binder.getCallingPid(), android.os.Binder.getCallingUid(), modeFlags, message); }
  public void enforceCallingOrSelfUriPermission(android.net.Uri uri, int modeFlags, String message) { enforceUriPermission(uri, android.os.Process.myPid(), android.os.Process.myUid(), modeFlags, message); }
  public void grantUriPermission(String toPackage, android.net.Uri uri, int modeFlags) { java.util.Objects.requireNonNull(uri); }
  public void revokeUriPermission(android.net.Uri uri, int modeFlags) { java.util.Objects.requireNonNull(uri); }
  public void revokeUriPermission(String toPackage, android.net.Uri uri, int modeFlags) { java.util.Objects.requireNonNull(uri); }
  public void revokeSelfPermissionOnKill(String permName) { revokeSelfPermissionsOnKill(java.util.Collections.singletonList(permName)); }
  public void revokeSelfPermissionsOnKill(java.util.Collection<String> permissions) { java.util.Objects.requireNonNull(permissions); }

  // ---- services with executors and flag objects
  /** android.content.Context.BindServiceFlags: binding flags as a long */
  public static final class BindServiceFlags {
    private final long mValue;
    private BindServiceFlags(long value) { mValue = value; }
    public static BindServiceFlags of(long value) { return new BindServiceFlags(value); }
    long getValue() { return mValue; }
  }
  private static ServiceConnection onExecutor(java.util.concurrent.Executor executor, ServiceConnection conn) {
    java.util.Objects.requireNonNull(executor); java.util.Objects.requireNonNull(conn);
    return a2w.ServiceConnections.onExecutor(executor, conn);
  }
  public boolean bindService(Intent service, int flags, java.util.concurrent.Executor executor, ServiceConnection conn) { return bindService(service, onExecutor(executor, conn), flags); }
  public boolean bindService(Intent service, BindServiceFlags flags, java.util.concurrent.Executor executor, ServiceConnection conn) { return bindService(service, onExecutor(executor, conn), (int) flags.getValue()); }
  public boolean bindService(Intent service, ServiceConnection conn, BindServiceFlags flags) { return bindService(service, conn, (int) flags.getValue()); }
  public boolean bindIsolatedService(Intent service, int flags, String instanceName, java.util.concurrent.Executor executor, ServiceConnection conn) {
    if (instanceName == null) throw new NullPointerException("null instanceName");
    return bindService(service, flags, executor, conn);
  }
  public boolean bindIsolatedService(Intent service, BindServiceFlags flags, String instanceName, java.util.concurrent.Executor executor, ServiceConnection conn) {
    return bindIsolatedService(service, (int) flags.getValue(), instanceName, executor, conn);
  }
  public boolean bindServiceAsUser(Intent service, ServiceConnection conn, int flags, android.os.UserHandle user) { return bindService(service, conn, flags); }
  public boolean bindServiceAsUser(Intent service, ServiceConnection conn, BindServiceFlags flags, android.os.UserHandle user) { return bindService(service, conn, flags); }
  public void updateServiceGroup(ServiceConnection conn, int group, int importance) { java.util.Objects.requireNonNull(conn); }

  // ---- other activities and senders
  public void startActivities(Intent[] intents, Bundle options) { startActivities(intents); }
  public void startIntentSender(IntentSender intent, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags) throws IntentSender.SendIntentException {
    startIntentSender(intent, fillInIntent, flagsMask, flagsValues, extraFlags, null);
  }
  public void startIntentSender(IntentSender intent, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, Bundle options) throws IntentSender.SendIntentException {
    intent.sendIntent(this, 0, fillInIntent, null, null);
  }
  public boolean startInstrumentation(ComponentName className, String profileFile, Bundle arguments) { return false; }

  // ---- wallpaper (deprecated in API 15): one in-memory wallpaper
  public android.graphics.drawable.Drawable getWallpaper() { android.graphics.drawable.Drawable d = peekWallpaper(); return d != null ? d : new android.graphics.drawable.ColorDrawable(0xff000000); }
  public android.graphics.drawable.Drawable peekWallpaper() { return a2w.Wallpaper.bitmap == null ? null : new android.graphics.drawable.BitmapDrawable(getResources(), a2w.Wallpaper.bitmap); }
  public int getWallpaperDesiredMinimumWidth() { return getResources().getDisplayMetrics().widthPixels; }
  public int getWallpaperDesiredMinimumHeight() { return getResources().getDisplayMetrics().heightPixels; }
  public void setWallpaper(android.graphics.Bitmap bitmap) throws java.io.IOException { a2w.Wallpaper.bitmap = bitmap; }
  public void setWallpaper(java.io.InputStream data) throws java.io.IOException {
    android.graphics.Bitmap b = android.graphics.BitmapFactory.decodeStream(data);
    if (b == null) throw new java.io.IOException("not an image");
    a2w.Wallpaper.bitmap = b;
  }
  public void clearWallpaper() throws java.io.IOException { a2w.Wallpaper.bitmap = null; }
  public abstract Context createPackageContext(String pkg, int flags) throws PackageManager.NameNotFoundException;
  /** the configuration never changes on the page: the context itself (ContextWrapper asks its base) */
  public Context createConfigurationContext(android.content.res.Configuration c) { return this; }
  public Context createDisplayContext(android.view.Display d) { return this; }
  public Context createDeviceProtectedStorageContext() { return this; }
  public boolean isRestricted() { return false; }

  public final CharSequence getText(int id) { return getResources().getText(id); }
  public final String getString(int id) { return getResources().getString(id); }
  public final String getString(int id, Object... args) { return getResources().getString(id, args); }
  public final TypedArray obtainStyledAttributes(int[] attrs) { return getTheme().obtainStyledAttributes(attrs); }
  public final TypedArray obtainStyledAttributes(int resid, int[] attrs) { return getTheme().obtainStyledAttributes(resid, attrs); }
  public final TypedArray obtainStyledAttributes(AttributeSet set, int[] attrs) { return getTheme().obtainStyledAttributes(set, attrs, 0, 0); }
  public final TypedArray obtainStyledAttributes(AttributeSet set, int[] attrs, int defStyleAttr, int defStyleRes) { return getTheme().obtainStyledAttributes(set, attrs, defStyleAttr, defStyleRes); }
  public void registerComponentCallbacks(ComponentCallbacks cb) {}
  public void unregisterComponentCallbacks(ComponentCallbacks cb) {}
}
