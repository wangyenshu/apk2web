package a2w;

import android.app.Activity;
import android.app.Application;
import android.content.*;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;

/** Activity stack and lifecycle (what ActivityManagerService + ActivityThread do on a device). */
public final class ActivityThread {
  private ActivityThread() {}
  static final String TAG = "ActivityManager";
  static Application app;
  static ContextImpl base;
  static final ArrayList<Activity> stack = new ArrayList<>();
  static boolean pageHidden;
  static Handler h() { return ViewRoot.mainHandler(); }

  public static Application application() { return app; }
  public static Context baseContext() { return base; }

  public static void main() {
    base = new ContextImpl();
    String appName = Res.application();
    app = appName == null || appName.isEmpty() ? null : (Application) Reflection.newInstance(appName, new Class<?>[0]);
    if (app == null) app = new Application();
    app.attach(base);
    base.app = app;
    installProviders();
    try { app.onCreate(); } catch (Throwable t) { Host.uncaught(t); }
    Intent launch = base.getPackageManager().getLaunchIntentForPackage(base.getPackageName());
    launch.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(null, launch, -1);
  }

  /** the manifest's content providers, created before Application.onCreate as on a device */
  static void installProviders() {
    String joined = Res.componentsJoined(Intents.PROVIDERS);
    if (joined == null || joined.isEmpty()) return;
    String[] kv = Res.split(joined);
    for (int i = 0; i + 1 < kv.length; i += 2) {
      String cls = kv[i], auth = kv[i + 1];
      Object o = Reflection.newInstance(cls, new Class<?>[0]);
      if (!(o instanceof ContentProvider)) { Log.w(TAG, "Unable to get provider " + cls); continue; }
      android.content.pm.ProviderInfo info = new android.content.pm.ProviderInfo();
      info.name = cls; info.packageName = Res.pkg(); info.authority = auth;
      info.applicationInfo = android.content.pm.ApplicationInfo.app();
      ContentProvider p = (ContentProvider) o;
      ContentResolver.installProvider(p);
      try { p.attachInfo(app, info); } catch (Throwable t) { Host.uncaught(t); }
    }
  }

  /** the activity's options menu changed: the page's menu button follows whether the top activity has one */
  public static void menuChanged(Activity a) {
    if (a != top() || !a.mResumed) return;
    h().post(() -> { if (a == top() && a.mResumed) Host.menuButton(a.hasOptionsMenu()); });
  }

  public static boolean isRoot(Activity a) { return !stack.isEmpty() && stack.get(0) == a; }
  /** the activity in front (Instrumentation's key events go there) */
  public static Activity front() { return top(); }
  static Activity top() { return stack.isEmpty() ? null : stack.get(stack.size() - 1); }
  public static android.content.ComponentName topComponent() { Activity a = top(); return a == null ? null : a.getComponentName(); }
  public static int stackSize() { return stack.size(); }
  static String debugStack() {
    StringBuilder b = new StringBuilder();
    for (Activity a : stack) b.append(a.getClass().getSimpleName()).append(a.isFinishing() ? "(finishing)" : "").append(a.mResumed ? "(resumed)" : "").append(a.mStopped ? "(stopped)" : "").append(' ');
    return b.append(" pending=").append(pendingLaunches).toString();
  }
  /** launches posted but not yet performed (a finishing activity then does not resume the one below) */
  static int pendingLaunches;

  // ---------------------------------------------------------------- starting
  public static void startActivity(Activity from, Intent intent, int requestCode) {
    ComponentName c = intent.getComponent();
    String cls = Intents.resolveOne(Intents.ACTIVITIES, intent);
    if (cls == null) {
      if (c != null) throw new ActivityNotFoundException("Unable to find explicit activity class " + c.toShortString() + "; have you declared this activity in your AndroidManifest.xml?");
      String action = intent.getAction();
      android.net.Uri data = intent.getData();
      // what other apps on a device would handle: the browser for web links, mail and the like
      if (data != null && (Intent.ACTION_VIEW.equals(action) || Intent.ACTION_SENDTO.equals(action) || "android.intent.action.DIAL".equals(action))) {
        Host.log(4, "I/" + TAG + ": open " + data); openUrl(data.toString()); return;
      }
      if (Intent.ACTION_MAIN.equals(action) && intent.hasCategory(Intent.CATEGORY_HOME)) { return; }
      if (Intent.ACTION_CHOOSER.equals(action)) {
        Intent t = intent.getParcelableExtra(Intent.EXTRA_INTENT);
        if (t != null && Intents.resolveOne(Intents.ACTIVITIES, t) != null) { startActivity(from, t, requestCode); return; }
        if (t != null) intent = t; action = intent.getAction();
      }
      if (Intent.ACTION_SEND.equals(action)) {
        CharSequence text = intent.getCharSequenceExtra(Intent.EXTRA_TEXT);
        String subject = intent.getStringExtra(Intent.EXTRA_SUBJECT);
        Host.share(text != null ? text.toString() : subject != null ? subject : "", subject);
        return;
      }
      throw new ActivityNotFoundException("No Activity found to handle " + intent);
    }
    final Intent in = c != null && c.getClassName().equals(cls) ? new Intent(intent) : Intents.explicit(intent, cls);
    pendingLaunches++;
    h().post(() -> { pendingLaunches--; launch(from, in, requestCode); });
  }
  static void openUrl(String url) {
    if (url.startsWith("market://details?")) url = "https://play.google.com/store/apps/details?" + url.substring(17);
    Host.openUrl(url);
  }

