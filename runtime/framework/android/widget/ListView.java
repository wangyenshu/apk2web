package android.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import a2w.Attrs;
import a2w.Values;
import java.util.ArrayList;

public class ListView extends AbsListView {
  public static final int CHOICE_MODE_NONE = 0, CHOICE_MODE_SINGLE = 1, CHOICE_MODE_MULTIPLE = 2;
  Drawable mDivider;
  int mDividerHeight;
  boolean mHeaderDividersEnabled = true, mFooterDividersEnabled = true, mItemsCanFocus;
  final ArrayList<FixedViewInfo> mHeaderViewInfos = new ArrayList<>(), mFooterViewInfos = new ArrayList<>();
  /** A header or footer: the view, its adapter data and whether it can be selected. */
  public class FixedViewInfo {
    public View view;
    public Object data;
    public boolean isSelectable;
    public FixedViewInfo() {}
  }
  private final boolean[] mRecycled = new boolean[1];
  private final Rect mTempRect = new Rect();

  public ListView(Context c) { this(c, null); }
  public ListView(Context c, AttributeSet a) { this(c, a, 0); }
  public ListView(Context c, AttributeSet a, int d) {
    super(c, a, d);
    Drawable div = null;
    String v = a instanceof Attrs ? ((Attrs) a).a("divider") : null;
    if (v == null) div = getResources().getDrawable(Values.frameworkId("drawable/divider_horizontal_dark"));
    else if (v.startsWith("#")) div = new android.graphics.drawable.ColorDrawable(Values.parseColor(v));
    else if (Values.isRef(v)) { int id = Values.refId(v); if (id != 0) div = getResources().getDrawable(id); }
    if (div != null) setDivider(div);
    if (a instanceof Attrs) {
      Attrs at = (Attrs) a;
      if ((v = at.a("dividerHeight")) != null) { int h = (int) dim(v); if (h != 0) setDividerHeight(h); }
      if ((v = at.a("headerDividersEnabled")) != null) mHeaderDividersEnabled = v.equals("true");
      if ((v = at.a("footerDividersEnabled")) != null) mFooterDividersEnabled = v.equals("true");
    }
  }
  public Drawable getDivider() { return mDivider; }
  public void setDivider(Drawable divider) {
    mDividerHeight = divider != null ? divider.getIntrinsicHeight() : 0;
    mDivider = divider;
    requestLayout2();
  }
  public int getDividerHeight() { return mDividerHeight; }
  public void setDividerHeight(int h) { mDividerHeight = h; requestLayout2(); }
  public void setHeaderDividersEnabled(boolean e) { mHeaderDividersEnabled = e; }
  public void setFooterDividersEnabled(boolean e) { mFooterDividersEnabled = e; }
  public void setItemsCanFocus(boolean f) { mItemsCanFocus = f; }
  public boolean getItemsCanFocus() { return mItemsCanFocus; }
  // ---------------------------------------------------------------- headers and footers: the adapter is wrapped in a HeaderViewListAdapter
  public void addHeaderView(View v) { addHeaderView(v, null, true); }
  /** a fixed view before the items (position 0 on); selectable: whether it can be clicked and selected like an item */
  public void addHeaderView(View v, Object data, boolean isSelectable) {
    FixedViewInfo info = new FixedViewInfo();
    info.view = v; info.data = data; info.isSelectable = isSelectable;
    mHeaderViewInfos.add(info);
    fixedViewsChanged();
  }
  public int getHeaderViewsCount() { return mHeaderViewInfos.size(); }
  public boolean removeHeaderView(View v) { return removeFixedView(v, mHeaderViewInfos, true); }
  public void addFooterView(View v) { addFooterView(v, null, true); }
  public void addFooterView(View v, Object data, boolean isSelectable) {
    FixedViewInfo info = new FixedViewInfo();
    info.view = v; info.data = data; info.isSelectable = isSelectable;
    mFooterViewInfos.add(info);
    fixedViewsChanged();
  }
  public int getFooterViewsCount() { return mFooterViewInfos.size(); }
  public boolean removeFooterView(View v) { return removeFixedView(v, mFooterViewInfos, false); }
  private void fixedViewsChanged() {
    if (mAdapter == null) return;
    if (!(mAdapter instanceof HeaderViewListAdapter)) {
      // added after setAdapter (allowed since API 19): wrap the adapter now
      ListAdapter inner = mAdapter;
      if (mDataSetObserver != null) inner.unregisterDataSetObserver(mDataSetObserver);
      mAdapter = new HeaderViewListAdapter(mHeaderViewInfos, mFooterViewInfos, inner);
      if (mDataSetObserver != null) mAdapter.registerDataSetObserver(mDataSetObserver);
      mRecycler.setViewTypeCount(mAdapter.getViewTypeCount());
    }
    if (mDataSetObserver != null) mDataSetObserver.onChanged(); else { mItemCount = mAdapter.getCount(); mDataChanged = true; requestLayout2(); }
  }
  private boolean removeFixedView(View v, ArrayList<FixedViewInfo> where, boolean header) {
    boolean result = false;
    if (mAdapter instanceof HeaderViewListAdapter) {
      HeaderViewListAdapter h = (HeaderViewListAdapter) mAdapter;
      if (header ? h.removeHeader(v) : h.removeFooter(v)) { result = true; if (mDataSetObserver != null) mDataSetObserver.onChanged(); }
    }
    for (int i = 0; i < where.size(); i++) if (where.get(i).view == v) { where.remove(i); break; }
    if (v.getParent() == null && v.isAttachedToWindow()) removeDetachedView(v, false);
    return result;
  }
  @Override public void setAdapter(ListAdapter adapter) {
    if (!mHeaderViewInfos.isEmpty() || !mFooterViewInfos.isEmpty()) adapter = new HeaderViewListAdapter(mHeaderViewInfos, mFooterViewInfos, adapter);
    super.setAdapter(adapter);
  }
  @Override public void setSelection(int position) { setSelectionFromTop(position, 0); }
  /** scrolls position to y from the top; out of touch mode it also becomes the selection */
  public void setSelectionFromTop(int position, int y) {
    if (mAdapter == null) return;
    if (!isInTouchMode()) { int p = lookForSelectablePosition(position, true); if (p >= 0) setSelectedPositionInt(p); }
    else mResurrectToPosition = position;
    mPendingSelection = Math.max(0, position);
    mPendingSelectionTop = y;
    requestLayout2();
  }
  public void setSelectionAfterHeaderView() { setSelection(0); }

