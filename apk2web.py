#!/usr/bin/env python3
"""apk2web: convert an Android app into a static web page.

    python3 apk2web.py APP OUT_DIR [options]

APP is an APK; several APKs (a base APK and its splits), or a directory of them; a split APK set (.apks from
bundletool or SAI, .xapk from APKPure, .apkm from APKMirror, a .zip of APKs); or an Android App Bundle (.aab). APK
expansion files (.obb) can be given too, as inputs or with --obb; XAPKs carry theirs.

Pipeline (see README.md):
  1. apktool decodes the manifest and resources; build_res.py resolves them for the target device -> res.json
  2. dex2jar turns every classesN.dex into JVM bytecode
  3. the framework (runtime/framework, an Android API implemented over the browser) is compiled
  4. the linker (tools/jarproc) closes the world: stubs for every missing class/member, JDK gaps, native-method
     bridges to the ARM emulator, reflection and JNI tables
  5. TeaVM compiles the result to WebAssembly (GC); errors it reports for JDK members it can't compile are fed back
     to the linker as missing, and the link is redone
  6. the site is assembled: page, runtime, app.wasm, res.json, the APK itself (assets, drawables), the ARM emulator and
     the native libraries

Needs JDK 17+, Maven, Python 3; Emscripten for the ARM emulator (only when the app has native code and no prebuilt
emulator is available).
"""
import argparse, hashlib, json, os, re, shutil, subprocess, sys, time, urllib.request, zipfile

ROOT = os.path.dirname(os.path.abspath(__file__))
APKTOOL_VERSION = '2.12.1'
APKTOOL_SHA256 = '66cf4524a4a45a7f56567d08b2c9b6ec237bcdd78cee69fd4a59c8a0243aeafa'
# SQLite for android.database.sqlite: sql.js (SQLite compiled to WebAssembly), from the npm registry
SQLJS_VERSION = '1.17.0'
SQLJS_SHA512 = 'HNdo5+2eSTEm7iLcfXUd2mjZX3Oe+5mJ0uDgIpirS7gSLHhZIw9kWDWxNcDVeFGEIBxJlkgo525g0CZJoH8GgA=='
SQLJS_FILES = ('sql-wasm-browser.js', 'sql-wasm-browser.wasm')
BINARYEN_VERSION = '133'
# split APKs and app bundles: APKEditor merges a base APK and its splits into one APK; bundletool builds the
# universal APK of an Android App Bundle
APKEDITOR_VERSION = '1.4.5'
APKEDITOR_SHA256 = '71999a1f28cf6b457aff17c139436349cd6ea30d75a0f9cd52f07bd52e21897b'
BUNDLETOOL_VERSION = '1.18.1'
BUNDLETOOL_SHA256 = '675786493983787ffa11550bdb7c0715679a44e1643f3ff980a529e9c822595c'
BINARYEN_SHA256 = '2dc9c7813f5375db93d96ead4b78222fcc3e2677bbb832297af4797782a37489'
# the WebAssembly features TeaVM's wasm-gc output uses (binaryen must not add newer encodings)
WASM_FEATURES = ['--enable-gc', '--enable-reference-types', '--enable-exception-handling', '--enable-bulk-memory',
                 '--enable-nontrapping-float-to-int', '--enable-sign-ext', '--enable-mutable-globals', '--enable-multivalue']
ABIS = ('armeabi-v7a', 'armeabi')   # what the emulator runs (ARMv7-A with VFP and NEON; ARMv5TE), best first
EMU_ABIS = {'armeabi-v7a': 'full', 'armeabi': 'full'}


def log(*a):
    print('[apk2web]', *a, flush=True)


def run(cmd, cwd=None, quiet=False, check=True, capture=False):
    if not quiet: log('$', ' '.join(str(c) for c in cmd))
    r = subprocess.run([str(c) for c in cmd], cwd=cwd, text=True,
                       stdout=subprocess.PIPE if capture or quiet else None,
                       stderr=subprocess.STDOUT if capture or quiet else None)
    if check and r.returncode != 0:
        if r.stdout: print(r.stdout[-20000:])
        raise SystemExit('command failed (%d): %s' % (r.returncode, ' '.join(str(c) for c in cmd)))
    return r


def sha256(path):
    h = hashlib.sha256()
    with open(path, 'rb') as f:
        for b in iter(lambda: f.read(1 << 20), b''): h.update(b)
    return h.hexdigest()


def tree_hash(*dirs):
    h = hashlib.sha256()
    for d in dirs:
        for base, subdirs, files in sorted(os.walk(d)):
            subdirs.sort()
            for f in sorted(files):
                p = os.path.join(base, f)
                h.update(os.path.relpath(p, d).encode()); h.update(open(p, 'rb').read())
    return h.hexdigest()[:16]


