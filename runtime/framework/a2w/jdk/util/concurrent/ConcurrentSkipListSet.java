package a2w.jdk.util.concurrent;

import java.util.Collection;
import java.util.Comparator;

public class ConcurrentSkipListSet<E> extends java.util.TreeSet<E> {
  public ConcurrentSkipListSet() {}
  public ConcurrentSkipListSet(Comparator<? super E> c) { super(c); }
  public ConcurrentSkipListSet(Collection<? extends E> c) { super(c); }
  public ConcurrentSkipListSet(java.util.SortedSet<E> s) { super(s); }
  // the SDK's return types: calls compiled against it use these descriptors
  @Override public ConcurrentSkipListSet<E> clone() { ConcurrentSkipListSet<E> c = new ConcurrentSkipListSet<>(comparator()); c.addAll(this); return c; }
  @Override public java.util.NavigableSet<E> headSet(E to) { return headSet(to, false); }
  @Override public java.util.NavigableSet<E> tailSet(E from) { return tailSet(from, true); }
  @Override public java.util.NavigableSet<E> subSet(E from, E to) { return subSet(from, true, to, false); }
}
