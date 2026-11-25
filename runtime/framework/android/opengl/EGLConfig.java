package android.opengl;

/** An EGL Config handle. */
public class EGLConfig extends EGLObjectHandle {
  EGLConfig(long handle) { super(handle); }
  @Override public boolean equals(Object o) { return this == o || o instanceof EGLConfig && getNativeHandle() == ((EGLConfig) o).getNativeHandle(); }
}
