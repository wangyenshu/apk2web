package jp;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.zip.ZipFile;

/**
 * Java sources for the API classes the runtime lacks (runtime/framework-gen), declared as the SDK declares them:
 * kind, modifiers, generics, supertypes, constants, fields, constructors, methods and nested classes, at every API
 * level (members removed later included). Abstract and interface methods, constants and plain fields are complete
 * as declared; other bodies call a2w.Stubs.hit and return a default, and carry @a2w.NotImplemented (docs/API.md
 * counts them as missing). Classes written by hand in runtime/framework are never generated: implementing a class
 * means moving its file there and filling it in.
 *
 * Usage: GenApi --fw framework-classes --jdk teavm-classlib.jar [--lib jar]... [--applib jar]... --versions
 *        api-versions.xml --sdk-jar android.jar... (newest first) --out runtime/framework-gen
 */
public final class GenApi {
  final ApiReport R = new ApiReport();
  final List<Map<String, ClassNode>> sdk = new ArrayList<>();
  final Map<String, VClass> gen = new TreeMap<>();
  final Map<String, VClass> universe = new HashMap<>();
  final List<String> problems = new ArrayList<>();

  public static void main(String[] a) throws Exception { new GenApi().run(a); }

  void run(String[] a) throws Exception {
    String fw = null, jdk = null, versions = null, out = null;
    List<String> libs = new ArrayList<>(), appLibs = new ArrayList<>(), jars = new ArrayList<>();
    for (int i = 0; i < a.length; i++) {
      switch (a[i]) {
        case "--fw": fw = a[++i]; break;
        case "--jdk": jdk = a[++i]; break;
        case "--lib": libs.add(a[++i]); break;
        case "--applib": appLibs.add(a[++i]); break;
        case "--versions": versions = a[++i]; break;
        case "--sdk-jar": jars.add(a[++i]); break;
        case "--out": out = a[++i]; break;
        case "--absent": readAbsent(Paths.get(a[++i])); break;
        default: throw new IllegalArgumentException(a[i]);
      }
    }
    R.load(fw, jdk, libs, appLibs);
    for (String j : jars) {
      Map<String, ClassNode> m = new HashMap<>();
      try (ZipFile z = new ZipFile(j)) {
        for (var e = z.entries(); e.hasMoreElements(); ) {
          var ze = e.nextElement();
          if (!ze.getName().endsWith(".class")) continue;
          ClassNode cn = Link.read(z.getInputStream(ze).readAllBytes());
          if (ApiReport.visible(cn.access) && (cn.access & Opcodes.ACC_SYNTHETIC) == 0) m.put(cn.name, cn);
        }
      }
      sdk.add(m);
    }
    List<VClass> api = Versions.read(Paths.get(versions));
    Versions.merge(api, sdk.get(0).values());
    for (VClass c : api) universe.put(c.name, c);
    // classes the runtime lacks, and those generated before (runtime/framework-gen is compiled with the framework)
    for (VClass c : api) {
      String pv = R.provider(c.name);
      Link.CI ci = pv == null ? null : R.L.world.get(pv);
      if ((pv == null || ci != null && ci.node != null && ApiReport.generated(ci.node)) && !skip(c.name)) gen.put(c.name, c);
    }
    // a generated class's own name is no runtime class (it is regenerated)
    for (String n : gen.keySet()) { String t = genName(n); Link.CI ci = R.L.world.get(t); if (ci != null && ci.node != null && ApiReport.generated(ci.node)) R.L.world.remove(t); }
    // a class whose types cannot all be named is left to the linker's stubs
    boolean changed = true;
    while (changed) {
      changed = false;
      for (Iterator<VClass> it = gen.values().iterator(); it.hasNext(); ) {
        VClass c = it.next();
        String bad = unresolvable(c);
        if (bad != null) { problems.add("skipped " + c.name + ": refers to " + bad); it.remove(); changed = true; continue; }
        ClassNode jn = node(c.name);
        String sup = jn != null ? jn.superName : c.sup;
        if (jn != null && (jn.access & (Opcodes.ACC_INTERFACE | Opcodes.ACC_ENUM)) != 0 || sup == null || has(sup)) continue;
        if (superCall(c.name, sup, null) == null) { problems.add("skipped " + c.name + ": no constructor of " + sup + " it can call"); it.remove(); changed = true; }
      }
    }
    for (String n : gen.keySet()) rev.put(genName(n), n);
    plan();
    Path root = Paths.get(out);
    if (Files.isDirectory(root)) try (var w = Files.walk(root)) {
      List<Path> all = new ArrayList<>(); w.forEach(all::add);
      Collections.reverse(all);
      for (Path p : all) Files.delete(p);
    }
    int files = 0;
    for (VClass c : gen.values()) {
      if (has(outer(c.name))) continue;   // written inside its enclosing class
      String t = target(c.name);
      Path f = root.resolve(t + ".java");
      Files.createDirectories(f.getParent());
      StringBuilder b = new StringBuilder();
      String pkg = t.contains("/") ? t.substring(0, t.lastIndexOf('/')).replace('/', '.') : "";
      b.append("// Generated by tools/genapi.sh from the Android SDK's declaration. Do not edit: to implement this class,\n")
          .append("// move it to runtime/framework and fill in the bodies marked @a2w.NotImplemented.\n");
      if (!pkg.isEmpty()) b.append("package ").append(pkg).append(";\n\n");
      emitClass(b, c, "", true);
      Files.writeString(f, b.toString());
      files++;
    }
    Files.writeString(root.resolve("problems.txt"), String.join("\n", problems) + "\n");
    System.out.printf("%d classes generated in %d files; %d problems (%s)%n", gen.size(), files, problems.size(), root.resolve("problems.txt"));
  }

  // ================================================================ names
  static boolean skip(String n) { return false; }
  boolean has(String n) { return n != null && gen.containsKey(n); }
  static String outer(String n) { int i = n.lastIndexOf('$'); return i < 0 ? null : n.substring(0, i); }

  /** where a generated class lives: java.* and JDK javax.* packages cannot be declared by the framework */
  static String genName(String n) {
    if (n.startsWith("java/")) return "a2w/jdk/" + n.substring(5);
    if (n.startsWith("javax/xml/")) return "a2w/xml/" + n.substring(10);
    if (n.startsWith("org/xml/sax/")) return "a2w/xml/sax/" + n.substring(12);
    if (n.startsWith("org/w3c/dom/")) return "a2w/xml/dom/" + n.substring(12);
    if (n.startsWith("javax/crypto/")) return "a2w/jdk/crypto/" + n.substring(13);
    if (n.startsWith("javax/") && !n.startsWith("javax/microedition/")) return "a2w/jdk/javax/" + n.substring(6);
    return n;
  }
  /** the runtime class a type of the SDK names (existing or generated); null: none */
  String target(String n) {
    if (has(n)) return genName(n);
    return R.provider(n);
  }
  /** names are the host JDK's own (what javac sees in its classes), not the runtime's */
  boolean lit;
  /** source form of a class name */
  String src(String n) {
    if (lit && !has(n)) return n.replace('/', '.').replace('$', '.');
    String t = target(n);
    if (t == null) throw new IllegalStateException("no class for " + n);
    if (has(n)) {
      String o = outer(n);
      if (o != null && has(o)) return src(o) + "." + n.substring(n.lastIndexOf('$') + 1);
      return t.replace('/', '.');   // a nested class of an existing class: a top-level class named Outer$Inner
    }
    return t.replace('/', '.').replace('$', '.');
  }
  /** the first type c's declaration refers to that nothing provides */
  String unresolvable(VClass c) {
    ClassNode jn = node(c.name);
    List<String> refs = new ArrayList<>();
    if (jn != null) {
      if (jn.superName != null) refs.add(jn.superName);
      refs.addAll(jn.interfaces);
    } else if (c.sup != null) refs.add(c.sup);
    for (VMember m : c.methods) for (Type t : Type.getArgumentTypes(m.desc)) refs.add(t.getSort() == Type.ARRAY ? t.getElementType().getInternalName() : t.getInternalName());
    for (VMember m : c.methods) { Type r = Type.getReturnType(m.desc); refs.add(r.getSort() == Type.ARRAY ? r.getElementType().getInternalName() : r.getInternalName()); }
    for (String r : refs) {
      if (r.length() == 1 || r.equals(c.name)) continue;   // primitive
      if (target(r) == null) return r;
    }
    return null;
  }

  // ================================================================ SDK declarations
  ClassNode node(String n) { for (Map<String, ClassNode> m : sdk) { ClassNode c = m.get(n); if (c != null) return c; } return null; }
  /** the newest SDK declaration of a method: [class, method] */
  Object[] method(String cls, String name, String desc) {
    for (Map<String, ClassNode> m : sdk) {
      ClassNode c = m.get(cls);
      if (c == null) continue;
      for (MethodNode x : c.methods) if (x.name.equals(name) && x.desc.equals(desc)) return new Object[]{c, x};
    }
    return null;
  }
  FieldNode field(String cls, String name) {
    for (Map<String, ClassNode> m : sdk) {
      ClassNode c = m.get(cls);
      if (c == null) continue;
      for (FieldNode f : c.fields) if (f.name.equals(name)) return f;
    }
    return null;
  }
  /** access flags of a nested class as its InnerClasses entry has them */
  int innerAccess(ClassNode cn) {
    for (InnerClassNode ic : cn.innerClasses) if (ic.name.equals(cn.name)) return ic.access;
    return cn.access;
  }

