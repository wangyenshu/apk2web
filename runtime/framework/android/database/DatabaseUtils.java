package android.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import java.io.PrintStream;

public class DatabaseUtils {
  public static final int STATEMENT_SELECT = 1, STATEMENT_UPDATE = 2, STATEMENT_ATTACH = 3, STATEMENT_BEGIN = 4, STATEMENT_COMMIT = 5,
      STATEMENT_ABORT = 6, STATEMENT_PRAGMA = 7, STATEMENT_DDL = 8, STATEMENT_UNPREPARED = 9, STATEMENT_OTHER = 99;
  public static void appendEscapedSQLString(StringBuilder sb, String sqlString) {
    sb.append('\'');
    if (sqlString.indexOf('\'') != -1) {
      for (int i = 0; i < sqlString.length(); i++) { char c = sqlString.charAt(i); if (c == '\'') sb.append('\''); sb.append(c); }
    } else sb.append(sqlString);
    sb.append('\'');
  }
  public static String sqlEscapeString(String value) { StringBuilder sb = new StringBuilder(); appendEscapedSQLString(sb, value); return sb.toString(); }
  public static final void appendValueToSql(StringBuilder sql, Object value) {
    if (value == null) sql.append("NULL");
    else if (value instanceof Boolean) sql.append((Boolean) value ? '1' : '0');
    else appendEscapedSQLString(sql, value.toString());
  }
  public static String concatenateWhere(String a, String b) {
    if (a == null || a.isEmpty()) return b;
    if (b == null || b.isEmpty()) return a;
    return "(" + a + ") AND (" + b + ")";
  }
  public static String[] appendSelectionArgs(String[] originalValues, String[] newValues) {
    if (originalValues == null || originalValues.length == 0) return newValues;
    String[] result = new String[originalValues.length + newValues.length];
    System.arraycopy(originalValues, 0, result, 0, originalValues.length);
    System.arraycopy(newValues, 0, result, originalValues.length, newValues.length);
    return result;
  }
  public static String getCollationKey(String name) { return name == null ? "" : name.toLowerCase(); }
  public static String getHexCollationKey(String name) { StringBuilder b = new StringBuilder(); for (char c : getCollationKey(name).toCharArray()) b.append(Integer.toHexString(c)); return b.toString(); }
  public static int getTypeOfObject(Object obj) { return MatrixCursor.typeOf(obj); }
  public static void cursorFillWindow(Cursor cursor, int position, CursorWindow window) { window.fill(cursor, position); }
  public static void dumpCursor(Cursor cursor) { dumpCursor(cursor, System.out); }
  public static void dumpCursor(Cursor cursor, PrintStream stream) { stream.println(dumpCursorToString(cursor)); }
  public static void dumpCursor(Cursor cursor, StringBuilder sb) { sb.append(dumpCursorToString(cursor)); }
  public static String dumpCursorToString(Cursor cursor) {
    StringBuilder sb = new StringBuilder(">>>>> Dumping cursor " + cursor + "\n");
    if (cursor != null) {
      int startPos = cursor.getPosition();
      cursor.moveToPosition(-1);
      while (cursor.moveToNext()) dumpCurrentRow(cursor, sb);
      cursor.moveToPosition(startPos);
    }
    return sb.append("<<<<<\n").toString();
  }
  public static void dumpCurrentRow(Cursor cursor) { System.out.println(dumpCurrentRowToString(cursor)); }
  public static void dumpCurrentRow(Cursor cursor, PrintStream stream) { stream.println(dumpCurrentRowToString(cursor)); }
  public static void dumpCurrentRow(Cursor cursor, StringBuilder sb) {
    String[] cols = cursor.getColumnNames();
    sb.append(cursor.getPosition()).append(" {\n");
    for (int i = 0; i < cols.length; i++) {
      String value;
      try { value = cursor.getString(i); } catch (RuntimeException e) { value = "<unprintable>"; }
      sb.append("   ").append(cols[i]).append('=').append(value).append('\n');
    }
    sb.append("}\n");
  }
  public static String dumpCurrentRowToString(Cursor cursor) { StringBuilder sb = new StringBuilder(); dumpCurrentRow(cursor, sb); return sb.toString(); }
  public static void cursorStringToContentValues(Cursor cursor, String field, ContentValues values) { cursorStringToContentValues(cursor, field, values, field); }
  public static void cursorStringToInsertHelper(Cursor cursor, String field, Object inserter, int index) {}
  public static void cursorStringToContentValues(Cursor cursor, String field, ContentValues values, String key) { values.put(key, cursor.getString(cursor.getColumnIndexOrThrow(field))); }
  public static void cursorIntToContentValues(Cursor cursor, String field, ContentValues values) { cursorIntToContentValues(cursor, field, values, field); }
  public static void cursorIntToContentValues(Cursor cursor, String field, ContentValues values, String key) {
    int colIndex = cursor.getColumnIndex(field);
    if (!cursor.isNull(colIndex)) values.put(key, cursor.getInt(colIndex)); else values.put(key, (Integer) null);
  }
  public static void cursorLongToContentValues(Cursor cursor, String field, ContentValues values) { cursorLongToContentValues(cursor, field, values, field); }
  public static void cursorLongToContentValues(Cursor cursor, String field, ContentValues values, String key) {
    int colIndex = cursor.getColumnIndex(field);
    if (!cursor.isNull(colIndex)) values.put(key, Long.valueOf(cursor.getLong(colIndex))); else values.put(key, (Long) null);
  }
  public static void cursorDoubleToCursorValues(Cursor cursor, String field, ContentValues values) { cursorDoubleToContentValues(cursor, field, values, field); }
  public static void cursorDoubleToContentValues(Cursor cursor, String field, ContentValues values, String key) {
    int colIndex = cursor.getColumnIndex(field);
    if (!cursor.isNull(colIndex)) values.put(key, cursor.getDouble(colIndex)); else values.put(key, (Double) null);
  }
  public static void cursorRowToContentValues(Cursor cursor, ContentValues values) {
    String[] columns = cursor.getColumnNames();
    for (int i = 0; i < columns.length; i++) {
      if (cursor.getType(i) == Cursor.FIELD_TYPE_BLOB) values.put(columns[i], cursor.getBlob(i));
      else values.put(columns[i], cursor.getString(i));
    }
  }
  public static int cursorPickFillWindowStartPosition(int cursorPosition, int cursorWindowCapacity) { return Math.max(cursorPosition - cursorWindowCapacity / 3, 0); }
  public static long queryNumEntries(SQLiteDatabase db, String table) { return queryNumEntries(db, table, null, null); }
  public static long queryNumEntries(SQLiteDatabase db, String table, String selection) { return queryNumEntries(db, table, selection, null); }
  public static long queryNumEntries(SQLiteDatabase db, String table, String selection, String[] selectionArgs) {
    String s = (selection != null && !selection.isEmpty()) ? " where " + selection : "";
    return longForQuery(db, "select count(*) from " + table + s, selectionArgs);
  }
  public static boolean queryIsEmpty(SQLiteDatabase db, String table) { return longForQuery(db, "select exists(select 1 from " + table + ")", null) == 0; }
  public static long longForQuery(SQLiteDatabase db, String query, String[] selectionArgs) {
    SQLiteStatement prog = db.compileStatement(query);
    try { return longForQuery(prog, selectionArgs); } finally { prog.close(); }
  }
  public static long longForQuery(SQLiteStatement prog, String[] selectionArgs) { prog.bindAllArgsAsStrings(selectionArgs); return prog.simpleQueryForLong(); }
  public static String stringForQuery(SQLiteDatabase db, String query, String[] selectionArgs) {
    SQLiteStatement prog = db.compileStatement(query);
    try { return stringForQuery(prog, selectionArgs); } finally { prog.close(); }
  }
  public static String stringForQuery(SQLiteStatement prog, String[] selectionArgs) { prog.bindAllArgsAsStrings(selectionArgs); return prog.simpleQueryForString(); }
  public static int getSqlStatementType(String sql) {
    sql = sql.trim();
    if (sql.length() < 3) return STATEMENT_OTHER;
    String prefixSql = sql.substring(0, 3).toUpperCase(java.util.Locale.ROOT);
    switch (prefixSql) {
      case "SEL": case "WIT": return STATEMENT_SELECT;
      case "INS": case "UPD": case "REP": case "DEL": return STATEMENT_UPDATE;
      case "ATT": return STATEMENT_ATTACH;
      case "COM": case "END": return STATEMENT_COMMIT;
      case "ROL": return sql.toUpperCase(java.util.Locale.ROOT).contains(" TO ") ? STATEMENT_UPDATE : STATEMENT_ABORT;
      case "BEG": return STATEMENT_BEGIN;
      case "PRA": return STATEMENT_PRAGMA;
      case "CRE": case "DRO": case "ALT": return STATEMENT_DDL;
      case "ANA": case "DET": return STATEMENT_UNPREPARED;
    }
    return STATEMENT_OTHER;
  }
  public static void createDbFromSqlStatements(android.content.Context context, String dbName, int dbVersion, String sqlStatements) {
    SQLiteDatabase db = context.openOrCreateDatabase(dbName, 0, null);
    for (String statement : sqlStatements.split(";\n")) if (!statement.trim().isEmpty()) db.execSQL(statement);
    db.setVersion(dbVersion);
    db.close();
  }
}
