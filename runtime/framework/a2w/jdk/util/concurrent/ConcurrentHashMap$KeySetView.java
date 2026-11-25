package a2w.jdk.util.concurrent;

import java.util.*;

/** java.util.concurrent.ConcurrentHashMap.KeySetView: the keys of a map as a set; add puts the view's value (a view
 *  made by keySet(V) or newKeySet()). */
public final class ConcurrentHashMap$KeySetView<K, V> implements Collection<K>, java.io.Serializable, Set<K> {
  private final java.util.concurrent.ConcurrentHashMap<K, V> map;
  private final V value;
  /** (not part of the API: made by a2w.jdk.Compat for ConcurrentHashMap.keySet(V) and newKeySet) */
  public ConcurrentHashMap$KeySetView(java.util.concurrent.ConcurrentHashMap<K, V> map, V value) { this.map = map; this.value = value; }
  public java.util.concurrent.ConcurrentHashMap<K, V> getMap() { return map; }
  public V getMappedValue() { return value; }
  public boolean add(K e) { if (value == null) throw new UnsupportedOperationException(); return map.putIfAbsent(e, value) == null; }
  public boolean addAll(Collection<? extends K> c) {
    if (value == null) throw new UnsupportedOperationException();
    boolean changed = false;
    for (K e : c) if (map.putIfAbsent(e, value) == null) changed = true;
    return changed;
  }
  public final void clear() { map.clear(); }
  public boolean contains(Object o) { return map.containsKey(o); }
  public final boolean containsAll(Collection<?> c) { for (Object o : c) if (!map.containsKey(o)) return false; return true; }
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Set)) return false;
    Set<?> s = (Set<?>) o;
    return s.size() == size() && containsAll(s);
  }
  public int hashCode() { int h = 0; for (K k : map.keySet()) h += k.hashCode(); return h; }
  public void forEach(java.util.function.Consumer<? super K> action) { Objects.requireNonNull(action); for (K k : map.keySet()) action.accept(k); }
  public final boolean isEmpty() { return map.isEmpty(); }
  public Iterator<K> iterator() { return map.keySet().iterator(); }
  public boolean remove(Object o) { return map.remove(o) != null; }
  public boolean removeAll(Collection<?> c) {
    Objects.requireNonNull(c);
    boolean changed = false;
    for (Iterator<K> it = map.keySet().iterator(); it.hasNext(); ) if (c.contains(it.next())) { it.remove(); changed = true; }
    return changed;
  }
  public final boolean retainAll(Collection<?> c) {
    Objects.requireNonNull(c);
    boolean changed = false;
    for (Iterator<K> it = map.keySet().iterator(); it.hasNext(); ) if (!c.contains(it.next())) { it.remove(); changed = true; }
    return changed;
  }
  public final int size() { return map.size(); }
  public Spliterator<K> spliterator() { return Spliterators.spliterator(this, Spliterator.DISTINCT | Spliterator.CONCURRENT | Spliterator.NONNULL); }
  public final Object[] toArray() { return map.keySet().toArray(); }
  public final <T> T[] toArray(T[] a) { return map.keySet().toArray(a); }
  public final String toString() { return map.keySet().toString(); }
}
