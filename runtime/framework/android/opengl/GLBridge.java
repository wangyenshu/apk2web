package android.opengl;

import java.nio.Buffer;
import org.teavm.jso.JSBody;
import org.teavm.jso.typedarrays.Int32Array;
import org.teavm.jso.typedarrays.Int8Array;

/**
 * Data marshaling for the generated GL bindings: Java arrays and buffers become byte arrays for runtime/site/gles.js
 * (and are copied back for calls that return data). Client-side vertex arrays are read when drawing, as on a device,
 * so the buffers given to gl*Pointer are sent again before each draw call.
 */
final class GLBridge {
  private GLBridge() {}
  static int clientUnit = 0x84C0;

  static Int8Array in(Object array, int offset) {
    if (array == null) return null;
    byte[] b;
    if (array instanceof byte[]) { byte[] a = (byte[]) array; b = java.util.Arrays.copyOfRange(a, offset, a.length); }
    else if (array instanceof int[]) { int[] a = (int[]) array; b = new byte[(a.length - offset) * 4]; for (int i = 0; i < a.length - offset; i++) put32(b, i * 4, a[offset + i]); }
    else if (array instanceof float[]) { float[] a = (float[]) array; b = new byte[(a.length - offset) * 4]; for (int i = 0; i < a.length - offset; i++) put32(b, i * 4, Float.floatToRawIntBits(a[offset + i])); }
    else if (array instanceof short[]) { short[] a = (short[]) array; b = new byte[(a.length - offset) * 2]; for (int i = 0; i < a.length - offset; i++) { b[i * 2] = (byte) a[offset + i]; b[i * 2 + 1] = (byte) (a[offset + i] >> 8); } }
    else if (array instanceof boolean[]) { boolean[] a = (boolean[]) array; b = new byte[a.length - offset]; for (int i = 0; i < b.length; i++) b[i] = (byte) (a[offset + i] ? 1 : 0); }
    else if (array instanceof long[]) { long[] a = (long[]) array; b = new byte[(a.length - offset) * 8]; for (int i = 0; i < a.length - offset; i++) { put32(b, i * 8, (int) a[offset + i]); put32(b, i * 8 + 4, (int) (a[offset + i] >>> 32)); } }
    else if (array instanceof char[]) { char[] a = (char[]) array; b = new byte[(a.length - offset) * 2]; for (int i = 0; i < a.length - offset; i++) { b[i * 2] = (byte) a[offset + i]; b[i * 2 + 1] = (byte) (a[offset + i] >> 8); } }
    else return null;
    return Int8Array.copyFromJavaArray(b);
  }
  static Int8Array in(Buffer buf) {
    if (buf == null) return null;
    byte[] b = a2w.Buffers.remaining(buf);
    return b == null ? null : Int8Array.copyFromJavaArray(b);
  }
  static void out(Int8Array data, Object array, int offset) {
    if (data == null || array == null) return;
    byte[] b = data.copyToJavaArray();
    if (array instanceof byte[]) System.arraycopy(b, 0, array, offset, Math.min(b.length, ((byte[]) array).length - offset));
    else if (array instanceof int[]) { int[] a = (int[]) array; for (int i = 0; i * 4 + 3 < b.length && offset + i < a.length; i++) a[offset + i] = get32(b, i * 4); }
    else if (array instanceof float[]) { float[] a = (float[]) array; for (int i = 0; i * 4 + 3 < b.length && offset + i < a.length; i++) a[offset + i] = Float.intBitsToFloat(get32(b, i * 4)); }
    else if (array instanceof boolean[]) { boolean[] a = (boolean[]) array; for (int i = 0; i < b.length && offset + i < a.length; i++) a[offset + i] = b[i] != 0; }
    else if (array instanceof short[]) { short[] a = (short[]) array; for (int i = 0; i * 2 + 1 < b.length && offset + i < a.length; i++) a[offset + i] = (short) ((b[i * 2] & 0xff) | b[i * 2 + 1] << 8); }
    else if (array instanceof long[]) { long[] a = (long[]) array; for (int i = 0; i * 8 + 7 < b.length && offset + i < a.length; i++) a[offset + i] = (get32(b, i * 8) & 0xffffffffL) | ((long) get32(b, i * 8 + 4) << 32); }
  }
  static void out(Int8Array data, Buffer buf) {
    if (data == null || buf == null) return;
    a2w.Buffers.set(buf, buf.position(), data.copyToJavaArray());
  }
  static void put32(byte[] b, int o, int v) { b[o] = (byte) v; b[o + 1] = (byte) (v >> 8); b[o + 2] = (byte) (v >> 16); b[o + 3] = (byte) (v >> 24); }
  static int get32(byte[] b, int o) { return (b[o] & 0xff) | (b[o + 1] & 0xff) << 8 | (b[o + 2] & 0xff) << 16 | (b[o + 3] & 0xff) << 24; }

