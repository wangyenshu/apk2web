package a2w;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.view.*;

/** One window: owns the root view, runs measure/layout and draws it (Android's ViewRootImpl). */
public final class ViewRoot implements ViewParent {
  private static Handler main;
  public static Handler mainHandler() { if (main == null) main = new Handler(Looper.getMainLooper()); return main; }

  public final View view;
  public final WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
  public final View.AttachInfo attachInfo;
  public final android.os.IBinder token = new android.os.Binder();
  public final KeyEvent.DispatcherState keyDispatchState = new KeyEvent.DispatcherState();
  public View focused;
  /** the activity window this one belongs to (itself for activity windows) */
  public ViewRoot group;
  boolean layoutRequested = true, firstLayout = true;
  int x, y, w, h;
  boolean visible = true;
  boolean hasFocus;
  int lastW = -1, lastH = -1;
  boolean added;

  ViewRoot(View v, WindowManager.LayoutParams p) {
    view = v;
    lp.copyFrom(p);
    attachInfo = new View.AttachInfo(this, mainHandler());
    attachInfo.rootView = v;
  }

  public int windowLeft() { return x; }
  public int windowTop() { return y; }
  public boolean isFullscreenOpaque() {
    return lp.width == ViewGroup.LayoutParams.MATCH_PARENT && lp.height == ViewGroup.LayoutParams.MATCH_PARENT
        && lp.format == android.graphics.PixelFormat.OPAQUE && lp.alpha >= 1;
  }

  void attach() {
    added = true;
    android.view.ViewHooks.setParent(view, this);
    android.view.ViewHooks.attach(view, attachInfo);
    requestLayout();
  }
  void detach() {
    added = false;
    android.view.ViewHooks.detach(view);
    android.view.ViewHooks.setParent(view, null);
  }

  /** a hidden window, or one under an opaque fullscreen window, doesn't need the screen redrawn */
  public void invalidate() { if (visible && !Windows.covered(this)) Windows.scheduleFrame(true); }

  // ---------------------------------------------------------------- traversal
  void traverse(int screenW, int screenH) {
    int vis = visible ? View.VISIBLE : View.GONE;
    if (attachInfo.windowVisibility != vis) {
      attachInfo.windowVisibility = vis;
      view.dispatchWindowVisibilityChanged(vis);
    }
    if (!visible) return;
    int maxW = screenW, maxH = screenH;
    boolean sizeChanged = false;
    if (layoutRequested || view.isLayoutRequested()) {
      if (firstLayout) { try { view.dispatchApplyWindowInsets(android.view.WindowInsets.zero()); } catch (RuntimeException e) { Host.uncaught(e); } }
      int ws = spec(lp.width, maxW), hs = spec(lp.height, maxH);
      view.measure(ws, hs);
      int mw = view.getMeasuredWidth(), mh = view.getMeasuredHeight();
      w = lp.width >= 0 ? lp.width : lp.width == ViewGroup.LayoutParams.MATCH_PARENT ? maxW : Math.min(mw, maxW);
      h = lp.height >= 0 ? lp.height : lp.height == ViewGroup.LayoutParams.MATCH_PARENT ? maxH : Math.min(mh, maxH);
      position(screenW, screenH);
      view.layout(0, 0, w, h);
      layoutRequested = false;
      sizeChanged = w != lastW || h != lastH;
      lastW = w; lastH = h;
      attachInfo.treeObserver.dispatchOnGlobalLayout();
      if (firstLayout) initialFocus();
      firstLayout = false;
      invalidate();
    }
  }
  /** a new window's first focus: out of touch mode (or always, for apps targeting API < 28) its default focus view */
  private void initialFocus() {
    try {
      int target = 10000;
      try { target = view.getContext().getApplicationInfo().targetSdkVersion; } catch (RuntimeException e) { /* keep */ }
      if (target < 28 || !touchMode) { if (!view.hasFocus()) view.restoreDefaultFocus(); }
      else {
        View f = view.findFocus();
        if (f instanceof ViewGroup && ((ViewGroup) f).getDescendantFocusability() == ViewGroup.FOCUS_AFTER_DESCENDANTS) f.restoreDefaultFocus();
      }
    } catch (RuntimeException e) { Host.uncaught(e); }
  }
  private static int spec(int dim, int max) {
    if (dim >= 0) return View.MeasureSpec.makeMeasureSpec(dim, View.MeasureSpec.EXACTLY);
    if (dim == ViewGroup.LayoutParams.MATCH_PARENT) return View.MeasureSpec.makeMeasureSpec(max, View.MeasureSpec.EXACTLY);
    return View.MeasureSpec.makeMeasureSpec(max, View.MeasureSpec.AT_MOST);
  }
  private void position(int sw, int sh) {
    int g = lp.gravity;
    if (g == 0) g = lp.type >= WindowManager.LayoutParams.FIRST_SUB_WINDOW || lp.width != ViewGroup.LayoutParams.MATCH_PARENT || lp.height != ViewGroup.LayoutParams.MATCH_PARENT ? Gravity.CENTER : Gravity.TOP | Gravity.LEFT;
    Rect out = new Rect();
    Gravity.apply(g, w, h, new Rect(0, 0, sw, sh), (int) (lp.x + lp.horizontalMargin * sw), (int) (lp.y + lp.verticalMargin * sh), out);
    x = out.left; y = out.top;
  }

