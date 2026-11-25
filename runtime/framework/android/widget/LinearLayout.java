package android.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import a2w.Attrs;
import a2w.Values;

/** LinearLayout after the Android 4.x implementation (weights, baselines, gravity). */
public class LinearLayout extends ViewGroup {
  public static final int HORIZONTAL = 0, VERTICAL = 1;
  public static final int SHOW_DIVIDER_NONE = 0, SHOW_DIVIDER_BEGINNING = 1, SHOW_DIVIDER_MIDDLE = 2, SHOW_DIVIDER_END = 4;
  static final int VERTICAL_GRAVITY_COUNT = 4, INDEX_CENTER_VERTICAL = 0, INDEX_TOP = 1, INDEX_BOTTOM = 2, INDEX_FILL = 3;

  boolean mBaselineAligned = true;
  int mBaselineAlignedChildIndex = -1;
  int mBaselineChildTop;
  int mOrientation;
  int mGravity = Gravity.LEFT | Gravity.TOP;
  int mTotalLength;
  float mWeightSum = -1;
  boolean mUseLargestChild;
  int[] mMaxAscent, mMaxDescent;
  Drawable mDivider;
  int mDividerWidth, mDividerHeight, mShowDividers, mDividerPadding;

  public LinearLayout(Context c) { super(c); }
  public LinearLayout(Context c, AttributeSet a) { this(c, a, 0); }
  public LinearLayout(Context c, AttributeSet a, int d) {
    super(c, a, d);
    if (a instanceof Attrs) {
      Attrs at = (Attrs) a;
      String v;
      if ((v = at.a("orientation")) != null) mOrientation = v.equals("vertical") || v.equals("1") ? VERTICAL : HORIZONTAL;
      if ((v = at.a("gravity")) != null) setGravity(Values.gravity(v));
      if ((v = at.a("baselineAligned")) != null) mBaselineAligned = v.equals("true");
      if ((v = at.a("weightSum")) != null) mWeightSum = Values.parseFloat(v, -1);
      if ((v = at.a("baselineAlignedChildIndex")) != null) mBaselineAlignedChildIndex = Values.parseInt(v, -1);
      if ((v = at.a("measureWithLargestChild")) != null) mUseLargestChild = v.equals("true");
      if ((v = at.a("divider")) != null && Values.isRef(v)) setDividerDrawable(getResources().getDrawable(Values.refId(v)));
      if ((v = at.a("showDividers")) != null) { int s = 0; if (v.contains("beginning")) s |= 1; if (v.contains("middle")) s |= 2; if (v.contains("end")) s |= 4; mShowDividers = s; }
      if ((v = at.a("dividerPadding")) != null) mDividerPadding = (int) dim(v);
    }
  }
  public void setShowDividers(int s) { mShowDividers = s; requestLayout(); }
  public int getShowDividers() { return mShowDividers; }
  public Drawable getDividerDrawable() { return mDivider; }
  public void setDividerDrawable(Drawable d) {
    mDivider = d;
    if (d != null) { mDividerWidth = d.getIntrinsicWidth(); mDividerHeight = d.getIntrinsicHeight(); } else { mDividerWidth = mDividerHeight = 0; }
    setWillNotDraw(d == null);
    requestLayout();
  }
  public void setDividerPadding(int p) { mDividerPadding = p; }
  public int getDividerPadding() { return mDividerPadding; }
  public boolean isBaselineAligned() { return mBaselineAligned; }
  public void setBaselineAligned(boolean b) { mBaselineAligned = b; }
  public boolean isMeasureWithLargestChildEnabled() { return mUseLargestChild; }
  public void setMeasureWithLargestChildEnabled(boolean e) { mUseLargestChild = e; }
  public int getBaselineAlignedChildIndex() { return mBaselineAlignedChildIndex; }
  public void setBaselineAlignedChildIndex(int i) { mBaselineAlignedChildIndex = i; }
  public float getWeightSum() { return mWeightSum; }
  public void setWeightSum(float w) { mWeightSum = Math.max(0.0f, w); }
  public void setOrientation(int o) { if (mOrientation != o) { mOrientation = o; requestLayout(); } }
  public int getOrientation() { return mOrientation; }
  public void setGravity(int g) {
    if (mGravity != g) {
      if ((g & Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK) == 0) g |= Gravity.LEFT;
      if ((g & Gravity.VERTICAL_GRAVITY_MASK) == 0) g |= Gravity.TOP;
      mGravity = g;
      requestLayout();
    }
  }
  public int getGravity() { return mGravity; }
  public void setHorizontalGravity(int h) { int g = h & Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK; if ((mGravity & Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK) != g) { mGravity = (mGravity & ~Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK) | g; requestLayout(); } }
  public void setVerticalGravity(int v) { int g = v & Gravity.VERTICAL_GRAVITY_MASK; if ((mGravity & Gravity.VERTICAL_GRAVITY_MASK) != g) { mGravity = (mGravity & ~Gravity.VERTICAL_GRAVITY_MASK) | g; requestLayout(); } }
  @Override public boolean shouldDelayChildPressedState() { return false; }

