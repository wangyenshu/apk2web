// Loads ARM ELF shared objects (the app's lib*.so) into linear memory and links them: against the host's
// implementations of the platform libraries (libc, libm, libdl, liblog, libz, GLES, EGL, ...) and against each other
// (DT_NEEDED libraries the APK carries are loaded first, through the page).
#include "emu.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct { uint8_t ident[16]; uint16_t type, machine; uint32_t version, entry, phoff, shoff, flags;
  uint16_t ehsize, phentsize, phnum, shentsize, shnum, shstrndx; } Ehdr;
typedef struct { uint32_t type, offset, vaddr, paddr, filesz, memsz, flags, align; } Phdr;
typedef struct { int32_t tag; uint32_t val; } Dyn;
typedef struct { uint32_t name, value, size; uint8_t info, other; uint16_t shndx; } Sym;
typedef struct { uint32_t offset, info; } Rel;

Lib libs[MAX_LIBS];
int nlibs;
Lib game;   // the first library loaded (kept for messages)

// the page hands over a library the APK carries: returns a malloc'd copy and its length, or 0
extern uint32_t js_need_library(const char *name, uint32_t *len);

// libraries the platform provides: their functions are host imports, there is no file to load
static const char *SYSTEM_LIBS[] = {"libc.so", "libm.so", "libdl.so", "liblog.so", "libz.so", "libGLESv1_CM.so",
  "libGLESv2.so", "libGLESv3.so", "libEGL.so", "libandroid.so", "libjnigraphics.so", "libOpenSLES.so", "libstdc++.so",
  "libOpenMAXAL.so", "libmediandk.so", "libvulkan.so", "libaaudio.so", "libcamera2ndk.so", "libnativewindow.so",
  "libsync.so", "libneuralnetworks.so", "libamidi.so", "libbinder_ndk.so", NULL};
static int is_system(const char *n) {
  for (int i = 0; SYSTEM_LIBS[i]; i++) if (!strcmp(SYSTEM_LIBS[i], n)) return 1;
  return 0;
}

const char *elf_where(uint32_t addr) {
  static char buf[96];
  for (int i = 0; i < nlibs; i++)
    if (addr >= GA(libs[i].base) && addr < GA(libs[i].base) + libs[i].size) {
      snprintf(buf, sizeof buf, "%s+0x%x", libs[i].name, addr - GA(libs[i].base));
      return buf;
    }
  extern const char *host_where_mapped(uint32_t a);
  extern const char *hook_at(uint32_t addr);
  const char *h = hook_at(addr & ~1u);
  if (h) { snprintf(buf, sizeof buf, "%s (host)", h); return buf; }
  const char *m = host_where_mapped(addr);
  snprintf(buf, sizeof buf, "%s", m ? m : "");
  if (!m) snprintf(buf, sizeof buf, "0x%08x", addr);
  return buf;
}
Lib *elf_lib_at(uint32_t addr) {
  for (int i = 0; i < nlibs; i++)
    if (addr >= GA(libs[i].base) && addr < GA(libs[i].base) + libs[i].size) return &libs[i];
  return NULL;
}

// ---- symbols
static uint32_t gnu_nsyms(Lib *lib) {
  const uint32_t *h = (const uint32_t *)(lib->base + lib->gnu_hash);
  uint32_t nbuckets = h[0], symoffset = h[1], bloom = h[2];
  const uint32_t *buckets = h + 4 + bloom, *chain = buckets + nbuckets;
  uint32_t last = 0;
  for (uint32_t i = 0; i < nbuckets; i++) if (buckets[i] > last) last = buckets[i];
  if (last < symoffset) return symoffset;
  while (!(chain[last - symoffset] & 1)) last++;
  return last + 1;
}
static Sym *lib_find(Lib *lib, const char *name) {
  Sym *syms = (Sym *)(lib->base + lib->dynsym);
  const char *strs = (const char *)(lib->base + lib->dynstr);
  for (uint32_t i = 1; i < lib->nsyms; i++) {
    Sym *s = &syms[i];
    if (s->shndx && (s->info >> 4) != 0 && !strcmp(strs + s->name, name)) return s;   // defined, global or weak
  }
  return NULL;
}
uint32_t elf_sym(Lib *lib, const char *name) {
  Sym *s = lib_find(lib, name);
  return s ? GA(lib->base) + s->value : 0;
}
/** a symbol in any loaded library, in load order */
uint32_t elf_sym_any(const char *name) {
  for (int i = 0; i < nlibs; i++) { uint32_t a = elf_sym(&libs[i], name); if (a) return a; }
  return 0;
}

