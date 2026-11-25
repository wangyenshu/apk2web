// ARMv7-A interpreter: ARM and Thumb-2 instruction sets with VFPv3/VFPv4 and Advanced SIMD (NEON).
// armeabi (ARMv5TE, soft-float) code is a subset; armeabi-v7a code uses the softfp calling convention, so
// arguments and results stay in core registers either way.
#include "emu.h"
#include <emscripten.h>
#include <errno.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define R8(a) (*(uint8_t *)(uintptr_t)(a))
#define R16(a) (*(uint16_t *)(uintptr_t)(a))
#define R32(a) (*(uint32_t *)(uintptr_t)(a))
#define S8(a) (*(int8_t *)(uintptr_t)(a))
#define S16(a) (*(int16_t *)(uintptr_t)(a))

// ---------------------------------------------------------------------------
// hooks: host functions reachable from guest code through a permanently undefined instruction:
// ARM 0xE7F000F0 | (id>>4)<<8 | (id&15), Thumb UDF.W 0xF7F0 | id>>12, 0xA000 | (id&0xFFF)

#define MAX_HOOKS 16384
static HookFn hooks[MAX_HOOKS];
static const char *hook_names[MAX_HOOKS];
static int nhooks;
static uint32_t *stubs;

static inline uint32_t hook_insn(int id) { return 0xE7F000F0u | ((uint32_t)(id >> 4) << 8) | (id & 15); }

int hook_register(const char *name, HookFn fn) {
  if (!stubs) {
    stubs = aligned_alloc(16, MAX_HOOKS * 4);
    for (int i = 0; i < MAX_HOOKS; i++) stubs[i] = hook_insn(i);
  }
  if (nhooks >= MAX_HOOKS) fatal("too many hooks");
  hooks[nhooks] = fn;
  hook_names[nhooks] = name;
  return nhooks++;
}
uint32_t hook_stub_address(int id) { return GA(&stubs[id]); }
void hook_patch(uint32_t addr, int id) {
  if (addr & 1) {   // Thumb function: a 32-bit UDF.W (the entry is at least 2-aligned)
    addr &= ~1u;
    R16(addr) = (uint16_t)(0xF7F0 | ((id >> 12) & 15));
    R16(addr + 2) = (uint16_t)(0xA000 | (id & 0xFFF));
    return;
  }
  R32(addr) = hook_insn(id);
}
const char *hook_name(int id) { return id >= 0 && id < nhooks ? hook_names[id] : "?"; }
/** the hook whose stub is at addr, or NULL */
const char *hook_at(uint32_t addr) {
  if (!stubs || addr < GA(stubs) || addr >= GA(stubs) + 4u * MAX_HOOKS) return NULL;
  return hook_name((int)((addr - GA(stubs)) / 4));
}

void hook_return(Cpu *c) {
  uint32_t lr = c->r[14];
  c->t = lr & 1;
  c->r[15] = lr & ~1u;
}

int current_hook;
static void run_hook(Cpu *c, int id, uint32_t at) {
  if (id >= nhooks || !hooks[id]) fatal("bad hook %d at %08x", id, at);
  current_hook = id;
  c->r[15] = at;  // a blocked hook retries from here
  uint32_t before_pc = at;
  extern void host_errno_sync(void);
  errno = 0;
  hooks[id](c);
  host_errno_sync();   // a host function that failed: its errno becomes the guest thread's
  if (c->stop || c->blocked) return;
  if (c->r[15] == before_pc) hook_return(c);  // hooks that jump set r15 themselves
}

// ---------------------------------------------------------------------------

static inline int cond_ok(Cpu *c, uint32_t cc) {
  switch (cc) {
    case 0: return c->z;
    case 1: return !c->z;
    case 2: return c->c;
    case 3: return !c->c;
    case 4: return c->n;
    case 5: return !c->n;
    case 6: return c->v;
    case 7: return !c->v;
    case 8: return c->c && !c->z;
    case 9: return !c->c || c->z;
    case 10: return c->n == c->v;
    case 11: return c->n != c->v;
    case 12: return !c->z && c->n == c->v;
    case 13: return c->z || c->n != c->v;
    default: return 1;
  }
}

#define SETNZ(x) do { uint32_t _x = (x); c->n = _x >> 31; c->z = _x == 0; } while (0)

static inline uint32_t add_flags(Cpu *c, uint32_t a, uint32_t b, uint32_t cin) {
  uint64_t s = (uint64_t)a + b + cin;
  uint32_t r = (uint32_t)s;
  c->n = r >> 31; c->z = r == 0; c->c = (uint32_t)(s >> 32);
  c->v = ((a ^ r) & (b ^ r)) >> 31;
  return r;
}
static inline uint32_t sub_flags(Cpu *c, uint32_t a, uint32_t b, uint32_t cin) {  // a - b - !cin
  return add_flags(c, a, ~b, cin);
}

static inline uint32_t cpsr(Cpu *c) {
  return (c->n << 31) | (c->z << 30) | (c->c << 29) | (c->v << 28) | (c->q << 27) | (c->ge << 16) | (c->t << 5) | 0x10;
}

