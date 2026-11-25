package android.widget;

import android.content.Context;
import android.util.AttributeSet;

public class Button extends TextView {
  public Button(Context c) { this(c, null); }
  public Button(Context c, AttributeSet a) { this(c, a, 0); }
  public Button(Context c, AttributeSet a, int d) { super(c, a, d, "Widget.Button"); }
  public Button(Context c, AttributeSet a, int d, int r) { this(c, a, d); }
  /** fw: the framework style with the widget's defaults */
  Button(Context c, AttributeSet a, int d, String fw) { super(c, a, d, fw); }
}
