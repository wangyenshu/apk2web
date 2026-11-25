package android.database;
public class ContentObservable extends Observable<ContentObserver> {
  @Override public void registerObserver(ContentObserver o) { super.registerObserver(o); }
  public void dispatchChange(boolean selfChange) { dispatchChange(selfChange, null); }
  public void dispatchChange(boolean selfChange, android.net.Uri uri) {
    synchronized (mObservers) { for (ContentObserver o : new java.util.ArrayList<>(mObservers)) if (!selfChange || o.deliverSelfNotifications()) o.dispatchChange(selfChange, uri); }
  }
  @Deprecated public void notifyChange(boolean selfChange) {
    synchronized (mObservers) { for (ContentObserver o : new java.util.ArrayList<>(mObservers)) o.onChange(selfChange, null); }
  }
}
