#!/usr/bin/env python3
"""Ports Java sources from AOSP (Apache License 2.0) into runtime/framework.

  tools/aosp.py SRC-PATH... [--repo platform/frameworks/base] [--to runtime/framework]

Each file is fetched at the pinned commit of its repository (cached in .cache/aosp), then:
  - annotations from android.annotation, android.compat.annotation, dalvik.annotation, com.android.internal.annotations
    and androidx.annotation are removed (with their imports): they have no effect on behaviour;
  - a header names the source (repository, commit, path).
The result usually needs hand edits (native methods, hidden dependencies); those are made in runtime/framework and
listed in the file's header.
"""
import argparse, base64, os, re, sys, urllib.request

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
PINS = {   # repository -> commit
    'platform/frameworks/base': 'cc8bb19595c661f5cf42f330457000e643c67d1b',   # android15-release, 2024-09-30
}
STRIP_PACKAGES = ('android.annotation.', 'android.compat.annotation.', 'dalvik.annotation.', 'com.android.internal.annotations.',
                  'androidx.annotation.', 'android.ravenwood.annotation.')
STRIP_ALWAYS = {'UnsupportedAppUsage', 'FlaggedApi', 'SystemApi', 'TestApi', 'NonNull', 'Nullable', 'IntDef', 'LongDef',
                'StringDef', 'IntRange', 'FloatRange', 'Size', 'ColorInt', 'ColorLong', 'HalfFloat', 'AnyThread', 'Px',
                'SuppressAutoDoc', 'CallSuper', 'MainThread', 'WorkerThread', 'UiThread', 'BinderThread', 'RequiresPermission',
                'SuppressLint', 'VisibleForTesting', 'GuardedBy', 'CriticalNative', 'FastNative', 'DrawableRes', 'StringRes',
                'IdRes', 'LayoutRes', 'AttrRes', 'StyleRes', 'ColorRes', 'DimenRes', 'AnimRes', 'AnimatorRes', 'XmlRes', 'RawRes',
                'MenuRes', 'BoolRes', 'IntegerRes', 'ArrayRes', 'PluralsRes', 'FractionRes', 'AnyRes', 'StyleableRes', 'Dimension',
                'CurrentTimeMillisLong', 'ElapsedRealtimeLong', 'DurationMillisLong', 'BytesLong', 'CheckResult', 'IntegerRes',
                'Keep', 'NonUiContext', 'UiContext', 'DisplayContext', 'SdkConstant', 'Widget', 'RequiresFeature', 'SystemService',
                'ChangeId', 'EnabledAfter', 'EnabledSince', 'Disabled', 'Overridable', 'CallbackExecutor', 'MinSdkVersion',
                'TestApi', 'Hide', 'KeepForWeakReference', 'PermissionManuallyEnforced', 'RequiresNoPermission',
                'EnforcePermission', 'SuppressWarnings_', 'RavenwoodKeepWholeClass', 'RavenwoodKeep', 'IgnoreUnderRavenwood',
                'RemotableViewMethod', 'InspectableProperty', 'ViewDebug.ExportedProperty', 'ExportedProperty', 'ViewDebug.CapturedViewProperty',
                'CapturedViewProperty', 'ViewDebug.IntToString', 'ViewDebug.FlagToString'}


def fetch(repo, path):
    commit = PINS[repo]
    cache = os.path.join(ROOT, '.cache', 'aosp', repo.replace('/', '_'), commit, path)
    if not os.path.exists(cache):
        url = 'https://android.googlesource.com/%s/+/%s/%s?format=TEXT' % (repo, commit, path)
        data = base64.b64decode(urllib.request.urlopen(url).read())
        os.makedirs(os.path.dirname(cache), exist_ok=True)
        open(cache, 'wb').write(data)
    return open(cache, encoding='utf-8').read()


def strip_annotations(src):
    names = set(STRIP_ALWAYS)
    out = []
    for line in src.split('\n'):
        m = re.match(r'\s*import\s+(static\s+)?([\w.]+)\s*;', line)
        if m and m.group(2).startswith(STRIP_PACKAGES):
            names.add(m.group(2).rsplit('.', 1)[1])
            continue
        out.append(line)
    src = '\n'.join(out)
    # imports of types used only as annotations (an @IntDef nested in a class, say)
    kept = []
    for line in src.split('\n'):
        m = re.match(r'\s*import\s+([\w.]+)\.(\w+)\s*;', line)
        if m:
            n = m.group(2)
            uses = [u.start() for u in re.finditer(r'(?<![\w.])' + n + r'\b', src)]
            body = [u for u in uses if src[u - 1:u] == '@' or not src[src.rfind('\n', 0, u) + 1:u].lstrip().startswith('import')]
            if body and all(src[u - 1:u] == '@' for u in body):
                names.add(n)
                continue
        kept.append(line)
    src = '\n'.join(kept)
    res, i = [], 0
    pat = re.compile(r'@(?:[\w.]+\.)?(\w+)')
    while True:
        m = pat.search(src, i)
        if not m:
            res.append(src[i:]); break
        # inside comments/javadoc: keep
        line_start = src.rfind('\n', 0, m.start()) + 1
        prefix = src[line_start:m.start()].lstrip()
        qual = src[m.start() + 1:m.end()]
        if (m.group(1) not in names and not (qual + '.').startswith(STRIP_PACKAGES)) or prefix.startswith(('*', '//')) or src[m.start() - 1:m.start()] == '{':
            res.append(src[i:m.end()]); i = m.end(); continue
        res.append(src[i:m.start()])
        j = m.end()
        k = j
        while k < len(src) and src[k] in ' \t': k += 1
        if k < len(src) and src[k] == '(':
            depth, k2 = 0, k
            while k2 < len(src):
                ch = src[k2]
                if ch == '"':
                    k2 += 1
                    while src[k2] != '"': k2 += 2 if src[k2] == '\\' else 1
                elif ch == '(': depth += 1
                elif ch == ')':
                    depth -= 1
                    if depth == 0: break
                k2 += 1
            j = k2 + 1
        while j < len(src) and src[j] in ' \t': j += 1
        i = j
    return ''.join(res)


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('paths', nargs='+')
    ap.add_argument('--repo', default='platform/frameworks/base')
    ap.add_argument('--to', default=os.path.join(ROOT, 'runtime', 'framework'))
    ap.add_argument('--strip-prefix', default=None, help='source path prefix before the package (default: up to /java/)')
    a = ap.parse_args()
    for p in a.paths:
        src = strip_annotations(fetch(a.repo, p))
        rel = p.split('/java/', 1)[1] if a.strip_prefix is None and '/java/' in p else p[len(a.strip_prefix or ''):]
        dst = os.path.join(a.to, rel)
        os.makedirs(os.path.dirname(dst), exist_ok=True)
        header = '// Ported from AOSP %s@%s %s (Apache License 2.0); annotations removed.\n' % (a.repo, PINS[a.repo][:12], p)
        open(dst, 'w', encoding='utf-8').write(header + src)
        print(dst)


if __name__ == '__main__':
    main()
