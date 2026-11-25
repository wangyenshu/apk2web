package android.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** android.util.Patterns: regular expressions for common text (simplified from the platform's, same intent). */
public class Patterns {
  private Patterns() {}
  public static final String TOP_LEVEL_DOMAIN_STR = "[a-zA-Z]{2,63}";
  public static final Pattern TOP_LEVEL_DOMAIN = Pattern.compile(TOP_LEVEL_DOMAIN_STR);
  public static final Pattern IP_ADDRESS = Pattern.compile(
      "((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9]))");
  static final String LABEL = "[a-zA-Z0-9\\u00A0-\\uFFFE](?:[a-zA-Z0-9\\u00A0-\\uFFFE_\\-]{0,61}[a-zA-Z0-9\\u00A0-\\uFFFE]){0,1}";
  static final String HOST_NAME = "(" + LABEL + "\\.)+" + TOP_LEVEL_DOMAIN_STR;
  public static final Pattern DOMAIN_NAME = Pattern.compile("(" + HOST_NAME + "|" + IP_ADDRESS + ")");
  public static final Pattern WEB_URL = Pattern.compile(
      "((?:(http|https|Http|Https|rtsp|Rtsp):\\/\\/(?:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)\\,;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,64}(?:\\:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)\\,;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,25})?\\@)?)?"
      + "(?:" + DOMAIN_NAME.pattern() + ")(?:\\:\\d{1,5})?)(\\/(?:(?:[a-zA-Z0-9\\u00A0-\\uFFFE;\\/\\?\\:\\@\\&\\=\\#\\~\\-\\.\\+\\!\\*\\'\\(\\)\\,\\_])|(?:\\%[a-fA-F0-9]{2}))*)?(?:\\b|$)");
  public static final Pattern AUTOLINK_WEB_URL = WEB_URL;
  public static final Pattern EMAIL_ADDRESS = Pattern.compile(
      "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+");
  public static final Pattern AUTOLINK_EMAIL_ADDRESS = EMAIL_ADDRESS;
  public static final Pattern PHONE = Pattern.compile("(\\+[0-9]+[\\- \\.]*)?(\\([0-9]+\\)[\\- \\.]*)?([0-9][0-9\\- \\.]+[0-9])");
  public static final String concatGroups(Matcher matcher) {
    StringBuilder b = new StringBuilder();
    for (int i = 1; i <= matcher.groupCount(); i++) { String s = matcher.group(i); if (s != null) b.append(s); }
    return b.toString();
  }
  public static final String digitsAndPlusOnly(Matcher matcher) {
    StringBuilder b = new StringBuilder();
    String m = matcher.group();
    for (int i = 0; i < m.length(); i++) { char c = m.charAt(i); if (c == '+' || Character.isDigit(c)) b.append(c); }
    return b.toString();
  }
}
