package android.graphics.drawable;

import android.util.StateSet;
import java.util.ArrayList;

public class StateListDrawable extends DrawableContainer {
  final ArrayList<int[]> specs = new ArrayList<>();
  public StateListDrawable() {}
  public void addState(int[] spec, Drawable d) {
    if (d == null) return;
    specs.add(spec);
    addChild(d);
    onStateChange(getState());
  }
  int indexOf(int[] state) {
    for (int i = 0; i < specs.size(); i++) if (StateSet.stateSetMatches(specs.get(i), state)) return i;
    return -1;
  }
  @Override protected boolean onStateChange(int[] state) {
    int idx = indexOf(state);
    if (idx < 0) idx = indexOf(StateSet.WILD_CARD);
    if (selectDrawable(idx)) return true;
    return super.onStateChange(state);
  }
  @Override public boolean isStateful() { return true; }
  /** whether some state spec names state_focused (then a focused view needs no default focus highlight) */
  @Override public boolean hasFocusStateSpecified() {
    for (int[] sp : specs) for (int x : sp) if (x == android.R.attr.state_focused || -x == android.R.attr.state_focused) return true;
    return false;
  }
  public int getStateCount() { return specs.size(); }
  public int[] getStateSet(int i) { return specs.get(i); }
  public Drawable getStateDrawable(int i) { return children.get(i); }
  public int getStateDrawableIndex(int[] s) { return indexOf(s); }
  public int findStateDrawableIndex(int[] stateSet) { return indexOf(stateSet); }
}