// shift by immediate amount (ARM encoding: 0 means 32 for LSR/ASR, RRX for ROR)
static inline uint32_t shift_imm(Cpu *c, uint32_t type, uint32_t v, uint32_t amt, uint32_t *carry) {
  switch (type) {
    case 0:  // LSL
      if (!amt) { *carry = c->c; return v; }
      *carry = (v >> (32 - amt)) & 1; return v << amt;
    case 1:  // LSR
      if (!amt) { *carry = v >> 31; return 0; }
      *carry = (v >> (amt - 1)) & 1; return v >> amt;
    case 2:  // ASR
      if (!amt) { *carry = v >> 31; return (uint32_t)((int32_t)v >> 31); }
      *carry = (v >> (amt - 1)) & 1; return (uint32_t)((int32_t)v >> amt);
    default:  // ROR / RRX
      if (!amt) { *carry = v & 1; return (c->c << 31) | (v >> 1); }
      *carry = (v >> (amt - 1)) & 1; return (v >> amt) | (v << (32 - amt));
  }
}
// shift by register amount (bottom byte)
static inline uint32_t shift_reg(Cpu *c, uint32_t type, uint32_t v, uint32_t amt, uint32_t *carry) {
  amt &= 0xFF;
  if (!amt) { *carry = c->c; return v; }
  switch (type) {
    case 0:
      if (amt < 32) { *carry = (v >> (32 - amt)) & 1; return v << amt; }
      *carry = amt == 32 ? (v & 1) : 0; return 0;
    case 1:
      if (amt < 32) { *carry = (v >> (amt - 1)) & 1; return v >> amt; }
      *carry = amt == 32 ? (v >> 31) : 0; return 0;
    case 2:
      if (amt < 32) { *carry = (v >> (amt - 1)) & 1; return (uint32_t)((int32_t)v >> amt); }
      *carry = v >> 31; return (uint32_t)((int32_t)v >> 31);
    default: {
      uint32_t a = amt & 31;
      if (!a) { *carry = v >> 31; return v; }
      *carry = (v >> (a - 1)) & 1; return (v >> a) | (v << (32 - a));
    }
  }
}

static inline void branch_x(Cpu *c, uint32_t target) {  // interworking branch
  c->t = target & 1;
  c->r[15] = target & (c->t ? ~1u : ~3u);
}

static inline int32_t sat32(Cpu *c, int64_t v) {
  if (v > 0x7FFFFFFFLL) { c->q = 1; return 0x7FFFFFFF; }
  if (v < -0x80000000LL) { c->q = 1; return (int32_t)0x80000000; }
  return (int32_t)v;
}

// thumb: 0 ARM, 1 16-bit Thumb, 2 32-bit Thumb
static void undefined(Cpu *c, uint32_t pc, uint32_t op, int thumb) {
  fatal("undefined %s instruction %0*x at %s: not an ARMv7-A (ARM, Thumb-2, VFPv4, NEON) instruction the emulator knows",
        thumb ? "thumb" : "arm", thumb == 1 ? 4 : 8, op, elf_where(pc));
}

static uint32_t cp15_read(Cpu *c, int crn, int opc1, int crm, int opc2);

// the kernel's user helpers at the top of the address space (old libgcc atomics and TLS reads call them)
static int kuser(Cpu *c, uint32_t pc) {
  uint32_t *r = c->r;
  switch (pc) {
    case 0xFFFF0FC0: {   // __kuser_cmpxchg(oldval r0, newval r1, ptr r2): r0 = 0 and C set when stored
      int ok = R32(r[2]) == r[0];
      if (ok) R32(r[2]) = r[1];
      r[0] = ok ? 0 : 1; c->c = ok;
      if (ok) { c->z = 1; c->n = 0; } else c->z = 0;
      break;
    }
    case 0xFFFF0F60: {   // __kuser_cmpxchg64(&oldval r0, &newval r1, ptr r2)
      uint64_t cur, old, nw;
      memcpy(&cur, G(r[2]), 8); memcpy(&old, G(r[0]), 8); memcpy(&nw, G(r[1]), 8);
      int ok = cur == old;
      if (ok) memcpy(G(r[2]), &nw, 8);
      r[0] = ok ? 0 : 1; c->c = ok; c->z = ok;
      break;
    }
    case 0xFFFF0FA0: break;   // __kuser_memory_barrier
    case 0xFFFF0FE0: r[0] = cp15_read(c, 13, 0, 0, 3); break;   // __kuser_get_tls
    default: return 0;
  }
  branch_x(c, r[14]);
  return 1;
}

// supervisor calls that libraries make directly (the rest of the system is the host's libc)
static void svc(Cpu *c, uint32_t pc) {
  uint32_t nr = c->r[7];
  switch (nr) {
    case 0xF0002: c->r[0] = 0; return;                   // cacheflush: the interpreter always sees fresh code
    case 0xF0005: c->tls = c->r[0]; c->r[0] = 0; return;  // set_tls
    case 20: case 224: c->r[0] = c->thread ? (uint32_t)c->thread->id + 1 : 1; return;   // getpid / gettid
    case 158: c->r[0] = 0; return;                       // sched_yield
  }
  fatal("system call %u at %s", nr, elf_where(pc));
}

#include "arm7.inc"
#include "vfp.inc"
#include "neon.inc"

