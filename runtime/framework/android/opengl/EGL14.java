package android.opengl;

import javax.microedition.khronos.egl.EGL10;

/**
 * android.opengl.EGL14 over the page's EGL (javax.microedition.khronos.egl): one display, its configurations, and the
 * page's WebGL context. Objects are handles to the EGL10 objects.
 */
public class EGL14 {
  public EGL14() {}
  public static final int EGL_ALPHA_MASK_SIZE = 12350;
  public static final int EGL_ALPHA_SIZE = 12321;
  public static final int EGL_BACK_BUFFER = 12420;
  public static final int EGL_BAD_ACCESS = 12290;
  public static final int EGL_BAD_ALLOC = 12291;
  public static final int EGL_BAD_ATTRIBUTE = 12292;
  public static final int EGL_BAD_CONFIG = 12293;
  public static final int EGL_BAD_CONTEXT = 12294;
  public static final int EGL_BAD_CURRENT_SURFACE = 12295;
  public static final int EGL_BAD_DISPLAY = 12296;
  public static final int EGL_BAD_MATCH = 12297;
  public static final int EGL_BAD_NATIVE_PIXMAP = 12298;
  public static final int EGL_BAD_NATIVE_WINDOW = 12299;
  public static final int EGL_BAD_PARAMETER = 12300;
  public static final int EGL_BAD_SURFACE = 12301;
  public static final int EGL_BIND_TO_TEXTURE_RGB = 12345;
  public static final int EGL_BIND_TO_TEXTURE_RGBA = 12346;
  public static final int EGL_BLUE_SIZE = 12322;
  public static final int EGL_BUFFER_DESTROYED = 12437;
  public static final int EGL_BUFFER_PRESERVED = 12436;
  public static final int EGL_BUFFER_SIZE = 12320;
  public static final int EGL_CLIENT_APIS = 12429;
  public static final int EGL_COLOR_BUFFER_TYPE = 12351;
  public static final int EGL_CONFIG_CAVEAT = 12327;
  public static final int EGL_CONFIG_ID = 12328;
  public static final int EGL_CONFORMANT = 12354;
  public static final int EGL_CONTEXT_CLIENT_TYPE = 12439;
  public static final int EGL_CONTEXT_CLIENT_VERSION = 12440;
  public static final int EGL_CONTEXT_LOST = 12302;
  public static final int EGL_CORE_NATIVE_ENGINE = 12379;
  public static final int EGL_DEFAULT_DISPLAY = 0;
  public static final int EGL_DEPTH_SIZE = 12325;
  public static final int EGL_DISPLAY_SCALING = 10000;
  public static final int EGL_DRAW = 12377;
  public static final int EGL_EXTENSIONS = 12373;
  public static final int EGL_FALSE = 0;
  public static final int EGL_GREEN_SIZE = 12323;
  public static final int EGL_HEIGHT = 12374;
  public static final int EGL_HORIZONTAL_RESOLUTION = 12432;
  public static final int EGL_LARGEST_PBUFFER = 12376;
  public static final int EGL_LEVEL = 12329;
  public static final int EGL_LUMINANCE_BUFFER = 12431;
  public static final int EGL_LUMINANCE_SIZE = 12349;
  public static final int EGL_MATCH_NATIVE_PIXMAP = 12353;
  public static final int EGL_MAX_PBUFFER_HEIGHT = 12330;
  public static final int EGL_MAX_PBUFFER_PIXELS = 12331;
  public static final int EGL_MAX_PBUFFER_WIDTH = 12332;
  public static final int EGL_MAX_SWAP_INTERVAL = 12348;
  public static final int EGL_MIN_SWAP_INTERVAL = 12347;
  public static final int EGL_MIPMAP_LEVEL = 12419;
  public static final int EGL_MIPMAP_TEXTURE = 12418;
  public static final int EGL_MULTISAMPLE_RESOLVE = 12441;
  public static final int EGL_MULTISAMPLE_RESOLVE_BOX = 12443;
  public static final int EGL_MULTISAMPLE_RESOLVE_BOX_BIT = 512;
  public static final int EGL_MULTISAMPLE_RESOLVE_DEFAULT = 12442;
  public static final int EGL_NATIVE_RENDERABLE = 12333;
  public static final int EGL_NATIVE_VISUAL_ID = 12334;
  public static final int EGL_NATIVE_VISUAL_TYPE = 12335;
  public static final int EGL_NONE = 12344;
  public static final int EGL_NON_CONFORMANT_CONFIG = 12369;
  public static final int EGL_NOT_INITIALIZED = 12289;
  public static final int EGL_NO_TEXTURE = 12380;
  public static final int EGL_OPENGL_API = 12450;
  public static final int EGL_OPENGL_BIT = 8;
  public static final int EGL_OPENGL_ES2_BIT = 4;
  public static final int EGL_OPENGL_ES_API = 12448;
  public static final int EGL_OPENGL_ES_BIT = 1;
  public static final int EGL_OPENVG_API = 12449;
  public static final int EGL_OPENVG_BIT = 2;
  public static final int EGL_OPENVG_IMAGE = 12438;
  public static final int EGL_PBUFFER_BIT = 1;
  public static final int EGL_PIXEL_ASPECT_RATIO = 12434;
  public static final int EGL_PIXMAP_BIT = 2;
  public static final int EGL_READ = 12378;
  public static final int EGL_RED_SIZE = 12324;
  public static final int EGL_RENDERABLE_TYPE = 12352;
  public static final int EGL_RENDER_BUFFER = 12422;
  public static final int EGL_RGB_BUFFER = 12430;
  public static final int EGL_SAMPLES = 12337;
  public static final int EGL_SAMPLE_BUFFERS = 12338;
  public static final int EGL_SINGLE_BUFFER = 12421;
  public static final int EGL_SLOW_CONFIG = 12368;
  public static final int EGL_STENCIL_SIZE = 12326;
  public static final int EGL_SUCCESS = 12288;
  public static final int EGL_SURFACE_TYPE = 12339;
  public static final int EGL_SWAP_BEHAVIOR = 12435;
  public static final int EGL_SWAP_BEHAVIOR_PRESERVED_BIT = 1024;
  public static final int EGL_TEXTURE_2D = 12383;
  public static final int EGL_TEXTURE_FORMAT = 12416;
  public static final int EGL_TEXTURE_RGB = 12381;
  public static final int EGL_TEXTURE_RGBA = 12382;
  public static final int EGL_TEXTURE_TARGET = 12417;
  public static final int EGL_TRANSPARENT_BLUE_VALUE = 12341;
  public static final int EGL_TRANSPARENT_GREEN_VALUE = 12342;
  public static final int EGL_TRANSPARENT_RED_VALUE = 12343;
  public static final int EGL_TRANSPARENT_RGB = 12370;
  public static final int EGL_TRANSPARENT_TYPE = 12340;
  public static final int EGL_TRUE = 1;
  public static final int EGL_VENDOR = 12371;
  public static final int EGL_VERSION = 12372;
  public static final int EGL_VERTICAL_RESOLUTION = 12433;
  public static final int EGL_VG_ALPHA_FORMAT = 12424;
  public static final int EGL_VG_ALPHA_FORMAT_NONPRE = 12427;
  public static final int EGL_VG_ALPHA_FORMAT_PRE = 12428;
  public static final int EGL_VG_ALPHA_FORMAT_PRE_BIT = 64;
  public static final int EGL_VG_COLORSPACE = 12423;
  public static final int EGL_VG_COLORSPACE_LINEAR = 12426;
  public static final int EGL_VG_COLORSPACE_LINEAR_BIT = 32;
  public static final int EGL_VG_COLORSPACE_sRGB = 12425;
  public static final int EGL_WIDTH = 12375;
  public static final int EGL_WINDOW_BIT = 4;
  public static EGLContext EGL_NO_CONTEXT = new EGLContext(0);
  public static EGLDisplay EGL_NO_DISPLAY = new EGLDisplay(0);
  public static EGLSurface EGL_NO_SURFACE = new EGLSurface(0);

