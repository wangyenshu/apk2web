#!/usr/bin/env python3
"""Generate the OpenGL ES bindings from android.jar's own declarations (run when the GL layer changes):

  - runtime/framework/android/opengl/GLES10, GLES11, GLES10Ext, GLES11Ext, GLES20 (static methods -> HW.GLES)
  - runtime/framework/javax/microedition/khronos/opengles/GL*, the interfaces a GLSurfaceView renderer gets, with
    default methods that call the static ones
  - native/gl_table.h + native/library_gltable.js: the emulator's GL imports (name, argument kinds)

Every pointer argument becomes a (bytes, offset) pair for runtime/site/gles.js: from Java a copy of the array or
buffer (copied back for glGen*/glGet*/glRead*), from native code the emulator heap and the guest pointer.

usage: gen_gl.py android.jar
"""
import os, re, subprocess, sys

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
AJ = sys.argv[1]
CLASSES = ['GLES10', 'GLES11', 'GLES10Ext', 'GLES11Ext', 'GLES20', 'GLES30', 'GLES31', 'GLES31Ext', 'GLES32']
# the emulator's GL imports (native code): GLES 1 and 2 only (native/gl_table.h is compiled into the prebuilt emulator)
NATIVE_CLASSES = ['GLES10', 'GLES11', 'GLES10Ext', 'GLES11Ext', 'GLES20']
EXTENDS = {'GLES11': 'GLES10', 'GLES30': 'GLES20', 'GLES31': 'GLES30', 'GLES32': 'GLES31'}
IFACES = ['GL', 'GL10', 'GL10Ext', 'GL11', 'GL11Ext', 'GL11ExtensionPack']
CORE = open(os.path.join(ROOT, 'runtime', 'site', 'gles.js')).read()


def javap(cls):
    out = subprocess.run(['javap', '-public', '-constants', '-cp', AJ, cls], capture_output=True, text=True, check=True).stdout
    consts, methods = [], []
    for line in out.splitlines():
        line = line.strip()
        m = re.match(r'public static final (int|long|float|java\.lang\.String) (\w+) = (.+);', line)
        if m: consts.append(m.groups()); continue
        m = re.match(r'public (?:static |abstract )*(?:native )?([\w.\[\]]+) (gl\w+)\(([^)]*)\);', line)
        if m: methods.append((m.group(1).replace('$', '.'), m.group(2), [p.strip().replace('$', '.') for p in m.group(3).split(',') if p.strip()]))
    return consts, methods


# nested callback interfaces of the GLES classes
NESTED = {
    'GLES31Ext': ['  /** a KHR_debug message callback (the page generates no debug messages) */',
                  '  public interface DebugProcKHR { void onMessage(int source, int type, int id, int severity, String message); }'],
    'GLES32': ['  /** a debug message callback (the page generates no debug messages) */',
               '  public interface DebugProc { void onMessage(int source, int type, int id, int severity, String message); }'],
}


KHR_ALIASES = {'debugMessageControl', 'debugMessageInsert', 'pushDebugGroup', 'popDebugGroup', 'objectLabel', 'getObjectLabel', 'objectPtrLabel',
               'getObjectPtrLabel', 'getDebugMessageLog', 'getGraphicsResetStatus', 'readnPixels', 'getnUniformfv', 'getnUniformiv'}
EXT_ALIASES = {'enablei', 'disablei', 'isEnabledi', 'blendEquationi', 'blendEquationSeparatei', 'blendFunci', 'blendFuncSeparatei', 'colorMaski',
               'primitiveBoundingBox'}


def core_name(n):
    return n[2].lower() + n[3:]


def core_has(name):
    # a key in the core object, or assigned as G.name / G['name' + ...] patterns for the generated families
    if re.search(r'\b' + re.escape(name) + r'\s*[(:]', CORE) or re.search(r"G\." + re.escape(name) + r"\b", CORE): return True
    m = re.match(r'(uniform|vertexAttrib|programUniform)([1-4])(f|i|ui)(v?)$', name) or re.match(r'(programUniform|uniform)Matrix([2-4])(x[2-4])?fv$', name)
    if m: return True
    # extension names gles.js aliases to the core calls (GLES31Ext)
    if name.endswith('KHR') and name[:-3] in KHR_ALIASES or name.endswith('EXT') and name[:-3] in EXT_ALIASES: return True
    for k in ('genVertexArrays', 'bindVertexArray', 'deleteVertexArrays', 'isVertexArray'):
        if name.startswith(k): return True
    return False


