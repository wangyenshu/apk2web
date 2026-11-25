package android.opengl;

/** An EGL Context handle. */
public class EGLContext extends EGLObjectHandle {
  EGLContext(long handle) { super(handle); }
  @Override public boolean equals(Object o) { return this == o || o instanceof EGLContext && getNativeHandle() == ((EGLContext) o).getNativeHandle(); }
}
