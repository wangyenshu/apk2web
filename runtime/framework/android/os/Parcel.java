package android.os;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.IntFunction;

/**
 * A parcel: values written in order and read back in the same order, as Android lays them out (little-endian, each
 * value padded to 4 bytes; strings as UTF-16). Objects that cannot be flattened without their creator (Parcelables
 * written with writeParcelable, binders, file descriptors, Serializables) take a 4-byte slot that refers to the object
 * itself: reading gives the same instance back. marshall() keeps such objects in a process-wide table, so its bytes
 * unmarshall in the same page session only.
 */
public final class Parcel {
  // Parcel.writeValue type tags (as AOSP)
  private static final int VAL_NULL = -1, VAL_STRING = 0, VAL_INTEGER = 1, VAL_MAP = 2, VAL_BUNDLE = 3, VAL_PARCELABLE = 4,
      VAL_SHORT = 5, VAL_LONG = 6, VAL_FLOAT = 7, VAL_DOUBLE = 8, VAL_BOOLEAN = 9, VAL_CHARSEQUENCE = 10, VAL_LIST = 11,
      VAL_SPARSEARRAY = 12, VAL_BYTEARRAY = 13, VAL_STRINGARRAY = 14, VAL_IBINDER = 15, VAL_PARCELABLEARRAY = 16,
      VAL_OBJECTARRAY = 17, VAL_INTARRAY = 18, VAL_LONGARRAY = 19, VAL_BYTE = 20, VAL_SERIALIZABLE = 21,
      VAL_SPARSEBOOLEANARRAY = 22, VAL_BOOLEANARRAY = 23, VAL_CHARSEQUENCEARRAY = 24, VAL_PERSISTABLEBUNDLE = 25,
      VAL_SIZE = 26, VAL_SIZEF = 27, VAL_DOUBLEARRAY = 28, VAL_CHAR = 29, VAL_SHORTARRAY = 30, VAL_CHARARRAY = 31,
      VAL_FLOATARRAY = 32;
  // writeException codes (as AOSP)
  private static final int EX_SECURITY = -1, EX_BAD_PARCELABLE = -2, EX_ILLEGAL_ARGUMENT = -3, EX_NULL_POINTER = -4,
      EX_ILLEGAL_STATE = -5, EX_NETWORK_MAIN_THREAD = -6, EX_UNSUPPORTED_OPERATION = -7, EX_SERVICE_SPECIFIC = -8,
      EX_PARCELABLE = -9, EX_TRANSACTION_FAILED = -129;

  public static final Parcelable.Creator<String> STRING_CREATOR = new Parcelable.Creator<String>() {
    @Override public String createFromParcel(Parcel source) { return source.readString(); }
    @Override public String[] newArray(int size) { return new String[size]; }
  };

  private byte[] buf = new byte[64];
  private int size, pos;
  /** object slots: byte position -> object */
  private final HashMap<Integer, Object> objects = new HashMap<>();
  private static final HashMap<Integer, Object> MARSHALLED = new HashMap<>();
  private static int nextMarshalled = 1;

  private Parcel() {}
  public static Parcel obtain() { return new Parcel(); }
  public static Parcel obtain(IBinder binder) { return new Parcel(); }
  /** @removed API 1-20 */
  static Parcel obtain(int obj) { return new Parcel(); }
  public void recycle() { size = 0; pos = 0; objects.clear(); }

  public int dataSize() { return size; }
  public int dataAvail() { return Math.max(0, size - pos); }
  public int dataPosition() { return pos; }
  public int dataCapacity() { return buf.length; }
  public void setDataSize(int n) { ensure(n); if (n < size) objects.keySet().removeIf(p -> p >= n); size = n; if (pos > n) pos = n; }
  public void setDataPosition(int p) { if (p < 0) throw new IllegalArgumentException("Position " + p); pos = p; }
  public void setDataCapacity(int n) { if (n > buf.length) buf = java.util.Arrays.copyOf(buf, n); }
  public boolean hasFileDescriptors() { return hasFileDescriptors(0, size); }
  public boolean hasFileDescriptors(int offset, int length) {
    for (Map.Entry<Integer, Object> e : objects.entrySet())
      if (e.getKey() >= offset && e.getKey() < offset + length && (e.getValue() instanceof java.io.FileDescriptor || e.getValue() instanceof ParcelFileDescriptor)) return true;
    return false;
  }
  public void setPropagateAllowBlocking() {}

  public byte[] marshall() {
    byte[] out = java.util.Arrays.copyOf(buf, size);
    for (Map.Entry<Integer, Object> e : objects.entrySet()) {
      int id;
      synchronized (MARSHALLED) { id = nextMarshalled++; MARSHALLED.put(id, e.getValue()); }
      int p = e.getKey();
      out[p] = (byte) id; out[p + 1] = (byte) (id >> 8); out[p + 2] = (byte) (id >> 16); out[p + 3] = (byte) (0x80 | (id >> 24) & 0x7f);
    }
    return out;
  }
  public void unmarshall(byte[] data, int offset, int length) {
    recycle();
    ensure(length);
    System.arraycopy(data, offset, buf, 0, length);
    size = length;
    // object slots of marshall(): a marked id in the table
    for (int p = 0; p + 4 <= length; p += 4) {
      if ((buf[p + 3] & 0x80) == 0) continue;
      int id = buf[p] & 0xff | (buf[p + 1] & 0xff) << 8 | (buf[p + 2] & 0xff) << 16 | (buf[p + 3] & 0x7f) << 24;
      Object o;
      synchronized (MARSHALLED) { o = MARSHALLED.get(id); }
      if (o != null) objects.put(p, o);
    }
  }
  public void appendFrom(Parcel parcel, int offset, int length) {
    ensure(pos + length);
    System.arraycopy(parcel.buf, offset, buf, pos, length);
    for (Map.Entry<Integer, Object> e : parcel.objects.entrySet())
      if (e.getKey() >= offset && e.getKey() < offset + length) objects.put(pos + e.getKey() - offset, e.getValue());
    pos += length;
    if (pos > size) size = pos;
  }

