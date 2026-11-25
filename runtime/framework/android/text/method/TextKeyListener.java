package android.text.method;

import android.text.InputType;

public class TextKeyListener extends BaseKeyListener {
  public enum Capitalize { NONE, SENTENCES, WORDS, CHARACTERS }
  private final Capitalize mAutoCap;
  private final boolean mAutoText;
  public TextKeyListener(Capitalize cap, boolean autotext) { mAutoCap = cap; mAutoText = autotext; }
  public static TextKeyListener getInstance(boolean autotext, Capitalize cap) { return new TextKeyListener(cap, autotext); }
  public static TextKeyListener getInstance() { return getInstance(false, Capitalize.NONE); }
  public static boolean shouldCap(Capitalize cap, CharSequence cs, int off) { return cap == Capitalize.CHARACTERS || (cap != Capitalize.NONE && (off == 0 || Character.isWhitespace(cs.charAt(off - 1)))); }
  public static void clear(android.text.Editable e) { e.clear(); }
  public int getInputType() {
    int t = InputType.TYPE_CLASS_TEXT;
    if (mAutoCap == Capitalize.SENTENCES) t |= InputType.TYPE_TEXT_FLAG_CAP_SENTENCES;
    else if (mAutoCap == Capitalize.WORDS) t |= InputType.TYPE_TEXT_FLAG_CAP_WORDS;
    else if (mAutoCap == Capitalize.CHARACTERS) t |= InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS;
    if (mAutoText) t |= InputType.TYPE_TEXT_FLAG_AUTO_CORRECT;
    return t;
  }
  public boolean onKeyUp(android.view.View view, android.text.Editable content, int keyCode, android.view.KeyEvent event) { return false; }
}
