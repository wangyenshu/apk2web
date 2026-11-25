# javax.microedition.khronos.egl

`+` implemented, `*` derived from the declaration (a data class or builder: stores and returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). "declaration": the class is declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).

## EGL — framework; API 1+ (0 +, 0 *, 0 ~, 0 -)

## EGL10 — framework; API 1+ (24 +, 0 *, 0 ~, 0 -)

```
+ EGL_DEFAULT_DISPLAY [field]  [API 1+]
+ EGL_NO_CONTEXT [field]  [API 1+]
+ EGL_NO_DISPLAY [field]  [API 1+]
+ EGL_NO_SURFACE [field]  [API 1+]
+ eglChooseConfig(EGLDisplay, int[], EGLConfig[], int, int[]): boolean  [API 1+]
+ eglCopyBuffers(EGLDisplay, EGLSurface, Object): boolean  [API 1+]
+ eglCreateContext(EGLDisplay, EGLConfig, EGLContext, int[]): EGLContext  [API 1+]
+ eglCreatePbufferSurface(EGLDisplay, EGLConfig, int[]): EGLSurface  [API 1+]
+ eglCreatePixmapSurface(EGLDisplay, EGLConfig, Object, int[]): EGLSurface  [API 1+, deprecated in 26]
+ eglCreateWindowSurface(EGLDisplay, EGLConfig, Object, int[]): EGLSurface  [API 1+]
+ eglDestroyContext(EGLDisplay, EGLContext): boolean  [API 1+]
+ eglDestroySurface(EGLDisplay, EGLSurface): boolean  [API 1+]
+ eglGetConfigAttrib(EGLDisplay, EGLConfig, int, int[]): boolean  [API 1+]
+ eglGetConfigs(EGLDisplay, EGLConfig[], int, int[]): boolean  [API 1+]
+ eglGetCurrentContext(): EGLContext  [API 1+]
+ eglGetCurrentDisplay(): EGLDisplay  [API 1+]
+ eglGetCurrentSurface(int): EGLSurface  [API 1+]
+ eglGetDisplay(Object): EGLDisplay  [API 1+]
+ eglGetError(): int  [API 1+]
+ eglInitialize(EGLDisplay, int[]): boolean  [API 1+]
+ eglMakeCurrent(EGLDisplay, EGLSurface, EGLSurface, EGLContext): boolean  [API 1+]
+ eglQueryContext(EGLDisplay, EGLContext, int, int[]): boolean  [API 1+]
+ eglQueryString(EGLDisplay, int): String  [API 1+]
+ eglQuerySurface(EGLDisplay, EGLSurface, int, int[]): boolean  [API 1+]
+ eglSwapBuffers(EGLDisplay, EGLSurface): boolean  [API 1+]
+ eglTerminate(EGLDisplay): boolean  [API 1+]
+ eglWaitGL(): boolean  [API 1+]
+ eglWaitNative(int, Object): boolean  [API 1+]
```

## EGL11 — framework; API 1+ (0 +, 0 *, 0 ~, 0 -)

## EGLConfig — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ EGLConfig()  [API 1+]
```

## EGLContext — framework; API 1+ (3 +, 0 *, 0 ~, 0 -)

```
+ EGLContext()  [API 1+]
+ getEGL(): EGL [static]  [API 1+]
+ getGL(): GL  [API 1+]
```

## EGLDisplay — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ EGLDisplay()  [API 1+]
```

## EGLSurface — framework; API 1+ (1 +, 0 *, 0 ~, 0 -)

```
+ EGLSurface()  [API 1+]
```

