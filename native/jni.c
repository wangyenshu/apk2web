// JNI for the emulated native libraries: the JNIEnv / JavaVM function tables the guest sees.
//
// Every Java object is a handle (an int) owned by the Java side (a2w.Jni): local handles live until the outermost
// native call returns, global ones until DeleteGlobalRef. Method and field IDs are the ids of the per-app table the
// linker generated (a2w.gen.JniTable); this file remembers each ID's signature to read call arguments. All Java work
// is done by library_jni.js, which calls the TeaVM exports (a2w.Main.jni*).
#include "emu.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define R32(a) (*(uint32_t *)(uintptr_t)(a))

uint32_t jni_env, jni_vm;
static uint32_t env_table[234], vm_table[8];
static uint32_t env_ptr, vm_ptr;

// ---- library_jni.js
extern uint32_t jj_find_class(const char *name);
extern uint32_t jj_superclass(uint32_t c);
extern uint32_t jj_assignable(uint32_t a, uint32_t b);
extern uint32_t jj_object_class(uint32_t o);
extern uint32_t jj_instance_of(uint32_t o, uint32_t c);
extern uint32_t jj_member(uint32_t c, const char *name, const char *sig, int method);
extern uint32_t jj_call(uint32_t id, uint32_t target, const uint32_t *slots, int n);
extern uint32_t jj_new_object(uint32_t id, const uint32_t *slots, int n);
extern uint32_t jj_alloc_object(uint32_t c);
extern uint32_t jj_get_field(uint32_t id, uint32_t target);
extern void jj_set_field(uint32_t id, uint32_t target, uint32_t lo, uint32_t hi);
extern uint32_t jj_hi(void);
extern uint32_t jj_new_string_utf(const char *s);
extern uint32_t jj_new_string_utf16(uint32_t chars, uint32_t len);
extern uint32_t jj_string_utf(uint32_t h);          // malloc'd UTF-8
extern uint32_t jj_string_utf16(uint32_t h);        // malloc'd UTF-16 (NUL-terminated)
extern uint32_t jj_string_len(uint32_t h);
extern uint32_t jj_string_utf_len(uint32_t h);
extern void jj_string_region16(uint32_t h, uint32_t start, uint32_t len, uint32_t buf);
extern void jj_string_region_utf(uint32_t h, uint32_t start, uint32_t len, uint32_t buf);
extern uint32_t jj_array_len(uint32_t h);
extern uint32_t jj_array_esize(uint32_t h);
extern uint32_t jj_new_array(int kind, uint32_t len);
extern void jj_array_get(uint32_t h, uint32_t start, uint32_t len, uint32_t buf);
extern void jj_array_set(uint32_t h, uint32_t start, uint32_t len, uint32_t buf, uint32_t esize);
extern uint32_t jj_new_object_array(uint32_t len, uint32_t c, uint32_t init);
extern uint32_t jj_get_element(uint32_t a, uint32_t i);
extern void jj_set_element(uint32_t a, uint32_t i, uint32_t v);
extern uint32_t jj_new_global(uint32_t h);
extern void jj_delete_global(uint32_t h);
extern uint32_t jj_new_local(uint32_t h);
extern uint32_t jj_same(uint32_t a, uint32_t b);
extern uint32_t jj_ref_type(uint32_t h);
extern void jj_enter(void);
extern uint32_t jj_pop_frame(uint32_t result);
extern uint32_t jj_throw(uint32_t h);
extern uint32_t jj_throw_new(uint32_t c, const char *msg);
extern uint32_t jj_exception(void);
extern void jj_exception_describe(void);
extern void jj_exception_clear(void);
extern void jj_register_native(uint32_t c, const char *name, const char *sig, uint32_t fn);
extern uint32_t jj_new_direct_buffer(uint32_t addr, uint32_t cap);
extern void jj_freed(uint32_t addr, uint32_t size);
extern uint32_t jj_direct_address(uint32_t h);
extern uint32_t jj_direct_capacity(uint32_t h);

