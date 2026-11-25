// Thumb-2 32-bit instructions (included by cpu.c after the ARM decoder's helpers).
// Reference: ARM Architecture Reference Manual ARMv7-A/R, A6.3 (32-bit Thumb instruction encoding).

// coprocessor space shared by ARM and Thumb (Thumb encodings converted to ARM ones first)
static int cop_exec(Cpu *c, uint32_t op, uint32_t pc_read) {
  int cp = (op >> 8) & 15;
  if ((cp & 0xE) == 0xA) return (op >> 28) != 15 && vfp_exec(c, op, pc_read);
  if (cp == 15 && (op & 0x0F000010) == 0x0E000010) {   // MRC / MCR p15
    int rt = (op >> 12) & 15, crn = (op >> 16) & 15, opc1 = (op >> 21) & 7, crm = op & 15, opc2 = (op >> 5) & 7;
    if (op & 0x00100000) {
      uint32_t v = cp15_read(c, crn, opc1, crm, opc2);
      if (rt == 15) { c->n = v >> 31; c->z = (v >> 30) & 1; c->c = (v >> 29) & 1; c->v = (v >> 28) & 1; }
      else c->r[rt] = v;
    } else cp15_write(c, crn, opc1, crm, opc2, c->r[rt]);
    return 1;
  }
  if (cp == 14 && (op & 0x0F100010) == 0x0E100010) {   // MRC p14 (debug ID registers): nothing to report
    int rt = (op >> 12) & 15;
    if (rt != 15) c->r[rt] = 0;
    return 1;
  }
  return 0;
}

// AND BIC ORR ORN EOR ADD ADC SBC SUB RSB with Thumb-2 numbering; -1 when the opcode is not one of these
static inline int t2_alu(Cpu *c, int opc, uint32_t a, uint32_t b, uint32_t carry, int s, uint32_t *res) {
  switch (opc) {
    case 0: *res = a & b; break;
    case 1: *res = a & ~b; break;
    case 2: *res = a | b; break;
    case 3: *res = a | ~b; break;
    case 4: *res = a ^ b; break;
    case 8: *res = s ? add_flags(c, a, b, 0) : a + b; return 1;
    case 10: *res = s ? add_flags(c, a, b, c->c) : a + b + c->c; return 1;
    case 11: *res = s ? sub_flags(c, a, b, c->c) : a - b - !c->c; return 1;
    case 13: *res = s ? sub_flags(c, a, b, 1) : a - b; return 1;
    case 14: *res = s ? sub_flags(c, b, a, 1) : b - a; return 1;
    default: return -1;
  }
  if (s) { SETNZ(*res); c->c = carry; }
  return 1;
}
static inline void t2_alu_write(Cpu *c, int opc, int s, int rd, uint32_t res) {
  // TST TEQ CMN CMP are the S forms with Rd = PC
  if (rd == 15 && s && (opc == 0 || opc == 4 || opc == 8 || opc == 13)) return;
  if (rd != 15) c->r[rd] = res;
}