// ARM media instructions (ARMv6 SIMD, saturation, bit fields, extends, reversal, ARMv7 divide)
static int arm_media(Cpu *c, uint32_t op) {
  uint32_t *r = c->r;
  int op1 = (op >> 20) & 31, op2 = (op >> 5) & 7;
  int rd = (op >> 12) & 15, rn = (op >> 16) & 15, rm = op & 15;
  if ((op1 & 0x18) == 0x00) {   // parallel add/subtract
    static const int pmap[8] = {0, 1, 2, 3, 4, -1, -1, 7};
    int prefix = op1 & 7, p = pmap[op2];
    if (!(prefix & 3) || p < 0) return 0;
    r[rd] = parallel(c, prefix, p, r[rn], r[rm]);
    return 1;
  }
  if ((op1 & 0x18) == 0x08) {   // packing, unpacking, saturation, reversal
    int o1 = op1 & 7, imm5 = (op >> 7) & 31;
    if (op2 == 3) {   // extends
      static const int map[8] = {0, -1, 1, 2, 4, -1, 5, 6};
      int e = map[o1];
      if (e < 0) return 0;
      r[rd] = do_extend(e, rn == 15 ? 0 : r[rn], ror32(r[rm], ((op >> 10) & 3) * 8), rn != 15);
      return 1;
    }
    if (o1 == 0 && !(op2 & 1)) {   // PKHBT / PKHTB
      if (op & 0x40) {
        uint32_t v = imm5 ? (uint32_t)((int32_t)r[rm] >> imm5) : (uint32_t)((int32_t)r[rm] >> 31);
        r[rd] = (r[rn] & 0xFFFF0000u) | (v & 0xFFFF);
      } else r[rd] = (r[rn] & 0xFFFF) | ((r[rm] << imm5) & 0xFFFF0000u);
      return 1;
    }
    if (o1 == 0 && op2 == 5) { r[rd] = sel(c, r[rn], r[rm]); return 1; }
    if ((o1 & 2) && !(op2 & 1)) {   // SSAT / USAT
      int sat = (op >> 16) & 31;
      int32_t x = (op & 0x40) ? (int32_t)r[rm] >> (imm5 ? imm5 : 31) : (int32_t)(r[rm] << imm5);
      r[rd] = (o1 & 4) ? usat(c, x, sat) : ssat(c, x, sat + 1);
      return 1;
    }
    if ((o1 == 2 || o1 == 6) && op2 == 1) {   // SSAT16 / USAT16
      int sat = (op >> 16) & 15;
      uint32_t v = r[rm], lo, hi;
      if (o1 == 6) { lo = usat(c, (int16_t)v, sat); hi = usat(c, (int32_t)v >> 16, sat); }
      else { lo = ssat(c, (int16_t)v, sat + 1); hi = ssat(c, (int32_t)v >> 16, sat + 1); }
      r[rd] = (lo & 0xFFFF) | (hi << 16);
      return 1;
    }
    if (o1 == 3 && op2 == 1) { r[rd] = __builtin_bswap32(r[rm]); return 1; }
    if (o1 == 3 && op2 == 5) { r[rd] = rev16(r[rm]); return 1; }
    if (o1 == 7 && op2 == 1) { r[rd] = rbit32(r[rm]); return 1; }
    if (o1 == 7 && op2 == 5) { r[rd] = revsh(r[rm]); return 1; }
    return 0;
  }
  if ((op1 & 0x18) == 0x10) {   // signed multiplies, divide
    int o1 = op1 & 7, mbit = (op >> 5) & 1;
    int d = (op >> 16) & 15, a = (op >> 12) & 15, m = (op >> 8) & 15, n = op & 15;
    switch (o1) {
      case 0: {   // SMLAD SMUAD SMLSD SMUSD
        if (op2 & 4) return 0;
        int64_t p = dual16(r[n], r[m], mbit, op2 >> 1) + (a == 15 ? 0 : (int32_t)r[a]);
        if (p != (int32_t)p) c->q = 1;
        r[d] = (uint32_t)p;
        return 1;
      }
      case 1: case 3: {   // SDIV / UDIV
        if (op2) return 0;
        uint32_t x = r[n], y = r[m];
        if (!y) r[d] = 0;
        else if (o1 == 1) r[d] = ((int32_t)x == INT32_MIN && (int32_t)y == -1) ? x : (uint32_t)((int32_t)x / (int32_t)y);
        else r[d] = x / y;
        return 1;
      }
      case 4: {   // SMLALD / SMLSLD (RdHi = bits 19:16, RdLo = bits 15:12)
        if (op2 & 4) return 0;
        uint64_t acc = ((uint64_t)r[d] << 32) | r[a];
        acc += (uint64_t)dual16(r[n], r[m], mbit, op2 >> 1);
        r[a] = (uint32_t)acc; r[d] = (uint32_t)(acc >> 32);
        return 1;
      }
      case 5: {   // SMMLA SMMUL SMMLS
        if (op2 != 0 && op2 != 1 && op2 != 6 && op2 != 7) return 0;
        int64_t prod = (int64_t)(int32_t)r[n] * (int32_t)r[m];
        int64_t p = (op2 >> 1) ? (int64_t)((uint64_t)r[a] << 32) - prod : (a == 15 ? 0 : (int64_t)((uint64_t)r[a] << 32)) + prod;
        if (mbit) p += 0x80000000LL;
        r[d] = (uint32_t)((uint64_t)p >> 32);
        return 1;
      }
    }
    return 0;
  }
  // op1 = 11xxx
  if (op1 == 0x18 && op2 == 0) {   // USAD8 / USADA8
    int d = (op >> 16) & 15, a = (op >> 12) & 15, m = (op >> 8) & 15;
    r[d] = usad8(r[rm], r[m]) + (a == 15 ? 0 : r[a]);
    return 1;
  }
  int lsb = (op >> 7) & 31, hi5 = (op >> 16) & 31;
  if ((op1 & 0x1E) == 0x1A && (op2 & 3) == 2) { r[rd] = sbfx(r[rm], lsb, hi5 + 1); return 1; }
  if ((op1 & 0x1E) == 0x1E && (op2 & 3) == 2) { r[rd] = ubfx(r[rm], lsb, hi5 + 1); return 1; }
  if ((op1 & 0x1E) == 0x1C && (op2 & 3) == 0) { r[rd] = bfi(r[rd], rm == 15 ? 0 : r[rm], lsb, hi5); return 1; }
  return 0;
}

#include "thumb2.inc"

// ---------------------------------------------------------------------------
// ARM state

