package android.text.method;

import android.text.InputType;

public class DialerKeyListener extends NumberKeyListener {
  public static final char[] CHARACTERS = {'0','1','2','3','4','5','6','7','8','9','#','*','+','-','(',')',',','/','N','.',' ',';'};
  public DialerKeyListener() {}
  public DialerKeyListener(java.util.Locale locale) {}
  @Override protected char[] getAcceptedChars() { return CHARACTERS; }
  public static DialerKeyListener getInstance() { return new DialerKeyListener(); }
  public static DialerKeyListener getInstance(java.util.Locale locale) { return new DialerKeyListener(); }
  public int getInputType() { return InputType.TYPE_CLASS_PHONE; }
}
