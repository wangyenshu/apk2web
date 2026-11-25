package android.graphics;

import a2w.Host;
import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import org.teavm.jso.typedarrays.Float32Array;
import org.teavm.jso.typedarrays.Int32Array;
import org.teavm.jso.typedarrays.Int8Array;

/**
 * A bitmap is an ImageBitmap (decoded, immutable) or a canvas (mutable) owned by the page. Pixels are kept as
 * 8-bit RGBA whatever the config; the config decides the byte layout of the pixel-buffer methods, getByteCount,
 * and that RGB_565 is opaque and ALPHA_8 keeps alpha only (drawn tinted with the paint's color).
 */
public final class Bitmap implements android.os.Parcelable {
  public static final int DENSITY_NONE = 0;
  public enum Config { ALPHA_8, RGB_565, ARGB_4444, ARGB_8888, RGBA_F16, HARDWARE, RGBA_1010102 }
  public enum CompressFormat { JPEG, PNG, WEBP, WEBP_LOSSY, WEBP_LOSSLESS }
  private static int defaultDensity = a2w.Screen.DPI;

  JSObject img;      // drawable source for the page
  /** counts replacements of img (shaders re-describe the bitmap) */
  int imgVersion;
  JSObject ctx;      // 2D context once the bitmap is canvas-backed
  int width, height, density = defaultDensity;
  Config config = Config.ARGB_8888;
  boolean mutable, recycled, hasAlpha = true, premultiplied = true, mipMap;
  private int allocation = -1;
  private ColorSpace colorSpace;
  private Gainmap gainmap;
  byte[] ninePatchChunk;
  /** npLb (optical insets l, t, r, b) and npOl (outline insets l, t, r, b, radius float bits, alpha) of a compiled .9.png */
  int[] npLb, npOl;
  private JSObject tintImg;
  private int tintColor;

  Bitmap(JSObject img, int w, int h, boolean mutable) { this.img = img; width = w; height = h; this.mutable = mutable; }
  public static Bitmap wrap(JSObject img, int w, int h) { return new Bitmap(img, w, h, false); }
  /** the page image behind this bitmap (GLUtils uploads it as a texture) */
  public JSObject a2wImage() { return img; }

  @JSBody(params = {"w", "h"}, script = "return HW.bmp.create(w, h);")
  static native JSObject newCanvas(int w, int h);
  @JSBody(params = {"c"}, script = "return HW.bmp.ctx(c);")
  static native JSObject context(JSObject c);
  @JSBody(params = {"i"}, script = "return HW.bmp.toCanvas(i);")
  static native JSObject toCanvas(JSObject img);
  @JSBody(params = {"i", "x", "y", "w", "h"}, script = "return HW.bmp.getPixels(i, x, y, w, h);")
  static native Int32Array getPixelsJS(JSObject img, int x, int y, int w, int h);
  @JSBody(params = {"c", "p", "x", "y", "w", "h"}, script = "HW.bmp.setPixels(c, p, x, y, w, h);")
  static native void setPixelsJS(JSObject ctx, Int32Array p, int x, int y, int w, int h);
  @JSBody(params = {"i", "m", "sx", "sy", "sw", "sh", "dw", "dh", "f"}, script = "return HW.bmp.transformed(i, m, sx, sy, sw, sh, dw, dh, f);")
  static native JSObject transformedJS(JSObject img, Float32Array m, int sx, int sy, int sw, int sh, int dw, int dh, boolean filter);
  @JSBody(params = {"c", "col"}, script = "HW.bmp.erase(c, col);")
  static native void eraseJS(JSObject ctx, int color);
  @JSBody(params = {"i", "w", "h"}, script = "return HW.bmp.rgba(i, w, h);")
  static native Int8Array rgbaJS(JSObject img, int w, int h);
  @JSBody(params = {"i"}, script = "if (i && i.close) i.close();")
  static native void closeJS(JSObject img);
  @JSBody(params = {"i", "w", "h", "t", "q"}, script = "return HW.bmp.encode(i, w, h, t, q);")
  static native Int8Array encodeJS(JSObject img, int w, int h, String type, double quality);
  @JSBody(params = {"i", "w", "h", "col"}, script = "return HW.bmp.tint(i, w, h, col);")
  static native JSObject tintJS(JSObject img, int w, int h, int color);
  @JSBody(params = {"i", "w", "h", "sigma", "pad"}, script = "return HW.bmp.alpha(i, w, h, sigma, pad);")
  static native JSObject alphaJS(JSObject img, int w, int h, double sigma, int pad);
  @JSBody(params = {"i", "w", "h"}, script = "return HW.bmp.opaque(i, w, h);")
  static native JSObject opaqueJS(JSObject img, int w, int h);
  @JSBody(params = {"c", "w", "h"}, script = "c.width = w; c.height = h;")
  static native void resizeJS(JSObject canvas, int w, int h);

