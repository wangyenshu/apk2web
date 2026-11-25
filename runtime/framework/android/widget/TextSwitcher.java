package android.widget;

import android.content.Context;
import android.util.AttributeSet;

public class TextSwitcher extends ViewSwitcher {
  public TextSwitcher(Context c) { super(c); }
  public TextSwitcher(Context c, AttributeSet a) { super(c, a); }
  public void setText(CharSequence t) { TextView v = (TextView) getNextView(); if (v != null) { v.setText(t); showNext(); } }
  public void setCurrentText(CharSequence t) { TextView v = (TextView) getCurrentView(); if (v != null) v.setText(t); }
}