  private static final java.util.IdentityHashMap<Object, Long> handles = new java.util.IdentityHashMap<>();
  private static final java.util.HashMap<Long, Object> objects = new java.util.HashMap<>();
  private static long nextHandle = 1;
  private static EGL10 egl() { return (EGL10) javax.microedition.khronos.egl.EGLContext.getEGL(); }
  private static long handle(Object o) {
    Long h = handles.get(o);
    if (h == null) { h = nextHandle++; handles.put(o, h); objects.put(h, o); }
    return h;
  }
  private static EGLDisplay wrap(javax.microedition.khronos.egl.EGLDisplay d) { return d == null || d == EGL10.EGL_NO_DISPLAY ? EGL_NO_DISPLAY : new EGLDisplay(handle(d)); }
  private static EGLConfig wrap(javax.microedition.khronos.egl.EGLConfig c) { return c == null ? null : new EGLConfig(handle(c)); }
  private static EGLContext wrap(javax.microedition.khronos.egl.EGLContext c) { return c == null || c == EGL10.EGL_NO_CONTEXT ? EGL_NO_CONTEXT : new EGLContext(handle(c)); }
  private static EGLSurface wrap(javax.microedition.khronos.egl.EGLSurface s) { return s == null || s == EGL10.EGL_NO_SURFACE ? EGL_NO_SURFACE : new EGLSurface(handle(s)); }
  private static javax.microedition.khronos.egl.EGLDisplay d(EGLDisplay d) { Object o = d == null ? null : objects.get(d.getNativeHandle()); return o instanceof javax.microedition.khronos.egl.EGLDisplay ? (javax.microedition.khronos.egl.EGLDisplay) o : EGL10.EGL_NO_DISPLAY; }
  private static javax.microedition.khronos.egl.EGLConfig c(EGLConfig c) { Object o = c == null ? null : objects.get(c.getNativeHandle()); return o instanceof javax.microedition.khronos.egl.EGLConfig ? (javax.microedition.khronos.egl.EGLConfig) o : null; }
  private static javax.microedition.khronos.egl.EGLContext x(EGLContext c) { Object o = c == null ? null : objects.get(c.getNativeHandle()); return o instanceof javax.microedition.khronos.egl.EGLContext ? (javax.microedition.khronos.egl.EGLContext) o : EGL10.EGL_NO_CONTEXT; }
  private static javax.microedition.khronos.egl.EGLSurface s(EGLSurface s) { Object o = s == null ? null : objects.get(s.getNativeHandle()); return o instanceof javax.microedition.khronos.egl.EGLSurface ? (javax.microedition.khronos.egl.EGLSurface) o : EGL10.EGL_NO_SURFACE; }
  private static int[] attribs(int[] list, int offset) {
    if (list == null) return null;
    if (offset < 0 || offset > list.length) throw new IllegalArgumentException("offset < 0 || offset > attrib_list.length");
    return java.util.Arrays.copyOfRange(list, offset, list.length);
  }
  private static void check(int[] a, int offset, String name) {
    if (a == null) throw new IllegalArgumentException(name + " == null");
    if (offset < 0 || offset >= a.length) throw new IllegalArgumentException(name + "Offset < 0 || " + name + "Offset >= " + name + ".length");
  }

