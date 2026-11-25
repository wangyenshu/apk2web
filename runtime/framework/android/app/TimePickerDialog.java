package android.app;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.TimePicker;

/** A dialog with a TimePicker and OK/Cancel. */
public class TimePickerDialog extends AlertDialog implements DialogInterface.OnClickListener, TimePicker.OnTimeChangedListener {
  public interface OnTimeSetListener { void onTimeSet(TimePicker view, int hourOfDay, int minute); }
  private final TimePicker mPicker;
  private final OnTimeSetListener mListener;

  public TimePickerDialog(Context context, OnTimeSetListener l, int hourOfDay, int minute, boolean is24HourView) { this(context, 0, l, hourOfDay, minute, is24HourView); }
  public TimePickerDialog(Context context, int themeResId, OnTimeSetListener l, int hourOfDay, int minute, boolean is24HourView) {
    super(context, themeResId);
    mListener = l;
    mPicker = new TimePicker(getContext());
    mPicker.setIs24HourView(is24HourView);
    mPicker.setHour(hourOfDay);
    mPicker.setMinute(minute);
    mPicker.setOnTimeChangedListener(this);
    setView(mPicker);
    setButton(BUTTON_POSITIVE, context.getText(android.R.string.ok), this);
    setButton(BUTTON_NEGATIVE, context.getText(android.R.string.cancel), this);
  }
  @Override public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {}
  @Override public void onClick(DialogInterface dialog, int which) {
    if (which == BUTTON_POSITIVE && mListener != null) mListener.onTimeSet(mPicker, mPicker.getHour(), mPicker.getMinute());
    else if (which == BUTTON_NEGATIVE) cancel();
  }
  public void updateTime(int hourOfDay, int minute) { mPicker.setHour(hourOfDay); mPicker.setMinute(minute); }
}
