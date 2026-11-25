// ARMv6/ARMv7 integer instructions shared by the ARM and Thumb-2 decoders (included by cpu.c).
// Reference: ARM Architecture Reference Manual ARMv7-A/R (DDI 0406C), chapters A5 (ARM) and A6 (Thumb).

static inline uint32_t ror32(uint32_t v, uint32_t n) { n &= 31; return n ? (v >> n) | (v << (32 - n)) : v; }

// --------------------------------------------------------------------------- saturation
static inline uint32_t ssat(Cpu *c, int32_t v, int bits) {   // SSAT to a signed 'bits'-bit range
  int32_t max = (int32_t)((1u << (bits - 1)) - 1), min = -max - 1;
  if (v > max) { c->q = 1; return (uint32_t)max; }
  if (v < min) { c->q = 1; return (uint32_t)min; }
  return (uint32_t)v;
}
static inline uint32_t usat(Cpu *c, int32_t v, int bits) {   // USAT to [0, 2^bits - 1]
  uint32_t max = bits >= 32 ? 0xFFFFFFFFu : (1u << bits) - 1;
  if (v < 0) { c->q = 1; return 0; }
  if ((uint32_t)v > max) { c->q = 1; return max; }
  return (uint32_t)v;
}

// --------------------------------------------------------------------------- extends, reversal, bit fields
static inline uint32_t do_extend(int op, uint32_t rn_val, uint32_t m, int add) {
  // op: 0 SXTB16 1 SXTB 2 SXTH 4 UXTB16 5 UXTB 6 UXTH ; m already rotated
  switch (op) {
    case 0: {
      uint32_t lo = (uint32_t)(int32_t)(int8_t)m, hi = (uint32_t)(int32_t)(int8_t)(m >> 16);
      if (add) { lo += rn_val; hi += rn_val >> 16; }
      return (lo & 0xFFFF) | (hi << 16);
    }
    case 1: return (add ? rn_val : 0) + (uint32_t)(int32_t)(int8_t)m;
    case 2: return (add ? rn_val : 0) + (uint32_t)(int32_t)(int16_t)m;
    case 4: {
      uint32_t lo = m & 0xFF, hi = (m >> 16) & 0xFF;
      if (add) { lo += rn_val; hi += rn_val >> 16; }
      return (lo & 0xFFFF) | (hi << 16);
    }
    case 5: return (add ? rn_val : 0) + (m & 0xFF);
    case 6: return (add ? rn_val : 0) + (m & 0xFFFF);
  }
  return m;
}
static inline uint32_t rbit32(uint32_t v) {
  v = ((v >> 1) & 0x55555555u) | ((v & 0x55555555u) << 1);
  v = ((v >> 2) & 0x33333333u) | ((v & 0x33333333u) << 2);
  v = ((v >> 4) & 0x0F0F0F0Fu) | ((v & 0x0F0F0F0Fu) << 4);
  return __builtin_bswap32(v);
}
static inline uint32_t rev16(uint32_t v) { return ((v & 0x00FF00FFu) << 8) | ((v >> 8) & 0x00FF00FFu); }
static inline uint32_t revsh(uint32_t v) { return (uint32_t)(int32_t)(int16_t)(((v & 0xFF) << 8) | ((v >> 8) & 0xFF)); }
static inline uint32_t ubfx(uint32_t v, int lsb, int width) { return width >= 32 ? v >> lsb : (v >> lsb) & ((1u << width) - 1); }
static inline uint32_t sbfx(uint32_t v, int lsb, int width) {
  uint32_t x = ubfx(v, lsb, width);
  return width >= 32 ? x : (uint32_t)((int32_t)(x << (32 - width)) >> (32 - width));
}
static inline uint32_t bfi(uint32_t d, uint32_t src, int lsb, int msb) {   // msb inclusive
  if (msb < lsb) return d;
  int width = msb - lsb + 1;
  uint32_t mask = (width >= 32 ? 0xFFFFFFFFu : ((1u << width) - 1)) << lsb;
  return (d & ~mask) | ((src << lsb) & mask);
}