  /** The canvas context, converting a decoded image to a canvas first. */
  public JSObject ctx() {
    if (ctx == null) {
      if (!isCanvas(img)) { img = toCanvas(img); imgVersion++; }
      ctx = context(img);
    }
    return ctx;
  }
  @JSBody(params = {"i"}, script = "return !!i && !!i.getContext;")
  static native boolean isCanvas(JSObject i);
  public JSObject js() { return img; }
  /** what Canvas draws: ALPHA_8 bitmaps take the paint's color */
  JSObject drawSource(Paint p) {
    if (config != Config.ALPHA_8 || p == null) return img;
    int c = p.getColor() | 0xff000000;
    if (tintImg == null || tintColor != c || mutable) { tintImg = tintJS(img, width, height, c); tintColor = c; }
    return tintImg;
  }
  private void checkRecycled(String what) { if (recycled) throw new IllegalStateException("Can't call " + what + " on a recycled bitmap"); }

  static int bytesPerPixel(Config c) {
    if (c == null) return 4;
    switch (c) {
      case ALPHA_8: return 1;
      case RGB_565: case ARGB_4444: return 2;
      case RGBA_F16: return 8;
      default: return 4;
    }
  }

  public static Bitmap createBitmap(int w, int h, Config c) { return createBitmap(w, h, c, true); }
  public static Bitmap createBitmap(int w, int h, Config c, boolean hasAlpha) { return createBitmap(null, w, h, c, hasAlpha, null); }
  public static Bitmap createBitmap(int w, int h, Config c, boolean hasAlpha, ColorSpace cs) { return createBitmap(null, w, h, c, hasAlpha, cs); }
  public static Bitmap createBitmap(android.util.DisplayMetrics d, int w, int h, Config c) { return createBitmap(d, w, h, c, true, null); }
  public static Bitmap createBitmap(android.util.DisplayMetrics d, int w, int h, Config c, boolean hasAlpha) { return createBitmap(d, w, h, c, hasAlpha, null); }
  public static Bitmap createBitmap(android.util.DisplayMetrics d, int w, int h, Config c, boolean hasAlpha, ColorSpace cs) {
    if (w <= 0 || h <= 0) throw new IllegalArgumentException("width and height must be > 0");
    if (c == Config.HARDWARE) throw new IllegalArgumentException("can't create mutable bitmap with Config.HARDWARE");
    Bitmap b = new Bitmap(newCanvas(w, h), w, h, true);
    b.config = c == null ? Config.ARGB_8888 : c;
    if (d != null) b.density = d.densityDpi;
    if (cs != null && b.config != Config.ALPHA_8) b.colorSpace = cs;
    if (b.config == Config.RGB_565) { b.hasAlpha = false; eraseJS(b.ctx(), 0xff000000); }
    else if (!hasAlpha) { b.hasAlpha = false; if (b.config == Config.ARGB_8888 || b.config == Config.RGBA_F16) eraseJS(b.ctx(), 0xff000000); }
    return b;
  }
  public static Bitmap createBitmap(Bitmap src) { return createBitmap(src, 0, 0, src.width, src.height); }
  public static Bitmap createBitmap(Bitmap src, int x, int y, int w, int h) { return createBitmap(src, x, y, w, h, null, false); }
  public static Bitmap createBitmap(Bitmap src, int x, int y, int w, int h, Matrix m, boolean filter) {
    if (x < 0 || y < 0 || x + w > src.width || y + h > src.height) throw new IllegalArgumentException("x + width must be <= bitmap.width()");
    if (!src.mutable && x == 0 && y == 0 && w == src.width && h == src.height && (m == null || m.isIdentity())) return src;
    int dw = w, dh = h;
    float[] mm = null;
    if (m != null && !m.isIdentity()) {
      RectF r = new RectF(0, 0, w, h);
      m.mapRect(r);
      dw = Math.round(r.width()); dh = Math.round(r.height());
      Matrix t = new Matrix(m);
      t.postTranslate(-r.left, -r.top);
      mm = new float[] {t.m[0], t.m[3], t.m[1], t.m[4], t.m[2], t.m[5]};
    }
    if (dw <= 0) dw = 1;
    if (dh <= 0) dh = 1;
    Bitmap b = new Bitmap(transformedJS(src.img, mm == null ? null : Float32Array.copyFromJavaArray(mm), x, y, w, h, dw, dh, filter), dw, dh, true);
    b.density = src.density;
    b.config = src.config == Config.HARDWARE ? Config.ARGB_8888 : src.config;
    b.hasAlpha = src.hasAlpha || mm != null;
    b.colorSpace = src.colorSpace;
    return b;
  }
  public static Bitmap createBitmap(int[] colors, int w, int h, Config c) { return createBitmap(colors, 0, w, w, h, c); }
  public static Bitmap createBitmap(int[] colors, int off, int stride, int w, int h, Config c) { return createBitmap(null, colors, off, stride, w, h, c); }
  public static Bitmap createBitmap(android.util.DisplayMetrics d, int[] colors, int w, int h, Config c) { return createBitmap(d, colors, 0, w, w, h, c); }
  public static Bitmap createBitmap(android.util.DisplayMetrics d, int[] colors, int off, int stride, int w, int h, Config c) {
    int last = off + (h - 1) * stride, len = colors.length;
    if (off < 0 || (off + w > len) || last < 0 || (last + w > len)) throw new ArrayIndexOutOfBoundsException();
    Bitmap b = createBitmap(d, w, h, c, true, null);
    b.setPixels(colors, off, stride, 0, 0, w, h);
    b.mutable = false;
    return b;
  }
  /** the picture rasterized at its own size (immutable) */
  public static Bitmap createBitmap(Picture source) { return createBitmap(source, source.getWidth(), source.getHeight(), Config.HARDWARE); }
  public static Bitmap createBitmap(Picture source, int w, int h, Config c) {
    Bitmap b = createBitmap(null, w, h, c == Config.HARDWARE ? Config.ARGB_8888 : c, true, null);
    Canvas canvas = new Canvas(b);
    if (source.getWidth() != w || source.getHeight() != h) canvas.scale(w / (float) source.getWidth(), h / (float) source.getHeight());
    canvas.drawPicture(source);
    b.config = c == null ? Config.ARGB_8888 : c;
    b.mutable = false;
    return b;
  }
  public static Bitmap createScaledBitmap(Bitmap src, int w, int h, boolean filter) {
    if (w == src.width && h == src.height) return src;
    if (w <= 0 || h <= 0) throw new IllegalArgumentException("width and height must be > 0");
    Matrix m = new Matrix();
    m.setScale(w / (float) src.width, h / (float) src.height);
    return createBitmap(src, 0, 0, src.width, src.height, m, filter);
  }

