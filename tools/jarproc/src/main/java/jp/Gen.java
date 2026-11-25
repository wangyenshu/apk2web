package jp;

import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Pattern;

/**
 * Generates the per-app tables that stand in for runtime metadata:
 * <ul>
 * <li>a2w.gen.Reflect: the classes the app can name (manifest components, layout tags, class-name strings, class
 *     literals when it instantiates reflectively), their constructors of the shapes Android instantiates
 *     reflectively, and the methods named in strings (onClick handlers, getMethod arguments).</li>
 * <li>a2w.gen.JniTable: the classes, methods, constructors and fields the native libraries name in their strings.</li>
 * </ul>
 * Both register into hand-written runtime code (a2w.Reflection, a2w.Jni) and dispatch calls by id.
 */
final class Gen {
  final Link L;
  final Link.Roots roots;
  final List<String> sos;
  static final int CHUNK = 250;
  static final Set<String> ANNOTATION_GETTERS = Set.of("getAnnotation", "isAnnotationPresent", "getAnnotations", "getDeclaredAnnotations",
      "getAnnotationsByType", "getDeclaredAnnotation", "getDeclaredAnnotationsByType");
  static final Pattern CLASS_NAME = Pattern.compile("[A-Za-z_$][\\w$]*(\\.[A-Za-z_$][\\w$]*)+");
  static final Pattern IDENT = Pattern.compile("[A-Za-z_$][\\w$]*|<init>");
  /** what cocos2d-x's script bridges pass: int (also Lua function handles), float, boolean, String */
  static final Pattern BRIDGE_SIG = Pattern.compile("[\\w$]+\\(([IFZ]|Ljava/lang/String;)*\\)([VIFZ]|Ljava/lang/String;)");
  static final String CTX = "Landroid/content/Context;", ATTRS = "Landroid/util/AttributeSet;";
  /** constructor shapes the platform and libraries instantiate reflectively ((), views, fragments, view models...) */
  static final List<String> SIG_SHAPES = List.of("()V", "(" + CTX + ")V", "(" + CTX + ATTRS + ")V", "(" + CTX + ATTRS + "I)V",
      "(" + CTX + ATTRS + "II)V", "(Landroid/app/Application;)V");
  static final List<String> INSTANTIATED_BASES = List.of("android/app/Activity", "android/app/Service", "android/content/BroadcastReceiver",
      "android/content/ContentProvider", "android/app/Application", "android/app/Fragment", "android/view/View",
      "android/app/backup/BackupAgent", "android/app/Instrumentation");

  Gen(Link l, Link.Roots r, List<String> sos) { L = l; roots = r; this.sos = sos; }

  // reflect
  final LinkedHashMap<String, String> regNames = new LinkedHashMap<>();   // dotted name -> internal
  final List<String> sigs = new ArrayList<>();
  final List<String[]> ctors = new ArrayList<>();      // cls, desc, sig index
  final List<String[]> methods = new ArrayList<>();    // cls, name, desc, static?, private?
  final Set<String> methodNamesWanted = new TreeSet<>();
  final Set<String> fieldNamesWanted = new TreeSet<>();
  final Set<String> ctorClassesWanted = new TreeSet<>();
  final Map<String, List<StringBuilder>> packedInts = new LinkedHashMap<>();
  final List<FieldEntry> fields = new ArrayList<>();      // reflection on fields
  record FieldEntry(String owner, FieldNode f, boolean constant) {}
  boolean fieldReflect;
  final Set<String> arrayTypes = new TreeSet<>();
  // annotations reflection reads (Method/Field.getAnnotation: EventBus's @Subscribe, Gson's @SerializedName ...)
  boolean methodAnnotations, fieldAnnotations;
  record AnnUse(String owner, String member, String desc, boolean field, AnnotationNode an) {}
  final List<AnnUse> annUses = new ArrayList<>();
  final LinkedHashMap<String, String> annImpls = new LinkedHashMap<>();   // annotation interface -> generated class
  final List<ClassNode> extraClasses = new ArrayList<>();   // classes whose array type reflection creates (getSpans, Array.newInstance)
  final Set<String> genericCallers = new TreeSet<>();             // classes that ask for generic superclasses/interfaces
  final LinkedHashMap<String, String> classSigs = new LinkedHashMap<>();   // internal name -> Signature attribute
  // jni
  final Set<String> soStrings = new HashSet<>();
  final Set<String> jniDropped = new TreeSet<>();   // dropped SDK classes native code names
  static final java.util.regex.Pattern IDENT_PATH = java.util.regex.Pattern.compile("[A-Za-z_$][\\w$]*(/[A-Za-z_$][\\w$]*)+");
  final LinkedHashMap<String, String> jniClasses = new LinkedHashMap<>();   // name native code uses -> internal
  final LinkedHashMap<String, Integer> jniKeys = new LinkedHashMap<>();
  final List<String[]> jniMethods = new ArrayList<>(), jniFields = new ArrayList<>();   // decl, name, desc, static, private, itf
  final Map<String, Integer> jniIds = new HashMap<>();
  final List<String> jniArrays = new ArrayList<>(), jniThrows = new ArrayList<>();

  List<ClassNode> generate() throws IOException {
    collectReflect();
    collectJni();
    List<ClassNode> out = new ArrayList<>(List.of(reflectClass(), jniClass()));
    out.addAll(extraClasses);
    return out;
  }

