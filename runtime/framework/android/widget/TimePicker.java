package android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import java.util.Calendar;

/** Picks a time of day: hour and minute spinners, with AM/PM unless 24-hour. */
public class TimePicker extends FrameLayout {
  public interface OnTimeChangedListener { void onTimeChanged(TimePicker view, int hourOfDay, int minute); }
  private final NumberPicker mHour, mMinute, mAmPm;
  private boolean m24;
  private OnTimeChangedListener mListener;

  public TimePicker(Context c) { this(c, null); }
  public TimePicker(Context c, AttributeSet a) { this(c, a, 0); }
  public TimePicker(Context c, AttributeSet a, int d) { this(c, a, d, 0); }
  public TimePicker(Context c, AttributeSet a, int d, int r) {
    super(c, a, d);
    LinearLayout row = new LinearLayout(c);
    row.setOrientation(LinearLayout.HORIZONTAL);
    row.setGravity(Gravity.CENTER);
    mHour = new NumberPicker(c); mMinute = new NumberPicker(c); mAmPm = new NumberPicker(c);
    mMinute.setMinValue(0); mMinute.setMaxValue(59);
    mMinute.setFormatter(v -> (v < 10 ? "0" : "") + v);
    String[] ampm = new java.text.DateFormatSymbols().getAmPmStrings();
    mAmPm.setMinValue(0); mAmPm.setMaxValue(1); mAmPm.setDisplayedValues(ampm); mAmPm.setWrapSelectorWheel(false);
    NumberPicker.OnValueChangeListener l = (p, o, n) -> { if (mListener != null) mListener.onTimeChanged(this, getHour(), getMinute()); };
    mHour.setOnValueChangedListener(l); mMinute.setOnValueChangedListener(l); mAmPm.setOnValueChangedListener(l);
    TextView colon = new TextView(c);
    colon.setText(":");
    colon.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, 20);
    row.addView(mHour); row.addView(colon); row.addView(mMinute); row.addView(mAmPm);
    addView(row, new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER));
    m24 = android.text.format.DateFormat.is24HourFormat(c);
    Calendar now = Calendar.getInstance();
    applyMode(now.get(Calendar.HOUR_OF_DAY));
    mMinute.setValue(now.get(Calendar.MINUTE));
  }
  private void applyMode(int hourOfDay) {
    if (m24) { mHour.setMinValue(0); mHour.setMaxValue(23); mHour.setFormatter(v -> (v < 10 ? "0" : "") + v); mAmPm.setVisibility(GONE); }
    else { mHour.setMinValue(1); mHour.setMaxValue(12); mHour.setFormatter(null); mAmPm.setVisibility(VISIBLE); }
    setHourInternal(hourOfDay);
  }
  private void setHourInternal(int h) {
    if (m24) mHour.setValue(h);
    else { mAmPm.setValue(h >= 12 ? 1 : 0); int x = h % 12; mHour.setValue(x == 0 ? 12 : x); }
  }
  public void setHour(int hour) { setHourInternal(hour); if (mListener != null) mListener.onTimeChanged(this, getHour(), getMinute()); }
  public int getHour() { if (m24) return mHour.getValue(); int h = mHour.getValue() % 12; return mAmPm.getValue() == 1 ? h + 12 : h; }
  public void setMinute(int minute) { mMinute.setValue(minute); if (mListener != null) mListener.onTimeChanged(this, getHour(), getMinute()); }
  public int getMinute() { return mMinute.getValue(); }
  @Deprecated public void setCurrentHour(Integer h) { setHour(h); }
  @Deprecated public Integer getCurrentHour() { return getHour(); }
  @Deprecated public void setCurrentMinute(Integer m) { setMinute(m); }
  @Deprecated public Integer getCurrentMinute() { return getMinute(); }
  public void setIs24HourView(Boolean is24) { int h = getHour(); m24 = is24 != null && is24; applyMode(h); }
  public boolean is24HourView() { return m24; }
  public void setOnTimeChangedListener(OnTimeChangedListener l) { mListener = l; }
  public boolean validateInput() { return true; }
  @Override public void setEnabled(boolean e) { super.setEnabled(e); mHour.setEnabled(e); mMinute.setEnabled(e); mAmPm.setEnabled(e); }
}
