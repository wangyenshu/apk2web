package android.os;

public class Handler {
  public interface Callback { boolean handleMessage(Message msg); }
  final Looper looper;
  final MessageQueue queue;
  final Callback callback;

  public Handler() { this((Callback) null); }
  public Handler(Callback cb) { this(cb, false); }
  public Handler(Looper l) { this(l, null); }
  public Handler(Looper l, Callback cb) { this(l, cb, false); }
  // hidden: asynchronous handlers (their messages skip sync barriers; here every message is equal)
  final boolean mAsynchronous;
  public Handler(boolean async) { this((Callback) null, async); }
  public Handler(Callback cb, boolean async) {
    Looper l = Looper.myLooper();
    if (l == null) l = Looper.getMainLooper();   // lenient: worker threads without loopers post to main
    looper = l; queue = l.queue; callback = cb; mAsynchronous = async;
  }
  public Handler(Looper l, Callback cb, boolean async) { looper = l; queue = l.queue; callback = cb; mAsynchronous = async; }
  public static Handler createAsync(Looper l) { return new Handler(l, null, true); }
  public static Handler createAsync(Looper l, Callback cb) { return new Handler(l, cb, true); }
  private static Handler sMain;
  public static Handler getMain() { if (sMain == null) sMain = new Handler(Looper.getMainLooper()); return sMain; }
  public static Handler mainIfNull(Handler h) { return h != null ? h : getMain(); }
  public String getMessageName(Message m) { return m.callback != null ? m.callback.getClass().getName() : "0x" + Integer.toHexString(m.what); }
  public final boolean hasCallbacks(Runnable r) { return queue.has(this, 0, null, r); }
  public final boolean hasMessagesOrCallbacks() { return queue.hasAny(this); }
  public final boolean postDelayed(Runnable r, Object token, long d) { Message m = Message.obtain(this, r); m.obj = token; return sendMessageDelayed(m, d); }
  public final void dump(android.util.Printer pw, String prefix) { pw.println(prefix + this + " @ " + SystemClock.uptimeMillis()); }
  /** runs r on this handler's thread and waits for it (at most timeout ms; 0: no limit) */
  public final boolean runWithScissors(Runnable r, long timeout) {
    if (Looper.myLooper() == looper) { r.run(); return true; }
    final boolean[] done = new boolean[1];
    final Object lock = new Object();
    post(new Scissors(r, done, lock));
    synchronized (lock) {
      long end = timeout > 0 ? SystemClock.uptimeMillis() + timeout : Long.MAX_VALUE;
      while (!done[0]) {
        long left = end - SystemClock.uptimeMillis();
        if (left <= 0) return false;
        try { lock.wait(Math.min(left, 1000)); } catch (InterruptedException e) { return false; }
      }
    }
    return true;
  }
  static final class Scissors implements Runnable {
    final Runnable r; final boolean[] done; final Object lock;
    Scissors(Runnable r, boolean[] done, Object lock) { this.r = r; this.done = done; this.lock = lock; }
    public void run() { try { r.run(); } finally { synchronized (lock) { done[0] = true; lock.notifyAll(); } } }
  }
  @Override public String toString() { return "Handler (" + getClass().getName() + ") {" + Integer.toHexString(System.identityHashCode(this)) + "}"; }

  public void handleMessage(Message msg) {}

  public void dispatchMessage(Message msg) {
    if (msg.callback != null) { msg.callback.run(); return; }
    if (callback != null && callback.handleMessage(msg)) return;
    handleMessage(msg);
  }

  public final Looper getLooper() { return looper; }
  public final Message obtainMessage() { return Message.obtain(this); }
  public final Message obtainMessage(int what) { return Message.obtain(this, what); }
  public final Message obtainMessage(int what, Object obj) { return Message.obtain(this, what, obj); }
  public final Message obtainMessage(int what, int a1, int a2) { return Message.obtain(this, what, a1, a2); }
  public final Message obtainMessage(int what, int a1, int a2, Object obj) { return Message.obtain(this, what, a1, a2, obj); }

  public final boolean post(Runnable r) { return sendMessageDelayed(Message.obtain(this, r), 0); }
  public final boolean postAtTime(Runnable r, long t) { return sendMessageAtTime(Message.obtain(this, r), t); }
  public final boolean postAtTime(Runnable r, Object token, long t) { Message m = Message.obtain(this, r); m.obj = token; return sendMessageAtTime(m, t); }
  public final boolean postDelayed(Runnable r, long d) { return sendMessageDelayed(Message.obtain(this, r), d); }
  public final boolean postAtFrontOfQueue(Runnable r) { return sendMessageAtFrontOfQueue(Message.obtain(this, r)); }
  public final void removeCallbacks(Runnable r) { queue.remove(this, 0, null, r, false); }
  public final void removeCallbacks(Runnable r, Object token) { queue.remove(this, 0, token, r, false); }

  public final boolean sendMessage(Message m) { return sendMessageDelayed(m, 0); }
  public final boolean sendEmptyMessage(int what) { return sendMessageDelayed(obtainMessage(what), 0); }
  public final boolean sendEmptyMessageDelayed(int what, long d) { return sendMessageDelayed(obtainMessage(what), d); }
  public final boolean sendEmptyMessageAtTime(int what, long t) { return sendMessageAtTime(obtainMessage(what), t); }
  public final boolean sendMessageDelayed(Message m, long d) { return sendMessageAtTime(m, SystemClock.uptimeMillis() + Math.max(0, d)); }
  public boolean sendMessageAtTime(Message m, long t) { m.target = this; if (mAsynchronous) m.setAsynchronous(true); return queue.enqueue(m, t); }
  public final boolean sendMessageAtFrontOfQueue(Message m) { m.target = this; return queue.enqueue(m, Long.MIN_VALUE); }

  public final void removeMessages(int what) { queue.remove(this, what, null, null, false); }
  public final void removeMessages(int what, Object obj) { queue.remove(this, what, obj, null, false); }
  public final void removeCallbacksAndMessages(Object token) { queue.remove(this, 0, token, null, true); }
  public final boolean hasMessages(int what) { return queue.has(this, what, null, null); }
  public final boolean hasMessages(int what, Object obj) { return queue.has(this, what, obj, null); }
}
