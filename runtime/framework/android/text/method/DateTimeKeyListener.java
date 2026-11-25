package android.text.method;

import android.text.InputType;

public class DateTimeKeyListener extends NumberKeyListener {
  public static final char[] CHARACTERS = {'0','1','2','3','4','5','6','7','8','9','a','m','p',':','/','-',' '};
  public DateTimeKeyListener() {}
  public DateTimeKeyListener(java.util.Locale locale) {}
  @Override protected char[] getAcceptedChars() { return CHARACTERS; }
  public static DateTimeKeyListener getInstance() { return new DateTimeKeyListener(); }
  public static DateTimeKeyListener getInstance(java.util.Locale locale) { return new DateTimeKeyListener(); }
  public int getInputType() { return InputType.TYPE_CLASS_DATETIME | InputType.TYPE_DATETIME_VARIATION_NORMAL; }
}
