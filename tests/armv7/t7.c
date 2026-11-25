// ARMv7 conformance test for the apk2web emulator: integer (ARMv6/v7, Thumb-2), VFP and NEON code, as compilers
// emit it and through the ACLE/NEON intrinsics. run_all() writes result words; the same code runs under
// qemu-arm (t7_qemu.c) to produce the reference output. Every test is preceded by a marker word 0x7E570000|id.
#include <stdint.h>
#include <arm_acle.h>
#include <arm_neon.h>

#define NOINLINE __attribute__((noinline))
static uint32_t *OUT;
static int NOUT;
static void put(uint32_t v) { OUT[NOUT++] = v; }
static void put64(uint64_t v) { put((uint32_t)v); put((uint32_t)(v >> 32)); }
static void putf(float f) { uint32_t u; __builtin_memcpy(&u, &f, 4); put(u); }
static void putd(double d) { uint64_t u; __builtin_memcpy(&u, &d, 8); put64(u); }
static void putv(const void *p, int n) { const uint8_t *b = p; for (int i = 0; i < n; i += 4) { uint32_t w; __builtin_memcpy(&w, b + i, 4); put(w); } }
static void mark(int id) { put(0x7E570000u | (uint32_t)id); }

static uint32_t seed = 0x12345678;
static uint32_t rnd(void) { seed ^= seed << 13; seed ^= seed >> 17; seed ^= seed << 5; return seed; }
static float rndf(void) { return (float)(int32_t)(rnd() % 20001 - 10000) / 97.0f; }

static uint8_t B8[256], C8[256];
static int16_t S16[128], T16[128];
static int32_t I32[64], J32[64];
static float F[64], G[64];
static double D[32];

static void fill(void) {
  for (int i = 0; i < 256; i++) { B8[i] = (uint8_t)rnd(); C8[i] = (uint8_t)rnd(); }
  for (int i = 0; i < 128; i++) { S16[i] = (int16_t)rnd(); T16[i] = (int16_t)rnd(); }
  for (int i = 0; i < 64; i++) { I32[i] = (int32_t)rnd(); J32[i] = (int32_t)rnd(); F[i] = rndf(); G[i] = rndf(); }
  for (int i = 0; i < 32; i++) D[i] = (double)rndf() * 3.25 + (double)(int32_t)rnd() / 1e6;
}

