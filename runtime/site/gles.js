// OpenGL ES 1.1 and 2.0 over WebGL 2, shared by the app's Java code (android.opengl.GLES10/11/20, GL10 & co.)
// and its native code (the emulator's GL imports). Object names are GLES integers; data pointers arrive as
// (bytes, offset) pairs: a Java array or buffer copied to a byte array, or the emulator's heap and a guest pointer.
// GLES 1.1's fixed-function pipeline is emulated with generated shaders.

const u8view = (u8, off, n) => (u8 ? new Uint8Array(u8.buffer, u8.byteOffset + off, Math.min(n, u8.byteLength - off)) : null);
const f32view = (u8, off, n) => { const b = u8view(u8, off, n * 4); return new Float32Array(b.buffer.slice(b.byteOffset, b.byteOffset + n * 4)); };
const i32view = (u8, off, n) => { const b = u8view(u8, off, n * 4); return new Int32Array(b.buffer.slice(b.byteOffset, b.byteOffset + n * 4)); };
function writeI32(u8, off, vals) { const dv = new DataView(u8.buffer, u8.byteOffset); for (let i = 0; i < vals.length; i++) dv.setInt32(off + 4 * i, vals[i] | 0, true); }
function writeF32(u8, off, vals) { const dv = new DataView(u8.buffer, u8.byteOffset); for (let i = 0; i < vals.length; i++) dv.setFloat32(off + 4 * i, +vals[i], true); }
const TYPE_SIZE = { 0x1400: 1, 0x1401: 1, 0x1402: 2, 0x1403: 2, 0x1404: 4, 0x1405: 4, 0x1406: 4, 0x140C: 4, 0x8D61: 2 };

