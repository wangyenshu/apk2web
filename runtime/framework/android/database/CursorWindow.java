package android.database;

import java.util.ArrayList;

/** Rows of values (here a plain Java table). */
public class CursorWindow implements java.io.Closeable {
  private final String mName;
  private int mStartPos, mNumColumns;
  private final ArrayList<Object[]> mRows = new ArrayList<>();
  public CursorWindow(String name) { mName = name == null ? "<unnamed>" : name; }
  public CursorWindow(String name, long windowSizeBytes) { this(name); }
  @Deprecated public CursorWindow(boolean localWindow) { this((String) null); }
  public String getName() { return mName; }
  public void clear() { mStartPos = 0; mRows.clear(); }
  public int getStartPosition() { return mStartPos; }
  public void setStartPosition(int pos) { mStartPos = pos; }
  public int getNumRows() { return mRows.size(); }
  public boolean setNumColumns(int n) { mNumColumns = n; return true; }
  public boolean allocRow() { mRows.add(new Object[mNumColumns]); return true; }
  public void freeLastRow() { if (!mRows.isEmpty()) mRows.remove(mRows.size() - 1); }
  private Object[] row(int r) { return mRows.get(r - mStartPos); }
  public boolean putBlob(byte[] v, int r, int c) { row(r)[c] = v; return true; }
  public boolean putString(String v, int r, int c) { row(r)[c] = v; return true; }
  public boolean putLong(long v, int r, int c) { row(r)[c] = v; return true; }
  public boolean putDouble(double v, int r, int c) { row(r)[c] = v; return true; }
  public boolean putNull(int r, int c) { row(r)[c] = null; return true; }
  public int getType(int r, int c) { return MatrixCursor.typeOf(row(r)[c]); }
  public byte[] getBlob(int r, int c) { Object o = row(r)[c]; return o instanceof byte[] ? (byte[]) o : o == null ? null : o.toString().getBytes(); }
  public String getString(int r, int c) { return MatrixCursor.text(row(r)[c]); }
  public long getLong(int r, int c) { return MatrixCursor.asLong(row(r)[c]); }
  public double getDouble(int r, int c) { return MatrixCursor.asDouble(row(r)[c]); }
  public short getShort(int r, int c) { return (short) getLong(r, c); }
  public int getInt(int r, int c) { return (int) getLong(r, c); }
  public float getFloat(int r, int c) { return (float) getDouble(r, c); }
  public void copyStringToBuffer(int r, int c, CharArrayBuffer buffer) {
    String s = getString(r, c);
    if (s == null) { buffer.sizeCopied = 0; return; }
    buffer.data = s.toCharArray(); buffer.sizeCopied = s.length();
  }
  void fill(Cursor c, int position) {
    clear();
    mStartPos = position;
    setNumColumns(c.getColumnCount());
    int old = c.getPosition();
    if (c.moveToPosition(position - 1)) {}
    while (c.moveToNext()) {
      allocRow();
      Object[] row = mRows.get(mRows.size() - 1);
      for (int i = 0; i < mNumColumns; i++) {
        switch (c.getType(i)) {
          case Cursor.FIELD_TYPE_NULL: row[i] = null; break;
          case Cursor.FIELD_TYPE_INTEGER: row[i] = c.getLong(i); break;
          case Cursor.FIELD_TYPE_FLOAT: row[i] = c.getDouble(i); break;
          case Cursor.FIELD_TYPE_BLOB: row[i] = c.getBlob(i); break;
          default: row[i] = c.getString(i);
        }
      }
    }
    c.moveToPosition(old);
  }
  public void close() {}
}
