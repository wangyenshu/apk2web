package a2w;

import android.graphics.Canvas;
import android.os.SystemClock;
import android.view.*;
import java.util.ArrayList;
import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;

/** The window manager: window stacking, the frame loop, and routing of input to windows. */
public final class Windows {
  private Windows() {}
  public static final int W = Screen.W, H = Screen.H;
  static final ArrayList<ViewRoot> roots = new ArrayList<>();
  static Canvas screen;
  static boolean frameScheduled, dirty = true;
  static ViewRoot touchRoot;
  static ViewRoot focusRoot;
  /** views that need a frame callback every frame (GLSurfaceView) */
  public interface FrameClient { boolean onFrame(long now); boolean isShown(); }
  static final ArrayList<FrameClient> clients = new ArrayList<>();
  public static long frameTime;

  @JSBody(script = "return HW.screen();")
  static native JSObject screenCtx();
  @JSBody(script = "HW.requestFrame();")
  static native void requestFrameJS();
  @JSBody(script = "HW.clearScreen();")
  static native void clearScreen();
  @JSBody(params = {"on"}, script = "HW.showGL(on);")
  static native void showGL(boolean on);

  // ---------------------------------------------------------------- window manager
  static final WindowManager MANAGER = new WindowManager() {
    public void addView(View v, ViewGroup.LayoutParams p) { Windows.add(v, (WindowManager.LayoutParams) p, null); }
    public void updateViewLayout(View v, ViewGroup.LayoutParams p) { Windows.update(v, (WindowManager.LayoutParams) p); }
    public void removeView(View v) { Windows.remove(v); }
    public void removeViewImmediate(View v) { Windows.remove(v); }
    public Display getDefaultDisplay() { return Display.DEFAULT; }
  };
  public static WindowManager manager() { return MANAGER; }

  static ViewRoot find(View v) { for (ViewRoot r : roots) if (r.view == v) return r; return null; }

