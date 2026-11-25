package jp;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.zip.ZipFile;

/**
 * Lists every public class, method and field of android.jar with what a converted app gets for it, resolved the way
 * the linker resolves an app's references (same providers, renames and redirections):
 *
 * <pre>
 *   +  implemented (framework, TeaVM's class library, a bundled library, or a2w.jdk.Compat)
 *   ~  present with an empty or constant body (no effect; a fixed answer)
 *   -  missing: the linker generates a stub that logs once and returns a default (0, false, null, "", empty)
 * </pre>
 * Compile-time constants are not listed: javac and d8 copy their values into the app.
 *
 * Usage: ApiReport --android android.jar --fw framework-classes --jdk teavm-classlib.jar [--lib jar]...
 *        [--versions api-versions.xml] --out docs-dir
 * With api-versions.xml (the SDK's platforms/android-N/data/api-versions.xml) the list covers every API level,
 * removed APIs included, and each member carries the levels it exists at; without it, android.jar's API only.
 */
public final class ApiReport {
  static final int IMPL = 0, FIXED = 1, MISSING = 2, DERIVED = 3;
  static final char[] MARK = {'+', '~', '-', '*'};

  final Link L = new Link();

  public static void main(String[] a) throws Exception { new ApiReport().run(a); }

  void run(String[] a) throws Exception {
    String androidJar = null, fw = null, jdk = null, out = null, versions = null, tsvOut = null;
    List<String> libs = new ArrayList<>(), appLibs = new ArrayList<>();
    for (int i = 0; i < a.length; i++) {
      switch (a[i]) {
        case "--android": androidJar = a[++i]; break;
        case "--fw": fw = a[++i]; break;
        case "--jdk": jdk = a[++i]; break;
        case "--lib": libs.add(a[++i]); break;
        case "--applib": appLibs.add(a[++i]); break;   // linked like app code (the Apache HTTP client): JDK names remapped
        case "--tsv": tsvOut = a[++i]; break;           // every member with its status, for analysis
        case "--out": out = a[++i]; break;
        case "--versions": versions = a[++i]; break;
        default: throw new IllegalArgumentException(a[i]);
      }
    }
    load(fw, jdk, libs, appLibs);

    // android.jar (API 35): access flags and constant values; api-versions.xml: every API level's classes and members
    Map<String, ClassNode> jar = new HashMap<>();
    try (ZipFile z = new ZipFile(androidJar)) {
      for (var e = z.entries(); e.hasMoreElements(); ) {
        var ze = e.nextElement();
        if (!ze.getName().endsWith(".class")) continue;
        ClassNode cn = Link.read(z.getInputStream(ze).readAllBytes());
        if (!visible(cn.access) || (cn.access & Opcodes.ACC_SYNTHETIC) != 0 || !publicNesting(cn)) continue;
        jar.put(cn.name, cn);
      }
    }
    List<VClass> universe = versions != null ? Versions.read(Paths.get(versions)) : Versions.fromJar(jar.values());
    // api-versions.xml leaves out overrides that android.jar declares: those are added (at their class's levels)
    if (versions != null) Versions.merge(universe, jar.values());
    if (tsvOut != null) tsv = new StringBuilder();
    TreeMap<String, List<VClass>> byPkg = new TreeMap<>();
    for (VClass c : universe) {
      String pkg = c.name.contains("/") ? c.name.substring(0, c.name.lastIndexOf('/')).replace('/', '.') : "";
      byPkg.computeIfAbsent(pkg, k -> new ArrayList<>()).add(c);
    }
    Path od = Paths.get(out);
    Path api = od.resolve("api");
    Files.createDirectories(api);
    try (var s = Files.list(api)) { for (Path p : (Iterable<Path>) s::iterator) if (p.toString().endsWith(".md")) Files.delete(p); }

    StringBuilder summary = new StringBuilder();
    long[] total = new long[8];
    summary.append("| Package | Classes (written / declared / all) | Methods + | * | ~ | - | Fields + | - |\n| --- | --- | --- | --- | --- | --- | --- | --- |\n");
    for (var en : byPkg.entrySet()) {
      List<VClass> classes = en.getValue();
      classes.sort(Comparator.comparing(c -> c.name));
      StringBuilder b = new StringBuilder();
      long[] cnt = new long[8];   // classes written, classes, methods +, ~, -, fields +, classes declared from the SDK, methods *
      long fieldsMissing = 0;
      b.append("# ").append(en.getKey()).append("\n\n`+` implemented, `*` derived from the declaration (a data class or builder: stores and "
          + "returns values), `~` empty or constant body, `-` missing (a stub that logs and returns a default). \"declaration\": the class is "
          + "declared from the SDK (runtime/framework-gen). Constants are not listed. See [API.md](../API.md).\n\n");
      for (VClass c : classes) {
        ClassNode jc = jar.get(c.name);
        String prov = provider(c.name);
        Link.CI pc = prov == null ? null : L.world.get(prov);
        cnt[1]++;
        if (pc != null && (pc.node == null || !generated(pc.node))) cnt[0]++;
        else if (pc != null) cnt[6]++;
        List<String> lines = new ArrayList<>();
        int[] mc = new int[4];
        int fPlus = 0, fMinus = 0;
        Map<String, List<String>> byParams = new TreeMap<>();   // name(params) -> "return levels mark"
        for (VMember vm : c.methods) {
          if (vm.name.equals("<clinit>")) continue;
          MethodNode m = jarMethod(jc, vm.name, vm.desc);
          boolean isStatic = m != null && (m.access & Opcodes.ACC_STATIC) != 0;
          int st = status(pc, vm.name, vm.desc, isStatic, m == null);
          if (st == MISSING && pc != null && !vm.name.equals("<init>")) {
            String other = sameParams(pc, vm.name, mapDesc(vm.desc));
            if (other != null) conflicts.add("`" + c.name.replace('/', '.').replace('$', '.') + "." + signature(c.name, vm.name, vm.desc, isStatic)
                + "` " + c.levels(vm) + ": the runtime has `" + vm.name + other + "`");
          }
          mc[st]++;
          if (!vm.name.equals("<init>"))
            byParams.computeIfAbsent(vm.name + vm.desc.substring(0, vm.desc.indexOf(')') + 1), k -> new ArrayList<>())
                .add("`" + MARK[st] + "` returns `" + Type.getReturnType(vm.desc).getClassName().replace('$', '.') + "` " + c.levels(vm));
          lines.add(MARK[st] + " " + signature(c.name, vm.name, vm.desc, isStatic) + "  " + c.levels(vm));
          if (tsv != null) tsv.append(MARK[st]).append('\t').append(c.name).append('\t').append(kind(jc)).append('\t').append(pc != null)
              .append('\t').append(vm.name).append('\t').append(vm.desc).append('\t').append(m == null ? "?" : ((m.access & Opcodes.ACC_ABSTRACT) != 0 ? "abstract" : isStatic ? "static" : "instance"))
              .append('\t').append(c.levels(vm)).append('\n');
          for (int l = 1; l <= Versions.MAX; l++) if (vm.at(c, l)) perLevel[l][st]++;
        }
        for (Map.Entry<String, List<String>> e : byParams.entrySet())
          if (e.getValue().size() > 1) {
            String k = e.getKey(), n = k.substring(0, k.indexOf('('));
            StringBuilder ps = new StringBuilder();
            for (Type t : Type.getArgumentTypes(k.substring(k.indexOf('(')) + "V"))
              ps.append(ps.length() == 0 ? "" : ", ").append(t.getClassName().replace('$', '.').replaceAll("^java\\.lang\\.", ""));
            pairs.add("`" + c.name.replace('/', '.').replace('$', '.') + "." + n + "(" + ps + ")`: " + String.join("; ", e.getValue()));
          }
        for (VMember vf : c.fields) {
          // compile-time constants are inlined by javac/d8 into the app: never looked up, not listed
          FieldNode f = jarField(jc, vf.name);
          if (f != null && f.value != null) continue;
          if (f == null && vf.constantName()) continue;
          boolean ok = fieldPresent(c.name, pc, vf.name);
          if (ok) fPlus++; else fMinus++;
          lines.add((ok ? "+ " : "- ") + vf.name + " [field]  " + c.levels(vf));
          if (tsv != null) tsv.append(ok ? "+" : "-").append('\t').append(c.name).append('\t').append(kind(jc)).append('\t').append(pc != null)
              .append('\t').append(vf.name).append('\t').append(f == null ? vf.desc : f.desc).append("\tfield\t").append(c.levels(vf)).append('\n');
        }
        cnt[2] += mc[0]; cnt[3] += mc[1]; cnt[4] += mc[2]; cnt[5] += fPlus; cnt[7] += mc[3]; fieldsMissing += fMinus;
        String simple = c.name.substring(c.name.lastIndexOf('/') + 1).replace('$', '.');
        b.append("## ").append(simple).append(" — ").append(origin(pc)).append("; ").append(c.levels(null).replace("[", "").replace("]", "")).append(" (").append(mc[0]).append(" +, ")
            .append(mc[3]).append(" *, ").append(mc[1]).append(" ~, ").append(mc[2]).append(" -)\n\n");
        if (!lines.isEmpty()) {
          lines.sort(Comparator.comparing((String l) -> l.substring(2)));
          b.append("```\n");
          for (String l : lines) b.append(l).append('\n');
          b.append("```\n\n");
        }
      }
      Files.writeString(api.resolve(en.getKey() + ".md"), b.toString());
      summary.append("| [").append(en.getKey()).append("](api/").append(en.getKey()).append(".md) | ").append(cnt[0]).append(" / ").append(cnt[6]).append(" / ").append(cnt[1])
          .append(" | ").append(cnt[2]).append(" | ").append(cnt[7]).append(" | ").append(cnt[3]).append(" | ").append(cnt[4]).append(" | ").append(cnt[5]).append(" | ").append(fieldsMissing).append(" |\n");
      total[0] += cnt[0]; total[6] += cnt[6]; total[7] += cnt[7]; total[1] += cnt[1]; total[2] += cnt[2]; total[3] += cnt[3]; total[4] += cnt[4]; total[5] += cnt[5];
      totalFieldsMissing += fieldsMissing;
    }
    summary.append("| **all** | ").append(total[0]).append(" / ").append(total[6]).append(" / ").append(total[1]).append(" | ").append(total[2]).append(" | ").append(total[7]).append(" | ").append(total[3])
        .append(" | ").append(total[4]).append(" | ").append(total[5]).append(" | ").append(totalFieldsMissing).append(" |\n");
    Files.writeString(od.resolve("api-summary.md"), summary.toString());
    StringBuilder lv = new StringBuilder("| API level | Methods | + | * | ~ | - | + and * share |\n| --- | --- | --- | --- | --- | --- | --- |\n");
    int top = 1;
    for (VClass c : universe) {
      top = Math.max(top, c.since);
      for (VMember m : c.methods) top = Math.max(top, Math.max(m.since, m.removed));
    }
    for (int l = 1; l <= Math.min(top, Versions.MAX); l++) {
      long all = perLevel[l][0] + perLevel[l][1] + perLevel[l][2] + perLevel[l][3];
      if (all == 0) continue;
      lv.append("| ").append(l).append(" | ").append(all).append(" | ").append(perLevel[l][0]).append(" | ").append(perLevel[l][3]).append(" | ").append(perLevel[l][1])
          .append(" | ").append(perLevel[l][2]).append(" | ").append(String.format("%.1f%%", 100.0 * (perLevel[l][0] + perLevel[l][3]) / all)).append(" |\n");
    }
    Files.writeString(od.resolve("api-levels.md"), lv.toString());
    StringBuilder cf = new StringBuilder();
    cf.append("Methods with the same name and parameters and different return types at different levels:\n\n");
    for (String x : pairs) cf.append("* ").append(x).append('\n');
    if (pairs.isEmpty()) cf.append("* none\n");
    cf.append("\nMethods missing while the runtime has the same name and parameters with another return type (a call "
        + "with the SDK's descriptor gets the stub):\n\n");
    for (String x : conflicts) cf.append("* ").append(x).append('\n');
    if (conflicts.isEmpty()) cf.append("* none\n");
    Files.writeString(od.resolve("api-conflicts.md"), cf.toString());
    // platform classes the framework has that android.jar does not list (hidden and internal API apps reach)
    Set<String> sdk = new HashSet<>();
    for (List<VClass> l : byPkg.values()) for (VClass c : l) sdk.add(c.name);
    try (ZipFile z = new ZipFile(androidJar)) {
      for (var e = z.entries(); e.hasMoreElements(); ) { String n = e.nextElement().getName(); if (n.endsWith(".class")) sdk.add(n.substring(0, n.length() - 6)); }
    }
    TreeSet<String> hidden = new TreeSet<>();
    for (Link.CI c : L.world.values())
      if (c.origin == Link.FW && (c.name.startsWith("android/") || c.name.startsWith("dalvik/") || c.name.startsWith("com/android/"))
          && !sdk.contains(c.name) && (c.access & Opcodes.ACC_PUBLIC) != 0 && !c.name.contains("$") && !WEB_HELPERS.contains(c.name))
        hidden.add(c.name.replace('/', '.'));
    StringBuilder h = new StringBuilder();
    for (String n : hidden) h.append("`").append(n).append("`, ");
    if (h.length() > 2) h.setLength(h.length() - 2);
    Files.writeString(od.resolve("api-hidden.md"), h.append('\n').toString());
    if (tsvOut != null) Files.writeString(Paths.get(tsvOut), tsv.toString());
    System.out.printf("%d written + %d declared of %d classes; methods: %d implemented, %d derived, %d fixed, %d missing; fields: %d present, %d missing%n",
        total[0], total[6], total[1], total[2], total[7], total[3], total[4], total[5], totalFieldsMissing);
  }
  /** the runtime's classes: framework, TeaVM's class library, platform libraries, the bundled Apache HTTP client */
  void load(String fw, String jdk, List<String> libs, List<String> appLibs) throws IOException {
    for (ClassNode cn : Link.readJar(fw)) L.add(cn, Link.FW);
    for (ClassNode cn : Link.readJar(jdk)) {
      if (!cn.name.startsWith("org/teavm/classlib/") && !Link.jdkExtra(cn.name)) continue;
      ClassNode m = Link.jdkMapped(cn);
      if (m != null && !L.world.containsKey(m.name)) L.add(m, Link.JDK);
    }
    for (String l : libs) for (ClassNode cn : Link.readJar(l)) if (!L.world.containsKey(cn.name)) L.add(cn, Link.LIB);
    List<ClassNode> appLib = new ArrayList<>();
    for (String l : appLibs) for (ClassNode cn : Link.readJar(l)) if (!L.world.containsKey(cn.name)) appLib.add(cn);
    Set<String> own = new HashSet<>();
    for (ClassNode cn : appLib) own.add(cn.name);
    org.objectweb.asm.commons.Remapper rm = new org.objectweb.asm.commons.Remapper() {
      @Override public String map(String n) { return own.contains(n) ? n : ApiReport.this.mapType(n); }
    };
    for (ClassNode cn : appLib) {
      ClassNode r = new ClassNode();
      cn.accept(new org.objectweb.asm.commons.ClassRemapper(r, rm));
      L.add(r, Link.LIB);
    }
  }