// ---- relocation

static void relocate(Lib *lib, uint32_t rel, uint32_t relsz) {
  Sym *syms = (Sym *)(lib->base + lib->dynsym);
  const char *strs = (const char *)(lib->base + lib->dynstr);
  for (uint32_t i = 0; i < relsz / 8; i++) {
    Rel *r = (Rel *)(lib->base + rel) + i;
    uint32_t type = r->info & 0xFF, si = r->info >> 8;
    uint32_t *where = (uint32_t *)(lib->base + r->offset);
    uint32_t S = 0, size = 0;
    if (si) {
      Sym *s = &syms[si];
      const char *name = strs + s->name;
      int is_data = 0;
      uint32_t host = host_import(name, &is_data);
      Sym *def = NULL;
      Lib *in = NULL;
      if (!host)   // global scope: libraries loaded earlier (dependencies first), then this one
        for (int k = 0; k < nlibs && !def; k++) { if (&libs[k] == lib) continue; def = lib_find(&libs[k], name); in = &libs[k]; }
      if (host) S = host;   // the host's definition wins: it replaces platform functions a library carries
      else if (def) { S = GA(in->base) + def->value; size = def->size; }
      else if (s->shndx) { S = GA(lib->base) + s->value; size = s->size; }
      else if ((s->info >> 4) == 2) S = 0;   // weak undefined
      else {   // calling it reports its name and fails (ENOSYS) instead of jumping to 0
        logf_("%s: unresolved symbol %s", lib->name, name);
        host_declare(name);
        int is_data;
        S = host_import(name, &is_data);
      }
    }
    switch (type) {
      case 0: break;
      case 2: *where += S; break;                    // R_ARM_ABS32
      case 3: *where += S - GA(where); break;        // R_ARM_REL32
      case 20: if (S && size) memcpy(where, G(S), size); break;   // R_ARM_COPY
      case 21: case 22: *where = S; break;           // GLOB_DAT, JUMP_SLOT
      case 23: *where += GA(lib->base); break;       // RELATIVE
      default: fatal("%s: relocation type %u not supported", lib->name, type);
    }
  }
}
// DT_RELR: packed relative relocations
static void relocate_relr(Lib *lib, uint32_t relr, uint32_t sz) {
  uint32_t *where = NULL;
  for (uint32_t i = 0; i < sz / 4; i++) {
    uint32_t e = ((uint32_t *)(lib->base + relr))[i];
    if (!(e & 1)) { where = (uint32_t *)(lib->base + e); *where++ += GA(lib->base); }
    else {
      for (uint32_t k = 0, bits = e >> 1; bits; k++, bits >>= 1) if (bits & 1) where[k] += GA(lib->base);
      where += 31;
    }
  }
}

static void needed_first(const uint8_t *file, size_t len);

