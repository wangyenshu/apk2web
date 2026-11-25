package a2w;

/** SQLite's value conversions (as its cursor window does them), for the cursors and statements. */
public final class SqlValues {
  private SqlValues() {}
  public static int typeOf(Object o) {
    if (o == null) return android.database.Cursor.FIELD_TYPE_NULL;
    if (o instanceof byte[]) return android.database.Cursor.FIELD_TYPE_BLOB;
    if (o instanceof Float || o instanceof Double) return android.database.Cursor.FIELD_TYPE_FLOAT;
    if (o instanceof Long || o instanceof Integer || o instanceof Short || o instanceof Byte) return android.database.Cursor.FIELD_TYPE_INTEGER;
    return android.database.Cursor.FIELD_TYPE_STRING;
  }
  public static String text(Object o) {
    if (o == null) return null;
    if (o instanceof byte[]) return new String((byte[]) o);
    if (o instanceof Double || o instanceof Float) {
      double d = ((Number) o).doubleValue();
      return d == Math.rint(d) && !Double.isInfinite(d) && Math.abs(d) < 1e15 ? (long) d + ".0" : String.valueOf(d);
    }
    return o.toString();
  }
  public static long asLong(Object o) {
    if (o == null) return 0;
    if (o instanceof Number) return ((Number) o).longValue();
    if (o instanceof Boolean) return (Boolean) o ? 1 : 0;
    String s = o.toString().trim();
    try { return Long.parseLong(s); } catch (NumberFormatException e) {}
    try { return (long) Double.parseDouble(s); } catch (NumberFormatException e) { return leadingNumber(s) == null ? 0 : leadingNumber(s).longValue(); }
  }
  public static double asDouble(Object o) {
    if (o == null) return 0;
    if (o instanceof Number) return ((Number) o).doubleValue();
    String s = o.toString().trim();
    try { return Double.parseDouble(s); } catch (NumberFormatException e) { Double d = leadingNumber(s); return d == null ? 0 : d; }
  }
  /** SQLite converts "12abc" to 12 */
  private static Double leadingNumber(String s) {
    int i = 0;
    if (i < s.length() && (s.charAt(i) == '-' || s.charAt(i) == '+')) i++;
    while (i < s.length() && (Character.isDigit(s.charAt(i)) || s.charAt(i) == '.')) i++;
    try { return i == 0 ? null : Double.parseDouble(s.substring(0, i)); } catch (NumberFormatException e) { return null; }
  }
}
