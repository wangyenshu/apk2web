package android.os;

/** Time-ordered message queue. Producers may be other Java threads or JS callbacks. */
public final class MessageQueue {
  public interface IdleHandler { boolean queueIdle(); }
  Message head;
  boolean quitting;
  /** like ViewRootImpl's sync barrier: while a traversal is pending only asynchronous messages run */
  boolean barrier;
  long barrierWhen;
  public void setBarrier(boolean b) {
    synchronized (this) {
      if (barrier == b) return;
      barrier = b;
      if (b) barrierWhen = SystemClock.uptimeMillis(); else notifyAll();
    }
  }
  private final java.util.ArrayList<IdleHandler> idle = new java.util.ArrayList<>();

  public void addIdleHandler(IdleHandler h) { synchronized (this) { idle.add(h); } }
  public void removeIdleHandler(IdleHandler h) { synchronized (this) { idle.remove(h); } }

  boolean enqueue(Message m, long when) {
    synchronized (this) {
      if (quitting) return false;
      m.when = when;
      if (head == null || when < head.when) { m.next = head; head = m; }
      else {
        Message p = head;
        while (p.next != null && p.next.when <= when) p = p.next;
        m.next = p.next; p.next = m;
      }
      notifyAll();
      return true;
    }
  }

  Message next() {
    boolean ranIdle = false;
    while (true) {
      IdleHandler[] toRun = null;
      synchronized (this) {
        if (quitting) return null;
        long now = SystemClock.uptimeMillis();
        Message prev = null, m = head;
        // messages queued before the barrier still run; later synchronous ones wait for the traversal
        if (barrier) while (m != null && !m.async && m.when >= barrierWhen) { prev = m; m = m.next; }
        if (m != null && m.when <= now) {
          if (prev == null) head = m.next; else prev.next = m.next;
          m.next = null;
          return m;
        }
        if (!ranIdle && !idle.isEmpty() && (m == null || m.when > now)) toRun = idle.toArray(new IdleHandler[0]);
        else {
          try {
            if (m == null) wait();
            else wait(Math.max(1, m.when - now));
          } catch (InterruptedException e) { return null; }
          ranIdle = false;
          continue;
        }
      }
      ranIdle = true;
      for (IdleHandler h : toRun) {
        boolean keep = false;
        try { keep = h.queueIdle(); } catch (Throwable t) { t.printStackTrace(); }
        if (!keep) removeIdleHandler(h);
      }
    }
  }

  boolean hasAny(Handler h) {
    synchronized (this) {
      for (Message p = head; p != null; p = p.next) if (p.target == h) return true;
      return false;
    }
  }
  boolean has(Handler h, int what, Object obj, Runnable r) {
    synchronized (this) {
      for (Message p = head; p != null; p = p.next)
        if (p.target == h && (r != null ? p.callback == r : (p.callback == null && p.what == what)) && (obj == null || p.obj == obj)) return true;
      return false;
    }
  }

  void remove(Handler h, int what, Object obj, Runnable r, boolean all) {
    synchronized (this) {
      Message prev = null, p = head;
      while (p != null) {
        Message n = p.next;
        boolean match = p.target == h && (all || (r != null ? p.callback == r : (p.callback == null && p.what == what)))
            && (obj == null || p.obj == obj);
        if (match) { if (prev == null) head = n; else prev.next = n; }
        else prev = p;
        p = n;
      }
    }
  }

  public String debugString() {
    synchronized (this) {
      StringBuilder b = new StringBuilder();
      long now = SystemClock.uptimeMillis();
      int n = 0;
      for (Message p = head; p != null && n < 12; p = p.next, n++)
        b.append("[").append(p.when - now).append("ms ").append(p.target == null ? "-" : p.target.getClass().getName()).append(" what=").append(p.what)
            .append(p.callback != null ? " cb=" + p.callback.getClass().getName() : "").append("] ");
      return b.toString();
    }
  }
  void quit() { synchronized (this) { quitting = true; notifyAll(); } }
}
