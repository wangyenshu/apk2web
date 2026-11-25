package a2w.xml.dom;

/** org.w3c.dom.Node (DOM Level 3 Core). */
public interface Node {
  short ELEMENT_NODE = 1, ATTRIBUTE_NODE = 2, TEXT_NODE = 3, CDATA_SECTION_NODE = 4, ENTITY_REFERENCE_NODE = 5, ENTITY_NODE = 6,
      PROCESSING_INSTRUCTION_NODE = 7, COMMENT_NODE = 8, DOCUMENT_NODE = 9, DOCUMENT_TYPE_NODE = 10, DOCUMENT_FRAGMENT_NODE = 11, NOTATION_NODE = 12;
  short DOCUMENT_POSITION_DISCONNECTED = 0x01, DOCUMENT_POSITION_PRECEDING = 0x02, DOCUMENT_POSITION_FOLLOWING = 0x04,
      DOCUMENT_POSITION_CONTAINS = 0x08, DOCUMENT_POSITION_CONTAINED_BY = 0x10, DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC = 0x20;
  String getNodeName();
  String getNodeValue() throws DOMException;
  void setNodeValue(String nodeValue) throws DOMException;
  short getNodeType();
  Node getParentNode();
  NodeList getChildNodes();
  Node getFirstChild();
  Node getLastChild();
  Node getPreviousSibling();
  Node getNextSibling();
  NamedNodeMap getAttributes();
  Document getOwnerDocument();
  Node insertBefore(Node newChild, Node refChild) throws DOMException;
  Node replaceChild(Node newChild, Node oldChild) throws DOMException;
  Node removeChild(Node oldChild) throws DOMException;
  Node appendChild(Node newChild) throws DOMException;
  boolean hasChildNodes();
  Node cloneNode(boolean deep);
  void normalize();
  boolean isSupported(String feature, String version);
  String getNamespaceURI();
  String getPrefix();
  void setPrefix(String prefix) throws DOMException;
  String getLocalName();
  boolean hasAttributes();
  String getBaseURI();
  short compareDocumentPosition(Node other) throws DOMException;
  String getTextContent() throws DOMException;
  void setTextContent(String textContent) throws DOMException;
  boolean isSameNode(Node other);
  String lookupPrefix(String namespaceURI);
  boolean isDefaultNamespace(String namespaceURI);
  String lookupNamespaceURI(String prefix);
  boolean isEqualNode(Node arg);
  Object getFeature(String feature, String version);
  Object setUserData(String key, Object data, UserDataHandler handler);
  Object getUserData(String key);
}
