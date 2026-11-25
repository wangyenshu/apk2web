package a2w;

import org.teavm.jso.JSBody;
import org.teavm.jso.typedarrays.Int32Array;
import org.teavm.jso.typedarrays.Int8Array;

/**
 * JNI for the app's native libraries, which run in the ARM emulator (HW.jni in hw.js).
 *
 * Every Java object native code sees is a handle (an int). Handles made while a native call is running are local:
 * they are released when the outermost native call returns. NewGlobalRef pins an object until DeleteGlobalRef.
 *
 * Java -> native: jarproc rewrites each native method to {@code Jni.enter(); pack args into int slots; Jni.callX(id, ...)}.
 * native -> Java: the JNIEnv functions (emulator jni.c -> library_jni.js -> Main.jni* exports) end up here, and member
 * access goes through the per-app generated table {@link a2w.gen.JniTable}.
 */
public final class Jni {
  private Jni() {}

  // ---------------------------------------------------------------- handles
  static Object[] objs = new Object[4096];
  static boolean[] global = new boolean[4096];
  static int[] free = new int[4096];
  static int nfree, next = 1;
  /** local handles made during native calls, in order; frameStart[depth] marks where each call began */
  static int[] locals = new int[4096];
  static int nlocals;
  static int[] frameStart = new int[256];
  static int depth;

  static int alloc(Object o, boolean isGlobal) {
    int h;
    if (nfree > 0) h = free[--nfree];
    else {
      if (next == objs.length) {
        objs = java.util.Arrays.copyOf(objs, next * 2);
        global = java.util.Arrays.copyOf(global, next * 2);
      }
      h = next++;
    }
    objs[h] = o;
    global[h] = isGlobal;
    if (!isGlobal) {
      if (nlocals == locals.length) locals = java.util.Arrays.copyOf(locals, nlocals * 2);
      locals[nlocals++] = h;
    }
    return h;
  }
  static void release(int h) {
    if (h <= 0 || h >= next || objs[h] == null && !global[h]) return;
    objs[h] = null;
    global[h] = false;
    if (nfree == free.length) free = java.util.Arrays.copyOf(free, nfree * 2);
    free[nfree++] = h;
  }
  /** a local handle for o (0 for null) */
  public static int handle(Object o) { return o == null ? 0 : alloc(o, false); }
  public static Object get(int h) { return h > 0 && h < next ? objs[h] : null; }
  public static int newGlobalRef(int h) { Object o = get(h); return o == null ? 0 : alloc(o, true); }
  public static void deleteGlobalRef(int h) { if (h > 0 && h < next && global[h]) release(h); }
  public static void deleteLocalRef(int h) { /* released with its frame; releasing early could free a reused slot */ }

  public static void enter() {
    if (depth == frameStart.length) frameStart = java.util.Arrays.copyOf(frameStart, depth * 2);
    frameStart[depth++] = nlocals;
  }
  static void exit() {
    int start = frameStart[--depth];
    for (int i = nlocals - 1; i >= start; i--) {
      int h = locals[i];
      if (!global[h]) release(h);
    }
    nlocals = start;
  }

  // ---------------------------------------------------------------- exceptions
  static Throwable pending;
  public static void setPending(Throwable t) { pending = t; }
  static void rethrow() {
    Throwable t = pending;
    if (t == null) return;
    pending = null;
    if (t instanceof RuntimeException) throw (RuntimeException) t;
    if (t instanceof Error) throw (Error) t;
    throw new RuntimeException(t);
  }

