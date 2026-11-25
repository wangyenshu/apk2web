package a2w.xml.transform;

public class TransformerFactoryConfigurationError extends Error {
  public TransformerFactoryConfigurationError() {}
  public TransformerFactoryConfigurationError(String msg) { super(msg); }
  public TransformerFactoryConfigurationError(Exception e) { super(e); }
  public TransformerFactoryConfigurationError(Exception e, String msg) { super(msg, e); }
  public Exception getException() { return getCause() instanceof Exception ? (Exception) getCause() : null; }
}
