package a2w.xml.transform;

public interface Result {
  String PI_DISABLE_OUTPUT_ESCAPING = "javax.xml.transform.disable-output-escaping", PI_ENABLE_OUTPUT_ESCAPING = "javax.xml.transform.enable-output-escaping";
  void setSystemId(String systemId);
  String getSystemId();
}
