package android.text.method;

import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.KeyEvent;
import android.view.View;

/** Accepts only the characters getAcceptedChars() lists (as a key listener and as an input filter). */
public abstract class NumberKeyListener extends BaseKeyListener implements InputFilter {
  protected abstract char[] getAcceptedChars();
  protected int lookup(KeyEvent event, Editable content) { return event.getMatch(getAcceptedChars()); }
  public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
    char[] accept = getAcceptedChars();
    int i;
    for (i = start; i < end; i++) if (!ok(accept, source.charAt(i))) break;
    if (i == end) return null;
    if (end - start == 1) return "";
    SpannableStringBuilder filtered = new SpannableStringBuilder(source, start, end);
    i -= start;
    end -= start;
    for (int j = end - 1; j >= i; j--) if (!ok(accept, source.charAt(j + start))) filtered.delete(j, j + 1);
    return filtered;
  }
  protected static boolean ok(char[] accept, char c) {
    for (int i = accept.length - 1; i >= 0; i--) if (accept[i] == c) return true;
    return false;
  }
  @Override public boolean onKeyDown(View view, Editable content, int keyCode, KeyEvent event) {
    int selStart = Math.min(Selection.getSelectionStart(content), Selection.getSelectionEnd(content));
    int selEnd = Math.max(Selection.getSelectionStart(content), Selection.getSelectionEnd(content));
    if (selStart < 0 || selEnd < 0) { selStart = selEnd = content.length(); Selection.setSelection(content, selStart); }
    int i = event != null ? lookup(event, content) : 0;
    if (i != 0 && ok(getAcceptedChars(), (char) i)) {
      content.replace(selStart, selEnd, String.valueOf((char) i));
      return true;
    }
    return super.onKeyDown(view, content, keyCode, event);
  }
  public boolean onKeyUp(View view, Editable content, int keyCode, KeyEvent event) { return false; }
}
