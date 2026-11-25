package a2w.jdk;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/** Single-threaded stand-in (TeaVM threads are cooperative); iterators are weakly consistent snapshots. */
public class ConcurrentLinkedQueue<E> extends AbstractCollection<E> implements Queue<E>, java.io.Serializable {
  final LinkedList<E> list = new LinkedList<>();
  public ConcurrentLinkedQueue() {}
  public ConcurrentLinkedQueue(Collection<? extends E> c) { addAll(c); }
  @Override public boolean add(E e) { return offer(e); }
  public boolean offer(E e) { if (e == null) throw new NullPointerException(); list.add(e); return true; }
  public E poll() { return list.isEmpty() ? null : list.removeFirst(); }
  public E peek() { return list.isEmpty() ? null : list.getFirst(); }
  public E remove() { if (list.isEmpty()) throw new NoSuchElementException(); return list.removeFirst(); }
  public E element() { if (list.isEmpty()) throw new NoSuchElementException(); return list.getFirst(); }
  @Override public int size() { return list.size(); }
  @Override public boolean isEmpty() { return list.isEmpty(); }
  @Override public boolean contains(Object o) { return o != null && list.contains(o); }
  @Override public boolean remove(Object o) { return o != null && list.remove(o); }
  @Override public void clear() { list.clear(); }
  @Override public Iterator<E> iterator() {
    final Iterator<E> it = new ArrayList<>(list).iterator();
    return new Iterator<E>() {
      E last;
      public boolean hasNext() { return it.hasNext(); }
      public E next() { return last = it.next(); }
      public void remove() { if (last == null) throw new IllegalStateException(); list.remove(last); last = null; }
    };
  }
}
