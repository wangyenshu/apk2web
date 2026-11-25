package android.graphics;

/** android.graphics.PathIterator (API 34): a path's segments, with rectangles, ovals and arcs as lines and cubic curves. */
public class PathIterator implements java.util.Iterator<PathIterator.Segment> {
  public static final int VERB_MOVE = 0, VERB_LINE = 1, VERB_QUAD = 2, VERB_CONIC = 3, VERB_CUBIC = 4, VERB_CLOSE = 5, VERB_DONE = 6;

  public static class Segment {
    private final int mVerb;
    private final float[] mPoints;
    private final float mWeight;
    Segment(int verb, float[] points, float weight) { mVerb = verb; mPoints = points; mWeight = weight; }
    public int getVerb() { return mVerb; }
    public float[] getPoints() { return mPoints; }
    public float getConicWeight() { return mWeight; }
  }

  private final java.util.ArrayList<Segment> segs = new java.util.ArrayList<>();
  private int pos;

  /** cmd: Path.expanded() (moves, lines, quads, cubics, closes) */
  PathIterator(float[] cmd) {
    float x = 0, y = 0, sx = 0, sy = 0;
    boolean open = false;
    int i = 0;
    while (i < cmd.length) {
      int c = (int) cmd[i];
      int a = Path.args(c);
      if (c != Path.MOVE && c != Path.CLOSE && !open) { segs.add(new Segment(VERB_MOVE, new float[] {sx, sy}, 0)); x = sx; y = sy; open = true; }
      switch (c) {
        case Path.MOVE: x = sx = cmd[i + 1]; y = sy = cmd[i + 2]; segs.add(new Segment(VERB_MOVE, new float[] {x, y}, 0)); open = true; break;
        case Path.LINE: segs.add(new Segment(VERB_LINE, new float[] {x, y, cmd[i + 1], cmd[i + 2]}, 0)); x = cmd[i + 1]; y = cmd[i + 2]; break;
        case Path.QUAD: segs.add(new Segment(VERB_QUAD, new float[] {x, y, cmd[i + 1], cmd[i + 2], cmd[i + 3], cmd[i + 4]}, 0)); x = cmd[i + 3]; y = cmd[i + 4]; break;
        case Path.CUBIC: segs.add(new Segment(VERB_CUBIC, new float[] {x, y, cmd[i + 1], cmd[i + 2], cmd[i + 3], cmd[i + 4], cmd[i + 5], cmd[i + 6]}, 0)); x = cmd[i + 5]; y = cmd[i + 6]; break;
        case Path.CLOSE: if (open) { segs.add(new Segment(VERB_CLOSE, new float[0], 0)); open = false; x = sx; y = sy; } break;
        default: break;
      }
      i += 1 + a;
    }
  }
  public boolean hasNext() { return pos < segs.size(); }
  public int peek() { return pos < segs.size() ? segs.get(pos).getVerb() : VERB_DONE; }
  public Segment next() {
    if (pos >= segs.size()) throw new java.util.NoSuchElementException("No more path segments to iterate");
    return segs.get(pos++);
  }
  /** the next verb, its points copied to points[offset...] (at least 8 floats there) */
  public int next(float[] points, int offset) {
    if (points.length < offset + 8) throw new ArrayIndexOutOfBoundsException("The points array must contain at least 8 floats");
    if (pos >= segs.size()) return VERB_DONE;
    Segment s = segs.get(pos++);
    System.arraycopy(s.mPoints, 0, points, offset, s.mPoints.length);
    return s.getVerb();
  }
}
