package android.graphics;

import android.content.res.Resources;
import android.util.TypedValue;
import a2w.Host;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.teavm.jso.JSObject;

public class BitmapFactory {
  public static class Options {
    public Bitmap inBitmap;
    public boolean inJustDecodeBounds, inMutable, inDither, inPurgeable, inInputShareable, inPreferQualityOverSpeed, inTempStorageSet;
    public int inSampleSize = 0, inDensity, inTargetDensity, inScreenDensity;
    public boolean inScaled = true;
    public Bitmap.Config inPreferredConfig = Bitmap.Config.ARGB_8888;
    public boolean inPremultiplied = true;
    public byte[] inTempStorage;
    public int outWidth, outHeight;
    public String outMimeType;
    public boolean mCancel;
    public void requestCancelDecode() { mCancel = true; }
  }

  /** width, height, mime of a PNG/JPEG/GIF/BMP header, or null */
  static Object[] probe(byte[] d) {
    if (d == null || d.length < 24) return null;
    if ((d[0] & 0xff) == 0x89 && d[1] == 'P' && d[2] == 'N' && d[3] == 'G')
      return new Object[] {be32(d, 16), be32(d, 20), "image/png"};
    if ((d[0] & 0xff) == 0xFF && (d[1] & 0xff) == 0xD8) {
      int i = 2;
      while (i + 9 < d.length) {
        if ((d[i] & 0xff) != 0xFF) { i++; continue; }
        int m = d[i + 1] & 0xff;
        if (m >= 0xC0 && m <= 0xCF && m != 0xC4 && m != 0xC8 && m != 0xCC)
          return new Object[] {((d[i + 7] & 0xff) << 8) | (d[i + 8] & 0xff), ((d[i + 5] & 0xff) << 8) | (d[i + 6] & 0xff), "image/jpeg"};
        if (m == 0xD8 || m == 0x01 || (m >= 0xD0 && m <= 0xD7)) { i += 2; continue; }
        i += 2 + (((d[i + 2] & 0xff) << 8) | (d[i + 3] & 0xff));
      }
      return null;
    }
    if (d[0] == 'G' && d[1] == 'I' && d[2] == 'F') return new Object[] {(d[6] & 0xff) | (d[7] & 0xff) << 8, (d[8] & 0xff) | (d[9] & 0xff) << 8, "image/gif"};
    if (d[0] == 'B' && d[1] == 'M') return new Object[] {le32(d, 18), Math.abs(le32(d, 22)), "image/bmp"};
    if (d[0] == 'R' && d[1] == 'I' && d[2] == 'F' && d[3] == 'F') return new Object[] {0, 0, "image/webp"};
    return null;
  }
  static int be32(byte[] d, int o) { return (d[o] & 0xff) << 24 | (d[o + 1] & 0xff) << 16 | (d[o + 2] & 0xff) << 8 | (d[o + 3] & 0xff); }
  static int le32(byte[] d, int o) { return (d[o] & 0xff) | (d[o + 1] & 0xff) << 8 | (d[o + 2] & 0xff) << 16 | (d[o + 3] & 0xff) << 24; }