  long totalFieldsMissing;
  StringBuilder tsv;
  static String kind(ClassNode jc) {
    if (jc == null) return "?";
    if ((jc.access & Opcodes.ACC_ANNOTATION) != 0) return "annotation";
    if ((jc.access & Opcodes.ACC_INTERFACE) != 0) return "interface";
    if ((jc.access & Opcodes.ACC_ENUM) != 0) return "enum";
    return (jc.access & Opcodes.ACC_ABSTRACT) != 0 ? "abstract" : "class";
  }
  final long[][] perLevel = new long[Versions.MAX + 1][4];
  final TreeSet<String> conflicts = new TreeSet<>(), pairs = new TreeSet<>();
  /** the descriptor of a method of c (or its supertypes) with these parameters and another return type, or null */
  String sameParams(Link.CI c, String name, String desc) {
    String params = desc.substring(0, desc.indexOf(')') + 1);
    // declared from the SDK (a stub with the SDK's own descriptor): not a return-type mismatch
    for (Link.CI k : L.supers(c))
      if (k.methods.containsKey(name + desc) && k.node != null)
        for (MethodNode x : k.node.methods) if (x.name.equals(name) && x.desc.equals(desc) && notImplemented(x.invisibleAnnotations)) return null;
    for (Link.CI k : L.supers(c))
      for (String key : k.methods.keySet())
        if (key.startsWith(name + params) && key.length() > (name + params).length() && !key.equals(name + desc)) return key.substring(name.length());
    return null;
  }
  /** framework classes of the web runtime itself (in platform packages for package access), not platform API */
  static final Set<String> WEB_HELPERS = Set.of("android/app/ActivityHooks", "android/view/ViewHooks", "android/graphics/drawable/SpinnerDrawable");

