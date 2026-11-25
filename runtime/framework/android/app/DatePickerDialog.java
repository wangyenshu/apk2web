package android.app;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.DatePicker;
import java.util.Calendar;

/** A dialog with a DatePicker and OK/Cancel. */
public class DatePickerDialog extends AlertDialog implements DialogInterface.OnClickListener, DatePicker.OnDateChangedListener {
  public interface OnDateSetListener { void onDateSet(DatePicker view, int year, int month, int dayOfMonth); }
  private final DatePicker mPicker;
  private OnDateSetListener mListener;

  public DatePickerDialog(Context context) { this(context, 0, null, -1, -1, -1); }
  public DatePickerDialog(Context context, int themeResId) { this(context, themeResId, null, -1, -1, -1); }
  public DatePickerDialog(Context context, OnDateSetListener l, int year, int month, int dayOfMonth) { this(context, 0, l, year, month, dayOfMonth); }
  public DatePickerDialog(Context context, int themeResId, OnDateSetListener l, int year, int month, int dayOfMonth) {
    super(context, themeResId);
    mListener = l;
    mPicker = new DatePicker(getContext());
    if (year < 0) { Calendar c = Calendar.getInstance(); year = c.get(Calendar.YEAR); month = c.get(Calendar.MONTH); dayOfMonth = c.get(Calendar.DAY_OF_MONTH); }
    mPicker.init(year, month, dayOfMonth, this);
    setView(mPicker);
    setButton(BUTTON_POSITIVE, context.getText(android.R.string.ok), this);
    setButton(BUTTON_NEGATIVE, context.getText(android.R.string.cancel), this);
  }
  @Override public void onDateChanged(DatePicker view, int year, int month, int dayOfMonth) {}
  @Override public void onClick(DialogInterface dialog, int which) {
    if (which == BUTTON_POSITIVE && mListener != null) { mPicker.clearFocus(); mListener.onDateSet(mPicker, mPicker.getYear(), mPicker.getMonth(), mPicker.getDayOfMonth()); }
    else if (which == BUTTON_NEGATIVE) cancel();
  }
  public void setOnDateSetListener(OnDateSetListener l) { mListener = l; }
  public DatePicker getDatePicker() { return mPicker; }
  public void updateDate(int year, int month, int dayOfMonth) { mPicker.updateDate(year, month, dayOfMonth); }
}