  // ---------------------------------------------------------------- raw
  private void ensure(int n) { if (n > buf.length) buf = java.util.Arrays.copyOf(buf, Math.max(n, buf.length * 2)); }
  private void put32(int v) {
    ensure(pos + 4);
    buf[pos] = (byte) v; buf[pos + 1] = (byte) (v >> 8); buf[pos + 2] = (byte) (v >> 16); buf[pos + 3] = (byte) (v >> 24);
    objects.remove(pos);
    pos += 4;
    if (pos > size) size = pos;
  }
  private int get32() {
    if (pos + 4 > size) { pos = size; return 0; }
    int v = buf[pos] & 0xff | (buf[pos + 1] & 0xff) << 8 | (buf[pos + 2] & 0xff) << 16 | (buf[pos + 3] & 0xff) << 24;
    pos += 4;
    return v;
  }
  private void putObject(Object o) { int p = pos; put32(0); objects.put(p, o); }
  private Object getObject() { int p = pos; get32(); return objects.get(p); }

  // ---------------------------------------------------------------- primitives
  public void writeInt(int val) { put32(val); }
  public void writeLong(long val) { put32((int) val); put32((int) (val >>> 32)); }
  public void writeFloat(float val) { put32(Float.floatToRawIntBits(val)); }
  public void writeDouble(double val) { writeLong(Double.doubleToRawLongBits(val)); }
  public void writeByte(byte val) { put32(val); }
  public void writeBoolean(boolean val) { put32(val ? 1 : 0); }
  public int readInt() { return get32(); }
  public long readLong() { long lo = get32() & 0xffffffffL; return lo | (long) get32() << 32; }
  public float readFloat() { return Float.intBitsToFloat(get32()); }
  public double readDouble() { return Double.longBitsToDouble(readLong()); }
  public byte readByte() { return (byte) get32(); }
  public boolean readBoolean() { return get32() != 0; }

  // ---------------------------------------------------------------- strings
  /** writeString in UTF-8 on Android (hidden API); the same bytes here */
  public void writeString8(String val) { writeString(val); }
  public String readString8() { return readString(); }
  public void writeString16(String val) { writeString(val); }
  public String readString16() { return readString(); }
  public void writeString(String val) {
    if (val == null) { put32(-1); return; }
    int n = val.length();
    put32(n);
    int bytes = (n + 1) * 2, padded = bytes + 3 & ~3;
    ensure(pos + padded);
    for (int i = 0; i < n; i++) { char c = val.charAt(i); buf[pos + 2 * i] = (byte) c; buf[pos + 2 * i + 1] = (byte) (c >> 8); }
    for (int i = 2 * n; i < padded; i++) buf[pos + i] = 0;
    pos += padded;
    if (pos > size) size = pos;
  }
  public String readString() {
    int n = get32();
    if (n < 0) return null;
    int padded = (n + 1) * 2 + 3 & ~3;
    if (pos + padded > size) { pos = size; return null; }
    char[] cs = new char[n];
    for (int i = 0; i < n; i++) cs[i] = (char) (buf[pos + 2 * i] & 0xff | (buf[pos + 2 * i + 1] & 0xff) << 8);
    pos += padded;
    return new String(cs);
  }
  void writeCharSequence(CharSequence cs) {
    // styled text keeps its spans: written as an object
    if (cs == null || cs instanceof String) { put32(0); writeString((String) cs); }
    else { put32(1); putObject(cs); }
  }
  CharSequence readCharSequence() { return get32() == 0 ? readString() : (CharSequence) getObject(); }

  // ---------------------------------------------------------------- arrays
  public void writeByteArray(byte[] b) { writeByteArray(b, 0, b == null ? 0 : b.length); }
  public void writeByteArray(byte[] b, int offset, int len) {
    if (b == null) { put32(-1); return; }
    if (offset < 0 || len < 0 || offset + len > b.length) throw new ArrayIndexOutOfBoundsException();
    put32(len);
    int padded = len + 3 & ~3;
    ensure(pos + padded);
    System.arraycopy(b, offset, buf, pos, len);
    for (int i = len; i < padded; i++) buf[pos + i] = 0;
    pos += padded;
    if (pos > size) size = pos;
  }
  public byte[] createByteArray() {
    int n = get32();
    if (n < 0) return null;
    int padded = n + 3 & ~3;
    if (pos + padded > size) { pos = size; return null; }
    byte[] b = java.util.Arrays.copyOfRange(buf, pos, pos + n);
    pos += padded;
    return b;
  }
  public void readByteArray(byte[] val) { byte[] b = createByteArray(); check(val, b == null ? -1 : b.length); System.arraycopy(b, 0, val, 0, b.length); }
  public void writeBlob(byte[] b) { writeByteArray(b); }
  public void writeBlob(byte[] b, int offset, int len) { writeByteArray(b, offset, len); }
  public byte[] readBlob() { return createByteArray(); }
  private static void check(Object a, int n) {
    if (a == null || n < 0 || java.lang.reflect.Array.getLength(a) != n) throw new RuntimeException("bad array lengths");
  }

