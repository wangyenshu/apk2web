package android.provider;

import android.content.ContentResolver;

public final class Settings {
  public static final String ACTION_SETTINGS = "android.settings.SETTINGS", ACTION_WIRELESS_SETTINGS = "android.settings.WIRELESS_SETTINGS", ACTION_WIFI_SETTINGS = "android.settings.WIFI_SETTINGS";
  public static class NameValueTable {}
  public static final class Secure extends NameValueTable {
    public static final android.net.Uri CONTENT_URI = android.net.Uri.parse("content://settings/secure");
    public static final String ANDROID_ID = "android_id";
    public static String getString(ContentResolver r, String name) { return ANDROID_ID.equals(name) ? a2w.Host.deviceId() : null; }
    public static int getInt(ContentResolver r, String name, int def) { return def; }
  }
  public static final class System extends NameValueTable {
    public static final android.net.Uri CONTENT_URI = android.net.Uri.parse("content://settings/system");
    public static final android.net.Uri DEFAULT_RINGTONE_URI = android.net.Uri.parse("content://settings/system/ringtone"), DEFAULT_NOTIFICATION_URI = android.net.Uri.parse("content://settings/system/notification_sound"), DEFAULT_ALARM_ALERT_URI = android.net.Uri.parse("content://settings/system/alarm_alert");
    public static final String ANDROID_ID = "android_id", SCREEN_BRIGHTNESS = "screen_brightness", AIRPLANE_MODE_ON = "airplane_mode_on";
    public static String getString(ContentResolver r, String name) { return ANDROID_ID.equals(name) ? a2w.Host.deviceId() : null; }
    public static int getInt(ContentResolver r, String name, int def) { return def; }
    public static int getInt(ContentResolver r, String name) throws SettingNotFoundException { throw new SettingNotFoundException(name); }
    public static boolean putInt(ContentResolver r, String name, int v) { return false; }
  }
  public static class SettingNotFoundException extends android.util.AndroidException { public SettingNotFoundException(String m) { super(m); } }
}
