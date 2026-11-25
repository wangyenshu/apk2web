#!/bin/bash
# Build the ARM emulator (emu.mjs + emu.wasm) with Emscripten.
set -e
OUT=$(realpath -m "${1:-$(dirname "$0")}")
cd "$(dirname "$0")"
mkdir -p "$OUT"
# use emcc from PATH, or from an emsdk checkout named by $EMSDK
# shellcheck disable=SC1091
if ! command -v emcc >/dev/null 2>&1 && [ -n "${EMSDK:-}" ]; then source "$EMSDK/emsdk_env.sh" >/dev/null 2>&1; fi
command -v emcc >/dev/null 2>&1 || { echo "emcc not found: install Emscripten (emsdk) or set EMSDK" >&2; exit 1; }
OPT=${OPT:--O3}
Z=(zlib/adler32.c zlib/compress.c zlib/crc32.c zlib/deflate.c zlib/gzclose.c zlib/gzlib.c zlib/gzread.c zlib/gzwrite.c zlib/infback.c
   zlib/inffast.c zlib/inflate.c zlib/inftrees.c zlib/trees.c zlib/uncompr.c zlib/zutil.c)
emcc "$OPT" -g1 -Wall -Wno-unused-function -Izlib -DHAVE_UNISTD_H cpu.c elf.c host.c softfloat.c gl.c jni.c main.c "${Z[@]}" \
  --js-library library_gltable.js --js-library library_gl.js --js-library library_jni.js \
  -sALLOW_MEMORY_GROWTH=1 -sINITIAL_MEMORY=134217728 -sMAXIMUM_MEMORY=2147483648 -sSTACK_SIZE=8388608 \
  -sMODULARIZE=1 -sEXPORT_ES6=1 -sEXPORT_NAME=createEmu -sFORCE_FILESYSTEM=1 -lidbfs.js \
  -sEXPORTED_FUNCTIONS=_malloc,_free \
  -sEXPORTED_RUNTIME_METHODS=FS,IDBFS,UTF8ToString,stringToUTF8,lengthBytesUTF8,HEAPU8,HEAP32,HEAPF32 \
  -o "$OUT/emu.mjs"
ls -la "$OUT/emu.mjs" "$OUT/emu.wasm"
