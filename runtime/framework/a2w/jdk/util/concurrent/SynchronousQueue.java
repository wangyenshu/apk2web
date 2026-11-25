package a2w.jdk.util.concurrent;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/** A queue with no room: offer succeeds only when a taker is waiting, put waits for one. */
public class SynchronousQueue<E> extends java.util.AbstractQueue<E> implements java.util.concurrent.BlockingQueue<E>, java.io.Serializable {
  private int takers;        // threads waiting in take/poll
  private Object item;       // handed over, not yet picked up
  private boolean full;
  private int givers;        // threads waiting in put/offer
  public SynchronousQueue() {}
  public SynchronousQueue(boolean fair) {}
  private synchronized boolean give(E e, long ms) throws InterruptedException {
    if (e == null) throw new NullPointerException();
    long end = ms < 0 ? Long.MAX_VALUE : System.currentTimeMillis() + ms;
    givers++;
    notifyAll();
    try {
      while (full || takers == 0) {
        if (ms == 0) return false;
        long left = end == Long.MAX_VALUE ? 0 : end - System.currentTimeMillis();
        if (end != Long.MAX_VALUE && left <= 0) return false;
        wait(left);
      }
    } finally { givers--; }
    item = e; full = true; takers--;
    notifyAll();
    return true;
  }
  @SuppressWarnings("unchecked")
  private synchronized E get(long ms) throws InterruptedException {
    if (ms == 0 && !full) {
      if (givers == 0) return null;
      ms = 20;   // a putter is waiting: take its element
    }
    long end = ms < 0 ? Long.MAX_VALUE : System.currentTimeMillis() + ms;
    takers++;
    notifyAll();
    boolean counted = true;
    try {
      while (!full) {
        long left = end == Long.MAX_VALUE ? 0 : end - System.currentTimeMillis();
        if (end != Long.MAX_VALUE && left <= 0) return null;
        wait(left);
      }
      counted = false;   // give() took our place
      E e = (E) item; item = null; full = false;
      notifyAll();
      return e;
    } finally {
      if (counted) takers--;
    }
  }
  @Override public boolean offer(E e) { try { return give(e, 0); } catch (InterruptedException x) { return false; } }
  @Override public boolean offer(E e, long t, TimeUnit u) throws InterruptedException { return give(e, Math.max(0, u.toMillis(t))); }
  @Override public void put(E e) throws InterruptedException { give(e, -1); }
  @Override public E poll() { try { return get(0); } catch (InterruptedException x) { return null; } }
  @Override public E poll(long t, TimeUnit u) throws InterruptedException { return get(Math.max(0, u.toMillis(t))); }
  @Override public E take() throws InterruptedException { return get(-1); }
  @Override public E peek() { return null; }
  @Override public Iterator<E> iterator() { return Collections.emptyIterator(); }
  @Override public int size() { return 0; }
  @Override public boolean isEmpty() { return true; }
  @Override public int remainingCapacity() { return 0; }
  @Override public int drainTo(Collection<? super E> c) { return drainTo(c, Integer.MAX_VALUE); }
  @Override public int drainTo(Collection<? super E> c, int max) { int n = 0; for (E e; n < max && (e = poll()) != null; n++) c.add(e); return n; }
}
