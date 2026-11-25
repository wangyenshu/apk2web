package a2w.xml.sax.ext;

import a2w.xml.sax.InputSource;
import a2w.xml.sax.SAXException;

public class DefaultHandler2 extends a2w.xml.sax.helpers.DefaultHandler implements LexicalHandler, DeclHandler {
  public DefaultHandler2() {}
  @Override public void startCDATA() throws SAXException {}
  @Override public void endCDATA() throws SAXException {}
  @Override public void startDTD(String name, String publicId, String systemId) throws SAXException {}
  @Override public void endDTD() throws SAXException {}
  @Override public void startEntity(String name) throws SAXException {}
  @Override public void endEntity(String name) throws SAXException {}
  @Override public void comment(char[] ch, int start, int length) throws SAXException {}
  @Override public void attributeDecl(String e, String a, String t, String m, String v) throws SAXException {}
  @Override public void elementDecl(String name, String model) throws SAXException {}
  @Override public void externalEntityDecl(String name, String publicId, String systemId) throws SAXException {}
  @Override public void internalEntityDecl(String name, String value) throws SAXException {}
  public InputSource getExternalSubset(String name, String baseURI) throws SAXException, java.io.IOException { return null; }
  public InputSource resolveEntity(String name, String publicId, String baseURI, String systemId) throws SAXException, java.io.IOException { return null; }
  @Override public InputSource resolveEntity(String publicId, String systemId) throws SAXException, java.io.IOException { return resolveEntity(null, publicId, null, systemId); }
}
