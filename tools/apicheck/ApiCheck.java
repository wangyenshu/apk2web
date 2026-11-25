import org.objectweb.asm.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.zip.*;
/**
 * Lists framework methods whose return type differs from android.jar's for the same name and parameters: app code
 * calls them with android.jar's descriptor, so such a method is missing for the app (the linker stubs it).
 * usage: ApiCheck android.jar compiled-framework-dir   (tools/apicheck.sh does both steps)
 */
public class ApiCheck {
  static Map<String, Set<String>> methods(InputStream in) throws IOException {
    Map<String, Set<String>> m = new HashMap<>();
    new ClassReader(in).accept(new ClassVisitor(Opcodes.ASM9) {
      @Override public MethodVisitor visitMethod(int acc, String name, String desc, String sig, String[] ex) {
        if ((acc & (Opcodes.ACC_PUBLIC | Opcodes.ACC_PROTECTED)) != 0 && (acc & Opcodes.ACC_SYNTHETIC) == 0) {
          String key = name + desc.substring(0, desc.indexOf(')') + 1);
          m.computeIfAbsent(key, k -> new HashSet<>()).add(desc.substring(desc.indexOf(')') + 1));
        }
        return null;
      }
    }, ClassReader.SKIP_CODE);
    return m;
  }
  public static void main(String[] a) throws Exception {
    Path fw = Paths.get(a[1]);
    int n = 0;
    try (ZipFile z = new ZipFile(a[0])) {
      for (Enumeration<? extends ZipEntry> e = z.entries(); e.hasMoreElements(); ) {
        ZipEntry ze = e.nextElement();
        if (!ze.getName().endsWith(".class") || !ze.getName().startsWith("android/")) continue;
        Path f = fw.resolve(ze.getName());
        if (!Files.exists(f)) continue;
        Map<String, Set<String>> want = methods(z.getInputStream(ze)), have;
        try (InputStream in = Files.newInputStream(f)) { have = methods(in); }
        // all framework methods incl. bridges
        Map<String, Set<String>> haveAll = new HashMap<>();
        try (InputStream in = Files.newInputStream(f)) {
          new ClassReader(in).accept(new ClassVisitor(Opcodes.ASM9) {
            @Override public MethodVisitor visitMethod(int acc, String name, String desc, String sig, String[] ex) {
              haveAll.computeIfAbsent(name + desc.substring(0, desc.indexOf(')') + 1), k -> new HashSet<>()).add(desc.substring(desc.indexOf(')') + 1));
              return null;
            }
          }, ClassReader.SKIP_CODE);
        }
        for (Map.Entry<String, Set<String>> w : want.entrySet()) {
          Set<String> h = haveAll.get(w.getKey());
          if (h == null) continue;
          for (String r : w.getValue()) if (!h.contains(r)) { System.out.println(ze.getName().replace(".class", "") + "." + w.getKey() + r + "   (framework: " + h + ")"); n++; }
        }
      }
    }
    System.err.println(n + " mismatches");
  }
}
