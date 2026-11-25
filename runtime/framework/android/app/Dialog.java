package android.app;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.*;
import a2w.PhoneWindow;
import a2w.Values;
import java.util.ArrayList;
import java.util.HashMap;

public class Dialog implements DialogInterface, Window.Callback, KeyEvent.Callback, View.OnCreateContextMenuListener {
  private static final HashMap<Activity, ArrayList<Dialog>> byActivity = new HashMap<>();
  final Context mContext;
  Activity mOwnerActivity;
  final PhoneWindow mWindow;
  boolean mCancelable = true, mCreated, mShowing, mCanceledOnTouchOutside;
  OnCancelListener mCancelListener;
  OnDismissListener mDismissListener;
  OnShowListener mShowListener;
  OnKeyListener mOnKeyListener;
  final Handler mHandler = new Handler();

  public Dialog(Context context) { this(context, 0); }
  public Dialog(Context context, int theme) {
    if (theme == 0 && context != null && context.getTheme() != null) {
      // the context theme's dialog theme (?android:attr/dialogTheme), as Dialog does
      String dt = context.getTheme().get("a", "dialogTheme");
      if (dt != null && Values.isRef(dt)) theme = Values.refId(dt);
    }
    if (theme == 0) theme = Values.frameworkId("style/Theme.Dialog");
    mContext = new ContextThemeWrapper(context, theme);
    Context c = context;
    while (c instanceof android.content.ContextWrapper && !(c instanceof Activity)) c = ((android.content.ContextWrapper) c).getBaseContext();
    if (c instanceof Activity) mOwnerActivity = (Activity) c;
    mWindow = new PhoneWindow(mContext);
    mWindow.setCallback(this);
    mWindow.applyTheme();
    WindowManager.LayoutParams a = mWindow.getAttributes();
    a.type = WindowManager.LayoutParams.TYPE_APPLICATION;
    if (mWindow.isFloating()) a.gravity = Gravity.CENTER;
    mCanceledOnTouchOutside = false;
  }
  protected Dialog(Context context, boolean cancelable, OnCancelListener l) { this(context); mCancelable = cancelable; mCancelListener = l; }
  public final Context getContext() { return mContext; }
  public final void setOwnerActivity(Activity a) { mOwnerActivity = a; }
  public final Activity getOwnerActivity() { return mOwnerActivity; }
  public boolean isShowing() { return mShowing; }
  public Window getWindow() { return mWindow; }
  public View getCurrentFocus() { return mWindow.getCurrentFocus(); }
  public LayoutInflater getLayoutInflater() { return mWindow.getLayoutInflater(); }
  public final boolean requestWindowFeature(int f) { return mWindow.requestFeature(f); }
  public final void setFeatureDrawableResource(int f, int r) { mWindow.setFeatureDrawableResource(f, r); }
  public final void setFeatureDrawable(int featureId, android.graphics.drawable.Drawable drawable) { mWindow.setFeatureDrawable(featureId, drawable); }
  public final void setFeatureDrawableUri(int featureId, android.net.Uri uri) { mWindow.setFeatureDrawableUri(featureId, uri); }
  public final void setFeatureDrawableAlpha(int featureId, int alpha) { mWindow.setFeatureDrawableAlpha(featureId, alpha); }
  public final void setVolumeControlStream(int s) { mWindow.setVolumeControlStream(s); }
  public final int getVolumeControlStream() { return mWindow.getVolumeControlStream(); }
  /** dialogs have no action bar unless their window has one */
  public ActionBar getActionBar() { return mWindow.peekActionBar(); }
  public final SearchEvent getSearchEvent() { return null; }
  public final <T extends View> T requireViewById(int id) {
    T v = (T) findViewById(id);
    if (v == null) throw new IllegalArgumentException("ID does not reference a View inside this Dialog");
    return v;
  }
  private a2w.BackDispatcher mBackDispatcher;
  public android.window.OnBackInvokedDispatcher getOnBackInvokedDispatcher() { if (mBackDispatcher == null) mBackDispatcher = new a2w.BackDispatcher(); return mBackDispatcher; }

