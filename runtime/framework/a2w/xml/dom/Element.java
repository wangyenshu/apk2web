package a2w.xml.dom;

public interface Element extends Node {
  String getTagName();
  String getAttribute(String name);
  void setAttribute(String name, String value) throws DOMException;
  void removeAttribute(String name) throws DOMException;
  Attr getAttributeNode(String name);
  Attr setAttributeNode(Attr newAttr) throws DOMException;
  Attr removeAttributeNode(Attr oldAttr) throws DOMException;
  NodeList getElementsByTagName(String name);
  String getAttributeNS(String namespaceURI, String localName) throws DOMException;
  void setAttributeNS(String namespaceURI, String qualifiedName, String value) throws DOMException;
  void removeAttributeNS(String namespaceURI, String localName) throws DOMException;
  Attr getAttributeNodeNS(String namespaceURI, String localName) throws DOMException;
  Attr setAttributeNodeNS(Attr newAttr) throws DOMException;
  NodeList getElementsByTagNameNS(String namespaceURI, String localName) throws DOMException;
  boolean hasAttribute(String name);
  boolean hasAttributeNS(String namespaceURI, String localName) throws DOMException;
  TypeInfo getSchemaTypeInfo();
  void setIdAttribute(String name, boolean isId) throws DOMException;
  void setIdAttributeNS(String namespaceURI, String localName, boolean isId) throws DOMException;
  void setIdAttributeNode(Attr idAttr, boolean isId) throws DOMException;
}
