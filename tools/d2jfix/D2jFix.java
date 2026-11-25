package a2wfix;

import com.googlecode.dex2jar.ir.expr.BinopExpr;
import com.googlecode.dex2jar.ir.expr.Constant;
import com.googlecode.dex2jar.ir.expr.Exprs;
import com.googlecode.dex2jar.ir.expr.Value;
import com.googlecode.dex2jar.ir.stmt.IfStmt;

/**
 * A fix for dex2jar (2.4.x): its IR2JConverter turns a comparison with zero on the left, "if (0 > x)" (R8 emits
 * these, for instance for Kotlin's Array(n) { ... }), into "if (x > 0)", with the operands swapped but the condition
 * not mirrored. tools/jarproc's D2jPatch makes reBuildJumpInstructions call normalize first, which puts the zero on
 * the right with the mirrored condition.
 */
public final class D2jFix {
  private D2jFix() {}
  static boolean zero(Value v) {
    if (v == null || v.vt != Value.VT.CONSTANT) return false;
    Object c = ((Constant) v).value;
    return Integer.valueOf(0).equals(c) || Constant.NULL.equals(c);
  }
  public static void normalize(IfStmt s) {
    Value op = s.op;
    if (!(op instanceof BinopExpr)) return;
    Value a = op.getOp1(), b = op.getOp2();
    if (!zero(a) || zero(b)) return;
    String t = ((BinopExpr) op).type;
    BinopExpr n;
    switch (op.vt) {
      case GT: n = Exprs.nLt(b, a, t); break;   // 0 > b  is  b < 0
      case GE: n = Exprs.nLe(b, a, t); break;
      case LT: n = Exprs.nGt(b, a, t); break;
      case LE: n = Exprs.nGe(b, a, t); break;
      case EQ: n = Exprs.nEq(b, a, t); break;
      case NE: n = Exprs.nNe(b, a, t); break;
      default: return;
    }
    n.valueType = op.valueType;
    s.op = n;
  }
}
