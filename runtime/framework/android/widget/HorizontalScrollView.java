package android.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.*;
import a2w.Attrs;

public class HorizontalScrollView extends FrameLayout {
  static final int ANIMATED_SCROLL_GAP = 250;
  private long mLastScroll;
  private final Rect mTempRect = new Rect();
  private final Scroller mScroller;
  private boolean mIsLayoutDirty = true, mIsBeingDragged, mFillViewport, mSmoothScrollingEnabled = true;
  private View mChildToScrollTo;
  private float mLastMotionX;
  private VelocityTracker mVelocityTracker;
  private final int mTouchSlop, mMinimumVelocity, mMaximumVelocity;
  private int mActivePointerId = -1;

  public HorizontalScrollView(Context c) { this(c, null); }
  public HorizontalScrollView(Context c, AttributeSet a) { this(c, a, 0); }
  public HorizontalScrollView(Context c, AttributeSet a, int d) {
    super(c, a, d);
    mScroller = new Scroller(c);
    setFocusable(true);
    setDescendantFocusability(FOCUS_AFTER_DESCENDANTS);
    setWillNotDraw(false);
    ViewConfiguration cfg = ViewConfiguration.get(c);
    mTouchSlop = cfg.getScaledTouchSlop();
    mMinimumVelocity = cfg.getScaledMinimumFlingVelocity();
    mMaximumVelocity = cfg.getScaledMaximumFlingVelocity();
    if (a instanceof Attrs) { String v = ((Attrs) a).a("fillViewport"); if (v != null) mFillViewport = v.equals("true"); }
  }
  @Override public boolean shouldDelayChildPressedState() { return true; }
  public int getMaxScrollAmount() { return (int) (0.5f * (mRight - mLeft)); }
  @Override public void addView(View child) { if (getChildCount() > 0) throw new IllegalStateException("HorizontalScrollView can host only one direct child"); super.addView(child); }
  @Override public void addView(View child, int index) { if (getChildCount() > 0) throw new IllegalStateException("HorizontalScrollView can host only one direct child"); super.addView(child, index); }
  @Override public void addView(View child, ViewGroup.LayoutParams p) { if (getChildCount() > 0) throw new IllegalStateException("HorizontalScrollView can host only one direct child"); super.addView(child, p); }
  @Override public void addView(View child, int index, ViewGroup.LayoutParams p) { if (getChildCount() > 0) throw new IllegalStateException("HorizontalScrollView can host only one direct child"); super.addView(child, index, p); }
  public boolean isFillViewport() { return mFillViewport; }
  public void setFillViewport(boolean f) { if (f != mFillViewport) { mFillViewport = f; requestLayout(); } }
  public void setSmoothScrollingEnabled(boolean e) { mSmoothScrollingEnabled = e; }