  // ================================================================ generic types
  abstract static class T {}
  static final class Prim extends T { final char c; Prim(char c) { this.c = c; } }
  static final class Var extends T { final String name; Var(String n) { name = n; } }
  static final class Arr extends T { final T comp; Arr(T c) { comp = c; } }
  static final class Cls extends T { final String name; final List<Arg> args = new ArrayList<>(); Cls(String n) { name = n; } }
  static final class Arg { final char kind; final T type; Arg(char k, T t) { kind = k; type = t; } }   // '=', '+', '-', '*'
  static final class TParam { final String name; final List<T> bounds = new ArrayList<>(); TParam(String n) { name = n; } }
  static final class MSig { List<TParam> tparams = new ArrayList<>(); List<T> params = new ArrayList<>(); T ret; List<T> exc = new ArrayList<>(); }
  static final class CSig { List<TParam> tparams = new ArrayList<>(); Cls sup; List<Cls> itfs = new ArrayList<>(); }

  static final class SigParser {
    final String s; int i;
    SigParser(String s) { this.s = s; }
    List<TParam> tparams() {
      List<TParam> out = new ArrayList<>();
      if (i >= s.length() || s.charAt(i) != '<') return out;
      i++;
      while (s.charAt(i) != '>') {
        int c = s.indexOf(':', i);
        TParam p = new TParam(s.substring(i, c));
        i = c;
        while (i < s.length() && s.charAt(i) == ':') {
          i++;
          if (s.charAt(i) == ':') continue;   // no class bound
          p.bounds.add(type());
        }
        out.add(p);
      }
      i++;
      return out;
    }
    T type() {
      char c = s.charAt(i);
      switch (c) {
        case 'L': return cls();
        case 'T': { int e = s.indexOf(';', i); Var v = new Var(s.substring(i + 1, e)); i = e + 1; return v; }
        case '[': i++; return new Arr(type());
        default: i++; return new Prim(c);
      }
    }
    Cls cls() {
      i++;   // L
      StringBuilder name = new StringBuilder();
      Cls cur = null;
      while (true) {
        char c = s.charAt(i);
        if (c == ';') { i++; if (cur == null) cur = new Cls(name.toString()); return cur; }
        if (c == '<') {
          Cls k = new Cls(name.toString());
          i++;
          while (s.charAt(i) != '>') {
            char w = s.charAt(i);
            if (w == '*') { k.args.add(new Arg('*', null)); i++; }
            else if (w == '+' || w == '-') { i++; k.args.add(new Arg(w, type())); }
            else k.args.add(new Arg('=', type()));
          }
          i++;
          cur = k;
          continue;
        }
        if (c == '.') {   // inner class of a parameterized outer: keep the inner's arguments
          name.append('$'); i++; cur = null;
          continue;
        }
        name.append(c); i++;
        if (cur != null) cur = null;
      }
    }
    static CSig classSig(ClassNode cn) {
      CSig cs = new CSig();
      if (cn.signature != null) {
        SigParser p = new SigParser(cn.signature);
        cs.tparams = p.tparams();
        cs.sup = p.cls();
        while (p.i < p.s.length()) cs.itfs.add(p.cls());
      } else {
        cs.sup = cn.superName == null ? null : new Cls(cn.superName);
        for (String x : cn.interfaces) cs.itfs.add(new Cls(x));
      }
      return cs;
    }
    static MSig methodSig(String signature, String desc, List<String> exceptions) {
      MSig ms = new MSig();
      if (signature != null) {
        try {
          SigParser p = new SigParser(signature);
          ms.tparams = p.tparams();
          p.i++;   // (
          while (p.s.charAt(p.i) != ')') ms.params.add(p.type());
          p.i++;
          ms.ret = p.type();
          while (p.i < p.s.length() && p.s.charAt(p.i) == '^') { p.i++; ms.exc.add(p.type()); }
          // javac leaves synthetic parameters (an inner class's outer instance, an enum's name and ordinal) out of
          // the signature: fall back to the descriptor if the counts differ
          if (ms.params.size() == Type.getArgumentTypes(desc).length) {
            if (ms.exc.isEmpty() && exceptions != null) for (String e : exceptions) ms.exc.add(new Cls(e));
            return ms;
          }
        } catch (RuntimeException e) { /* fall back to the descriptor */ }
        ms = new MSig();
      }
      for (Type t : Type.getArgumentTypes(desc)) ms.params.add(fromType(t));
      ms.ret = fromType(Type.getReturnType(desc));
      if (exceptions != null) for (String e : exceptions) ms.exc.add(new Cls(e));
      return ms;
    }
    static T fieldSig(String signature, String desc) {
      if (signature != null) try { return new SigParser(signature).type(); } catch (RuntimeException e) { /* descriptor */ }
      return fromType(Type.getType(desc));
    }
    static T fromType(Type t) {
      switch (t.getSort()) {
        case Type.ARRAY: { T e = fromType(t.getElementType()); for (int d = 0; d < t.getDimensions(); d++) e = new Arr(e); return e; }
        case Type.OBJECT: return new Cls(t.getInternalName());
        default: return new Prim(t.getDescriptor().charAt(0));
      }
    }
  }

  /** the erasure of a type (type variables: their first bound), with the runtime's class names */
  String erase(T t, Map<String, T> env, Set<String> seen) {
    if (t instanceof Prim p) return String.valueOf(p.c);
    if (t instanceof Arr a) return "[" + erase(a.comp, env, seen);
    if (t instanceof Cls c) { String n = lit ? null : target(c.name); return "L" + (n == null ? c.name : n) + ";"; }
    Var v = (Var) t;
    T b = env.get(v.name);
    if (b == null || !seen.add(v.name)) return "Ljava/lang/Object;";
    return erase(b, env, seen);
  }
  static Map<String, T> boundsEnv(List<TParam> ps, Map<String, T> outer) {
    Map<String, T> env = new HashMap<>(outer);
    for (TParam p : ps) env.put(p.name, p.bounds.isEmpty() ? new Cls("java/lang/Object") : p.bounds.get(0));
    return env;
  }
  /** t with the class's type variables replaced (raw use: by their erasure) */
  static T subst(T t, Map<String, T> s) {
    if (t instanceof Var v) return s.containsKey(v.name) ? s.get(v.name) : t;
    if (t instanceof Arr a) return new Arr(subst(a.comp, s));
    if (t instanceof Cls c && !c.args.isEmpty()) {
      Cls k = new Cls(c.name);
      for (Arg x : c.args) k.args.add(new Arg(x.kind, x.type == null ? null : subst(x.type, s)));
      return k;
    }
    return t;
  }

  String render(T t) {
    if (t instanceof Prim p) {
      switch (p.c) {
        case 'Z': return "boolean"; case 'B': return "byte"; case 'C': return "char"; case 'S': return "short";
        case 'I': return "int"; case 'J': return "long"; case 'F': return "float"; case 'D': return "double"; default: return "void";
      }
    }
    if (t instanceof Var v) return v.name;
    if (t instanceof Arr a) return render(a.comp) + "[]";
    Cls c = (Cls) t;
    StringBuilder b = new StringBuilder(src(c.name));
    if (!c.args.isEmpty()) {
      b.append('<');
      for (int i = 0; i < c.args.size(); i++) {
        Arg x = c.args.get(i);
        if (i > 0) b.append(", ");
        if (x.kind == '*') b.append('?');
        else if (x.kind == '+') b.append("? extends ").append(render(x.type));
        else if (x.kind == '-') b.append("? super ").append(render(x.type));
        else b.append(render(x.type));
      }
      b.append('>');
    }
    return b.toString();
  }
  String renderTParams(List<TParam> ps) {
    if (ps.isEmpty()) return "";
    StringBuilder b = new StringBuilder("<");
    for (int i = 0; i < ps.size(); i++) {
      TParam p = ps.get(i);
      if (i > 0) b.append(", ");
      b.append(p.name);
      List<T> bounds = new ArrayList<>(p.bounds);
      if (bounds.size() == 1 && bounds.get(0) instanceof Cls c && c.name.equals("java/lang/Object")) bounds.clear();
      for (int j = 0; j < bounds.size(); j++) b.append(j == 0 ? " extends " : " & ").append(render(bounds.get(j)));
    }
    return b.append("> ").toString();
  }
  /** whether a type names only classes that exist or are generated */
  boolean nameable(T t) {
    if (t instanceof Arr a) return nameable(a.comp);
    if (t instanceof Cls c) {
      if (target(c.name) == null) return false;
      for (Arg x : c.args) if (x.type != null && !nameable(x.type)) return false;
    }
    return true;
  }
  /** a type in the source of a generated class: unnameable generic arguments are dropped (raw type) */
  T usable(T t) {
    if (t instanceof Arr a) return new Arr(usable(a.comp));
    if (t instanceof Cls c) {
      for (Arg x : c.args) if (x.type != null && !nameable(x.type)) return new Cls(c.name);
      return c;
    }
    return t;
  }

