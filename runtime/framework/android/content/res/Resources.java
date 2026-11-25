package android.content.res;

import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import a2w.Drawables;
import a2w.Res;
import a2w.Values;
import a2w.XmlNode;
import java.io.InputStream;
import java.util.HashMap;
import org.teavm.jso.JSObject;

public class Resources {
  public static class NotFoundException extends RuntimeException {
    public NotFoundException() {}
    public NotFoundException(String m) { super(m); }
  }

  private static Resources system;
  final AssetManager assets;
  final DisplayMetrics metrics = Values.METRICS;
  final Configuration config = Configuration.device();
  private final HashMap<Integer, Drawable.ConstantState> drawableCache = new HashMap<>();
  private final HashMap<Integer, ColorStateList> colorCache = new HashMap<>();
  /** drawables made against a theme (their ?attr values are the theme's) */
  private final java.util.WeakHashMap<Theme, HashMap<Integer, Drawable.ConstantState>> themedCache = new java.util.WeakHashMap<>();

  public Resources(AssetManager a, DisplayMetrics m, Configuration c) { assets = a; }
  public static Resources getSystem() { if (system == null) system = new Resources(AssetManager.INSTANCE, null, null); return system; }
  public final AssetManager getAssets() { return assets; }
  public DisplayMetrics getDisplayMetrics() { return metrics; }
  public Configuration getConfiguration() { return config; }
  public void updateConfiguration(Configuration c, DisplayMetrics m) { if (c != null) config.setTo(c); }
  public final Theme newTheme() { return new Theme(); }
  public void flushLayoutCache() {}
  public final void finishPreloading() {}

  // ---------------------------------------------------------------- names
  public int getIdentifier(String name, String defType, String defPackage) {
    if (name == null) return 0;
    if (!name.isEmpty() && Character.isDigit(name.charAt(0))) { try { return Integer.parseInt(name); } catch (NumberFormatException e) { /* a name */ } }
    String pkg = defPackage, type = defType, n = name;
    int colon = n.indexOf(':');
    if (colon >= 0) { pkg = n.substring(0, colon); n = n.substring(colon + 1); }
    int slash = n.indexOf('/');
    if (slash >= 0) { type = n.substring(0, slash); n = n.substring(slash + 1); }
    if (n.startsWith("@")) return Values.refId(n);
    if (type == null) return 0;
    if ("android".equals(pkg)) return Values.frameworkId(type + "/" + n);
    try { return Integer.parseInt(n); } catch (NumberFormatException e) {}
    return Res.id(type, n);
  }
  String nameOf(int id) {
    if (Values.isFramework(id)) { String n = Values.frameworkName(id); if (n == null) throw new NotFoundException("Unable to find resource ID #0x" + Integer.toHexString(id)); return n; }
    String n = Res.name(id);
    if (n == null) throw new NotFoundException("Unable to find resource ID #0x" + Integer.toHexString(id));
    return n;
  }
  public String getResourceName(int id) { return (Values.isFramework(id) ? "android:" : Res.pkg() + ":") + nameOf(id); }
  public String getResourcePackageName(int id) { nameOf(id); return Values.isFramework(id) ? "android" : Res.pkg(); }
  public String getResourceTypeName(int id) { String n = nameOf(id); return n.substring(0, n.indexOf('/')); }
  public String getResourceEntryName(int id) { String n = nameOf(id); return n.substring(n.indexOf('/') + 1); }

