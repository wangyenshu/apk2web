package a2w.xml.parsers;

import a2w.xml.sax.InputSource;
import a2w.xml.sax.SAXException;
import a2w.xml.sax.XMLReader;
import a2w.xml.sax.helpers.DefaultHandler;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public abstract class SAXParser {
  protected SAXParser() {}
  public void reset() {}
  public void parse(InputStream is, DefaultHandler dh) throws SAXException, IOException {
    if (is == null) throw new IllegalArgumentException("InputStream cannot be null");
    parse(new InputSource(is), dh);
  }
  public void parse(InputStream is, DefaultHandler dh, String systemId) throws SAXException, IOException {
    if (is == null) throw new IllegalArgumentException("InputStream cannot be null");
    InputSource in = new InputSource(is);
    in.setSystemId(systemId);
    parse(in, dh);
  }
  public void parse(String uri, DefaultHandler dh) throws SAXException, IOException {
    if (uri == null) throw new IllegalArgumentException("uri cannot be null");
    parse(new InputSource(uri), dh);
  }
  public void parse(File f, DefaultHandler dh) throws SAXException, IOException {
    if (f == null) throw new IllegalArgumentException("File cannot be null");
    try (InputStream in = new java.io.FileInputStream(f)) {
      InputSource s = new InputSource(in);
      s.setSystemId("file:" + f.getAbsolutePath());
      parse(s, dh);
    }
  }
  public void parse(InputSource is, DefaultHandler dh) throws SAXException, IOException {
    if (is == null) throw new IllegalArgumentException("InputSource cannot be null");
    XMLReader r = getXMLReader();
    if (dh != null) {
      r.setContentHandler(dh);
      r.setEntityResolver(dh);
      r.setErrorHandler(dh);
      r.setDTDHandler(dh);
      if (dh instanceof a2w.xml.sax.ext.LexicalHandler) r.setProperty("http://xml.org/sax/properties/lexical-handler", dh);
    }
    r.parse(is);
  }
  public abstract XMLReader getXMLReader() throws SAXException;
  public abstract boolean isNamespaceAware();
  public abstract boolean isValidating();
  public abstract void setProperty(String name, Object value) throws a2w.xml.sax.SAXNotRecognizedException, a2w.xml.sax.SAXNotSupportedException;
  public abstract Object getProperty(String name) throws a2w.xml.sax.SAXNotRecognizedException, a2w.xml.sax.SAXNotSupportedException;
  public a2w.xml.validation.Schema getSchema() { return null; }
  public boolean isXIncludeAware() { return false; }

  static final class Impl extends SAXParser {
    final a2w.xml.PullReader reader;
    Impl(a2w.xml.PullReader r) { reader = r; }
    @Override public XMLReader getXMLReader() { return reader; }
    @Override public boolean isNamespaceAware() { try { return reader.getFeature("http://xml.org/sax/features/namespaces"); } catch (SAXException e) { return false; } }
    @Override public boolean isValidating() { return false; }
    @Override public void setProperty(String name, Object value) throws a2w.xml.sax.SAXNotRecognizedException, a2w.xml.sax.SAXNotSupportedException { reader.setProperty(name, value); }
    @Override public Object getProperty(String name) throws a2w.xml.sax.SAXNotRecognizedException, a2w.xml.sax.SAXNotSupportedException { return reader.getProperty(name); }
  }
}
