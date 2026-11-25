package a2w;

import android.content.res.XmlResourceParser;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/** The pull parser over a compiled XML resource (Resources.getXml/getLayout/getAnimation). */
public final class XmlResParser implements XmlResourceParser {
  static final String ANDROID = "http://schemas.android.com/apk/res/android", AUTO = "http://schemas.android.com/apk/res-auto";
  // flattened events: type, node, text
  private final ArrayList<Object[]> events = new ArrayList<>();
  private int pos = -1;
  private final ArrayList<XmlNode> stack = new ArrayList<>();
  private Attrs attrs;

  public XmlResParser(XmlNode root) {
    events.add(new Object[] {START_DOCUMENT, null, null});
    if (root != null) flatten(root);
    events.add(new Object[] {END_DOCUMENT, null, null});
  }
  private void flatten(XmlNode n) {
    events.add(new Object[] {START_TAG, n, null});
    if (n.text() != null) events.add(new Object[] {TEXT, null, n.text()});
    for (XmlNode c : n.children()) {
      flatten(c);
      if (c.tail() != null) events.add(new Object[] {TEXT, null, c.tail()});
    }
    events.add(new Object[] {END_TAG, n, null});
  }
  private Object[] cur() { return events.get(Math.max(pos, 0)); }
  /** the element at the parser's position (before the first element: the first element) */
  public XmlNode currentNode() {
    XmlNode n = node();
    if (n != null) return n;
    for (int i = Math.max(pos, 0); i < events.size(); i++) if ((int) events.get(i)[0] == START_TAG) return (XmlNode) events.get(i)[1];
    return null;
  }
  private XmlNode node() {
    Object[] e = cur();
    return (int) e[0] == START_TAG ? (XmlNode) e[1] : null;
  }
  private Attrs at() {
    XmlNode n = node();
    if (n == null) return null;
    if (attrs == null || attrs.node != n) attrs = new Attrs(n);
    return attrs;
  }

  // ---------------------------------------------------------------- XmlPullParser
  public int next() throws XmlPullParserException {
    if (pos >= 0 && (int) cur()[0] == END_TAG) stack.remove(stack.size() - 1);
    if (pos + 1 >= events.size()) return END_DOCUMENT;
    pos++;
    if ((int) cur()[0] == START_TAG) stack.add((XmlNode) cur()[1]);
    return getEventType();
  }
  public int nextToken() throws XmlPullParserException { return next(); }
  public int getEventType() { return pos < 0 ? START_DOCUMENT : (int) cur()[0]; }
  public int nextTag() throws XmlPullParserException {
    int t = next();
    if (t == TEXT && isWhitespace()) t = next();
    if (t != START_TAG && t != END_TAG) throw new XmlPullParserException("expected start or end tag", this, null);
    return t;
  }
  public String nextText() throws XmlPullParserException {
    if (getEventType() != START_TAG) throw new XmlPullParserException("parser must be on START_TAG to read next text", this, null);
    int t = next();
    if (t == TEXT) { String s = getText(); t = next(); if (t != END_TAG) throw new XmlPullParserException("event TEXT must be immediately followed by END_TAG", this, null); return s; }
    if (t == END_TAG) return "";
    throw new XmlPullParserException("parser must be on START_TAG or TEXT to read text", this, null);
  }
  public void require(int type, String namespace, String name) throws XmlPullParserException {
    if (type != getEventType() || name != null && !name.equals(getName()))
      throw new XmlPullParserException("expected " + TYPES[type] + " " + name + ", got " + TYPES[getEventType()] + " " + getName(), this, null);
  }
  public String getName() {
    int t = getEventType();
    return t == START_TAG || t == END_TAG ? ((XmlNode) cur()[1]).tag : null;
  }
  public String getText() { return getEventType() == TEXT ? (String) cur()[2] : null; }
  public char[] getTextCharacters(int[] holder) {
    String s = getText();
    if (s == null) { holder[0] = -1; holder[1] = -1; return null; }
    holder[0] = 0; holder[1] = s.length();
    return s.toCharArray();
  }
  public boolean isWhitespace() { String s = getText(); return s == null || s.trim().isEmpty(); }
  public boolean isEmptyElementTag() { XmlNode n = node(); return n != null && n.children().isEmpty() && n.text() == null; }
  public int getDepth() { return getEventType() == END_TAG ? stack.size() : stack.size(); }
  public String getNamespace() { return ""; }
  public String getPrefix() { return null; }
  public int getLineNumber() { return -1; }
  public int getColumnNumber() { return -1; }
  public void setFeature(String name, boolean state) {}
  public boolean getFeature(String name) { return FEATURE_PROCESS_NAMESPACES.equals(name); }
  public void setProperty(String name, Object value) {}
  public Object getProperty(String name) { return null; }
  public void setInput(Reader in) throws XmlPullParserException { throw new XmlPullParserException("setInput() not supported"); }
  public void setInput(InputStream in, String enc) throws XmlPullParserException { throw new XmlPullParserException("setInput() not supported"); }
  public String getInputEncoding() { return "utf-8"; }
  public void defineEntityReplacementText(String entityName, String replacementText) {}
  public int getNamespaceCount(int depth) { return 0; }
  public String getNamespacePrefix(int pos) { return null; }
  public String getNamespaceUri(int pos) { return null; }
  public String getNamespace(String prefix) { return "android".equals(prefix) ? ANDROID : null; }
  public boolean isAttributeDefault(int index) { return false; }
  public String getAttributeType(int index) { return "CDATA"; }
  public String getAttributePrefix(int index) { String ns = raw(index * 3); return "a".equals(ns) ? "android" : "x".equals(ns) ? "app" : null; }
  public String getAttributeNamespace(int index) { String ns = raw(index * 3); return "a".equals(ns) ? ANDROID : "x".equals(ns) ? AUTO : ""; }
  private String raw(int i) { XmlNode n = node(); return n == null || i >= n.attrs.length ? null : n.attrs[i]; }
  public void close() {}