  /** group: the activity window this window belongs to (dialogs), or null for a new top-level window */
  public static ViewRoot add(View v, WindowManager.LayoutParams p, ViewRoot group) {
    if (find(v) != null) throw new IllegalStateException("View " + v + " has already been added to the window manager.");
    ViewRoot r = new ViewRoot(v, p);
    r.group = group == null ? r : group;
    int idx = roots.size();
    boolean toast = p.type >= WindowManager.LayoutParams.FIRST_SYSTEM_WINDOW;
    if (!toast) {
      // stay below system windows (toasts)
      while (idx > 0 && roots.get(idx - 1).lp.type >= WindowManager.LayoutParams.FIRST_SYSTEM_WINDOW) idx--;
      if (group != null) {
        // right above the last window of the same group
        int last = -1;
        for (int i = 0; i < roots.size(); i++) if (roots.get(i).group == group) last = i;
        if (last >= 0) idx = Math.min(idx, last + 1);
      }
    }
    roots.add(idx, r);
    r.attach();
    scheduleFrame(true);
    return r;
  }
  public static void update(View v, WindowManager.LayoutParams p) {
    ViewRoot r = find(v);
    if (r == null) throw new IllegalArgumentException("View not attached to window manager");
    r.lp.copyFrom(p);
    r.requestLayout();
  }
  public static void remove(View v) {
    ViewRoot r = find(v);
    if (r == null) throw new IllegalArgumentException("View not attached to window manager");
    roots.remove(r);
    if (touchRoot == r) touchRoot = null;
    if (focusRoot == r) { r.setWindowFocus(false); focusRoot = null; }
    r.detach();
    // windows of a removed activity go with it
    if (r.group == r) {
      for (ViewRoot o : new ArrayList<>(roots)) if (o.group == r) { roots.remove(o); o.detach(); if (focusRoot == o) focusRoot = null; }
    }
    scheduleFrame(true);
  }
  public static void setVisible(View v, boolean vis) {
    ViewRoot r = find(v);
    if (r != null && r.visible != vis) { r.visible = vis; r.requestLayout(); scheduleFrame(true); }
  }
  public static ViewRoot rootOf(View v) { return find(v); }
  static String debugState() {
    StringBuilder b = new StringBuilder("touch mode ").append(ViewRoot.touchMode).append('\n');
    for (ViewRoot r : roots) b.append(r == focusRoot ? "* " : "  ").append(r.view.getClass().getName()).append(" type=").append(r.lp.type)
        .append(" vis=").append(r.visible).append(" ").append(r.x).append(',').append(r.y).append(' ').append(r.w).append('x').append(r.h)
        .append(" flags=0x").append(Integer.toHexString(r.lp.flags)).append(" title=").append(r.lp.getTitle()).append('\n');
    return b.toString();
  }
  /** the view tree of every window: class, id, bounds, visibility, and a text or drawable hint */
  static String debugViews() {
    StringBuilder b = new StringBuilder();
    for (ViewRoot r : roots) dumpView(b, r.view, 0);
    return b.toString();
  }
  private static void dumpView(StringBuilder b, View v, int depth) {
    for (int i = 0; i < depth; i++) b.append("  ");
    b.append(v.getClass().getName());
    int id = v.getId();
    if (id != View.NO_ID) {
      try { b.append(" #").append(v.getResources().getResourceEntryName(id)); } catch (RuntimeException e) { b.append(" #0x").append(Integer.toHexString(id)); }
    }
    b.append(' ').append(v.getLeft()).append(',').append(v.getTop()).append(' ').append(v.getWidth()).append('x').append(v.getHeight());
    if (v.getVisibility() != View.VISIBLE) b.append(v.getVisibility() == View.GONE ? " GONE" : " INVISIBLE");
    if (v instanceof android.widget.TextView t && t.getText() != null && t.getText().length() > 0) {
      String s = t.getText().toString();
      b.append(" \"").append(s.length() > 40 ? s.substring(0, 40) + "..." : s).append('"');
    }
    if (v.isFocused()) b.append(" FOCUSED");
    else if (v.isFocusable()) b.append(v.isFocusableInTouchMode() ? " focusable(touch)" : " focusable");
    if (v.isSelected()) b.append(" selected");
    if (v instanceof android.widget.AdapterView<?> av) b.append(" sel=").append(av.getSelectedItemPosition()).append(" first=").append(av.getFirstVisiblePosition());
    if (v.getBackground() != null) b.append(" bg=").append(v.getBackground().getClass().getSimpleName());
    if (v instanceof android.widget.ImageView iv && iv.getDrawable() != null) b.append(" img=").append(iv.getDrawable().getClass().getSimpleName())
        .append('(').append(iv.getDrawable().getIntrinsicWidth()).append('x').append(iv.getDrawable().getIntrinsicHeight()).append(')');
    b.append('\n');
    if (v instanceof android.view.ViewGroup g) for (int i = 0; i < g.getChildCount(); i++) dumpView(b, g.getChildAt(i), depth + 1);
  }
  static boolean covered(ViewRoot r) {
    for (int i = roots.size() - 1; i >= 0; i--) {
      ViewRoot o = roots.get(i);
      if (o == r) return false;
      if (o.visible && !o.firstLayout && o.isFullscreenOpaque()) return true;
    }
    return false;
  }

  public static void addFrameClient(FrameClient c) { if (!clients.contains(c)) clients.add(c); scheduleFrame(false); }
  public static void removeFrameClient(FrameClient c) { clients.remove(c); }

  // ---------------------------------------------------------------- frames
  public static void scheduleFrame(boolean invalidate) {
    if (invalidate) { dirty = true; android.os.Looper.getMainLooper().getQueue().setBarrier(true); }
    if (!frameScheduled) { frameScheduled = true; requestFrameJS(); }
  }