SIMPLE = {'int': 'int', 'float': 'float', 'boolean': 'boolean', 'byte': 'int', 'short': 'int', 'char': 'int', 'long': 'int', 'double': 'float'}
BUFFERS = ('java.nio.Buffer', 'java.nio.IntBuffer', 'java.nio.FloatBuffer', 'java.nio.ShortBuffer', 'java.nio.ByteBuffer',
           'java.nio.LongBuffer', 'java.nio.CharBuffer', 'java.nio.DoubleBuffer')
OUTPUT = re.compile(r'^gl(Gen|Get|Read|QueryMatrix)')
CLIENT_POINTERS = {'glVertexAttribPointer', 'glVertexPointer', 'glColorPointer', 'glNormalPointer', 'glTexCoordPointer',
                   'glPointSizePointerOES', 'glMatrixIndexPointerOES', 'glWeightPointerOES'}
DRAWS = {'glDrawArrays', 'glDrawElements'}


def shape(params):
    """Java params -> list of kinds: i f b s P(array: takes 2 Java params) B(buffer) or None if not bindable"""
    kinds, i = [], 0
    while i < len(params):
        p = params[i]
        if p == 'long': kinds.append('l'); i += 1
        elif p in SIMPLE: kinds.append(SIMPLE[p][0]); i += 1
        elif p == 'java.lang.String': kinds.append('s'); i += 1
        elif p.endswith('[]') and i + 1 < len(params) and params[i + 1] == 'int' and p[:-2] in SIMPLE: kinds.append('P'); i += 2
        elif p in BUFFERS: kinds.append('B'); i += 1
        else: return None
    return kinds


# Java overloads whose shape differs from the core function: hand-written in GLBridge
JAVA_OVERRIDE = {
    ('glGetActiveAttrib', 11): 'GLBridge.activeInfoC(false, %s);', ('glGetActiveUniform', 11): 'GLBridge.activeInfoC(true, %s);',
    ('glGetActiveAttrib', 6): 'return GLBridge.activeInfo(false, %s);', ('glGetActiveUniform', 6): 'return GLBridge.activeInfo(true, %s);',
    ('glGetActiveAttrib', 4): 'return GLBridge.activeInfo(false, %s);', ('glGetActiveUniform', 4): 'return GLBridge.activeInfo(true, %s);',
    ('glGetShaderSource', 6): 'GLBridge.shaderSourceC(%s);',
    # GLES 3.0: mapped buffers (a copy, written back on unmap or flush), string arrays, names returned as strings
    ('glMapBufferRange', 4): 'return GLBridge.mapBufferRange(%s);', ('glUnmapBuffer', 1): 'return GLBridge.unmapBuffer(%s);',
    ('glFlushMappedBufferRange', 3): 'GLBridge.flushMappedBufferRange(%s);', ('glGetBufferPointerv', 2): 'return GLBridge.bufferPointer(%s);',
    ('glTransformFeedbackVaryings', 3): 'GLBridge.transformFeedbackVaryings(%s);',
    ('glGetUniformIndices', 4): 'GLBridge.uniformIndices(%s);', ('glGetUniformIndices', 3): 'GLBridge.uniformIndices(%s);',
    ('glGetActiveUniformBlockName', 2): 'return GLBridge.uniformBlockName(%s);',
    ('glGetActiveUniformBlockName', 7): 'GLBridge.uniformBlockNameC(%s);', ('glGetActiveUniformBlockName', 4): 'GLBridge.uniformBlockNameB(%s);',
    ('glGetTransformFeedbackVarying', 6): 'return GLBridge.feedbackVarying(%s);', ('glGetTransformFeedbackVarying', 4): 'return GLBridge.feedbackVarying(%s);',
    ('glGetTransformFeedbackVarying', 11): 'GLBridge.feedbackVaryingC(%s);',
    # debug output: a context that generates no messages keeps the callback and never calls it
    ('glDebugMessageCallback', 1): 'GLBridge.debugCallback = %s;', ('glDebugMessageCallbackKHR', 1): 'GLBridge.debugCallback = %s;', ('glGetTransformFeedbackVarying', 7): 'GLBridge.feedbackVaryingB(%s);',
}