  public void writeIntArray(int[] a) { if (a == null) { put32(-1); return; } put32(a.length); for (int v : a) put32(v); }
  public int[] createIntArray() { int n = get32(); if (n < 0) return null; int[] a = new int[n]; for (int i = 0; i < n; i++) a[i] = get32(); return a; }
  public void readIntArray(int[] val) { int[] a = createIntArray(); check(val, a == null ? -1 : a.length); System.arraycopy(a, 0, val, 0, a.length); }
  public void writeLongArray(long[] a) { if (a == null) { put32(-1); return; } put32(a.length); for (long v : a) writeLong(v); }
  public long[] createLongArray() { int n = get32(); if (n < 0) return null; long[] a = new long[n]; for (int i = 0; i < n; i++) a[i] = readLong(); return a; }
  public void readLongArray(long[] val) { long[] a = createLongArray(); check(val, a == null ? -1 : a.length); System.arraycopy(a, 0, val, 0, a.length); }
  public void writeFloatArray(float[] a) { if (a == null) { put32(-1); return; } put32(a.length); for (float v : a) writeFloat(v); }
  public float[] createFloatArray() { int n = get32(); if (n < 0) return null; float[] a = new float[n]; for (int i = 0; i < n; i++) a[i] = readFloat(); return a; }
  public void readFloatArray(float[] val) { float[] a = createFloatArray(); check(val, a == null ? -1 : a.length); System.arraycopy(a, 0, val, 0, a.length); }
  public void writeDoubleArray(double[] a) { if (a == null) { put32(-1); return; } put32(a.length); for (double v : a) writeDouble(v); }
  public double[] createDoubleArray() { int n = get32(); if (n < 0) return null; double[] a = new double[n]; for (int i = 0; i < n; i++) a[i] = readDouble(); return a; }
  public void readDoubleArray(double[] val) { double[] a = createDoubleArray(); check(val, a == null ? -1 : a.length); System.arraycopy(a, 0, val, 0, a.length); }
  public void writeBooleanArray(boolean[] a) { if (a == null) { put32(-1); return; } put32(a.length); for (boolean v : a) put32(v ? 1 : 0); }
  public boolean[] createBooleanArray() { int n = get32(); if (n < 0) return null; boolean[] a = new boolean[n]; for (int i = 0; i < n; i++) a[i] = get32() != 0; return a; }
  public void readBooleanArray(boolean[] val) { boolean[] a = createBooleanArray(); check(val, a == null ? -1 : a.length); System.arraycopy(a, 0, val, 0, a.length); }
  public void writeCharArray(char[] a) { if (a == null) { put32(-1); return; } put32(a.length); for (char v : a) put32(v); }
  public char[] createCharArray() { int n = get32(); if (n < 0) return null; char[] a = new char[n]; for (int i = 0; i < n; i++) a[i] = (char) get32(); return a; }
  public void readCharArray(char[] val) { char[] a = createCharArray(); check(val, a == null ? -1 : a.length); System.arraycopy(a, 0, val, 0, a.length); }
  void writeShortArray(short[] a) { if (a == null) { put32(-1); return; } put32(a.length); for (short v : a) put32(v); }
  short[] createShortArray() { int n = get32(); if (n < 0) return null; short[] a = new short[n]; for (int i = 0; i < n; i++) a[i] = (short) get32(); return a; }
  public void writeStringArray(String[] a) { if (a == null) { put32(-1); return; } put32(a.length); for (String v : a) writeString(v); }
  public String[] createStringArray() { int n = get32(); if (n < 0) return null; String[] a = new String[n]; for (int i = 0; i < n; i++) a[i] = readString(); return a; }
  public void readStringArray(String[] val) { String[] a = createStringArray(); check(val, a == null ? -1 : a.length); System.arraycopy(a, 0, val, 0, a.length); }
  public void writeStringList(List<String> val) { if (val == null) { put32(-1); return; } put32(val.size()); for (String s : val) writeString(s); }
  public ArrayList<String> createStringArrayList() { int n = get32(); if (n < 0) return null; ArrayList<String> l = new ArrayList<>(n); for (int i = 0; i < n; i++) l.add(readString()); return l; }
  public void readStringList(List<String> list) { ArrayList<String> l = createStringArrayList(); list.clear(); if (l != null) list.addAll(l); }

