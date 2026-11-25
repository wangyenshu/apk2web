package android.widget;

import android.content.Context;
import android.util.AttributeSet;

/** Stars drawn as text; touching sets the rating. */
public class RatingBar extends AbsSeekBar {
  public interface OnRatingBarChangeListener { void onRatingChanged(RatingBar bar, float rating, boolean fromUser); }
  private int stars = 5;
  private float rating, step = 0.5f;
  private boolean indicator;
  private OnRatingBarChangeListener listener;
  public RatingBar(Context c) { this(c, null); }
  public RatingBar(Context c, AttributeSet a) { this(c, a, 0); }
  public RatingBar(Context c, AttributeSet a, int d) {
    super(c, a, d);
    if (a != null) {
      String ns = "http://schemas.android.com/apk/res/android";
      stars = a.getAttributeIntValue(ns, "numStars", 5);
      rating = a.getAttributeFloatValue(ns, "rating", 0);
      step = a.getAttributeFloatValue(ns, "stepSize", 0.5f);
      indicator = a.getAttributeBooleanValue(ns, "isIndicator", false);
    }
  }
  public void setNumStars(int n) { stars = n; invalidate(); }
  public int getNumStars() { return stars; }
  public void setRating(float r) { rating = Math.max(0, Math.min(stars, r)); invalidate(); if (listener != null) listener.onRatingChanged(this, rating, false); }
  public float getRating() { return rating; }
  public void setStepSize(float s) { step = s; }
  public float getStepSize() { return step; }
  public void setIsIndicator(boolean b) { indicator = b; }
  public boolean isIndicator() { return indicator; }
  public void setOnRatingBarChangeListener(OnRatingBarChangeListener l) { listener = l; }
  public OnRatingBarChangeListener getOnRatingBarChangeListener() { return listener; }
  @Override protected void onMeasure(int ws, int hs) {
    float d = getResources().getDisplayMetrics().density;
    setMeasuredDimension(resolveSize((int) (stars * 28 * d), ws), resolveSize((int) (28 * d), hs));
  }
  @Override protected void onDraw(android.graphics.Canvas c) {
    float d = getResources().getDisplayMetrics().density, w = 28 * d;
    android.graphics.Paint p = new android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG);
    p.setTextSize(24 * d);
    for (int i = 0; i < stars; i++) { p.setColor(i < Math.round(rating) ? 0xffffbb33 : 0x66888888); c.drawText("★", i * w, 22 * d, p); }
  }
  @Override public boolean onTouchEvent(android.view.MotionEvent e) {
    if (indicator || !isEnabled()) return false;
    float d = getResources().getDisplayMetrics().density, r = e.getX() / (28 * d);
    r = Math.max(0, Math.min(stars, (float) Math.ceil(r / step) * step));
    if (r != rating) { rating = r; invalidate(); if (listener != null) listener.onRatingChanged(this, rating, true); }
    return true;
  }
}
