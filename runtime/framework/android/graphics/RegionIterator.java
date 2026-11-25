package android.graphics;

/** android.graphics.RegionIterator: the region's rectangles, top band first, left to right. */
public class RegionIterator {
  private final int[] rs;
  private final int n;
  private int i;
  public RegionIterator(Region region) { rs = java.util.Arrays.copyOf(region.rs, region.n); n = region.n; }
  public final boolean next(Rect r) {
    if (r == null) throw new NullPointerException("The Rect must be provided");
    if (i >= n) return false;
    r.set(rs[i], rs[i + 1], rs[i + 2], rs[i + 3]);
    i += 4;
    return true;
  }
  protected void finalize() throws Throwable {}
}
