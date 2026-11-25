#!/usr/bin/env python3
"""Compile the decoded APK resources (apktool output) into res.json for the web Android framework, resolving
configuration qualifiers for the target device the way the platform does, and list the reflection roots (manifest
components, inflated tags, class-name strings, onClick handlers) for the linker.

usage: build_res.py <apktool dir> <out res.json> [--apk APK] [--roots roots.txt]
                    [--screen WxH@DPI] [--sdk N] [--orientation land|port]

The page reads drawables and raw files straight from the APK, so res.json names their real entries there (apktool may
name a decoded folder differently, e.g. drawable-mdpi vs drawable-mdpi-v4). Files whose entry can't be found (resource
paths obfuscated by shrinkers) are listed under "extraFiles" for the build to pack next to the APK.
"""
import json, os, re, struct, sys, zipfile
import xml.etree.ElementTree as ET

A = '{http://schemas.android.com/apk/res/android}'


def arg(name, default=None):
    return sys.argv[sys.argv.index(name) + 1] if name in sys.argv else default


src, out_json = sys.argv[1:3]
res = os.path.join(src, 'res')
man = ET.parse(os.path.join(src, 'AndroidManifest.xml')).getroot()
pkg = man.get('package')
APP_NS = ('http://schemas.android.com/apk/res/' + pkg, 'http://schemas.android.com/apk/res-auto')

# ---------------------------------------------------------------- target device configuration
LAND = ('landscape', 'sensorLandscape', 'reverseLandscape', 'userLandscape', '0', '6', '8', '11')
PORT = ('portrait', 'sensorPortrait', 'reversePortrait', 'userPortrait', '1', '7', '9', '12')


def manifest_orientation():
    """the launcher activity's fixed orientation (or the first activity's), if any"""
    acts = man.findall('application/activity')
    launch = [a for a in acts if any(c.get(A + 'name') == 'android.intent.category.LAUNCHER'
                                     for f in a.findall('intent-filter') for c in f.findall('category'))]
    for a in launch + acts[:1]:
        o = a.get(A + 'screenOrientation') or ''
        if o in LAND: return 'land'
        if o in PORT: return 'port'
    return None


ORIENT = arg('--orientation') or manifest_orientation()
if not ORIENT and arg('--screen'):
    _m = re.match(r'(\d+)x(\d+)', arg('--screen'))
    ORIENT = 'land' if int(_m.group(1)) >= int(_m.group(2)) else 'port'
ORIENT = ORIENT or 'port'
_m = re.match(r'(\d+)x(\d+)@(\d+)', arg('--screen', '800x480@240'))
SW, SH, DPI = int(_m.group(1)), int(_m.group(2)), int(_m.group(3))


def manifest_sdk():
    sdk = man.find('uses-sdk')
    lo = sdk.get(A + 'minSdkVersion') if sdk is not None else None
    hi = sdk.get(A + 'targetSdkVersion') if sdk is not None else None
    yml = os.path.join(src, 'apktool.yml')
    if os.path.exists(yml):
        t = open(yml, encoding='utf-8').read()
        m = re.search(r"minSdkVersion:\s*'?(\d+)", t)
        if m: lo = m.group(1)
        m = re.search(r"targetSdkVersion:\s*'?(\d+)", t)
        if m: hi = m.group(1)
    lo = int(lo) if lo and str(lo).isdigit() else 1
    return lo, int(hi) if hi and str(hi).isdigit() else lo


MIN_SDK, TARGET_SDK = manifest_sdk()
# the platform level the app sees: the framework implements the API 19 behaviour, newer APIs are stubs; apps that
# require more are told they have their minimum
SDK = int(arg('--sdk', max(19, MIN_SDK)))
if (ORIENT == 'land') != (SW >= SH): SW, SH = SH, SW
W_DP, H_DP = SW * 160 // DPI, SH * 160 // DPI
SW_DP = min(W_DP, H_DP)
SIZE = 4 if SW_DP >= 720 else 3 if SW_DP >= 480 else 2 if SW_DP >= 320 else 1   # xlarge/large/normal/small (approx.)
LONG = max(W_DP, H_DP) * 3 >= min(W_DP, H_DP) * 5

