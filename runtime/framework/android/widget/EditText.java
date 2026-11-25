package android.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;

public class EditText extends TextView {
  public EditText(Context c) { this(c, null); }
  public EditText(Context c, AttributeSet a) { this(c, a, 0); }
  public EditText(Context c, AttributeSet a, int d) {
    super(c, mark(a), d, "Widget.EditText");
    mEditable = true;
    if (!(getText() instanceof Editable)) setText(getText(), BufferType.EDITABLE);
    if ((mInputType & 0x20000) == 0 && mMaxMode == LINES && mMaximum == Integer.MAX_VALUE && !mSingleLine) {}
  }
  static AttributeSet mark(AttributeSet a) { return a; }
  /** an editable text keeps its text in its saved state */
  @Override public boolean getFreezesText() { return true; }
  @Override public Editable getText() {
    CharSequence t = super.getText();
    if (t instanceof Editable) return (Editable) t;
    setText(t, BufferType.EDITABLE);
    return (Editable) super.getText();
  }
  @Override public void setText(CharSequence text, BufferType type) { super.setText(text, BufferType.EDITABLE); }
  public void setSelection(int start, int stop) {
    int n = getText().length();
    if (start < 0 || start > n || stop < 0 || stop > n) throw new IndexOutOfBoundsException("setSpan (" + start + " ... " + stop + ") ends beyond length " + n);
    super.setSelection(start, stop);
  }
  public void setSelection(int index) { setSelection(index, index); }
  public void selectAll() { setSelection(0, getText().length()); }
  public void extendSelection(int index) {
    int n = getText().length();
    if (index < 0 || index > n) throw new IndexOutOfBoundsException("setSpan (" + index + " ... " + index + ") ends beyond length " + n);
    android.text.Selection.extendSelection(getText(), index);
    a2w.TextInput.selectionChanged(this);
  }
  @Override public void setEllipsize(TextUtils.TruncateAt e) {
    if (e == TextUtils.TruncateAt.MARQUEE) throw new IllegalArgumentException("EditText cannot use the ellipsize mode TextUtils.TruncateAt.MARQUEE");
    super.setEllipsize(e);
  }
}
