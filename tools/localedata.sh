#!/bin/bash
# Writes runtime/framework/a2w/LocaleData.java (ISO language and country codes, which TeaVM's java.util.Locale lacks)
# from the tables of the JDK this runs on: jp.LocaleTables.
set -e
cd "$(dirname "$0")/.."
OUT=$(mktemp -d)
javac -d "$OUT/classes" tools/jarproc/src/main/java/jp/LocaleTables.java
java -cp "$OUT/classes" jp.LocaleTables "$OUT/src"
cp "$OUT/src/a2w/LocaleData.java" "${LOCALE_OUT:-runtime/framework/a2w/LocaleData.java}"
rm -rf "$OUT"