def java_method(cls, ret, name, params):
    ov = JAVA_OVERRIDE.get((name, len(params)))
    if ov:
        args = ['a%d' % i for i in range(len(params))]
        return '  public static %s %s(%s) {\n    %s\n  }\n' % (ret, name, ', '.join('%s %s' % (p, a) for p, a in zip(params, args)),
                                                          ov % ', '.join(args)), None
    kinds = shape(params)
    args = ['a%d' % i for i in range(len(params))]
    sig = ', '.join('%s %s' % (p, a) for p, a in zip(params, args))
    rtype = {'void': 'void', 'int': 'int', 'boolean': 'boolean', 'java.lang.String': 'String', 'float': 'float', 'long': 'int'}.get(ret)
    cn = core_name(name)
    head = '  public static %s %s(%s) {\n' % (ret, name, sig)
    default = '' if ret == 'void' else ('    return %s;\n' % ('false' if ret == 'boolean' else '0' if ret in ('int', 'long', 'float', 'double', 'short', 'byte', 'char') else 'null'))
    if kinds is None or rtype is None or not core_has(cn):
        return head + '    a2w.Stubs.hit("%s.%s");\n%s  }\n' % (cls, name, default), None
    # glVertexAttribPointer(..., int offset) and glDrawElements(..., int offset): a buffer-object offset, not data
    if name in ('glVertexAttribPointer', 'glDrawElements', 'glVertexPointer', 'glColorPointer', 'glNormalPointer',
                'glTexCoordPointer', 'glPointSizePointerOES', 'glVertexAttribIPointer', 'glDrawRangeElements', 'glTexImage3D',
                'glTexSubImage3D', 'glCompressedTexImage3D', 'glCompressedTexSubImage3D') and params[-1] == 'int' and 'B' not in kinds:
        kinds = kinds[:-1] + ['O']
    # buffer-object offsets in other places: glDrawElementsInstanced's indices, glReadPixels into a pack buffer
    if name == 'glDrawElementsInstanced' and 'B' not in kinds: kinds = kinds[:3] + ['O'] + kinds[4:]
    if name == 'glReadPixels' and len(params) == 7 and all(p == 'int' for p in params): kinds = kinds[:6] + ['O']
    pre, jsargs, jsparams, jtypes, post, casts = [], [], [], [], [], {}
    ai = 0
    for k in kinds:
        a = args[ai]
        if k == 'P':
            pre.append('    Int8Array p%d = GLBridge.in(%s, %s);' % (ai, a, args[ai + 1]))
            if OUTPUT.match(name): post.append('    GLBridge.out(p%d, %s, %s);' % (ai, a, args[ai + 1]))
            jsparams.append('p%d' % ai); jtypes.append('Int8Array'); jsargs.append('p%d, 0' % ai); ai += 2
        elif k == 'B':
            pre.append('    Int8Array p%d = GLBridge.in(%s);' % (ai, a))
            if OUTPUT.match(name): post.append('    GLBridge.out(p%d, %s);' % (ai, a))
            jsparams.append('p%d' % ai); jtypes.append('Int8Array'); jsargs.append('p%d, 0' % ai); ai += 1
        elif k == 'O':
            jsparams.append(a); jtypes.append('int'); jsargs.append('null, ' + a); ai += 1
        elif k == 'l':   # a sync object name (GLsync as a Java long): small integers in gles.js
            jsparams.append(a); jtypes.append('int'); jsargs.append(a); casts[a] = '(int) '; ai += 1
        else:
            jsparams.append(a); jtypes.append({'i': 'int', 'f': 'float', 'b': 'boolean', 's': 'String'}[k]); jsargs.append(a); ai += 1
    if name in CLIENT_POINTERS and 'B' in kinds: pre.append('    GLBridge.client("%s", new Object[] {%s});' % (cn, ', '.join(args)))
    elif name in CLIENT_POINTERS: pre.append('    GLBridge.unclient("%s", %s);' % (cn, args[0] if cn == 'vertexAttribPointer' else '-1'))
    if name in DRAWS: pre.append('    GLBridge.beforeDraw();')
    if name == 'glClientActiveTexture': pre.append('    GLBridge.clientUnit = %s;' % args[0])
    COUNTER[0] += 1
    native = 'js%d_%s' % (COUNTER[0], name)
    call = '%s(%s)' % (native, ', '.join(casts.get(x, '') + x for x in jsparams))
    body = '\n'.join(pre) + ('\n' if pre else '')
    if ret == 'void': body += '    %s;\n' % call + '\n'.join(post) + ('\n' if post else '')
    else: body += '    %s r = %s;\n' % (ret, call) + '\n'.join(post) + ('\n' if post else '') + '    return r;\n'
    script = ('' if ret == 'void' else 'return ') + 'HW.GLES.%s(%s);' % (cn, ', '.join(jsargs))
    if ret == 'boolean': script = 'return !!HW.GLES.%s(%s);' % (cn, ', '.join(jsargs))
    if ret == 'java.lang.String': script = 'var r = HW.GLES.%s(%s); return r == null ? null : String(r);' % (cn, ', '.join(jsargs))
    jsb = '  @JSBody(params = {%s}, script = "%s")\n  private static native %s %s(%s);\n' % (
        ', '.join('"%s"' % p for p in jsparams), script, rtype, native, ', '.join('%s %s' % (t, p) for t, p in zip(jtypes, jsparams)))
    return head + body + '  }\n', jsb


