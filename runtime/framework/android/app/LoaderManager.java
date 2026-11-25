package android.app;

import android.content.Loader;
import android.os.Bundle;
import android.util.SparseArray;

/** Runs the app's Loaders and reports their data to the callbacks on the main thread. */
public abstract class LoaderManager {
  public interface LoaderCallbacks<D> {
    Loader<D> onCreateLoader(int id, Bundle args);
    void onLoadFinished(Loader<D> loader, D data);
    void onLoaderReset(Loader<D> loader);
  }
  public abstract <D> Loader<D> initLoader(int id, Bundle args, LoaderManager.LoaderCallbacks<D> callback);
  public abstract <D> Loader<D> restartLoader(int id, Bundle args, LoaderManager.LoaderCallbacks<D> callback);
  public abstract void destroyLoader(int id);
  public abstract <D> Loader<D> getLoader(int id);
  public abstract void dump(String prefix, java.io.FileDescriptor fd, java.io.PrintWriter writer, String[] args);
  public static void enableDebugLogging(boolean enabled) {}

  static final class Impl extends LoaderManager {
    final SparseArray<Object[]> loaders = new SparseArray<>();   // {Loader, LoaderCallbacks, Object data, Boolean haveData}
    boolean started = true;
    @SuppressWarnings("unchecked")
    public <D> Loader<D> initLoader(int id, Bundle args, LoaderCallbacks<D> callback) {
      Object[] info = loaders.get(id);
      if (info == null) return create(id, args, callback);
      info[1] = callback;
      if ((Boolean) info[3]) deliver(info);
      return (Loader<D>) info[0];
    }
    public <D> Loader<D> restartLoader(int id, Bundle args, LoaderCallbacks<D> callback) {
      destroyLoader(id);
      return create(id, args, callback);
    }
    @SuppressWarnings({"unchecked", "rawtypes"})
    private <D> Loader<D> create(int id, Bundle args, LoaderCallbacks<D> callback) {
      Loader<D> loader = callback.onCreateLoader(id, args);
      if (loader == null) return null;
      final Object[] info = {loader, callback, null, false};
      loaders.put(id, info);
      loader.registerListener(id, (Loader.OnLoadCompleteListener) (l, data) -> { info[2] = data; info[3] = true; deliver(info); });
      if (started) loader.startLoading();
      return loader;
    }
    @SuppressWarnings({"unchecked", "rawtypes"})
    void deliver(Object[] info) { ((LoaderCallbacks) info[1]).onLoadFinished((Loader) info[0], info[2]); }
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void destroyLoader(int id) {
      Object[] info = loaders.get(id);
      if (info == null) return;
      loaders.remove(id);
      Loader l = (Loader) info[0];
      if ((Boolean) info[3]) ((LoaderCallbacks) info[1]).onLoaderReset(l);
      l.reset();
    }
    @SuppressWarnings("unchecked")
    public <D> Loader<D> getLoader(int id) { Object[] info = loaders.get(id); return info == null ? null : (Loader<D>) info[0]; }
    public void dump(String prefix, java.io.FileDescriptor fd, java.io.PrintWriter writer, String[] args) {}
    void doStart() { started = true; for (int i = 0; i < loaders.size(); i++) ((Loader<?>) loaders.valueAt(i)[0]).startLoading(); }
    void doStop() { started = false; for (int i = 0; i < loaders.size(); i++) ((Loader<?>) loaders.valueAt(i)[0]).stopLoading(); }
    void doDestroy() { for (int i = loaders.size() - 1; i >= 0; i--) destroyLoader(loaders.keyAt(i)); }
  }
}
