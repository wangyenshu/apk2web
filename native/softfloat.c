// Replace libgcc's soft-float and division helpers inside libgame.so with host
// arithmetic. armeabi code calls these for every float operation, so running them
// natively instead of emulating ~50 ARM instructions each is the biggest speed-up.
#include "emu.h"
#include <math.h>
#include <string.h>
#include <limits.h>

static inline float uf(uint32_t u) { float f; memcpy(&f, &u, 4); return f; }
static inline double ud(uint64_t u) { double d; memcpy(&d, &u, 8); return d; }
static inline double d0(Cpu *c) { return ud((uint64_t)c->r[0] | ((uint64_t)c->r[1] << 32)); }
static inline double d1(Cpu *c) { return ud((uint64_t)c->r[2] | ((uint64_t)c->r[3] << 32)); }
#define F0 uf(c->r[0])
#define F1 uf(c->r[1])

static int32_t f2i(double x) {
  if (x != x) return 0;
  if (x >= 2147483647.0) return INT_MAX;
  if (x <= -2147483648.0) return INT_MIN;
  return (int32_t)x;
}
static uint32_t f2u(double x) {
  if (x != x || x <= 0) return 0;
  if (x >= 4294967295.0) return UINT_MAX;
  return (uint32_t)x;
}

static void fadd(Cpu *c) { retf(c, F0 + F1); }
static void fsub(Cpu *c) { retf(c, F0 - F1); }
static void frsub(Cpu *c) { retf(c, F1 - F0); }
static void fmul(Cpu *c) { retf(c, F0 * F1); }
static void fdiv(Cpu *c) { retf(c, F0 / F1); }
static void dadd(Cpu *c) { retd(c, d0(c) + d1(c)); }
static void dsub(Cpu *c) { retd(c, d0(c) - d1(c)); }
static void drsub(Cpu *c) { retd(c, d1(c) - d0(c)); }
static void dmul(Cpu *c) { retd(c, d0(c) * d1(c)); }
static void ddiv(Cpu *c) { retd(c, d0(c) / d1(c)); }

static void i2f(Cpu *c) { retf(c, (float)(int32_t)c->r[0]); }
static void ui2f(Cpu *c) { retf(c, (float)c->r[0]); }
static void i2d(Cpu *c) { retd(c, (double)(int32_t)c->r[0]); }
static void ui2d(Cpu *c) { retd(c, (double)c->r[0]); }
static void l2f(Cpu *c) { retf(c, (float)(int64_t)arg64(c, 0)); }
static void ul2f(Cpu *c) { retf(c, (float)arg64(c, 0)); }
static void l2d(Cpu *c) { retd(c, (double)(int64_t)arg64(c, 0)); }
static void ul2d(Cpu *c) { retd(c, (double)arg64(c, 0)); }
static void f2d(Cpu *c) { retd(c, (double)F0); }
static void d2f(Cpu *c) { retf(c, (float)d0(c)); }
static void f2iz(Cpu *c) { ret32(c, (uint32_t)f2i(F0)); }
static void f2uiz(Cpu *c) { ret32(c, f2u(F0)); }
static void d2iz(Cpu *c) { ret32(c, (uint32_t)f2i(d0(c))); }
static void d2uiz(Cpu *c) { ret32(c, f2u(d0(c))); }

// boolean comparisons (__aeabi_fcmpXX / dcmpXX)
static void fcmpeq(Cpu *c) { ret32(c, F0 == F1); }
static void fcmplt(Cpu *c) { ret32(c, F0 < F1); }
static void fcmple(Cpu *c) { ret32(c, F0 <= F1); }
static void fcmpge(Cpu *c) { ret32(c, F0 >= F1); }
static void fcmpgt(Cpu *c) { ret32(c, F0 > F1); }
static void fcmpun(Cpu *c) { ret32(c, F0 != F0 || F1 != F1); }
static void dcmpeq(Cpu *c) { ret32(c, d0(c) == d1(c)); }
static void dcmplt(Cpu *c) { ret32(c, d0(c) < d1(c)); }
static void dcmple(Cpu *c) { ret32(c, d0(c) <= d1(c)); }
static void dcmpge(Cpu *c) { ret32(c, d0(c) >= d1(c)); }
static void dcmpgt(Cpu *c) { ret32(c, d0(c) > d1(c)); }
static void dcmpun(Cpu *c) { double a = d0(c), b = d1(c); ret32(c, a != a || b != b); }

