package jp;

import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;
import java.io.*;
import java.nio.file.*;
import java.util.zip.*;

/**
 * A fix for TeaVM 0.16's wasm-gc backend, applied to a copy of teavm-core (installed into the local Maven repository
 * as version 0.16.0-a2w1, which runtime/pom.template.xml uses for the TeaVM plugin).
 *
 * WasmGCVirtualTableBuilder.Table.merge(other) makes other refer to this table. When it lays out an interface that
 * extends two interfaces, setUpInterfaceInHierarchy merges their tables level by level; if one of them was merged into
 * java.lang.Object (an interface the program reaches no method of, whose implementors have only Object in common),
 * Object's table ends up referring to the other interface's, Object gets no virtual table, and code generation fails
 * with a NullPointerException (WasmGetGlobal). The fix: an interface's table never becomes what a class's table refers
 * to; merging an interface table with a class table makes the interface refer to the class.
 *
 * WasmGCSupertypeFunctionGenerator.generateIsClass tests a class by its tag ranges; an interface that no class of the
 * program implements has none, so its test is a constant false and even an empty I[] made by Array.newInstance(I) fails
 * a cast to I[] (the array test compares item types with it). The fix: with no ranges the test is identity with the
 * class itself.
 *
 * Usage: TeaVMPatch teavm-core-0.16.0.jar out.jar
 */
public final class TeaVMPatch {
  static final String TABLE = "org/teavm/backend/wasm/vtable/WasmGCVirtualTableBuilder$Table";
  static final String SUPER = "org/teavm/backend/wasm/generate/classes/WasmGCSupertypeFunctionGenerator";

  public static void main(String[] a) throws IOException {
    int patched = 0;
    try (ZipInputStream in = new ZipInputStream(Files.newInputStream(Paths.get(a[0])));
         ZipOutputStream out = new ZipOutputStream(Files.newOutputStream(Paths.get(a[1])))) {
      for (ZipEntry e; (e = in.getNextEntry()) != null; ) {
        byte[] b = in.readAllBytes();
        if (e.getName().equals(TABLE + ".class")) { b = patch(b); patched++; }
        if (e.getName().equals(SUPER + ".class")) { b = patchIsClass(b); patched++; }
        out.putNextEntry(new ZipEntry(e.getName()));
        out.write(b);
        out.closeEntry();
      }
    }
    if (patched != 2) throw new IllegalStateException(TABLE + " or " + SUPER + " not found in " + a[0]);
  }

