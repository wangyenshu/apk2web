package android.graphics;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/**
 * android.graphics.Region: a set of pixels kept like SkRegion, as y-sorted bands of x-sorted disjoint rectangles,
 * with vertically adjacent bands of equal spans merged. Boolean ops work on a grid of the operands' edges.
 */
public class Region implements Parcelable {
  public enum Op { DIFFERENCE, INTERSECT, UNION, XOR, REVERSE_DIFFERENCE, REPLACE }

  /** rectangles as l, t, r, b quadruples; n ints are used */
  int[] rs = new int[0];
  int n;
  final Rect bounds = new Rect();

  public Region() {}
  public Region(Region region) { set(region); }
  public Region(Rect r) { set(r); }
  public Region(int left, int top, int right, int bottom) { set(left, top, right, bottom); }

  void setRects(int[] a, int len) {
    rs = a; n = len;
    if (n == 0) { bounds.setEmpty(); return; }
    int l = Integer.MAX_VALUE, r = Integer.MIN_VALUE;
    for (int i = 0; i < n; i += 4) { l = Math.min(l, a[i]); r = Math.max(r, a[i + 2]); }
    bounds.set(l, a[1], r, a[n - 1]);
  }
  public void setEmpty() { setRects(new int[0], 0); }
  public boolean set(Region region) { setRects(Arrays.copyOf(region.rs, region.n), region.n); return n > 0; }
  public boolean set(Rect r) { return set(r.left, r.top, r.right, r.bottom); }
  public boolean set(int left, int top, int right, int bottom) {
    if (left < right && top < bottom) setRects(new int[] {left, top, right, bottom}, 4); else setEmpty();
    return n > 0;
  }

  /** the pixels of the path (sampled at pixel centers, with its fill type) inside clip */
  public boolean setPath(Path path, Region clip) {
    if (clip.n == 0) { setEmpty(); return false; }
    boolean inverse = path.isInverseFillType(), evenOdd = path.evenOdd();
    if (path.isEmpty()) { if (inverse) return set(clip); setEmpty(); return false; }
    Rect cb = clip.bounds;
    RectF pb = new RectF();
    path.computeBounds(pb, false);
    int top = cb.top, bottom = cb.bottom;
    if (!inverse) { top = Math.max(top, (int) Math.floor(pb.top)); bottom = Math.min(bottom, (int) Math.ceil(pb.bottom)); }
    java.util.List<Path.Contour> cs = path.contours(0.25f);
    Builder out = new Builder();
    float[] xs = new float[16];
    int[] ds = new int[16];
    for (int y = top; y < bottom; y++) {
      float sy = y + 0.5f;
      int k = 0;
      for (Path.Contour c : cs) {
        int m = c.n / 2;
        if (m < 2) continue;
        for (int j = 0; j < m; j++) {
          int q = (j + 1) % m;
          float x0 = c.p[2 * j], y0 = c.p[2 * j + 1], x1 = c.p[2 * q], y1 = c.p[2 * q + 1];
          if (y0 == y1 || !(sy >= Math.min(y0, y1) && sy < Math.max(y0, y1))) continue;
          if (k == xs.length) { xs = Arrays.copyOf(xs, k * 2); ds = Arrays.copyOf(ds, k * 2); }
          xs[k] = x0 + (sy - y0) * (x1 - x0) / (y1 - y0);
          ds[k++] = y1 > y0 ? 1 : -1;
        }
      }
      for (int i = 1; i < k; i++) {   // insertion sort by x
        float x = xs[i]; int d = ds[i], j = i - 1;
        while (j >= 0 && xs[j] > x) { xs[j + 1] = xs[j]; ds[j + 1] = ds[j]; j--; }
        xs[j + 1] = x; ds[j + 1] = d;
      }
      out.row(y, y + 1);
      int w = 0;
      boolean in = inverse;
      float from = Float.NEGATIVE_INFINITY;
      for (int i = 0; i <= k; i++) {
        float x = i < k ? xs[i] : Float.POSITIVE_INFINITY;
        if (in) span(out, from, x, cb.left, cb.right);
        if (i == k) break;
        w += ds[i];
        boolean inside = evenOdd ? (w & 1) != 0 : w != 0;
        in = inside != inverse;
        from = x;
      }
      out.endRow();
    }
    int[] r = out.done();
    if (!clip.isRect()) r = combine(r, r.length, clip.rs, clip.n, Op.INTERSECT);
    setRects(r, r.length);
    return n > 0;
  }
  /** pixels whose centers lie in [a, b), within [lo, hi) */
  private static void span(Builder out, float a, float b, int lo, int hi) {
    int l = a == Float.NEGATIVE_INFINITY ? lo : Math.max(lo, (int) Math.ceil(a - 0.5f));
    int r = b == Float.POSITIVE_INFINITY ? hi : Math.min(hi, (int) Math.ceil(b - 0.5f));
    if (l < r) out.span(l, r);
  }

