package a2w;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Generic type information (Class.getGenericSuperclass, Field.getGenericType ...) from the class files' Signature
 * attributes, which the linker registers for the classes and members reflection can reach (a2w.gen.Reflect).
 */
public final class Generics {
  private Generics() {}
  static final HashMap<Class<?>, String> CLASS_SIGS = new HashMap<>();
  static final HashMap<String, String> MEMBER_SIGS = new HashMap<>();   // "owner.name" (fields) or "owner.name/n" (methods)

  public static void classSig(Class<?> c, String sig) { CLASS_SIGS.put(c, sig); }
  public static void fieldSig(Class<?> owner, String name, String sig) { MEMBER_SIGS.put(owner.getName() + "." + name, sig); }
  public static void methodSig(Class<?> owner, String name, int params, String sig) { MEMBER_SIGS.put(owner.getName() + "." + name + "/" + params, sig); }

  // ---------------------------------------------------------------- queries
  static final class ClassSig { TypeVariable<?>[] params; Type superclass; Type[] interfaces; }
  static final HashMap<Class<?>, ClassSig> PARSED = new HashMap<>();
  static ClassSig parsed(Class<?> c) {
    Reflection.ensure();
    ClassSig cs = PARSED.get(c);
    if (cs != null) return cs;
    String s = CLASS_SIGS.get(c);
    if (s == null) return null;
    try {
      Parser p = new Parser(s, c);
      cs = new ClassSig();
      cs.params = p.formals(c);
      cs.superclass = p.fieldType();
      ArrayList<Type> is = new ArrayList<>();
      while (!p.done()) is.add(p.fieldType());
      cs.interfaces = is.toArray(new Type[0]);
    } catch (RuntimeException e) {
      Host.log(5, "W/apk2web: bad generic signature of " + c.getName() + ": " + s);
      return null;
    }
    PARSED.put(c, cs);
    return cs;
  }
  public static Type superclass(Class<?> c) {
    if (c.isInterface() || c.isPrimitive() || c.isArray() || c == Object.class) return c.isArray() ? Object.class : null;
    ClassSig s = parsed(c);
    return s != null ? s.superclass : c.getSuperclass();
  }
  public static Type[] interfaces(Class<?> c) {
    ClassSig s = parsed(c);
    if (s != null && s.interfaces.length == c.getInterfaces().length) return s.interfaces.clone();
    return c.getInterfaces();
  }
  public static TypeVariable<?>[] typeParameters(Class<?> c) {
    ClassSig s = parsed(c);
    return s != null ? s.params.clone() : new TypeVariable<?>[0];
  }
  public static Type fieldType(Class<?> owner, String name, Class<?> raw) {
    Reflection.ensure();
    String s = MEMBER_SIGS.get(owner.getName() + "." + name);
    if (s == null) return raw;
    try { return new Parser(s, owner).fieldType(); } catch (RuntimeException e) { return raw; }
  }
  /** {return type, parameter types...} of a method signature, or null */
  public static Type[] methodTypes(Class<?> owner, String name, int params) {
    Reflection.ensure();
    String s = MEMBER_SIGS.get(owner.getName() + "." + name + "/" + params);
    if (s == null) return null;
    try {
      Parser p = new Parser(s, owner);
      TypeVariable<?>[] own = p.formals(null);
      p.scope = own;
      p.expect('(');
      ArrayList<Type> ps = new ArrayList<>();
      while (p.peek() != ')') ps.add(p.type());
      p.expect(')');
      Type ret = p.peek() == 'V' ? void.class : p.type();
      ps.add(0, ret);
      return ps.toArray(new Type[0]);
    } catch (RuntimeException e) { return null; }
  }

