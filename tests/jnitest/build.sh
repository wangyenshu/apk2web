#!/bin/bash
# Builds tests/jnitest/jnitest.apk: the JNI conformance test app for apk2web.
#   tests/jnitest/build.sh [android.jar] [build-tools dir] [org.apache.http.legacy.jar]
# Needs a JDK, clang with the ARM target and lld, and the Android build tools (aapt2, d8).
set -euo pipefail
cd "$(dirname "$0")"
AJ=${1:-${ANDROID_JAR:?pass android.jar or set ANDROID_JAR}}
BT=${2:-${BUILD_TOOLS:?pass the build-tools directory or set BUILD_TOOLS}}
# the Apache HTTP client (out of android.jar since API 23): the SDK's optional/org.apache.http.legacy.jar
LEGACY=${3:-${HTTP_LEGACY_JAR:-$(dirname "$AJ")/optional/org.apache.http.legacy.jar}}
[ -f "$LEGACY" ] || { echo "no $LEGACY: pass org.apache.http.legacy.jar (any platform's optional/ folder) or set HTTP_LEGACY_JAR" >&2; exit 1; }
JH=${JAVA_HOME:-$(dirname "$(dirname "$(readlink -f "$(command -v javac)")")")}
B=build
rm -rf "$B" && mkdir -p "$B/classes" "$B/apk/lib/armeabi"
clang --target=armv5te-linux-androideabi -marm -mfloat-abi=soft -O2 -fPIC -shared -nostdlib -fuse-ld=lld \
  -nostdlibinc -isystem jni/include -I"$JH/include" -I"$JH/include/linux" -Wl,-soname,libjnitest.so -o "$B/apk/lib/armeabi/libjnitest.so" jni/jnitest.c
"$BT/aapt2" compile --dir res -o "$B/res.zip"
"$BT/aapt2" link --no-version-vectors -I "$AJ" --manifest AndroidManifest.xml --java "$B/gen" -A assets -o "$B/base.apk" "$B/res.zip"
mapfile -t SRC < <(find src "$B/gen" -name '*.java')
javac -nowarn -Xlint:-options --release 8 -cp "$AJ:$LEGACY" -d "$B/classes" "${SRC[@]}"
mapfile -t CLS < <(find "$B/classes" -name '*.class')
"$BT/d8" --min-api 14 --lib "$AJ" --lib "$LEGACY" --output "$B/apk" "${CLS[@]}"
cp "$B/base.apk" jnitest.apk
(cd "$B/apk" && zip -q -r ../../jnitest.apk classes.dex lib)
ls -la jnitest.apk
