package jp;

import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;
import java.io.*;
import java.nio.file.*;
import java.util.zip.*;

/**
 * Patches dex2jar's IR2JConverter (see tools/d2jfix/D2jFix.java): reBuildJumpInstructions first calls
 * a2wfix.D2jFix.normalize(IfStmt). usage: D2jPatch dex-translator.jar outDir
 */
public class D2jPatch {
  static final String CLS = "com/googlecode/d2j/converter/IR2JConverter";
  public static void main(String[] a) throws IOException {
    byte[] b = null;
    try (ZipFile z = new ZipFile(a[0])) {
      ZipEntry e = z.getEntry(CLS + ".class");
      if (e == null) throw new IOException(CLS + " is not in " + a[0]);
      try (InputStream in = z.getInputStream(e)) { b = in.readAllBytes(); }
    }
    ClassNode cn = new ClassNode();
    new ClassReader(b).accept(cn, 0);
    boolean done = false;
    for (MethodNode m : cn.methods) {
      if (!m.name.equals("reBuildJumpInstructions") || !m.desc.startsWith("(Lcom/googlecode/dex2jar/ir/stmt/IfStmt;")) continue;
      InsnList il = new InsnList();
      il.add(new VarInsnNode(Opcodes.ALOAD, 1));
      il.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "a2wfix/D2jFix", "normalize", "(Lcom/googlecode/dex2jar/ir/stmt/IfStmt;)V", false));
      m.instructions.insert(il);
      done = true;
    }
    if (!done) throw new IOException("reBuildJumpInstructions not found in " + CLS);
    ClassWriter cw = new ClassWriter(0);
    cn.accept(cw);
    Path out = Paths.get(a[1], CLS + ".class");
    Files.createDirectories(out.getParent());
    Files.write(out, cw.toByteArray());
    System.out.println("patched " + out);
  }
}