  // ---------------------------------------------------------------- Java -> native
  /** native method id -> resolved; slots: 32-bit words in Java parameter order (long/double = lo, hi) */
  public static void callV(int id, Object self, int[] slots) { call(id, self, slots); }
  public static int callI(int id, Object self, int[] slots) { return call(id, self, slots); }
  public static boolean callZ(int id, Object self, int[] slots) { return (call(id, self, slots) & 0xff) != 0; }
  public static byte callB(int id, Object self, int[] slots) { return (byte) call(id, self, slots); }
  public static char callC(int id, Object self, int[] slots) { return (char) call(id, self, slots); }
  public static short callS(int id, Object self, int[] slots) { return (short) call(id, self, slots); }
  public static float callF(int id, Object self, int[] slots) { return Float.intBitsToFloat(call(id, self, slots)); }
  public static long callJ(int id, Object self, int[] slots) {
    int lo = call(id, self, slots);
    return (lo & 0xffffffffL) | ((long) lastHi << 32);
  }
  public static double callD(int id, Object self, int[] slots) { return Double.longBitsToDouble(callJ(id, self, slots)); }
  public static Object callL(int id, Object self, int[] slots) {
    // the result handle must be read before the frame is released
    int selfH = handle(self);
    int r;
    try {
      r = callJS(id, selfH, slots == null ? null : Int32Array.copyFromJavaArray(slots));
    } catch (Throwable t) {
      exit();
      throw t;
    }
    Object o = get(r);
    lastHi = hiJS();
    exit();
    rethrow();
    return o;
  }
  static int lastHi;
  private static int call(int id, Object self, int[] slots) {
    int selfH = handle(self);
    int r;
    try {
      r = callJS(id, selfH, slots == null ? null : Int32Array.copyFromJavaArray(slots));
      lastHi = hiJS();
    } finally {
      exit();
    }
    rethrow();
    return r;
  }
  @JSBody(params = {"id", "self", "slots"}, script = "return HW.jni.callNative(id, self, slots);")
  static native int callJS(int id, int self, Int32Array slots);
  @JSBody(script = "return HW.jni.hi();")
  static native int hiJS();

  public static void loadLibrary(String name) {
    if (!loadJS(name)) throw new a2w.jdk.UnsatisfiedLinkError("dlopen failed: library \"lib" + name + ".so\" not found");
  }
  public static void load(String path) {
    String n = path.substring(path.lastIndexOf('/') + 1);
    if (n.startsWith("lib")) n = n.substring(3);
    if (n.endsWith(".so")) n = n.substring(0, n.length() - 3);
    loadLibrary(n);
  }
  @JSBody(params = "n", script = "return HW.jni.loadLibrary(n);")
  static native boolean loadJS(String name);

  // ---------------------------------------------------------------- native -> Java (called from Main exports)
  static int retHi;
  static int lo(long v) { retHi = (int) (v >>> 32); return (int) v; }

  // ---- what the native code can name (registered by the generated a2w.gen.JniTable)
  static java.util.HashMap<String, Class<?>> classes;
  static java.util.HashMap<String, Integer> members, arrayIds, throwIds;
  static void tables() {
    if (classes != null) return;
    classes = new java.util.HashMap<>(); members = new java.util.HashMap<>(); arrayIds = new java.util.HashMap<>(); throwIds = new java.util.HashMap<>();
    a2w.gen.JniTable.register();
  }
  /** a class FindClass may name; arrayId > 0: NewObjectArray of it; throwId > 0: ThrowNew of it */
  public static void regClass(String name, Class<?> c, int arrayId, int throwId) {
    classes.put(name, c);
    if (arrayId > 0) arrayIds.put(c.getName(), arrayId);
    if (throwId > 0) throwIds.put(c.getName(), throwId);
  }
  /** an SDK class the build replaced by nothing (analytics, ads): FindClass finds it, every member of it is a no-op */
  public static void regDroppedClass(String name) { classes.put(name, DroppedClass.class); }
  /** stands for every dropped class */
  public static final class DroppedClass {}
  // IDs past the generated table's are members of dropped classes (each its own: native code keeps one signature per ID)
  static int methodCount = Integer.MAX_VALUE / 2, fieldCount = Integer.MAX_VALUE / 2, nextNoopMethod, nextNoopField;
  public static void regMemberCounts(int methods, int fields) {
    methodCount = methods; fieldCount = fields; nextNoopMethod = methods; nextNoopField = fields;
  }