static void arm_step(Cpu *c, uint32_t pc, uint32_t op) {
  uint32_t *r = c->r;
  uint32_t cc = op >> 28;
  if (cc == 15) {
    if ((op & 0x0E000000) == 0x0A000000) {  // BLX imm
      int32_t off = ((int32_t)(op << 8) >> 6) | ((op >> 23) & 2);
      r[14] = pc + 4;
      c->t = 1;
      r[15] = pc + 8 + off;
      return;
    }
    if ((op & 0xFE000000) == 0xF2000000) { if (!neon_dp(c, op)) undefined(c, pc, op, 0); return; }
    if ((op & 0xFF100000) == 0xF4000000) { if (!neon_ls(c, op)) undefined(c, pc, op, 0); return; }
    if ((op & 0xFD30F000) == 0xF510F000 || (op & 0xFD70F000) == 0xF450F000) return;   // PLD PLDW PLI
    if ((op & 0xFFFFFF00) == 0xF57FF000) { if (((op >> 4) & 15) == 1) c->excl = 1; return; }   // CLREX DSB DMB ISB
    if ((op & 0xFFF10020) == 0xF1000000 || (op & 0xFFFFFDFF) == 0xF1010000) return;   // CPS, SETEND LE
    undefined(c, pc, op, 0);
    return;
  }
  if (cc != 14 && !cond_ok(c, cc)) return;

  switch ((op >> 25) & 7) {
    case 0:
    case 1: {
      if (!(op & 0x02000000)) {
        // miscellaneous encodings in the register data-processing space
        if ((op & 0x0FFFFFD0) == 0x012FFF10) {  // BX / BLX reg
          uint32_t target = r[op & 15];
          if (op & 0x20) r[14] = pc + 4;
          branch_x(c, target);
          return;
        }
        if ((op & 0x0FFF0FF0) == 0x016F0F10) {  // CLZ
          uint32_t v = r[op & 15];
          r[(op >> 12) & 15] = v ? __builtin_clz(v) : 32;
          return;
        }
        if ((op & 0x0F900FF0) == 0x01000050) {  // QADD QSUB QDADD QDSUB
          int32_t a = r[op & 15], b = r[(op >> 16) & 15];
          int ty = (op >> 21) & 3;
          if (ty & 2) b = sat32(c, (int64_t)b * 2);
          r[(op >> 12) & 15] = sat32(c, (ty & 1) ? (int64_t)a - b : (int64_t)a + b);
          return;
        }
        if ((op & 0x0F900090) == 0x01000080) {  // signed halfword multiplies
          uint32_t rd = (op >> 16) & 15, rn = (op >> 12) & 15, rs = (op >> 8) & 15, rm = op & 15;
          int x = (op >> 5) & 1, y = (op >> 6) & 1;
          int32_t m = x ? (int32_t)r[rm] >> 16 : (int16_t)r[rm];
          int32_t s = y ? (int32_t)r[rs] >> 16 : (int16_t)r[rs];
          switch ((op >> 21) & 3) {
            case 0: {  // SMLAxy: wraps, sets Q on overflow
              int64_t p = (int64_t)m * s + (int32_t)r[rn];
              if (p != (int32_t)p) c->q = 1;
              r[rd] = (uint32_t)p;
              break;
            }
            case 1: {
              int64_t p = ((int64_t)(int32_t)r[rm] * s) >> 16;
              if (!x) { p += (int32_t)r[rn]; if (p != (int32_t)p) c->q = 1; }  // SMLAWy (SMULWy when x)
              r[rd] = (uint32_t)p;
              break;
            }
            case 2: {  // SMLALxy
              int64_t acc = (int64_t)(((uint64_t)r[rd] << 32) | r[rn]);
              acc += (int64_t)m * s;
              r[rn] = (uint32_t)acc; r[rd] = (uint32_t)((uint64_t)acc >> 32);
              break;
            }
            case 3: r[rd] = (uint32_t)(m * s); break;  // SMULxy
          }
          return;
        }
        if ((op & 0x0FBF0FFF) == 0x010F0000) { r[(op >> 12) & 15] = cpsr(c); return; }  // MRS
        if ((op & 0x0FB0FFF0) == 0x0120F000) {  // MSR reg
          uint32_t v = r[op & 15];
          if (op & 0x00080000 && !(op & 0x00400000)) {
            c->n = v >> 31; c->z = (v >> 30) & 1; c->c = (v >> 29) & 1; c->v = (v >> 28) & 1; c->q = (v >> 27) & 1;
          }
          if (op & 0x00040000 && !(op & 0x00400000)) c->ge = (v >> 16) & 15;
          return;
        }
        if ((op & 0x0FF000F0) == 0x01200070) fatal("BKPT at %s", elf_where(pc));
        if ((op & 0x0F8000F0) == 0x01800090) {  // LDREX STREX (word, doubleword, byte, halfword)
          uint32_t rn = (op >> 16) & 15, rd = (op >> 12) & 15, rt = op & 15, addr = r[rn];
          int type = (op >> 21) & 3;
          if (op & 0x00100000) {
            if (type == 1) { c->excl = addr; r[rd] = R32(addr); r[rd + 1] = R32(addr + 4); }
            else r[rd] = ldrex(c, addr, type == 0 ? 4 : type == 2 ? 1 : 2);
          } else if (strex_ok(c, addr)) {
            if (type == 0) R32(addr) = r[rt];
            else if (type == 1) { R32(addr) = r[rt]; R32(addr + 4) = r[rt + 1]; }
            else if (type == 2) R8(addr) = (uint8_t)r[rt];
            else R16(addr) = (uint16_t)r[rt];
            r[rd] = 0;
          } else r[rd] = 1;
          return;
        }
        if ((op & 0x0F0000F0) == 0x00000090) {  // multiplies
          uint32_t rd = (op >> 16) & 15, rn = (op >> 12) & 15, rs = (op >> 8) & 15, rm = op & 15;
          if ((op & 0x0FC00000) == 0x00000000) {  // MUL / MLA
            uint32_t res = r[rm] * r[rs] + ((op & 0x00200000) ? r[rn] : 0);
            r[rd] = res;
            if (op & 0x00100000) SETNZ(res);
            return;
          }
          if ((op & 0x0FF00000) == 0x00400000) {  // UMAAL
            uint64_t res = (uint64_t)r[rm] * r[rs] + r[rn] + r[rd];
            r[rn] = (uint32_t)res; r[rd] = (uint32_t)(res >> 32);
            return;
          }
          if ((op & 0x0FF00000) == 0x00600000) { r[rd] = r[rn] - r[rm] * r[rs]; return; }  // MLS
          if ((op & 0x0F800000) == 0x00800000) {  // UMULL UMLAL SMULL SMLAL
            uint64_t res;
            if (op & 0x00400000) res = (uint64_t)((int64_t)(int32_t)r[rm] * (int32_t)r[rs]);
            else res = (uint64_t)r[rm] * r[rs];
            if (op & 0x00200000) res += ((uint64_t)r[rd] << 32) | r[rn];
            r[rn] = (uint32_t)res; r[rd] = (uint32_t)(res >> 32);
            if (op & 0x00100000) { c->n = (uint32_t)(res >> 63); c->z = res == 0; }
            return;
          }
          undefined(c, pc, op, 0);
          return;
        }
        if ((op & 0x0FB00FF0) == 0x01000090) {  // SWP / SWPB
          uint32_t rm = op & 15, rd = (op >> 12) & 15, addr = r[(op >> 16) & 15];
          if (op & 0x00400000) { uint32_t t = R8(addr); R8(addr) = (uint8_t)r[rm]; r[rd] = t; }
          else { uint32_t t = R32(addr); R32(addr) = r[rm]; r[rd] = t; }
          return;
        }
        if ((op & 0x0E000090) == 0x00000090) {  // extra loads/stores
          uint32_t rn = (op >> 16) & 15, rd = (op >> 12) & 15;
          uint32_t base = rn == 15 ? pc + 8 : r[rn];
          uint32_t off = (op & 0x00400000) ? (((op >> 4) & 0xF0) | (op & 0xF)) : r[op & 15];
          uint32_t addr = (op & 0x00800000) ? base + off : base - off;
          uint32_t ea = (op & 0x01000000) ? addr : base;
          int wb = !(op & 0x01000000) || (op & 0x00200000);
          uint32_t sh = (op >> 5) & 3;
          if (op & 0x00100000) {
            uint32_t v = sh == 1 ? R16(ea) : sh == 2 ? (uint32_t)(int32_t)S8(ea) : (uint32_t)(int32_t)S16(ea);
            if (wb) r[rn] = addr;
            r[rd] = v;
            if (rd == 15) branch_x(c, v);
          } else if (sh == 1) {  // STRH
            R16(ea) = (uint16_t)(rd == 15 ? pc + 8 : r[rd]);
            if (wb) r[rn] = addr;
          } else if (sh == 2) {  // LDRD
            uint32_t lo = R32(ea), hi = R32(ea + 4);
            if (wb) r[rn] = addr;
            r[rd] = lo; r[rd + 1] = hi;
          } else {  // STRD
            R32(ea) = r[rd]; R32(ea + 4) = r[rd + 1];
            if (wb) r[rn] = addr;
          }
          return;
        }
      } else if ((op & 0x0FB00000) == 0x03000000) {  // MOVW / MOVT
        uint32_t rd = (op >> 12) & 15, imm = ((op >> 4) & 0xF000) | (op & 0xFFF);
        r[rd] = (op & 0x00400000) ? (r[rd] & 0xFFFF) | (imm << 16) : imm;
        return;
      } else if ((op & 0x0FB00000) == 0x03200000) {  // MSR imm (and the hints NOP YIELD WFE WFI SEV)
        if (op & 0x00080000 && !(op & 0x00400000)) {
          uint32_t imm = op & 0xFF, rot = (op >> 7) & 0x1E;
          uint32_t v = rot ? (imm >> rot) | (imm << (32 - rot)) : imm;
          c->n = v >> 31; c->z = (v >> 30) & 1; c->c = (v >> 29) & 1; c->v = (v >> 28) & 1; c->q = (v >> 27) & 1;
        }
        return;
      }

      // data processing
      uint32_t carry, b;
      uint32_t rn = (op >> 16) & 15, rd = (op >> 12) & 15;
      if (op & 0x02000000) {
        uint32_t imm = op & 0xFF, rot = (op >> 7) & 0x1E;
        if (rot) { b = (imm >> rot) | (imm << (32 - rot)); carry = b >> 31; }
        else { b = imm; carry = c->c; }
      } else {
        uint32_t rm = op & 15;
        if (op & 0x10) {
          uint32_t v = rm == 15 ? pc + 12 : r[rm];
          b = shift_reg(c, (op >> 5) & 3, v, r[(op >> 8) & 15], &carry);
        } else {
          uint32_t v = rm == 15 ? pc + 8 : r[rm];
          uint32_t amt = (op >> 7) & 31, type = (op >> 5) & 3;
          if (!amt && !type) { b = v; carry = c->c; }
          else b = shift_imm(c, type, v, amt, &carry);
        }
      }
      uint32_t a = rn == 15 ? pc + ((op & 0x02000010) == 0x10 ? 12 : 8) : r[rn];
      int s = (op >> 20) & 1;
      uint32_t res;
      switch ((op >> 21) & 15) {
        case 0: res = a & b; if (s) { SETNZ(res); c->c = carry; } break;
        case 1: res = a ^ b; if (s) { SETNZ(res); c->c = carry; } break;
        case 2: res = s ? sub_flags(c, a, b, 1) : a - b; break;
        case 3: res = s ? sub_flags(c, b, a, 1) : b - a; break;
        case 4: res = s ? add_flags(c, a, b, 0) : a + b; break;
        case 5: res = s ? add_flags(c, a, b, c->c) : a + b + c->c; break;
        case 6: res = s ? sub_flags(c, a, b, c->c) : a - b - !c->c; break;
        case 7: res = s ? sub_flags(c, b, a, c->c) : b - a - !c->c; break;
        case 8: res = a & b; SETNZ(res); c->c = carry; return;
        case 9: res = a ^ b; SETNZ(res); c->c = carry; return;
        case 10: sub_flags(c, a, b, 1); return;
        case 11: add_flags(c, a, b, 0); return;
        case 12: res = a | b; if (s) { SETNZ(res); c->c = carry; } break;
        case 13: res = b; if (s) { SETNZ(res); c->c = carry; } break;
        case 14: res = a & ~b; if (s) { SETNZ(res); c->c = carry; } break;
        default: res = ~b; if (s) { SETNZ(res); c->c = carry; } break;
      }
      if (rd == 15) branch_x(c, res);  // ARMv7: ALU writes to PC interwork
      else r[rd] = res;
      return;
    }

    case 2:
    case 3: {  // LDR / STR word and byte
      if ((op & 0x02000010) == 0x02000010) {
        if ((op & 0x0FF000F0) == 0x07F000F0) {  // hook
          int id = (int)(((op >> 8) & 0xFFF) << 4 | (op & 15));
          run_hook(c, id, pc);
          return;
        }
        if (!arm_media(c, op)) undefined(c, pc, op, 0);
        return;
      }
      uint32_t rn = (op >> 16) & 15, rd = (op >> 12) & 15;
      uint32_t base = rn == 15 ? pc + 8 : r[rn];
      uint32_t off;
      if (op & 0x02000000) {
        uint32_t carry, rm = op & 15;
        uint32_t amt = (op >> 7) & 31, type = (op >> 5) & 3;
        off = (!amt && !type) ? r[rm] : shift_imm(c, type, r[rm], amt, &carry);
      } else off = op & 0xFFF;
      uint32_t addr = (op & 0x00800000) ? base + off : base - off;
      uint32_t ea = (op & 0x01000000) ? addr : base;
      int wb = !(op & 0x01000000) || (op & 0x00200000);
      if (op & 0x00100000) {
        uint32_t v = (op & 0x00400000) ? R8(ea) : R32(ea);
        if (wb) r[rn] = addr;
        if (rd == 15) branch_x(c, v);
        else r[rd] = v;
      } else {
        uint32_t v = rd == 15 ? pc + 8 : r[rd];
        if (op & 0x00400000) R8(ea) = (uint8_t)v;
        else R32(ea) = v;
        if (wb) r[rn] = addr;
      }
      return;
    }

    case 4: {  // LDM / STM
      uint32_t rn = (op >> 16) & 15, list = op & 0xFFFF;
      uint32_t n = __builtin_popcount(list);
      uint32_t base = r[rn];
      uint32_t addr;
      int up = (op >> 23) & 1, pre = (op >> 24) & 1;
      if (up) addr = base + (pre ? 4 : 0);
      else addr = base - 4 * n + (pre ? 0 : 4);
      uint32_t newbase = up ? base + 4 * n : base - 4 * n;
      int wb = (op >> 21) & 1;
      if (op & 0x00100000) {
        uint32_t pcval = 0;
        int haspc = 0;
        if (wb && !(list & (1u << rn))) r[rn] = newbase;
        for (int i = 0; i < 16; i++) {
          if (!(list & (1u << i))) continue;
          uint32_t v = R32(addr); addr += 4;
          if (i == 15) { pcval = v; haspc = 1; }
          else r[i] = v;
        }
        if (haspc) branch_x(c, pcval);
      } else {
        for (int i = 0; i < 16; i++) {
          if (!(list & (1u << i))) continue;
          R32(addr) = i == 15 ? pc + 8 : r[i];
          addr += 4;
        }
        if (wb) r[rn] = newbase;
      }
      return;
    }

    case 5: {  // B / BL
      int32_t off = (int32_t)(op << 8) >> 6;
      if (op & 0x01000000) r[14] = pc + 4;
      r[15] = pc + 8 + off;
      return;
    }

    default:
      if ((op & 0x0F000000) == 0x0F000000) { svc(c, pc); return; }
      if (!cop_exec(c, op, pc + 8)) undefined(c, pc, op, 0);
      return;
  }
}