// --------------------------------------------------------------------------- integer code
struct bits { unsigned a : 3, b : 7, c : 13, d : 9; signed e : 5, f : 11, g : 16; };
NOINLINE static void t_bitfields(void) {
  struct bits x = {0};
  for (int i = 0; i < 16; i++) {
    x.a += I32[i]; x.b ^= I32[i] >> 3; x.c += J32[i]; x.d = x.d * 3 + i;
    x.e = (int)x.e - (I32[i] & 7); x.f += J32[i] >> 9; x.g -= I32[i] >> 5;
    put(x.a | x.b << 3 | (uint32_t)x.c << 10); put((uint32_t)x.d | (uint32_t)(x.e & 31) << 9); put((uint32_t)x.f ^ (uint32_t)x.g);
  }
}
NOINLINE static void t_arith(void) {
  uint32_t h = 0;
  for (int i = 0; i < 64; i++) {
    uint32_t a = (uint32_t)I32[i], b = (uint32_t)J32[i] | 1;
    h = h * 31 + a / b + (uint32_t)((int32_t)a / (int32_t)b) + a % b + (uint32_t)((int32_t)a % (int32_t)(b | 0x10));
    h ^= (uint32_t)__builtin_clz(a | 1) + (uint32_t)__builtin_ctz(b) + __builtin_bitreverse32(a) + __builtin_bswap32(b);
    h += (uint32_t)(((uint64_t)a * b) >> 32) + (uint32_t)(((int64_t)(int32_t)a * (int32_t)b) >> 32);
    h -= a * b - h;   // MLS
    uint64_t w = (uint64_t)a * b + h; w += (uint64_t)h * a;   // UMLAL
    h += (uint32_t)(w >> 29) + (uint32_t)w;
    h += (uint16_t)(a >> 3) + (int8_t)b + (uint32_t)(int16_t)(a >> 9);
    h = (h << 7) | (h >> 25);
    h += (a & 0xFFFF0000u) | (b & 0xFFFF);   // PKHBT-like
    put(h);
  }
}
NOINLINE static int t_select1(int a, int b, int c) {   // IT blocks with conditional arithmetic
  int r = 0;
  if (a < b) r = a + c; else r = b - c;
  if (r == c) r <<= 2;
  r = (a > 0 && b < 0) ? r ^ a : r | b;
  if ((unsigned)a > (unsigned)c) r += 7; else r -= 3;
  return r < 0 ? -r : r;
}
NOINLINE static void t_select(void) {
  for (int i = 0; i < 48; i++) {
    int a = I32[i] >> (i & 15), b = J32[i] >> (i % 13), c = (int)(rnd() % 100) - 50;
    put((uint32_t)t_select1(a, b, c));
    put((uint32_t)(a < b ? a : b) + (uint32_t)(a > c ? a : c) + (uint32_t)(b == c));
  }
}
NOINLINE static int t_switch1(int k, int v) {
  switch (k) {
    case 0: return v + 1; case 1: return v * 3; case 2: return v - 7; case 3: return v ^ 0x55; case 4: return v << 2;
    case 5: return v >> 1; case 6: return ~v; case 7: return v & 0xF0F0; case 8: return v | 0x0F; case 9: return -v;
    case 10: return v * v; case 11: return v + 1000; case 12: return v - 1000; case 13: return v / 3; case 14: return v % 7;
    default: return 42;
  }
}
NOINLINE int t_switch2(int k, int v);
#define C4(n) case n: v = v * (n + 3) + (n ^ 0x5A); v ^= v >> 3; v += (v << 4) ^ n; break;
#define C16(n) C4(n) C4(n + 1) C4(n + 2) C4(n + 3)
NOINLINE int t_switch2(int k, int v) {   // a large switch: TBH with long distances
  switch (k) {
    C16(0) C16(4) C16(8) C16(12) C16(16) C16(20) C16(24) C16(28) C16(32) C16(36) C16(40) C16(44) C16(48) C16(52) C16(56) C16(60)
    C16(64) C16(68) C16(72) C16(76) C16(80) C16(84) C16(88) C16(92) C16(96) C16(100) C16(104) C16(108) C16(112) C16(116) C16(120) C16(124)
    default: v = -1;
  }
  return v;
}
NOINLINE static void t_switch(void) {
  for (int i = 0; i < 40; i++) put((uint32_t)t_switch1((int)(rnd() % 17), I32[i]));
  for (int i = 0; i < 40; i++) put((uint32_t)t_switch2((int)(rnd() % 140), J32[i]));
}
NOINLINE static void t_atomic(void) {
  static int32_t a32; static int64_t a64; static uint8_t a8; static uint16_t a16;
  a32 = 5; a64 = 7; a8 = 3; a16 = 9;
  for (int i = 0; i < 20; i++) {
    put((uint32_t)__atomic_fetch_add(&a32, I32[i], __ATOMIC_SEQ_CST));
    put((uint32_t)__atomic_fetch_xor(&a8, (uint8_t)J32[i], __ATOMIC_SEQ_CST));
    put((uint32_t)__atomic_fetch_or(&a16, (uint16_t)I32[i], __ATOMIC_SEQ_CST));
    put64((uint64_t)__atomic_fetch_add(&a64, (int64_t)I32[i] * 1000, __ATOMIC_SEQ_CST));
    int32_t exp = a32;
    put((uint32_t)__atomic_compare_exchange_n(&a32, &exp, exp + 1, 0, __ATOMIC_SEQ_CST, __ATOMIC_SEQ_CST));
    exp = 12345;
    put((uint32_t)__atomic_compare_exchange_n(&a32, &exp, 1, 0, __ATOMIC_SEQ_CST, __ATOMIC_SEQ_CST));
    put((uint32_t)exp);
    put((uint32_t)__atomic_exchange_n(&a16, (uint16_t)i, __ATOMIC_SEQ_CST));
  }
  put((uint32_t)a32); put64((uint64_t)a64); put(a8); put(a16);
}
NOINLINE static void t_acle(void) {
  for (int i = 0; i < 32; i++) {
    int32_t a = I32[i], b = J32[i], c = I32[63 - i];
    put((uint32_t)__qadd(a, b)); put((uint32_t)__qsub(a, b)); put((uint32_t)__qdbl(a));
    put((uint32_t)__ssat(a >> 8, 12)); put(__usat(a >> 10, 9));
    put((uint32_t)__smlabb(a, b, c)); put((uint32_t)__smultt(a, b)); put((uint32_t)__smulwb(a, b)); put((uint32_t)__smlawt(a, b, c));
    put((uint32_t)__sadd16(a, b)); put((uint32_t)__qsub16(a, b)); put((uint32_t)__shasx(a, b)); put((uint32_t)__uadd8(a, b));
    put((uint32_t)__uqsub8(a, b)); put((uint32_t)__uhadd16(a, b)); put((uint32_t)__ssax(a, b)); put((uint32_t)__usub16(a, b));
    put(__sel(a, b));   // GE from the previous instruction
    put((uint32_t)__qadd8(a, b)); put((uint32_t)__shsub8(a, b)); put((uint32_t)__uqasx(a, b)); put((uint32_t)__uhsax(a, b));
    put(__usad8(a, b)); put(__usada8(a, b, (uint32_t)c));
    put((uint32_t)__smlad(a, b, c)); put((uint32_t)__smuadx(a, b)); put((uint32_t)__smlsd(a, b, c)); put((uint32_t)__smusdx(a, b));
    put64((uint64_t)__smlald(a, b, (int64_t)c * 77)); put64((uint64_t)__smlsldx(a, b, (int64_t)c));
    put((uint32_t)__ssat16(a, 9)); put(__usat16(a, 7));
    put(__sxtab16(a, b)); put(__uxtb16(a)); put(__rev16(a)); put((uint32_t)__revsh((int16_t)a)); put(__rbit(b));
    put((uint32_t)(((int64_t)a * b + 0x80000000LL) >> 32));   // SMMULR
    put((uint32_t)(((int64_t)c << 32) - (int64_t)a * b) >> 0);
  }
}
NOINLINE static uint32_t t_crc1(const uint8_t *p, int n) {
  uint32_t crc = 0xFFFFFFFFu;
  for (int i = 0; i < n; i++) { crc ^= p[i]; for (int k = 0; k < 8; k++) crc = (crc >> 1) ^ (0xEDB88320u & -(crc & 1)); }
  return ~crc;
}
NOINLINE static void t_memory(void) {
  uint8_t buf[300];
  for (int i = 0; i < 300; i++) buf[i] = (uint8_t)(i * 7);
  __builtin_memcpy(buf + 3, B8, 200);
  __builtin_memmove(buf + 10, buf + 1, 150);
  __builtin_memset(buf + 250, 0x5A, 33);
  put(t_crc1(buf, 300));
  struct { int64_t a; int32_t b; int16_t c; int8_t d; double e; } s1, s2;
  s1.a = (int64_t)I32[0] << 20; s1.b = I32[1]; s1.c = (int16_t)I32[2]; s1.d = (int8_t)I32[3]; s1.e = D[0];
  s2 = s1; s2.b++;
  put64((uint64_t)s2.a); put((uint32_t)s2.b); put((uint32_t)s2.c); put((uint32_t)s2.d); putd(s2.e);
  uint16_t h[40];
  for (int i = 0; i < 40; i++) h[i] = (uint16_t)(B8[i] * 300 + C8[i]);
  uint32_t acc = 0;
  for (int i = 0; i < 39; i++) acc = acc * 17 + h[i] + (uint32_t)(int8_t)B8[i + 1] + (uint32_t)(int16_t)h[i + 1];
  put(acc);
}
NOINLINE static void t_long(void) {
  for (int i = 0; i < 24; i++) {
    uint64_t a = (uint64_t)(uint32_t)I32[i] << 32 | (uint32_t)J32[i], b = (uint64_t)(uint32_t)J32[i + 1] << 17 | 3;
    int64_t s = (int64_t)a;
    put64(a + b); put64(a - b); put64(a * b); put64(a << (i % 40)); put64(a >> (i % 50)); put64((uint64_t)(s >> (i % 63)));
    put((uint32_t)(a < b) | (uint32_t)(s < (int64_t)b) << 1 | (uint32_t)(a == b) << 2);
    put64((uint64_t)__builtin_clzll(a | 1) + (uint64_t)__builtin_popcountll(b));
  }
}

