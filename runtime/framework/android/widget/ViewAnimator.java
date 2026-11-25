package android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/** A FrameLayout that shows one child at a time. */
public class ViewAnimator extends FrameLayout {
  int mWhichChild;
  android.view.animation.Animation in, out;
  public ViewAnimator(Context c) { this(c, null); }
  public ViewAnimator(Context c, AttributeSet a) { super(c, a); }
  @Override public void addView(View child, int index, android.view.ViewGroup.LayoutParams p) {
    super.addView(child, index, p);
    child.setVisibility(getChildCount() == 1 ? View.VISIBLE : View.GONE);
    if (index >= 0 && mWhichChild >= index) setDisplayedChild(mWhichChild + 1);
  }
  public void setDisplayedChild(int which) {
    int n = getChildCount();
    if (n == 0) { mWhichChild = 0; return; }
    mWhichChild = which >= n ? 0 : which < 0 ? n - 1 : which;
    for (int i = 0; i < n; i++) getChildAt(i).setVisibility(i == mWhichChild ? View.VISIBLE : View.GONE);
  }
  public int getDisplayedChild() { return mWhichChild; }
  public void showNext() { setDisplayedChild(mWhichChild + 1); }
  public void showPrevious() { setDisplayedChild(mWhichChild - 1); }
  public View getCurrentView() { return getChildAt(mWhichChild); }
  public void setInAnimation(android.view.animation.Animation a) { in = a; }
  public void setOutAnimation(android.view.animation.Animation a) { out = a; }
  public void setInAnimation(Context c, int id) { in = android.view.animation.AnimationUtils.loadAnimation(c, id); }
  public void setOutAnimation(Context c, int id) { out = android.view.animation.AnimationUtils.loadAnimation(c, id); }
  public android.view.animation.Animation getInAnimation() { return in; }
  public android.view.animation.Animation getOutAnimation() { return out; }
  public void setAnimateFirstView(boolean b) {}
}
