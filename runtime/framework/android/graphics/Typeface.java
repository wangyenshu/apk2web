package android.graphics;

import android.content.res.AssetManager;
import java.util.HashMap;

public class Typeface {
  public static final int NORMAL = 0, BOLD = 1, ITALIC = 2, BOLD_ITALIC = 3;
  /** CSS font-family list */
  public final String family;
  final int style;
  private static final HashMap<String, Typeface> assets = new HashMap<>();
  private static int nextFont;

  public static final Typeface DEFAULT = new Typeface("sans-serif", NORMAL);
  public static final Typeface DEFAULT_BOLD = new Typeface("sans-serif", BOLD);
  public static final Typeface SANS_SERIF = new Typeface("sans-serif", NORMAL);
  public static final Typeface SERIF = new Typeface("serif", NORMAL);
  public static final Typeface MONOSPACE = new Typeface("monospace", NORMAL);

  Typeface(String family, int style) { this.family = family; this.style = style; }
  public int getStyle() { return style; }
  public final boolean isBold() { return (style & BOLD) != 0; }
  public final boolean isItalic() { return (style & ITALIC) != 0; }

  public static Typeface create(String familyName, int style) {
    String f = familyName == null ? "sans-serif" : familyName;
    if (f.equals("sans-serif") || f.equals("serif") || f.equals("monospace")) return new Typeface(f, style);
    return new Typeface("\"" + f + "\", sans-serif", style);
  }
  public static Typeface create(Typeface family, int style) { return new Typeface(family == null ? "sans-serif" : family.family, style); }
  public static Typeface defaultFromStyle(int style) { return style == 0 ? DEFAULT : new Typeface("sans-serif", style); }

  public static Typeface createFromAsset(AssetManager mgr, String path) {
    synchronized (assets) {
      Typeface t = assets.get(path);
      if (t != null) return t;
      byte[] data = mgr.readAsset(path);
      if (data == null) throw new RuntimeException("native typeface cannot be made");
      String name = "hwfont" + (nextFont++);
      if (!a2w.Host.loadFont(name, data)) throw new RuntimeException("native typeface cannot be made");
      t = new Typeface("\"" + name + "\", sans-serif", NORMAL);
      assets.put(path, t);
      return t;
    }
  }
  public static Typeface createFromFile(String path) { return createFromFile(new java.io.File(path)); }
  public static Typeface createFromFile(java.io.File f) {
    String key = "file:" + f.getAbsolutePath();
    synchronized (assets) {
      Typeface t = assets.get(key);
      if (t != null) return t;
    }
    byte[] data;
    try (java.io.InputStream in = new java.io.FileInputStream(f)) {
      java.io.ByteArrayOutputStream o = new java.io.ByteArrayOutputStream();
      byte[] b = new byte[8192];
      for (int n; (n = in.read(b)) > 0; ) o.write(b, 0, n);
      data = o.toByteArray();
    } catch (java.io.IOException e) { throw new RuntimeException("Font asset not found " + f); }
    return fromBytes(key, data);
  }
  /** a font from its file's bytes (a font resource, a file), cached under key */
  public static Typeface fromBytes(String key, byte[] data) {
    synchronized (assets) {
      Typeface t = assets.get(key);
      if (t != null) return t;
      String name = "hwfont" + (nextFont++);
      if (data == null || !a2w.Host.loadFont(name, data)) throw new RuntimeException("native typeface cannot be made");
      t = new Typeface("\"" + name + "\", sans-serif", NORMAL);
      assets.put(key, t);
      return t;
    }
  }
  public static Typeface create(Typeface family, int weight, boolean italic) {
    return new Typeface(family == null ? "sans-serif" : family.family, (weight >= 600 ? BOLD : 0) | (italic ? ITALIC : 0));
  }
  public int getWeight() { return isBold() ? 700 : 400; }
  public String getSystemFontFamilyName() { return family; }
  public boolean equals(Object o) { return o instanceof Typeface && ((Typeface) o).family.equals(family) && ((Typeface) o).style == style; }
  public int hashCode() { return family.hashCode() * 31 + style; }
}
