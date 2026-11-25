package android.text.format;

import android.content.Context;
import java.util.Calendar;
import java.util.Locale;

public class DateUtils {
  public static final long SECOND_IN_MILLIS = 1000, MINUTE_IN_MILLIS = SECOND_IN_MILLIS * 60, HOUR_IN_MILLIS = MINUTE_IN_MILLIS * 60, DAY_IN_MILLIS = HOUR_IN_MILLIS * 24,
      WEEK_IN_MILLIS = DAY_IN_MILLIS * 7, YEAR_IN_MILLIS = WEEK_IN_MILLIS * 52;
  public static final int FORMAT_SHOW_TIME = 0x00001, FORMAT_SHOW_WEEKDAY = 0x00002, FORMAT_SHOW_YEAR = 0x00004, FORMAT_NO_YEAR = 0x00008, FORMAT_SHOW_DATE = 0x00010,
      FORMAT_NO_MONTH_DAY = 0x00020, FORMAT_12HOUR = 0x00040, FORMAT_24HOUR = 0x00080, FORMAT_CAP_AMPM = 0x00100, FORMAT_NO_NOON = 0x00200, FORMAT_CAP_NOON = 0x00400,
      FORMAT_NO_MIDNIGHT = 0x00800, FORMAT_CAP_MIDNIGHT = 0x01000, FORMAT_UTC = 0x02000, FORMAT_ABBREV_TIME = 0x04000, FORMAT_ABBREV_WEEKDAY = 0x08000,
      FORMAT_ABBREV_MONTH = 0x10000, FORMAT_NUMERIC_DATE = 0x20000, FORMAT_ABBREV_RELATIVE = 0x40000, FORMAT_ABBREV_ALL = 0x80000;
  public DateUtils() {}
  public static boolean isToday(long when) {
    Calendar a = Calendar.getInstance(), b = Calendar.getInstance();
    b.setTimeInMillis(when);
    return a.get(Calendar.YEAR) == b.get(Calendar.YEAR) && a.get(Calendar.DAY_OF_YEAR) == b.get(Calendar.DAY_OF_YEAR);
  }
  public static String formatElapsedTime(long elapsedSeconds) { return formatElapsedTime(null, elapsedSeconds); }
  public static String formatElapsedTime(StringBuilder recycle, long elapsedSeconds) {
    long hours = elapsedSeconds / 3600, minutes = (elapsedSeconds % 3600) / 60, seconds = elapsedSeconds % 60;
    String r = hours > 0 ? String.format(Locale.US, "%d:%02d:%02d", hours, minutes, seconds) : String.format(Locale.US, "%02d:%02d", minutes, seconds);
    if (recycle != null) { recycle.setLength(0); recycle.append(r); }
    return r;
  }
  public static CharSequence getRelativeTimeSpanString(long startTime) { return getRelativeTimeSpanString(startTime, System.currentTimeMillis(), MINUTE_IN_MILLIS); }
  public static CharSequence getRelativeTimeSpanString(long time, long now, long minResolution) { return getRelativeTimeSpanString(time, now, minResolution, FORMAT_ABBREV_MONTH); }
  public static CharSequence getRelativeTimeSpanString(long time, long now, long minResolution, int flags) {
    long duration = Math.abs(now - time);
    boolean past = now >= time;
    long count; String unit;
    if (duration < MINUTE_IN_MILLIS && minResolution < MINUTE_IN_MILLIS) { count = duration / SECOND_IN_MILLIS; unit = count == 1 ? "second" : "seconds"; }
    else if (duration < HOUR_IN_MILLIS && minResolution < HOUR_IN_MILLIS) { count = duration / MINUTE_IN_MILLIS; unit = count == 1 ? "minute" : "minutes"; }
    else if (duration < DAY_IN_MILLIS && minResolution < DAY_IN_MILLIS) { count = duration / HOUR_IN_MILLIS; unit = count == 1 ? "hour" : "hours"; }
    else if (duration < WEEK_IN_MILLIS && minResolution < WEEK_IN_MILLIS) {
      count = duration / DAY_IN_MILLIS;
      if (count == 1) return past ? "Yesterday" : "Tomorrow";
      unit = "days";
    } else return formatDateRange(null, time, time, flags);
    return past ? count + " " + unit + " ago" : "In " + count + " " + unit;
  }
  public static CharSequence getRelativeTimeSpanString(Context c, long millis) { return getRelativeTimeSpanString(millis); }
  public static CharSequence getRelativeTimeSpanString(Context c, long millis, boolean withPreposition) { return getRelativeTimeSpanString(millis); }
  public static CharSequence getRelativeDateTimeString(Context c, long time, long minResolution, long transitionResolution, int flags) { return getRelativeTimeSpanString(time, System.currentTimeMillis(), minResolution, flags); }
  public static String formatDateTime(Context context, long millis, int flags) { return formatDateRange(context, millis, millis, flags); }
  public static String formatDateRange(Context context, long startMillis, long endMillis, int flags) {
    StringBuilder pat = new StringBuilder();
    boolean showTime = (flags & FORMAT_SHOW_TIME) != 0, showDate = (flags & FORMAT_SHOW_DATE) != 0 || !showTime;
    if ((flags & FORMAT_SHOW_WEEKDAY) != 0) pat.append((flags & (FORMAT_ABBREV_WEEKDAY | FORMAT_ABBREV_ALL)) != 0 ? "EEE, " : "EEEE, ");
    if (showDate) {
      if ((flags & FORMAT_NUMERIC_DATE) != 0) pat.append((flags & FORMAT_NO_YEAR) != 0 ? "M/d" : "M/d/yyyy");
      else {
        pat.append((flags & (FORMAT_ABBREV_MONTH | FORMAT_ABBREV_ALL)) != 0 ? "MMM" : "MMMM");
        if ((flags & FORMAT_NO_MONTH_DAY) == 0) pat.append(" d");
        if ((flags & FORMAT_NO_YEAR) == 0 && ((flags & FORMAT_SHOW_YEAR) != 0 || !sameYear(startMillis))) pat.append(", yyyy");
      }
    }
    if (showTime) { if (pat.length() > 0) pat.append(", "); pat.append((flags & FORMAT_24HOUR) != 0 ? "HH:mm" : "h:mm a"); }
    java.text.SimpleDateFormat f = new java.text.SimpleDateFormat(pat.toString(), Locale.getDefault());
    if ((flags & FORMAT_UTC) != 0) f.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
    String a = f.format(new java.util.Date(startMillis));
    return startMillis == endMillis ? a : a + " – " + f.format(new java.util.Date(endMillis));
  }
  public static java.util.Formatter formatDateRange(Context context, java.util.Formatter formatter, long startMillis, long endMillis, int flags) { formatter.format("%s", formatDateRange(context, startMillis, endMillis, flags)); return formatter; }
  public static java.util.Formatter formatDateRange(Context context, java.util.Formatter formatter, long startMillis, long endMillis, int flags, String timeZone) { return formatDateRange(context, formatter, startMillis, endMillis, flags); }
  private static boolean sameYear(long t) { Calendar a = Calendar.getInstance(), b = Calendar.getInstance(); b.setTimeInMillis(t); return a.get(Calendar.YEAR) == b.get(Calendar.YEAR); }
  public static String getDayOfWeekString(int dayOfWeek, int abbrev) { String[] d = java.text.DateFormatSymbols.getInstance().getWeekdays(); return abbrev >= 20 ? java.text.DateFormatSymbols.getInstance().getShortWeekdays()[dayOfWeek] : d[dayOfWeek]; }
  public static String getMonthString(int month, int abbrev) { return abbrev >= 20 ? java.text.DateFormatSymbols.getInstance().getShortMonths()[month] : java.text.DateFormatSymbols.getInstance().getMonths()[month]; }
  public static String getAMPMString(int ampm) { return ampm == Calendar.AM ? "AM" : "PM"; }
}
