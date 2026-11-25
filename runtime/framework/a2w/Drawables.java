package a2w;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.*;
import android.util.TypedValue;
import android.view.Gravity;
import java.util.ArrayList;
import java.util.HashMap;
import org.teavm.jso.JSObject;

/** Inflates drawable resources (bitmaps and XML) and provides the framework drawables the game names. */
public final class Drawables {
  private Drawables() {}
  private static final HashMap<Integer, Bitmap> bitmaps = new HashMap<>();
  /** the theme "?attr/x" values in drawable and color XML resolve against (Resources.getDrawable(id, theme)) */
  public static Resources.Theme theme;
  /** a "?attr/x" value through the drawable theme (else the platform default) */
  static String attr(String v) {
    if (v == null || !v.startsWith("?")) return v;
    if (theme != null) { String r = theme.resolve(v); if (r != null && !r.startsWith("?")) return r; }
    return Styles.themeAttr(v);
  }

  static final java.util.Set<String> DRAWABLE_TAGS = new java.util.HashSet<>(java.util.Arrays.asList("selector", "level-list",
      "animation-list", "layer-list", "bitmap", "nine-patch", "clip", "scale", "inset", "rotate", "color", "shape", "transition",
      "vector", "animated-vector", "ripple", "animated-selector", "adaptive-icon", "gradient", "animated-image"));
  public static Drawable load(Resources r, int id) {
    if (Values.isFramework(id) && Res.drawable(id) == null) return framework(r, Values.frameworkName(id));
    String name = Res.name(id);
    if (name == null) return null;
    if (name.startsWith("color/")) {
      ColorStateList l = r.getColorStateList(id);
      return new ColorDrawable(l.getDefaultColor());
    }
    JSObject d = Res.drawable(id);
    if (d == null) {
      // a drawable XML kept in another resource folder (res/layout/x.xml with a <selector>): Android goes by the
      // file, not by the folder
      JSObject x = name.startsWith("layout/") ? Res.layout(id) : name.startsWith("xml/") ? Res.xml(id) : name.startsWith("anim/") ? Res.anim(id) : null;
      if (x == null) return null;
      XmlNode n = new XmlNode(x);
      return DRAWABLE_TAGS.contains(n.tag) ? fromXml(r, n) : null;
    }
    JSObject xml = Res.drawableXml(d);
    if (xml != null) return fromXml(r, new XmlNode(xml));
    Bitmap b = bitmap(r, id);
    if (b == null) return null;
    byte[] np = b.getNinePatchChunk();
    if (np != null && android.graphics.NinePatch.isNinePatchChunk(np)) return new android.graphics.drawable.NinePatchDrawable(r, b, np, null, name);
    return new BitmapDrawable(r, b);
  }

  /** Decodes like Resources.getDrawable: density-scaled from the resource directory to the screen. */
  public static Bitmap bitmap(Resources r, int id) {
    Bitmap b = bitmaps.get(id);
    if (b != null) return b;
    BitmapFactory.Options o = new BitmapFactory.Options();
    b = BitmapFactory.decodeResource(r, id, o);
    if (b != null) bitmaps.put(id, b);
    return b;
  }

  /** a tileMode attribute value, or the fallback */
  static android.graphics.Shader.TileMode tile(String v, android.graphics.Shader.TileMode fallback) {
    if (v == null) return fallback;
    switch (v) {
      case "repeat": case "1": return android.graphics.Shader.TileMode.REPEAT;
      case "mirror": case "2": return android.graphics.Shader.TileMode.MIRROR;
      case "clamp": case "0": return android.graphics.Shader.TileMode.CLAMP;
      default: return fallback;
    }
  }
  static Drawable ref(Resources r, String v) {
    v = attr(v);
    if (v == null) return null;
    if (v.startsWith("#")) return new ColorDrawable(Values.parseColor(v));
    if (!Values.isRef(v)) return null;
    int id = Values.refId(v);
    return id == 0 ? null : r.getDrawable(id);
  }

  static int[] states(XmlNode n) {
    ArrayList<Integer> s = new ArrayList<>();
    String[] a = n.attrs;
    for (int i = 0; i + 2 < a.length; i += 3) {
      if (!a[i].equals("a") || !a[i + 1].startsWith("state_")) continue;
      int id = stateId(a[i + 1]);
      if (id == 0) continue;
      s.add(a[i + 2].equals("true") ? id : -id);
    }
    int[] r = new int[s.size()];
    for (int i = 0; i < r.length; i++) r[i] = s.get(i);
    return r;
  }
  public static int stateId(String name) {
    switch (name) {
      case "state_pressed": return android.R.attr.state_pressed;
      case "state_focused": return android.R.attr.state_focused;
      case "state_selected": return android.R.attr.state_selected;
      case "state_checked": return android.R.attr.state_checked;
      case "state_checkable": return android.R.attr.state_checkable;
      case "state_enabled": return android.R.attr.state_enabled;
      case "state_window_focused": return android.R.attr.state_window_focused;
      case "state_activated": return android.R.attr.state_activated;
      case "state_active": return android.R.attr.state_active;
      case "state_hovered": return android.R.attr.state_hovered;
    }
    return 0;
  }