  /** "pkg/Cls.name(sig)" for methods and constructors, "pkg/Cls.name:sig" for fields */
  public static void regMember(String key, int id) { members.put(key, id); }
  static int member(String key) { Integer i = members.get(key); return i == null ? 0 : i; }

  static Class<?> cls(int h) { Object o = get(h); return o instanceof Class ? (Class<?>) o : null; }
  static String jniName(Class<?> c) { return c == null ? null : c.getName().replace('.', '/'); }

  static int findClass(String name) {
    tables();
    Class<?> c = classes.get(name.replace('.', '/'));
    if (c == null) { pending = new NoClassDefFoundError(name); return 0; }
    return handle(c);
  }
  static int getSuperclass(int h) { Class<?> c = cls(h); return c == null ? 0 : handle(c.getSuperclass()); }
  static boolean isAssignableFrom(int a, int b) { Class<?> x = cls(a), y = cls(b); return x != null && y != null && y.isAssignableFrom(x); }
  static int getObjectClass(int h) { Object o = get(h); return o == null ? 0 : handle(o.getClass()); }
  static boolean isInstanceOf(int h, int c) { Object o = get(h); Class<?> k = cls(c); return o == null || k != null && k.isInstance(o); }
  static String className(int h) { return jniName(cls(h)); }

  /** member ids: looked up by "pkg/Class.name(sig)" in the class and then its superclasses */
  static int memberId(int clsH, String name, String sig, boolean method) {
    tables();
    if (cls(clsH) == DroppedClass.class) return method ? ++nextNoopMethod : ++nextNoopField;
    for (Class<?> c = cls(clsH); c != null; c = c.getSuperclass()) {
      String key = jniName(c) + "." + name + (method ? "" : ":") + sig;
      int id = member(key);
      if (id != 0) return id;
      if ("<init>".equals(name)) break;
    }
    // interfaces of the class: default and interface methods
    Class<?> c = cls(clsH);
    if (c != null && method) {
      for (Class<?> i : c.getInterfaces()) {
        int id = member(jniName(i) + "." + name + sig);
        if (id != 0) return id;
      }
    }
    if (!method)
      for (Class<?> k = cls(clsH); k != null; k = k.getSuperclass()) {
        int v = VIRTUAL_FIELDS.indexOf(jniName(k) + "." + name + ":" + sig);
        if (v >= 0) return VIRTUAL_BASE + v;
      }
    pending = method ? new NoSuchMethodError(className(clsH) + "." + name + sig) : new NoSuchFieldError(className(clsH) + "." + name);
    Host.log(5, "JNI: no " + (method ? "method " : "field ") + className(clsH) + "." + name + " " + sig
        + " (not in the generated JNI table)");
    return 0;
  }

