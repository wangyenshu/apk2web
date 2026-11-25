package a2w;

import java.util.ArrayList;
import java.util.HashMap;
import org.teavm.jso.JSObject;

/** Style resolution: app styles from res.json plus the few framework styles/themes the game names. */
public final class Styles {
  private Styles() {}
  private static final HashMap<Integer, String[]> cache = new HashMap<>();

  // framework styles: ns,name,value triples (values already concrete)
  static final HashMap<String, String[]> FRAMEWORK = new HashMap<>();
  static {
    FRAMEWORK.put("Widget", new String[0]);
    // text colors follow the theme (?textColorPrimary...: white on the dark themes, black on the light ones)
    FRAMEWORK.put("TextAppearance", new String[] {"a", "textSize", "16sp", "a", "textColor", "?android:attr/textColorPrimary"});
    FRAMEWORK.put("TextAppearance.Small", new String[] {"a", "textSize", "14sp", "a", "textColor", "?android:attr/textColorSecondary"});
    FRAMEWORK.put("TextAppearance.Medium", new String[] {"a", "textSize", "18sp", "a", "textColor", "?android:attr/textColorSecondary"});
    FRAMEWORK.put("TextAppearance.Large", new String[] {"a", "textSize", "22sp", "a", "textColor", "?android:attr/textColorPrimary"});
    FRAMEWORK.put("TextAppearance.Small.Inverse", new String[] {"a", "textSize", "14sp", "a", "textColor", "?android:attr/textColorSecondaryInverse"});
    FRAMEWORK.put("TextAppearance.Medium.Inverse", new String[] {"a", "textSize", "18sp", "a", "textColor", "?android:attr/textColorSecondaryInverse"});
    FRAMEWORK.put("Widget.ProgressBar", new String[] {"a", "indeterminateOnly", "true", "a", "indeterminateDrawable", "@android:drawable/progress_medium_white",
        "a", "minWidth", "48dip", "a", "maxWidth", "48dip", "a", "minHeight", "48dip", "a", "maxHeight", "48dip"});
    FRAMEWORK.put("Widget.ProgressBar.Small", new String[] {"a", "indeterminateOnly", "true", "a", "indeterminateDrawable", "@android:drawable/progress_small_white",
        "a", "minWidth", "16dip", "a", "maxWidth", "16dip", "a", "minHeight", "16dip", "a", "maxHeight", "16dip"});
    FRAMEWORK.put("Widget.ProgressBar.Large", new String[] {"a", "indeterminateOnly", "true", "a", "indeterminateDrawable", "@android:drawable/progress_large_white",
        "a", "minWidth", "76dip", "a", "maxWidth", "76dip", "a", "minHeight", "76dip", "a", "maxHeight", "76dip"});
    FRAMEWORK.put("Widget.ProgressBar.Horizontal", new String[] {"a", "indeterminateOnly", "false", "a", "progressDrawable", "@android:drawable/progress_horizontal",
        "a", "indeterminateDrawable", "@android:drawable/progress_indeterminate_horizontal", "a", "minHeight", "20dip", "a", "maxHeight", "20dip"});
    FRAMEWORK.put("Widget.EditText", new String[] {"a", "focusable", "true", "a", "focusableInTouchMode", "true", "a", "clickable", "true",
        "a", "background", "@android:drawable/edit_text", "a", "textSize", "18sp", "a", "textColor", "#ff000000", "a", "gravity", "center_vertical"});
    // compound buttons: text beside an indicator (no button background); the indicators are drawn in code (WidgetDrawables)
    FRAMEWORK.put("Widget.CompoundButton", new String[] {"a", "focusable", "true", "a", "clickable", "true", "a", "textSize", "16sp",
        "a", "textColor", "?android:attr/textColorPrimaryDisableOnly", "a", "gravity", "center_vertical|left"});
    FRAMEWORK.put("Widget.CompoundButton.CheckBox", new String[] {"a", "button", "@android:drawable/btn_check", "a", "background", "@android:drawable/btn_check_label_background"});
    FRAMEWORK.put("Widget.CompoundButton.RadioButton", new String[] {"a", "button", "@android:drawable/btn_radio", "a", "background", "@android:drawable/btn_check_label_background"});
    FRAMEWORK.put("Widget.CompoundButton.Star", new String[] {"a", "button", "@android:drawable/btn_star", "a", "background", "@android:drawable/btn_check_label_background"});
    FRAMEWORK.put("Widget.CompoundButton.Switch", new String[] {"a", "textOn", "ON", "a", "textOff", "OFF", "a", "thumbTextPadding", "12dip", "a", "switchPadding", "16dip"});
    FRAMEWORK.put("Widget.Button.Toggle", new String[] {"a", "background", "@android:drawable/btn_toggle_bg", "a", "textOn", "ON", "a", "textOff", "OFF"});
    FRAMEWORK.put("Widget.Button", new String[] {"a", "background", "@android:drawable/btn_default", "a", "focusable", "true", "a", "clickable", "true",
        "a", "textSize", "14sp", "a", "textColor", "#ff000000", "a", "gravity", "center"});
    FRAMEWORK.put("Theme", new String[] {"a", "windowBackground", "#ff000000", "a", "windowIsFloating", "false", "a", "windowIsTranslucent", "false",
        "a", "backgroundDimEnabled", "false", "a", "windowNoTitle", "false", "a", "windowFullscreen", "false", "a", "backgroundDimAmount", "0.6"});
    FRAMEWORK.put("Theme.NoTitleBar", new String[] {"a", "windowNoTitle", "true"});
    FRAMEWORK.put("Theme.NoTitleBar.Fullscreen", new String[] {"a", "windowFullscreen", "true"});
    FRAMEWORK.put("Theme.Black.NoTitleBar.Fullscreen", new String[] {"a", "windowNoTitle", "true", "a", "windowFullscreen", "true"});
    FRAMEWORK.put("Theme.Translucent", new String[] {"a", "windowBackground", "#00000000", "a", "windowIsTranslucent", "true", "a", "backgroundDimEnabled", "false"});
    FRAMEWORK.put("Theme.Translucent.NoTitleBar", new String[] {"a", "windowNoTitle", "true"});
    FRAMEWORK.put("Theme.Translucent.NoTitleBar.Fullscreen", new String[] {"a", "windowFullscreen", "true"});
    FRAMEWORK.put("Theme.Dialog", new String[] {"a", "windowBackground", "@android:drawable/panel_background", "a", "windowIsFloating", "true",
        "a", "backgroundDimEnabled", "true", "a", "windowNoTitle", "false"});
    FRAMEWORK.put("Theme.Panel", new String[] {"a", "windowBackground", "#00000000", "a", "windowIsFloating", "true", "a", "backgroundDimEnabled", "false", "a", "windowNoTitle", "true"});
  }

