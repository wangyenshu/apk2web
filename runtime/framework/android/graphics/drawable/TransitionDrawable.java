package android.graphics.drawable;

import android.graphics.Canvas;
import android.os.SystemClock;

public class TransitionDrawable extends LayerDrawable {
  private long start; private int duration; private boolean reverse, crossFade;
  public TransitionDrawable(Drawable[] layers) { super(layers); }
  public void startTransition(int ms) { start = SystemClock.uptimeMillis(); duration = ms; reverse = false; invalidateSelf(); }
  public void reverseTransition(int ms) { start = SystemClock.uptimeMillis(); duration = ms; reverse = true; invalidateSelf(); }
  public void resetTransition() { duration = 0; reverse = false; invalidateSelf(); }
  public void setCrossFadeEnabled(boolean b) { crossFade = b; }
  public boolean isCrossFadeEnabled() { return crossFade; }
  @Override public void draw(Canvas c) {
    float t = duration <= 0 ? (reverse ? 0 : 0) : Math.min(1, (SystemClock.uptimeMillis() - start) / (float) duration);
    if (reverse) t = 1 - t;
    if (layers.size() < 2) { super.draw(c); return; }
    Drawable a = layers.get(0).d, b = layers.get(1).d;
    a.setAlpha(crossFade ? (int) (255 * (1 - t)) : 255); a.draw(c);
    b.setAlpha((int) (255 * t)); b.draw(c);
    if (duration > 0 && SystemClock.uptimeMillis() - start < duration) invalidateSelf();
  }
}