  static int invoke(int id, int target, int[] a) {
    if (id > methodCount) { retHi = 0; return 0; }
    try {
      return lo(a2w.gen.JniTable.invoke(id, get(target), a));
    } catch (Throwable t) {
      pending = t;
      return lo(0);
    }
  }
  static int newObject(int id, int[] a) {
    if (id > methodCount) return handle(new DroppedClass());
    try {
      return lo(a2w.gen.JniTable.invoke(id, null, a));
    } catch (Throwable t) {
      pending = t;
      return 0;
    }
  }
  /** hidden fields of platform classes that native code reads (Android's GL bindings: a buffer's position, limit and
   *  element size; its native address), answered by code */
  static final java.util.List<String> VIRTUAL_FIELDS = java.util.Arrays.asList(
      "java/nio/Buffer.position:I", "java/nio/Buffer.limit:I", "java/nio/Buffer.capacity:I", "java/nio/Buffer._elementSizeShift:I",
      "java/nio/Buffer.effectiveDirectAddress:J", "java/nio/Buffer.address:J", "java/nio/Buffer.mark:I");
  static final int VIRTUAL_BASE = 0x40000000;
  static long virtualGet(int v, Object o) {
    java.nio.Buffer b = (java.nio.Buffer) o;
    switch (v) {
      case 0: return b.position();
      case 1: return b.limit();
      case 2: return b.capacity();
      case 3: return a2w.jdk.nio.NIOAccess.shift(b);
      case 4: case 5: return b.hasArray() ? 0 : nativeAddress(b);
      default: return -1;
    }
  }
  static void virtualSet(int v, Object o, long value) {
    java.nio.Buffer b = (java.nio.Buffer) o;
    if (v == 0) b.position((int) value);
    else if (v == 1) b.limit((int) value);
  }
  static int getField(int id, int target) {
    if (id >= VIRTUAL_BASE) {
      try { return lo(virtualGet(id - VIRTUAL_BASE, get(target))); } catch (Throwable t) { pending = t; return lo(0); }
    }
    if (id > fieldCount) { retHi = 0; return 0; }
    try { return lo(a2w.gen.JniTable.getField(id, get(target))); } catch (Throwable t) { pending = t; return lo(0); }
  }
  static void setField(int id, int target, int lo, int hi) {
    if (id >= VIRTUAL_BASE) { try { virtualSet(id - VIRTUAL_BASE, get(target), (lo & 0xffffffffL) | ((long) hi << 32)); } catch (Throwable t) { pending = t; } return; }
    if (id > fieldCount) return;
    try { a2w.gen.JniTable.setField(id, get(target), (lo & 0xffffffffL) | ((long) hi << 32)); } catch (Throwable t) { pending = t; }
  }

  // ---- helpers for the generated table: argument slots -> Java values, results -> raw 64-bit
  public static long j(int[] a, int i) { return (a[i] & 0xffffffffL) | ((long) a[i + 1] << 32); }
  public static float f(int[] a, int i) { return Float.intBitsToFloat(a[i]); }
  public static double d(int[] a, int i) { return Double.longBitsToDouble(j(a, i)); }
  public static Object o(int[] a, int i) { return get(a[i]); }
  public static long r(Object o) { return handle(o); }
  public static long r(boolean v) { return v ? 1 : 0; }
  public static long r(int v) { return v; }
  public static long r(long v) { return v; }
  public static long r(float v) { return Float.floatToRawIntBits(v); }
  public static long r(double v) { return Double.doubleToRawLongBits(v); }
  public static float fv(long v) { return Float.intBitsToFloat((int) v); }
  public static double dv(long v) { return Double.longBitsToDouble(v); }
  public static Object ov(long v) { return get((int) v); }

  // ---- strings
  static int newString(String s) { return handle(s); }
  static String string(int h) { Object o = get(h); return o == null ? null : o.toString(); }

