// Advanced SIMD (NEON) for ARMv7 (included by cpu.c, after vfp.inc). Instructions are taken in their ARM
// encoding; Thumb-2 code maps its encodings onto these first.
// Reference: ARM Architecture Reference Manual ARMv7-A/R, A7 (Advanced SIMD and Floating-point encodings).
// Elements are stored little-endian in D0-D31 (Qn = D2n:D2n+1), the same order as in memory.
#include <float.h>

#define FPSCR_QC (1u << 27)
static inline uint8_t *VB(Cpu *c, int reg) { return (uint8_t *)c->d + reg * 8; }
static inline uint64_t ld_el(const uint8_t *p, int eb) { uint64_t v = 0; memcpy(&v, p, eb); return v; }
static inline void st_el(uint8_t *p, int eb, uint64_t v) { memcpy(p, &v, eb); }
static inline int64_t sext(uint64_t v, int bits) { return bits >= 64 ? (int64_t)v : (int64_t)(v << (64 - bits)) >> (64 - bits); }
static inline uint64_t mask_bits(int bits) { return bits >= 64 ? ~0ull : (1ull << bits) - 1; }
typedef __int128 i128;
static inline i128 el(const uint8_t *base, int i, int eb, int sgn) {
  uint64_t v = ld_el(base + i * eb, eb);
  return sgn ? (i128)sext(v, eb * 8) : (i128)v;
}
static uint64_t satq(Cpu *c, i128 v, int bits, int sgn) {
  i128 mx, mn;
  if (sgn) { mx = ((i128)1 << (bits - 1)) - 1; mn = -mx - 1; }
  else { mx = ((i128)1 << bits) - 1; mn = 0; }
  if (v > mx) { c->fpscr |= FPSCR_QC; v = mx; }
  else if (v < mn) { c->fpscr |= FPSCR_QC; v = mn; }
  return (uint64_t)v & mask_bits(bits);
}
// shift one element left (sh > 0) or right (sh < 0), optionally rounding and saturating (to out_sgn)
static uint64_t vshift(Cpu *c, i128 v, int bits, int sh, int rnd, int sat, int out_sgn) {
  i128 r;
  if (sh >= 0) {
    if (sh >= bits) {
      if (!sat || v == 0) return 0;
      return satq(c, v > 0 ? (i128)1 << 100 : -((i128)1 << 100), bits, out_sgn);
    }
    r = v << sh;
  } else {
    int s = -sh;
    if (s > bits + 1) s = bits + 1;
    r = rnd ? (v + ((i128)1 << (s - 1))) >> s : v >> s;
  }
  return sat ? satq(c, r, bits, out_sgn) : (uint64_t)r & mask_bits(bits);
}

// --------------------------------------------------------------------------- single-precision helpers
// NEON arithmetic runs in the "standard FPSCR" mode: flush-to-zero, default NaN, round to nearest
static inline float nf(float f) {
  if (isnan(f)) { uint32_t u = 0x7FC00000; memcpy(&f, &u, 4); return f; }
  if (f != 0 && fabsf(f) < FLT_MIN) return copysignf(0, f);
  return f;
}
static inline float f_of(uint64_t u) { uint32_t w = (uint32_t)u; float f; memcpy(&f, &w, 4); return f; }
static inline uint64_t u_of(float f) { uint32_t w; memcpy(&w, &f, 4); return w; }
static inline float fmaxn(float a, float b) { if (isnan(a) || isnan(b)) return nf(NAN); if (a == 0 && b == 0) return signbit(a) ? b : a; return a > b ? a : b; }
static inline float fminn(float a, float b) { if (isnan(a) || isnan(b)) return nf(NAN); if (a == 0 && b == 0) return signbit(a) ? a : b; return a < b ? a : b; }
static double recip_est(double a) {   // 0.5 <= a < 1.0 -> about 1/a with 8 bits (the ARM estimate table)
  int q = (int)(a * 512.0);
  double r = 1.0 / (((double)q + 0.5) / 512.0);
  int s = (int)(256.0 * r + 0.5);
  return (double)s / 256.0;
}
static double rsqrt_est(double a) {   // 0.25 <= a < 1.0
  double r;
  if (a < 0.5) { int q = (int)(a * 512.0); r = 1.0 / sqrt(((double)q + 0.5) / 512.0); }
  else { int q = (int)(a * 256.0); r = 1.0 / sqrt(((double)q + 0.5) / 256.0); }
  int s = (int)(256.0 * r + 0.5);
  return (double)s / 256.0;
}
static float vrecpe_f(float x) {
  if (isnan(x)) return nf(NAN);
  if (isinf(x)) return copysignf(0, x);
  if (x == 0 || fabsf(x) < FLT_MIN) return copysignf(INFINITY, x);
  int e; double m = frexp(fabs((double)x), &e);
  double r = ldexp(recip_est(m), -e);
  if (r < FLT_MIN) return copysignf(0, x);
  return copysignf((float)r, x);
}
static float vrsqrte_f(float x) {
  if (isnan(x) || x < 0) return x == 0 ? copysignf(INFINITY, x) : nf(NAN);
  if (isinf(x)) return 0;
  if (x == 0 || x < FLT_MIN) return copysignf(INFINITY, x);
  int e; double m = frexp((double)x, &e);
  if (e & 1) { m /= 2; e++; }
  return (float)ldexp(rsqrt_est(m), -e / 2);
}
static uint32_t vrecpe_u(uint32_t x) {
  if (!(x >> 31)) return 0xFFFFFFFFu;
  double est = recip_est((double)(x >> 23) / 512.0);
  return (uint32_t)(est * 256.0) << 23;
}
static uint32_t vrsqrte_u(uint32_t x) {
  if (!(x >> 30)) return 0xFFFFFFFFu;
  double est = rsqrt_est((double)(x >> 23) / 512.0);
  return (uint32_t)(est * 256.0) << 23;
}
static inline uint32_t f2i_rz(float f, int uns) {   // NEON VCVT: round toward zero, saturate
  if (isnan(f)) return 0;
  double v = trunc((double)f);
  if (uns) return v <= 0 ? 0 : v >= 4294967295.0 ? 0xFFFFFFFFu : (uint32_t)v;
  return v >= 2147483647.0 ? 0x7FFFFFFF : v <= -2147483648.0 ? 0x80000000u : (uint32_t)(int32_t)v;
}
static inline uint32_t poly_mul8(uint32_t a, uint32_t b) {   // carry-less multiply, result truncated to 8 bits
  uint32_t r = 0;
  for (int i = 0; i < 8; i++) if ((b >> i) & 1) r ^= a << i;
  return r;
}
static inline uint32_t poly_mull8(uint32_t a, uint32_t b) {
  uint32_t r = 0;
  for (int i = 0; i < 8; i++) if ((b >> i) & 1) r ^= a << i;
  return r & 0xFFFF;
}

