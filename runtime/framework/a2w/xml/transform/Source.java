package a2w.xml.transform;

public interface Source {
  void setSystemId(String systemId);
  String getSystemId();
  default boolean isEmpty() { return false; }
}