  // ---- shapes the core answers differently
  @JSBody(params = {"u", "p", "i"}, script = "return u ? HW.GLES.getActiveUniform(p, i) : HW.GLES.getActiveAttrib(p, i);")
  static native org.teavm.jso.JSObject activeInfoJS(boolean uniform, int p, int i);
  @JSBody(params = "o", script = "return o ? o.size : 0;") static native int infoSize(org.teavm.jso.JSObject o);
  @JSBody(params = "o", script = "return o ? o.type : 0;") static native int infoType(org.teavm.jso.JSObject o);
  @JSBody(params = "o", script = "return o ? o.name : null;") static native String infoName(org.teavm.jso.JSObject o);
  static String activeInfo(boolean uniform, int p, int i, int[] size, int sizeOff, int[] type, int typeOff) {
    org.teavm.jso.JSObject o = activeInfoJS(uniform, p, i);
    size[sizeOff] = infoSize(o);
    type[typeOff] = infoType(o);
    return infoName(o);
  }
  static String activeInfo(boolean uniform, int p, int i, java.nio.IntBuffer size, java.nio.IntBuffer type) {
    org.teavm.jso.JSObject o = activeInfoJS(uniform, p, i);
    size.put(size.position(), infoSize(o));
    type.put(type.position(), infoType(o));
    return infoName(o);
  }
  static void activeInfoC(boolean uniform, int p, int i, int bufsize, int[] length, int lengthOff, int[] size, int sizeOff,
      int[] type, int typeOff, byte[] name, int nameOff) {
    String n = activeInfo(uniform, p, i, size, sizeOff, type, typeOff);
    byte[] b = (n == null ? "" : n).getBytes(java.nio.charset.StandardCharsets.UTF_8);
    int k = Math.max(0, Math.min(b.length, bufsize - 1));
    System.arraycopy(b, 0, name, nameOff, k);
    if (nameOff + k < name.length) name[nameOff + k] = 0;
    if (length != null) length[lengthOff] = k;
  }
  static void shaderSourceC(int shader, int bufsize, int[] length, int lengthOff, byte[] source, int sourceOff) {
    String s = GLES20.glGetShaderSource(shader);
    byte[] b = (s == null ? "" : s).getBytes(java.nio.charset.StandardCharsets.UTF_8);
    int k = Math.max(0, Math.min(b.length, bufsize - 1));
    System.arraycopy(b, 0, source, sourceOff, k);
    if (sourceOff + k < source.length) source[sourceOff + k] = 0;
    if (length != null) length[lengthOff] = k;
  }

  // ---- client-side arrays: name (+ attribute index / texture unit) -> the call to repeat before drawing
  static final class Client { String name; int[] ints; Buffer buf; int pos, unit; }
  static final java.util.LinkedHashMap<String, Client> clients = new java.util.LinkedHashMap<>();
  static String key(String name, int index, int unit) {
    if (name.equals("vertexAttribPointer")) return name + index;
    if (name.equals("texCoordPointer")) return name + unit;
    return name;
  }
  static void client(String name, Object[] args) {
    Client c = new Client();
    c.name = name;
    c.unit = clientUnit;
    c.ints = new int[args.length - 1];
    for (int i = 0; i < args.length - 1; i++) {
      Object a = args[i];
      c.ints[i] = a instanceof Integer ? (Integer) a : a instanceof Boolean ? ((Boolean) a ? 1 : 0) : 0;
    }
    c.buf = (Buffer) args[args.length - 1];
    c.pos = c.buf == null ? 0 : c.buf.position();
    clients.put(key(name, c.ints.length > 0 ? c.ints[0] : 0, clientUnit), c);
  }
  static void unclient(String name, int index) { clients.remove(key(name, index, clientUnit)); }
  static void beforeDraw() {
    if (clients.isEmpty()) return;
    for (Client c : clients.values()) {
      if (c.buf == null) continue;
      byte[] b = a2w.Buffers.bytes(c.buf, c.pos, c.buf.limit() - c.pos);
      if (c.name.equals("texCoordPointer")) clientActive(c.unit);
      callPointer(c.name, Int32Array.copyFromJavaArray(c.ints), b == null ? null : Int8Array.copyFromJavaArray(b));
    }
    clientActive(clientUnit);
  }
  @JSBody(params = {"name", "ints", "data"}, script = "var a = Array.from(ints); a.push(data, 0); HW.GLES[name].apply(null, a);")
  static native void callPointer(String name, Int32Array ints, Int8Array data);
  @JSBody(params = "u", script = "if (HW.GLES.clientActiveTexture) HW.GLES.clientActiveTexture(u);")
  static native void clientActive(int u);