  public static ColorStateList colorList(Resources r, XmlNode n) {
    ArrayList<int[]> specs = new ArrayList<>();
    ArrayList<Integer> cols = new ArrayList<>();
    for (XmlNode it : n.children()) {
      String c = attr(it.a("color"));
      if (c == null) continue;
      int col;
      try { col = Values.isRef(c) ? r.getColor(Values.refId(c)) : Values.parseColor(c); } catch (RuntimeException e) { continue; }
      String al = attr(it.a("alpha"));
      if (al == null) al = attr(it.get("x", "alpha"));
      if (al != null) {
        float f = Values.isRef(al) ? r.getFloat(Values.refId(al)) : Values.parseFloat(al, 1);
        col = (Math.round(android.graphics.Color.alpha(col) * Math.max(0, Math.min(1, f))) << 24) | (col & 0xffffff);
      }
      specs.add(states(it));
      cols.add(col);
    }
    int[] cs = new int[cols.size()];
    for (int i = 0; i < cs.length; i++) cs[i] = cols.get(i);
    return new ColorStateList(specs.toArray(new int[0][]), cs);
  }

  static Drawable child(Resources r, XmlNode item) {
    Drawable d = ref(r, item.a("drawable"));
    if (d == null && !item.children().isEmpty()) d = fromXml(r, item.children().get(0));
    return d;
  }
  static float dim(String v, float def) {
    v = attr(v);
    if (v == null || v.startsWith("?")) return def;
    if (Values.isRef(v)) { String d = Res.dimen(Values.refId(v)); return d == null ? def : Values.dimension(d); }
    return Values.dimension(v);
  }
  /** a stateful color state list behind a color attribute, or null */
  static ColorStateList colorList(Resources r, String v) {
    v = attr(v);
    if (v == null || !Values.isRef(v)) return null;
    try {
      ColorStateList l = r.getColorStateList(Values.refId(v));
      return l != null && l.isStateful() ? l : null;
    } catch (RuntimeException e) { return null; }
  }
  static int color(Resources r, String v, int def) {
    v = attr(v);
    if (v == null || v.startsWith("?")) return def;
    try { return Values.isRef(v) ? r.getColor(Values.refId(v)) : Values.parseColor(v); } catch (RuntimeException e) { return def; }
  }
  static float num(Resources r, String v, float def) {
    v = attr(v);
    if (v == null || v.startsWith("?")) return def;
    if (Values.isRef(v)) { try { return r.getFloat(Values.refId(v)); } catch (RuntimeException e) { return def; } }
    return Values.parseFloat(v, def);
  }
  /** a color attribute: a color list (a color, @color/x selector) or, for vector fills, a gradient resource */
  static VectorDrawable.Fill fill(Resources r, String v) {
    v = attr(v);
    if (v == null || v.startsWith("?") || v.equals("@null")) return null;
    if (v.startsWith("#")) { try { return new VectorDrawable.Fill(ColorStateList.valueOf(Values.parseColor(v))); } catch (RuntimeException e) { return null; } }
    if (!Values.isRef(v)) return null;
    int id = Values.refId(v);
    if (id == 0) return null;
    XmlNode x = XmlNode.of(Res.colorList(id));
    if (x == null) { JSObject d = Res.drawable(id); if (d != null) x = XmlNode.of(Res.drawableXml(d)); }
    if (x != null && x.tag.equals("gradient")) return new VectorDrawable.Fill(gradient(r, x));
    try { return new VectorDrawable.Fill(r.getColorStateList(id, theme)); } catch (RuntimeException e) { return null; }
  }
  /** a <gradient> color resource (vector fills) as a shader */
  static android.graphics.Shader gradient(Resources r, XmlNode g) {
    ArrayList<Integer> cols = new ArrayList<>();
    ArrayList<Float> offs = new ArrayList<>();
    for (XmlNode it : g.children()) {
      if (!it.tag.equals("item")) continue;
      cols.add(color(r, it.a("color"), 0));
      offs.add(num(r, it.a("offset"), 0));
    }
    if (cols.isEmpty()) {
      cols.add(color(r, g.a("startColor"), 0));
      if (g.a("centerColor") != null) cols.add(color(r, g.a("centerColor"), 0));
      cols.add(color(r, g.a("endColor"), 0));
    }
    int[] c = new int[cols.size()];
    for (int i = 0; i < c.length; i++) c[i] = cols.get(i);
    float[] pos = null;
    if (offs.size() == c.length) { pos = new float[c.length]; for (int i = 0; i < c.length; i++) pos[i] = offs.get(i); }
    String tm = g.a("tileMode");
    android.graphics.Shader.TileMode mode = "repeat".equals(tm) ? android.graphics.Shader.TileMode.REPEAT
        : "mirror".equals(tm) ? android.graphics.Shader.TileMode.MIRROR : android.graphics.Shader.TileMode.CLAMP;
    String type = g.a("type");
    if ("radial".equals(type))
      return new android.graphics.RadialGradient(num(r, g.a("centerX"), 0), num(r, g.a("centerY"), 0), Math.max(0.001f, num(r, g.a("gradientRadius"), 1)), c, pos, mode);
    if ("sweep".equals(type)) return new android.graphics.SweepGradient(num(r, g.a("centerX"), 0), num(r, g.a("centerY"), 0), c, pos);
    return new android.graphics.LinearGradient(num(r, g.a("startX"), 0), num(r, g.a("startY"), 0), num(r, g.a("endX"), 0), num(r, g.a("endY"), 0), c, pos, mode);
  }