  // ================================================================ supertypes and inherited abstract methods
  /** a method as seen from a class: name, erased parameter descriptor, its types after substitution */
  static final class Seen {
    String name, params, ret, owner; int access, depth; boolean itf, literal; MSig sig; List<String> exc;
  }
  ClassNode runtimeNode(String n) {
    if (has(n)) return node(n);
    String t = R.provider(n);
    Link.CI ci = t == null ? null : R.L.world.get(t);
    return ci == null ? null : ci.node;
  }
  /** what javac sees for a class: TeaVM's JDK classes compile against the host JDK's */
  ClassNode javacNode(String n) {
    if (has(n)) return node(n);
    String t = R.provider(n);
    Link.CI ci = t == null ? null : R.L.world.get(t);
    if (ci != null && ci.origin == Link.JDK && t.startsWith("java")) { ClassNode h = host(t); if (h != null) return h; }
    return ci == null ? null : ci.node;
  }
  final Map<String, Optional<ClassNode>> hostCache = new HashMap<>();
  ClassNode host(String n) {
    return hostCache.computeIfAbsent(n, k -> {
      try (var in = ClassLoader.getSystemResourceAsStream(k + ".class")) {
        return in == null ? Optional.empty() : Optional.of(Link.read(in.readAllBytes()));
      } catch (IOException e) { return Optional.empty(); }
    }).orElse(null);
  }
  /** the methods of a type and its supertypes, with the type's arguments substituted */
  void collect(Cls type, Map<String, T> subst, List<Seen> out, Set<String> visited, int depth, boolean viaItf) {
    String n = type.name;
    if (!visited.add(n + render0(type))) return;
    ClassNode cn = javacNode(n);
    if (cn == null) return;
    CSig cs = SigParser.classSig(cn);
    Map<String, T> s = new HashMap<>();
    for (int i = 0; i < cs.tparams.size(); i++) {
      TParam p = cs.tparams.get(i);
      T arg = i < type.args.size() && type.args.get(i).type != null && type.args.get(i).kind == '=' ? subst(type.args.get(i).type, subst)
          : i < type.args.size() && type.args.get(i).type != null ? subst(type.args.get(i).type, subst)
          : p.bounds.isEmpty() ? new Cls("java/lang/Object") : p.bounds.get(0);
      s.put(p.name, arg);
    }
    boolean genClass = has(n);
    boolean hostNode = cn != runtimeNode(n);
    boolean was = lit;
    lit = hostNode;
    try {
    for (MethodNode m : cn.methods) {
      if (m.name.startsWith("<") || (m.access & (Opcodes.ACC_SYNTHETIC | Opcodes.ACC_BRIDGE | Opcodes.ACC_STATIC | Opcodes.ACC_PRIVATE)) != 0) continue;
      if (genClass && !inUniverse(n, m.name, m.desc)) continue;
      MSig ms = SigParser.methodSig(m.signature, m.desc, m.exceptions);
      MSig sub = new MSig();
      sub.tparams = ms.tparams;
      for (T p : ms.params) sub.params.add(subst(p, s));
      sub.ret = subst(ms.ret, s);
      sub.exc = ms.exc;
      Map<String, T> env = boundsEnv(ms.tparams, Map.of());
      StringBuilder pd = new StringBuilder("(");
      for (T p : sub.params) pd.append(erase(p, env, new HashSet<>()));
      Seen x = new Seen();
      x.name = m.name; x.params = pd.append(')').toString(); x.owner = n; x.access = m.access | (cn.access & Opcodes.ACC_INTERFACE) << 16; x.sig = sub;
      x.exc = m.exceptions;
      x.depth = depth;
      x.itf = viaItf || (cn.access & Opcodes.ACC_INTERFACE) != 0;
      x.literal = hostNode;
      x.ret = erase(sub.ret, env, new HashSet<>());
      out.add(x);
    }
    } finally { lit = was; }
    if (cs.sup != null && (cn.access & Opcodes.ACC_INTERFACE) == 0) collect((Cls) subst(cs.sup, s), Map.of(), out, visited, depth + 1, viaItf);
    for (Cls i : cs.itfs) collect((Cls) subst(i, s), Map.of(), out, visited, depth + 1, true);
  }
  /** type variables not in scope (a raw supertype's) become Object */
  static T close(T t, Set<String> scope) {
    if (t instanceof Var v) return scope.contains(v.name) ? t : new Cls("java/lang/Object");
    if (t instanceof Arr a) return new Arr(close(a.comp, scope));
    if (t instanceof Cls c && !c.args.isEmpty()) {
      Cls k = new Cls(c.name);
      for (Arg x : c.args) k.args.add(new Arg(x.kind, x.type == null ? null : close(x.type, scope)));
      return k;
    }
    return t;
  }
  static String render0(T t) {
    if (t instanceof Cls c) { StringBuilder b = new StringBuilder(c.name); for (Arg a : c.args) b.append(',').append(a.type == null ? "?" : render0(a.type)); return b.toString(); }
    if (t instanceof Arr a) return render0(a.comp) + "[]";
    if (t instanceof Var v) return "T" + v.name;
    return String.valueOf(((Prim) t).c);
  }
  boolean inUniverse(String cls, String name, String desc) {
    VClass c = universe.get(cls);
    if (c == null) return false;
    for (VMember m : c.methods) if (m.name.equals(name) && m.desc.equals(desc)) return true;
    return false;
  }