  static void launch(Activity from, Intent intent, int requestCode) {
    String cls = intent.getComponent().getClassName();
    int flags = intent.getFlags();
    ArrayList<Activity> toFinish = new ArrayList<>();
    if ((flags & Intent.FLAG_ACTIVITY_CLEAR_TASK) != 0 && (flags & Intent.FLAG_ACTIVITY_NEW_TASK) != 0) toFinish.addAll(stack);
    else if ((flags & Intent.FLAG_ACTIVITY_CLEAR_TOP) != 0) {
      int idx = -1;
      for (int i = stack.size() - 1; i >= 0; i--) if (stack.get(i).getClass().getName().equals(cls) && !stack.get(i).isFinishing()) { idx = i; break; }
      if (idx >= 0) {
        Activity existing = stack.get(idx);
        for (int i = stack.size() - 1; i > idx; i--) toFinish.add(stack.get(i));
        if ((flags & Intent.FLAG_ACTIVITY_SINGLE_TOP) != 0) {
          Activity t = top();
          if (t != null && t != existing && t.mResumed) performPause(t);
          for (Activity a : toFinish) { a.mFinished = true; }
          if (existing.mStopped) performRestart(existing);
          deliverNewIntent(existing, intent);
          for (Activity a : toFinish) destroy(a);
          resume(existing);
          return;
        }
        toFinish.add(existing);
      }
    } else if ((flags & Intent.FLAG_ACTIVITY_SINGLE_TOP) != 0) {
      Activity t = top();
      if (t != null && t.getClass().getName().equals(cls) && !t.isFinishing()) {
        if (t.mResumed) performPause(t);
        deliverNewIntent(t, intent);
        resume(t);
        return;
      }
    }
    Activity a = (Activity) Reflection.newInstance(cls, new Class<?>[0]);
    if (a == null) { Log.e(TAG, "Unable to find explicit activity class " + cls); return; }
    Activity prev = top();
    if (prev != null && prev.mResumed) performPause(prev);
    for (Activity f : toFinish) f.mFinished = true;
    String theme = Res.activityTheme(cls);
    int themeId = theme == null || theme.isEmpty() ? Values.refId(Res.appTheme()) : Values.refId(theme);
    a.attachWeb(base, app, intent, themeId);
    if (requestCode >= 0 && from != null) { a.mCaller = from; a.mRequestCode = requestCode; }
    stack.add(a);
    Log.i(TAG, "START " + intent);
    try {
      a.mCalled = false;
      android.app.ActivityHooks.create(a, null);
      if (!a.isFinishing()) {
        a.mStarted = true; android.app.ActivityHooks.call(a, "onStart");
        android.app.ActivityHooks.call(a, "onPostCreate");
      }
    } catch (Throwable t) { Host.uncaught(t); }
    if (!a.isFinishing()) resume(a);
    // what is now hidden behind the new activity stops; cleared activities go away
    for (Activity f : toFinish) destroy(f);
    if (prev != null && !prev.isFinishing() && stack.contains(prev) && !a.isFinishing() && !a.mWindow.isFloating()) performStop(prev);
    if (a.isFinishing()) completeFinish(a);
  }
  /** Activity.recreate: the old instance saves its state and is destroyed; a new one of the class takes its place with the state */
  public static void recreate(Activity old) {
    h().post(() -> {
      if (!stack.contains(old) || old.isFinishing() || old.mDestroyed) return;
      boolean wasResumed = old.mResumed, top = top() == old;
      if (old.mResumed) performPause(old);
      Bundle state = null;
      Object nonConfig = null;
      try { state = android.app.ActivityHooks.saveState(old); nonConfig = android.app.ActivityHooks.retainNonConfiguration(old); } catch (Throwable t) { Host.uncaught(t); }
      // platform fragments are not saved: the new instance starts from scratch rather than without its fragments
      if (android.app.ActivityHooks.hasFragments(old)) state = null;
      int index = stack.indexOf(old);
      old.mChangingConfigurations = true;
      destroy(old);
      String cls = old.getClass().getName();
      Activity a = (Activity) Reflection.newInstance(cls, new Class<?>[0]);
      if (a == null) { Log.e(TAG, "Unable to instantiate activity " + cls); return; }
      String theme = Res.activityTheme(cls);
      int themeId = theme == null || theme.isEmpty() ? Values.refId(Res.appTheme()) : Values.refId(theme);
      a.attachWeb(base, app, old.getIntent(), themeId);
      a.mCaller = old.mCaller; a.mRequestCode = old.mRequestCode;
      a.mLastNonConfigurationInstance = nonConfig;
      for (Activity o : stack) if (o.mCaller == old) o.mCaller = a;
      stack.add(Math.min(index, stack.size()), a);
      Log.i(TAG, "RECREATE " + cls);
      try {
        android.app.ActivityHooks.create(a, state);
        if (!a.isFinishing()) {
          a.mStarted = true; android.app.ActivityHooks.call(a, "onStart");
          if (state != null) android.app.ActivityHooks.restoreState(a, state);
          android.app.ActivityHooks.postCreate(a, state);
        }
      } catch (Throwable t) { Host.uncaught(t); }
      a.mLastNonConfigurationInstance = null;
      if (!a.isFinishing() && top && wasResumed) resume(a);
      else if (!a.isFinishing() && !top) performStop(a);
      if (a.isFinishing()) completeFinish(a);
    });
  }
  /** an activity hosted inside another (LocalActivityManager): created and started, its window never shown on its own */
  public static Activity embed(Activity parent, Intent intent) {
    String cls = Intents.resolveOne(Intents.ACTIVITIES, intent);
    if (cls == null) { Log.e(TAG, "No Activity found to handle " + intent); return null; }
    Activity a = (Activity) Reflection.newInstance(cls, new Class<?>[0]);
    if (a == null) { Log.e(TAG, "Unable to instantiate activity " + cls); return null; }
    String theme = Res.activityTheme(cls);
    int themeId = theme == null || theme.isEmpty() ? Values.refId(Res.appTheme()) : Values.refId(theme);
    a.attachWeb(base, app, Intents.explicit(intent, cls), themeId);
    a.mParent = parent;
    Log.i(TAG, "START (embedded) " + intent);
    try {
      android.app.ActivityHooks.create(a, null);
      if (!a.isFinishing()) { a.mStarted = true; android.app.ActivityHooks.call(a, "onStart"); android.app.ActivityHooks.call(a, "onPostCreate"); }
    } catch (Throwable t) { Host.uncaught(t); }
    return a;
  }
  static void deliverNewIntent(Activity a, Intent intent) {
    try { android.app.ActivityHooks.newIntent(a, intent); } catch (Throwable t) { Host.uncaught(t); }
  }

