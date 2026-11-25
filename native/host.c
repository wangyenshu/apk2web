// Host implementations of the functions libgame.so imports from Android's libc, libm,
// libz, liblog and libstdc++ (GLES2 is in gl.c, JNI in jni.c).
#define _GNU_SOURCE
#include "emu.h"
#include <inttypes.h>
#include <ctype.h>
#include <errno.h>
#include <malloc.h>
#include <fcntl.h>
#include <locale.h>
#include <math.h>
#include <stdarg.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h>
#include <sys/time.h>
#include <time.h>
#include <unistd.h>
#include <wchar.h>
#include <wctype.h>
#include <zlib.h>
#include <dirent.h>
#include <strings.h>
#include <emscripten.h>

#define R8(a) (*(uint8_t *)(uintptr_t)(a))
#define R16(a) (*(uint16_t *)(uintptr_t)(a))
#define R32(a) (*(uint32_t *)(uintptr_t)(a))

void logf_(const char *fmt, ...) {
  char buf[2048];
  va_list ap;
  va_start(ap, fmt);
  vsnprintf(buf, sizeof buf, fmt, ap);
  va_end(ap);
  EM_ASM({ (Module.log || console.log)(UTF8ToString($0)); }, buf);
}

void fatal(const char *fmt, ...) {
  char buf[2048];
  va_list ap;
  va_start(ap, fmt);
  vsnprintf(buf, sizeof buf, fmt, ap);
  va_end(ap);
  if (current_thread) {   // where the guest was: the caller of the hook that stopped it
    size_t n = strlen(buf);
    snprintf(buf + n, sizeof buf - n, " (thread %d, called from %s)", current_thread->id, elf_where(current_thread->cpu.r[14]));
  }
  EM_ASM({ (Module.onFatal || console.error)(UTF8ToString($0)); }, buf);
  emscripten_force_exit(1);
  abort();
}

static FILE *mapf(uint32_t p);
static void set_errno(int e);
int errno_to_linux(int e);
static int errno_from_linux(int e);

// ---------------------------------------------------------------------------
// import table

typedef struct { const char *name; HookFn fn; uint32_t data; int id; } Import;
#define MAX_IMPORTS 8192
static Import imports[MAX_IMPORTS];
static int nimports;

static void add_fn(const char *name, HookFn fn);
uint32_t host_add_fn(const char *name, HookFn fn) { add_fn(name, fn); return (uint32_t)imports[nimports - 1].id; }
static void add_fn(const char *name, HookFn fn) {
  if (nimports == MAX_IMPORTS) fatal("too many host imports");
  imports[nimports].name = name;
  imports[nimports].fn = fn;
  imports[nimports].id = hook_register(name, fn);
  nimports++;
}
static void add_data(const char *name, void *p) {
  imports[nimports].name = name;
  imports[nimports].data = GA(p);
  nimports++;
}

uint32_t host_import(const char *name, int *is_data) {
  for (int i = 0; i < nimports; i++) {
    if (strcmp(imports[i].name, name)) continue;
    if (imports[i].fn) { *is_data = 0; return hook_stub_address(imports[i].id); }
    *is_data = 1;
    return imports[i].data;
  }
  return 0;
}

/** the i-th import (for symbol tables of the platform libraries): its address, or 0 past the end */
uint32_t host_import_at(int i, const char **name, int *is_data) {
  if (i < 0 || i >= nimports) return 0;
  *name = imports[i].name;
  *is_data = !imports[i].fn;
  return imports[i].fn ? hook_stub_address(imports[i].id) : imports[i].data;
}

static void missing(Cpu *c) { fatal("called a missing import"); }

// SHA-1 as old bionic exported it (SHA1_CTX: state[5], count[2], buffer[64])
typedef struct { uint32_t state[5], count[2]; uint8_t buffer[64]; } Sha1;
static uint32_t rol(uint32_t v, int n) { return (v << n) | (v >> (32 - n)); }
static void sha1_block(uint32_t st[5], const uint8_t *p) {
  uint32_t w[80], a = st[0], b = st[1], c = st[2], d = st[3], e = st[4];
  for (int i = 0; i < 16; i++) w[i] = (uint32_t)p[4 * i] << 24 | p[4 * i + 1] << 16 | p[4 * i + 2] << 8 | p[4 * i + 3];
  for (int i = 16; i < 80; i++) w[i] = rol(w[i - 3] ^ w[i - 8] ^ w[i - 14] ^ w[i - 16], 1);
  for (int i = 0; i < 80; i++) {
    uint32_t f, k;
    if (i < 20) { f = (b & c) | (~b & d); k = 0x5A827999; }
    else if (i < 40) { f = b ^ c ^ d; k = 0x6ED9EBA1; }
    else if (i < 60) { f = (b & c) | (b & d) | (c & d); k = 0x8F1BBCDC; }
    else { f = b ^ c ^ d; k = 0xCA62C1D6; }
    uint32_t t = rol(a, 5) + f + e + k + w[i];
    e = d; d = c; c = rol(b, 30); b = a; a = t;
  }
  st[0] += a; st[1] += b; st[2] += c; st[3] += d; st[4] += e;
}
static void sha1_update(Sha1 *s, const uint8_t *p, uint32_t n) {
  uint32_t have = (s->count[0] >> 3) & 63;
  uint64_t bits = ((uint64_t)s->count[1] << 32 | s->count[0]) + ((uint64_t)n << 3);
  s->count[0] = (uint32_t)bits; s->count[1] = (uint32_t)(bits >> 32);
  while (n--) { s->buffer[have++] = *p++; if (have == 64) { sha1_block(s->state, s->buffer); have = 0; } }
}
static void h_SHA1Init(Cpu *c) {
  Sha1 *s = G(arg(c, 0));
  static const uint32_t iv[5] = {0x67452301, 0xEFCDAB89, 0x98BADCFE, 0x10325476, 0xC3D2E1F0};
  memcpy(s->state, iv, 20); s->count[0] = s->count[1] = 0;
}
static void h_SHA1Update(Cpu *c) { sha1_update(G(arg(c, 0)), G(arg(c, 1)), arg(c, 2)); }
static void h_SHA1Final(Cpu *c) {
  uint8_t *out = G(arg(c, 0));
  Sha1 *s = G(arg(c, 1));
  uint8_t len[8];
  for (int i = 0; i < 8; i++) len[i] = (uint8_t)((i < 4 ? s->count[1] : s->count[0]) >> (24 - 8 * (i & 3)));
  uint8_t pad = 0x80, z = 0;
  sha1_update(s, &pad, 1);
  while (((s->count[0] >> 3) & 63) != 56) sha1_update(s, &z, 1);
  sha1_update(s, len, 8);
  for (int i = 0; i < 20; i++) out[i] = (uint8_t)(s->state[i >> 2] >> (24 - 8 * (i & 3)));
}

static void h_div(Cpu *c) {   // div_t is returned in memory: r0 points at it
  int32_t n = (int32_t)arg(c, 1), d = (int32_t)arg(c, 2);
  uint32_t *r = G(arg(c, 0));
  r[0] = d ? (uint32_t)(n / d) : 0; r[1] = d ? (uint32_t)(n % d) : 0;
  ret32(c, arg(c, 0));
}
static void h_getrusage(Cpu *c) { memset(G(arg(c, 1)), 0, 72); ret32(c, 0); }
static void h_getresuid(Cpu *c) { for (int i = 0; i < 3; i++) if (arg(c, i)) R32(arg(c, i)) = 10000; ret32(c, 0); }
static void h_sendfile(Cpu *c) {
  int out = arg(c, 0), in = arg(c, 1);
  uint32_t offp = arg(c, 2), n = arg(c, 3), done = 0;
  char buf[8192];
  if (offp) lseek(in, (off_t)R32(offp), SEEK_SET);
  while (done < n) {
    ssize_t k = read(in, buf, n - done < sizeof buf ? n - done : sizeof buf);
    if (k <= 0) break;
    if (write(out, buf, k) != k) break;
    done += (uint32_t)k;
  }
  if (offp) R32(offp) += done;
  ret32(c, done);
}
static void h_truncate(Cpu *c) { ret32(c, truncate(G(arg(c, 0)), (off_t)(int32_t)arg(c, 1))); }
static void h_wcscmp(Cpu *c) {
  const int32_t *a = G(arg(c, 0)), *b = G(arg(c, 1));
  while (*a && *a == *b) { a++; b++; }
  ret32(c, (uint32_t)(*a < *b ? -1 : *a > *b));
}
static void h_wcsncpy(Cpu *c) {
  uint32_t *d = G(arg(c, 0)); const uint32_t *s = G(arg(c, 1));
  uint32_t n = arg(c, 2), i = 0;
  for (; i < n && s[i]; i++) d[i] = s[i];
  for (; i < n; i++) d[i] = 0;
  ret32(c, arg(c, 0));
}
static void h_iswcntrl(Cpu *c) { ret32(c, iswcntrl(arg(c, 0)) != 0); }
static void h_iswxdigit(Cpu *c) { ret32(c, iswxdigit(arg(c, 0)) != 0); }
static void h___fpclassifyf(Cpu *c) {   // bionic's FP_* values
  float f = argf(c, 0);
  ret32(c, isnan(f) ? 1 : isinf(f) ? 2 : f == 0 ? 0x10 : fabsf(f) < 1.17549435e-38f ? 8 : 4);
}
static void h_fgetpos(Cpu *c) { long p = ftell(mapf(arg(c, 0))); if (p < 0) { set_errno(errno); ret32(c, (uint32_t)-1); return; } R32(arg(c, 1)) = (uint32_t)p; ret32(c, 0); }
static void h_fsetpos(Cpu *c) { ret32(c, (uint32_t)fseek(mapf(arg(c, 0)), (long)(int32_t)R32(arg(c, 1)), SEEK_SET)); }
// the C++ ABI's exception helpers some libraries import from the platform (old NDK STLs): no handler matches
static void h___cxa_begin_cleanup(Cpu *c) { ret32(c, 1); }
static void h___cxa_type_match(Cpu *c) { ret32(c, 0); }
static void h___cxa_call_unexpected(Cpu *c) { fatal("C++ unexpected exception (std::unexpected) in native code"); }
static uint32_t page_size_v = 4096, data_start_v;

// a platform function the emulator does not provide: calls report it (once per function) and fail with ENOSYS
void host_unimplemented(Cpu *c) {
  static uint8_t told[16384];
  int id = current_hook;
  if (id >= 0 && id < 16384 && !told[id]) { told[id] = 1; logf_("W/apk2web: native code called %s, which the emulator does not provide (it returns -1)", hook_name(id)); }
  set_errno(ENOSYS);
  ret32(c, (uint32_t)-1);
}
/** names libraries import (from the build): the platform libraries' symbol tables list them, unimplemented ones fail */
void host_declare(const char *name) {
  int is_data;
  if (host_import(name, &is_data)) return;
  add_fn(strdup(name), host_unimplemented);
}

// ---------------------------------------------------------------------------
// varargs (AAPCS): registers first, then the caller's stack; or a va_list pointer

void va_from_args(Va *v, Cpu *c, int first) {
  v->c = c;
  if (first < 4) { v->reg = first; v->mem_mode = 0; v->mem = c->r[13]; }
  else { v->reg = 4; v->mem_mode = 1; v->mem = c->r[13] + 4 * (first - 4); }
}
void va_from_list(Va *v, uint32_t ap) { v->c = NULL; v->reg = 4; v->mem_mode = 1; v->mem = ap; }
uint32_t va32(Va *v) {
  if (!v->mem_mode) {
    if (v->reg < 4) return v->c->r[v->reg++];
    v->mem_mode = 1;
  }
  uint32_t x = R32(v->mem);
  v->mem += 4;
  return x;
}
uint64_t va64(Va *v) {
  if (!v->mem_mode) {
    if (v->reg & 1) v->reg++;
    if (v->reg <= 2) {
      uint64_t x = (uint64_t)v->c->r[v->reg] | ((uint64_t)v->c->r[v->reg + 1] << 32);
      v->reg += 2;
      return x;
    }
    v->reg = 4;
    v->mem_mode = 1;
  }
  v->mem = (v->mem + 7) & ~7u;
  uint64_t x = (uint64_t)R32(v->mem) | ((uint64_t)R32(v->mem + 4) << 32);
  v->mem += 8;
  return x;
}

// printf with arguments taken from the guest
static int gformat(char *out, size_t cap, const char *fmt, Va *va) {
  size_t len = 0;
#define EMIT(s, n) do { size_t _n = (n); if (len < cap) memcpy(out + len, s, (len + _n < cap) ? _n : cap - len); len += _n; } while (0)
  while (*fmt) {
    if (*fmt != '%') {
      const char *p = strchr(fmt, '%');
      size_t n = p ? (size_t)(p - fmt) : strlen(fmt);
      EMIT(fmt, n);
      fmt += n;
      continue;
    }
    const char *start = fmt++;
    if (*fmt == '%') { EMIT("%", 1); fmt++; continue; }
    char spec[64];
    int si = 0;
    spec[si++] = '%';
    while (strchr("-+ #0", *fmt)) spec[si++] = *fmt++;
    if (*fmt == '*') { si += snprintf(spec + si, 16, "%d", (int)va32(va)); fmt++; }
    else while (isdigit((unsigned char)*fmt)) spec[si++] = *fmt++;
    if (*fmt == '.') {
      spec[si++] = *fmt++;
      if (*fmt == '*') { si += snprintf(spec + si, 16, "%d", (int)va32(va)); fmt++; }
      else while (isdigit((unsigned char)*fmt)) spec[si++] = *fmt++;
    }
    int lng = 0;  // 0 int, 1 long, 2 long long, -1 short, -2 char
    for (;;) {
      if (*fmt == 'l') { lng = lng == 1 ? 2 : 1; fmt++; }
      else if (*fmt == 'h') { lng = lng == -1 ? -2 : -1; fmt++; }
      else if (*fmt == 'q' || *fmt == 'j') { lng = 2; fmt++; }
      else if (*fmt == 'z' || *fmt == 't' || *fmt == 'L') { fmt++; }
      else break;
    }
    char conv = *fmt ? *fmt++ : 0;
    char tmp[512];
    int n = 0;
    switch (conv) {
      case 'd': case 'i': case 'u': case 'o': case 'x': case 'X': case 'c':
        if (lng == 2) {
          spec[si++] = 'l'; spec[si++] = 'l'; spec[si++] = conv; spec[si] = 0;
          n = snprintf(tmp, sizeof tmp, spec, (long long)va64(va));
        } else {
          if (lng == -1) spec[si++] = 'h';
          if (lng == -2) { spec[si++] = 'h'; spec[si++] = 'h'; }
          spec[si++] = conv; spec[si] = 0;
          n = snprintf(tmp, sizeof tmp, spec, (int)va32(va));
        }
        break;
      case 'p':
        spec[si++] = 'x'; spec[si] = 0;
        EMIT("0x", 2);
        n = snprintf(tmp, sizeof tmp, spec, va32(va));
        break;
      case 'f': case 'F': case 'e': case 'E': case 'g': case 'G': case 'a': case 'A': {
        uint64_t u = va64(va);
        double d;
        memcpy(&d, &u, 8);
        spec[si++] = conv; spec[si] = 0;
        n = snprintf(tmp, sizeof tmp, spec, d);
        break;
      }
      case 's': {
        uint32_t p = va32(va);
        spec[si++] = 's'; spec[si] = 0;
        const char *s = p ? (const char *)G(p) : "(null)";
        size_t need = strlen(s) + 64;
        if (need < sizeof tmp) n = snprintf(tmp, sizeof tmp, spec, s);
        else {
          char *big = malloc(need + 256);
          int m = snprintf(big, need + 256, spec, s);
          EMIT(big, (size_t)m);
          free(big);
          continue;
        }
        break;
      }
      case 'n': {
        uint32_t p = va32(va);
        if (p) R32(p) = (uint32_t)len;
        continue;
      }
      default:
        EMIT(start, (size_t)(fmt - start));
        continue;
    }
    if (n > (int)sizeof tmp - 1) n = sizeof tmp - 1;
    if (n > 0) EMIT(tmp, (size_t)n);
  }
  if (cap) out[len < cap ? len : cap - 1] = 0;
  return (int)len;
#undef EMIT
}

static char *gformat_alloc(const char *fmt, Va *va) {
  Va copy = *va;
  int n = gformat(NULL, 0, fmt, &copy);
  char *buf = malloc(n + 1);
  gformat(buf, n + 1, fmt, va);
  return buf;
}

// ---------------------------------------------------------------------------
// stdio: guest FILE* is either one of bionic's __sF[3] or a host FILE*

static uint8_t sF[3 * 84];
static FILE *mapf(uint32_t p) {
  if (p >= GA(sF) && p < GA(sF) + sizeof sF) {
    int i = (p - GA(sF)) / 84;
    return i == 0 ? stdin : i == 1 ? stdout : stderr;
  }
  return (FILE *)G(p);
}