// --------------------------------------------------------------------------- three registers of the same length
static int neon_3same(Cpu *c, uint32_t op) {
  int u = (op >> 24) & 1, a = (op >> 8) & 15, b = (op >> 4) & 1, sz = (op >> 20) & 3, q = (op >> 6) & 1;
  int d = VD_D(op), n = VN_D(op), m = VM_D(op);
  if (q && ((d | n | m) & 1)) return 0;
  int bytes = q ? 16 : 8, eb = 1 << sz, nel = bytes / eb, bits = eb * 8;
  uint8_t *pd = VB(c, d), *pn = VB(c, n), *pm = VB(c, m);
  uint8_t out[16];
  memcpy(out, pd, bytes);
  int sgn = !u;

  if (a == 1 && b == 1) {   // bitwise
    for (int i = 0; i < bytes; i += 8) {
      uint64_t x = ld_el(pn + i, 8), y = ld_el(pm + i, 8), z = ld_el(pd + i, 8), r;
      switch (u << 2 | sz) {
        case 0: r = x & y; break;              // VAND
        case 1: r = x & ~y; break;             // VBIC
        case 2: r = x | y; break;              // VORR / VMOV
        case 3: r = x | ~y; break;             // VORN
        case 4: r = x ^ y; break;              // VEOR
        case 5: r = (x & z) | (y & ~z); break; // VBSL
        case 6: r = (x & y) | (z & ~y); break; // VBIT
        default: r = (z & y) | (x & ~y); break;// VBIF
      }
      st_el(out + i, 8, r);
    }
    memcpy(pd, out, bytes);
    return 1;
  }

  if (a >= 13 || (a == 12 && b == 1)) {   // single-precision floating point
    if (sz & 1) return 0;
    int hi = sz >> 1;   // selects between the pair of operations sharing an encoding
    int pairwise = u && ((a == 13 && b == 0 && !hi) || (a == 15 && b == 0));
    if (pairwise) {
      if (q) return 0;
      float x0 = f_of(ld_el(pn, 4)), x1 = f_of(ld_el(pn + 4, 4)), y0 = f_of(ld_el(pm, 4)), y1 = f_of(ld_el(pm + 4, 4)), r0, r1;
      if (a == 13) { r0 = nf(x0 + x1); r1 = nf(y0 + y1); }
      else if (!hi) { r0 = fmaxn(x0, x1); r1 = fmaxn(y0, y1); }
      else { r0 = fminn(x0, x1); r1 = fminn(y0, y1); }
      st_el(out, 4, u_of(r0)); st_el(out + 4, 4, u_of(r1));
      memcpy(pd, out, 8);
      return 1;
    }
    for (int i = 0; i < bytes / 4; i++) {
      float x = nf(f_of(ld_el(pn + 4 * i, 4))), y = nf(f_of(ld_el(pm + 4 * i, 4))), z = nf(f_of(ld_el(pd + 4 * i, 4)));
      uint64_t r;
      switch (a << 2 | b << 1 | u) {
        case 12 << 2 | 2: r = u_of(nf(hi ? fmaf(-x, y, z) : fmaf(x, y, z))); break;   // VFMS / VFMA
        case 13 << 2 | 0: r = u_of(nf(hi ? x - y : x + y)); break;                    // VSUB / VADD
        case 13 << 2 | 1: if (!hi) return 0; r = u_of(nf(fabsf(x - y))); break;      // VABD
        case 13 << 2 | 2: r = u_of(nf(hi ? z - nf(x * y) : z + nf(x * y))); break;   // VMLS / VMLA
        case 13 << 2 | 3: if (hi) return 0; r = u_of(nf(x * y)); break;              // VMUL
        case 14 << 2 | 0: if (hi) return 0; r = x == y ? 0xFFFFFFFFu : 0; break;     // VCEQ
        case 14 << 2 | 1: r = (hi ? x > y : x >= y) ? 0xFFFFFFFFu : 0; break;         // VCGT / VCGE
        case 14 << 2 | 3: r = (hi ? fabsf(x) > fabsf(y) : fabsf(x) >= fabsf(y)) ? 0xFFFFFFFFu : 0; break;   // VACGT / VACGE
        case 15 << 2 | 0: r = u_of(hi ? fminn(x, y) : fmaxn(x, y)); break;
        case 15 << 2 | 2:   // VRSQRTS / VRECPS
          if (hi) r = u_of(nf(((isinf(x) && y == 0) || (x == 0 && isinf(y))) ? 1.5f : (3.0f - x * y) / 2.0f));
          else r = u_of(nf(((isinf(x) && y == 0) || (x == 0 && isinf(y))) ? 2.0f : 2.0f - x * y));
          break;
        default: return 0;
      }
      st_el(out + 4 * i, 4, r);
    }
    memcpy(pd, out, bytes);
    return 1;
  }

  if (a == 10 || (a == 11 && b == 1)) {   // integer pairwise: VPMAX VPMIN VPADD
    if (q || (a == 11 && u) || sz == 3) return 0;
    int half = nel / 2;
    for (int i = 0; i < nel; i++) {
      const uint8_t *src = i < half ? pn : pm;
      int j = (i % half) * 2;
      i128 x = el(src, j, eb, sgn), y = el(src, j + 1, eb, sgn), r;
      if (a == 11) r = x + y;
      else r = b ? (x < y ? x : y) : (x > y ? x : y);
      st_el(out + i * eb, eb, (uint64_t)r);
    }
    memcpy(pd, out, bytes);
    return 1;
  }

  for (int i = 0; i < nel; i++) {
    i128 x = el(pn, i, eb, sgn), y = el(pm, i, eb, sgn), z = el(pd, i, eb, sgn);
    uint64_t r;
    switch (a << 1 | b) {
      case 0: r = (uint64_t)((x + y) >> 1); break;                       // VHADD
      case 1: r = satq(c, x + y, bits, sgn); break;                      // VQADD
      case 2: r = (uint64_t)((x + y + 1) >> 1); break;                   // VRHADD
      case 4: r = (uint64_t)((x - y) >> 1); break;                       // VHSUB
      case 5: r = satq(c, x - y, bits, sgn); break;                      // VQSUB
      case 6: r = x > y ? ~0ull : 0; break;                              // VCGT
      case 7: r = x >= y ? ~0ull : 0; break;                             // VCGE
      case 8: case 9: case 10: case 11: {                                // VSHL VQSHL VRSHL VQRSHL (by register)
        int sh = (int8_t)ld_el(pn + i * eb, 1);
        i128 v = el(pm, i, eb, sgn);
        r = vshift(c, v, bits, sh, a == 5, b, sgn);
        break;
      }
      case 12: r = (uint64_t)(x > y ? x : y); break;                     // VMAX
      case 13: r = (uint64_t)(x < y ? x : y); break;                     // VMIN
      case 14: r = (uint64_t)(x > y ? x - y : y - x); break;             // VABD
      case 15: r = (uint64_t)(z + (x > y ? x - y : y - x)); break;       // VABA
      case 16: r = (uint64_t)(u ? x - y : x + y); break;                 // VSUB / VADD
      case 17: r = u ? (((uint64_t)x & mask_bits(bits)) == ((uint64_t)y & mask_bits(bits)) ? ~0ull : 0)   // VCEQ
                     : (((uint64_t)(x & y) & mask_bits(bits)) ? ~0ull : 0); break;                         // VTST
      case 18: r = (uint64_t)(u ? z - x * y : z + x * y); break;         // VMLS / VMLA
      case 19:                                                           // VMUL (integer / polynomial)
        if (u) { if (sz) return 0; r = poly_mul8((uint32_t)x & 0xFF, (uint32_t)y & 0xFF); }
        else r = (uint64_t)(x * y);
        break;
      case 22: {                                                         // VQDMULH / VQRDMULH
        if (sz != 1 && sz != 2) return 0;
        i128 xs = el(pn, i, eb, 1), ys = el(pm, i, eb, 1), p = 2 * xs * ys;
        if (u) p += (i128)1 << (bits - 1);
        r = satq(c, p >> bits, bits, 1);
        break;
      }
      default: return 0;
    }
    st_el(out + i * eb, eb, r);
  }
  memcpy(pd, out, bytes);
  return 1;
}