  // ---------------------------------------------------------------- lifecycle steps
  static void resume(Activity a) {
    if (a.mResumed || a.mDestroyed || pageHidden) return;
    deliverResult(a);
    try {
      android.app.ActivityHooks.call(a, "onResume");
      a.mResumed = true;
      android.app.ActivityHooks.call(a, "onPostResume");
    } catch (Throwable t) { Host.uncaught(t); }
    if (a.isFinishing()) return;
    a.mWindow.show(null);
    if (!a.mVisible) a.mWindow.setVisible(false);
    menuChanged(a);
  }
  static void performPause(Activity a) {
    if (!a.mResumed) return;
    a.mResumed = false;
    try { android.app.ActivityHooks.call(a, "onPause"); } catch (Throwable t) { Host.uncaught(t); }
    TextInput.hide();
  }
  static void performStop(Activity a) {
    if (a.mStopped) return;
    if (a.mResumed) performPause(a);
    a.mStopped = true;
    a.mWindow.hide();
    try { android.app.ActivityHooks.call(a, "onStop"); } catch (Throwable t) { Host.uncaught(t); }
  }
  static void performRestart(Activity a) {
    if (!a.mStopped) return;
    a.mStopped = false;
    try { android.app.ActivityHooks.call(a, "onRestart"); a.mStarted = true; android.app.ActivityHooks.call(a, "onStart"); } catch (Throwable t) { Host.uncaught(t); }
  }
  static void destroy(Activity a) {
    if (a.mDestroyed) return;
    a.mFinished = true;
    if (a.mResumed) performPause(a);
    // (an activity that finished in onCreate was never started: it goes straight to onDestroy, as on Android)
    if (!a.mStopped && a.mStarted) { a.mStopped = true; try { android.app.ActivityHooks.call(a, "onStop"); } catch (Throwable t) { Host.uncaught(t); } }
    a.mDestroyed = true;
    try { android.app.ActivityHooks.call(a, "onDestroy"); } catch (Throwable t) { Host.uncaught(t); }
    a.mWindow.remove();
    android.app.ActivityHooks.dismissDialogs(a);
    stack.remove(a);
  }
  static void deliverResult(Activity a) {
    Object[] r = pendingResults.remove(a);
    if (r != null) {
      try { android.app.ActivityHooks.result(a, (Integer) r[0], (Integer) r[1], (Intent) r[2]); } catch (Throwable t) { Host.uncaught(t); }
    }
  }
  static final HashMap<Activity, Object[]> pendingResults = new HashMap<>();

