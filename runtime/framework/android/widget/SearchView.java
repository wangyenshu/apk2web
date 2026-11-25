package android.widget;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import a2w.Attrs;
import a2w.Values;

/**
 * A search box, as the platform's: iconified it is a search button; expanded, a query field (with suggestions from a
 * CursorAdapter), a close button and an optional submit button. Submitting calls the OnQueryTextListener, else
 * starts the searchable activity with ACTION_SEARCH; a suggestion starts its intent (SearchManager columns).
 */
public class SearchView extends LinearLayout implements android.view.CollapsibleActionView {
  public interface OnQueryTextListener { boolean onQueryTextSubmit(String query); boolean onQueryTextChange(String newText); }
  public interface OnCloseListener { boolean onClose(); }
  public interface OnSuggestionListener { boolean onSuggestionSelect(int position); boolean onSuggestionClick(int position); }

  private final ImageView mSearchButton, mCollapsedIcon, mCloseButton, mGoButton;
  private final LinearLayout mSearchEditFrame;
  private final SearchAutoComplete mQueryTextView;
  private OnQueryTextListener mOnQueryChangeListener;
  private OnCloseListener mOnCloseListener;
  private OnSuggestionListener mOnSuggestionListener;
  private View.OnClickListener mOnSearchClickListener;
  private View.OnFocusChangeListener mOnQueryTextFocusChangeListener;
  private boolean mIconifiedByDefault = true, mIconified, mSubmitButtonEnabled, mQueryRefinement, mClearingFocus, mExpandedInActionView;
  private CharSequence mQueryHint, mOldQueryText, mUserQuery;
  private int mMaxWidth, mCollapsedImeOptions;
  private CursorAdapter mSuggestionsAdapter;
  private SearchableInfo mSearchable;
  private Bundle mAppSearchData;
  private Runnable mOnSubmitted;
  private final int mPreferredWidth, mPreferredHeight;