// ---------------------------------------------------------------------------
// Thumb state

static void thumb_step(Cpu *c, uint32_t pc, uint32_t op) {
  uint32_t *r = c->r;
  switch (op >> 11) {
    case 0: {  // LSL imm
      uint32_t rd = op & 7, v = r[(op >> 3) & 7], amt = (op >> 6) & 31;
      if (amt) { c->c = (v >> (32 - amt)) & 1; v <<= amt; }
      r[rd] = v; SETNZ(v);
      return;
    }
    case 1: {  // LSR imm
      uint32_t rd = op & 7, v = r[(op >> 3) & 7], amt = (op >> 6) & 31;
      if (!amt) { c->c = v >> 31; v = 0; }
      else { c->c = (v >> (amt - 1)) & 1; v >>= amt; }
      r[rd] = v; SETNZ(v);
      return;
    }
    case 2: {  // ASR imm
      uint32_t rd = op & 7, v = r[(op >> 3) & 7], amt = (op >> 6) & 31;
      if (!amt) { c->c = v >> 31; v = (uint32_t)((int32_t)v >> 31); }
      else { c->c = (v >> (amt - 1)) & 1; v = (uint32_t)((int32_t)v >> amt); }
      r[rd] = v; SETNZ(v);
      return;
    }
    case 3: {  // ADD/SUB reg/imm3
      uint32_t rd = op & 7, a = r[(op >> 3) & 7];
      uint32_t b = (op & 0x400) ? (op >> 6) & 7 : r[(op >> 6) & 7];
      r[rd] = (op & 0x200) ? sub_flags(c, a, b, 1) : add_flags(c, a, b, 0);
      return;
    }
    case 4: { uint32_t rd = (op >> 8) & 7, v = op & 0xFF; r[rd] = v; SETNZ(v); return; }  // MOV imm8
    case 5: sub_flags(c, r[(op >> 8) & 7], op & 0xFF, 1); return;  // CMP imm8
    case 6: { uint32_t rd = (op >> 8) & 7; r[rd] = add_flags(c, r[rd], op & 0xFF, 0); return; }
    case 7: { uint32_t rd = (op >> 8) & 7; r[rd] = sub_flags(c, r[rd], op & 0xFF, 1); return; }
    case 8: {
      if (!(op & 0x400)) {  // ALU
        uint32_t rd = op & 7, rs = (op >> 3) & 7;
        uint32_t a = r[rd], b = r[rs], res, carry;
        switch ((op >> 6) & 15) {
          case 0: res = a & b; r[rd] = res; SETNZ(res); return;
          case 1: res = a ^ b; r[rd] = res; SETNZ(res); return;
          case 2: res = shift_reg(c, 0, a, b, &carry); c->c = carry; r[rd] = res; SETNZ(res); return;
          case 3: res = shift_reg(c, 1, a, b, &carry); c->c = carry; r[rd] = res; SETNZ(res); return;
          case 4: res = shift_reg(c, 2, a, b, &carry); c->c = carry; r[rd] = res; SETNZ(res); return;
          case 5: r[rd] = add_flags(c, a, b, c->c); return;
          case 6: r[rd] = sub_flags(c, a, b, c->c); return;
          case 7: res = shift_reg(c, 3, a, b, &carry); c->c = carry; r[rd] = res; SETNZ(res); return;
          case 8: res = a & b; SETNZ(res); return;
          case 9: r[rd] = sub_flags(c, 0, b, 1); return;
          case 10: sub_flags(c, a, b, 1); return;
          case 11: add_flags(c, a, b, 0); return;
          case 12: res = a | b; r[rd] = res; SETNZ(res); return;
          case 13: res = a * b; r[rd] = res; SETNZ(res); return;
          case 14: res = a & ~b; r[rd] = res; SETNZ(res); return;
          default: res = ~b; r[rd] = res; SETNZ(res); return;
        }
      } else {  // hi register ops / BX
        uint32_t rd = (op & 7) | ((op >> 4) & 8), rm = (op >> 3) & 15;
        uint32_t v = rm == 15 ? pc + 4 : r[rm];
        switch ((op >> 8) & 3) {
          case 0: {
            uint32_t a = rd == 15 ? pc + 4 : r[rd];
            if (rd == 15) r[15] = (a + v) & ~1u;
            else r[rd] = a + v;
            return;
          }
          case 1: sub_flags(c, rd == 15 ? pc + 4 : r[rd], v, 1); return;
          case 2:
            if (rd == 15) r[15] = v & ~1u;
            else r[rd] = v;
            return;
          default:
            if (op & 0x80) r[14] = (pc + 2) | 1;
            branch_x(c, v);
            return;
        }
      }
    }
    case 9: r[(op >> 8) & 7] = R32(((pc + 4) & ~3u) + ((op & 0xFF) << 2)); return;  // LDR PC-rel
    case 10:
    case 11: {  // load/store register offset
      uint32_t rd = op & 7, addr = r[(op >> 3) & 7] + r[(op >> 6) & 7];
      switch ((op >> 9) & 7) {
        case 0: R32(addr) = r[rd]; return;
        case 1: R16(addr) = (uint16_t)r[rd]; return;
        case 2: R8(addr) = (uint8_t)r[rd]; return;
        case 3: r[rd] = (uint32_t)(int32_t)S8(addr); return;
        case 4: r[rd] = R32(addr); return;
        case 5: r[rd] = R16(addr); return;
        case 6: r[rd] = R8(addr); return;
        default: r[rd] = (uint32_t)(int32_t)S16(addr); return;
      }
    }
    case 12: R32(r[(op >> 3) & 7] + ((op >> 4) & 0x7C)) = r[op & 7]; return;
    case 13: r[op & 7] = R32(r[(op >> 3) & 7] + ((op >> 4) & 0x7C)); return;
    case 14: R8(r[(op >> 3) & 7] + ((op >> 6) & 31)) = (uint8_t)r[op & 7]; return;
    case 15: r[op & 7] = R8(r[(op >> 3) & 7] + ((op >> 6) & 31)); return;
    case 16: R16(r[(op >> 3) & 7] + ((op >> 5) & 0x3E)) = (uint16_t)r[op & 7]; return;
    case 17: r[op & 7] = R16(r[(op >> 3) & 7] + ((op >> 5) & 0x3E)); return;
    case 18: R32(r[13] + ((op & 0xFF) << 2)) = r[(op >> 8) & 7]; return;
    case 19: r[(op >> 8) & 7] = R32(r[13] + ((op & 0xFF) << 2)); return;
    case 20: r[(op >> 8) & 7] = ((pc + 4) & ~3u) + ((op & 0xFF) << 2); return;
    case 21: r[(op >> 8) & 7] = r[13] + ((op & 0xFF) << 2); return;
    case 22:
    case 23: {
      if ((op & 0xFF00) == 0xB000) {  // ADD/SUB SP
        uint32_t imm = (op & 0x7F) << 2;
        r[13] = (op & 0x80) ? r[13] - imm : r[13] + imm;
        return;
      }
      if ((op & 0xF600) == 0xB400) {  // PUSH / POP
        uint32_t list = op & 0xFF;
        if (!(op & 0x800)) {
          uint32_t n = __builtin_popcount(list) + ((op >> 8) & 1);
          uint32_t addr = r[13] - 4 * n;
          r[13] = addr;
          for (int i = 0; i < 8; i++) if (list & (1u << i)) { R32(addr) = r[i]; addr += 4; }
          if (op & 0x100) R32(addr) = r[14];
        } else {
          uint32_t addr = r[13];
          for (int i = 0; i < 8; i++) if (list & (1u << i)) { r[i] = R32(addr); addr += 4; }
          if (op & 0x100) { uint32_t v = R32(addr); addr += 4; r[13] = addr; branch_x(c, v); }
          else r[13] = addr;
        }
        return;
      }
      if ((op & 0xF500) == 0xB100) {  // CBZ / CBNZ
        uint32_t off = ((op >> 3) & 0x40) | ((op >> 2) & 0x3E);
        if ((r[op & 7] != 0) == ((op >> 11) & 1)) r[15] = pc + 4 + off;
        return;
      }
      if ((op & 0xFF00) == 0xB200) {  // SXTH SXTB UXTH UXTB
        uint32_t v = r[(op >> 3) & 7];
        switch ((op >> 6) & 3) {
          case 0: v = (uint32_t)(int32_t)(int16_t)v; break;
          case 1: v = (uint32_t)(int32_t)(int8_t)v; break;
          case 2: v &= 0xFFFF; break;
          default: v &= 0xFF; break;
        }
        r[op & 7] = v;
        return;
      }
      if ((op & 0xFF00) == 0xBA00) {  // REV REV16 REVSH
        uint32_t v = r[(op >> 3) & 7], k = (op >> 6) & 3;
        if (k == 2) { undefined(c, pc, op, 1); return; }
        r[op & 7] = k == 0 ? __builtin_bswap32(v) : k == 1 ? rev16(v) : revsh(v);
        return;
      }
      if ((op & 0xFF00) == 0xBF00) {  // IT, and the hints NOP YIELD WFE WFI SEV
        if (op & 15) c->it = op & 0xFF;
        return;
      }
      if ((op & 0xFFE0) == 0xB660 || (op & 0xFFF7) == 0xB650) return;  // CPS, SETEND LE
      if ((op & 0xFF00) == 0xBE00) fatal("BKPT at %s", elf_where(pc));
      undefined(c, pc, op, 1);
      return;
    }
    case 24: {  // STMIA
      uint32_t rn = (op >> 8) & 7, addr = r[rn];
      for (int i = 0; i < 8; i++) if (op & (1u << i)) { R32(addr) = r[i]; addr += 4; }
      r[rn] = addr;
      return;
    }
    case 25: {  // LDMIA
      uint32_t rn = (op >> 8) & 7, addr = r[rn];
      for (int i = 0; i < 8; i++) if (op & (1u << i)) { r[i] = R32(addr); addr += 4; }
      if (!(op & (1u << rn))) r[rn] = addr;
      return;
    }
    case 26:
    case 27: {
      uint32_t cc = (op >> 8) & 15;
      if (cc == 15) { svc(c, pc); return; }
      if (cc == 14) { undefined(c, pc, op, 1); return; }
      if (cond_ok(c, cc)) r[15] = pc + 4 + ((int32_t)(op << 24) >> 23);
      return;
    }
    case 28: r[15] = pc + 4 + ((int32_t)(op << 21) >> 20); return;
    default: undefined(c, pc, op, 1); return;   // 32-bit instructions are decoded by thumb32_step
  }
}