// guest console output goes to the JS console line by line
static char linebuf[4096];
static int linelen;
static void out_text(const char *s, size_t n) {
  for (size_t i = 0; i < n; i++) {
    if (s[i] == '\n' || linelen == sizeof linebuf - 1) {
      linebuf[linelen] = 0;
      logf_("[native] %s", linebuf);
      linelen = 0;
      if (s[i] == '\n') continue;
    }
    linebuf[linelen++] = s[i];
  }
}
static size_t fwrite_g(const void *p, size_t sz, size_t n, FILE *f) {
  if (f == stdout || f == stderr) { out_text(p, sz * n); return n; }
  return fwrite(p, sz, n, f);
}

static const char *proc_path(const char *p);
static void h_fopen(Cpu *c) { ret32(c, GA(fopen(proc_path(G(arg(c, 0))), G(arg(c, 1))))); }
static void h_fdopen(Cpu *c) { ret32(c, GA(fdopen(arg(c, 0), G(arg(c, 1))))); }
static void h_fclose(Cpu *c) { FILE *f = mapf(arg(c, 0)); ret32(c, (f == stdout || f == stderr || f == stdin) ? 0 : fclose(f)); }
static void h_fflush(Cpu *c) { ret32(c, arg(c, 0) ? fflush(mapf(arg(c, 0))) : 0); }
static void h_fread(Cpu *c) { ret32(c, fread(G(arg(c, 0)), arg(c, 1), arg(c, 2), mapf(arg(c, 3)))); }
static void h_fwrite(Cpu *c) { ret32(c, fwrite_g(G(arg(c, 0)), arg(c, 1), arg(c, 2), mapf(arg(c, 3)))); }
static void h_fseek(Cpu *c) { ret32(c, fseek(mapf(arg(c, 0)), (int32_t)arg(c, 1), arg(c, 2))); }
static void h_ftell(Cpu *c) { ret32(c, ftell(mapf(arg(c, 0)))); }
static void h_getc(Cpu *c) { ret32(c, getc(mapf(arg(c, 0)))); }
static void h_ungetc(Cpu *c) { ret32(c, ungetc(arg(c, 0), mapf(arg(c, 1)))); }
static void h_fputc(Cpu *c) { char ch = arg(c, 0); fwrite_g(&ch, 1, 1, mapf(arg(c, 1))); ret32(c, (uint8_t)ch); }
static void h_fputs(Cpu *c) { const char *s = G(arg(c, 0)); fwrite_g(s, 1, strlen(s), mapf(arg(c, 1))); ret32(c, 0); }
static void h_setvbuf(Cpu *c) { ret32(c, 0); }
static void h_getwc(Cpu *c) { ret32(c, getwc(mapf(arg(c, 0)))); }
static void h_putwc(Cpu *c) { wchar_t ch = arg(c, 0); char b[8]; int n = wctomb(b, ch); if (n > 0) fwrite_g(b, 1, n, mapf(arg(c, 1))); ret32(c, ch); }
static void h_ungetwc(Cpu *c) { ret32(c, ungetwc(arg(c, 0), mapf(arg(c, 1)))); }

static void h_printf(Cpu *c) {
  Va va; va_from_args(&va, c, 1);
  char *s = gformat_alloc(G(arg(c, 0)), &va);
  out_text(s, strlen(s)); ret32(c, strlen(s)); free(s);
}
static void h_fprintf(Cpu *c) {
  Va va; va_from_args(&va, c, 2);
  char *s = gformat_alloc(G(arg(c, 1)), &va);
  ret32(c, fwrite_g(s, 1, strlen(s), mapf(arg(c, 0)))); free(s);
}
static void h_vfprintf(Cpu *c) {
  Va va; va_from_list(&va, arg(c, 2));
  char *s = gformat_alloc(G(arg(c, 1)), &va);
  ret32(c, fwrite_g(s, 1, strlen(s), mapf(arg(c, 0)))); free(s);
}
static void h_sprintf(Cpu *c) { Va va; va_from_args(&va, c, 2); ret32(c, gformat(G(arg(c, 0)), 0x7FFFFFFF, G(arg(c, 1)), &va)); }
static void h_snprintf(Cpu *c) { Va va; va_from_args(&va, c, 3); ret32(c, gformat(G(arg(c, 0)), arg(c, 1), G(arg(c, 2)), &va)); }
static void h_vsprintf(Cpu *c) { Va va; va_from_list(&va, arg(c, 2)); ret32(c, gformat(G(arg(c, 0)), 0x7FFFFFFF, G(arg(c, 1)), &va)); }
static void h_vsnprintf(Cpu *c) { Va va; va_from_list(&va, arg(c, 3)); ret32(c, gformat(G(arg(c, 0)), arg(c, 1), G(arg(c, 2)), &va)); }

static void h_android_log_print(Cpu *c) {
  Va va; va_from_args(&va, c, 3);
  char *s = gformat_alloc(G(arg(c, 2)), &va);
  logf_("[%s] %s", (const char *)G(arg(c, 1)), s);
  free(s);
  ret32(c, 0);
}
static void h_android_log_vprint(Cpu *c) {
  Va va; va_from_list(&va, arg(c, 3));
  char *s = gformat_alloc(G(arg(c, 2)), &va);
  logf_("[%s] %s", (const char *)G(arg(c, 1)), s);
  free(s);
  ret32(c, 0);
}
static void h_android_log_write(Cpu *c) { logf_("[%s] %s", (const char *)G(arg(c, 1)), (const char *)G(arg(c, 2))); ret32(c, 0); }

// sscanf: one conversion at a time through the host's sscanf; *used = characters consumed
static int scan_core(const char *in, const char *fmt, Va *vap, int *used) {
  Va va = *vap;
  int assigned = 0;
  const char *p = in;
  while (*fmt) {
    if (isspace((unsigned char)*fmt)) { while (isspace((unsigned char)*p)) p++; fmt++; continue; }
    if (*fmt != '%' || fmt[1] == '%') {
      if (*fmt == '%') fmt++;
      if (*p != *fmt) break;
      p++; fmt++;
      continue;
    }
    char spec[128];
    int si = 0, suppress = 0;
    spec[si++] = *fmt++;
    if (*fmt == '*') { suppress = 1; spec[si++] = *fmt++; }
    while (isdigit((unsigned char)*fmt)) spec[si++] = *fmt++;
    while (*fmt == 'l' || *fmt == 'h' || *fmt == 'L' || *fmt == 'q') spec[si++] = *fmt++;
    char conv = *fmt;
    if (conv == '[') {
      do { spec[si++] = *fmt++; } while (*fmt && *fmt != ']' && si < 120);
      if (*fmt == ']') spec[si++] = *fmt++;
    } else spec[si++] = *fmt++;
    strcpy(spec + si, "%n");
    if (!*p && conv != 'n') break;
    int consumed = -1, r;
    if (conv == 'n') {
      if (!suppress) R32(va32(&va)) = (uint32_t)(p - in);
      continue;
    }
    if (suppress) r = sscanf(p, spec, &consumed);
    else r = sscanf(p, spec, G(va32(&va)), &consumed);
    if (r < 1 && !suppress) break;
    if (consumed < 0) break;
    if (!suppress) assigned++;
    p += consumed;
  }
  if (used) *used = (int)(p - in);
  return assigned == 0 && !*in ? -1 : assigned;
}
static void h_sscanf(Cpu *c) { Va va; va_from_args(&va, c, 2); ret32(c, (uint32_t)scan_core(G(arg(c, 0)), G(arg(c, 1)), &va, NULL)); }
static void h_vsscanf_(Cpu *c) { Va va; va_from_list(&va, arg(c, 2)); ret32(c, (uint32_t)scan_core(G(arg(c, 0)), G(arg(c, 1)), &va, NULL)); }

// ---------------------------------------------------------------------------
// files

static int open_flags(uint32_t f) { return f & (O_ACCMODE | O_CREAT | O_EXCL | O_TRUNC | O_APPEND | O_NONBLOCK); }
static void h_open(Cpu *c) { int fd = open(proc_path(G(arg(c, 0))), open_flags(arg(c, 1)), arg(c, 2)); ret32(c, fd); }
static void h_close(Cpu *c) { ret32(c, close(arg(c, 0))); }
static void h_read(Cpu *c) { ret32(c, read(arg(c, 0), G(arg(c, 1)), arg(c, 2))); }
static void h_write(Cpu *c) {
  int fd = arg(c, 0);
  if (fd == 1 || fd == 2) { out_text(G(arg(c, 1)), arg(c, 2)); ret32(c, arg(c, 2)); return; }
  ret32(c, write(fd, G(arg(c, 1)), arg(c, 2)));
}
static void h_lseek(Cpu *c) { ret32(c, lseek(arg(c, 0), (int32_t)arg(c, 1), arg(c, 2))); }
static void h_ioctl(Cpu *c) { ret32(c, (uint32_t)-1); }
// mmap: memory from the host heap, page aligned. File mappings are read in (MAP_SHARED writable ones are
// written back by msync/munmap); MAP_FIXED works inside a range mapped earlier (how dynamic linkers and
// allocators reserve address space and then place segments in it).
#define MAP_MAX 4096
typedef struct { uint32_t addr, len; int fd_shared; uint32_t off; uint32_t file_len; } MapRegion;
static MapRegion maps[MAP_MAX];
static int nmaps;
static MapRegion *map_containing(uint32_t a, uint32_t len) {
  for (int i = 0; i < nmaps; i++)
    if (a >= maps[i].addr && a - maps[i].addr + (uint64_t)len <= maps[i].len) return &maps[i];
  return NULL;
}
static void map_writeback(MapRegion *m) {
  if (m->fd_shared < 0) return;
  pwrite(m->fd_shared, G(m->addr), m->file_len, m->off);
}
static uint32_t do_mmap(uint32_t addr, uint32_t len, uint32_t prot, uint32_t flags, int fd, uint64_t off) {
  enum { M_SHARED = 1, M_FIXED = 0x10, M_ANON = 0x20 };
  if (!len) { errno = EINVAL; return (uint32_t)-1; }
  uint32_t size = (len + 4095) & ~4095u, p;
  int fresh = 0;
  if (flags & M_FIXED) {
    if (addr & 4095 || !map_containing(addr, size)) { errno = ENOMEM; return (uint32_t)-1; }
    p = addr;
  } else {
    if (nmaps == MAP_MAX) { errno = ENOMEM; return (uint32_t)-1; }
    void *m = aligned_alloc(4096, size);
    if (!m) { errno = ENOMEM; return (uint32_t)-1; }
    p = GA(m);
    fresh = 1;
  }
  memset(G(p), 0, size);
  uint32_t got = 0;
  if (!(flags & M_ANON) && fd >= 0) {
    ssize_t n = pread(fd, G(p), len, (off_t)off);
    if (n < 0) { if (fresh) free(G(p)); return (uint32_t)-1; }
    got = (uint32_t)n;
  }
  if (fresh) {
    MapRegion *m = &maps[nmaps++];
    m->addr = p; m->len = size; m->off = (uint32_t)off; m->file_len = got; m->fd_shared = -1;
    if (!(flags & M_ANON) && (flags & M_SHARED) && (prot & 2) && fd >= 0) m->fd_shared = dup(fd);
  }
  (void)prot;
  return p;
}
/** "map@0xBASE+0xOFF" for an address in an mmap region (a library a guest linker loaded maps at its base) */
const char *host_where_mapped(uint32_t a) {
  static char buf[64];
  for (int i = 0; i < nmaps; i++)
    if (a >= maps[i].addr && a - maps[i].addr < maps[i].len) { snprintf(buf, sizeof buf, "map@0x%08x+0x%x", maps[i].addr, a - maps[i].addr); return buf; }
  return NULL;
}
static void h_mmap(Cpu *c) { ret32(c, do_mmap(arg(c, 0), arg(c, 1), arg(c, 2), arg(c, 3), (int)arg(c, 4), arg(c, 5))); }
static void h_mmap64(Cpu *c) { ret32(c, do_mmap(arg(c, 0), arg(c, 1), arg(c, 2), arg(c, 3), (int)arg(c, 4), arg64(c, 6))); }
static void h_munmap(Cpu *c) {
  uint32_t a = arg(c, 0), len = (arg(c, 1) + 4095) & ~4095u;
  for (int i = 0; i < nmaps; i++) {
    if (maps[i].addr != a || maps[i].len != len) continue;   // a whole mapping: free it (parts stay mapped)
    map_writeback(&maps[i]);
    if (maps[i].fd_shared >= 0) close(maps[i].fd_shared);
    free(G(a));
    maps[i] = maps[--nmaps];
    break;
  }
  ret32(c, 0);
}
static void h_msync(Cpu *c) {
  MapRegion *m = map_containing(arg(c, 0) & ~4095u, 1);
  if (m) map_writeback(m);
  ret32(c, 0);
}
static void h_mremap(Cpu *c) {
  uint32_t a = arg(c, 0), old = (arg(c, 1) + 4095) & ~4095u, len = arg(c, 2), flags = arg(c, 3);
  for (int i = 0; i < nmaps; i++) {
    if (maps[i].addr != a) continue;
    if (((len + 4095) & ~4095u) <= maps[i].len) { ret32(c, a); return; }
    if (!(flags & 1)) break;   // MREMAP_MAYMOVE
    void *m = aligned_alloc(4096, (len + 4095) & ~4095u);
    if (!m) break;
    memcpy(m, G(a), old < maps[i].len ? old : maps[i].len);
    memset((char *)m + maps[i].len, 0, ((len + 4095) & ~4095u) - maps[i].len);
    free(G(a));
    maps[i].addr = GA(m); maps[i].len = (len + 4095) & ~4095u;
    ret32(c, GA(m));
    return;
  }
  errno = ENOMEM;
  ret32(c, (uint32_t)-1);
}
// the real working directory (Tcl and others cache it and build absolute paths from it); getcwd(NULL, n) allocates
static void h_getcwd(Cpu *c) {
  char tmp[4096];
  uint32_t b = arg(c, 0), n = arg(c, 1);
  if (!getcwd(tmp, sizeof tmp)) { set_errno(errno); ret32(c, 0); return; }
  size_t need = strlen(tmp) + 1;
  if (!b) { char *m = malloc(n > need ? n : need); if (!m) { set_errno(ENOMEM); ret32(c, 0); return; } strcpy(m, tmp); ret32(c, GA(m)); return; }
  if (n < need) { set_errno(ERANGE); ret32(c, 0); return; }
  memcpy(G(b), tmp, need);
  ret32(c, b);
}

// bionic ARM struct stat (104 bytes)
static void put_stat(uint32_t p, struct stat *st) {
  memset(G(p), 0, 104);
  *(uint64_t *)G(p + 0) = st->st_dev;
  R32(p + 12) = (uint32_t)st->st_ino;
  R32(p + 16) = st->st_mode;
  R32(p + 20) = st->st_nlink;
  R32(p + 24) = st->st_uid;
  R32(p + 28) = st->st_gid;
  *(uint64_t *)G(p + 32) = st->st_rdev;
  *(int64_t *)G(p + 48) = st->st_size;
  R32(p + 56) = 4096;
  *(uint64_t *)G(p + 64) = (st->st_size + 511) / 512;
  R32(p + 72) = (uint32_t)st->st_atime;
  R32(p + 80) = (uint32_t)st->st_mtime;
  R32(p + 88) = (uint32_t)st->st_ctime;
  *(uint64_t *)G(p + 96) = st->st_ino;
}
static void h_stat(Cpu *c) { struct stat st; int r = stat(proc_path(G(arg(c, 0))), &st); if (!r) put_stat(arg(c, 1), &st); ret32(c, r); }
static void h_fstat(Cpu *c) { struct stat st; int r = fstat(arg(c, 0), &st); if (!r) put_stat(arg(c, 1), &st); ret32(c, r); }

// ---------------------------------------------------------------------------
// time

static void h_time(Cpu *c) { uint32_t t = (uint32_t)time(NULL); if (arg(c, 0)) R32(arg(c, 0)) = t; ret32(c, t); }
static void h_clock(Cpu *c) { ret32(c, (uint32_t)clock()); }
static void h_gettimeofday(Cpu *c) {
  struct timeval tv;
  gettimeofday(&tv, NULL);
  uint32_t p = arg(c, 0);
  if (p) { R32(p) = (uint32_t)tv.tv_sec; R32(p + 4) = (uint32_t)tv.tv_usec; }
  ret32(c, 0);
}
static void h_gmtime(Cpu *c) { time_t t = (int32_t)R32(arg(c, 0)); ret32(c, GA(gmtime(&t))); }
static void h_strftime(Cpu *c) { ret32(c, strftime(G(arg(c, 0)), arg(c, 1), G(arg(c, 2)), G(arg(c, 3)))); }
static void h_wcsftime(Cpu *c) { ret32(c, wcsftime(G(arg(c, 0)), arg(c, 1), G(arg(c, 2)), G(arg(c, 3)))); }

