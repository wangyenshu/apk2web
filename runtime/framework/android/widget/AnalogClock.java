package android.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.util.AttributeSet;
import android.view.View;
import a2w.Attrs;
import a2w.Values;

/**
 * A clock face with hour, minute and (when given) second hands, in a time zone, as the platform's AnalogClock: the
 * dial and hands are drawables (dial, hand_hour, hand_minute, hand_second), drawn rotated about the center; the
 * default ones are drawn in code. Updated every second while attached and visible.
 */
@Deprecated
public class AnalogClock extends View {
  private Drawable mDial, mHourHand, mMinuteHand, mSecondHand;
  private ColorStateList mDialTint, mHourTint, mMinuteTint, mSecondTint;
  private BlendMode mDialMode, mHourMode, mMinuteMode, mSecondMode;
  private int mDialWidth, mDialHeight;
  private String mTimeZone;
  private java.util.Calendar mTime;
  private float mHour, mMinutes, mSeconds;
  private boolean mChanged, mRegistered, mVisible = true;
  private final Runnable mTick = new Runnable() {
    public void run() {
      removeCallbacks(this);
      if (!mRegistered || !mVisible) return;
      onTimeChanged();
      invalidate();
      long now = System.currentTimeMillis();
      postDelayed(this, 1000 - now % 1000);
    }
  };

