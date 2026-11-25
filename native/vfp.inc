// VFPv3 / VFPv4 (single and double precision) and the core<->SIMD register transfers (included by cpu.c).
// Instructions are given in their ARM encoding (Thumb-2 coprocessor instructions have the same low 28 bits).
#include <math.h>

static inline uint32_t sreg(Cpu *c, int i) { return ((uint32_t *)c->d)[i]; }
static inline void sreg_set(Cpu *c, int i, uint32_t v) { ((uint32_t *)c->d)[i] = v; }
static inline float sf(Cpu *c, int i) { float f; uint32_t u = sreg(c, i); memcpy(&f, &u, 4); return f; }
static inline void sf_set(Cpu *c, int i, float f) { uint32_t u; memcpy(&u, &f, 4); sreg_set(c, i, u); }
static inline double df(Cpu *c, int i) { double f; memcpy(&f, &c->d[i], 8); return f; }
static inline void df_set(Cpu *c, int i, double f) { memcpy(&c->d[i], &f, 8); }

// register numbers: single Vx:X, double X:Vx
#define VD_S(op) ((((op) >> 12) & 15) << 1 | (((op) >> 22) & 1))
#define VN_S(op) ((((op) >> 16) & 15) << 1 | (((op) >> 7) & 1))
#define VM_S(op) (((op) & 15) << 1 | (((op) >> 5) & 1))
#define VD_D(op) ((((op) >> 22) & 1) << 4 | (((op) >> 12) & 15))
#define VN_D(op) ((((op) >> 7) & 1) << 4 | (((op) >> 16) & 15))
#define VM_D(op) ((((op) >> 5) & 1) << 4 | ((op) & 15))

static inline float flush_f(Cpu *c, float f) {   // FPSCR.FZ: denormals are zero
  if ((c->fpscr >> 24) & 1 && f != 0 && fabsf(f) < 1.17549435e-38f) return copysignf(0, f);
  return f;
}
static inline void vfp_cmp_flags(Cpu *c, double a, double b) {
  uint32_t nzcv;
  if (isnan(a) || isnan(b)) nzcv = 0x3;
  else if (a == b) nzcv = 0x6;
  else if (a < b) nzcv = 0x8;
  else nzcv = 0x2;
  c->fpscr = (c->fpscr & 0x0FFFFFFF) | (nzcv << 28);
}
static inline double vfp_round(Cpu *c, double v, int toward_zero) {
  if (toward_zero) return trunc(v);
  switch ((c->fpscr >> 22) & 3) {
    case 0: return nearbyint(v);   // round to nearest even (the default environment)
    case 1: return ceil(v);
    case 2: return floor(v);
    default: return trunc(v);
  }
}
static inline uint32_t fp_to_int(Cpu *c, double v, int is_signed, int toward_zero) {
  if (isnan(v)) return 0;
  v = vfp_round(c, v, toward_zero);
  if (is_signed) {
    if (v >= 2147483647.0) return 0x7FFFFFFF;
    if (v <= -2147483648.0) return 0x80000000u;
    return (uint32_t)(int32_t)v;
  }
  if (v >= 4294967295.0) return 0xFFFFFFFFu;
  if (v <= 0) return 0;
  return (uint32_t)v;
}
static inline uint32_t vfp_expand_imm32(uint32_t imm8) {
  uint32_t b6 = (imm8 >> 6) & 1;
  return ((imm8 >> 7) << 31) | ((b6 ^ 1) << 30) | ((b6 ? 0x1Fu : 0) << 25) | (((imm8 >> 4) & 3) << 23) | ((imm8 & 15) << 19);
}
static inline uint64_t vfp_expand_imm64(uint32_t imm8) {
  uint64_t b6 = (imm8 >> 6) & 1;
  return ((uint64_t)(imm8 >> 7) << 63) | ((b6 ^ 1) << 62) | ((b6 ? 0xFFull : 0) << 54) | ((uint64_t)((imm8 >> 4) & 3) << 52) | ((uint64_t)(imm8 & 15) << 48);
}
// half precision <-> single (VCVTB/VCVTT)
static float half_to_float(uint16_t h) {
  uint32_t s = (h >> 15) & 1, e = (h >> 10) & 31, m = h & 0x3FF, bits;
  if (e == 0) {
    if (!m) bits = s << 31;
    else { e = 127 - 15 + 1; while (!(m & 0x400)) { m <<= 1; e--; } m &= 0x3FF; bits = (s << 31) | (e << 23) | (m << 13); }
  } else if (e == 31) bits = (s << 31) | 0x7F800000u | (m << 13);
  else bits = (s << 31) | ((e - 15 + 127) << 23) | (m << 13);
  float f; memcpy(&f, &bits, 4); return f;
}
static uint16_t float_to_half(float f) {
  uint32_t x; memcpy(&x, &f, 4);
  uint32_t s = (x >> 16) & 0x8000, e = (x >> 23) & 0xFF, m = x & 0x7FFFFF;
  if (e == 0xFF) return (uint16_t)(s | 0x7C00 | (m ? 0x200 : 0));
  int ne = (int)e - 127 + 15;
  if (ne >= 31) return (uint16_t)(s | 0x7C00);
  if (ne <= 0) {
    if (ne < -10) return (uint16_t)s;
    m |= 0x800000;
    uint32_t shift = (uint32_t)(14 - ne);
    uint32_t hm = m >> shift, rem = m & ((1u << shift) - 1), half = 1u << (shift - 1);
    if (rem > half || (rem == half && (hm & 1))) hm++;
    return (uint16_t)(s | hm);
  }
  uint32_t hm = m >> 13, rem = m & 0x1FFF;
  uint32_t h = s | ((uint32_t)ne << 10) | hm;
  if (rem > 0x1000 || (rem == 0x1000 && (hm & 1))) h++;
  return (uint16_t)h;
}

