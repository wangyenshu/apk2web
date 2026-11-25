package android.app;

import android.os.Handler;
import android.os.SystemClock;
import java.util.HashMap;

/** Alarms fire while the page is open (the device's AlarmManager keeps them across sleep; a browser tab can't). */
public class AlarmManager {
  public static final int RTC_WAKEUP = 0, RTC = 1, ELAPSED_REALTIME_WAKEUP = 2, ELAPSED_REALTIME = 3;
  public static final long INTERVAL_FIFTEEN_MINUTES = 900000L, INTERVAL_HALF_HOUR = 1800000L, INTERVAL_HOUR = 3600000L, INTERVAL_HALF_DAY = 43200000L, INTERVAL_DAY = 86400000L;
  public static final class AlarmClockInfo implements android.os.Parcelable {
    final long trigger; final PendingIntent show;
    public AlarmClockInfo(long triggerTime, PendingIntent showIntent) { trigger = triggerTime; show = showIntent; }
    public long getTriggerTime() { return trigger; }
    public PendingIntent getShowIntent() { return show; }
    public int describeContents() { return 0; }
    public void writeToParcel(android.os.Parcel p, int f) {}
  }
  public interface OnAlarmListener { void onAlarm(); }
  private static final HashMap<Object, Runnable> pending = new HashMap<>();
  private static Handler handler;
  private static Handler h() { if (handler == null) handler = new Handler(android.os.Looper.getMainLooper()); return handler; }

  private static long delay(int type, long at) {
    long now = type == RTC || type == RTC_WAKEUP ? System.currentTimeMillis() : SystemClock.elapsedRealtime();
    return Math.max(0, at - now);
  }
  private static void schedule(Object key, int type, long at, long interval, Runnable fire) {
    cancelKey(key);
    Runnable r = new Runnable() {
      long next = at;
      public void run() {
        if (interval > 0) { next += interval; h().postDelayed(this, delay(type, next)); } else pending.remove(key);
        try { fire.run(); } catch (Throwable t) { a2w.Host.uncaught(t); }
      }
    };
    pending.put(key, r);
    h().postDelayed(r, delay(type, at));
  }
  private static void cancelKey(Object key) { Runnable r = pending.remove(key); if (r != null) h().removeCallbacks(r); }
  private static Runnable sender(PendingIntent op) { return () -> { try { op.send(); } catch (PendingIntent.CanceledException e) { cancelKey(op); } }; }

  public void set(int type, long triggerAtMillis, PendingIntent op) { schedule(op, type, triggerAtMillis, 0, sender(op)); }
  public void set(int type, long triggerAtMillis, String tag, OnAlarmListener l, Handler target) { schedule(l, type, triggerAtMillis, 0, l::onAlarm); }
  public void setExact(int type, long triggerAtMillis, PendingIntent op) { set(type, triggerAtMillis, op); }
  public void setExact(int type, long triggerAtMillis, String tag, OnAlarmListener l, Handler target) { set(type, triggerAtMillis, tag, l, target); }
  public void setAndAllowWhileIdle(int type, long triggerAtMillis, PendingIntent op) { set(type, triggerAtMillis, op); }
  public void setExactAndAllowWhileIdle(int type, long triggerAtMillis, PendingIntent op) { set(type, triggerAtMillis, op); }
  public void setWindow(int type, long start, long length, PendingIntent op) { set(type, start, op); }
  public void setAlarmClock(AlarmClockInfo info, PendingIntent op) { set(RTC_WAKEUP, info.getTriggerTime(), op); }
  public void setRepeating(int type, long trigger, long interval, PendingIntent op) { schedule(op, type, trigger, Math.max(interval, 1000), sender(op)); }
  public void setInexactRepeating(int type, long trigger, long interval, PendingIntent op) { setRepeating(type, trigger, interval, op); }
  public void cancel(PendingIntent op) { cancelKey(op); }
  public void cancel(OnAlarmListener l) { cancelKey(l); }
  public boolean canScheduleExactAlarms() { return true; }
  public AlarmClockInfo getNextAlarmClock() { return null; }
  public void setTime(long m) {}
  public void setTimeZone(String tz) {}
}
