package android.widget;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import a2w.Attrs;

/**
 * The current date and time as text, in format12Hour or format24Hour (by the device's 24-hour setting), in a time zone;
 * updated every minute (every second when the format shows seconds) while the view is attached and visible.
 */
public class TextClock extends TextView {
  public static final CharSequence DEFAULT_FORMAT_12_HOUR = "h:mm a";
  public static final CharSequence DEFAULT_FORMAT_24_HOUR = "H:mm";
  private CharSequence mFormat12 = DEFAULT_FORMAT_12_HOUR, mFormat24 = DEFAULT_FORMAT_24_HOUR, mFormat;
  private String mTimeZone;
  private java.util.Calendar mTime;
  private boolean mHasSeconds, mRegistered, mVisible = true;
  private final Runnable mTicker = new Runnable() {
    public void run() {
      removeCallbacks(this);
      if (!mRegistered || !mVisible) return;
      onTimeChanged();
      long now = System.currentTimeMillis(), period = mHasSeconds ? 1000 : 60000;
      postDelayed(this, period - now % period);
    }
  };

  public TextClock(Context context) { this(context, null); }
  public TextClock(Context context, AttributeSet attrs) { this(context, attrs, 0); }
  public TextClock(Context context, AttributeSet attrs, int defStyleAttr) { this(context, attrs, defStyleAttr, 0); }
  public TextClock(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr);
    if (mCtorAttrs instanceof Attrs) {
      Attrs a = (Attrs) mCtorAttrs;
      String v;
      if ((v = a.a("format12Hour")) != null) mFormat12 = text(v);
      if ((v = a.a("format24Hour")) != null) mFormat24 = text(v);
      if ((v = a.a("timeZone")) != null) mTimeZone = v;
    }
    createTime(mTimeZone);
    chooseFormat();
  }
  private void createTime(String timeZone) {
    mTime = timeZone != null ? java.util.Calendar.getInstance(java.util.TimeZone.getTimeZone(timeZone)) : java.util.Calendar.getInstance();
  }
  public CharSequence getFormat12Hour() { return mFormat12; }
  public void setFormat12Hour(CharSequence format) { mFormat12 = format; chooseFormat(); onTimeChanged(); }
  public CharSequence getFormat24Hour() { return mFormat24; }
  public void setFormat24Hour(CharSequence format) { mFormat24 = format; chooseFormat(); onTimeChanged(); }
  public boolean is24HourModeEnabled() { return DateFormat.is24HourFormat(getContext()); }
  public String getTimeZone() { return mTimeZone; }
  public void setTimeZone(String timeZone) { mTimeZone = timeZone; createTime(timeZone); onTimeChanged(); }
  /** the format in use: the one for the 24-hour setting, else the other, else the default */
  private void chooseFormat() {
    boolean format24 = is24HourModeEnabled();
    if (format24) mFormat = abc(mFormat24, mFormat12, DEFAULT_FORMAT_24_HOUR);
    else mFormat = abc(mFormat12, mFormat24, DEFAULT_FORMAT_12_HOUR);
    boolean had = mHasSeconds;
    mHasSeconds = hasSeconds(mFormat);
    if (mRegistered && had != mHasSeconds) mTicker.run();
  }
  private static CharSequence abc(CharSequence a, CharSequence b, CharSequence c) { return a == null ? (b == null ? c : b) : a; }
  /** whether a format shows seconds ('s' outside quotes) */
  static boolean hasSeconds(CharSequence f) {
    if (f == null) return false;
    boolean quoted = false;
    for (int i = 0; i < f.length(); i++) {
      char c = f.charAt(i);
      if (c == '\'') quoted = !quoted;
      else if (!quoted && c == 's') return true;
    }
    return false;
  }
  public void refreshTime() { onTimeChanged(); invalidate(); }
  private void onTimeChanged() {
    mTime.setTimeInMillis(System.currentTimeMillis());
    setText(DateFormat.format(mFormat, mTime));
  }
  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    if (!mRegistered) { mRegistered = true; createTime(mTimeZone); chooseFormat(); mTicker.run(); onTimeChanged(); }
  }
  @Override public void onVisibilityAggregated(boolean isVisible) {
    super.onVisibilityAggregated(isVisible);
    if (mVisible != isVisible) { mVisible = isVisible; if (isVisible) mTicker.run(); else removeCallbacks(mTicker); }
  }
  @Override protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    if (mRegistered) { mRegistered = false; removeCallbacks(mTicker); }
  }
  @Override public CharSequence getAccessibilityClassName() { return TextClock.class.getName(); }
}