  static byte[] patch(byte[] b) {
    ClassNode cn = new ClassNode();
    new ClassReader(b).accept(cn, 0);
    MethodNode merge = null;
    for (MethodNode m : cn.methods) if (m.name.equals("merge") && m.desc.equals("(L" + TABLE + ";)V")) merge = m;
    if (merge == null) throw new IllegalStateException("merge(Table) not found");
    // if (this.cls is an interface && !(other.cls is an interface)) { this.reference = other; return; }  other.reference = this;
    InsnList p = new InsnList();
    LabelNode orig = new LabelNode();
    String cr = "org/teavm/model/ClassReader", mod = "org/teavm/model/ElementModifier";
    for (int v : new int[] {0, 1}) {
      p.add(new VarInsnNode(Opcodes.ALOAD, v));
      p.add(new FieldInsnNode(Opcodes.GETFIELD, TABLE, "cls", "L" + cr + ";"));
      p.add(new FieldInsnNode(Opcodes.GETSTATIC, mod, "INTERFACE", "L" + mod + ";"));
      p.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, cr, "hasModifier", "(L" + mod + ";)Z", true));
      p.add(new JumpInsnNode(v == 0 ? Opcodes.IFEQ : Opcodes.IFNE, orig));
    }
    p.add(new VarInsnNode(Opcodes.ALOAD, 0));
    p.add(new VarInsnNode(Opcodes.ALOAD, 1));
    p.add(new FieldInsnNode(Opcodes.PUTFIELD, TABLE, "reference", "L" + TABLE + ";"));
    p.add(new InsnNode(Opcodes.RETURN));
    p.add(orig);
    p.add(new FrameNode(Opcodes.F_SAME, 0, null, 0, null));
    merge.instructions.insert(p);
    merge.maxStack = Math.max(merge.maxStack, 2);
    ClassWriter cw = new ClassWriter(0);
    cn.accept(cw);
    return cw.toByteArray();
  }

  /** generateIsClass(subtype, className, function): "if (ranges.isEmpty()) { body.add(i32.const 0); return; }" becomes
   *  "{ body.builder().getLocal(subtype).getGlobal(classGenerator.getClassInfo(ValueType.object(className)).pointer).refEqual(); return; }" */
  static byte[] patchIsClass(byte[] b) {
    ClassNode cn = new ClassNode();
    new ClassReader(b).accept(cn, 0);
    MethodNode m = null;
    for (MethodNode x : cn.methods) if (x.name.equals("generateIsClass")) m = x;
    if (m == null) throw new IllegalStateException("generateIsClass not found");
    String w = "org/teavm/backend/wasm/model/", ins = w + "instruction/", gen = "org/teavm/backend/wasm/generate/classes/";
    AbstractInsnNode first = null, last = null;
    for (AbstractInsnNode n = m.instructions.getFirst(); n != null; n = n.getNext()) {
      if (n instanceof TypeInsnNode && ((TypeInsnNode) n).desc.equals(ins + "WasmInt32Constant") && first == null) {
        first = n.getPrevious().getPrevious();   // aload_3; invokevirtual getBody
        for (last = n; !(last instanceof MethodInsnNode && ((MethodInsnNode) last).name.equals("add")); ) last = last.getNext();
        break;
      }
    }
    if (first == null || !(first instanceof VarInsnNode) || ((VarInsnNode) first).var != 3) throw new IllegalStateException("generateIsClass: unexpected code");
    InsnList p = new InsnList();
    p.add(new VarInsnNode(Opcodes.ALOAD, 3));
    p.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, w + "WasmFunction", "getBody", "()L" + ins + "WasmInstructionList;", false));
    p.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, ins + "WasmInstructionList", "builder", "()L" + ins + "WasmInstructionBuilder;", false));
    p.add(new VarInsnNode(Opcodes.ALOAD, 1));
    p.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, ins + "WasmInstructionBuilder", "getLocal", "(L" + w + "WasmLocal;)L" + ins + "WasmInstructionBuilder;", false));
    p.add(new VarInsnNode(Opcodes.ALOAD, 0));
    p.add(new FieldInsnNode(Opcodes.GETFIELD, SUPER, "classGenerator", "L" + gen + "WasmGCClassGenerator;"));
    p.add(new VarInsnNode(Opcodes.ALOAD, 2));
    p.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "org/teavm/model/ValueType", "object", "(Ljava/lang/String;)Lorg/teavm/model/ValueType$Object;", false));
    p.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, gen + "WasmGCClassGenerator", "getClassInfo", "(Lorg/teavm/model/ValueType;)L" + gen + "WasmGCClassInfo;", false));
    p.add(new FieldInsnNode(Opcodes.GETFIELD, gen + "WasmGCClassInfo", "pointer", "L" + w + "WasmGlobal;"));
    p.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, ins + "WasmInstructionBuilder", "getGlobal", "(L" + w + "WasmGlobal;)L" + ins + "WasmInstructionBuilder;", false));
    p.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, ins + "WasmInstructionBuilder", "refEqual", "()L" + ins + "WasmInstructionBuilder;", false));
    p.add(new InsnNode(Opcodes.POP));
    m.instructions.insertBefore(first, p);
    for (AbstractInsnNode n = first, end = last.getNext(); n != end; ) { AbstractInsnNode nx = n.getNext(); m.instructions.remove(n); n = nx; }
    m.maxStack = Math.max(m.maxStack, 4);
    ClassWriter cw = new ClassWriter(0);
    cn.accept(cw);
    return cw.toByteArray();
  }
}
