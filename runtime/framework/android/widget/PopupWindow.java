package android.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/** A floating window over the activity: a panel window placed at a location or below (or above) an anchor view. */
public class PopupWindow {
  public interface OnDismissListener { void onDismiss(); }
  public static final int INPUT_METHOD_FROM_FOCUSABLE = 0, INPUT_METHOD_NEEDED = 1, INPUT_METHOD_NOT_NEEDED = 2;

  private Context mContext;
  private View mContentView;
  private PopupDecorView mDecorView;
  private Drawable mBackground;
  private int mWidth = ViewGroup.LayoutParams.WRAP_CONTENT, mHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
  private int mWidthMode, mHeightMode;
  private boolean mShowing, mFocusable, mTouchable = true, mOutsideTouchable, mClippingEnabled = true, mAboveAnchor,
      mOverlapAnchor, mSplitTouchEnabled, mLayoutInScreen, mAttachedInDecor = true, mIgnoreCheekPress;
  private int mInputMethodMode = INPUT_METHOD_FROM_FOCUSABLE, mSoftInputMode, mAnimationStyle = -1, mWindowLayoutType = WindowManager.LayoutParams.TYPE_APPLICATION_PANEL;
  private float mElevation;
  private OnDismissListener mOnDismissListener;
  private View.OnTouchListener mTouchInterceptor;
  private View mAnchor;
  private int mAnchorXoff, mAnchorYoff, mAnchoredGravity;
  private WindowManager.LayoutParams mParams;
  private Rect mEpicenterBounds;

