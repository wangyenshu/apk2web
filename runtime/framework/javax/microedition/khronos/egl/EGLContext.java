package javax.microedition.khronos.egl;
public abstract class EGLContext {
  public static EGL getEGL() { return EGLImpl.INSTANCE; }
  public abstract javax.microedition.khronos.opengles.GL getGL();
}