  // ---------------------------------------------------------------- finishing
  public static void finish(Activity a) {
    if (a.mFinished && !stack.contains(a)) return;
    boolean already = a.mFinished;
    a.mFinished = true;
    if (!already) h().post(() -> completeFinish(a));
  }
  public static void finishActivity(Activity from, int requestCode) {
    for (Activity a : new ArrayList<>(stack)) if (a.mCaller == from && a.mRequestCode == requestCode) finish(a);
  }
  public static void finishAll() { for (Activity a : new ArrayList<>(stack)) finish(a); }
  /** Activity.navigateUpTo: finishes the activities above the target's (or the caller alone when the target is not in the stack) and gives it the intent */
  public static boolean navigateUpTo(Activity from, ComponentName target, Intent upIntent) {
    int idx = -1;
    for (int i = stack.indexOf(from) - 1; i >= 0; i--) if (target.getClassName().equals(stack.get(i).getComponentName().getClassName())) { idx = i; break; }
    if (idx < 0) { finish(from); return false; }
    Activity dest = stack.get(idx);
    for (Activity a : new ArrayList<>(stack.subList(idx + 1, stack.size()))) finish(a);
    h().post(() -> deliverNewIntent(dest, upIntent));
    return true;
  }
  static void completeFinish(Activity a) {
    if (!stack.contains(a)) return;
    boolean wasTop = top() == a;
    if (a.mCaller != null && a.mRequestCode >= 0 && stack.contains(a.mCaller)) pendingResults.put(a.mCaller, new Object[] {a.mRequestCode, a.mResultCode, a.mResultData});
    if (wasTop) {
      performPause(a);
      int i = stack.indexOf(a);
      Activity next = null;
      for (int k = i - 1; k >= 0; k--) if (!stack.get(k).isFinishing()) { next = stack.get(k); break; }
      if (next != null && pendingLaunches == 0) {
        if (next.mStopped) performRestart(next);
        resume(next);
      }
      destroy(a);
      // finish anything else that was marked meanwhile
      for (Activity o : new ArrayList<>(stack)) if (o.isFinishing() && o != next) destroy(o);
    } else destroy(a);
    if (stack.isEmpty() && pendingLaunches == 0) {
      Log.i(TAG, "all activities finished");
      Host.exitApp();
    }
  }

  // ---------------------------------------------------------------- page visibility (like Home / back to the app)
  static void onPageVisibility(boolean visible) {
    Activity t = top();
    if (!visible) {
      pageHidden = true;
      if (t != null) { performPause(t); performStop(t); }
      Audio.pageHidden(true);
    } else {
      pageHidden = false;
      Audio.pageHidden(false);
      if (t != null) { performRestart(t); resume(t); }
    }
  }

  // ---------------------------------------------------------------- broadcasts & services
  static void broadcast(Context c, Intent intent) { Broadcasts.send(c, intent); }

  // services: created on first start/bind, destroyed when stopped and unbound (ActiveServices)
  static final class Running {
    final String cls; final android.app.Service service;
    boolean started; int lastStartId, jobs;
    android.os.IBinder binder; boolean bound;
    final ArrayList<ServiceConnection> conns = new ArrayList<>();
    Running(String cls, android.app.Service s) { this.cls = cls; service = s; }
  }
  static final HashMap<String, Running> services = new HashMap<>();
  static final HashMap<ServiceConnection, Running> connections = new HashMap<>();

