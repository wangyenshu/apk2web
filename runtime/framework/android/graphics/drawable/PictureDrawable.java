package android.graphics.drawable;

import android.graphics.*;

/** android.graphics.drawable.PictureDrawable: a Picture drawn at the bounds' corner, clipped to them. */
public class PictureDrawable extends Drawable {
  private Picture mPicture;
  public PictureDrawable(Picture picture) { mPicture = picture; }
  public Picture getPicture() { return mPicture; }
  public void setPicture(Picture picture) { mPicture = picture; }
  @Override public void draw(Canvas canvas) {
    if (mPicture == null) return;
    Rect b = getBounds();
    canvas.save();
    canvas.clipRect(b);
    canvas.translate(b.left, b.top);
    canvas.drawPicture(mPicture);
    canvas.restore();
  }
  @Override public int getIntrinsicWidth() { return mPicture != null ? mPicture.getWidth() : -1; }
  @Override public int getIntrinsicHeight() { return mPicture != null ? mPicture.getHeight() : -1; }
  @Override public int getOpacity() { return PixelFormat.TRANSLUCENT; }
  /** (ignored, as on Android) */
  @Override public void setColorFilter(ColorFilter colorFilter) {}
  @Override public void setAlpha(int alpha) {}
}
