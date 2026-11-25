package a2w;

import android.os.Looper;
import org.teavm.jso.JSExport;

public final class Main {
  private Main() {}
  static Thread mainThread;
  /** the page's callbacks run as the main thread; returns the thread to resume afterwards */
  private static Thread asMain() { Thread t = Thread.currentThread(); FiberSupport.resume(mainThread); return t; }
  public static void main(String[] args) {
    mainThread = Thread.currentThread();
    Vfs.install();
    XhrConnection.install();
    // what Android reports (MultiDex, for one, checks java.vm.version and skips its work on ART)
    String pkg = Res.pkg();
    String[][] props = {{"java.vm.name", "Dalvik"}, {"java.runtime.name", "Android Runtime"}, {"java.runtime.version", "0.9"}, {"java.vm.version", "2.1.0"}, {"java.vm.vendor", "The Android Project"},
        {"java.vendor", "The Android Project"}, {"java.version", "0"}, {"java.specification.version", "0.9"},
        {"os.name", "Linux"}, {"os.arch", "armv7l"}, {"os.version", "3.4.0"}, {"file.separator", "/"}, {"path.separator", ":"},
        {"line.separator", "\n"}, {"file.encoding", "UTF-8"}, {"user.dir", "/"}, {"user.home", ""}, {"user.name", "u0_a42"},
        {"java.io.tmpdir", "/data/data/" + pkg + "/cache"}, {"java.home", "/system"}, {"java.class.path", "."},
        {"http.agent", "Dalvik/2.1.0 (Linux; U; Android " + android.os.Build.VERSION.RELEASE + "; apk2web)"},
        {"android.icu.library.version", "0"}};
    for (String[] p : props) System.setProperty(p[0], p[1]);
    String loc = Host.config("locale");
    if (loc != null) {
      String[] p = loc.split("_");
      java.util.Locale.setDefault(p.length > 1 ? new java.util.Locale(p[0], p[1]) : new java.util.Locale(p[0]));
    }
    Looper.prepareMainLooper();
    try {
      ActivityThread.main();
    } catch (Throwable t) {
      Host.uncaught(t);
    }
    Looper.loop();
  }

  // ---- entry points for the page (teavm.exports.*)
  @JSExport public static String debugState() { return Windows.debugState() + "\nactivities: " + ActivityThread.debugStack() + "\nqueue: " + android.os.Looper.getMainLooper().getQueue().debugString()
      + "\nhandling: " + (android.os.Looper.dispatching == null ? "nothing" : android.os.Looper.dispatching.toString()); }
  @JSExport public static String debugViews() { return Windows.debugViews(); }
  @JSExport public static void debugLog(boolean verbose) { android.util.Log.level = verbose ? android.util.Log.VERBOSE : android.util.Log.INFO; }
  @JSExport public static String debugInvalidators() {
    java.util.HashMap<String, Integer> m = android.view.View.DEBUG_INVALIDATE;
    android.view.View.DEBUG_INVALIDATE = new java.util.HashMap<>();
    return m == null ? "started" : m.toString();
  }
  @JSExport public static String debugUnimplemented() { return Stubs.report(); }
  @JSExport public static void onFrame() { Thread t = asMain(); try { Events.onFrame(); } finally { FiberSupport.resume(t); } }
  @JSExport public static void onTouch(int action, int id, float x, float y) { Thread t = asMain(); try { Events.onTouch(action, id, x, y); } finally { FiberSupport.resume(t); } }
  @JSExport public static void onKey(int code, boolean down, int repeat, int meta) { Thread t = asMain(); try { Events.onKey(code, down, repeat, meta); } finally { FiberSupport.resume(t); } }
  @JSExport public static void onPointer(int action, int id, float x, float y, float pressure, int tool, int buttons) { Thread t = asMain(); try { Events.onPointer(action, id, x, y, pressure, tool, buttons); } finally { FiberSupport.resume(t); } }
  @JSExport public static void onPointerSample(int id, float x, float y, float ageMs) { Thread t = asMain(); try { Events.onPointerSample(id, x, y, ageMs); } finally { FiberSupport.resume(t); } }
  @JSExport public static void onHover(int action, float x, float y) { Thread t = asMain(); try { Events.onHover(action, x, y); } finally { FiberSupport.resume(t); } }
  @JSExport public static void onScroll(float x, float y, float h, float v) { Thread t = asMain(); try { Events.onScroll(x, y, h, v); } finally { FiberSupport.resume(t); } }
  @JSExport public static void onText(String text, int action) { Thread t = asMain(); try { Events.onText(text, action); } finally { FiberSupport.resume(t); } }
  @JSExport public static void onSelection(int start, int end) { Thread t = asMain(); try { Events.onSelection(start, end); } finally { FiberSupport.resume(t); } }
  @JSExport public static void onVisibility(boolean visible) { Thread t = asMain(); try { Events.onVisibility(visible); } finally { FiberSupport.resume(t); } }
  @JSExport public static void onAudio(int id, int event) { Thread t = asMain(); try { Events.onAudio(id, event); } finally { FiberSupport.resume(t); } }
  @JSExport public static void onResize() { Thread t = asMain(); try { Events.onResize(); } finally { FiberSupport.resume(t); } }
  @JSExport public static String webJsCall(int id, String name, String method, String args) { Thread t = asMain(); try { return android.webkit.WebView.jsCall(id, name, method, args); } finally { FiberSupport.resume(t); } }
  @JSExport public static void onWeb(int id, int event, String url) { Thread t = asMain(); try { Events.onWeb(id, event, url); } finally { FiberSupport.resume(t); } }