// --------------------------------------------------------------------------- three registers of different lengths
static int neon_3diff(Cpu *c, uint32_t op) {
  int u = (op >> 24) & 1, a = (op >> 8) & 15, sz = (op >> 20) & 3;
  int d = VD_D(op), n = VN_D(op), m = VM_D(op);
  int eb = 1 << sz, bits = eb * 8, nel = 8 / eb, sgn = !u;
  uint8_t *pd = VB(c, d), *pn = VB(c, n), *pm = VB(c, m);
  uint8_t out[16];
  if (a == 4 || a == 6) {   // VADDHN VRADDHN VSUBHN VRSUBHN: Dd = high half of (Qn +/- Qm)
    if ((n | m) & 1) return 0;
    for (int i = 0; i < nel; i++) {
      i128 x = el(pn, i, eb * 2, 0), y = el(pm, i, eb * 2, 0);
      i128 s = a == 4 ? x + y : x - y;
      if (u) s += (i128)1 << (bits - 1);
      st_el(out + i * eb, eb, (uint64_t)(s >> bits));
    }
    memcpy(pd, out, 8);
    return 1;
  }
  if (d & 1) return 0;
  memcpy(out, pd, 16);
  for (int i = 0; i < nel; i++) {
    i128 x = (a == 1 || a == 3) ? el(pn, i, eb * 2, sgn) : el(pn, i, eb, sgn);   // VADDW / VSUBW take a wide Qn
    i128 y = el(pm, i, eb, sgn), z = el(pd, i, eb * 2, sgn), r;
    uint64_t w;
    switch (a) {
      case 0: case 1: r = x + y; break;
      case 2: case 3: r = x - y; break;
      case 5: r = z + (x > y ? x - y : y - x); break;
      case 7: r = x > y ? x - y : y - x; break;
      case 8: r = z + x * y; break;
      case 10: r = z - x * y; break;
      case 12: r = x * y; break;
      case 9: case 11: case 13: {   // VQDMLAL VQDMLSL VQDMULL
        if (u || (sz != 1 && sz != 2)) return 0;
        i128 p = (i128)(int64_t)satq(c, 2 * x * y, bits * 2, 1);
        p = (i128)sext((uint64_t)p, bits * 2);
        if (a == 13) { w = (uint64_t)p; st_el(out + i * eb * 2, eb * 2, w); continue; }
        w = satq(c, a == 9 ? z + p : z - p, bits * 2, 1);
        st_el(out + i * eb * 2, eb * 2, w);
        continue;
      }
      case 14:   // VMULL.P8
        if (u || sz) return 0;
        r = poly_mull8((uint32_t)x & 0xFF, (uint32_t)y & 0xFF);
        break;
      default: return 0;
    }
    st_el(out + i * eb * 2, eb * 2, (uint64_t)r);
  }
  memcpy(pd, out, 16);
  return 1;
}

