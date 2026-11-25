package a2w.jdk.reflect;

import a2w.Reflection;

/** java.lang.reflect.Constructor for the constructor shapes Android code instantiates by reflection. */
public final class Constructor<T> extends AccessibleObject {
  final Class<T> cls;
  final int sig;
  final Class<?>[] params;
  Constructor(Class<T> c, int sig, Class<?>[] params) { this.cls = c; this.sig = sig; this.params = params; }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public static Constructor find(Class<?> c, Class<?>[] params) {
    int sig = Reflection.sigOf(params);
    if (sig < 0 || !Reflection.hasConstructor(c, sig)) return null;
    return new Constructor(c, sig, params == null ? new Class<?>[0] : params);
  }
  @SuppressWarnings({"rawtypes"})
  public static Constructor[] all(Class<?> c) {
    java.util.ArrayList<Constructor> out = new java.util.ArrayList<>();
    for (int s = 0; s < Reflection.sigCount(); s++) if (Reflection.hasConstructor(c, s)) out.add(new Constructor<>(c, s, Reflection.sigParams(s)));
    return out.toArray(new Constructor[0]);
  }
  @SuppressWarnings("unchecked")
  public T newInstance(Object... args) throws InstantiationException, IllegalAccessException, InvocationTargetException {
    Object o;
    try {
      o = Reflection.construct(cls, sig, args);
    } catch (RuntimeException | Error e) {
      throw new InvocationTargetException(e);
    }
    if (o == null) throw new InstantiationException(cls.getName());
    return (T) o;
  }
  public Class<T> getDeclaringClass() { return cls; }
  public Class<?>[] getParameterTypes() { return params.clone(); }
  public int getParameterCount() { return params.length; }
  public int getModifiers() { return 1; }
  public String getName() { return cls.getName(); }
  @Override public String toString() { return "public " + cls.getName() + "(" + params.length + " params)"; }
}
