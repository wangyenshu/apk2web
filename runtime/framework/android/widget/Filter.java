package android.widget;

import android.os.Handler;
import android.os.Looper;

/** Filtering runs on the calling turn's next message (no worker thread), then publishes on the main thread. */
public abstract class Filter {
  public interface FilterListener { void onFilterComplete(int count); }
  public interface Delayer { long getPostingDelay(CharSequence constraint); }
  protected static class FilterResults { public Object values; public int count; public FilterResults() {} }
  private Handler mHandler;
  private int mGeneration;
  public Filter() {}
  public void setDelayer(Delayer delayer) {}
  public final void filter(CharSequence constraint) { filter(constraint, null); }
  public final void filter(CharSequence constraint, FilterListener listener) {
    if (mHandler == null) mHandler = new Handler(Looper.getMainLooper());
    final int gen = ++mGeneration;
    final CharSequence c = constraint == null ? null : constraint.toString();
    mHandler.post(() -> {
      if (gen != mGeneration) return;
      FilterResults r;
      try { r = performFiltering(c); } catch (RuntimeException e) { android.util.Log.w("Filter", "An exception occured during performFiltering()!", e); r = new FilterResults(); }
      if (gen != mGeneration) return;
      publishResults(c, r);
      if (listener != null) listener.onFilterComplete(r != null ? r.count : -1);
    });
  }
  protected abstract FilterResults performFiltering(CharSequence constraint);
  protected abstract void publishResults(CharSequence constraint, FilterResults results);
  public CharSequence convertResultToString(Object resultValue) { return resultValue == null ? "" : resultValue.toString(); }
}
