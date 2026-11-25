package a2w;

import android.util.DisplayMetrics;

/** Parsing of attribute values as written in compiled XML ("@drawable/x", "12.0dip", "#ff00ff00"...). */
public final class Values {
  private Values() {}
  public static final DisplayMetrics METRICS = new DisplayMetrics();
  static { METRICS.setToDefaults(); }

  // framework resources the game refers to (real android.R ids where it matters)
  private static final java.util.HashMap<String, Integer> FW = new java.util.HashMap<>();
  private static final java.util.HashMap<Integer, String> FW_NAMES = new java.util.HashMap<>();
  private static int nextFw = 0x01ff0000;
  static {
    fw("color/darker_gray", 0x01060000); fw("color/white", 0x0106000b); fw("color/black", 0x0106000c);
    fw("color/transparent", 0x0106000d); fw("color/background_dark", 0x0106000e); fw("color/background_light", 0x0106000f);
    fw("anim/fade_in", 0x010a0000); fw("anim/fade_out", 0x010a0001); fw("anim/slide_in_left", 0x010a0002); fw("anim/slide_out_right", 0x010a0003);
    fw("id/content", 0x01020002); fw("id/text1", 0x01020014); fw("id/message", 0x0102000b); fw("id/title", 0x01020016);
    fw("id/button1", 0x01020019); fw("id/button2", 0x0102001a); fw("id/button3", 0x0102001b); fw("id/progress", 0x0102000d);
    fw("style/Theme", 0x01030005); fw("style/Theme.NoTitleBar", 0x01030006); fw("style/Theme.NoTitleBar.Fullscreen", 0x01030007);
    fw("style/Theme.Dialog", 0x0103000b); fw("style/Theme.Translucent", 0x0103000f); fw("style/Theme.Translucent.NoTitleBar", 0x01030010);
    fw("style/Theme.Translucent.NoTitleBar.Fullscreen", 0x01030011); fw("style/Theme.Panel", 0x01030052);
  }
  static void fw(String n, int id) { FW.put(n, id); FW_NAMES.put(id, n); }
  /** the real android.R id (res.json names every public framework resource under "android:<type>") */
  public static int frameworkId(String typeName) {
    Integer i = FW.get(typeName);
    if (i == null) {
      int slash = typeName.indexOf('/');
      int real = slash < 0 ? 0 : Res.id("android:" + typeName.substring(0, slash), typeName.substring(slash + 1));
      i = real != 0 ? real : nextFw++;
      fw(typeName, i);
    }
    return i;
  }
  public static String frameworkName(int id) {
    String n = FW_NAMES.get(id);
    if (n == null) { n = Res.name(id); if (n != null && n.startsWith("android:")) { n = n.substring(8); fw(n, id); } else n = null; }
    return n;
  }
  public static boolean isFramework(int id) { return (id >>> 24) == 0x01; }

  public static boolean isRef(String v) { return v != null && v.length() > 1 && v.charAt(0) == '@' && !v.equals("@null"); }

  /** "@type/name", "@+id/name", "@android:type/name", "@0x7f..." -> id (0 for @null/unknown). */
  public static int refId(String v) {
    if (v == null || v.length() < 2 || v.charAt(0) != '@' && v.charAt(0) != '?') return 0;
    String s = v.substring(1);
    if (s.equals("null")) return 0;
    if (s.startsWith("+")) s = s.substring(1);
    if (s.startsWith("0x")) return (int) Long.parseLong(s.substring(2), 16);
    boolean android = false;
    if (s.startsWith("android:")) { android = true; s = s.substring(8); }
    else if (s.startsWith("*android:")) { android = true; s = s.substring(9); }
    int colon = s.indexOf(':');
    if (colon >= 0) s = s.substring(colon + 1);
    if (android) return frameworkId(s);
    int slash = s.indexOf('/');
    if (slash < 0) return 0;
    return Res.id(s.substring(0, slash), s.substring(slash + 1));
  }

  public static int parseColor(String s) {
    if (s == null) return 0;
    s = s.trim();
    if (s.startsWith("#")) {
      String h = s.substring(1);
      long v = Long.parseLong(h, 16);
      switch (h.length()) {
        case 3: { int r = (int) (v >> 8) & 15, g = (int) (v >> 4) & 15, b = (int) v & 15; return 0xff000000 | r * 0x110000 | g * 0x1100 | b * 0x11; }
        case 4: { int a = (int) (v >> 12) & 15, r = (int) (v >> 8) & 15, g = (int) (v >> 4) & 15, b = (int) v & 15; return a * 0x11000000 | r * 0x110000 | g * 0x1100 | b * 0x11; }
        case 6: return 0xff000000 | (int) v;
        default: return (int) v;
      }
    }
    return android.graphics.Color.parseColor(s);
  }

  /** dimension -> pixels (float); bare numbers are pixels */
  public static float dimension(String s) {
    if (s == null) return 0;
    s = s.trim();
    int i = s.length();
    while (i > 0 && Character.isLetter(s.charAt(i - 1))) i--;
    float v;
    try { v = Float.parseFloat(s.substring(0, i)); } catch (NumberFormatException e) { return 0; }
    String u = s.substring(i);
    switch (u) {
      case "dip": case "dp": return v * METRICS.density;
      case "sp": return v * METRICS.scaledDensity;
      case "pt": return v * METRICS.xdpi / 72f;
      case "in": return v * METRICS.xdpi;
      case "mm": return v * METRICS.xdpi / 25.4f;
      default: return v;
    }
  }
  public static int dimensionPixelSize(float f) {
    int r = (int) (f + 0.5f);
    if (r != 0) return r;
    if (f == 0) return 0;
    return f > 0 ? 1 : -1;
  }
  public static int dimensionPixelOffset(float f) { return (int) f; }