class Build:
    def __init__(self, a):
        self.a = a
        self.inputs = [os.path.abspath(x) for x in a.inputs]
        self.apk = None   # the one APK the pipeline works on (prepare_input)
        self.obbs = [os.path.abspath(x) for x in a.obb or []]
        self.out = os.path.abspath(a.out)
        self.work = os.path.abspath(a.work or os.path.join(self.out + '.build'))
        self.cache = os.path.abspath(a.cache or os.path.join(ROOT, '.cache'))
        os.makedirs(self.work, exist_ok=True)
        os.makedirs(self.cache, exist_ok=True)
        self.mvn = ['mvn', '-B', '-q', '-ntp'] + (a.maven_args.split() if a.maven_args else [])
        # TeaVM compiles in Maven's JVM; big apps (AndroidX, thousands of classes) need more than its default heap
        if '-Xmx' not in os.environ.get('MAVEN_OPTS', ''):
            try: mem = os.sysconf('SC_PAGE_SIZE') * os.sysconf('SC_PHYS_PAGES')
            except (ValueError, OSError, AttributeError): mem = 0
            if mem: os.environ['MAVEN_OPTS'] = (os.environ.get('MAVEN_OPTS', '') + ' -Xmx%dm' % max(1024, mem * 7 // 10 >> 20)).strip()

    def w(self, *p): return os.path.join(self.work, *p)

    # ------------------------------------------------------------ tools
    def apktool(self):
        p = os.path.join(self.cache, 'apktool_%s.jar' % APKTOOL_VERSION)
        if not os.path.exists(p):
            url = 'https://github.com/iBotPeaches/Apktool/releases/download/v%s/apktool_%s.jar' % (APKTOOL_VERSION, APKTOOL_VERSION)
            log('downloading', url)
            urllib.request.urlretrieve(url, p + '.part')
            os.replace(p + '.part', p)
        if sha256(p) != APKTOOL_SHA256: raise SystemExit('apktool checksum mismatch: ' + p)
        return p

    def pinned_jar(self, name, url, digest):
        p = os.path.join(self.cache, name)
        if not os.path.exists(p):
            log('downloading', url)
            urllib.request.urlretrieve(url, p + '.part')
            os.replace(p + '.part', p)
        if sha256(p) != digest: raise SystemExit('checksum mismatch: ' + p)
        return p

    def apkeditor(self):
        return self.pinned_jar('APKEditor-%s.jar' % APKEDITOR_VERSION, 'https://github.com/REAndroid/APKEditor/releases/download/'
                               'V%s/APKEditor-%s.jar' % (APKEDITOR_VERSION, APKEDITOR_VERSION), APKEDITOR_SHA256)

    def bundletool(self):
        return self.pinned_jar('bundletool-all-%s.jar' % BUNDLETOOL_VERSION, 'https://github.com/google/bundletool/releases/download/'
                               '%s/bundletool-all-%s.jar' % (BUNDLETOOL_VERSION, BUNDLETOOL_VERSION), BUNDLETOOL_SHA256)

    # ------------------------------------------------------------ input formats
    def prepare_input(self):
        """turns what was given into one APK (self.apk) and a list of expansion files (self.obbs): split APKs are
        merged, an app bundle becomes its universal APK"""
        d = self.w('input')
        shutil.rmtree(d, ignore_errors=True)
        os.makedirs(d)
        apks, bundles = [], []
        def take(path, name=None):
            kind = package_kind(path)
            if kind == 'apk': apks.append(path)
            elif kind == 'aab': bundles.append(path)
            elif kind == 'obb': self.obbs.append(path)
            elif kind == 'set': self.unpack_set(path, os.path.join(d, 'set%d' % len(os.listdir(d))), take)
            else: raise SystemExit('%s: not an APK, split APK set, app bundle or expansion file' % (name or path))
        for x in self.inputs:
            if os.path.isdir(x):
                for f in sorted(os.listdir(x)):
                    if f.lower().endswith(('.apk', '.obb', '.apks', '.xapk', '.apkm', '.aab')): take(os.path.join(x, f))
            else: take(x)
        if bundles:
            if len(bundles) > 1 or apks: raise SystemExit('give one app bundle (.aab), without APKs')
            apks = [self.universal_apk(bundles[0])]
        if not apks: raise SystemExit('no APK in the input')
        apks = choose_splits(apks)
        if len(apks) == 1:
            self.apk = apks[0]
        else:
            split_dir = os.path.join(d, 'splits')
            os.makedirs(split_dir)
            for i, a in enumerate(apks): shutil.copy(a, os.path.join(split_dir, '%02d-%s' % (i, os.path.basename(a))))
            log('merging %d split APKs: %s' % (len(apks), ', '.join(os.path.basename(a) for a in apks)))
            self.apk = self.w('merged.apk')
            run(['java', '-jar', self.apkeditor(), 'm', '-f', '-i', split_dir, '-o', self.apk], quiet=True)
        if self.obbs: log('expansion files: ' + ', '.join(os.path.basename(o) for o in self.obbs))

    def unpack_set(self, path, d, take):
        """a split APK set. bundletool's .apks: its universal APK if it has one, else what bundletool hands a device
        like the emulated one (a standalone APK if the set has those, else the splits of every module and language).
        XAPK (APKs and Android/obb/...), APKM or any other zip of APKs: all its APKs and expansion files"""
        os.makedirs(d, exist_ok=True)
        with zipfile.ZipFile(path) as z:
            if any(i.flag_bits & 1 for i in z.infolist()):
                raise SystemExit('%s is encrypted (older APKMirror .apkm files are; current ones are not)' % path)
            names = [n for n in z.namelist() if not n.endswith('/')]
            if 'toc.pb' in names and 'universal.apk' not in names:
                standalone = any(n.startswith('standalones/') for n in names)
                dpi = int((self.a.screen or '@240').split('@')[-1] or 240)
                for abis in (['armeabi-v7a', 'armeabi'], ['arm64-v8a'], ['x86_64', 'x86']):
                    spec = os.path.join(d, 'device.json')
                    json.dump({'supportedAbis': abis, 'supportedLocales': LANGUAGES, 'screenDensity': dpi,
                               'sdkVersion': 19 if standalone else 30}, open(spec, 'w'))
                    out = os.path.join(d, 'extracted')
                    shutil.rmtree(out, ignore_errors=True)
                    r = run(['java', '-jar', self.bundletool(), 'extract-apks', '--apks=' + path, '--device-spec=' + spec,
                             '--output-dir=' + out] + ([] if standalone else ['--modules=_ALL_']), check=False, capture=True)
                    if r.returncode == 0: break
                else: raise SystemExit('bundletool found no APKs in %s for any ABI:\n%s' % (path, r.stdout[-2000:]))
                for f in sorted(os.listdir(out)): take(os.path.join(out, f), f)
                return
            if 'universal.apk' in names: names = ['universal.apk']
            names = [n for n in names if n.lower().endswith(('.apk', '.obb')) and not n.startswith(('standalones/', 'instant/'))]
            for n in names:
                dest = os.path.join(d, os.path.basename(n))
                with z.open(n) as src, open(dest, 'wb') as f: shutil.copyfileobj(src, f)
                take(dest, n)

    def universal_apk(self, aab):
        """bundletool's universal APK of an app bundle: every module and install-time asset pack in one APK"""
        out = self.w('input', 'universal.apks')
        ks = os.path.join(self.cache, 'apk2web-debug.keystore')   # bundletool signs what it builds; any key does
        if not os.path.exists(ks):
            run(['keytool', '-genkeypair', '-keystore', ks, '-storepass', 'android', '-keypass', 'android', '-alias', 'apk2web',
                 '-keyalg', 'RSA', '-keysize', '2048', '-validity', '10000', '-dname', 'CN=apk2web'], quiet=True)
        log('building the universal APK of', os.path.basename(aab))
        run(['java', '-jar', self.bundletool(), 'build-apks', '--bundle=' + aab, '--output=' + out, '--mode=universal',
             '--overwrite', '--ks=' + ks, '--ks-pass=pass:android', '--ks-key-alias=apk2web', '--key-pass=pass:android'], quiet=True)
        apk = self.w('input', 'universal.apk')
        with zipfile.ZipFile(out) as z, open(apk, 'wb') as f: f.write(z.read('universal.apk'))
        return apk

    def sqljs(self):
        """the sql.js page script and wasm, from its npm package (integrity-checked)"""
        d = os.path.join(self.cache, 'sqljs-' + SQLJS_VERSION)
        if all(os.path.exists(os.path.join(d, f)) for f in SQLJS_FILES): return d
        tgz = d + '.tgz'
        if not os.path.exists(tgz):
            url = 'https://registry.npmjs.org/sql.js/-/sql.js-%s.tgz' % SQLJS_VERSION
            log('downloading', url)
            urllib.request.urlretrieve(url, tgz + '.part')
            os.replace(tgz + '.part', tgz)
        import base64, tarfile
        h = hashlib.sha512(open(tgz, 'rb').read()).digest()
        if base64.b64encode(h).decode() != SQLJS_SHA512: raise SystemExit('sql.js checksum mismatch: ' + tgz)
        os.makedirs(d, exist_ok=True)
        with tarfile.open(tgz) as t:
            for f in SQLJS_FILES + ('LICENSE',):
                m = t.getmember('package/dist/' + f if f != 'LICENSE' else 'package/LICENSE')
                with t.extractfile(m) as src, open(os.path.join(d, f), 'wb') as dst: shutil.copyfileobj(src, dst)
        return d

    def uses(self, jars, prefix):
        """whether the app's classes refer to a package (e.g. b'android/database/sqlite/')"""
        for j in jars:
            with zipfile.ZipFile(j) as z:
                for n in z.namelist():
                    if n.endswith('.class') and prefix in z.read(n): return True
        return False

    def deps(self):
        """jar paths: TeaVM class library, JSO, the platform libraries (org.json, xmlpull/kXML, Apache HTTP), dex2jar"""
        pom = os.path.join(ROOT, 'runtime', 'deps.pom.xml')
        cp = os.path.join(self.cache, 'deps-%s.classpath' % sha256(pom)[:12])
        if not os.path.exists(cp):
            run(self.mvn + ['-f', os.path.join(ROOT, 'runtime', 'deps.pom.xml'), 'dependency:build-classpath', '-Dmdep.outputFile=' + cp])
        jars = open(cp).read().strip().split(os.pathsep)
        pick = lambda frag: [j for j in jars if frag in os.path.basename(j)]
        libs = [j for j in jars if os.path.basename(j).startswith(('teavm-jso', 'teavm-interop', 'teavm-platform', 'teavm-core'))]
        platform = pick('android-json') + pick('kxml2')
        # the Apache HTTP client Android bundled: linked like the app's own code (it is compiled against the same
        # boot class path) when the app uses it; its transport is the framework's DefaultRequestDirector (XHR)
        bundled = pick('httpcore-4') + pick('httpclient-4')
        templates = pick('android-4.1.1.4')
        jars = [j for j in jars if j not in templates]
        return {'all': jars, 'classlib': pick('teavm-classlib')[0], 'jso': libs, 'platform': platform, 'bundled': bundled,
                'templates': templates, 'dex2jar': jars}

    def linker(self):
        jp = os.path.join(ROOT, 'tools', 'jarproc')
        jar = os.path.join(jp, 'target', 'jarproc-1.jar')
        cpf = os.path.join(jp, 'target', 'cp.txt')
        src_newer = not os.path.exists(jar) or any(
            os.path.getmtime(os.path.join(b, f)) > os.path.getmtime(jar)
            for b, _, fs in os.walk(os.path.join(jp, 'src')) for f in fs)
        if src_newer or not os.path.exists(cpf):
            run(self.mvn + ['package', 'dependency:build-classpath', '-Dmdep.outputFile=' + cpf], cwd=jp)
        return jar + os.pathsep + open(cpf).read().strip()

    def android_jar(self):
        if self.a.android_jar: return self.a.android_jar
        if os.environ.get('A2W_ANDROID_JAR'): return os.environ['A2W_ANDROID_JAR']
        for env in ('ANDROID_HOME', 'ANDROID_SDK_ROOT'):
            d = os.path.join(os.environ.get(env, ''), 'platforms')
            if os.path.isdir(d):
                levels = sorted((int(m.group(1)), x) for x in os.listdir(d) for m in [re.match(r'android-(\d+)$', x)] if m
                                and os.path.exists(os.path.join(d, x, 'android.jar')))
                if levels: return os.path.join(d, levels[-1][1], 'android.jar')
        log('warning: no android.jar (pass --android-jar or set ANDROID_HOME): missing SDK classes get shapes from use only')
        return None

    # ------------------------------------------------------------ steps
    def decode(self):
        d = self.w('apk')
        shutil.rmtree(d, ignore_errors=True)
        run(['java', '-jar', self.apktool(), 'd', '-f', '-s', '-o', d, self.apk], quiet=True)
        return d

    def resources(self, d):
        cmd = [sys.executable, '-I', os.path.join(ROOT, 'tools', 'build_res.py'), d, self.w('res.json'),
               '--apk', self.apk, '--roots', self.w('roots.txt')]
        if self.a.screen: cmd += ['--screen', self.a.screen]
        if self.a.orientation: cmd += ['--orientation', self.a.orientation]
        if self.a.sdk: cmd += ['--sdk', str(self.a.sdk)]
        run(cmd)
        return json.load(open(self.w('res.json'), encoding='utf-8'))

    def dex2jar_fix(self, deps):
        """dex2jar with tools/d2jfix applied (a comparison with zero on the left loses its direction): a class
        directory put first on dex2jar's class path"""
        src = os.path.join(ROOT, 'tools', 'd2jfix', 'D2jFix.java')
        translator = [j for j in deps['dex2jar'] if os.path.basename(j).startswith('dex-translator-')]
        if not translator: return None
        key = sha256(src)[:12] + '-' + os.path.basename(translator[0])[:-4]
        out = os.path.join(self.cache, 'd2jfix-' + key)
        if os.path.isdir(out): return out
        tmp = out + '.tmp'
        shutil.rmtree(tmp, ignore_errors=True)
        os.makedirs(tmp)
        run(['javac', '-nowarn', '--release', '11', '-cp', os.pathsep.join(deps['dex2jar']), '-d', tmp, src], quiet=True)
        run(['java', '-cp', self.linker(), 'jp.D2jPatch', translator[0], tmp], quiet=True)
        os.replace(tmp, out)
        return out

    def dex2jar(self, d, deps):
        fix = self.dex2jar_fix(deps)
        cp = os.pathsep.join(([fix] if fix else []) + deps['dex2jar'])
        dexes = sorted((f for f in os.listdir(d) if re.fullmatch(r'classes\d*\.dex', f)),
                       key=lambda f: int(re.sub(r'\D', '', f) or 1))
        jars = []
        for f in dexes:
            j = self.w('dex', f[:-4] + '.jar')
            os.makedirs(os.path.dirname(j), exist_ok=True)
            run(['java', '-cp', cp, 'com.googlecode.dex2jar.tools.Dex2jarCmd', '-f',
                 '-o', j, os.path.join(d, f)], quiet=True)
            jars.append(j)
        log('dex2jar: %d dex files' % len(jars))
        return jars

    def framework(self, deps):
        fw = os.path.join(ROOT, 'runtime', 'framework')
        gen = os.path.join(ROOT, 'runtime', 'framework-gen')   # declarations from the SDK (tools/genapi.sh)
        key = tree_hash(fw)[:16] + (tree_hash(gen)[:8] if os.path.isdir(gen) else '') + sha256(os.path.join(ROOT, 'runtime', 'deps.pom.xml'))[:8]
        out = os.path.join(self.cache, 'framework-' + key)
        if os.path.isdir(out): return out
        tmp = out + '.tmp'
        shutil.rmtree(tmp, ignore_errors=True)
        os.makedirs(tmp)
        srcs = [os.path.join(b, f) for top in (fw, gen, os.path.join(ROOT, 'runtime', 'gen-placeholder'))
                for b, _, fs in os.walk(top) for f in fs if f.endswith('.java')]
        lst = self.w('fw-sources.txt')
        open(lst, 'w').write('\n'.join(srcs))
        cp = os.pathsep.join([deps['classlib']] + deps['jso'] + deps['platform'] + deps['bundled'])
        run(['javac', '-nowarn', '-Xmaxerrs', '1000', '-encoding', 'UTF-8', '--release', '17', '-cp', cp, '-d', tmp, '@' + lst])
        shutil.rmtree(os.path.join(tmp, 'a2w', 'gen'))   # the linker generates the real ones
        os.replace(tmp, out)
        return out

    def native_libs(self, d):
        lib = os.path.join(d, 'lib')
        if not os.path.isdir(lib): return None, []
        have = {x: sorted(f for f in os.listdir(os.path.join(lib, x)) if f.endswith('.so')) for x in os.listdir(lib)}
        for abi in ([self.a.abi] if self.a.abi else ABIS):
            if have.get(abi):
                if EMU_ABIS.get(abi) != 'full':
                    log('warning: the emulator runs %s code only in part' % abi)
                return abi, [os.path.join(lib, abi, f) for f in have[abi]]
        if any(have.values()):
            log('warning: native libraries only for %s: no ARM build the emulator can run; native methods will throw '
                'UnsatisfiedLinkError' % ', '.join(k for k, v in have.items() if v))
        return None, []

    def asset_libs(self):
        """ARM libraries an app carries outside lib/ (loaded by its own code, e.g. from assets): their strings name
        the Java members native code uses"""
        out = []
        root = os.path.join(self.w('apk'), 'assets')
        for dp, _, fs in os.walk(root):
            for f in fs:
                p = os.path.join(dp, f)
                try:
                    with open(p, 'rb') as h: head = h.read(20)
                except OSError: continue
                if head[:4] == b'\x7fELF' and head[4] == 1 and head[18] == 40: out.append(p)
        return sorted(out)

    def managed_strings(self):
        """Unity's C# scripts reach Java by name (AndroidJavaClass("com.x.Plugin").CallStatic("init")): the string
        literals of the app's .NET assemblies (UTF-16, class names dotted), as a file of NUL-separated strings the
        linker reads like a library's"""
        root = os.path.join(self.w('apk'), 'assets', 'bin', 'Data', 'Managed')
        if not os.path.isdir(root): return []
        platform = re.compile(r'^(mscorlib|System(\..*)?|Mono\..*|UnityEngine(\..*)?|UnityEditor.*|Boo\..*|UnityScript.*)\.dll$', re.I)
        found = set()
        for f in sorted(os.listdir(root)):
            if not f.lower().endswith('.dll') or platform.match(f): continue
            b = open(os.path.join(root, f), 'rb').read()
            for m in re.findall(rb'(?:[\x20-\x7e]\x00){2,}', b):
                t = m.decode('utf-16le')
                found.add(t)
                if re.match(r'^[A-Za-z_][\w$]*(\.[A-Za-z_][\w$]*)+$', t): found.add(t.replace('.', '/'))
        if not found: return []
        out = self.w('managed-strings.bin')
        with open(out, 'wb') as h:
            for t in sorted(found): h.write(t.encode('ascii', 'replace') + b'\0')
        log('%d strings from the app\'s .NET assemblies name Java members' % len(found))
        return [out]

    def link(self, fw, jars, deps, sos, missing):
        out = self.w('link')
        shutil.rmtree(out, ignore_errors=True)
        cmd = ['java', '-Xss16m', '-cp', self.linker(), 'jp.Link', '--fw', fw, '--jdk', deps['classlib'],
               '--roots', self.w('roots.txt'), '--out', out, '--missing', missing]
        for j in deps['platform'] + deps['jso']: cmd += ['--lib', j]
        # the first copy of a class wins. Android's Apache HTTP client is on the boot class path up to API 27, and from
        # API 28 a shared library the app asks for (uses-library org.apache.http.legacy): then its classes win over
        # copies the app carries. Otherwise the app's own copy wins and the bundled client only fills the gaps.
        http = deps['bundled'] if self.uses(jars, b'org/apache/http/') else []
        res = getattr(self, 'res', None) or {}
        platform_http = (res.get('device') or {}).get('sdk', 19) <= 27 or 'org.apache.http.legacy' in res.get('libraries', [])
        for j in (http + jars if platform_http else jars + http): cmd += ['--app', j]
        aj = self.android_jar()
        if aj: cmd += ['--android', aj]
        for t in deps['templates']: cmd += ['--template', t]
        for s in sos + self.asset_libs() + self.managed_strings(): cmd += ['--so', s]   # strings for the JNI tables
        for p in self.a.drop or []: cmd += ['--drop', p.replace('.', '/').rstrip('/') + '/']
        for p in self.a.keep or []: cmd += ['--keep', p.replace('.', '/').rstrip('/') + '/']
        run(cmd)
        return out

    TEAVM_PATCHED = '0.16.0-a2w2'

    def patched_teavm(self, deps):
        """teavm-core with jp.TeaVMPatch's fix (see there), installed into the local Maven repository next to 0.16.0"""
        core = deps['classlib'].replace('teavm-classlib', 'teavm-core')
        d = os.path.join(os.path.dirname(os.path.dirname(core)), self.TEAVM_PATCHED)
        jar = os.path.join(d, 'teavm-core-%s.jar' % self.TEAVM_PATCHED)
        if os.path.exists(jar): return
        if not os.path.exists(core):   # resolved by Maven with the plugin; fetch it now
            run(self.mvn + ['dependency:get', '-Dartifact=org.teavm:teavm-core:0.16.0'])
        os.makedirs(d, exist_ok=True)
        run(['java', '-cp', self.linker(), 'jp.TeaVMPatch', core, jar + '.tmp'], quiet=True)
        pom = open(core[:-4] + '.pom').read().replace('<version>0.16.0</version>', '<version>%s</version>' % self.TEAVM_PATCHED, 1)
        open(jar[:-4] + '.pom', 'w').write(pom)
        os.replace(jar + '.tmp', jar)
        log('TeaVM: patched teavm-core installed as', self.TEAVM_PATCHED)

    def teavm(self, link_out):
        proj = self.w('teavm')
        os.makedirs(proj, exist_ok=True)
        pom = open(os.path.join(ROOT, 'runtime', 'pom.template.xml')).read()
        pom = pom.replace('@ALLJAR@', os.path.join(link_out, 'all.jar')).replace('@OPT@', self.a.opt)
        # a name section, so wasm errors and stack traces name the Java method (split off into symbols.txt later)
        pom = pom.replace('<debugInformationGenerated>false</debugInformationGenerated>',
                          '<debugInformationGenerated>false</debugInformationGenerated><minifying>false</minifying>')
        open(os.path.join(proj, 'pom.xml'), 'w').write(pom)
        r = run([x for x in self.mvn if x != '-q'] + ['package'], cwd=proj, check=False, capture=True)   # TeaVM's diagnostics
        open(self.w('teavm.log'), 'w').write(r.stdout or '')
        wasm = os.path.join(proj, 'target', 'out', 'app.wasm')
        return (wasm if r.returncode == 0 and os.path.exists(wasm) else None), r.stdout or ''

    def wasm_opt(self):
        """binaryen's wasm-opt: on PATH, in Emscripten, or a pinned release downloaded into the cache"""
        found = shutil.which('wasm-opt')
        if found: return found
        if os.environ.get('EMSDK') and os.path.exists(os.path.join(os.environ['EMSDK'], 'upstream', 'bin', 'wasm-opt')):
            return os.path.join(os.environ['EMSDK'], 'upstream', 'bin', 'wasm-opt')
        d = os.path.join(self.cache, 'binaryen-version_' + BINARYEN_VERSION)
        exe = os.path.join(d, 'bin', 'wasm-opt')
        if not os.path.exists(exe):
            tgz = d + '.tar.gz'
            url = ('https://github.com/WebAssembly/binaryen/releases/download/version_%s/binaryen-version_%s-x86_64-linux.tar.gz'
                   % (BINARYEN_VERSION, BINARYEN_VERSION))
            log('downloading', url)
            urllib.request.urlretrieve(url, tgz + '.part')
            os.replace(tgz + '.part', tgz)
            if sha256(tgz) != BINARYEN_SHA256: raise SystemExit('binaryen checksum mismatch: ' + tgz)
            import tarfile
            with tarfile.open(tgz) as t: t.extractall(self.cache, filter='data')
        return exe

    def fix_wasm(self, wasm):
        """TeaVM can emit locals of non-nullable types that are read before they are set on some path, which engines
        reject; a binaryen round trip (no optimization) rewrites them. Checked with node when it is there.
        Returns the Java methods ('pkg/Cls.name') whose code is invalid beyond that repair, [] when app.wasm is good."""
        node = shutil.which('node')
        check = "WebAssembly.compile(require('fs').readFileSync(process.argv[1])).then(()=>0,e=>{console.log(e.message);process.exit(3)})"
        if node:
            r = subprocess.run([node, '-e', check, wasm], capture_output=True, text=True)
            if r.returncode == 0: return []
            log('app.wasm does not validate (%s); rewriting it with binaryen' % r.stdout.strip())
        fixed = wasm + '.fixed'
        r = run([self.wasm_opt()] + WASM_FEATURES + ['-g', wasm, '-o', fixed], check=False, capture=True)   # -g: keep the names (symbols.txt)
        if r.returncode == 0 and node:
            r2 = subprocess.run([node, '-e', check, fixed], capture_output=True, text=True)
            if r2.returncode == 0: os.replace(fixed, wasm); return []
        elif r.returncode == 0: os.replace(fixed, wasm); return []
        # invalid beyond repair: the name section says which methods
        bad = set(re.findall(r'validator error in function ([^\]]+)\]', r.stdout or ''))
        if node:
            r3 = subprocess.run([node, '-e', check, wasm], capture_output=True, text=True)
            bad.update(re.findall(r'Compiling function #\d+:"([^"]+)" failed', r3.stdout or ''))
        methods = sorted(set(b.replace('.', '/').replace('::', '.') for b in bad if '::' in b))
        if not methods:
            raise SystemExit('app.wasm does not validate (TeaVM emitted mistyped code):\n' + (r.stdout or '')[-3000:])
        return methods

    @staticmethod
    def teavm_missing(text):
        """members/classes TeaVM reports it can't find -> the linker's --missing lines"""
        out = set()
        for m in re.finditer(r'Method (\S+?)\.([\w$<>]+)(\([^)\s]*\)\S+) was not found', text):
            out.add('method %s.%s%s' % (m.group(1).replace('.', '/'), m.group(2), m.group(3)))
        for m in re.finditer(r'Field (\S+?)\.([\w$]+) was not found', text):
            out.add('field %s.%s' % (m.group(1).replace('.', '/'), m.group(2)))
        for m in re.finditer(r'Class (\S+) was not found', text):
            out.add('class ' + m.group(1).replace('.', '/'))
        return out

    def emulator(self):
        """the ARM emulator (app independent): native/prebuilt when it matches the sources, else built with Emscripten"""
        nat = os.path.join(ROOT, 'native')
        srcs = [f for f in sorted(os.listdir(nat)) if f.endswith(('.c', '.h', '.inc', '.js', '.sh'))]
        h = hashlib.sha256()
        for f in srcs: h.update(f.encode()); h.update(open(os.path.join(nat, f), 'rb').read())
        key = h.hexdigest()[:16]
        pre = os.path.join(nat, 'prebuilt')
        if os.path.exists(os.path.join(pre, 'emu.wasm')) and open(os.path.join(pre, 'SOURCE_HASH')).read().strip() == key:
            return os.path.join(pre, 'emu.mjs'), os.path.join(pre, 'emu.wasm')
        c = os.path.join(self.cache, 'emu-' + key)
        if not os.path.exists(os.path.join(c, 'emu.wasm')):
            if not shutil.which('emcc') and not os.environ.get('EMSDK'):
                if os.path.exists(os.path.join(pre, 'emu.wasm')):
                    log('warning: native/ changed since native/prebuilt was built and there is no Emscripten: using it anyway')
                    return os.path.join(pre, 'emu.mjs'), os.path.join(pre, 'emu.wasm')
                raise SystemExit('the ARM emulator needs Emscripten (emcc on PATH or EMSDK set)')
            os.makedirs(c, exist_ok=True)
            run(['bash', os.path.join(nat, 'build.sh'), c])
        return os.path.join(c, 'emu.mjs'), os.path.join(c, 'emu.wasm')

    def assemble(self, res, wasm, link_out, abi, sos):
        out = self.out
        shutil.rmtree(out, ignore_errors=True)
        os.makedirs(out)
        site = os.path.join(ROOT, 'runtime', 'site')
        for f in os.listdir(site):
            p = os.path.join(site, f)
            (shutil.copytree if os.path.isdir(p) else shutil.copy)(p, os.path.join(out, f))
        shutil.copy(wasm, os.path.join(out, 'app.wasm'))
        if os.path.exists(self.w('symbols.txt')): shutil.copy(self.w('symbols.txt'), os.path.join(out, 'symbols.txt'))
        shutil.copy(self.w('res.json'), os.path.join(out, 'res.json'))
        shutil.copy(self.apk, os.path.join(out, 'app.apk'))
        natives = json.load(open(os.path.join(link_out, 'natives.json')))
        # the emulator also hosts the file system Java and native code share, so every site has it
        emu_js, emu_wasm = self.emulator()
        shutil.copy(emu_js, os.path.join(out, 'emu.js'))
        shutil.copy(emu_wasm, os.path.join(out, 'emu.wasm'))
        libs = [os.path.basename(s) for s in sos]
        sqlite = self.uses(self.jars, b'android/database/sqlite/')
        if sqlite:
            d = self.sqljs()
            for f in SQLJS_FILES: shutil.copy(os.path.join(d, f), os.path.join(out, f))
            shutil.copy(os.path.join(d, 'LICENSE'), os.path.join(out, 'sql-wasm.LICENSE'))
        if res.get('extraFiles'):
            with zipfile.ZipFile(os.path.join(out, 'extra.zip'), 'w', zipfile.ZIP_STORED) as z:
                for f in res['extraFiles']:
                    src = os.path.join(ROOT, 'runtime', 'res', f[3:]) if f.startswith('fw/') else os.path.join(self.w('apk'), f)
                    z.write(src, f)
        icon = None
        if res.get('icon') and not res['icon'].startswith('extra:'):
            with zipfile.ZipFile(self.apk) as z:
                try:
                    data = z.read(res['icon'])
                    icon = 'icon' + os.path.splitext(res['icon'])[1]
                    open(os.path.join(out, icon), 'wb').write(data)
                except KeyError: pass
        dev = res.get('device', {})
        label = self.a.title or res.get('appLabel') or res['package']
        if label.startswith('@'): label = res['package']
        config = {'package': res['package'], 'label': label, 'version': res.get('version', {}),
                  'screen': dev.get('screen', '800x480@240'), 'orientation': dev.get('orientation', 'land'),
                  'sdk': dev.get('sdk', 19), 'abi': abi, 'libs': libs, 'natives': natives, 'icon': icon,
                  'extra': bool(res.get('extraFiles')), 'locales': sorted(res.get('locales', {})), 'sqlite': sqlite}
        if self.obbs:   # APK expansion files, served next to the page; the page puts them in Android/obb/<package>/
            os.makedirs(os.path.join(out, 'obb'), exist_ok=True)
            code = str((res.get('version') or {}).get('code') or 1)
            names = []
            for i, o in enumerate(self.obbs):
                n = os.path.basename(o)
                if not re.match(r'^(main|patch)\.\d+\.[\w.]+\.obb$', n):   # named as Google Play names them
                    n = '%s.%s.%s.obb' % ('main' if i == 0 else 'patch', code, res['package'])
                shutil.copy(o, os.path.join(out, 'obb', n))
                names.append(n)
            config['obb'] = names
        web = self.web_assets(out)
        if web: config['webAssets'] = web
        if sos:   # what the libraries import, also those the app links itself (e.g. from its assets)
            elfs = sos + self.asset_libs()
            config['imports'] = sorted(set().union(*(elf_imports(p) for p in elfs)) - set().union(*(elf_imports(p, True) for p in elfs)))
        json.dump(config, open(os.path.join(out, 'config.json'), 'w'), ensure_ascii=False, indent=1)
        idx = os.path.join(out, 'index.html')
        h = open(idx, encoding='utf-8').read().replace('@TITLE@', html_escape(label))
        h = h.replace('<!--icon-->', '<link rel="icon" href="%s">' % icon if icon else '')
        open(idx, 'w', encoding='utf-8').write(h)
        shutil.copy(os.path.join(link_out, 'link-report.txt'), os.path.join(self.work, 'link-report.txt'))

    def web_assets(self, out):
        """HTML pages in the assets (a WebView's file:///android_asset/ pages, Cordova's www/): the folders that hold
        them are served beside the page under android_asset/, so that the pages' relative URLs (scripts, images,
        requests) resolve. Returns the served folders."""
        with zipfile.ZipFile(self.apk) as z:
            names = [n for n in z.namelist() if n.startswith('assets/') and not n.endswith('/')]
            tops = set()
            for n in names:
                if n.lower().endswith(('.html', '.htm', '.xhtml')):
                    parts = n.split('/')
                    tops.add('' if len(parts) == 2 else parts[1] + '/')
            if not tops: return []
            if '' in tops: tops = {''}
            dest = os.path.join(out, 'android_asset')
            for n in names:
                rel = n[len('assets/'):]
                if not any(rel.startswith(t) for t in tops): continue
                p = os.path.normpath(os.path.join(dest, rel))
                if not p.startswith(dest + os.sep): continue   # (a name that would leave the folder)
                os.makedirs(os.path.dirname(p), exist_ok=True)
                with z.open(n) as src, open(p, 'wb') as dst: shutil.copyfileobj(src, dst)
            log('web assets: %s served under android_asset/' % ', '.join(sorted(t or '(all)' for t in tops)))
            return sorted(tops)

    def go(self):
        t0 = time.time()
        self.prepare_input()
        deps = self.deps()
        d = self.decode()
        res = self.res = self.resources(d)
        jars = self.jars = self.dex2jar(d, deps)
        fw = self.framework(deps)
        self.patched_teavm(deps)
        abi, sos = self.native_libs(d) if not self.a.no_native else (None, [])
        missing = self.w('missing.txt')
        open(missing, 'w').close()
        wasm = None
        for attempt in range(1, self.a.max_rounds + 1):
            link_out = self.link(fw, jars, deps, sos, missing)
            wasm, text = self.teavm(link_out)
            known = set(l.strip() for l in open(missing) if l.strip())
            if wasm:
                probs = self.fix_wasm(wasm)
                if not probs: break
                bad = set('stub ' + m for m in probs) - known
                if not bad: raise SystemExit('app.wasm does not validate (TeaVM emitted mistyped code; log: %s)' % self.w('teavm.log'))
                log('round %d: TeaVM emitted invalid code for %s; relinking with those methods stubbed'
                    % (attempt, ', '.join(b[5:] for b in sorted(bad))))
                with open(missing, 'a') as f:
                    for n in sorted(bad): f.write(n + '\n')
                wasm = None
                continue
            new = self.teavm_missing(text) - known
            errors = [l for l in text.splitlines() if '[ERROR]' in l][:40]
            if not new:
                print('\n'.join(errors))
                raise SystemExit('TeaVM failed (full log: %s)' % self.w('teavm.log'))
            log('round %d: TeaVM could not compile %d members; relinking with them stubbed' % (attempt, len(new)))
            with open(missing, 'a') as f:
                for n in sorted(new): f.write(n + '\n')
        if not wasm: raise SystemExit('TeaVM failed after %d rounds (log: %s)' % (self.a.max_rounds, self.w('teavm.log')))
        split_names(wasm, self.w('symbols.txt'))
        self.assemble(res, wasm, link_out, abi, sos)
        log('done in %ds: %s (%s); link report: %s' % (time.time() - t0, self.out, human(dir_size(self.out)),
                                                    os.path.join(self.work, 'link-report.txt')))


ABI_DIRS = ('armeabi-v7a', 'armeabi', 'arm64-v8a', 'x86', 'x86_64', 'mips', 'mips64')


def package_kind(path):
    """what a file is, by its content: apk, aab (app bundle), set (a zip of APKs: .apks, .xapk, .apkm), obb, or None"""
    if path.lower().endswith('.obb'): return 'obb'
    try:
        with zipfile.ZipFile(path) as z: names = z.namelist()
    except (zipfile.BadZipFile, OSError):
        return None
    if 'AndroidManifest.xml' in names: return 'apk'
    if 'base/manifest/AndroidManifest.xml' in names or 'BundleConfig.pb' in names: return 'aab'
    if any(n.lower().endswith('.apk') for n in names): return 'set'
    return None


# languages a device spec lists so that bundletool hands out every language split
LANGUAGES = ('af am ar as az be bg bn bs ca cs cy da de el en es et eu fa fi fil fr ga gl gu he hi hr hu hy id is it ja '
             'ka kk km kn ko ky lo lt lv mk ml mn mr ms my nb ne nl or pa pl pt ro ru si sk sl sq sr sv sw ta te th tl tr '
             'uk ur uz vi zh zu').split()


def choose_splits(apks):
    """the APKs to merge: all of them (base, features, densities, languages), except native code splits of ABIs the
    emulator does not run when one it runs is there"""
    def abis(p):
        with zipfile.ZipFile(p) as z:
            return {n.split('/')[1] for n in z.namelist() if n.startswith('lib/') and n.count('/') >= 2}
    found = {p: abis(p) for p in apks}
    runnable = set().union(*found.values()) & set(ABIS)
    if not runnable: return apks
    return [p for p in apks if not found[p] or found[p] & runnable or package_has_code(p)]


def package_has_code(p):
    with zipfile.ZipFile(p) as z: return any(n.endswith('.dex') for n in z.namelist())


def elf_imports(path, defined=False):
    """names a 32-bit ARM ELF library imports (undefined dynamic symbols), or with defined=True those it exports"""
    import struct
    try: b = open(path, 'rb').read()
    except OSError: return set()
    if b[:4] != b'\x7fELF' or b[4] != 1: return set()
    shoff, = struct.unpack_from('<I', b, 32)
    shentsize, shnum = struct.unpack_from('<HH', b, 46)
    out = set()
    try:
        secs = [struct.unpack_from('<IIIIIIIIII', b, shoff + i * shentsize) for i in range(shnum)]
        for sec in secs:
            if sec[1] != 11: continue   # SHT_DYNSYM
            strtab = secs[sec[6]]
            for k in range(1, sec[5] // 16):
                name, _, _, info, _, shndx = struct.unpack_from('<IIIBBH', b, sec[4] + 16 * k)
                if bool(shndx) != defined or (info >> 4) not in (1, 2) or not name: continue
                o = strtab[4] + name
                n = b[o:b.index(b'\0', o)].decode('ascii', 'replace')
                if n: out.add(n)
    except (struct.error, IndexError, ValueError): pass
    return out


def split_names(wasm, symbols):
    """moves the wasm name section into a text file: each function's code offset and Java name (offsets delta-coded in
    base 36), for the page to name stack frames; the module itself loses nothing it runs with"""
    b = open(wasm, 'rb').read()
    def leb(i):
        r = sh = 0
        while True:
            x = b[i]; i += 1; r |= (x & 0x7f) << sh; sh += 7
            if x < 0x80: return r, i
    pos, nimp, bodies, names, keep = 8, 0, [], {}, [b[:8]]
    while pos < len(b):
        start = pos
        sid = b[pos]; n, pos = leb(pos + 1); end = pos + n
        if sid == 2:   # imported functions come first in the function index space
            cnt, j = leb(pos)
            for _ in range(cnt):
                for _ in range(2): l, j = leb(j); j += l
                kind = b[j]; j += 1
                if kind == 0: _, j = leb(j); nimp += 1
                elif kind in (1, 2):
                    if kind == 1: j += 1
                    fl = b[j]; _, j = leb(j + 1)
                    if fl & 1: _, j = leb(j)
                elif kind == 3: j += 2
                else: _, j = leb(j + 1)
        elif sid == 10:
            cnt, j = leb(pos)
            for k in range(cnt):
                sz, j2 = leb(j); bodies.append((j2, nimp + k)); j = j2 + sz
        elif sid == 0:
            l, j = leb(pos)
            if b[j:j + l] == b'name':
                j += l
                while j < end:
                    sub = b[j]; sl, j = leb(j + 1); se = j + sl
                    if sub == 1:
                        cnt, j = leb(j)
                        for _ in range(cnt):
                            idx, j = leb(j); l2, j = leb(j); names[idx] = b[j:j + l2].decode('utf-8', 'replace'); j += l2
                    j = se
                pos = end
                continue   # dropped from the module
        keep.append(b[start:end])
        pos = end
    if not names: return
    def b36(v):
        d = '0123456789abcdefghijklmnopqrstuvwxyz'
        s = ''
        while True:
            s = d[v % 36] + s; v //= 36
            if not v: return s
    with open(symbols, 'w') as f:
        prev = 0
        for off, idx in bodies:
            if idx in names: f.write('%s\t%s\n' % (b36(off - prev), names[idx])); prev = off
    with open(wasm + '.tmp', 'wb') as f: f.write(b''.join(keep))
    os.replace(wasm + '.tmp', wasm)


def html_escape(s):
    return s.replace('&', '&amp;').replace('<', '&lt;').replace('>', '&gt;').replace('"', '&quot;')


def dir_size(d):
    return sum(os.path.getsize(os.path.join(b, f)) for b, _, fs in os.walk(d) for f in fs)


def human(n):
    for u in ('B', 'KB', 'MB', 'GB'):
        if n < 1024: return '%d %s' % (n, u)
        n //= 1024
    return '%d TB' % n


def main():
    p = argparse.ArgumentParser(description='Convert an Android APK into a static web page.')
    p.add_argument('inputs', nargs='+', metavar='APP', help='APK, split APKs (or their directory), .apks, .xapk, .apkm, .aab, .obb')
    p.add_argument('out', help='output directory (the site)')
    p.add_argument('--obb', action='append', help='APK expansion file (main.<version>.<package>.obb; repeatable)')
    p.add_argument('--work', help='build directory (default: OUT.build)')
    p.add_argument('--cache', help='tool and framework cache (default: .cache next to this script)')
    p.add_argument('--screen', help='WIDTHxHEIGHT@DPI (default 800x480@240, turned to the app orientation)')
    p.add_argument('--orientation', choices=('land', 'port'), help='default: from the manifest, else portrait')
    p.add_argument('--sdk', type=int, help='API level the app sees (default: its minSdkVersion, at least 19)')
    p.add_argument('--abi', choices=ABIS, help='native library ABI to use (default: the best one present)')
    p.add_argument('--no-native', action='store_true', help='ignore native libraries')
    p.add_argument('--android-jar', help='android.jar for the shapes of SDK classes (default: newest in $ANDROID_HOME)')
    p.add_argument('--drop', action='append', help='package whose classes are replaced by no-op stubs (repeatable)')
    p.add_argument('--keep', action='append', help='package to keep although it is on the default SDK drop list')
    p.add_argument('--title', help='page title (default: the app label)')
    p.add_argument('--opt', default='SIMPLE', choices=('NONE', 'SIMPLE', 'ADVANCED', 'FULL'), help='TeaVM optimization')
    p.add_argument('--max-rounds', type=int, default=6, help='link/compile rounds for TeaVM-reported gaps')
    p.add_argument('--maven-args', help='extra Maven options (e.g. -Dmaven.repo.local=...)')
    Build(p.parse_args()).go()


if __name__ == '__main__':
    main()
