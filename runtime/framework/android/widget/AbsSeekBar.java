package android.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import a2w.Attrs;
import a2w.Values;

public abstract class AbsSeekBar extends ProgressBar {
  Drawable mThumb;
  int mThumbOffset;
  float mTouchProgressOffset;
  boolean mIsUserSeekable = true;
  int mKeyProgressIncrement = 1;
  boolean mIsDragging;
  static final String[] DEFAULTS = {"a", "indeterminateOnly", "false", "a", "progressDrawable", "@android:drawable/progress_horizontal",
      "a", "indeterminateDrawable", "@android:drawable/progress_horizontal", "a", "minHeight", "20dip", "a", "maxHeight", "20dip",
      "a", "thumb", "@android:drawable/seek_thumb", "a", "thumbOffset", "8dip", "a", "focusable", "true", "a", "paddingLeft", "8dip", "a", "paddingRight", "8dip"};

  public AbsSeekBar(Context c) { this(c, null); }
  public AbsSeekBar(Context c, AttributeSet a) { this(c, a, 0); }
  public AbsSeekBar(Context c, AttributeSet a, int d) {
    super(c, seekDefaults(a), d, "Widget.ProgressBar.Horizontal");
    Attrs at = mCtorAttrs instanceof Attrs ? (Attrs) mCtorAttrs : Attrs.defaultsOnly(DEFAULTS);
    String v = at.a("thumb");
    Drawable thumb = v != null ? drawable(v) : null;
    if ((v = at.a("thumbTint")) != null) mThumbTintList = tintList(v);
    if ((v = at.a("thumbTintMode")) != null) mThumbTintMode = CompoundButton.tintMode(v);
    if ((v = at.a("tickMarkTint")) != null) mTickMarkTintList = tintList(v);
    if ((v = at.a("tickMarkTintMode")) != null) mTickMarkTintMode = CompoundButton.tintMode(v);
    setThumb(thumb);
    if ((v = at.a("tickMark")) != null) setTickMark(drawable(v));
    if ((v = at.a("splitTrack")) != null) mSplitTrack = Values.parseBool(v, false);
    v = at.a("thumbOffset");
    setThumbOffset(v != null ? (int) dim(v) : getThumbOffset());
  }
  public AbsSeekBar(Context c, AttributeSet a, int d, int r) { this(c, a, d); }
  // ---- thumb and tick mark tints, tick marks, split track
  android.content.res.ColorStateList mThumbTintList, mTickMarkTintList;
  android.graphics.PorterDuff.Mode mThumbTintMode, mTickMarkTintMode;
  android.graphics.BlendMode mThumbBlendMode, mTickMarkBlendMode;
  Drawable mTickMark;
  boolean mSplitTrack;
  public void setThumbTintList(android.content.res.ColorStateList t) { mThumbTintList = t; applyThumbTint(); }
  public android.content.res.ColorStateList getThumbTintList() { return mThumbTintList; }
  public void setThumbTintMode(android.graphics.PorterDuff.Mode m) { mThumbTintMode = m; mThumbBlendMode = null; applyThumbTint(); }
  public android.graphics.PorterDuff.Mode getThumbTintMode() { return mThumbTintMode; }
  public void setThumbTintBlendMode(android.graphics.BlendMode m) { mThumbBlendMode = m; applyThumbTint(); }
  public android.graphics.BlendMode getThumbTintBlendMode() { return mThumbBlendMode; }
  private void applyThumbTint() {
    if (mThumb == null || (mThumbTintList == null && mThumbTintMode == null && mThumbBlendMode == null)) return;
    mThumb = mThumb.mutate();
    if (mThumbTintList != null) mThumb.setTintList(mThumbTintList);
    if (mThumbBlendMode != null) mThumb.setTintBlendMode(mThumbBlendMode); else if (mThumbTintMode != null) mThumb.setTintMode(mThumbTintMode);
    if (mThumb.isStateful()) mThumb.setState(getDrawableState());
    invalidate();
  }
  /** a drawable drawn at each step of the bar (when max - min > 1) */
  public void setTickMark(Drawable tickMark) {
    if (mTickMark != null) mTickMark.setCallback(null);
    mTickMark = tickMark;
    if (tickMark != null) { tickMark.setCallback(this); if (tickMark.isStateful()) tickMark.setState(getDrawableState()); applyTickMarkTint(); }
    invalidate();
  }
  public Drawable getTickMark() { return mTickMark; }
  public void setTickMarkTintList(android.content.res.ColorStateList t) { mTickMarkTintList = t; applyTickMarkTint(); }
  public android.content.res.ColorStateList getTickMarkTintList() { return mTickMarkTintList; }
  public void setTickMarkTintMode(android.graphics.PorterDuff.Mode m) { mTickMarkTintMode = m; mTickMarkBlendMode = null; applyTickMarkTint(); }
  public android.graphics.PorterDuff.Mode getTickMarkTintMode() { return mTickMarkTintMode; }
  public void setTickMarkTintBlendMode(android.graphics.BlendMode m) { mTickMarkBlendMode = m; applyTickMarkTint(); }
  public android.graphics.BlendMode getTickMarkTintBlendMode() { return mTickMarkBlendMode; }
  private void applyTickMarkTint() {
    if (mTickMark == null || (mTickMarkTintList == null && mTickMarkTintMode == null && mTickMarkBlendMode == null)) return;
    mTickMark = mTickMark.mutate();
    if (mTickMarkTintList != null) mTickMark.setTintList(mTickMarkTintList);
    if (mTickMarkBlendMode != null) mTickMark.setTintBlendMode(mTickMarkBlendMode); else if (mTickMarkTintMode != null) mTickMark.setTintMode(mTickMarkTintMode);
    invalidate();
  }
  /** the track is not drawn under the thumb */
  public void setSplitTrack(boolean splitTrack) { mSplitTrack = splitTrack; invalidate(); }
  public boolean getSplitTrack() { return mSplitTrack; }
  float progressScale() { int range = getMax() - getMin(); return range > 0 ? (float) (getProgress() - getMin()) / range : 0; }
  static AttributeSet seekDefaults(AttributeSet a) {
    if (a instanceof Attrs) return ((Attrs) a).withDefaults(DEFAULTS);
    if (a == null) return Attrs.defaultsOnly(DEFAULTS);
    return a;
  }
  public void setThumb(Drawable thumb) {
    boolean needUpdate;
    if (mThumb != null && thumb != mThumb) { mThumb.setCallback(null); needUpdate = true; } else needUpdate = false;
    if (thumb != null) {
      thumb.setCallback(this);
      mThumbOffset = thumb.getIntrinsicWidth() / 2;
      if (needUpdate && (thumb.getIntrinsicWidth() != mThumb.getIntrinsicWidth() || thumb.getIntrinsicHeight() != mThumb.getIntrinsicHeight())) requestLayout();
    }
    mThumb = thumb;
    applyThumbTint();
    invalidate();
    if (needUpdate) { updateThumbPos(getWidth(), getHeight()); if (mThumb != null && mThumb.isStateful()) mThumb.setState(getDrawableState()); }
  }
  public Drawable getThumb() { return mThumb; }
  public int getThumbOffset() { return mThumbOffset; }
  public void setThumbOffset(int o) { mThumbOffset = o; invalidate(); }
  public void setKeyProgressIncrement(int i) { mKeyProgressIncrement = i < 0 ? -i : i; }
  public int getKeyProgressIncrement() { return mKeyProgressIncrement; }
  @Override public synchronized void setMax(int max) { super.setMax(max); adjustKeyIncrement(); }
  @Override public synchronized void setMin(int min) { super.setMin(min); adjustKeyIncrement(); }
  private void adjustKeyIncrement() {
    int range = getMax() - getMin();
    if (mKeyProgressIncrement == 0 || range / mKeyProgressIncrement > 20) setKeyProgressIncrement(Math.max(1, Math.round((float) range / 20)));
  }
  @Override protected boolean verifyDrawable(Drawable who) { return who == mThumb || who == mTickMark || super.verifyDrawable(who); }
  @Override protected void drawableStateChanged() {
    super.drawableStateChanged();
    if (mThumb != null && mThumb.isStateful()) mThumb.setState(getDrawableState());
    if (mTickMark != null && mTickMark.isStateful()) mTickMark.setState(getDrawableState());
  }
  @Override void onProgressRefresh(float scale, boolean fromUser) {
    Drawable thumb = mThumb;
    if (thumb != null) { setThumbPos(getWidth(), thumb, scale, Integer.MIN_VALUE); invalidate(); }
  }
  @Override protected void onSizeChanged(int w, int h, int ow, int oh) { super.onSizeChanged(w, h, ow, oh); updateThumbPos(w, h); }
  private void updateThumbPos(int w, int h) {
    Drawable d = getCurrentDrawable();
    Drawable thumb = mThumb;
    int thumbHeight = thumb == null ? 0 : thumb.getIntrinsicHeight();
    int trackHeight = Math.min(mMaxHeight, h - mPaddingTop - mPaddingBottom);
    float scale = progressScale();
    if (thumbHeight > trackHeight) {
      if (thumb != null) setThumbPos(w, thumb, scale, 0);
      int gapForCenteringTrack = (thumbHeight - trackHeight) / 2;
      if (d != null) d.setBounds(0, gapForCenteringTrack, w - mPaddingRight - mPaddingLeft, h - mPaddingBottom - gapForCenteringTrack - mPaddingTop);
    } else {
      if (d != null) d.setBounds(0, 0, w - mPaddingRight - mPaddingLeft, h - mPaddingBottom - mPaddingTop);
      int gap = (trackHeight - thumbHeight) / 2;
      if (thumb != null) setThumbPos(w, thumb, scale, gap);
    }
  }
  private void setThumbPos(int w, Drawable thumb, float scale, int gap) {
    int available = w - mPaddingLeft - mPaddingRight;
    int thumbWidth = thumb.getIntrinsicWidth(), thumbHeight = thumb.getIntrinsicHeight();
    available -= thumbWidth;
    available += mThumbOffset * 2;
    int thumbPos = (int) (scale * available);
    int topBound, bottomBound;
    if (gap == Integer.MIN_VALUE) { Rect oldBounds = thumb.getBounds(); topBound = oldBounds.top; bottomBound = oldBounds.bottom; }
    else { topBound = gap; bottomBound = gap + thumbHeight; }
    thumb.setBounds(thumbPos, topBound, thumbPos + thumbWidth, bottomBound);
  }
  @Override protected synchronized void onDraw(Canvas canvas) {
    if (mSplitTrack && mThumb != null) {
      // the track around the thumb
      int save = canvas.save();
      Rect tb = new Rect(mThumb.getBounds());
      tb.offset(mPaddingLeft - mThumbOffset, mPaddingTop);
      canvas.clipOutRect(tb);
      super.onDraw(canvas);
      canvas.restoreToCount(save);
    } else super.onDraw(canvas);
    drawTickMarks(canvas);
    if (mThumb != null) {
      canvas.save();
      canvas.translate(mPaddingLeft - mThumbOffset, mPaddingTop);
      mThumb.draw(canvas);
      canvas.restore();
    }
  }
  void drawTickMarks(Canvas canvas) {
    if (mTickMark == null) return;
    final int count = getMax() - getMin();
    if (count <= 1) return;
    final int w = mTickMark.getIntrinsicWidth(), h = mTickMark.getIntrinsicHeight();
    final int halfW = w >= 0 ? w / 2 : 1, halfH = h >= 0 ? h / 2 : 1;
    mTickMark.setBounds(-halfW, -halfH, halfW, halfH);
    final float spacing = (getWidth() - mPaddingLeft - mPaddingRight) / (float) count;
    final int saveCount = canvas.save();
    canvas.translate(mPaddingLeft, getHeight() / 2f);
    for (int i = 0; i <= count; i++) { mTickMark.draw(canvas); canvas.translate(spacing, 0); }
    canvas.restoreToCount(saveCount);
  }
  @Override protected synchronized void onMeasure(int ws, int hs) {
    Drawable d = getCurrentDrawable();
    int thumbHeight = mThumb == null ? 0 : mThumb.getIntrinsicHeight();
    int dw = 0, dh = 0;
    if (d != null) {
      dw = Math.max(mMinWidth, Math.min(mMaxWidth, d.getIntrinsicWidth()));
      dh = Math.max(mMinHeight, Math.min(mMaxHeight, d.getIntrinsicHeight()));
      dh = Math.max(thumbHeight, dh);
    }
    dw += mPaddingLeft + mPaddingRight;
    dh += mPaddingTop + mPaddingBottom;
    setMeasuredDimension(resolveSizeAndState(dw, ws, 0), resolveSizeAndState(dh, hs, 0));
  }
  @Override public boolean onTouchEvent(MotionEvent event) {
    if (!mIsUserSeekable || !isEnabled()) return false;
    switch (event.getActionMasked()) {
      case MotionEvent.ACTION_DOWN:
        setPressed(true);
        if (mThumb != null) invalidate();
        onStartTrackingTouch();
        trackTouchEvent(event);
        if (getParent() != null) getParent().requestDisallowInterceptTouchEvent(true);
        break;
      case MotionEvent.ACTION_MOVE:
        if (mIsDragging) trackTouchEvent(event);
        break;
      case MotionEvent.ACTION_UP:
        if (mIsDragging) { trackTouchEvent(event); onStopTrackingTouch(); setPressed(false); }
        invalidate();
        break;
      case MotionEvent.ACTION_CANCEL:
        if (mIsDragging) { onStopTrackingTouch(); setPressed(false); }
        invalidate();
        break;
    }
    return true;
  }
  private void trackTouchEvent(MotionEvent event) {
    final int width = getWidth();
    final int available = width - mPaddingLeft - mPaddingRight;
    int x = (int) event.getX();
    float scale, progress = 0;
    if (x < mPaddingLeft) scale = 0.0f;
    else if (x > width - mPaddingRight) scale = 1.0f;
    else { scale = (float) (x - mPaddingLeft) / (float) available; progress = mTouchProgressOffset; }
    final int range = getMax() - getMin();
    progress += scale * range + getMin();
    setProgress(Math.round(progress), true);
  }
  void onStartTrackingTouch() { mIsDragging = true; }
  void onStopTrackingTouch() { mIsDragging = false; }
  void onKeyChange() {}
  @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (isEnabled()) {
      int progress = getProgress();
      switch (keyCode) {
        // left/right and minus/plus step by keyProgressIncrement (mirrored in RTL)
        case KeyEvent.KEYCODE_DPAD_LEFT: case KeyEvent.KEYCODE_MINUS: case KeyEvent.KEYCODE_DPAD_RIGHT: case KeyEvent.KEYCODE_PLUS: case KeyEvent.KEYCODE_EQUALS: {
          int inc = keyCode == KeyEvent.KEYCODE_DPAD_LEFT || keyCode == KeyEvent.KEYCODE_MINUS ? -mKeyProgressIncrement : mKeyProgressIncrement;
          if (isLayoutRtl()) inc = -inc;
          int next = Math.max(getMin(), Math.min(getMax(), progress + inc));
          if (next == progress) break;
          setProgress(next, true); onKeyChange(); return true;
        }
      }
    }
    return super.onKeyDown(keyCode, event);
  }
}
