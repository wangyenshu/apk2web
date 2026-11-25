package android.content.res;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import a2w.Attrs;
import a2w.Res;
import a2w.Values;

public class TypedArray {
  final Resources res;
  final String[] values;
  final int[] attrIds;

  Resources.Theme theme;
  TypedArray(Resources r, String[] values, int[] attrIds) { res = r; this.values = values; this.attrIds = attrIds; }

  /** an attribute's name (without "attr/"), framework or app */
  public static String attrNameOf(int id) { return attrName(id); }
  static String attrName(int id) {
    if (Values.isFramework(id)) {
      String n = Values.frameworkName(id);
      return n == null ? null : n.substring(n.indexOf('/') + 1);
    }
    return Res.attrName(id);
  }

  /**
   * Theme.obtainStyledAttributes: each attribute from the set (its own value, its style=), else the default style
   * (defStyleAttr in the theme, else defStyleRes), else the theme; "?attr" references resolved against the theme
   */
  public static TypedArray obtain(Resources r, Resources.Theme t, AttributeSet set, int[] attrs, int defStyleAttr, int defStyleRes) {
    if (t == null) return obtain(r, set, attrs);
    String[] defItems = null;
    if (defStyleAttr != 0) {
      String n = attrName(defStyleAttr);
      String sv = n == null ? null : t.get(Values.isFramework(defStyleAttr) ? "a" : "x", n);
      if (sv != null && Values.isRef(sv)) defItems = a2w.Styles.items(Values.refId(sv));
    }
    if (defItems == null && defStyleRes != 0) defItems = a2w.Styles.items(defStyleRes);
    if (set instanceof Attrs && ((Attrs) set).theme == null) ((Attrs) set).theme = t;
    String[] v = new String[attrs.length];
    for (int i = 0; i < attrs.length; i++) {
      String n = attrName(attrs[i]);
      if (n == null) continue;
      String ns = Values.isFramework(attrs[i]) ? "a" : "x";
      String x = null;
      if (set instanceof Attrs) x = ((Attrs) set).get(ns, n);
      else if (set != null) x = set.getAttributeValue(ns.equals("a") ? "http://schemas.android.com/apk/res/android" : "http://schemas.android.com/apk/res-auto", n);
      if (x == null && defItems != null) x = a2w.Styles.find(defItems, ns, n);
      if (x == null) x = a2w.Styles.find(t.items, ns, n);
      if (x != null && x.startsWith("?")) { x = t.resolve(x); if (x != null && x.startsWith("?")) x = null; }
      v[i] = x;
    }
    TypedArray a = new TypedArray(r, v, attrs);
    a.theme = t;
    return a;
  }

  public static TypedArray obtain(Resources r, AttributeSet set, int[] attrs) {
    String[] v = new String[attrs.length];
    if (set != null) {
      for (int i = 0; i < attrs.length; i++) {
        String n = attrName(attrs[i]);
        if (n == null) continue;
        String ns = Values.isFramework(attrs[i]) ? "a" : "x";
        if (set instanceof Attrs) v[i] = ((Attrs) set).get(ns, n);
        else v[i] = set.getAttributeValue(ns.equals("a") ? "http://schemas.android.com/apk/res/android" : "http://schemas.android.com/apk/res-auto", n);
      }
    }
    return new TypedArray(r, v, attrs);
  }

  public int length() { return values.length; }
  public int getIndexCount() { int n = 0; for (String v : values) if (v != null) n++; return n; }
  public int getIndex(int at) { int n = 0; for (int i = 0; i < values.length; i++) if (values[i] != null && n++ == at) return i; return -1; }
  public Resources getResources() { return res; }
  public boolean hasValue(int i) { return i >= 0 && i < values.length && values[i] != null; }
  public void recycle() {}
  public String getPositionDescription() { return "<typed array>"; }
  public int getChangingConfigurations() { return 0; }
  String raw(int i) { return i >= 0 && i < values.length ? values[i] : null; }