  public static boolean isDimension(String s) {
    if (s == null || s.isEmpty()) return false;
    char c = s.charAt(0);
    return (c >= '0' && c <= '9') || c == '-' || c == '.';
  }

  public static int layoutSize(String s) {
    if (s == null) return -2;
    switch (s) {
      case "fill_parent": case "match_parent": return -1;
      case "wrap_content": return -2;
    }
    if (isRef(s)) return dimensionPixelSize(dimension(Res.dimen(refId(s))));
    return dimensionPixelSize(dimension(s));
  }

  public static int parseInt(String s, int def) {
    if (s == null) return def;
    s = s.trim();
    try {
      if (s.startsWith("0x") || s.startsWith("0X")) return (int) Long.parseLong(s.substring(2), 16);
      if (s.startsWith("#")) return parseColor(s);
      if (s.indexOf('.') >= 0) return (int) Float.parseFloat(s);
      return Integer.parseInt(s);
    } catch (NumberFormatException e) { return def; }
  }
  public static float parseFloat(String s, float def) {
    if (s == null) return def;
    try { return Float.parseFloat(s.trim()); } catch (NumberFormatException e) { return def; }
  }
  public static boolean parseBool(String s, boolean def) {
    if (s == null) return def;
    if (s.equals("true")) return true;
    if (s.equals("false")) return false;
    return def;
  }

  private static final java.util.HashMap<String, Integer> INPUT_TYPES = new java.util.HashMap<>(), IME_OPTIONS = new java.util.HashMap<>();
  static {
    Object[] it = {"none", 0x0, "text", 0x1, "textCapCharacters", 0x1001, "textCapWords", 0x2001, "textCapSentences", 0x4001, "textAutoCorrect", 0x8001,
        "textAutoComplete", 0x10001, "textMultiLine", 0x20001, "textImeMultiLine", 0x40001, "textNoSuggestions", 0x80001,
        "textEnableTextConversionSuggestions", 0x100001, "textUri", 0x11, "textEmailAddress", 0x21, "textEmailSubject", 0x31, "textShortMessage", 0x41,
        "textLongMessage", 0x51, "textPersonName", 0x61, "textPostalAddress", 0x71, "textPassword", 0x81, "textVisiblePassword", 0x91,
        "textWebEditText", 0xa1, "textFilter", 0xb1, "textPhonetic", 0xc1, "textWebEmailAddress", 0xd1, "textWebPassword", 0xe1, "number", 0x2,
        "numberSigned", 0x1002, "numberDecimal", 0x2002, "numberPassword", 0x12, "phone", 0x3, "datetime", 0x4, "date", 0x14, "time", 0x24};
    for (int i = 0; i < it.length; i += 2) INPUT_TYPES.put((String) it[i], (Integer) it[i + 1]);
    Object[] io = {"normal", 0, "actionUnspecified", 0, "actionNone", 1, "actionGo", 2, "actionSearch", 3, "actionSend", 4, "actionNext", 5, "actionDone", 6,
        "actionPrevious", 7, "flagNoPersonalizedLearning", 0x1000000, "flagNoFullscreen", 0x2000000, "flagNavigatePrevious", 0x4000000,
        "flagNavigateNext", 0x8000000, "flagNoExtractUi", 0x10000000, "flagNoAccessoryAction", 0x20000000, "flagNoEnterAction", 0x40000000,
        "flagForceAscii", 0x80000000};
    for (int i = 0; i < io.length; i += 2) IME_OPTIONS.put((String) io[i], (Integer) io[i + 1]);
  }
  /** android:inputType flags ("text|textCapWords", or a number) */
  public static int inputType(String v) { return flags(v, INPUT_TYPES); }
  /** android:imeOptions flags ("actionSearch|flagNoExtractUi", or a number) */
  public static int imeOptions(String v) { return flags(v, IME_OPTIONS); }
  private static int flags(String v, java.util.HashMap<String, Integer> names) {
    if (v == null) return 0;
    int r = 0;
    for (String p : v.split("\\|")) {
      p = p.trim();
      Integer f = names.get(p);
      if (f != null) r |= f;
      else if (!p.isEmpty()) r |= (int) parseLong(p);
    }
    return r;
  }
  private static long parseLong(String p) {
    try { return p.startsWith("0x") || p.startsWith("0X") ? Long.parseLong(p.substring(2), 16) : Long.parseLong(p); } catch (NumberFormatException e) { return 0; }
  }
  public static int gravity(String s) {
    if (s == null) return -1;
    int g = 0;
    for (String p : s.split("\\|")) {
      switch (p.trim()) {
        case "top": g |= 0x30; break;
        case "bottom": g |= 0x50; break;
        case "left": g |= 0x03; break;
        case "right": g |= 0x05; break;
        case "start": g |= 0x00800003; break;
        case "end": g |= 0x00800005; break;
        case "center_vertical": g |= 0x10; break;
        case "fill_vertical": g |= 0x70; break;
        case "center_horizontal": g |= 0x01; break;
        case "fill_horizontal": g |= 0x07; break;
        case "center": g |= 0x11; break;
        case "fill": g |= 0x77; break;
        case "clip_vertical": g |= 0x80; break;
        case "clip_horizontal": g |= 0x08; break;
        default: try { g |= Integer.decode(p.trim()); } catch (Exception e) {}
      }
    }
    return g;
  }
}
