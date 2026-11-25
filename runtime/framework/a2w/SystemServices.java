package a2w;

import android.content.Context;
import java.util.HashMap;

public final class SystemServices {
  private SystemServices() {}
  static final HashMap<String, Object> cache = new HashMap<>();

  public static Object get(Context ctx, String name) {
    if (name == null) return null;
    Object o = cache.get(name);
    if (o != null) return o;
    switch (name) {
      case Context.ACTIVITY_SERVICE: o = new android.app.ActivityManager(); break;
      case Context.ALARM_SERVICE: o = new android.app.AlarmManager(); break;
      case Context.CONNECTIVITY_SERVICE: o = new android.net.ConnectivityManager(); break;
      case "download": o = new android.app.DownloadManager(); break;
      case Context.INPUT_METHOD_SERVICE: o = android.view.inputmethod.InputMethodManager.get(); break;
      case Context.KEYGUARD_SERVICE: o = new android.app.KeyguardManager(); break;
      case Context.NOTIFICATION_SERVICE: o = new android.app.NotificationManager(); break;
      case Context.TELEPHONY_SERVICE: o = new android.telephony.TelephonyManager(); break;
      case Context.SENSOR_SERVICE: o = new android.hardware.SensorManager(); break;
      case Context.WINDOW_SERVICE: o = Windows.manager(); break;
      case Context.AUDIO_SERVICE: o = new android.media.AudioManager(); break;
      case Context.POWER_SERVICE: o = new android.os.PowerManager(); break;
      case Context.VIBRATOR_SERVICE: o = new android.os.Vibrator(); break;
      case Context.WIFI_SERVICE: o = new android.net.wifi.WifiManager(); break;
      case Context.CLIPBOARD_SERVICE: o = new android.content.ClipboardManager(); break;
      case Context.LOCATION_SERVICE: o = new android.location.LocationManager(); break;
      case Context.UI_MODE_SERVICE: o = new android.app.UiModeManager(); break;
      case "accessibility": o = new android.view.accessibility.AccessibilityManager(); break;
      case Context.SEARCH_SERVICE: o = new android.app.SearchManager(ctx); break;
      case "vibrator_manager": o = new VibratorManagerImpl(); break;
      case "jobscheduler": o = new JobSchedulerImpl(); break;
      // services whose hardware or feature this device lacks (null, as on such a device)
      case "fingerprint": case "wifip2p": case "wifiaware": case "wifirtt": case "lights": case "telephony_ims": return null;
      // managers declared from the SDK (methods not implemented log a warning; absent hardware answers as a device without it)
      case "storage": o = new android.os.storage.StorageManager((a2w.Hidden) null); break;
      case "batterymanager": o = new android.os.BatteryManager((a2w.Hidden) null); break;
      case "display": o = new android.hardware.display.DisplayManager((a2w.Hidden) null); break;
      case "account": o = new android.accounts.AccountManager((a2w.Hidden) null); break;
      case "user": o = new android.os.UserManager((a2w.Hidden) null); break;
      case "shortcut": o = new android.content.pm.ShortcutManager((a2w.Hidden) null); break;
      case "appops": o = new android.app.AppOpsManager((a2w.Hidden) null); break;
      case "camera": o = new android.hardware.camera2.CameraManager((a2w.Hidden) null); break;
      case "bluetooth": o = new android.bluetooth.BluetoothManager((a2w.Hidden) null); break;
      case "usb": o = new android.hardware.usb.UsbManager((a2w.Hidden) null); break;
      case "media_session": o = new android.media.session.MediaSessionManager((a2w.Hidden) null); break;
      case "media_router": o = new android.media.MediaRouter((a2w.Hidden) null); break;
      case "textservices": o = new android.view.textservice.TextServicesManager((a2w.Hidden) null); break;
      case "captioning": o = new android.view.accessibility.CaptioningManager((a2w.Hidden) null); break;
      case "consumer_ir": o = new android.hardware.ConsumerIrManager((a2w.Hidden) null); break;
      case "biometric": o = new android.hardware.biometrics.BiometricManager((a2w.Hidden) null); break;
      case "wallpaper": o = new android.app.WallpaperManager((a2w.Hidden) null); break;
      case "device_policy": o = new android.app.admin.DevicePolicyManager((a2w.Hidden) null); break;
      case "usagestats": o = new android.app.usage.UsageStatsManager((a2w.Hidden) null); break;
      case "print": o = new android.print.PrintManager((a2w.Hidden) null); break;
      case "servicediscovery": o = new android.net.nsd.NsdManager((a2w.Hidden) null); break;
      case "nfc": o = new android.nfc.NfcManager((a2w.Hidden) null); break;
      case "role": o = new android.app.role.RoleManager((a2w.Hidden) null); break;
      case "locale": o = new android.app.LocaleManager((a2w.Hidden) null); break;
      case "restrictions": o = new android.content.RestrictionsManager((a2w.Hidden) null); break;
      case "launcherapps": o = new android.content.pm.LauncherApps((a2w.Hidden) null); break;
      case "telecom": o = new android.telecom.TelecomManager((a2w.Hidden) null); break;
      case "telephony_subscription_service": o = new android.telephony.SubscriptionManager((a2w.Hidden) null); break;
      case "statusbar": o = new android.app.StatusBarManager((a2w.Hidden) null); break;
      case "midi": o = new android.media.midi.MidiManager((a2w.Hidden) null); break;
      case "hardware_properties": o = new android.os.HardwarePropertiesManager((a2w.Hidden) null); break;
      case "game": o = new android.app.GameManager((a2w.Hidden) null); break;
      case "systemhealth": o = new android.os.health.SystemHealthManager((a2w.Hidden) null); break;
      case "storagestats": o = new android.app.usage.StorageStatsManager((a2w.Hidden) null); break;
      case "dropbox": o = new android.os.DropBoxManager((a2w.Hidden) null); break;
      case "media_projection": o = new android.media.projection.MediaProjectionManager((a2w.Hidden) null); break;
      case "connectivity_diagnostics": o = new android.net.ConnectivityDiagnosticsManager((a2w.Hidden) null); break;
      case "credential": o = new android.credentials.CredentialManager((a2w.Hidden) null); break;
      case "autofill": o = new android.view.autofill.AutofillManager((a2w.Hidden) null); break;
      case "translation": o = new android.view.translation.TranslationManager((a2w.Hidden) null); break;
      case "appwidget": o = new android.appwidget.AppWidgetManager((a2w.Hidden) null); break;
      case "netstats": o = new android.app.usage.NetworkStatsManager((a2w.Hidden) null); break;
      case "textclassification": o = new android.view.textclassifier.TextClassificationManager((a2w.Hidden) null); break;
      case "people": o = new android.app.people.PeopleManager((a2w.Hidden) null); break;
      case "companiondevice": o = new android.companion.CompanionDeviceManager((a2w.Hidden) null); break;
      case "blob_store": o = new android.app.blob.BlobStoreManager((a2w.Hidden) null); break;
      case "crossprofileapps": o = new android.content.pm.CrossProfileApps((a2w.Hidden) null); break;
      case "carrier_config": o = new android.telephony.CarrierConfigManager((a2w.Hidden) null); break;
      case "euicc": o = new android.telephony.euicc.EuiccManager((a2w.Hidden) null); break;
      case "tv_input": o = new android.media.tv.TvInputManager((a2w.Hidden) null); break;
      case "overlay": o = new android.content.om.OverlayManager((a2w.Hidden) null); break;
      case "performance_hint": o = new android.os.PerformanceHintManager((a2w.Hidden) null); break;
      case "domain_verification": o = new android.content.pm.verify.domain.DomainVerificationManager((a2w.Hidden) null); break;
      case "file_integrity": o = new android.security.FileIntegrityManager((a2w.Hidden) null); break;
      case "ipsec": o = new android.net.IpSecManager((a2w.Hidden) null); break;
      case "vpn_management": o = new android.net.VpnManager((a2w.Hidden) null); break;
      case "app_search": o = new android.app.appsearch.AppSearchManager((a2w.Hidden) null); break;
      case "media_communication": o = new android.media.MediaCommunicationManager((a2w.Hidden) null); break;
      case "media_metrics": o = new android.media.metrics.MediaMetricsManager((a2w.Hidden) null); break;
      case "grammatical_inflection": o = new android.app.GrammaticalInflectionManager((a2w.Hidden) null); break;
      case "display_hash": o = new android.view.displayhash.DisplayHashManager((a2w.Hidden) null); break;
      case "bugreport": o = new android.os.BugreportManager((a2w.Hidden) null); break;
      case "security_state": o = new android.os.SecurityStateManager((a2w.Hidden) null); break;
      case "profiling": o = new android.os.ProfilingManager((a2w.Hidden) null); break;
      case "persistent_data_block": o = new android.service.persistentdata.PersistentDataBlockManager((a2w.Hidden) null); break;
      case "contact_keys": o = new android.provider.E2eeContactKeysManager((a2w.Hidden) null); break;
      case "device_lock": o = new android.devicelock.DeviceLockManager((a2w.Hidden) null); break;
      case "tv_interactive_app": o = new android.media.tv.interactive.TvInteractiveAppManager((a2w.Hidden) null); break;
      case "virtualdevice": o = new android.companion.virtual.VirtualDeviceManager((a2w.Hidden) null); break;
      case "healthconnect": o = new android.health.connect.HealthConnectManager((a2w.Hidden) null); break;
      default: Host.log(5, "SystemServices: unknown service " + name); return null;
    }
    cache.put(name, o);
    return o;
  }
}
