package a2w.xml.transform.dom;

import a2w.xml.dom.Node;

public class DOMSource implements a2w.xml.transform.Source {
  public static final String FEATURE = "http://javax.xml.transform.dom.DOMSource/feature";
  private Node node;
  private String systemId;
  public DOMSource() {}
  public DOMSource(Node n) { node = n; }
  public DOMSource(Node n, String systemId) { node = n; this.systemId = systemId; }
  public void setNode(Node n) { node = n; }
  public Node getNode() { return node; }
  @Override public void setSystemId(String s) { systemId = s; }
  @Override public String getSystemId() { return systemId; }
  @Override public boolean isEmpty() { return node == null; }
}
