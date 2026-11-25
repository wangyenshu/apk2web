package a2w.jdk.util.concurrent;

import java.util.Collection;
import java.util.Iterator;

public class CopyOnWriteArraySet<E> extends java.util.AbstractSet<E> implements java.io.Serializable {
  private final java.util.concurrent.CopyOnWriteArrayList<E> al = new java.util.concurrent.CopyOnWriteArrayList<>();
  public CopyOnWriteArraySet() {}
  public CopyOnWriteArraySet(Collection<? extends E> c) { addAll(c); }
  @Override public int size() { return al.size(); }
  @Override public boolean contains(Object o) { return al.contains(o); }
  @Override public boolean add(E e) { return al.addIfAbsent(e); }
  @Override public boolean remove(Object o) { return al.remove(o); }
  @Override public void clear() { al.clear(); }
  @Override public Iterator<E> iterator() { return al.iterator(); }
}