// --------------------------------------------------------------------------- VFP code
NOINLINE static void t_vfp(void) {
  float s = 0; double d = 0;
  for (int i = 0; i < 64; i++) {
    float a = F[i], b = G[i];
    s = s * 0.75f + a * b - a / (b == 0 ? 1 : b);
    s += __builtin_sqrtf(a * a + 1) - __builtin_fabsf(b);
    s = __builtin_fmaf(a, b, s) - (float)(int32_t)(a * 100);
    putf(s); put((uint32_t)(int32_t)(s * 3.5f)); put((uint32_t)(s > a) | (uint32_t)(s <= b) << 1 | (uint32_t)(a == b) << 2);
    put((uint32_t)(s < 0 ? 0 : s > 4e9f ? 0 : s));   // float -> unsigned
    d = d * 0.5 + (double)a * D[i % 32] - D[(i + 3) % 32] / ((double)b + 0.5);
    d += __builtin_sqrt(__builtin_fabs(d) + 2.0) + (double)(int32_t)I32[i] / 1024.0 + (double)(uint32_t)J32[i] / 4096.0;
    d = __builtin_fma(d, 0.125, (double)s);
    putd(d); putf((float)d); putd((double)s); put((uint32_t)(int32_t)(d / 1000.0));
    put((uint32_t)(d < (double)s) | (uint32_t)(d >= 0) << 1);
  }
  float fx = 0.0f;
  for (int i = 0; i < 16; i++) { fx = fx * 2.0f - 1.25f; fx = -fx; putf(fx); putf(-fx * 0.5f); }
  putf(1.0f); putf(-0.5f); putf(31.0f); putd(0.1875); putd(-17.0);
}