// --------------------------------------------------------------------------- two registers and a scalar
static int neon_scalar(Cpu *c, uint32_t op) {
  int a = (op >> 8) & 15, sz = (op >> 20) & 3, u = (op >> 24) & 1;
  if (sz != 1 && sz != 2) return 0;
  int d = VD_D(op), n = VN_D(op), vm = op & 15, mbit = (op >> 5) & 1;
  int eb = 1 << sz, bits = eb * 8;
  int mreg = sz == 1 ? (vm & 7) : vm, idx = sz == 1 ? (mbit << 1 | (vm >> 3)) : mbit;
  uint8_t *pd = VB(c, d), *pn = VB(c, n), *ps = VB(c, mreg) + idx * eb;
  uint8_t out[16];
  int is_long = a == 2 || a == 3 || a == 6 || a == 7 || a == 10 || a == 11;
  if (!is_long) {
    int q = u, bytes = q ? 16 : 8, nel = bytes / eb, f = a & 1;
    if (q && ((d | n) & 1)) return 0;
    memcpy(out, pd, bytes);
    for (int i = 0; i < nel; i++) {
      uint64_t r;
      if (f && a < 12) {   // floating point VMLA VMLS VMUL
        if (sz != 2) return 0;
        float x = nf(f_of(ld_el(pn + 4 * i, 4))), s = nf(f_of(ld_el(ps, 4))), z = nf(f_of(ld_el(pd + 4 * i, 4)));
        float p = nf(x * s);
        r = u_of(a == 9 ? p : a == 1 ? nf(z + p) : nf(z - p));
      } else {
        i128 x = el(pn, i, eb, 1), s = el(ps, 0, eb, 1), z = el(pd, i, eb, 1);
        switch (a) {
          case 0: r = (uint64_t)(z + x * s); break;
          case 4: r = (uint64_t)(z - x * s); break;
          case 8: r = (uint64_t)(x * s); break;
          case 12: r = satq(c, (2 * x * s) >> bits, bits, 1); break;
          case 13: r = satq(c, (2 * x * s + ((i128)1 << (bits - 1))) >> bits, bits, 1); break;
          default: return 0;
        }
      }
      st_el(out + i * eb, eb, r);
    }
    memcpy(pd, out, bytes);
    return 1;
  }
  if (d & 1) return 0;
  int nel = 8 / eb, sgn = !u || a == 3 || a == 7 || a == 11;
  memcpy(out, pd, 16);
  for (int i = 0; i < nel; i++) {
    i128 x = el(pn, i, eb, sgn), s = el(ps, 0, eb, sgn), z = el(pd, i, eb * 2, sgn);
    uint64_t r;
    switch (a) {
      case 2: r = (uint64_t)(z + x * s); break;
      case 6: r = (uint64_t)(z - x * s); break;
      case 10: r = (uint64_t)(x * s); break;
      case 3: case 7: case 11: {
        if (u) return 0;
        i128 p = sext(satq(c, 2 * x * s, bits * 2, 1), bits * 2);
        r = a == 11 ? (uint64_t)p : satq(c, a == 3 ? z + p : z - p, bits * 2, 1);
        break;
      }
      default: return 0;
    }
    st_el(out + i * eb * 2, eb * 2, r);
  }
  memcpy(pd, out, 16);
  return 1;
}

