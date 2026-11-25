package a2w.jdk.util.concurrent;

import java.util.Collection;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/** An unbounded priority queue whose take waits for an element. */
public class PriorityBlockingQueue<E> extends java.util.PriorityQueue<E> implements java.util.concurrent.BlockingQueue<E> {
  public PriorityBlockingQueue() {}
  public PriorityBlockingQueue(int n) { super(Math.max(1, n)); }
  public PriorityBlockingQueue(int n, Comparator<? super E> c) { super(Math.max(1, n), c); }
  public PriorityBlockingQueue(Collection<? extends E> c) { super(c); }
  @Override public synchronized boolean offer(E e) { boolean r = super.offer(e); notifyAll(); return r; }
  @Override public synchronized boolean add(E e) { return offer(e); }
  @Override public synchronized E poll() { return super.poll(); }
  @Override public synchronized E peek() { return super.peek(); }
  @Override public synchronized int size() { return super.size(); }
  @Override public void put(E e) { offer(e); }
  @Override public boolean offer(E e, long t, TimeUnit u) { return offer(e); }
  @Override public synchronized E take() throws InterruptedException { while (super.isEmpty()) wait(); return super.poll(); }
  @Override public synchronized E poll(long t, TimeUnit u) throws InterruptedException {
    long end = System.currentTimeMillis() + Math.max(0, u.toMillis(t));
    while (super.isEmpty()) { long left = end - System.currentTimeMillis(); if (left <= 0) return null; wait(left); }
    return super.poll();
  }
  @Override public int remainingCapacity() { return Integer.MAX_VALUE; }
  @Override public synchronized int drainTo(Collection<? super E> c) { int n = 0; for (E e; (e = super.poll()) != null; n++) c.add(e); return n; }
  @Override public synchronized int drainTo(Collection<? super E> c, int max) { int n = 0; for (E e; n < max && (e = super.poll()) != null; n++) c.add(e); return n; }
}
