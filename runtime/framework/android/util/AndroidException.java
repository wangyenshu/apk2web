package android.util;
public class AndroidException extends Exception {
  public AndroidException() {}
  public AndroidException(String m) { super(m); }
  public AndroidException(String m, Throwable c) { super(m, c); }
  public AndroidException(Exception c) { super(c); }
}
