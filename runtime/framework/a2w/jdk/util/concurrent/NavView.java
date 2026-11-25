package a2w.jdk.util.concurrent;

import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.Set;

/** a view of a NavigableMap (a range or the descending order of a ConcurrentSkipListMap) as a ConcurrentNavigableMap */
final class NavView<K, V> extends java.util.AbstractMap<K, V> implements ConcurrentNavigableMap<K, V> {
  private final NavigableMap<K, V> d;
  NavView(NavigableMap<K, V> d) { this.d = d; }
  @Override public Set<Map.Entry<K, V>> entrySet() { return d.entrySet(); }
  @Override public int size() { return d.size(); }
  @Override public boolean containsKey(Object k) { return d.containsKey(k); }
  @Override public V get(Object k) { return d.get(k); }
  @Override public V put(K k, V v) { return d.put(k, v); }
  @Override public V remove(Object k) { return d.remove(k); }
  @Override public void clear() { d.clear(); }
  @Override public V putIfAbsent(K k, V v) { V o = d.get(k); if (o == null) d.put(k, v); return o; }
  @Override public boolean remove(Object k, Object v) { if (d.containsKey(k) && Objects.equals(d.get(k), v)) { d.remove(k); return true; } return false; }
  @Override public boolean replace(K k, V o, V v) { if (d.containsKey(k) && Objects.equals(d.get(k), o)) { d.put(k, v); return true; } return false; }
  @Override public V replace(K k, V v) { return d.containsKey(k) ? d.put(k, v) : null; }
  @Override public Comparator<? super K> comparator() { return d.comparator(); }
  @Override public K firstKey() { return d.firstKey(); }
  @Override public K lastKey() { return d.lastKey(); }
  @Override public Map.Entry<K, V> lowerEntry(K k) { return d.lowerEntry(k); }
  @Override public K lowerKey(K k) { return d.lowerKey(k); }
  @Override public Map.Entry<K, V> floorEntry(K k) { return d.floorEntry(k); }
  @Override public K floorKey(K k) { return d.floorKey(k); }
  @Override public Map.Entry<K, V> ceilingEntry(K k) { return d.ceilingEntry(k); }
  @Override public K ceilingKey(K k) { return d.ceilingKey(k); }
  @Override public Map.Entry<K, V> higherEntry(K k) { return d.higherEntry(k); }
  @Override public K higherKey(K k) { return d.higherKey(k); }
  @Override public Map.Entry<K, V> firstEntry() { return d.firstEntry(); }
  @Override public Map.Entry<K, V> lastEntry() { return d.lastEntry(); }
  @Override public Map.Entry<K, V> pollFirstEntry() { return d.pollFirstEntry(); }
  @Override public Map.Entry<K, V> pollLastEntry() { return d.pollLastEntry(); }
  @Override public ConcurrentNavigableMap<K, V> descendingMap() { return new NavView<>(d.descendingMap()); }
  @Override public NavigableSet<K> navigableKeySet() { return d.navigableKeySet(); }
  @Override public NavigableSet<K> keySet() { return d.navigableKeySet(); }
  @Override public NavigableSet<K> descendingKeySet() { return d.descendingKeySet(); }
  @Override public ConcurrentNavigableMap<K, V> subMap(K f, boolean fi, K t, boolean ti) { return new NavView<>(d.subMap(f, fi, t, ti)); }
  @Override public ConcurrentNavigableMap<K, V> headMap(K t, boolean i) { return new NavView<>(d.headMap(t, i)); }
  @Override public ConcurrentNavigableMap<K, V> tailMap(K f, boolean i) { return new NavView<>(d.tailMap(f, i)); }
  @Override public ConcurrentNavigableMap<K, V> subMap(K f, K t) { return subMap(f, true, t, false); }
  @Override public ConcurrentNavigableMap<K, V> headMap(K t) { return headMap(t, false); }
  @Override public ConcurrentNavigableMap<K, V> tailMap(K f) { return tailMap(f, true); }
}
