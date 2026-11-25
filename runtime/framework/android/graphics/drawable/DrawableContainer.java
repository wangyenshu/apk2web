package android.graphics.drawable;

import android.graphics.*;
import java.util.ArrayList;

public class DrawableContainer extends Drawable implements Drawable.Callback {
  final ArrayList<Drawable> children = new ArrayList<>();
  Drawable current;
  int curIndex = -1;
  int alpha = 255;
  ColorFilter filter;
  boolean constantSize, variablePadding;
  private int enterFade, exitFade;
  private Drawable last;
  private long enterEnd, exitEnd;
  public void setEnterFadeDuration(int ms) { enterFade = ms; }
  public void setExitFadeDuration(int ms) { exitFade = ms; }

  int addChild(Drawable d) {
    children.add(d);
    d.setVisible(false, true);
    d.setCallback(this);
    return children.size() - 1;
  }
  public boolean selectDrawable(int idx) {
    if (idx == curIndex) return false;
    long now = android.os.SystemClock.uptimeMillis();
    if (last != null) { last.setVisible(false, false); last = null; }
    if (exitFade > 0 && current != null) { last = current; exitEnd = now + exitFade; }
    else if (current != null) current.setVisible(false, false);
    enterEnd = enterFade > 0 && current != null ? now + enterFade : 0;
    if (idx >= 0 && idx < children.size()) {
      Drawable d = children.get(idx);
      current = d; curIndex = idx;
      d.setVisible(isVisible(), true);
      d.setAlpha(alpha);
      d.setColorFilter(filter);
      d.setState(getState());
      d.setLevel(getLevel());
      d.setBounds(getBounds());
    } else { current = null; curIndex = -1; }
    invalidateSelf();
    return true;
  }
  @Override public void draw(Canvas c) {
    long now = android.os.SystemClock.uptimeMillis();
    boolean animating = false;
    if (current != null) {
      if (enterEnd > now) { current.setAlpha(alpha * (int) (255 - 255 * (enterEnd - now) / enterFade) / 255); animating = true; }
      else if (enterEnd != 0) { current.setAlpha(alpha); enterEnd = 0; }
      current.draw(c);
    }
    if (last != null) {
      if (exitEnd > now) { last.setAlpha(alpha * (int) (255 * (exitEnd - now) / exitFade) / 255); last.draw(c); animating = true; }
      else { last.setVisible(false, false); last.setAlpha(alpha); last = null; }
    }
    if (animating) invalidateSelf();
  }
  @Override public void setAlpha(int a) { if (alpha != a) { alpha = a; if (current != null) current.setAlpha(a); } }
  @Override public int getAlpha() { return alpha; }
  @Override public void setColorFilter(ColorFilter cf) { filter = cf; if (current != null) current.setColorFilter(cf); }
  @Override protected void onBoundsChange(Rect b) { if (current != null) current.setBounds(b); }
  @Override public boolean isStateful() { return true; }
  @Override protected boolean onStateChange(int[] s) { return current != null && current.setState(s); }
  @Override protected boolean onLevelChange(int l) { return current != null && current.setLevel(l); }
  @Override public int getIntrinsicWidth() {
    if (constantSize) { int m = -1; for (Drawable d : children) m = Math.max(m, d.getIntrinsicWidth()); return m; }
    return current != null ? current.getIntrinsicWidth() : -1;
  }
  @Override public int getIntrinsicHeight() {
    if (constantSize) { int m = -1; for (Drawable d : children) m = Math.max(m, d.getIntrinsicHeight()); return m; }
    return current != null ? current.getIntrinsicHeight() : -1;
  }
  @Override public int getMinimumWidth() { return current != null ? current.getMinimumWidth() : 0; }
  @Override public int getMinimumHeight() { return current != null ? current.getMinimumHeight() : 0; }
  @Override public boolean getPadding(Rect p) { if (current != null) return current.getPadding(p); return super.getPadding(p); }
  @Override public boolean setVisible(boolean v, boolean restart) { boolean c = super.setVisible(v, restart); if (current != null) current.setVisible(v, restart); return c; }
  @Override public Drawable getCurrent() { return current; }
  @Override public int getOpacity() { return current == null ? PixelFormat.TRANSPARENT : current.getOpacity(); }
  public void invalidateDrawable(Drawable who) { if (who == current || who == last) invalidateSelf(); }
  @Override public void jumpToCurrentState() {
    if (last != null) { last.setVisible(false, false); last.setAlpha(alpha); last = null; }
    if (current != null) { current.jumpToCurrentState(); current.setAlpha(alpha); }
    enterEnd = 0;
  }
  public void scheduleDrawable(Drawable who, Runnable what, long when) { if (who == current && callback != null) callback.scheduleDrawable(this, what, when); }
  public void unscheduleDrawable(Drawable who, Runnable what) { if (who == current && callback != null) callback.unscheduleDrawable(this, what); }
  @Override public Drawable mutate() { return this; }
}
