package android.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import a2w.Attrs;
import a2w.Values;
import a2w.WidgetDrawables;

/**
 * A two-state switch after AOSP's: a thumb sliding on a track at the end of the text, dragged or toggled by a click,
 * animated to its new state. Without thumb/track attributes the parts are drawn in code: Material for apps
 * targeting API 21+, else Holo with the on/off text on the thumb.
 */
public class Switch extends CompoundButton {
  private static final int THUMB_ANIMATION_DURATION = 250;
  private static final int TOUCH_MODE_IDLE = 0, TOUCH_MODE_DOWN = 1, TOUCH_MODE_DRAGGING = 2;
  private Drawable mThumbDrawable, mTrackDrawable;
  private ColorStateList mThumbTintList, mTrackTintList;
  private PorterDuff.Mode mThumbTintMode, mTrackTintMode;
  private BlendMode mThumbBlendMode, mTrackBlendMode;
  private int mThumbTextPadding, mSwitchMinWidth, mSwitchPadding;
  private boolean mSplitTrack, mShowText;
  private CharSequence mTextOn, mTextOff;
  private int mTouchMode, mTouchSlop, mMinFlingVelocity;
  private float mTouchX, mTouchY;
  private VelocityTracker mVelocityTracker;
  /** 0 off .. 1 on */
  private float mThumbPosition;
  private int mSwitchWidth, mSwitchHeight, mThumbWidth, mSwitchLeft, mSwitchTop, mSwitchRight, mSwitchBottom;
  private TextPaint mTextPaint;
  private ColorStateList mTextColors;
  private ValueAnimator mPositionAnimator;
  private Rect mTempRect;

  public Switch(Context c) { this(c, null); }
  public Switch(Context c, AttributeSet a) { this(c, a, 0); }
  public Switch(Context c, AttributeSet a, int d) { this(c, a, d, 0); }
  public Switch(Context c, AttributeSet a, int d, int r) {
    super(c, a, d, "Widget.CompoundButton.Switch");
    mTempRect = new Rect();
    mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    mTextPaint.density = a2w.Screen.DENSITY;
    boolean material = c.getApplicationInfo() != null && c.getApplicationInfo().targetSdkVersion >= 21;
    Attrs at = mCtorAttrs instanceof Attrs ? (Attrs) mCtorAttrs : Attrs.defaultsOnly(a2w.Styles.framework("Widget.CompoundButton.Switch"));
    String v;
    mTextOn = (v = at.a("textOn")) != null ? text(v) : "ON";
    mTextOff = (v = at.a("textOff")) != null ? text(v) : "OFF";
    mShowText = (v = at.a("showText")) != null ? Values.parseBool(v, !material) : !material;
    mThumbTextPadding = (v = at.a("thumbTextPadding")) != null ? (int) dim(v) : Math.round(12 * a2w.Screen.DENSITY);
    mSwitchMinWidth = (v = at.a("switchMinWidth")) != null ? (int) dim(v) : Math.round((material ? 4 : 96) * a2w.Screen.DENSITY);
    mSwitchPadding = (v = at.a("switchPadding")) != null ? (int) dim(v) : Math.round(16 * a2w.Screen.DENSITY);
    mSplitTrack = (v = at.a("splitTrack")) != null && Values.parseBool(v, false);
    int accent = WidgetDrawables.accent(c);
    boolean light = WidgetDrawables.lightTheme(c);
    Drawable thumb = (v = at.a("thumb")) != null && Values.isRef(v) ? getResources().getDrawable(Values.refId(v)) : null;
    Drawable track = (v = at.a("track")) != null && Values.isRef(v) ? getResources().getDrawable(Values.refId(v)) : null;
    if (thumb == null) thumb = new WidgetDrawables.SwitchPart(WidgetDrawables.SwitchPart.THUMB, material, accent, light);
    if (track == null) track = new WidgetDrawables.SwitchPart(WidgetDrawables.SwitchPart.TRACK, material, accent, light);
    if ((v = at.a("thumbTint")) != null) mThumbTintList = tintList(v);
    if ((v = at.a("thumbTintMode")) != null) mThumbTintMode = tintMode(v);
    if ((v = at.a("trackTint")) != null) mTrackTintList = tintList(v);
    if ((v = at.a("trackTintMode")) != null) mTrackTintMode = tintMode(v);
    setThumbDrawable(thumb);
    setTrackDrawable(track);
    // the switch text: switchTextAppearance, else small bold text in the theme's primary color
    mTextPaint.setTextSize(android.util.TypedValue.applyDimension(android.util.TypedValue.COMPLEX_UNIT_SP, 14, getResources().getDisplayMetrics()));
    mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
    mTextColors = material ? null : ColorStateList.valueOf(0xffffffff);
    if ((v = at.a("switchTextAppearance")) != null && Values.isRef(v)) setSwitchTextAppearance(c, Values.refId(v));
    ViewConfiguration config = ViewConfiguration.get(c);
    mTouchSlop = config.getScaledTouchSlop();
    mMinFlingVelocity = config.getScaledMinimumFlingVelocity();
    mThumbPosition = isChecked() ? 1 : 0;
    refreshDrawableState();
  }

