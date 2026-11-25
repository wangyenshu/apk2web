package a2w.xml.sax;

public interface EntityResolver {
  InputSource resolveEntity(String publicId, String systemId) throws SAXException, java.io.IOException;
}
