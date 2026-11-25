package android.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/** Hosts embedded child activities (TabActivity, ActivityGroup): their windows' decor views are placed inside the parent. */
public class LocalActivityManager {
  private final Activity parent;
  private final boolean single;
  private final LinkedHashMap<String, Activity> acts = new LinkedHashMap<>();
  private Activity current;
  private String currentId;
  private boolean parentResumed;
  public LocalActivityManager(Activity parent, boolean singleMode) { this.parent = parent; this.single = singleMode; }

  public Window startActivity(String id, Intent intent) {
    Activity a = acts.get(id);
    if (a != null && a.mDestroyed) { acts.remove(id); a = null; }
    if (a != null && intent.getComponent() != null && !a.getClass().getName().equals(intent.getComponent().getClassName())) { destroyActivity(id, true); a = null; }
    if (current != null && current != a) {
      if (current.mResumed) ActivityHooks.call(current, "onPause");
      current.mResumed = false;
      if (single) destroyActivity(currentId, true);
    }
    if (a == null) {
      a = a2w.ActivityThread.embed(parent, intent);
      if (a == null) return null;
      acts.put(id, a);
    } else if ((intent.getFlags() & Intent.FLAG_ACTIVITY_CLEAR_TOP) == 0 && !intent.filterEquals(a.getIntent())) {
      ActivityHooks.newIntent(a, intent);
    }
    current = a;
    currentId = id;
    if (parentResumed && !a.mResumed) resume(a);
    return a.getWindow();
  }
  private static void resume(Activity a) {
    ActivityHooks.call(a, "onResume");
    a.mResumed = true;
    ActivityHooks.call(a, "onPostResume");
  }
  public Window destroyActivity(String id, boolean finish) {
    Activity a = acts.remove(id);
    if (a == null) return null;
    if (a.mResumed) { ActivityHooks.call(a, "onPause"); a.mResumed = false; }
    if (!a.mStopped) { a.mStopped = true; ActivityHooks.call(a, "onStop"); }
    if (!a.mDestroyed) { a.mDestroyed = true; a.mFinished = finish; ActivityHooks.call(a, "onDestroy"); }
    if (a == current) { current = null; currentId = null; }
    return a.getWindow();
  }
  public Activity getCurrentActivity() { return current; }
  public String getCurrentId() { return currentId; }
  public Activity getActivity(String id) { return acts.get(id); }
  public void dispatchCreate(Bundle state) {}
  public Bundle saveInstanceState() { return null; }
  public void dispatchResume() { parentResumed = true; if (current != null && !current.mResumed && !current.mDestroyed) resume(current); }
  public void dispatchPause(boolean finishing) { parentResumed = false; if (current != null && current.mResumed) { ActivityHooks.call(current, "onPause"); current.mResumed = false; } }
  public void dispatchStop() { for (Activity a : acts.values()) if (!a.mStopped) { a.mStopped = true; ActivityHooks.call(a, "onStop"); } }
  public void dispatchDestroy(boolean finishing) { for (String id : new ArrayList<>(acts.keySet())) destroyActivity(id, finishing); }
  public void removeAllActivities() { dispatchDestroy(true); }
}