  // ================================================================ reflection
  void collectReflect() {
    sigs.add("()V");
    Set<String> want = new LinkedHashSet<>();
    Set<String> materialize = new LinkedHashSet<>();   // named by the manifest or layouts: must exist, even as stubs
    if (!roots.application.isEmpty()) materialize.add(roots.application);
    materialize.addAll(roots.activities);
    materialize.addAll(roots.components);
    for (String t : roots.tags) {
      if (t.indexOf('.') >= 0) materialize.add(t);
      else for (String p : new String[] {"android.widget.", "android.webkit.", "android.app.", "android.view.", "android.preference."})
        if (L.world.containsKey((p + t).replace('.', '/')) || L.template((p + t).replace('.', '/')) != null) { materialize.add(p + t); break; }
    }
    want.addAll(roots.strings);
    boolean reflectiveNew = false;
    Set<String> dynamicPackages = new TreeSet<>();
    Set<String> literals = new LinkedHashSet<>();
    for (Link.CI c : L.world.values()) {
      if (c.origin != Link.APP) continue;
      for (MethodNode m : c.node.methods) {
        if (m.instructions == null) continue;
        for (AbstractInsnNode n : m.instructions) {
          if (n instanceof MethodInsnNode fn && fn.name.equals("forName") && (fn.owner.equals("a2w/Reflection") || fn.owner.equals("java/lang/Class"))) {
            // Class.forName of a name built at run time ("com.x.screen." + name): the classes of the caller's package
            AbstractInsnNode p = fn.getPrevious();
            while (p != null && (p instanceof LineNumberNode || p instanceof LabelNode)) p = p.getPrevious();
            if (!(p instanceof LdcInsnNode) && c.name.indexOf('/') > 0) {
              String[] parts = c.name.split("/");
              dynamicPackages.add(String.join("/", java.util.Arrays.copyOf(parts, Math.min(3, parts.length - 1))) + "/");
            }
          }
          if (n instanceof MethodInsnNode an && ANNOTATION_GETTERS.contains(an.name)) {
            if (an.owner.equals("a2w/jdk/reflect/Method") || an.owner.equals("a2w/jdk/reflect/AccessibleObject") || an.owner.equals("java/lang/reflect/AccessibleObject") || an.owner.equals("java/lang/reflect/AnnotatedElement")) methodAnnotations = true;
            if (an.owner.equals("a2w/jdk/reflect/Field") || an.owner.equals("a2w/jdk/reflect/AccessibleObject") || an.owner.equals("java/lang/reflect/AccessibleObject") || an.owner.equals("java/lang/reflect/AnnotatedElement")) fieldAnnotations = true;
          }
          // getSpans(start, end, X.class) and Array.newInstance(X.class, n) create X[]: TeaVM needs that array type
          if (n instanceof MethodInsnNode am && (am.name.equals("getSpans") || am.name.equals("newInstance") && am.owner.equals("java/lang/reflect/Array"))) {
            AbstractInsnNode p = am.getPrevious();
            for (int k = 0; k < 6 && p != null; k++, p = p.getPrevious())
              if (p instanceof LdcInsnNode pl && pl.cst instanceof Type t && (t.getSort() == Type.OBJECT || t.getSort() == Type.ARRAY)) {
                if (t.getSort() == Type.OBJECT && L.world.containsKey(t.getInternalName())) arrayTypes.add(t.getInternalName());
                break;
              }
          }
          if (n instanceof LdcInsnNode ld) {
            if (ld.cst instanceof String s && s.length() < 200 && CLASS_NAME.matcher(s).matches()) want.add(s);
            else if (ld.cst instanceof Type t && t.getSort() == Type.OBJECT) literals.add(t.getInternalName());
          } else if (n instanceof MethodInsnNode mi && (mi.owner.equals("android/animation/ObjectAnimator") || mi.owner.equals("android/animation/PropertyValuesHolder"))
              && (mi.name.startsWith("of") || mi.name.equals("setPropertyName"))) {
            // ObjectAnimator.ofFloat(target, "alpha", ...): the property's setter and getter are called by name
            AbstractInsnNode p = mi.getPrevious();
            for (int k = 0; k < 16 && p != null; k++, p = p.getPrevious())
              if (p instanceof LdcInsnNode pl && pl.cst instanceof String s && !s.isEmpty() && Character.isJavaIdentifierStart(s.charAt(0))) {
                String cap = Character.toUpperCase(s.charAt(0)) + s.substring(1);
                methodNamesWanted.add("set" + cap); methodNamesWanted.add("get" + cap);
                break;
              }
          } else if (n instanceof MethodInsnNode mi && mi.owner.equals("a2w/jdk/Compat")) {
            if (mi.name.startsWith("Class_getConstructor") || mi.name.startsWith("Class_getDeclaredConstructor") || mi.name.equals("Class_newInstance"))
              reflectiveNew = true;
            if (mi.name.startsWith("Class_getConstructor") || mi.name.startsWith("Class_getDeclaredConstructor")) {
              // a platform class whose constructor the app looks up (HandlerCompat: Handler(Looper, Callback, boolean)):
              // the class literals just before the call
              AbstractInsnNode p = mi.getPrevious();
              for (int k = 0; k < 40 && p != null; k++, p = p.getPrevious())
                if (p instanceof LdcInsnNode pl && pl.cst instanceof Type t && t.getSort() == Type.OBJECT) ctorClassesWanted.add(t.getInternalName());
                else if (p instanceof MethodInsnNode) break;
            }
            if (mi.name.startsWith("Class_getField") || mi.name.startsWith("Class_getDeclaredField")) {
              fieldReflect = true;
              // a platform field named by the app (LayoutInflater's "mFactory2"): registered too
              AbstractInsnNode p = mi.getPrevious();
              for (int k = 0; k < 8 && p != null; k++, p = p.getPrevious())
                if (p instanceof LdcInsnNode pl && pl.cst instanceof String s) { fieldNamesWanted.add(s); break; }
            }
            if (mi.name.equals("Class_getGenericSuperclass") || mi.name.equals("Class_getGenericInterfaces")) genericCallers.add(c.name);
            if (mi.name.equals("Class_getMethod") || mi.name.equals("Class_getDeclaredMethod")) {
              AbstractInsnNode p = mi.getPrevious();
              for (int k = 0; k < 8 && p != null; k++, p = p.getPrevious())
                if (p instanceof LdcInsnNode pl && pl.cst instanceof String s) { methodNamesWanted.add(s); break; }
            }
          }
        }
      }
    }
    methodNamesWanted.addAll(roots.onClick);
    // methods with runtime annotations, when the app reads method annotations: registered, with their annotations
    if (methodAnnotations)
      for (Link.CI c : L.world.values()) {
        if (c.origin != Link.APP || c.node == null) continue;
        for (MethodNode m : c.node.methods) {
          if (m.visibleAnnotations == null || m.name.startsWith("<")) continue;
          boolean any = false;
          for (AnnotationNode an : m.visibleAnnotations) if (annotationUsable(an.desc)) { annUses.add(new AnnUse(c.name, m.name, m.desc, false, an)); any = true; }
          if (any) methodNamesWanted.add(m.name);
        }
      }
    // WebView JavaScript interfaces: the methods marked @JavascriptInterface, and (apps from before that annotation)
    // the public methods of the objects handed to addJavascriptInterface
    for (Link.CI c : L.world.values()) {
      if (c.origin != Link.APP || c.node == null) continue;
      for (MethodNode m : c.node.methods) {
        if (m.visibleAnnotations != null)
          for (AnnotationNode an : m.visibleAnnotations) if (an.desc.equals("Landroid/webkit/JavascriptInterface;")) methodNamesWanted.add(m.name);
        if (m.instructions == null) continue;
        for (AbstractInsnNode n : m.instructions) {
          if (!(n instanceof MethodInsnNode mi) || !mi.name.equals("addJavascriptInterface")) continue;
          AbstractInsnNode p = mi.getPrevious();
          for (int k = 0; k < 24 && p != null; k++, p = p.getPrevious()) {
            if (p instanceof TypeInsnNode tn && tn.getOpcode() == Opcodes.NEW) {
              Link.CI t = L.world.get(tn.desc);
              if (t != null && t.node != null && t.origin == Link.APP)
                for (MethodNode tm : t.node.methods) if ((tm.access & Opcodes.ACC_PUBLIC) != 0 && !tm.name.startsWith("<")) methodNamesWanted.add(tm.name);
              break;
            }
          }
        }
      }
    }
    // (only the app's own packages, those of its manifest components: libraries probe for optional classes this way)
    List<String> own = new ArrayList<>();
    for (String a : roots.activities) own.add(a.replace('.', '/'));
    if (!roots.application.isEmpty()) own.add(roots.application.replace('.', '/'));
    // (the organization's: com/x/app/ may build names in com/x/engine/)
    dynamicPackages.removeIf(pk -> { String org = String.join("/", java.util.Arrays.copyOf(pk.split("/"), 2)) + "/"; return own.stream().noneMatch(a -> a.startsWith(org)); });
    for (Link.CI c : L.world.values())
      if (c.origin == Link.APP && c.concrete() && !c.name.contains("$"))
        for (String pk : dynamicPackages) if (c.name.startsWith(pk)) { want.add(c.name.replace('/', '.')); break; }
    for (String s : materialize) {
      String in = s.replace('.', '/');
      if (!L.world.containsKey(in) && (L.template(in) == null)) continue;
      L.ensureClass(in);
      want.add(s);
    }
    for (String s : want) {
      String in = s.replace('.', '/');
      Link.CI c = L.world.get(in);
      if (c == null && L.remap.containsKey(in)) c = L.world.get(L.remap.get(in));
      if (c != null) regNames.put(s, c.name);
    }
    for (String in : literals) {
      Link.CI c = L.world.get(in);
      if (c == null || c.origin != Link.APP || !c.concrete()) continue;
      boolean base = false;
      for (String b : INSTANTIATED_BASES) if (L.isSubclass(in, b)) { base = true; break; }
      if (reflectiveNew || base) regNames.putIfAbsent(in.replace('/', '.'), in);
    }
    // generic signatures: of the subclasses of the classes that read them (TypeToken, TypeReference ...); when those
    // read them of anything (a utility method), of every class whose superclass has type arguments
    if (!genericCallers.isEmpty()) {
      for (Link.CI c : L.world.values()) {
        if (c.origin != Link.APP || c.node == null || c.node.signature == null || !superHasArgs(c.node.signature, false)) continue;
        for (String caller : genericCallers) if (!caller.equals(c.name) && L.isSubclass(c.name, caller)) { classSigs.put(c.name, c.node.signature); break; }
      }
      if (classSigs.isEmpty())
        for (Link.CI c : L.world.values())
          if (c.origin == Link.APP && c.node != null && c.node.signature != null && superHasArgs(c.node.signature, true) && !L.isSubclass(c.name, "kotlin/jvm/internal/Lambda"))
            classSigs.put(c.name, c.node.signature);
      for (String sg : classSigs.values()) registerSignatureClasses(sg);
    }
    // field reflection (Gson and the like walk a model's fields): the classes those fields hold are reached too
    if (fieldReflect) {
      ArrayDeque<String> todo = new ArrayDeque<>(new TreeSet<>(regNames.values()));
      todo.addAll(literals);
      Set<String> done = new HashSet<>(todo);
      while (!todo.isEmpty()) {
        Link.CI c = L.world.get(todo.poll());
        if (c == null || c.origin != Link.APP || c.node == null) continue;
        for (FieldNode f : c.node.fields) {
          if ((f.access & (Opcodes.ACC_STATIC | Opcodes.ACC_SYNTHETIC)) != 0) continue;
          Set<String> named = new LinkedHashSet<>();
          if (f.signature != null) named.addAll(signatureClasses(f.signature));
          Type t = Type.getType(f.desc);
          while (t.getSort() == Type.ARRAY) t = t.getElementType();
          if (t.getSort() == Type.OBJECT) named.add(t.getInternalName());
          for (String n : named) {
            Link.CI k = L.world.get(n);
            if (k == null) continue;
            if (f.signature != null) regNames.putIfAbsent(n.replace('/', '.'), n);
            if (k.origin == Link.APP && done.add(n)) { regNames.putIfAbsent(n.replace('/', '.'), n); todo.add(n); }
          }
        }
      }
    }
    // what the platform will instantiate needs the constructor it uses
    for (String s : materialize) {
      Link.CI c = L.world.get(s.replace('.', '/'));
      if (c == null || !c.mutable() || !c.concrete()) continue;
      boolean view = L.isSubclass(c.name, "android/view/View") || L.isSubclass(c.name, "android/preference/Preference");
      String need = view && roots.tags.contains(s) || view && roots.tags.contains(s.substring(s.lastIndexOf('.') + 1))
          ? "(" + CTX + ATTRS + ")V" : "()V";
      if (!c.methods.containsKey("<init>" + need)) L.addMethodStub(c, "<init>", need, false);
    }
    Set<String> seen = new HashSet<>();
    for (String in : regNames.values()) {
      if (!seen.add(in)) continue;
      Link.CI c = L.world.get(in);
      if (c == null || !c.concrete()) continue;
      for (String k : new ArrayList<>(c.methods.keySet())) {
        if (!k.startsWith("<init>(")) continue;
        String d = k.substring(6);
        // the platform's shapes; and when the app looks constructors up itself (getConstructor(Application.class,
        // Context.class, ...)), any constructor of its own classes it names
        if (!SIG_SHAPES.contains(d) && !(reflectiveNew && c.origin == Link.APP)) continue;
        if (c.origin == Link.JDK && (c.methods.get(k) & Opcodes.ACC_PUBLIC) == 0) continue;
        int si = sigs.indexOf(d);
        if (si < 0) { sigs.add(d); si = sigs.size() - 1; }
        ctors.add(new String[] {in, d, String.valueOf(si)});
      }
    }
    for (String in : ctorClassesWanted) {
      Link.CI c = L.world.get(in);
      if (c == null || c.origin != Link.FW || !c.concrete()) continue;
      for (String k : new ArrayList<>(c.methods.keySet())) {
        if (!k.startsWith("<init>(") || (c.methods.get(k) & (Opcodes.ACC_PUBLIC | Opcodes.ACC_PROTECTED)) == 0) continue;
        String d = k.substring(6);
        boolean have = false;
        for (String[] e : ctors) if (e[0].equals(in) && e[1].equals(d)) { have = true; break; }
        if (have) continue;
        int si = sigs.indexOf(d);
        if (si < 0) { sigs.add(d); si = sigs.size() - 1; }
        ctors.add(new String[] {in, d, String.valueOf(si)});
        regNames.putIfAbsent(in.replace('/', '.'), in);
      }
    }
    if (!methodNamesWanted.isEmpty())
      for (Link.CI c : L.world.values()) {
        // the app's own methods, and the platform's public ones (an SDK probing for a newer API by name)
        if (c.origin != Link.APP && c.origin != Link.FW || c.isInterface() || c.node == null) continue;
        for (MethodNode m : c.node.methods)
          if (methodNamesWanted.contains(m.name) && (m.access & Opcodes.ACC_ABSTRACT) == 0 && !m.name.startsWith("<")
              && (c.origin == Link.APP || (m.access & Opcodes.ACC_PUBLIC) != 0))
            methods.add(new String[] {c.name, m.name, m.desc, (m.access & Opcodes.ACC_STATIC) != 0 ? "1" : "0", (m.access & Opcodes.ACC_PRIVATE) != 0 ? "1" : "0"});
      }
    // fields, when the app looks fields up by name: of the classes it can name (strings, class literals) and of its R
    // classes (resource ids by name). static final constants are registered by value
    if (fieldReflect) {
      Set<String> owners = new TreeSet<>();
      for (String in : regNames.values()) owners.add(in);
      owners.addAll(literals);
      for (Link.CI c : L.world.values())
        if (c.origin == Link.APP && (c.name.endsWith("/R") || c.name.contains("/R$"))) owners.add(c.name);
      for (String in : owners) {
        Link.CI c = L.world.get(in);
        if (c == null || c.node == null || c.origin != Link.APP && c.origin != Link.FW) continue;
        for (FieldNode f : c.node.fields) {
          if ((f.access & Opcodes.ACC_SYNTHETIC) != 0) continue;
          if (c.origin == Link.FW && !fieldNamesWanted.contains(f.name)) continue;
          boolean constant = (f.access & (Opcodes.ACC_STATIC | Opcodes.ACC_FINAL)) == (Opcodes.ACC_STATIC | Opcodes.ACC_FINAL) && f.value != null;
          if (constant && f.desc.equals("I") && f.value instanceof Integer v && f.visibleAnnotations == null) {
            // int constants (R classes have tens of thousands): packed in strings, unpacked when looked at
            int mod = f.access & 0xFFFF;
            String e = L.fieldOrigName.getOrDefault(c.name + "." + f.name, f.name) + ":" + Integer.toString(v, 36)
                + (mod == 0x19 ? "" : ":" + Integer.toString(mod, 36));
            List<StringBuilder> parts = packedInts.computeIfAbsent(c.name, k -> new ArrayList<>());
            if (parts.isEmpty() || parts.get(parts.size() - 1).length() > 16000) parts.add(new StringBuilder());
            StringBuilder sb = parts.get(parts.size() - 1);
            if (sb.length() > 0) sb.append(';');
            sb.append(e);
            continue;
          }
          fields.add(new FieldEntry(c.name, f, constant));
        }
      }
    }
  }

