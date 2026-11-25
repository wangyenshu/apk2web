// Host side of apk2web: everything the TeaVM-compiled Android framework (HW.* in @JSBody scripts) and the ARM
// emulator running the app's native libraries need from the browser. The app is described by config.json.
import createEmu from './emu.js';
import { load as loadTeaVM } from './wasm-gc-module-runtime.js';
import { createGLES } from './gles.js';

let W = 800, H = 480;            // the device screen in pixels (config.screen)
let PKG = 'app', APK_PATH = '/data/app/app.apk', LABEL = 'The app';
let CONFIG = {};
const HW = (globalThis.HW = {});
HW.config = { versionName: '', versionCode: 0, debug: false };
const LOG_LEVELS = ['', '', 'V', 'D', 'I', 'W', 'E', 'A'];
HW.verbose = /[?&]log/.test(location.search);

const SYNC_LOG = /[?&]synclog/.test(location.search);   // debugging: each line also POSTed at once (seen while a native call runs)
HW.log = (l, s) => {
  if (SYNC_LOG && l >= 4) { try { const x = new XMLHttpRequest(); x.open('POST', '__log', false); x.send(String(s)); } catch {} }
  if (FAKE_FRAME.test(s)) { symbolized(s).then((t) => HW.log(l, t)); return; }
  if (l >= 6) console.error(s);
  else if (l >= 5) console.warn(s);
  else if (HW.verbose || l >= 4) console.log(LOG_LEVELS[l] + ' ' + s);
};
// Java stack frames: TeaVM gives code offsets in app.wasm; symbols.txt (from the build) names the method at each
const FAKE_FRAME = /at java\.lang\.Throwable\$FakeClass\.fakeMethod\(Throwable\.java:(\d+)\)/;
let symbolTable = null;
function symbolized(text) {
  symbolTable ??= fetch('symbols.txt').then((r) => (r.ok ? r.text() : '')).catch(() => '').then((t) => {
    const starts = [], names = [];
    let at = 0;
    for (const line of t.split('\n')) {
      const tab = line.indexOf('\t');
      if (tab < 0) continue;
      at += parseInt(line.slice(0, tab), 36);   // offsets are delta-coded
      starts.push(at); names.push(line.slice(tab + 1));
    }
    return { starts, names };
  });
  return symbolTable.then(({ starts, names }) => text.replace(new RegExp(FAKE_FRAME.source, 'g'), (all, off) => {
    let lo = 0, hi = starts.length - 1, k = -1;
    while (lo <= hi) { const mid = (lo + hi) >> 1; if (starts[mid] <= +off) { k = mid; lo = mid + 1; } else hi = mid - 1; }
    if (k < 0) return all;
    const n = names[k], c = n.indexOf('::');
    return 'at ' + (c < 0 ? n : n.slice(0, c) + '.' + n.slice(c + 2)) + '(app.wasm:' + off + ')';
  }));
}
HW.fatal = (s) => { console.error('FATAL ' + s); showMessage(LABEL + ' stopped: ' + String(s).split('\n')[0]); };
HW.exitApp = () => { HW.exited = true; showMessage(LABEL + ' has closed. Click to start it again.', () => location.reload()); };
HW.openUrl = (u) => { try { window.open(u, '_blank', 'noopener'); } catch (e) { console.warn(e); } };
// ACTION_SEND with nothing in the app to take it: the browser's share sheet, else the clipboard
HW.share = (text, title) => {
  if (navigator.share) navigator.share({ text, title: title || undefined }).catch(() => {});
  else if (navigator.clipboard) navigator.clipboard.writeText(text).catch(() => {});
};
HW.deviceId = () => {
  let id = null;
  const key = 'apk2web.deviceId.' + PKG;
  try { id = localStorage.getItem(key); } catch {}
  if (!id) {
    const b = new Uint8Array(8); crypto.getRandomValues(b);
    id = Array.from(b, (x) => x.toString(16).padStart(2, '0')).join('');
    try { localStorage.setItem(key, id); } catch {}
  }
  return id;
};

let overlay;
function showMessage(text, onClick) {
  if (!overlay) {
    overlay = document.createElement('div');
    overlay.style.cssText = 'position:fixed;inset:0;display:flex;align-items:center;justify-content:center;color:#ccc;' +
      'font:16px sans-serif;background:rgba(0,0,0,.85);z-index:10;text-align:center;padding:2em;white-space:pre-wrap';
    document.body.appendChild(overlay);
  }
  overlay.textContent = text;
  overlay.onclick = onClick || null;
  overlay.style.cursor = onClick ? 'pointer' : 'default';
}

// ------------------------------------------------------------------ screen
const stage = document.getElementById('stage');
const glCanvas = document.getElementById('gl');
const uiCanvas = document.getElementById('ui');
const ui = uiCanvas.getContext('2d');
let cssScale = 1, backing = 1;
ui.__base = [1, 0, 0, 1, 0, 0];

function layout() {
  const vw = window.innerWidth, vh = window.innerHeight;
  cssScale = Math.min(vw / W, vh / H);
  const cw = Math.floor(W * cssScale), ch = Math.floor(H * cssScale);
  stage.style.width = cw + 'px'; stage.style.height = ch + 'px';
  stage.style.left = Math.floor((vw - cw) / 2) + 'px'; stage.style.top = Math.floor((vh - ch) / 2) + 'px';
  const dpr = window.devicePixelRatio || 1;
  backing = Math.max(1, Math.min(4, (cw * dpr) / W));
  const bw = Math.round(W * backing), bh = Math.round(H * backing);
  if (uiCanvas.width !== bw || uiCanvas.height !== bh) { uiCanvas.width = bw; uiCanvas.height = bh; }
  ui.__base = [bw / W, 0, 0, bh / H, 0, 0];
  ui.setTransform(...ui.__base);
  placeGL();
  for (const f of overlaysToPlace) f();
  if (HW.java) HW.java.onResize();
}
const overlaysToPlace = new Set();
window.addEventListener('resize', layout);

HW.screen = () => ui;
HW.clearScreen = () => {
  ui.setTransform(1, 0, 0, 1, 0, 0);
  ui.clearRect(0, 0, uiCanvas.width, uiCanvas.height);
  ui.setTransform(...ui.__base);
};
let glShown = false;
HW.showGL = (on) => { if (on !== glShown) { glShown = on; glCanvas.style.visibility = on ? 'visible' : 'hidden'; } };
let glRect = [0, 0, W, H];
function placeGL() {
  const [x, y, w, h] = glRect;
  Object.assign(glCanvas.style, { left: x * cssScale + 'px', top: y * cssScale + 'px', width: w * cssScale + 'px', height: h * cssScale + 'px' });
}
HW.gl = {
  place(x, y, w, h) {
    glRect = [x, y, w, h];
    if (glCanvas.width !== w || glCanvas.height !== h) { glCanvas.width = w; glCanvas.height = h; }
    placeGL();
  },
};
let frameRequested = false;
HW.requestFrame = () => {
  if (frameRequested) return;
  frameRequested = true;
  const run = () => { frameRequested = false; HW.java.onFrame(); HW.tickNative(); };
  // hidden pages get no animation frames; keep layout (and the work waiting on it) going slowly
  if (document.hidden) setTimeout(run, 100); else requestAnimationFrame(run);
};

// ------------------------------------------------------------------ APK (zip) access
let apk;          // Uint8Array of the whole APK
const apkEntries = new Map();
function parseZip(u8, prefix = '') {
  const dv = new DataView(u8.buffer, u8.byteOffset, u8.byteLength);
  let eocd = -1;
  for (let i = u8.length - 22; i >= Math.max(0, u8.length - 65557); i--) if (dv.getUint32(i, true) === 0x06054b50) { eocd = i; break; }
  if (eocd < 0) throw new Error('zip: no end of central directory');
  const count = dv.getUint16(eocd + 10, true);
  let p = dv.getUint32(eocd + 16, true);
  const dec = new TextDecoder();
  for (let i = 0; i < count; i++) {
    if (dv.getUint32(p, true) !== 0x02014b50) throw new Error('zip: bad central directory');
    const method = dv.getUint16(p + 10, true), csize = dv.getUint32(p + 20, true), usize = dv.getUint32(p + 24, true);
    const nlen = dv.getUint16(p + 28, true), xlen = dv.getUint16(p + 30, true), clen = dv.getUint16(p + 32, true);
    const lho = dv.getUint32(p + 42, true);
    const name = dec.decode(u8.subarray(p + 46, p + 46 + nlen));
    apkEntries.set(prefix + name, { method, csize, usize, lho, data: -1, src: u8 });
    p += 46 + nlen + xlen + clen;
  }
}
function entryData(e) {
  if (e.data < 0) {
    const dv = new DataView(e.src.buffer, e.src.byteOffset);
    e.data = e.lho + 30 + dv.getUint16(e.lho + 26, true) + dv.getUint16(e.lho + 28, true);
  }
  return e.data;
}
HW.apk = {
  entry: (n) => apkEntries.get(n) || null,
  raw(n) {
    const e = apkEntries.get(n);
    if (!e) return null;
    const o = entryData(e);
    return new Int8Array(e.src.buffer, e.src.byteOffset + o, e.csize);
  },
  /** names directly under a directory prefix ("assets/x/"), files and subdirectories, newline-separated */
  list(prefix) {
    const out = new Set();
    for (const n of apkEntries.keys()) {
      if (!n.startsWith(prefix) || n.length === prefix.length) continue;
      const rest = n.slice(prefix.length), k = rest.indexOf('/');
      out.add(k < 0 ? rest : rest.slice(0, k));
    }
    return [...out].join('\n');
  },
  async bytes(n) {
    const e = apkEntries.get(n);
    if (!e) return null;
    const raw = new Uint8Array(e.src.buffer, e.src.byteOffset + entryData(e), e.csize);
    if (e.method === 0) return raw;
    const ds = new Blob([raw]).stream().pipeThrough(new DecompressionStream('deflate-raw'));
    return new Uint8Array(await new Response(ds).arrayBuffer());
  },
};

// ------------------------------------------------------------------ resources
HW.resStr = (id) => {
  const loc = HW.locale && HW.res.locales[HW.locale];
  if (loc && loc.strings && id in loc.strings) return loc.strings[id];
  const v = HW.res.strings[id];
  return v == null ? null : v;
};
HW.resArr = (id) => {
  const loc = HW.locale && HW.res.locales[HW.locale];
  const v = loc && loc.arrays && id in loc.arrays ? loc.arrays[id] : HW.res.arrays[id];
  return v == null ? null : v;
};
HW.resPlural = (id) => {
  const loc = HW.locale && HW.res.locales[HW.locale];
  const v = loc && loc.plurals && id in loc.plurals ? loc.plurals[id] : HW.res.plurals && HW.res.plurals[id];
  return v == null ? null : v;
};
let resIds;
HW.resId = (t, n) => {
  if (!resIds) {
    resIds = new Map();
    for (const [id, [ty, nm]] of Object.entries(HW.res.names)) resIds.set(ty + '/' + nm, id | 0);
  }
  return resIds.get(t + '/' + n) || 0;
};

// ------------------------------------------------------------------ intents: manifest intent filters (IntentFilter.match)
function componentsOf(kind) {
  return kind === 'activities' ? HW.res.activities || [] : (HW.res.components && HW.res.components[kind]) || [];
}
const mimeMatch = (want, have) => {
  if (!want || !have) return false;
  const [wt, ws] = want.split('/'), [ht, hs] = have.split('/');
  return (ht === '*' || wt === '*' || ht === wt) && (hs === '*' || ws === '*' || hs === ws);
};
function filterMatches(f, action, cats, scheme, host, type) {
  if (action ? !f.a.includes(action) : !f.a.length) return false;
  for (const c of cats) if (c && c !== 'android.intent.category.DEFAULT' && !f.c.includes(c)) return false;
  if (type) {
    if (!f.t.some((t) => mimeMatch(type, t))) return false;
    if (scheme && f.s.length && !f.s.includes(scheme)) return false;
    if (scheme && !f.s.length && scheme !== 'content' && scheme !== 'file') return false;
    return true;
  }
  if (f.t.length) return false;
  if (scheme) return f.s.includes(scheme) && (!f.h.length || f.h.includes(host) || f.h.includes('*'));
  return !f.s.length;
}
// "<class>\u0001..." of the components whose filters accept the intent (no explicit component)
HW.resolveIntent = (kind, action, cats, scheme, host, type) => {
  const cs = cats ? cats.split('\u0001') : [];
  return componentsOf(kind).filter((c) => c.enabled !== false && (c.filters || []).some((f) => filterMatches(f, action, cs, scheme, host, type)))
    .map((c) => c.name).join('\u0001');
};
// a component's class by its manifest name (activity-alias names map to their target)
HW.componentClass = (kind, name) => {
  for (const c of componentsOf(kind)) if (c.name === name || (c.aliases && c.aliases.includes(name))) return c.name;
  return null;
};

