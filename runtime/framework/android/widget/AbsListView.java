package android.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.*;
import a2w.Attrs;
import a2w.Values;
import java.util.ArrayList;

/**
 * Shared machinery of ListView and GridView: adapter views recycled by type, rows filled to the
 * visible area, touch scrolling and flinging, item clicks and the selector, after Android 4.x.
 */
public abstract class AbsListView extends AdapterView<ListAdapter> implements android.text.TextWatcher,
    ViewTreeObserver.OnGlobalLayoutListener, Filter.FilterListener, ViewTreeObserver.OnTouchModeChangeListener {
  /** the callback of CHOICE_MODE_MULTIPLE_MODAL: an action mode that also hears item check changes */
  public interface MultiChoiceModeListener extends ActionMode.Callback {
    void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked);
  }
  public interface SelectionBoundsAdjuster { void adjustListItemSelectionBounds(Rect bounds); }
  public interface OnScrollListener {
    int SCROLL_STATE_IDLE = 0, SCROLL_STATE_TOUCH_SCROLL = 1, SCROLL_STATE_FLING = 2;
    void onScrollStateChanged(AbsListView view, int scrollState);
    void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount);
  }
  public interface RecyclerListener { void onMovedToScrapHeap(View view); }
  public static final int TRANSCRIPT_MODE_DISABLED = 0, TRANSCRIPT_MODE_NORMAL = 1, TRANSCRIPT_MODE_ALWAYS_SCROLL = 2;
  public static final int CHOICE_MODE_NONE = 0, CHOICE_MODE_SINGLE = 1, CHOICE_MODE_MULTIPLE = 2, CHOICE_MODE_MULTIPLE_MODAL = 3;
  static final int TOUCH_MODE_REST = -1, TOUCH_MODE_DOWN = 0, TOUCH_MODE_TAP = 1, TOUCH_MODE_DONE_WAITING = 2, TOUCH_MODE_SCROLL = 3, TOUCH_MODE_FLING = 4;

  ListAdapter mAdapter;
  AdapterDataSetObserver mDataSetObserver;
  final RecycleBin mRecycler = new RecycleBin();
  Drawable mSelector;
  final Rect mSelectorRect = new Rect();
  int mSelectorPosition = INVALID_POSITION;
  boolean mDrawSelectorOnTop;
  final Rect mListPadding = new Rect();
  int mWidthMeasureSpec;
  int mTouchMode = TOUCH_MODE_REST;
  int mMotionX, mMotionY, mLastY, mMotionPosition = INVALID_POSITION, mMotionCorrection;
  int mActivePointerId = -1;
  VelocityTracker mVelocityTracker;
  FlingRunnable mFlingRunnable;
  OnScrollListener mOnScrollListener;
  RecyclerListener mRecyclerListener;
  int mLastScrollState = OnScrollListener.SCROLL_STATE_IDLE;
  int mTouchSlop, mMinimumVelocity, mMaximumVelocity;
  int mPendingSelection = INVALID_POSITION, mPendingSelectionTop;
  boolean mStackFromBottom, mScrollingCacheEnabled, mSmoothScrollbarEnabled = true, mFastScrollEnabled, mBlockLayoutRequests, mInLayout;
  int mTranscriptMode, mCacheColorHint;
  int mChoiceMode = CHOICE_MODE_NONE;
  android.util.SparseBooleanArray mCheckStates;
  int mCheckedItemCount;
  private Runnable mPendingCheckForTap, mPendingCheckForLongPress, mPerformClick;
  private View mPressedChild;
  boolean[] mIsScrap = new boolean[1];

  public AbsListView(Context c) { this(c, null); }
  public AbsListView(Context c, AttributeSet a) { this(c, a, 0); }
  public AbsListView(Context c, AttributeSet a, int d, int r) { this(c, a, d); }
  public AbsListView(Context c, AttributeSet a, int d) {
    super(c, a, d);
    setClickable(true);
    setFocusableInTouchMode(true);
    setDefaultFocusHighlightEnabled(false);   // the selector marks the focused list's selected item
    setWillNotDraw(false);
    setAlwaysDrawnWithCacheEnabled(false);
    ViewConfiguration cfg = ViewConfiguration.get(c);
    mTouchSlop = cfg.getScaledTouchSlop();
    mMinimumVelocity = cfg.getScaledMinimumFlingVelocity();
    mMaximumVelocity = cfg.getScaledMaximumFlingVelocity();
    String sel = a instanceof Attrs ? ((Attrs) a).a("listSelector") : null;
    Drawable d0 = null;
    if (sel != null) {
      if (sel.startsWith("#")) d0 = new android.graphics.drawable.ColorDrawable(Values.parseColor(sel));
      else if (Values.isRef(sel)) { int id = Values.refId(sel); if (id != 0) d0 = getResources().getDrawable(id); }
    }
    setSelector(d0 != null ? d0 : getResources().getDrawable(Values.frameworkId("drawable/list_selector_background")));
    if (a instanceof Attrs) {
      Attrs at = (Attrs) a;
      String v;
      if ((v = at.a("drawSelectorOnTop")) != null) mDrawSelectorOnTop = v.equals("true");
      if ((v = at.a("stackFromBottom")) != null) mStackFromBottom = v.equals("true");
      if ((v = at.a("transcriptMode")) != null) mTranscriptMode = v.equals("alwaysScroll") ? 2 : v.equals("normal") ? 1 : 0;
      if ((v = at.a("cacheColorHint")) != null && v.startsWith("#")) mCacheColorHint = Values.parseColor(v);
      if ((v = at.a("choiceMode")) != null) mChoiceMode = v.equals("singleChoice") ? 1 : v.equals("multipleChoice") ? 2 : 0;
    }
  }

  // ---------------------------------------------------------------- adapter
  @Override public ListAdapter getAdapter() { return mAdapter; }
  @Override public void setAdapter(ListAdapter adapter) {
    if (mAdapter != null && mDataSetObserver != null) mAdapter.unregisterDataSetObserver(mDataSetObserver);
    resetList();
    mRecycler.clear();
    mAdapter = adapter;
    mOldItemCount = mItemCount;
    if (adapter != null) {
      mItemCount = adapter.getCount();
      mDataChanged = true;
      mDataSetObserver = new AdapterDataSetObserver();
      adapter.registerDataSetObserver(mDataSetObserver);
      mRecycler.setViewTypeCount(adapter.getViewTypeCount());
    } else mItemCount = 0;
    if (mCheckStates != null) mCheckStates.clear();
    mCheckedItemCount = 0;
    requestLayout();
    updateEmptyStatus();
  }
  void resetList() {
    removeAllViewsInLayout();
    mFirstPosition = 0;
    mDataChanged = false;
    mSelectedChildView = null;
    mSelectedPosition = mNextSelectedPosition = INVALID_POSITION;
    mSelectedRowId = INVALID_ROW_ID;
    mResurrectToPosition = INVALID_POSITION;
    mPendingSelection = INVALID_POSITION;
    mSelectorPosition = INVALID_POSITION;
    mSelectorRect.setEmpty();
    invalidate();
  }
  public void invalidateViews() { mDataChanged = true; requestLayout(); invalidate(); }

  // ---------------------------------------------------------------- recycling
  final class RecycleBin {
    SparseArray<View> active = new SparseArray<>();
    ArrayList<View>[] scrap;
    int typeCount = 1;
    @SuppressWarnings("unchecked") RecycleBin() { scrap = new ArrayList[] {new ArrayList<View>()}; }
    @SuppressWarnings("unchecked") void setViewTypeCount(int n) {
      if (n < 1) n = 1;
      typeCount = n;
      scrap = new ArrayList[n];
      for (int i = 0; i < n; i++) scrap[i] = new ArrayList<>();
    }
    void clear() {
      for (ArrayList<View> l : scrap) { for (View v : l) if (v.isAttachedToWindow() && v.getParent() == null) removeDetachedView(v, false); l.clear(); }
      active.clear();
    }
    boolean shouldRecycle(int type) { return type >= 0; }
    View getScrap(int position) {
      int type = mAdapter.getItemViewType(position);
      if (type < 0 || type >= scrap.length) return null;
      ArrayList<View> l = scrap[type];
      return l.isEmpty() ? null : l.remove(l.size() - 1);
    }
    void addScrap(View v) {
      LayoutParams lp = (LayoutParams) v.getLayoutParams();
      if (lp == null) return;
      int type = lp.viewType;
      if (type == ITEM_VIEW_TYPE_HEADER_OR_FOOTER) return;   // a header or footer is its adapter's own view, laid out again as it is
      if (type < 0 || type >= scrap.length) { removeDetachedView(v, false); return; }
      v.onStartTemporaryDetach();
      scrap[type].add(v);
      if (mRecyclerListener != null) mRecyclerListener.onMovedToScrapHeap(v);
    }
    void fillActive(int first) {
      active.clear();
      for (int i = 0; i < getChildCount(); i++) {
        View c = getChildAt(i);
        LayoutParams lp = (LayoutParams) c.getLayoutParams();
        if (lp != null && lp.viewType != ITEM_VIEW_TYPE_HEADER_OR_FOOTER) active.put(first + i, c);
      }
    }
    View takeActive(int position) { View v = active.get(position); if (v != null) active.remove(position); return v; }
    void scrapActive() {
      for (int i = 0; i < active.size(); i++) { View v = active.valueAt(i); if (v != null) addScrap(v); }
      active.clear();
    }
  }

  /** The view for a position, through getView with a recycled view when one fits. */
  View obtainView(int position, boolean[] isScrap) {
    isScrap[0] = false;
    View scrapView = mRecycler.getScrap(position);
    View child;
    if (scrapView != null) {
      child = mAdapter.getView(position, scrapView, this);
      if (child != scrapView) mRecycler.addScrap(scrapView);
      else { isScrap[0] = true; child.onFinishTemporaryDetach(); }
    } else child = mAdapter.getView(position, null, this);
    ViewGroup.LayoutParams vlp = child.getLayoutParams();
    LayoutParams lp;
    if (vlp == null) lp = (LayoutParams) generateDefaultLayoutParams();
    else if (!checkLayoutParams(vlp)) lp = (LayoutParams) generateLayoutParams(vlp);
    else lp = (LayoutParams) vlp;
    lp.viewType = mAdapter.getItemViewType(position);
    child.setLayoutParams2(lp);
    return child;
  }

  // ---------------------------------------------------------------- measure/layout
  @Override protected void onMeasure(int ws, int hs) {
    if (mSelector == null) useDefaultSelector();
    mListPadding.set(mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom);
  }
  void useDefaultSelector() { setSelector(getResources().getDrawable(Values.frameworkId("drawable/list_selector_background"))); }
  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
    mInLayout = true;
    if (changed) { for (int i = 0; i < getChildCount(); i++) getChildAt(i).forceLayout(); }
    layoutChildren();
    mInLayout = false;
  }
  @Override public void requestLayout() { if (!mBlockLayoutRequests && !mInLayout) super.requestLayout(); }

  abstract int itemsPerRow();
  /** lays out the views of the row starting at position; top is the row's top (down) or bottom (up); returns the row's bottom (down) or top (up) */
  abstract int layoutRow(int position, int y, boolean down, boolean recycled);
  abstract int rowGap();

  int rowStart(int pos) { int n = itemsPerRow(); return pos - pos % n; }
  int childrenTop() { return mListPadding.top; }
  int childrenBottom() { return getHeight() - mListPadding.bottom; }

  void layoutChildren() {
    final boolean blockLayoutRequests = mBlockLayoutRequests;
    if (blockLayoutRequests) return;
    mBlockLayoutRequests = true;
    try {
      invalidate();
      if (mAdapter == null) { resetList(); invokeOnItemScrollListener(); return; }
      mItemCount = mAdapter.getCount();
      if (mItemCount == 0) { mRecycler.fillActive(mFirstPosition); detachAllViewsFromParent(); mRecycler.scrapActive(); mFirstPosition = 0; invokeOnItemScrollListener(); return; }
      int childCount = getChildCount();
      int top = childCount > 0 ? getChildAt(0).getTop() : childrenTop();
      int first = mFirstPosition;
      if (mPendingSelection >= 0) {
        first = rowStart(Math.min(mPendingSelection, mItemCount - 1));
        top = childrenTop() + mPendingSelectionTop;
        mPendingSelection = INVALID_POSITION;
      }
      if (first >= mItemCount) first = rowStart(mItemCount - 1);
      boolean dataChanged = mDataChanged;
      if (dataChanged) {
        for (int i = 0; i < childCount; i++) mRecycler.addScrap(getChildAt(i));
        mRecycler.active.clear();
      } else mRecycler.fillActive(mFirstPosition);
      detachAllViewsFromParent();
      mFirstPosition = first;
      fillDown(first, top);
      correctTooHigh();
      mRecycler.scrapActive();
      mDataChanged = false;
      if (mTranscriptMode == TRANSCRIPT_MODE_ALWAYS_SCROLL && getChildCount() > 0) {
        View last = getChildAt(getChildCount() - 1);
        if (mFirstPosition + getChildCount() < mItemCount || last.getBottom() > childrenBottom()) { setSelectionInt(mItemCount - 1, true); }
      }
      updatePressedChild();
      if (mSelectedPosition >= mItemCount) setSelectedPositionInt(lookForSelectablePosition(mItemCount - 1, false));
      updateSelectedChild();
      invokeOnItemScrollListener();
    } finally {
      mBlockLayoutRequests = blockLayoutRequests;
    }
  }
  void setSelectionInt(int pos, boolean bottom) {
    // put the last row at the bottom
    detachAndScrapAll();
    int start = rowStart(pos);
    int y = childrenBottom();
    fillUpFrom(start, y);
  }
  void detachAndScrapAll() { for (int i = 0; i < getChildCount(); i++) mRecycler.addScrap(getChildAt(i)); detachAllViewsFromParent(); }

  void fillDown(int pos, int top) {
    int end = childrenBottom();
    int n = itemsPerRow();
    while (top < end && pos < mItemCount) {
      int bottom = layoutRow(pos, top, true, false);
      top = bottom + rowGap();
      pos += n;
    }
  }
  void fillUp(int pos, int bottom) {
    int start = childrenTop();
    int n = itemsPerRow();
    while (bottom > start && pos >= 0) {
      int t = layoutRow(pos, bottom, false, false);
      mFirstPosition = pos;
      bottom = t - rowGap();
      pos -= n;
    }
  }
  /** fill so the row of pos ends at bottom, then down to the end */
  void fillUpFrom(int pos, int bottom) {
    int t = layoutRow(pos, bottom, false, false);
    mFirstPosition = pos;
    fillUp(pos - itemsPerRow(), t - rowGap());
    int last = getChildCount() > 0 ? getChildAt(getChildCount() - 1).getBottom() : bottom;
    fillDown(pos + itemsPerRow(), last + rowGap());
    correctTooLow();
  }
  /** if the content ends above the bottom while there is more above, slide it down */
  void correctTooHigh() {
    int count = getChildCount();
    if (count == 0) return;
    View last = getChildAt(count - 1);
    int lastPos = mFirstPosition + count - 1;
    int end = childrenBottom();
    if (lastPos == mItemCount - 1 && last.getBottom() < end) {
      View first = getChildAt(0);
      int firstTop = first.getTop();
      int bottomOffset = end - last.getBottom();
      int start = childrenTop();
      if ((mFirstPosition > 0 || firstTop < start)) {
        if (mFirstPosition == 0) bottomOffset = Math.min(bottomOffset, start - firstTop);
        offsetChildrenTopAndBottom(bottomOffset);
        if (mFirstPosition > 0) fillUp(mFirstPosition - itemsPerRow(), getChildAt(0).getTop() - rowGap());
      }
    }
  }
  void correctTooLow() {
    int count = getChildCount();
    if (count == 0) return;
    View first = getChildAt(0);
    int start = childrenTop();
    if (mFirstPosition == 0 && first.getTop() > start) {
      offsetChildrenTopAndBottom(start - first.getTop());
      View last = getChildAt(getChildCount() - 1);
      fillDown(mFirstPosition + getChildCount(), last.getBottom() + rowGap());
    }
  }

  /** layout helpers for subclasses */
  View makeChild(int position, boolean[] recycled) {
    View c = null;
    if (!mDataChanged) c = mRecycler.takeActive(position);
    if (c != null) { recycled[0] = true; return c; }
    c = obtainView(position, mIsScrap);
    recycled[0] = mIsScrap[0];
    return c;
  }
  void addChild(View child, int index, boolean recycled) {
    ViewGroup.LayoutParams lp = child.getLayoutParams();
    if (child.getParent() == null && (recycled || child.isAttachedToWindow())) attachViewToParent(child, index, lp);
    else if (child.getParent() == null) addViewInLayout(child, index, lp, true);
    else if (child.getParent() == this) {}
  }

  // ---------------------------------------------------------------- scrolling
  /** moves the content by delta (positive: down); returns true when an edge stopped it */
  /** a mouse wheel: AXIS_VSCROLL units of the vertical scroll factor */
  @Override public boolean onGenericMotionEvent(android.view.MotionEvent event) {
    if (event.getAction() == android.view.MotionEvent.ACTION_SCROLL && event.isFromSource(android.view.InputDevice.SOURCE_CLASS_POINTER)) {
      int delta = Math.round(event.getAxisValue(android.view.MotionEvent.AXIS_VSCROLL) * getVerticalScrollFactor());
      if (delta != 0) { trackMotionScroll(delta); return true; }
    }
    return super.onGenericMotionEvent(event);
  }
  boolean trackMotionScroll(int delta) {
    final int childCount = getChildCount();
    if (childCount == 0) return true;
    final int firstTop = getChildAt(0).getTop();
    final int lastBottom = getChildAt(childCount - 1).getBottom();
    final int start = childrenTop(), end = childrenBottom();
    final int spaceAbove = start - firstTop, spaceBelow = lastBottom - end;
    final int height = getHeight() - mPaddingBottom - mPaddingTop;
    if (delta < 0) delta = Math.max(-(height - 1), delta); else delta = Math.min(height - 1, delta);
    final boolean atTop = mFirstPosition == 0 && firstTop >= start && delta >= 0;
    final boolean atBottom = mFirstPosition + childCount == mItemCount && lastBottom <= end && delta <= 0;
    if (atTop || atBottom) return delta != 0;
    if (delta > 0 && mFirstPosition == 0) delta = Math.min(delta, spaceAbove);
    if (delta < 0 && mFirstPosition + childCount == mItemCount) delta = Math.max(delta, -spaceBelow);
    if (delta == 0) return true;
    offsetChildrenTopAndBottom(delta);
    // recycle what left the screen
    if (delta < 0) {
      int n = itemsPerRow();
      while (getChildCount() > n) {
        int rowEnd = Math.min(n, getChildCount());
        int rowBottom = 0;
        for (int i = 0; i < rowEnd; i++) rowBottom = Math.max(rowBottom, getChildAt(i).getBottom());
        if (rowBottom >= start) break;
        for (int i = 0; i < rowEnd; i++) { View c = getChildAt(0); detachViewFromParent(0); mRecycler.addScrap(c); }
        mFirstPosition += rowEnd;
      }
      View last = getChildAt(getChildCount() - 1);
      fillDown(mFirstPosition + getChildCount(), last.getBottom() + rowGap());
    } else {
      int n = itemsPerRow();
      while (getChildCount() > n) {
        int lastRowStart = rowStart(mFirstPosition + getChildCount() - 1) - mFirstPosition;
        int rowTop = Integer.MAX_VALUE;
        for (int i = lastRowStart; i < getChildCount(); i++) rowTop = Math.min(rowTop, getChildAt(i).getTop());
        if (rowTop <= end) break;
        for (int i = getChildCount() - 1; i >= lastRowStart; i--) { View c = getChildAt(i); detachViewFromParent(i); mRecycler.addScrap(c); }
      }
      if (mFirstPosition > 0) fillUp(mFirstPosition - itemsPerRow(), getChildAt(0).getTop() - rowGap());
    }
    invokeOnItemScrollListener();
    updateSelectedChild();
    invalidate();
    onScrollChanged(0, 0, 0, 0);
    return false;
  }
  void invokeOnItemScrollListener() { if (mOnScrollListener != null) mOnScrollListener.onScroll(this, mFirstPosition, getChildCount(), mItemCount); }
  void reportScrollStateChange(int s) { if (s != mLastScrollState && mOnScrollListener != null) { mLastScrollState = s; mOnScrollListener.onScrollStateChanged(this, s); } else mLastScrollState = s; }
  public void setOnScrollListener(OnScrollListener l) { mOnScrollListener = l; invokeOnItemScrollListener(); }
  public void setRecyclerListener(RecyclerListener l) { mRecyclerListener = l; }

  final class FlingRunnable implements Runnable {
    final Scroller scroller = new Scroller(getContext());
    int lastFlingY;
    void start(int initialVelocity) {
      int initialY = initialVelocity < 0 ? Integer.MAX_VALUE / 2 : 0;
      lastFlingY = initialY;
      scroller.fling(0, initialY, 0, initialVelocity, 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
      mTouchMode = TOUCH_MODE_FLING;
      postOnAnimation(this);
    }
    void end() { mTouchMode = TOUCH_MODE_REST; removeCallbacks(this); reportScrollStateChange(OnScrollListener.SCROLL_STATE_IDLE); scroller.abortAnimation(); }
    public void run() {
      if (mTouchMode != TOUCH_MODE_FLING) return;
      if (mItemCount == 0 || getChildCount() == 0) { end(); return; }
      boolean more = scroller.computeScrollOffset();
      int y = scroller.getCurrY();
      int delta = lastFlingY - y;
      boolean atEnd = trackMotionScroll(delta);
      if (more && !atEnd) { lastFlingY = y; postDelayed(this, 16); }
      else end();
    }
  }
  public void smoothScrollBy(int distance, int duration) { trackMotionScroll(-distance); }
  public void smoothScrollToPosition(int position) {
    if (position >= mFirstPosition && position < mFirstPosition + getChildCount()) {
      View c = getChildAt(position - mFirstPosition);
      if (c.getTop() >= childrenTop() && c.getBottom() <= childrenBottom()) return;
    }
    setSelection(position);
  }
  public void smoothScrollToPosition(int position, int bound) { smoothScrollToPosition(position); }
  public void smoothScrollToPositionFromTop(int position, int offset) { setSelectionFromTop2(position, offset); }
  void setSelectionFromTop2(int position, int y) { mPendingSelection = position; mPendingSelectionTop = y; requestLayout2(); }
  void requestLayout2() { super.requestLayout(); invalidate(); }

  // ---------------------------------------------------------------- touch
  public int pointToPosition(int x, int y) {
    for (int i = getChildCount() - 1; i >= 0; i--) {
      View c = getChildAt(i);
      if (c.getVisibility() == VISIBLE && x >= c.getLeft() && x < c.getRight() && y >= c.getTop() && y < c.getBottom()) return mFirstPosition + i;
    }
    return INVALID_POSITION;
  }
  public long pointToRowId(int x, int y) { int p = pointToPosition(x, y); return p >= 0 ? mAdapter.getItemId(p) : INVALID_ROW_ID; }
  int findMotionRow(int y) {
    int n = getChildCount();
    for (int i = 0; i < n; i++) if (y <= getChildAt(i).getBottom()) return mFirstPosition + i;
    return INVALID_POSITION;
  }
  @Override public boolean shouldDelayChildPressedState() { return true; }
  @Override public boolean onInterceptTouchEvent(MotionEvent ev) {
    int action = ev.getActionMasked();
    switch (action) {
      case MotionEvent.ACTION_DOWN: {
        int touchMode = mTouchMode;
        final int x = (int) ev.getX(), y = (int) ev.getY();
        mActivePointerId = ev.getPointerId(0);
        int motionPosition = findMotionRow(y);
        if (touchMode != TOUCH_MODE_FLING && motionPosition >= 0) mTouchMode = TOUCH_MODE_DOWN;
        mMotionX = x; mMotionY = y; mMotionPosition = motionPosition; mLastY = Integer.MIN_VALUE;
        if (touchMode == TOUCH_MODE_FLING) { if (mFlingRunnable != null) mFlingRunnable.end(); mTouchMode = TOUCH_MODE_SCROLL; mLastY = y; return true; }
        break;
      }
      case MotionEvent.ACTION_MOVE: {
        if (mTouchMode == TOUCH_MODE_DOWN) {
          int pi = ev.findPointerIndex(mActivePointerId);
          if (pi < 0) pi = 0;
          final int y = (int) ev.getY(pi);
          if (startScrollIfNeeded(y - mMotionY)) { mLastY = y; return true; }
        }
        break;
      }
      case MotionEvent.ACTION_CANCEL: case MotionEvent.ACTION_UP:
        mTouchMode = TOUCH_MODE_REST; mActivePointerId = -1; reportScrollStateChange(OnScrollListener.SCROLL_STATE_IDLE); break;
    }
    return false;
  }
  boolean startScrollIfNeeded(int deltaY) {
    if (Math.abs(deltaY) > mTouchSlop) {
      mTouchMode = TOUCH_MODE_SCROLL;
      mMotionCorrection = deltaY > 0 ? mTouchSlop : -mTouchSlop;
      removeCallbacks(mPendingCheckForTap);
      removeCallbacks(mPendingCheckForLongPress);
      setPressedChild(null);
      if (getParent() != null) getParent().requestDisallowInterceptTouchEvent(true);
      reportScrollStateChange(OnScrollListener.SCROLL_STATE_TOUCH_SCROLL);
      return true;
    }
    return false;
  }
  void setPressedChild(View c) {
    if (mPressedChild != null && mPressedChild != c) mPressedChild.setPressed(false);
    mPressedChild = c;
    if (c != null) {
      c.setPressed(true);
      mSelectorPosition = mMotionPosition;
      mSelectorRect.set(c.getLeft(), c.getTop(), c.getRight(), c.getBottom());
      if (mSelector != null) mSelector.setState(PRESSED_ENABLED_STATE_SET2);
    } else {
      mSelectorRect.setEmpty();
      if (mSelector != null) mSelector.setState(EMPTY_STATE_SET);
    }
    setPressed(c != null);
    invalidate();
  }
  static final int[] PRESSED_ENABLED_STATE_SET2 = {android.R.attr.state_pressed, android.R.attr.state_enabled};
  @Override protected void dispatchSetPressed(boolean pressed) {}
  void updatePressedChild() {
    if (mPressedChild != null && mPressedChild.getParent() != this) { mPressedChild.setPressed(false); mPressedChild = null; mSelectorRect.setEmpty(); }
  }
  @Override public boolean onTouchEvent(MotionEvent ev) {
    if (!isEnabled()) return isClickable() || isLongClickable();
    if (mVelocityTracker == null) mVelocityTracker = VelocityTracker.obtain();
    mVelocityTracker.addMovement(ev);
    final int action = ev.getActionMasked();
    switch (action) {
      case MotionEvent.ACTION_DOWN: {
        mActivePointerId = ev.getPointerId(0);
        final int x = (int) ev.getX(), y = (int) ev.getY();
        int motionPosition = pointToPosition(x, y);
        if (!mDataChanged) {
          if (mTouchMode != TOUCH_MODE_FLING && motionPosition >= 0 && mAdapter.isEnabled(motionPosition)) {
            mTouchMode = TOUCH_MODE_DOWN;
            if (mPendingCheckForTap == null) mPendingCheckForTap = this::checkForTap;
            postDelayed(mPendingCheckForTap, ViewConfiguration.getTapTimeout());
          } else if (mTouchMode == TOUCH_MODE_FLING) {
            if (mFlingRunnable != null) mFlingRunnable.end();
            mTouchMode = TOUCH_MODE_SCROLL;
            mMotionCorrection = 0;
            motionPosition = findMotionRow(y);
          } else mTouchMode = TOUCH_MODE_DOWN;
        }
        mMotionX = x; mMotionY = y; mMotionPosition = motionPosition; mLastY = Integer.MIN_VALUE;
        break;
      }
      case MotionEvent.ACTION_MOVE: {
        int pi = ev.findPointerIndex(mActivePointerId);
        if (pi < 0) pi = 0;
        final int y = (int) ev.getY(pi);
        switch (mTouchMode) {
          case TOUCH_MODE_DOWN: case TOUCH_MODE_TAP: case TOUCH_MODE_DONE_WAITING:
            if (startScrollIfNeeded(y - mMotionY)) mLastY = y;
            break;
          case TOUCH_MODE_SCROLL:
            if (y != mLastY) {
              int incremental = mLastY != Integer.MIN_VALUE ? y - mLastY : y - mMotionY - mMotionCorrection;
              trackMotionScroll(incremental);
              mLastY = y;
            }
            break;
        }
        break;
      }
      case MotionEvent.ACTION_UP: {
        switch (mTouchMode) {
          case TOUCH_MODE_DOWN: case TOUCH_MODE_TAP: case TOUCH_MODE_DONE_WAITING: {
            final int motionPosition = mMotionPosition;
            final View child = motionPosition >= 0 ? getChildAt(motionPosition - mFirstPosition) : null;
            if (child != null && !child.hasExplicitFocusable()) {
              final int touchMode = mTouchMode;
              final long id = mAdapter.getItemId(motionPosition);
              removeCallbacks(mPendingCheckForTap);
              removeCallbacks(mPendingCheckForLongPress);
              if (!mDataChanged && mAdapter.isEnabled(motionPosition)) {
                mTouchMode = TOUCH_MODE_TAP;
                setPressedChild(child);
                final View fc = child;
                mPerformClick = () -> {
                  setPressedChild(null);
                  mTouchMode = TOUCH_MODE_REST;
                  if (!mDataChanged && mAdapter != null && motionPosition < mAdapter.getCount() && fc.getParent() == AbsListView.this) performItemClick(fc, motionPosition, id);
                };
                postDelayed(mPerformClick, ViewConfiguration.getPressedStateDuration());
                return true;
              }
            }
            mTouchMode = TOUCH_MODE_REST;
            setPressedChild(null);
            break;
          }
          case TOUCH_MODE_SCROLL: {
            final VelocityTracker vt = mVelocityTracker;
            vt.computeCurrentVelocity(1000, mMaximumVelocity);
            final int initialVelocity = (int) vt.getYVelocity();
            if (Math.abs(initialVelocity) > mMinimumVelocity && getChildCount() > 0) {
              if (mFlingRunnable == null) mFlingRunnable = new FlingRunnable();
              reportScrollStateChange(OnScrollListener.SCROLL_STATE_FLING);
              mFlingRunnable.start(-initialVelocity);
            } else {
              mTouchMode = TOUCH_MODE_REST;
              reportScrollStateChange(OnScrollListener.SCROLL_STATE_IDLE);
            }
            break;
          }
        }
        setPressed(false);
        invalidate();
        if (mVelocityTracker != null) { mVelocityTracker.recycle(); mVelocityTracker = null; }
        mActivePointerId = -1;
        break;
      }
      case MotionEvent.ACTION_CANCEL: {
        mTouchMode = TOUCH_MODE_REST;
        setPressedChild(null);
        removeCallbacks(mPendingCheckForTap);
        removeCallbacks(mPendingCheckForLongPress);
        if (mVelocityTracker != null) { mVelocityTracker.recycle(); mVelocityTracker = null; }
        mActivePointerId = -1;
        break;
      }
    }
    return true;
  }
  private void checkForTap() {
    if (mTouchMode == TOUCH_MODE_DOWN) {
      mTouchMode = TOUCH_MODE_TAP;
      final View child = getChildAt(mMotionPosition - mFirstPosition);
      if (child != null && !child.hasExplicitFocusable()) {
        if (!mDataChanged) {
          setPressedChild(child);
          if (isLongClickable()) {
            if (mPendingCheckForLongPress == null) mPendingCheckForLongPress = this::checkForLongPress;
            postDelayed(mPendingCheckForLongPress, ViewConfiguration.getLongPressTimeout());
          } else mTouchMode = TOUCH_MODE_DONE_WAITING;
        } else mTouchMode = TOUCH_MODE_DONE_WAITING;
      }
    }
  }
  private void checkForLongPress() {
    final int motionPosition = mMotionPosition;
    final View child = getChildAt(motionPosition - mFirstPosition);
    if (child != null && mTouchMode == TOUCH_MODE_TAP && !mDataChanged) {
      // the item long-click listener, else the list's context menu for that item (registerForContextMenu(listView))
      boolean handled = performLongPress(child, motionPosition, mAdapter.getItemId(motionPosition));
      if (handled) { mTouchMode = TOUCH_MODE_REST; setPressedChild(null); }
      else mTouchMode = TOUCH_MODE_DONE_WAITING;
    }
  }
  ContextMenu.ContextMenuInfo mContextMenuInfo;
  @Override protected ContextMenu.ContextMenuInfo getContextMenuInfo() { return mContextMenuInfo; }
  @Override public boolean showContextMenuForChild(View originalView) {
    int position = getPositionForView(originalView);
    if (position < 0) return false;
    mContextMenuInfo = new AdapterContextMenuInfo(getChildAt(position - mFirstPosition), position, mAdapter.getItemId(position));
    return showContextMenu();
  }
  /** choice modes check the item (in an active modal mode a click only toggles it); then the item click listener */
  @Override public boolean performItemClick(View view, int position, long id) {
    boolean dispatch = true;
    if (mChoiceMode == CHOICE_MODE_SINGLE || mChoiceMode == CHOICE_MODE_MULTIPLE || (mChoiceMode == CHOICE_MODE_MULTIPLE_MODAL && mChoiceActionMode != null)) {
      if (mCheckStates == null) mCheckStates = new android.util.SparseBooleanArray();
      if (mChoiceMode == CHOICE_MODE_SINGLE) { mCheckStates.clear(); mCheckStates.put(position, true); }
      else {
        boolean checked = !mCheckStates.get(position, false);
        mCheckStates.put(position, checked);
        if (mChoiceActionMode != null) { modalCheckChanged(position, checked); dispatch = false; }
      }
      requestLayout2();
    }
    return dispatch ? super.performItemClick(view, position, id) : true;
  }

  // ---------------------------------------------------------------- drawing
  @Override protected void dispatchDraw(Canvas canvas) {
    if (!mDrawSelectorOnTop) drawSelector(canvas);
    super.dispatchDraw(canvas);
    if (mDrawSelectorOnTop) drawSelector(canvas);
  }
  void drawSelector(Canvas c) {
    if (mSelector != null && !mSelectorRect.isEmpty() && mPressedChild != null) {
      mSelectorRect.set(mPressedChild.getLeft(), mPressedChild.getTop(), mPressedChild.getRight(), mPressedChild.getBottom());
      mSelector.setBounds(mSelectorRect);
      mSelector.draw(c);
      return;
    }
    // out of touch mode, the focused list shows its selected item
    if (mSelector != null && shouldShowSelector() && mSelectedPosition >= mFirstPosition && mSelectedPosition < mFirstPosition + getChildCount()) {
      View s = getChildAt(mSelectedPosition - mFirstPosition);
      mSelector.setState(getDrawableState());
      mSelector.setBounds(s.getLeft(), s.getTop(), s.getRight(), s.getBottom());
      mSelector.draw(c);
    }
  }
  /** focus searches start from the selected item, not the whole list */
  @Override public void getFocusedRect(Rect r) {
    View view = getSelectedView();
    if (view != null && view.getParent() == this) { view.getFocusedRect(r); offsetDescendantRectToMyCoords(view, r); }
    else super.getFocusedRect(r);
  }
  /** the selector marks the selected item while the list has focus out of touch mode */
  boolean shouldShowSelector() { return isFocused() && !isInTouchMode(); }

  // ---------------------------------------------------------------- selection (out of touch mode) and keys
  int mResurrectToPosition = INVALID_POSITION, mOldSelectedPosition = INVALID_POSITION;
  long mSelectedRowId = INVALID_ROW_ID, mOldSelectedRowId = INVALID_ROW_ID;
  private View mSelectedChildView;
  private Runnable mSelectionNotifier, mPendingCheckForKeyLongPress;
  private ViewTreeObserver.OnTouchModeChangeListener mTouchModeListener;
  /** position, or the nearest enabled one in the direction; none in touch mode */
  int lookForSelectablePosition(int position, boolean lookDown) {
    ListAdapter a = mAdapter;
    if (a == null || isInTouchMode()) return INVALID_POSITION;
    int count = a.getCount();
    if (!a.areAllItemsEnabled()) {
      if (lookDown) { position = Math.max(0, position); while (position < count && !a.isEnabled(position)) position++; }
      else { position = Math.min(position, count - 1); while (position >= 0 && !a.isEnabled(position)) position--; }
    }
    return position < 0 || position >= count ? INVALID_POSITION : position;
  }
  void setSelectedPositionInt(int position) {
    mSelectedPosition = position;
    mNextSelectedPosition = position;
    mSelectedRowId = position >= 0 && mAdapter != null && position < mAdapter.getCount() ? mAdapter.getItemId(position) : INVALID_ROW_ID;
    updateSelectedChild();
    invalidate();
    checkSelectionChanged();
  }
  /** the selected item's view has state_selected while the selector shows */
  void updateSelectedChild() {
    View v = shouldShowSelector() && mSelectedPosition >= mFirstPosition && mSelectedPosition < mFirstPosition + getChildCount() ? getChildAt(mSelectedPosition - mFirstPosition) : null;
    if (mSelectedChildView != null && mSelectedChildView != v) mSelectedChildView.setSelected(false);
    mSelectedChildView = v;
    if (v != null && !v.isSelected()) v.setSelected(true);
  }
  /** OnItemSelectedListener: told (after the current event) when the selection moved */
  void checkSelectionChanged() {
    if (mSelectedPosition == mOldSelectedPosition && mSelectedRowId == mOldSelectedRowId) return;
    mOldSelectedPosition = mSelectedPosition;
    mOldSelectedRowId = mSelectedRowId;
    if (mOnItemSelectedListener == null) return;
    if (mSelectionNotifier == null) mSelectionNotifier = () -> {
      OnItemSelectedListener l = mOnItemSelectedListener;
      if (l == null) return;
      int s = mSelectedPosition;
      if (s >= 0 && mAdapter != null && s < mAdapter.getCount()) l.onItemSelected(this, getSelectedView(), s, mAdapter.getItemId(s));
      else l.onNothingSelected(this);
    };
    removeCallbacks(mSelectionNotifier);
    post(mSelectionNotifier);
  }
  /** selects the item to resume from (the one selected before touch mode if still on screen, else the first fully visible) */
  boolean resurrectSelection() {
    final int childCount = getChildCount();
    if (childCount <= 0 || isInTouchMode()) return false;
    int sel = INVALID_POSITION;
    int r = mResurrectToPosition;
    if (r >= mFirstPosition && r < mFirstPosition + childCount) {
      View c = getChildAt(r - mFirstPosition);
      if (c.getBottom() > childrenTop() && c.getTop() < childrenBottom()) sel = r;
    }
    if (sel < 0) {
      for (int i = 0; i < childCount; i++) if (getChildAt(i).getTop() >= childrenTop()) { sel = mFirstPosition + i; break; }
      if (sel < 0) sel = mFirstPosition;
    }
    int p = lookForSelectablePosition(sel, true);
    if (p < 0) p = lookForSelectablePosition(sel, false);
    if (p < 0) return false;
    mResurrectToPosition = INVALID_POSITION;
    setSelectedPositionInt(p);
    ensureSelectionVisible(p, View.FOCUS_DOWN);
    return true;
  }
  boolean resurrectSelectionIfNeeded() { return mSelectedPosition < 0 && resurrectSelection(); }
  /** scrolls the selected item fully into view (or lays the list out around it when it is off screen) */
  void ensureSelectionVisible(int pos, int direction) {
    if (pos < 0) return;
    int n = getChildCount();
    if (pos >= mFirstPosition && pos < mFirstPosition + n) {
      View c = getChildAt(pos - mFirstPosition);
      int top = childrenTop(), bottom = childrenBottom();
      if (c.getBottom() > bottom && c.getTop() > top) trackMotionScroll(Math.max(top - c.getTop(), bottom - c.getBottom()));
      else if (c.getTop() < top) trackMotionScroll(top - c.getTop());
    } else if (direction == View.FOCUS_DOWN || direction == View.FOCUS_RIGHT) setSelectionInt(pos, true);
    else {
      detachAndScrapAll();
      mFirstPosition = rowStart(pos);
      fillDown(mFirstPosition, childrenTop());
      correctTooHigh();
      invokeOnItemScrollListener();
    }
    updateSelectedChild();
    invalidate();
  }
  /** moves the selection one step (a row for up/down, an item for left/right in a grid); false when it can't move */
  boolean arrowScroll(int direction) {
    if (mAdapter == null || mItemCount == 0) return false;
    int sel = mSelectedPosition, n = itemsPerRow();
    if (sel < 0) return resurrectSelection();
    int rowStart = sel - sel % n, rowEnd = Math.min(rowStart + n - 1, mItemCount - 1), target = INVALID_POSITION;
    switch (direction) {
      case View.FOCUS_UP: if (rowStart > 0) target = n == 1 ? lookForSelectablePosition(sel - 1, false) : Math.max(0, sel - n); break;
      case View.FOCUS_DOWN: if (rowEnd < mItemCount - 1) target = n == 1 ? lookForSelectablePosition(sel + 1, true) : Math.min(sel + n, mItemCount - 1); break;
      case View.FOCUS_LEFT: if (n > 1 && sel > rowStart) target = sel - 1; break;
      case View.FOCUS_RIGHT: if (n > 1 && sel < rowEnd) target = sel + 1; break;
    }
    if (target < 0) {
      // nothing more to select: show the rest of a partly visible end item, if any
      int cc = getChildCount();
      if (direction == View.FOCUS_DOWN && cc > 0 && getChildAt(cc - 1).getBottom() > childrenBottom()) { trackMotionScroll(childrenBottom() - getChildAt(cc - 1).getBottom()); return true; }
      if (direction == View.FOCUS_UP && cc > 0 && getChildAt(0).getTop() < childrenTop()) { trackMotionScroll(childrenTop() - getChildAt(0).getTop()); return true; }
      return false;
    }
    setSelectedPositionInt(target);
    ensureSelectionVisible(target, direction);
    playSoundEffect(SoundEffectConstants.getContantForFocusDirection(direction));
    return true;
  }
  /** a page: the selection moves by a screenful of items and its row goes to the top (or the list's end) */
  boolean pageScroll(int direction) {
    if (mAdapter == null || mItemCount == 0) return false;
    boolean down = direction == View.FOCUS_DOWN;
    int page = Math.max(1, getChildCount() - itemsPerRow());
    int next = down ? Math.min(mItemCount - 1, mSelectedPosition + page) : Math.max(0, mSelectedPosition - page);
    int p = lookForSelectablePosition(next, down);
    if (p < 0 || p == mSelectedPosition) return false;
    setSelectedPositionInt(p);
    ensureSelectionVisible(p, direction);
    return true;
  }
  /** home/end: the first or last item */
  boolean fullScroll(int direction) {
    if (mAdapter == null || mItemCount == 0) return false;
    boolean down = direction == View.FOCUS_DOWN;
    int p = lookForSelectablePosition(down ? mItemCount - 1 : 0, !down);
    if (p < 0 || p == mSelectedPosition) return false;
    setSelectedPositionInt(p);
    ensureSelectionVisible(p, direction);
    return true;
  }
  /** a confirm key down: the selected item shows pressed, and a long press starts */
  void keyPressed() {
    if (!isEnabled() || !isClickable() || mSelectedPosition < 0) return;
    View v = getChildAt(mSelectedPosition - mFirstPosition);
    if (v == null || v.hasExplicitFocusable()) return;
    mMotionPosition = mSelectedPosition;
    setPressedChild(v);
    if (isLongClickable() && !mDataChanged) {
      if (mPendingCheckForKeyLongPress == null) mPendingCheckForKeyLongPress = () -> {
        int pos = mSelectedPosition;
        View c = pos >= 0 ? getChildAt(pos - mFirstPosition) : null;
        if (!isPressed() || c == null || mDataChanged) return;
        if (performLongPress(c, pos, mAdapter.getItemId(pos))) setPressedChild(null);
      };
      removeCallbacks(mPendingCheckForKeyLongPress);
      postDelayed(mPendingCheckForKeyLongPress, ViewConfiguration.getLongPressTimeout());
    }
  }
  boolean performLongPress(View child, int position, long id) {
    if (mChoiceMode == CHOICE_MODE_MULTIPLE_MODAL) {
      if (mChoiceActionMode == null && mMultiChoiceModeListener != null && (mChoiceActionMode = startActionMode(mModalCallback)) != null) {
        setItemChecked(position, true);
        performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
      }
      return true;
    }
    boolean handled = false;
    if (mOnItemLongClickListener != null) handled = mOnItemLongClickListener.onItemLongClick(this, child, position, id);
    if (!handled) { mContextMenuInfo = new AdapterContextMenuInfo(child, position, id); handled = showContextMenu(); }
    return handled;
  }
  static boolean isConfirmKey2(int keyCode) {
    return keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_SPACE || keyCode == KeyEvent.KEYCODE_NUMPAD_ENTER;
  }
  @Override public boolean onKeyDown(int keyCode, KeyEvent event) { return commonKey(keyCode, 1, event); }
  @Override public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) { return commonKey(keyCode, repeatCount, event); }
  @Override public boolean onKeyUp(int keyCode, KeyEvent event) {
    if (isConfirmKey2(keyCode)) {
      if (!isEnabled()) return true;
      if (isClickable() && isPressed() && mSelectedPosition >= 0 && mAdapter != null && mSelectedPosition < mAdapter.getCount()) {
        if (mPendingCheckForKeyLongPress != null) removeCallbacks(mPendingCheckForKeyLongPress);
        final View view = getChildAt(mSelectedPosition - mFirstPosition);
        setPressedChild(null);
        if (view != null && !event.isCanceled()) performItemClick(view, mSelectedPosition, mAdapter.getItemId(mSelectedPosition));
        return true;
      }
    }
    return super.onKeyUp(keyCode, event);
  }
  /** arrows move the selection (left/right only in a grid), page up/down and home/end jump, confirm keys click; at an end the key goes on to focus navigation */
  boolean commonKey(int keyCode, int count, KeyEvent event) {
    if (mAdapter == null || !isAttachedToWindow()) return false;
    if (mDataChanged) layoutChildren();
    boolean handled = false;
    int action = event.getAction();
    if (isConfirmKey2(keyCode) && event.hasNoModifiers() && action != KeyEvent.ACTION_UP) {
      handled = resurrectSelectionIfNeeded();
      if (!handled && event.getRepeatCount() == 0 && getChildCount() > 0) { keyPressed(); handled = true; }
    }
    boolean grid = itemsPerRow() > 1;
    if (!handled && action != KeyEvent.ACTION_UP) {
      switch (keyCode) {
        case KeyEvent.KEYCODE_DPAD_UP: case KeyEvent.KEYCODE_DPAD_DOWN: {
          int dir = keyCode == KeyEvent.KEYCODE_DPAD_UP ? View.FOCUS_UP : View.FOCUS_DOWN;
          if (event.hasNoModifiers()) { handled = resurrectSelectionIfNeeded(); if (!handled) while (count-- > 0) { if (arrowScroll(dir)) handled = true; else break; } }
          else if (event.hasModifiers(KeyEvent.META_ALT_ON)) handled = resurrectSelectionIfNeeded() || fullScroll(dir);
          break;
        }
        case KeyEvent.KEYCODE_DPAD_LEFT: case KeyEvent.KEYCODE_DPAD_RIGHT:
          if (grid && event.hasNoModifiers()) handled = resurrectSelectionIfNeeded() || arrowScroll(keyCode == KeyEvent.KEYCODE_DPAD_LEFT ? View.FOCUS_LEFT : View.FOCUS_RIGHT);
          break;
        case KeyEvent.KEYCODE_PAGE_UP: case KeyEvent.KEYCODE_PAGE_DOWN: {
          int dir = keyCode == KeyEvent.KEYCODE_PAGE_UP ? View.FOCUS_UP : View.FOCUS_DOWN;
          if (event.hasNoModifiers()) handled = resurrectSelectionIfNeeded() || pageScroll(dir);
          else if (event.hasModifiers(KeyEvent.META_ALT_ON)) handled = resurrectSelectionIfNeeded() || fullScroll(dir);
          break;
        }
        case KeyEvent.KEYCODE_MOVE_HOME: if (event.hasNoModifiers()) handled = resurrectSelectionIfNeeded() || fullScroll(View.FOCUS_UP); break;
        case KeyEvent.KEYCODE_MOVE_END: if (event.hasNoModifiers()) handled = resurrectSelectionIfNeeded() || fullScroll(View.FOCUS_DOWN); break;
        case KeyEvent.KEYCODE_TAB:
          if (event.hasNoModifiers()) handled = resurrectSelectionIfNeeded() || arrowScroll(grid ? View.FOCUS_RIGHT : View.FOCUS_DOWN);
          else if (event.hasModifiers(KeyEvent.META_SHIFT_ON)) handled = resurrectSelectionIfNeeded() || arrowScroll(grid ? View.FOCUS_LEFT : View.FOCUS_UP);
          break;
      }
    }
    if (handled) return true;
    switch (action) {
      case KeyEvent.ACTION_DOWN: return super.onKeyDown(keyCode, event);
      case KeyEvent.ACTION_UP: return super.onKeyUp(keyCode, event);
      case KeyEvent.ACTION_MULTIPLE: return super.onKeyMultiple(keyCode, count, event);
      default: return false;
    }
  }
  /** gaining focus out of touch mode selects an item: the one nearest the previously focused rect, the last when coming from below, else the first visible */
  @Override protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
    super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
    if (gainFocus && mSelectedPosition < 0 && !isInTouchMode() && getChildCount() > 0) {
      int n = getChildCount(), pick = -1;
      if (previouslyFocusedRect != null && direction != View.FOCUS_FORWARD && direction != View.FOCUS_BACKWARD) {
        Rect r = new Rect(previouslyFocusedRect);
        int best = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
          View c = getChildAt(i);
          int d = Math.abs((c.getTop() + c.getBottom()) / 2 - (r.top + r.bottom) / 2) + (itemsPerRow() > 1 ? Math.abs((c.getLeft() + c.getRight()) / 2 - (r.left + r.right) / 2) : 0);
          if (c.getBottom() > childrenTop() && c.getTop() < childrenBottom() && d < best) { best = d; pick = mFirstPosition + i; }
        }
      } else if (direction == View.FOCUS_UP || direction == View.FOCUS_BACKWARD) {
        for (int i = n - 1; i >= 0; i--) if (getChildAt(i).getBottom() <= childrenBottom()) { pick = mFirstPosition + i; break; }
      }
      if (pick >= 0) { int p = lookForSelectablePosition(pick, true); if (p < 0) p = lookForSelectablePosition(pick, false); if (p >= 0) { setSelectedPositionInt(p); ensureSelectionVisible(p, direction); } }
      else resurrectSelection();
    }
    updateSelectedChild();
    invalidate();
  }
  /** entering touch mode hides the selection (remembered for later) */
  public void onTouchModeChanged(boolean inTouchMode) {
    if (inTouchMode) {
      if (mSelectedPosition >= 0) { mResurrectToPosition = mSelectedPosition; setSelectedPositionInt(INVALID_POSITION); }
      setPressedChild(null);
    }
    updateSelectedChild();
    invalidate();
  }
  public void setSelector(int resID) { setSelector(getResources().getDrawable(resID)); }
  public void setSelector(Drawable sel) {
    if (mSelector != null) { mSelector.setCallback(null); unscheduleDrawable(mSelector); }
    mSelector = sel;
    if (sel != null) sel.setCallback(this);
  }
  public Drawable getSelector() { return mSelector; }
  public void setDrawSelectorOnTop(boolean b) { mDrawSelectorOnTop = b; }
  public boolean isDrawSelectorOnTop() { return mDrawSelectorOnTop; }
  boolean mSelectedChildViewEnabled;
  public boolean isSelectedChildViewEnabled() { return mSelectedChildViewEnabled; }
  public void setSelectedChildViewEnabled(boolean selectedChildViewEnabled) { mSelectedChildViewEnabled = selectedChildViewEnabled; }
  int mTopEdgeEffectColor, mBottomEdgeEffectColor, mFastScrollStyle;
  boolean mFastScrollAlwaysVisible;
  public void setEdgeEffectColor(int color) { mTopEdgeEffectColor = mBottomEdgeEffectColor = color; }
  public void setTopEdgeEffectColor(int color) { mTopEdgeEffectColor = color; }
  public void setBottomEdgeEffectColor(int color) { mBottomEdgeEffectColor = color; }
  public int getTopEdgeEffectColor() { return mTopEdgeEffectColor; }
  public int getBottomEdgeEffectColor() { return mBottomEdgeEffectColor; }
  public void setFastScrollStyle(int styleResId) { mFastScrollStyle = styleResId; }
  public boolean isFastScrollAlwaysVisible() { return mFastScrollEnabled && mFastScrollAlwaysVisible; }
  public boolean isSmoothScrollbarEnabled() { return mSmoothScrollbarEnabled; }
  /** the adapter's data changed (layoutChildren reconciles positions); no remote adapters */
  protected void handleDataChanged() { mDataChanged = true; }
  public void deferNotifyDataSetChanged() {}
  public void setRemoteViewsAdapter(android.content.Intent intent) {}
  public boolean onRemoteAdapterConnected() { return false; }
  public void onRemoteAdapterDisconnected() {}
  public void onInitializeAccessibilityNodeInfoForItem(View view, int position, android.view.accessibility.AccessibilityNodeInfo info) {
    if (position == INVALID_POSITION || mAdapter == null) return;
    boolean enabled = mAdapter.isEnabled(position);
    info.setEnabled(enabled && isEnabled());
    if (position == getSelectedItemPosition()) info.setSelected(true);
    if (enabled && isClickable()) info.setClickable(true);
    if (enabled && isLongClickable()) info.setLongClickable(true);
  }
  @Override protected boolean verifyDrawable(Drawable dr) { return mSelector == dr || super.verifyDrawable(dr); }

  // ---------------------------------------------------------------- scrolling API
  /** whether the list can scroll by direction (positive: down) */
  public boolean canScrollList(int direction) {
    final int childCount = getChildCount();
    if (childCount == 0) return false;
    if (direction > 0) return mFirstPosition + childCount < mItemCount || getChildAt(childCount - 1).getBottom() > childrenBottom();
    return mFirstPosition > 0 || getChildAt(0).getTop() < childrenTop();
  }
  /** scrolls the content by y pixels (positive: down the list) */
  public void scrollListBy(int y) { trackMotionScroll(-y); }
  /** a fling with this velocity (pixels per second; positive: down the list) */
  public void fling(int velocityY) {
    if (getChildCount() == 0) return;
    if (mFlingRunnable == null) mFlingRunnable = new FlingRunnable();
    reportScrollStateChange(OnScrollListener.SCROLL_STATE_FLING);
    mFlingRunnable.start(velocityY);
  }
  public void smoothScrollToPositionFromTop(int position, int offset, int duration) { setSelectionFromTop2(position, offset); }
  /** scrolls position to y pixels from the top (out of touch mode it also becomes the selection) */
  public void setSelectionFromTop(int position, int y) {
    if (mAdapter == null) return;
    if (!isInTouchMode()) { int p = lookForSelectablePosition(position, true); if (p >= 0) setSelectedPositionInt(p); }
    else mResurrectToPosition = position;
    setSelectionFromTop2(Math.max(0, position), y);
  }
  View mScrollUp, mScrollDown;
  /** views shown while there is more to scroll above / below */
  public void setScrollIndicators(View up, View down) { mScrollUp = up; mScrollDown = down; updateScrollIndicators(); }
  void updateScrollIndicators() {
    if (mScrollUp != null) mScrollUp.setVisibility(canScrollList(-1) ? View.VISIBLE : View.INVISIBLE);
    if (mScrollDown != null) mScrollDown.setVisibility(canScrollList(1) ? View.VISIBLE : View.INVISIBLE);
  }
  @Override protected void onScrollChanged(int l, int t, int oldl, int oldt) { super.onScrollChanged(l, t, oldl, oldt); updateScrollIndicators(); }

  // ---------------------------------------------------------------- text filter (a Filterable adapter filtered by typed text)
  boolean mTextFilterEnabled, mFiltered;
  String mFilterText;
  public void setTextFilterEnabled(boolean e) { mTextFilterEnabled = e; }
  public boolean isTextFilterEnabled() { return mTextFilterEnabled; }
  public boolean isInFilterMode() { return mFiltered; }
  public boolean hasTextFilter() { return mFiltered; }
  public CharSequence getTextFilter() { return mTextFilterEnabled && mFilterText != null ? mFilterText : null; }
  /** filters a Filterable adapter by the text (the list must have text filtering enabled) */
  public void setFilterText(String filterText) {
    if (!mTextFilterEnabled || filterText == null || filterText.isEmpty()) return;
    mFilterText = filterText;
    mFiltered = true;
    if (mAdapter instanceof Filterable) { Filter f = ((Filterable) mAdapter).getFilter(); if (f != null) f.filter(filterText, this); }
  }
  public void clearTextFilter() {
    if (!mFiltered) return;
    mFiltered = false;
    mFilterText = null;
    if (mAdapter instanceof Filterable) { Filter f = ((Filterable) mAdapter).getFilter(); if (f != null) f.filter(null, this); }
  }
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
  /** the filter popup's text changed: filter the adapter by it */
  public void onTextChanged(CharSequence s, int start, int before, int count) {
    if (!(mAdapter instanceof Filterable)) return;
    Filter f = ((Filterable) mAdapter).getFilter();
    if (f == null) throw new IllegalStateException("You cannot call onTextChanged with a non filterable adapter");
    mFiltered = s != null && s.length() > 0;
    mFilterText = s == null ? null : s.toString();
    f.filter(s, this);
  }
  public void afterTextChanged(android.text.Editable s) {}
  public void onFilterComplete(int count) {
    if (mSelectedPosition < 0 && count > 0 && !isInTouchMode()) resurrectSelection();
  }
  public void onGlobalLayout() {}

  // ---------------------------------------------------------------- modal multiple choice
  MultiChoiceModeListener mMultiChoiceModeListener;
  ActionMode mChoiceActionMode;
  /** CHOICE_MODE_MULTIPLE_MODAL: a long press starts the action mode (startActionMode) and checks the item */
  public void setMultiChoiceModeListener(MultiChoiceModeListener listener) { mMultiChoiceModeListener = listener; }
  final ActionMode.Callback mModalCallback = new ActionMode.Callback() {
    public boolean onCreateActionMode(ActionMode mode, Menu menu) { return mMultiChoiceModeListener != null && mMultiChoiceModeListener.onCreateActionMode(mode, menu); }
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) { return mMultiChoiceModeListener != null && mMultiChoiceModeListener.onPrepareActionMode(mode, menu); }
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) { return mMultiChoiceModeListener != null && mMultiChoiceModeListener.onActionItemClicked(mode, item); }
    public void onDestroyActionMode(ActionMode mode) {
      if (mMultiChoiceModeListener != null) mMultiChoiceModeListener.onDestroyActionMode(mode);
      mChoiceActionMode = null;
      clearChoices();
      requestLayout2();
      setLongClickable(true);
    }
  };
  /** a check change in modal mode: the listener hears it; the mode ends when nothing is checked */
  void modalCheckChanged(int position, boolean checked) {
    if (mChoiceActionMode == null || mMultiChoiceModeListener == null) return;
    mMultiChoiceModeListener.onItemCheckedStateChanged(mChoiceActionMode, position, mAdapter != null ? mAdapter.getItemId(position) : INVALID_ROW_ID, checked);
    if (getCheckedItemCount() == 0) mChoiceActionMode.finish();
  }
  /** checked items' ids (adapters with stable ids) */
  public long[] getCheckedItemIds() {
    if (mChoiceMode == CHOICE_MODE_NONE || mCheckStates == null || mAdapter == null || !mAdapter.hasStableIds()) return new long[0];
    int n = 0;
    for (int i = 0; i < mCheckStates.size(); i++) if (mCheckStates.valueAt(i)) n++;
    long[] ids = new long[n];
    for (int i = 0, k = 0; i < mCheckStates.size(); i++) if (mCheckStates.valueAt(i) && mCheckStates.keyAt(i) < mAdapter.getCount()) ids[k++] = mAdapter.getItemId(mCheckStates.keyAt(i));
    return ids;
  }

  // ---------------------------------------------------------------- misc API
  @Override public View getSelectedView() {
    int s = mSelectedPosition >= 0 ? mSelectedPosition : -1;
    return s >= mFirstPosition && s < mFirstPosition + getChildCount() ? getChildAt(s - mFirstPosition) : null;
  }
  public int getListPaddingTop() { return mListPadding.top; }
  public int getListPaddingBottom() { return mListPadding.bottom; }
  public int getListPaddingLeft() { return mListPadding.left; }
  public int getListPaddingRight() { return mListPadding.right; }
  public void setCacheColorHint(int c) { mCacheColorHint = c; }
  public int getCacheColorHint() { return mCacheColorHint; }
  public void setScrollingCacheEnabled(boolean e) { mScrollingCacheEnabled = e; }
  public boolean isScrollingCacheEnabled() { return mScrollingCacheEnabled; }
  public void setFastScrollEnabled(boolean e) { mFastScrollEnabled = e; }
  public boolean isFastScrollEnabled() { return mFastScrollEnabled; }
  public void setFastScrollAlwaysVisible(boolean e) { mFastScrollAlwaysVisible = e; if (e) mFastScrollEnabled = true; }
  public void setSmoothScrollbarEnabled(boolean e) { mSmoothScrollbarEnabled = e; }
  public void setStackFromBottom(boolean s) { mStackFromBottom = s; requestLayout2(); }
  public boolean isStackFromBottom() { return mStackFromBottom; }
  public void setTranscriptMode(int m) { mTranscriptMode = m; }
  public int getTranscriptMode() { return mTranscriptMode; }
  public void setFriction(float f) {}
  public void setVelocityScale(float s) {}
  public void setChoiceMode(int m) {
    if (mChoiceActionMode != null) { mChoiceActionMode.finish(); mChoiceActionMode = null; }
    mChoiceMode = m;
    if (m != CHOICE_MODE_NONE && mCheckStates == null) mCheckStates = new android.util.SparseBooleanArray();
    // modal multiple choice starts with a long press
    if (m == CHOICE_MODE_MULTIPLE_MODAL) { clearChoices(); setLongClickable(true); }
  }
  public int getChoiceMode() { return mChoiceMode; }
  public void setItemChecked(int position, boolean value) {
    if (mChoiceMode == CHOICE_MODE_NONE) return;
    if (value && mChoiceMode == CHOICE_MODE_MULTIPLE_MODAL && mChoiceActionMode == null && mMultiChoiceModeListener != null) mChoiceActionMode = startActionMode(mModalCallback);
    if (mCheckStates == null) mCheckStates = new android.util.SparseBooleanArray();
    boolean old = mCheckStates.get(position, false);
    if (mChoiceMode == CHOICE_MODE_SINGLE && value) mCheckStates.clear();
    mCheckStates.put(position, value);
    if (old != value) modalCheckChanged(position, value);
    requestLayout2();
  }
  public boolean isItemChecked(int position) { return mCheckStates != null && mCheckStates.get(position, false); }
  public int getCheckedItemPosition() {
    if (mChoiceMode == CHOICE_MODE_SINGLE && mCheckStates != null && mCheckStates.size() == 1) return mCheckStates.keyAt(0);
    return INVALID_POSITION;
  }
  public android.util.SparseBooleanArray getCheckedItemPositions() { return mChoiceMode != CHOICE_MODE_NONE ? mCheckStates : null; }
  public int getCheckedItemCount() {
    if (mCheckStates == null) return 0;
    int n = 0;
    for (int i = 0; i < mCheckStates.size(); i++) if (mCheckStates.valueAt(i)) n++;
    return n;
  }
  public void clearChoices() { if (mCheckStates != null) mCheckStates.clear(); }
  public void reclaimViews(java.util.List<View> views) { for (int i = 0; i < getChildCount(); i++) views.add(getChildAt(i)); }
  @Override protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    mRecycler.clear();
    if (mFlingRunnable != null) mFlingRunnable.end();
    if (mTouchModeListener != null) { getViewTreeObserver().removeOnTouchModeChangeListener(mTouchModeListener); mTouchModeListener = null; }
  }
  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    if (mAdapter != null) { mDataChanged = true; mOldItemCount = mItemCount; mItemCount = mAdapter.getCount(); }
    if (mTouchModeListener == null) { mTouchModeListener = this; getViewTreeObserver().addOnTouchModeChangeListener(mTouchModeListener); }
  }
  @Override protected int computeVerticalScrollExtent() { return getHeight(); }
  @Override protected int computeVerticalScrollOffset() { return mFirstPosition; }
  @Override protected int computeVerticalScrollRange() { return mItemCount; }
  @Override public void onWindowFocusChanged(boolean f) { super.onWindowFocusChanged(f); if (!f) setPressedChild(null); }

  @Override protected ViewGroup.LayoutParams generateDefaultLayoutParams() { return new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0); }
  @Override protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) { return new LayoutParams(p); }
  @Override public LayoutParams generateLayoutParams(AttributeSet a) { return new LayoutParams(getContext(), a); }
  @Override protected boolean checkLayoutParams(ViewGroup.LayoutParams p) { return p instanceof LayoutParams; }

  public static class LayoutParams extends ViewGroup.LayoutParams {
    int viewType;
    boolean recycledHeaderFooter, forceAdd;
    public LayoutParams(Context c, AttributeSet a) { super(c, a); }
    public LayoutParams(int w, int h) { super(w, h); }
    public LayoutParams(int w, int h, int viewType) { super(w, h); this.viewType = viewType; }
    public LayoutParams(ViewGroup.LayoutParams s) { super(s); }
  }
}