  public Bitmap copy(Config c, boolean mutable) {
    checkRecycled("copy()");
    if (c == Config.HARDWARE && mutable) throw new IllegalArgumentException("Hardware bitmaps are always immutable");
    JSObject j = c == Config.ALPHA_8 ? alphaJS(img, width, height, 0, 0)
        : c == Config.RGB_565 && hasAlpha ? opaqueJS(img, width, height)
        : transformedJS(img, null, 0, 0, width, height, width, height, false);
    Bitmap b = new Bitmap(j, width, height, mutable);
    b.config = c; b.density = density; b.premultiplied = premultiplied; b.colorSpace = c == Config.ALPHA_8 ? null : colorSpace;
    b.hasAlpha = c != Config.RGB_565 && hasAlpha;
    return b;
  }
  /** an immutable bitmap with these pixels (this one when already immutable) */
  public Bitmap asShared() { return mutable ? copy(config, false) : this; }
  public final int getWidth() { return width; }
  public final int getHeight() { return height; }
  public int getScaledWidth(int target) { return scale(width, density, target); }
  public int getScaledHeight(int target) { return scale(height, density, target); }
  public int getScaledWidth(Canvas c) { return scale(width, density, c.getDensity()); }
  public int getScaledHeight(Canvas c) { return scale(height, density, c.getDensity()); }
  public int getScaledWidth(android.util.DisplayMetrics m) { return scale(width, density, m.densityDpi); }
  public int getScaledHeight(android.util.DisplayMetrics m) { return scale(height, density, m.densityDpi); }
  static int scale(int size, int sdensity, int tdensity) {
    if (sdensity == DENSITY_NONE || tdensity == DENSITY_NONE || sdensity == tdensity) return size;
    return (size * tdensity + (sdensity >> 1)) / sdensity;
  }
  public int getDensity() { return density; }
  public void setDensity(int d) { density = d; }
  public final Config getConfig() { return config; }
  public final boolean isMutable() { return mutable; }
  public final boolean isRecycled() { return recycled; }
  public void recycle() { recycled = true; }
  public final boolean hasAlpha() { return hasAlpha; }
  public void setHasAlpha(boolean b) { hasAlpha = b; }
  public final boolean isPremultiplied() { return premultiplied && config != Config.RGB_565 && hasAlpha; }
  public final void setPremultiplied(boolean p) { premultiplied = p; }
  public final boolean hasMipMap() { return mipMap; }
  public final void setHasMipMap(boolean m) { mipMap = m; }
  public final int getRowBytes() { return width * bytesPerPixel(config); }
  public final int getByteCount() { return getRowBytes() * height; }
  public final int getAllocationByteCount() { return Math.max(allocation, getByteCount()); }
  public int getGenerationId() { return System.identityHashCode(this); }
  public void prepareToDraw() {}
  public byte[] getNinePatchChunk() { return ninePatchChunk; }
  /** (not part of the API) the nine-patch's optical insets, or null */
  public int[] a2wOpticalInsets() { return npLb; }
  /** (not part of the API) the nine-patch's outline insets, radius bits and alpha, or null */
  public int[] a2wOutlineInsets() { return npOl; }
  public ColorSpace getColorSpace() {
    if (config == Config.ALPHA_8) return null;
    if (colorSpace != null) return colorSpace;
    return ColorSpace.get(config == Config.RGBA_F16 ? ColorSpace.Named.LINEAR_EXTENDED_SRGB : ColorSpace.Named.SRGB);
  }
  public void setColorSpace(ColorSpace cs) {
    if (cs == null) throw new IllegalArgumentException("The colorSpace cannot be set to null");
    if (config == Config.ALPHA_8) throw new IllegalArgumentException("Cannot set a ColorSpace on ALPHA_8");
    if (cs.getModel() != ColorSpace.Model.RGB) throw new IllegalArgumentException("Color space must use the RGB color model");
    colorSpace = cs;
  }
  public boolean hasGainmap() { return gainmap != null; }
  public Gainmap getGainmap() { return gainmap; }
  public void setGainmap(Gainmap g) { gainmap = g; }

