package android.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

/** Detects taps, double taps, long presses, scrolls and flings from a stream of MotionEvents (as the platform does). */
public class GestureDetector {
  public interface OnGestureListener {
    boolean onDown(MotionEvent e);
    void onShowPress(MotionEvent e);
    boolean onSingleTapUp(MotionEvent e);
    boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY);
    void onLongPress(MotionEvent e);
    boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY);
  }
  public interface OnDoubleTapListener {
    boolean onSingleTapConfirmed(MotionEvent e);
    boolean onDoubleTap(MotionEvent e);
    boolean onDoubleTapEvent(MotionEvent e);
  }
  public interface OnContextClickListener { boolean onContextClick(MotionEvent e); }
  public static class SimpleOnGestureListener implements OnGestureListener, OnDoubleTapListener, OnContextClickListener {
    public boolean onSingleTapUp(MotionEvent e) { return false; }
    public void onLongPress(MotionEvent e) {}
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float dx, float dy) { return false; }
    public boolean onFling(MotionEvent e1, MotionEvent e2, float vx, float vy) { return false; }
    public void onShowPress(MotionEvent e) {}
    public boolean onDown(MotionEvent e) { return false; }
    public boolean onDoubleTap(MotionEvent e) { return false; }
    public boolean onDoubleTapEvent(MotionEvent e) { return false; }
    public boolean onSingleTapConfirmed(MotionEvent e) { return false; }
    public boolean onContextClick(MotionEvent e) { return false; }
  }

  private static final int SHOW_PRESS = 1, LONG_PRESS = 2, TAP = 3;
  private static final int TAP_TIMEOUT = ViewConfiguration.getTapTimeout(), DOUBLE_TAP_TIMEOUT = ViewConfiguration.getDoubleTapTimeout(),
      DOUBLE_TAP_MIN_TIME = 40, LONGPRESS_TIMEOUT = ViewConfiguration.getLongPressTimeout();

  private int mTouchSlopSquare, mDoubleTapTouchSlopSquare, mDoubleTapSlopSquare, mMinimumFlingVelocity, mMaximumFlingVelocity;
  private final Handler mHandler;
  private final OnGestureListener mListener;
  private OnDoubleTapListener mDoubleTapListener;
  private OnContextClickListener mContextClickListener;
  private boolean mStillDown, mDeferConfirmSingleTap, mInLongPress, mInContextClick, mAlwaysInTapRegion, mAlwaysInBiggerTapRegion,
      mIgnoreNextUpEvent, mIsDoubleTapping, mIsLongpressEnabled = true;
  private MotionEvent mCurrentDownEvent, mPreviousUpEvent;
  private float mLastFocusX, mLastFocusY, mDownFocusX, mDownFocusY;
  private VelocityTracker mVelocityTracker;

  private final class GestureHandler extends Handler {
    GestureHandler() { super(); }
    GestureHandler(Handler h) { super(h.getLooper()); }
    @Override public void handleMessage(Message msg) {
      switch (msg.what) {
        case SHOW_PRESS: if (mCurrentDownEvent != null) mListener.onShowPress(mCurrentDownEvent); break;
        case LONG_PRESS: dispatchLongPress(); break;
        case TAP:
          if (mDoubleTapListener != null) {
            if (!mStillDown) mDoubleTapListener.onSingleTapConfirmed(mCurrentDownEvent);
            else mDeferConfirmSingleTap = true;
          }
          break;
        default:
      }
    }
  }

  @Deprecated public GestureDetector(OnGestureListener listener, Handler handler) { this(null, listener, handler); }
  @Deprecated public GestureDetector(OnGestureListener listener) { this(null, listener, null); }
  public GestureDetector(Context context, OnGestureListener listener) { this(context, listener, null); }
  public GestureDetector(Context context, OnGestureListener listener, Handler handler) {
    mHandler = handler != null ? new GestureHandler(handler) : new GestureHandler();
    mListener = listener;
    if (listener instanceof OnDoubleTapListener) setOnDoubleTapListener((OnDoubleTapListener) listener);
    if (listener instanceof OnContextClickListener) setContextClickListener((OnContextClickListener) listener);
    init(context);
  }
  public GestureDetector(Context context, OnGestureListener listener, Handler handler, boolean unused) { this(context, listener, handler); }

  private void init(Context context) {
    if (mListener == null) throw new NullPointerException("OnGestureListener must not be null");
    ViewConfiguration c = ViewConfiguration.get(context);
    int touchSlop = c.getScaledTouchSlop(), doubleTapTouchSlop = touchSlop, doubleTapSlop = c.getScaledDoubleTapSlop();
    mMinimumFlingVelocity = c.getScaledMinimumFlingVelocity();
    mMaximumFlingVelocity = c.getScaledMaximumFlingVelocity();
    mTouchSlopSquare = touchSlop * touchSlop;
    mDoubleTapTouchSlopSquare = doubleTapTouchSlop * doubleTapTouchSlop;
    mDoubleTapSlopSquare = doubleTapSlop * doubleTapSlop;
  }
  public void setOnDoubleTapListener(OnDoubleTapListener l) { mDoubleTapListener = l; }
  public void setContextClickListener(OnContextClickListener l) { mContextClickListener = l; }
  public void setIsLongpressEnabled(boolean e) { mIsLongpressEnabled = e; }
  public boolean isLongpressEnabled() { return mIsLongpressEnabled; }

  public boolean onTouchEvent(MotionEvent ev) {
    final int action = ev.getAction();
    if (mVelocityTracker == null) mVelocityTracker = VelocityTracker.obtain();
    mVelocityTracker.addMovement(ev);
    final boolean pointerUp = (action & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_POINTER_UP;
    final int skipIndex = pointerUp ? ev.getActionIndex() : -1;
    float sumX = 0, sumY = 0;
    final int count = ev.getPointerCount();
    for (int i = 0; i < count; i++) { if (skipIndex == i) continue; sumX += ev.getX(i); sumY += ev.getY(i); }
    final int div = pointerUp ? count - 1 : count;
    final float focusX = div > 0 ? sumX / div : 0, focusY = div > 0 ? sumY / div : 0;
    boolean handled = false;
    switch (action & MotionEvent.ACTION_MASK) {
      case MotionEvent.ACTION_POINTER_DOWN:
        mDownFocusX = mLastFocusX = focusX;
        mDownFocusY = mLastFocusY = focusY;
        cancelTaps();
        break;
      case MotionEvent.ACTION_POINTER_UP: {
        mDownFocusX = mLastFocusX = focusX;
        mDownFocusY = mLastFocusY = focusY;
        mVelocityTracker.computeCurrentVelocity(1000, mMaximumFlingVelocity);
        break;
      }
      case MotionEvent.ACTION_DOWN:
        if (mDoubleTapListener != null) {
          boolean hadTapMessage = mHandler.hasMessages(TAP);
          if (hadTapMessage) mHandler.removeMessages(TAP);
          if (mCurrentDownEvent != null && mPreviousUpEvent != null && hadTapMessage && isConsideredDoubleTap(mCurrentDownEvent, mPreviousUpEvent, ev)) {
            mIsDoubleTapping = true;
            handled |= mDoubleTapListener.onDoubleTap(mCurrentDownEvent);
            handled |= mDoubleTapListener.onDoubleTapEvent(ev);
          } else mHandler.sendEmptyMessageDelayed(TAP, DOUBLE_TAP_TIMEOUT);
        }
        mDownFocusX = mLastFocusX = focusX;
        mDownFocusY = mLastFocusY = focusY;
        if (mCurrentDownEvent != null) mCurrentDownEvent.recycle();
        mCurrentDownEvent = MotionEvent.obtain(ev);
        mAlwaysInTapRegion = true;
        mAlwaysInBiggerTapRegion = true;
        mStillDown = true;
        mInLongPress = false;
        mDeferConfirmSingleTap = false;
        if (mIsLongpressEnabled) {
          mHandler.removeMessages(LONG_PRESS);
          mHandler.sendEmptyMessageAtTime(LONG_PRESS, mCurrentDownEvent.getDownTime() + LONGPRESS_TIMEOUT);
        }
        mHandler.sendEmptyMessageAtTime(SHOW_PRESS, mCurrentDownEvent.getDownTime() + TAP_TIMEOUT);
        handled |= mListener.onDown(ev);
        break;
      case MotionEvent.ACTION_MOVE: {
        if (mInLongPress || mInContextClick) break;
        final float scrollX = mLastFocusX - focusX, scrollY = mLastFocusY - focusY;
        if (mIsDoubleTapping) handled |= mDoubleTapListener.onDoubleTapEvent(ev);
        else if (mAlwaysInTapRegion) {
          final int dx = (int) (focusX - mDownFocusX), dy = (int) (focusY - mDownFocusY);
          int distance = dx * dx + dy * dy;
          if (distance > mTouchSlopSquare) {
            handled = mListener.onScroll(mCurrentDownEvent, ev, scrollX, scrollY);
            mLastFocusX = focusX;
            mLastFocusY = focusY;
            mAlwaysInTapRegion = false;
            mHandler.removeMessages(TAP);
            mHandler.removeMessages(SHOW_PRESS);
            mHandler.removeMessages(LONG_PRESS);
          }
          if (distance > mDoubleTapTouchSlopSquare) mAlwaysInBiggerTapRegion = false;
        } else if (Math.abs(scrollX) >= 1 || Math.abs(scrollY) >= 1) {
          handled = mListener.onScroll(mCurrentDownEvent, ev, scrollX, scrollY);
          mLastFocusX = focusX;
          mLastFocusY = focusY;
        }
        break;
      }
      case MotionEvent.ACTION_UP: {
        mStillDown = false;
        MotionEvent currentUpEvent = MotionEvent.obtain(ev);
        if (mIsDoubleTapping) handled |= mDoubleTapListener.onDoubleTapEvent(ev);
        else if (mInLongPress) { mHandler.removeMessages(TAP); mInLongPress = false; }
        else if (mAlwaysInTapRegion && !mIgnoreNextUpEvent) {
          handled = mListener.onSingleTapUp(ev);
          if (mDeferConfirmSingleTap && mDoubleTapListener != null) mDoubleTapListener.onSingleTapConfirmed(ev);
        } else if (!mIgnoreNextUpEvent) {
          final VelocityTracker vt = mVelocityTracker;
          final int pointerId = ev.getPointerId(0);
          vt.computeCurrentVelocity(1000, mMaximumFlingVelocity);
          final float vy = vt.getYVelocity(pointerId), vx = vt.getXVelocity(pointerId);
          if (Math.abs(vy) > mMinimumFlingVelocity || Math.abs(vx) > mMinimumFlingVelocity)
            handled = mListener.onFling(mCurrentDownEvent, ev, vx, vy);
        }
        if (mPreviousUpEvent != null) mPreviousUpEvent.recycle();
        mPreviousUpEvent = currentUpEvent;
        if (mVelocityTracker != null) { mVelocityTracker.recycle(); mVelocityTracker = null; }
        mIsDoubleTapping = false;
        mDeferConfirmSingleTap = false;
        mIgnoreNextUpEvent = false;
        mHandler.removeMessages(SHOW_PRESS);
        mHandler.removeMessages(LONG_PRESS);
        break;
      }
      case MotionEvent.ACTION_CANCEL:
        cancel();
        break;
      default:
    }
    return handled;
  }
  public boolean onGenericMotionEvent(MotionEvent ev) { return false; }

  private void cancel() {
    mHandler.removeMessages(SHOW_PRESS);
    mHandler.removeMessages(LONG_PRESS);
    mHandler.removeMessages(TAP);
    if (mVelocityTracker != null) { mVelocityTracker.recycle(); mVelocityTracker = null; }
    mIsDoubleTapping = false;
    mStillDown = false;
    mAlwaysInTapRegion = false;
    mAlwaysInBiggerTapRegion = false;
    mDeferConfirmSingleTap = false;
    mInLongPress = false;
    mInContextClick = false;
    mIgnoreNextUpEvent = false;
  }
  private void cancelTaps() {
    mHandler.removeMessages(SHOW_PRESS);
    mHandler.removeMessages(LONG_PRESS);
    mHandler.removeMessages(TAP);
    mIsDoubleTapping = false;
    mAlwaysInTapRegion = false;
    mAlwaysInBiggerTapRegion = false;
    mDeferConfirmSingleTap = false;
    mInLongPress = false;
    mInContextClick = false;
    mIgnoreNextUpEvent = false;
  }
  private boolean isConsideredDoubleTap(MotionEvent firstDown, MotionEvent firstUp, MotionEvent secondDown) {
    if (!mAlwaysInBiggerTapRegion) return false;
    final long deltaTime = secondDown.getEventTime() - firstUp.getEventTime();
    if (deltaTime > DOUBLE_TAP_TIMEOUT || deltaTime < DOUBLE_TAP_MIN_TIME) return false;
    int dx = (int) firstDown.getX() - (int) secondDown.getX(), dy = (int) firstDown.getY() - (int) secondDown.getY();
    return dx * dx + dy * dy < mDoubleTapSlopSquare;
  }
  private void dispatchLongPress() {
    mHandler.removeMessages(TAP);
    mDeferConfirmSingleTap = false;
    mInLongPress = true;
    if (mCurrentDownEvent != null) mListener.onLongPress(mCurrentDownEvent);
  }
}