  static void frame() {
    frameScheduled = false;
    android.os.Looper.getMainLooper().getQueue().setBarrier(false);
    long now = SystemClock.uptimeMillis();
    frameTime = now;
    android.view.Choreographer.mainFrame(now);   // frame callbacks (animations) before layout and drawing
    if (screen == null) screen = new Canvas(screenCtx(), W, H);
    for (ViewRoot r : new ArrayList<>(roots)) {
      try { r.traverse(W, H); } catch (Throwable t) { Host.uncaught(t); }
    }
    updateFocus();
    int base = 0;
    for (int i = roots.size() - 1; i >= 0; i--) { ViewRoot r = roots.get(i); if (r.visible && r.isFullscreenOpaque()) { base = i; break; } }
    boolean wantFrame = false;
    boolean gl = false;
    for (FrameClient c : new ArrayList<>(clients)) {
      try {
        if (c.isShown()) { gl = true; wantFrame |= c.onFrame(now); }
      } catch (Throwable t) { Host.uncaught(t); }
    }
    showGL(gl);
    if (dirty) {
      dirty = false;
      clearScreen();
      android.webkit.WebView.beginFrame();
      int save = screen.save();
      for (int i = base; i < roots.size(); i++) {
        try { roots.get(i).draw(screen, now); } catch (Throwable t) { Host.uncaught(t); }
      }
      screen.restoreToCount(save);
      android.webkit.WebView.endFrame();
    }
    if (dirty || wantFrame) scheduleFrame(false);
  }

  static void updateFocus() {
    ViewRoot top = null;
    for (int i = roots.size() - 1; i >= 0; i--) {
      ViewRoot r = roots.get(i);
      if (!r.visible || (r.lp.flags & WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE) != 0 || r.lp.type >= WindowManager.LayoutParams.FIRST_SYSTEM_WINDOW) continue;
      if (r.firstLayout) continue;
      top = r; break;
    }
    if (top != focusRoot) {
      ViewRoot old = focusRoot;
      focusRoot = top;
      if (old != null) old.setWindowFocus(false);
      if (top != null) top.setWindowFocus(true);
      TextInput.hide();
    }
  }

  // ---------------------------------------------------------------- input
  static final int MAXP = 10;
  static final int[] pid = new int[MAXP];
  static final float[] px = new float[MAXP], py = new float[MAXP];
  static int pcount;
  static long downTime;

  static ViewRoot hitWindow(float x, float y) {
    for (int i = roots.size() - 1; i >= 0; i--) {
      ViewRoot r = roots.get(i);
      if (!r.visible || (r.lp.flags & WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE) != 0 || r.lp.type >= WindowManager.LayoutParams.FIRST_SYSTEM_WINDOW) continue;
      if (r.contains(x, y)) return r;
      // a focusable window is touch-modal unless it says otherwise: a touch outside it is "outside" and goes nowhere
      // else; a window watching outside touches is told, and the touch goes on to the windows below
      boolean modal = (r.lp.flags & (WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)) == 0;
      if (modal || (r.lp.flags & WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH) != 0) {
        long t = SystemClock.uptimeMillis();
        MotionEvent o = MotionEvent.obtain(t, t, MotionEvent.ACTION_OUTSIDE, x - r.x, y - r.y, 0);
        r.view.dispatchTouchEvent(o);
      }
      if (modal) return null;
    }
    return null;
  }

  /** Android pointer ids (the lowest free) for the browser's */
  static final int[] aid = new int[MAXP];
  static int tool = MotionEvent.TOOL_TYPE_FINGER, buttons;
  static float pressure = 1;
  /** coalesced samples waiting for the next move: browser id, x, y, age in ms */
  static final java.util.ArrayList<float[]> samples = new java.util.ArrayList<>();
  static void touchSample(int id, float x, float y, float ageMs) { samples.add(new float[] {id, x, y, ageMs}); }

