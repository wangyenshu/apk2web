package a2w;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Reflection without runtime metadata: the linker (tools/jarproc) generates a2w.gen.Reflect, which registers every
 * class the app can name (manifest components, layout tags, class-name strings, class literals) with the
 * constructors and methods reflection may reach, and dispatches calls to them by id.
 */
public final class Reflection {
  private Reflection() {}
  static HashMap<String, Class<?>> classes;
  static HashMap<String, Integer> ctors, methods;
  static HashMap<String, ArrayList<String>> methodNames;
  static HashMap<Integer, Class<?>[]> methodParams;
  static ArrayList<Class<?>[]> sigs;
  static HashMap<Class<?>, ArrayList<a2w.jdk.reflect.Field>> fields;
  /** int constants (R classes: thousands of them), packed by the linker and unpacked when the class is looked at */
  static HashMap<Class<?>, ArrayList<String>> packedInts;

  static void ensure() {
    if (classes != null) return;
    classes = new HashMap<>(); ctors = new HashMap<>(); methods = new HashMap<>(); methodNames = new HashMap<>();
    methodParams = new HashMap<>(); sigs = new ArrayList<>(); fields = new HashMap<>(); packedInts = new HashMap<>();
    a2w.gen.Reflect.register();
  }

  // ---- registration (called by the generated code)
  public static void sig(Class<?>[] params) { sigs.add(params); }
  public static void cls(String name, Class<?> c) { classes.put(name, c); }
  public static void ctor(Class<?> c, int sig, int id) { ctors.put(c.getName() + "#" + sig, id); }
  public static void method(Class<?> c, String name, Class<?>[] params, int id) {
    methods.put(c.getName() + "." + name + "/" + params.length, id);
    methods.putIfAbsent(c.getName() + "." + name + "/-1", id);
    methodParams.put(id, params);
    methodNames.computeIfAbsent(c.getName(), k -> new ArrayList<>()).add(name);
  }

  public static void field(Class<?> owner, String name, Class<?> type, int modifiers, int id, Object constant) {
    fields.computeIfAbsent(owner, k -> new ArrayList<>()).add(new a2w.jdk.reflect.Field(owner, name, type, modifiers, id, constant));
  }
  /** static final int constants of owner: "name:value(radix 36)[:modifiers(radix 36)]" entries separated by ';' */
  public static void intConstants(Class<?> owner, String packed) {
    packedInts.computeIfAbsent(owner, k -> new ArrayList<>()).add(packed);
  }
  private static void unpack(Class<?> c) {
    ArrayList<String> ps = packedInts.remove(c);
    if (ps == null) return;
    ArrayList<a2w.jdk.reflect.Field> l = fields.computeIfAbsent(c, k -> new ArrayList<>());
    for (String p : ps) {
      int i = 0, n = p.length();
      while (i < n) {
        int end = p.indexOf(';', i);
        if (end < 0) end = n;
        int a = p.indexOf(':', i), b = p.indexOf(':', a + 1);
        if (b < 0 || b > end) b = -1;
        String name = p.substring(i, a);
        int value = Integer.parseInt(p.substring(a + 1, b < 0 ? end : b), 36);
        int mod = b < 0 ? 0x19 : Integer.parseInt(p.substring(b + 1, end), 36);   // public static final
        l.add(new a2w.jdk.reflect.Field(c, name, int.class, mod, 0, value));
        i = end + 1;
      }
    }
  }
  /** the fields registered for c (declared in c), in declaration order */
  public static java.util.List<a2w.jdk.reflect.Field> declaredFields(Class<?> c) {
    ensure();
    if (packedInts.containsKey(c)) unpack(c);
    ArrayList<a2w.jdk.reflect.Field> l = fields.get(c);
    return l == null ? new ArrayList<>() : l;
  }

  // ---- lookups
  public static Class<?> forName(String name) throws ClassNotFoundException {
    ensure();
    Class<?> c = classes.get(name);
    if (c == null) {
      if (name.startsWith("[") || name.indexOf('/') >= 0) c = classes.get(name.replace('/', '.'));
      if (c == null) throw new ClassNotFoundException(name);
    }
    return c;
  }
  public static Class<?> forName(String name, boolean init, ClassLoader loader) throws ClassNotFoundException { return forName(name); }
  public static boolean known(String name) { ensure(); return classes.containsKey(name); }
  /** the names Class.forName can find (DexFile.entries) */
  public static String[] classNames() { ensure(); return classes.keySet().toArray(new String[0]); }

  public static int sigOf(Class<?>[] params) {
    ensure();
    if (params == null) params = new Class<?>[0];
    for (int i = 0; i < sigs.size(); i++) if (java.util.Arrays.equals(sigs.get(i), params)) return i;
    return -1;
  }
  public static int sigCount() { ensure(); return sigs.size(); }
  public static Class<?>[] sigParams(int s) { ensure(); return sigs.get(s).clone(); }
  public static boolean hasConstructor(Class<?> c, int sig) { ensure(); return c != null && ctors.containsKey(c.getName() + "#" + sig); }
  /** a new instance through constructor shape sig, or null when the linker generated none */
  public static Object construct(Class<?> c, int sig, Object[] args) {
    ensure();
    Integer id = c == null ? null : ctors.get(c.getName() + "#" + sig);
    if (id == null) {
      Host.log(5, "W/apk2web: no reflective constructor " + (c == null ? "null" : c.getName()) + " shape " + sig);
      return null;
    }
    return a2w.gen.Reflect.construct(id, args == null ? new Object[0] : args);
  }
  /** new instance of a class by name with constructor shape params (null when unknown) */
  public static Object newInstance(String name, Class<?>[] params, Object... args) {
    ensure();
    Class<?> c = classes.get(name);
    int s = sigOf(params);
    return c == null || s < 0 ? null : construct(c, s, args);
  }
  public static int method(Class<?> c, String name, int nparams) {
    ensure();
    Integer id = methods.get(c.getName() + "." + name + "/" + nparams);
    return id == null ? 0 : id;
  }
  public static String[] methodNames(Class<?> c) {
    ensure();
    ArrayList<String> l = methodNames.get(c.getName());
    return l == null ? new String[0] : l.toArray(new String[0]);
  }
  public static Class<?>[] methodParams(int id) { ensure(); Class<?>[] p = methodParams.get(id); return p == null ? new Class<?>[0] : p.clone(); }
  public static Object invoke(int id, Object target, Object[] args) { ensure(); return a2w.gen.Reflect.invoke(id, target, args); }
  /** a method by name taking the given argument count, called on target (onClick handlers) */
  public static boolean call(Object target, String name, Object... args) {
    ensure();
    for (Class<?> k = target.getClass(); k != null; k = k.getSuperclass()) {
      int id = method(k, name, args.length);
      if (id > 0) { a2w.gen.Reflect.invoke(id, target, args); return true; }
    }
    return false;
  }
}
