package android.widget;

import android.content.Context;
import android.view.animation.Interpolator;

public class OverScroller extends Scroller {
  public OverScroller(Context c) { super(c); }
  public OverScroller(Context c, Interpolator i) { super(c, i); }
  public OverScroller(Context c, Interpolator i, float bx, float by) { super(c, i); }
  public OverScroller(Context c, Interpolator i, float bx, float by, boolean flywheel) { super(c, i); }
  public void fling(int sx, int sy, int vx, int vy, int minX, int maxX, int minY, int maxY, int ox, int oy) { fling(sx, sy, vx, vy, minX, maxX, minY, maxY); }
  public boolean springBack(int sx, int sy, int minX, int maxX, int minY, int maxY) { return false; }
  public void notifyHorizontalEdgeReached(int a, int b, int c) {}
  public void notifyVerticalEdgeReached(int a, int b, int c) {}
  public boolean isOverScrolled() { return false; }
}