// ---------------------------------------------------------------------------
// memory and strings

static void h_malloc(Cpu *c) { ret32(c, GA(malloc(arg(c, 0)))); }
static void h_calloc(Cpu *c) { ret32(c, GA(calloc(arg(c, 0), arg(c, 1)))); }
static void h_realloc(Cpu *c) {
  void *p = G(arg(c, 0));
  size_t old = p ? malloc_usable_size(p) : 0;
  void *n = realloc(p, arg(c, 1));
  if (p && n != p) jni_memory_freed(GA(p), (uint32_t)old);
  ret32(c, GA(n));
}
static void h_free(Cpu *c) { void *p = G(arg(c, 0)); if (p) jni_memory_freed(GA(p), (uint32_t)malloc_usable_size(p)); free(p); }
static void h_memalign(Cpu *c) {
  uint32_t al = arg(c, 0), n = arg(c, 1);
  if (al < 8) al = 8;
  if (al & (al - 1)) { uint32_t p2 = 8; while (p2 < al) p2 <<= 1; al = p2; }
  ret32(c, GA(aligned_alloc(al, (n + al - 1) & ~(al - 1))));
}
static void h_valloc(Cpu *c) { ret32(c, GA(aligned_alloc(4096, (arg(c, 0) + 4095) & ~4095u))); }
static void h_posix_memalign(Cpu *c) {
  uint32_t al = arg(c, 1), n = arg(c, 2);
  if (al < 8 || (al & (al - 1))) { ret32(c, errno_to_linux(EINVAL)); return; }
  void *p = aligned_alloc(al, (n + al - 1) & ~(al - 1));
  if (!p) { ret32(c, errno_to_linux(ENOMEM)); return; }
  R32(arg(c, 0)) = GA(p);
  ret32(c, 0);
}
static void h_memcpy(Cpu *c) { memcpy(G(arg(c, 0)), G(arg(c, 1)), arg(c, 2)); ret32(c, arg(c, 0)); }
static void h_memmove(Cpu *c) { memmove(G(arg(c, 0)), G(arg(c, 1)), arg(c, 2)); ret32(c, arg(c, 0)); }
static void h_memset(Cpu *c) { memset(G(arg(c, 0)), arg(c, 1), arg(c, 2)); ret32(c, arg(c, 0)); }
static void h_memcmp(Cpu *c) { ret32(c, memcmp(G(arg(c, 0)), G(arg(c, 1)), arg(c, 2))); }
static void h_memchr(Cpu *c) { ret32(c, GA(memchr(G(arg(c, 0)), arg(c, 1), arg(c, 2)))); }
static void h_strlen(Cpu *c) { ret32(c, strlen(G(arg(c, 0)))); }
static void h_strcpy(Cpu *c) { strcpy(G(arg(c, 0)), G(arg(c, 1))); ret32(c, arg(c, 0)); }
static void h_strncpy(Cpu *c) { strncpy(G(arg(c, 0)), G(arg(c, 1)), arg(c, 2)); ret32(c, arg(c, 0)); }
static void h_strcat(Cpu *c) { strcat(G(arg(c, 0)), G(arg(c, 1))); ret32(c, arg(c, 0)); }
static void h_strcmp(Cpu *c) { ret32(c, strcmp(G(arg(c, 0)), G(arg(c, 1)))); }
static void h_strncmp(Cpu *c) { ret32(c, strncmp(G(arg(c, 0)), G(arg(c, 1)), arg(c, 2))); }
static void h_strcoll(Cpu *c) { ret32(c, strcmp(G(arg(c, 0)), G(arg(c, 1)))); }
static void h_strchr(Cpu *c) { ret32(c, GA(strchr(G(arg(c, 0)), arg(c, 1)))); }
static void h_strstr(Cpu *c) { ret32(c, GA(strstr(G(arg(c, 0)), G(arg(c, 1))))); }
static void h_strpbrk(Cpu *c) { ret32(c, GA(strpbrk(G(arg(c, 0)), G(arg(c, 1))))); }
static void h_strtok(Cpu *c) { ret32(c, GA(strtok(G(arg(c, 0)), G(arg(c, 1))))); }
static void h_strxfrm(Cpu *c) { ret32(c, strxfrm(G(arg(c, 0)), G(arg(c, 1)), arg(c, 2))); }
static void h_strerror(Cpu *c) { ret32(c, GA(strerror(errno_from_linux((int)arg(c, 0))))); }
static void h_strtod(Cpu *c) {
  char *end;
  double d = strtod(G(arg(c, 0)), &end);
  if (arg(c, 1)) R32(arg(c, 1)) = GA(end);
  retd(c, d);
}
static void h_atoi(Cpu *c) { ret32(c, atoi(G(arg(c, 0)))); }
static void h_getenv(Cpu *c) { ret32(c, 0); }
static uint64_t rand48 = 0x1234ABCD330EULL;
static void h_srand48(Cpu *c) { rand48 = ((uint64_t)arg(c, 0) << 16) | 0x330E; }
static void h_lrand48(Cpu *c) {
  rand48 = (rand48 * 0x5DEECE66DULL + 0xB) & 0xFFFFFFFFFFFFULL;
  ret32(c, (uint32_t)(rand48 >> 17));
}

// qsort / bsearch with a guest comparator
static void h_qsort(Cpu *c) {
  uint8_t *base = G(arg(c, 0));
  uint32_t n = arg(c, 1), size = arg(c, 2), cmp = arg(c, 3);
  if (n < 2 || !size) return;
  uint8_t *tmp = malloc(size);
  // insertion sort for small inputs, otherwise a bottom-up merge sort
  if (n <= 16) {
    for (uint32_t i = 1; i < n; i++) {
      memcpy(tmp, base + i * size, size);
      uint32_t j = i;
      while (j > 0) {
        uint32_t a[2] = { GA(base + (j - 1) * size), GA(tmp) };
        if ((int32_t)guest_call(c, cmp, 2, a) <= 0) break;
        memcpy(base + j * size, base + (j - 1) * size, size);
        j--;
      }
      memcpy(base + j * size, tmp, size);
    }
  } else {
    uint8_t *buf = malloc((size_t)n * size), *src = base, *dst = buf;
    for (uint32_t w = 1; w < n; w *= 2) {
      for (uint32_t lo = 0; lo < n; lo += 2 * w) {
        uint32_t mid = lo + w < n ? lo + w : n, hi = lo + 2 * w < n ? lo + 2 * w : n;
        uint32_t i = lo, j = mid, k = lo;
        while (i < mid && j < hi) {
          uint32_t a[2] = { GA(src + j * size), GA(src + i * size) };
          if ((int32_t)guest_call(c, cmp, 2, a) < 0) { memcpy(dst + k * size, src + j * size, size); j++; }
          else { memcpy(dst + k * size, src + i * size, size); i++; }
          k++;
        }
        if (i < mid) memcpy(dst + k * size, src + i * size, (mid - i) * size), k += mid - i;
        if (j < hi) memcpy(dst + k * size, src + j * size, (hi - j) * size);
      }
      uint8_t *t = src; src = dst; dst = t;
    }
    if (src != base) memcpy(base, src, (size_t)n * size);
    free(buf);
  }
  free(tmp);
}
static void h_bsearch(Cpu *c) {
  uint32_t key = arg(c, 0), base = arg(c, 1), n = arg(c, 2), size = arg(c, 3), cmp = arg(c, 4);
  uint32_t lo = 0, hi = n;
  while (lo < hi) {
    uint32_t mid = (lo + hi) / 2;
    uint32_t a[2] = { key, base + mid * size };
    int32_t r = (int32_t)guest_call(c, cmp, 2, a);
    if (!r) { ret32(c, base + mid * size); return; }
    if (r < 0) hi = mid; else lo = mid + 1;
  }
  ret32(c, 0);
}

// wide characters and locale (C locale)
static void h_wcslen(Cpu *c) { ret32(c, wcslen(G(arg(c, 0)))); }
static void h_wmemchr(Cpu *c) { ret32(c, GA(wmemchr(G(arg(c, 0)), arg(c, 1), arg(c, 2)))); }
static void h_wmemcmp(Cpu *c) { ret32(c, wmemcmp(G(arg(c, 0)), G(arg(c, 1)), arg(c, 2))); }
static void h_wmemcpy(Cpu *c) { wmemcpy(G(arg(c, 0)), G(arg(c, 1)), arg(c, 2)); ret32(c, arg(c, 0)); }
static void h_wmemmove(Cpu *c) { wmemmove(G(arg(c, 0)), G(arg(c, 1)), arg(c, 2)); ret32(c, arg(c, 0)); }
static void h_wmemset(Cpu *c) { wmemset(G(arg(c, 0)), arg(c, 1), arg(c, 2)); ret32(c, arg(c, 0)); }
static void h_wcscoll(Cpu *c) { ret32(c, wcscmp(G(arg(c, 0)), G(arg(c, 1)))); }
static void h_wcsxfrm(Cpu *c) { ret32(c, wcsxfrm(G(arg(c, 0)), G(arg(c, 1)), arg(c, 2))); }
static void h_btowc(Cpu *c) { int ch = (int32_t)arg(c, 0); ret32(c, ch >= 0 && ch < 128 ? ch : (uint32_t)WEOF); }
static void h_wctob(Cpu *c) { uint32_t ch = arg(c, 0); ret32(c, ch < 128 ? ch : (uint32_t)EOF); }
static void h_towlower(Cpu *c) { ret32(c, towlower(arg(c, 0))); }
static void h_towupper(Cpu *c) { ret32(c, towupper(arg(c, 0))); }
static void h_wctype(Cpu *c) { ret32(c, wctype(G(arg(c, 0)))); }
static void h_iswctype(Cpu *c) { ret32(c, iswctype(arg(c, 0), arg(c, 1))); }
static void h_mbrtowc(Cpu *c) {
  uint32_t pwc = arg(c, 0);
  const uint8_t *s = G(arg(c, 1));
  uint32_t n = arg(c, 2);
  if (!s) { ret32(c, 0); return; }
  if (!n) { ret32(c, (uint32_t)-2); return; }
  if (pwc) R32(pwc) = s[0];
  ret32(c, s[0] ? 1 : 0);
}
static void h_wcrtomb(Cpu *c) {
  uint32_t s = arg(c, 0);
  if (!s) { ret32(c, 1); return; }
  uint32_t wc = arg(c, 1);
  if (wc > 255) { ret32(c, (uint32_t)-1); return; }
  R8(s) = (uint8_t)wc;
  ret32(c, 1);
}
static char c_locale[] = "C";
static void h_setlocale(Cpu *c) { ret32(c, GA(c_locale)); }

// ctype tables in bionic's (OpenBSD) format
static char ctype_table[257];
static short tolower_table[257], toupper_table[257];
static uint32_t ctype_ptr, tolower_ptr, toupper_ptr;
static void init_ctype(void) {
  ctype_table[0] = 0;
  for (int ch = 0; ch < 256; ch++) {
    int f = 0;
    if (ch < 128) {
      if (isupper(ch)) f |= 0x01;
      if (islower(ch)) f |= 0x02;
      if (isdigit(ch)) f |= 0x04;
      if (isspace(ch)) f |= 0x08;
      if (ispunct(ch)) f |= 0x10;
      if (iscntrl(ch)) f |= 0x20;
      if (isxdigit(ch) && !isdigit(ch)) f |= 0x40;
      if (ch == ' ') f |= 0x80;
    }
    ctype_table[ch + 1] = (char)f;
    tolower_table[ch + 1] = ch < 128 ? tolower(ch) : ch;
    toupper_table[ch + 1] = ch < 128 ? toupper(ch) : ch;
  }
  tolower_table[0] = toupper_table[0] = -1;
  ctype_ptr = GA(ctype_table);
  tolower_ptr = GA(tolower_table);
  toupper_ptr = GA(toupper_table);
}

// ---------------------------------------------------------------------------
// math (soft-float ABI: doubles in register pairs)

#define MATH1(name) static void h_##name(Cpu *c) { retd(c, name(argd(c, 0))); }
MATH1(acos) MATH1(asin) MATH1(atan) MATH1(ceil) MATH1(cos) MATH1(cosh) MATH1(exp) MATH1(floor)
MATH1(log) MATH1(log10) MATH1(sin) MATH1(sinh) MATH1(sqrt) MATH1(tan) MATH1(tanh)
static void h_atan2(Cpu *c) { retd(c, atan2(argd(c, 0), argd(c, 2))); }
static void h_pow(Cpu *c) { retd(c, pow(argd(c, 0), argd(c, 2))); }
static void h_fmod(Cpu *c) { retd(c, fmod(argd(c, 0), argd(c, 2))); }
static void h_ldexp(Cpu *c) { retd(c, ldexp(argd(c, 0), (int32_t)arg(c, 2))); }
static void h_frexp(Cpu *c) { int e; double d = frexp(argd(c, 0), &e); R32(arg(c, 2)) = e; retd(c, d); }
static void h_modf(Cpu *c) { double ip; double d = modf(argd(c, 0), &ip); memcpy(G(arg(c, 2)), &ip, 8); retd(c, d); }

// ---------------------------------------------------------------------------
// process, C++ runtime, errors

// errno: guest code compares it with Linux's numbers, which Emscripten's libc (the host's) does not use. Each thread
// has its own. A host function that fails sets the host's errno: it is taken over (translated) when the hook returns.
static const short errno_map[][2] = {
  {EPERM, 1}, {ENOENT, 2}, {ESRCH, 3}, {EINTR, 4}, {EIO, 5}, {ENXIO, 6}, {E2BIG, 7}, {ENOEXEC, 8}, {EBADF, 9},
  {ECHILD, 10}, {EAGAIN, 11}, {ENOMEM, 12}, {EACCES, 13}, {EFAULT, 14}, {ENOTBLK, 15}, {EBUSY, 16}, {EEXIST, 17},
  {EXDEV, 18}, {ENODEV, 19}, {ENOTDIR, 20}, {EISDIR, 21}, {EINVAL, 22}, {ENFILE, 23}, {EMFILE, 24}, {ENOTTY, 25},
  {ETXTBSY, 26}, {EFBIG, 27}, {ENOSPC, 28}, {ESPIPE, 29}, {EROFS, 30}, {EMLINK, 31}, {EPIPE, 32}, {EDOM, 33},
  {ERANGE, 34}, {EDEADLK, 35}, {ENAMETOOLONG, 36}, {ENOLCK, 37}, {ENOSYS, 38}, {ENOTEMPTY, 39}, {ELOOP, 40},
  {ENOMSG, 42}, {EIDRM, 43}, {ECHRNG, 44}, {EL2NSYNC, 45}, {EL3HLT, 46}, {EL3RST, 47}, {ELNRNG, 48}, {EUNATCH, 49},
  {ENOCSI, 50}, {EL2HLT, 51}, {EBADE, 52}, {EBADR, 53}, {EXFULL, 54}, {ENOANO, 55}, {EBADRQC, 56}, {EBADSLT, 57},
  {EBFONT, 59}, {ENOSTR, 60}, {ENODATA, 61}, {ETIME, 62}, {ENOSR, 63}, {ENONET, 64}, {ENOPKG, 65}, {EREMOTE, 66},
  {ENOLINK, 67}, {EADV, 68}, {ESRMNT, 69}, {ECOMM, 70}, {EPROTO, 71}, {EMULTIHOP, 72}, {EDOTDOT, 73},
  {EBADMSG, 74}, {EOVERFLOW, 75}, {ENOTUNIQ, 76}, {EBADFD, 77}, {EREMCHG, 78}, {ELIBACC, 79}, {ELIBBAD, 80},
  {ELIBSCN, 81}, {ELIBMAX, 82}, {ELIBEXEC, 83}, {EILSEQ, 84}, {ESTRPIPE, 86}, {EUSERS, 87}, {ENOTSOCK, 88},
  {EDESTADDRREQ, 89}, {EMSGSIZE, 90}, {EPROTOTYPE, 91}, {ENOPROTOOPT, 92}, {EPROTONOSUPPORT, 93},
  {ESOCKTNOSUPPORT, 94}, {EOPNOTSUPP, 95}, {ENOTSUP, 95}, {EPFNOSUPPORT, 96}, {EAFNOSUPPORT, 97}, {EADDRINUSE, 98},
  {EADDRNOTAVAIL, 99}, {ENETDOWN, 100}, {ENETUNREACH, 101}, {ENETRESET, 102}, {ECONNABORTED, 103},
  {ECONNRESET, 104}, {ENOBUFS, 105}, {EISCONN, 106}, {ENOTCONN, 107}, {ESHUTDOWN, 108}, {ETOOMANYREFS, 109},
  {ETIMEDOUT, 110}, {ECONNREFUSED, 111}, {EHOSTDOWN, 112}, {EHOSTUNREACH, 113}, {EALREADY, 114},
  {EINPROGRESS, 115}, {ESTALE, 116}, {EDQUOT, 122}, {ENOMEDIUM, 123}, {EMEDIUMTYPE, 124}, {ECANCELED, 125},
  {ENOKEY, 126}, {EKEYEXPIRED, 127}, {EKEYREVOKED, 128}, {EKEYREJECTED, 129}, {EOWNERDEAD, 130},
  {ENOTRECOVERABLE, 131},
};
int errno_to_linux(int e) {
  if (e <= 0) return e;
  for (size_t i = 0; i < sizeof errno_map / sizeof errno_map[0]; i++) if (errno_map[i][0] == e) return errno_map[i][1];
  return e;
}
static int errno_from_linux(int e) {
  if (e <= 0) return e;
  for (size_t i = 0; i < sizeof errno_map / sizeof errno_map[0]; i++) if (errno_map[i][1] == e) return errno_map[i][0];
  return e;
}
static int errno_early;   // before the main thread exists
static int *guest_errno_ptr(void) { return current_thread ? &current_thread->guest_errno : &errno_early; }
#define guest_errno (*guest_errno_ptr())
void host_errno_sync(void) { if (errno) { guest_errno = errno_to_linux(errno); errno = 0; } }
static uint32_t stack_guard = 0x5A17E3C1;
static uint32_t optarg_var, optind_var = 1;
static void h_errno(Cpu *c) { host_errno_sync(); ret32(c, GA(guest_errno_ptr())); }
static void h_abort(Cpu *c) { fatal("guest abort() from %s", elf_where(c->r[14])); }
static void h_exit(Cpu *c) { fatal("guest exit(%d)", arg(c, 0)); }
static void h_stack_chk_fail(Cpu *c) { fatal("stack smashing detected (called from %s)", elf_where(c->r[14])); }
static void h_cxa_atexit(Cpu *c) { ret32(c, 0); }
static void h_cxa_finalize(Cpu *c) {}
static void h_find_exidx(Cpu *c) {
  Lib *lib = elf_lib_at(arg(c, 0));
  if (!lib) { if (arg(c, 1)) R32(arg(c, 1)) = 0; ret32(c, 0); return; }
  if (arg(c, 1)) R32(arg(c, 1)) = lib->exidx_sz / 8;
  ret32(c, GA(lib->base) + lib->exidx);
}
static void h_getopt(Cpu *c) { ret32(c, (uint32_t)-1); }
// the emulated CPU for getauxval: AT_HWCAP = swp half thumb fastmult vfp edsp neon vfpv3 tls vfpv4 idiva idivt vfpd32
static void h_getauxval(Cpu *c) {
  static const char platform[] = "v7l";
  switch (arg(c, 0)) {
    case 16: ret32(c, 1 | 2 | 4 | 16 | 64 | 128 | 4096 | 8192 | 32768 | 65536 | 131072 | 262144 | 524288); return;
    case 15: ret32(c, GA(platform)); return;
    case 6: ret32(c, 4096); return;   // AT_PAGESZ
    default: ret32(c, 0); return;
  }
}

