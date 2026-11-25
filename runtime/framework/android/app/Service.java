package android.app;

import android.content.*;
import android.os.IBinder;

public abstract class Service extends ContextWrapper implements ComponentCallbacks2 {
  public static final int START_STICKY_COMPATIBILITY = 0, START_STICKY = 1, START_NOT_STICKY = 2, START_REDELIVER_INTENT = 3, START_CONTINUATION_MASK = 15,
      START_FLAG_REDELIVERY = 1, START_FLAG_RETRY = 2, STOP_FOREGROUND_REMOVE = 1;
  public Service() { super(null); }
  /** web: what ActivityThread does when it creates a service */
  public final void attachWeb(Context base) { attachBaseContext(base); }
  public final Application getApplication() { return a2w.ActivityThread.application(); }
  public void onCreate() {}
  @Deprecated public void onStart(Intent intent, int startId) {}
  public int onStartCommand(Intent intent, int flags, int startId) { onStart(intent, startId); return START_STICKY; }
  public void onDestroy() {}
  public void onConfigurationChanged(android.content.res.Configuration c) {}
  public void onLowMemory() {}
  public void onTrimMemory(int level) {}
  public abstract IBinder onBind(Intent intent);
  public boolean onUnbind(Intent intent) { return false; }
  public void onRebind(Intent intent) {}
  public void onTaskRemoved(Intent rootIntent) {}
  public final void stopSelf() { a2w.ActivityThread.stopSelf(this, -1); }
  public final void stopSelf(int startId) { a2w.ActivityThread.stopSelf(this, startId); }
  public final boolean stopSelfResult(int startId) { return a2w.ActivityThread.stopSelf(this, startId); }
  public final void startForeground(int id, Notification n) {}
  public final void stopForeground(boolean remove) {}
  public final void setForeground(boolean f) {}
}