  @Override int itemsPerRow() { return 1; }
  @Override int rowGap() { return mDividerHeight; }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    int widthMode = MeasureSpec.getMode(widthMeasureSpec), heightMode = MeasureSpec.getMode(heightMeasureSpec);
    int widthSize = MeasureSpec.getSize(widthMeasureSpec), heightSize = MeasureSpec.getSize(heightMeasureSpec);
    int childWidth = 0, childHeight = 0;
    mItemCount = mAdapter == null ? 0 : mAdapter.getCount();
    if (mItemCount > 0 && (widthMode == MeasureSpec.UNSPECIFIED || heightMode == MeasureSpec.UNSPECIFIED)) {
      final View child = obtainView(0, mIsScrap);
      measureScrapChild(child, 0, widthMeasureSpec);
      childWidth = child.getMeasuredWidth();
      childHeight = child.getMeasuredHeight();
      mRecycler.addScrap(child);
    }
    if (widthMode == MeasureSpec.UNSPECIFIED) widthSize = mListPadding.left + mListPadding.right + childWidth;
    if (heightMode == MeasureSpec.UNSPECIFIED) heightSize = mListPadding.top + mListPadding.bottom + childHeight;
    if (heightMode == MeasureSpec.AT_MOST) heightSize = measureHeightOfChildren(widthMeasureSpec, 0, -1, heightSize, -1);
    setMeasuredDimension(widthSize, heightSize);
    mWidthMeasureSpec = widthMeasureSpec;
  }
  private void measureScrapChild(View child, int position, int widthMeasureSpec) {
    LayoutParams p = (LayoutParams) child.getLayoutParams();
    if (p == null) { p = (LayoutParams) generateDefaultLayoutParams(); child.setLayoutParams2(p); }
    p.viewType = mAdapter.getItemViewType(position);
    int childWidthSpec = ViewGroup.getChildMeasureSpec(widthMeasureSpec, mListPadding.left + mListPadding.right, p.width);
    int lpHeight = p.height;
    int childHeightSpec = lpHeight > 0 ? MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.EXACTLY) : MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
    child.measure(childWidthSpec, childHeightSpec);
  }
  final int measureHeightOfChildren(int widthMeasureSpec, int startPosition, int endPosition, final int maxHeight, int disallowPartialChildPosition) {
    final ListAdapter adapter = mAdapter;
    if (adapter == null) return mListPadding.top + mListPadding.bottom;
    int returnedHeight = mListPadding.top + mListPadding.bottom;
    final int dividerHeight = ((mDividerHeight > 0) && mDivider != null) ? mDividerHeight : 0;
    int prevHeightWithoutPartialChild = 0;
    endPosition = (endPosition == -1) ? adapter.getCount() - 1 : endPosition;
    for (int i = startPosition; i <= endPosition; ++i) {
      View child = obtainView(i, mIsScrap);
      measureScrapChild(child, i, widthMeasureSpec);
      if (i > 0) returnedHeight += dividerHeight;
      mRecycler.addScrap(child);
      returnedHeight += child.getMeasuredHeight();
      if (returnedHeight >= maxHeight) {
        return (disallowPartialChildPosition >= 0) && (i > disallowPartialChildPosition) && (prevHeightWithoutPartialChild > 0) && (returnedHeight != maxHeight) ? prevHeightWithoutPartialChild : maxHeight;
      }
      if ((disallowPartialChildPosition >= 0) && (i >= disallowPartialChildPosition)) prevHeightWithoutPartialChild = returnedHeight;
    }
    return returnedHeight;
  }

  @Override int layoutRow(int position, int y, boolean down, boolean unused) {
    View child = makeChild(position, mRecycled);
    boolean recycled = mRecycled[0];
    LayoutParams p = (LayoutParams) child.getLayoutParams();
    if (p == null) { p = (LayoutParams) generateDefaultLayoutParams(); child.setLayoutParams2(p); }
    p.viewType = mAdapter.getItemViewType(position);
    addChild(child, down ? -1 : 0, recycled);
    // checked items: Checkable rows are checked, others activated (apps targeting API 11+)
    if (mCheckStates != null) { boolean ch = mCheckStates.get(position, false); if (child instanceof Checkable) ((Checkable) child).setChecked(ch); else if (getContext().getApplicationInfo().targetSdkVersion >= 11) child.setActivated(ch); }
    boolean needToMeasure = !recycled || child.isLayoutRequested();
    if (needToMeasure) {
      int childWidthSpec = ViewGroup.getChildMeasureSpec(mWidthMeasureSpec, mListPadding.left + mListPadding.right, p.width);
      int lpHeight = p.height;
      int childHeightSpec = lpHeight > 0 ? MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.EXACTLY) : MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
      child.measure(childWidthSpec, childHeightSpec);
    }
    final int w = child.getMeasuredWidth(), h = child.getMeasuredHeight();
    final int childLeft = mListPadding.left;
    final int childTop = down ? y : y - h;
    if (needToMeasure) child.layout(childLeft, childTop, childLeft + w, childTop + h);
    else { child.offsetLeftAndRight(childLeft - child.getLeft()); child.offsetTopAndBottom(childTop - child.getTop()); }
    return down ? childTop + h : childTop;
  }

  @Override protected void dispatchDraw(Canvas canvas) {
    super.dispatchDraw(canvas);
    final int dividerHeight = mDividerHeight;
    if (dividerHeight > 0 && mDivider != null) {
      final int count = getChildCount();
      final Rect bounds = mTempRect;
      bounds.left = mPaddingLeft;
      bounds.right = mRight - mLeft - mPaddingRight;
      int bottom;
      final int listBottom = mBottom - mTop - mListPadding.bottom + mScrollY;
      final int headerCount = mHeaderViewInfos.size(), footerStart = mItemCount - mFooterViewInfos.size();
      for (int i = 0; i < count; i++) {
        int pos = mFirstPosition + i;
        // between enabled items; not after headers or before footers when their dividers are off
        if (pos < mItemCount - 1 && (mHeaderDividersEnabled || pos >= headerCount) && (mFooterDividersEnabled || pos + 1 < footerStart)
            && (mAdapter.areAllItemsEnabled() || (mAdapter.isEnabled(pos) && mAdapter.isEnabled(pos + 1)))) {
          View child = getChildAt(i);
          bottom = child.getBottom();
          if (bottom < listBottom) {
            bounds.top = bottom;
            bounds.bottom = bottom + dividerHeight;
            mDivider.setBounds(bounds);
            mDivider.draw(canvas);
          }
        }
      }
    }
  }
}
