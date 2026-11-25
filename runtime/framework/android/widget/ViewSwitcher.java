package android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class ViewSwitcher extends ViewAnimator {
  public interface ViewFactory { View makeView(); }
  private ViewFactory factory;
  public ViewSwitcher(Context c) { super(c); }
  public ViewSwitcher(Context c, AttributeSet a) { super(c, a); }
  public void setFactory(ViewFactory f) { factory = f; while (getChildCount() < 2) addView(f.makeView()); }
  public View getNextView() { return getChildAt(getDisplayedChild() == 0 ? 1 : 0); }
  public void reset() { setDisplayedChild(0); }
}