// ------------------------------------------------------------------ files: the emulator's MEMFS, persisted in IndexedDB
let FS;   // Emscripten FS of the emulator; Java (a2w.Vfs) and the native libraries share it
const PERSIST = ['/data/data/', '/sdcard/'];
const persisted = (p) => PERSIST.some((r) => p.startsWith(r)) && !p.startsWith('/sdcard/Android/obb/');   // (expansion files come with the page)
let db;
function idb(mode) { return db.transaction('files', mode).objectStore('files'); }
async function openDB() {
  db = await new Promise((res, rej) => {
    const r = indexedDB.open('apk2web:' + PKG, 1);
    r.onupgradeneeded = () => r.result.createObjectStore('files');
    r.onsuccess = () => res(r.result);
    r.onerror = () => rej(r.error);
  });
  const all = await new Promise((res, rej) => {
    const out = [];
    const c = idb('readonly').openCursor();
    c.onsuccess = () => { const cur = c.result; if (!cur) return res(out); out.push([cur.key, cur.value]); cur.continue(); };
    c.onerror = () => rej(c.error);
  });
  return all;
}
const known = new Map();   // persisted path -> mtime written to IndexedDB (-1 for directories)
function restoreFiles(all) {
  all.sort((a, b) => a[0].length - b[0].length);
  for (const [p, v] of all) {
    try {
      if (v.dir) { FS.mkdirTree(p); known.set(p, -1); }
      else {
        FS.mkdirTree(p.slice(0, p.lastIndexOf('/')) || '/');
        FS.writeFile(p, v.data);
        if (v.mtime) FS.utime(p, v.mtime, v.mtime);
        known.set(p, FS.stat(p).mtime.getTime());
      }
    } catch (e) { console.warn('restore ' + p, e); }
  }
}
function walk(dir, out) {
  let names;
  try { names = FS.readdir(dir); } catch { return; }
  for (const n of names) {
    if (n === '.' || n === '..') continue;
    const p = dir + '/' + n;
    let st;
    try { st = FS.stat(p); } catch { continue; }
    if (FS.isDir(st.mode)) { out.set(p, -1); walk(p, out); } else out.set(p, st.mtime.getTime());
  }
}
let flushing = false;
async function flushFiles() {
  if (!db || flushing) return;
  flushing = true;
  try {
    const now = new Map();
    for (const r of PERSIST) walk(r.slice(0, -1), now);
    const store = idb('readwrite');
    for (const [p, m] of now) {
      if (known.get(p) === m) continue;
      if (m === -1) store.put({ dir: true }, p);
      else store.put({ data: FS.readFile(p), mtime: m }, p);
      known.set(p, m);
    }
    for (const p of [...known.keys()]) if (!now.has(p)) { store.delete(p); known.delete(p); }
    await new Promise((res) => { store.transaction.oncomplete = res; store.transaction.onerror = res; });
  } catch (e) { console.warn('save failed', e); }
  flushing = false;
}
let flushTimer = 0;
function scheduleFlush() { if (!flushTimer) flushTimer = setTimeout(() => { flushTimer = 0; flushFiles(); }, 400); }
setInterval(flushFiles, 5000);
window.addEventListener('pagehide', flushFiles);

const norm = (p) => { p = String(p).replace(/\/+/g, '/'); return p.length > 1 && p.endsWith('/') ? p.slice(0, -1) : p; };
function stat(p) { try { return FS.stat(norm(p)); } catch { return null; } }
HW.fs = {
  isFile: (p) => { const s = stat(p); return !!s && FS.isFile(s.mode); },
  isDir: (p) => { const s = stat(p); return !!s && FS.isDir(s.mode); },
  size: (p) => { const s = stat(p); return s && FS.isFile(s.mode) ? s.size : 0; },
  mtime: (p) => { const s = stat(p); return s ? s.mtime.getTime() : 0; },
  rm: (p) => { try { FS.unlink(norm(p)); if (persisted(p)) scheduleFlush(); } catch {} },
  rmdir: (p) => { try { FS.rmdir(norm(p)); if (persisted(p)) scheduleFlush(); } catch {} },
  mkdir: (p) => { try { FS.mkdir(norm(p)); if (persisted(p)) scheduleFlush(); } catch {} },
  list: (p) => { try { return FS.readdir(norm(p)).filter((n) => n !== '.' && n !== '..').join('\n'); } catch { return ''; } },
  read: (p) => { try { const d = FS.readFile(norm(p)); return new Int8Array(d.buffer, d.byteOffset, d.byteLength); } catch { return null; } },
  write(p, d, append) {
    p = norm(p);
    const bytes = d ? new Uint8Array(d.buffer, d.byteOffset, d.byteLength) : new Uint8Array(0);
    try {
      if (append && HW.fs.isFile(p)) {
        const s = FS.open(p, 'a'); FS.write(s, bytes, 0, bytes.length); FS.close(s);
      } else FS.writeFile(p, bytes);
    } catch (e) { console.warn('write ' + p, e); }
    if (persisted(p)) scheduleFlush();
  },
  prefs(name) {
    const d = HW.fs.read('/data/data/' + PKG + '/shared_prefs/' + name + '.json');
    return d ? new TextDecoder().decode(d) : null;
  },
  setPrefs(name, json) {
    FS.mkdirTree('/data/data/' + PKG + '/shared_prefs');
    const b = new TextEncoder().encode(json);
    HW.fs.write('/data/data/' + PKG + '/shared_prefs/' + name + '.json', new Int8Array(b.buffer), false);
  },
};

// ------------------------------------------------------------------ SQLite (sql.js), database files on the shared FS
// Java (android.database.sqlite) runs statements here; a database's file is rewritten from sqlite's image after
// changes, once no transaction is open, so it persists with the app's other files.
let SQL = null;
const sqlDbs = new Map();   // handle -> { db, path, dirty, tx }
let sqlNext = 1, sqlTimer = 0;
function sqlFlush() {
  sqlTimer = 0;
  for (const d of sqlDbs.values()) {
    if (!d.dirty || d.tx > 0 || !d.path) continue;
    try {
      const bytes = d.db.export();
      FS.mkdirTree(d.path.slice(0, d.path.lastIndexOf('/')) || '/');
      FS.writeFile(d.path, bytes);
      d.dirty = false;
      if (persisted(d.path)) scheduleFlush();
    } catch (e) { console.warn('sqlite save ' + d.path, e); }
  }
}
const sqlChanged = (d) => { d.dirty = true; if (!sqlTimer) sqlTimer = setTimeout(sqlFlush, 250); };
window.addEventListener('pagehide', sqlFlush, { capture: true });
document.addEventListener('visibilitychange', () => { if (document.hidden) sqlFlush(); });
HW.sql = {
  available: () => !!SQL,
  // a database file (null/'' path: in memory); returns a handle, or a negative SQLite error code
  open(path, create) {
    if (!SQL) return -1;
    let bytes = null;
    if (path) {
      try { bytes = FS.readFile(norm(path)); } catch { if (!create) return -14; }   // SQLITE_CANTOPEN
    }
    try {
      const db = new SQL.Database(bytes && bytes.length ? bytes : undefined);
      const h = sqlNext++;
      sqlDbs.set(h, { db, path: path ? norm(path) : null, dirty: !bytes && !!path, tx: 0 });
      if (!bytes && path) sqlFlush();
      return h;
    } catch (e) { console.warn('sqlite open ' + path, e); return -26; }   // SQLITE_NOTADB
  },
  close(h) { const d = sqlDbs.get(h); if (!d) return; d.tx = 0; sqlFlush(); try { d.db.close(); } catch {} sqlDbs.delete(h); },
  setTx(h, depth) { const d = sqlDbs.get(h); if (d) { d.tx = depth; if (!depth && d.dirty) sqlChanged(d); } },
  // runs one statement; args: array of null/number/bigint/string/Uint8Array. Returns
  // { cols, rows, changes, lastId } or { error, code }
  run(h, sql, args, wantRows) {
    const d = sqlDbs.get(h);
    if (!d) return { error: 'database not open', code: 21 };
    let st = null;
    try {
      st = d.db.prepare(sql);
      if (args && args.length) st.bind(args);
      const rows = [];
      let cols = null;
      while (st.step()) { if (wantRows) rows.push(st.get(null, { useBigInt: true })); else break; }
      if (wantRows || st.getColumnNames().length) cols = st.getColumnNames();
      st.free(); st = null;
      const ro = /^\s*(select|pragma\s+\w+\s*$|explain|with\s[\s\S]*\bselect\b(?![\s\S]*\b(insert|update|delete)\b))/i.test(sql);
      const changes = ro ? 0 : d.db.getRowsModified();
      if (!ro) sqlChanged(d);
      let lastId = 0;
      if (!ro && /^\s*(insert|replace)/i.test(sql)) {
        const r = d.db.exec('SELECT last_insert_rowid()');
        lastId = r.length ? Number(r[0].values[0][0]) : 0;
      }
      return { cols: cols || [], rows, changes, lastId };
    } catch (e) {
      if (st) try { st.free(); } catch {}
      const msg = String(e && e.message || e);
      return { error: msg, code: sqlCode(msg) };
    }
  },
  // cell access for Java: type 0 null, 1 integer, 2 float, 3 text, 4 blob
  type(r, i, j) { const v = r.rows[i][j]; return v == null ? 0 : typeof v === 'bigint' ? 1 : typeof v === 'number' ? 2 : typeof v === 'string' ? 3 : 4; },
  long(r, i, j) { const v = r.rows[i][j]; return v == null ? '0' : typeof v === 'bigint' ? v.toString() : typeof v === 'number' ? String(Math.trunc(v)) : typeof v === 'string' ? v : '0'; },
  num(r, i, j) { const v = r.rows[i][j]; return v == null ? 0 : typeof v === 'bigint' ? Number(v) : typeof v === 'number' ? v : typeof v === 'string' ? (parseFloat(v) || 0) : 0; },
  text(r, i, j) {
    const v = r.rows[i][j];
    if (v == null) return null;
    if (v instanceof Uint8Array) return new TextDecoder().decode(v);
    if (typeof v === 'number') return Number.isInteger(v) ? v.toFixed(1) : String(v);
    return String(v);
  },
  blob(r, i, j) {
    const v = r.rows[i][j];
    if (v == null) return null;
    if (v instanceof Uint8Array) return new Int8Array(v.buffer, v.byteOffset, v.byteLength);
    const b = new TextEncoder().encode(String(v));
    return new Int8Array(b.buffer);
  },
};
// SQLite result codes from sql.js error messages (the message is sqlite3_errmsg)
function sqlCode(m) {
  if (/constraint|UNIQUE|NOT NULL|FOREIGN KEY|CHECK/i.test(m)) return 19;
  if (/no such table|no such column|syntax error|incomplete input|already exists|has no column/i.test(m)) return 1;
  if (/database is locked/i.test(m)) return 5;
  if (/not a database|malformed/i.test(m)) return 26;
  if (/readonly/i.test(m)) return 8;
  if (/out of memory/i.test(m)) return 7;
  if (/interrupt/i.test(m)) return 9;
  if (/misuse|bad parameter|column index out of range/i.test(m)) return 21;
  return 1;
}
async function loadSqlite() {
  await new Promise((res, rej) => {
    const s = document.createElement('script');
    s.src = 'sql-wasm-browser.js'; s.onload = res; s.onerror = () => rej(new Error('sql-wasm-browser.js'));
    document.head.appendChild(s);
  });
  SQL = await globalThis.initSqlJs({ locateFile: (f) => f });
}

