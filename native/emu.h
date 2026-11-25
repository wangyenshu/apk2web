// ARMv7-A interpreter (ARM, Thumb-2, VFPv3, Advanced SIMD) that runs an Android app's native libraries inside
// WebAssembly; armeabi (ARMv5TE) code is a subset.
// Guest addresses are WebAssembly linear-memory addresses: the library is loaded into
// the module's own heap, so host code can use guest pointers directly.
#pragma once
#include <stdint.h>
#include <stddef.h>

typedef struct Cpu {
  uint32_t r[16];
  uint32_t n, z, c, v;   // flags, 0 or 1
  uint32_t t;            // 1 = Thumb state
  uint32_t q;            // sticky overflow (DSP)
  uint32_t ge;           // GE[3:0] (SIMD compare results, SEL)
  uint32_t it;           // ITSTATE: base condition in [7:4], mask in [3:0] (0 = not in an IT block)
  uint32_t fpscr;        // VFP status/control
  uint32_t tls;          // TPIDRURO (mrc p15, 0, rN, c13, c0, 3)
  uint32_t excl;         // exclusive monitor: address of the last LDREX (1 = none)
  uint64_t d[32];        // VFP/NEON registers D0-D31 (S0-S31 are the halves of D0-D15, Qn = D2n:D2n+1)
  int stop;              // set by hooks: leave the run loop
  int blocked;           // set by hooks: thread must wait, retry the call later
  uint64_t icount;       // instructions executed
  struct Thread *thread;
} Cpu;

// a host function the guest can call: reads arguments from the cpu, writes results back
typedef void (*HookFn)(Cpu *cpu);

enum { HOOK_RETURN_TO_HOST = 0 };

int hook_register(const char *name, HookFn fn);  // returns id
uint32_t hook_stub_address(int id);              // ARM-mode address that calls the hook
void hook_patch(uint32_t addr, int id);          // overwrite a guest function entry with the hook
const char *hook_name(int id);
extern int current_hook;

void cpu_run(Cpu *cpu, uint64_t budget);         // run until cpu->stop or cpu->blocked, or about budget instructions
uint32_t guest_call(Cpu *cpu, uint32_t fn, int nargs, const uint32_t *args);  // returns r0 (r1 in cpu->r[1])
void hook_return(Cpu *cpu);                      // pc = lr (with interworking)

// argument access (AAPCS)
static inline uint32_t arg(Cpu *c, int i) {
  return i < 4 ? c->r[i] : *(uint32_t *)(uintptr_t)(c->r[13] + 4 * (i - 4));
}
static inline uint64_t arg64(Cpu *c, int i) {  // i must be even when in registers
  if (i < 4) return (uint64_t)c->r[i] | ((uint64_t)c->r[i + 1] << 32);
  uint32_t *p = (uint32_t *)(uintptr_t)(c->r[13] + 4 * (i - 4));
  return (uint64_t)p[0] | ((uint64_t)p[1] << 32);
}
static inline double argd(Cpu *c, int i) { uint64_t u = arg64(c, i); double d; __builtin_memcpy(&d, &u, 8); return d; }
static inline float argf(Cpu *c, int i) { uint32_t u = arg(c, i); float f; __builtin_memcpy(&f, &u, 4); return f; }
static inline void ret32(Cpu *c, uint32_t v) { c->r[0] = v; }
static inline void ret64(Cpu *c, uint64_t v) { c->r[0] = (uint32_t)v; c->r[1] = (uint32_t)(v >> 32); }
static inline void retd(Cpu *c, double d) { uint64_t u; __builtin_memcpy(&u, &d, 8); ret64(c, u); }
static inline void retf(Cpu *c, float f) { uint32_t u; __builtin_memcpy(&u, &f, 4); c->r[0] = u; }
void jni_memory_freed(uint32_t p, uint32_t size);
#define G(p) ((void *)(uintptr_t)(p))
#define GA(p) ((uint32_t)(uintptr_t)(p))

// threads (cooperative)
typedef struct Thread {
  Cpu cpu;
  uint32_t stack, stack_size;
  int id, finished;
  int running;                   // its code is executing (somewhere up the host's call stack): not to be scheduled
  uint32_t wait_obj, wait_gen;   // a condition variable this thread waits on, and its count then
  uint32_t wait_mutex, wait_count;   // the mutex cond_wait released (and its recursion count), to take back
  int sleeping; double wake;         // sleeping until wake (emscripten_get_now ms)
  uint32_t sigpending, sigmask;      // signals sent to it / blocked (bit n-1 = signal n, as in bionic's sigset_t)
  uint32_t sigframe;                 // the innermost signal frame on its stack (the context sigreturn restores)
  uint32_t sig_count, sig_wait_count, sig_saved_mask; int sig_waiting;   // handlers run; sigsuspend's state
  int jni_resume; uint32_t jni_r0, jni_r1; double jni_wait;
  int guest_errno;   // the thread's errno, in Linux numbering   // a JNI call whose caller waits before it returns
  struct Thread *next;
} Thread;
extern Thread *main_thread, *current_thread;
void sched_run_others(uint64_t budget);

// loader (elf.c): several libraries, linked against the host and each other
#define MAX_LIBS 32
typedef struct Lib {
  char name[64];
  uint8_t *base;
  uint32_t size;
  uint32_t dynsym, dynstr, nsyms, hash, gnu_hash;
  uint32_t init, init_array, init_array_sz, exidx, exidx_sz;
  int inited;
} Lib;
extern Lib libs[MAX_LIBS];
extern int nlibs;
extern Lib game;   // the first library loaded
int elf_load_named(const char *name, const uint8_t *file, size_t len);
uint32_t elf_sym(Lib *lib, const char *name);
uint32_t elf_sym_any(const char *name);
void elf_init(Lib *lib, Cpu *cpu);
void elf_init_pending(Cpu *cpu);
void elf_host_init(void);
Lib *elf_lib_at(uint32_t addr);
const char *elf_where(uint32_t addr);   // "libfoo.so+0x1234" for messages

// varargs reader (AAPCS registers + stack, or a va_list pointer)
typedef struct { Cpu *c; int reg; int mem_mode; uint32_t mem; } Va;
void va_from_args(Va *v, Cpu *c, int first);
void va_from_list(Va *v, uint32_t ap);
uint32_t va32(Va *v);
uint64_t va64(Va *v);
uint32_t host_add_fn(const char *name, HookFn fn);
void gl_init(void);

void host_init(void);
uint32_t host_import(const char *name, int *is_data);  // address for an imported symbol
uint32_t host_import_at(int i, const char **name, int *is_data);
void host_declare(const char *name);   // a name libraries import: unimplemented ones report the call and fail
void host_unimplemented(Cpu *cpu);
void softfloat_hooks(Lib *lib);
void softfloat_imports(void);
void jni_init(void);
extern uint32_t jni_env, jni_vm;

void logf_(const char *fmt, ...);
void fatal(const char *fmt, ...);
