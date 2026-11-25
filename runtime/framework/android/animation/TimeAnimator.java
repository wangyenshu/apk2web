package android.animation;

public class TimeAnimator extends ValueAnimator {
  public interface TimeListener { void onTimeUpdate(TimeAnimator animation, long totalTime, long deltaTime); }
  private TimeListener mListener;
  private long mPreviousTime = -1, mStart = -1;
  public TimeAnimator() { setDuration(Long.MAX_VALUE / 4); setInterpolator(null); setFloatValues(0f, 1f); }
  @Override public void start() { mPreviousTime = -1; mStart = -1; super.start(); }
  public void setTimeListener(TimeListener listener) { mListener = listener; }
  @Override void doAnimationFrame(long frameTime) {
    if (mStart < 0) mStart = frameTime;
    if (mListener != null) {
      long totalTime = frameTime - mStart;
      long deltaTime = mPreviousTime < 0 ? 0 : frameTime - mPreviousTime;
      mPreviousTime = frameTime;
      mListener.onTimeUpdate(this, totalTime, deltaTime);
    }
  }
  @Override void animateValue(float fraction) {}
}
