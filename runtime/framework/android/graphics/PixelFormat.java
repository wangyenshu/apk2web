package android.graphics;
public class PixelFormat {
  public static final int UNKNOWN = 0, TRANSLUCENT = -3, TRANSPARENT = -2, OPAQUE = -1, RGBA_8888 = 1, RGBX_8888 = 2, RGB_888 = 3, RGB_565 = 4, A_8 = 8;
  public int bitsPerPixel, bytesPerPixel;
  public static void getPixelFormatInfo(int f, PixelFormat info) { info.bitsPerPixel = 32; info.bytesPerPixel = 4; }
  public static boolean formatHasAlpha(int f) { return f != OPAQUE && f != RGBX_8888 && f != RGB_565 && f != RGB_888; }
}
