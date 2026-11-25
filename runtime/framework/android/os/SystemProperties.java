package android.os;

import java.util.HashMap;

/**
 * android.os.SystemProperties (hidden): the device's system properties, as an emulator image reports them; apps may
 * set their own (in-process).
 */
public class SystemProperties {
  public static final int PROP_NAME_MAX = Integer.MAX_VALUE, PROP_VALUE_MAX = 91;
  static final HashMap<String, String> P = new HashMap<>();
  static {
    P.put("ro.build.version.sdk", String.valueOf(Build.VERSION.SDK_INT));
    P.put("ro.build.version.release", Build.VERSION.RELEASE);
    P.put("ro.build.version.codename", "REL");
    P.put("ro.build.version.incremental", Build.VERSION.INCREMENTAL);
    P.put("ro.build.id", Build.ID);
    P.put("ro.build.display.id", Build.DISPLAY);
    P.put("ro.build.type", Build.TYPE);
    P.put("ro.build.tags", Build.TAGS);
    P.put("ro.build.fingerprint", Build.FINGERPRINT);
    P.put("ro.product.model", Build.MODEL);
    P.put("ro.product.brand", Build.BRAND);
    P.put("ro.product.name", Build.PRODUCT);
    P.put("ro.product.device", Build.DEVICE);
    P.put("ro.product.board", Build.BOARD);
    P.put("ro.product.manufacturer", Build.MANUFACTURER);
    P.put("ro.product.cpu.abi", "armeabi-v7a");
    P.put("ro.product.cpu.abilist", "armeabi-v7a,armeabi");
    P.put("ro.product.cpu.abilist32", "armeabi-v7a,armeabi");
    P.put("ro.product.locale", java.util.Locale.getDefault().toLanguageTag());
    P.put("ro.hardware", Build.HARDWARE);
    P.put("ro.serialno", "unknown");
    P.put("ro.debuggable", "0");
    P.put("ro.secure", "1");
    P.put("ro.kernel.qemu", "0");
    P.put("ro.sf.lcd_density", String.valueOf(a2w.Screen.DPI));
    P.put("ro.config.low_ram", "false");
    P.put("dalvik.vm.heapgrowthlimit", "256m");
    P.put("dalvik.vm.heapsize", "512m");
    P.put("persist.sys.language", java.util.Locale.getDefault().getLanguage());
    P.put("persist.sys.country", java.util.Locale.getDefault().getCountry());
    P.put("persist.sys.timezone", java.util.TimeZone.getDefault().getID());
    P.put("gsm.operator.alpha", "");
    P.put("net.hostname", "localhost");
  }
  public static String get(String key) { return get(key, ""); }
  public static String get(String key, String def) { String v = P.get(key); return v == null || v.isEmpty() ? def : v; }
  public static int getInt(String key, int def) { try { return Integer.parseInt(get(key, "").trim()); } catch (NumberFormatException e) { return def; } }
  public static long getLong(String key, long def) { try { return Long.parseLong(get(key, "").trim()); } catch (NumberFormatException e) { return def; } }
  public static boolean getBoolean(String key, boolean def) {
    String v = get(key, "");
    if (v.equals("1") || v.equals("y") || v.equals("yes") || v.equals("on") || v.equals("true")) return true;
    if (v.equals("0") || v.equals("n") || v.equals("no") || v.equals("off") || v.equals("false")) return false;
    return def;
  }
  public static void set(String key, String val) { P.put(key, val == null ? "" : val); }
  public static void addChangeCallback(Runnable callback) {}
}
