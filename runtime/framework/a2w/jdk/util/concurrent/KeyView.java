package a2w.jdk.util.concurrent;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;

/** ConcurrentSkipListMap.keySet(): a NavigableSet over the map, iterating the map's plain key set */
final class KeyView<K> extends java.util.AbstractSet<K> implements NavigableSet<K> {
  private final ConcurrentSkipListMap<K, ?> m;
  private final Set<K> keys;
  KeyView(ConcurrentSkipListMap<K, ?> m, Set<K> keys) { this.m = m; this.keys = keys; }
  @Override public Iterator<K> iterator() { return keys.iterator(); }
  @Override public int size() { return m.size(); }
  @Override public boolean contains(Object o) { return m.containsKey(o); }
  @Override public boolean remove(Object o) { boolean had = m.containsKey(o); m.remove(o); return had; }
  @Override public void clear() { m.clear(); }
  @Override public Comparator<? super K> comparator() { return m.comparator(); }
  @Override public K first() { return m.firstKey(); }
  @Override public K last() { return m.lastKey(); }
  @Override public K lower(K k) { return m.lowerKey(k); }
  @Override public K floor(K k) { return m.floorKey(k); }
  @Override public K ceiling(K k) { return m.ceilingKey(k); }
  @Override public K higher(K k) { return m.higherKey(k); }
  @Override public K pollFirst() { Map.Entry<K, ?> e = m.pollFirstEntry(); return e == null ? null : e.getKey(); }
  @Override public K pollLast() { Map.Entry<K, ?> e = m.pollLastEntry(); return e == null ? null : e.getKey(); }
  @Override public NavigableSet<K> descendingSet() { return m.descendingKeySet(); }
  @Override public Iterator<K> descendingIterator() { return m.descendingKeySet().iterator(); }
  @Override public NavigableSet<K> subSet(K f, boolean fi, K t, boolean ti) { return m.subMap(f, fi, t, ti).navigableKeySet(); }
  @Override public NavigableSet<K> headSet(K t, boolean i) { return m.headMap(t, i).navigableKeySet(); }
  @Override public NavigableSet<K> tailSet(K f, boolean i) { return m.tailMap(f, i).navigableKeySet(); }
  @Override public NavigableSet<K> subSet(K f, K t) { return subSet(f, true, t, false); }
  @Override public NavigableSet<K> headSet(K t) { return headSet(t, false); }
  @Override public NavigableSet<K> tailSet(K f) { return tailSet(f, true); }
}