COUNTER = [0]


def gen_class(cls):
    consts, methods = javap('android.opengl.' + cls)
    lines = ['package android.opengl;', '', 'import org.teavm.jso.JSBody;', 'import org.teavm.jso.typedarrays.Int8Array;', '',
             '/** Generated by tools/gen_gl.py from android.jar: OpenGL ES through runtime/site/gles.js (HW.GLES). */',
             'public class %s%s {' % (cls, ' extends ' + EXTENDS[cls] if cls in EXTENDS else ''), '  public %s() {}' % cls] + NESTED.get(cls, [])
    for t, n, v in consts:
        t = 'String' if t == 'java.lang.String' else t
        lines.append('  public static final %s %s = %s;' % (t, n, v))
    natives, seen = [], set()
    for ret, name, params in methods:
        key = (name, tuple(params))
        if key in seen: continue
        seen.add(key)
        m, n = java_method(cls, ret, name, params)
        lines.append(m)
        if n: natives.append(n)
    lines += natives + ['}', '']
    open(os.path.join(ROOT, 'runtime', 'framework', 'android', 'opengl', cls + '.java'), 'w').write('\n'.join(lines))
    return methods


def gen_iface(name, statics):
    consts, methods = javap('javax.microedition.khronos.opengles.' + name)
    ext = {'GL': '', 'GL10': ' extends GL', 'GL10Ext': ' extends GL', 'GL11': ' extends GL10', 'GL11Ext': ' extends GL',
           'GL11ExtensionPack': ' extends GL'}[name]
    lines = ['package javax.microedition.khronos.opengles;', '',
             '/** Generated by tools/gen_gl.py: the GL object a renderer gets calls the static GLES bindings. */',
             'public interface %s%s {' % (name, ext)]
    for t, n, v in consts:
        lines.append('  %s %s = %s;' % ('String' if t == 'java.lang.String' else t, n, v))
    for ret, m, params in methods:
        args = ['a%d' % i for i in range(len(params))]
        sig = ', '.join('%s %s' % (p, a) for p, a in zip(params, args))
        target = statics.get((m, tuple(params)))
        call = 'android.opengl.%s.%s(%s)' % (target, m, ', '.join(args)) if target else None
        if call: body = ('%s;' if ret == 'void' else 'return %s;') % call
        else:
            d = '' if ret == 'void' else ' return %s;' % ('null' if not ret in ('int', 'float', 'boolean', 'long') else 'false' if ret == 'boolean' else '0')
            body = 'a2w.Stubs.hit("%s.%s");%s' % (name, m, d)
        lines.append('  default %s %s(%s) { %s }' % (ret, m, sig, body))
        UNION.setdefault((m, tuple(params)), (ret, call, name))
    lines += ['}', '']
    open(os.path.join(ROOT, 'runtime', 'framework', 'javax', 'microedition', 'khronos', 'opengles', name + '.java'), 'w').write('\n'.join(lines))


UNION = {}