  // ---- JNI services for the native libraries (library_jni.js)
  @JSExport public static int jniFindClass(String name) { return Jni.findClass(name); }
  @JSExport public static int jniGetSuperclass(int c) { return Jni.getSuperclass(c); }
  @JSExport public static boolean jniIsAssignableFrom(int a, int b) { return Jni.isAssignableFrom(a, b); }
  @JSExport public static int jniGetObjectClass(int o) { return Jni.getObjectClass(o); }
  @JSExport public static boolean jniIsInstanceOf(int o, int c) { return Jni.isInstanceOf(o, c); }
  @JSExport public static String jniClassName(int c) { return Jni.className(c); }
  @JSExport public static int jniMethodId(int c, String name, String sig) { return Jni.memberId(c, name, sig, true); }
  @JSExport public static int jniFieldId(int c, String name, String sig) { return Jni.memberId(c, name, sig, false); }
  @JSExport public static int jniCall(int id, int target, org.teavm.jso.typedarrays.Int32Array args) { return Jni.invoke(id, target, args == null ? new int[0] : args.copyToJavaArray()); }
  @JSExport public static int jniNewObject(int id, org.teavm.jso.typedarrays.Int32Array args) { return Jni.newObject(id, args == null ? new int[0] : args.copyToJavaArray()); }
  @JSExport public static int jniGetField(int id, int target) { return Jni.getField(id, target); }
  @JSExport public static void jniSetField(int id, int target, int lo, int hi) { Jni.setField(id, target, lo, hi); }
  @JSExport public static int jniRetHi() { return Jni.retHi; }
  @JSExport public static int jniNewString(String s) { return Jni.newString(s); }
  @JSExport public static String jniString(int h) { return Jni.string(h); }
  @JSExport public static int jniArrayLength(int h) { return Jni.arrayLength(h); }
  @JSExport public static int jniNewArray(int kind, int len) { return Jni.newArray(kind, len); }
  @JSExport public static org.teavm.jso.typedarrays.Int8Array jniArrayGet(int h, int start, int len) { return Jni.arrayGet(h, start, len); }
  @JSExport public static void jniArraySet(int h, int start, org.teavm.jso.typedarrays.Int8Array data) { Jni.arraySet(h, start, data); }
  @JSExport public static int jniNewObjectArray(int len, int c, int init) { return Jni.newObjectArray(len, c, init); }
  @JSExport public static int jniGetObjectElement(int a, int i) { return Jni.getObjectElement(a, i); }
  @JSExport public static void jniSetObjectElement(int a, int i, int v) { Jni.setObjectElement(a, i, v); }
  @JSExport public static int jniNewGlobalRef(int h) { return Jni.newGlobalRef(h); }
  @JSExport public static void jniDeleteGlobalRef(int h) { Jni.deleteGlobalRef(h); }
  @JSExport public static int jniNewLocalRef(int h) { return Jni.handle(Jni.get(h)); }
  @JSExport public static boolean jniIsSameObject(int a, int b) { return Jni.get(a) == Jni.get(b); }
  @JSExport public static int jniThrow(int h) { return Jni.throwObject(h); }
  @JSExport public static int jniThrowNew(int c, String msg) { return Jni.throwNew(c, msg); }
  @JSExport public static int jniExceptionOccurred() { return Jni.exceptionOccurred(); }
  @JSExport public static void jniExceptionClear() { Jni.exceptionClear(); }
  @JSExport public static void jniExceptionDescribe() { Jni.exceptionDescribe(); }
  @JSExport public static int jniArrayElemSize(int h) { return Jni.elemSize(h); }
  @JSExport public static int jniAllocObject(int c) { return Jni.allocObject(c); }
  @JSExport public static int jniRefType(int h) { return Jni.refType(h); }
  @JSExport public static int jniPopFrame(int h) { return Jni.popFrame(h); }
  @JSExport public static void jniThrowUnsatisfied(String msg) { Jni.throwUnsatisfied(msg); }
  @JSExport public static int jniNewDirectBuffer(org.teavm.jso.typedarrays.Int8Array d) { return Jni.newDirectBuffer(d); }
  @JSExport public static org.teavm.jso.typedarrays.Int8Array jniBufferBytes(int h) { return Jni.bufferBytes(h); }
  @JSExport public static void jniBufferSet(int h, org.teavm.jso.typedarrays.Int8Array d) { Jni.bufferSet(h, d); }
  @JSExport public static int jniBufferCapacity(int h) { return Jni.bufferCapacity(h); }
  @JSExport public static void jniBufferMirror(int h, int g) { Jni.bufferMirror(h, g); }
  @JSExport public static int jniBufferMirrorOf(int h) { return Jni.bufferMirrorOf(h); }
  @JSExport public static void jniEnter() { Jni.enter(); }
  @JSExport public static void jniExit() { Jni.exit(); }
}
