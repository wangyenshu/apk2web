package android.graphics;

import java.util.ArrayList;
import java.util.List;

/** android.graphics.PathMeasure over the path's contours flattened to line segments (curves within a quarter pixel). */
public class PathMeasure {
  public static final int POSITION_MATRIX_FLAG = 1;
  public static final int TANGENT_MATRIX_FLAG = 2;
  private List<Path.Contour> contours = new ArrayList<>();
  private int index = -1;
  private float[] cum;   // cumulative length at each point of the current contour (closing segment included)
  private float[] pts;

  public PathMeasure() {}
  public PathMeasure(Path path, boolean forceClosed) { setPath(path, forceClosed); }

  public void setPath(Path path, boolean forceClosed) {
    contours = new ArrayList<>();
    if (path != null) for (Path.Contour c : path.contours(0.25f)) {
      if (forceClosed) c.closed = true;
      if (c.length() > 0) contours.add(c);
    }
    index = -1;
    if (!contours.isEmpty()) select(0);
  }
  private void select(int i) {
    index = i;
    Path.Contour c = contours.get(i);
    int k = c.n / 2, m = c.closed ? k + 1 : k;
    pts = new float[2 * m];
    System.arraycopy(c.p, 0, pts, 0, 2 * k);
    if (c.closed) { pts[2 * k] = c.p[0]; pts[2 * k + 1] = c.p[1]; }
    cum = new float[m];
    for (int j = 1; j < m; j++) cum[j] = cum[j - 1] + (float) Math.hypot(pts[2 * j] - pts[2 * j - 2], pts[2 * j + 1] - pts[2 * j - 1]);
  }
  public float getLength() { return index < 0 ? 0 : cum[cum.length - 1]; }
  /** the segment containing distance d (pinned to the contour) and the fraction along it */
  private int segment(float d) {
    int lo = 1, hi = cum.length - 1;
    while (lo < hi) { int mid = (lo + hi) >>> 1; if (cum[mid] < d) lo = mid + 1; else hi = mid; }
    return lo;
  }
  private void point(float d, float[] pos, float[] tan) {
    int s = segment(d);
    float len = cum[s] - cum[s - 1], t = len == 0 ? 0 : (d - cum[s - 1]) / len;
    float x0 = pts[2 * s - 2], y0 = pts[2 * s - 1], x1 = pts[2 * s], y1 = pts[2 * s + 1];
    if (pos != null) { pos[0] = x0 + (x1 - x0) * t; pos[1] = y0 + (y1 - y0) * t; }
    if (tan != null) { float l = (float) Math.hypot(x1 - x0, y1 - y0); tan[0] = l == 0 ? 1 : (x1 - x0) / l; tan[1] = l == 0 ? 0 : (y1 - y0) / l; }
  }
  public boolean getPosTan(float distance, float[] pos, float[] tan) {
    if ((pos != null && pos.length < 2) || (tan != null && tan.length < 2)) throw new ArrayIndexOutOfBoundsException();
    if (index < 0) return false;
    point(Math.max(0, Math.min(distance, getLength())), pos, tan);
    return true;
  }
  public boolean getMatrix(float distance, Matrix matrix, int flags) {
    float[] pos = new float[2], tan = new float[2];
    if (!getPosTan(distance, pos, tan)) return false;
    matrix.reset();
    if ((flags & TANGENT_MATRIX_FLAG) != 0) matrix.setSinCos(tan[1], tan[0]);
    if ((flags & POSITION_MATRIX_FLAG) != 0) matrix.postTranslate(pos[0], pos[1]);
    return true;
  }
  public boolean getSegment(float startD, float stopD, Path dst, boolean startWithMoveTo) {
    if (index < 0) return false;
    float len = getLength();
    if (startD < 0) startD = 0;
    if (stopD > len) stopD = len;
    if (startD > stopD) return false;
    float[] p = new float[2];
    point(startD, p, null);
    if (startWithMoveTo) dst.moveTo(p[0], p[1]); else dst.lineTo(p[0], p[1]);
    int s0 = segment(startD), s1 = segment(stopD);
    for (int j = s0; j < s1; j++) dst.lineTo(pts[2 * j], pts[2 * j + 1]);
    point(stopD, p, null);
    dst.lineTo(p[0], p[1]);
    return true;
  }
  public boolean isClosed() { return index >= 0 && contours.get(index).closed; }
  public boolean nextContour() {
    if (index < 0 || index + 1 >= contours.size()) { index = -1; return false; }
    select(index + 1);
    return true;
  }
  protected void finalize() throws Throwable {}
}
