package jp;

import org.objectweb.asm.*;
import org.objectweb.asm.commons.*;
import org.objectweb.asm.tree.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.zip.*;

/**
 * The apk2web linker. Takes the app's classes (dex2jar output) and the web framework (compiled shim) and makes one
 * closed world TeaVM can compile:
 * <ul>
 * <li>every class, method and field the code names is resolved against the app, the framework, TeaVM's class
 *     library and android.jar. What is missing is generated: classes from their android.jar shape (or from how
 *     they are used), members as no-op stubs that log once (a2w.Stubs) and return a harmless default.</li>
 * <li>JDK classes TeaVM lacks are renamed to generated classes under a2w/jdkx/; missing members of JDK classes are
 *     redirected to hand-written replacements (a2w.jdk.Compat) or to generated stubs (a2w.gen.Missing).</li>
 * <li>native methods become calls into the ARM emulator through a2w.Jni; natives.json lists them for the page.</li>
 * <li>reflection is answered from tables generated for what the app can name: a2w.gen.Factories (manifest
 *     components, layout tags), a2w.gen.Reflect (Class.forName, constructors, methods) and a2w.gen.JniTable (the
 *     classes and members the native libraries name).</li>
 * </ul>
 * usage: Link --app app.jar --fw fw.jar --jdk teavm-classlib.jar [--lib x.jar]... --android android.jar
 *             [--roots roots.json] [--so lib.so]... [--drop pkg/]... [--keep pkg/]... [--missing file] --out dir
 */
public class Link {
  static final int APP = 0, FW = 1, JDK = 2, LIB = 3, STUB = 4, GEN = 5;
  static final String[] ORIGIN = {"app", "framework", "jdk", "lib", "stub", "gen"};

  static final class CI {
    String name, sup, orig;
    List<String> itfs = new ArrayList<>();
    int access, origin;
    ClassNode node;
    final Map<String, Integer> methods = new HashMap<>();   // name+desc -> access
    final Map<String, String> fields = new HashMap<>();     // name -> desc
    final Map<String, Integer> fieldAcc = new HashMap<>();
    boolean templated;
    boolean isInterface() { return (access & Opcodes.ACC_INTERFACE) != 0; }
    /** a declaration generated from the SDK (runtime/framework-gen) that nothing has referred to yet: not linked or written */
    boolean lazy;
    boolean mutable() { return node != null && !lazy; }
    boolean concrete() { return (access & (Opcodes.ACC_INTERFACE | Opcodes.ACC_ABSTRACT)) == 0; }
  }

  final Map<String, CI> world = new HashMap<>();
  final List<CI> worklist = new ArrayList<>();
  final Map<String, String> remap = new HashMap<>();
  final Set<String> forcedMissing = new HashSet<>();
  final List<ZipFile> android = new ArrayList<>();
  final List<String> templateJars = new ArrayList<>();
  final Map<String, ClassNode> templates = new HashMap<>();
  // usage hints for classes nobody defines
  final Set<String> hintInterface = new HashSet<>(), hintThrowable = new HashSet<>();
  // report
  final TreeMap<String, Integer> stubbedMembers = new TreeMap<>();
  final TreeSet<String> stubbedClasses = new TreeSet<>(), unknownClasses = new TreeSet<>(), redirects = new TreeSet<>();
  int droppedDup, droppedPkg;
  final Set<String> drop = new LinkedHashSet<>();   // package prefixes whose classes are dropped
  final List<String[]> natives = new ArrayList<>();   // cls, name, desc, static
  ClassNode missing;   // a2w/gen/Missing

  static final List<String> DEFAULT_DROP = List.of(
      // analytics, crash reporting, ads: SDKs that only talk to servers. Their classes are regenerated as
      // no-op stubs from how the app uses them.
      "com/flurry/", "com/crittercism/", "com/crashlytics/", "io/fabric/", "com/google/firebase/analytics/",
      "com/google/firebase/crashlytics/", "com/google/android/gms/ads/", "com/google/ads/", "com/google/android/gms/analytics/",
      "com/mopub/", "com/inmobi/", "com/chartboost/", "com/applovin/", "com/tapjoy/", "com/vungle/", "com/adcolony/",
      "com/unity3d/ads/", "com/startapp/", "com/millennialmedia/", "com/amazon/device/ads/", "com/facebook/ads/",
      "com/ironsource/", "com/appsflyer/", "com/adjust/sdk/", "com/kochava/", "com/localytics/", "com/mixpanel/",
      "com/bugsnag/", "io/sentry/", "com/newrelic/", "com/umeng/", "com/tencent/bugly/", "com/admob/", "com/airpush/",
      "com/revmob/", "com/heyzap/", "com/supersonicads/", "com/smaato/", "com/mobclix/", "com/greystripe/");

  // hand-written replacements in the framework (only used when the target class exists there)
  static final String[][] PREFERRED_REMAP = {
      {"java/security/MessageDigest", "a2w/jdk/MessageDigest"},
      {"java/security/NoSuchAlgorithmException", "a2w/jdk/NoSuchAlgorithmException"},
      // java.io.File & co. are TeaVM's, over a2w.Vfs; FileInputStream also opens asset file descriptors
      {"java/io/FileDescriptor", "a2w/jdk/FileDescriptor"}, {"java/io/FileInputStream", "a2w/jdk/FileInputStream"},
      {"java/lang/UnsatisfiedLinkError", "a2w/jdk/UnsatisfiedLinkError"},
      {"java/lang/IllegalThreadStateException", "a2w/jdk/IllegalThreadStateException"},
      {"javax/net/ssl/HttpsURLConnection", "a2w/jdk/HttpsURLConnection"},   // https over XMLHttpRequest (a2w.XhrConnection)
      {"java/util/concurrent/ConcurrentLinkedQueue", "a2w/jdk/ConcurrentLinkedQueue"},
      {"java/lang/reflect/Constructor", "a2w/jdk/reflect/Constructor"},
      {"java/lang/reflect/Method", "a2w/jdk/reflect/Method"},
      {"java/lang/reflect/Field", "a2w/jdk/reflect/Field"},
      {"java/lang/reflect/AccessibleObject", "a2w/jdk/reflect/AccessibleObject"},
      {"java/lang/reflect/InvocationTargetException", "a2w/jdk/reflect/InvocationTargetException"},
  };
  /** whole packages with hand-written replacements in the framework (it cannot declare them under their own names) */
  int replacedDeclarations, activated;
  static final String[][] PREFERRED_PREFIX = {
      {"javax/crypto/", "a2w/jdk/crypto/"},
      {"javax/xml/parsers/", "a2w/xml/parsers/"}, {"javax/xml/transform/", "a2w/xml/transform/"},
      {"org/xml/sax/", "a2w/xml/sax/"}, {"org/w3c/dom/", "a2w/xml/dom/"}, {"javax/xml/validation/", "a2w/xml/validation/"},
      // the rest of javax.xml and the JDK's javax packages: generated declarations (runtime/framework-gen)
      {"javax/xml/", "a2w/xml/"}, {"javax/", "a2w/jdk/javax/"},
  };
  /** java.time: TeaVM's class library carries the ThreeTen backport (by java.time's author) and presents it as java.time */
  static boolean jdkExtra(String n) { return n.startsWith("org/threeten/bp/"); }
  static final String[] SEC = {"Key", "InvalidKeyException", "InvalidAlgorithmParameterException", "spec/KeySpec",
      "spec/AlgorithmParameterSpec", "spec/InvalidKeySpecException"};

  public static void main(String[] args) throws Exception {
    new Link().run(args);
  }

