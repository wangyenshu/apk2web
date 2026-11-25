package android.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import a2w.Attrs;
import a2w.Values;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

/** RelativeLayout after the Android 4.4 implementation. */
public class RelativeLayout extends ViewGroup {
  public static final int TRUE = -1;
  public static final int LEFT_OF = 0, RIGHT_OF = 1, ABOVE = 2, BELOW = 3, ALIGN_BASELINE = 4, ALIGN_LEFT = 5, ALIGN_TOP = 6, ALIGN_RIGHT = 7,
      ALIGN_BOTTOM = 8, ALIGN_PARENT_LEFT = 9, ALIGN_PARENT_TOP = 10, ALIGN_PARENT_RIGHT = 11, ALIGN_PARENT_BOTTOM = 12, CENTER_IN_PARENT = 13,
      CENTER_HORIZONTAL = 14, CENTER_VERTICAL = 15, START_OF = 16, END_OF = 17, ALIGN_START = 18, ALIGN_END = 19, ALIGN_PARENT_START = 20, ALIGN_PARENT_END = 21;
  static final int VERB_COUNT = 22;
  static final int[] RULES_VERTICAL = {ABOVE, BELOW, ALIGN_BASELINE, ALIGN_TOP, ALIGN_BOTTOM};
  static final int[] RULES_HORIZONTAL = {LEFT_OF, RIGHT_OF, ALIGN_LEFT, ALIGN_RIGHT, START_OF, END_OF, ALIGN_START, ALIGN_END};

  private View mBaselineView;
  private boolean mHasBaselineAlignedChild;
  private int mGravity = Gravity.LEFT | Gravity.TOP;
  private final Rect mContentBounds = new Rect(), mSelfBounds = new Rect();
  private int mIgnoreGravity = View.NO_ID;
  private boolean mDirtyHierarchy = true;
  private View[] mSortedHorizontalChildren = new View[0], mSortedVerticalChildren = new View[0];
  private final DependencyGraph mGraph = new DependencyGraph();
  /** the game targets API 8: pre-JB-MR2 measure specs and pre-KitKat wrap_content sizing */
  private final boolean mAllowBrokenMeasureSpecs = true;

  public RelativeLayout(Context c) { super(c); }
  public RelativeLayout(Context c, AttributeSet a) { this(c, a, 0); }
  public RelativeLayout(Context c, AttributeSet a, int d) {
    super(c, a, d);
    if (a instanceof Attrs) {
      Attrs at = (Attrs) a;
      String v;
      if ((v = at.a("ignoreGravity")) != null) mIgnoreGravity = Values.refId(v);
      if ((v = at.a("gravity")) != null) mGravity = Values.gravity(v);
    }
  }
  @Override public boolean shouldDelayChildPressedState() { return false; }
  public void setIgnoreGravity(int id) { mIgnoreGravity = id; }
  public int getGravity() { return mGravity; }
  public void setGravity(int g) {
    if (mGravity != g) {
      if ((g & Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK) == 0) g |= Gravity.LEFT;
      if ((g & Gravity.VERTICAL_GRAVITY_MASK) == 0) g |= Gravity.TOP;
      mGravity = g;
      requestLayout();
    }
  }
  public void setHorizontalGravity(int h) { int g = h & Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK; if ((mGravity & Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK) != g) { mGravity = (mGravity & ~Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK) | g; requestLayout(); } }
  public void setVerticalGravity(int v) { int g = v & Gravity.VERTICAL_GRAVITY_MASK; if ((mGravity & Gravity.VERTICAL_GRAVITY_MASK) != g) { mGravity = (mGravity & ~Gravity.VERTICAL_GRAVITY_MASK) | g; requestLayout(); } }
  @Override public int getBaseline() { return mBaselineView != null ? mBaselineView.getBaseline() : super.getBaseline(); }
  @Override public void requestLayout() { super.requestLayout(); mDirtyHierarchy = true; }

