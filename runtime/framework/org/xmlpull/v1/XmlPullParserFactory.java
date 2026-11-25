package org.xmlpull.v1;

import java.util.ArrayList;
import java.util.HashMap;

/** As on Android: always the bundled kXML parser and serializer (no service lookup through class loaders). */
public class XmlPullParserFactory {
  public static final String PROPERTY_NAME = "org.xmlpull.v1.XmlPullParserFactory";
  protected ArrayList parserClasses = new ArrayList();
  protected ArrayList serializerClasses = new ArrayList();
  protected String classNamesLocation = "org.kxml2.io.kXmlParser,org.kxml2.io.KXmlSerializer";
  protected HashMap<String, Boolean> features = new HashMap<>();
  protected XmlPullParserFactory() {}
  public static XmlPullParserFactory newInstance() throws XmlPullParserException { return new XmlPullParserFactory(); }
  public static XmlPullParserFactory newInstance(String classNames, Class context) throws XmlPullParserException { return new XmlPullParserFactory(); }
  public void setFeature(String name, boolean state) throws XmlPullParserException { features.put(name, state); }
  public boolean getFeature(String name) { Boolean v = features.get(name); return v != null && v; }
  public void setNamespaceAware(boolean aware) { features.put(XmlPullParser.FEATURE_PROCESS_NAMESPACES, aware); }
  public boolean isNamespaceAware() { return getFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES); }
  public void setValidating(boolean v) { features.put(XmlPullParser.FEATURE_VALIDATION, v); }
  public boolean isValidating() { return getFeature(XmlPullParser.FEATURE_VALIDATION); }
  public XmlPullParser newPullParser() throws XmlPullParserException {
    XmlPullParser p = new org.kxml2.io.KXmlParser();
    for (java.util.Map.Entry<String, Boolean> e : features.entrySet()) if (e.getValue()) p.setFeature(e.getKey(), true);
    return p;
  }
  public XmlSerializer newSerializer() throws XmlPullParserException { return new org.kxml2.io.KXmlSerializer(); }
}
