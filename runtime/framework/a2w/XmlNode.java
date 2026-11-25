package a2w;

import org.teavm.jso.JSObject;
import java.util.ArrayList;

/** A compiled XML element (layouts, drawables, animations, color lists). */
public final class XmlNode {
  public final String tag;
  /** ns, name, value triples; ns is "a" (android), "x" (app), "" (none) */
  public final String[] attrs;
  private final JSObject js;
  private ArrayList<XmlNode> children;

  public XmlNode(JSObject n) {
    js = n;
    tag = Res.nTag(n);
    attrs = Res.split(Res.nAttrs(n));
  }
  public static XmlNode of(JSObject n) { return n == null ? null : new XmlNode(n); }

  public ArrayList<XmlNode> children() {
    if (children == null) {
      int c = Res.nCount(js);
      children = new ArrayList<>(c);
      for (int i = 0; i < c; i++) children.add(new XmlNode(Res.nChild(js, i)));
    }
    return children;
  }
  public String get(String ns, String name) {
    for (int i = 0; i + 2 < attrs.length; i += 3) if (attrs[i + 1].equals(name) && attrs[i].equals(ns)) return attrs[i + 2];
    return null;
  }
  public String a(String name) { return get("a", name); }
  /** character data before the first child / after this element (null when only whitespace) */
  public String text() { return Res.nText(js); }
  public String tail() { return Res.nTail(js); }
  public String toString() { return "<" + tag + ">"; }
}