  // ---- arrays (element bytes little-endian, as ARM sees them)
  static int arrayLength(int h) {
    Object a = get(h);
    if (a instanceof Object[]) return ((Object[]) a).length;
    if (a instanceof byte[]) return ((byte[]) a).length;
    if (a instanceof int[]) return ((int[]) a).length;
    if (a instanceof float[]) return ((float[]) a).length;
    if (a instanceof char[]) return ((char[]) a).length;
    if (a instanceof short[]) return ((short[]) a).length;
    if (a instanceof long[]) return ((long[]) a).length;
    if (a instanceof double[]) return ((double[]) a).length;
    if (a instanceof boolean[]) return ((boolean[]) a).length;
    return 0;
  }
  static int newArray(int kind, int len) {
    switch (kind) {
      case 'Z': return handle(new boolean[len]);
      case 'B': return handle(new byte[len]);
      case 'C': return handle(new char[len]);
      case 'S': return handle(new short[len]);
      case 'I': return handle(new int[len]);
      case 'J': return handle(new long[len]);
      case 'F': return handle(new float[len]);
      case 'D': return handle(new double[len]);
    }
    return 0;
  }
  static Int8Array arrayGet(int h, int start, int len) {
    Object a = get(h);
    byte[] out;
    if (a instanceof byte[]) {
      out = new byte[len];
      System.arraycopy((byte[]) a, start, out, 0, len);
      return Int8Array.copyFromJavaArray(out);
    }
    if (a instanceof boolean[]) {
      out = new byte[len];
      for (int i = 0; i < len; i++) out[i] = (byte) (((boolean[]) a)[start + i] ? 1 : 0);
    } else if (a instanceof int[]) {
      out = new byte[len * 4];
      for (int i = 0; i < len; i++) put32(out, i * 4, ((int[]) a)[start + i]);
    } else if (a instanceof float[]) {
      out = new byte[len * 4];
      for (int i = 0; i < len; i++) put32(out, i * 4, Float.floatToRawIntBits(((float[]) a)[start + i]));
    } else if (a instanceof char[]) {
      out = new byte[len * 2];
      for (int i = 0; i < len; i++) { char c = ((char[]) a)[start + i]; out[i * 2] = (byte) c; out[i * 2 + 1] = (byte) (c >> 8); }
    } else if (a instanceof short[]) {
      out = new byte[len * 2];
      for (int i = 0; i < len; i++) { short c = ((short[]) a)[start + i]; out[i * 2] = (byte) c; out[i * 2 + 1] = (byte) (c >> 8); }
    } else if (a instanceof long[]) {
      out = new byte[len * 8];
      for (int i = 0; i < len; i++) { long v = ((long[]) a)[start + i]; put32(out, i * 8, (int) v); put32(out, i * 8 + 4, (int) (v >>> 32)); }
    } else if (a instanceof double[]) {
      out = new byte[len * 8];
      for (int i = 0; i < len; i++) { long v = Double.doubleToRawLongBits(((double[]) a)[start + i]); put32(out, i * 8, (int) v); put32(out, i * 8 + 4, (int) (v >>> 32)); }
    } else out = new byte[0];
    return Int8Array.copyFromJavaArray(out);
  }
  static void arraySet(int h, int start, Int8Array data) {
    Object a = get(h);
    byte[] b = data.copyToJavaArray();
    if (a instanceof byte[]) { System.arraycopy(b, 0, (byte[]) a, start, b.length); return; }
    if (a instanceof boolean[]) { for (int i = 0; i < b.length; i++) ((boolean[]) a)[start + i] = b[i] != 0; }
    else if (a instanceof int[]) { for (int i = 0; i < b.length / 4; i++) ((int[]) a)[start + i] = get32(b, i * 4); }
    else if (a instanceof float[]) { for (int i = 0; i < b.length / 4; i++) ((float[]) a)[start + i] = Float.intBitsToFloat(get32(b, i * 4)); }
    else if (a instanceof char[]) { for (int i = 0; i < b.length / 2; i++) ((char[]) a)[start + i] = (char) ((b[i * 2] & 0xff) | (b[i * 2 + 1] & 0xff) << 8); }
    else if (a instanceof short[]) { for (int i = 0; i < b.length / 2; i++) ((short[]) a)[start + i] = (short) ((b[i * 2] & 0xff) | (b[i * 2 + 1] & 0xff) << 8); }
    else if (a instanceof long[]) { for (int i = 0; i < b.length / 8; i++) ((long[]) a)[start + i] = (get32(b, i * 8) & 0xffffffffL) | ((long) get32(b, i * 8 + 4) << 32); }
    else if (a instanceof double[]) { for (int i = 0; i < b.length / 8; i++) ((double[]) a)[start + i] = Double.longBitsToDouble((get32(b, i * 8) & 0xffffffffL) | ((long) get32(b, i * 8 + 4) << 32)); }
  }
  static void put32(byte[] b, int o, int v) { b[o] = (byte) v; b[o + 1] = (byte) (v >> 8); b[o + 2] = (byte) (v >> 16); b[o + 3] = (byte) (v >> 24); }
  static int get32(byte[] b, int o) { return (b[o] & 0xff) | (b[o + 1] & 0xff) << 8 | (b[o + 2] & 0xff) << 16 | (b[o + 3] & 0xff) << 24; }
  static int newObjectArray(int len, int clsH, int initH) {
    tables();
    Class<?> c = cls(clsH);
    Integer id = c == null ? null : arrayIds.get(c.getName());
    Object[] a = id != null ? a2w.gen.JniTable.newArray(id, len) : new Object[len];
    Object init = get(initH);
    if (init != null) java.util.Arrays.fill(a, init);
    return handle(a);
  }
  static int getObjectElement(int h, int i) {
    Object a = get(h);
    try { return handle(((Object[]) a)[i]); } catch (Throwable t) { pending = t; return 0; }
  }
  static void setObjectElement(int h, int i, int v) {
    Object a = get(h);
    try { ((Object[]) a)[i] = get(v); } catch (Throwable t) { pending = t; }
  }

