package android.text.method;

import android.text.InputType;

public class TimeKeyListener extends NumberKeyListener {
  public static final char[] CHARACTERS = {'0','1','2','3','4','5','6','7','8','9','a','m','p',':'};
  public TimeKeyListener() {}
  public TimeKeyListener(java.util.Locale locale) {}
  @Override protected char[] getAcceptedChars() { return CHARACTERS; }
  public static TimeKeyListener getInstance() { return new TimeKeyListener(); }
  public static TimeKeyListener getInstance(java.util.Locale locale) { return new TimeKeyListener(); }
  public int getInputType() { return InputType.TYPE_CLASS_DATETIME | InputType.TYPE_DATETIME_VARIATION_TIME; }
}
