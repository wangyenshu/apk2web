package a2w.xml.parsers;

import a2w.xml.dom.DOMImplementation;
import a2w.xml.dom.Document;
import a2w.xml.sax.EntityResolver;
import a2w.xml.sax.ErrorHandler;
import a2w.xml.sax.InputSource;
import a2w.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public abstract class DocumentBuilder {
  protected DocumentBuilder() {}
  public void reset() {}
  public Document parse(InputStream is) throws SAXException, IOException {
    if (is == null) throw new IllegalArgumentException("InputStream cannot be null");
    return parse(new InputSource(is));
  }
  public Document parse(InputStream is, String systemId) throws SAXException, IOException {
    if (is == null) throw new IllegalArgumentException("InputStream cannot be null");
    InputSource in = new InputSource(is);
    in.setSystemId(systemId);
    return parse(in);
  }
  public Document parse(String uri) throws SAXException, IOException {
    if (uri == null) throw new IllegalArgumentException("URI cannot be null");
    return parse(new InputSource(uri));
  }
  public Document parse(File f) throws SAXException, IOException {
    if (f == null) throw new IllegalArgumentException("File cannot be null");
    try (InputStream in = new java.io.FileInputStream(f)) {
      InputSource s = new InputSource(in);
      s.setSystemId("file:" + f.getAbsolutePath());
      return parse(s);
    }
  }
  public abstract Document parse(InputSource is) throws SAXException, IOException;
  public abstract boolean isNamespaceAware();
  public abstract boolean isValidating();
  public abstract void setEntityResolver(EntityResolver er);
  public abstract void setErrorHandler(ErrorHandler eh);
  public abstract Document newDocument();
  public abstract DOMImplementation getDOMImplementation();
  public a2w.xml.validation.Schema getSchema() { return null; }
  public boolean isXIncludeAware() { return false; }

  static final class Impl extends DocumentBuilder {
    final boolean ns, ignoreComments, coalescing, ignoreWhitespace;
    EntityResolver resolver;
    ErrorHandler errors;
    Impl(boolean ns, boolean ignoreComments, boolean coalescing, boolean ignoreWhitespace) { this.ns = ns; this.ignoreComments = ignoreComments; this.coalescing = coalescing; this.ignoreWhitespace = ignoreWhitespace; }
    @Override public Document parse(InputSource is) throws SAXException, IOException {
      if (is == null) throw new IllegalArgumentException("InputSource cannot be null");
      a2w.xml.PullReader r = new a2w.xml.PullReader();
      r.setFeature("http://xml.org/sax/features/namespaces", ns);
      Object b = a2w.xml.Parsing.domBuilder(ns, ignoreComments, coalescing);
      r.setContentHandler((a2w.xml.sax.ContentHandler) b);
      r.setLexicalHandler((a2w.xml.sax.ext.LexicalHandler) b);
      r.setErrorHandler(errors);
      r.setEntityResolver(resolver);
      r.parse(is);
      Document d = a2w.xml.Parsing.document(b);
      if (is.getSystemId() != null) d.setDocumentURI(is.getSystemId());
      if (is.getEncoding() != null) a2w.xml.Parsing.inputEncoding(d, is.getEncoding());
      return d;
    }
    @Override public boolean isNamespaceAware() { return ns; }
    @Override public boolean isValidating() { return false; }
    @Override public void setEntityResolver(EntityResolver er) { resolver = er; }
    @Override public void setErrorHandler(ErrorHandler eh) { errors = eh; }
    @Override public Document newDocument() { return new a2w.xml.Dom.DocumentImpl(a2w.xml.Dom.Impl.INSTANCE); }
    @Override public DOMImplementation getDOMImplementation() { return a2w.xml.Dom.Impl.INSTANCE; }
  }
}
