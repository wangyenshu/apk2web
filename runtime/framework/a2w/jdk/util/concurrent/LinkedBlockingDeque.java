package a2w.jdk.util.concurrent;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

/** java.util.concurrent.LinkedBlockingDeque (replaces TeaVM's, which lacks the blocking methods): an optionally bounded
 *  deque; the take methods wait for an element, the put methods for room. Iterators work on a snapshot. */
public class LinkedBlockingDeque<E> extends java.util.AbstractQueue<E> implements java.util.concurrent.BlockingDeque<E>, java.io.Serializable {
  private final ArrayDeque<E> q = new ArrayDeque<>();
  private final int capacity;
  public LinkedBlockingDeque() { this(Integer.MAX_VALUE); }
  public LinkedBlockingDeque(int capacity) { if (capacity <= 0) throw new IllegalArgumentException(); this.capacity = capacity; }
  public LinkedBlockingDeque(Collection<? extends E> c) { this(); for (E e : c) addLast(e); }

  private static void nn(Object e) { if (e == null) throw new NullPointerException(); }
  private static long deadline(long t, TimeUnit u) { return System.currentTimeMillis() + Math.max(0, u.toMillis(t)); }

  public synchronized boolean offerFirst(E e) { nn(e); if (q.size() >= capacity) return false; q.addFirst(e); notifyAll(); return true; }
  public synchronized boolean offerLast(E e) { nn(e); if (q.size() >= capacity) return false; q.addLast(e); notifyAll(); return true; }
  public void addFirst(E e) { if (!offerFirst(e)) throw new IllegalStateException("Deque full"); }
  public void addLast(E e) { if (!offerLast(e)) throw new IllegalStateException("Deque full"); }
  public synchronized void putFirst(E e) throws InterruptedException { nn(e); while (q.size() >= capacity) wait(); q.addFirst(e); notifyAll(); }
  public synchronized void putLast(E e) throws InterruptedException { nn(e); while (q.size() >= capacity) wait(); q.addLast(e); notifyAll(); }
  public synchronized boolean offerFirst(E e, long t, TimeUnit u) throws InterruptedException {
    nn(e); long end = deadline(t, u);
    while (q.size() >= capacity) { long left = end - System.currentTimeMillis(); if (left <= 0) return false; wait(left); }
    q.addFirst(e); notifyAll(); return true;
  }
  public synchronized boolean offerLast(E e, long t, TimeUnit u) throws InterruptedException {
    nn(e); long end = deadline(t, u);
    while (q.size() >= capacity) { long left = end - System.currentTimeMillis(); if (left <= 0) return false; wait(left); }
    q.addLast(e); notifyAll(); return true;
  }
  public synchronized E pollFirst() { E e = q.pollFirst(); if (e != null) notifyAll(); return e; }
  public synchronized E pollLast() { E e = q.pollLast(); if (e != null) notifyAll(); return e; }
  public E removeFirst() { E e = pollFirst(); if (e == null) throw new NoSuchElementException(); return e; }
  public E removeLast() { E e = pollLast(); if (e == null) throw new NoSuchElementException(); return e; }
  public synchronized E takeFirst() throws InterruptedException { while (q.isEmpty()) wait(); E e = q.pollFirst(); notifyAll(); return e; }
  public synchronized E takeLast() throws InterruptedException { while (q.isEmpty()) wait(); E e = q.pollLast(); notifyAll(); return e; }
  public synchronized E pollFirst(long t, TimeUnit u) throws InterruptedException {
    long end = deadline(t, u);
    while (q.isEmpty()) { long left = end - System.currentTimeMillis(); if (left <= 0) return null; wait(left); }
    E e = q.pollFirst(); notifyAll(); return e;
  }
  public synchronized E pollLast(long t, TimeUnit u) throws InterruptedException {
    long end = deadline(t, u);
    while (q.isEmpty()) { long left = end - System.currentTimeMillis(); if (left <= 0) return null; wait(left); }
    E e = q.pollLast(); notifyAll(); return e;
  }
  public synchronized E getFirst() { E e = q.peekFirst(); if (e == null) throw new NoSuchElementException(); return e; }
  public synchronized E getLast() { E e = q.peekLast(); if (e == null) throw new NoSuchElementException(); return e; }
  public synchronized E peekFirst() { return q.peekFirst(); }
  public synchronized E peekLast() { return q.peekLast(); }
  public synchronized boolean removeFirstOccurrence(Object o) { if (o == null) return false; boolean r = q.removeFirstOccurrence(o); if (r) notifyAll(); return r; }
  public synchronized boolean removeLastOccurrence(Object o) { if (o == null) return false; boolean r = q.removeLastOccurrence(o); if (r) notifyAll(); return r; }

  public boolean add(E e) { addLast(e); return true; }
  public boolean offer(E e) { return offerLast(e); }
  public void put(E e) throws InterruptedException { putLast(e); }
  public boolean offer(E e, long t, TimeUnit u) throws InterruptedException { return offerLast(e, t, u); }
  public E remove() { return removeFirst(); }
  public E poll() { return pollFirst(); }
  public E take() throws InterruptedException { return takeFirst(); }
  public E poll(long t, TimeUnit u) throws InterruptedException { return pollFirst(t, u); }
  public E element() { return getFirst(); }
  public E peek() { return peekFirst(); }
  public void push(E e) { addFirst(e); }
  public E pop() { return removeFirst(); }
  public boolean remove(Object o) { return removeFirstOccurrence(o); }
  public synchronized boolean contains(Object o) { return o != null && q.contains(o); }
  public synchronized int size() { return q.size(); }
  public synchronized int remainingCapacity() { return capacity - q.size(); }
  public synchronized void clear() { q.clear(); notifyAll(); }
  public int drainTo(Collection<? super E> c) { return drainTo(c, Integer.MAX_VALUE); }
  public synchronized int drainTo(Collection<? super E> c, int max) {
    nn(c); if (c == this) throw new IllegalArgumentException();
    int n = 0; while (!q.isEmpty() && n < max) { c.add(q.pollFirst()); n++; }
    if (n > 0) notifyAll();
    return n;
  }
  public synchronized Object[] toArray() { return q.toArray(); }
  public synchronized <T> T[] toArray(T[] a) { return q.toArray(a); }
  public synchronized String toString() { return q.toString(); }
  public Iterator<E> iterator() { return snapshot(false); }
  public Iterator<E> descendingIterator() { return snapshot(true); }
  private Iterator<E> snapshot(boolean reverse) {
    ArrayList<E> s;
    synchronized (this) { s = new ArrayList<>(q); }
    if (reverse) Collections.reverse(s);
    Iterator<E> it = s.iterator();
    return new Iterator<E>() {
      E last;
      public boolean hasNext() { return it.hasNext(); }
      public E next() { return last = it.next(); }
      public void remove() { if (last == null) throw new IllegalStateException(); LinkedBlockingDeque.this.removeFirstOccurrence(last); last = null; }
    };
  }
}
