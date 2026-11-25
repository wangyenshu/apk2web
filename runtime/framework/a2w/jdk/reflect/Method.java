package a2w.jdk.reflect;

import a2w.Reflection;

/** java.lang.reflect.Method for the methods the linker found named in strings (onClick handlers, getMethod args). */
public final class Method extends AccessibleObject {
  final int id;
  final Class<?> cls;
  final String name;
  Method(int id, Class<?> cls, String name) { this.id = id; this.cls = cls; this.name = name; }

  public static Method find(Class<?> c, String name, Class<?>[] params, boolean inherited) {
    int n = params == null ? 0 : params.length;
    for (Class<?> k = c; k != null; k = inherited ? k.getSuperclass() : null) {
      int id = Reflection.method(k, name, n);
      if (id > 0) return new Method(id, k, name);
    }
    return null;
  }
  public static Method[] all(Class<?> c, boolean inherited) {
    java.util.ArrayList<Method> out = new java.util.ArrayList<>();
    for (Class<?> k = c; k != null; k = inherited ? k.getSuperclass() : null)
      for (String n : Reflection.methodNames(k)) {
        int id = Reflection.method(k, n, -1);
        if (id > 0) out.add(new Method(id, k, n));
      }
    return out.toArray(new Method[0]);
  }
  public Object invoke(Object target, Object... args) throws IllegalAccessException, InvocationTargetException {
    try {
      return Reflection.invoke(id, target, args == null ? new Object[0] : args);
    } catch (RuntimeException | Error e) {
      throw new InvocationTargetException(e);
    }
  }
  public String getName() { return name; }
  public Class<?> getDeclaringClass() { return cls; }
  public Class<?>[] getParameterTypes() { return Reflection.methodParams(id); }
  public int getParameterCount() { return Reflection.methodParams(id).length; }
  public Class<?> getReturnType() { return Object.class; }
  public java.lang.reflect.Type getGenericReturnType() {
    java.lang.reflect.Type[] t = a2w.Generics.methodTypes(cls, name, getParameterCount());
    return t != null ? t[0] : getReturnType();
  }
  public java.lang.reflect.Type[] getGenericParameterTypes() {
    java.lang.reflect.Type[] t = a2w.Generics.methodTypes(cls, name, getParameterCount());
    return t != null ? java.util.Arrays.copyOfRange(t, 1, t.length) : getParameterTypes();
  }
  public int getModifiers() { return 1; }
  public <T extends java.lang.annotation.Annotation> T getAnnotation(Class<T> type) { return a2w.Annotations.get(cls, name, getParameterCount(), type); }
  public <T extends java.lang.annotation.Annotation> T getDeclaredAnnotation(Class<T> type) { return getAnnotation(type); }
  public boolean isAnnotationPresent(Class<? extends java.lang.annotation.Annotation> type) { return getAnnotation(type) != null; }
  public java.lang.annotation.Annotation[] getAnnotations() { return a2w.Annotations.of(cls, name, getParameterCount()); }
  public java.lang.annotation.Annotation[] getDeclaredAnnotations() { return getAnnotations(); }
  public java.lang.annotation.Annotation[][] getParameterAnnotations() { return new java.lang.annotation.Annotation[getParameterCount()][0]; }
  public boolean isBridge() { return false; }
  public boolean isSynthetic() { return false; }
  public boolean isVarArgs() { return false; }
  public boolean isDefault() { return false; }
  public Class<?>[] getExceptionTypes() { return new Class<?>[0]; }
  @Override public boolean equals(Object o) { return o instanceof Method && ((Method) o).id == id; }
  @Override public int hashCode() { return cls.getName().hashCode() ^ name.hashCode(); }
  @Override public String toString() { return cls.getName() + "." + name; }
}