  public AnalogClock(Context context) { this(context, null); }
  public AnalogClock(Context context, AttributeSet attrs) { this(context, attrs, 0); }
  public AnalogClock(Context context, AttributeSet attrs, int defStyleAttr) { this(context, attrs, defStyleAttr, 0); }
  public AnalogClock(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr);
    if (mCtorAttrs instanceof Attrs) {
      Attrs a = (Attrs) mCtorAttrs;
      String v;
      if ((v = a.a("dial")) != null && Values.isRef(v)) mDial = getResources().getDrawable(Values.refId(v));
      if ((v = a.a("hand_hour")) != null && Values.isRef(v)) mHourHand = getResources().getDrawable(Values.refId(v));
      if ((v = a.a("hand_minute")) != null && Values.isRef(v)) mMinuteHand = getResources().getDrawable(Values.refId(v));
      if ((v = a.a("hand_second")) != null && Values.isRef(v)) mSecondHand = getResources().getDrawable(Values.refId(v));
      if ((v = a.a("timeZone")) != null) mTimeZone = v;
      if ((v = a.a("dialTint")) != null) mDialTint = colors(v);
      if ((v = a.a("hand_hourTint")) != null) mHourTint = colors(v);
      if ((v = a.a("hand_minuteTint")) != null) mMinuteTint = colors(v);
      if ((v = a.a("hand_secondTint")) != null) mSecondTint = colors(v);
    }
    int size = Math.round(160 * getResources().getDisplayMetrics().density);
    if (mDial == null) mDial = new Part(Part.DIAL, size);
    if (mHourHand == null) mHourHand = new Part(Part.HOUR, size);
    if (mMinuteHand == null) mMinuteHand = new Part(Part.MINUTE, size);
    createTime(mTimeZone);
    mDialWidth = mDial.getIntrinsicWidth();
    mDialHeight = mDial.getIntrinsicHeight();
    applyTints();
  }
  private ColorStateList colors(String v) {
    try { return Values.isRef(v) ? getResources().getColorStateList(Values.refId(v)) : ColorStateList.valueOf(Values.parseColor(v)); }
    catch (RuntimeException e) { return null; }
  }
  private void createTime(String timeZone) {
    mTime = timeZone != null ? java.util.Calendar.getInstance(java.util.TimeZone.getTimeZone(timeZone)) : java.util.Calendar.getInstance();
  }
  private Drawable load(Icon icon) { return icon == null ? null : icon.loadDrawable(getContext()); }
  public void setDial(Icon icon) { Drawable d = load(icon); if (d != null) { mDial = d; mDialWidth = d.getIntrinsicWidth(); mDialHeight = d.getIntrinsicHeight(); } applyTints(); mChanged = true; requestLayout(); invalidate(); }
  public void setHourHand(Icon icon) { Drawable d = load(icon); if (d != null) mHourHand = d; applyTints(); mChanged = true; invalidate(); }
  public void setMinuteHand(Icon icon) { Drawable d = load(icon); if (d != null) mMinuteHand = d; applyTints(); mChanged = true; invalidate(); }
  public void setSecondHand(Icon icon) { mSecondHand = load(icon); applyTints(); mChanged = true; invalidate(); }
  public void setDialTintList(ColorStateList tint) { mDialTint = tint; applyTints(); }
  public ColorStateList getDialTintList() { return mDialTint; }
  public void setDialTintBlendMode(BlendMode mode) { mDialMode = mode; applyTints(); }
  public BlendMode getDialTintBlendMode() { return mDialMode; }
  public void setHourHandTintList(ColorStateList tint) { mHourTint = tint; applyTints(); }
  public ColorStateList getHourHandTintList() { return mHourTint; }
  public void setHourHandTintBlendMode(BlendMode mode) { mHourMode = mode; applyTints(); }
  public BlendMode getHourHandTintBlendMode() { return mHourMode; }
  public void setMinuteHandTintList(ColorStateList tint) { mMinuteTint = tint; applyTints(); }
  public ColorStateList getMinuteHandTintList() { return mMinuteTint; }
  public void setMinuteHandTintBlendMode(BlendMode mode) { mMinuteMode = mode; applyTints(); }
  public BlendMode getMinuteHandTintBlendMode() { return mMinuteMode; }
  public void setSecondHandTintList(ColorStateList tint) { mSecondTint = tint; applyTints(); }
  public ColorStateList getSecondHandTintList() { return mSecondTint; }
  public void setSecondHandTintBlendMode(BlendMode mode) { mSecondMode = mode; applyTints(); }
  public BlendMode getSecondHandTintBlendMode() { return mSecondMode; }
  private void applyTints() {
    tint(mDial, mDialTint, mDialMode); tint(mHourHand, mHourTint, mHourMode); tint(mMinuteHand, mMinuteTint, mMinuteMode); tint(mSecondHand, mSecondTint, mSecondMode);
    invalidate();
  }
  private static void tint(Drawable d, ColorStateList t, BlendMode m) {
    if (d == null || t == null && m == null) return;
    d.mutate();
    if (t != null) d.setTintList(t);
    if (m != null) d.setTintBlendMode(m);
  }
  public String getTimeZone() { return mTimeZone; }
  public void setTimeZone(String timeZone) { mTimeZone = timeZone; createTime(timeZone); onTimeChanged(); invalidate(); }
  @Override public void onVisibilityAggregated(boolean isVisible) {
    super.onVisibilityAggregated(isVisible);
    if (mVisible != isVisible) { mVisible = isVisible; if (isVisible) mTick.run(); else removeCallbacks(mTick); }
  }
  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    if (!mRegistered) { mRegistered = true; createTime(mTimeZone); onTimeChanged(); mTick.run(); }
  }
  @Override protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    if (mRegistered) { mRegistered = false; removeCallbacks(mTick); }
  }
  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int widthMode = MeasureSpec.getMode(widthMeasureSpec), widthSize = MeasureSpec.getSize(widthMeasureSpec);
    int heightMode = MeasureSpec.getMode(heightMeasureSpec), heightSize = MeasureSpec.getSize(heightMeasureSpec);
    float hScale = 1.0f, vScale = 1.0f;
    if (widthMode != MeasureSpec.UNSPECIFIED && widthSize < mDialWidth) hScale = (float) widthSize / (float) mDialWidth;
    if (heightMode != MeasureSpec.UNSPECIFIED && heightSize < mDialHeight) vScale = (float) heightSize / (float) mDialHeight;
    float scale = Math.min(hScale, vScale);
    setMeasuredDimension(resolveSizeAndState((int) (mDialWidth * scale), widthMeasureSpec, 0), resolveSizeAndState((int) (mDialHeight * scale), heightMeasureSpec, 0));
  }
  @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) { super.onSizeChanged(w, h, oldw, oldh); mChanged = true; }
  private void onTimeChanged() {
    mTime.setTimeInMillis(System.currentTimeMillis());
    int hour = mTime.get(java.util.Calendar.HOUR), minute = mTime.get(java.util.Calendar.MINUTE), second = mTime.get(java.util.Calendar.SECOND);
    mSeconds = second;
    mMinutes = minute + second / 60.0f;
    mHour = hour + mMinutes / 60.0f;
    mChanged = true;
    setContentDescription(android.text.format.DateFormat.format(android.text.format.DateFormat.is24HourFormat(getContext()) ? "H:mm" : "h:mm a", mTime));
  }
  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    boolean changed = mChanged;
    mChanged = false;
    int availableWidth = getRight() - getLeft(), availableHeight = getBottom() - getTop();
    int x = availableWidth / 2, y = availableHeight / 2;
    Drawable dial = mDial;
    int w = dial.getIntrinsicWidth(), h = dial.getIntrinsicHeight();
    boolean scaled = false;
    if (availableWidth < w || availableHeight < h) {
      scaled = true;
      float scale = Math.min((float) availableWidth / (float) w, (float) availableHeight / (float) h);
      canvas.save();
      canvas.scale(scale, scale, x, y);
    }
    if (changed) dial.setBounds(x - (w / 2), y - (h / 2), x + (w / 2), y + (h / 2));
    dial.draw(canvas);
    drawHand(canvas, mHourHand, x, y, mHour / 12.0f * 360.0f, changed);
    drawHand(canvas, mMinuteHand, x, y, mMinutes / 60.0f * 360.0f, changed);
    if (mSecondHand != null) drawHand(canvas, mSecondHand, x, y, mSeconds / 60.0f * 360.0f, changed);
    if (scaled) canvas.restore();
  }
  private static void drawHand(Canvas canvas, Drawable hand, int x, int y, float angle, boolean changed) {
    canvas.save();
    canvas.rotate(angle, x, y);
    if (changed) { int w = hand.getIntrinsicWidth(), h = hand.getIntrinsicHeight(); hand.setBounds(x - (w / 2), y - (h / 2), x + (w / 2), y + (h / 2)); }
    hand.draw(canvas);
    canvas.restore();
  }

  /** the default dial (a ring with hour ticks) and hands (pointing up from the center), drawn at the dial's size */
  static final class Part extends Drawable {
    static final int DIAL = 0, HOUR = 1, MINUTE = 2;
    final int kind, size;
    final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Part(int kind, int size) { this.kind = kind; this.size = size; paint.setColor(0xffcccccc); }
    @Override public void draw(Canvas c) {
      android.graphics.Rect b = getBounds();
      float cx = b.exactCenterX(), cy = b.exactCenterY(), r = Math.min(b.width(), b.height()) / 2f;
      if (kind == DIAL) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(r * 0.04f);
        c.drawCircle(cx, cy, r * 0.95f, paint);
        for (int i = 0; i < 12; i++) {
          double a = Math.PI * i / 6;
          float s = (float) Math.sin(a), co = (float) -Math.cos(a), in = i % 3 == 0 ? 0.75f : 0.82f;
          c.drawLine(cx + s * r * in, cy + co * r * in, cx + s * r * 0.9f, cy + co * r * 0.9f, paint);
        }
        paint.setStyle(Paint.Style.FILL);
        return;
      }
      paint.setStyle(Paint.Style.STROKE);
      paint.setStrokeCap(Paint.Cap.ROUND);
      paint.setStrokeWidth(r * (kind == HOUR ? 0.07f : 0.045f));
      c.drawLine(cx, cy + r * 0.08f, cx, cy - r * (kind == HOUR ? 0.5f : 0.75f), paint);
    }
    @Override public void setAlpha(int alpha) { paint.setAlpha(alpha); }
    @Override public void setColorFilter(ColorFilter cf) { paint.setColorFilter(cf); }
    @Override public int getOpacity() { return PixelFormat.TRANSLUCENT; }
    @Override public int getIntrinsicWidth() { return size; }
    @Override public int getIntrinsicHeight() { return size; }
  }
}