  static boolean visible(int acc) { return (acc & (Opcodes.ACC_PUBLIC | Opcodes.ACC_PROTECTED)) != 0; }
  static boolean publicNesting(ClassNode cn) {
    if (cn.innerClasses != null)
      for (InnerClassNode ic : cn.innerClasses)
        if (ic.name.equals(cn.name) && (ic.innerName == null || !visible(ic.access))) return false;
    return true;
  }

  /** the class an app's reference to n ends up at (Link.run's renames), or null: generated from android.jar */
  String provider(String n) {
    for (String[] r : Link.PREFERRED_REMAP) if (r[0].equals(n) && L.world.containsKey(r[1])) return r[1];
    if (n.startsWith("java/security/")) {
      String s = n.substring("java/security/".length());
      for (String x : Link.SEC) if (x.equals(s) && L.world.containsKey("a2w/jdk/sec/" + s)) return "a2w/jdk/sec/" + s;
    }
    if (n.startsWith("java/") && L.world.containsKey("a2w/jdk/" + n.substring(5))) return "a2w/jdk/" + n.substring(5);
    if (L.world.containsKey(n)) return n;
    for (String[] pp : Link.PREFERRED_PREFIX)
      if (n.startsWith(pp[0]) && L.world.containsKey(pp[1] + n.substring(pp[0].length()))) return pp[1] + n.substring(pp[0].length());
    return null;
  }
  String mapType(String n) { String p = provider(n); return p != null ? p : Link.isJdkName(n) ? "a2w/jdkx/" + n : n; }
  String mapDesc(String desc) {
    StringBuilder b = new StringBuilder();
    for (int i = 0; i < desc.length(); i++) {
      char ch = desc.charAt(i);
      b.append(ch);
      if (ch == 'L') {
        int e = desc.indexOf(';', i);
        b.append(mapType(desc.substring(i + 1, e))).append(';');
        i = e;
      }
    }
    return b.toString();
  }

