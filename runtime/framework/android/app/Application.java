package android.app;

import android.content.ComponentCallbacks;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import java.util.ArrayList;

public class Application extends ContextWrapper implements ComponentCallbacks2 {
  public interface ActivityLifecycleCallbacks {
    void onActivityCreated(Activity a, android.os.Bundle b);
    void onActivityStarted(Activity a);
    void onActivityResumed(Activity a);
    void onActivityPaused(Activity a);
    void onActivityStopped(Activity a);
    void onActivitySaveInstanceState(Activity a, android.os.Bundle b);
    void onActivityDestroyed(Activity a);
    default void onActivityPreCreated(Activity activity, android.os.Bundle savedInstanceState) {}
    default void onActivityPostCreated(Activity activity, android.os.Bundle savedInstanceState) {}
    default void onActivityPreStarted(Activity activity) {}
    default void onActivityPostStarted(Activity activity) {}
    default void onActivityPreResumed(Activity activity) {}
    default void onActivityPostResumed(Activity activity) {}
    default void onActivityPrePaused(Activity activity) {}
    default void onActivityPostPaused(Activity activity) {}
    default void onActivityPreStopped(Activity activity) {}
    default void onActivityPostStopped(Activity activity) {}
    default void onActivityPreSaveInstanceState(Activity activity, android.os.Bundle outState) {}
    default void onActivityPostSaveInstanceState(Activity activity, android.os.Bundle outState) {}
    default void onActivityPreDestroyed(Activity activity) {}
    default void onActivityPostDestroyed(Activity activity) {}
  }
  /** Application.OnProvideAssistDataListener (API 18) */
  public interface OnProvideAssistDataListener { void onProvideAssistData(Activity activity, android.os.Bundle data); }
  final ArrayList<OnProvideAssistDataListener> assistListeners = new ArrayList<>();
  public void registerOnProvideAssistDataListener(OnProvideAssistDataListener l) { assistListeners.add(l); }
  public void unregisterOnProvideAssistDataListener(OnProvideAssistDataListener l) { assistListeners.remove(l); }
  final ArrayList<ComponentCallbacks> componentCallbacks = new ArrayList<>();
  /** the registered lifecycle callbacks (a copy, safe to iterate while callbacks change it) */
  public ActivityLifecycleCallbacks[] webLifecycleCallbacks() { synchronized (callbacks) { return callbacks.toArray(new ActivityLifecycleCallbacks[0]); } }
  final ArrayList<ActivityLifecycleCallbacks> callbacks = new ArrayList<>();
  public Application() { super(null); }
  /** web: called by ActivityThread */
  public final void attach(Context base) { attachBaseContext(base); }
  public void onCreate() {}
  public void onTerminate() {}
  public void onConfigurationChanged(Configuration c) { for (ComponentCallbacks cb : componentCallbacks()) cb.onConfigurationChanged(c); }
  public void onLowMemory() { for (ComponentCallbacks cb : componentCallbacks()) cb.onLowMemory(); }
  public void onTrimMemory(int level) { for (ComponentCallbacks cb : componentCallbacks()) if (cb instanceof ComponentCallbacks2) ((ComponentCallbacks2) cb).onTrimMemory(level); }
  private ComponentCallbacks[] componentCallbacks() { synchronized (componentCallbacks) { return componentCallbacks.toArray(new ComponentCallbacks[0]); } }
  @Override public void registerComponentCallbacks(ComponentCallbacks cb) { synchronized (componentCallbacks) { componentCallbacks.add(cb); } }
  @Override public void unregisterComponentCallbacks(ComponentCallbacks cb) { synchronized (componentCallbacks) { componentCallbacks.remove(cb); } }
  public void registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks cb) { synchronized (callbacks) { callbacks.add(cb); } }
  public void unregisterActivityLifecycleCallbacks(ActivityLifecycleCallbacks cb) { synchronized (callbacks) { callbacks.remove(cb); } }
  public static String getProcessName() { return a2w.Res.pkg(); }
}
