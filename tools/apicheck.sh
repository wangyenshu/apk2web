#!/bin/bash
# Compiles the framework and lists its methods whose return types differ from android.jar's (see ApiCheck.java).
#   tools/apicheck.sh path/to/android.jar
set -euo pipefail
cd "$(dirname "$0")/.."
AJ=${1:?pass android.jar}
T=$(mktemp -d)
trap 'rm -rf "$T"' EXIT
CP=$(python3 - "$T" <<'PY'
import argparse, importlib.util, sys
s = importlib.util.spec_from_file_location('apk2web', 'apk2web.py')
m = importlib.util.module_from_spec(s)
s.loader.exec_module(m)
t = sys.argv[1]
d = m.Build(argparse.Namespace(inputs=['x'], out=t + '/o', obb=None, work=t + '/w', cache=None, maven_args=None)).deps()
print(':'.join([d['classlib']] + d['jso'] + d['platform'] + d['bundled']))
PY
)
CP=$(printf '%s\n' "$CP" | tail -1)   # (the last line: Maven may have logged before it)
ASM=$(tr ':' '\n' < tools/jarproc/target/cp.txt | grep '/asm-9' | head -1)
find runtime/framework runtime/gen-placeholder -name '*.java' > "$T/src.txt"
javac -nowarn -encoding UTF-8 --release 17 -cp "$CP" -d "$T/fw" @"$T/src.txt"
javac -cp "$ASM" -d "$T/tool" tools/apicheck/ApiCheck.java
java -cp "$T/tool:$ASM" ApiCheck "$AJ" "$T/fw" | grep -v 'Ljava/io/File\(InputStream\|Descriptor\);' || true
