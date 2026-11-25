package android.app;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import a2w.Values;

/** AlertDialog with the old (pre-Holo) look: dark panel, title, message, list and buttons. */
public class AlertDialog extends Dialog implements DialogInterface {
  public static final int THEME_TRADITIONAL = 1, THEME_HOLO_DARK = 2, THEME_HOLO_LIGHT = 3, THEME_DEVICE_DEFAULT_DARK = 4, THEME_DEVICE_DEFAULT_LIGHT = 5;
  CharSequence mTitle, mMessage;
  View mView, mCustomTitle;
  final CharSequence[] mButtonText = new CharSequence[3];
  final OnClickListener[] mButtonListener = new OnClickListener[3];
  final Button[] mButtons = new Button[3];
  // list: an adapter (items, cursor or the app's), single or multiple choice
  ListAdapter mAdapter;
  OnClickListener mItemsListener;
  OnMultiChoiceClickListener mMultiListener;
  int mCheckedItem = -1;
  boolean mSingleChoice, mMultiChoice;
  boolean[] mCheckedItems;
  String mIsCheckedColumn;
  AdapterView.OnItemSelectedListener mOnItemSelectedListener;
  ListView mListView;
  Drawable mIcon;
  TextView mMessageView, mTitleView;
  LinearLayout mPanel;

  protected AlertDialog(Context c) { this(c, 0); }
  protected AlertDialog(Context c, int theme) { super(c, theme); }
  protected AlertDialog(Context c, boolean cancelable, OnCancelListener l) { this(c); setCancelable(cancelable); setOnCancelListener(l); }
  static float dp(float v) { return v * Values.METRICS.density; }

