#!/bin/bash
# Regenerates docs/API.md's tables and docs/api/*.md: every public class, method and field of the Android API, at every
# API level, with what a converted app gets for it (implemented, fixed answer, or missing) and the levels it exists at.
# See tools/jarproc/src/main/java/jp/ApiReport.java.
#   tools/apireport.sh path/to/android.jar [api-versions.xml]
# api-versions.xml: the SDK's platforms/android-N/data/api-versions.xml; else the one beside android.jar's data/
# folder; else a pinned copy from AOSP (prebuilts/sdk, current/public/data), downloaded into .cache and checked.
set -euo pipefail
cd "$(dirname "$0")/.."
AJ=${1:?pass android.jar}
AV=${2:-$(dirname "$AJ")/data/api-versions.xml}
if [ ! -f "$AV" ]; then
  AV=.cache/api-versions-3af7c935.xml
  if [ ! -f "$AV" ]; then
    mkdir -p .cache
    curl -fsS "https://android.googlesource.com/platform/prebuilts/sdk/+/3af7c93524be6f51e092b87b17f009f13ee98b43/current/public/data/api-versions.xml?format=TEXT" \
      | base64 -d > "$AV.tmp"
    echo "529b2d3d3eeb4c303438eb2f496f7f6ec9ecb5cecd1aa090001e8659ae0b00c9  $AV.tmp" | sha256sum -c --quiet
    mv "$AV.tmp" "$AV"
  fi
fi
T=$(mktemp -d)
trap 'rm -rf "$T"' EXIT
python3 - "$T" > "$T/deps.txt" <<'PY'
import argparse, importlib.util, sys
s = importlib.util.spec_from_file_location('apk2web', 'apk2web.py')
m = importlib.util.module_from_spec(s)
s.loader.exec_module(m)
t = sys.argv[1]
b = m.Build(argparse.Namespace(inputs=['x'], out=t + '/o', obb=None, work=t + '/w', cache=None, maven_args=None))
d = b.deps()
print('CP=' + ':'.join([d['classlib']] + d['jso'] + d['platform'] + d['bundled']))
print('CLASSLIB=' + d['classlib'])
print('LIBS=' + ':'.join(d['platform']))
print('APPLIBS=' + ':'.join(d['bundled']))
print('LINKER=' + b.linker())
PY
get() { grep "^$1=" "$T/deps.txt" | tail -1 | cut -d= -f2-; }
find runtime/framework runtime/framework-gen runtime/gen-placeholder -name '*.java' > "$T/src.txt"
javac -nowarn -encoding UTF-8 --release 17 -cp "$(get CP)" -d "$T/fw" @"$T/src.txt"
args=(--android "$AJ" --fw "$T/fw" --jdk "$(get CLASSLIB)" --versions "$AV" --out docs)
IFS=: read -r -a libs <<< "$(get LIBS)"
for l in "${libs[@]}"; do [ -n "$l" ] && args+=(--lib "$l"); done
IFS=: read -r -a applibs <<< "$(get APPLIBS)"
for l in "${applibs[@]}"; do [ -n "$l" ] && args+=(--applib "$l"); done
[ -n "${A2W_TSV:-}" ] && args+=(--tsv "$A2W_TSV")   # every member with its status, for analysis
java -cp "$(get LINKER)" jp.ApiReport "${args[@]}"
# the summary table goes into docs/API.md between its markers
python3 - <<'PY'
import re
doc = open('docs/API.md').read()
table = open('docs/api-summary.md').read()
hidden = open('docs/api-hidden.md').read()
levels = open('docs/api-levels.md').read()
conflicts = open('docs/api-conflicts.md').read()
doc = re.sub(r'(<!-- conflicts -->\n).*?(<!-- /conflicts -->)', lambda m: m.group(1) + conflicts + m.group(2), doc, flags=re.S)
doc = re.sub(r'(<!-- levels -->\n).*?(<!-- /levels -->)', lambda m: m.group(1) + levels + m.group(2), doc, flags=re.S)
doc = re.sub(r'(<!-- summary -->\n).*?(<!-- /summary -->)', lambda m: m.group(1) + table + m.group(2), doc, flags=re.S)
doc = re.sub(r'(<!-- hidden -->\n).*?(<!-- /hidden -->)', lambda m: m.group(1) + hidden + m.group(2), doc, flags=re.S)
open('docs/API.md', 'w').write(doc)
PY
rm -f docs/api-summary.md docs/api-hidden.md docs/api-levels.md docs/api-conflicts.md
