package android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import java.util.Calendar;

/** Picks a date: year, month and day spinners (the platform's "spinner" mode). */
public class DatePicker extends FrameLayout {
  public interface OnDateChangedListener { void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth); }
  private final NumberPicker mDay, mMonth, mYear;
  private OnDateChangedListener mListener;
  private long mMinDate = -2208988800000L, mMaxDate = 4102444800000L;   // 1900..2100
  private int mFirstDayOfWeek = Calendar.SUNDAY;
  private final CalendarView mCalendar = null;

  public DatePicker(Context c) { this(c, null); }
  public DatePicker(Context c, AttributeSet a) { this(c, a, 0); }
  public DatePicker(Context c, AttributeSet a, int d) { this(c, a, d, 0); }
  public DatePicker(Context c, AttributeSet a, int d, int r) {
    super(c, a, d);
    LinearLayout row = new LinearLayout(c);
    row.setOrientation(LinearLayout.HORIZONTAL);
    row.setGravity(Gravity.CENTER);
    mMonth = new NumberPicker(c); mDay = new NumberPicker(c); mYear = new NumberPicker(c);
    String[] months = new java.text.DateFormatSymbols().getShortMonths();
    String[] m12 = new String[12];
    for (int i = 0; i < 12; i++) m12[i] = months[i];
    mMonth.setMinValue(0); mMonth.setMaxValue(11); mMonth.setDisplayedValues(m12);
    mYear.setMinValue(1900); mYear.setMaxValue(2100); mYear.setWrapSelectorWheel(false);
    mDay.setMinValue(1); mDay.setMaxValue(31);
    NumberPicker.OnValueChangeListener l = (p, o, n) -> changed();
    mMonth.setOnValueChangedListener(l); mDay.setOnValueChangedListener(l); mYear.setOnValueChangedListener(l);
    row.addView(mMonth); row.addView(mDay); row.addView(mYear);
    addView(row, new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER));
    Calendar now = Calendar.getInstance();
    init(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH), null);
  }
  private void fixDays() {
    Calendar c = Calendar.getInstance();
    c.clear();
    c.set(mYear.getValue(), mMonth.getValue(), 1);
    int max = c.getActualMaximum(Calendar.DAY_OF_MONTH);
    if (mDay.getValue() > max) mDay.setValue(max);
    mDay.setMaxValue(max);
  }
  private void changed() {
    fixDays();
    clamp();
    if (mListener != null) mListener.onDateChanged(this, getYear(), getMonth(), getDayOfMonth());
  }
  private void clamp() {
    Calendar c = Calendar.getInstance();
    c.clear();
    c.set(getYear(), getMonth(), getDayOfMonth());
    long t = c.getTimeInMillis();
    if (t < mMinDate || t > mMaxDate) {
      c.setTimeInMillis(t < mMinDate ? mMinDate : mMaxDate);
      set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
    }
  }
  private void set(int y, int m, int d) { mYear.setValue(y); mMonth.setValue(m); fixDays(); mDay.setValue(d); }
  public void init(int year, int monthOfYear, int dayOfMonth, OnDateChangedListener l) { set(year, monthOfYear, dayOfMonth); mListener = l; }
  public void updateDate(int year, int month, int dayOfMonth) {
    if (year == getYear() && month == getMonth() && dayOfMonth == getDayOfMonth()) return;
    set(year, month, dayOfMonth);
    if (mListener != null) mListener.onDateChanged(this, year, month, dayOfMonth);
  }
  public void setOnDateChangedListener(OnDateChangedListener l) { mListener = l; }
  public int getYear() { return mYear.getValue(); }
  public int getMonth() { return mMonth.getValue(); }
  public int getDayOfMonth() { return mDay.getValue(); }
  public long getMinDate() { return mMinDate; }
  public void setMinDate(long t) { mMinDate = t; Calendar c = Calendar.getInstance(); c.setTimeInMillis(t); mYear.setMinValue(c.get(Calendar.YEAR)); clamp(); }
  public long getMaxDate() { return mMaxDate; }
  public void setMaxDate(long t) { mMaxDate = t; Calendar c = Calendar.getInstance(); c.setTimeInMillis(t); mYear.setMaxValue(c.get(Calendar.YEAR)); clamp(); }
  public void setFirstDayOfWeek(int d) { mFirstDayOfWeek = d; }
  public int getFirstDayOfWeek() { return mFirstDayOfWeek; }
  public boolean getCalendarViewShown() { return false; }
  public void setCalendarViewShown(boolean shown) {}
  public boolean getSpinnersShown() { return true; }
  public void setSpinnersShown(boolean shown) {}
  public CalendarView getCalendarView() { return mCalendar; }
  @Override public void setEnabled(boolean e) { super.setEnabled(e); mDay.setEnabled(e); mMonth.setEnabled(e); mYear.setEnabled(e); }
}
