package a2w.jdk.reflect;

/** base of the reflection stand-ins */
public class AccessibleObject {
  boolean accessible;
  public void setAccessible(boolean flag) { accessible = flag; }
  public boolean isAccessible() { return accessible; }
  public <A extends java.lang.annotation.Annotation> A getAnnotation(Class<A> c) { return null; }
  public boolean isAnnotationPresent(Class<? extends java.lang.annotation.Annotation> c) { return false; }
  public java.lang.annotation.Annotation[] getAnnotations() { return new java.lang.annotation.Annotation[0]; }
  public java.lang.annotation.Annotation[] getDeclaredAnnotations() { return new java.lang.annotation.Annotation[0]; }
}
