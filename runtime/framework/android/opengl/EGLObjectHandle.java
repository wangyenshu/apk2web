package android.opengl;

/** Base class of the EGL objects of android.opengl.EGL14 (a handle to the page's EGL object). */
public abstract class EGLObjectHandle {
  private final long mHandle;
  @Deprecated protected EGLObjectHandle(int handle) { mHandle = handle; }
  protected EGLObjectHandle(long handle) { mHandle = handle; }
  @Deprecated public int getHandle() {
    if ((mHandle & 0xffffffffL) != mHandle) throw new UnsupportedOperationException();
    return (int) mHandle;
  }
  public long getNativeHandle() { return mHandle; }
  @Override public int hashCode() { return 17 * 31 + (int) (mHandle ^ (mHandle >>> 32)); }
}
