package android.view.animation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.Random;

/** android.view.animation.LayoutAnimationController: one animation for each child of a group, started at staggered offsets. */
public class LayoutAnimationController {
  public static final int ORDER_NORMAL = 0, ORDER_REVERSE = 1, ORDER_RANDOM = 2;
  protected Animation mAnimation;
  protected Random mRandomizer;
  protected Interpolator mInterpolator;
  private float mDelay;
  private int mOrder;
  private long mDuration, mMaxDelay;

  /** <layoutAnimation android:delay android:animationOrder android:animation android:interpolator> */
  public LayoutAnimationController(Context context, AttributeSet attrs) {
    String d = attr(attrs, "delay");
    mDelay = d == null ? 0.5f : d.endsWith("%") ? a2w.Values.parseFloat(d.substring(0, d.length() - 1), 50) / 100f : a2w.Values.parseFloat(d, 0.5f);
    mOrder = order(attr(attrs, "animationOrder"));
    String a = attr(attrs, "animation");
    if (a != null && a2w.Values.isRef(a)) setAnimation(context, a2w.Values.refId(a));
    String i = attr(attrs, "interpolator");
    if (i != null && a2w.Values.isRef(i)) setInterpolator(context, a2w.Values.refId(i));
  }
  static String attr(AttributeSet attrs, String name) {
    if (attrs == null) return null;
    String v = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", name);
    return v != null ? v : attrs.getAttributeValue(null, name);
  }
  static int order(String v) { return "reverse".equals(v) || "1".equals(v) ? ORDER_REVERSE : "random".equals(v) || "2".equals(v) ? ORDER_RANDOM : ORDER_NORMAL; }
  public LayoutAnimationController(Animation animation) { this(animation, 0.5f); }
  public LayoutAnimationController(Animation animation, float delay) { mDelay = delay; setAnimation(animation); }
  public int getOrder() { return mOrder; }
  public void setOrder(int order) { mOrder = order; }
  public void setAnimation(Context context, int resourceID) { setAnimation(AnimationUtils.loadAnimation(context, resourceID)); }
  public void setAnimation(Animation animation) { mAnimation = animation; if (animation != null) mAnimation.setFillBefore(true); }
  public Animation getAnimation() { return mAnimation; }
  public void setInterpolator(Context context, int resourceID) { setInterpolator(AnimationUtils.loadInterpolator(context, resourceID)); }
  public void setInterpolator(Interpolator interpolator) { mInterpolator = interpolator; }
  public Interpolator getInterpolator() { return mInterpolator; }
  public float getDelay() { return mDelay; }
  public void setDelay(float delay) { mDelay = delay; }
  public boolean willOverlap() { return mDelay < 1.0f; }
  public void start() { mDuration = mAnimation.getDuration(); mMaxDelay = Long.MIN_VALUE; mAnimation.setStartTime(-1); }
  /** a copy of the animation, offset by the view's delay */
  public final Animation getAnimationForView(View view) {
    long delay = getDelayForView(view) + mAnimation.getStartOffset();
    mMaxDelay = Math.max(mMaxDelay, delay);
    try {
      Animation a = mAnimation.clone();
      a.setStartOffset(delay);
      return a;
    } catch (CloneNotSupportedException e) {
      return null;
    }
  }
  public boolean isDone() { return AnimationUtils.currentAnimationTimeMillis() > mAnimation.getStartTime() + mMaxDelay + mDuration; }
  protected long getDelayForView(View view) {
    ViewGroup.LayoutParams lp = view.getLayoutParams();
    AnimationParameters params = lp == null ? null : lp.layoutAnimationParameters;
    if (params == null) return 0;
    float delay = mDelay * mAnimation.getDuration();
    long viewDelay = (long) (getTransformedIndex(params) * delay);
    float totalDelay = delay * params.count;
    if (mInterpolator == null) mInterpolator = new LinearInterpolator();
    if (totalDelay == 0) return 0;
    float normalized = mInterpolator.getInterpolation(viewDelay / totalDelay);
    return (long) (normalized * totalDelay);
  }
  protected int getTransformedIndex(AnimationParameters params) {
    switch (getOrder()) {
      case ORDER_REVERSE: return params.count - 1 - params.index;
      case ORDER_RANDOM: if (mRandomizer == null) mRandomizer = new Random(); return (int) (params.count * mRandomizer.nextFloat());
      default: return params.index;
    }
  }
  public static class AnimationParameters {
    public int count, index;
    public AnimationParameters() {}
  }
}
