package javax.microedition.khronos.egl;

/**
 * EGL over the page: one display, one RGBA8888 configuration with depth 24 and stencil 8 (OpenGL ES 1 and 2), and the
 * page's WebGL context, which is always current. Engines that check for ES 2 (libGDX) or drive GL themselves find
 * what a device reports.
 */
final class EGLImpl implements EGL11 {
  static final EGLDisplay NO_DISPLAY = new EGLDisplay() {}, DISPLAY = new EGLDisplay() {};
  static final EGLContext NO_CONTEXT = new Ctx(), CONTEXT = new Ctx();
  static final EGLSurface NO_SURFACE = new EGLSurface() {};
  /** the configurations a device lists (r, g, b, a, depth, stencil); all draw into the page's RGBA canvas */
  static final class Config extends EGLConfig {
    final int[] v;
    Config(int... v) { this.v = v; }
  }
  static final Config[] CONFIGS = {
    new Config(8, 8, 8, 8, 24, 8), new Config(8, 8, 8, 8, 16, 0), new Config(8, 8, 8, 8, 0, 0), new Config(8, 8, 8, 0, 24, 8),
    new Config(8, 8, 8, 0, 16, 0), new Config(5, 6, 5, 0, 24, 8), new Config(5, 6, 5, 0, 16, 0), new Config(5, 6, 5, 0, 0, 0),
  };
  static final EGLConfig CONFIG = CONFIGS[0];
  static final class Ctx extends EGLContext { public javax.microedition.khronos.opengles.GL getGL() { return android.opengl.GLSurfaceView.gl(); } }
  /** a window surface: while its view is shown the page shows the GL canvas (an app's own GL thread over EGL) */
  static final class Surf extends EGLSurface implements a2w.Windows.FrameClient {
    int w, h;
    android.view.View view;
    @Override public boolean onFrame(long now) { return false; }
    @Override public boolean isShown() { return view != null && view.isShown() && view.getWidth() > 0 && view.getHeight() > 0; }
  }
  static final EGLImpl INSTANCE = new EGLImpl();   // (after the objects its fields start with)
  EGLContext current = NO_CONTEXT;
  EGLSurface draw = NO_SURFACE, read = NO_SURFACE;
  int error = EGL_SUCCESS;

  static int attr(EGLConfig config, int a) {
    int[] v = (config instanceof Config ? (Config) config : CONFIGS[0]).v;
    switch (a) {
      case EGL_RED_SIZE: return v[0];
      case EGL_GREEN_SIZE: return v[1];
      case EGL_BLUE_SIZE: return v[2];
      case EGL_ALPHA_SIZE: return v[3];
      case EGL_BUFFER_SIZE: return v[0] + v[1] + v[2] + v[3];
      case EGL_DEPTH_SIZE: return v[4];
      case EGL_STENCIL_SIZE: return v[5];
      case EGL_NATIVE_VISUAL_ID: return v[0] == 5 ? 4 : v[3] == 0 ? 2 : 1;   // PixelFormat RGB_565 / RGBX_8888 / RGBA_8888
      case EGL_RENDERABLE_TYPE: return 1 | 4;   // EGL_OPENGL_ES_BIT | EGL_OPENGL_ES2_BIT
      case EGL_SURFACE_TYPE: return EGL_WINDOW_BIT | EGL_PBUFFER_BIT;
      case EGL_CONFIG_ID: { for (int i = 0; i < CONFIGS.length; i++) if (CONFIGS[i].v == v) return i + 1; return 1; }
      case EGL_CONFIG_CAVEAT: return EGL_NONE;
      case EGL_COLOR_BUFFER_TYPE: return EGL_RGB_BUFFER;
      case EGL_NATIVE_RENDERABLE: return 1;
      case 0x3042: return 1 | 4;   // EGL_CONFORMANT
      case EGL_MAX_PBUFFER_WIDTH: case EGL_MAX_PBUFFER_HEIGHT: return 4096;
      default: return 0;
    }
  }
  /** does the configuration meet the attribute list (minimum sizes, required bits)? */
  static boolean matches(EGLConfig config, int[] attribs) {
    if (attribs == null) return true;
    for (int i = 0; i + 1 < attribs.length && attribs[i] != EGL_NONE; i += 2) {
      int a = attribs[i], v = attribs[i + 1];
      if (v == EGL_DONT_CARE) continue;
      switch (a) {
        case EGL_RED_SIZE: case EGL_GREEN_SIZE: case EGL_BLUE_SIZE: case EGL_ALPHA_SIZE: case EGL_DEPTH_SIZE: case EGL_STENCIL_SIZE:
        case EGL_BUFFER_SIZE:
          if (v > attr(config, a)) return false; break;
        case EGL_SAMPLE_BUFFERS: case EGL_SAMPLES: if (v > 0) return false; break;
        case EGL_RENDERABLE_TYPE: case EGL_SURFACE_TYPE: if ((v & attr(config, a)) != v) return false; break;
        default: break;
      }
    }
    return true;
  }
  boolean ok() { error = EGL_SUCCESS; return true; }