// ---- member signatures by ID (IDs are small positive ints from the Java side)
typedef struct { char *sig; } Member;
static Member *mids, *fids;
static uint32_t nmids, nfids;

static void remember(Member **tab, uint32_t *n, uint32_t id, const char *sig) {
  if (id >= *n) {
    uint32_t m = *n ? *n : 64;
    while (m <= id) m *= 2;
    *tab = realloc(*tab, m * sizeof(Member));
    memset(*tab + *n, 0, (m - *n) * sizeof(Member));
    *n = m;
  }
  if (!(*tab)[id].sig) (*tab)[id].sig = strdup(sig);
}
static const char *msig(uint32_t id) {
  if (id >= nmids || !mids[id].sig) fatal("JNI: unknown method ID %u", id);
  return mids[id].sig;
}
static char fkind(uint32_t id) {
  if (id >= nfids || !fids[id].sig) fatal("JNI: unknown field ID %u", id);
  char k = fids[id].sig[0];
  return k == '[' ? 'L' : k;
}

// ---- arguments: the guest's (varargs, va_list or jvalue[]) -> Java slots (32-bit words, long/double = lo, hi)
enum { ARGS_VARARGS, ARGS_VALIST, ARGS_JVALUES };
static int collect(Cpu *c, const char *sig, int mode, int first, uint32_t *out, int max) {
  Va va;
  uint32_t jv = 0;
  if (mode == ARGS_VARARGS) va_from_args(&va, c, first);
  else if (mode == ARGS_VALIST) va_from_list(&va, arg(c, first));
  else jv = arg(c, first);
  int n = 0;
  const char *p = sig + 1;
  while (*p && *p != ')') {
    if (n + 2 > max) fatal("JNI: too many arguments in %s", sig);
    char t = *p;
    if (t == '[') { while (*p == '[') p++; if (*p == 'L') p = strchr(p, ';'); p++; t = 'L'; }
    else if (t == 'L') p = strchr(p, ';') + 1;
    else p++;
    if (mode == ARGS_JVALUES) {
      uint32_t lo = R32(jv), hi = R32(jv + 4);
      jv += 8;
      switch (t) {
        case 'J': case 'D': out[n++] = lo; out[n++] = hi; break;
        case 'Z': out[n++] = lo & 0xFF; break;
        case 'B': out[n++] = (uint32_t)(int32_t)(int8_t)lo; break;
        case 'C': out[n++] = lo & 0xFFFF; break;
        case 'S': out[n++] = (uint32_t)(int32_t)(int16_t)lo; break;
        default: out[n++] = lo;
      }
      continue;
    }
    if (t == 'J' || t == 'D') { uint64_t u = va64(&va); out[n++] = (uint32_t)u; out[n++] = (uint32_t)(u >> 32); }
    else if (t == 'F') {   // promoted to double through "..."
      uint64_t u = va64(&va);
      double d; memcpy(&d, &u, 8);
      float f = (float)d; uint32_t v; memcpy(&v, &f, 4);
      out[n++] = v;
    } else out[n++] = va32(&va);
  }
  return n;
}

static void put_result(Cpu *c, char kind, uint32_t lo) {
  switch (kind) {
    case 'V': break;
    case 'J': case 'D': c->r[0] = lo; c->r[1] = jj_hi(); break;
    case 'Z': c->r[0] = lo & 0xFF; break;
    case 'B': c->r[0] = (uint32_t)(int32_t)(int8_t)lo; break;
    case 'C': c->r[0] = lo & 0xFFFF; break;
    case 'S': c->r[0] = (uint32_t)(int32_t)(int16_t)lo; break;
    default: c->r[0] = lo;
  }
}
static char ret_kind(const char *sig) { char k = strchr(sig, ')')[1]; return k == '[' ? 'L' : k; }