// --------------------------------------------------------------------------- two registers and a shift amount
static int neon_shift(Cpu *c, uint32_t op) {
  int u = (op >> 24) & 1, a = (op >> 8) & 15, l = (op >> 7) & 1, q = (op >> 6) & 1, imm6 = (op >> 16) & 63;
  int d = VD_D(op), m = VM_D(op);
  int esize = l ? 64 : (imm6 & 0x20) ? 32 : (imm6 & 0x10) ? 16 : 8;
  int rs = (l ? 64 : 2 * esize) - imm6, ls = l ? imm6 : imm6 - esize;
  int eb = esize / 8;
  uint8_t *pd = VB(c, d), *pm = VB(c, m);
  uint8_t out[16];

  if (a == 8 || a == 9) {   // narrowing right shifts: Dd (esize) <- Qm (2*esize)
    if (l || (m & 1)) return 0;
    int rnd = q;   // bit 6 selects the rounding form here
    int in_sgn, out_sgn, sat;
    if (a == 8 && !u) { in_sgn = 0; out_sgn = 0; sat = 0; }         // VSHRN / VRSHRN
    else if (a == 8) { in_sgn = 1; out_sgn = 0; sat = 1; }          // VQSHRUN / VQRSHRUN
    else { in_sgn = !u; out_sgn = !u; sat = 1; }                    // VQSHRN / VQRSHRN
    for (int i = 0; i < 8 / eb; i++) {
      i128 v = el(pm, i, eb * 2, in_sgn);
      i128 r = rnd ? (v + ((i128)1 << (rs - 1))) >> rs : v >> rs;
      st_el(out + i * eb, eb, sat ? satq(c, r, esize, out_sgn) : (uint64_t)r & mask_bits(esize));
    }
    memcpy(pd, out, 8);
    return 1;
  }
  if (a == 10) {   // VSHLL / VMOVL: Qd (2*esize) <- Dm (esize) << shift
    if (l || q || (d & 1)) return 0;
    for (int i = 0; i < 8 / eb; i++) st_el(out + i * eb * 2, eb * 2, (uint64_t)(el(pm, i, eb, !u) << ls));
    memcpy(pd, out, 16);
    return 1;
  }
  int bytes = q ? 16 : 8, nel = bytes / eb;
  if (q && ((d | m) & 1)) return 0;
  if (a == 14 || a == 15) {   // VCVT between single precision and fixed point
    if (l || !(imm6 & 0x20)) return 0;
    int frac = 64 - imm6;
    for (int i = 0; i < nel; i++) {
      uint32_t v = (uint32_t)ld_el(pm + 4 * i, 4), r;
      if (a == 14) r = (uint32_t)u_of(nf((float)((u ? (double)v : (double)(int32_t)v) / ldexp(1.0, frac))));
      else r = f2i_rz((float)((double)nf(f_of(v)) * ldexp(1.0, frac)), u);
      st_el(out + 4 * i, 4, r);
    }
    memcpy(pd, out, bytes);
    return 1;
  }
  memcpy(out, pd, bytes);
  for (int i = 0; i < nel; i++) {
    uint64_t r, dv = ld_el(pd + i * eb, eb);
    i128 v = el(pm, i, eb, !u);
    switch (a) {
      case 0: r = vshift(c, v, esize, -rs, 0, 0, 0); break;                            // VSHR
      case 1: r = dv + vshift(c, v, esize, -rs, 0, 0, 0); break;                       // VSRA
      case 2: r = vshift(c, v, esize, -rs, 1, 0, 0); break;                            // VRSHR
      case 3: r = dv + vshift(c, v, esize, -rs, 1, 0, 0); break;                       // VRSRA
      case 4: {                                                                       // VSRI
        if (!u) return 0;
        uint64_t mk = rs >= esize ? 0 : mask_bits(esize) >> rs;
        r = (dv & ~mk) | ((rs >= esize ? 0 : (uint64_t)(el(pm, i, eb, 0) >> rs)) & mk);
        break;
      }
      case 5:
        if (!u) r = vshift(c, v, esize, ls, 0, 0, 0);                                  // VSHL
        else { uint64_t mk = (mask_bits(esize) << ls) & mask_bits(esize); r = (dv & ~mk) | (((uint64_t)el(pm, i, eb, 0) << ls) & mk); }   // VSLI
        break;
      case 6: if (!u) return 0; r = vshift(c, el(pm, i, eb, 1), esize, ls, 0, 1, 0); break;   // VQSHLU
      case 7: r = vshift(c, v, esize, ls, 0, 1, !u); break;                             // VQSHL
      default: return 0;
    }
    st_el(out + i * eb, eb, r & mask_bits(esize));
  }
  memcpy(pd, out, bytes);
  return 1;
}