  /** action: 0 down, 1 up, 2 move, 3 cancel; coordinates in screen pixels; tool: Android tool type; buttons: the mouse's */
  static void touch(int action, int id, float x, float y, float press, int toolType, int btns) {
    long t = SystemClock.uptimeMillis();
    int idx = -1;
    for (int i = 0; i < pcount; i++) if (pid[i] == id) idx = i;
    int act, actionButton = 0;
    if (action == 0) {
      if (idx >= 0) { samples.clear(); return; }
      if (pcount == 0) {
        ViewRoot.ensureTouchMode(true);
        touchRoot = hitWindow(x, y);
        downTime = t;
        TextInput.touchedOutside(x, y);
        tool = toolType;
      }
      if (pcount >= MAXP) { samples.clear(); return; }
      int free = 0;
      for (boolean used = true; used; ) { used = false; for (int i = 0; i < pcount; i++) if (aid[i] == free) { used = true; free++; break; } }
      pid[pcount] = id; aid[pcount] = free; px[pcount] = x; py[pcount] = y;
      idx = pcount++;
      act = pcount == 1 ? MotionEvent.ACTION_DOWN : MotionEvent.ACTION_POINTER_DOWN | (idx << MotionEvent.ACTION_POINTER_INDEX_SHIFT);
      actionButton = btns & ~buttons;
    } else {
      if (idx < 0) { samples.clear(); return; }
      if (action == 2) act = MotionEvent.ACTION_MOVE;
      else if (action == 3) act = MotionEvent.ACTION_CANCEL;
      else { act = pcount == 1 ? MotionEvent.ACTION_UP : MotionEvent.ACTION_POINTER_UP | (idx << MotionEvent.ACTION_POINTER_INDEX_SHIFT); actionButton = buttons & ~btns; }
    }
    buttons = btns;
    pressure = press;
    ViewRoot r = touchRoot;
    if (r != null && roots.contains(r)) {
      float[] xs = new float[pcount], ys = new float[pcount];
      int[] ids = new int[pcount];
      for (int i = 0; i < pcount; i++) { ids[i] = aid[i]; xs[i] = px[i] - r.x; ys[i] = py[i] - r.y; }
      if (idx >= 0) { xs[idx] = x - r.x; ys[idx] = y - r.y; }
      MotionEvent e = MotionEvent.obtain(downTime, t, act, pcount, ids, xs, ys, metaState);
      e.a2wSetRawOffset(r.x, r.y);
      e.a2wSetPointer(tool, tool == MotionEvent.TOOL_TYPE_MOUSE ? btns : 0, tool == MotionEvent.TOOL_TYPE_MOUSE ? actionButton : 0, press);
      if (action == 2 && !samples.isEmpty()) {
        // the browser's coalesced moves become the event's history
        float[] hx = new float[pcount], hy = new float[pcount];
        for (int i = 0; i < pcount; i++) { hx[i] = px[i] - r.x; hy[i] = py[i] - r.y; }
        for (float[] smp : samples) {
          int k = -1;
          for (int i = 0; i < pcount; i++) if (pid[i] == (int) smp[0]) k = i;
          if (k < 0) continue;
          hx[k] = smp[1] - r.x; hy[k] = smp[2] - r.y;
          e.a2wAddHistory((long) ((t - smp[3]) * 1000000L), hx.clone(), hy.clone());
        }
      }
      try { r.view.dispatchTouchEvent(e); } catch (Throwable th) { Host.uncaught(th); }
    }
    samples.clear();
    if (idx >= 0) { px[idx] = x; py[idx] = y; }
    if (action == 1 || action == 3) {
      if (action == 3) pcount = 0;
      else {
        for (int i = idx; i < pcount - 1; i++) { pid[i] = pid[i + 1]; aid[i] = aid[i + 1]; px[i] = px[i + 1]; py[i] = py[i + 1]; }
        pcount--;
      }
      if (pcount == 0) { touchRoot = null; buttons = 0; }
    }
  }