// Call<T>Method(env, obj, id, ...), CallNonvirtual<T>Method(env, obj, cls, id, ...), CallStatic<T>Method(env, cls, id, ...)
extern double jj_take_wait(void);
extern int host_sleep_for(Cpu *c, double ms);
static void call(Cpu *c, int mode, int nonvirtual) {
  Thread *t = c->thread;
  if (t && t->jni_resume) {   // the call is done; its caller waits out what the Java side asked for, then gets the result
    if (!host_sleep_for(c, t->jni_wait)) return;
    t->jni_resume = 0;
    c->r[0] = t->jni_r0; c->r[1] = t->jni_r1;
    return;
  }
  uint32_t target = arg(c, 1);
  int idarg = nonvirtual ? 3 : 2;
  uint32_t id = arg(c, idarg);
  const char *sig = msig(id);
  uint32_t slots[64];
  int n = collect(c, sig, mode, idarg + 1, slots, 64);
  put_result(c, ret_kind(sig), jj_call(id, target, slots, n));
  double wait = jj_take_wait();
  if (wait > 0 && t) {
    t->jni_r0 = c->r[0]; t->jni_r1 = c->r[1]; t->jni_wait = wait; t->jni_resume = 1;
    if (host_sleep_for(c, wait)) { t->jni_resume = 0; }   // (already over)
  }
}
#define CALLS(T) \
  static void Call##T##Method(Cpu *c) { call(c, ARGS_VARARGS, 0); } \
  static void Call##T##MethodV(Cpu *c) { call(c, ARGS_VALIST, 0); } \
  static void Call##T##MethodA(Cpu *c) { call(c, ARGS_JVALUES, 0); } \
  static void CallNonvirtual##T##Method(Cpu *c) { call(c, ARGS_VARARGS, 1); } \
  static void CallNonvirtual##T##MethodV(Cpu *c) { call(c, ARGS_VALIST, 1); } \
  static void CallNonvirtual##T##MethodA(Cpu *c) { call(c, ARGS_JVALUES, 1); }
CALLS(Any)

static void new_object(Cpu *c, int mode) {
  uint32_t id = arg(c, 2);
  uint32_t slots[64];
  int n = collect(c, msig(id), mode, 3, slots, 64);
  c->r[0] = jj_new_object(id, slots, n);
}
static void NewObject(Cpu *c) { new_object(c, ARGS_VARARGS); }
static void NewObjectV(Cpu *c) { new_object(c, ARGS_VALIST); }
static void NewObjectA(Cpu *c) { new_object(c, ARGS_JVALUES); }
static void AllocObject(Cpu *c) { c->r[0] = jj_alloc_object(arg(c, 1)); }

// ---- members
static void get_member(Cpu *c, int method) {
  const char *name = G(arg(c, 2)), *sig = G(arg(c, 3));
  uint32_t id = jj_member(arg(c, 1), name, sig, method);
  if (id) remember(method ? &mids : &fids, method ? &nmids : &nfids, id, sig);
  c->r[0] = id;
}
static void GetMethodID(Cpu *c) { get_member(c, 1); }
static void GetFieldID(Cpu *c) { get_member(c, 0); }

// Get<T>Field(env, obj, id) / GetStatic<T>Field(env, cls, id)
static void GetField(Cpu *c) { uint32_t id = arg(c, 2); put_result(c, fkind(id), jj_get_field(id, arg(c, 1))); }
// Set<T>Field(env, obj, id, value): a 64-bit value can't start in r3, so it is on the stack
static void SetField(Cpu *c) {
  uint32_t id = arg(c, 2);
  char k = fkind(id);
  if (k == 'J' || k == 'D') { uint64_t v = arg64(c, 4); jj_set_field(id, arg(c, 1), (uint32_t)v, (uint32_t)(v >> 32)); }
  else jj_set_field(id, arg(c, 1), arg(c, 3), 0);
}