/** load and link one library (and the libraries it needs that the APK has); returns its index */
int elf_load_named(const char *name, const uint8_t *file, size_t len) {
  for (int i = 0; i < nlibs; i++) if (!strcmp(libs[i].name, name)) return i;
  const Ehdr *eh = (const Ehdr *)file;
  if (len < sizeof(Ehdr) || memcmp(eh->ident, "\177ELF", 4)) fatal("%s: not an ELF file", name);
  if (eh->ident[4] != 1 || eh->machine != 40) fatal("%s: not a 32-bit ARM library (machine %u): the emulator runs ARM code only", name, eh->machine);
  needed_first(file, len);
  if (nlibs == MAX_LIBS) fatal("too many native libraries");
  Lib *lib = &libs[nlibs];
  memset(lib, 0, sizeof *lib);
  snprintf(lib->name, sizeof lib->name, "%s", name);
  const Phdr *ph = (const Phdr *)(file + eh->phoff);
  uint32_t end = 0, dyn = 0;
  for (int i = 0; i < eh->phnum; i++) {
    if (ph[i].type == 1 && ph[i].vaddr + ph[i].memsz > end) end = ph[i].vaddr + ph[i].memsz;
    if (ph[i].type == 2) dyn = ph[i].vaddr;
    if (ph[i].type == 0x70000001) { lib->exidx = ph[i].vaddr; lib->exidx_sz = ph[i].memsz; }
    if (ph[i].type == 7) fatal("%s: ELF thread-local storage is not supported", name);
  }
  lib->size = (end + 0xFFF) & ~0xFFFu;
  lib->base = aligned_alloc(4096, lib->size);
  memset(lib->base, 0, lib->size);
  for (int i = 0; i < eh->phnum; i++)
    if (ph[i].type == 1) memcpy(lib->base + ph[i].vaddr, file + ph[i].offset, ph[i].filesz);

  uint32_t rel = 0, relsz = 0, jmprel = 0, pltrelsz = 0, relr = 0, relrsz = 0;
  for (Dyn *d = (Dyn *)(lib->base + dyn); d->tag; d++) {
    switch ((uint32_t)d->tag) {
      case 4: lib->hash = d->val; break;
      case 0x6ffffef5: lib->gnu_hash = d->val; break;
      case 5: lib->dynstr = d->val; break;
      case 6: lib->dynsym = d->val; break;
      case 17: rel = d->val; break;
      case 18: relsz = d->val; break;
      case 23: jmprel = d->val; break;
      case 2: pltrelsz = d->val; break;
      case 12: lib->init = d->val; break;
      case 25: lib->init_array = d->val; break;
      case 27: lib->init_array_sz = d->val; break;
      case 36: case 0x6fffe000: relr = d->val; break;   // DT_RELR (and the Android pre-standard tag)
      case 35: case 0x6fffe001: relrsz = d->val; break;
      case 0x6000000f: case 0x60000011: fatal("%s: Android packed relocations (APS2) are not supported", name);
      case 7: fatal("%s: RELA relocations are not supported on ARM", name);
    }
  }
  if (lib->hash) lib->nsyms = ((uint32_t *)(lib->base + lib->hash))[1];
  else if (lib->gnu_hash) lib->nsyms = gnu_nsyms(lib);
  nlibs++;
  if (nlibs == 1) game = *lib;
  relocate(lib, rel, relsz);
  relocate(lib, jmprel, pltrelsz);
  if (relr) relocate_relr(lib, relr, relrsz);
  softfloat_hooks(lib);
  logf_("loaded %s: %u bytes at %p, %u symbols", name, lib->size, lib->base, lib->nsyms);
  return nlibs - 1;
}

/** DT_NEEDED entries the APK has (not platform libraries): load them before the library that needs them */
static void needed_first(const uint8_t *file, size_t len) {
  const Ehdr *eh = (const Ehdr *)file;
  const Phdr *ph = (const Phdr *)(file + eh->phoff);
  const Dyn *dyn = NULL;
  uint32_t strtab_vaddr = 0;
  for (int i = 0; i < eh->phnum; i++) if (ph[i].type == 2) dyn = (const Dyn *)(file + ph[i].offset);
  if (!dyn) return;
  for (const Dyn *d = dyn; d->tag; d++) if (d->tag == 5) strtab_vaddr = d->val;
  // the string table's file offset: find the PT_LOAD that maps it
  uint32_t strtab = 0;
  for (int i = 0; i < eh->phnum; i++)
    if (ph[i].type == 1 && strtab_vaddr >= ph[i].vaddr && strtab_vaddr < ph[i].vaddr + ph[i].filesz)
      strtab = strtab_vaddr - ph[i].vaddr + ph[i].offset;
  if (!strtab) return;
  for (const Dyn *d = dyn; d->tag; d++) {
    if (d->tag != 1) continue;
    const char *n = (const char *)(file + strtab + d->val);
    if (is_system(n)) continue;
    int known = 0;
    for (int i = 0; i < nlibs; i++) if (!strcmp(libs[i].name, n)) known = 1;
    if (known) continue;
    uint32_t l = 0, p = js_need_library(n, &l);
    if (!p) { logf_("needed library %s is not in the APK (its symbols stay unresolved)", n); continue; }
    elf_load_named(n, G(p), l);
  }
}

static void call_init(Lib *lib, Cpu *cpu) {
  if (lib->init) guest_call(cpu, GA(lib->base) + lib->init, 0, NULL);
  uint32_t *arr = (uint32_t *)(lib->base + lib->init_array);
  for (uint32_t i = 0; i < lib->init_array_sz / 4; i++) {
    if (arr[i] == 0 || arr[i] == 0xFFFFFFFFu) continue;
    guest_call(cpu, arr[i], 0, NULL);
  }
}
/** run the constructors of every library loaded since the last call, dependencies first */
void elf_init_pending(Cpu *cpu) {
  for (int i = 0; i < nlibs; i++) {
    if (libs[i].inited) continue;
    libs[i].inited = 1;
    call_init(&libs[i], cpu);
  }
}
void elf_init(Lib *lib, Cpu *cpu) { call_init(lib, cpu); }