  @Override protected void onMeasure(int ws, int hs) {
    super.onMeasure(ws, hs);
    if (!mFillViewport) return;
    if (MeasureSpec.getMode(ws) == MeasureSpec.UNSPECIFIED) return;
    if (getChildCount() > 0) {
      final View child = getChildAt(0);
      int width = getMeasuredWidth();
      if (child.getMeasuredWidth() < width) {
        final FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) child.getLayoutParams();
        int chs = getChildMeasureSpec(hs, mPaddingTop + mPaddingBottom, lp.height);
        width -= mPaddingLeft + mPaddingRight;
        child.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY), chs);
      }
    }
  }
  @Override protected void measureChild(View child, int pws, int phs) {
    ViewGroup.LayoutParams lp = child.getLayoutParams();
    child.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), getChildMeasureSpec(phs, mPaddingTop + mPaddingBottom, lp.height));
  }
  @Override protected void measureChildWithMargins(View child, int pws, int wu, int phs, int hu) {
    final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
    child.measure(MeasureSpec.makeMeasureSpec(lp.leftMargin + lp.rightMargin, MeasureSpec.UNSPECIFIED),
        getChildMeasureSpec(phs, mPaddingTop + mPaddingBottom + lp.topMargin + lp.bottomMargin + hu, lp.height));
  }
  @Override public boolean onInterceptTouchEvent(MotionEvent ev) {
    final int action = ev.getActionMasked();
    if (action == MotionEvent.ACTION_MOVE && mIsBeingDragged) return true;
    switch (action) {
      case MotionEvent.ACTION_MOVE: {
        final int pointerIndex = ev.findPointerIndex(mActivePointerId);
        if (pointerIndex < 0) break;
        final float x = ev.getX(pointerIndex);
        final int xDiff = (int) Math.abs(x - mLastMotionX);
        if (xDiff > mTouchSlop) { mIsBeingDragged = true; mLastMotionX = x; if (getParent() != null) getParent().requestDisallowInterceptTouchEvent(true); }
        break;
      }
      case MotionEvent.ACTION_DOWN: {
        final float x = ev.getX();
        if (!inChild((int) x, (int) ev.getY())) { mIsBeingDragged = false; break; }
        mLastMotionX = x;
        mActivePointerId = ev.getPointerId(0);
        mIsBeingDragged = !mScroller.isFinished();
        break;
      }
      case MotionEvent.ACTION_CANCEL: case MotionEvent.ACTION_UP: mIsBeingDragged = false; mActivePointerId = -1; break;
    }
    return mIsBeingDragged;
  }
  private boolean inChild(int x, int y) {
    if (getChildCount() > 0) {
      final int scrollX = mScrollX;
      final View child = getChildAt(0);
      return !(y < child.getTop() || y >= child.getBottom() || x < child.getLeft() - scrollX || x >= child.getRight() - scrollX);
    }
    return false;
  }
  @Override public boolean onTouchEvent(MotionEvent ev) {
    if (ev.getAction() == MotionEvent.ACTION_DOWN && ev.getEdgeFlags() != 0) return false;
    if (mVelocityTracker == null) mVelocityTracker = VelocityTracker.obtain();
    mVelocityTracker.addMovement(ev);
    switch (ev.getActionMasked()) {
      case MotionEvent.ACTION_DOWN: {
        mIsBeingDragged = getChildCount() != 0;
        if (!mIsBeingDragged) return false;
        if (!mScroller.isFinished()) mScroller.abortAnimation();
        mLastMotionX = ev.getX();
        mActivePointerId = ev.getPointerId(0);
        break;
      }
      case MotionEvent.ACTION_MOVE:
        if (mIsBeingDragged) {
          final int i = ev.findPointerIndex(mActivePointerId);
          if (i < 0) break;
          final float x = ev.getX(i);
          final int deltaX = (int) (mLastMotionX - x);
          mLastMotionX = x;
          scrollBy(deltaX, 0);
        }
        break;
      case MotionEvent.ACTION_UP:
        if (mIsBeingDragged) {
          final VelocityTracker vt = mVelocityTracker;
          vt.computeCurrentVelocity(1000, mMaximumVelocity);
          int initialVelocity = (int) vt.getXVelocity(mActivePointerId);
          if (getChildCount() > 0 && Math.abs(initialVelocity) > mMinimumVelocity) fling(-initialVelocity);
          mActivePointerId = -1;
          mIsBeingDragged = false;
          if (mVelocityTracker != null) { mVelocityTracker.recycle(); mVelocityTracker = null; }
        }
        break;
      case MotionEvent.ACTION_CANCEL:
        mIsBeingDragged = false; mActivePointerId = -1;
        if (mVelocityTracker != null) { mVelocityTracker.recycle(); mVelocityTracker = null; }
        break;
    }
    return true;
  }
  // ---------------------------------------------------------------- keys (after AOSP): the focused descendant first, then scrolling
  private boolean canScroll() {
    View child = getChildAt(0);
    return child != null && getWidth() < child.getWidth() + mPaddingLeft + mPaddingRight;
  }
  @Override public boolean dispatchKeyEvent(KeyEvent event) { return super.dispatchKeyEvent(event) || executeKeyEvent(event); }
  /** left/right (alt: to the ends), space (shift: left); moves focus to what scrolls into view */
  public boolean executeKeyEvent(KeyEvent event) {
    mTempRect.setEmpty();
    if (!canScroll()) {
      if (isFocused()) {
        View currentFocused = findFocus();
        if (currentFocused == this) currentFocused = null;
        View nextFocused = FocusFinder.getInstance().findNextFocus(this, currentFocused, View.FOCUS_RIGHT);
        return nextFocused != null && nextFocused != this && nextFocused.requestFocus(View.FOCUS_RIGHT);
      }
      return false;
    }
    boolean handled = false;
    if (event.getAction() == KeyEvent.ACTION_DOWN) {
      switch (event.getKeyCode()) {
        case KeyEvent.KEYCODE_DPAD_LEFT: handled = !event.isAltPressed() ? arrowScroll(View.FOCUS_LEFT) : fullScroll(View.FOCUS_LEFT); break;
        case KeyEvent.KEYCODE_DPAD_RIGHT: handled = !event.isAltPressed() ? arrowScroll(View.FOCUS_RIGHT) : fullScroll(View.FOCUS_RIGHT); break;
        case KeyEvent.KEYCODE_SPACE: pageScroll(event.isShiftPressed() ? View.FOCUS_LEFT : View.FOCUS_RIGHT); break;
      }
    }
    return handled;
  }
  public boolean pageScroll(int direction) {
    boolean right = direction == View.FOCUS_RIGHT;
    int width = getWidth();
    if (right) {
      mTempRect.left = mScrollX + width;
      int count = getChildCount();
      if (count > 0) { View view = getChildAt(0); if (mTempRect.left + width > view.getRight()) mTempRect.left = view.getRight() - width; }
    } else {
      mTempRect.left = mScrollX - width;
      if (mTempRect.left < 0) mTempRect.left = 0;
    }
    mTempRect.right = mTempRect.left + width;
    return scrollAndFocus(direction, mTempRect.left, mTempRect.right);
  }
  public boolean fullScroll(int direction) {
    boolean right = direction == View.FOCUS_RIGHT;
    int width = getWidth();
    mTempRect.left = 0;
    mTempRect.right = width;
    if (right && getChildCount() > 0) { View view = getChildAt(0); mTempRect.right = view.getRight(); mTempRect.left = mTempRect.right - width; }
    return scrollAndFocus(direction, mTempRect.left, mTempRect.right);
  }
  private boolean scrollAndFocus(int direction, int left, int right) {
    boolean handled = true;
    int width = getWidth(), containerLeft = mScrollX, containerRight = containerLeft + width;
    boolean goLeft = direction == View.FOCUS_LEFT;
    View newFocused = findFocusableViewInBounds(goLeft, left, right);
    if (newFocused == null) newFocused = this;
    if (left >= containerLeft && right <= containerRight) handled = false;
    else doScrollX(goLeft ? left - containerLeft : right - containerRight);
    if (newFocused != findFocus()) newFocused.requestFocus(direction);
    return handled;
  }
  private View findFocusableViewInBounds(boolean leftFocus, int left, int right) {
    java.util.List<View> focusables = getFocusables(View.FOCUS_FORWARD);
    View focusCandidate = null;
    boolean foundFullyContainedFocusable = false;
    for (View view : focusables) {
      int viewLeft = view.getLeft(), viewRight = view.getRight();
      if (left < viewRight && viewLeft < right) {
        final boolean viewIsFullyContained = left < viewLeft && viewRight < right;
        if (focusCandidate == null) { focusCandidate = view; foundFullyContainedFocusable = viewIsFullyContained; }
        else {
          final boolean viewIsCloserToBoundary = (leftFocus && viewLeft < focusCandidate.getLeft()) || (!leftFocus && viewRight > focusCandidate.getRight());
          if (foundFullyContainedFocusable) { if (viewIsFullyContained && viewIsCloserToBoundary) focusCandidate = view; }
          else if (viewIsFullyContained) { focusCandidate = view; foundFullyContainedFocusable = true; }
          else if (viewIsCloserToBoundary) focusCandidate = view;
        }
      }
    }
    return focusCandidate;
  }
  public boolean arrowScroll(int direction) {
    View currentFocused = findFocus();
    if (currentFocused == this) currentFocused = null;
    View nextFocused = FocusFinder.getInstance().findNextFocus(this, currentFocused, direction);
    final int maxJump = getMaxScrollAmount();
    if (nextFocused != null && isWithinDeltaOfScreen(nextFocused, maxJump)) {
      nextFocused.getDrawingRect(mTempRect);
      offsetDescendantRectToMyCoords(nextFocused, mTempRect);
      doScrollX(computeScrollDeltaToGetChildRectOnScreen(mTempRect));
      nextFocused.requestFocus(direction);
    } else {
      int scrollDelta = maxJump;
      if (direction == View.FOCUS_LEFT && mScrollX < scrollDelta) scrollDelta = mScrollX;
      else if (direction == View.FOCUS_RIGHT && getChildCount() > 0) {
        int daRight = getChildAt(0).getRight(), screenRight = mScrollX + getWidth();
        if (daRight - screenRight < maxJump) scrollDelta = daRight - screenRight;
      }
      if (scrollDelta == 0) return false;
      doScrollX(direction == View.FOCUS_RIGHT ? scrollDelta : -scrollDelta);
    }
    if (currentFocused != null && currentFocused.isFocused() && !isWithinDeltaOfScreen(currentFocused, 0)) {
      final int descendantFocusability = getDescendantFocusability();
      setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
      requestFocus();
      setDescendantFocusability(descendantFocusability);
    }
    return true;
  }
  private boolean isWithinDeltaOfScreen(View descendant, int delta) {
    descendant.getDrawingRect(mTempRect);
    offsetDescendantRectToMyCoords(descendant, mTempRect);
    return mTempRect.right + delta >= mScrollX && mTempRect.left - delta <= mScrollX + getWidth();
  }
  protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
    if (getChildCount() == 0) return 0;
    int width = getWidth(), screenLeft = mScrollX, screenRight = screenLeft + width, delta = 0;
    if (rect.right > screenRight && rect.left > screenLeft) {
      delta = rect.width() > width ? rect.left - screenLeft : rect.right - screenRight;
      delta = Math.min(delta, getChildAt(0).getRight() - screenRight);
    } else if (rect.left < screenLeft && rect.right < screenRight) {
      delta = rect.width() > width ? -(screenRight - rect.right) : -(screenLeft - rect.left);
      delta = Math.max(delta, -mScrollX);
    }
    return delta;
  }
  private void doScrollX(int delta) { if (delta != 0) { if (mSmoothScrollingEnabled) smoothScrollBy(delta, 0); else scrollBy(delta, 0); } }
  @Override protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
    if (direction == View.FOCUS_FORWARD) direction = View.FOCUS_RIGHT;
    else if (direction == View.FOCUS_BACKWARD) direction = View.FOCUS_LEFT;
    final View nextFocus = previouslyFocusedRect == null ? FocusFinder.getInstance().findNextFocus(this, null, direction)
        : FocusFinder.getInstance().findNextFocusFromRect(this, previouslyFocusedRect, direction);
    if (nextFocus == null || !isWithinDeltaOfScreen(nextFocus, 0)) return false;
    return nextFocus.requestFocus(direction, previouslyFocusedRect);
  }
  public final void smoothScrollBy(int dx, int dy) {
    if (getChildCount() == 0) return;
    long duration = android.view.animation.AnimationUtils.currentAnimationTimeMillis() - mLastScroll;
    if (duration > ANIMATED_SCROLL_GAP) {
      final int width = getWidth() - mPaddingRight - mPaddingLeft;
      final int right = getChildAt(0).getWidth();
      final int maxX = Math.max(0, right - width);
      final int scrollX = mScrollX;
      dx = Math.max(0, Math.min(scrollX + dx, maxX)) - scrollX;
      mScroller.startScroll(scrollX, mScrollY, dx, 0);
      invalidate();
    } else {
      if (!mScroller.isFinished()) mScroller.abortAnimation();
      scrollBy(dx, dy);
    }
    mLastScroll = android.view.animation.AnimationUtils.currentAnimationTimeMillis();
  }
  public final void smoothScrollTo(int x, int y) { smoothScrollBy(x - mScrollX, y - mScrollY); }
  private int getScrollRange() { return getChildCount() == 0 ? 0 : Math.max(0, getChildAt(0).getWidth() - (getWidth() - mPaddingLeft - mPaddingRight)); }
  /** a mouse wheel: AXIS_HSCROLL (or AXIS_VSCROLL with shift) units of the horizontal scroll factor */
  @Override public boolean onGenericMotionEvent(android.view.MotionEvent event) {
    if (event.getAction() == android.view.MotionEvent.ACTION_SCROLL && event.isFromSource(android.view.InputDevice.SOURCE_CLASS_POINTER)) {
      float v = (event.getMetaState() & android.view.KeyEvent.META_SHIFT_ON) != 0 ? -event.getAxisValue(android.view.MotionEvent.AXIS_VSCROLL)
          : event.getAxisValue(android.view.MotionEvent.AXIS_HSCROLL);
      int delta = Math.round(v * getHorizontalScrollFactor());
      if (delta != 0) {
        int range = getScrollRange(), old = mScrollX, x = Math.max(0, Math.min(range, old + delta));
        if (x != old) { super.scrollTo(x, mScrollY); return true; }
      }
    }
    return super.onGenericMotionEvent(event);
  }
  @Override protected int computeHorizontalScrollRange() { return getChildCount() == 0 ? getWidth() - mPaddingLeft - mPaddingRight : getChildAt(0).getRight(); }
  @Override public void computeScroll() {
    if (mScroller.computeScrollOffset()) {
      int oldX = mScrollX, oldY = mScrollY;
      int x = mScroller.getCurrX(), y = mScroller.getCurrY();
      if (getChildCount() > 0) {
        View child = getChildAt(0);
        x = ScrollView.clamp(x, getWidth() - mPaddingRight - mPaddingLeft, child.getWidth());
        y = ScrollView.clamp(y, getHeight() - mPaddingBottom - mPaddingTop, child.getHeight());
        if (x != oldX || y != oldY) { mScrollX = x; mScrollY = y; onScrollChanged(x, y, oldX, oldY); }
      }
      invalidate();
    }
  }
  @Override public void requestChildFocus(View child, View focused) {
    if (!mIsLayoutDirty) scrollToChild(focused); else mChildToScrollTo = focused;
    super.requestChildFocus(child, focused);
  }
  private void scrollToChild(View child) {
    child.getDrawingRect(mTempRect);
    offsetDescendantRectToMyCoords(child, mTempRect);
    int width = getWidth(), screenLeft = mScrollX, screenRight = screenLeft + width, delta = 0;
    if (mTempRect.right > screenRight && mTempRect.left > screenLeft) delta = Math.min(mTempRect.width() > width ? mTempRect.left - screenLeft : mTempRect.right - screenRight, getChildAt(0).getRight() - screenRight);
    else if (mTempRect.left < screenLeft && mTempRect.right < screenRight) delta = Math.max(mTempRect.width() > width ? -(screenRight - mTempRect.right) : -(screenLeft - mTempRect.left), -mScrollX);
    if (delta != 0) scrollBy(delta, 0);
  }
  @Override public void requestLayout() { mIsLayoutDirty = true; super.requestLayout(); }
  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
    super.onLayout(changed, l, t, r, b);
    mIsLayoutDirty = false;
    if (mChildToScrollTo != null) { scrollToChild(mChildToScrollTo); mChildToScrollTo = null; }
    scrollTo(mScrollX, mScrollY);
  }
  public void fling(int velocityX) {
    if (getChildCount() > 0) {
      int width = getWidth() - mPaddingRight - mPaddingLeft;
      int right = getChildAt(0).getWidth();
      mScroller.fling(mScrollX, mScrollY, velocityX, 0, 0, Math.max(0, right - width), 0, 0);
      invalidate();
    }
  }
  @Override public void scrollTo(int x, int y) {
    if (getChildCount() > 0) {
      View child = getChildAt(0);
      x = ScrollView.clamp(x, getWidth() - mPaddingRight - mPaddingLeft, child.getWidth());
      y = ScrollView.clamp(y, getHeight() - mPaddingBottom - mPaddingTop, child.getHeight());
      if (x != mScrollX || y != mScrollY) super.scrollTo(x, y);
    }
  }
}