  private void sortChildren() {
    int count = getChildCount();
    if (mSortedVerticalChildren.length != count) mSortedVerticalChildren = new View[count];
    if (mSortedHorizontalChildren.length != count) mSortedHorizontalChildren = new View[count];
    final DependencyGraph graph = mGraph;
    graph.clear();
    for (int i = 0; i < count; i++) graph.add(getChildAt(i));
    graph.getSortedViews(mSortedVerticalChildren, RULES_VERTICAL);
    graph.getSortedViews(mSortedHorizontalChildren, RULES_HORIZONTAL);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    if (mDirtyHierarchy) { mDirtyHierarchy = false; sortChildren(); }
    int myWidth = -1, myHeight = -1, width = 0, height = 0;
    final int widthMode = MeasureSpec.getMode(widthMeasureSpec), heightMode = MeasureSpec.getMode(heightMeasureSpec);
    final int widthSize = MeasureSpec.getSize(widthMeasureSpec), heightSize = MeasureSpec.getSize(heightMeasureSpec);
    if (widthMode != MeasureSpec.UNSPECIFIED) myWidth = widthSize;
    if (heightMode != MeasureSpec.UNSPECIFIED) myHeight = heightSize;
    if (widthMode == MeasureSpec.EXACTLY) width = myWidth;
    if (heightMode == MeasureSpec.EXACTLY) height = myHeight;
    mHasBaselineAlignedChild = false;
    View ignore = null;
    int gravity = mGravity & Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK;
    final boolean horizontalGravity = gravity != Gravity.LEFT && gravity != 0;
    gravity = mGravity & Gravity.VERTICAL_GRAVITY_MASK;
    final boolean verticalGravity = gravity != Gravity.TOP && gravity != 0;
    int left = Integer.MAX_VALUE, top = Integer.MAX_VALUE, right = Integer.MIN_VALUE, bottom = Integer.MIN_VALUE;
    boolean offsetHorizontalAxis = false, offsetVerticalAxis = false;
    if ((horizontalGravity || verticalGravity) && mIgnoreGravity != View.NO_ID) ignore = findViewById(mIgnoreGravity);
    final boolean isWrapContentWidth = widthMode != MeasureSpec.EXACTLY;
    final boolean isWrapContentHeight = heightMode != MeasureSpec.EXACTLY;
    View[] views = mSortedHorizontalChildren;
    int count = views.length;
    for (int i = 0; i < count; i++) {
      View child = views[i];
      if (child.getVisibility() != GONE) {
        LayoutParams params = (LayoutParams) child.getLayoutParams();
        applyHorizontalSizeRules(params, myWidth, params.mRules);
        measureChildHorizontal(child, params, myWidth, myHeight);
        if (positionChildHorizontal(child, params, myWidth, isWrapContentWidth)) offsetHorizontalAxis = true;
      }
    }
    views = mSortedVerticalChildren;
    count = views.length;
    for (int i = 0; i < count; i++) {
      View child = views[i];
      if (child.getVisibility() != GONE) {
        LayoutParams params = (LayoutParams) child.getLayoutParams();
        applyVerticalSizeRules(params, myHeight);
        measureChild(child, params, myWidth, myHeight);
        if (positionChildVertical(child, params, myHeight, isWrapContentHeight)) offsetVerticalAxis = true;
        if (isWrapContentWidth) width = Math.max(width, params.mRight);
        if (isWrapContentHeight) height = Math.max(height, params.mBottom);
        if (child != ignore || verticalGravity) { left = Math.min(left, params.mLeft - params.leftMargin); top = Math.min(top, params.mTop - params.topMargin); }
        if (child != ignore || horizontalGravity) { right = Math.max(right, params.mRight + params.rightMargin); bottom = Math.max(bottom, params.mBottom + params.bottomMargin); }
      }
    }
    if (mHasBaselineAlignedChild) {
      for (int i = 0; i < count; i++) {
        View child = getChildAt(i);
        if (child.getVisibility() != GONE) {
          LayoutParams params = (LayoutParams) child.getLayoutParams();
          alignBaseline(child, params);
          if (child != ignore || verticalGravity) { left = Math.min(left, params.mLeft - params.leftMargin); top = Math.min(top, params.mTop - params.topMargin); }
          if (child != ignore || horizontalGravity) { right = Math.max(right, params.mRight + params.rightMargin); bottom = Math.max(bottom, params.mBottom + params.bottomMargin); }
        }
      }
    }
    if (isWrapContentWidth) {
      width += mPaddingRight;
      if (mLayoutParams != null && mLayoutParams.width >= 0) width = Math.max(width, mLayoutParams.width);
      width = Math.max(width, getSuggestedMinimumWidth());
      width = resolveSize(width, widthMeasureSpec);
      if (offsetHorizontalAxis) {
        for (int i = 0; i < count; i++) {
          View child = getChildAt(i);
          if (child.getVisibility() != GONE) {
            LayoutParams params = (LayoutParams) child.getLayoutParams();
            final int[] rules = params.mRules;
            if (rules[CENTER_IN_PARENT] != 0 || rules[CENTER_HORIZONTAL] != 0) centerHorizontal(child, params, width);
            else if (rules[ALIGN_PARENT_RIGHT] != 0) {
              final int childWidth = child.getMeasuredWidth();
              params.mLeft = width - mPaddingRight - childWidth;
              params.mRight = params.mLeft + childWidth;
            }
          }
        }
      }
    }
    if (isWrapContentHeight) {
      height += mPaddingBottom;
      if (mLayoutParams != null && mLayoutParams.height >= 0) height = Math.max(height, mLayoutParams.height);
      height = Math.max(height, getSuggestedMinimumHeight());
      height = resolveSize(height, heightMeasureSpec);
      if (offsetVerticalAxis) {
        for (int i = 0; i < count; i++) {
          View child = getChildAt(i);
          if (child.getVisibility() != GONE) {
            LayoutParams params = (LayoutParams) child.getLayoutParams();
            final int[] rules = params.mRules;
            if (rules[CENTER_IN_PARENT] != 0 || rules[CENTER_VERTICAL] != 0) centerVertical(child, params, height);
            else if (rules[ALIGN_PARENT_BOTTOM] != 0) {
              final int childHeight = child.getMeasuredHeight();
              params.mTop = height - mPaddingBottom - childHeight;
              params.mBottom = params.mTop + childHeight;
            }
          }
        }
      }
    }
    if (horizontalGravity || verticalGravity) {
      final Rect selfBounds = mSelfBounds;
      selfBounds.set(mPaddingLeft, mPaddingTop, width - mPaddingRight, height - mPaddingBottom);
      final Rect contentBounds = mContentBounds;
      Gravity.apply(mGravity, right - left, bottom - top, selfBounds, contentBounds);
      final int horizontalOffset = contentBounds.left - left, verticalOffset = contentBounds.top - top;
      if (horizontalOffset != 0 || verticalOffset != 0) {
        for (int i = 0; i < count; i++) {
          View child = getChildAt(i);
          if (child.getVisibility() != GONE && child != ignore) {
            LayoutParams params = (LayoutParams) child.getLayoutParams();
            if (horizontalGravity) { params.mLeft += horizontalOffset; params.mRight += horizontalOffset; }
            if (verticalGravity) { params.mTop += verticalOffset; params.mBottom += verticalOffset; }
          }
        }
      }
    }
    setMeasuredDimension(width, height);
  }