  // ---------------------------------------------------------------- menus: the dialog's options menu (Menu key or openOptionsMenu) and context menus
  private a2w.MenuBuilder mMenu;
  private boolean mMenuShown;
  private a2w.MenuBuilder optionsMenu() {
    if (mMenu == null) {
      a2w.MenuBuilder m = new a2w.MenuBuilder(mContext);
      m.setCallback((menu, item) -> onMenuItemSelected(Window.FEATURE_OPTIONS_PANEL, item));
      boolean show;
      try { show = onCreatePanelMenu(Window.FEATURE_OPTIONS_PANEL, m); } catch (RuntimeException e) { a2w.Host.uncaught(e); show = false; }
      mMenu = m;
      mMenuShown = show;
    }
    return mMenu;
  }
  public void openOptionsMenu() {
    if (!mShowing) return;
    a2w.MenuBuilder m = optionsMenu();
    if (!mMenuShown || !onPreparePanel(Window.FEATURE_OPTIONS_PANEL, null, m)) return;
    onMenuOpened(Window.FEATURE_OPTIONS_PANEL, m);
    a2w.MenuPopup.show(m, () -> onPanelClosed(Window.FEATURE_OPTIONS_PANEL, m));
  }
  public void closeOptionsMenu() { a2w.MenuPopup.closeAll(); }
  public void invalidateOptionsMenu() { mMenu = null; }
  public void onOptionsMenuClosed(Menu menu) {}
  public void registerForContextMenu(View view) { view.setOnCreateContextMenuListener(this); }
  public void unregisterForContextMenu(View view) { view.setOnCreateContextMenuListener(null); }
  public void openContextMenu(View view) { view.showContextMenu(); }
  public boolean onContextItemSelected(MenuItem item) { return false; }
  public void onContextMenuClosed(Menu menu) {}
  public boolean onKeyShortcut(int keyCode, KeyEvent event) { return false; }
  public void takeKeyEvents(boolean g) {}

  void dispatchOnCreate(Bundle b) { if (!mCreated) { onCreate(b); mCreated = true; } }
  public void create() { dispatchOnCreate(null); }
  protected void onCreate(Bundle b) {}
  protected void onStart() {}
  protected void onStop() {}
  public Bundle onSaveInstanceState() { return new Bundle(); }
  public void onRestoreInstanceState(Bundle b) {}

  public void show() {
    if (mShowing) { mWindow.setVisible(true); return; }
    if (!mCreated) dispatchOnCreate(null);
    onStart();
    a2w.ViewRoot group = null;
    if (mOwnerActivity != null) {
      PhoneWindow w = (PhoneWindow) mOwnerActivity.getWindow();
      if (w != null) { if (w.root() == null) w.show(null); group = w.root(); }
    }
    mWindow.show(group);
    mShowing = true;
    if (mOwnerActivity != null) byActivity.computeIfAbsent(mOwnerActivity, k -> new ArrayList<>()).add(this);
    if (mShowListener != null) { final OnShowListener l = mShowListener; mHandler.post(() -> l.onShow(this)); }
  }
  public void hide() { mWindow.setVisible(false); }
  public void dismiss() {
    if (!mShowing) return;
    mWindow.remove();
    mShowing = false;
    try { onStop(); } catch (Throwable t) { a2w.Host.uncaught(t); }
    if (mOwnerActivity != null) { ArrayList<Dialog> l = byActivity.get(mOwnerActivity); if (l != null) l.remove(this); }
    if (mDismissListener != null) { final OnDismissListener l = mDismissListener; mHandler.post(() -> l.onDismiss(this)); }
  }
  static void dismissAll(Activity a) {
    ArrayList<Dialog> l = byActivity.remove(a);
    if (l != null) for (Dialog d : new ArrayList<>(l)) { if (d.mShowing) { d.mWindow.remove(); d.mShowing = false; } }
  }
  public void cancel() {
    if (mCancelListener != null) { final OnCancelListener l = mCancelListener; mHandler.post(() -> l.onCancel(this)); }
    dismiss();
  }
  public void setCancelable(boolean c) { mCancelable = c; }
  public void setCanceledOnTouchOutside(boolean c) { if (c && !mCancelable) mCancelable = true; mCanceledOnTouchOutside = c; }
  public void setOnCancelListener(OnCancelListener l) { mCancelListener = l; }
  public void setOnDismissListener(OnDismissListener l) { mDismissListener = l; }
  public void setOnShowListener(OnShowListener l) { mShowListener = l; }
  public void setOnKeyListener(OnKeyListener l) { mOnKeyListener = l; }
  public void setCancelMessage(android.os.Message m) {}
  public void setDismissMessage(android.os.Message m) {}
  public void setTitle(CharSequence t) { mWindow.setTitle(t); }
  public void setTitle(int id) { setTitle(mContext.getText(id)); }