  static String origin(Link.CI pc) {
    if (pc == null) return "missing";
    switch (pc.origin) {
      case Link.FW:
        if (pc.node != null && generated(pc.node)) return "declaration";
        return pc.name.startsWith("a2w/") ? "framework (" + pc.name.replace('/', '.') + ")" : "framework";
      case Link.JDK: return "TeaVM class library";
      case Link.LIB: return "bundled library";
      default: return "present";
    }
  }

  int status(Link.CI pc, String name, String rawDesc, boolean isStatic, boolean staticUnknown) {
    if (pc == null) return MISSING;
    String desc = mapDesc(rawDesc);
    Link.CI decl = find(pc, name, desc);
    if (decl == null) {
      if (Link.RETARGETED.contains(pc.name + "." + name + rawDesc)) return IMPL;
      if (pc.origin != Link.FW) {
        String simple = pc.name.substring(pc.name.lastIndexOf('/') + 1).replace('$', '_');
        Link.CI compat = L.world.get("a2w/jdk/Compat");
        String idesc = "(L" + pc.name + ";" + desc.substring(1);
        if (compat != null && (compat.methods.containsKey(simple + "_" + name + (isStatic ? desc : idesc))
            || staticUnknown && compat.methods.containsKey(simple + "_" + name + desc))) return IMPL;
        Link.CI sub = L.world.get("a2w/jdk/ctor/" + simple);
        if (name.equals("<init>") && sub != null && sub.methods.containsKey("<init>" + desc)) return IMPL;
      }
      return MISSING;
    }
    if (decl.node == null || decl.origin != Link.FW) return IMPL;
    MethodNode body = null;
    for (MethodNode x : decl.node.methods) if (x.name.equals(name) && x.desc.equals(desc)) { body = x; break; }
    if (body != null && notImplemented(body.invisibleAnnotations)) return MISSING;   // a generated declaration's stub body
    if (body != null && hasAnnotation(body.invisibleAnnotations, "La2w/Derived;")) return DERIVED;
    // what a device without the hardware answers (tools/genapi-absent.txt)
    if (body != null && hasAnnotation(body.invisibleAnnotations, "La2w/Absent;")) return IMPL;
    if (body == null || (body.access & Opcodes.ACC_ABSTRACT) != 0 || (body.access & Opcodes.ACC_NATIVE) != 0) return IMPL;
    // callbacks (onX) are meant to be overridden: an empty one is the platform's own behaviour
    if (name.length() > 2 && name.startsWith("on") && Character.isUpperCase(name.charAt(2))) return IMPL;
    return trivial(body) ? FIXED : IMPL;
  }