  public static int eglGetError() { return egl().eglGetError(); }
  public static EGLDisplay eglGetDisplay(int display_id) { return wrap(egl().eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY)); }
  public static boolean eglInitialize(EGLDisplay dpy, int[] major, int majorOffset, int[] minor, int minorOffset) {
    int[] v = new int[2];
    boolean r = egl().eglInitialize(d(dpy), v);
    if (r) {
      if (major != null) { check(major, majorOffset, "major"); major[majorOffset] = 1; }
      if (minor != null) { check(minor, minorOffset, "minor"); minor[minorOffset] = 4; }
    }
    return r;
  }
  public static boolean eglTerminate(EGLDisplay dpy) { return egl().eglTerminate(d(dpy)); }
  public static String eglQueryString(EGLDisplay dpy, int name) {
    if (name == EGL_VERSION) return "1.4 apk2web";
    if (name == EGL_CLIENT_APIS) return "OpenGL_ES";
    return egl().eglQueryString(d(dpy), name);
  }
  public static boolean eglGetConfigs(EGLDisplay dpy, EGLConfig[] configs, int configsOffset, int config_size, int[] num_config, int num_configOffset) {
    check(num_config, num_configOffset, "num_config");
    javax.microedition.khronos.egl.EGLConfig[] a = configs == null ? null : new javax.microedition.khronos.egl.EGLConfig[config_size];
    int[] n = new int[1];
    boolean r = egl().eglGetConfigs(d(dpy), a, config_size, n);
    num_config[num_configOffset] = n[0];
    if (r && a != null) for (int i = 0; i < n[0] && configsOffset + i < configs.length; i++) configs[configsOffset + i] = wrap(a[i]);
    return r;
  }
  public static boolean eglChooseConfig(EGLDisplay dpy, int[] attrib_list, int attrib_listOffset, EGLConfig[] configs, int configsOffset, int config_size, int[] num_config, int num_configOffset) {
    check(num_config, num_configOffset, "num_config");
    javax.microedition.khronos.egl.EGLConfig[] a = configs == null ? null : new javax.microedition.khronos.egl.EGLConfig[config_size];
    int[] n = new int[1];
    boolean r = egl().eglChooseConfig(d(dpy), attribs(attrib_list, attrib_listOffset), a, config_size, n);
    num_config[num_configOffset] = n[0];
    if (r && a != null) for (int i = 0; i < n[0] && configsOffset + i < configs.length; i++) configs[configsOffset + i] = wrap(a[i]);
    return r;
  }
  public static boolean eglGetConfigAttrib(EGLDisplay dpy, EGLConfig config, int attribute, int[] value, int offset) {
    check(value, offset, "value");
    int[] v = new int[1];
    boolean r = egl().eglGetConfigAttrib(d(dpy), c(config), attribute, v);
    if (attribute == EGL_RENDERABLE_TYPE) v[0] |= EGL_OPENGL_ES2_BIT | 0x40;   // EGL_OPENGL_ES3_BIT_KHR: WebGL 2
    value[offset] = v[0];
    return r;
  }
  public static EGLSurface eglCreateWindowSurface(EGLDisplay dpy, EGLConfig config, Object win, int[] attrib_list, int offset) {
    if (!(win instanceof android.view.Surface) && !(win instanceof android.view.SurfaceView) && !(win instanceof android.view.SurfaceHolder) && !(win instanceof android.graphics.SurfaceTexture))
      throw new UnsupportedOperationException("eglCreateWindowSurface() can only be called with an instance of Surface, SurfaceView, SurfaceTexture or SurfaceHolder at the moment, this will be fixed later.");
    return wrap(egl().eglCreateWindowSurface(d(dpy), c(config), win, attribs(attrib_list, offset)));
  }
  public static EGLSurface eglCreatePbufferSurface(EGLDisplay dpy, EGLConfig config, int[] attrib_list, int offset) {
    return wrap(egl().eglCreatePbufferSurface(d(dpy), c(config), attribs(attrib_list, offset)));
  }
  /** as on Android: pixmap surfaces are not supported */
  public static EGLSurface eglCreatePixmapSurface(EGLDisplay dpy, EGLConfig config, int pixmap, int[] attrib_list, int offset) {
    throw new UnsupportedOperationException("eglCreatePixmapSurface");
  }
  public static boolean eglDestroySurface(EGLDisplay dpy, EGLSurface surface) { return egl().eglDestroySurface(d(dpy), s(surface)); }
  public static boolean eglQuerySurface(EGLDisplay dpy, EGLSurface surface, int attribute, int[] value, int offset) {
    check(value, offset, "value");
    int[] v = new int[1];
    boolean r = egl().eglQuerySurface(d(dpy), s(surface), attribute, v);
    value[offset] = v[0];
    return r;
  }
  public static boolean eglBindAPI(int api) { return api == EGL_OPENGL_ES_API; }
  public static int eglQueryAPI() { return EGL_OPENGL_ES_API; }
  public static boolean eglWaitClient() { return egl().eglWaitGL(); }
  public static boolean eglReleaseThread() { return true; }
  public static boolean eglSurfaceAttrib(EGLDisplay dpy, EGLSurface surface, int attribute, int value) { return s(surface) != EGL10.EGL_NO_SURFACE; }
  /** the page shows frames at its own refresh rate */
  public static boolean eglSwapInterval(EGLDisplay dpy, int interval) { return d(dpy) != EGL10.EGL_NO_DISPLAY; }
  public static EGLContext eglCreateContext(EGLDisplay dpy, EGLConfig config, EGLContext share_context, int[] attrib_list, int offset) {
    int[] a = attribs(attrib_list, offset);
    if (a != null) for (int i = 0; i + 1 < a.length && a[i] != EGL_NONE; i += 2) if (a[i] == EGL_CONTEXT_CLIENT_VERSION) GLBridge.setClientVersion(a[i + 1]);
    return wrap(egl().eglCreateContext(d(dpy), c(config), x(share_context), a));
  }
  public static boolean eglDestroyContext(EGLDisplay dpy, EGLContext ctx) { return egl().eglDestroyContext(d(dpy), x(ctx)); }
  public static boolean eglMakeCurrent(EGLDisplay dpy, EGLSurface draw, EGLSurface read, EGLContext ctx) { return egl().eglMakeCurrent(d(dpy), s(draw), s(read), x(ctx)); }
  public static boolean eglQueryContext(EGLDisplay dpy, EGLContext ctx, int attribute, int[] value, int offset) {
    check(value, offset, "value");
    int[] v = new int[1];
    boolean r = egl().eglQueryContext(d(dpy), x(ctx), attribute, v);
    value[offset] = v[0];
    return r;
  }
  public static EGLContext eglGetCurrentContext() { return wrap(egl().eglGetCurrentContext()); }
  public static EGLDisplay eglGetCurrentDisplay() { return wrap(egl().eglGetCurrentDisplay()); }
  public static EGLSurface eglGetCurrentSurface(int readdraw) { return wrap(egl().eglGetCurrentSurface(readdraw)); }
  public static boolean eglWaitGL() { return egl().eglWaitGL(); }
  public static boolean eglWaitNative(int engine) { return egl().eglWaitNative(engine, null); }
  public static boolean eglSwapBuffers(EGLDisplay dpy, EGLSurface surface) { return egl().eglSwapBuffers(d(dpy), s(surface)); }
}
