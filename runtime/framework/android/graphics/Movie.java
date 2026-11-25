package android.graphics;

/**
 * android.graphics.Movie: an animated GIF (like SkGIFMovie). Frames are LZW-decoded to color indexes once and
 * composed (with their disposal methods) as the time moves; draw paints the current frame.
 */
public class Movie {
  private static final class Frame {
    int x, y, w, h, delay, disposal, transparent = -1;
    int[] palette;
    byte[] index;
  }
  private final int width, height, duration;
  private final Frame[] frames;
  private int time, shown = -1, current;
  private int[] canvas, saved;
  private Bitmap bitmap;

  private Movie(int w, int h, Frame[] f) {
    width = w; height = h; frames = f;
    int d = 0;
    for (Frame x : f) d += x.delay;
    duration = d;
  }

  public int width() { return width; }
  public int height() { return height; }
  public int duration() { return duration; }
  public boolean isOpaque() { return false; }
  /** the frame for this time (clamped to the duration); true when the time changed */
  public boolean setTime(int relativeMilliseconds) {
    int t = Math.max(0, Math.min(relativeMilliseconds, duration));
    if (t == time && shown >= 0) return false;
    time = t;
    int d = 0;
    current = frames.length - 1;
    for (int i = 0; i < frames.length; i++) { d += frames[i].delay; if (d >= t) { current = i; break; } }
    return true;
  }
  public void draw(Canvas canvas, float x, float y) { draw(canvas, x, y, null); }
  public void draw(Canvas c, float x, float y, Paint paint) {
    if (frames.length == 0) return;
    compose(current);
    c.drawBitmap(bitmap, x, y, paint);
  }

  private void compose(int target) {
    if (target == shown && bitmap != null) return;
    if (canvas == null || target < shown) { canvas = new int[width * height]; shown = -1; saved = null; }
    for (int i = shown + 1; i <= target; i++) {
      if (i > 0) dispose(frames[i - 1]);
      Frame f = frames[i];
      if (f.disposal == 3) saved = canvas.clone();
      for (int yy = 0; yy < f.h; yy++) {
        int cy = f.y + yy;
        if (cy < 0 || cy >= height) continue;
        for (int xx = 0; xx < f.w; xx++) {
          int cx = f.x + xx;
          if (cx < 0 || cx >= width) continue;
          int k = f.index[yy * f.w + xx] & 0xff;
          if (k == f.transparent || k >= f.palette.length) continue;
          canvas[cy * width + cx] = f.palette[k];
        }
      }
    }
    shown = target;
    if (bitmap == null) bitmap = Bitmap.createBitmap(Math.max(1, width), Math.max(1, height), Bitmap.Config.ARGB_8888);
    bitmap.setPixels(canvas, 0, width, 0, 0, width, height);
  }
  /** what a frame leaves behind before the next: 2 clears its rectangle, 3 restores what was there */
  private void dispose(Frame f) {
    if (f.disposal == 2) {
      for (int yy = Math.max(0, f.y); yy < Math.min(height, f.y + f.h); yy++)
        for (int xx = Math.max(0, f.x); xx < Math.min(width, f.x + f.w); xx++) canvas[yy * width + xx] = 0;
    } else if (f.disposal == 3 && saved != null) { canvas = saved; saved = null; }
  }

  public static Movie decodeStream(java.io.InputStream is) {
    if (is == null) return null;
    byte[] d = BitmapFactory.readAll(is);
    return d == null ? null : decode(d, 0, d.length);
  }
  public static Movie decodeByteArray(byte[] data, int offset, int length) {
    if ((offset | length) < 0 || data.length < offset + length) throw new ArrayIndexOutOfBoundsException();
    return decode(data, offset, length);
  }
  public static Movie decodeFile(String pathName) {
    try (java.io.InputStream is = new a2w.jdk.FileInputStream(pathName)) { return decodeStream(is); } catch (java.io.IOException e) { return null; }
  }