// --------------------------------------------------------------------------- auto-vectorized loops
NOINLINE static void t_vec_auto(void) {
  static uint8_t o8[256]; static int16_t o16[128]; static int32_t o32[64]; static float of[64]; static uint16_t w16[256];
  for (int i = 0; i < 256; i++) o8[i] = (uint8_t)((B8[i] + C8[i] + 1) >> 1);
  putv(o8, 256);
  for (int i = 0; i < 256; i++) o8[i] = B8[i] > C8[i] ? B8[i] - C8[i] : C8[i] - B8[i];
  putv(o8, 256);
  for (int i = 0; i < 256; i++) { int v = B8[i] + C8[i]; o8[i] = v > 255 ? 255 : (uint8_t)v; }
  putv(o8, 256);
  for (int i = 0; i < 128; i++) o16[i] = (int16_t)(S16[i] * T16[i] + (S16[i] >> 3));
  putv(o16, 256);
  for (int i = 0; i < 128; i++) { int v = S16[i] + T16[i]; o16[i] = (int16_t)(v > 32767 ? 32767 : v < -32768 ? -32768 : v); }
  putv(o16, 256);
  for (int i = 0; i < 64; i++) o32[i] = I32[i] * J32[i] + (I32[i] >> 7) - (J32[i] << 3);
  putv(o32, 256);
  for (int i = 0; i < 64; i++) o32[i] = I32[i] < J32[i] ? I32[i] : J32[i];
  putv(o32, 256);
  for (int i = 0; i < 64; i++) o32[i] = (I32[i] & 0xFF00FF) ^ (~J32[i] | 0x101);
  putv(o32, 256);
  for (int i = 0; i < 64; i++) of[i] = F[i] * G[i] + of[i] * 0.5f;
  putv(of, 256);
  for (int i = 0; i < 64; i++) of[i] = F[i] > G[i] ? F[i] - G[i] : G[i] * 2.0f;
  putv(of, 256);
  for (int i = 0; i < 64; i++) o32[i] = (int32_t)(F[i] * 16.0f);
  putv(o32, 256);
  for (int i = 0; i < 64; i++) of[i] = (float)I32[i] * (1.0f / 65536.0f);
  putv(of, 256);
  for (int i = 0; i < 256; i++) w16[i] = (uint16_t)(B8[i] * C8[i]);
  putv(w16, 512);
  uint32_t sum = 0; for (int i = 0; i < 256; i++) sum += B8[i] * 3u + C8[i];
  put(sum);
  int32_t ssum = 0; for (int i = 0; i < 128; i++) ssum += S16[i] * T16[i];
  put((uint32_t)ssum);
  float fs = 0; for (int i = 0; i < 64; i++) fs += F[i] * G[i];
  putf(fs);
  int32_t mx = I32[0]; for (int i = 1; i < 64; i++) mx = I32[i] > mx ? I32[i] : mx;
  put((uint32_t)mx);
  for (int i = 0; i < 64; i++) o8[i] = (uint8_t)(I32[i] >> 24);
  putv(o8, 64);
  for (int i = 0; i < 80; i++) {   // interleaved RGB -> gray (VLD3)
    uint8_t r = B8[3 * i % 240], g = B8[(3 * i + 1) % 240], b = B8[(3 * i + 2) % 240];
    o8[i] = (uint8_t)((r * 77 + g * 150 + b * 29) >> 8);
  }
  putv(o8, 80);
  for (int i = 0; i < 64; i++) { o8[2 * i] = B8[i]; o8[2 * i + 1] = C8[i]; }   // interleave (VST2 / VZIP)
  putv(o8, 128);
  for (int i = 0; i < 64; i++) o16[i] = (int16_t)(((int32_t)S16[i] * T16[i]) >> 15);
  putv(o16, 128);
  for (int i = 0; i < 64; i++) { uint32_t v = (uint32_t)I32[i]; o32[i] = (int32_t)((v >> 24) | ((v >> 8) & 0xFF00) | ((v << 8) & 0xFF0000) | (v << 24)); }
  putv(o32, 256);
}

