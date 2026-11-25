package a2w.xml.sax;

public class SAXException extends Exception {
  private final Exception exception;
  public SAXException() { exception = null; }
  public SAXException(String message) { super(message); exception = null; }
  public SAXException(Exception e) { super(e == null ? null : e.toString()); exception = e; }
  public SAXException(String message, Exception e) { super(message); exception = e; }
  @Override public String getMessage() { String m = super.getMessage(); return m == null && exception != null ? exception.getMessage() : m; }
  public Exception getException() { return exception; }
  @Override public Throwable getCause() { return exception; }
  @Override public String toString() { return exception != null ? super.toString() + "\n" + exception : super.toString(); }
}
