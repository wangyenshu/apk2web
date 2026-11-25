package android.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;

/** An editable text view that shows completion suggestions (its adapter's filter results) in a drop-down while typing. */
public class AutoCompleteTextView extends EditText implements Filter.FilterListener {
  public interface Validator { boolean isValid(CharSequence text); CharSequence fixText(CharSequence invalidText); }
  public interface OnDismissListener { void onDismiss(); }
  private ListAdapter mAdapter;
  private Filter mFilter;
  private int mThreshold = 2;
  private final ListPopupWindow mPopup;
  private AdapterView.OnItemClickListener mItemClick;
  private AdapterView.OnItemSelectedListener mItemSelected;
  private Validator mValidator;
  private boolean mBlockCompletion, mPopupCanBeUpdated = true;
  private CharSequence mHintText;
  private int mDropDownAnchor = View.NO_ID;

  public AutoCompleteTextView(Context c) { this(c, null); }
  public AutoCompleteTextView(Context c, AttributeSet a) { this(c, a, android.R.attr.autoCompleteTextViewStyle); }
  public AutoCompleteTextView(Context c, AttributeSet a, int d) { this(c, a, d, 0); }
  public AutoCompleteTextView(Context c, AttributeSet a, int d, int r) {
    super(c, a, d);
    mPopup = new ListPopupWindow(c);
    mPopup.setPromptPosition(ListPopupWindow.POSITION_PROMPT_BELOW);
    mPopup.setOnItemClickListener((p, v, pos, id) -> performCompletion(v, pos, id));
    if (a instanceof a2w.Attrs) {
      String th = ((a2w.Attrs) a).a("completionThreshold");
      if (th != null) mThreshold = Math.max(1, a2w.Values.parseInt(th, 2));
      String hint = ((a2w.Attrs) a).a("completionHint");
      if (hint != null) mHintText = hint;
    }
    addTextChangedListener(new TextWatcher() {
      public void beforeTextChanged(CharSequence s, int st, int c2, int af) {}
      public void onTextChanged(CharSequence s, int st, int b, int c2) {}
      public void afterTextChanged(Editable s) { doAfterTextChanged(); }
    });
  }
  void doAfterTextChanged() {
    if (mBlockCompletion) return;
    if (enoughToFilter()) { if (mFilter != null) { mPopupCanBeUpdated = true; performFiltering(getText(), 0); } }
    else { if (!mPopup.isDropDownAlwaysVisible()) dismissDropDown(); if (mFilter != null) mFilter.filter(null); }
  }
  public void setCompletionHint(CharSequence hint) { mHintText = hint; }
  public CharSequence getCompletionHint() { return mHintText; }
  public int getDropDownWidth() { return mPopup.getWidth(); }
  public void setDropDownWidth(int width) { mPopup.setWidth(width); }
  public int getDropDownHeight() { return mPopup.getHeight(); }
  public void setDropDownHeight(int height) { mPopup.setHeight(height); }
  public int getDropDownAnchor() { return mDropDownAnchor; }
  public void setDropDownAnchor(int id) { mDropDownAnchor = id; }
  public android.graphics.drawable.Drawable getDropDownBackground() { return mPopup.getBackground(); }
  public void setDropDownBackgroundDrawable(android.graphics.drawable.Drawable d) { mPopup.setBackgroundDrawable(d); }
  public void setDropDownBackgroundResource(int id) { mPopup.setBackgroundDrawable(getContext().getResources().getDrawable(id)); }
  public void setDropDownVerticalOffset(int offset) { mPopup.setVerticalOffset(offset); }
  public int getDropDownVerticalOffset() { return mPopup.getVerticalOffset(); }
  public void setDropDownHorizontalOffset(int offset) { mPopup.setHorizontalOffset(offset); }
  public int getDropDownHorizontalOffset() { return mPopup.getHorizontalOffset(); }
  public void setDropDownAnimationStyle(int s) {}
  public int getThreshold() { return mThreshold; }
  public void setThreshold(int threshold) { mThreshold = Math.max(1, threshold); }
  public void setOnItemClickListener(AdapterView.OnItemClickListener l) { mItemClick = l; }
  public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener l) { mItemSelected = l; }
  public AdapterView.OnItemClickListener getOnItemClickListener() { return mItemClick; }
  public AdapterView.OnItemSelectedListener getOnItemSelectedListener() { return mItemSelected; }
  public void setOnDismissListener(OnDismissListener l) { mPopup.setOnDismissListener(l == null ? null : l::onDismiss); }
  public ListAdapter getAdapter() { return mAdapter; }
  public <T extends ListAdapter & Filterable> void setAdapter(T adapter) {
    mAdapter = adapter;
    mFilter = adapter != null ? ((Filterable) adapter).getFilter() : null;
    mPopup.setAdapter(adapter);
  }
  public boolean enoughToFilter() { return getText().length() >= mThreshold; }
  public boolean isPopupShowing() { return mPopup.isShowing(); }
  protected CharSequence convertSelectionToString(Object selectedItem) { return mFilter != null ? mFilter.convertResultToString(selectedItem) : String.valueOf(selectedItem); }
  public void clearListSelection() { mPopup.clearListSelection(); }
  public void setListSelection(int position) { mPopup.setSelection(position); }
  public int getListSelection() { return mPopup.getSelectedItemPosition(); }
  protected void performFiltering(CharSequence text, int keyCode) { mFilter.filter(text, this); }
  public void performCompletion() { performCompletion(null, -1, -1); }
  private void performCompletion(View selectedView, int position, long id) {
    if (isPopupShowing() && mAdapter != null && position >= 0) {
      Object selectedItem = mAdapter.getItem(position);
      if (selectedItem == null) return;
      mBlockCompletion = true;
      replaceText(convertSelectionToString(selectedItem));
      mBlockCompletion = false;
      if (mItemClick != null) mItemClick.onItemClick(mPopup.getListView(), selectedView, position, id);
    }
    if (!mPopup.isDropDownAlwaysVisible()) dismissDropDown();
  }
  public boolean isPerformingCompletion() { return mBlockCompletion; }
  public void setText(CharSequence text, boolean filter) {
    if (filter) setText(text);
    else { mBlockCompletion = true; setText(text); mBlockCompletion = false; }
  }
  protected void replaceText(CharSequence text) {
    clearComposingText();
    setText(text);
    Editable e = getText();
    android.text.Selection.setSelection(e, e.length());
  }
  public void onFilterComplete(int count) { updateDropDownForFilter(count); }
  private void updateDropDownForFilter(int count) {
    if (getWindowVisibility() == View.GONE) return;
    if ((count > 0 || mPopup.isDropDownAlwaysVisible()) && enoughToFilter()) { if (hasFocus() && hasWindowFocus() && mPopupCanBeUpdated) showDropDown(); }
    else if (!mPopup.isDropDownAlwaysVisible() && isPopupShowing()) { dismissDropDown(); mPopupCanBeUpdated = true; }
  }
  public void dismissDropDown() { mPopup.dismiss(); }
  public void showDropDown() {
    View anchor = mDropDownAnchor != View.NO_ID ? getRootView().findViewById(mDropDownAnchor) : null;
    mPopup.setAnchorView(anchor != null ? anchor : this);
    mPopup.setModal(false);
    mPopup.show();
  }
  public void setDropDownAlwaysVisible(boolean v) { mPopup.setDropDownAlwaysVisible(v); }
  public boolean isDropDownAlwaysVisible() { return mPopup.isDropDownAlwaysVisible(); }
  public void setDropDownDismissedOnCompletion(boolean d) {}
  public boolean isDropDownDismissedOnCompletion() { return true; }
  public void setValidator(Validator v) { mValidator = v; }
  public Validator getValidator() { return mValidator; }
  public void performValidation() {
    if (mValidator == null) return;
    CharSequence t = getText();
    if (!android.text.TextUtils.isEmpty(t) && !mValidator.isValid(t)) setText(mValidator.fixText(t));
  }
  protected Filter getFilter() { return mFilter; }
  public void refreshAutoCompleteResults() { if (enoughToFilter() && mFilter != null) performFiltering(getText(), 0); }
  @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK && isPopupShowing()) { dismissDropDown(); return true; }
    return super.onKeyDown(keyCode, event);
  }
  @Override protected void onFocusChanged(boolean focused, int direction, android.graphics.Rect previouslyFocusedRect) {
    super.onFocusChanged(focused, direction, previouslyFocusedRect);
    if (!focused) { performValidation(); dismissDropDown(); }
  }
}
