package a2w.xml.transform;

public class TransformerException extends Exception {
  private SourceLocator locator;
  private Throwable containedException;
  public TransformerException(String message) { super(message); }
  public TransformerException(Throwable e) { super(e == null ? null : e.toString(), e); containedException = e; }
  public TransformerException(String message, Throwable e) { super(message, e); containedException = e; }
  public TransformerException(String message, SourceLocator locator) { super(message); this.locator = locator; }
  public TransformerException(String message, SourceLocator locator, Throwable e) { super(message, e); this.locator = locator; containedException = e; }
  public SourceLocator getLocator() { return locator; }
  public void setLocator(SourceLocator l) { locator = l; }
  public Throwable getException() { return containedException; }
  public String getMessageAndLocation() { return getMessage(); }
  public String getLocationAsString() { return locator == null ? null : "line " + locator.getLineNumber(); }
}