// 16-bit instructions that set the flags outside an IT block, and not inside one
static inline int t16_flags_outside_it(uint32_t op) {
  uint32_t top = op >> 11;
  if (top <= 4 || top == 6 || top == 7) return 1;   // shifts, ADD/SUB, MOV, ADD/SUB imm8 (not CMP imm8)
  if ((op >> 10) == 0x10) { uint32_t alu = (op >> 6) & 15; return alu != 8 && alu != 10 && alu != 11; }   // not TST CMP CMN
  return 0;
}

#ifdef EMU_TRACE   // debugging builds: the last instructions executed, for emu_where()
uint32_t trace_pc[256], trace_pos;
#define TRACE(pc) (trace_pc[trace_pos++ & 255] = (pc) | c->t)
#else
#define TRACE(pc) ((void)0)
#endif

void cpu_run(Cpu *c, uint64_t budget) {
  c->stop = 0;
  uint64_t end = c->icount + budget;
  while (!c->stop && !c->blocked && c->icount < end) {
    // a batch of instructions between checks of stop/blocked
    for (int i = 0; i < 64; i++) {
      uint32_t pc = c->r[15];
      TRACE(pc);
      if (pc >= 0xFFFF0000u) {   // not memory: the kernel helpers
        if (!kuser(c, pc)) fatal("jump to 0x%08x (outside memory) from %s", pc, elf_where(c->r[14]));
        continue;
      }
      if (c->t) {
        uint32_t op = R16(pc), it = c->it;
        if (it) c->it = (it & 7) ? ((it & 0xE0) | ((it << 1) & 0x1F)) : 0;   // ITAdvance
        if (op >= 0xE800) {
          c->r[15] = pc + 4;
          if (!it || cond_ok(c, it >> 4)) thumb32_step(c, pc, (op << 16) | R16(pc + 2));
        } else {
          c->r[15] = pc + 2;
          if (!it) thumb_step(c, pc, op);
          else if (cond_ok(c, it >> 4)) {
            if (t16_flags_outside_it(op)) {
              uint32_t n = c->n, z = c->z, cf = c->c, v = c->v;
              thumb_step(c, pc, op);
              c->n = n; c->z = z; c->c = cf; c->v = v;
            } else thumb_step(c, pc, op);
          }
        }
      } else {
        uint32_t op = R32(pc);
        c->r[15] = pc + 4;
        arm_step(c, pc, op);
      }
      if (c->stop | c->blocked) { c->icount += i + 1; return; }
    }
    c->icount += 64;
  }
}

