package android.graphics;

/**
 * android.graphics.NinePatch: a bitmap with stretchable columns and rows, described by the Res_png_9patch chunk
 * (as BitmapFactory returns it from a compiled .9.png's npTc chunk, in device byte order).
 */
public class NinePatch {
  /** (hidden API) optical and outline insets of a nine-patch */
  public static class InsetStruct {
    public final Rect opticalRect, outlineRect;
    public final float outlineRadius, outlineAlpha;
    InsetStruct(Rect optical, Rect outline, float radius, float alpha) { opticalRect = optical; outlineRect = outline; outlineRadius = radius; outlineAlpha = alpha; }
  }
  static final int NO_COLOR = 0x00000001, TRANSPARENT_COLOR = 0x00000000;

  private final Bitmap mBitmap;
  private Paint mPaint;
  private String mSrcName;
  final int[] xDivs, yDivs, colors;
  final Rect padding = new Rect();

  public NinePatch(Bitmap bitmap, byte[] chunk) { this(bitmap, chunk, null); }
  public NinePatch(Bitmap bitmap, byte[] chunk, String srcName) {
    mBitmap = bitmap;
    mSrcName = srcName;
    if (chunk == null || chunk.length < 32) { xDivs = yDivs = colors = new int[0]; return; }
    int nx = chunk[1] & 0xff, ny = chunk[2] & 0xff, nc = chunk[3] & 0xff;
    if (chunk.length < 32 + 4 * (nx + ny + nc)) throw new IllegalArgumentException("invalid nine-patch chunk");
    padding.set(le32(chunk, 12), le32(chunk, 20), le32(chunk, 16), le32(chunk, 24));
    xDivs = new int[nx]; yDivs = new int[ny]; colors = new int[nc];
    for (int i = 0; i < nx; i++) xDivs[i] = le32(chunk, 32 + 4 * i);
    for (int i = 0; i < ny; i++) yDivs[i] = le32(chunk, 32 + 4 * (nx + i));
    for (int i = 0; i < nc; i++) colors[i] = le32(chunk, 32 + 4 * (nx + ny + i));
  }
  static int le32(byte[] d, int o) { return (d[o] & 0xff) | (d[o + 1] & 0xff) << 8 | (d[o + 2] & 0xff) << 16 | (d[o + 3] & 0xff) << 24; }
  static int be32(byte[] d, int o) { return (d[o] & 0xff) << 24 | (d[o + 1] & 0xff) << 16 | (d[o + 2] & 0xff) << 8 | (d[o + 3] & 0xff); }
  static void putLe32(byte[] d, int o, int v) { d[o] = (byte) v; d[o + 1] = (byte) (v >> 8); d[o + 2] = (byte) (v >> 16); d[o + 3] = (byte) (v >> 24); }

  /** (not part of the API) the chunk's padding */
  public Rect a2wPadding() { return new Rect(padding); }
  public String getName() { return mSrcName; }
  public Paint getPaint() { return mPaint; }
  public void setPaint(Paint p) { mPaint = p; }
  public Bitmap getBitmap() { return mBitmap; }
  public int getDensity() { return mBitmap.getDensity(); }
  public int getWidth() { return mBitmap.getWidth(); }
  public int getHeight() { return mBitmap.getHeight(); }
  public final boolean hasAlpha() { return mBitmap.hasAlpha(); }
  /** a chunk unless it is marked as already deserialized (wasDeserialized == -1) */
  public static boolean isNinePatchChunk(byte[] chunk) { return chunk != null && chunk.length >= 32 && chunk[0] != -1; }

  public void draw(Canvas canvas, RectF location) { draw(canvas, location.left, location.top, location.right, location.bottom, mPaint, false); }
  public void draw(Canvas canvas, Rect location) { draw(canvas, location, mPaint); }
  public void draw(Canvas canvas, Rect location, Paint paint) { draw(canvas, location.left, location.top, location.right, location.bottom, paint, true); }

  /** segment edges along one axis: src[i]..src[i+1] maps to dst[i]..dst[i+1] */
  private static float[][] lattice(int[] divs, int size, float start, float length, boolean round) {
    int k = divs.length & ~1;
    int[] e = new int[k + 2];
    e[0] = 0;
    for (int i = 0; i < k; i++) e[i + 1] = Math.max(e[i], Math.min(size, divs[i]));
    e[k + 1] = size;
    int stretch = 0;
    for (int i = 1; i < k + 1; i += 2) stretch += e[i + 1] - e[i];
    int fixed = size - stretch;
    float[] src = new float[k + 2], dst = new float[k + 2];
    float pos = start;
    for (int i = 0; i < k + 2; i++) {
      src[i] = e[i];
      dst[i] = round ? Math.round(pos) : pos;
      if (i == k + 1) break;
      int len = e[i + 1] - e[i];
      boolean isStretch = (i & 1) == 1;
      float d;
      if (stretch == 0) d = size == 0 ? 0 : len * length / size;
      else if (length >= fixed) d = isStretch ? len * (length - fixed) / stretch : len;
      else d = isStretch ? 0 : len * length / fixed;
      pos += d;
    }
    dst[k + 1] = round ? Math.round(start + length) : start + length;
    return new float[][] {src, dst};
  }
  private void draw(Canvas canvas, float l, float t, float r, float b, Paint paint, boolean round) {
    if (mBitmap.isRecycled()) throw new RuntimeException("Canvas: trying to use a recycled bitmap " + mBitmap);
    int cd = canvas.getDensity(), bd = mBitmap.getDensity();
    boolean scaled = cd != 0 && bd != 0 && cd != bd;
    if (scaled) {
      float s = cd / (float) bd;
      canvas.save();
      canvas.translate(l, t);
      canvas.scale(s, s);
      r = (r - l) / s; b = (b - t) / s; l = 0; t = 0;
      round = false;
    }
    float[][] xs = lattice(xDivs, mBitmap.getWidth(), l, r - l, round), ys = lattice(yDivs, mBitmap.getHeight(), t, b - t, round);
    Rect s = new Rect();
    RectF d = new RectF();
    for (int j = 0; j + 1 < ys[0].length; j++) {
      if (ys[0][j + 1] <= ys[0][j] || ys[1][j + 1] <= ys[1][j]) continue;
      for (int i = 0; i + 1 < xs[0].length; i++) {
        if (xs[0][i + 1] <= xs[0][i] || xs[1][i + 1] <= xs[1][i]) continue;
        s.set((int) xs[0][i], (int) ys[0][j], (int) xs[0][i + 1], (int) ys[0][j + 1]);
        d.set(xs[1][i], ys[1][j], xs[1][i + 1], ys[1][j + 1]);
        canvas.drawBitmap(mBitmap, s, d, paint);
      }
    }
    if (scaled) canvas.restore();
  }