  // ---------------------------------------------------------------- binders, interfaces, descriptors
  public void writeStrongBinder(IBinder val) { putObject(val); }
  public void writeStrongInterface(IInterface val) { putObject(val == null ? null : val.asBinder()); }
  public IBinder readStrongBinder() { return (IBinder) getObject(); }
  public void writeBinderArray(IBinder[] val) { if (val == null) { put32(-1); return; } put32(val.length); for (IBinder b : val) writeStrongBinder(b); }
  public IBinder[] createBinderArray() { int n = get32(); if (n < 0) return null; IBinder[] a = new IBinder[n]; for (int i = 0; i < n; i++) a[i] = readStrongBinder(); return a; }
  public void readBinderArray(IBinder[] val) { IBinder[] a = createBinderArray(); check(val, a == null ? -1 : a.length); System.arraycopy(a, 0, val, 0, a.length); }
  public void writeBinderList(List<IBinder> val) { if (val == null) { put32(-1); return; } put32(val.size()); for (IBinder b : val) writeStrongBinder(b); }
  public ArrayList<IBinder> createBinderArrayList() { int n = get32(); if (n < 0) return null; ArrayList<IBinder> l = new ArrayList<>(n); for (int i = 0; i < n; i++) l.add(readStrongBinder()); return l; }
  public void readBinderList(List<IBinder> list) { ArrayList<IBinder> l = createBinderArrayList(); list.clear(); if (l != null) list.addAll(l); }
  public <T extends IInterface> void writeInterfaceArray(T[] val) { if (val == null) { put32(-1); return; } put32(val.length); for (T t : val) writeStrongInterface(t); }
  public <T extends IInterface> T[] createInterfaceArray(IntFunction<T[]> newArray, Function<IBinder, T> asInterface) {
    int n = get32(); if (n < 0) return null;
    T[] a = newArray.apply(n);
    for (int i = 0; i < n; i++) a[i] = asInterface.apply(readStrongBinder());
    return a;
  }
  public <T extends IInterface> void readInterfaceArray(T[] val, Function<IBinder, T> asInterface) {
    int n = get32(); check(val, n);
    for (int i = 0; i < n; i++) val[i] = asInterface.apply(readStrongBinder());
  }
  public <T extends IInterface> void writeInterfaceList(List<T> val) { if (val == null) { put32(-1); return; } put32(val.size()); for (T t : val) writeStrongInterface(t); }
  public <T extends IInterface> ArrayList<T> createInterfaceArrayList(Function<IBinder, T> asInterface) {
    int n = get32(); if (n < 0) return null;
    ArrayList<T> l = new ArrayList<>(n);
    for (int i = 0; i < n; i++) l.add(asInterface.apply(readStrongBinder()));
    return l;
  }
  public <T extends IInterface> void readInterfaceList(List<T> list, Function<IBinder, T> asInterface) {
    ArrayList<T> l = createInterfaceArrayList(asInterface); list.clear(); if (l != null) list.addAll(l);
  }
  public void writeFileDescriptor(java.io.FileDescriptor val) { putObject(val); }
  public ParcelFileDescriptor readFileDescriptor() {
    Object o = getObject();
    if (o instanceof ParcelFileDescriptor p) return p;
    return null;   // a bare FileDescriptor: no ParcelFileDescriptor wraps it here
  }
  public void writeInterfaceToken(String interfaceName) { writeString(interfaceName); }
  public void enforceInterface(String interfaceName) {
    String s = readString();
    if (interfaceName == null ? s != null : !interfaceName.equals(s))
      throw new SecurityException("Binder invocation to an incorrect interface: expected " + interfaceName + ", got " + s);
  }
  public void enforceNoDataAvail() {
    int n = dataAvail();
    if (n > 0) throw new BadParcelableException("Parcel data not fully consumed, unread size: " + n);
  }

  // ---------------------------------------------------------------- exceptions
  public void writeNoException() { put32(0); }
  public void writeException(Exception e) {
    int code = e instanceof SecurityException ? EX_SECURITY : e instanceof BadParcelableException ? EX_BAD_PARCELABLE
        : e instanceof IllegalArgumentException ? EX_ILLEGAL_ARGUMENT : e instanceof NullPointerException ? EX_NULL_POINTER
        : e instanceof NetworkOnMainThreadException ? EX_NETWORK_MAIN_THREAD : e instanceof IllegalStateException ? EX_ILLEGAL_STATE
        : e instanceof UnsupportedOperationException ? EX_UNSUPPORTED_OPERATION : e instanceof Parcelable ? EX_PARCELABLE : 0;
    if (code == 0) {
      if (e instanceof RuntimeException r) throw r;
      throw new RuntimeException(e);
    }
    put32(code);
    writeString(e.getMessage());
    if (code == EX_PARCELABLE) putObject(e);
  }
  public void readException() { int code = get32(); if (code != 0) readException(code, code == EX_PARCELABLE ? null : readString()); }
  public void readException(int code, String msg) {
    switch (code) {
      case EX_SECURITY: throw new SecurityException(msg);
      case EX_BAD_PARCELABLE: throw new BadParcelableException(msg);
      case EX_ILLEGAL_ARGUMENT: throw new IllegalArgumentException(msg);
      case EX_NULL_POINTER: throw new NullPointerException(msg);
      case EX_ILLEGAL_STATE: throw new IllegalStateException(msg);
      case EX_NETWORK_MAIN_THREAD: throw new NetworkOnMainThreadException();
      case EX_UNSUPPORTED_OPERATION: throw new UnsupportedOperationException(msg);
      case EX_SERVICE_SPECIFIC: throw new IllegalStateException(msg);
      case EX_PARCELABLE: {
        readString();
        Object o = getObject();
        if (o instanceof RuntimeException r) throw r;
        throw new RuntimeException(String.valueOf(o));
      }
      case EX_TRANSACTION_FAILED: throw new IllegalStateException(msg);
      default: throw new RuntimeException("Unknown exception code: " + code + " msg " + msg);
    }
  }

