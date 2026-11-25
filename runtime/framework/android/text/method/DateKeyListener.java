package android.text.method;

import android.text.InputType;

public class DateKeyListener extends NumberKeyListener {
  public static final char[] CHARACTERS = {'0','1','2','3','4','5','6','7','8','9','/','-','.'};
  public DateKeyListener() {}
  public DateKeyListener(java.util.Locale locale) {}
  @Override protected char[] getAcceptedChars() { return CHARACTERS; }
  public static DateKeyListener getInstance() { return new DateKeyListener(); }
  public static DateKeyListener getInstance(java.util.Locale locale) { return new DateKeyListener(); }
  public int getInputType() { return InputType.TYPE_CLASS_DATETIME | InputType.TYPE_DATETIME_VARIATION_DATE; }
}
