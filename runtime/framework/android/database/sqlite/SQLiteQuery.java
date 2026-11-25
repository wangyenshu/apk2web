package android.database.sqlite;

public final class SQLiteQuery extends SQLiteProgram {
  SQLiteQuery(SQLiteDatabase db, String query, Object[] bindArgs) { super(db, query, bindArgs); }
  a2w.Sql.Result run() { return mDatabase.run(mSql, mBindArgs, true); }
  @Override public String toString() { return "SQLiteQuery: " + mSql; }
}