  /** the binder of a running, bound service the intent names (BroadcastReceiver.peekService) */
  public static android.os.IBinder peekService(Intent intent) {
    String cls = Intents.resolveOne(Intents.SERVICES, intent);
    Running r = cls == null ? null : services.get(cls);
    return r != null && r.bound ? r.binder : null;
  }
  static Running service(String cls) {
    Running r = services.get(cls);
    if (r != null) return r;
    Object o = Reflection.newInstance(cls, new Class<?>[0]);
    if (!(o instanceof android.app.Service)) { Log.w(TAG, "Unable to instantiate service " + cls); return null; }
    android.app.Service s = (android.app.Service) o;
    s.attachWeb(base);
    r = new Running(cls, s);
    services.put(cls, r);
    Log.i(TAG, "Start service " + cls);
    try { s.onCreate(); } catch (Throwable t) { Host.uncaught(t); }
    return r;
  }
  /** a JobService for a job that starts (created if needed); releaseJobService when it ends */
  public static android.app.Service jobService(String cls) {
    Running r = service(cls);
    if (r == null) return null;
    r.jobs++;
    return r.service;
  }
  public static void releaseJobService(String cls) {
    Running r = services.get(cls);
    if (r == null) return;
    r.jobs = Math.max(0, r.jobs - 1);
    if (r.jobs == 0) maybeDestroy(r);
  }
  static ComponentName startService(Context c, Intent s) {
    String cls = Intents.resolveOne(Intents.SERVICES, s);
    if (cls == null) { Log.w(TAG, "Unable to start service " + s + ": not found"); return null; }
    Intent in = Intents.explicit(s, cls);
    h().post(() -> {
      Running r = service(cls);
      if (r == null) return;
      r.started = true;
      int id = ++r.lastStartId;
      try { r.service.onStartCommand(in, 0, id); } catch (Throwable t) { Host.uncaught(t); }
    });
    return in.getComponent();
  }
  static boolean stopService(Intent s) {
    String cls = Intents.resolveOne(Intents.SERVICES, s);
    Running r = cls == null ? null : services.get(cls);
    if (r == null) return false;
    r.started = false;
    maybeDestroy(r);
    return true;
  }
  /** Service.stopSelf(startId): only when no newer start arrived (startId < 0: unconditionally) */
  public static boolean stopSelf(android.app.Service s, int startId) {
    for (Running r : services.values()) {
      if (r.service != s) continue;
      if (startId >= 0 && startId != r.lastStartId) return false;
      r.started = false;
      h().post(() -> maybeDestroy(r));
      return true;
    }
    return false;
  }
  static boolean bindService(Context c, Intent s, ServiceConnection conn) {
    String cls = Intents.resolveOne(Intents.SERVICES, s);
    if (cls == null) { Log.w(TAG, "Unable to bind service " + s + ": not found"); return false; }
    Intent in = Intents.explicit(s, cls);
    h().post(() -> {
      Running r = service(cls);
      if (r == null) return;
      if (!r.bound) {
        try { r.binder = r.service.onBind(in); } catch (Throwable t) { Host.uncaught(t); }
        r.bound = true;
      }
      if (!r.conns.contains(conn)) r.conns.add(conn);
      connections.put(conn, r);
      try {
        if (r.binder != null) conn.onServiceConnected(in.getComponent(), r.binder);
        else conn.onNullBinding(in.getComponent());   // (Android calls only this when onBind returns null)
      } catch (Throwable t) { Host.uncaught(t); }
    });
    return true;
  }
  static void unbindService(ServiceConnection conn) {
    Running r = connections.remove(conn);
    if (r == null) return;
    r.conns.remove(conn);
    if (r.conns.isEmpty() && r.bound) {
      r.bound = false;
      try { r.service.onUnbind(new Intent().setComponent(new ComponentName(Res.pkg(), r.cls))); } catch (Throwable t) { Host.uncaught(t); }
      maybeDestroy(r);
    }
  }
  static void maybeDestroy(Running r) {
    if (r.started || r.jobs > 0 || !r.conns.isEmpty() || services.get(r.cls) != r) return;
    services.remove(r.cls);
    Log.i(TAG, "Stop service " + r.cls);
    try { r.service.onDestroy(); } catch (Throwable t) { Host.uncaught(t); }
  }
}
