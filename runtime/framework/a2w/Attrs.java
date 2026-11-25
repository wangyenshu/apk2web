package a2w;

import android.util.AttributeSet;

/**
 * AttributeSet over a compiled XML element, with the element's style= and the
 * widget's default style folded in (explicit attributes win, then style, then defaults).
 */
public final class Attrs implements AttributeSet {
  public final XmlNode node;
  private final String[] own;      // explicit
  private final String[] style;    // from style="@style/..."
  private String[] defaults;       // widget default style (set by the widget constructor)
  private final int styleId;
  /** the theme "?attr/x" values resolve against (the inflating context's) */
  public android.content.res.Resources.Theme theme;
  /** the theme of the inflation in progress (LayoutInflater), for attribute sets made during it */
  public static android.content.res.Resources.Theme current;

  public Attrs(XmlNode n) {
    node = n;
    own = n.attrs;
    theme = current;
    String st = n.get("", "style");
    if (st != null && st.startsWith("?")) st = resolveRef(st);
    styleId = st == null ? 0 : Values.refId(st);
    style = Styles.items(styleId);
  }
  public Attrs(String[] items) { node = null; own = items; style = new String[0]; styleId = 0; theme = current; }
  /** a widget made in code: no attributes of its own, the widget's defaults (a theme default style goes ahead of them) */
  public static Attrs defaultsOnly(String[] defaults) { return new Attrs(new String[0]).withDefaults(defaults); }
  /** a "?attr/x" reference: the theme's value, else the platform default */
  String resolveRef(String v) {
    if (theme != null) { String r = theme.resolve(v); if (r == null || !r.startsWith("?")) return r; }
    return Styles.themeAttr(v);
  }
  /** a default style named by a theme attribute (the widget's defStyleAttr), ahead of the widget's own defaults */
  public Attrs withThemeStyle(int defStyleAttr) {
    if (theme == null || defStyleAttr == 0) return this;
    String n = android.content.res.TypedArray.attrNameOf(defStyleAttr);
    if (n == null) return this;
    String v = theme.get(Values.isFramework(defStyleAttr) ? "a" : "x", n);
    if (v == null || !Values.isRef(v)) return this;
    String[] d = Styles.items(Values.refId(v));
    if (d.length == 0) return this;
    if (defaults == null) defaults = d;
    else {
      String[] m = new String[d.length + defaults.length];
      System.arraycopy(d, 0, m, 0, d.length);
      System.arraycopy(defaults, 0, m, d.length, defaults.length);
      defaults = m;
    }
    return this;
  }

  public static String ns(String uri) {
    if (uri == null) return "";
    if (uri.equals("http://schemas.android.com/apk/res/android")) return "a";
    if (uri.startsWith("http://schemas.android.com/apk/res")) return "x";
    return uri;
  }

  /** Raw value lookup with style/defaults; resolves theme references. */
  public String get(String ns, String name) {
    String v = null;
    for (int i = 0; i + 2 < own.length; i += 3) if (own[i + 1].equals(name) && own[i].equals(ns)) { v = own[i + 2]; break; }
    if (v == null) v = Styles.find(style, ns, name);
    if (v == null && defaults != null) v = Styles.find(defaults, ns, name);
    if (v == null && ns.equals("a")) v = textAppearance(name);
    if (v != null && v.startsWith("?")) v = resolveRef(v);
    return v;
  }
  private String textAppearance(String name) {
    if (!name.equals("textSize") && !name.equals("textColor") && !name.equals("textStyle") && !name.equals("typeface")) return null;
    String ta = null;
    for (int i = 0; i + 2 < own.length; i += 3) if (own[i + 1].equals("textAppearance") && own[i].equals("a")) ta = own[i + 2];
    if (ta == null) ta = Styles.find(style, "a", "textAppearance");
    if (ta == null && defaults != null) ta = Styles.find(defaults, "a", "textAppearance");
    if (ta == null && theme != null) ta = theme.get("a", "textAppearance");   // the theme's default text appearance
    if (ta == null) return null;
    if (ta.startsWith("?")) ta = resolveRef(ta);
    if (ta == null || !Values.isRef(ta)) return null;
    String v = Styles.find(Styles.items(Values.refId(ta)), "a", name);
    return v != null && v.startsWith("?") ? resolveRef(v) : v;
  }
  public String a(String name) { return get("a", name); }
  public boolean has(String name) { return get("a", name) != null; }

  /** Widgets call this from their constructor with their framework default style. */
  public Attrs withDefaults(String[] d) {
    if (defaults == null) defaults = d;
    else {
      String[] m = new String[defaults.length + d.length];
      System.arraycopy(defaults, 0, m, 0, defaults.length);
      System.arraycopy(d, 0, m, defaults.length, d.length);
      defaults = m;
    }
    return this;
  }

  // ---------------- AttributeSet
  public int getAttributeCount() { return own.length / 3; }
  public String getAttributeName(int i) { return own[i * 3 + 1]; }
  public String getAttributeValue(int i) { return own[i * 3 + 2]; }
  public String getAttributeValue(String namespace, String name) { return get(ns(namespace), name); }
  public String getPositionDescription() { return node == null ? "<attrs>" : node.toString(); }
  public int getAttributeNameResource(int i) { return 0; }
  public int getAttributeListValue(String namespace, String attribute, String[] options, int def) {
    String v = getAttributeValue(namespace, attribute);
    if (v != null && options != null) for (int i = 0; i < options.length; i++) if (options[i].equals(v)) return i;
    return def;
  }
  public boolean getAttributeBooleanValue(String namespace, String attribute, boolean def) { return Values.parseBool(getAttributeValue(namespace, attribute), def); }
  public int getAttributeResourceValue(String namespace, String attribute, int def) {
    String v = getAttributeValue(namespace, attribute);
    return Values.isRef(v) ? Values.refId(v) : def;
  }
  public int getAttributeIntValue(String namespace, String attribute, int def) { return Values.parseInt(getAttributeValue(namespace, attribute), def); }
  public int getAttributeUnsignedIntValue(String namespace, String attribute, int def) { return Values.parseInt(getAttributeValue(namespace, attribute), def); }
  public float getAttributeFloatValue(String namespace, String attribute, float def) { return Values.parseFloat(getAttributeValue(namespace, attribute), def); }
  public int getAttributeListValue(int i, String[] options, int def) {
    String v = getAttributeValue(i);
    if (options != null) for (int k = 0; k < options.length; k++) if (options[k].equals(v)) return k;
    return def;
  }
  public boolean getAttributeBooleanValue(int i, boolean def) { return Values.parseBool(getAttributeValue(i), def); }
  public int getAttributeResourceValue(int i, int def) { String v = getAttributeValue(i); return Values.isRef(v) ? Values.refId(v) : def; }
  public int getAttributeIntValue(int i, int def) { return Values.parseInt(getAttributeValue(i), def); }
  public int getAttributeUnsignedIntValue(int i, int def) { return Values.parseInt(getAttributeValue(i), def); }
  public float getAttributeFloatValue(int i, float def) { return Values.parseFloat(getAttributeValue(i), def); }
  public String getIdAttribute() { return get("a", "id"); }
  public String getClassAttribute() { return get("", "class"); }
  public int getIdAttributeResourceValue(int def) { String v = get("a", "id"); return v == null ? def : Values.refId(v); }
  public int getStyleAttribute() { return styleId; }
}
