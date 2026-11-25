package a2w.jdk.util.concurrent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;

/** java.util.concurrent.DelayQueue: an unbounded queue whose elements can be taken once their delay has expired. */
public class DelayQueue<E extends Delayed> extends java.util.AbstractQueue<E> implements java.util.concurrent.BlockingQueue<E> {
  private final PriorityQueue<E> q = new PriorityQueue<>();
  public DelayQueue() {}
  public DelayQueue(Collection<? extends E> c) { addAll(c); }
  public boolean add(E e) { return offer(e); }
  public synchronized boolean offer(E e) { q.offer(java.util.Objects.requireNonNull(e)); notifyAll(); return true; }
  public void put(E e) { offer(e); }
  public boolean offer(E e, long timeout, TimeUnit unit) { return offer(e); }
  private E expired() { E f = q.peek(); return f != null && f.getDelay(TimeUnit.NANOSECONDS) <= 0 ? q.poll() : null; }
  public synchronized E poll() { return expired(); }
  public synchronized E take() throws InterruptedException {
    for (;;) {
      E f = q.peek();
      if (f == null) { wait(); continue; }
      long ms = f.getDelay(TimeUnit.MILLISECONDS);
      if (f.getDelay(TimeUnit.NANOSECONDS) <= 0) { E e = q.poll(); notifyAll(); return e; }
      wait(Math.max(1, ms));
    }
  }
  public synchronized E poll(long timeout, TimeUnit unit) throws InterruptedException {
    long deadline = System.currentTimeMillis() + unit.toMillis(timeout);
    for (;;) {
      E f = q.peek();
      if (f != null && f.getDelay(TimeUnit.NANOSECONDS) <= 0) { E e = q.poll(); notifyAll(); return e; }
      long left = deadline - System.currentTimeMillis();
      if (left <= 0) return null;
      wait(f == null ? left : Math.max(1, Math.min(left, f.getDelay(TimeUnit.MILLISECONDS))));
    }
  }
  public synchronized E peek() { return q.peek(); }
  public E remove() { E e = poll(); if (e == null) throw new NoSuchElementException(); return e; }
  public synchronized int size() { return q.size(); }
  public int remainingCapacity() { return Integer.MAX_VALUE; }
  public int drainTo(Collection<? super E> c) { return drainTo(c, Integer.MAX_VALUE); }
  public synchronized int drainTo(Collection<? super E> c, int maxElements) {
    java.util.Objects.requireNonNull(c);
    if (c == this) throw new IllegalArgumentException();
    int n = 0;
    for (E e; n < maxElements && (e = expired()) != null; n++) c.add(e);
    return n;
  }
  public synchronized void clear() { q.clear(); }
  public synchronized boolean remove(Object o) { return q.remove(o); }
  public synchronized Object[] toArray() { return q.toArray(); }
  public synchronized <T> T[] toArray(T[] a) { return q.toArray(a); }
  public Iterator<E> iterator() {
    ArrayList<E> snap;
    synchronized (this) { snap = new ArrayList<>(q); }
    Iterator<E> it = snap.iterator();
    return new Iterator<E>() {
      E last;
      public boolean hasNext() { return it.hasNext(); }
      public E next() { return last = it.next(); }
      public void remove() { if (last == null) throw new IllegalStateException(); DelayQueue.this.removeEq(last); last = null; }
    };
  }
  private synchronized void removeEq(Object o) { for (Iterator<E> i = q.iterator(); i.hasNext(); ) if (i.next() == o) { i.remove(); break; } }
}
