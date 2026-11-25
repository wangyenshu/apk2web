package android.database.sqlite;

import a2w.Sql;

public final class SQLiteStatement extends SQLiteProgram {
  SQLiteStatement(SQLiteDatabase db, String sql, Object[] bindArgs) { super(db, sql, bindArgs); }
  public void execute() { mDatabase.run(mSql, mBindArgs, false); }
  public int executeUpdateDelete() { return mDatabase.run(mSql, mBindArgs, false).changes; }
  public long executeInsert() {
    try { Sql.Result r = mDatabase.run(mSql, mBindArgs, false); return r.changes > 0 || r.lastInsertId > 0 ? r.lastInsertId : -1; }
    catch (SQLiteConstraintException e) { throw e; }
  }
  public long simpleQueryForLong() {
    Sql.Result r = mDatabase.run(mSql, mBindArgs, true);
    if (r.rows.length == 0 || r.columns.length == 0) throw new SQLiteDoneException("no rows");
    return a2w.SqlValues.asLong(r.rows[0][0]);
  }
  public String simpleQueryForString() {
    Sql.Result r = mDatabase.run(mSql, mBindArgs, true);
    if (r.rows.length == 0 || r.columns.length == 0) throw new SQLiteDoneException("no rows");
    return a2w.SqlValues.text(r.rows[0][0]);
  }
  public android.os.ParcelFileDescriptor simpleQueryForBlobFileDescriptor() { return null; }
  @Override public String toString() { return "SQLiteProgram: " + mSql; }
}