// setjmp/longjmp: the buffer layout is ours (r4-r11, sp, lr, then d8-d15 and FPSCR: 27 of bionic's 64 words)
static void h_setjmp(Cpu *c) {
  uint32_t *env = G(arg(c, 0));
  for (int i = 0; i < 8; i++) env[i] = c->r[4 + i];
  env[8] = c->r[13];
  env[9] = c->r[14];
  memcpy(env + 10, &c->d[8], 64);
  env[26] = c->fpscr;
  ret32(c, 0);
}
static void h_longjmp(Cpu *c) {
  uint32_t *env = G(arg(c, 0));
  uint32_t val = arg(c, 1);
  for (int i = 0; i < 8; i++) c->r[4 + i] = env[i];
  c->r[13] = env[8];
  c->r[14] = env[9];
  memcpy(&c->d[8], env + 10, 64);
  c->fpscr = env[26];
  c->r[0] = val ? val : 1;
  hook_return(c);
}

// ---------------------------------------------------------------------------
// threads: cooperative, switched only when a thread blocks

Thread *main_thread, *current_thread;
static Thread *threads;
static int next_thread_id = 1;
static int exit_hook;

typedef struct { uint32_t key_vals[64]; } Tls;
static uint32_t tls_main[64];
static uint32_t *tls_of(Cpu *c) { return c->thread && c->thread != main_thread ? ((Tls *)c->thread->stack)->key_vals : tls_main; }
static int next_key;

static void h_thread_exit(Cpu *c) { c->thread->finished = 1; c->stop = 1; }
static void h_pthread_create(Cpu *c) {
  Thread *t = calloc(1, sizeof(Thread));
  t->id = ++next_thread_id;
  uint32_t want = arg(c, 1) ? ((uint32_t *)G(arg(c, 1)))[2] : 0;   // the attribute's stack size
  t->stack_size = want >= (64u << 10) && want <= (16u << 20) ? (want + 4095) & ~4095u : 1u << 20;
  t->cpu.excl = 1;
  t->stack = GA(aligned_alloc(16, t->stack_size));
  memset(G(t->stack), 0, sizeof(Tls));
  t->cpu.thread = t;
  t->cpu.r[0] = arg(c, 3);
  t->cpu.r[13] = t->stack + t->stack_size - 64;
  t->cpu.r[14] = hook_stub_address(exit_hook);
  uint32_t fn = arg(c, 2);
  t->cpu.t = fn & 1;
  t->cpu.r[15] = fn & ~1u;
  t->next = threads;
  threads = t;
  if (arg(c, 0)) R32(arg(c, 0)) = t->id;
  logf_("pthread_create -> thread %d (%s)", t->id, elf_where(fn));
  ret32(c, 0);
}
// ---------------------------------------------------------------------------
// signals between threads (garbage collectors stop the world this way: pthread_kill, a handler that acknowledges
// and waits in sigsuspend for the restart signal). A signal for a thread is delivered when it next runs, as the
// kernel does: its context is saved in a frame on its stack and it continues in the handler, which returns into
// sigreturn. Handlers are process-wide (sigaction).
typedef struct { uint32_t handler, mask, flags; } SigAction;
static SigAction sigactions[65];
static int sigreturn_hook;
enum { SF_PREV, SF_SIG, SF_R0, SF_FLAGS = SF_R0 + 16, SF_FPSCR, SF_EXCL, SF_ACTIVE, SF_D, SF_INFO = SF_D + 64, SF_UC = SF_INFO + 32, SF_WORDS = SF_UC + 64 };
static Thread *thread_of_id(uint32_t id) {
  if (main_thread && (uint32_t)main_thread->id == id) return main_thread;
  for (Thread *t = threads; t; t = t->next) if ((uint32_t)t->id == id) return t;
  return NULL;
}
static void signal_frame(Thread *t, int sig) {
  Cpu *c = &t->cpu;
  SigAction *a = &sigactions[sig];
  uint32_t sp = (c->r[13] - 4 * SF_WORDS - 64) & ~7u, *f = G(sp);
  memset(f, 0, 4 * SF_WORDS);
  f[SF_PREV] = t->sigframe; f[SF_SIG] = (uint32_t)sig;
  for (int i = 0; i < 16; i++) f[SF_R0 + i] = c->r[i];
  f[SF_FLAGS] = c->n | c->z << 1 | c->c << 2 | c->v << 3 | c->q << 4 | c->t << 5 | c->ge << 8 | c->it << 16;
  f[SF_FPSCR] = c->fpscr; f[SF_EXCL] = c->excl; f[SF_ACTIVE] = t->sigmask;
  memcpy(f + SF_D, c->d, 256);
  f[SF_INFO] = (uint32_t)sig;   // siginfo.si_signo; the ucontext stays zero
  t->sigframe = sp;
  t->sigmask |= a->mask | ((a->flags & 0x40000000u) ? 0 : 1u << (sig - 1));   // the handler's mask, and the signal (no SA_NODEFER)
  t->sig_count++;
  c->r[13] = sp;
  c->r[0] = (uint32_t)sig; c->r[1] = sp + 4 * SF_INFO; c->r[2] = sp + 4 * SF_UC;
  c->r[14] = hook_stub_address(sigreturn_hook);
  c->t = a->handler & 1; c->it = 0; c->excl = 1;
  c->r[15] = a->handler & ~1u;
  if (a->flags & 0x80000000u) a->handler = 0;   // SA_RESETHAND
}
static void h_sigreturn(Cpu *c) {
  Thread *t = c->thread;
  if (!t || !t->sigframe) fatal("sigreturn without a signal frame");
  uint32_t *f = G(t->sigframe);
  for (int i = 0; i < 16; i++) c->r[i] = f[SF_R0 + i];
  uint32_t fl = f[SF_FLAGS];
  c->n = fl & 1; c->z = (fl >> 1) & 1; c->c = (fl >> 2) & 1; c->v = (fl >> 3) & 1; c->q = (fl >> 4) & 1; c->t = (fl >> 5) & 1;
  c->ge = (fl >> 8) & 15; c->it = (fl >> 16) & 0xFF;
  c->fpscr = f[SF_FPSCR]; c->excl = 1;
  memcpy(c->d, f + SF_D, 256);
  t->sigmask = f[SF_ACTIVE];
  t->sigframe = f[SF_PREV];
}
/** called before a thread runs: its pending signals, whose handlers are not running on it, start */
void host_deliver_signals(Cpu *c) {
  Thread *t = c->thread;
  if (!t || !t->sigpending) return;
  for (int sig = 1; sig <= 32; sig++) {
    uint32_t bit = 1u << (sig - 1);
    if (!(t->sigpending & bit) || (t->sigmask & bit)) continue;
    t->sigpending &= ~bit;
    if (sigactions[sig].handler > 1) signal_frame(t, sig);
  }
}
static int send_signal(Thread *t, int sig) {
  if (!t || t->finished) return ESRCH;
  if (sig <= 0 || sig > 32) return sig ? EINVAL : 0;
  if (sigactions[sig].handler <= 1) return 0;   // default or ignored: nothing runs
  t->sigpending |= 1u << (sig - 1);
  return 0;
}
static void h_pthread_kill(Cpu *c) { ret32(c, (uint32_t)errno_to_linux(send_signal(thread_of_id(arg(c, 0)), (int)arg(c, 1)))); }
static void sig_result(Cpu *c, int r) { if (r) { set_errno(r); ret32(c, (uint32_t)-1); } else ret32(c, 0); }
static void h_tgkill(Cpu *c) { sig_result(c, send_signal(thread_of_id(arg(c, 1) - 4242), (int)arg(c, 2))); }
static void h_tkill(Cpu *c) { sig_result(c, send_signal(thread_of_id(arg(c, 0) - 4242), (int)arg(c, 1))); }
static void h_sigaction(Cpu *c) {
  int sig = (int)arg(c, 0);
  uint32_t act = arg(c, 1), old = arg(c, 2);
  if (sig <= 0 || sig > 64) { sig_result(c, EINVAL); return; }
  if (old) { uint32_t *o = G(old); o[0] = sigactions[sig].handler; o[1] = sigactions[sig].mask; o[2] = sigactions[sig].flags; o[3] = 0; }
  if (act) { uint32_t *a = G(act); sigactions[sig].handler = a[0]; sigactions[sig].mask = a[1]; sigactions[sig].flags = a[2]; }
  ret32(c, 0);
}
static void h_signal_set(Cpu *c) {   // signal / bsd_signal: returns the previous handler
  int sig = (int)arg(c, 0);
  if (sig <= 0 || sig > 64) { set_errno(EINVAL); ret32(c, (uint32_t)-1); return; }
  uint32_t prev = sigactions[sig].handler;
  sigactions[sig].handler = arg(c, 1); sigactions[sig].flags = 0x10000000; sigactions[sig].mask = 0;
  ret32(c, prev);
}
// sigsuspend(mask): with that mask, waits until a handler has run on this thread, then fails with EINTR
static void h_sigsuspend_wait(Cpu *c) {
  Thread *t = c->thread;
  if (!t) { set_errno(EINTR); ret32(c, (uint32_t)-1); return; }
  if (!t->sig_waiting) {
    t->sig_waiting = 1; t->sig_wait_count = t->sig_count;
    t->sig_saved_mask = t->sigmask;
    if (arg(c, 0)) t->sigmask = R32(arg(c, 0));
    c->blocked = 1;
    return;
  }
  if (t->sig_count == t->sig_wait_count) { c->blocked = 1; return; }
  t->sig_waiting = 0;
  t->sigmask = t->sig_saved_mask;
  set_errno(EINTR);
  ret32(c, (uint32_t)-1);
}
static void h_raise_sig(Cpu *c) {
  int sig = (int)arg(c, 0);
  if (sig > 0 && sig <= 32 && sigactions[sig].handler > 1) { send_signal(current_thread, sig); host_deliver_signals(c); ret32(c, 0); return; }
  logf_("guest raise(%d)", sig);
  if (sig == 6) fatal("abort() (raise(SIGABRT))");
  ret32(c, 0);
}

// pthread_sigmask / sigprocmask(how, set, old): the calling thread's mask (SIG_BLOCK 0, SIG_UNBLOCK 1, SIG_SETMASK 2)
static void h_sigmask(Cpu *c) {
  Thread *t = c->thread;
  uint32_t how = arg(c, 0), set = arg(c, 1), old = arg(c, 2);
  if (!t) { ret32(c, 0); return; }
  if (old) R32(old) = t->sigmask;
  if (set) {
    uint32_t v = R32(set);
    if (how == 0) t->sigmask |= v; else if (how == 1) t->sigmask &= ~v; else if (how == 2) t->sigmask = v;
  }
  ret32(c, 0);
}

