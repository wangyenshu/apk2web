package android.app;
public class NotificationManager {
  public void notify(int id, Notification n) { android.util.Log.i("NotificationManager", "notify " + id + ": " + n.tickerText); }
  public void notify(String tag, int id, Notification n) { notify(id, n); }
  public void cancel(int id) {}
  public void cancel(String tag, int id) {}
  public void cancelAll() {}
}