  private static String parentOfFramework(String name) {
    int i = name.lastIndexOf('.');
    if (name.startsWith("Theme") && i < 0 && !name.equals("Theme")) return "Theme";
    return i < 0 ? null : name.substring(0, i);
  }

  /** Items of a style and its ancestors, nearest first. */
  public static String[] items(int styleId) {
    if (styleId == 0) return new String[0];
    String[] c = cache.get(styleId);
    if (c != null) return c;
    if (!resolving.add(styleId)) return new String[0];   // a parent cycle
    try {
      ArrayList<String> out = new ArrayList<>();
      JSObject s = Res.style(styleId);
      if (Values.isFramework(styleId) && s == null) {
        String n = Values.frameworkName(styleId);
        if (n != null && n.startsWith("style/")) addFramework(n.substring(6), out);
      } else if (s != null) {
        for (String v : Res.split(Res.styleItems(s))) out.add(v);
        // the compiled parent (aapt resolves a dotted name's implicit parent at build time; an empty one is none)
        String parent = Res.styleParent(s);
        int pid = 0;
        if (parent != null && !parent.isEmpty()) pid = parentId(parent);
        if (pid != 0 && pid != styleId) for (String v : items(pid)) out.add(v);
      }
      c = out.toArray(new String[0]);
      cache.put(styleId, c);
      return c;
    } finally { resolving.remove(styleId); }
  }
  private static final java.util.HashSet<Integer> resolving = new java.util.HashSet<>();
  /** a style's parent: "@style/x", "@android:style/x", "android:x", "x" */
  static int parentId(String p) {
    if (p.startsWith("@")) return Values.refId(p);
    if (p.startsWith("android:")) return Values.frameworkId("style/" + p.substring(8));
    if (p.startsWith("*android:")) return Values.frameworkId("style/" + p.substring(9));
    return Values.refId("@style/" + p);
  }

  static void addFramework(String name, ArrayList<String> out) {
    while (name != null) {
      String[] it = FRAMEWORK.get(name);
      if (it != null) for (String v : it) out.add(v);
      name = parentOfFramework(name);
    }
  }

  public static String[] framework(String name) {
    ArrayList<String> out = new ArrayList<>();
    addFramework(name, out);
    return out.toArray(new String[0]);
  }

  public static String find(String[] items, String ns, String name) {
    if (items == null) return null;
    for (int i = 0; i + 2 < items.length; i += 3) if (items[i + 1].equals(name) && items[i].equals(ns)) return items[i + 2];
    return null;
  }

  /** the platform theme a context without one gets (Resources.selectDefaultTheme, by the app's target API level) */
  public static int defaultTheme() {
    int t = Res.targetSdk();
    if (t <= 0) t = Res.deviceSdk();
    String name = t < 11 ? "Theme" : t < 14 ? "Theme.Holo" : t < 24 || Res.deviceSdk() < 24 ? "Theme.DeviceDefault" : "Theme.DeviceDefault.Light.DarkActionBar";
    return Values.frameworkId("style/" + name);
  }

  /** theme attribute references (?android:attr/x) used by the game's styles */
  public static String themeAttr(String v) {
    String n = v.substring(v.lastIndexOf('/') + 1);
    if (n.startsWith("?")) n = n.substring(1);
    if (n.startsWith("android:")) n = n.substring(8);
    switch (n) {
      case "textColorPrimary": return "#ffffffff";
      case "textColorSecondary": return "#ffbebebe";
      case "textColorPrimaryInverse": return "#ff000000";
      case "textColorSecondaryInverse": return "#ff323232";
      case "colorBackground": return "#ff000000";
      case "colorForeground": return "#ffffffff";
      case "progressBarStyleHorizontal": return "@android:style/Widget.ProgressBar.Horizontal";
      case "progressBarStyleSmall": return "@android:style/Widget.ProgressBar.Small";
      case "progressBarStyle": return "@android:style/Widget.ProgressBar";
      case "textAppearanceSmall": return "@android:style/TextAppearance.Small";
      case "textAppearanceMedium": return "@android:style/TextAppearance.Medium";
      case "textAppearanceLarge": return "@android:style/TextAppearance.Large";
    }
    return null;
  }
}
