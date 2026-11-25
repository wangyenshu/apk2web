package android.graphics.drawable;

import android.graphics.*;
import android.os.SystemClock;

/** Stand-in for the framework's rotating progress spinners. */
public class SpinnerDrawable extends Drawable implements Animatable, Runnable {
  final int size; boolean running; int alpha = 255;
  private final Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
  public SpinnerDrawable(int size) { this.size = size; }
  @Override public int getIntrinsicWidth() { return size; }
  @Override public int getIntrinsicHeight() { return size; }
  @Override public void draw(Canvas c) {
    Rect b = getBounds();
    float cx = b.exactCenterX(), cy = b.exactCenterY(), r = Math.min(b.width(), b.height()) / 2f;
    int step = (int) ((SystemClock.uptimeMillis() / 80) % 12);
    p.setStyle(Paint.Style.STROKE);
    p.setStrokeCap(Paint.Cap.ROUND);
    p.setStrokeWidth(Math.max(1.5f, r / 5));
    for (int i = 0; i < 12; i++) {
      double ang = Math.PI * 2 * i / 12;
      int k = (i - step + 12) % 12;
      p.setColor(Color.argb(alpha * (60 + 195 * k / 11) / 255, 255, 255, 255));
      float s = (float) Math.sin(ang), co = (float) Math.cos(ang);
      c.drawLine(cx + co * r * 0.45f, cy + s * r * 0.45f, cx + co * r * 0.9f, cy + s * r * 0.9f, p);
    }
    if (running) scheduleSelf(this, SystemClock.uptimeMillis() + 80);
  }
  public void run() { invalidateSelf(); }
  @Override public void setAlpha(int a) { alpha = a; }
  @Override public void setColorFilter(ColorFilter cf) {}
  @Override public int getOpacity() { return PixelFormat.TRANSLUCENT; }
  public void start() { if (!running) { running = true; invalidateSelf(); } }
  public void stop() { running = false; unscheduleSelf(this); }
  public boolean isRunning() { return running; }
}