// VMOV between core registers and NEON scalars, VDUP from a core register
static int simd_transfer(Cpu *c, uint32_t op) {
  uint32_t *r = c->r;
  int rt = (op >> 12) & 15;
  int d = (((op >> 7) & 1) << 4) | ((op >> 16) & 15);
  uint8_t *dbytes = (uint8_t *)&c->d[0];
  if (!(op & 0x00100000)) {
    if (op & 0x00800000) {   // VDUP (core register)
      if (op & 0x40) return 0;
      int b = (op >> 22) & 1, e = (op >> 5) & 1, q = (op >> 21) & 1;
      uint32_t v = r[rt];
      uint64_t pat = b ? 0x0101010101010101ull * (v & 0xFF) : e ? 0x0001000100010001ull * (v & 0xFFFF) : ((uint64_t)v << 32 | v);
      c->d[d] = pat;
      if (q) c->d[d + 1] = pat;
      return 1;
    }
    int opc1 = (op >> 21) & 3, opc2 = (op >> 5) & 3;
    if (opc1 & 2) { int idx = ((opc1 & 1) << 2) | opc2; dbytes[d * 8 + idx] = (uint8_t)r[rt]; }
    else if (opc2 & 1) { int idx = ((opc1 & 1) << 1) | (opc2 >> 1); memcpy(dbytes + d * 8 + idx * 2, &r[rt], 2); }
    else if (opc2 == 0) { int idx = opc1 & 1; memcpy(dbytes + d * 8 + idx * 4, &r[rt], 4); }
    else return 0;
    return 1;
  }
  // scalar -> core
  int u = (op >> 23) & 1, opc1 = (op >> 21) & 3, opc2 = (op >> 5) & 3;
  uint32_t v;
  if (opc1 & 2) { int idx = ((opc1 & 1) << 2) | opc2; v = dbytes[d * 8 + idx]; if (!u) v = (uint32_t)(int32_t)(int8_t)v; }
  else if (opc2 & 1) { int idx = ((opc1 & 1) << 1) | (opc2 >> 1); uint16_t h; memcpy(&h, dbytes + d * 8 + idx * 2, 2); v = u ? h : (uint32_t)(int32_t)(int16_t)h; }
  else if (opc2 == 0 && !u) { int idx = opc1 & 1; memcpy(&v, dbytes + d * 8 + idx * 4, 4); }
  else return 0;
  if (rt == 15) return 0;
  r[rt] = v;
  return 1;
}

