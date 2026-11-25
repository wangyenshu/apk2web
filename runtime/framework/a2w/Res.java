package a2w;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;

/** Accessors for site/res.json (compiled by tools/build_res.py), held by the page as HW.res. */
public final class Res {
  private Res() {}
  @JSBody(params = {"id"}, script = "return HW.resStr(id);")
  public static native String string(int id);
  @JSBody(params = {"id"}, script = "var v = HW.res.colors[id]; return v == null ? null : v;")
  public static native String color(int id);
  @JSBody(params = {"id"}, script = "var v = HW.res.colorlists[id]; return v == null ? null : v;")
  public static native JSObject colorList(int id);
  @JSBody(params = {"id"}, script = "var v = HW.resArr(id); return v == null ? null : v.join('\\u0001');")
  public static native String arrayJoined(int id);
  /** the plural form for quantity class q ("zero", "one", "two", "few", "many", "other"), falling back to other */
  @JSBody(params = {"id", "q"}, script = "var v = HW.resPlural(id); return v == null ? null : (v[q] != null ? v[q] : v.other != null ? v.other : null);")
  public static native String plural(int id, String q);
  @JSBody(params = {"id"}, script = "var v = HW.res.styles[id]; return v == null ? null : v;")
  public static native JSObject style(int id);
  @JSBody(params = {"s"}, script = "return s.parent;")
  public static native String styleParent(JSObject s);
  @JSBody(params = {"s"}, script = "return s.name;")
  public static native String styleName(JSObject s);
  @JSBody(params = {"s"}, script = "return s.items.join('\\u0001');")
  public static native String styleItems(JSObject s);
  @JSBody(params = {"id"}, script = "var v = HW.res.drawables[id]; return v == null ? null : v;")
  public static native JSObject drawable(int id);
  @JSBody(params = {"d"}, script = "return d.file || null;")
  public static native String drawableFile(JSObject d);
  @JSBody(params = {"d"}, script = "return d.density|0;")
  public static native int drawableDensity(JSObject d);
  @JSBody(params = {"d"}, script = "return d.w|0;")
  public static native int drawableW(JSObject d);
  @JSBody(params = {"d"}, script = "return d.h|0;")
  public static native int drawableH(JSObject d);
  @JSBody(params = {"d"}, script = "return d.xml || null;")
  public static native JSObject drawableXml(JSObject d);
  @JSBody(params = {"id"}, script = "var v = HW.res.layouts[id]; return v == null ? null : v;")
  public static native JSObject layout(int id);
  @JSBody(params = {"id"}, script = "var v = HW.res.anims[id]; return v == null ? null : v;")
  public static native JSObject anim(int id);
  @JSBody(params = {"id"}, script = "var v = HW.res.xmls[id]; return v == null ? null : v;")
  public static native JSObject xml(int id);
  @JSBody(params = {"id"}, script = "var v = HW.res.raws[id]; return v == null ? null : v;")
  public static native String raw(int id);
  @JSBody(params = {"id"}, script = "var v = HW.res.fonts && HW.res.fonts[id]; return v == null ? null : v;")
  public static native String font(int id);
  @JSBody(params = {"id"}, script = "var v = HW.res.names[id]; return v == null ? null : v[0] + '/' + v[1];")
  public static native String name(int id);
  @JSBody(params = {"t", "n"}, script = "return HW.resId(t, n);")
  public static native int id(String type, String name);
  @JSBody(params = {"id"}, script = "var v = HW.res.attrs[id]; return v == null ? null : v.name;")
  public static native String attrName(int id);
  @JSBody(params = {"id"}, script = "var v = HW.res.attrs[id]; return v == null ? null : v.format;")
  public static native String attrFormat(int id);
  @JSBody(params = {"id", "n"}, script = "var v = HW.res.attrs[id]; return v && v.values && (n in v.values) ? v.values[n] : -2147483648;")
  public static native int attrEnum(int id, String value);
  @JSBody(params = {"id"}, script = "var v = HW.res.integers[id]; return v == null ? 0 : v;")
  public static native int integer(int id);
  @JSBody(params = {"id"}, script = "var v = HW.res.bools[id]; return !!v;")
  public static native boolean bool(int id);
  @JSBody(params = {"id"}, script = "var v = HW.res.dimens[id]; return v == null ? null : v;")
  public static native String dimen(int id);
  @JSBody(params = {"k"}, script = "var v = HW.res.meta[k]; return v == null ? null : v;")
  public static native String meta(String k);
  /** meta-data of the application (kind "application") or of a manifest component ("activities", "services",
   *  "receivers", "providers"), joined as name, value pairs; null when there is no such component */
  @JSBody(params = {"kind", "cls"}, script = "var m; if (kind == 'application') m = HW.res.meta; else {"
      + " var l = kind == 'activities' ? HW.res.activities : (HW.res.components || {})[kind] || [];"
      + " var c = l.find(function(x){return x.name == cls}); if (!c) return null; m = c.meta || {}; }"
      + " var out = []; for (var k in m) { out.push(k, m[k] == null ? '' : String(m[k])); } return out.join('\\u0001');")
  public static native String metaJoined(String kind, String cls);
  @JSBody(script = "return HW.res.package;")
  public static native String pkg();
  @JSBody(script = "return HW.res.components && HW.res.components.application || null;")
  public static native String application();
  @JSBody(script = "return HW.res.device && HW.res.device.sdk || 19;")
  public static native int deviceSdk();
  @JSBody(script = "return HW.res.targetSdk || 0;")
  public static native int targetSdk();
  @JSBody(script = "return HW.res.minSdk || 1;")
  public static native int minSdk();
  @JSBody(script = "return HW.res.appTheme;")
  public static native String appTheme();
  /** the manifest's android:label and android:icon of the application, as written (references or text) */
  @JSBody(script = "return HW.res.appLabelRef || '';")
  public static native String appLabelRef();
  @JSBody(script = "return HW.res.appIconRef || '';")
  public static native String appIconRef();
  @JSBody(params = {"n"}, script = "var a = HW.res.activities.find(function(x){return x.name == n}); return a ? a.theme : null;")
  public static native String activityTheme(String name);
  @JSBody(params = {"n"}, script = "var a = HW.res.activities.find(function(x){return x.name == n}); if (!a) return null;"
      + " return a.parent || (a.meta && a.meta['android.support.PARENT_ACTIVITY']) || null;")
  private static native String activityParentRaw(String name);
  /** the parent activity of an activity (parentActivityName, or the support library's PARENT_ACTIVITY meta-data) */
  public static String activityParent(String name) {
    String p = activityParentRaw(name);
    if (p == null || p.isEmpty()) return null;
    if (p.startsWith(".")) return pkg() + p;
    return p.indexOf('.') < 0 ? pkg() + "." + p : p;
  }
  @JSBody(script = "var a = HW.res.activities.find(function(x){return x.launcher}); return a ? a.name : null;")
  public static native String launcher();
  @JSBody(params = {"kind", "action", "cats", "scheme", "host", "type"}, script = "return HW.resolveIntent(kind, action, cats, scheme, host, type);")
  public static native String resolveIntent(String kind, String action, String cats, String scheme, String host, String type);
  @JSBody(params = {"kind"}, script = "var c = HW.res.components && HW.res.components[kind] || []; var o = []; for (var i = 0; i < c.length; i++) { if (c[i].enabled !== false) o.push(c[i].name, c[i].authorities || ''); } return o.join('\\u0001');")
  public static native String componentsJoined(String kind);
  @JSBody(params = {"kind", "name"}, script = "return HW.componentClass(kind, name);")
  public static native String componentClass(String kind, String name);

  // ---- compiled XML nodes: [tag, [ns, name, value, ...], [children]]
  @JSBody(params = {"n"}, script = "return n[0];")
  public static native String nTag(JSObject n);
  @JSBody(params = {"n"}, script = "return n[1].join('\\u0001');")
  public static native String nAttrs(JSObject n);
  @JSBody(params = {"n"}, script = "return n[2].length;")
  public static native int nCount(JSObject n);
  @JSBody(params = {"n", "i"}, script = "return n[2][i];")
  public static native JSObject nChild(JSObject n, int i);
  @JSBody(params = {"n"}, script = "return n.length > 3 && n[3] ? n[3] : null;")
  public static native String nText(JSObject n);
  @JSBody(params = {"n"}, script = "return n.length > 4 && n[4] ? n[4] : null;")
  public static native String nTail(JSObject n);

  public static String[] split(String s) {
    if (s == null) return null;
    if (s.isEmpty()) return new String[0];
    java.util.ArrayList<String> out = new java.util.ArrayList<>();
    int st = 0;
    for (int i = 0; i < s.length(); i++) if (s.charAt(i) == '\u0001') { out.add(s.substring(st, i)); st = i + 1; }
    out.add(s.substring(st));
    return out.toArray(new String[0]);
  }
}
