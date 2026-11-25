package android.content;

import android.os.Bundle;

/** android.content.BroadcastReceiver: the result of an ordered broadcast lives in the pending result. */
public abstract class BroadcastReceiver {
  /** the state of a broadcast being received; finish() lets an ordered broadcast go on */
  public static class PendingResult {
    int mResultCode; String mResultData; Bundle mResultExtras;
    boolean mAbortBroadcast, mFinished;
    final boolean mOrderedHint, mInitialStickyHint;
    private final java.util.function.Consumer<PendingResult> mOnFinish;
    PendingResult(int resultCode, String resultData, Bundle resultExtras, boolean ordered, boolean sticky, java.util.function.Consumer<PendingResult> onFinish) {
      mResultCode = resultCode; mResultData = resultData; mResultExtras = resultExtras; mOrderedHint = ordered; mInitialStickyHint = sticky; mOnFinish = onFinish;
    }
    /** (not part of the API: made by the broadcast queue, a2w.Broadcasts) */
    public static PendingResult webCreate(int resultCode, String resultData, Bundle resultExtras, boolean ordered, boolean sticky, java.util.function.Consumer<PendingResult> onFinish) {
      return new PendingResult(resultCode, resultData, resultExtras, ordered, sticky, onFinish);
    }
    public final void setResultCode(int code) { checkOrdered(); mResultCode = code; }
    public final int getResultCode() { return mResultCode; }
    public final void setResultData(String data) { checkOrdered(); mResultData = data; }
    public final String getResultData() { return mResultData; }
    public final void setResultExtras(Bundle extras) { checkOrdered(); mResultExtras = extras; }
    public final Bundle getResultExtras(boolean makeMap) { Bundle e = mResultExtras; if (!makeMap) return e; if (e == null) mResultExtras = e = new Bundle(); return e; }
    public final void setResult(int code, String data, Bundle extras) { checkOrdered(); mResultCode = code; mResultData = data; mResultExtras = extras; }
    public final boolean getAbortBroadcast() { return mAbortBroadcast; }
    public final void abortBroadcast() { checkOrdered(); mAbortBroadcast = true; }
    public final void clearAbortBroadcast() { mAbortBroadcast = false; }
    public final void finish() {
      if (mFinished) throw new IllegalStateException("Broadcast already finished");
      mFinished = true;
      if (mOnFinish != null) mOnFinish.accept(this);
    }
    void checkOrdered() {
      if (!mOrderedHint) android.util.Log.e("BroadcastReceiver", "BroadcastReceiver trying to return result during a non-ordered broadcast", new RuntimeException("here"));
    }
  }

  private PendingResult mPendingResult;
  private boolean mDebugUnregister;

  public BroadcastReceiver() {}
  public abstract void onReceive(Context context, Intent intent);

  public final PendingResult goAsync() { PendingResult r = mPendingResult; mPendingResult = null; return r; }
  /** (hidden in Android; used by the broadcast queue) */
  public final void setPendingResult(PendingResult result) { mPendingResult = result; }
  public final PendingResult getPendingResult() { return mPendingResult; }
  private PendingResult pending() { if (mPendingResult == null) throw new IllegalStateException("Call while result is not pending"); return mPendingResult; }

  public final void setResultCode(int code) { pending().setResultCode(code); }
  public final int getResultCode() { return mPendingResult != null ? mPendingResult.mResultCode : 0; }
  public final void setResultData(String data) { pending().setResultData(data); }
  public final String getResultData() { return mPendingResult != null ? mPendingResult.mResultData : null; }
  public final void setResultExtras(Bundle extras) { pending().setResultExtras(extras); }
  public final Bundle getResultExtras(boolean makeMap) { return mPendingResult == null ? null : mPendingResult.getResultExtras(makeMap); }
  public final void setResult(int code, String data, Bundle extras) { pending().setResult(code, data, extras); }
  public final boolean getAbortBroadcast() { return mPendingResult != null && mPendingResult.mAbortBroadcast; }
  public final void abortBroadcast() { pending().abortBroadcast(); }
  public final void clearAbortBroadcast() { if (mPendingResult != null) mPendingResult.mAbortBroadcast = false; }
  public final boolean isOrderedBroadcast() { return mPendingResult != null && mPendingResult.mOrderedHint; }
  public final boolean isInitialStickyBroadcast() { return mPendingResult != null && mPendingResult.mInitialStickyHint; }
  public final void setOrderedHint(boolean isOrdered) {}
  public final void setDebugUnregister(boolean debug) { mDebugUnregister = debug; }
  public final boolean getDebugUnregister() { return mDebugUnregister; }
  /** the binder of the service, if it is running and bound */
  public android.os.IBinder peekService(Context myContext, Intent service) { return a2w.ActivityThread.peekService(service); }
  public int getSentFromUid() { return android.os.Process.myUid(); }
  public String getSentFromPackage() { return a2w.Res.pkg(); }
}