  public boolean eglChooseConfig(EGLDisplay d, int[] attribs, EGLConfig[] configs, int size, int[] num) {
    int n = 0;
    for (Config c : CONFIGS) {
      if (!matches(c, attribs)) continue;
      if (configs != null && n < size) configs[n] = c;
      n++;
    }
    if (num != null && num.length > 0) num[0] = configs != null ? Math.min(n, size) : n;
    return ok();
  }
  public boolean eglGetConfigs(EGLDisplay d, EGLConfig[] configs, int size, int[] num) {
    int n = configs == null ? CONFIGS.length : Math.min(size, CONFIGS.length);
    for (int i = 0; configs != null && i < n; i++) configs[i] = CONFIGS[i];
    if (num != null && num.length > 0) num[0] = n;
    return ok();
  }
  public boolean eglGetConfigAttrib(EGLDisplay d, EGLConfig c, int a, int[] value) { value[0] = attr(c, a); return ok(); }
  public boolean eglCopyBuffers(EGLDisplay d, EGLSurface s, Object pixmap) { return ok(); }
  public EGLContext eglCreateContext(EGLDisplay d, EGLConfig c, EGLContext share, int[] attribs) { ok(); return CONTEXT; }
  public EGLSurface eglCreatePbufferSurface(EGLDisplay d, EGLConfig c, int[] attribs) {
    Surf s = new Surf();
    for (int i = 0; attribs != null && i + 1 < attribs.length && attribs[i] != EGL_NONE; i += 2) {
      if (attribs[i] == EGL_WIDTH) s.w = attribs[i + 1];
      if (attribs[i] == EGL_HEIGHT) s.h = attribs[i + 1];
    }
    ok(); return s;
  }
  public EGLSurface eglCreatePixmapSurface(EGLDisplay d, EGLConfig c, Object pixmap, int[] attribs) { error = EGL_BAD_NATIVE_PIXMAP; return NO_SURFACE; }
  public EGLSurface eglCreateWindowSurface(EGLDisplay d, EGLConfig c, Object window, int[] attribs) {
    Surf s = new Surf();
    int[] wh = a2w.Host.canvasSize();
    s.w = wh[0]; s.h = wh[1];
    s.view = android.view.SurfaceView.viewOf(window);
    if (s.view != null) {
      if (s.view.getWidth() > 0) { s.w = s.view.getWidth(); s.h = s.view.getHeight(); }
      android.view.View v = s.view;
      v.post(() -> a2w.Windows.addFrameClient(s));
    }
    ok(); return s;
  }
  public boolean eglDestroyContext(EGLDisplay d, EGLContext c) { if (current == c) current = NO_CONTEXT; return ok(); }
  public boolean eglDestroySurface(EGLDisplay d, EGLSurface s) {
    if (draw == s) draw = NO_SURFACE;
    if (read == s) read = NO_SURFACE;
    if (s instanceof Surf && ((Surf) s).view != null) { Surf f = (Surf) s; f.view.post(() -> a2w.Windows.removeFrameClient(f)); }
    return ok();
  }
  public EGLContext eglGetCurrentContext() { return current; }
  public EGLDisplay eglGetCurrentDisplay() { return current == NO_CONTEXT ? NO_DISPLAY : DISPLAY; }
  public EGLSurface eglGetCurrentSurface(int which) { return which == EGL_READ ? read : draw; }
  public EGLDisplay eglGetDisplay(Object display) { ok(); return DISPLAY; }
  public int eglGetError() { int e = error; error = EGL_SUCCESS; return e; }
  public boolean eglInitialize(EGLDisplay d, int[] version) {
    if (version != null && version.length >= 2) { version[0] = 1; version[1] = 4; }
    return ok();
  }
  public boolean eglMakeCurrent(EGLDisplay d, EGLSurface dr, EGLSurface rd, EGLContext c) { current = c; draw = dr; read = rd; return ok(); }
  public boolean eglQueryContext(EGLDisplay d, EGLContext c, int a, int[] value) {
    value[0] = a == 0x3098 ? 2 : a == EGL_CONFIG_ID ? 1 : 0;   // EGL_CONTEXT_CLIENT_VERSION
    return ok();
  }
  public String eglQueryString(EGLDisplay d, int name) {
    switch (name) {
      case EGL_VENDOR: return "apk2web";
      case EGL_VERSION: return "1.4 apk2web";
      case EGL_EXTENSIONS: return "EGL_KHR_image_base EGL_ANDROID_image_native_buffer";
      case 0x308D: return "OpenGL_ES";   // EGL_CLIENT_APIS
      default: return null;
    }
  }
  public boolean eglQuerySurface(EGLDisplay d, EGLSurface s, int a, int[] value) {
    int w = 0, h = 0;
    if (s instanceof Surf) { w = ((Surf) s).w; h = ((Surf) s).h; }
    if (s != null && !(s instanceof Surf) || w == 0) { int[] wh = a2w.Host.canvasSize(); w = wh[0]; h = wh[1]; }
    value[0] = a == EGL_WIDTH ? w : a == EGL_HEIGHT ? h : a == EGL_CONFIG_ID ? 1 : 0;
    return ok();
  }
  public boolean eglReleaseThread() { return ok(); }
  long lastSwap;
  /** the page shows what was drawn when the thread waits; a GL thread of the app's own waits for the next frame here,
   *  as eglSwapBuffers does on a device */
  public boolean eglSwapBuffers(EGLDisplay d, EGLSurface s) {
    if (android.os.Looper.myLooper() != android.os.Looper.getMainLooper()) {
      long now = android.os.SystemClock.uptimeMillis(), wait = lastSwap + 16 - now;
      lastSwap = Math.max(now, lastSwap + 16);
      try { Thread.sleep(wait > 0 ? wait : 1); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
    return ok();
  }
  public boolean eglTerminate(EGLDisplay d) { return ok(); }
  public boolean eglWaitGL() { return ok(); }
  public boolean eglWaitNative(int engine, Object bindTarget) { return ok(); }
}
