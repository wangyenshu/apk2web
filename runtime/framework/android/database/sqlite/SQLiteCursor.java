package android.database.sqlite;

import android.database.MatrixCursor;

/** The rows of one query, read in full when the cursor is made (and again on requery). */
public class SQLiteCursor extends android.database.AbstractWindowedCursor {
  private final String mEditTable;
  private final SQLiteQuery mQuery;
  private final SQLiteCursorDriver mDriver;
  private String[] mColumns;
  private Object[][] mRows;
  public SQLiteCursor(SQLiteCursorDriver driver, String editTable, SQLiteQuery query) {
    if (query == null) throw new IllegalArgumentException("query object cannot be null");
    mDriver = driver; mEditTable = editTable; mQuery = query;
    fill();
  }
  @Deprecated public SQLiteCursor(SQLiteDatabase db, SQLiteCursorDriver driver, String editTable, SQLiteQuery query) { this(driver, editTable, query); }
  private void fill() {
    a2w.Sql.Result r = mQuery.run();
    mColumns = r.columns;
    mRows = r.rows;
  }
  public SQLiteDatabase getDatabase() { return mQuery.getDatabase(); }
  @Override public int getCount() { return mRows.length; }
  @Override public String[] getColumnNames() { return mColumns; }
  @Override protected Object value(int column) {
    checkPosition();
    if (column < 0 || column >= mColumns.length) throw new IllegalStateException("Couldn't read row " + mPos + ", col " + column + " from CursorWindow.  Make sure the Cursor is initialized correctly before accessing data from it.");
    return mRows[mPos][column];
  }
  @Override public void deactivate() { super.deactivate(); if (mDriver != null) mDriver.cursorDeactivated(); }
  @Override public void close() { super.close(); if (mDriver != null) mDriver.cursorClosed(); }
  @Override public boolean requery() {
    if (isClosed()) return false;
    try { fill(); } catch (RuntimeException e) { return false; }
    mPos = -1;
    if (mDriver != null) mDriver.cursorRequeried(this);
    return super.requery();
  }
  public void setSelectionArguments(String[] selectionArgs) { mQuery.clearBindings(); mQuery.bindAllArgsAsStrings(selectionArgs); }
  public void setFillWindowForwardOnly(boolean b) {}
  @Override public String toString() { return "SQLiteCursor{" + mQuery.getSql() + "}"; }
}
