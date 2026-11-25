#!/bin/bash
# Writes runtime/framework/a2w/UnicodeData.java (Unicode blocks, scripts, bidi classes, emoji and other properties
# TeaVM's java.lang.Character lacks) from the tables of the JDK this runs on: jp.UnicodeTables.
set -e
cd "$(dirname "$0")/.."
OUT=$(mktemp -d)
javac -d "$OUT/classes" tools/jarproc/src/main/java/jp/UnicodeTables.java
java --add-opens java.base/java.lang=ALL-UNNAMED -cp "$OUT/classes" jp.UnicodeTables "$OUT/src"
cp "$OUT/src/a2w/UnicodeData.java" runtime/framework/a2w/UnicodeData.java
rm -rf "$OUT"
