package android.view.animation;

import java.util.ArrayList;
import java.util.List;

public class AnimationSet extends Animation {
  static final int PROPERTY_FILL_AFTER_MASK = 1, PROPERTY_FILL_BEFORE_MASK = 2, PROPERTY_REPEAT_MODE_MASK = 4, PROPERTY_START_OFFSET_MASK = 8,
      PROPERTY_SHARE_INTERPOLATOR_MASK = 16, PROPERTY_DURATION_MASK = 32, PROPERTY_MORPH_MATRIX_MASK = 64, PROPERTY_CHANGE_BOUNDS_MASK = 128;
  int mFlags;
  ArrayList<Animation> mAnimations = new ArrayList<>();
  private Transformation mTempTransformation = new Transformation();
  private long mLastEnd;
  private long[] mStoredOffsets;
  boolean mHasAlpha;

  @Override protected AnimationSet clone() throws CloneNotSupportedException {
    AnimationSet a = (AnimationSet) super.clone();
    a.mTempTransformation = new Transformation();
    a.mAnimations = new ArrayList<>();
    for (Animation x : mAnimations) a.mAnimations.add(x.clone());
    return a;
  }
  public AnimationSet(boolean shareInterpolator) {
    if (shareInterpolator) mFlags |= PROPERTY_SHARE_INTERPOLATOR_MASK;
    mStartTime = 0;
  }
  @Override public void setFillAfter(boolean f) { mFlags |= PROPERTY_FILL_AFTER_MASK; super.setFillAfter(f); }
  @Override public void setFillBefore(boolean f) { mFlags |= PROPERTY_FILL_BEFORE_MASK; super.setFillBefore(f); }
  @Override public void setRepeatMode(int m) { mFlags |= PROPERTY_REPEAT_MODE_MASK; super.setRepeatMode(m); }
  @Override public void setStartOffset(long o) { mFlags |= PROPERTY_START_OFFSET_MASK; super.setStartOffset(o); }
  @Override public boolean hasAlpha() { for (Animation a : mAnimations) if (a.hasAlpha()) return true; return false; }
  @Override public void setDuration(long d) { mFlags |= PROPERTY_DURATION_MASK; super.setDuration(d); mLastEnd = mStartOffset + mDuration; }
  public void addAnimation(Animation a) {
    mAnimations.add(a);
    if ((mFlags & PROPERTY_DURATION_MASK) == PROPERTY_DURATION_MASK) mLastEnd = mStartOffset + mDuration;
    else {
      if (mAnimations.size() == 1) { mDuration = a.getStartOffset() + a.getDuration(); mLastEnd = mStartOffset + mDuration; }
      else { mLastEnd = Math.max(mLastEnd, a.getStartOffset() + a.getDuration()); mDuration = mLastEnd - mStartOffset; }
    }
  }
  @Override public void setStartTime(long t) { super.setStartTime(t); for (Animation a : mAnimations) a.setStartTime(t); }
  @Override public long getStartTime() { long s = Long.MAX_VALUE; for (Animation a : mAnimations) s = Math.min(s, a.getStartTime()); return s; }
  @Override public void restrictDuration(long d) { super.restrictDuration(d); for (Animation a : mAnimations) a.restrictDuration(d); }
  @Override public long getDuration() {
    if ((mFlags & PROPERTY_DURATION_MASK) == PROPERTY_DURATION_MASK) return mDuration;
    long d = 0;
    for (Animation a : mAnimations) d = Math.max(d, a.getDuration());
    return d;
  }
  @Override public long computeDurationHint() { long d = 0; for (Animation a : mAnimations) d = Math.max(d, a.computeDurationHint()); return d; }
  @Override public void initializeInvalidateArea(int l, int t, int r, int b) {}
  @Override public boolean getTransformation(long currentTime, Transformation t) {
    boolean more = false, started = false, ended = true;
    t.clear();
    for (int i = mAnimations.size() - 1; i >= 0; --i) {
      Animation a = mAnimations.get(i);
      mTempTransformation.clear();
      more = a.getTransformation(currentTime, mTempTransformation, getScaleFactor()) || more;
      t.compose(mTempTransformation);
      started = started || a.hasStarted();
      ended = a.hasEnded() && ended;
    }
    if (started && !mStarted) {
      if (mListener != null) mListener.onAnimationStart(this);
      mStarted = true;
    }
    if (ended != mEnded) {
      if (mListener != null) mListener.onAnimationEnd(this);
      mEnded = ended;
    }
    return more;
  }
  @Override public void scaleCurrentDuration(float s) { for (Animation a : mAnimations) a.scaleCurrentDuration(s); }
  @Override public void initialize(int width, int height, int parentWidth, int parentHeight) {
    super.initialize(width, height, parentWidth, parentHeight);
    boolean durationSet = (mFlags & PROPERTY_DURATION_MASK) == PROPERTY_DURATION_MASK;
    boolean fillAfterSet = (mFlags & PROPERTY_FILL_AFTER_MASK) == PROPERTY_FILL_AFTER_MASK;
    boolean fillBeforeSet = (mFlags & PROPERTY_FILL_BEFORE_MASK) == PROPERTY_FILL_BEFORE_MASK;
    boolean repeatModeSet = (mFlags & PROPERTY_REPEAT_MODE_MASK) == PROPERTY_REPEAT_MODE_MASK;
    boolean shareInterpolator = (mFlags & PROPERTY_SHARE_INTERPOLATOR_MASK) == PROPERTY_SHARE_INTERPOLATOR_MASK;
    boolean startOffsetSet = (mFlags & PROPERTY_START_OFFSET_MASK) == PROPERTY_START_OFFSET_MASK;
    if (shareInterpolator) ensureInterpolator();
    long duration = mDuration;
    boolean fillAfter = mFillAfter, fillBefore = mFillBefore;
    int repeatMode = mRepeatMode;
    Interpolator interpolator = mInterpolator;
    long startOffset = mStartOffset;
    long[] stored = mStoredOffsets;
    if (startOffsetSet) { if (stored == null || stored.length != mAnimations.size()) stored = mStoredOffsets = new long[mAnimations.size()]; }
    else if (stored != null) stored = mStoredOffsets = null;
    for (int i = 0; i < mAnimations.size(); i++) {
      Animation a = mAnimations.get(i);
      if (durationSet) a.setDuration(duration);
      if (fillAfterSet) a.setFillAfter(fillAfter);
      if (fillBeforeSet) a.setFillBefore(fillBefore);
      if (repeatModeSet) a.setRepeatMode(repeatMode);
      if (shareInterpolator) a.setInterpolator(interpolator);
      if (startOffsetSet) { long offset = a.getStartOffset(); a.setStartOffset(offset + startOffset); stored[i] = offset; }
      a.initialize(width, height, parentWidth, parentHeight);
    }
  }
  @Override public void reset() { super.reset(); restoreChildrenStartOffset(); }
  void restoreChildrenStartOffset() {
    if (mStoredOffsets == null) return;
    for (int i = 0; i < mAnimations.size(); i++) mAnimations.get(i).setStartOffset(mStoredOffsets[i]);
  }
  public List<Animation> getAnimations() { return mAnimations; }
  @Override public boolean willChangeTransformationMatrix() { return true; }
  @Override public boolean willChangeBounds() { return true; }
}