  /** a mutable bitmap's new size and config within its allocation (the pixels are cleared) */
  public void reconfigure(int w, int h, Config c) {
    checkRecycled("reconfigure()");
    if (w <= 0 || h <= 0) throw new IllegalArgumentException("width and height must be > 0");
    if (!mutable) throw new IllegalStateException("only mutable bitmaps may be reconfigured");
    if (c == Config.HARDWARE) throw new IllegalArgumentException("Bitmap not large enough to support new configuration");
    long need = (long) w * h * bytesPerPixel(c);
    if (need > getAllocationByteCount()) throw new IllegalArgumentException("Bitmap not large enough to support new configuration");
    allocation = getAllocationByteCount();
    ctx();
    resizeJS(img, w, h);
    width = w; height = h; config = c;
    if (c == Config.RGB_565) { hasAlpha = false; eraseJS(ctx, 0xff000000); }
    tintImg = null;
  }
  public void setWidth(int w) { reconfigure(w, height, config); }
  public void setHeight(int h) { reconfigure(width, h, config); }
  public void setConfig(Config c) { reconfigure(width, height, c); }

  public void eraseColor(int c) {
    checkRecycled("eraseColor()");
    if (!mutable) throw new IllegalStateException("cannot erase immutable bitmaps");
    eraseJS(ctx(), config == Config.RGB_565 ? c | 0xff000000 : config == Config.ALPHA_8 ? c & 0xff000000 : c);
  }
  public void eraseColor(long color) { eraseColor(Color.toArgb(color)); }
  public int getPixel(int x, int y) {
    checkRecycled("getPixel()");
    if (x < 0 || x >= width) throw new IllegalArgumentException("x must be < bitmap.width()");
    if (y < 0 || y >= height) throw new IllegalArgumentException("y must be < bitmap.height()");
    return getPixelsJS(img, x, y, 1, 1).get(0);
  }
  public Color getColor(int x, int y) { return Color.valueOf(getPixel(x, y)); }
  public void getPixels(int[] px, int off, int stride, int x, int y, int w, int h) {
    checkRecycled("getPixels()");
    if (w == 0 || h == 0) return;
    int[] r = getPixelsJS(img, x, y, w, h).copyToJavaArray();
    for (int row = 0; row < h; row++) System.arraycopy(r, row * w, px, off + row * stride, w);
  }
  public void setPixel(int x, int y, int c) {
    checkRecycled("setPixel()");
    if (!mutable) throw new IllegalStateException();
    if (x < 0 || x >= width || y < 0 || y >= height) throw new IllegalArgumentException("x and y must be within the bitmap");
    setPixelsJS(ctx(), Int32Array.copyFromJavaArray(new int[] {fit(c)}), x, y, 1, 1);
  }
  public void setPixels(int[] px, int off, int stride, int x, int y, int w, int h) {
    checkRecycled("setPixels()");
    if (!mutable) throw new IllegalStateException();
    if (w == 0 || h == 0) return;
    int[] r = new int[w * h];
    for (int row = 0; row < h; row++) System.arraycopy(px, off + row * stride, r, row * w, w);
    if (config == Config.RGB_565 || config == Config.ALPHA_8) for (int i = 0; i < r.length; i++) r[i] = fit(r[i]);
    setPixelsJS(ctx(), Int32Array.copyFromJavaArray(r), x, y, w, h);
  }
  /** a color as the config can hold it */
  private int fit(int c) { return config == Config.RGB_565 ? c | 0xff000000 : config == Config.ALPHA_8 ? c & 0xff000000 : c; }
  /** premultiplied RGBA bytes, like Skia's ARGB_8888 in memory */
  public byte[] rgba() { return Host.bytes(rgbaJS(img, width, height)); }

