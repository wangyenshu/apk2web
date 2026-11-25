package a2w;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import org.teavm.jso.typedarrays.Int8Array;

/** SQLite (sql.js in the page): the engine under android.database.sqlite. */
public final class Sql {
  private Sql() {}
  @JSBody(script = "return HW.sql.available();")
  public static native boolean available();
  @JSBody(params = {"p", "c"}, script = "return HW.sql.open(p, c);")
  public static native int open(String path, boolean create);
  @JSBody(params = {"h"}, script = "HW.sql.close(h);")
  public static native void close(int h);
  @JSBody(params = {"h", "d"}, script = "HW.sql.setTx(h, d);")
  public static native void setTx(int h, int depth);
  @JSBody(params = {"h", "s", "a", "rows"}, script = "return HW.sql.run(h, s, a, rows);")
  static native JSObject runJS(int h, String sql, JSObject args, boolean rows);
  @JSBody(params = {"r"}, script = "return r.error == null ? null : String(r.error);")
  static native String error(JSObject r);
  @JSBody(params = {"r"}, script = "return r.code | 0;")
  static native int code(JSObject r);
  @JSBody(params = {"r"}, script = "return r.changes | 0;")
  static native int changes(JSObject r);
  @JSBody(params = {"r"}, script = "return r.lastId;")
  static native double lastId(JSObject r);
  @JSBody(params = {"r"}, script = "return r.cols.length;")
  static native int ncols(JSObject r);
  @JSBody(params = {"r", "i"}, script = "return r.cols[i];")
  static native String col(JSObject r, int i);
  @JSBody(params = {"r"}, script = "return r.rows.length;")
  static native int nrows(JSObject r);
  @JSBody(params = {"r", "i", "j"}, script = "return HW.sql.type(r, i, j);")
  static native int type(JSObject r, int i, int j);
  @JSBody(params = {"r", "i", "j"}, script = "return HW.sql.long(r, i, j);")
  static native String longText(JSObject r, int i, int j);
  @JSBody(params = {"r", "i", "j"}, script = "return HW.sql.num(r, i, j);")
  static native double num(JSObject r, int i, int j);
  @JSBody(params = {"r", "i", "j"}, script = "return HW.sql.text(r, i, j);")
  static native String text(JSObject r, int i, int j);
  @JSBody(params = {"r", "i", "j"}, script = "return HW.sql.blob(r, i, j);")
  static native Int8Array blob(JSObject r, int i, int j);
  @JSBody(script = "return [];")
  static native JSObject newArgs();
  @JSBody(params = {"a"}, script = "a.push(null);")
  static native void pushNull(JSObject a);
  @JSBody(params = {"a", "s"}, script = "a.push(BigInt(s));")
  static native void pushLong(JSObject a, String s);
  @JSBody(params = {"a", "v"}, script = "a.push(v);")
  static native void pushDouble(JSObject a, double v);
  @JSBody(params = {"a", "v"}, script = "a.push(v);")
  static native void pushText(JSObject a, String v);
  @JSBody(params = {"a", "b"}, script = "a.push(new Uint8Array(b.buffer, b.byteOffset, b.byteLength).slice());")
  static native void pushBlob(JSObject a, Int8Array b);

  /** one statement's outcome: columns and rows (Long, Double, String, byte[] or null), changes, last rowid */
  public static final class Result {
    public String[] columns;
    public Object[][] rows;
    public int changes;
    public long lastInsertId;
  }

  public static JSObject args(Object[] bind) {
    JSObject a = newArgs();
    if (bind == null) return a;
    for (Object o : bind) {
      if (o == null) pushNull(a);
      else if (o instanceof Long || o instanceof Integer || o instanceof Short || o instanceof Byte) pushLong(a, String.valueOf(((Number) o).longValue()));
      else if (o instanceof Boolean) pushLong(a, (Boolean) o ? "1" : "0");
      else if (o instanceof Number) pushDouble(a, ((Number) o).doubleValue());
      else if (o instanceof byte[]) pushBlob(a, Host.js((byte[]) o));
      else pushText(a, o.toString());
    }
    return a;
  }

  /** runs one statement; throws the SQLiteException for its error */
  public static Result run(int h, String sql, Object[] bind, boolean rows) {
    JSObject r = runJS(h, sql, args(bind), rows);
    String err = error(r);
    if (err != null) throw exception(code(r), err, sql);
    Result out = new Result();
    int nc = ncols(r);
    out.columns = new String[nc];
    for (int i = 0; i < nc; i++) out.columns[i] = col(r, i);
    int nr = nrows(r);
    out.rows = new Object[nr][];
    for (int i = 0; i < nr; i++) {
      Object[] row = new Object[nc];
      for (int j = 0; j < nc; j++) {
        switch (type(r, i, j)) {
          case 1: row[j] = Long.parseLong(longText(r, i, j)); break;
          case 2: row[j] = num(r, i, j); break;
          case 3: row[j] = text(r, i, j); break;
          case 4: row[j] = Host.bytes(blob(r, i, j)); break;
          default: row[j] = null;
        }
      }
      out.rows[i] = row;
    }
    out.changes = changes(r);
    out.lastInsertId = (long) lastId(r);
    return out;
  }

  public static RuntimeException exception(int code, String msg, String sql) {
    String m = msg + " (code " + code + ")" + (sql != null ? ": , while compiling: " + sql : "");
    switch (code) {
      case 19: return new android.database.sqlite.SQLiteConstraintException(m);
      case 5: case 6: return new android.database.sqlite.SQLiteDatabaseLockedException(m);
      case 11: case 26: return new android.database.sqlite.SQLiteDatabaseCorruptException(m);
      case 8: return new android.database.sqlite.SQLiteReadOnlyDatabaseException(m);
      case 13: return new android.database.sqlite.SQLiteFullException(m);
      case 14: return new android.database.sqlite.SQLiteCantOpenDatabaseException(m);
      case 21: return new android.database.sqlite.SQLiteMisuseException(m);
      case 4: return new android.database.sqlite.SQLiteAbortException(m);
      case 10: return new android.database.sqlite.SQLiteDiskIOException(m);
      case 18: return new android.database.sqlite.SQLiteBlobTooBigException(m);
      default: return new android.database.sqlite.SQLiteException(m);
    }
  }
}
