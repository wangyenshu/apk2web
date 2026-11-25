package android.database;
public class SQLException extends RuntimeException {
  public SQLException() {}
  public SQLException(String m) { super(m); }
  public SQLException(String m, Throwable cause) { super(m, cause); }
}