  public void setContentView(int id) { mWindow.setContentView(id); }
  public void setContentView(View v) { mWindow.setContentView(v); }
  public void setContentView(View v, ViewGroup.LayoutParams p) { mWindow.setContentView(v, p); }
  public void addContentView(View v, ViewGroup.LayoutParams p) { mWindow.addContentView(v, p); }
  public View findViewById(int id) { return mWindow.findViewById(id); }

  // ---------------------------------------------------------------- input
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK) { event.startTracking(); return true; }
    if (keyCode == KeyEvent.KEYCODE_MENU) { optionsMenu(); if (mMenuShown) { event.startTracking(); return true; } }
    return false;
  }
  public boolean onKeyLongPress(int keyCode, KeyEvent event) { return false; }
  public boolean onKeyUp(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK && event.isTracking() && !event.isCanceled()) { if (mBackDispatcher == null || !mBackDispatcher.dispatch()) onBackPressed(); return true; }
    if (keyCode == KeyEvent.KEYCODE_MENU && event.isTracking() && !event.isCanceled() && mMenuShown) { if (a2w.MenuPopup.isOpen()) closeOptionsMenu(); else openOptionsMenu(); return true; }
    return false;
  }
  public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) { return false; }
  public void onBackPressed() { if (mCancelable) cancel(); }
  public boolean onTouchEvent(MotionEvent event) {
    if (mCancelable && mShowing && mCanceledOnTouchOutside && event.getAction() == MotionEvent.ACTION_DOWN) {
      View d = mWindow.getDecorView();
      float x = event.getX(), y = event.getY();
      if (x < 0 || y < 0 || x > d.getWidth() || y > d.getHeight() || event.getAction() == MotionEvent.ACTION_OUTSIDE) { cancel(); return true; }
    }
    if (event.getActionMasked() == MotionEvent.ACTION_OUTSIDE && mCancelable && mCanceledOnTouchOutside) { cancel(); return true; }
    return false;
  }
  public boolean onTrackballEvent(MotionEvent e) { return false; }
  public boolean dispatchKeyEvent(KeyEvent event) {
    if (mOnKeyListener != null && mOnKeyListener.onKey(this, event.getKeyCode(), event)) return true;
    if (mWindow.superDispatchKeyEvent(event)) return true;
    View decor = mWindow.peekDecorView();
    return event.dispatch(this, decor != null ? decor.getKeyDispatcherState() : null, this);
  }
  public boolean dispatchTouchEvent(MotionEvent ev) {
    if (ev.getActionMasked() != MotionEvent.ACTION_OUTSIDE && mWindow.superDispatchTouchEvent(ev)) return true;
    return onTouchEvent(ev);
  }
  public boolean dispatchTrackballEvent(MotionEvent ev) { return false; }
  public boolean dispatchGenericMotionEvent(MotionEvent ev) {
    if (mWindow.superDispatchGenericMotionEvent(ev)) return true;
    return onGenericMotionEvent(ev);
  }
  public boolean onGenericMotionEvent(MotionEvent event) { return false; }
  public View onCreatePanelView(int f) { return null; }
  public boolean onCreatePanelMenu(int f, Menu m) { return f == Window.FEATURE_OPTIONS_PANEL && onCreateOptionsMenu(m); }
  public boolean onPreparePanel(int f, View v, Menu m) { return f != Window.FEATURE_OPTIONS_PANEL || m == null || (onPrepareOptionsMenu(m) && m.hasVisibleItems()); }
  public boolean onMenuOpened(int f, Menu m) { return false; }
  public boolean onMenuItemSelected(int f, MenuItem i) { return false; }
  public void onPanelClosed(int f, Menu m) {}
  public void onWindowAttributesChanged(WindowManager.LayoutParams p) { mWindow.attributesChanged(); }
  public void onContentChanged() {}
  public void onWindowFocusChanged(boolean f) {}
  public void onAttachedToWindow() {}
  public void onDetachedFromWindow() {}
  public boolean onSearchRequested() { return false; }
  public boolean onCreateOptionsMenu(Menu m) { return true; }
  public boolean onPrepareOptionsMenu(Menu m) { return true; }
  public boolean onOptionsItemSelected(MenuItem i) { return false; }
  public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo info) {}
}
