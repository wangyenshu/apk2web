package a2w.xml.dom;

public interface Entity extends Node {
  String getPublicId();
  String getSystemId();
  String getNotationName();
  String getInputEncoding();
  String getXmlEncoding();
  String getXmlVersion();
}