// --------------------------------------------------------------------------- parallel add/subtract (SIMD in core registers)
// prefix: 1 S (signed, GE) 2 Q (signed saturating) 3 SH (signed halving) 5 U (unsigned, GE) 6 UQ 7 UH
// op: 0 ADD16 1 ASX 2 SAX 3 SUB16 4 ADD8 7 SUB8
static uint32_t parallel(Cpu *c, int prefix, int op, uint32_t a, uint32_t b) {
  int sgn = prefix < 4, kind = prefix & 3;   // kind: 1 plain, 2 saturating, 3 halving
  uint32_t res = 0, ge = 0;
  if (op >= 4) {   // bytes
    for (int i = 0; i < 4; i++) {
      int32_t x = sgn ? (int8_t)(a >> (8 * i)) : (int32_t)((a >> (8 * i)) & 0xFF);
      int32_t y = sgn ? (int8_t)(b >> (8 * i)) : (int32_t)((b >> (8 * i)) & 0xFF);
      int32_t r = op == 4 ? x + y : x - y;
      if (kind == 1) ge |= (sgn ? r >= 0 : (op == 4 ? r >= 0x100 : r >= 0)) ? 1u << i : 0;
      if (kind == 2) r = sgn ? (r > 127 ? 127 : r < -128 ? -128 : r) : (r > 255 ? 255 : r < 0 ? 0 : r);
      if (kind == 3) r >>= 1;
      res |= ((uint32_t)r & 0xFF) << (8 * i);
    }
    if (kind == 1) c->ge = ge;
    return res;
  }
  for (int i = 0; i < 2; i++) {
    int32_t ax = sgn ? (int16_t)(a >> (16 * i)) : (int32_t)((a >> (16 * i)) & 0xFFFF);
    // ASX: lo = a.lo - b.hi, hi = a.hi + b.lo ; SAX: lo = a.lo + b.hi, hi = a.hi - b.lo
    int j = (op == 1 || op == 2) ? 1 - i : i;
    int32_t bx = sgn ? (int16_t)(b >> (16 * j)) : (int32_t)((b >> (16 * j)) & 0xFFFF);
    int add = op == 0 || (op == 1 && i == 1) || (op == 2 && i == 0);
    int32_t r = add ? ax + bx : ax - bx;
    if (kind == 1) ge |= (sgn ? r >= 0 : (add ? r >= 0x10000 : r >= 0)) ? 3u << (2 * i) : 0;
    if (kind == 2) r = sgn ? (r > 32767 ? 32767 : r < -32768 ? -32768 : r) : (r > 65535 ? 65535 : r < 0 ? 0 : r);
    if (kind == 3) r >>= 1;
    res |= ((uint32_t)r & 0xFFFF) << (16 * i);
  }
  if (kind == 1) c->ge = ge;
  return res;
}
static inline uint32_t sel(Cpu *c, uint32_t a, uint32_t b) {
  uint32_t r = 0;
  for (int i = 0; i < 4; i++) r |= ((c->ge >> i) & 1 ? a : b) & (0xFFu << (8 * i));
  return r;
}
static inline uint32_t usad8(uint32_t a, uint32_t b) {
  uint32_t s = 0;
  for (int i = 0; i < 4; i++) { int d = (int)((a >> (8 * i)) & 0xFF) - (int)((b >> (8 * i)) & 0xFF); s += d < 0 ? -d : d; }
  return s;
}

// --------------------------------------------------------------------------- signed multiplies (SMLAD, SMMUL...)
static inline int64_t dual16(uint32_t n, uint32_t m, int swap, int sub) {
  if (swap) m = ror32(m, 16);
  int64_t p1 = (int64_t)(int16_t)n * (int16_t)m, p2 = (int64_t)(int16_t)(n >> 16) * (int16_t)(m >> 16);
  return sub ? p1 - p2 : p1 + p2;
}

// --------------------------------------------------------------------------- exclusive access
static inline uint32_t ldrex(Cpu *c, uint32_t addr, int size) {
  c->excl = addr;
  switch (size) { case 1: return R8(addr); case 2: return R16(addr); default: return R32(addr); }
}
static inline int strex_ok(Cpu *c, uint32_t addr) {
  int ok = c->excl == addr;
  c->excl = 1;
  return ok;
}

// --------------------------------------------------------------------------- coprocessor 15 (thread registers)
static uint32_t tpidrurw;
static uint32_t cp15_read(Cpu *c, int crn, int opc1, int crm, int opc2) {
  if (crn == 13 && crm == 0 && opc1 == 0) {
    if (opc2 == 3) {   // TPIDRURO: the thread's TLS area (bionic keeps its slots there)
      if (!c->tls) { c->tls = GA(calloc(1, 4096)); }
      return c->tls;
    }
    if (opc2 == 2) return tpidrurw;
  }
  if (crn == 0 && crm == 0 && opc1 == 0 && opc2 == 0) return 0x410FC090;   // MIDR: Cortex-A9
  return 0;
}
static void cp15_write(Cpu *c, int crn, int opc1, int crm, int opc2, uint32_t v) {
  if (crn == 13 && crm == 0 && opc1 == 0 && opc2 == 2) tpidrurw = v;
  if (crn == 7) return;   // cache maintenance, barriers (CP15DMB...)
}
