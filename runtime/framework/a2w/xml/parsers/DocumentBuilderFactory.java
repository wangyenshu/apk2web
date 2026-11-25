package a2w.xml.parsers;

import java.util.HashMap;

/** javax.xml.parsers.DocumentBuilderFactory (Android's defaults: not namespace aware, not validating). */
public abstract class DocumentBuilderFactory {
  private boolean validating, namespaceAware, whitespace, expandEntityRef = true, ignoreComments, coalescing, xinclude;
  final HashMap<String, Object> attributes = new HashMap<>();
  final HashMap<String, Boolean> features = new HashMap<>();
  protected DocumentBuilderFactory() {}
  public static DocumentBuilderFactory newInstance() { return new Impl(); }
  public static DocumentBuilderFactory newInstance(String factoryClassName, ClassLoader classLoader) { return new Impl(); }
  public static DocumentBuilderFactory newDefaultInstance() { return new Impl(); }
  public static DocumentBuilderFactory newNSInstance() { Impl f = new Impl(); f.setNamespaceAware(true); return f; }
  public static DocumentBuilderFactory newDefaultNSInstance() { return newNSInstance(); }
  public abstract DocumentBuilder newDocumentBuilder() throws ParserConfigurationException;
  public void setNamespaceAware(boolean b) { namespaceAware = b; }
  public void setValidating(boolean b) { validating = b; }
  public void setIgnoringElementContentWhitespace(boolean b) { whitespace = b; }
  public void setExpandEntityReferences(boolean b) { expandEntityRef = b; }
  public void setIgnoringComments(boolean b) { ignoreComments = b; }
  public void setCoalescing(boolean b) { coalescing = b; }
  public boolean isNamespaceAware() { return namespaceAware; }
  public boolean isValidating() { return validating; }
  public boolean isIgnoringElementContentWhitespace() { return whitespace; }
  public boolean isExpandEntityReferences() { return expandEntityRef; }
  public boolean isIgnoringComments() { return ignoreComments; }
  public boolean isCoalescing() { return coalescing; }
  public void setAttribute(String name, Object value) { attributes.put(name, value); }
  public Object getAttribute(String name) { return attributes.get(name); }
  public void setFeature(String name, boolean value) throws ParserConfigurationException {
    if (name == null) throw new NullPointerException();
    if (name.equals("http://xml.org/sax/features/namespaces")) { namespaceAware = value; return; }
    if (name.equals("http://xml.org/sax/features/validation")) { validating = value; return; }
    features.put(name, value);
  }
  public boolean getFeature(String name) throws ParserConfigurationException {
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

  static final class Impl extends DocumentBuilderFactory {
    @Override public DocumentBuilder newDocumentBuilder() throws ParserConfigurationException {
      if (isValidating()) throw new ParserConfigurationException("No validating DocumentBuilder implementation available");
      return new DocumentBuilder.Impl(isNamespaceAware(), isIgnoringComments(), isCoalescing(), isIgnoringElementContentWhitespace());
    }
  }
}