  /** <vector>: viewport, groups, paths, clip paths */
  static Drawable vector(Resources r, XmlNode n) {
    VectorDrawable v = new VectorDrawable();
    v.viewportWidth = num(r, n.a("viewportWidth"), 0);
    v.viewportHeight = num(r, n.a("viewportHeight"), 0);
    v.width = Math.round(dim(n.a("width"), v.viewportWidth));
    v.height = Math.round(dim(n.a("height"), v.viewportHeight));
    if (v.viewportWidth <= 0) v.viewportWidth = v.width;
    if (v.viewportHeight <= 0) v.viewportHeight = v.height;
    v.rootAlpha = num(r, n.a("alpha"), 1);
    v.setAutoMirrored(Values.parseBool(attr(n.a("autoMirrored")), false));
    vectorGroup(r, n, v.root);
    String tint = attr(n.a("tint"));
    if (tint != null && !tint.startsWith("?")) {
      VectorDrawable.Fill f = fill(r, tint);
      if (f != null && f.colors != null) {
        String tm = n.a("tintMode");
        if (tm != null) v.setTintMode(porterDuff(tm));
        v.setTintList(f.colors);
      }
    }
    return v;
  }
  public static android.graphics.PorterDuff.Mode porterDuffMode(String m) { return porterDuff(m); }
  static android.graphics.PorterDuff.Mode porterDuff(String m) {
    switch (m) {
      case "src_over": return android.graphics.PorterDuff.Mode.SRC_OVER;
      case "src_atop": return android.graphics.PorterDuff.Mode.SRC_ATOP;
      case "multiply": return android.graphics.PorterDuff.Mode.MULTIPLY;
      case "screen": return android.graphics.PorterDuff.Mode.SCREEN;
      case "add": return android.graphics.PorterDuff.Mode.ADD;
      default: return android.graphics.PorterDuff.Mode.SRC_IN;
    }
  }
  private static void vectorGroup(Resources r, XmlNode n, VectorDrawable.Group g) {
    for (XmlNode c : n.children()) {
      switch (c.tag) {
        case "group": {
          VectorDrawable.Group k = new VectorDrawable.Group();
          k.name = c.a("name");
          k.rotate = num(r, c.a("rotation"), 0);
          k.pivotX = num(r, c.a("pivotX"), 0); k.pivotY = num(r, c.a("pivotY"), 0);
          k.scaleX = num(r, c.a("scaleX"), 1); k.scaleY = num(r, c.a("scaleY"), 1);
          k.translateX = num(r, c.a("translateX"), 0); k.translateY = num(r, c.a("translateY"), 0);
          vectorGroup(r, c, k);
          g.children.add(k);
          break;
        }
        case "path": case "clip-path": {
          VectorDrawable.VPath p = new VectorDrawable.VPath();
          p.name = c.a("name");
          p.clip = c.tag.equals("clip-path");
          String d = attr(c.a("pathData"));
          if (d != null && Values.isRef(d)) d = r.getString(Values.refId(d));
          p.path = android.util.PathParser.createPathFromPathData(d);
          if (!p.clip) {
            p.fill = fill(r, c.a("fillColor"));
            p.stroke = fill(r, c.a("strokeColor"));
            p.strokeWidth = num(r, c.a("strokeWidth"), 0);
            p.fillAlpha = num(r, c.a("fillAlpha"), 1);
            p.strokeAlpha = num(r, c.a("strokeAlpha"), 1);
            p.trimStart = num(r, c.a("trimPathStart"), 0);
            p.trimEnd = num(r, c.a("trimPathEnd"), 1);
            p.trimOffset = num(r, c.a("trimPathOffset"), 0);
            p.miter = num(r, c.a("strokeMiterLimit"), 4);
            String cap = c.a("strokeLineCap"), join = c.a("strokeLineJoin");
            if ("round".equals(cap)) p.cap = android.graphics.Paint.Cap.ROUND; else if ("square".equals(cap)) p.cap = android.graphics.Paint.Cap.SQUARE;
            if ("round".equals(join)) p.join = android.graphics.Paint.Join.ROUND; else if ("bevel".equals(join)) p.join = android.graphics.Paint.Join.BEVEL;
            p.evenOdd = "evenOdd".equals(c.a("fillType"));
          }
          g.children.add(p);
          break;
        }
        default:
      }
    }
  }
  /** <ripple>: its layers (the mask layer only bounds the ripple), with the ripple color over them while pressed */
  static Drawable ripple(Resources r, XmlNode n) {
    ArrayList<Drawable> ds = new ArrayList<>();
    Drawable mask = null;
    for (XmlNode it : n.children()) {
      if (!it.tag.equals("item")) continue;
      Drawable d = child(r, it);
      if (d == null) continue;
      String id = it.a("id");
      if (id != null && id.endsWith("id/mask")) { mask = d; continue; }
      ds.add(d);
    }
    VectorDrawable.Fill f = fill(r, n.a("color"));
    RippleDrawable rd = new RippleDrawable(f != null && f.colors != null ? f.colors : ColorStateList.valueOf(0x1f000000), ds.toArray(new Drawable[0]), mask);
    return rd;
  }