  private void alignBaseline(View child, LayoutParams params) {
    final int[] rules = params.mRules;
    int anchorBaseline = getRelatedViewBaseline(rules, ALIGN_BASELINE);
    if (anchorBaseline != -1) {
      LayoutParams anchorParams = getRelatedViewParams(rules, ALIGN_BASELINE);
      if (anchorParams != null) {
        int offset = anchorParams.mTop + anchorBaseline;
        int baseline = child.getBaseline();
        if (baseline != -1) offset -= baseline;
        int height = params.mBottom - params.mTop;
        params.mTop = offset;
        params.mBottom = params.mTop + height;
      }
    }
    if (mBaselineView == null) mBaselineView = child;
    else {
      LayoutParams lp = (LayoutParams) mBaselineView.getLayoutParams();
      if (params.mTop < lp.mTop || (params.mTop == lp.mTop && params.mLeft < lp.mLeft)) mBaselineView = child;
    }
  }

  private void measureChild(View child, LayoutParams params, int myWidth, int myHeight) {
    int cws = getChildMeasureSpec(params.mLeft, params.mRight, params.width, params.leftMargin, params.rightMargin, mPaddingLeft, mPaddingRight, myWidth);
    int chs = getChildMeasureSpec(params.mTop, params.mBottom, params.height, params.topMargin, params.bottomMargin, mPaddingTop, mPaddingBottom, myHeight);
    child.measure(cws, chs);
  }