  // ================================================================ emission
  void emitClass(StringBuilder b, VClass c, String ind, boolean top) {
    ClassNode jn = node(c.name);
    int acc = jn == null ? Opcodes.ACC_PUBLIC : outer(c.name) != null ? innerAccess(jn) : jn.access;
    boolean itf = (acc & Opcodes.ACC_INTERFACE) != 0, ann = (acc & Opcodes.ACC_ANNOTATION) != 0, en = (acc & Opcodes.ACC_ENUM) != 0;
    boolean nestedHere = !top;
    String simple = top ? target(c.name).substring(target(c.name).lastIndexOf('/') + 1) : c.name.substring(c.name.lastIndexOf('$') + 1);
    CSig cs = jn != null ? SigParser.classSig(jn) : new CSig();
    if (jn == null) {
      cs.sup = c.sup == null ? new Cls("java/lang/Object") : new Cls(c.sup);
      for (String i : c.itfs) cs.itfs.add(new Cls(i));
    }
    b.append(ind).append("@a2w.Generated\n").append(ind);
    if ((acc & Opcodes.ACC_PROTECTED) != 0 && nestedHere) b.append("protected "); else b.append("public ");
    if (nestedHere && (acc & Opcodes.ACC_STATIC) != 0 && !itf && !en) b.append("static ");
    if ((acc & Opcodes.ACC_ABSTRACT) != 0 && !itf && !en) b.append("abstract ");
    if ((acc & Opcodes.ACC_FINAL) != 0 && !en) b.append("final ");
    b.append(ann ? "@interface " : itf ? "interface " : en ? "enum " : "class ").append(simple);
    if (!ann) b.append(renderTParams(cs.tparams).stripTrailing());
    Set<String> tvars = new HashSet<>();
    for (TParam p : cs.tparams) tvars.add(p.name);
    if (!itf && !en && cs.sup != null && !cs.sup.name.equals("java/lang/Object")) {
      String t = target(cs.sup.name);
      ClassNode sn = runtimeNode(cs.sup.name);
      if (sn != null && (sn.access & Opcodes.ACC_INTERFACE) != 0) problems.add(c.name + ": superclass " + cs.sup.name + " is an interface in the runtime");
      else if (sn != null && (sn.access & Opcodes.ACC_FINAL) != 0) problems.add(c.name + ": superclass " + cs.sup.name + " is final in the runtime");
      else if (t != null) b.append(" extends ").append(render(usable(cs.sup)));
    }
    List<String> sups = new ArrayList<>();
    for (Cls i : cs.itfs) {
      if (ann && i.name.equals("java/lang/annotation/Annotation")) continue;
      ClassNode in = runtimeNode(i.name);
      if (in != null && (in.access & Opcodes.ACC_INTERFACE) == 0) { problems.add(c.name + ": interface " + i.name + " is a class in the runtime"); continue; }
      if (target(i.name) != null) sups.add(render(usable(i)));
    }
    if (cloneable.contains(c.name)) sups.add("java.lang.Cloneable");
    if (!sups.isEmpty()) b.append(itf ? " extends " : " implements ").append(String.join(", ", sups));
    b.append(" {\n");
    String in2 = ind + "  ";
    String qname = c.name.replace('/', '.').replace('$', '.');

    // enum constants
    List<VMember> fields = new ArrayList<>(c.fields);
    fields.sort(Comparator.comparing(f -> f.name));
    if (en) {
      List<String> consts = new ArrayList<>();
      if (jn != null) for (FieldNode f : jn.fields) if ((f.access & Opcodes.ACC_ENUM) != 0) consts.add(f.name);
      b.append(in2).append(String.join(", ", consts)).append(";\n");
    }
    // fields
    for (VMember vf : fields) {
      FieldNode f = field(c.name, vf.name);
      if (f != null && (f.access & Opcodes.ACC_ENUM) != 0) continue;
      if (f == null) { problems.add(c.name + "." + vf.name + ": no declaration"); continue; }
      T ft = usable(SigParser.fieldSig(f.signature, f.desc));
      if (!nameable(ft)) { problems.add(c.name + "." + vf.name + ": type"); continue; }
      boolean st = (f.access & Opcodes.ACC_STATIC) != 0, fin = (f.access & Opcodes.ACC_FINAL) != 0;
      StringBuilder line = new StringBuilder(in2);
      if (!itf) {
        if ((f.access & Opcodes.ACC_PROTECTED) != 0) line.append("protected "); else line.append("public ");
        if (st) line.append("static ");
        if (fin) line.append("final ");
      }
      if (f.value != null) {
        line.append(render(ft)).append(' ').append(f.name).append(" = ").append(literal(f.desc, f.value)).append(";\n");
      } else if (f.name.equals("CREATOR") && st && creators.containsKey(c.name)) {
        line.insert(in2.length(), "@a2w.Derived ");
        line.append(render(ft)).append(' ').append(f.name).append(" = ").append(creators.get(c.name)).append(";\n");
      } else if (fin || itf) {
        line.insert(in2.length(), "@a2w.NotImplemented ");   // its value is not known
        line.append(render(ft)).append(' ').append(f.name).append(" = ").append(defaultValue(ft)).append(";\n");
      } else {
        line.append(render(ft)).append(' ').append(f.name).append(";\n");
      }
      b.append(line);
    }

    for (String x : extraFields.getOrDefault(c.name, List.of())) b.append(in2).append(x).append('\n');
    Map<String, String> derived = bodies.getOrDefault(c.name, Map.of());
    // constructors
    boolean throwable = isThrowable(c.name);
    String sup = !itf && !en && cs.sup != null ? cs.sup.name : "java/lang/Object";
    if (!itf && !en && !ann) {
      b.append(in2).append("/** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */\n");
      b.append(in2).append("public ").append(simple).append("(a2w.Hidden hidden)");
      String call = superCall(c.name, sup, cs);
      if (call == null) { problems.add(c.name + ": no usable constructor in " + sup); call = ""; }
      String thr = superThrows;
      if (thr != null) b.append(" throws ").append(thr);
      b.append(" {").append(call.isEmpty() ? "" : " " + call).append(" }\n");
      for (VMember m : sorted(c.methods)) {
        if (!m.name.equals("<init>")) continue;
        Object[] d = method(c.name, m.name, m.desc);
        MethodNode mn = d == null ? null : (MethodNode) d[1];
        int macc = mn == null ? Opcodes.ACC_PUBLIC : mn.access;
        MSig ms = mn == null ? SigParser.methodSig(null, m.desc, null) : SigParser.methodSig(mn.signature, mn.desc, mn.exceptions);
        if (!allNameable(ms)) { problems.add(c.name + ".<init>" + m.desc + ": type"); continue; }
        List<String> names = paramNames(mn, ms.params.size(), true);
        b.append(in2);
        String pass = throwable ? passThrough(sup, m.desc) : null;
        boolean trivial = pass == null && m.desc.equals("()V") && callbacksOnly(c);
        String body = derived.get("<init>" + m.desc);
        if (body != null && pass == null) {
          b.append("@a2w.Derived ").append((macc & Opcodes.ACC_PROTECTED) != 0 ? "protected " : "public ").append(renderTParams(ms.tparams)).append(simple)
              .append('(').append(params(ms, names, macc)).append(')').append(throwsClause(ms, thr)).append(" { ").append(body).append(" }\n");
          continue;
        }
        if (pass == null && !trivial) b.append("@a2w.NotImplemented ");
        b.append((macc & Opcodes.ACC_PROTECTED) != 0 ? "protected " : "public ").append(renderTParams(ms.tparams)).append(simple).append('(')
            .append(params(ms, names, macc)).append(')').append(throwsClause(ms, thr)).append(" {");
        if (pass != null) b.append(" super(").append(String.join(", ", names)).append(");");
        else {
          b.append(" this((a2w.Hidden) null);");
          if (!trivial) b.append(" a2w.Stubs.hit(\"").append(qname).append(".<init>\");");
        }
        b.append(" }\n");
      }
    }

    // methods: what they override (as javac sees the supertypes)
    Map<String, Seen> inherited = new HashMap<>();
    {
      List<Seen> all = new ArrayList<>();
      Cls self = new Cls(c.name);
      for (TParam p : cs.tparams) self.args.add(new Arg('=', new Var(p.name)));
      collect(self, Map.of(), all, new HashSet<>(), 0, false);
      for (Seen x : all) {
        if (x.owner.equals(c.name)) continue;
        String k = x.name + x.params;
        Seen o = inherited.get(k);
        if (o == null || !x.itf && (o.itf || x.depth < o.depth)) inherited.put(k, x);
      }
    }
    curInherited = inherited;
    Set<String> declared = new HashSet<>();
    for (VMember m : sorted(c.methods)) {
      if (m.name.startsWith("<")) continue;
      if (en && (m.name.equals("values") && m.desc.startsWith("()") || m.name.equals("valueOf") && m.desc.startsWith("(Ljava/lang/String;)"))) continue;
      Object[] d = method(c.name, m.name, m.desc);
      MethodNode mn = d == null ? null : (MethodNode) d[1];
      if (mn != null && (mn.access & (Opcodes.ACC_SYNTHETIC | Opcodes.ACC_BRIDGE)) != 0) continue;
      if (mn == null) problems.add(c.name + "." + m.name + m.desc + ": no declaration (instance method assumed)");
      int macc = mn == null ? Opcodes.ACC_PUBLIC : mn.access;
      if (en) macc &= ~Opcodes.ACC_ABSTRACT;   // constants with bodies are not generated
      MSig ms = mn == null ? SigParser.methodSig(null, m.desc, null) : SigParser.methodSig(mn.signature, mn.desc, mn.exceptions);
      if (!allNameable(ms)) { ms = SigParser.methodSig(null, m.desc, mn == null ? null : mn.exceptions); if (!allNameable(ms)) { problems.add(c.name + "." + m.name + m.desc + ": type"); continue; } }
      String key = m.name + erasedParams(ms);
      if (!declared.add(key)) continue;
      Seen o = inherited.get(key);
      if (o != null && !has(o.owner)) {
        boolean st = (macc & Opcodes.ACC_STATIC) != 0, ost = (o.access & Opcodes.ACC_STATIC) != 0;
        String ret = erase(ms.ret, boundsEnv(ms.tparams, Map.of()), new HashSet<>());
        if ((o.access & Opcodes.ACC_FINAL) != 0 || st != ost || !assignable(ret, o.ret)) {
          problems.add(c.name + "." + m.name + m.desc + ": conflicts with " + o.owner + "." + o.name + " of the runtime");
          continue;
        }
        if ((o.access & Opcodes.ACC_PUBLIC) != 0) macc = (macc & ~Opcodes.ACC_PROTECTED) | Opcodes.ACC_PUBLIC;
        // an override may not throw checked exceptions the overridden method does not
        List<T> keep = new ArrayList<>();
        for (T e : ms.exc) {
          if (!(e instanceof Cls k)) continue;
          String exn = target(k.name);
          if (isUnchecked(exn)) { keep.add(e); continue; }
          if (o.exc != null) for (String oe : o.exc) if (subtype(exn, oe)) { keep.add(e); break; }
        }
        ms.exc = keep;
      }
      String body = derived.get(m.name + m.desc);
      if (body != null) {
        b.append(in2).append("@a2w.Derived ").append((macc & Opcodes.ACC_PROTECTED) != 0 ? "protected " : "public ")
            .append((macc & Opcodes.ACC_FINAL) != 0 ? "final " : "").append(renderTParams(ms.tparams)).append(render(ms.ret)).append(' ').append(m.name)
            .append('(').append(params(ms, paramNames(mn, ms.params.size(), true), macc)).append(')').append(throwsClause(ms, null))
            .append(" { ").append(body).append(" }\n");
        continue;
      }
      emitMethod(b, in2, c, qname, m.name, macc, ms, paramNames(mn, ms.params.size(), (macc & Opcodes.ACC_STATIC) == 0), itf, ann, mn, sup, cs);
    }
    // abstract methods inherited and not implemented (a concrete class must implement them to compile)
    if (!itf && !ann && (acc & Opcodes.ACC_ABSTRACT) == 0) {
      List<Seen> all = new ArrayList<>();
      Cls self = new Cls(c.name);
      for (TParam p : cs.tparams) self.args.add(new Arg('=', new Var(p.name)));
      collect(self, Map.of(), all, new HashSet<>(), 0, false);
      // the superclass chain decides (its nearest declaration); interface methods need one unless a default exists
      Map<String, Seen> chain = new HashMap<>();
      Set<String> defaults = new HashSet<>();
      for (Seen x : all) {
        String k = x.name + x.params;
        if (!x.itf) { Seen o = chain.get(k); if (o == null || x.depth < o.depth) chain.put(k, x); }
        else if ((x.access & Opcodes.ACC_ABSTRACT) == 0) defaults.add(k);
      }
      Set<String> done = new HashSet<>(declared);
      for (Seen x : all) {
        String k = x.name + x.params;
        if (x.owner.equals(c.name) || done.contains(k)) continue;
        Seen ch = chain.get(k);
        boolean need = ch != null ? (ch.access & Opcodes.ACC_ABSTRACT) != 0 && ch.depth > 0 : (x.access & Opcodes.ACC_ABSTRACT) != 0 && !defaults.contains(k);
        if (!need || ch != null && ch != x) continue;
        done.add(k);
        if ((x.access & (Opcodes.ACC_PUBLIC | Opcodes.ACC_PROTECTED)) == 0) { problems.add(c.name + ": cannot implement package-private " + x.owner + "." + x.name); continue; }
        if (!allNameable(x.sig)) { problems.add(c.name + ": inherited " + x.owner + "." + x.name + ": type"); continue; }
        Set<String> scope = new HashSet<>(tvars);
        for (TParam p : x.sig.tparams) scope.add(p.name);
        MSig ms = new MSig();
        ms.tparams = x.sig.tparams;
        for (T p : x.sig.params) ms.params.add(close(p, scope));
        ms.ret = close(x.sig.ret, scope);
        for (T e : x.sig.exc) ms.exc.add(close(e, scope));
        b.append(in2).append("/** (inherited abstract) */\n");
        boolean was = lit;
        lit = x.literal;
        try {
        emitMethod(b, in2, c, qname, x.name, (x.access & 0xffff & ~Opcodes.ACC_ABSTRACT) | Opcodes.ACC_PUBLIC, ms, paramNames(null, ms.params.size(), true), false, false, null, sup, cs);
        } finally { lit = was; }
      }
      // an enum's constants would each need a body for its abstract methods: none are declared abstract here
    }

    // nested classes
    for (VClass n : gen.values())
      if (c.name.equals(outer(n.name))) { b.append('\n'); emitClass(b, n, in2, false); }
    b.append(ind).append("}\n");
  }

  // ================================================================ derived bodies: data classes and builders
  /** a value a class keeps for a getter (getX / isX), in a field a2w$x of the class that declares the getter */
  static final class Prop { String name, erased, owner; T type; boolean backed, collection; String elem; }
  final Map<String, Map<String, String>> bodies = new HashMap<>();   // class -> name+desc -> body
  final Map<String, List<String>> extraFields = new HashMap<>();
  final Map<String, String> creators = new HashMap<>();
  final Set<String> cloneable = new HashSet<>();
  final Map<String, Map<String, Prop>> propCache = new HashMap<>();
  static final java.util.regex.Pattern SERVICE = java.util.regex.Pattern.compile(
      ".*(Manager|Service|Session|Controller|Player|Recorder|Engine|Connection|Adapter|Client|Callback|Callbacks|Listener|Exception|Error|Factory|Provider|Helper|Monitor|Tracker|Detector)");