  // ---- pixel buffers in the config's memory layout (little-endian)
  /** this bitmap's pixels as the bytes Android keeps for its config */
  byte[] rawPixels() {
    int[] px = getPixelsJS(img, 0, 0, width, height).copyToJavaArray();
    int bpp = bytesPerPixel(config);
    byte[] out = new byte[px.length * bpp];
    boolean pm = isPremultiplied();
    for (int i = 0, o = 0; i < px.length; i++, o += bpp) {
      int c = px[i], a = c >>> 24, r = (c >> 16) & 255, g = (c >> 8) & 255, b = c & 255;
      if (pm && a != 255) { r = (r * a + 127) / 255; g = (g * a + 127) / 255; b = (b * a + 127) / 255; }
      switch (config) {
        case ALPHA_8: out[o] = (byte) a; break;
        case RGB_565: { int v = (r >> 3) << 11 | (g >> 2) << 5 | (b >> 3); out[o] = (byte) v; out[o + 1] = (byte) (v >> 8); break; }
        case ARGB_4444: { int v = (r >> 4) << 12 | (g >> 4) << 8 | (b >> 4) << 4 | (a >> 4); out[o] = (byte) v; out[o + 1] = (byte) (v >> 8); break; }
        case RGBA_F16: {
          float fa = a / 255f;
          float[] ch = {lin(r, a), lin(g, a), lin(b, a), fa};
          for (int k = 0; k < 4; k++) { short h = libcore.util.FP16.toHalf(ch[k]); out[o + 2 * k] = (byte) h; out[o + 2 * k + 1] = (byte) (h >> 8); }
          break;
        }
        case RGBA_1010102: {
          int v = (r * 1023 + 127) / 255 | ((g * 1023 + 127) / 255) << 10 | ((b * 1023 + 127) / 255) << 20 | ((a * 3 + 127) / 255) << 30;
          out[o] = (byte) v; out[o + 1] = (byte) (v >> 8); out[o + 2] = (byte) (v >> 16); out[o + 3] = (byte) (v >> 24);
          break;
        }
        default: out[o] = (byte) r; out[o + 1] = (byte) g; out[o + 2] = (byte) b; out[o + 3] = (byte) a;
      }
    }
    return out;
  }
  /** F16 is linear: c is the (possibly premultiplied) sRGB value, a its alpha */
  private float lin(int c, int a) {
    boolean pm = isPremultiplied();
    float s = pm ? (a == 0 ? 0 : c / (float) a) : c / 255f;
    float l = s <= 0.04045f ? s / 12.92f : (float) Math.pow((s + 0.055f) / 1.055f, 2.4f);
    return pm ? l * a / 255f : l;
  }
  private static int srgb8(float l) {
    float s = l <= 0.0031308f ? l * 12.92f : (float) (1.055f * Math.pow(l, 1 / 2.4f) - 0.055f);
    return Math.max(0, Math.min(255, Math.round(s * 255)));
  }
  void setRawPixels(byte[] in) {
    int bpp = bytesPerPixel(config), count = width * height;
    int[] px = new int[count];
    boolean pm = isPremultiplied();
    for (int i = 0, o = 0; i < count; i++, o += bpp) {
      int a = 255, r, g, b;
      switch (config) {
        case ALPHA_8: a = in[o] & 255; r = g = b = 0; break;
        case RGB_565: { int v = (in[o] & 255) | (in[o + 1] & 255) << 8; r = ((v >> 11) & 31) * 255 / 31; g = ((v >> 5) & 63) * 255 / 63; b = (v & 31) * 255 / 31; break; }
        case ARGB_4444: { int v = (in[o] & 255) | (in[o + 1] & 255) << 8; r = ((v >> 12) & 15) * 17; g = ((v >> 8) & 15) * 17; b = ((v >> 4) & 15) * 17; a = (v & 15) * 17; break; }
        case RGBA_F16: {
          float[] ch = new float[4];
          for (int k = 0; k < 4; k++) ch[k] = libcore.util.FP16.toFloat((short) ((in[o + 2 * k] & 255) | (in[o + 2 * k + 1] & 255) << 8));
          float fa = Math.max(0, Math.min(1, ch[3]));
          a = Math.round(fa * 255);
          for (int k = 0; k < 3; k++) { float l = pm ? (fa == 0 ? 0 : ch[k] / fa) : ch[k]; ch[k] = srgb8(l); }
          r = (int) ch[0]; g = (int) ch[1]; b = (int) ch[2];
          px[i] = a << 24 | r << 16 | g << 8 | b;
          continue;
        }
        case RGBA_1010102: {
          int v = (in[o] & 255) | (in[o + 1] & 255) << 8 | (in[o + 2] & 255) << 16 | (in[o + 3] & 255) << 24;
          r = (v & 1023) * 255 / 1023; g = ((v >> 10) & 1023) * 255 / 1023; b = ((v >> 20) & 1023) * 255 / 1023; a = ((v >>> 30) & 3) * 85;
          break;
        }
        default: r = in[o] & 255; g = in[o + 1] & 255; b = in[o + 2] & 255; a = in[o + 3] & 255;
      }
      if (pm && a != 255 && config != Config.RGB_565 && config != Config.ALPHA_8) {
        if (a == 0) r = g = b = 0;
        else { r = Math.min(255, r * 255 / a); g = Math.min(255, g * 255 / a); b = Math.min(255, b * 255 / a); }
      }
      px[i] = a << 24 | r << 16 | g << 8 | b;
    }
    setPixelsJS(ctx(), Int32Array.copyFromJavaArray(px), 0, 0, width, height);
  }
  public void copyPixelsToBuffer(java.nio.Buffer dst) {
    checkRecycled("copyPixelsToBuffer()");
    byte[] b = rawPixels();
    int shift = dst instanceof java.nio.ByteBuffer ? 0 : dst instanceof java.nio.ShortBuffer ? 1 : dst instanceof java.nio.IntBuffer ? 2 : -1;
    if (shift < 0) throw new RuntimeException("unsupported Buffer subclass");
    if (((long) dst.remaining() << shift) < b.length) throw new RuntimeException("Buffer not large enough for pixels");
    if (shift == 0) ((java.nio.ByteBuffer) dst).put(b);
    else if (shift == 1) { java.nio.ShortBuffer sb = (java.nio.ShortBuffer) dst; for (int i = 0; i + 1 < b.length; i += 2) sb.put((short) ((b[i] & 0xff) | (b[i + 1] & 0xff) << 8)); }
    else { java.nio.IntBuffer ib = (java.nio.IntBuffer) dst; for (int i = 0; i + 3 < b.length; i += 4) ib.put((b[i] & 0xff) | (b[i + 1] & 0xff) << 8 | (b[i + 2] & 0xff) << 16 | (b[i + 3] & 0xff) << 24); }
  }
  public void copyPixelsFromBuffer(java.nio.Buffer src) {
    checkRecycled("copyPixelsFromBuffer()");
    int shift = src instanceof java.nio.ByteBuffer ? 0 : src instanceof java.nio.ShortBuffer ? 1 : src instanceof java.nio.IntBuffer ? 2 : -1;
    if (shift < 0) throw new RuntimeException("unsupported Buffer subclass");
    int need = getByteCount();
    if (((long) src.remaining() << shift) < need) throw new RuntimeException("Buffer not large enough for pixels");
    byte[] b = new byte[need];
    if (shift == 0) ((java.nio.ByteBuffer) src).get(b);
    else if (shift == 1) { java.nio.ShortBuffer sb = (java.nio.ShortBuffer) src; for (int i = 0; i + 1 < need; i += 2) { short v = sb.get(); b[i] = (byte) v; b[i + 1] = (byte) (v >> 8); } }
    else { java.nio.IntBuffer ib = (java.nio.IntBuffer) src; for (int i = 0; i + 3 < need; i += 4) { int v = ib.get(); b[i] = (byte) v; b[i + 1] = (byte) (v >> 8); b[i + 2] = (byte) (v >> 16); b[i + 3] = (byte) (v >> 24); } }
    setRawPixels(b);
    tintImg = null;
  }

