package android.util;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

public class Xml {
  public static final String FEATURE_RELAXED = "http://xmlpull.org/v1/doc/features.html#relaxed";
  public enum Encoding {
    US_ASCII("US-ASCII"), UTF_8("UTF-8"), UTF_16("UTF-16"), ISO_8859_1("ISO-8859-1");
    final String expatName;
    Encoding(String n) { expatName = n; }
  }
  public static XmlPullParser newPullParser() {
    try {
      org.kxml2.io.KXmlParser p = new org.kxml2.io.KXmlParser();
      try { p.setFeature(XmlPullParser.FEATURE_PROCESS_DOCDECL, true); } catch (XmlPullParserException e) { /* this kXML does not take it */ }
      p.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);
      return p;
    } catch (XmlPullParserException e) { throw new AssertionError(e); }
  }
  public static void parse(String xml, a2w.xml.sax.ContentHandler h) throws a2w.xml.sax.SAXException {
    try { parse(new java.io.StringReader(xml), h); } catch (java.io.IOException e) { throw new AssertionError(e); }
  }
  public static void parse(java.io.Reader in, a2w.xml.sax.ContentHandler h) throws java.io.IOException, a2w.xml.sax.SAXException {
    a2w.xml.PullReader r = new a2w.xml.PullReader();
    r.setContentHandler(h);
    r.parse(new a2w.xml.sax.InputSource(in));
  }
  public static void parse(java.io.InputStream in, Encoding enc, a2w.xml.sax.ContentHandler h) throws java.io.IOException, a2w.xml.sax.SAXException {
    a2w.xml.PullReader r = new a2w.xml.PullReader();
    r.setContentHandler(h);
    a2w.xml.sax.InputSource s = new a2w.xml.sax.InputSource(in);
    s.setEncoding(enc == null ? null : enc.expatName);
    r.parse(s);
  }
  public static XmlSerializer newSerializer() { return new org.kxml2.io.KXmlSerializer(); }
  public static Encoding findEncodingByName(String n) throws java.io.UnsupportedEncodingException {
    if (n == null) return Encoding.UTF_8;
    for (Encoding e : Encoding.values()) if (e.expatName.equalsIgnoreCase(n)) return e;
    throw new java.io.UnsupportedEncodingException(n);
  }
  public static AttributeSet asAttributeSet(XmlPullParser parser) {
    return parser instanceof AttributeSet ? (AttributeSet) parser : new a2w.PullAttrs(parser);
  }
}