export function createGLES(gl, log = console.warn) {
  const once = new Set();
  const warn = (what) => { if (!once.has(what)) { once.add(what); log('W/GLES: ' + what); } };
  let err = 0;
  const setErr = (e) => { if (!err) err = e; };
  // ---- names
  // per GL object namespace, as in GLES: a texture and a buffer can both be 1 (programs and shaders share one)
  const spaces = {};
  const sp = (kind) => (spaces[kind === 'program' || kind === 'shader' ? 'ps' : kind] ??= { objs: [null], kinds: [null], free: [] });
  const add = (o, kind) => { if (!o) return 0; const s = sp(kind), id = s.free.length ? s.free.pop() : s.objs.length; s.objs[id] = o; s.kinds[id] = kind; return id; };
  const get = (id, kind) => (id ? sp(kind).objs[id] || null : null);
  const is = (id, kind) => !!id && sp(kind).kinds[id] === kind;
  const del = (id, kind) => { const s = sp(kind); if (id && s.objs[id]) { s.objs[id] = null; s.kinds[id] = null; s.free.push(id); } };
  const idOf = (o) => { if (!o) return 0; for (const k in spaces) { const i = spaces[k].objs.indexOf(o); if (i > 0) return i; } return 0; };
  // names an app picks itself (glBindTexture(7) without glGenTextures) are created on first bind
  const named = (id, kind, make) => {
    if (!id) return null;
    const s = sp(kind);
    let o = s.objs[id];
    if (!o) {
      while (s.objs.length <= id) { s.objs.push(null); s.kinds.push(null); }
      o = s.objs[id] = make(); s.kinds[id] = kind;
      const f = s.free.indexOf(id); if (f >= 0) s.free.splice(f, 1);
    }
    return o;
  };
  // uniform locations: per program, name -> int
  const progLocs = new Map(), locObjs = [null];
  let curProgram = 0, arrayBuf = 0, elemBuf = 0, unpackAlign = 4, packAlign = 4, activeTex = 0;
  // ---- client-side arrays (GLES lets vertex data and indices live in app memory; WebGL doesn't)
  const MAXA = Math.min(16, gl.getParameter(gl.MAX_VERTEX_ATTRIBS));
  const attribs = []; for (let i = 0; i < MAXA; i++) attribs.push({ enabled: false, client: null, off: 0, size: 4, type: 0x1406, norm: false, stride: 0, buf: 0 });
  const scratch = []; for (let i = 0; i < MAXA; i++) scratch.push(gl.createBuffer());
  const scratchIdx = gl.createBuffer();
  function clientAttribs(first, count) {
    let used = false;
    for (let i = 0; i < MAXA; i++) {
      const a = attribs[i];
      if (!a.enabled || !a.client) continue;
      // vertices 0 .. first+count-1, so client and buffer-object attributes share vertex numbers
      const elem = a.size * (TYPE_SIZE[a.type] || 4), stride = a.stride || elem;
      const bytes = Math.max(0, Math.min(stride * (first + count - 1) + elem, a.client.byteLength - a.off));
      gl.bindBuffer(gl.ARRAY_BUFFER, scratch[i]);
      gl.bufferData(gl.ARRAY_BUFFER, u8view(a.client, a.off, bytes), gl.STREAM_DRAW);
      gl.vertexAttribPointer(i, a.size, a.type, a.norm, a.stride, 0);
      used = true;
    }
    if (used) gl.bindBuffer(gl.ARRAY_BUFFER, get(arrayBuf, 'buffer'));
    return used;
  }
  function maxIndex(u8, off, count, type) {
    const n = TYPE_SIZE[type] || 2, dv = new DataView(u8.buffer, u8.byteOffset + off, count * n);
    let m = 0;
    for (let i = 0; i < count; i++) { const v = n === 1 ? dv.getUint8(i) : n === 2 ? dv.getUint16(i * 2, true) : dv.getUint32(i * 4, true); if (v > m) m = v; }
    return m;
  }
  // ---- textures: sizes of a GLES upload
  function texBytes(w, h, format, type, align = unpackAlign) {
    const comps = { 0x1906: 1, 0x1909: 1, 0x190A: 2, 0x1907: 3, 0x1908: 4, 0x80E1: 4, 0x1902: 1, 0x1903: 1, 0x8227: 2, 0x8D94: 4 }[format] || 4;
    const bpp = type === 0x8363 || type === 0x8033 || type === 0x8034 ? 2 : type === 0x1406 ? 4 * comps : type === 0x1403 || type === 0x8D61 || type === 0x140B ? 2 * comps : comps;
    const row = Math.ceil((w * bpp) / align) * align;
    return h > 0 ? row * (h - 1) + w * bpp : 0;
  }
  const BGRA = 0x80E1;
  function pixels(u8, off, w, h, format, type) {
    if (!u8) return null;
    const n = texBytes(w, h, format, type);
    const v = u8view(u8, off, n);
    if (type === 0x8363 || type === 0x8033 || type === 0x8034 || type === 0x140B || type === 0x1403) return new Uint16Array(v.buffer.slice(v.byteOffset, v.byteOffset + n + (n & 1)));
    if (type === 0x1406) return new Float32Array(v.buffer.slice(v.byteOffset, v.byteOffset + n));
    if (format === BGRA) { const c = new Uint8Array(v); for (let i = 0; i + 3 < c.length; i += 4) { const t = c[i]; c[i] = c[i + 2]; c[i + 2] = t; } return c; }
    return v;
  }
  // ETC1 (GL_ETC1_RGB8_OES): WebGL has ETC2, a superset, on some devices; else decode to RGB
  const etc = gl.getExtension('WEBGL_compressed_texture_etc');
  const s3tc = gl.getExtension('WEBGL_compressed_texture_s3tc');
  gl.getExtension('EXT_color_buffer_float');
  function decodeETC1(src, w, h) {
    const out = new Uint8Array(w * h * 3), T = [[2, 8, -2, -8], [5, 17, -5, -17], [9, 29, -9, -29], [13, 42, -13, -42], [18, 60, -18, -60], [24, 80, -24, -80], [33, 106, -33, -106], [47, 183, -47, -183]];
    const c = (v) => (v < 0 ? 0 : v > 255 ? 255 : v);
    let p = 0;
    for (let by = 0; by < h; by += 4) for (let bx = 0; bx < w; bx += 4, p += 8) {
      const hi = (src[p] << 24 | src[p + 1] << 16 | src[p + 2] << 8 | src[p + 3]) >>> 0, lo = (src[p + 4] << 24 | src[p + 5] << 16 | src[p + 6] << 8 | src[p + 7]) >>> 0;
      const diff = hi & 2, flip = hi & 1, t1 = (hi >>> 5) & 7, t2 = (hi >>> 2) & 7;
      let r1, g1, b1, r2, g2, b2;
      if (diff) {
        const ext5 = (v) => (v << 3) | (v >> 2), sd = (v) => (v & 4 ? v - 8 : v);
        const R = (hi >>> 27) & 31, G = (hi >>> 19) & 31, B = (hi >>> 11) & 31;
        r1 = ext5(R); g1 = ext5(G); b1 = ext5(B);
        r2 = ext5((R + sd((hi >>> 24) & 7)) & 31); g2 = ext5((G + sd((hi >>> 16) & 7)) & 31); b2 = ext5((B + sd((hi >>> 8) & 7)) & 31);
      } else {
        const ext4 = (v) => (v << 4) | v;
        r1 = ext4((hi >>> 28) & 15); r2 = ext4((hi >>> 24) & 15); g1 = ext4((hi >>> 20) & 15); g2 = ext4((hi >>> 16) & 15); b1 = ext4((hi >>> 12) & 15); b2 = ext4((hi >>> 8) & 15);
      }
      for (let i = 0; i < 16; i++) {
        const x = i >> 2, y = i & 3;   // pixel indices run down the columns
        if (bx + x >= w || by + y >= h) continue;
        const second = flip ? y >= 2 : x >= 2;
        const idx = (((lo >>> (i + 16)) & 1) << 1) | ((lo >>> i) & 1);   // 0: +a, 1: +b, 2: -a, 3: -b
        const d = T[second ? t2 : t1][idx];
        const o = ((by + y) * w + bx + x) * 3;
        out[o] = c((second ? r2 : r1) + d); out[o + 1] = c((second ? g2 : g1) + d); out[o + 2] = c((second ? b2 : b1) + d);
      }
    }
    return out;
  }

  const G = {
    gl,
    // ================================================================ GLES 2.0
    activeTexture(t) { activeTex = t - 0x84C0; gl.activeTexture(t); },
    attachShader(p, s) { gl.attachShader(get(p, 'program'), get(s, 'shader')); },
    bindAttribLocation(p, i, name) { gl.bindAttribLocation(get(p, 'program'), i, name); },
    bindBuffer(t, b) {
      const o = b ? named(b, 'buffer', () => gl.createBuffer()) : null;
      if (t === 0x8892) arrayBuf = b; else if (t === 0x8893) elemBuf = b;
      gl.bindBuffer(t, o);
    },
    bindFramebuffer(t, f) { gl.bindFramebuffer(t, f ? named(f, 'fb', () => gl.createFramebuffer()) : null); },
    bindRenderbuffer(t, r) { gl.bindRenderbuffer(t, r ? named(r, 'rb', () => gl.createRenderbuffer()) : null); },
    bindTexture(t, x) { gl.bindTexture(t, x ? named(x, 'tex', () => gl.createTexture()) : null); },
    blendColor: (r, g, b, a) => gl.blendColor(r, g, b, a),
    blendEquation: (m) => gl.blendEquation(m),
    blendEquationSeparate: (a, b) => gl.blendEquationSeparate(a, b),
    blendFunc: (s, d) => gl.blendFunc(s, d),
    blendFuncSeparate: (a, b, c, d) => gl.blendFuncSeparate(a, b, c, d),
    bufferData(t, size, u8, off, usage) { if (u8) gl.bufferData(t, u8view(u8, off, size), usage); else gl.bufferData(t, size, usage); },
    bufferSubData(t, o, size, u8, off) { if (u8) gl.bufferSubData(t, o, u8view(u8, off, size)); },
    checkFramebufferStatus: (t) => gl.checkFramebufferStatus(t),
    clear(m) { gl.clear(m & 0x4500); },
    clearColor: (r, g, b, a) => gl.clearColor(r, g, b, a),
    clearDepthf: (d) => gl.clearDepth(d),
    clearStencil: (s) => gl.clearStencil(s),
    colorMask: (r, g, b, a) => gl.colorMask(!!r, !!g, !!b, !!a),
    compileShader(s) {
      gl.compileShader(get(s, 'shader'));
      if (!gl.getShaderParameter(get(s, 'shader'), gl.COMPILE_STATUS)) log('W/GLES: shader: ' + gl.getShaderInfoLog(get(s, 'shader')));
    },
    compressedTexImage2D(t, level, ifmt, w, h, border, size, u8, off) {
      const data = u8view(u8, off, size);
      if (ifmt === 0x8D64) {   // ETC1
        if (etc) { gl.compressedTexImage2D(t, level, etc.COMPRESSED_RGB8_ETC2, w, h, 0, data); return; }
        gl.pixelStorei(gl.UNPACK_ALIGNMENT, 1);
        gl.texImage2D(t, level, gl.RGB, w, h, 0, gl.RGB, gl.UNSIGNED_BYTE, decodeETC1(data, w, h));
        gl.pixelStorei(gl.UNPACK_ALIGNMENT, unpackAlign);
        return;
      }
      try { gl.compressedTexImage2D(t, level, ifmt, w, h, 0, data); } catch (e) { warn('compressed format 0x' + ifmt.toString(16) + ' is not supported by this browser'); }
    },
    compressedTexSubImage2D(t, level, x, y, w, h, fmt, size, u8, off) { try { gl.compressedTexSubImage2D(t, level, x, y, w, h, fmt, u8view(u8, off, size)); } catch (e) { warn('compressedTexSubImage2D 0x' + fmt.toString(16)); } },
    copyTexImage2D: (t, l, f, x, y, w, h, b) => gl.copyTexImage2D(t, l, f, x, y, w, h, 0),
    copyTexSubImage2D: (t, l, xo, yo, x, y, w, h) => gl.copyTexSubImage2D(t, l, xo, yo, x, y, w, h),
    createProgram: () => add(gl.createProgram(), 'program'),
    createShader: (t) => add(gl.createShader(t), 'shader'),
    cullFace: (m) => gl.cullFace(m),
    deleteBuffers(n, u8, off) { const ids = i32view(u8, off, n); for (const id of ids) { if (is(id, 'buffer')) { gl.deleteBuffer(get(id, 'buffer')); del(id, 'buffer'); } if (arrayBuf === id) arrayBuf = 0; if (elemBuf === id) elemBuf = 0; } },
    deleteFramebuffers(n, u8, off) { for (const id of i32view(u8, off, n)) if (is(id, 'fb')) { gl.deleteFramebuffer(get(id, 'fb')); del(id, 'fb'); } },
    deleteProgram(p) { if (is(p, 'program')) { gl.deleteProgram(get(p, 'program')); progLocs.delete(p); del(p, 'program'); } },
    deleteRenderbuffers(n, u8, off) { for (const id of i32view(u8, off, n)) if (is(id, 'rb')) { gl.deleteRenderbuffer(get(id, 'rb')); del(id, 'rb'); } },
    deleteShader(s) { if (is(s, 'shader')) { gl.deleteShader(get(s, 'shader')); del(s, 'shader'); } },
    deleteTextures(n, u8, off) { for (const id of i32view(u8, off, n)) if (is(id, 'tex')) { gl.deleteTexture(get(id, 'tex')); del(id, 'tex'); } },
    depthFunc: (f) => gl.depthFunc(f),
    depthMask: (f) => gl.depthMask(!!f),
    depthRangef: (n, f) => gl.depthRange(n, f),
    detachShader: (p, s) => gl.detachShader(get(p, 'program'), get(s, 'shader')),
    disable(c) { if (fixedCaps.has(c)) ff.caps[c] = false; else gl.disable(c); },
    disableVertexAttribArray(i) { if (i < MAXA) attribs[i].enabled = false; gl.disableVertexAttribArray(i); },
    drawArrays(mode, first, count) {
      if (ffActive()) ffPrepare();
      clientAttribs(first, count);
      gl.drawArrays(mode, first, count);
    },
    drawElements(mode, count, type, u8, off) {
      if (ffActive()) ffPrepare();
      if (u8) {   // indices in client memory
        const n = count * (TYPE_SIZE[type] || 2);
        if (attribsClient()) clientAttribs(0, maxIndex(u8, off, count, type) + 1);
        gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, scratchIdx);
        gl.bufferData(gl.ELEMENT_ARRAY_BUFFER, u8view(u8, off, n), gl.STREAM_DRAW);
        gl.drawElements(mode, count, type, 0);
        gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, get(elemBuf, 'buffer'));
      } else {
        if (attribsClient()) warn('client vertex arrays with indices in a buffer object (drawn from the start of the arrays)'), clientAttribs(0, 65536);
        gl.drawElements(mode, count, type, off);
      }
    },
    enable(c) { if (fixedCaps.has(c)) ff.caps[c] = true; else gl.enable(c); },
    enableVertexAttribArray(i) { if (i < MAXA) attribs[i].enabled = true; gl.enableVertexAttribArray(i); },
    finish: () => gl.finish(),
    flush: () => gl.flush(),
    framebufferRenderbuffer: (t, a, rt, r) => gl.framebufferRenderbuffer(t, a, rt, get(r, 'rb')),
    framebufferTexture2D: (t, a, tt, x, l) => gl.framebufferTexture2D(t, a, tt, get(x, 'tex'), l),
    frontFace: (m) => gl.frontFace(m),
    genBuffers(n, u8, off) { const ids = []; for (let i = 0; i < n; i++) ids.push(add(gl.createBuffer(), 'buffer')); writeI32(u8, off, ids); },
    generateMipmap: (t) => gl.generateMipmap(t),
    genFramebuffers(n, u8, off) { const ids = []; for (let i = 0; i < n; i++) ids.push(add(gl.createFramebuffer(), 'fb')); writeI32(u8, off, ids); },
    genRenderbuffers(n, u8, off) { const ids = []; for (let i = 0; i < n; i++) ids.push(add(gl.createRenderbuffer(), 'rb')); writeI32(u8, off, ids); },
    genTextures(n, u8, off) { const ids = []; for (let i = 0; i < n; i++) ids.push(add(gl.createTexture(), 'tex')); writeI32(u8, off, ids); },
    getActiveAttrib(p, i) { return gl.getActiveAttrib(get(p, 'program'), i); },     // {size, type, name}
    getActiveUniform(p, i) { return gl.getActiveUniform(get(p, 'program'), i); },
    getAttachedShaders(p, max, cu8, coff, u8, off) { const s = (gl.getAttachedShaders(get(p, 'program')) || []).slice(0, max).map(idOf); if (cu8) writeI32(cu8, coff, [s.length]); writeI32(u8, off, s); },
    getAttribLocation: (p, name) => gl.getAttribLocation(get(p, 'program'), name),
    getBooleanv(pname, u8, off) { const v = G.getv(pname); const a = Array.isArray(v) || ArrayBuffer.isView(v) ? Array.from(v) : [v]; for (let i = 0; i < a.length; i++) u8[off + i] = a[i] ? 1 : 0; },
    getBufferParameteriv(t, pname, u8, off) { writeI32(u8, off, [gl.getBufferParameter(t, pname) | 0]); },
    getError() { const e = err || gl.getError(); err = 0; return e; },
    getFloatv(pname, u8, off) { const v = G.getv(pname); writeF32(u8, off, Array.isArray(v) || ArrayBuffer.isView(v) ? Array.from(v) : [+v]); },
    getFramebufferAttachmentParameteriv(t, a, pname, u8, off) { const v = gl.getFramebufferAttachmentParameter(t, a, pname); writeI32(u8, off, [typeof v === 'object' ? idOf(v) : v | 0]); },
    getIntegerv(pname, u8, off) {
      const v = G.getv(pname);
      writeI32(u8, off, Array.isArray(v) || ArrayBuffer.isView(v) ? Array.from(v, (x) => (typeof x === 'boolean' ? +x : x)) : [typeof v === 'object' ? idOf(v) : typeof v === 'boolean' ? +v : v | 0]);
    },
    getv(pname) {
      switch (pname) {
        case 0x8DFB: return gl.getParameter(gl.MAX_VERTEX_UNIFORM_VECTORS);
        case 0x8DFD: return gl.getParameter(gl.MAX_FRAGMENT_UNIFORM_VECTORS);
        case 0x8DFC: return gl.getParameter(gl.MAX_VARYING_VECTORS);
        case 0x86A2: return 1;           // NUM_COMPRESSED_TEXTURE_FORMATS
        case 0x86A3: return [0x8D64];    // COMPRESSED_TEXTURE_FORMATS: ETC1
        case 0x8B9A: return 0x1401;      // IMPLEMENTATION_COLOR_READ_TYPE
        case 0x8B9B: return 0x1908;      // IMPLEMENTATION_COLOR_READ_FORMAT
        case 0x8DF9: return 0;           // NUM_SHADER_BINARY_FORMATS
        case 0x8B8D: return curProgram;  // CURRENT_PROGRAM
        case 0x821B: if (clientVersion >= 3) return 3; setErr(0x500); return 0;   // MAJOR_VERSION (GLES 3)
        case 0x821C: if (clientVersion >= 3) return 0; setErr(0x500); return 0;   // MINOR_VERSION
        case 0x821D: return (G.getString(0x1F03) || '').split(' ').filter((x) => x).length;   // NUM_EXTENSIONS
        case 0x8894: return arrayBuf;    // ARRAY_BUFFER_BINDING
        case 0x8895: return elemBuf;
        case 0x0D33: return gl.getParameter(gl.MAX_TEXTURE_SIZE);
        case 0x0D31: return 8;           // MAX_LIGHTS (GLES 1)
        case 0x0D32: return 8;           // MAX_CLIP_PLANES
        case 0x0D36: return 32;          // MAX_MODELVIEW_STACK_DEPTH
        case 0x0D38: return 2;           // MAX_PROJECTION_STACK_DEPTH
        case 0x0D39: return 2;           // MAX_TEXTURE_STACK_DEPTH
        case 0x84E2: return 2;           // MAX_TEXTURE_UNITS (GLES 1)
        case 0x0BA6: return ff.mv[ff.mv.length - 1];   // MODELVIEW_MATRIX
        case 0x0BA7: return ff.proj[ff.proj.length - 1];
        case 0x0BA8: return ff.tex[ff.unit][ff.tex[ff.unit].length - 1];
        case 0x0BA0: return ff.mode;     // MATRIX_MODE
        default: {
          const v = gl.getParameter(pname);
          if (v === null && gl.getError()) setErr(0x500);
          return v == null ? 0 : v;
        }
      }
    },
    getProgramiv(p, pname, u8, off) {
      let v;
      if (pname === 0x8B84) v = (gl.getProgramInfoLog(get(p, 'program')) || '').length + 1;            // INFO_LOG_LENGTH
      else if (pname === 0x8B87) v = Math.max(0, ...Array.from({ length: gl.getProgramParameter(get(p, 'program'), gl.ACTIVE_UNIFORMS) }, (_, i) => gl.getActiveUniform(get(p, 'program'), i).name.length + 1));
      else if (pname === 0x8B8A) v = Math.max(0, ...Array.from({ length: gl.getProgramParameter(get(p, 'program'), gl.ACTIVE_ATTRIBUTES) }, (_, i) => gl.getActiveAttrib(get(p, 'program'), i).name.length + 1));
      else v = gl.getProgramParameter(get(p, 'program'), pname);
      writeI32(u8, off, [typeof v === 'boolean' ? +v : v | 0]);
    },
    getProgramInfoLog: (p) => gl.getProgramInfoLog(get(p, 'program')) || '',
    getRenderbufferParameteriv(t, pname, u8, off) { writeI32(u8, off, [gl.getRenderbufferParameter(t, pname) | 0]); },
    getShaderiv(s, pname, u8, off) {
      let v;
      if (pname === 0x8B84) v = (gl.getShaderInfoLog(get(s, 'shader')) || '').length + 1;
      else if (pname === 0x8B88) v = (gl.getShaderSource(get(s, 'shader')) || '').length + 1;
      else v = gl.getShaderParameter(get(s, 'shader'), pname);
      writeI32(u8, off, [typeof v === 'boolean' ? +v : v | 0]);
    },
    getShaderInfoLog: (s) => gl.getShaderInfoLog(get(s, 'shader')) || '',
    getShaderPrecisionFormat(st, pt, ru8, roff, pu8, poff) { const f = gl.getShaderPrecisionFormat(st, pt); writeI32(ru8, roff, [f.rangeMin, f.rangeMax]); writeI32(pu8, poff, [f.precision]); },
    getShaderSource: (s) => gl.getShaderSource(get(s, 'shader')) || '',
    getString(name) {
      switch (name) {
        case 0x1F00: return 'apk2web';
        case 0x1F01: return 'WebGL 2';
        case 0x1F02: return ffUsed ? 'OpenGL ES-CM 1.1' : clientVersion >= 3 ? 'OpenGL ES 3.0 (WebGL 2)' : 'OpenGL ES 2.0 (WebGL 2)';
        case 0x8B8C: return clientVersion >= 3 ? 'OpenGL ES GLSL ES 3.00' : 'OpenGL ES GLSL ES 1.00';
        case 0x1F03: return 'GL_OES_compressed_ETC1_RGB8_texture GL_OES_rgb8_rgba8 GL_OES_depth24 GL_OES_element_index_uint GL_OES_packed_depth_stencil GL_OES_texture_npot GL_OES_vertex_array_object GL_OES_standard_derivatives GL_OES_draw_texture GL_OES_framebuffer_object GL_OES_point_sprite GL_OES_blend_subtract GL_OES_blend_func_separate GL_OES_matrix_get GL_EXT_texture_format_BGRA8888' + (s3tc ? ' GL_EXT_texture_compression_s3tc GL_EXT_texture_compression_dxt1' : '');
        default: setErr(0x500); return null;
      }
    },
    getTexParameterfv(t, pname, u8, off) { writeF32(u8, off, [+gl.getTexParameter(t, pname)]); },
    getTexParameteriv(t, pname, u8, off) { writeI32(u8, off, [gl.getTexParameter(t, pname) | 0]); },
    getUniformfv(p, l, u8, off) { const v = gl.getUniform(get(p, 'program'), locObjs[l]); writeF32(u8, off, typeof v === 'number' ? [v] : Array.from(v)); },
    getUniformiv(p, l, u8, off) { const v = gl.getUniform(get(p, 'program'), locObjs[l]); writeI32(u8, off, typeof v === 'number' || typeof v === 'boolean' ? [+v] : Array.from(v, Number)); },
    getUniformLocation(p, name) {
      let m = progLocs.get(p, 'program');
      if (!m) progLocs.set(p, (m = new Map()));
      if (m.has(name)) return m.get(name);
      const l = gl.getUniformLocation(get(p, 'program'), name);
      let id = -1;
      if (l) { id = locObjs.length; locObjs.push(l); }
      m.set(name, id);
      return id;
    },
    getVertexAttribfv(i, pname, u8, off) { const v = gl.getVertexAttrib(i, pname); writeF32(u8, off, typeof v === 'object' && v && v.length ? Array.from(v) : [+v]); },
    getVertexAttribiv(i, pname, u8, off) { const v = gl.getVertexAttrib(i, pname); writeI32(u8, off, v && typeof v === 'object' ? (v.length ? Array.from(v) : [idOf(v)]) : [+v]); },
    hint: (t, m) => { if (t !== 0x0C50 && t !== 0x0C51 && t !== 0x0C52 && t !== 0x0C53 && t !== 0x0C54) gl.hint(t, m); },
    isBuffer: (b) => is(b, 'buffer'),
    isEnabled: (c) => (fixedCaps.has(c) ? !!ff.caps[c] : gl.isEnabled(c)),
    isFramebuffer: (f) => is(f, 'fb'),
    isProgram: (p) => is(p, 'program'),
    isRenderbuffer: (r) => is(r, 'rb'),
    isShader: (s) => is(s, 'shader'),
    isTexture: (t) => is(t, 'tex'),
    lineWidth: (w) => gl.lineWidth(w),
    linkProgram(p) {
      gl.linkProgram(get(p, 'program'));
      progLocs.delete(p);
      if (!gl.getProgramParameter(get(p, 'program'), gl.LINK_STATUS)) log('W/GLES: link: ' + gl.getProgramInfoLog(get(p, 'program')));
    },
    pixelStorei(pname, v) { if (pname === 0x0CF5) unpackAlign = v; if (pname === 0x0D05) packAlign = v; gl.pixelStorei(pname, v); },
    polygonOffset: (f, u) => gl.polygonOffset(f, u),
    readPixels(x, y, w, h, fmt, type, u8, off) {
      if (!u8) { gl.readPixels(x, y, w, h, fmt, type, off); return; }   // into the bound pixel pack buffer
      const n = texBytes(w, h, fmt, type, packAlign);
      if (fmt === 0x1908 && type === 0x1401) { gl.readPixels(x, y, w, h, gl.RGBA, gl.UNSIGNED_BYTE, u8view(u8, off, n)); return; }
      // other formats: read RGBA bytes, convert row by row
      const rgba = new Uint8Array(w * h * 4);
      gl.readPixels(x, y, w, h, gl.RGBA, gl.UNSIGNED_BYTE, rgba);
      const out = u8view(u8, off, n), row = n / Math.max(1, h) | 0, stride = h > 1 ? Math.ceil(texBytes(w, 1, fmt, type, 1) / packAlign) * packAlign : 0;
      for (let j = 0; j < h; j++) for (let i = 0; i < w; i++) {
        const s = (j * w + i) * 4, r = rgba[s], g = rgba[s + 1], b = rgba[s + 2], a = rgba[s + 3];
        if (fmt === 0x1907 && type === 0x1401) { const d = j * stride + i * 3; out[d] = r; out[d + 1] = g; out[d + 2] = b; }
        else if (type === 0x8363) { const v = ((r >> 3) << 11) | ((g >> 2) << 5) | (b >> 3), d = j * stride + i * 2; out[d] = v & 255; out[d + 1] = v >> 8; }
        else if (fmt === 0x1906) out[j * stride + i] = a;
        else { const d = j * stride + i * 4; out[d] = r; out[d + 1] = g; out[d + 2] = b; out[d + 3] = a; }
      }
      void row;
    },
    releaseShaderCompiler() {},
    renderbufferStorage(t, f, w, h) {
      const map = { 0x8D62: gl.RGB565, 0x8056: gl.RGBA4, 0x8057: gl.RGB5_A1, 0x81A5: gl.DEPTH_COMPONENT16, 0x81A6: gl.DEPTH_COMPONENT24, 0x8D48: gl.STENCIL_INDEX8, 0x88F0: gl.DEPTH24_STENCIL8, 0x8051: gl.RGB8, 0x8058: gl.RGBA8 };
      gl.renderbufferStorage(t, map[f] || f, w, h);
    },
    sampleCoverage: (v, i) => gl.sampleCoverage(v, !!i),
    scissor: (x, y, w, h) => gl.scissor(x, y, w, h),
    shaderBinary() { setErr(0x500); },
    shaderSource(s, src) { gl.shaderSource(get(s, 'shader'), src); },
    stencilFunc: (f, r, m) => gl.stencilFunc(f, r, m),
    stencilFuncSeparate: (fc, f, r, m) => gl.stencilFuncSeparate(fc, f, r, m),
    stencilMask: (m) => gl.stencilMask(m),
    stencilMaskSeparate: (f, m) => gl.stencilMaskSeparate(f, m),
    stencilOp: (a, b, c) => gl.stencilOp(a, b, c),
    stencilOpSeparate: (f, a, b, c) => gl.stencilOpSeparate(f, a, b, c),
    texImage2D(t, level, ifmt, w, h, border, fmt, type, u8, off) {
      if (fmt === BGRA) fmt = ifmt = gl.RGBA;
      if (ifmt !== fmt && !(ifmt > 0x8000)) ifmt = fmt;   // GLES requires them equal; old code passes 3/4 or GL_RGBA8
      if (ifmt === 3) ifmt = gl.RGB; else if (ifmt === 4) ifmt = gl.RGBA;
      if (type === 0x8D61) type = gl.HALF_FLOAT;
      gl.texImage2D(t, level, ifmt, w, h, 0, fmt, type, pixels(u8, off, w, h, fmt, type));
    },
    /** a page image (android.graphics.Bitmap) as a texture: GLUtils.texImage2D */
    texImageSource(t, level, ifmt, type, img, premultiplied) {
      gl.pixelStorei(gl.UNPACK_PREMULTIPLY_ALPHA_WEBGL, !!premultiplied);
      const f = ifmt === gl.RGB || ifmt === gl.ALPHA || ifmt === gl.LUMINANCE || ifmt === gl.LUMINANCE_ALPHA ? ifmt : gl.RGBA;
      gl.texImage2D(t, level, f, f, type || gl.UNSIGNED_BYTE, img);
      gl.pixelStorei(gl.UNPACK_PREMULTIPLY_ALPHA_WEBGL, false);
    },
    texSubImageSource(t, level, x, y, img, fmt, type) { gl.texSubImage2D(t, level, x, y, fmt || gl.RGBA, type || gl.UNSIGNED_BYTE, img); },
    texParameterf(t, p, v) { if (p === 0x8191) { if (v) gl.generateMipmap(t); return; } if (p === 0x84FE) return; gl.texParameterf(t, p, v); },
    texParameterfv(t, p, u8, off) { G.texParameterf(t, p, f32view(u8, off, 1)[0]); },
    texParameteri(t, p, v) { if (p === 0x8191) { if (v) genMipmapOn.add(t); return; } gl.texParameteri(t, p, v); },
    texParameteriv(t, p, u8, off) { G.texParameteri(t, p, i32view(u8, off, 1)[0]); },
    texSubImage2D(t, level, x, y, w, h, fmt, type, u8, off) {
      if (fmt === BGRA) fmt = gl.RGBA;
      gl.texSubImage2D(t, level, x, y, w, h, fmt, type, pixels(u8, off, w, h, fmt, type));
    },
    useProgram(p) { curProgram = p; gl.useProgram(get(p, 'program')); },
    validateProgram: (p) => gl.validateProgram(get(p, 'program')),
    vertexAttribPointer(i, size, type, norm, stride, u8, off) {
      if (i >= MAXA) return;
      const a = attribs[i];
      Object.assign(a, { size, type, norm: !!norm, stride, client: arrayBuf ? null : u8, off });
      if (arrayBuf || !u8) gl.vertexAttribPointer(i, size, type, !!norm, stride, off);
    },
    viewport: (x, y, w, h) => gl.viewport(x, y, w, h),
  };
  const genMipmapOn = new Set();
  G.elementBufferBound = () => elemBuf !== 0;
  G.setError = (e) => setErr(e);
  G.warnOnce = warn;
  const attribsClient = () => attribs.some((a) => a.enabled && a.client);
  // uniforms
  for (const n of [1, 2, 3, 4]) {
    G['uniform' + n + 'f'] = (l, ...v) => gl['uniform' + n + 'f'](locObjs[l] || null, ...v);
    G['uniform' + n + 'i'] = (l, ...v) => gl['uniform' + n + 'i'](locObjs[l] || null, ...v);
    G['uniform' + n + 'fv'] = (l, count, u8, off) => gl['uniform' + n + 'fv'](locObjs[l] || null, f32view(u8, off, n * count));
    G['uniform' + n + 'iv'] = (l, count, u8, off) => gl['uniform' + n + 'iv'](locObjs[l] || null, i32view(u8, off, n * count));
    G['vertexAttrib' + n + 'f'] = (i, ...v) => gl['vertexAttrib' + n + 'f'](i, ...v);
    G['vertexAttrib' + n + 'fv'] = (i, u8, off) => gl['vertexAttrib' + n + 'fv'](i, f32view(u8, off, n));
  }
  for (const n of [2, 3, 4]) G['uniformMatrix' + n + 'fv'] = (l, count, tr, u8, off) => gl['uniformMatrix' + n + 'fv'](locObjs[l] || null, !!tr, f32view(u8, off, n * n * count));
  // the OES framebuffer_object names (GLES 1.1 extension) are the GLES 2 functions
  for (const k of ['bindFramebuffer', 'bindRenderbuffer', 'checkFramebufferStatus', 'deleteFramebuffers', 'deleteRenderbuffers', 'framebufferRenderbuffer',
    'framebufferTexture2D', 'genFramebuffers', 'genRenderbuffers', 'generateMipmap', 'getFramebufferAttachmentParameteriv', 'getRenderbufferParameteriv',
    'isFramebuffer', 'isRenderbuffer', 'renderbufferStorage', 'blendEquation', 'blendEquationSeparate', 'blendFuncSeparate']) G[k + 'OES'] = G[k];
  // vertex array objects (GLES 3 / OES_vertex_array_object)
  G.genVertexArrays = G.genVertexArraysOES = (n, u8, off) => { const ids = []; for (let i = 0; i < n; i++) ids.push(add(gl.createVertexArray(), 'vao')); writeI32(u8, off, ids); };
  G.bindVertexArray = G.bindVertexArrayOES = (v) => gl.bindVertexArray(v ? named(v, 'vao', () => gl.createVertexArray()) : null);
  G.deleteVertexArrays = G.deleteVertexArraysOES = (n, u8, off) => { for (const id of i32view(u8, off, n)) if (is(id, 'vao')) { gl.deleteVertexArray(get(id, 'vao')); del(id, 'vao'); } };
  G.isVertexArray = G.isVertexArrayOES = (v) => is(v, 'vao');
  G.mapBufferOES = () => { warn('glMapBufferOES'); return 0; };

  // ================================================================ GLES 3.0 (WebGL 2)
  const writeI64 = (u8, off, vals) => { const dv = new DataView(u8.buffer, u8.byteOffset); for (let i = 0; i < vals.length; i++) { const v = Number(vals[i]) || 0; dv.setUint32(off + 8 * i, v >>> 0, true); dv.setInt32(off + 8 * i + 4, Math.floor(v / 4294967296), true); } };
  const u32view = (u8, off, n) => { const b = u8view(u8, off, n * 4); return new Uint32Array(b.buffer.slice(b.byteOffset, b.byteOffset + n * 4)); };
  const asList = (v) => (Array.isArray(v) || ArrayBuffer.isView(v) ? Array.from(v, (x) => (typeof x === 'boolean' ? +x : x)) : [typeof v === 'object' ? idOf(v) : typeof v === 'boolean' ? +v : v == null ? 0 : v]);
  const genIn = (kind, make) => (n, u8, off) => { const ids = []; for (let i = 0; i < n; i++) ids.push(add(make(), kind)); writeI32(u8, off, ids); };
  const delIn = (kind, free) => (n, u8, off) => { for (const id of i32view(u8, off, n)) if (is(id, kind)) { free(get(id, kind)); del(id, kind); } };
  let clientVersion = 2;
  Object.assign(G, {
    setClientVersion(v) { clientVersion = v; },
    readBuffer: (m) => gl.readBuffer(m),
    drawRangeElements(mode, start, end, count, type, u8, off) { G.drawElements(mode, count, type, u8, off); },
    texImage3D(t, level, ifmt, w, h, d, border, fmt, type, u8, off) {
      if (!u8) { gl.texImage3D(t, level, ifmt, w, h, d, 0, fmt, type, off); return; }
      const n = texBytes(w, h, fmt, type) * Math.max(1, d);
      const v = u8view(u8, off, n);
      const data = type === 0x1406 ? new Float32Array(v.buffer.slice(v.byteOffset, v.byteOffset + n)) : type === 0x1403 || type === 0x140B || type === 0x8D61 || type === 0x8363 || type === 0x8033 || type === 0x8034 ? new Uint16Array(v.buffer.slice(v.byteOffset, v.byteOffset + n + (n & 1))) : type === 0x1405 || type === 0x1404 || type === 0x8C3B || type === 0x8C3E || type === 0x8368 || type === 0x84FA ? new Uint32Array(v.buffer.slice(v.byteOffset, v.byteOffset + n)) : v;
      gl.texImage3D(t, level, ifmt, w, h, d, 0, fmt, type, type === 0x1404 ? new Int32Array(data.buffer) : data);
    },
    texSubImage3D(t, level, x, y, z, w, h, d, fmt, type, u8, off) {
      if (!u8) { gl.texSubImage3D(t, level, x, y, z, w, h, d, fmt, type, off); return; }
      const n = texBytes(w, h, fmt, type) * Math.max(1, d);
      const v = u8view(u8, off, n);
      const data = type === 0x1406 ? new Float32Array(v.buffer.slice(v.byteOffset, v.byteOffset + n)) : type === 0x1403 || type === 0x140B || type === 0x8D61 || type === 0x8363 || type === 0x8033 || type === 0x8034 ? new Uint16Array(v.buffer.slice(v.byteOffset, v.byteOffset + n + (n & 1))) : type === 0x1405 || type === 0x8C3B || type === 0x8C3E || type === 0x8368 || type === 0x84FA ? new Uint32Array(v.buffer.slice(v.byteOffset, v.byteOffset + n)) : v;
      gl.texSubImage3D(t, level, x, y, z, w, h, d, fmt, type, data);
    },
    copyTexSubImage3D: (t, level, xo, yo, zo, x, y, w, h) => gl.copyTexSubImage3D(t, level, xo, yo, zo, x, y, w, h),
    compressedTexImage3D(t, level, ifmt, w, h, d, border, size, u8, off) {
      try { if (u8) gl.compressedTexImage3D(t, level, ifmt, w, h, d, 0, u8view(u8, off, size)); else gl.compressedTexImage3D(t, level, ifmt, w, h, d, 0, size, off); }
      catch (e) { warn('compressed 3D format 0x' + ifmt.toString(16) + ' is not supported by this browser'); }
    },
    compressedTexSubImage3D(t, level, x, y, z, w, h, d, fmt, size, u8, off) {
      try { if (u8) gl.compressedTexSubImage3D(t, level, x, y, z, w, h, d, fmt, u8view(u8, off, size)); else gl.compressedTexSubImage3D(t, level, x, y, z, w, h, d, fmt, size, off); }
      catch (e) { warn('compressedTexSubImage3D 0x' + fmt.toString(16)); }
    },
    texStorage2D: (t, levels, ifmt, w, h) => gl.texStorage2D(t, levels, ifmt, w, h),
    texStorage3D: (t, levels, ifmt, w, h, d) => gl.texStorage3D(t, levels, ifmt, w, h, d),
    // queries
    genQueries: genIn('query', () => gl.createQuery()),
    deleteQueries: delIn('query', (o) => gl.deleteQuery(o)),
    isQuery: (q) => is(q, 'query'),
    beginQuery: (t, q) => gl.beginQuery(t, named(q, 'query', () => gl.createQuery())),
    endQuery: (t) => gl.endQuery(t),
    getQueryiv(t, pname, u8, off) { const v = gl.getQuery(t, pname); writeI32(u8, off, [typeof v === 'object' ? idOf(v) : v | 0]); },
    getQueryObjectuiv(q, pname, u8, off) { const v = gl.getQueryParameter(get(q, 'query'), pname); writeI32(u8, off, [typeof v === 'boolean' ? +v : v >>> 0]); },
    // buffers
    getBufferSubData(t, srcOff, u8, off, n) { gl.getBufferSubData(t, srcOff, u8view(u8, off, n)); },
    copyBufferSubData: (rt, wt, ro, wo, n) => gl.copyBufferSubData(rt, wt, ro, wo, n),
    bindBufferBase(t, index, b) { gl.bindBufferBase(t, index, b ? named(b, 'buffer', () => gl.createBuffer()) : null); },
    bindBufferRange(t, index, b, off, size) { gl.bindBufferRange(t, index, b ? named(b, 'buffer', () => gl.createBuffer()) : null, off, size); },
    getBufferParameteri64v(t, pname, u8, off) { writeI64(u8, off, [gl.getBufferParameter(t, pname) || 0]); },
    // framebuffers
    drawBuffers(n, u8, off) { gl.drawBuffers(Array.from(i32view(u8, off, n))); },
    blitFramebuffer: (x0, y0, x1, y1, dx0, dy0, dx1, dy1, mask, filter) => gl.blitFramebuffer(x0, y0, x1, y1, dx0, dy0, dx1, dy1, mask, filter),
    renderbufferStorageMultisample: (t, samples, ifmt, w, h) => gl.renderbufferStorageMultisample(t, samples, ifmt, w, h),
    framebufferTextureLayer: (t, a, x, level, layer) => gl.framebufferTextureLayer(t, a, get(x, 'tex'), level, layer),
    invalidateFramebuffer(t, n, u8, off) { gl.invalidateFramebuffer(t, Array.from(i32view(u8, off, n))); },
    invalidateSubFramebuffer(t, n, u8, off, x, y, w, h) { gl.invalidateSubFramebuffer(t, Array.from(i32view(u8, off, n)), x, y, w, h); },
    clearBufferiv(b, db, u8, off) { gl.clearBufferiv(b, db, i32view(u8, off, b === 0x1802 ? 1 : 4)); },
    clearBufferuiv(b, db, u8, off) { gl.clearBufferuiv(b, db, u32view(u8, off, 4)); },
    clearBufferfv(b, db, u8, off) { gl.clearBufferfv(b, db, f32view(u8, off, b === 0x1801 ? 1 : 4)); },
    clearBufferfi: (b, db, depth, stencil) => gl.clearBufferfi(b, db, depth, stencil),
    getInternalformativ(t, ifmt, pname, n, u8, off) {
      const v = gl.getInternalformatParameter(t, ifmt, pname);
      const a = pname === 0x9380 ? [v ? v.length : 0] : v ? Array.from(v) : [];
      writeI32(u8, off, a.slice(0, n));
    },
    // state
    getIntegeri_v(t, index, u8, off) { writeI32(u8, off, asList(gl.getIndexedParameter(t, index)).map((x) => x | 0)); },
    getInteger64v(pname, u8, off) { writeI64(u8, off, asList(G.getv(pname))); },
    getInteger64i_v(t, index, u8, off) { writeI64(u8, off, asList(gl.getIndexedParameter(t, index))); },
    getStringi(name, index) {
      if (name !== 0x1F03) { setErr(0x500); return null; }
      const ext = (G.getString(0x1F03) || '').split(' ').filter((s) => s);
      if (index < 0 || index >= ext.length) { setErr(0x501); return null; }
      return ext[index];
    },
    // vertex attributes
    vertexAttribIPointer(i, size, type, stride, u8, off) {
      if (i >= MAXA) return;
      if (!arrayBuf && u8) { warn('glVertexAttribIPointer with client memory'); return; }
      gl.vertexAttribIPointer(i, size, type, stride, off);
    },
    vertexAttribDivisor: (i, d) => gl.vertexAttribDivisor(i, d),
    vertexAttribI4i: (i, x, y, z, w) => gl.vertexAttribI4i(i, x, y, z, w),
    vertexAttribI4ui: (i, x, y, z, w) => gl.vertexAttribI4ui(i, x >>> 0, y >>> 0, z >>> 0, w >>> 0),
    vertexAttribI4iv(i, u8, off) { gl.vertexAttribI4iv(i, i32view(u8, off, 4)); },
    vertexAttribI4uiv(i, u8, off) { gl.vertexAttribI4uiv(i, u32view(u8, off, 4)); },
    getVertexAttribIiv(i, pname, u8, off) { const v = gl.getVertexAttrib(i, pname); writeI32(u8, off, v && v.length ? Array.from(v) : [typeof v === 'object' ? idOf(v) : +v]); },
    getVertexAttribIuiv(i, pname, u8, off) { const v = gl.getVertexAttrib(i, pname); writeI32(u8, off, v && v.length ? Array.from(v) : [typeof v === 'object' ? idOf(v) : +v]); },
    // drawing
    drawArraysInstanced(mode, first, count, n) { if (ffActive()) ffPrepare(); clientAttribs(first, count); gl.drawArraysInstanced(mode, first, count, n); },
    drawElementsInstanced(mode, count, type, u8, off, n) {
      if (u8) {
        if (attribsClient()) clientAttribs(0, maxIndex(u8, off, count, type) + 1);
        gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, scratchIdx);
        gl.bufferData(gl.ELEMENT_ARRAY_BUFFER, u8view(u8, off, count * (TYPE_SIZE[type] || 2)), gl.STREAM_DRAW);
        gl.drawElementsInstanced(mode, count, type, 0, n);
        gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, get(elemBuf, 'buffer'));
      } else gl.drawElementsInstanced(mode, count, type, off, n);
    },
    // programs and uniforms
    getFragDataLocation: (p, name) => gl.getFragDataLocation(get(p, 'program'), name),
    getUniformuiv(p, l, u8, off) { const v = gl.getUniform(get(p, 'program'), locObjs[l]); writeI32(u8, off, typeof v === 'number' ? [v] : Array.from(v)); },
    uniformBlockIndex: (p, name) => gl.getUniformBlockIndex(get(p, 'program'), name),
    getUniformBlockIndex: (p, name) => gl.getUniformBlockIndex(get(p, 'program'), name),
    getActiveUniformBlockiv(p, idx, pname, u8, off) { writeI32(u8, off, asList(gl.getActiveUniformBlockParameter(get(p, 'program'), idx, pname))); },
    getActiveUniformBlockName: (p, idx) => gl.getActiveUniformBlockName(get(p, 'program'), idx),
    uniformBlockBinding: (p, idx, binding) => gl.uniformBlockBinding(get(p, 'program'), idx, binding),
    getActiveUniformsiv(p, n, iu8, ioff, pname, u8, off) { writeI32(u8, off, asList(gl.getActiveUniforms(get(p, 'program'), Array.from(i32view(iu8, ioff, n)), pname))); },
    getUniformIndices: (p, names) => Array.from(gl.getUniformIndices(get(p, 'program'), names.split('\0')) || [], (x) => (x == null ? -1 : x | 0)),
    transformFeedbackVaryings(p, names, mode) { gl.transformFeedbackVaryings(get(p, 'program'), names ? names.split('\0') : [], mode); },
    getTransformFeedbackVarying: (p, i) => gl.getTransformFeedbackVarying(get(p, 'program'), i),   // {size, type, name}
    programParameteri() {},   // PROGRAM_BINARY_RETRIEVABLE_HINT: a hint (no binary formats are offered)
    // samplers
    genSamplers: genIn('sampler', () => gl.createSampler()),
    deleteSamplers: delIn('sampler', (o) => gl.deleteSampler(o)),
    isSampler: (s) => is(s, 'sampler'),
    bindSampler: (unit, s) => gl.bindSampler(unit, s ? named(s, 'sampler', () => gl.createSampler()) : null),
    samplerParameteri: (s, p, v) => gl.samplerParameteri(get(s, 'sampler'), p, v),
    samplerParameterf: (s, p, v) => gl.samplerParameterf(get(s, 'sampler'), p, v),
    samplerParameteriv(s, p, u8, off) { gl.samplerParameteri(get(s, 'sampler'), p, i32view(u8, off, 1)[0]); },
    samplerParameterfv(s, p, u8, off) { gl.samplerParameterf(get(s, 'sampler'), p, f32view(u8, off, 1)[0]); },
    getSamplerParameteriv(s, p, u8, off) { writeI32(u8, off, [gl.getSamplerParameter(get(s, 'sampler'), p) | 0]); },
    getSamplerParameterfv(s, p, u8, off) { writeF32(u8, off, [+gl.getSamplerParameter(get(s, 'sampler'), p)]); },
    // transform feedback
    genTransformFeedbacks: genIn('tf', () => gl.createTransformFeedback()),
    deleteTransformFeedbacks: delIn('tf', (o) => gl.deleteTransformFeedback(o)),
    isTransformFeedback: (t) => is(t, 'tf'),
    bindTransformFeedback: (t, id) => gl.bindTransformFeedback(t, id ? named(id, 'tf', () => gl.createTransformFeedback()) : null),
    beginTransformFeedback: (m) => gl.beginTransformFeedback(m),
    endTransformFeedback: () => gl.endTransformFeedback(),
    pauseTransformFeedback: () => gl.pauseTransformFeedback(),
    resumeTransformFeedback: () => gl.resumeTransformFeedback(),
    // sync objects: names in their own space
    fenceSync: (cond, flags) => add(gl.fenceSync(cond, flags), 'sync'),
    isSync: (s) => is(s, 'sync'),
    deleteSync(s) { if (is(s, 'sync')) { gl.deleteSync(get(s, 'sync')); del(s, 'sync'); } },
    clientWaitSync: (s, flags, timeout) => gl.clientWaitSync(get(s, 'sync'), flags & 1, 0),
    waitSync: (s, flags, timeout) => gl.waitSync(get(s, 'sync'), 0, -1),
    getSynciv(s, pname, n, lu8, loff, u8, off) { const v = gl.getSyncParameter(get(s, 'sync'), pname); if (n > 0) writeI32(u8, off, [v | 0]); if (lu8) writeI32(lu8, loff, [n > 0 ? 1 : 0]); },
  });
  for (const n of [1, 2, 3, 4]) {
    G['uniform' + n + 'ui'] = (l, ...v) => gl['uniform' + n + 'ui'](locObjs[l] || null, ...v.map((x) => x >>> 0));
    G['uniform' + n + 'uiv'] = (l, count, u8, off) => gl['uniform' + n + 'uiv'](locObjs[l] || null, u32view(u8, off, n * count));
  }
  for (const [c, r] of [[2, 3], [2, 4], [3, 2], [3, 4], [4, 2], [4, 3]])
    G['uniformMatrix' + c + 'x' + r + 'fv'] = (l, count, tr, u8, off) => gl['uniformMatrix' + c + 'x' + r + 'fv'](locObjs[l] || null, !!tr, f32view(u8, off, c * r * count));

  // ================================================================ GLES 3.1 / 3.2 calls WebGL 2 can serve
  // glProgramUniform*: the program is made current for the call
  for (const k of Object.keys(G)) {
    const m = /^uniform(Matrix)?[1-4]/.exec(k);
    if (!m) continue;
    G['program' + k[0].toUpperCase() + k.slice(1)] = (p, ...args) => {
      const prog = get(p, 'program');
      if (p !== curProgram) gl.useProgram(prog);
      try { G[k](...args); } finally { if (p !== curProgram) gl.useProgram(get(curProgram, 'program')); }
    };
  }
  const indexed = gl.getExtension('OES_draw_buffers_indexed');
  const perBuffer = (name, global) => (buf, ...a) => {
    if (indexed) indexed[name + 'iOES'](buf, ...a);
    else if (buf === 0) global(...a);
    else warn('gl' + name[0].toUpperCase() + name.slice(1) + 'i on draw buffer ' + buf + ' (no OES_draw_buffers_indexed)');
  };
  const labels = new Map();
  Object.assign(G, {
    getBooleani_v(t, index, u8, off) { const a = asList(gl.getIndexedParameter(t, index)); for (let i = 0; i < a.length; i++) u8[off + i] = a[i] ? 1 : 0; },
    blendEquationi: perBuffer('blendEquation', (m) => gl.blendEquation(m)),
    blendEquationSeparatei: perBuffer('blendEquationSeparate', (a, b) => gl.blendEquationSeparate(a, b)),
    blendFunci: perBuffer('blendFunc', (s, d) => gl.blendFunc(s, d)),
    blendFuncSeparatei: perBuffer('blendFuncSeparate', (a, b, c, d) => gl.blendFuncSeparate(a, b, c, d)),
    colorMaski: perBuffer('colorMask', (r, g, b, a) => gl.colorMask(!!r, !!g, !!b, !!a)),
    enablei(cap, buf) { if (indexed) indexed.enableiOES(cap, buf); else if (buf === 0) gl.enable(cap); else warn('glEnablei (no OES_draw_buffers_indexed)'); },
    disablei(cap, buf) { if (indexed) indexed.disableiOES(cap, buf); else if (buf === 0) gl.disable(cap); else warn('glDisablei (no OES_draw_buffers_indexed)'); },
    isEnabledi: (cap, buf) => (indexed ? indexed.isEnablediOES(cap, buf) : buf === 0 ? gl.isEnabled(cap) : false),
    // debug output: the context generates no messages (no debug context); labels are kept
    debugMessageControl() {},
    debugMessageInsert() {},
    pushDebugGroup() {},
    popDebugGroup() {},
    getDebugMessageLog: () => 0,
    objectLabel(identifier, name, length, label) { labels.set(identifier + ':' + name, length >= 0 && label ? label.slice(0, length) : label); },
    getObjectLabel: (identifier, name) => labels.get(identifier + ':' + name) || '',
    objectPtrLabel(ptr, length, label) { labels.set('ptr:' + ptr, length >= 0 && label ? label.slice(0, length) : label); },
    getObjectPtrLabel: (ptr) => labels.get('ptr:' + ptr) || '',
    getGraphicsResetStatus: () => (gl.isContextLost() ? 0x8255 : 0),   // UNKNOWN_CONTEXT_RESET
    readnPixels(x, y, w, h, fmt, type, bufSize, u8, off) {
      if (texBytes(w, h, fmt, type, packAlign) > bufSize) { setErr(0x502); return; }
      G.readPixels(x, y, w, h, fmt, type, u8, off);
    },
    getnUniformfv(p, l, bufSize, u8, off) { G.getUniformfv(p, l, u8, off); },
    getnUniformiv(p, l, bufSize, u8, off) { G.getUniformiv(p, l, u8, off); },
    getnUniformuiv(p, l, bufSize, u8, off) { G.getUniformuiv(p, l, u8, off); },
    primitiveBoundingBox() {},   // a hint for tiling GPUs
    memoryBarrier() {},          // nothing writes through images or storage buffers on WebGL 2
    memoryBarrierByRegion() {},
  });
  // the extension names of the same calls (GLES31Ext: KHR_debug, EXT_draw_buffers_indexed, EXT_primitive_bounding_box)
  for (const k of ['debugMessageControl', 'debugMessageInsert', 'pushDebugGroup', 'popDebugGroup', 'objectLabel', 'getObjectLabel', 'objectPtrLabel',
    'getObjectPtrLabel', 'getDebugMessageLog', 'getGraphicsResetStatus', 'readnPixels', 'getnUniformfv', 'getnUniformiv']) G[k + 'KHR'] = G[k];
  for (const k of ['enablei', 'disablei', 'isEnabledi', 'blendEquationi', 'blendEquationSeparatei', 'blendFunci', 'blendFuncSeparatei', 'colorMaski',
    'primitiveBoundingBox']) G[k + 'EXT'] = G[k];

  // ================================================================ GLES 1.1: fixed function, emulated
  let ffUsed = false;
  const I4 = () => new Float32Array([1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1]);
  const mul = (a, b) => { const o = new Float32Array(16); for (let c = 0; c < 4; c++) for (let r = 0; r < 4; r++) { let s = 0; for (let k = 0; k < 4; k++) s += a[k * 4 + r] * b[c * 4 + k]; o[c * 4 + r] = s; } return o; };
  const ff = {
    mode: 0x1700, mv: [I4()], proj: [I4()], tex: [[I4()], [I4()]], unit: 0, cunit: 0,
    color: [1, 1, 1, 1], normal: [0, 0, 1], texcoord: [[0, 0, 0, 1], [0, 0, 0, 1]],
    caps: {}, arrays: { v: null, c: null, n: null, t0: null, t1: null, p: null },
    alphaFunc: 0x0207, alphaRef: 0, texEnv: [0x2100, 0x2100], envColor: [[0, 0, 0, 0], [0, 0, 0, 0]],
    fog: { mode: 0x0800, density: 1, start: 0, end: 1, color: [0, 0, 0, 0] },
    lights: Array.from({ length: 8 }, (_, i) => ({ amb: [0, 0, 0, 1], dif: i ? [0, 0, 0, 1] : [1, 1, 1, 1], spec: i ? [0, 0, 0, 1] : [1, 1, 1, 1], pos: [0, 0, 1, 0], att: [1, 0, 0] })),
    mat: { amb: [0.2, 0.2, 0.2, 1], dif: [0.8, 0.8, 0.8, 1], spec: [0, 0, 0, 1], emi: [0, 0, 0, 1], shin: 0 }, lightAmb: [0.2, 0.2, 0.2, 1],
    pointSize: 1, crop: [0, 0, 0, 0], progs: new Map(), active: false,
  };
  // capabilities the fixed-function emulation handles itself (WebGL doesn't know them)
  const fixedCaps = new Set([0x0DE1, 0x0BC0, 0x0B60, 0x0B50, 0x4000, 0x4001, 0x4002, 0x4003, 0x4004, 0x4005, 0x4006, 0x4007, 0x0B57, 0x0BA1, 0x803A,
    0x8861, 0x0B10, 0x0B20, 0x809D, 0x0BF2, 0x3000, 0x3001, 0x3002, 0x3003, 0x3004, 0x3005]);
  const stack = () => (ff.mode === 0x1700 ? ff.mv : ff.mode === 0x1701 ? ff.proj : ff.tex[ff.unit]);
  const top = () => { const s = stack(); return s[s.length - 1]; };
  const setTop = (m) => { const s = stack(); s[s.length - 1] = m; };
  const ffActive = () => ff.active && curProgram === 0;
  // vertex attribute slots the emulation uses
  const A = { v: 0, c: 1, n: 2, t0: 3, t1: 4, p: 5 };
  function ffProgram(key) {
    let p = ff.progs.get(key);
    if (p) return p;
    const [tex0, tex1, light, nlights, alphaTest, fog, pointSprite] = key.split(',').map(Number);
    const vs = `#version 300 es
in vec4 aV; in vec4 aC; in vec3 aN; in vec4 aT0; in vec4 aT1; in float aP;
uniform mat4 uMV, uP, uT0, uT1; uniform float uPS;
uniform vec4 uMatAmb, uMatDif, uMatSpec, uMatEmi, uLightAmb; uniform float uShin;
uniform vec4 uLAmb[8], uLDif[8], uLSpec[8], uLPos[8]; uniform vec3 uLAtt[8];
out vec4 vC; out vec4 vT0; out vec4 vT1; out float vFog;
void main() {
  vec4 e = uMV * aV;
  gl_Position = uP * e;
  gl_PointSize = aP > 0.0 ? aP : uPS;
  vT0 = uT0 * aT0; vT1 = uT1 * aT1;
  vFog = length(e.xyz);
  ${light ? `vec3 n = normalize(mat3(uMV) * aN);
  vec4 c = uMatEmi + uMatAmb * uLightAmb;
  for (int i = 0; i < ${nlights}; i++) {
    vec3 L = uLPos[i].w == 0.0 ? normalize(uLPos[i].xyz) : normalize(uLPos[i].xyz - e.xyz);
    float d = uLPos[i].w == 0.0 ? 0.0 : length(uLPos[i].xyz - e.xyz);
    float att = 1.0 / (uLAtt[i].x + uLAtt[i].y * d + uLAtt[i].z * d * d);
    float nl = max(dot(n, L), 0.0);
    vec3 H = normalize(L + vec3(0.0, 0.0, 1.0));
    float sp = nl > 0.0 ? pow(max(dot(n, H), 0.0), max(uShin, 0.0001)) : 0.0;
    c += att * (uMatAmb * uLAmb[i] + nl * uMatDif * uLDif[i] + sp * uMatSpec * uLSpec[i]);
  }
  vC = vec4(c.rgb, uMatDif.a);` : 'vC = aC;'}
}`;
    const env = (i) => `{ vec4 t = texture(uS${i}, ${pointSprite ? 'gl_PointCoord' : `vT${i}.xy / (vT${i}.w == 0.0 ? 1.0 : vT${i}.w)`});
  int m = uEnv${i};
  if (uFmt${i} == 1) t = vec4(1.0, 1.0, 1.0, t.a);          // GL_ALPHA texture
  else if (uFmt${i} == 2) t = vec4(t.rgb, 1.0);            // no alpha
  if (m == 0x1E01) c = t;                                   // REPLACE
  else if (m == 0x2101) c = vec4(mix(c.rgb, t.rgb, t.a), c.a);   // DECAL
  else if (m == 0x0104) c = vec4(c.rgb + t.rgb, c.a * t.a);      // ADD
  else if (m == 0x0BE2) c = vec4(mix(c.rgb, uEnvC${i}.rgb, t.rgb), c.a * t.a);  // BLEND
  else c *= t; }`;
    const fs = `#version 300 es
precision mediump float;
in vec4 vC; in vec4 vT0; in vec4 vT1; in float vFog;
uniform sampler2D uS0, uS1; uniform int uEnv0, uEnv1, uFmt0, uFmt1; uniform vec4 uEnvC0, uEnvC1;
uniform int uAlphaFunc; uniform float uAlphaRef;
uniform int uFogMode; uniform float uFogDensity, uFogStart, uFogEnd; uniform vec4 uFogColor;
out vec4 o;
void main() {
  vec4 c = vC;
  ${tex0 ? env(0) : ''}
  ${tex1 ? env(1) : ''}
  ${alphaTest ? `float a = c.a, r = uAlphaRef; bool pass = uAlphaFunc == 0x0207 || (uAlphaFunc == 0x0201 && a < r) || (uAlphaFunc == 0x0202 && a == r) ||
    (uAlphaFunc == 0x0203 && a <= r) || (uAlphaFunc == 0x0204 && a > r) || (uAlphaFunc == 0x0205 && a != r) || (uAlphaFunc == 0x0206 && a >= r);
  if (!pass) discard;` : ''}
  ${fog ? `float f = uFogMode == 0x2601 ? (uFogEnd - vFog) / (uFogEnd - uFogStart) : uFogMode == 0x0800 ? exp(-uFogDensity * vFog) : exp(-pow(uFogDensity * vFog, 2.0));
  c.rgb = mix(uFogColor.rgb, c.rgb, clamp(f, 0.0, 1.0));` : ''}
  o = c;
}`;
    const sh = (t, src) => { const s = gl.createShader(t); gl.shaderSource(s, src); gl.compileShader(s); if (!gl.getShaderParameter(s, gl.COMPILE_STATUS)) log('E/GLES: fixed-function shader: ' + gl.getShaderInfoLog(s)); return s; };
    const prog = gl.createProgram();
    gl.attachShader(prog, sh(gl.VERTEX_SHADER, vs));
    gl.attachShader(prog, sh(gl.FRAGMENT_SHADER, fs));
    for (const [n, i] of Object.entries({ aV: A.v, aC: A.c, aN: A.n, aT0: A.t0, aT1: A.t1, aP: A.p })) gl.bindAttribLocation(prog, i, n);
    gl.linkProgram(prog);
    if (!gl.getProgramParameter(prog, gl.LINK_STATUS)) log('E/GLES: fixed-function program: ' + gl.getProgramInfoLog(prog));
    const u = {};
    const n = gl.getProgramParameter(prog, gl.ACTIVE_UNIFORMS);
    for (let i = 0; i < n; i++) { const a = gl.getActiveUniform(prog, i); u[a.name.replace('[0]', '')] = gl.getUniformLocation(prog, a.name); }
    p = { prog, u };
    ff.progs.set(key, p);
    return p;
  }
  const texFormats = new Map();   // texture object -> 0 rgba, 1 alpha only, 2 rgb
  function boundTexFormat(unit) {
    const t = gl.getParameter(gl.TEXTURE_BINDING_2D);
    return texFormats.get(t) || 0;
  }
  function ffPrepare() {
    const tex0 = !!ff.caps.tex0, tex1 = !!ff.caps.tex1;
    if (ff.caps[0x0B57]) { ff.mat.amb = ff.color; ff.mat.dif = ff.color; }   // COLOR_MATERIAL
    const light = !!ff.caps[0x0B50];
    let nl = 0;
    if (light) for (let i = 0; i < 8; i++) if (ff.caps[0x4000 + i]) nl = i + 1;
    const key = [tex0 ? 1 : 0, tex1 ? 1 : 0, light ? 1 : 0, nl, ff.caps[0x0BC0] ? 1 : 0, ff.caps[0x0B60] ? 1 : 0, ff.caps[0x8861] && ff.pointSpriteReplace ? 1 : 0].join(',');
    const { prog, u } = ffProgram(key);
    gl.useProgram(prog);
    const m = (n, v) => u[n] && gl.uniformMatrix4fv(u[n], false, v);
    m('uMV', ff.mv[ff.mv.length - 1]); m('uP', ff.proj[ff.proj.length - 1]);
    m('uT0', ff.tex[0][ff.tex[0].length - 1]); m('uT1', ff.tex[1][ff.tex[1].length - 1]);
    if (u.uPS) gl.uniform1f(u.uPS, ff.pointSize);
    const prevUnit = activeTex;
    for (let i = 0; i < 2; i++) {
      if (u['uS' + i]) gl.uniform1i(u['uS' + i], i);
      if (u['uEnv' + i]) gl.uniform1i(u['uEnv' + i], ff.texEnv[i]);
      if (u['uEnvC' + i]) gl.uniform4fv(u['uEnvC' + i], ff.envColor[i]);
      if (u['uFmt' + i]) { gl.activeTexture(gl.TEXTURE0 + i); gl.uniform1i(u['uFmt' + i], boundTexFormat(i)); }
    }
    gl.activeTexture(gl.TEXTURE0 + prevUnit);
    if (u.uAlphaFunc) { gl.uniform1i(u.uAlphaFunc, ff.alphaFunc); gl.uniform1f(u.uAlphaRef, ff.alphaRef); }
    if (u.uFogMode) { gl.uniform1i(u.uFogMode, ff.fog.mode); gl.uniform1f(u.uFogDensity, ff.fog.density); gl.uniform1f(u.uFogStart, ff.fog.start); gl.uniform1f(u.uFogEnd, ff.fog.end); gl.uniform4fv(u.uFogColor, ff.fog.color); }
    if (light) {
      const mt = ff.mat;
      gl.uniform4fv(u.uMatAmb, mt.amb); gl.uniform4fv(u.uMatDif, mt.dif); gl.uniform4fv(u.uMatSpec, mt.spec); gl.uniform4fv(u.uMatEmi, mt.emi);
      gl.uniform1f(u.uShin, mt.shin); gl.uniform4fv(u.uLightAmb, ff.lightAmb);
      const cat = (k) => new Float32Array(ff.lights.flatMap((l) => l[k]));
      gl.uniform4fv(u.uLAmb, cat('amb')); gl.uniform4fv(u.uLDif, cat('dif')); gl.uniform4fv(u.uLSpec, cat('spec')); gl.uniform4fv(u.uLPos, cat('pos')); gl.uniform3fv(u.uLAtt, cat('att'));
    }
    // attributes: arrays, or the current values
    const set = (slot, arr, cur) => {
      const a = attribs[slot];
      if (arr && arr.enabled) {
        a.enabled = true;
        Object.assign(a, { size: arr.size, type: arr.type, norm: arr.type !== 0x1406 && slot === A.c, stride: arr.stride, client: arr.buf ? null : arr.u8, off: arr.off });
        gl.enableVertexAttribArray(slot);
        if (arr.buf) { gl.bindBuffer(gl.ARRAY_BUFFER, get(arr.buf, 'buffer')); gl.vertexAttribPointer(slot, arr.size, arr.type, a.norm, arr.stride, arr.off); gl.bindBuffer(gl.ARRAY_BUFFER, get(arrayBuf, 'buffer')); }
      } else { a.enabled = false; a.client = null; gl.disableVertexAttribArray(slot); if (cur) gl.vertexAttrib4f(slot, ...cur); }
    };
    set(A.v, ff.arrays.v, [0, 0, 0, 1]);
    set(A.c, ff.arrays.c, ff.color);
    set(A.n, ff.arrays.n, [...ff.normal, 0]);
    set(A.t0, ff.arrays.t0, ff.texcoord[0]);
    set(A.t1, ff.arrays.t1, ff.texcoord[1]);
    set(A.p, ff.arrays.p, [0, 0, 0, 0]);
  }
  const touch = () => { ff.active = true; ffUsed = true; };
  const ffArray = (k) => (size, type, stride, u8, off) => {
    touch();
    ff.arrays[k] = Object.assign(ff.arrays[k] || { enabled: false }, { size, type, stride, u8: arrayBuf ? null : u8, off, buf: arrayBuf });
  };
  const arrayKey = { 0x8074: 'v', 0x8076: 'c', 0x8075: 'n', 0x8078: 't', 0x8B9C: 'p' };
  Object.assign(G, {
    matrixMode(m) { touch(); ff.mode = m; },
    loadIdentity() { touch(); setTop(I4()); },
    loadMatrixf(u8, off) { touch(); setTop(f32view(u8, off, 16)); },
    loadMatrixx(u8, off) { touch(); setTop(Float32Array.from(i32view(u8, off, 16), (x) => x / 65536)); },
    multMatrixf(u8, off) { touch(); setTop(mul(top(), f32view(u8, off, 16))); },
    multMatrixx(u8, off) { touch(); setTop(mul(top(), Float32Array.from(i32view(u8, off, 16), (x) => x / 65536))); },
    pushMatrix() { touch(); const s = stack(); s.push(new Float32Array(s[s.length - 1])); },
    popMatrix() { const s = stack(); if (s.length > 1) s.pop(); else setErr(0x504); },
    translatef(x, y, z) { touch(); setTop(mul(top(), new Float32Array([1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, x, y, z, 1]))); },
    scalef(x, y, z) { touch(); setTop(mul(top(), new Float32Array([x, 0, 0, 0, 0, y, 0, 0, 0, 0, z, 0, 0, 0, 0, 1]))); },
    rotatef(a, x, y, z) {
      touch();
      const l = Math.hypot(x, y, z) || 1, c = Math.cos((a * Math.PI) / 180), s = Math.sin((a * Math.PI) / 180), t = 1 - c;
      x /= l; y /= l; z /= l;
      setTop(mul(top(), new Float32Array([t * x * x + c, t * x * y + s * z, t * x * z - s * y, 0, t * x * y - s * z, t * y * y + c, t * y * z + s * x, 0, t * x * z + s * y, t * y * z - s * x, t * z * z + c, 0, 0, 0, 0, 1])));
    },
    orthof(l, r, b, t, n, f) { touch(); setTop(mul(top(), new Float32Array([2 / (r - l), 0, 0, 0, 0, 2 / (t - b), 0, 0, 0, 0, -2 / (f - n), 0, -(r + l) / (r - l), -(t + b) / (t - b), -(f + n) / (f - n), 1]))); },
    frustumf(l, r, b, t, n, f) { touch(); setTop(mul(top(), new Float32Array([(2 * n) / (r - l), 0, 0, 0, 0, (2 * n) / (t - b), 0, 0, (r + l) / (r - l), (t + b) / (t - b), -(f + n) / (f - n), -1, 0, 0, (-2 * f * n) / (f - n), 0]))); },
    color4f(r, g, b, a) { touch(); ff.color = [r, g, b, a]; },
    color4ub(r, g, b, a) { touch(); ff.color = [r / 255, g / 255, b / 255, a / 255]; },
    color4x(r, g, b, a) { touch(); ff.color = [r / 65536, g / 65536, b / 65536, a / 65536]; },
    normal3f(x, y, z) { ff.normal = [x, y, z]; },
    multiTexCoord4f(t, s, tt, r, q) { ff.texcoord[(t - 0x84C0) & 1] = [s, tt, r, q]; },
    clientActiveTexture(t) { ff.cunit = (t - 0x84C0) & 1; },
    vertexPointer: ffArray('v'),
    colorPointer: ffArray('c'),
    normalPointer: (type, stride, u8, off) => ffArray('n')(3, type, stride, u8, off),
    texCoordPointer: (size, type, stride, u8, off) => ffArray(ff.cunit ? 't1' : 't0')(size, type, stride, u8, off),
    pointSizePointerOES: (type, stride, u8, off) => ffArray('p')(1, type, stride, u8, off),
    enableClientState(a) { touch(); let k = arrayKey[a]; if (k === 't') k = ff.cunit ? 't1' : 't0'; if (k) { ff.arrays[k] = ff.arrays[k] || {}; ff.arrays[k].enabled = true; } },
    disableClientState(a) { let k = arrayKey[a]; if (k === 't') k = ff.cunit ? 't1' : 't0'; if (k && ff.arrays[k]) ff.arrays[k].enabled = false; },
    alphaFunc(f, r) { ff.alphaFunc = f; ff.alphaRef = r; },
    alphaFuncx(f, r) { ff.alphaFunc = f; ff.alphaRef = r / 65536; },
    texEnvf(t, p, v) { if (p === 0x2200) ff.texEnv[activeTex & 1] = v | 0; else if (t === 0x8861 && p === 0x8862) ff.pointSpriteReplace = !!v; },
    texEnvi(t, p, v) { G.texEnvf(t, p, v); },
    texEnvx(t, p, v) { G.texEnvf(t, p, v); },
    texEnvfv(t, p, u8, off) { if (p === 0x2201) ff.envColor[activeTex & 1] = Array.from(f32view(u8, off, 4)); else G.texEnvf(t, p, f32view(u8, off, 1)[0]); },
    texEnviv(t, p, u8, off) { G.texEnvf(t, p, i32view(u8, off, 1)[0]); },
    shadeModel() {},
    pointSize(s) { ff.pointSize = s; },
    fogf(p, v) { if (p === 0x0B65) ff.fog.mode = v | 0; else if (p === 0x0B62) ff.fog.density = v; else if (p === 0x0B63) ff.fog.start = v; else if (p === 0x0B64) ff.fog.end = v; },
    fogfv(p, u8, off) { if (p === 0x0B66) ff.fog.color = Array.from(f32view(u8, off, 4)); else G.fogf(p, f32view(u8, off, 1)[0]); },
    fogx(p, v) { G.fogf(p, p === 0x0B65 ? v : v / 65536); },
    lightf(l, p, v) { const L = ff.lights[l - 0x4000]; if (!L) return; if (p === 0x1207) L.att[0] = v; else if (p === 0x1208) L.att[1] = v; else if (p === 0x1209) L.att[2] = v; },
    lightfv(l, p, u8, off) {
      const L = ff.lights[l - 0x4000];
      if (!L) return;
      const v = Array.from(f32view(u8, off, 4));
      if (p === 0x1200) L.amb = v; else if (p === 0x1201) L.dif = v; else if (p === 0x1202) L.spec = v;
      else if (p === 0x1203) { const m = ff.mv[ff.mv.length - 1]; L.pos = [0, 1, 2, 3].map((r) => m[r] * v[0] + m[4 + r] * v[1] + m[8 + r] * v[2] + m[12 + r] * v[3]); }
      else G.lightf(l, p, v[0]);
    },
    lightModelfv(p, u8, off) { if (p === 0x0B53) ff.lightAmb = Array.from(f32view(u8, off, 4)); },
    lightModelf() {},
    materialf(f, p, v) { if (p === 0x1601) ff.mat.shin = v; },
    materialfv(f, p, u8, off) {
      const v = Array.from(f32view(u8, off, 4));
      if (p === 0x1200) ff.mat.amb = v; else if (p === 0x1201) ff.mat.dif = v; else if (p === 0x1202) ff.mat.spec = v;
      else if (p === 0x1600) ff.mat.emi = v; else if (p === 0x1602) { ff.mat.amb = v; ff.mat.dif = v; } else if (p === 0x1601) ff.mat.shin = v[0];
    },
    // OES_draw_texture: the crop rectangle of the bound texture drawn in window coordinates
    drawTexfOES(x, y, z, w, h) {
      touch();
      const save = { mv: ff.mv, proj: ff.proj, t0: ff.tex[0], arr: ff.arrays, light: ff.caps[0x0B50] };
      const vp = gl.getParameter(gl.VIEWPORT);
      const tw = texSize.get(gl.getParameter(gl.TEXTURE_BINDING_2D)) || [1, 1];
      const [cu, cv, cw, ch] = ff.crop;
      const u0 = cu / tw[0], v0 = cv / tw[1], u1 = (cu + cw) / tw[0], v1 = (cv + ch) / tw[1];
      const zz = z <= 0 ? -1 : z >= 1 ? 1 : 2 * z - 1;
      const verts = new Float32Array([x, y, zz, u0, v0, x + w, y, zz, u1, v0, x, y + h, zz, u0, v1, x + w, y + h, zz, u1, v1]);
      ff.mv = [I4()];
      ff.proj = [new Float32Array([2 / vp[2], 0, 0, 0, 0, 2 / vp[3], 0, 0, 0, 0, 1, 0, -1 - (2 * vp[0]) / vp[2], -1 - (2 * vp[1]) / vp[3], 0, 1])];
      ff.tex[0] = [I4()];
      const u = new Uint8Array(verts.buffer);
      const saveBuf = arrayBuf; arrayBuf = 0;
      ff.arrays = { v: { enabled: true, size: 3, type: 0x1406, stride: 20, u8: u, off: 0 }, t0: { enabled: true, size: 2, type: 0x1406, stride: 20, u8: u, off: 12 } };
      ff.caps[0x0B50] = false;
      G.drawArrays(5, 0, 4);
      arrayBuf = saveBuf;
      ff.mv = save.mv; ff.proj = save.proj; ff.tex[0] = save.t0; ff.arrays = save.arr; ff.caps[0x0B50] = save.light;
    },
    drawTexiOES(x, y, z, w, h) { G.drawTexfOES(x, y, z, w, h); },
    drawTexsOES(x, y, z, w, h) { G.drawTexfOES(x, y, z, w, h); },
    drawTexxOES(x, y, z, w, h) { G.drawTexfOES(x / 65536, y / 65536, z / 65536, w / 65536, h / 65536); },
    clipPlanef() { warn('glClipPlane'); },
    logicOp() { warn('glLogicOp'); },
  });
  // texture units, crop rectangles and formats the emulation needs to know
  const texSize = new Map();
  const tex2D = G.texImage2D, texSrc = G.texImageSource, texParamiv = G.texParameteriv, enable = G.enable, disable = G.disable, isEnabled = G.isEnabled, active = G.activeTexture;
  G.texImage2D = (t, level, ifmt, w, h, b, fmt, type, u8, off) => {
    tex2D(t, level, ifmt, w, h, b, fmt, type, u8, off);
    const o = gl.getParameter(gl.TEXTURE_BINDING_2D);
    if (level === 0 && o) { texSize.set(o, [w, h]); texFormats.set(o, fmt === gl.ALPHA ? 1 : fmt === gl.RGB || fmt === gl.LUMINANCE ? 2 : 0); }
    if (genMipmapOn.has(t)) gl.generateMipmap(t);
  };
  G.texImageSource = (t, level, ifmt, type, img, pre) => {
    texSrc(t, level, ifmt, type, img, pre);
    const o = gl.getParameter(gl.TEXTURE_BINDING_2D);
    if (level === 0 && o) { texSize.set(o, [img.width, img.height]); texFormats.set(o, ifmt === gl.ALPHA ? 1 : ifmt === gl.RGB ? 2 : 0); }
    if (genMipmapOn.has(t)) gl.generateMipmap(t);
  };
  G.texParameteriv = (t, p, u8, off) => { if (p === 0x8B9D) ff.crop = Array.from(i32view(u8, off, 4)); else texParamiv(t, p, u8, off); };
  G.texParameterfv = (t, p, u8, off) => { if (p === 0x8B9D) ff.crop = Array.from(f32view(u8, off, 4)); else G.texParameterf(t, p, f32view(u8, off, 1)[0]); };
  G.activeTexture = (t) => { active(t); ff.unit = (t - 0x84C0) & 1; };
  G.enable = (c) => { if (c === 0x0DE1) { ff.caps[0x0DE1] = true; ff.caps['tex' + ff.unit] = true; } else enable(c); };
  G.disable = (c) => { if (c === 0x0DE1) ff.caps['tex' + ff.unit] = false; else disable(c); };
  G.isEnabled = (c) => (c === 0x0DE1 ? !!ff.caps['tex' + ff.unit] : isEnabled(c));
  const useProg = G.useProgram;
  G.useProgram = (p) => { useProg(p); };
  const drawA = G.drawArrays, drawE = G.drawElements;
  G.drawArrays = (mode, first, count) => {
    if (ffActive()) { ffPrepare(); clientAttribs(first, count); gl.drawArrays(mode, first, count); gl.useProgram(get(curProgram, 'program')); return; }
    drawA(mode, first, count);
  };
  G.drawElements = (mode, count, type, u8, off) => {
    if (ffActive()) { ffPrepare(); ff.active = false; try { drawE(mode, count, type, u8, off); } finally { ff.active = true; gl.useProgram(get(curProgram, 'program')); } return; }
    drawE(mode, count, type, u8, off);
  };
  // fixed-point (GLES 1.1 "x") variants
  const X = (v) => v / 65536;
  Object.assign(G, {
    clearColorx: (r, g, b, a) => gl.clearColor(X(r), X(g), X(b), X(a)), clearDepthx: (d) => gl.clearDepth(X(d)), depthRangex: (n, f) => gl.depthRange(X(n), X(f)),
    lineWidthx: (w) => gl.lineWidth(X(w)), pointSizex: (s) => { ff.pointSize = X(s); }, polygonOffsetx: (f, u) => gl.polygonOffset(X(f), X(u)),
    translatex: (x, y, z) => G.translatef(X(x), X(y), X(z)), scalex: (x, y, z) => G.scalef(X(x), X(y), X(z)), rotatex: (a, x, y, z) => G.rotatef(X(a), X(x), X(y), X(z)),
    orthox: (l, r, b, t, n, f) => G.orthof(X(l), X(r), X(b), X(t), X(n), X(f)), frustumx: (l, r, b, t, n, f) => G.frustumf(X(l), X(r), X(b), X(t), X(n), X(f)),
    normal3x: (x, y, z) => G.normal3f(X(x), X(y), X(z)), multiTexCoord4x: (t, s, tt, r, q) => G.multiTexCoord4f(t, X(s), X(tt), X(r), X(q)),
    texParameterx: (t, p, v) => G.texParameteri(t, p, v), sampleCoveragex: (v, i) => gl.sampleCoverage(X(v), !!i),
    lightx: (l, p, v) => G.lightf(l, p, X(v)), materialx: (f, p, v) => G.materialf(f, p, X(v)),
  });
  return G;
}
