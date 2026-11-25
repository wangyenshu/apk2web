package a2w;

import org.teavm.interop.Async;
import org.teavm.interop.AsyncCallback;
import org.teavm.jso.JSBody;
import org.teavm.jso.JSFunctor;
import org.teavm.jso.JSObject;
import org.teavm.jso.typedarrays.Int8Array;

/** Everything the Java side needs from the page (see site/hw.js). */
public final class Host {
  private Host() {}

  @JSFunctor public interface ObjCb extends JSObject { void call(JSObject o); }
  @JSFunctor public interface IntCb extends JSObject { void call(int v); }

  // ------------------------------------------------------------ basics
  @JSBody(script = "return performance.now();")
  public static native double now();
  @JSBody(params = {"l", "s"}, script = "HW.log(l, s);")
  public static native void log(int level, String s);
  @JSBody(params = {"s"}, script = "HW.fatal(s);")
  static native void fatal(String s);
  @JSBody(script = "HW.exitApp();")
  public static native void exitApp();
  /** System.exit (calls rewritten by jarproc) */
  public static void exit(int code) { exitApp(); }
  @JSBody(params = "u", script = "HW.openUrl(u);")
  public static native void openUrl(String url);
  @JSBody(params = {"v"}, script = "HW.menuButton(v);")
  public static native void menuButton(boolean visible);
  @JSBody(params = {"text", "title"}, script = "HW.share(text, title);")
  public static native void share(String text, String title);
  @JSBody(params = {"x", "y", "w", "h"}, script = "HW.gl.place(x, y, w, h);")
  public static native void glPlace(int x, int y, int w, int h);
  /** the page's theme color (the browser's toolbar), from Window.setStatusBarColor */
  @JSBody(params = "c", script = "var m = document.querySelector('meta[name=theme-color]'); if (!m) { m = document.createElement('meta'); m.name = 'theme-color'; document.head.appendChild(m); }"
      + " m.content = '#' + ('000000' + (c & 0xffffff).toString(16)).slice(-6);")
  public static native void setThemeColor(int color);
  /** Activity.requestFullscreenMode: 1 when the page went full screen or left it */
  @JSBody(params = {"enter", "cb"}, script = "var p = enter ? (document.documentElement.requestFullscreen ? document.documentElement.requestFullscreen() : Promise.reject())"
      + " : (document.fullscreenElement && document.exitFullscreen ? document.exitFullscreen() : Promise.reject()); p.then(function() { cb(1); }, function() { cb(0); });")
  public static native void fullscreen(boolean enter, IntCb cb);
  @JSBody(script = "return HW.deviceId();")
  public static native String deviceId();
  /** Cocos2dxRenderer.onDrawFrame's frame limiter (Thread.sleep rewritten by jarproc): the next frame waits instead */
  static long nextFrameAt;
  public static void frameSleep(long ms) { nextFrameAt = android.os.SystemClock.uptimeMillis() + ms; }
  public static boolean frameDue(long now) { return now >= nextFrameAt - 2; }
  @JSBody(params = {"k"}, script = "return HW.config[k] == null ? null : String(HW.config[k]);")
  public static native String config(String key);

  public static void uncaught(Throwable t) {
    log(6, "E/AndroidRuntime: " + android.util.Log.getStackTraceString(t));
  }

  public static byte[] bytes(Int8Array a) { return a == null ? null : a.copyToJavaArray(); }
  public static Int8Array js(byte[] b) { return b == null ? null : Int8Array.copyFromJavaArray(b); }

  // ------------------------------------------------------------ persistent files (IndexedDB-backed)
  @JSBody(params = {"p"}, script = "return HW.fs.isFile(p);")
  public static native boolean fileExists(String p);
  @JSBody(params = {"p"}, script = "return HW.fs.isDir(p);")
  public static native boolean dirExists(String p);
  @JSBody(params = {"p"}, script = "return HW.fs.size(p);")
  public static native int fileLength(String p);
  @JSBody(params = {"p"}, script = "return HW.fs.mtime(p);")
  public static native double fileTime(String p);
  @JSBody(params = {"p"}, script = "HW.fs.rm(p);")
  public static native void fileDelete(String p);
  @JSBody(params = {"p"}, script = "HW.fs.rmdir(p);")
  public static native void dirDelete(String p);
  @JSBody(params = {"p"}, script = "HW.fs.mkdir(p);")
  public static native void dirCreate(String p);
  @JSBody(params = {"p"}, script = "return HW.fs.list(p);")
  public static native String listDir(String p);
  @JSBody(params = {"p"}, script = "return HW.fs.read(p);")
  static native Int8Array fileReadJS(String p);
  @JSBody(params = {"p", "d", "a"}, script = "HW.fs.write(p, d, a);")
  static native void fileWriteJS(String p, Int8Array d, boolean append);
  public static byte[] fileRead(String p) { Int8Array a = fileReadJS(p); return a == null ? new byte[0] : bytes(a); }
  public static void fileWrite(String p, byte[] d, boolean append) { fileWriteJS(p, js(d), append); }

