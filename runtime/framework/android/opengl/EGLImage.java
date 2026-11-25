package android.opengl;

/** An EGL Image handle. */
public class EGLImage extends EGLObjectHandle {
  EGLImage(long handle) { super(handle); }
  @Override public boolean equals(Object o) { return this == o || o instanceof EGLImage && getNativeHandle() == ((EGLImage) o).getNativeHandle(); }
}