DENS = {'ldpi': 120, 'mdpi': 160, 'tvdpi': 213, 'hdpi': 240, 'xhdpi': 320, 'xxhdpi': 480, 'xxxhdpi': 640,
        'nodpi': 0xffff, 'anydpi': 0xfffe}
SIZES = {'small': 1, 'normal': 2, 'large': 3, 'xlarge': 4}
# precedence (ResTable_config::isBetterThan / the "Providing resources" table)
SLOTS = ['mcc', 'locale', 'dir', 'sw', 'w', 'h', 'size', 'long', 'round', 'wcg', 'hdr', 'orient', 'ui', 'night',
         'density', 'touch', 'keys', 'keyboard', 'nav', 'navexp', 'v']


def parse_quals(tokens):
    """qualifier tokens -> {slot: value}, or None for a token this parser doesn't know (the folder is skipped)"""
    q = {}
    i = 0
    while i < len(tokens):
        t = tokens[i]
        if re.fullmatch(r'mcc\d+|mnc\d+', t): q['mcc'] = t
        elif re.fullmatch(r'[a-z]{2,3}', t) and t not in ('car', 'tv') :
            loc = t
            if i + 1 < len(tokens) and re.fullmatch(r'r[A-Z]{2}|r\d{3}', tokens[i + 1]):
                loc += '-' + tokens[i + 1][1:]; i += 1
            q['locale'] = loc
        elif t.startswith('b+'): q['locale'] = '-'.join(t[2:].split('+'))
        elif t in ('ldrtl', 'ldltr'): q['dir'] = t
        elif re.fullmatch(r'sw\d+dp', t): q['sw'] = int(t[2:-2])
        elif re.fullmatch(r'w\d+dp', t): q['w'] = int(t[1:-2])
        elif re.fullmatch(r'h\d+dp', t): q['h'] = int(t[1:-2])
        elif t in SIZES: q['size'] = SIZES[t]
        elif t in ('long', 'notlong'): q['long'] = t
        elif t in ('round', 'notround'): q['round'] = t
        elif t in ('widecg', 'nowidecg'): q['wcg'] = t
        elif t in ('highdr', 'lowdr'): q['hdr'] = t
        elif t in ('port', 'land', 'square'): q['orient'] = t
        elif t in ('car', 'desk', 'television', 'appliance', 'watch', 'vrheadset'): q['ui'] = t
        elif t in ('night', 'notnight'): q['night'] = t
        elif t in DENS: q['density'] = DENS[t]
        elif re.fullmatch(r'\d+dpi', t): q['density'] = int(t[:-3])
        elif t in ('notouch', 'finger', 'stylus'): q['touch'] = t
        elif t in ('keysexposed', 'keyshidden', 'keyssoft'): q['keys'] = t
        elif t in ('nokeys', 'qwerty', '12key'): q['keyboard'] = t
        elif t in ('navexposed', 'navhidden'): q['navexp'] = t
        elif t in ('nonav', 'dpad', 'trackball', 'wheel'): q['nav'] = t
        elif re.fullmatch(r'v\d+', t): q['v'] = int(t[1:])
        elif re.fullmatch(r'\d+x\d+', t): pass   # screen dimensions (deprecated): ignore
        else: return None
        i += 1
    return q


