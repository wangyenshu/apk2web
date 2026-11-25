package a2w.jdk.util.concurrent;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

/** java.util.concurrent.LinkedTransferQueue: an unbounded FIFO queue where a producer can wait (transfer) until a
 *  consumer has received its element. */
public class LinkedTransferQueue<E> extends java.util.AbstractQueue<E> implements TransferQueue<E>, java.io.Serializable {
  private static final class Item<E> { final E e; boolean taken; Item(E e) { this.e = e; } }
  private final ArrayDeque<Item<E>> q = new ArrayDeque<>();
  private int waitingConsumers;

  public LinkedTransferQueue() {}
  public LinkedTransferQueue(Collection<? extends E> c) { for (E e : c) add(e); }

  private static void nn(Object e) { if (e == null) throw new NullPointerException(); }
  private Item<E> enqueue(E e) { nn(e); Item<E> it = new Item<>(e); q.addLast(it); notifyAll(); return it; }
  private E dequeue() { Item<E> it = q.pollFirst(); if (it == null) return null; it.taken = true; notifyAll(); return it.e; }

  public synchronized boolean offer(E e) { enqueue(e); return true; }
  public boolean offer(E e, long timeout, TimeUnit unit) { return offer(e); }
  public void put(E e) { offer(e); }
  public boolean add(E e) { return offer(e); }
  public synchronized boolean tryTransfer(E e) {
    nn(e);
    if (waitingConsumers <= q.size()) return false;
    enqueue(e);
    return true;
  }
  public synchronized void transfer(E e) throws InterruptedException {
    Item<E> it = enqueue(e);
    try { while (!it.taken) wait(); }
    catch (InterruptedException ex) { if (!it.taken) q.remove(it); throw ex; }
  }
  public synchronized boolean tryTransfer(E e, long timeout, TimeUnit unit) throws InterruptedException {
    Item<E> it = enqueue(e);
    long deadline = System.currentTimeMillis() + unit.toMillis(timeout);
    try {
      while (!it.taken) {
        long left = deadline - System.currentTimeMillis();
        if (left <= 0) { q.remove(it); return false; }
        wait(left);
      }
      return true;
    } catch (InterruptedException ex) { if (!it.taken) q.remove(it); throw ex; }
  }
  public synchronized E take() throws InterruptedException {
    waitingConsumers++;
    notifyAll();
    try { while (q.isEmpty()) wait(); } finally { waitingConsumers--; }
    return dequeue();
  }
  public synchronized E poll(long timeout, TimeUnit unit) throws InterruptedException {
    long deadline = System.currentTimeMillis() + unit.toMillis(timeout);
    waitingConsumers++;
    notifyAll();
    try {
      while (q.isEmpty()) { long left = deadline - System.currentTimeMillis(); if (left <= 0) return null; wait(left); }
    } finally { waitingConsumers--; }
    return dequeue();
  }
  public synchronized E poll() { return dequeue(); }
  public synchronized E peek() { Item<E> it = q.peekFirst(); return it == null ? null : it.e; }
  public synchronized boolean isEmpty() { return q.isEmpty(); }
  public synchronized int size() { return q.size(); }
  public int remainingCapacity() { return Integer.MAX_VALUE; }
  public synchronized boolean hasWaitingConsumer() { return waitingConsumers > 0; }
  public synchronized int getWaitingConsumerCount() { return waitingConsumers; }
  public synchronized boolean contains(Object o) { if (o == null) return false; for (Item<E> it : q) if (o.equals(it.e)) return true; return false; }
  public synchronized boolean remove(Object o) {
    if (o == null) return false;
    for (Iterator<Item<E>> i = q.iterator(); i.hasNext(); ) { Item<E> it = i.next(); if (o.equals(it.e)) { i.remove(); notifyAll(); return true; } }
    return false;
  }
  public int drainTo(Collection<? super E> c) { return drainTo(c, Integer.MAX_VALUE); }
  public synchronized int drainTo(Collection<? super E> c, int maxElements) {
    nn(c);
    if (c == this) throw new IllegalArgumentException();
    int n = 0;
    for (E e; n < maxElements && (e = dequeue()) != null; n++) c.add(e);
    return n;
  }
  public synchronized void clear() { while (dequeue() != null) { } }
  public synchronized Object[] toArray() { ArrayList<E> l = new ArrayList<>(); for (Item<E> it : q) l.add(it.e); return l.toArray(); }
  public synchronized <T> T[] toArray(T[] a) { ArrayList<E> l = new ArrayList<>(); for (Item<E> it : q) l.add(it.e); return l.toArray(a); }
  public synchronized String toString() { ArrayList<E> l = new ArrayList<>(); for (Item<E> it : q) l.add(it.e); return l.toString(); }
  public synchronized boolean removeIf(java.util.function.Predicate<? super E> filter) {
    nn(filter);
    boolean r = false;
    for (Iterator<Item<E>> i = q.iterator(); i.hasNext(); ) if (filter.test(i.next().e)) { i.remove(); r = true; }
    if (r) notifyAll();
    return r;
  }
  public synchronized boolean removeAll(Collection<?> c) { nn(c); return removeIf(c::contains); }
  public synchronized boolean retainAll(Collection<?> c) { nn(c); return removeIf(e -> !c.contains(e)); }
  public synchronized void forEach(java.util.function.Consumer<? super E> action) { nn(action); for (Item<E> it : q) action.accept(it.e); }
  public Iterator<E> iterator() {
    ArrayList<E> snap;
    synchronized (this) { snap = new ArrayList<>(); for (Item<E> it : q) snap.add(it.e); }
    Iterator<E> it = snap.iterator();
    return new Iterator<E>() {
      E last;
      public boolean hasNext() { return it.hasNext(); }
      public E next() { if (!it.hasNext()) throw new NoSuchElementException(); return last = it.next(); }
      public void remove() { if (last == null) throw new IllegalStateException(); LinkedTransferQueue.this.removeEq(last); last = null; }
    };
  }
  private synchronized void removeEq(Object o) { for (Iterator<Item<E>> i = q.iterator(); i.hasNext(); ) if (i.next().e == o) { i.remove(); break; } }
}
