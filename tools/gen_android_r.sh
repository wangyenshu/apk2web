#!/bin/bash
# Regenerates runtime/framework/android/R.java (every public framework resource id) from an SDK android.jar.
#   tools/gen_android_r.sh path/to/android.jar API-level
set -euo pipefail
cd "$(dirname "$0")/.."
JAR=${1:?pass android.jar}
API=${2:?pass the API level of that android.jar}
T=$(mktemp -d)
trap 'rm -rf "$T"' EXIT
javac -d "$T" tools/genr/GenR.java
java -cp "$T" GenR "$JAR" "$API" > runtime/framework/android/R.java
echo "wrote runtime/framework/android/R.java"