def matches(q, locale=None):
    """does a folder with qualifiers q apply on the target device (with UI locale `locale`)?"""
    if 'mcc' in q or 'ui' in q: return False
    if 'locale' in q:
        if locale is None: return False
        ql, ll = q['locale'].lower(), locale.lower()
        if ql != ll and ql != ll.split('-')[0]: return False
    if q.get('dir') == 'ldrtl': return False
    if q.get('sw', 0) > SW_DP or q.get('w', 0) > W_DP or q.get('h', 0) > H_DP: return False
    if q.get('size', 0) > SIZE: return False
    if q.get('long') == ('notlong' if LONG else 'long'): return False
    if q.get('round') == 'round' or q.get('wcg') == 'widecg' or q.get('hdr') == 'highdr': return False
    if 'orient' in q and q['orient'] != ORIENT: return False
    if q.get('night') == 'night': return False
    if q.get('touch') in ('notouch', 'stylus'): return False
    if q.get('keys') == 'keysexposed' or q.get('keyboard') in ('qwerty', '12key'): return False
    if q.get('navexp') == 'navexposed' or q.get('nav') in ('dpad', 'trackball', 'wheel'): return False
    if q.get('v', 0) > SDK: return False
    return True


def better_density(a, b, req=DPI):
    """ResTable_config::isBetterThan for density only: is a better than b?"""
    if a == b: return False
    if a == 0xfffe: return True
    if b == 0xfffe: return False
    h, l, big = (a, b, True) if a > b else (b, a, False)
    if req >= h: return big
    if l >= req: return not big
    return big if ((2 * l) - req) * h <= req * req else not big


def best(cands, locale=None):
    """the best of [(quals, payload)] for the target device: eliminate per qualifier in precedence order"""
    c = [x for x in cands if matches(x[0], locale)]
    if not c: return None
    for slot in SLOTS:
        if len(c) == 1: break
        if slot == 'density':
            top = None
            for q, _ in c:
                d = q.get('density', 160)
                if top is None or better_density(d, top): top = d
            c = [x for x in c if x[0].get('density', 160) == top]
            continue
        have = [x for x in c if slot in x[0]]
        if not have: continue
        if slot in ('sw', 'w', 'h', 'size', 'v'):
            m = max(x[0][slot] for x in have)
            c = [x for x in have if x[0][slot] == m]
        elif slot == 'locale':
            exact = [x for x in have if x[0]['locale'].lower() == (locale or '').lower()]
            c = exact or have
        else:
            c = have
    return c[0][1]


def res_dirs(res=res):
    """(kind, quals, dirname) for every resource folder the device could use"""
    out = []
    if not os.path.isdir(res): return out
    for d in sorted(os.listdir(res)):
        if not os.path.isdir(os.path.join(res, d)): continue
        parts = d.split('-')
        q = parse_quals(parts[1:])
        if q is None:
            print('build_res: skipping folder with unknown qualifiers:', d, file=sys.stderr)
            continue
        out.append((parts[0], q, d))
    return out


DIRS = res_dirs()

# ---------------------------------------------------------------- APK entries
apk_entries = {}
apk_file = arg('--apk')
if apk_file:
    def _key(path):
        d, f = path.rsplit('/', 1)
        return re.sub(r'-v\d+$', '', d) + '/' + f
    for n in zipfile.ZipFile(apk_file).namelist():
        if n.startswith('res/') and n.count('/') == 2: apk_entries.setdefault(_key(n), n)
extra_files = {}


def apk_path(path):
    if not apk_file: return path
    real = apk_entries.get(_key(path))
    if real is None:
        # not where its name says: the build packs the decoded file
        extra_files[path] = os.path.join(src, path)
        return 'extra:' + path
    return real


# ---------------------------------------------------------------- ids
ids, names = {}, {}
pub = os.path.join(res, 'values', 'public.xml')
if os.path.exists(pub):
    for p in ET.parse(pub).getroot():
        t, n, i = p.get('type'), p.get('name'), int(p.get('id'), 16)
        ids[(t, n)] = i
        names[i] = (t, n)

# framework resources: apps compile android.R ids into their code; their names (and the framework's own layouts,
# strings... in runtime/res) live in the same tables under types prefixed "android:"
FWRES = os.path.join(os.path.dirname(os.path.abspath(__file__)), '..', 'runtime', 'res')
FW = 'android:'
# (fwids-private.txt: the platform's private resources the framework's own themes name, at ids past the public ones)
for _f in ('fwids.txt', 'fwids-private.txt'):
    if not os.path.exists(os.path.join(FWRES, _f)): continue
    for line in open(os.path.join(FWRES, _f), encoding='utf-8'):
        if line.startswith('#') or not line.strip(): continue
        h, t, n = line.split()
        ids[(FW + t, n)] = int(h, 16)
        names[int(h, 16)] = (FW + t, n)


