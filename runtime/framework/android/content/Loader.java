package android.content;

public class Loader<D> {
  public interface OnLoadCompleteListener<D> { void onLoadComplete(Loader<D> loader, D data); }
  public interface OnLoadCanceledListener<D> { void onLoadCanceled(Loader<D> loader); }
  public final class ForceLoadContentObserver extends android.database.ContentObserver {
    public ForceLoadContentObserver() { super(new android.os.Handler()); }
    @Override public boolean deliverSelfNotifications() { return true; }
    @Override public void onChange(boolean selfChange) { onContentChanged(); }
  }
  int mId;
  OnLoadCompleteListener<D> mListener;
  OnLoadCanceledListener<D> mOnLoadCanceledListener;
  Context mContext;
  boolean mStarted, mAbandoned, mReset = true, mContentChanged, mProcessingChange;
  public Loader(Context context) { mContext = context.getApplicationContext() != null ? context.getApplicationContext() : context; }
  public void deliverResult(D data) { if (mListener != null) mListener.onLoadComplete(this, data); }
  public void deliverCancellation() { if (mOnLoadCanceledListener != null) mOnLoadCanceledListener.onLoadCanceled(this); }
  public Context getContext() { return mContext; }
  public int getId() { return mId; }
  public void registerListener(int id, OnLoadCompleteListener<D> listener) {
    if (mListener != null) throw new IllegalStateException("There is already a listener registered");
    mListener = listener; mId = id;
  }
  public void unregisterListener(OnLoadCompleteListener<D> listener) { if (mListener == listener) mListener = null; }
  public void registerOnLoadCanceledListener(OnLoadCanceledListener<D> listener) { mOnLoadCanceledListener = listener; }
  public void unregisterOnLoadCanceledListener(OnLoadCanceledListener<D> listener) { if (mOnLoadCanceledListener == listener) mOnLoadCanceledListener = null; }
  public boolean isStarted() { return mStarted; }
  public boolean isAbandoned() { return mAbandoned; }
  public boolean isReset() { return mReset; }
  public final void startLoading() { mStarted = true; mReset = false; mAbandoned = false; onStartLoading(); }
  protected void onStartLoading() {}
  public boolean cancelLoad() { return onCancelLoad(); }
  protected boolean onCancelLoad() { return false; }
  public void forceLoad() { onForceLoad(); }
  protected void onForceLoad() {}
  public void stopLoading() { mStarted = false; onStopLoading(); }
  protected void onStopLoading() {}
  public void abandon() { mAbandoned = true; onAbandon(); }
  protected void onAbandon() {}
  public void reset() { onReset(); mReset = true; mStarted = false; mAbandoned = false; mContentChanged = false; mProcessingChange = false; }
  protected void onReset() {}
  public boolean takeContentChanged() { boolean res = mContentChanged; mContentChanged = false; mProcessingChange |= res; return res; }
  public void commitContentChanged() { mProcessingChange = false; }
  public void rollbackContentChanged() { if (mProcessingChange) onContentChanged(); }
  public void onContentChanged() { if (mStarted) forceLoad(); else mContentChanged = true; }
  public String dataToString(D data) { return String.valueOf(data); }
  @Override public String toString() { return getClass().getSimpleName() + "{" + Integer.toHexString(System.identityHashCode(this)) + " id=" + mId + "}"; }
  public void dump(String prefix, java.io.FileDescriptor fd, java.io.PrintWriter writer, String[] args) {}
}
