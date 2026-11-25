package android.text.format;

import android.content.Context;

public final class Formatter {
  public Formatter() {}
  public static String formatFileSize(Context context, long sizeBytes) { return format(sizeBytes, false); }
  public static String formatShortFileSize(Context context, long sizeBytes) { return format(sizeBytes, true); }
  private static String format(long sizeBytes, boolean shorter) {
    float result = Math.abs(sizeBytes);
    String suffix = "B";
    String[] units = {"kB", "MB", "GB", "TB", "PB"};
    for (int i = 0; i < units.length && result > 900; i++) { suffix = units[i]; result /= 1000; }
    if (sizeBytes < 0) result = -result;
    String value;
    if (result < 1) value = String.format(java.util.Locale.getDefault(), "%.2f", result);
    else if (result < 10) value = shorter ? String.format(java.util.Locale.getDefault(), "%.1f", result) : String.format(java.util.Locale.getDefault(), "%.2f", result);
    else if (result < 100) value = shorter ? String.format(java.util.Locale.getDefault(), "%.0f", result) : String.format(java.util.Locale.getDefault(), "%.2f", result);
    else value = String.format(java.util.Locale.getDefault(), "%.0f", result);
    return value + " " + suffix;
  }
  @Deprecated public static String formatIpAddress(int ipv4Address) { return (ipv4Address & 0xff) + "." + ((ipv4Address >> 8) & 0xff) + "." + ((ipv4Address >> 16) & 0xff) + "." + ((ipv4Address >> 24) & 0xff); }
}