  // ---- mouse hover and wheel: generic motion events to the window under the pointer
  static ViewRoot hoverRoot;
  static ViewRoot windowAt(float x, float y) {
    for (int i = roots.size() - 1; i >= 0; i--) {
      ViewRoot r = roots.get(i);
      if (!r.visible || (r.lp.flags & WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE) != 0 || r.lp.type >= WindowManager.LayoutParams.FIRST_SYSTEM_WINDOW) continue;
      if (r.contains(x, y)) return r;
      if ((r.lp.flags & (WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)) == 0) return null;
    }
    return null;
  }
  private static void generic(ViewRoot r, int action, float x, float y, float hscroll, float vscroll) {
    if (r == null || !roots.contains(r)) return;
    long t = SystemClock.uptimeMillis();
    MotionEvent e = MotionEvent.obtain(t, t, action, 1, new int[] {0}, new float[] {x - r.x}, new float[] {y - r.y}, metaState);
    e.a2wSetRawOffset(r.x, r.y);
    e.a2wSetPointer(MotionEvent.TOOL_TYPE_MOUSE, buttons, 0, 0);
    if (action == MotionEvent.ACTION_SCROLL) {
      MotionEvent.PointerCoords c = new MotionEvent.PointerCoords();
      e.getPointerCoords(0, c);
      c.setAxisValue(MotionEvent.AXIS_HSCROLL, hscroll);
      c.setAxisValue(MotionEvent.AXIS_VSCROLL, vscroll);
      MotionEvent.PointerProperties pp = new MotionEvent.PointerProperties();
      e.getPointerProperties(0, pp);
      e = MotionEvent.obtain(t, t, action, 1, new MotionEvent.PointerProperties[] {pp}, new MotionEvent.PointerCoords[] {c}, metaState, buttons, 1, 1, 0, 0,
          android.view.InputDevice.SOURCE_MOUSE, 0);
      e.a2wSetRawOffset(r.x, r.y);
    }
    try { r.view.dispatchGenericMotionEvent(e); } catch (Throwable th) { Host.uncaught(th); }
  }
  /** action: MotionEvent.ACTION_HOVER_MOVE, _ENTER or _EXIT */
  static void hover(int action, float x, float y) {
    ViewRoot r = action == MotionEvent.ACTION_HOVER_EXIT ? null : windowAt(x, y);
    if (r != hoverRoot) {
      if (hoverRoot != null) generic(hoverRoot, MotionEvent.ACTION_HOVER_EXIT, x, y, 0, 0);
      hoverRoot = r;
      if (r != null) generic(r, MotionEvent.ACTION_HOVER_ENTER, x, y, 0, 0);
      return;
    }
    if (r != null && action == MotionEvent.ACTION_HOVER_MOVE) generic(r, action, x, y, 0, 0);
  }
  static void scroll(float x, float y, float h, float v) { ViewRoot.ensureTouchMode(true); generic(windowAt(x, y), MotionEvent.ACTION_SCROLL, x, y, h, v); }

  /** the keyboard's meta state, as the page last reported it */
  static int metaState;
  static void key(int code, boolean down, int repeat, int meta) {
    metaState = meta;
    ViewRoot r = focusRoot;
    if (r == null) return;
    long t = SystemClock.uptimeMillis();
    KeyEvent e = new KeyEvent(t, t, down ? KeyEvent.ACTION_DOWN : KeyEvent.ACTION_UP, code, repeat, meta, 0, 0, 0, android.view.InputDevice.SOURCE_KEYBOARD);
    try { r.processKey(e); } catch (Throwable th) { Host.uncaught(th); }
  }
  /** Instrumentation's injected key: through the key window's full input path */
  public static boolean injectKey(KeyEvent e) {
    ViewRoot r = focusRoot;
    if (r == null) return false;
    return r.processKey(e);
  }
  /** Instrumentation's injected touch event (window coordinates of the top touchable window) */
  public static boolean injectTouch(MotionEvent e) {
    ViewRoot r = null;
    for (int i = roots.size() - 1; i >= 0 && r == null; i--) { ViewRoot c = roots.get(i); if (c.visible && (c.lp.flags & WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE) == 0) r = c; }
    if (r == null) return false;
    if (e.getActionMasked() == MotionEvent.ACTION_DOWN) ViewRoot.ensureTouchMode(true);
    return r.view.dispatchTouchEvent(e);
  }
}