  /** Decodes like Skia: subsampling first, then density scaling. */
  public static Bitmap decode(byte[] data, Options o) {
    Object[] p = probe(data);
    if (p == null) { if (o != null) { o.outWidth = -1; o.outHeight = -1; } return null; }
    int w = (Integer) p[0], h = (Integer) p[1];
    int sample = o == null || o.inSampleSize <= 1 ? 1 : o.inSampleSize;
    if (sample > 1) { w = Math.max(1, w / sample); h = Math.max(1, h / sample); }
    float scale = 1;
    int density = 0;
    if (o != null) {
      density = o.inDensity;
      if (o.inScaled && density != 0 && o.inTargetDensity != 0 && density != o.inTargetDensity && density != o.inScreenDensity)
        scale = o.inTargetDensity / (float) density;
    }
    int sw = scale == 1 ? w : (int) (w * scale + 0.5f), sh = scale == 1 ? h : (int) (h * scale + 0.5f);
    if (o != null) { o.outWidth = sw; o.outHeight = sh; o.outMimeType = (String) p[2]; }
    if (o != null && o.inJustDecodeBounds) return null;
    JSObject img = Host.decodeImage(data, sample);
    if (img == null) return null;
    int iw = Host.width(img), ih = Host.height(img);
    Bitmap b;
    if (iw != sw || ih != sh) {
      Bitmap tmp = Bitmap.wrap(img, iw, ih);
      b = Bitmap.createScaledBitmap(tmp, sw, sh, true);
      b.mutable = o != null && o.inMutable;
    } else {
      b = Bitmap.wrap(img, iw, ih);
      if (o != null && o.inMutable) { b.ctx(); b.mutable = true; }
    }
    if (o != null) {
      if (density != 0) b.density = o.inScaled && o.inTargetDensity != 0 && density != o.inTargetDensity ? o.inTargetDensity : density;
      if (o.inPreferredConfig != null && o.inPreferredConfig != Bitmap.Config.HARDWARE) b.config = o.inPreferredConfig;
      if (b.config == Bitmap.Config.RGB_565) b.hasAlpha = false;
      if (!o.inPremultiplied) b.premultiplied = false;
    }
    // a compiled nine-patch: its chunk (scaled with the bitmap), optical and outline insets
    byte[][] np = NinePatch.pngChunks(data);
    if (np != null) {
      float s = (float) b.width / (Integer) p[0];
      if (s != 1) NinePatch.scaleChunk(np[0], s, b.width, b.height);
      b.ninePatchChunk = np[0];
      if (np[1] != null) b.npLb = scaleInts(np[1], 4, s);
      if (np[2] != null) {
        int[] ol = scaleInts(np[2], 6, s);
        ol[4] = Float.floatToIntBits(Float.intBitsToFloat(ol[4]) * s);
        b.npOl = ol;
      }
    }
    return b;
  }
  /** little-endian ints of a chunk, the first four scaled */
  private static int[] scaleInts(byte[] d, int n, float s) {
    int[] r = new int[n];
    for (int i = 0; i < n; i++) { r[i] = le32(d, 4 * i); if (i < 4 && s != 1) r[i] = (int) (r[i] * s + 0.5f); }
    return r;
  }
  /** decodeStream's outPadding: the nine-patch padding, or -1s */
  static Bitmap withPadding(Bitmap b, Rect pad) {
    if (pad == null) return b;
    byte[] c = b == null ? null : b.ninePatchChunk;
    if (c == null) pad.set(-1, -1, -1, -1);
    else pad.set(le32(c, 12), le32(c, 20), le32(c, 16), le32(c, 24));
    return b;
  }

  static byte[] readAll(InputStream is) {
    if (is instanceof a2w.jdk.FileInputStream) {
      a2w.jdk.FileInputStream f = (a2w.jdk.FileInputStream) is;
      if (f.available() == f.buffer().length) return f.buffer();
    }
    try {
      ByteArrayOutputStream bo = new ByteArrayOutputStream();
      byte[] buf = new byte[16384];
      int n;
      while ((n = is.read(buf)) > 0) bo.write(buf, 0, n);
      return bo.toByteArray();
    } catch (IOException e) {
      return null;
    }
  }

  public static Bitmap decodeByteArray(byte[] data, int off, int len, Options o) {
    if (off != 0 || len != data.length) { byte[] d = new byte[len]; System.arraycopy(data, off, d, 0, len); data = d; }
    return decode(data, o);
  }
  public static Bitmap decodeByteArray(byte[] data, int off, int len) { return decodeByteArray(data, off, len, null); }
  public static Bitmap decodeStream(InputStream is) { return decodeStream(is, null, null); }
  public static Bitmap decodeStream(InputStream is, Rect pad, Options o) {
    if (is == null) return null;
    return withPadding(decode(readAll(is), o), pad);
  }
  public static Bitmap decodeFile(String path, Options o) {
    try { return decodeStream(new a2w.jdk.FileInputStream(path), null, o); } catch (IOException e) { return null; }
  }
  public static Bitmap decodeFile(String path) { return decodeFile(path, null); }
  public static Bitmap decodeFileDescriptor(a2w.jdk.FileDescriptor fd, Rect pad, Options o) { return decodeStream(new a2w.jdk.FileInputStream(fd), pad, o); }
  public static Bitmap decodeFileDescriptor(a2w.jdk.FileDescriptor fd) { return decodeFileDescriptor(fd, null, null); }
  public static Bitmap decodeResourceStream(Resources res, TypedValue value, InputStream is, Rect pad, Options o) {
    if (o == null) o = new Options();
    if (o.inDensity == 0 && value != null) {
      int d = value.density;
      if (d == TypedValue.DENSITY_DEFAULT) o.inDensity = 160;
      else if (d != TypedValue.DENSITY_NONE) o.inDensity = d;
    }
    if (o.inTargetDensity == 0 && res != null) o.inTargetDensity = res.getDisplayMetrics().densityDpi;
    return decodeStream(is, pad, o);
  }
  public static Bitmap decodeResource(Resources res, int id, Options o) {
    TypedValue v = new TypedValue();
    InputStream is;
    try { is = res.openRawResource(id, v); } catch (Resources.NotFoundException e) { return null; }
    return decodeResourceStream(res, v, is, null, o);
  }
  public static Bitmap decodeResource(Resources res, int id) { return decodeResource(res, id, null); }
}