// ---- classes, objects, references
static void GetVersion(Cpu *c) { c->r[0] = 0x00010006; }
static void DefineClass(Cpu *c) { jj_throw_new(jj_find_class("java/lang/UnsupportedOperationException"), "DefineClass"); c->r[0] = 0; }
static void FindClass(Cpu *c) { c->r[0] = jj_find_class(G(arg(c, 1))); }
static void GetSuperclass(Cpu *c) { c->r[0] = jj_superclass(arg(c, 1)); }
static void IsAssignableFrom(Cpu *c) { c->r[0] = jj_assignable(arg(c, 1), arg(c, 2)); }
static void GetObjectClass(Cpu *c) { c->r[0] = jj_object_class(arg(c, 1)); }
static void IsInstanceOf(Cpu *c) { c->r[0] = jj_instance_of(arg(c, 1), arg(c, 2)); }
static void NewGlobalRef(Cpu *c) { c->r[0] = jj_new_global(arg(c, 1)); }
static void DeleteGlobalRef(Cpu *c) { jj_delete_global(arg(c, 1)); }
static void DeleteLocalRef(Cpu *c) {}
static void NewLocalRef(Cpu *c) { c->r[0] = jj_new_local(arg(c, 1)); }
static void IsSameObject(Cpu *c) { c->r[0] = jj_same(arg(c, 1), arg(c, 2)); }
static void GetObjectRefType(Cpu *c) { c->r[0] = jj_ref_type(arg(c, 1)); }
static void PushLocalFrame(Cpu *c) { jj_enter(); c->r[0] = 0; }
static void PopLocalFrame(Cpu *c) { c->r[0] = jj_pop_frame(arg(c, 1)); }
static void EnsureLocalCapacity(Cpu *c) { c->r[0] = 0; }
static void Ok(Cpu *c) { c->r[0] = 0; }

// ---- exceptions
static void Throw(Cpu *c) { c->r[0] = jj_throw(arg(c, 1)); }
static void ThrowNew(Cpu *c) { c->r[0] = jj_throw_new(arg(c, 1), G(arg(c, 2))); }
static void ExceptionOccurred(Cpu *c) { c->r[0] = jj_exception(); }
static void ExceptionCheck(Cpu *c) { c->r[0] = jj_exception() != 0; }
static void ExceptionDescribe(Cpu *c) { jj_exception_describe(); }
static void ExceptionClear(Cpu *c) { jj_exception_clear(); }
static void FatalError(Cpu *c) { fatal("JNI FatalError: %s", (const char *)G(arg(c, 1))); }

// ---- strings
static void NewString(Cpu *c) { c->r[0] = jj_new_string_utf16(arg(c, 1), arg(c, 2)); }
static void GetStringLength(Cpu *c) { c->r[0] = jj_string_len(arg(c, 1)); }
static void GetStringChars(Cpu *c) { if (arg(c, 2)) *(uint8_t *)G(arg(c, 2)) = 1; c->r[0] = jj_string_utf16(arg(c, 1)); }
static void ReleaseStringChars(Cpu *c) { free(G(arg(c, 2))); }
static void NewStringUTF(Cpu *c) { c->r[0] = arg(c, 1) ? jj_new_string_utf(G(arg(c, 1))) : 0; }
static void GetStringUTFLength(Cpu *c) { c->r[0] = jj_string_utf_len(arg(c, 1)); }
static void GetStringUTFChars(Cpu *c) { if (arg(c, 2)) *(uint8_t *)G(arg(c, 2)) = 1; c->r[0] = jj_string_utf(arg(c, 1)); }
static void GetStringRegion(Cpu *c) { jj_string_region16(arg(c, 1), arg(c, 2), arg(c, 3), arg(c, 4)); }
static void GetStringUTFRegion(Cpu *c) { jj_string_region_utf(arg(c, 1), arg(c, 2), arg(c, 3), arg(c, 4)); }

