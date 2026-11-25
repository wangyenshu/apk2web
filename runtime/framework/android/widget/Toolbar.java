package android.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import a2w.Attrs;
import a2w.Values;

/**
 * A bar with a navigation button, a logo, a title and subtitle, custom views and an action menu, laid out as the
 * platform's Toolbar (content insets, title margins, collapsing margins, custom views by gravity). It can be an
 * activity's action bar (Activity.setActionBar).
 */
public class Toolbar extends ViewGroup {
  public interface OnMenuItemClickListener { boolean onMenuItemClick(MenuItem item); }

  private ActionMenuView mMenuView;
  private TextView mTitleTextView, mSubtitleTextView;
  private ImageButton mNavButtonView, mCollapseButtonView;
  private ImageView mLogoView;
  private Drawable mCollapseIcon;
  private CharSequence mCollapseDescription;
  View mExpandedActionView;
  private MenuItem mExpandedItem;
  private int mPopupTheme, mTitleTextAppearance, mSubtitleTextAppearance;
  private int mButtonGravity, mMaxButtonHeight = -1;
  private int mTitleMarginStart, mTitleMarginEnd, mTitleMarginTop, mTitleMarginBottom;
  private int mContentInsetStart, mContentInsetEnd, mContentInsetLeft = 0, mContentInsetRight = 0;
  private boolean mExplicitLeft, mExplicitRight;
  private int mContentInsetStartWithNavigation, mContentInsetEndWithActions;
  private int mGravity = Gravity.START | Gravity.CENTER_VERTICAL;
  private CharSequence mTitleText, mSubtitleText;
  private ColorStateList mTitleTextColor, mSubtitleTextColor;
  private OnMenuItemClickListener mOnMenuItemClickListener;
  private final int[] mTempMargins = new int[2];
  private final java.util.ArrayList<View> mTempViews = new java.util.ArrayList<>();
  private final java.util.ArrayList<View> mHiddenViews = new java.util.ArrayList<>();

