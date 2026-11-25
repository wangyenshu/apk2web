package android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import a2w.Attrs;
import a2w.Values;

public class GridView extends AbsListView {
  public static final int NO_STRETCH = 0, STRETCH_SPACING = 1, STRETCH_COLUMN_WIDTH = 2, STRETCH_SPACING_UNIFORM = 3, AUTO_FIT = -1;
  int mNumColumns = AUTO_FIT, mHorizontalSpacing, mRequestedHorizontalSpacing, mVerticalSpacing, mStretchMode = STRETCH_COLUMN_WIDTH;
  int mColumnWidth, mRequestedColumnWidth, mRequestedNumColumns = AUTO_FIT;
  int mGravity = Gravity.LEFT;
  private final boolean[] mRecycled = new boolean[1];

  public GridView(Context c) { this(c, null); }
  public GridView(Context c, AttributeSet a) { this(c, a, 0); }
  public GridView(Context c, AttributeSet a, int d) {
    super(c, a, d);
    if (a instanceof Attrs) {
      Attrs at = (Attrs) a;
      String v;
      if ((v = at.a("horizontalSpacing")) != null) setHorizontalSpacing((int) dim(v));
      if ((v = at.a("verticalSpacing")) != null) setVerticalSpacing((int) dim(v));
      if ((v = at.a("stretchMode")) != null) setStretchMode(v.equals("none") ? NO_STRETCH : v.equals("spacingWidth") ? STRETCH_SPACING : v.equals("spacingWidthUniform") ? STRETCH_SPACING_UNIFORM : STRETCH_COLUMN_WIDTH);
      if ((v = at.a("columnWidth")) != null) setColumnWidth((int) dim(v));
      if ((v = at.a("numColumns")) != null) setNumColumns(v.equals("auto_fit") ? AUTO_FIT : Values.parseInt(v, 1));
      if ((v = at.a("gravity")) != null) setGravity(Values.gravity(v));
    }
  }
  public void setGravity(int g) { if (mGravity != g) { mGravity = g; requestLayout2(); } }
  public void setHorizontalSpacing(int s) { if (s != mRequestedHorizontalSpacing) { mRequestedHorizontalSpacing = s; requestLayout2(); } }
  public int getHorizontalSpacing() { return mHorizontalSpacing; }
  public void setVerticalSpacing(int s) { if (s != mVerticalSpacing) { mVerticalSpacing = s; requestLayout2(); } }
  public int getVerticalSpacing() { return mVerticalSpacing; }
  public void setStretchMode(int m) { if (m != mStretchMode) { mStretchMode = m; requestLayout2(); } }
  public int getStretchMode() { return mStretchMode; }
  public void setColumnWidth(int w) { if (w != mRequestedColumnWidth) { mRequestedColumnWidth = w; requestLayout2(); } }
  public void setNumColumns(int n) { if (n != mRequestedNumColumns) { mRequestedNumColumns = n; requestLayout2(); } }
  public int getNumColumns() { return mNumColumns; }
  /** the column and row of each child, for a GridLayoutAnimationController */
  @Override protected void attachLayoutAnimationParameters(android.view.View child, android.view.ViewGroup.LayoutParams params, int index, int count) {
    android.view.animation.GridLayoutAnimationController.AnimationParameters p = params.layoutAnimationParameters instanceof android.view.animation.GridLayoutAnimationController.AnimationParameters
        ? (android.view.animation.GridLayoutAnimationController.AnimationParameters) params.layoutAnimationParameters : new android.view.animation.GridLayoutAnimationController.AnimationParameters();
    params.layoutAnimationParameters = p;
    int cols = Math.max(1, mNumColumns);
    p.count = count; p.index = index; p.columnsCount = cols; p.rowsCount = count / cols;
    if (!mStackFromBottom) { p.column = index % cols; p.row = index / cols; }
    else { int inv = count - 1 - index; p.column = cols - 1 - (inv % cols); p.row = p.rowsCount - 1 - inv / cols; }
  }
  /** scrolls position's row to the top; out of touch mode it also becomes the selection */
  @Override public void setSelection(int position) {
    if (!isInTouchMode()) { int p = lookForSelectablePosition(position, true); if (p >= 0) setSelectedPositionInt(p); }
    else mResurrectToPosition = position;
    mPendingSelection = Math.max(0, position); mPendingSelectionTop = 0; requestLayout2();
  }
  @Override int itemsPerRow() { return Math.max(1, mNumColumns); }
  @Override int rowGap() { return mVerticalSpacing; }

