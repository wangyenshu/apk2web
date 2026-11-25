package a2w.xml.sax.helpers;

import a2w.xml.sax.SAXException;
import a2w.xml.sax.XMLReader;

public final class XMLReaderFactory {
  private XMLReaderFactory() {}
  public static XMLReader createXMLReader() throws SAXException { return new a2w.xml.PullReader(); }
  public static XMLReader createXMLReader(String className) throws SAXException { return new a2w.xml.PullReader(); }
}
