package android.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

/** A list in a popup anchored to a view (drop-downs, overflow menus, auto-complete suggestions). */
public class ListPopupWindow {
  public static final int POSITION_PROMPT_ABOVE = 0, POSITION_PROMPT_BELOW = 1, MATCH_PARENT = -1, WRAP_CONTENT = -2,
      INPUT_METHOD_FROM_FOCUSABLE = 0, INPUT_METHOD_NEEDED = 1, INPUT_METHOD_NOT_NEEDED = 2;
  private final Context mContext;
  private final PopupWindow mPopup;
  private ListAdapter mAdapter;
  private ListView mList;
  private View mAnchor;
  private int mWidth = WRAP_CONTENT, mHeight = WRAP_CONTENT, mHorizontalOffset, mVerticalOffset, mGravity = Gravity.NO_GRAVITY, mPromptPosition;
  private boolean mModal, mVerticalOffsetSet;
  private AdapterView.OnItemClickListener mItemClick;
  private AdapterView.OnItemSelectedListener mItemSelected;
  private View mPrompt;
  private int mSelection = -1;

  public ListPopupWindow(Context context) { this(context, null, android.R.attr.listPopupWindowStyle, 0); }
  public ListPopupWindow(Context context, AttributeSet attrs) { this(context, attrs, android.R.attr.listPopupWindowStyle, 0); }
  public ListPopupWindow(Context context, AttributeSet attrs, int defStyleAttr) { this(context, attrs, defStyleAttr, 0); }
  public ListPopupWindow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    mContext = context;
    mPopup = new PopupWindow(context, attrs, defStyleAttr, defStyleRes);
    if (mPopup.getBackground() == null) {
      boolean light = false;
      try { light = "true".equals(context.getTheme().get("a", "isLightTheme")); } catch (RuntimeException e) { light = false; }
      mPopup.setBackgroundDrawable(new ColorDrawable(light ? 0xfffafafa : 0xff303030));
    }
    mPopup.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
  }
  public void setAdapter(ListAdapter adapter) { mAdapter = adapter; if (mList != null) mList.setAdapter(adapter); }
  public void setPromptPosition(int position) { mPromptPosition = position; }
  public int getPromptPosition() { return mPromptPosition; }
  public void setModal(boolean modal) { mModal = modal; mPopup.setFocusable(modal); }
  public boolean isModal() { return mModal; }
  public void setDropDownAlwaysVisible(boolean v) {}
  public boolean isDropDownAlwaysVisible() { return false; }
  public void setSoftInputMode(int mode) { mPopup.setSoftInputMode(mode); }
  public int getSoftInputMode() { return mPopup.getSoftInputMode(); }
  public void setListSelector(Drawable selector) {}
  public Drawable getBackground() { return mPopup.getBackground(); }
  public void setBackgroundDrawable(Drawable d) { mPopup.setBackgroundDrawable(d); }
  public void setAnimationStyle(int s) { mPopup.setAnimationStyle(s); }
  public int getAnimationStyle() { return mPopup.getAnimationStyle(); }
  public View getAnchorView() { return mAnchor; }
  public void setAnchorView(View anchor) { mAnchor = anchor; }
  public int getHorizontalOffset() { return mHorizontalOffset; }
  public void setHorizontalOffset(int offset) { mHorizontalOffset = offset; }
  public int getVerticalOffset() { return mVerticalOffsetSet ? mVerticalOffset : 0; }
  public void setVerticalOffset(int offset) { mVerticalOffset = offset; mVerticalOffsetSet = true; }
  public void setEpicenterBounds(android.graphics.Rect bounds) {}
  public void setDropDownGravity(int gravity) { mGravity = gravity; }
  public int getWidth() { return mWidth; }
  public void setWidth(int width) { mWidth = width; }
  public void setContentWidth(int width) { android.graphics.drawable.Drawable bg = mPopup.getBackground(); if (bg != null) { android.graphics.Rect p = new android.graphics.Rect(); bg.getPadding(p); mWidth = p.left + p.right + width; } else mWidth = width; }
  public int getHeight() { return mHeight; }
  public void setHeight(int height) { mHeight = height; }
  public void setWindowLayoutType(int type) { mPopup.setWindowLayoutType(type); }
  public void setOnItemClickListener(AdapterView.OnItemClickListener l) { mItemClick = l; }
  public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener l) { mItemSelected = l; }
  public void setPromptView(View prompt) { mPrompt = prompt; }
  public void postShow() { new android.os.Handler(android.os.Looper.getMainLooper()).post(this::show); }
  public void show() {
    if (mList == null) {
      mList = new ListView(mContext);
      mList.setAdapter(mAdapter);
      mList.setOnItemClickListener((p, v, pos, id) -> { if (mItemClick != null) mItemClick.onItemClick(p, v, pos, id); });
      if (mItemSelected != null) mList.setOnItemSelectedListener(mItemSelected);
    }
    View content = mList;
    if (mPrompt != null) {
      LinearLayout col = new LinearLayout(mContext);
      col.setOrientation(LinearLayout.VERTICAL);
      if (mPromptPosition == POSITION_PROMPT_ABOVE) col.addView(mPrompt);
      col.addView(mList, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
      if (mPromptPosition == POSITION_PROMPT_BELOW) col.addView(mPrompt);
      content = col;
    }
    int width = mWidth == WRAP_CONTENT && mAnchor != null ? mAnchor.getWidth() : mWidth == MATCH_PARENT ? a2w.Screen.W : mWidth;
    int height = mHeight;
    if (mAnchor != null && height == WRAP_CONTENT) {
      int max = mPopup.getMaxAvailableHeight(mAnchor, mVerticalOffset);
      int listH = measureList(width);
      height = Math.min(listH, max);
    }
    if (mPopup.isShowing()) { mPopup.update(mAnchor, mHorizontalOffset, mVerticalOffset, width, height); return; }
    mPopup.setContentView(content);
    mPopup.setWidth(width);
    mPopup.setHeight(height);
    mPopup.setOutsideTouchable(!mModal);
    if (mAnchor != null) mPopup.showAsDropDown(mAnchor, mHorizontalOffset, mVerticalOffset, mGravity == Gravity.NO_GRAVITY ? Gravity.TOP | Gravity.START : mGravity);
    else mPopup.showAtLocation((View) null, Gravity.CENTER, 0, 0);
    if (mSelection >= 0) mList.setSelection(mSelection);
  }
  private int measureList(int width) {
    if (mAdapter == null) return 0;
    int h = 0, n = Math.min(mAdapter.getCount(), 50);
    int ws = View.MeasureSpec.makeMeasureSpec(Math.max(1, width), View.MeasureSpec.EXACTLY), hs = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
    for (int i = 0; i < n; i++) {
      View v = mAdapter.getView(i, null, mList);
      if (v.getLayoutParams() == null) v.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
      v.measure(ws, hs);
      h += v.getMeasuredHeight();
    }
    android.graphics.drawable.Drawable bg = mPopup.getBackground();
    if (bg != null) { android.graphics.Rect p = new android.graphics.Rect(); bg.getPadding(p); h += p.top + p.bottom; }
    return h;
  }
  public void dismiss() { mPopup.dismiss(); }
  public void setOnDismissListener(PopupWindow.OnDismissListener l) { mPopup.setOnDismissListener(l); }
  public void setInputMethodMode(int mode) { mPopup.setInputMethodMode(mode); }
  public int getInputMethodMode() { return mPopup.getInputMethodMode(); }
  public void setSelection(int position) { mSelection = position; if (mList != null) mList.setSelection(position); }
  public void clearListSelection() { mSelection = -1; }
  public boolean isShowing() { return mPopup.isShowing(); }
  public boolean isInputMethodNotNeeded() { return mPopup.getInputMethodMode() == INPUT_METHOD_NOT_NEEDED; }
  public boolean performItemClick(int position) {
    if (!isShowing() || mItemClick == null || mAdapter == null || position >= mAdapter.getCount()) return false;
    mItemClick.onItemClick(mList, null, position, mAdapter.getItemId(position));
    return true;
  }
  public Object getSelectedItem() { return mList != null ? mList.getSelectedItem() : null; }
  public int getSelectedItemPosition() { return mList != null ? mList.getSelectedItemPosition() : -1; }
  public long getSelectedItemId() { return mList != null ? mList.getSelectedItemId() : Long.MIN_VALUE; }
  public View getSelectedView() { return mList != null ? mList.getSelectedView() : null; }
  public ListView getListView() { return mList; }
  public View.OnTouchListener createDragToOpenListener(View src) { return null; }
}