// ------------------------------------------------------------------ images
HW.decodeImage = async (d, sample) => {
  const blob = new Blob([d]);
  let img = await createImageBitmap(blob, { premultiplyAlpha: 'premultiply', colorSpaceConversion: 'none' });
  if (sample > 1) {
    const w = Math.max(1, Math.floor(img.width / sample)), h = Math.max(1, Math.floor(img.height / sample));
    const s = await createImageBitmap(img, { resizeWidth: w, resizeHeight: h, resizeQuality: 'medium' });
    img.close();
    img = s;
  }
  return img;
};
const newCanvas = (w, h) => {
  const c = typeof OffscreenCanvas !== 'undefined' ? new OffscreenCanvas(w, h) : Object.assign(document.createElement('canvas'), { width: w, height: h });
  return c;
};
function ctxOf(c) {
  if (!c.__ctx) { c.__ctx = c.getContext('2d', { willReadFrequently: false }); c.__ctx.__base = null; }
  return c.__ctx;
}
HW.bmp = {
  create: (w, h) => newCanvas(Math.max(1, w), Math.max(1, h)),
  ctx: (c) => ctxOf(c),
  toCanvas(img) { const c = newCanvas(img.width, img.height); ctxOf(c).drawImage(img, 0, 0); return c; },
  getPixels(img, x, y, w, h) {
    let ctx;
    if (img.getContext) ctx = ctxOf(img);
    else { const c = newCanvas(w, h); ctx = ctxOf(c); ctx.drawImage(img, -x, -y); x = 0; y = 0; }
    const px = ctx.getImageData(x, y, w, h).data, out = new Int32Array(w * h);
    for (let i = 0, j = 0; i < out.length; i++, j += 4) out[i] = (px[j + 3] << 24) | (px[j] << 16) | (px[j + 1] << 8) | px[j + 2];
    return out;
  },
  setPixels(ctx, p, x, y, w, h) {
    const id = new ImageData(w, h), px = id.data;
    for (let i = 0, j = 0; i < p.length; i++, j += 4) { const c = p[i]; px[j] = (c >> 16) & 255; px[j + 1] = (c >> 8) & 255; px[j + 2] = c & 255; px[j + 3] = (c >>> 24) & 255; }
    ctx.putImageData(id, x, y);
  },
  transformed(img, m, sx, sy, sw, sh, dw, dh, filter) {
    const c = newCanvas(dw, dh), ctx = ctxOf(c);
    ctx.imageSmoothingEnabled = !!filter;
    if (m) { ctx.setTransform(m[0], m[1], m[2], m[3], m[4], m[5]); ctx.drawImage(img, sx, sy, sw, sh, 0, 0, sw, sh); ctx.setTransform(1, 0, 0, 1, 0, 0); }
    else ctx.drawImage(img, sx, sy, sw, sh, 0, 0, dw, dh);
    return c;
  },
  erase(ctx, col) {
    ctx.save(); ctx.setTransform(1, 0, 0, 1, 0, 0);
    ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);
    if (col >>> 24) { ctx.fillStyle = css(col); ctx.fillRect(0, 0, ctx.canvas.width, ctx.canvas.height); }
    ctx.restore();
  },
  rgba(img, w, h) {   // Android ARGB_8888 buffer layout: premultiplied R,G,B,A bytes
    const c = img.getContext ? img : HW.bmp.toCanvas(img);
    const px = ctxOf(c).getImageData(0, 0, w, h).data, out = new Int8Array(px.length);
    for (let i = 0; i < px.length; i += 4) { const a = px[i + 3]; out[i] = (px[i] * a + 127) / 255; out[i + 1] = (px[i + 1] * a + 127) / 255; out[i + 2] = (px[i + 2] * a + 127) / 255; out[i + 3] = a; }
    return out;
  },
  // Bitmap.compress: the browser's encoder on a document canvas (synchronous), bytes of the file
  encode(img, w, h, type, q) {
    if (typeof document === 'undefined') return null;
    const c = document.createElement('canvas');
    c.width = w; c.height = h;
    const x = c.getContext('2d');
    if (type === 'image/jpeg') { x.fillStyle = '#000'; x.fillRect(0, 0, w, h); }
    x.drawImage(img, 0, 0);
    const u = c.toDataURL(type, q), k = u.indexOf(',');
    if (k < 0 || u.length <= k + 1) return null;
    const s = atob(u.slice(k + 1)), out = new Int8Array(s.length);
    for (let i = 0; i < s.length; i++) out[i] = s.charCodeAt(i);
    return out;
  },
  // ALPHA_8 bitmaps draw in the paint's color
  tint(img, w, h, col) {
    const c = newCanvas(w, h), x = ctxOf(c);
    x.drawImage(img, 0, 0);
    x.globalCompositeOperation = 'source-in';
    x.fillStyle = css(col); x.fillRect(0, 0, w, h);
    x.globalCompositeOperation = 'source-over';
    return c;
  },
  // extractAlpha: black with the source's alpha, optionally blurred (sigma) into a padded canvas
  alpha(img, w, h, sigma, pad) {
    const c = newCanvas(w + 2 * pad, h + 2 * pad), x = ctxOf(c);
    if (sigma > 0) x.filter = 'blur(' + sigma + 'px)';
    x.drawImage(img, pad, pad);
    x.filter = 'none';
    x.globalCompositeOperation = 'source-in';
    x.fillStyle = '#000'; x.fillRect(0, 0, c.width, c.height);
    x.globalCompositeOperation = 'source-over';
    return c;
  },
  // RGB_565 copies: composited over black
  opaque(img, w, h) {
    const c = newCanvas(w, h), x = ctxOf(c);
    x.fillStyle = '#000'; x.fillRect(0, 0, w, h);
    x.drawImage(img, 0, 0);
    return c;
  },
};

// ------------------------------------------------------------------ fonts
// Android 4.x metrics (Roboto) as fractions of the text size: top, ascent, descent, bottom
const ROBOTO = [-1.0566406, -0.9277344, 0.2441406, 0.2709961];
const fontMetrics = new Map();
function ttfMetrics(u8) {
  try {
    const dv = new DataView(u8.buffer, u8.byteOffset, u8.byteLength);
    const n = dv.getUint16(4), tables = {};
    for (let i = 0; i < n; i++) {
      const o = 12 + 16 * i;
      tables[String.fromCharCode(u8[o], u8[o + 1], u8[o + 2], u8[o + 3])] = dv.getUint32(o + 8);
    }
    const head = tables.head, hhea = tables.hhea;
    const upem = dv.getUint16(head + 18), yMin = dv.getInt16(head + 38), yMax = dv.getInt16(head + 42);
    const asc = dv.getInt16(hhea + 4), desc = dv.getInt16(hhea + 6);
    return [-yMax / upem, -asc / upem, -desc / upem, -yMin / upem];
  } catch { return ROBOTO; }
}
HW.loadFont = async (name, d) => {
  const u8 = new Uint8Array(d.buffer, d.byteOffset, d.byteLength).slice();
  const face = new FontFace(name, u8);
  await face.load();
  document.fonts.add(face);
  fontMetrics.set(name, ttfMetrics(u8));
};
function cssFamily(f) {
  if (!f || f === 'sans-serif') return 'Roboto, "Droid Sans", sans-serif';
  return f.replace(/(^|, *)sans-serif$/, '$1Roboto, sans-serif');
}
function metricsFor(f) {
  const m = /^"(hwfont\d+)"/.exec(f || '');
  return (m && fontMetrics.get(m[1])) || ROBOTO;
}
const measureCtx = newCanvas(4, 4).getContext('2d');

// ------------------------------------------------------------------ canvas drawing
const css = (c) => {
  const a = (c >>> 24) & 255;
  return a === 255 ? '#' + (c & 0xffffff).toString(16).padStart(6, '0') : `rgba(${(c >> 16) & 255},${(c >> 8) & 255},${c & 255},${(a / 255).toFixed(4)})`;
};
const COMPOSITE = {
  CLEAR: 'destination-out', SRC: 'copy', DST: 'destination-over', SRC_OVER: 'source-over', DST_OVER: 'destination-over',
  SRC_IN: 'source-in', DST_IN: 'destination-in', SRC_OUT: 'source-out', DST_OUT: 'destination-out', SRC_ATOP: 'source-atop',
  DST_ATOP: 'destination-atop', XOR: 'xor', DARKEN: 'darken', LIGHTEN: 'lighten', MULTIPLY: 'multiply', SCREEN: 'screen',
  ADD: 'lighter', OVERLAY: 'overlay',
};
const MODES = Object.keys(COMPOSITE);
/** Paint letter spacing (em) and word spacing (px), where the canvas supports them */
function spacing(c, p) {
  if (!('letterSpacing' in c)) return;
  const ls = p.ls ? `${p.ls * p.size}px` : '0px', ws = p.ws ? `${p.ws}px` : '0px';
  if (c.letterSpacing !== ls) c.letterSpacing = ls;
  if (c.wordSpacing !== ws) c.wordSpacing = ws;
}
const CAPS = ['butt', 'round', 'square'], JOINS = ['miter', 'round', 'bevel'], ALIGNS = ['left', 'center', 'right'];