  /** declared in c, its superclasses or interfaces (no classes are made up on the way) */
  Link.CI find(Link.CI c, String name, String desc) {
    for (Link.CI k : L.supers(c)) {
      if (k.methods.containsKey(name + desc)) return k;
      if (name.equals("<init>")) return null;
    }
    ArrayDeque<Link.CI> q = new ArrayDeque<>();
    Set<String> seen = new HashSet<>();
    for (Link.CI k : L.supers(c)) for (String i : k.itfs) if (L.world.get(i) != null) q.add(L.world.get(i));
    while (!q.isEmpty()) {
      Link.CI k = q.poll();
      if (!seen.add(k.name)) continue;
      if (k.methods.containsKey(name + desc)) return k;
      for (String i : k.itfs) if (L.world.get(i) != null) q.add(L.world.get(i));
    }
    Link.CI o = L.world.get("java/lang/Object");
    return o != null && o.methods.containsKey(name + desc) ? o : null;
  }

  /** an empty body, or one that returns a constant, null or this */
  static boolean trivial(MethodNode m) {
    List<AbstractInsnNode> code = new ArrayList<>();
    for (AbstractInsnNode n : m.instructions) if (n.getOpcode() >= 0) code.add(n);
    if (code.size() == 1) return code.get(0).getOpcode() == Opcodes.RETURN;
    // throws unconditionally: new X(constant or argument...) and athrow, nothing else
    if (code.get(code.size() - 1).getOpcode() == Opcodes.ATHROW && code.size() <= 8) {
      boolean onlyThrow = true;
      for (AbstractInsnNode n : code.subList(0, code.size() - 1)) {
        int o = n.getOpcode();
        boolean ok = o == Opcodes.NEW || o == Opcodes.DUP || o == Opcodes.LDC || o >= Opcodes.ACONST_NULL && o <= Opcodes.SIPUSH
            || o >= Opcodes.ILOAD && o <= Opcodes.ALOAD
            || o == Opcodes.INVOKESPECIAL && ((MethodInsnNode) n).name.equals("<init>");
        if (!ok) { onlyThrow = false; break; }
      }
      if (onlyThrow) return true;
    }
    if (code.size() != 2) return false;
    int op = code.get(0).getOpcode(), ret = code.get(1).getOpcode();
    if (ret < Opcodes.IRETURN || ret > Opcodes.ARETURN) return false;
    boolean constant = op >= Opcodes.ACONST_NULL && op <= Opcodes.DCONST_1 || op == Opcodes.BIPUSH || op == Opcodes.SIPUSH || op == Opcodes.LDC;
    boolean self = op == Opcodes.ALOAD && ((VarInsnNode) code.get(0)).var == 0 && (m.access & Opcodes.ACC_STATIC) == 0;
    return constant || self;
  }

