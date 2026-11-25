package android.os;

import java.io.Serializable;
import java.util.ArrayList;

/** A map from String keys to values of the types Parcel can write. */
public final class Bundle extends BaseBundle implements Parcelable, Cloneable {
  public static final Bundle EMPTY = new Bundle();
  public static final Parcelable.Creator<Bundle> CREATOR = new Parcelable.Creator<Bundle>() {
    @Override public Bundle createFromParcel(Parcel in) { return in.readBundle(); }
    @Override public Bundle[] newArray(int size) { return new Bundle[size]; }
  };

  public Bundle() {}
  public Bundle(Bundle b) { super(b); }
  public Bundle(PersistableBundle b) { super(b); }
  public Bundle(ClassLoader loader) { this.loader = loader; }
  public Bundle(int capacity) {}

  @Override public Object clone() { return new Bundle(this); }
  /** copies nested bundles, lists and primitive arrays; other values are shared */
  public Bundle deepCopy() {
    Bundle b = new Bundle();
    b.loader = loader;
    for (java.util.Map.Entry<String, Object> e : map.entrySet()) b.map.put(e.getKey(), deepCopyValue(e.getValue()));
    return b;
  }
  public void putAll(Bundle bundle) { if (bundle != null) map.putAll(bundle.map); }
  public ClassLoader getClassLoader() { return loader; }
  public void setClassLoader(ClassLoader loader) { this.loader = loader; }
  public boolean hasFileDescriptors() {
    for (Object v : map.values()) if (v instanceof ParcelFileDescriptor || v instanceof java.io.FileDescriptor || v instanceof Bundle b && b.hasFileDescriptors()) return true;
    return false;
  }

  public void putByte(String key, byte value) { map.put(key, value); }
  public void putChar(String key, char value) { map.put(key, value); }
  public void putShort(String key, short value) { map.put(key, value); }
  public void putFloat(String key, float value) { map.put(key, value); }
  public void putCharSequence(String key, CharSequence value) { map.put(key, value); }
  public void putParcelable(String key, Parcelable value) { map.put(key, value); }
  public void putSize(String key, android.util.Size value) { map.put(key, value); }
  public void putSizeF(String key, android.util.SizeF value) { map.put(key, value); }
  public void putParcelableArray(String key, Parcelable[] value) { map.put(key, value); }
  public void putParcelableArrayList(String key, ArrayList<? extends Parcelable> value) { map.put(key, value); }
  public void putSparseParcelableArray(String key, android.util.SparseArray<? extends Parcelable> value) { map.put(key, value); }
  public void putIntegerArrayList(String key, ArrayList<Integer> value) { map.put(key, value); }
  public void putStringArrayList(String key, ArrayList<String> value) { map.put(key, value); }
  public void putCharSequenceArrayList(String key, ArrayList<CharSequence> value) { map.put(key, value); }
  public void putSerializable(String key, Serializable value) { map.put(key, value); }
  public void putByteArray(String key, byte[] value) { map.put(key, value); }
  public void putShortArray(String key, short[] value) { map.put(key, value); }
  public void putCharArray(String key, char[] value) { map.put(key, value); }
  public void putFloatArray(String key, float[] value) { map.put(key, value); }
  public void putCharSequenceArray(String key, CharSequence[] value) { map.put(key, value); }
  public void putBundle(String key, Bundle value) { map.put(key, value); }
  public void putBinder(String key, IBinder value) { map.put(key, value); }

