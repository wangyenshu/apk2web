package android.os;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/** A Bundle of values that can be written to storage: ints, longs, doubles, booleans, strings, their arrays, and nested PersistableBundles. */
public final class PersistableBundle extends BaseBundle implements Cloneable, Parcelable {
  public static final PersistableBundle EMPTY = new PersistableBundle();
  public static final Parcelable.Creator<PersistableBundle> CREATOR = new Parcelable.Creator<PersistableBundle>() {
    @Override public PersistableBundle createFromParcel(Parcel in) { return in.readPersistableBundle(); }
    @Override public PersistableBundle[] newArray(int size) { return new PersistableBundle[size]; }
  };

  public PersistableBundle() {}
  public PersistableBundle(int capacity) {}
  public PersistableBundle(PersistableBundle b) { super(b); }

  public void putPersistableBundle(String key, PersistableBundle value) { map.put(key, value); }
  public PersistableBundle getPersistableBundle(String key) { Object o = map.get(key); return o instanceof PersistableBundle ? (PersistableBundle) o : null; }
  @Override public Object clone() { return new PersistableBundle(this); }
  public PersistableBundle deepCopy() {
    PersistableBundle b = new PersistableBundle();
    for (java.util.Map.Entry<String, Object> e : map.entrySet()) b.map.put(e.getKey(), deepCopyValue(e.getValue()));
    return b;
  }
  @Override public int describeContents() { return 0; }
  @Override public void writeToParcel(Parcel p, int flags) { writeMap(p); }
  @Override public String toString() { return "PersistableBundle[" + map + "]"; }

  // ---- storage: XML (<pbundle> with <int name= value=/>, <string name=>text</string>, arrays as <int-array>...)
  public void writeToStream(OutputStream out) throws IOException {
    StringBuilder b = new StringBuilder("<?xml version='1.0' encoding='utf-8' standalone='yes' ?>\n<pbundle>");
    write(b, this);
    out.write(b.append("</pbundle>\n").toString().getBytes(java.nio.charset.StandardCharsets.UTF_8));
    out.flush();
  }
  private static void write(StringBuilder b, PersistableBundle pb) {
    for (java.util.Map.Entry<String, Object> e : new java.util.TreeMap<>(pb.map).entrySet()) {
      String n = " name=\"" + esc(e.getKey()) + "\"";
      Object v = e.getValue();
      if (v == null) b.append("<null").append(n).append("/>");
      else if (v instanceof Integer) b.append("<int").append(n).append(" value=\"").append(v).append("\"/>");
      else if (v instanceof Long) b.append("<long").append(n).append(" value=\"").append(v).append("\"/>");
      else if (v instanceof Double) b.append("<double").append(n).append(" value=\"").append(v).append("\"/>");
      else if (v instanceof Boolean) b.append("<boolean").append(n).append(" value=\"").append(v).append("\"/>");
      else if (v instanceof String s) b.append("<string").append(n).append(">").append(esc(s)).append("</string>");
      else if (v instanceof PersistableBundle p) { b.append("<pbundle").append(n).append(">"); write(b, p); b.append("</pbundle>"); }
      else if (v instanceof int[] a) { b.append("<int-array").append(n).append(" num=\"").append(a.length).append("\">"); for (int x : a) b.append("<item value=\"").append(x).append("\"/>"); b.append("</int-array>"); }
      else if (v instanceof long[] a) { b.append("<long-array").append(n).append(" num=\"").append(a.length).append("\">"); for (long x : a) b.append("<item value=\"").append(x).append("\"/>"); b.append("</long-array>"); }
      else if (v instanceof double[] a) { b.append("<double-array").append(n).append(" num=\"").append(a.length).append("\">"); for (double x : a) b.append("<item value=\"").append(x).append("\"/>"); b.append("</double-array>"); }
      else if (v instanceof boolean[] a) { b.append("<boolean-array").append(n).append(" num=\"").append(a.length).append("\">"); for (boolean x : a) b.append("<item value=\"").append(x).append("\"/>"); b.append("</boolean-array>"); }
      else if (v instanceof String[] a) { b.append("<string-array").append(n).append(" num=\"").append(a.length).append("\">"); for (String x : a) b.append("<item value=\"").append(x == null ? "" : esc(x)).append("\"/>"); b.append("</string-array>"); }
    }
  }
  private static String esc(String s) { return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;"); }

  public static PersistableBundle readFromStream(InputStream in) throws IOException {
    try {
      org.xmlpull.v1.XmlPullParser p = new org.kxml2.io.KXmlParser();
      p.setInput(in, "UTF-8");
      int ev;
      while ((ev = p.next()) != org.xmlpull.v1.XmlPullParser.START_TAG) if (ev == org.xmlpull.v1.XmlPullParser.END_DOCUMENT) throw new IOException("no pbundle");
      return read(p);
    } catch (org.xmlpull.v1.XmlPullParserException e) { throw new IOException(e.getMessage()); }
  }
  private static PersistableBundle read(org.xmlpull.v1.XmlPullParser p) throws IOException, org.xmlpull.v1.XmlPullParserException {
    PersistableBundle b = new PersistableBundle();
    int depth = p.getDepth();
    while (true) {
      int ev = p.next();
      if (ev == org.xmlpull.v1.XmlPullParser.END_DOCUMENT || ev == org.xmlpull.v1.XmlPullParser.END_TAG && p.getDepth() == depth) return b;
      if (ev != org.xmlpull.v1.XmlPullParser.START_TAG) continue;
      String tag = p.getName(), name = p.getAttributeValue(null, "name"), v = p.getAttributeValue(null, "value");
      switch (tag) {
        case "int": b.map.put(name, Integer.parseInt(v)); break;
        case "long": b.map.put(name, Long.parseLong(v)); break;
        case "double": b.map.put(name, Double.parseDouble(v)); break;
        case "boolean": b.map.put(name, Boolean.parseBoolean(v)); break;
        case "null": b.map.put(name, null); break;
        case "string": b.map.put(name, p.nextText()); break;
        case "pbundle": b.map.put(name, read(p)); break;
        default:
          if (tag.endsWith("-array")) {
            java.util.List<String> items = new java.util.ArrayList<>();
            int d = p.getDepth();
            while (true) {
              int e2 = p.next();
              if (e2 == org.xmlpull.v1.XmlPullParser.END_TAG && p.getDepth() == d) break;
              if (e2 == org.xmlpull.v1.XmlPullParser.START_TAG) items.add(p.getAttributeValue(null, "value"));
              if (e2 == org.xmlpull.v1.XmlPullParser.END_DOCUMENT) break;
            }
            int n = items.size();
            switch (tag) {
              case "int-array": { int[] a = new int[n]; for (int i = 0; i < n; i++) a[i] = Integer.parseInt(items.get(i)); b.map.put(name, a); break; }
              case "long-array": { long[] a = new long[n]; for (int i = 0; i < n; i++) a[i] = Long.parseLong(items.get(i)); b.map.put(name, a); break; }
              case "double-array": { double[] a = new double[n]; for (int i = 0; i < n; i++) a[i] = Double.parseDouble(items.get(i)); b.map.put(name, a); break; }
              case "boolean-array": { boolean[] a = new boolean[n]; for (int i = 0; i < n; i++) a[i] = Boolean.parseBoolean(items.get(i)); b.map.put(name, a); break; }
              default: b.map.put(name, items.toArray(new String[0]));
            }
          }
      }
    }
  }
}