uint64_t sched_progress;   // instructions the other threads ran (to notice when nothing can run)
void sched_run_others(uint64_t budget) {
  for (Thread *t = threads; t; t = t->next) {
    if (t->finished || t->running) continue;
    Thread *prev = current_thread;
    current_thread = t;
    t->running = 1;
    t->cpu.blocked = 0;
    t->cpu.excl = 1;   // a context switch clears the exclusive monitor (as the kernel's CLREX does)
    host_deliver_signals(&t->cpu);
    uint64_t start = t->cpu.icount;
    while (!t->cpu.blocked && !t->finished && t->cpu.icount - start < budget) {
      cpu_run(&t->cpu, budget - (t->cpu.icount - start));
      if (t->cpu.stop && !t->finished) t->cpu.stop = 0;
    }
    sched_progress += t->cpu.icount - start;
    t->running = 0;
    current_thread = prev;
  }
}
// Mutexes (threads interleave at any instruction, so they must exclude). The 32-bit word keeps bionic's type bits
// (14-15: 0 normal, 1 recursive, 2 error-checking, as the static initializers set them); bits 16-31 hold the owner
// (thread id + 1), bits 0-13 the recursion count. A normal mutex its owner locks again counts too (instead of a hang).
static uint32_t self_tag(Cpu *c) { return (uint32_t)(c->thread ? c->thread->id : 0) + 1; }
static int mutex_take(Cpu *c, uint32_t m) {   // 0 locked, else EBUSY or EDEADLK
  uint32_t v = R32(m), owner = v >> 16, type = (v >> 14) & 3;
  if (!owner) { R32(m) = (self_tag(c) << 16) | (v & 0xC000) | 1; return 0; }
  if (owner == self_tag(c)) {
    if (type == 2) return EDEADLK;
    R32(m) = v + 1;
    return 0;
  }
  return EBUSY;
}
static void mutex_release(Cpu *c, uint32_t m) {
  uint32_t v = R32(m);
  if ((v >> 16) != self_tag(c)) return;
  if ((v & 0x3FFF) > 1) R32(m) = v - 1;
  else R32(m) = v & 0xC000;
}
static void h_mutex_init(Cpu *c) { uint32_t a = arg(c, 1); R32(arg(c, 0)) = a ? (R32(a) & 3) << 14 : 0; ret32(c, 0); }
static void h_mutex_lock(Cpu *c) { int r = mutex_take(c, arg(c, 0)); if (r == EBUSY) c->blocked = 1; else ret32(c, (uint32_t)errno_to_linux(r)); }
static void h_mutex_trylock(Cpu *c) { ret32(c, (uint32_t)errno_to_linux(mutex_take(c, arg(c, 0)))); }
static void h_mutex_unlock(Cpu *c) { mutex_release(c, arg(c, 0)); ret32(c, 0); }
static void h_mutexattr_settype(Cpu *c) { R32(arg(c, 0)) = arg(c, 1) & 3; ret32(c, 0); }
static void h_mutexattr_init(Cpu *c) { R32(arg(c, 0)) = 0; ret32(c, 0); }
// rwlocks: word 0 the writer (thread id + 1), word 1 the number of readers
static void h_rwlock_rdlock(Cpu *c) { uint32_t *l = G(arg(c, 0)); if (l[0] && l[0] != self_tag(c)) { c->blocked = 1; return; } l[1]++; ret32(c, 0); }
static void h_rwlock_wrlock(Cpu *c) {
  uint32_t *l = G(arg(c, 0));
  if ((l[0] && l[0] != self_tag(c)) || l[1]) { c->blocked = 1; return; }
  l[0] = self_tag(c); ret32(c, 0);
}
static void h_rwlock_tryrdlock(Cpu *c) { uint32_t *l = G(arg(c, 0)); if (l[0] && l[0] != self_tag(c)) { ret32(c, errno_to_linux(EBUSY)); return; } l[1]++; ret32(c, 0); }
static void h_rwlock_trywrlock(Cpu *c) { uint32_t *l = G(arg(c, 0)); if ((l[0] && l[0] != self_tag(c)) || l[1]) { ret32(c, errno_to_linux(EBUSY)); return; } l[0] = self_tag(c); ret32(c, 0); }
static void h_rwlock_unlock(Cpu *c) { uint32_t *l = G(arg(c, 0)); if (l[0] == self_tag(c)) l[0] = 0; else if (l[1]) l[1]--; ret32(c, 0); }
static void h_rwlock_init(Cpu *c) { memset(G(arg(c, 0)), 0, 8); ret32(c, 0); }
static void h_cond_broadcast(Cpu *c) { ret32(c, 0); }
static void h_cond_wait(Cpu *c) { c->blocked = 1; }  // woken by retrying
static void h_key_create(Cpu *c) { R32(arg(c, 0)) = next_key++; ret32(c, 0); }
static void h_key_delete(Cpu *c) { ret32(c, 0); }
static void h_getspecific(Cpu *c) { uint32_t k = arg(c, 0); ret32(c, k < 64 ? tls_of(c)[k] : 0); }
static void h_setspecific(Cpu *c) { uint32_t k = arg(c, 0); if (k < 64) tls_of(c)[k] = arg(c, 1); ret32(c, 0); }
static void h_pthread_once(Cpu *c) {   // 0 not run, 1 running (others wait), 2 done
  uint32_t once = arg(c, 0);
  if (R32(once) == 1) { c->blocked = 1; return; }
  if (!R32(once)) { R32(once) = 1; guest_call(c, arg(c, 1), 0, NULL); R32(once) = 2; }
  ret32(c, 0);
}
static void h_sem_init(Cpu *c) { R32(arg(c, 0)) = arg(c, 2); ret32(c, 0); }
static void h_sem_destroy(Cpu *c) { ret32(c, 0); }
static void h_sem_post(Cpu *c) { R32(arg(c, 0))++; ret32(c, 0); }
static void h_sem_wait(Cpu *c) {
  uint32_t s = arg(c, 0);
  if (R32(s)) { R32(s)--; ret32(c, 0); }
  else c->blocked = 1;
}
static void h_sem_timedwait(Cpu *c) {   // waits while other threads run; times out when nothing else can run
  uint32_t s = arg(c, 0);
  if (R32(s)) { R32(s)--; ret32(c, 0); return; }
  sched_run_others(2000000);
  if (R32(s)) { R32(s)--; ret32(c, 0); return; }
  set_errno(ETIMEDOUT);
  ret32(c, (uint32_t)-1);
}

// ---------------------------------------------------------------------------
// zlib: the guest's z_stream has the same 32-bit layout as the host's

typedef struct { uint32_t next_in, avail_in, total_in, next_out, avail_out, total_out, msg, state, zalloc, zfree, opaque, data_type, adler, reserved; } GZs;
static void zs_host_alloc(uint32_t p) { GZs *z = G(p); z->zalloc = 0; z->zfree = 0; z->opaque = 0; }
static void h_inflateInit_(Cpu *c) { zs_host_alloc(arg(c, 0)); ret32(c, inflateInit_(G(arg(c, 0)), G(arg(c, 1)), arg(c, 2))); }
static void h_inflateInit2_(Cpu *c) { zs_host_alloc(arg(c, 0)); ret32(c, inflateInit2_(G(arg(c, 0)), arg(c, 1), G(arg(c, 2)), arg(c, 3))); }
static void h_deflateInit_(Cpu *c) { zs_host_alloc(arg(c, 0)); ret32(c, deflateInit_(G(arg(c, 0)), arg(c, 1), G(arg(c, 2)), arg(c, 3))); }
static void h_deflateInit2_(Cpu *c) {
  zs_host_alloc(arg(c, 0));
  ret32(c, deflateInit2_(G(arg(c, 0)), arg(c, 1), arg(c, 2), arg(c, 3), arg(c, 4), arg(c, 5), G(arg(c, 6)), arg(c, 7)));
}
static void h_inflate(Cpu *c) { ret32(c, inflate(G(arg(c, 0)), arg(c, 1))); }
static void h_inflateEnd(Cpu *c) { ret32(c, inflateEnd(G(arg(c, 0)))); }
static void h_inflateReset(Cpu *c) { ret32(c, inflateReset(G(arg(c, 0)))); }
static void h_inflateSync(Cpu *c) { ret32(c, inflateSync(G(arg(c, 0)))); }
static void h_inflateCopy(Cpu *c) { ret32(c, inflateCopy(G(arg(c, 0)), G(arg(c, 1)))); }
static void h_deflate(Cpu *c) { ret32(c, deflate(G(arg(c, 0)), arg(c, 1))); }
static void h_deflateEnd(Cpu *c) { ret32(c, deflateEnd(G(arg(c, 0)))); }
static void h_deflateReset(Cpu *c) { ret32(c, deflateReset(G(arg(c, 0)))); }
static void h_deflateParams(Cpu *c) { ret32(c, deflateParams(G(arg(c, 0)), arg(c, 1), arg(c, 2))); }
static void h_crc32(Cpu *c) { ret32(c, crc32(arg(c, 0), G(arg(c, 1)), arg(c, 2))); }
static void h_uncompress(Cpu *c) {
  uLongf n = R32(arg(c, 1));
  int r = uncompress(G(arg(c, 0)), &n, G(arg(c, 2)), arg(c, 3));
  R32(arg(c, 1)) = (uint32_t)n;
  ret32(c, r);
}
static void h_gzopen(Cpu *c) { ret32(c, GA(gzopen(G(arg(c, 0)), G(arg(c, 1))))); }
static void h_gzread(Cpu *c) { ret32(c, gzread(G(arg(c, 0)), G(arg(c, 1)), arg(c, 2))); }
static void h_gzclose(Cpu *c) { ret32(c, gzclose(G(arg(c, 0)))); }


// ---------------------------------------------------------------------------- more of bionic's libc
// Guest and host share one address space, so most functions are thin wrappers. Differences handled here: 32-bit
// time_t and off_t on the guest, bionic's struct layouts where they differ, and no network or processes.
static void set_errno(int e) { guest_errno = errno_to_linux(e); errno = 0; }
#define FAIL(e) do { set_errno(e); ret32(c, (uint32_t)-1); return; } while (0)

// strings and characters
static void h_strrchr(Cpu *c) { ret32(c, GA(strrchr(G(arg(c, 0)), (int)arg(c, 1)))); }
static void h_strdup(Cpu *c) { ret32(c, GA(strdup(G(arg(c, 0))))); }
static void h_strndup(Cpu *c) { ret32(c, GA(strndup(G(arg(c, 0)), arg(c, 1)))); }
static void h_strcasecmp(Cpu *c) { ret32(c, strcasecmp(G(arg(c, 0)), G(arg(c, 1)))); }
static void h_strncasecmp(Cpu *c) { ret32(c, strncasecmp(G(arg(c, 0)), G(arg(c, 1)), arg(c, 2))); }
static void h_strncat(Cpu *c) { ret32(c, GA(strncat(G(arg(c, 0)), G(arg(c, 1)), arg(c, 2)))); }
static void h_strlcpy(Cpu *c) {
  char *d = G(arg(c, 0)); const char *src = G(arg(c, 1)); size_t n = arg(c, 2), l = strlen(src);
  if (n) { size_t k = l < n - 1 ? l : n - 1; memcpy(d, src, k); d[k] = 0; }
  ret32(c, (uint32_t)l);
}
static void h_strlcat(Cpu *c) {
  char *d = G(arg(c, 0)); const char *src = G(arg(c, 1)); size_t n = arg(c, 2), dl = strnlen(d, n), l = strlen(src);
  if (dl < n) { size_t k = l < n - dl - 1 ? l : n - dl - 1; memcpy(d + dl, src, k); d[dl + k] = 0; }
  ret32(c, (uint32_t)(dl + l));
}
static void h_strspn(Cpu *c) { ret32(c, strspn(G(arg(c, 0)), G(arg(c, 1)))); }
static void h_strcspn(Cpu *c) { ret32(c, strcspn(G(arg(c, 0)), G(arg(c, 1)))); }
static void h_strnlen(Cpu *c) { ret32(c, strnlen(G(arg(c, 0)), arg(c, 1))); }
static void h_strsep(Cpu *c) {
  uint32_t pp = arg(c, 0); char *s = G(R32(pp));
  char *r = strsep(&s, G(arg(c, 1)));
  R32(pp) = GA(s);
  ret32(c, GA(r));
}
static void h_strtok_r(Cpu *c) {
  uint32_t sp = arg(c, 2); char *save = G(R32(sp));
  char *r = strtok_r(G(arg(c, 0)), G(arg(c, 1)), &save);
  R32(sp) = GA(save);
  ret32(c, GA(r));
}
static void h_strerror_r(Cpu *c) { snprintf(G(arg(c, 1)), arg(c, 2), "%s", strerror(errno_from_linux((int)arg(c, 0)))); ret32(c, 0); }
static void h_memrchr(Cpu *c) { ret32(c, GA(memrchr(G(arg(c, 0)), (int)arg(c, 1), arg(c, 2)))); }
static void h_bcopy(Cpu *c) { memmove(G(arg(c, 1)), G(arg(c, 0)), arg(c, 2)); }
static void h_bzero(Cpu *c) { memset(G(arg(c, 0)), 0, arg(c, 1)); }
static void h_strcasestr(Cpu *c) { ret32(c, GA(strcasestr(G(arg(c, 0)), G(arg(c, 1))))); }
// strtol & co. write the end pointer back as a guest pointer
#define STRTO32(name, T) static void h_##name(Cpu *c) { char *e; T v = name(G(arg(c, 0)), &e, (int)arg(c, 2)); if (arg(c, 1)) R32(arg(c, 1)) = GA(e); ret32(c, (uint32_t)v); }
#define STRTO64(name, T) static void h_##name(Cpu *c) { char *e; T v = name(G(arg(c, 0)), &e, (int)arg(c, 2)); if (arg(c, 1)) R32(arg(c, 1)) = GA(e); ret64(c, (uint64_t)v); }
static long strtol32(const char *s, char **e, int b) { long long v = strtoll(s, e, b); if (v > 0x7fffffffLL) { errno = ERANGE; return 0x7fffffff; } if (v < -0x80000000LL) { errno = ERANGE; return -0x7fffffff - 1; } return (long)v; }
static unsigned long strtoul32(const char *s, char **e, int b) { unsigned long long v = strtoull(s, e, b); if (v > 0xffffffffULL && v < 0xffffffff00000000ULL) { errno = ERANGE; return 0xffffffffUL; } return (unsigned long)v; }
#define strtol strtol32
#define strtoul strtoul32
STRTO32(strtol, long) STRTO32(strtoul, unsigned long)
#undef strtol
#undef strtoul
STRTO64(strtoll, long long) STRTO64(strtoull, unsigned long long) STRTO64(strtoimax, long long) STRTO64(strtoumax, unsigned long long)
static void h_strtof(Cpu *c) { char *e; float v = strtof(G(arg(c, 0)), &e); if (arg(c, 1)) R32(arg(c, 1)) = GA(e); retf(c, v); }
static void h_atol(Cpu *c) { ret32(c, (uint32_t)atol(G(arg(c, 0)))); }
static void h_atoll(Cpu *c) { ret64(c, (uint64_t)atoll(G(arg(c, 0)))); }
static void h_atof(Cpu *c) { retd(c, atof(G(arg(c, 0)))); }
static void h_abs(Cpu *c) { ret32(c, (uint32_t)abs((int)arg(c, 0))); }
static void h_labs(Cpu *c) { ret32(c, (uint32_t)abs((int)arg(c, 0))); }
static void h_rand(Cpu *c) { ret32(c, (uint32_t)(rand() & 0x7fffffff)); }
static void h_srand(Cpu *c) { srand(arg(c, 0)); }
static void h_random(Cpu *c) { ret32(c, (uint32_t)(random() & 0x7fffffff)); }
static void h_srandom(Cpu *c) { srandom(arg(c, 0)); }
static void h_arc4random(Cpu *c) { ret32(c, ((uint32_t)rand() << 16) ^ (uint32_t)rand()); }
static void h_drand48(Cpu *c) { retd(c, drand48()); }
#define CTYPE(f) static void h_##f(Cpu *c) { int ch = (int)arg(c, 0); ret32(c, (uint32_t)(ch >= -1 && ch < 256 ? f(ch) : 0)); }
CTYPE(isalpha) CTYPE(isdigit) CTYPE(isspace) CTYPE(isalnum) CTYPE(isupper) CTYPE(islower) CTYPE(isxdigit)
CTYPE(ispunct) CTYPE(isprint) CTYPE(iscntrl) CTYPE(isgraph) CTYPE(isblank)
static void h_toupper(Cpu *c) { ret32(c, (uint32_t)toupper((int)arg(c, 0))); }
static void h_tolower(Cpu *c) { ret32(c, (uint32_t)tolower((int)arg(c, 0))); }

// math: doubles in register pairs, floats as raw words (soft-float ABI)
#define MATHF1(n) static void h_##n(Cpu *c) { retf(c, n(argf(c, 0))); }
#define MATHF2(n) static void h_##n(Cpu *c) { retf(c, n(argf(c, 0), argf(c, 1))); }
#define MATHD2(n) static void h_##n(Cpu *c) { retd(c, n(argd(c, 0), argd(c, 2))); }
#define MATHD1(n) static void h_##n(Cpu *c) { retd(c, n(argd(c, 0))); }
MATHF1(sinf) MATHF1(cosf) MATHF1(tanf) MATHF1(asinf) MATHF1(acosf) MATHF1(atanf) MATHF1(expf) MATHF1(logf) MATHF1(log10f)
MATHF1(sqrtf) MATHF1(floorf) MATHF1(ceilf) MATHF1(fabsf) MATHF1(roundf) MATHF1(truncf) MATHF1(sinhf) MATHF1(coshf) MATHF1(tanhf)
MATHF1(exp2f) MATHF1(log2f) MATHF1(cbrtf) MATHF1(rintf) MATHF1(nearbyintf)
MATHF2(atan2f) MATHF2(powf) MATHF2(fmodf) MATHF2(fminf) MATHF2(fmaxf) MATHF2(hypotf) MATHF2(copysignf)
MATHD1(fabs) MATHD1(round) MATHD1(trunc) MATHD1(exp2) MATHD1(log2) MATHD1(cbrt) MATHD1(rint) MATHD1(nearbyint)
MATHD1(asinh) MATHD1(acosh) MATHD1(atanh) MATHD1(expm1) MATHD1(log1p) MATHD1(tgamma) MATHD1(lgamma) MATHD1(erf) MATHD1(erfc)
MATHD2(hypot) MATHD2(fmin) MATHD2(fmax) MATHD2(copysign) MATHD2(remainder) MATHD2(fdim) MATHD2(nextafter)
static void h_lround(Cpu *c) { ret32(c, (uint32_t)lround(argd(c, 0))); }
static void h_lroundf(Cpu *c) { ret32(c, (uint32_t)lroundf(argf(c, 0))); }
static void h_lrint(Cpu *c) { ret32(c, (uint32_t)lrint(argd(c, 0))); }
static void h_lrintf(Cpu *c) { ret32(c, (uint32_t)lrintf(argf(c, 0))); }
static void h_ldexpf(Cpu *c) { retf(c, ldexpf(argf(c, 0), (int)arg(c, 1))); }
static void h_scalbn(Cpu *c) { retd(c, scalbn(argd(c, 0), (int)arg(c, 2))); }
static void h_scalbnf(Cpu *c) { retf(c, scalbnf(argf(c, 0), (int)arg(c, 1))); }
static void h_frexpf(Cpu *c) { int e; float r = frexpf(argf(c, 0), &e); R32(arg(c, 1)) = (uint32_t)e; retf(c, r); }
static void h_modff(Cpu *c) { float ip; float r = modff(argf(c, 0), &ip); memcpy(G(arg(c, 1)), &ip, 4); retf(c, r); }
static void h_sincos(Cpu *c) { double x = argd(c, 0), s = sin(x), co = cos(x); memcpy(G(arg(c, 2)), &s, 8); memcpy(G(arg(c, 3)), &co, 8); }
static void h_sincosf(Cpu *c) { float x = argf(c, 0), s = sinf(x), co = cosf(x); memcpy(G(arg(c, 1)), &s, 4); memcpy(G(arg(c, 2)), &co, 4); }
static void h_isnan(Cpu *c) { ret32(c, isnan(argd(c, 0))); }
static void h_isinf(Cpu *c) { ret32(c, isinf(argd(c, 0)) ? 1 : 0); }
static void h_isnanf(Cpu *c) { ret32(c, isnan(argf(c, 0))); }
static void h_finite(Cpu *c) { ret32(c, isfinite(argd(c, 0))); }