  /** a plain data class: generated, a class, and Object or another such class as superclass */
  boolean dataClass(String n) {
    if (!has(n)) return false;
    ClassNode jn = node(n);
    if (jn == null || (jn.access & (Opcodes.ACC_INTERFACE | Opcodes.ACC_ENUM | Opcodes.ACC_ANNOTATION)) != 0) return false;
    String simple = n.substring(n.lastIndexOf('/') + 1);
    if (SERVICE.matcher(simple).matches()) return false;
    if (jn.superName == null || jn.superName.equals("java/lang/Object")) return true;
    return dataClass(jn.superName);
  }
  Map<String, T> classEnv(String n) {
    ClassNode jn = node(n);
    return jn == null ? Map.of() : boundsEnv(SigParser.classSig(jn).tparams, Map.of());
  }
  MSig sigOf(String cls, VMember m) {
    Object[] d = method(cls, m.name, m.desc);
    MethodNode mn = d == null ? null : (MethodNode) d[1];
    return mn == null ? SigParser.methodSig(null, m.desc, null) : SigParser.methodSig(mn.signature, mn.desc, mn.exceptions);
  }
  MethodNode mnOf(String cls, VMember m) { Object[] d = method(cls, m.name, m.desc); return d == null ? null : (MethodNode) d[1]; }
  static String decap(String s) { return s.isEmpty() ? s : Character.toLowerCase(s.charAt(0)) + s.substring(1); }
  /** a class's properties, its generated superclasses' included (the topmost declaration owns the field) */
  Map<String, Prop> props(String n) {
    Map<String, Prop> have = propCache.get(n);
    if (have != null) return have;
    Map<String, Prop> out = new LinkedHashMap<>();
    ClassNode jn = node(n);
    if (jn != null && jn.superName != null && dataClass(jn.superName)) out.putAll(props(jn.superName));
    VClass c = gen.get(n);
    Map<String, T> env = classEnv(n);
    for (VMember m : sorted(c.methods)) {
      if (!m.desc.startsWith("()") || m.desc.endsWith(")V")) continue;
      String pn;
      if (m.name.length() > 3 && m.name.startsWith("get") && Character.isUpperCase(m.name.charAt(3))) pn = decap(m.name.substring(3));
      else if (m.name.length() > 2 && m.name.startsWith("is") && Character.isUpperCase(m.name.charAt(2)) && m.desc.equals("()Z")) pn = decap(m.name.substring(2));
      else continue;
      MethodNode mn = mnOf(n, m);
      if (mn == null || (mn.access & (Opcodes.ACC_STATIC | Opcodes.ACC_ABSTRACT)) != 0 || out.containsKey(pn)) continue;
      MSig ms = sigOf(n, m);
      if (!allNameable(ms)) continue;
      Prop p = new Prop();
      p.name = pn; p.owner = n; p.type = usable(ms.ret); p.erased = erase(ms.ret, env, new HashSet<>());
      if (p.type instanceof Cls k && k.name.equals("java/util/List")) {
        p.collection = true;
        p.elem = !k.args.isEmpty() && k.args.get(0).type != null ? erase(k.args.get(0).type, env, new HashSet<>()) : "Ljava/lang/Object;";
      }
      out.put(pn, p);
    }
    propCache.put(n, out);
    return out;
  }
  String field(Prop p) { return "a2w$" + p.name; }
  /** a value for a property's field: a cast where the generic types differ (the erasures are the same) */
  String assign(Prop p, T from, String expr) {
    if (from instanceof Prim || render(usable(from)).equals(render(p.type))) return expr;
    return "(" + render(p.type) + ") (java.lang.Object) " + expr;
  }
  Map<String, String> bodiesOf(String n) { return bodies.computeIfAbsent(n, k -> new HashMap<>()); }
  /** the property a parameter (by name) or a setter's suffix names, if of the same type */
  Prop match(Map<String, Prop> props, String name, String erased) {
    for (Prop p : props.values()) if (p.name.equalsIgnoreCase(name) && p.erased.equals(erased)) return p;
    return null;
  }
  void plan() {
    planFieldConstructors();
    for (String n : new ArrayList<>(gen.keySet())) {
      if (!dataClass(n)) continue;
      Map<String, Prop> ps = props(n);
      if (ps.isEmpty()) continue;
      VClass c = gen.get(n);
      Map<String, T> env = classEnv(n);
      ClassNode jn = node(n);
      boolean concrete = (jn.access & Opcodes.ACC_ABSTRACT) == 0;
      int setters = 0;
      // the class's own setters (void or fluent)
      for (VMember m : sorted(c.methods)) {
        if (m.name.length() < 4 || !m.name.startsWith("set") || !Character.isUpperCase(m.name.charAt(3))) continue;
        MSig ms = sigOf(n, m);
        MethodNode mn = mnOf(n, m);
        if (ms.params.size() != 1 || mn == null || (mn.access & Opcodes.ACC_STATIC) != 0) continue;
        Prop p = match(ps, m.name.substring(3), erase(ms.params.get(0), boundsEnv(ms.tparams, env), new HashSet<>()));
        if (p == null) continue;
        String ret = erase(ms.ret, env, new HashSet<>());
        String arg = assign(p, ms.params.get(0), paramNames(mn, 1, true).get(0));
        String body = "this." + field(p) + " = " + arg + ";";
        if (ret.equals("V")) { }
        else if (ret.equals("L" + target(n) + ";")) body += ms.ret instanceof Var v ? " return (" + v.name + ") this;" : " return this;";
        else continue;
        bodiesOf(n).put(m.name + m.desc, body);
        p.backed = true;
        setters++;
      }
      // constructors whose parameters all name properties
      for (VMember m : sorted(c.methods)) {
        if (!m.name.equals("<init>")) continue;
        MSig ms = sigOf(n, m);
        MethodNode mn = mnOf(n, m);
        if (mn == null) continue;
        List<String> names = paramNames(mn, ms.params.size(), true);
        StringBuilder body = new StringBuilder("this((a2w.Hidden) null);");
        List<Prop> set = new ArrayList<>();
        boolean ok = true;
        for (int i = 0; i < ms.params.size() && ok; i++) {
          String nm = names.get(i);
          if (nm.matches("p\\d+")) { ok = false; break; }
          Prop p = match(ps, nm.startsWith("m") && nm.length() > 1 && Character.isUpperCase(nm.charAt(1)) ? nm.substring(1) : nm,
              erase(ms.params.get(i), boundsEnv(ms.tparams, env), new HashSet<>()));
          if (p == null) ok = false; else { set.add(p); body.append(" this.").append(field(p)).append(" = ").append(assign(p, ms.params.get(i), nm)).append(';'); }
        }
        if (!ok || ms.params.isEmpty() && setters == 0) continue;
        bodiesOf(n).put("<init>" + m.desc, body.toString());
        for (Prop p : set) p.backed = true;
      }
      // clone(): a field-by-field copy (the class becomes Cloneable)
      for (VMember m : c.methods)
        if (m.name.equals("clone") && m.desc.equals("()L" + n + ";") && concrete) {
          bodiesOf(n).put(m.name + m.desc, "try { return (" + src(n) + ") super.clone(); } catch (java.lang.CloneNotSupportedException e) { throw new java.lang.AssertionError(e); }");
          cloneable.add(n);
        }
      // the builder
      String bn = n + "$Builder";
      if (has(bn) && concrete) planBuilder(n, bn, ps);
    }
    // getters of properties something sets, and the classes whose state is then fully known
    for (String n : new ArrayList<>(gen.keySet())) {
      if (!dataClass(n) || props(n).isEmpty()) continue;
      Map<String, Prop> ps = props(n);
      VClass c = gen.get(n);
      boolean all = true;
      for (Prop p : ps.values()) if (!p.backed) all = false;
      List<String> decls = new ArrayList<>();
      for (Prop p : ps.values()) {
        if (!p.owner.equals(n) || !p.backed) continue;
        decls.add("/** (state of the property " + p.name + ", not part of the API) */ public " + render(p.type) + " " + field(p) + ";");
      }
      if (!decls.isEmpty()) extraFields.put(n, decls);
      for (VMember m : c.methods) {
        if (!m.desc.startsWith("()") || m.desc.endsWith(")V")) continue;
        for (Prop p : ps.values())
          if (p.backed && (m.name.equals("get" + Character.toUpperCase(p.name.charAt(0)) + p.name.substring(1))
              || m.name.equals("is" + Character.toUpperCase(p.name.charAt(0)) + p.name.substring(1)) && m.desc.equals("()Z"))) {
            MethodNode mn = mnOf(n, m);
            if (mn != null && (mn.access & Opcodes.ACC_STATIC) == 0 && (mn.access & Opcodes.ACC_ABSTRACT) == 0)
              bodiesOf(n).put(m.name + m.desc, "return this." + field(p) + ";");
          }
      }
      if (!all) continue;
      List<Prop> fs = new ArrayList<>(ps.values());
      String self = src(n);
      for (VMember m : c.methods) {
        String k = m.name + m.desc;
        if (k.equals("equals(Ljava/lang/Object;)Z")) {
          String o = paramNames(mnOf(n, m), 1, true).get(0);
          StringBuilder b = new StringBuilder("if (this == " + o + ") return true; if (!(" + o + " instanceof " + self + ")) return false; ")
              .append(self).append(" x = (").append(self).append(") ").append(o).append("; return true");
          for (Prop p : fs) b.append(p.erased.startsWith("[") ? " && java.util.Arrays.equals(" + field(p) + ", x." + field(p) + ")"
              : " && java.util.Objects.equals(" + field(p) + ", x." + field(p) + ")");
          bodiesOf(n).put(k, b.append(';').toString());
        } else if (k.equals("hashCode()I")) {
          StringBuilder b = new StringBuilder("return java.util.Arrays.deepHashCode(new Object[]{");
          for (int i = 0; i < fs.size(); i++) b.append(i == 0 ? "" : ", ").append(field(fs.get(i)));
          bodiesOf(n).put(k, b.append("});").toString());
        } else if (k.equals("toString()Ljava/lang/String;")) {
          StringBuilder b = new StringBuilder("return \"").append(n.substring(n.lastIndexOf('/') + 1).replace('$', '.')).append("{\"");
          for (int i = 0; i < fs.size(); i++) {
            Prop p = fs.get(i);
            b.append(" + \"").append(i == 0 ? "" : ", ").append(p.name).append("=\" + ")
                .append(p.erased.startsWith("[") ? "java.util.Arrays.deepToString(new Object[]{" + field(p) + "})" : "java.lang.String.valueOf(" + field(p) + ")");
          }
          bodiesOf(n).put(k, b.append(" + \"}\";").toString());
        } else if (k.equals("describeContents()I")) {
          bodiesOf(n).put(k, "return 0;");
        } else if (k.equals("writeToParcel(Landroid/os/Parcel;I)V")) {
          MethodNode mn = mnOf(n, m);
          List<String> names = paramNames(mn, 2, true);
          StringBuilder b = new StringBuilder();
          for (Prop p : fs) b.append(names.get(0)).append(".writeValue(").append(field(p)).append("); ");
          bodiesOf(n).put(k, b.toString().trim());
        }
      }
      ClassNode jn = node(n);
      boolean hasCreator = false;
      for (VMember f : c.fields) if (f.name.equals("CREATOR")) hasCreator = true;
      if (hasCreator && (jn.access & Opcodes.ACC_ABSTRACT) == 0 && node(n).signature == null) {
        StringBuilder b = new StringBuilder("new android.os.Parcelable.Creator<").append(self).append(">() { public ").append(self)
            .append(" createFromParcel(android.os.Parcel in) { ").append(self).append(" x = new ").append(self).append("((a2w.Hidden) null); ");
        for (Prop p : fs) b.append("x.").append(field(p)).append(" = ").append(fromValue(p)).append("; ");
        b.append("return x; } public ").append(self).append("[] newArray(int size) { return new ").append(self).append("[size]; } }");
        creators.put(n, b.toString());
      }
    }
  }
  /** in.readValue(null) as the property's type */
  String fromValue(Prop p) {
    String e = p.erased;
    switch (e) {
      case "Z": return "(java.lang.Boolean) in.readValue(null)";
      case "B": return "(java.lang.Byte) in.readValue(null)";
      case "C": return "(java.lang.Character) in.readValue(null)";
      case "S": return "(java.lang.Short) in.readValue(null)";
      case "I": return "(java.lang.Integer) in.readValue(null)";
      case "J": return "(java.lang.Long) in.readValue(null)";
      case "F": return "(java.lang.Float) in.readValue(null)";
      case "D": return "(java.lang.Double) in.readValue(null)";
      default: return "(" + render(p.type) + ") in.readValue(null)";
    }
  }
  /** constructors whose parameters all name public, non-final instance fields of the class (MutableInt(int value)) */
  void planFieldConstructors() {
    for (String n : new ArrayList<>(gen.keySet())) {
      VClass c = gen.get(n);
      ClassNode jn = node(n);
      if (jn == null || (jn.access & (Opcodes.ACC_INTERFACE | Opcodes.ACC_ENUM | Opcodes.ACC_ANNOTATION)) != 0) continue;
      Map<String, FieldNode> fs = new HashMap<>();
      for (VMember vf : c.fields) {
        FieldNode f = field(n, vf.name);
        if (f != null && (f.access & (Opcodes.ACC_STATIC | Opcodes.ACC_FINAL)) == 0 && (f.access & Opcodes.ACC_PUBLIC) != 0) fs.put(f.name, f);
      }
      if (fs.isEmpty()) continue;
      for (VMember m : sorted(c.methods)) {
        if (!m.name.equals("<init>")) continue;
        MethodNode mn = mnOf(n, m);
        if (mn == null) continue;
        Type[] args = Type.getArgumentTypes(mn.desc);
        if (args.length == 0) continue;
        List<String> names = paramNames(mn, args.length, true);
        StringBuilder body = new StringBuilder("this((a2w.Hidden) null);");
        boolean ok = true;
        for (int i = 0; i < args.length && ok; i++) {
          FieldNode f = fs.get(names.get(i));
          if (f == null || !f.desc.equals(args[i].getDescriptor())) ok = false;
          else body.append(" this.").append(f.name).append(" = ").append(names.get(i)).append(';');
        }
        if (ok) bodiesOf(n).put("<init>" + m.desc, body.toString());
      }
    }
  }
  void planBuilder(String n, String bn, Map<String, Prop> ps) {
    VClass b = gen.get(bn);
    ClassNode bj = node(bn);
    if (bj == null || (bj.access & Opcodes.ACC_ABSTRACT) != 0 || (innerAccess(bj) & Opcodes.ACC_STATIC) == 0) return;
    String self = src(n), bself = src(bn);
    Map<String, T> env = classEnv(bn);
    Map<String, Prop> held = new LinkedHashMap<>();
    Map<String, String> bb = new HashMap<>();
    VMember build = null;
    for (VMember m : sorted(b.methods)) {
      if (m.name.equals("build") && m.desc.equals("()L" + n + ";")) { build = m; continue; }
      if (m.name.startsWith("<")) continue;
      MSig ms = sigOf(bn, m);
      MethodNode mn = mnOf(bn, m);
      if (mn == null || (mn.access & Opcodes.ACC_STATIC) != 0) continue;
      String ret = erase(ms.ret, env, new HashSet<>());
      if (!ret.equals("L" + target(bn) + ";")) continue;
      List<String> names = paramNames(mn, ms.params.size(), true);
      if (m.name.length() > 3 && m.name.startsWith("set") && Character.isUpperCase(m.name.charAt(3)) && ms.params.size() == 1) {
        Prop p = match(ps, m.name.substring(3), erase(ms.params.get(0), boundsEnv(ms.tparams, env), new HashSet<>()));
        if (p == null) continue;
        held.put(p.name, p);
        String r = ms.ret instanceof Var v ? " return (" + v.name + ") this;" : " return this;";
        bb.put(m.name + m.desc, "this." + field(p) + " = " + (p.collection ? "new java.util.ArrayList<>(" + names.get(0) + ")" : assign(p, ms.params.get(0), names.get(0))) + ";" + r);
      } else if (m.name.length() > 3 && m.name.startsWith("add") && Character.isUpperCase(m.name.charAt(3)) && ms.params.size() == 1) {
        String base = decap(m.name.substring(3));
        String pe = erase(ms.params.get(0), boundsEnv(ms.tparams, env), new HashSet<>());
        Prop p = null;
        for (String cand : List.of(base + "s", base + "es", base + "List", base.endsWith("y") ? base.substring(0, base.length() - 1) + "ies" : base))
          for (Prop q : ps.values()) if (q.collection && q.name.equals(cand) && q.elem.equals(pe) && !q.erased.equals("Ljava/util/Collection;")) p = q;
        if (p == null) continue;
        held.put(p.name, p);
        bb.put(m.name + m.desc, "this." + field(p) + ".add(" + names.get(0) + ");" + (ms.ret instanceof Var v ? " return (" + v.name + ") this;" : " return this;"));
      } else if (m.name.length() > 5 && m.name.startsWith("clear") && ms.params.isEmpty()) {
        Prop p = ps.get(decap(m.name.substring(5)));
        if (p == null || !p.collection) continue;
        held.put(p.name, p);
        bb.put(m.name + m.desc, "this." + field(p) + ".clear();" + (ms.ret instanceof Var v ? " return (" + v.name + ") this;" : " return this;"));
      }
    }
    if (build == null) return;
    // constructors: properties by parameter name, or a copy of an instance
    for (VMember m : sorted(b.methods)) {
      if (!m.name.equals("<init>")) continue;
      MSig ms = sigOf(bn, m);
      MethodNode mn = mnOf(bn, m);
      if (mn == null) continue;
      List<String> names = paramNames(mn, ms.params.size(), true);
      if (ms.params.size() == 1 && erase(ms.params.get(0), env, new HashSet<>()).equals("L" + target(n) + ";")) {
        StringBuilder body = new StringBuilder("this((a2w.Hidden) null);");
        for (Prop p : ps.values()) {
          held.put(p.name, p);
          body.append(" this.").append(field(p)).append(" = ").append(p.collection ? "new java.util.ArrayList<>(" + names.get(0) + "." + field(p) + ")" : names.get(0) + "." + field(p)).append(';');
        }
        bb.put("<init>" + m.desc, body.toString());
        continue;
      }
      StringBuilder body = new StringBuilder("this((a2w.Hidden) null);");
      boolean ok = true;
      for (int i = 0; i < ms.params.size() && ok; i++) {
        String nm = names.get(i);
        if (nm.matches("p\\d+")) { ok = false; break; }
        Prop p = match(ps, nm, erase(ms.params.get(i), boundsEnv(ms.tparams, env), new HashSet<>()));
        if (p == null) ok = false;
        else { held.put(p.name, p); body.append(" this.").append(field(p)).append(" = ").append(p.collection ? "new java.util.ArrayList<>(" + nm + ")" : assign(p, ms.params.get(i), nm)).append(';'); }
      }
      if (ok) bb.put("<init>" + m.desc, body.toString());
    }
    if (held.isEmpty()) return;
    StringBuilder body = new StringBuilder(self).append(" x = new ").append(self).append("((a2w.Hidden) null);");
    for (Prop p : held.values()) {
      body.append(" x.").append(field(p)).append(" = ").append(p.collection ? "new java.util.ArrayList<>(this." + field(p) + ")" : "this." + field(p)).append(';');
      p.backed = true;
    }
    bb.put("build()L" + n + ";", body.append(" return x;").toString());
    bodies.computeIfAbsent(bn, k -> new HashMap<>()).putAll(bb);
    List<String> decls = new ArrayList<>();
    for (Prop p : held.values()) {
      String t = p.collection ? "java.util.List" + (p.type instanceof Cls k && !k.args.isEmpty() ? render(p.type).substring(render(p.type).indexOf('<')) : "") : render(p.type);
      decls.add("private " + t + " " + field(p) + (p.collection ? " = new java.util.ArrayList<>();" : ";"));
    }
    extraFields.computeIfAbsent(bn, k -> new ArrayList<>()).addAll(decls);
  }

