#!/usr/bin/env python3
"""Names the Java methods in stack traces from a converted app: frames like Throwable.java:N (TeaVM) and
app.wasm:1:N (the browser) become Class.method, using the site's symbols.txt.
    tools/symbolize.py SITE_DIR [LOG]   (LOG defaults to stdin)"""
import bisect, os, re, sys

def load(site):
    starts, names, at = [], [], 0
    for line in open(os.path.join(site, 'symbols.txt'), encoding='utf-8'):
        d, _, n = line.rstrip('\n').partition('\t')
        if not n: continue
        at += int(d, 36)
        starts.append(at); names.append(n.replace('::', '.'))
    return starts, names

def main():
    starts, names = load(sys.argv[1])
    text = open(sys.argv[2], encoding='utf-8', errors='replace').read() if len(sys.argv) > 2 else sys.stdin.read()
    def name(off):
        k = bisect.bisect_right(starts, off) - 1
        return names[k] if k >= 0 else '?'
    text = re.sub(r'at java\.lang\.Throwable\$FakeClass\.fakeMethod\(Throwable\.java:(\d+)\)',
                  lambda m: 'at %s(app.wasm:%s)' % (name(int(m.group(1))), m.group(1)), text)
    text = re.sub(r'at (\$?[\w$.]+) \((\S*app\.wasm):1:(\d+)\)', lambda m: 'at %s (%s:%s)' % (name(int(m.group(3))), m.group(2), m.group(3)), text)
    sys.stdout.write(text)

if __name__ == '__main__':
    main()
