package a2w.xml.parsers;

import java.util.HashMap;

public abstract class SAXParserFactory {
  private boolean validating, namespaceAware, xinclude;
  final HashMap<String, Boolean> features = new HashMap<>();
  protected SAXParserFactory() {}
  public static SAXParserFactory newInstance() { return new Impl(); }
  public static SAXParserFactory newInstance(String factoryClassName, ClassLoader classLoader) { return new Impl(); }
  public static SAXParserFactory newDefaultInstance() { return new Impl(); }
  public static SAXParserFactory newNSInstance() { Impl f = new Impl(); f.setNamespaceAware(true); return f; }
  public static SAXParserFactory newDefaultNSInstance() { return newNSInstance(); }
  public abstract SAXParser newSAXParser() throws ParserConfigurationException, a2w.xml.sax.SAXException;
  public void setNamespaceAware(boolean b) { namespaceAware = b; }
  public void setValidating(boolean b) { validating = b; }
  public boolean isNamespaceAware() { return namespaceAware; }
  public boolean isValidating() { return validating; }
  public void setFeature(String name, boolean value) throws ParserConfigurationException, a2w.xml.sax.SAXNotRecognizedException, a2w.xml.sax.SAXNotSupportedException {
    if (name == null) throw new NullPointerException();
    if (name.equals("http://xml.org/sax/features/namespaces")) { namespaceAware = value; return; }
    if (name.equals("http://xml.org/sax/features/validation")) { validating = value; return; }
    features.put(name, value);
  }
  public boolean getFeature(String name) throws ParserConfigurationException, a2w.xml.sax.SAXNotRecognizedException, a2w.xml.sax.SAXNotSupportedException {
    if (name.equals("http://xml.org/sax/features/namespaces")) return namespaceAware;
    if (name.equals("http://xml.org/sax/features/validation")) return validating;
    Boolean b = features.get(name);
    return b != null && b;
  }
  public a2w.xml.validation.Schema getSchema() { return schema; }
  private a2w.xml.validation.Schema schema;
  public void setSchema(a2w.xml.validation.Schema s) { schema = s; }
  public void setXIncludeAware(boolean state) { xinclude = state; }
  public boolean isXIncludeAware() { return xinclude; }

  static final class Impl extends SAXParserFactory {
    @Override public SAXParser newSAXParser() throws ParserConfigurationException, a2w.xml.sax.SAXException {
      if (isValidating()) throw new ParserConfigurationException("No validating SAXParser implementation available");
      a2w.xml.PullReader r = new a2w.xml.PullReader();
      r.setFeature("http://xml.org/sax/features/namespaces", isNamespaceAware());
      for (java.util.Map.Entry<String, Boolean> e : features.entrySet()) r.setFeature(e.getKey(), e.getValue());
      return new SAXParser.Impl(r);
    }
  }
}