  // ---- GIF87a / GIF89a
  private static Movie decode(byte[] d, int off, int len) {
    try {
      int p = off, end = off + len;
      if (len < 13 || d[p] != 'G' || d[p + 1] != 'I' || d[p + 2] != 'F') return null;
      int w = u16(d, p + 6), h = u16(d, p + 8), packed = d[p + 10] & 0xff;
      p += 13;
      int[] global = null;
      if ((packed & 0x80) != 0) { int n = 2 << (packed & 7); global = palette(d, p, n); p += 3 * n; }
      java.util.ArrayList<Frame> frames = new java.util.ArrayList<>();
      int delay = 0, disposal = 0, transparent = -1;
      while (p < end) {
        int b = d[p++] & 0xff;
        if (b == 0x3B) break;
        if (b == 0x21) {
          int label = d[p++] & 0xff;
          if (label == 0xF9 && (d[p] & 0xff) >= 4) {
            int f = d[p + 1] & 0xff;
            disposal = (f >> 2) & 7;
            delay = u16(d, p + 2) * 10;
            transparent = (f & 1) != 0 ? d[p + 4] & 0xff : -1;
          }
          p = skipBlocks(d, p);
        } else if (b == 0x2C) {
          Frame f = new Frame();
          f.x = u16(d, p); f.y = u16(d, p + 2); f.w = u16(d, p + 4); f.h = u16(d, p + 6);
          int fp = d[p + 8] & 0xff;
          p += 9;
          f.palette = global;
          if ((fp & 0x80) != 0) { int n = 2 << (fp & 7); f.palette = palette(d, p, n); p += 3 * n; }
          if (f.palette == null) f.palette = new int[0];
          int minCode = d[p++] & 0xff;
          java.io.ByteArrayOutputStream data = new java.io.ByteArrayOutputStream();
          while (p < end) { int n = d[p++] & 0xff; if (n == 0) break; data.write(d, p, Math.min(n, end - p)); p += n; }
          byte[] idx = lzw(data.toByteArray(), minCode, f.w * f.h);
          if ((fp & 0x40) != 0) idx = deinterlace(idx, f.w, f.h);
          f.index = idx;
          f.delay = delay; f.disposal = disposal; f.transparent = transparent;
          frames.add(f);
          delay = 0; disposal = 0; transparent = -1;
        } else break;
      }
      if (frames.isEmpty()) return null;
      return new Movie(w, h, frames.toArray(new Frame[0]));
    } catch (RuntimeException e) {
      return null;
    }
  }
  private static int u16(byte[] d, int p) { return (d[p] & 0xff) | (d[p + 1] & 0xff) << 8; }
  private static int[] palette(byte[] d, int p, int n) {
    int[] c = new int[n];
    for (int i = 0; i < n; i++) c[i] = 0xff000000 | (d[p + 3 * i] & 0xff) << 16 | (d[p + 3 * i + 1] & 0xff) << 8 | (d[p + 3 * i + 2] & 0xff);
    return c;
  }
  private static int skipBlocks(byte[] d, int p) {
    while (p < d.length) { int n = d[p++] & 0xff; if (n == 0) break; p += n; }
    return p;
  }
  private static byte[] lzw(byte[] in, int minCode, int count) {
    byte[] out = new byte[count];
    int clear = 1 << minCode, eoi = clear + 1, codeSize = minCode + 1, next = eoi + 1;
    short[] prefix = new short[4096];
    byte[] suffix = new byte[4096], stack = new byte[4097];
    for (int i = 0; i < clear; i++) { prefix[i] = -1; suffix[i] = (byte) i; }
    int bits = 0, buf = 0, pos = 0, o = 0, prev = -1;
    byte first = 0;
    while (o < count) {
      while (bits < codeSize && pos < in.length) { buf |= (in[pos++] & 0xff) << bits; bits += 8; }
      if (bits < codeSize) break;
      int code = buf & ((1 << codeSize) - 1);
      buf >>>= codeSize; bits -= codeSize;
      if (code == clear) { codeSize = minCode + 1; next = eoi + 1; prev = -1; continue; }
      if (code == eoi) break;
      int c = code, sp = 0;
      if (prev == -1) {
        if (code >= clear) break;
        out[o++] = suffix[code]; first = suffix[code]; prev = code;
        continue;
      }
      if (code >= next) { stack[sp++] = first; c = prev; }
      while (c >= clear && sp < 4096) { stack[sp++] = suffix[c]; c = prefix[c]; }
      first = suffix[c];
      stack[sp++] = first;
      while (sp > 0 && o < count) out[o++] = stack[--sp];
      if (next < 4096) { prefix[next] = (short) prev; suffix[next] = first; next++; if (next == (1 << codeSize) && codeSize < 12) codeSize++; }
      prev = code;
    }
    return out;
  }
  private static byte[] deinterlace(byte[] in, int w, int h) {
    byte[] out = new byte[in.length];
    int row = 0;
    int[] passes = {0, 8, 4, 8, 2, 4, 1, 2};
    for (int k = 0; k < 8; k += 2) for (int y = passes[k]; y < h; y += passes[k + 1]) { System.arraycopy(in, row * w, out, y * w, w); row++; }
    return out;
  }
}
