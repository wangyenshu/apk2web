package a2w.xml.dom;

public interface Document extends Node {
  DocumentType getDoctype();
  DOMImplementation getImplementation();
  Element getDocumentElement();
  Element createElement(String tagName) throws DOMException;
  DocumentFragment createDocumentFragment();
  Text createTextNode(String data);
  Comment createComment(String data);
  CDATASection createCDATASection(String data) throws DOMException;
  ProcessingInstruction createProcessingInstruction(String target, String data) throws DOMException;
  Attr createAttribute(String name) throws DOMException;
  EntityReference createEntityReference(String name) throws DOMException;
  NodeList getElementsByTagName(String tagname);
  Node importNode(Node importedNode, boolean deep) throws DOMException;
  Element createElementNS(String namespaceURI, String qualifiedName) throws DOMException;
  Attr createAttributeNS(String namespaceURI, String qualifiedName) throws DOMException;
  NodeList getElementsByTagNameNS(String namespaceURI, String localName);
  Element getElementById(String elementId);
  String getInputEncoding();
  String getXmlEncoding();
  boolean getXmlStandalone();
  void setXmlStandalone(boolean xmlStandalone) throws DOMException;
  String getXmlVersion();
  void setXmlVersion(String xmlVersion) throws DOMException;
  boolean getStrictErrorChecking();
  void setStrictErrorChecking(boolean strictErrorChecking);
  String getDocumentURI();
  void setDocumentURI(String documentURI);
  Node adoptNode(Node source) throws DOMException;
  DOMConfiguration getDomConfig();
  void normalizeDocument();
  Node renameNode(Node n, String namespaceURI, String qualifiedName) throws DOMException;
}