  // ---------------------------------------------------------------- parcelables
  public void writeParcelable(Parcelable p, int flags) {
    if (p == null) { writeString(null); return; }
    writeString(p.getClass().getName());
    putObject(p);
  }
  public void writeParcelableCreator(Parcelable p) { writeString(p.getClass().getName()); putObject(p); }
  @SuppressWarnings("unchecked") public <T extends Parcelable> T readParcelable(ClassLoader loader) {
    String name = readString();
    return name == null ? null : (T) getObject();
  }
  @SuppressWarnings("unchecked") public <T> T readParcelable(ClassLoader loader, Class<T> clazz) {
    Object o = readParcelable(loader);
    if (o != null && clazz != null && !clazz.isInstance(o)) throw new BadParcelableException("Parcelable " + o.getClass().getName() + " is not a " + clazz.getName());
    return (T) o;
  }
  /** the creator of the parcelable written next (its own static CREATOR cannot be looked up: a creator returning it) */
  public Parcelable.Creator<?> readParcelableCreator(ClassLoader loader) {
    String name = readString();
    if (name == null) return null;
    Object o = getObject();
    return new Parcelable.Creator<Object>() {
      @Override public Object createFromParcel(Parcel source) { return o; }
      @Override public Object[] newArray(int size) { return new Parcelable[size]; }
    };
  }
  @SuppressWarnings("unchecked") public <T> Parcelable.Creator<T> readParcelableCreator(ClassLoader loader, Class<T> clazz) { return (Parcelable.Creator<T>) readParcelableCreator(loader); }
  public void writeParcelableArray(Parcelable[] value, int flags) {
    if (value == null) { put32(-1); return; }
    put32(value.length);
    for (Parcelable p : value) writeParcelable(p, flags);
  }
  public Parcelable[] readParcelableArray(ClassLoader loader) {
    int n = get32(); if (n < 0) return null;
    Parcelable[] a = new Parcelable[n];
    for (int i = 0; i < n; i++) a[i] = readParcelable(loader);
    return a;
  }
  @SuppressWarnings("unchecked") public <T> T[] readParcelableArray(ClassLoader loader, Class<T> clazz) { return (T[]) readParcelableArray(loader); }
  public <T extends Parcelable> void writeParcelableList(List<T> val, int flags) {
    if (val == null) { put32(-1); return; }
    put32(val.size());
    for (T p : val) writeParcelable(p, flags);
  }
  @SuppressWarnings("unchecked") public <T extends Parcelable> List<T> readParcelableList(List<T> list, ClassLoader cl) {
    int n = get32();
    if (n < 0) { list.clear(); return list; }
    list.clear();
    for (int i = 0; i < n; i++) list.add((T) readParcelable(cl));
    return list;
  }
  @SuppressWarnings("unchecked") public <T> List<T> readParcelableList(List<T> list, ClassLoader cl, Class<? extends T> clazz) {
    return (List<T>) readParcelableList((List<Parcelable>) list, cl);
  }

  // typed: flattened through writeToParcel and the creator (a copy, as on Android)
  public <T extends Parcelable> void writeTypedObject(T val, int flags) { if (val == null) put32(0); else { put32(1); val.writeToParcel(this, flags); } }
  public <T> T readTypedObject(Parcelable.Creator<T> c) { return get32() != 0 ? c.createFromParcel(this) : null; }
  public <T extends Parcelable> void writeTypedArray(T[] val, int flags) {
    if (val == null) { put32(-1); return; }
    put32(val.length);
    for (T t : val) writeTypedObject(t, flags);
  }
  public <T> T[] createTypedArray(Parcelable.Creator<T> c) {
    int n = get32(); if (n < 0) return null;
    T[] a = c.newArray(n);
    for (int i = 0; i < n; i++) a[i] = readTypedObject(c);
    return a;
  }
  public <T> void readTypedArray(T[] val, Parcelable.Creator<T> c) {
    int n = get32(); check(val, n);
    for (int i = 0; i < n; i++) val[i] = readTypedObject(c);
  }
  public <T extends Parcelable> void writeTypedList(List<T> val) { writeTypedList(val, 0); }
  public <T extends Parcelable> void writeTypedList(List<T> val, int flags) {
    if (val == null) { put32(-1); return; }
    put32(val.size());
    for (T t : val) writeTypedObject(t, flags);
  }
  public <T> ArrayList<T> createTypedArrayList(Parcelable.Creator<T> c) {
    int n = get32(); if (n < 0) return null;
    ArrayList<T> l = new ArrayList<>(n);
    for (int i = 0; i < n; i++) l.add(readTypedObject(c));
    return l;
  }
  public <T> void readTypedList(List<T> list, Parcelable.Creator<T> c) { ArrayList<T> l = createTypedArrayList(c); list.clear(); if (l != null) list.addAll(l); }
  public <T extends Parcelable> void writeTypedSparseArray(android.util.SparseArray<T> val, int flags) {
    if (val == null) { put32(-1); return; }
    put32(val.size());
    for (int i = 0; i < val.size(); i++) { put32(val.keyAt(i)); writeTypedObject(val.valueAt(i), flags); }
  }
  public <T> android.util.SparseArray<T> createTypedSparseArray(Parcelable.Creator<T> c) {
    int n = get32(); if (n < 0) return null;
    android.util.SparseArray<T> a = new android.util.SparseArray<>(n);
    for (int i = 0; i < n; i++) { int k = get32(); a.append(k, readTypedObject(c)); }
    return a;
  }
  public <T extends Parcelable> void writeTypedArrayMap(android.util.ArrayMap<String, T> val, int flags) {
    if (val == null) { put32(-1); return; }
    put32(val.size());
    for (int i = 0; i < val.size(); i++) { writeString(val.keyAt(i)); writeTypedObject(val.valueAt(i), flags); }
  }
  public <T> android.util.ArrayMap<String, T> createTypedArrayMap(Parcelable.Creator<T> c) {
    int n = get32(); if (n < 0) return null;
    android.util.ArrayMap<String, T> m = new android.util.ArrayMap<>(n);
    for (int i = 0; i < n; i++) { String k = readString(); m.put(k, readTypedObject(c)); }
    return m;
  }

