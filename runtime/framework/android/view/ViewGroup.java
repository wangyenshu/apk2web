package android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.LayoutAnimationController;
import android.view.animation.Transformation;
import a2w.Attrs;
import a2w.Values;
import java.util.ArrayList;

public abstract class ViewGroup extends View implements ViewParent, ViewManager {
  public static final int FOCUS_BEFORE_DESCENDANTS = 0x20000, FOCUS_AFTER_DESCENDANTS = 0x40000, FOCUS_BLOCK_DESCENDANTS = 0x60000;
  public static final int PERSISTENT_NO_CACHE = 0, PERSISTENT_ANIMATION_CACHE = 1, PERSISTENT_SCROLLING_CACHE = 2, PERSISTENT_ALL_CACHES = 3;
  static final int FLAG_DISALLOW_INTERCEPT = 0x80000;

  public interface OnHierarchyChangeListener {
    void onChildViewAdded(View parent, View child);
    void onChildViewRemoved(View parent, View child);
  }

  public static class LayoutParams {
    public static final int FILL_PARENT = -1, MATCH_PARENT = -1, WRAP_CONTENT = -2;
    public int width, height;
    public LayoutAnimationController.AnimationParameters layoutAnimationParameters;
    public LayoutParams(Context c, AttributeSet attrs) {
      if (attrs instanceof Attrs) {
        Attrs a = (Attrs) attrs;
        width = Values.layoutSize(a.a("layout_width"));
        height = Values.layoutSize(a.a("layout_height"));
      } else {
        width = Values.layoutSize(attrs == null ? null : attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "layout_width"));
        height = Values.layoutSize(attrs == null ? null : attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "layout_height"));
      }
    }
    public LayoutParams(int w, int h) { width = w; height = h; }
    public LayoutParams(LayoutParams s) { width = s.width; height = s.height; }
    LayoutParams() {}
    protected void setBaseAttributes(TypedArray a, int wAttr, int hAttr) { width = a.getLayoutDimension(wAttr, "layout_width"); height = a.getLayoutDimension(hAttr, "layout_height"); }
    public void resolveLayoutDirection(int d) {}
    protected static String sizeToString(int s) { return s == WRAP_CONTENT ? "wrap-content" : s == MATCH_PARENT ? "match-parent" : String.valueOf(s); }
    public String toString() { return "LayoutParams{" + sizeToString(width) + ", " + sizeToString(height) + "}"; }
  }

  public static class MarginLayoutParams extends LayoutParams {
    public int leftMargin, topMargin, rightMargin, bottomMargin;
    public MarginLayoutParams(Context c, AttributeSet attrs) {
      super(c, attrs);
      if (attrs instanceof Attrs) {
        Attrs a = (Attrs) attrs;
        String m = a.a("layout_margin");
        if (m != null) { int v = (int) Values.dimension(resolveDim(m)); leftMargin = topMargin = rightMargin = bottomMargin = v; }
        else {
          String h = a.a("layout_marginHorizontal"), v = a.a("layout_marginVertical");
          if (h != null) leftMargin = rightMargin = px(h, 0);
          else {
            leftMargin = px(a.a("layout_marginLeft"), px(a.a("layout_marginStart"), 0));
            rightMargin = px(a.a("layout_marginRight"), px(a.a("layout_marginEnd"), 0));
          }
          if (v != null) topMargin = bottomMargin = px(v, 0);
          else {
            topMargin = px(a.a("layout_marginTop"), 0);
            bottomMargin = px(a.a("layout_marginBottom"), 0);
          }
        }
      }
    }
    static String resolveDim(String v) { return Values.isRef(v) ? a2w.Res.dimen(Values.refId(v)) : v; }
    static int px(String v, int def) { return v == null ? def : (int) Values.dimension(resolveDim(v)); }
    public MarginLayoutParams(int w, int h) { super(w, h); }
    public MarginLayoutParams(MarginLayoutParams s) { super(s); leftMargin = s.leftMargin; topMargin = s.topMargin; rightMargin = s.rightMargin; bottomMargin = s.bottomMargin; }
    public MarginLayoutParams(LayoutParams s) { super(s); }
    public void setMargins(int l, int t, int r, int b) { leftMargin = l; topMargin = t; rightMargin = r; bottomMargin = b; }
    public void setMarginStart(int s) { leftMargin = s; }
    public void setMarginEnd(int e) { rightMargin = e; }
    public int getMarginStart() { return leftMargin; }
    public int getMarginEnd() { return rightMargin; }
  }

  View[] mChildren = new View[12];
  int mChildrenCount;
  boolean mClipChildren = true, mClipToPadding = true, mStaticTransformationsEnabled, mAddStatesFromChildren, mChildrenDrawingOrderEnabled;
  int mGroupFlags;
  int mDescendantFocusability = FOCUS_BEFORE_DESCENDANTS;
  View mFocused;
  OnHierarchyChangeListener mOnHierarchyChangeListener;
  final Transformation mChildTransformation = new Transformation(), mChildTransformation2 = new Transformation();
  private TouchTarget mFirstTouchTarget;
  LayoutAnimationController mLayoutAnimation;
  Animation.AnimationListener mAnimationListener;

  public ViewGroup(Context c) { super(c); }
  public ViewGroup(Context c, AttributeSet a) { this(c, a, 0); }
  public ViewGroup(Context c, AttributeSet a, int defStyleAttr, int defStyleRes) { this(c, a, defStyleAttr); }
  public ViewGroup(Context c, AttributeSet a, int defStyle) {
    super(c, a, defStyle);
    if (a instanceof Attrs) {
      Attrs at = (Attrs) a;
      String v;
      if ((v = at.a("clipChildren")) != null) mClipChildren = v.equals("true");
      if ((v = at.a("layoutAnimation")) != null && a2w.Values.isRef(v)) {
        try { setLayoutAnimation(android.view.animation.AnimationUtils.loadLayoutAnimation(c, a2w.Values.refId(v))); } catch (RuntimeException e) { android.util.Log.w("ViewGroup", "layoutAnimation: " + e); }
      }
      if ((v = at.a("animateLayoutChanges")) != null && v.equals("true")) setLayoutTransition(new android.animation.LayoutTransition());
      if ((v = at.a("clipToPadding")) != null) mClipToPadding = v.equals("true");
      if ((v = at.a("addStatesFromChildren")) != null) mAddStatesFromChildren = v.equals("true");
      if ((v = at.a("descendantFocusability")) != null)
        mDescendantFocusability = v.equals("blocksDescendants") ? FOCUS_BLOCK_DESCENDANTS : v.equals("afterDescendants") ? FOCUS_AFTER_DESCENDANTS : FOCUS_BEFORE_DESCENDANTS;
    }
  }

  // ---------------------------------------------------------------- children
  public int getChildCount() { return mChildrenCount; }
  public View getChildAt(int i) { return i < 0 || i >= mChildrenCount ? null : mChildren[i]; }
  public int indexOfChild(View c) { for (int i = 0; i < mChildrenCount; i++) if (mChildren[i] == c) return i; return -1; }
  protected int getChildDrawingOrder(int count, int i) { return i; }
  protected void setChildrenDrawingOrderEnabled(boolean e) { mChildrenDrawingOrderEnabled = e; }
  protected boolean isChildrenDrawingOrderEnabled() { return mChildrenDrawingOrderEnabled; }
  public void setOnHierarchyChangeListener(OnHierarchyChangeListener l) { mOnHierarchyChangeListener = l; }

  public void addView(View child) { addView(child, -1); }
  public void addView(View child, int index) {
    LayoutParams p = child.getLayoutParams();
    if (p == null) { p = generateDefaultLayoutParams(); if (p == null) throw new IllegalArgumentException("generateDefaultLayoutParams() cannot return null"); }
    addView(child, index, p);
  }
  public void addView(View child, int w, int h) { LayoutParams p = generateDefaultLayoutParams(); p.width = w; p.height = h; addView(child, -1, p); }
  public void addView(View child, LayoutParams p) { addView(child, -1, p); }
  public void addView(View child, int index, LayoutParams p) {
    requestLayout();
    invalidate();
    addViewInner(child, index, p, false);
  }
  public void updateViewLayout(View v, LayoutParams p) {
    if (!checkLayoutParams(p)) throw new IllegalArgumentException("Invalid LayoutParams supplied to " + this);
    if (v.mParent != this) throw new IllegalArgumentException("Given view not a child of " + this);
    v.setLayoutParams(p);
  }
  protected boolean addViewInLayout(View child, int index, LayoutParams p) { return addViewInLayout(child, index, p, false); }
  protected boolean addViewInLayout(View child, int index, LayoutParams p, boolean preventRequestLayout) {
    child.mParent = null;
    addViewInner(child, index, p, preventRequestLayout);
    return true;
  }
  private void addViewInner(View child, int index, LayoutParams p, boolean preventRequestLayout) {
    if (child.getParent() != null) throw new IllegalStateException("The specified child already has a parent. You must call removeView() on the child's parent first.");
    if (!checkLayoutParams(p)) p = generateLayoutParams(p);
    child.mLayoutParams = p;
    if (!preventRequestLayout) child.requestLayout(); else child.mPrivateFlags |= PFLAG_FORCE_LAYOUT;
    if (index < 0) index = mChildrenCount;
    addInArray(child, index);
    child.mParent = this;
    if (child.hasFocus()) requestChildFocus(child, child.findFocus());
    if (mAttachInfo != null) child.dispatchAttachedToWindow(mAttachInfo, mViewFlags & VISIBILITY_MASK);
    onViewAdded(child);
    if (mOnHierarchyChangeListener != null) mOnHierarchyChangeListener.onChildViewAdded(this, child);
  }
  public void onViewAdded(View child) {}
  public void onViewRemoved(View child) {}
  private void addInArray(View c, int index) {
    if (mChildrenCount == mChildren.length) mChildren = java.util.Arrays.copyOf(mChildren, mChildren.length * 2);
    if (index > mChildrenCount) index = mChildrenCount;
    System.arraycopy(mChildren, index, mChildren, index + 1, mChildrenCount - index);
    mChildren[index] = c;
    mChildrenCount++;
  }
  private void removeFromArray(int index) {
    System.arraycopy(mChildren, index + 1, mChildren, index, mChildrenCount - index - 1);
    mChildren[--mChildrenCount] = null;
  }
  public void removeView(View v) { removeViewInternal(v); requestLayout(); invalidate(); }
  public void removeViewInLayout(View v) { removeViewInternal(v); }
  public void removeViewsInLayout(int start, int count) { removeViewsInternal(start, count); }
  public void removeViewAt(int i) { removeViewInternal(i, getChildAt(i)); requestLayout(); invalidate(); }
  public void removeViews(int start, int count) { removeViewsInternal(start, count); requestLayout(); invalidate(); }
  public void removeAllViews() { removeAllViewsInLayout(); requestLayout(); invalidate(); }
  public void removeAllViewsInLayout() { removeViewsInternal(0, mChildrenCount); }
  private void removeViewInternal(View v) { int i = indexOfChild(v); if (i >= 0) removeViewInternal(i, v); }
  private void removeViewInternal(int index, View v) {
    if (v == null) return;
    boolean clearFocus = v == mFocused;
    if (clearFocus) { v.clearFocus(); mFocused = null; }
    cancelTouchTarget(v);
    if (v.mAttachInfo != null) v.dispatchDetachedFromWindow();
    onViewRemoved(v);
    if (mOnHierarchyChangeListener != null) mOnHierarchyChangeListener.onChildViewRemoved(this, v);
    removeFromArray(index);
    v.mParent = null;
  }
  private void removeViewsInternal(int start, int count) {
    int end = Math.min(mChildrenCount, start + count);
    for (int i = end - 1; i >= start; i--) removeViewInternal(i, mChildren[i]);
  }
  protected void removeDetachedView(View child, boolean animate) {
    if (child == mFocused) child.clearFocus();
    if (child.mAttachInfo != null) child.dispatchDetachedFromWindow();
    if (mOnHierarchyChangeListener != null) mOnHierarchyChangeListener.onChildViewRemoved(this, child);
  }
  protected void attachViewToParent(View child, int index, LayoutParams p) {
    child.mLayoutParams = p;
    if (index < 0) index = mChildrenCount;
    addInArray(child, index);
    child.mParent = this;
    if (mAttachInfo != null && child.mAttachInfo == null) child.dispatchAttachedToWindow(mAttachInfo, mViewFlags & VISIBILITY_MASK);
  }
  protected void detachViewFromParent(View child) { int i = indexOfChild(child); if (i >= 0) detachViewFromParent(i); }
  protected void detachViewFromParent(int index) { View c = mChildren[index]; cancelTouchTarget(c); removeFromArray(index); c.mParent = null; }
  protected void detachViewsFromParent(int start, int count) { for (int i = Math.min(mChildrenCount, start + count) - 1; i >= start; i--) detachViewFromParent(i); }
  protected void detachAllViewsFromParent() { for (int i = mChildrenCount - 1; i >= 0; i--) detachViewFromParent(i); }
  protected void cleanupLayoutState(View child) { child.mPrivateFlags &= ~PFLAG_FORCE_LAYOUT; }
  public void bringChildToFront(View child) {
    int i = indexOfChild(child);
    if (i >= 0) { removeFromArray(i); addInArray(child, mChildrenCount); child.mParent = this; requestLayout(); invalidate(); }
  }
  void onSetLayoutParams(View child, LayoutParams p) {}
  void onChildVisibilityChanged(View child, int oldVis, int newVis) {}
  public boolean shouldDelayChildPressedState() { return true; }

  // ---------------------------------------------------------------- layout params
  public LayoutParams generateLayoutParams(AttributeSet attrs) { return new LayoutParams(getContext(), attrs); }
  protected LayoutParams generateLayoutParams(LayoutParams p) { return p; }
  protected LayoutParams generateDefaultLayoutParams() { return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT); }
  protected boolean checkLayoutParams(LayoutParams p) { return p != null; }

  // ---------------------------------------------------------------- measure helpers
  protected void measureChildren(int ws, int hs) {
    for (int i = 0; i < mChildrenCount; i++) { View c = mChildren[i]; if ((c.mViewFlags & VISIBILITY_MASK) != GONE) measureChild(c, ws, hs); }
  }
  protected void measureChild(View child, int ws, int hs) {
    LayoutParams lp = child.getLayoutParams();
    child.measure(getChildMeasureSpec(ws, mPaddingLeft + mPaddingRight, lp.width), getChildMeasureSpec(hs, mPaddingTop + mPaddingBottom, lp.height));
  }
  protected void measureChildWithMargins(View child, int ws, int widthUsed, int hs, int heightUsed) {
    MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
    child.measure(getChildMeasureSpec(ws, mPaddingLeft + mPaddingRight + lp.leftMargin + lp.rightMargin + widthUsed, lp.width),
        getChildMeasureSpec(hs, mPaddingTop + mPaddingBottom + lp.topMargin + lp.bottomMargin + heightUsed, lp.height));
  }
  public static int getChildMeasureSpec(int spec, int padding, int childDimension) {
    int specMode = MeasureSpec.getMode(spec), specSize = MeasureSpec.getSize(spec);
    int size = Math.max(0, specSize - padding);
    int resultSize = 0, resultMode = 0;
    switch (specMode) {
      case MeasureSpec.EXACTLY:
        if (childDimension >= 0) { resultSize = childDimension; resultMode = MeasureSpec.EXACTLY; }
        else if (childDimension == LayoutParams.MATCH_PARENT) { resultSize = size; resultMode = MeasureSpec.EXACTLY; }
        else if (childDimension == LayoutParams.WRAP_CONTENT) { resultSize = size; resultMode = MeasureSpec.AT_MOST; }
        break;
      case MeasureSpec.AT_MOST:
        if (childDimension >= 0) { resultSize = childDimension; resultMode = MeasureSpec.EXACTLY; }
        else if (childDimension == LayoutParams.MATCH_PARENT) { resultSize = size; resultMode = MeasureSpec.AT_MOST; }
        else if (childDimension == LayoutParams.WRAP_CONTENT) { resultSize = size; resultMode = MeasureSpec.AT_MOST; }
        break;
      case MeasureSpec.UNSPECIFIED:
        if (childDimension >= 0) { resultSize = childDimension; resultMode = MeasureSpec.EXACTLY; }
        else if (childDimension == LayoutParams.MATCH_PARENT) { resultSize = 0; resultMode = MeasureSpec.UNSPECIFIED; }
        else if (childDimension == LayoutParams.WRAP_CONTENT) { resultSize = 0; resultMode = MeasureSpec.UNSPECIFIED; }
        break;
    }
    return MeasureSpec.makeMeasureSpec(resultSize, resultMode);
  }
  @Override protected abstract void onLayout(boolean changed, int l, int t, int r, int b);

  // ---------------------------------------------------------------- drawing
  public void setClipChildren(boolean c) { mClipChildren = c; invalidate(); }
  public boolean getClipChildren() { return mClipChildren; }
  public void setClipToPadding(boolean c) { mClipToPadding = c; invalidate(); }
  public boolean getClipToPadding() { return mClipToPadding; }
  protected void setStaticTransformationsEnabled(boolean e) { mStaticTransformationsEnabled = e; }
  protected boolean getChildStaticTransformation(View child, Transformation t) { return false; }
  @Override protected void dispatchDraw(Canvas canvas) {
    if (mRunLayoutAnimation && canAnimate() && mChildrenCount > 0) runLayoutAnimation();
    dispatchDrawChildren(canvas);
    if (mLayoutAnimation != null) checkLayoutAnimationEnd();
  }
  private void dispatchDrawChildren(Canvas canvas) {
    final int count = mChildrenCount;
    final long drawingTime = getDrawingTime();
    boolean clip = mClipToPadding && (mPaddingLeft | mPaddingTop | mPaddingRight | mPaddingBottom) != 0;
    int save = 0;
    if (clip) {
      save = canvas.save();
      canvas.clipRect(mScrollX + mPaddingLeft, mScrollY + mPaddingTop, mScrollX + mRight - mLeft - mPaddingRight, mScrollY + mBottom - mTop - mPaddingBottom);
    }
    View[] children = mChildren;
    for (int i = 0; i < count; i++) {
      View child = children[mChildrenDrawingOrderEnabled ? getChildDrawingOrder(count, i) : i];
      if (child == null) continue;
      if ((child.mViewFlags & VISIBILITY_MASK) == VISIBLE || child.getAnimation() != null) drawChild(canvas, child, drawingTime);
    }
    if (clip) canvas.restoreToCount(save);
  }
  protected boolean drawChild(Canvas canvas, View child, long drawingTime) { return child.drawAsChild(canvas, this, drawingTime); }
  void finishAnimatingView(View view, Animation animation) {
    if (animation != null && !animation.getFillAfter()) view.clearAnimation();
    if ((view.mPrivateFlags & PFLAG_ANIMATION_STARTED) == PFLAG_ANIMATION_STARTED) {
      view.onAnimationEnd();
      view.mPrivateFlags &= ~PFLAG_ANIMATION_STARTED;
    }
  }
  /** the layout animation runs at the next draw (and again after startLayoutAnimation or scheduleLayoutAnimation) */
  private boolean mRunLayoutAnimation, mLayoutAnimationDone = true, mNotifyLayoutAnimationEnd;
  public void setLayoutAnimation(LayoutAnimationController c) { mLayoutAnimation = c; if (c != null) mRunLayoutAnimation = true; }
  public LayoutAnimationController getLayoutAnimation() { return mLayoutAnimation; }
  public void startLayoutAnimation() { if (mLayoutAnimation != null) { mRunLayoutAnimation = true; requestLayout(); } }
  public void scheduleLayoutAnimation() { if (mLayoutAnimation != null) mRunLayoutAnimation = true; }
  /** (hidden API) */
  public boolean isLayoutAnimationRunning() { return !mLayoutAnimationDone; }
  private void runLayoutAnimation() {
    int count = mChildrenCount;
    for (int i = 0; i < count; i++) {
      View child = mChildren[i];
      if (child == null || (child.mViewFlags & VISIBILITY_MASK) != VISIBLE) continue;
      LayoutParams params = child.getLayoutParams();
      if (params == null) continue;
      attachLayoutAnimationParameters(child, params, i, count);
      child.setAnimation(mLayoutAnimation.getAnimationForView(child));
    }
    mLayoutAnimation.start();
    mRunLayoutAnimation = false;
    mLayoutAnimationDone = false;
    mNotifyLayoutAnimationEnd = false;
    if (mAnimationListener != null) mAnimationListener.onAnimationStart(mLayoutAnimation.getAnimation());
  }
  private void checkLayoutAnimationEnd() {
    if (mLayoutAnimationDone || mNotifyLayoutAnimationEnd || mLayoutAnimation == null) return;
    if (!mLayoutAnimation.isDone()) { invalidate(); return; }
    mNotifyLayoutAnimationEnd = true;
    post(() -> {
      mLayoutAnimationDone = true;
      if (mAnimationListener != null) mAnimationListener.onAnimationEnd(mLayoutAnimation == null ? null : mLayoutAnimation.getAnimation());
    });
  }
  public void setLayoutAnimationListener(Animation.AnimationListener l) { mAnimationListener = l; }
  public void setAnimationCacheEnabled(boolean e) {}
  public void setAlwaysDrawnWithCacheEnabled(boolean e) {}
  public void setChildrenDrawingCacheEnabled(boolean e) {}
  protected void setChildrenDrawnWithCacheEnabled(boolean e) { mChildrenDrawnWithCache = e; }
  public void setPersistentDrawingCache(int c) {}
  public int getPersistentDrawingCache() { return PERSISTENT_SCROLLING_CACHE; }
  private boolean mSplitMotionEvents = true, mTouchscreenBlocksFocus, mSuppressLayout, mLayoutCalledWhileSuppressed, mChildrenDrawnWithCache;
  private Boolean mTransitionGroup;
  private int mLayoutMode = LAYOUT_MODE_CLIP_BOUNDS;
  private android.animation.LayoutTransition mTransition;
  public static final int LAYOUT_MODE_CLIP_BOUNDS = 0, LAYOUT_MODE_OPTICAL_BOUNDS = 1;
  public void setMotionEventSplittingEnabled(boolean s) { mSplitMotionEvents = s; }
  public boolean isMotionEventSplittingEnabled() { return mSplitMotionEvents; }
  public void setLayoutTransition(android.animation.LayoutTransition t) { mTransition = t; }
  public android.animation.LayoutTransition getLayoutTransition() { return mTransition; }
  public void setTouchscreenBlocksFocus(boolean b) { mTouchscreenBlocksFocus = b; }
  public boolean getTouchscreenBlocksFocus() { return mTouchscreenBlocksFocus; }
  public void setTransitionGroup(boolean isTransitionGroup) { mTransitionGroup = isTransitionGroup; }
  public boolean isTransitionGroup() { return mTransitionGroup != null ? mTransitionGroup : getBackground() != null || getTransitionName() != null; }
  public int getLayoutMode() { return mLayoutMode; }
  public void setLayoutMode(int layoutMode) { if (mLayoutMode != layoutMode) { mLayoutMode = layoutMode; requestLayout(); } }
  /** (API 29) layout requests are held back while suppressed and made when it ends */
  public void suppressLayout(boolean suppress) {
    mSuppressLayout = suppress;
    if (!suppress && mLayoutCalledWhileSuppressed) { requestLayout(); mLayoutCalledWhileSuppressed = false; }
  }
  public boolean isLayoutSuppressed() { return mSuppressLayout; }
  @Override public void requestLayout() {
    if (mSuppressLayout) { mLayoutCalledWhileSuppressed = true; return; }
    super.requestLayout();
  }
  @Deprecated protected boolean isChildrenDrawnWithCacheEnabled() { return mChildrenDrawnWithCache; }
  public final int getChildDrawingOrder(int drawingPosition) { return getChildDrawingOrder(getChildCount(), drawingPosition); }
  public Animation.AnimationListener getLayoutAnimationListener() { return mAnimationListener; }
  protected void attachLayoutAnimationParameters(View child, LayoutParams params, int index, int count) {
    LayoutAnimationController.AnimationParameters p = params.layoutAnimationParameters;
    if (p == null) { p = new LayoutAnimationController.AnimationParameters(); params.layoutAnimationParameters = p; }
    p.count = count; p.index = index;
  }
  protected boolean canAnimate() { return mLayoutAnimation != null; }
  public void clearDisappearingChildren() { invalidate(); }
  public void debug(int depth) {
    StringBuilder b = new StringBuilder();
    for (int i = 0; i < depth; i++) b.append("  ");
    android.util.Log.d("View", b + toString() + " {");
    for (int i = 0; i < getChildCount(); i++) { View c = getChildAt(i); if (c instanceof ViewGroup) ((ViewGroup) c).debug(depth + 1); else android.util.Log.d("View", b + "  " + c); }
    android.util.Log.d("View", b + "}");
  }
  protected void dispatchFreezeSelfOnly(android.util.SparseArray<android.os.Parcelable> container) { super.dispatchSaveInstanceState(container); }
  protected void dispatchThawSelfOnly(android.util.SparseArray<android.os.Parcelable> container) { super.dispatchRestoreInstanceState(container); }
  @Override public void dispatchSaveInstanceState(android.util.SparseArray<android.os.Parcelable> container) {
    super.dispatchSaveInstanceState(container);
    for (int i = 0; i < getChildCount(); i++) { View c = getChildAt(i); if (c.isSaveFromParentEnabled()) c.dispatchSaveInstanceState(container); }
  }
  @Override public void dispatchRestoreInstanceState(android.util.SparseArray<android.os.Parcelable> container) {
    super.dispatchRestoreInstanceState(container);
    for (int i = 0; i < getChildCount(); i++) { View c = getChildAt(i); if (c.isSaveFromParentEnabled()) c.dispatchRestoreInstanceState(container); }
  }
  public boolean onRequestSendAccessibilityEvent(View child, android.view.accessibility.AccessibilityEvent event) { return true; }
  public boolean requestSendAccessibilityEvent(View child, android.view.accessibility.AccessibilityEvent event) {
    ViewParent parent = getParent();
    if (parent == null || !onRequestSendAccessibilityEvent(child, event)) return false;
    return parent.requestSendAccessibilityEvent(this, event);
  }
  public boolean showContextMenuForChild(View originalView, float x, float y) { ViewParent p = getParent(); return p != null && p.showContextMenuForChild(originalView, x, y); }
  public ActionMode startActionModeForChild(View originalView, ActionMode.Callback callback) { return startActionModeForChild(originalView, callback, ActionMode.TYPE_PRIMARY); }
  public ActionMode startActionModeForChild(View originalView, ActionMode.Callback callback, int type) { ViewParent p = getParent(); return p == null ? null : p.startActionModeForChild(originalView, callback, type); }
  public android.window.OnBackInvokedDispatcher findOnBackInvokedDispatcherForChild(View child, View requester) { ViewParent p = getParent(); return p == null ? null : p.findOnBackInvokedDispatcherForChild(this, requester); }
  @Override public void invalidateChild(View child, Rect r) { invalidate(); }
  @Override public ViewParent invalidateChildInParent(int[] loc, Rect r) { invalidate(); return null; }
  @Override public void requestTransparentRegion(View child) {}
  @Override public boolean getChildVisibleRect(View child, Rect r, Point offset) { r.offset(child.mLeft - mScrollX, child.mTop - mScrollY); return true; }
  public void offsetDescendantRectToMyCoords(View d, Rect r) { offsetRect(d, r, true); }
  public void offsetRectIntoDescendantCoords(View d, Rect r) { offsetRect(d, r, false); }
  private void offsetRect(View d, Rect r, boolean toMine) {
    View v = d;
    while (v != null && v != this) {
      int dx = v.mLeft - v.mScrollX, dy = v.mTop - v.mScrollY;
      if (v.mParent instanceof View) { View p = (View) v.mParent; }
      if (toMine) r.offset(dx, dy); else r.offset(-dx, -dy);
      ViewParent p = v.mParent;
      v = p instanceof View ? (View) p : null;
    }
    if (toMine) r.offset(-0, -0);
  }
  public final void offsetChildrenTopAndBottom(int o) { for (int i = 0; i < mChildrenCount; i++) { View c = mChildren[i]; c.mTop += o; c.mBottom += o; } invalidate(); }

  // ---------------------------------------------------------------- state propagation
  @Override protected void dispatchSetPressed(boolean p) {
    for (int i = 0; i < mChildrenCount; i++) {
      View c = mChildren[i];
      if (!p || (!c.isClickable() && !c.isLongClickable())) {
        if ((c.mViewFlags & DUPLICATE_PARENT_STATE) != 0) c.setPressed(p);
      }
    }
  }
  @Override protected void dispatchSetSelected(boolean s) { for (int i = 0; i < mChildrenCount; i++) mChildren[i].setSelected(s); }
  @Override protected void dispatchSetActivated(boolean a) { for (int i = 0; i < mChildrenCount; i++) mChildren[i].setActivated(a); }
  @Override protected void drawableStateChanged() {
    super.drawableStateChanged();
    for (int i = 0; i < mChildrenCount; i++) { View c = mChildren[i]; if ((c.mViewFlags & DUPLICATE_PARENT_STATE) != 0) c.refreshDrawableState(); }
  }
  @Override protected int[] onCreateDrawableState(int extra) {
    if (!mAddStatesFromChildren) return super.onCreateDrawableState(extra);
    int need = 0;
    for (int i = 0; i < mChildrenCount; i++) { int[] cs = mChildren[i].getDrawableState(); if (cs != null) need += cs.length; }
    int[] s = super.onCreateDrawableState(extra + need);
    for (int i = 0; i < mChildrenCount; i++) { int[] cs = mChildren[i].getDrawableState(); if (cs != null) s = mergeDrawableStates(s, cs); }
    return s;
  }
  public void setAddStatesFromChildren(boolean a) { mAddStatesFromChildren = a; refreshDrawableState(); }
  public boolean addStatesFromChildren() { return mAddStatesFromChildren; }
  @Override public void childDrawableStateChanged(View child) { if (mAddStatesFromChildren) refreshDrawableState(); }

  // ---------------------------------------------------------------- attach / window
  @Override void dispatchAttachedToWindow(AttachInfo info, int visibility) {
    super.dispatchAttachedToWindow(info, visibility);
    for (int i = 0; i < mChildrenCount; i++) mChildren[i].dispatchAttachedToWindow(info, visibility | (mChildren[i].mViewFlags & VISIBILITY_MASK));
  }
  @Override void dispatchDetachedFromWindow() {
    cancelAndClearTouchTargets(null);
    for (int i = 0; i < mChildrenCount; i++) mChildren[i].dispatchDetachedFromWindow();
    super.dispatchDetachedFromWindow();
  }
  @Override public void dispatchWindowFocusChanged(boolean f) {
    super.dispatchWindowFocusChanged(f);
    for (int i = 0; i < mChildrenCount; i++) mChildren[i].dispatchWindowFocusChanged(f);
  }
  @Override public void dispatchWindowVisibilityChanged(int v) {
    super.dispatchWindowVisibilityChanged(v);
    for (int i = 0; i < mChildrenCount; i++) mChildren[i].dispatchWindowVisibilityChanged(v);
  }
  @Override void dispatchVisibilityChanged(View changed, int v) {
    super.dispatchVisibilityChanged(changed, v);
    for (int i = 0; i < mChildrenCount; i++) mChildren[i].dispatchVisibilityChanged(changed, v);
  }
  @Override public void dispatchConfigurationChanged(android.content.res.Configuration c) {
    super.dispatchConfigurationChanged(c);
    for (int i = 0; i < mChildrenCount; i++) mChildren[i].dispatchConfigurationChanged(c);
  }

  // ---------------------------------------------------------------- finding
  @Override protected View findViewTraversal(int id) {
    if (id == mID) return this;
    for (int i = 0; i < mChildrenCount; i++) { View v = mChildren[i].findViewTraversal(id); if (v != null) return v; }
    return null;
  }
  @Override protected View findViewWithTagTraversal(Object tag) {
    if (tag.equals(mTag)) return this;
    for (int i = 0; i < mChildrenCount; i++) { View v = mChildren[i].findViewWithTagTraversal(tag); if (v != null) return v; }
    return null;
  }
  public View getFocusedChild() { return mFocused; }

  // ---------------------------------------------------------------- focus
  public int getDescendantFocusability() { return mDescendantFocusability; }
  public void setDescendantFocusability(int f) { mDescendantFocusability = f; }
  @Override public void requestChildFocus(View child, View focused) {
    if (mDescendantFocusability == FOCUS_BLOCK_DESCENDANTS) return;
    if (mFocused != child) { if (mFocused != null && mFocused != child) mFocused.unFocus(); mFocused = child; }
    if (mParent != null) mParent.requestChildFocus(this, focused);
  }
  @Override public void clearChildFocus(View child) { mFocused = null; if (mParent != null) mParent.clearChildFocus(this); }
  @Override public void clearFocus() { super.clearFocus(); if (mFocused != null) mFocused.clearFocus(); }
  @Override void unFocus() { if (mFocused == null) super.unFocus(); else { mFocused.unFocus(); mFocused = null; } }
  @Override public boolean hasFocus() { return (mPrivateFlags & PFLAG_FOCUSED) != 0 || mFocused != null; }
  @Override public View findFocus() { if (isFocused()) return this; return mFocused != null ? mFocused.findFocus() : null; }
  @Override boolean hasFocusable(boolean allowAutoFocus, boolean dispatchExplicit) {
    if ((mViewFlags & VISIBILITY_MASK) != VISIBLE) return false;
    if ((allowAutoFocus || getFocusable() != FOCUSABLE_AUTO) && isFocusable()) return true;
    if (mDescendantFocusability != FOCUS_BLOCK_DESCENDANTS)
      for (int i = 0; i < mChildrenCount; i++) { View c = mChildren[i]; if (dispatchExplicit ? c.hasExplicitFocusable() : c.hasFocusable()) return true; }
    return false;
  }
  /** touchscreenBlocksFocus: arrows don't enter it (keyboard navigation clusters still do, and focus already inside stays) */
  boolean shouldBlockFocusForTouchscreen() { return mTouchscreenBlocksFocus && !(isKeyboardNavigationCluster() && hasFocus()); }
  @Override public boolean requestFocus(int dir, Rect prev) {
    switch (mDescendantFocusability) {
      case FOCUS_BLOCK_DESCENDANTS: return super.requestFocus(dir, prev);
      case FOCUS_BEFORE_DESCENDANTS: return super.requestFocus(dir, prev) || onRequestFocusInDescendants(dir, prev);
      default: return onRequestFocusInDescendants(dir, prev) || super.requestFocus(dir, prev);
    }
  }
  /** children in order (backwards for up, left and backward), the first that takes focus */
  protected boolean onRequestFocusInDescendants(int dir, Rect prev) {
    int count = mChildrenCount, index, increment, end;
    if ((dir & FOCUS_FORWARD) != 0) { index = 0; increment = 1; end = count; } else { index = count - 1; increment = -1; end = -1; }
    View[] children = mChildren;
    for (int i = index; i != end; i += increment) { View c = children[i]; if ((c.mViewFlags & VISIBILITY_MASK) == VISIBLE && c.requestFocus(dir, prev)) return true; }
    return false;
  }
  @Override public boolean restoreDefaultFocus() {
    if (mDescendantFocusability != FOCUS_BLOCK_DESCENDANTS)
      for (int i = 0; i < mChildrenCount; i++) {
        View c = mChildren[i];
        if ((c.mViewFlags & VISIBILITY_MASK) == VISIBLE && (c.isFocusedByDefault() || (c instanceof ViewGroup && ((ViewGroup) c).hasDefaultFocus())) && c.restoreDefaultFocus()) return true;
      }
    return super.restoreDefaultFocus();
  }
  boolean hasDefaultFocus() {
    if (mDescendantFocusability == FOCUS_BLOCK_DESCENDANTS) return false;
    for (int i = 0; i < mChildrenCount; i++) { View c = mChildren[i]; if (c.isFocusedByDefault() || (c instanceof ViewGroup && ((ViewGroup) c).hasDefaultFocus())) return true; }
    return false;
  }
  @Override public boolean dispatchUnhandledMove(View focused, int direction) { return mFocused != null && mFocused.dispatchUnhandledMove(focused, direction); }
  @Override public boolean dispatchKeyShortcutEvent(KeyEvent event) {
    if ((mPrivateFlags & PFLAG_FOCUSED) != 0) return super.dispatchKeyShortcutEvent(event);
    if (mFocused != null && (mFocused.mViewFlags & VISIBILITY_MASK) == VISIBLE) return mFocused.dispatchKeyShortcutEvent(event);
    return false;
  }
  @Override public void addTouchables(ArrayList<View> views) {
    super.addTouchables(views);
    for (int i = 0; i < mChildrenCount; i++) if ((mChildren[i].mViewFlags & VISIBILITY_MASK) == VISIBLE) mChildren[i].addTouchables(views);
  }
  /** itself (before or after its descendants, per descendantFocusability) and its visible children in reading order */
  @Override public void addFocusables(ArrayList<View> views, int direction, int focusableMode) {
    final int focusableCount = views.size();
    final int descendantFocusability = getDescendantFocusability();
    final boolean blockFocusForTouchscreen = shouldBlockFocusForTouchscreen();
    final boolean focusSelf = isFocusableInTouchMode() || !blockFocusForTouchscreen;
    if (descendantFocusability == FOCUS_BLOCK_DESCENDANTS) { if (focusSelf) super.addFocusables(views, direction, focusableMode); return; }
    if (blockFocusForTouchscreen) focusableMode |= FOCUSABLES_TOUCH_MODE;
    if (descendantFocusability == FOCUS_BEFORE_DESCENDANTS && focusSelf) super.addFocusables(views, direction, focusableMode);
    int count = 0;
    final View[] children = new View[mChildrenCount];
    for (int i = 0; i < mChildrenCount; ++i) { View c = mChildren[i]; if ((c.mViewFlags & VISIBILITY_MASK) == VISIBLE) children[count++] = c; }
    FocusFinder.sort(children, 0, count, this, isLayoutRtl());
    for (int i = 0; i < count; ++i) children[i].addFocusables(views, direction, focusableMode);
    if (descendantFocusability == FOCUS_AFTER_DESCENDANTS && focusSelf && focusableCount == views.size()) super.addFocusables(views, direction, focusableMode);
  }
  @Override public View focusSearch(View focused, int dir) { return mParent != null ? mParent.focusSearch(focused, dir) : null; }
  @Override public void focusableViewAvailable(View v) { if (mParent != null) mParent.focusableViewAvailable(v); }
  @Override public void recomputeViewAttributes(View child) { if (mParent != null) mParent.recomputeViewAttributes(this); }
  @Override public boolean showContextMenuForChild(View v) { return mParent != null && mParent.showContextMenuForChild(v); }
  @Override public boolean requestChildRectangleOnScreen(View child, Rect r, boolean immediate) { return false; }

  // ---------------------------------------------------------------- keys
  @Override public boolean dispatchKeyEvent(KeyEvent event) {
    if ((mPrivateFlags & PFLAG_FOCUSED) != 0) return super.dispatchKeyEvent(event);
    if (mFocused != null && (mFocused.mViewFlags & VISIBILITY_MASK) == VISIBLE) return mFocused.dispatchKeyEvent(event);
    return false;
  }

  // ---------------------------------------------------------------- touch
  private static final class TouchTarget { View child; int idBits; TouchTarget next; }

  @Override public void requestDisallowInterceptTouchEvent(boolean disallow) {
    if (disallow == ((mGroupFlags & FLAG_DISALLOW_INTERCEPT) != 0)) return;
    if (disallow) mGroupFlags |= FLAG_DISALLOW_INTERCEPT; else mGroupFlags &= ~FLAG_DISALLOW_INTERCEPT;
    if (mParent != null) mParent.requestDisallowInterceptTouchEvent(disallow);
  }
  @Override public boolean onInterceptTouchEvent(MotionEvent ev) { return false; }

  // ---- nested scrolling: a group passes it on to its own parent unless it takes part
  int mNestedScrollAxes;
  public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) { return false; }
  public void onNestedScrollAccepted(View child, View target, int axes) { mNestedScrollAxes = axes; }
  public void onStopNestedScroll(View child) { stopNestedScroll(); mNestedScrollAxes = 0; }
  public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) { dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, null); }
  public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) { dispatchNestedPreScroll(dx, dy, consumed, null); }
  public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) { return dispatchNestedFling(velocityX, velocityY, consumed); }
  public boolean onNestedPreFling(View target, float velocityX, float velocityY) { return dispatchNestedPreFling(velocityX, velocityY); }
  public int getNestedScrollAxes() { return mNestedScrollAxes; }
  @Override public WindowInsets dispatchApplyWindowInsets(WindowInsets insets) {
    insets = super.dispatchApplyWindowInsets(insets);
    if (!insets.isConsumed()) for (int i = 0; i < getChildCount(); i++) { insets = getChildAt(i).dispatchApplyWindowInsets(insets); if (insets.isConsumed()) break; }
    return insets;
  }
  @Override public boolean dispatchTouchEvent(MotionEvent ev) {
    boolean handled = false;
    final int action = ev.getActionMasked();
    if (action == MotionEvent.ACTION_DOWN) { cancelAndClearTouchTargets(ev); mGroupFlags &= ~FLAG_DISALLOW_INTERCEPT; }
    final boolean intercepted;
    if (action == MotionEvent.ACTION_DOWN || mFirstTouchTarget != null) {
      boolean disallow = (mGroupFlags & FLAG_DISALLOW_INTERCEPT) != 0;
      intercepted = !disallow && onInterceptTouchEvent(ev);
    } else intercepted = true;
    final boolean canceled = action == MotionEvent.ACTION_CANCEL || (mPrivateFlags & PFLAG_CANCEL_NEXT_UP_EVENT) != 0;
    if (canceled) mPrivateFlags &= ~PFLAG_CANCEL_NEXT_UP_EVENT;
    TouchTarget newTarget = null;
    boolean already = false;
    if (!canceled && !intercepted && (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_DOWN)) {
      final int ai = ev.getActionIndex();
      final int idBits = 1 << ev.getPointerId(ai);
      removePointersFromTouchTargets(idBits);
      final float x = ev.getX(ai), y = ev.getY(ai);
      for (int i = mChildrenCount - 1; i >= 0; i--) {
        final View child = mChildren[mChildrenDrawingOrderEnabled ? getChildDrawingOrder(mChildrenCount, i) : i];
        if (child == null) continue;
        if (((child.mViewFlags & VISIBILITY_MASK) != VISIBLE && child.getAnimation() == null) || !pointInChild(x, y, child)) continue;
        newTarget = getTouchTarget(child);
        if (newTarget != null) { newTarget.idBits |= idBits; break; }
        if (dispatchTransformed(ev, false, child, idBits)) { newTarget = addTouchTarget(child, idBits); already = true; break; }
      }
      if (newTarget == null && mFirstTouchTarget != null) {
        newTarget = mFirstTouchTarget;
        while (newTarget.next != null) newTarget = newTarget.next;
        newTarget.idBits |= idBits;
      }
    }
    if (mFirstTouchTarget == null) {
      handled = dispatchTransformed(ev, canceled, null, -1);
    } else {
      TouchTarget pred = null, t = mFirstTouchTarget;
      while (t != null) {
        TouchTarget next = t.next;
        if (already && t == newTarget) handled = true;
        else {
          boolean cancelChild = (t.child.mPrivateFlags & PFLAG_CANCEL_NEXT_UP_EVENT) != 0 || intercepted;
          t.child.mPrivateFlags &= ~PFLAG_CANCEL_NEXT_UP_EVENT;
          if (dispatchTransformed(ev, cancelChild, t.child, t.idBits)) handled = true;
          if (cancelChild) {
            if (pred == null) mFirstTouchTarget = next; else pred.next = next;
            t = next;
            continue;
          }
        }
        pred = t;
        t = next;
      }
    }
    if (canceled || action == MotionEvent.ACTION_UP) { mFirstTouchTarget = null; mGroupFlags &= ~FLAG_DISALLOW_INTERCEPT; }
    else if (action == MotionEvent.ACTION_POINTER_UP) removePointersFromTouchTargets(1 << ev.getPointerId(ev.getActionIndex()));
    return handled;
  }
  // ---- generic pointer events and hover
  private final java.util.ArrayList<View> mHovered = new java.util.ArrayList<>();
  private boolean mHoveredSelf;
  public boolean onInterceptHoverEvent(MotionEvent event) { return false; }
  private boolean receivesPointer(View child) { return (child.mViewFlags & VISIBILITY_MASK) == VISIBLE || child.getAnimation() != null; }
  private View childAt(int i) { return mChildren[mChildrenDrawingOrderEnabled ? getChildDrawingOrder(mChildrenCount, i) : i]; }
  /** the event in the child's coordinates, to the child */
  private boolean dispatchTransformedGeneric(MotionEvent event, View child) {
    MotionEvent e = MotionEvent.obtain(event);
    e.offsetLocation(mScrollX - child.mLeft, mScrollY - child.mTop);
    if (child.hasTransform()) {
      android.graphics.Matrix inv = new android.graphics.Matrix();
      child.getMatrix().invert(inv);
      e.transform(inv);
    }
    return child.dispatchGenericMotionEvent(e);
  }
  private static MotionEvent withAction(MotionEvent e, int action) { MotionEvent c = MotionEvent.obtainNoHistory(e); c.setAction(action); return c; }
  @Override protected boolean dispatchGenericPointerEvent(MotionEvent event) {
    float x = event.getX(), y = event.getY();
    for (int i = mChildrenCount - 1; i >= 0; i--) {
      View child = childAt(i);
      if (child == null || !receivesPointer(child) || !pointInChild(x, y, child)) continue;
      if (dispatchTransformedGeneric(event, child)) return true;
    }
    return super.dispatchGenericPointerEvent(event);
  }
  @Override protected boolean dispatchGenericFocusedEvent(MotionEvent event) {
    if (isFocused()) return super.dispatchGenericFocusedEvent(event);
    View f = getFocusedChild();
    return f != null && f.dispatchGenericMotionEvent(event);
  }
  /** like ViewGroup.dispatchHoverEvent: enters and exits for the children under the pointer, then this group */
  @Override protected boolean dispatchHoverEvent(MotionEvent event) {
    int action = event.getAction();
    boolean intercept = onInterceptHoverEvent(event);
    event.setAction(action);
    boolean handled = false;
    java.util.ArrayList<View> old = new java.util.ArrayList<>(mHovered);
    mHovered.clear();
    if (!intercept && action != MotionEvent.ACTION_HOVER_EXIT) {
      float x = event.getX(), y = event.getY();
      for (int i = mChildrenCount - 1; i >= 0; i--) {
        View child = childAt(i);
        if (child == null || !receivesPointer(child) || !pointInChild(x, y, child)) continue;
        boolean was = old.remove(child);
        mHovered.add(child);
        if (action == MotionEvent.ACTION_HOVER_ENTER) { if (!was) handled |= dispatchTransformedGeneric(event, child); }
        else if (action == MotionEvent.ACTION_HOVER_MOVE) {
          if (!was) {
            handled |= dispatchTransformedGeneric(withAction(event, MotionEvent.ACTION_HOVER_ENTER), child);
            handled |= dispatchTransformedGeneric(withAction(event, action), child);
          } else handled |= dispatchTransformedGeneric(event, child);
        }
        if (handled) break;
      }
    }
    for (View child : old) {
      if (action == MotionEvent.ACTION_HOVER_EXIT) handled |= dispatchTransformedGeneric(event, child);
      else {
        if (action == MotionEvent.ACTION_HOVER_MOVE) dispatchTransformedGeneric(event, child);
        dispatchTransformedGeneric(withAction(event, MotionEvent.ACTION_HOVER_EXIT), child);
      }
    }
    boolean newHoveredSelf = !handled && action != MotionEvent.ACTION_HOVER_EXIT;
    if (newHoveredSelf == mHoveredSelf) {
      if (newHoveredSelf) handled |= super.dispatchHoverEvent(event);
    } else {
      if (mHoveredSelf) {
        if (action == MotionEvent.ACTION_HOVER_EXIT) handled |= super.dispatchHoverEvent(event);
        else {
          if (action == MotionEvent.ACTION_HOVER_MOVE) super.dispatchHoverEvent(event);
          super.dispatchHoverEvent(withAction(event, MotionEvent.ACTION_HOVER_EXIT));
        }
        mHoveredSelf = false;
      }
      if (newHoveredSelf) {
        if (action == MotionEvent.ACTION_HOVER_ENTER) { handled |= super.dispatchHoverEvent(event); mHoveredSelf = true; }
        else if (action == MotionEvent.ACTION_HOVER_MOVE) {
          handled |= super.dispatchHoverEvent(withAction(event, MotionEvent.ACTION_HOVER_ENTER));
          handled |= super.dispatchHoverEvent(event);
          mHoveredSelf = true;
        }
      }
    }
    return handled;
  }
  /** (API 14) a group that handles hover itself reports onHoverEvent's answer for its own area */
  @Override public boolean onHoverEvent(MotionEvent event) { return super.onHoverEvent(event); }

  boolean pointInChild(float x, float y, View child) {
    float lx = x + mScrollX - child.mLeft, ly = y + mScrollY - child.mTop;
    if (child.hasTransform()) {
      android.graphics.Matrix inv = new android.graphics.Matrix();
      child.getMatrix().invert(inv);
      float[] p = {lx, ly};
      inv.mapPoints(p);
      lx = p[0]; ly = p[1];
    }
    return child.pointInView(lx, ly, 0);
  }
  private boolean dispatchTransformed(MotionEvent ev, boolean cancel, View child, int idBits) {
    boolean handled;
    if (cancel || ev.getActionMasked() == MotionEvent.ACTION_CANCEL) {
      MotionEvent c = MotionEvent.obtain(ev);
      c.setAction(MotionEvent.ACTION_CANCEL);
      if (child == null) handled = super.dispatchTouchEvent(c);
      else { c.offsetLocation(mScrollX - child.mLeft, mScrollY - child.mTop); handled = child.dispatchTouchEvent(c); }
      return handled;
    }
    int all = ev.pointerIdBits();
    int bits = idBits == -1 ? all : all & idBits;
    if (bits == 0) return false;
    MotionEvent e = bits == all ? MotionEvent.obtain(ev) : ev.split(bits);
    if (child == null) return super.dispatchTouchEvent(e);
    e.offsetLocation(mScrollX - child.mLeft, mScrollY - child.mTop);
    if (child.hasTransform()) {
      android.graphics.Matrix inv = new android.graphics.Matrix();
      child.getMatrix().invert(inv);
      e.transform(inv);
    }
    return child.dispatchTouchEvent(e);
  }
  private TouchTarget getTouchTarget(View c) { for (TouchTarget t = mFirstTouchTarget; t != null; t = t.next) if (t.child == c) return t; return null; }
  private TouchTarget addTouchTarget(View c, int bits) { TouchTarget t = new TouchTarget(); t.child = c; t.idBits = bits; t.next = mFirstTouchTarget; mFirstTouchTarget = t; return t; }
  private void removePointersFromTouchTargets(int bits) {
    TouchTarget pred = null, t = mFirstTouchTarget;
    while (t != null) {
      TouchTarget next = t.next;
      if ((t.idBits & bits) != 0) {
        t.idBits &= ~bits;
        if (t.idBits == 0) { if (pred == null) mFirstTouchTarget = next; else pred.next = next; t = next; continue; }
      }
      pred = t; t = next;
    }
  }
  private void cancelTouchTarget(View view) {
    TouchTarget pred = null, t = mFirstTouchTarget;
    while (t != null) {
      TouchTarget next = t.next;
      if (t.child == view) {
        if (pred == null) mFirstTouchTarget = next; else pred.next = next;
        long now = android.os.SystemClock.uptimeMillis();
        MotionEvent e = MotionEvent.obtain(now, now, MotionEvent.ACTION_CANCEL, 0, 0, 0);
        view.dispatchTouchEvent(e);
        return;
      }
      pred = t; t = next;
    }
  }
  private void cancelAndClearTouchTargets(MotionEvent ev) {
    if (mFirstTouchTarget != null) {
      long now = android.os.SystemClock.uptimeMillis();
      for (TouchTarget t = mFirstTouchTarget; t != null; t = t.next) {
        MotionEvent e = MotionEvent.obtain(now, now, MotionEvent.ACTION_CANCEL, 0, 0, 0);
        t.child.dispatchTouchEvent(e);
      }
      mFirstTouchTarget = null;
    }
  }

  // ---------------------------------------------------------------- misc
  @Override public boolean isLayoutRequested() { return super.isLayoutRequested(); }
  public boolean isAlwaysDrawnWithCacheEnabled() { return false; }
  public boolean isAnimationCacheEnabled() { return false; }
  public boolean onRequestSendAccessibilityEvent(View c, Object e) { return true; }
  public void startViewTransition(View v) {}
  public void endViewTransition(View v) {}
}
