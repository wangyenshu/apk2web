#!/bin/bash
# Regenerates runtime/framework-gen: Java declarations of every API class (all levels) that runtime/framework lacks,
# as the SDK declares them. See tools/jarproc/src/main/java/jp/GenApi.java.
#   tools/genapi.sh path/to/android.jar [older android.jar ...]
# Declarations come from the newest SDK jar that has the class: AOSP's current android.jar (pinned, downloaded into
# .cache and checked), then the jars given, then the API 16 jar the linker uses, then older AOSP jars (pinned) for
# classes removed before; levels from api-versions.xml.
set -euo pipefail
cd "$(dirname "$0")/.."
AJ=${1:?pass android.jar}
shift
pinned() {   # pinned() file sha256 path-in-prebuilts/sdk
  if [ ! -f "$1" ]; then
    mkdir -p .cache
    curl -fsS "https://android.googlesource.com/platform/prebuilts/sdk/+/3af7c93524be6f51e092b87b17f009f13ee98b43/$3?format=TEXT" \
      | base64 -d > "$1.tmp"
    echo "$2  $1.tmp" | sha256sum -c --quiet
    mv "$1.tmp" "$1"
  fi
}
AV=.cache/api-versions-3af7c935.xml
pinned "$AV" 529b2d3d3eeb4c303438eb2f496f7f6ec9ecb5cecd1aa090001e8659ae0b00c9 current/public/data/api-versions.xml
CUR=.cache/android-current.jar
pinned "$CUR" 2680325c498b0625da42fe1269973da19cd3e4e70e65574e919fd205c1e6d4d2 current/public/android.jar
# classes removed before the jars below (their last level) and the test libraries
OLD=()
while read -r sha path; do
  f=.cache/sdk/$(echo "$path" | tr / _)
  pinned "$f" "$sha" "$path"
  OLD+=("$f")
done <<'LIST'
2f0e6e4ec44e95beefe0c041541afcec4f55f8b964b33599a987efb6e972f990 30/public/android.jar
0f56cf4fd82f1c3888039e9bd38eb2326b00cd080e437875d1b7df95b81abdf6 20/public/android.jar
f5f6d7a8bece2c0e7b985545b30b80ed04ca781822138346dcc5e914c344e3a9 11/public/android.jar
327195c8647eb254bb74acc6648474955120cee561a3ca6c58c026454aa11f5e 8/public/android.jar
53de7c22cb3f74bb4ed7b9fc1416571c352fe8fedb6671ba40725d838618f75b 3/public/android.jar
c52414232459ed5d5b171b718f1b03e1a5d9735388efe2270942f7ee6f263db0 current/public/android.test.runner.jar
e6a5cf1c56e6de3720a4469663c970a214a3cddbbea493627f89ae0a464e0067 current/public/android.test.base.jar
eaf3a69baad9cb0fa0245cc21ff4d31b6b2d7bda58aa0560d45a8e9445a809d0 current/public/android.test.mock.jar
LIST
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
print('TEMPLATES=' + ':'.join(d['templates']))
print('LINKER=' + b.linker())
PY
get() { grep "^$1=" "$T/deps.txt" | tail -1 | cut -d= -f2-; }
# the framework refers to some generated declarations: it compiles against the SDK's android.jar for them, and the
# previous generation's JDK renames (a2w.jdk.*) as a source path whose classes are not written out
find runtime/framework runtime/gen-placeholder -name '*.java' > "$T/src.txt"
mkdir -p "$T/gensrc" && ln -s "$PWD/runtime/framework-gen/a2w" "$T/gensrc/a2w"
javac -nowarn -encoding UTF-8 --release 17 -cp "$(get CP):$CUR" -sourcepath "$T/gensrc" -implicit:none -d "$T/fw" @"$T/src.txt"
args=(--fw "$T/fw" --jdk "$(get CLASSLIB)" --versions "$AV" --out "${GEN_OUT:-runtime/framework-gen}" --absent tools/genapi-absent.txt --sdk-jar "$CUR" --sdk-jar "$AJ")
for j in "$@"; do args+=(--sdk-jar "$j"); done
IFS=: read -r -a t <<< "$(get TEMPLATES)"
for l in "${t[@]}"; do [ -n "$l" ] && args+=(--sdk-jar "$l"); done
for j in "${OLD[@]}"; do args+=(--sdk-jar "$j"); done
IFS=: read -r -a libs <<< "$(get LIBS)"
for l in "${libs[@]}"; do [ -n "$l" ] && args+=(--lib "$l"); done
IFS=: read -r -a applibs <<< "$(get APPLIBS)"
for l in "${applibs[@]}"; do [ -n "$l" ] && args+=(--applib "$l"); done
java -cp "$(get LINKER)" jp.GenApi "${args[@]}"
# the generated sources must compile with the framework
find runtime/framework "${GEN_OUT:-runtime/framework-gen}" runtime/gen-placeholder -name '*.java' > "$T/all.txt"
javac -nowarn -Xmaxerrs 100000 -encoding UTF-8 --release 17 -cp "$(get CP)" -d "$T/all" @"$T/all.txt"