  public PopupWindow(Context context) { this(context, null); }
  public PopupWindow(Context context, AttributeSet attrs) { this(context, attrs, 0); }
  public PopupWindow(Context context, AttributeSet attrs, int defStyleAttr) { this(context, attrs, defStyleAttr, 0); }
  public PopupWindow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    mContext = context;
    if (context != null) {
      android.content.res.TypedArray a = context.obtainStyledAttributes(attrs, new int[] {android.R.attr.popupBackground, android.R.attr.popupElevation}, defStyleAttr, defStyleRes);
      Drawable bg = a.getDrawable(0);
      mElevation = a.getDimension(1, 0);
      a.recycle();
      mBackground = bg;
    }
  }
  public PopupWindow() { this(null, 0, 0); }
  public PopupWindow(View contentView) { this(contentView, 0, 0); }
  public PopupWindow(int width, int height) { this(null, width, height); }
  public PopupWindow(View contentView, int width, int height) { this(contentView, width, height, false); }
  public PopupWindow(View contentView, int width, int height, boolean focusable) {
    if (contentView != null) { mContext = contentView.getContext(); }
    setContentView(contentView);
    setWidth(width);
    setHeight(height);
    setFocusable(focusable);
  }

  public Drawable getBackground() { return mBackground; }
  public void setBackgroundDrawable(Drawable background) { mBackground = background; if (mDecorView != null) mDecorView.setBackgroundDrawable(background); }
  public float getElevation() { return mElevation; }
  public void setElevation(float e) { mElevation = e; }
  public int getAnimationStyle() { return mAnimationStyle; }
  public void setAnimationStyle(int s) { mAnimationStyle = s; }
  public void setIgnoreCheekPress() { mIgnoreCheekPress = true; }
  public View getContentView() { return mContentView; }
  public void setContentView(View contentView) {
    if (isShowing()) return;
    mContentView = contentView;
    if (mContext == null && contentView != null) mContext = contentView.getContext();
  }
  public void setTouchInterceptor(View.OnTouchListener l) { mTouchInterceptor = l; }
  public boolean isFocusable() { return mFocusable; }
  public void setFocusable(boolean focusable) { mFocusable = focusable; }
  public int getInputMethodMode() { return mInputMethodMode; }
  public void setInputMethodMode(int mode) { mInputMethodMode = mode; }
  public void setSoftInputMode(int mode) { mSoftInputMode = mode; }
  public int getSoftInputMode() { return mSoftInputMode; }
  public boolean isTouchable() { return mTouchable; }
  public void setTouchable(boolean touchable) { mTouchable = touchable; }
  public boolean isOutsideTouchable() { return mOutsideTouchable; }
  public void setOutsideTouchable(boolean touchable) { mOutsideTouchable = touchable; }
  public boolean isClippingEnabled() { return mClippingEnabled; }
  public void setClippingEnabled(boolean enabled) { mClippingEnabled = enabled; }
  public boolean isClippedToScreen() { return mClippingEnabled; }
  public void setIsClippedToScreen(boolean enabled) { mClippingEnabled = enabled; }
  public boolean isSplitTouchEnabled() { return mSplitTouchEnabled; }
  public void setSplitTouchEnabled(boolean enabled) { mSplitTouchEnabled = enabled; }
  public boolean isLayoutInScreenEnabled() { return mLayoutInScreen; }
  public void setLayoutInScreenEnabled(boolean enabled) { mLayoutInScreen = enabled; }
  public boolean isLaidOutInScreen() { return mLayoutInScreen; }
  public void setIsLaidOutInScreen(boolean enabled) { mLayoutInScreen = enabled; }
  public boolean isAttachedInDecor() { return mAttachedInDecor; }
  public void setAttachedInDecor(boolean enabled) { mAttachedInDecor = enabled; }
  public void setLayoutInsetDecor(boolean enabled) {}
  public void setWindowLayoutType(int type) { mWindowLayoutType = type; }
  public int getWindowLayoutType() { return mWindowLayoutType; }
  public void setTouchModal(boolean touchModal) {}
  public void setWindowLayoutMode(int widthSpec, int heightSpec) { mWidthMode = widthSpec; mHeightMode = heightSpec; }
  public int getHeight() { return mHeight; }
  public void setHeight(int height) { mHeight = height; }
  public int getWidth() { return mWidth; }
  public void setWidth(int width) { mWidth = width; }
  public void setOverlapAnchor(boolean overlap) { mOverlapAnchor = overlap; }
  public boolean getOverlapAnchor() { return mOverlapAnchor; }
  public boolean isShowing() { return mShowing; }
  public boolean isAboveAnchor() { return mAboveAnchor; }
  public void setEpicenterBounds(Rect bounds) { mEpicenterBounds = bounds; }
  public Rect getEpicenterBounds() { return mEpicenterBounds; }
  public void setOnDismissListener(OnDismissListener l) { mOnDismissListener = l; }
  private android.transition.Transition mEnter, mExit;
  public void setEnterTransition(android.transition.Transition t) { mEnter = t; }
  public void setExitTransition(android.transition.Transition t) { mExit = t; }
  public android.transition.Transition getEnterTransition() { return mEnter; }
  public android.transition.Transition getExitTransition() { return mExit; }

  public void showAtLocation(View parent, int gravity, int x, int y) {
    showAtLocation(parent != null ? parent.getWindowToken() : null, gravity, x, y);
  }
  public void showAtLocation(IBinder token, int gravity, int x, int y) {
    if (isShowing() || mContentView == null) return;
    mAnchor = null;
    WindowManager.LayoutParams p = createParams();
    p.gravity = gravity == Gravity.NO_GRAVITY ? Gravity.TOP | Gravity.START : gravity;
    p.x = x;
    p.y = y;
    invokePopup(p);
  }
  public void showAsDropDown(View anchor) { showAsDropDown(anchor, 0, 0); }
  public void showAsDropDown(View anchor, int xoff, int yoff) { showAsDropDown(anchor, xoff, yoff, Gravity.TOP | Gravity.START); }
  public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
    if (isShowing() || mContentView == null || anchor == null) return;
    mAnchor = anchor; mAnchorXoff = xoff; mAnchorYoff = yoff; mAnchoredGravity = gravity;
    WindowManager.LayoutParams p = createParams();
    prepare();
    findDropDownPosition(anchor, p, xoff, yoff, gravity);
    invokePopup(p);
  }

  private WindowManager.LayoutParams createParams() {
    WindowManager.LayoutParams p = new WindowManager.LayoutParams();
    p.type = mWindowLayoutType;
    p.gravity = Gravity.TOP | Gravity.START;
    p.width = mWidthMode != 0 ? mWidthMode : mWidth;
    p.height = mHeightMode != 0 ? mHeightMode : mHeight;
    p.format = android.graphics.PixelFormat.TRANSLUCENT;
    p.flags = computeFlags(p.flags);
    p.softInputMode = mSoftInputMode;
    p.setTitle("PopupWindow:" + Integer.toHexString(hashCode()));
    mParams = p;
    return p;
  }
  private int computeFlags(int f) {
    f &= ~(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);
    if (!mFocusable) f |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
    if (!mTouchable) f |= WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
    if (mOutsideTouchable) f |= WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
    return f;
  }
  private void prepare() {
    if (mDecorView != null) return;
    mDecorView = new PopupDecorView(mContext);
    if (mBackground != null) mDecorView.setBackgroundDrawable(mBackground);
    ViewGroup.LayoutParams clp = mContentView.getLayoutParams();
    int h = clp != null && clp.height == ViewGroup.LayoutParams.WRAP_CONTENT ? ViewGroup.LayoutParams.WRAP_CONTENT : ViewGroup.LayoutParams.MATCH_PARENT;
    if (mContentView.getParent() instanceof ViewGroup) ((ViewGroup) mContentView.getParent()).removeView(mContentView);
    mDecorView.addView(mContentView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, h));
  }
  private void invokePopup(WindowManager.LayoutParams p) {
    prepare();
    mShowing = true;
    WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
    wm.addView(mDecorView, p);
  }
  /** below the anchor (or above it when it does not fit below); returns whether it went above */
  private boolean findDropDownPosition(View anchor, WindowManager.LayoutParams p, int xoff, int yoff, int gravity) {
    int[] loc = new int[2];
    anchor.getLocationOnScreen(loc);
    int anchorH = anchor.getHeight(), anchorW = anchor.getWidth();
    int sw = a2w.Screen.W, sh = a2w.Screen.H;
    int popupW = p.width, popupH = p.height;
    if (popupW < 0 || popupH < 0) {
      int ws = popupW >= 0 ? View.MeasureSpec.makeMeasureSpec(popupW, View.MeasureSpec.EXACTLY)
          : popupW == ViewGroup.LayoutParams.MATCH_PARENT ? View.MeasureSpec.makeMeasureSpec(sw, View.MeasureSpec.EXACTLY) : View.MeasureSpec.makeMeasureSpec(sw, View.MeasureSpec.AT_MOST);
      int hs = popupH >= 0 ? View.MeasureSpec.makeMeasureSpec(popupH, View.MeasureSpec.EXACTLY) : View.MeasureSpec.makeMeasureSpec(sh, View.MeasureSpec.AT_MOST);
      mDecorView.measure(ws, hs);
      if (popupW < 0) popupW = mDecorView.getMeasuredWidth();
      if (popupH < 0) popupH = mDecorView.getMeasuredHeight();
    }
    int hg = Gravity.getAbsoluteGravity(gravity, anchor.getLayoutDirection()) & Gravity.HORIZONTAL_GRAVITY_MASK;
    int x = loc[0] + xoff;
    if (hg == Gravity.RIGHT) x -= popupW - anchorW;
    int y = loc[1] + (mOverlapAnchor ? 0 : anchorH) + yoff;
    boolean above = false;
    if (y + popupH > sh && loc[1] - popupH - yoff >= 0) { y = loc[1] - popupH + (mOverlapAnchor ? anchorH : 0) - yoff; above = true; }
    if (mClippingEnabled) {
      x = Math.max(0, Math.min(x, sw - popupW));
      y = Math.max(0, Math.min(y, sh - popupH));
    }
    p.gravity = Gravity.TOP | Gravity.LEFT;
    p.x = x;
    p.y = y;
    mAboveAnchor = above;
    if (mDecorView != null) mDecorView.refreshDrawableState();
    return above;
  }
  public int getMaxAvailableHeight(View anchor) { return getMaxAvailableHeight(anchor, 0); }
  public int getMaxAvailableHeight(View anchor, int yOffset) { return getMaxAvailableHeight(anchor, yOffset, false); }
  public int getMaxAvailableHeight(View anchor, int yOffset, boolean ignoreBottomDecorations) {
    int[] loc = new int[2];
    anchor.getLocationOnScreen(loc);
    int below = a2w.Screen.H - (loc[1] + anchor.getHeight()) - yOffset, above = loc[1] - yOffset;
    int h = Math.max(below, above);
    if (mBackground != null) { Rect pad = new Rect(); mBackground.getPadding(pad); h -= pad.top + pad.bottom; }
    return h;
  }

  public void dismiss() {
    if (!mShowing) return;
    mShowing = false;
    try {
      WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
      wm.removeViewImmediate(mDecorView);
    } catch (RuntimeException e) { /* already gone */ }
    if (mDecorView != null && mContentView != null && mContentView.getParent() == mDecorView) mDecorView.removeView(mContentView);
    mDecorView = null;
    if (mOnDismissListener != null) mOnDismissListener.onDismiss();
  }

  public void update() {
    if (!isShowing() || mParams == null) return;
    int f = computeFlags(mParams.flags);
    if (f != mParams.flags) { mParams.flags = f; relayout(); }
  }
  public void update(int width, int height) { if (mParams != null) update(mParams.x, mParams.y, width, height); }
  public void update(int x, int y, int width, int height) { update(x, y, width, height, false); }
  public void update(int x, int y, int width, int height, boolean force) {
    if (width >= 0) setWidth(width);
    if (height >= 0) setHeight(height);
    if (!isShowing() || mParams == null) return;
    if (width != -1) mParams.width = width;
    if (height != -1) mParams.height = height;
    if (mAnchor == null) { mParams.x = x; mParams.y = y; }
    mParams.flags = computeFlags(mParams.flags);
    relayout();
  }
  public void update(View anchor, int width, int height) { update(anchor, false, 0, 0, width, height); }
  public void update(View anchor, int xoff, int yoff, int width, int height) { update(anchor, true, xoff, yoff, width, height); }
  private void update(View anchor, boolean updateLocation, int xoff, int yoff, int width, int height) {
    if (!isShowing() || mParams == null || anchor == null) return;
    if (width >= 0) mParams.width = width;
    if (height >= 0) mParams.height = height;
    if (updateLocation) { mAnchorXoff = xoff; mAnchorYoff = yoff; }
    mAnchor = anchor;
    findDropDownPosition(anchor, mParams, mAnchorXoff, mAnchorYoff, mAnchoredGravity);
    relayout();
  }
  private void relayout() {
    WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
    wm.updateViewLayout(mDecorView, mParams);
  }

  /** the popup's root: its background, outside touches and Back dismiss it */
  private final class PopupDecorView extends FrameLayout {
    PopupDecorView(Context c) { super(c); }
    @Override public boolean dispatchKeyEvent(KeyEvent event) {
      if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
        if (event.getAction() == KeyEvent.ACTION_UP && !event.isCanceled()) dismiss();
        return true;
      }
      return super.dispatchKeyEvent(event);
    }
    @Override public boolean dispatchTouchEvent(MotionEvent ev) {
      if (mTouchInterceptor != null && mTouchInterceptor.onTouch(this, ev)) return true;
      return super.dispatchTouchEvent(ev);
    }
    @Override public boolean onTouchEvent(MotionEvent event) {
      final int x = (int) event.getX(), y = (int) event.getY();
      if (event.getAction() == MotionEvent.ACTION_DOWN && (x < 0 || x >= getWidth() || y < 0 || y >= getHeight())) { dismiss(); return true; }
      if (event.getAction() == MotionEvent.ACTION_OUTSIDE) { dismiss(); return true; }
      return super.onTouchEvent(event);
    }
    @Override protected int[] onCreateDrawableState(int extraSpace) {
      if (mAboveAnchor) {
        int[] s = super.onCreateDrawableState(extraSpace + 1);
        return mergeDrawableStates(s, new int[] {android.R.attr.state_above_anchor});
      }
      return super.onCreateDrawableState(extraSpace);
    }
  }
}