  // ---------------------------------------------------------------- values
  public CharSequence getText(int id) throws NotFoundException { return getString(id); }
  public CharSequence getText(int id, CharSequence def) { String s = id == 0 ? null : Res.string(id); return s == null ? def : s; }
  public String getString(int id) throws NotFoundException {
    String s = Res.string(id);
    if (s == null) {
      if (Values.isFramework(id)) return fwString(id);
      throw new NotFoundException("String resource ID #0x" + Integer.toHexString(id));
    }
    return s;
  }
  private static String fwString(int id) {
    switch (id) {
      case android.R.string.ok: return "OK";
      case android.R.string.cancel: return "Cancel";
      case android.R.string.yes: return "Yes";
      case android.R.string.no: return "No";
    }
    return "";
  }
  public String getString(int id, Object... args) throws NotFoundException { return String.format(getString(id), args); }
  public String getQuantityString(int id, int q, Object... args) { return String.format(getQuantityString(id, q), args); }
  public String getQuantityString(int id, int q) {
    String s = a2w.Res.plural(id, quantityClass(q));
    if (s == null) throw new NotFoundException("Plural resource ID #0x" + Integer.toHexString(id));
    return s;
  }
  public CharSequence getQuantityText(int id, int q) { return getQuantityString(id, q); }
  /** CLDR plural class of q for the current language (the common rules; other languages fall back to "other") */
  static String quantityClass(int q) {
    String lang = java.util.Locale.getDefault().getLanguage();
    switch (lang) {
      case "ja": case "ko": case "zh": case "th": case "vi": case "id": case "ms": case "tr": return "other";
      case "fr": case "pt": return q == 0 || q == 1 ? "one" : "other";
      case "ru": case "uk": case "be": case "sr": case "hr": case "bs": {
        int m10 = q % 10, m100 = q % 100;
        if (m10 == 1 && m100 != 11) return "one";
        if (m10 >= 2 && m10 <= 4 && (m100 < 12 || m100 > 14)) return "few";
        return "many";
      }
      case "pl": {
        int m10 = q % 10, m100 = q % 100;
        if (q == 1) return "one";
        if (m10 >= 2 && m10 <= 4 && (m100 < 12 || m100 > 14)) return "few";
        return "many";
      }
      case "cs": case "sk": return q == 1 ? "one" : q >= 2 && q <= 4 ? "few" : "other";
      case "ar": {
        int m100 = q % 100;
        if (q == 0) return "zero";
        if (q == 1) return "one";
        if (q == 2) return "two";
        if (m100 >= 3 && m100 <= 10) return "few";
        if (m100 >= 11) return "many";
        return "other";
      }
      default: return q == 1 ? "one" : "other";
    }
  }
  public String[] getStringArray(int id) throws NotFoundException {
    String[] a = Res.split(Res.arrayJoined(id));
    if (a == null) throw new NotFoundException("String array resource ID #0x" + Integer.toHexString(id));
    return a;
  }
  public CharSequence[] getTextArray(int id) throws NotFoundException { return getStringArray(id); }
  public int[] getIntArray(int id) throws NotFoundException {
    String[] a = getStringArray(id);
    int[] r = new int[a.length];
    for (int i = 0; i < a.length; i++) r[i] = Values.parseInt(a[i], 0);
    return r;
  }
  public TypedArray obtainTypedArray(int id) throws NotFoundException { return new TypedArray(this, getStringArray(id), null); }
  public int getInteger(int id) throws NotFoundException { return Res.integer(id); }
  public boolean getBoolean(int id) throws NotFoundException { return Res.bool(id); }
  public float getDimension(int id) throws NotFoundException {
    String d = Res.dimen(id);
    if (d == null) throw new NotFoundException("Resource ID #0x" + Integer.toHexString(id) + " type #0x5 is not valid");
    return Values.dimension(d);
  }
  public int getDimensionPixelSize(int id) throws NotFoundException { return Values.dimensionPixelSize(getDimension(id)); }
  public int getDimensionPixelOffset(int id) throws NotFoundException { return Values.dimensionPixelOffset(getDimension(id)); }
  public float getFraction(int id, int base, int pbase) { return 0; }