  // ---------------------------------------------------------------- the signature grammar (JVMS 4.7.9.1)
  static final class Parser {
    final String s;
    int i;
    final Class<?> context;
    TypeVariable<?>[] scope = new TypeVariable<?>[0];
    Parser(String s, Class<?> context) { this.s = s; this.context = context; }
    boolean done() { return i >= s.length(); }
    char peek() { return s.charAt(i); }
    void expect(char c) { if (s.charAt(i) != c) throw new IllegalArgumentException("expected " + c + " at " + i + " in " + s); i++; }
    TypeVariable<?>[] formals(GenericDeclaration decl) {
      if (done() || peek() != '<') return new TypeVariable<?>[0];
      i++;
      ArrayList<Var> vs = new ArrayList<>();
      ArrayList<int[]> boundsAt = new ArrayList<>();
      while (peek() != '>') {
        int c = s.indexOf(':', i);
        Var v = new Var(s.substring(i, c), decl);
        i = c;
        int start = i;
        while (peek() == ':') { i++; if (peek() != ':') skipType(); }
        boundsAt.add(new int[] {start, i});
        vs.add(v);
      }
      i++;
      TypeVariable<?>[] out = vs.toArray(new TypeVariable<?>[0]);
      TypeVariable<?>[] outer = scope;
      scope = out;
      int save = i;
      for (int k = 0; k < vs.size(); k++) {
        i = boundsAt.get(k)[0];
        ArrayList<Type> b = new ArrayList<>();
        while (i < boundsAt.get(k)[1]) { i++; if (i < boundsAt.get(k)[1] && peek() != ':') b.add(fieldType()); }
        vs.get(k).bounds = b.isEmpty() ? new Type[] {Object.class} : b.toArray(new Type[0]);
      }
      i = save;
      if (outer.length > 0) { TypeVariable<?>[] all = Arrays.copyOf(out, out.length + outer.length); System.arraycopy(outer, 0, all, out.length, outer.length); scope = all; }
      return out;
    }
    void skipType() {
      char c = peek();
      if (c == 'L') { int depth = 0; while (true) { char d = s.charAt(i++); if (d == '<') depth++; else if (d == '>') depth--; else if (d == ';' && depth == 0) return; } }
      if (c == 'T') { i = s.indexOf(';', i) + 1; return; }
      if (c == '[') { i++; skipType(); return; }
      i++;
    }
    Type fieldType() { return type(); }
    Type type() {
      char c = s.charAt(i);
      switch (c) {
        case 'B': i++; return byte.class;
        case 'C': i++; return char.class;
        case 'D': i++; return double.class;
        case 'F': i++; return float.class;
        case 'I': i++; return int.class;
        case 'J': i++; return long.class;
        case 'S': i++; return short.class;
        case 'Z': i++; return boolean.class;
        case 'V': i++; return void.class;
        case '[': {
          i++;
          Type comp = type();
          if (comp instanceof Class) return java.lang.reflect.Array.newInstance((Class<?>) comp, 0).getClass();
          return new ArrayType(comp);
        }
        case 'T': {
          int e = s.indexOf(';', i);
          String name = s.substring(i + 1, e);
          i = e + 1;
          for (TypeVariable<?> v : scope) if (v.getName().equals(name)) return v;
          // a type variable of an enclosing class
          for (Class<?> k = context; k != null; k = k.getEnclosingClass())
            for (TypeVariable<?> v : typeParameters(k)) if (v.getName().equals(name)) return v;
          return new Var(name, context);
        }
        case 'L': return classType();
        default: throw new IllegalArgumentException("bad signature char " + c + " in " + s);
      }
    }
    Type classType() {
      expect('L');
      StringBuilder name = new StringBuilder();
      Type owner = null;
      while (true) {
        int st = i;
        while (peek() != '<' && peek() != ';' && peek() != '.') i++;
        name.append(s, st, i);
        Class<?> raw = load(name.toString());
        Type t = raw;
        if (peek() == '<') {
          i++;
          ArrayList<Type> args = new ArrayList<>();
          while (peek() != '>') {
            char c = peek();
            if (c == '*') { i++; args.add(new Wildcard(new Type[] {Object.class}, new Type[0])); }
            else if (c == '+') { i++; args.add(new Wildcard(new Type[] {type()}, new Type[0])); }
            else if (c == '-') { i++; args.add(new Wildcard(new Type[] {Object.class}, new Type[] {type()})); }
            else args.add(type());
          }
          i++;
          t = new Param(raw, args.toArray(new Type[0]), owner);
        } else if (owner instanceof ParameterizedType) t = new Param(raw, new Type[0], owner);
        if (peek() == '.') { i++; owner = t; name.append('$'); continue; }
        expect(';');
        return t;
      }
    }
    static Class<?> load(String internal) {
      String n = internal.replace('/', '.');
      try { return Reflection.forName(n); } catch (ClassNotFoundException e) { /* not registered */ }
      switch (n) {
        case "java.lang.Object": return Object.class;
        case "java.lang.String": return String.class;
        case "java.lang.Integer": return Integer.class;
        case "java.lang.Long": return Long.class;
        case "java.lang.Boolean": return Boolean.class;
        case "java.lang.Double": return Double.class;
        case "java.lang.Float": return Float.class;
        case "java.util.List": return java.util.List.class;
        case "java.util.Map": return java.util.Map.class;
        case "java.util.Set": return java.util.Set.class;
        case "java.util.Collection": return java.util.Collection.class;
        default:
          Host.log(5, "W/apk2web: generic signature names an unregistered class " + n);
          return Object.class;
      }
    }
  }

