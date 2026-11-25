package android.os;

public abstract class CountDownTimer {
  private final long millisInFuture, interval;
  private long stopAt;
  private boolean cancelled;
  private final Handler handler = new Handler(Looper.getMainLooper()) {
    @Override public void handleMessage(Message msg) {
      synchronized (CountDownTimer.this) {
        if (cancelled) return;
        long left = stopAt - SystemClock.elapsedRealtime();
        if (left <= 0) onFinish();
        else if (left < interval) sendMessageDelayed(obtainMessage(1), left);
        else {
          long start = SystemClock.elapsedRealtime();
          onTick(left);
          long delay = start + interval - SystemClock.elapsedRealtime();
          while (delay < 0) delay += interval;
          sendMessageDelayed(obtainMessage(1), delay);
        }
      }
    }
  };
  public CountDownTimer(long millisInFuture, long countDownInterval) { this.millisInFuture = millisInFuture; this.interval = countDownInterval; }
  public final synchronized void cancel() { cancelled = true; handler.removeMessages(1); }
  public final synchronized CountDownTimer start() {
    cancelled = false;
    if (millisInFuture <= 0) { onFinish(); return this; }
    stopAt = SystemClock.elapsedRealtime() + millisInFuture;
    handler.sendMessage(handler.obtainMessage(1));
    return this;
  }
  public abstract void onTick(long millisUntilFinished);
  public abstract void onFinish();
}
