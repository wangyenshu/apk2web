package a2w.jdk.util;

import java.util.*;

/**
 * The Collections wrappers TeaVM's class library lacks: unmodifiable, synchronized and checked views of sorted and
 * navigable sets and maps and of queues, the empty sorted and navigable collections, and asLifoQueue. One class per
 * shape serves all three modes: UNMOD rejects changes (views and iterators too), SYNC runs each call holding the
 * mutex (iterating needs the caller to hold it, as with the JDK's), CHECKED tests the class of what is added.
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public final class Wrap {
  private Wrap() {}
  public static final int UNMOD = 0, SYNC = 1, CHECKED = 2;

  static final class Mode {
    final int kind; Object mutex; final Class<?> type, valueType;   // the mutex of SYNC is the outermost wrapper, as in the JDK
    Mode(int kind, Object mutex, Class<?> type, Class<?> valueType) { this.kind = kind; this.mutex = mutex; this.type = type; this.valueType = valueType; }
    void write() { if (kind == UNMOD) throw new UnsupportedOperationException(); }
    Object check(Object o) {
      if (kind == CHECKED && o != null && !type.isInstance(o)) throw new ClassCastException("Attempt to insert " + o.getClass() + " element into collection with element type " + type);
      return o;
    }
    Object checkValue(Object o) {
      if (kind == CHECKED && o != null && !valueType.isInstance(o)) throw new ClassCastException("Attempt to insert " + o.getClass() + " value into map with value type " + valueType);
      return o;
    }
    void checkAll(Collection<?> c) { if (kind == CHECKED) for (Object o : c) check(o); }
    Mode forValues() { return kind == CHECKED ? new Mode(CHECKED, mutex, valueType, valueType) : this; }
  }

  static <E> Iterator<E> it(Mode m, Iterator<E> i) {
    if (m.kind != UNMOD) return i;
    return new Iterator<E>() {
      public boolean hasNext() { return i.hasNext(); }
      public E next() { return i.next(); }
      public void remove() { throw new UnsupportedOperationException(); }
    };
  }

  /** a collection view (Map.values()) */
  static class WColl<E> implements Collection<E>, java.io.Serializable {
    final Collection<E> d; final Mode m;
    WColl(Collection<E> d, Mode m) { this.d = Objects.requireNonNull(d); this.m = m; }
    public int size() { synchronized (m.mutex) { return d.size(); } }
    public boolean isEmpty() { synchronized (m.mutex) { return d.isEmpty(); } }
    public boolean contains(Object o) { synchronized (m.mutex) { return d.contains(o); } }
    public Object[] toArray() { synchronized (m.mutex) { return d.toArray(); } }
    public <T> T[] toArray(T[] a) { synchronized (m.mutex) { return d.toArray(a); } }
    public Iterator<E> iterator() { return it(m, d.iterator()); }
    public boolean add(E e) { m.write(); m.check(e); synchronized (m.mutex) { return d.add(e); } }
    public boolean remove(Object o) { m.write(); synchronized (m.mutex) { return d.remove(o); } }
    public boolean containsAll(Collection<?> c) { synchronized (m.mutex) { return d.containsAll(c); } }
    public boolean addAll(Collection<? extends E> c) { m.write(); m.checkAll(c); synchronized (m.mutex) { return d.addAll(c); } }
    public boolean removeAll(Collection<?> c) { m.write(); synchronized (m.mutex) { return d.removeAll(c); } }
    public boolean retainAll(Collection<?> c) { m.write(); synchronized (m.mutex) { return d.retainAll(c); } }
    public boolean removeIf(java.util.function.Predicate<? super E> p) { m.write(); synchronized (m.mutex) { return d.removeIf(p); } }
    public void clear() { m.write(); synchronized (m.mutex) { d.clear(); } }
    public void forEach(java.util.function.Consumer<? super E> a) { synchronized (m.mutex) { d.forEach(a); } }
    public String toString() { synchronized (m.mutex) { return d.toString(); } }
  }

  /** a set; the sorted and navigable methods work when the wrapped set is one */
  static final class WSet<E> extends WColl<E> implements NavigableSet<E> {
    WSet(Set<E> d, Mode m) { super(d, m); }
    SortedSet<E> s() { return (SortedSet<E>) d; }
    NavigableSet<E> n() { return (NavigableSet<E>) d; }
    public boolean equals(Object o) { if (o == this) return true; synchronized (m.mutex) { return d.equals(o); } }
    public int hashCode() { synchronized (m.mutex) { return d.hashCode(); } }
    public Comparator<? super E> comparator() { return s().comparator(); }
    public E first() { synchronized (m.mutex) { return s().first(); } }
    public E last() { synchronized (m.mutex) { return s().last(); } }
    public SortedSet<E> subSet(E a, E b) { synchronized (m.mutex) { return new WSet<>(s().subSet(a, b), m); } }
    public SortedSet<E> headSet(E b) { synchronized (m.mutex) { return new WSet<>(s().headSet(b), m); } }
    public SortedSet<E> tailSet(E a) { synchronized (m.mutex) { return new WSet<>(s().tailSet(a), m); } }
    public E lower(E e) { synchronized (m.mutex) { return n().lower(e); } }
    public E floor(E e) { synchronized (m.mutex) { return n().floor(e); } }
    public E ceiling(E e) { synchronized (m.mutex) { return n().ceiling(e); } }
    public E higher(E e) { synchronized (m.mutex) { return n().higher(e); } }
    public E pollFirst() { m.write(); synchronized (m.mutex) { return n().pollFirst(); } }
    public E pollLast() { m.write(); synchronized (m.mutex) { return n().pollLast(); } }
    public NavigableSet<E> descendingSet() { synchronized (m.mutex) { return new WSet<>(n().descendingSet(), m); } }
    public Iterator<E> descendingIterator() { return it(m, n().descendingIterator()); }
    public NavigableSet<E> subSet(E a, boolean ai, E b, boolean bi) { synchronized (m.mutex) { return new WSet<>(n().subSet(a, ai, b, bi), m); } }
    public NavigableSet<E> headSet(E b, boolean bi) { synchronized (m.mutex) { return new WSet<>(n().headSet(b, bi), m); } }
    public NavigableSet<E> tailSet(E a, boolean ai) { synchronized (m.mutex) { return new WSet<>(n().tailSet(a, ai), m); } }
  }

  /** a map; the sorted and navigable methods work when the wrapped map is one */
  static final class WMap<K, V> implements NavigableMap<K, V>, java.io.Serializable {
    final Map<K, V> d; final Mode m;
    WMap(Map<K, V> d, Mode m) { this.d = Objects.requireNonNull(d); this.m = m; }
    SortedMap<K, V> s() { return (SortedMap<K, V>) d; }
    NavigableMap<K, V> n() { return (NavigableMap<K, V>) d; }
    Map.Entry<K, V> e(Map.Entry<K, V> e) { return e == null || m.kind != UNMOD ? e : new AbstractMap.SimpleImmutableEntry<>(e); }
    public int size() { synchronized (m.mutex) { return d.size(); } }
    public boolean isEmpty() { synchronized (m.mutex) { return d.isEmpty(); } }
    public boolean containsKey(Object k) { synchronized (m.mutex) { return d.containsKey(k); } }
    public boolean containsValue(Object v) { synchronized (m.mutex) { return d.containsValue(v); } }
    public V get(Object k) { synchronized (m.mutex) { return d.get(k); } }
    public V put(K k, V v) { m.write(); m.check(k); m.checkValue(v); synchronized (m.mutex) { return d.put(k, v); } }
    public V remove(Object k) { m.write(); synchronized (m.mutex) { return d.remove(k); } }
    public void putAll(Map<? extends K, ? extends V> o) {
      m.write();
      if (m.kind == CHECKED) for (Map.Entry<? extends K, ? extends V> x : o.entrySet()) { m.check(x.getKey()); m.checkValue(x.getValue()); }
      synchronized (m.mutex) { d.putAll(o); }
    }
    public void clear() { m.write(); synchronized (m.mutex) { d.clear(); } }
    public Set<K> keySet() { synchronized (m.mutex) { return new WSet<>(d.keySet(), m); } }
    public Collection<V> values() { synchronized (m.mutex) { return new WColl<>(d.values(), m.forValues()); } }
    public Set<Map.Entry<K, V>> entrySet() {
      synchronized (m.mutex) {
        if (m.kind == UNMOD) return Collections.unmodifiableMap(d).entrySet();
        return new WSet<>(d.entrySet(), m.kind == CHECKED ? new Mode(UNMOD, m.mutex, null, null) : m);
      }
    }
    public boolean equals(Object o) { if (o == this) return true; synchronized (m.mutex) { return d.equals(o); } }
    public int hashCode() { synchronized (m.mutex) { return d.hashCode(); } }
    public String toString() { synchronized (m.mutex) { return d.toString(); } }
    public V getOrDefault(Object k, V dv) { synchronized (m.mutex) { return d.getOrDefault(k, dv); } }
    public void forEach(java.util.function.BiConsumer<? super K, ? super V> a) { synchronized (m.mutex) { d.forEach(a); } }
    public V putIfAbsent(K k, V v) { m.write(); m.check(k); m.checkValue(v); synchronized (m.mutex) { return d.putIfAbsent(k, v); } }
    public boolean remove(Object k, Object v) { m.write(); synchronized (m.mutex) { return d.remove(k, v); } }
    public boolean replace(K k, V o, V v) { m.write(); m.checkValue(v); synchronized (m.mutex) { return d.replace(k, o, v); } }
    public V replace(K k, V v) { m.write(); m.checkValue(v); synchronized (m.mutex) { return d.replace(k, v); } }
    public void replaceAll(java.util.function.BiFunction<? super K, ? super V, ? extends V> f) {
      m.write(); synchronized (m.mutex) { d.replaceAll(m.kind == CHECKED ? (k, v) -> (V) m.checkValue(f.apply(k, v)) : f); }
    }
    public V computeIfAbsent(K k, java.util.function.Function<? super K, ? extends V> f) {
      synchronized (m.mutex) {
        V v = d.get(k);
        if (v != null) return v;
        m.write(); m.check(k);
        return d.computeIfAbsent(k, m.kind == CHECKED ? x -> (V) m.checkValue(f.apply(x)) : f);
      }
    }
    public V computeIfPresent(K k, java.util.function.BiFunction<? super K, ? super V, ? extends V> f) {
      m.write(); synchronized (m.mutex) { return d.computeIfPresent(k, m.kind == CHECKED ? (x, v) -> (V) m.checkValue(f.apply(x, v)) : f); }
    }
    public V compute(K k, java.util.function.BiFunction<? super K, ? super V, ? extends V> f) {
      m.write(); m.check(k); synchronized (m.mutex) { return d.compute(k, m.kind == CHECKED ? (x, v) -> (V) m.checkValue(f.apply(x, v)) : f); }
    }
    public V merge(K k, V v, java.util.function.BiFunction<? super V, ? super V, ? extends V> f) {
      m.write(); m.check(k); m.checkValue(v); synchronized (m.mutex) { return d.merge(k, v, m.kind == CHECKED ? (a, b) -> (V) m.checkValue(f.apply(a, b)) : f); }
    }
    public Comparator<? super K> comparator() { return s().comparator(); }
    public K firstKey() { synchronized (m.mutex) { return s().firstKey(); } }
    public K lastKey() { synchronized (m.mutex) { return s().lastKey(); } }
    public SortedMap<K, V> subMap(K a, K b) { synchronized (m.mutex) { return new WMap<>(s().subMap(a, b), m); } }
    public SortedMap<K, V> headMap(K b) { synchronized (m.mutex) { return new WMap<>(s().headMap(b), m); } }
    public SortedMap<K, V> tailMap(K a) { synchronized (m.mutex) { return new WMap<>(s().tailMap(a), m); } }
    public Map.Entry<K, V> lowerEntry(K k) { synchronized (m.mutex) { return e(n().lowerEntry(k)); } }
    public K lowerKey(K k) { synchronized (m.mutex) { return n().lowerKey(k); } }
    public Map.Entry<K, V> floorEntry(K k) { synchronized (m.mutex) { return e(n().floorEntry(k)); } }
    public K floorKey(K k) { synchronized (m.mutex) { return n().floorKey(k); } }
    public Map.Entry<K, V> ceilingEntry(K k) { synchronized (m.mutex) { return e(n().ceilingEntry(k)); } }
    public K ceilingKey(K k) { synchronized (m.mutex) { return n().ceilingKey(k); } }
    public Map.Entry<K, V> higherEntry(K k) { synchronized (m.mutex) { return e(n().higherEntry(k)); } }
    public K higherKey(K k) { synchronized (m.mutex) { return n().higherKey(k); } }
    public Map.Entry<K, V> firstEntry() { synchronized (m.mutex) { return e(n().firstEntry()); } }
    public Map.Entry<K, V> lastEntry() { synchronized (m.mutex) { return e(n().lastEntry()); } }
    public Map.Entry<K, V> pollFirstEntry() { m.write(); synchronized (m.mutex) { return n().pollFirstEntry(); } }
    public Map.Entry<K, V> pollLastEntry() { m.write(); synchronized (m.mutex) { return n().pollLastEntry(); } }
    public NavigableMap<K, V> descendingMap() { synchronized (m.mutex) { return new WMap<>(n().descendingMap(), m); } }
    public NavigableSet<K> navigableKeySet() { synchronized (m.mutex) { return new WSet<>(n().navigableKeySet(), m); } }
    public NavigableSet<K> descendingKeySet() { synchronized (m.mutex) { return new WSet<>(n().descendingKeySet(), m); } }
    public NavigableMap<K, V> subMap(K a, boolean ai, K b, boolean bi) { synchronized (m.mutex) { return new WMap<>(n().subMap(a, ai, b, bi), m); } }
    public NavigableMap<K, V> headMap(K b, boolean bi) { synchronized (m.mutex) { return new WMap<>(n().headMap(b, bi), m); } }
    public NavigableMap<K, V> tailMap(K a, boolean ai) { synchronized (m.mutex) { return new WMap<>(n().tailMap(a, ai), m); } }
  }

  /** a checked queue */
  static final class WQueue<E> extends WColl<E> implements Queue<E> {
    WQueue(Queue<E> d, Mode m) { super(d, m); }
    Queue<E> q() { return (Queue<E>) d; }
    public boolean offer(E e) { m.check(e); return q().offer(e); }
    public E remove() { return q().remove(); }
    public E poll() { return q().poll(); }
    public E element() { return q().element(); }
    public E peek() { return q().peek(); }
    public boolean equals(Object o) { return o == this || d.equals(o); }
    public int hashCode() { return d.hashCode(); }
  }

  /** Collections.asLifoQueue: a deque seen as a last-in-first-out queue */
  static final class Lifo<E> extends AbstractQueue<E> implements java.io.Serializable {
    final Deque<E> q;
    Lifo(Deque<E> q) { this.q = q; }
    public boolean add(E e) { q.addFirst(e); return true; }
    public boolean offer(E e) { return q.offerFirst(e); }
    public E poll() { return q.pollFirst(); }
    public E remove() { return q.removeFirst(); }
    public E peek() { return q.peekFirst(); }
    public E element() { return q.getFirst(); }
    public void clear() { q.clear(); }
    public int size() { return q.size(); }
    public boolean isEmpty() { return q.isEmpty(); }
    public boolean contains(Object o) { return q.contains(o); }
    public boolean remove(Object o) { return q.remove(o); }
    public Iterator<E> iterator() { return q.iterator(); }
    public Object[] toArray() { return q.toArray(); }
    public <T> T[] toArray(T[] a) { return q.toArray(a); }
    public String toString() { return q.toString(); }
  }

  static Mode unmod() { return new Mode(UNMOD, new Object(), null, null); }

  public static <E> NavigableSet<E> unmodifiableSet(Set<? extends E> s) { return new WSet<>((Set<E>) s, unmod()); }
  public static <K, V> NavigableMap<K, V> unmodifiableMap(Map<K, ? extends V> s) { return new WMap<>((Map<K, V>) s, unmod()); }
  public static <E> NavigableSet<E> synchronizedSet(Set<E> s) { Mode m = new Mode(SYNC, null, null, null); WSet<E> w = new WSet<>(s, m); m.mutex = w; return w; }
  public static <K, V> NavigableMap<K, V> synchronizedMap(Map<K, V> s) { Mode m = new Mode(SYNC, null, null, null); WMap<K, V> w = new WMap<>(s, m); m.mutex = w; return w; }
  public static <E> NavigableSet<E> checkedSet(Set<E> s, Class<E> type) { return new WSet<>(s, new Mode(CHECKED, new Object(), Objects.requireNonNull(type), null)); }
  public static <K, V> NavigableMap<K, V> checkedMap(Map<K, V> s, Class<K> kt, Class<V> vt) {
    return new WMap<>(s, new Mode(CHECKED, new Object(), Objects.requireNonNull(kt), Objects.requireNonNull(vt)));
  }
  public static <E> Queue<E> checkedQueue(Queue<E> q, Class<E> type) { return new WQueue<>(q, new Mode(CHECKED, new Object(), Objects.requireNonNull(type), null)); }
  public static <E> Queue<E> asLifoQueue(Deque<E> d) { return new Lifo<>(d); }
  public static <E> NavigableSet<E> emptyNavigableSet() { return new WSet<>(new TreeSet<E>(), unmod()); }
  public static <K, V> NavigableMap<K, V> emptyNavigableMap() { return new WMap<>(new TreeMap<K, V>(), unmod()); }
}