// --------------------------------------------------------------------------- one register and a modified immediate
static int neon_imm(Cpu *c, uint32_t op) {
  int cmode = (op >> 8) & 15, o = (op >> 5) & 1, q = (op >> 6) & 1, d = VD_D(op);
  uint32_t imm8 = ((op >> 24) & 1) << 7 | ((op >> 16) & 7) << 4 | (op & 15);
  if (q && (d & 1)) return 0;
  uint64_t imm;
  switch (cmode >> 1) {
    case 0: imm = imm8 * 0x0000000100000001ull; break;
    case 1: imm = (imm8 << 8) * 0x0000000100000001ull; break;
    case 2: imm = (imm8 << 16) * 0x0000000100000001ull; break;
    case 3: imm = ((uint64_t)imm8 << 24) * 0x0000000100000001ull; break;
    case 4: imm = imm8 * 0x0001000100010001ull; break;
    case 5: imm = (imm8 << 8) * 0x0001000100010001ull; break;
    case 6: imm = ((cmode & 1) ? ((imm8 << 16) | 0xFFFF) : ((imm8 << 8) | 0xFF)) * 0x0000000100000001ull; break;
    default:
      if (!(cmode & 1)) {
        if (!o) imm = imm8 * 0x0101010101010101ull;
        else { imm = 0; for (int i = 0; i < 8; i++) if ((imm8 >> i) & 1) imm |= 0xFFull << (8 * i); }
      } else {
        if (o) return 0;
        imm = (uint64_t)vfp_expand_imm32(imm8) * 0x0000000100000001ull;
      }
  }
  int is_or = cmode < 12 && (cmode & 1);   // 0xx1 and 10x1: VORR / VBIC
  for (int i = 0; i <= q; i++) {
    uint64_t *p = &c->d[d + i];
    if (is_or) *p = o ? (*p & ~imm) : (*p | imm);           // VBIC / VORR
    else if (o && cmode != 14) *p = ~imm;                   // VMVN
    else *p = imm;                                          // VMOV
  }
  return 1;
}

