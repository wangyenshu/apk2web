package android.util;

import java.util.ArrayList;

public class TimingLogger {
  private String mTag, mLabel;
  private boolean mDisabled;
  final ArrayList<Long> mSplits = new ArrayList<>();
  final ArrayList<String> mSplitLabels = new ArrayList<>();
  public TimingLogger(String tag, String label) { reset(tag, label); }
  public void reset(String tag, String label) { mTag = tag; mLabel = label; reset(); }
  public void reset() { mDisabled = !Log.isLoggable(mTag, Log.VERBOSE); if (mDisabled) return; mSplits.clear(); mSplitLabels.clear(); addSplit(null); }
  public void addSplit(String splitLabel) { if (mDisabled) return; mSplits.add(android.os.SystemClock.elapsedRealtime()); mSplitLabels.add(splitLabel); }
  public void dumpToLog() {
    if (mDisabled) return;
    Log.d(mTag, mLabel + ": begin");
    long first = mSplits.get(0), now = first;
    for (int i = 1; i < mSplits.size(); i++) { now = mSplits.get(i); Log.d(mTag, mLabel + ":      " + (now - mSplits.get(i - 1)) + " ms, " + mSplitLabels.get(i)); }
    Log.d(mTag, mLabel + ": end, " + (now - first) + " ms");
  }
}