  // ---------------------------------------------------------------- AttributeSet (current START_TAG)
  public int getAttributeCount() { XmlNode n = node(); return n == null ? -1 : n.attrs.length / 3; }
  public String getAttributeName(int i) { return raw(i * 3 + 1); }
  public String getAttributeValue(int i) { return raw(i * 3 + 2); }
  public String getAttributeValue(String namespace, String name) { Attrs a = at(); return a == null ? null : a.getAttributeValue(namespace, name); }
  public String getPositionDescription() { return "Binary XML file line #-1 " + (node() != null ? "<" + getName() + ">" : TYPES[getEventType()]); }
  public int getAttributeNameResource(int i) { return 0; }
  public int getAttributeListValue(String ns, String a, String[] o, int d) { Attrs x = at(); return x == null ? d : x.getAttributeListValue(ns, a, o, d); }
  public boolean getAttributeBooleanValue(String ns, String a, boolean d) { Attrs x = at(); return x == null ? d : x.getAttributeBooleanValue(ns, a, d); }
  public int getAttributeResourceValue(String ns, String a, int d) { Attrs x = at(); return x == null ? d : x.getAttributeResourceValue(ns, a, d); }
  public int getAttributeIntValue(String ns, String a, int d) { Attrs x = at(); return x == null ? d : x.getAttributeIntValue(ns, a, d); }
  public int getAttributeUnsignedIntValue(String ns, String a, int d) { Attrs x = at(); return x == null ? d : x.getAttributeUnsignedIntValue(ns, a, d); }
  public float getAttributeFloatValue(String ns, String a, float d) { Attrs x = at(); return x == null ? d : x.getAttributeFloatValue(ns, a, d); }
  public int getAttributeListValue(int i, String[] o, int d) { Attrs x = at(); return x == null ? d : x.getAttributeListValue(i, o, d); }
  public boolean getAttributeBooleanValue(int i, boolean d) { Attrs x = at(); return x == null ? d : x.getAttributeBooleanValue(i, d); }
  public int getAttributeResourceValue(int i, int d) { Attrs x = at(); return x == null ? d : x.getAttributeResourceValue(i, d); }
  public int getAttributeIntValue(int i, int d) { Attrs x = at(); return x == null ? d : x.getAttributeIntValue(i, d); }
  public int getAttributeUnsignedIntValue(int i, int d) { Attrs x = at(); return x == null ? d : x.getAttributeUnsignedIntValue(i, d); }
  public float getAttributeFloatValue(int i, float d) { Attrs x = at(); return x == null ? d : x.getAttributeFloatValue(i, d); }
  public String getIdAttribute() { Attrs x = at(); return x == null ? null : x.getIdAttribute(); }
  public String getClassAttribute() { Attrs x = at(); return x == null ? null : x.getClassAttribute(); }
  public int getIdAttributeResourceValue(int d) { Attrs x = at(); return x == null ? d : x.getIdAttributeResourceValue(d); }
  public int getStyleAttribute() { Attrs x = at(); return x == null ? 0 : x.getStyleAttribute(); }
}