// three-way comparisons (libgcc __cmpsf2 family); 'unord' is the result for NaN
static inline int cmp3(double a, double b, int unord) { return a < b ? -1 : a > b ? 1 : a == b ? 0 : unord; }
static void sf_gex(Cpu *c) { ret32(c, (uint32_t)cmp3(F0, F1, -1)); }
static void sf_lex(Cpu *c) { ret32(c, (uint32_t)cmp3(F0, F1, 1)); }
static void sf_cmp(Cpu *c) { ret32(c, (uint32_t)cmp3(F0, F1, 1)); }
static void df_gex(Cpu *c) { ret32(c, (uint32_t)cmp3(d0(c), d1(c), -1)); }
static void df_lex(Cpu *c) { ret32(c, (uint32_t)cmp3(d0(c), d1(c), 1)); }
static void df_cmp(Cpu *c) { ret32(c, (uint32_t)cmp3(d0(c), d1(c), 1)); }

// flag-setting comparisons (__aeabi_cfcmple etc.): Z = equal, C = !(a < b); unordered -> Z=0 C=1
static inline void cflags(Cpu *c, double a, double b) {
  if (a < b) { c->z = 0; c->c = 0; }
  else if (a == b) { c->z = 1; c->c = 1; }
  else { c->z = 0; c->c = 1; }
  c->n = 0; c->v = 0;
}
static void cfcmple(Cpu *c) { cflags(c, F0, F1); }
static void cfrcmple(Cpu *c) { cflags(c, F1, F0); }
static void cdcmple(Cpu *c) { cflags(c, d0(c), d1(c)); }
static void cdrcmple(Cpu *c) { cflags(c, d1(c), d0(c)); }

// integer division
static void idiv(Cpu *c) {
  int32_t a = c->r[0], b = c->r[1];
  ret32(c, !b ? 0 : (a == INT_MIN && b == -1) ? (uint32_t)INT_MIN : (uint32_t)(a / b));
}
static void idivmod(Cpu *c) {
  int32_t a = c->r[0], b = c->r[1];
  if (!b) { c->r[0] = 0; c->r[1] = a; return; }
  if (a == INT_MIN && b == -1) { c->r[0] = (uint32_t)INT_MIN; c->r[1] = 0; return; }
  c->r[0] = (uint32_t)(a / b); c->r[1] = (uint32_t)(a % b);
}
static void uidiv(Cpu *c) { uint32_t b = c->r[1]; ret32(c, b ? c->r[0] / b : 0); }
static void uidivmod(Cpu *c) {
  uint32_t a = c->r[0], b = c->r[1];
  if (!b) { c->r[0] = 0; c->r[1] = a; return; }
  c->r[0] = a / b; c->r[1] = a % b;
}
static void uldivmod(Cpu *c) {
  uint64_t a = arg64(c, 0), b = arg64(c, 2);
  uint64_t q = b ? a / b : 0, r = b ? a % b : a;
  c->r[0] = (uint32_t)q; c->r[1] = (uint32_t)(q >> 32);
  c->r[2] = (uint32_t)r; c->r[3] = (uint32_t)(r >> 32);
}
static void divdi3(Cpu *c) {
  int64_t a = (int64_t)arg64(c, 0), b = (int64_t)arg64(c, 2);
  ret64(c, !b ? 0 : (a == LLONG_MIN && b == -1) ? (uint64_t)LLONG_MIN : (uint64_t)(a / b));
}
static void udivdi3(Cpu *c) { uint64_t a = arg64(c, 0), b = arg64(c, 2); ret64(c, b ? a / b : 0); }
static void clzsi2(Cpu *c) { uint32_t v = c->r[0]; ret32(c, v ? __builtin_clz(v) : 32); }