  public int getColor(int id) throws NotFoundException {
    String c = Res.color(id);
    if (c == null && Values.isFramework(id) && Res.colorList(id) == null) return fwColor(id);
    if (c != null) return colorValue(c);
    ColorStateList l = getColorStateList(id);
    return l.getDefaultColor();
  }
  int colorValue(String c) {
    if (Values.isRef(c)) return getColor(Values.refId(c));
    return Values.parseColor(c);
  }
  static int fwColor(int id) {
    switch (id) {
      case android.R.color.white: return 0xffffffff;
      case android.R.color.black: return 0xff000000;
      case android.R.color.transparent: return 0;
      case android.R.color.background_dark: return 0xff000000;
      case android.R.color.background_light: return 0xffffffff;
      case android.R.color.darker_gray: return 0xffaaaaaa;
    }
    return 0xff000000;
  }
  public ColorStateList getColorStateList(int id) throws NotFoundException {
    ColorStateList l = colorCache.get(id);
    if (l != null) return l;
    String c = Res.color(id);
    if (c != null) l = ColorStateList.valueOf(colorValue(c));
    else if (Values.isFramework(id) && Res.colorList(id) == null) l = ColorStateList.valueOf(fwColor(id));
    else {
      {
        XmlNode n = XmlNode.of(Res.colorList(id));
        if (n == null) throw new NotFoundException("Color resource ID #0x" + Integer.toHexString(id));
        l = Drawables.colorList(this, n);
      }
    }
    colorCache.put(id, l);
    return l;
  }

  public Drawable getDrawable(int id) throws NotFoundException {
    Drawable.ConstantState cs = drawableCache.get(id);
    if (cs != null) return cs.newDrawable(this);
    Drawable d = Drawables.load(this, id);
    if (d == null) throw new NotFoundException("Resource ID #0x" + Integer.toHexString(id));
    cs = d.getConstantState();
    if (cs != null) drawableCache.put(id, cs);
    return d;
  }
  /** a drawable whose XML may name theme attributes (?attr/colorControlNormal): resolved against t */
  public Drawable getDrawable(int id, Theme t) {
    if (t == null) return getDrawable(id);
    HashMap<Integer, Drawable.ConstantState> cache = themedCache.get(t);
    if (cache == null) { cache = new HashMap<>(); themedCache.put(t, cache); }
    Drawable.ConstantState cs = cache.get(id);
    if (cs != null) return cs.newDrawable(this);
    Theme was = Drawables.theme;
    Drawables.theme = t;
    try {
      Drawable d = Drawables.load(this, id);
      if (d == null) throw new NotFoundException("Resource ID #0x" + Integer.toHexString(id));
      cs = d.getConstantState();
      if (cs != null) cache.put(id, cs);
      return d;
    } finally { Drawables.theme = was; }
  }
  public Drawable getDrawableForDensity(int id, int density) { return getDrawable(id); }
  public Drawable getDrawableForDensity(int id, int density, Theme t) { return getDrawable(id, t); }
  public int getColor(int id, Theme t) throws NotFoundException {
    if (t == null) return getColor(id);
    Theme was = Drawables.theme;
    Drawables.theme = t;
    try { return Res.color(id) != null || Values.isFramework(id) && Res.colorList(id) == null ? getColor(id) : getColorStateList(id, t).getDefaultColor(); }
    finally { Drawables.theme = was; }
  }
  public ColorStateList getColorStateList(int id, Theme t) throws NotFoundException {
    if (t == null || Res.colorList(id) == null) return getColorStateList(id);
    Theme was = Drawables.theme;
    Drawables.theme = t;
    try {
      XmlNode n = XmlNode.of(Res.colorList(id));
      if (n == null) throw new NotFoundException("Color resource ID #0x" + Integer.toHexString(id));
      return Drawables.colorList(this, n);
    } finally { Drawables.theme = was; }
  }
  public float getFloat(int id) { TypedValue v = new TypedValue(); getValue(id, v, true); return v.type == TypedValue.TYPE_FLOAT ? v.getFloat() : v.data; }

  public android.graphics.Movie getMovie(int id) { return null; }
  /** a font resource: a font file, or a font-family XML (its first font) */
  public android.graphics.Typeface getFont(int id) throws NotFoundException {
    String f = Res.font(id);
    if (f == null) {
      XmlNode n = XmlNode.of(Res.xml(id));
      if (n != null && n.tag.equals("font-family"))
        for (XmlNode c : n.children()) {
          String ref = c.a("font");
          if (ref == null) ref = c.get("x", "font");
          if (ref != null && Values.isRef(ref)) return getFont(Values.refId(ref));
        }
      throw new NotFoundException("Font resource ID #0x" + Integer.toHexString(id));
    }
    return android.graphics.Typeface.fromBytes("res:" + f, assets.readEntry(f));
  }

