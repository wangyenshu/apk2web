package android.os;

public class Build {
  public static final String MODEL = "Web Browser";
  public static final String MANUFACTURER = "apk2web";
  public static final String BRAND = "web";
  public static final String DEVICE = "web";
  public static final String PRODUCT = "web";
  public static final String BOARD = "web";
  public static final String HARDWARE = "wasm";
  public static final String DISPLAY = "web";
  public static final String FINGERPRINT = "web/web/web:4.4/KRT16M/1:user/release-keys";
  public static final String ID = "KRT16M";
  public static final String SERIAL = "unknown";
  public static final String TAGS = "release-keys";
  public static final String TYPE = "user";
  public static final String USER = "web";
  public static final String HOST = "web";
  // the emulator runs ARMv7-A (VFPv4, NEON) and ARMv5TE code
  public static final String CPU_ABI = "armeabi-v7a";
  public static final String[] SUPPORTED_ABIS = {"armeabi-v7a", "armeabi"}, SUPPORTED_32_BIT_ABIS = {"armeabi-v7a", "armeabi"},
      SUPPORTED_64_BIT_ABIS = {};
  public static final String CPU_ABI2 = "armeabi";
  public static final String BOOTLOADER = "unknown";
  public static final String RADIO = "unknown";
  public static final long TIME = 0;
  public static class VERSION {
    /** the level res.json was resolved for (build_res.py: the app's minimum, at least 19); not a constant */
    public static final int SDK_INT = a2w.Res.deviceSdk();
    public static final String SDK = String.valueOf(SDK_INT);
    public static final String RELEASE = release(SDK_INT);
    public static final int PREVIEW_SDK_INT = 0;
    public static final String SECURITY_PATCH = "2016-01-01";
    public static final String BASE_OS = "";
    static String release(int sdk) {
      String[] r = {"1.0", "1.0", "1.1", "1.5", "1.6", "2.0", "2.0.1", "2.1", "2.2", "2.3", "2.3.3", "3.0", "3.1", "3.2", "4.0",
          "4.0.3", "4.1", "4.2", "4.3", "4.4", "4.4W", "5.0", "5.1", "6.0", "7.0", "7.1", "8.0", "8.1", "9", "10", "11", "12",
          "12L", "13", "14", "15"};
      return sdk < r.length ? r[sdk] : String.valueOf(sdk - 20);
    }
    public static final String INCREMENTAL = "1";
    public static final String CODENAME = "REL";
  }
  public static class VERSION_CODES {
    public static final int BASE = 1, CUPCAKE = 3, DONUT = 4, ECLAIR = 5, FROYO = 8, GINGERBREAD = 9, HONEYCOMB = 11,
        HONEYCOMB_MR1 = 12, HONEYCOMB_MR2 = 13, ICE_CREAM_SANDWICH = 14, ICE_CREAM_SANDWICH_MR1 = 15, JELLY_BEAN = 16,
        JELLY_BEAN_MR1 = 17, JELLY_BEAN_MR2 = 18, KITKAT = 19, KITKAT_WATCH = 20, LOLLIPOP = 21, LOLLIPOP_MR1 = 22, M = 23,
        N = 24, N_MR1 = 25, O = 26, O_MR1 = 27, P = 28, Q = 29, R = 30, S = 31, S_V2 = 32, TIRAMISU = 33,
        UPSIDE_DOWN_CAKE = 34, VANILLA_ICE_CREAM = 35, CUR_DEVELOPMENT = 10000;
  }
  public static String getRadioVersion() { return ""; }
}
