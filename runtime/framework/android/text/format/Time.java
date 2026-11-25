package android.text.format;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/** The deprecated android.text.format.Time, over java.util.Calendar. */
public class Time {
  public static final String TIMEZONE_UTC = "UTC";
  public static final int EPOCH_JULIAN_DAY = 2440588, MONDAY_BEFORE_JULIAN_EPOCH = EPOCH_JULIAN_DAY - 3;
  public static final int SECOND = 1, MINUTE = 2, HOUR = 3, MONTH_DAY = 4, MONTH = 5, YEAR = 6, WEEK_DAY = 7, YEAR_DAY = 8, WEEK_NUM = 9;
  public static final int SUNDAY = 0, MONDAY = 1, TUESDAY = 2, WEDNESDAY = 3, THURSDAY = 4, FRIDAY = 5, SATURDAY = 6;
  public boolean allDay;
  public int second, minute, hour, monthDay, month, year, weekDay, yearDay, isDst;
  public long gmtoff;
  public String timezone;
  public Time(String timezoneId) { timezone = timezoneId; }
  public Time() { this(TimeZone.getDefault().getID()); }
  public Time(Time other) { set(other); }
  private Calendar cal() {
    Calendar c = new GregorianCalendar(TimeZone.getTimeZone(timezone));
    c.clear();
    c.set(year, month, monthDay, allDay ? 0 : hour, allDay ? 0 : minute, allDay ? 0 : second);
    return c;
  }
  private void from(Calendar c) {
    second = c.get(Calendar.SECOND); minute = c.get(Calendar.MINUTE); hour = c.get(Calendar.HOUR_OF_DAY);
    monthDay = c.get(Calendar.DAY_OF_MONTH); month = c.get(Calendar.MONTH); year = c.get(Calendar.YEAR);
    weekDay = c.get(Calendar.DAY_OF_WEEK) - 1; yearDay = c.get(Calendar.DAY_OF_YEAR) - 1;
    isDst = c.getTimeZone().inDaylightTime(c.getTime()) ? 1 : 0;
    gmtoff = c.getTimeZone().getOffset(c.getTimeInMillis()) / 1000;
  }
  public long normalize(boolean ignoreDst) { Calendar c = cal(); from(c); return c.getTimeInMillis(); }
  public void switchTimezone(String timezone) { long t = toMillis(true); this.timezone = timezone; set(t); }
  public int getActualMaximum(int field) {
    switch (field) {
      case SECOND: return 59; case MINUTE: return 59; case HOUR: return 23;
      case MONTH_DAY: return cal().getActualMaximum(Calendar.DAY_OF_MONTH);
      case MONTH: return 11; case YEAR: return 2037; case WEEK_DAY: return 6;
      case YEAR_DAY: return cal().getActualMaximum(Calendar.DAY_OF_YEAR) - 1;
      case WEEK_NUM: return 53;
    }
    throw new RuntimeException("bad field=" + field);
  }
  public void clear(String timezoneId) { timezone = timezoneId; allDay = false; second = minute = hour = monthDay = month = year = weekDay = yearDay = 0; isDst = -1; gmtoff = 0; }
  public static int compare(Time a, Time b) { return Long.compare(a.toMillis(false), b.toMillis(false)); }
  public String format(String format) {
    Calendar c = cal();
    StringBuilder b = new StringBuilder();
    for (int i = 0; i < format.length(); i++) {
      char ch = format.charAt(i);
      if (ch != '%' || i + 1 >= format.length()) { b.append(ch); continue; }
      char f = format.charAt(++i);
      switch (f) {
        case 'Y': b.append(year); break;
        case 'y': b.append(String.format("%02d", year % 100)); break;
        case 'm': b.append(String.format("%02d", month + 1)); break;
        case 'd': b.append(String.format("%02d", monthDay)); break;
        case 'e': b.append(String.format("%2d", monthDay)); break;
        case 'H': b.append(String.format("%02d", hour)); break;
        case 'I': b.append(String.format("%02d", hour % 12 == 0 ? 12 : hour % 12)); break;
        case 'M': b.append(String.format("%02d", minute)); break;
        case 'S': b.append(String.format("%02d", second)); break;
        case 'p': b.append(hour < 12 ? "AM" : "PM"); break;
        case 'P': b.append(hour < 12 ? "am" : "pm"); break;
        case 'A': b.append(java.text.DateFormatSymbols.getInstance().getWeekdays()[weekDay + 1]); break;
        case 'a': b.append(java.text.DateFormatSymbols.getInstance().getShortWeekdays()[weekDay + 1]); break;
        case 'B': b.append(java.text.DateFormatSymbols.getInstance().getMonths()[month]); break;
        case 'b': case 'h': b.append(java.text.DateFormatSymbols.getInstance().getShortMonths()[month]); break;
        case 'j': b.append(String.format("%03d", yearDay + 1)); break;
        case 'Z': b.append(c.getTimeZone().getDisplayName(isDst > 0, TimeZone.SHORT)); break;
        case 's': b.append(toMillis(false) / 1000); break;
        case 'F': b.append(String.format("%d-%02d-%02d", year, month + 1, monthDay)); break;
        case 'T': b.append(String.format("%02d:%02d:%02d", hour, minute, second)); break;
        case 'R': b.append(String.format("%02d:%02d", hour, minute)); break;
        case 'D': b.append(String.format("%02d/%02d/%02d", month + 1, monthDay, year % 100)); break;
        case 'n': b.append('\n'); break;
        case 't': b.append('\t'); break;
        case '%': b.append('%'); break;
        default: b.append('%').append(f);
      }
    }
    return b.toString();
  }
  @Override public String toString() { return String.format("%04d%02d%02dT%02d%02d%02d%s(%d,%d,%d,%d,%d)", year, month + 1, monthDay, hour, minute, second, timezone, weekDay, yearDay, gmtoff, isDst, toMillis(false) / 1000); }
  public boolean parse(String s) {
    if (s == null) throw new NullPointerException("time string is null");
    try {
      year = Integer.parseInt(s.substring(0, 4)); month = Integer.parseInt(s.substring(4, 6)) - 1; monthDay = Integer.parseInt(s.substring(6, 8));
      if (s.length() >= 15 && s.charAt(8) == 'T') { hour = Integer.parseInt(s.substring(9, 11)); minute = Integer.parseInt(s.substring(11, 13)); second = Integer.parseInt(s.substring(13, 15)); allDay = false; }
      else { hour = minute = second = 0; allDay = true; }
      boolean utc = s.endsWith("Z");
      if (utc) timezone = TIMEZONE_UTC;
      return utc;
    } catch (RuntimeException e) { throw new android.util.TimeFormatException("Parse error at pos=0"); }
  }
  public boolean parse3339(String s) {
    try {
      year = Integer.parseInt(s.substring(0, 4)); month = Integer.parseInt(s.substring(5, 7)) - 1; monthDay = Integer.parseInt(s.substring(8, 10));
      if (s.length() > 10 && s.charAt(10) == 'T') {
        hour = Integer.parseInt(s.substring(11, 13)); minute = Integer.parseInt(s.substring(14, 16)); second = Integer.parseInt(s.substring(17, 19)); allDay = false;
        String tz = s.substring(19).replaceAll("^\\.\\d+", "");
        if (!tz.isEmpty()) {
          int off = 0;
          if (!tz.startsWith("Z")) { int sg = tz.charAt(0) == '-' ? -1 : 1; off = sg * (Integer.parseInt(tz.substring(1, 3)) * 60 + Integer.parseInt(tz.substring(4, 6))); }
          Calendar c = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
          c.clear();
          c.set(year, month, monthDay, hour, minute, second);
          timezone = TIMEZONE_UTC;
          set(c.getTimeInMillis() - off * 60000L);
          return true;
        }
      } else { allDay = true; hour = minute = second = 0; }
      return false;
    } catch (RuntimeException e) { throw new android.util.TimeFormatException("Parse error"); }
  }
  public static String getCurrentTimezone() { return TimeZone.getDefault().getID(); }
  public void setToNow() { set(System.currentTimeMillis()); }
  public long toMillis(boolean ignoreDst) { return cal().getTimeInMillis(); }
  public void set(long millis) { allDay = false; Calendar c = new GregorianCalendar(TimeZone.getTimeZone(timezone)); c.setTimeInMillis(millis); from(c); }
  public String format2445() { return allDay ? String.format("%04d%02d%02d", year, month + 1, monthDay) : String.format("%04d%02d%02dT%02d%02d%02d", year, month + 1, monthDay, hour, minute, second) + (TIMEZONE_UTC.equals(timezone) ? "Z" : ""); }
  public void set(Time that) { timezone = that.timezone; allDay = that.allDay; second = that.second; minute = that.minute; hour = that.hour; monthDay = that.monthDay; month = that.month; year = that.year; weekDay = that.weekDay; yearDay = that.yearDay; isDst = that.isDst; gmtoff = that.gmtoff; }
  public void set(int second, int minute, int hour, int monthDay, int month, int year) { allDay = false; this.second = second; this.minute = minute; this.hour = hour; this.monthDay = monthDay; this.month = month; this.year = year; weekDay = 0; yearDay = 0; isDst = -1; gmtoff = 0; }
  public void set(int monthDay, int month, int year) { allDay = true; second = minute = hour = 0; this.monthDay = monthDay; this.month = month; this.year = year; weekDay = 0; yearDay = 0; isDst = -1; gmtoff = 0; }
  public boolean before(Time that) { return compare(this, that) < 0; }
  public boolean after(Time that) { return compare(this, that) > 0; }
  public int getWeekNumber() { Calendar c = cal(); c.setMinimalDaysInFirstWeek(4); c.setFirstDayOfWeek(Calendar.MONDAY); return c.get(Calendar.WEEK_OF_YEAR); }
  public String format3339(boolean allDay) {
    if (allDay) return String.format("%04d-%02d-%02d", year, month + 1, monthDay);
    if (TIMEZONE_UTC.equals(timezone)) return String.format("%04d-%02d-%02dT%02d:%02d:%02d.000Z", year, month + 1, monthDay, hour, minute, second);
    long off = gmtoff / 60;
    return String.format("%04d-%02d-%02dT%02d:%02d:%02d.000%s%02d:%02d", year, month + 1, monthDay, hour, minute, second, off < 0 ? "-" : "+", Math.abs(off) / 60, Math.abs(off) % 60);
  }
  public static boolean isEpoch(Time time) { return getJulianDay(time.toMillis(true), 0) == EPOCH_JULIAN_DAY; }
  public static int getJulianDay(long millis, long gmtoff) { long offsetMillis = gmtoff * 1000; long julianDay = (millis + offsetMillis) / DateUtils.DAY_IN_MILLIS; return (int) julianDay + EPOCH_JULIAN_DAY; }
  public long setJulianDay(int julianDay) { long millis = (julianDay - EPOCH_JULIAN_DAY) * DateUtils.DAY_IN_MILLIS; set(millis); monthDay += julianDay - getJulianDay(millis, gmtoff); hour = minute = second = 0; return normalize(true); }
  public static int getWeeksSinceEpochFromJulianDay(int julianDay, int firstDayOfWeek) { int diff = THURSDAY - firstDayOfWeek; if (diff < 0) diff += 7; int refDay = EPOCH_JULIAN_DAY - diff; return (julianDay - refDay) / 7; }
  public static int getJulianMondayFromWeeksSinceEpoch(int week) { return MONDAY_BEFORE_JULIAN_EPOCH + week * 7; }
}
