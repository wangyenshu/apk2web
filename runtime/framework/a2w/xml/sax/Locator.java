package a2w.xml.sax;

public interface Locator {
  String getPublicId();
  String getSystemId();
  int getLineNumber();
  int getColumnNumber();
}