def rid(t, n):
    return ids.get((t, n))


def unescape(s):
    """aapt string processing: quotes keep whitespace, otherwise collapse it; backslash escapes."""
    if s is None: return ''
    out, i, q, ws = [], 0, False, False
    s = s.strip() if not s.startswith('"') else s
    while i < len(s):
        c = s[i]
        if c == '\\' and i + 1 < len(s):
            d = s[i + 1]; i += 2
            if d == 'n': out.append('\n')
            elif d == 't': out.append('\t')
            elif d == 'u' and i + 4 <= len(s):
                try: out.append(chr(int(s[i:i + 4], 16))); i += 4
                except ValueError: out.append('u')
            else: out.append(d)
            ws = False
            continue
        if c == '"': q = not q; i += 1; continue
        if not q and c in ' \t\n\r':
            if not ws: out.append(' ')
            ws = True; i += 1; continue
        ws = False
        out.append(c); i += 1
    return ''.join(out)


def inner_text(e):
    # styled strings (<b>, <u>...) lose their markup in getString()
    return (e.text or '') + ''.join(inner_text(c) + (c.tail or '') for c in e)


def ns_name(k):
    if k.startswith(A): return 'a', k[len(A):]
    for ns in APP_NS:
        if k.startswith('{' + ns + '}'): return 'x', k[len(ns) + 2:]
    if k.startswith('{'): return '?', k.split('}', 1)[1]
    return '', k


tags, class_strings, onclicks = set(), set(), set()
CLASS_RE = re.compile(r'[A-Za-z_$][\w$]*(\.[A-Za-z_$][\w$]*)+')


def tree(e, inflated=True):
    attrs = []
    for k, v in e.attrib.items():
        ns, n = ns_name(k)
        attrs += [ns, n, v]
        if n == 'onClick' and ns == 'a': onclicks.add(v)
        if v and CLASS_RE.fullmatch(v): class_strings.add(v)
        if v.startswith('.') and CLASS_RE.fullmatch(pkg + v): class_strings.add(pkg + v)
    if inflated: tags.add(e.tag)
    node = [e.tag, attrs, [tree(c, inflated) for c in e]]
    # character data, for XmlPullParser over res/xml files: text before the first child, text after the element
    text, tail = (e.text or '').strip() and e.text, (e.tail or '').strip() and e.tail
    if text or tail: node += [text or '', tail or '']
    return node


def parse_xml(path, inflated=True):
    try:
        return tree(ET.parse(path).getroot(), inflated)
    except ET.ParseError as ex:
        print('build_res: unreadable XML', path, ex, file=sys.stderr)
        return None


# ---------------------------------------------------------------- values
CATS = ('strings', 'colors', 'arrays', 'styles', 'attrs', 'integers', 'bools', 'dimens', 'plurals', 'refs', 'fractions')
vcands = {c: {} for c in CATS}   # cat -> id -> [(quals, value)]


def put(cat, i, q, v):
    if i is not None: vcands[cat].setdefault(i, []).append((q, v))


