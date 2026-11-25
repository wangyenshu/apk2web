package android.graphics.drawable;

import android.graphics.*;

/** An animated vector drawable: its vector drawn in its first state (the animations are not run). */
public class AnimatedVectorDrawable extends Drawable implements Animatable2 {
  Drawable mVector;
  private boolean mRunning;
  private final java.util.ArrayList<Animatable2.AnimationCallback> mCallbacks = new java.util.ArrayList<>();
  public AnimatedVectorDrawable() { this(null); }
  public AnimatedVectorDrawable(Drawable vector) { mVector = vector; }
  public Drawable getVector() { return mVector; }
  @Override public void draw(Canvas c) { if (mVector != null) { mVector.setBounds(getBounds()); mVector.draw(c); } }
  @Override public void setAlpha(int a) { if (mVector != null) mVector.setAlpha(a); }
  @Override public int getAlpha() { return mVector != null ? mVector.getAlpha() : 255; }
  @Override public void setColorFilter(ColorFilter cf) { if (mVector != null) mVector.setColorFilter(cf); }
  @Override public int getOpacity() { return PixelFormat.TRANSLUCENT; }
  @Override public int getIntrinsicWidth() { return mVector != null ? mVector.getIntrinsicWidth() : -1; }
  @Override public int getIntrinsicHeight() { return mVector != null ? mVector.getIntrinsicHeight() : -1; }
  @Override public void setTintList(android.content.res.ColorStateList t) { if (mVector != null) mVector.setTintList(t); }
  @Override public void setTintMode(PorterDuff.Mode m) { if (mVector != null) mVector.setTintMode(m); }
  @Override public boolean isStateful() { return mVector != null && mVector.isStateful(); }
  @Override protected boolean onStateChange(int[] s) { return mVector != null && mVector.setState(s); }
  @Override public void start() {
    if (mRunning) return;
    mRunning = true;
    for (Animatable2.AnimationCallback cb : new java.util.ArrayList<>(mCallbacks)) cb.onAnimationStart(this);
    // no animation to run: it ends at once, on the next message
    android.os.Handler h = new android.os.Handler(android.os.Looper.getMainLooper());
    h.post(() -> { if (mRunning) { mRunning = false; for (Animatable2.AnimationCallback cb : new java.util.ArrayList<>(mCallbacks)) cb.onAnimationEnd(this); } });
  }
  @Override public void stop() { mRunning = false; }
  @Override public boolean isRunning() { return mRunning; }
  public void reset() { mRunning = false; }
  public boolean canReverse() { return false; }
  public void reverse() {}
  @Override public void registerAnimationCallback(Animatable2.AnimationCallback cb) { if (cb != null && !mCallbacks.contains(cb)) mCallbacks.add(cb); }
  @Override public boolean unregisterAnimationCallback(Animatable2.AnimationCallback cb) { return mCallbacks.remove(cb); }
  @Override public void clearAnimationCallbacks() { mCallbacks.clear(); }
  @Override public ConstantState getConstantState() {
    final Drawable v = mVector;
    return new ConstantState() {
      @Override public Drawable newDrawable() {
        ConstantState cs = v == null ? null : v.getConstantState();
        return new AnimatedVectorDrawable(cs != null ? cs.newDrawable() : v);
      }
      @Override public int getChangingConfigurations() { return 0; }
    };
  }
}
