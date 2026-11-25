package android.graphics.drawable;

import android.os.SystemClock;
import java.util.ArrayList;

/** Android 4.4 semantics (an ImageView shows it animating once it has been started or made visible). */
public class AnimationDrawable extends DrawableContainer implements Runnable, Animatable {
  final ArrayList<Integer> durations = new ArrayList<>();
  boolean oneShot;
  int curFrame = -1;
  boolean running, animating;

  public AnimationDrawable() {}
  public void addFrame(Drawable frame, int duration) {
    addChild(frame);
    durations.add(duration);
    if (curFrame < 0) setFrame(0, true, false);
  }
  public int getNumberOfFrames() { return children.size(); }
  public Drawable getFrame(int i) { return children.get(i); }
  public int getDuration(int i) { return durations.get(i); }
  public boolean isOneShot() { return oneShot; }
  public void setOneShot(boolean b) { oneShot = b; }
  @Override public boolean setVisible(boolean visible, boolean restart) {
    boolean changed = super.setVisible(visible, restart);
    if (visible) { if (changed || restart) setFrame(0, true, curFrame >= 0); }
    else unscheduleSelf(this);
    return changed;
  }
  public void start() { if (!isRunning()) run(); }
  public void stop() { if (isRunning()) unscheduleSelf(this); }
  public boolean isRunning() { return running; }
  public void run() { nextFrame(false); }
  @Override public void unscheduleSelf(Runnable what) { curFrame = -1; running = false; super.unscheduleSelf(what); }
  private void nextFrame(boolean unschedule) {
    int next = curFrame + 1;
    int n = children.size();
    if (next >= n) next = 0;
    setFrame(next, unschedule, !oneShot || next < n - 1);
  }
  private void setFrame(int frame, boolean unschedule, boolean animate) {
    if (frame >= children.size()) return;
    animating = animate;
    curFrame = frame;
    selectDrawable(frame);
    if (unschedule || animate) super.unscheduleSelf(this);
    if (animate) {
      curFrame = frame;
      running = true;
      scheduleSelf(this, SystemClock.uptimeMillis() + durations.get(frame));
    }
  }
}
