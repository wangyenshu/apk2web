package android.util;
public class AndroidRuntimeException extends RuntimeException {
  public AndroidRuntimeException() {}
  public AndroidRuntimeException(String m) { super(m); }
  public AndroidRuntimeException(String m, Throwable c) { super(m, c); }
  public AndroidRuntimeException(Exception c) { super(c); }
}
