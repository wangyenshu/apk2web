package android.text.method;

import android.text.Editable;
import android.text.Selection;
import android.view.KeyEvent;
import android.view.View;

public abstract class BaseKeyListener extends MetaKeyKeyListener implements KeyListener {
  public BaseKeyListener() {}
  public boolean backspace(View view, Editable content, int keyCode, KeyEvent event) { return delete(content, true); }
  public boolean forwardDelete(View view, Editable content, int keyCode, KeyEvent event) { return delete(content, false); }
  private static boolean delete(Editable content, boolean back) {
    int a = Selection.getSelectionStart(content), b = Selection.getSelectionEnd(content);
    if (a < 0) a = b = content.length();
    int st = Math.min(a, b), en = Math.max(a, b);
    if (st == en) { if (back) { if (st == 0) return false; st--; } else { if (en >= content.length()) return false; en++; } }
    content.delete(st, en);
    return true;
  }
  @Override public boolean onKeyDown(View view, Editable content, int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_DEL) return backspace(view, content, keyCode, event);
    if (keyCode == KeyEvent.KEYCODE_FORWARD_DEL) return forwardDelete(view, content, keyCode, event);
    return super.onKeyDown(view, content, keyCode, event);
  }
  public boolean onKeyOther(View view, Editable content, KeyEvent event) {
    String s = event.getCharacters();
    if (s == null) return false;
    int a = Selection.getSelectionStart(content), b = Selection.getSelectionEnd(content);
    if (a < 0) a = b = content.length();
    content.replace(Math.min(a, b), Math.max(a, b), s);
    return true;
  }
}