// stdio
static void h_puts(Cpu *c) { ret32(c, (uint32_t)puts(G(arg(c, 0)))); }
static void h_putchar(Cpu *c) { ret32(c, (uint32_t)putchar((int)arg(c, 0))); }
static void h_perror(Cpu *c) { const char *s = G(arg(c, 0)); logf_("%s%s%s", s ? s : "", s ? ": " : "", strerror(errno_from_linux(guest_errno))); }
static void h_fgets(Cpu *c) { ret32(c, GA(fgets(G(arg(c, 0)), (int)arg(c, 1), mapf(arg(c, 2))))); }
static void h_fgetc(Cpu *c) { ret32(c, (uint32_t)fgetc(mapf(arg(c, 0)))); }
static void h_getchar(Cpu *c) { ret32(c, (uint32_t)EOF); }
static void h_feof(Cpu *c) { ret32(c, (uint32_t)feof(mapf(arg(c, 0)))); }
static void h_ferror(Cpu *c) { ret32(c, (uint32_t)ferror(mapf(arg(c, 0)))); }
static void h_clearerr(Cpu *c) { clearerr(mapf(arg(c, 0))); }
static void h_rewind(Cpu *c) { rewind(mapf(arg(c, 0))); }
static void h_fileno(Cpu *c) { ret32(c, (uint32_t)fileno(mapf(arg(c, 0)))); }
static void h_ftello(Cpu *c) { ret32(c, (uint32_t)ftello(mapf(arg(c, 0)))); }
static void h_fseeko(Cpu *c) { ret32(c, (uint32_t)fseeko(mapf(arg(c, 0)), (int32_t)arg(c, 1), (int)arg(c, 2))); }
static void h_freopen(Cpu *c) { FILE *f = freopen(G(arg(c, 0)), G(arg(c, 1)), mapf(arg(c, 2))); ret32(c, f ? arg(c, 2) : 0); }
static void h_tmpfile(Cpu *c) { ret32(c, GA(tmpfile())); }
static void h_vprintf(Cpu *c) { Va va; va_from_list(&va, arg(c, 1)); char *s = gformat_alloc(G(arg(c, 0)), &va); fputs(s, stdout); ret32(c, (uint32_t)strlen(s)); free(s); }
static void h_asprintf(Cpu *c) { Va va; va_from_args(&va, c, 2); char *s = gformat_alloc(G(arg(c, 1)), &va); R32(arg(c, 0)) = GA(s); ret32(c, (uint32_t)strlen(s)); }
static void h_vasprintf(Cpu *c) { Va va; va_from_list(&va, arg(c, 2)); char *s = gformat_alloc(G(arg(c, 1)), &va); R32(arg(c, 0)) = GA(s); ret32(c, (uint32_t)strlen(s)); }
static void h_dprintf(Cpu *c) { Va va; va_from_args(&va, c, 2); char *s = gformat_alloc(G(arg(c, 1)), &va); ret32(c, (uint32_t)write((int)arg(c, 0), s, strlen(s))); free(s); }

// files and directories
static void h_unlink(Cpu *c) { ret32(c, (uint32_t)unlink(G(arg(c, 0)))); }
static void h_remove(Cpu *c) { ret32(c, (uint32_t)remove(G(arg(c, 0)))); }
static void h_rename(Cpu *c) { ret32(c, (uint32_t)rename(G(arg(c, 0)), G(arg(c, 1)))); }
static void h_rmdir(Cpu *c) { ret32(c, (uint32_t)rmdir(G(arg(c, 0)))); }
static void h_mkdir(Cpu *c) { ret32(c, (uint32_t)mkdir(G(arg(c, 0)), arg(c, 1))); }
static void h_chdir(Cpu *c) { ret32(c, (uint32_t)chdir(G(arg(c, 0)))); }
static void h_access(Cpu *c) { ret32(c, (uint32_t)access(proc_path(G(arg(c, 0))), (int)arg(c, 1))); }
static void h_lstat(Cpu *c) { struct stat st; int r = lstat(proc_path(G(arg(c, 0))), &st); if (!r) put_stat(arg(c, 1), &st); ret32(c, (uint32_t)r); }
static void h_ok0(Cpu *c) { ret32(c, 0); }
static void h_umask(Cpu *c) { ret32(c, 022); }
static void h_ftruncate(Cpu *c) { ret32(c, (uint32_t)ftruncate((int)arg(c, 0), (int32_t)arg(c, 1))); }
static void h_isatty(Cpu *c) { ret32(c, 0); }
static void h_dup(Cpu *c) { ret32(c, (uint32_t)dup((int)arg(c, 0))); }
static void h_dup2(Cpu *c) { ret32(c, (uint32_t)dup2((int)arg(c, 0), (int)arg(c, 1))); }
static void h_fsync(Cpu *c) { ret32(c, 0); }
static void h_realpath(Cpu *c) {
  char *buf = arg(c, 1) ? G(arg(c, 1)) : malloc(4096);
  char *r = realpath(G(arg(c, 0)), buf);
  if (!r && !arg(c, 1)) free(buf);
  ret32(c, GA(r));
}
static void h_mkstemp(Cpu *c) { ret32(c, (uint32_t)mkstemp(G(arg(c, 0)))); }
static void h_opendir(Cpu *c) { ret32(c, GA(opendir(proc_path(G(arg(c, 0)))))); }
static void h_fdopendir(Cpu *c) { ret32(c, GA(fdopendir((int)arg(c, 0)))); }
static void h_closedir(Cpu *c) { ret32(c, (uint32_t)closedir(G(arg(c, 0)))); }
static void h_rewinddir(Cpu *c) { rewinddir(G(arg(c, 0))); }
// bionic's struct dirent {u64 ino; s64 off; u16 reclen; u8 type; char name[256]} has musl's layout
static void h_readdir(Cpu *c) { ret32(c, GA(readdir(G(arg(c, 0))))); }
// /proc/self/maps and /proc/self/stat describe this process as it is now (garbage collectors read them for their
// roots and the main stack); written when they are opened. /proc/<pid>/... is /proc/self/...
static const char *proc_path(const char *p) {
  static char buf[256];
  if (!p || strncmp(p, "/proc/", 6)) return p;
  const char *rest = p + 6;
  if (!strncmp(rest, "self/", 5)) rest += 5;
  else if (rest[0] >= '0' && rest[0] <= '9') { while (*rest >= '0' && *rest <= '9') rest++; if (*rest != '/') return p; rest++; }
  else if (!strncmp(rest, "thread-self/", 12)) rest += 12;
  else return p;
  snprintf(buf, sizeof buf, "/proc/self/%s", rest);
  if (!strcmp(rest, "maps") || !strcmp(rest, "smaps")) {
    FILE *f = fopen(buf, "w");
    if (f) {
      for (int i = 0; i < nlibs; i++)
        fprintf(f, "%08x-%08x rwxp 00000000 00:00 %d /data/app-lib/%s\n", GA(libs[i].base), GA(libs[i].base) + libs[i].size, i + 1, libs[i].name);
      for (int i = 0; i < nmaps; i++) fprintf(f, "%08x-%08x rw-p 00000000 00:00 0 \n", maps[i].addr, maps[i].addr + maps[i].len);
      for (Thread *t = threads; t; t = t->next) fprintf(f, "%08x-%08x rw-p 00000000 00:00 0 [stack:%d]\n", t->stack, t->stack + t->stack_size, t->id);
      if (main_thread) fprintf(f, "%08x-%08x rw-p 00000000 00:00 0 [stack]\n", main_thread->stack, main_thread->stack + main_thread->stack_size);
      fprintf(f, "ffff0000-ffff1000 r-xp 00000000 00:00 0 [vectors]\n");
      fclose(f);
    }
  } else if (!strcmp(rest, "stat")) {
    FILE *f = fopen(buf, "w");
    if (f) {
      uint32_t top = main_thread ? main_thread->stack + main_thread->stack_size - 256 : 0;
      fprintf(f, "1 (app_process) R 0 1 1 0 -1 4194560 0 0 0 0 0 0 0 0 20 0 1 0 0 134217728 8192 4294967295 4096 4096 %u 0 0 0 0 0 0 0 0 0 17 0 0 0 0 0 0\n", top);
      fclose(f);
    }
  }
  return buf;
}

static void h_unsupported(Cpu *c) { FAIL(ENOSYS); }

// more of libc
static void h_fscanf(Cpu *c) {   // a line at a time, through sscanf, then back to what it did not consume
  FILE *f = mapf(arg(c, 0));
  Va va; va_from_args(&va, c, 2);
  long pos = ftell(f);
  char line[4096];
  if (!fgets(line, sizeof line, f)) { ret32(c, (uint32_t)-1); return; }
  int used = 0, r = scan_core(line, G(arg(c, 1)), &va, &used);
  if (pos >= 0) fseek(f, pos + used, SEEK_SET);
  ret32(c, (uint32_t)r);
}
static void h___srget(Cpu *c) { ret32(c, (uint32_t)fgetc(mapf(arg(c, 0)))); }
static void h___swbuf(Cpu *c) { char ch = (char)arg(c, 0); fwrite_g(&ch, 1, 1, mapf(arg(c, 1))); ret32(c, (uint8_t)ch); }
static void h_memmem(Cpu *c) { ret32(c, GA(memmem(G(arg(c, 0)), arg(c, 1), G(arg(c, 2)), arg(c, 3)))); }
static void h_basename(Cpu *c) {
  static char buf[1024];
  const char *p = G(arg(c, 0));
  if (!p || !*p) { strcpy(buf, "."); ret32(c, GA(buf)); return; }
  snprintf(buf, sizeof buf, "%s", p);
  size_t n = strlen(buf);
  while (n > 1 && buf[n - 1] == '/') buf[--n] = 0;
  char *s = strrchr(buf, '/');
  ret32(c, GA(s && s[1] ? s + 1 : buf));
}
static void h_dirname(Cpu *c) {
  static char buf[1024];
  const char *p = G(arg(c, 0));
  snprintf(buf, sizeof buf, "%s", p && *p ? p : ".");
  size_t n = strlen(buf);
  while (n > 1 && buf[n - 1] == '/') buf[--n] = 0;
  char *s = strrchr(buf, '/');
  if (!s) strcpy(buf, ".");
  else if (s == buf) buf[1] = 0;
  else *s = 0;
  ret32(c, GA(buf));
}
static void h_writev(Cpu *c) {
  int fd = arg(c, 0);
  const uint32_t *iov = G(arg(c, 1));
  uint32_t total = 0;
  for (uint32_t i = 0; i < arg(c, 2); i++) {
    uint32_t base = iov[2 * i], len = iov[2 * i + 1];
    if (fd == 1 || fd == 2) out_text(G(base), len);
    else if (write(fd, G(base), len) < 0) { set_errno(errno); ret32(c, (uint32_t)-1); return; }
    total += len;
  }
  ret32(c, total);
}
static void h_readv(Cpu *c) {
  const uint32_t *iov = G(arg(c, 1));
  uint32_t total = 0;
  for (uint32_t i = 0; i < arg(c, 2); i++) {
    ssize_t n = read(arg(c, 0), G(iov[2 * i]), iov[2 * i + 1]);
    if (n < 0) { set_errno(errno); ret32(c, (uint32_t)-1); return; }
    total += (uint32_t)n;
    if ((uint32_t)n < iov[2 * i + 1]) break;
  }
  ret32(c, total);
}
static void h___assert2(Cpu *c) {
  fatal("assertion \"%s\" failed in %s at %s:%u", (const char *)G(arg(c, 3)), (const char *)G(arg(c, 2)), (const char *)G(arg(c, 0)), arg(c, 1));
}
static void h___assert(Cpu *c) { fatal("assertion \"%s\" failed at %s:%u", (const char *)G(arg(c, 2)), (const char *)G(arg(c, 0)), arg(c, 1)); }
static void h_getdtablesize(Cpu *c) { ret32(c, 1024); }
static void h_syslog(Cpu *c) {
  Va va; va_from_args(&va, c, 2);
  char *s = gformat_alloc(G(arg(c, 1)), &va);
  logf_("syslog: %s", s);
  free(s);
}
static void h_inet_aton(Cpu *c) {
  unsigned a, b, cc, d;
  if (sscanf(G(arg(c, 0)), "%u.%u.%u.%u", &a, &b, &cc, &d) != 4 || a > 255 || b > 255 || cc > 255 || d > 255) { ret32(c, 0); return; }
  if (arg(c, 1)) R32(arg(c, 1)) = a | b << 8 | cc << 16 | d << 24;
  ret32(c, 1);
}
static void h_getnameinfo(Cpu *c) { ret32(c, (uint32_t)-4); }   // EAI_FAIL: there is no network name service
static void h_hstrerror(Cpu *c) { ret32(c, GA("Unknown host")); }
static int guest_h_errno;
static void h___get_h_errno(Cpu *c) { ret32(c, GA(&guest_h_errno)); }
static void h_tmpnam(Cpu *c) {
  static char buf[64]; static int n;
  snprintf(buf, sizeof buf, "/data/local/tmp/tmp%d", ++n);
  mkdir("/data", 0777); mkdir("/data/local", 0777); mkdir("/data/local/tmp", 0777);
  if (arg(c, 0)) { strcpy(G(arg(c, 0)), buf); ret32(c, arg(c, 0)); } else ret32(c, GA(buf));
}
static void h_iswspace(Cpu *c) { ret32(c, iswspace(arg(c, 0)) != 0); }
static void h_iswalpha(Cpu *c) { ret32(c, iswalpha(arg(c, 0)) != 0); }
static void h_iswdigit(Cpu *c) { ret32(c, iswdigit(arg(c, 0)) != 0); }
static void h_iswupper(Cpu *c) { ret32(c, iswupper(arg(c, 0)) != 0); }
static void h_iswlower(Cpu *c) { ret32(c, iswlower(arg(c, 0)) != 0); }
static void h_iswalnum(Cpu *c) { ret32(c, iswalnum(arg(c, 0)) != 0); }
static void h_iswpunct(Cpu *c) { ret32(c, iswpunct(arg(c, 0)) != 0); }
static void h_iswprint(Cpu *c) { ret32(c, iswprint(arg(c, 0)) != 0); }
static void h_mbstowcs(Cpu *c) {   // UTF-8 to 32-bit wchar_t (bionic)
  const unsigned char *s = G(arg(c, 1));
  uint32_t *d = G(arg(c, 0)), n = arg(c, 2), k = 0;
  while (*s && (!d || k < n)) {
    uint32_t ch = *s++;
    if (ch >= 0xC0) {
      int more = ch >= 0xF0 ? 3 : ch >= 0xE0 ? 2 : 1;
      ch &= 0x3F >> more;
      while (more-- && (*s & 0xC0) == 0x80) ch = (ch << 6) | (*s++ & 0x3F);
    }
    if (d) d[k] = ch;
    k++;
  }
  if (d && k < n) d[k] = 0;
  ret32(c, k);
}
static void h_wcstombs(Cpu *c) {
  const uint32_t *s = G(arg(c, 1));
  unsigned char *d = G(arg(c, 0));
  uint32_t n = arg(c, 2), k = 0;
  for (; *s; s++) {
    unsigned char b[4]; int m;
    uint32_t ch = *s;
    if (ch < 0x80) { b[0] = ch; m = 1; }
    else if (ch < 0x800) { b[0] = 0xC0 | ch >> 6; b[1] = 0x80 | (ch & 0x3F); m = 2; }
    else if (ch < 0x10000) { b[0] = 0xE0 | ch >> 12; b[1] = 0x80 | ((ch >> 6) & 0x3F); b[2] = 0x80 | (ch & 0x3F); m = 3; }
    else { b[0] = 0xF0 | ch >> 18; b[1] = 0x80 | ((ch >> 12) & 0x3F); b[2] = 0x80 | ((ch >> 6) & 0x3F); b[3] = 0x80 | (ch & 0x3F); m = 4; }
    if (d && k + m > n) break;
    if (d) memcpy(d + k, b, m);
    k += m;
  }
  if (d && k < n) d[k] = 0;
  ret32(c, k);
}
static void h_adler32(Cpu *c) { ret32(c, (uint32_t)adler32(arg(c, 0), G(arg(c, 1)), arg(c, 2))); }
static void h_adler32_combine(Cpu *c) { ret32(c, (uint32_t)adler32_combine(arg(c, 0), arg(c, 1), (int32_t)arg(c, 2))); }
static void h_crc32_combine(Cpu *c) { ret32(c, (uint32_t)crc32_combine(arg(c, 0), arg(c, 1), (int32_t)arg(c, 2))); }
static void h_zlibVersion(Cpu *c) { ret32(c, GA(ZLIB_VERSION)); }
static void h_zError(Cpu *c) { static char buf[32]; snprintf(buf, sizeof buf, "zlib error %d", (int32_t)arg(c, 0)); ret32(c, GA(buf)); }
// syscall(number, ...): the few that libraries make directly
static void h_syscall(Cpu *c) {
  switch (arg(c, 0)) {
    case 224: ret32(c, c->thread ? (uint32_t)c->thread->id + 1 : 1); return;   // gettid
    case 20: ret32(c, 1); return;                                              // getpid
    case 158: ret32(c, 0); return;                                             // sched_yield
    case 0xF0002: ret32(c, 0); return;                                         // cacheflush
  }
  FAIL(ENOSYS);
}