// --------------------------------------------------------------------------- NEON intrinsics
#define PQ8(x) do { uint8x16_t _t = (x); putv(&_t, 16); } while (0)
#define PD8(x) do { uint8x8_t _t = (x); putv(&_t, 8); } while (0)
#define PQ(x) do { __typeof__(x) _t = (x); putv(&_t, 16); } while (0)
#define PD(x) do { __typeof__(x) _t = (x); putv(&_t, 8); } while (0)
NOINLINE static void t_neon_int(void) {
  for (int k = 0; k < 4; k++) {
    const uint8_t *p = B8 + 32 * k, *q = C8 + 32 * k;
    uint8x16_t a = vld1q_u8(p), b = vld1q_u8(q);
    int8x16_t sa = vreinterpretq_s8_u8(a), sb = vreinterpretq_s8_u8(b);
    int16x8_t h = vld1q_s16(S16 + 8 * k), g = vld1q_s16(T16 + 8 * k);
    uint16x8_t uh = vreinterpretq_u16_s16(h), ug = vreinterpretq_u16_s16(g);
    int32x4_t w = vld1q_s32(I32 + 4 * k), x = vld1q_s32(J32 + 4 * k);
    uint32x4_t uw = vreinterpretq_u32_s32(w), ux = vreinterpretq_u32_s32(x);
    int64x2_t l = vreinterpretq_s64_s32(w), m = vreinterpretq_s64_s32(x);
    PQ8(vaddq_u8(a, b)); PQ8(vsubq_u8(a, b)); PQ8(vqaddq_u8(a, b)); PQ(vqsubq_s8(sa, sb)); PQ8(vhaddq_u8(a, b)); PQ(vrhaddq_s8(sa, sb));
    PQ(vhsubq_s16(h, g)); PQ8(vmaxq_u8(a, b)); PQ(vminq_s8(sa, sb)); PQ8(vabdq_u8(a, b)); PQ(vabaq_s16(h, g, h));
    PQ8(vcgtq_u8(a, b)); PQ(vcgeq_s16(h, g)); PQ(vceqq_s32(w, w)); PQ8(vtstq_u8(a, b));
    PQ(vmulq_s16(h, g)); PQ(vmlaq_s32(w, x, w)); PQ(vmlsq_u16(uh, ug, uh)); PQ(vmulq_p8(vreinterpretq_p8_u8(a), vreinterpretq_p8_u8(b)));
    PQ(vqdmulhq_s16(h, g)); PQ(vqrdmulhq_s32(w, x));
    PQ(vshlq_s16(h, vreinterpretq_s16_u16(vandq_u16(ug, vdupq_n_u16(0x1F))))); PQ(vqshlq_u8(a, vreinterpretq_s8_u8(vandq_u8(b, vdupq_n_u8(7)))));
    PQ(vrshlq_s32(w, vsubq_s32(vdupq_n_s32(0), vandq_s32(x, vdupq_n_s32(31))))); PQ(vqrshlq_s16(h, vdupq_n_s16(-3)));
    PQ(vshlq_s64(l, vdupq_n_s64(5))); PQ(vqaddq_s64(l, m)); PQ(vsubq_s64(l, m));
    PQ8(vandq_u8(a, b)); PQ8(vbicq_u8(a, b)); PQ8(vorrq_u8(a, b)); PQ8(vornq_u8(a, b)); PQ8(veorq_u8(a, b));
    PQ8(vbslq_u8(vcgtq_u8(a, b), a, b)); PQ8(vmvnq_u8(a));
    PD(vpadd_s16(vget_low_s16(h), vget_high_s16(g))); PD(vpmax_u8(vget_low_u8(a), vget_high_u8(b))); PD(vpmin_s32(vget_low_s32(w), vget_high_s32(x)));
    PQ(vpaddlq_u8(a)); PQ(vpadalq_s16(w, h)); PQ(vpaddlq_u32(uw));
    PQ(vaddl_u8(vget_low_u8(a), vget_high_u8(b))); PQ(vaddw_s16(w, vget_low_s16(h))); PQ(vsubl_s16(vget_low_s16(h), vget_high_s16(g)));
    PQ(vmull_u8(vget_low_u8(a), vget_high_u8(b))); PQ(vmull_s32(vget_low_s32(w), vget_high_s32(x))); PQ(vmlal_s16(w, vget_low_s16(h), vget_low_s16(g)));
    PQ(vmlsl_u16(uw, vget_high_u16(uh), vget_low_u16(ug))); PQ(vqdmull_s16(vget_low_s16(h), vget_high_s16(g))); PQ(vqdmlal_s32(l, vget_low_s32(w), vget_high_s32(x)));
    PQ(vabdl_u8(vget_low_u8(a), vget_low_u8(b))); PQ(vabal_s8(h, vget_low_s8(sa), vget_high_s8(sb))); PQ(vmull_p8(vget_low_p8(vreinterpretq_p8_u8(a)), vget_low_p8(vreinterpretq_p8_u8(b))));
    PD(vaddhn_s32(w, x)); PD(vraddhn_u16(uh, ug)); PD(vsubhn_s64(l, m)); PD(vrsubhn_s16(h, g));
    PQ(vmulq_lane_s16(h, vget_low_s16(g), 3)); PQ(vmlaq_lane_s32(w, x, vget_high_s32(w), 1)); PQ(vmull_lane_s16(vget_low_s16(h), vget_high_s16(g), 2));
    PQ(vqdmulhq_lane_s32(w, vget_low_s32(x), 0)); PQ(vqrdmulhq_lane_s16(h, vget_low_s16(g), 1)); PQ(vmlsq_lane_u16(uh, ug, vget_high_u16(uh), 0));
    PQ(vqdmlsl_lane_s16(w, vget_low_s16(h), vget_high_s16(g), 3)); PQ(vmlal_lane_u32(vreinterpretq_u64_u32(uw), vget_low_u32(ux), vget_high_u32(uw), 1));
    PQ(vshrq_n_s16(h, 3)); PQ(vshrq_n_u8(a, 7)); PQ(vsraq_n_u32(uw, ux, 9)); PQ(vrshrq_n_s32(w, 31)); PQ(vrsraq_n_u16(uh, ug, 1)); PQ(vshrq_n_s64(l, 40));
    PQ(vshlq_n_u8(a, 3)); PQ(vsliq_n_u16(uh, ug, 5)); PQ(vsriq_n_u32(uw, ux, 12)); PQ(vqshlq_n_s8(sa, 2)); PQ(vqshluq_n_s16(h, 4)); PQ(vqshlq_n_u32(uw, 1));
    PD(vshrn_n_u16(uh, 5)); PD(vrshrn_n_s32(w, 16)); PD(vqshrn_n_s16(h, 2)); PD(vqrshrn_n_u32(uw, 7)); PD(vqshrun_n_s16(h, 1)); PD(vqrshrun_n_s32(w, 13));
    PQ(vshll_n_u8(vget_low_u8(a), 3)); PQ(vshll_n_s16(vget_low_s16(h), 16)); PQ(vmovl_s8(vget_high_s8(sa))); PQ(vmovl_u16(vget_low_u16(uh)));
    PD(vmovn_s32(w)); PD(vqmovn_s16(h)); PD(vqmovn_u32(uw)); PD(vqmovun_s16(h)); PD(vqmovn_s64(l));
    PQ(vabsq_s8(sa)); PQ(vnegq_s16(h)); PQ(vqabsq_s32(w)); PQ(vqnegq_s8(sa)); PQ(vclsq_s16(h)); PQ(vclzq_u8(a)); PQ(vclzq_s32(w)); PQ(vcntq_u8(a));
    PQ(vcgtq_s8(sa, vdupq_n_s8(0))); PQ(vcleq_s16(h, vdupq_n_s16(0))); PQ(vceqq_s32(vandq_s32(w, vdupq_n_s32(3)), vdupq_n_s32(0))); PQ(vcltq_s8(sa, vdupq_n_s8(0)));
    PQ(vrev64q_u8(a)); PQ(vrev32q_u16(uh)); PQ(vrev16q_u8(b)); PQ(vrev64q_s32(w));
    PQ8(vextq_u8(a, b, 5)); PQ(vextq_s32(w, x, 3)); PD(vext_u16(vget_low_u16(uh), vget_high_u16(ug), 1));
    { uint8x16x2_t z = vzipq_u8(a, b); PQ8(z.val[0]); PQ8(z.val[1]); }
    { int16x8x2_t z = vuzpq_s16(h, g); PQ(z.val[0]); PQ(z.val[1]); }
    { int32x4x2_t z = vtrnq_s32(w, x); PQ(z.val[0]); PQ(z.val[1]); }
    { uint8x8x2_t z = vtrn_u8(vget_low_u8(a), vget_low_u8(b)); PD(z.val[0]); PD(z.val[1]); }
    { uint16x4x2_t z = vzip_u16(vget_low_u16(uh), vget_high_u16(ug)); PD(z.val[0]); PD(z.val[1]); }
    { uint8x8x2_t t = {{vget_low_u8(a), vget_high_u8(a)}}; PD(vtbl2_u8(t, vand_u8(vget_low_u8(b), vdup_n_u8(31)))); PD(vtbx2_u8(vget_high_u8(b), t, vget_low_u8(b))); }
    { uint8x8x4_t t = {{vget_low_u8(a), vget_high_u8(a), vget_low_u8(b), vget_high_u8(b)}}; PD(vtbl4_u8(t, vand_u8(vget_high_u8(b), vdup_n_u8(63)))); }
    PD(vtbl1_u8(vget_low_u8(a), vget_high_u8(b)));
    PQ(vdupq_lane_u8(vget_low_u8(a), 6)); PQ(vdupq_lane_s16(vget_high_s16(h), 2)); PQ(vdupq_lane_s32(vget_low_s32(w), 1));
    PQ(vdupq_n_u8(p[3])); PQ(vdupq_n_s16(h[1])); PQ(vdupq_n_s32(I32[k]));
    put(vgetq_lane_u8(a, 13)); put((uint32_t)vgetq_lane_s16(h, 5)); put((uint32_t)vgetq_lane_s8(sa, 2)); put(vgetq_lane_u16(ug, 7)); put((uint32_t)vgetq_lane_s32(x, 3));
    put64(vgetq_lane_u64(vreinterpretq_u64_u8(a), 1));
    PQ(vsetq_lane_u8(0x77, a, 9)); PQ(vsetq_lane_s16(-5, h, 6)); PQ(vsetq_lane_s32(123456, w, 2));
    PQ(vmovq_n_u32(0xDEADBEEF)); PQ(vmovq_n_u16(0x1234)); PQ(vdupq_n_s8(-3)); PD(vmov_n_u64(0x0102030405060708ULL)); PQ(vdupq_n_u32(0xFF0000FF));
    PQ(vcombine_s16(vget_high_s16(h), vget_low_s16(g)));
  }
}
NOINLINE static void t_neon_mem(void) {
  static uint8_t buf[512];
  for (int i = 0; i < 512; i++) buf[i] = (uint8_t)(i * 13 + 1);
  { uint8x8x2_t v = vld2_u8(B8); PD(v.val[0]); PD(v.val[1]); vst2_u8(buf + 3, v); }
  { uint8x16x2_t v = vld2q_u8(B8 + 5); PQ(v.val[0]); PQ(v.val[1]); vst2q_u8(buf + 40, v); }
  { uint8x8x3_t v = vld3_u8(C8 + 1); PD(v.val[0]); PD(v.val[1]); PD(v.val[2]); vst3_u8(buf + 80, v); }
  { int16x8x3_t v = vld3q_s16(S16 + 2); PQ(v.val[0]); PQ(v.val[1]); PQ(v.val[2]); vst3q_s16((int16_t *)(buf + 120), v); }
  { uint16x4x4_t v = vld4_u16((const uint16_t *)T16); PD(v.val[0]); PD(v.val[3]); vst4_u16((uint16_t *)(buf + 200), v); }
  { int32x4x4_t v = vld4q_s32(I32 + 1); PQ(v.val[0]); PQ(v.val[1]); PQ(v.val[2]); PQ(v.val[3]); vst4q_s32((int32_t *)(buf + 256), v); }
  { float32x4x2_t v = vld2q_f32(F); PQ(v.val[0]); PQ(v.val[1]); vst2q_f32((float *)(buf + 324), v); }
  { uint8x16_t v = vld1q_u8(B8); v = vld1q_lane_u8(C8 + 7, v, 3); PQ(v); vst1q_lane_u8(buf + 400, v, 11); }
  { int16x4_t v = vld1_dup_s16(S16 + 9); PD(v); int32x4_t q = vld1q_dup_s32(I32 + 5); PQ(q); vst1q_lane_s32((int32_t *)(buf + 404), q, 2); }
  { uint8x8x2_t v = vld2_dup_u8(C8 + 40); PD(v.val[0]); PD(v.val[1]); }
  { int16x4x3_t v = vld3_dup_s16(T16 + 2); PD(v.val[0]); PD(v.val[2]); }
  { int32x2x4_t v = vld4_dup_s32(J32 + 3); PD(v.val[0]); PD(v.val[1]); PD(v.val[3]); }
  { uint16x8x2_t v = vld2q_u16((const uint16_t *)S16); v = vld2q_lane_u16((const uint16_t *)T16 + 1, v, 5); PQ(v.val[0]); PQ(v.val[1]); vst2q_lane_u16((uint16_t *)(buf + 412), v, 3); }
  { int32x4x3_t v = vld3q_s32(J32); v = vld3q_lane_s32(I32 + 10, v, 1); PQ(v.val[0]); PQ(v.val[2]); vst3q_lane_s32((int32_t *)(buf + 420), v, 2); }
  { uint8x8x4_t v = vld4_u8(B8 + 64); v = vld4_lane_u8(C8 + 9, v, 6); PD(v.val[1]); PD(v.val[3]); vst4_lane_u8(buf + 436, v, 4); }
  { uint8x16x4_t v = {{vld1q_u8(B8), vld1q_u8(B8 + 16), vld1q_u8(C8), vld1q_u8(C8 + 16)}}; vst4q_u8(buf + 440 - 64 - 64 - 64, v); }
  { uint64x2_t v = vld1q_u64((const uint64_t *)(B8 + 8)); vst1q_u64((uint64_t *)(buf + 448), v); PQ(v); }
  { int32x2x2_t v = vld1_s32_x2(I32); PD(v.val[0]); PD(v.val[1]); }
  putv(buf, 512);
}
NOINLINE static void t_neon_float(void) {
  for (int k = 0; k < 8; k++) {
    float32x4_t a = vld1q_f32(F + 4 * k), b = vld1q_f32(G + 4 * k), c = vld1q_f32(F + 32 + 4 * k);
    PQ(vaddq_f32(a, b)); PQ(vsubq_f32(a, b)); PQ(vmulq_f32(a, b)); PQ(vmlaq_f32(c, a, b)); PQ(vmlsq_f32(c, a, b)); PQ(vfmaq_f32(c, a, b)); PQ(vfmsq_f32(c, a, b));
    PQ(vabdq_f32(a, b)); PQ(vabsq_f32(a)); PQ(vnegq_f32(b)); PQ(vmaxq_f32(a, b)); PQ(vminq_f32(a, b));
    PD(vpadd_f32(vget_low_f32(a), vget_high_f32(b))); PD(vpmax_f32(vget_low_f32(a), vget_high_f32(b))); PD(vpmin_f32(vget_low_f32(c), vget_high_f32(a)));
    PQ(vcgtq_f32(a, b)); PQ(vcgeq_f32(a, c)); PQ(vceqq_f32(a, a)); PQ(vcltq_f32(a, vdupq_n_f32(0))); PQ(vcagtq_f32(a, b)); PQ(vcaleq_f32(a, c));
    PQ(vmulq_n_f32(a, 1.5f)); PQ(vmulq_lane_f32(a, vget_low_f32(b), 1)); PQ(vmlaq_lane_f32(c, a, vget_high_f32(b), 0)); PQ(vmlsq_n_f32(c, a, -2.0f));
    float32x4_t ra = vabsq_f32(a), e = vrecpeq_f32(ra), s = vrsqrteq_f32(ra);
    PQ(e); PQ(vrecpsq_f32(ra, e)); PQ(s); PQ(vrsqrtsq_f32(vmulq_f32(ra, s), s));
    PQ(vmulq_f32(e, vrecpsq_f32(ra, e)));
    PQ(vcvtq_s32_f32(vmulq_n_f32(a, 1000.0f))); PQ(vcvtq_u32_f32(vmulq_n_f32(ra, 123.0f))); PQ(vcvtq_f32_s32(vld1q_s32(I32 + 4 * k))); PQ(vcvtq_f32_u32(vld1q_u32((const uint32_t *)J32 + 4 * k)));
    PQ(vcvtq_n_s32_f32(a, 8)); PQ(vcvtq_n_f32_s32(vld1q_s32(J32 + 4 * k), 16)); PQ(vcvtq_n_u32_f32(ra, 20)); PQ(vcvtq_n_f32_u32(vld1q_u32((const uint32_t *)I32 + 4 * k), 31));
    PD(vcvt_f16_f32(a)); PQ(vcvt_f32_f16(vcvt_f16_f32(b)));
    PQ(vrecpeq_u32(vld1q_u32((const uint32_t *)I32 + 4 * k))); PQ(vrsqrteq_u32(vld1q_u32((const uint32_t *)J32 + 4 * k)));
    PQ(vbslq_f32(vcgtq_f32(a, b), a, b)); PQ(vdupq_n_f32(0.25f)); PQ(vmovq_n_f32(-3.0f)); PQ(vdupq_lane_f32(vget_high_f32(c), 1));
    float m[16]; for (int i = 0; i < 16; i++) m[i] = F[(i * 3 + k) % 64];
    float32x4_t r0 = vld1q_f32(m), r1 = vld1q_f32(m + 4), r2 = vld1q_f32(m + 8), r3 = vld1q_f32(m + 12);
    float32x4_t v = vmulq_n_f32(r0, F[k]); v = vmlaq_n_f32(v, r1, G[k]); v = vmlaq_n_f32(v, r2, F[k + 8]); v = vmlaq_n_f32(v, r3, 1.0f);
    PQ(v); putf(vgetq_lane_f32(v, 2)); PQ(vsetq_lane_f32(9.5f, v, 1));
  }
}

int run_all(uint32_t *out) {
  OUT = out; NOUT = 0;
  fill();
  mark(1); t_bitfields();
  mark(2); t_arith();
  mark(3); t_select();
  mark(4); t_switch();
  mark(5); t_atomic();
  mark(6); t_acle();
  mark(7); t_memory();
  mark(8); t_long();
  mark(9); t_vfp();
  mark(10); t_vec_auto();
  mark(11); t_neon_int();
  mark(12); t_neon_mem();
  mark(13); t_neon_float();
  mark(99);
  return NOUT;
}
