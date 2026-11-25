package android.view;

import android.graphics.Rect;

/** Gives a view a larger touch area than its bounds: touches in the area its parent sees go to the delegate view. */
public class TouchDelegate {
  public static final int ABOVE = 1, BELOW = 2, TO_LEFT = 4, TO_RIGHT = 8;
  private final View mDelegateView;
  private final Rect mBounds, mSlopBounds;
  private boolean mDelegateTargeted;
  private final int mSlop;
  public TouchDelegate(Rect bounds, View delegateView) {
    mBounds = bounds;
    mSlop = ViewConfiguration.get(delegateView.getContext()).getScaledTouchSlop();
    mSlopBounds = new Rect(bounds);
    mSlopBounds.inset(-mSlop, -mSlop);
    mDelegateView = delegateView;
  }
  public boolean onTouchEvent(MotionEvent event) {
    int x = (int) event.getX(), y = (int) event.getY();
    boolean sendToDelegate = false, hit = true, handled = false;
    switch (event.getActionMasked()) {
      case MotionEvent.ACTION_DOWN: mDelegateTargeted = mBounds.contains(x, y); sendToDelegate = mDelegateTargeted; break;
      case MotionEvent.ACTION_POINTER_DOWN: case MotionEvent.ACTION_POINTER_UP: case MotionEvent.ACTION_UP: case MotionEvent.ACTION_MOVE:
        sendToDelegate = mDelegateTargeted;
        if (sendToDelegate && !mSlopBounds.contains(x, y)) hit = false;
        break;
      case MotionEvent.ACTION_CANCEL: sendToDelegate = mDelegateTargeted; mDelegateTargeted = false; break;
      default:
    }
    if (sendToDelegate) {
      MotionEvent e = MotionEvent.obtain(event);
      if (hit) e.setLocation(mDelegateView.getWidth() / 2f, mDelegateView.getHeight() / 2f);
      else { int s = mSlop; e.setLocation(-(s * 2), -(s * 2)); }
      handled = mDelegateView.dispatchTouchEvent(e);
    }
    return handled;
  }
}