  public static Drawable fromXml(Resources r, XmlNode n) {
    switch (n.tag) {
      case "selector": {
        StateListDrawable s = new StateListDrawable();
        for (XmlNode it : n.children()) { Drawable d = child(r, it); if (d != null) s.addState(states(it), d); }
        return s;
      }
      case "level-list": {
        LevelListDrawable l = new LevelListDrawable();
        for (XmlNode it : n.children()) { Drawable d = child(r, it); if (d != null) l.addLevel(Values.parseInt(it.a("minLevel"), 0), Values.parseInt(it.a("maxLevel"), 0), d); }
        return l;
      }
      case "animation-list": {
        AnimationDrawable a = new AnimationDrawable();
        a.setOneShot(Values.parseBool(n.a("oneshot"), false));
        for (XmlNode it : n.children()) { Drawable d = child(r, it); if (d != null) a.addFrame(d, Values.parseInt(it.a("duration"), 0)); }
        if (n.a("visible") != null) a.setVisible(Values.parseBool(n.a("visible"), true), true);
        return a;
      }
      case "layer-list": case "transition": {
        ArrayList<Drawable> ds = new ArrayList<>();
        ArrayList<int[]> info = new ArrayList<>();
        ArrayList<XmlNode> items = new ArrayList<>();
        for (XmlNode it : n.children()) {
          Drawable d = child(r, it);
          if (d == null) continue;
          ds.add(d);
          items.add(it);
        }
        LayerDrawable l = n.tag.equals("transition") ? new TransitionDrawable(ds.toArray(new Drawable[0])) : new LayerDrawable(ds.toArray(new Drawable[0]));
        for (int i = 0; i < items.size(); i++) {
          XmlNode it = items.get(i);
          l.setId(i, it.a("id") == null ? -1 : Values.refId(it.a("id")));
          l.setLayerInset(i, (int) dim(it.a("left"), 0), (int) dim(it.a("top"), 0), (int) dim(it.a("right"), 0), (int) dim(it.a("bottom"), 0));
          if (it.a("start") != null) l.setLayerInsetStart(i, (int) dim(it.a("start"), 0));
          if (it.a("end") != null) l.setLayerInsetEnd(i, (int) dim(it.a("end"), 0));
          if (it.a("width") != null) l.setLayerWidth(i, (int) dim(it.a("width"), -1));
          if (it.a("height") != null) l.setLayerHeight(i, (int) dim(it.a("height"), -1));
          if (it.a("gravity") != null) l.setLayerGravity(i, Values.gravity(it.a("gravity")));
        }
        String pm = n.a("paddingMode");
        if ("stack".equals(pm) || "1".equals(pm)) l.setPaddingMode(LayerDrawable.PADDING_MODE_STACK);
        if (n.a("paddingLeft") != null || n.a("paddingTop") != null || n.a("paddingRight") != null || n.a("paddingBottom") != null)
          l.setPadding((int) dim(n.a("paddingLeft"), -1), (int) dim(n.a("paddingTop"), -1), (int) dim(n.a("paddingRight"), -1), (int) dim(n.a("paddingBottom"), -1));
        if (n.a("paddingStart") != null || n.a("paddingEnd") != null)
          l.setPaddingRelative((int) dim(n.a("paddingStart"), -1), (int) dim(n.a("paddingTop"), -1), (int) dim(n.a("paddingEnd"), -1), (int) dim(n.a("paddingBottom"), -1));
        if (n.a("opacity") != null) {
          String op = n.a("opacity");
          l.setOpacity("opaque".equals(op) ? android.graphics.PixelFormat.OPAQUE : "transparent".equals(op) ? android.graphics.PixelFormat.TRANSPARENT : "translucent".equals(op) ? android.graphics.PixelFormat.TRANSLUCENT : android.graphics.PixelFormat.UNKNOWN);
        }
        return l;
      }
      case "bitmap": case "nine-patch": {
        Drawable d = ref(r, n.a("src"));
        if (d instanceof BitmapDrawable) {
          BitmapDrawable b = (BitmapDrawable) d;
          String g = n.a("gravity");
          if (g != null) b.setGravity(Values.gravity(g));
          String t = n.a("tileMode");
          android.graphics.Shader.TileMode tm = "repeat".equals(t) ? android.graphics.Shader.TileMode.REPEAT : "mirror".equals(t) ? android.graphics.Shader.TileMode.MIRROR
              : "clamp".equals(t) ? android.graphics.Shader.TileMode.CLAMP : null;
          String tx = n.a("tileModeX"), ty = n.a("tileModeY");
          android.graphics.Shader.TileMode mx = tile(tx, tm), my = tile(ty, tm);
          if (mx != null || my != null) { b.setTileModeX(mx); b.setTileModeY(my); }
        }
        return d;
      }
      case "clip": {
        Drawable d = child(r, n);
        String o = n.a("clipOrientation");
        return new ClipDrawable(d, n.a("gravity") == null ? Gravity.LEFT : Values.gravity(n.a("gravity")), "vertical".equals(o) ? ClipDrawable.VERTICAL : ClipDrawable.HORIZONTAL);
      }
      case "scale": {
        Drawable d = child(r, n);
        return new ScaleDrawable(d, n.a("scaleGravity") == null ? Gravity.LEFT : Values.gravity(n.a("scaleGravity")), pct(n.a("scaleWidth")), pct(n.a("scaleHeight")));
      }
      case "inset": {
        Drawable d = child(r, n);
        String[] in = {n.a("insetLeft"), n.a("insetTop"), n.a("insetRight"), n.a("insetBottom")};
        for (int i = 0; i < 4; i++) if (in[i] == null) in[i] = n.a("inset");
        boolean frac = false;
        for (String v : in) frac |= v != null && v.endsWith("%");
        if (frac) {
          float[] f = new float[4];
          for (int i = 0; i < 4; i++) f[i] = in[i] != null && in[i].endsWith("%") ? pct(in[i]) : 0;
          return new InsetDrawable(d, f[0], f[1], f[2], f[3]);
        }
        return new InsetDrawable(d, (int) dim(in[0], 0), (int) dim(in[1], 0), (int) dim(in[2], 0), (int) dim(in[3], 0));
      }
      case "rotate": case "animated-rotate": {
        RotateDrawable d = new RotateDrawable(child(r, n));
        if (n.tag.equals("rotate")) {
          if (n.a("fromDegrees") != null) d.setFromDegrees(Values.parseFloat(n.a("fromDegrees"), 0));
          if (n.a("toDegrees") != null) d.setToDegrees(Values.parseFloat(n.a("toDegrees"), 360));
        }
        String px = n.a("pivotX"), py = n.a("pivotY");
        if (px != null) { boolean rel = px.endsWith("%"); d.setPivotXRelative(rel); d.setPivotX(rel ? pct(px) : dim(px, 0)); }
        if (py != null) { boolean rel = py.endsWith("%"); d.setPivotYRelative(rel); d.setPivotY(rel ? pct(py) : dim(py, 0)); }
        return d;
      }
      case "color": return new ColorDrawable(color(r, n.a("color"), 0));
      case "shape": return shape(r, n);
      case "vector": return vector(r, n);
      case "animated-vector": {
        Drawable v = ref(r, n.a("drawable"));
        if (v == null) for (XmlNode c : n.children()) if (c.tag.equals("vector")) v = vector(r, c);   // (inline aapt:attr)
        return new AnimatedVectorDrawable(v);
      }
      case "ripple": return ripple(r, n);
      case "animated-selector": {
        AnimatedStateListDrawable s = new AnimatedStateListDrawable();
        for (XmlNode it : n.children()) {
          if (!it.tag.equals("item")) continue;
          Drawable d = child(r, it);
          if (d != null) s.addState(states(it), d, it.a("id") == null ? 0 : Values.refId(it.a("id")));
        }
        for (XmlNode it : n.children()) {
          if (!it.tag.equals("transition")) continue;
          Drawable d = child(r, it);
          if (!(d instanceof Animatable) || it.a("fromId") == null || it.a("toId") == null) continue;
          addTransition(s, Values.refId(it.a("fromId")), Values.refId(it.a("toId")), d, Values.parseBool(it.a("reversible"), false));
        }
        return s;
      }
      case "adaptive-icon": {
        Drawable bg = null, fg = null, mono = null;
        for (XmlNode it : n.children()) {
          if (it.tag.equals("background")) bg = child(r, it);
          else if (it.tag.equals("foreground")) fg = child(r, it);
          else if (it.tag.equals("monochrome")) mono = child(r, it);
        }
        return new AdaptiveIconDrawable(bg, fg, mono);
      }
      case "animated-image": {
        String src = attr(n.a("src"));
        if (src == null || !Values.isRef(src)) return new ColorDrawable(0);
        android.util.TypedValue tv = new android.util.TypedValue();
        android.graphics.Movie m;
        try (java.io.InputStream is = r.openRawResource(Values.refId(src), tv)) { m = android.graphics.Movie.decodeStream(is); } catch (Exception e) { m = null; }
        if (m == null) { Drawable d = ref(r, src); return d != null ? d : new ColorDrawable(0); }
        AnimatedImageDrawable a = new AnimatedImageDrawable();
        a.a2wSetMovie(m, tv.density == 0 || tv.density == android.util.TypedValue.DENSITY_DEFAULT ? android.util.DisplayMetrics.DENSITY_DEFAULT : tv.density);
        if (n.a("repeatCount") != null) a.setRepeatCount(Values.parseInt(n.a("repeatCount"), -1));
        if (n.a("autoMirrored") != null) a.setAutoMirrored(Values.parseBool(n.a("autoMirrored"), false));
        if (Values.parseBool(n.a("autoStart"), false)) a.start();
        return a;
      }
    }
    Host.log(5, "W/Drawables: unsupported drawable <" + n.tag + ">");
    return new ColorDrawable(0);
  }
  @SuppressWarnings({"unchecked", "rawtypes"})
  private static void addTransition(AnimatedStateListDrawable s, int from, int to, Drawable d, boolean reversible) {
    if (d instanceof AnimationDrawable) s.addTransition(from, to, (AnimationDrawable) d, reversible);
    else if (d instanceof AnimatedVectorDrawable) s.addTransition(from, to, (AnimatedVectorDrawable) d, reversible);
    else if (d instanceof AnimatedImageDrawable) s.addTransition(from, to, (AnimatedImageDrawable) d, reversible);
  }
  static float pct(String v) { if (v == null) return -1; return Values.parseFloat(v.replace("%", ""), 0) / 100f; }