// time (guest time_t and long are 32 bits; struct tm and timespec have the same layout)
static time_t gtime(uint32_t p) { return (time_t)(int32_t)R32(p); }
static void h_localtime(Cpu *c) { time_t t = gtime(arg(c, 0)); ret32(c, GA(localtime(&t))); }
static void h_localtime_r(Cpu *c) { time_t t = gtime(arg(c, 0)); ret32(c, localtime_r(&t, G(arg(c, 1))) ? arg(c, 1) : 0); }
static void h_gmtime_r(Cpu *c) { time_t t = gtime(arg(c, 0)); ret32(c, gmtime_r(&t, G(arg(c, 1))) ? arg(c, 1) : 0); }
static void h_mktime(Cpu *c) { ret32(c, (uint32_t)(int32_t)mktime(G(arg(c, 0)))); }
static void h_timegm(Cpu *c) { ret32(c, (uint32_t)(int32_t)timegm(G(arg(c, 0)))); }
static void h_asctime(Cpu *c) { ret32(c, GA(asctime(G(arg(c, 0))))); }
static void h_ctime(Cpu *c) { time_t t = gtime(arg(c, 0)); ret32(c, GA(ctime(&t))); }
static void h_difftime(Cpu *c) { retd(c, difftime((time_t)(int32_t)arg(c, 0), (time_t)(int32_t)arg(c, 1))); }
static void h_tzset(Cpu *c) { tzset(); }
static void h_clock_gettime(Cpu *c) {
  struct timespec ts;
  int r = clock_gettime(arg(c, 0) == 0 ? CLOCK_REALTIME : CLOCK_MONOTONIC, &ts);
  R32(arg(c, 1)) = (uint32_t)ts.tv_sec;
  R32(arg(c, 1) + 4) = (uint32_t)ts.tv_nsec;
  ret32(c, (uint32_t)r);
}
static void h_clock_getres(Cpu *c) { if (arg(c, 1)) { R32(arg(c, 1)) = 0; R32(arg(c, 1) + 4) = 1000; } ret32(c, 0); }
// sleeping lets the other emulated threads run: the call returns when the page gets back to this thread
// Sleeping blocks the thread until the time has passed (the others run meanwhile); returns 1 when it has.
int host_sleep_for(Cpu *c, double ms);
static int sleep_for(Cpu *c, double ms) { return host_sleep_for(c, ms); }
int host_sleep_for(Cpu *c, double ms) {
  Thread *t = c->thread;
  double now = emscripten_get_now();
  if (!t || ms <= 0) return 1;
  if (!t->sleeping) { t->sleeping = 1; t->wake = now + ms; c->blocked = 1; return 0; }
  if (now < t->wake) { c->blocked = 1; return 0; }
  t->sleeping = 0;
  return 1;
}
static void h_nanosleep(Cpu *c) {
  uint32_t ts = arg(c, 0);
  double ms = ts ? (int32_t)R32(ts) * 1000.0 + (int32_t)R32(ts + 4) / 1e6 : 0;
  if (!sleep_for(c, ms)) return;
  if (arg(c, 1)) { R32(arg(c, 1)) = 0; R32(arg(c, 1) + 4) = 0; }
  ret32(c, 0);
}
static void h_usleep(Cpu *c) { if (sleep_for(c, arg(c, 0) / 1000.0)) ret32(c, 0); }
static void h_sleep(Cpu *c) { if (sleep_for(c, arg(c, 0) * 1000.0)) ret32(c, 0); }
static void h_sched_yield(Cpu *c) { sched_run_others(100000); ret32(c, 0); }

// process, users, environment, signals
static void h_getpid(Cpu *c) { ret32(c, 4242); }
static void h_getppid(Cpu *c) { ret32(c, 1); }
static void h_gettid(Cpu *c) { ret32(c, 4242 + (uint32_t)current_thread->id); }
static void h_getuid(Cpu *c) { ret32(c, 10042); }
static void h_null(Cpu *c) { ret32(c, 0); }
static void h_fork(Cpu *c) { FAIL(EAGAIN); }
static void h_exit_now(Cpu *c) { fatal("the native code called _exit(%d)", (int)arg(c, 0)); }
static void h_kill(Cpu *c) { ret32(c, 0); }
static void h_sysconf(Cpu *c) {
  switch (arg(c, 0)) {
    case 0x27: case 0x28: ret32(c, 4096); break;   // _SC_PAGESIZE, _SC_PAGE_SIZE
    case 0x60: case 0x61: ret32(c, 1); break;      // _SC_NPROCESSORS_CONF/ONLN
    case 0x6: ret32(c, 100); break;                // _SC_CLK_TCK
    default: ret32(c, (uint32_t)-1);
  }
}
static void h_getpagesize(Cpu *c) { ret32(c, 4096); }
static void h_uname(Cpu *c) {
  char *u = G(arg(c, 0));
  const char *f[6] = {"Linux", "localhost", "3.4.0", "#1 SMP", "armv7l", "localdomain"};
  for (int i = 0; i < 6; i++) { memset(u + 65 * i, 0, 65); strncpy(u + 65 * i, f[i], 64); }
  ret32(c, 0);
}
static void h_setenv(Cpu *c) { ret32(c, (uint32_t)setenv(G(arg(c, 0)), G(arg(c, 1)), (int)arg(c, 2))); }
static void h_unsetenv(Cpu *c) { ret32(c, (uint32_t)unsetenv(G(arg(c, 0)))); }
static void h_putenv(Cpu *c) { ret32(c, (uint32_t)putenv(G(arg(c, 0)))); }
static uint32_t guest_environ_list[2], guest_environ;

// network: there is none (the page can't open sockets); everything reports it
static void h_net_fail(Cpu *c) { FAIL(ENETUNREACH); }
// poll/select: no descriptor here ever becomes ready (no sockets or pipes), so they wait out their timeout
static void h_poll(Cpu *c) {
  int32_t timeout = (int32_t)arg(c, 2);
  if (timeout < 0) { c->blocked = 1; return; }   // forever
  if (sleep_for(c, timeout)) ret32(c, 0);
}
static void h_select(Cpu *c) {
  uint32_t tv = arg(c, 4);
  if (!tv) { c->blocked = 1; return; }
  if (sleep_for(c, (int32_t)R32(tv) * 1000.0 + (int32_t)R32(tv + 4) / 1000.0)) ret32(c, 0);
}
static void h_getaddrinfo(Cpu *c) { ret32(c, (uint32_t)-2); }   // EAI_NONAME
static void h_gai_strerror(Cpu *c) { ret32(c, GA("network is unreachable")); }
static void h_inet_ntoa(Cpu *c) {
  static char buf[16]; uint32_t a = arg(c, 0);
  snprintf(buf, sizeof buf, "%u.%u.%u.%u", a & 255, (a >> 8) & 255, (a >> 16) & 255, a >> 24);
  ret32(c, GA(buf));
}
static void h_inet_addr(Cpu *c) {
  unsigned a, b, cc, d;
  if (sscanf(G(arg(c, 0)), "%u.%u.%u.%u", &a, &b, &cc, &d) == 4) ret32(c, a | b << 8 | cc << 16 | d << 24);
  else ret32(c, 0xffffffff);
}
static void h_htonl(Cpu *c) { ret32(c, __builtin_bswap32(arg(c, 0))); }
static void h_htons(Cpu *c) { ret32(c, __builtin_bswap16((uint16_t)arg(c, 0))); }

// threads (cooperative, see sched_run_others): blocking calls return once their condition holds
void host_thread_report(void) {
  Thread *all[64]; int n = 0;
  if (main_thread) all[n++] = main_thread;
  for (Thread *t = threads; t && n < 64; t = t->next) if (t != main_thread) all[n++] = t;
  for (int i = 0; i < n; i++) {
    Thread *t = all[i];
    char pc[96];
    snprintf(pc, sizeof pc, "%s", elf_where(t->cpu.r[15]));
    logf_("thread %d%s: pc %s lr %s%s%s wait 0x%08x icount %llu", t->id, t == current_thread ? " (current)" : "", pc, elf_where(t->cpu.r[14]),
          t->finished ? " finished" : "", t->sleeping ? " sleeping" : t->cpu.blocked ? " blocked" : "", t->wait_obj, (unsigned long long)t->cpu.icount);
  }
}
// a thread sleeps (waits out a time): the threads being idle for a while is not a deadlock
int host_any_sleeping(void) {
  if (main_thread && main_thread->sleeping) return 1;
  for (Thread *t = threads; t; t = t->next) if (t->sleeping && !t->finished) return 1;
  return 0;
}
static Thread *thread_by_id(uint32_t id) { for (Thread *t = threads; t; t = t->next) if ((uint32_t)t->id == id) return t; return NULL; }
static void h_pthread_self(Cpu *c) { ret32(c, (uint32_t)current_thread->id); }
// pthread_attr_t (bionic): flags, stack_base, stack_size, guard_size, sched_policy, sched_priority
static void h_pthread_attr_init(Cpu *c) { uint32_t *a = G(arg(c, 0)); memset(a, 0, 24); a[2] = 1 << 20; a[3] = 4096; ret32(c, 0); }
static void h_pthread_attr_setdetachstate(Cpu *c) { uint32_t *a = G(arg(c, 0)); a[0] = (a[0] & ~1u) | (arg(c, 1) ? 1 : 0); ret32(c, 0); }
static void h_pthread_attr_getdetachstate(Cpu *c) { R32(arg(c, 1)) = R32(arg(c, 0)) & 1; ret32(c, 0); }
static void h_pthread_attr_setstacksize(Cpu *c) { ((uint32_t *)G(arg(c, 0)))[2] = arg(c, 1); ret32(c, 0); }
static void h_pthread_attr_getstacksize(Cpu *c) { uint32_t v = ((uint32_t *)G(arg(c, 0)))[2]; R32(arg(c, 1)) = v ? v : 1 << 20; ret32(c, 0); }
static void h_pthread_attr_setstack(Cpu *c) { uint32_t *a = G(arg(c, 0)); a[1] = arg(c, 1); a[2] = arg(c, 2); ret32(c, 0); }
static void h_pthread_attr_getstack(Cpu *c) { uint32_t *a = G(arg(c, 0)); R32(arg(c, 1)) = a[1]; R32(arg(c, 2)) = a[2]; ret32(c, 0); }
static void h_pthread_attr_getguardsize(Cpu *c) { R32(arg(c, 1)) = ((uint32_t *)G(arg(c, 0)))[3]; ret32(c, 0); }
static void h_pthread_getattr_np(Cpu *c) {   // the thread's own stack
  Thread *t = thread_by_id(arg(c, 0));
  if (!t) t = current_thread;
  uint32_t *a = G(arg(c, 1));
  memset(a, 0, 24);
  a[1] = t->stack; a[2] = t->stack_size; a[3] = 4096;
  ret32(c, 0);
}
// __pthread_cleanup_push(struct { prev, routine, arg }, routine, arg) / pop(c, execute)
static uint32_t cleanup_top;
static void h___pthread_cleanup_push(Cpu *c) { uint32_t *b = G(arg(c, 0)); b[0] = cleanup_top; b[1] = arg(c, 1); b[2] = arg(c, 2); cleanup_top = arg(c, 0); }
static void h___pthread_cleanup_pop(Cpu *c) {
  uint32_t *b = G(arg(c, 0));
  cleanup_top = b[0];
  if (arg(c, 1)) { uint32_t a = b[2]; guest_call(c, b[1], 1, &a); }
}
static void h_pthread_equal(Cpu *c) { ret32(c, arg(c, 0) == arg(c, 1)); }
static void h_pthread_join(Cpu *c) {
  Thread *t = thread_by_id(arg(c, 0));
  if (!t || t->finished) { if (arg(c, 1)) R32(arg(c, 1)) = 0; ret32(c, 0); return; }
  c->blocked = 1;
}
static void h_pthread_exit(Cpu *c) { c->thread->finished = 1; c->stop = 1; }
static void h_cond_signal(Cpu *c) { R32(arg(c, 0))++; ret32(c, 0); }
// wait: remember the condition's count, then block until a signal changes it (timedwait also ends at its deadline)
// The mutex is released for the wait and taken back before it returns (waiting for it again if another thread has it)
static void cond_wait(Cpu *c, int timed) {
  Thread *t = c->thread;
  uint32_t cond = arg(c, 0), m = arg(c, 1);
  if (t->wait_obj != cond) {
    t->wait_obj = cond; t->wait_gen = R32(cond);
    t->wait_mutex = 0;
    if (m && (R32(m) >> 16) == self_tag(c)) { t->wait_mutex = m; t->wait_count = R32(m) & 0x3FFF; R32(m) &= 0xC000; }
    c->blocked = 1;
    return;
  }
  int done = R32(cond) != t->wait_gen, rc = 0;
  if (!done && timed) {
    struct timespec now; clock_gettime(CLOCK_REALTIME, &now);
    uint32_t ts = arg(c, 2);
    if (!ts || (int64_t)now.tv_sec > (int32_t)R32(ts) || ((int64_t)now.tv_sec == (int32_t)R32(ts) && now.tv_nsec >= (long)R32(ts + 4))) { done = 1; rc = ETIMEDOUT; }
  }
  if (!done) { c->blocked = 1; return; }
  if (t->wait_mutex) {
    uint32_t v = R32(t->wait_mutex);
    if ((v >> 16) && (v >> 16) != self_tag(c)) { c->blocked = 1; return; }   // signalled: now the mutex
    R32(t->wait_mutex) = (self_tag(c) << 16) | (v & 0xC000) | (t->wait_count ? t->wait_count : 1);
  }
  t->wait_obj = 0; t->wait_mutex = 0;
  ret32(c, (uint32_t)errno_to_linux(rc));
}
static void h_cond_wait2(Cpu *c) { cond_wait(c, 0); }
static void h_cond_timedwait(Cpu *c) { cond_wait(c, 1); }
static void h_sem_trywait(Cpu *c) { uint32_t s = arg(c, 0); if ((int32_t)R32(s) > 0) { R32(s)--; ret32(c, 0); } else FAIL(EAGAIN); }
static void h_sem_getvalue(Cpu *c) { R32(arg(c, 1)) = R32(arg(c, 0)); ret32(c, 0); }

// C++ runtime pieces libraries import from libc / libstdc++
static void h_op_new(Cpu *c) { ret32(c, GA(malloc(arg(c, 0) ? arg(c, 0) : 1))); }
static void h_op_delete(Cpu *c) { free(G(arg(c, 0))); }
static void h_pure_virtual(Cpu *c) { fatal("pure virtual function called (from %s)", elf_where(c->r[14])); }
static void h_guard_acquire(Cpu *c) { ret32(c, (*(uint8_t *)G(arg(c, 0)) & 1) ? 0 : 1); }
static void h_guard_release(Cpu *c) { *(uint32_t *)G(arg(c, 0)) = 1; }
static void h_guard_abort(Cpu *c) {}

