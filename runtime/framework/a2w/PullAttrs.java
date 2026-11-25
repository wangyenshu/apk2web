package a2w;

import android.util.AttributeSet;
import org.xmlpull.v1.XmlPullParser;

/** Xml.asAttributeSet: the attributes of a plain pull parser's current tag. */
public final class PullAttrs implements AttributeSet {
  private final XmlPullParser p;
  public PullAttrs(XmlPullParser p) { this.p = p; }
  public int getAttributeCount() { return p.getAttributeCount(); }
  public String getAttributeName(int i) { return p.getAttributeName(i); }
  public String getAttributeValue(int i) { return p.getAttributeValue(i); }
  public String getAttributeValue(String ns, String name) { return p.getAttributeValue(ns, name); }
  public String getAttributeNamespace(int i) { return p.getAttributeNamespace(i); }
  public String getPositionDescription() { return p.getPositionDescription(); }
  public int getAttributeNameResource(int i) { return 0; }
  private static int list(String v, String[] o, int d) { if (v != null && o != null) for (int i = 0; i < o.length; i++) if (o[i].equals(v)) return i; return d; }
  public int getAttributeListValue(String ns, String a, String[] o, int d) { return list(getAttributeValue(ns, a), o, d); }
  public boolean getAttributeBooleanValue(String ns, String a, boolean d) { return Values.parseBool(getAttributeValue(ns, a), d); }
  public int getAttributeResourceValue(String ns, String a, int d) { String v = getAttributeValue(ns, a); return Values.isRef(v) ? Values.refId(v) : d; }
  public int getAttributeIntValue(String ns, String a, int d) { return Values.parseInt(getAttributeValue(ns, a), d); }
  public int getAttributeUnsignedIntValue(String ns, String a, int d) { return Values.parseInt(getAttributeValue(ns, a), d); }
  public float getAttributeFloatValue(String ns, String a, float d) { return Values.parseFloat(getAttributeValue(ns, a), d); }
  public int getAttributeListValue(int i, String[] o, int d) { return list(getAttributeValue(i), o, d); }
  public boolean getAttributeBooleanValue(int i, boolean d) { return Values.parseBool(getAttributeValue(i), d); }
  public int getAttributeResourceValue(int i, int d) { String v = getAttributeValue(i); return Values.isRef(v) ? Values.refId(v) : d; }
  public int getAttributeIntValue(int i, int d) { return Values.parseInt(getAttributeValue(i), d); }
  public int getAttributeUnsignedIntValue(int i, int d) { return Values.parseInt(getAttributeValue(i), d); }
  public float getAttributeFloatValue(int i, float d) { return Values.parseFloat(getAttributeValue(i), d); }
  public String getIdAttribute() { return getAttributeValue(null, "id"); }
  public String getClassAttribute() { return getAttributeValue(null, "class"); }
  public int getIdAttributeResourceValue(int d) { return getAttributeResourceValue(null, "id", d); }
  public int getStyleAttribute() { return getAttributeResourceValue(null, "style", 0); }
}