  // ---------------------------------------------------------------- fixed-size (multi-dimensional) arrays, API 33
  public <T> void writeFixedArray(T val, int parcelableFlags, int... dimensions) {
    if (val == null) { put32(-1); return; }
    writeFixed(val, parcelableFlags, dimensions, 0);
  }
  private void writeFixed(Object v, int flags, int[] dims, int d) {
    int n = java.lang.reflect.Array.getLength(v);
    if (n != dims[d]) throw new BadParcelableException("bad length: expected " + dims[d] + ", but got " + n);
    put32(n);
    if (d < dims.length - 1) { for (Object x : (Object[]) v) writeFixed(x, flags, dims, d + 1); return; }
    if (v instanceof boolean[] a) for (boolean x : a) put32(x ? 1 : 0);
    else if (v instanceof byte[] a) for (byte x : a) put32(x);
    else if (v instanceof char[] a) for (char x : a) put32(x);
    else if (v instanceof int[] a) for (int x : a) put32(x);
    else if (v instanceof long[] a) for (long x : a) writeLong(x);
    else if (v instanceof float[] a) for (float x : a) writeFloat(x);
    else if (v instanceof double[] a) for (double x : a) writeDouble(x);
    else if (v instanceof IBinder[] a) for (IBinder x : a) writeStrongBinder(x);
    else if (v instanceof IInterface[] a) for (IInterface x : a) writeStrongInterface(x);
    else if (v instanceof Parcelable[] a) for (Parcelable x : a) writeTypedObject(x, flags);
    else throw new BadParcelableException("unsupported array type: " + v.getClass());
  }
  public <T> void readFixedArray(T val) { readFixed(val, null, null); }
  public <T, S extends IInterface> void readFixedArray(T val, Function<IBinder, S> asInterface) { readFixed(val, asInterface, null); }
  public <T, S extends Parcelable> void readFixedArray(T val, Parcelable.Creator<S> c) { readFixed(val, null, c); }
  @SuppressWarnings({"unchecked", "rawtypes"})
  private void readFixed(Object v, Function asInterface, Parcelable.Creator c) {
    int n = get32();
    if (n != java.lang.reflect.Array.getLength(v)) throw new BadParcelableException("Bad length: expected " + java.lang.reflect.Array.getLength(v) + ", but got " + n);
    if (v instanceof Object[] o && !(v instanceof IBinder[]) && !(v instanceof IInterface[]) && !(v instanceof Parcelable[])) {
      for (Object x : o) readFixed(x, asInterface, c);
      return;
    }
    for (int i = 0; i < n; i++) {
      if (v instanceof boolean[] a) a[i] = get32() != 0;
      else if (v instanceof byte[] a) a[i] = (byte) get32();
      else if (v instanceof char[] a) a[i] = (char) get32();
      else if (v instanceof int[] a) a[i] = get32();
      else if (v instanceof long[] a) a[i] = readLong();
      else if (v instanceof float[] a) a[i] = readFloat();
      else if (v instanceof double[] a) a[i] = readDouble();
      else if (v instanceof IBinder[] a) a[i] = readStrongBinder();
      else if (v instanceof IInterface[] a) a[i] = (IInterface) asInterface.apply(readStrongBinder());
      else if (v instanceof Parcelable[] a) a[i] = (Parcelable) readTypedObject(c);
    }
  }
  /** creates the array from the dimensions written: the component type of clazz must be primitive, IBinder or a Parcelable with c */
  public <T> T createFixedArray(Class<T> clazz, int... dimensions) { return createFixed(clazz, dimensions, 0, null, null); }
  public <T, S extends IInterface> T createFixedArray(Class<T> clazz, Function<IBinder, S> asInterface, int... dimensions) { return createFixed(clazz, dimensions, 0, asInterface, null); }
  public <T, S extends Parcelable> T createFixedArray(Class<T> clazz, Parcelable.Creator<S> c, int... dimensions) { return createFixed(clazz, dimensions, 0, null, c); }
  @SuppressWarnings({"unchecked", "rawtypes"})
  private <T> T createFixed(Class<?> clazz, int[] dims, int d, Function asInterface, Parcelable.Creator c) {
    int n = get32();
    if (n < 0) return null;
    if (n != dims[d]) throw new BadParcelableException("Bad length: expected " + dims[d] + ", but got " + n);
    Class<?> comp = clazz.getComponentType();
    if (d < dims.length - 1) {
      Object[] a = (Object[]) java.lang.reflect.Array.newInstance(comp, n);
      for (int i = 0; i < n; i++) a[i] = createFixed(comp, dims, d + 1, asInterface, c);
      return (T) a;
    }
    Object a = c != null ? c.newArray(n) : java.lang.reflect.Array.newInstance(comp, n);
    pos -= 4;   // let readFixed read the length again
    readFixed(a, asInterface, c);
    return (T) a;
  }