  // ================================================================ driver
  void run(String[] a) throws Exception {
    List<String> apps = new ArrayList<>(), libs = new ArrayList<>(), sos = new ArrayList<>();
    String fw = null, jdk = null, androidJar = null, rootsFile = null, out = null, missingFile = null;
    Set<String> drop = this.drop;
    drop.addAll(DEFAULT_DROP);
    for (int i = 0; i < a.length; i++) {
      switch (a[i]) {
        case "--app": apps.add(a[++i]); break;
        case "--fw": fw = a[++i]; break;
        case "--jdk": jdk = a[++i]; break;
        case "--lib": libs.add(a[++i]); break;
        case "--android": androidJar = a[++i]; break;
        case "--template": templateJars.add(a[++i]); break;
        case "--roots": rootsFile = a[++i]; break;
        case "--so": sos.add(a[++i]); break;
        case "--drop": drop.add(a[++i]); break;
        case "--keep": drop.remove(a[++i]); break;
        case "--missing": missingFile = a[++i]; break;
        case "--out": out = a[++i]; break;
        default: throw new IllegalArgumentException(a[i]);
      }
    }
    Roots roots = rootsFile == null ? new Roots() : Roots.read(Paths.get(rootsFile));
    if (missingFile != null && Files.exists(Paths.get(missingFile)))
      for (String l : Files.readAllLines(Paths.get(missingFile))) if (!l.isBlank()) forcedMissing.add(l.trim());
    if (androidJar != null) android.add(new ZipFile(androidJar));
    for (String t : templateJars) android.add(new ZipFile(t));

    // declarations generated from the SDK are linked only when something refers to them
    for (ClassNode cn : readJar(fw)) add(cn, FW).lazy = ApiReport.generated(cn);
    for (ClassNode cn : readJar(jdk)) {
      if (!cn.name.startsWith("org/teavm/classlib/") && !jdkExtra(cn.name)) continue;
      ClassNode m = jdkMapped(cn);
      if (m != null && !world.containsKey(m.name)) addInfo(m, JDK);
    }
    for (String l : libs) for (ClassNode cn : readJar(l)) if (!world.containsKey(cn.name)) addInfo(cn, LIB);

    // the app's classes; the platform's own classes win over copies the app carries (boot class path first), and
    // among the --app jars the first copy of a class wins (the bundled Apache HTTP client is passed before the app)
    List<ClassNode> appClasses = new ArrayList<>();
    Set<String> appNames = new HashSet<>();
    for (String ap : apps) for (ClassNode cn : readJar(ap)) {
      CI have = world.get(cn.name);
      // a declaration generated from the SDK (stub bodies) does not win over a real implementation the app carries
      if (have != null && have.origin == FW && have.node != null && ApiReport.generated(have.node) && !appNames.contains(cn.name)) {
        world.remove(cn.name);
        replacedDeclarations++;
      } else if (have != null || appNames.contains(cn.name)) { droppedDup++; continue; }
      if (dropped(cn.name, drop)) { droppedPkg++; continue; }
      appClasses.add(cn);
      appNames.add(cn.name);
    }

    // renames: preferred hand-written replacements, then JDK classes TeaVM lacks -> a2w/jdkx/
    for (String[] r : PREFERRED_REMAP) if (world.containsKey(r[1])) remap.put(r[0], r[1]);
    for (String s : SEC) if (world.containsKey("a2w/jdk/sec/" + s)) remap.put("java/security/" + s, "a2w/jdk/sec/" + s);
    Set<String> referenced = new TreeSet<>();
    Remapper recorder = new Remapper() {
      @Override public String map(String n) { referenced.add(n); return n; }
    };
    for (ClassNode cn : appClasses) cn.accept(new ClassRemapper(new ClassNode(), recorder));
    for (String n : referenced) {
      // a hand-written replacement in the framework wins, even over TeaVM's class: a2w/jdk/<path without "java/">
      if (n.startsWith("java/") && world.containsKey("a2w/jdk/" + n.substring(5)) && !remap.containsKey(n)) { remap.put(n, "a2w/jdk/" + n.substring(5)); continue; }
      if (remap.containsKey(n) || world.containsKey(n) || appNames.contains(n)) continue;
      String pre = null;
      for (String[] pp : PREFERRED_PREFIX)
        if (n.startsWith(pp[0]) && world.containsKey(pp[1] + n.substring(pp[0].length()))) { pre = pp[1] + n.substring(pp[0].length()); break; }
      if (pre != null) { remap.put(n, pre); continue; }
      if (isJdkName(n)) remap.put(n, "a2w/jdkx/" + n);
    }
    for (String f : forcedMissing)
      if (f.startsWith("class ")) { String n = f.substring(6); if (isJdkName(n) && !remap.containsKey(n)) remap.put(n, "a2w/jdkx/" + n); }
    Remapper mapper = new SimpleRemapper(remap);
    List<ClassNode> remapped = new ArrayList<>();
    for (ClassNode cn : appClasses) {
      ClassNode r = new ClassNode();
      cn.accept(new ClassRemapper(r, mapper));
      remapped.add(r);
    }
    disambiguateFields(remapped);
    for (ClassNode r : remapped) {
      collectHints(r);
      add(r, APP);
    }

    missing = newClass("a2w/gen/Missing", "java/lang/Object", Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL);
    addInfo(missing, GEN).node = missing;

    // methods the compiler after us could not translate (an earlier round found them): stub bodies
    for (String f : forcedMissing) if (f.startsWith("stub ")) stubAppMethods(f.substring(5));
    // patches and natives (app classes only), then resolve everything to a fixed point
    for (CI c : new ArrayList<>(world.values())) if (c.origin == APP) patchClass(c);
    for (CI c : world.values()) if (c.mutable()) worklist.add(c);
    Set<CI> done = new HashSet<>();
    while (!worklist.isEmpty()) {
      CI c = worklist.remove(worklist.size() - 1);
      if (!done.add(c)) continue;
      resolveClass(c);
    }
    // classes generated from usage alone get the supertypes their uses need (an SDK's AdView handed to addView)
    for (CI c : inferStubSupertypes()) { done.remove(c); worklist.add(c); }
    while (!worklist.isEmpty()) {
      CI c = worklist.remove(worklist.size() - 1);
      if (!done.add(c)) continue;
      resolveClass(c);
    }

    // generated tables
    Gen gen = new Gen(this, roots, sos);
    List<ClassNode> gens = gen.generate();
    for (ClassNode g : gens) { addInfo(g, GEN).node = g; resolveClass(world.get(g.name)); }
    // stubs made while resolving the generated code
    while (!worklist.isEmpty()) { CI c = worklist.remove(worklist.size() - 1); if (done.add(c)) resolveClass(c); }
    // concrete classes must implement every abstract method they inherit (a generated superclass may lack one)
    fillAbstractHoles();
    while (!worklist.isEmpty()) { CI c = worklist.remove(worklist.size() - 1); if (done.add(c)) resolveClass(c); }

    // time slices for the app's Java threads (a2w.Preempt): a call on every backward jump
    if (world.containsKey("a2w/Preempt")) for (CI c : world.values()) if (c.origin == APP && c.node != null) preemptible(c.node);

    Path od = Paths.get(out);
    Files.createDirectories(od);
    int n = 0;
    try (ZipOutputStream zo = new ZipOutputStream(Files.newOutputStream(od.resolve("all.jar")))) {
      for (CI c : world.values()) {
        if (!c.mutable()) continue;
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        c.node.accept(cw);
        byte[] bytes = cw.toByteArray();
        if (!verified(c, bytes)) { cw = new ClassWriter(ClassWriter.COMPUTE_MAXS); c.node.accept(cw); bytes = cw.toByteArray(); }
        zo.putNextEntry(new ZipEntry(c.name + ".class"));
        zo.write(bytes);
        zo.closeEntry();
        n++;
      }
      // TeaVM compiler plugins (named in the TeaVM configuration), from this jar
      for (String plugin : new String[] {"jp/teavm/FiberFix.class"}) {
        try (InputStream in = Link.class.getClassLoader().getResourceAsStream(plugin)) {
          if (in == null) continue;
          zo.putNextEntry(new ZipEntry(plugin));
          zo.write(in.readAllBytes());
          zo.closeEntry();
        }
      }
    }
    writeNatives(od.resolve("natives.json"));
    writeReport(od.resolve("link-report.txt"), gen);
    int appN = 0, stubN = 0;
    for (CI c : world.values()) { if (c.origin == APP) appN++; if (c.origin == STUB) stubN++; }
    System.out.printf("link: %d app classes (%d duplicates of platform classes, %d replacing SDK declarations, %d in dropped SDK"
        + " packages), %d stub classes (%d unknown), %d stub members, %d redirects, %d natives, %d classes written%n",
        appN, droppedDup, replacedDeclarations, droppedPkg, stubN, unknownClasses.size(), stubbedMembers.size(), redirects.size(), natives.size(), n);
    gen.summary();
  }

  /** inserts a2w.Preempt.tick() before every backward jump (loops) of the class's methods (not class initializers:
   *  TeaVM runs those where a thread cannot always suspend) */
  static void preemptible(ClassNode cn) {
    for (MethodNode m : cn.methods) {
      if (m.instructions == null || m.instructions.size() == 0 || m.name.equals("<clinit>")) continue;
      Map<LabelNode, Integer> at = new HashMap<>();
      int i = 0;
      for (AbstractInsnNode n : m.instructions) { if (n instanceof LabelNode l) at.put(l, i); i++; }
      List<AbstractInsnNode> back = new ArrayList<>();
      i = 0;
      for (AbstractInsnNode n : m.instructions) {
        boolean b = false;
        if (n instanceof JumpInsnNode j) b = at.getOrDefault(j.label, Integer.MAX_VALUE) <= i;
        else if (n instanceof TableSwitchInsnNode t) { b = at.getOrDefault(t.dflt, Integer.MAX_VALUE) <= i; for (LabelNode l : t.labels) b |= at.getOrDefault(l, Integer.MAX_VALUE) <= i; }
        else if (n instanceof LookupSwitchInsnNode t) { b = at.getOrDefault(t.dflt, Integer.MAX_VALUE) <= i; for (LabelNode l : t.labels) b |= at.getOrDefault(l, Integer.MAX_VALUE) <= i; }
        if (b) back.add(n);
        i++;
      }
      for (AbstractInsnNode n : back) m.instructions.insertBefore(n, new MethodInsnNode(Opcodes.INVOKESTATIC, "a2w/Preempt", "tick", "()V", false));
    }
  }

