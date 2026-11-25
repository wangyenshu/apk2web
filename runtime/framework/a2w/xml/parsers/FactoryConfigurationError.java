package a2w.xml.parsers;

public class FactoryConfigurationError extends Error {
  private final Exception exception;
  public FactoryConfigurationError() { exception = null; }
  public FactoryConfigurationError(String msg) { super(msg); exception = null; }
  public FactoryConfigurationError(Exception e) { super(e.toString()); exception = e; }
  public FactoryConfigurationError(Exception e, String msg) { super(msg); exception = e; }
  public Exception getException() { return exception; }
}
