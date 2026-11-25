// Runs a test library's run_all() in the apk2web ARM emulator and prints the result words (one hex word per line).
//   node run.mjs <emulator dir with emu.mjs> <lib.so>
import { readFileSync } from 'node:fs';
import { pathToFileURL } from 'node:url';
import path from 'node:path';
const [dir, so] = process.argv.slice(2);
const createEmu = (await import(pathToFileURL(path.resolve(dir, 'emu.mjs')).href)).default;
const M = await createEmu({
  log: (s) => process.stderr.write('log: ' + s + '\n'),
  onFatal: (s) => { process.stderr.write('FATAL: ' + s + '\n'); process.exit(2); },
  locateFile: (p) => path.resolve(dir, p),
  needLibrary: () => null,
});
M._emu_init();
const bytes = readFileSync(so);
const p = M._malloc(bytes.length), name = M._malloc(64);
M.HEAPU8.set(bytes, p);
M.stringToUTF8('libt7.so', name, 64);
M._emu_load_lib(p, bytes.length, name);
const sym = M._malloc(16);
M.stringToUTF8('run_all', sym, 16);
const fn = M._emu_sym(sym);
const out = M._malloc(4 << 16), args = M._malloc(16);
M.HEAP32[args >> 2] = out;
const t0 = Date.now();
const n = M._emu_call(fn, 1, args);
const lines = [];
for (let i = 0; i < n; i++) lines.push((M.HEAP32[(out >> 2) + i] >>> 0).toString(16).padStart(8, '0'));
process.stdout.write(lines.join('\n') + '\n');
process.stderr.write(`${n} words, ${M._emu_icount()} instructions, ${Date.now() - t0} ms\n`);
