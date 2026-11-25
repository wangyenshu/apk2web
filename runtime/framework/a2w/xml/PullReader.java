package a2w.xml;

import a2w.xml.sax.*;
import a2w.xml.sax.ext.LexicalHandler;
import a2w.xml.sax.helpers.AttributesImpl;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/** A SAX XMLReader on the platform pull parser (kXML), as Android's own javax.xml.parsers SAX parser. */
public final class PullReader implements XMLReader, Locator {
  static final String NAMESPACES = "http://xml.org/sax/features/namespaces", PREFIXES = "http://xml.org/sax/features/namespace-prefixes",
      LEXICAL = "http://xml.org/sax/properties/lexical-handler", LEXICAL2 = "http://xml.org/sax/handlers/LexicalHandler";
  boolean namespaces = true, prefixes;
  ContentHandler content;
  ErrorHandler errors;
  EntityResolver resolver;
  DTDHandler dtd;
  LexicalHandler lexical;
  XmlPullParser p;
  String publicId, systemId;

  @Override public boolean getFeature(String name) throws SAXNotRecognizedException {
    if (NAMESPACES.equals(name)) return namespaces;
    if (PREFIXES.equals(name)) return prefixes;
    if (name != null && name.startsWith("http://xml.org/sax/features/")) return false;
    throw new SAXNotRecognizedException(name);
  }
  @Override public void setFeature(String name, boolean value) throws SAXNotRecognizedException, SAXNotSupportedException {
    if (NAMESPACES.equals(name)) { namespaces = value; return; }
    if (PREFIXES.equals(name)) { prefixes = value; return; }
    if (name != null && name.startsWith("http://xml.org/sax/features/")) {
      if (value && name.endsWith("/validation")) throw new SAXNotSupportedException("validation is not supported");
      return;
    }
    throw new SAXNotRecognizedException(name);
  }
  @Override public Object getProperty(String name) throws SAXNotRecognizedException {
    if (LEXICAL.equals(name) || LEXICAL2.equals(name)) return lexical;
    throw new SAXNotRecognizedException(name);
  }
  @Override public void setProperty(String name, Object value) throws SAXNotRecognizedException, SAXNotSupportedException {
    if (LEXICAL.equals(name) || LEXICAL2.equals(name)) {
      if (value != null && !(value instanceof LexicalHandler)) throw new SAXNotSupportedException("not a LexicalHandler");
      lexical = (LexicalHandler) value;
      return;
    }
    throw new SAXNotRecognizedException(name);
  }
  @Override public void setEntityResolver(EntityResolver r) { resolver = r; }
  @Override public EntityResolver getEntityResolver() { return resolver; }
  @Override public void setDTDHandler(DTDHandler h) { dtd = h; }
  @Override public DTDHandler getDTDHandler() { return dtd; }
  @Override public void setContentHandler(ContentHandler h) { content = h; }
  @Override public ContentHandler getContentHandler() { return content; }
  @Override public void setErrorHandler(ErrorHandler h) { errors = h; }
  @Override public ErrorHandler getErrorHandler() { return errors; }
  public void setLexicalHandler(LexicalHandler h) { lexical = h; }

  @Override public String getPublicId() { return publicId; }
  @Override public String getSystemId() { return systemId; }
  @Override public int getLineNumber() { return p == null ? -1 : p.getLineNumber(); }
  @Override public int getColumnNumber() { return p == null ? -1 : p.getColumnNumber(); }

  @Override public void parse(String systemId) throws IOException, SAXException { parse(new InputSource(systemId)); }

  /** opens what an InputSource names: its character stream, byte stream, or system id (a file path or URL) */
  static void setInput(XmlPullParser p, InputSource in) throws IOException, XmlPullParserException {
    Reader r = in.getCharacterStream();
    if (r != null) { p.setInput(r); return; }
    InputStream s = in.getByteStream();
    if (s == null) s = open(in.getSystemId());
    p.setInput(s, in.getEncoding());
  }
  static InputStream open(String id) throws IOException {
    if (id == null) throw new IOException("InputSource has no stream and no system id");
    if (id.startsWith("file:")) { String path = id.substring(5); while (path.startsWith("//")) path = path.substring(1); return new java.io.FileInputStream(java.net.URLDecoder.decode(path, "UTF-8")); }
    if (id.indexOf(':') < 0 || id.startsWith("/")) return new java.io.FileInputStream(id);
    return new java.net.URL(id).openStream();
  }

