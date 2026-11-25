package a2w.xml.dom;

public interface Attr extends Node {
  String getName();
  boolean getSpecified();
  String getValue();
  void setValue(String value) throws DOMException;
  Element getOwnerElement();
  TypeInfo getSchemaTypeInfo();
  boolean isId();
}
