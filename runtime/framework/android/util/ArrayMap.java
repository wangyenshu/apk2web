package android.util;

import java.util.*;

/** android.util.ArrayMap: a map with index access (keyAt/valueAt); entries are kept in insertion order. */
public final class ArrayMap<K, V> extends AbstractMap<K, V> implements Map<K, V> {
  final ArrayList<K> keys = new ArrayList<>();
  final ArrayList<V> values = new ArrayList<>();
  public ArrayMap() {}
  public ArrayMap(int capacity) { keys.ensureCapacity(capacity); values.ensureCapacity(capacity); }
  public ArrayMap(ArrayMap<K, V> map) { if (map != null) putAll((Map<K, V>) map); }
  public ArrayMap(Map<K, V> map) { if (map != null) putAll(map); }
  public int indexOfKey(Object key) { return keys.indexOf(key); }
  public int indexOfValue(Object value) { return values.indexOf(value); }
  public K keyAt(int index) { return keys.get(index); }
  public V valueAt(int index) { return values.get(index); }
  public V setValueAt(int index, V value) { return values.set(index, value); }
  public V removeAt(int index) { keys.remove(index); return values.remove(index); }
  public boolean removeAll(Collection<?> c) { boolean r = false; for (Object k : c) if (containsKey(k)) { remove(k); r = true; } return r; }
  public boolean retainAll(Collection<?> c) { boolean r = false; for (int i = keys.size() - 1; i >= 0; i--) if (!c.contains(keys.get(i))) { removeAt(i); r = true; } return r; }
  public boolean containsAll(Collection<?> c) { for (Object k : c) if (!containsKey(k)) return false; return true; }
  public void ensureCapacity(int n) { keys.ensureCapacity(n); values.ensureCapacity(n); }
  public void putAll(ArrayMap<? extends K, ? extends V> a) { for (int i = 0; i < a.size(); i++) put(a.keyAt(i), a.valueAt(i)); }
  public void append(K key, V value) { put(key, value); }
  public void erase() { clear(); }
  public boolean isEmpty() { return keys.isEmpty(); }
  @Override public int size() { return keys.size(); }
  @Override public boolean containsKey(Object key) { return keys.contains(key); }
  @Override public boolean containsValue(Object value) { return values.contains(value); }
  @Override public V get(Object key) { int i = keys.indexOf(key); return i < 0 ? null : values.get(i); }
  @Override public V put(K key, V value) {
    int i = keys.indexOf(key);
    if (i >= 0) return values.set(i, value);
    keys.add(key); values.add(value);
    return null;
  }
  @Override public V remove(Object key) { int i = keys.indexOf(key); return i < 0 ? null : removeAt(i); }
  @Override public void clear() { keys.clear(); values.clear(); }
  @Override public Set<Entry<K, V>> entrySet() {
    return new AbstractSet<Entry<K, V>>() {
      @Override public int size() { return keys.size(); }
      @Override public Iterator<Entry<K, V>> iterator() {
        return new Iterator<Entry<K, V>>() {
          int i = 0;
          boolean removable;
          @Override public boolean hasNext() { return i < keys.size(); }
          @Override public Entry<K, V> next() {
            if (i >= keys.size()) throw new NoSuchElementException();
            final int at = i++;
            removable = true;
            return new Entry<K, V>() {
              public K getKey() { return keys.get(at); }
              public V getValue() { return values.get(at); }
              public V setValue(V v) { return values.set(at, v); }
              @Override public boolean equals(Object o) { return o instanceof Entry && Objects.equals(getKey(), ((Entry<?, ?>) o).getKey()) && Objects.equals(getValue(), ((Entry<?, ?>) o).getValue()); }
              @Override public int hashCode() { return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue()); }
              @Override public String toString() { return getKey() + "=" + getValue(); }
            };
          }
          @Override public void remove() { if (!removable) throw new IllegalStateException(); removeAt(--i); removable = false; }
        };
      }
    };
  }
}