  @Override public void parse(InputSource in) throws IOException, SAXException {
    p = android.util.Xml.newPullParser();
    publicId = in.getPublicId();
    systemId = in.getSystemId();
    ContentHandler h = content != null ? content : new a2w.xml.sax.helpers.DefaultHandler();
    try {
      p.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, namespaces);
      setInput(p, in);
      h.setDocumentLocator(this);
      h.startDocument();
      int[] nsDepth = new int[64];
      for (int t = p.getEventType(); t != XmlPullParser.END_DOCUMENT; t = p.nextToken()) {
        switch (t) {
          case XmlPullParser.START_TAG: {
            AttributesImpl atts = new AttributesImpl();
            int depth = p.getDepth();
            if (namespaces) {
              int from = p.getNamespaceCount(depth - 1), to = p.getNamespaceCount(depth);
              if (depth >= nsDepth.length) nsDepth = java.util.Arrays.copyOf(nsDepth, depth * 2);
              nsDepth[depth] = to - from;
              for (int i = from; i < to; i++) {
                String pre = p.getNamespacePrefix(i), uri = p.getNamespaceUri(i);
                h.startPrefixMapping(pre == null ? "" : pre, uri == null ? "" : uri);
                if (prefixes) atts.addAttribute("", "", pre == null ? "xmlns" : "xmlns:" + pre, "CDATA", uri == null ? "" : uri);
              }
            }
            for (int i = 0; i < p.getAttributeCount(); i++) {
              String name = p.getAttributeName(i);
              if (namespaces) {
                String pre = p.getAttributePrefix(i), uri = p.getAttributeNamespace(i);
                atts.addAttribute(uri == null ? "" : uri, name, pre == null || pre.isEmpty() ? name : pre + ":" + name, "CDATA", p.getAttributeValue(i));
              } else atts.addAttribute("", name, name, "CDATA", p.getAttributeValue(i));
            }
            String name = p.getName();
            if (namespaces) {
              String pre = p.getPrefix(), uri = p.getNamespace();
              h.startElement(uri == null ? "" : uri, name, pre == null || pre.isEmpty() ? name : pre + ":" + name, atts);
            } else h.startElement("", name, name, atts);
            if (p.isEmptyElementTag()) { /* kXML reports the END_TAG next */ }
            break;
          }
          case XmlPullParser.END_TAG: {
            String name = p.getName();
            if (namespaces) {
              String pre = p.getPrefix(), uri = p.getNamespace();
              h.endElement(uri == null ? "" : uri, name, pre == null || pre.isEmpty() ? name : pre + ":" + name);
              int depth = p.getDepth();
              int from = p.getNamespaceCount(depth - 1);
              for (int i = from + (depth < nsDepth.length ? nsDepth[depth] : 0) - 1; i >= from; i--) {
                String pre2 = p.getNamespacePrefix(i);
                h.endPrefixMapping(pre2 == null ? "" : pre2);
              }
            } else h.endElement("", name, name);
            break;
          }
          case XmlPullParser.TEXT: case XmlPullParser.ENTITY_REF: {
            String s = p.getText();
            if (s != null && !s.isEmpty()) h.characters(s.toCharArray(), 0, s.length());
            break;
          }
          case XmlPullParser.IGNORABLE_WHITESPACE: {
            String s = p.getText();
            // outside the root element whitespace is not content
            if (s != null && !s.isEmpty() && p.getDepth() > 0) h.characters(s.toCharArray(), 0, s.length());
            break;
          }
          case XmlPullParser.CDSECT: {
            String s = p.getText();
            if (lexical != null) lexical.startCDATA();
            if (s != null && !s.isEmpty()) h.characters(s.toCharArray(), 0, s.length());
            if (lexical != null) lexical.endCDATA();
            break;
          }
          case XmlPullParser.COMMENT: {
            String s = p.getText();
            if (lexical != null && s != null) lexical.comment(s.toCharArray(), 0, s.length());
            break;
          }
          case XmlPullParser.PROCESSING_INSTRUCTION: {
            String s = p.getText();
            if (s == null) break;
            int sp = 0;
            while (sp < s.length() && !Character.isWhitespace(s.charAt(sp))) sp++;
            String target = s.substring(0, sp);
            if (!target.equalsIgnoreCase("xml")) h.processingInstruction(target, s.substring(sp).trim());
            break;
          }
          case XmlPullParser.DOCDECL: {
            if (lexical != null) {
              String s = p.getText() == null ? "" : p.getText().trim();
              int sp = 0;
              while (sp < s.length() && !Character.isWhitespace(s.charAt(sp)) && s.charAt(sp) != '[') sp++;
              lexical.startDTD(s.substring(0, sp), null, null);
              lexical.endDTD();
            }
            break;
          }
          default:
        }
      }
      h.endDocument();
    } catch (XmlPullParserException e) {
      SAXParseException x = new SAXParseException(e.getMessage(), publicId, systemId, e.getLineNumber(), e.getColumnNumber(), e);
      if (errors != null) errors.fatalError(x);
      throw x;
    } catch (RuntimeException e) {
      SAXParseException x = new SAXParseException(String.valueOf(e), this, e);
      if (errors != null) errors.fatalError(x);
      throw x;
    }
  }
}
