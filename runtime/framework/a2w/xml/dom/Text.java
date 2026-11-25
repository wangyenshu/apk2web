package a2w.xml.dom;

public interface Text extends CharacterData {
  Text splitText(int offset) throws DOMException;
  boolean isElementContentWhitespace();
  String getWholeText();
  Text replaceWholeText(String content) throws DOMException;
}