static void ldivmod(Cpu *c) {
  int64_t a = (int64_t)arg64(c, 0), b = (int64_t)arg64(c, 2);
  int64_t q = !b ? 0 : (a == LLONG_MIN && b == -1) ? LLONG_MIN : a / b, r = !b ? a : (b == -1 ? 0 : a % b);
  c->r[0] = (uint32_t)q; c->r[1] = (uint32_t)((uint64_t)q >> 32);
  c->r[2] = (uint32_t)r; c->r[3] = (uint32_t)((uint64_t)r >> 32);
}
static void lmul(Cpu *c) { ret64(c, arg64(c, 0) * arg64(c, 2)); }
static void llsl(Cpu *c) { uint32_t n = c->r[2]; ret64(c, n >= 64 ? 0 : arg64(c, 0) << n); }
static void llsr(Cpu *c) { uint32_t n = c->r[2]; ret64(c, n >= 64 ? 0 : arg64(c, 0) >> n); }
static void lasr(Cpu *c) { uint32_t n = c->r[2]; int64_t v = (int64_t)arg64(c, 0); ret64(c, (uint64_t)(n >= 64 ? (v < 0 ? -1 : 0) : v >> n)); }
static void lcmp(Cpu *c) { int64_t a = (int64_t)arg64(c, 0), b = (int64_t)arg64(c, 2); ret32(c, (uint32_t)(a < b ? -1 : a > b)); }
static void ulcmp(Cpu *c) { uint64_t a = arg64(c, 0), b = arg64(c, 2); ret32(c, (uint32_t)(a < b ? -1 : a > b)); }
static int64_t f2l(double x) { if (x != x) return 0; if (x >= 9.2233720368547758e18) return LLONG_MAX; if (x <= -9.2233720368547758e18) return LLONG_MIN; return (int64_t)x; }
static uint64_t f2ul(double x) { if (x != x || x <= 0) return 0; if (x >= 1.8446744073709552e19) return ULLONG_MAX; return (uint64_t)x; }
static void f2lz(Cpu *c) { ret64(c, (uint64_t)f2l(F0)); }
static void d2lz(Cpu *c) { ret64(c, (uint64_t)f2l(d0(c))); }
static void f2ulz(Cpu *c) { ret64(c, f2ul(F0)); }
static void d2ulz(Cpu *c) { ret64(c, f2ul(d0(c))); }
static void amemcpy(Cpu *c) { memcpy(G(c->r[0]), G(c->r[1]), c->r[2]); }
static void amemmove(Cpu *c) { memmove(G(c->r[0]), G(c->r[1]), c->r[2]); }
static void amemset(Cpu *c) { memset(G(c->r[0]), (int)c->r[2], c->r[1]); }   // (dest, n, c)
static void amemclr(Cpu *c) { memset(G(c->r[0]), 0, c->r[1]); }

