package android.widget;

import android.content.Context;
import android.util.AttributeSet;
import java.util.Calendar;

/** A calendar: here a DatePicker's spinners holding the selected date. */
public class CalendarView extends FrameLayout {
  public interface OnDateChangeListener { void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth); }
  private final DatePicker mPicker;
  private OnDateChangeListener mListener;
  public CalendarView(Context c) { this(c, null); }
  public CalendarView(Context c, AttributeSet a) { this(c, a, 0); }
  public CalendarView(Context c, AttributeSet a, int d) { this(c, a, d, 0); }
  public CalendarView(Context c, AttributeSet a, int d, int r) {
    super(c, a, d);
    mPicker = new DatePicker(c);
    mPicker.setOnDateChangedListener((v, y, m, day) -> { if (mListener != null) mListener.onSelectedDayChange(this, y, m, day); });
    addView(mPicker);
  }
  public void setOnDateChangeListener(OnDateChangeListener l) { mListener = l; }
  public long getDate() { Calendar c = Calendar.getInstance(); c.clear(); c.set(mPicker.getYear(), mPicker.getMonth(), mPicker.getDayOfMonth()); return c.getTimeInMillis(); }
  public void setDate(long date) { setDate(date, false, false); }
  public void setDate(long date, boolean animate, boolean center) { Calendar c = Calendar.getInstance(); c.setTimeInMillis(date); mPicker.updateDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)); }
  public long getMinDate() { return mPicker.getMinDate(); }
  public void setMinDate(long t) { mPicker.setMinDate(t); }
  public long getMaxDate() { return mPicker.getMaxDate(); }
  public void setMaxDate(long t) { mPicker.setMaxDate(t); }
  public void setFirstDayOfWeek(int d) { mPicker.setFirstDayOfWeek(d); }
  public int getFirstDayOfWeek() { return mPicker.getFirstDayOfWeek(); }
  public void setShowWeekNumber(boolean show) {}
  public boolean getShowWeekNumber() { return false; }
}