  /** the switch text's size, color and typeface from a TextAppearance style */
  public void setSwitchTextAppearance(Context context, int resid) {
    android.content.res.TypedArray ta = context.obtainStyledAttributes(resid, new int[] {android.R.attr.textSize, android.R.attr.typeface, android.R.attr.textStyle, android.R.attr.textColor});
    try {
      ColorStateList colors = ta.getColorStateList(3);
      if (colors != null) mTextColors = colors;
      int ts = ta.getDimensionPixelSize(0, 0);
      if (ts != 0 && ts != mTextPaint.getTextSize()) mTextPaint.setTextSize(ts);
      int style = ta.getInt(2, -1);
      if (style >= 0) setSwitchTypeface(Typeface.defaultFromStyle(style), style);
    } finally { ta.recycle(); }
    requestLayout();
  }
  public void setSwitchTypeface(Typeface tf, int style) {
    if (style > 0) {
      tf = tf == null ? Typeface.defaultFromStyle(style) : Typeface.create(tf, style);
      setSwitchTypeface(tf);
      int need = ~(tf != null ? tf.getStyle() : 0) & style;
      mTextPaint.setFakeBoldText((need & Typeface.BOLD) != 0);
      mTextPaint.setTextSkewX((need & Typeface.ITALIC) != 0 ? -0.25f : 0);
    } else {
      mTextPaint.setFakeBoldText(false);
      mTextPaint.setTextSkewX(0);
      setSwitchTypeface(tf);
    }
  }
  public void setSwitchTypeface(Typeface tf) {
    if (mTextPaint.getTypeface() != tf) { mTextPaint.setTypeface(tf); requestLayout(); invalidate(); }
  }
  public void setSwitchPadding(int pixels) { mSwitchPadding = pixels; requestLayout(); }
  public int getSwitchPadding() { return mSwitchPadding; }
  public void setSwitchMinWidth(int pixels) { mSwitchMinWidth = pixels; requestLayout(); }
  public int getSwitchMinWidth() { return mSwitchMinWidth; }
  public void setThumbTextPadding(int pixels) { mThumbTextPadding = pixels; requestLayout(); }
  public int getThumbTextPadding() { return mThumbTextPadding; }
  public void setTrackDrawable(Drawable track) {
    if (mTrackDrawable != null) mTrackDrawable.setCallback(null);
    mTrackDrawable = track;
    if (track != null) track.setCallback(this);
    applyTrackTint();
    requestLayout();
  }
  public void setTrackResource(int resId) { setTrackDrawable(getContext().getDrawable(resId)); }
  public void setTrackIcon(android.graphics.drawable.Icon icon) { setTrackDrawable(icon == null ? null : icon.loadDrawable(getContext())); }
  public Drawable getTrackDrawable() { return mTrackDrawable; }
  public void setTrackTintList(ColorStateList tint) { mTrackTintList = tint; applyTrackTint(); }
  public ColorStateList getTrackTintList() { return mTrackTintList; }
  public void setTrackTintMode(PorterDuff.Mode mode) { mTrackTintMode = mode; mTrackBlendMode = null; applyTrackTint(); }
  public PorterDuff.Mode getTrackTintMode() { return mTrackTintMode; }
  public void setTrackTintBlendMode(BlendMode mode) { mTrackBlendMode = mode; applyTrackTint(); }
  public BlendMode getTrackTintBlendMode() { return mTrackBlendMode; }
  private void applyTrackTint() { mTrackDrawable = tinted(mTrackDrawable, mTrackTintList, mTrackTintMode, mTrackBlendMode); }
  public void setThumbDrawable(Drawable thumb) {
    if (mThumbDrawable != null) mThumbDrawable.setCallback(null);
    mThumbDrawable = thumb;
    if (thumb != null) thumb.setCallback(this);
    applyThumbTint();
    requestLayout();
  }
  public void setThumbResource(int resId) { setThumbDrawable(getContext().getDrawable(resId)); }
  public void setThumbIcon(android.graphics.drawable.Icon icon) { setThumbDrawable(icon == null ? null : icon.loadDrawable(getContext())); }
  public Drawable getThumbDrawable() { return mThumbDrawable; }
  public void setThumbTintList(ColorStateList tint) { mThumbTintList = tint; applyThumbTint(); }
  public ColorStateList getThumbTintList() { return mThumbTintList; }
  public void setThumbTintMode(PorterDuff.Mode mode) { mThumbTintMode = mode; mThumbBlendMode = null; applyThumbTint(); }
  public PorterDuff.Mode getThumbTintMode() { return mThumbTintMode; }
  public void setThumbTintBlendMode(BlendMode mode) { mThumbBlendMode = mode; applyThumbTint(); }
  public BlendMode getThumbTintBlendMode() { return mThumbBlendMode; }
  private void applyThumbTint() { mThumbDrawable = tinted(mThumbDrawable, mThumbTintList, mThumbTintMode, mThumbBlendMode); }
  private Drawable tinted(Drawable d, ColorStateList list, PorterDuff.Mode mode, BlendMode blend) {
    if (d == null || (list == null && mode == null && blend == null)) return d;
    d = d.mutate();
    if (list != null) d.setTintList(list);
    if (blend != null) d.setTintBlendMode(blend); else if (mode != null) d.setTintMode(mode);
    if (d.isStateful()) d.setState(getDrawableState());
    invalidate();
    return d;
  }
  public void setSplitTrack(boolean splitTrack) { mSplitTrack = splitTrack; invalidate(); }
  public boolean getSplitTrack() { return mSplitTrack; }
  public CharSequence getTextOn() { return mTextOn; }
  public void setTextOn(CharSequence textOn) { mTextOn = textOn; requestLayout(); }
  public CharSequence getTextOff() { return mTextOff; }
  public void setTextOff(CharSequence textOff) { mTextOff = textOff; requestLayout(); }
  public void setShowText(boolean showText) { if (mShowText != showText) { mShowText = showText; requestLayout(); } }
  public boolean getShowText() { return mShowText; }

