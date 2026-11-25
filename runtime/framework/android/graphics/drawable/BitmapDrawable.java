package android.graphics.drawable;

import android.content.res.Resources;
import android.graphics.*;
import android.view.Gravity;

public class BitmapDrawable extends Drawable {
  Bitmap bitmap;
  final Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG | Paint.DITHER_FLAG);
  int gravity = Gravity.FILL;
  int targetDensity = 160;
  Shader.TileMode tileX, tileY;
  private final Rect dst = new Rect();
  private BitmapShader tileShader;
  private boolean applyGravity;
  int bw = -1, bh = -1;

  @Deprecated public BitmapDrawable() {}
  @Deprecated public BitmapDrawable(Resources r) { if (r != null) targetDensity = r.getDisplayMetrics().densityDpi; }
  public final boolean hasAntiAlias() { return paint.isAntiAlias(); }
  public boolean hasMipMap() { return bitmap != null && bitmap.hasMipMap(); }
  public void setMipMap(boolean mipMap) { if (bitmap != null) { bitmap.setHasMipMap(mipMap); invalidateSelf(); } }
  @Deprecated public BitmapDrawable(Bitmap b) { setBitmap(b); }
  public BitmapDrawable(Resources r, Bitmap b) { if (r != null) targetDensity = r.getDisplayMetrics().densityDpi; setBitmap(b); }
  @Deprecated public BitmapDrawable(String path) { this(BitmapFactory.decodeFile(path)); }
  public BitmapDrawable(Resources r, String path) { this(r, BitmapFactory.decodeFile(path)); }
  @Deprecated public BitmapDrawable(java.io.InputStream is) { this(BitmapFactory.decodeStream(is)); }
  public BitmapDrawable(Resources r, java.io.InputStream is) { this(r, BitmapFactory.decodeStream(is)); }

  void setBitmap(Bitmap b) {
    bitmap = b;
    if (b != null) { bw = b.getScaledWidth(targetDensity); bh = b.getScaledHeight(targetDensity); }
    else bw = bh = -1;
    applyGravity = true;
  }
  public final Paint getPaint() { return paint; }
  public final Bitmap getBitmap() { return bitmap; }
  public void setTargetDensity(Canvas c) { setTargetDensity(c.getDensity()); }
  public void setTargetDensity(android.util.DisplayMetrics m) { setTargetDensity(m.densityDpi); }
  public void setTargetDensity(int d) { targetDensity = d == 0 ? 160 : d; setBitmap(bitmap); }
  public int getGravity() { return gravity; }
  public void setGravity(int g) { gravity = g; applyGravity = true; }
  public void setAntiAlias(boolean a) { paint.setAntiAlias(a); }
  public void setFilterBitmap(boolean f) { paint.setFilterBitmap(f); }
  public void setDither(boolean d) { paint.setDither(d); }
  public Shader.TileMode getTileModeX() { return tileX; }
  public Shader.TileMode getTileModeY() { return tileY; }
  public void setTileModeX(Shader.TileMode m) { tileX = m; }
  public void setTileModeY(Shader.TileMode m) { tileY = m; }
  public void setTileModeXY(Shader.TileMode x, Shader.TileMode y) { tileX = x; tileY = y; }
  @Override protected void onBoundsChange(Rect b) { applyGravity = true; }
  @Override public void draw(Canvas c) {
    if (bitmap == null) return;
    if (tileX != null || tileY != null) {
      // like BitmapDrawable: a BitmapShader from the bounds' corner, at the target density
      Rect b = getBounds();
      if (tileShader == null || tileShader.bitmap != bitmap || tileShader.tx != (tileX == null ? Shader.TileMode.CLAMP : tileX) || tileShader.ty != (tileY == null ? Shader.TileMode.CLAMP : tileY))
        tileShader = new BitmapShader(bitmap, tileX == null ? Shader.TileMode.CLAMP : tileX, tileY == null ? Shader.TileMode.CLAMP : tileY);
      Matrix m = new Matrix();
      m.setScale(bw / (float) bitmap.getWidth(), bh / (float) bitmap.getHeight());
      m.postTranslate(b.left, b.top);
      tileShader.setLocalMatrix(m);
      Shader old = paint.getShader();
      paint.setShader(tileShader);
      c.drawRect(b, paint);
      paint.setShader(old);
      return;
    }
    if (applyGravity) {
      Gravity.apply(gravity, bw, bh, getBounds(), dst);
      applyGravity = false;
    }
    c.drawBitmap(bitmap, null, dst, paint);
  }
  @Override public void setAlpha(int a) { if (paint.getAlpha() != a) { paint.setAlpha(a); invalidateSelf(); } }
  @Override public int getAlpha() { return paint.getAlpha(); }
  @Override public void setColorFilter(ColorFilter cf) { paint.setColorFilter(cf); invalidateSelf(); }
  @Override public int getIntrinsicWidth() { return bw; }
  @Override public int getIntrinsicHeight() { return bh; }
  @Override public int getOpacity() { return PixelFormat.TRANSLUCENT; }
  @Override public Drawable mutate() { return this; }
  @Override public ConstantState getConstantState() {
    final Bitmap b = bitmap; final int d = targetDensity;
    return new ConstantState() {
      public Drawable newDrawable() { BitmapDrawable n = new BitmapDrawable(); n.targetDensity = d; n.setBitmap(b); return n; }
      public int getChangingConfigurations() { return 0; }
    };
  }
}
