package android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import a2w.Attrs;
import a2w.Values;

/**
 * Children in a grid of rows and columns, as the platform's GridLayout: cells given by row/column specs (start, span,
 * alignment, weight) or placed automatically in orientation order; each column (row) is as wide as its widest
 * single-span child, wider spans widen their last flexible column; excess space goes to weighted columns by weight,
 * else to the last flexible one (a column is flexible when all its children define an alignment or a weight), else
 * stays at the end. Rows default to baseline alignment; default margins (useDefaultMargins) are 4dp, 0 at the edges.
 */
public class GridLayout extends ViewGroup {
  public static final int HORIZONTAL = LinearLayout.HORIZONTAL, VERTICAL = LinearLayout.VERTICAL;
  public static final int UNDEFINED = Integer.MIN_VALUE;
  public static final int ALIGN_BOUNDS = 0, ALIGN_MARGINS = 1;
  static final int DEFAULT_ORIENTATION = HORIZONTAL, DEFAULT_COUNT = UNDEFINED;

  int mOrientation = DEFAULT_ORIENTATION;
  int mRowCount = DEFAULT_COUNT, mColumnCount = DEFAULT_COUNT;
  boolean mUseDefaultMargins, mRowOrderPreserved = true, mColumnOrderPreserved = true;
  int mAlignmentMode = ALIGN_MARGINS;
  final int mDefaultGap;
  android.util.Printer mPrinter = new android.util.LogPrinter(android.util.Log.DEBUG, "GridLayout");

