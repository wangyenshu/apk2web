package android.widget;
public class RemoteViews implements android.os.Parcelable, Cloneable {
  public RemoteViews(String pkg, int layout) {}
  public void setTextViewText(int id, CharSequence t) {}
  public void setProgressBar(int id, int max, int progress, boolean ind) {}
  public void setImageViewResource(int id, int res) {}
  public void setViewVisibility(int id, int v) {}
  public void setOnClickPendingIntent(int id, android.app.PendingIntent p) {}
  public int describeContents() { return 0; }
  public void writeToParcel(android.os.Parcel p, int f) {}
  @Override public RemoteViews clone() {
    try { return (RemoteViews) super.clone(); } catch (CloneNotSupportedException e) { throw new RuntimeException(e); }
  }
}
