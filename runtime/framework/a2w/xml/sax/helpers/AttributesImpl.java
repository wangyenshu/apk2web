package a2w.xml.sax.helpers;

import a2w.xml.sax.Attributes;
import java.util.ArrayList;

public class AttributesImpl implements Attributes {
  private final ArrayList<String[]> a = new ArrayList<>();   // uri, local, qname, type, value
  public AttributesImpl() {}
  public AttributesImpl(Attributes atts) { setAttributes(atts); }
  @Override public int getLength() { return a.size(); }
  private String f(int i, int k) { return i >= 0 && i < a.size() ? a.get(i)[k] : null; }
  @Override public String getURI(int i) { return f(i, 0); }
  @Override public String getLocalName(int i) { return f(i, 1); }
  @Override public String getQName(int i) { return f(i, 2); }
  @Override public String getType(int i) { return f(i, 3); }
  @Override public String getValue(int i) { return f(i, 4); }
  @Override public int getIndex(String uri, String local) { for (int i = 0; i < a.size(); i++) if (a.get(i)[0].equals(uri) && a.get(i)[1].equals(local)) return i; return -1; }
  @Override public int getIndex(String q) { for (int i = 0; i < a.size(); i++) if (a.get(i)[2].equals(q)) return i; return -1; }
  @Override public String getType(String uri, String local) { return getType(getIndex(uri, local)); }
  @Override public String getType(String q) { return getType(getIndex(q)); }
  @Override public String getValue(String uri, String local) { return getValue(getIndex(uri, local)); }
  @Override public String getValue(String q) { return getValue(getIndex(q)); }
  public void clear() { a.clear(); }
  public void setAttributes(Attributes atts) { clear(); for (int i = 0; i < atts.getLength(); i++) addAttribute(atts.getURI(i), atts.getLocalName(i), atts.getQName(i), atts.getType(i), atts.getValue(i)); }
  public void addAttribute(String uri, String local, String q, String type, String value) { a.add(new String[] {nz(uri), nz(local), nz(q), type == null ? "CDATA" : type, nz(value)}); }
  public void setAttribute(int i, String uri, String local, String q, String type, String value) { check(i); a.set(i, new String[] {nz(uri), nz(local), nz(q), type, nz(value)}); }
  public void removeAttribute(int i) { check(i); a.remove(i); }
  public void setURI(int i, String v) { check(i); a.get(i)[0] = nz(v); }
  public void setLocalName(int i, String v) { check(i); a.get(i)[1] = nz(v); }
  public void setQName(int i, String v) { check(i); a.get(i)[2] = nz(v); }
  public void setType(int i, String v) { check(i); a.get(i)[3] = v; }
  public void setValue(int i, String v) { check(i); a.get(i)[4] = nz(v); }
  private void check(int i) { if (i < 0 || i >= a.size()) throw new ArrayIndexOutOfBoundsException("Attempt to modify attribute at illegal index: " + i); }
  private static String nz(String s) { return s == null ? "" : s; }
}