def parse_values(res, dirs, pre=''):
    def rid(t, n): return ids.get((pre + t, n))
    for kind, q, d in dirs:
        if kind != 'values': continue
        for f in sorted(os.listdir(os.path.join(res, d))):
            if not f.endswith('.xml') or f == 'public.xml': continue
            try:
                root = ET.parse(os.path.join(res, d, f)).getroot()
            except ET.ParseError as ex:
                print('build_res: unreadable', d, f, ex, file=sys.stderr); continue
            for e in root:
                n = e.get('name')
                tag = e.tag
                if tag == 'item' and e.get('type'): tag = e.get('type')
                if tag == 'string':
                    v = unescape(inner_text(e))
                    put('strings', rid('string', n), q, v)
                    if CLASS_RE.fullmatch(v): class_strings.add(v)
                elif tag == 'color':
                    put('colors', rid('color', n), q, (e.text or '').strip())
                elif tag in ('string-array', 'array', 'integer-array'):
                    put('arrays', rid('array', n), q, [unescape(inner_text(it)) for it in e])
                elif tag == 'plurals':
                    put('plurals', rid('plurals', n), q, {it.get('quantity'): unescape(inner_text(it)) for it in e})
                elif tag == 'style':
                    items = []
                    for it in e:
                        nm = it.get('name') or ''
                        ns, nm = ('a', nm[8:]) if nm.startswith('android:') else ('x', nm)
                        items += [ns, nm, (it.text or '').strip()]
                    put('styles', rid('style', n), q, {'name': n, 'parent': e.get('parent') or '', 'items': items})
                elif tag == 'attr':
                    info = {'name': n, 'format': e.get('format') or ''}
                    en = {}
                    for c in e:
                        if c.tag in ('enum', 'flag'):
                            try: en[c.get('name')] = int(c.get('value'), 0) - (1 << 32 if int(c.get('value'), 0) >= 1 << 31 else 0)
                            except (TypeError, ValueError): pass
                    if en: info['values'] = en
                    put('attrs', rid('attr', n), q, info)
                elif tag == 'integer':
                    try: put('integers', rid('integer', n), q, int((e.text or '0').strip(), 0))
                    except ValueError: put('refs', rid('integer', n), q, (e.text or '').strip())
                elif tag == 'bool':
                    t = (e.text or '').strip()
                    if t.startswith('@'): put('refs', rid('bool', n), q, t)
                    else: put('bools', rid('bool', n), q, t == 'true')
                elif tag == 'dimen':
                    put('dimens', rid('dimen', n), q, (e.text or '').strip())
                elif tag == 'fraction':
                    put('fractions', rid('fraction', n), q, (e.text or '').strip())
                elif tag in ('drawable', 'layout', 'anim', 'animator', 'xml', 'menu', 'mipmap', 'raw', 'id', 'interpolator', 'transition'):
                    if e.text and e.text.strip(): put('refs', rid(tag, n), q, e.text.strip())


FW_DIRS = res_dirs(FWRES)
parse_values(res, DIRS)
parse_values(FWRES, FW_DIRS, FW)


def resolve_values(locale=None, cats=CATS):
    out = {}
    for cat in cats:
        m = {}
        for i, cs in vcands[cat].items():
            if locale is not None and not any('locale' in x[0] for x in cs): continue
            v = best(cs, locale)
            if v is not None and (locale is None or any('locale' in x[0] and matches(x[0], locale) for x in cs)):
                m[i] = v
        out[cat] = m
    return out


base = resolve_values()
all_locales = sorted({q['locale'] for kind, q, d in DIRS if kind == 'values' and 'locale' in q})
locales = {}
for loc in all_locales:
    v = resolve_values(loc, ('strings', 'arrays', 'plurals'))
    locales[loc] = {'strings': v['strings'], 'arrays': v['arrays'], 'plurals': v['plurals']}


def deref_strings(arr, strings):
    out = []
    for v in arr:
        m = re.match(r'^@string/(.+)$', v)
        out.append(strings.get(rid('string', m.group(1)), base['strings'].get(rid('string', m.group(1)), '')) if m else v)
    return out


for k, arr in base['arrays'].items(): base['arrays'][k] = deref_strings(arr, base['strings'])
for loc, v in locales.items():
    for k, arr in v['arrays'].items(): v['arrays'][k] = deref_strings(arr, v['strings'] or base['strings'])

