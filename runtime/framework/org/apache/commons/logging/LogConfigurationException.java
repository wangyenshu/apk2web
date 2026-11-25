package org.apache.commons.logging;

public class LogConfigurationException extends RuntimeException {
  public LogConfigurationException() {}
  public LogConfigurationException(String message) { super(message); }
  public LogConfigurationException(Throwable cause) { super(cause); }
  public LogConfigurationException(String message, Throwable cause) { super(message, cause); }
}