  public GridLayout(Context context) { this(context, null); }
  public GridLayout(Context context, AttributeSet attrs) { this(context, attrs, 0); }
  public GridLayout(Context context, AttributeSet attrs, int defStyleAttr) { this(context, attrs, defStyleAttr, 0); }
  public GridLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr);
    mDefaultGap = Math.round(8 * context.getResources().getDisplayMetrics().density);
    if (mCtorAttrs instanceof Attrs) {
      Attrs a = (Attrs) mCtorAttrs;
      String v;
      if ((v = a.a("orientation")) != null) mOrientation = v.equals("vertical") || v.equals("1") ? VERTICAL : HORIZONTAL;
      if ((v = a.a("rowCount")) != null) mRowCount = Values.parseInt(v, DEFAULT_COUNT);
      if ((v = a.a("columnCount")) != null) mColumnCount = Values.parseInt(v, DEFAULT_COUNT);
      if ((v = a.a("useDefaultMargins")) != null) mUseDefaultMargins = Values.parseBool(v, false);
      if ((v = a.a("alignmentMode")) != null) mAlignmentMode = v.equals("alignBounds") || v.equals("0") ? ALIGN_BOUNDS : ALIGN_MARGINS;
      if ((v = a.a("rowOrderPreserved")) != null) mRowOrderPreserved = Values.parseBool(v, true);
      if ((v = a.a("columnOrderPreserved")) != null) mColumnOrderPreserved = Values.parseBool(v, true);
    }
  }

  public int getOrientation() { return mOrientation; }
  public void setOrientation(int orientation) { if (mOrientation != orientation) { mOrientation = orientation; requestLayout(); } }
  public int getRowCount() { return mRowCount != UNDEFINED ? mRowCount : Math.max(0, count(false)); }
  public void setRowCount(int rowCount) { if (rowCount != UNDEFINED && rowCount < 0) throw new IllegalArgumentException("rowCount must be greater than or equal to 0"); mRowCount = rowCount; requestLayout(); }
  public int getColumnCount() { return mColumnCount != UNDEFINED ? mColumnCount : Math.max(0, count(true)); }
  public void setColumnCount(int columnCount) { if (columnCount != UNDEFINED && columnCount < 0) throw new IllegalArgumentException("columnCount must be greater than or equal to 0"); mColumnCount = columnCount; requestLayout(); }
  public boolean getUseDefaultMargins() { return mUseDefaultMargins; }
  public void setUseDefaultMargins(boolean useDefaultMargins) { mUseDefaultMargins = useDefaultMargins; requestLayout(); }
  public int getAlignmentMode() { return mAlignmentMode; }
  public void setAlignmentMode(int alignmentMode) { mAlignmentMode = alignmentMode; requestLayout(); }
  public boolean isRowOrderPreserved() { return mRowOrderPreserved; }
  public void setRowOrderPreserved(boolean rowOrderPreserved) { mRowOrderPreserved = rowOrderPreserved; requestLayout(); }
  public boolean isColumnOrderPreserved() { return mColumnOrderPreserved; }
  public void setColumnOrderPreserved(boolean columnOrderPreserved) { mColumnOrderPreserved = columnOrderPreserved; requestLayout(); }
  public android.util.Printer getPrinter() { return mPrinter; }
  public void setPrinter(android.util.Printer printer) { mPrinter = printer == null ? new android.util.Printer() { public void println(String x) {} } : printer; }
  @Override public CharSequence getAccessibilityClassName() { return GridLayout.class.getName(); }
  @Override public void onViewAdded(View child) { super.onViewAdded(child); mCells = null; }
  @Override public void onViewRemoved(View child) { super.onViewRemoved(child); mCells = null; }
  @Override public void requestLayout() { mCells = null; super.requestLayout(); }

  // ---------------------------------------------------------------- cells
  /** per child: {row, rowEnd, column, columnEnd} */
  private int[][] mCells;
  private int mRows, mColumns;
  private void validate() {
    if (mCells != null) return;
    final int n = getChildCount();
    mCells = new int[n][];
    final boolean horizontal = mOrientation == HORIZONTAL;
    final int definedCount = horizontal ? mColumnCount : mRowCount;
    final int count = definedCount != UNDEFINED ? definedCount : 0;
    int major = 0, minor = 0;
    int[] maxSizes = new int[count];
    for (int i = 0; i < n; i++) {
      LayoutParams lp = (LayoutParams) getChildAt(i).getLayoutParams();
      Spec majorSpec = horizontal ? lp.rowSpec : lp.columnSpec, minorSpec = horizontal ? lp.columnSpec : lp.rowSpec;
      boolean majorDefined = majorSpec.startDefined, minorDefined = minorSpec.startDefined;
      int majorSpan = majorSpec.size;
      if (majorDefined) major = majorSpec.start;
      int minorSpan = minorSpec.size;
      if (count != 0) { int min = minorDefined ? Math.min(minorSpec.start, count) : 0; minorSpan = Math.min(minorSpan, count - min); }
      if (minorDefined) minor = minorSpec.start;
      if (count != 0) {
        if (!majorDefined || !minorDefined) {
          while (!fits(maxSizes, major, minor, minor + minorSpan)) {
            if (minorDefined) major++;
            else if (minor + minorSpan <= count) minor++;
            else { minor = 0; major++; }
          }
        }
        java.util.Arrays.fill(maxSizes, Math.min(minor, count), Math.min(minor + minorSpan, count), major + majorSpan);
      }
      mCells[i] = horizontal ? new int[] {major, major + majorSpan, minor, minor + minorSpan} : new int[] {minor, minor + minorSpan, major, major + majorSpan};
      minor = minor + minorSpan;
    }
    int rows = 0, cols = 0;
    for (int[] c : mCells) { rows = Math.max(rows, c[1]); cols = Math.max(cols, c[3]); }
    mRows = Math.max(rows, mRowCount == UNDEFINED ? 0 : mRowCount);
    mColumns = Math.max(cols, mColumnCount == UNDEFINED ? 0 : mColumnCount);
  }
  private static boolean fits(int[] a, int value, int start, int end) {
    if (end > a.length) return false;
    for (int i = start; i < end; i++) if (a[i] > value) return false;
    return true;
  }
  private int count(boolean horizontal) { validate(); return horizontal ? mColumns : mRows; }

  // ---------------------------------------------------------------- margins
  private int margin(View c, boolean horizontal, boolean leading, int index) {
    LayoutParams lp = (LayoutParams) c.getLayoutParams();
    int m = horizontal ? (leading ? lp.leftMargin : lp.rightMargin) : (leading ? lp.topMargin : lp.bottomMargin);
    if (m != UNDEFINED) return m;
    if (!mUseDefaultMargins || c instanceof Space) return 0;
    int[] cell = mCells[index];
    boolean atEdge = horizontal ? (leading ? cell[2] == 0 : cell[3] == mColumns) : (leading ? cell[0] == 0 : cell[1] == mRows);
    return atEdge ? 0 : mDefaultGap / 2;
  }
  private int totalMargin(View c, boolean horizontal, int index) { return margin(c, horizontal, true, index) + margin(c, horizontal, false, index); }

  // ---------------------------------------------------------------- axes
  private int[] mHLocations, mVLocations;
  private int[] mRowBaselines;
  /** the locations of the column (row) boundaries for a parent size between min and max */
  private int[] locations(boolean horizontal, int parentMin, int parentMax) {
    final int n = horizontal ? mColumns : mRows;
    int[] size = new int[n];
    float[] weight = new float[n];
    boolean[] hasChild = new boolean[n], inflexible = new boolean[n];
    int[] before = horizontal ? null : new int[n], after = horizontal ? null : new int[n];
    java.util.ArrayList<int[]> spans = new java.util.ArrayList<>();   // {start, end, size, flexible}
    for (int i = 0; i < getChildCount(); i++) {
      View c = getChildAt(i);
      if (c.getVisibility() == GONE) continue;
      LayoutParams lp = (LayoutParams) c.getLayoutParams();
      Spec spec = horizontal ? lp.columnSpec : lp.rowSpec;
      int[] cell = mCells[i];
      int s = horizontal ? cell[2] : cell[0], e = horizontal ? cell[3] : cell[1];
      if (e <= s || e > n) continue;
      int need = (horizontal ? c.getMeasuredWidth() : c.getMeasuredHeight()) + totalMargin(c, horizontal, i);
      boolean flexible = spec.alignment != UNDEFINED_ALIGNMENT || spec.weight != 0;
      if (e - s == 1) {
        hasChild[s] = true;
        if (!flexible) inflexible[s] = true;
        weight[s] = Math.max(weight[s], spec.weight);
        Alignment al = spec.alignment == UNDEFINED_ALIGNMENT && !horizontal ? BASELINE : spec.alignment;
        int baseline = !horizontal && al == BASELINE ? c.getBaseline() : -1;
        if (baseline >= 0) {
          int b = margin(c, false, true, i) + baseline;
          before[s] = Math.max(before[s], b);
          after[s] = Math.max(after[s], need - b);
        } else size[s] = Math.max(size[s], need);
      } else spans.add(new int[] {s, e, need, flexible ? 1 : 0});
    }
    if (!horizontal) for (int r = 0; r < n; r++) size[r] = Math.max(size[r], before[r] + after[r]);
    if (!horizontal) mRowBaselines = before;
    spans.sort((a, b) -> (a[1] - a[0]) - (b[1] - b[0]));
    for (int[] sp : spans) {
      int sum = 0;
      for (int k = sp[0]; k < sp[1]; k++) sum += size[k];
      if (sum >= sp[2]) continue;
      int target = sp[1] - 1;
      for (int k = sp[1] - 1; k >= sp[0]; k--) if (!inflexible[k]) { target = k; break; }
      size[target] += sp[2] - sum;
    }
    int total = 0;
    for (int v : size) total += v;
    if (parentMin > total && n > 0) {
      int excess = parentMin - total;
      float totalWeight = 0;
      for (float w : weight) totalWeight += w;
      if (totalWeight > 0) {
        int given = 0, last = -1;
        for (int k = 0; k < n; k++) if (weight[k] > 0) { int d = Math.round(excess * weight[k] / totalWeight); size[k] += d; given += d; last = k; }
        size[last] += excess - given;
      } else {
        int target = -1;
        for (int k = n - 1; k >= 0; k--) if (!inflexible[k]) { target = k; break; }
        if (target >= 0) size[target] += excess;
      }
    }
    int[] loc = new int[n + 1];
    for (int k = 0; k < n; k++) loc[k + 1] = loc[k] + size[k];
    return loc;
  }
  private static int[] limits(int spec) {
    int mode = MeasureSpec.getMode(spec), size = MeasureSpec.getSize(spec);
    switch (mode) {
      case MeasureSpec.EXACTLY: return new int[] {size, size};
      case MeasureSpec.AT_MOST: return new int[] {0, size};
      default: return new int[] {0, Integer.MAX_VALUE};
    }
  }

  // ---------------------------------------------------------------- measure and layout
  private void measureChild2(View c, int index, int widthSpec, int heightSpec, int childWidth, int childHeight) {
    int ws = getChildMeasureSpec(widthSpec, totalMargin(c, true, index), childWidth);
    int hs = getChildMeasureSpec(heightSpec, totalMargin(c, false, index), childHeight);
    c.measure(ws, hs);
  }
  private void measureChildren(int widthSpec, int heightSpec, boolean firstPass) {
    final boolean horizontal = mOrientation == HORIZONTAL;
    for (int i = 0; i < getChildCount(); i++) {
      View c = getChildAt(i);
      if (c.getVisibility() == GONE) continue;
      LayoutParams lp = (LayoutParams) c.getLayoutParams();
      if (firstPass) { measureChild2(c, i, widthSpec, heightSpec, lp.width, lp.height); continue; }
      Spec spec = horizontal ? lp.columnSpec : lp.rowSpec;
      if (spec.alignment != FILL) continue;
      int[] loc = horizontal ? mHLocations : mVLocations;
      int[] cell = mCells[i];
      int s = horizontal ? cell[2] : cell[0], e = horizontal ? cell[3] : cell[1];
      int viewSize = loc[Math.min(e, loc.length - 1)] - loc[Math.min(s, loc.length - 1)] - totalMargin(c, horizontal, i);
      if (horizontal) measureChild2(c, i, widthSpec, heightSpec, Math.max(0, viewSize), lp.height);
      else measureChild2(c, i, widthSpec, heightSpec, lp.width, Math.max(0, viewSize));
    }
  }
  private static int adjust(int measureSpec, int delta) {
    return MeasureSpec.makeMeasureSpec(Math.max(0, MeasureSpec.getSize(measureSpec) + delta), MeasureSpec.getMode(measureSpec));
  }
  @Override protected void onMeasure(int widthSpec, int heightSpec) {
    validate();
    int hPadding = getPaddingLeft() + getPaddingRight(), vPadding = getPaddingTop() + getPaddingBottom();
    int widthSansPadding = adjust(widthSpec, -hPadding), heightSansPadding = adjust(heightSpec, -vPadding);
    measureChildren(widthSansPadding, heightSansPadding, true);
    int w, h;
    int[] wl = limits(widthSansPadding), hl = limits(heightSansPadding);
    if (mOrientation == HORIZONTAL) {
      mHLocations = locations(true, wl[0], wl[1]);
      w = mHLocations[mHLocations.length - 1];
      measureChildren(widthSansPadding, heightSansPadding, false);
      mVLocations = locations(false, hl[0], hl[1]);
      h = mVLocations[mVLocations.length - 1];
    } else {
      mVLocations = locations(false, hl[0], hl[1]);
      h = mVLocations[mVLocations.length - 1];
      measureChildren(widthSansPadding, heightSansPadding, false);
      mHLocations = locations(true, wl[0], wl[1]);
      w = mHLocations[mHLocations.length - 1];
    }
    int measuredWidth = Math.max(w + hPadding, getSuggestedMinimumWidth()), measuredHeight = Math.max(h + vPadding, getSuggestedMinimumHeight());
    setMeasuredDimension(resolveSizeAndState(measuredWidth, widthSpec, 0), resolveSizeAndState(measuredHeight, heightSpec, 0));
  }
  @Override protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    validate();
    int targetWidth = right - left - getPaddingLeft() - getPaddingRight(), targetHeight = bottom - top - getPaddingTop() - getPaddingBottom();
    int[] hl = locations(true, targetWidth, targetWidth), vl = locations(false, targetHeight, targetHeight);
    int[] baselines = mRowBaselines;
    boolean rtl = isLayoutRtl();
    for (int i = 0; i < getChildCount(); i++) {
      View c = getChildAt(i);
      if (c.getVisibility() == GONE) continue;
      LayoutParams lp = (LayoutParams) c.getLayoutParams();
      int[] cell = mCells[i];
      if (cell[3] >= hl.length || cell[1] >= vl.length) continue;
      int x1 = hl[cell[2]], x2 = hl[cell[3]], y1 = vl[cell[0]], y2 = vl[cell[1]];
      int cellWidth = x2 - x1, cellHeight = y2 - y1;
      int pWidth = c.getMeasuredWidth(), pHeight = c.getMeasuredHeight();
      int leftMargin = margin(c, true, true, i), rightMargin = margin(c, true, false, i), topMargin = margin(c, false, true, i), bottomMargin = margin(c, false, false, i);
      Alignment hAlign = lp.columnSpec.alignment == UNDEFINED_ALIGNMENT ? START : lp.columnSpec.alignment;
      Alignment vAlign = lp.rowSpec.alignment == UNDEFINED_ALIGNMENT ? BASELINE : lp.rowSpec.alignment;
      int hRoom = cellWidth - leftMargin - rightMargin, vRoom = cellHeight - topMargin - bottomMargin;
      int width = hAlign == FILL ? Math.max(0, hRoom) : pWidth, height = vAlign == FILL ? Math.max(0, vRoom) : pHeight;
      int dx = hAlign.offset(hRoom - width, rtl, true), dy;
      if (vAlign == BASELINE && cell[1] - cell[0] == 1 && c.getBaseline() >= 0 && baselines != null) dy = baselines[cell[0]] - topMargin - c.getBaseline();
      else dy = vAlign.offset(vRoom - height, rtl, false);
      int cx = rtl ? getPaddingLeft() + (targetWidth - x2) + rightMargin + (hRoom - width - dx) : getPaddingLeft() + x1 + leftMargin + dx;
      int cy = getPaddingTop() + y1 + topMargin + dy;
      if (width != c.getMeasuredWidth() || height != c.getMeasuredHeight()) c.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
      c.layout(cx, cy, cx + width, cy + height);
    }
  }

  // ---------------------------------------------------------------- layout params
  @Override protected boolean checkLayoutParams(ViewGroup.LayoutParams p) { return p instanceof LayoutParams; }
  @Override protected LayoutParams generateDefaultLayoutParams() { return new LayoutParams(); }
  @Override public LayoutParams generateLayoutParams(AttributeSet attrs) { return new LayoutParams(getContext(), attrs); }
  @Override protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams lp) {
    if (lp instanceof LayoutParams) return new LayoutParams((LayoutParams) lp);
    if (lp instanceof MarginLayoutParams) return new LayoutParams((MarginLayoutParams) lp);
    return new LayoutParams(lp);
  }

  // ---------------------------------------------------------------- specs and alignments
  public static Spec spec(int start, int size, Alignment alignment, float weight) { return new Spec(start != UNDEFINED, start, size, alignment, weight); }
  public static Spec spec(int start, Alignment alignment, float weight) { return spec(start, 1, alignment, weight); }
  public static Spec spec(int start, int size, float weight) { return spec(start, size, UNDEFINED_ALIGNMENT, weight); }
  public static Spec spec(int start, float weight) { return spec(start, 1, weight); }
  public static Spec spec(int start, int size, Alignment alignment) { return spec(start, size, alignment, Spec.DEFAULT_WEIGHT); }
  public static Spec spec(int start, Alignment alignment) { return spec(start, 1, alignment); }
  public static Spec spec(int start, int size) { return spec(start, size, UNDEFINED_ALIGNMENT); }
  public static Spec spec(int start) { return spec(start, 1); }

  /** A cell range (start, span) along one axis, with an alignment and a weight. */
  public static class Spec {
    static final Spec UNDEFINED_SPEC = GridLayout.spec(UNDEFINED);
    static final float DEFAULT_WEIGHT = 0;
    final boolean startDefined;
    final int start, size;
    final Alignment alignment;
    final float weight;
    Spec(boolean startDefined, int start, int size, Alignment alignment, float weight) {
      this.startDefined = startDefined; this.start = startDefined ? start : 0; this.size = Math.max(1, size); this.alignment = alignment; this.weight = weight;
    }
    Spec copyWriteAlignment(Alignment a) { return new Spec(startDefined, start, size, a, weight); }
    @Override public boolean equals(Object that) {
      if (this == that) return true;
      if (!(that instanceof Spec)) return false;
      Spec s = (Spec) that;
      return alignment.equals(s.alignment) && start == s.start && size == s.size && startDefined == s.startDefined;
    }
    @Override public int hashCode() { return (31 * (31 * start + size)) + alignment.hashCode(); }
  }

  /** How a child sits in its cell along one axis. */
  public static abstract class Alignment {
    Alignment() {}
    /** the child's offset in the room left in its cell */
    abstract int offset(int room, boolean rtl, boolean horizontal);
  }
  static final class Fixed extends Alignment {
    final int kind;   // 0 leading, 1 trailing, 2 center, 3 fill, 4 start, 5 end, 6 baseline, 7 undefined
    Fixed(int kind) { this.kind = kind; }
    int offset(int room, boolean rtl, boolean horizontal) {
      switch (kind) {
        case 1: return room;
        case 2: return room / 2;
        case 4: return horizontal && rtl ? room : 0;
        case 5: return horizontal && rtl ? 0 : room;
        default: return 0;
      }
    }
  }
  static final Alignment UNDEFINED_ALIGNMENT = new Fixed(7);
  public static final Alignment TOP = new Fixed(0), BOTTOM = new Fixed(1), START = new Fixed(4), END = new Fixed(5);
  public static final Alignment LEFT = new Fixed(0), RIGHT = new Fixed(1), CENTER = new Fixed(2), BASELINE = new Fixed(6), FILL = new Fixed(3);
  static Alignment getAlignment(int gravity, boolean horizontal) {
    int mask = horizontal ? Gravity.HORIZONTAL_GRAVITY_MASK : Gravity.VERTICAL_GRAVITY_MASK;
    int shift = horizontal ? Gravity.AXIS_X_SHIFT : Gravity.AXIS_Y_SHIFT;
    int flags = (gravity & mask) >> shift;
    switch (flags) {
      case (Gravity.AXIS_SPECIFIED | Gravity.AXIS_PULL_BEFORE): return horizontal ? LEFT : TOP;
      case (Gravity.AXIS_SPECIFIED | Gravity.AXIS_PULL_AFTER): return horizontal ? RIGHT : BOTTOM;
      case (Gravity.AXIS_SPECIFIED | Gravity.AXIS_PULL_BEFORE | Gravity.AXIS_PULL_AFTER): return FILL;
      case Gravity.AXIS_SPECIFIED: return CENTER;
      case (Gravity.AXIS_SPECIFIED | Gravity.AXIS_PULL_BEFORE | Gravity.RELATIVE_LAYOUT_DIRECTION): return START;
      case (Gravity.AXIS_SPECIFIED | Gravity.AXIS_PULL_AFTER | Gravity.RELATIVE_LAYOUT_DIRECTION): return END;
      default: return UNDEFINED_ALIGNMENT;
    }
  }

  /** A child's cell (row and column specs) and margins; margins not given are UNDEFINED (default margins apply). */
  public static class LayoutParams extends MarginLayoutParams {
    private static final int DEFAULT_MARGIN = UNDEFINED;
    public Spec rowSpec = Spec.UNDEFINED_SPEC, columnSpec = Spec.UNDEFINED_SPEC;
    private LayoutParams(int width, int height, int left, int top, int right, int bottom, Spec rowSpec, Spec columnSpec) {
      super(width, height);
      setMargins(left, top, right, bottom);
      this.rowSpec = rowSpec; this.columnSpec = columnSpec;
    }
    public LayoutParams(Spec rowSpec, Spec columnSpec) { this(WRAP_CONTENT, WRAP_CONTENT, DEFAULT_MARGIN, DEFAULT_MARGIN, DEFAULT_MARGIN, DEFAULT_MARGIN, rowSpec, columnSpec); }
    public LayoutParams() { this(Spec.UNDEFINED_SPEC, Spec.UNDEFINED_SPEC); }
    public LayoutParams(ViewGroup.LayoutParams params) { super(params); }
    public LayoutParams(MarginLayoutParams params) { super(params); }
    public LayoutParams(LayoutParams source) { super((MarginLayoutParams) source); rowSpec = source.rowSpec; columnSpec = source.columnSpec; }
    public LayoutParams(Context context, AttributeSet attrs) {
      super(context, attrs);
      if (!(attrs instanceof Attrs)) return;
      Attrs a = (Attrs) attrs;
      if (a.a("layout_margin") == null) {
        if (a.a("layout_marginLeft") == null && a.a("layout_marginStart") == null && a.a("layout_marginHorizontal") == null) leftMargin = DEFAULT_MARGIN;
        if (a.a("layout_marginTop") == null && a.a("layout_marginVertical") == null) topMargin = DEFAULT_MARGIN;
        if (a.a("layout_marginRight") == null && a.a("layout_marginEnd") == null && a.a("layout_marginHorizontal") == null) rightMargin = DEFAULT_MARGIN;
        if (a.a("layout_marginBottom") == null && a.a("layout_marginVertical") == null) bottomMargin = DEFAULT_MARGIN;
      }
      String g = a.a("layout_gravity");
      int gravity = g != null ? Values.gravity(g) : Gravity.NO_GRAVITY;
      int column = Values.parseInt(a.a("layout_column"), UNDEFINED), colSpan = Values.parseInt(a.a("layout_columnSpan"), 1);
      float colWeight = Values.parseFloat(a.a("layout_columnWeight"), Spec.DEFAULT_WEIGHT);
      columnSpec = spec(column, colSpan, getAlignment(gravity, true), colWeight);
      int row = Values.parseInt(a.a("layout_row"), UNDEFINED), rowSpan = Values.parseInt(a.a("layout_rowSpan"), 1);
      float rowWeight = Values.parseFloat(a.a("layout_rowWeight"), Spec.DEFAULT_WEIGHT);
      rowSpec = spec(row, rowSpan, getAlignment(gravity, false), rowWeight);
    }
    public void setGravity(int gravity) { rowSpec = rowSpec.copyWriteAlignment(getAlignment(gravity, false)); columnSpec = columnSpec.copyWriteAlignment(getAlignment(gravity, true)); }
    @Override protected void setBaseAttributes(android.content.res.TypedArray attributes, int widthAttr, int heightAttr) {
      width = attributes.getLayoutDimension(widthAttr, WRAP_CONTENT);
      height = attributes.getLayoutDimension(heightAttr, WRAP_CONTENT);
    }
    @Override public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      LayoutParams that = (LayoutParams) o;
      return columnSpec.equals(that.columnSpec) && rowSpec.equals(that.rowSpec);
    }
    @Override public int hashCode() { return 31 * rowSpec.hashCode() + columnSpec.hashCode(); }
  }
}