  /** "pkg/Cls.name": every app method of that name gets a body that reports the call and returns a default (a
   *  constructor still runs its superclass constructor) */
  void stubAppMethods(String spec) {
    int dot = spec.lastIndexOf('.');
    CI c = world.get(spec.substring(0, dot));
    String name = spec.substring(dot + 1);
    if (c == null || c.origin != APP) return;
    for (MethodNode m : c.node.methods) {
      if (!m.name.equals(name) || (m.access & (Opcodes.ACC_ABSTRACT | Opcodes.ACC_NATIVE)) != 0) continue;
      MethodNode s = stubBody(c.node, m.access, m.name, m.desc, c.name.replace('/', '.') + "." + m.name + " (could not be compiled)", (m.access & Opcodes.ACC_STATIC) == 0, c);
      InsnList il = new InsnList();
      if (m.name.equals("<init>")) {
        CI sup = world.get(c.sup);
        String use = sup != null && findMethod(sup, "<init>", "()V") != null ? "()V" : null;
        if (use == null && sup != null)
          for (String k : sup.methods.keySet())
            if (k.startsWith("<init>(") && (sup.methods.get(k) & Opcodes.ACC_PRIVATE) == 0
                && (use == null || Type.getArgumentTypes(k.substring(6)).length < Type.getArgumentTypes(use).length)) use = k.substring(6);
        if (use == null) use = "()V";
        il.add(new VarInsnNode(Opcodes.ALOAD, 0));
        for (Type t : Type.getArgumentTypes(use)) il.add(richDefault(t));
        il.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, c.sup, "<init>", use, false));
      }
      il.add(s.instructions);
      m.instructions = il;
      m.tryCatchBlocks = new ArrayList<>();
      m.localVariables = null;
      m.maxLocals = Math.max(m.maxLocals, s.maxLocals);
      stubbedMembers.merge(c.name.replace('/', '.') + "." + m.name + m.desc + " (not compilable)", 1, Integer::sum);
    }
  }

  /** original names of fields renamed by disambiguateFields: class.newName -> name (JNI looks fields up by name) */
  final Map<String, String> fieldOrigName = new HashMap<>();

  /**
   * Bytecode names a field by name and type, TeaVM (and this linker) by name alone: obfuscators that overload
   * aggressively give a class several fields of one name. Those get names of their own, and every access is pointed
   * at the field the JVM would resolve (by name and type, up the superclass chain).
   */
  void disambiguateFields(List<ClassNode> classes) {
    Map<String, ClassNode> byName = new HashMap<>();
    for (ClassNode cn : classes) byName.put(cn.name, cn);
    Map<String, String> renamed = new HashMap<>();   // class.name:desc -> new name
    for (ClassNode cn : classes) {
      Map<String, Integer> count = new HashMap<>();
      for (FieldNode f : cn.fields) count.merge(f.name, 1, Integer::sum);
      Set<String> used = new HashSet<>();
      for (FieldNode f : cn.fields) used.add(f.name);
      for (FieldNode f : cn.fields) {
        if (count.get(f.name) < 2) continue;
        String t = Type.getType(f.desc).getClassName().replaceAll("[^A-Za-z0-9]", "_");
        String base = f.name + "$" + t.substring(t.lastIndexOf('_') + 1), n = base;
        for (int k = 2; used.contains(n); k++) n = base + k;
        used.add(n);
        renamed.put(cn.name + "." + f.name + ":" + f.desc, n);
        fieldOrigName.put(cn.name + "." + n, f.name);
      }
      for (FieldNode f : cn.fields) { String n = renamed.get(cn.name + "." + f.name + ":" + f.desc); if (n != null) f.name = n; }
    }
    int fixed = 0;
    for (ClassNode cn : classes)
      for (MethodNode m : cn.methods)
        for (AbstractInsnNode in : m.instructions) {
          if (!(in instanceof FieldInsnNode fi)) continue;
          boolean shadowed = false;
          for (ClassNode k = byName.get(fi.owner); k != null; k = byName.get(k.superName)) {
            String n = renamed.get(k.name + "." + fi.name + ":" + fi.desc);
            if (n != null) { fi.name = n; if (!k.name.equals(fi.owner)) fi.owner = k.name; fixed++; break; }
            FieldNode exact = null; boolean sameName = false;
            for (FieldNode f : k.fields) {
              if (f.name.equals(fi.name) && f.desc.equals(fi.desc)) exact = f;
              else if (fieldOrigName.getOrDefault(k.name + "." + f.name, f.name).equals(fi.name)) sameName = true;
            }
            if (exact != null) { if (shadowed && !k.name.equals(fi.owner)) { fi.owner = k.name; fixed++; } break; }
            if (sameName) shadowed = true;
          }
        }
    if (!renamed.isEmpty()) System.out.printf("fields: %d overloaded field names made unique (%d accesses)%n", renamed.size(), fixed);
  }

  /** checks every method's stack and local types (as the compiler after us parses them); an app method that does not
   *  verify gets a body that throws LinkageError (VerifyError on a device). Returns false when it changed c. */
  boolean verified(CI c, byte[] bytes) {
    ClassNode cn = new ClassNode();
    new ClassReader(bytes).accept(cn, 0);
    boolean ok = true;
    for (MethodNode m : cn.methods) {
      if (m.instructions.size() == 0) continue;
      try { new org.objectweb.asm.tree.analysis.Analyzer<>(new org.objectweb.asm.tree.analysis.BasicVerifier()).analyze(cn.name, m); }
      catch (org.objectweb.asm.tree.analysis.AnalyzerException e) {
        String where = c.name + "." + m.name + m.desc + ": " + e.getMessage();
        if (c.origin == GEN) throw new IllegalStateException("generated code does not verify: " + where);
        System.out.println("warning: method does not verify, it throws LinkageError: " + where);
        for (MethodNode o : c.node.methods)
          if (o.name.equals(m.name) && o.desc.equals(m.desc)) {
            o.instructions = new InsnList();
            o.tryCatchBlocks = new ArrayList<>();
            o.localVariables = null;
            o.instructions.add(new TypeInsnNode(Opcodes.NEW, "java/lang/LinkageError"));
            o.instructions.add(new InsnNode(Opcodes.DUP));
            o.instructions.add(new LdcInsnNode(c.name + "." + m.name));
            o.instructions.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, "java/lang/LinkageError", "<init>", "(Ljava/lang/String;)V", false));
            o.instructions.add(new InsnNode(Opcodes.ATHROW));
          }
        ok = false;
      }
    }
    return ok;
  }

  static boolean dropped(String n, Set<String> drop) {
    for (String d : drop) if (n.startsWith(d)) return true;
    return false;
  }
  static boolean isJdkName(String n) { return n.startsWith("java/") || n.startsWith("javax/") || n.startsWith("sun/"); }

  // ================================================================ loading
  static List<ClassNode> readJar(String path) throws IOException {
    List<ClassNode> out = new ArrayList<>();
    Path p = Paths.get(path);
    if (Files.isDirectory(p)) {
      try (var s = Files.walk(p)) {
        for (Path f : (Iterable<Path>) s::iterator) if (f.toString().endsWith(".class")) out.add(read(Files.readAllBytes(f)));
      }
      return out;
    }
    try (ZipInputStream in = new ZipInputStream(new BufferedInputStream(Files.newInputStream(p)))) {
      ZipEntry e;
      while ((e = in.getNextEntry()) != null) {
        if (!e.getName().endsWith(".class") || e.getName().startsWith("META-INF/")) continue;
        out.add(read(in.readAllBytes()));
      }
    }
    return out;
  }
  static ClassNode read(byte[] b) {
    ClassNode cn = new ClassNode();
    new ClassReader(b).accept(cn, 0);
    return cn;
  }

  /** org/teavm/classlib/java/util/TMap$Entry -> java/util/Map$Entry, descriptors too */
  static String unT(String n) {
    if (jdkExtra(n)) return "java/time/" + n.substring("org/threeten/bp/".length());
    if (!n.startsWith("org/teavm/classlib/")) return n;
    String rest = n.substring("org/teavm/classlib/".length());
    int slash = rest.lastIndexOf('/');
    String simple = rest.substring(slash + 1);
    if (!simple.startsWith("T")) return n;
    return rest.substring(0, slash + 1) + simple.substring(1);
  }
  static final Remapper UNT = new Remapper() { @Override public String map(String n) { return unT(n); } };
  static ClassNode jdkMapped(ClassNode cn) {
    String n = unT(cn.name);
    if (n.equals(cn.name) || !(n.startsWith("java/") || n.startsWith("javax/"))) return null;
    ClassNode m = new ClassNode();
    cn.accept(new ClassRemapper(m, UNT));
    // TeaVM's class library annotations: @Rename("name") gives the JDK name, @Remove drops the member
    m.methods.removeIf(x -> annotation(x.visibleAnnotations, "Lorg/teavm/interop/Remove;") != null
        || annotation(x.invisibleAnnotations, "Lorg/teavm/interop/Remove;") != null);
    for (MethodNode x : m.methods) {
      AnnotationNode r = annotation(x.visibleAnnotations, "Lorg/teavm/interop/Rename;");
      if (r == null) r = annotation(x.invisibleAnnotations, "Lorg/teavm/interop/Rename;");
      if (r != null && r.values != null && r.values.size() == 2) {
        x.name = (String) r.values.get(1);
        // constructors are written as methods renamed to <init> (the real ones become "fakeInit")
        if (x.name.equals("<init>")) x.access = (x.access & ~(Opcodes.ACC_PRIVATE | Opcodes.ACC_STATIC)) | Opcodes.ACC_PUBLIC;
      }
    }
    m.methods.removeIf(x -> (x.access & (Opcodes.ACC_PUBLIC | Opcodes.ACC_PROTECTED)) == 0);
    m.fields.removeIf(x -> (x.access & (Opcodes.ACC_PUBLIC | Opcodes.ACC_PROTECTED)) == 0);
    return m;
  }

  static AnnotationNode annotation(List<AnnotationNode> l, String desc) {
    if (l != null) for (AnnotationNode a : l) if (a.desc.equals(desc)) return a;
    return null;
  }

  CI add(ClassNode cn, int origin) {
    CI c = addInfo(cn, origin);
    c.node = cn;
    return c;
  }
  CI addInfo(ClassNode cn, int origin) {
    CI c = new CI();
    c.name = cn.name;
    c.sup = cn.name.equals(cn.superName) || cn.name.equals("java/lang/Object") ? null : cn.superName;
    for (String i : cn.interfaces) if (!i.equals(cn.name)) c.itfs.add(i);
    c.access = cn.access;
    c.origin = origin;
    for (MethodNode m : cn.methods) c.methods.put(m.name + m.desc, m.access);
    for (FieldNode f : cn.fields) { c.fields.put(f.name, f.desc); c.fieldAcc.put(f.name, f.access); }
    world.put(c.name, c);
    return c;
  }

  /**
   * The shape of a platform class nobody implements: from the app's android.jar, else from older API jars (classes
   * since removed, such as PixelXorXfermode, and the org.apache.http classes Android bundled until API 22).
   */
  ClassNode template(String name) {
    if (android.isEmpty()) return null;
    if (templates.containsKey(name)) return templates.get(name);
    ClassNode cn = null;
    try {
      for (ZipFile z : android) {
        ZipEntry e = z.getEntry(name + ".class");
        if (e == null) continue;
        cn = new ClassNode();
        new ClassReader(z.getInputStream(e).readAllBytes()).accept(cn, ClassReader.SKIP_CODE);
        break;
      }
    } catch (IOException ex) { throw new UncheckedIOException(ex); }
    templates.put(name, cn);
    return cn;
  }

  void collectHints(ClassNode cn) {
    hintInterface.addAll(cn.interfaces);
    for (MethodNode m : cn.methods) {
      if (m.exceptions != null) hintThrowable.addAll(m.exceptions);
      for (TryCatchBlockNode t : m.tryCatchBlocks) if (t.type != null) hintThrowable.add(t.type);
      if (m.instructions == null) continue;
      for (AbstractInsnNode n : m.instructions) {
        if (n instanceof MethodInsnNode mi && (mi.getOpcode() == Opcodes.INVOKEINTERFACE || mi.itf)) hintInterface.add(mi.owner);
        if (n.getOpcode() == Opcodes.ATHROW) {
          AbstractInsnNode p = n.getPrevious();
          if (p instanceof TypeInsnNode t && t.getOpcode() == Opcodes.CHECKCAST) hintThrowable.add(t.desc);
        }
      }
    }
  }

  // ================================================================ class materialization
  CI ensureType(Type t) {
    if (t.getSort() == Type.ARRAY) t = t.getElementType();
    if (t.getSort() != Type.OBJECT) return null;
    return ensureClass(t.getInternalName());
  }
  void ensureDesc(String desc) {
    Type mt = Type.getType(desc);
    if (mt.getSort() == Type.METHOD) {
      for (Type x : mt.getArgumentTypes()) ensureType(x);
      ensureType(mt.getReturnType());
    } else ensureType(mt);
  }

  CI ensureClass(String name) {
    if (name.startsWith("[")) return ensureType(Type.getType(name));
    CI c = world.get(name);
    if (c != null) {
      if (c.lazy) { c.lazy = false; worklist.add(c); activated++; ensureClass(c.sup == null ? "java/lang/Object" : c.sup); for (String i : c.itfs) ensureClass(i); }
      return c;
    }
    String orig = name.startsWith("a2w/jdkx/") ? name.substring(9) : name;
    ClassNode t = template(orig);
    ClassNode cn;
    if (t != null) {
      int acc = (t.access & (Opcodes.ACC_INTERFACE | Opcodes.ACC_ABSTRACT | Opcodes.ACC_ANNOTATION | Opcodes.ACC_ENUM)) | Opcodes.ACC_PUBLIC;
      cn = newClass(name, mapName(t.superName == null ? "java/lang/Object" : t.superName), acc);
      for (String i : t.interfaces) cn.interfaces.add(mapName(i));
    } else {
      boolean itf = hintInterface.contains(name);
      String sup = hintThrowable.contains(name) || name.endsWith("Exception") ? "java/lang/RuntimeException"
          : name.endsWith("Error") ? "java/lang/Error" : "java/lang/Object";
      cn = newClass(name, "java/lang/Object", Opcodes.ACC_PUBLIC | (itf ? Opcodes.ACC_INTERFACE | Opcodes.ACC_ABSTRACT : 0));
      if (!itf) cn.superName = sup;
      unknownClasses.add(name);
    }
    c = add(cn, STUB);
    c.orig = orig;
    c.templated = t != null;
    stubbedClasses.add(name);
    worklist.add(c);
    if (c.sup != null) ensureClass(c.sup);
    for (String i : c.itfs) ensureClass(i);
    return c;
  }
  /** a name as the linked world spells it: JDK classes TeaVM lacks get their replacement (computed on first use,
   *  for names only android.jar shapes mention) */
  String mapName(String n) {
    String r = remap.get(n);
    if (r != null) return r;
    if (isJdkName(n) && !world.containsKey(n)) {
      r = n.startsWith("java/") && world.containsKey("a2w/jdk/" + n.substring(5)) ? "a2w/jdk/" + n.substring(5) : "a2w/jdkx/" + n;
      remap.put(n, r);
      return r;
    }
    return n;
  }

  static ClassNode newClass(String name, String sup, int access) {
    ClassNode cn = new ClassNode();
    cn.version = Opcodes.V1_8;
    cn.access = access;
    cn.name = name;
    cn.superName = (access & Opcodes.ACC_INTERFACE) != 0 ? "java/lang/Object" : sup;
    return cn;
  }

  // ================================================================ lookup
  /** the class declaring name+desc as seen from c (superclasses, then interfaces), or null */
  /** the superclass chain of c, itself first (cycles, which only broken inputs have, are cut) */
  List<CI> supers(CI c) {
    List<CI> out = new ArrayList<>();
    Set<String> seen = new HashSet<>();
    for (CI k = c; k != null && seen.add(k.name); k = k.sup == null ? null : world.get(k.sup)) out.add(k);
    return out;
  }
  CI findMethod(CI c, String name, String desc) {
    for (CI k : supers(c)) {
      if (k.methods.containsKey(name + desc)) return k;
      if (name.equals("<init>")) return null;
    }
    ArrayDeque<CI> q = new ArrayDeque<>();
    Set<String> seen = new HashSet<>();
    for (CI k : supers(c)) for (String i : k.itfs) q.add(world.get(i) == null ? ensureClass(i) : world.get(i));
    while (!q.isEmpty()) {
      CI k = q.poll();
      if (k == null || !seen.add(k.name)) continue;
      if (k.methods.containsKey(name + desc)) return k;
      for (String i : k.itfs) if (world.get(i) != null) q.add(world.get(i));
    }
    // methods of Object are visible through interfaces
    if (c.isInterface()) { CI o = world.get("java/lang/Object"); if (o != null && o.methods.containsKey(name + desc)) return o; }
    return null;
  }
  CI findField(CI c, String name) {
    for (CI k : supers(c)) {
      if (k.fields.containsKey(name)) return k;
      for (String i : k.itfs) { CI f = findFieldItf(world.get(i), name); if (f != null) return f; }
    }
    return null;
  }
  CI findFieldItf(CI c, String name) {
    if (c == null) return null;
    if (c.fields.containsKey(name)) return c;
    for (String i : c.itfs) { CI f = findFieldItf(world.get(i), name); if (f != null) return f; }
    return null;
  }
  boolean isSubclass(String sub, String sup) {
    Set<String> seen = new HashSet<>();
    ArrayDeque<String> q = new ArrayDeque<>();
    q.add(sub);
    while (!q.isEmpty()) {
      String n = q.poll();
      if (n == null || !seen.add(n)) continue;
      if (n.equals(sup)) return true;
      CI c = world.get(n);
      if (c == null) continue;
      if (c.sup != null) q.add(c.sup);
      q.addAll(c.itfs);
    }
    return false;
  }

  // ================================================================ resolution
  void resolveClass(CI c) {
    ClassNode cn = c.node;
    if (cn.superName != null) ensureClass(cn.superName);
    for (String i : cn.interfaces) ensureClass(i);
    for (FieldNode f : cn.fields) ensureDesc(f.desc);
    boolean nullSafe = (c.origin == APP || c.origin == FW || c.origin == LIB) && !c.name.equals("a2w/jdk/Compat");
    for (MethodNode m : new ArrayList<>(cn.methods)) {
      ensureDesc(m.desc);
      if (m.exceptions != null) for (String e : m.exceptions) ensureClass(e);
      for (TryCatchBlockNode t : m.tryCatchBlocks) if (t.type != null) ensureClass(t.type);
      if (m.instructions == null) continue;
      for (AbstractInsnNode n = m.instructions.getFirst(); n != null; ) {
        AbstractInsnNode next = n.getNext();
        if (n instanceof MethodInsnNode mi) { if (nullSafe) nullSafeAppend(mi); resolveCall(c, m, mi); }
        else if (n instanceof FieldInsnNode fi) resolveFieldInsn(c, m, fi);
        else if (n instanceof TypeInsnNode ti) ensureType(ti.desc.startsWith("[") ? Type.getType(ti.desc) : Type.getObjectType(ti.desc));
        else if (n instanceof LdcInsnNode li && li.cst instanceof Type t) { if (t.getSort() != Type.METHOD) ensureType(t); }
        else if (n instanceof MultiANewArrayInsnNode ma) ensureType(Type.getType(ma.desc));
        n = next;
      }
    }
  }

  /** a renamed type can be an interface on one side and a class on the other (java.time.chrono.ChronoLocalDate is an
   *  interface, ThreeTen's an abstract class): the call's opcode follows the class it now names */
  static void fixInvokeKind(CI owner, MethodInsnNode mi) {
    if (owner == null || owner.name.startsWith("[")) return;
    boolean itf = (owner.access & Opcodes.ACC_INTERFACE) != 0;
    int op = mi.getOpcode();
    if (op == Opcodes.INVOKEINTERFACE && !itf) { mi.setOpcode(Opcodes.INVOKEVIRTUAL); mi.itf = false; }
    else if (op == Opcodes.INVOKEVIRTUAL && itf) { mi.setOpcode(Opcodes.INVOKEINTERFACE); mi.itf = true; }
    else if ((op == Opcodes.INVOKESTATIC || op == Opcodes.INVOKESPECIAL) && !mi.name.equals("<init>")) mi.itf = itf;
  }

  void resolveCall(CI from, MethodNode m, MethodInsnNode mi) {
    if (mi.owner.startsWith("[")) { ensureType(Type.getType(mi.owner)); return; }
    CI owner = ensureClass(mi.owner);
    ensureDesc(mi.desc);
    String key = mi.owner + "." + mi.name + mi.desc;
    CI decl = findMethod(owner, mi.name, mi.desc);
    boolean forced = forcedMissing.contains("method " + key) && (decl == null || !decl.mutable());
    if (decl != null && !forced) { fixInvokeKind(owner, mi); return; }
    boolean isStatic = mi.getOpcode() == Opcodes.INVOKESTATIC;
    if (owner.mutable() && !forced) {
      addMethodStub(owner, mi.name, mi.desc, isStatic);
      return;
    }
    // a JDK or library class without this member: redirect the call site
    if (mi.name.equals("<init>")) { if (!ctorSubclass(from, m, mi)) redirectCtor(m, mi, owner); return; }
    String sdesc = isStatic ? mi.desc : "(L" + mi.owner + ";" + mi.desc.substring(1);
    String simple = mi.owner.substring(mi.owner.lastIndexOf('/') + 1).replace('$', '_');
    CI compat = world.get("a2w/jdk/Compat");
    String cname = simple + "_" + mi.name;
    if (compat != null && compat.methods.containsKey(cname + sdesc)) {
      mi.setOpcode(Opcodes.INVOKESTATIC); mi.owner = "a2w/jdk/Compat"; mi.name = cname; mi.desc = sdesc; mi.itf = false;
      redirects.add(key + " -> Compat." + cname);
      return;
    }
    String mname = mangle(mi.owner) + "$" + mi.name;
    if (!hasMethod(missing, mname, sdesc)) {
      MethodNode s = stubBody(missing, Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, mname, sdesc, mi.owner.replace('/', '.') + "." + mi.name, false, null);
      missing.methods.add(s);
      world.get(missing.name).methods.put(mname + sdesc, s.access);
      stubbedMembers.merge(mi.owner.replace('/', '.') + "." + mi.name + mi.desc + " (jdk)", 1, Integer::sum);
    }
    mi.setOpcode(Opcodes.INVOKESTATIC); mi.owner = missing.name; mi.name = mname; mi.desc = sdesc; mi.itf = false;
    redirects.add(key);
  }



  /**
   * new X(args) for a constructor the JDK class lacks, when a2w/jdk/ctor/X (a subclass of X) has it: the object is made
   * as that subclass, so every other use still sees an X (e.g. new FileOutputStream(FileDescriptor) with the remapped
   * descriptor type).
   */
  boolean ctorSubclass(CI from, MethodNode m, MethodInsnNode mi) {
    String simple = mi.owner.substring(mi.owner.lastIndexOf('/') + 1);
    String sub = "a2w/jdk/ctor/" + simple;
    CI sc = world.get(sub);
    if (sc == null || !sc.methods.containsKey("<init>" + mi.desc) || !mi.owner.equals(sc.sup)) return false;
    TypeInsnNode alloc = allocationOf(from, m, mi);
    if (alloc == null) return false;
    redirects.add(mi.owner + ".<init>" + mi.desc + " -> " + sub);
    alloc.desc = sub;
    mi.owner = sub;
    return true;
  }
  /** the NEW whose object an INVOKESPECIAL <init> initializes (through the DUP that copied it) */
  TypeInsnNode allocationOf(CI from, MethodNode m, MethodInsnNode mi) {
    org.objectweb.asm.tree.analysis.Frame<org.objectweb.asm.tree.analysis.SourceValue>[] frames;
    try {
      frames = new org.objectweb.asm.tree.analysis.Analyzer<>(new org.objectweb.asm.tree.analysis.SourceInterpreter()).analyze(from.name, m);
    } catch (org.objectweb.asm.tree.analysis.AnalyzerException e) { return null; }
    int idx = m.instructions.indexOf(mi);
    var f = frames[idx];
    if (f == null) return null;
    int recv = f.getStackSize() - Type.getArgumentTypes(mi.desc).length - 1;
    if (recv < 0) return null;
    for (int hops = 0; hops < 4; hops++) {
      var v = f.getStack(recv);
      if (v.insns.size() != 1) return null;
      AbstractInsnNode src = v.insns.iterator().next();
      if (src instanceof TypeInsnNode t && t.getOpcode() == Opcodes.NEW) return t;
      if (src.getOpcode() != Opcodes.DUP) return null;
      f = frames[m.instructions.indexOf(src)];
      if (f == null) return null;
      recv = f.getStackSize() - 1;
    }
    return null;
  }

  /** new X(args) for a constructor the JDK class lacks: drop the args, use X(), or else X's shortest constructor with
   *  default arguments */
  void redirectCtor(MethodNode m, MethodInsnNode mi, CI owner) {
    String key = mi.owner + ".<init>" + mi.desc;
    String use = "()V";
    if (findMethod(owner, "<init>", "()V") == null) {
      use = null;
      for (String k : owner.methods.keySet())
        if (k.startsWith("<init>(") && (owner.methods.get(k) & (Opcodes.ACC_PUBLIC | Opcodes.ACC_PROTECTED)) != 0
            && (use == null || Type.getArgumentTypes(k.substring(6)).length < Type.getArgumentTypes(use).length)) use = k.substring(6);
      if (use == null) { redirects.add(key + " (unresolved: no constructor)"); return; }
    }
    String mname = "new$" + mangle(mi.owner);
    String sdesc = "(" + mi.desc.substring(1, mi.desc.indexOf(')')) + ")V";
    if (!hasMethod(missing, mname, sdesc)) {
      MethodNode s = stubBody(missing, Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, mname, sdesc, mi.owner.replace('/', '.') + ".<init>" + mi.desc, false, null);
      missing.methods.add(s);
      world.get(missing.name).methods.put(mname + sdesc, s.access);
    }
    m.instructions.insertBefore(mi, new MethodInsnNode(Opcodes.INVOKESTATIC, missing.name, mname, sdesc, false));
    for (Type t : Type.getArgumentTypes(use)) m.instructions.insertBefore(mi, richDefault(t));
    mi.desc = use;
    redirects.add(key + " -> " + mi.owner + use);
  }

  void resolveFieldInsn(CI from, MethodNode m, FieldInsnNode fi) {
    CI owner = ensureClass(fi.owner);
    ensureDesc(fi.desc);
    CI decl = findField(owner, fi.name);
    boolean forced = forcedMissing.contains("field " + fi.owner + "." + fi.name);
    if (decl != null && !forced) return;
    // TeaVM maps some JDK fields to accessors (System.out); only what it reports missing is missing
    if (owner.origin == JDK && !forced) return;
    boolean isStatic = fi.getOpcode() == Opcodes.GETSTATIC || fi.getOpcode() == Opcodes.PUTSTATIC;
    if (owner.mutable()) {
      addField(owner, fi.name, fi.desc, isStatic);
      return;
    }
    String key = fi.owner + "." + fi.name;
    if (isStatic) {
      String fname = mangle(fi.owner) + "$" + fi.name;
      if (!world.get(missing.name).fields.containsKey(fname)) {
        missing.fields.add(new FieldNode(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, fname, fi.desc, null, null));
        world.get(missing.name).fields.put(fname, fi.desc);
      }
      fi.owner = missing.name; fi.name = fname;
    } else {
      Type t = Type.getType(fi.desc);
      InsnList il = new InsnList();
      if (fi.getOpcode() == Opcodes.GETFIELD) { il.add(new InsnNode(Opcodes.POP)); il.add(defaultValue(t)); }
      else { il.add(new InsnNode(t.getSize() == 2 ? Opcodes.POP2 : Opcodes.POP)); il.add(new InsnNode(Opcodes.POP)); }
      m.instructions.insert(fi, il);
      m.instructions.remove(fi);
    }
    stubbedMembers.merge(key.replace('/', '.') + " (jdk field)", 1, Integer::sum);
    redirects.add(key);
  }

  static boolean hasMethod(ClassNode cn, String name, String desc) {
    for (MethodNode m : cn.methods) if (m.name.equals(name) && m.desc.equals(desc)) return true;
    return false;
  }

  void addField(CI c, String name, String desc, boolean isStatic) {
    Object value = null;
    int acc = Opcodes.ACC_PUBLIC | (isStatic ? Opcodes.ACC_STATIC : 0);
    ClassNode t = c.orig != null ? template(c.orig) : template(c.name);
    if (t != null) for (FieldNode f : t.fields) if (f.name.equals(name) && f.value != null && isStatic) { value = f.value; acc |= Opcodes.ACC_FINAL; }
    if (c.isInterface()) acc |= Opcodes.ACC_STATIC | Opcodes.ACC_FINAL;
    c.node.fields.add(new FieldNode(acc, name, desc, null, value));
    c.fields.put(name, desc);
    c.fieldAcc.put(name, acc);
    stubbedMembers.merge(c.name.replace('/', '.') + "." + name + (value != null ? " = " + value : "") + " (field)", 1, Integer::sum);
  }

  /**
   * A class generated without a template (a library class missing from the APK, an API removed long ago) starts as a
   * direct subclass of Object; where the app passes, stores or returns one as some other type, it becomes a subtype of
   * that type (an interface it implements, or its superclass), so the code type-checks as it does on a device.
   */
  Set<CI> inferStubSupertypes() {
    Set<CI> changed = new LinkedHashSet<>();
    for (int round = 0; round < 4; round++) {
      boolean any = false;
      for (CI c : new ArrayList<>(world.values())) {
        if (c.origin != APP || !c.mutable()) continue;
        for (MethodNode m : c.node.methods) {
          if (m.instructions == null || m.instructions.size() == 0 || !mentionsLooseStub(m)) continue;
          org.objectweb.asm.tree.analysis.Frame<org.objectweb.asm.tree.analysis.SourceValue>[] frames;
          try { frames = new org.objectweb.asm.tree.analysis.Analyzer<>(new org.objectweb.asm.tree.analysis.SourceInterpreter()).analyze(c.name, m); }
          catch (org.objectweb.asm.tree.analysis.AnalyzerException e) { continue; }
          for (int i = 0; i < frames.length; i++) {
            var f = frames[i];
            if (f == null) continue;
            AbstractInsnNode n = m.instructions.get(i);
            if (n instanceof MethodInsnNode mi) {
              Type[] args = Type.getArgumentTypes(mi.desc);
              int base = f.getStackSize() - args.length;
              for (int k = 0; k < args.length; k++)
                for (String x : producedTypes(m, frames, i, base + k, 0)) if (constrain(x, args[k], changed)) any = true;
            } else if (n instanceof FieldInsnNode fi && (n.getOpcode() == Opcodes.PUTFIELD || n.getOpcode() == Opcodes.PUTSTATIC)) {
              for (String x : producedTypes(m, frames, i, f.getStackSize() - 1, 0)) if (constrain(x, Type.getType(fi.desc), changed)) any = true;
            } else if (n.getOpcode() == Opcodes.ARETURN) {
              for (String x : producedTypes(m, frames, i, f.getStackSize() - 1, 0)) if (constrain(x, Type.getReturnType(m.desc), changed)) any = true;
            }
          }
        }
      }
      if (!any) break;
    }
    return changed;
  }
  boolean looseStub(String n) { CI c = world.get(n); return c != null && c.origin == STUB && !c.templated && c.mutable() && !c.isInterface(); }
  boolean mentionsLooseStub(MethodNode m) {
    for (AbstractInsnNode n : m.instructions) {
      if (n instanceof TypeInsnNode t && looseStub(t.desc)) return true;
      if (n instanceof MethodInsnNode mi) { Type r = Type.getReturnType(mi.desc); if (r.getSort() == Type.OBJECT && looseStub(r.getInternalName())) return true; }
      if (n instanceof FieldInsnNode fi) { Type t = Type.getType(fi.desc); if (t.getSort() == Type.OBJECT && looseStub(t.getInternalName())) return true; }
    }
    return false;
  }
  /** the static types of the value in a stack slot before instruction i (through locals and DUPs) */
  Set<String> producedTypes(MethodNode m, org.objectweb.asm.tree.analysis.Frame<org.objectweb.asm.tree.analysis.SourceValue>[] frames, int i, int slot, int depth) {
    Set<String> out = new HashSet<>();
    if (depth > 6 || slot < 0 || frames[i] == null || slot >= frames[i].getStackSize()) return out;
    for (AbstractInsnNode src : frames[i].getStack(slot).insns) {
      int si = m.instructions.indexOf(src);
      switch (src.getOpcode()) {
        case Opcodes.NEW: case Opcodes.CHECKCAST: out.add(((TypeInsnNode) src).desc); break;
        case Opcodes.INVOKEVIRTUAL: case Opcodes.INVOKESTATIC: case Opcodes.INVOKEINTERFACE: case Opcodes.INVOKESPECIAL: {
          Type r = Type.getReturnType(((MethodInsnNode) src).desc);
          if (r.getSort() == Type.OBJECT) out.add(r.getInternalName());
          break;
        }
        case Opcodes.GETFIELD: case Opcodes.GETSTATIC: { Type t = Type.getType(((FieldInsnNode) src).desc); if (t.getSort() == Type.OBJECT) out.add(t.getInternalName()); break; }
        case Opcodes.DUP: if (frames[si] != null) out.addAll(producedTypes(m, frames, si, frames[si].getStackSize() - 1, depth + 1)); break;
        case Opcodes.ALOAD: {
          var fr = frames[si];
          if (fr == null) break;
          for (AbstractInsnNode st : fr.getLocal(((VarInsnNode) src).var).insns) {
            int sti = m.instructions.indexOf(st);
            if (st.getOpcode() == Opcodes.ASTORE && frames[sti] != null) out.addAll(producedTypes(m, frames, sti, frames[sti].getStackSize() - 1, depth + 1));
          }
          break;
        }
        default: break;
      }
    }
    return out;
  }
  boolean constrain(String x, Type p, Set<CI> changed) {
    if (p.getSort() != Type.OBJECT || !looseStub(x)) return false;
    String pn = p.getInternalName();
    if (pn.equals("java/lang/Object") || pn.equals(x) || isSubclass(x, pn)) return false;
    CI xc = world.get(x), pc = world.get(pn);
    if (pc == null || isSubclass(pn, x)) return false;
    if (pc.isInterface()) {
      xc.itfs.add(pn);
      if (!xc.node.interfaces.contains(pn)) xc.node.interfaces.add(pn);
    } else {
      // a class: becomes the superclass when x's is Object or an ancestor of it
      if (!xc.sup.equals("java/lang/Object") && !isSubclass(pn, xc.sup)) return false;
      if ((pc.access & Opcodes.ACC_FINAL) != 0) return false;
      reparent(xc, pn);
    }
    changed.add(xc);
    return true;
  }
  void reparent(CI xc, String sup) {
    xc.sup = sup;
    xc.node.superName = sup;
    // constructors are regenerated against the new superclass; stubs the new ancestors implement go away
    List<String> ctorDescs = new ArrayList<>();
    for (MethodNode mn : new ArrayList<>(xc.node.methods)) {
      String sig = mn.name + mn.desc;
      if (mn.name.equals("<init>")) { ctorDescs.add(mn.desc); xc.node.methods.remove(mn); xc.methods.remove(sig); continue; }
      if ((mn.access & Opcodes.ACC_STATIC) != 0 || mn.name.startsWith("<")) continue;
      CI decl = findMethod(world.get(sup), mn.name, mn.desc);
      if (decl != null && (decl.methods.get(sig) & Opcodes.ACC_ABSTRACT) == 0) { xc.node.methods.remove(mn); xc.methods.remove(sig); }
    }
    for (String d : ctorDescs) addMethodStub(xc, "<init>", d, false);
    redirects.add(xc.name + " now extends " + sup);
  }

  /**
   * A concrete class that inherits an abstract method nobody implements (its superclass was generated from android.jar
   * with only the members the app uses, e.g. NumberKeyListener.filter) gets a stub, placed on the generated ancestor
   * when there is one so every subclass shares it.
   */
  void fillAbstractHoles() {
    for (CI c : new ArrayList<>(world.values())) {
      if (!c.mutable() || c.isInterface() || !c.concrete()) continue;
      List<CI> chain = new ArrayList<>();
      for (CI k = c; k != null && chain.size() < 64; k = k.sup == null ? null : world.get(k.sup)) chain.add(k);
      Set<String> impl = new HashSet<>(), required = new LinkedHashSet<>();
      for (CI k : chain) for (Map.Entry<String, Integer> e : k.methods.entrySet()) {
        String sig = e.getKey(); int acc = e.getValue();
        if ((acc & Opcodes.ACC_STATIC) != 0 || sig.startsWith("<")) continue;
        if ((acc & Opcodes.ACC_ABSTRACT) == 0) impl.add(sig); else required.add(sig);
      }
      Set<String> seen = new HashSet<>();
      Deque<String> q = new ArrayDeque<>();
      for (CI k : chain) q.addAll(k.itfs);
      while (!q.isEmpty()) {
        String i = q.poll();
        if (!seen.add(i)) continue;
        CI ic = world.get(i);
        if (ic == null) continue;
        q.addAll(ic.itfs);
        for (Map.Entry<String, Integer> e : ic.methods.entrySet()) {
          String sig = e.getKey(); int acc = e.getValue();
          if ((acc & Opcodes.ACC_STATIC) != 0 || sig.startsWith("<")) continue;
          if ((acc & Opcodes.ACC_ABSTRACT) == 0) impl.add(sig); else required.add(sig);
        }
      }
      for (String sig : required) {
        if (impl.contains(sig)) continue;
        CI target = c;
        for (CI k : chain) if (k.origin == STUB && k.mutable()) target = k;
        int p = sig.indexOf('(');
        String name = sig.substring(0, p), desc = sig.substring(p);
        if (target.methods.containsKey(sig)) {   // declared abstract there: give it a body
          target.node.methods.removeIf(m -> m.name.equals(name) && m.desc.equals(desc));
          target.methods.remove(sig);
        }
        addMethodStub(target, name, desc, false);
        worklist.add(target);
        impl.add(sig);
      }
    }
  }

  void addMethodStub(CI c, String name, String desc, boolean isStatic) {
    if (c.methods.containsKey(name + desc)) return;
    int acc = Opcodes.ACC_PUBLIC | (isStatic ? Opcodes.ACC_STATIC : 0);
    MethodNode m;
    if (name.equals("<init>")) m = ctorStub(c, desc);
    else m = stubBody(c.node, acc, name, desc, c.name.replace('/', '.') + "." + name, !isStatic, c);
    if (c.isInterface() && !isStatic && c.node.version < Opcodes.V1_8) c.node.version = Opcodes.V1_8;
    c.node.methods.add(m);
    c.methods.put(name + desc, m.access);
    stubbedMembers.merge(c.name.replace('/', '.') + "." + name + desc + (c.origin == FW ? " (framework)" : c.origin == APP ? " (app)" : ""), 1, Integer::sum);
  }

  /** a constructor the class lacks: delegate to the most similar one (same leading parameters), else to the super's */
  MethodNode ctorStub(CI c, String desc) {
    MethodNode m = new MethodNode(Opcodes.ACC_PUBLIC, "<init>", desc, null, null);
    Type[] args = Type.getArgumentTypes(desc);
    InsnList il = m.instructions;
    String target = c.name;
    String best = null;
    int bestScore = -1;
    for (String k : c.methods.keySet()) {
      if (!k.startsWith("<init>(")) continue;
      String d = k.substring(6);
      if (d.equals(desc)) continue;
      int s = prefixScore(args, Type.getArgumentTypes(d));
      if (s > bestScore) { bestScore = s; best = d; }
    }
    // a generated class has no real constructor to call: hand the arguments to the superclass's constructor of
    // the same shape (generated there too when it is missing), so a framework class up the chain gets initialized
    CI supc = world.get(c.sup);
    if (c.origin == STUB && supc != null && !c.sup.equals("java/lang/Object")
        && (supc.methods.containsKey("<init>" + desc) || supc.mutable())) {
      if (!supc.methods.containsKey("<init>" + desc)) addMethodStub(supc, "<init>", desc, false);
      best = desc;
      target = c.sup;
      bestScore = args.length;
    } else if (best == null || bestScore == 0 && !best.equals("()V")) {
      // nothing in this class: the superclass
      target = c.sup;
      CI sup = world.get(c.sup);
      best = null; bestScore = -1;
      if (sup != null) for (String k : sup.methods.keySet()) {
        if (!k.startsWith("<init>(")) continue;
        String d = k.substring(6);
        if ((sup.methods.get(k) & Opcodes.ACC_PRIVATE) != 0) continue;
        int s = prefixScore(args, Type.getArgumentTypes(d)) * 100 - Type.getArgumentTypes(d).length;
        if (s > bestScore) { bestScore = s; best = d; }
      }
      if (best == null && sup != null && sup.mutable()) { addMethodStub(sup, "<init>", "()V", false); best = "()V"; }
      if (best == null) best = "()V";
    }
    il.add(new VarInsnNode(Opcodes.ALOAD, 0));
    Type[] targs = Type.getArgumentTypes(best);
    int slot = 1;
    int[] slots = new int[args.length];
    for (int i = 0; i < args.length; i++) { slots[i] = slot; slot += args[i].getSize(); }
    for (int i = 0; i < targs.length; i++) {
      if (i < args.length && args[i].equals(targs[i])) il.add(new VarInsnNode(args[i].getOpcode(Opcodes.ILOAD), slots[i]));
      else il.add(defaultValue(targs[i]));
    }
    il.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, target, "<init>", best, false));
    il.add(new LdcInsnNode(c.name.replace('/', '.') + ".<init>" + desc));
    il.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "a2w/Stubs", "hit", "(Ljava/lang/String;)V", false));
    il.add(new InsnNode(Opcodes.RETURN));
    m.maxLocals = slot;
    return m;
  }
  static int prefixScore(Type[] want, Type[] have) {
    int s = 0;
    for (int i = 0; i < Math.min(want.length, have.length); i++) { if (!want[i].equals(have[i])) break; s++; }
    if (have.length > want.length) return s == want.length ? s : 0;   // extra params get defaults only after a full match
    return s;
  }

  /** log once, return a harmless default: this (builders), "", empty collections and arrays, dummy SDK objects */
  MethodNode stubBody(ClassNode owner, int acc, String name, String desc, String label, boolean instance, CI c) {
    MethodNode m = new MethodNode(acc, name, desc, null, null);
    InsnList il = m.instructions;
    il.add(new LdcInsnNode(label));
    il.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "a2w/Stubs", "hit", "(Ljava/lang/String;)V", false));
    Type r = Type.getReturnType(desc);
    if (r.getSort() == Type.VOID) il.add(new InsnNode(Opcodes.RETURN));
    else {
      if (instance && c != null && r.getSort() == Type.OBJECT && r.getInternalName().equals(c.name) && builderStyle(c.name, name))
        il.add(new VarInsnNode(Opcodes.ALOAD, 0));
      else il.add(richDefault(r));
      il.add(new InsnNode(r.getOpcode(Opcodes.IRETURN)));
    }
    int locals = (acc & Opcodes.ACC_STATIC) != 0 ? 0 : 1;
    for (Type t : Type.getArgumentTypes(desc)) locals += t.getSize();
    m.maxLocals = locals;
    return m;
  }

  /** a method returning its own class that looks like a builder step: returning this keeps call chains going */
  static boolean builderStyle(String cls, String method) {
    return cls.contains("Builder") || method.matches("(set|add|with|append|put|enable|disable|use|apply|extend)[A-Z_0-9].*");
  }

  static AbstractInsnNode defaultValue(Type t) {
    switch (t.getSort()) {
      case Type.LONG: return new InsnNode(Opcodes.LCONST_0);
      case Type.FLOAT: return new InsnNode(Opcodes.FCONST_0);
      case Type.DOUBLE: return new InsnNode(Opcodes.DCONST_0);
      case Type.OBJECT: case Type.ARRAY: return new InsnNode(Opcodes.ACONST_NULL);
      default: return new InsnNode(Opcodes.ICONST_0);
    }
  }
  InsnList richDefault(Type t) {
    InsnList il = new InsnList();
    if (t.getSort() == Type.ARRAY && t.getDimensions() == 1) {
      il.add(new InsnNode(Opcodes.ICONST_0));
      Type e = t.getElementType();
      if (e.getSort() == Type.OBJECT) il.add(new TypeInsnNode(Opcodes.ANEWARRAY, e.getInternalName()));
      else il.add(new IntInsnNode(Opcodes.NEWARRAY, newarrayCode(e)));
      return il;
    }
    if (t.getSort() != Type.OBJECT) { il.add(defaultValue(t)); return il; }
    String n = t.getInternalName();
    String impl = switch (n) {
      case "java/util/List", "java/util/Collection", "java/lang/Iterable", "java/util/ArrayList" -> "java/util/ArrayList";
      case "java/util/Map", "java/util/HashMap" -> "java/util/HashMap";
      case "java/util/Set", "java/util/HashSet" -> "java/util/HashSet";
      default -> null;
    };
    if (n.equals("java/lang/String") || n.equals("java/lang/CharSequence")) { il.add(new LdcInsnNode("")); return il; }
    CI c = world.get(n);
    if (impl == null && c != null && c.origin == STUB && !c.templated && c.concrete() && !n.endsWith("Exception") && !n.endsWith("Error")) impl = n;
    if (impl == null) { il.add(new InsnNode(Opcodes.ACONST_NULL)); return il; }
    if (impl.equals(n) && c != null && !c.methods.containsKey("<init>()V")) addMethodStub(c, "<init>", "()V", false);
    il.add(new TypeInsnNode(Opcodes.NEW, impl));
    il.add(new InsnNode(Opcodes.DUP));
    il.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, impl, "<init>", "()V", false));
    return il;
  }
  static int newarrayCode(Type e) {
    switch (e.getSort()) {
      case Type.BOOLEAN: return Opcodes.T_BOOLEAN;
      case Type.CHAR: return Opcodes.T_CHAR;
      case Type.BYTE: return Opcodes.T_BYTE;
      case Type.SHORT: return Opcodes.T_SHORT;
      case Type.INT: return Opcodes.T_INT;
      case Type.LONG: return Opcodes.T_LONG;
      case Type.FLOAT: return Opcodes.T_FLOAT;
      default: return Opcodes.T_DOUBLE;
    }
  }
  static String mangle(String internal) {
    StringBuilder b = new StringBuilder();
    for (char ch : internal.toCharArray()) b.append(Character.isLetterOrDigit(ch) ? ch : '_');
    return b.toString();
  }

  // ================================================================ app patches
  /** JDK members patchClass sends elsewhere (the API report counts them as implemented) */
  static final Set<String> RETARGETED = Set.of("java/lang/System.loadLibrary(Ljava/lang/String;)V", "java/lang/System.load(Ljava/lang/String;)V",
      "java/lang/Runtime.loadLibrary(Ljava/lang/String;)V", "java/lang/System.exit(I)V", "java/lang/Runtime.exit(I)V", "java/lang/Runtime.halt(I)V",
      "java/lang/Class.forName(Ljava/lang/String;)Ljava/lang/Class;", "java/lang/Class.forName(Ljava/lang/String;ZLjava/lang/ClassLoader;)Ljava/lang/Class;",
      "java/lang/Class.newInstance()Ljava/lang/Object;", "java/lang/Class.getCanonicalName()Ljava/lang/String;",
      "java/lang/Class.getClassLoader()Ljava/lang/ClassLoader;", "java/lang/Thread.getContextClassLoader()Ljava/lang/ClassLoader;",
      "java/lang/ClassLoader.getSystemClassLoader()Ljava/lang/ClassLoader;", "java/lang/ClassLoader.loadClass(Ljava/lang/String;)Ljava/lang/Class;");
  /** new RandomAccessFile(f, "rw") must create a missing file (TeaVM's throws FileNotFoundException) */
  static final Set<String> FIXED_CTORS = Set.of("java/io/RandomAccessFile");
  void patchClass(CI c) {
    ClassNode cn = c.node;
    for (MethodNode m : cn.methods) {
      if ((m.access & Opcodes.ACC_NATIVE) != 0) bridgeNative(cn, m);
      if (m.instructions == null) continue;
      for (AbstractInsnNode n = m.instructions.getFirst(); n != null; n = n.getNext()) {
        if (!(n instanceof MethodInsnNode mi)) continue;
        String k = mi.owner + "." + mi.name + mi.desc;
        switch (k) {
          case "java/lang/System.loadLibrary(Ljava/lang/String;)V": retarget(mi, "a2w/Jni", "loadLibrary", mi.desc); break;
          case "java/lang/System.load(Ljava/lang/String;)V": retarget(mi, "a2w/Jni", "load", mi.desc); break;
          case "java/lang/Runtime.loadLibrary(Ljava/lang/String;)V": popReceiver(m, mi); retarget(mi, "a2w/Jni", "loadLibrary", "(Ljava/lang/String;)V"); break;
          case "java/lang/System.exit(I)V": retarget(mi, "a2w/Host", "exit", mi.desc); break;
          case "java/lang/Runtime.exit(I)V": case "java/lang/Runtime.halt(I)V": popReceiver(m, mi); retarget(mi, "a2w/Host", "exit", "(I)V"); break;
          case "java/lang/Class.forName(Ljava/lang/String;)Ljava/lang/Class;": retarget(mi, "a2w/Reflection", "forName", mi.desc); break;
          case "java/lang/Class.forName(Ljava/lang/String;ZLjava/lang/ClassLoader;)Ljava/lang/Class;":
            retarget(mi, "a2w/Reflection", "forName", mi.desc); break;
          case "java/lang/Class.newInstance()Ljava/lang/Object;": retarget(mi, "a2w/jdk/Compat", "Class_newInstance", "(Ljava/lang/Class;)Ljava/lang/Object;"); break;
          case "java/lang/Class.getCanonicalName()Ljava/lang/String;": retarget(mi, "a2w/jdk/Compat", "Class_getCanonicalName", "(Ljava/lang/Class;)Ljava/lang/String;"); break;
          case "java/lang/Class.getClassLoader()Ljava/lang/ClassLoader;": retarget(mi, "a2w/jdk/Compat", "Class_getClassLoader", "(Ljava/lang/Class;)Ljava/lang/ClassLoader;"); break;
          case "java/lang/Thread.getContextClassLoader()Ljava/lang/ClassLoader;": retarget(mi, "a2w/jdk/Compat", "Thread_getContextClassLoader", "(Ljava/lang/Thread;)Ljava/lang/ClassLoader;"); break;
          case "java/lang/ClassLoader.getSystemClassLoader()Ljava/lang/ClassLoader;": retarget(mi, "a2w/jdk/Compat", "ClassLoader_getSystemClassLoader", mi.desc); break;
          default:
            if (mi.name.equals("loadClass") && mi.desc.equals("(Ljava/lang/String;)Ljava/lang/Class;") && isSubclass(mi.owner, "java/lang/ClassLoader"))
              retarget(mi, "a2w/jdk/Compat", "ClassLoader_loadClass", "(Ljava/lang/ClassLoader;Ljava/lang/String;)Ljava/lang/Class;");
            // a render loop's frame limiter: sleeping would block the page, so it becomes a frame delay
            else if (k.equals("java/lang/Thread.sleep(J)V") && m.name.equals("onDrawFrame"))
              retarget(mi, "a2w/Host", "frameSleep", "(J)V");
            // JDK constructors TeaVM gets wrong: the object is made as a2w/jdk/ctor/X, which fixes the arguments
            else if (mi.getOpcode() == Opcodes.INVOKESPECIAL && mi.name.equals("<init>") && FIXED_CTORS.contains(mi.owner))
              ctorSubclass(c, m, mi);
        }
      }
    }
  }
  /** TeaVM's StringBuilder and StringBuffer throw NullPointerException for a null CharSequence or StringBuffer, where
   *  Java appends or inserts "null": those calls go to a2w.jdk.Compat */
  static final Map<String, String> NULL_SAFE = new HashMap<>();
  static {
    for (String sb : new String[] {"StringBuilder", "StringBuffer"}) {
      String t = "Ljava/lang/" + sb + ";";
      NULL_SAFE.put("java/lang/" + sb + ".append(Ljava/lang/CharSequence;)" + t, sb + "_appendCS");
      NULL_SAFE.put("java/lang/" + sb + ".append(Ljava/lang/CharSequence;II)" + t, sb + "_appendCSRange");
      NULL_SAFE.put("java/lang/" + sb + ".append(Ljava/lang/StringBuffer;)" + t, sb + "_appendSB");
      NULL_SAFE.put("java/lang/" + sb + ".insert(ILjava/lang/CharSequence;)" + t, sb + "_insertCS");
      NULL_SAFE.put("java/lang/" + sb + ".insert(ILjava/lang/CharSequence;II)" + t, sb + "_insertCSRange");
    }
  }
  static void nullSafeAppend(MethodInsnNode mi) {
    if (mi.getOpcode() != Opcodes.INVOKEVIRTUAL) return;
    String name = NULL_SAFE.get(mi.owner + "." + mi.name + mi.desc);
    if (name != null) retarget(mi, "a2w/jdk/Compat", name, "(L" + mi.owner + ";" + mi.desc.substring(1));
  }
  static void retarget(MethodInsnNode mi, String owner, String name, String desc) {
    mi.setOpcode(Opcodes.INVOKESTATIC); mi.owner = owner; mi.name = name; mi.desc = desc; mi.itf = false;
  }
  /** Runtime.getRuntime().x(arg) -> drop the Runtime receiver that sits under the argument */
  static void popReceiver(MethodNode m, MethodInsnNode mi) {
    Type[] a = Type.getArgumentTypes(mi.desc);
    if (a.length != 1) return;
    InsnList il = new InsnList();
    if (a[0].getSize() == 1) { il.add(new InsnNode(Opcodes.SWAP)); il.add(new InsnNode(Opcodes.POP)); }
    else { il.add(new InsnNode(Opcodes.DUP2_X1)); il.add(new InsnNode(Opcodes.POP2)); il.add(new InsnNode(Opcodes.POP)); }
    m.instructions.insertBefore(mi, il);
  }

  /** native method -> Jni.enter(); int[] slots = {args as 32-bit words}; return Jni.callX(id, this|Class, slots) */
  void bridgeNative(ClassNode cn, MethodNode m) {
    m.access &= ~Opcodes.ACC_NATIVE;
    boolean isStatic = (m.access & Opcodes.ACC_STATIC) != 0;
    int id = natives.size() + 1;
    natives.add(new String[] {cn.name, m.name, m.desc, isStatic ? "1" : "0"});
    Type[] args = Type.getArgumentTypes(m.desc);
    int words = 0;
    for (Type t : args) words += t.getSize();
    InsnList il = new InsnList();
    int locals = isStatic ? 0 : 1;
    for (Type t : args) locals += t.getSize();
    int arr = locals;
    il.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "a2w/Jni", "enter", "()V", false));
    il.add(push(words));
    il.add(new IntInsnNode(Opcodes.NEWARRAY, Opcodes.T_INT));
    il.add(new VarInsnNode(Opcodes.ASTORE, arr));
    int slot = isStatic ? 0 : 1, w = 0;
    for (Type t : args) {
      switch (t.getSort()) {
        case Type.LONG: case Type.DOUBLE: {
          for (int half = 0; half < 2; half++) {
            il.add(new VarInsnNode(Opcodes.ALOAD, arr));
            il.add(push(w + half));
            il.add(new VarInsnNode(t.getOpcode(Opcodes.ILOAD), slot));
            if (t.getSort() == Type.DOUBLE) il.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/lang/Double", "doubleToRawLongBits", "(D)J", false));
            if (half == 1) { il.add(push(32)); il.add(new InsnNode(Opcodes.LUSHR)); }
            il.add(new InsnNode(Opcodes.L2I));
            il.add(new InsnNode(Opcodes.IASTORE));
          }
          break;
        }
        default:
          il.add(new VarInsnNode(Opcodes.ALOAD, arr));
          il.add(push(w));
          il.add(new VarInsnNode(t.getOpcode(Opcodes.ILOAD), slot));
          if (t.getSort() == Type.FLOAT) il.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/lang/Float", "floatToRawIntBits", "(F)I", false));
          else if (t.getSort() == Type.OBJECT || t.getSort() == Type.ARRAY) il.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "a2w/Jni", "handle", "(Ljava/lang/Object;)I", false));
          il.add(new InsnNode(Opcodes.IASTORE));
      }
      slot += t.getSize();
      w += t.getSize();
    }
    il.add(push(id));
    if (isStatic) il.add(new LdcInsnNode(Type.getObjectType(cn.name)));
    else il.add(new VarInsnNode(Opcodes.ALOAD, 0));
    il.add(new VarInsnNode(Opcodes.ALOAD, arr));
    Type r = Type.getReturnType(m.desc);
    String suffix = switch (r.getSort()) {
      case Type.VOID -> "V"; case Type.BOOLEAN -> "Z"; case Type.BYTE -> "B"; case Type.CHAR -> "C"; case Type.SHORT -> "S";
      case Type.INT -> "I"; case Type.LONG -> "J"; case Type.FLOAT -> "F"; case Type.DOUBLE -> "D"; default -> "L";
    };
    String rd = suffix.equals("L") ? "Ljava/lang/Object;" : suffix;
    il.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "a2w/Jni", "call" + suffix, "(ILjava/lang/Object;[I)" + rd, false));
    if (suffix.equals("L") && !r.getDescriptor().equals("Ljava/lang/Object;"))
      il.add(new TypeInsnNode(Opcodes.CHECKCAST, r.getSort() == Type.ARRAY ? r.getDescriptor() : r.getInternalName()));
    il.add(new InsnNode(r.getOpcode(Opcodes.IRETURN)));
    m.instructions = il;
    m.tryCatchBlocks = new ArrayList<>();
    m.maxLocals = arr + 1;
  }
  static AbstractInsnNode push(int v) {
    if (v >= -1 && v <= 5) return new InsnNode(Opcodes.ICONST_0 + v);
    if (v >= Byte.MIN_VALUE && v <= Byte.MAX_VALUE) return new IntInsnNode(Opcodes.BIPUSH, v);
    if (v >= Short.MIN_VALUE && v <= Short.MAX_VALUE) return new IntInsnNode(Opcodes.SIPUSH, v);
    return new LdcInsnNode(v);
  }

  // ================================================================ outputs
  static String jniMangle(String s) {
    StringBuilder b = new StringBuilder();
    for (char ch : s.toCharArray()) {
      if (ch == '/') b.append('_');
      else if (ch == '_') b.append("_1");
      else if (ch == ';') b.append("_2");
      else if (ch == '[') b.append("_3");
      else if (ch < 128 && Character.isLetterOrDigit(ch)) b.append(ch);
      else b.append(String.format("_0%04x", (int) ch));
    }
    return b.toString();
  }
  void writeNatives(Path p) throws IOException {
    StringBuilder b = new StringBuilder("[\n");
    for (int i = 0; i < natives.size(); i++) {
      String[] n = natives.get(i);
      String shortSym = "Java_" + jniMangle(n[0]) + "_" + jniMangle(n[1]);
      String args = n[2].substring(1, n[2].indexOf(')'));
      b.append(String.format(" {\"id\":%d,\"cls\":%s,\"name\":%s,\"sig\":%s,\"static\":%s,\"sym\":%s,\"lsym\":%s}%s%n",
          i + 1, js(n[0]), js(n[1]), js(n[2]), n[3].equals("1"), js(shortSym), js(shortSym + "__" + jniMangle(args)),
          i + 1 < natives.size() ? "," : ""));
    }
    Files.writeString(p, b.append("]\n").toString());
  }
  static String js(String s) {
    StringBuilder b = new StringBuilder("\"");
    for (char ch : s.toCharArray()) {
      if (ch == '"' || ch == '\\') b.append('\\').append(ch);
      else if (ch < 32 || ch > 126) b.append(String.format("\\u%04x", (int) ch));
      else b.append(ch);
    }
    return b.append('"').toString();
  }
  void writeReport(Path p, Gen gen) throws IOException {
    StringBuilder b = new StringBuilder();
    b.append("# apk2web link report\n\n## classes generated from android.jar\n");
    for (String s : stubbedClasses) if (!unknownClasses.contains(s)) b.append(s).append('\n');
    b.append("\n## classes nobody defines (generated from how the app uses them)\n");
    for (String s : unknownClasses) b.append(s).append('\n');
    b.append("\n## stubbed members\n");
    for (String s : stubbedMembers.keySet()) b.append(s).append('\n');
    b.append("\n## redirected call sites (JDK members TeaVM lacks)\n");
    for (String s : redirects) b.append(s).append('\n');
    b.append("\n## renamed JDK classes\n");
    for (Map.Entry<String, String> e : new TreeMap<>(remap).entrySet()) b.append(e.getKey()).append(" -> ").append(e.getValue()).append('\n');
    b.append(gen.report());
    Files.writeString(p, b.toString());
  }

  // ================================================================ roots (from build_res.py)
  static final class Roots {
    String application = "";
    List<String> activities = new ArrayList<>(), components = new ArrayList<>(), tags = new ArrayList<>(),
        strings = new ArrayList<>(), onClick = new ArrayList<>();
    static Roots read(Path p) throws IOException {
      Roots r = new Roots();
      for (String line : Files.readAllLines(p, StandardCharsets.UTF_8)) {
        int t = line.indexOf('\t');
        if (t < 0) continue;
        String k = line.substring(0, t), v = line.substring(t + 1);
        switch (k) {
          case "application": r.application = v; break;
          case "activity": r.activities.add(v); break;
          case "component": r.components.add(v); break;
          case "tag": r.tags.add(v); break;
          case "string": r.strings.add(v); break;
          case "onClick": r.onClick.add(v); break;
          default:
        }
      }
      return r;
    }
  }
}