  @JSBody(params = {"n"}, script = "return HW.fs.prefs(n);")
  public static native String prefsLoad(String name);
  @JSBody(params = {"n", "j"}, script = "HW.fs.setPrefs(n, j);")
  public static native void prefsSave(String name, String json);

  // ------------------------------------------------------------ APK contents
  /** -1 missing, 0 stored, 8 deflated */
  @JSBody(params = {"n"}, script = "var e = HW.apk.entry(n); return e ? e.method : -1;")
  public static native int apkMethod(String name);
  @JSBody(params = {"n"}, script = "var e = HW.apk.entry(n); return e ? e.usize : -1;")
  public static native int apkSize(String name);
  @JSBody(params = {"n"}, script = "return HW.apk.raw(n);")
  static native Int8Array apkRawJS(String name);
  @JSBody(params = {"p"}, script = "return HW.apk.list(p);")
  public static native String apkList(String prefix);

  public static byte[] apkRead(String name) {
    int m = apkMethod(name);
    if (m < 0) return null;
    byte[] raw = bytes(apkRawJS(name));
    if (m == 0) return raw;
    java.util.zip.Inflater inf = new java.util.zip.Inflater(true);
    inf.setInput(raw);
    byte[] out = new byte[apkSize(name)];
    try {
      int n = 0;
      while (n < out.length) {
        int k = inf.inflate(out, n, out.length - n);
        if (k == 0 && (inf.finished() || inf.needsInput())) break;
        n += k;
      }
    } catch (java.util.zip.DataFormatException e) {
      return null;
    } finally {
      inf.end();
    }
    return out;
  }

  @JSBody(script = "return HW.webgl ? HW.webgl.drawingBufferWidth | 0 : 0;")
  static native int canvasWidth();
  @JSBody(script = "return HW.webgl ? HW.webgl.drawingBufferHeight | 0 : 0;")
  static native int canvasHeight();
  /** the size of the page's GL drawing buffer */
  public static int[] canvasSize() { return new int[] {canvasWidth(), canvasHeight()}; }

  // ------------------------------------------------------------ images & fonts
  @JSBody(params = {"d", "s", "cb"}, script = "HW.decodeImage(d, s).then(cb, function(e){ HW.log(6, 'decode failed: ' + e); cb(null); });")
  static native void decodeImageJS(Int8Array d, int sample, ObjCb cb);
  @Async static native JSObject decodeImageAsync(Int8Array d, int sample);
  static void decodeImageAsync(Int8Array d, int sample, AsyncCallback<JSObject> cb) { decodeImageJS(d, sample, o -> cb.complete(o)); }
  public static JSObject decodeImage(byte[] data, int sample) { return decodeImageAsync(js(data), sample); }

  @JSBody(params = {"o"}, script = "return o.width|0;")
  public static native int width(JSObject o);
  @JSBody(params = {"o"}, script = "return o.height|0;")
  public static native int height(JSObject o);

  @JSBody(params = {"n", "d", "cb"}, script = "HW.loadFont(n, d).then(function(){cb(1)}, function(e){ HW.log(6, 'font failed: ' + e); cb(0); });")
  static native void loadFontJS(String name, Int8Array d, IntCb cb);
  @Async static native Integer loadFontAsync(String name, Int8Array d);
  static void loadFontAsync(String name, Int8Array d, AsyncCallback<Integer> cb) { loadFontJS(name, d, v -> cb.complete(v)); }
  public static boolean loadFont(String name, byte[] data) { return loadFontAsync(name, js(data)) != 0; }

  /** Lets the browser run (rendering, events) and resumes on the next macrotask. */
  @JSBody(params = {"cb"}, script = "setTimeout(function(){cb(0)}, 0);")
  static native void yieldJS(IntCb cb);
  @Async static native Integer yieldAsync();
  static void yieldAsync(AsyncCallback<Integer> cb) { yieldJS(v -> cb.complete(v)); }
  public static void yieldToBrowser() { yieldAsync(); }
}
