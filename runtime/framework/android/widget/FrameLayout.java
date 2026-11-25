package android.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import a2w.Attrs;
import a2w.Values;
import java.util.ArrayList;

public class FrameLayout extends ViewGroup {
  boolean mMeasureAllChildren;
  Drawable mForeground;
  int mForegroundGravity = Gravity.FILL;
  int mForegroundPaddingLeft, mForegroundPaddingTop, mForegroundPaddingRight, mForegroundPaddingBottom;
  boolean mForegroundInPadding = true, mForegroundBoundsChanged;
  private final Rect mSelfBounds = new Rect(), mOverlayBounds = new Rect();
  private final ArrayList<View> mMatchParentChildren = new ArrayList<>(1);

  public FrameLayout(Context c) { super(c); }
  public FrameLayout(Context c, AttributeSet a) { this(c, a, 0); }
  public FrameLayout(Context c, AttributeSet a, int d, int defStyleRes) { this(c, a, d); }
  public FrameLayout(Context c, AttributeSet a, int d) {
    super(c, a, d);
    if (a instanceof Attrs) {
      Attrs at = (Attrs) a;
      String v;
      if ((v = at.a("foreground")) != null && Values.isRef(v)) setForeground(getResources().getDrawable(Values.refId(v)));
      if ((v = at.a("foregroundGravity")) != null) mForegroundGravity = Values.gravity(v);
      if ((v = at.a("measureAllChildren")) != null) mMeasureAllChildren = v.equals("true");
    }
  }
  public void setForegroundGravity(int g) { if ((g & Gravity.HORIZONTAL_GRAVITY_MASK) == 0) g |= Gravity.LEFT; if ((g & Gravity.VERTICAL_GRAVITY_MASK) == 0) g |= Gravity.TOP; mForegroundGravity = g; requestLayout(); }
  public void setForeground(Drawable d) {
    if (mForeground != d) {
      if (mForeground != null) mForeground.setCallback(null);
      mForeground = d;
      if (d != null) { d.setCallback(this); if (d.isStateful()) d.setState(getDrawableState()); }
      mForegroundBoundsChanged = true;
      requestLayout(); invalidate();
    }
  }
  public Drawable getForeground() { return mForeground; }
  @Override protected boolean verifyDrawable(Drawable who) { return super.verifyDrawable(who) || who == mForeground; }
  @Override protected void drawableStateChanged() { super.drawableStateChanged(); if (mForeground != null && mForeground.isStateful()) mForeground.setState(getDrawableState()); }
  public void setMeasureAllChildren(boolean m) { mMeasureAllChildren = m; }
  public boolean getConsiderGoneChildrenWhenMeasuring() { return mMeasureAllChildren; }
  @Override public boolean shouldDelayChildPressedState() { return false; }