function scaleOf(c) { const t = c.getTransform(); return Math.hypot(t.a, t.b) || 1; }
function applyCommon(c, p) {
  c.globalCompositeOperation = p.xfer ? COMPOSITE[p.xfer] || 'source-over' : 'source-over';
  if (p.sc >>> 24 && (p.sr > 0 || p.sdx || p.sdy)) {
    const s = scaleOf(c);
    c.shadowColor = css(p.sc);
    c.shadowBlur = p.sr * s;
    c.shadowOffsetX = p.sdx * s; c.shadowOffsetY = p.sdy * s;
  } else if (c.shadowColor !== 'rgba(0, 0, 0, 0)') { c.shadowColor = 'rgba(0,0,0,0)'; c.shadowBlur = 0; c.shadowOffsetX = 0; c.shadowOffsetY = 0; }
}
// ---- shaders: {k: 1 linear | 2 radial | 3 sweep | 4 bitmap | 5 compose | 6 two-point radial, m: local matrix (a..f) or null,
// d: geometry then stop positions, c: colors, img, a, b (compose), tx, ty (tile modes: clamp, repeat, mirror, decal), mode}
const mirroredImages = new WeakMap();
function mirrored(img, mx, my) {
  let byMode = mirroredImages.get(img);
  const key = (mx ? 1 : 0) | (my ? 2 : 0);
  if (byMode && byMode[key] && !img.getContext) return byMode[key];
  const w = img.width, h = img.height, c = newCanvas(mx ? 2 * w : w, my ? 2 * h : h), x = ctxOf(c);
  x.drawImage(img, 0, 0);
  if (mx) { x.save(); x.translate(2 * w, 0); x.scale(-1, 1); x.drawImage(img, 0, 0); x.restore(); }
  if (my) { x.save(); x.translate(0, 2 * h); x.scale(1, -1); x.drawImage(img, 0, 0); if (mx) { x.translate(2 * w, 0); x.scale(-1, 1); x.drawImage(img, 0, 0); } x.restore(); }
  if (!img.getContext) { if (!byMode) mirroredImages.set(img, byMode = {}); byMode[key] = c; }
  return c;
}
/** color stops for one period, repeated n times each way for REPEAT and MIRROR (returns the period count) */
function addStops(g, s, pa, n) {
  const cols = s.c, off = s.k === 1 ? 4 : s.k === 6 ? 6 : s.k === 3 ? 2 : 3, reps = 2 * n + 1, tile = s.tx;
  const stop = (t, col) => {
    if (pa < 1) col = ((Math.round(((col >>> 24) & 255) * pa) << 24) | (col & 0xffffff)) >>> 0;
    g.addColorStop(Math.min(1, Math.max(0, t)), css(col));
  };
  for (let k = 0; k < reps; k++) {
    const flip = tile === 2 && ((k - n) & 1) !== 0;
    for (let i0 = 0; i0 < cols.length; i0++) {
      const i = flip ? cols.length - 1 - i0 : i0;
      const t = Math.min(1, Math.max(0, s.d[off + i]));
      stop((k + (flip ? 1 - t : t)) / reps, cols[i]);
    }
  }
}
function shaderStyle(c, s, pa) {
  const m = s.m, n = s.tx === 1 || s.tx === 2 ? 64 : 0;
  const map = (x, y) => m ? [m[0] * x + m[2] * y + m[4], m[1] * x + m[3] * y + m[5]] : [x, y];
  const det = m ? m[0] * m[3] - m[1] * m[2] : 1;
  if (s.k === 1) {
    let [x0, y0] = map(s.d[0], s.d[1]);
    const vx = s.d[2] - s.d[0], vy = s.d[3] - s.d[1], vv = vx * vx + vy * vy;
    let x1, y1;
    if (!m || vv === 0 || det === 0) { [x1, y1] = map(s.d[2], s.d[3]); }
    else {
      // t(q) = (q - q0) . A^-T v / |v|^2: the same gradient after any affine local matrix
      const ux = (m[3] * vx - m[1] * vy) / det / vv, uy = (-m[2] * vx + m[0] * vy) / det / vv, uu = ux * ux + uy * uy;
      x1 = x0 + ux / uu; y1 = y0 + uy / uu;
    }
    const dx = x1 - x0, dy = y1 - y0;
    const g = c.createLinearGradient(x0 - n * dx, y0 - n * dy, x1 + n * dx, y1 + n * dy);
    addStops(g, s, pa, n);
    return g;
  }
  if (s.k === 2 || s.k === 6) {
    const sc = Math.sqrt(Math.abs(det)) || 1;
    let g;
    if (s.k === 2) { const [x, y] = map(s.d[0], s.d[1]); g = c.createRadialGradient(x, y, 0, x, y, Math.max(0.001, s.d[2] * sc * (2 * n + 1))); addStops(g, s, pa, n); }
    else { const [x0, y0] = map(s.d[0], s.d[1]), [x1, y1] = map(s.d[3], s.d[4]); g = c.createRadialGradient(x0, y0, Math.max(0, s.d[2] * sc), x1, y1, Math.max(0.001, s.d[5] * sc)); addStops(g, s, pa, 0); }
    return g;
  }
  if (s.k === 3) {
    if (!c.createConicGradient) return null;
    const [x, y] = map(s.d[0], s.d[1]);
    const g = c.createConicGradient(m ? Math.atan2(m[1], m[0]) : 0, x, y);
    addStops(g, s, pa, 0);
    return g;
  }
  if (s.k === 4) {
    if (!s.img) return null;
    const rx = s.tx === 1 || s.tx === 2, ry = s.ty === 1 || s.ty === 2;
    const src = s.tx === 2 || s.ty === 2 ? mirrored(s.img, s.tx === 2, s.ty === 2) : s.img;
    const pat = c.createPattern(src, rx && ry ? 'repeat' : rx ? 'repeat-x' : ry ? 'repeat-y' : 'no-repeat');
    if (pat && m && pat.setTransform) pat.setTransform(new DOMMatrix([m[0], m[1], m[2], m[3], m[4], m[5]]));
    return pat;
  }
  if (s.k === 5) {
    // compose: shader a, then b over it with the mode, rendered at the canvas's size and laid back in device space
    const W = c.canvas.width, H = c.canvas.height, oc = newCanvas(W, H), ox = ctxOf(oc), t = c.getTransform();
    ox.setTransform(t);
    const big = 1e5;
    const sa = shaderStyle(ox, s.a, 1), sb = shaderStyle(ox, s.b, 1);
    if (sa) { ox.fillStyle = sa; ox.fillRect(-big, -big, 2 * big, 2 * big); }
    ox.globalCompositeOperation = COMPOSITE[s.mode] || 'source-over';
    if (sb) { ox.fillStyle = sb; ox.fillRect(-big, -big, 2 * big, 2 * big); }
    const pat = c.createPattern(oc, 'no-repeat');
    if (pat && pat.setTransform) pat.setTransform(t.inverse());
    return pat;
  }
  return null;
}
/** the fill or stroke style of the paint; patterns take the paint's alpha through globalAlpha (see paintShape) */
function fillStyle(c, p) {
  const s = p.shader;
  if (!s) return p.css;
  const pa = ((p.color >>> 24) & 255) / 255;
  return shaderStyle(c, s, s.k === 4 || s.k === 5 ? 1 : pa) || p.css;
}
function paintShape(c, p, draw) {
  applyCommon(c, p);
  const ga = c.globalAlpha, sk = p.shader && p.shader.k;
  if ((sk === 4 || sk === 5) && (p.color >>> 24) !== 255) c.globalAlpha = ga * ((p.color >>> 24) & 255) / 255;
  if (p.style === 0 || p.style === 2) { c.fillStyle = fillStyle(c, p); draw(false); }
  if (p.style === 1 || p.style === 2) {
    c.strokeStyle = fillStyle(c, p);
    c.lineWidth = p.w > 0 ? p.w : 1 / scaleOf(c);
    c.lineCap = CAPS[p.cap]; c.lineJoin = JOINS[p.join];
    if (p.dash) { c.setLineDash(p.dash); c.lineDashOffset = p.dashPhase || 0; }
    draw(true);
    if (p.dash) c.setLineDash([]);
  }
  c.globalAlpha = ga;
  c.globalCompositeOperation = 'source-over';
}
function setTransformBase(c, a, b, cc, d, e, f) {
  const B = c.__base;
  if (!B) { c.setTransform(a, b, cc, d, e, f); return; }
  c.setTransform(B[0] * a + B[2] * b, B[1] * a + B[3] * b, B[0] * cc + B[2] * d, B[1] * cc + B[3] * d,
    B[0] * e + B[2] * f + B[4], B[1] * e + B[3] * f + B[5]);
}
function buildPath(a) {
  const p = new Path2D();
  let i = 0;
  const arc = (l, t, r, b, start, sweep, move) => {
    const cx = (l + r) / 2, cy = (t + b) / 2, rx = Math.abs(r - l) / 2, ry = Math.abs(b - t) / 2;
    const s = (start * Math.PI) / 180, e = ((start + sweep) * Math.PI) / 180;
    if (move) p.moveTo(cx + rx * Math.cos(s), cy + ry * Math.sin(s));
    p.ellipse(cx, cy, rx, ry, 0, s, e, sweep < 0);
  };
  while (i < a.length) {
    const op = a[i++];
    switch (op) {
      case 0: p.moveTo(a[i], a[i + 1]); i += 2; break;
      case 1: p.lineTo(a[i], a[i + 1]); i += 2; break;
      case 2: p.quadraticCurveTo(a[i], a[i + 1], a[i + 2], a[i + 3]); i += 4; break;
      case 3: p.bezierCurveTo(a[i], a[i + 1], a[i + 2], a[i + 3], a[i + 4], a[i + 5]); i += 6; break;
      case 4: p.closePath(); break;
      case 5: p.rect(a[i], a[i + 1], a[i + 2] - a[i], a[i + 3] - a[i + 1]); i += 4; break;
      case 6: { const [l, t, r, b] = [a[i], a[i + 1], a[i + 2], a[i + 3]]; const ccw = a[i + 6] === 1;
        p.moveTo(r, (t + b) / 2); p.ellipse((l + r) / 2, (t + b) / 2, Math.abs(r - l) / 2, Math.abs(b - t) / 2, 0, 0, Math.PI * 2, ccw); p.closePath(); i += 7; break; }
      case 7: arc(a[i], a[i + 1], a[i + 2], a[i + 3], a[i + 4], a[i + 5], a[i + 6] === 1); i += 7; break;
      case 8: if (p.roundRect) p.roundRect(a[i], a[i + 1], a[i + 2] - a[i], a[i + 3] - a[i + 1], [Math.max(0, Math.min(a[i + 4], Math.abs(a[i + 2] - a[i]) / 2))]); else p.rect(a[i], a[i + 1], a[i + 2] - a[i], a[i + 3] - a[i + 1]); i += 6; break;
      default: i = a.length;
    }
  }
  return p;
}
// color-filtered copies of images, cached per source image
const filtered = new WeakMap();
function filterImage(img, f) {
  const key = f.m ? Array.prototype.join.call(f.m, ',') : f.pc + f.pm;
  let m = img.getContext ? null : filtered.get(img);
  if (m && m.has(key)) return m.get(key);
  const w = img.width, h = img.height, c = newCanvas(w, h), x = ctxOf(c);
  if (f.m) {
    x.drawImage(img, 0, 0);
    const id = x.getImageData(0, 0, w, h), px = id.data, M = f.m;
    for (let i = 0; i < px.length; i += 4) {
      const r = px[i], g = px[i + 1], b = px[i + 2], a = px[i + 3];
      px[i] = M[0] * r + M[1] * g + M[2] * b + M[3] * a + M[4];
      px[i + 1] = M[5] * r + M[6] * g + M[7] * b + M[8] * a + M[9];
      px[i + 2] = M[10] * r + M[11] * g + M[12] * b + M[13] * a + M[14];
      px[i + 3] = M[15] * r + M[16] * g + M[17] * b + M[18] * a + M[19];
    }
    x.putImageData(id, 0, 0);
  } else {
    const col = css(f.pc);
    x.drawImage(img, 0, 0);
    switch (f.pm) {
      case 'SRC_ATOP': case 'SRC_IN': x.globalCompositeOperation = 'source-in'; x.fillStyle = col; x.fillRect(0, 0, w, h); break;
      case 'MULTIPLY': x.globalCompositeOperation = 'multiply'; x.fillStyle = col; x.fillRect(0, 0, w, h);
        x.globalCompositeOperation = 'destination-in'; x.drawImage(img, 0, 0); break;
      case 'SRC': x.clearRect(0, 0, w, h); x.fillStyle = col; x.fillRect(0, 0, w, h); break;
      case 'CLEAR': x.clearRect(0, 0, w, h); break;
      case 'DST_IN': x.globalCompositeOperation = 'destination-in'; x.fillStyle = col; x.fillRect(0, 0, w, h); break;
      case 'SCREEN': x.globalCompositeOperation = 'screen'; x.fillStyle = col; x.fillRect(0, 0, w, h);
        x.globalCompositeOperation = 'destination-in'; x.drawImage(img, 0, 0); break;
      default: x.globalCompositeOperation = 'source-atop'; x.fillStyle = col; x.fillRect(0, 0, w, h);
    }
    x.globalCompositeOperation = 'source-over';
  }
  if (!img.getContext) { if (!m) filtered.set(img, (m = new Map())); m.set(key, c); }
  return c;
}