  String superThrows;
  Map<String, Seen> curInherited = Map.of();
  /** the super(...) call of the hidden constructor; sets superThrows */
  String superCall(String self, String sup, CSig cs) {
    superThrows = null;
    if (sup == null || sup.equals("java/lang/Object")) return "";
    if (has(sup)) return "super((a2w.Hidden) null);";
    ClassNode sn = runtimeNode(sup), jn = javacNode(sup);
    if (sn == null) return "";
    MethodNode best = null;
    for (MethodNode m : sn.methods) {
      if (!m.name.equals("<init>") || (m.access & (Opcodes.ACC_PUBLIC | Opcodes.ACC_PROTECTED)) == 0 || (m.access & Opcodes.ACC_SYNTHETIC) != 0) continue;
      if (jn != sn && !hasCtor(jn, m.desc)) continue;
      boolean throwsChecked = m.exceptions != null && !m.exceptions.isEmpty();
      if (best == null) { best = m; continue; }
      int a = Type.getArgumentTypes(m.desc).length, bl = Type.getArgumentTypes(best.desc).length;
      boolean bt = best.exceptions != null && !best.exceptions.isEmpty();
      if ((bt && !throwsChecked) || (bt == throwsChecked && a < bl)) best = m;
    }
    if (best == null) return null;
    if (best.exceptions != null && !best.exceptions.isEmpty()) superThrows = "java.lang.Exception";
    StringBuilder b = new StringBuilder("super(");
    Type[] ps = Type.getArgumentTypes(best.desc);
    for (int i = 0; i < ps.length; i++) {
      if (i > 0) b.append(", ");
      T t = SigParser.fromType(ps[i]);
      if (t instanceof Prim) b.append(defaultValue(t));
      else b.append("(").append(runtimeSrc(ps[i])).append(") null");
    }
    return b.append(");").toString();
  }
  /** a runtime class name's supertypes (generated classes: from their SDK declaration) */
  final Map<String, String> rev = new HashMap<>();
  List<String> supersOf(String r) {
    List<String> out = new ArrayList<>();
    String sdkName = rev.get(r);
    if (sdkName != null) {
      ClassNode jn = node(sdkName);
      List<String> raw = new ArrayList<>();
      if (jn != null) { if (jn.superName != null) raw.add(jn.superName); raw.addAll(jn.interfaces); }
      else { VClass c = universe.get(sdkName); if (c != null) { if (c.sup != null) raw.add(c.sup); raw.addAll(c.itfs); } }
      for (String x : raw) { String t = target(x); if (t != null) out.add(t); }
      return out;
    }
    Link.CI ci = R.L.world.get(r);
    if (ci == null) { ClassNode h = host(r); if (h != null) { if (h.superName != null) out.add(h.superName); out.addAll(h.interfaces); } return out; }
    if (ci.sup != null) out.add(ci.sup);
    out.addAll(ci.itfs);
    return out;
  }
  boolean subtype(String a, String b) {
    Deque<String> q = new ArrayDeque<>(List.of(a));
    Set<String> seen = new HashSet<>();
    while (!q.isEmpty()) {
      String x = q.poll();
      if (x.equals(b)) return true;
      // TeaVM's Throwable extends RuntimeException (an implementation detail)
      if (seen.add(x) && !x.equals("java/lang/Throwable")) q.addAll(supersOf(x));
    }
    return b.equals("java/lang/Object");
  }
  /** a return descriptor may override another if equal, or a subtype (references) */
  boolean assignable(String d, String od) {
    if (d.equals(od)) return true;
    if (!d.startsWith("L") || !od.startsWith("L")) return d.startsWith("[") && od.equals("Ljava/lang/Object;");
    return subtype(d.substring(1, d.length() - 1), od.substring(1, od.length() - 1));
  }
  boolean isUnchecked(String e) { return subtype(e, "java/lang/RuntimeException") || subtype(e, "java/lang/Error"); }
  static boolean hasCtor(ClassNode cn, String desc) {
    if (cn == null) return false;
    for (MethodNode m : cn.methods)
      if (m.name.equals("<init>") && m.desc.equals(desc) && (m.access & (Opcodes.ACC_PUBLIC | Opcodes.ACC_PROTECTED)) != 0) return true;
    return false;
  }
  /** source form of a type of the runtime (already its own name) */
  String runtimeSrc(Type t) {
    if (t.getSort() == Type.ARRAY) { StringBuilder b = new StringBuilder(runtimeSrc(t.getElementType())); for (int i = 0; i < t.getDimensions(); i++) b.append("[]"); return b.toString(); }
    if (t.getSort() == Type.OBJECT) return t.getInternalName().replace('/', '.').replace('$', '.');
    return render(SigParser.fromType(t));
  }
  /** an exception constructor that the superclass has with the same parameters: implemented by passing them on */
  String passThrough(String sup, String desc) {
    if (sup == null) return null;
    if (has(sup)) {
      VClass s = gen.get(sup);
      for (VMember m : s.methods) if (m.name.equals("<init>") && m.desc.equals(desc)) return passThrough(superOf(sup), desc) != null ? "" : null;
      return null;
    }
    ClassNode sn = runtimeNode(sup);
    if (sn == null) return null;
    String mapped = R.mapDesc(desc);
    for (MethodNode m : sn.methods)
      if (m.name.equals("<init>") && (m.desc.equals(mapped) || m.desc.equals(desc)) && (m.access & (Opcodes.ACC_PUBLIC | Opcodes.ACC_PROTECTED)) != 0) return "";
    return null;
  }
  String superOf(String n) {
    ClassNode jn = node(n);
    if (jn != null) return jn.superName;
    VClass c = universe.get(n);
    return c == null ? null : c.sup;
  }
  boolean isThrowable(String n) {
    for (int i = 0; n != null && i < 20; i++) {
      if (n.equals("java/lang/Throwable")) return true;
      if (has(n) || node(n) != null) { n = superOf(n); continue; }
      String t = R.provider(n);
      Link.CI ci = t == null ? null : R.L.world.get(t);
      if (ci == null) return false;
      for (Link.CI k : R.L.supers(ci)) if (k.name.equals("java/lang/Throwable")) return true;
      return false;
    }
    return false;
  }
  /** a class whose methods are all abstract or callbacks: its no-argument constructor has nothing to set up */
  boolean callbacksOnly(VClass c) {
    for (VMember m : c.methods) {
      if (m.name.startsWith("<")) continue;
      Object[] d = method(c.name, m.name, m.desc);
      MethodNode mn = d == null ? null : (MethodNode) d[1];
      if (mn != null && (mn.access & Opcodes.ACC_ABSTRACT) != 0) continue;
      if (callback(m.name, Type.getReturnType(m.desc)) && callbackClass(c)) continue;
      return false;
    }
    return true;
  }
  boolean callbackClass(VClass c) {
    ClassNode jn = node(c.name);
    if (jn != null && (jn.access & (Opcodes.ACC_ABSTRACT | Opcodes.ACC_INTERFACE)) != 0) return true;
    return c.name.matches(".*(Callback|Callbacks|Listener|Observer|Monitor|Watcher)");
  }
  static boolean callback(String name, Type ret) {
    return ret.getSort() == Type.VOID && name.length() > 2 && name.startsWith("on") && Character.isUpperCase(name.charAt(2));
  }