  public boolean isEmpty() { return n == 0; }
  public boolean isRect() { return n == 4; }
  public boolean isComplex() { return n > 4; }
  public Rect getBounds() { return new Rect(bounds); }
  public boolean getBounds(Rect r) {
    if (r == null) throw new NullPointerException();
    r.set(bounds);
    return n > 0;
  }
  public boolean contains(int x, int y) {
    if (!bounds.contains(x, y)) return false;
    for (int i = 0; i < n; i += 4) {
      if (rs[i + 1] > y) break;
      if (y < rs[i + 3] && x >= rs[i] && x < rs[i + 2]) return true;
    }
    return false;
  }
  /** true only when the region is one rectangle containing the given one */
  public boolean quickContains(Rect r) { return quickContains(r.left, r.top, r.right, r.bottom); }
  public boolean quickContains(int left, int top, int right, int bottom) {
    return n == 4 && left < right && top < bottom && rs[0] <= left && rs[1] <= top && rs[2] >= right && rs[3] >= bottom;
  }
  /** true when the region is empty or does not meet the rectangle */
  public boolean quickReject(Rect r) { return quickReject(r.left, r.top, r.right, r.bottom); }
  public boolean quickReject(int left, int top, int right, int bottom) {
    if (n == 0 || left >= right || top >= bottom) return true;
    for (int i = 0; i < n; i += 4) if (rs[i] < right && left < rs[i + 2] && rs[i + 1] < bottom && top < rs[i + 3]) return false;
    return true;
  }
  public boolean quickReject(Region rgn) {
    if (n == 0 || rgn.n == 0 || !Rect.intersects(bounds, rgn.bounds)) return true;
    for (int j = 0; j < rgn.n; j += 4) if (!quickReject(rgn.rs[j], rgn.rs[j + 1], rgn.rs[j + 2], rgn.rs[j + 3])) return false;
    return true;
  }
  public void translate(int dx, int dy) { translate(dx, dy, this); }
  public void translate(int dx, int dy, Region dst) {
    if (dst == null) dst = this;
    int[] a = Arrays.copyOf(rs, n);
    for (int i = 0; i < n; i += 2) { a[i] += dx; a[i + 1] += dy; }
    dst.setRects(a, n);
  }
  /** (hidden API) */
  public void scale(float scale) { scale(scale, this); }
  public void scale(float scale, Region dst) {
    if (dst == null) dst = this;
    Builder b = new Builder();
    for (int i = 0; i < n; ) {
      int t = rs[i + 1], bt = rs[i + 3];
      b.row((int) (t * scale + 0.5f), (int) (bt * scale + 0.5f));
      for (; i < n && rs[i + 1] == t; i += 4) b.span((int) (rs[i] * scale + 0.5f), (int) (rs[i + 2] * scale + 0.5f));
      if (b.ct < b.cb) b.endRow(); else b.cn = 0;
    }
    int[] r = b.done();
    dst.setRects(r, r.length);
  }

  public final boolean union(Rect r) { return op(r, Op.UNION); }
  public boolean op(Rect r, Op op) { return op(r.left, r.top, r.right, r.bottom, op); }
  public boolean op(int left, int top, int right, int bottom, Op op) { return op(this, new Region(left, top, right, bottom), op); }
  public boolean op(Region region, Op op) { return op(this, region, op); }
  public boolean op(Rect rect, Region region, Op op) { return op(new Region(rect), region, op); }
  public boolean op(Region region1, Region region2, Op op) {
    if (op == Op.REPLACE) return set(region2);
    int[] r = combine(region1.rs, region1.n, region2.rs, region2.n, op);
    setRects(r, r.length);
    return n > 0;
  }