HW.cv = {
  newPaint: () => ({ color: 0xff000000, css: '#000000', style: 0, w: 0, size: 12, font: '12px sans-serif', align: 0, sr: 0, sdx: 0, sdy: 0, sc: 0, cap: 0, join: 0, flags: 0, scaleX: 1, met: ROBOTO }),
  setPaint(p, color, style, w, size, family, bold, italic, align, sr, sdx, sdy, sc, cap, join, flags, scaleX) {
    p.color = color >>> 0; p.css = css(color); p.style = style; p.w = w; p.size = size;
    p.font = `${italic ? 'italic ' : ''}${bold ? 'bold ' : ''}${size}px ${cssFamily(family)}`;
    p.align = align; p.sr = sr; p.sdx = sdx; p.sdy = sdy; p.sc = sc >>> 0; p.cap = cap; p.join = join; p.flags = flags; p.scaleX = scaleX || 1;
    p.met = metricsFor(family);
    p.underline = (flags & 8) !== 0; p.strike = (flags & 16) !== 0;
  },
  setSpacing(p, ls, ws) { p.ls = ls; p.ws = ws; },
  path: (a) => buildPath(a),
  setMatrix: (c, a, b, cc, d, e, f) => setTransformBase(c, a, b, cc, d, e, f),
  getMatrix(c) {
    const t = c.getTransform(), B = c.__base;
    if (!B) return new Float32Array([t.a, t.b, t.c, t.d, t.e, t.f]);
    const ib = new DOMMatrix(B).inverse().multiply(t);
    return new Float32Array([ib.a, ib.b, ib.c, ib.d, ib.e, ib.f]);
  },
  clipRect(c, l, t, r, b, op) {
    if (op === 2 || op === 3) return;   // UNION / XOR: can't grow a canvas clip
    const p = new Path2D();
    if (op === 0 || op === 4) {
      const big = 1e6;
      p.rect(-big, -big, 2 * big, 2 * big);
      p.rect(l, t, r - l, b - t);
      c.clip(p, 'evenodd');
    } else { p.rect(l, t, Math.max(0, r - l), Math.max(0, b - t)); c.clip(p); }
  },
  clipPath(c, path, eo, op) {
    if (op === 2 || op === 3) return;
    if (op === 0 || op === 4) {
      const p = new Path2D(); const big = 1e6;
      p.rect(-big, -big, 2 * big, 2 * big); p.addPath(path);
      c.clip(p, 'evenodd');
    } else c.clip(path, eo ? 'evenodd' : 'nonzero');
  },
  color(c, col, mode) {
    c.save();
    c.setTransform(1, 0, 0, 1, 0, 0);
    const w = c.canvas.width, h = c.canvas.height;
    const m = MODES[mode] || 'SRC_OVER';
    if (m === 'CLEAR' || (m === 'SRC' && (col >>> 24) === 0)) c.clearRect(0, 0, w, h);
    else {
      if (m === 'SRC') c.clearRect(0, 0, w, h);
      else c.globalCompositeOperation = COMPOSITE[m];
      c.fillStyle = css(col);
      c.fillRect(0, 0, w, h);
    }
    c.restore();
  },
  rect(c, l, t, r, b, p) {
    paintShape(c, p, (stroke) => stroke ? c.strokeRect(l, t, r - l, b - t) : c.fillRect(l, t, r - l, b - t));
  },
  rrect(c, l, t, r, b, rx, ry, p) {
    const path = new Path2D();
    if (path.roundRect) path.roundRect(l, t, r - l, b - t, [{ x: Math.max(0, Math.min(rx, Math.abs(r - l) / 2)), y: Math.max(0, Math.min(ry, Math.abs(b - t) / 2)) }]);
    else path.rect(l, t, r - l, b - t);
    paintShape(c, p, (stroke) => stroke ? c.stroke(path) : c.fill(path));
  },
  arc(c, l, t, r, b, start, sweep, useCenter, p) {
    const path = new Path2D(), cx = (l + r) / 2, cy = (t + b) / 2;
    const s = (start * Math.PI) / 180, e = ((start + sweep) * Math.PI) / 180;
    if (useCenter) path.moveTo(cx, cy);
    path.ellipse(cx, cy, Math.abs(r - l) / 2, Math.abs(b - t) / 2, 0, s, e, sweep < 0);
    if (useCenter) path.closePath();
    if (Math.abs(sweep) >= 360) path.closePath();
    paintShape(c, p, (stroke) => stroke ? c.stroke(path) : c.fill(path));
  },
  lines(c, pts, p) {
    const path = new Path2D();
    for (let i = 0; i + 3 < pts.length; i += 4) { path.moveTo(pts[i], pts[i + 1]); path.lineTo(pts[i + 2], pts[i + 3]); }
    const st = p.style; p.style = 1;
    paintShape(c, p, () => c.stroke(path));
    p.style = st;
  },
  points(c, pts, p) {
    applyCommon(c, p);
    c.fillStyle = p.css;
    const w = p.w > 0 ? p.w : 1;
    for (let i = 0; i + 1 < pts.length; i += 2) {
      if (p.cap === 1) { c.beginPath(); c.arc(pts[i], pts[i + 1], w / 2, 0, Math.PI * 2); c.fill(); }
      else c.fillRect(pts[i] - w / 2, pts[i + 1] - w / 2, w, w);
    }
  },
  path2d(c, path, eo, p) {
    paintShape(c, p, (stroke) => stroke ? c.stroke(path) : c.fill(path, eo ? 'evenodd' : 'nonzero'));
  },
  image(c, img, sx, sy, sw, sh, dx, dy, dw, dh, p) {
    if (!img || sw <= 0 || sh <= 0 || dw === 0 || dh === 0) return;
    const pa = p ? ((p.color >>> 24) & 255) / 255 : 1;
    if (pa <= 0) return;
    if (p && p.filter) img = filterImage(img, p.filter);
    const ga = c.globalAlpha;
    if (pa < 1) c.globalAlpha = ga * pa;
    c.imageSmoothingEnabled = !p || (p.flags & 2) !== 0 || (c.__base != null && c.__base[0] !== 1);
    if (p) applyCommon(c, p);
    // keep the source rectangle inside the image (drawImage rejects/clamps differently)
    if (sx < 0) { dx -= (sx * dw) / sw; dw += (sx * dw) / sw; sw += sx; sx = 0; }
    if (sy < 0) { dy -= (sy * dh) / sh; dh += (sy * dh) / sh; sh += sy; sy = 0; }
    if (sx + sw > img.width) { const k = img.width - sx; dw = (dw * k) / sw; sw = k; }
    if (sy + sh > img.height) { const k = img.height - sy; dh = (dh * k) / sh; sh = k; }
    if (sw > 0 && sh > 0) c.drawImage(img, sx, sy, sw, sh, dx, dy, dw, dh);
    c.globalAlpha = ga;
    c.globalCompositeOperation = 'source-over';
  },
  text(c, s, x, y, p) {
    c.font = p.font;
    spacing(c, p);
    c.textAlign = ALIGNS[p.align];
    c.textBaseline = 'alphabetic';
    const sx = p.scaleX;
    if (sx !== 1) { c.save(); c.translate(x, y); c.scale(sx, 1); x = 0; y = 0; }
    paintShape(c, p, (stroke) => stroke ? c.strokeText(s, x, y) : c.fillText(s, x, y));
    if (p.underline || p.strike) {
      const w = c.measureText(s).width, x0 = p.align === 1 ? x - w / 2 : p.align === 2 ? x - w : x;
      c.fillStyle = p.css;
      const th = Math.max(1, p.size / 18);
      if (p.underline) c.fillRect(x0, y + p.size * 0.11, w, th);
      if (p.strike) c.fillRect(x0, y - p.size * 0.28, w, th);
    }
    if (sx !== 1) c.restore();
  },
  measure(p, s) { measureCtx.font = p.font; spacing(measureCtx, p); return measureCtx.measureText(s).width * p.scaleX; },
  metric(p, i) { return p.met[i] * p.size; },
  widths(p, s) {
    measureCtx.font = p.font;
    spacing(measureCtx, p);
    const out = new Float32Array(s.length);
    let prev = 0;
    // cumulative measuring keeps kerning and surrogate pairs consistent with whole-string measure
    for (let i = 0; i < s.length; i++) {
      const code = s.charCodeAt(i);
      if (code >= 0xd800 && code < 0xdc00 && i + 1 < s.length) { const w = measureCtx.measureText(s.slice(0, i + 2)).width; out[i] = (w - prev) * p.scaleX; out[i + 1] = 0; prev = w; i++; continue; }
      const w = measureCtx.measureText(s.slice(0, i + 1)).width;
      out[i] = (w - prev) * p.scaleX; prev = w;
    }
    return out;
  },
};

// ------------------------------------------------------------------ input: pointer and keys
function toLogical(e) {
  const r = uiCanvas.getBoundingClientRect();
  return [((e.clientX - r.left) / r.width) * W, ((e.clientY - r.top) / r.height) * H];
}
const activePointers = new Set();
const TOOL = { touch: 1, pen: 2, mouse: 3 };
const pointerTool = (e) => (e.pointerType === 'pen' && e.buttons & 32 ? 4 : TOOL[e.pointerType] || 1);   // eraser: a pen's button 5
const pointerPressure = (e) => (e.pointerType === 'pen' ? e.pressure : e.pointerType === 'mouse' ? (e.buttons ? 1 : 0) : 1);
uiCanvas.addEventListener('pointerdown', (e) => {
  if (!HW.java) return;
  unlockAudio();
  e.preventDefault();
  try { uiCanvas.setPointerCapture(e.pointerId); } catch {}
  if (activePointers.has(e.pointerId)) return;   // another mouse button on a held pointer
  activePointers.add(e.pointerId);
  const [x, y] = toLogical(e);
  HW.java.onPointer(0, e.pointerId & 0xffff, x, y, pointerPressure(e), pointerTool(e), e.pointerType === 'mouse' ? e.buttons : 0);
});
uiCanvas.addEventListener('pointermove', (e) => {
  if (!HW.java) return;
  if (!activePointers.has(e.pointerId)) {
    if (e.pointerType === 'mouse') { const [x, y] = toLogical(e); HW.java.onHover(7, x, y); }
    return;
  }
  const evs = e.getCoalescedEvents ? e.getCoalescedEvents() : [];
  const now = performance.now();
  for (let k = 0; k + 1 < evs.length; k++) {
    const [hx, hy] = toLogical(evs[k]);
    HW.java.onPointerSample(e.pointerId & 0xffff, hx, hy, Math.max(0, now - evs[k].timeStamp));
  }
  const last = evs.length ? evs[evs.length - 1] : e;
  const [x, y] = toLogical(last);
  HW.java.onPointer(2, e.pointerId & 0xffff, x, y, pointerPressure(last), pointerTool(e), e.pointerType === 'mouse' ? e.buttons : 0);
});
const up = (cancel) => (e) => {
  if (!HW.java || !activePointers.has(e.pointerId)) return;
  if (!cancel && e.pointerType === 'mouse' && e.buttons) return;   // a button released while others are held
  activePointers.delete(e.pointerId);
  const [x, y] = toLogical(e);
  HW.java.onPointer(cancel ? 3 : 1, e.pointerId & 0xffff, x, y, pointerPressure(e), pointerTool(e), e.pointerType === 'mouse' ? e.buttons : 0);
};
uiCanvas.addEventListener('pointerup', up(false));
uiCanvas.addEventListener('pointercancel', up(true));
uiCanvas.addEventListener('pointerleave', (e) => { if (HW.java && e.pointerType === 'mouse' && !activePointers.has(e.pointerId)) { const [x, y] = toLogical(e); HW.java.onHover(10, x, y); } });
uiCanvas.addEventListener('contextmenu', (e) => e.preventDefault());
// the mouse wheel: AXIS_VSCROLL / AXIS_HSCROLL, one unit per notch (positive vertical: away from the user)
uiCanvas.addEventListener('wheel', (e) => {
  if (!HW.java) return;
  if (e.ctrlKey) return;   // browser zoom
  e.preventDefault();
  const unit = e.deltaMode === 1 ? 3 : e.deltaMode === 2 ? 1 : 100;
  const [x, y] = toLogical(e);
  HW.java.onScroll(x, y, e.deltaX / unit, -e.deltaY / unit);
}, { passive: false });
// keys by physical position (KeyboardEvent.code), as Android's Generic.kl maps a USB keyboard; Escape is Back
const CODES = { Escape: 4, BrowserBack: 4, ContextMenu: 82, F1: 82, Enter: 66, NumpadEnter: 160, Backspace: 67, Delete: 112, Tab: 61, Space: 62,
  ArrowUp: 19, ArrowDown: 20, ArrowLeft: 21, ArrowRight: 22, Home: 122, End: 123, PageUp: 92, PageDown: 93, Insert: 124,
  ShiftLeft: 59, ShiftRight: 60, ControlLeft: 113, ControlRight: 114, AltLeft: 57, AltRight: 58, MetaLeft: 117, MetaRight: 118,
  CapsLock: 115, NumLock: 143, ScrollLock: 116, Pause: 121, PrintScreen: 120,
  Minus: 69, Equal: 70, BracketLeft: 71, BracketRight: 72, Backslash: 73, Semicolon: 74, Quote: 75, Backquote: 68, Comma: 55, Period: 56, Slash: 76,
  NumpadDivide: 154, NumpadMultiply: 155, NumpadSubtract: 156, NumpadAdd: 157, NumpadDecimal: 158, NumpadComma: 159, NumpadEqual: 161,
  NumpadParenLeft: 162, NumpadParenRight: 163,
  AudioVolumeUp: 24, AudioVolumeDown: 25, AudioVolumeMute: 164, MediaPlayPause: 85, MediaStop: 86, MediaTrackNext: 87, MediaTrackPrevious: 88 };
for (let i = 0; i < 26; i++) CODES['Key' + String.fromCharCode(65 + i)] = 29 + i;
for (let i = 0; i < 10; i++) { CODES['Digit' + i] = 7 + i; CODES['Numpad' + i] = 144 + i; }
for (let i = 2; i <= 12; i++) CODES['F' + i] = 130 + i;
const META = { ShiftLeft: 0x41, ShiftRight: 0x81, ControlLeft: 0x3000, ControlRight: 0x5000, AltLeft: 0x12, AltRight: 0x22, MetaLeft: 0x30000, MetaRight: 0x50000 };
const heldModifiers = new Set();
function metaState(e) {
  let m = 0;
  for (const c of heldModifiers) m |= META[c];
  if (e.shiftKey && !(m & 1)) m |= 0x41;
  if (e.ctrlKey && !(m & 0x1000)) m |= 0x3000;
  if (e.altKey && !(m & 2)) m |= 0x12;
  if (e.metaKey && !(m & 0x10000)) m |= 0x30000;
  if (e.getModifierState) {
    if (e.getModifierState('CapsLock')) m |= 0x100000;
    if (e.getModifierState('NumLock')) m |= 0x200000;
    if (e.getModifierState('ScrollLock')) m |= 0x400000;
  }
  return m;
}
const browserKey = (e) => e.code === 'F5' || e.code === 'F11' || e.code === 'F12' || ((e.ctrlKey || e.metaKey) && /^Key[RWTLNQ]$|^Digit/.test(e.code))
  || ((e.ctrlKey || e.metaKey) && e.shiftKey && e.code === 'KeyI');
function keyEvent(down) {
  return (e) => {
    if (!HW.java || inputEl && document.activeElement === inputEl) return;
    if (META[e.code]) { if (down) heldModifiers.add(e.code); else heldModifiers.delete(e.code); }
    const code = CODES[e.code] != null ? CODES[e.code] : e.key === 'Escape' ? 4 : undefined;
    if (code == null || browserKey(e)) return;
    e.preventDefault();
    HW.java.onKey(code, down, e.repeat ? 1 : 0, metaState(e));
  };
}
window.addEventListener('keydown', keyEvent(true));
window.addEventListener('keyup', keyEvent(false));
window.addEventListener('blur', () => heldModifiers.clear());
// the legacy menu button (the navigation bar's overflow): shown while the top activity has an options menu
const menuBtn = document.getElementById('menu');
HW.menuButton = (v) => { if (menuBtn) menuBtn.style.display = v ? 'block' : 'none'; };
if (menuBtn) {
  menuBtn.addEventListener('pointerdown', (e) => e.stopPropagation());
  menuBtn.addEventListener('click', (e) => { e.preventDefault(); if (HW.java) { HW.java.onKey(82, true, 0, 0); HW.java.onKey(82, false, 0, 0); } });
}
document.addEventListener('visibilitychange', () => {
  const vis = document.visibilityState === 'visible';
  if (!vis) flushFiles();
  if (HW.java) HW.java.onVisibility(vis);
});

