// GL imports of the native libraries -> the page's GLES implementation (runtime/site/gles.js, Module.GLES).
// Arguments arrive as raw 32-bit words; GLTABLE (generated) says how to read them: i int, f float, b boolean,
// p pointer (passed on as the emulator heap and an offset), s C string.
addToLibrary({
  $GLN: {
    adapters: [],
    strings: new Map(),
    cstr(s) {   // a C copy of a string glGetString returns (kept for the program's lifetime)
      let p = GLN.strings.get(s);
      if (!p) { const n = lengthBytesUTF8(s) + 1; p = _malloc(n); stringToUTF8(s, p, n); GLN.strings.set(s, p); }
      return p;
    },
    put(s, max, lenp, out) {   // a string into (bufsize, length*, char*)
      s = s == null ? '' : String(s);
      if (max > 0 && out) { const n = stringToUTF8(s, out, max); if (lenp) HEAP32[lenp >> 2] = n; }
      else if (lenp) HEAP32[lenp >> 2] = 0;
    },
    special: {
      glShaderSource: (G, w) => {
        const [sh, count, strs, lens] = w;
        let src = '';
        for (let i = 0; i < count; i++) {
          const p = HEAPU32[(strs >> 2) + i], len = lens ? HEAP32[(lens >> 2) + i] : -1;
          src += len < 0 ? UTF8ToString(p) : UTF8ToString(p, len);
        }
        G.shaderSource(sh, src);
        return 0;
      },
      glGetShaderInfoLog: (G, w) => { GLN.put(G.getShaderInfoLog(w[0]), w[1], w[2], w[3]); return 0; },
      glGetProgramInfoLog: (G, w) => { GLN.put(G.getProgramInfoLog(w[0]), w[1], w[2], w[3]); return 0; },
      glGetShaderSource: (G, w) => { GLN.put(G.getShaderSource(w[0]), w[1], w[2], w[3]); return 0; },
      glGetActiveAttrib: (G, w) => GLN.activeInfo(G.getActiveAttrib(w[0], w[1]), w),
      glGetActiveUniform: (G, w) => GLN.activeInfo(G.getActiveUniform(w[0], w[1]), w),
      glGetString: (G, w) => { const s = G.getString(w[0]); return s == null ? 0 : GLN.cstr(s); },
      glDrawElements: (G, w) => { G.drawElements(w[0], w[1], w[2], G.elementBufferBound() ? null : HEAPU8, w[3]); return 0; },
      glGetPointerv: () => 0,
    },
    activeInfo(info, w) {
      if (!info) return 0;
      GLN.put(info.name, w[2], w[3], w[6]);
      if (w[4]) HEAP32[w[4] >> 2] = info.size;
      if (w[5]) HEAP32[w[5] >> 2] = info.type;
      return 0;
    },
    adapter(i) {
      const [name, kinds, ret] = GLTABLE[i];
      if (GLN.special[name]) { const f = GLN.special[name]; return (G, w) => f(G, w); }
      const core = name[2].toLowerCase() + name.slice(3);
      const f32 = new Float32Array(1), i32 = new Int32Array(f32.buffer);
      return (G, w) => {
        const fn = G[core];
        if (!fn) { G.warnOnce && G.warnOnce(name + ' is not implemented'); return 0; }
        const a = [];
        for (let k = 0; k < kinds.length; k++) {
          const v = w[k];
          switch (kinds[k]) {
            case 'f': i32[0] = v; a.push(f32[0]); break;
            case 'b': a.push(v !== 0); break;
            case 'p': if (v) a.push(HEAPU8, v); else a.push(null, 0); break;
            case 's': a.push(v ? UTF8ToString(v) : ''); break;
            default: a.push(v | 0);
          }
        }
        const r = fn.apply(G, a);
        if (ret === 's') return r == null ? 0 : GLN.cstr(r);
        return typeof r === 'boolean' ? +r : r | 0;
      };
    },
  },
  jgl_call__deps: ['$GLN', '$GLTABLE'],
  jgl_call: (i, wp, n) => {
    const G = Module.GLES;
    if (i < 0) { const [cw, ch] = Module.canvasSize ? Module.canvasSize() : [0, 0]; HEAP32[wp >> 2] = cw; HEAP32[(wp >> 2) + 1] = ch; return 0; }
    if (!G) return 0;
    const f = GLN.adapters[i] || (GLN.adapters[i] = GLN.adapter(i));
    return f(G, n ? HEAPU32.slice(wp >> 2, (wp >> 2) + n) : []);
  },
  jegl_swap: () => { if (Module.onSwap) Module.onSwap(); return 1; },
});
