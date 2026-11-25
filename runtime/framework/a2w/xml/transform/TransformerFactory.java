package a2w.xml.transform;

/** javax.xml.transform.TransformerFactory: identity transforms only (serializing or copying a DOM); XSLT is not available. */
public abstract class TransformerFactory {
  protected TransformerFactory() {}
  public static TransformerFactory newInstance() { return new Impl(); }
  public static TransformerFactory newInstance(String name, ClassLoader loader) { return new Impl(); }
  public static TransformerFactory newDefaultInstance() { return new Impl(); }
  public abstract Transformer newTransformer(Source source) throws TransformerConfigurationException;
  public abstract Transformer newTransformer() throws TransformerConfigurationException;
  public abstract Templates newTemplates(Source source) throws TransformerConfigurationException;
  public abstract Source getAssociatedStylesheet(Source source, String media, String title, String charset) throws TransformerConfigurationException;
  public abstract void setURIResolver(URIResolver resolver);
  public abstract URIResolver getURIResolver();
  public abstract void setFeature(String name, boolean value) throws TransformerConfigurationException;
  public abstract boolean getFeature(String name);
  public abstract void setAttribute(String name, Object value);
  public abstract Object getAttribute(String name);
  public abstract void setErrorListener(ErrorListener listener);
  public abstract ErrorListener getErrorListener();

  static final class Impl extends TransformerFactory {
    URIResolver resolver;
    ErrorListener listener;
    final java.util.HashMap<String, Object> attrs = new java.util.HashMap<>();
    @Override public Transformer newTransformer(Source source) throws TransformerConfigurationException {
      if (source == null) return newTransformer();
      throw new TransformerConfigurationException("XSLT stylesheets are not supported");
    }
    @Override public Transformer newTransformer() { return new a2w.xml.Identity(); }
    @Override public Templates newTemplates(Source source) throws TransformerConfigurationException { throw new TransformerConfigurationException("XSLT stylesheets are not supported"); }
    @Override public Source getAssociatedStylesheet(Source s, String m, String t, String c) { return null; }
    @Override public void setURIResolver(URIResolver r) { resolver = r; }
    @Override public URIResolver getURIResolver() { return resolver; }
    @Override public void setFeature(String name, boolean value) {}
    @Override public boolean getFeature(String name) {
      return name != null && (name.endsWith("DOMSource/feature") || name.endsWith("DOMResult/feature") || name.endsWith("StreamSource/feature") || name.endsWith("StreamResult/feature"));
    }
    @Override public void setAttribute(String name, Object value) { attrs.put(name, value); }
    @Override public Object getAttribute(String name) { return attrs.get(name); }
    @Override public void setErrorListener(ErrorListener l) { listener = l; }
    @Override public ErrorListener getErrorListener() { return listener; }
  }
}