static void more_libc(void) {
#define F(n) add_fn(#n, h_##n)
  F(strrchr); F(strdup); F(strndup); F(strcasecmp); F(strncasecmp); F(strncat); F(strlcpy); F(strlcat); F(strspn); F(strcspn);
  F(strnlen); F(strsep); F(strtok_r); F(strerror_r); F(memrchr); F(bcopy); F(bzero); F(strcasestr);
  F(strtol); F(strtoul); F(strtoll); F(strtoull); F(strtoimax); F(strtoumax); F(strtof); F(atol); F(atoll); F(atof);
  F(abs); F(labs); F(rand); F(srand); F(random); F(srandom); F(arc4random); F(drand48);
  F(isalpha); F(isdigit); F(isspace); F(isalnum); F(isupper); F(islower); F(isxdigit); F(ispunct); F(isprint); F(iscntrl);
  F(isgraph); F(isblank); F(toupper); F(tolower);
  F(sinf); F(cosf); F(tanf); F(asinf); F(acosf); F(atanf); F(expf); F(logf); F(log10f); F(sqrtf); F(floorf); F(ceilf);
  F(fabsf); F(roundf); F(truncf); F(sinhf); F(coshf); F(tanhf); F(exp2f); F(log2f); F(cbrtf); F(rintf); F(nearbyintf);
  F(atan2f); F(powf); F(fmodf); F(fminf); F(fmaxf); F(hypotf); F(copysignf);
  F(fabs); F(round); F(trunc); F(exp2); F(log2); F(cbrt); F(rint); F(nearbyint); F(asinh); F(acosh); F(atanh); F(expm1);
  F(log1p); F(tgamma); F(lgamma); F(erf); F(erfc); F(hypot); F(fmin); F(fmax); F(copysign); F(remainder); F(fdim); F(nextafter);
  F(lround); F(lroundf); F(lrint); F(lrintf); F(ldexpf); F(scalbn); F(scalbnf); F(frexpf); F(modff); F(sincos); F(sincosf);
  F(isnan); F(isinf); F(isnanf); F(finite);
  add_fn("__isnan", h_isnan); add_fn("__isinf", h_isinf); add_fn("__isnanf", h_isnanf); add_fn("__finite", h_finite);
  F(puts); F(putchar); F(perror); F(fgets); F(fgetc); F(getchar); F(feof); F(ferror); F(clearerr); F(rewind); F(fileno);
  F(ftello); F(fseeko); F(freopen); F(tmpfile); F(vprintf); F(asprintf); F(vasprintf); F(dprintf);
  add_fn("getc_unlocked", h_fgetc); add_fn("fputc_unlocked", h_fputc);
  F(unlink); F(remove); F(rename); F(rmdir); F(mkdir); F(chdir); F(access); F(lstat); F(umask); F(ftruncate); F(isatty);
  F(dup); F(dup2); F(fsync); F(realpath); F(mkstemp); F(opendir); F(fdopendir); F(closedir); F(rewinddir); F(readdir);
  add_fn("fdatasync", h_fsync); add_fn("sync", h_ok0);
  add_fn("chmod", h_ok0); add_fn("fchmod", h_ok0); add_fn("chown", h_ok0); add_fn("fchown", h_ok0); add_fn("lchown", h_ok0);
  add_fn("utime", h_ok0); add_fn("utimes", h_ok0); add_fn("flock", h_ok0); add_fn("fcntl", h_ok0); add_fn("madvise", h_ok0);
  add_fn("mprotect", h_ok0); add_fn("msync", h_msync); add_fn("mmap64", h_mmap64); add_fn("mremap", h_mremap); add_fn("mlock", h_ok0); add_fn("munlock", h_ok0);
  add_fn("symlink", h_unsupported); add_fn("link", h_unsupported); add_fn("readlink", h_unsupported);
  add_fn("mknod", h_unsupported); add_fn("pipe", h_unsupported); add_fn("pipe2", h_unsupported); add_fn("mkfifo", h_unsupported);
  add_fn("execvp", h_unsupported); add_fn("execv", h_unsupported); add_fn("execve", h_unsupported); add_fn("execl", h_unsupported);
  add_fn("execlp", h_unsupported); add_fn("waitpid", h_unsupported); add_fn("wait", h_unsupported); add_fn("system", h_unsupported);
  add_fn("popen", h_null); add_fn("pclose", h_unsupported);
  F(localtime); F(localtime_r); F(gmtime_r); F(mktime); F(timegm); F(asctime); F(ctime); F(difftime); F(tzset);
  F(clock_gettime); F(clock_getres); F(nanosleep); F(usleep); F(sleep); F(sched_yield);
  F(getpid); F(getppid); F(gettid); F(getuid); F(fork); F(kill); add_fn("signal", h_signal_set); F(sysconf); F(getpagesize); F(uname);
  F(setenv); F(unsetenv); F(putenv);
  add_fn("vfork", h_fork); add_fn("_exit", h_exit_now); add_fn("_Exit", h_exit_now);
  add_fn("geteuid", h_getuid); add_fn("getgid", h_getuid); add_fn("getegid", h_getuid);
  add_fn("setuid", h_ok0); add_fn("setgid", h_ok0); add_fn("seteuid", h_ok0); add_fn("setsid", h_ok0); add_fn("setpgid", h_ok0);
  add_fn("getpwnam", h_null); add_fn("getpwuid", h_null); add_fn("getgrnam", h_null); add_fn("getgrgid", h_null);
  add_fn("endpwent", h_ok0); add_fn("getlogin", h_null); add_fn("ttyname", h_null);
  add_fn("bsd_signal", h_signal_set); add_fn("sigaction", h_sigaction); add_fn("sigprocmask", h_sigmask); add_fn("pthread_sigmask", h_sigmask);
  add_fn("sigemptyset", h_ok0); add_fn("sigfillset", h_ok0); add_fn("sigaddset", h_ok0); add_fn("sigdelset", h_ok0);
  add_fn("sigaltstack", h_ok0); add_fn("alarm", h_ok0); add_fn("setitimer", h_ok0); add_fn("getrlimit", h_unsupported);
  add_fn("setrlimit", h_ok0); add_fn("prctl", h_ok0); add_fn("getpriority", h_ok0); add_fn("setpriority", h_ok0);
  add_fn("nice", h_ok0); add_fn("tgkill", h_ok0);
  guest_environ_list[0] = 0;
  guest_environ = GA(guest_environ_list);
  add_data("environ", &guest_environ);
  add_fn("socket", h_net_fail); add_fn("socketpair", h_net_fail); add_fn("bind", h_net_fail); add_fn("connect", h_net_fail);
  add_fn("listen", h_net_fail); add_fn("accept", h_net_fail); add_fn("send", h_net_fail); add_fn("recv", h_net_fail);
  add_fn("sendto", h_net_fail); add_fn("recvfrom", h_net_fail); add_fn("sendmsg", h_net_fail); add_fn("recvmsg", h_net_fail);
  add_fn("setsockopt", h_net_fail); add_fn("getsockopt", h_net_fail); add_fn("getsockname", h_net_fail);
  add_fn("getpeername", h_net_fail); add_fn("shutdown", h_net_fail); add_fn("select", h_select); add_fn("poll", h_poll);
  add_fn("gethostbyname", h_null); add_fn("gethostbyaddr", h_null); add_fn("getservbyname", h_null);
  add_fn("gethostname", h_ok0); add_fn("getaddrinfo", h_getaddrinfo); add_fn("freeaddrinfo", h_ok0);
  add_fn("gai_strerror", h_gai_strerror); add_fn("inet_ntoa", h_inet_ntoa); add_fn("inet_addr", h_inet_addr);
  add_fn("htonl", h_htonl); add_fn("ntohl", h_htonl); add_fn("htons", h_htons); add_fn("ntohs", h_htons);
  add_fn("inet_pton", h_null); add_fn("inet_ntop", h_null); add_fn("if_nametoindex", h_null);
  F(pthread_self); F(pthread_equal); F(pthread_join); F(pthread_exit);
  add_fn("pthread_detach", h_ok0); add_fn("pthread_kill", h_pthread_kill); add_fn("pthread_setname_np", h_ok0);
  add_fn("tgkill", h_tgkill); sigreturn_hook = hook_register("sigreturn", h_sigreturn);
  F(pthread_attr_init); add_fn("pthread_attr_destroy", h_ok0); F(pthread_attr_setdetachstate);
  F(pthread_attr_setstacksize); F(pthread_attr_getstacksize); add_fn("pthread_attr_setschedparam", h_ok0);
  add_fn("pthread_attr_getschedparam", h_ok0); add_fn("pthread_attr_setschedpolicy", h_ok0); add_fn("pthread_setschedparam", h_ok0);
  add_fn("pthread_getschedparam", h_ok0); add_fn("pthread_mutexattr_init", h_mutexattr_init); add_fn("pthread_mutexattr_destroy", h_ok0);
  add_fn("pthread_mutexattr_settype", h_mutexattr_settype); add_fn("pthread_mutex_destroy", h_ok0); add_fn("pthread_mutex_trylock", h_mutex_trylock);
  add_fn("pthread_cond_init", h_ok0); add_fn("pthread_cond_destroy", h_ok0); add_fn("pthread_cond_signal", h_cond_signal);
  add_fn("pthread_cond_timedwait", h_cond_timedwait); add_fn("pthread_condattr_init", h_ok0);
  add_fn("pthread_condattr_destroy", h_ok0); add_fn("pthread_rwlock_init", h_rwlock_init); add_fn("pthread_rwlock_destroy", h_ok0);
  add_fn("pthread_rwlock_rdlock", h_rwlock_rdlock); add_fn("pthread_rwlock_wrlock", h_rwlock_wrlock); add_fn("pthread_rwlock_unlock", h_rwlock_unlock);
  add_fn("pthread_rwlock_tryrdlock", h_rwlock_tryrdlock); add_fn("pthread_rwlock_trywrlock", h_rwlock_trywrlock);
  F(sem_trywait); F(sem_getvalue);
  add_fn("_Znwj", h_op_new); add_fn("_Znaj", h_op_new); add_fn("_ZnwjRKSt9nothrow_t", h_op_new); add_fn("_ZnajRKSt9nothrow_t", h_op_new);
  add_fn("_ZdlPv", h_op_delete); add_fn("_ZdaPv", h_op_delete); add_fn("_ZdlPvRKSt9nothrow_t", h_op_delete);
  add_fn("_ZdaPvRKSt9nothrow_t", h_op_delete); add_fn("__cxa_pure_virtual", h_pure_virtual);
  add_fn("__cxa_guard_acquire", h_guard_acquire); add_fn("__cxa_guard_release", h_guard_release); add_fn("__cxa_guard_abort", h_guard_abort);
  add_fn("__aeabi_atexit", h_ok0);
#undef F
}

// ---------------------------------------------------------------------------

static void h_return_to_host(Cpu *c) { c->stop = 1; }

void host_init(void) {
  init_ctype();
  hook_register("return_to_host", h_return_to_host);  // id 0
  exit_hook = hook_register("thread_exit", h_thread_exit);

  add_data("__sF", sF);
  add_data("_ctype_", &ctype_ptr);
  add_data("_tolower_tab_", &tolower_ptr);
  add_data("_toupper_tab_", &toupper_ptr);
  add_data("__stack_chk_guard", &stack_guard);
  add_data("optarg", &optarg_var);
  add_data("optind", &optind_var);

#define F(n) add_fn(#n, h_##n)
  add_fn("__android_log_print", h_android_log_print);
  add_fn("__cxa_atexit", h_cxa_atexit);
  add_fn("__cxa_finalize", h_cxa_finalize);
  add_fn("__errno", h_errno);
  add_fn("getauxval", h_getauxval);
  F(memalign); F(valloc); F(posix_memalign); add_fn("pvalloc", h_valloc);
  F(pthread_attr_getdetachstate); F(pthread_attr_setstack); F(pthread_attr_getstack); F(pthread_attr_getguardsize);
  F(pthread_getattr_np); F(__pthread_cleanup_push); F(__pthread_cleanup_pop); F(sem_timedwait);
  add_fn("__android_log_vprint", h_android_log_vprint); add_fn("__android_log_write", h_android_log_write);
  F(SHA1Init); F(SHA1Update); F(SHA1Final); F(div); F(getrusage); F(getresuid); add_fn("getresgid", h_getresuid);
  add_fn("setresuid", h_ok0); add_fn("setresgid", h_ok0); F(sendfile); F(truncate); F(wcscmp); F(wcsncpy);
  F(iswcntrl); F(iswxdigit); F(__fpclassifyf); F(fgetpos); F(fsetpos); add_fn("sigsuspend", h_sigsuspend_wait); add_fn("tkill", h_tkill);
  add_fn("atexit", h_ok0); add_fn("sched_get_priority_max", h_ok0); add_fn("sched_get_priority_min", h_ok0);
  F(__cxa_begin_cleanup); F(__cxa_type_match); F(__cxa_call_unexpected);
  add_data("__page_size", &page_size_v); add_data("data_start", &data_start_v);
  F(fscanf); F(__srget); F(__swbuf); F(memmem); F(basename); F(dirname); F(writev); F(readv); F(__assert2); F(__assert);
  F(getdtablesize); F(syslog); F(inet_aton); F(getnameinfo); F(hstrerror); F(__get_h_errno); F(tmpnam);
  F(iswspace); F(iswalpha); F(iswdigit); F(iswupper); F(iswlower); F(iswalnum); F(iswpunct); F(iswprint);
  F(mbstowcs); F(wcstombs); F(adler32); F(adler32_combine); F(crc32_combine); F(zlibVersion); F(zError); F(syscall);
  add_fn("vsscanf", h_vsscanf_); add_fn("openlog", h_ok0); add_fn("closelog", h_ok0); add_fn("pause", h_unsupported);
  add_fn("sigsetjmp", h_setjmp); add_fn("siglongjmp", h_longjmp); add_fn("_setjmp", h_setjmp); add_fn("_longjmp", h_longjmp);
  add_fn("__gnu_Unwind_Find_exidx", h_find_exidx); add_fn("dl_unwind_find_exidx", h_find_exidx);
  add_fn("__stack_chk_fail", h_stack_chk_fail);
  F(abort); F(exit); add_fn("raise", h_raise_sig); F(getopt);
  F(acos); F(asin); F(atan); F(atan2); F(ceil); F(cos); F(cosh); F(exp); F(floor); F(fmod); F(frexp);
  F(ldexp); F(log); F(log10); F(modf); F(pow); F(sin); F(sinh); F(sqrt); F(tan); F(tanh);
  F(atoi); F(bsearch); F(calloc); F(free); F(malloc); F(realloc); F(qsort); F(getenv);
  F(lrand48); F(srand48); F(strtod);
  F(memchr); F(memcmp); F(memcpy); F(memmove); F(memset);
  F(strcat); F(strchr); F(strcmp); F(strcoll); F(strcpy); F(strerror); F(strlen); F(strncmp); F(strncpy);
  F(strpbrk); F(strstr); F(strtok); F(strxfrm);
  F(btowc); F(getwc); F(iswctype); F(mbrtowc); F(putwc); F(towlower); F(towupper); F(ungetwc);
  F(wcrtomb); F(wcscoll); F(wcsftime); F(wcslen); F(wcsxfrm); F(wctob); F(wctype);
  F(wmemchr); F(wmemcmp); F(wmemcpy); F(wmemmove); F(wmemset); F(setlocale);
  F(fclose); F(fdopen); F(fflush); F(fopen); F(fprintf); F(fputc); F(fputs); F(fread); F(fseek); F(ftell);
  F(fwrite); F(getc); F(printf); F(setvbuf); F(snprintf); F(sprintf); F(sscanf); F(ungetc);
  F(vfprintf); F(vsnprintf); F(vsprintf);
  add_fn("putc", h_fputc);
  F(open); F(close); F(read); F(write); F(lseek); F(fstat); F(stat); F(ioctl); F(mmap); F(munmap); F(getcwd);
  F(time); F(clock); F(gettimeofday); F(gmtime); F(strftime);
  F(setjmp); F(longjmp);
  F(pthread_create); F(pthread_once);
  add_fn("pthread_mutex_init", h_mutex_init);
  add_fn("pthread_mutex_lock", h_mutex_lock);
  add_fn("pthread_mutex_unlock", h_mutex_unlock);
  add_fn("pthread_cond_broadcast", h_cond_signal);
  add_fn("pthread_cond_wait", h_cond_wait2);
  add_fn("pthread_key_create", h_key_create);
  add_fn("pthread_key_delete", h_key_delete);
  add_fn("pthread_getspecific", h_getspecific);
  add_fn("pthread_setspecific", h_setspecific);
  F(sem_init); F(sem_destroy); F(sem_post); F(sem_wait);
  F(crc32); F(deflate); F(deflateEnd); F(deflateInit2_); F(deflateInit_); F(deflateParams); F(deflateReset);
  F(gzclose); F(gzopen); F(gzread); F(inflate); F(inflateCopy); F(inflateEnd); F(inflateInit2_); F(inflateInit_);
  F(inflateReset); F(inflateSync); F(uncompress);
#undef F
  more_libc();
  (void)missing;
}
