package android.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.text.*;
import android.text.method.*;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.*;
import android.view.inputmethod.EditorInfo;
import a2w.Attrs;
import a2w.Styles;
import a2w.Values;
import java.util.ArrayList;

/** TextView: single/multi-line text with Android's StaticLayout metrics (font padding, spacing, gravity). */
public class TextView extends View implements ViewTreeObserver.OnPreDrawListener {
  public enum BufferType { NORMAL, SPANNABLE, EDITABLE }
  public interface OnEditorActionListener { boolean onEditorAction(TextView v, int actionId, KeyEvent event); }
  static final int LINES = 1, EMS = 1, PIXELS = 2;

  CharSequence mText = "";
  CharSequence mHint;
  BufferType mBufferType = BufferType.NORMAL;
  ColorStateList mTextColor, mHintTextColor;
  int mCurTextColor = 0xffbebebe, mCurHintTextColor = 0xff808080;
  final TextPaint mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
  int mGravity = Gravity.TOP | Gravity.LEFT;
  boolean mSingleLine, mHorizontallyScrolling, mIncludePad = true, mEditable, mCursorVisible = true, mFreezesText;
  int mMaximum = Integer.MAX_VALUE, mMaxMode = LINES, mMinimum = 0, mMinMode = LINES;
  int mMaxWidthValue = Integer.MAX_VALUE, mMaxWidthMode = PIXELS, mMinWidthValue = 0, mMinWidthMode = PIXELS;
  int mOldMaximum = Integer.MAX_VALUE, mOldMaxMode = LINES;
  TextUtils.TruncateAt mEllipsize;
  float mSpacingMult = 1, mSpacingAdd;
  float mShadowRadius, mShadowDx, mShadowDy; int mShadowColor;
  int mMarqueeRepeatLimit = 3;
  ArrayList<TextWatcher> mListeners;
  OnEditorActionListener mEditorListener;
  TransformationMethod mTransformation;
  int mInputType = EditorInfo.TYPE_CLASS_TEXT, mImeOptions;
  InputFilter[] mFilters = new InputFilter[0];
  Drawable mDrawableLeft, mDrawableTop, mDrawableRight, mDrawableBottom;
  int mDrawablePadding;
  boolean mHideText;
  int mMaxLength = -1;
  // layout
  Lines mLayout, mHintLayout;
  private boolean mMarquee; private long mMarqueeStart; private float mMarqueeScroll;
  private boolean mPreDrawRegistered;

  public TextView(Context c) { this(c, null); }
  public TextView(Context c, AttributeSet a) { this(c, a, 0); }
  public TextView(Context c, AttributeSet a, int defStyle) { this(c, a, defStyle, "TextAppearance.Small"); }
  public TextView(Context c, AttributeSet a, int defStyleAttr, int defStyleRes) { this(c, a, defStyleAttr); }
  /** web: fw names the framework style holding this widget's defaults (Widget.Button, Widget.EditText...) */
  protected TextView(Context c, AttributeSet a, int defStyle, String fw) {
    super(c, withDefaults(a, fw), defStyle);
    mTextPaint.density = a2w.Screen.DENSITY;
    mTextPaint.setTextSize(Values.dimension("14sp"));
    applyTextAttrs(mCtorAttrs instanceof Attrs ? (Attrs) mCtorAttrs : Attrs.defaultsOnly(Styles.framework(fw)));
  }
  static AttributeSet withDefaults(AttributeSet a, String fw) {
    if (a instanceof Attrs) return ((Attrs) a).withDefaults(Styles.framework(fw));
    if (a == null) return Attrs.defaultsOnly(Styles.framework(fw));
    return a;
  }

  void applyTextAttrs(Attrs a) {
    String v;
    if ((v = a.a("textSize")) != null) mTextPaint.setTextSize(dim(v));
    if ((v = a.a("textColor")) != null) mTextColor = colorList(v);
    if ((v = a.a("textAllCaps")) != null) mAllCaps = Values.parseBool(v, false);
    if ((v = a.a("textColorHint")) != null) mHintTextColor = colorList(v);
    int style = 0;
    if ((v = a.a("textStyle")) != null) { if (v.contains("bold")) style |= Typeface.BOLD; if (v.contains("italic")) style |= Typeface.ITALIC; }
    Typeface tf = null;
    if ((v = a.a("typeface")) != null) tf = v.equals("serif") ? Typeface.SERIF : v.equals("monospace") ? Typeface.MONOSPACE : Typeface.SANS_SERIF;
    if ((v = a.a("fontFamily")) != null) tf = Typeface.create(v, 0);
    setTypeface(tf, style);
    if ((v = a.a("gravity")) != null) setGravity(Values.gravity(v));
    if ((v = a.a("shadowColor")) != null) mShadowColor = Values.isRef(v) ? getResources().getColor(Values.refId(v)) : Values.parseColor(v);
    if ((v = a.a("shadowDx")) != null) mShadowDx = Values.parseFloat(v, 0);
    if ((v = a.a("shadowDy")) != null) mShadowDy = Values.parseFloat(v, 0);
    if ((v = a.a("shadowRadius")) != null) mShadowRadius = Values.parseFloat(v, 0);
    if (mShadowColor != 0 || mShadowRadius != 0) mTextPaint.setShadowLayer(mShadowRadius, mShadowDx, mShadowDy, mShadowColor);
    if ((v = a.a("lineSpacingExtra")) != null) mSpacingAdd = dim(v);
    if ((v = a.a("lineSpacingMultiplier")) != null) mSpacingMult = Values.parseFloat(v, 1);
    if ((v = a.a("includeFontPadding")) != null) mIncludePad = v.equals("true");
    if ((v = a.a("maxLines")) != null) setMaxLines(Values.parseInt(v, Integer.MAX_VALUE));
    if ((v = a.a("minLines")) != null) setMinLines(Values.parseInt(v, 0));
    if ((v = a.a("lines")) != null) setLines(Values.parseInt(v, 1));
    if ((v = a.a("maxHeight")) != null) setMaxHeight((int) dim(v));
    if ((v = a.a("height")) != null) setHeight((int) dim(v));
    if ((v = a.a("maxWidth")) != null) setMaxWidth((int) dim(v));
    if ((v = a.a("width")) != null) setWidth((int) dim(v));
    if ((v = a.a("minWidth")) != null) setMinWidth((int) dim(v));
    if ((v = a.a("ems")) != null) setEms(Values.parseInt(v, 0));
    if ((v = a.a("ellipsize")) != null) {
      switch (v) { case "start": mEllipsize = TextUtils.TruncateAt.START; break; case "middle": mEllipsize = TextUtils.TruncateAt.MIDDLE; break;
        case "end": mEllipsize = TextUtils.TruncateAt.END; break; case "marquee": mEllipsize = TextUtils.TruncateAt.MARQUEE; break; default: mEllipsize = null; }
    }
    if ((v = a.a("marqueeRepeatLimit")) != null) mMarqueeRepeatLimit = v.equals("marquee_forever") ? -1 : Values.parseInt(v, 3);
    if ((v = a.a("scrollHorizontally")) != null) mHorizontallyScrolling = v.equals("true");
    if ((v = a.a("maxLength")) != null) { mMaxLength = Values.parseInt(v, -1); if (mMaxLength >= 0) mFilters = new InputFilter[] {new InputFilter.LengthFilter(mMaxLength)}; }
    if ((v = a.a("inputType")) != null) mInputType = inputType(v);
    if ((v = a.a("password")) != null && v.equals("true")) mInputType = EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_PASSWORD;
    if ((v = a.a("numeric")) != null) mInputType = EditorInfo.TYPE_CLASS_NUMBER;
    if ((v = a.a("phoneNumber")) != null && v.equals("true")) mInputType = EditorInfo.TYPE_CLASS_PHONE;
    if ((v = a.a("imeOptions")) != null) mImeOptions = imeOptions(v);
    if ((v = a.a("editable")) != null && v.equals("false")) mEditable = false;
    if ((v = a.a("cursorVisible")) != null) mCursorVisible = v.equals("true");
    if ((v = a.a("drawableLeft")) != null) mDrawableLeft = drawable(v);
    if ((v = a.a("drawableTop")) != null) mDrawableTop = drawable(v);
    if ((v = a.a("drawableRight")) != null) mDrawableRight = drawable(v);
    if ((v = a.a("drawableBottom")) != null) mDrawableBottom = drawable(v);
    if ((v = a.a("drawablePadding")) != null) mDrawablePadding = (int) dim(v);
    setCompoundDrawablesWithIntrinsicBounds(mDrawableLeft, mDrawableTop, mDrawableRight, mDrawableBottom);
    boolean single = false;
    if ((v = a.a("singleLine")) != null) single = v.equals("true");
    else if (a.a("inputType") != null && (mInputType & EditorInfo.TYPE_MASK_CLASS) != 0 && (mInputType & EditorInfo.TYPE_TEXT_FLAG_MULTI_LINE) == 0) single = true;
    else if ("true".equals(a.a("password"))) single = true;
    if (single) {
      setSingleLine(true);
      if (a.a("ellipsize") == null && !(this instanceof EditText)) mEllipsize = TextUtils.TruncateAt.END;
    }
    if (isPassword()) mTransformation = PasswordTransformationMethod.getInstance();
    if ((v = a.a("textColorHighlight")) != null) {}
    if ((v = a.a("hint")) != null) mHint = text(v);
    CharSequence t = "";
    if ((v = a.a("text")) != null) t = text(v);
    setText(t, mBufferType);
    updateTextColors();
  }
  CharSequence text(String v) { return Values.isRef(v) ? getResources().getText(Values.refId(v)) : v; }
  Drawable drawable(String v) { if (Values.isRef(v)) { int id = Values.refId(v); return id == 0 ? null : getResources().getDrawable(id); } return null; }
  ColorStateList colorList(String v) {
    if (Values.isRef(v)) { int id = Values.refId(v); return id == 0 ? null : getResources().getColorStateList(id); }
    return ColorStateList.valueOf(Values.parseColor(v));
  }
  static int inputType(String v) { return a2w.Values.inputType(v); }
  static int imeOptions(String v) { return a2w.Values.imeOptions(v); }

