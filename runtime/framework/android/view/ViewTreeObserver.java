package android.view;

import java.util.concurrent.CopyOnWriteArrayList;

public final class ViewTreeObserver {
  public interface OnGlobalFocusChangeListener { void onGlobalFocusChanged(View oldFocus, View newFocus); }
  public interface OnGlobalLayoutListener { void onGlobalLayout(); }
  public interface OnPreDrawListener { boolean onPreDraw(); }
  public interface OnDrawListener { void onDraw(); }
  public interface OnTouchModeChangeListener { void onTouchModeChanged(boolean isInTouchMode); }
  public interface OnScrollChangedListener { void onScrollChanged(); }
  public interface OnWindowFocusChangeListener { void onWindowFocusChanged(boolean hasFocus); }
  public interface OnWindowAttachListener { void onWindowAttached(); void onWindowDetached(); }

  CopyOnWriteArrayList<OnGlobalFocusChangeListener> focus;
  CopyOnWriteArrayList<OnGlobalLayoutListener> layout;
  CopyOnWriteArrayList<OnPreDrawListener> preDraw;
  CopyOnWriteArrayList<OnDrawListener> draw;
  CopyOnWriteArrayList<OnTouchModeChangeListener> touch;
  CopyOnWriteArrayList<OnScrollChangedListener> scroll;
  CopyOnWriteArrayList<OnWindowFocusChangeListener> wfocus;
  private boolean alive = true;

  public ViewTreeObserver() {}
  void merge(ViewTreeObserver o) {
    if (o.focus != null) { if (focus == null) focus = new CopyOnWriteArrayList<>(); focus.addAll(o.focus); }
    if (o.layout != null) { if (layout == null) layout = new CopyOnWriteArrayList<>(); layout.addAll(o.layout); }
    if (o.preDraw != null) { if (preDraw == null) preDraw = new CopyOnWriteArrayList<>(); preDraw.addAll(o.preDraw); }
    if (o.draw != null) { if (draw == null) draw = new CopyOnWriteArrayList<>(); draw.addAll(o.draw); }
    if (o.touch != null) { if (touch == null) touch = new CopyOnWriteArrayList<>(); touch.addAll(o.touch); }
    if (o.scroll != null) { if (scroll == null) scroll = new CopyOnWriteArrayList<>(); scroll.addAll(o.scroll); }
    if (o.wfocus != null) { if (wfocus == null) wfocus = new CopyOnWriteArrayList<>(); wfocus.addAll(o.wfocus); }
    o.alive = false;
  }
  public boolean isAlive() { return alive; }
  public void addOnGlobalFocusChangeListener(OnGlobalFocusChangeListener l) { if (focus == null) focus = new CopyOnWriteArrayList<>(); focus.add(l); }
  public void removeOnGlobalFocusChangeListener(OnGlobalFocusChangeListener l) { if (focus != null) focus.remove(l); }
  public void addOnGlobalLayoutListener(OnGlobalLayoutListener l) { if (layout == null) layout = new CopyOnWriteArrayList<>(); layout.add(l); }
  public void removeGlobalOnLayoutListener(OnGlobalLayoutListener l) { removeOnGlobalLayoutListener(l); }
  public void removeOnGlobalLayoutListener(OnGlobalLayoutListener l) { if (layout != null) layout.remove(l); }
  public void addOnPreDrawListener(OnPreDrawListener l) { if (preDraw == null) preDraw = new CopyOnWriteArrayList<>(); preDraw.add(l); }
  public void removeOnPreDrawListener(OnPreDrawListener l) { if (preDraw != null) preDraw.remove(l); }
  public void addOnDrawListener(OnDrawListener l) { if (draw == null) draw = new CopyOnWriteArrayList<>(); draw.add(l); }
  public void removeOnDrawListener(OnDrawListener l) { if (draw != null) draw.remove(l); }
  public void addOnTouchModeChangeListener(OnTouchModeChangeListener l) { if (touch == null) touch = new CopyOnWriteArrayList<>(); touch.add(l); }
  public void removeOnTouchModeChangeListener(OnTouchModeChangeListener l) { if (touch != null) touch.remove(l); }
  public void addOnScrollChangedListener(OnScrollChangedListener l) { if (scroll == null) scroll = new CopyOnWriteArrayList<>(); scroll.add(l); }
  public void removeOnScrollChangedListener(OnScrollChangedListener l) { if (scroll != null) scroll.remove(l); }
  public void addOnWindowFocusChangeListener(OnWindowFocusChangeListener l) { if (wfocus == null) wfocus = new CopyOnWriteArrayList<>(); wfocus.add(l); }
  public void removeOnWindowFocusChangeListener(OnWindowFocusChangeListener l) { if (wfocus != null) wfocus.remove(l); }

  public final void dispatchOnGlobalFocusChange(View o, View n) { if (focus != null) for (OnGlobalFocusChangeListener l : focus) l.onGlobalFocusChanged(o, n); }
  public final void dispatchOnGlobalLayout() { if (layout != null) for (OnGlobalLayoutListener l : layout) l.onGlobalLayout(); }
  public final boolean dispatchOnPreDraw() { boolean cancel = false; if (preDraw != null) for (OnPreDrawListener l : preDraw) cancel |= !l.onPreDraw(); return cancel; }
  public final void dispatchOnDraw() { if (draw != null) for (OnDrawListener l : draw) l.onDraw(); }
  final void dispatchOnTouchModeChanged(boolean t) { if (touch != null) for (OnTouchModeChangeListener l : touch) l.onTouchModeChanged(t); }
  final void dispatchOnScrollChanged() { if (scroll != null) for (OnScrollChangedListener l : scroll) l.onScrollChanged(); }
  public final void dispatchOnWindowFocusChange(boolean f) { if (wfocus != null) for (OnWindowFocusChangeListener l : wfocus) l.onWindowFocusChanged(f); }
}
