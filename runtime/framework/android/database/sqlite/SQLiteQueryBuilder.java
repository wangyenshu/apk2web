package android.database.sqlite;

import android.database.Cursor;
import android.database.DatabaseUtils;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class SQLiteQueryBuilder {
  private static final Pattern sLimitPattern = Pattern.compile("\\s*\\d+\\s*(,\\s*\\d+\\s*)?");
  private Map<String, String> mProjectionMap = null;
  private String mTables = "";
  private StringBuilder mWhereClause = null;
  private boolean mDistinct;
  private SQLiteDatabase.CursorFactory mFactory;
  private boolean mStrict;
  public SQLiteQueryBuilder() {}
  public void setDistinct(boolean distinct) { mDistinct = distinct; }
  public boolean isDistinct() { return mDistinct; }
  public String getTables() { return mTables; }
  public void setTables(String inTables) { mTables = inTables; }
  public void appendWhere(CharSequence inWhere) {
    if (mWhereClause == null) mWhereClause = new StringBuilder(inWhere.length() + 16);
    mWhereClause.append(inWhere);
  }
  public void appendWhereEscapeString(String inWhere) {
    if (mWhereClause == null) mWhereClause = new StringBuilder(inWhere.length() + 16);
    DatabaseUtils.appendEscapedSQLString(mWhereClause, inWhere);
  }
  public void appendWhereStandalone(CharSequence inWhere) {
    if (mWhereClause == null) mWhereClause = new StringBuilder(inWhere.length() + 16);
    if (mWhereClause.length() > 0) mWhereClause.append(" AND ");
    mWhereClause.append('(').append(inWhere).append(')');
  }
  public void setProjectionMap(Map<String, String> columnMap) { mProjectionMap = columnMap; }
  public Map<String, String> getProjectionMap() { return mProjectionMap; }
  public void setCursorFactory(SQLiteDatabase.CursorFactory factory) { mFactory = factory; }
  public SQLiteDatabase.CursorFactory getCursorFactory() { return mFactory; }
  public void setStrict(boolean flag) { mStrict = flag; }
  public boolean isStrict() { return mStrict; }
  public void setStrictColumns(boolean b) {}
  public void setStrictGrammar(boolean b) {}
  public static String buildQueryString(boolean distinct, String tables, String[] columns, String where, String groupBy, String having, String orderBy, String limit) {
    if (isEmpty(groupBy) && !isEmpty(having)) throw new IllegalArgumentException("HAVING clauses are only permitted when using a groupBy clause");
    if (!isEmpty(limit) && !sLimitPattern.matcher(limit).matches()) throw new IllegalArgumentException("invalid LIMIT clauses:" + limit);
    StringBuilder query = new StringBuilder(120);
    query.append("SELECT ");
    if (distinct) query.append("DISTINCT ");
    if (columns != null && columns.length != 0) appendColumns(query, columns); else query.append("* ");
    query.append("FROM ").append(tables);
    appendClause(query, " WHERE ", where);
    appendClause(query, " GROUP BY ", groupBy);
    appendClause(query, " HAVING ", having);
    appendClause(query, " ORDER BY ", orderBy);
    appendClause(query, " LIMIT ", limit);
    return query.toString();
  }
  private static boolean isEmpty(String s) { return s == null || s.isEmpty(); }
  private static void appendClause(StringBuilder s, String name, String clause) { if (!isEmpty(clause)) { s.append(name); s.append(clause); } }
  public static void appendColumns(StringBuilder s, String[] columns) {
    int n = columns.length;
    for (int i = 0; i < n; i++) {
      String column = columns[i];
      if (column != null) { if (i > 0) s.append(", "); s.append(column); }
    }
    s.append(' ');
  }
  public Cursor query(SQLiteDatabase db, String[] projectionIn, String selection, String[] selectionArgs, String groupBy, String having, String sortOrder) {
    return query(db, projectionIn, selection, selectionArgs, groupBy, having, sortOrder, null);
  }
  public Cursor query(SQLiteDatabase db, String[] projectionIn, String selection, String[] selectionArgs, String groupBy, String having, String sortOrder, String limit) {
    if (mTables == null) return null;
    String sql = buildQuery(projectionIn, selection, groupBy, having, sortOrder, limit);
    return db.rawQueryWithFactory(mFactory, sql, selectionArgs, SQLiteDatabase.findEditTable(mTables));
  }
  public Cursor query(SQLiteDatabase db, String[] projectionIn, String selection, String[] selectionArgs, String groupBy, String having, String sortOrder, String limit, android.os.CancellationSignal cs) {
    return query(db, projectionIn, selection, selectionArgs, groupBy, having, sortOrder, limit);
  }
  public long insert(SQLiteDatabase db, android.content.ContentValues values) { return db.insert(mTables, null, values); }
  public int update(SQLiteDatabase db, android.content.ContentValues values, String selection, String[] selectionArgs) { return db.update(mTables, values, concatWhere(selection), selectionArgs); }
  public int delete(SQLiteDatabase db, String selection, String[] selectionArgs) { return db.delete(mTables, concatWhere(selection), selectionArgs); }
  private String concatWhere(String selection) {
    String w = mWhereClause == null ? null : mWhereClause.toString();
    return DatabaseUtils.concatenateWhere(w, selection);
  }
  public String buildQuery(String[] projectionIn, String selection, String groupBy, String having, String sortOrder, String limit) {
    String[] projection = computeProjection(projectionIn);
    StringBuilder where = new StringBuilder();
    boolean hasBaseWhereClause = mWhereClause != null && mWhereClause.length() > 0;
    if (hasBaseWhereClause) { where.append(mWhereClause.toString()); where.append(')'); }
    if (selection != null && selection.length() > 0) {
      if (hasBaseWhereClause) where.append(" AND ");
      where.append('(').append(selection).append(')');
    }
    String w = where.toString();
    if (hasBaseWhereClause) w = "(" + w;
    return buildQueryString(mDistinct, mTables, projection, w, groupBy, having, sortOrder, limit);
  }
  @Deprecated public String buildQuery(String[] projectionIn, String selection, String[] selectionArgs, String groupBy, String having, String sortOrder, String limit) {
    return buildQuery(projectionIn, selection, groupBy, having, sortOrder, limit);
  }
  public String buildUnionSubQuery(String typeDiscriminatorColumn, String[] unionColumns, Set<String> columnsPresentInTable, int computedColumnsOffset, String typeDiscriminatorValue, String selection, String groupBy, String having) {
    int unionColumnsCount = unionColumns.length;
    String[] projectionIn = new String[unionColumnsCount];
    for (int i = 0; i < unionColumnsCount; i++) {
      String unionColumn = unionColumns[i];
      if (unionColumn.equals(typeDiscriminatorColumn)) projectionIn[i] = "'" + typeDiscriminatorValue + "' AS " + typeDiscriminatorColumn;
      else if (i <= computedColumnsOffset || columnsPresentInTable.contains(unionColumn)) projectionIn[i] = unionColumn;
      else projectionIn[i] = "NULL AS " + unionColumn;
    }
    return buildQuery(projectionIn, selection, groupBy, having, null, null);
  }
  @Deprecated public String buildUnionSubQuery(String typeDiscriminatorColumn, String[] unionColumns, Set<String> columnsPresentInTable, int computedColumnsOffset, String typeDiscriminatorValue, String selection, String[] selectionArgs, String groupBy, String having) {
    return buildUnionSubQuery(typeDiscriminatorColumn, unionColumns, columnsPresentInTable, computedColumnsOffset, typeDiscriminatorValue, selection, groupBy, having);
  }
  public String buildUnionQuery(String[] subQueries, String sortOrder, String limit) {
    StringBuilder query = new StringBuilder(128);
    int subQueryCount = subQueries.length;
    String unionOperator = mDistinct ? " UNION " : " UNION ALL ";
    for (int i = 0; i < subQueryCount; i++) { if (i > 0) query.append(unionOperator); query.append(subQueries[i]); }
    appendClause(query, " ORDER BY ", sortOrder);
    appendClause(query, " LIMIT ", limit);
    return query.toString();
  }
  private String[] computeProjection(String[] projectionIn) {
    if (projectionIn != null && projectionIn.length > 0) {
      if (mProjectionMap != null) {
        String[] projection = new String[projectionIn.length];
        int length = projectionIn.length;
        for (int i = 0; i < length; i++) {
          String userColumn = projectionIn[i];
          String column = mProjectionMap.get(userColumn);
          if (column != null) { projection[i] = column; continue; }
          if (!mStrict && (userColumn.contains(" AS ") || userColumn.contains(" as "))) { projection[i] = userColumn; continue; }
          throw new IllegalArgumentException("Invalid column " + projectionIn[i]);
        }
        return projection;
      }
      return projectionIn;
    } else if (mProjectionMap != null) {
      Set<Map.Entry<String, String>> entrySet = mProjectionMap.entrySet();
      String[] projection = new String[entrySet.size()];
      Iterator<Map.Entry<String, String>> entryIter = entrySet.iterator();
      int i = 0;
      while (entryIter.hasNext()) {
        Map.Entry<String, String> entry = entryIter.next();
        if (entry.getKey().equals("_count")) continue;
        projection[i++] = entry.getValue();
      }
      return java.util.Arrays.copyOf(projection, i);
    }
    return null;
  }
}
