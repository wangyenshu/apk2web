package android.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.*;
import android.util.AttributeSet;
import android.view.View;
import a2w.Attrs;
import a2w.Styles;
import a2w.Values;

public class ProgressBar extends View {
  static final int MAX_LEVEL = 10000;
  int mMinWidth = 24, mMaxWidth = 48, mMinHeight = 24, mMaxHeight = 48;
  int mProgress, mSecondaryProgress, mMax = 100, mMin;
  boolean mMinInitialized, mMaxInitialized;
  boolean mIndeterminate, mOnlyIndeterminate, mNoInvalidate, mInDrawing;
  Drawable mIndeterminateDrawable, mProgressDrawable, mCurrentDrawable;

  public ProgressBar(Context c) { this(c, null); }
  public ProgressBar(Context c, AttributeSet a) { this(c, a, 0); }
  public ProgressBar(Context c, AttributeSet a, int d) { this(c, a, d, styleFor(d)); }
  /** the framework style of a default style attribute (progressBarStyleHorizontal...) */
  static String styleFor(int defStyleAttr) {
    if (defStyleAttr == android.R.attr.progressBarStyleHorizontal) return "Widget.ProgressBar.Horizontal";
    if (defStyleAttr == android.R.attr.progressBarStyleSmall || defStyleAttr == android.R.attr.progressBarStyleSmallInverse || defStyleAttr == android.R.attr.progressBarStyleSmallTitle) return "Widget.ProgressBar.Small";
    if (defStyleAttr == android.R.attr.progressBarStyleLarge || defStyleAttr == android.R.attr.progressBarStyleLargeInverse) return "Widget.ProgressBar.Large";
    return "Widget.ProgressBar";
  }
  public ProgressBar(Context c, AttributeSet a, int d, int r) { this(c, a, d); }
  ProgressBar(Context c, AttributeSet a, int d, String fw) {
    super(c, defaults(a, fw), d);
    Attrs at = mCtorAttrs instanceof Attrs ? (Attrs) mCtorAttrs : Attrs.defaultsOnly(Styles.framework(fw));
    String v;
    mNoInvalidate = true;
    // bitmap progress drawables tile (as the platform's tileify)
    if ((v = at.a("progressDrawable")) != null) { Drawable dr = drawable(v); if (dr != null) { if (needsTileify(dr)) setProgressDrawableTiled(dr); else setProgressDrawable(dr); } }
    if ((v = at.a("indeterminateDrawable")) != null) { Drawable dr = drawable(v); if (dr != null) setIndeterminateDrawable(tileifyIndeterminate(dr)); }
    if ((v = at.a("minWidth")) != null) mMinWidth = (int) dim(v);
    if ((v = at.a("maxWidth")) != null) mMaxWidth = (int) dim(v);
    if ((v = at.a("minHeight")) != null) mMinHeight = (int) dim(v);
    if ((v = at.a("maxHeight")) != null) mMaxHeight = (int) dim(v);
    if ((v = at.a("min")) != null) setMin(Values.parseInt(v, 0));
    if ((v = at.a("max")) != null) setMax(Values.parseInt(v, 100));
    if ((v = at.a("interpolator")) != null && Values.isRef(v)) setInterpolator(c, Values.refId(v));
    if ((v = at.a("progressTint")) != null) mProgressTintList = tintList(v);
    if ((v = at.a("progressTintMode")) != null) mProgressTintMode = CompoundButton.tintMode(v);
    if ((v = at.a("progressBackgroundTint")) != null) mProgressBackgroundTintList = tintList(v);
    if ((v = at.a("progressBackgroundTintMode")) != null) mProgressBackgroundTintMode = CompoundButton.tintMode(v);
    if ((v = at.a("secondaryProgressTint")) != null) mSecondaryProgressTintList = tintList(v);
    if ((v = at.a("secondaryProgressTintMode")) != null) mSecondaryProgressTintMode = CompoundButton.tintMode(v);
    if ((v = at.a("indeterminateTint")) != null) mIndeterminateTintList = tintList(v);
    if ((v = at.a("indeterminateTintMode")) != null) mIndeterminateTintMode = CompoundButton.tintMode(v);
    applyProgressTints();
    applyIndeterminateTint();
    if ((v = at.a("progress")) != null) setProgress(Values.parseInt(v, 0));
    if ((v = at.a("secondaryProgress")) != null) setSecondaryProgress(Values.parseInt(v, 0));
    mOnlyIndeterminate = Values.parseBool(at.a("indeterminateOnly"), false);
    mNoInvalidate = false;
    setIndeterminate(mOnlyIndeterminate || Values.parseBool(at.a("indeterminate"), false));
  }
  static AttributeSet defaults(AttributeSet a, String fw) {
    if (a instanceof Attrs) {
      Attrs at = (Attrs) a;
      if (at.getStyleAttribute() != 0) return a;
      return at.withDefaults(Styles.framework(fw));
    }
    if (a == null) return Attrs.defaultsOnly(Styles.framework(fw));
    return a;
  }
  Drawable drawable(String v) { if (Values.isRef(v)) { int id = Values.refId(v); return id == 0 ? null : getResources().getDrawable(id); } return null; }
  public synchronized boolean isIndeterminate() { return mIndeterminate; }
  public synchronized void setIndeterminate(boolean ind) {
    if ((!mOnlyIndeterminate || !mIndeterminate) && ind != mIndeterminate) {
      mIndeterminate = ind;
      if (ind) { mCurrentDrawable = mIndeterminateDrawable; startAnimation(); }
      else { mCurrentDrawable = mProgressDrawable; stopAnimation(); }
    }
  }
  public Drawable getIndeterminateDrawable() { return mIndeterminateDrawable; }
  public void setIndeterminateDrawable(Drawable d) {
    if (d != null) d.setCallback(this);
    mIndeterminateDrawable = d;
    applyIndeterminateTint();
    if (mIndeterminate) { mCurrentDrawable = mIndeterminateDrawable; postInvalidate(); }
  }
  public Drawable getProgressDrawable() { return mProgressDrawable; }
  public void setProgressDrawable(Drawable d) {
    boolean needUpdate = mProgressDrawable != null && d != mProgressDrawable;
    if (mProgressDrawable != null) mProgressDrawable.setCallback(null);
    if (d != null) {
      d.setCallback(this);
      int h = d.getMinimumHeight();
      if (mMaxHeight < h) { mMaxHeight = h; requestLayout(); }
    }
    mProgressDrawable = d;
    applyProgressTints();
    if (!mIndeterminate) { mCurrentDrawable = mProgressDrawable; postInvalidate(); }
    if (needUpdate) { updateDrawableBounds(getWidth(), getHeight()); refreshProgress(android.R.id.progress, mProgress); refreshProgress(android.R.id.secondaryProgress, mSecondaryProgress); }
  }
  Drawable getCurrentDrawable() { return mCurrentDrawable; }
  @Override protected boolean verifyDrawable(Drawable who) { return who == mProgressDrawable || who == mIndeterminateDrawable || super.verifyDrawable(who); }
  @Override public void postInvalidate() { if (!mNoInvalidate) super.postInvalidate(); }
  private void refreshProgress(int id, int progress) {
    int range = mMax - mMin;
    float scale = range > 0 ? (float) (progress - mMin) / (float) range : 0;
    final Drawable d = mCurrentDrawable;
    if (d != null) {
      Drawable progressDrawable = null;
      if (d instanceof LayerDrawable) progressDrawable = ((LayerDrawable) d).findDrawableByLayerId(id);
      final int level = (int) (scale * MAX_LEVEL);
      (progressDrawable != null ? progressDrawable : d).setLevel(level);
    } else invalidate();
    if (id == android.R.id.progress) onProgressRefresh(scale, true);
  }
  void onProgressRefresh(float scale, boolean fromUser) {}
  public synchronized void setProgress(int p) { setProgress(p, false); }
  synchronized void setProgress(int progress, boolean fromUser) {
    if (mIndeterminate) return;
    if (progress < mMin) progress = mMin;
    if (progress > mMax) progress = mMax;
    if (progress != mProgress) { mProgress = progress; refreshProgress(android.R.id.progress, mProgress); invalidate(); }
  }
  public synchronized void setSecondaryProgress(int s) {
    if (mIndeterminate) return;
    if (s < mMin) s = mMin;
    if (s > mMax) s = mMax;
    if (s != mSecondaryProgress) { mSecondaryProgress = s; refreshProgress(android.R.id.secondaryProgress, mSecondaryProgress); invalidate(); }
  }
  public synchronized int getProgress() { return mIndeterminate ? 0 : mProgress; }
  public synchronized int getSecondaryProgress() { return mIndeterminate ? 0 : mSecondaryProgress; }
  public synchronized int getMax() { return mMax; }
  /** the lowest progress (API 26); progress below it is raised to it */
  public synchronized void setMin(int min) {
    if (mMaxInitialized && min > mMax) min = mMax;
    mMinInitialized = true;
    if (min != mMin) {
      mMin = min;
      postInvalidate();
      if (mProgress < min) mProgress = min;
      refreshProgress(android.R.id.progress, mProgress);
    }
  }
  public synchronized int getMin() { return mMin; }
  public synchronized void setMax(int max) {
    if (mMinInitialized && max < mMin) max = mMin;
    mMaxInitialized = true;
    if (max < 0 && !mMinInitialized) max = 0;
    if (max != mMax) {
      mMax = max;
      postInvalidate();
      if (mProgress > max) mProgress = max;
      refreshProgress(android.R.id.progress, mProgress);
    }
  }
  public synchronized final void incrementProgressBy(int d) { setProgress(mProgress + d); }
  public synchronized final void incrementSecondaryProgressBy(int d) { setSecondaryProgress(mSecondaryProgress + d); }
  void startAnimation() {
    if (getVisibility() != VISIBLE) return;
    if (mIndeterminateDrawable instanceof Animatable) ((Animatable) mIndeterminateDrawable).start();
    postInvalidate();
  }
  void stopAnimation() { if (mIndeterminateDrawable instanceof Animatable) ((Animatable) mIndeterminateDrawable).stop(); postInvalidate(); }
  @Override public void setVisibility(int v) {
    if (getVisibility() != v) {
      super.setVisibility(v);
      if (mIndeterminate) { if (v == GONE || v == INVISIBLE) stopAnimation(); else startAnimation(); }
    }
  }
  @Override protected void onVisibilityChanged(View changed, int vis) {
    super.onVisibilityChanged(changed, vis);
    if (mIndeterminate) { if (vis == GONE || vis == INVISIBLE) stopAnimation(); else startAnimation(); }
  }
  @Override public void invalidateDrawable(Drawable dr) { if (!mInDrawing) { if (verifyDrawable(dr)) invalidate(); else super.invalidateDrawable(dr); } }
  @Override protected void onSizeChanged(int w, int h, int ow, int oh) { updateDrawableBounds(w, h); }
  private void updateDrawableBounds(int w, int h) {
    int right = w - mPaddingRight - mPaddingLeft, bottom = h - mPaddingBottom - mPaddingTop, top = 0, left = 0;
    if (mIndeterminateDrawable != null) {
      if (mOnlyIndeterminate && !(mIndeterminateDrawable instanceof AnimationDrawable)) {
        final int intrinsicWidth = mIndeterminateDrawable.getIntrinsicWidth(), intrinsicHeight = mIndeterminateDrawable.getIntrinsicHeight();
        final float intrinsicAspect = (float) intrinsicWidth / intrinsicHeight, boundAspect = (float) w / h;
        if (intrinsicWidth > 0 && intrinsicHeight > 0 && intrinsicAspect != boundAspect) {
          if (boundAspect > intrinsicAspect) { final int width = (int) (h * intrinsicAspect); left = (w - width) / 2; right = left + width; }
          else { final int height = (int) (w * (1 / intrinsicAspect)); top = (h - height) / 2; bottom = top + height; }
        }
      }
      mIndeterminateDrawable.setBounds(left, top, right, bottom);
    }
    if (mProgressDrawable != null) mProgressDrawable.setBounds(0, 0, right, bottom);
  }
  @Override protected synchronized void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    Drawable d = mCurrentDrawable;
    if (d != null) {
      canvas.save();
      canvas.translate(mPaddingLeft, mPaddingTop);
      mInDrawing = true;
      d.draw(canvas);
      mInDrawing = false;
      canvas.restore();
      if (mIndeterminate && d instanceof SpinnerDrawable) postInvalidateDelayed(80);
    }
  }
  @Override protected synchronized void onMeasure(int ws, int hs) {
    Drawable d = mCurrentDrawable;
    int dw = 0, dh = 0;
    if (d != null) {
      dw = Math.max(mMinWidth, Math.min(mMaxWidth, d.getIntrinsicWidth()));
      dh = Math.max(mMinHeight, Math.min(mMaxHeight, d.getIntrinsicHeight()));
    }
    updateDrawableState();
    dw += mPaddingLeft + mPaddingRight;
    dh += mPaddingTop + mPaddingBottom;
    setMeasuredDimension(resolveSizeAndState(dw, ws, 0), resolveSizeAndState(dh, hs, 0));
  }
  @Override protected void drawableStateChanged() { super.drawableStateChanged(); updateDrawableState(); }
  private void updateDrawableState() {
    int[] state = getDrawableState();
    if (mProgressDrawable != null && mProgressDrawable.isStateful()) mProgressDrawable.setState(state);
    if (mIndeterminateDrawable != null && mIndeterminateDrawable.isStateful()) mIndeterminateDrawable.setState(state);
  }
  @Override protected void onAttachedToWindow() { super.onAttachedToWindow(); if (mIndeterminate) startAnimation(); }
  @Override protected void onDetachedFromWindow() { if (mIndeterminate) stopAnimation(); super.onDetachedFromWindow(); }
  // ---------------------------------------------------------------- interpolator, sizes, tints, tiled drawables
  android.view.animation.Interpolator mInterpolator;
  public void setInterpolator(Context c, int id) { setInterpolator(android.view.animation.AnimationUtils.loadInterpolator(c, id)); }
  public void setInterpolator(android.view.animation.Interpolator i) { mInterpolator = i; }
  public android.view.animation.Interpolator getInterpolator() { if (mInterpolator == null) mInterpolator = new android.view.animation.LinearInterpolator(); return mInterpolator; }
  /** an indeterminate bar whose animation runs (shown in a visible window) */
  public boolean isAnimating() { return isIndeterminate() && getWindowVisibility() == VISIBLE && isShown(); }
  public void setMinWidth(int minWidth) { mMinWidth = minWidth; requestLayout(); }
  public int getMinWidth() { return mMinWidth; }
  public void setMaxWidth(int maxWidth) { mMaxWidth = maxWidth; requestLayout(); }
  public int getMaxWidth() { return mMaxWidth; }
  public void setMinHeight(int minHeight) { mMinHeight = minHeight; requestLayout(); }
  public int getMinHeight() { return mMinHeight; }
  public void setMaxHeight(int maxHeight) { mMaxHeight = maxHeight; requestLayout(); }
  public int getMaxHeight() { return mMaxHeight; }
  android.content.res.ColorStateList mProgressTintList, mProgressBackgroundTintList, mSecondaryProgressTintList, mIndeterminateTintList;
  android.graphics.PorterDuff.Mode mProgressTintMode, mProgressBackgroundTintMode, mSecondaryProgressTintMode, mIndeterminateTintMode;
  android.graphics.BlendMode mProgressBlendMode, mProgressBackgroundBlendMode, mSecondaryProgressBlendMode, mIndeterminateBlendMode;
  android.content.res.ColorStateList tintList(String v) {
    try {
      if (Values.isRef(v)) return getResources().getColorStateList(Values.refId(v));
      if (v.startsWith("#")) return android.content.res.ColorStateList.valueOf(Values.parseColor(v));
    } catch (RuntimeException e) { /* none */ }
    return null;
  }
  public void setProgressTintList(android.content.res.ColorStateList t) { mProgressTintList = t; applyProgressTints(); }
  public android.content.res.ColorStateList getProgressTintList() { return mProgressTintList; }
  public void setProgressTintMode(android.graphics.PorterDuff.Mode m) { mProgressTintMode = m; mProgressBlendMode = null; applyProgressTints(); }
  public android.graphics.PorterDuff.Mode getProgressTintMode() { return mProgressTintMode; }
  public void setProgressTintBlendMode(android.graphics.BlendMode m) { mProgressBlendMode = m; applyProgressTints(); }
  public android.graphics.BlendMode getProgressTintBlendMode() { return mProgressBlendMode; }
  public void setProgressBackgroundTintList(android.content.res.ColorStateList t) { mProgressBackgroundTintList = t; applyProgressTints(); }
  public android.content.res.ColorStateList getProgressBackgroundTintList() { return mProgressBackgroundTintList; }
  public void setProgressBackgroundTintMode(android.graphics.PorterDuff.Mode m) { mProgressBackgroundTintMode = m; mProgressBackgroundBlendMode = null; applyProgressTints(); }
  public android.graphics.PorterDuff.Mode getProgressBackgroundTintMode() { return mProgressBackgroundTintMode; }
  public void setProgressBackgroundTintBlendMode(android.graphics.BlendMode m) { mProgressBackgroundBlendMode = m; applyProgressTints(); }
  public android.graphics.BlendMode getProgressBackgroundTintBlendMode() { return mProgressBackgroundBlendMode; }
  public void setSecondaryProgressTintList(android.content.res.ColorStateList t) { mSecondaryProgressTintList = t; applyProgressTints(); }
  public android.content.res.ColorStateList getSecondaryProgressTintList() { return mSecondaryProgressTintList; }
  public void setSecondaryProgressTintMode(android.graphics.PorterDuff.Mode m) { mSecondaryProgressTintMode = m; mSecondaryProgressBlendMode = null; applyProgressTints(); }
  public android.graphics.PorterDuff.Mode getSecondaryProgressTintMode() { return mSecondaryProgressTintMode; }
  public void setSecondaryProgressTintBlendMode(android.graphics.BlendMode m) { mSecondaryProgressBlendMode = m; applyProgressTints(); }
  public android.graphics.BlendMode getSecondaryProgressTintBlendMode() { return mSecondaryProgressBlendMode; }
  public void setIndeterminateTintList(android.content.res.ColorStateList t) { mIndeterminateTintList = t; applyIndeterminateTint(); }
  public android.content.res.ColorStateList getIndeterminateTintList() { return mIndeterminateTintList; }
  public void setIndeterminateTintMode(android.graphics.PorterDuff.Mode m) { mIndeterminateTintMode = m; mIndeterminateBlendMode = null; applyIndeterminateTint(); }
  public android.graphics.PorterDuff.Mode getIndeterminateTintMode() { return mIndeterminateTintMode; }
  public void setIndeterminateTintBlendMode(android.graphics.BlendMode m) { mIndeterminateBlendMode = m; applyIndeterminateTint(); }
  public android.graphics.BlendMode getIndeterminateTintBlendMode() { return mIndeterminateBlendMode; }
  private static boolean tint(Drawable d, android.content.res.ColorStateList l, android.graphics.PorterDuff.Mode m, android.graphics.BlendMode b, int[] state) {
    if (d == null || (l == null && m == null && b == null)) return false;
    if (l != null) d.setTintList(l);
    if (b != null) d.setTintBlendMode(b); else if (m != null) d.setTintMode(m);
    if (d.isStateful()) d.setState(state);
    return true;
  }
  /** the tints go to the progress drawable's layers: progress, background, secondaryProgress (else the whole drawable) */
  void applyProgressTints() {
    if (mProgressDrawable == null) return;
    boolean any = mProgressTintList != null || mProgressTintMode != null || mProgressBlendMode != null || mProgressBackgroundTintList != null
        || mProgressBackgroundTintMode != null || mProgressBackgroundBlendMode != null || mSecondaryProgressTintList != null || mSecondaryProgressTintMode != null || mSecondaryProgressBlendMode != null;
    if (!any) return;
    mProgressDrawable = mProgressDrawable.mutate();
    if (!mIndeterminate) mCurrentDrawable = mProgressDrawable;
    int[] st = getDrawableState();
    Drawable p = layer(android.R.id.progress), bg = layer(android.R.id.background), sec = layer(android.R.id.secondaryProgress);
    if (p == null && !(mProgressDrawable instanceof LayerDrawable)) p = mProgressDrawable;
    tint(p, mProgressTintList, mProgressTintMode, mProgressBlendMode, st);
    tint(bg, mProgressBackgroundTintList, mProgressBackgroundTintMode, mProgressBackgroundBlendMode, st);
    tint(sec, mSecondaryProgressTintList, mSecondaryProgressTintMode, mSecondaryProgressBlendMode, st);
    invalidate();
  }
  private Drawable layer(int id) { return mProgressDrawable instanceof LayerDrawable ? ((LayerDrawable) mProgressDrawable).findDrawableByLayerId(id) : null; }
  void applyIndeterminateTint() {
    if (mIndeterminateDrawable == null || (mIndeterminateTintList == null && mIndeterminateTintMode == null && mIndeterminateBlendMode == null)) return;
    mIndeterminateDrawable = mIndeterminateDrawable.mutate();
    if (mIndeterminate) mCurrentDrawable = mIndeterminateDrawable;
    tint(mIndeterminateDrawable, mIndeterminateTintList, mIndeterminateTintMode, mIndeterminateBlendMode, getDrawableState());
    invalidate();
  }
  /** a progress drawable whose bitmaps repeat across (the progress and secondary layers clipped to their level) */
  public void setProgressDrawableTiled(Drawable d) { if (d != null) d = tileify(d, false); setProgressDrawable(d); }
  /** an indeterminate animation whose frames' bitmaps repeat across */
  public void setIndeterminateDrawableTiled(Drawable d) { if (d != null) d = tileifyIndeterminate(d); setIndeterminateDrawable(d); }
  static boolean needsTileify(Drawable d) {
    if (d instanceof LayerDrawable) { LayerDrawable l = (LayerDrawable) d; for (int i = 0; i < l.getNumberOfLayers(); i++) if (needsTileify(l.getDrawable(i))) return true; return false; }
    return d instanceof BitmapDrawable;
  }
  Drawable tileify(Drawable d, boolean clip) {
    if (d instanceof LayerDrawable) {
      LayerDrawable orig = (LayerDrawable) d;
      int n = orig.getNumberOfLayers();
      Drawable[] out = new Drawable[n];
      for (int i = 0; i < n; i++) { int id = orig.getId(i); out[i] = tileify(orig.getDrawable(i), id == android.R.id.progress || id == android.R.id.secondaryProgress); }
      LayerDrawable clone = new LayerDrawable(out);
      for (int i = 0; i < n; i++) clone.setId(i, orig.getId(i));
      return clone;
    }
    if (d instanceof BitmapDrawable) {
      BitmapDrawable b = new BitmapDrawable(getResources(), ((BitmapDrawable) d).getBitmap());
      b.setTileModeXY(android.graphics.Shader.TileMode.REPEAT, android.graphics.Shader.TileMode.CLAMP);
      return clip ? new ClipDrawable(b, android.view.Gravity.LEFT, ClipDrawable.HORIZONTAL) : b;
    }
    return d;
  }
  Drawable tileifyIndeterminate(Drawable d) {
    if (d instanceof AnimationDrawable) {
      AnimationDrawable bg = (AnimationDrawable) d;
      boolean tile = false;
      for (int i = 0; i < bg.getNumberOfFrames(); i++) if (needsTileify(bg.getFrame(i))) tile = true;
      if (!tile) return d;
      AnimationDrawable n = new AnimationDrawable();
      n.setOneShot(bg.isOneShot());
      for (int i = 0; i < bg.getNumberOfFrames(); i++) { Drawable f = tileify(bg.getFrame(i), true); f.setLevel(10000); n.addFrame(f, bg.getDuration(i)); }
      n.setLevel(10000);
      return n;
    }
    return d;
  }
}