  private static int[] coords(int[] a, int na, int[] b, int nb, int off) {
    int[] c = new int[(na + nb) / 2];
    int k = 0;
    for (int i = 0; i < na; i += 4) { c[k++] = a[i + off]; c[k++] = a[i + off + 2]; }
    for (int i = 0; i < nb; i += 4) { c[k++] = b[i + off]; c[k++] = b[i + off + 2]; }
    Arrays.sort(c, 0, k);
    int u = 0;
    for (int i = 0; i < k; i++) if (u == 0 || c[u - 1] != c[i]) c[u++] = c[i];
    return Arrays.copyOf(c, u);
  }
  private static void mark(byte[] g, int bit, int[] a, int na, int[] xs, int[] ys) {
    int w = xs.length - 1;
    for (int i = 0; i < na; i += 4) {
      int x0 = Arrays.binarySearch(xs, a[i]), x1 = Arrays.binarySearch(xs, a[i + 2]);
      int y0 = Arrays.binarySearch(ys, a[i + 1]), y1 = Arrays.binarySearch(ys, a[i + 3]);
      for (int y = y0; y < y1; y++) for (int x = x0; x < x1; x++) g[y * w + x] |= bit;
    }
  }
  private static boolean keep(int c, Op op) {
    boolean a = (c & 1) != 0, b = (c & 2) != 0;
    switch (op) {
      case DIFFERENCE: return a && !b;
      case INTERSECT: return a && b;
      case UNION: return a || b;
      case XOR: return a != b;
      case REVERSE_DIFFERENCE: return b && !a;
      default: return b;
    }
  }
  /** a op b over the grid of both operands' edges */
  static int[] combine(int[] a, int na, int[] b, int nb, Op op) {
    if (na + nb == 0) return new int[0];
    int[] xs = coords(a, na, b, nb, 0), ys = coords(a, na, b, nb, 1);
    int w = xs.length - 1, h = ys.length - 1;
    if (w <= 0 || h <= 0) return new int[0];
    byte[] g = new byte[w * h];
    mark(g, 1, a, na, xs, ys);
    mark(g, 2, b, nb, xs, ys);
    Builder out = new Builder();
    for (int y = 0; y < h; y++) {
      out.row(ys[y], ys[y + 1]);
      int start = -1;
      for (int x = 0; x <= w; x++) {
        boolean in = x < w && keep(g[y * w + x], op);
        if (in && start < 0) start = x;
        else if (!in && start >= 0) { out.span(xs[start], xs[x]); start = -1; }
      }
      out.endRow();
    }
    return out.done();
  }

  /** collects rows of x-sorted spans into banded rectangles, merging equal adjacent rows */
  static final class Builder {
    int[] out = new int[16]; int n;
    int[] prev = new int[8]; int pn = -1, pt, pb;
    int[] cur = new int[8]; int cn, ct, cb;
    void row(int t, int b) { ct = t; cb = b; cn = 0; }
    void span(int l, int r) {
      if (l >= r) return;
      if (cn > 0 && cur[cn - 1] >= l) { cur[cn - 1] = Math.max(cur[cn - 1], r); return; }
      if (cn + 2 > cur.length) cur = Arrays.copyOf(cur, cur.length * 2);
      cur[cn++] = l; cur[cn++] = r;
    }
    void endRow() {
      if (cn == 0) { flush(); return; }
      if (pn == cn && pb == ct) {
        boolean same = true;
        for (int i = 0; i < cn && same; i++) same = prev[i] == cur[i];
        if (same) { pb = cb; cn = 0; return; }
      }
      flush();
      int[] t = prev; prev = cur; cur = t;
      pn = cn; pt = ct; pb = cb; cn = 0;
    }
    void flush() {
      if (pn < 0) return;
      for (int i = 0; i < pn; i += 2) {
        if (n + 4 > out.length) out = Arrays.copyOf(out, out.length * 2);
        out[n++] = prev[i]; out[n++] = pt; out[n++] = prev[i + 1]; out[n++] = pb;
      }
      pn = -1;
    }
    int[] done() { flush(); return Arrays.copyOf(out, n); }
  }

