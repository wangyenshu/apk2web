package android.widget;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import java.util.ArrayList;

public class Toast {
  public static final int LENGTH_SHORT = 0, LENGTH_LONG = 1;
  private static final ArrayList<Toast> queue = new ArrayList<>();
  private static Toast showing;
  private static final Handler handler = new Handler(Looper.getMainLooper());
  final Context mContext;
  View mView;
  int mDuration, mGravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, mX, mY;
  float mHorizontalMargin, mVerticalMargin;

  public Toast(Context c) {
    mContext = c;
    mY = (int) (64 * a2w.Values.METRICS.density);
  }
  public static Toast makeText(Context c, CharSequence text, int duration) {
    Toast t = new Toast(c);
    TextView tv = new TextView(c);
    GradientDrawable bg = new GradientDrawable();
    bg.setColor(0xe0333333);
    bg.setStroke(1, 0xff777777);
    bg.setCornerRadius(6 * a2w.Values.METRICS.density);
    tv.setBackgroundDrawable(bg);
    int p = (int) (10 * a2w.Values.METRICS.density);
    tv.setPadding(p + p / 2, p, p + p / 2, p);
    tv.setTextColor(0xffffffff);
    tv.setTextSize(16);
    tv.setShadowLayer(2.75f, 0, 0, 0xbb000000);
    tv.setText(text);
    t.mView = tv;
    t.mDuration = duration;
    return t;
  }
  public static Toast makeText(Context c, int resId, int duration) { return makeText(c, c.getResources().getText(resId), duration); }
  public void show() {
    if (mView == null) throw new RuntimeException("setView must have been called");
    if (!queue.contains(this) && showing != this) queue.add(this);
    if (showing == null) next();
  }
  private static void next() {
    if (queue.isEmpty()) { showing = null; return; }
    final Toast t = queue.remove(0);
    showing = t;
    WindowManager.LayoutParams lp = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
        WindowManager.LayoutParams.TYPE_TOAST, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, android.graphics.PixelFormat.TRANSLUCENT);
    lp.gravity = t.mGravity; lp.x = t.mX; lp.y = t.mY; lp.horizontalMargin = t.mHorizontalMargin; lp.verticalMargin = t.mVerticalMargin;
    if (t.mView.getParent() != null) a2w.Windows.remove(t.mView);
    a2w.Windows.add(t.mView, lp, null);
    handler.postDelayed(() -> { t.hide(); next(); }, t.mDuration == LENGTH_LONG ? 3500 : 2000);
  }
  void hide() { if (mView != null && mView.getParent() != null) a2w.Windows.remove(mView); }
  public void cancel() { queue.remove(this); if (showing == this) { hide(); } }
  public void setView(View v) { mView = v; }
  public View getView() { return mView; }
  public void setDuration(int d) { mDuration = d; }
  public int getDuration() { return mDuration; }
  public void setMargin(float h, float v) { mHorizontalMargin = h; mVerticalMargin = v; }
  public void setGravity(int g, int x, int y) { mGravity = g; mX = x; mY = y; }
  public int getGravity() { return mGravity; }
  public int getXOffset() { return mX; }
  public int getYOffset() { return mY; }
  public void setText(int id) { setText(mContext.getText(id)); }
  public void setText(CharSequence s) { if (mView instanceof TextView) ((TextView) mView).setText(s); }
}
