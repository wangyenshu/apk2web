package android.graphics.drawable;

import android.content.res.Resources;
import android.graphics.*;

/** android.graphics.drawable.NinePatchDrawable: a NinePatch stretched over the bounds, with the chunk's padding. */
public class NinePatchDrawable extends Drawable {
  private NinePatch mNinePatch;
  private Paint mPaint;
  private Rect mPadding;
  private Rect mOptical;
  private int mTargetDensity = android.util.DisplayMetrics.DENSITY_DEFAULT;
  private int mBitmapWidth = -1, mBitmapHeight = -1;
  private Rect mScaledPadding, mScaledOptical;
  private boolean mMutated, mFilter = true;

  @Deprecated public NinePatchDrawable(Bitmap bitmap, byte[] chunk, Rect padding, String srcName) { this(null, bitmap, chunk, padding, srcName); }
  public NinePatchDrawable(Resources res, Bitmap bitmap, byte[] chunk, Rect padding, String srcName) { this(res, new NinePatch(bitmap, chunk, srcName), padding); }
  @Deprecated public NinePatchDrawable(NinePatch patch) { this(null, patch, null); }
  public NinePatchDrawable(Resources res, NinePatch patch) { this(res, patch, null); }
  private NinePatchDrawable(Resources res, NinePatch patch, Rect padding) {
    mNinePatch = patch;
    mPadding = padding != null ? new Rect(padding) : patch.a2wPadding();
    int[] ins = patch.getBitmap().a2wOpticalInsets();
    mOptical = ins == null ? new Rect() : new Rect(ins[0], ins[1], ins[2], ins[3]);
    if (res != null) mTargetDensity = res.getDisplayMetrics().densityDpi;
    computeSize();
  }

  private static int scaled(int v, int from, int to) { return from == to || from == 0 ? v : (v * to + (from >> 1)) / from; }
  private void computeSize() {
    int d = mNinePatch.getDensity();
    mBitmapWidth = scaled(mNinePatch.getWidth(), d, mTargetDensity);
    mBitmapHeight = scaled(mNinePatch.getHeight(), d, mTargetDensity);
    mScaledPadding = new Rect(scaled(mPadding.left, d, mTargetDensity), scaled(mPadding.top, d, mTargetDensity), scaled(mPadding.right, d, mTargetDensity), scaled(mPadding.bottom, d, mTargetDensity));
    mScaledOptical = new Rect(scaled(mOptical.left, d, mTargetDensity), scaled(mOptical.top, d, mTargetDensity), scaled(mOptical.right, d, mTargetDensity), scaled(mOptical.bottom, d, mTargetDensity));
  }
  public void setTargetDensity(Canvas canvas) { setTargetDensity(canvas.getDensity()); }
  public void setTargetDensity(android.util.DisplayMetrics metrics) { setTargetDensity(metrics.densityDpi); }
  public void setTargetDensity(int density) {
    if (density == 0) density = android.util.DisplayMetrics.DENSITY_DEFAULT;
    if (mTargetDensity != density) { mTargetDensity = density; computeSize(); invalidateSelf(); }
  }

  private Paint paint() { if (mPaint == null) { mPaint = new Paint(); mPaint.setDither(false); mPaint.setFilterBitmap(mFilter); } return mPaint; }
  public Paint getPaint() { return paint(); }

  @Override public void draw(Canvas canvas) {
    Rect b = getBounds();
    if (b.isEmpty()) return;
    boolean mirror = isAutoMirrored() && getLayoutDirection() == android.util.LayoutDirection.RTL;
    int save = -1;
    if (mirror) { save = canvas.save(); canvas.translate(b.right + b.left, 0); canvas.scale(-1, 1); }
    // the patch at the canvas's density: the bounds are in target-density pixels
    int d = mNinePatch.getDensity();
    if (d != 0 && mTargetDensity != d && canvas.getDensity() == 0) {
      if (save < 0) save = canvas.save();
      float s = mTargetDensity / (float) d;
      canvas.translate(b.left, b.top);
      canvas.scale(s, s);
      mNinePatch.draw(canvas, new RectF(0, 0, b.width() / s, b.height() / s));
    } else mNinePatch.draw(canvas, b, mPaint);
    if (save >= 0) canvas.restoreToCount(save);
  }

