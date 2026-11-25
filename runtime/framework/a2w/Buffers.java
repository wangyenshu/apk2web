package a2w;

import java.nio.*;

/** java.nio buffers as little-endian bytes (what native code and WebGL see), and back. */
public final class Buffers {
  private Buffers() {}

  /** little-endian bytes of count elements from element index start (count -1: up to the capacity); null when o is
   *  not a buffer */
  public static byte[] bytes(Object o, int start, int count) {
    if (!(o instanceof Buffer)) return null;
    Buffer buf = (Buffer) o;
    int end = count < 0 ? buf.capacity() : start + count;
    int n = Math.max(0, end - start);
    if (o instanceof ByteBuffer) {
      ByteBuffer d = ((ByteBuffer) o).duplicate();
      d.clear(); d.position(start); d.limit(start + n);
      byte[] out = new byte[n];
      d.get(out);
      return out;
    }
    if (o instanceof FloatBuffer) {
      FloatBuffer d = ((FloatBuffer) o).duplicate(); d.clear();
      byte[] out = new byte[n * 4];
      for (int i = 0; i < n; i++) put32(out, i * 4, Float.floatToRawIntBits(d.get(start + i)));
      return out;
    }
    if (o instanceof IntBuffer) {
      IntBuffer d = ((IntBuffer) o).duplicate(); d.clear();
      byte[] out = new byte[n * 4];
      for (int i = 0; i < n; i++) put32(out, i * 4, d.get(start + i));
      return out;
    }
    if (o instanceof ShortBuffer) {
      ShortBuffer d = ((ShortBuffer) o).duplicate(); d.clear();
      byte[] out = new byte[n * 2];
      for (int i = 0; i < n; i++) { short v = d.get(start + i); out[i * 2] = (byte) v; out[i * 2 + 1] = (byte) (v >> 8); }
      return out;
    }
    if (o instanceof CharBuffer) {
      CharBuffer d = ((CharBuffer) o).duplicate(); d.clear();
      byte[] out = new byte[n * 2];
      for (int i = 0; i < n; i++) { char v = d.get(start + i); out[i * 2] = (byte) v; out[i * 2 + 1] = (byte) (v >> 8); }
      return out;
    }
    if (o instanceof LongBuffer) {
      LongBuffer d = ((LongBuffer) o).duplicate(); d.clear();
      byte[] out = new byte[n * 8];
      for (int i = 0; i < n; i++) { long v = d.get(start + i); put32(out, i * 8, (int) v); put32(out, i * 8 + 4, (int) (v >>> 32)); }
      return out;
    }
    if (o instanceof DoubleBuffer) {
      DoubleBuffer d = ((DoubleBuffer) o).duplicate(); d.clear();
      byte[] out = new byte[n * 8];
      for (int i = 0; i < n; i++) { long v = Double.doubleToRawLongBits(d.get(start + i)); put32(out, i * 8, (int) v); put32(out, i * 8 + 4, (int) (v >>> 32)); }
      return out;
    }
    return null;
  }
  /** bytes from the buffer's position to its limit (what GL calls take) */
  public static byte[] remaining(Object o) {
    if (!(o instanceof Buffer)) return null;
    Buffer b = (Buffer) o;
    return bytes(o, b.position(), b.limit() - b.position());
  }
  /** element size in bytes */
  public static int elementSize(Object o) {
    if (o instanceof ByteBuffer) return 1;
    if (o instanceof ShortBuffer || o instanceof CharBuffer) return 2;
    if (o instanceof LongBuffer || o instanceof DoubleBuffer) return 8;
    return 4;
  }
  /** write little-endian bytes into the buffer from element index start (absolute; position unchanged) */
  /** writes little-endian bytes into a buffer from element index start (absolute puts on a duplicate, so the
   *  buffer's position and limit stay; read-only buffers are left alone) */
  public static void set(Object o, int start, byte[] b) {
    if (!(o instanceof Buffer) || ((Buffer) o).isReadOnly()) return;
    if (o instanceof ByteBuffer) {
      ByteBuffer d = ((ByteBuffer) o).duplicate();
      d.clear(); d.position(start);
      d.put(b, 0, Math.min(b.length, d.remaining()));
    } else if (o instanceof FloatBuffer) {
      FloatBuffer d = ((FloatBuffer) o).duplicate(); d.clear();
      for (int i = 0; i * 4 + 3 < b.length && start + i < d.capacity(); i++) d.put(start + i, Float.intBitsToFloat(get32(b, i * 4)));
    } else if (o instanceof IntBuffer) {
      IntBuffer d = ((IntBuffer) o).duplicate(); d.clear();
      for (int i = 0; i * 4 + 3 < b.length && start + i < d.capacity(); i++) d.put(start + i, get32(b, i * 4));
    } else if (o instanceof ShortBuffer) {
      ShortBuffer d = ((ShortBuffer) o).duplicate(); d.clear();
      for (int i = 0; i * 2 + 1 < b.length && start + i < d.capacity(); i++) d.put(start + i, (short) ((b[i * 2] & 0xff) | b[i * 2 + 1] << 8));
    } else if (o instanceof CharBuffer) {
      CharBuffer d = ((CharBuffer) o).duplicate(); d.clear();
      for (int i = 0; i * 2 + 1 < b.length && start + i < d.capacity(); i++) d.put(start + i, (char) ((b[i * 2] & 0xff) | (b[i * 2 + 1] & 0xff) << 8));
    } else if (o instanceof LongBuffer) {
      LongBuffer d = ((LongBuffer) o).duplicate(); d.clear();
      for (int i = 0; i * 8 + 7 < b.length && start + i < d.capacity(); i++) d.put(start + i, (get32(b, i * 8) & 0xffffffffL) | ((long) get32(b, i * 8 + 4) << 32));
    } else if (o instanceof DoubleBuffer) {
      DoubleBuffer d = ((DoubleBuffer) o).duplicate(); d.clear();
      for (int i = 0; i * 8 + 7 < b.length && start + i < d.capacity(); i++)
        d.put(start + i, Double.longBitsToDouble((get32(b, i * 8) & 0xffffffffL) | ((long) get32(b, i * 8 + 4) << 32)));
    }
  }
  static void put32(byte[] b, int o, int v) { b[o] = (byte) v; b[o + 1] = (byte) (v >> 8); b[o + 2] = (byte) (v >> 16); b[o + 3] = (byte) (v >> 24); }
  static int get32(byte[] b, int o) { return (b[o] & 0xff) | (b[o + 1] & 0xff) << 8 | (b[o + 2] & 0xff) << 16 | (b[o + 3] & 0xff) << 24; }
}