  private void measureChildHorizontal(View child, LayoutParams params, int myWidth, int myHeight) {
    int cws = getChildMeasureSpec(params.mLeft, params.mRight, params.width, params.leftMargin, params.rightMargin, mPaddingLeft, mPaddingRight, myWidth);
    int maxHeight = myHeight;
    int chs;
    if (myHeight < 0 && !mAllowBrokenMeasureSpecs) {
      if (params.height >= 0) chs = MeasureSpec.makeMeasureSpec(params.height, MeasureSpec.EXACTLY);
      else chs = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
    } else if (params.width == LayoutParams.MATCH_PARENT) {
      chs = MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.EXACTLY);
    } else {
      chs = MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.AT_MOST);
    }
    child.measure(cws, chs);
  }

  private int getChildMeasureSpec(int childStart, int childEnd, int childSize, int startMargin, int endMargin, int startPadding, int endPadding, int mySize) {
    if (mySize < 0 && !mAllowBrokenMeasureSpecs) {
      if (childSize >= 0) return MeasureSpec.makeMeasureSpec(childSize, MeasureSpec.EXACTLY);
      return MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
    }
    int childSpecMode = 0, childSpecSize = 0;
    int tempStart = childStart, tempEnd = childEnd;
    if (tempStart < 0) tempStart = startPadding + startMargin;
    if (tempEnd < 0) tempEnd = mySize - endPadding - endMargin;
    int maxAvailable = tempEnd - tempStart;
    if (childStart >= 0 && childEnd >= 0) {
      childSpecMode = MeasureSpec.EXACTLY;
      childSpecSize = maxAvailable;
    } else {
      if (childSize >= 0) {
        childSpecMode = MeasureSpec.EXACTLY;
        if (maxAvailable >= 0) childSpecSize = Math.min(maxAvailable, childSize);
        else childSpecSize = childSize;
      } else if (childSize == LayoutParams.MATCH_PARENT) {
        childSpecMode = MeasureSpec.EXACTLY;
        childSpecSize = maxAvailable;
      } else if (childSize == LayoutParams.WRAP_CONTENT) {
        if (maxAvailable >= 0) { childSpecMode = MeasureSpec.AT_MOST; childSpecSize = maxAvailable; }
        else { childSpecMode = MeasureSpec.UNSPECIFIED; childSpecSize = 0; }
      }
    }
    return MeasureSpec.makeMeasureSpec(childSpecSize, childSpecMode);
  }

  private boolean positionChildHorizontal(View child, LayoutParams params, int myWidth, boolean wrapContent) {
    final int[] rules = params.mRules;
    if (params.mLeft < 0 && params.mRight >= 0) {
      params.mLeft = params.mRight - child.getMeasuredWidth();
    } else if (params.mLeft >= 0 && params.mRight < 0) {
      params.mRight = params.mLeft + child.getMeasuredWidth();
    } else if (params.mLeft < 0 && params.mRight < 0) {
      if (rules[CENTER_IN_PARENT] != 0 || rules[CENTER_HORIZONTAL] != 0) {
        if (!wrapContent) centerHorizontal(child, params, myWidth);
        else { params.mLeft = mPaddingLeft + params.leftMargin; params.mRight = params.mLeft + child.getMeasuredWidth(); }
        return true;
      } else {
        params.mLeft = mPaddingLeft + params.leftMargin;
        params.mRight = params.mLeft + child.getMeasuredWidth();
      }
    }
    return rules[ALIGN_PARENT_RIGHT] != 0;
  }

  private boolean positionChildVertical(View child, LayoutParams params, int myHeight, boolean wrapContent) {
    final int[] rules = params.mRules;
    if (params.mTop < 0 && params.mBottom >= 0) {
      params.mTop = params.mBottom - child.getMeasuredHeight();
    } else if (params.mTop >= 0 && params.mBottom < 0) {
      params.mBottom = params.mTop + child.getMeasuredHeight();
    } else if (params.mTop < 0 && params.mBottom < 0) {
      if (rules[CENTER_IN_PARENT] != 0 || rules[CENTER_VERTICAL] != 0) {
        if (!wrapContent) centerVertical(child, params, myHeight);
        else { params.mTop = mPaddingTop + params.topMargin; params.mBottom = params.mTop + child.getMeasuredHeight(); }
        return true;
      } else {
        params.mTop = mPaddingTop + params.topMargin;
        params.mBottom = params.mTop + child.getMeasuredHeight();
      }
    }
    return rules[ALIGN_PARENT_BOTTOM] != 0;
  }

  private void applyHorizontalSizeRules(LayoutParams childParams, int myWidth, int[] rules) {
    LayoutParams anchorParams;
    childParams.mLeft = -1;
    childParams.mRight = -1;
    anchorParams = getRelatedViewParams(rules, LEFT_OF);
    if (anchorParams != null) childParams.mRight = anchorParams.mLeft - (anchorParams.leftMargin + childParams.rightMargin);
    else if (childParams.alignWithParent && rules[LEFT_OF] != 0) { if (myWidth >= 0) childParams.mRight = myWidth - mPaddingRight - childParams.rightMargin; }
    anchorParams = getRelatedViewParams(rules, RIGHT_OF);
    if (anchorParams != null) childParams.mLeft = anchorParams.mRight + (anchorParams.rightMargin + childParams.leftMargin);
    else if (childParams.alignWithParent && rules[RIGHT_OF] != 0) childParams.mLeft = mPaddingLeft + childParams.leftMargin;
    anchorParams = getRelatedViewParams(rules, ALIGN_LEFT);
    if (anchorParams != null) childParams.mLeft = anchorParams.mLeft + childParams.leftMargin;
    else if (childParams.alignWithParent && rules[ALIGN_LEFT] != 0) childParams.mLeft = mPaddingLeft + childParams.leftMargin;
    anchorParams = getRelatedViewParams(rules, ALIGN_RIGHT);
    if (anchorParams != null) childParams.mRight = anchorParams.mRight - childParams.rightMargin;
    else if (childParams.alignWithParent && rules[ALIGN_RIGHT] != 0) { if (myWidth >= 0) childParams.mRight = myWidth - mPaddingRight - childParams.rightMargin; }
    if (0 != rules[ALIGN_PARENT_LEFT]) childParams.mLeft = mPaddingLeft + childParams.leftMargin;
    if (0 != rules[ALIGN_PARENT_RIGHT]) { if (myWidth >= 0) childParams.mRight = myWidth - mPaddingRight - childParams.rightMargin; }
  }

  private void applyVerticalSizeRules(LayoutParams childParams, int myHeight) {
    final int[] rules = childParams.mRules;
    LayoutParams anchorParams;
    childParams.mTop = -1;
    childParams.mBottom = -1;
    anchorParams = getRelatedViewParams(rules, ABOVE);
    if (anchorParams != null) childParams.mBottom = anchorParams.mTop - (anchorParams.topMargin + childParams.bottomMargin);
    else if (childParams.alignWithParent && rules[ABOVE] != 0) { if (myHeight >= 0) childParams.mBottom = myHeight - mPaddingBottom - childParams.bottomMargin; }
    anchorParams = getRelatedViewParams(rules, BELOW);
    if (anchorParams != null) childParams.mTop = anchorParams.mBottom + (anchorParams.bottomMargin + childParams.topMargin);
    else if (childParams.alignWithParent && rules[BELOW] != 0) childParams.mTop = mPaddingTop + childParams.topMargin;
    anchorParams = getRelatedViewParams(rules, ALIGN_TOP);
    if (anchorParams != null) childParams.mTop = anchorParams.mTop + childParams.topMargin;
    else if (childParams.alignWithParent && rules[ALIGN_TOP] != 0) childParams.mTop = mPaddingTop + childParams.topMargin;
    anchorParams = getRelatedViewParams(rules, ALIGN_BOTTOM);
    if (anchorParams != null) childParams.mBottom = anchorParams.mBottom - childParams.bottomMargin;
    else if (childParams.alignWithParent && rules[ALIGN_BOTTOM] != 0) { if (myHeight >= 0) childParams.mBottom = myHeight - mPaddingBottom - childParams.bottomMargin; }
    if (0 != rules[ALIGN_PARENT_TOP]) childParams.mTop = mPaddingTop + childParams.topMargin;
    if (0 != rules[ALIGN_PARENT_BOTTOM]) { if (myHeight >= 0) childParams.mBottom = myHeight - mPaddingBottom - childParams.bottomMargin; }
    if (rules[ALIGN_BASELINE] != 0) mHasBaselineAlignedChild = true;
  }

  private View getRelatedView(int[] rules, int relation) {
    int id = rules[relation];
    if (id != 0) {
      DependencyGraph.Node node = mGraph.mKeyNodes.get(id);
      if (node == null) return null;
      View v = node.view;
      int guard = 0;
      while (v.getVisibility() == View.GONE) {
        rules = ((LayoutParams) v.getLayoutParams()).mRules;
        node = mGraph.mKeyNodes.get(rules[relation]);
        if (node == null || ++guard > 100) return null;
        v = node.view;
      }
      return v;
    }
    return null;
  }
  private LayoutParams getRelatedViewParams(int[] rules, int relation) {
    View v = getRelatedView(rules, relation);
    if (v != null && v.getLayoutParams() instanceof LayoutParams) return (LayoutParams) v.getLayoutParams();
    return null;
  }
  private int getRelatedViewBaseline(int[] rules, int relation) {
    View v = getRelatedView(rules, relation);
    return v != null ? v.getBaseline() : -1;
  }
  private static void centerHorizontal(View child, LayoutParams params, int myWidth) {
    int childWidth = child.getMeasuredWidth();
    int left = (myWidth - childWidth) / 2;
    params.mLeft = left;
    params.mRight = left + childWidth;
  }
  private static void centerVertical(View child, LayoutParams params, int myHeight) {
    int childHeight = child.getMeasuredHeight();
    int top = (myHeight - childHeight) / 2;
    params.mTop = top;
    params.mBottom = top + childHeight;
  }

  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
    int count = getChildCount();
    for (int i = 0; i < count; i++) {
      View child = getChildAt(i);
      if (child.getVisibility() != GONE) {
        LayoutParams st = (LayoutParams) child.getLayoutParams();
        child.layout(st.mLeft, st.mTop, st.mRight, st.mBottom);
      }
    }
  }

  @Override public LayoutParams generateLayoutParams(AttributeSet a) { return new LayoutParams(getContext(), a); }
  @Override protected ViewGroup.LayoutParams generateDefaultLayoutParams() { return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT); }
  @Override protected boolean checkLayoutParams(ViewGroup.LayoutParams p) { return p instanceof LayoutParams; }
  @Override protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
    return p instanceof MarginLayoutParams ? new LayoutParams((MarginLayoutParams) p) : new LayoutParams(p);
  }

  public static class LayoutParams extends ViewGroup.MarginLayoutParams {
    final int[] mRules = new int[VERB_COUNT];
    int mLeft, mTop, mRight, mBottom;
    public boolean alignWithParent;

    public LayoutParams(Context c, AttributeSet attrs) {
      super(c, attrs);
      if (!(attrs instanceof Attrs)) return;
      Attrs a = (Attrs) attrs;
      String[] at = a.node != null ? a.node.attrs : null;
      ref(a, "layout_toLeftOf", LEFT_OF); ref(a, "layout_toRightOf", RIGHT_OF); ref(a, "layout_above", ABOVE); ref(a, "layout_below", BELOW);
      ref(a, "layout_alignBaseline", ALIGN_BASELINE); ref(a, "layout_alignLeft", ALIGN_LEFT); ref(a, "layout_alignTop", ALIGN_TOP);
      ref(a, "layout_alignRight", ALIGN_RIGHT); ref(a, "layout_alignBottom", ALIGN_BOTTOM);
      ref(a, "layout_toStartOf", LEFT_OF); ref(a, "layout_toEndOf", RIGHT_OF); ref(a, "layout_alignStart", ALIGN_LEFT); ref(a, "layout_alignEnd", ALIGN_RIGHT);
      bool(a, "layout_alignParentLeft", ALIGN_PARENT_LEFT); bool(a, "layout_alignParentTop", ALIGN_PARENT_TOP);
      bool(a, "layout_alignParentRight", ALIGN_PARENT_RIGHT); bool(a, "layout_alignParentBottom", ALIGN_PARENT_BOTTOM);
      bool(a, "layout_alignParentStart", ALIGN_PARENT_LEFT); bool(a, "layout_alignParentEnd", ALIGN_PARENT_RIGHT);
      bool(a, "layout_centerInParent", CENTER_IN_PARENT); bool(a, "layout_centerHorizontal", CENTER_HORIZONTAL); bool(a, "layout_centerVertical", CENTER_VERTICAL);
      alignWithParent = Values.parseBool(a.a("layout_alignWithParentIfMissing"), false);
    }
    private void ref(Attrs a, String n, int verb) { String v = a.a(n); if (v != null) mRules[verb] = Values.refId(v); }
    private void bool(Attrs a, String n, int verb) { String v = a.a(n); if (v != null && v.equals("true")) mRules[verb] = TRUE; }
    public LayoutParams(int w, int h) { super(w, h); }
    public LayoutParams(ViewGroup.LayoutParams s) { super(s); }
    public LayoutParams(MarginLayoutParams s) { super(s); }
    public LayoutParams(LayoutParams s) { super((MarginLayoutParams) s); System.arraycopy(s.mRules, 0, mRules, 0, VERB_COUNT); alignWithParent = s.alignWithParent; }
    public void addRule(int verb) { mRules[verb] = TRUE; }
    public void addRule(int verb, int anchor) { mRules[verb] = anchor; }
    public void removeRule(int verb) { mRules[verb] = 0; }
    public int[] getRules() { return mRules; }
    public int[] getRules(int dir) { return mRules; }
  }

  /** Orders children so that every view is measured after the views it is positioned relative to. */
  private static class DependencyGraph {
    final ArrayList<Node> mNodes = new ArrayList<>();
    final SparseArray<Node> mKeyNodes = new SparseArray<>();
    final ArrayDeque<Node> mRoots = new ArrayDeque<>();
    void clear() { mNodes.clear(); mKeyNodes.clear(); mRoots.clear(); }
    void add(View view) {
      final int id = view.getId();
      final Node node = new Node(view);
      if (id != View.NO_ID) mKeyNodes.put(id, node);
      mNodes.add(node);
    }
    void getSortedViews(View[] sorted, int[] rules) {
      final ArrayDeque<Node> roots = findRoots(rules);
      int index = 0;
      Node node;
      while ((node = roots.pollLast()) != null) {
        final View view = node.view;
        final int key = view.getId();
        sorted[index++] = view;
        for (Node dependent : node.dependents.keySet()) {
          dependent.dependencies.remove(key);
          if (dependent.dependencies.size() == 0) roots.add(dependent);
        }
      }
      if (index < sorted.length) throw new IllegalStateException("Circular dependencies cannot exist in RelativeLayout");
    }
    private ArrayDeque<Node> findRoots(int[] rulesFilter) {
      final int count = mNodes.size();
      for (int i = 0; i < count; i++) { final Node node = mNodes.get(i); node.dependents.clear(); node.dependencies.clear(); }
      for (int i = 0; i < count; i++) {
        final Node node = mNodes.get(i);
        final LayoutParams layoutParams = (LayoutParams) node.view.getLayoutParams();
        final int[] rules = layoutParams.mRules;
        for (int rule : rulesFilter) {
          final int r = rules[rule];
          if (r > 0) {
            final Node dependency = mKeyNodes.get(r);
            if (dependency == null || dependency == node) continue;
            dependency.dependents.put(node, this);
            node.dependencies.put(r, dependency);
          }
        }
      }
      final ArrayDeque<Node> roots = mRoots;
      roots.clear();
      for (int i = 0; i < count; i++) { final Node node = mNodes.get(i); if (node.dependencies.size() == 0) roots.addLast(node); }
      return roots;
    }
    static class Node {
      final View view;
      final java.util.LinkedHashMap<Node, DependencyGraph> dependents = new java.util.LinkedHashMap<>();
      final SparseArray<Node> dependencies = new SparseArray<>();
      Node(View v) { view = v; }
    }
  }
}