  @Override public boolean getPadding(Rect padding) {
    padding.set(mScaledPadding);
    if (isAutoMirrored() && getLayoutDirection() == android.util.LayoutDirection.RTL) { int l = padding.left; padding.left = padding.right; padding.right = l; }
    return (padding.left | padding.top | padding.right | padding.bottom) != 0;
  }
  public Insets getOpticalInsets() {
    Rect o = mScaledOptical;
    if (isAutoMirrored() && getLayoutDirection() == android.util.LayoutDirection.RTL) return Insets.of(o.right, o.top, o.left, o.bottom);
    return Insets.of(o);
  }
  @Override public void getOutline(Outline outline) {
    Rect b = getBounds();
    if (b.isEmpty()) return;
    int[] ol = mNinePatch.getBitmap().a2wOutlineInsets();
    if (ol != null) {
      int d = mNinePatch.getDensity();
      outline.setRoundRect(b.left + scaled(ol[0], d, mTargetDensity), b.top + scaled(ol[1], d, mTargetDensity),
          b.right - scaled(ol[2], d, mTargetDensity), b.bottom - scaled(ol[3], d, mTargetDensity), Float.intBitsToFloat(ol[4]) * mTargetDensity / Math.max(1, d));
      outline.setAlpha(ol[5] / 255f * (getAlpha() / 255f));
      return;
    }
    super.getOutline(outline);
  }

  @Override public void setAlpha(int alpha) {
    if (mPaint == null && alpha == 0xff) return;
    paint().setAlpha(alpha);
    invalidateSelf();
  }
  @Override public int getAlpha() { return mPaint == null ? 0xff : mPaint.getAlpha(); }
  @Override public void setColorFilter(ColorFilter colorFilter) {
    if (mPaint == null && colorFilter == null) return;
    paint().setColorFilter(colorFilter);
    invalidateSelf();
  }
  @Override public ColorFilter getColorFilter() { return mPaint == null ? null : mPaint.getColorFilter(); }
  public void setTintBlendMode(BlendMode blendMode) {
    String n = blendMode == null ? "SRC_IN" : blendMode.name();
    if (n.equals("PLUS")) n = "ADD"; else if (n.equals("MODULATE")) n = "MULTIPLY";
    PorterDuff.Mode m = PorterDuff.Mode.SRC_IN;
    for (PorterDuff.Mode v : PorterDuff.Mode.values()) if (v.name().equals(n)) m = v;
    setTintMode(m);
  }
  @Override public void setDither(boolean dither) {}
  @Override public void setFilterBitmap(boolean filter) { mFilter = filter; paint().setFilterBitmap(filter); invalidateSelf(); }
  @Override public boolean isFilterBitmap() { return mFilter; }
  @Override public int getIntrinsicWidth() { return mBitmapWidth; }
  @Override public int getIntrinsicHeight() { return mBitmapHeight; }
  @Override public int getMinimumWidth() { return mBitmapWidth; }
  @Override public int getMinimumHeight() { return mBitmapHeight; }
  @Override public int getOpacity() { return mNinePatch.hasAlpha() || (mPaint != null && mPaint.getAlpha() < 255) ? PixelFormat.TRANSLUCENT : PixelFormat.OPAQUE; }
  @Override public Region getTransparentRegion() { return mNinePatch.getTransparentRegion(getBounds()); }
  @Override public Drawable mutate() {
    if (!mMutated) { if (mPaint != null) mPaint = new Paint(mPaint); mPadding = new Rect(mPadding); mMutated = true; }
    return this;
  }
  @Override public ConstantState getConstantState() {
    final NinePatch p = mNinePatch; final Rect pad = new Rect(mPadding); final int d = mTargetDensity;
    return new ConstantState() {
      public Drawable newDrawable() { NinePatchDrawable n = new NinePatchDrawable(null, p, pad); n.mTargetDensity = d; n.computeSize(); return n; }
      public Drawable newDrawable(Resources res) { NinePatchDrawable n = new NinePatchDrawable(res, p, pad); if (res == null) { n.mTargetDensity = d; n.computeSize(); } return n; }
      public int getChangingConfigurations() { return 0; }
    };
  }
}