  /** does a class Signature give its superclass (or, unless superOnly, an interface) type arguments? */
  static boolean superHasArgs(String sig, boolean superOnly) {
    int i = 0;
    if (sig.startsWith("<")) { int d = 0; for (; i < sig.length(); i++) { char ch = sig.charAt(i); if (ch == '<') d++; else if (ch == '>' && --d == 0) { i++; break; } } }
    if (!superOnly) return sig.indexOf('<', i) >= 0;
    int d = 0;
    for (int k = i; k < sig.length(); k++) { char ch = sig.charAt(k); if (ch == '<') return true; if (ch == ';' && d == 0) return false; }
    return false;
  }
  /** the classes a generic signature names (inner classes as Outer$Inner) */
  static Set<String> signatureClasses(String sig) {
    Set<String> out = new LinkedHashSet<>();
    try {
      new org.objectweb.asm.signature.SignatureReader(sig).accept(new org.objectweb.asm.signature.SignatureVisitor(Opcodes.ASM9) {
        String current;
        @Override public void visitClassType(String name) { current = name; out.add(name); }
        @Override public void visitInnerClassType(String name) { current = current + "$" + name; out.add(current); }
      });
    } catch (RuntimeException e) { /* malformed: ignore */ }
    return out;
  }
  /** so that a2w.Generics can turn the names in a signature into classes */
  void registerSignatureClasses(String sig) {
    for (String n : signatureClasses(sig)) if (L.world.containsKey(n)) regNames.putIfAbsent(n.replace('/', '.'), n);
  }

  /** an annotation type reflection can be given an instance of: a runtime-retained interface the world defines */
  boolean annotationUsable(String desc) {
    if (!desc.startsWith("L") || !desc.endsWith(";")) return false;
    String n = desc.substring(1, desc.length() - 1);
    if (n.startsWith("kotlin/") || n.startsWith("dalvik/annotation/") || n.startsWith("java/lang/annotation/")) return false;
    Link.CI a = L.world.get(n);
    return a != null && a.isInterface();
  }