// returns 1 when op was a VFP / SIMD-transfer instruction it executed
static int vfp_exec(Cpu *c, uint32_t op, uint32_t pc_read) {
  uint32_t *r = c->r;
  uint32_t cp = (op >> 8) & 15;
  if ((cp & 0xE) != 0xA) return 0;   // coprocessors 10 and 11 only
  int dbl = cp & 1;

  // 64-bit transfers: VMOV two core registers <-> D register / two S registers
  if ((op & 0x0FE00000) == 0x0C400000) {
    int rt = (op >> 12) & 15, rt2 = (op >> 16) & 15, to_core = (op >> 20) & 1;
    if (dbl) {
      int m = VM_D(op);
      if (to_core) { r[rt] = (uint32_t)c->d[m]; r[rt2] = (uint32_t)(c->d[m] >> 32); }
      else c->d[m] = (uint64_t)r[rt2] << 32 | r[rt];
    } else {
      int m = VM_S(op);
      if (to_core) { r[rt] = sreg(c, m); r[rt2] = sreg(c, m + 1); }
      else { sreg_set(c, m, r[rt]); sreg_set(c, m + 1, r[rt2]); }
    }
    return 1;
  }
  // loads and stores
  if ((op & 0x0E000000) == 0x0C000000) {
    int p = (op >> 24) & 1, u = (op >> 23) & 1, w = (op >> 21) & 1, l = (op >> 20) & 1;
    int rn = (op >> 16) & 15;
    uint32_t imm = (op & 0xFF) << 2;
    uint32_t base = rn == 15 ? (pc_read & ~3u) : r[rn];
    if (p && !w) {   // VLDR / VSTR
      uint32_t addr = u ? base + imm : base - imm;
      if (dbl) {
        int dd = VD_D(op);
        if (l) c->d[dd] = (uint64_t)R32(addr + 4) << 32 | R32(addr);
        else { R32(addr) = (uint32_t)c->d[dd]; R32(addr + 4) = (uint32_t)(c->d[dd] >> 32); }
      } else {
        int sd = VD_S(op);
        if (l) sreg_set(c, sd, R32(addr)); else R32(addr) = sreg(c, sd);
      }
      return 1;
    }
    // VLDM / VSTM / VPUSH / VPOP
    if (p == u && w) return 0;
    uint32_t addr = p ? base - imm : base;
    if (dbl) {
      int dd = VD_D(op), n = (op & 0xFF) >> 1;
      for (int i = 0; i < n && dd + i < 32; i++, addr += 8) {
        if (l) c->d[dd + i] = (uint64_t)R32(addr + 4) << 32 | R32(addr);
        else { R32(addr) = (uint32_t)c->d[dd + i]; R32(addr + 4) = (uint32_t)(c->d[dd + i] >> 32); }
      }
    } else {
      int sd = VD_S(op), n = op & 0xFF;
      for (int i = 0; i < n && sd + i < 32; i++, addr += 4) {
        if (l) sreg_set(c, sd + i, R32(addr)); else R32(addr) = sreg(c, sd + i);
      }
    }
    if (w) r[rn] = u ? base + imm : base - imm;
    return 1;
  }
  if ((op & 0x0F000000) != 0x0E000000) return 0;

  // register transfers
  if (op & 0x10) {
    int rt = (op >> 12) & 15, l = (op >> 20) & 1;
    if (!dbl) {
      int opc1 = (op >> 21) & 7;
      if (opc1 == 0) {   // VMOV core <-> single
        int n = VN_S(op);
        if (l) r[rt] = sreg(c, n); else sreg_set(c, n, r[rt]);
        return 1;
      }
      if (opc1 == 7) {   // VMSR / VMRS
        int reg = (op >> 16) & 15;
        if (l) {
          uint32_t v = reg == 1 ? c->fpscr : reg == 0 ? 0x41023090 : reg == 8 ? 0x40000000 : reg == 7 ? 0x10110222 : reg == 6 ? 0x01111111 : 0;
          if (rt == 15) { c->n = c->fpscr >> 31; c->z = (c->fpscr >> 30) & 1; c->c = (c->fpscr >> 29) & 1; c->v = (c->fpscr >> 28) & 1; }
          else r[rt] = v;
        } else if (reg == 1) c->fpscr = r[rt];
        return 1;
      }
      return 0;
    }
    return simd_transfer(c, op);
  }

  // data processing
  int opc1 = ((op >> 20) & 3) | ((op >> 21) & 4), opc3 = (op >> 6) & 3;
  if (dbl) {
    int dd = VD_D(op), dn = VN_D(op), dm = VM_D(op);
    double a = df(c, dd), n = df(c, dn), m = df(c, dm);
    switch (opc1) {
      case 0: df_set(c, dd, (opc3 & 1) ? a - n * m : a + n * m); return 1;           // VMLA / VMLS
      case 1: df_set(c, dd, (opc3 & 1) ? -a - n * m : -a + n * m); return 1;         // VNMLA / VNMLS
      case 2: df_set(c, dd, (opc3 & 1) ? -(n * m) : n * m); return 1;                // VNMUL / VMUL
      case 3: df_set(c, dd, (opc3 & 1) ? n - m : n + m); return 1;                   // VSUB / VADD
      case 4: if (opc3 & 1) return 0; df_set(c, dd, n / m); return 1;                // VDIV
      case 5: df_set(c, dd, (opc3 & 1) ? fma(-n, m, -a) : fma(n, m, -a)); return 1;  // VFNMA / VFNMS
      case 6: df_set(c, dd, (opc3 & 1) ? fma(-n, m, a) : fma(n, m, a)); return 1;    // VFMS / VFMA
      default: break;
    }
    if (!(opc3 & 1)) { c->d[dd] = vfp_expand_imm64(((op >> 12) & 0xF0) | (op & 15)); return 1; }   // VMOV imm
    int opc2 = (op >> 16) & 15;
    switch (opc2) {
      case 0: if (opc3 == 1) c->d[dd] = c->d[dm]; else df_set(c, dd, fabs(m)); return 1;
      case 1: df_set(c, dd, opc3 == 1 ? -m : sqrt(m)); return 1;
      case 4: case 5: vfp_cmp_flags(c, a, opc2 == 5 ? 0.0 : m); return 1;
      case 7: if (opc3 != 3) return 0; sf_set(c, VD_S(op), (float)m); return 1;    // VCVT single <- double
      case 8: {   // VCVT double <- int (from a single register)
        uint32_t v = sreg(c, VM_S(op));
        df_set(c, dd, (op & 0x80) ? (double)(int32_t)v : (double)v);
        return 1;
      }
      case 10: case 11: case 14: case 15: {   // VCVT double <-> fixed point
        int to_fixed = (opc2 >> 2) & 1, uns = opc2 & 1, size = (op & 0x80) ? 32 : 16;
        int frac = size - (int)(((op & 15) << 1) | ((op >> 5) & 1));
        double scale = ldexp(1.0, frac);
        if (to_fixed) {
          double v = trunc(a * scale);
          uint64_t res;
          if (uns) res = isnan(v) || v < 0 ? 0 : v >= ldexp(1.0, size) - 1 ? (1ull << size) - 1 : (uint64_t)v;
          else { double mx = ldexp(1.0, size - 1); res = isnan(v) ? 0 : v >= mx - 1 ? (uint64_t)(mx - 1) : v <= -mx ? (uint64_t)(int64_t)-mx : (uint64_t)(int64_t)v; }
          c->d[dd] = size == 16 ? (uint64_t)(uns ? (uint16_t)res : (uint64_t)(int64_t)(int16_t)res) : (uint64_t)(uns ? (uint32_t)res : (uint64_t)(int64_t)(int32_t)res);
        } else {
          uint32_t v = (uint32_t)c->d[dd];
          double x = size == 16 ? (uns ? (double)(uint16_t)v : (double)(int16_t)v) : (uns ? (double)v : (double)(int32_t)v);
          df_set(c, dd, x / scale);
        }
        return 1;
      }
      case 12: case 13:   // VCVT int <- double (to a single register)
        sreg_set(c, VD_S(op), fp_to_int(c, m, opc2 & 1, (op >> 7) & 1));
        return 1;
    }
    return 0;
  }
  int sd = VD_S(op), sn = VN_S(op), sm = VM_S(op);
  float a = sf(c, sd), n = sf(c, sn), m = sf(c, sm);
  switch (opc1) {
    case 0: sf_set(c, sd, flush_f(c, (opc3 & 1) ? a - n * m : a + n * m)); return 1;
    case 1: sf_set(c, sd, flush_f(c, (opc3 & 1) ? -a - n * m : -a + n * m)); return 1;
    case 2: sf_set(c, sd, flush_f(c, (opc3 & 1) ? -(n * m) : n * m)); return 1;
    case 3: sf_set(c, sd, flush_f(c, (opc3 & 1) ? n - m : n + m)); return 1;
    case 4: if (opc3 & 1) return 0; sf_set(c, sd, flush_f(c, n / m)); return 1;
    case 5: sf_set(c, sd, (opc3 & 1) ? fmaf(-n, m, -a) : fmaf(n, m, -a)); return 1;
    case 6: sf_set(c, sd, (opc3 & 1) ? fmaf(-n, m, a) : fmaf(n, m, a)); return 1;
    default: break;
  }
  if (!(opc3 & 1)) { sreg_set(c, sd, vfp_expand_imm32(((op >> 12) & 0xF0) | (op & 15))); return 1; }
  int opc2 = (op >> 16) & 15;
  switch (opc2) {
    case 0: if (opc3 == 1) sreg_set(c, sd, sreg(c, sm)); else sf_set(c, sd, fabsf(m)); return 1;
    case 1: sf_set(c, sd, opc3 == 1 ? -m : sqrtf(m)); return 1;
    case 2: case 3: {   // VCVTB / VCVTT: half <-> single
      int top = (op >> 7) & 1;
      if (opc2 == 2) sf_set(c, sd, half_to_float((uint16_t)(sreg(c, sm) >> (top ? 16 : 0))));
      else {
        uint32_t h = float_to_half(m), cur = sreg(c, sd);
        sreg_set(c, sd, top ? (cur & 0xFFFF) | (h << 16) : (cur & 0xFFFF0000u) | h);
      }
      return 1;
    }
    case 4: case 5: vfp_cmp_flags(c, a, opc2 == 5 ? 0.0 : m); return 1;
    case 7: if (opc3 != 3) return 0; df_set(c, VD_D(op), (double)m); return 1;      // VCVT double <- single
    case 8: { uint32_t v = sreg(c, sm); sf_set(c, sd, (op & 0x80) ? (float)(int32_t)v : (float)v); return 1; }
    case 10: case 11: case 14: case 15: {
      int to_fixed = (opc2 >> 2) & 1, uns = opc2 & 1, size = (op & 0x80) ? 32 : 16;
      int frac = size - (int)(((op & 15) << 1) | ((op >> 5) & 1));
      double scale = ldexp(1.0, frac);
      if (to_fixed) {
        double v = trunc((double)a * scale);
        uint32_t res;
        if (uns) res = isnan(v) || v < 0 ? 0 : v >= ldexp(1.0, size) - 1 ? (uint32_t)((1ull << size) - 1) : (uint32_t)v;
        else { double mx = ldexp(1.0, size - 1); res = isnan(v) ? 0 : v >= mx - 1 ? (uint32_t)(int32_t)(mx - 1) : v <= -mx ? (uint32_t)(int32_t)-mx : (uint32_t)(int32_t)v; }
        if (size == 16) res = uns ? (res & 0xFFFF) : (uint32_t)(int32_t)(int16_t)res;
        sreg_set(c, sd, res);
      } else {
        uint32_t v = sreg(c, sd);
        double x = size == 16 ? (uns ? (double)(uint16_t)v : (double)(int16_t)v) : (uns ? (double)v : (double)(int32_t)v);
        sf_set(c, sd, (float)(x / scale));
      }
      return 1;
    }
    case 12: case 13: sreg_set(c, sd, fp_to_int(c, m, opc2 & 1, (op >> 7) & 1)); return 1;
  }
  return 0;
}