  public Toolbar(Context context) { this(context, null); }
  public Toolbar(Context context, AttributeSet attrs) { this(context, attrs, android.R.attr.toolbarStyle); }
  public Toolbar(Context context, AttributeSet attrs, int defStyleAttr) { this(context, attrs, defStyleAttr, 0); }
  public Toolbar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr);
    float d = getResources().getDisplayMetrics().density;
    mTitleMarginStart = mTitleMarginEnd = mTitleMarginTop = mTitleMarginBottom = Math.round(4 * d);
    mContentInsetStart = Math.round(16 * d);
    mContentInsetStartWithNavigation = Math.round(72 * d);
    mMaxButtonHeight = Math.round(56 * d);
    mButtonGravity = Gravity.TOP;
    if (getMinimumHeight() == 0) setMinimumHeight(actionBarSize(context));
    Attrs a = mCtorAttrs instanceof Attrs ? (Attrs) mCtorAttrs : null;
    if (a == null) return;
    String v;
    if ((v = a.a("titleTextAppearance")) != null && Values.isRef(v)) mTitleTextAppearance = Values.refId(v);
    if ((v = a.a("subtitleTextAppearance")) != null && Values.isRef(v)) mSubtitleTextAppearance = Values.refId(v);
    if ((v = a.a("gravity")) != null) mGravity = Values.gravity(v);
    if ((v = a.a("buttonGravity")) != null) mButtonGravity = Values.gravity(v);
    if ((v = a.a("titleMargin")) != null) mTitleMarginStart = mTitleMarginEnd = mTitleMarginTop = mTitleMarginBottom = (int) dim(v);
    if ((v = a.a("titleMargins")) != null) mTitleMarginStart = mTitleMarginEnd = mTitleMarginTop = mTitleMarginBottom = (int) dim(v);
    if ((v = a.a("titleMarginStart")) != null) mTitleMarginStart = (int) dim(v);
    if ((v = a.a("titleMarginEnd")) != null) mTitleMarginEnd = (int) dim(v);
    if ((v = a.a("titleMarginTop")) != null) mTitleMarginTop = (int) dim(v);
    if ((v = a.a("titleMarginBottom")) != null) mTitleMarginBottom = (int) dim(v);
    if ((v = a.a("maxButtonHeight")) != null) mMaxButtonHeight = (int) dim(v);
    if ((v = a.a("contentInsetStart")) != null) mContentInsetStart = (int) dim(v);
    if ((v = a.a("contentInsetEnd")) != null) mContentInsetEnd = (int) dim(v);
    if ((v = a.a("contentInsetLeft")) != null) { mContentInsetLeft = (int) dim(v); mExplicitLeft = true; }
    if ((v = a.a("contentInsetRight")) != null) { mContentInsetRight = (int) dim(v); mExplicitRight = true; }
    if ((v = a.a("contentInsetStartWithNavigation")) != null) mContentInsetStartWithNavigation = (int) dim(v);
    if ((v = a.a("contentInsetEndWithActions")) != null) mContentInsetEndWithActions = (int) dim(v);
    if ((v = a.a("collapseIcon")) != null && Values.isRef(v)) mCollapseIcon = getResources().getDrawable(Values.refId(v));
    if ((v = a.a("collapseContentDescription")) != null) mCollapseDescription = text(v);
    if ((v = a.a("popupTheme")) != null && Values.isRef(v)) mPopupTheme = Values.refId(v);
    if ((v = a.a("titleTextColor")) != null) mTitleTextColor = colors(v);
    if ((v = a.a("subtitleTextColor")) != null) mSubtitleTextColor = colors(v);
    if ((v = a.a("title")) != null && !v.isEmpty()) setTitle(text(v));
    if ((v = a.a("subtitle")) != null && !v.isEmpty()) setSubtitle(text(v));
    if ((v = a.a("navigationIcon")) != null && Values.isRef(v)) setNavigationIcon(getResources().getDrawable(Values.refId(v)));
    if ((v = a.a("navigationContentDescription")) != null) setNavigationContentDescription(text(v));
    if ((v = a.a("logo")) != null && Values.isRef(v)) setLogo(getResources().getDrawable(Values.refId(v)));
    if ((v = a.a("logoDescription")) != null) setLogoDescription(text(v));
  }
  private CharSequence text(String v) { return Values.isRef(v) ? getResources().getText(Values.refId(v)) : v; }
  private ColorStateList colors(String v) {
    try { return Values.isRef(v) ? getResources().getColorStateList(Values.refId(v)) : ColorStateList.valueOf(Values.parseColor(v)); }
    catch (RuntimeException e) { return null; }
  }
  static int actionBarSize(Context c) {
    TypedValue tv = new TypedValue();
    if (c.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) return TypedValue.complexToDimensionPixelSize(tv.data, c.getResources().getDisplayMetrics());
    return Math.round(56 * c.getResources().getDisplayMetrics().density);
  }
  private Drawable borderlessBackground() {
    try {
      android.content.res.TypedArray ta = getContext().obtainStyledAttributes(new int[] {android.R.attr.selectableItemBackgroundBorderless});
      Drawable dr = ta.getDrawable(0);
      ta.recycle();
      return dr;
    } catch (RuntimeException e) { return null; }
  }
  private int titleColor() { return mTitleTextColor != null ? mTitleTextColor.getDefaultColor() : ActionMenuView.defaultColor(getContext()); }

  // ---------------------------------------------------------------- the system views
  private void ensureMenuView() {
    if (mMenuView != null) return;
    mMenuView = new ActionMenuView(getContext());
    mMenuView.setPopupTheme(mPopupTheme);
    mMenuView.setItemColor(titleColor());
    mMenuView.setOnMenuItemClickListener(item -> mOnMenuItemClickListener != null && mOnMenuItemClickListener.onMenuItemClick(item));
    LayoutParams lp = generateDefaultLayoutParams();
    lp.gravity = Gravity.END | (mButtonGravity & Gravity.VERTICAL_GRAVITY_MASK);
    mMenuView.setLayoutParams(lp);
    addSystemView(mMenuView, false);
  }
  private void ensureNavButtonView() {
    if (mNavButtonView != null) return;
    mNavButtonView = new ImageButton(getContext());
    styleButton(mNavButtonView);
  }
  private void ensureCollapseButtonView() {
    if (mCollapseButtonView != null) return;
    mCollapseButtonView = new ImageButton(getContext());
    styleButton(mCollapseButtonView);
    mCollapseButtonView.setImageDrawable(mCollapseIcon != null ? mCollapseIcon : a2w.ActionBarImpl.glyph(false, titleColor()));
    mCollapseButtonView.setContentDescription(mCollapseDescription != null ? mCollapseDescription : "Collapse");
    mCollapseButtonView.setOnClickListener(v -> collapseActionView());
  }
  private void styleButton(ImageButton b) {
    b.setBackground(borderlessBackground());
    b.setScaleType(ImageView.ScaleType.CENTER);
    b.setMinimumWidth(Math.round(56 * getResources().getDisplayMetrics().density));
    b.setPadding(0, 0, 0, 0);
    LayoutParams lp = generateDefaultLayoutParams();
    lp.gravity = Gravity.START | (mButtonGravity & Gravity.VERTICAL_GRAVITY_MASK);
    b.setLayoutParams(lp);
  }
  private void ensureLogoView() { if (mLogoView == null) mLogoView = new ImageView(getContext()); }
  private void addSystemView(View v, boolean allowHide) {
    ViewGroup.LayoutParams vlp = v.getLayoutParams();
    LayoutParams lp = vlp == null ? generateDefaultLayoutParams() : !checkLayoutParams(vlp) ? generateLayoutParams(vlp) : (LayoutParams) vlp;
    lp.mViewType = LayoutParams.SYSTEM;
    if (allowHide && mExpandedActionView != null) { v.setLayoutParams(lp); mHiddenViews.add(v); }
    else addView(v, lp);
  }
  private boolean isChildOrHidden(View child) { return child.getParent() == this || mHiddenViews.contains(child); }

  // ---------------------------------------------------------------- title, subtitle, logo, navigation
  public CharSequence getTitle() { return mTitleText; }
  public void setTitle(int resId) { setTitle(getContext().getText(resId)); }
  public void setTitle(CharSequence title) {
    if (!TextUtils.isEmpty(title)) {
      if (mTitleTextView == null) {
        mTitleTextView = new TextView(getContext());
        mTitleTextView.setSingleLine();
        mTitleTextView.setEllipsize(TextUtils.TruncateAt.END);
        mTitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        mTitleTextView.setTypeface(android.graphics.Typeface.create("sans-serif-medium", android.graphics.Typeface.NORMAL));
        mTitleTextView.setTextColor(titleColor());
        if (mTitleTextAppearance != 0) mTitleTextView.setTextAppearance(getContext(), mTitleTextAppearance);
        if (mTitleTextColor != null) mTitleTextView.setTextColor(mTitleTextColor);
      }
      if (!isChildOrHidden(mTitleTextView)) addSystemView(mTitleTextView, true);
    } else if (mTitleTextView != null && isChildOrHidden(mTitleTextView)) { removeView(mTitleTextView); mHiddenViews.remove(mTitleTextView); }
    if (mTitleTextView != null) mTitleTextView.setText(title);
    mTitleText = title;
  }
  public CharSequence getSubtitle() { return mSubtitleText; }
  public void setSubtitle(int resId) { setSubtitle(getContext().getText(resId)); }
  public void setSubtitle(CharSequence subtitle) {
    if (!TextUtils.isEmpty(subtitle)) {
      if (mSubtitleTextView == null) {
        mSubtitleTextView = new TextView(getContext());
        mSubtitleTextView.setSingleLine();
        mSubtitleTextView.setEllipsize(TextUtils.TruncateAt.END);
        mSubtitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        int c = titleColor();
        mSubtitleTextView.setTextColor((c & 0x00ffffff) | 0xb3000000);
        if (mSubtitleTextAppearance != 0) mSubtitleTextView.setTextAppearance(getContext(), mSubtitleTextAppearance);
        if (mSubtitleTextColor != null) mSubtitleTextView.setTextColor(mSubtitleTextColor);
      }
      if (!isChildOrHidden(mSubtitleTextView)) addSystemView(mSubtitleTextView, true);
    } else if (mSubtitleTextView != null && isChildOrHidden(mSubtitleTextView)) { removeView(mSubtitleTextView); mHiddenViews.remove(mSubtitleTextView); }
    if (mSubtitleTextView != null) mSubtitleTextView.setText(subtitle);
    mSubtitleText = subtitle;
  }
  public void setTitleTextAppearance(Context context, int resId) { mTitleTextAppearance = resId; if (mTitleTextView != null) mTitleTextView.setTextAppearance(context, resId); }
  public void setSubtitleTextAppearance(Context context, int resId) { mSubtitleTextAppearance = resId; if (mSubtitleTextView != null) mSubtitleTextView.setTextAppearance(context, resId); }
  public void setTitleTextColor(int color) { setTitleTextColor(ColorStateList.valueOf(color)); }
  public void setTitleTextColor(ColorStateList color) { mTitleTextColor = color; if (mTitleTextView != null) mTitleTextView.setTextColor(color); }
  public void setSubtitleTextColor(int color) { setSubtitleTextColor(ColorStateList.valueOf(color)); }
  public void setSubtitleTextColor(ColorStateList color) { mSubtitleTextColor = color; if (mSubtitleTextView != null) mSubtitleTextView.setTextColor(color); }
  public int getTitleMarginStart() { return mTitleMarginStart; }
  public void setTitleMarginStart(int margin) { mTitleMarginStart = margin; requestLayout(); }
  public int getTitleMarginTop() { return mTitleMarginTop; }
  public void setTitleMarginTop(int margin) { mTitleMarginTop = margin; requestLayout(); }
  public int getTitleMarginEnd() { return mTitleMarginEnd; }
  public void setTitleMarginEnd(int margin) { mTitleMarginEnd = margin; requestLayout(); }
  public int getTitleMarginBottom() { return mTitleMarginBottom; }
  public void setTitleMarginBottom(int margin) { mTitleMarginBottom = margin; requestLayout(); }
  public void setTitleMargin(int start, int top, int end, int bottom) { mTitleMarginStart = start; mTitleMarginTop = top; mTitleMarginEnd = end; mTitleMarginBottom = bottom; requestLayout(); }

  public void setLogo(int resId) { setLogo(getContext().getDrawable(resId)); }
  public void setLogo(Drawable drawable) {
    if (drawable != null) { ensureLogoView(); if (!isChildOrHidden(mLogoView)) addSystemView(mLogoView, true); }
    else if (mLogoView != null && isChildOrHidden(mLogoView)) { removeView(mLogoView); mHiddenViews.remove(mLogoView); }
    if (mLogoView != null) mLogoView.setImageDrawable(drawable);
  }
  public Drawable getLogo() { return mLogoView != null ? mLogoView.getDrawable() : null; }
  public void setLogoDescription(int resId) { setLogoDescription(getContext().getText(resId)); }
  public void setLogoDescription(CharSequence description) { if (!TextUtils.isEmpty(description)) ensureLogoView(); if (mLogoView != null) mLogoView.setContentDescription(description); }
  public CharSequence getLogoDescription() { return mLogoView != null ? mLogoView.getContentDescription() : null; }

  public CharSequence getNavigationContentDescription() { return mNavButtonView != null ? mNavButtonView.getContentDescription() : null; }
  public void setNavigationContentDescription(int resId) { setNavigationContentDescription(resId != 0 ? getContext().getText(resId) : null); }
  public void setNavigationContentDescription(CharSequence description) { if (!TextUtils.isEmpty(description)) ensureNavButtonView(); if (mNavButtonView != null) mNavButtonView.setContentDescription(description); }
  public void setNavigationIcon(int resId) { setNavigationIcon(getContext().getDrawable(resId)); }
  public void setNavigationIcon(Drawable icon) {
    if (icon != null) { ensureNavButtonView(); if (!isChildOrHidden(mNavButtonView)) addSystemView(mNavButtonView, true); }
    else if (mNavButtonView != null && isChildOrHidden(mNavButtonView)) { removeView(mNavButtonView); mHiddenViews.remove(mNavButtonView); }
    if (mNavButtonView != null) mNavButtonView.setImageDrawable(icon);
  }
  public Drawable getNavigationIcon() { return mNavButtonView != null ? mNavButtonView.getDrawable() : null; }
  public void setNavigationOnClickListener(OnClickListener listener) { ensureNavButtonView(); mNavButtonView.setOnClickListener(listener); }


  public CharSequence getCollapseContentDescription() { return mCollapseButtonView != null ? mCollapseButtonView.getContentDescription() : mCollapseDescription; }
  public void setCollapseContentDescription(int resId) { setCollapseContentDescription(resId != 0 ? getContext().getText(resId) : null); }
  public void setCollapseContentDescription(CharSequence description) { mCollapseDescription = description; if (mCollapseButtonView != null) mCollapseButtonView.setContentDescription(description); }
  public Drawable getCollapseIcon() { return mCollapseButtonView != null ? mCollapseButtonView.getDrawable() : mCollapseIcon; }
  public void setCollapseIcon(int resId) { setCollapseIcon(getContext().getDrawable(resId)); }
  public void setCollapseIcon(Drawable icon) { mCollapseIcon = icon; if (mCollapseButtonView != null) mCollapseButtonView.setImageDrawable(icon); }

  // ---------------------------------------------------------------- menu
  public Menu getMenu() { ensureMenuView(); return mMenuView.getMenu(); }
  public void inflateMenu(int resId) { getMenuInflater().inflate(resId, getMenu()); }
  private android.view.MenuInflater getMenuInflater() { return new android.view.MenuInflater(getContext()); }
  public void setOnMenuItemClickListener(OnMenuItemClickListener listener) { mOnMenuItemClickListener = listener; }
  /** web: shows an existing menu (the activity's options menu, for the toolbar action bar) */
  public void a2wSetMenu(a2w.MenuBuilder menu) { ensureMenuView(); mMenuView.setMenuInternal(menu, false); }
  public void setOverflowIcon(Drawable icon) { ensureMenuView(); mMenuView.setOverflowIcon(icon); }
  public Drawable getOverflowIcon() { ensureMenuView(); return mMenuView.getOverflowIcon(); }
  public boolean isOverflowMenuShowing() { return mMenuView != null && mMenuView.isOverflowMenuShowing(); }
  public boolean showOverflowMenu() { return mMenuView != null && mMenuView.showOverflowMenu(); }
  public boolean hideOverflowMenu() { return mMenuView != null && mMenuView.hideOverflowMenu(); }
  public void dismissPopupMenus() { if (mMenuView != null) mMenuView.dismissPopupMenus(); }
  public void setPopupTheme(int resId) { if (mPopupTheme != resId) { mPopupTheme = resId; if (mMenuView != null) mMenuView.setPopupTheme(resId); } }
  public int getPopupTheme() { return mPopupTheme; }

  // ---------------------------------------------------------------- expanded action views
  public boolean hasExpandedActionView() { return mExpandedActionView != null; }
  /** shows item's action view in place of the toolbar's other content (MenuItem.expandActionView) */
  public boolean a2wExpandItemActionView(MenuItem item) {
    View av = item.getActionView();
    if (av == null) return false;
    ensureCollapseButtonView();
    if (mCollapseButtonView.getParent() != this) addSystemView(mCollapseButtonView, false);
    if (av.getParent() instanceof ViewGroup) ((ViewGroup) av.getParent()).removeView(av);
    mExpandedActionView = av;
    mExpandedItem = item;
    LayoutParams lp = generateDefaultLayoutParams();
    lp.gravity = Gravity.START | (mButtonGravity & Gravity.VERTICAL_GRAVITY_MASK);
    lp.mViewType = LayoutParams.EXPANDED;
    addView(av, lp);
    for (int i = getChildCount() - 1; i >= 0; i--) {
      View c = getChildAt(i);
      if (((LayoutParams) c.getLayoutParams()).mViewType != LayoutParams.EXPANDED && c != mMenuView && c != mCollapseButtonView) { removeViewAt(i); mHiddenViews.add(c); }
    }
    if (av instanceof android.view.CollapsibleActionView) ((android.view.CollapsibleActionView) av).onActionViewExpanded();
    requestLayout();
    return true;
  }
  public void collapseActionView() {
    if (mExpandedActionView == null) return;
    MenuItem item = mExpandedItem;
    View av = mExpandedActionView;
    if (av instanceof android.view.CollapsibleActionView) ((android.view.CollapsibleActionView) av).onActionViewCollapsed();
    removeView(av);
    if (mCollapseButtonView != null) removeView(mCollapseButtonView);
    mExpandedActionView = null; mExpandedItem = null;
    for (int i = mHiddenViews.size() - 1; i >= 0; i--) addView(mHiddenViews.get(i));
    mHiddenViews.clear();
    if (item != null) item.collapseActionView();
    requestLayout();
  }

  // ---------------------------------------------------------------- content insets
  public void setContentInsetsRelative(int contentInsetStart, int contentInsetEnd) { mContentInsetStart = contentInsetStart; mContentInsetEnd = contentInsetEnd; requestLayout(); }
  public int getContentInsetStart() { return mContentInsetStart; }
  public int getContentInsetEnd() { return mContentInsetEnd; }
  public void setContentInsetsAbsolute(int contentInsetLeft, int contentInsetRight) {
    mContentInsetLeft = contentInsetLeft; mContentInsetRight = contentInsetRight; mExplicitLeft = mExplicitRight = true; requestLayout();
  }
  public int getContentInsetLeft() { return mExplicitLeft ? mContentInsetLeft : isLayoutRtl() ? mContentInsetEnd : mContentInsetStart; }
  public int getContentInsetRight() { return mExplicitRight ? mContentInsetRight : isLayoutRtl() ? mContentInsetStart : mContentInsetEnd; }
  public int getContentInsetStartWithNavigation() { return mContentInsetStartWithNavigation; }
  public void setContentInsetStartWithNavigation(int insetStartWithNavigation) { mContentInsetStartWithNavigation = Math.max(0, insetStartWithNavigation); requestLayout(); }
  public int getContentInsetEndWithActions() { return mContentInsetEndWithActions; }
  public void setContentInsetEndWithActions(int insetEndWithActions) { mContentInsetEndWithActions = Math.max(0, insetEndWithActions); requestLayout(); }
  public int getCurrentContentInsetStart() {
    return getNavigationIcon() != null ? Math.max(getContentInsetStart(), Math.max(mContentInsetStartWithNavigation, 0)) : getContentInsetStart();
  }
  public int getCurrentContentInsetEnd() {
    boolean hasActions = mMenuView != null && mMenuView.hasItems();
    return hasActions ? Math.max(getContentInsetEnd(), Math.max(mContentInsetEndWithActions, 0)) : getContentInsetEnd();
  }
  public int getCurrentContentInsetLeft() { return isLayoutRtl() ? getCurrentContentInsetEnd() : getCurrentContentInsetStart(); }
  public int getCurrentContentInsetRight() { return isLayoutRtl() ? getCurrentContentInsetStart() : getCurrentContentInsetEnd(); }

  @Override public void onRtlPropertiesChanged(int layoutDirection) { super.onRtlPropertiesChanged(layoutDirection); requestLayout(); }
  /** as the platform's: the toolbar eats touches so they don't reach views behind it */
  private boolean mEatingTouch;
  @Override public boolean onTouchEvent(MotionEvent ev) {
    int action = ev.getActionMasked();
    if (action == MotionEvent.ACTION_DOWN) mEatingTouch = false;
    if (!mEatingTouch) { boolean handled = super.onTouchEvent(ev); if (action == MotionEvent.ACTION_DOWN && !handled) mEatingTouch = true; }
    if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) mEatingTouch = false;
    return true;
  }
  @Override protected void onDetachedFromWindow() { super.onDetachedFromWindow(); dismissPopupMenus(); }

  // ---------------------------------------------------------------- measure and layout (as the platform's)
  private boolean shouldLayout(View view) { return view != null && view.getParent() == this && view.getVisibility() != GONE; }
  private static int getHorizontalMargins(View v) { MarginLayoutParams mlp = (MarginLayoutParams) v.getLayoutParams(); return mlp.getMarginStart() + mlp.getMarginEnd(); }
  private static int getVerticalMargins(View v) { MarginLayoutParams mlp = (MarginLayoutParams) v.getLayoutParams(); return mlp.topMargin + mlp.bottomMargin; }
  private void measureChildConstrained(View child, int parentWidthSpec, int widthUsed, int parentHeightSpec, int heightUsed, int heightConstraint) {
    MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
    int childWidthSpec = getChildMeasureSpec(parentWidthSpec, getPaddingLeft() + getPaddingRight() + lp.leftMargin + lp.rightMargin + widthUsed, lp.width);
    int childHeightSpec = getChildMeasureSpec(parentHeightSpec, getPaddingTop() + getPaddingBottom() + lp.topMargin + lp.bottomMargin + heightUsed, lp.height);
    int childHeightMode = MeasureSpec.getMode(childHeightSpec);
    if (childHeightMode != MeasureSpec.EXACTLY && heightConstraint >= 0) {
      int size = childHeightMode != MeasureSpec.UNSPECIFIED ? Math.min(MeasureSpec.getSize(childHeightSpec), heightConstraint) : heightConstraint;
      childHeightSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
    }
    child.measure(childWidthSpec, childHeightSpec);
  }
  private int measureChildCollapseMargins(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed, int[] collapsingMargins) {
    MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
    int leftDiff = lp.leftMargin - collapsingMargins[0], rightDiff = lp.rightMargin - collapsingMargins[1];
    int leftMargin = Math.max(0, leftDiff), rightMargin = Math.max(0, rightDiff), hMargins = leftMargin + rightMargin;
    collapsingMargins[0] = Math.max(0, -leftDiff);
    collapsingMargins[1] = Math.max(0, -rightDiff);
    int childWidthMeasureSpec = getChildMeasureSpec(parentWidthMeasureSpec, getPaddingLeft() + getPaddingRight() + hMargins + widthUsed, lp.width);
    int childHeightMeasureSpec = getChildMeasureSpec(parentHeightMeasureSpec, getPaddingTop() + getPaddingBottom() + lp.topMargin + lp.bottomMargin + heightUsed, lp.height);
    child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
    return child.getMeasuredWidth() + hMargins;
  }
  /** a toolbar with nothing in it but empty views collapses to zero height */
  private boolean shouldCollapse() {
    for (int i = 0; i < getChildCount(); i++) {
      View child = getChildAt(i);
      if (child.getMeasuredWidth() > 0 && child.getMeasuredHeight() > 0) return false;
    }
    return true;
  }
  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int width = 0, height = 0, childState = 0;
    final int[] collapsingMargins = mTempMargins;
    final int marginStartIndex, marginEndIndex;
    if (isLayoutRtl()) { marginStartIndex = 1; marginEndIndex = 0; } else { marginStartIndex = 0; marginEndIndex = 1; }
    int navWidth = 0;
    if (shouldLayout(mNavButtonView)) {
      measureChildConstrained(mNavButtonView, widthMeasureSpec, width, heightMeasureSpec, 0, mMaxButtonHeight);
      navWidth = mNavButtonView.getMeasuredWidth() + getHorizontalMargins(mNavButtonView);
      height = Math.max(height, mNavButtonView.getMeasuredHeight() + getVerticalMargins(mNavButtonView));
      childState = combineMeasuredStates(childState, mNavButtonView.getMeasuredState());
    }
    if (shouldLayout(mCollapseButtonView)) {
      measureChildConstrained(mCollapseButtonView, widthMeasureSpec, width, heightMeasureSpec, 0, mMaxButtonHeight);
      navWidth = mCollapseButtonView.getMeasuredWidth() + getHorizontalMargins(mCollapseButtonView);
      height = Math.max(height, mCollapseButtonView.getMeasuredHeight() + getVerticalMargins(mCollapseButtonView));
      childState = combineMeasuredStates(childState, mCollapseButtonView.getMeasuredState());
    }
    final int contentInsetStart = getCurrentContentInsetStart();
    width += Math.max(contentInsetStart, navWidth);
    collapsingMargins[marginStartIndex] = Math.max(0, contentInsetStart - navWidth);
    int menuWidth = 0;
    if (shouldLayout(mMenuView)) {
      measureChildConstrained(mMenuView, widthMeasureSpec, width, heightMeasureSpec, 0, mMaxButtonHeight);
      menuWidth = mMenuView.getMeasuredWidth() + getHorizontalMargins(mMenuView);
      height = Math.max(height, mMenuView.getMeasuredHeight() + getVerticalMargins(mMenuView));
      childState = combineMeasuredStates(childState, mMenuView.getMeasuredState());
    }
    final int contentInsetEnd = getCurrentContentInsetEnd();
    width += Math.max(contentInsetEnd, menuWidth);
    collapsingMargins[marginEndIndex] = Math.max(0, contentInsetEnd - menuWidth);
    if (shouldLayout(mExpandedActionView)) {
      width += measureChildCollapseMargins(mExpandedActionView, widthMeasureSpec, width, heightMeasureSpec, 0, collapsingMargins);
      height = Math.max(height, mExpandedActionView.getMeasuredHeight() + getVerticalMargins(mExpandedActionView));
      childState = combineMeasuredStates(childState, mExpandedActionView.getMeasuredState());
    }
    if (shouldLayout(mLogoView)) {
      width += measureChildCollapseMargins(mLogoView, widthMeasureSpec, width, heightMeasureSpec, 0, collapsingMargins);
      height = Math.max(height, mLogoView.getMeasuredHeight() + getVerticalMargins(mLogoView));
      childState = combineMeasuredStates(childState, mLogoView.getMeasuredState());
    }
    for (int i = 0; i < getChildCount(); i++) {
      View child = getChildAt(i);
      LayoutParams lp = (LayoutParams) child.getLayoutParams();
      if (lp.mViewType != LayoutParams.CUSTOM || !shouldLayout(child)) continue;
      width += measureChildCollapseMargins(child, widthMeasureSpec, width, heightMeasureSpec, 0, collapsingMargins);
      height = Math.max(height, child.getMeasuredHeight() + getVerticalMargins(child));
      childState = combineMeasuredStates(childState, child.getMeasuredState());
    }
    int titleWidth = 0, titleHeight = 0;
    final int titleVertMargins = mTitleMarginTop + mTitleMarginBottom, titleHorizMargins = mTitleMarginStart + mTitleMarginEnd;
    if (shouldLayout(mTitleTextView)) {
      measureChildCollapseMargins(mTitleTextView, widthMeasureSpec, width + titleHorizMargins, heightMeasureSpec, titleVertMargins, collapsingMargins);
      titleWidth = mTitleTextView.getMeasuredWidth() + getHorizontalMargins(mTitleTextView);
      titleHeight = mTitleTextView.getMeasuredHeight() + getVerticalMargins(mTitleTextView);
      childState = combineMeasuredStates(childState, mTitleTextView.getMeasuredState());
    }
    if (shouldLayout(mSubtitleTextView)) {
      titleWidth = Math.max(titleWidth, measureChildCollapseMargins(mSubtitleTextView, widthMeasureSpec, width + titleHorizMargins, heightMeasureSpec,
          titleHeight + titleVertMargins, collapsingMargins));
      titleHeight += mSubtitleTextView.getMeasuredHeight() + getVerticalMargins(mSubtitleTextView);
      childState = combineMeasuredStates(childState, mSubtitleTextView.getMeasuredState());
    }
    width += titleWidth;
    height = Math.max(height, titleHeight);
    width += getPaddingLeft() + getPaddingRight();
    height += getPaddingTop() + getPaddingBottom();
    final int measuredWidth = resolveSizeAndState(Math.max(width, getSuggestedMinimumWidth()), widthMeasureSpec, childState & MEASURED_STATE_MASK);
    final int measuredHeight = resolveSizeAndState(Math.max(height, getSuggestedMinimumHeight()), heightMeasureSpec, childState << MEASURED_HEIGHT_STATE_SHIFT);
    setMeasuredDimension(measuredWidth, shouldCollapse() ? 0 : measuredHeight);
  }

  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
    final boolean isRtl = isLayoutRtl();
    final int width = getWidth(), height = getHeight();
    final int paddingLeft = getPaddingLeft(), paddingRight = getPaddingRight(), paddingTop = getPaddingTop(), paddingBottom = getPaddingBottom();
    int left = paddingLeft, right = width - paddingRight;
    final int[] collapsingMargins = mTempMargins;
    collapsingMargins[0] = collapsingMargins[1] = 0;
    final int alignmentHeight = getMinimumHeight() >= 0 ? Math.min(getMinimumHeight(), b - t) : 0;
    if (shouldLayout(mNavButtonView)) {
      if (isRtl) right = layoutChildRight(mNavButtonView, right, collapsingMargins, alignmentHeight);
      else left = layoutChildLeft(mNavButtonView, left, collapsingMargins, alignmentHeight);
    }
    if (shouldLayout(mCollapseButtonView)) {
      if (isRtl) right = layoutChildRight(mCollapseButtonView, right, collapsingMargins, alignmentHeight);
      else left = layoutChildLeft(mCollapseButtonView, left, collapsingMargins, alignmentHeight);
    }
    if (shouldLayout(mMenuView)) {
      if (isRtl) left = layoutChildLeft(mMenuView, left, collapsingMargins, alignmentHeight);
      else right = layoutChildRight(mMenuView, right, collapsingMargins, alignmentHeight);
    }
    final int contentInsetLeft = getCurrentContentInsetLeft(), contentInsetRight = getCurrentContentInsetRight();
    collapsingMargins[0] = Math.max(0, contentInsetLeft - left);
    collapsingMargins[1] = Math.max(0, contentInsetRight - (width - paddingRight - right));
    left = Math.max(left, contentInsetLeft);
    right = Math.min(right, width - paddingRight - contentInsetRight);
    if (shouldLayout(mExpandedActionView)) {
      if (isRtl) right = layoutChildRight(mExpandedActionView, right, collapsingMargins, alignmentHeight);
      else left = layoutChildLeft(mExpandedActionView, left, collapsingMargins, alignmentHeight);
    }
    if (shouldLayout(mLogoView)) {
      if (isRtl) right = layoutChildRight(mLogoView, right, collapsingMargins, alignmentHeight);
      else left = layoutChildLeft(mLogoView, left, collapsingMargins, alignmentHeight);
    }
    final boolean layoutTitle = shouldLayout(mTitleTextView), layoutSubtitle = shouldLayout(mSubtitleTextView);
    int titleHeight = 0;
    if (layoutTitle) { LayoutParams lp = (LayoutParams) mTitleTextView.getLayoutParams(); titleHeight += lp.topMargin + mTitleTextView.getMeasuredHeight() + lp.bottomMargin; }
    if (layoutSubtitle) { LayoutParams lp = (LayoutParams) mSubtitleTextView.getLayoutParams(); titleHeight += lp.topMargin + mSubtitleTextView.getMeasuredHeight() + lp.bottomMargin; }
    if (layoutTitle || layoutSubtitle) {
      int titleTop;
      final View topChild = layoutTitle ? mTitleTextView : mSubtitleTextView, bottomChild = layoutSubtitle ? mSubtitleTextView : mTitleTextView;
      final LayoutParams toplp = (LayoutParams) topChild.getLayoutParams(), bottomlp = (LayoutParams) bottomChild.getLayoutParams();
      final boolean titleHasWidth = layoutTitle && mTitleTextView.getMeasuredWidth() > 0 || layoutSubtitle && mSubtitleTextView.getMeasuredWidth() > 0;
      switch (mGravity & Gravity.VERTICAL_GRAVITY_MASK) {
        case Gravity.TOP: titleTop = getPaddingTop() + toplp.topMargin + mTitleMarginTop; break;
        case Gravity.BOTTOM: titleTop = height - paddingBottom - bottomlp.bottomMargin - mTitleMarginBottom - titleHeight; break;
        default: {
          final int space = height - paddingTop - paddingBottom;
          int spaceAbove = (space - titleHeight) / 2;
          if (spaceAbove < toplp.topMargin + mTitleMarginTop) spaceAbove = toplp.topMargin + mTitleMarginTop;
          else {
            final int spaceBelow = height - paddingBottom - titleHeight - spaceAbove - paddingTop;
            if (spaceBelow < toplp.bottomMargin + mTitleMarginBottom) spaceAbove = Math.max(0, spaceAbove - (bottomlp.bottomMargin + mTitleMarginBottom - spaceBelow));
          }
          titleTop = paddingTop + spaceAbove;
        }
      }
      if (isRtl) {
        final int rd = (titleHasWidth ? mTitleMarginStart : 0) - collapsingMargins[1];
        right -= Math.max(0, rd);
        collapsingMargins[1] = Math.max(0, -rd);
        int titleLeft = right, subtitleLeft = right;
        if (layoutTitle) {
          LayoutParams lp = (LayoutParams) mTitleTextView.getLayoutParams();
          int tl = right - mTitleTextView.getMeasuredWidth(), tb = titleTop + mTitleTextView.getMeasuredHeight();
          mTitleTextView.layout(tl, titleTop, right, tb);
          titleLeft = tl - mTitleMarginEnd;
          titleTop = tb + lp.bottomMargin;
        }
        if (layoutSubtitle) {
          LayoutParams lp = (LayoutParams) mSubtitleTextView.getLayoutParams();
          titleTop += lp.topMargin;
          int sl = right - mSubtitleTextView.getMeasuredWidth(), sb = titleTop + mSubtitleTextView.getMeasuredHeight();
          mSubtitleTextView.layout(sl, titleTop, right, sb);
          subtitleLeft = right - mTitleMarginEnd;
        }
        if (titleHasWidth) right = Math.min(titleLeft, subtitleLeft);
      } else {
        final int ld = (titleHasWidth ? mTitleMarginStart : 0) - collapsingMargins[0];
        left += Math.max(0, ld);
        collapsingMargins[0] = Math.max(0, -ld);
        int titleRight = left, subtitleRight = left;
        if (layoutTitle) {
          LayoutParams lp = (LayoutParams) mTitleTextView.getLayoutParams();
          int tr = left + mTitleTextView.getMeasuredWidth(), tb = titleTop + mTitleTextView.getMeasuredHeight();
          mTitleTextView.layout(left, titleTop, tr, tb);
          titleRight = tr + mTitleMarginEnd;
          titleTop = tb + lp.bottomMargin;
        }
        if (layoutSubtitle) {
          LayoutParams lp = (LayoutParams) mSubtitleTextView.getLayoutParams();
          titleTop += lp.topMargin;
          int sr = left + mSubtitleTextView.getMeasuredWidth(), sb = titleTop + mSubtitleTextView.getMeasuredHeight();
          mSubtitleTextView.layout(left, titleTop, sr, sb);
          subtitleRight = sr + mTitleMarginEnd;
        }
        if (titleHasWidth) left = Math.max(titleRight, subtitleRight);
      }
    }
    // custom views: left, right, then centered
    addCustomViewsWithGravity(mTempViews, Gravity.LEFT);
    for (View v : mTempViews) left = layoutChildLeft(v, left, collapsingMargins, alignmentHeight);
    addCustomViewsWithGravity(mTempViews, Gravity.RIGHT);
    for (View v : mTempViews) right = layoutChildRight(v, right, collapsingMargins, alignmentHeight);
    addCustomViewsWithGravity(mTempViews, Gravity.CENTER_HORIZONTAL);
    final int centerViewsWidth = getViewListMeasuredWidth(mTempViews, collapsingMargins);
    final int parentCenter = paddingLeft + (width - paddingLeft - paddingRight) / 2;
    int centerLeft = parentCenter - centerViewsWidth / 2;
    final int centerRight = centerLeft + centerViewsWidth;
    if (centerLeft < left) centerLeft = left;
    else if (centerRight > right) centerLeft -= centerRight - right;
    for (View v : mTempViews) centerLeft = layoutChildLeft(v, centerLeft, collapsingMargins, alignmentHeight);
    mTempViews.clear();
  }
  private int getViewListMeasuredWidth(java.util.List<View> views, int[] collapsingMargins) {
    int collapseLeft = collapsingMargins[0], collapseRight = collapsingMargins[1], width = 0;
    for (View v : views) {
      LayoutParams lp = (LayoutParams) v.getLayoutParams();
      int l = lp.leftMargin - collapseLeft, r = lp.rightMargin - collapseRight;
      int leftMargin = Math.max(0, l), rightMargin = Math.max(0, r);
      collapseLeft = Math.max(0, -l); collapseRight = Math.max(0, -r);
      width += leftMargin + v.getMeasuredWidth() + rightMargin;
    }
    return width;
  }
  private int layoutChildLeft(View child, int left, int[] collapsingMargins, int alignmentHeight) {
    LayoutParams lp = (LayoutParams) child.getLayoutParams();
    int l = lp.leftMargin - collapsingMargins[0];
    left += Math.max(0, l);
    collapsingMargins[0] = Math.max(0, -l);
    int top = getChildTop(child, alignmentHeight), childWidth = child.getMeasuredWidth();
    child.layout(left, top, left + childWidth, top + child.getMeasuredHeight());
    return left + childWidth + lp.rightMargin;
  }
  private int layoutChildRight(View child, int right, int[] collapsingMargins, int alignmentHeight) {
    LayoutParams lp = (LayoutParams) child.getLayoutParams();
    int r = lp.rightMargin - collapsingMargins[1];
    right -= Math.max(0, r);
    collapsingMargins[1] = Math.max(0, -r);
    int top = getChildTop(child, alignmentHeight), childWidth = child.getMeasuredWidth();
    child.layout(right - childWidth, top, right, top + child.getMeasuredHeight());
    return right - (childWidth + lp.leftMargin);
  }
  private int getChildTop(View child, int alignmentHeight) {
    LayoutParams lp = (LayoutParams) child.getLayoutParams();
    int childHeight = child.getMeasuredHeight();
    int alignmentOffset = alignmentHeight > 0 ? (childHeight - alignmentHeight) / 2 : 0;
    switch (getChildVerticalGravity(lp.gravity)) {
      case Gravity.TOP: return getPaddingTop() - alignmentOffset;
      case Gravity.BOTTOM: return getHeight() - getPaddingBottom() - childHeight - lp.bottomMargin - alignmentOffset;
      default: {
        int paddingTop = getPaddingTop(), paddingBottom = getPaddingBottom(), height = getHeight();
        int space = height - paddingTop - paddingBottom;
        int spaceAbove = (space - childHeight) / 2;
        if (spaceAbove < lp.topMargin) spaceAbove = lp.topMargin;
        else {
          int spaceBelow = height - paddingBottom - childHeight - spaceAbove - paddingTop;
          if (spaceBelow < lp.bottomMargin) spaceAbove = Math.max(0, spaceAbove - (lp.bottomMargin - spaceBelow));
        }
        return paddingTop + spaceAbove;
      }
    }
  }
  private int getChildVerticalGravity(int gravity) {
    int vgrav = gravity & Gravity.VERTICAL_GRAVITY_MASK;
    switch (vgrav) {
      case Gravity.TOP: case Gravity.BOTTOM: case Gravity.CENTER_VERTICAL: return vgrav;
      default: return mGravity & Gravity.VERTICAL_GRAVITY_MASK;
    }
  }
  /** custom children by horizontal gravity (start/end resolved for the layout direction), in layout order */
  private void addCustomViewsWithGravity(java.util.List<View> views, int gravity) {
    final boolean isRtl = isLayoutRtl();
    final int childCount = getChildCount();
    final int absGrav = Gravity.getAbsoluteGravity(gravity, getLayoutDirection());
    views.clear();
    for (int i = 0; i < childCount; i++) {
      View child = getChildAt(isRtl ? childCount - 1 - i : i);
      LayoutParams lp = (LayoutParams) child.getLayoutParams();
      if (lp.mViewType == LayoutParams.CUSTOM && shouldLayout(child) && getChildHorizontalGravity(lp.gravity) == absGrav) views.add(child);
    }
  }
  private int getChildHorizontalGravity(int gravity) {
    final int ld = getLayoutDirection();
    final int absGrav = Gravity.getAbsoluteGravity(gravity, ld);
    final int hGrav = absGrav & Gravity.HORIZONTAL_GRAVITY_MASK;
    switch (hGrav) {
      case Gravity.LEFT: case Gravity.RIGHT: case Gravity.CENTER_HORIZONTAL: return hGrav;
      default: return ld == LAYOUT_DIRECTION_RTL ? Gravity.RIGHT : Gravity.LEFT;
    }
  }

  @Override public LayoutParams generateLayoutParams(AttributeSet attrs) { return new LayoutParams(getContext(), attrs); }
  @Override protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
    if (p instanceof LayoutParams) return new LayoutParams((LayoutParams) p);
    if (p instanceof android.app.ActionBar.LayoutParams) return new LayoutParams((android.app.ActionBar.LayoutParams) p);
    if (p instanceof MarginLayoutParams) return new LayoutParams((MarginLayoutParams) p);
    return new LayoutParams(p);
  }
  @Override protected LayoutParams generateDefaultLayoutParams() { return new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT); }
  @Override protected boolean checkLayoutParams(ViewGroup.LayoutParams p) { return super.checkLayoutParams(p) && p instanceof LayoutParams; }

  /** A child's layout: its gravity (a custom view's horizontal position) and margins. */
  public static class LayoutParams extends android.app.ActionBar.LayoutParams {
    static final int CUSTOM = 0, SYSTEM = 1, EXPANDED = 2;
    int mViewType = CUSTOM;
    public LayoutParams(Context c, AttributeSet attrs) { super(c, attrs); }
    public LayoutParams(int width, int height) { super(width, height); gravity = Gravity.CENTER_VERTICAL | Gravity.START; }
    public LayoutParams(int width, int height, int gravity) { super(width, height); this.gravity = gravity; }
    public LayoutParams(int gravity) { this(WRAP_CONTENT, MATCH_PARENT, gravity); }
    public LayoutParams(LayoutParams source) { super(source); mViewType = source.mViewType; }
    public LayoutParams(android.app.ActionBar.LayoutParams source) { super(source); }
    public LayoutParams(MarginLayoutParams source) { super(source); leftMargin = source.leftMargin; topMargin = source.topMargin; rightMargin = source.rightMargin; bottomMargin = source.bottomMargin; }
    public LayoutParams(ViewGroup.LayoutParams source) { super(source); }
  }
}
