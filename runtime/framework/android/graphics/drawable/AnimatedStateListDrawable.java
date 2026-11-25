package android.graphics.drawable;

import android.graphics.Canvas;
import java.util.ArrayList;

/**
 * android.graphics.drawable.AnimatedStateListDrawable (API 21): a state list whose keyframes (states with ids) can
 * be joined by animated transitions (AnimationDrawable, AnimatedVectorDrawable), played when the state moves from
 * one keyframe to the other. Reversed transitions jump to the target.
 */
public class AnimatedStateListDrawable extends StateListDrawable {
  private final ArrayList<Integer> ids = new ArrayList<>();
  private static final class Transition { int from, to; Drawable d; boolean reversible; }
  private final ArrayList<Transition> transitions = new ArrayList<>();
  private Drawable running;

  public AnimatedStateListDrawable() {}
  @Override public void addState(int[] stateSet, Drawable drawable) { addState(stateSet, drawable, 0); }
  public void addState(int[] stateSet, Drawable drawable, int id) {
    if (drawable == null) throw new IllegalArgumentException("Drawable must not be null");
    ids.add(id);
    super.addState(stateSet, drawable);
  }
  public <T extends Drawable & Animatable> void addTransition(int fromId, int toId, T transition, boolean reversible) {
    if (transition == null) throw new IllegalArgumentException("Transition drawable must not be null");
    Transition t = new Transition();
    t.from = fromId; t.to = toId; t.d = transition; t.reversible = reversible;
    transition.setCallback(this);
    transitions.add(t);
  }
  private int idAt(int index) { return index >= 0 && index < ids.size() ? ids.get(index) : 0; }
  @Override protected boolean onStateChange(int[] state) {
    int target = indexOf(state);
    if (target < 0) target = indexOf(android.util.StateSet.WILD_CARD);
    int from = idAt(curIndex), to = idAt(target);
    if (target != curIndex && from != 0 && to != 0 && isVisible()) {
      for (Transition t : transitions) {
        if (t.from == from && t.to == to) {
          stopRunning();
          running = t.d;
          running.setVisible(true, true);
          running.setBounds(getBounds());
          running.setState(state);
          ((Animatable) running).start();
          break;
        }
      }
    }
    return super.onStateChange(state) || running != null;
  }
  private void stopRunning() {
    if (running != null) { ((Animatable) running).stop(); running.setVisible(false, false); running = null; }
  }
  @Override public void draw(Canvas c) {
    if (running != null) {
      if (((Animatable) running).isRunning()) { running.setBounds(getBounds()); running.draw(c); invalidateSelf(); return; }
      stopRunning();
    }
    super.draw(c);
  }
  @Override public void jumpToCurrentState() { stopRunning(); super.jumpToCurrentState(); }
  @Override public boolean setVisible(boolean visible, boolean restart) {
    boolean ch = super.setVisible(visible, restart);
    if (!visible) stopRunning();
    return ch;
  }
  @Override public boolean isStateful() { return true; }
  @Override public void applyTheme(android.content.res.Resources.Theme theme) {}
  @Override public void inflate(android.content.res.Resources r, org.xmlpull.v1.XmlPullParser parser, android.util.AttributeSet attrs, android.content.res.Resources.Theme theme) {}
  @Override public Drawable mutate() { return this; }
  @Override public void invalidateDrawable(Drawable who) { if (who == running || who == current) invalidateSelf(); }
}
