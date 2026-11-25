package android.os;

public final class Looper {
  private static final ThreadLocal<Looper> local = new ThreadLocal<>();
  private static Looper main;
  final MessageQueue queue = new MessageQueue();
  final Thread thread;

  private Looper() { thread = Thread.currentThread(); }

  public static void prepare() {
    if (local.get() != null) throw new RuntimeException("Only one Looper may be created per thread");
    local.set(new Looper());
  }
  public static void prepareMainLooper() { prepare(); main = local.get(); }
  public static Looper getMainLooper() { return main; }
  public static Looper myLooper() { return local.get(); }
  public static MessageQueue myQueue() { return local.get().queue; }
  public Thread getThread() { return thread; }
  public MessageQueue getQueue() { return queue; }
  public void quit() { queue.quit(); }

  public static void loop() {
    Looper me = myLooper();
    while (true) {
      Message m = me.queue.next();
      if (m == null) return;
      if (me == main) dispatching = m;
      try {
        m.target.dispatchMessage(m);
      } catch (Throwable t) {
        a2w.Host.uncaught(t);
      }
      if (me == main) dispatching = null;
    }
  }
  /** the message the main thread is handling (diagnostics: a main thread that waits for good) */
  public static Message dispatching;

  /** Runs everything already due, without blocking (used for nested loops). */
  public static boolean runPending() {
    Looper me = myLooper();
    Message m;
    synchronized (me.queue) {
      if (me.queue.head == null || me.queue.head.when > SystemClock.uptimeMillis()) return false;
      m = me.queue.head; me.queue.head = m.next; m.next = null;
    }
    try { m.target.dispatchMessage(m); } catch (Throwable t) { a2w.Host.uncaught(t); }
    return true;
  }
}