  public CharSequence getText(int i) { return getString(i); }
  public String getString(int i) {
    String v = raw(i);
    if (v == null) return null;
    if (Values.isRef(v)) {
      int id = Values.refId(v);
      String n = Res.name(id);
      if (n != null && n.startsWith("string/")) return res.getString(id);
      if (n != null && (n.startsWith("drawable/") || n.startsWith("mipmap/"))) { TypedValue tv = new TypedValue(); res.getValue(id, tv, true); return tv.string == null ? null : tv.string.toString(); }
      return v;
    }
    return v;
  }
  public String getNonResourceString(int i) { String v = raw(i); return v == null || Values.isRef(v) ? null : v; }
  public CharSequence[] getTextArray(int i) { String v = raw(i); return v == null ? null : res.getTextArray(Values.refId(v)); }
  public boolean getBoolean(int i, boolean def) {
    String v = raw(i);
    if (v == null) return def;
    if (Values.isRef(v)) return res.getBoolean(Values.refId(v));
    if (v.equals("true")) return true;
    if (v.equals("false")) return false;
    return Values.parseInt(v, def ? 1 : 0) != 0;
  }
  public int getInt(int i, int def) {
    String v = raw(i);
    if (v == null) return def;
    if (Values.isRef(v)) return res.getInteger(Values.refId(v));
    if (attrIds != null && !Values.isFramework(attrIds[i])) {
      int e = Res.attrEnum(attrIds[i], v);
      if (e != Integer.MIN_VALUE) return e;
    }
    if (v.equals("true")) return 1;
    if (v.equals("false")) return 0;
    if (attrIds != null && Values.isFramework(attrIds[i]) && !v.isEmpty() && Character.isLetter(v.charAt(0))) {
      int e = frameworkEnum(Values.frameworkName(attrIds[i]), v);   // a library reading android:layout_gravity itself
      if (e != Integer.MIN_VALUE) return e;
    }
    return Values.parseInt(v, def);
  }
  /** the values of the platform's enum and flag attributes that libraries read with getInt */
  static int frameworkEnum(String attr, String v) {
    if (attr == null) return Integer.MIN_VALUE;
    String a = attr.startsWith("attr/") ? attr.substring(5) : attr;
    switch (a) {
      case "gravity": case "layout_gravity": case "foregroundGravity": return Values.gravity(v);
      case "orientation": return v.equals("vertical") ? 1 : v.equals("horizontal") ? 0 : Integer.MIN_VALUE;
      case "visibility": return v.equals("visible") ? 0 : v.equals("invisible") ? 1 : v.equals("gone") ? 2 : Integer.MIN_VALUE;
      case "textStyle": { int f = 0; for (String p : v.split("\\|")) f |= p.trim().equals("bold") ? 1 : p.trim().equals("italic") ? 2 : 0; return f; }
      case "ellipsize": return v.equals("start") ? 1 : v.equals("middle") ? 2 : v.equals("end") ? 3 : v.equals("marquee") ? 4 : v.equals("none") ? 0 : Integer.MIN_VALUE;
      case "layout_width": case "layout_height": return v.equals("match_parent") || v.equals("fill_parent") ? -1 : v.equals("wrap_content") ? -2 : Integer.MIN_VALUE;
      default: return Integer.MIN_VALUE;
    }
  }
  public int getInteger(int i, int def) { return getInt(i, def); }
  public float getFloat(int i, float def) {
    String v = raw(i);
    if (v == null) return def;
    if (Values.isDimension(v) && !Character.isDigit(v.charAt(v.length() - 1)) && v.charAt(v.length() - 1) != '.') return Values.dimension(v);
    return Values.parseFloat(v, def);
  }
  public int getColor(int i, int def) {
    String v = raw(i);
    if (v == null) return def;
    if (Values.isRef(v)) return res.getColor(Values.refId(v));
    try { return Values.parseColor(v); } catch (RuntimeException e) { return def; }
  }
  public ColorStateList getColorStateList(int i) {
    String v = raw(i);
    if (v == null) return null;
    if (Values.isRef(v)) return res.getColorStateList(Values.refId(v));
    return ColorStateList.valueOf(Values.parseColor(v));
  }
  public float getDimension(int i, float def) {
    String v = raw(i);
    if (v == null) return def;
    if (Values.isRef(v)) return res.getDimension(Values.refId(v));
    return Values.dimension(v);
  }
  public int getDimensionPixelOffset(int i, int def) { String v = raw(i); return v == null ? def : Values.dimensionPixelOffset(getDimension(i, 0)); }
  public int getDimensionPixelSize(int i, int def) { String v = raw(i); return v == null ? def : Values.dimensionPixelSize(getDimension(i, 0)); }
  public int getLayoutDimension(int i, String name) { return Values.layoutSize(raw(i)); }
  public int getLayoutDimension(int i, int def) { String v = raw(i); return v == null ? def : Values.layoutSize(v); }
  public float getFraction(int i, int base, int pbase, float def) {
    String v = raw(i);
    if (v == null) return def;
    if (v.endsWith("%p")) return Values.parseFloat(v.substring(0, v.length() - 2), 0) / 100f * pbase;
    if (v.endsWith("%")) return Values.parseFloat(v.substring(0, v.length() - 1), 0) / 100f * base;
    return Values.parseFloat(v, def);
  }
  public int getResourceId(int i, int def) {
    String v = raw(i);
    if (v == null || !Values.isRef(v)) return def;
    int id = Values.refId(v);
    return id == 0 ? def : id;
  }
  public Drawable getDrawable(int i) {
    String v = raw(i);
    if (v == null) return null;
    if (Values.isRef(v)) { int id = Values.refId(v); return id == 0 ? null : res.getDrawable(id, theme); }
    if (v.startsWith("#")) return new ColorDrawable(Values.parseColor(v));
    return null;
  }
  public boolean getValue(int i, TypedValue out) {
    String v = raw(i);
    if (v == null) return false;
    return toTypedValue(res, v, attrIds == null ? 0 : attrIds[i], out, true);
  }
  public int getType(int i) { TypedValue t = new TypedValue(); return getValue(i, t) ? t.type : TypedValue.TYPE_NULL; }
  public boolean hasValueOrEmpty(int i) { return hasValue(i); }
  public int getSourceResourceId(int i, int def) { return def; }
  public Resources.Theme getTheme() { return theme; }
  public android.graphics.Typeface getFont(int i) {
    String v = raw(i);
    if (v == null) return null;
    if (Values.isRef(v)) { try { return res.getFont(Values.refId(v)); } catch (RuntimeException e) { return null; } }
    return android.graphics.Typeface.create(v, 0);
  }
  public int getComplexColor(int i, int def) { return getColor(i, def); }
  public void close() { recycle(); }

