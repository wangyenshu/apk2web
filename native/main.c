// Entry points used from JavaScript (hw.js).
#include "emu.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <emscripten.h>

static Thread main_t;
static int ready;

EMSCRIPTEN_KEEPALIVE void emu_init(void) {
  if (ready) return;
  ready = 1;
  host_init();
  softfloat_imports();
  elf_host_init();
  gl_init();
  jni_init();
  main_thread = current_thread = &main_t;
  main_t.stack_size = 4 << 20;
  main_t.stack = GA(aligned_alloc(16, main_t.stack_size));
  main_t.cpu.thread = &main_t;
  main_t.cpu.r[13] = main_t.stack + main_t.stack_size - 256;
}

/** System.loadLibrary: load a library (and the ones it needs), run constructors, then its JNI_OnLoad.
 *  Returns the JNI version JNI_OnLoad asked for (0x10002 when it has none), or -1. */
EMSCRIPTEN_KEEPALIVE int emu_load_lib(const uint8_t *file, int len, const char *name) {
  emu_init();
  int i = elf_load_named(name, file, len);
  uint64_t before = main_t.cpu.icount;
  elf_init_pending(&main_t.cpu);
  logf_("%s: constructors done (%llu instructions)", name, (unsigned long long)(main_t.cpu.icount - before));
  uint32_t onload = elf_sym(&libs[i], "JNI_OnLoad");
  if (!onload) return 0x10002;
  uint32_t args[2] = {jni_vm, 0};
  int v = (int)guest_call(&main_t.cpu, onload, 2, args);
  logf_("%s: JNI_OnLoad -> %x", name, v);
  return v;
}

EMSCRIPTEN_KEEPALIVE uint32_t emu_sym(const char *name) { return elf_sym_any(name); }
/** newline-separated names the app's libraries import (config.json "imports"), for the platform symbol tables */
EMSCRIPTEN_KEEPALIVE void emu_declare_imports(const char *names) {
  emu_init();
  for (const char *p = names; *p;) {
    const char *e = strchr(p, '\n');
    size_t n = e ? (size_t)(e - p) : strlen(p);
    if (n && n < 255) { char buf[256]; memcpy(buf, p, n); buf[n] = 0; host_declare(buf); }
    if (!e) break;
    p = e + 1;
  }
}
EMSCRIPTEN_KEEPALIVE uint32_t emu_env(void) { return jni_env; }
EMSCRIPTEN_KEEPALIVE double emu_icount(void) { return (double)main_t.cpu.icount; }
EMSCRIPTEN_KEEPALIVE uint32_t emu_call(uint32_t fn, int nargs, const uint32_t *args) {
  return guest_call(&main_t.cpu, fn, nargs, args);
}
/** r1 of the last emu_call: the high word of a 64-bit result */
EMSCRIPTEN_KEEPALIVE uint32_t emu_r1(void) { return main_t.cpu.r[1]; }
// runs the other threads for about budget instructions; returns how many they ran (0: all of them wait)
EMSCRIPTEN_KEEPALIVE double emu_tick(int budget) {
  extern uint64_t sched_progress;
  uint64_t before = sched_progress;
  sched_run_others((uint64_t)budget);
  return (double)(sched_progress - before);
}
EMSCRIPTEN_KEEPALIVE int emu_nlibs(void) { return nlibs; }
/** a report of every guest thread (where it is, whether it waits), in the log: for diagnosing hangs */
EMSCRIPTEN_KEEPALIVE void emu_threads(void) {
  extern void host_thread_report(void);
  host_thread_report();
}
/** where the running guest thread is (for a report when the emulator traps: a guest access outside memory) */
EMSCRIPTEN_KEEPALIVE const char *emu_where(void) {
  static char buf[256];
  Cpu *c = current_thread ? &current_thread->cpu : &main_t.cpu;
  char pc[96];
  snprintf(pc, sizeof pc, "%s", elf_where(c->r[15] - (c->t ? 2 : 4)));
  snprintf(buf, sizeof buf, "%s (%s state, lr %s, sp 0x%08x)", pc, c->t ? "Thumb" : "ARM", elf_where(c->r[14]), c->r[13]);
#ifdef EMU_TRACE
  extern uint32_t trace_pc[256], trace_pos;
  logf_("last instructions (oldest first):");
  for (int i = 0; i < 256; i++) { uint32_t p = trace_pc[(trace_pos + i) & 255]; if (p) logf_("  %s%s", elf_where(p & ~1u), p & 1 ? " T" : ""); }
  for (int i = 0; i < 16; i++) logf_("  r%d = 0x%08x", i, c->r[i]);
#endif
  return buf;
}
