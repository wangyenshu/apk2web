package android.opengl;

/** An EGL Display handle. */
public class EGLDisplay extends EGLObjectHandle {
  EGLDisplay(long handle) { super(handle); }
  @Override public boolean equals(Object o) { return this == o || o instanceof EGLDisplay && getNativeHandle() == ((EGLDisplay) o).getNativeHandle(); }
}
