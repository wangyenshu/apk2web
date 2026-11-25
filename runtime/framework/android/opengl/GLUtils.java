package android.opengl;

import android.graphics.Bitmap;
import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;

/** Bitmap uploads straight from the page image (a canvas or ImageBitmap) to the texture. */
public final class GLUtils {
  private GLUtils() {}
  public static int getInternalFormat(Bitmap b) {
    if (b == null) throw new NullPointerException("getInternalFormat can't be used with a null Bitmap");
    Bitmap.Config c = b.getConfig();
    return c == Bitmap.Config.ALPHA_8 ? GLES20.GL_ALPHA : c == Bitmap.Config.RGB_565 ? GLES20.GL_RGB : GLES20.GL_RGBA;
  }
  public static int getType(Bitmap b) {
    if (b == null) throw new NullPointerException("getType can't be used with a null Bitmap");
    return b.getConfig() == Bitmap.Config.RGB_565 ? GLES20.GL_UNSIGNED_SHORT_5_6_5 : GLES20.GL_UNSIGNED_BYTE;
  }
  public static void texImage2D(int target, int level, int internalformat, Bitmap b, int type, int border) {
    if (b == null) throw new NullPointerException("texImage2D can't be used with a null Bitmap");
    upload(target, level, internalformat < 0 ? getInternalFormat(b) : internalformat, type < 0 ? GLES20.GL_UNSIGNED_BYTE : type, b.a2wImage());
  }
  public static void texImage2D(int target, int level, int internalformat, Bitmap b, int border) { texImage2D(target, level, internalformat, b, -1, border); }
  public static void texImage2D(int target, int level, Bitmap b, int border) { texImage2D(target, level, -1, b, -1, border); }
  public static void texSubImage2D(int target, int level, int x, int y, Bitmap b) { sub(target, level, x, y, b.a2wImage(), getInternalFormat(b), GLES20.GL_UNSIGNED_BYTE); }
  public static void texSubImage2D(int target, int level, int x, int y, Bitmap b, int format, int type) { sub(target, level, x, y, b.a2wImage(), format, type); }
  public static String getEGLErrorString(int error) {
    switch (error) {
      case 0x3000: return "EGL_SUCCESS";
      case 0x3001: return "EGL_NOT_INITIALIZED";
      case 0x3003: return "EGL_BAD_ALLOC";
      case 0x3005: return "EGL_BAD_CONFIG";
      case 0x3006: return "EGL_BAD_CONTEXT";
      case 0x300D: return "EGL_BAD_SURFACE";
      case 0x300E: return "EGL_CONTEXT_LOST";
      default: return "0x" + Integer.toHexString(error);
    }
  }
  @JSBody(params = {"t", "l", "f", "ty", "img"}, script = "HW.GLES.texImageSource(t, l, f, ty === 0x8363 ? 0x1401 : ty, img, true);")
  static native void upload(int target, int level, int format, int type, JSObject img);
  @JSBody(params = {"t", "l", "x", "y", "img", "f", "ty"}, script = "HW.GLES.texSubImageSource(t, l, x, y, img, f, ty === 0x8363 ? 0x1401 : ty);")
  static native void sub(int target, int level, int x, int y, JSObject img, int format, int type);
}