// ---- arrays
static const char KINDS[8] = {'Z', 'B', 'C', 'S', 'I', 'J', 'F', 'D'};
static const int SIZES[8] = {1, 1, 2, 2, 4, 8, 4, 8};
static void GetArrayLength(Cpu *c) { c->r[0] = jj_array_len(arg(c, 1)); }
static void NewObjectArray(Cpu *c) { c->r[0] = jj_new_object_array(arg(c, 1), arg(c, 2), arg(c, 3)); }
static void GetObjectArrayElement(Cpu *c) { c->r[0] = jj_get_element(arg(c, 1), arg(c, 2)); }
static void SetObjectArrayElement(Cpu *c) { jj_set_element(arg(c, 1), arg(c, 2), arg(c, 3)); }
static uint32_t elements(uint32_t h, uint32_t esize, uint32_t is_copy) {
  uint32_t n = jj_array_len(h);
  void *buf = malloc(n * esize + 8);
  jj_array_get(h, 0, n, GA(buf));
  if (is_copy) *(uint8_t *)G(is_copy) = 1;
  return GA(buf);
}
static void release(uint32_t h, uint32_t buf, uint32_t mode, uint32_t esize) {
  if (mode != 2) jj_array_set(h, 0, jj_array_len(h), buf, esize);   // 0: copy back and free, 1 (COMMIT): copy back
  if (mode != 1) free(G(buf));                                        // 2 (ABORT): free only
}
#define ARR(i, T) \
  static void New##T##Array(Cpu *c) { c->r[0] = jj_new_array(KINDS[i], arg(c, 1)); } \
  static void Get##T##ArrayElements(Cpu *c) { c->r[0] = elements(arg(c, 1), SIZES[i], arg(c, 2)); } \
  static void Release##T##ArrayElements(Cpu *c) { release(arg(c, 1), arg(c, 2), arg(c, 3), SIZES[i]); } \
  static void Get##T##ArrayRegion(Cpu *c) { jj_array_get(arg(c, 1), arg(c, 2), arg(c, 3), arg(c, 4)); } \
  static void Set##T##ArrayRegion(Cpu *c) { jj_array_set(arg(c, 1), arg(c, 2), arg(c, 3), arg(c, 4), SIZES[i]); }
ARR(0, Boolean) ARR(1, Byte) ARR(2, Char) ARR(3, Short) ARR(4, Int) ARR(5, Long) ARR(6, Float) ARR(7, Double)
static void GetPrimitiveArrayCritical(Cpu *c) { uint32_t h = arg(c, 1); c->r[0] = elements(h, jj_array_esize(h), arg(c, 2)); }
static void ReleasePrimitiveArrayCritical(Cpu *c) { uint32_t h = arg(c, 1); release(h, arg(c, 2), arg(c, 3), jj_array_esize(h)); }

// ---- natives, monitors, VM, buffers
static void RegisterNatives(Cpu *c) {
  uint32_t cls = arg(c, 1), m = arg(c, 2), n = arg(c, 3);
  for (uint32_t i = 0; i < n; i++, m += 12) jj_register_native(cls, G(R32(m)), G(R32(m + 4)), R32(m + 8));
  c->r[0] = 0;
}
static void GetJavaVM(Cpu *c) { R32(arg(c, 1)) = vm_ptr; c->r[0] = 0; }
// direct buffers over native memory: the page mirrors their bytes at native call boundaries, so when the memory is
// freed the mirror must go (else the next synchronization writes into the heap) — host.c's free/realloc report it
#define MAX_DIRECT 4096
static uint32_t direct_lo[MAX_DIRECT];
static int direct_n;
static void NewDirectByteBuffer(Cpu *c) {
  uint32_t a = arg(c, 1), n = (uint32_t)arg64(c, 2);
  if (direct_n < MAX_DIRECT) direct_lo[direct_n++] = a;
  c->r[0] = jj_new_direct_buffer(a, n);
}
void jni_memory_freed(uint32_t p, uint32_t size) {
  if (!direct_n || !p) return;
  int hit = 0;
  for (int i = direct_n - 1; i >= 0; i--)
    if (direct_lo[i] >= p && direct_lo[i] < p + size) { direct_lo[i] = direct_lo[--direct_n]; hit = 1; }
  if (hit) jj_freed(p, size);
}
static void GetDirectBufferAddress(Cpu *c) { c->r[0] = jj_direct_address(arg(c, 1)); }
static void GetDirectBufferCapacity(Cpu *c) { c->r[0] = jj_direct_capacity(arg(c, 1)); c->r[1] = 0; }

