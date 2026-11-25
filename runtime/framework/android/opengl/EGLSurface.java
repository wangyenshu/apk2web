package android.opengl;

/** An EGL Surface handle. */
public class EGLSurface extends EGLObjectHandle {
  EGLSurface(long handle) { super(handle); }
  @Override public boolean equals(Object o) { return this == o || o instanceof EGLSurface && getNativeHandle() == ((EGLSurface) o).getNativeHandle(); }
}