  /** TypedValue for a resource: for drawables the file inside the APK, like the real asset path. */
  public void getValue(int id, TypedValue out, boolean resolveRefs) throws NotFoundException {
    out.resourceId = id;
    out.assetCookie = 1;
    out.string = null;
    out.density = 0;
    out.data = id;   // (for file values Android gives the string's index: unique per file, which caches key on)
    out.changingConfigurations = 0;
    String type = Values.isFramework(id) ? Values.frameworkName(id) : Res.name(id);   // (framework: "color/white")
    if (type == null && !Values.isFramework(id)) throw new NotFoundException("Resource ID #0x" + Integer.toHexString(id));
    type = type == null || type.indexOf('/') < 0 ? "" : type.substring(0, type.indexOf('/'));
    if (Values.isFramework(id) && (type.equals("drawable") || type.equals("layout"))) type = "";   // (the platform's files are not in the APK)
    switch (type) {
      case "drawable": case "mipmap": {
        org.teavm.jso.JSObject d = Res.drawable(id);
        String f = d == null ? null : Res.drawableFile(d);
        out.type = TypedValue.TYPE_STRING;
        if (f != null) {
          out.string = f;
          int dens = Res.drawableDensity(d);
          out.density = dens == 160 && !f.contains("dpi") ? TypedValue.DENSITY_DEFAULT : dens;
        } else out.string = "res/" + type + "/" + getResourceEntryName(id) + ".xml";
        return;
      }
      case "layout": case "anim": case "xml": case "menu": case "animator": case "interpolator": case "transition": case "navigation":
        out.type = TypedValue.TYPE_STRING; out.string = "res/" + type + "/" + getResourceEntryName(id) + ".xml"; return;
      case "raw":
        out.type = TypedValue.TYPE_STRING; out.string = Res.raw(id); return;
      case "string":
        out.type = TypedValue.TYPE_STRING; out.string = getString(id); return;
      case "color":
        out.type = TypedValue.TYPE_INT_COLOR_ARGB8; out.data = getColor(id); return;
      case "integer":
        out.type = TypedValue.TYPE_INT_DEC; out.data = getInteger(id); return;
      case "bool":
        out.type = TypedValue.TYPE_INT_BOOLEAN; out.data = getBoolean(id) ? -1 : 0; return;
      case "dimen": {
        String d = Res.dimen(id);
        if (d != null && !Values.isRef(d)) { TypedArray.toTypedValue(this, d, 0, out, true); out.resourceId = id; return; }
        out.setDimension(getDimension(id), TypedValue.COMPLEX_UNIT_PX);
        return;
      }
      default:
        out.type = TypedValue.TYPE_REFERENCE; out.data = id;
    }
  }
  public void getValue(String name, TypedValue out, boolean resolveRefs) { getValue(getIdentifier(name, null, null), out, resolveRefs); }
  public InputStream openRawResource(int id) throws NotFoundException { return openRawResource(id, new TypedValue()); }
  public InputStream openRawResource(int id, TypedValue v) throws NotFoundException {
    getValue(id, v, true);
    if (v.string == null) throw new NotFoundException("Resource ID #0x" + Integer.toHexString(id));
    byte[] d = assets.readEntry(v.string.toString());
    if (d == null) throw new NotFoundException("File " + v.string + " from drawable resource ID #0x" + Integer.toHexString(id));
    return new a2w.jdk.FileInputStream(d, 0, d.length);
  }
  public AssetFileDescriptor openRawResourceFd(int id) throws NotFoundException {
    TypedValue v = new TypedValue();
    getValue(id, v, true);
    try { return assets.openNonAssetFd(v.string.toString()); } catch (java.io.IOException e) { throw new NotFoundException(e.getMessage()); }
  }
  /** (any XML resource, as on Android: a support-library menu inflater reads menus with getLayout) */
  public XmlResourceParser getLayout(int id) throws NotFoundException {
    XmlNode n = XmlNode.of(Res.layout(id));
    return n != null ? new a2w.XmlResParser(n) : getXml(id);
  }
  public XmlResourceParser getXml(int id) throws NotFoundException {
    XmlNode n = XmlNode.of(Res.xml(id));
    if (n == null) n = XmlNode.of(Res.layout(id));
    if (n == null) n = XmlNode.of(Res.anim(id));
    if (n == null) {
      JSObject d = Res.drawable(id);
      if (d != null) n = XmlNode.of(Res.drawableXml(d));
    }
    if (n == null) n = XmlNode.of(Res.colorList(id));
    if (n == null) throw new NotFoundException("Resource ID #0x" + Integer.toHexString(id) + " is not an XML file");
    return new a2w.XmlResParser(n);
  }
  public XmlResourceParser getAnimation(int id) throws NotFoundException {
    XmlNode n = animNode(id);
    if (n == null) throw new NotFoundException("Resource ID #0x" + Integer.toHexString(id) + " is not an animation");
    return new a2w.XmlResParser(n);
  }