  /** a class implementing annotation interface a: element values in an Object[] (boxed) */
  String annotationImpl(String a) {
    String have = annImpls.get(a);
    if (have != null) return have;
    String name = "a2w/gen/Ann" + annImpls.size();
    annImpls.put(a, name);
    ClassNode cn = Link.newClass(name, "java/lang/Object", Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL);
    cn.interfaces.add(a);
    cn.interfaces.add("java/lang/annotation/Annotation");
    cn.fields.add(new FieldNode(Opcodes.ACC_FINAL, "v", "[Ljava/lang/Object;", null, null));
    MethodNode init = new MethodNode(Opcodes.ACC_PUBLIC, "<init>", "([Ljava/lang/Object;)V", null, null);
    init.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));
    init.instructions.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false));
    init.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));
    init.instructions.add(new VarInsnNode(Opcodes.ALOAD, 1));
    init.instructions.add(new FieldInsnNode(Opcodes.PUTFIELD, name, "v", "[Ljava/lang/Object;"));
    init.instructions.add(new InsnNode(Opcodes.RETURN));
    cn.methods.add(init);
    List<String[]> elems = annotationElements(a);
    for (int i = 0; i < elems.size(); i++) {
      String[] e = elems.get(i);
      Type rt = Type.getReturnType(e[1]);
      MethodNode m = new MethodNode(Opcodes.ACC_PUBLIC, e[0], e[1], null, null);
      m.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));
      m.instructions.add(new FieldInsnNode(Opcodes.GETFIELD, name, "v", "[Ljava/lang/Object;"));
      m.instructions.add(Link.push(i));
      m.instructions.add(new InsnNode(Opcodes.AALOAD));
      String box = boxName(rt);
      if (box != null) {
        m.instructions.add(new TypeInsnNode(Opcodes.CHECKCAST, box));
        m.instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, box, rt.getClassName() + "Value", "()" + rt.getDescriptor(), false));
        m.instructions.add(new InsnNode(rt.getOpcode(Opcodes.IRETURN)));
      } else {
        m.instructions.add(new TypeInsnNode(Opcodes.CHECKCAST, rt.getSort() == Type.ARRAY ? rt.getDescriptor() : rt.getInternalName()));
        m.instructions.add(new InsnNode(Opcodes.ARETURN));
      }
      cn.methods.add(m);
    }
    MethodNode at = new MethodNode(Opcodes.ACC_PUBLIC, "annotationType", "()Ljava/lang/Class;", null, null);
    at.instructions.add(new LdcInsnNode(Type.getObjectType(a)));
    at.instructions.add(new InsnNode(Opcodes.ARETURN));
    cn.methods.add(at);
    MethodNode ts = new MethodNode(Opcodes.ACC_PUBLIC, "toString", "()Ljava/lang/String;", null, null);
    ts.instructions.add(new LdcInsnNode("@" + a.replace('/', '.') + "()"));
    ts.instructions.add(new InsnNode(Opcodes.ARETURN));
    cn.methods.add(ts);
    extraClasses.add(cn);
    return name;
  }
  /** {name, desc} of an annotation interface's elements, in declaration order */
  List<String[]> annotationElements(String a) {
    List<String[]> out = new ArrayList<>();
    Link.CI c = L.world.get(a);
    if (c == null) return out;
    if (c.node != null) { for (MethodNode m : c.node.methods) if ((m.access & Opcodes.ACC_STATIC) == 0 && !m.name.startsWith("<")) out.add(new String[] {m.name, m.desc}); }
    else for (String k : c.methods.keySet()) { int p = k.indexOf('('); if (p > 0 && !k.startsWith("<")) out.add(new String[] {k.substring(0, p), k.substring(p)}); }
    return out;
  }
  static Type primitiveOf(Object v) {
    if (v instanceof Long) return Type.LONG_TYPE;
    if (v instanceof Float) return Type.FLOAT_TYPE;
    if (v instanceof Double) return Type.DOUBLE_TYPE;
    if (v instanceof Boolean) return Type.BOOLEAN_TYPE;
    if (v instanceof Character) return Type.CHAR_TYPE;
    if (v instanceof Short) return Type.SHORT_TYPE;
    if (v instanceof Byte) return Type.BYTE_TYPE;
    return Type.INT_TYPE;
  }
  static void pushPrimitive(InsnList il, Object v) {
    if (v instanceof Boolean b) il.add(Link.push(b ? 1 : 0));
    else if (v instanceof Character ch) il.add(Link.push(ch));
    else if (v instanceof Byte || v instanceof Short || v instanceof Integer) il.add(Link.push(((Number) v).intValue()));
    else il.add(new LdcInsnNode(v));
  }
  static int newarrayCode(Type t) {
    switch (t.getSort()) {
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
  String arrayElementClass(Type comp) {
    if (comp.getSort() == Type.ARRAY) return comp.getDescriptor();
    return comp.getSort() == Type.OBJECT && L.world.containsKey(comp.getInternalName()) ? comp.getInternalName() : "java/lang/Object";
  }
  /** pushes an annotation element value (as an AnnotationNode stores it), boxed */
  void pushAnnValue(InsnList il, Object v, Type t) {
    if (v == null) { pushAnnDefault(il, t); return; }
    if (v instanceof String[] en && en.length == 2) {   // an enum constant: {desc, name}
      Type et = Type.getType(en[0]);
      Link.CI ec = L.world.get(et.getInternalName());
      if (ec != null && ec.fields.containsKey(en[1])) il.add(new FieldInsnNode(Opcodes.GETSTATIC, et.getInternalName(), en[1], en[0]));
      else il.add(new InsnNode(Opcodes.ACONST_NULL));
      return;
    }
    if (v instanceof Type ct) {
      il.add(new LdcInsnNode(ct.getSort() == Type.OBJECT && !L.world.containsKey(ct.getInternalName()) ? Type.getType(Object.class) : ct));
      return;
    }
    if (v instanceof AnnotationNode) { il.add(new InsnNode(Opcodes.ACONST_NULL)); return; }
    if (v instanceof List<?> list) {
      if (t.getSort() != Type.ARRAY || t.getDimensions() > 1) { il.add(new InsnNode(Opcodes.ACONST_NULL)); return; }
      Type comp = t.getElementType();
      boolean prim = boxName(comp) != null;
      il.add(Link.push(list.size()));
      if (prim) il.add(new IntInsnNode(Opcodes.NEWARRAY, newarrayCode(comp)));
      else il.add(new TypeInsnNode(Opcodes.ANEWARRAY, arrayElementClass(comp)));
      for (int i = 0; i < list.size(); i++) {
        il.add(new InsnNode(Opcodes.DUP));
        il.add(Link.push(i));
        if (prim) { pushPrimitive(il, list.get(i)); il.add(new InsnNode(comp.getOpcode(Opcodes.IASTORE))); }
        else {
          pushAnnValue(il, list.get(i), comp);
          String ec = arrayElementClass(comp);
          if (!ec.equals("java/lang/Object")) il.add(new TypeInsnNode(Opcodes.CHECKCAST, ec));
          il.add(new InsnNode(Opcodes.AASTORE));
        }
      }
      return;
    }
    if (v instanceof String) { il.add(new LdcInsnNode(v)); return; }
    pushPrimitive(il, v);
    box(il, primitiveOf(v));
  }
  void pushAnnDefault(InsnList il, Type t) {
    switch (t.getSort()) {
      case Type.BOOLEAN: case Type.CHAR: case Type.BYTE: case Type.SHORT: case Type.INT: il.add(Link.push(0)); box(il, t); break;
      case Type.LONG: il.add(new InsnNode(Opcodes.LCONST_0)); box(il, t); break;
      case Type.FLOAT: il.add(new InsnNode(Opcodes.FCONST_0)); box(il, t); break;
      case Type.DOUBLE: il.add(new InsnNode(Opcodes.DCONST_0)); box(il, t); break;
      case Type.ARRAY: {
        il.add(Link.push(0));
        Type comp = t.getElementType();
        if (t.getDimensions() == 1 && boxName(comp) != null) il.add(new IntInsnNode(Opcodes.NEWARRAY, newarrayCode(comp)));
        else il.add(new TypeInsnNode(Opcodes.ANEWARRAY, t.getDimensions() > 1 ? t.getDescriptor().substring(1) : arrayElementClass(comp)));
        break;
      }
      case Type.OBJECT:
        if (t.getInternalName().equals("java/lang/String")) il.add(new LdcInsnNode(""));
        else il.add(new InsnNode(Opcodes.ACONST_NULL));
        break;
      default: il.add(new InsnNode(Opcodes.ACONST_NULL));
    }
  }
  /** new AnnN(new Object[] {element values in order: given, else the interface's defaults}) */
  void pushAnnotation(InsnList il, AnnotationNode an) {
    String a = an.desc.substring(1, an.desc.length() - 1);
    String impl = annotationImpl(a);
    List<String[]> elems = annotationElements(a);
    Map<String, Object> given = new HashMap<>();
    if (an.values != null) for (int i = 0; i + 1 < an.values.size(); i += 2) given.put((String) an.values.get(i), an.values.get(i + 1));
    Link.CI ac = L.world.get(a);
    il.add(new TypeInsnNode(Opcodes.NEW, impl));
    il.add(new InsnNode(Opcodes.DUP));
    il.add(Link.push(elems.size()));
    il.add(new TypeInsnNode(Opcodes.ANEWARRAY, "java/lang/Object"));
    for (int i = 0; i < elems.size(); i++) {
      String[] e = elems.get(i);
      Object v = given.get(e[0]);
      if (v == null && ac != null && ac.node != null)
        for (MethodNode m : ac.node.methods) if (m.name.equals(e[0]) && m.desc.equals(e[1])) v = m.annotationDefault;
      il.add(new InsnNode(Opcodes.DUP));
      il.add(Link.push(i));
      pushAnnValue(il, v, Type.getReturnType(e[1]));
      il.add(new InsnNode(Opcodes.AASTORE));
    }
    il.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, impl, "<init>", "([Ljava/lang/Object;)V", false));
  }

  ClassNode reflectClass() {
    if (fieldAnnotations)
      for (FieldEntry fe : fields)
        if (fe.f().visibleAnnotations != null)
          for (AnnotationNode an : fe.f().visibleAnnotations)
            if (annotationUsable(an.desc)) annUses.add(new AnnUse(fe.owner(), L.fieldOrigName.getOrDefault(fe.owner() + "." + fe.f().name, fe.f().name), fe.f().desc, true, an));
    ClassNode cn = Link.newClass("a2w/gen/Reflect", "java/lang/Object", Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL);
    List<Consumer<InsnList>> reg = new ArrayList<>();
    for (String s : sigs) reg.add(il -> { pushClassArray(il, Type.getArgumentTypes(s)); il.add(call("a2w/Reflection", "sig", "([Ljava/lang/Class;)V")); });
    for (Map.Entry<String, String> e : regNames.entrySet()) reg.add(il -> {
      il.add(new LdcInsnNode(e.getKey()));
      il.add(new LdcInsnNode(Type.getObjectType(e.getValue())));
      il.add(call("a2w/Reflection", "cls", "(Ljava/lang/String;Ljava/lang/Class;)V"));
    });
    for (int i = 0; i < ctors.size(); i++) {
      String[] c = ctors.get(i);
      int id = i + 1;
      reg.add(il -> {
        il.add(new LdcInsnNode(Type.getObjectType(c[0])));
        il.add(Link.push(Integer.parseInt(c[2])));
        il.add(Link.push(id));
        il.add(call("a2w/Reflection", "ctor", "(Ljava/lang/Class;II)V"));
      });
    }
    for (int i = 0; i < methods.size(); i++) {
      String[] m = methods.get(i);
      int id = i + 1;
      reg.add(il -> {
        il.add(new LdcInsnNode(Type.getObjectType(m[0])));
        il.add(new LdcInsnNode(m[1]));
        pushClassArray(il, Type.getArgumentTypes(m[2]));
        il.add(Link.push(id));
        il.add(call("a2w/Reflection", "method", "(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;I)V"));
      });
    }
    for (int i = 0; i < fields.size(); i++) {
      FieldEntry fe = fields.get(i);
      int id = i + 1;
      reg.add(il -> {
        il.add(new LdcInsnNode(Type.getObjectType(fe.owner())));
        il.add(new LdcInsnNode(L.fieldOrigName.getOrDefault(fe.owner() + "." + fe.f().name, fe.f().name)));
        pushClass(il, Type.getType(fe.f().desc));
        il.add(Link.push(fe.f().access & 0xFFFF));
        il.add(Link.push(id));
        if (fe.constant()) { il.add(new LdcInsnNode(fe.f().value)); box(il, Type.getType(fe.f().desc)); }
        else il.add(new InsnNode(Opcodes.ACONST_NULL));
        il.add(call("a2w/Reflection", "field", "(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Class;IILjava/lang/Object;)V"));
      });
    }
    for (Map.Entry<String, List<StringBuilder>> e : packedInts.entrySet())
      for (StringBuilder sb : e.getValue()) reg.add(il -> {
        il.add(new LdcInsnNode(Type.getObjectType(e.getKey())));
        il.add(new LdcInsnNode(sb.toString()));
        il.add(call("a2w/Reflection", "intConstants", "(Ljava/lang/Class;Ljava/lang/String;)V"));
      });
    for (AnnUse u : annUses) reg.add(il -> {
      il.add(new LdcInsnNode(Type.getObjectType(u.owner())));
      il.add(new LdcInsnNode(u.member()));
      il.add(Link.push(u.field() ? -1 : Type.getArgumentTypes(u.desc()).length));
      pushAnnotation(il, u.an());
      il.add(call("a2w/Annotations", "add", "(Ljava/lang/Class;Ljava/lang/String;ILjava/lang/Object;)V"));
    });
    for (String t : arrayTypes) reg.add(il -> { il.add(new InsnNode(Opcodes.ICONST_0)); il.add(new TypeInsnNode(Opcodes.ANEWARRAY, t)); il.add(new InsnNode(Opcodes.POP)); });
    for (Map.Entry<String, String> e : classSigs.entrySet()) reg.add(il -> {
      il.add(new LdcInsnNode(Type.getObjectType(e.getKey())));
      il.add(new LdcInsnNode(e.getValue()));
      il.add(call("a2w/Generics", "classSig", "(Ljava/lang/Class;Ljava/lang/String;)V"));
    });
    for (FieldEntry fe : fields) {
      if (fe.f().signature == null) continue;
      reg.add(il -> {
        il.add(new LdcInsnNode(Type.getObjectType(fe.owner())));
        il.add(new LdcInsnNode(L.fieldOrigName.getOrDefault(fe.owner() + "." + fe.f().name, fe.f().name)));
        il.add(new LdcInsnNode(fe.f().signature));
        il.add(call("a2w/Generics", "fieldSig", "(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)V"));
      });
    }
    registerMethods(cn, "register", reg);

    // fieldGet(int id, Object target) Object, fieldSet(int id, Object target, Object value)
    List<Consumer<InsnList>> fget = new ArrayList<>(), fset = new ArrayList<>();
    for (FieldEntry fe : fields) {
      boolean st = (fe.f().access & Opcodes.ACC_STATIC) != 0;
      Type t = Type.getType(fe.f().desc);
      fget.add(il -> {
        if (fe.constant()) { il.add(new InsnNode(Opcodes.ACONST_NULL)); il.add(new InsnNode(Opcodes.ARETURN)); return; }
        if (!st) { il.add(new VarInsnNode(Opcodes.ALOAD, 1)); il.add(new TypeInsnNode(Opcodes.CHECKCAST, fe.owner())); }
        il.add(new FieldInsnNode(st ? Opcodes.GETSTATIC : Opcodes.GETFIELD, fe.owner(), fe.f().name, fe.f().desc));
        box(il, t);
        il.add(new InsnNode(Opcodes.ARETURN));
      });
      fset.add(il -> {
        if (fe.constant() || st && (fe.f().access & Opcodes.ACC_FINAL) != 0) { il.add(new InsnNode(Opcodes.RETURN)); return; }
        if (!st) { il.add(new VarInsnNode(Opcodes.ALOAD, 1)); il.add(new TypeInsnNode(Opcodes.CHECKCAST, fe.owner())); }
        il.add(new VarInsnNode(Opcodes.ALOAD, 2));
        String box = boxName(t);
        if (box == null) il.add(new TypeInsnNode(Opcodes.CHECKCAST, t.getSort() == Type.ARRAY ? t.getDescriptor() : t.getInternalName()));
        else { il.add(new TypeInsnNode(Opcodes.CHECKCAST, box)); il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, box, t.getClassName() + "Value", "()" + t.getDescriptor(), false)); }
        il.add(new FieldInsnNode(st ? Opcodes.PUTSTATIC : Opcodes.PUTFIELD, fe.owner(), fe.f().name, fe.f().desc));
        il.add(new InsnNode(Opcodes.RETURN));
      });
    }
    dispatch(cn, "fieldGet", "(ILjava/lang/Object;)Ljava/lang/Object;", fget, il -> { il.add(new InsnNode(Opcodes.ACONST_NULL)); il.add(new InsnNode(Opcodes.ARETURN)); });
    dispatch(cn, "fieldSet", "(ILjava/lang/Object;Ljava/lang/Object;)V", fset, il -> il.add(new InsnNode(Opcodes.RETURN)));

    // construct(int id, Object[] args) Object
    List<Consumer<InsnList>> cases = new ArrayList<>();
    for (String[] c : ctors) cases.add(il -> {
      il.add(new TypeInsnNode(Opcodes.NEW, c[0]));
      il.add(new InsnNode(Opcodes.DUP));
      Type[] p = Type.getArgumentTypes(c[1]);
      for (int i = 0; i < p.length; i++) unboxArg(il, 1, i, p[i]);
      il.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, c[0], "<init>", c[1], false));
      il.add(new InsnNode(Opcodes.ARETURN));
    });
    dispatch(cn, "construct", "(I[Ljava/lang/Object;)Ljava/lang/Object;", cases, il -> { il.add(new InsnNode(Opcodes.ACONST_NULL)); il.add(new InsnNode(Opcodes.ARETURN)); });
    // invoke(int id, Object target, Object[] args) Object
    cases = new ArrayList<>();
    for (String[] m : methods) cases.add(il -> {
      boolean st = m[3].equals("1");
      if (!st) { il.add(new VarInsnNode(Opcodes.ALOAD, 1)); il.add(new TypeInsnNode(Opcodes.CHECKCAST, m[0])); }
      Type[] p = Type.getArgumentTypes(m[2]);
      for (int i = 0; i < p.length; i++) unboxArg(il, 2, i, p[i]);
      int op = st ? Opcodes.INVOKESTATIC : m[4].equals("1") ? Opcodes.INVOKESPECIAL : Opcodes.INVOKEVIRTUAL;
      il.add(new MethodInsnNode(op, m[0], m[1], m[2], false));
      box(il, Type.getReturnType(m[2]));
      il.add(new InsnNode(Opcodes.ARETURN));
    });
    dispatch(cn, "invoke", "(ILjava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;", cases, il -> { il.add(new InsnNode(Opcodes.ACONST_NULL)); il.add(new InsnNode(Opcodes.ARETURN)); });
    return cn;
  }

  // ================================================================ JNI
  void collectJni() throws IOException {
    for (String so : sos) readStrings(Files.readAllBytes(Paths.get(so)), soStrings);
    if (soStrings.isEmpty()) return;
    Set<String> names = new HashSet<>(), sigStrings = new HashSet<>();
    for (String s : soStrings) {
      if (IDENT.matcher(s).matches()) names.add(s);
      else if (s.startsWith("(") && s.indexOf(')') > 0) sigStrings.add(s);
    }
    List<String> named = new ArrayList<>();
    for (String s : new TreeSet<>(soStrings)) {
      if (s.indexOf('/') <= 0 || s.length() > 200 || s.contains(" ") || s.startsWith("/") || s.contains("//")) continue;
      String in = L.world.containsKey(s) ? s : L.remap.containsKey(s) && L.world.containsKey(L.remap.get(s)) ? L.remap.get(s) : null;
      // a platform class only native code names (java/nio/NIOAccess): its hand-written replacement
      if (in == null && s.startsWith("java/") && L.world.containsKey("a2w/jdk/" + s.substring(5))) in = "a2w/jdk/" + s.substring(5);
      if (in == null && Link.dropped(s, L.drop) && IDENT_PATH.matcher(s).matches()) { jniDropped.add(s); continue; }
      if (in == null) continue;
      jniClasses.put(s, in);
      named.add(in);
    }
    // members: of the named classes (declared or inherited), and those app subclasses declare themselves
    Set<String> namedSet = new HashSet<>(named);
    for (String in : named) {
      Link.CI c = L.world.get(in);
      List<Link.CI> walk = hierarchy(c);
      for (Link.CI k : walk) addMembers(in, k, k == c, names, sigStrings);
    }
    Set<String> covered = new HashSet<>(namedSet);
    for (Link.CI c : L.world.values()) {
      if (c.origin != Link.APP || namedSet.contains(c.name)) continue;
      for (String in : named) if (L.isSubclass(c.name, in)) { addMembers(c.name, c, true, names, sigStrings); covered.add(c.name); break; }
    }
    // classes native code reaches without naming them (GetObjectClass, a callback object, an inherited framework
    // method): members whose name and signature both appear in the library (the runtime looks up the superclasses)
    for (Link.CI c : L.world.values())
      if ((c.origin == Link.APP || c.origin == Link.FW || c.origin == Link.JDK) && !covered.contains(c.name)) addMembersStrict(c, names, sigStrings);
    // a script bridge (cocos2d-x's luaj / jsb.reflection.callStaticMethod) calls static methods its scripts name, and
    // scripts are often compiled or encrypted: every public static method of the app with a signature it can pass
    if (soStrings.contains("callStaticMethod") || soStrings.contains("callJavaStaticMethod")) {
      int before = jniMethods.size();
      for (Link.CI c : L.world.values()) {
        if (c.origin != Link.APP) continue;
        boolean any = false;
        for (Map.Entry<String, Integer> m : c.methods.entrySet()) {
          int acc = m.getValue();
          if ((acc & (Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC)) != (Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC) || !BRIDGE_SIG.matcher(m.getKey()).matches()) continue;
          int p = m.getKey().indexOf('(');
          String id = c.name + "." + m.getKey();
          Integer i = jniIds.get(id);
          if (i == null) {
            jniMethods.add(new String[] {c.name, m.getKey().substring(0, p), m.getKey().substring(p), "1", "0", c.isInterface() ? "1" : "0"});
            i = jniMethods.size();
            jniIds.put(id, i);
          }
          jniKeys.putIfAbsent(id, i);
          any = true;
        }
        if (any) jniClasses.putIfAbsent(c.name, c.name);
      }
      System.err.println("jni: script bridge: " + (jniMethods.size() - before) + " static methods callable from scripts");
    }
    for (String in : new LinkedHashSet<>(jniClasses.values())) {
      Link.CI c = L.world.get(in);
      jniArrays.add(in);
      if (c.concrete() && L.isSubclass(in, "java/lang/Throwable")) jniThrows.add(in);
    }
  }
  List<Link.CI> hierarchy(Link.CI c) {
    List<Link.CI> out = new ArrayList<>();
    Set<String> seen = new HashSet<>();
    ArrayDeque<Link.CI> q = new ArrayDeque<>();
    q.add(c);
    while (!q.isEmpty()) {
      Link.CI k = q.poll();
      if (k == null || !seen.add(k.name)) continue;
      out.add(k);
      if (k.sup != null && L.world.get(k.sup) != null) q.add(L.world.get(k.sup));
      for (String i : k.itfs) if (L.world.get(i) != null) q.add(L.world.get(i));
    }
    return out;
  }
  void addMembersStrict(Link.CI k, Set<String> names, Set<String> sigStrings) {
    for (String md : k.methods.keySet()) {
      int p = md.indexOf('(');
      String n = md.substring(0, p), d = md.substring(p);
      if (!names.contains(n) || !sigStrings.contains(d) || n.equals("<clinit>")) continue;
      if (n.equals("<init>") && !k.concrete()) continue;   // NewObject needs a class it can instantiate
      int acc = k.methods.get(md);
      if (k.origin != Link.APP && (acc & (Opcodes.ACC_PUBLIC | Opcodes.ACC_PROTECTED)) == 0) continue;
      if ((acc & Opcodes.ACC_ABSTRACT) != 0) continue;
      String id = k.name + "." + md;
      Integer i = jniIds.get(id);
      if (i == null) {
        jniMethods.add(new String[] {k.name, n, d, (acc & Opcodes.ACC_STATIC) != 0 ? "1" : "0", (acc & Opcodes.ACC_PRIVATE) != 0 ? "1" : "0", k.isInterface() ? "1" : "0"});
        i = jniMethods.size();
        jniIds.put(id, i);
      }
      jniKeys.putIfAbsent(k.name + "." + md, i);
    }
    for (Map.Entry<String, String> f : k.fields.entrySet()) {
      String jname = L.fieldOrigName.getOrDefault(k.name + "." + f.getKey(), f.getKey());
      if (!names.contains(jname) || !soStrings.contains(f.getValue())) continue;
      int acc = k.fieldAcc.getOrDefault(f.getKey(), 0);
      if (k.origin != Link.APP && (acc & (Opcodes.ACC_PUBLIC | Opcodes.ACC_PROTECTED)) == 0) continue;
      String id = k.name + "." + f.getKey() + ":" + f.getValue();
      Integer i = jniIds.get(id);
      if (i == null) {
        jniFields.add(new String[] {k.name, f.getKey(), f.getValue(), (acc & Opcodes.ACC_STATIC) != 0 ? "1" : "0"});
        i = jniFields.size();
        jniIds.put(id, i);
      }
      jniKeys.putIfAbsent(k.name + "." + jname + ":" + f.getValue(), i);
    }
  }
  void addMembers(String keyCls, Link.CI k, boolean own, Set<String> names, Set<String> sigStrings) {
    Map<String, List<String>> byName = new HashMap<>();
    for (String md : k.methods.keySet()) {
      int p = md.indexOf('(');
      String n = md.substring(0, p);
      if (!names.contains(n) || n.equals("<clinit>") || n.equals("<init>") && (!own || !k.concrete())) continue;
      byName.computeIfAbsent(n, x -> new ArrayList<>()).add(md.substring(p));
    }
    for (Map.Entry<String, List<String>> e : byName.entrySet()) {
      boolean anySig = false;
      for (String d : e.getValue()) if (sigStrings.contains(d)) anySig = true;
      for (String d : e.getValue()) {
        if (anySig && !sigStrings.contains(d)) continue;
        int acc = k.methods.get(e.getKey() + d);
        if (k.origin == Link.JDK && (acc & (Opcodes.ACC_PUBLIC | Opcodes.ACC_PROTECTED)) == 0) continue;
        String id = k.name + "." + e.getKey() + d;
        Integer n = jniIds.get(id);
        if (n == null) {
          jniMethods.add(new String[] {k.name, e.getKey(), d, (acc & Opcodes.ACC_STATIC) != 0 ? "1" : "0", (acc & Opcodes.ACC_PRIVATE) != 0 ? "1" : "0", k.isInterface() ? "1" : "0"});
          n = jniMethods.size();
          jniIds.put(id, n);
        }
        jniKeys.putIfAbsent(keyCls + "." + e.getKey() + d, n);
      }
    }
    for (Map.Entry<String, String> f : k.fields.entrySet()) {
      String jname = L.fieldOrigName.getOrDefault(k.name + "." + f.getKey(), f.getKey());   // the name native code uses
      if (!names.contains(jname)) continue;
      int acc = k.fieldAcc.getOrDefault(f.getKey(), 0);
      String id = k.name + "." + f.getKey() + ":" + f.getValue();
      Integer n = jniIds.get(id);
      if (n == null) {
        jniFields.add(new String[] {k.name, f.getKey(), f.getValue(), (acc & Opcodes.ACC_STATIC) != 0 ? "1" : "0"});
        n = jniFields.size();
        jniIds.put(id, n);
      }
      jniKeys.putIfAbsent(keyCls + "." + jname + ":" + f.getValue(), n);
    }
  }
  static void readStrings(byte[] b, Set<String> out) {
    int start = 0;
    for (int i = 0; i < b.length; i++) {
      if (b[i] != 0) continue;
      int len = i - start;
      if (len > 0 && len < 512) {
        boolean ok = true;
        for (int j = start; j < i; j++) { int ch = b[j] & 0xff; if (ch < 0x20 || ch > 0x7e) { ok = false; break; } }
        if (ok) out.add(new String(b, start, len, java.nio.charset.StandardCharsets.US_ASCII));
      }
      start = i + 1;
    }
  }

  ClassNode jniClass() {
    ClassNode cn = Link.newClass("a2w/gen/JniTable", "java/lang/Object", Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL);
    List<Consumer<InsnList>> reg = new ArrayList<>();
    for (Map.Entry<String, String> e : jniClasses.entrySet()) reg.add(il -> {
      il.add(new LdcInsnNode(e.getKey()));
      il.add(new LdcInsnNode(Type.getObjectType(e.getValue())));
      il.add(Link.push(jniArrays.indexOf(e.getValue()) + 1));
      il.add(Link.push(jniThrows.indexOf(e.getValue()) + 1));
      il.add(call("a2w/Jni", "regClass", "(Ljava/lang/String;Ljava/lang/Class;II)V"));
    });
    reg.add(il -> {
      il.add(Link.push(jniMethods.size()));
      il.add(Link.push(jniFields.size()));
      il.add(call("a2w/Jni", "regMemberCounts", "(II)V"));
    });
    for (String d : jniDropped) reg.add(il -> {
      il.add(new LdcInsnNode(d));
      il.add(call("a2w/Jni", "regDroppedClass", "(Ljava/lang/String;)V"));
    });
    for (Map.Entry<String, Integer> e : jniKeys.entrySet()) reg.add(il -> {
      il.add(new LdcInsnNode(e.getKey()));
      il.add(Link.push(e.getValue()));
      il.add(call("a2w/Jni", "regMember", "(Ljava/lang/String;I)V"));
    });
    registerMethods(cn, "register", reg);

    // invoke(int id, Object target, int[] a) long: methods by id, constructors return the new object's handle
    List<Consumer<InsnList>> cases = new ArrayList<>();
    for (String[] m : jniMethods) cases.add(il -> {
      boolean ctor = m[1].equals("<init>"), st = m[3].equals("1"), priv = m[4].equals("1"), itf = m[5].equals("1");
      if (ctor) { il.add(new TypeInsnNode(Opcodes.NEW, m[0])); il.add(new InsnNode(Opcodes.DUP)); }
      else if (!st) { il.add(new VarInsnNode(Opcodes.ALOAD, 1)); il.add(new TypeInsnNode(Opcodes.CHECKCAST, m[0])); }
      int w = 0;
      for (Type t : Type.getArgumentTypes(m[2])) { slotArg(il, 2, w, t); w += t.getSize(); }
      int op = ctor ? Opcodes.INVOKESPECIAL : st ? Opcodes.INVOKESTATIC : priv ? Opcodes.INVOKESPECIAL : itf ? Opcodes.INVOKEINTERFACE : Opcodes.INVOKEVIRTUAL;   // private static: INVOKESTATIC
      il.add(new MethodInsnNode(op, m[0], m[1], m[2], itf));
      toRaw(il, ctor ? Type.getObjectType(m[0]) : Type.getReturnType(m[2]));
      il.add(new InsnNode(Opcodes.LRETURN));
    });
    dispatch(cn, "invoke", "(ILjava/lang/Object;[I)J", cases, il -> { il.add(new InsnNode(Opcodes.LCONST_0)); il.add(new InsnNode(Opcodes.LRETURN)); });
    // getField(int id, Object target) long
    cases = new ArrayList<>();
    for (String[] f : jniFields) cases.add(il -> {
      boolean st = f[3].equals("1");
      if (!st) { il.add(new VarInsnNode(Opcodes.ALOAD, 1)); il.add(new TypeInsnNode(Opcodes.CHECKCAST, f[0])); }
      il.add(new FieldInsnNode(st ? Opcodes.GETSTATIC : Opcodes.GETFIELD, f[0], f[1], f[2]));
      toRaw(il, Type.getType(f[2]));
      il.add(new InsnNode(Opcodes.LRETURN));
    });
    dispatch(cn, "getField", "(ILjava/lang/Object;)J", cases, il -> { il.add(new InsnNode(Opcodes.LCONST_0)); il.add(new InsnNode(Opcodes.LRETURN)); });
    // setField(int id, Object target, long v)
    cases = new ArrayList<>();
    for (String[] f : jniFields) cases.add(il -> {
      boolean st = f[3].equals("1");
      if (!st) { il.add(new VarInsnNode(Opcodes.ALOAD, 1)); il.add(new TypeInsnNode(Opcodes.CHECKCAST, f[0])); }
      il.add(new VarInsnNode(Opcodes.LLOAD, 2));
      fromRaw(il, Type.getType(f[2]));
      il.add(new FieldInsnNode(st ? Opcodes.PUTSTATIC : Opcodes.PUTFIELD, f[0], f[1], f[2]));
      il.add(new InsnNode(Opcodes.RETURN));
    });
    dispatch(cn, "setField", "(ILjava/lang/Object;J)V", cases, il -> il.add(new InsnNode(Opcodes.RETURN)));
    // newArray(int id, int len) Object[]
    cases = new ArrayList<>();
    for (String a : jniArrays) cases.add(il -> {
      il.add(new VarInsnNode(Opcodes.ILOAD, 1));
      il.add(new TypeInsnNode(Opcodes.ANEWARRAY, a));
      il.add(new InsnNode(Opcodes.ARETURN));
    });
    dispatch(cn, "newArray", "(II)[Ljava/lang/Object;", cases, il -> {
      il.add(new VarInsnNode(Opcodes.ILOAD, 1)); il.add(new TypeInsnNode(Opcodes.ANEWARRAY, "java/lang/Object")); il.add(new InsnNode(Opcodes.ARETURN));
    });
    // newThrowable(int id, String msg) Throwable
    cases = new ArrayList<>();
    for (String t : jniThrows) cases.add(il -> {
      Link.CI c = L.world.get(t);
      boolean withMsg = c.methods.containsKey("<init>(Ljava/lang/String;)V");
      if (!withMsg && !c.methods.containsKey("<init>()V")) { il.add(new InsnNode(Opcodes.ACONST_NULL)); il.add(new InsnNode(Opcodes.ARETURN)); return; }
      il.add(new TypeInsnNode(Opcodes.NEW, t));
      il.add(new InsnNode(Opcodes.DUP));
      if (withMsg) il.add(new VarInsnNode(Opcodes.ALOAD, 1));
      il.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, t, "<init>", withMsg ? "(Ljava/lang/String;)V" : "()V", false));
      il.add(new InsnNode(Opcodes.ARETURN));
    });
    dispatch(cn, "newThrowable", "(ILjava/lang/String;)Ljava/lang/Throwable;", cases, il -> { il.add(new InsnNode(Opcodes.ACONST_NULL)); il.add(new InsnNode(Opcodes.ARETURN)); });
    return cn;
  }

  // ================================================================ code helpers
  static MethodInsnNode call(String owner, String name, String desc) { return new MethodInsnNode(Opcodes.INVOKESTATIC, owner, name, desc, false); }

  /** chunk methods go to companion classes (cn$0, cn$1, ...), a few per class, so no class outgrows its constant pool */
  static final int CHUNKS_PER_CLASS = 4;
  final Map<String, ClassNode> spill = new HashMap<>();
  final Map<String, Integer> spillCount = new HashMap<>();
  /** adds the (package-visible static) chunk method m to a companion class of cn; returns that class's name */
  String placeChunk(ClassNode cn, MethodNode m) {
    int n = spillCount.merge(cn.name, 1, Integer::sum) - 1;
    String owner = cn.name + "$" + (n / CHUNKS_PER_CLASS);
    ClassNode c = spill.get(owner);
    if (c == null) {
      c = Link.newClass(owner, "java/lang/Object", Opcodes.ACC_FINAL);
      spill.put(owner, c);
      extraClasses.add(c);
    }
    m.access = Opcodes.ACC_STATIC;
    c.methods.add(m);
    return owner;
  }

  /** register() calling register$0..n, each a chunk of statements */
  void registerMethods(ClassNode cn, String name, List<Consumer<InsnList>> stmts) {
    MethodNode top = new MethodNode(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, name, "()V", null, null);
    for (int k = 0; k * CHUNK < stmts.size(); k++) {
      MethodNode m = new MethodNode(Opcodes.ACC_STATIC, name + "$" + k, "()V", null, null);
      for (int i = k * CHUNK; i < Math.min(stmts.size(), (k + 1) * CHUNK); i++) stmts.get(i).accept(m.instructions);
      m.instructions.add(new InsnNode(Opcodes.RETURN));
      top.instructions.add(call(placeChunk(cn, m), m.name, "()V"));
    }
    top.instructions.add(new InsnNode(Opcodes.RETURN));
    cn.methods.add(top);
  }

  /** static method switching on its first (int) argument over ids 1..n, split in chunks so no method gets too big */
  void dispatch(ClassNode cn, String name, String desc, List<Consumer<InsnList>> cases, Consumer<InsnList> dflt) {
    if (cases.size() <= CHUNK) { cn.methods.add(switchMethod(name, desc, Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, cases, 1, dflt)); return; }
    MethodNode top = new MethodNode(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, name, desc, null, null);
    int chunks = (cases.size() + CHUNK - 1) / CHUNK;
    InsnList il = top.instructions;
    LabelNode[] labels = new LabelNode[chunks];
    LabelNode def = new LabelNode();
    for (int k = 0; k < chunks; k++) labels[k] = new LabelNode();
    il.add(new VarInsnNode(Opcodes.ILOAD, 0));
    il.add(Link.push(1)); il.add(new InsnNode(Opcodes.ISUB));
    il.add(Link.push(CHUNK)); il.add(new InsnNode(Opcodes.IDIV));
    il.add(new TableSwitchInsnNode(0, chunks - 1, def, labels));
    Type[] args = Type.getArgumentTypes(desc);
    for (int k = 0; k < chunks; k++) {
      il.add(labels[k]);
      int slot = 0;
      for (Type t : args) { il.add(new VarInsnNode(t.getOpcode(Opcodes.ILOAD), slot)); slot += t.getSize(); }
      MethodNode part = switchMethod(name + "$" + k, desc, Opcodes.ACC_STATIC,
          cases.subList(k * CHUNK, Math.min(cases.size(), (k + 1) * CHUNK)), k * CHUNK + 1, dflt);
      il.add(call(placeChunk(cn, part), part.name, desc));
      il.add(new InsnNode(Type.getReturnType(desc).getOpcode(Opcodes.IRETURN)));
    }
    il.add(def);
    dflt.accept(il);
    cn.methods.add(top);
  }
  static MethodNode switchMethod(String name, String desc, int acc, List<Consumer<InsnList>> cases, int first, Consumer<InsnList> dflt) {
    MethodNode m = new MethodNode(acc, name, desc, null, null);
    InsnList il = m.instructions;
    LabelNode def = new LabelNode();
    if (!cases.isEmpty()) {
      LabelNode[] labels = new LabelNode[cases.size()];
      for (int i = 0; i < labels.length; i++) labels[i] = new LabelNode();
      il.add(new VarInsnNode(Opcodes.ILOAD, 0));
      il.add(new TableSwitchInsnNode(first, first + cases.size() - 1, def, labels));
      for (int i = 0; i < labels.length; i++) { il.add(labels[i]); cases.get(i).accept(il); }
    }
    il.add(def);
    dflt.accept(il);
    return m;
  }

  static void pushClass(InsnList il, Type t) {
    String box = boxName(t);
    if (box != null) il.add(new FieldInsnNode(Opcodes.GETSTATIC, box, "TYPE", "Ljava/lang/Class;"));
    else il.add(new LdcInsnNode(t));
  }
  static void pushClassArray(InsnList il, Type[] ts) {
    il.add(Link.push(ts.length));
    il.add(new TypeInsnNode(Opcodes.ANEWARRAY, "java/lang/Class"));
    for (int i = 0; i < ts.length; i++) {
      il.add(new InsnNode(Opcodes.DUP));
      il.add(Link.push(i));
      pushClass(il, ts[i]);
      il.add(new InsnNode(Opcodes.AASTORE));
    }
  }
  static String boxName(Type t) {
    switch (t.getSort()) {
      case Type.BOOLEAN: return "java/lang/Boolean";
      case Type.BYTE: return "java/lang/Byte";
      case Type.CHAR: return "java/lang/Character";
      case Type.SHORT: return "java/lang/Short";
      case Type.INT: return "java/lang/Integer";
      case Type.LONG: return "java/lang/Long";
      case Type.FLOAT: return "java/lang/Float";
      case Type.DOUBLE: return "java/lang/Double";
      default: return null;
    }
  }
  /** args[i] (Object[] in local slot) as a value of type t */
  static void unboxArg(InsnList il, int arraySlot, int i, Type t) {
    il.add(new VarInsnNode(Opcodes.ALOAD, arraySlot));
    il.add(Link.push(i));
    il.add(new InsnNode(Opcodes.AALOAD));
    String box = boxName(t);
    if (box == null) { il.add(new TypeInsnNode(Opcodes.CHECKCAST, t.getSort() == Type.ARRAY ? t.getDescriptor() : t.getInternalName())); return; }
    il.add(new TypeInsnNode(Opcodes.CHECKCAST, box));
    il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, box, t.getClassName() + "Value", "()" + t.getDescriptor(), false));
  }
  static void box(InsnList il, Type t) {
    if (t.getSort() == Type.VOID) { il.add(new InsnNode(Opcodes.ACONST_NULL)); return; }
    String b = boxName(t);
    if (b != null) il.add(new MethodInsnNode(Opcodes.INVOKESTATIC, b, "valueOf", "(" + t.getDescriptor() + ")L" + b + ";", false));
  }
  /** JNI argument word w of the int[] in local slot as a value of type t */
  static void slotArg(InsnList il, int arraySlot, int w, Type t) {
    il.add(new VarInsnNode(Opcodes.ALOAD, arraySlot));
    il.add(Link.push(w));
    switch (t.getSort()) {
      case Type.BOOLEAN: case Type.INT: il.add(new InsnNode(Opcodes.IALOAD)); break;
      case Type.BYTE: il.add(new InsnNode(Opcodes.IALOAD)); il.add(new InsnNode(Opcodes.I2B)); break;
      case Type.CHAR: il.add(new InsnNode(Opcodes.IALOAD)); il.add(new InsnNode(Opcodes.I2C)); break;
      case Type.SHORT: il.add(new InsnNode(Opcodes.IALOAD)); il.add(new InsnNode(Opcodes.I2S)); break;
      case Type.LONG: il.add(call("a2w/Jni", "j", "([II)J")); break;
      case Type.FLOAT: il.add(call("a2w/Jni", "f", "([II)F")); break;
      case Type.DOUBLE: il.add(call("a2w/Jni", "d", "([II)D")); break;
      default:
        il.add(call("a2w/Jni", "o", "([II)Ljava/lang/Object;"));
        il.add(new TypeInsnNode(Opcodes.CHECKCAST, t.getSort() == Type.ARRAY ? t.getDescriptor() : t.getInternalName()));
    }
  }
  /** value of type t on the stack -> raw 64-bit JNI value (objects become local handles) */
  static void toRaw(InsnList il, Type t) {
    switch (t.getSort()) {
      case Type.VOID: il.add(new InsnNode(Opcodes.LCONST_0)); break;
      case Type.LONG: break;
      case Type.FLOAT: il.add(call("java/lang/Float", "floatToRawIntBits", "(F)I")); il.add(new InsnNode(Opcodes.I2L)); break;
      case Type.DOUBLE: il.add(call("java/lang/Double", "doubleToRawLongBits", "(D)J")); break;
      case Type.OBJECT: case Type.ARRAY: il.add(call("a2w/Jni", "handle", "(Ljava/lang/Object;)I")); il.add(new InsnNode(Opcodes.I2L)); break;
      default: il.add(new InsnNode(Opcodes.I2L));
    }
  }
  static void fromRaw(InsnList il, Type t) {
    switch (t.getSort()) {
      case Type.LONG: break;
      case Type.FLOAT: il.add(new InsnNode(Opcodes.L2I)); il.add(call("java/lang/Float", "intBitsToFloat", "(I)F")); break;
      case Type.DOUBLE: il.add(call("java/lang/Double", "longBitsToDouble", "(J)D")); break;
      case Type.OBJECT: case Type.ARRAY:
        il.add(new InsnNode(Opcodes.L2I));
        il.add(call("a2w/Jni", "get", "(I)Ljava/lang/Object;"));
        il.add(new TypeInsnNode(Opcodes.CHECKCAST, t.getSort() == Type.ARRAY ? t.getDescriptor() : t.getInternalName()));
        break;
      case Type.BYTE: il.add(new InsnNode(Opcodes.L2I)); il.add(new InsnNode(Opcodes.I2B)); break;
      case Type.CHAR: il.add(new InsnNode(Opcodes.L2I)); il.add(new InsnNode(Opcodes.I2C)); break;
      case Type.SHORT: il.add(new InsnNode(Opcodes.L2I)); il.add(new InsnNode(Opcodes.I2S)); break;
      default: il.add(new InsnNode(Opcodes.L2I));
    }
  }

  // ================================================================ report
  void summary() {
    System.out.printf("reflection: %d classes, %d constructors (%d shapes), %d methods; jni: %d classes, %d methods, %d fields%n",
        regNames.size(), ctors.size(), sigs.size(), methods.size(), jniClasses.size(), jniMethods.size(), jniFields.size());
  }
  String report() {
    StringBuilder b = new StringBuilder("\n## reflection: classes by name\n");
    for (Map.Entry<String, String> e : regNames.entrySet()) b.append(e.getKey()).append('\n');
    b.append("\n## reflection: constructors\n");
    for (String[] c : ctors) b.append(c[0]).append(c[1]).append('\n');
    b.append("\n## reflection: methods\n");
    for (String[] m : methods) b.append(m[0]).append('.').append(m[1]).append(m[2]).append('\n');
    b.append("\n## JNI: classes\n");
    for (String c : jniClasses.keySet()) b.append(c).append('\n');
    b.append("\n## JNI: members\n");
    for (String k : jniKeys.keySet()) b.append(k).append('\n');
    return b.toString();
  }
}