def gen_impl():
    """the object GLSurfaceView hands renderers: every GL interface at once (overriding the shared defaults)"""
    lines = ['package android.opengl;', '', '/** Generated by tools/gen_gl.py: implements every GL interface with the static bindings. */',
             'final class GLImpl implements javax.microedition.khronos.opengles.GL11, javax.microedition.khronos.opengles.GL10Ext,',
             '    javax.microedition.khronos.opengles.GL11Ext, javax.microedition.khronos.opengles.GL11ExtensionPack {']
    for (m, params), (ret, call, iface) in sorted(UNION.items()):
        args = ['a%d' % i for i in range(len(params))]
        sig = ', '.join('%s %s' % (p, a) for p, a in zip(params, args))
        if call: body = ('%s;' if ret == 'void' else 'return %s;') % call
        else:
            d = '' if ret == 'void' else ' return %s;' % ('null' if not ret in ('int', 'float', 'boolean', 'long') else 'false' if ret == 'boolean' else '0')
            body = 'a2w.Stubs.hit("%s.%s");%s' % (iface, m, d)
        lines.append('  @Override public %s %s(%s) { %s }' % (ret, m, sig, body))
    lines += ['}', '']
    open(os.path.join(ROOT, 'runtime', 'framework', 'android', 'opengl', 'GLImpl.java'), 'w').write('\n'.join(lines))


# native: one entry per C function (GLES 1 and 2), argument kinds from the Buffer overload (or the array one)
NATIVE_SPECIAL = {
    'glShaderSource': 'iipp', 'glGetShaderInfoLog': 'iipp', 'glGetProgramInfoLog': 'iipp', 'glGetShaderSource': 'iipp',
    'glGetActiveAttrib': 'iiipppp', 'glGetActiveUniform': 'iiipppp', 'glGetString': 'i',
    'glGetAttribLocation': 'is', 'glGetUniformLocation': 'is', 'glBindAttribLocation': 'iis',
    'glVertexAttribPointer': 'iiibip', 'glDrawElements': 'iiip', 'glVertexPointer': 'iiip', 'glColorPointer': 'iiip',
    'glNormalPointer': 'iip', 'glTexCoordPointer': 'iiip', 'glPointSizePointerOES': 'iip', 'glGetPointerv': 'ip',
}
NATIVE_RET = {'glGetString': 's'}


def native_sig(params):
    out, i = '', 0
    while i < len(params):
        p = params[i]
        if p in SIMPLE: out += {'int': 'i', 'float': 'f', 'boolean': 'b'}.get(SIMPLE[p], 'i'); i += 1
        elif p == 'java.lang.String': out += 's'; i += 1
        elif p.endswith('[]') and i + 1 < len(params) and params[i + 1] == 'int': out += 'p'; i += 2
        elif p in BUFFERS: out += 'p'; i += 1
        else: return None
    return out


natives = {}
statics = {}
for c in CLASSES:
    for ret, name, params in gen_class(c):
        statics.setdefault((name, tuple(params)), c)
        if c not in NATIVE_CLASSES: continue
        s = NATIVE_SPECIAL.get(name) or native_sig(params)
        if s is None: continue
        r = NATIVE_RET.get(name, {'void': 'v', 'int': 'i', 'boolean': 'i', 'java.lang.String': 's'}.get(ret, 'i'))
        prev = natives.get(name)
        if prev is None or ('p' in s and 'p' not in prev[0]) or len(s) < len(prev[0]): natives[name] = (s, r)
for i in IFACES: gen_iface(i, statics)
gen_impl()
for n, s in NATIVE_SPECIAL.items(): natives[n] = (s, NATIVE_RET.get(n, natives.get(n, ('', 'i'))[1] if n in natives else 'i'))
names = sorted(natives)
with open(os.path.join(ROOT, 'native', 'gl_table.h'), 'w') as f:
    f.write('// Generated by tools/gen_gl.py: the GLES functions native code can import (name, number of arguments).\n')
    f.write('static const struct { const char *name; int nargs; } GL_TABLE[] = {\n')
    for n in names: f.write('  {"%s", %d},\n' % (n, len(natives[n][0])))
    f.write('};\n')
with open(os.path.join(ROOT, 'native', 'library_gltable.js'), 'w') as f:
    f.write('// Generated by tools/gen_gl.py: argument kinds (i int, f float, b boolean, p pointer, s string) and result\n')
    f.write('// kind of each GL import, in gl_table.h order.\naddToLibrary({\n  $GLTABLE: [\n')
    for n in names: f.write("    ['%s', '%s', '%s'],\n" % (n, natives[n][0], natives[n][1]))
    f.write('  ],\n});\n')
print('gen_gl: %d classes, %d interfaces, %d native functions' % (len(CLASSES), len(IFACES), len(names)))