  // ---------------------------------------------------------------- sizes, sparse arrays, bundles
  public void writeSize(android.util.Size val) { put32(val.getWidth()); put32(val.getHeight()); }
  public android.util.Size readSize() { int w = get32(); return new android.util.Size(w, get32()); }
  public void writeSizeF(android.util.SizeF val) { writeFloat(val.getWidth()); writeFloat(val.getHeight()); }
  public android.util.SizeF readSizeF() { float w = readFloat(); return new android.util.SizeF(w, readFloat()); }
  public void writeSparseBooleanArray(android.util.SparseBooleanArray val) {
    if (val == null) { put32(-1); return; }
    put32(val.size());
    for (int i = 0; i < val.size(); i++) { put32(val.keyAt(i)); put32(val.valueAt(i) ? 1 : 0); }
  }
  public android.util.SparseBooleanArray readSparseBooleanArray() {
    int n = get32(); if (n < 0) return null;
    android.util.SparseBooleanArray a = new android.util.SparseBooleanArray(n);
    for (int i = 0; i < n; i++) { int k = get32(); a.put(k, get32() != 0); }
    return a;
  }
  public void writeSparseArray(android.util.SparseArray<Object> val) {
    if (val == null) { put32(-1); return; }
    put32(val.size());
    for (int i = 0; i < val.size(); i++) { put32(val.keyAt(i)); writeValue(val.valueAt(i)); }
  }
  @SuppressWarnings("unchecked") public <T> android.util.SparseArray<T> readSparseArray(ClassLoader loader) {
    int n = get32(); if (n < 0) return null;
    android.util.SparseArray<T> a = new android.util.SparseArray<>(n);
    for (int i = 0; i < n; i++) { int k = get32(); a.append(k, (T) readValue(loader)); }
    return a;
  }
  public <T> android.util.SparseArray<T> readSparseArray(ClassLoader loader, Class<? extends T> clazz) { return readSparseArray(loader); }
  public void writeBundle(Bundle val) { if (val == null) { put32(-1); return; } put32(1); val.writeMap(this); }
  public Bundle readBundle() { return readBundle(null); }
  public Bundle readBundle(ClassLoader loader) {
    if (get32() < 0) return null;
    Bundle b = new Bundle(loader);
    b.readMap(this, loader);
    return b;
  }
  public void writePersistableBundle(PersistableBundle val) { if (val == null) { put32(-1); return; } put32(1); val.writeMap(this); }
  public PersistableBundle readPersistableBundle() { return readPersistableBundle(null); }
  public PersistableBundle readPersistableBundle(ClassLoader loader) {
    if (get32() < 0) return null;
    PersistableBundle b = new PersistableBundle();
    b.readMap(this, loader);
    return b;
  }

  // ---------------------------------------------------------------- serializable, lists, maps, arrays, values
  public void writeSerializable(java.io.Serializable s) { if (s == null) { writeString(null); return; } writeString(s.getClass().getName()); putObject(s); }
  public java.io.Serializable readSerializable() { String n = readString(); return n == null ? null : (java.io.Serializable) getObject(); }
  @SuppressWarnings("unchecked") public <T> T readSerializable(ClassLoader loader, Class<T> clazz) { return (T) readSerializable(); }
  @SuppressWarnings("rawtypes") public void writeList(List val) { if (val == null) { put32(-1); return; } put32(val.size()); for (Object o : val) writeValue(o); }
  @SuppressWarnings({"rawtypes", "unchecked"}) public void readList(List outVal, ClassLoader loader) { int n = get32(); for (int i = 0; i < n; i++) outVal.add(readValue(loader)); }
  public <T> void readList(List<? super T> outVal, ClassLoader loader, Class<T> clazz) { readList((List) outVal, loader); }
  @SuppressWarnings("rawtypes") public ArrayList readArrayList(ClassLoader loader) { int n = get32(); if (n < 0) return null; ArrayList<Object> l = new ArrayList<>(n); for (int i = 0; i < n; i++) l.add(readValue(loader)); return l; }
  @SuppressWarnings("unchecked") public <T> ArrayList<T> readArrayList(ClassLoader loader, Class<? extends T> clazz) { return readArrayList(loader); }
  @SuppressWarnings("rawtypes") public void writeMap(Map val) {
    if (val == null) { put32(-1); return; }
    put32(val.size());
    for (Object o : val.entrySet()) { Map.Entry e = (Map.Entry) o; writeValue(e.getKey()); writeValue(e.getValue()); }
  }
  @SuppressWarnings({"rawtypes", "unchecked"}) public void readMap(Map outVal, ClassLoader loader) { int n = get32(); for (int i = 0; i < n; i++) { Object k = readValue(loader); outVal.put(k, readValue(loader)); } }
  public <K, V> void readMap(Map<? super K, ? super V> outVal, ClassLoader loader, Class<K> clazzKey, Class<V> clazzValue) { readMap((Map) outVal, loader); }
  @SuppressWarnings("rawtypes") public HashMap readHashMap(ClassLoader loader) { int n = get32(); if (n < 0) return null; HashMap<Object, Object> m = new HashMap<>(); for (int i = 0; i < n; i++) { Object k = readValue(loader); m.put(k, readValue(loader)); } return m; }
  @SuppressWarnings("unchecked") public <K, V> HashMap<K, V> readHashMap(ClassLoader loader, Class<? extends K> clazzKey, Class<? extends V> clazzValue) { return readHashMap(loader); }
  public void writeArray(Object[] val) { if (val == null) { put32(-1); return; } put32(val.length); for (Object o : val) writeValue(o); }
  public Object[] readArray(ClassLoader loader) { int n = get32(); if (n < 0) return null; Object[] a = new Object[n]; for (int i = 0; i < n; i++) a[i] = readValue(loader); return a; }
  @SuppressWarnings("unchecked") public <T> T[] readArray(ClassLoader loader, Class<T> clazz) {
    int n = get32(); if (n < 0) return null;
    T[] a = (T[]) java.lang.reflect.Array.newInstance(clazz, n);
    for (int i = 0; i < n; i++) a[i] = (T) readValue(loader);
    return a;
  }

