package android.app;

import android.content.*;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.*;
import a2w.ActivityThread;
import a2w.PhoneWindow;

public class Activity extends ContextThemeWrapper implements LayoutInflater.Factory2, Window.Callback, KeyEvent.Callback, View.OnCreateContextMenuListener, ComponentCallbacks2 {
  public static final int RESULT_CANCELED = 0, RESULT_OK = -1, RESULT_FIRST_USER = 1;
  public static final int DEFAULT_KEYS_DISABLE = 0, DEFAULT_KEYS_DIALER = 1, DEFAULT_KEYS_SHORTCUT = 2, DEFAULT_KEYS_SEARCH_LOCAL = 3, DEFAULT_KEYS_SEARCH_GLOBAL = 4;
  protected static final int[] FOCUSED_STATE_SET = {android.R.attr.state_focused};

  // ---- managed by a2w.ActivityThread
  public Application mApplication;
  public Intent mIntent;
  public ComponentName mComponent;
  public PhoneWindow mWindow;
  public boolean mCalled, mFinished, mResumed, mStopped, mDestroyed, mStarted, mVisible = true;
  public int mResultCode = RESULT_CANCELED;
  public Intent mResultData;
  public Activity mParent;
  public int mRequestCode = -1;
  public Activity mCaller;
  final Handler mHandler = new Handler();
  CharSequence mTitle;
  int mTitleColor;
  private Thread mUiThread;

  public Activity() { super(null, 0); mUiThread = Thread.currentThread(); }

  /** web: attach to the application, as ActivityThread.performLaunchActivity does */
  public final void attachWeb(Context base, Application app, Intent intent, int theme) {
    attachBaseContext(base);
    mApplication = app;
    mIntent = intent;
    mComponent = intent.getComponent();
    // the title: the activity's label, else the application's (as ActivityThread sets it)
    try {
      android.content.pm.PackageManager pm = getPackageManager();
      android.content.pm.ActivityInfo ai = pm.getActivityInfo(mComponent, 0);
      mTitle = ai != null && (ai.nonLocalizedLabel != null || ai.labelRes != 0) ? ai.loadLabel(pm) : getApplicationInfo().loadLabel(pm);
    } catch (Exception e) { mTitle = null; }
    setTheme(theme);
    mWindow = new PhoneWindow(this);
    mWindow.setCallback(this);
    mWindow.getLayoutInflater().setPrivateFactory(this);
    mWindow.applyTheme();
  }

  public Intent getIntent() { return mIntent; }
  public void setIntent(Intent i) { mIntent = i; }
  public final Application getApplication() { return mApplication; }
  public final boolean isChild() { return mParent != null; }
  public final Activity getParent() { return mParent; }
  public WindowManager getWindowManager() { return a2w.Windows.manager(); }
  public Window getWindow() { return mWindow; }
  public View getCurrentFocus() { return mWindow != null ? mWindow.getCurrentFocus() : null; }
  public ComponentName getComponentName() { return mComponent; }
  public ComponentName getCallingActivity() { return mCaller != null ? mCaller.mComponent : null; }
  public String getCallingPackage() { return mCaller != null ? getPackageName() : null; }
  public String getLocalClassName() { String c = mComponent.getClassName(), p = getPackageName(); return c.startsWith(p + ".") ? c.substring(p.length() + 1) : c; }
  public int getTaskId() { return 1; }
  public boolean isTaskRoot() { return ActivityThread.isRoot(this); }
  public boolean moveTaskToBack(boolean nonRoot) { return false; }
  public final boolean isResumed() { return mResumed; }
  public boolean isFinishing() { return mFinished; }
  public boolean isDestroyed() { return mDestroyed; }
  /** true while the activity is being destroyed to be made again (recreate) */
  public boolean isChangingConfigurations() { return mChangingConfigurations; }
  public boolean mChangingConfigurations;
  public Object mLastNonConfigurationInstance;
  public final void runOnUiThread(Runnable r) { if (Thread.currentThread() != mUiThread) mHandler.post(r); else r.run(); }
  public final void setResult(int resultCode) { mResultCode = resultCode; mResultData = null; }
  public final void setResult(int resultCode, Intent data) { mResultCode = resultCode; mResultData = data; }
  @Override public Object getSystemService(String name) {
    if (WINDOW_SERVICE.equals(name)) return getWindowManager();
    return super.getSystemService(name);
  }

