package a2w;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;

/** Runtime annotations of methods and fields, registered by the linker's a2w.gen.Reflect (as generated instances). */
public final class Annotations {
  private Annotations() {}
  static final HashMap<String, ArrayList<Annotation>> BY_MEMBER = new HashMap<>();
  static final Annotation[] NONE = new Annotation[0];
  /** params: the method's parameter count, -1 for a field */
  public static void add(Class<?> owner, String member, int params, Object annotation) {
    if (annotation instanceof Annotation) BY_MEMBER.computeIfAbsent(owner.getName() + "." + member + "/" + params, k -> new ArrayList<>()).add((Annotation) annotation);
  }
  public static Annotation[] of(Class<?> owner, String member, int params) {
    Reflection.ensure();
    ArrayList<Annotation> l = BY_MEMBER.get(owner.getName() + "." + member + "/" + params);
    return l == null ? NONE : l.toArray(new Annotation[0]);
  }
  @SuppressWarnings("unchecked")
  public static <T extends Annotation> T get(Class<?> owner, String member, int params, Class<T> type) {
    for (Annotation a : of(owner, member, params)) if (a.annotationType() == type) return (T) a;
    return null;
  }
}
