package android.database;

import android.net.Uri;
import android.os.Handler;

public abstract class ContentObserver {
  final Handler mHandler;
  public ContentObserver(Handler handler) { mHandler = handler; }
  public boolean deliverSelfNotifications() { return false; }
  public void onChange(boolean selfChange) {}
  public void onChange(boolean selfChange, Uri uri) { onChange(selfChange); }
  public void onChange(boolean selfChange, Uri uri, int flags) { onChange(selfChange, uri); }
  @Deprecated public final void dispatchChange(boolean selfChange) { dispatchChange(selfChange, null); }
  public final void dispatchChange(boolean selfChange, Uri uri) {
    if (mHandler == null) onChange(selfChange, uri);
    else mHandler.post(() -> onChange(selfChange, uri));
  }
}