// ------------------------------------------------------------------ soft keyboard: a DOM text field over the EditText
let inputEl = null, inputPlace = null, inputSuppressBlur = false;
function placeInput() {
  if (!inputEl || !inputPlace) return;
  const [x, y, w, h] = inputPlace;
  Object.assign(inputEl.style, { left: x * cssScale + 'px', top: y * cssScale + 'px', width: Math.max(8, w) * cssScale + 'px', height: Math.max(8, h) * cssScale + 'px' });
}
let lastSel = '';
/** the field's selection to Java when it changed (with the text first, so offsets match) */
function reportSel() {
  if (!inputEl || inputEl.selectionStart == null) return;
  const k = inputEl.selectionStart + ',' + inputEl.selectionEnd;
  if (k === lastSel) return;
  lastSel = k;
  if (HW.java.onSelection) HW.java.onSelection(inputEl.selectionStart, inputEl.selectionEnd);
}
HW.input = {
  show(x, y, w, h, text, hint, size, color, max, flags, gravity, family) {
    const multi = (flags & 2) !== 0;
    if (inputEl && (inputEl.tagName === 'TEXTAREA') !== multi) HW.input.hide();
    if (!inputEl) {
      inputEl = document.createElement(multi ? 'textarea' : 'input');
      inputEl.className = 'hw-input';
      inputEl.addEventListener('input', () => { HW.java.onText(inputEl.value, 0); reportSel(); });
      for (const ev of ['select', 'keyup', 'mouseup', 'touchend']) inputEl.addEventListener(ev, reportSel);
      inputEl.addEventListener('keydown', (e) => {
        e.stopPropagation();
        if (e.key === 'Enter' && inputEl.tagName === 'INPUT') { e.preventDefault(); HW.java.onText(inputEl.value, 1); }
        else if (e.key === 'Escape') { e.preventDefault(); HW.java.onText(inputEl.value, 2); }
        else if (!e.ctrlKey && !e.altKey && !e.metaKey && (e.key === 'Tab' || (inputEl.tagName === 'INPUT' && (e.key === 'ArrowUp' || e.key === 'ArrowDown')))) {
          // focus navigation out of the field (Tab; up/down in a single-line field), as the key would on a device
          e.preventDefault();
          const code = e.key === 'Tab' ? 61 : e.key === 'ArrowUp' ? 19 : 20, m = metaState(e);
          HW.java.onText(inputEl.value, 0);
          HW.java.onKey(code, true, e.repeat ? 1 : 0, m);
          HW.java.onKey(code, false, 0, m);
        }
      });
      inputEl.addEventListener('keyup', (e) => e.stopPropagation());
      inputEl.addEventListener('blur', () => { if (!inputSuppressBlur && inputEl) HW.java.onText(inputEl.value, 2); });
      stage.appendChild(inputEl);
      overlaysToPlace.add(placeInput);
    }
    if (inputEl.tagName === 'INPUT') inputEl.type = flags & 1 ? 'password' : flags & 8 ? 'email' : 'text';
    inputEl.inputMode = flags & 4 ? 'numeric' : 'text';
    inputEl.value = text;
    inputEl.placeholder = hint || '';
    if (max > 0 && max < 100000) inputEl.maxLength = max; else inputEl.removeAttribute('maxlength');
    const hg = gravity & 7, vg = gravity & 0x70;
    Object.assign(inputEl.style, {
      font: `${size * cssScale}px ${cssFamily(family)}`, color: css(color),
      textAlign: hg === 1 ? 'center' : hg === 5 ? 'right' : 'left',
    });
    inputEl.style.alignContent = vg === 0x10 ? 'center' : vg === 0x50 ? 'end' : 'start';
    inputPlace = [x, y, w, h];
    placeInput();
    inputEl.focus({ preventScroll: true });
    try { inputEl.setSelectionRange(text.length, text.length); } catch {}
  },
  hide() {
    if (!inputEl) return;
    const el = inputEl;
    inputEl = null; inputPlace = null;
    overlaysToPlace.delete(placeInput);
    inputSuppressBlur = true;
    el.blur(); el.remove();
    inputSuppressBlur = false;
  },
  set(s) { if (inputEl && inputEl.value !== s) inputEl.value = s; },
  select(a, b) {
    if (!inputEl) return;
    const n = inputEl.value.length;
    a = Math.max(0, Math.min(a, n)); b = Math.max(0, Math.min(b, n));
    try { inputEl.setSelectionRange(Math.min(a, b), Math.max(a, b), a > b ? 'backward' : 'forward'); } catch {}
    lastSel = inputEl.selectionStart + ',' + inputEl.selectionEnd;
  },
};

// ------------------------------------------------------------------ WebView: iframes over the view
const webs = new Map();
const SANDBOX = 'allow-scripts allow-forms allow-popups allow-modals';
/** where an android_asset path is served, when the build put it beside the page (CONFIG.webAssets) */
function servedAsset(path) {
  const dirs = CONFIG.webAssets || [];
  return dirs.some((d) => path.startsWith(d)) ? new URL('android_asset/' + path, location.href).href : null;
}
/** the script a page gets first: scripts posted by its WebView (a page of another origin can't be reached
 *  otherwise), and (same origin) window.<name> for each JavaScript interface of its WebView */
