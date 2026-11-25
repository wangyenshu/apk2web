package a2w.jdk.util.concurrent;

import java.util.concurrent.TimeUnit;

/** java.util.concurrent.Exchanger: two threads swap objects at a rendezvous. */
public class Exchanger<V> {
  private static final class Slot { final Object offered; Object reply; boolean done; Slot(Object o) { offered = o; } }
  private Slot waiting;
  public Exchanger() {}
  @SuppressWarnings("unchecked")
  private synchronized V doExchange(V x, long deadline) throws InterruptedException, TimeoutException {
    if (waiting != null) {
      Slot s = waiting; waiting = null;
      s.reply = x; s.done = true;
      notifyAll();
      return (V) s.offered;
    }
    Slot mine = new Slot(x);
    waiting = mine;
    try {
      while (!mine.done) {
        long left = deadline == 0 ? 0 : deadline - System.currentTimeMillis();
        if (deadline != 0 && left <= 0) throw new TimeoutException();
        wait(left);
      }
      return (V) mine.reply;
    } finally {
      if (!mine.done && waiting == mine) waiting = null;
    }
  }
  public V exchange(V x) throws InterruptedException {
    if (Thread.interrupted()) throw new InterruptedException();
    try { return doExchange(x, 0); } catch (TimeoutException e) { throw new IllegalStateException(e); }
  }
  public V exchange(V x, long timeout, TimeUnit unit) throws InterruptedException, TimeoutException {
    if (Thread.interrupted()) throw new InterruptedException();
    return doExchange(x, System.currentTimeMillis() + Math.max(1, unit.toMillis(timeout)));
  }
}
