package android.view;

import android.content.Context;
import android.os.Handler;

/** Detects pinch scaling from multi-touch MotionEvents (the platform's algorithm without the quick-scale gesture's stylus mode). */
public class ScaleGestureDetector {
  public interface OnScaleGestureListener {
    boolean onScale(ScaleGestureDetector detector);
    boolean onScaleBegin(ScaleGestureDetector detector);
    void onScaleEnd(ScaleGestureDetector detector);
  }
  public static class SimpleOnScaleGestureListener implements OnScaleGestureListener {
    public boolean onScale(ScaleGestureDetector d) { return false; }
    public boolean onScaleBegin(ScaleGestureDetector d) { return true; }
    public void onScaleEnd(ScaleGestureDetector d) {}
  }

  private final Context mContext;
  private final OnScaleGestureListener mListener;
  private float mFocusX, mFocusY, mCurrSpan, mPrevSpan, mInitialSpan, mCurrSpanX, mCurrSpanY, mPrevSpanX, mPrevSpanY;
  private long mCurrTime, mPrevTime;
  private boolean mInProgress, mQuickScaleEnabled, mStylusScaleEnabled;
  private int mSpanSlop, mMinSpan;
  private float mAnchoredScaleStartX, mAnchoredScaleStartY;
  private int mAnchoredScaleMode;
  private static final int ANCHORED_SCALE_MODE_NONE = 0, ANCHORED_SCALE_MODE_DOUBLE_TAP = 1;
  private GestureDetector mGestureDetector;
  private boolean mEventBeforeOrAboveStartingGestureEvent;
  private final Handler mHandler;

  public ScaleGestureDetector(Context context, OnScaleGestureListener listener) { this(context, listener, null); }
  public ScaleGestureDetector(Context context, OnScaleGestureListener listener, Handler handler) {
    mContext = context;
    mListener = listener;
    ViewConfiguration vc = ViewConfiguration.get(context);
    mSpanSlop = vc.getScaledTouchSlop() * 2;
    mMinSpan = (int) (27 * a2w.Screen.DENSITY + 0.5f);
    mHandler = handler;
  }

  public boolean onTouchEvent(MotionEvent event) {
    mCurrTime = event.getEventTime();
    final int action = event.getActionMasked();
    if (mQuickScaleEnabled) mGestureDetector.onTouchEvent(event);
    final int count = event.getPointerCount();
    final boolean streamComplete = action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL;
    if (action == MotionEvent.ACTION_DOWN || streamComplete) {
      if (mInProgress) {
        mListener.onScaleEnd(this);
        mInProgress = false;
        mInitialSpan = 0;
        mAnchoredScaleMode = ANCHORED_SCALE_MODE_NONE;
      } else if (inAnchoredScaleMode() && streamComplete) {
        mInProgress = false;
        mInitialSpan = 0;
        mAnchoredScaleMode = ANCHORED_SCALE_MODE_NONE;
      }
      if (streamComplete) return true;
    }
    final boolean configChanged = action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_UP
        || action == MotionEvent.ACTION_POINTER_DOWN;
    final boolean pointerUp = action == MotionEvent.ACTION_POINTER_UP;
    final int skipIndex = pointerUp ? event.getActionIndex() : -1;
    float sumX = 0, sumY = 0;
    final int div = pointerUp ? count - 1 : count;
    final float focusX, focusY;
    if (inAnchoredScaleMode()) {
      focusX = mAnchoredScaleStartX;
      focusY = mAnchoredScaleStartY;
      mEventBeforeOrAboveStartingGestureEvent = event.getY() < focusY;
    } else {
      for (int i = 0; i < count; i++) { if (skipIndex == i) continue; sumX += event.getX(i); sumY += event.getY(i); }
      focusX = div > 0 ? sumX / div : 0;
      focusY = div > 0 ? sumY / div : 0;
    }
    float devSumX = 0, devSumY = 0;
    for (int i = 0; i < count; i++) { if (skipIndex == i) continue; devSumX += Math.abs(event.getX(i) - focusX); devSumY += Math.abs(event.getY(i) - focusY); }
    final float devX = div > 0 ? devSumX / div : 0, devY = div > 0 ? devSumY / div : 0;
    final float spanX = devX * 2, spanY = devY * 2;
    final float span = inAnchoredScaleMode() ? spanY : (float) Math.hypot(spanX, spanY);
    final boolean wasInProgress = mInProgress;
    mFocusX = focusX;
    mFocusY = focusY;
    if (!inAnchoredScaleMode() && mInProgress && (span < mMinSpan || configChanged)) {
      mListener.onScaleEnd(this);
      mInProgress = false;
      mInitialSpan = span;
    }
    if (configChanged) {
      mPrevSpanX = mCurrSpanX = spanX;
      mPrevSpanY = mCurrSpanY = spanY;
      mInitialSpan = mPrevSpan = mCurrSpan = span;
    }
    final int minSpan = inAnchoredScaleMode() ? mSpanSlop : mMinSpan;
    if (!mInProgress && span >= minSpan && (wasInProgress || Math.abs(span - mInitialSpan) > mSpanSlop)) {
      mPrevSpanX = mCurrSpanX = spanX;
      mPrevSpanY = mCurrSpanY = spanY;
      mPrevSpan = mCurrSpan = span;
      mPrevTime = mCurrTime;
      mInProgress = mListener.onScaleBegin(this);
    }
    if (action == MotionEvent.ACTION_MOVE) {
      mCurrSpanX = spanX;
      mCurrSpanY = spanY;
      mCurrSpan = span;
      boolean updatePrev = true;
      if (mInProgress) updatePrev = mListener.onScale(this);
      if (updatePrev) {
        mPrevSpanX = mCurrSpanX;
        mPrevSpanY = mCurrSpanY;
        mPrevSpan = mCurrSpan;
        mPrevTime = mCurrTime;
      }
    }
    return true;
  }
  private boolean inAnchoredScaleMode() { return mAnchoredScaleMode != ANCHORED_SCALE_MODE_NONE; }

