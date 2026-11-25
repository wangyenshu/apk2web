package android.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.SystemClock;

/** The glow at the edge of a scrolled list or page when it is pulled past its end (fades out after the pull). */
public class EdgeEffect {
  public static final int TYPE_GLOW = 0, TYPE_STRETCH = 1;
  private int mWidth, mHeight, mColor = 0x33666666, mType = TYPE_GLOW;
  private float mPull, mAlpha;
  private long mReleaseAt;
  private boolean mReleasing;
  private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
  public EdgeEffect(Context context) {}
  public EdgeEffect(Context context, android.util.AttributeSet attrs) {}
  public void setSize(int width, int height) { mWidth = width; mHeight = height; }
  public boolean isFinished() { return mAlpha <= 0 && mPull <= 0; }
  public void finish() { mPull = 0; mAlpha = 0; mReleasing = false; }
  public void onPull(float deltaDistance) { onPull(deltaDistance, 0.5f); }
  public void onPull(float deltaDistance, float displacement) { mPull = Math.min(1, Math.max(0, mPull + Math.abs(deltaDistance))); mAlpha = Math.min(0.5f, mPull * 2); mReleasing = false; }
  public float onPullDistance(float deltaDistance, float displacement) { float before = mPull; onPull(Math.max(-mPull, deltaDistance), displacement); return mPull - before; }
  public float getDistance() { return mPull; }
  public void onRelease() { if (!mReleasing && mPull > 0) { mReleasing = true; mReleaseAt = SystemClock.uptimeMillis(); } }
  public void onAbsorb(int velocity) { mPull = Math.min(1, Math.abs(velocity) / 8000f + 0.2f); mAlpha = 0.5f; mReleasing = true; mReleaseAt = SystemClock.uptimeMillis(); }
  public void setColor(int color) { mColor = color; }
  public int getColor() { return mColor; }
  public void setType(int type) { mType = type; }
  public int getType() { return mType; }
  public void setBlendMode(Object mode) {}
  public int getMaxHeight() { return mHeight; }
  /** draws the glow along the top edge of (0, 0, width, height); returns whether it is still animating */
  public boolean draw(Canvas canvas) {
    if (mReleasing) {
      float t = (SystemClock.uptimeMillis() - mReleaseAt) / 600f;
      float k = Math.max(0, 1 - t);
      if (k <= 0) { finish(); return false; }
      mAlpha = Math.min(mAlpha, 0.5f * k);
      mPull = Math.min(mPull, k);
    }
    if (isFinished() || mWidth <= 0) return false;
    float h = Math.min(mHeight, mWidth * 0.3f) * Math.max(0.2f, mPull);
    mPaint.setColor(mColor);
    mPaint.setAlpha(Math.round(255 * Math.min(1, mAlpha * 2) * (mColor >>> 24) / 255f));
    canvas.drawOval(new android.graphics.RectF(-mWidth * 0.25f, -h, mWidth * 1.25f, h), mPaint);
    return mReleasing || mPull > 0;
  }
}
