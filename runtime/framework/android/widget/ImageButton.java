package android.widget;

import android.content.Context;
import android.util.AttributeSet;
import a2w.Attrs;
import a2w.Styles;

public class ImageButton extends ImageView {
  static final String[] DEFAULTS = {"a", "background", "@android:drawable/btn_default", "a", "focusable", "true", "a", "clickable", "true", "a", "gravity", "center"};
  public ImageButton(Context c) { this(c, null); }
  public ImageButton(Context c, AttributeSet a) { this(c, a, 0); }
  public ImageButton(Context c, AttributeSet a, int d) { super(c, defaults(a), d); }
  public ImageButton(Context c, AttributeSet a, int defStyleAttr, int defStyleRes) { this(c, a, defStyleAttr); }
  static AttributeSet defaults(AttributeSet a) {
    if (a instanceof Attrs) return ((Attrs) a).withDefaults(DEFAULTS);
    if (a == null) return Attrs.defaultsOnly(DEFAULTS);
    return a;
  }
  @Override protected boolean onSetAlpha(int alpha) { return false; }
}
