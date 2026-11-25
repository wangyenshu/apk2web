package android.database.sqlite;

import java.util.Arrays;

/** A statement with its bound arguments; compiled (prepared) when it runs. */
public abstract class SQLiteProgram extends SQLiteClosable {
  final SQLiteDatabase mDatabase;
  final String mSql;
  Object[] mBindArgs;
  SQLiteProgram(SQLiteDatabase db, String sql, Object[] bindArgs) {
    mDatabase = db;
    mSql = sql.trim();
    int n = countParams(mSql);
    mBindArgs = new Object[Math.max(n, bindArgs == null ? 0 : bindArgs.length)];
    if (bindArgs != null) System.arraycopy(bindArgs, 0, mBindArgs, 0, bindArgs.length);
  }
  /** "?", "?NNN", ":name", "@name", "$name" outside quotes and comments */
  static int countParams(String sql) {
    int n = 0, max = 0;
    for (int i = 0; i < sql.length(); i++) {
      char c = sql.charAt(i);
      if (c == '\'' || c == '"' || c == '`') { char q = c; i++; while (i < sql.length() && sql.charAt(i) != q) i++; }
      else if (c == '[') { while (i < sql.length() && sql.charAt(i) != ']') i++; }
      else if (c == '-' && i + 1 < sql.length() && sql.charAt(i + 1) == '-') { while (i < sql.length() && sql.charAt(i) != '\n') i++; }
      else if (c == '/' && i + 1 < sql.length() && sql.charAt(i + 1) == '*') { i = sql.indexOf("*/", i + 2); if (i < 0) break; i++; }
      else if (c == '?') {
        int j = i + 1;
        while (j < sql.length() && Character.isDigit(sql.charAt(j))) j++;
        if (j > i + 1) { max = Math.max(max, Integer.parseInt(sql.substring(i + 1, j))); n = Math.max(n, max); i = j - 1; } else n++;
      } else if ((c == ':' || c == '@' || c == '$') && i + 1 < sql.length() && Character.isLetter(sql.charAt(i + 1))) n++;
    }
    return Math.max(n, max);
  }
  final SQLiteDatabase getDatabase() { return mDatabase; }
  final String getSql() { return mSql; }
  final Object[] getBindArgs() { return mBindArgs; }
  @Deprecated public final int getUniqueId() { return -1; }
  private void bind(int index, Object value) {
    if (index < 1 || index > mBindArgs.length) throw new IllegalArgumentException("Cannot bind argument at index " + index + " because the index is out of range.  The statement has " + mBindArgs.length + " parameters.");
    mBindArgs[index - 1] = value;
  }
  public void bindNull(int index) { bind(index, null); }
  public void bindLong(int index, long value) { bind(index, value); }
  public void bindDouble(int index, double value) { bind(index, value); }
  public void bindString(int index, String value) {
    if (value == null) throw new IllegalArgumentException("the bind value at index " + index + " is null");
    bind(index, value);
  }
  public void bindBlob(int index, byte[] value) {
    if (value == null) throw new IllegalArgumentException("the bind value at index " + index + " is null");
    bind(index, value);
  }
  public void clearBindings() { if (mBindArgs != null) Arrays.fill(mBindArgs, null); }
  public void bindAllArgsAsStrings(String[] bindArgs) {
    if (bindArgs != null) for (int i = bindArgs.length; i != 0; i--) bindString(i, bindArgs[i - 1]);
  }
  @Override protected void onAllReferencesReleased() { clearBindings(); }
}