// --------------------------------------------------------------------------- two registers, miscellaneous
static int neon_misc(Cpu *c, uint32_t op) {
  int sz = (op >> 18) & 3, a = (op >> 16) & 3, b = (op >> 6) & 31, q = (op >> 6) & 1;
  int d = VD_D(op), m = VM_D(op);
  int eb = 1 << sz, bits = eb * 8;
  uint8_t *pd = VB(c, d), *pm = VB(c, m);
  uint8_t out[16];
  int bytes = q ? 16 : 8, nel = bytes / eb;

  if (a == 2 && (b >> 1) >= 4) {   // narrowing / widening forms (registers of different lengths)
    if (b >= 8 && b <= 11) {   // VMOVN VQMOVUN VQMOVN: Dd <- Qm
      if (m & 1) return 0;
      for (int i = 0; i < 8 / eb; i++) {
        uint64_t r;
        if (b == 8) r = ld_el(pm + i * eb * 2, eb);
        else if (b == 9) r = satq(c, el(pm, i, eb * 2, 1), bits, 0);
        else r = satq(c, el(pm, i, eb * 2, b == 10), bits, b == 10);
        st_el(out + i * eb, eb, r);
      }
      memcpy(pd, out, 8);
      return 1;
    }
    if (b == 12) {   // VSHLL (shift by element size): Qd <- Dm
      if (d & 1) return 0;
      for (int i = 0; i < 8 / eb; i++) st_el(out + i * eb * 2, eb * 2, ld_el(pm + i * eb, eb) << bits);
      memcpy(pd, out, 16);
      return 1;
    }
    if (b == 24 && sz == 1) {   // VCVT.F16.F32 Dd, Qm
      if (m & 1) return 0;
      for (int i = 0; i < 4; i++) st_el(out + 2 * i, 2, float_to_half(f_of(ld_el(pm + 4 * i, 4))));
      memcpy(pd, out, 8);
      return 1;
    }
    if (b == 28 && sz == 1) {   // VCVT.F32.F16 Qd, Dm
      if (d & 1) return 0;
      for (int i = 0; i < 4; i++) st_el(out + 4 * i, 4, u_of(half_to_float((uint16_t)ld_el(pm + 2 * i, 2))));
      memcpy(pd, out, 16);
      return 1;
    }
    return 0;
  }
  if (q && ((d | m) & 1)) return 0;

  if (a == 2) {   // VSWP VTRN VUZP VZIP
    uint8_t x[16], y[16];
    memcpy(x, pd, bytes); memcpy(y, pm, bytes);
    switch (b >> 1) {
      case 0: memcpy(pd, y, bytes); memcpy(pm, x, bytes); return 1;
      case 1:
        for (int i = 0; i + 1 < nel; i += 2) {
          uint64_t t = ld_el(x + (i + 1) * eb, eb);
          st_el(x + (i + 1) * eb, eb, ld_el(y + i * eb, eb));
          st_el(y + i * eb, eb, t);
        }
        memcpy(pd, x, bytes); memcpy(pm, y, bytes);
        return 1;
      case 2: case 3: {
        uint8_t cat[32], res[32];
        if (sz == 3) return 0;
        if ((b >> 1) == 2) {   // VUZP: even elements to Dd, odd to Dm
          memcpy(cat, x, bytes); memcpy(cat + bytes, y, bytes);
          for (int i = 0; i < nel; i++) {
            st_el(res + i * eb, eb, ld_el(cat + 2 * i * eb, eb));
            st_el(res + bytes + i * eb, eb, ld_el(cat + (2 * i + 1) * eb, eb));
          }
        } else {               // VZIP: interleave
          for (int i = 0; i < nel; i++) {
            st_el(res + 2 * i * eb, eb, ld_el(x + i * eb, eb));
            st_el(res + (2 * i + 1) * eb, eb, ld_el(y + i * eb, eb));
          }
        }
        memcpy(pd, res, bytes); memcpy(pm, res + bytes, bytes);
        return 1;
      }
    }
    return 0;
  }

  for (int i = 0; i < nel; i++) {
    uint64_t v = ld_el(pm + i * eb, eb), r;
    if (a == 0) {
      switch (b >> 1) {
        case 0: case 1: case 2: {   // VREV64 VREV32 VREV16: reverse elements within each group
          int group = (8 >> (b >> 1)), per = group / eb;
          if (per < 1 || eb >= group) return 0;
          int base = (i / per) * per, k = i - base;
          r = ld_el(pm + (base + per - 1 - k) * eb, eb);
          break;
        }
        case 8: { int64_t s = sext(v, bits); uint64_t x = (uint64_t)(s < 0 ? ~s : s) & mask_bits(bits); r = x ? __builtin_clzll(x) - (64 - bits) - 1 : bits - 1; break; }   // VCLS
        case 9: r = v ? __builtin_clzll(v) - (64 - bits) : bits; break;                   // VCLZ
        case 10: if (sz) return 0; r = __builtin_popcountll(v); break;                    // VCNT
        case 11: r = ~v; break;                                                          // VMVN
        case 14: r = satq(c, sext(v, bits) < 0 ? -(i128)sext(v, bits) : (i128)sext(v, bits), bits, 1); break;   // VQABS
        case 15: r = satq(c, -(i128)sext(v, bits), bits, 1); break;                      // VQNEG
        default:
          if ((b >> 2) == 2 || (b >> 2) == 6) {   // VPADDL / VPADAL: wider elements from adjacent pairs
            if (sz == 3) return 0;
            int uns = (op >> 7) & 1, w = eb * 2;
            for (int j = 0; j < bytes / w; j++) {
              i128 s = el(pm, 2 * j, eb, !uns) + el(pm, 2 * j + 1, eb, !uns);
              if ((b >> 2) == 6) s += el(pd, j, w, !uns);
              st_el(out + j * w, w, (uint64_t)s);
            }
            memcpy(pd, out, bytes);
            return 1;
          }
          return 0;
      }
    } else if (a == 1) {
      int f = (op >> 10) & 1, k = (b >> 1) & 7;
      if (f) {
        if (sz != 2) return 0;
        float x = nf(f_of(v));
        switch (k) {
          case 0: r = x > 0 ? 0xFFFFFFFFu : 0; break;
          case 1: r = x >= 0 ? 0xFFFFFFFFu : 0; break;
          case 2: r = x == 0 ? 0xFFFFFFFFu : 0; break;
          case 3: r = x <= 0 ? 0xFFFFFFFFu : 0; break;
          case 4: r = x < 0 ? 0xFFFFFFFFu : 0; break;
          case 6: r = v & 0x7FFFFFFFu; break;
          case 7: r = v ^ 0x80000000u; break;
          default: return 0;
        }
      } else {
        int64_t s = sext(v, bits);
        switch (k) {
          case 0: r = s > 0 ? ~0ull : 0; break;
          case 1: r = s >= 0 ? ~0ull : 0; break;
          case 2: r = s == 0 ? ~0ull : 0; break;
          case 3: r = s <= 0 ? ~0ull : 0; break;
          case 4: r = s < 0 ? ~0ull : 0; break;
          case 6: r = (uint64_t)(s < 0 ? -s : s); break;
          case 7: r = (uint64_t)-s; break;
          default: return 0;
        }
      }
    } else {   // a == 3: VRECPE VRSQRTE VCVT
      if (sz != 2) return 0;
      if ((b >> 3) == 2) {
        int f = (b >> 2) & 1, rsq = (b >> 1) & 1;
        if (f) r = u_of(rsq ? vrsqrte_f(nf(f_of(v))) : vrecpe_f(nf(f_of(v))));
        else r = rsq ? vrsqrte_u((uint32_t)v) : vrecpe_u((uint32_t)v);
      } else if ((b >> 3) == 3) {
        int o = (b >> 1) & 3;
        if (o == 0) r = u_of(nf((float)(int32_t)v));
        else if (o == 1) r = u_of(nf((float)(uint32_t)v));
        else r = f2i_rz(nf(f_of(v)), o == 3);
      } else return 0;
    }
    st_el(out + i * eb, eb, r);
  }
  memcpy(pd, out, bytes);
  return 1;
}