  static Drawable shape(Resources r, XmlNode n) {
    GradientDrawable g = new GradientDrawable();
    String s = n.a("shape");
    g.setShape("oval".equals(s) ? GradientDrawable.OVAL : "line".equals(s) ? GradientDrawable.LINE : "ring".equals(s) ? GradientDrawable.RING : GradientDrawable.RECTANGLE);
    if ("ring".equals(s)) {
      if (n.a("innerRadius") != null) g.setInnerRadius((int) dim(n.a("innerRadius"), -1));
      if (n.a("thickness") != null) g.setThickness((int) dim(n.a("thickness"), -1));
      if (n.a("innerRadiusRatio") != null) g.setInnerRadiusRatio(Values.parseFloat(n.a("innerRadiusRatio"), 3));
      if (n.a("thicknessRatio") != null) g.setThicknessRatio(Values.parseFloat(n.a("thicknessRatio"), 9));
      g.a2wSetUseLevelForShape(Values.parseBool(n.a("useLevel"), true));
    }
    for (XmlNode c : n.children()) {
      switch (c.tag) {
        case "solid": { ColorStateList l = colorList(r, c.a("color")); if (l != null) g.setColor(l); else g.setColor(color(r, c.a("color"), 0)); break; }
        case "stroke": {
          ColorStateList l = colorList(r, c.a("color"));
          if (l != null) g.setStroke((int) dim(c.a("width"), 0), l, dim(c.a("dashWidth"), 0), dim(c.a("dashGap"), 0));
          else g.setStroke((int) dim(c.a("width"), 0), color(r, c.a("color"), 0), dim(c.a("dashWidth"), 0), dim(c.a("dashGap"), 0));
          break;
        }
        case "corners": {
          float rad = dim(c.a("radius"), 0);
          float tl = dim(c.a("topLeftRadius"), rad), tr = dim(c.a("topRightRadius"), rad), bl = dim(c.a("bottomLeftRadius"), rad), br = dim(c.a("bottomRightRadius"), rad);
          if (tl == rad && tr == rad && bl == rad && br == rad) g.setCornerRadius(rad);
          else g.setCornerRadii(new float[] {tl, tl, tr, tr, br, br, bl, bl});
          break;
        }
        case "padding": g.setPadding((int) dim(c.a("left"), 0), (int) dim(c.a("top"), 0), (int) dim(c.a("right"), 0), (int) dim(c.a("bottom"), 0)); break;
        case "size": g.setSize((int) dim(c.a("width"), -1), (int) dim(c.a("height"), -1)); break;
        case "gradient": {
          int start = color(r, c.a("startColor"), 0), end = color(r, c.a("endColor"), 0);
          String center = c.a("centerColor");
          g.setColors(center == null ? new int[] {start, end} : new int[] {start, color(r, center, 0), end});
          String type = c.a("type");
          g.setGradientType("radial".equals(type) ? GradientDrawable.RADIAL_GRADIENT : "sweep".equals(type) ? GradientDrawable.SWEEP_GRADIENT : GradientDrawable.LINEAR_GRADIENT);
          int angle = Values.parseInt(c.a("angle"), 0) % 360;
          GradientDrawable.Orientation[] o = {GradientDrawable.Orientation.LEFT_RIGHT, GradientDrawable.Orientation.BL_TR, GradientDrawable.Orientation.BOTTOM_TOP,
              GradientDrawable.Orientation.BR_TL, GradientDrawable.Orientation.RIGHT_LEFT, GradientDrawable.Orientation.TR_BL,
              GradientDrawable.Orientation.TOP_BOTTOM, GradientDrawable.Orientation.TL_BR};
          g.setOrientation(o[((angle + 360) % 360) / 45]);
          if (c.a("gradientRadius") != null) g.setGradientRadius(dim(c.a("gradientRadius"), 0.5f));
          g.setGradientCenter(Values.parseFloat(c.a("centerX"), 0.5f), Values.parseFloat(c.a("centerY"), 0.5f));
          g.setUseLevel(Values.parseBool(c.a("useLevel"), false));
          break;
        }
      }
    }
    return g;
  }

