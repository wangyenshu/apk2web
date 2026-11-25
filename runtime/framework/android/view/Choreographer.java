package android.view;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import java.util.ArrayList;

/**
 * Frame timing: callbacks run at the start of the next frame (the page's animation frame on the main thread; on other
 * looper threads every 16 ms), in Android's order: input, animation, insets animation, traversal, commit.
 */
public final class Choreographer {
  public interface FrameCallback { void doFrame(long frameTimeNanos); }
  public interface VsyncCallback { void onVsync(FrameData data); }
  public static final class FrameData {
    final long t;
    FrameData(long t) { this.t = t; }
    public long getFrameTimeNanos() { return t; }
  }
  public static final int CALLBACK_INPUT = 0, CALLBACK_ANIMATION = 1, CALLBACK_INSETS_ANIMATION = 2, CALLBACK_TRAVERSAL = 3, CALLBACK_COMMIT = 4;
  private static final int TYPES = 5;
  private static final long FRAME_NANOS = 16_666_667L;

  private static final class Entry {
    final long due; final Object action; final Object token;
    Entry(long due, Object action, Object token) { this.due = due; this.action = action; this.token = token; }
  }
  private static final ThreadLocal<Choreographer> sInstance = new ThreadLocal<>();
  private static Choreographer sMain;

  private final Looper mLooper;
  private final boolean mMain;
  private final Handler mHandler;
  @SuppressWarnings("unchecked")
  private final ArrayList<Entry>[] mQueues = new ArrayList[TYPES];
  private long mLastFrameNanos;
  private boolean mScheduled;

  private Choreographer(Looper looper) {
    mLooper = looper;
    mMain = looper == Looper.getMainLooper();
    mHandler = new Handler(looper);
    for (int i = 0; i < TYPES; i++) mQueues[i] = new ArrayList<>();
  }
  public static Choreographer getInstance() {
    Looper l = Looper.myLooper();
    if (l == null) l = Looper.getMainLooper();
    if (l == Looper.getMainLooper()) { if (sMain == null) sMain = new Choreographer(l); return sMain; }
    Choreographer c = sInstance.get();
    if (c == null) { c = new Choreographer(l); sInstance.set(c); }
    return c;
  }
  public static Choreographer getMainThreadInstance() { if (sMain == null) sMain = new Choreographer(Looper.getMainLooper()); return sMain; }
  public static long getFrameDelay() { return 10; }
  public static void setFrameDelay(long ms) {}

  public void postFrameCallback(FrameCallback cb) { postFrameCallbackDelayed(cb, 0); }
  public void postFrameCallbackDelayed(FrameCallback cb, long delayMillis) {
    if (cb == null) throw new IllegalArgumentException("callback must not be null");
    add(CALLBACK_ANIMATION, cb, FRAME_CALLBACK_TOKEN, delayMillis);
  }
  public void removeFrameCallback(FrameCallback cb) { remove(CALLBACK_ANIMATION, cb, FRAME_CALLBACK_TOKEN); }
  public void postCallback(int type, Runnable action, Object token) { postCallbackDelayed(type, action, token, 0); }
  public void postCallbackDelayed(int type, Runnable action, Object token, long delayMillis) {
    if (action == null) throw new IllegalArgumentException("action must not be null");
    add(type, action, token, delayMillis);
  }
  public void removeCallbacks(int type, Runnable action, Object token) { remove(type, action, token); }
  public void postVsyncCallback(VsyncCallback cb) { add(CALLBACK_ANIMATION, cb, FRAME_CALLBACK_TOKEN, 0); }
  public void removeVsyncCallback(VsyncCallback cb) { remove(CALLBACK_ANIMATION, cb, FRAME_CALLBACK_TOKEN); }
  public long getFrameTime() { return mLastFrameNanos / 1_000_000L; }
  public long getFrameTimeNanos() { return mLastFrameNanos; }
  public long getLastFrameTimeNanos() { return mLastFrameNanos; }
  public long getFrameIntervalNanos() { return FRAME_NANOS; }
  private static final Object FRAME_CALLBACK_TOKEN = new Object();

  private void add(int type, Object action, Object token, long delay) {
    if (type < 0 || type >= TYPES) throw new IllegalArgumentException("callbackType is invalid");
    synchronized (this) { mQueues[type].add(new Entry(SystemClock.uptimeMillis() + Math.max(0, delay), action, token)); }
    schedule(delay);
  }
  private void remove(int type, Object action, Object token) {
    if (type < 0 || type >= TYPES) return;
    synchronized (this) {
      for (int i = mQueues[type].size() - 1; i >= 0; i--) {
        Entry e = mQueues[type].get(i);
        if ((action == null || e.action == action) && (token == null || e.token == token)) mQueues[type].remove(i);
      }
    }
  }
  private void schedule(long delay) {
    if (mMain) {
      if (delay <= 0) a2w.Windows.scheduleFrame(false);
      else mHandler.postDelayed(() -> a2w.Windows.scheduleFrame(false), delay);
      return;
    }
    synchronized (this) { if (mScheduled) return; mScheduled = true; }
    mHandler.postDelayed(() -> { synchronized (this) { mScheduled = false; } doFrame(SystemClock.uptimeMillis()); }, Math.max(16, delay));
  }
  /** web: the main thread's frame (a2w.Windows, before layout and drawing) */
  public static void mainFrame(long nowMillis) { if (sMain != null) sMain.doFrame(nowMillis); }

  void doFrame(long nowMillis) {
    mLastFrameNanos = nowMillis * 1_000_000L;
    boolean more = false;
    for (int type = 0; type < TYPES; type++) {
      ArrayList<Entry> due = new ArrayList<>();
      synchronized (this) {
        for (int i = 0; i < mQueues[type].size(); ) {
          Entry e = mQueues[type].get(i);
          if (e.due <= nowMillis) { due.add(e); mQueues[type].remove(i); } else { i++; more = true; }
        }
      }
      for (Entry e : due) {
        try {
          if (e.action instanceof FrameCallback) ((FrameCallback) e.action).doFrame(mLastFrameNanos);
          else if (e.action instanceof VsyncCallback) ((VsyncCallback) e.action).onVsync(new FrameData(mLastFrameNanos));
          else ((Runnable) e.action).run();
        } catch (RuntimeException ex) { a2w.Host.uncaught(ex); }
      }
    }
    if (more) schedule(16);
  }
}
