package android.text.format;

import android.content.Context;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/** Date/time formatting by Android's pattern letters (h hh k kk m mm s ss a A d dd M MM MMM MMMM yy yyyy E EEEE). */
public class DateFormat {
  @Deprecated public static final char QUOTE = '\'', AM_PM = 'a', CAPITAL_AM_PM = 'A', DATE = 'd', DAY = 'E', HOUR = 'h', HOUR_OF_DAY = 'k', MINUTE = 'm', MONTH = 'M', SECONDS = 's', TIME_ZONE = 'z', YEAR = 'y';
  public DateFormat() {}
  public static boolean is24HourFormat(Context context) {
    java.text.DateFormat f = java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT, Locale.getDefault());
    String p = f instanceof java.text.SimpleDateFormat ? ((java.text.SimpleDateFormat) f).toPattern() : "HH:mm";
    return p.indexOf('a') < 0;
  }
  public static String getBestDateTimePattern(Locale locale, String skeleton) {
    StringBuilder b = new StringBuilder();
    boolean date = skeleton.indexOf('d') >= 0 || skeleton.indexOf('M') >= 0 || skeleton.indexOf('y') >= 0;
    boolean time = skeleton.indexOf('h') >= 0 || skeleton.indexOf('H') >= 0 || skeleton.indexOf('m') >= 0;
    if (date) b.append(skeleton.contains("MMMM") ? "MMMM d, yyyy" : skeleton.contains("MMM") ? "MMM d, yyyy" : "M/d/yyyy");
    if (time) { if (b.length() > 0) b.append(' '); b.append(skeleton.indexOf('H') >= 0 ? "HH:mm" : "h:mm a"); }
    return b.length() == 0 ? skeleton : b.toString();
  }
  public static java.text.DateFormat getTimeFormat(Context context) {
    return new java.text.SimpleDateFormat(is24HourFormat(context) ? "HH:mm" : "h:mm a", Locale.getDefault());
  }
  public static java.text.DateFormat getDateFormat(Context context) { return java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT, Locale.getDefault()); }
  public static java.text.DateFormat getLongDateFormat(Context context) { return java.text.DateFormat.getDateInstance(java.text.DateFormat.LONG, Locale.getDefault()); }
  public static java.text.DateFormat getMediumDateFormat(Context context) { return java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM, Locale.getDefault()); }
  public static char[] getDateFormatOrder(Context context) { return new char[] {'M', 'd', 'y'}; }
  public static CharSequence format(CharSequence inFormat, long inTimeInMillis) { return format(inFormat, new Date(inTimeInMillis)); }
  public static CharSequence format(CharSequence inFormat, Date inDate) { Calendar c = Calendar.getInstance(); c.setTime(inDate); return format(inFormat, c); }
  public static CharSequence format(CharSequence inFormat, Calendar inDate) {
    StringBuilder s = new StringBuilder();
    String f = inFormat.toString();
    int len = f.length();
    String[] months = java.text.DateFormatSymbols.getInstance(Locale.getDefault()).getMonths();
    String[] shortMonths = java.text.DateFormatSymbols.getInstance(Locale.getDefault()).getShortMonths();
    String[] days = java.text.DateFormatSymbols.getInstance(Locale.getDefault()).getWeekdays();
    String[] shortDays = java.text.DateFormatSymbols.getInstance(Locale.getDefault()).getShortWeekdays();
    for (int i = 0; i < len; ) {
      char c = f.charAt(i);
      int count = 1;
      if (c == '\'') {
        int j = i + 1;
        if (j < len && f.charAt(j) == '\'') { s.append('\''); i += 2; continue; }
        while (j < len && f.charAt(j) != '\'') { s.append(f.charAt(j)); j++; }
        i = j + 1;
        continue;
      }
      while (i + count < len && f.charAt(i + count) == c) count++;
      switch (c) {
        case 'A': case 'a': s.append(inDate.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM"); break;
        case 'd': pad(s, inDate.get(Calendar.DAY_OF_MONTH), count); break;
        case 'c': case 'E': { int d = inDate.get(Calendar.DAY_OF_WEEK); s.append(count < 4 ? shortDays[d] : days[d]); break; }
        case 'h': { int h = inDate.get(Calendar.HOUR); pad(s, h == 0 ? 12 : h, count); break; }
        case 'H': case 'k': pad(s, inDate.get(Calendar.HOUR_OF_DAY), count); break;
        case 'K': pad(s, inDate.get(Calendar.HOUR), count); break;
        case 'L': case 'M': { int m = inDate.get(Calendar.MONTH); if (count >= 4) s.append(months[m]); else if (count == 3) s.append(shortMonths[m]); else pad(s, m + 1, count); break; }
        case 'm': pad(s, inDate.get(Calendar.MINUTE), count); break;
        case 's': pad(s, inDate.get(Calendar.SECOND), count); break;
        case 'y': { int y = inDate.get(Calendar.YEAR); if (count == 2) pad(s, y % 100, 2); else pad(s, y, count); break; }
        case 'z': s.append(inDate.getTimeZone().getDisplayName(inDate.getTimeZone().inDaylightTime(inDate.getTime()), count < 4 ? java.util.TimeZone.SHORT : java.util.TimeZone.LONG)); break;
        default: for (int k = 0; k < count; k++) s.append(c);
      }
      i += count;
    }
    return s.toString();
  }
  private static void pad(StringBuilder s, int v, int n) { String t = String.valueOf(v); for (int i = t.length(); i < n; i++) s.append('0'); s.append(t); }
}