  /** declared from the SDK by tools/genapi.sh (runtime/framework-gen) */
  static boolean generated(ClassNode cn) {
    if (cn.invisibleAnnotations != null) for (AnnotationNode an : cn.invisibleAnnotations) if (an.desc.equals("La2w/Generated;")) return true;
    return false;
  }
  static boolean hasAnnotation(List<AnnotationNode> as, String desc) {
    if (as != null) for (AnnotationNode an : as) if (an.desc.equals(desc)) return true;
    return false;
  }
  static boolean notImplemented(List<AnnotationNode> as) {
    if (as != null) for (AnnotationNode an : as) if (an.desc.equals("La2w/NotImplemented;")) return true;
    return false;
  }
  boolean fieldPresent(String cname, Link.CI pc, String fname) {
    if (pc == null) return false;
    for (Link.CI k : L.supers(pc))
      if (k.node != null) for (FieldNode f : k.node.fields) if (f.name.equals(fname) && notImplemented(f.invisibleAnnotations)) return false;
    for (Link.CI k : L.supers(pc)) {
      if (k.fields.containsKey(fname)) return true;
      for (String i : k.itfs) if (fieldInItf(L.world.get(i), fname, new HashSet<>())) return true;
    }
    // a JDK field TeaVM maps to an accessor counts as there (the linker keeps such references)
    if (pc.origin == Link.JDK) return true;
    return false;
  }
  boolean fieldInItf(Link.CI c, String name, Set<String> seen) {
    if (c == null || !seen.add(c.name)) return false;
    if (c.fields.containsKey(name)) return true;
    for (String i : c.itfs) if (fieldInItf(L.world.get(i), name, seen)) return true;
    return false;
  }