// ---- libdl for the guest
// Handles are bionic soinfo structures (the Android 2.3-4.x layout): some libraries read them, e.g. linkers of their
// own (Unity 3) that look symbols up in the hash tables of the library a handle names. The platform libraries share
// one symbol table made from the host's imports.
#define SOINFO_SIZE 0x200
enum { SI_NAME = 0, SI_PHDR = 0x80, SI_PHNUM = 0x84, SI_BASE = 0x8c, SI_SIZE = 0x90, SI_DYNAMIC = 0x98, SI_NEXT = 0xa4,
  SI_FLAGS = 0xa8, SI_STRTAB = 0xac, SI_SYMTAB = 0xb0, SI_NBUCKET = 0xb4, SI_NCHAIN = 0xb8, SI_BUCKET = 0xbc, SI_CHAIN = 0xc0,
  SI_REFCOUNT = 0x174 };
static uint32_t lib_soinfo[MAX_LIBS];
typedef struct { char name[32]; uint32_t si; } SysHandle;
static SysHandle sys_handles[32];
static int nsys;
static uint32_t sys_strtab, sys_symtab, sys_nsyms, sys_bucket, sys_chain, sys_nbucket;

static uint32_t elf_hash(const char *name) {
  uint32_t h = 0, g;
  for (const unsigned char *p = (const unsigned char *)name; *p; p++) {
    h = (h << 4) + *p;
    g = h & 0xf0000000u;
    h ^= g >> 24;
    h &= ~g;
  }
  return h;
}
// SysV hash table for nsyms symbols
static void sysv_hash(const Sym *syms, uint32_t nsyms, const char *strtab, uint32_t *nbucket, uint32_t *bucket, uint32_t *chain) {
  uint32_t nb = nsyms / 2 + 1;
  uint32_t *b = calloc(nb, 4), *ch = calloc(nsyms, 4);
  for (uint32_t i = 1; i < nsyms; i++) {
    uint32_t h = elf_hash(strtab + syms[i].name) % nb;
    ch[i] = b[h];
    b[h] = i;
  }
  *nbucket = nb; *bucket = GA(b); *chain = GA(ch);
}
static void sys_table(void) {
  if (sys_symtab) return;
  uint32_t n = 1, strsz = 1;
  const char *name; int is_data;
  for (int i = 0; host_import_at(i, &name, &is_data); i++) { n++; strsz += strlen(name) + 1; }
  Sym *syms = calloc(n, sizeof(Sym));
  char *str = calloc(strsz, 1);
  uint32_t k = 1, at = 1;
  for (int i = 0; ; i++) {
    uint32_t a = host_import_at(i, &name, &is_data);
    if (!a) break;
    syms[k].name = at; syms[k].value = a; syms[k].info = is_data ? 0x11 : 0x12; syms[k].shndx = 1;
    strcpy(str + at, name); at += strlen(name) + 1; k++;
  }
  sys_nsyms = k;
  sys_strtab = GA(str); sys_symtab = GA(syms);
  sysv_hash(syms, k, str, &sys_nbucket, &sys_bucket, &sys_chain);
}
static uint32_t new_soinfo(const char *name) {
  uint8_t *si = calloc(1, SOINFO_SIZE);
  snprintf((char *)si, 128, "%s", name);
  *(uint32_t *)(si + SI_FLAGS) = 1;   // FLAG_LINKED
  *(uint32_t *)(si + SI_REFCOUNT) = 1;
  return GA(si);
}
static uint32_t system_handle(const char *n) {
  for (int i = 0; i < nsys; i++) if (!strcmp(sys_handles[i].name, n)) return sys_handles[i].si;
  sys_table();
  uint32_t si = new_soinfo(n);
  uint8_t *p = G(si);
  *(uint32_t *)(p + SI_STRTAB) = sys_strtab; *(uint32_t *)(p + SI_SYMTAB) = sys_symtab;
  *(uint32_t *)(p + SI_NBUCKET) = sys_nbucket; *(uint32_t *)(p + SI_NCHAIN) = sys_nsyms;
  *(uint32_t *)(p + SI_BUCKET) = sys_bucket; *(uint32_t *)(p + SI_CHAIN) = sys_chain;
  if (nsys < 32) { snprintf(sys_handles[nsys].name, sizeof sys_handles[nsys].name, "%s", n); sys_handles[nsys++].si = si; }
  return si;
}
static uint32_t lib_handle(int i) {
  if (lib_soinfo[i]) return lib_soinfo[i];
  Lib *lib = &libs[i];
  uint32_t si = new_soinfo(lib->name), base = GA(lib->base);
  uint8_t *p = G(si);
  const Ehdr *eh = (const Ehdr *)lib->base;
  *(uint32_t *)(p + SI_PHDR) = base + eh->phoff; *(uint32_t *)(p + SI_PHNUM) = eh->phnum;
  *(uint32_t *)(p + SI_BASE) = base; *(uint32_t *)(p + SI_SIZE) = lib->size;
  *(uint32_t *)(p + SI_STRTAB) = base + lib->dynstr; *(uint32_t *)(p + SI_SYMTAB) = base + lib->dynsym;
  uint32_t nb, b, ch;
  if (lib->hash) {
    const uint32_t *h = (const uint32_t *)(lib->base + lib->hash);
    nb = h[0]; b = base + lib->hash + 8; ch = b + 4 * nb;
    *(uint32_t *)(p + SI_NCHAIN) = h[1];
  } else {
    sysv_hash((const Sym *)(lib->base + lib->dynsym), lib->nsyms, (const char *)lib->base + lib->dynstr, &nb, &b, &ch);
    *(uint32_t *)(p + SI_NCHAIN) = lib->nsyms;
  }
  *(uint32_t *)(p + SI_NBUCKET) = nb; *(uint32_t *)(p + SI_BUCKET) = b; *(uint32_t *)(p + SI_CHAIN) = ch;
  return lib_soinfo[i] = si;
}
static int handle_lib(uint32_t h) {
  for (int i = 0; i < nlibs; i++) if (lib_soinfo[i] && lib_soinfo[i] == h) return i;
  return -1;
}

