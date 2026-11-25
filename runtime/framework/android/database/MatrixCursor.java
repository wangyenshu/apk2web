package android.database;

import java.util.ArrayList;

/** A cursor over rows kept in memory (also what SQLite queries return here). */
public class MatrixCursor extends AbstractCursor {
  private final String[] columnNames;
  private Object[] data;
  private int rowCount;
  private final int columnCount;
  public MatrixCursor(String[] columnNames, int initialCapacity) {
    this.columnNames = columnNames;
    this.columnCount = columnNames.length;
    if (initialCapacity < 1) initialCapacity = 1;
    this.data = new Object[columnCount * initialCapacity];
  }
  public MatrixCursor(String[] columnNames) { this(columnNames, 16); }
  private Object get(int column) {
    if (column < 0 || column >= columnCount) throw new CursorIndexOutOfBoundsException("Requested column: " + column + ", # of columns: " + columnCount);
    if (mPos < 0) throw new CursorIndexOutOfBoundsException("Before first row.");
    if (mPos >= rowCount) throw new CursorIndexOutOfBoundsException("After last row.");
    return data[mPos * columnCount + column];
  }
  public RowBuilder newRow() {
    final int row = rowCount++;
    final int endIndex = rowCount * columnCount;
    ensureCapacity(endIndex);
    return new RowBuilder(row);
  }
  public void addRow(Object[] columnValues) {
    if (columnValues.length != columnCount) throw new IllegalArgumentException("columnNames.length = " + columnCount + ", columnValues.length = " + columnValues.length);
    int start = rowCount++ * columnCount;
    ensureCapacity(start + columnCount);
    System.arraycopy(columnValues, 0, data, start, columnCount);
  }
  public void addRow(Iterable<?> columnValues) {
    int start = rowCount * columnCount;
    int end = start + columnCount;
    ensureCapacity(end);
    int current = start;
    for (Object columnValue : columnValues) {
      if (current == end) throw new IllegalArgumentException("columnValues.size() > columnNames.length");
      data[current++] = columnValue;
    }
    if (current != end) throw new IllegalArgumentException("columnValues.size() < columnNames.length");
    rowCount++;
  }
  private void ensureCapacity(int size) {
    if (size > data.length) {
      Object[] oldData = this.data;
      int newSize = data.length * 2;
      if (newSize < size) newSize = size;
      this.data = new Object[newSize];
      System.arraycopy(oldData, 0, this.data, 0, oldData.length);
    }
  }
  public class RowBuilder {
    private final int row, endIndex;
    private int index;
    RowBuilder(int row) { this.row = row; this.index = row * columnCount; this.endIndex = index + columnCount; }
    public RowBuilder add(Object columnValue) {
      if (index == endIndex) throw new CursorIndexOutOfBoundsException("No more columns left.");
      data[index++] = columnValue;
      return this;
    }
    public RowBuilder add(String columnName, Object value) {
      for (int i = 0; i < columnNames.length; i++) if (columnName.equals(columnNames[i])) data[(row * columnCount) + i] = value;
      return this;
    }
  }
  @Override public int getCount() { return rowCount; }
  @Override public String[] getColumnNames() { return columnNames; }
  @Override public String getString(int column) { return text(get(column)); }
  @Override public short getShort(int column) { return (short) asLong(get(column)); }
  @Override public int getInt(int column) { return (int) asLong(get(column)); }
  @Override public long getLong(int column) { return asLong(get(column)); }
  @Override public float getFloat(int column) { return (float) asDouble(get(column)); }
  @Override public double getDouble(int column) { return asDouble(get(column)); }
  @Override public byte[] getBlob(int column) { Object o = get(column); return o instanceof byte[] ? (byte[]) o : o == null ? null : o.toString().getBytes(); }
  @Override public int getType(int column) { return typeOf(get(column)); }
  @Override public boolean isNull(int column) { return get(column) == null; }

  static int typeOf(Object o) { return a2w.SqlValues.typeOf(o); }
  static String text(Object o) { return a2w.SqlValues.text(o); }
  static long asLong(Object o) { return a2w.SqlValues.asLong(o); }
  static double asDouble(Object o) { return a2w.SqlValues.asDouble(o); }
}