// call a guest function from the host; the guest returns into hook 0
uint32_t guest_call(Cpu *c, uint32_t fn, int nargs, const uint32_t *args) {
  Cpu saved = *c;
  uint32_t sp = (c->r[13] - 64) & ~7u;  // keep a little red zone
  int nstack = nargs > 4 ? nargs - 4 : 0;
  sp -= ((nstack * 4) + 7) & ~7u;
  for (int i = 0; i < nstack; i++) R32(sp + 4 * i) = args[4 + i];
  for (int i = 0; i < 4 && i < nargs; i++) c->r[i] = args[i];
  c->r[13] = sp;
  c->r[14] = hook_stub_address(HOOK_RETURN_TO_HOST);
  c->t = fn & 1;
  c->r[15] = fn & ~1u;
  int was_running = c->thread ? c->thread->running : 0;
  if (c->thread) c->thread->running = 1;
  uint64_t slices = 0;
  double t0 = emscripten_get_now(), next_report = t0 + 10000;
  int idle = 0;
  for (;;) {
    extern void host_deliver_signals(Cpu *c);
    host_deliver_signals(c);
    cpu_run(c, 4000000);
    if (c->stop) break;
    if (++slices % 16 == 0 && emscripten_get_now() >= next_report) {   // a native call that does not return: where every thread is
      extern void host_thread_report(void);
      logf_("a native call (to %s) has run for %.0f s:", elf_where(fn), (emscripten_get_now() - t0) / 1000);
      host_thread_report();
      next_report += (next_report - t0);
    }
    // blocked (wait for another thread to make progress, then retry the call), or its time slice is over: the
    // other threads run for a while (threads interleave at instruction boundaries, as if preempted)
    int blocked = c->blocked;
    c->blocked = 0;
    extern uint64_t sched_progress;
    uint64_t before = sched_progress + c->icount;
    sched_run_others(blocked ? 2000000 : 500000);
    c->excl = 1;
    extern int host_any_sleeping(void);
    if (sched_progress + c->icount - before < 64 && !host_any_sleeping()) {   // every thread waits on another: say so once (a deadlock)
      if (++idle == 100000) {
        extern void host_thread_report(void);
        logf_("every native thread is waiting (a native call from %s cannot finish):", elf_where(fn));
        host_thread_report();
      }
    } else idle = 0;
  }
  if (c->thread) c->thread->running = was_running;
  uint32_t r0 = c->r[0], r1 = c->r[1];
  uint64_t ic = c->icount;
  *c = saved;
  c->icount = ic;
  c->r[0] = r0;
  c->r[1] = r1;
  return r0;
}