# ---------------------------------------------------------------- files
def image_size(path):
    with open(path, 'rb') as f:
        data = f.read()
    if data[:8] == b'\x89PNG\r\n\x1a\n': return struct.unpack('>II', data[16:24])
    if data[:4] == b'RIFF' and data[8:12] == b'WEBP':
        c = data[12:16]
        if c == b'VP8 ': return struct.unpack('<HH', data[26:30])[0] & 0x3fff, struct.unpack('<HH', data[26:30])[1] & 0x3fff
        if c == b'VP8L':
            b = int.from_bytes(data[21:25], 'little')
            return (b & 0x3fff) + 1, ((b >> 14) & 0x3fff) + 1
        if c == b'VP8X': return int.from_bytes(data[24:27], 'little') + 1, int.from_bytes(data[27:30], 'little') + 1
    if data[:6] in (b'GIF87a', b'GIF89a'): return struct.unpack('<HH', data[6:10])
    i = 2
    while data[:2] == b'\xff\xd8' and i + 9 < len(data):
        if data[i] != 0xFF: i += 1; continue
        m = data[i + 1]
        if m in (0xC0, 0xC1, 0xC2, 0xC3, 0xC5, 0xC6, 0xC7, 0xC9, 0xCA, 0xCB, 0xCD, 0xCE, 0xCF):
            h, w = struct.unpack('>HH', data[i + 5:i + 9]); return w, h
        i += 2 + struct.unpack('>H', data[i + 2:i + 4])[0]
    return 0, 0


fcands = {}   # id -> [(quals, (kind, dirname, file))]
FILE_KINDS = {'drawable': 'drawable', 'mipmap': 'mipmap', 'layout': 'layout', 'anim': 'anim', 'animator': 'animator',
              'interpolator': 'interpolator', 'color': 'color', 'raw': 'raw', 'xml': 'xml', 'menu': 'menu',
              'navigation': 'navigation', 'transition': 'transition', 'font': 'font'}
for root_dir, dirs, pre in ((res, DIRS, ''), (FWRES, FW_DIRS, FW)):
    for kind, q, d in dirs:
        t = FILE_KINDS.get(kind)
        if not t: continue
        for f in os.listdir(os.path.join(root_dir, d)):
            i = rid(pre + t, f.split('.')[0])
            if i is None: continue
            fcands.setdefault(i, []).append((q, (kind, d, f, root_dir)))

drawables, layouts, anims, raws, colorlists, xmls, fonts = {}, {}, {}, {}, {}, {}, {}


def file_ref(root_dir, d, f):
    if root_dir == res: return apk_path('res/%s/%s' % (d, f))
    extra_files['fw/%s/%s' % (d, f)] = os.path.join(root_dir, d, f)   # framework files are packed with the page
    return 'extra:fw/%s/%s' % (d, f)


for i, cs in fcands.items():
    pick = best(cs)
    if pick is None: continue
    kind, d, f, root_dir = pick
    p = os.path.join(root_dir, d, f)
    q = parse_quals(d.split('-')[1:]) or {}
    if kind in ('drawable', 'mipmap'):
        if f.endswith('.xml'):
            x = parse_xml(p, False)
            if x: drawables[i] = {'xml': x}
        else:
            w, h = image_size(p)
            dens = q.get('density', 160)
            if dens >= 0xfffe: dens = DPI   # nodpi / anydpi bitmaps are not scaled
            drawables[i] = {'file': file_ref(root_dir, d, f), 'density': dens, 'w': w, 'h': h,
                            'nine': f.endswith('.9.png')}
    elif kind == 'layout':
        x = parse_xml(p)
        if x: layouts[i] = x
    elif kind in ('anim', 'animator', 'interpolator'):
        x = parse_xml(p, False)
        if x: anims[i] = x
    elif kind == 'color':
        x = parse_xml(p, False)
        if x: colorlists[i] = x
    elif kind == 'raw':
        raws[i] = file_ref(root_dir, d, f)
    elif kind == 'font':
        if f.endswith('.xml'):
            x = parse_xml(p, False)
            if x: xmls[i] = x
        else: fonts[i] = file_ref(root_dir, d, f)
    else:   # xml, menu, navigation, transition: inflated by name (preferences, menus) or parsed
        x = parse_xml(p, kind in ('xml', 'menu', 'navigation'))
        if x: xmls[i] = x

