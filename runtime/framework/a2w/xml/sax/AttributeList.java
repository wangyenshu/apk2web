package a2w.xml.sax;

/** SAX1 attributes (deprecated) */
public interface AttributeList {
  int getLength();
  String getName(int i);
  String getType(int i);
  String getValue(int i);
  String getType(String name);
  String getValue(String name);
}