  @Override public int getBaseline() {
    if (mBaselineAlignedChildIndex < 0) return super.getBaseline();
    if (getChildCount() <= mBaselineAlignedChildIndex) throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
    final View child = getChildAt(mBaselineAlignedChildIndex);
    final int childBaseline = child.getBaseline();
    if (childBaseline == -1) {
      if (mBaselineAlignedChildIndex == 0) return -1;
      throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
    }
    int childTop = mBaselineChildTop;
    if (mOrientation == VERTICAL) {
      final int majorGravity = mGravity & Gravity.VERTICAL_GRAVITY_MASK;
      if (majorGravity != Gravity.TOP) {
        switch (majorGravity) {
          case Gravity.BOTTOM: childTop = mBottom - mTop - mPaddingBottom - mTotalLength; break;
          case Gravity.CENTER_VERTICAL: childTop += ((mBottom - mTop - mPaddingTop - mPaddingBottom) - mTotalLength) / 2; break;
        }
      }
    }
    LayoutParams lp = (LayoutParams) child.getLayoutParams();
    return childTop + lp.topMargin + childBaseline;
  }

  View getVirtualChildAt(int i) { return getChildAt(i); }
  int getVirtualChildCount() { return getChildCount(); }
  int measureNullChild(int i) { return 0; }
  int getChildrenSkipCount(View c, int i) { return 0; }
  int getLocationOffset(View c) { return 0; }
  int getNextLocationOffset(View c) { return 0; }
  void measureChildBeforeLayout(View child, int index, int ws, int totalWidth, int hs, int totalHeight) { measureChildWithMargins(child, ws, totalWidth, hs, totalHeight); }
  protected boolean hasDividerBeforeChildAt(int i) {
    if (i == 0) return (mShowDividers & SHOW_DIVIDER_BEGINNING) != 0;
    else if (i == getChildCount()) return (mShowDividers & SHOW_DIVIDER_END) != 0;
    else if ((mShowDividers & SHOW_DIVIDER_MIDDLE) != 0) {
      for (int k = i - 1; k >= 0; k--) if (getChildAt(k).getVisibility() != GONE) return true;
    }
    return false;
  }

  @Override protected void onMeasure(int ws, int hs) { if (mOrientation == VERTICAL) measureVertical(ws, hs); else measureHorizontal(ws, hs); }

