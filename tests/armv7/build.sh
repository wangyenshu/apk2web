#!/bin/bash
# Builds the ARMv7 emulator test (Thumb-2 and ARM variants) and the qemu-arm reference executables.
#   tests/armv7/build.sh   -> build/t7-{thumb,arm}.so and build/t7-{thumb,arm}.qemu
set -euo pipefail
cd "$(dirname "$0")"
mkdir -p build
F=(--target=armv7a-linux-androideabi -mcpu=cortex-a15 -mfpu=neon-vfpv4 -mfloat-abi=softfp -O2 -ffreestanding -fno-math-errno -fno-builtin -Wall -Wno-unused-function)
for mode in thumb arm; do
  clang "${F[@]}" "-m$mode" -fPIC -shared -nostdlib -fuse-ld=lld -Wl,-soname,libt7.so -o "build/t7-$mode.so" t7.c
  clang "${F[@]}" "-m$mode" -static -nostdlib -fuse-ld=lld -o "build/t7-$mode.qemu" t7.c t7_qemu.c
done
ls -la build