  // ---------------------------------------------------------------- lifecycle (overridable)
  protected void onCreate(Bundle b) { mCalled = true; mFragments.dispatchCreate(); dispatchMain("onCreate", b); }
  protected void onPostCreate(Bundle b) { mCalled = true; }
  protected void onStart() { mCalled = true; dispatchMain("onStart", null); }
  protected void onRestart() { mCalled = true; }
  protected void onResume() { mCalled = true; dispatchMain("onResume", null); }
  protected void onPostResume() { mCalled = true; }
  protected void onPause() { mCalled = true; dispatchMain("onPause", null); }
  protected void onStop() { mCalled = true; dispatchMain("onStop", null); }
  protected void onDestroy() { mCalled = true; dispatchMain("onDestroy", null); }
  protected void onNewIntent(Intent i) {}
  /** the window's view states (and focus), as Android's default */
  protected void onSaveInstanceState(Bundle outState) {
    if (mWindow != null) outState.putBundle("android:viewHierarchyState", mWindow.saveHierarchyState());
    dispatchMain("onSaveInstanceState", outState);
  }
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    if (mWindow != null && savedInstanceState != null) {
      Bundle windowState = savedInstanceState.getBundle("android:viewHierarchyState");
      if (windowState != null) mWindow.restoreHierarchyState(windowState);
    }
  }
  protected void onUserLeaveHint() {}
  public void onUserInteraction() {}
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {}
  public void onConfigurationChanged(Configuration c) { mCalled = true; }
  public void onLowMemory() { mCalled = true; }
  public void onTrimMemory(int level) { mCalled = true; }
  public Object onRetainNonConfigurationInstance() { return null; }
  public Object getLastNonConfigurationInstance() { return mLastNonConfigurationInstance; }
  protected void onTitleChanged(CharSequence t, int color) {
    if (mWindow != null) { a2w.ActionBarImpl ab = mWindow.peekActionBar(); if (ab != null) ab.titleChanged(t); }
    if (mToolbarActionBar != null) mToolbarActionBar.setWindowTitle(t);
  }
  /** the platform action bar, when the theme has one (null otherwise, as on a device) */
  public ActionBar getActionBar() {
    if (mParent != null) return mParent.getActionBar();
    if (mToolbarActionBar != null) return mToolbarActionBar;
    return mWindow != null ? mWindow.actionBar() : null;
  }
  private a2w.ToolbarActionBar mToolbarActionBar;
  /** a Toolbar as this activity's action bar (the window must not have one of its own) */
  public void setActionBar(android.widget.Toolbar toolbar) {
    if (toolbar != null && mWindow != null && mWindow.peekActionBar() != null)
      throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_ACTION_BAR and set android:windowActionBar to false in your theme to use a Toolbar instead.");
    if (mToolbarActionBar != null) mToolbarActionBar.detach();
    mMenu = null;
    mToolbarActionBar = toolbar == null ? null : new a2w.ToolbarActionBar(this, toolbar);
  }
  /** web: the options menu for the action bar's items (null when the activity shows none) */
  public a2w.MenuBuilder optionsMenuForBar() {
    a2w.MenuBuilder m = optionsMenu();
    if (!mMenuShown) return null;
    try { onPreparePanel(Window.FEATURE_OPTIONS_PANEL, null, m); } catch (RuntimeException e) { a2w.Host.uncaught(e); }
    return m;
  }
  protected Dialog onCreateDialog(int id) { return null; }
  protected Dialog onCreateDialog(int id, Bundle args) { return onCreateDialog(id); }
  protected void onPrepareDialog(int id, Dialog d) {}
  protected void onPrepareDialog(int id, Dialog d, Bundle args) { onPrepareDialog(id, d); }
  private final android.util.SparseArray<Dialog> mManagedDialogs = new android.util.SparseArray<>();
  public final void showDialog(int id) { showDialog(id, null); }
  public final boolean showDialog(int id, Bundle args) {
    Dialog d = mManagedDialogs.get(id);
    if (d == null) { d = onCreateDialog(id, args); if (d == null) return false; mManagedDialogs.put(id, d); }
    onPrepareDialog(id, d, args);
    d.show();
    return true;
  }
  public final void dismissDialog(int id) { Dialog d = mManagedDialogs.get(id); if (d == null) throw new IllegalArgumentException("no dialog with id " + id + " was ever shown via Activity#showDialog"); d.dismiss(); }
  public final void removeDialog(int id) { Dialog d = mManagedDialogs.get(id); if (d != null) { d.dismiss(); mManagedDialogs.remove(id); } }

  // ---------------------------------------------------------------- content
  public void setContentView(int layoutResID) { mWindow.setContentView(layoutResID); }
  public void setContentView(View v) { mWindow.setContentView(v); }
  public void setContentView(View v, ViewGroup.LayoutParams p) { mWindow.setContentView(v, p); }
  public void addContentView(View v, ViewGroup.LayoutParams p) { mWindow.addContentView(v, p); }
  public View findViewById(int id) { return mWindow.findViewById(id); }
  @SuppressWarnings("unchecked")
  public final <T extends View> T requireViewById(int id) {
    View v = findViewById(id);
    if (v == null) throw new IllegalArgumentException("ID does not reference a View inside this Activity");
    return (T) v;
  }
  // ---- runtime permissions (API 23): the page grants what the manifest asks for
  public final void requestPermissions(String[] permissions, int requestCode) {
    if (permissions == null) permissions = new String[0];
    final String[] p = permissions;
    final int[] results = new int[p.length];
    for (int i = 0; i < p.length; i++) results[i] = checkSelfPermission(p[i]);
    new android.os.Handler(android.os.Looper.getMainLooper()).post(() -> onRequestPermissionsResult(requestCode, p, results));
  }
  public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {}
  public boolean shouldShowRequestPermissionRationale(String permission) { return false; }
  public void finishAndRemoveTask() { finish(); }
  public void finishAfterTransition() { finish(); }
  public void postponeEnterTransition() {}
  public void startPostponedEnterTransition() {}
  public void reportFullyDrawn() {}
  public boolean isInMultiWindowMode() { return false; }
  public boolean isInPictureInPictureMode() { return false; }
  public android.net.Uri getReferrer() { return null; }
  boolean mImmersive;
  public boolean isImmersive() { return mImmersive; }
  public void setImmersive(boolean i) { mImmersive = i; }
  public boolean isActivityTransitionRunning() { return false; }
  public LayoutInflater getLayoutInflater() { return mWindow.getLayoutInflater(); }
  public MenuInflater getMenuInflater() { return new MenuInflater(this); }
  public final boolean requestWindowFeature(int f) { return mWindow.requestFeature(f); }
  int mTitleProgress, mTitleSecondaryProgress; boolean mTitleProgressVisible, mTitleIndeterminate, mTitleIndeterminateVisible;
  public final void setProgressBarVisibility(boolean v) { mTitleProgressVisible = v; }
  public final void setProgressBarIndeterminateVisibility(boolean v) { mTitleIndeterminateVisible = v; }
  public final void setProgressBarIndeterminate(boolean i) { mTitleIndeterminate = i; }
  public final void setProgress(int p) { mTitleProgress = Math.max(Window.PROGRESS_START, Math.min(p, Window.PROGRESS_END)); }
  public final void setSecondaryProgress(int secondaryProgress) { mTitleSecondaryProgress = Math.max(Window.PROGRESS_START, Math.min(secondaryProgress, Window.PROGRESS_END)); }
  public void setTitle(CharSequence t) { mTitle = t; onTitleChanged(t, mTitleColor); }
  public void setTitle(int id) { setTitle(getText(id)); }
  public void setTitleColor(int c) { mTitleColor = c; onTitleChanged(mTitle, c); }
  public final int getTitleColor() { return mTitleColor; }
  public final CharSequence getTitle() { return mTitle; }
  public final void setDefaultKeyMode(int m) {}
  public void setRequestedOrientation(int o) {}
  public int getRequestedOrientation() { return 0; }
  public final void setVolumeControlStream(int s) { getWindow().setVolumeControlStream(s); }
  public final int getVolumeControlStream() { return getWindow().getVolumeControlStream(); }
  public void setVisible(boolean v) { mVisible = v; if (mWindow != null) mWindow.setVisible(v); }
  public void takeKeyEvents(boolean t) {}
  // ---------------------------------------------------------------- menus
  a2w.MenuBuilder mMenu;
  /** the options menu, created once (onCreateOptionsMenu) and prepared before each showing */
  a2w.MenuBuilder optionsMenu() {
    if (mMenu == null) {
      a2w.MenuBuilder m = new a2w.MenuBuilder(this);
      m.setCallback((menu, item) -> onMenuItemSelected(Window.FEATURE_OPTIONS_PANEL, item));
      boolean show;
      try { show = onCreatePanelMenu(Window.FEATURE_OPTIONS_PANEL, m); } catch (RuntimeException e) { a2w.Host.uncaught(e); show = false; }
      mMenu = m;
      mMenuShown = show;
    }
    return mMenu;
  }
  boolean mMenuShown;
  /** web: whether the page should offer the menu button (the legacy overflow of the navigation bar) */
  public boolean hasOptionsMenu() {
    // (an action bar shows the menu itself)
    a2w.ActionBarImpl ab = mWindow != null ? mWindow.peekActionBar() : null;
    if (ab != null && ab.isShowing()) return false;
    a2w.MenuBuilder m = optionsMenu();
    return mMenuShown && m.hasVisibleItems();
  }
  public void openOptionsMenu() {
    a2w.MenuBuilder m = optionsMenu();
    if (!mMenuShown) return;
    if (!onPreparePanel(Window.FEATURE_OPTIONS_PANEL, null, m) || !m.hasVisibleItems()) return;
    onMenuOpened(Window.FEATURE_OPTIONS_PANEL, m);
    a2w.MenuPopup.show(m, () -> onPanelClosed(Window.FEATURE_OPTIONS_PANEL, m));
  }
  public void closeOptionsMenu() { a2w.MenuPopup.closeAll(); }
  public void invalidateOptionsMenu() {
    mMenu = null;
    a2w.ActionBarImpl ab = mWindow != null ? mWindow.peekActionBar() : null;
    if (ab != null) ab.menuChanged();
    if (mToolbarActionBar != null) mToolbarActionBar.menuChanged();
    ActivityThread.menuChanged(this);
  }
  @Deprecated public void supportInvalidateOptionsMenu() { invalidateOptionsMenu(); }
  public boolean onCreateOptionsMenu(Menu m) { return true; }
  public boolean onPrepareOptionsMenu(Menu m) { return true; }
  public boolean onOptionsItemSelected(MenuItem i) {
    if (i.getItemId() == android.R.id.home && mParent == null) return onNavigateUp();
    return false;
  }
  public boolean onNavigateUp() { return false; }
  public boolean onNavigateUpFromChild(Activity child) { return onNavigateUp(); }
  public void onOptionsMenuClosed(Menu m) {}
  public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo info) {}
  public boolean onContextItemSelected(MenuItem i) { return false; }
  public void onContextMenuClosed(Menu m) {}
  public void registerForContextMenu(View v) { v.setOnCreateContextMenuListener(this); }
  public void unregisterForContextMenu(View v) { v.setOnCreateContextMenuListener(null); }
  public void openContextMenu(View v) { v.showContextMenu(); }
  public void closeContextMenu() { a2w.MenuPopup.closeAll(); }
  /** the search dialog for this activity's searchable configuration (startSearch); false where the UI mode has none (TV) */
  public boolean onSearchRequested() {
    int mode = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_TYPE_MASK;
    if (mode == android.content.res.Configuration.UI_MODE_TYPE_TELEVISION || mode == android.content.res.Configuration.UI_MODE_TYPE_WATCH) return false;
    startSearch(null, false, null, false);
    return true;
  }
  // managed cursors: deactivated when stopped, requeried when restarted, closed with the activity
  final java.util.ArrayList<android.database.Cursor> mManagedCursors = new java.util.ArrayList<>();
  @Deprecated public void startManagingCursor(android.database.Cursor c) { synchronized (mManagedCursors) { if (c != null && !mManagedCursors.contains(c)) mManagedCursors.add(c); } }
  @Deprecated public void stopManagingCursor(android.database.Cursor c) { synchronized (mManagedCursors) { mManagedCursors.remove(c); } }
  @Deprecated public final android.database.Cursor managedQuery(android.net.Uri u, String[] p, String s, String[] a, String o) {
    android.database.Cursor c = getContentResolver().query(u, p, s, a, o);
    if (c != null) startManagingCursor(c);
    return c;
  }
  @Deprecated public final android.database.Cursor managedQuery(android.net.Uri u, String[] p, String s, String o) { return managedQuery(u, p, s, null, o); }
  void managedCursors(int what) {
    for (android.database.Cursor c : new java.util.ArrayList<>(mManagedCursors)) {
      try {
        if (what == 0) c.deactivate(); else if (what == 1) { if (!c.isClosed()) c.requery(); } else c.close();
      } catch (RuntimeException e) { android.util.Log.w("Activity", "managed cursor: " + e); }
    }
    if (what == 2) mManagedCursors.clear();
  }

  // ---------------------------------------------------------------- fragments, loaders
  final FragmentManagerImpl mFragments = new FragmentManagerImpl(this, null);
  LoaderManager.Impl mLoaderManager;
  final java.util.HashMap<Integer, String> mFragmentRequests = new java.util.HashMap<>();
  public FragmentManager getFragmentManager() { return mFragments; }
  public LoaderManager getLoaderManager() { if (mLoaderManager == null) mLoaderManager = new LoaderManager.Impl(); return mLoaderManager; }
  public void onAttachFragment(Fragment fragment) {}
  public void startActivityFromFragment(Fragment fragment, Intent intent, int requestCode) { startActivityFromFragment(fragment, intent, requestCode, null); }
  public void startActivityFromFragment(Fragment fragment, Intent intent, int requestCode, Bundle options) {
    if (requestCode >= 0) mFragmentRequests.put(requestCode, fragment.mWho);
    startActivityForResult(intent, requestCode);
  }
  /** a result goes to the fragment that asked for it, else to the activity */
  void dispatchActivityResult(int requestCode, int resultCode, Intent data) {
    String who = mFragmentRequests.remove(requestCode);
    Fragment f = who == null ? null : mFragments.findFragmentByWho(who);
    if (f != null) f.onActivityResult(requestCode, resultCode, data);
    else onActivityResult(requestCode, resultCode, data);
  }
  public SharedPreferences getPreferences(int mode) { return getSharedPreferences(getLocalClassName(), mode); }
  public void overridePendingTransition(int enter, int exit) {}
  /** destroys this activity and makes it again (its saved state and onRetainNonConfigurationInstance kept), after the current event */
  public void recreate() { if (mParent == null) a2w.ActivityThread.recreate(this); }

  // ---------------------------------------------------------------- activities
  @Override public void startActivity(Intent intent) { startActivityForResult(intent, -1); }
  @Override public void startActivity(Intent intent, Bundle options) { startActivity(intent); }
  public void startActivityForResult(Intent intent, int requestCode) { ActivityThread.startActivity(this, intent, requestCode); }
  public void startActivityForResult(Intent intent, int requestCode, Bundle options) { startActivityForResult(intent, requestCode); }
  public boolean startActivityIfNeeded(Intent intent, int requestCode) { startActivityForResult(intent, requestCode); return true; }
  public void startActivityFromChild(Activity child, Intent intent, int requestCode) { startActivityForResult(intent, requestCode); }
  public void finish() { if (mParent != null) mParent.finishFromChild(this); else ActivityThread.finish(this); }
  public void finishActivity(int requestCode) { ActivityThread.finishActivity(this, requestCode); }
  public void finishFromChild(Activity child) { finish(); }
  public void finishAffinity() { ActivityThread.finishAll(); }
  public void finishActivityFromChild(Activity child, int requestCode) {}
  public PendingIntent createPendingResult(int requestCode, Intent data, int flags) { return PendingIntent.getActivity(this, requestCode, data, flags); }
  protected void onChildTitleChanged(Activity childActivity, CharSequence title) {}

  // ---------------------------------------------------------------- input
  public boolean dispatchKeyEvent(KeyEvent event) {
    onUserInteraction();
    if (mWindow.superDispatchKeyEvent(event)) return true;
    View decor = mWindow.peekDecorView();
    return event.dispatch(this, decor != null ? decor.getKeyDispatcherState() : null, this);
  }
  public boolean dispatchKeyShortcutEvent(KeyEvent e) { return false; }
  public boolean dispatchTouchEvent(MotionEvent ev) {
    if (ev.getActionMasked() == MotionEvent.ACTION_DOWN) onUserInteraction();
    if (mWindow.superDispatchTouchEvent(ev)) return true;
    return onTouchEvent(ev);
  }
  public boolean dispatchTrackballEvent(MotionEvent ev) { return onTrackballEvent(ev); }
  public boolean dispatchGenericMotionEvent(MotionEvent ev) {
    if (mWindow.superDispatchGenericMotionEvent(ev)) return true;
    return onGenericMotionEvent(ev);
  }
  public boolean onTouchEvent(MotionEvent e) { return false; }
  public boolean onTrackballEvent(MotionEvent e) { return false; }
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK) { event.startTracking(); return true; }
    if (keyCode == KeyEvent.KEYCODE_MENU) return true;
    if (event.isCtrlPressed() || event.isAltPressed()) { a2w.MenuBuilder m = optionsMenu(); onPreparePanel(Window.FEATURE_OPTIONS_PANEL, null, m); if (m.performShortcut(keyCode, event, 0)) return true; }
    return false;
  }
  public boolean onKeyLongPress(int keyCode, KeyEvent event) { return false; }
  public boolean onKeyUp(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK && event.isTracking() && !event.isCanceled()) { if (mBackDispatcher == null || !mBackDispatcher.dispatch()) onBackPressed(); return true; }
    if (keyCode == KeyEvent.KEYCODE_MENU) { if (!a2w.MenuPopup.isOpen()) openOptionsMenu(); else closeOptionsMenu(); return true; }
    return false;
  }
  public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) { return false; }
  public boolean onKeyShortcut(int keyCode, KeyEvent event) { return false; }
  private a2w.BackDispatcher mBackDispatcher;
  public android.window.OnBackInvokedDispatcher getOnBackInvokedDispatcher() { if (mBackDispatcher == null) mBackDispatcher = new a2w.BackDispatcher(); return mBackDispatcher; }
  public void onBackPressed() {
    if (mFragments.popBackStackImmediate()) return;
    if (mParent != null) { mParent.onBackPressed(); return; }
    finish();
  }

  // ---------------------------------------------------------------- Window.Callback
  public View onCreatePanelView(int f) { return null; }
  public boolean onCreatePanelMenu(int f, Menu m) {
    if (f != Window.FEATURE_OPTIONS_PANEL) return false;
    boolean show = onCreateOptionsMenu(m);
    show |= mFragments.dispatchCreateOptionsMenu(m, getMenuInflater());
    return show;
  }
  public boolean onPreparePanel(int f, View v, Menu m) {
    if (f != Window.FEATURE_OPTIONS_PANEL || m == null) return true;
    boolean goforit = onPrepareOptionsMenu(m);
    goforit |= mFragments.dispatchPrepareOptionsMenu(m);
    return goforit;
  }
  public boolean onMenuOpened(int f, Menu m) { return true; }
  public boolean onMenuItemSelected(int f, MenuItem i) {
    if (f == Window.FEATURE_OPTIONS_PANEL) return onOptionsItemSelected(i) || mFragments.dispatchOptionsItemSelected(i);
    if (f == Window.FEATURE_CONTEXT_MENU) return onContextItemSelected(i) || mFragments.dispatchContextItemSelected(i);
    return false;
  }
  public void onPanelClosed(int f, Menu m) {
    if (f == Window.FEATURE_OPTIONS_PANEL) onOptionsMenuClosed(m);
    else if (f == Window.FEATURE_CONTEXT_MENU) onContextMenuClosed(m);
  }
  public void onWindowAttributesChanged(WindowManager.LayoutParams p) { if (mWindow != null) mWindow.attributesChanged(); }
  public void onContentChanged() {}
  public void onWindowFocusChanged(boolean hasFocus) {}
  public void onAttachedToWindow() {}
  public void onDetachedFromWindow() {}
  public boolean hasWindowFocus() { View d = mWindow.peekDecorView(); return d != null && d.hasWindowFocus(); }

  // ---------------------------------------------------------------- LayoutInflater.Factory2
  public View onCreateView(String name, Context c, android.util.AttributeSet a) { return null; }
  public View onCreateView(View parent, String name, Context c, android.util.AttributeSet a) {
    if (!"fragment".equals(name)) return onCreateView(name, c, a);
    return mFragments.onCreateView(parent, name, c, a);
  }

  // ---------------------------------------------------------------- lifecycle callbacks (Application's and the activity's own, API 29)
  final java.util.ArrayList<Application.ActivityLifecycleCallbacks> mLifecycleCallbacks = new java.util.ArrayList<>();
  public void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback) { synchronized (mLifecycleCallbacks) { mLifecycleCallbacks.add(callback); } }
  public void unregisterActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback) { synchronized (mLifecycleCallbacks) { mLifecycleCallbacks.remove(callback); } }
  private java.util.List<Application.ActivityLifecycleCallbacks> lifecycleCallbacks(boolean appFirst) {
    java.util.ArrayList<Application.ActivityLifecycleCallbacks> own;
    synchronized (mLifecycleCallbacks) { own = new java.util.ArrayList<>(mLifecycleCallbacks); }
    java.util.List<Application.ActivityLifecycleCallbacks> app = mApplication == null ? java.util.Collections.emptyList() : java.util.Arrays.asList(mApplication.webLifecycleCallbacks());
    java.util.ArrayList<Application.ActivityLifecycleCallbacks> out = new java.util.ArrayList<>();
    if (appFirst) { out.addAll(app); out.addAll(own); } else { java.util.Collections.reverse(own); out.addAll(own); out.addAll(app); }
    return out;
  }
  private static boolean setUp(String what) { return what.equals("onCreate") || what.equals("onStart") || what.equals("onResume"); }
  /** the onActivityXxx callbacks, from super.onXxx as in Android (set-up steps: Application's first; tear-down: the activity's, newest first) */
  void dispatchMain(String what, Bundle b) {
    for (Application.ActivityLifecycleCallbacks cb : lifecycleCallbacks(setUp(what))) {
      switch (what) {
        case "onCreate": cb.onActivityCreated(this, b); break;
        case "onStart": cb.onActivityStarted(this); break;
        case "onResume": cb.onActivityResumed(this); break;
        case "onPause": cb.onActivityPaused(this); break;
        case "onStop": cb.onActivityStopped(this); break;
        case "onDestroy": cb.onActivityDestroyed(this); break;
        case "onSaveInstanceState": cb.onActivitySaveInstanceState(this, b); break;
      }
    }
  }
  /** the pre and post callbacks around a lifecycle step (ActivityHooks) */
  void dispatchLifecycle(String what, boolean pre, Bundle b) {
    for (Application.ActivityLifecycleCallbacks cb : lifecycleCallbacks(pre)) {
      switch (what) {
        case "onCreate": if (pre) cb.onActivityPreCreated(this, b); else cb.onActivityPostCreated(this, b); break;
        case "onStart": if (pre) cb.onActivityPreStarted(this); else cb.onActivityPostStarted(this); break;
        case "onResume": if (pre) cb.onActivityPreResumed(this); else cb.onActivityPostResumed(this); break;
        case "onPause": if (pre) cb.onActivityPrePaused(this); else cb.onActivityPostPaused(this); break;
        case "onStop": if (pre) cb.onActivityPreStopped(this); else cb.onActivityPostStopped(this); break;
        case "onDestroy": if (pre) cb.onActivityPreDestroyed(this); else cb.onActivityPostDestroyed(this); break;
        case "onSaveInstanceState": if (pre) cb.onActivityPreSaveInstanceState(this, b); else cb.onActivityPostSaveInstanceState(this, b); break;
      }
    }
  }
  /** (the options menu, for Window's panel actions) */
  public Menu webOptionsMenu() { return optionsMenu(); }

  // ---------------------------------------------------------------- persistable and caller variants (forwarding as Android's defaults do)
  public void onCreate(Bundle savedInstanceState, android.os.PersistableBundle persistentState) { onCreate(savedInstanceState); }
  public void onPostCreate(Bundle savedInstanceState, android.os.PersistableBundle persistentState) { onPostCreate(savedInstanceState); }
  public void onRestoreInstanceState(Bundle savedInstanceState, android.os.PersistableBundle persistentState) { if (savedInstanceState != null) onRestoreInstanceState(savedInstanceState); }
  public void onSaveInstanceState(Bundle outState, android.os.PersistableBundle outPersistentState) { onSaveInstanceState(outState); }
  public void onActivityResult(int requestCode, int resultCode, Intent data, ComponentCaller caller) { onActivityResult(requestCode, resultCode, data); }
  public void onNewIntent(Intent intent, ComponentCaller caller) { onNewIntent(intent); }
  public void setIntent(Intent newIntent, ComponentCaller newCaller) { setIntent(newIntent); }
  public ComponentCaller getCaller() { return null; }
  public ComponentCaller getCurrentCaller() { return null; }
  public ComponentCaller getInitialCaller() { return null; }
  public final void requestPermissions(String[] permissions, int requestCode, int deviceId) { requestPermissions(permissions, requestCode); }
  public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults, int deviceId) { onRequestPermissionsResult(requestCode, permissions, grantResults); }
  public boolean shouldShowRequestPermissionRationale(String permission, int deviceId) { return shouldShowRequestPermissionRationale(permission); }
  public void onMultiWindowModeChanged(boolean isInMultiWindowMode, android.content.res.Configuration newConfig) { onMultiWindowModeChanged(isInMultiWindowMode); }
  public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {}
  public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, android.content.res.Configuration newConfig) { onPictureInPictureModeChanged(isInPictureInPictureMode); }
  public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {}
  public boolean onPictureInPictureRequested() { return false; }
  public void onPictureInPictureUiStateChanged(PictureInPictureUiState pipState) {}
  public void onTopResumedActivityChanged(boolean isTopResumedActivity) {}
  public void onActivityReenter(int resultCode, Intent data) {}
  public void onEnterAnimationComplete() {}
  public void onStateNotSaved() {}
  public void onVisibleBehindCanceled() {}
  public boolean onGenericMotionEvent(MotionEvent event) { return false; }
  public CharSequence onCreateDescription() { return null; }
  public boolean onCreateThumbnail(android.graphics.Bitmap outBitmap, android.graphics.Canvas canvas) { return false; }
  public void onProvideAssistData(Bundle data) {}
  public void onProvideAssistContent(android.app.assist.AssistContent outContent) {}
  public android.net.Uri onProvideReferrer() { return null; }
  public void onProvideKeyboardShortcuts(java.util.List<KeyboardShortcutGroup> data, Menu menu, int deviceId) {}
  public void onGetDirectActions(android.os.CancellationSignal cancellationSignal, java.util.function.Consumer<java.util.List<DirectAction>> callback) { callback.accept(java.util.Collections.emptyList()); }
  public void onPerformDirectAction(String actionId, Bundle arguments, android.os.CancellationSignal cancellationSignal, java.util.function.Consumer<Bundle> resultListener) {}
  public void onLocalVoiceInteractionStarted() {}
  public void onLocalVoiceInteractionStopped() {}
  public void onActionModeStarted(ActionMode mode) {}
  public void onActionModeFinished(ActionMode mode) {}
  public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) { return null; }
  public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int type) { return onWindowStartingActionMode(callback); }
  /** no action mode UI: the window does not start one (Android's result when none can be shown) */
  /** an action mode on the window (the contextual bar over the action bar; TYPE_FLOATING: a floating toolbar) */
  public ActionMode startActionMode(ActionMode.Callback callback) { return mWindow.getDecorView().startActionMode(callback); }
  public ActionMode startActionMode(ActionMode.Callback callback, int type) { return mWindow.getDecorView().startActionMode(callback, type); }
  public void onCreateNavigateUpTaskStack(TaskStackBuilder builder) { builder.addParentStack(this); }
  public void onPrepareNavigateUpTaskStack(TaskStackBuilder builder) {}
  public boolean onSearchRequested(SearchEvent searchEvent) { return onSearchRequested(); }
  public final SearchEvent getSearchEvent() { return null; }

  // ---------------------------------------------------------------- up navigation (the manifest's parentActivityName)
  public Intent getParentActivityIntent() {
    String parent = a2w.Res.activityParent(getComponentName().getClassName());
    return parent == null ? null : new Intent().setClassName(this, parent);
  }
  public boolean shouldUpRecreateTask(Intent targetIntent) { return false; }
  /** finishes the activities above the target's in the stack (or this one when it is not there) and delivers the intent to it */
  public boolean navigateUpTo(Intent upIntent) {
    android.content.ComponentName target = upIntent.getComponent();
    if (target == null) { String cls = a2w.Intents.resolveOne(a2w.Intents.ACTIVITIES, upIntent); if (cls == null) return false; target = new android.content.ComponentName(getPackageName(), cls); }
    return a2w.ActivityThread.navigateUpTo(this, target, upIntent);
  }
  public boolean navigateUpToFromChild(Activity child, Intent upIntent) { return navigateUpTo(upIntent); }

  // ---------------------------------------------------------------- starting
  public boolean startActivityIfNeeded(Intent intent, int requestCode, Bundle options) { startActivityForResult(intent, requestCode, options); return true; }
  public boolean startNextMatchingActivity(Intent intent) { return false; }
  public boolean startNextMatchingActivity(Intent intent, Bundle options) { return false; }
  public void startActivityFromChild(Activity child, Intent intent, int requestCode, Bundle options) { startActivityForResult(intent, requestCode, options); }
  public void startIntentSenderForResult(android.content.IntentSender intent, int requestCode, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags) throws android.content.IntentSender.SendIntentException {
    startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, null);
  }
  public void startIntentSenderForResult(android.content.IntentSender intent, int requestCode, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, Bundle options) throws android.content.IntentSender.SendIntentException {
    PendingIntent p = intent.webTarget();
    if (p != null && p.isActivity()) startActivityForResult(p.webIntent(fillInIntent), requestCode, options);
    else intent.sendIntent(this, 0, fillInIntent, null, null);
  }
  public void startIntentSenderFromChild(Activity child, android.content.IntentSender intent, int requestCode, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags) throws android.content.IntentSender.SendIntentException {
    startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags);
  }
  public void startIntentSenderFromChild(Activity child, android.content.IntentSender intent, int requestCode, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, Bundle options) throws android.content.IntentSender.SendIntentException {
    startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, options);
  }
  public String getLaunchedFromPackage() { return null; }
  public int getLaunchedFromUid() { return android.os.Process.INVALID_UID; }
  public int getChangingConfigurations() { return 0; }

  // ---------------------------------------------------------------- window options
  boolean mFinishOnTouchOutside;
  public void setFinishOnTouchOutside(boolean finish) { mFinishOnTouchOutside = finish; }
  public final void setFeatureDrawableResource(int featureId, int resId) { getWindow().setFeatureDrawableResource(featureId, resId); }
  public final void setFeatureDrawableUri(int featureId, android.net.Uri uri) { getWindow().setFeatureDrawableUri(featureId, uri); }
  public final void setFeatureDrawable(int featureId, android.graphics.drawable.Drawable drawable) { getWindow().setFeatureDrawable(featureId, drawable); }
  public final void setFeatureDrawableAlpha(int featureId, int alpha) { getWindow().setFeatureDrawableAlpha(featureId, alpha); }
  boolean mShowWhenLocked, mTurnScreenOn, mInheritShowWhenLocked, mRecentsScreenshot = true, mDockBigOverlays = true, mTranslucent;
  public void setShowWhenLocked(boolean showWhenLocked) { mShowWhenLocked = showWhenLocked; }
  public void setInheritShowWhenLocked(boolean inheritShowWhenLocked) { mInheritShowWhenLocked = inheritShowWhenLocked; }
  public void setTurnScreenOn(boolean turnScreenOn) { mTurnScreenOn = turnScreenOn; }
  public void setRecentsScreenshotEnabled(boolean enabled) { mRecentsScreenshot = enabled; }
  public void setShouldDockBigOverlays(boolean shouldDockBigOverlays) { mDockBigOverlays = shouldDockBigOverlays; }
  public boolean shouldDockBigOverlays() { return mDockBigOverlays; }
  public boolean setTranslucent(boolean translucent) { mTranslucent = translucent; return true; }
  public void setAllowCrossUidActivitySwitchFromBelow(boolean allowed) {}
  public void setVrModeEnabled(boolean enabled, android.content.ComponentName requestedComponent) throws android.content.pm.PackageManager.NameNotFoundException {}
  public void setLocusContext(android.content.LocusId locusId, Bundle bundle) {}
  public void startLockTask() {}
  public void stopLockTask() {}
  public void showLockTaskEscapeMessage() {}
  public boolean releaseInstance() { return false; }
  public boolean requestVisibleBehind(boolean visible) { return false; }
  public boolean isLaunchedFromBubble() { return false; }
  // picture-in-picture and voice: not available on the page
  PictureInPictureParams mPipParams;
  public void enterPictureInPictureMode() {}
  public boolean enterPictureInPictureMode(PictureInPictureParams params) { return false; }
  public void setPictureInPictureParams(PictureInPictureParams params) { mPipParams = params; }
  public int getMaxNumPictureInPictureActions() { return 0; }
  public VoiceInteractor getVoiceInteractor() { return null; }
  public boolean isVoiceInteraction() { return false; }
  public boolean isVoiceInteractionRoot() { return false; }
  public boolean isLocalVoiceInteractionSupported() { return false; }
  public void startLocalVoiceInteraction(Bundle privateOptions) {}
  public void stopLocalVoiceInteraction() {}
  public boolean showAssist(Bundle args) { return false; }
  // search
  public void startSearch(String initialQuery, boolean selectInitialQuery, Bundle appSearchData, boolean globalSearch) {
    SearchManager sm = (SearchManager) getSystemService(SEARCH_SERVICE);
    if (sm != null) sm.startSearch(initialQuery, selectInitialQuery, getComponentName(), appSearchData, globalSearch);
  }
  public void triggerSearch(String query, Bundle appSearchData) {
    SearchManager sm = (SearchManager) getSystemService(SEARCH_SERVICE);
    if (sm != null) sm.triggerSearch(query, getComponentName(), appSearchData);
  }
  // keyboard shortcuts, drag and drop, screen capture
  public final void requestShowKeyboardShortcuts() {}
  public final void dismissKeyboardShortcutsHelper() {}
  public DragAndDropPermissions requestDragAndDropPermissions(android.view.DragEvent event) { return null; }
  final java.util.ArrayList<ScreenCaptureCallback> mScreenCaptureCallbacks = new java.util.ArrayList<>();
  public interface ScreenCaptureCallback { void onScreenCaptured(); }
  public void registerScreenCaptureCallback(java.util.concurrent.Executor executor, ScreenCaptureCallback callback) { mScreenCaptureCallbacks.add(callback); }
  public void unregisterScreenCaptureCallback(ScreenCaptureCallback callback) { mScreenCaptureCallbacks.remove(callback); }
  // transitions (kept; activities change without animation)
  public void overridePendingTransition(int enterAnim, int exitAnim, int backgroundColor) { overridePendingTransition(enterAnim, exitAnim); }
  public void overrideActivityTransition(int overrideType, int enterAnim, int exitAnim) { overrideActivityTransition(overrideType, enterAnim, exitAnim, 0); }
  public void overrideActivityTransition(int overrideType, int enterAnim, int exitAnim, int backgroundColor) {}
  public void clearOverrideActivityTransition(int overrideType) {}
  public void setContentTransitionManager(android.transition.TransitionManager tm) { getWindow().setTransitionManager(tm); }
  public android.transition.TransitionManager getContentTransitionManager() { return getWindow().getTransitionManager(); }
  public android.transition.Scene getContentScene() { return getWindow().getContentScene(); }
  SharedElementCallback mEnterSharedElementCallback, mExitSharedElementCallback;
  public void setEnterSharedElementCallback(SharedElementCallback callback) { mEnterSharedElementCallback = callback; }
  public void setExitSharedElementCallback(SharedElementCallback callback) { mExitSharedElementCallback = callback; }
  // media and splash screens
  public final void setMediaController(android.media.session.MediaController controller) { getWindow().setMediaController(controller); }
  public final android.media.session.MediaController getMediaController() { return getWindow().getMediaController(); }
  public final android.window.SplashScreen getSplashScreen() { return null; }
  public static final int FULLSCREEN_MODE_REQUEST_EXIT = 0, FULLSCREEN_MODE_REQUEST_ENTER = 1;
  /** full screen on the page (the browser's Fullscreen API) */
  public void requestFullscreenMode(int request, android.os.OutcomeReceiver<Void, Throwable> approvalCallback) {
    if (request != FULLSCREEN_MODE_REQUEST_ENTER && request != FULLSCREEN_MODE_REQUEST_EXIT) throw new IllegalArgumentException("request");
    a2w.Host.fullscreen(request == FULLSCREEN_MODE_REQUEST_ENTER, ok -> {
      if (approvalCallback == null) return;
      if (ok != 0) approvalCallback.onResult(null); else approvalCallback.onError(new IllegalStateException("The page could not change full-screen mode"));
    });
  }
  public boolean dispatchPopulateAccessibilityEvent(android.view.accessibility.AccessibilityEvent event) {
    event.setClassName(getClass().getName());
    event.setPackageName(getPackageName());
    CharSequence title = getTitle();
    if (!android.text.TextUtils.isEmpty(title)) event.getText().add(title);
    return true;
  }
  public void dump(String prefix, java.io.FileDescriptor fd, java.io.PrintWriter writer, String[] args) {
    writer.print(prefix); writer.print("Local Activity "); writer.print(Integer.toHexString(System.identityHashCode(this))); writer.println(" State:");
    String inner = prefix + "  ";
    writer.print(inner); writer.print("mResumed="); writer.print(mResumed); writer.print(" mStopped="); writer.print(mStopped); writer.print(" mFinished="); writer.println(isFinishing());
    writer.print(inner); writer.print("mComponent="); writer.println(getComponentName());
    writer.flush();
  }
}
