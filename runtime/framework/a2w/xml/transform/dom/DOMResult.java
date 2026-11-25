package a2w.xml.transform.dom;

import a2w.xml.dom.Node;

public class DOMResult implements a2w.xml.transform.Result {
  public static final String FEATURE = "http://javax.xml.transform.dom.DOMResult/feature";
  private Node node, nextSibling;
  private String systemId;
  public DOMResult() {}
  public DOMResult(Node n) { node = n; }
  public DOMResult(Node n, String systemId) { node = n; this.systemId = systemId; }
  public DOMResult(Node n, Node nextSibling) { node = n; this.nextSibling = nextSibling; }
  public DOMResult(Node n, Node nextSibling, String systemId) { node = n; this.nextSibling = nextSibling; this.systemId = systemId; }
  public void setNode(Node n) { node = n; }
  public Node getNode() { return node; }
  public void setNextSibling(Node n) { nextSibling = n; }
  public Node getNextSibling() { return nextSibling; }
  @Override public void setSystemId(String s) { systemId = s; }
  @Override public String getSystemId() { return systemId; }
}
