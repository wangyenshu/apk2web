package android.database.sqlite;

import a2w.Sql;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.util.Log;
import android.util.Pair;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/** A SQLite database file, run by SQLite compiled to WebAssembly; the file lives with the app's other files. */
public final class SQLiteDatabase extends SQLiteClosable {
  private static final String TAG = "SQLiteDatabase";
  public interface CursorFactory { Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver masterQuery, String editTable, SQLiteQuery query); }
  public interface CustomFunction { void callback(String[] args); }
  public static final int CONFLICT_ROLLBACK = 1, CONFLICT_ABORT = 2, CONFLICT_FAIL = 3, CONFLICT_IGNORE = 4, CONFLICT_REPLACE = 5, CONFLICT_NONE = 0;
  private static final String[] CONFLICT_VALUES = {"", " OR ROLLBACK ", " OR ABORT ", " OR FAIL ", " OR IGNORE ", " OR REPLACE "};
  public static final int SQLITE_MAX_LIKE_PATTERN_LENGTH = 50000;
  public static final int OPEN_READWRITE = 0x00000000, OPEN_READONLY = 0x00000001, OPEN_READ_MASK = 0x00000001, NO_LOCALIZED_COLLATORS = 0x00000010,
      CREATE_IF_NECESSARY = 0x10000000, ENABLE_WRITE_AHEAD_LOGGING = 0x20000000, MAX_SQL_CACHE_SIZE = 100;

  private final String mPath;
  private final int mFlags;
  private final CursorFactory mFactory;
  private final DatabaseErrorHandler mErrorHandler;
  private int mHandle;
  // nested transactions, as SQLiteSession does them
  private static final class Tx { boolean successful, childFailed; SQLiteTransactionListener listener; Tx parent; }
  private Tx mTx;
  private int mTxDepth;

  private SQLiteDatabase(String path, int flags, CursorFactory factory, DatabaseErrorHandler eh) {
    mPath = path; mFlags = flags; mFactory = factory; mErrorHandler = eh != null ? eh : new DefaultDatabaseErrorHandler();
  }
  private void open() {
    if (!Sql.available()) throw new SQLiteCantOpenDatabaseException("SQLite is not available in this page");
    boolean mem = mPath == null || mPath.equals(":memory:");
    if (!mem) {
      File f = new File(mPath);
      if ((mFlags & CREATE_IF_NECESSARY) != 0 && f.getParentFile() != null) f.getParentFile().mkdirs();
    }
    int h = Sql.open(mem ? null : mPath, (mFlags & CREATE_IF_NECESSARY) != 0);
    if (h == -26) {
      mErrorHandler.onCorruption(this);
      h = Sql.open(mPath, true);
    }
    if (h < 0) throw new SQLiteCantOpenDatabaseException("unknown error (code " + -h + "): Could not open database " + mPath);
    mHandle = h;
  }
  public static SQLiteDatabase openDatabase(String path, CursorFactory factory, int flags) { return openDatabase(path, factory, flags, null); }
  public static SQLiteDatabase openDatabase(String path, CursorFactory factory, int flags, DatabaseErrorHandler errorHandler) {
    SQLiteDatabase db = new SQLiteDatabase(path, flags, factory, errorHandler);
    db.open();
    return db;
  }
  public static SQLiteDatabase openDatabase(File path, OpenParams params) {
    return openDatabase(path.getPath(), params.mCursorFactory, params.mOpenFlags, params.mErrorHandler);
  }
  public static SQLiteDatabase openOrCreateDatabase(File file, CursorFactory factory) { return openOrCreateDatabase(file.getPath(), factory); }
  public static SQLiteDatabase openOrCreateDatabase(String path, CursorFactory factory) { return openDatabase(path, factory, CREATE_IF_NECESSARY, null); }
  public static SQLiteDatabase openOrCreateDatabase(String path, CursorFactory factory, DatabaseErrorHandler errorHandler) { return openDatabase(path, factory, CREATE_IF_NECESSARY, errorHandler); }
  public static SQLiteDatabase create(CursorFactory factory) { return openDatabase(":memory:", factory, CREATE_IF_NECESSARY); }
  public static SQLiteDatabase createInMemory(OpenParams params) { return openDatabase(":memory:", params.mCursorFactory, CREATE_IF_NECESSARY, params.mErrorHandler); }
  public static boolean deleteDatabase(File file) {
    boolean deleted = file.delete();
    new File(file.getPath() + "-journal").delete();
    new File(file.getPath() + "-shm").delete();
    new File(file.getPath() + "-wal").delete();
    return deleted;
  }
  public static int releaseMemory() { return 0; }
  public static String findEditTable(String tables) {
    if (tables == null || tables.isEmpty()) throw new IllegalStateException("Invalid tables");
    int spacepos = tables.indexOf(' '), commapos = tables.indexOf(',');
    if (spacepos > 0 && (spacepos < commapos || commapos < 0)) return tables.substring(0, spacepos);
    else if (commapos > 0 && (commapos < spacepos || spacepos < 0)) return tables.substring(0, commapos);
    return tables;
  }

  // ---------------------------------------------------------------- statements
  Sql.Result run(String sql, Object[] args, boolean rows) {
    if (mHandle == 0) throw new IllegalStateException("attempt to re-open an already-closed object: SQLiteDatabase: " + mPath);
    String s = sql.trim();
    String u = s.length() >= 8 ? s.substring(0, 8).toUpperCase(Locale.ROOT) : s.toUpperCase(Locale.ROOT);
    // transaction control written as SQL goes through the same bookkeeping as beginTransaction()
    if (u.startsWith("BEGIN")) { beginTransaction(); return new Sql.Result(); }
    if ((u.startsWith("COMMIT") || u.startsWith("END")) && mTx != null) { setTransactionSuccessful(); endTransaction(); return new Sql.Result(); }
    if (u.startsWith("ROLLBACK") && mTx != null && !s.toUpperCase(Locale.ROOT).contains(" TO ")) { endTransaction(); return new Sql.Result(); }
    try {
      return Sql.run(mHandle, s, args, rows);
    } catch (SQLiteDatabaseCorruptException e) {
      mErrorHandler.onCorruption(this);
      throw e;
    }
  }
  public void execSQL(String sql) throws android.database.SQLException { run(sql, null, false); }
  public void execSQL(String sql, Object[] bindArgs) throws android.database.SQLException {
    if (bindArgs == null) throw new IllegalArgumentException("Empty bindArgs");
    run(sql, bindArgs, false);
  }
  public void execPerConnectionSQL(String sql, Object[] bindArgs) { run(sql, bindArgs, false); }
  public SQLiteStatement compileStatement(String sql) throws android.database.SQLException { return new SQLiteStatement(this, sql, null); }
  public void validateSql(String sql, android.os.CancellationSignal cancellationSignal) {}

  public Cursor rawQuery(String sql, String[] selectionArgs) { return rawQueryWithFactory(null, sql, selectionArgs, null); }
  public Cursor rawQuery(String sql, String[] selectionArgs, android.os.CancellationSignal cancellationSignal) { return rawQueryWithFactory(null, sql, selectionArgs, null); }
  public Cursor rawQuery(String sql, Object[] selectionArgs) { return query(null, sql, selectionArgs, null); }
  public Cursor rawQueryWithFactory(CursorFactory cursorFactory, String sql, String[] selectionArgs, String editTable) { return query(cursorFactory, sql, selectionArgs, editTable); }
  public Cursor rawQueryWithFactory(CursorFactory cursorFactory, String sql, String[] selectionArgs, String editTable, android.os.CancellationSignal cs) { return query(cursorFactory, sql, selectionArgs, editTable); }
  private Cursor query(CursorFactory cursorFactory, String sql, Object[] selectionArgs, String editTable) {
    final CursorFactory f = cursorFactory != null ? cursorFactory : mFactory;
    SQLiteCursorDriver driver = new SQLiteCursorDriver() {
      SQLiteQuery q;
      public Cursor query(CursorFactory factory, String[] bindArgs) {
        q = new SQLiteQuery(SQLiteDatabase.this, sql, bindArgs);
        return factory == null ? new SQLiteCursor(this, editTable, q) : factory.newCursor(SQLiteDatabase.this, this, editTable, q);
      }
      public void cursorDeactivated() {}
      public void cursorRequeried(Cursor cursor) {}
      public void cursorClosed() {}
      public void setBindArguments(String[] bindArgs) { if (q != null) q.bindAllArgsAsStrings(bindArgs); }
    };
    if (selectionArgs == null || selectionArgs instanceof String[]) return driver.query(f, (String[]) selectionArgs);
    SQLiteQuery q = new SQLiteQuery(this, sql, selectionArgs);
    return f == null ? new SQLiteCursor(driver, editTable, q) : f.newCursor(this, driver, editTable, q);
  }
  public Cursor query(boolean distinct, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
    return queryWithFactory(null, distinct, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
  }
  public Cursor query(boolean distinct, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit, android.os.CancellationSignal cs) {
    return queryWithFactory(null, distinct, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
  }
  public Cursor queryWithFactory(CursorFactory cursorFactory, boolean distinct, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
    String sql = SQLiteQueryBuilder.buildQueryString(distinct, table, columns, selection, groupBy, having, orderBy, limit);
    return rawQueryWithFactory(cursorFactory, sql, selectionArgs, findEditTable(table));
  }
  public Cursor queryWithFactory(CursorFactory cursorFactory, boolean distinct, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit, android.os.CancellationSignal cs) {
    return queryWithFactory(cursorFactory, distinct, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
  }
  public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
    return query(false, table, columns, selection, selectionArgs, groupBy, having, orderBy, null);
  }
  public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
    return query(false, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
  }

  public long insert(String table, String nullColumnHack, ContentValues values) {
    try { return insertWithOnConflict(table, nullColumnHack, values, CONFLICT_NONE); }
    catch (android.database.SQLException e) { Log.e(TAG, "Error inserting " + values, e); return -1; }
  }
  public long insertOrThrow(String table, String nullColumnHack, ContentValues values) throws android.database.SQLException { return insertWithOnConflict(table, nullColumnHack, values, CONFLICT_NONE); }
  public long replace(String table, String nullColumnHack, ContentValues initialValues) {
    try { return insertWithOnConflict(table, nullColumnHack, initialValues, CONFLICT_REPLACE); }
    catch (android.database.SQLException e) { Log.e(TAG, "Error inserting " + initialValues, e); return -1; }
  }
  public long replaceOrThrow(String table, String nullColumnHack, ContentValues initialValues) throws android.database.SQLException { return insertWithOnConflict(table, nullColumnHack, initialValues, CONFLICT_REPLACE); }
  public long insertWithOnConflict(String table, String nullColumnHack, ContentValues initialValues, int conflictAlgorithm) {
    StringBuilder sql = new StringBuilder();
    sql.append("INSERT").append(CONFLICT_VALUES[conflictAlgorithm]).append(" INTO ").append(table).append('(');
    Object[] bindArgs = null;
    int size = (initialValues != null && !initialValues.isEmpty()) ? initialValues.size() : 0;
    if (size > 0) {
      bindArgs = new Object[size];
      int i = 0;
      for (String colName : initialValues.keySet()) { sql.append((i > 0) ? "," : "").append(colName); bindArgs[i++] = initialValues.get(colName); }
      sql.append(") VALUES (");
      for (i = 0; i < size; i++) sql.append((i > 0) ? ",?" : "?");
    } else sql.append(nullColumnHack).append(") VALUES (NULL");
    sql.append(')');
    Sql.Result r = run(sql.toString(), bindArgs, false);
    return r.changes > 0 ? r.lastInsertId : -1;
  }
  public int delete(String table, String whereClause, String[] whereArgs) {
    return run("DELETE FROM " + table + (whereClause != null && !whereClause.isEmpty() ? " WHERE " + whereClause : ""), whereArgs, false).changes;
  }
  public int update(String table, ContentValues values, String whereClause, String[] whereArgs) { return updateWithOnConflict(table, values, whereClause, whereArgs, CONFLICT_NONE); }
  public int updateWithOnConflict(String table, ContentValues values, String whereClause, String[] whereArgs, int conflictAlgorithm) {
    if (values == null || values.isEmpty()) throw new IllegalArgumentException("Empty values");
    StringBuilder sql = new StringBuilder(120);
    sql.append("UPDATE ").append(CONFLICT_VALUES[conflictAlgorithm]).append(table).append(" SET ");
    int setValuesSize = values.size();
    int bindArgsSize = (whereArgs == null) ? setValuesSize : (setValuesSize + whereArgs.length);
    Object[] bindArgs = new Object[bindArgsSize];
    int i = 0;
    for (String colName : values.keySet()) { sql.append((i > 0) ? "," : "").append(colName); bindArgs[i++] = values.get(colName); sql.append("=?"); }
    if (whereArgs != null) for (i = setValuesSize; i < bindArgsSize; i++) bindArgs[i] = whereArgs[i - setValuesSize];
    if (whereClause != null && !whereClause.isEmpty()) sql.append(" WHERE ").append(whereClause);
    return run(sql.toString(), bindArgs, false).changes;
  }

  // ---------------------------------------------------------------- transactions
  public void beginTransaction() { beginTransactionWithListener(null); }
  public void beginTransactionNonExclusive() { beginTransactionWithListener(null); }
  public void beginTransactionReadOnly() { beginTransactionWithListener(null); }
  public void beginTransactionWithListenerNonExclusive(SQLiteTransactionListener l) { beginTransactionWithListener(l); }
  public void beginTransactionWithListenerReadOnly(SQLiteTransactionListener l) { beginTransactionWithListener(l); }
  public void beginTransactionWithListener(SQLiteTransactionListener transactionListener) {
    if (mTx == null) Sql.run(mHandle, "BEGIN EXCLUSIVE;", null, false);
    Tx t = new Tx();
    t.listener = transactionListener;
    t.parent = mTx;
    mTx = t;
    mTxDepth++;
    Sql.setTx(mHandle, mTxDepth);
    if (transactionListener != null) {
      try { transactionListener.onBegin(); }
      catch (RuntimeException e) { endTransactionInternal(); throw e; }
    }
  }
  public void setTransactionSuccessful() {
    if (mTx == null) throw new IllegalStateException("Cannot perform this operation because there is no current transaction.");
    if (mTx.successful) throw new IllegalStateException("Cannot perform this operation because the transaction has already been marked successful.  The only thing you can do now is call endTransaction().");
    mTx.successful = true;
  }
  public void endTransaction() {
    if (mTx == null) throw new IllegalStateException("Cannot perform this operation because there is no current transaction.");
    endTransactionInternal();
  }
  private void endTransactionInternal() {
    Tx t = mTx;
    boolean ok = t.successful && !t.childFailed;
    RuntimeException listenerException = null;
    if (t.listener != null) {
      try { if (ok) t.listener.onCommit(); else t.listener.onRollback(); }
      catch (RuntimeException e) { listenerException = e; ok = false; }
    }
    mTx = t.parent;
    mTxDepth--;
    if (mTx != null) { if (!ok) mTx.childFailed = true; }
    else {
      try { Sql.run(mHandle, ok ? "COMMIT;" : "ROLLBACK;", null, false); }
      finally { Sql.setTx(mHandle, 0); }
    }
    if (mTx != null) Sql.setTx(mHandle, mTxDepth);
    if (listenerException != null) throw listenerException;
  }
  public boolean inTransaction() { return mTx != null; }
  public boolean isDbLockedByCurrentThread() { return mTx != null; }
  @Deprecated public boolean isDbLockedByOtherThreads() { return false; }
  @Deprecated public boolean yieldIfContended() { return false; }
  public boolean yieldIfContendedSafely() { return false; }
  public boolean yieldIfContendedSafely(long sleepAfterYieldDelay) { return false; }

  // ---------------------------------------------------------------- properties
  private long pragma(String p) {
    Sql.Result r = Sql.run(mHandle, "PRAGMA " + p + ";", null, true);
    return r.rows.length == 0 ? 0 : a2w.SqlValues.asLong(r.rows[0][0]);
  }
  public int getVersion() { return (int) pragma("user_version"); }
  public void setVersion(int version) { execSQL("PRAGMA user_version = " + version); }
  public long getMaximumSize() { return pragma("max_page_count") * getPageSize(); }
  public long setMaximumSize(long numBytes) {
    long pageSize = getPageSize();
    long numPages = numBytes / pageSize;
    if ((numBytes % pageSize) != 0) numPages++;
    return pragma("max_page_count = " + numPages) * pageSize;
  }
  public long getPageSize() { return pragma("page_size"); }
  public void setPageSize(long numBytes) { execSQL("PRAGMA page_size = " + numBytes); }
  @Deprecated public void markTableSyncable(String table, String deletedTable) {}
  @Deprecated public void markTableSyncable(String table, String foreignKey, String updateTable) {}
  public boolean isReadOnly() { return (mFlags & OPEN_READ_MASK) == OPEN_READONLY; }
  public boolean isInMemoryDatabase() { return mPath == null || mPath.equals(":memory:"); }
  public boolean isOpen() { return mHandle != 0; }
  public boolean needUpgrade(int newVersion) { return newVersion > getVersion(); }
  public final String getPath() { return mPath; }
  public void setLocale(Locale locale) {}
  public void setMaxSqlCacheSize(int cacheSize) {}
  public void setForeignKeyConstraintsEnabled(boolean enable) { execSQL("PRAGMA foreign_keys = " + (enable ? 1 : 0)); }
  public boolean enableWriteAheadLogging() { return false; }
  public void disableWriteAheadLogging() {}
  public boolean isWriteAheadLoggingEnabled() { return false; }
  @Deprecated public void setLockingEnabled(boolean lockingEnabled) {}
  public List<Pair<String, String>> getAttachedDbs() {
    ArrayList<Pair<String, String>> out = new ArrayList<>();
    Sql.Result r = Sql.run(mHandle, "PRAGMA database_list;", null, true);
    for (Object[] row : r.rows) out.add(new Pair<>(a2w.SqlValues.text(row[1]), a2w.SqlValues.text(row[2])));
    return out;
  }
  public boolean isDatabaseIntegrityOk() {
    Sql.Result r = Sql.run(mHandle, "PRAGMA integrity_check(1);", null, true);
    return r.rows.length > 0 && "ok".equalsIgnoreCase(a2w.SqlValues.text(r.rows[0][0]));
  }
  public Map<String, String> getSyncedTables() { return new java.util.HashMap<>(); }
  public void addCustomFunction(String name, int numArgs, CustomFunction function) {}
  @Override protected void onAllReferencesReleased() {
    if (mHandle != 0) { Sql.close(mHandle); mHandle = 0; }
  }
  @Override public String toString() { return "SQLiteDatabase: " + mPath; }

  public static final class OpenParams {
    final int mOpenFlags; final CursorFactory mCursorFactory; final DatabaseErrorHandler mErrorHandler;
    OpenParams(int flags, CursorFactory f, DatabaseErrorHandler eh) { mOpenFlags = flags; mCursorFactory = f; mErrorHandler = eh; }
    public int getOpenFlags() { return mOpenFlags; }
    public CursorFactory getCursorFactory() { return mCursorFactory; }
    public DatabaseErrorHandler getErrorHandler() { return mErrorHandler; }
    public static final class Builder {
      int flags; CursorFactory f; DatabaseErrorHandler eh;
      public Builder() {}
      public Builder(OpenParams p) { flags = p.mOpenFlags; f = p.mCursorFactory; eh = p.mErrorHandler; }
      public Builder setOpenFlags(int fl) { flags = fl; return this; }
      public Builder addOpenFlags(int fl) { flags |= fl; return this; }
      public Builder removeOpenFlags(int fl) { flags &= ~fl; return this; }
      public Builder setCursorFactory(CursorFactory c) { f = c; return this; }
      public Builder setErrorHandler(DatabaseErrorHandler e) { eh = e; return this; }
      public Builder setLookasideConfig(int a, int b) { return this; }
      public Builder setJournalMode(String m) { return this; }
      public Builder setSynchronousMode(String m) { return this; }
      public Builder setIdleConnectionTimeout(long t) { return this; }
      public OpenParams build() { return new OpenParams(flags, f, eh); }
    }
  }
}
