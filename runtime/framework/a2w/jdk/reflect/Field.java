package a2w.jdk.reflect;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Modifier;

/**
 * java.lang.reflect.Field for the fields the linker registered (a2w.gen.Reflect: those of the classes an app can
 * name, and its R classes); reads and writes go through generated code by id. static final constants carry their value.
 */
public final class Field extends AccessibleObject {
  private final Class<?> decl, type;
  private final String name;
  private final int modifiers, id;
  private final Object constant;

  public Field(Class<?> decl, String name, Class<?> type, int modifiers, int id, Object constant) {
    this.decl = decl; this.name = name; this.type = type; this.modifiers = modifiers; this.id = id; this.constant = constant;
  }

  /** Class.getField: a public field of c, its superclasses or interfaces */
  public static Field find(Class<?> c, String name) throws NoSuchFieldException {
    for (Class<?> k = c; k != null; k = k.getSuperclass()) {
      for (Field f : a2w.Reflection.declaredFields(k)) if (f.name.equals(name) && Modifier.isPublic(f.modifiers)) return f;
      for (Class<?> i : k.getInterfaces()) for (Field f : a2w.Reflection.declaredFields(i)) if (f.name.equals(name)) return f;
    }
    throw missing(c, name);
  }
  /** Class.getDeclaredField */
  public static Field findDeclared(Class<?> c, String name) throws NoSuchFieldException {
    for (Field f : a2w.Reflection.declaredFields(c)) if (f.name.equals(name)) return f;
    throw missing(c, name);
  }
  private static NoSuchFieldException missing(Class<?> c, String name) {
    a2w.Stubs.hit("java.lang.reflect.Field " + c.getName() + "." + name);
    return new NoSuchFieldException(name);
  }
  public static Field[] all(Class<?> c, boolean declared) {
    List<Field> out = new ArrayList<>();
    if (declared) out.addAll(a2w.Reflection.declaredFields(c));
    else
      for (Class<?> k = c; k != null; k = k.getSuperclass())
        for (Field f : a2w.Reflection.declaredFields(k)) if (Modifier.isPublic(f.modifiers)) out.add(f);
    return out.toArray(new Field[0]);
  }

  public Object get(Object o) throws IllegalAccessException {
    if (constant != null) return constant;
    if (!Modifier.isStatic(modifiers)) {
      if (o == null) throw new NullPointerException("field " + name + " of a null object");
      if (!decl.isInstance(o)) throw new IllegalArgumentException("not an instance of " + decl.getName());
    }
    return a2w.gen.Reflect.fieldGet(id, o);
  }
  public void set(Object o, Object v) throws IllegalAccessException {
    if (Modifier.isFinal(modifiers) && Modifier.isStatic(modifiers)) throw new IllegalAccessException("final field " + name);
    if (!Modifier.isStatic(modifiers) && o == null) throw new NullPointerException("field " + name + " of a null object");
    if (type.isPrimitive() && v == null) throw new IllegalArgumentException("null for primitive field " + name);
    a2w.gen.Reflect.fieldSet(id, o, convert(v));
  }
  /** widening, as Field.set does for boxed primitives (an Integer into a long field) */
  private Object convert(Object v) {
    if (!type.isPrimitive() || v == null) return v;
    if (v instanceof Number n && !(v.getClass() == box(type))) {
      if (type == long.class) return n.longValue();
      if (type == int.class) return n.intValue();
      if (type == float.class) return n.floatValue();
      if (type == double.class) return n.doubleValue();
      if (type == short.class) return n.shortValue();
      if (type == byte.class) return n.byteValue();
    }
    if (v instanceof Character ch && type != char.class) return convert((int) ch.charValue());
    return v;
  }
  private static Class<?> box(Class<?> p) {
    if (p == int.class) return Integer.class;
    if (p == long.class) return Long.class;
    if (p == float.class) return Float.class;
    if (p == double.class) return Double.class;
    if (p == short.class) return Short.class;
    if (p == byte.class) return Byte.class;
    if (p == char.class) return Character.class;
    if (p == boolean.class) return Boolean.class;
    return p;
  }
  private Number num(Object o) throws IllegalAccessException {
    Object v = get(o);
    if (v instanceof Number n) return n;
    if (v instanceof Character c) return (int) c.charValue();
    throw new IllegalArgumentException("field " + name + " is not numeric");
  }
  public int getInt(Object o) throws IllegalAccessException { return num(o).intValue(); }
  public long getLong(Object o) throws IllegalAccessException { return num(o).longValue(); }
  public float getFloat(Object o) throws IllegalAccessException { return num(o).floatValue(); }
  public double getDouble(Object o) throws IllegalAccessException { return num(o).doubleValue(); }
  public short getShort(Object o) throws IllegalAccessException { return num(o).shortValue(); }
  public byte getByte(Object o) throws IllegalAccessException { return num(o).byteValue(); }
  public char getChar(Object o) throws IllegalAccessException { Object v = get(o); return v instanceof Character c ? c : (char) num(o).intValue(); }
  public boolean getBoolean(Object o) throws IllegalAccessException {
    Object v = get(o);
    if (v instanceof Boolean b) return b;
    if (v instanceof Integer i) return i != 0;   // a boolean constant in the class file
    throw new IllegalArgumentException("field " + name + " is not boolean");
  }
  public void setInt(Object o, int v) throws IllegalAccessException { set(o, v); }
  public void setLong(Object o, long v) throws IllegalAccessException { set(o, v); }
  public void setFloat(Object o, float v) throws IllegalAccessException { set(o, v); }
  public void setDouble(Object o, double v) throws IllegalAccessException { set(o, v); }
  public void setBoolean(Object o, boolean v) throws IllegalAccessException { set(o, v); }
  public void setShort(Object o, short v) throws IllegalAccessException { set(o, v); }
  public void setByte(Object o, byte v) throws IllegalAccessException { set(o, v); }
  public void setChar(Object o, char v) throws IllegalAccessException { set(o, v); }
  public String getName() { return name; }
  public Class<?> getType() { return type; }
  public java.lang.reflect.Type getGenericType() { return a2w.Generics.fieldType(decl, name, type); }
  public Class<?> getDeclaringClass() { return decl; }
  public int getModifiers() { return modifiers; }
  public <T extends java.lang.annotation.Annotation> T getAnnotation(Class<T> t) { return a2w.Annotations.get(decl, name, -1, t); }
  public <T extends java.lang.annotation.Annotation> T getDeclaredAnnotation(Class<T> t) { return getAnnotation(t); }
  public boolean isAnnotationPresent(Class<? extends java.lang.annotation.Annotation> t) { return getAnnotation(t) != null; }
  public java.lang.annotation.Annotation[] getAnnotations() { return a2w.Annotations.of(decl, name, -1); }
  public java.lang.annotation.Annotation[] getDeclaredAnnotations() { return getAnnotations(); }
  public boolean isSynthetic() { return false; }
  public boolean isEnumConstant() { return (modifiers & 0x4000) != 0; }
  @Override public boolean equals(Object o) { return o instanceof Field f && f.decl == decl && f.name.equals(name); }
  @Override public int hashCode() { return decl.getName().hashCode() ^ name.hashCode(); }
  @Override public String toString() { return Modifier.toString(modifiers) + " " + type.getName() + " " + decl.getName() + "." + name; }
}