  public byte getByte(String key) { return getByte(key, (byte) 0); }
  public Byte getByte(String key, byte defaultValue) { Object o = map.get(key); return o instanceof Byte ? (Byte) o : Byte.valueOf(defaultValue); }
  public char getChar(String key) { return getChar(key, (char) 0); }
  public char getChar(String key, char defaultValue) { Object o = map.get(key); return o instanceof Character ? (Character) o : defaultValue; }
  public short getShort(String key) { return getShort(key, (short) 0); }
  public short getShort(String key, short defaultValue) { Object o = map.get(key); return o instanceof Short ? (Short) o : defaultValue; }
  public float getFloat(String key) { return getFloat(key, 0f); }
  public float getFloat(String key, float defaultValue) { Object o = map.get(key); return o instanceof Float ? (Float) o : defaultValue; }
  public CharSequence getCharSequence(String key) { Object o = map.get(key); return o instanceof CharSequence ? (CharSequence) o : null; }
  public CharSequence getCharSequence(String key, CharSequence defaultValue) { CharSequence c = getCharSequence(key); return c == null ? defaultValue : c; }
  public android.util.Size getSize(String key) { Object o = map.get(key); return o instanceof android.util.Size ? (android.util.Size) o : null; }
  public android.util.SizeF getSizeF(String key) { Object o = map.get(key); return o instanceof android.util.SizeF ? (android.util.SizeF) o : null; }
  public Bundle getBundle(String key) { Object o = map.get(key); return o instanceof Bundle ? (Bundle) o : null; }
  @SuppressWarnings("unchecked") public <T extends Parcelable> T getParcelable(String key) { Object o = map.get(key); return o instanceof Parcelable ? (T) o : null; }
  public <T> T getParcelable(String key, Class<T> clazz) { return typed(key, clazz); }
  public Parcelable[] getParcelableArray(String key) { Object o = map.get(key); return o instanceof Parcelable[] ? (Parcelable[]) o : null; }
  @SuppressWarnings("unchecked") public <T> T[] getParcelableArray(String key, Class<T> clazz) { Object o = map.get(key); return o instanceof Parcelable[] ? (T[]) o : null; }
  public <T extends Parcelable> ArrayList<T> getParcelableArrayList(String key) { return list(key); }
  public <T> ArrayList<T> getParcelableArrayList(String key, Class<? extends T> clazz) { return list(key); }
  @SuppressWarnings("unchecked") public <T extends Parcelable> android.util.SparseArray<T> getSparseParcelableArray(String key) {
    Object o = map.get(key); return o instanceof android.util.SparseArray ? (android.util.SparseArray<T>) o : null;
  }
  @SuppressWarnings("unchecked") public <T> android.util.SparseArray<T> getSparseParcelableArray(String key, Class<? extends T> clazz) {
    Object o = map.get(key); return o instanceof android.util.SparseArray ? (android.util.SparseArray<T>) o : null;
  }
  public Serializable getSerializable(String key) { Object o = map.get(key); return o instanceof Serializable ? (Serializable) o : null; }
  public <T extends Serializable> T getSerializable(String key, Class<T> clazz) { return typed(key, clazz); }
  public ArrayList<Integer> getIntegerArrayList(String key) { return list(key); }
  public ArrayList<String> getStringArrayList(String key) { return list(key); }
  public ArrayList<CharSequence> getCharSequenceArrayList(String key) { return list(key); }
  public byte[] getByteArray(String key) { Object o = map.get(key); return o instanceof byte[] ? (byte[]) o : null; }
  public short[] getShortArray(String key) { Object o = map.get(key); return o instanceof short[] ? (short[]) o : null; }
  public char[] getCharArray(String key) { Object o = map.get(key); return o instanceof char[] ? (char[]) o : null; }
  public float[] getFloatArray(String key) { Object o = map.get(key); return o instanceof float[] ? (float[]) o : null; }
  public CharSequence[] getCharSequenceArray(String key) { Object o = map.get(key); return o instanceof CharSequence[] ? (CharSequence[]) o : null; }
  public IBinder getBinder(String key) { Object o = map.get(key); return o instanceof IBinder ? (IBinder) o : null; }

  @Override public int describeContents() { return hasFileDescriptors() ? Parcelable.CONTENTS_FILE_DESCRIPTOR : 0; }
  @Override public void writeToParcel(Parcel p, int flags) { writeMap(p); }
  public void readFromParcel(Parcel p) { map.clear(); readMap(p, loader); }
  @Override public String toString() { return "Bundle[" + map + "]"; }
}