// --------------------------------------------------------------------------- data-processing dispatch
static int neon_dp(Cpu *c, uint32_t op) {
  int u = (op >> 24) & 1;
  if (!(op & 0x00800000)) return neon_3same(c, op);
  if (op & 0x10) {
    if (!((op >> 19) & 7) && !(op & 0x80)) return neon_imm(c, op);
    return neon_shift(c, op);
  }
  int sz = (op >> 20) & 3;
  if (sz != 3) return (op & 0x40) ? neon_scalar(c, op) : neon_3diff(c, op);
  if (!u) {   // VEXT
    int q = (op >> 6) & 1, imm4 = (op >> 8) & 15, bytes = q ? 16 : 8;
    int d = VD_D(op), n = VN_D(op), m = VM_D(op);
    if (q && ((d | n | m) & 1)) return 0;
    if (!q && imm4 > 7) return 0;
    uint8_t cat[32];
    memcpy(cat, VB(c, n), bytes); memcpy(cat + bytes, VB(c, m), bytes);
    memcpy(VB(c, d), cat + imm4, bytes);
    return 1;
  }
  int b = (op >> 8) & 15;
  if (!(b & 8)) return neon_misc(c, op);
  if ((b & 12) == 8) {   // VTBL / VTBX
    int len = (op >> 8) & 3, x = (op >> 6) & 1, d = VD_D(op), n = VN_D(op), m = VM_D(op);
    uint8_t table[32], res[8];
    for (int i = 0; i <= len; i++) memcpy(table + 8 * i, VB(c, (n + i) & 31), 8);
    for (int i = 0; i < 8; i++) {
      int idx = VB(c, m)[i];
      res[i] = idx < 8 * (len + 1) ? table[idx] : x ? VB(c, d)[i] : 0;
    }
    memcpy(VB(c, d), res, 8);
    return 1;
  }
  if (b == 12 && !(op & 0x80)) {   // VDUP (scalar)
    int imm4 = (op >> 16) & 15, q = (op >> 6) & 1, d = VD_D(op), m = VM_D(op), eb, idx;
    if (imm4 & 1) { eb = 1; idx = imm4 >> 1; }
    else if (imm4 & 2) { eb = 2; idx = imm4 >> 2; }
    else if (imm4 & 4) { eb = 4; idx = imm4 >> 3; }
    else return 0;
    if (q && (d & 1)) return 0;
    uint64_t v = ld_el(VB(c, m) + idx * eb, eb);
    uint8_t res[16];
    for (int i = 0; i < (q ? 16 : 8) / eb; i++) st_el(res + i * eb, eb, v);
    memcpy(VB(c, d), res, q ? 16 : 8);
    return 1;
  }
  return 0;
}

// --------------------------------------------------------------------------- element and structure loads/stores
static int neon_ls(Cpu *c, uint32_t op) {
  uint32_t *r = c->r;
  int a = (op >> 23) & 1, l = (op >> 21) & 1, rn = (op >> 16) & 15, rm = op & 15, b = (op >> 8) & 15;
  int d = VD_D(op);
  uint32_t addr = r[rn];
  uint32_t total;
  if (!a) {   // multiple structures
    int n, regs, inc = 1;
    switch (b) {
      case 7: n = 1; regs = 1; break;
      case 10: n = 1; regs = 2; break;
      case 6: n = 1; regs = 3; break;
      case 2: n = 1; regs = 4; break;
      case 8: n = 2; regs = 1; break;
      case 9: n = 2; regs = 1; inc = 2; break;
      case 3: n = 2; regs = 2; inc = 2; break;
      case 4: n = 3; regs = 1; break;
      case 5: n = 3; regs = 1; inc = 2; break;
      case 0: n = 4; regs = 1; break;
      case 1: n = 4; regs = 1; inc = 2; break;
      default: return 0;
    }
    int eb = 1 << ((op >> 6) & 3), nel = 8 / eb;
    if (n == 1) {
      if (d + regs > 32) return 0;
      if (l) memcpy(VB(c, d), G(addr), 8 * regs); else memcpy(G(addr), VB(c, d), 8 * regs);
      total = 8 * regs;
    } else {
      if (eb == 8) return 0;
      uint32_t p = addr;
      for (int rr = 0; rr < regs; rr++)
        for (int e = 0; e < nel; e++)
          for (int k = 0; k < n; k++, p += eb) {
            uint8_t *reg = VB(c, (d + rr + k * inc) & 31) + e * eb;
            if (l) memcpy(reg, G(p), eb); else memcpy(G(p), reg, eb);
          }
      total = p - addr;
    }
  } else {
    int size = (b >> 2) & 3, n = (b & 3) + 1;
    if (size == 3) {   // load one element to all lanes
      if (!l) return 0;
      int sz2 = (op >> 6) & 3, t = (op >> 5) & 1, eb = sz2 == 3 ? 4 : 1 << sz2;
      int regs = n == 1 ? (t ? 2 : 1) : 1, inc = n == 1 ? 1 : (t ? 2 : 1);
      for (int k = 0; k < n; k++) {
        uint64_t v = ld_el(G(addr + k * eb), eb);
        for (int rr = 0; rr < regs; rr++) {
          uint8_t *reg = VB(c, (d + k * inc + rr) & 31);
          for (int i = 0; i < 8 / eb; i++) st_el(reg + i * eb, eb, v);
        }
      }
      total = n * eb;
    } else {           // one lane
      int ia = (op >> 4) & 15, eb = 1 << size;
      int idx = size == 0 ? ia >> 1 : size == 1 ? ia >> 2 : ia >> 3;
      int inc = n == 1 || size == 0 ? 1 : size == 1 ? ((ia >> 1) & 1) + 1 : ((ia >> 2) & 1) + 1;
      for (int k = 0; k < n; k++) {
        uint8_t *reg = VB(c, (d + k * inc) & 31) + idx * eb;
        if (l) memcpy(reg, G(addr + k * eb), eb); else memcpy(G(addr + k * eb), reg, eb);
      }
      total = n * eb;
    }
  }
  if (rm == 13) r[rn] = addr + total;
  else if (rm != 15) r[rn] = addr + r[rm];
  return 1;
}
