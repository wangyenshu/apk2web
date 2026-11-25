package android.content;

import android.os.Handler;
import android.os.Looper;

/** Loads on a worker thread (loadInBackground), delivers on the main thread. */
public abstract class AsyncTaskLoader<D> extends Loader<D> {
  private final Handler mHandler = new Handler(Looper.getMainLooper());
  private volatile int mGeneration;
  private volatile boolean mRunning;
  private long mUpdateThrottle;
  public AsyncTaskLoader(Context context) { super(context); }
  public void setUpdateThrottle(long delayMS) { mUpdateThrottle = delayMS; }
  @Override protected void onForceLoad() {
    super.onForceLoad();
    cancelLoad();
    final int gen = ++mGeneration;
    mRunning = true;
    Thread t = new Thread(() -> {
      D data = null;
      RuntimeException err = null;
      try { data = loadInBackground(); } catch (android.os.OperationCanceledException e) { data = null; } catch (RuntimeException e) { err = e; }
      final D result = data;
      final RuntimeException error = err;
      mHandler.post(() -> {
        mRunning = false;
        if (gen != mGeneration) { onCanceled(result); return; }
        if (error != null) { a2w.Host.uncaught(error); return; }
        if (isAbandoned()) onCanceled(result);
        else { commitContentChanged(); deliverResult(result); }
      });
    }, "AsyncTaskLoader");
    t.start();
  }
  @Override protected boolean onCancelLoad() {
    if (!mRunning) return false;
    mGeneration++;
    mRunning = false;
    cancelLoadInBackground();
    return true;
  }
  public void onCanceled(D data) {}
  public abstract D loadInBackground();
  public void cancelLoadInBackground() {}
  public boolean isLoadInBackgroundCanceled() { return !mRunning; }
  protected D onLoadInBackground() { return loadInBackground(); }
  public void waitForLoader() {}
}
