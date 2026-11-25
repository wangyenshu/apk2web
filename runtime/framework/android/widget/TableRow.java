package android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import a2w.Attrs;
import a2w.Values;

public class TableRow extends LinearLayout {
  private int mNumColumns;
  private int[] mColumnWidths, mConstrainedColumnWidths;

  public TableRow(Context c) { super(c); setOrientation(HORIZONTAL); }
  public TableRow(Context c, AttributeSet a) { super(c, a); setOrientation(HORIZONTAL); }
  void setColumnsWidthConstraints(int[] w) { if (w == null || w.length < mNumColumns) throw new IllegalArgumentException("columnWidths should be >= getVirtualChildCount()"); mConstrainedColumnWidths = w; }
  @Override protected void onMeasure(int ws, int hs) { measureHorizontal(ws, hs); }
  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) { layoutHorizontal(); }
  @Override int getVirtualChildCount() { mNumColumns = getChildCount(); return mNumColumns; }
  @Override int getLocationOffset(View child) { return ((TableRow.LayoutParams) child.getLayoutParams()).mOffset[LayoutParams.LOCATION]; }
  @Override int getNextLocationOffset(View child) { return ((TableRow.LayoutParams) child.getLayoutParams()).mOffset[LayoutParams.LOCATION_NEXT]; }
  @Override void measureChildBeforeLayout(View child, int childIndex, int widthMeasureSpec, int totalWidth, int heightMeasureSpec, int totalHeight) {
    if (mConstrainedColumnWidths != null) {
      final LayoutParams lp = (LayoutParams) child.getLayoutParams();
      int measureMode = MeasureSpec.EXACTLY;
      int columnWidth = 0;
      final int span = lp.span;
      final int[] constrainedColumnWidths = mConstrainedColumnWidths;
      for (int i = 0; i < span; i++) if (childIndex + i < constrainedColumnWidths.length) columnWidth += constrainedColumnWidths[childIndex + i];
      final int gravity = lp.gravity;
      final boolean isHorizontalGravity = Gravity.isHorizontal(gravity);
      if (isHorizontalGravity) measureMode = MeasureSpec.AT_MOST;
      int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(Math.max(0, columnWidth - lp.leftMargin - lp.rightMargin), measureMode);
      int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, mPaddingTop + mPaddingBottom + lp.topMargin + lp.bottomMargin + totalHeight, lp.height);
      child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
      if (isHorizontalGravity) {
        final int childWidth = child.getMeasuredWidth();
        lp.mOffset[LayoutParams.LOCATION_NEXT] = columnWidth - childWidth;
        switch (Gravity.getAbsoluteGravity(gravity, 0) & Gravity.HORIZONTAL_GRAVITY_MASK) {
          case Gravity.LEFT: break;
          case Gravity.RIGHT: lp.mOffset[LayoutParams.LOCATION] = lp.mOffset[LayoutParams.LOCATION_NEXT]; break;
          case Gravity.CENTER_HORIZONTAL: lp.mOffset[LayoutParams.LOCATION] = lp.mOffset[LayoutParams.LOCATION_NEXT] / 2; break;
        }
      } else {
        lp.mOffset[LayoutParams.LOCATION] = lp.mOffset[LayoutParams.LOCATION_NEXT] = 0;
      }
    } else super.measureChildBeforeLayout(child, childIndex, widthMeasureSpec, totalWidth, heightMeasureSpec, totalHeight);
  }
  int[] getColumnsWidths(int widthMeasureSpec) {
    final int numColumns = getVirtualChildCount();
    if (mColumnWidths == null || numColumns != mColumnWidths.length) mColumnWidths = new int[numColumns];
    final int[] columnWidths = mColumnWidths;
    for (int i = 0; i < numColumns; i++) {
      final View child = getVirtualChildAt(i);
      if (child != null && child.getVisibility() != GONE) {
        final LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
        if (layoutParams.span == 1) {
          int spec;
          switch (layoutParams.width) {
            case LayoutParams.WRAP_CONTENT: spec = getChildMeasureSpec(widthMeasureSpec, 0, LayoutParams.WRAP_CONTENT); break;
            case LayoutParams.MATCH_PARENT: spec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED); break;
            default: spec = MeasureSpec.makeMeasureSpec(layoutParams.width, MeasureSpec.EXACTLY);
          }
          child.measure(spec, spec);
          final int width = child.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
          columnWidths[i] = width;
        } else columnWidths[i] = 0;
      } else columnWidths[i] = 0;
    }
    return columnWidths;
  }
  @Override public LayoutParams generateLayoutParams(AttributeSet a) { return new TableRow.LayoutParams(getContext(), a); }
  @Override protected LinearLayout.LayoutParams generateDefaultLayoutParams() { return new LayoutParams(); }
  @Override protected boolean checkLayoutParams(ViewGroup.LayoutParams p) { return p instanceof TableRow.LayoutParams; }
  @Override protected LinearLayout.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) { return new LayoutParams(p); }
  public static class LayoutParams extends LinearLayout.LayoutParams {
    public int column = -1, span = 1;
    static final int LOCATION = 0, LOCATION_NEXT = 1;
    final int[] mOffset = new int[2];
    public LayoutParams(Context c, AttributeSet attrs) {
      super(c, attrs);
      if (attrs instanceof Attrs) {
        Attrs a = (Attrs) attrs;
        if (a.a("layout_width") == null) width = MATCH_PARENT;
        if (a.a("layout_height") == null) height = WRAP_CONTENT;
        column = Values.parseInt(a.a("layout_column"), -1);
        span = Math.max(1, Values.parseInt(a.a("layout_span"), 1));
      }
    }
    public LayoutParams(int w, int h) { super(w, h); }
    public LayoutParams(int w, int h, float weight) { super(w, h, weight); }
    public LayoutParams() { super(MATCH_PARENT, WRAP_CONTENT); }
    public LayoutParams(int column) { this(); this.column = column; }
    public LayoutParams(ViewGroup.LayoutParams p) { super(p); }
    public LayoutParams(MarginLayoutParams s) { super(s); }
  }
}