  /** PNG, JPEG or WebP through the browser's encoder (WebP falls back to PNG where the browser can't encode it) */
  public boolean compress(CompressFormat f, int q, java.io.OutputStream out) {
    checkRecycled("compress()");
    if (out == null) throw new NullPointerException();
    if (q < 0 || q > 100) throw new IllegalArgumentException("quality must be 0..100");
    String type = f == CompressFormat.JPEG ? "image/jpeg" : f == CompressFormat.PNG ? "image/png" : "image/webp";
    double quality = f == CompressFormat.WEBP_LOSSLESS ? 1.0 : q / 100.0;
    Int8Array data = encodeJS(img, width, height, type, quality);
    if (data == null) return false;
    try {
      out.write(Host.bytes(data));
      out.flush();
      return true;
    } catch (java.io.IOException e) {
      return false;
    }
  }
  /** a new ALPHA_8 bitmap holding this bitmap's alpha */
  public Bitmap extractAlpha() { return extractAlpha(null, null); }
  /** the alpha, blurred by the paint's BlurMaskFilter; offsetXY gets where the result sits relative to this bitmap */
  public Bitmap extractAlpha(Paint paint, int[] offsetXY) {
    checkRecycled("extractAlpha()");
    float radius = paint != null && paint.getMaskFilter() instanceof BlurMaskFilter ? ((BlurMaskFilter) paint.getMaskFilter()).radius : 0;
    double sigma = radius > 0 ? radius * 0.57735 + 0.5 : 0;
    int pad = sigma > 0 ? (int) Math.ceil(3 * sigma) : 0;
    Bitmap b = new Bitmap(alphaJS(img, width, height, sigma, pad), width + 2 * pad, height + 2 * pad, true);
    b.config = Config.ALPHA_8; b.density = density;
    if (offsetXY != null) { offsetXY[0] = -pad; offsetXY[1] = -pad; }
    return b;
  }
  public boolean sameAs(Bitmap o) {
    checkRecycled("sameAs()");
    if (this == o) return true;
    if (o == null) return false;
    if (o.recycled) throw new IllegalArgumentException("Can't compare to a recycled bitmap!");
    if (width != o.width || height != o.height || config != o.config) return false;
    int[] a = getPixelsJS(img, 0, 0, width, height).copyToJavaArray(), b = getPixelsJS(o.img, 0, 0, width, height).copyToJavaArray();
    return java.util.Arrays.equals(a, b);
  }
  public int describeContents() { return 0; }
  public void writeToParcel(android.os.Parcel p, int f) {
    checkRecycled("writeToParcel()");
    p.writeInt(width); p.writeInt(height); p.writeInt(config.ordinal()); p.writeInt(density);
    p.writeInt((mutable ? 1 : 0) | (hasAlpha ? 2 : 0) | (premultiplied ? 4 : 0) | (mipMap ? 8 : 0));
    int[] px = new int[width * height];
    getPixels(px, 0, width, 0, 0, width, height);
    p.writeIntArray(px);
  }
  public static final android.os.Parcelable.Creator<Bitmap> CREATOR = new android.os.Parcelable.Creator<Bitmap>() {
    public Bitmap createFromParcel(android.os.Parcel p) {
      int w = p.readInt(), h = p.readInt();
      Config c = Config.values()[p.readInt()];
      int d = p.readInt(), flags = p.readInt();
      int[] px = p.createIntArray();
      Bitmap b = createBitmap(null, w, h, c == Config.HARDWARE ? Config.ARGB_8888 : c, true, null);
      b.setPixels(px, 0, w, 0, 0, w, h);
      b.config = c; b.density = d;
      b.mutable = (flags & 1) != 0; b.hasAlpha = (flags & 2) != 0; b.premultiplied = (flags & 4) != 0; b.mipMap = (flags & 8) != 0;
      return b;
    }
    public Bitmap[] newArray(int size) { return new Bitmap[size]; }
  };
  public String toString() { return "Bitmap(" + width + "x" + height + ")"; }
}