  /** the region's outline: closed clockwise contours (holes counter-clockwise), one per boundary loop */
  public Path getBoundaryPath() { Path p = new Path(); getBoundaryPath(p); return p; }
  public boolean getBoundaryPath(Path path) {
    path.reset();
    if (n == 0) return false;
    int[] e = new int[32];
    int ne = 0;
    // vertical edges: right sides go down, left sides go up
    for (int i = 0; i < n; i += 4) {
      if (ne + 8 > e.length) e = Arrays.copyOf(e, e.length * 2);
      e[ne++] = rs[i + 2]; e[ne++] = rs[i + 1]; e[ne++] = rs[i + 2]; e[ne++] = rs[i + 3];
      e[ne++] = rs[i]; e[ne++] = rs[i + 3]; e[ne++] = rs[i]; e[ne++] = rs[i + 1];
    }
    // horizontal edges where exactly one side is inside: tops go right, bottoms go left
    int[] ys = coords(rs, n, rs, 0, 1);
    for (int y : ys) {
      int[] xs = new int[0];
      int k = 0;
      for (int i = 0; i < n; i += 4) if (rs[i + 1] == y || rs[i + 3] == y) {
        if (k + 2 > xs.length) xs = Arrays.copyOf(xs, Math.max(8, xs.length * 2));
        xs[k++] = rs[i]; xs[k++] = rs[i + 2];
      }
      Arrays.sort(xs, 0, k);
      int segStart = 0, segKind = 0;
      for (int j = 0; j < k; j++) {
        int x0 = xs[j], x1 = j + 1 < k ? xs[j + 1] : x0;
        int kind = 0;
        if (x1 > x0) {
          int mx2 = x0 + x1;   // test the midpoint, doubled to stay integral
          boolean above = false, below = false;
          for (int i = 0; i < n; i += 4) if (2 * rs[i] <= mx2 && mx2 < 2 * rs[i + 2]) {
            if (rs[i + 3] == y) above = true;
            if (rs[i + 1] == y) below = true;
          }
          kind = below && !above ? 1 : above && !below ? -1 : 0;
        }
        if (kind != segKind || x1 == x0) {
          if (segKind != 0 && x0 > segStart) {
            if (ne + 4 > e.length) e = Arrays.copyOf(e, e.length * 2);
            if (segKind > 0) { e[ne++] = segStart; e[ne++] = y; e[ne++] = x0; e[ne++] = y; }
            else { e[ne++] = x0; e[ne++] = y; e[ne++] = segStart; e[ne++] = y; }
          }
          segKind = x1 == x0 ? 0 : kind;
          segStart = x0;
        }
      }
    }
    int m = ne / 4;
    boolean[] used = new boolean[m];
    java.util.HashMap<Long, java.util.ArrayList<Integer>> from = new java.util.HashMap<>();
    for (int i = 0; i < m; i++) from.computeIfAbsent(key(e[4 * i], e[4 * i + 1]), q -> new java.util.ArrayList<>()).add(i);
    int[] pts = new int[16];
    for (int s = 0; s < m; s++) {
      if (used[s]) continue;
      int np = 0, cur = s;
      int sx = e[4 * s], sy = e[4 * s + 1];
      while (cur >= 0) {
        used[cur] = true;
        if (np + 2 > pts.length) pts = Arrays.copyOf(pts, pts.length * 2);
        pts[np++] = e[4 * cur]; pts[np++] = e[4 * cur + 1];
        int x = e[4 * cur + 2], y = e[4 * cur + 3];
        if (x == sx && y == sy) break;
        int next = -1;
        java.util.ArrayList<Integer> c = from.get(key(x, y));
        if (c != null) for (int q : c) if (!used[q]) { next = q; break; }
        cur = next;
      }
      // drop collinear points
      int k = np / 2, emitted = 0;
      for (int i = 0; i < k; i++) {
        int px = pts[2 * ((i + k - 1) % k)], py = pts[2 * ((i + k - 1) % k) + 1];
        int x = pts[2 * i], y = pts[2 * i + 1];
        int qx = pts[2 * ((i + 1) % k)], qy = pts[2 * ((i + 1) % k) + 1];
        if ((long) (x - px) * (qy - y) - (long) (y - py) * (qx - x) == 0 && k > 2) continue;
        if (emitted++ == 0) path.moveTo(x, y); else path.lineTo(x, y);
      }
      if (emitted > 0) path.close();
    }
    return true;
  }
  private static long key(int x, int y) { return ((long) x << 32) ^ (y & 0xffffffffL); }

  public int describeContents() { return 0; }
  public void writeToParcel(Parcel p, int flags) {
    p.writeInt(n);
    for (int i = 0; i < n; i++) p.writeInt(rs[i]);
  }
  public static final Parcelable.Creator<Region> CREATOR = new Parcelable.Creator<Region>() {
    public Region createFromParcel(Parcel p) {
      int k = p.readInt();
      int[] a = new int[k];
      for (int i = 0; i < k; i++) a[i] = p.readInt();
      Region r = new Region();
      r.setRects(a, k);
      return r;
    }
    public Region[] newArray(int size) { return new Region[size]; }
  };
  @Override public boolean equals(Object obj) {
    if (!(obj instanceof Region)) return false;
    Region o = (Region) obj;
    if (o.n != n) return false;
    for (int i = 0; i < n; i++) if (rs[i] != o.rs[i]) return false;
    return true;
  }
  @Override public int hashCode() { int h = n; for (int i = 0; i < n; i++) h = 31 * h + rs[i]; return h; }
  @Override public String toString() {
    StringBuilder b = new StringBuilder("SkRegion(");
    for (int i = 0; i < n; i += 4) b.append('(').append(rs[i]).append(',').append(rs[i + 1]).append(',').append(rs[i + 2]).append(',').append(rs[i + 3]).append(')');
    return b.append(')').toString();
  }
}
