package android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import a2w.Attrs;

public class TableLayout extends LinearLayout {
  private int[] mMaxWidths;
  private SparseBooleanArray mStretchableColumns = new SparseBooleanArray(), mShrinkableColumns = new SparseBooleanArray(), mCollapsedColumns = new SparseBooleanArray();
  private boolean mShrinkAllColumns, mStretchAllColumns;

  public TableLayout(Context c) { super(c); setOrientation(VERTICAL); }
  public TableLayout(Context c, AttributeSet a) {
    super(c, a);
    setOrientation(VERTICAL);
    if (a instanceof Attrs) {
      Attrs at = (Attrs) a;
      String v;
      if ((v = at.a("stretchColumns")) != null) { if (v.charAt(0) == '*') mStretchAllColumns = true; else mStretchableColumns = parse(v); }
      if ((v = at.a("shrinkColumns")) != null) { if (v.charAt(0) == '*') mShrinkAllColumns = true; else mShrinkableColumns = parse(v); }
      if ((v = at.a("collapseColumns")) != null) mCollapsedColumns = parse(v);
    }
  }
  private static SparseBooleanArray parse(String seq) {
    SparseBooleanArray c = new SparseBooleanArray();
    for (String n : seq.split(" *, *")) { try { int i = Integer.parseInt(n.trim()); if (i >= 0) c.put(i, true); } catch (NumberFormatException e) {} }
    return c;
  }
  public void setShrinkAllColumns(boolean s) { mShrinkAllColumns = s; }
  public void setStretchAllColumns(boolean s) { mStretchAllColumns = s; }
  public void setColumnStretchable(int i, boolean s) { mStretchableColumns.put(i, s); requestLayout(); }
  public void setColumnShrinkable(int i, boolean s) { mShrinkableColumns.put(i, s); requestLayout(); }
  public void setColumnCollapsed(int i, boolean c) { mCollapsedColumns.put(i, c); requestLayout(); }
  @Override protected void onMeasure(int ws, int hs) { measureVertical(ws, hs); }
  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) { layoutVertical(); }
  @Override void measureChildBeforeLayout(View child, int childIndex, int ws, int totalWidth, int hs, int totalHeight) {
    if (child instanceof TableRow) ((TableRow) child).setColumnsWidthConstraints(mMaxWidths);
    super.measureChildBeforeLayout(child, childIndex, ws, totalWidth, hs, totalHeight);
  }
  @Override void measureVertical(int ws, int hs) {
    findLargestCells(ws);
    shrinkAndStretchColumns(ws);
    super.measureVertical(ws, hs);
  }
  private void findLargestCells(int widthMeasureSpec) {
    boolean firstRow = true;
    final int count = getChildCount();
    for (int i = 0; i < count; i++) {
      final View child = getChildAt(i);
      if (child.getVisibility() == GONE) continue;
      if (child instanceof TableRow) {
        final TableRow row = (TableRow) child;
        row.getLayoutParams().height = LayoutParams.WRAP_CONTENT;
        final int[] widths = row.getColumnsWidths(widthMeasureSpec);
        final int newLength = widths.length;
        if (firstRow) {
          if (mMaxWidths == null || mMaxWidths.length != newLength) mMaxWidths = new int[newLength];
          System.arraycopy(widths, 0, mMaxWidths, 0, newLength);
          firstRow = false;
        } else {
          int length = mMaxWidths.length;
          final int difference = newLength - length;
          if (difference > 0) { final int[] oldMaxWidths = mMaxWidths; mMaxWidths = new int[newLength]; System.arraycopy(oldMaxWidths, 0, mMaxWidths, 0, oldMaxWidths.length); length = mMaxWidths.length; }
          final int[] maxWidths = mMaxWidths;
          length = Math.min(length, newLength);
          for (int j = 0; j < length; j++) maxWidths[j] = Math.max(maxWidths[j], widths[j]);
        }
      }
    }
    if (mMaxWidths == null) mMaxWidths = new int[0];
  }
  private void shrinkAndStretchColumns(int widthMeasureSpec) {
    if (mMaxWidths == null) return;
    int totalWidth = 0;
    for (int width : mMaxWidths) totalWidth += width;
    int size = MeasureSpec.getSize(widthMeasureSpec) - mPaddingLeft - mPaddingRight;
    if ((totalWidth > size) && (mShrinkAllColumns || mShrinkableColumns.size() > 0)) mutateColumnsWidth(mShrinkableColumns, mShrinkAllColumns, size, totalWidth);
    else if ((totalWidth < size) && (mStretchAllColumns || mStretchableColumns.size() > 0)) mutateColumnsWidth(mStretchableColumns, mStretchAllColumns, size, totalWidth);
  }
  private void mutateColumnsWidth(SparseBooleanArray columns, boolean allColumns, int size, int totalWidth) {
    int skipped = 0;
    final int[] maxWidths = mMaxWidths;
    final int length = maxWidths.length;
    final int count = allColumns ? length : columns.size();
    if (count == 0) return;
    final int totalExtraSpace = size - totalWidth;
    int extraSpace = totalExtraSpace / count;
    for (int i = 0; i < getChildCount(); i++) { View c = getChildAt(i); if (c instanceof TableRow) c.forceLayout(); }
    if (!allColumns) {
      for (int i = 0; i < count; i++) {
        int column = columns.keyAt(i);
        if (columns.valueAt(i)) { if (column < length) maxWidths[column] += extraSpace; else skipped++; }
      }
    } else { for (int i = 0; i < count; i++) maxWidths[i] += extraSpace; return; }
    if (skipped > 0 && skipped < count) {
      extraSpace = skipped * extraSpace / (count - skipped);
      for (int i = 0; i < count; i++) {
        int column = columns.keyAt(i);
        if (columns.valueAt(i) && column < length) { if (extraSpace > maxWidths[column]) maxWidths[column] = 0; else maxWidths[column] += extraSpace; }
      }
    }
  }
  @Override public TableLayout.LayoutParams generateLayoutParams(AttributeSet a) { return new TableLayout.LayoutParams(getContext(), a); }
  @Override protected TableLayout.LayoutParams generateDefaultLayoutParams() { return new LayoutParams(); }
  @Override protected boolean checkLayoutParams(ViewGroup.LayoutParams p) { return p instanceof TableLayout.LayoutParams; }
  @Override protected LinearLayout.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) { return new LayoutParams(p); }
  public static class LayoutParams extends LinearLayout.LayoutParams {
    public LayoutParams(Context c, AttributeSet attrs) {
      super(c, attrs);
      width = MATCH_PARENT;
      if (attrs instanceof Attrs && ((Attrs) attrs).a("layout_height") == null) height = WRAP_CONTENT;
    }
    public LayoutParams(int w, int h) { super(MATCH_PARENT, h); }
    public LayoutParams(int w, int h, float weight) { super(MATCH_PARENT, h, weight); }
    public LayoutParams() { super(MATCH_PARENT, WRAP_CONTENT); }
    public LayoutParams(ViewGroup.LayoutParams p) { super(p); width = MATCH_PARENT; }
    public LayoutParams(MarginLayoutParams s) { super(s); width = MATCH_PARENT; }
  }
}
