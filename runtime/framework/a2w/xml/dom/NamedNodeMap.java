package a2w.xml.dom;

public interface NamedNodeMap {
  Node getNamedItem(String name);
  Node setNamedItem(Node arg) throws DOMException;
  Node removeNamedItem(String name) throws DOMException;
  Node item(int index);
  int getLength();
  Node getNamedItemNS(String namespaceURI, String localName) throws DOMException;
  Node setNamedItemNS(Node arg) throws DOMException;
  Node removeNamedItemNS(String namespaceURI, String localName) throws DOMException;
}
