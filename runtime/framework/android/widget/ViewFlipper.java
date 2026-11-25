package android.widget;

import android.content.Context;
import android.util.AttributeSet;

public class ViewFlipper extends ViewAnimator {
  private int interval = 3000;
  private boolean running;
  private final Runnable flip = new Runnable() { public void run() { if (running) { showNext(); postDelayed(this, interval); } } };
  public ViewFlipper(Context c) { super(c); }
  public ViewFlipper(Context c, AttributeSet a) { super(c, a); }
  public void setFlipInterval(int ms) { interval = ms; }
  public void startFlipping() { if (!running) { running = true; postDelayed(flip, interval); } }
  public void stopFlipping() { running = false; removeCallbacks(flip); }
  public boolean isFlipping() { return running; }
  public void setAutoStart(boolean b) { if (b) startFlipping(); }
  public boolean isAutoStart() { return running; }
}