  @SuppressWarnings("unchecked")
  public void writeValue(Object v) {
    if (v == null) put32(VAL_NULL);
    else if (v instanceof String s) { put32(VAL_STRING); writeString(s); }
    else if (v instanceof Integer i) { put32(VAL_INTEGER); put32(i); }
    else if (v instanceof Map<?, ?> m) { put32(VAL_MAP); writeMap(m); }
    else if (v instanceof Bundle b) { put32(VAL_BUNDLE); writeBundle(b); }
    else if (v instanceof PersistableBundle b) { put32(VAL_PERSISTABLEBUNDLE); writePersistableBundle(b); }
    else if (v instanceof android.util.SizeF z) { put32(VAL_SIZEF); writeSizeF(z); }
    else if (v instanceof android.util.Size z) { put32(VAL_SIZE); writeSize(z); }
    else if (v instanceof Parcelable p) { put32(VAL_PARCELABLE); writeParcelable(p, 0); }
    else if (v instanceof Short s) { put32(VAL_SHORT); put32(s); }
    else if (v instanceof Long l) { put32(VAL_LONG); writeLong(l); }
    else if (v instanceof Float f) { put32(VAL_FLOAT); writeFloat(f); }
    else if (v instanceof Double d) { put32(VAL_DOUBLE); writeDouble(d); }
    else if (v instanceof Boolean b) { put32(VAL_BOOLEAN); put32(b ? 1 : 0); }
    else if (v instanceof CharSequence cs) { put32(VAL_CHARSEQUENCE); writeCharSequence(cs); }
    else if (v instanceof List<?> l) { put32(VAL_LIST); writeList(l); }
    else if (v instanceof android.util.SparseArray<?> a) { put32(VAL_SPARSEARRAY); writeSparseArray((android.util.SparseArray<Object>) a); }
    else if (v instanceof boolean[] a) { put32(VAL_BOOLEANARRAY); writeBooleanArray(a); }
    else if (v instanceof byte[] a) { put32(VAL_BYTEARRAY); writeByteArray(a); }
    else if (v instanceof String[] a) { put32(VAL_STRINGARRAY); writeStringArray(a); }
    else if (v instanceof CharSequence[] a) { put32(VAL_CHARSEQUENCEARRAY); put32(a.length); for (CharSequence x : a) writeCharSequence(x); }
    else if (v instanceof IBinder b) { put32(VAL_IBINDER); writeStrongBinder(b); }
    else if (v instanceof Parcelable[] a) { put32(VAL_PARCELABLEARRAY); writeParcelableArray(a, 0); }
    else if (v instanceof int[] a) { put32(VAL_INTARRAY); writeIntArray(a); }
    else if (v instanceof long[] a) { put32(VAL_LONGARRAY); writeLongArray(a); }
    else if (v instanceof Byte b) { put32(VAL_BYTE); put32(b); }
    else if (v instanceof android.util.SparseBooleanArray a) { put32(VAL_SPARSEBOOLEANARRAY); writeSparseBooleanArray(a); }
    else if (v instanceof double[] a) { put32(VAL_DOUBLEARRAY); writeDoubleArray(a); }
    else if (v instanceof Character c) { put32(VAL_CHAR); put32(c); }
    else if (v instanceof short[] a) { put32(VAL_SHORTARRAY); writeShortArray(a); }
    else if (v instanceof char[] a) { put32(VAL_CHARARRAY); writeCharArray(a); }
    else if (v instanceof float[] a) { put32(VAL_FLOATARRAY); writeFloatArray(a); }
    else if (v instanceof Object[] a) { put32(VAL_OBJECTARRAY); writeArray(a); }
    else if (v instanceof java.io.Serializable s) { put32(VAL_SERIALIZABLE); writeSerializable(s); }
    else throw new RuntimeException("Parcel: unable to marshal value " + v);
  }
  public Object readValue(ClassLoader loader) {
    int t = get32();
    switch (t) {
      case VAL_NULL: return null;
      case VAL_STRING: return readString();
      case VAL_INTEGER: return get32();
      case VAL_MAP: return readHashMap(loader);
      case VAL_BUNDLE: return readBundle(loader);
      case VAL_PERSISTABLEBUNDLE: return readPersistableBundle(loader);
      case VAL_SIZE: return readSize();
      case VAL_SIZEF: return readSizeF();
      case VAL_PARCELABLE: return readParcelable(loader);
      case VAL_SHORT: return (short) get32();
      case VAL_LONG: return readLong();
      case VAL_FLOAT: return readFloat();
      case VAL_DOUBLE: return readDouble();
      case VAL_BOOLEAN: return get32() != 0;
      case VAL_CHARSEQUENCE: return readCharSequence();
      case VAL_LIST: return readArrayList(loader);
      case VAL_SPARSEARRAY: return readSparseArray(loader);
      case VAL_BOOLEANARRAY: return createBooleanArray();
      case VAL_BYTEARRAY: return createByteArray();
      case VAL_STRINGARRAY: return createStringArray();
      case VAL_CHARSEQUENCEARRAY: { int n = get32(); if (n < 0) return null; CharSequence[] a = new CharSequence[n]; for (int i = 0; i < n; i++) a[i] = readCharSequence(); return a; }
      case VAL_IBINDER: return readStrongBinder();
      case VAL_PARCELABLEARRAY: return readParcelableArray(loader);
      case VAL_INTARRAY: return createIntArray();
      case VAL_LONGARRAY: return createLongArray();
      case VAL_BYTE: return (byte) get32();
      case VAL_SPARSEBOOLEANARRAY: return readSparseBooleanArray();
      case VAL_DOUBLEARRAY: return createDoubleArray();
      case VAL_CHAR: return (char) get32();
      case VAL_SHORTARRAY: return createShortArray();
      case VAL_CHARARRAY: return createCharArray();
      case VAL_FLOATARRAY: return createFloatArray();
      case VAL_OBJECTARRAY: return readArray(loader);
      case VAL_SERIALIZABLE: return readSerializable();
      default: throw new BadParcelableException("Parcel: unmarshalling unknown type code " + t + " at offset " + (pos - 4));
    }
  }
  @SuppressWarnings("unchecked") public <T> T readValue(ClassLoader loader, Class<T> clazz, Class<?>... itemTypes) { return (T) readValue(loader); }
}