static void vm_DestroyJavaVM(Cpu *c) { c->r[0] = 0; }
static void vm_AttachCurrentThread(Cpu *c) { if (arg(c, 1)) R32(arg(c, 1)) = env_ptr; c->r[0] = 0; }
static void vm_DetachCurrentThread(Cpu *c) { c->r[0] = 0; }
static void vm_GetEnv(Cpu *c) { if (arg(c, 1)) R32(arg(c, 1)) = env_ptr; c->r[0] = 0; }

static void unimpl(Cpu *c) { fatal("JNI function %s is not implemented", hook_name(current_hook)); }

static const char *TYPES[10] = {"Object", "Boolean", "Byte", "Char", "Short", "Int", "Long", "Float", "Double", "Void"};
static void set(int i, const char *name, HookFn fn) { env_table[i] = hook_stub_address(hook_register(name, fn)); }

void jni_init(void) {
  static char names[234][48];
  for (int i = 0; i < 234; i++) { snprintf(names[i], 48, "JNIEnv slot %d", i); env_table[i] = hook_stub_address(hook_register(names[i], unimpl)); }
  set(4, "GetVersion", GetVersion); set(5, "DefineClass", DefineClass); set(6, "FindClass", FindClass);
  set(7, "FromReflectedMethod", unimpl); set(8, "FromReflectedField", unimpl); set(9, "ToReflectedMethod", unimpl);
  set(10, "GetSuperclass", GetSuperclass); set(11, "IsAssignableFrom", IsAssignableFrom); set(12, "ToReflectedField", unimpl);
  set(13, "Throw", Throw); set(14, "ThrowNew", ThrowNew); set(15, "ExceptionOccurred", ExceptionOccurred);
  set(16, "ExceptionDescribe", ExceptionDescribe); set(17, "ExceptionClear", ExceptionClear); set(18, "FatalError", FatalError);
  set(19, "PushLocalFrame", PushLocalFrame); set(20, "PopLocalFrame", PopLocalFrame); set(21, "NewGlobalRef", NewGlobalRef);
  set(22, "DeleteGlobalRef", DeleteGlobalRef); set(23, "DeleteLocalRef", DeleteLocalRef); set(24, "IsSameObject", IsSameObject);
  set(25, "NewLocalRef", NewLocalRef); set(26, "EnsureLocalCapacity", EnsureLocalCapacity); set(27, "AllocObject", AllocObject);
  set(28, "NewObject", NewObject); set(29, "NewObjectV", NewObjectV); set(30, "NewObjectA", NewObjectA);
  set(31, "GetObjectClass", GetObjectClass); set(32, "IsInstanceOf", IsInstanceOf); set(33, "GetMethodID", GetMethodID);
  static char cn[3][10][40];
  for (int k = 0; k < 10; k++) {
    HookFn f[3] = {CallAnyMethod, CallAnyMethodV, CallAnyMethodA};
    HookFn nv[3] = {CallNonvirtualAnyMethod, CallNonvirtualAnyMethodV, CallNonvirtualAnyMethodA};
    for (int v = 0; v < 3; v++) {
      snprintf(cn[v][k], 40, "Call%sMethod%s", TYPES[k], v == 0 ? "" : v == 1 ? "V" : "A");
      set(34 + k * 3 + v, cn[v][k], f[v]);
      set(64 + k * 3 + v, "CallNonvirtualMethod", nv[v]);
      set(114 + k * 3 + v, "CallStaticMethod", f[v]);
    }
  }
  set(94, "GetFieldID", GetFieldID);
  for (int k = 0; k < 9; k++) {
    set(95 + k, "GetField", GetField); set(104 + k, "SetField", SetField);
    set(145 + k, "GetStaticField", GetField); set(154 + k, "SetStaticField", SetField);
  }
  set(113, "GetStaticMethodID", GetMethodID); set(144, "GetStaticFieldID", GetFieldID);
  set(163, "NewString", NewString); set(164, "GetStringLength", GetStringLength); set(165, "GetStringChars", GetStringChars);
  set(166, "ReleaseStringChars", ReleaseStringChars); set(167, "NewStringUTF", NewStringUTF);
  set(168, "GetStringUTFLength", GetStringUTFLength); set(169, "GetStringUTFChars", GetStringUTFChars);
  set(170, "ReleaseStringUTFChars", ReleaseStringChars); set(171, "GetArrayLength", GetArrayLength);
  set(172, "NewObjectArray", NewObjectArray); set(173, "GetObjectArrayElement", GetObjectArrayElement);
  set(174, "SetObjectArrayElement", SetObjectArrayElement);
  HookFn news[8] = {NewBooleanArray, NewByteArray, NewCharArray, NewShortArray, NewIntArray, NewLongArray, NewFloatArray, NewDoubleArray};
  HookFn gete[8] = {GetBooleanArrayElements, GetByteArrayElements, GetCharArrayElements, GetShortArrayElements, GetIntArrayElements, GetLongArrayElements, GetFloatArrayElements, GetDoubleArrayElements};
  HookFn rele[8] = {ReleaseBooleanArrayElements, ReleaseByteArrayElements, ReleaseCharArrayElements, ReleaseShortArrayElements, ReleaseIntArrayElements, ReleaseLongArrayElements, ReleaseFloatArrayElements, ReleaseDoubleArrayElements};
  HookFn getr[8] = {GetBooleanArrayRegion, GetByteArrayRegion, GetCharArrayRegion, GetShortArrayRegion, GetIntArrayRegion, GetLongArrayRegion, GetFloatArrayRegion, GetDoubleArrayRegion};
  HookFn setr[8] = {SetBooleanArrayRegion, SetByteArrayRegion, SetCharArrayRegion, SetShortArrayRegion, SetIntArrayRegion, SetLongArrayRegion, SetFloatArrayRegion, SetDoubleArrayRegion};
  for (int i = 0; i < 8; i++) {
    set(175 + i, "NewArray", news[i]); set(183 + i, "GetArrayElements", gete[i]); set(191 + i, "ReleaseArrayElements", rele[i]);
    set(199 + i, "GetArrayRegion", getr[i]); set(207 + i, "SetArrayRegion", setr[i]);
  }
  set(215, "RegisterNatives", RegisterNatives); set(216, "UnregisterNatives", Ok); set(217, "MonitorEnter", Ok);
  set(218, "MonitorExit", Ok); set(219, "GetJavaVM", GetJavaVM); set(220, "GetStringRegion", GetStringRegion);
  set(221, "GetStringUTFRegion", GetStringUTFRegion); set(222, "GetPrimitiveArrayCritical", GetPrimitiveArrayCritical);
  set(223, "ReleasePrimitiveArrayCritical", ReleasePrimitiveArrayCritical); set(224, "GetStringCritical", GetStringChars);
  set(225, "ReleaseStringCritical", ReleaseStringChars); set(226, "NewWeakGlobalRef", NewGlobalRef);
  set(227, "DeleteWeakGlobalRef", DeleteGlobalRef); set(228, "ExceptionCheck", ExceptionCheck);
  set(229, "NewDirectByteBuffer", NewDirectByteBuffer); set(230, "GetDirectBufferAddress", GetDirectBufferAddress);
  set(231, "GetDirectBufferCapacity", GetDirectBufferCapacity); set(232, "GetObjectRefType", GetObjectRefType);
  set(233, "GetModule", Ok);

  for (int i = 0; i < 8; i++) vm_table[i] = hook_stub_address(hook_register("JavaVM slot", unimpl));
  vm_table[3] = hook_stub_address(hook_register("DestroyJavaVM", vm_DestroyJavaVM));
  vm_table[4] = hook_stub_address(hook_register("AttachCurrentThread", vm_AttachCurrentThread));
  vm_table[5] = hook_stub_address(hook_register("DetachCurrentThread", vm_DetachCurrentThread));
  vm_table[6] = hook_stub_address(hook_register("GetEnv", vm_GetEnv));
  vm_table[7] = vm_table[4];   // AttachCurrentThreadAsDaemon
  static uint32_t envs, vms;
  envs = GA(env_table);
  vms = GA(vm_table);
  env_ptr = jni_env = GA(&envs);
  vm_ptr = jni_vm = GA(&vms);
}
