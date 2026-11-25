package android.database;

/** A cursor whose values come from a table of objects (the window); subclasses supply value(). */
public abstract class AbstractWindowedCursor extends AbstractCursor {
  protected CursorWindow mWindow;
  protected Object value(int column) { checkPosition(); throw new IllegalStateException("no window"); }
  @Override public byte[] getBlob(int c) { Object o = value(c); return o instanceof byte[] ? (byte[]) o : o == null ? null : o.toString().getBytes(); }
  @Override public String getString(int c) { return MatrixCursor.text(value(c)); }
  @Override public void copyStringToBuffer(int c, CharArrayBuffer buffer) { super.copyStringToBuffer(c, buffer); }
  @Override public short getShort(int c) { return (short) MatrixCursor.asLong(value(c)); }
  @Override public int getInt(int c) { return (int) MatrixCursor.asLong(value(c)); }
  @Override public long getLong(int c) { return MatrixCursor.asLong(value(c)); }
  @Override public float getFloat(int c) { return (float) MatrixCursor.asDouble(value(c)); }
  @Override public double getDouble(int c) { return MatrixCursor.asDouble(value(c)); }
  @Override public boolean isNull(int c) { return value(c) == null; }
  @Deprecated public boolean isBlob(int c) { return getType(c) == FIELD_TYPE_BLOB; }
  @Deprecated public boolean isString(int c) { int t = getType(c); return t == FIELD_TYPE_STRING || t == FIELD_TYPE_NULL; }
  @Deprecated public boolean isLong(int c) { return getType(c) == FIELD_TYPE_INTEGER; }
  @Deprecated public boolean isFloat(int c) { return getType(c) == FIELD_TYPE_FLOAT; }
  @Override public int getType(int c) { return MatrixCursor.typeOf(value(c)); }
  @Override protected void checkPosition() { super.checkPosition(); }
  @Override public CursorWindow getWindow() { return mWindow; }
  public void setWindow(CursorWindow window) { mWindow = window; }
  public boolean hasWindow() { return mWindow != null; }
}
