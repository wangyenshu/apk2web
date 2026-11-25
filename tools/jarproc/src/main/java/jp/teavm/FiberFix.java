package jp.teavm;

import java.util.ArrayList;
import java.util.List;
import org.teavm.model.BasicBlock;
import org.teavm.model.ClassHolder;
import org.teavm.model.ClassHolderTransformer;
import org.teavm.model.ClassHolderTransformerContext;
import org.teavm.model.Instruction;
import org.teavm.model.MethodHolder;
import org.teavm.model.MethodReference;
import org.teavm.model.Program;
import org.teavm.model.ValueType;
import org.teavm.model.FieldReference;
import org.teavm.model.MethodDescriptor;
import org.teavm.model.Variable;
import org.teavm.model.instructions.ExitInstruction;
import org.teavm.model.instructions.GetFieldInstruction;
import org.teavm.model.instructions.InvocationType;
import org.teavm.model.instructions.InvokeInstruction;
import org.teavm.model.instructions.PutFieldInstruction;

/**
 * A TeaVM compiler plugin (the linker puts it in all.jar, the TeaVM configuration names it) that fixes TeaVM's
 * coroutines (org.teavm.runtime.Fiber), which threads, monitors and every @Async method use:
 * <ul>
 * <li>Fiber saves a suspending call stack in arrays it grows with Arrays.copyOf. Once a static initializer can suspend,
 * so can class initialization and with it Arrays.copyOf(Object[]); saving the stack then recurses until the
 * JavaScript stack overflows. Fiber grows them with a2w.FiberSupport.grow, which cannot suspend.</li>
 * <li>Fiber.suspend marks the fiber as suspending before it runs the start of the asynchronous call (the callback half
 * of an @Async method: Object.wait's, monitor entry's, Thread.sleep's). That code is meant to run synchronously, but in
 * a large program TeaVM's analysis may find that something it calls can suspend (ArrayDeque.add, once some class
 * initializer can), and then each such call returns early as if suspended: Object.wait suspended without releasing
 * its monitor, and threads deadlocked. Fiber.suspend marks the fiber after the start instead (a callback completed
 * during the start is still noticed: AsyncCallbackImpl checks Fiber.current, not the state).</li>
 * <li>a2w.FiberSupport.setCurrent calls java.lang.Thread.setCurrentThread (package private in TeaVM's class library),
 * so the runtime can make a thread current.</li>
 * <li>TeaVM's charset table (Charset.forName) gets the usual aliases ("UTF8", "ASCII", "latin1"...) from
 * a2w.CharsetAliases.</li>
 * </ul>
 */
public class FiberFix implements ClassHolderTransformer {
  private static final ValueType OBJECTS = ValueType.arrayOf(ValueType.object("java.lang.Object"));
  private static final MethodReference COPY_OF = new MethodReference("java.util.Arrays", "copyOf", OBJECTS, ValueType.INTEGER, OBJECTS);
  private static final MethodReference GROW = new MethodReference("a2w.FiberSupport", "grow", OBJECTS, ValueType.INTEGER, OBJECTS);
  private static final ValueType THREAD = ValueType.object("java.lang.Thread");
  private static final MethodReference SET_CURRENT = new MethodReference("a2w.FiberSupport", "setCurrent", THREAD, ValueType.VOID);
  private static final MethodReference SET_CURRENT_THREAD = new MethodReference("java.lang.Thread", "setCurrentThread", THREAD, ValueType.VOID);

  @Override
  public void transformClass(ClassHolder cls, ClassHolderTransformerContext context) {
    if (cls.getName().equals("org.teavm.runtime.Fiber")) {
      redirect(cls, COPY_OF, GROW);
      boolean fixed = false;
      for (MethodHolder m : cls.getMethods())
        if (m.getName().equals("suspend") && m.getProgram() != null) fixed |= markAfterStart(m.getProgram());
      if (!fixed) System.err.println("apk2web: warning: Fiber.suspend has an unexpected shape (left as it is)");
    } else if (cls.getName().equals("a2w.FiberSupport")) {
      redirect(cls, SET_CURRENT, SET_CURRENT_THREAD);
    } else if (cls.getName().equals("java.nio.charset.Charset$Charsets")) {
      MethodHolder clinit = cls.getMethod(new MethodDescriptor("<clinit>", ValueType.VOID));
      Program p = clinit == null ? null : clinit.getProgram();
      if (p == null) return;
      ValueType map = ValueType.object("java.util.Map");
      for (BasicBlock b : p.getBasicBlocks()) {
        Instruction last = b.getLastInstruction();
        if (!(last instanceof ExitInstruction)) continue;
        Variable v = p.createVariable();
        GetFieldInstruction get = new GetFieldInstruction();
        get.setField(new FieldReference(cls.getName(), "value"));
        get.setFieldType(map);
        get.setReceiver(v);
        InvokeInstruction call = new InvokeInstruction();
        call.setType(InvocationType.SPECIAL);
        call.setMethod(new MethodReference("a2w.CharsetAliases", "register", map, ValueType.VOID));
        call.setArguments(v);
        last.insertPrevious(get);
        last.insertPrevious(call);
      }
    }
  }

  private static void redirect(ClassHolder cls, MethodReference from, MethodReference to) {
    for (MethodHolder m : cls.getMethods()) {
      Program p = m.getProgram();
      if (p == null) continue;
      for (BasicBlock b : p.getBasicBlocks())
        for (Instruction insn : b)
          if (insn instanceof InvokeInstruction inv && from.equals(inv.getMethod())) inv.setMethod(to);
    }
  }

  /** moves "state = SUSPENDING" after the AsyncCall.run call that follows it in the same block */
  private static boolean markAfterStart(Program p) {
    for (BasicBlock b : p.getBasicBlocks()) {
      List<Instruction> insns = new ArrayList<>();
      for (Instruction insn : b) insns.add(insn);
      for (int i = 0; i < insns.size(); i++) {
        if (!(insns.get(i) instanceof PutFieldInstruction put) || !put.getField().getFieldName().equals("state")) continue;
        for (int j = i + 1; j < insns.size(); j++) {
          if (insns.get(j) instanceof InvokeInstruction inv && inv.getMethod().getName().equals("run")
              && inv.getMethod().getClassName().equals("org.teavm.runtime.Fiber$AsyncCall")) {
            put.delete();
            inv.insertNext(put);
            return true;
          }
        }
      }
    }
    return false;
  }
}