  @Override protected void onCreate(android.os.Bundle b) {
    Context c = getContext();
    LinearLayout panel = new LinearLayout(c);
    mPanel = panel;
    panel.setOrientation(LinearLayout.VERTICAL);
    int pad = (int) dp(10);
    panel.setPadding(pad, pad, pad, pad);
    panel.setMinimumWidth((int) dp(280));
    if (mCustomTitle != null) {
      if (mCustomTitle.getParent() instanceof ViewGroup) ((ViewGroup) mCustomTitle.getParent()).removeView(mCustomTitle);
      panel.addView(mCustomTitle, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    } else if (!TextUtils.isEmpty(mTitle)) {
      LinearLayout tl = new LinearLayout(c);
      tl.setOrientation(LinearLayout.HORIZONTAL);
      tl.setGravity(Gravity.CENTER_VERTICAL);
      if (mIcon != null) { ImageView iv = new ImageView(c); iv.setImageDrawable(mIcon); tl.addView(iv, new LinearLayout.LayoutParams((int) dp(32), (int) dp(32))); }
      mTitleView = new TextView(c);
      mTitleView.setText(mTitle);
      mTitleView.setTextColor(0xffffffff);
      mTitleView.setTextSize(20);
      mTitleView.setPadding((int) dp(6), 0, 0, 0);
      tl.addView(mTitleView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
      panel.addView(tl, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
      View div = new View(c);
      div.setBackgroundColor(0xff9a9a9a);
      LinearLayout.LayoutParams dlp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Math.max(1, (int) dp(1)));
      dlp.topMargin = (int) dp(8); dlp.bottomMargin = (int) dp(6);
      panel.addView(div, dlp);
    }
    if (!TextUtils.isEmpty(mMessage) || mAdapter == null && mView == null) {
      mMessageView = new TextView(c);
      mMessageView.setText(mMessage);
      mMessageView.setTextColor(0xffdddddd);
      mMessageView.setTextSize(16);
      mMessageView.setPadding((int) dp(4), (int) dp(4), (int) dp(4), (int) dp(8));
      if (TextUtils.isEmpty(mMessage)) mMessageView.setVisibility(View.GONE);
      ScrollView sv = new ScrollView(c);
      sv.addView(mMessageView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
      panel.addView(sv, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
    }
    if (mAdapter != null) {
      ListView lv = mListView = new ListView(c);
      lv.setAdapter(mAdapter);
      if (mMultiChoice) lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
      else if (mSingleChoice) lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
      if (mMultiChoice && mCheckedItems != null) for (int i = 0; i < mCheckedItems.length; i++) lv.setItemChecked(i, mCheckedItems[i]);
      if (mSingleChoice && mCheckedItem >= 0) { lv.setItemChecked(mCheckedItem, true); lv.setSelection(mCheckedItem); }
      lv.setOnItemClickListener((parent, view, position, id) -> {
        if (mMultiChoice) {
          boolean on = lv.isItemChecked(position);
          if (mCheckedItems != null && position < mCheckedItems.length) mCheckedItems[position] = on;
          if (mMultiListener != null) mMultiListener.onClick(this, position, on);
        } else {
          if (mSingleChoice) mCheckedItem = position;
          if (mItemsListener != null) mItemsListener.onClick(this, position);
          if (!mSingleChoice) dismiss();
        }
      });
      if (mOnItemSelectedListener != null) lv.setOnItemSelectedListener(mOnItemSelectedListener);
      panel.addView(lv, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
    }
    if (mView != null) {
      if (mView.getParent() instanceof ViewGroup) ((ViewGroup) mView.getParent()).removeView(mView);
      panel.addView(mView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
    }
    LinearLayout bar = new LinearLayout(c);
    bar.setOrientation(LinearLayout.HORIZONTAL);
    bar.setPadding(0, (int) dp(6), 0, 0);
    int[] order = {0, 2, 1};
    int[] which = {BUTTON_POSITIVE, BUTTON_NEUTRAL, BUTTON_NEGATIVE};
    boolean any = false;
    for (int k : order) {
      final int w = which[k], idx = k;
      Button bt = mButtons[k] = new Button(c);
      bt.setText(mButtonText[k]);
      bt.setOnClickListener(v -> { if (mButtonListener[idx] != null) mButtonListener[idx].onClick(this, w); dismiss(); });
      if (mButtonText[k] == null) bt.setVisibility(View.GONE); else any = true;
      LinearLayout.LayoutParams blp = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
      blp.leftMargin = blp.rightMargin = (int) dp(2);
      bar.addView(bt, blp);
    }
    if (!any) bar.setVisibility(View.GONE);
    panel.addView(bar, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    FrameLayout.LayoutParams plp = new FrameLayout.LayoutParams((int) dp(320), ViewGroup.LayoutParams.WRAP_CONTENT);
    setContentView(panel, plp);
  }
  static int index(int which) { return which == BUTTON_POSITIVE ? 0 : which == BUTTON_NEGATIVE ? 1 : 2; }
  public Button getButton(int which) { create(); return mButtons[index(which)]; }
  public ListView getListView() { return mListView; }
  @Override public void setTitle(CharSequence t) { mTitle = t; if (mTitleView != null) mTitleView.setText(t); }
  public void setCustomTitle(View v) { mCustomTitle = v; }
  public void setMessage(CharSequence m) {
    mMessage = m;
    if (mMessageView != null) { mMessageView.setText(m); mMessageView.setVisibility(TextUtils.isEmpty(m) ? View.GONE : View.VISIBLE); }
  }
  public void setView(View v) { mView = v; }
  public void setView(View v, int l, int t, int r, int b) { mView = v; if (v != null) v.setPadding(l, t, r, b); }
  public void setButton(int which, CharSequence text, OnClickListener l) {
    int i = index(which);
    mButtonText[i] = text; mButtonListener[i] = l;
    if (mButtons[i] != null) { mButtons[i].setText(text); mButtons[i].setVisibility(text == null ? View.GONE : View.VISIBLE); }
  }
  public void setButton(int which, CharSequence text, android.os.Message msg) { setButton(which, text, msg == null ? null : (OnClickListener) (d, w) -> msg.sendToTarget()); }
  public void setButton(int which, CharSequence text, Drawable icon, OnClickListener l) { setButton(which, text, l); }
  @Deprecated public void setButton(CharSequence text, OnClickListener l) { setButton(BUTTON_POSITIVE, text, l); }
  @Deprecated public void setButton2(CharSequence text, OnClickListener l) { setButton(BUTTON_NEGATIVE, text, l); }
  @Deprecated public void setButton3(CharSequence text, OnClickListener l) { setButton(BUTTON_NEUTRAL, text, l); }
  @Deprecated public void setButton(CharSequence text, android.os.Message msg) { setButton(BUTTON_POSITIVE, text, msg); }
  @Deprecated public void setButton2(CharSequence text, android.os.Message msg) { setButton(BUTTON_NEGATIVE, text, msg); }
  @Deprecated public void setButton3(CharSequence text, android.os.Message msg) { setButton(BUTTON_NEUTRAL, text, msg); }
  public void setIcon(int id) { mIcon = id == 0 ? null : getContext().getResources().getDrawable(id); }
  public void setIcon(Drawable d) { mIcon = d; }
  public void setIconAttribute(int attrId) {}
  public void setInverseBackgroundForced(boolean f) {}

  /** rows of a list: the framework's select_dialog layouts (CheckedTextView with a check mark for choices) */
  static int rowLayout(boolean single, boolean multi) { return multi ? android.R.layout.select_dialog_multichoice : single ? android.R.layout.select_dialog_singlechoice : android.R.layout.select_dialog_item; }
  static ListAdapter itemsAdapter(Context c, CharSequence[] items, boolean single, boolean multi) {
    return new ArrayAdapter<CharSequence>(c, rowLayout(single, multi), android.R.id.text1, items) {
      @Override public View getView(int p, View convert, ViewGroup parent) {
        View v = super.getView(p, convert, parent);
        if (v instanceof TextView) ((TextView) v).setTextColor(0xffffffff);
        return v;
      }
    };
  }
  static ListAdapter cursorAdapter(Context c, Cursor cursor, String labelColumn, String checkedColumn, boolean single, boolean multi) {
    return new ResourceCursorAdapter(c, rowLayout(single, multi), cursor, false) {
      @Override public void bindView(View v, Context ctx, Cursor cur) {
        TextView t = (TextView) v.findViewById(android.R.id.text1);
        t.setText(cur.getString(cur.getColumnIndexOrThrow(labelColumn)));
        t.setTextColor(0xffffffff);
        if (checkedColumn != null && v instanceof CheckedTextView) ((CheckedTextView) v).setChecked(cur.getInt(cur.getColumnIndexOrThrow(checkedColumn)) == 1);
      }
    };
  }

  public static class Builder {
    final Context mContext;
    final int mTheme;
    CharSequence title, message;
    Drawable icon;
    View view, customTitle;
    int viewLayout;
    final CharSequence[] text = new CharSequence[3];
    final OnClickListener[] listeners = new OnClickListener[3];
    CharSequence[] items;
    ListAdapter adapter;
    Cursor cursor;
    String labelColumn, checkedColumn;
    OnClickListener itemsListener;
    OnMultiChoiceClickListener multiListener;
    boolean[] checkedItems;
    int checkedItem = -1;
    boolean singleChoice, multiChoice, cancelable = true;
    OnCancelListener cancelListener;
    OnDismissListener dismissListener;
    OnKeyListener keyListener;
    AdapterView.OnItemSelectedListener selectedListener;
    public Builder(Context c) { this(c, 0); }
    public Builder(Context c, int theme) { mContext = c; mTheme = theme; }
    public Context getContext() { return mContext; }
    public Builder setTitle(int id) { title = mContext.getText(id); return this; }
    public Builder setTitle(CharSequence t) { title = t; return this; }
    public Builder setCustomTitle(View v) { customTitle = v; return this; }
    public Builder setMessage(int id) { message = mContext.getText(id); return this; }
    public Builder setMessage(CharSequence m) { message = m; return this; }
    public Builder setIcon(int id) { icon = id == 0 ? null : mContext.getResources().getDrawable(id); return this; }
    public Builder setIcon(Drawable d) { icon = d; return this; }
    public Builder setIconAttribute(int attrId) { return this; }
    public Builder setPositiveButton(int id, OnClickListener l) { return setPositiveButton(mContext.getText(id), l); }
    public Builder setPositiveButton(CharSequence t, OnClickListener l) { text[0] = t; listeners[0] = l; return this; }
    public Builder setPositiveButtonIcon(Drawable d) { return this; }
    public Builder setNegativeButton(int id, OnClickListener l) { return setNegativeButton(mContext.getText(id), l); }
    public Builder setNegativeButton(CharSequence t, OnClickListener l) { text[1] = t; listeners[1] = l; return this; }
    public Builder setNegativeButtonIcon(Drawable d) { return this; }
    public Builder setNeutralButton(int id, OnClickListener l) { return setNeutralButton(mContext.getText(id), l); }
    public Builder setNeutralButton(CharSequence t, OnClickListener l) { text[2] = t; listeners[2] = l; return this; }
    public Builder setNeutralButtonIcon(Drawable d) { return this; }
    public Builder setCancelable(boolean c) { cancelable = c; return this; }
    public Builder setOnCancelListener(OnCancelListener l) { cancelListener = l; return this; }
    public Builder setOnDismissListener(OnDismissListener l) { dismissListener = l; return this; }
    public Builder setOnKeyListener(OnKeyListener l) { keyListener = l; return this; }
    public Builder setItems(int id, OnClickListener l) { return setItems(mContext.getResources().getTextArray(id), l); }
    public Builder setItems(CharSequence[] i, OnClickListener l) { items = i; itemsListener = l; return this; }
    public Builder setAdapter(ListAdapter a, OnClickListener l) { adapter = a; itemsListener = l; return this; }
    public Builder setCursor(Cursor c, OnClickListener l, String label) { cursor = c; labelColumn = label; itemsListener = l; return this; }
    public Builder setMultiChoiceItems(int id, boolean[] checked, OnMultiChoiceClickListener l) { return setMultiChoiceItems(mContext.getResources().getTextArray(id), checked, l); }
    public Builder setMultiChoiceItems(CharSequence[] i, boolean[] checked, OnMultiChoiceClickListener l) { items = i; checkedItems = checked; multiListener = l; multiChoice = true; return this; }
    public Builder setMultiChoiceItems(Cursor c, String isChecked, String label, OnMultiChoiceClickListener l) { cursor = c; checkedColumn = isChecked; labelColumn = label; multiListener = l; multiChoice = true; return this; }
    public Builder setSingleChoiceItems(int id, int checked, OnClickListener l) { return setSingleChoiceItems(mContext.getResources().getTextArray(id), checked, l); }
    public Builder setSingleChoiceItems(CharSequence[] i, int checked, OnClickListener l) { items = i; itemsListener = l; checkedItem = checked; singleChoice = true; return this; }
    public Builder setSingleChoiceItems(Cursor c, int checked, String label, OnClickListener l) { cursor = c; labelColumn = label; itemsListener = l; checkedItem = checked; singleChoice = true; return this; }
    public Builder setSingleChoiceItems(ListAdapter a, int checked, OnClickListener l) { adapter = a; itemsListener = l; checkedItem = checked; singleChoice = true; return this; }
    public Builder setOnItemSelectedListener(AdapterView.OnItemSelectedListener l) { selectedListener = l; return this; }
    public Builder setView(View v) { view = v; viewLayout = 0; return this; }
    public Builder setView(int layoutResId) { view = null; viewLayout = layoutResId; return this; }
    @Deprecated public Builder setInverseBackgroundForced(boolean f) { return this; }
    public Builder setRecycleOnMeasureEnabled(boolean e) { return this; }
    public AlertDialog create() {
      AlertDialog d = new AlertDialog(mContext, mTheme);
      d.mTitle = title; d.mMessage = message; d.mIcon = icon; d.mCustomTitle = customTitle;
      d.mView = view != null ? view : viewLayout != 0 ? LayoutInflater.from(mContext).inflate(viewLayout, null) : null;
      for (int i = 0; i < 3; i++) { d.mButtonText[i] = text[i]; d.mButtonListener[i] = listeners[i]; }
      if (adapter != null) d.mAdapter = adapter;
      else if (cursor != null) d.mAdapter = cursorAdapter(mContext, cursor, labelColumn, checkedColumn, singleChoice, multiChoice);
      else if (items != null) d.mAdapter = itemsAdapter(mContext, items, singleChoice, multiChoice);
      d.mItemsListener = itemsListener; d.mMultiListener = multiListener; d.mCheckedItem = checkedItem;
      d.mSingleChoice = singleChoice; d.mMultiChoice = multiChoice; d.mCheckedItems = checkedItems; d.mOnItemSelectedListener = selectedListener;
      d.setCancelable(cancelable);
      if (cancelable) d.setCanceledOnTouchOutside(false);
      d.setOnCancelListener(cancelListener);
      if (dismissListener != null) d.setOnDismissListener(dismissListener);
      if (keyListener != null) d.setOnKeyListener(keyListener);
      return d;
    }
    public AlertDialog show() { AlertDialog d = create(); d.show(); return d; }
  }
}