function webBootstrap(id, w) {
  const names = JSON.stringify([...w.ifaces]);
  return '<script>(function(){addEventListener("message",function(e){if(e.source===parent&&e.data&&typeof e.data.a2wEval==="string")' +
    '{try{(0,eval)(e.data.a2wEval);}catch(x){console.warn("WebView script: "+x);}}});' +
    'var P;try{P=parent.HW&&parent.HW.web;}catch(x){}if(!P)return;' + names + '.forEach(function(n){' +
    'window[n]=new Proxy({},{get:function(t,m){if(typeof m!=="string"||m==="then"||m==="toJSON")return undefined;' +
    'return function(){return P.call(' + id + ',n,m,Array.prototype.slice.call(arguments));};}});});})();</' + 'script>';
}
/** a navigation of the frame starts (scripts from Java wait for its page) / its document is set */
function webLoading(w) { w.loading = true; }
function webNavigate(w, set) { w.nav++; w.loading = true; set(); }
/** a page as the frame's document, its relative URLs resolving against base (a served android_asset URL) */
function webDocument(id, w, html, base) {
  const doc = new DOMParser().parseFromString(html, 'text/html');
  const head = doc.head || doc.documentElement.insertBefore(doc.createElement('head'), doc.documentElement.firstChild);
  if (base && !doc.querySelector('base[href]')) { const b = doc.createElement('base'); b.href = base; head.insertBefore(b, head.firstChild); }
  return '<!DOCTYPE html>' + doc.documentElement.outerHTML.replace(/<head([^>]*)>/i, (m) => m + webBootstrap(id, w));
}
HW.web = {
  create(id) {
    const f = document.createElement('iframe');
    f.className = 'hw-web';
    f.setAttribute('sandbox', SANDBOX);
    f.referrerPolicy = 'no-referrer';
    f.style.visibility = 'hidden';
    const w = { f, rect: null, place: null, ifaces: new Set(), nav: 0, navDone: 0, loading: false, queued: [] };
    f.addEventListener('load', () => {
      if (w.navDone < w.nav) {
        w.navDone = w.nav; w.loading = false;
        for (const c of w.queued.splice(0)) HW.web.js(id, c, false);
      }
      HW.java.onWeb(id, 1, f.dataset.url || 'about:blank');
    });
    f.addEventListener('error', () => HW.java.onWeb(id, 2, f.dataset.url || ''));
    stage.appendChild(f);
    w.place = () => { if (!w.rect) return; const [x, y, ww, hh] = w.rect; Object.assign(f.style, { left: x * cssScale + 'px', top: y * cssScale + 'px', width: ww * cssScale + 'px', height: hh * cssScale + 'px' }); };
    overlaysToPlace.add(w.place);
    webs.set(id, w);
  },
  remove(id) { const w = webs.get(id); if (w) { overlaysToPlace.delete(w.place); w.f.remove(); webs.delete(id); } },
  url(id, u) {
    const w = webs.get(id); if (!w) return;
    w.f.dataset.url = u;
    webLoading(w);
    if (/^file:\/\/\/android_asset\//.test(u)) {
      const path = u.slice('file:///android_asset/'.length);
      const served = servedAsset(path.split(/[?#]/)[0]);
      if (served) {
        // the app's own page, served beside this one: same origin, so its requests, scripts and the app's
        // JavaScript interfaces work as in a WebView
        HW.apk.bytes('assets/' + path.split(/[?#]/)[0]).then((b) => webNavigate(w, () => {
          w.f.setAttribute('sandbox', SANDBOX + ' allow-same-origin');
          w.f.srcdoc = webDocument(id, w, b ? new TextDecoder().decode(b) : '', served);
        }));
      } else {
        w.f.setAttribute('sandbox', SANDBOX);
        assetPage(path).then((h) => webNavigate(w, () => { w.f.srcdoc = webDocument(id, w, h, null); }));
      }
    } else if (/^https?:/i.test(u)) webNavigate(w, () => { w.f.setAttribute('sandbox', SANDBOX); w.f.removeAttribute('srcdoc'); w.f.src = u; });
    else webNavigate(w, () => { w.f.srcdoc = ''; });
  },
  /** loadDataWithBaseURL: a base under file:///android_asset/ that the build served makes the page same-origin */
  html(id, d, base) {
    const w = webs.get(id); if (!w) return;
    w.f.dataset.url = 'about:blank';
    w.f.removeAttribute('src');
    const served = base && /^file:\/\/\/android_asset\//.test(base) ? servedAsset(base.slice('file:///android_asset/'.length)) : null;
    webNavigate(w, () => {
      w.f.setAttribute('sandbox', SANDBOX + ' allow-same-origin');
      w.f.srcdoc = webDocument(id, w, d, served);
    });
  },
  iface(id, name, on) {
    const w = webs.get(id); if (!w) return;
    if (on) w.ifaces.add(name); else w.ifaces.delete(name);
  },
  /** a page calling a JavaScript interface */
  call(id, name, method, args) {
    let a;
    try { a = JSON.stringify(args); } catch { a = '[]'; }
    const r = HW.java.webJsCall(id, name, method, a);
    if (r == null || r === 'undefined') return undefined;
    try { return JSON.parse(r); } catch { return r; }
  },
  /** script from Java (loadUrl("javascript:..."), evaluateJavascript): run in the page; its value as JSON */
  js(id, code, urlEncoded) {
    const w = webs.get(id); if (!w) return 'null';
    if (urlEncoded) { try { code = decodeURIComponent(code); } catch {} }
    // a page still loading gets the script once it is there (an app's loadUrl("javascript:...") right after its page)
    if (w.loading) { w.queued.push(code); return 'null'; }
    const win = w.f.contentWindow;
    let ev;
    try { ev = win.eval; } catch {
      // a page of another origin: posted to its listener (no value comes back)
      try { win.postMessage({ a2wEval: code }, '*'); } catch (e) { HW.log(5, 'W/WebView: script not delivered: ' + e); }
      return 'null';
    }
    try {
      const j = JSON.stringify(ev.call(win, code));
      return j === undefined ? 'null' : j;
    } catch (e) {
      HW.log(5, 'W/WebView: script failed: ' + e);
      return 'null';
    }
  },
  place(id, x, y, ww, hh, v) {
    const w = webs.get(id); if (!w) return;
    w.rect = [x, y, ww, hh]; w.place();
    w.f.style.visibility = v ? 'visible' : 'hidden';
  },
};

/** a page from the APK's assets for a WebView: the scripts, style sheets and images it names by relative path are
 *  put in the page (the frame has no URL they could be resolved against) */
const MIME_BY_EXT = { png: 'image/png', jpg: 'image/jpeg', jpeg: 'image/jpeg', gif: 'image/gif', webp: 'image/webp', svg: 'image/svg+xml',
  mp3: 'audio/mpeg', ogg: 'audio/ogg', wav: 'audio/wav', m4a: 'audio/mp4', mp4: 'video/mp4', webm: 'video/webm', ttf: 'font/ttf', woff: 'font/woff' };
async function assetPage(path) {
  const page = await HW.apk.bytes('assets/' + path.split(/[?#]/)[0]);
  if (!page) return '';
  const doc = new DOMParser().parseFromString(new TextDecoder().decode(page), 'text/html');
  const dir = path.slice(0, path.lastIndexOf('/') + 1);
  const relative = (u) => u && !/^([a-z][\w+.-]*:|\/\/|#)/i.test(u);
  const asset = (u) => {
    const out = [];
    for (const p of (dir + u.split(/[?#]/)[0]).split('/')) { if (p === '..') out.pop(); else if (p && p !== '.') out.push(p); }
    return HW.apk.bytes('assets/' + out.join('/'));
  };
  const text = (b) => (b ? new TextDecoder().decode(b) : '');
  for (const e of doc.querySelectorAll('script[src]')) {
    const u = e.getAttribute('src');
    if (!relative(u)) continue;
    e.removeAttribute('src');
    e.textContent = text(await asset(u));
  }
  for (const e of doc.querySelectorAll('link[rel~="stylesheet"][href]')) {
    const u = e.getAttribute('href');
    if (!relative(u)) continue;
    const st = doc.createElement('style');
    st.textContent = text(await asset(u));
    e.replaceWith(st);
  }
  for (const e of doc.querySelectorAll('img[src], audio[src], video[src], source[src], input[src]')) {
    const u = e.getAttribute('src');
    if (!relative(u)) continue;
    const b = await asset(u);
    if (!b) continue;
    let bin = '';
    for (let i = 0; i < b.length; i += 0x8000) bin += String.fromCharCode.apply(null, b.subarray(i, i + 0x8000));
    e.setAttribute('src', 'data:' + (MIME_BY_EXT[u.split('.').pop().toLowerCase()] || 'application/octet-stream') + ';base64,' + btoa(bin));
  }
  return '<!DOCTYPE html>' + doc.documentElement.outerHTML;
}

// ------------------------------------------------------------------ audio: MediaPlayer on <audio>, SoundPool on Web Audio
let actx = null;
function audioCtx() { if (!actx) actx = new (window.AudioContext || window.webkitAudioContext)(); return actx; }
const pendingPlays = new Set();
let audioUnlocked = false;
function unlockAudio() {
  if (audioUnlocked) return;
  audioUnlocked = true;
  try { audioCtx().resume(); } catch {}
  for (const a of pendingPlays) a.play().catch(() => {});
  pendingPlays.clear();
}
window.addEventListener('keydown', unlockAudio);
const players = new Map();
let nextPlayer = 1;
const pools = new Map();   // sound id -> AudioBuffer (or promise)
const streams = new Map();
let nextStream = 1;
const tracks = new Map();
let nextTrack = 1;
let pageHidden = false;
HW.audio = {
  mpCreate() {
    const id = nextPlayer++;
    const a = new Audio();
    a.preload = 'auto';
    a.addEventListener('ended', () => { if (!a.loop) HW.java.onAudio(id, 1); });
    a.addEventListener('error', () => { if (a.src) HW.java.onAudio(id, 2); });
    players.set(id, { a, url: null, wantPlay: false });
    return id;
  },
  mpSource(id, d, loop) {
    const p = players.get(id); if (!p) return;
    if (p.url) URL.revokeObjectURL(p.url);
    const bytes = new Uint8Array(d.buffer, d.byteOffset, d.byteLength);
    const mime = bytes[0] === 0x4f && bytes[1] === 0x67 ? 'audio/ogg' : bytes[0] === 0x52 && bytes[1] === 0x49 ? 'audio/wav' : 'audio/mpeg';
    p.url = URL.createObjectURL(new Blob([bytes], { type: mime }));
    p.a.src = p.url;
    p.a.loop = !!loop;
  },
  mpPlay(id) {
    const p = players.get(id); if (!p) return;
    p.wantPlay = true;
    if (pageHidden) return;
    p.a.play().catch(() => pendingPlays.add(p.a));
  },
  mpPause(id) { const p = players.get(id); if (p) { p.wantPlay = false; p.a.pause(); pendingPlays.delete(p.a); } },
  mpStop(id) { const p = players.get(id); if (p) { p.wantPlay = false; p.a.pause(); pendingPlays.delete(p.a); try { p.a.currentTime = 0; } catch {} } },
  mpSeek(id, ms) { const p = players.get(id); if (p) try { p.a.currentTime = ms / 1000; } catch {} },
  mpLoop(id, l) { const p = players.get(id); if (p) p.a.loop = !!l; },
  mpVolume(id, v) { const p = players.get(id); if (p) p.a.volume = Math.max(0, Math.min(1, v)); },
  mpPosition(id) { const p = players.get(id); return p ? Math.floor(p.a.currentTime * 1000) : 0; },
  mpDuration(id) { const p = players.get(id); return p && isFinite(p.a.duration) ? Math.floor(p.a.duration * 1000) : -1; },
  mpPlaying(id) { const p = players.get(id); return !!p && (p.wantPlay || !p.a.paused); },
  mpRelease(id) { const p = players.get(id); if (p) { p.a.pause(); pendingPlays.delete(p.a); p.a.removeAttribute('src'); if (p.url) URL.revokeObjectURL(p.url); players.delete(id); } },
  spLoad(id, d) {
    const bytes = new Uint8Array(d.buffer, d.byteOffset, d.byteLength).slice();
    const pr = audioCtx().decodeAudioData(bytes.buffer).then((b) => { pools.set(id, b); HW.java.onAudio(id | 0x40000000, 0); },
      () => { pools.delete(id); HW.java.onAudio(id | 0x40000000, 2); });
    pools.set(id, pr);
  },
  spPlay(id, l, r, loop, rate) {
    const b = pools.get(id);
    if (!(b instanceof AudioBuffer) || pageHidden) return 0;
    const ctx = audioCtx();
    const src = ctx.createBufferSource();
    src.buffer = b;
    src.playbackRate.value = rate > 0 ? rate : 1;
    const gain = ctx.createGain(), pan = ctx.createStereoPanner ? ctx.createStereoPanner() : null;
    const vol = Math.max(l, r);
    gain.gain.value = vol;
    if (pan) pan.pan.value = vol > 0 ? (r - l) / vol : 0;
    src.connect(gain);
    if (pan) { gain.connect(pan); pan.connect(ctx.destination); } else gain.connect(ctx.destination);
    const sid = nextStream++;
    const s = { src, gain, pan, id, vol, paused: false };
    if (loop < 0) src.loop = true;
    else if (loop > 0) { src.loop = true; setTimeout(() => { try { src.loop = false; } catch {} }, (b.duration * loop * 1000) / src.playbackRate.value); }
    src.addEventListener('ended', () => streams.delete(sid));
    src.start();
    streams.set(sid, s);
    return sid;
  },
  spStop(sid) { const s = streams.get(sid); if (s) { try { s.src.stop(); } catch {} streams.delete(sid); } },
  spPause(sid) { const s = streams.get(sid); if (s) { s.gain.gain.value = 0; s.paused = true; } },
  spResume(sid) { const s = streams.get(sid); if (s && s.paused) { s.paused = false; s.gain.gain.value = s.vol ?? 1; } },
  spVolume(sid, l, r) { const s = streams.get(sid); if (s) { const v = Math.max(l, r); s.vol = v; if (!s.paused) s.gain.gain.value = v; if (s.pan) s.pan.pan.value = v > 0 ? (r - l) / v : 0; } },
  spUnload(id) { pools.delete(id); },
  // AudioTrack: PCM chunks scheduled back to back on Web Audio
  trackCreate(rate, ch, bufMs) {
    const id = nextTrack++;
    tracks.set(id, { rate, ch, bufMs, end: 0, start: 0, frames: 0, playing: false, gain: null, queued: [], vol: 1 });
    return id;
  },
  trackWrite(id, pcm) {
    const t = tracks.get(id);
    if (!t || !pcm.length) return 0;
    const ctx = audioCtx(), n = Math.floor(pcm.length / t.ch);
    if (!t.gain) { t.gain = ctx.createGain(); t.gain.gain.value = t.vol; t.gain.connect(ctx.destination); }
    const buf = ctx.createBuffer(t.ch, n, t.rate);
    for (let c = 0; c < t.ch; c++) {
      const d = buf.getChannelData(c);
      for (let i = 0; i < n; i++) d[i] = pcm[i * t.ch + c] / 32768;
    }
    let wait;
    if (t.playing && !pageHidden && ctx.state === 'running') {
      const now = ctx.currentTime;
      if (t.end < now + 0.02) { t.end = now + 0.02; if (!t.start) t.start = t.end; }
      const src = ctx.createBufferSource();
      src.buffer = buf; src.connect(t.gain); src.start(t.end);
      t.queued.push(src);
      src.addEventListener('ended', () => { const k = t.queued.indexOf(src); if (k >= 0) t.queued.splice(k, 1); });
      t.end += n / t.rate;
      // the writer waits while more than the track's buffer is queued
      const ahead = (t.end - now) * 1000 - t.bufMs;
      wait = ahead > 0 ? Math.min(ahead, 200) : 0;
    } else wait = Math.min((n / t.rate) * 1000, 200);   // not audible (paused, or audio not yet allowed): real-time pace
    t.frames += n;
    if (wait > 0 && nativeDepth > 0) { HW.nativeWait = Math.max(HW.nativeWait || 0, wait); return 0; }
    return wait;
  },
  trackControl(id, op) {
    const t = tracks.get(id); if (!t) return;
    if (op === 1) { t.playing = true; try { audioCtx().resume(); } catch {} return; }
    if (op === 2 || op === 3 || op === 4 || op === 5) {
      if (op !== 2) for (const src of t.queued) try { src.stop(); } catch {}
      if (op !== 2) { t.queued = []; t.end = 0; }
      if (op !== 4) t.playing = op === 2 ? false : op === 3 ? false : t.playing;
      if (op === 3) { t.frames = 0; t.start = 0; }
      if (op === 5) { if (t.gain) t.gain.disconnect(); tracks.delete(id); }
    }
  },
  trackVolume(id, v) { const t = tracks.get(id); if (t) { t.vol = v; if (t.gain) t.gain.gain.value = v; } },
  trackPosition(id) {
    const t = tracks.get(id); if (!t || !t.start) return 0;
    const played = Math.max(0, (audioCtx().currentTime - t.start) * t.rate);
    return Math.min(t.frames, Math.floor(played));
  },
  debug: () => ({ players: [...players.entries()].map(([id, p]) => [id, p.a.src.slice(0, 30), p.a.paused, p.wantPlay, p.a.currentTime]), pools: pools.size, streams: streams.size, ctx: actx && actx.state }),
  pageHidden(h) {
    pageHidden = h;
    if (h) {
      if (actx) actx.suspend();
      for (const p of players.values()) if (!p.a.paused) p.a.pause();
    } else {
      if (actx && audioUnlocked) actx.resume();
      for (const p of players.values()) if (p.wantPlay) p.a.play().catch(() => pendingPlays.add(p.a));
    }
  },
};

// ------------------------------------------------------------------ native libraries: the ARM emulator and JNI
let M;                           // the emulator (Emscripten module)
const libFiles = new Map();      // "libfoo.so" -> bytes, from lib/<abi>/ in the APK
const loadedLibs = new Set();
const registeredNatives = new Map();   // "pkg/Cls.name(sig)" -> guest function (RegisterNatives)
const nativeFns = new Map();     // native method id -> guest function
let nativeDepth = 0, lastHi = 0;
HW.inNative = () => nativeDepth > 0;   // Java called from native code cannot suspend (a2w.XhrConnection then goes synchronous)
function cstr(s) { const n = M.lengthBytesUTF8(s) + 1, p = M._malloc(n); M.stringToUTF8(s, p, n); return p; }
function guestSym(name) { const p = cstr(name); try { return M._emu_sym(p); } finally { M._free(p); } }
function argTypes(sig) {
  const out = [];
  for (let i = 1; sig[i] !== ')';) {
    const t = sig[i];
    if (t === '[') { while (sig[i] === '[') i++; if (sig[i] === 'L') i = sig.indexOf(';', i); i++; out.push('L'); }
    else if (t === 'L') { i = sig.indexOf(';', i) + 1; out.push('L'); }
    else { out.push(t); i++; }
  }
  return out;
}
/** run guest code: the outermost entry synchronizes direct-buffer mirrors, and gets a JNI local frame unless Java
 *  already made one (a native method call does; library loading, threads and callbacks don't) */
function inJniFrame(fn, javaFrame = false) {
  const outer = nativeDepth === 0;
  if (outer) { if (!javaFrame) HW.java.jniEnter(); M.JNI.syncIn(); }
  nativeDepth++;
  try { return fn(); } catch (e) {
    // a WebAssembly trap inside the emulator is the guest's fault (a bad pointer): say where the guest was
    if (e instanceof WebAssembly.RuntimeError && !e.guestWhere) {
      e.guestWhere = M.UTF8ToString(M._emu_where());
      e.message += ' in native code at ' + e.guestWhere;
      console.error('native crash: ' + e.message);
    }
    throw e;
  } finally {
    nativeDepth--;
    if (outer) { M.JNI.syncOut(); if (!javaFrame) HW.java.jniExit(); }
  }
}
HW.jni = {
  /** a buffer's native mirror address, made on first use (as GetDirectBufferAddress; java.nio.NIOAccess) */
  directAddress(h) {
    let g = HW.java.jniBufferMirrorOf(h);
    const known = g && M.JNI.mirrors.get(g);
    if (known) return known.ptr;
    const bytes = HW.java.jniBufferBytes(h);
    if (bytes == null) return 0;
    const p = M._malloc(Math.max(8, bytes.length));
    M.HEAPU8.set(new Uint8Array(bytes.buffer, bytes.byteOffset, bytes.length), p);
    g = HW.java.jniNewGlobalRef(h);
    M.JNI.mirrors.set(g, { ptr: p, cap: bytes.length, owned: false });
    HW.java.jniBufferMirror(h, g);
    return p;
  },
  /** System.loadLibrary(name): false when the APK has no such library for the emulator */
  loadLibrary(name) {
    const file = 'lib' + name + '.so';
    if (loadedLibs.has(file)) return true;
    const bytes = libFiles.get(file);
    if (!bytes) { HW.log(6, 'E/apk2web: ' + file + ' is not in the APK for ' + (CONFIG.abi || 'an ABI the emulator runs')); return false; }
    loadedLibs.add(file);
    const p = M._malloc(bytes.length);
    M.HEAPU8.set(bytes, p);
    const n = cstr(file);
    inJniFrame(() => M._emu_load_lib(p, bytes.length, n));
    M._free(n);
    nativeFns.clear();
    return true;
  },
  /** a native method (natives.json id) called from Java: AAPCS arguments are (JNIEnv*, this|class, args...) */
  callNative(id, self, slots) {
    const n = CONFIG.natives[id - 1];
    let fn = nativeFns.get(id);
    if (fn === undefined) {
      fn = registeredNatives.get(n.cls + '.' + n.name + n.sig) || (M ? guestSym(n.sym) || guestSym(n.lsym) : 0);
      if (fn) nativeFns.set(id, fn);
    }
    if (!fn) {
      HW.java.jniThrowUnsatisfied('No implementation found for ' + n.cls.replace(/\//g, '.') + '.' + n.name + n.sig +
        ' (tried ' + n.sym + ' and ' + n.lsym + ')');
      return 0;
    }
    const words = [M._emu_env(), self];
    let k = 0;
    for (const t of argTypes(n.sig)) {
      if (t === 'J' || t === 'D') { if (words.length & 1) words.push(0); words.push(slots[k], slots[k + 1]); k += 2; }
      else words.push(slots[k++]);
    }
    const buf = M._malloc(4 * words.length);
    M.HEAP32.set(words, buf >> 2);
    try {
      return inJniFrame(() => { const r = M._emu_call(fn, words.length, buf); lastHi = M._emu_r1(); return r; }, true);
    } finally { M._free(buf); }
  },
  hi: () => lastHi,
};
/** run the emulator's other threads for a while (each frame) */
HW.nativeThreads = () => { if (M) M._emu_threads(); };   // diagnostics: where each native thread is, in the log
// for up to ms milliseconds, in slices, while any of them can run (a loading thread gets most of an idle page)
HW.tickNative = (ms = 6) => {
  if (!M || !loadedLibs.size) return;
  inJniFrame(() => {
    const end = performance.now() + ms;
    while (M._emu_tick(CONFIG.tickBudget || 200000) > 64 && performance.now() < end) { /* next slice */ }
  });
};
setInterval(() => { if (!frameRequested && HW.java) HW.tickNative(24); }, 33);   // native threads also run without frames

// ------------------------------------------------------------------ boot
function progress(f, label) {
  const bar = document.getElementById('bar');
  if (bar) bar.style.width = Math.round(f * 100) + '%';
  if (label && document.getElementById('label')) document.getElementById('label').textContent = label;
}
async function fetchBytes(url, onProgress) {
  const r = await fetch(url);
  if (!r.ok) throw new Error(url + ': HTTP ' + r.status);
  const total = +r.headers.get('content-length') || 0;
  if (!r.body || !total || !onProgress) return new Uint8Array(await r.arrayBuffer());
  const out = new Uint8Array(total);
  const rd = r.body.getReader();
  let n = 0;
  for (;;) {
    const { done, value } = await rd.read();
    if (done) break;
    out.set(value, n); n += value.length;
    onProgress(n / total);
  }
  return n === total ? out : out.subarray(0, n);
}
/** the browser's language as the Java locale, and the app's best resource locale for the browser's languages
 *  (?lang=xx overrides both) */
function pickLocale(have) {
  const q = /[?&]lang=([\w-]+)/.exec(location.search);
  const want = q ? [q[1]] : navigator.languages && navigator.languages.length ? navigator.languages : [navigator.language || 'en-US'];
  const lower = have.map((h) => h.toLowerCase());
  let res = null;
  for (const l of want) {
    const [lang, region] = l.replace('_', '-').split('-');
    const exact = lower.indexOf((lang + (region ? '-' + region : '')).toLowerCase());
    res = exact >= 0 ? have[exact] : have[lower.findIndex((h) => h.split('-')[0] === lang.toLowerCase())] || null;
    if (res || lang.toLowerCase() === 'en') break;   // English is the default resources
  }
  const [lang, region] = want[0].replace('_', '-').split('-');
  return { res, java: lang.toLowerCase() + (region ? '_' + region.toUpperCase() : '') };
}

async function boot() {
  CONFIG = await fetch('config.json').then((r) => r.json());
  PKG = CONFIG.package; APK_PATH = '/data/app/' + PKG + '.apk'; LABEL = CONFIG.label || PKG;
  const m = /(\d+)x(\d+)@(\d+)/.exec(CONFIG.screen || '');
  if (m) { W = +m[1]; H = +m[2]; }
  glRect = [0, 0, W, H];
  for (const c of [glCanvas, uiCanvas]) { c.width = W; c.height = H; }
  HW.config.screen = CONFIG.screen;
  HW.config.sdk = String(CONFIG.sdk || 19);
  if (CONFIG.version) { HW.config.versionName = CONFIG.version.name || ''; HW.config.versionCode = CONFIG.version.code || 0; }
  layout();
  progress(0, 'Loading…');
  const loc = pickLocale(CONFIG.locales || []);
  HW.locale = loc.res;
  HW.config.locale = loc.java;
  const [apkBytes, res, extra, files] = await Promise.all([
    fetchBytes('app.apk', (f) => progress(f * 0.85)),
    fetch('res.json').then((r) => r.json()),
    CONFIG.extra ? fetchBytes('extra.zip') : Promise.resolve(null),
    openDB().catch((e) => { console.warn('IndexedDB unavailable; files will not be saved', e); return []; }),
    CONFIG.sqlite ? loadSqlite().catch((e) => console.warn('SQLite unavailable', e)) : null,
  ]);
  apk = apkBytes;
  HW.res = res;
  parseZip(apk);
  if (extra) parseZip(extra, 'extra:');
  for (const f of CONFIG.libs || []) {
    const b = await HW.apk.bytes('lib/' + CONFIG.abi + '/' + f);
    if (b) libFiles.set(f, b);
  }
  progress(0.9, 'Starting…');
  M = await createEmu({
    log: (s) => HW.log(4, s),
    onFatal: (s) => HW.fatal('native: ' + s),
    locateFile: (p) => p,
    needLibrary: (name) => libFiles.get(name) || null,
  });
  M.registerNative = (key, fn) => { registeredNatives.set(key, fn); nativeFns.clear(); };
  FS = M.FS;
  FS.mkdirTree('/data/app');
  FS.writeFile(APK_PATH, apk);
  FS.mkdirTree('/data/data/' + PKG + '/files');
  FS.mkdirTree('/sdcard');
  // APK expansion files, where Google Play puts them (Context.getObbDir())
  for (const [i, name] of (CONFIG.obb || []).entries()) {
    progress(0.9, 'Loading expansion file ' + (i + 1) + '/' + CONFIG.obb.length + '…');
    const r = await fetch('obb/' + name);
    if (!r.ok) { HW.log(6, 'E/apk2web: expansion file obb/' + name + ': HTTP ' + r.status); continue; }
    FS.mkdirTree('/sdcard/Android/obb/' + PKG);
    FS.writeFile('/sdcard/Android/obb/' + PKG + '/' + name, new Uint8Array(await r.arrayBuffer()));
  }
  // the emulated device as /proc and /sys describe it: apps and libraries probe the CPU before choosing their code
  FS.mkdirTree('/proc/self');
  FS.writeFile('/proc/cpuinfo', 'Processor\t: ARMv7 Processor rev 10 (v7l)\nprocessor\t: 0\nBogoMIPS\t: 1000.00\n\n' +
    'Features\t: swp half thumb fastmult vfp edsp neon vfpv3 tls vfpv4 idiva idivt vfpd32\nCPU implementer\t: 0x41\n' +
    'CPU architecture: 7\nCPU variant\t: 0x2\nCPU part\t: 0xc09\nCPU revision\t: 10\n\nHardware\t: apk2web\nRevision\t: 0000\n' +
    'Serial\t\t: 0000000000000000\n');
  FS.writeFile('/proc/meminfo', 'MemTotal:        1048576 kB\nMemFree:          524288 kB\nBuffers:               0 kB\nCached:           262144 kB\n');
  FS.writeFile('/proc/version', 'Linux version 3.4.0 (apk2web) #1 SMP PREEMPT\n');
  FS.writeFile('/proc/stat', 'cpu  100 0 100 10000 0 0 0 0 0 0\ncpu0 100 0 100 10000 0 0 0 0 0 0\nbtime 0\nprocesses 1\nprocs_running 1\n');
  FS.writeFile('/proc/uptime', '1000.00 1000.00\n');
  FS.writeFile('/proc/self/cmdline', PKG + '\0');
  FS.writeFile('/proc/self/status', 'Name:\t' + PKG.slice(-15) + '\nState:\tR (running)\nTracerPid:\t0\nThreads:\t1\n');
  FS.mkdirTree('/sys/devices/system/cpu/cpu0/cpufreq');
  for (const f of ['present', 'possible', 'online']) FS.writeFile('/sys/devices/system/cpu/' + f, '0\n');
  FS.writeFile('/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq', '1000000\n');
  restoreFiles(files);
  M._emu_init();
  if (CONFIG.imports && CONFIG.imports.length) { const n = cstr(CONFIG.imports.join('\n')); M._emu_declare_imports(n); M._free(n); }
  const gl = glCanvas.getContext('webgl2', { alpha: false, depth: true, stencil: true, antialias: false, premultipliedAlpha: false, preserveDrawingBuffer: true });
  if (gl) HW.GLES = M.GLES = createGLES(gl, (s) => HW.log(5, s));
  else {   // no WebGL 2: GL calls do nothing instead of failing
    console.warn('WebGL 2 is not available: OpenGL ES rendering is off');
    HW.GLES = M.GLES = new Proxy({}, { get: () => () => 0 });
  }
  if (gl && /[?&]gltrace/.test(location.search)) {   // debugging: every GL call, in the log
    const G = M.GLES;
    let n = 0;
    for (const k of Object.keys(G)) {
      const f = G[k];
      if (typeof f !== 'function') continue;
      G[k] = (...a) => { if (n++ < 20000) HW.log(4, 'GL ' + k + '(' + a.map((x) => (typeof x === 'object' && x ? '[' + (x.length ?? 'obj') + ']' : x)).join(', ') + ')'); return f(...a); };
    }
  }
  M.canvasSize = () => [glCanvas.width, glCanvas.height];
  HW.webgl = gl;
  await Promise.all([document.fonts.load('16px Roboto').catch(() => {}), document.fonts.load('bold 16px Roboto').catch(() => {})]);
  const teavm = await loadTeaVM('app.wasm', {});
  HW.java = teavm.exports;
  M.java = HW.java;
  progress(1);
  document.getElementById('loading')?.remove();
  if (HW.verbose && HW.java.debugLog) HW.java.debugLog(true);   // ?log: android.util.Log debug/verbose lines too
  HW.java.main([]);
}

boot().catch((e) => { console.error(e); showMessage('Could not start: ' + (e && e.message ? e.message : e)); });