  @Override protected LayoutParams generateDefaultLayoutParams() { return new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT); }
  @Override public LayoutParams generateLayoutParams(AttributeSet a) { return new LayoutParams(getContext(), a); }
  @Override protected boolean checkLayoutParams(ViewGroup.LayoutParams p) { return p instanceof LayoutParams; }
  @Override protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) { return new LayoutParams(p); }

  int getPaddingLeftWithForeground() { return mForegroundInPadding ? Math.max(mPaddingLeft, mForegroundPaddingLeft) : mPaddingLeft + mForegroundPaddingLeft; }
  int getPaddingRightWithForeground() { return mForegroundInPadding ? Math.max(mPaddingRight, mForegroundPaddingRight) : mPaddingRight + mForegroundPaddingRight; }
  int getPaddingTopWithForeground() { return mForegroundInPadding ? Math.max(mPaddingTop, mForegroundPaddingTop) : mPaddingTop + mForegroundPaddingTop; }
  int getPaddingBottomWithForeground() { return mForegroundInPadding ? Math.max(mPaddingBottom, mForegroundPaddingBottom) : mPaddingBottom + mForegroundPaddingBottom; }

  @Override protected void onMeasure(int ws, int hs) {
    int count = getChildCount();
    final boolean measureMatchParentChildren = MeasureSpec.getMode(ws) != MeasureSpec.EXACTLY || MeasureSpec.getMode(hs) != MeasureSpec.EXACTLY;
    mMatchParentChildren.clear();
    int maxHeight = 0, maxWidth = 0, childState = 0;
    for (int i = 0; i < count; i++) {
      final View child = getChildAt(i);
      if (mMeasureAllChildren || child.getVisibility() != GONE) {
        measureChildWithMargins(child, ws, 0, hs, 0);
        final LayoutParams lp = (LayoutParams) child.getLayoutParams();
        maxWidth = Math.max(maxWidth, child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin);
        maxHeight = Math.max(maxHeight, child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin);
        childState = combineMeasuredStates(childState, child.getMeasuredState());
        if (measureMatchParentChildren && (lp.width == LayoutParams.MATCH_PARENT || lp.height == LayoutParams.MATCH_PARENT)) mMatchParentChildren.add(child);
      }
    }
    maxWidth += getPaddingLeftWithForeground() + getPaddingRightWithForeground();
    maxHeight += getPaddingTopWithForeground() + getPaddingBottomWithForeground();
    maxHeight = Math.max(maxHeight, getSuggestedMinimumHeight());
    maxWidth = Math.max(maxWidth, getSuggestedMinimumWidth());
    if (mForeground != null) { maxHeight = Math.max(maxHeight, mForeground.getMinimumHeight()); maxWidth = Math.max(maxWidth, mForeground.getMinimumWidth()); }
    setMeasuredDimension(resolveSizeAndState(maxWidth, ws, childState), resolveSizeAndState(maxHeight, hs, childState << MEASURED_HEIGHT_STATE_SHIFT));
    count = mMatchParentChildren.size();
    if (count > 1) {
      for (int i = 0; i < count; i++) {
        final View child = mMatchParentChildren.get(i);
        final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
        int cws, chs;
        if (lp.width == LayoutParams.MATCH_PARENT)
          cws = MeasureSpec.makeMeasureSpec(getMeasuredWidth() - getPaddingLeftWithForeground() - getPaddingRightWithForeground() - lp.leftMargin - lp.rightMargin, MeasureSpec.EXACTLY);
        else cws = getChildMeasureSpec(ws, getPaddingLeftWithForeground() + getPaddingRightWithForeground() + lp.leftMargin + lp.rightMargin, lp.width);
        if (lp.height == LayoutParams.MATCH_PARENT)
          chs = MeasureSpec.makeMeasureSpec(getMeasuredHeight() - getPaddingTopWithForeground() - getPaddingBottomWithForeground() - lp.topMargin - lp.bottomMargin, MeasureSpec.EXACTLY);
        else chs = getChildMeasureSpec(hs, getPaddingTopWithForeground() + getPaddingBottomWithForeground() + lp.topMargin + lp.bottomMargin, lp.height);
        child.measure(cws, chs);
      }
    }
  }

  @Override protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    final int count = getChildCount();
    final int parentLeft = getPaddingLeftWithForeground(), parentRight = right - left - getPaddingRightWithForeground();
    final int parentTop = getPaddingTopWithForeground(), parentBottom = bottom - top - getPaddingBottomWithForeground();
    mForegroundBoundsChanged = true;
    for (int i = 0; i < count; i++) {
      final View child = getChildAt(i);
      if (child.getVisibility() != GONE) {
        final LayoutParams lp = (LayoutParams) child.getLayoutParams();
        final int width = child.getMeasuredWidth(), height = child.getMeasuredHeight();
        int childLeft, childTop;
        int gravity = lp.gravity;
        if (gravity == -1) gravity = Gravity.TOP | Gravity.LEFT;
        final int horizontalGravity = Gravity.getAbsoluteGravity(gravity, 0) & Gravity.HORIZONTAL_GRAVITY_MASK;
        final int verticalGravity = gravity & Gravity.VERTICAL_GRAVITY_MASK;
        switch (horizontalGravity) {
          case Gravity.LEFT: childLeft = parentLeft + lp.leftMargin; break;
          case Gravity.CENTER_HORIZONTAL: childLeft = parentLeft + (parentRight - parentLeft - width) / 2 + lp.leftMargin - lp.rightMargin; break;
          case Gravity.RIGHT: childLeft = parentRight - width - lp.rightMargin; break;
          default: childLeft = parentLeft + lp.leftMargin;
        }
        switch (verticalGravity) {
          case Gravity.TOP: childTop = parentTop + lp.topMargin; break;
          case Gravity.CENTER_VERTICAL: childTop = parentTop + (parentBottom - parentTop - height) / 2 + lp.topMargin - lp.bottomMargin; break;
          case Gravity.BOTTOM: childTop = parentBottom - height - lp.bottomMargin; break;
          default: childTop = parentTop + lp.topMargin;
        }
        child.layout(childLeft, childTop, childLeft + width, childTop + height);
      }
    }
  }
  @Override protected void onSizeChanged(int w, int h, int ow, int oh) { super.onSizeChanged(w, h, ow, oh); mForegroundBoundsChanged = true; }
  @Override public void draw(Canvas canvas) {
    super.draw(canvas);
    if (mForeground != null) {
      final Drawable fg = mForeground;
      if (mForegroundBoundsChanged) {
        mForegroundBoundsChanged = false;
        final int w = mRight - mLeft, h = mBottom - mTop;
        if (mForegroundInPadding) mSelfBounds.set(0, 0, w, h); else mSelfBounds.set(mPaddingLeft, mPaddingTop, w - mPaddingRight, h - mPaddingBottom);
        Gravity.apply(mForegroundGravity, fg.getIntrinsicWidth(), fg.getIntrinsicHeight(), mSelfBounds, mOverlayBounds);
        fg.setBounds(mOverlayBounds);
      }
      fg.draw(canvas);
    }
  }

  public static class LayoutParams extends MarginLayoutParams {
    public int gravity = -1;
    public LayoutParams(Context c, AttributeSet attrs) {
      super(c, attrs);
      if (attrs instanceof Attrs) { String g = ((Attrs) attrs).a("layout_gravity"); if (g != null) gravity = Values.gravity(g); }
    }
    public LayoutParams(int w, int h) { super(w, h); }
    public LayoutParams(int w, int h, int gravity) { super(w, h); this.gravity = gravity; }
    public LayoutParams(ViewGroup.LayoutParams s) { super(s); }
    public LayoutParams(MarginLayoutParams s) { super(s); }
    public LayoutParams(LayoutParams s) { super((MarginLayoutParams) s); gravity = s.gravity; }
  }
}
