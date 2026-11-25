package android.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;

/**
 * android.app.Instrumentation: the parts apps call themselves (sending key events, e.g. a "back" button that sends
 * KEYCODE_BACK, and the callActivityOn* hooks); events go to the front activity on the main thread.
 */
public class Instrumentation {
  public static final String REPORT_KEY_IDENTIFIER = "id", REPORT_KEY_STREAMRESULT = "stream";
  public Instrumentation() {}
  public void onCreate(Bundle arguments) {}
  public void start() {}
  public void onStart() {}
  public boolean onException(Object obj, Throwable e) { return false; }
  public void finish(int resultCode, Bundle results) {}
  public Context getContext() { return a2w.ActivityThread.baseContext(); }
  public Context getTargetContext() { return a2w.ActivityThread.application(); }
  public String getProcessName() { return a2w.Res.pkg(); }
  public boolean isProfiling() { return false; }
  public void startProfiling() {}
  public void stopProfiling() {}
  public void setInTouchMode(boolean inTouch) {}
  public void waitForIdleSync() {}
  public void waitForIdle(Runnable recipient) { android.os.Looper.myQueue(); new android.os.Handler(android.os.Looper.getMainLooper()).post(recipient); }
  public void runOnMainSync(Runnable runner) {
    if (android.os.Looper.myLooper() == android.os.Looper.getMainLooper()) { runner.run(); return; }
    final Object lock = new Object();
    final boolean[] done = {false};
    new android.os.Handler(android.os.Looper.getMainLooper()).post(() -> { try { runner.run(); } finally { synchronized (lock) { done[0] = true; lock.notifyAll(); } } });
    synchronized (lock) { while (!done[0]) { try { lock.wait(); } catch (InterruptedException e) { return; } } }
  }
  public Activity startActivitySync(Intent intent) { a2w.ActivityThread.application().startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)); return null; }
  /** the key goes through the key window's input path (touch mode, the view tree, focus navigation), as an injected key does */
  public void sendKeySync(KeyEvent event) { runOnMainSync(() -> a2w.Windows.injectKey(event)); }
  public void sendKeyDownUpSync(int key) {
    sendKeySync(new KeyEvent(KeyEvent.ACTION_DOWN, key));
    sendKeySync(new KeyEvent(KeyEvent.ACTION_UP, key));
  }
  public void sendCharacterSync(int keyCode) { sendKeyDownUpSync(keyCode); }
  public void sendStringSync(String text) {
    if (text == null) return;
    for (char c : text.toCharArray()) {
      Activity a = topActivity();
      if (a != null && a.getCurrentFocus() instanceof android.widget.TextView) {
        android.widget.TextView v = (android.widget.TextView) a.getCurrentFocus();
        runOnMainSync(() -> v.append(String.valueOf(c)));
      }
    }
  }
  public void sendPointerSync(MotionEvent event) { runOnMainSync(() -> a2w.Windows.injectTouch(event)); }
  public void sendTrackballEventSync(MotionEvent event) {}
  static Activity topActivity() { return a2w.ActivityThread.front(); }
  public void callActivityOnCreate(Activity activity, Bundle icicle) { activity.onCreate(icicle); }
  public void callActivityOnDestroy(Activity activity) {}
  public void callActivityOnRestoreInstanceState(Activity activity, Bundle savedInstanceState) { activity.onRestoreInstanceState(savedInstanceState); }
  public void callActivityOnPostCreate(Activity activity, Bundle icicle) { activity.onPostCreate(icicle); }
  public void callActivityOnNewIntent(Activity activity, Intent intent) { activity.onNewIntent(intent); }
  public void callActivityOnStart(Activity activity) { activity.onStart(); }
  public void callActivityOnRestart(Activity activity) { activity.onRestart(); }
  public void callActivityOnResume(Activity activity) { activity.onResume(); }
  public void callActivityOnStop(Activity activity) { activity.onStop(); }
  public void callActivityOnSaveInstanceState(Activity activity, Bundle outState) { activity.onSaveInstanceState(outState); }
  public void callActivityOnPause(Activity activity) { activity.onPause(); }
  public void callActivityOnUserLeaving(Activity activity) { activity.onUserLeaveHint(); }
  public void callApplicationOnCreate(Application app) { app.onCreate(); }
  public Activity newActivity(ClassLoader cl, String className, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    return (Activity) a2w.jdk.Compat.Class_newInstance(a2w.Reflection.forName(className));
  }
  public Application newApplication(ClassLoader cl, String className, Context context) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    return (Application) a2w.jdk.Compat.Class_newInstance(a2w.Reflection.forName(className));
  }
  public static Application newApplication(Class<?> clazz, Context context) throws InstantiationException, IllegalAccessException {
    return (Application) a2w.jdk.Compat.Class_newInstance(clazz);
  }
  public static final class ActivityResult {
    private final int mResultCode;
    private final Intent mResultData;
    public ActivityResult(int resultCode, Intent resultData) { mResultCode = resultCode; mResultData = resultData; }
    public int getResultCode() { return mResultCode; }
    public Intent getResultData() { return mResultData; }
  }
  public static class ActivityMonitor {
    public ActivityMonitor(String cls, ActivityResult result, boolean block) {}
    public final Activity getLastActivity() { return null; }
    public final int getHits() { return 0; }
  }
}