  // ---------------------------------------------------------------- framework look (Android 2.x/4.x "Theme")
  static float dp(float v) { return v * Values.METRICS.density; }
  static GradientDrawable rr(int top, int bottom, int stroke, float radius) {
    GradientDrawable g = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[] {top, bottom});
    g.setCornerRadius(radius);
    if (stroke != 0) g.setStroke((int) Math.max(1, dp(1)), stroke);
    return g;
  }
  static Drawable framework(Resources r, String name) {
    if (name == null) return null;
    switch (name) {
      case "drawable/btn_default": {
        StateListDrawable s = new StateListDrawable();
        GradientDrawable pressed = rr(0xffffc94c, 0xffff8a00, 0xff8d5a00, dp(5));
        GradientDrawable disabled = rr(0xffd8d8d8, 0xffb8b8b8, 0xff8a8a8a, dp(5));
        GradientDrawable normal = rr(0xfff6f6f6, 0xffc9c9c9, 0xff7a7a7a, dp(5));
        for (GradientDrawable g : new GradientDrawable[] {pressed, disabled, normal}) g.setPadding((int) dp(10), (int) dp(7), (int) dp(10), (int) dp(7));
        s.addState(new int[] {android.R.attr.state_pressed}, pressed);
        s.addState(new int[] {-android.R.attr.state_enabled}, disabled);
        s.addState(new int[0], normal);
        return s;
      }
      case "drawable/edit_text": {
        StateListDrawable s = new StateListDrawable();
        GradientDrawable focused = rr(0xffffffff, 0xffffffff, 0xffff9900, dp(3));
        GradientDrawable normal = rr(0xffffffff, 0xfff0f0f0, 0xff9a9a9a, dp(3));
        for (GradientDrawable g : new GradientDrawable[] {focused, normal}) g.setPadding((int) dp(8), (int) dp(6), (int) dp(8), (int) dp(6));
        s.addState(new int[] {android.R.attr.state_focused}, focused);
        s.addState(new int[0], normal);
        return s;
      }
      case "drawable/progress_horizontal": {
        GradientDrawable bg = rr(0xff9d9e9d, 0xff747674, 0, dp(5));
        GradientDrawable sec = rr(0x80ffd300, 0xa0ffcb00, 0, dp(5));
        GradientDrawable prog = rr(0xffffd300, 0xffffb600, 0, dp(5));
        LayerDrawable l = new LayerDrawable(new Drawable[] {bg, new ClipDrawable(sec, Gravity.LEFT, ClipDrawable.HORIZONTAL), new ClipDrawable(prog, Gravity.LEFT, ClipDrawable.HORIZONTAL)});
        l.setId(0, android.R.id.background); l.setId(1, android.R.id.secondaryProgress); l.setId(2, android.R.id.progress);
        return l;
      }
      case "drawable/progress_indeterminate_horizontal": return rr(0xffffd300, 0xffffb600, 0, dp(5));
      case "drawable/progress_small_white": case "drawable/progress_small": return new SpinnerDrawable((int) dp(16));
      case "drawable/progress_large_white": case "drawable/progress_large": return new SpinnerDrawable((int) dp(76));
      case "drawable/progress_medium_white": case "drawable/progress_medium": return new SpinnerDrawable((int) dp(48));
      case "drawable/seek_thumb": {
        GradientDrawable g = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[] {0xfffafafa, 0xffbdbdbd});
        g.setShape(GradientDrawable.OVAL);
        g.setStroke((int) dp(1), 0xff6a6a6a);
        g.setSize((int) dp(20), (int) dp(20));
        return g;
      }
      case "drawable/panel_background": case "drawable/dialog_full_dark": {
        GradientDrawable g = rr(0xf0333333, 0xf0222222, 0xff8a8a8a, dp(6));
        g.setPadding((int) dp(8), (int) dp(8), (int) dp(8), (int) dp(8));
        return g;
      }
      case "drawable/divider_horizontal_dark": case "drawable/divider_horizontal_bright": {
        GradientDrawable g = new GradientDrawable();
        g.setColor(0xff4f4f4f);
        g.setSize(1, 1);
        return g;
      }
      case "drawable/list_selector_background": {
        // pressed: orange; focused (the keyboard's selected item): lighter orange; nothing while the window lacks focus
        StateListDrawable s = new StateListDrawable();
        s.addState(new int[] {-android.R.attr.state_window_focused}, new ColorDrawable(0));
        s.addState(new int[] {android.R.attr.state_pressed}, new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[] {0xffffc94c, 0xffff8a00}));
        s.addState(new int[] {android.R.attr.state_focused, -android.R.attr.state_enabled}, new ColorDrawable(0x80808080));
        s.addState(new int[] {android.R.attr.state_focused}, new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[] {0xffffe08a, 0xffffb02e}));
        s.addState(new int[0], new ColorDrawable(0));
        return s;
      }
      case "drawable/screen_background_dark": return new ColorDrawable(0xff000000);
      case "drawable/btn_toggle_bg": return WidgetDrawables.toggleBackground(r);
      case "drawable/btn_toggle": return new WidgetDrawables.ToggleBar();
      case "drawable/btn_check_label_background": return new ColorDrawable(0);
      case "drawable/activated_background": {
        // a checked (activated) list item
        StateListDrawable s = new StateListDrawable();
        s.addState(new int[] {android.R.attr.state_activated}, new ColorDrawable(0x8033b5e5));
        s.addState(new int[0], new ColorDrawable(0));
        return s;
      }
      case "color/transparent": return new ColorDrawable(0);
      case "color/black": return new ColorDrawable(0xff000000);
      case "color/white": return new ColorDrawable(0xffffffff);
      case "color/background_dark": return new ColorDrawable(0xff000000);
      case "color/background_light": return new ColorDrawable(0xffffffff);
      case "color/darker_gray": return new ColorDrawable(0xffaaaaaa);
    }
    if (name.startsWith("color/")) {
      int id = Values.frameworkId(name);
      if (Res.color(id) != null) return new ColorDrawable(r.getColor(id));
    }
    // the compound-button indicators in any of their themed names (btn_check_holo_light, btn_radio_material...)
    if (name.startsWith("drawable/btn_check") && !name.contains("label")) return new WidgetDrawables.Indicator(WidgetDrawables.Indicator.CHECK);
    if (name.startsWith("drawable/btn_radio")) return new WidgetDrawables.Indicator(WidgetDrawables.Indicator.RADIO);
    if (name.startsWith("drawable/btn_star")) return new WidgetDrawables.Indicator(WidgetDrawables.Indicator.STAR);
    return new ColorDrawable(0);
  }
}