  /** returns true when the view tree wants another frame (running animations) */
  boolean draw(Canvas c, long time) {
    if (!visible) return false;
    if (attachInfo.treeObserver.dispatchOnPreDraw()) { invalidate(); return false; }
    attachInfo.drawingTime = time;
    int save = c.save();
    if ((lp.flags & WindowManager.LayoutParams.FLAG_DIM_BEHIND) != 0) {
      c.drawColor(((int) (Math.max(0, Math.min(1, lp.dimAmount)) * 255) << 24));
    }
    c.translate(x, y);
    if (lp.alpha < 1) c.multiplyAlpha(lp.alpha);
    c.clipRect(0, 0, w, h);
    view.computeScroll();
    if (view.getScrollX() != 0 || view.getScrollY() != 0) c.translate(-view.getScrollX(), -view.getScrollY());
    view.draw(c);
    c.restoreToCount(save);
    attachInfo.treeObserver.dispatchOnDraw();
    return false;
  }

  boolean contains(float px, float py) { return px >= x && py >= y && px < x + w && py < y + h; }

  // ---------------------------------------------------------------- touch mode and keys (ViewRootImpl's input stages)
  /** touch mode, global as on a device: on at start, entered by a pointer down or wheel, left by a navigation or typing key */
  public static boolean touchMode = true;
  /** switches touch mode in every window; true when that moved focus in the key window */
  public static boolean ensureTouchMode(boolean inTouchMode) {
    if (touchMode == inTouchMode) return false;
    touchMode = inTouchMode;
    boolean result = false;
    for (ViewRoot r : new java.util.ArrayList<>(Windows.roots)) {
      boolean changed;
      try {
        android.view.ViewHooks.touchModeChanged(r.attachInfo, inTouchMode);
        changed = inTouchMode ? r.enterTouchMode() : r.leaveTouchMode();
      } catch (RuntimeException e) { Host.uncaught(e); changed = false; }
      if (r == Windows.focusRoot) result = changed;
      r.invalidate();
    }
    return result;
  }
  /** a focused view not focusable in touch mode gives focus to an ancestor that wants it, or drops it */
  boolean enterTouchMode() {
    if (!view.hasFocus()) return false;
    View focused = view.findFocus();
    if (focused == null || focused.isFocusableInTouchMode()) return false;
    for (ViewParent p = focused.getParent(); p instanceof ViewGroup; p = p.getParent()) {
      ViewGroup g = (ViewGroup) p;
      if (g.getDescendantFocusability() == ViewGroup.FOCUS_AFTER_DESCENDANTS && g.isFocusableInTouchMode()) return g.requestFocus();
    }
    focused.clearFocus();
    return true;
  }
  /** focus goes to the window's default view unless a view (or a group preferring itself) has it */
  boolean leaveTouchMode() {
    if (view.hasFocus()) {
      View f = view.findFocus();
      if (!(f instanceof ViewGroup)) return false;
      if (((ViewGroup) f).getDescendantFocusability() != ViewGroup.FOCUS_AFTER_DESCENDANTS) return false;
    }
    return view.restoreDefaultFocus();
  }
  static boolean isNavigationKey(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.KEYCODE_DPAD_LEFT: case KeyEvent.KEYCODE_DPAD_RIGHT: case KeyEvent.KEYCODE_DPAD_UP: case KeyEvent.KEYCODE_DPAD_DOWN:
      case KeyEvent.KEYCODE_DPAD_CENTER: case KeyEvent.KEYCODE_PAGE_UP: case KeyEvent.KEYCODE_PAGE_DOWN: case KeyEvent.KEYCODE_MOVE_HOME:
      case KeyEvent.KEYCODE_MOVE_END: case KeyEvent.KEYCODE_TAB: case KeyEvent.KEYCODE_SPACE: case KeyEvent.KEYCODE_ENTER:
        return true;
    }
    return false;
  }
  /** a navigation key out of touch mode is consumed when leaving moved focus (its purpose was to show focus); a typing key leaves and goes on */
  boolean checkForLeavingTouchModeAndConsume(KeyEvent e) {
    if (!touchMode) return false;
    if (e.getAction() != KeyEvent.ACTION_DOWN && e.getAction() != KeyEvent.ACTION_MULTIPLE) return false;
    if ((e.getFlags() & KeyEvent.FLAG_KEEP_TOUCH_MODE) != 0) return false;
    if (e.hasNoModifiers() && isNavigationKey(e)) return ensureTouchMode(false);
    if (e.getUnicodeChar() > 0) { ensureTouchMode(false); return false; }
    return false;
  }
  /** a key for this window: leave touch mode, the view tree, shortcuts, then focus navigation (arrows, Tab, Meta+Tab) */
  public boolean processKey(KeyEvent e) {
    if (checkForLeavingTouchModeAndConsume(e)) return true;
    if (view.dispatchKeyEvent(e)) return true;
    if (e.getAction() != KeyEvent.ACTION_DOWN) return false;
    int group = 0;
    if (e.getKeyCode() == KeyEvent.KEYCODE_TAB) {
      if (KeyEvent.metaStateHasModifiers(e.getMetaState(), KeyEvent.META_META_ON)) group = View.FOCUS_FORWARD;
      else if (KeyEvent.metaStateHasModifiers(e.getMetaState(), KeyEvent.META_META_ON | KeyEvent.META_SHIFT_ON)) group = View.FOCUS_BACKWARD;
    }
    if (!KeyEvent.metaStateHasNoModifiers(e.getMetaState()) && e.getRepeatCount() == 0 && !KeyEvent.isModifierKey(e.getKeyCode()) && group == 0) {
      if (view.dispatchKeyShortcutEvent(e)) return true;
    }
    return group != 0 ? performKeyboardGroupNavigation(group) : performFocusNavigation(e);
  }
  boolean performFocusNavigation(KeyEvent e) {
    int direction = 0;
    switch (e.getKeyCode()) {
      case KeyEvent.KEYCODE_DPAD_LEFT: if (e.hasNoModifiers()) direction = View.FOCUS_LEFT; break;
      case KeyEvent.KEYCODE_DPAD_RIGHT: if (e.hasNoModifiers()) direction = View.FOCUS_RIGHT; break;
      case KeyEvent.KEYCODE_DPAD_UP: if (e.hasNoModifiers()) direction = View.FOCUS_UP; break;
      case KeyEvent.KEYCODE_DPAD_DOWN: if (e.hasNoModifiers()) direction = View.FOCUS_DOWN; break;
      case KeyEvent.KEYCODE_TAB:
        if (e.hasNoModifiers()) direction = View.FOCUS_FORWARD;
        else if (e.hasModifiers(KeyEvent.META_SHIFT_ON)) direction = View.FOCUS_BACKWARD;
        break;
    }
    if (direction == 0) return false;
    View focused = view.findFocus();
    if (focused == null) return view.restoreDefaultFocus();
    View v = focused.focusSearch(direction);
    if (v != null && v != focused) {
      Rect r = new Rect();
      focused.getFocusedRect(r);
      if (view instanceof ViewGroup) {
        ((ViewGroup) view).offsetDescendantRectToMyCoords(focused, r);
        ((ViewGroup) view).offsetRectIntoDescendantCoords(v, r);
      }
      if (v.requestFocus(direction, r)) {
        view.playSoundEffect(SoundEffectConstants.getContantForFocusDirection(direction));
        return true;
      }
    }
    return view.dispatchUnhandledMove(focused, direction);
  }
  /** Meta+Tab: focus into the next keyboard navigation cluster */
  boolean performKeyboardGroupNavigation(int direction) {
    View focused = view.findFocus();
    View cluster = null;
    for (View v = focused; v != null; v = v.getParent() instanceof View ? (View) v.getParent() : null) if (v.isKeyboardNavigationCluster()) { cluster = v; break; }
    View next = FocusFinder.getInstance().findNextKeyboardNavigationCluster(view, cluster, direction);
    if (next == null || next == cluster) return false;
    return next.requestFocus(View.FOCUS_DOWN);
  }

  void setWindowFocus(boolean f) {
    if (hasFocus == f) return;
    hasFocus = f;
    attachInfo.hasWindowFocus = f;
    view.dispatchWindowFocusChanged(f);
    attachInfo.treeObserver.dispatchOnWindowFocusChange(f);
  }

  // ---------------------------------------------------------------- ViewParent
  public void requestLayout() { layoutRequested = true; Windows.scheduleFrame(true); }
  public boolean isLayoutRequested() { return layoutRequested; }
  public void requestTransparentRegion(View child) {}
  public void invalidateChild(View child, Rect r) { invalidate(); }
  public ViewParent invalidateChildInParent(int[] location, Rect r) { invalidate(); return null; }
  public ViewParent getParent() { return null; }
  public void requestChildFocus(View child, View f) { focused = f; }
  public void recomputeViewAttributes(View child) {}
  public void clearChildFocus(View child) { focused = null; }
  public boolean getChildVisibleRect(View child, Rect r, Point offset) { return r.intersect(0, 0, w, h); }
  public View focusSearch(View v, int direction) { return view instanceof ViewGroup ? FocusFinder.getInstance().findNextFocus((ViewGroup) view, v, direction) : null; }
  public void bringChildToFront(View child) {}
  public void focusableViewAvailable(View v) {}
  public boolean showContextMenuForChild(View originalView) { return false; }
  public void childDrawableStateChanged(View child) {}
  public void requestDisallowInterceptTouchEvent(boolean disallow) {}
  public boolean requestChildRectangleOnScreen(View child, Rect rectangle, boolean immediate) { return false; }
}