  // ---- GLES 3.0 shapes: mapped buffers (a copy of the range, written back on unmap or flush), string arrays, names
  static final class Mapped { java.nio.ByteBuffer buf; int offset, length, access; }
  static final java.util.HashMap<Integer, Mapped> mapped = new java.util.HashMap<>();
  @JSBody(params = {"t", "o", "u", "n"}, script = "HW.GLES.getBufferSubData(t, o, u, 0, n);")
  static native void getBufferSubData(int t, int o, Int8Array u, int n);
  @JSBody(params = {"t", "o", "n", "u"}, script = "HW.GLES.bufferSubData(t, o, n, u, 0);")
  static native void bufferSubData(int t, int o, int n, Int8Array u);
  @JSBody(params = "e", script = "HW.GLES.setError(e);")
  static native void setError(int e);
  static Buffer mapBufferRange(int target, int offset, int length, int access) {
    if (offset < 0 || length < 0 || (access & 3) == 0) { setError(0x501); return null; }
    if (mapped.containsKey(target)) { setError(0x502); return null; }
    java.nio.ByteBuffer b = java.nio.ByteBuffer.allocateDirect(length).order(java.nio.ByteOrder.nativeOrder());
    if ((access & 1) != 0 && length > 0) {   // GL_MAP_READ_BIT: the current contents
      Int8Array u = Int8Array.create(length);
      getBufferSubData(target, offset, u, length);
      b.put(u.copyToJavaArray());
      b.rewind();
    }
    Mapped m = new Mapped();
    m.buf = b; m.offset = offset; m.length = length; m.access = access;
    mapped.put(target, m);
    return b;
  }
  private static void upload(int target, Mapped m, int from, int length) {
    byte[] all = new byte[length];
    java.nio.ByteBuffer d = m.buf.duplicate();
    d.clear();
    d.position(from);
    d.get(all);
    bufferSubData(target, m.offset + from, length, Int8Array.copyFromJavaArray(all));
  }
  static boolean unmapBuffer(int target) {
    Mapped m = mapped.remove(target);
    if (m == null) { setError(0x502); return false; }
    if ((m.access & 2) != 0 && (m.access & 0x10) == 0 && m.length > 0) upload(target, m, 0, m.length);   // WRITE without FLUSH_EXPLICIT
    return true;
  }
  static void flushMappedBufferRange(int target, int offset, int length) {
    Mapped m = mapped.get(target);
    if (m == null || (m.access & 0x10) == 0) { setError(0x502); return; }
    if (offset < 0 || length < 0 || offset + length > m.length) { setError(0x501); return; }
    if (length > 0) upload(target, m, offset, length);
  }
  static Buffer bufferPointer(int target, int pname) {
    if (pname != 0x88BD) { setError(0x500); return null; }   // GL_BUFFER_MAP_POINTER
    Mapped m = mapped.get(target);
    return m == null ? null : m.buf;
  }
  @JSBody(params = {"p", "n", "m"}, script = "HW.GLES.transformFeedbackVaryings(p, n, m);")
  static native void varyingsJS(int p, String names, int mode);
  static void transformFeedbackVaryings(int program, String[] varyings, int bufferMode) { varyingsJS(program, String.join("\0", varyings), bufferMode); }
  @JSBody(params = {"p", "n"}, script = "return new Int32Array(HW.GLES.getUniformIndices(p, n));")
  static native org.teavm.jso.typedarrays.Int32Array indicesJS(int p, String names);
  static void uniformIndices(int program, String[] names, int[] indices, int offset) {
    org.teavm.jso.typedarrays.Int32Array a = indicesJS(program, String.join("\0", names));
    for (int i = 0; i < a.getLength() && offset + i < indices.length; i++) indices[offset + i] = a.get(i);
  }
  static void uniformIndices(int program, String[] names, java.nio.IntBuffer indices) {
    org.teavm.jso.typedarrays.Int32Array a = indicesJS(program, String.join("\0", names));
    for (int i = 0; i < a.getLength() && indices.position() + i < indices.limit(); i++) indices.put(indices.position() + i, a.get(i));
  }
  @JSBody(params = {"p", "i"}, script = "var r = HW.GLES.getActiveUniformBlockName(p, i); return r == null ? null : String(r);")
  static native String uniformBlockName(int program, int index);
  private static int putName(String s, int bufSize, byte[] dst, int off) {
    if (s == null || bufSize <= 0) return 0;
    byte[] b = s.getBytes(java.nio.charset.StandardCharsets.UTF_8);
    int n = Math.min(b.length, Math.min(bufSize - 1, dst.length - off - 1));
    System.arraycopy(b, 0, dst, off, n);
    dst[off + n] = 0;
    return n;
  }
  static void uniformBlockNameC(int program, int index, int bufSize, int[] length, int lengthOff, byte[] name, int nameOff) {
    int n = putName(uniformBlockName(program, index), bufSize, name, nameOff);
    if (length != null) length[lengthOff] = n;
  }
  static void uniformBlockNameB(int program, int index, Buffer length, Buffer name) {
    String s = uniformBlockName(program, index);
    byte[] b = new byte[Math.max(1, name.remaining())];
    int n = putName(s, b.length, b, 0);
    a2w.Buffers.set(name, name.position(), java.util.Arrays.copyOf(b, n + 1));
    if (length instanceof java.nio.IntBuffer) ((java.nio.IntBuffer) length).put(length.position(), n);
  }
  @JSBody(params = {"p", "i"}, script = "return HW.GLES.getTransformFeedbackVarying(p, i);")
  static native org.teavm.jso.JSObject varyingJS(int p, int i);
  static String feedbackVarying(int program, int index, int[] size, int sizeOff, int[] type, int typeOff) {
    org.teavm.jso.JSObject o = varyingJS(program, index);
    size[sizeOff] = infoSize(o);
    type[typeOff] = infoType(o);
    return infoName(o);
  }
  static String feedbackVarying(int program, int index, java.nio.IntBuffer size, java.nio.IntBuffer type) {
    org.teavm.jso.JSObject o = varyingJS(program, index);
    size.put(size.position(), infoSize(o));
    type.put(type.position(), infoType(o));
    return infoName(o);
  }
  static void feedbackVaryingC(int program, int index, int bufSize, int[] length, int lengthOff, int[] size, int sizeOff, int[] type, int typeOff, byte[] name, int nameOff) {
    String s = feedbackVarying(program, index, size, sizeOff, type, typeOff);
    int n = putName(s, bufSize, name, nameOff);
    if (length != null) length[lengthOff] = n;
  }
  static void feedbackVaryingB(int program, int index, int bufSize, java.nio.IntBuffer length, java.nio.IntBuffer size, java.nio.IntBuffer type, java.nio.ByteBuffer name) {
    String s = feedbackVarying(program, index, size, type);
    byte[] b = new byte[Math.max(1, Math.min(bufSize, name.remaining()))];
    int n = putName(s, b.length, b, 0);
    for (int i = 0; i <= n && i < b.length; i++) name.put(name.position() + i, b[i]);
    length.put(length.position(), n);
  }
  /** the old overload whose last parameter is a byte: writes the name's first byte */
  static void feedbackVaryingB(int program, int index, int bufSize, java.nio.IntBuffer length, java.nio.IntBuffer size, java.nio.IntBuffer type, byte name) {
    String s = feedbackVarying(program, index, size, type);
    length.put(length.position(), s == null ? 0 : Math.min(s.length(), Math.max(0, bufSize - 1)));
  }
  /** the debug message callback (GLES32 / KHR_debug): kept, never called */
  static Object debugCallback;
  @JSBody(params = "v", script = "HW.GLES.setClientVersion(v);")
  static native void setClientVersion(int v);
}
