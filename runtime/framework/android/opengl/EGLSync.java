package android.opengl;

/** An EGL Sync handle. */
public class EGLSync extends EGLObjectHandle {
  EGLSync(long handle) { super(handle); }
  @Override public boolean equals(Object o) { return this == o || o instanceof EGLSync && getNativeHandle() == ((EGLSync) o).getNativeHandle(); }
}