  static int elemSize(int h) {
    Object a = get(h);
    if (a instanceof byte[] || a instanceof boolean[]) return 1;
    if (a instanceof char[] || a instanceof short[]) return 2;
    if (a instanceof long[] || a instanceof double[]) return 8;
    return 4;
  }
  static int allocObject(int clsH) {
    Class<?> c = cls(clsH);
    Object o = c == null ? null : Reflection.construct(c, 0, null);
    if (o == null) { pending = new InstantiationException(className(clsH)); return 0; }
    return handle(o);
  }
  static int refType(int h) { return h <= 0 || h >= next || objs[h] == null ? 0 : global[h] ? 2 : 1; }
  /** PopLocalFrame(result): the result survives as a local of the enclosing frame */
  static int popFrame(int h) { Object o = get(h); exit(); return handle(o); }
  static void throwUnsatisfied(String msg) { pending = new a2w.jdk.UnsatisfiedLinkError(msg); }

  // ---- direct buffers: the page keeps a native-memory mirror per buffer object (library_jni.js)
  static final java.util.IdentityHashMap<Object, Integer> mirrors = new java.util.IdentityHashMap<>();
  static void bufferMirror(int h, int g) { Object o = get(h); if (o != null) mirrors.put(o, g); }
  static int bufferMirrorOf(int h) { Object o = get(h); Integer g = o == null ? null : mirrors.get(o); return g == null ? 0 : g; }
  static int newDirectBuffer(Int8Array data) {
    byte[] b = data.copyToJavaArray();
    java.nio.ByteBuffer bb = java.nio.ByteBuffer.allocateDirect(b.length).order(java.nio.ByteOrder.LITTLE_ENDIAN);
    bb.put(b);
    bb.clear();
    return handle(bb);
  }
  /** the native-memory address of a buffer's mirror (made now if it has none), as GetDirectBufferAddress */
  public static long nativeAddress(Object buffer) { return directAddress(handle(buffer)) & 0xFFFFFFFFL; }
  @org.teavm.jso.JSBody(params = "h", script = "return HW.jni.directAddress(h);")
  static native int directAddress(int h);
  static int bufferCapacity(int h) { Object o = get(h); return o instanceof java.nio.Buffer ? ((java.nio.Buffer) o).capacity() : -1; }
  /** a buffer's whole contents (0..capacity) as little-endian bytes */
  static Int8Array bufferBytes(int h) {
    Object o = get(h);
    byte[] b = Buffers.bytes(o, 0, -1);
    return b == null ? null : Int8Array.copyFromJavaArray(b);
  }
  static void bufferSet(int h, Int8Array data) { Buffers.set(get(h), 0, data.copyToJavaArray()); }

  // ---- exceptions
  static int throwObject(int h) { Object o = get(h); if (o instanceof Throwable) { pending = (Throwable) o; return 0; } return -1; }
  static int throwNew(int clsH, String msg) {
    tables();
    Class<?> c = cls(clsH);
    Integer id = c == null ? null : throwIds.get(c.getName());
    Throwable t = id != null ? a2w.gen.JniTable.newThrowable(id, msg) : null;
    pending = t != null ? t : new RuntimeException(className(clsH) + ": " + msg);
    return 0;
  }
  static int exceptionOccurred() { return handle(pending); }
  static void exceptionClear() { pending = null; }
  static void exceptionDescribe() { if (pending != null) Host.uncaught(pending); }
}
