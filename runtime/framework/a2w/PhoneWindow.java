package a2w;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.*;
import android.widget.FrameLayout;

/** Window implementation: a DecorView holding the content, themed from the activity/dialog style. */
public class PhoneWindow extends Window {
  public final class DecorView extends FrameLayout {
    DecorView(Context c) { super(c); }
    @Override public boolean dispatchKeyEvent(KeyEvent e) {
      // Back ends a primary action mode first
      if (e.getKeyCode() == KeyEvent.KEYCODE_BACK && mPrimaryActionMode != null) {
        if (e.getAction() == KeyEvent.ACTION_UP) mPrimaryActionMode.finish();
        return true;
      }
      Callback cb = getCallback();
      return cb != null ? cb.dispatchKeyEvent(e) : super.dispatchKeyEvent(e);
    }
    @Override public ActionMode startActionMode(ActionMode.Callback callback) { return startActionModeForChild(this, callback, ActionMode.TYPE_PRIMARY); }
    @Override public ActionMode startActionMode(ActionMode.Callback callback, int type) { return startActionModeForChild(this, callback, type); }
    @Override public boolean dispatchTouchEvent(MotionEvent e) {
      Callback cb = getCallback();
      return cb != null ? cb.dispatchTouchEvent(e) : super.dispatchTouchEvent(e);
    }
    @Override public boolean dispatchGenericMotionEvent(MotionEvent e) {
      Callback cb = getCallback();
      return cb != null ? cb.dispatchGenericMotionEvent(e) : super.dispatchGenericMotionEvent(e);
    }
    boolean superDispatchGenericMotionEvent(MotionEvent e) { return super.dispatchGenericMotionEvent(e); }
    /** the window callback's own mode (onWindowStartingActionMode), else the window's: a contextual bar or a floating toolbar */
    @Override public ActionMode startActionModeForChild(View originalView, ActionMode.Callback callback, int type) {
      if (callback == null) return null;
      Callback cb = getCallback();
      if (type == ActionMode.TYPE_PRIMARY && mPrimaryActionMode != null) mPrimaryActionMode.finish();
      if (type == ActionMode.TYPE_FLOATING && mFloatingActionMode != null) mFloatingActionMode.finish();
      ActionMode mode = null;
      if (cb != null) mode = type == ActionMode.TYPE_PRIMARY ? cb.onWindowStartingActionMode(callback) : cb.onWindowStartingActionMode(callback, type);
      if (mode == null) {
        ActionModeImpl m = new ActionModeImpl(PhoneWindow.this, originalView, callback, type);
        if (type == ActionMode.TYPE_PRIMARY) mPrimaryActionMode = m; else mFloatingActionMode = m;
        if (!m.start()) { if (type == ActionMode.TYPE_PRIMARY) mPrimaryActionMode = null; else mFloatingActionMode = null; return null; }
        mode = m;
      } else if (type == ActionMode.TYPE_PRIMARY) mPrimaryActionMode = mode; else mFloatingActionMode = mode;
      if (cb != null) cb.onActionModeStarted(mode);
      return mode;
    }
    boolean superDispatchKeyEvent(KeyEvent e) { return super.dispatchKeyEvent(e); }
    boolean superDispatchTouchEvent(MotionEvent e) { return super.dispatchTouchEvent(e); }
    @Override public void onWindowFocusChanged(boolean f) {
      super.onWindowFocusChanged(f);
      Callback cb = getCallback();
      if (cb != null) cb.onWindowFocusChanged(f);
    }
    @Override protected void onAttachedToWindow() { super.onAttachedToWindow(); Callback cb = getCallback(); if (cb != null) cb.onAttachedToWindow(); }
    @Override protected void onDetachedFromWindow() { super.onDetachedFromWindow(); Callback cb = getCallback(); if (cb != null) cb.onDetachedFromWindow(); }
    public PhoneWindow window() { return PhoneWindow.this; }
    /** the window's default focus is in its content before the action bar (whose container blocks touchscreen focus on a device) */
    @Override public boolean restoreDefaultFocus() {
      if (mContentParent != null && mContentParent.getVisibility() == VISIBLE && mContentParent.restoreDefaultFocus()) return true;
      return super.restoreDefaultFocus();
    }
  }