# aliases: <item type="layout" name="main">@layout/main_wide</item>, <drawable name="x">#fff</drawable>
for i, v in base['refs'].items():
    t = names.get(i, ('', ''))[0]
    m = re.match(r'^@(?:\+)?([\w]+)/(.+)$', v)
    if m:
        j = rid(m.group(1), m.group(2))
        for table in (layouts, drawables, anims, xmls, colorlists, raws):
            if j in table and i not in table: table[i] = table[j]
        if t == 'bool' and j in base['bools']: base['bools'][i] = base['bools'][j]
        if t == 'integer' and j in base['integers']: base['integers'][i] = base['integers'][j]
    elif t == 'drawable' and v.startswith('#'):
        drawables[i] = {'color': v}

# ---------------------------------------------------------------- manifest
app = man.find('application')
if app is None: app = ET.Element('application')


def cname(n):
    if not n: return n
    if n.startswith('.'): return pkg + n
    return n if '.' in n else pkg + '.' + n


def has_cat(e, cat):
    return any(c.get(A + 'name') == cat for f in e.findall('intent-filter') for c in f.findall('category'))


def resval(v):
    """@string/x -> its text, other references unchanged"""
    m = re.match(r'^@string/(.+)$', v or '')
    return base['strings'].get(rid('string', m.group(1)), v) if m else (v or '')


def meta_value(v):
    """android:value="@integer/x" means the value, as the package manager resolves it (android:resource means the id)"""
    m = re.match(r'^@(integer|bool|string|color|dimen)/(.+)$', v or '')
    if not m: return v
    t, n = m.group(1), m.group(2)
    table = {'integer': 'integers', 'bool': 'bools', 'string': 'strings', 'color': 'colors', 'dimen': 'dimens'}[t]
    r = base[table].get(rid(t, n))
    if r is None: return v
    if t == 'bool': return 'true' if r else 'false'
    return str(r)


def meta_of(e):
    out = {}
    for m in e.findall('meta-data'):
        out[m.get(A + 'name')] = meta_value(m.get(A + 'value')) if m.get(A + 'value') is not None else m.get(A + 'resource')
    return out


def filters(x):
    """intent filters: actions, categories, data schemes/hosts/paths and MIME types"""
    out = []
    for f in x.findall('intent-filter'):
        d = f.findall('data')
        out.append({'a': [y.get(A + 'name') for y in f.findall('action')],
                    'c': [y.get(A + 'name') for y in f.findall('category')],
                    's': sorted({y.get(A + 'scheme') for y in d if y.get(A + 'scheme')}),
                    'h': sorted({y.get(A + 'host') for y in d if y.get(A + 'host')}),
                    't': sorted({y.get(A + 'mimeType') for y in d if y.get(A + 'mimeType')})})
    return out


meta = meta_of(app)
activities = []
for a in app.findall('activity'):
    activities.append({'name': cname(a.get(A + 'name')), 'theme': a.get(A + 'theme') or app.get(A + 'theme') or '',
                       'launcher': has_cat(a, 'android.intent.category.LAUNCHER'),
                       'orientation': a.get(A + 'screenOrientation') or '',
                       'label': resval(a.get(A + 'label')), 'config': a.get(A + 'configChanges') or '',
                       'actions': sorted({x.get(A + 'name') for f in a.findall('intent-filter') for x in f.findall('action')}),
                       'filters': filters(a), 'meta': meta_of(a),
                       'parent': cname(a.get(A + 'parentActivityName')) if a.get(A + 'parentActivityName') else None})
for al in app.findall('activity-alias'):
    t = cname(al.get(A + 'targetActivity'))
    for a in activities:
        if a['name'] == t:
            if has_cat(al, 'android.intent.category.LAUNCHER'): a['launcher'] = True
            a['filters'] += filters(al)
            a.setdefault('aliases', []).append(cname(al.get(A + 'name')))
