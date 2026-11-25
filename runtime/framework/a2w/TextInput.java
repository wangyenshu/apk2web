package a2w;

import android.view.View;
import android.widget.TextView;
import org.teavm.jso.JSBody;

/**
 * The soft keyboard: a real DOM text field laid over the focused EditText. The EditText stops
 * drawing its own text while the field is shown; the field's value is pushed back on every edit.
 */
public final class TextInput {
  private TextInput() {}
  static TextView target;
  static boolean pushing;

  public static boolean show(View v) {
    if (!(v instanceof TextView)) return false;
    TextView t = (TextView) v;
    if (target != null && target != t) hide();
    target = t;
    place();
    t.webHideText(true);
    return true;
  }
  static void place() {
    TextView t = target;
    int[] loc = new int[2];
    t.getLocationOnScreen(loc);
    int left = loc[0] + t.getCompoundPaddingLeft(), top = loc[1] + t.getExtendedPaddingTop();
    int w = t.getWidth() - t.getCompoundPaddingLeft() - t.getCompoundPaddingRight();
    int h = t.getHeight() - t.getExtendedPaddingTop() - t.getExtendedPaddingBottom();
    int flags = (t.webPassword() ? 1 : 0) | (t.webMultiLine() ? 2 : 0) | (t.webNumeric() ? 4 : 0) | (t.webEmail() ? 8 : 0);
    CharSequence hint = t.getHint();
    showJS(left, top, w, h, t.getText().toString(), hint == null ? "" : hint.toString(), t.getTextSize(), t.webTextColor(),
        t.webMaxLength(), flags, t.webGravity(), t.getTypeface() == null ? "" : t.getTypeface().family);
    selectJS(t.getSelectionStart(), t.getSelectionEnd());
  }
  public static boolean hide() {
    if (target == null) return false;
    TextView t = target;
    target = null;
    hideJS();
    t.webHideText(false);
    return true;
  }
  public static void toggle() {
    if (target != null) { hide(); return; }
  }
  public static boolean isActive(View v) { return target != null && (v == null || v == target); }
  /** the EditText's text changed from code */
  public static void textChanged(TextView t) {
    if (t == target && !pushing) setJS(t.getText().toString());
  }
  public static void blurred(TextView t) { if (t == target) hide(); }
  /** the TextView's selection changed from code */
  public static void selectionChanged(TextView t) { if (t == target && !pushing) selectJS(t.getSelectionStart(), t.getSelectionEnd()); }
  /** from the page: the field's selection */
  public static void onSelection(int start, int end) {
    TextView t = target;
    if (t == null) return;
    pushing = true;
    try { t.webSetSelection(start, end); } finally { pushing = false; }
  }
  /** a touch landed somewhere on the screen; keep the field only if it hit the target */
  public static void touchedOutside(float x, float y) {
    if (target == null) return;
    int[] loc = new int[2];
    target.getLocationOnScreen(loc);
    if (x < loc[0] || y < loc[1] || x >= loc[0] + target.getWidth() || y >= loc[1] + target.getHeight()) hide();
  }
  /** from the page: 0 input, 1 enter, 2 blur */
  public static void onText(String text, int action) {
    TextView t = target;
    if (t == null) return;
    if (action == 0) {
      pushing = true;
      try { t.webSetText(text); } finally { pushing = false; }
    } else if (action == 1) {
      pushing = true;
      try { t.webSetText(text); } finally { pushing = false; }
      t.webEnter();
    } else if (action == 2) {
      hide();
    }
  }

  @JSBody(params = {"x", "y", "w", "h", "text", "hint", "size", "color", "max", "flags", "gravity", "family"},
      script = "HW.input.show(x, y, w, h, text, hint, size, color, max, flags, gravity, family);")
  static native void showJS(int x, int y, int w, int h, String text, String hint, float size, int color, int max, int flags, int gravity, String family);
  @JSBody(script = "HW.input.hide();") static native void hideJS();
  @JSBody(params = {"a", "b"}, script = "HW.input.select(a, b);") static native void selectJS(int a, int b);
  @JSBody(params = "s", script = "HW.input.set(s);") static native void setJS(String s);
}
