package android.system;

/** An errno from an android.system.Os call. */
public final class ErrnoException extends Exception {
  public final int errno;
  private final String functionName;
  public ErrnoException(String functionName, int errno) { this(functionName, errno, null); }
  public ErrnoException(String functionName, int errno, Throwable cause) { super(null, cause); this.functionName = functionName; this.errno = errno; }
  /** "open failed: ENOENT (No such file or directory)" */
  @Override public String getMessage() {
    String name = OsConstants.errnoName(errno);
    if (name == null) name = "errno " + errno;
    return functionName + " failed: " + name + " (" + OsConstants.strerror(errno) + ")";
  }
  public java.io.IOException rethrowAsIOException() throws java.io.IOException { java.io.IOException e = new java.io.IOException(getMessage()); e.initCause(this); throw e; }
  public a2w.jdk.net.SocketException rethrowAsSocketException() throws a2w.jdk.net.SocketException { a2w.jdk.net.SocketException e = new a2w.jdk.net.SocketException(getMessage()); e.initCause(this); throw e; }
}
