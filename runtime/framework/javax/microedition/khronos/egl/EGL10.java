package javax.microedition.khronos.egl;

public interface EGL10 extends EGL {
  int EGL_SUCCESS = 0x3000, EGL_NOT_INITIALIZED = 0x3001, EGL_BAD_ACCESS = 0x3002, EGL_BAD_ALLOC = 0x3003, EGL_BAD_ATTRIBUTE = 0x3004,
      EGL_BAD_CONFIG = 0x3005, EGL_BAD_CONTEXT = 0x3006, EGL_BAD_CURRENT_SURFACE = 0x3007, EGL_BAD_DISPLAY = 0x3008, EGL_BAD_MATCH = 0x3009,
      EGL_BAD_NATIVE_PIXMAP = 0x300A, EGL_BAD_NATIVE_WINDOW = 0x300B, EGL_BAD_PARAMETER = 0x300C, EGL_BAD_SURFACE = 0x300D,
      EGL_BUFFER_SIZE = 0x3020, EGL_ALPHA_SIZE = 0x3021, EGL_BLUE_SIZE = 0x3022, EGL_GREEN_SIZE = 0x3023, EGL_RED_SIZE = 0x3024,
      EGL_DEPTH_SIZE = 0x3025, EGL_STENCIL_SIZE = 0x3026, EGL_CONFIG_CAVEAT = 0x3027, EGL_CONFIG_ID = 0x3028, EGL_LEVEL = 0x3029,
      EGL_MAX_PBUFFER_HEIGHT = 0x302A, EGL_MAX_PBUFFER_PIXELS = 0x302B, EGL_MAX_PBUFFER_WIDTH = 0x302C, EGL_NATIVE_RENDERABLE = 0x302D,
      EGL_NATIVE_VISUAL_ID = 0x302E, EGL_NATIVE_VISUAL_TYPE = 0x302F, EGL_SAMPLES = 0x3031, EGL_SAMPLE_BUFFERS = 0x3032,
      EGL_SURFACE_TYPE = 0x3033, EGL_TRANSPARENT_TYPE = 0x3034, EGL_TRANSPARENT_BLUE_VALUE = 0x3035, EGL_TRANSPARENT_GREEN_VALUE = 0x3036,
      EGL_TRANSPARENT_RED_VALUE = 0x3037, EGL_NONE = 0x3038, EGL_LUMINANCE_SIZE = 0x303D, EGL_ALPHA_MASK_SIZE = 0x303E,
      EGL_COLOR_BUFFER_TYPE = 0x303F, EGL_RENDERABLE_TYPE = 0x3040, EGL_SLOW_CONFIG = 0x3050, EGL_NON_CONFORMANT_CONFIG = 0x3051,
      EGL_TRANSPARENT_RGB = 0x3052, EGL_RGB_BUFFER = 0x308E, EGL_LUMINANCE_BUFFER = 0x308F, EGL_VENDOR = 0x3053, EGL_VERSION = 0x3054,
      EGL_EXTENSIONS = 0x3055, EGL_HEIGHT = 0x3056, EGL_WIDTH = 0x3057, EGL_LARGEST_PBUFFER = 0x3058, EGL_RENDER_BUFFER = 0x3086,
      EGL_COLORSPACE = 0x3087, EGL_ALPHA_FORMAT = 0x3088, EGL_HORIZONTAL_RESOLUTION = 0x3090, EGL_VERTICAL_RESOLUTION = 0x3091,
      EGL_PIXEL_ASPECT_RATIO = 0x3092, EGL_SINGLE_BUFFER = 0x3085, EGL_CORE_NATIVE_ENGINE = 0x305B, EGL_DRAW = 0x3059, EGL_READ = 0x305A,
      EGL_DONT_CARE = -1, EGL_PBUFFER_BIT = 0x01, EGL_PIXMAP_BIT = 0x02, EGL_WINDOW_BIT = 0x04, EGL_DEFAULT_DISPLAY_INT = 0;
  Object EGL_DEFAULT_DISPLAY = null;
  EGLContext EGL_NO_CONTEXT = javax.microedition.khronos.egl.EGLImpl.NO_CONTEXT;
  EGLDisplay EGL_NO_DISPLAY = javax.microedition.khronos.egl.EGLImpl.NO_DISPLAY;
  EGLSurface EGL_NO_SURFACE = javax.microedition.khronos.egl.EGLImpl.NO_SURFACE;
  boolean eglChooseConfig(EGLDisplay d, int[] attribs, EGLConfig[] configs, int size, int[] num);
  boolean eglCopyBuffers(EGLDisplay d, EGLSurface s, Object pixmap);
  EGLContext eglCreateContext(EGLDisplay d, EGLConfig c, EGLContext share, int[] attribs);
  EGLSurface eglCreatePbufferSurface(EGLDisplay d, EGLConfig c, int[] attribs);
  EGLSurface eglCreatePixmapSurface(EGLDisplay d, EGLConfig c, Object pixmap, int[] attribs);
  EGLSurface eglCreateWindowSurface(EGLDisplay d, EGLConfig c, Object window, int[] attribs);
  boolean eglDestroyContext(EGLDisplay d, EGLContext c);
  boolean eglDestroySurface(EGLDisplay d, EGLSurface s);
  boolean eglGetConfigAttrib(EGLDisplay d, EGLConfig c, int attr, int[] value);
  boolean eglGetConfigs(EGLDisplay d, EGLConfig[] configs, int size, int[] num);
  EGLContext eglGetCurrentContext();
  EGLDisplay eglGetCurrentDisplay();
  EGLSurface eglGetCurrentSurface(int which);
  EGLDisplay eglGetDisplay(Object display);
  int eglGetError();
  boolean eglInitialize(EGLDisplay d, int[] version);
  boolean eglMakeCurrent(EGLDisplay d, EGLSurface draw, EGLSurface read, EGLContext c);
  boolean eglQueryContext(EGLDisplay d, EGLContext c, int attr, int[] value);
  String eglQueryString(EGLDisplay d, int name);
  boolean eglQuerySurface(EGLDisplay d, EGLSurface s, int attr, int[] value);
  boolean eglReleaseThread();
  boolean eglSwapBuffers(EGLDisplay d, EGLSurface s);
  boolean eglTerminate(EGLDisplay d);
  boolean eglWaitGL();
  boolean eglWaitNative(int engine, Object bindTarget);
}