  /** the patches marked fully transparent, laid out over bounds; null when there are none */
  public final Region getTransparentRegion(Rect bounds) {
    float[][] xs = lattice(xDivs, mBitmap.getWidth(), bounds.left, bounds.width(), true), ys = lattice(yDivs, mBitmap.getHeight(), bounds.top, bounds.height(), true);
    Region out = null;
    int c = 0;
    for (int j = 0; j + 1 < ys[0].length; j++) {
      if (ys[0][j + 1] <= ys[0][j]) continue;
      for (int i = 0; i + 1 < xs[0].length; i++) {
        if (xs[0][i + 1] <= xs[0][i]) continue;
        if (c < colors.length && colors[c] == TRANSPARENT_COLOR) {
          if (out == null) out = new Region();
          out.op((int) xs[1][i], (int) ys[1][j], (int) xs[1][i + 1], (int) ys[1][j + 1], Region.Op.UNION);
        }
        c++;
      }
    }
    return out;
  }

  // ---- compiled .9.png chunks, for BitmapFactory
  /** npTc (in device byte order), npLb and npOl of a PNG, or null when it has no npTc */
  static byte[][] pngChunks(byte[] d) {
    if (d == null || d.length < 8 || (d[0] & 0xff) != 0x89 || d[1] != 'P' || d[2] != 'N' || d[3] != 'G') return null;
    byte[] tc = null, lb = null, ol = null;
    int i = 8;
    while (i + 8 <= d.length) {
      int len = be32(d, i);
      if (len < 0 || i + 12 + len > d.length) break;
      String type = new String(d, i + 4, 4, java.nio.charset.StandardCharsets.ISO_8859_1);
      if (type.equals("npTc")) tc = java.util.Arrays.copyOfRange(d, i + 8, i + 8 + len);
      else if (type.equals("npLb") && len == 16) lb = java.util.Arrays.copyOfRange(d, i + 8, i + 8 + len);
      else if (type.equals("npOl") && len >= 24) ol = java.util.Arrays.copyOfRange(d, i + 8, i + 8 + len);
      else if (type.equals("IDAT") || type.equals("IEND")) { if (tc != null && type.equals("IEND")) break; }
      i += 12 + len;
    }
    if (tc == null || tc.length < 32) return null;
    int nx = tc[1] & 0xff, ny = tc[2] & 0xff, nc = tc[3] & 0xff;
    if (tc.length < 32 + 4 * (nx + ny + nc)) return null;
    // file order is big-endian for padding, divs and colors (Res_png_9patch::fileToDevice)
    for (int o = 12; o < 28; o += 4) putLe32(tc, o, be32(tc, o));
    for (int k = 0; k < nx + ny + nc; k++) putLe32(tc, 32 + 4 * k, be32(tc, 32 + 4 * k));
    return new byte[][] {tc, lb, ol};
  }
  /** the chunk for a bitmap scaled by s to w x h (like BitmapFactory's scaleNinePatchChunk) */
  static void scaleChunk(byte[] tc, float s, int w, int h) {
    for (int o = 12; o < 28; o += 4) putLe32(tc, o, (int) (le32(tc, o) * s + 0.5f));
    int nx = tc[1] & 0xff, ny = tc[2] & 0xff;
    scaleDivs(tc, 32, nx, s, w);
    scaleDivs(tc, 32 + 4 * nx, ny, s, h);
  }
  private static void scaleDivs(byte[] tc, int off, int count, float s, int max) {
    if (count == 0) return;
    int[] d = new int[count];
    for (int i = 0; i < count; i++) {
      d[i] = (int) (le32(tc, off + 4 * i) * s + 0.5f);
      if (i > 0 && d[i] == d[i - 1]) d[i]++;
    }
    if (d[count - 1] > max) {
      int high = max;
      for (int i = count - 1; i >= 0; i--) {
        d[i] = high;
        if (i > 0 && d[i] <= d[i - 1]) high = d[i] - 1; else break;
      }
    }
    for (int i = 0; i < count; i++) putLe32(tc, off + 4 * i, d[i]);
  }
}
