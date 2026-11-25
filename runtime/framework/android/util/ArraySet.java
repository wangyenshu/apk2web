package android.util;

import java.util.*;

/** android.util.ArraySet: a set with index access (valueAt), in insertion order. */
public final class ArraySet<E> extends AbstractSet<E> implements Collection<E>, Set<E> {
  final ArrayList<E> items = new ArrayList<>();
  public ArraySet() {}
  public ArraySet(int capacity) { items.ensureCapacity(capacity); }
  public ArraySet(ArraySet<E> set) { if (set != null) addAll(set); }
  public ArraySet(Collection<? extends E> set) { if (set != null) addAll(set); }
  public ArraySet(E[] array) { if (array != null) for (E e : array) add(e); }
  public int indexOf(Object key) { return items.indexOf(key); }
  public E valueAt(int index) { return items.get(index); }
  public E removeAt(int index) { return items.remove(index); }
  public void ensureCapacity(int n) { items.ensureCapacity(n); }
  public void append(E value) { add(value); }
  public void addAll(ArraySet<? extends E> a) { for (int i = 0; i < a.size(); i++) add(a.valueAt(i)); }
  public boolean removeAll(ArraySet<? extends E> a) { boolean r = false; for (int i = 0; i < a.size(); i++) r |= remove(a.valueAt(i)); return r; }
  @Override public boolean add(E e) { if (items.contains(e)) return false; items.add(e); return true; }
  @Override public boolean remove(Object o) { return items.remove(o); }
  @Override public boolean contains(Object o) { return items.contains(o); }
  @Override public int size() { return items.size(); }
  @Override public void clear() { items.clear(); }
  @Override public Iterator<E> iterator() { return items.iterator(); }
}
