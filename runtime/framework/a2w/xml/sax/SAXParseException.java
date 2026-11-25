package a2w.xml.sax;

public class SAXParseException extends SAXException {
  private final String publicId, systemId;
  private final int lineNumber, columnNumber;
  public SAXParseException(String message, Locator l) { this(message, l, null); }
  public SAXParseException(String message, Locator l, Exception e) {
    super(message, e);
    publicId = l == null ? null : l.getPublicId(); systemId = l == null ? null : l.getSystemId();
    lineNumber = l == null ? -1 : l.getLineNumber(); columnNumber = l == null ? -1 : l.getColumnNumber();
  }
  public SAXParseException(String message, String publicId, String systemId, int line, int column) { this(message, publicId, systemId, line, column, null); }
  public SAXParseException(String message, String publicId, String systemId, int line, int column, Exception e) {
    super(message, e);
    this.publicId = publicId; this.systemId = systemId; lineNumber = line; columnNumber = column;
  }
  public String getPublicId() { return publicId; }
  public String getSystemId() { return systemId; }
  public int getLineNumber() { return lineNumber; }
  public int getColumnNumber() { return columnNumber; }
  @Override public String toString() {
    StringBuilder b = new StringBuilder(getClass().getName());
    String m = getLocalizedMessage();
    if (publicId != null) b.append("publicId: ").append(publicId);
    if (systemId != null) b.append("; systemId: ").append(systemId);
    if (lineNumber != -1) b.append("; lineNumber: ").append(lineNumber);
    if (columnNumber != -1) b.append("; columnNumber: ").append(columnNumber);
    if (m != null) b.append("; ").append(m);
    return b.toString();
  }
}