  /** a resource value string ("@type/x", "#color", "16dp", "true", "1.5", text) as a TypedValue */
  public static boolean toTypedValue(Resources res, String v, int attr, TypedValue out, boolean resolveRefs) {
    out.string = null; out.resourceId = 0; out.data = 0; out.fdata = 0; out.assetCookie = 0; out.density = 0;
    if (v == null) { out.type = TypedValue.TYPE_NULL; return false; }
    if (v.equals("@null")) { out.type = TypedValue.TYPE_NULL; out.data = TypedValue.DATA_NULL_UNDEFINED; return true; }
    if (v.equals("@empty")) { out.type = TypedValue.TYPE_NULL; out.data = TypedValue.DATA_NULL_EMPTY; return true; }
    if (Values.isRef(v)) {
      int id = Values.refId(v);
      if (id == 0) { out.type = TypedValue.TYPE_NULL; return false; }
      if (!resolveRefs) { out.type = TypedValue.TYPE_REFERENCE; out.data = id; out.resourceId = id; return true; }
      try { res.getValue(id, out, true); } catch (Resources.NotFoundException e) { out.type = TypedValue.TYPE_REFERENCE; out.data = id; }
      out.resourceId = id;
      return true;
    }
    if (v.startsWith("#")) {
      try { out.data = Values.parseColor(v); } catch (RuntimeException e) { out.type = TypedValue.TYPE_STRING; out.string = v; return true; }
      int n = v.length() - 1;
      out.type = n == 8 ? TypedValue.TYPE_INT_COLOR_ARGB8 : n == 6 ? TypedValue.TYPE_INT_COLOR_RGB8 : n == 4 ? TypedValue.TYPE_INT_COLOR_ARGB4 : TypedValue.TYPE_INT_COLOR_RGB4;
      return true;
    }
    if (v.equals("true") || v.equals("false")) { out.type = TypedValue.TYPE_INT_BOOLEAN; out.data = v.equals("true") ? -1 : 0; return true; }
    int unitAt = v.length();
    while (unitAt > 0 && Character.isLetter(v.charAt(unitAt - 1))) unitAt--;
    String num = v.substring(0, unitAt), unit = v.substring(unitAt);
    if (!num.isEmpty() && (Character.isDigit(num.charAt(num.length() - 1)) || num.endsWith("."))) {
      try {
        if (unit.isEmpty()) {
          if (num.indexOf('.') < 0 && num.indexOf('e') < 0) {
            boolean hex = num.startsWith("0x") || num.startsWith("0X");
            out.type = hex ? TypedValue.TYPE_INT_HEX : TypedValue.TYPE_INT_DEC;
            out.data = hex ? (int) Long.parseLong(num.substring(2), 16) : Integer.parseInt(num);
            out.fdata = out.data;
          } else out.setFloat(Float.parseFloat(num));
          return true;
        }
        int u = -1;
        switch (unit) {
          case "px": u = TypedValue.COMPLEX_UNIT_PX; break;
          case "dp": case "dip": u = TypedValue.COMPLEX_UNIT_DIP; break;
          case "sp": u = TypedValue.COMPLEX_UNIT_SP; break;
          case "pt": u = TypedValue.COMPLEX_UNIT_PT; break;
          case "in": u = TypedValue.COMPLEX_UNIT_IN; break;
          case "mm": u = TypedValue.COMPLEX_UNIT_MM; break;
        }
        if (u >= 0) { out.setDimension(Float.parseFloat(num), u); return true; }
      } catch (NumberFormatException e) { /* text */ }
    } else if (v.endsWith("%") || v.endsWith("%p")) {
      try {
        boolean p = v.endsWith("%p");
        float f = Float.parseFloat(v.substring(0, v.length() - (p ? 2 : 1))) / 100f;
        out.type = TypedValue.TYPE_FRACTION;
        out.data = TypedValue.createComplex(f, p ? TypedValue.COMPLEX_UNIT_FRACTION_PARENT : TypedValue.COMPLEX_UNIT_FRACTION);
        return true;
      } catch (NumberFormatException e) { /* text */ }
    }
    if (attr != 0 && !Values.isFramework(attr)) {
      int e = Res.attrEnum(attr, v);
      if (e != Integer.MIN_VALUE) { out.type = TypedValue.TYPE_INT_DEC; out.data = e; return true; }
    }
    out.type = TypedValue.TYPE_STRING; out.string = v;
    return true;
  }
  public TypedValue peekValue(int i) { TypedValue t = new TypedValue(); return getValue(i, t) ? t : null; }
  public String toString() { return java.util.Arrays.toString(values); }
}