  // ---------------------------------------------------------------- text
  public final void setText(CharSequence t) { setText(t, mBufferType); }
  public void setText(CharSequence text, BufferType type) {
    if (text == null) text = "";
    CharSequence old = mText;
    if (mListeners != null) for (TextWatcher w : new ArrayList<>(mListeners)) w.beforeTextChanged(old, 0, old.length(), text.length());
    if (type == BufferType.EDITABLE || mEditable) {
      SpannableStringBuilder e = text instanceof SpannableStringBuilder && text != old ? (SpannableStringBuilder) text : new SpannableStringBuilder(text);
      InputFilter[] filters = editFilters();
      if (filters.length > 0) {
        CharSequence f = text;
        for (InputFilter fl : filters) { CharSequence r = fl.filter(f, 0, f.length(), new SpannedString(""), 0, 0); if (r != null) f = r; }
        e = new SpannableStringBuilder(f);
      }
      e.setFilters(filters);
      mText = e;
      mBufferType = BufferType.EDITABLE;
    } else {
      if (type == BufferType.SPANNABLE || mAutoLinkMask != 0) { text = mSpannableFactory.newSpannable(text); type = BufferType.SPANNABLE; }
      mText = text;
      mBufferType = type;
    }
    if (mAutoLinkMask != 0 && mText instanceof android.text.Spannable && android.text.util.Linkify.addLinks((android.text.Spannable) mText, mAutoLinkMask)
        && mLinksClickable && !(mText instanceof Editable)) setMovementMethod(android.text.method.LinkMovementMethod.getInstance());
    mLayout = null;
    checkForRelayout();
    onTextChanged(mText, 0, old.length(), mText.length());
    if (mListeners != null) {
      for (TextWatcher w : new ArrayList<>(mListeners)) w.onTextChanged(mText, 0, old.length(), mText.length());
      if (mText instanceof Editable) for (TextWatcher w : new ArrayList<>(mListeners)) w.afterTextChanged((Editable) mText);
    }
    a2w.TextInput.textChanged(this);
  }
  public final void setText(char[] t, int start, int len) { setText(new String(t, start, len)); }
  public final void setText(int resid) { setText(getResources().getText(resid)); }
  public final void setText(int resid, BufferType type) { setText(getResources().getText(resid), type); }
  public final void setTextKeepState(CharSequence t) { setTextKeepState(t, mBufferType); }
  public final void setTextKeepState(CharSequence text, BufferType type) {
    int start = getSelectionStart(), end = getSelectionEnd(), len = text == null ? 0 : text.length();
    setText(text, type);
    if ((start >= 0 || end >= 0) && mText instanceof android.text.Spannable)
      android.text.Selection.setSelection((android.text.Spannable) mText, Math.max(0, Math.min(start, len)), Math.max(0, Math.min(end, len)));
  }
  public void append(CharSequence t) { append(t, 0, t.length()); }
  public void append(CharSequence t, int start, int end) {
    if (mText instanceof Editable) { ((Editable) mText).append(t, start, end); mLayout = null; checkForRelayout(); }
    else setText(mText.toString() + t.subSequence(start, end));
  }
  public CharSequence getText() { return mText; }
  public int length() { return mText.length(); }
  public Editable getEditableText() { return mText instanceof Editable ? (Editable) mText : null; }
  public CharSequence getHint() { return mHint; }
  public final void setHint(CharSequence h) { mHint = h; mHintLayout = null; if (mText.length() == 0) checkForRelayout(); }
  public final void setHint(int resid) { setHint(getResources().getText(resid)); }
  public void addTextChangedListener(TextWatcher w) { if (mListeners == null) mListeners = new ArrayList<>(); mListeners.add(w); }
  public void removeTextChangedListener(TextWatcher w) { if (mListeners != null) mListeners.remove(w); }
  public void setOnEditorActionListener(OnEditorActionListener l) { mEditorListener = l; }
  /** the default handling of an editor action: next/previous move focus (keeping the keyboard on a next text field), done/go/send/search close the keyboard */
  public void onEditorAction(int actionCode) {
    if (mEditorListener != null && mEditorListener.onEditorAction(this, actionCode, null)) return;
    if (actionCode == EditorInfo.IME_ACTION_NEXT || actionCode == EditorInfo.IME_ACTION_PREVIOUS) {
      int dir = actionCode == EditorInfo.IME_ACTION_NEXT ? FOCUS_FORWARD : FOCUS_BACKWARD;
      View v = focusSearch(dir);
      if (v != null) {
        boolean keyboard = a2w.TextInput.isActive(this);
        if (!v.requestFocus(dir)) throw new IllegalStateException("focus search returned a view that wasn't able to take focus!");
        if (keyboard && v instanceof EditText && v.isFocused()) a2w.TextInput.show(v);
      } else a2w.TextInput.hide();
      return;
    }
    if (actionCode == EditorInfo.IME_ACTION_DONE || actionCode == EditorInfo.IME_ACTION_GO || actionCode == EditorInfo.IME_ACTION_SEND || actionCode == EditorInfo.IME_ACTION_SEARCH)
      a2w.TextInput.hide();
  }
  /** web: Enter in the page's text field: the OnKeyListener first, then the editor action (unspecified: next when a view follows below, else done) */
  public void webEnter() {
    long t0 = android.os.SystemClock.uptimeMillis();
    KeyEvent kd = new KeyEvent(t0, t0, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER, 0, 0, -1, 0, KeyEvent.FLAG_SOFT_KEYBOARD | KeyEvent.FLAG_KEEP_TOUCH_MODE | KeyEvent.FLAG_EDITOR_ACTION);
    if (a2wKeyListenerConsumes(kd)) { a2wKeyListenerConsumes(KeyEvent.changeAction(kd, KeyEvent.ACTION_UP)); return; }
    int action = mImeOptions & EditorInfo.IME_MASK_ACTION;
    if (action == EditorInfo.IME_ACTION_UNSPECIFIED) action = focusSearch(FOCUS_DOWN) != null ? EditorInfo.IME_ACTION_NEXT : EditorInfo.IME_ACTION_DONE;
    else if (action == EditorInfo.IME_ACTION_NONE) action = EditorInfo.IME_ACTION_DONE;
    if (mEditorListener != null) {
      long t = android.os.SystemClock.uptimeMillis();
      if (mEditorListener.onEditorAction(this, action, new KeyEvent(t, t, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER, 0))) return;
    }
    onEditorAction(action);
  }
  public int getImeOptions() { return mImeOptions; }
  public void setImeOptions(int o) { mImeOptions = o; }
  CharSequence mImeActionLabel; int mImeActionId;
  public void setImeActionLabel(CharSequence l, int id) { mImeActionLabel = l; mImeActionId = id; }
  public CharSequence getImeActionLabel() { return mImeActionLabel; }
  public int getImeActionId() { return mImeActionId; }
  public int getInputType() { return mInputType; }
  public void setInputType(int t) {
    mInputType = t;
    mTransformation = isPassword() ? PasswordTransformationMethod.getInstance() : null;
    if ((t & EditorInfo.TYPE_TEXT_FLAG_MULTI_LINE) == 0 && (t & 15) == 1 && this instanceof EditText) {}
    mLayout = null; checkForRelayout();
  }
  public void setRawInputType(int t) { mInputType = t; }
  boolean isPassword() {
    int v = mInputType & (EditorInfo.TYPE_MASK_CLASS | EditorInfo.TYPE_MASK_VARIATION);
    return v == (EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_PASSWORD) || v == (EditorInfo.TYPE_CLASS_NUMBER | EditorInfo.TYPE_NUMBER_VARIATION_PASSWORD);
  }
  public void setTransformationMethod(TransformationMethod m) { mTransformation = m; mLayout = null; checkForRelayout(); }
  public final TransformationMethod getTransformationMethod() { return mTransformation; }
  KeyListener mKeyListener;
  public void setKeyListener(KeyListener l) {
    mKeyListener = l;
    if (l != null) mInputType = l.getInputType();
    if (mText instanceof Editable) ((Editable) mText).setFilters(editFilters());
  }
  public final KeyListener getKeyListener() { return mKeyListener; }
  /** the filters edits go through: the app's, then the key listener's (a NumberKeyListener accepts only its characters) */
  InputFilter[] editFilters() {
    if (!(mKeyListener instanceof InputFilter)) return mFilters;
    InputFilter[] f = java.util.Arrays.copyOf(mFilters, mFilters.length + 1);
    f[mFilters.length] = (InputFilter) mKeyListener;
    return f;
  }
  MovementMethod mMovement;
  public void setMovementMethod(MovementMethod m) {
    mMovement = m;
    if (m != null) { setFocusable(true); setClickable(true); setLongClickable(true); }
  }
  public final MovementMethod getMovementMethod() { return mMovement; }
  public void setLinksClickable(boolean b) { mLinksClickable = b; }
  public final boolean getLinksClickable() { return mLinksClickable; }
  boolean mLinksClickable = true;
  /** the character offset nearest to a point in the view (as getOffsetForPosition) */
  public int getOffsetForPosition(float x, float y) {
    assumeLayout();
    Lines l = mLayout;
    if (l == null || l.n == 0) return -1;
    int voffset = (mGravity & Gravity.VERTICAL_GRAVITY_MASK) != Gravity.TOP ? getVerticalOffset(false) : 0;
    float ly = y - getExtendedPaddingTop() - voffset + mScrollY;
    int line = l.getLineForVertical((int) ly);
    String text = l.text[line];
    float avail = getWidth() - getCompoundPaddingLeft() - getCompoundPaddingRight();
    final int hg = mGravity & Gravity.HORIZONTAL_GRAVITY_MASK;
    float left = hg == Gravity.CENTER_HORIZONTAL ? (avail - l.widths[line]) / 2 : hg == Gravity.RIGHT ? avail - l.widths[line] : 0;
    float lx = x - getCompoundPaddingLeft() + mScrollX - left;
    int best = 0;
    for (int i = 1; i <= text.length(); i++) {
      float w = l.styled != null ? a2w.SpanText.width(mTextPaint, l.styled, l.starts[line], Math.min(l.styled.length(), l.starts[line] + i)) : mTextPaint.measureText(text, 0, i);
      if (w > lx) { float prev = i == 1 ? 0 : (l.styled != null ? a2w.SpanText.width(mTextPaint, l.styled, l.starts[line], l.starts[line] + i - 1) : mTextPaint.measureText(text, 0, i - 1)); best = lx - prev < w - lx ? i - 1 : i; break; }
      best = i;
    }
    return l.starts[line] + best;
  }
  /** LinkMovementMethod: a tap on a ClickableSpan clicks it */
  private boolean linkTouch(MotionEvent e) {
    if (!(mMovement instanceof android.text.method.LinkMovementMethod) || !mLinksClickable || !(mText instanceof android.text.Spanned)) return false;
    int a = e.getAction();
    if (a != MotionEvent.ACTION_UP && a != MotionEvent.ACTION_DOWN) return false;
    int off = getOffsetForPosition(e.getX(), e.getY());
    if (off < 0) return false;
    android.text.Spanned sp = (android.text.Spanned) mText;
    android.text.style.ClickableSpan hit = null;
    for (Object o : sp.getSpans(off, off, Object.class)) {
      if (!(o instanceof android.text.style.ClickableSpan)) continue;
      int st = sp.getSpanStart(o), en = sp.getSpanEnd(o);
      if (off >= st && off <= en) hit = (android.text.style.ClickableSpan) o;
    }
    if (hit == null) return false;
    if (a == MotionEvent.ACTION_UP) hit.onClick(this);
    return true;
  }
  public void setFilters(InputFilter[] f) { mFilters = f == null ? new InputFilter[0] : f; if (mText instanceof Editable) ((Editable) mText).setFilters(editFilters()); }
  public InputFilter[] getFilters() { return mFilters; }
  public void setSelection(int i) { setSelection(i, i); }
  public void setSelection(int a, int b) {
    if (!(mText instanceof android.text.Spannable)) return;
    android.text.Selection.setSelection((android.text.Spannable) mText, a, b);
    onSelectionChanged(a, b);
    a2w.TextInput.selectionChanged(this);
  }
  public void selectAll() { setSelection(0, mText.length()); }
  /** the selection (the end of an editable text that has none; -1 for other text without one) */
  public int getSelectionStart() { int s = android.text.Selection.getSelectionStart(mText); return s >= 0 ? s : mEditable ? mText.length() : -1; }
  public int getSelectionEnd() { int s = android.text.Selection.getSelectionEnd(mText); return s >= 0 ? s : mEditable ? mText.length() : -1; }
  public void setCursorVisible(boolean v) { mCursorVisible = v; }
  // ---- the error indicator (setError): an icon at the end and, while focused, a popup with the message below the view
  private CharSequence mError;
  private Drawable mErrorIcon, mSavedRight;
  private boolean mShowingErrorIcon;
  private PopupWindow mErrorPopup;
  public void setError(CharSequence error) {
    if (error == null) { setError(null, null); return; }
    int size = Math.round(24 * getResources().getDisplayMetrics().density);
    Drawable icon = new ErrorIcon(size);
    icon.setBounds(0, 0, size, size);
    setError(error, icon);
  }
  public void setError(CharSequence error, Drawable icon) {
    mError = TextUtils.stringOrSpannedString(error);
    if (mShowingErrorIcon) { mShowingErrorIcon = false; Drawable keep = mSavedRight; setCompoundDrawables(mDrawableLeft, mDrawableTop, keep, mDrawableBottom); }
    mErrorIcon = icon;
    if (mError != null && icon != null) {
      mSavedRight = mDrawableRight;
      setCompoundDrawables(mDrawableLeft, mDrawableTop, icon, mDrawableBottom);
      mShowingErrorIcon = true;
    }
    if (mError == null) hideErrorPopup();
    else if (isFocused()) showErrorPopup();
  }
  public CharSequence getError() { return mError; }
  private void showErrorPopup() {
    if (getWindowToken() == null && !isAttachedToWindow()) return;
    if (mErrorPopup == null) {
      TextView msg = new TextView(getContext());
      float d = getResources().getDisplayMetrics().density;
      int p = Math.round(8 * d);
      msg.setPadding(p, p / 2, p, p / 2);
      msg.setTextColor(0xffffffff);
      msg.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, 14);
      msg.setBackground(new android.graphics.drawable.ColorDrawable(0xffb00020));
      mErrorPopup = new PopupWindow(msg, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
      mErrorPopup.setTouchable(false);
    }
    ((TextView) mErrorPopup.getContentView()).setText(mError);
    if (!mErrorPopup.isShowing()) mErrorPopup.showAsDropDown(this, Math.max(0, getWidth() - Math.round(220 * getResources().getDisplayMetrics().density)), 0);
  }
  private void hideErrorPopup() { if (mErrorPopup != null && mErrorPopup.isShowing()) mErrorPopup.dismiss(); }
  /** the default error icon: a red circle with an exclamation mark */
  static final class ErrorIcon extends Drawable {
    final int size; final android.graphics.Paint p = new android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG);
    ErrorIcon(int size) { this.size = size; }
    @Override public void draw(Canvas c) {
      android.graphics.Rect b = getBounds();
      float cx = b.exactCenterX(), cy = b.exactCenterY(), r = Math.min(b.width(), b.height()) * 0.42f;
      p.setColor(0xffd32f2f); p.setStyle(android.graphics.Paint.Style.FILL);
      c.drawCircle(cx, cy, r, p);
      p.setColor(0xffffffff);
      c.drawRect(cx - r * 0.12f, cy - r * 0.6f, cx + r * 0.12f, cy + r * 0.15f, p);
      c.drawCircle(cx, cy + r * 0.45f, r * 0.13f, p);
    }
    @Override public void setAlpha(int alpha) { p.setAlpha(alpha); }
    @Override public void setColorFilter(android.graphics.ColorFilter cf) { p.setColorFilter(cf); }
    @Override public int getOpacity() { return android.graphics.PixelFormat.TRANSLUCENT; }
    @Override public int getIntrinsicWidth() { return size; }
    @Override public int getIntrinsicHeight() { return size; }
  }

  // ---- saved state: the selection, and the text when it is frozen (getFreezesText: always for an editable text)
  /** A TextView's saved state: its selection, text (when frozen) and error. */
  public static class SavedState extends BaseSavedState {
    int selStart = -1, selEnd = -1;
    CharSequence text;
    boolean frozenWithFocus;
    CharSequence error;
    SavedState(android.os.Parcelable superState) { super(superState); }
    private SavedState(android.os.Parcel in) {
      super(in);
      selStart = in.readInt(); selEnd = in.readInt(); frozenWithFocus = in.readInt() != 0;
      text = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
      if (in.readInt() != 0) error = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
    }
    @Override public void writeToParcel(android.os.Parcel out, int flags) {
      super.writeToParcel(out, flags);
      out.writeInt(selStart); out.writeInt(selEnd); out.writeInt(frozenWithFocus ? 1 : 0);
      TextUtils.writeToParcel(text, out, flags);
      if (error == null) out.writeInt(0); else { out.writeInt(1); TextUtils.writeToParcel(error, out, flags); }
    }
    @Override public String toString() {
      String str = "TextView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " start=" + selStart + " end=" + selEnd;
      if (text != null) str += " text=" + text;
      return str + "}";
    }
    public static final android.os.Parcelable.Creator<SavedState> CREATOR = new android.os.Parcelable.Creator<SavedState>() {
      public SavedState createFromParcel(android.os.Parcel in) { return new SavedState(in); }
      public SavedState[] newArray(int size) { return new SavedState[size]; }
    };
  }
  @Override public android.os.Parcelable onSaveInstanceState() {
    android.os.Parcelable superState = super.onSaveInstanceState();
    final boolean freezesText = getFreezesText();
    int start = -1, end = -1;
    boolean hasSelection = false;
    if (mText != null) {
      start = android.text.Selection.getSelectionStart(mText); end = android.text.Selection.getSelectionEnd(mText);
      if (start >= 0 || end >= 0) hasSelection = true;
    }
    if (freezesText || hasSelection) {
      SavedState ss = new SavedState(superState);
      if (freezesText) ss.text = mText instanceof android.text.Spanned ? new android.text.SpannableStringBuilder(mText) : mText.toString();
      if (hasSelection) { ss.selStart = start; ss.selEnd = end; }
      if (isFocused() && start >= 0 && end >= 0) ss.frozenWithFocus = true;
      ss.error = getError();
      return ss;
    }
    return superState;
  }
  @Override public void onRestoreInstanceState(android.os.Parcelable state) {
    if (!(state instanceof SavedState)) { super.onRestoreInstanceState(state); return; }
    SavedState ss = (SavedState) state;
    super.onRestoreInstanceState(ss.getSuperState());
    if (ss.text != null) setText(ss.text);
    if (ss.selStart >= 0 && ss.selEnd >= 0 && mText instanceof android.text.Spannable) {
      int len = mText.length();
      if (ss.selStart <= len && ss.selEnd <= len) android.text.Selection.setSelection((android.text.Spannable) mText, ss.selStart, ss.selEnd);
    }
    if (ss.error != null) { final CharSequence error = ss.error; post(() -> setError(error)); }
  }
  public void setFreezesText(boolean f) { mFreezesText = f; }
  int mAutoLinkMask;
  public void setAutoLinkMask(int m) { mAutoLinkMask = m; }
  public final int getAutoLinkMask() { return mAutoLinkMask; }
  public boolean getFreezesText() { return mFreezesText; }
  public void setTextIsSelectable(boolean b) {}
  boolean mAllCaps;
  public void setAllCaps(boolean c) { if (mAllCaps != c) { mAllCaps = c; mLayout = null; requestLayout(); invalidate(); } }
  public boolean isAllCaps() { return mAllCaps; }
  /** subclasses' hooks (Android calls them when the text or the selection changes) */
  protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {}
  protected void onSelectionChanged(int selStart, int selEnd) {}
  public boolean isSingleLine() { return mSingleLine; }
  public int getMinLines() { return mMinMode == LINES ? mMinimum : -1; }
  public float getLineSpacingExtra() { return mSpacingAdd; }
  public float getLineSpacingMultiplier() { return mSpacingMult; }
  public boolean getIncludeFontPadding() { return mIncludePad; }
  public final ColorStateList getHintTextColors() { return mHintTextColor; }
  public final int getCurrentHintTextColor() { return mCurHintTextColor; }
  public final ColorStateList getLinkTextColors() { return mLinkColors; }
  ColorStateList mLinkColors;
  public final void setLinkTextColor(ColorStateList c) { mLinkColors = c; if (c != null) mTextPaint.linkColor = c.getDefaultColor(); invalidate(); }
  public int getShadowColor() { return mShadowColor; }
  public float getShadowDx() { return mShadowDx; }
  public float getShadowDy() { return mShadowDy; }
  public android.text.style.URLSpan[] getUrls() {
    if (!(mText instanceof android.text.Spanned)) return new android.text.style.URLSpan[0];
    ArrayList<android.text.style.URLSpan> l = new ArrayList<>();
    for (Object o : ((android.text.Spanned) mText).getSpans(0, mText.length(), Object.class)) if (o instanceof android.text.style.URLSpan) l.add((android.text.style.URLSpan) o);
    return l.toArray(new android.text.style.URLSpan[0]);
  }
  float mLetterSpacing;
  public void setLetterSpacing(float em) { mLetterSpacing = em; mTextPaint.setLetterSpacing(em); mLayout = null; requestLayout(); invalidate(); }
  public float getLetterSpacing() { return mLetterSpacing; }
  public void setLineHeight(int px) {
    int fontHeight = mTextPaint.getFontMetricsInt(null);
    if (px != fontHeight) setLineSpacing(px - fontHeight, 1f);
  }
  public void setCompoundDrawablesRelative(Drawable start, Drawable top, Drawable end, Drawable bottom) { setCompoundDrawables(start, top, end, bottom); }
  public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable start, Drawable top, Drawable end, Drawable bottom) { setCompoundDrawablesWithIntrinsicBounds(start, top, end, bottom); }
  public void setCompoundDrawablesRelativeWithIntrinsicBounds(int start, int top, int end, int bottom) { setCompoundDrawablesWithIntrinsicBounds(start, top, end, bottom); }
  public Drawable[] getCompoundDrawablesRelative() { return getCompoundDrawables(); }
  public int getCompoundPaddingStart() { return getCompoundPaddingLeft(); }
  public int getCompoundPaddingEnd() { return getCompoundPaddingRight(); }
  public int getTotalPaddingStart() { return getTotalPaddingLeft(); }
  public int getTotalPaddingEnd() { return getTotalPaddingRight(); }
  public boolean hasSelection() { int a = getSelectionStart(), b = getSelectionEnd(); return a >= 0 && a != b; }
  /** drops the IME's composing region (the page's text input commits as it goes) */
  public void clearComposingText() {
    if (mText instanceof android.text.Spannable) android.view.inputmethod.BaseInputConnection.removeComposingSpans((android.text.Spannable) mText);
  }
  public boolean isCursorVisible() { return this instanceof EditText; }
  public void setHorizontallyScrolling(boolean h) { mHorizontallyScrolling = h; mLayout = null; checkForRelayout(); }
  boolean mSelectAllOnFocus;
  public void setSelectAllOnFocus(boolean b) { mSelectAllOnFocus = b; if (b && !(mText instanceof android.text.Spannable)) setText(mText, BufferType.SPANNABLE); }
  public void setScroller(Scroller s) {}
  public void setEditableFactory(Editable.Factory f) {}

  // ---------------------------------------------------------------- appearance
  public void setTextColor(int c) { mTextColor = ColorStateList.valueOf(c); updateTextColors(); }
  public void setTextColor(ColorStateList c) { if (c == null) throw new NullPointerException(); mTextColor = c; updateTextColors(); }
  public final ColorStateList getTextColors() { return mTextColor; }
  public final int getCurrentTextColor() { return mCurTextColor; }
  public final void setHintTextColor(int c) { mHintTextColor = ColorStateList.valueOf(c); updateTextColors(); }
  public final void setHintTextColor(ColorStateList c) { mHintTextColor = c; updateTextColors(); }
  public final void setLinkTextColor(int c) {}
  int mHighlightColor = 0x6633b5e5;
  public void setHighlightColor(int c) { if (mHighlightColor != c) { mHighlightColor = c; invalidate(); } }
  public int getHighlightColor() { return mHighlightColor; }
  public void setTextAppearance(Context ctx, int resid) {
    Attrs a = new Attrs(Styles.items(resid));
    String v;
    if ((v = a.a("textSize")) != null) setRawTextSize(dim(v));
    if ((v = a.a("textColor")) != null) { ColorStateList l = colorList(v); if (l != null) setTextColor(l); }
    if ((v = a.a("textStyle")) != null) { int s = 0; if (v.contains("bold")) s |= 1; if (v.contains("italic")) s |= 2; setTypeface(getTypeface(), s); }
  }
  private void updateTextColors() {
    boolean inval = false;
    int[] st = getDrawableState();
    int c = mTextColor != null ? mTextColor.getColorForState(st, mTextColor.getDefaultColor()) : mCurTextColor;
    if (c != mCurTextColor) { mCurTextColor = c; inval = true; }
    if (mHintTextColor != null) { c = mHintTextColor.getColorForState(st, mHintTextColor.getDefaultColor()); if (c != mCurHintTextColor && mText.length() == 0) { mCurHintTextColor = c; inval = true; } else mCurHintTextColor = c; }
    if (inval) invalidate();
  }
  @Override protected void drawableStateChanged() {
    super.drawableStateChanged();
    if (mTextColor != null && mTextColor.isStateful() || mHintTextColor != null && mHintTextColor.isStateful()) updateTextColors();
    int[] s = getDrawableState();
    for (Drawable d : new Drawable[] {mDrawableLeft, mDrawableTop, mDrawableRight, mDrawableBottom}) if (d != null && d.isStateful()) d.setState(s);
  }
  public float getTextSize() { return mTextPaint.getTextSize(); }
  public void setTextSize(float size) { setTextSize(TypedValue.COMPLEX_UNIT_SP, size); }
  int mTextSizeUnit = TypedValue.COMPLEX_UNIT_SP;
  public void setTextSize(int unit, float size) { mTextSizeUnit = unit; mAutoSizeType = AUTO_SIZE_TEXT_TYPE_NONE; setRawTextSize(TypedValue.applyDimension(unit, size, Values.METRICS)); }
  public int getTextSizeUnit() { return mTextSizeUnit; }
  private void setRawTextSize(float size) {
    if (size != mTextPaint.getTextSize()) { mTextPaint.setTextSize(size); mLayout = null; mHintLayout = null; requestLayout(); invalidate(); }
  }
  public float getTextScaleX() { return mTextPaint.getTextScaleX(); }
  public void setTextScaleX(float s) { mTextPaint.setTextScaleX(s); mLayout = null; requestLayout(); invalidate(); }
  public void setTypeface(Typeface tf, int style) {
    if (style > 0) {
      if (tf == null) tf = Typeface.defaultFromStyle(style);
      else tf = Typeface.create(tf, style);
      setTypeface(tf);
      int typefaceStyle = tf != null ? tf.getStyle() : 0;
      int need = style & ~typefaceStyle;
      mTextPaint.setFakeBoldText((need & Typeface.BOLD) != 0);
      mTextPaint.setTextSkewX((need & Typeface.ITALIC) != 0 ? -0.25f : 0);
    } else {
      mTextPaint.setFakeBoldText(false);
      mTextPaint.setTextSkewX(0);
      setTypeface(tf);
    }
  }
  public void setTypeface(Typeface tf) {
    if (mTextPaint.getTypeface() != tf) { mTextPaint.setTypeface(tf); mLayout = null; mHintLayout = null; requestLayout(); invalidate(); }
  }
  public Typeface getTypeface() { return mTextPaint.getTypeface(); }
  public TextPaint getPaint() { return mTextPaint; }
  public int getPaintFlags() { return mTextPaint.getFlags(); }
  public void setPaintFlags(int f) { if (mTextPaint.getFlags() != f) { mTextPaint.setFlags(f); mLayout = null; requestLayout(); invalidate(); } }
  public void setShadowLayer(float radius, float dx, float dy, int color) {
    mTextPaint.setShadowLayer(radius, dx, dy, color);
    mShadowRadius = radius; mShadowDx = dx; mShadowDy = dy; mShadowColor = color;
    invalidate();
  }
  public float getShadowRadius() { return mShadowRadius; }
  public void setLineSpacing(float add, float mult) { mSpacingAdd = add; mSpacingMult = mult; mLayout = null; requestLayout(); invalidate(); }
  public void setIncludeFontPadding(boolean p) { mIncludePad = p; mLayout = null; requestLayout(); invalidate(); }
  public void setGravity(int g) {
    if ((g & Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK) == 0) g |= Gravity.LEFT;
    if ((g & Gravity.VERTICAL_GRAVITY_MASK) == 0) g |= Gravity.TOP;
    if (g != mGravity) { mGravity = g; mLayout = null; invalidate(); }
  }
  public int getGravity() { return mGravity; }
  public void setEllipsize(TextUtils.TruncateAt w) { if (mEllipsize != w) { mEllipsize = w; mLayout = null; requestLayout(); invalidate(); } }
  public TextUtils.TruncateAt getEllipsize() { return mEllipsize; }
  public void setMarqueeRepeatLimit(int l) { mMarqueeRepeatLimit = l; }
  public int getMarqueeRepeatLimit() { return mMarqueeRepeatLimit; }
  public void setSingleLine() { setSingleLine(true); }
  public void setSingleLine(boolean s) {
    mSingleLine = s;
    if (s) { mHorizontallyScrolling = true; mMaximum = 1; mMaxMode = LINES; mMinimum = 1; mMinMode = LINES; }
    else { mHorizontallyScrolling = false; mMaximum = Integer.MAX_VALUE; mMaxMode = LINES; mMinimum = 0; }
    mLayout = null; requestLayout(); invalidate();
  }
  public void setLines(int l) { mMaximum = mMinimum = l; mMaxMode = mMinMode = LINES; mLayout = null; requestLayout(); invalidate(); }
  public void setMaxLines(int l) { mMaximum = l; mMaxMode = LINES; mLayout = null; requestLayout(); invalidate(); }
  public int getMaxLines() { return mMaxMode == LINES ? mMaximum : -1; }
  public void setMinLines(int l) { mMinimum = l; mMinMode = LINES; requestLayout(); invalidate(); }
  public void setHeight(int px) { mMaximum = mMinimum = px; mMaxMode = mMinMode = PIXELS; requestLayout(); invalidate(); }
  public void setMaxHeight(int px) { mMaximum = px; mMaxMode = PIXELS; requestLayout(); invalidate(); }
  public void setMinHeight(int px) { mMinimum = px; mMinMode = PIXELS; requestLayout(); invalidate(); }
  public void setWidth(int px) { mMaxWidthValue = mMinWidthValue = px; mMaxWidthMode = mMinWidthMode = PIXELS; requestLayout(); invalidate(); }
  public void setMaxWidth(int px) { mMaxWidthValue = px; mMaxWidthMode = PIXELS; requestLayout(); invalidate(); }
  public void setMinWidth(int px) { mMinWidthValue = px; mMinWidthMode = PIXELS; requestLayout(); invalidate(); }
  public void setEms(int ems) { mMaxWidthValue = mMinWidthValue = ems; mMaxWidthMode = mMinWidthMode = EMS; requestLayout(); invalidate(); }
  public void setMaxEms(int ems) { mMaxWidthValue = ems; mMaxWidthMode = EMS; requestLayout(); }
  public void setMinEms(int ems) { mMinWidthValue = ems; mMinWidthMode = EMS; requestLayout(); }
  public int getLineHeight() {
    Paint.FontMetricsInt fm = mTextPaint.getFontMetricsInt();
    return Math.round((fm.descent - fm.ascent) * mSpacingMult + mSpacingAdd);
  }
  public int getLineCount() { return mLayout != null ? mLayout.n : 0; }
  public final android.text.Layout getLayout() { return mLayout; }
  public void setCompoundDrawables(Drawable l, Drawable t, Drawable r, Drawable b) {
    for (Drawable d : new Drawable[] {mDrawableLeft, mDrawableTop, mDrawableRight, mDrawableBottom}) if (d != null) d.setCallback(null);
    mDrawableLeft = l; mDrawableTop = t; mDrawableRight = r; mDrawableBottom = b;
    for (Drawable d : new Drawable[] {l, t, r, b}) if (d != null) { d.setCallback(this); if (d.isStateful()) d.setState(getDrawableState()); }
    requestLayout(); invalidate();
  }
  public void setCompoundDrawablesWithIntrinsicBounds(Drawable l, Drawable t, Drawable r, Drawable b) {
    for (Drawable d : new Drawable[] {l, t, r, b}) if (d != null) d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
    setCompoundDrawables(l, t, r, b);
  }
  public void setCompoundDrawablesWithIntrinsicBounds(int l, int t, int r, int b) {
    setCompoundDrawablesWithIntrinsicBounds(l == 0 ? null : getResources().getDrawable(l), t == 0 ? null : getResources().getDrawable(t), r == 0 ? null : getResources().getDrawable(r), b == 0 ? null : getResources().getDrawable(b));
  }
  public Drawable[] getCompoundDrawables() { return new Drawable[] {mDrawableLeft, mDrawableTop, mDrawableRight, mDrawableBottom}; }
  public void setCompoundDrawablePadding(int p) { mDrawablePadding = p; requestLayout(); invalidate(); }
  public int getCompoundDrawablePadding() { return mDrawablePadding; }
  @Override protected boolean verifyDrawable(Drawable who) { return super.verifyDrawable(who) || who == mDrawableLeft || who == mDrawableTop || who == mDrawableRight || who == mDrawableBottom; }
  public int getCompoundPaddingLeft() { return mPaddingLeft + (mDrawableLeft == null ? 0 : mDrawableLeft.getBounds().width() + mDrawablePadding); }
  public int getCompoundPaddingRight() { return mPaddingRight + (mDrawableRight == null ? 0 : mDrawableRight.getBounds().width() + mDrawablePadding); }
  public int getCompoundPaddingTop() { return mPaddingTop + (mDrawableTop == null ? 0 : mDrawableTop.getBounds().height() + mDrawablePadding); }
  public int getCompoundPaddingBottom() { return mPaddingBottom + (mDrawableBottom == null ? 0 : mDrawableBottom.getBounds().height() + mDrawablePadding); }
  public int getExtendedPaddingTop() {
    if (mMaxMode != LINES) return getCompoundPaddingTop();
    if (mLayout == null || mLayout.n <= mMaximum) return getCompoundPaddingTop();
    int top = getCompoundPaddingTop(), bottom = getCompoundPaddingBottom();
    int viewht = getHeight() - top - bottom;
    int layoutht = mLayout.getLineTop(mMaximum);
    if (layoutht >= viewht) return top;
    final int gravity = mGravity & Gravity.VERTICAL_GRAVITY_MASK;
    if (gravity == Gravity.TOP) return top;
    else if (gravity == Gravity.BOTTOM) return top + viewht - layoutht;
    else return top + (viewht - layoutht) / 2;
  }
  public int getExtendedPaddingBottom() {
    if (mMaxMode != LINES) return getCompoundPaddingBottom();
    if (mLayout == null || mLayout.n <= mMaximum) return getCompoundPaddingBottom();
    int top = getCompoundPaddingTop(), bottom = getCompoundPaddingBottom();
    int viewht = getHeight() - top - bottom;
    int layoutht = mLayout.getLineTop(mMaximum);
    if (layoutht >= viewht) return bottom;
    final int gravity = mGravity & Gravity.VERTICAL_GRAVITY_MASK;
    if (gravity == Gravity.TOP) return bottom + viewht - layoutht;
    else if (gravity == Gravity.BOTTOM) return bottom;
    else return bottom + (viewht - layoutht) / 2;
  }
  public int getTotalPaddingLeft() { return getCompoundPaddingLeft(); }
  public int getTotalPaddingRight() { return getCompoundPaddingRight(); }
  public int getTotalPaddingTop() { return getExtendedPaddingTop() + getVerticalOffset(true); }
  public int getTotalPaddingBottom() { return getExtendedPaddingBottom() + getBottomVerticalOffset(true); }

  // ---------------------------------------------------------------- layout of the text
  /** Line breaking and StaticLayout-style vertical metrics. */
  final class Lines extends android.text.Layout {
    Lines(CharSequence t, TextPaint p, int w) { super(t, p, Math.max(0, w), android.text.Layout.Alignment.ALIGN_NORMAL, 1, 0); }
    int n; String[] text; float[] widths; int[] tops; int[] descents; int[] starts; int width; int ellipsisCount;
    android.text.Spanned styled;   // the displayed text, when it has spans to draw
    public int getLineCount() { return n; }
    public int getLineTop(int l) { return tops[Math.max(0, Math.min(l, n))]; }
    public int getLineDescent(int l) { return descents[Math.max(0, Math.min(l, n - 1))]; }
    public int getLineStart(int l) { return starts[Math.max(0, Math.min(l, n))]; }
    public float getLineWidth(int l) { return widths[l]; }
    public int getEllipsisCount(int l) { return l == n - 1 ? ellipsisCount : 0; }
    public int getWidth() { return width; }
  }

  CharSequence displayText(CharSequence src) {
    CharSequence t = src;
    if (mTransformation != null && src == mText) t = mTransformation.getTransformation(src, this);
    if (mAllCaps && t != null) {
      String up = t.toString().toUpperCase(java.util.Locale.getDefault());
      if (t instanceof android.text.Spanned && up.length() == t.length()) {
        android.text.SpannableString ss = new android.text.SpannableString(up);
        android.text.TextUtils.copySpansFrom((android.text.Spanned) t, 0, t.length(), null, ss, 0);
        t = ss;
      } else t = up;
    }
    if (mSingleLine) {
      String s = t.toString();
      if (s.indexOf('\n') >= 0 || s.indexOf('\r') >= 0) t = s.replace('\n', ' ').replace('\r', '﻿');
    }
    return t;
  }

  Lines makeLines(CharSequence src, int wantWidth, int ellipsisWidth, boolean ellipsize) {
    CharSequence disp = displayText(src);
    String s = disp.toString();
    TextPaint p = mTextPaint;
    ArrayList<String> out = new ArrayList<>();
    ArrayList<Integer> starts = new ArrayList<>();
    int ellipsisCount = 0;
    boolean noWrap = mHorizontallyScrolling || wantWidth < 0;
    if (noWrap) {
      int pos = 0;
      for (String para : s.split("\n", -1)) { out.add(para); starts.add(pos); pos += para.length() + 1; }
      if (ellipsize && mEllipsize != null && mEllipsize != TextUtils.TruncateAt.MARQUEE && out.size() == 1 && ellipsisWidth > 0) {
        String line = out.get(0);
        if (p.measureText(line) > ellipsisWidth) {
          String e = ellipsizeLine(line, ellipsisWidth, mEllipsize);
          ellipsisCount = line.length() - e.length() + 1;
          out.set(0, e);
        }
      }
    } else {
      int pos = 0;
      for (String para : s.split("\n", -1)) {
        wrap(para, wantWidth, out, starts, pos);
        pos += para.length() + 1;
      }
      int maxLines = mMaxMode == LINES ? mMaximum : Integer.MAX_VALUE;
      if (ellipsize && mEllipsize == TextUtils.TruncateAt.END && out.size() > maxLines && maxLines > 0) {
        String last = out.get(maxLines - 1);
        StringBuilder rest = new StringBuilder(last);
        for (int i = maxLines; i < out.size(); i++) rest.append(out.get(i));
        String e = ellipsizeLine(rest.toString(), ellipsisWidth > 0 ? ellipsisWidth : wantWidth, TextUtils.TruncateAt.END);
        while (out.size() > maxLines) { out.remove(out.size() - 1); starts.remove(starts.size() - 1); }
        out.set(maxLines - 1, e);
        ellipsisCount = 1;
      }
    }
    Lines L = new Lines(disp, p, wantWidth);
    L.n = out.size();
    L.text = out.toArray(new String[0]);
    L.widths = new float[L.n];
    L.tops = new int[L.n + 1];
    L.descents = new int[L.n];
    L.starts = new int[L.n + 1];
    L.ellipsisCount = ellipsisCount;
    L.styled = a2w.SpanText.styled(disp) ? (android.text.Spanned) disp : null;
    Paint.FontMetricsInt fm0 = p.getFontMetricsInt();
    boolean needMultiply = mSpacingMult != 1 || mSpacingAdd != 0;
    int v = 0;
    float maxW = 0;
    for (int i = 0; i < L.n; i++) {
      L.starts[i] = starts.get(i);
      Paint.FontMetricsInt fm = fm0;
      int ls = L.starts[i], le = ls + L.text[i].length();
      if (L.styled != null && le <= s.length() && s.regionMatches(ls, L.text[i], 0, L.text[i].length())) {
        L.widths[i] = a2w.SpanText.width(p, L.styled, ls, le) + a2w.SpanText.margin(L.styled, ls, le);
        fm = new Paint.FontMetricsInt();
        fm.top = fm0.top; fm.ascent = fm0.ascent; fm.descent = fm0.descent; fm.bottom = fm0.bottom; fm.leading = fm0.leading;
        a2w.SpanText.metrics(p, L.styled, ls, le, fm);
      } else L.widths[i] = p.measureText(trimTrailing(L.text[i]));
      maxW = Math.max(maxW, L.widths[i]);
      int above = fm.ascent, below = fm.descent;
      if (i == 0 && mIncludePad) above = fm.top;
      if (i == L.n - 1 && mIncludePad) below = fm.bottom;
      int extra = 0;
      if (needMultiply) { double ex = (below - above) * (mSpacingMult - 1) + mSpacingAdd; extra = (int) (ex >= 0 ? ex + 0.5 : -(-ex + 0.5)); }
      L.tops[i] = v;
      L.descents[i] = below + extra;
      v += (below - above) + extra;
    }
    L.tops[L.n] = v;
    L.starts[L.n] = s.length();
    L.width = wantWidth < 0 ? (int) Math.ceil(maxW) : wantWidth;
    return L;
  }
  static String trimTrailing(String s) { int e = s.length(); while (e > 0 && s.charAt(e - 1) == ' ') e--; return s.substring(0, e); }
  String ellipsizeLine(String line, float avail, TextUtils.TruncateAt where) {
    TextPaint p = mTextPaint;
    String e = "…";
    float ew = p.measureText(e);
    if (where == TextUtils.TruncateAt.START) {
      int lo = 0, hi = line.length();
      while (lo < hi) { int mid = (lo + hi) / 2; if (p.measureText(line.substring(mid)) + ew <= avail) hi = mid; else lo = mid + 1; }
      return e + line.substring(lo);
    }
    int lo = 0, hi = line.length();
    while (lo < hi) { int mid = (lo + hi + 1) / 2; if (p.measureText(line.substring(0, mid)) + ew <= avail) lo = mid; else hi = mid - 1; }
    return line.substring(0, lo) + e;
  }
  static boolean isCjk(char c) { return (c >= 0x2E80 && c <= 0x9FFF) || (c >= 0xAC00 && c <= 0xD7AF) || (c >= 0xF900 && c <= 0xFAFF) || (c >= 0xFF00 && c <= 0xFFEF) || (c >= 0x3000 && c <= 0x303F); }
  void wrap(String para, int width, ArrayList<String> out, ArrayList<Integer> starts, int base) {
    TextPaint p = mTextPaint;
    if (para.isEmpty()) { out.add(""); starts.add(base); return; }
    if (p.measureText(trimTrailing(para)) <= width) { out.add(para); starts.add(base); return; }
    float[] w = new float[para.length()];
    p.getTextWidths(para, w);
    int start = 0;
    while (start < para.length()) {
      float sum = 0;
      int lastBreak = -1, i = start;
      for (; i < para.length(); i++) {
        char c = para.charAt(i);
        if (c == ' ') { lastBreak = i + 1; sum += w[i]; continue; }
        if (sum + w[i] > width && i > start) break;
        sum += w[i];
        if (isCjk(c) || c == '-' || c == '/') lastBreak = i + 1;
        else if (i + 1 < para.length() && isCjk(para.charAt(i + 1))) lastBreak = i + 1;
      }
      int end;
      if (i >= para.length()) end = para.length();
      else if (lastBreak > start) end = lastBreak;
      else end = Math.max(start + 1, i);
      out.add(para.substring(start, end));
      starts.add(base + start);
      start = end;
    }
  }

  int desiredWidth(CharSequence t) {
    String s = displayText(t).toString();
    float m = 0;
    for (String l : s.split("\n", -1)) m = Math.max(m, mTextPaint.measureText(l));
    return (int) Math.ceil(m);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int widthMode = MeasureSpec.getMode(widthMeasureSpec), heightMode = MeasureSpec.getMode(heightMeasureSpec);
    int widthSize = MeasureSpec.getSize(widthMeasureSpec), heightSize = MeasureSpec.getSize(heightMeasureSpec);
    int width, height;
    int des = -1;
    boolean fromexisting = false;
    if (widthMode == MeasureSpec.EXACTLY) width = widthSize;
    else {
      des = desiredWidth(mText);
      width = des;
      if (mHint != null && mHint.length() > 0) width = Math.max(width, desiredWidth(mHint));
      width += getCompoundPaddingLeft() + getCompoundPaddingRight();
      if (mMaxWidthMode == EMS) width = Math.min(width, mMaxWidthValue * getLineHeight());
      else width = Math.min(width, mMaxWidthValue);
      if (mMinWidthMode == EMS) width = Math.max(width, mMinWidthValue * getLineHeight());
      else width = Math.max(width, mMinWidthValue);
      width = Math.max(width, getSuggestedMinimumWidth());
      if (widthMode == MeasureSpec.AT_MOST) width = Math.min(widthSize, width);
    }
    int want = width - getCompoundPaddingLeft() - getCompoundPaddingRight();
    int unpaddedWidth = want;
    if (mHorizontallyScrolling) want = -1;
    int ellipsisWidth = unpaddedWidth;
    if (mAutoSizeType != AUTO_SIZE_TEXT_TYPE_NONE && want > 0 && heightMode != MeasureSpec.UNSPECIFIED) autoSize(want, heightSize - getCompoundPaddingTop() - getCompoundPaddingBottom());
    mLayout = makeLines(mText, want, ellipsisWidth, true);
    mHintLayout = mHint != null ? makeLines(mHint, want, ellipsisWidth, true) : null;
    if (heightMode == MeasureSpec.EXACTLY) height = heightSize;
    else {
      int desired = getDesiredHeight();
      height = desired;
      if (heightMode == MeasureSpec.AT_MOST) height = Math.min(desired, heightSize);
    }
    int unpaddedHeight = height - getCompoundPaddingTop() - getCompoundPaddingBottom();
    if (mMaxMode == LINES && mLayout.n > mMaximum) unpaddedHeight = Math.min(unpaddedHeight, mLayout.getLineTop(mMaximum));
    setMeasuredDimension(width, height);
    mLayoutWidth = want;
    updateMarquee();
  }
  int mLayoutWidth;
  private int getDesiredHeight() { return Math.max(getDesiredHeight(mLayout, true), getDesiredHeight(mHintLayout, mEllipsize != null)); }
  private int getDesiredHeight(Lines layout, boolean cap) {
    if (layout == null) return 0;
    int linecount = layout.n;
    int pad = getCompoundPaddingTop() + getCompoundPaddingBottom();
    int desired = layout.getLineTop(linecount);
    if (mDrawableLeft != null) desired = Math.max(desired, mDrawableLeft.getBounds().height());
    if (mDrawableRight != null) desired = Math.max(desired, mDrawableRight.getBounds().height());
    desired += pad;
    if (mMaxMode == LINES) {
      if (cap && linecount > mMaximum) {
        desired = layout.getLineTop(mMaximum);
        if (mDrawableLeft != null) desired = Math.max(desired, mDrawableLeft.getBounds().height());
        if (mDrawableRight != null) desired = Math.max(desired, mDrawableRight.getBounds().height());
        desired += pad;
        linecount = mMaximum;
      }
    } else desired = Math.min(desired, mMaximum);
    if (mMinMode == LINES) { if (linecount < mMinimum) desired += getLineHeight() * (mMinimum - linecount); }
    else desired = Math.max(desired, mMinimum);
    desired = Math.max(desired, getSuggestedMinimumHeight());
    return desired;
  }

  void checkForRelayout() {
    mLayout = null;
    requestLayout();
    invalidate();
  }
  private void assumeLayout() {
    if (mLayout == null) {
      int w = getWidth() - getCompoundPaddingLeft() - getCompoundPaddingRight();
      if (w < 1) w = 0;
      int want = mHorizontallyScrolling ? -1 : w;
      mLayout = makeLines(mText, want, w, true);
      mHintLayout = mHint != null ? makeLines(mHint, want, w, true) : null;
      mLayoutWidth = want;
    }
  }
  int getVerticalOffset(boolean forceNormal) {
    int voffset = 0;
    final int gravity = mGravity & Gravity.VERTICAL_GRAVITY_MASK;
    Lines l = mLayout;
    if (!forceNormal && mText.length() == 0 && mHintLayout != null) l = mHintLayout;
    if (gravity != Gravity.TOP && l != null) {
      int boxht = getHeight() - getExtendedPaddingTop() - getExtendedPaddingBottom();
      int textht = l.getHeight();
      if (textht < boxht) {
        if (gravity == Gravity.BOTTOM) voffset = boxht - textht;
        else voffset = (boxht - textht) >> 1;
      }
    }
    return voffset;
  }
  private int getBottomVerticalOffset(boolean forceNormal) {
    int voffset = 0;
    final int gravity = mGravity & Gravity.VERTICAL_GRAVITY_MASK;
    Lines l = mLayout;
    if (!forceNormal && mText.length() == 0 && mHintLayout != null) l = mHintLayout;
    if (gravity != Gravity.BOTTOM && l != null) {
      int boxht = getHeight() - getExtendedPaddingTop() - getExtendedPaddingBottom();
      int textht = l.getHeight();
      if (textht < boxht) {
        if (gravity == Gravity.TOP) voffset = boxht - textht;
        else voffset = (boxht - textht) >> 1;
      }
    }
    return voffset;
  }
  @Override public int getBaseline() {
    assumeLayout();
    if (mLayout == null) return super.getBaseline();
    int voffset = 0;
    if ((mGravity & Gravity.VERTICAL_GRAVITY_MASK) != Gravity.TOP) voffset = getVerticalOffset(true);
    return getExtendedPaddingTop() + voffset + mLayout.getLineBaseline(0);
  }

  // ---------------------------------------------------------------- marquee
  private void updateMarquee() {
    boolean want = mEllipsize == TextUtils.TruncateAt.MARQUEE && mLayout != null && mLayout.n == 1 && (isFocused() || isSelected())
        && mLayout.widths[0] > getWidthForMarquee();
    if (want != mMarquee) { mMarquee = want; mMarqueeStart = android.os.SystemClock.uptimeMillis(); invalidate(); }
  }
  private int getWidthForMarquee() { int w = getMeasuredWidth() - getCompoundPaddingLeft() - getCompoundPaddingRight(); return w; }
  @Override public void setSelected(boolean s) { super.setSelected(s); updateMarquee(); }
  @Override protected void onFocusChanged(boolean f, int dir, Rect prev) {
    if (mError != null) { if (f) post(this::showErrorPopup); else hideErrorPopup(); }
    super.onFocusChanged(f, dir, prev);
    updateMarquee();
    if (!f && this instanceof EditText) a2w.TextInput.blurred(this);
    // focus by keyboard (out of touch mode): the text field opens, so typing goes into it
    if (f && this instanceof EditText && !isInTouchMode() && isEnabled()) post(() -> { if (isFocused() && !isInTouchMode()) a2w.TextInput.show(this); });
  }
  @Override public void onWindowFocusChanged(boolean f) { super.onWindowFocusChanged(f); updateMarquee(); }

  // ---------------------------------------------------------------- drawing
  public boolean onPreDraw() { return true; }
  @Override protected void onDraw(Canvas canvas) {
    assumeLayout();
    final int compoundPaddingLeft = getCompoundPaddingLeft(), compoundPaddingTop = getCompoundPaddingTop();
    final int compoundPaddingRight = getCompoundPaddingRight(), compoundPaddingBottom = getCompoundPaddingBottom();
    final int scrollX = mScrollX, scrollY = mScrollY;
    final int right = mRight, left = mLeft, bottom = mBottom, top = mTop;
    int vspace = bottom - top - compoundPaddingBottom - compoundPaddingTop;
    int hspace = right - left - compoundPaddingRight - compoundPaddingLeft;
    if (mDrawableLeft != null) { canvas.save(); canvas.translate(scrollX + mPaddingLeft, scrollY + compoundPaddingTop + (vspace - mDrawableLeft.getBounds().height()) / 2); mDrawableLeft.draw(canvas); canvas.restore(); }
    if (mDrawableRight != null) { canvas.save(); canvas.translate(scrollX + right - left - mPaddingRight - mDrawableRight.getBounds().width(), scrollY + compoundPaddingTop + (vspace - mDrawableRight.getBounds().height()) / 2); mDrawableRight.draw(canvas); canvas.restore(); }
    if (mDrawableTop != null) { canvas.save(); canvas.translate(scrollX + compoundPaddingLeft + (hspace - mDrawableTop.getBounds().width()) / 2, scrollY + mPaddingTop); mDrawableTop.draw(canvas); canvas.restore(); }
    if (mDrawableBottom != null) { canvas.save(); canvas.translate(scrollX + compoundPaddingLeft + (hspace - mDrawableBottom.getBounds().width()) / 2, scrollY + bottom - top - mPaddingBottom - mDrawableBottom.getBounds().height()); mDrawableBottom.draw(canvas); canvas.restore(); }
    if (mHideText) return;
    Lines layout = mLayout;
    int color = mCurTextColor;
    if (mText.length() == 0 && mHint != null && mHintLayout != null) { layout = mHintLayout; color = mCurHintTextColor; }
    if (layout == null) return;
    mTextPaint.setColor(color);
    canvas.save();
    int extendedPaddingTop = getExtendedPaddingTop(), extendedPaddingBottom = getExtendedPaddingBottom();
    float clipLeft = compoundPaddingLeft + scrollX, clipTop = extendedPaddingTop + scrollY;
    float clipRight = right - left - compoundPaddingRight + scrollX, clipBottom = bottom - top - extendedPaddingBottom + scrollY;
    if (mShadowRadius != 0) {
      clipLeft += Math.min(0, mShadowDx - mShadowRadius);
      clipRight += Math.max(0, mShadowDx + mShadowRadius);
      clipTop += Math.min(0, mShadowDy - mShadowRadius);
      clipBottom += Math.max(0, mShadowDy + mShadowRadius);
    }
    canvas.clipRect(clipLeft, clipTop, clipRight, clipBottom);
    int voffsetText = 0;
    if ((mGravity & Gravity.VERTICAL_GRAVITY_MASK) != Gravity.TOP) voffsetText = getVerticalOffset(false);
    canvas.translate(compoundPaddingLeft, extendedPaddingTop + voffsetText);
    int avail = hspace;
    final int hg = mGravity & Gravity.HORIZONTAL_GRAVITY_MASK;
    float marqueeOffset = 0;
    if (mMarquee && layout == mLayout) {
      float lw = layout.widths[0], gap = avail / 3f;
      float speed = 30 * Values.METRICS.density;
      long t = android.os.SystemClock.uptimeMillis() - mMarqueeStart - 1200;
      if (t > 0) marqueeOffset = (t * speed / 1000f) % (lw + gap);
      canvas.translate(-marqueeOffset, 0);
      mTextPaint.setTextAlign(Paint.Align.LEFT);
      int y = layout.getLineBaseline(0);
      canvas.drawText(layout.text[0], 0, y, mTextPaint);
      if (marqueeOffset > 0) canvas.drawText(layout.text[0], lw + gap, y, mTextPaint);
      canvas.restore();
      postInvalidateDelayed(16);
      return;
    }
    for (int i = 0; i < layout.n; i++) {
      String line = layout.text[i];
      if (line.isEmpty()) continue;
      float lw = layout.widths[i];
      float x;
      if (mHorizontallyScrolling && lw > avail) x = hg == Gravity.RIGHT ? avail - lw : hg == Gravity.CENTER_HORIZONTAL && mEllipsize == null ? (avail - lw) / 2 : 0;
      else if (hg == Gravity.CENTER_HORIZONTAL) x = (avail - (int) Math.ceil(lw)) / 2;
      else if (hg == Gravity.RIGHT) x = avail - (int) Math.ceil(lw);
      else x = 0;
      int ls = layout.starts[i];
      if (layout == mLayout && (mHighlights != null || mSearchResults != null)) drawHighlights(canvas, layout, i, line, x);
      android.text.Spanned sp = layout.styled;
      if (sp != null && ls + line.length() <= sp.length() && sp.toString().regionMatches(ls, line, 0, line.length())) {
        int lt = layout.getLineTop(i), base = layout.getLineBaseline(i), bot = layout.getLineBottom(i);
        int m = a2w.SpanText.drawMargins(canvas, mTextPaint, sp, ls, ls + line.length(), lt, base, bot, layout);
        a2w.SpanText.draw(canvas, mTextPaint, sp, ls, ls + line.length(), x + m, lt, base, bot);
        continue;
      }
      canvas.drawText(line, x, layout.getLineBaseline(i), mTextPaint);
    }
    canvas.restore();
  }

  /** the highlight and search-result backgrounds on line i (drawn at x) */
  private void drawHighlights(Canvas canvas, Lines layout, int i, String line, float x) {
    int ls = layout.starts[i], le = ls + line.length();
    float top = layout.getLineTop(i), bottom = layout.getLineBottom(i);
    if (mSearchResults != null) {
      Paint p = new Paint();
      for (int k = 0; k + 1 < mSearchResults.length; k += 2) {
        p.setColor(k / 2 == mFocusedSearchResultIndex ? mFocusedSearchResultColor : mSearchResultColor);
        highlightRange(canvas, line, ls, le, mSearchResults[k], mSearchResults[k + 1], x, top, bottom, p);
      }
    }
    if (mHighlights != null)
      for (int h = 0; h < mHighlights.getSize(); h++) {
        int[] r = mHighlights.getRanges(h);
        for (int k = 0; k + 1 < r.length; k += 2) highlightRange(canvas, line, ls, le, r[k], r[k + 1], x, top, bottom, mHighlights.getPaint(h));
      }
  }
  private void highlightRange(Canvas canvas, String line, int ls, int le, int s, int e, float x, float top, float bottom, Paint p) {
    int a = Math.max(s, ls), b = Math.min(e, le);
    if (a >= b) return;
    float l = x + mTextPaint.measureText(line, 0, a - ls), r = x + mTextPaint.measureText(line, 0, b - ls);
    canvas.drawRect(l, top, r, bottom, p);
  }

  // ---------------------------------------------------------------- input
  @Override public boolean onTouchEvent(MotionEvent e) {
    if (linkTouch(e)) return true;
    boolean superResult = super.onTouchEvent(e);
    if (this instanceof EditText && isEnabled() && e.getAction() == MotionEvent.ACTION_UP && isFocusableInTouchMode()) {
      if (!isFocused()) requestFocus();
      if (isFocused()) a2w.TextInput.show(this);
      return true;
    }
    return superResult;
  }
  @Override public boolean onCheckIsTextEditor() { return this instanceof EditText; }
  /** an editable text focused without its page text field (keyboard focus, or after Escape): a typed character or delete opens the field with the edit */
  @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (this instanceof EditText && isEnabled() && !a2w.TextInput.isActive(this)) {
      String cur = mText.toString();
      int len = cur.length();
      int a = Math.min(getSelectionStart(), getSelectionEnd()), b = Math.max(getSelectionStart(), getSelectionEnd());
      if (a < 0 || a > len) a = len;
      if (b < a || b > len) b = a;
      int ch = event.isCtrlPressed() || event.isAltPressed() || event.isMetaPressed() ? 0 : event.getUnicodeChar();
      if (ch > 0 && ch != '\n' && ch != '\t') {
        if (mMaxLength > 0 && len - (b - a) >= mMaxLength) return true;
        setText(cur.substring(0, a) + (char) ch + cur.substring(b));
        setSelection(Math.min(a + 1, length()));
        a2w.TextInput.show(this);
        return true;
      }
      if (keyCode == KeyEvent.KEYCODE_DEL || keyCode == KeyEvent.KEYCODE_FORWARD_DEL) {
        if (a == b) { if (keyCode == KeyEvent.KEYCODE_DEL) a = Math.max(0, a - 1); else b = Math.min(len, b + 1); }
        if (a < b) setText(cur.substring(0, a) + cur.substring(b));
        setSelection(Math.min(a, length()));
        a2w.TextInput.show(this);
        return true;
      }
    }
    return super.onKeyDown(keyCode, event);
  }
  @Override public boolean onKeyUp(int keyCode, KeyEvent event) { return super.onKeyUp(keyCode, event); }
  /** web: text typed into the page's text field */
  public void webSetText(String s) {
    if (mText.toString().equals(s)) return;
    setText(s);
  }
  public boolean webHideText(boolean h) { mHideText = h; invalidate(); return true; }
  public boolean webPassword() { return isPassword(); }
  public int webMaxLength() { return mMaxLength; }
  public int webTextColor() { return mCurTextColor; }
  public boolean webMultiLine() { return !mSingleLine && (mInputType & EditorInfo.TYPE_TEXT_FLAG_MULTI_LINE) != 0; }
  public boolean webNumeric() { return (mInputType & EditorInfo.TYPE_MASK_CLASS) == EditorInfo.TYPE_CLASS_NUMBER || (mInputType & EditorInfo.TYPE_MASK_CLASS) == EditorInfo.TYPE_CLASS_PHONE; }
  public boolean webEmail() { return (mInputType & EditorInfo.TYPE_MASK_VARIATION) == EditorInfo.TYPE_TEXT_VARIATION_EMAIL_ADDRESS; }
  public int webGravity() { return mGravity; }

  @Override protected void onAttachedToWindow() { super.onAttachedToWindow(); updateMarquee(); }
  @Override protected void onDetachedFromWindow() { super.onDetachedFromWindow(); a2w.TextInput.blurred(this); }
  @Override protected void onSizeChanged(int w, int h, int ow, int oh) { super.onSizeChanged(w, h, ow, oh); }
  @Override public void setPadding(int l, int t, int r, int b) { if (l != mPaddingLeft || r != mPaddingRight || t != mPaddingTop || b != mPaddingBottom) mLayout = null; super.setPadding(l, t, r, b); invalidate(); }
  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) { super.onLayout(changed, l, t, r, b); }
  @Override public void setEnabled(boolean e) { super.setEnabled(e); updateTextColors(); }
  public boolean didTouchFocusSelect() { return false; }
  public boolean isInputMethodTarget() { return isFocused(); }
  public boolean isTextSelectable() { return false; }
  public void beginBatchEdit() {}
  public void endBatchEdit() {}
  public void setRawText(CharSequence t) { setText(t); }

  // ---------------------------------------------------------------- appearance resources
  public void setTextAppearance(int resId) { setTextAppearance(getContext(), resId); }
  Spannable.Factory mSpannableFactory = Spannable.Factory.getInstance();
  public final void setSpannableFactory(Spannable.Factory factory) { mSpannableFactory = factory == null ? Spannable.Factory.getInstance() : factory; setText(mText); }
  // (API 1-20 helpers, removed from the SDK in 21; resolution is immediate here)
  public void resetResolvedDrawables() {}
  public void resolveDrawables() {}
  public void resetResolvedLayoutDirection() {}
  public void resolveTextDirection() {}

  // ---------------------------------------------------------------- size limits
  public int getMaxHeight() { return mMaxMode == PIXELS ? mMaximum : -1; }
  public int getMinHeight() { return mMinMode == PIXELS ? mMinimum : -1; }
  public int getMaxWidth() { return mMaxWidthMode == PIXELS ? mMaxWidthValue : -1; }
  public int getMinWidth() { return mMinWidthMode == PIXELS ? mMinWidthValue : -1; }
  public int getMaxEms() { return mMaxWidthMode == EMS ? mMaxWidthValue : -1; }
  public int getMinEms() { return mMinWidthMode == EMS ? mMinWidthValue : -1; }
  public boolean isHorizontallyScrollable() { return mHorizontallyScrolling; }
  public void setLineHeight(int unit, float lineHeight) { setLineHeight((int) TypedValue.applyDimension(unit, lineHeight, Values.METRICS)); }
  /** top padding so the first baseline lies this far below the top (as Android: from the font's ascent) */
  public void setFirstBaselineToTopHeight(int firstBaselineToTopHeight) {
    if (firstBaselineToTopHeight < 0) throw new IllegalArgumentException();
    Paint.FontMetricsInt fm = mTextPaint.getFontMetricsInt();
    int fontMetricsTop = getIncludeFontPadding() ? fm.top : fm.ascent;
    if (firstBaselineToTopHeight > Math.abs(fontMetricsTop)) setPadding(getPaddingLeft(), firstBaselineToTopHeight - (-fontMetricsTop), getPaddingRight(), getPaddingBottom());
  }
  public void setLastBaselineToBottomHeight(int lastBaselineToBottomHeight) {
    if (lastBaselineToBottomHeight < 0) throw new IllegalArgumentException();
    Paint.FontMetricsInt fm = mTextPaint.getFontMetricsInt();
    int fontMetricsBottom = getIncludeFontPadding() ? fm.bottom : fm.descent;
    if (lastBaselineToBottomHeight > Math.abs(fontMetricsBottom)) setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), lastBaselineToBottomHeight - fontMetricsBottom);
  }
  public int getFirstBaselineToTopHeight() { return getPaddingTop() - mTextPaint.getFontMetricsInt().top; }
  public int getLastBaselineToBottomHeight() { return getPaddingBottom() + mTextPaint.getFontMetricsInt().bottom; }

  // ---------------------------------------------------------------- auto-size
  public static final int AUTO_SIZE_TEXT_TYPE_NONE = 0, AUTO_SIZE_TEXT_TYPE_UNIFORM = 1;
  int mAutoSizeType = AUTO_SIZE_TEXT_TYPE_NONE;
  int[] mAutoSizeSizes = new int[0];
  int mAutoSizeMin = -1, mAutoSizeMax = -1, mAutoSizeStep = -1;
  public void setAutoSizeTextTypeWithDefaults(int autoSizeTextType) {
    if (autoSizeTextType == AUTO_SIZE_TEXT_TYPE_NONE) { mAutoSizeType = 0; mAutoSizeSizes = new int[0]; mAutoSizeMin = mAutoSizeMax = mAutoSizeStep = -1; return; }
    if (autoSizeTextType != AUTO_SIZE_TEXT_TYPE_UNIFORM) throw new IllegalArgumentException("Unknown auto-size text type: " + autoSizeTextType);
    setAutoSizeTextTypeUniformWithConfiguration((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, Values.METRICS),
        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 112, Values.METRICS), 1, TypedValue.COMPLEX_UNIT_PX);
  }
  public void setAutoSizeTextTypeUniformWithConfiguration(int min, int max, int step, int unit) {
    float lo = TypedValue.applyDimension(unit, min, Values.METRICS), hi = TypedValue.applyDimension(unit, max, Values.METRICS), st = TypedValue.applyDimension(unit, step, Values.METRICS);
    if (lo <= 0) throw new IllegalArgumentException("Minimum auto-size text size (" + min + "px) is less or equal to (0px)");
    if (hi <= lo) throw new IllegalArgumentException("Maximum auto-size text size (" + max + "px) is less or equal to minimum auto-size text size (" + min + "px)");
    if (st <= 0) throw new IllegalArgumentException("The auto-size step granularity (" + step + "px) is less or equal to (0px)");
    int n = (int) ((hi - lo) / st) + 1;
    int[] sizes = new int[n];
    for (int i = 0; i < n; i++) sizes[i] = Math.round(lo + i * st);
    mAutoSizeType = AUTO_SIZE_TEXT_TYPE_UNIFORM; mAutoSizeSizes = sizes;
    mAutoSizeMin = Math.round(lo); mAutoSizeMax = Math.round(hi); mAutoSizeStep = Math.round(st);
    mLayout = null; requestLayout(); invalidate();
  }
  public void setAutoSizeTextTypeUniformWithPresetSizes(int[] presetSizes, int unit) {
    int[] sizes = new int[presetSizes.length];
    for (int i = 0; i < sizes.length; i++) sizes[i] = Math.round(TypedValue.applyDimension(unit, presetSizes[i], Values.METRICS));
    java.util.Arrays.sort(sizes);
    if (sizes.length == 0) throw new IllegalArgumentException("Preset sizes are empty");
    mAutoSizeType = AUTO_SIZE_TEXT_TYPE_UNIFORM; mAutoSizeSizes = sizes;
    mAutoSizeMin = sizes[0]; mAutoSizeMax = sizes[sizes.length - 1]; mAutoSizeStep = -1;
    mLayout = null; requestLayout(); invalidate();
  }
  public int getAutoSizeTextType() { return mAutoSizeType; }
  public int getAutoSizeStepGranularity() { return mAutoSizeStep; }
  public int getAutoSizeMinTextSize() { return mAutoSizeMin; }
  public int getAutoSizeMaxTextSize() { return mAutoSizeMax; }
  public int[] getAutoSizeTextAvailableSizes() { return mAutoSizeSizes.clone(); }
  /** the largest available size whose layout fits the space (binary search, as Android) */
  private void autoSize(int width, int height) {
    if (mAutoSizeSizes.length == 0 || height <= 0) return;
    int lo = 0, hi = mAutoSizeSizes.length - 1, best = 0;
    float saved = mTextPaint.getTextSize();
    while (lo <= hi) {
      int mid = (lo + hi) >>> 1;
      mTextPaint.setTextSize(mAutoSizeSizes[mid]);
      Lines l = makeLines(mText, width, width, false);
      boolean fits = l.getLineTop(l.n) <= height && (mMaxMode != LINES || l.n <= mMaximum);
      if (fits) { best = mid; lo = mid + 1; } else hi = mid - 1;
    }
    mTextPaint.setTextSize(saved);
    if (mAutoSizeSizes[best] != saved) { mTextPaint.setTextSize(mAutoSizeSizes[best]); mHintLayout = null; }
  }

  // ---------------------------------------------------------------- paint-backed text settings
  public void setFontFeatureSettings(String fontFeatureSettings) { mTextPaint.setFontFeatureSettings(fontFeatureSettings); mLayout = null; requestLayout(); invalidate(); }
  public String getFontFeatureSettings() { return mTextPaint.getFontFeatureSettings(); }
  public boolean setFontVariationSettings(String fontVariationSettings) {
    boolean ok = mTextPaint.setFontVariationSettings(fontVariationSettings);
    if (ok) { mLayout = null; requestLayout(); invalidate(); }
    return ok;
  }
  public String getFontVariationSettings() { return mTextPaint.getFontVariationSettings(); }
  public void setElegantTextHeight(boolean elegant) { mTextPaint.setElegantTextHeight(elegant); mLayout = null; requestLayout(); invalidate(); }
  public boolean isElegantTextHeight() { return mTextPaint.isElegantTextHeight(); }
  public void setTextLocale(java.util.Locale locale) { mTextPaint.setTextLocale(locale); mLayout = null; requestLayout(); invalidate(); }
  public java.util.Locale getTextLocale() { return mTextPaint.getTextLocale(); }
  public void setTextLocales(android.os.LocaleList locales) { mTextPaint.setTextLocales(locales); mLayout = null; requestLayout(); invalidate(); }
  public android.os.LocaleList getTextLocales() { return mTextPaint.getTextLocales(); }
  public android.text.TextDirectionHeuristic getTextDirectionHeuristic() { return android.text.TextDirectionHeuristics.FIRSTSTRONG_LTR; }

  // ---------------------------------------------------------------- line breaking options (kept; the line breaker is greedy)
  int mBreakStrategy, mHyphenationFrequency, mJustificationMode, mLineBreakStyle, mLineBreakWordStyle;
  boolean mFallbackLineSpacing = true, mUseBoundsForWidth, mShiftDrawingOffsetForStartOverhang, mLocalePreferredLineHeight;
  Paint.FontMetrics mMinimumFontMetrics;
  android.text.PrecomputedText.Params mTextMetricsParams;
  public void setBreakStrategy(int breakStrategy) { mBreakStrategy = breakStrategy; }
  public int getBreakStrategy() { return mBreakStrategy; }
  public void setHyphenationFrequency(int hyphenationFrequency) { mHyphenationFrequency = hyphenationFrequency; }
  public int getHyphenationFrequency() { return mHyphenationFrequency; }
  public void setJustificationMode(int justificationMode) { mJustificationMode = justificationMode; }
  public int getJustificationMode() { return mJustificationMode; }
  public void setLineBreakStyle(int lineBreakStyle) { mLineBreakStyle = lineBreakStyle; }
  public int getLineBreakStyle() { return mLineBreakStyle; }
  public void setLineBreakWordStyle(int lineBreakWordStyle) { mLineBreakWordStyle = lineBreakWordStyle; }
  public int getLineBreakWordStyle() { return mLineBreakWordStyle; }
  public void setFallbackLineSpacing(boolean enabled) { mFallbackLineSpacing = enabled; }
  public boolean isFallbackLineSpacing() { return mFallbackLineSpacing; }
  public void setUseBoundsForWidth(boolean useBoundsForWidth) { mUseBoundsForWidth = useBoundsForWidth; }
  public boolean getUseBoundsForWidth() { return mUseBoundsForWidth; }
  public void setShiftDrawingOffsetForStartOverhang(boolean shift) { mShiftDrawingOffsetForStartOverhang = shift; }
  public boolean getShiftDrawingOffsetForStartOverhang() { return mShiftDrawingOffsetForStartOverhang; }
  public void setLocalePreferredLineHeightForMinimumUsed(boolean flag) { mLocalePreferredLineHeight = flag; }
  public boolean isLocalePreferredLineHeightForMinimumUsed() { return mLocalePreferredLineHeight; }
  public void setMinimumFontMetrics(Paint.FontMetrics minimumFontMetrics) { mMinimumFontMetrics = minimumFontMetrics; }
  public Paint.FontMetrics getMinimumFontMetrics() { return mMinimumFontMetrics; }
  public void setTextMetricsParams(android.text.PrecomputedText.Params params) { mTextMetricsParams = params; }
  /** the measuring parameters: the ones set, else this view's paint and line-breaking settings */
  public android.text.PrecomputedText.Params getTextMetricsParams() {
    if (mTextMetricsParams != null) return mTextMetricsParams;
    return new android.text.PrecomputedText.Params.Builder(new TextPaint(mTextPaint)).setBreakStrategy(mBreakStrategy).setHyphenationFrequency(mHyphenationFrequency)
        .setTextDirection(getTextDirectionHeuristic()).build();
  }

  // ---------------------------------------------------------------- drawables and decorations
  ColorStateList mDrawableTint; PorterDuff.Mode mDrawableTintMode; android.graphics.BlendMode mDrawableBlendMode;
  public void setCompoundDrawableTintList(ColorStateList tint) { mDrawableTint = tint; applyCompoundDrawableTint(); }
  public ColorStateList getCompoundDrawableTintList() { return mDrawableTint; }
  public void setCompoundDrawableTintMode(PorterDuff.Mode tintMode) { mDrawableTintMode = tintMode; applyCompoundDrawableTint(); }
  public PorterDuff.Mode getCompoundDrawableTintMode() { return mDrawableTintMode; }
  public void setCompoundDrawableTintBlendMode(android.graphics.BlendMode blendMode) { mDrawableBlendMode = blendMode; applyCompoundDrawableTint(); }
  public android.graphics.BlendMode getCompoundDrawableTintBlendMode() { return mDrawableBlendMode; }
  private void applyCompoundDrawableTint() {
    for (Drawable d : new Drawable[] {mDrawableLeft, mDrawableTop, mDrawableRight, mDrawableBottom}) {
      if (d == null) continue;
      d = d.mutate();
      if (mDrawableTint != null) d.setTintList(mDrawableTint);
      if (mDrawableTintMode != null) d.setTintMode(mDrawableTintMode);
      if (d.isStateful()) d.setState(getDrawableState());
    }
    invalidate();
  }
  Drawable mCursorDrawable, mSelectHandle, mSelectHandleLeft, mSelectHandleRight;
  public void setTextCursorDrawable(Drawable textCursorDrawable) { mCursorDrawable = textCursorDrawable; }
  public void setTextCursorDrawable(int textCursorDrawable) { setTextCursorDrawable(textCursorDrawable == 0 ? null : getContext().getDrawable(textCursorDrawable)); }
  public Drawable getTextCursorDrawable() { return mCursorDrawable; }
  public void setTextSelectHandle(Drawable d) { mSelectHandle = java.util.Objects.requireNonNull(d); }
  public void setTextSelectHandle(int res) { if (res == 0) throw new IllegalArgumentException(); setTextSelectHandle(getContext().getDrawable(res)); }
  public Drawable getTextSelectHandle() { return mSelectHandle; }
  public void setTextSelectHandleLeft(Drawable d) { mSelectHandleLeft = java.util.Objects.requireNonNull(d); }
  public void setTextSelectHandleLeft(int res) { if (res == 0) throw new IllegalArgumentException(); setTextSelectHandleLeft(getContext().getDrawable(res)); }
  public Drawable getTextSelectHandleLeft() { return mSelectHandleLeft; }
  public void setTextSelectHandleRight(Drawable d) { mSelectHandleRight = java.util.Objects.requireNonNull(d); }
  public void setTextSelectHandleRight(int res) { if (res == 0) throw new IllegalArgumentException(); setTextSelectHandleRight(getContext().getDrawable(res)); }
  public Drawable getTextSelectHandleRight() { return mSelectHandleRight; }
  android.text.Highlights mHighlights;
  int[] mSearchResults; int mSearchResultColor = 0xffffff00, mFocusedSearchResultColor = 0xffff9632, mFocusedSearchResultIndex = -1;
  public void setHighlights(android.text.Highlights highlights) { mHighlights = highlights; invalidate(); }
  public android.text.Highlights getHighlights() { return mHighlights; }
  public void setSearchResultHighlights(int... ranges) {
    if (ranges != null && ranges.length % 2 == 1) throw new IllegalArgumentException("Flatten ranges must have even numbered elements");
    mSearchResults = ranges == null ? null : ranges.clone(); invalidate();
  }
  public int[] getSearchResultHighlights() { return mSearchResults; }
  public void setSearchResultHighlightColor(int color) { mSearchResultColor = color; invalidate(); }
  public int getSearchResultHighlightColor() { return mSearchResultColor; }
  public void setFocusedSearchResultHighlightColor(int color) { mFocusedSearchResultColor = color; invalidate(); }
  public int getFocusedSearchResultHighlightColor() { return mFocusedSearchResultColor; }
  public void setFocusedSearchResultIndex(int index) {
    if (mSearchResults == null) throw new IllegalArgumentException("Search result range must be set beforehand.");
    if (index < -1 || index >= mSearchResults.length / 2) throw new IllegalArgumentException("Focused index(" + index + ") must be larger than -1 and less than range count(" + (mSearchResults.length / 2) + ")");
    mFocusedSearchResultIndex = index; invalidate();
  }
  public int getFocusedSearchResultIndex() { return mFocusedSearchResultIndex; }

  // ---------------------------------------------------------------- shadow and fading edges
  protected boolean isPaddingOffsetRequired() { return mShadowRadius != 0 || mDrawableLeft != null || mDrawableTop != null || mDrawableRight != null || mDrawableBottom != null; }
  protected int getLeftPaddingOffset() { return getCompoundPaddingLeft() - mPaddingLeft + (int) Math.min(0, mShadowDx - mShadowRadius); }
  protected int getTopPaddingOffset() { return (int) Math.min(0, mShadowDy - mShadowRadius); }
  protected int getBottomPaddingOffset() { return (int) Math.max(0, mShadowDy + mShadowRadius); }
  protected int getRightPaddingOffset() { return -(getCompoundPaddingRight() - mPaddingRight) + (int) Math.max(0, mShadowDx + mShadowRadius); }
  protected float getLeftFadingEdgeStrength() { return mMarquee && mMarqueeScroll > 0 ? Math.min(1f, mMarqueeScroll / Math.max(1, getHorizontalFadingEdgeLength())) : 0f; }
  protected float getRightFadingEdgeStrength() {
    if (mEllipsize == TextUtils.TruncateAt.MARQUEE && mLayout != null && mLayout.n > 0) {
      float over = mLayout.getLineWidth(0) - mMarqueeScroll - (getWidth() - getCompoundPaddingLeft() - getCompoundPaddingRight());
      return over > 0 ? Math.min(1f, over / Math.max(1, getHorizontalFadingEdgeLength())) : 0f;
    }
    return 0f;
  }

  // ---------------------------------------------------------------- geometry
  public int getLineBounds(int line, Rect bounds) {
    if (mLayout == null) { if (bounds != null) bounds.set(0, 0, 0, 0); return 0; }
    int baseline = mLayout.getLineBounds(line, bounds);
    int voffset = getExtendedPaddingTop() + getVerticalOffsetForBounds();
    if (bounds != null) bounds.offset(getCompoundPaddingLeft(), voffset);
    return baseline + voffset;
  }
  private int getVerticalOffsetForBounds() {
    int gravity = mGravity & Gravity.VERTICAL_GRAVITY_MASK;
    if (gravity == Gravity.TOP || mLayout == null) return 0;
    int boxht = getMeasuredHeight() - getExtendedPaddingTop() - getExtendedPaddingBottom(), textht = mLayout.getHeight();
    if (textht >= boxht) return 0;
    return gravity == Gravity.BOTTOM ? boxht - textht : (boxht - textht) >> 1;
  }
  /** scrolls so the offset is visible; true when the scroll position changed */
  public boolean bringPointIntoView(int offset) {
    if (mLayout == null) return false;
    int line = mLayout.getLineForOffset(Math.max(0, Math.min(offset, mText.length())));
    int x = (int) mLayout.getPrimaryHorizontal(offset), top = mLayout.getLineTop(line), bottom = mLayout.getLineTop(line + 1);
    int hspace = getWidth() - getCompoundPaddingLeft() - getCompoundPaddingRight(), vspace = getHeight() - getExtendedPaddingTop() - getExtendedPaddingBottom();
    if (hspace <= 0 || vspace <= 0) return false;
    int sx = getScrollX(), sy = getScrollY();
    if (x < sx) sx = x; else if (x > sx + hspace) sx = x - hspace;
    if (top < sy) sy = top; else if (bottom > sy + vspace) sy = bottom - vspace;
    if (sx == getScrollX() && sy == getScrollY()) return false;
    scrollTo(sx, sy);
    return true;
  }
  public boolean bringPointIntoView(int offset, boolean requestRectWithoutFocus) { return bringPointIntoView(offset); }
  /** moves the cursor into the visible lines when it is outside them */
  public boolean moveCursorToVisibleOffset() {
    if (!(mText instanceof android.text.Spannable) || mLayout == null) return false;
    int start = getSelectionStart(), end = getSelectionEnd();
    if (start != end) return false;
    int line = mLayout.getLineForOffset(start);
    int top = getScrollY(), bottom = top + getHeight() - getExtendedPaddingTop() - getExtendedPaddingBottom();
    int lineTop = mLayout.getLineTop(line), lineBottom = mLayout.getLineTop(line + 1);
    int target = line;
    if (lineTop < top) target = mLayout.getLineForVertical(top + (lineBottom - lineTop));
    else if (lineBottom > bottom) target = mLayout.getLineForVertical(bottom - (lineBottom - lineTop));
    if (target == line) return false;
    int off = mLayout.getOffsetForHorizontal(target, mLayout.getPrimaryHorizontal(start));
    android.text.Selection.setSelection((android.text.Spannable) mText, off);
    return true;
  }

  // ---------------------------------------------------------------- editing and the input method
  protected boolean getDefaultEditable() { return false; }
  protected android.text.method.MovementMethod getDefaultMovementMethod() { return null; }
  public boolean getShowSoftInputOnFocus() { return mShowSoftInputOnFocus; }
  boolean mShowSoftInputOnFocus = true;
  public final void setShowSoftInputOnFocus(boolean show) { mShowSoftInputOnFocus = show; }
  String mPrivateImeOptions;
  public void setPrivateImeOptions(String type) { mPrivateImeOptions = type; }
  public String getPrivateImeOptions() { return mPrivateImeOptions; }
  android.os.Bundle mInputExtras;
  public void setInputExtras(int xmlResId) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
    android.content.res.XmlResourceParser parser = getResources().getXml(xmlResId);
    mInputExtras = new android.os.Bundle();
    getResources().parseBundleExtras(parser, mInputExtras);
  }
  public android.os.Bundle getInputExtras(boolean create) { if (mInputExtras == null && create) mInputExtras = new android.os.Bundle(); return mInputExtras; }
  android.os.LocaleList mImeHintLocales;
  public void setImeHintLocales(android.os.LocaleList hintLocales) { mImeHintLocales = hintLocales; }
  public android.os.LocaleList getImeHintLocales() { return mImeHintLocales; }
  public boolean isSuggestionsEnabled() {
    if ((mInputType & EditorInfo.TYPE_MASK_CLASS) != EditorInfo.TYPE_CLASS_TEXT) return false;
    if ((mInputType & EditorInfo.TYPE_TEXT_FLAG_NO_SUGGESTIONS) > 0) return false;
    int variation = mInputType & EditorInfo.TYPE_MASK_VARIATION;
    return variation == EditorInfo.TYPE_TEXT_VARIATION_NORMAL || variation == EditorInfo.TYPE_TEXT_VARIATION_EMAIL_SUBJECT || variation == EditorInfo.TYPE_TEXT_VARIATION_LONG_MESSAGE
        || variation == EditorInfo.TYPE_TEXT_VARIATION_SHORT_MESSAGE || variation == EditorInfo.TYPE_TEXT_VARIATION_WEB_EDIT_TEXT;
  }
  public boolean extractText(android.view.inputmethod.ExtractedTextRequest request, android.view.inputmethod.ExtractedText outText) {
    if (outText == null) return false;
    CharSequence content = mText;
    outText.text = request != null && (request.flags & android.view.inputmethod.InputConnection.GET_TEXT_WITH_STYLES) != 0 ? content : content.toString();
    outText.startOffset = 0;
    outText.partialStartOffset = outText.partialEndOffset = -1;
    outText.selectionStart = getSelectionStart();
    outText.selectionEnd = getSelectionEnd();
    outText.flags = mSingleLine ? android.view.inputmethod.ExtractedText.FLAG_SINGLE_LINE : 0;
    return true;
  }
  public void setExtractedText(android.view.inputmethod.ExtractedText text) {
    if (text.text != null) {
      if (text.partialStartOffset < 0 || !(mText instanceof Editable)) setText(text.text, BufferType.EDITABLE);
      else {
        int n = mText.length(), start = Math.max(0, Math.min(text.partialStartOffset, n)), end = Math.max(0, Math.min(text.partialEndOffset, n));
        ((Editable) mText).replace(start, end, text.text);
      }
    }
    if (mText instanceof android.text.Spannable) {
      int n = mText.length();
      setSelection(Math.max(0, Math.min(text.selectionStart, n)), Math.max(0, Math.min(text.selectionEnd, n)));
    }
  }
  public void onCommitCompletion(android.view.inputmethod.CompletionInfo text) {}
  public void onCommitCorrection(android.view.inputmethod.CorrectionInfo info) {}
  public void onBeginBatchEdit() {}
  public void onEndBatchEdit() {}
  public boolean onPrivateIMECommand(String action, android.os.Bundle data) { return false; }
  /** the standard context menu items, on the selection and the clipboard */
  public boolean onTextContextMenuItem(int id) {
    int min = 0, max = mText.length();
    if (hasSelection()) { int a = getSelectionStart(), b = getSelectionEnd(); min = Math.max(0, Math.min(a, b)); max = Math.max(0, Math.max(a, b)); }
    android.content.ClipboardManager cm = (android.content.ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
    switch (id) {
      case android.R.id.selectAll: selectAll(); return true;
      case android.R.id.copy:
        if (cm != null) cm.setPrimaryClip(android.content.ClipData.newPlainText(null, mText.subSequence(min, max)));
        return true;
      case android.R.id.cut:
        if (cm != null) cm.setPrimaryClip(android.content.ClipData.newPlainText(null, mText.subSequence(min, max)));
        if (mText instanceof Editable) { ((Editable) mText).delete(min, max); setSelection(min); }
        return true;
      case android.R.id.paste:
      case android.R.id.pasteAsPlainText:
        if (cm != null && mText instanceof Editable && cm.hasPrimaryClip() && cm.getPrimaryClip().getItemCount() > 0) {
          CharSequence p = cm.getPrimaryClip().getItemAt(0).coerceToText(getContext());
          if (id == android.R.id.pasteAsPlainText && p != null) p = p.toString();
          if (p != null) { ((Editable) mText).replace(min, max, p); setSelection(min + p.length()); }
        }
        return true;
      default: return false;
    }
  }
  android.view.ActionMode.Callback mSelectionCallback, mInsertionCallback;
  public void setCustomSelectionActionModeCallback(android.view.ActionMode.Callback actionModeCallback) { mSelectionCallback = actionModeCallback; }
  public android.view.ActionMode.Callback getCustomSelectionActionModeCallback() { return mSelectionCallback; }
  public void setCustomInsertionActionModeCallback(android.view.ActionMode.Callback actionModeCallback) { mInsertionCallback = actionModeCallback; }
  public android.view.ActionMode.Callback getCustomInsertionActionModeCallback() { return mInsertionCallback; }
  android.view.textclassifier.TextClassifier mTextClassifier;
  public void setTextClassifier(android.view.textclassifier.TextClassifier textClassifier) { mTextClassifier = textClassifier; }
  public android.view.textclassifier.TextClassifier getTextClassifier() { return mTextClassifier != null ? mTextClassifier : android.view.textclassifier.TextClassifier.NO_OP; }
  /** logs this view and its text (Android's debug dump) */
  public void debug(int depth) {
    StringBuilder b = new StringBuilder();
    for (int i = 0; i < depth; i++) b.append("  ");
    b.append(getClass().getName()).append(" frame={").append(getLeft()).append(", ").append(getTop()).append(", ").append(getRight()).append(", ").append(getBottom())
        .append("} scroll={").append(getScrollX()).append(", ").append(getScrollY()).append("} mText=\"").append(mText).append("\"");
    android.util.Log.d("View", b.toString());
  }
  /** web: the selection of the page's text field */
  public void webSetSelection(int start, int end) {
    if (!(mText instanceof android.text.Spannable)) return;
    int n = mText.length();
    start = Math.max(0, Math.min(start, n)); end = Math.max(0, Math.min(end, n));
    if (start == android.text.Selection.getSelectionStart(mText) && end == android.text.Selection.getSelectionEnd(mText)) return;
    android.text.Selection.setSelection((android.text.Spannable) mText, start, end);
    onSelectionChanged(start, end);
  }
}
