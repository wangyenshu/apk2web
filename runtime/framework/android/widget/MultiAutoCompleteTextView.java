package android.widget;

import android.content.Context;
import android.text.Editable;
import android.text.Selection;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.AttributeSet;

/** Auto-completion for each token of a list (comma-separated addresses, tags). */
public class MultiAutoCompleteTextView extends AutoCompleteTextView {
  public interface Tokenizer {
    int findTokenStart(CharSequence text, int cursor);
    int findTokenEnd(CharSequence text, int cursor);
    CharSequence terminateToken(CharSequence text);
  }
  public static class CommaTokenizer implements Tokenizer {
    public int findTokenStart(CharSequence text, int cursor) {
      int i = cursor;
      while (i > 0 && text.charAt(i - 1) != ',') i--;
      while (i < cursor && text.charAt(i) == ' ') i++;
      return i;
    }
    public int findTokenEnd(CharSequence text, int cursor) {
      int i = cursor, len = text.length();
      while (i < len) { if (text.charAt(i) == ',') return i; i++; }
      return len;
    }
    public CharSequence terminateToken(CharSequence text) {
      int i = text.length();
      while (i > 0 && text.charAt(i - 1) == ' ') i--;
      if (i > 0 && text.charAt(i - 1) == ',') return text;
      if (text instanceof Spanned) {
        SpannableString sp = new SpannableString(text + ", ");
        TextUtils.copySpansFrom((Spanned) text, 0, text.length(), Object.class, sp, 0);
        return sp;
      }
      return text + ", ";
    }
  }
  private Tokenizer mTokenizer;
  public MultiAutoCompleteTextView(Context c) { this(c, null); }
  public MultiAutoCompleteTextView(Context c, AttributeSet a) { this(c, a, android.R.attr.autoCompleteTextViewStyle); }
  public MultiAutoCompleteTextView(Context c, AttributeSet a, int d) { super(c, a, d); }
  public MultiAutoCompleteTextView(Context c, AttributeSet a, int d, int r) { super(c, a, d, r); }
  public void setTokenizer(Tokenizer t) { mTokenizer = t; }
  @Override public boolean enoughToFilter() {
    Editable text = getText();
    int end = getSelectionEnd();
    if (end < 0 || mTokenizer == null) return false;
    int start = mTokenizer.findTokenStart(text, end);
    return end - start >= getThreshold();
  }
  @Override protected void performFiltering(CharSequence text, int keyCode) {
    if (enoughToFilter()) {
      int end = getSelectionEnd();
      int start = mTokenizer.findTokenStart(text, end);
      performFiltering(text, start, end, keyCode);
    } else { dismissDropDown(); Filter f = getFilter(); if (f != null) f.filter(null); }
  }
  protected void performFiltering(CharSequence text, int start, int end, int keyCode) { getFilter().filter(text.subSequence(start, end), this); }
  @Override protected void replaceText(CharSequence text) {
    clearComposingText();
    int end = getSelectionEnd();
    Editable editable = getText();
    int start = mTokenizer.findTokenStart(editable, end);
    String original = TextUtils.substring(editable, start, end);
    editable.replace(start, end, mTokenizer.terminateToken(text));
  }
}
