package android.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.*;
import a2w.Attrs;

public class ScrollView extends FrameLayout {
  static final int ANIMATED_SCROLL_GAP = 250;
  private long mLastScroll;
  private final Rect mTempRect = new Rect();
  private final Scroller mScroller;
  private boolean mIsLayoutDirty = true, mIsBeingDragged, mFillViewport, mSmoothScrollingEnabled = true;
  private View mChildToScrollTo;
  private float mLastMotionY;
  private VelocityTracker mVelocityTracker;
  private final int mTouchSlop, mMinimumVelocity, mMaximumVelocity;
  private int mActivePointerId = -1;

  public ScrollView(Context c) { this(c, null); }
  public ScrollView(Context c, AttributeSet a) { this(c, a, 0); }
  public ScrollView(Context c, AttributeSet a, int d) {
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
  public int getMaxScrollAmount() { return (int) (0.5f * (mBottom - mTop)); }
  @Override public void addView(View child) { if (getChildCount() > 0) throw new IllegalStateException("ScrollView can host only one direct child"); super.addView(child); }
  @Override public void addView(View child, int index) { if (getChildCount() > 0) throw new IllegalStateException("ScrollView can host only one direct child"); super.addView(child, index); }
  @Override public void addView(View child, ViewGroup.LayoutParams p) { if (getChildCount() > 0) throw new IllegalStateException("ScrollView can host only one direct child"); super.addView(child, p); }
  @Override public void addView(View child, int index, ViewGroup.LayoutParams p) { if (getChildCount() > 0) throw new IllegalStateException("ScrollView can host only one direct child"); super.addView(child, index, p); }
  private boolean canScroll() {
    View child = getChildAt(0);
    if (child != null) { int childHeight = child.getHeight(); return getHeight() < childHeight + mPaddingTop + mPaddingBottom; }
    return false;
  }
  public boolean isFillViewport() { return mFillViewport; }
  public void setFillViewport(boolean f) { if (f != mFillViewport) { mFillViewport = f; requestLayout(); } }
  public boolean isSmoothScrollingEnabled() { return mSmoothScrollingEnabled; }
  public void setSmoothScrollingEnabled(boolean e) { mSmoothScrollingEnabled = e; }

  @Override protected void onMeasure(int ws, int hs) {
    super.onMeasure(ws, hs);
    if (!mFillViewport) return;
    if (MeasureSpec.getMode(hs) == MeasureSpec.UNSPECIFIED) return;
    if (getChildCount() > 0) {
      final View child = getChildAt(0);
      int height = getMeasuredHeight();
      if (child.getMeasuredHeight() < height) {
        final FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) child.getLayoutParams();
        int cws = getChildMeasureSpec(ws, mPaddingLeft + mPaddingRight, lp.width);
        height -= mPaddingTop + mPaddingBottom;
        child.measure(cws, MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
      }
    }
  }
  @Override protected void measureChild(View child, int pws, int phs) {
    ViewGroup.LayoutParams lp = child.getLayoutParams();
    child.measure(getChildMeasureSpec(pws, mPaddingLeft + mPaddingRight, lp.width), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
  }
  @Override protected void measureChildWithMargins(View child, int pws, int wu, int phs, int hu) {
    final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
    child.measure(getChildMeasureSpec(pws, mPaddingLeft + mPaddingRight + lp.leftMargin + lp.rightMargin + wu, lp.width),
        MeasureSpec.makeMeasureSpec(lp.topMargin + lp.bottomMargin, MeasureSpec.UNSPECIFIED));
  }

  @Override public boolean onInterceptTouchEvent(MotionEvent ev) {
    final int action = ev.getActionMasked();
    if (action == MotionEvent.ACTION_MOVE && mIsBeingDragged) return true;
    switch (action) {
      case MotionEvent.ACTION_MOVE: {
        final int pointerIndex = ev.findPointerIndex(mActivePointerId);
        if (pointerIndex < 0) break;
        final float y = ev.getY(pointerIndex);
        final int yDiff = (int) Math.abs(y - mLastMotionY);
        if (yDiff > mTouchSlop) { mIsBeingDragged = true; mLastMotionY = y; if (getParent() != null) getParent().requestDisallowInterceptTouchEvent(true); }
        break;
      }
      case MotionEvent.ACTION_DOWN: {
        final float y = ev.getY();
        if (!inChild((int) ev.getX(), (int) y)) { mIsBeingDragged = false; break; }
        mLastMotionY = y;
        mActivePointerId = ev.getPointerId(0);
        mIsBeingDragged = !mScroller.isFinished();
        break;
      }
      case MotionEvent.ACTION_CANCEL: case MotionEvent.ACTION_UP:
        mIsBeingDragged = false; mActivePointerId = -1; break;
      case MotionEvent.ACTION_POINTER_UP: onSecondaryPointerUp(ev); break;
    }
    return mIsBeingDragged;
  }
  private boolean inChild(int x, int y) {
    if (getChildCount() > 0) {
      final int scrollY = mScrollY;
      final View child = getChildAt(0);
      return !(y < child.getTop() - scrollY || y >= child.getBottom() - scrollY || x < child.getLeft() || x >= child.getRight());
    }
    return false;
  }
  @Override public boolean onTouchEvent(MotionEvent ev) {
    if (ev.getAction() == MotionEvent.ACTION_DOWN && ev.getEdgeFlags() != 0) return false;
    if (mVelocityTracker == null) mVelocityTracker = VelocityTracker.obtain();
    mVelocityTracker.addMovement(ev);
    final int action = ev.getActionMasked();
    switch (action) {
      case MotionEvent.ACTION_DOWN: {
        mIsBeingDragged = getChildCount() != 0;
        if (!mIsBeingDragged) return false;
        if (!mScroller.isFinished()) mScroller.abortAnimation();
        mLastMotionY = ev.getY();
        mActivePointerId = ev.getPointerId(0);
        break;
      }
      case MotionEvent.ACTION_MOVE:
        if (mIsBeingDragged) {
          final int activePointerIndex = ev.findPointerIndex(mActivePointerId);
          if (activePointerIndex < 0) break;
          final float y = ev.getY(activePointerIndex);
          final int deltaY = (int) (mLastMotionY - y);
          mLastMotionY = y;
          scrollBy(0, deltaY);
        }
        break;
      case MotionEvent.ACTION_UP:
        if (mIsBeingDragged) {
          final VelocityTracker vt = mVelocityTracker;
          vt.computeCurrentVelocity(1000, mMaximumVelocity);
          int initialVelocity = (int) vt.getYVelocity(mActivePointerId);
          if (getChildCount() > 0 && Math.abs(initialVelocity) > mMinimumVelocity) fling(-initialVelocity);
          mActivePointerId = -1;
          mIsBeingDragged = false;
          if (mVelocityTracker != null) { mVelocityTracker.recycle(); mVelocityTracker = null; }
        }
        break;
      case MotionEvent.ACTION_CANCEL:
        if (mIsBeingDragged && getChildCount() > 0) { mActivePointerId = -1; mIsBeingDragged = false; if (mVelocityTracker != null) { mVelocityTracker.recycle(); mVelocityTracker = null; } }
        break;
      case MotionEvent.ACTION_POINTER_DOWN: { final int index = ev.getActionIndex(); mLastMotionY = ev.getY(index); mActivePointerId = ev.getPointerId(index); break; }
      case MotionEvent.ACTION_POINTER_UP: onSecondaryPointerUp(ev); if (ev.findPointerIndex(mActivePointerId) >= 0) mLastMotionY = ev.getY(ev.findPointerIndex(mActivePointerId)); break;
    }
    return true;
  }
  private void onSecondaryPointerUp(MotionEvent ev) {
    final int pointerIndex = ev.getActionIndex();
    final int pointerId = ev.getPointerId(pointerIndex);
    if (pointerId == mActivePointerId) {
      final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
      if (newPointerIndex < ev.getPointerCount()) { mLastMotionY = ev.getY(newPointerIndex); mActivePointerId = ev.getPointerId(newPointerIndex); }
      if (mVelocityTracker != null) mVelocityTracker.clear();
    }
  }
  // ---------------------------------------------------------------- keys (after AOSP): the focused descendant first, then scrolling
  @Override public boolean dispatchKeyEvent(KeyEvent event) { return super.dispatchKeyEvent(event) || executeKeyEvent(event); }
  /** up/down (alt: to the ends), page up/down, home/end, space (shift: up); moves focus to what scrolls into view */
  public boolean executeKeyEvent(KeyEvent event) {
    mTempRect.setEmpty();
    if (!canScroll()) {
      if (isFocused() && event.getKeyCode() != KeyEvent.KEYCODE_BACK) {
        View currentFocused = findFocus();
        if (currentFocused == this) currentFocused = null;
        View nextFocused = FocusFinder.getInstance().findNextFocus(this, currentFocused, View.FOCUS_DOWN);
        return nextFocused != null && nextFocused != this && nextFocused.requestFocus(View.FOCUS_DOWN);
      }
      return false;
    }
    boolean handled = false;
    if (event.getAction() == KeyEvent.ACTION_DOWN) {
      switch (event.getKeyCode()) {
        case KeyEvent.KEYCODE_DPAD_UP: handled = !event.isAltPressed() ? arrowScroll(View.FOCUS_UP) : fullScroll(View.FOCUS_UP); break;
        case KeyEvent.KEYCODE_DPAD_DOWN: handled = !event.isAltPressed() ? arrowScroll(View.FOCUS_DOWN) : fullScroll(View.FOCUS_DOWN); break;
        case KeyEvent.KEYCODE_MOVE_HOME: handled = fullScroll(View.FOCUS_UP); break;
        case KeyEvent.KEYCODE_MOVE_END: handled = fullScroll(View.FOCUS_DOWN); break;
        case KeyEvent.KEYCODE_PAGE_UP: handled = pageScroll(View.FOCUS_UP); break;
        case KeyEvent.KEYCODE_PAGE_DOWN: handled = pageScroll(View.FOCUS_DOWN); break;
        case KeyEvent.KEYCODE_SPACE: pageScroll(event.isShiftPressed() ? View.FOCUS_UP : View.FOCUS_DOWN); break;
      }
    }
    return handled;
  }
  public boolean pageScroll(int direction) {
    boolean down = direction == View.FOCUS_DOWN;
    int height = getHeight();
    if (down) {
      mTempRect.top = mScrollY + height;
      int count = getChildCount();
      if (count > 0) { View view = getChildAt(count - 1); if (mTempRect.top + height > view.getBottom()) mTempRect.top = view.getBottom() - height; }
    } else {
      mTempRect.top = mScrollY - height;
      if (mTempRect.top < 0) mTempRect.top = 0;
    }
    mTempRect.bottom = mTempRect.top + height;
    return scrollAndFocus(direction, mTempRect.top, mTempRect.bottom);
  }
  public boolean fullScroll(int direction) {
    boolean down = direction == View.FOCUS_DOWN;
    int height = getHeight();
    mTempRect.top = 0;
    mTempRect.bottom = height;
    if (down) {
      int count = getChildCount();
      if (count > 0) { View view = getChildAt(count - 1); mTempRect.bottom = view.getBottom() + mPaddingBottom; mTempRect.top = mTempRect.bottom - height; }
    }
    return scrollAndFocus(direction, mTempRect.top, mTempRect.bottom);
  }
  private boolean scrollAndFocus(int direction, int top, int bottom) {
    boolean handled = true;
    int height = getHeight(), containerTop = mScrollY, containerBottom = containerTop + height;
    boolean up = direction == View.FOCUS_UP;
    View newFocused = findFocusableViewInBounds(up, top, bottom);
    if (newFocused == null) newFocused = this;
    if (top >= containerTop && bottom <= containerBottom) handled = false;
    else doScrollY(up ? top - containerTop : bottom - containerBottom);
    if (newFocused != findFocus()) newFocused.requestFocus(direction);
    return handled;
  }
  private View findFocusableViewInBounds(boolean topFocus, int top, int bottom) {
    java.util.List<View> focusables = getFocusables(View.FOCUS_FORWARD);
    View focusCandidate = null;
    boolean foundFullyContainedFocusable = false;
    for (View view : focusables) {
      int viewTop = view.getTop(), viewBottom = view.getBottom();
      if (top < viewBottom && viewTop < bottom) {
        final boolean viewIsFullyContained = top < viewTop && viewBottom < bottom;
        if (focusCandidate == null) { focusCandidate = view; foundFullyContainedFocusable = viewIsFullyContained; }
        else {
          final boolean viewIsCloserToBoundary = (topFocus && viewTop < focusCandidate.getTop()) || (!topFocus && viewBottom > focusCandidate.getBottom());
          if (foundFullyContainedFocusable) { if (viewIsFullyContained && viewIsCloserToBoundary) focusCandidate = view; }
          else if (viewIsFullyContained) { focusCandidate = view; foundFullyContainedFocusable = true; }
          else if (viewIsCloserToBoundary) focusCandidate = view;
        }
      }
    }
    return focusCandidate;
  }
  /** moves focus to the next view in the direction when it is within half a screen, else scrolls by up to half a screen */
  public boolean arrowScroll(int direction) {
    View currentFocused = findFocus();
    if (currentFocused == this) currentFocused = null;
    View nextFocused = FocusFinder.getInstance().findNextFocus(this, currentFocused, direction);
    final int maxJump = getMaxScrollAmount();
    if (nextFocused != null && isWithinDeltaOfScreen(nextFocused, maxJump, getHeight())) {
      nextFocused.getDrawingRect(mTempRect);
      offsetDescendantRectToMyCoords(nextFocused, mTempRect);
      doScrollY(computeScrollDeltaToGetChildRectOnScreen(mTempRect));
      nextFocused.requestFocus(direction);
    } else {
      int scrollDelta = maxJump;
      if (direction == View.FOCUS_UP && mScrollY < scrollDelta) scrollDelta = mScrollY;
      else if (direction == View.FOCUS_DOWN && getChildCount() > 0) {
        int daBottom = getChildAt(0).getBottom(), screenBottom = mScrollY + getHeight() - mPaddingBottom;
        if (daBottom - screenBottom < maxJump) scrollDelta = daBottom - screenBottom;
      }
      if (scrollDelta == 0) return false;
      doScrollY(direction == View.FOCUS_DOWN ? scrollDelta : -scrollDelta);
    }
    if (currentFocused != null && currentFocused.isFocused() && isOffScreen(currentFocused)) {
      // the old focus scrolled away: take focus ourselves so it is not scrolled back to
      final int descendantFocusability = getDescendantFocusability();
      setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
      requestFocus();
      setDescendantFocusability(descendantFocusability);
    }
    return true;
  }
  private boolean isOffScreen(View descendant) { return !isWithinDeltaOfScreen(descendant, 0, getHeight()); }
  private boolean isWithinDeltaOfScreen(View descendant, int delta, int height) {
    descendant.getDrawingRect(mTempRect);
    offsetDescendantRectToMyCoords(descendant, mTempRect);
    return mTempRect.bottom + delta >= mScrollY && mTempRect.top - delta <= mScrollY + height;
  }
  private void doScrollY(int delta) { if (delta != 0) { if (mSmoothScrollingEnabled) smoothScrollBy(0, delta); else scrollBy(0, delta); } }
  /** focus into the descendants: the first in the direction that is on screen */
  @Override protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
    if (direction == View.FOCUS_FORWARD) direction = View.FOCUS_DOWN;
    else if (direction == View.FOCUS_BACKWARD) direction = View.FOCUS_UP;
    final View nextFocus = previouslyFocusedRect == null ? FocusFinder.getInstance().findNextFocus(this, null, direction)
        : FocusFinder.getInstance().findNextFocusFromRect(this, previouslyFocusedRect, direction);
    if (nextFocus == null || isOffScreen(nextFocus)) return false;
    return nextFocus.requestFocus(direction, previouslyFocusedRect);
  }
  public final void smoothScrollBy(int dx, int dy) {
    if (getChildCount() == 0) return;
    long duration = android.view.animation.AnimationUtils.currentAnimationTimeMillis() - mLastScroll;
    if (duration > ANIMATED_SCROLL_GAP) {
      final int height = getHeight() - mPaddingBottom - mPaddingTop;
      final int bottom = getChildAt(0).getHeight();
      final int maxY = Math.max(0, bottom - height);
      final int scrollY = mScrollY;
      dy = Math.max(0, Math.min(scrollY + dy, maxY)) - scrollY;
      mScroller.startScroll(mScrollX, scrollY, 0, dy);
      invalidate();
    } else {
      if (!mScroller.isFinished()) mScroller.abortAnimation();
      scrollBy(dx, dy);
    }
    mLastScroll = android.view.animation.AnimationUtils.currentAnimationTimeMillis();
  }
  public final void smoothScrollTo(int x, int y) { smoothScrollBy(x - mScrollX, y - mScrollY); }
  @Override protected int computeVerticalScrollRange() {
    final int count = getChildCount();
    final int contentHeight = getHeight() - mPaddingBottom - mPaddingTop;
    if (count == 0) return contentHeight;
    return getChildAt(0).getBottom();
  }
  @Override protected int computeVerticalScrollOffset() { return Math.max(0, super.computeVerticalScrollOffset()); }
  /** a mouse wheel: AXIS_VSCROLL units of the vertical scroll factor */
  @Override public boolean onGenericMotionEvent(android.view.MotionEvent event) {
    if (event.getAction() == android.view.MotionEvent.ACTION_SCROLL && event.isFromSource(android.view.InputDevice.SOURCE_CLASS_POINTER)) {
      int delta = Math.round(event.getAxisValue(android.view.MotionEvent.AXIS_VSCROLL) * getVerticalScrollFactor());
      if (delta != 0) {
        int range = getScrollRange(), old = mScrollY, y = Math.max(0, Math.min(range, old - delta));
        if (y != old) { super.scrollTo(mScrollX, y); return true; }
      }
    }
    return super.onGenericMotionEvent(event);
  }
  private int getScrollRange() {
    int r = 0;
    if (getChildCount() > 0) r = Math.max(0, getChildAt(0).getHeight() - (getHeight() - mPaddingBottom - mPaddingTop));
    return r;
  }
  @Override public void computeScroll() {
    if (mScroller.computeScrollOffset()) {
      int oldX = mScrollX, oldY = mScrollY;
      int x = mScroller.getCurrX(), y = mScroller.getCurrY();
      if (getChildCount() > 0) {
        View child = getChildAt(0);
        x = clamp(x, getWidth() - mPaddingRight - mPaddingLeft, child.getWidth());
        y = clamp(y, getHeight() - mPaddingBottom - mPaddingTop, child.getHeight());
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
    int delta = computeScrollDeltaToGetChildRectOnScreen(mTempRect);
    if (delta != 0) scrollBy(0, delta);
  }
  protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
    if (getChildCount() == 0) return 0;
    int height = getHeight();
    int screenTop = mScrollY, screenBottom = screenTop + height;
    int delta = 0;
    if (rect.bottom > screenBottom && rect.top > screenTop) {
      if (rect.height() > height) delta += (rect.top - screenTop); else delta += (rect.bottom - screenBottom);
      int bottom = getChildAt(0).getBottom();
      int distanceToBottom = bottom - screenBottom;
      delta = Math.min(delta, distanceToBottom);
    } else if (rect.top < screenTop && rect.bottom < screenBottom) {
      if (rect.height() > height) delta -= (screenBottom - rect.bottom); else delta -= (screenTop - rect.top);
      delta = Math.max(delta, -mScrollY);
    }
    return delta;
  }
  @Override public boolean requestChildRectangleOnScreen(View child, Rect rectangle, boolean immediate) {
    rectangle.offset(child.getLeft() - child.getScrollX(), child.getTop() - child.getScrollY());
    int delta = computeScrollDeltaToGetChildRectOnScreen(rectangle);
    if (delta != 0) { if (immediate) scrollBy(0, delta); else smoothScrollBy(0, delta); }
    return delta != 0;
  }
  @Override public void requestLayout() { mIsLayoutDirty = true; super.requestLayout(); }
  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
    super.onLayout(changed, l, t, r, b);
    mIsLayoutDirty = false;
    if (mChildToScrollTo != null) { scrollToChild(mChildToScrollTo); mChildToScrollTo = null; }
    scrollTo(mScrollX, mScrollY);
  }
  public void fling(int velocityY) {
    if (getChildCount() > 0) {
      int height = getHeight() - mPaddingBottom - mPaddingTop;
      int bottom = getChildAt(0).getHeight();
      mScroller.fling(mScrollX, mScrollY, 0, velocityY, 0, 0, 0, Math.max(0, bottom - height));
      invalidate();
    }
  }
  @Override public void scrollTo(int x, int y) {
    if (getChildCount() > 0) {
      View child = getChildAt(0);
      x = clamp(x, getWidth() - mPaddingRight - mPaddingLeft, child.getWidth());
      y = clamp(y, getHeight() - mPaddingBottom - mPaddingTop, child.getHeight());
      if (x != mScrollX || y != mScrollY) super.scrollTo(x, y);
    }
  }
  static int clamp(int n, int my, int child) {
    if (my >= child || n < 0) return 0;
    if ((my + n) > child) return child - my;
    return n;
  }
  @Override public boolean canScrollVertically(int d) { int off = computeVerticalScrollOffset(); int range = getScrollRange(); return d < 0 ? off > 0 : off < range; }
}
