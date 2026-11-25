package android.app;

import android.content.Context;

public class Notification implements android.os.Parcelable, Cloneable {
  public static final int DEFAULT_ALL = -1, DEFAULT_SOUND = 1, DEFAULT_VIBRATE = 2, DEFAULT_LIGHTS = 4;
  public static final int FLAG_SHOW_LIGHTS = 1, FLAG_ONGOING_EVENT = 2, FLAG_INSISTENT = 4, FLAG_ONLY_ALERT_ONCE = 8, FLAG_AUTO_CANCEL = 16, FLAG_NO_CLEAR = 32, FLAG_FOREGROUND_SERVICE = 64;
  public static final int PRIORITY_DEFAULT = 0;
  public int icon, iconLevel, number, defaults, flags, ledARGB, ledOnMS, ledOffMS, audioStreamType, priority;
  public long when;
  public PendingIntent contentIntent, deleteIntent, fullScreenIntent;
  public CharSequence tickerText;
  public android.widget.RemoteViews contentView;
  public android.net.Uri sound;
  public long[] vibrate;
  public Notification() { when = System.currentTimeMillis(); }
  @Override public Notification clone() {
    try { return (Notification) super.clone(); } catch (CloneNotSupportedException e) { throw new RuntimeException(e); }
  }
  public Notification(int icon, CharSequence ticker, long when) { this.icon = icon; tickerText = ticker; this.when = when; }
  public void setLatestEventInfo(Context c, CharSequence title, CharSequence text, PendingIntent intent) { contentIntent = intent; }
  public int describeContents() { return 0; }
  public void writeToParcel(android.os.Parcel p, int f) {}
  public static class Builder {
    final Notification n = new Notification();
    public Builder(Context c) {}
    public Builder setSmallIcon(int i) { n.icon = i; return this; }
    public Builder setContentTitle(CharSequence t) { return this; }
    public Builder setContentText(CharSequence t) { return this; }
    public Builder setTicker(CharSequence t) { n.tickerText = t; return this; }
    public Builder setWhen(long w) { n.when = w; return this; }
    public Builder setContentIntent(PendingIntent i) { n.contentIntent = i; return this; }
    public Builder setAutoCancel(boolean a) { return this; }
    public Builder setOngoing(boolean o) { return this; }
    public Builder setDefaults(int d) { n.defaults = d; return this; }
    public Builder setProgress(int max, int p, boolean ind) { return this; }
    public Notification getNotification() { return n; }
    public Notification build() { return n; }
  }
}
