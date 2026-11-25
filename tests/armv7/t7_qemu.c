// Reference runner for t7.c under qemu-arm: a static freestanding Linux executable that prints the result words.
#include <stdint.h>
#include <stddef.h>
int run_all(uint32_t *out);
__attribute__((naked, noinline)) static long sys3(long nr, long a, long b, long c) {
  __asm__("push {r7}\n mov r7, r0\n mov r0, r1\n mov r1, r2\n mov r2, r3\n svc 0\n pop {r7}\n bx lr\n");
}
void *memcpy(void *d, const void *s, size_t n) { uint8_t *a = d; const uint8_t *b = s; while (n--) *a++ = *b++; return d; }
void *memmove(void *d, const void *s, size_t n) {
  uint8_t *a = d; const uint8_t *b = s;
  if (a < b) while (n--) *a++ = *b++; else { a += n; b += n; while (n--) *--a = *--b; }
  return d;
}
void *memset(void *d, int c, size_t n) { uint8_t *a = d; while (n--) *a++ = (uint8_t)c; return d; }
void __aeabi_memcpy(void *d, const void *s, size_t n) { memcpy(d, s, n); }
void __aeabi_memcpy4(void *d, const void *s, size_t n) { memcpy(d, s, n); }
void __aeabi_memcpy8(void *d, const void *s, size_t n) { memcpy(d, s, n); }
void __aeabi_memmove(void *d, const void *s, size_t n) { memmove(d, s, n); }
void __aeabi_memmove4(void *d, const void *s, size_t n) { memmove(d, s, n); }
void __aeabi_memset(void *d, size_t n, int c) { memset(d, c, n); }
void __aeabi_memset4(void *d, size_t n, int c) { memset(d, c, n); }
void __aeabi_memclr(void *d, size_t n) { memset(d, 0, n); }
void __aeabi_memclr4(void *d, size_t n) { memset(d, 0, n); }
void __aeabi_memclr8(void *d, size_t n) { memset(d, 0, n); }
static uint32_t out[1 << 16];
static char line[16];
void _start_c(void) {
  int n = run_all(out);
  for (int i = 0; i < n; i++) {
    for (int k = 0; k < 8; k++) line[k] = "0123456789abcdef"[(out[i] >> (28 - 4 * k)) & 15];
    line[8] = '\n';
    sys3(4, 1, (long)line, 9);
  }
  sys3(1, 0, 0, 0);
}
__attribute__((naked)) void _start(void) { __asm__("mov r0, sp\n bic r0, r0, #7\n mov sp, r0\n bl _start_c\n"); }