  // ---------------------------------------------------------------- measure and layout
  private int textWidth(CharSequence t) { return t == null ? 0 : (int) Math.ceil(mTextPaint.measureText(t.toString())); }
  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    final Rect padding = mTempRect;
    final int thumbWidth, thumbHeight;
    if (mThumbDrawable != null) {
      if (!mThumbDrawable.getPadding(padding)) padding.setEmpty();
      thumbWidth = Math.max(0, mThumbDrawable.getIntrinsicWidth()) - padding.left - padding.right;
      thumbHeight = mThumbDrawable.getIntrinsicHeight();
    } else { thumbWidth = 0; thumbHeight = 0; }
    final int maxTextWidth = mShowText ? Math.max(textWidth(mTextOn), textWidth(mTextOff)) + mThumbTextPadding * 2 : 0;
    mThumbWidth = Math.max(maxTextWidth, thumbWidth);
    final int trackHeight;
    if (mTrackDrawable != null) { if (!mTrackDrawable.getPadding(padding)) padding.setEmpty(); trackHeight = mTrackDrawable.getIntrinsicHeight(); }
    else { padding.setEmpty(); trackHeight = 0; }
    mSwitchWidth = Math.max(mSwitchMinWidth, 2 * mThumbWidth + padding.left + padding.right);
    mSwitchHeight = Math.max(trackHeight, thumbHeight);
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    if (getMeasuredHeight() < mSwitchHeight) setMeasuredDimension(getMeasuredWidthAndState(), mSwitchHeight);
  }
  @Override public int getCompoundPaddingLeft() {
    if (!isLayoutRtl()) return super.getCompoundPaddingLeft();
    int p = super.getCompoundPaddingLeft() + mSwitchWidth;
    if (getText() != null && getText().length() > 0) p += mSwitchPadding;
    return p;
  }
  @Override public int getCompoundPaddingRight() {
    if (isLayoutRtl()) return super.getCompoundPaddingRight();
    int p = super.getCompoundPaddingRight() + mSwitchWidth;
    if (getText() != null && getText().length() > 0) p += mSwitchPadding;
    return p;
  }
  @Override protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    super.onLayout(changed, left, top, right, bottom);
    final int switchLeft, switchRight;
    if (isLayoutRtl()) { switchLeft = getPaddingLeft(); switchRight = switchLeft + mSwitchWidth; }
    else { switchRight = getWidth() - getPaddingRight(); switchLeft = switchRight - mSwitchWidth; }
    final int switchTop;
    switch (getGravity() & Gravity.VERTICAL_GRAVITY_MASK) {
      case Gravity.CENTER_VERTICAL: switchTop = (getPaddingTop() + getHeight() - getPaddingBottom()) / 2 - mSwitchHeight / 2; break;
      case Gravity.BOTTOM: switchTop = getHeight() - getPaddingBottom() - mSwitchHeight; break;
      default: switchTop = getPaddingTop();
    }
    mSwitchLeft = switchLeft; mSwitchRight = switchRight; mSwitchTop = switchTop; mSwitchBottom = switchTop + mSwitchHeight;
  }
  private int getThumbScrollRange() {
    if (mTrackDrawable == null) return 0;
    final Rect padding = mTempRect;
    if (!mTrackDrawable.getPadding(padding)) padding.setEmpty();
    return mSwitchWidth - mThumbWidth - padding.left - padding.right;
  }
  private int getThumbOffset() {
    float pos = isLayoutRtl() ? 1 - mThumbPosition : mThumbPosition;
    return (int) (pos * getThumbScrollRange() + 0.5f);
  }
  private void setThumbPosition(float position) { mThumbPosition = position; invalidate(); }

  // ---------------------------------------------------------------- drawing
  @Override public void draw(Canvas c) {
    if (mTempRect != null) {
      final Rect padding = mTempRect;
      int thumbInitialLeft = mSwitchLeft + getThumbOffset();
      if (mTrackDrawable != null) {
        if (!mTrackDrawable.getPadding(padding)) padding.setEmpty();
        thumbInitialLeft += padding.left;
        mTrackDrawable.setBounds(mSwitchLeft, mSwitchTop, mSwitchRight, mSwitchBottom);
      }
      if (mThumbDrawable != null) {
        if (!mThumbDrawable.getPadding(padding)) padding.setEmpty();
        mThumbDrawable.setBounds(thumbInitialLeft - padding.left, mSwitchTop, thumbInitialLeft + mThumbWidth + padding.right, mSwitchBottom);
      }
    }
    super.draw(c);
  }
  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    final Drawable track = mTrackDrawable, thumb = mThumbDrawable;
    if (track != null) {
      if (mSplitTrack && thumb != null) {
        int save = canvas.save();
        canvas.clipOutRect(thumb.getBounds());
        track.draw(canvas);
        canvas.restoreToCount(save);
      } else track.draw(canvas);
    }
    if (thumb != null) thumb.draw(canvas);
    if (mShowText) {
      CharSequence t = mThumbPosition > 0.5f ? mTextOn : mTextOff;
      if (t != null && t.length() > 0) {
        int[] state = getDrawableState();
        mTextPaint.setColor(mTextColors != null ? mTextColors.getColorForState(state, mTextColors.getDefaultColor()) : getCurrentTextColor());
        Rect b = thumb != null ? thumb.getBounds() : new Rect(0, 0, getWidth(), getHeight());
        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        float x = b.exactCenterX() - mTextPaint.measureText(t.toString()) / 2;
        float y = (mSwitchTop + mSwitchBottom) / 2f - (fm.ascent + fm.descent) / 2;
        canvas.drawText(t.toString(), x, y, mTextPaint);
      }
    }
  }
  @Override protected void drawableStateChanged() {
    super.drawableStateChanged();
    int[] state = getDrawableState();
    boolean changed = false;
    if (mThumbDrawable != null && mThumbDrawable.isStateful()) changed |= mThumbDrawable.setState(state);
    if (mTrackDrawable != null && mTrackDrawable.isStateful()) changed |= mTrackDrawable.setState(state);
    if (changed) invalidate();
  }
  @Override protected boolean verifyDrawable(Drawable who) { return super.verifyDrawable(who) || who == mThumbDrawable || who == mTrackDrawable; }
  @Override public void jumpDrawablesToCurrentState() {
    super.jumpDrawablesToCurrentState();
    if (mThumbDrawable != null) mThumbDrawable.jumpToCurrentState();
    if (mTrackDrawable != null) mTrackDrawable.jumpToCurrentState();
    if (mPositionAnimator != null && mPositionAnimator.isStarted()) { mPositionAnimator.end(); mPositionAnimator = null; }
  }

  // ---------------------------------------------------------------- touch: drag the thumb, or click to toggle
  private boolean hitThumb(float x, float y) {
    if (mThumbDrawable == null) return false;
    final int thumbOffset = getThumbOffset();
    if (!mThumbDrawable.getPadding(mTempRect)) mTempRect.setEmpty();
    final int thumbTop = mSwitchTop - mTouchSlop, thumbLeft = mSwitchLeft + thumbOffset - mTouchSlop;
    final int thumbRight = thumbLeft + mThumbWidth + mTempRect.left + mTempRect.right + mTouchSlop, thumbBottom = mSwitchBottom + mTouchSlop;
    return x > thumbLeft && x < thumbRight && y > thumbTop && y < thumbBottom;
  }
  @Override public boolean onTouchEvent(MotionEvent ev) {
    if (mVelocityTracker == null) mVelocityTracker = VelocityTracker.obtain();
    mVelocityTracker.addMovement(ev);
    switch (ev.getActionMasked()) {
      case MotionEvent.ACTION_DOWN: {
        final float x = ev.getX(), y = ev.getY();
        if (isEnabled() && hitThumb(x, y)) { mTouchMode = TOUCH_MODE_DOWN; mTouchX = x; mTouchY = y; }
        break;
      }
      case MotionEvent.ACTION_MOVE: {
        if (mTouchMode == TOUCH_MODE_DOWN) {
          final float x = ev.getX(), y = ev.getY();
          if (Math.abs(x - mTouchX) > mTouchSlop || Math.abs(y - mTouchY) > mTouchSlop) {
            mTouchMode = TOUCH_MODE_DRAGGING;
            if (getParent() != null) getParent().requestDisallowInterceptTouchEvent(true);
            mTouchX = x; mTouchY = y;
            return true;
          }
        } else if (mTouchMode == TOUCH_MODE_DRAGGING) {
          final float x = ev.getX();
          final int range = getThumbScrollRange();
          final float off = x - mTouchX;
          float dPos = range != 0 ? off / range : off > 0 ? 1 : -1;
          if (isLayoutRtl()) dPos = -dPos;
          final float newPos = Math.max(0, Math.min(1, mThumbPosition + dPos));
          if (newPos != mThumbPosition) { mTouchX = x; setThumbPosition(newPos); }
          return true;
        }
        break;
      }
      case MotionEvent.ACTION_UP: case MotionEvent.ACTION_CANCEL: {
        if (mTouchMode == TOUCH_MODE_DRAGGING) { stopDrag(ev); super.onTouchEvent(ev); return true; }
        mTouchMode = TOUCH_MODE_IDLE;
        mVelocityTracker.clear();
        break;
      }
    }
    return super.onTouchEvent(ev);
  }
  private void stopDrag(MotionEvent ev) {
    mTouchMode = TOUCH_MODE_IDLE;
    final boolean commit = ev.getAction() == MotionEvent.ACTION_UP && isEnabled();
    final boolean oldState = isChecked(), newState;
    if (commit) {
      mVelocityTracker.computeCurrentVelocity(1000);
      final float xvel = mVelocityTracker.getXVelocity();
      newState = Math.abs(xvel) > mMinFlingVelocity ? (isLayoutRtl() ? xvel < 0 : xvel > 0) : mThumbPosition > 0.5f;
    } else newState = oldState;
    if (newState != oldState) playSoundEffect(SoundEffectConstants.CLICK);
    setChecked(newState);
    MotionEvent cancel = MotionEvent.obtain(ev);
    cancel.setAction(MotionEvent.ACTION_CANCEL);
    super.onTouchEvent(cancel);
    cancel.recycle();
  }
  @Override public void toggle() { setChecked(!isChecked()); }
  /** the thumb slides to the new state (jumps while not attached or not laid out) */
  @Override public void setChecked(boolean checked) {
    super.setChecked(checked);
    checked = isChecked();
    if (mPositionAnimator != null) { mPositionAnimator.cancel(); mPositionAnimator = null; }
    if (isAttachedToWindow() && isLaidOut() && mTempRect != null) {
      mPositionAnimator = ValueAnimator.ofFloat(mThumbPosition, checked ? 1 : 0);
      mPositionAnimator.setDuration(THUMB_ANIMATION_DURATION);
      mPositionAnimator.addUpdateListener(a -> setThumbPosition((Float) a.getAnimatedValue()));
      mPositionAnimator.start();
    } else setThumbPosition(checked ? 1 : 0);
  }
  @Override public CharSequence getAccessibilityClassName() { return Switch.class.getName(); }
}
