package android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import a2w.Attrs;
import a2w.Values;

@Deprecated
public class AbsoluteLayout extends ViewGroup {
  public AbsoluteLayout(Context c) { super(c); }
  public AbsoluteLayout(Context c, AttributeSet a) { super(c, a); }
  public AbsoluteLayout(Context c, AttributeSet a, int d) { super(c, a, d); }
  @Override protected void onMeasure(int ws, int hs) {
    int count = getChildCount();
    int maxHeight = 0, maxWidth = 0;
    measureChildren(ws, hs);
    for (int i = 0; i < count; i++) {
      View child = getChildAt(i);
      if (child.getVisibility() != GONE) {
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        maxWidth = Math.max(maxWidth, lp.x + child.getMeasuredWidth());
        maxHeight = Math.max(maxHeight, lp.y + child.getMeasuredHeight());
      }
    }
    maxWidth += mPaddingLeft + mPaddingRight;
    maxHeight += mPaddingTop + mPaddingBottom;
    maxHeight = Math.max(maxHeight, getSuggestedMinimumHeight());
    maxWidth = Math.max(maxWidth, getSuggestedMinimumWidth());
    setMeasuredDimension(resolveSizeAndState(maxWidth, ws, 0), resolveSizeAndState(maxHeight, hs, 0));
  }
  @Override protected ViewGroup.LayoutParams generateDefaultLayoutParams() { return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 0); }
  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
    int count = getChildCount();
    for (int i = 0; i < count; i++) {
      View child = getChildAt(i);
      if (child.getVisibility() != GONE) {
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        int childLeft = mPaddingLeft + lp.x, childTop = mPaddingTop + lp.y;
        child.layout(childLeft, childTop, childLeft + child.getMeasuredWidth(), childTop + child.getMeasuredHeight());
      }
    }
  }
  @Override public ViewGroup.LayoutParams generateLayoutParams(AttributeSet a) { return new LayoutParams(getContext(), a); }
  @Override protected boolean checkLayoutParams(ViewGroup.LayoutParams p) { return p instanceof LayoutParams; }
  @Override protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) { return new LayoutParams(p); }
  @Override public boolean shouldDelayChildPressedState() { return false; }
  public static class LayoutParams extends ViewGroup.LayoutParams {
    public int x, y;
    public LayoutParams(int w, int h, int x, int y) { super(w, h); this.x = x; this.y = y; }
    public LayoutParams(Context c, AttributeSet attrs) {
      super(c, attrs);
      if (attrs instanceof Attrs) {
        String v = ((Attrs) attrs).a("layout_x"); if (v != null) x = (int) Values.dimension(v);
        v = ((Attrs) attrs).a("layout_y"); if (v != null) y = (int) Values.dimension(v);
      }
    }
    public LayoutParams(ViewGroup.LayoutParams s) { super(s); }
  }
}
