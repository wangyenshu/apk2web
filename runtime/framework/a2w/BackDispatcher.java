package a2w;

/** An activity's OnBackInvokedDispatcher: the back key goes to the callback of highest priority registered last. */
public final class BackDispatcher implements android.window.OnBackInvokedDispatcher {
  private final java.util.ArrayList<android.window.OnBackInvokedCallback> callbacks = new java.util.ArrayList<>();
  private final java.util.ArrayList<Integer> priorities = new java.util.ArrayList<>();
  @Override public void registerOnBackInvokedCallback(int priority, android.window.OnBackInvokedCallback callback) {
    if (priority < 0) throw new IllegalArgumentException("Application registered OnBackInvokedCallback cannot have negative priority. Priority: " + priority);
    unregisterOnBackInvokedCallback(callback);
    callbacks.add(callback);
    priorities.add(priority);
  }
  @Override public void unregisterOnBackInvokedCallback(android.window.OnBackInvokedCallback callback) {
    int i = callbacks.indexOf(callback);
    if (i >= 0) { callbacks.remove(i); priorities.remove(i); }
  }
  /** calls the top callback; false when none is registered */
  public boolean dispatch() {
    int best = -1;
    for (int i = 0; i < callbacks.size(); i++) if (best < 0 || priorities.get(i) >= priorities.get(best)) best = i;
    if (best < 0) return false;
    callbacks.get(best).onBackInvoked();
    return true;
  }
}