  private void determineColumns(int availableSpace) {
    final int requestedHorizontalSpacing = mRequestedHorizontalSpacing, stretchMode = mStretchMode, requestedColumnWidth = mRequestedColumnWidth;
    if (mRequestedNumColumns == AUTO_FIT) {
      if (requestedColumnWidth > 0) mNumColumns = (availableSpace + requestedHorizontalSpacing) / (requestedColumnWidth + requestedHorizontalSpacing);
      else mNumColumns = 2;
    } else mNumColumns = mRequestedNumColumns;
    if (mNumColumns <= 0) mNumColumns = 1;
    switch (stretchMode) {
      case NO_STRETCH: mColumnWidth = requestedColumnWidth; mHorizontalSpacing = requestedHorizontalSpacing; break;
      default: {
        int spaceLeftOver = availableSpace - (mNumColumns * requestedColumnWidth) - ((mNumColumns - 1) * requestedHorizontalSpacing);
        switch (stretchMode) {
          case STRETCH_COLUMN_WIDTH: mColumnWidth = requestedColumnWidth + spaceLeftOver / mNumColumns; mHorizontalSpacing = requestedHorizontalSpacing; break;
          case STRETCH_SPACING:
            mColumnWidth = requestedColumnWidth;
            mHorizontalSpacing = mNumColumns > 1 ? requestedHorizontalSpacing + spaceLeftOver / (mNumColumns - 1) : requestedHorizontalSpacing + spaceLeftOver;
            break;
          case STRETCH_SPACING_UNIFORM:
            mColumnWidth = requestedColumnWidth;
            mHorizontalSpacing = mNumColumns > 1 ? requestedHorizontalSpacing + spaceLeftOver / (mNumColumns + 1) : requestedHorizontalSpacing + spaceLeftOver;
            break;
        }
      }
    }
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    int widthMode = MeasureSpec.getMode(widthMeasureSpec), heightMode = MeasureSpec.getMode(heightMeasureSpec);
    int widthSize = MeasureSpec.getSize(widthMeasureSpec), heightSize = MeasureSpec.getSize(heightMeasureSpec);
    if (widthMode == MeasureSpec.UNSPECIFIED) {
      if (mColumnWidth > 0) widthSize = mColumnWidth + mListPadding.left + mListPadding.right;
      else widthSize = mListPadding.left + mListPadding.right;
    }
    int childWidth = widthSize - mListPadding.left - mListPadding.right;
    determineColumns(childWidth);
    int childHeight = 0;
    mItemCount = mAdapter == null ? 0 : mAdapter.getCount();
    final int count = mItemCount;
    if (count > 0) {
      final View child = obtainView(0, mIsScrap);
      LayoutParams p = (LayoutParams) child.getLayoutParams();
      if (p == null) { p = (LayoutParams) generateDefaultLayoutParams(); child.setLayoutParams2(p); }
      p.viewType = mAdapter.getItemViewType(0);
      int childHeightSpec = getChildMeasureSpec(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), 0, p.height);
      int childWidthSpec = getChildMeasureSpec(MeasureSpec.makeMeasureSpec(mColumnWidth, MeasureSpec.EXACTLY), 0, p.width);
      child.measure(childWidthSpec, childHeightSpec);
      childHeight = child.getMeasuredHeight();
      mRecycler.addScrap(child);
    }
    if (heightMode == MeasureSpec.UNSPECIFIED) heightSize = mListPadding.top + mListPadding.bottom + childHeight;
    if (heightMode == MeasureSpec.AT_MOST) {
      int ourSize = mListPadding.top + mListPadding.bottom;
      final int numColumns = mNumColumns;
      for (int i = 0; i < count; i += numColumns) {
        ourSize += childHeight;
        if (i + numColumns < count) ourSize += mVerticalSpacing;
        if (ourSize >= heightSize) { ourSize = heightSize; break; }
      }
      heightSize = ourSize;
    }
    setMeasuredDimension(widthSize, heightSize);
    mWidthMeasureSpec = widthMeasureSpec;
  }

  /** a row: the items of [position, position+numColumns); its extent is that of the last item, as in Android */
  @Override int layoutRow(int startPos, int y, boolean down, boolean unused) {
    final int columnWidth = mColumnWidth, horizontalSpacing = mHorizontalSpacing;
    int last = Math.min(startPos + mNumColumns, mItemCount);
    int nextLeft = mListPadding.left + (mStretchMode == STRETCH_SPACING_UNIFORM ? horizontalSpacing : 0);
    int result = y;
    int insertAt = 0;
    for (int pos = startPos; pos < last; pos++) {
      View child = makeChild(pos, mRecycled);
      boolean recycled = mRecycled[0];
      LayoutParams p = (LayoutParams) child.getLayoutParams();
      if (p == null) { p = (LayoutParams) generateDefaultLayoutParams(); child.setLayoutParams2(p); }
      p.viewType = mAdapter.getItemViewType(pos);
      addChild(child, down ? -1 : insertAt++, recycled);
      if (mCheckStates != null) { boolean ch = mCheckStates.get(pos, false); if (child instanceof Checkable) ((Checkable) child).setChecked(ch); else if (getContext().getApplicationInfo().targetSdkVersion >= 11) child.setActivated(ch); }
      boolean needToMeasure = !recycled || child.isLayoutRequested();
      if (needToMeasure) {
        int childHeightSpec = getChildMeasureSpec(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), 0, p.height);
        int childWidthSpec = getChildMeasureSpec(MeasureSpec.makeMeasureSpec(mColumnWidth, MeasureSpec.EXACTLY), 0, p.width);
        child.measure(childWidthSpec, childHeightSpec);
      }
      final int w = child.getMeasuredWidth(), h = child.getMeasuredHeight();
      int childLeft;
      final int childTop = down ? y : y - h;
      switch (Gravity.getAbsoluteGravity(mGravity, 0) & Gravity.HORIZONTAL_GRAVITY_MASK) {
        case Gravity.CENTER_HORIZONTAL: childLeft = nextLeft + ((columnWidth - w) / 2); break;
        case Gravity.RIGHT: childLeft = nextLeft + columnWidth - w; break;
        default: childLeft = nextLeft;
      }
      if (needToMeasure) child.layout(childLeft, childTop, childLeft + w, childTop + h);
      else { child.offsetLeftAndRight(childLeft - child.getLeft()); child.offsetTopAndBottom(childTop - child.getTop()); }
      result = down ? childTop + h : childTop;
      nextLeft += columnWidth;
      if (pos < last - 1) nextLeft += horizontalSpacing;
    }
    return result;
  }
}
