// JNI services for jni.c: everything is answered by the Java side (the TeaVM exports a2w.Main.jni*, set as
// Module.java by the page), which owns all objects; the emulator only sees their handles.
addToLibrary({
  $JNI__postset: 'Module.JNI = JNI;',
  $JNI: {
    java: () => Module.java,
    utf8(s) {   // malloc'd NUL-terminated UTF-8
      s = s == null ? '' : String(s);
      const n = lengthBytesUTF8(s) + 1, p = _malloc(n);
      stringToUTF8(s, p, n);
      return p;
    },
    slots(p, n) { return n ? HEAP32.slice(p >> 2, (p >> 2) + n) : new Int32Array(0); },
    // direct buffers: native memory mirrors of java.nio buffers, synchronized at the outermost native call boundaries
    mirrors: new Map(),   // global handle -> { ptr, cap, owned }
    syncIn() { for (const [h, m] of JNI.mirrors) HEAP8.set(Module.java.jniBufferBytes(h), m.ptr); },
    syncOut() { for (const [h, m] of JNI.mirrors) Module.java.jniBufferSet(h, HEAP8.slice(m.ptr, m.ptr + m.cap)); },
  },
  js_need_library: (name, lenp) => {
    const b = Module.needLibrary && Module.needLibrary(UTF8ToString(name));
    if (!b) return 0;
    const p = _malloc(b.length);
    HEAPU8.set(b, p);
    HEAPU32[lenp >> 2] = b.length;
    return p;
  },
  // a Java method called from native code asked its caller to wait (AudioTrack.write with a full queue): ms, once
  jj_take_wait: () => { const H = globalThis.HW; if (!H || !H.nativeWait) return 0; const w = H.nativeWait; H.nativeWait = 0; return w; },
  jj_find_class__deps: ['$JNI'],
  jj_find_class: (p) => Module.java.jniFindClass(UTF8ToString(p)),
  jj_superclass: (c) => Module.java.jniGetSuperclass(c),
  jj_assignable: (a, b) => (Module.java.jniIsAssignableFrom(a, b) ? 1 : 0),
  jj_object_class: (o) => Module.java.jniGetObjectClass(o),
  jj_instance_of: (o, c) => (Module.java.jniIsInstanceOf(o, c) ? 1 : 0),
  jj_member: (c, name, sig, method) => (method ? Module.java.jniMethodId(c, UTF8ToString(name), UTF8ToString(sig))
    : Module.java.jniFieldId(c, UTF8ToString(name), UTF8ToString(sig))),
  jj_call__deps: ['$JNI'],
  jj_call: (id, target, p, n) => Module.java.jniCall(id, target, JNI.slots(p, n)),
  jj_new_object__deps: ['$JNI'],
  jj_new_object: (id, p, n) => Module.java.jniNewObject(id, JNI.slots(p, n)),
  jj_alloc_object: (c) => Module.java.jniAllocObject(c),
  jj_get_field: (id, t) => Module.java.jniGetField(id, t),
  jj_set_field: (id, t, lo, hi) => Module.java.jniSetField(id, t, lo, hi),
  jj_hi: () => Module.java.jniRetHi(),
  jj_new_string_utf: (p) => Module.java.jniNewString(UTF8ToString(p)),
  jj_new_string_utf16: (p, n) => {
    let s = '';
    for (let i = 0; i < n; i += 4096) s += String.fromCharCode.apply(null, HEAPU16.subarray((p >> 1) + i, (p >> 1) + Math.min(n, i + 4096)));
    return Module.java.jniNewString(s);
  },
  jj_string_utf__deps: ['$JNI'],
  jj_string_utf: (h) => JNI.utf8(Module.java.jniString(h)),
  jj_string_utf16: (h) => {
    const s = String(Module.java.jniString(h) ?? ''), p = _malloc(2 * s.length + 2);
    for (let i = 0; i < s.length; i++) HEAPU16[(p >> 1) + i] = s.charCodeAt(i);
    HEAPU16[(p >> 1) + s.length] = 0;
    return p;
  },
  jj_string_len: (h) => String(Module.java.jniString(h) ?? '').length,
  jj_string_utf_len: (h) => lengthBytesUTF8(String(Module.java.jniString(h) ?? '')),
  jj_string_region16: (h, start, len, buf) => {
    const s = String(Module.java.jniString(h) ?? '');
    for (let i = 0; i < len; i++) HEAPU16[(buf >> 1) + i] = s.charCodeAt(start + i);
  },
  jj_string_region_utf: (h, start, len, buf) => {
    const s = String(Module.java.jniString(h) ?? '').substr(start, len);
    stringToUTF8(s, buf, lengthBytesUTF8(s) + 1);
  },
  jj_array_len: (h) => Module.java.jniArrayLength(h),
  jj_array_esize: (h) => Module.java.jniArrayElemSize(h),
  jj_new_array: (kind, len) => Module.java.jniNewArray(kind, len),
  jj_array_get: (h, start, len, buf) => { if (len) HEAP8.set(Module.java.jniArrayGet(h, start, len), buf); },
  jj_array_set: (h, start, len, buf, esize) => { if (len) Module.java.jniArraySet(h, start, HEAP8.slice(buf, buf + len * esize)); },
  jj_new_object_array: (len, c, init) => Module.java.jniNewObjectArray(len, c, init),
  jj_get_element: (a, i) => Module.java.jniGetObjectElement(a, i),
  jj_set_element: (a, i, v) => Module.java.jniSetObjectElement(a, i, v),
  jj_new_global: (h) => Module.java.jniNewGlobalRef(h),
  jj_delete_global__deps: ['$JNI'],
  jj_delete_global: (h) => {
    const m = JNI.mirrors.get(h);
    if (m) { if (!m.owned) _free(m.ptr); JNI.mirrors.delete(h); }
    Module.java.jniDeleteGlobalRef(h);
  },
  jj_new_local: (h) => Module.java.jniNewLocalRef(h),
  jj_same: (a, b) => (Module.java.jniIsSameObject(a, b) ? 1 : 0),
  jj_ref_type: (h) => Module.java.jniRefType(h),
  jj_enter: () => Module.java.jniEnter(),
  jj_pop_frame: (h) => Module.java.jniPopFrame(h),
  jj_throw: (h) => Module.java.jniThrow(h),
  jj_throw_new: (c, msg) => Module.java.jniThrowNew(c, msg ? UTF8ToString(msg) : null),
  jj_exception: () => Module.java.jniExceptionOccurred(),
  jj_exception_describe: () => Module.java.jniExceptionDescribe(),
  jj_exception_clear: () => Module.java.jniExceptionClear(),
  jj_register_native: (c, name, sig, fn) => {
    const cls = Module.java.jniClassName(c);
    Module.registerNative(cls + '.' + UTF8ToString(name) + UTF8ToString(sig), fn);
  },
  jj_new_direct_buffer__deps: ['$JNI'],
  jj_new_direct_buffer: (addr, cap) => {
    const h = Module.java.jniNewDirectBuffer(HEAP8.slice(addr, addr + cap));
    const g = Module.java.jniNewGlobalRef(h);
    JNI.mirrors.set(g, { ptr: addr, cap, owned: true });
    Module.java.jniBufferMirror(h, g);
    return h;
  },
  // native memory under direct buffers was freed: their mirrors go (the buffers keep their last bytes)
  jj_freed__deps: ['$JNI'],
  jj_freed: (p, size) => { for (const [g, m] of JNI.mirrors) if (m.owned && m.ptr >= p && m.ptr < p + size) JNI.mirrors.delete(g); },
  jj_direct_address__deps: ['$JNI'],
  jj_direct_address: (h) => {
    let g = Module.java.jniBufferMirrorOf(h);
    const known = g && JNI.mirrors.get(g);
    if (known) return known.ptr;
    const bytes = Module.java.jniBufferBytes(h);
    if (bytes == null) return 0;
    const p = _malloc(Math.max(8, bytes.length));
    HEAP8.set(bytes, p);
    g = Module.java.jniNewGlobalRef(h);
    JNI.mirrors.set(g, { ptr: p, cap: bytes.length, owned: false });
    Module.java.jniBufferMirror(h, g);
    return p;
  },
  jj_direct_capacity: (h) => Module.java.jniBufferCapacity(h),
});