static uint32_t dl_error;
static void h_dlopen(Cpu *c) {
  const char *path = G(arg(c, 0));
  if (!path) { ret32(c, system_handle("libc.so")); return; }   // the "global" handle
  const char *n = strrchr(path, '/') ? strrchr(path, '/') + 1 : path;
  for (int i = 0; i < nlibs; i++) if (!strcmp(libs[i].name, n)) { ret32(c, lib_handle(i)); return; }
  if (is_system(n) || !strncmp(n, "libc.so", 7) || !strncmp(n, "libm.so", 7)) { ret32(c, system_handle(n)); return; }
  if (strchr(path, '/')) {   // a file the app wrote or extracted (e.g. from its assets)
    FILE *f = fopen(path, "rb");
    if (f) {
      fseek(f, 0, SEEK_END);
      long len = ftell(f);
      fseek(f, 0, SEEK_SET);
      uint8_t *buf = len > 0 ? malloc(len) : NULL;
      size_t got = buf ? fread(buf, 1, len, f) : 0;
      fclose(f);
      if (buf && got == (size_t)len && len > 52 && !memcmp(buf, "\177ELF", 4)) {
        int i = elf_load_named(n, buf, len);
        free(buf);
        elf_init_pending(c);
        ret32(c, lib_handle(i));
        return;
      }
      free(buf);
    }
  }
  uint32_t l = 0, p = js_need_library(n, &l);
  if (!p) { static char msg[128]; snprintf(msg, sizeof msg, "dlopen failed: library \"%s\" not found", n); dl_error = GA(msg); ret32(c, 0); return; }
  int i = elf_load_named(n, G(p), l);
  elf_init_pending(c);
  ret32(c, lib_handle(i));
}
static void h_dlsym(Cpu *c) {
  uint32_t h = arg(c, 0);
  const char *name = G(arg(c, 1));
  int is_data = 0;
  int li = handle_lib(h);
  uint32_t a = li >= 0 ? elf_sym(&libs[li], name) : 0;   // the library's own definition first
  if (!a) a = host_import(name, &is_data);
  if (!a) a = elf_sym_any(name);
  if (!a) { static char msg[160]; snprintf(msg, sizeof msg, "undefined symbol: %s", name); dl_error = GA(msg); }
  ret32(c, a);
}
static void h_dlclose(Cpu *c) { ret32(c, 0); }
static void h_dlerror(Cpu *c) { ret32(c, dl_error); dl_error = 0; }
static void h_dladdr(Cpu *c) {
  uint32_t addr = arg(c, 0), info = arg(c, 1);
  Lib *lib = elf_lib_at(addr);
  if (!lib || !info) { ret32(c, 0); return; }
  uint32_t *d = G(info);
  d[0] = GA(lib->name); d[1] = GA(lib->base); d[2] = 0; d[3] = 0;
  ret32(c, 1);
}
void elf_host_init(void) {
  host_add_fn("dlopen", h_dlopen);
  host_add_fn("dlsym", h_dlsym);
  host_add_fn("dlclose", h_dlclose);
  host_add_fn("dlerror", h_dlerror);
  host_add_fn("dladdr", h_dladdr);
}