  static String simpleType(Type t) {
    switch (t.getSort()) {
      case Type.ARRAY: { StringBuilder b = new StringBuilder(simpleType(t.getElementType())); for (int i = 0; i < t.getDimensions(); i++) b.append("[]"); return b.toString(); }
      case Type.OBJECT: { String n = t.getInternalName(); return n.substring(n.lastIndexOf('/') + 1).replace('$', '.'); }
      default: return t.getClassName();
    }
  }
  static String signature(String cname, String name, String desc, boolean isStatic) {
    StringBuilder b = new StringBuilder();
    String simple = cname.substring(cname.lastIndexOf('/') + 1);
    simple = simple.substring(simple.lastIndexOf('$') + 1);
    b.append(name.equals("<init>") ? simple : name).append('(');
    Type[] args = Type.getArgumentTypes(desc);
    for (int i = 0; i < args.length; i++) { if (i > 0) b.append(", "); b.append(simpleType(args[i])); }
    b.append(')');
    if (!name.equals("<init>")) b.append(": ").append(simpleType(Type.getReturnType(desc)));
    if (isStatic) b.append(" [static]");
    return b.toString();
  }
  static MethodNode jarMethod(ClassNode c, String name, String desc) {
    if (c != null) for (MethodNode m : c.methods) if (m.name.equals(name) && m.desc.equals(desc)) return m;
    return null;
  }
  static FieldNode jarField(ClassNode c, String name) {
    if (c != null) for (FieldNode f : c.fields) if (f.name.equals(name)) return f;
    return null;
  }
}
