package android.widget;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import a2w.Attrs;
import a2w.Values;

public class ImageView extends View {
  public enum ScaleType { MATRIX, FIT_XY, FIT_START, FIT_CENTER, FIT_END, CENTER, CENTER_CROP, CENTER_INSIDE }
  int mResource;
  Uri mUri;
  Matrix mMatrix = new Matrix(), mDrawMatrix;
  ScaleType mScaleType = ScaleType.FIT_CENTER;
  boolean mHaveFrame, mAdjustViewBounds, mBaselineAlignBottom, mCropToPadding;
  int mMaxWidth = Integer.MAX_VALUE, mMaxHeight = Integer.MAX_VALUE;
  ColorFilter mColorFilter;
  int mAlpha = 255, mViewAlphaScale = 256;
  boolean mColorMod;
  Drawable mDrawable;
  int[] mState;
  boolean mMergeState;
  int mLevel;
  int mDrawableWidth, mDrawableHeight;
  int mBaseline = -1;
  private final RectF mTempSrc = new RectF(), mTempDst = new RectF();

  public ImageView(Context c) { super(c); }
  public ImageView(Context c, AttributeSet a) { this(c, a, 0); }
  public ImageView(Context c, AttributeSet a, int d) {
    super(c, a, d);
    if (a instanceof Attrs) {
      Attrs at = (Attrs) a;
      String v;
      if ((v = at.a("src")) != null && Values.isRef(v)) { int id = Values.refId(v); if (id != 0) setImageDrawable(getResources().getDrawable(id)); }
      else if (v != null && v.startsWith("#")) setImageDrawable(new android.graphics.drawable.ColorDrawable(Values.parseColor(v)));
      if ((v = at.a("baselineAlignBottom")) != null) mBaselineAlignBottom = v.equals("true");
      if ((v = at.a("baseline")) != null) mBaseline = (int) dim(v);
      if ((v = at.a("adjustViewBounds")) != null) setAdjustViewBounds(v.equals("true"));
      if ((v = at.a("maxWidth")) != null) mMaxWidth = (int) dim(v);
      if ((v = at.a("maxHeight")) != null) mMaxHeight = (int) dim(v);
      if ((v = at.a("scaleType")) != null) setScaleType(scaleType(v));
      if ((v = at.a("tint")) != null) setColorFilter(Values.isRef(v) ? getResources().getColor(Values.refId(v)) : Values.parseColor(v), PorterDuff.Mode.SRC_ATOP);
      if ((v = at.a("cropToPadding")) != null) mCropToPadding = v.equals("true");
    }
  }
  static ScaleType scaleType(String v) {
    switch (v) {
      case "matrix": case "0": return ScaleType.MATRIX;
      case "fitXY": case "1": return ScaleType.FIT_XY;
      case "fitStart": case "2": return ScaleType.FIT_START;
      case "fitCenter": case "3": return ScaleType.FIT_CENTER;
      case "fitEnd": case "4": return ScaleType.FIT_END;
      case "center": case "5": return ScaleType.CENTER;
      case "centerCrop": case "6": return ScaleType.CENTER_CROP;
      case "centerInside": case "7": return ScaleType.CENTER_INSIDE;
    }
    return ScaleType.FIT_CENTER;
  }
  @Override protected boolean verifyDrawable(Drawable dr) { return mDrawable == dr || super.verifyDrawable(dr); }
  @Override public void invalidateDrawable(Drawable dr) { if (dr == mDrawable) invalidate(); else super.invalidateDrawable(dr); }
  public void setAdjustViewBounds(boolean a) { mAdjustViewBounds = a; if (a) setScaleType(ScaleType.FIT_CENTER); }
  public boolean getAdjustViewBounds() { return mAdjustViewBounds; }
  public void setMaxWidth(int w) { mMaxWidth = w; }
  public void setMaxHeight(int h) { mMaxHeight = h; }
  public Drawable getDrawable() { return mDrawable; }
  public void setImageResource(int resId) {
    if (mUri != null || mResource != resId) {
      updateDrawable(null);
      mResource = resId;
      mUri = null;
      resolveUri();
      requestLayout();
      invalidate();
    }
  }
  public void setImageURI(Uri uri) {
    if (mResource != 0 || (mUri != uri && (uri == null || mUri == null || !uri.equals(mUri)))) {
      updateDrawable(null);
      mResource = 0;
      mUri = uri;
      if (uri != null) { Drawable d = Drawable.createFromPath(uri.getPath()); updateDrawable(d); }
      requestLayout();
      invalidate();
    }
  }
  public void setImageDrawable(Drawable drawable) {
    if (mDrawable != drawable) {
      mResource = 0;
      mUri = null;
      int oldWidth = mDrawableWidth, oldHeight = mDrawableHeight;
      updateDrawable(drawable);
      if (oldWidth != mDrawableWidth || oldHeight != mDrawableHeight) requestLayout();
      invalidate();
    }
  }
  public void setImageBitmap(Bitmap bm) { setImageDrawable(new BitmapDrawable(getContext().getResources(), bm)); }
  public void setImageState(int[] state, boolean merge) { mState = state; mMergeState = merge; if (mDrawable != null) { refreshDrawableState(); resizeFromDrawable(); } }
  @Override public void setSelected(boolean s) { super.setSelected(s); resizeFromDrawable(); }
  public void setImageLevel(int level) { mLevel = level; if (mDrawable != null) { mDrawable.setLevel(level); resizeFromDrawable(); } }
  public void setScaleType(ScaleType s) {
    if (s == null) throw new NullPointerException();
    if (mScaleType != s) { mScaleType = s; setWillNotCacheDrawing(s == ScaleType.CENTER); requestLayout(); invalidate(); }
  }
  public ScaleType getScaleType() { return mScaleType; }
  public Matrix getImageMatrix() { return mMatrix; }
  public void setImageMatrix(Matrix m) {
    if (m != null && m.isIdentity()) m = null;
    if (m == null && !mMatrix.isIdentity() || m != null && !mMatrix.equals(m)) { mMatrix.set(m); configureBounds(); invalidate(); }
  }
  public boolean getCropToPadding() { return mCropToPadding; }
  public void setCropToPadding(boolean c) { mCropToPadding = c; requestLayout(); invalidate(); }
  private void resolveUri() {
    if (mDrawable != null) return;
    Drawable d = null;
    if (mResource != 0) {
      try { d = getResources().getDrawable(mResource); }
      catch (Exception e) { android.util.Log.w("ImageView", "Unable to find resource: " + mResource, e); mUri = null; }
    }
    updateDrawable(d);
  }
  @Override protected int[] onCreateDrawableState(int extra) {
    if (mState == null) return super.onCreateDrawableState(extra);
    else if (!mMergeState) return mState;
    else return mergeDrawableStates(super.onCreateDrawableState(extra + mState.length), mState);
  }
  private void updateDrawable(Drawable d) {
    if (mDrawable != null) { mDrawable.setCallback(null); unscheduleDrawable(mDrawable); }
    mDrawable = d;
    if (d != null) {
      d.setCallback(this);
      if (d.isStateful()) d.setState(getDrawableState());
      d.setLevel(mLevel);
      d.setVisible(getVisibility() == VISIBLE, true);
      mDrawableWidth = d.getIntrinsicWidth();
      mDrawableHeight = d.getIntrinsicHeight();
      applyColorMod();
      configureBounds();
    } else {
      mDrawableWidth = mDrawableHeight = -1;
    }
  }
  private void resizeFromDrawable() {
    Drawable d = mDrawable;
    if (d != null) {
      int w = d.getIntrinsicWidth(); if (w < 0) w = mDrawableWidth;
      int h = d.getIntrinsicHeight(); if (h < 0) h = mDrawableHeight;
      if (w != mDrawableWidth || h != mDrawableHeight) { mDrawableWidth = w; mDrawableHeight = h; requestLayout(); }
    }
  }
  @Override public void setVisibility(int v) { super.setVisibility(v); if (mDrawable != null) mDrawable.setVisible(v == VISIBLE, false); }
  @Override protected void onAttachedToWindow() { super.onAttachedToWindow(); if (mDrawable != null) mDrawable.setVisible(getVisibility() == VISIBLE, false); }
  @Override protected void onDetachedFromWindow() { super.onDetachedFromWindow(); if (mDrawable != null) mDrawable.setVisible(false, false); }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    resolveUri();
    int w, h;
    float desiredAspect = 0.0f;
    boolean resizeWidth = false, resizeHeight = false;
    final int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec), heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
    if (mDrawable == null) { mDrawableWidth = -1; mDrawableHeight = -1; w = h = 0; }
    else {
      w = mDrawableWidth; h = mDrawableHeight;
      if (w <= 0) w = 1;
      if (h <= 0) h = 1;
      if (mAdjustViewBounds) {
        resizeWidth = widthSpecMode != MeasureSpec.EXACTLY;
        resizeHeight = heightSpecMode != MeasureSpec.EXACTLY;
        desiredAspect = (float) w / (float) h;
      }
    }
    int pleft = mPaddingLeft, pright = mPaddingRight, ptop = mPaddingTop, pbottom = mPaddingBottom;
    int widthSize, heightSize;
    if (resizeWidth || resizeHeight) {
      widthSize = resolveAdjustedSize(w + pleft + pright, mMaxWidth, widthMeasureSpec);
      heightSize = resolveAdjustedSize(h + ptop + pbottom, mMaxHeight, heightMeasureSpec);
      if (desiredAspect != 0.0f) {
        float actualAspect = (float) (widthSize - pleft - pright) / (heightSize - ptop - pbottom);
        if (Math.abs(actualAspect - desiredAspect) > 0.0000001) {
          boolean done = false;
          if (resizeWidth) {
            int newWidth = (int) (desiredAspect * (heightSize - ptop - pbottom)) + pleft + pright;
            if (newWidth <= widthSize) { widthSize = newWidth; done = true; }
          }
          if (!done && resizeHeight) {
            int newHeight = (int) ((widthSize - pleft - pright) / desiredAspect) + ptop + pbottom;
            if (newHeight <= heightSize) heightSize = newHeight;
          }
        }
      }
    } else {
      w += pleft + pright; h += ptop + pbottom;
      w = Math.max(w, getSuggestedMinimumWidth());
      h = Math.max(h, getSuggestedMinimumHeight());
      widthSize = resolveSizeAndState(w, widthMeasureSpec, 0);
      heightSize = resolveSizeAndState(h, heightMeasureSpec, 0);
    }
    setMeasuredDimension(widthSize, heightSize);
  }
  private int resolveAdjustedSize(int desiredSize, int maxSize, int measureSpec) {
    int result = desiredSize;
    int specMode = MeasureSpec.getMode(measureSpec), specSize = MeasureSpec.getSize(measureSpec);
    switch (specMode) {
      case MeasureSpec.UNSPECIFIED: result = Math.min(desiredSize, maxSize); break;
      case MeasureSpec.AT_MOST: result = Math.min(Math.min(desiredSize, specSize), maxSize); break;
      case MeasureSpec.EXACTLY: result = specSize; break;
    }
    return result;
  }
  @Override protected boolean setFrame(int l, int t, int r, int b) {
    boolean changed = super.setFrame(l, t, r, b);
    mHaveFrame = true;
    configureBounds();
    return changed;
  }
  private void configureBounds() {
    if (mDrawable == null || !mHaveFrame) return;
    int dwidth = mDrawableWidth, dheight = mDrawableHeight;
    int vwidth = getWidth() - mPaddingLeft - mPaddingRight, vheight = getHeight() - mPaddingTop - mPaddingBottom;
    boolean fits = (dwidth < 0 || vwidth == dwidth) && (dheight < 0 || vheight == dheight);
    if (dwidth <= 0 || dheight <= 0 || ScaleType.FIT_XY == mScaleType) {
      mDrawable.setBounds(0, 0, vwidth, vheight);
      mDrawMatrix = null;
    } else {
      mDrawable.setBounds(0, 0, dwidth, dheight);
      if (ScaleType.MATRIX == mScaleType) mDrawMatrix = mMatrix.isIdentity() ? null : mMatrix;
      else if (fits) mDrawMatrix = null;
      else if (ScaleType.CENTER == mScaleType) {
        mDrawMatrix = mMatrix;
        mDrawMatrix.setTranslate((int) ((vwidth - dwidth) * 0.5f + 0.5f), (int) ((vheight - dheight) * 0.5f + 0.5f));
      } else if (ScaleType.CENTER_CROP == mScaleType) {
        mDrawMatrix = mMatrix;
        float scale, dx = 0, dy = 0;
        if (dwidth * vheight > vwidth * dheight) { scale = (float) vheight / (float) dheight; dx = (vwidth - dwidth * scale) * 0.5f; }
        else { scale = (float) vwidth / (float) dwidth; dy = (vheight - dheight * scale) * 0.5f; }
        mDrawMatrix.setScale(scale, scale);
        mDrawMatrix.postTranslate((int) (dx + 0.5f), (int) (dy + 0.5f));
      } else if (ScaleType.CENTER_INSIDE == mScaleType) {
        mDrawMatrix = mMatrix;
        float scale;
        if (dwidth <= vwidth && dheight <= vheight) scale = 1.0f;
        else scale = Math.min((float) vwidth / (float) dwidth, (float) vheight / (float) dheight);
        float dx = (int) ((vwidth - dwidth * scale) * 0.5f + 0.5f), dy = (int) ((vheight - dheight * scale) * 0.5f + 0.5f);
        mDrawMatrix.setScale(scale, scale);
        mDrawMatrix.postTranslate(dx, dy);
      } else {
        mTempSrc.set(0, 0, dwidth, dheight);
        mTempDst.set(0, 0, vwidth, vheight);
        mDrawMatrix = mMatrix;
        Matrix.ScaleToFit stf = mScaleType == ScaleType.FIT_START ? Matrix.ScaleToFit.START : mScaleType == ScaleType.FIT_END ? Matrix.ScaleToFit.END : Matrix.ScaleToFit.CENTER;
        mDrawMatrix.setRectToRect(mTempSrc, mTempDst, stf);
      }
    }
  }
  @Override protected void drawableStateChanged() { super.drawableStateChanged(); Drawable d = mDrawable; if (d != null && d.isStateful()) d.setState(getDrawableState()); }
  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    if (mDrawable == null) return;
    if (mDrawableWidth == 0 || mDrawableHeight == 0) return;
    if (mDrawMatrix == null && mPaddingTop == 0 && mPaddingLeft == 0) mDrawable.draw(canvas);
    else {
      int saveCount = canvas.getSaveCount();
      canvas.save();
      if (mCropToPadding) canvas.clipRect(mScrollX + mPaddingLeft, mScrollY + mPaddingTop, mScrollX + mRight - mLeft - mPaddingRight, mScrollY + mBottom - mTop - mPaddingBottom);
      canvas.translate(mPaddingLeft, mPaddingTop);
      if (mDrawMatrix != null) canvas.concat(mDrawMatrix);
      mDrawable.draw(canvas);
      canvas.restoreToCount(saveCount);
    }
  }
  @Override public int getBaseline() {
    if (mBaselineAlignBottom) return getMeasuredHeight();
    return mBaseline;
  }
  public void setBaseline(int b) { if (mBaseline != b) { mBaseline = b; requestLayout(); } }
  public void setBaselineAlignBottom(boolean a) { if (mBaselineAlignBottom != a) { mBaselineAlignBottom = a; requestLayout(); } }
  public final void setColorFilter(int color, PorterDuff.Mode mode) { setColorFilter(new PorterDuffColorFilter(color, mode)); }
  public final void setColorFilter(int color) { setColorFilter(color, PorterDuff.Mode.SRC_ATOP); }
  public final void clearColorFilter() { setColorFilter(null); }
  public void setColorFilter(ColorFilter cf) { if (mColorFilter != cf) { mColorFilter = cf; mColorMod = true; applyColorMod(); invalidate(); } }
  public ColorFilter getColorFilter() { return mColorFilter; }
  public void setAlpha(int alpha) { alpha &= 0xFF; if (mAlpha != alpha) { mAlpha = alpha; mColorMod = true; applyColorMod(); invalidate(); } }
  public int getImageAlpha() { return mAlpha; }
  public void setImageAlpha(int a) { setAlpha(a); }
  private void applyColorMod() {
    if (mDrawable != null && mColorMod) {
      mDrawable.mutate();
      mDrawable.setColorFilter(mColorFilter);
      mDrawable.setAlpha(mAlpha * mViewAlphaScale >> 8);
    }
  }
  @Override public boolean isOpaque() { return super.isOpaque(); }
}