static void thumb32_step(Cpu *c, uint32_t pc, uint32_t op) {
  uint32_t *r = c->r;
  uint32_t hw1 = op >> 16, hw2 = op & 0xFFFF, pcv = pc + 4;
  int op1 = (hw1 >> 11) & 3, op2 = (hw1 >> 4) & 0x7F;

  if (op1 == 1) {
    if ((op2 & 0x64) == 0x00) {   // load/store multiple
      int opc = (hw1 >> 7) & 3, w = (hw1 >> 5) & 1, l = (hw1 >> 4) & 1, rn = hw1 & 15;
      if (opc == 0 || opc == 3) goto undef;   // SRS / RFE
      uint32_t list = hw2, n = __builtin_popcount(list), base = r[rn];
      uint32_t addr = opc == 1 ? base : base - 4 * n, newbase = opc == 1 ? base + 4 * n : base - 4 * n;
      if (l) {
        if (w && !(list & (1u << rn))) r[rn] = newbase;
        uint32_t pcval = 0; int haspc = 0;
        for (int i = 0; i < 16; i++) {
          if (!(list & (1u << i))) continue;
          uint32_t v = R32(addr); addr += 4;
          if (i == 15) { pcval = v; haspc = 1; } else r[i] = v;
        }
        if (haspc) branch_x(c, pcval);
      } else {
        for (int i = 0; i < 16; i++) if (list & (1u << i)) { R32(addr) = r[i]; addr += 4; }
        if (w) r[rn] = newbase;
      }
      return;
    }
    if ((op2 & 0x64) == 0x04) {   // load/store dual, exclusive, table branch
      int p = (hw1 >> 8) & 1, u = (hw1 >> 7) & 1, w = (hw1 >> 5) & 1, l = (hw1 >> 4) & 1, rn = hw1 & 15;
      int rt = hw2 >> 12, rt2 = (hw2 >> 8) & 15;
      if (!p && !w) {
        if (!u) {   // LDREX / STREX (word, with offset)
          uint32_t addr = r[rn] + ((hw2 & 0xFF) << 2);
          if (l) r[rt] = ldrex(c, addr, 4);
          else if (strex_ok(c, addr)) { R32(addr) = r[rt]; r[rt2] = 0; }
          else r[rt2] = 1;
          return;
        }
        int op3 = (hw2 >> 4) & 15, rd = hw2 & 15;
        uint32_t addr = rn == 15 ? pcv : r[rn];
        if (l) {
          switch (op3) {
            case 0: r[15] = pcv + 2 * R8(addr + r[hw2 & 15]); return;          // TBB
            case 1: r[15] = pcv + 2 * R16(addr + 2 * r[hw2 & 15]); return;     // TBH
            case 4: r[rt] = ldrex(c, addr, 1); return;
            case 5: r[rt] = ldrex(c, addr, 2); return;
            case 7: c->excl = addr; r[rt] = R32(addr); r[rt2] = R32(addr + 4); return;
          }
          goto undef;
        }
        if (op3 != 4 && op3 != 5 && op3 != 7) goto undef;
        if (!strex_ok(c, addr)) { r[rd] = 1; return; }
        if (op3 == 4) R8(addr) = (uint8_t)r[rt];
        else if (op3 == 5) R16(addr) = (uint16_t)r[rt];
        else { R32(addr) = r[rt]; R32(addr + 4) = r[rt2]; }
        r[rd] = 0;
        return;
      }
      uint32_t imm = (hw2 & 0xFF) << 2, base = rn == 15 ? (pcv & ~3u) : r[rn];
      uint32_t off = u ? base + imm : base - imm, ea = p ? off : base;
      if (l) {
        uint32_t lo = R32(ea), hi = R32(ea + 4);
        if (w) r[rn] = off;
        r[rt] = lo; r[rt2] = hi;
      } else {
        R32(ea) = r[rt]; R32(ea + 4) = r[rt2];
        if (w) r[rn] = off;
      }
      return;
    }
    if ((op2 & 0x60) == 0x20) {   // data processing (shifted register)
      int opc = (hw1 >> 5) & 15, s = (hw1 >> 4) & 1, rn = hw1 & 15, rd = (hw2 >> 8) & 15, rm = hw2 & 15;
      uint32_t type = (hw2 >> 4) & 3, amt = ((hw2 >> 12) & 7) << 2 | ((hw2 >> 6) & 3);
      if (opc == 6) {   // PKHBT / PKHTB
        if (type & 2) {
          uint32_t v = amt ? (uint32_t)((int32_t)r[rm] >> amt) : (uint32_t)((int32_t)r[rm] >> 31);
          r[rd] = (r[rn] & 0xFFFF0000u) | (v & 0xFFFF);
        } else r[rd] = (r[rn] & 0xFFFF) | ((r[rm] << amt) & 0xFFFF0000u);
        return;
      }
      uint32_t carry, b = shift_imm(c, type, r[rm], amt, &carry), res;
      uint32_t a = (rn == 15 && (opc == 2 || opc == 3)) ? 0 : r[rn];
      if (t2_alu(c, opc, a, b, carry, s, &res) < 0) goto undef;
      t2_alu_write(c, opc, s, rd, res);
      return;
    }
    goto coproc;
  }

  if (op1 == 2) {
    if (hw2 & 0x8000) {   // branches and miscellaneous control
      int ob = (hw2 >> 12) & 7, om = (hw1 >> 4) & 0x7F;
      uint32_t s = (hw1 >> 10) & 1, j1 = (hw2 >> 13) & 1, j2 = (hw2 >> 11) & 1;
      if (!(ob & 5)) {
        if ((om & 0x38) != 0x38) {   // B<cond>.W
          int32_t off = (int32_t)((s << 20) | (j2 << 19) | (j1 << 18) | ((hw1 & 0x3F) << 12) | ((hw2 & 0x7FF) << 1));
          off = (off << 11) >> 11;
          if (cond_ok(c, (hw1 >> 6) & 15)) r[15] = pcv + off;
          return;
        }
        switch (om) {
          case 0x38: case 0x39: {   // MSR (register)
            uint32_t v = r[hw1 & 15];
            if (hw2 & 0x800) { c->n = v >> 31; c->z = (v >> 30) & 1; c->c = (v >> 29) & 1; c->v = (v >> 28) & 1; c->q = (v >> 27) & 1; }
            if (hw2 & 0x400) c->ge = (v >> 16) & 15;
            return;
          }
          case 0x3A: return;   // NOP YIELD WFE WFI SEV DBG, CPS
          case 0x3B:           // CLREX DSB DMB ISB
            if (((hw2 >> 4) & 15) == 2) c->excl = 1;
            return;
          case 0x3C: branch_x(c, r[hw1 & 15]); return;   // BXJ
          case 0x3E: case 0x3F: r[(hw2 >> 8) & 15] = cpsr(c); return;   // MRS
          case 0x7F:
            if (ob == 2) { run_hook(c, (int)(((hw1 & 15) << 12) | (hw2 & 0xFFF)), pc); return; }   // UDF.W: a hook
            break;
        }
        goto undef;
      }
      uint32_t i1 = !(j1 ^ s), i2 = !(j2 ^ s);
      int32_t off = (int32_t)((s << 24) | (i1 << 23) | (i2 << 22) | ((hw1 & 0x3FF) << 12) | ((hw2 & 0x7FF) << 1));
      off = (off << 7) >> 7;
      if (ob & 1) {   // B.W, BL
        if (ob & 4) r[14] = pcv | 1;
        r[15] = pcv + off;
      } else {        // BLX (immediate)
        r[14] = pcv | 1;
        c->t = 0;
        r[15] = (pcv & ~3u) + (off & ~3);
      }
      return;
    }
    int rn = hw1 & 15, rd = (hw2 >> 8) & 15;
    uint32_t imm12 = ((hw1 >> 10) & 1) << 11 | ((hw2 >> 12) & 7) << 8 | (hw2 & 0xFF);
    if (!(op2 & 0x20)) {   // data processing (modified immediate)
      int opc = (hw1 >> 5) & 15, s = (hw1 >> 4) & 1;
      uint32_t b, carry = c->c, res;
      if (!(imm12 >> 10)) {
        uint32_t imm8 = imm12 & 0xFF;
        switch ((imm12 >> 8) & 3) {
          case 0: b = imm8; break;
          case 1: b = imm8 * 0x00010001u; break;
          case 2: b = imm8 * 0x01000100u; break;
          default: b = imm8 * 0x01010101u; break;
        }
      } else { b = ror32(0x80 | (imm12 & 0x7F), imm12 >> 7); carry = b >> 31; }
      uint32_t a = (rn == 15 && (opc == 2 || opc == 3)) ? 0 : r[rn];
      if (t2_alu(c, opc, a, b, carry, s, &res) < 0) goto undef;
      t2_alu_write(c, opc, s, rd, res);
      return;
    }
    // data processing (plain binary immediate)
    int opc = (hw1 >> 4) & 31;
    uint32_t amt = ((hw2 >> 12) & 7) << 2 | ((hw2 >> 6) & 3), v = r[rn];
    switch (opc) {
      case 0: r[rd] = (rn == 15 ? (pcv & ~3u) : v) + imm12; return;    // ADDW / ADR
      case 10: r[rd] = (rn == 15 ? (pcv & ~3u) : v) - imm12; return;   // SUBW / ADR
      case 4: r[rd] = ((uint32_t)rn << 12) | imm12; return;              // MOVW
      case 12: r[rd] = (r[rd] & 0xFFFF) | ((((uint32_t)rn << 12) | imm12) << 16); return;   // MOVT
      case 16: case 18: case 24: case 26: {   // SSAT SSAT16 USAT USAT16
        int sh = (opc >> 1) & 1, uns = opc >= 24, sat = hw2 & 31;
        if (sh && !amt) {
          int bits = sat & 15;
          uint32_t lo, hi;
          if (uns) { lo = usat(c, (int16_t)v, bits); hi = usat(c, (int32_t)v >> 16, bits); }
          else { lo = ssat(c, (int16_t)v, bits + 1); hi = ssat(c, (int32_t)v >> 16, bits + 1); }
          r[rd] = (lo & 0xFFFF) | (hi << 16);
          return;
        }
        int32_t x = sh ? (int32_t)v >> amt : (int32_t)(v << amt);
        r[rd] = uns ? usat(c, x, sat) : ssat(c, x, sat + 1);
        return;
      }
      case 20: r[rd] = sbfx(v, amt, (hw2 & 31) + 1); return;            // SBFX
      case 28: r[rd] = ubfx(v, amt, (hw2 & 31) + 1); return;            // UBFX
      case 22: r[rd] = bfi(r[rd], rn == 15 ? 0 : v, amt, hw2 & 31); return;   // BFI / BFC
    }
    goto undef;
  }

  // op1 == 3
  if ((op2 & 0x71) == 0x00 || (op2 & 0x67) == 0x01 || (op2 & 0x67) == 0x03 || (op2 & 0x67) == 0x05) {   // load/store single
    int load = op2 & 1, size = (hw1 >> 5) & 3, sgn = (hw1 >> 8) & 1, rn = hw1 & 15, rt = hw2 >> 12, wb = 0;
    uint32_t ea, wbv = 0;
    if (size == 3) goto undef;
    if (load && rn == 15) {
      uint32_t base = pcv & ~3u, imm = hw2 & 0xFFF;
      ea = (hw1 & 0x80) ? base + imm : base - imm;
    } else if (hw1 & 0x80) ea = r[rn] + (hw2 & 0xFFF);
    else if (hw2 & 0x800) {
      int p = (hw2 >> 10) & 1, u = (hw2 >> 9) & 1;
      uint32_t imm = hw2 & 0xFF, base = r[rn], off = u ? base + imm : base - imm;
      ea = p ? off : base; wb = (hw2 >> 8) & 1; wbv = off;
      if (!p) wb = 1;
    } else {
      if (hw2 & 0x7C0) goto undef;
      ea = r[rn] + (r[hw2 & 15] << ((hw2 >> 4) & 3));
    }
    if (!load) {
      uint32_t v = r[rt];
      if (size == 0) R8(ea) = (uint8_t)v; else if (size == 1) R16(ea) = (uint16_t)v; else R32(ea) = v;
      if (wb) r[rn] = wbv;
      return;
    }
    if (rt == 15 && size < 2) return;   // PLD PLDW PLI and the unallocated hints
    uint32_t v;
    if (size == 0) v = sgn ? (uint32_t)(int32_t)S8(ea) : R8(ea);
    else if (size == 1) v = sgn ? (uint32_t)(int32_t)S16(ea) : R16(ea);
    else v = R32(ea);
    if (wb) r[rn] = wbv;
    if (rt == 15) branch_x(c, v); else r[rt] = v;
    return;
  }
  if ((op2 & 0x71) == 0x10) {   // Advanced SIMD element or structure load/store
    if (!neon_ls(c, (op & 0x00FFFFFF) | 0xF4000000u)) goto undef;
    return;
  }
  if ((op2 & 0x70) == 0x20) {   // data processing (register)
    int d1 = (hw1 >> 4) & 15, d2 = (hw2 >> 4) & 15, rn = hw1 & 15, rd = (hw2 >> 8) & 15, rm = hw2 & 15;
    if ((hw2 & 0xF000) != 0xF000) goto undef;
    if (d1 < 8 && d2 == 0) {   // LSL LSR ASR ROR (register)
      uint32_t carry, res = shift_reg(c, d1 >> 1, r[rn], r[rm], &carry);
      r[rd] = res;
      if (d1 & 1) { SETNZ(res); c->c = carry; }
      return;
    }
    if (d1 < 8 && (d2 & 8)) {   // SXTAH UXTAH SXTAB16 UXTAB16 SXTAB UXTAB (and the forms without add)
      static const int map[8] = {2, 6, 0, 4, 1, 5, -1, -1};
      int e = map[d1];
      if (e < 0) goto undef;
      r[rd] = do_extend(e, rn == 15 ? 0 : r[rn], ror32(r[rm], (d2 & 3) * 8), rn != 15);
      return;
    }
    if (d1 & 8) {
      if (d2 < 8) {   // parallel addition and subtraction
        static const int pmap[8] = {4, 0, 1, -1, 7, 3, 2, -1};
        int kind = d2 & 3, p = pmap[d1 & 7];
        if (kind == 3 || p < 0) goto undef;
        r[rd] = parallel(c, ((d2 & 4) ? 4 : 0) | (kind + 1), p, r[rn], r[rm]);
        return;
      }
      if ((d2 & 12) == 8 && (d1 & 12) == 8) {
        int m1 = d1 & 3, m2 = d2 & 3;
        uint32_t v = r[rm];
        switch (m1) {
          case 0: {   // QADD QDADD QSUB QDSUB
            int32_t b = (int32_t)r[rn];
            if (m2 & 1) b = sat32(c, (int64_t)b * 2);
            r[rd] = sat32(c, (m2 & 2) ? (int64_t)(int32_t)v - b : (int64_t)(int32_t)v + b);
            return;
          }
          case 1: r[rd] = m2 == 0 ? __builtin_bswap32(v) : m2 == 1 ? rev16(v) : m2 == 2 ? rbit32(v) : revsh(v); return;
          case 2: if (m2) goto undef; r[rd] = sel(c, r[rn], v); return;
          default: if (m2) goto undef; r[rd] = v ? __builtin_clz(v) : 32; return;
        }
      }
    }
    goto undef;
  }
  if ((op2 & 0x78) == 0x30) {   // multiply, multiply accumulate, absolute difference
    int m1 = (hw1 >> 4) & 7, m2 = (hw2 >> 4) & 3, rn = hw1 & 15, ra = hw2 >> 12, rd = (hw2 >> 8) & 15, rm = hw2 & 15;
    if (hw2 & 0xC0) goto undef;
    uint32_t acc = ra == 15 ? 0 : r[ra];
    switch (m1) {
      case 0:
        if (m2 == 0) { r[rd] = r[rn] * r[rm] + acc; return; }
        if (m2 == 1) { r[rd] = r[ra] - r[rn] * r[rm]; return; }
        goto undef;
      case 1: {   // SMLA<x><y> / SMUL<x><y>
        int32_t a = (m2 & 2) ? (int32_t)r[rn] >> 16 : (int16_t)r[rn], b = (m2 & 1) ? (int32_t)r[rm] >> 16 : (int16_t)r[rm];
        int64_t p = (int64_t)a * b + (int32_t)acc;
        if (p != (int32_t)p) c->q = 1;
        r[rd] = (uint32_t)p;
        return;
      }
      case 2: case 4: {   // SMLAD SMUAD SMLSD SMUSD
        if (m2 & 2) goto undef;
        int64_t p = dual16(r[rn], r[rm], m2 & 1, m1 == 4) + (int32_t)acc;
        if (p != (int32_t)p) c->q = 1;
        r[rd] = (uint32_t)p;
        return;
      }
      case 3: {   // SMLAW<y> / SMULW<y>
        if (m2 & 2) goto undef;
        int32_t b = (m2 & 1) ? (int32_t)r[rm] >> 16 : (int16_t)r[rm];
        int64_t p = (((int64_t)(int32_t)r[rn] * b) >> 16) + (int32_t)acc;
        if (p != (int32_t)p) c->q = 1;
        r[rd] = (uint32_t)p;
        return;
      }
      case 5: case 6: {   // SMMLA SMMUL SMMLS
        if (m2 & 2) goto undef;
        int64_t prod = (int64_t)(int32_t)r[rn] * (int32_t)r[rm], p = (int64_t)((uint64_t)acc << 32);
        p = m1 == 5 ? p + prod : p - prod;
        if (m2 & 1) p += 0x80000000LL;
        r[rd] = (uint32_t)((uint64_t)p >> 32);
        return;
      }
      default:   // USAD8 / USADA8
        if (m2) goto undef;
        r[rd] = usad8(r[rn], r[rm]) + acc;
        return;
    }
  }
  if ((op2 & 0x78) == 0x38) {   // long multiply, long multiply accumulate, divide
    int l1 = (hw1 >> 4) & 7, l2 = (hw2 >> 4) & 15, rn = hw1 & 15, lo = hw2 >> 12, hi = (hw2 >> 8) & 15, rm = hw2 & 15;
    uint64_t acc = ((uint64_t)r[hi] << 32) | r[lo], res;
    switch (l1) {
      case 0: if (l2) goto undef; res = (uint64_t)((int64_t)(int32_t)r[rn] * (int32_t)r[rm]); break;   // SMULL
      case 2: if (l2) goto undef; res = (uint64_t)r[rn] * r[rm]; break;                                 // UMULL
      case 1: case 3: {   // SDIV / UDIV
        if (l2 != 15) goto undef;
        uint32_t n = r[rn], m = r[rm];
        if (!m) r[hi] = 0;
        else if (l1 == 1) r[hi] = ((int32_t)n == INT32_MIN && (int32_t)m == -1) ? n : (uint32_t)((int32_t)n / (int32_t)m);
        else r[hi] = n / m;
        return;
      }
      case 4:
        if (l2 == 0) res = acc + (uint64_t)((int64_t)(int32_t)r[rn] * (int32_t)r[rm]);   // SMLAL
        else if ((l2 & 12) == 8) {   // SMLAL<x><y>
          int32_t a = (l2 & 2) ? (int32_t)r[rn] >> 16 : (int16_t)r[rn], b = (l2 & 1) ? (int32_t)r[rm] >> 16 : (int16_t)r[rm];
          res = acc + (uint64_t)((int64_t)a * b);
        } else if ((l2 & 14) == 12) res = acc + (uint64_t)dual16(r[rn], r[rm], l2 & 1, 0);   // SMLALD
        else goto undef;
        break;
      case 5: if ((l2 & 14) != 12) goto undef; res = acc + (uint64_t)dual16(r[rn], r[rm], l2 & 1, 1); break;   // SMLSLD
      case 6:
        if (l2 == 0) res = acc + (uint64_t)r[rn] * r[rm];                                        // UMLAL
        else if (l2 == 6) res = (uint64_t)r[rn] * r[rm] + r[lo] + r[hi];                         // UMAAL
        else goto undef;
        break;
      default: goto undef;
    }
    r[lo] = (uint32_t)res; r[hi] = (uint32_t)(res >> 32);
    return;
  }
  if (!(op2 & 0x40)) goto undef;

coproc:
  if ((hw1 & 0xEF00) == 0xEF00) {   // Advanced SIMD data processing
    if (!neon_dp(c, (op & 0x00FFFFFF) | 0xF2000000u | (((hw1 >> 12) & 1) << 24))) goto undef;
    return;
  }
  if (cop_exec(c, (op & 0x0FFFFFFF) | ((hw1 & 0x1000) ? 0xF0000000u : 0xE0000000u), pcv)) return;
undef:
  undefined(c, pc, op, 2);
}
