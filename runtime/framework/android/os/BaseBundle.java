package android.os;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/** The key-value map of Bundle and PersistableBundle. */
public class BaseBundle {
  final HashMap<String, Object> map = new HashMap<>();
  ClassLoader loader;

  BaseBundle() {}
  BaseBundle(BaseBundle b) { if (b != null) { map.putAll(b.map); loader = b.loader; } }

  /** the entries (framework use) */
  public HashMap<String, Object> map() { return map; }

  public int size() { return map.size(); }
  public boolean isEmpty() { return map.isEmpty(); }
  public void clear() { map.clear(); }
  public boolean containsKey(String key) { return map.containsKey(key); }
  /** @deprecated typed getters check the type; this does not */
  @Deprecated public Object get(String key) { return map.get(key); }
  public void remove(String key) { map.remove(key); }
  public void putAll(PersistableBundle bundle) { if (bundle != null) map.putAll(bundle.map); }
  public Set<String> keySet() { return map.keySet(); }

  public void putBoolean(String key, boolean value) { map.put(key, value); }
  public void putInt(String key, int value) { map.put(key, value); }
  public void putLong(String key, long value) { map.put(key, value); }
  public void putDouble(String key, double value) { map.put(key, value); }
  public void putString(String key, String value) { map.put(key, value); }
  public void putBooleanArray(String key, boolean[] value) { map.put(key, value); }
  public void putIntArray(String key, int[] value) { map.put(key, value); }
  public void putLongArray(String key, long[] value) { map.put(key, value); }
  public void putDoubleArray(String key, double[] value) { map.put(key, value); }
  public void putStringArray(String key, String[] value) { map.put(key, value); }

  public boolean getBoolean(String key) { return getBoolean(key, false); }
  public boolean getBoolean(String key, boolean defaultValue) { Object o = map.get(key); return o instanceof Boolean ? (Boolean) o : defaultValue; }
  public int getInt(String key) { return getInt(key, 0); }
  public int getInt(String key, int defaultValue) { Object o = map.get(key); return o instanceof Integer ? (Integer) o : defaultValue; }
  public long getLong(String key) { return getLong(key, 0L); }
  public long getLong(String key, long defaultValue) { Object o = map.get(key); return o instanceof Long ? (Long) o : defaultValue; }
  public double getDouble(String key) { return getDouble(key, 0.0); }
  public double getDouble(String key, double defaultValue) { Object o = map.get(key); return o instanceof Double ? (Double) o : defaultValue; }
  public String getString(String key) { Object o = map.get(key); return o instanceof String ? (String) o : null; }
  public String getString(String key, String defaultValue) { String s = getString(key); return s == null ? defaultValue : s; }
  public boolean[] getBooleanArray(String key) { Object o = map.get(key); return o instanceof boolean[] ? (boolean[]) o : null; }
  public int[] getIntArray(String key) { Object o = map.get(key); return o instanceof int[] ? (int[]) o : null; }
  public long[] getLongArray(String key) { Object o = map.get(key); return o instanceof long[] ? (long[]) o : null; }
  public double[] getDoubleArray(String key) { Object o = map.get(key); return o instanceof double[] ? (double[]) o : null; }
  public String[] getStringArray(String key) { Object o = map.get(key); return o instanceof String[] ? (String[]) o : null; }

  // ---- shared by Bundle (typed access to the other value kinds)
  @SuppressWarnings("unchecked") <T> ArrayList<T> list(String key) { Object o = map.get(key); return o instanceof ArrayList ? (ArrayList<T>) o : null; }
  <T> T typed(String key, Class<T> c) { Object o = map.get(key); return o != null && c.isInstance(o) ? c.cast(o) : null; }

  /** the entries written as a parcel: count, then key and value (Parcel.writeValue) */
  void writeMap(Parcel p) {
    p.writeInt(map.size());
    for (java.util.Map.Entry<String, Object> e : map.entrySet()) { p.writeString(e.getKey()); p.writeValue(e.getValue()); }
  }
  void readMap(Parcel p, ClassLoader l) {
    int n = p.readInt();
    for (int i = 0; i < n; i++) { String k = p.readString(); map.put(k, p.readValue(l)); }
  }
  static Object deepCopyValue(Object v) {
    if (v instanceof Bundle b) return b.deepCopy();
    if (v instanceof PersistableBundle b) return b.deepCopy();
    if (v instanceof int[] a) return a.clone();
    if (v instanceof long[] a) return a.clone();
    if (v instanceof double[] a) return a.clone();
    if (v instanceof boolean[] a) return a.clone();
    if (v instanceof String[] a) return a.clone();
    if (v instanceof ArrayList<?> l) {
      ArrayList<Object> c = new ArrayList<>(l.size());
      for (Object x : l) c.add(deepCopyValue(x));
      return c;
    }
    return v;
  }
}