  void emitMethod(StringBuilder b, String in2, VClass c, String qname, String name, int macc, MSig ms, List<String> names, boolean itf,
                  boolean ann, MethodNode mn, String sup, CSig cs) {
    boolean st = (macc & Opcodes.ACC_STATIC) != 0, abs = (macc & Opcodes.ACC_ABSTRACT) != 0;
    StringBuilder h = new StringBuilder();
    if (ann) {
      b.append(in2).append(render(ms.ret)).append(' ').append(name).append("()");
      if (mn != null && mn.annotationDefault != null) {
        String dv = annotationValue(mn.annotationDefault);
        if (dv != null) b.append(" default ").append(dv);
      }
      b.append(";\n");
      return;
    }
    // an empty onX callback is the platform's own behaviour in listener and callback types, which exist to be overridden
    boolean cb = callback(name, typeOf(ms.ret)) && (itf || callbackClass(c));
    String supCall = !itf && !st && !abs ? superMethod(sup, name, ms, names) : null;
    String absentBody = !abs && !cb ? absent(c.name, name, ms) : null;
    boolean impl = abs || cb || absentBody != null;
    if (absentBody != null) h.append("@a2w.Absent ");
    else if (!impl) h.append("@a2w.NotImplemented ");
    if (itf) { if (st) h.append("static "); else if (!abs) h.append("default "); }
    else {
      h.append((macc & Opcodes.ACC_PROTECTED) != 0 ? "protected " : "public ");
      if (st) h.append("static ");
      if (abs) h.append("abstract ");
      if ((macc & Opcodes.ACC_FINAL) != 0) h.append("final ");
    }
    h.append(renderTParams(ms.tparams)).append(render(ms.ret)).append(' ').append(name).append('(').append(params(ms, names, macc)).append(')')
        .append(throwsClause(ms, null));
    b.append(in2).append(h);
    if (abs) { b.append(";\n"); return; }
    if (absentBody != null) { b.append(" { ").append(absentBody).append(" }\n"); return; }
    b.append(" {");
    if (!cb) b.append(" a2w.Stubs.hit(\"").append(qname).append('.').append(name).append("\");");
    boolean isVoid = ms.ret instanceof Prim p && p.c == 'V';
    if (supCall != null) b.append(isVoid ? " " : " return ").append(supCall).append(';');
    else if (!isVoid) b.append(" return ").append(defaultValue(ms.ret)).append(';');
    b.append(" }\n");
  }
  // ================================================================ absent hardware (tools/genapi-absent.txt)
  final List<String> absentPrefixes = new ArrayList<>();
  final Map<String, String> absentOverrides = new HashMap<>();
  void readAbsent(Path p) throws IOException {
    for (String l : Files.readAllLines(p)) {
      l = l.strip();
      if (l.isEmpty() || l.startsWith("#")) continue;
      if (l.startsWith("feature ")) { absentPrefixes.add(l.split("\\s+")[1]); continue; }
      int eq = l.indexOf(" = ");
      if (eq > 0) absentOverrides.put(l.substring(0, eq).strip(), l.substring(eq + 3).strip());
    }
  }
  boolean isAbsent(String cls) {
    for (String pre : absentPrefixes) if (cls.startsWith(pre)) return true;
    return false;
  }
  /** the body of a method of a class whose feature the device lacks; null: not such a class */
  String absent(String cls, String name, MSig ms) {
    if (!isAbsent(cls)) return null;
    String o = absentOverrides.get(cls + "." + name);
    if (o != null) return o;
    T r = ms.ret;
    if (r instanceof Prim p) {
      switch (p.c) {
        case 'V': return "";
        case 'Z': return "return false;";
        default: return "return " + defaultValue(r) + ";";
      }
    }
    if (r instanceof Arr a) return a.comp instanceof Var || a.comp instanceof Cls k && !k.args.isEmpty() ? "return null;" : "return new " + render(r).replaceFirst("\\[\\]", "[0]") + ";";
    if (r instanceof Cls k) {
      switch (k.name) {
        case "java/util/List": case "java/util/Collection": return "return java.util.Collections.emptyList();";
        case "java/util/Set": return "return java.util.Collections.emptySet();";
        case "java/util/Map": return "return java.util.Collections.emptyMap();";
        default: return "return null;";
      }
    }
    return "return null;";
  }
  static Type typeOf(T t) { return t instanceof Prim p ? Type.getType(String.valueOf(p.c)) : Type.getType(Object.class); }
  /** super.name(args) when the superclass chain of the runtime has that method concrete, with no checked exceptions */
  String superMethod(String sup, String name, MSig ms, List<String> names) {
    if (sup == null) return null;
    List<Seen> all = new ArrayList<>();
    collect(new Cls(sup), Map.of(), all, new HashSet<>(), 1, false);
    String pd = erasedParams(ms);
    for (Seen x : all) {
      if (!x.name.equals(name) || !x.params.equals(pd)) continue;
      if ((x.access & Opcodes.ACC_ABSTRACT) != 0 || (x.access >> 16 & Opcodes.ACC_INTERFACE) != 0) return null;
      if (x.exc != null && !x.exc.isEmpty()) return null;
      if (!x.ret.equals(erase(ms.ret, boundsEnv(ms.tparams, Map.of()), new HashSet<>()))) return null;
      return "super." + name + "(" + String.join(", ", names) + ")";
    }
    return null;
  }
  String erasedParams(MSig ms) {
    Map<String, T> env = boundsEnv(ms.tparams, Map.of());
    StringBuilder b = new StringBuilder("(");
    for (T p : ms.params) b.append(erase(p, env, new HashSet<>()));
    return b.append(')').toString();
  }
  boolean allNameable(MSig ms) {
    for (T p : ms.params) if (!nameable(p)) return false;
    if (!nameable(ms.ret)) return false;
    for (T e : ms.exc) if (!nameable(e)) return false;
    for (TParam p : ms.tparams) for (T bd : p.bounds) if (!nameable(bd)) return false;
    return true;
  }
  String params(MSig ms, List<String> names, int macc) {
    StringBuilder b = new StringBuilder();
    for (int i = 0; i < ms.params.size(); i++) {
      if (i > 0) b.append(", ");
      String t = render(usable(ms.params.get(i)));
      if (i == ms.params.size() - 1 && (macc & Opcodes.ACC_VARARGS) != 0 && t.endsWith("[]")) t = t.substring(0, t.length() - 2) + "...";
      b.append(t).append(' ').append(names.get(i));
    }
    return b.toString();
  }
  String throwsClause(MSig ms, String extra) {
    List<String> ex = new ArrayList<>();
    for (T e : ms.exc) ex.add(render(e));
    if (extra != null && !ex.contains(extra)) ex.add(extra);
    return ex.isEmpty() ? "" : " throws " + String.join(", ", ex);
  }
  static final Set<String> RESERVED = Set.of("a2w", "java", "javax", "android", "org", "dalvik", "hidden");
  static List<String> paramNames(MethodNode mn, int n, boolean instance) {
    List<String> out = new ArrayList<>();
    if (mn != null && mn.localVariables != null) {
      Type[] ts = Type.getArgumentTypes(mn.desc);
      int slot = (mn.access & Opcodes.ACC_STATIC) != 0 ? 0 : 1;
      // signature parameters may be fewer than the descriptor's (synthetic ones first)
      int skip = ts.length - n;
      for (int i = 0; i < ts.length; i++) {
        if (i >= skip) {
          String name = null;
          for (LocalVariableNode lv : mn.localVariables) if (lv.index == slot && lv.start == mn.localVariables.get(0).start) { name = lv.name; break; }
          if (name == null) for (LocalVariableNode lv : mn.localVariables) if (lv.index == slot) { name = lv.name; break; }
          out.add(name == null || RESERVED.contains(name) || out.contains(name) || !name.matches("[A-Za-z_][A-Za-z0-9_]*") ? "p" + (i - skip) : name);
        }
        slot += ts[i].getSize();
      }
    }
    while (out.size() < n) out.add("p" + out.size());
    return out;
  }
  List<VMember> sorted(List<VMember> ms) {
    List<VMember> l = new ArrayList<>(ms);
    l.sort(Comparator.comparing((VMember m) -> m.name).thenComparing(m -> m.desc));
    return l;
  }
  static boolean tvarFree(T t) {
    if (t instanceof Var) return false;
    if (t instanceof Arr a) return tvarFree(a.comp);
    if (t instanceof Cls c) for (Arg x : c.args) if (x.type != null && !tvarFree(x.type)) return false;
    return true;
  }
  static String defaultValue(T t) {
    if (t instanceof Prim p) {
      switch (p.c) {
        case 'Z': return "false"; case 'C': return "(char) 0"; case 'B': return "(byte) 0"; case 'S': return "(short) 0";
        case 'J': return "0L"; case 'F': return "0f"; case 'D': return "0d"; default: return "0";
      }
    }
    return "null";
  }
  static String literal(String desc, Object v) {
    switch (desc) {
      case "Z": return ((Integer) v) != 0 ? "true" : "false";
      case "C": return "(char) " + v;
      case "B": return "(byte) " + v;
      case "S": return "(short) " + v;
      case "J": return v + "L";
      case "F": {
        float f = (Float) v;
        if (Float.isNaN(f)) return "0.0f / 0.0f";
        if (Float.isInfinite(f)) return f > 0 ? "1.0f / 0.0f" : "-1.0f / 0.0f";
        return Float.toString(f) + "f";
      }
      case "D": {
        double d = (Double) v;
        if (Double.isNaN(d)) return "0.0d / 0.0d";
        if (Double.isInfinite(d)) return d > 0 ? "1.0d / 0.0d" : "-1.0d / 0.0d";
        return Double.toString(d) + "d";
      }
      case "I": return Integer.toString((Integer) v);
      default: return v instanceof String s ? quote(s) : String.valueOf(v);
    }
  }
  static String quote(String s) {
    StringBuilder b = new StringBuilder("\"");
    for (char ch : s.toCharArray()) {
      switch (ch) {
        case '"': b.append("\\\""); break;
        case '\\': b.append("\\\\"); break;
        case '\n': b.append("\\n"); break;
        case '\r': b.append("\\r"); break;
        case '\t': b.append("\\t"); break;
        default: if (ch < 32 || ch > 126) b.append(String.format("\\u%04x", (int) ch)); else b.append(ch);
      }
    }
    return b.append('"').toString();
  }
  String annotationValue(Object v) {
    if (v instanceof String s) return quote(s);
    if (v instanceof Integer || v instanceof Short || v instanceof Byte) return v.toString();
    if (v instanceof Long) return v + "L";
    if (v instanceof Boolean) return v.toString();
    if (v instanceof Character ch) return "'" + (ch == '\'' ? "\\'" : ch == '\\' ? "\\\\" : String.valueOf(ch)) + "'";
    if (v instanceof Float f) return literal("F", f);
    if (v instanceof Double d) return literal("D", d);
    if (v instanceof Type t) { String n = t.getSort() == Type.OBJECT ? t.getInternalName() : null; return n != null && target(n) != null ? src(n) + ".class" : null; }
    if (v instanceof String[] e && e.length == 2) {   // enum: [descriptor, name]
      String n = Type.getType(e[0]).getInternalName();
      return target(n) != null ? src(n) + "." + e[1] : null;
    }
    if (v instanceof List<?> l) {
      List<String> parts = new ArrayList<>();
      for (Object x : l) { String p = annotationValue(x); if (p == null) return null; parts.add(p); }
      return "{" + String.join(", ", parts) + "}";
    }
    return null;
  }
}