  void measureVertical(int widthMeasureSpec, int heightMeasureSpec) {
    mTotalLength = 0;
    int maxWidth = 0, childState = 0, alternativeMaxWidth = 0, weightedMaxWidth = 0;
    boolean allFillParent = true;
    float totalWeight = 0;
    final int count = getVirtualChildCount();
    final int widthMode = MeasureSpec.getMode(widthMeasureSpec), heightMode = MeasureSpec.getMode(heightMeasureSpec);
    boolean matchWidth = false;
    final int baselineChildIndex = mBaselineAlignedChildIndex;
    final boolean useLargestChild = mUseLargestChild;
    int largestChildHeight = Integer.MIN_VALUE;
    for (int i = 0; i < count; ++i) {
      final View child = getVirtualChildAt(i);
      if (child == null) { mTotalLength += measureNullChild(i); continue; }
      if (child.getVisibility() == View.GONE) { i += getChildrenSkipCount(child, i); continue; }
      if (hasDividerBeforeChildAt(i)) mTotalLength += mDividerHeight;
      LayoutParams lp = (LayoutParams) child.getLayoutParams();
      totalWeight += lp.weight;
      if (heightMode == MeasureSpec.EXACTLY && lp.height == 0 && lp.weight > 0) {
        final int totalLength = mTotalLength;
        mTotalLength = Math.max(totalLength, totalLength + lp.topMargin + lp.bottomMargin);
      } else {
        int oldHeight = Integer.MIN_VALUE;
        if (lp.height == 0 && lp.weight > 0) { oldHeight = 0; lp.height = LayoutParams.WRAP_CONTENT; }
        measureChildBeforeLayout(child, i, widthMeasureSpec, 0, heightMeasureSpec, totalWeight == 0 ? mTotalLength : 0);
        if (oldHeight != Integer.MIN_VALUE) lp.height = oldHeight;
        final int childHeight = child.getMeasuredHeight();
        final int totalLength = mTotalLength;
        mTotalLength = Math.max(totalLength, totalLength + childHeight + lp.topMargin + lp.bottomMargin + getNextLocationOffset(child));
        if (useLargestChild) largestChildHeight = Math.max(childHeight, largestChildHeight);
      }
      if ((baselineChildIndex >= 0) && (baselineChildIndex == i + 1)) mBaselineChildTop = mTotalLength;
      if (i < baselineChildIndex && lp.weight > 0) throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.");
      boolean matchWidthLocally = false;
      if (widthMode != MeasureSpec.EXACTLY && lp.width == LayoutParams.MATCH_PARENT) { matchWidth = true; matchWidthLocally = true; }
      final int margin = lp.leftMargin + lp.rightMargin;
      final int measuredWidth = child.getMeasuredWidth() + margin;
      maxWidth = Math.max(maxWidth, measuredWidth);
      childState = combineMeasuredStates(childState, child.getMeasuredState());
      allFillParent = allFillParent && lp.width == LayoutParams.MATCH_PARENT;
      if (lp.weight > 0) weightedMaxWidth = Math.max(weightedMaxWidth, matchWidthLocally ? margin : measuredWidth);
      else alternativeMaxWidth = Math.max(alternativeMaxWidth, matchWidthLocally ? margin : measuredWidth);
      i += getChildrenSkipCount(child, i);
    }
    if (mTotalLength > 0 && hasDividerBeforeChildAt(count)) mTotalLength += mDividerHeight;
    if (useLargestChild && (heightMode == MeasureSpec.AT_MOST || heightMode == MeasureSpec.UNSPECIFIED)) {
      mTotalLength = 0;
      for (int i = 0; i < count; ++i) {
        final View child = getVirtualChildAt(i);
        if (child == null) { mTotalLength += measureNullChild(i); continue; }
        if (child.getVisibility() == GONE) { i += getChildrenSkipCount(child, i); continue; }
        final LayoutParams lp = (LayoutParams) child.getLayoutParams();
        final int totalLength = mTotalLength;
        mTotalLength = Math.max(totalLength, totalLength + largestChildHeight + lp.topMargin + lp.bottomMargin + getNextLocationOffset(child));
      }
    }
    mTotalLength += mPaddingTop + mPaddingBottom;
    int heightSize = mTotalLength;
    heightSize = Math.max(heightSize, getSuggestedMinimumHeight());
    int heightSizeAndState = resolveSizeAndState(heightSize, heightMeasureSpec, 0);
    heightSize = heightSizeAndState & MEASURED_SIZE_MASK;
    int delta = heightSize - mTotalLength;
    if (delta != 0 && totalWeight > 0.0f) {
      float weightSum = mWeightSum > 0.0f ? mWeightSum : totalWeight;
      mTotalLength = 0;
      for (int i = 0; i < count; ++i) {
        final View child = getVirtualChildAt(i);
        if (child.getVisibility() == View.GONE) continue;
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        float childExtra = lp.weight;
        if (childExtra > 0) {
          int share = (int) (childExtra * delta / weightSum);
          weightSum -= childExtra;
          delta -= share;
          final int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, mPaddingLeft + mPaddingRight + lp.leftMargin + lp.rightMargin, lp.width);
          if ((lp.height != 0) || (heightMode != MeasureSpec.EXACTLY)) {
            int childHeight = child.getMeasuredHeight() + share;
            if (childHeight < 0) childHeight = 0;
            child.measure(childWidthMeasureSpec, MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY));
          } else {
            child.measure(childWidthMeasureSpec, MeasureSpec.makeMeasureSpec(share > 0 ? share : 0, MeasureSpec.EXACTLY));
          }
          childState = combineMeasuredStates(childState, child.getMeasuredState() & (MEASURED_STATE_MASK >> MEASURED_HEIGHT_STATE_SHIFT));
        }
        final int margin = lp.leftMargin + lp.rightMargin;
        final int measuredWidth = child.getMeasuredWidth() + margin;
        maxWidth = Math.max(maxWidth, measuredWidth);
        boolean matchWidthLocally = widthMode != MeasureSpec.EXACTLY && lp.width == LayoutParams.MATCH_PARENT;
        alternativeMaxWidth = Math.max(alternativeMaxWidth, matchWidthLocally ? margin : measuredWidth);
        allFillParent = allFillParent && lp.width == LayoutParams.MATCH_PARENT;
        final int totalLength = mTotalLength;
        mTotalLength = Math.max(totalLength, totalLength + child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin + getNextLocationOffset(child));
      }
      mTotalLength += mPaddingTop + mPaddingBottom;
    } else {
      alternativeMaxWidth = Math.max(alternativeMaxWidth, weightedMaxWidth);
      if (useLargestChild && heightMode != MeasureSpec.EXACTLY) {
        for (int i = 0; i < count; i++) {
          final View child = getVirtualChildAt(i);
          if (child == null || child.getVisibility() == View.GONE) continue;
          final LayoutParams lp = (LayoutParams) child.getLayoutParams();
          if (lp.weight > 0) child.measure(MeasureSpec.makeMeasureSpec(child.getMeasuredWidth(), MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(largestChildHeight, MeasureSpec.EXACTLY));
        }
      }
    }
    if (!allFillParent && widthMode != MeasureSpec.EXACTLY) maxWidth = alternativeMaxWidth;
    maxWidth += mPaddingLeft + mPaddingRight;
    maxWidth = Math.max(maxWidth, getSuggestedMinimumWidth());
    setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, childState), heightSizeAndState);
    if (matchWidth) forceUniformWidth(count, heightMeasureSpec);
  }

  private void forceUniformWidth(int count, int heightMeasureSpec) {
    int uniformMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), MeasureSpec.EXACTLY);
    for (int i = 0; i < count; ++i) {
      final View child = getVirtualChildAt(i);
      if (child.getVisibility() != GONE) {
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        if (lp.width == LayoutParams.MATCH_PARENT) {
          int oldHeight = lp.height;
          lp.height = child.getMeasuredHeight();
          measureChildWithMargins(child, uniformMeasureSpec, 0, heightMeasureSpec, 0);
          lp.height = oldHeight;
        }
      }
    }
  }

  void measureHorizontal(int widthMeasureSpec, int heightMeasureSpec) {
    mTotalLength = 0;
    int maxHeight = 0, childState = 0, alternativeMaxHeight = 0, weightedMaxHeight = 0;
    boolean allFillParent = true;
    float totalWeight = 0;
    final int count = getVirtualChildCount();
    final int widthMode = MeasureSpec.getMode(widthMeasureSpec), heightMode = MeasureSpec.getMode(heightMeasureSpec);
    boolean matchHeight = false;
    if (mMaxAscent == null || mMaxDescent == null) { mMaxAscent = new int[VERTICAL_GRAVITY_COUNT]; mMaxDescent = new int[VERTICAL_GRAVITY_COUNT]; }
    final int[] maxAscent = mMaxAscent, maxDescent = mMaxDescent;
    maxAscent[0] = maxAscent[1] = maxAscent[2] = maxAscent[3] = -1;
    maxDescent[0] = maxDescent[1] = maxDescent[2] = maxDescent[3] = -1;
    final boolean baselineAligned = mBaselineAligned, useLargestChild = mUseLargestChild;
    final boolean isExactly = widthMode == MeasureSpec.EXACTLY;
    int largestChildWidth = Integer.MIN_VALUE;
    for (int i = 0; i < count; ++i) {
      final View child = getVirtualChildAt(i);
      if (child == null) { mTotalLength += measureNullChild(i); continue; }
      if (child.getVisibility() == GONE) { i += getChildrenSkipCount(child, i); continue; }
      if (hasDividerBeforeChildAt(i)) mTotalLength += mDividerWidth;
      final LayoutParams lp = (LayoutParams) child.getLayoutParams();
      totalWeight += lp.weight;
      if (widthMode == MeasureSpec.EXACTLY && lp.width == 0 && lp.weight > 0) {
        if (isExactly) mTotalLength += lp.leftMargin + lp.rightMargin;
        else { final int totalLength = mTotalLength; mTotalLength = Math.max(totalLength, totalLength + lp.leftMargin + lp.rightMargin); }
        if (baselineAligned) {
          final int freeSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
          child.measure(freeSpec, freeSpec);
        }
      } else {
        int oldWidth = Integer.MIN_VALUE;
        if (lp.width == 0 && lp.weight > 0) { oldWidth = 0; lp.width = LayoutParams.WRAP_CONTENT; }
        measureChildBeforeLayout(child, i, widthMeasureSpec, totalWeight == 0 ? mTotalLength : 0, heightMeasureSpec, 0);
        if (oldWidth != Integer.MIN_VALUE) lp.width = oldWidth;
        final int childWidth = child.getMeasuredWidth();
        if (isExactly) mTotalLength += childWidth + lp.leftMargin + lp.rightMargin + getNextLocationOffset(child);
        else { final int totalLength = mTotalLength; mTotalLength = Math.max(totalLength, totalLength + childWidth + lp.leftMargin + lp.rightMargin + getNextLocationOffset(child)); }
        if (useLargestChild) largestChildWidth = Math.max(childWidth, largestChildWidth);
      }
      boolean matchHeightLocally = false;
      if (heightMode != MeasureSpec.EXACTLY && lp.height == LayoutParams.MATCH_PARENT) { matchHeight = true; matchHeightLocally = true; }
      final int margin = lp.topMargin + lp.bottomMargin;
      final int childHeight = child.getMeasuredHeight() + margin;
      childState = combineMeasuredStates(childState, child.getMeasuredState());
      if (baselineAligned) {
        final int childBaseline = child.getBaseline();
        if (childBaseline != -1) {
          final int gravity = (lp.gravity < 0 ? mGravity : lp.gravity) & Gravity.VERTICAL_GRAVITY_MASK;
          final int index = ((gravity >> Gravity.AXIS_Y_SHIFT) & ~Gravity.AXIS_SPECIFIED) >> 1;
          maxAscent[index] = Math.max(maxAscent[index], childBaseline);
          maxDescent[index] = Math.max(maxDescent[index], childHeight - childBaseline);
        }
      }
      maxHeight = Math.max(maxHeight, childHeight);
      allFillParent = allFillParent && lp.height == LayoutParams.MATCH_PARENT;
      if (lp.weight > 0) weightedMaxHeight = Math.max(weightedMaxHeight, matchHeightLocally ? margin : childHeight);
      else alternativeMaxHeight = Math.max(alternativeMaxHeight, matchHeightLocally ? margin : childHeight);
      i += getChildrenSkipCount(child, i);
    }
    if (mTotalLength > 0 && hasDividerBeforeChildAt(count)) mTotalLength += mDividerWidth;
    if (maxAscent[INDEX_TOP] != -1 || maxAscent[INDEX_CENTER_VERTICAL] != -1 || maxAscent[INDEX_BOTTOM] != -1 || maxAscent[INDEX_FILL] != -1) {
      final int ascent = Math.max(maxAscent[INDEX_FILL], Math.max(maxAscent[INDEX_CENTER_VERTICAL], Math.max(maxAscent[INDEX_TOP], maxAscent[INDEX_BOTTOM])));
      final int descent = Math.max(maxDescent[INDEX_FILL], Math.max(maxDescent[INDEX_CENTER_VERTICAL], Math.max(maxDescent[INDEX_TOP], maxDescent[INDEX_BOTTOM])));
      maxHeight = Math.max(maxHeight, ascent + descent);
    }
    if (useLargestChild && (widthMode == MeasureSpec.AT_MOST || widthMode == MeasureSpec.UNSPECIFIED)) {
      mTotalLength = 0;
      for (int i = 0; i < count; ++i) {
        final View child = getVirtualChildAt(i);
        if (child == null) { mTotalLength += measureNullChild(i); continue; }
        if (child.getVisibility() == GONE) { i += getChildrenSkipCount(child, i); continue; }
        final LayoutParams lp = (LayoutParams) child.getLayoutParams();
        if (isExactly) mTotalLength += largestChildWidth + lp.leftMargin + lp.rightMargin + getNextLocationOffset(child);
        else { final int totalLength = mTotalLength; mTotalLength = Math.max(totalLength, totalLength + largestChildWidth + lp.leftMargin + lp.rightMargin + getNextLocationOffset(child)); }
      }
    }
    mTotalLength += mPaddingLeft + mPaddingRight;
    int widthSize = mTotalLength;
    widthSize = Math.max(widthSize, getSuggestedMinimumWidth());
    int widthSizeAndState = resolveSizeAndState(widthSize, widthMeasureSpec, 0);
    widthSize = widthSizeAndState & MEASURED_SIZE_MASK;
    int delta = widthSize - mTotalLength;
    if (delta != 0 && totalWeight > 0.0f) {
      float weightSum = mWeightSum > 0.0f ? mWeightSum : totalWeight;
      maxAscent[0] = maxAscent[1] = maxAscent[2] = maxAscent[3] = -1;
      maxDescent[0] = maxDescent[1] = maxDescent[2] = maxDescent[3] = -1;
      maxHeight = -1;
      mTotalLength = 0;
      for (int i = 0; i < count; ++i) {
        final View child = getVirtualChildAt(i);
        if (child == null || child.getVisibility() == View.GONE) continue;
        final LayoutParams lp = (LayoutParams) child.getLayoutParams();
        float childExtra = lp.weight;
        if (childExtra > 0) {
          int share = (int) (childExtra * delta / weightSum);
          weightSum -= childExtra;
          delta -= share;
          final int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, mPaddingTop + mPaddingBottom + lp.topMargin + lp.bottomMargin, lp.height);
          if ((lp.width != 0) || (widthMode != MeasureSpec.EXACTLY)) {
            int childWidth = child.getMeasuredWidth() + share;
            if (childWidth < 0) childWidth = 0;
            child.measure(MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY), childHeightMeasureSpec);
          } else {
            child.measure(MeasureSpec.makeMeasureSpec(share > 0 ? share : 0, MeasureSpec.EXACTLY), childHeightMeasureSpec);
          }
          childState = combineMeasuredStates(childState, child.getMeasuredState() & MEASURED_STATE_MASK);
        }
        if (isExactly) mTotalLength += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin + getNextLocationOffset(child);
        else { final int totalLength = mTotalLength; mTotalLength = Math.max(totalLength, totalLength + child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin + getNextLocationOffset(child)); }
        boolean matchHeightLocally = heightMode != MeasureSpec.EXACTLY && lp.height == LayoutParams.MATCH_PARENT;
        final int margin = lp.topMargin + lp.bottomMargin;
        int childHeight = child.getMeasuredHeight() + margin;
        maxHeight = Math.max(maxHeight, childHeight);
        alternativeMaxHeight = Math.max(alternativeMaxHeight, matchHeightLocally ? margin : childHeight);
        allFillParent = allFillParent && lp.height == LayoutParams.MATCH_PARENT;
        if (baselineAligned) {
          final int childBaseline = child.getBaseline();
          if (childBaseline != -1) {
            final int gravity = (lp.gravity < 0 ? mGravity : lp.gravity) & Gravity.VERTICAL_GRAVITY_MASK;
            final int index = ((gravity >> Gravity.AXIS_Y_SHIFT) & ~Gravity.AXIS_SPECIFIED) >> 1;
            maxAscent[index] = Math.max(maxAscent[index], childBaseline);
            maxDescent[index] = Math.max(maxDescent[index], childHeight - childBaseline);
          }
        }
      }
      mTotalLength += mPaddingLeft + mPaddingRight;
      if (maxAscent[INDEX_TOP] != -1 || maxAscent[INDEX_CENTER_VERTICAL] != -1 || maxAscent[INDEX_BOTTOM] != -1 || maxAscent[INDEX_FILL] != -1) {
        final int ascent = Math.max(maxAscent[INDEX_FILL], Math.max(maxAscent[INDEX_CENTER_VERTICAL], Math.max(maxAscent[INDEX_TOP], maxAscent[INDEX_BOTTOM])));
        final int descent = Math.max(maxDescent[INDEX_FILL], Math.max(maxDescent[INDEX_CENTER_VERTICAL], Math.max(maxDescent[INDEX_TOP], maxDescent[INDEX_BOTTOM])));
        maxHeight = Math.max(maxHeight, ascent + descent);
      }
    } else {
      alternativeMaxHeight = Math.max(alternativeMaxHeight, weightedMaxHeight);
      if (useLargestChild && widthMode != MeasureSpec.EXACTLY) {
        for (int i = 0; i < count; i++) {
          final View child = getVirtualChildAt(i);
          if (child == null || child.getVisibility() == View.GONE) continue;
          final LayoutParams lp = (LayoutParams) child.getLayoutParams();
          if (lp.weight > 0) child.measure(MeasureSpec.makeMeasureSpec(largestChildWidth, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(child.getMeasuredHeight(), MeasureSpec.EXACTLY));
        }
      }
    }
    if (!allFillParent && heightMode != MeasureSpec.EXACTLY) maxHeight = alternativeMaxHeight;
    maxHeight += mPaddingTop + mPaddingBottom;
    maxHeight = Math.max(maxHeight, getSuggestedMinimumHeight());
    setMeasuredDimension(widthSizeAndState | (childState & MEASURED_STATE_MASK), resolveSizeAndState(maxHeight, heightMeasureSpec, (childState << MEASURED_HEIGHT_STATE_SHIFT)));
    if (matchHeight) forceUniformHeight(count, widthMeasureSpec);
  }

  private void forceUniformHeight(int count, int widthMeasureSpec) {
    int uniformMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredHeight(), MeasureSpec.EXACTLY);
    for (int i = 0; i < count; ++i) {
      final View child = getVirtualChildAt(i);
      if (child.getVisibility() != GONE) {
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        if (lp.height == LayoutParams.MATCH_PARENT) {
          int oldWidth = lp.width;
          lp.width = child.getMeasuredWidth();
          measureChildWithMargins(child, widthMeasureSpec, 0, uniformMeasureSpec, 0);
          lp.width = oldWidth;
        }
      }
    }
  }

  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) { if (mOrientation == VERTICAL) layoutVertical(); else layoutHorizontal(); }

  void layoutVertical() {
    final int paddingLeft = mPaddingLeft;
    int childTop;
    int childLeft;
    final int width = mRight - mLeft;
    int childRight = width - mPaddingRight;
    int childSpace = width - paddingLeft - mPaddingRight;
    final int count = getVirtualChildCount();
    final int majorGravity = mGravity & Gravity.VERTICAL_GRAVITY_MASK;
    final int minorGravity = mGravity & Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK;
    switch (majorGravity) {
      case Gravity.BOTTOM: childTop = mPaddingTop + mBottom - mTop - mTotalLength; break;
      case Gravity.CENTER_VERTICAL: childTop = mPaddingTop + (mBottom - mTop - mTotalLength) / 2; break;
      default: childTop = mPaddingTop; break;
    }
    for (int i = 0; i < count; i++) {
      final View child = getVirtualChildAt(i);
      if (child == null) childTop += measureNullChild(i);
      else if (child.getVisibility() != GONE) {
        final int childWidth = child.getMeasuredWidth(), childHeight = child.getMeasuredHeight();
        final LayoutParams lp = (LayoutParams) child.getLayoutParams();
        int gravity = lp.gravity;
        if (gravity < 0) gravity = minorGravity;
        final int absoluteGravity = Gravity.getAbsoluteGravity(gravity, 0);
        switch (absoluteGravity & Gravity.HORIZONTAL_GRAVITY_MASK) {
          case Gravity.CENTER_HORIZONTAL: childLeft = paddingLeft + ((childSpace - childWidth) / 2) + lp.leftMargin - lp.rightMargin; break;
          case Gravity.RIGHT: childLeft = childRight - childWidth - lp.rightMargin; break;
          default: childLeft = paddingLeft + lp.leftMargin; break;
        }
        if (hasDividerBeforeChildAt(i)) childTop += mDividerHeight;
        childTop += lp.topMargin;
        child.layout(childLeft, childTop + getLocationOffset(child), childLeft + childWidth, childTop + getLocationOffset(child) + childHeight);
        childTop += childHeight + lp.bottomMargin + getNextLocationOffset(child);
        i += getChildrenSkipCount(child, i);
      }
    }
  }

  void layoutHorizontal() {
    final int paddingTop = mPaddingTop;
    int childTop;
    int childLeft;
    final int height = mBottom - mTop;
    int childBottom = height - mPaddingBottom;
    int childSpace = height - paddingTop - mPaddingBottom;
    final int count = getVirtualChildCount();
    final int majorGravity = mGravity & Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK;
    final int minorGravity = mGravity & Gravity.VERTICAL_GRAVITY_MASK;
    final boolean baselineAligned = mBaselineAligned;
    final int[] maxAscent = mMaxAscent, maxDescent = mMaxDescent;
    switch (Gravity.getAbsoluteGravity(majorGravity, 0)) {
      case Gravity.RIGHT: childLeft = mPaddingLeft + mRight - mLeft - mTotalLength; break;
      case Gravity.CENTER_HORIZONTAL: childLeft = mPaddingLeft + (mRight - mLeft - mTotalLength) / 2; break;
      default: childLeft = mPaddingLeft; break;
    }
    for (int i = 0; i < count; i++) {
      final View child = getVirtualChildAt(i);
      if (child == null) childLeft += measureNullChild(i);
      else if (child.getVisibility() != GONE) {
        final int childWidth = child.getMeasuredWidth(), childHeight = child.getMeasuredHeight();
        int childBaseline = -1;
        final LayoutParams lp = (LayoutParams) child.getLayoutParams();
        if (baselineAligned && lp.height != LayoutParams.MATCH_PARENT) childBaseline = child.getBaseline();
        int gravity = lp.gravity;
        if (gravity < 0) gravity = minorGravity;
        switch (gravity & Gravity.VERTICAL_GRAVITY_MASK) {
          case Gravity.TOP:
            childTop = paddingTop + lp.topMargin;
            if (childBaseline != -1 && maxAscent != null) childTop += maxAscent[INDEX_TOP] - childBaseline;
            break;
          case Gravity.CENTER_VERTICAL:
            childTop = paddingTop + ((childSpace - childHeight) / 2) + lp.topMargin - lp.bottomMargin;
            break;
          case Gravity.BOTTOM:
            childTop = childBottom - childHeight - lp.bottomMargin;
            if (childBaseline != -1 && maxDescent != null) {
              int descent = child.getMeasuredHeight() - childBaseline;
              childTop -= (maxDescent[INDEX_BOTTOM] - descent);
            }
            break;
          default: childTop = paddingTop; break;
        }
        if (hasDividerBeforeChildAt(i)) childLeft += mDividerWidth;
        childLeft += lp.leftMargin;
        child.layout(childLeft + getLocationOffset(child), childTop, childLeft + getLocationOffset(child) + childWidth, childTop + childHeight);
        childLeft += childWidth + lp.rightMargin + getNextLocationOffset(child);
        i += getChildrenSkipCount(child, i);
      }
    }
  }

  @Override protected void onDraw(Canvas canvas) {
    if (mDivider == null) return;
    final int count = getVirtualChildCount();
    for (int i = 0; i < count; i++) {
      final View child = getVirtualChildAt(i);
      if (child != null && child.getVisibility() != GONE && hasDividerBeforeChildAt(i)) {
        final LayoutParams lp = (LayoutParams) child.getLayoutParams();
        if (mOrientation == VERTICAL) {
          int top = child.getTop() - lp.topMargin - mDividerHeight;
          mDivider.setBounds(getPaddingLeft() + mDividerPadding, top, getWidth() - getPaddingRight() - mDividerPadding, top + mDividerHeight);
        } else {
          int left = child.getLeft() - lp.leftMargin - mDividerWidth;
          mDivider.setBounds(left, getPaddingTop() + mDividerPadding, left + mDividerWidth, getHeight() - getPaddingBottom() - mDividerPadding);
        }
        mDivider.draw(canvas);
      }
    }
  }

  @Override public LayoutParams generateLayoutParams(AttributeSet a) { return new LayoutParams(getContext(), a); }
  @Override protected LayoutParams generateDefaultLayoutParams() {
    if (mOrientation == HORIZONTAL) return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    else return new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
  }
  @Override protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
    return p instanceof MarginLayoutParams ? new LayoutParams((MarginLayoutParams) p) : new LayoutParams(p);
  }
  @Override protected boolean checkLayoutParams(ViewGroup.LayoutParams p) { return p instanceof LayoutParams; }

  public static class LayoutParams extends ViewGroup.MarginLayoutParams {
    public float weight;
    public int gravity = -1;
    public LayoutParams(Context c, AttributeSet attrs) {
      super(c, attrs);
      if (attrs instanceof Attrs) {
        Attrs a = (Attrs) attrs;
        weight = Values.parseFloat(a.a("layout_weight"), 0);
        String g = a.a("layout_gravity");
        if (g != null) gravity = Values.gravity(g);
      }
    }
    public LayoutParams(int w, int h) { super(w, h); weight = 0; }
    public LayoutParams(int w, int h, float weight) { super(w, h); this.weight = weight; }
    public LayoutParams(ViewGroup.LayoutParams p) { super(p); }
    public LayoutParams(MarginLayoutParams s) { super(s); }
    public LayoutParams(LayoutParams s) { super((MarginLayoutParams) s); weight = s.weight; gravity = s.gravity; }
  }
}
