package android.app;

import android.content.Intent;
import android.os.Bundle;

/** Lets the web ActivityThread call the protected lifecycle methods. */
public final class ActivityHooks {
  private ActivityHooks() {}
  public static void call(Activity a, String what) {
    a.dispatchLifecycle(what, true, null);
    a.mCalled = false;
    // as Activity.performXxx: the activity's fragments, loaders and managed cursors follow its state
    switch (what) {
      case "onStart": a.onStart(); a.mFragments.dispatchStart(); if (a.mLoaderManager != null) a.mLoaderManager.doStart(); break;
      case "onPostCreate": a.onPostCreate(null); break;
      case "onRestart": a.managedCursors(1); a.onRestart(); break;
      case "onResume": a.onResume(); a.mFragments.dispatchResume(); break;
      case "onPostResume": a.onPostResume(); break;
      case "onPause": a.mFragments.dispatchPause(); a.onPause(); break;
      case "onStop": a.mFragments.dispatchStop(); if (a.mLoaderManager != null) a.mLoaderManager.doStop(); a.onStop(); a.managedCursors(0); break;
      case "onDestroy": a.mFragments.dispatchDestroy(); if (a.mLoaderManager != null) a.mLoaderManager.doDestroy(); a.onDestroy(); a.managedCursors(2); break;
    }
    if (!a.mCalled) android.util.Log.w("ActivityThread", "Activity " + a.getClass().getName() + " did not call through to super." + what + "()");
    a.dispatchLifecycle(what, false, null);
  }
  public static void create(Activity a, Bundle b) {
    a.dispatchLifecycle("onCreate", true, b);
    a.mCalled = false;
    a.onCreate(b);
    if (!a.mCalled) android.util.Log.w("ActivityThread", "Activity " + a.getClass().getName() + " did not call through to super.onCreate()");
    a.mFragments.dispatchActivityCreated();
    a.dispatchLifecycle("onCreate", false, b);
  }
  public static void newIntent(Activity a, Intent i) { a.onNewIntent(i); }
  /** Activity.performSaveInstanceState: the state the activity and its window keep */
  public static Bundle saveState(Activity a) {
    Bundle b = new Bundle();
    a.dispatchLifecycle("onSaveInstanceState", true, b);
    a.onSaveInstanceState(b);
    a.dispatchLifecycle("onSaveInstanceState", false, b);
    return b;
  }
  public static void restoreState(Activity a, Bundle b) { a.onRestoreInstanceState(b); }
  public static void postCreate(Activity a, Bundle b) { a.mCalled = false; a.onPostCreate(b); }
  public static Object retainNonConfiguration(Activity a) { return a.onRetainNonConfigurationInstance(); }
  /** whether the activity has platform fragments (their state is not kept: a recreated activity starts without saved state) */
  public static boolean hasFragments(Activity a) { return !a.mFragments.mAdded.isEmpty(); }
  public static void result(Activity a, int req, int res, Intent data) { a.dispatchActivityResult(req, res, data); }
  public static void dismissDialogs(Activity a) { Dialog.dismissAll(a); }
}