static const struct { const char *name; HookFn fn; } table[] = {
  {"__aeabi_ldivmod", ldivmod}, {"__aeabi_lmul", lmul}, {"__aeabi_llsl", llsl}, {"__aeabi_llsr", llsr},
  {"__aeabi_lasr", lasr}, {"__aeabi_lcmp", lcmp}, {"__aeabi_ulcmp", ulcmp}, {"__aeabi_f2lz", f2lz},
  {"__aeabi_d2lz", d2lz}, {"__aeabi_f2ulz", f2ulz}, {"__aeabi_d2ulz", d2ulz},
  {"__aeabi_memcpy", amemcpy}, {"__aeabi_memcpy4", amemcpy}, {"__aeabi_memcpy8", amemcpy},
  {"__aeabi_memmove", amemmove}, {"__aeabi_memmove4", amemmove}, {"__aeabi_memmove8", amemmove},
  {"__aeabi_memset", amemset}, {"__aeabi_memset4", amemset}, {"__aeabi_memset8", amemset},
  {"__aeabi_memclr", amemclr}, {"__aeabi_memclr4", amemclr}, {"__aeabi_memclr8", amemclr},
  {"__fixsfdi", f2lz}, {"__fixdfdi", d2lz}, {"__fixunssfdi", f2ulz}, {"__fixunsdfdi", d2ulz},
  {"__aeabi_fadd", fadd}, {"__aeabi_fsub", fsub}, {"__aeabi_frsub", frsub}, {"__aeabi_fmul", fmul},
  {"__aeabi_fdiv", fdiv}, {"__aeabi_dadd", dadd}, {"__aeabi_dsub", dsub}, {"__aeabi_drsub", drsub},
  {"__aeabi_dmul", dmul}, {"__aeabi_ddiv", ddiv},
  {"__aeabi_i2f", i2f}, {"__aeabi_ui2f", ui2f}, {"__aeabi_i2d", i2d}, {"__aeabi_ui2d", ui2d},
  {"__aeabi_l2f", l2f}, {"__aeabi_ul2f", ul2f}, {"__aeabi_l2d", l2d}, {"__aeabi_ul2d", ul2d},
  {"__aeabi_f2d", f2d}, {"__aeabi_d2f", d2f}, {"__aeabi_f2iz", f2iz}, {"__aeabi_f2uiz", f2uiz},
  {"__aeabi_d2iz", d2iz}, {"__aeabi_d2uiz", d2uiz},
  {"__aeabi_fcmpeq", fcmpeq}, {"__aeabi_fcmplt", fcmplt}, {"__aeabi_fcmple", fcmple},
  {"__aeabi_fcmpge", fcmpge}, {"__aeabi_fcmpgt", fcmpgt}, {"__aeabi_fcmpun", fcmpun},
  {"__aeabi_dcmpeq", dcmpeq}, {"__aeabi_dcmplt", dcmplt}, {"__aeabi_dcmple", dcmple},
  {"__aeabi_dcmpge", dcmpge}, {"__aeabi_dcmpgt", dcmpgt}, {"__aeabi_dcmpun", dcmpun},
  {"__gtsf2", sf_gex}, {"__ltsf2", sf_lex}, {"__cmpsf2", sf_cmp},
  {"__gtdf2", df_gex}, {"__ltdf2", df_lex}, {"__cmpdf2", df_cmp},
  {"__aeabi_cfcmple", cfcmple}, {"__aeabi_cfrcmple", cfrcmple},
  {"__aeabi_cdcmple", cdcmple}, {"__aeabi_cdrcmple", cdrcmple},
  {"__aeabi_idiv", idiv}, {"__aeabi_idivmod", idivmod}, {"__aeabi_uidiv", uidiv},
  {"__aeabi_uidivmod", uidivmod}, {"__aeabi_uldivmod", uldivmod},
  {"__divdi3", divdi3}, {"__udivdi3", udivdi3}, {"__clzsi2", clzsi2},
};

/** helpers a library carries (libgcc linked in) run natively instead */
void softfloat_hooks(Lib *lib) {
  int n = 0;
  for (size_t i = 0; i < sizeof table / sizeof table[0]; i++) {
    uint32_t a = elf_sym(lib, table[i].name);
    if (!a) continue;
    hook_patch(a, hook_register(table[i].name, table[i].fn));
    n++;
  }
  if (n) logf_("%s: %d runtime helpers run natively", lib->name, n);
}
/** ... and libraries importing them (from libc or libgcc_s) get the host versions */
void softfloat_imports(void) {
  for (size_t i = 0; i < sizeof table / sizeof table[0]; i++) host_add_fn(table[i].name, table[i].fn);
}
