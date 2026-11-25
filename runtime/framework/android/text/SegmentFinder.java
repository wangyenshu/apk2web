package android.text;

/** Finds the segments (graphemes, words...) of a text: the start and end boundaries around an offset. */
public abstract class SegmentFinder {
  public static final int DONE = -1;
  public SegmentFinder() {}
  public abstract int previousStartBoundary(int offset);
  public abstract int previousEndBoundary(int offset);
  public abstract int nextStartBoundary(int offset);
  public abstract int nextEndBoundary(int offset);

  /** segments given as sorted, non-overlapping [start, end) pairs */
  public static class PrescribedSegmentFinder extends SegmentFinder {
    private final int[] mStarts, mEnds;
    public PrescribedSegmentFinder(int[] segments) {
      if (segments.length % 2 != 0) throw new IllegalArgumentException("the length of segments must be even");
      int n = segments.length / 2;
      mStarts = new int[n]; mEnds = new int[n];
      for (int i = 0; i < n; i++) {
        mStarts[i] = segments[2 * i]; mEnds[i] = segments[2 * i + 1];
        if (mStarts[i] > mEnds[i] || (i > 0 && mStarts[i] < mEnds[i - 1])) throw new IllegalArgumentException("segments must be sorted and must not overlap");
      }
    }
    PrescribedSegmentFinder(int[] starts, int[] ends) { mStarts = starts; mEnds = ends; }
    static int next(int[] a, int offset) { for (int v : a) if (v > offset) return v; return DONE; }
    static int previous(int[] a, int offset) { for (int i = a.length - 1; i >= 0; i--) if (a[i] < offset) return a[i]; return DONE; }
    public int previousStartBoundary(int offset) { return previous(mStarts, offset); }
    public int previousEndBoundary(int offset) { return previous(mEnds, offset); }
    public int nextStartBoundary(int offset) { return next(mStarts, offset); }
    public int nextEndBoundary(int offset) { return next(mEnds, offset); }
  }
}
