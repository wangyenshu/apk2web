package a2w.jdk.nio;

import java.nio.*;

/**
 * java.nio.NIOAccess, the hidden class Android's GL bindings (and libGDX's libandroidgl20) call through JNI to reach a
 * buffer's memory: its native address (direct buffers, through the page's native mirror), or its array and offset.
 */
public final class NIOAccess {
  private NIOAccess() {}
  public static int shift(Buffer b) {
    if (b instanceof ByteBuffer) return 0;
    if (b instanceof ShortBuffer || b instanceof CharBuffer) return 1;
    if (b instanceof LongBuffer || b instanceof DoubleBuffer) return 3;
    return 2;
  }
  /** the address of the buffer's current position in native memory, or 0 for a buffer with a Java array */
  public static long getBasePointer(Buffer b) {
    if (b == null || b.hasArray()) return 0;
    long base = a2w.Jni.nativeAddress(b);
    return base == 0 ? 0 : base + ((long) b.position() << shift(b));
  }
  public static Object getBaseArray(Buffer b) { return b != null && b.hasArray() ? b.array() : null; }
  /** the byte offset of the current position in the array */
  public static int getBaseArrayOffset(Buffer b) { return b != null && b.hasArray() ? (b.arrayOffset() + b.position()) << shift(b) : 0; }
}
