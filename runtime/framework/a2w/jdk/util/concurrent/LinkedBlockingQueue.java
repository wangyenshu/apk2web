package a2w.jdk.util.concurrent;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/** A blocking FIFO queue: take waits for an element, put waits for room. */
public class LinkedBlockingQueue<E> extends java.util.AbstractQueue<E> implements java.util.concurrent.BlockingQueue<E>, java.io.Serializable {
  private final ArrayDeque<E> q = new ArrayDeque<>();
  private final int capacity;
  public LinkedBlockingQueue() { this(Integer.MAX_VALUE); }
  public LinkedBlockingQueue(int capacity) { if (capacity <= 0) throw new IllegalArgumentException(); this.capacity = capacity; }
  public LinkedBlockingQueue(Collection<? extends E> c) { this(); addAll(c); }
  @Override public synchronized Iterator<E> iterator() { return new ArrayList<>(q).iterator(); }
  @Override public synchronized int size() { return q.size(); }
  @Override public synchronized boolean offer(E e) {
    if (e == null) throw new NullPointerException();
    if (q.size() >= capacity) return false;
    q.add(e); notifyAll();
    return true;
  }
  @Override public synchronized E poll() { E e = q.poll(); if (e != null) notifyAll(); return e; }
  @Override public synchronized E peek() { return q.peek(); }
  @Override public synchronized boolean remove(Object o) { boolean r = q.remove(o); if (r) notifyAll(); return r; }
  @Override public synchronized void clear() { q.clear(); notifyAll(); }
  @Override public synchronized void put(E e) throws InterruptedException {
    if (e == null) throw new NullPointerException();
    while (q.size() >= capacity) wait();
    q.add(e); notifyAll();
  }
  @Override public synchronized boolean offer(E e, long t, TimeUnit u) throws InterruptedException {
    if (e == null) throw new NullPointerException();
    long end = System.currentTimeMillis() + Math.max(0, u.toMillis(t));
    while (q.size() >= capacity) { long left = end - System.currentTimeMillis(); if (left <= 0) return false; wait(left); }
    q.add(e); notifyAll();
    return true;
  }
  @Override public synchronized E take() throws InterruptedException { while (q.isEmpty()) wait(); E e = q.poll(); notifyAll(); return e; }
  @Override public synchronized E poll(long t, TimeUnit u) throws InterruptedException {
    long end = System.currentTimeMillis() + Math.max(0, u.toMillis(t));
    while (q.isEmpty()) { long left = end - System.currentTimeMillis(); if (left <= 0) return null; wait(left); }
    E e = q.poll(); notifyAll();
    return e;
  }
  @Override public synchronized int remainingCapacity() { return capacity - q.size(); }
  @Override public int drainTo(Collection<? super E> c) { return drainTo(c, Integer.MAX_VALUE); }
  @Override public synchronized int drainTo(Collection<? super E> c, int max) { int n = 0; while (!q.isEmpty() && n < max) { c.add(q.poll()); n++; } if (n > 0) notifyAll(); return n; }
}
