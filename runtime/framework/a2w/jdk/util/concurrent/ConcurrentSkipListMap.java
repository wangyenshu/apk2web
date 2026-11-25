package a2w.jdk.util.concurrent;

import java.util.Comparator;
import java.util.Map;
import java.util.NavigableSet;

/** java.util.concurrent.ConcurrentSkipListMap over a TreeMap (TeaVM lacks it; the app's threads do not run in parallel) */
public class ConcurrentSkipListMap<K, V> extends java.util.TreeMap<K, V> implements ConcurrentNavigableMap<K, V> {
  public ConcurrentSkipListMap() {}
  public ConcurrentSkipListMap(Comparator<? super K> c) { super(c); }
  public ConcurrentSkipListMap(Map<? extends K, ? extends V> m) { super(m); }
  public ConcurrentSkipListMap(java.util.SortedMap<K, ? extends V> m) { super(m); }
  @Override public V putIfAbsent(K k, V v) { V o = get(k); if (o == null) put(k, v); return o; }
  @Override public boolean remove(Object k, Object v) { if (containsKey(k) && java.util.Objects.equals(get(k), v)) { remove(k); return true; } return false; }
  @Override public boolean replace(K k, V o, V v) { if (containsKey(k) && java.util.Objects.equals(get(k), o)) { put(k, v); return true; } return false; }
  @Override public V replace(K k, V v) { return containsKey(k) ? put(k, v) : null; }
  // the SDK's return types (ConcurrentNavigableMap, NavigableSet): calls compiled against it use these descriptors
  @Override public ConcurrentSkipListMap<K, V> clone() { ConcurrentSkipListMap<K, V> c = new ConcurrentSkipListMap<>(comparator()); c.putAll(this); return c; }
  @Override public ConcurrentNavigableMap<K, V> descendingMap() { return new NavView<>(super.descendingMap()); }
  // (not navigableKeySet(): TeaVM's key set iterates through keySet())
  @Override public NavigableSet<K> keySet() { return new KeyView<>(this, super.keySet()); }
  @Override public ConcurrentNavigableMap<K, V> subMap(K f, boolean fi, K t, boolean ti) { return new NavView<>(super.subMap(f, fi, t, ti)); }
  @Override public ConcurrentNavigableMap<K, V> headMap(K t, boolean i) { return new NavView<>(super.headMap(t, i)); }
  @Override public ConcurrentNavigableMap<K, V> tailMap(K f, boolean i) { return new NavView<>(super.tailMap(f, i)); }
  @Override public ConcurrentNavigableMap<K, V> subMap(K f, K t) { return subMap(f, true, t, false); }
  @Override public ConcurrentNavigableMap<K, V> headMap(K t) { return headMap(t, false); }
  @Override public ConcurrentNavigableMap<K, V> tailMap(K f) { return tailMap(f, true); }
}
