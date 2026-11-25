package a2w.xml.sax.helpers;

import a2w.xml.sax.Locator;

public class LocatorImpl implements Locator {
  private String publicId, systemId;
  private int line, column;
  public LocatorImpl() {}
  public LocatorImpl(Locator l) { publicId = l.getPublicId(); systemId = l.getSystemId(); line = l.getLineNumber(); column = l.getColumnNumber(); }
  @Override public String getPublicId() { return publicId; }
  @Override public String getSystemId() { return systemId; }
  @Override public int getLineNumber() { return line; }
  @Override public int getColumnNumber() { return column; }
  public void setPublicId(String p) { publicId = p; }
  public void setSystemId(String s) { systemId = s; }
  public void setLineNumber(int l) { line = l; }
  public void setColumnNumber(int c) { column = c; }
}