  public SearchView(Context context) { this(context, null); }
  public SearchView(Context context, AttributeSet attrs) { this(context, attrs, android.R.attr.searchViewStyle); }
  public SearchView(Context context, AttributeSet attrs, int defStyleAttr) { this(context, attrs, defStyleAttr, 0); }
  public SearchView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr);
    float d = getResources().getDisplayMetrics().density;
    mPreferredWidth = Math.round(320 * d);
    mPreferredHeight = Math.round(48 * d);
    setOrientation(HORIZONTAL);
    setGravity(Gravity.CENTER_VERTICAL);
    int color = ActionMenuView.defaultColor(context);
    int iconSize = Math.round(48 * d);
    mSearchButton = icon(context, Glyph.SEARCH, color, "Search");
    addView(mSearchButton, new LayoutParams(iconSize, iconSize));
    mSearchEditFrame = new LinearLayout(context);
    mSearchEditFrame.setOrientation(HORIZONTAL);
    mSearchEditFrame.setGravity(Gravity.CENTER_VERTICAL);
    addView(mSearchEditFrame, new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
    mCollapsedIcon = icon(context, Glyph.SEARCH, (color & 0x00ffffff) | 0x99000000, null);
    mCollapsedIcon.setFocusable(false);
    mSearchEditFrame.addView(mCollapsedIcon, new LayoutParams(Math.round(32 * d), iconSize));
    mQueryTextView = new SearchAutoComplete(context, this);
    mQueryTextView.setSingleLine(true);
    mQueryTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
    mQueryTextView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
    mQueryTextView.setThreshold(1);
    mSearchEditFrame.addView(mQueryTextView, new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
    mCloseButton = icon(context, Glyph.CLOSE, color, "Clear query");
    mSearchEditFrame.addView(mCloseButton, new LayoutParams(iconSize, iconSize));
    mGoButton = icon(context, Glyph.GO, color, "Submit query");
    addView(mGoButton, new LayoutParams(iconSize, iconSize));
    mSearchButton.setOnClickListener(v -> onSearchClicked());
    mCloseButton.setOnClickListener(v -> onCloseClicked());
    mGoButton.setOnClickListener(v -> onSubmitQuery());
    mQueryTextView.addTextChangedListener(new TextWatcher() {
      public void beforeTextChanged(CharSequence s, int start, int before, int after) {}
      public void onTextChanged(CharSequence s, int start, int before, int after) { onTextChangedInternal(s); }
      public void afterTextChanged(Editable s) {}
    });
    mQueryTextView.setOnEditorActionListener((v, actionId, event) -> { onSubmitQuery(); return true; });
    mQueryTextView.setOnItemClickListener((parent, view, position, id) -> onItemClicked(position));
    mQueryTextView.setOnFocusChangeListener((v, hasFocus) -> {
      if (mOnQueryTextFocusChangeListener != null) mOnQueryTextFocusChangeListener.onFocusChange(SearchView.this, hasFocus);
      updateSubmitButton(!TextUtils.isEmpty(mQueryTextView.getText()));
    });
    if (mCtorAttrs instanceof Attrs) {
      Attrs a = (Attrs) mCtorAttrs;
      String v;
      if ((v = a.a("iconifiedByDefault")) != null) mIconifiedByDefault = Values.parseBool(v, true);
      if ((v = a.a("maxWidth")) != null) mMaxWidth = (int) dim(v);
      if ((v = a.a("queryHint")) != null) mQueryHint = Values.isRef(v) ? getResources().getText(Values.refId(v)) : v;
      if ((v = a.a("imeOptions")) != null) setImeOptions(Values.imeOptions(v));
      if ((v = a.a("inputType")) != null) setInputType(Values.inputType(v));
    }
    setFocusable(true);
    updateViewsVisibility(mIconifiedByDefault);
    updateQueryHint();
  }
  private static ImageView icon(Context c, int kind, int color, CharSequence desc) {
    ImageView v = new ImageView(c);
    v.setImageDrawable(new Glyph(kind, color, Math.round(24 * c.getResources().getDisplayMetrics().density)));
    v.setScaleType(ImageView.ScaleType.CENTER);
    v.setFocusable(true);
    v.setClickable(true);
    if (desc != null) v.setContentDescription(desc);
    return v;
  }

  // ---------------------------------------------------------------- configuration
  public void setSearchableInfo(SearchableInfo searchable) {
    mSearchable = searchable;
    if (searchable != null) {
      mQueryTextView.setThreshold(Math.max(1, searchable.getSuggestThreshold()));
      mQueryTextView.setImeOptions(searchable.getImeOptions());
      int inputType = searchable.getInputType();
      if ((inputType & android.text.InputType.TYPE_MASK_CLASS) == android.text.InputType.TYPE_CLASS_TEXT && searchable.getSuggestAuthority() != null)
        inputType |= android.text.InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE | android.text.InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS;
      mQueryTextView.setInputType(inputType);
    }
    updateQueryHint();
  }
  /** web: the extras added to the search intent as APP_DATA (the search dialog's appSearchData) */
  public void a2wSetAppSearchData(Bundle appSearchData) { mAppSearchData = appSearchData; }
  /** web: run after a query or suggestion was sent (the search dialog closes) */
  public void a2wSetOnSubmitted(Runnable r) { mOnSubmitted = r; }
  public void a2wSelectQuery() { mQueryTextView.selectAll(); }
  public void setImeOptions(int imeOptions) { mQueryTextView.setImeOptions(imeOptions); }
  public int getImeOptions() { return mQueryTextView.getImeOptions(); }
  public void setInputType(int inputType) { mQueryTextView.setInputType(inputType); }
  public int getInputType() { return mQueryTextView.getInputType(); }
  @Override public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
    if (mClearingFocus || !isFocusable()) return false;
    if (!isIconified()) {
      boolean result = mQueryTextView.requestFocus(direction, previouslyFocusedRect);
      if (result) updateViewsVisibility(false);
      return result;
    }
    return super.requestFocus(direction, previouslyFocusedRect);
  }
  @Override public void clearFocus() {
    mClearingFocus = true;
    super.clearFocus();
    mQueryTextView.clearFocus();
    mClearingFocus = false;
  }
  public void setOnQueryTextListener(OnQueryTextListener listener) { mOnQueryChangeListener = listener; }
  public void setOnCloseListener(OnCloseListener listener) { mOnCloseListener = listener; }
  public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener listener) { mOnQueryTextFocusChangeListener = listener; }
  public void setOnSuggestionListener(OnSuggestionListener listener) { mOnSuggestionListener = listener; }
  public void setOnSearchClickListener(View.OnClickListener listener) { mOnSearchClickListener = listener; }
  public CharSequence getQuery() { return mQueryTextView.getText(); }
  public void setQuery(CharSequence query, boolean submit) {
    mQueryTextView.setText(query, false);
    if (query != null) { mQueryTextView.setSelection(mQueryTextView.length()); mUserQuery = query; }
    if (submit && !TextUtils.isEmpty(query)) onSubmitQuery();
  }
  public void setQueryHint(CharSequence hint) { mQueryHint = hint; updateQueryHint(); }
  public CharSequence getQueryHint() {
    if (mQueryHint != null) return mQueryHint;
    if (mSearchable != null && mSearchable.getHintId() != 0) return getContext().getText(mSearchable.getHintId());
    return null;
  }
  private void updateQueryHint() { CharSequence h = getQueryHint(); mQueryTextView.setHint(h == null ? "" : h); }
  public void setIconifiedByDefault(boolean iconified) { if (mIconifiedByDefault == iconified) return; mIconifiedByDefault = iconified; updateViewsVisibility(iconified); updateQueryHint(); }
  @Deprecated public boolean isIconfiedByDefault() { return mIconifiedByDefault; }
  public boolean isIconifiedByDefault() { return mIconifiedByDefault; }
  public void setIconified(boolean iconify) { if (iconify) onCloseClicked(); else onSearchClicked(); }
  public boolean isIconified() { return mIconified; }
  public void setSubmitButtonEnabled(boolean enabled) { mSubmitButtonEnabled = enabled; updateViewsVisibility(isIconified()); }
  public boolean isSubmitButtonEnabled() { return mSubmitButtonEnabled; }
  public void setQueryRefinementEnabled(boolean enable) { mQueryRefinement = enable; }
  public boolean isQueryRefinementEnabled() { return mQueryRefinement; }
  public void setSuggestionsAdapter(CursorAdapter adapter) { mSuggestionsAdapter = adapter; mQueryTextView.setAdapter(adapter); }
  public CursorAdapter getSuggestionsAdapter() { return mSuggestionsAdapter; }
  public void setMaxWidth(int maxpixels) { mMaxWidth = maxpixels; requestLayout(); }
  public int getMaxWidth() { return mMaxWidth; }

  // ---------------------------------------------------------------- measure
  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    if (isIconified()) { super.onMeasure(widthMeasureSpec, heightMeasureSpec); return; }
    int widthMode = MeasureSpec.getMode(widthMeasureSpec), width = MeasureSpec.getSize(widthMeasureSpec);
    switch (widthMode) {
      case MeasureSpec.AT_MOST: width = mMaxWidth > 0 ? Math.min(mMaxWidth, width) : Math.min(mPreferredWidth, width); break;
      case MeasureSpec.EXACTLY: if (mMaxWidth > 0) width = Math.min(mMaxWidth, width); break;
      default: width = mMaxWidth > 0 ? mMaxWidth : mPreferredWidth;
    }
    int heightMode = MeasureSpec.getMode(heightMeasureSpec), height = MeasureSpec.getSize(heightMeasureSpec);
    switch (heightMode) {
      case MeasureSpec.AT_MOST: height = Math.min(mPreferredHeight, height); break;
      case MeasureSpec.UNSPECIFIED: height = mPreferredHeight; break;
      default: break;
    }
    super.onMeasure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
  }
  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) { super.onLayout(changed, l, t, r, b); }
  @Override protected void onDetachedFromWindow() { super.onDetachedFromWindow(); mQueryTextView.dismissDropDown(); }
  @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (mSearchable == null) return false;
    return super.onKeyDown(keyCode, event);
  }
  @Override public void onWindowFocusChanged(boolean hasWindowFocus) { super.onWindowFocusChanged(hasWindowFocus); }

  // ---------------------------------------------------------------- state
  private void updateViewsVisibility(boolean collapsed) {
    mIconified = collapsed;
    int visCollapsed = collapsed ? VISIBLE : GONE;
    boolean hasText = !TextUtils.isEmpty(mQueryTextView.getText());
    mSearchButton.setVisibility(visCollapsed);
    updateSubmitButton(hasText);
    mSearchEditFrame.setVisibility(collapsed ? GONE : VISIBLE);
    mCollapsedIcon.setVisibility(mIconifiedByDefault ? GONE : VISIBLE);
    updateCloseButton();
  }
  private boolean isSubmitAreaEnabled() { return mSubmitButtonEnabled && !isIconified(); }
  private void updateSubmitButton(boolean hasText) {
    int visibility = GONE;
    if (mSubmitButtonEnabled && isSubmitAreaEnabled() && hasFocus() && hasText) visibility = VISIBLE;
    if (mSubmitButtonEnabled && isSubmitAreaEnabled() && hasText) visibility = VISIBLE;
    mGoButton.setVisibility(visibility);
  }
  private void updateCloseButton() {
    boolean hasText = !TextUtils.isEmpty(mQueryTextView.getText());
    boolean showClose = hasText || (mIconifiedByDefault && !mExpandedInActionView);
    mCloseButton.setVisibility(showClose ? VISIBLE : GONE);
    mCloseButton.setEnabled(hasText || mIconifiedByDefault);
  }
  private void onTextChangedInternal(CharSequence newText) {
    CharSequence text = mQueryTextView.getText();
    mUserQuery = text;
    boolean hasText = !TextUtils.isEmpty(text);
    updateSubmitButton(hasText);
    updateCloseButton();
    if (mOnQueryChangeListener != null && !TextUtils.equals(newText, mOldQueryText)) mOnQueryChangeListener.onQueryTextChange(newText.toString());
    mOldQueryText = newText.toString();
  }
  private void onSubmitQuery() {
    CharSequence query = mQueryTextView.getText();
    if (query != null && TextUtils.getTrimmedLength(query) > 0) {
      if (mOnQueryChangeListener == null || !mOnQueryChangeListener.onQueryTextSubmit(query.toString())) {
        if (mSearchable != null) launchQuerySearch(query.toString());
        mQueryTextView.dismissDropDown();
        if (mOnSubmitted != null) mOnSubmitted.run();
      }
    }
  }
  private void launchQuerySearch(String query) {
    Intent intent = new Intent(Intent.ACTION_SEARCH);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.putExtra(SearchManager.USER_QUERY, mUserQuery != null ? mUserQuery.toString() : query);
    intent.putExtra(SearchManager.QUERY, query);
    if (mAppSearchData != null) intent.putExtra(SearchManager.APP_DATA, mAppSearchData);
    intent.setComponent(mSearchable.getSearchActivity());
    getContext().startActivity(intent);
  }
  private void onCloseClicked() {
    CharSequence text = mQueryTextView.getText();
    if (TextUtils.isEmpty(text)) {
      if (mIconifiedByDefault && (mOnCloseListener == null || !mOnCloseListener.onClose())) { clearFocus(); updateViewsVisibility(true); }
    } else {
      mQueryTextView.setText("");
      mQueryTextView.requestFocus();
    }
  }
  private void onSearchClicked() {
    updateViewsVisibility(false);
    mQueryTextView.requestFocus();
    if (mOnSearchClickListener != null) mOnSearchClickListener.onClick(this);
  }
  /** a suggestion: the listener, else its intent (the suggestion columns of SearchManager) */
  private void onItemClicked(int position) {
    if (mOnSuggestionListener != null && mOnSuggestionListener.onSuggestionClick(position)) return;
    if (mSuggestionsAdapter != null) {
      android.database.Cursor c = mSuggestionsAdapter.getCursor();
      if (c != null && c.moveToPosition(position)) {
        Intent intent = suggestionIntent(c);
        if (intent != null) { try { getContext().startActivity(intent); } catch (RuntimeException e) { android.util.Log.e("SearchView", "Failed launch activity: " + intent, e); } }
      }
    }
    mQueryTextView.dismissDropDown();
    if (mOnSubmitted != null) mOnSubmitted.run();
  }
  private static String column(android.database.Cursor c, String name) {
    int i = c.getColumnIndex(name);
    if (i < 0) return null;
    try { return c.getString(i); } catch (RuntimeException e) { return null; }
  }
  private Intent suggestionIntent(android.database.Cursor c) {
    String action = column(c, SearchManager.SUGGEST_COLUMN_INTENT_ACTION);
    if (action == null && mSearchable != null) action = mSearchable.getSuggestIntentAction();
    if (action == null) action = Intent.ACTION_SEARCH;
    String data = column(c, SearchManager.SUGGEST_COLUMN_INTENT_DATA);
    if (data == null && mSearchable != null) data = mSearchable.getSuggestIntentData();
    if (data != null) {
      String id = column(c, SearchManager.SUGGEST_COLUMN_INTENT_DATA_ID);
      if (id != null) data = data + "/" + android.net.Uri.encode(id);
    }
    String query = column(c, SearchManager.SUGGEST_COLUMN_QUERY);
    String extraData = column(c, SearchManager.SUGGEST_COLUMN_INTENT_EXTRA_DATA);
    Intent intent = new Intent(action);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    if (data != null) intent.setData(android.net.Uri.parse(data));
    intent.putExtra(SearchManager.USER_QUERY, mUserQuery != null ? mUserQuery.toString() : "");
    if (query != null) intent.putExtra(SearchManager.QUERY, query);
    if (extraData != null) intent.putExtra(SearchManager.EXTRA_DATA_KEY, extraData);
    if (mAppSearchData != null) intent.putExtra(SearchManager.APP_DATA, mAppSearchData);
    if (mSearchable != null) intent.setComponent(mSearchable.getSearchActivity());
    return intent;
  }
  @Override public void onActionViewExpanded() {
    if (mExpandedInActionView) return;
    mExpandedInActionView = true;
    mCollapsedImeOptions = mQueryTextView.getImeOptions();
    mQueryTextView.setImeOptions(mCollapsedImeOptions | EditorInfo.IME_FLAG_NO_FULLSCREEN);
    mQueryTextView.setText("");
    setIconified(false);
  }
  @Override public void onActionViewCollapsed() {
    setQuery("", false);
    clearFocus();
    updateViewsVisibility(true);
    mQueryTextView.setImeOptions(mCollapsedImeOptions);
    mExpandedInActionView = false;
  }
  static class SavedState extends BaseSavedState {
    boolean isIconified;
    SavedState(Parcelable superState) { super(superState); }
    SavedState(Parcel source) { super(source); isIconified = source.readInt() != 0; }
    @Override public void writeToParcel(Parcel dest, int flags) { super.writeToParcel(dest, flags); dest.writeInt(isIconified ? 1 : 0); }
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
      public SavedState createFromParcel(Parcel in) { return new SavedState(in); }
      public SavedState[] newArray(int size) { return new SavedState[size]; }
    };
  }
  @Override protected Parcelable onSaveInstanceState() {
    SavedState ss = new SavedState(super.onSaveInstanceState());
    ss.isIconified = isIconified();
    return ss;
  }
  @Override protected void onRestoreInstanceState(Parcelable state) {
    if (!(state instanceof SavedState)) { super.onRestoreInstanceState(state); return; }
    SavedState ss = (SavedState) state;
    super.onRestoreInstanceState(ss.getSuperState());
    updateViewsVisibility(ss.isIconified);
    requestLayout();
  }
  @Override public CharSequence getAccessibilityClassName() { return SearchView.class.getName(); }

  /** the query field: completion does not replace the query (a suggestion's click is handled by the SearchView) */
  static final class SearchAutoComplete extends AutoCompleteTextView {
    final SearchView mSearchView;
    SearchAutoComplete(Context context, SearchView sv) { super(context); mSearchView = sv; }
    @Override protected void replaceText(CharSequence text) {}
    @Override public boolean enoughToFilter() { return getThreshold() <= 0 || super.enoughToFilter(); }
  }

  /** the search (magnifier), close (cross) and submit (arrow) glyphs */
  static final class Glyph extends Drawable {
    static final int SEARCH = 0, CLOSE = 1, GO = 2;
    final int kind, size;
    final Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
    Glyph(int kind, int color, int size) { this.kind = kind; this.size = size; p.setColor(color); p.setStyle(Paint.Style.STROKE); p.setStrokeCap(Paint.Cap.ROUND); }
    @Override public void draw(Canvas c) {
      Rect b = getBounds();
      float cx = b.exactCenterX(), cy = b.exactCenterY(), u = size / 24f;
      p.setStrokeWidth(2 * u);
      if (!isStateEnabled()) p.setAlpha(0x60); else p.setAlpha(0xff);
      switch (kind) {
        case SEARCH: c.drawCircle(cx - 2 * u, cy - 2 * u, 5.5f * u, p); c.drawLine(cx + 2 * u, cy + 2 * u, cx + 7 * u, cy + 7 * u, p); break;
        case CLOSE: c.drawLine(cx - 6 * u, cy - 6 * u, cx + 6 * u, cy + 6 * u, p); c.drawLine(cx + 6 * u, cy - 6 * u, cx - 6 * u, cy + 6 * u, p); break;
        default: c.drawLine(cx - 7 * u, cy, cx + 7 * u, cy, p); c.drawLine(cx + 7 * u, cy, cx + 1 * u, cy - 6 * u, p); c.drawLine(cx + 7 * u, cy, cx + 1 * u, cy + 6 * u, p);
      }
    }
    private boolean isStateEnabled() { for (int s : getState()) if (s == android.R.attr.state_enabled) return true; return getState().length == 0; }
    @Override public boolean isStateful() { return true; }
    @Override protected boolean onStateChange(int[] state) { invalidateSelf(); return true; }
    @Override public void setAlpha(int alpha) {}
    @Override public void setColorFilter(ColorFilter cf) { p.setColorFilter(cf); }
    @Override public int getOpacity() { return PixelFormat.TRANSLUCENT; }
    @Override public int getIntrinsicWidth() { return size; }
    @Override public int getIntrinsicHeight() { return size; }
  }
}