  public void setQuickScaleEnabled(boolean scales) {
    mQuickScaleEnabled = scales;
    if (mQuickScaleEnabled && mGestureDetector == null) {
      GestureDetector.SimpleOnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener() {
        @Override public boolean onDoubleTap(MotionEvent e) {
          mAnchoredScaleStartX = e.getX();
          mAnchoredScaleStartY = e.getY();
          mAnchoredScaleMode = ANCHORED_SCALE_MODE_DOUBLE_TAP;
          return true;
        }
      };
      mGestureDetector = new GestureDetector(mContext, gestureListener, mHandler);
    }
  }
  public boolean isQuickScaleEnabled() { return mQuickScaleEnabled; }
  public void setStylusScaleEnabled(boolean scales) { mStylusScaleEnabled = scales; }
  public boolean isStylusScaleEnabled() { return mStylusScaleEnabled; }
  public boolean isInProgress() { return mInProgress; }
  public float getFocusX() { return mFocusX; }
  public float getFocusY() { return mFocusY; }
  public float getCurrentSpan() { return mCurrSpan; }
  public float getCurrentSpanX() { return mCurrSpanX; }
  public float getCurrentSpanY() { return mCurrSpanY; }
  public float getPreviousSpan() { return mPrevSpan; }
  public float getPreviousSpanX() { return mPrevSpanX; }
  public float getPreviousSpanY() { return mPrevSpanY; }
  public float getScaleFactor() {
    if (inAnchoredScaleMode()) {
      final boolean scaleUp = (mEventBeforeOrAboveStartingGestureEvent && (mCurrSpan < mPrevSpan))
          || (!mEventBeforeOrAboveStartingGestureEvent && (mCurrSpan > mPrevSpan));
      final float spanDiff = Math.abs(1 - (mCurrSpan / mPrevSpan)) * 0.5f;
      return mPrevSpan <= mSpanSlop ? 1 : scaleUp ? (1 + spanDiff) : (1 - spanDiff);
    }
    return mPrevSpan > 0 ? mCurrSpan / mPrevSpan : 1;
  }
  public long getTimeDelta() { return mCurrTime - mPrevTime; }
  public long getEventTime() { return mCurrTime; }
  public int getMinSpan() { return mMinSpan; }
  public void setMinSpan(int s) { mMinSpan = s; }
  public int getSpanSlop() { return mSpanSlop; }
  public void setSpanSlop(int s) { mSpanSlop = s; }
}