  DecorView mDecor;
  FrameLayout mContentParent;
  ActionBarImpl mActionBar;
  /** the platform action bar (themes with windowActionBar), made with the decor */
  public ActionBarImpl actionBar() {
    if (mDecor == null) themeFeatures();   // the theme as it is now (setTheme in onCreate before getActionBar)
    if (hasFeature(FEATURE_ACTION_BAR)) installDecor();
    return mActionBar;
  }
  // (an embedded activity's getActionBar is its parent's, as on a device)
  public ActionBarImpl peekActionBar() { return mActionBar; }
  LayoutInflater mLayoutInflater;
  boolean mIsFloating, mIsTranslucent;
  Drawable mBackground;
  ViewRoot mRoot;
  boolean mVisible = true;

  public PhoneWindow(Context c) {
    super(c);
    mLayoutInflater = LayoutInflater.from(c);
  }
  /** reads windowBackground / windowIsFloating / backgroundDimEnabled from the context's theme */
  public void applyTheme() {
    android.content.res.Resources.Theme t = getContext().getTheme();
    String floating = t.get("a", "windowIsFloating"), translucent = t.get("a", "windowIsTranslucent"), dim = t.get("a", "backgroundDimEnabled");
    String dimAmount = t.get("a", "backgroundDimAmount");
    mIsFloating = "true".equals(floating);
    themeFeatures();   // the platform action bar, the window background
    mIsTranslucent = "true".equals(translucent);
    WindowManager.LayoutParams a = getAttributes();
    if (mIsFloating) { a.width = ViewGroup.LayoutParams.WRAP_CONTENT; a.height = ViewGroup.LayoutParams.WRAP_CONTENT; }
    if ("true".equals(dim)) { a.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND; a.dimAmount = Values.parseFloat(dimAmount, 0.6f); }
    a.format = mIsFloating || mIsTranslucent ? PixelFormat.TRANSLUCENT : PixelFormat.OPAQUE;
  }
  /** the window features the theme asks for (the platform action bar), unless the app requested its own */
  void themeFeatures() {
    android.content.res.Resources.Theme t = getContext().getTheme();
    boolean wantBar = (mRequestedFeatures & (1 << FEATURE_ACTION_BAR)) != 0
        || (mRequestedFeatures & (1 << FEATURE_NO_TITLE)) == 0 && "true".equals(t.get("a", "windowActionBar")) && !"true".equals(t.get("a", "windowNoTitle"));
    if (wantBar && !mIsFloating) {
      mFeatures &= ~(1 << FEATURE_NO_TITLE);
      mFeatures |= 1 << FEATURE_ACTION_BAR;
    } else {
      mFeatures &= ~(1 << FEATURE_ACTION_BAR);
      mFeatures |= 1 << FEATURE_NO_TITLE;
    }
    if (!mBackgroundSet) {
      String bg = t.get("a", "windowBackground");
      if (bg != null && bg.startsWith("#")) mBackground = new ColorDrawable(Values.parseColor(bg));
      else if (bg != null && Values.isRef(bg)) { int id = Values.refId(bg); try { mBackground = id == 0 ? null : getContext().getResources().getDrawable(id); } catch (RuntimeException e) { mBackground = null; } }
    }
  }
  boolean mBackgroundSet;
  @Override public boolean isFloating() { return mIsFloating; }
  void installDecor() {
    if (mDecor == null) {
      themeFeatures();   // (the theme as it is now: an activity may set its theme in onCreate)
      mDecor = new DecorView(getContext());
      mDecor.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
      if (mBackground != null) mDecor.setBackgroundDrawable(mBackground);
      mContentParent = new FrameLayout(getContext());
      mContentParent.setId(android.R.id.content);
      // (an activity embedded in another, as in a TabActivity, shows no action bar of its own)
      if (hasFeature(FEATURE_ACTION_BAR) && getContext() instanceof android.app.Activity && ((android.app.Activity) getContext()).getParent() == null && !mIsFloating) {
        // the action bar above the content (or over it, with FEATURE_ACTION_BAR_OVERLAY)
        mActionBar = new ActionBarImpl((android.app.Activity) getContext());
        boolean overlay = hasFeature(FEATURE_ACTION_BAR_OVERLAY);
        android.widget.LinearLayout col = new android.widget.LinearLayout(getContext());
        col.setOrientation(android.widget.LinearLayout.VERTICAL);
        if (overlay) {
          mDecor.addView(mContentParent, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
          mDecor.addView(mActionBar.view(), new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        } else {
          col.addView(mActionBar.view(), new android.widget.LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
          col.addView(mContentParent, new android.widget.LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
          mDecor.addView(col, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
      } else
      mDecor.addView(mContentParent, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
      if (mIsFloating) {
        mContentParent.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
        mContentParent.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
      }
    }
  }
  @Override public void setContentView(int layoutResID) {
    installDecor();
    mContentParent.removeAllViews();
    mLayoutInflater.inflate(layoutResID, mContentParent);
    Callback cb = getCallback();
    if (cb != null) cb.onContentChanged();
  }
  @Override public void setContentView(View v) { setContentView(v, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)); }
  @Override public void setContentView(View v, ViewGroup.LayoutParams p) {
    installDecor();
    mContentParent.removeAllViews();
    mContentParent.addView(v, p);
    Callback cb = getCallback();
    if (cb != null) cb.onContentChanged();
  }
  @Override public void addContentView(View v, ViewGroup.LayoutParams p) {
    installDecor();
    mContentParent.addView(v, p);
    Callback cb = getCallback();
    if (cb != null) cb.onContentChanged();
  }
  @Override public View getCurrentFocus() { return mDecor != null ? mDecor.findFocus() : null; }
  @Override public LayoutInflater getLayoutInflater() { return mLayoutInflater; }
  @Override public View getDecorView() { installDecor(); return mDecor; }
  @Override public View peekDecorView() { return mDecor; }
  @Override public View findViewById(int id) { return getDecorView().findViewById(id); }
  @Override public void setBackgroundDrawable(Drawable d) { mBackground = d; mBackgroundSet = true; if (mDecor != null) mDecor.setBackgroundDrawable(d); }
  @Override public boolean superDispatchKeyEvent(KeyEvent e) { return mDecor != null && mDecor.superDispatchKeyEvent(e); }
  @Override public boolean superDispatchTouchEvent(MotionEvent e) { return mDecor != null && mDecor.superDispatchTouchEvent(e); }
  @Override public boolean superDispatchGenericMotionEvent(MotionEvent e) { return mDecor != null && mDecor.superDispatchGenericMotionEvent(e); }

  /** adds the decor to the window manager (activities: on first resume; dialogs: on show) */
  public void show(ViewRoot group) {
    installDecor();
    if (mRoot == null) mRoot = Windows.add(mDecor, getAttributes(), group);
    setVisible(true);
  }
  public void hide() { setVisible(false); }
  public void remove() { if (mRoot != null) { if (mDecor.getParent() != null) Windows.remove(mDecor); mRoot = null; } }
  public ViewRoot root() { return mRoot; }
  // ---------------------------------------------------------------- action modes
  ActionMode mPrimaryActionMode, mFloatingActionMode;
  /** the contextual bar, over the top of the window */
  void showActionModeBar(View bar, int height) {
    installDecor();
    mDecor.addView(bar, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height, android.view.Gravity.TOP));
  }
  void hideActionModeBar(View bar) { if (bar.getParent() == mDecor) mDecor.removeView(bar); }
  void actionModeFinished(ActionMode mode) {
    if (mode == mPrimaryActionMode) mPrimaryActionMode = null;
    if (mode == mFloatingActionMode) mFloatingActionMode = null;
    Callback cb = getCallback();
    if (cb != null) cb.onActionModeFinished(mode);
  }
  public void setVisible(boolean v) { mVisible = v; if (mDecor != null && mRoot != null) Windows.setVisible(mDecor, v); }
  public void attributesChanged() { if (mRoot != null) Windows.update(mDecor, getAttributes()); }
  @Override protected void dispatchAttributesChanged() { super.dispatchAttributesChanged(); if (getCallback() == null) attributesChanged(); }
}