  // ---------------------------------------------------------------- the Type implementations (equal to the JDK's)
  static String name(Type t) { return t instanceof Class ? ((Class<?>) t).getName() : t.toString(); }

  public static final class Param implements ParameterizedType {
    final Class<?> raw;
    final Type[] args;
    final Type owner;
    Param(Class<?> raw, Type[] args, Type owner) { this.raw = raw; this.args = args; this.owner = owner != null ? owner : raw.getEnclosingClass(); }
    @Override public Type[] getActualTypeArguments() { return args.clone(); }
    @Override public Type getRawType() { return raw; }
    @Override public Type getOwnerType() { return owner; }
    @Override public boolean equals(Object o) {
      if (!(o instanceof ParameterizedType)) return false;
      ParameterizedType p = (ParameterizedType) o;
      return raw.equals(p.getRawType()) && java.util.Objects.equals(owner, p.getOwnerType()) && Arrays.equals(args, p.getActualTypeArguments());
    }
    @Override public int hashCode() { return Arrays.hashCode(args) ^ java.util.Objects.hashCode(owner) ^ raw.hashCode(); }
    @Override public String toString() {
      StringBuilder b = new StringBuilder();
      if (owner != null) {
        b.append(name(owner)).append('$');
        String n = raw.getName();
        int d = n.lastIndexOf('$');
        b.append(d >= 0 ? n.substring(d + 1) : n);
      } else b.append(raw.getName());
      if (args.length > 0) {
        b.append('<');
        for (int k = 0; k < args.length; k++) b.append(k == 0 ? "" : ", ").append(args[k].getTypeName());
        b.append('>');
      }
      return b.toString();
    }
  }
  public static final class ArrayType implements GenericArrayType {
    final Type comp;
    ArrayType(Type comp) { this.comp = comp; }
    @Override public Type getGenericComponentType() { return comp; }
    @Override public boolean equals(Object o) { return o instanceof GenericArrayType && comp.equals(((GenericArrayType) o).getGenericComponentType()); }
    @Override public int hashCode() { return comp.hashCode(); }
    @Override public String toString() { return comp.getTypeName() + "[]"; }
  }
  public static final class Wildcard implements WildcardType {
    final Type[] upper, lower;
    Wildcard(Type[] upper, Type[] lower) { this.upper = upper; this.lower = lower; }
    @Override public Type[] getUpperBounds() { return upper.clone(); }
    @Override public Type[] getLowerBounds() { return lower.clone(); }
    @Override public boolean equals(Object o) {
      return o instanceof WildcardType && Arrays.equals(upper, ((WildcardType) o).getUpperBounds()) && Arrays.equals(lower, ((WildcardType) o).getLowerBounds());
    }
    @Override public int hashCode() { return Arrays.hashCode(upper) ^ Arrays.hashCode(lower); }
    @Override public String toString() {
      if (lower.length > 0) return "? super " + lower[0].getTypeName();
      if (upper.length == 0 || upper[0] == Object.class) return "?";
      return "? extends " + upper[0].getTypeName();
    }
  }
  @SuppressWarnings({"rawtypes", "unchecked"})
  public static final class Var implements TypeVariable {
    final String name;
    final GenericDeclaration decl;
    Type[] bounds = {Object.class};
    Var(String name, GenericDeclaration decl) { this.name = name; this.decl = decl; }
    @Override public Type[] getBounds() { return bounds.clone(); }
    @Override public GenericDeclaration getGenericDeclaration() { return decl; }
    @Override public String getName() { return name; }
    @Override public java.lang.reflect.AnnotatedType[] getAnnotatedBounds() { return null; }
    @Override public java.lang.annotation.Annotation getAnnotation(Class c) { return null; }
    @Override public java.lang.annotation.Annotation[] getAnnotations() { return new java.lang.annotation.Annotation[0]; }
    @Override public java.lang.annotation.Annotation[] getDeclaredAnnotations() { return new java.lang.annotation.Annotation[0]; }
    @Override public boolean equals(Object o) {
      return o instanceof TypeVariable && name.equals(((TypeVariable) o).getName()) && java.util.Objects.equals(decl, ((TypeVariable) o).getGenericDeclaration());
    }
    @Override public int hashCode() { return (decl == null ? 0 : decl.hashCode()) ^ name.hashCode(); }
    @Override public String toString() { return name; }
  }
}