  /** web: compiled XML for layouts/anims */
  public XmlNode layoutNode(int id) {
    XmlNode n = XmlNode.of(Res.layout(id));
    if (n == null) throw new NotFoundException("Resource ID #0x" + Integer.toHexString(id) + " is not a layout");
    return n;
  }
  public XmlNode animNode(int id) { return XmlNode.of(Res.anim(id)); }

  public TypedArray obtainAttributes(android.util.AttributeSet set, int[] attrs) { return TypedArray.obtain(this, set, attrs); }

  /**
   * A theme: the items of the styles applied to it (nearest first; applyStyle with force puts the new style's items
   * ahead), the values "?attr/x" references and obtainStyledAttributes fall back to.
   */
  public final class Theme {
    int style;
    String[] items = new String[0];
    private final java.util.ArrayList<Integer> applied = new java.util.ArrayList<>();
    public void applyStyle(int resid, boolean force) {
      if (resid == 0) return;
      String[] add = a2w.Styles.items(resid);
      String[] m = new String[add.length + items.length];
      if (force) { System.arraycopy(add, 0, m, 0, add.length); System.arraycopy(items, 0, m, add.length, items.length); }
      else { System.arraycopy(items, 0, m, 0, items.length); System.arraycopy(add, 0, m, items.length, add.length); }
      items = m;
      style = resid;
      applied.add(resid);
    }
    public void setTo(Theme o) { style = o.style; items = o.items; applied.clear(); applied.addAll(o.applied); }
    public void rebase() {}
    public Resources getResources() { return Resources.this; }
    public int getChangingConfigurations() { return 0; }
    public Drawable getDrawable(int id) throws NotFoundException { return Resources.this.getDrawable(id, this); }
    public int[] getAttributeResolutionStack(int defStyleAttr, int defStyleRes, int explicitStyleRes) {
      int[] r = new int[applied.size()];
      for (int i = 0; i < r.length; i++) r[i] = applied.get(r.length - 1 - i);
      return r;
    }
    public int getExplicitStyle(android.util.AttributeSet set) {
      if (set == null) return 0;
      String s = set.getAttributeValue(null, "style");
      if (s != null && s.startsWith("?")) s = resolve(s);
      return s != null && Values.isRef(s) ? Values.refId(s) : 0;
    }
    public TypedArray obtainStyledAttributes(int[] attrs) { return TypedArray.obtain(Resources.this, this, null, attrs, 0, 0); }
    public TypedArray obtainStyledAttributes(int resid, int[] attrs) { return TypedArray.obtain(Resources.this, this, null, attrs, 0, resid); }
    public TypedArray obtainStyledAttributes(android.util.AttributeSet set, int[] attrs, int defAttr, int defRes) {
      return TypedArray.obtain(Resources.this, this, set, attrs, defAttr, defRes);
    }
    public boolean resolveAttribute(int resid, TypedValue out, boolean resolveRefs) {
      String n = TypedArray.attrNameOf(resid);
      if (n == null) return false;
      String v = get(Values.isFramework(resid) ? "a" : "x", n);
      if (v == null) return false;
      if (v.startsWith("?")) return false;
      return TypedArray.toTypedValue(Resources.this, v, resid, out, resolveRefs);
    }
    /** an attribute's value in this theme, "?attr" references followed */
    public String get(String ns, String name) { return resolve(a2w.Styles.find(items, ns, name)); }
    /** follows "?attr/x" ("?android:attr/x", "?x") references through this theme (a value that is none comes back as is) */
    public String resolve(String v) {
      for (int guard = 0; v != null && v.startsWith("?") && guard < 16; guard++) {
        String r = v.substring(1);
        boolean fw = r.startsWith("android:");
        if (fw) r = r.substring(8);
        int colon = r.indexOf(':');
        if (colon >= 0) r = r.substring(colon + 1);   // ?pkg:attr/x
        if (r.startsWith("attr/")) r = r.substring(5);
        String found = a2w.Styles.find(items, fw ? "a" : "x", r);
        if (found == null && !fw) found = a2w.Styles.find(items, "a", r);   // ?attr/x written for a platform attribute
        if (found == null) { String d = a2w.Styles.themeAttr(v); return d != null ? d : v; }
        v = found;
      }
      return v;
    }
    public void dump(int p, String tag, String prefix) {}
    @Override public String toString() { return "Theme{style=0x" + Integer.toHexString(style) + "}"; }
  }
  /** the <extra> elements under the parser's current element into the bundle (other elements are skipped) */
  public void parseBundleExtras(XmlResourceParser parser, android.os.Bundle outBundle) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
    int depth = parser.getDepth(), type;
    while ((type = parser.next()) != org.xmlpull.v1.XmlPullParser.END_DOCUMENT && (type != org.xmlpull.v1.XmlPullParser.END_TAG || parser.getDepth() > depth)) {
      if (type != org.xmlpull.v1.XmlPullParser.START_TAG) continue;
      if (parser.getName().equals("extra")) parseBundleExtra("extra", parser, outBundle);
      int d = parser.getDepth();
      while ((type = parser.next()) != org.xmlpull.v1.XmlPullParser.END_DOCUMENT && (type != org.xmlpull.v1.XmlPullParser.END_TAG || parser.getDepth() > d)) { }
    }
  }
  /** an <extra android:name="..." android:value="..."/> of a resource into the bundle: a string, boolean, integer or float (by the value's form) */
  public void parseBundleExtra(String tagName, android.util.AttributeSet attrs, android.os.Bundle outBundle) throws org.xmlpull.v1.XmlPullParserException {
    String ns = "http://schemas.android.com/apk/res/android";
    String name = attrs.getAttributeValue(ns, "name");
    if (name == null) throw new org.xmlpull.v1.XmlPullParserException("<" + tagName + "> requires an android:name attribute at " + attrs.getPositionDescription());
    String v = attrs.getAttributeValue(ns, "value");
    if (v == null) throw new org.xmlpull.v1.XmlPullParserException("<" + tagName + "> only supports string, integer, float, color, and boolean at " + attrs.getPositionDescription());
    if (a2w.Values.isRef(v)) {
      int id = a2w.Values.refId(v);
      String typeName = id != 0 ? getResourceTypeName(id) : "";
      switch (typeName) {
        case "bool": outBundle.putBoolean(name, getBoolean(id)); return;
        case "integer": outBundle.putInt(name, getInteger(id)); return;
        case "color": outBundle.putInt(name, getColor(id)); return;
        case "dimen": outBundle.putFloat(name, getDimension(id)); return;
        default: outBundle.putCharSequence(name, getText(id)); return;
      }
    }
    if (v.equals("true") || v.equals("false")) outBundle.putBoolean(name, Boolean.parseBoolean(v));
    else if (v.startsWith("#")) outBundle.putInt(name, a2w.Values.parseColor(v));
    else if (v.matches("-?(0x[0-9a-fA-F]+|\\d+)")) outBundle.putInt(name, Integer.decode(v));
    else if (v.matches("-?\\d*\\.\\d+([eE][-+]?\\d+)?")) outBundle.putFloat(name, Float.parseFloat(v));
    else outBundle.putString(name, v);
  }
}
