package android.animation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** Plays animators together or one after another (a dependency graph of start/end). */
public final class AnimatorSet extends Animator {
  private final ArrayList<Animator> mNodes = new ArrayList<>();
  private final HashMap<Animator, ArrayList<Animator>> mAfter = new HashMap<>();   // anim -> those that must end before it starts
  private final HashMap<Animator, Long> mDelays = new HashMap<>();
  private long mDuration = -1, mStartDelay;
  private TimeInterpolator mInterpolator;
  private boolean mStarted, mRunning;
  private int mRemaining;
  private final ArrayList<Animator> mPlaying = new ArrayList<>();
  public AnimatorSet() {}
  private void node(Animator a) { if (!mNodes.contains(a)) { mNodes.add(a); mAfter.put(a, new ArrayList<>()); } }
  public void playTogether(Animator... items) { if (items != null) for (Animator a : items) node(a); }
  public void playTogether(java.util.Collection<Animator> items) { if (items != null) for (Animator a : items) node(a); }
  public void playSequentially(Animator... items) {
    if (items == null) return;
    for (int i = 0; i < items.length; i++) { node(items[i]); if (i > 0) mAfter.get(items[i]).add(items[i - 1]); }
  }
  public void playSequentially(List<Animator> items) { playSequentially(items.toArray(new Animator[0])); }
  public ArrayList<Animator> getChildAnimations() { return new ArrayList<>(mNodes); }
  @Override public void setTarget(Object target) { for (Animator a : mNodes) a.setTarget(target); }
  @Override public void setInterpolator(TimeInterpolator interpolator) { mInterpolator = interpolator; }
  @Override public TimeInterpolator getInterpolator() { return mInterpolator; }
  public Builder play(Animator anim) { if (anim == null) return null; node(anim); return new Builder(anim); }
  @Override public void cancel() {
    if (!mStarted) return;
    notifyCancel();
    for (Animator a : new ArrayList<>(mPlaying)) a.cancel();
    finish();
  }
  @Override public void end() {
    if (!mStarted) { mStarted = true; notifyStart(); }
    for (Animator a : mNodes) a.end();
    finish();
  }
  @Override public boolean isRunning() { return mRunning; }
  @Override public boolean isStarted() { return mStarted; }
  @Override public long getStartDelay() { return mStartDelay; }
  @Override public void setStartDelay(long startDelay) { mStartDelay = startDelay; }
  @Override public long getDuration() { return mDuration; }
  @Override public AnimatorSet setDuration(long duration) {
    if (duration < 0) throw new IllegalArgumentException("duration must be a value of zero or greater");
    mDuration = duration;
    return this;
  }
  @Override public void setupStartValues() { for (Animator a : mNodes) a.setupStartValues(); }
  @Override public void setupEndValues() { for (Animator a : mNodes) a.setupEndValues(); }
  @Override public void start() {
    if (mStarted) cancel();
    mStarted = true;
    mRunning = true;
    for (Animator a : mNodes) {
      if (mDuration >= 0) a.setDuration(mDuration);
      if (mInterpolator != null) a.setInterpolator(mInterpolator);
    }
    notifyStart();
    mRemaining = mNodes.size();
    if (mRemaining == 0) { finish(); return; }
    final java.util.HashSet<Animator> ended = new java.util.HashSet<>();
    for (Animator a : mNodes) {
      a.addListener(new AnimatorListenerAdapter() {
        boolean canceled;
        @Override public void onAnimationCancel(Animator animation) { canceled = true; }
        @Override public void onAnimationEnd(Animator animation) {
          animation.removeListener(this);
          mPlaying.remove(animation);
          if (!mStarted) return;
          ended.add(animation);
          for (Animator next : mNodes) if (!ended.contains(next) && !mPlaying.contains(next) && mAfter.get(next).contains(animation) && ended.containsAll(mAfter.get(next))) launch(next);
          if (--mRemaining == 0 && !canceled) finish();
        }
      });
    }
    android.os.Handler h = new android.os.Handler(android.os.Looper.getMainLooper());
    Runnable go = () -> { for (Animator a : new ArrayList<>(mNodes)) if (mAfter.get(a).isEmpty()) launch(a); };
    if (mStartDelay > 0) h.postDelayed(go, mStartDelay); else go.run();
  }
  private void launch(Animator a) {
    mPlaying.add(a);
    Long d = mDelays.get(a);
    if (d != null && d > 0) a.setStartDelay(d);
    a.start();
  }
  private void finish() {
    boolean was = mStarted;
    mStarted = false;
    mRunning = false;
    mPlaying.clear();
    if (was) notifyEnd();
  }
  @Override public AnimatorSet clone() {
    AnimatorSet s = (AnimatorSet) super.clone();
    return s;
  }
  @Override public boolean canReverse() { return false; }
  public class Builder {
    private final Animator mCurrent;
    Builder(Animator anim) { mCurrent = anim; }
    public Builder with(Animator anim) { node(anim); mAfter.get(anim).addAll(mAfter.get(mCurrent)); return this; }
    public Builder before(Animator anim) { node(anim); mAfter.get(anim).add(mCurrent); return this; }
    public Builder after(Animator anim) { node(anim); mAfter.get(mCurrent).add(anim); return this; }
    public Builder after(long delay) { ValueAnimator anim = ValueAnimator.ofFloat(0f, 1f); anim.setDuration(delay); after(anim); return this; }
  }
}
