package android.database.sqlite;
public class SQLiteException extends android.database.SQLException {
  public SQLiteException() {}
  public SQLiteException(String m) { super(m); }
  public SQLiteException(String m, Throwable cause) { super(m, cause); }
}
