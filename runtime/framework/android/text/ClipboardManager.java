package android.text;
public class ClipboardManager {
  CharSequence text;
  public CharSequence getText() { return text; }
  public void setText(CharSequence t) { text = t; }
  public boolean hasText() { return text != null && text.length() > 0; }
}
