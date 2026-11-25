#!/bin/bash
# Runs the ARMv7 instruction test under qemu-arm and in the emulator, and compares the results word for word.
#   tests/armv7/check.sh [emulator dir, default native/prebuilt]   (needs clang with the ARM target, lld, qemu-arm, node)
set -euo pipefail
cd "$(dirname "$0")"
[ -f build/t7-arm.so ] && [ -f build/t7-thumb.so ] || ./build.sh
emu=${1:-../../native/prebuilt}
status=0
for mode in thumb arm; do
  qemu-arm "build/t7-$mode.qemu" > "build/t7-$mode.ref"
  node run.mjs "$emu" "build/t7-$mode.so" > "build/t7-$mode.out"
  if cmp -s "build/t7-$mode.ref" "build/t7-$mode.out"; then
    echo "$mode: same as qemu ($(wc -l < "build/t7-$mode.ref") results)"
  else
    echo "$mode: differs from qemu"; diff "build/t7-$mode.ref" "build/t7-$mode.out" | head -20; status=1
  fi
done
exit $status
