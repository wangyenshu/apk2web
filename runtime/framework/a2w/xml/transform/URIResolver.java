package a2w.xml.transform;

public interface URIResolver {
  Source resolve(String href, String base) throws TransformerException;
}
