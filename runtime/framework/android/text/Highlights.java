package android.text;

import android.graphics.Paint;

/** Text ranges to highlight, each set with its paint (TextView.setHighlights). */
public class Highlights {
  private final Paint[] mPaints;
  private final int[][] mRanges;
  Highlights(Paint[] paints, int[][] ranges) { mPaints = paints; mRanges = ranges; }
  public int getSize() { return mPaints.length; }
  public Paint getPaint(int index) { return mPaints[index]; }
  /** the ranges of the paint at index, as start/end pairs */
  public int[] getRanges(int index) { return mRanges[index]; }

  public static final class Builder {
    private final java.util.ArrayList<Paint> mPaints = new java.util.ArrayList<>();
    private final java.util.ArrayList<int[]> mRanges = new java.util.ArrayList<>();
    public Builder() {}
    public Builder addRange(Paint paint, int start, int end) {
      if (start > end) throw new IllegalArgumentException("start must not be larger than end: " + start + ", " + end);
      return addRanges(paint, start, end);
    }
    public Builder addRanges(Paint paint, int... ranges) {
      java.util.Objects.requireNonNull(paint, "paint");
      if (ranges.length % 2 == 1) throw new IllegalArgumentException("Flatten ranges must have even numbered elements");
      for (int j = 0; j < ranges.length; j += 2)
        if (ranges[j] > ranges[j + 1]) throw new IllegalArgumentException("Reverse range found: " + ranges[j] + ", " + ranges[j + 1]);
      int i = mPaints.indexOf(paint);
      if (i < 0) { mPaints.add(paint); mRanges.add(ranges.clone()); }
      else {
        int[] a = mRanges.get(i), b = new int[a.length + ranges.length];
        System.arraycopy(a, 0, b, 0, a.length); System.arraycopy(ranges, 0, b, a.length, ranges.length);
        mRanges.set(i, b);
      }
      return this;
    }
    public Highlights build() { return new Highlights(mPaints.toArray(new Paint[0]), mRanges.toArray(new int[0][])); }
  }
}