if activities and not any(a['launcher'] for a in activities):
    activities[0]['launcher'] = True


def comp(tag):
    out = []
    for x in app.findall(tag):
        out.append({'name': cname(x.get(A + 'name')),
                    'actions': sorted({y.get(A + 'name') for f in x.findall('intent-filter') for y in f.findall('action')}),
                    'filters': filters(x), 'enabled': x.get(A + 'enabled') != 'false',
                    'authorities': x.get(A + 'authorities') or '', 'meta': meta_of(x)})
    return out


components = {'application': cname(app.get(A + 'name')) or '', 'services': comp('service'),
              'receivers': comp('receiver'), 'providers': comp('provider')}
min_sdk, target_sdk = MIN_SDK, TARGET_SDK
version = {'name': man.get(A + 'versionName') or '', 'code': int(man.get(A + 'versionCode') or 0)}
yml = os.path.join(src, 'apktool.yml')
if os.path.exists(yml):
    t = open(yml, encoding='utf-8').read()
    m = re.search(r"versionCode:\s*'?(\d+)", t)
    if m and not version['code']: version['code'] = int(m.group(1))
    m = re.search(r"versionName:\s*'?([^'\n]+)", t)
    if m and not version['name']: version['name'] = m.group(1).strip()
launcher = next((a for a in activities if a['launcher']), None)
icon = app.get(A + 'icon') or ''
m = re.match(r'^@(drawable|mipmap)/(.+)$', icon)
icon_file = None
if m:
    di = drawables.get(rid(m.group(1), m.group(2)))
    if di and 'file' in di: icon_file = di['file']

out = {
    'package': pkg,
    'names': {str(k): [t, n] for k, (t, n) in names.items()},
    'strings': base['strings'], 'locales': locales,
    'colors': base['colors'], 'colorlists': colorlists,
    'arrays': base['arrays'], 'plurals': base['plurals'], 'styles': base['styles'], 'attrs': base['attrs'],
    'integers': base['integers'], 'bools': base['bools'], 'dimens': base['dimens'], 'fractions': base['fractions'],
    'drawables': drawables, 'layouts': layouts, 'anims': anims, 'raws': raws, 'xmls': xmls, 'fonts': fonts,
    'meta': meta, 'activities': activities, 'components': components,
    'appTheme': app.get(A + 'theme') or '', 'appLabel': resval(app.get(A + 'label')), 'icon': icon_file,
    'appLabelRef': app.get(A + 'label') or '', 'appIconRef': icon,
    'version': version, 'minSdk': min_sdk, 'targetSdk': target_sdk,
    'libraries': [l.get(A + 'name') for l in app.findall('uses-library') if l.get(A + 'name')],
    'device': {'screen': '%dx%d@%d' % (SW, SH, DPI), 'sdk': SDK, 'orientation': ORIENT},
    'extraFiles': sorted(extra_files),
}
with open(out_json, 'w') as f:
    json.dump(out, f, ensure_ascii=False, separators=(',', ':'))

roots_file = arg('--roots')
if roots_file:
    with open(roots_file, 'w') as f:
        if components['application']: f.write('application\t%s\n' % components['application'])
        for a in activities: f.write('activity\t%s\n' % a['name'])
        for k in ('services', 'receivers', 'providers'):
            for c in components[k]: f.write('component\t%s\n' % c['name'])
        for t in sorted(tags): f.write('tag\t%s\n' % t)
        for s in sorted(class_strings | {m for m in meta.values() if m and CLASS_RE.fullmatch(m)}): f.write('string\t%s\n' % s)
        for s in sorted(onclicks): f.write('onClick\t%s\n' % s)

print('build_res: %s %s, %d ids, %d strings (%d locales), %d drawables, %d layouts, %d tags, %d activities, '
      '%d extra files -> %s (%d KB)' % (pkg, version['name'], len(names), len(base['strings']), len(locales),
                                         len(drawables), len(layouts), len(tags), len(activities), len(extra_files),
                                         out_json, os.path.getsize(out_json) // 1024))
